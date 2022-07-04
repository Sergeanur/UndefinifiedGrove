MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
//
//			   MISSION NAME : Deconstruction
//				     AUTHOR : Neil
//		       DESICRIPTION :
//
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

{
// *************************************************************************************************************
//								GENERAL INITIALISATION - Every level requires this
// *************************************************************************************************************
SCRIPT_NAME DECON
GOSUB mission_start_DECON
IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_failed_DECON
ENDIF														  
GOSUB mission_cleanup_DECON
MISSION_END

mission_start_DECON:
REGISTER_MISSION_GIVEN
flag_player_on_mission = 1

// general variables that are in every mission - DON'T DECLARE MISSION SPECIFIC VARIABLES HERE
LVAR_INT m_stage
LVAR_INT m_goals
LVAR_INT m_passed
LVAR_INT m_failed  
LVAR_INT m_quit
LVAR_INT m_frame_num
LVAR_INT m_this_frame_time
LVAR_INT m_last_frame_time
LVAR_INT m_time_diff	
// commonly used temporary variables
LVAR_INT temp_int temp_int2 temp_int3
LVAR_FLOAT temp_float temp_float2 temp_float3
LVAR_INT temp_seq
LVAR_FLOAT vec_x vec_y vec_z
LVAR_FLOAT vec2_x vec2_y vec2_z
LVAR_FLOAT x2 y2 z2
LVAR_FLOAT x3 y3 z3
LVAR_INT vis_area
// commonly used flags
LVAR_INT dialogue_flag
LVAR_INT help_flag
// commonly used timers
LVAR_INT dialogue_timer
LVAR_INT help_timer

m_stage 	= 0
m_goals 	= 0
m_passed	= 0
m_failed	= 0	  
m_quit		= 0

dialogue_flag = 0
help_flag	  = 0

dialogue_timer = 0
help_timer	   = 0

TIMERA = 0
TIMERB = 0

mission_loop_DECON:
WAIT 0


// stuff for filshie
IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
	m_passed = 1
ENDIF	
IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_F
	m_failed = 1
ENDIF
									
// Frame count
m_frame_num++
IF m_frame_num > 9
	m_frame_num = 0
ENDIF

// Timer
GET_GAME_TIMER m_this_frame_time
m_time_diff = m_this_frame_time - m_last_frame_time 
m_last_frame_time = m_this_frame_time

dialogue_timer += m_time_diff
help_timer	   += m_time_diff
baddie_spawn_timer += m_time_diff
portaloo_timer += m_time_diff


IF pause_level = 1
	GOTO end_of_main_loop_DECON
ENDIF


	GOSUB DECON_debug_tools

	SWITCH m_stage
		CASE 0
			GOSUB DECON_m_stage_0
		BREAK
		CASE 1
			GOSUB DECON_m_stage_1
		BREAK
		CASE 2
			GOSUB DECON_m_stage_2
		BREAK
		CASE 3
			GOSUB DECON_m_stage_3
		BREAK
		CASE 4
			GOSUB DECON_m_stage_4
		BREAK
		CASE 5
			GOSUB DECON_m_stage_5
		BREAK
	ENDSWITCH

	GOSUB DECON_global_functions
	

//  end of main loop *** don't change ***
end_of_main_loop_DECON:
IF m_quit = 0
	IF m_failed = 0
		IF m_passed = 0																 
			GOTO mission_loop_DECON 
		ELSE
			GOSUB mission_passed_DECON
			RETURN
		ENDIF
	ELSE
		GOSUB mission_failed_DECON
		RETURN
	ENDIF
ELSE
	RETURN // quits out - goes to cleanup
ENDIF



// *************************************************************************************************************
// 												DEBUG TOOLS   
// *************************************************************************************************************
DECON_debug_tools:
	LVAR_INT debug_on	
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_X
		IF debug_on = 0
			debug_on = 1
			WRITE_DEBUG LEVEL_DEBUG_ON
		ELSE
			debug_on = 0
			WRITE_DEBUG LEVEL_DEBUG_OFF
		ENDIF
	ENDIF

	IF debug_on = 1
		// Display mission stage variables for debug
		LVAR_INT display_debug
		VAR_INT DECON_view_debug[8]
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED  PS2_KEY_SPACE
			display_debug++
			IF display_debug > 4
				display_debug = 0
			ENDIF
			CLEAR_ALL_VIEW_VARIABLES
		ENDIF
		IF display_debug = 1
			temp_int = 0
			WHILE temp_int < 6
				IF DOES_OBJECT_EXIST init_portakabin[temp_int]
					GET_OBJECT_HEALTH init_portakabin[temp_int] DECON_view_debug[temp_int]
				ENDIF
			temp_int++
			ENDWHILE
			VIEW_INTEGER_VARIABLE DECON_view_debug[0] cabin_0_health
			VIEW_INTEGER_VARIABLE DECON_view_debug[1] cabin_1_health
			VIEW_INTEGER_VARIABLE DECON_view_debug[2] cabin_2_health
			VIEW_INTEGER_VARIABLE DECON_view_debug[3] cabin_3_health
			VIEW_INTEGER_VARIABLE DECON_view_debug[4] cabin_4_health
			VIEW_INTEGER_VARIABLE DECON_view_debug[5] cabin_5_health
		ENDIF
		IF display_debug = 2
			DECON_view_debug[0] = m_stage
			DECON_view_debug[1] = m_goals
			DECON_view_debug[2] = dialogue_flag
			DECON_view_debug[3] = dialogue_timer
			DECON_view_debug[4] = help_flag
			DECON_view_debug[5] = help_timer
			DECON_view_debug[6] = TIMERA
			DECON_view_debug[7]	= TIMERB
			VIEW_INTEGER_VARIABLE DECON_view_debug[0] m_stage
			VIEW_INTEGER_VARIABLE DECON_view_debug[1] m_goals
			VIEW_INTEGER_VARIABLE DECON_view_debug[2] dialogue_flag
			VIEW_INTEGER_VARIABLE DECON_view_debug[3] dialogue_timer
			VIEW_INTEGER_VARIABLE DECON_view_debug[4] help_flag
			VIEW_INTEGER_VARIABLE DECON_view_debug[5] help_timer
			VIEW_INTEGER_VARIABLE DECON_view_debug[6] TIMERA
			VIEW_INTEGER_VARIABLE DECON_view_debug[7] TIMERB
		ENDIF
		 
		IF display_debug = 3
			DECON_view_debug[0] = cabin_this_health[0]
			DECON_view_debug[1] = cabin_last_health[0]
			DECON_view_debug[2] = cabin_this_health[1]
			DECON_view_debug[3] = cabin_last_health[1]
			DECON_view_debug[4] = cabin_this_health[2]
			DECON_view_debug[5] = cabin_last_health[2]
			DECON_view_debug[6] = cabin_this_health[3]
			DECON_view_debug[7]	= cabin_last_health[3]
			VIEW_INTEGER_VARIABLE DECON_view_debug[0] cabin_this_health[0]
			VIEW_INTEGER_VARIABLE DECON_view_debug[1] cabin_last_health[0]
			VIEW_INTEGER_VARIABLE DECON_view_debug[2] cabin_this_health[1]
			VIEW_INTEGER_VARIABLE DECON_view_debug[3] cabin_last_health[1]
			VIEW_INTEGER_VARIABLE DECON_view_debug[4] cabin_this_health[2]
			VIEW_INTEGER_VARIABLE DECON_view_debug[5] cabin_last_health[2]
			VIEW_INTEGER_VARIABLE DECON_view_debug[6] cabin_this_health[3]
			VIEW_INTEGER_VARIABLE DECON_view_debug[7] cabin_last_health[3]
		ENDIF

		IF display_debug = 4
			DECON_view_debug[0] = player_is_in_crane
		//	DECON_view_debug[1] = 
		//	DECON_view_debug[2] = 
		//	DECON_view_debug[3] = 
		//	DECON_view_debug[4] = 
		//	DECON_view_debug[5] = 
		//	DECON_view_debug[6] = 
		//	DECON_view_debug[7]	= 
			VIEW_INTEGER_VARIABLE DECON_view_debug[0] player_is_in_crane
		//	VIEW_INTEGER_VARIABLE DECON_view_debug[1] 
		//	VIEW_INTEGER_VARIABLE DECON_view_debug[2] 
		//	VIEW_INTEGER_VARIABLE DECON_view_debug[3] 
		//	VIEW_INTEGER_VARIABLE DECON_view_debug[4] 
		//	VIEW_INTEGER_VARIABLE DECON_view_debug[5] 
		//	VIEW_INTEGER_VARIABLE DECON_view_debug[6] 
		//	VIEW_INTEGER_VARIABLE DECON_view_debug[7] 
		ENDIF

		// Quit level - no mission pass/fail - cleanup only 
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_ESC 
			m_quit = 1
		ENDIF

		// Pause level
		LVAR_INT pause_level
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED  PS2_KEY_P
			IF pause_level = 0
				pause_level = 1
				WRITE_DEBUG LEVEL_PAUSED
			ELSE
				pause_level = 0
				WRITE_DEBUG LEVEL_UNPAUSED
			ENDIF		
		ENDIF
	ENDIF
RETURN

// *************************************************************************************************************
//									INITIALISATION - SPECIFIC TO THIS LEVEL
// *************************************************************************************************************
DECON_m_stage_0:
		
		// fake creates
		IF m_goals = -1
			CREATE_CAR PONY 0.0 0.0 0.0 mission_vehicle[0]
			CREATE_CAR PONY 0.0 0.0 0.0 pcar
			CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 mission_ped[0]
			CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 ped_to_add
			CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 spawned_ped
			ADD_BLIP_FOR_COORD 0.0 0.0 0.0 cement_truck1_blip
			ADD_BLIP_FOR_COORD 0.0 0.0 0.0 cement_truck2_blip
			ADD_BLIP_FOR_COORD 0.0 0.0 0.0 location_blip
		ENDIF

		// fade out
		SET_PLAYER_CONTROL player1 OFF
		SET_FADING_COLOUR 0 0 0 
		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		// VARIABLES 
		// objects
		LVAR_INT loo[6]
		LVAR_INT exploding_barrel[5]
		LVAR_INT cement_hole
		LVAR_INT new_hole_in_ground

		// vehicles
		LVAR_INT bull
		LVAR_INT bull2
		LVAR_INT cement_truck
		LVAR_INT truck[2]
		LVAR_INT dumper_truck
		LVAR_INT cement_truck2
		LVAR_INT pcar
		LVAR_INT mission_vehicle[7]

		// peds
		LVAR_INT worker[6]
		LVAR_INT mission_ped[10]
		LVAR_INT boss
		LVAR_INT bull_driver
		LVAR_INT spawned_ped
		LVAR_INT suit_guy

		// blips
		LVAR_INT cabin_blip[6]
		LVAR_INT location_blip
		LVAR_INT object_blip
		LVAR_INT cement_truck1_blip
		LVAR_INT cement_truck2_blip

		// flags
		LVAR_INT cabin_fucked[6]
		LVAR_INT cabin_peds_generated[6]
		LVAR_INT cabins_destroyed
		LVAR_INT alarm_raised
		LVAR_INT mission_ped_flag[10]
		LVAR_INT available_mission_peds
		LVAR_INT cabin_last_health[6]
		LVAR_INT cabin_this_health[6]
		LVAR_INT fix_portaloo_in_hole
		LVAR_INT hit_portacabin_first_time
		LVAR_INT destroyed_dozers
		VAR_INT portacabin_timer
		LVAR_INT cement_hole_flag
		LVAR_INT exlosive_barrel_help

		// floats
		LVAR_FLOAT last_pcar_speed
		LVAR_FLOAT last_car_inertia
		
		// timers
		LVAR_INT baddie_spawn_timer
		LVAR_INT portaloo_timer

		// decision maker
		LVAR_INT worker_dm
		LVAR_INT suit_guy_dm

		// sequences
		LVAR_INT observer_seq1 
		LVAR_INT observer_seq2
		LVAR_INT observer_seq3

		// particle fx
		LVAR_INT particle_fx

		// set flags
		cabin_fucked[0] 		= 0
		cabin_fucked[1] 		= 0
		cabin_fucked[2] 		= 0
		cabin_fucked[3] 		= 0
		cabin_fucked[4] 		= 0
		cabin_fucked[5] 		= 0
		cabins_destroyed 		= 0
		alarm_raised 			= 0
		mission_ped_flag[0] 	= 0
		mission_ped_flag[1] 	= 0
		mission_ped_flag[2] 	= 0
		mission_ped_flag[3] 	= 0
		mission_ped_flag[4] 	= 0
		mission_ped_flag[5] 	= 0
		mission_ped_flag[6] 	= 0
		mission_ped_flag[7] 	= 0
		mission_ped_flag[8] 	= 0
		mission_ped_flag[9]		= 0
		available_mission_peds  = 0
		cabin_peds_generated[0] = 0
		cabin_peds_generated[1] = 0
		cabin_peds_generated[2] = 0
		cabin_peds_generated[3] = 0
		cabin_peds_generated[4] = 0
		cabin_peds_generated[5] = 0
		fix_portaloo_in_hole	= 0
		hit_portacabin_first_time =0
		destroyed_dozers		= 0
		cement_hole_flag		= 0
		exlosive_barrel_help	= 0
	


		GOSUB next_stage
RETURN

// *************************************************************************************************************
//										STAGE 1 - Scripted Cutscene  
// *************************************************************************************************************
DECON_m_stage_1:
	
	CLEAR_HELP
		
		// animated cutscene
		IF m_goals = 0

			LOAD_MISSION_TEXT GARAGE2

			LOAD_CUTSCENE GARAG3A
			WHILE NOT HAS_CUTSCENE_LOADED
				WAIT 0
			ENDWHILE
			SET_AREA_VISIBLE 1
			SET_CHAR_AREA_VISIBLE scplayer 1
			LOAD_SCENE -2031.0 149.0 29.0
			START_CUTSCENE
			DO_FADE 1000 FADE_IN
			WHILE NOT HAS_CUTSCENE_FINISHED
				WAIT 0
			ENDWHILE
			CLEAR_CUTSCENE
			
			SET_PLAYER_CONTROL player1 OFF

			DO_FADE 0 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			SET_CHAR_AREA_VISIBLE scplayer 0
			SET_AREA_VISIBLE 0
		
		m_goals++
		ENDIF
		
		// stage specific stuff
		IF m_goals = 1




		// REQUESTS
		REQUEST_MODEL PORTALOO
		REQUEST_MODEL PORTAKABIN 
		REQUEST_MODEL BARREL4
		REQUEST_MODEL DOZER
		REQUEST_MODEL CEMENT
		REQUEST_MODEL DUMPER
		REQUEST_MODEL BOBCAT
		REQUEST_MODEL WMYCON
		REQUEST_MODEL SHOVEL 
		REQUEST_MODEL COLT45
 		//REQUEST_ANIMATION ON_LOOKERS
		//REQUEST_MODEL CEMENT_IN_HOLE
		REQUEST_MODEL BMYAP
		LOAD_ALL_MODELS_NOW
		WHILE NOT HAS_MODEL_LOADED PORTALOO
		   OR NOT HAS_MODEL_LOADED PORTAKABIN
		   OR NOT HAS_MODEL_LOADED BARREL4
		   OR NOT HAS_MODEL_LOADED DOZER
		   OR NOT HAS_MODEL_LOADED CEMENT
		   OR NOT HAS_MODEL_LOADED BMYAP
			WAIT 0
		ENDWHILE
		WHILE NOT HAS_MODEL_LOADED DUMPER
		   OR NOT HAS_MODEL_LOADED BOBCAT
		   OR NOT HAS_MODEL_LOADED WMYCON
		   OR NOT HAS_MODEL_LOADED SHOVEL
		   OR NOT HAS_MODEL_LOADED COLT45
			WAIT 0
		ENDWHILE

		
		// setup cement hole
		SWAP_NEAREST_BUILDING_MODEL -2049.1714 250.3193 34.4770 20.0 Hubhole1_SFSe Hubhole2_SFSe
		//SWAP_NEAREST_BUILDING_MODEL -2049.1714 250.3193 34.4770 20.0 Hubhole1_SFSe Hubhole4_SFSe
		
		//SWAP_NEAREST_BUILDING_MODEL -2049.1714 250.3193 34.4770 20.0 Hubhole1_SFSe hunhole4_SFSe
//		IF DOES_OBJECT_EXIST san_fran_hub_hole
//			DELETE_OBJECT san_fran_hub_hole
//			CREATE_OBJECT_NO_OFFSET Hubhole2_SFSe -2049.1714 250.3193 33.0784 new_hole_in_ground 
//		ENDIF


		// rebuild portacabins
		temp_int = 0
		WHILE temp_int < 6
			IF DOES_OBJECT_EXIST init_portakabin[temp_int]
				DELETE_OBJECT init_portakabin[temp_int]
			ENDIF
			CREATE_OBJECT PORTAKABIN init_portakabin_x[temp_int] init_portakabin_y[temp_int] init_portakabin_z[temp_int] init_portakabin[temp_int]
			SET_OBJECT_HEADING init_portakabin[temp_int] init_portakabin_h[temp_int]
			SET_OBJECT_DYNAMIC init_portakabin[temp_int] FALSE
			DONT_REMOVE_OBJECT init_portakabin[temp_int]
			ADD_BLIP_FOR_OBJECT init_portakabin[temp_int] cabin_blip[temp_int] 
//			SET_OBJECT_HEALTH init_portakabin[temp_int] 10000
		temp_int++
		ENDWHILE

		// create portaloos
		CREATE_OBJECT_NO_OFFSET PORTALOO -2087.8606 178.2922 35.3947 loo[0]
		SET_OBJECT_HEADING loo[0] 3.3302 
		CREATE_OBJECT_NO_OFFSET PORTALOO -2114.9026 156.9329 35.4667 loo[1]
		SET_OBJECT_HEADING loo[1] 271.6719 
		CREATE_OBJECT_NO_OFFSET PORTALOO -2116.4248 157.4618 35.7225 loo[2]
		SET_OBJECT_HEADING loo[2] 269.1095 
//		CREATE_OBJECT_NO_OFFSET PORTALOO -2060.4109 228.8140 35.9767 loo[3]
//		SET_OBJECT_HEADING loo[3] 4.7 	 
		CREATE_OBJECT_NO_OFFSET PORTALOO -2054.4736 222.6438 35.8767 loo[3]
		SET_OBJECT_HEADING loo[3] 0.0
		FREEZE_OBJECT_POSITION loo[3] TRUE
		CREATE_OBJECT_NO_OFFSET PORTALOO -2069.2620 285.8519 35.8543 loo[4]
		SET_OBJECT_HEADING loo[4] 51.0305 
		CREATE_OBJECT_NO_OFFSET PORTALOO -2134.8726 293.1065 35.4117 loo[5]
		SET_OBJECT_HEADING loo[5] 175.9489

		// create exploding barrels
		CREATE_OBJECT_NO_OFFSET BARREL4 -2109.3970 157.8976 34.4311 exploding_barrel[0]
		CREATE_OBJECT_NO_OFFSET BARREL4	-2108.4932 157.8779 34.4523 exploding_barrel[1]
		CREATE_OBJECT_NO_OFFSET BARREL4	-2091.6829 172.1051 34.4430 exploding_barrel[2]
		CREATE_OBJECT_NO_OFFSET BARREL4	-2128.9502 303.4010 33.8880 exploding_barrel[3]
		CREATE_OBJECT_NO_OFFSET BARREL4	-2129.9233 303.4470 33.8842 exploding_barrel[4]
		temp_int = 0
		WHILE temp_int < 5
			MAKE_OBJECT_TARGETTABLE exploding_barrel[temp_int] TRUE
		temp_int++
		ENDWHILE

		// set crane height of wrecker ball
		IF DOES_OBJECT_EXIST sf_crane1_trolley
			SET_ROPE_HEIGHT_FOR_OBJECT sf_crane1_trolley 0.3
		ENDIF
		IF DOES_OBJECT_EXIST sf_crane1_arm
			SET_OBJECT_HEADING sf_crane1_arm 78.0
			IF DOES_OBJECT_EXIST sf_crane1_arm_LOD
				SET_OBJECT_HEADING sf_crane1_arm_LOD 78.0
			ENDIF
			IF DOES_OBJECT_EXIST sf_crane1_trolley
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS sf_crane1_arm 0.0 15.0 3.453 x y z	// was -1.8
				SET_OBJECT_COORDINATES_AND_VELOCITY sf_crane1_trolley x y z
				SET_OBJECT_HEADING sf_crane1_trolley 78.0
			ENDIF
		ENDIF

		// bulldozers
		CREATE_CAR DOZER -2098.7263 278.5271 34.8743 bull
		SET_CAR_HEADING bull 15.4537
		SET_CAR_HEALTH bull 5000
		SET_CAN_BURST_CAR_TYRES bull FALSE

		CREATE_CAR DOZER -2088.8015 188.5362 34.0469 bull2
		SET_CAR_HEADING bull2 182.8425
		SET_CAR_HEALTH bull2 5000
		SET_CAN_BURST_CAR_TYRES bull2 FALSE
		REQUEST_CAR_RECORDING 551
		WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 551
			WAIT 0
		ENDWHILE
		IF NOT IS_CAR_DEAD bull
			START_PLAYBACK_RECORDED_CAR_LOOPED bull 551
		ENDIF

		// cement truck
		CREATE_CAR CEMENT -2112.6028 193.7894 33.6684 cement_truck
		SET_CAR_HEADING cement_truck 276.2221 

//		// dumper truck
//		CREATE_CAR DUMPER -2114.8062 179.4597 34.2970 dumper_truck
//		SET_CAR_HEADING dumper_truck 269.4381
//		SET_CAN_BURST_CAR_TYRES dumper_truck FALSE

		// trucks
		CREATE_CAR BOBCAT -2102.4607 160.2542 34.8444 truck[0]			
		SET_CAR_HEADING truck[0] 66.4396									
		CREATE_CAR BOBCAT -2121.6694 302.7410 34.4590 truck[1]
		SET_CAR_HEADING truck[1] 160.6221

		// store all the mission vehicles in a single array
		mission_vehicle[0] = bull
		mission_vehicle[1] = bull2
		mission_vehicle[2] = cement_truck
//		mission_vehicle[3] = dumper_truck
		mission_vehicle[4] = truck[0]
		mission_vehicle[5] = truck[1]
		temp_int = 0
		WHILE temp_int < 6
			IF NOT IS_CAR_DEAD mission_vehicle[temp_int]
				SET_CAR_STRAIGHT_LINE_DISTANCE mission_vehicle[temp_int] 50
			ENDIF
		temp_int++
		ENDWHILE
			
		// intial peds
		LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH worker_dm

		CREATE_CHAR PEDTYPE_MISSION1 WMYCON -2098.7581 168.5889 34.0869	worker[0]
		CREATE_CHAR PEDTYPE_MISSION1 WMYCON	-2099.9744 169.3999 34.0947	worker[1]
		CREATE_CHAR PEDTYPE_MISSION1 WMYCON	-2090.9546 265.4674 34.6686	worker[2]
		CREATE_CHAR PEDTYPE_MISSION1 WMYCON	-2089.7610 264.5739 34.6557	worker[3]
		CREATE_CHAR PEDTYPE_MISSION1 WMYCON	-2085.7532 269.4974 34.5456	worker[4]
		CREATE_CHAR PEDTYPE_MISSION1 WMYCON	-2125.7480 303.8916 33.5532	worker[5]

		SET_CHAR_HEADING worker[0]	47.9799 
		SET_CHAR_HEADING worker[1]	229.2811
		SET_CHAR_HEADING worker[2]	229.2811
		SET_CHAR_HEADING worker[3]	51.1609 
		SET_CHAR_HEADING worker[4]	177.1601
		SET_CHAR_HEADING worker[5] 	177.1601

		GIVE_WEAPON_TO_CHAR worker[4] WEAPONTYPE_SHOVEL 9999
		GIVE_WEAPON_TO_CHAR worker[5] WEAPONTYPE_SHOVEL 9999

		temp_int = 0
		WHILE temp_int < 6
			SET_CHAR_DECISION_MAKER worker[temp_int] worker_dm
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER worker[temp_int] TRUE
			SET_CHAR_HEALTH worker[temp_int] 50
			mission_ped[temp_int] = worker[temp_int]
		temp_int++
		ENDWHILE

		TASK_CHAT_WITH_CHAR worker[0] worker[1] TRUE TRUE
		TASK_CHAT_WITH_CHAR worker[1] worker[0] FALSE TRUE

		TASK_CHAT_WITH_CHAR worker[2] worker[3] TRUE TRUE
		TASK_CHAT_WITH_CHAR worker[3] worker[2] FALSE TRUE

		// bull driver
		IF NOT IS_CAR_DEAD bull
			CREATE_CHAR_INSIDE_CAR bull PEDTYPE_MISSION1 WMYCON bull_driver
			SET_CHAR_DECISION_MAKER bull_driver worker_dm
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER bull_driver TRUE
			SET_CHAR_HEALTH bull_driver 50
			mission_ped[6] = bull_driver
		ENDIF

		// suit guys
		CREATE_CHAR PEDTYPE_MISSION1 BMYAP -2089.7090 266.1128 34.6455 suit_guy 
		SET_CHAR_HEADING suit_guy 170.2217
		SET_CHAR_HEALTH	suit_guy 40
		LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY suit_guy_dm
		SET_CHAR_DECISION_MAKER suit_guy suit_guy_dm
		mission_ped[7] = suit_guy

		// remove car parked at garage
		//SWITCH_CAR_GENERATOR gen_car11 0 
		CLEAR_AREA -2036.1797 179.2597 27.8359 5.0 TRUE

		//SET_WANTED_MULTIPLIER 0.1
		SET_PED_DENSITY_MULTIPLIER 1.0
		SET_CAR_DENSITY_MULTIPLIER 1.0
		CLEAR_WANTED_LEVEL player1
	
		START_NEW_SCRIPT cleanup_audio_lines
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2










			CLEAR_PRINTS
			SWITCH_WIDESCREEN ON
			SET_PLAYER_CONTROL player1 OFF
			SET_FIXED_CAMERA_POSITION -2010.4608 171.3564 41.8495 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2011.3015 171.8702 41.6786 JUMP_CUT
 
			SET_CHAR_COORDINATES scplayer -2030.9873 148.5787 27.8359 
			SET_CHAR_HEADING scplayer 359.4339


			vec_x = -2011.3015 + 2010.4608
			vec_y = 171.8702 - 171.3564
			vec_z = 41.6786 - 41.8495
			vec_x *= 10.0
			vec_y *= 10.0 
			vec_z *= 10.0
			x = -2010.4608 + vec_x
			y = 171.3564   + vec_y
			z = 41.8495	   + vec_z
			
			GET_HEADING_FROM_VECTOR_2D vec_x vec_y heading

			LOAD_SCENE_IN_DIRECTION	x y z heading

			LOAD_ALL_MODELS_NOW


//			REQUEST_MODEL SHOPPIE4_SFS
//			REQUEST_MODEL SCAFFOLDING_SFS 
//			REQUEST_MODEL MALL_01_SFS
//			REQUEST_MODEL SHOPPIE6_SFS
//			LOAD_ALL_MODELS_NOW

			DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			PRINT_NOW GAR2_01 5000 1 // Scare off the current construction firm
			
			TIMERA = 0
			m_goals++
		ENDIF

		IF m_goals = 2
			IF TIMERA > 5000
			
//				DO_FADE 250 FADE_OUT
//				WHILE GET_FADING_STATUS 
//					WAIT 0
//				ENDWHILE
				 
						  
				SET_FIXED_CAMERA_POSITION -2076.3936 263.7876 35.7185 0.0 0.0 0.0 //-2082.9609 259.4915 40.8476 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2077.2866 264.2365 35.6941 JUMP_CUT //-2083.7207 260.0294 40.4827 JUMP_CUT
				
				vec_x = -2077.2866 + 2076.3936 
				vec_y = 264.2365 - 263.7876
				vec_z = 35.6941 - 35.7185
				vec_x *= 10.0
				vec_y *= 10.0 
				vec_z *= 10.0
				x = -2076.3936 + vec_x
				y = 263.7876   + vec_y
				z = 35.7185	   + vec_z
				
				GET_HEADING_FROM_VECTOR_2D vec_x vec_y heading

				LOAD_SCENE_IN_DIRECTION	x y z heading

				LOAD_ALL_MODELS_NOW
				


				IF NOT IS_CHAR_DEAD worker[4]
					OPEN_SEQUENCE_TASK temp_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -2099.1619 171.1465 34.0869 PEDMOVE_WALK 60000
						TASK_SCRATCH_HEAD -1 
						TASK_GO_STRAIGHT_TO_COORD -1 -2090.8005 263.5923 34.7131 PEDMOVE_WALK 60000
						TASK_SCRATCH_HEAD -1 
						SET_SEQUENCE_TO_REPEAT temp_seq 1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK worker[4] temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
	
				IF NOT IS_CHAR_DEAD suit_guy
					OPEN_SEQUENCE_TASK temp_seq
						TASK_SCRATCH_HEAD -1
						TASK_LOOK_ABOUT -1 5000
						TASK_PLAY_ANIM -1 IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE -1
						SET_SEQUENCE_TO_REPEAT temp_seq 1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK suit_guy temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF

				
//				DO_FADE 250 FADE_IN
//				WHILE GET_FADING_STATUS 
//					WAIT 0
//				ENDWHILE

				
				PRINT_NOW GAR2_02 5000 1 // Destroy all the portakabins and kill the foreman.
				

				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF	

		IF m_goals = 3

			IF TIMERA > 5500

				DO_FADE 150 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

						  
				IF NOT IS_CHAR_DEAD worker[4]
					SET_CHAR_COORDINATES worker[4] -2098.8330 191.0430 34.2113 
					SET_CHAR_HEADING worker[4] 150.0
				ENDIF


				SET_FIXED_CAMERA_POSITION -2084.9932 195.2235 39.2186 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2085.4658 194.3752 38.9801 JUMP_CUT

				vec_x = -2085.4658 + 2084.9932
				vec_y = 194.3752 - 195.2235
				vec_z = 38.9801 - 39.2186
				vec_x *= 10.0
				vec_y *= 10.0 
				vec_z *= 10.0
				x = -2084.9932 + vec_x
				y = 195.2235   + vec_y
				z = 39.2186	   + vec_z
				
				GET_HEADING_FROM_VECTOR_2D vec_x vec_y heading

				LOAD_SCENE_IN_DIRECTION	x y z heading

				LOAD_ALL_MODELS_NOW

				DO_FADE 150 FADE_IN
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

				PRINT GAR2_05 5000 1 // Construction machinery will help demolish portakabins.
				m_goals++
				TIMERA = 0
			ENDIF
		ENDIF

		IF m_goals = 4
			
			IF TIMERA > 5000
				m_goals = 99
			ENDIF
		ENDIF

		// skip
		IF m_goals > 0
			IF IS_BUTTON_PRESSED PAD1 CROSS
			OR IS_BUTTON_PRESSED PAD1 CIRCLE
				m_goals = 99
			ENDIF	
		ENDIF
	

		// exit
		IF m_goals = 99
						

//			DO_FADE 250 FADE_OUT
//			WHILE GET_FADING_STATUS
//				WAIT 0
//			ENDWHILE

			IF NOT IS_CHAR_DEAD worker[4]
				SET_CHAR_COORDINATES worker[4] -2092.2520 252.5526 34.4575
				CLEAR_CHAR_TASKS worker[4]
				OPEN_SEQUENCE_TASK temp_seq
					TASK_GO_STRAIGHT_TO_COORD -1 -2099.1619 171.1465 34.0869 PEDMOVE_WALK 60000
					TASK_SCRATCH_HEAD -1 
					TASK_GO_STRAIGHT_TO_COORD -1 -2090.8005 263.5923 34.7131 PEDMOVE_WALK 60000
					TASK_SCRATCH_HEAD -1 
					SET_SEQUENCE_TO_REPEAT temp_seq 1
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK worker[4] temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
			ENDIF

			IF NOT IS_CHAR_DEAD suit_guy
				OPEN_SEQUENCE_TASK temp_seq
					TASK_SCRATCH_HEAD -1
					TASK_LOOK_ABOUT -1 5000
					TASK_PLAY_ANIM -1 IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE -1
					SET_SEQUENCE_TO_REPEAT temp_seq 1
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK suit_guy temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
			ENDIF


			IF DOES_OBJECT_EXIST init_portakabin[3]
				SET_OBJECT_COORDINATES init_portakabin[3] -2077.0400 280.395 35.817  
				SET_OBJECT_HEADING init_portakabin[3] 0.0
			ENDIF

			CLEAR_PRINTS
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL player1 ON
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			DO_FADE 0 FADE_IN


//			DO_FADE 250 FADE_IN
//			WHILE GET_FADING_STATUS
//				WAIT 0
//			ENDWHILE

			GOSUB next_stage
		ENDIF

RETURN 


// *************************************************************************************************************
//									STAGE 2 - Wait for portacabins to get destroyed  
// *************************************************************************************************************
DECON_m_stage_2:


//		// adjust portacabin z
//		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
//			IF DOES_OBJECT_EXIST init_portakabin[3]
//				GET_OBJECT_COORDINATES init_portakabin[3] x y z
//				z += 0.01
//				SET_OBJECT_COORDINATES init_portakabin[3] x y z
//				WRITE_DEBUG_WITH_FLOAT z z
//			ENDIF
//		ENDIF
//		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
//			IF DOES_OBJECT_EXIST init_portakabin[3]
//				GET_OBJECT_COORDINATES init_portakabin[3] x y z
//				z += -0.01
//				SET_OBJECT_COORDINATES init_portakabin[3] x y z
//				WRITE_DEBUG_WITH_FLOAT z z
//			ENDIF
//		ENDIF 
//		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_8
//			IF DOES_OBJECT_EXIST init_portakabin[3]
//				GET_OBJECT_COORDINATES init_portakabin[3] x y z
//				y += 0.01
//				SET_OBJECT_COORDINATES init_portakabin[3] x y z
//				WRITE_DEBUG_WITH_FLOAT y y
//			ENDIF
//		ENDIF
//		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_2
//			IF DOES_OBJECT_EXIST init_portakabin[3]
//				GET_OBJECT_COORDINATES init_portakabin[3] x y z
//				y += -0.01
//				SET_OBJECT_COORDINATES init_portakabin[3] x y z
//				WRITE_DEBUG_WITH_FLOAT y y
//			ENDIF
//		ENDIF
//		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_4
//			IF DOES_OBJECT_EXIST init_portakabin[3]
//				GET_OBJECT_COORDINATES init_portakabin[3] x y z
//				x += 0.01
//				SET_OBJECT_COORDINATES init_portakabin[3] x y z
//				WRITE_DEBUG_WITH_FLOAT x x
//			ENDIF
//		ENDIF
//		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_NUMPAD_6
//			IF DOES_OBJECT_EXIST init_portakabin[3]
//				GET_OBJECT_COORDINATES init_portakabin[3] x y z
//				x += -0.01
//				SET_OBJECT_COORDINATES init_portakabin[3] x y z
//				WRITE_DEBUG_WITH_FLOAT x x
//			ENDIF
//		ENDIF
 
		
		// sort out wanted level
		IF IS_PLAYER_PLAYING player1
			IF IS_CHAR_IN_AREA_2D scplayer -2053.2258 139.1389 -2135.4492 309.5472 FALSE
				SET_WANTED_MULTIPLIER 0.0
			ELSE
				SET_WANTED_MULTIPLIER 1.0
			ENDIF
		ENDIF

		// initialisation for stage
		IF m_goals = 0
			IF TIMERA > 0
				PRINT_NOW GAR2_19 10000 1 // destroy all the builders cabins
				m_goals++
				TIMERB = 30000
				dialogue_flag = 0

				
				START_NEW_SCRIPT cleanup_audio_lines
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2

				LOAD_MISSION_AUDIO 3 SOUND_SMASH_PORTACABIN
				WHILE NOT HAS_MISSION_AUDIO_LOADED 3
					WAIT 0
				ENDWHILE

			ENDIF
		ENDIF
		
		// wait for portacabins to get destroyed
		IF m_goals = 1

//			// text prompt reminder
//			IF NOT IS_MESSAGE_BEING_DISPLAYED 
//				IF TIMERA > 40000
//					IF cabins_destroyed = 0
//						PRINT_NOW GAR2_19 7000 1 // destroy all the builders cabins
//					ENDIF
//					IF cabins_destroyed = 1
//						PRINT_NOW GAR2_29 7000 1 
//					ENDIF
//					IF cabins_destroyed = 2
//						PRINT_NOW GAR2_30 7000 1 
//					ENDIF
//					IF cabins_destroyed = 3
//						PRINT_NOW GAR2_31 7000 1 
//					ENDIF
//					IF cabins_destroyed = 4
//						PRINT_NOW GAR2_32 7000 1 
//					ENDIF
//					IF cabins_destroyed = 5
//						PRINT_NOW GAR2_33 7000 1 
//					ENDIF
//					TIMERA = 0
//				ENDIF
//			ENDIF

			IF TIMERB > 30000
				IF DOES_OBJECT_EXIST loo[3] 
					IF IS_CHAR_TOUCHING_OBJECT scplayer loo[3]
						IF audio_line_is_active = 0
							//WRITE_DEBUG dialog1
							SWITCH dialogue_flag
								CASE 0
									$audio_string    = &GAR2_AK		// Shouldn’t have had that curry!
									audio_sound_file = SOUND_GAR2_AK
									START_NEW_SCRIPT audio_line -1 0 0 1 0
								BREAK
								CASE 1
									$audio_string    = &GAR2_AF		// Oh god the stench!
									audio_sound_file = SOUND_GAR2_AF
									START_NEW_SCRIPT audio_line -1 0 0 1 0
								BREAK
							ENDSWITCH
							dialogue_flag++
							IF dialogue_flag > 1
								dialogue_flag = 0
							ENDIF
							TIMERB = 0
						ENDIF
					ENDIF
				ENDIF
			ENDIF

//			IF portacabin_timer > 0
//				CLEAR_WANTED_LEVEL player1
//			ENDIF

			IF cabins_destroyed = 6
				TIMERA = 0
				m_goals++
			ENDIF
 		ENDIF	
		
		IF m_goals = 2
			IF TIMERA > 1000
				m_goals = 99
			ENDIF
		ENDIF

		IF alarm_raised = 1
		AND portacabin_timer <= 0
		AND cabins_destroyed < 6
			IF NOT IS_WANTED_LEVEL_GREATER player1 1
				ALTER_WANTED_LEVEL player1 2
			ENDIF
			CREATE_EMERGENCY_SERVICES_CAR COPCARSF -2039.2551 232.4810 33.9528
			CREATE_EMERGENCY_SERVICES_CAR COPCARSF -2038.1851 254.2709 34.0893
			m_failed = 1
			PRINT_NOW GAR2_40 5000 1
		ENDIF


		// exit
		IF m_goals = 99
			CLEAR_MISSION_AUDIO 3
			CLEAR_ONSCREEN_TIMER portacabin_timer
			GOSUB next_stage
		ENDIF


		// count cabins that have been destroyed
		temp_int = 0
		WHILE temp_int < 6
			IF DOES_OBJECT_EXIST init_portakabin[temp_int]
				IF HAS_OBJECT_BEEN_DAMAGED init_portakabin[temp_int]
					IF cabin_fucked[temp_int] = 0 
						REMOVE_BLIP cabin_blip[temp_int]
						cabins_destroyed++
						cabin_fucked[temp_int] = 1
						IF cabins_destroyed = 1
							PRINT_NOW GAR2_29 7000 1 
						ENDIF
						IF cabins_destroyed = 2
							PRINT_NOW GAR2_30 7000 1 
						ENDIF
						IF cabins_destroyed = 3
							PRINT_NOW GAR2_31 7000 1 
						ENDIF
						IF cabins_destroyed = 4
							PRINT_NOW GAR2_32 7000 1 
						ENDIF
						IF cabins_destroyed = 5
							PRINT_NOW GAR2_33 7000 1 
						ENDIF
						TIMERA = 0
					ENDIF
				ENDIF
			ENDIF
		temp_int++
		ENDWHILE

		// if portacabin is being hit by a vehicle - not the bulldozer don't take off as much health


		// debug skip
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED  PS2_KEY_RETURN
			temp_int = 0
			WHILE temp_int < 6
		   		IF DOES_OBJECT_EXIST init_portakabin[temp_int]
		   			IF NOT HAS_OBJECT_BEEN_DAMAGED init_portakabin[temp_int]
		   				BREAK_OBJECT init_portakabin[temp_int] FALSE
		   			ENDIF
		   		ENDIF
			temp_int++
			ENDWHILE
	   	ENDIF 

RETURN 

// *************************************************************************************************************
//									STAGE 3 - cutscene of foreman  
// *************************************************************************************************************
DECON_m_stage_3:

CLEAR_HELP		

		IF m_goals = 0		 

			CLEAR_PRINTS
			
			// setup
			SET_PLAYER_CONTROL player1 OFF
			SET_FADING_COLOUR 0 0 0 
			DO_FADE 500 FADE_OUT
			WHILE GET_FADING_STATUS
	            WAIT 0
			ENDWHILE
			SWITCH_WIDESCREEN ON

			// set visible area
			GET_AREA_VISIBLE vis_area
			SET_AREA_VISIBLE 0
			

			// set player's position
			IF NOT player_is_in_crane = 1
				IF IS_CHAR_IN_AREA_3D scplayer -2066.8386 209.6444 28.4030 -2040.1006 262.7024 34.9146 FALSE
					SET_CHAR_COORDINATES scplayer -2090.0444 209.4996 33.9509 
					SET_CHAR_HEADING scplayer 250.8130
				ENDIF
			ELSE
				do_not_update_camera_crane1 = 1
			ENDIF
			
			LOAD_MISSION_AUDIO 3 SOUND_TOILET_FLUSH
			WHILE NOT HAS_MISSION_AUDIO_LOADED 3
				WAIT 0
			ENDWHILE 

			// re-create foreman's toilet
			CLEAR_AREA -2054.4736 222.6438 35.8767 3.0 TRUE

			// remove any mission cars or peds from location
			temp_int = 0
			WHILE temp_int < 10
				IF DOES_CHAR_EXIST mission_ped[temp_int]
					IF NOT IS_CHAR_DEAD mission_ped[temp_int]
						IF LOCATE_CHAR_ANY_MEANS_3D mission_ped[temp_int] -2054.4736 222.6438 35.8767 5.0 5.0 3.0 FALSE
							DELETE_CHAR mission_ped[temp_int]
						ENDIF
					ELSE
						DELETE_CHAR mission_ped[temp_int]
					ENDIF
				ENDIF
			temp_int++
			ENDWHILE
			temp_int = 0
			WHILE temp_int < 7
				IF DOES_VEHICLE_EXIST mission_vehicle[temp_int]
					IF NOT IS_CAR_DEAD mission_vehicle[temp_int]
						IF LOCATE_CAR_3D mission_vehicle[temp_int] -2054.4736 222.6438 35.8767 6.0 6.0 3.0 mission_vehicle[temp_int]
							DELETE_CAR mission_vehicle[temp_int]
						ENDIF
					ELSE
						DELETE_CAR mission_vehicle[temp_int]
					ENDIF
				ENDIF
			temp_int++
			ENDWHILE
			temp_int = 0
			WHILE temp_int < 6
				IF DOES_OBJECT_EXIST loo[temp_int]
					IF LOCATE_OBJECT_3D loo[temp_int] -2054.4736 222.6438 35.8767 3.0 3.0 3.0 loo[temp_int]
						DELETE_OBJECT loo[temp_int]
					ENDIF
				ENDIF
			temp_int++
			ENDWHILE
			temp_int = 0										
			WHILE temp_int < 5
				IF DOES_OBJECT_EXIST exploding_barrel[temp_int]
					IF LOCATE_OBJECT_3D exploding_barrel[temp_int] -2054.4736 222.6438 35.8767 3.0 3.0 3.0 exploding_barrel[temp_int]
						DELETE_OBJECT exploding_barrel[temp_int]
					ENDIF
				ENDIF
			temp_int++
			ENDWHILE
			// make sure wrecker ball isn't there either
			IF DOES_OBJECT_EXIST sf_crane1_trolley
				SET_ROPE_HEIGHT_FOR_OBJECT sf_crane1_trolley 0.5
			ENDIF

			IF DOES_OBJECT_EXIST loo[3]
				DELETE_OBJECT loo[3]
			ENDIF
			CREATE_OBJECT_NO_OFFSET PORTALOO -2054.4736 222.6438 35.8767 loo[3]
			SET_OBJECT_HEADING loo[3] 0.0
			SET_OBJECT_COLLISION loo[3] FALSE

			// cut to view behind toilet
//			SET_FIXED_CAMERA_POSITION -2063.9299 226.6998 36.8442 0.0 0.0 0.0
//			POINT_CAMERA_AT_POINT -2063.1084 227.2301 36.6345 JUMP_CUT
									  
			SET_FIXED_CAMERA_POSITION -2058.0498 218.8743 35.7489 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT     -2057.4229 219.6530 35.7245 JUMP_CUT

			// re-create portacabin
			IF DOES_OBJECT_EXIST init_portakabin[2]
				DELETE_OBJECT init_portakabin[2]
			ENDIF
			CREATE_OBJECT PORTAKABIN init_portakabin_x[2] init_portakabin_y[2] init_portakabin_z[2] init_portakabin[2]
			SET_OBJECT_HEADING init_portakabin[2] init_portakabin_h[2]
			SET_OBJECT_DYNAMIC init_portakabin[2] FALSE
			DONT_REMOVE_OBJECT init_portakabin[2]

			// create boss					  
			CREATE_CHAR PEDTYPE_CIVMALE BMYAP -2054.4736 222.6438 34.8767 boss
			SET_CHAR_HEADING boss 90.0

			// create cement truck 2
			CREATE_CAR CEMENT -2037.1759 269.7917 34.9191 cement_truck2
			SET_CAR_HEADING cement_truck2 179.5245
			mission_vehicle[6] = cement_truck2
 
			// freeze any remaining workers that are alive
			temp_int = 0
			WHILE temp_int < 10	
				IF DOES_CHAR_EXIST mission_ped[temp_int]
					IF NOT IS_CHAR_DEAD mission_ped[temp_int]
						IF NOT IS_CHAR_IN_ANY_CAR mission_ped[temp_int]
							FREEZE_CHAR_POSITION mission_ped[temp_int] TRUE
							SET_CHAR_COLLISION mission_ped[temp_int] FALSE
							SET_CHAR_VISIBLE mission_ped[temp_int] FALSE
						ELSE
							STORE_CAR_CHAR_IS_IN_NO_SAVE mission_ped[temp_int] car
							FREEZE_CAR_POSITION car TRUE
							SET_CAR_VISIBLE car FALSE
						ENDIF
					ENDIF
				ENDIF
			temp_int++
			ENDWHILE
			
			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
	            WAIT 0
			ENDWHILE
			m_goals++
		ENDIF

		// skip 
		IF m_goals > 0
			IF IS_BUTTON_PRESSED PAD1 CROSS
			OR IS_BUTTON_PRESSED PAD1 CIRCLE
				m_goals = 99
			ENDIF
		ENDIF
		
		// flush noise
		IF m_goals = 1
			//PRINT_NOW GAR2_06 4000 1 // flush
			IF DOES_OBJECT_EXIST loo[3]
				ATTACH_MISSION_AUDIO_TO_OBJECT 3 loo[3]
				PLAY_MISSION_AUDIO 3
			ENDIF 
			TIMERA = 0
			m_goals++	
		ENDIF

		// break portacabin
		IF m_goals = 2		
			IF TIMERA > 3000
				IF DOES_OBJECT_EXIST init_portakabin[2] 
					BREAK_OBJECT init_portakabin[2]	FALSE
					m_goals++
				ENDIF
			ENDIF
		ENDIF
		
		// dude walk out of loo
		IF m_goals = 3							 
			IF TIMERA > 4000				   	 
				IF NOT IS_CHAR_DEAD boss
					SET_FIXED_CAMERA_POSITION -2051.7214 225.4406 36.5865 0.0 0.0 0.0 
					POINT_CAMERA_AT_POINT     -2052.5605 224.9327 36.3922 JUMP_CUT
 					OPEN_SEQUENCE_TASK temp_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -2057.1367 223.541 34.5820 PEDMOVE_WALK 5000
						IF DOES_OBJECT_EXIST init_portakabin[2]
							TASK_LOOK_AT_OBJECT -1 init_portakabin[2] 100
						ENDIF
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK boss temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
					//PRINT_NOW GAR2_07 5000 1 // FOREMAN - 'What the hell was all the noise out here?' 
					
					$audio_string    = &GAR2_AA	
					audio_sound_file = SOUND_GAR2_AA
					START_NEW_SCRIPT audio_line boss 0 1 1 0

					m_goals++
					TIMERA = 0
				ENDIF 
			ENDIF
		ENDIF
	
		IF m_goals = 4
			IF TIMERA > 3000
			AND audio_line_is_active = 0
				m_goals++	
			ENDIF
		ENDIF								   

		// wait for dude to finish						 
		IF m_goals = 5
			IF NOT IS_CHAR_DEAD boss
				GET_SCRIPT_TASK_STATUS boss PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					SET_CHAR_COORDINATES boss -2057.1367 223.541 34.5820 
					SET_CHAR_HEADING boss 97.4598
					SET_FIXED_CAMERA_POSITION -2056.0564 222.2135 36.2036 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT     -2056.9387 222.6817 36.1606 JUMP_CUT
					TASK_LOOK_AT_COORD boss -2069.0 229.0 36.0 2000
					//PRINT_NOW GAR2_08 5000 1 // FOREMAN - 'What the f....'
					TIMERA = 0
					m_goals++
				ENDIF
			ENDIF
		ENDIF

		// wait for text to finish
		IF m_goals = 6
			IF TIMERA > 2000				
				TIMERA = 0
				SET_FIXED_CAMERA_POSITION -2051.7214 225.4406 36.5865 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT     -2052.5605 224.9327 36.3922 JUMP_CUT
				m_goals++
			ENDIF
		ENDIF

		// run back into toilet
		IF m_goals = 7
			IF NOT IS_CHAR_DEAD boss
				//PRINT_NOW GAR2_09 5000 1 // FOREMAN - 'Arrghhhh! A nutter!'
				$audio_string    = &GAR2_AB	
				audio_sound_file = SOUND_GAR2_AB
				START_NEW_SCRIPT audio_line boss 0 1 1 0
				TASK_GO_STRAIGHT_TO_COORD boss -2054.4736 222.6438 35.8767 PEDMOVE_RUN 5000	
				TIMERA = 0
				m_goals++
			ENDIF	
		ENDIF

		// change camera angle back
		IF m_goals = 8
			IF TIMERA > 1500
				m_goals++
			ENDIF
		ENDIF

		// delete boss
		IF m_goals = 9
			IF NOT IS_CHAR_DEAD boss
				GET_SCRIPT_TASK_STATUS boss TASK_GO_STRAIGHT_TO_COORD temp_int
				IF temp_int = FINISHED_TASK
					DELETE_CHAR boss
					TIMERA = 0
					m_goals++
				ENDIF 
			ENDIF
		ENDIF

		// show hole
		IF m_goals = 10
			IF TIMERA > 2000
				CLEAR_PRINTS
				SET_FIXED_CAMERA_POSITION -2037.4390 244.0506 43.8289 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2038.1473 244.4092 43.2209 JUMP_CUT
				PRINT GAR2_11 5000 1 // push guy into ditch
				m_goals++
			ENDIF
		ENDIF

		// wait a few secs
		IF m_goals = 11
			IF TIMERA > 5000
				m_goals++
			ENDIF
		ENDIF

		// show cement truck
		IF m_goals = 12 
			CLEAR_PRINTS
			SET_FIXED_CAMERA_POSITION -2031.7805 262.1947 36.3181 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2032.4756 262.9132 36.3398 JUMP_CUT
			PRINT GAR2_12 5000 1 // bury the fucker
			TIMERA = 0
			m_goals++
		ENDIF

		// wait a couple of secs
		IF m_goals = 13
			IF TIMERA > 5000
				m_goals = 99
			ENDIF	
		ENDIF
			
		// finish
		IF m_goals = 99

			START_NEW_SCRIPT cleanup_audio_lines
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2

			IF DOES_OBJECT_EXIST loo[3]
				SET_OBJECT_COLLISION loo[3] TRUE
			ENDIF
			CLEAR_PRINTS	 
			IF player_is_in_crane = 0
				SET_PLAYER_CONTROL player1 ON
			ELSE
				do_not_update_camera_crane1 = 0
			ENDIF
			
			SET_AREA_VISIBLE vis_area
			RESTORE_CAMERA_JUMPCUT 
			SET_CAMERA_BEHIND_PLAYER
			SWITCH_WIDESCREEN OFF

			IF DOES_OBJECT_EXIST init_portakabin[2]
				DELETE_OBJECT init_portakabin[2]
			ENDIF
			IF DOES_CHAR_EXIST boss
				DELETE_CHAR boss
			ENDIF

			// defrost any mission peds that are alive
			temp_int = 0
			WHILE temp_int < 10	
				IF DOES_CHAR_EXIST mission_ped[temp_int]
					IF NOT IS_CHAR_DEAD mission_ped[temp_int]
						IF NOT IS_CHAR_IN_ANY_CAR mission_ped[temp_int]
							FREEZE_CHAR_POSITION mission_ped[temp_int] FALSE
							SET_CHAR_COLLISION mission_ped[temp_int] TRUE
							SET_CHAR_VISIBLE mission_ped[temp_int] TRUE
						ELSE
							STORE_CAR_CHAR_IS_IN_NO_SAVE mission_ped[temp_int] car
							FREEZE_CAR_POSITION car FALSE
							SET_CAR_VISIBLE car TRUE
						ENDIF
					ENDIF
				ENDIF
			temp_int++
			ENDWHILE
			
			IF player_is_in_crane = 1
				reset_crane_camera = 1
			ENDIF

			CLEAR_MISSION_AUDIO 3

			GOSUB next_stage
		ENDIF

RETURN 

// *************************************************************************************************************
//									STAGE 4 - deal with foreman  
// *************************************************************************************************************
DECON_m_stage_4:
		

		// sort out wanted level
		IF IS_PLAYER_PLAYING player1
			IF IS_CHAR_IN_AREA_2D scplayer -2053.2258 139.1389 -2135.4492 309.5472 FALSE
				SET_WANTED_MULTIPLIER 0.0
			ELSE
				SET_WANTED_MULTIPLIER 1.0
			ENDIF
		ENDIF

		IF m_goals = 0

			START_NEW_SCRIPT cleanup_audio_lines
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			
			// add blip for toilet
			IF DOES_OBJECT_EXIST loo[3]
				ADD_BLIP_FOR_OBJECT loo[3] object_blip
				CHANGE_BLIP_COLOUR object_blip RED
			ENDIF 
			// add marker for hole
			ADD_BLIP_FOR_COORD -2048.6436 250.1341 35.5400 location_blip
			PRINT GAR2_10 7000 1 // bury the foreman and his toilet 
			TIMERA = 0
			dialogue_flag = 0
			dialogue_timer = 4000
			portaloo_timer = 0
			cement_hole_flag = -1
			m_goals++
		ENDIF

		// wait for portaloo to get put in hole
		IF m_goals = 1

			// print dialogue if portacabin is hit or moved
			IF dialogue_timer > 5000
				IF NOT IS_MESSAGE_BEING_DISPLAYED 
					IF DOES_OBJECT_EXIST loo[3]
						GET_OBJECT_SPEED loo[3] temp_float 
						IF temp_float > 2.0
						OR IS_CHAR_TOUCHING_OBJECT scplayer loo[3]
							
							IF audio_line_is_active = 0
								//WRITE_DEBUG dialog2
								SWITCH dialogue_flag
									CASE 0
										$audio_string    = &GAR2_AC
										audio_sound_file = SOUND_GAR2_AC
									BREAK
									CASE 1
										$audio_string    = &GAR2_AD
										audio_sound_file = SOUND_GAR2_AD
									BREAK
									CASE 2
										$audio_string    = &GAR2_AE
										audio_sound_file = SOUND_GAR2_AE
									BREAK
									CASE 3
										$audio_string    = &GAR2_AF
										audio_sound_file = SOUND_GAR2_AF
									BREAK
									CASE 4
										$audio_string    = &GAR2_AG
										audio_sound_file = SOUND_GAR2_AG
									BREAK
									CASE 5
										$audio_string    = &GAR2_AH
										audio_sound_file = SOUND_GAR2_AH
									BREAK
									CASE 6
										$audio_string    = &GAR2_AJ
										audio_sound_file = SOUND_GAR2_AJ
									BREAK
									CASE 7
										$audio_string    = &GAR2_AK
										audio_sound_file = SOUND_GAR2_AK
									BREAK
									CASE 8
										$audio_string    = &GAR2_AL
										audio_sound_file = SOUND_GAR2_AL
									BREAK
									CASE 9
										$audio_string    = &GAR2_AM
										audio_sound_file = SOUND_GAR2_AM
									BREAK																	 
								ENDSWITCH
								START_NEW_SCRIPT audio_line -1 0 0 1 0
								dialogue_flag++
								IF dialogue_flag > 7
									dialogue_flag = 0
								ENDIF
								dialogue_timer = 0
								WAIT 1
							ENDIF							
						ENDIF 
					ENDIF
				ENDIF
			ENDIF

			IF portaloo_timer > 150000
			AND portaloo_timer < 151000
				CLEAR_PRINTS
				PRINT_NOW GAR2_34 7000 1 // ~s~Hurry up! The cops will be here soon!
				portaloo_timer = 151001
				dialogue_timer = 0
			ENDIF


			IF portaloo_timer > 200000
				IF NOT IS_WANTED_LEVEL_GREATER player1 1
					ALTER_WANTED_LEVEL player1 2
				ENDIF
				CREATE_EMERGENCY_SERVICES_CAR COPCARSF -2039.2551 232.4810 33.9528
				CREATE_EMERGENCY_SERVICES_CAR COPCARSF -2038.1851 254.2709 34.0893
				m_failed = 1
				PRINT_NOW GAR2_35 5000 1 // ~r~You took too long to bury the foreman.
			ENDIF

			IF DOES_OBJECT_EXIST loo[3]
				IF LOCATE_OBJECT_3D loo[3] -2048.8862 250.6607 32.5177 2.8980 5.0500 2.000 FALSE
					IF NOT IS_MESSAGE_BEING_DISPLAYED
					AND audio_line_is_active = 0 

						START_NEW_SCRIPT cleanup_audio_lines
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
					
						CLEAR_PRINTS
						PRINT_NOW GAR2_36 7000 1 //get a cement truck.
						REMOVE_BLIP location_blip

						REMOVE_BLIP object_blip
						//ADD_BLIP_FOR_COORD -2049.7065 240.5694 34.7620 location_blip

						m_goals++
						TIMERA = 0
						TIMERB = 0
					ENDIF
				ENDIF
			ENDIF

			GOSUB decon_empty_hole_of_cement_trucks

		ENDIF

		// look after behaviour of cement truck for this small section.
		IF m_goals > 1
		AND m_goals < 4
			

			IF IS_CHAR_IN_ANY_CAR scplayer
				STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
				IF IS_CAR_MODEL car CEMENT
					IF NOT cement_hole_flag = 1
						PRINT_NOW GAR2_37 7000 1 // back truck into position

						IF DOES_BLIP_EXIST cement_truck1_blip
							REMOVE_BLIP cement_truck1_blip
						ENDIF
						IF DOES_BLIP_EXIST cement_truck2_blip
					   		REMOVE_BLIP	cement_truck2_blip
						ENDIF
						IF DOES_BLIP_EXIST location_blip
							REMOVE_BLIP location_blip
						ENDIF

						ADD_BLIP_FOR_COORD  -2049.7065 240.5694 34.7620	location_blip
						
						cement_hole_flag = 1
					ENDIF
				ELSE	
					IF NOT cement_hole_flag = 0
						GOSUB decon_cement_hole_blips
						cement_hole_flag = 0
					ENDIF
				ENDIF
			ELSE
				IF NOT cement_hole_flag = 0
					GOSUB decon_cement_hole_blips
					cement_hole_flag = 0
				ENDIF
			ENDIF


			GOSUB decon_empty_hole_of_cement_trucks

		ENDIF

		// wait a couple of secs for player to read text
		IF m_goals = 2
  			//IF NOT IS_MESSAGE_BEING_DISPLAYED
				TIMERA = 0
				TIMERB = 0
				m_goals++
			//ENDIF

			// fix portaloo
			IF fix_portaloo_in_hole = 0
				IF DOES_OBJECT_EXIST loo[3]
					IF NOT IS_OBJECT_ON_SCREEN loo[3]
						SET_OBJECT_COORDINATES loo[3] -2048.9568 250.2059 32.6542 
						SET_OBJECT_HEADING loo[3] 158.5600
						SET_OBJECT_ROTATION loo[3] -170.1394 -88.8094 -21.4400
						fix_portaloo_in_hole = 1
					ENDIF
				ENDIF	
			ENDIF

		ENDIF

		// wait for player to back cement truck into position
		IF m_goals = 3	

			// fix portaloo
			IF fix_portaloo_in_hole = 0
				IF DOES_OBJECT_EXIST loo[3]
					IF NOT IS_OBJECT_ON_SCREEN loo[3]
						SET_OBJECT_COORDINATES loo[3] -2048.9568 250.2059 32.6542 
						SET_OBJECT_HEADING loo[3] 158.5600
						SET_OBJECT_ROTATION loo[3] -170.1394 -88.8094 -21.4400
						fix_portaloo_in_hole = 1
					ENDIF
				ENDIF	
			ENDIF

			IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2049.7065 240.5694 34.7620 3.0 3.0 3.0 TRUE
				IF IS_CHAR_IN_MODEL scplayer CEMENT
					IF IS_CAR_STOPPED pcar
						IF NOT IS_CAR_DEAD pcar
							GET_CAR_HEADING pcar heading
							IF heading < 190.0
							AND heading >170.0
								REMOVE_BLIP location_blip
								m_goals = 99
							ELSE
								PRINT_NOW GAR2_14 1000 1 // straighten up!	
							ENDIF
						ENDIF
					ENDIF
				ELSE
					IF TIMERA > 30000
						PRINT_NOW GAR2_20 7000 1 // you need a cement truck
						TIMERA = 0
					ENDIF	
				ENDIF
			ELSE
//				IF TIMERA < 7000
//					CLEAR_PRINTS
//					TIMERA = 7000
//				ENDIF	
			ENDIF

//			// text prompt reminder
//			IF TIMERB > 30000
//				PRINT GAR2_13 10000 1 //Back a cement mixer into position in front of the hole.
//				TIMERB = 0
//			ENDIF

		ENDIF

		IF m_goals = 99
			GOSUB next_stage
		ENDIF

RETURN

decon_cement_hole_blips:

	IF DOES_BLIP_EXIST location_blip
		REMOVE_BLIP location_blip
	ENDIF

	// blip behaviour - truck1
	IF NOT IS_CAR_DEAD cement_truck
		IF NOT DOES_BLIP_EXIST cement_truck1_blip
			ADD_BLIP_FOR_CAR cement_truck cement_truck1_blip
			SET_BLIP_AS_FRIENDLY cement_truck1_blip TRUE
			PRINT_NOW GAR2_36 7000 1 //get a cement truck.	
		ENDIF
	ENDIF

	// truck 2
	IF NOT IS_CAR_DEAD cement_truck2
		IF NOT DOES_BLIP_EXIST cement_truck2_blip
			ADD_BLIP_FOR_CAR cement_truck2 cement_truck2_blip
			SET_BLIP_AS_FRIENDLY cement_truck2_blip TRUE
			PRINT_NOW GAR2_36 7000 1 //get a cement truck.	
		ENDIF
	ENDIF


RETURN

decon_empty_hole_of_cement_trucks:

	// if cement trucks get stuck in hole, delete them when offscreen
	IF DOES_VEHICLE_EXIST cement_truck
		IF NOT IS_CAR_DEAD cement_truck
			IF LOCATE_CAR_3D cement_truck -2049.1990 250.3882 32.9084 2.4540 5.0420 2.3 FALSE
				IF NOT IS_CAR_ON_SCREEN cement_truck
					DELETE_CAR cement_truck
					REMOVE_BLIP cement_truck1_blip
				ENDIF
			ENDIF
		ELSE
			MARK_CAR_AS_NO_LONGER_NEEDED cement_truck
			REMOVE_BLIP cement_truck1_blip
		ENDIF
	ELSE
		IF NOT IS_POINT_ON_SCREEN -2112.6028 193.7894 33.6684 5.0 
			IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY -2112.6028 193.7894 33.6684 3.0 3.0 3.0
				CREATE_CAR CEMENT -2112.6028 193.7894 33.6684 cement_truck
				SET_CAR_HEADING cement_truck 276.2221	
				IF DOES_BLIP_EXIST cement_truck1_blip
					REMOVE_BLIP cement_truck1_blip
				ENDIF
				ADD_BLIP_FOR_CAR cement_truck cement_truck1_blip
				SET_BLIP_AS_FRIENDLY cement_truck1_blip TRUE
			ENDIF 
		ENDIF
	ENDIF


	IF DOES_VEHICLE_EXIST cement_truck2
		IF NOT IS_CAR_DEAD cement_truck2
			IF LOCATE_CAR_3D cement_truck2 -2049.1990 250.3882 32.9084 2.4540 5.0420 2.3 FALSE
				IF NOT IS_CAR_ON_SCREEN cement_truck2
					DELETE_CAR cement_truck2
					REMOVE_BLIP cement_truck2_blip
				ENDIF
			ENDIF
		ELSE
			MARK_CAR_AS_NO_LONGER_NEEDED cement_truck2
			REMOVE_BLIP cement_truck2_blip
		ENDIF
	ELSE
		IF NOT IS_POINT_ON_SCREEN -2037.1759 269.7917 34.9191 5.0 
			IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY -2037.1759 269.7917 34.9191 3.0 3.0 3.0
				CREATE_CAR CEMENT -2037.1759 269.7917 34.9191 cement_truck2
				SET_CAR_HEADING cement_truck2 179.5245
				IF DOES_BLIP_EXIST cement_truck2_blip
					REMOVE_BLIP cement_truck2_blip
				ENDIF
				ADD_BLIP_FOR_CAR cement_truck2 cement_truck2_blip
				SET_BLIP_AS_FRIENDLY cement_truck2_blip	TRUE
			ENDIF 
		ENDIF
	ENDIF

RETURN


// *************************************************************************************************************
//									STAGE 5 - scripted cut of cement mixer filling hole  
// *************************************************************************************************************
DECON_m_stage_5:

		// pour cement
		IF m_goals = 0

			CLEAR_PRINTS
			SET_PLAYER_CONTROL player1 OFF

			DO_FADE 250 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			
			LOAD_MISSION_AUDIO 3 SOUND_CEMENT_POUR
			WHILE NOT HAS_MISSION_AUDIO_LOADED 3
				WAIT 0 
			ENDWHILE

			IF DOES_OBJECT_EXIST loo[3]
				IF NOT IS_OBJECT_ON_SCREEN loo[3]
					SET_OBJECT_COORDINATES loo[3] -2048.9568 250.2059 32.6542 
					SET_OBJECT_HEADING loo[3] 158.5600
					SET_OBJECT_ROTATION loo[3] -170.1394 -88.8094 -21.4400
					//fix_portaloo_in_hole = 1
				ENDIF
			ENDIF	
			
			SWITCH_WIDESCREEN ON
//			SET_FIXED_CAMERA_POSITION -2043.7852 251.9825 36.4564 0.0 0.0 0.0
//			POINT_CAMERA_AT_POINT -2044.5497 251.3475 36.3463 JUMP_CUT

			SET_FIXED_CAMERA_POSITION -2046.3302 255.5639 35.1158  0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2046.6879 254.6398 34.9808 JUMP_CUT

			// remove any remaining builders
			temp_int = 0
			WHILE temp_int < 10
				IF DOES_CHAR_EXIST mission_ped[temp_int]
					DELETE_CHAR mission_ped[temp_int] 
					mission_ped[temp_int] = 0
				ENDIF
			temp_int++
			ENDWHILE
							   
			// get this vehicle out of the way before performing test
			SET_CHAR_COORDINATES scplayer -2049.7344 241.7195 70.7037
			SET_CHAR_HEADING scplayer 176.8350

			// check if any vehicles are in the way
			temp_int = 0
			WHILE temp_int < 7
				IF NOT IS_CAR_DEAD mission_vehicle[temp_int]
					IF LOCATE_CAR_3D mission_vehicle[temp_int] -2049.7344 241.7195 34.7037 7.0 7.0 5.0 FALSE
						DELETE_CAR mission_vehicle[temp_int]
					ENDIF
				ENDIF
			temp_int++
			ENDWHILE



			// delete any cars in the hole
			temp_int = 0
			WHILE temp_int < 7
				IF NOT IS_CAR_DEAD mission_vehicle[temp_int]
					IF LOCATE_CAR_3D mission_vehicle[temp_int] -2048.8862 250.6607 32.5177 2.8980 5.0500 2.000 FALSE
						DELETE_CAR mission_vehicle[temp_int]
					ENDIF
				ELSE
					DELETE_CAR mission_vehicle[temp_int]	
				ENDIF
			temp_int++
			ENDWHILE



			CLEAR_AREA -2049.7344 241.7195 34.7037 10.0 TRUE
			
			// set truck back in correct position		   			   
			SET_CHAR_COORDINATES scplayer -2049.7344 241.7195 34.7037
			SET_CHAR_HEADING scplayer 176.8350


			IF NOT IS_CAR_DEAD pcar
				SET_VEHICLE_QUATERNION pcar -0.0448 0.0178 1.0030 -0.0611 
				//FREEZE_CAR_POSITION pcar TRUE
				CREATE_OBJECT_NO_OFFSET Hubhole3_SFSe -2049.1714 250.3193 30.8784 cement_hole
				//SET_OBJECT_VISIBLE cement_hole FALSE
				//DONT_REMOVE_OBJECT cement_hole
				CREATE_FX_SYSTEM_ON_CAR_WITH_DIRECTION cement pcar 0.0 -4.4 0.0 0.0 -1.0 0.0 TRUE particle_fx 
			ENDIF

			WAIT 500
	

			DO_FADE 250 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

//			SET_FIXED_CAMERA_POSITION -2043.4509 251.4274 36.2142 0.0 0.0 0.0 
//			POINT_CAMERA_AT_POINT -2044.1686 250.7310 36.2195 JUMP_CUT
			
			TIMERA = 0
			
			m_goals++	
		ENDIF

		IF m_goals = 1	 
			temp_float = 0.0
			WHILE temp_float < 1.0
				WAIT 0
					IF NOT IS_CAR_DEAD pcar
						CONTROL_MOVABLE_VEHICLE_PART pcar temp_float
					ENDIF
			temp_float +=@ 0.01
			ENDWHILE
			m_goals++
		ENDIF

		IF m_goals = 2
			IF TIMERA > 2000
				PLAY_FX_SYSTEM particle_fx

				IF NOT IS_CAR_DEAD pcar
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pcar 0.0 -4.4 0.0 x y z
					SET_MISSION_AUDIO_POSITION 3 X Y Z
					PLAY_MISSION_AUDIO 3 
				ENDIF
				TIMERA = 0
				m_goals++
			ENDIF
		ENDIF

		IF m_goals = 3
			IF TIMERA > 1000
//				IF DOES_OBJECT_EXIST cement_hole
//					SET_OBJECT_VISIBLE cement_hole TRUE
//				ENDIF
				m_goals++
			ENDIF
		ENDIF

		IF m_goals = 4					
			IF SLIDE_OBJECT cement_hole -2049.1714 250.3193 34.1770 0.01 0.01 0.009 FALSE 
   				TIMERA = 0
				CLEAR_MISSION_AUDIO 3
				STOP_FX_SYSTEM particle_fx
   				m_goals++
			ENDIF
		ENDIF

		IF m_goals = 5
			IF TIMERA > 2000
				m_goals = 99
			ENDIF
		ENDIF

		IF m_goals = 99
			IF NOT IS_CAR_DEAD pcar
				//FREEZE_CAR_POSITION pcar FALSE
			ENDIF
			SET_PLAYER_CONTROL player1 ON
			SWITCH_WIDESCREEN OFF
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			CLEAR_MISSION_AUDIO 3
			m_passed = 1
		ENDIF	

RETURN 


// *************************************************************************************************************
//						GLOBAL FUNCTIONS - run continuously throughout lifetime of level  
// *************************************************************************************************************
DECON_global_functions:


	// check if alarm has been raised
	IF m_frame_num = 1
	AND alarm_raised = 0

		// check if any of the initial peds have been attacked
		temp_int = 0
		WHILE temp_int < 6
			IF DOES_CHAR_EXIST worker[temp_int]
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR worker[temp_int] scplayer
				OR IS_CHAR_DEAD worker[temp_int]
					alarm_raised = 1
				ENDIF
			ENDIF
		temp_int++
		ENDWHILE

		// suit guy
		IF DOES_CHAR_EXIST suit_guy
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR suit_guy scplayer
			OR IS_CHAR_DEAD suit_guy
				alarm_raised = 1
			ENDIF
		ENDIF

		// bull driver
		IF DOES_CHAR_EXIST bull_driver
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR bull_driver scplayer
			OR IS_CHAR_DEAD bull_driver
				alarm_raised = 1
			ENDIF
		ENDIF

		// check if portabins have been attacked
		temp_int = 0
		WHILE temp_int < 6
			IF DOES_OBJECT_EXIST init_portakabin[temp_int]
				GET_OBJECT_HEALTH init_portakabin[temp_int] temp_int2
				IF temp_int2 < 900
					alarm_raised = 1
				ENDIF
			ENDIF
		temp_int++
		ENDWHILE

		// check if any of the building site vehicles have been stolen or damaged
		temp_int = 0
		WHILE temp_int < 7
			IF DOES_VEHICLE_EXIST mission_vehicle[temp_int] 
				IF NOT IS_CAR_DEAD mission_vehicle[temp_int]
					IF IS_CHAR_IN_CAR scplayer mission_vehicle[temp_int]
						alarm_raised = 1
					ENDIF
					IF HAS_CAR_BEEN_DAMAGED_BY_CHAR mission_vehicle[temp_int] scplayer
						alarm_raised = 1
					ENDIF
				ENDIF
			ENDIF
		temp_int++
		ENDWHILE

		// if alarm has been raised clear all the current ped task
		IF alarm_raised = 1
			temp_int = 0
			WHILE temp_int < 10
				IF NOT IS_CHAR_DEAD mission_ped[temp_int]
					CLEAR_CHAR_TASKS mission_ped[temp_int]
				ENDIF
			temp_int++
			ENDWHILE
		ENDIF
		
		// switch on timer
		IF alarm_raised = 1
			portacabin_timer = 180000
			DISPLAY_ONSCREEN_TIMER portacabin_timer TIMER_DOWN
			PRINT GAR2_41 7000 1 // destroy all the portables before the cops arrive!
		ENDIF

	ELSE
		
		IF exlosive_barrel_help = 0
			IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED 
			AND NOT IS_MESSAGE_BEING_DISPLAYED
				IF IS_CHAR_IN_ANY_CAR scplayer
					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
					IF IS_CAR_MODEL car DOZER
						PRINT_HELP GAR2_42
						exlosive_barrel_help++
					ENDIF
				ENDIF 
			ENDIF
		ENDIF

	ENDIF

	// put up message if player destroys both bulldozers
	IF destroyed_dozers = 0
	AND m_stage <= 2
		IF IS_CAR_DEAD bull
		AND IS_CAR_DEAD bull2
			IF NOT IS_MESSAGE_BEING_DISPLAYED
				PRINT_NOW GAR2_39 7000 1 // other heavy vehicle 
				destroyed_dozers++
			ENDIF
		ENDIF
	ENDIF


	// baddie ai if alarm has been triggered
	IF alarm_raised = 1

		// suit guy - make him run towards player then flee char
		IF NOT IS_CHAR_DEAD suit_guy
			GET_SCRIPT_TASK_STATUS suit_guy PERFORM_SEQUENCE_TASK temp_int
			IF temp_int = FINISHED_TASK
				OPEN_SEQUENCE_TASK temp_seq
					TASK_GOTO_CHAR -1 scplayer 60000 20.0
					TASK_SMART_FLEE_CHAR -1 scplayer 300.0 60000 
				CLOSE_SEQUENCE_TASK temp_seq
				PERFORM_SEQUENCE_TASK suit_guy temp_seq
				CLEAR_SEQUENCE_TASK temp_seq
				MARK_CHAR_AS_NO_LONGER_NEEDED suit_guy
				mission_ped[7] = 0
			ENDIF
		ENDIF

		// 2 observer sequences - for all the peds that don't have task_kill_char_on_foot
		OPEN_SEQUENCE_TASK observer_seq1
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				TASK_GOTO_CHAR -1 scplayer 60000 5.0
			ELSE
				TASK_GOTO_CHAR -1 scplayer 60000 15.0
			ENDIF
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				TASK_PLAY_ANIM -1 FIGHTIDLE PED 4.0 FALSE FALSE FALSE TRUE -1
			ELSE
				//TASK_PLAY_ANIM -1 panic_point ON_LOOKERS 4.0 FALSE FALSE FALSE FALSE -1
			ENDIF
		CLOSE_SEQUENCE_TASK observer_seq1
		OPEN_SEQUENCE_TASK observer_seq2
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				TASK_GOTO_CHAR -1 scplayer 60000 7.5
			ELSE
				TASK_GOTO_CHAR -1 scplayer 60000 10.0
			ENDIF
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				TASK_PLAY_ANIM -1 FIGHTIDLE PED 4.0 FALSE FALSE FALSE TRUE -1
			ELSE
				//TASK_PLAY_ANIM -1 panic_shout ON_LOOKERS 4.0 FALSE FALSE FALSE FALSE -1
		  	ENDIF
		CLOSE_SEQUENCE_TASK observer_seq2
		OPEN_SEQUENCE_TASK observer_seq3
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				TASK_GOTO_CHAR -1 scplayer 60000 10.0
			ELSE
				TASK_GOTO_CHAR -1 scplayer 60000 20.0
			ENDIF
			TASK_FLEE_CHAR -1 scplayer 20.0 3000 
		CLOSE_SEQUENCE_TASK	observer_seq3


		// find 5 nearest peds to player
		LVAR_INT closest_ped[2]
		LVAR_FLOAT closest_dist[2]
		
		temp_int = 0
		WHILE temp_int < 2
			closest_ped[temp_int] = -1
			closest_dist[temp_int] = 9999.0	
		temp_int++
		ENDWHILE

		temp_int = 0
		WHILE temp_int < 10
			IF NOT IS_CHAR_DEAD mission_ped[temp_int]
				GET_CHAR_COORDINATES mission_ped[temp_int] x y z
				GET_CHAR_COORDINATES scplayer x2 y2 z2
				GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 distance
				IF distance < closest_dist[0]
					closest_dist[1] = closest_dist[0]
					closest_dist[0] = distance

					closest_ped[1] = closest_ped[0]
					closest_ped[0] = temp_int
				ELSE
					IF distance < closest_dist[1]
						closest_dist[1] = distance
						closest_ped[1] = temp_int
					ENDIF
				ENDIF

				// if the distance is quite large and the ped is off screen - delete ped
				IF distance > 500.0
				AND NOT IS_CHAR_ON_SCREEN mission_ped[temp_int]
					DELETE_CHAR mission_ped[temp_int]
					mission_ped[temp_int] = 0
				ENDIF
					
			ENDIF
		temp_int++
		ENDWHILE


		// make nearest 2 peds fight the player
		temp_int = 0
		WHILE temp_int < 10
		
			IF NOT IS_CHAR_DEAD mission_ped[temp_int]

				IF NOT IS_CHAR_IN_ANY_CAR mission_ped[temp_int]

					// if the ped has a shotgun - make him attack all the time
					IF IS_CURRENT_CHAR_WEAPON mission_ped[temp_int] WEAPONTYPE_PISTOL
						GET_SCRIPT_TASK_STATUS mission_ped[temp_int] TASK_KILL_CHAR_ON_FOOT temp_int2
				   		IF temp_int2 = FINISHED_TASK
				   			CLEAR_CHAR_TASKS mission_ped[temp_int]
							TASK_KILL_CHAR_ON_FOOT mission_ped[temp_int] scplayer
				   		ENDIF
					ELSE

						// if it is one of the nearest 2
						IF temp_int = closest_ped[0]
						OR temp_int = closest_ped[1]
							GET_SCRIPT_TASK_STATUS mission_ped[temp_int] TASK_KILL_CHAR_ON_FOOT temp_int2
					   		IF temp_int2 = FINISHED_TASK
					   			CLEAR_CHAR_TASKS mission_ped[temp_int]
								TASK_KILL_CHAR_ON_FOOT mission_ped[temp_int] scplayer
					   		ENDIF	

							//GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS mission_ped[temp_int] 0.0 0.0 1.0 x y z
							//DRAW_CORONA x y z 0.25 CORONATYPE_MOON FLARETYPE_NONE 0 255 0

						ELSE
							// else they are observers or flee-ers
							GET_SCRIPT_TASK_STATUS mission_ped[temp_int] PERFORM_SEQUENCE_TASK temp_int2
							IF temp_int2 = FINISHED_TASK
								GENERATE_RANDOM_INT_IN_RANGE 0 9 temp_int2
								IF temp_int2 < 4
									CLEAR_CHAR_TASKS mission_ped[temp_int]
									PERFORM_SEQUENCE_TASK mission_ped[temp_int] observer_seq1
								ELSE
									IF temp_int2 < 8	
										CLEAR_CHAR_TASKS mission_ped[temp_int]
										PERFORM_SEQUENCE_TASK mission_ped[temp_int] observer_seq2
									ELSE
										CLEAR_CHAR_TASKS mission_ped[temp_int]
										PERFORM_SEQUENCE_TASK mission_ped[temp_int] observer_seq3
									ENDIF
								ENDIF
							ELSE
								IF IS_CHAR_PLAYING_ANIM mission_ped[temp_int] panic_point
								OR IS_CHAR_PLAYING_ANIM mission_ped[temp_int] panic_shout
									GET_CHAR_COORDINATES mission_ped[temp_int] x y z
									GET_CHAR_COORDINATES scplayer x2 y2 z2
									vec_x = x2 - x
									vec_y = y2 - y
									GET_HEADING_FROM_VECTOR_2D vec_x vec_y heading
									SET_CHAR_HEADING mission_ped[temp_int] heading
								ENDIF
							ENDIF
						ENDIF

					ENDIF

				ELSE
					
					// if the ped is in a car make him ram the player
				   	STORE_CAR_CHAR_IS_IN_NO_SAVE mission_ped[temp_int] car
					SET_CAR_STRAIGHT_LINE_DISTANCE car 255

					IF NOT IS_CAR_DEAD car
						IF IS_PLAYBACK_GOING_ON_FOR_CAR car
							STOP_PLAYBACK_RECORDED_CAR car
						ENDIF
					ENDIF

					GET_SCRIPT_TASK_STATUS mission_ped[temp_int] PERFORM_SEQUENCE_TASK temp_int2
					IF temp_int2 = FINISHED_TASK
						OPEN_SEQUENCE_TASK temp_seq
							TASK_CAR_MISSION -1 car -1 MISSION_RAMPLAYER_CLOSE 40.0 DRIVINGMODE_PLOUGHTHROUGH
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK mission_ped[temp_int] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq									
						IF NOT touching_dozer = 0
							touching_dozer = 0
						ENDIF
					ELSE
						IF NOT IS_CHAR_IN_ANY_CAR scplayer
							IF IS_CHAR_TOUCHING_VEHICLE scplayer car
								
								LVAR_INT touching_dozer
								//LVAR_INT raise_shovel
									
								IF touching_dozer = 0 

									

									//WRITE_DEBUG touching_car
									OPEN_SEQUENCE_TASK temp_seq
										TASK_CAR_TEMP_ACTION -1 car TEMPACT_REVERSE 2500
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK mission_ped[temp_int] temp_seq
									CLEAR_SEQUENCE_TASK temp_seq
									touching_dozer = 1
								ENDIF
							ENDIF
						ENDIF
					ENDIF

				ENDIF
			ENDIF

		temp_int++
		ENDWHILE

		CLEAR_SEQUENCE_TASK observer_seq1
		CLEAR_SEQUENCE_TASK observer_seq2
		CLEAR_SEQUENCE_TASK observer_seq3

	ENDIF



	// make dude walk up to player and ask him what he's doing
	IF alarm_raised = 0
		IF NOT IS_CHAR_DEAD worker[4]

			IF mission_ped_flag[4] = 0
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2074.2637 222.3900 34.7395 30.0 30.0 4.0 FALSE
					//IF LOCATE_CHAR_ON_FOOT_CHAR_3D worker[4] scplayer 40.0 40.0 10.0 FALSE
						CLEAR_CHAR_TASKS worker[4] 
						OPEN_SEQUENCE_TASK temp_seq
							TASK_GOTO_CHAR -1 scplayer 50000 3.0
							TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
							TASK_SCRATCH_HEAD -1
						CLOSE_SEQUENCE_TASK temp_Seq
						PERFORM_SEQUENCE_TASK worker[4] temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
						mission_ped_flag[4]++			
					//ENDIF
				ENDIF
			ENDIF

			IF mission_ped_flag[4] = 1
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2074.2637 222.3900 34.7395 30.0 30.0 4.0 FALSE
					IF NOT IS_MESSAGE_BEING_DISPLAYED 
						IF audio_line_is_active = 0
							IF LOCATE_CHAR_ON_FOOT_CHAR_3D worker[4] scplayer 3.0 3.0 2.0 FALSE
								GENERATE_RANDOM_INT_IN_RANGE 0 3 temp_int
								IF temp_int = 0					  
									$audio_string    = &GAR2_BA	
									audio_sound_file = SOUND_GAR2_BA
									START_NEW_SCRIPT audio_line worker[4] 1 0 1 0
								ENDIF							  
								IF temp_int = 1					  
									$audio_string    = &GAR2_BB	
									audio_sound_file = SOUND_GAR2_BB
									START_NEW_SCRIPT audio_line worker[4] 1 0 1 0
								ENDIF							  
								IF temp_int = 2					  
									$audio_string    = &GAR2_BC	
									audio_sound_file = SOUND_GAR2_BC
									START_NEW_SCRIPT audio_line worker[4] 1 0 1 0
								ENDIF							  
								mission_ped_flag[4]++
							ENDIF
							TIMERB = 0
						ENDIF
					ENDIF
				ELSE
					OPEN_SEQUENCE_TASK temp_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -2099.1619 171.1465 34.0869 PEDMOVE_WALK 60000
						TASK_SCRATCH_HEAD -1 
						TASK_GO_STRAIGHT_TO_COORD -1 -2090.8005 263.5923 34.7131 PEDMOVE_WALK 60000
						TASK_SCRATCH_HEAD -1 
						SET_SEQUENCE_TO_REPEAT temp_seq 1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK worker[4] temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
					mission_ped_flag[4] = 0					
				ENDIF
			ENDIF

			IF mission_ped_flag[4] = 2
				GET_SCRIPT_TASK_STATUS worker[4] PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -2099.1619 171.1465 34.0869 PEDMOVE_WALK 60000
						TASK_SCRATCH_HEAD -1 
						TASK_GO_STRAIGHT_TO_COORD -1 -2090.8005 263.5923 34.7131 PEDMOVE_WALK 60000
						TASK_SCRATCH_HEAD -1 
						SET_SEQUENCE_TO_REPEAT temp_seq 1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK worker[4] temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
					mission_ped_flag[4]++
				ENDIF
			ENDIF

		ENDIF
	ENDIF

	// stop recording of bull dozer
	IF NOT IS_CHAR_DEAD bull_driver
		IF NOT IS_CAR_DEAD bull
			IF IS_PLAYBACK_GOING_ON_FOR_CAR bull
				IF NOT IS_CHAR_IN_CAR bull_driver bull
					STOP_PLAYBACK_RECORDED_CAR bull
				ENDIF
			ENDIF	
		ENDIF
	ELSE
		IF NOT IS_CAR_DEAD bull
			IF IS_PLAYBACK_GOING_ON_FOR_CAR bull
				STOP_PLAYBACK_RECORDED_CAR bull
			ENDIF
		ENDIF
	ENDIF

	// store car player is in 
	IF IS_CHAR_IN_ANY_CAR scplayer
		IF NOT IS_CAR_DEAD pcar
			IF NOT IS_CHAR_IN_CAR scplayer pcar
				pcar = 0
				STORE_CAR_CHAR_IS_IN scplayer pcar
			ENDIF
		ELSE
			pcar = 0
			STORE_CAR_CHAR_IS_IN scplayer pcar
		ENDIF
	ENDIF

//	// check if portacabins have been damaged by players vehicle, if so make sure it was the bulldozer
//	temp_int  = 0
//	WHILE temp_int < 6
//		IF DOES_OBJECT_EXIST init_portakabin[temp_int]
//			IF NOT HAS_OBJECT_BEEN_DAMAGED init_portakabin[temp_int]
//				GET_OBJECT_HEALTH init_portakabin[temp_int] cabin_this_health[temp_int]
//
//				IF cabin_this_health[temp_int] < cabin_last_health[temp_int]
//					IF cabin_this_health[temp_int] > 0
//						
//						IF NOT IS_CAR_DEAD pcar
//							IF IS_CHAR_IN_CAR scplayer pcar
//								IF NOT IS_CAR_MODEL pcar DOZER
//									temp_int2 = cabin_last_health[temp_int] - cabin_this_health[temp_int]
//									temp_float =# temp_int2
//									temp_float *= 0.5 // we'll add half the damage caused back onto the cabins health
//									temp_int2 =# temp_float
//									cabin_this_health[temp_int] += temp_int2
//									SET_OBJECT_HEALTH init_portakabin[temp_int] cabin_this_health[temp_int]
//								ELSE
//									temp_int2 = cabin_last_health[temp_int] - cabin_this_health[temp_int]
//									temp_int2 *= -30
//									cabin_this_health[temp_int] += temp_int2
//									IF cabin_this_health[temp_int] < 0
//										cabin_this_health[temp_int] = 0
//									ENDIF
//									SET_OBJECT_HEALTH init_portakabin[temp_int] cabin_this_health[temp_int]	
//								ENDIF
//							ENDIF
//						ENDIF
//
//						IF player_is_in_crane = 1
//							SET_OBJECT_HEALTH init_portakabin[temp_int]	0
//							BREAK_OBJECT init_portakabin[temp_int] FALSE
//						ENDIF
//
//						IF cabin_this_health[temp_int] = 0
//							BREAK_OBJECT init_portakabin[temp_int] FALSE
//
//							IF NOT IS_CAR_DEAD pcar
//								IF IS_CHAR_IN_CAR scplayer pcar
//									SET_CAR_FORWARD_SPEED pcar last_pcar_speed
//									WRITE_DEBUG DESTROYED1
//								ENDIF
//							ENDIF
//						
//						ENDIF
//						
//
//					ENDIF
//				ENDIF
//
//				cabin_last_health[temp_int] = cabin_this_health[temp_int]
//			ENDIF
//		ENDIF
//	temp_int++
//	ENDWHILE

	//WRITE_DEBUG_WITH_FLOAT last_pcar_speed last_pcar_speed


	// if player hits portakabin first time travelling about a certain speed - destroy it
	temp_int = 0
	WHILE temp_int < 6


		// write debug text
		IF debug_on = 1
			IF DOES_OBJECT_EXIST init_portakabin[temp_int]

				IF HAS_OBJECT_BEEN_DAMAGED_BY_WEAPON init_portakabin[temp_int] WEAPONTYPE_EXPLOSION	
				OR HAS_OBJECT_BEEN_DAMAGED_BY_WEAPON init_portakabin[temp_int] WEAPONTYPE_ROCKETLAUNCHER
				OR HAS_OBJECT_BEEN_DAMAGED_BY_WEAPON init_portakabin[temp_int] WEAPONTYPE_GRENADE
				OR HAS_OBJECT_BEEN_DAMAGED_BY_WEAPON init_portakabin[temp_int] WEAPONTYPE_ROCKET_HS
				OR HAS_OBJECT_BEEN_DAMAGED_BY_WEAPON init_portakabin[temp_int] WEAPONTYPE_REMOTE_SATCHEL_CHARGE
					WRITE_DEBUG damaged_by_explosion
				ENDIF			

				IF HAS_OBJECT_BEEN_DAMAGED_BY_WEAPON init_portakabin[temp_int] WEAPONTYPE_ANYWEAPON	
					WRITE_DEBUG damaged_by_any
				ENDIF

			ENDIF		  
		ENDIF

		
		// check if cabin has been damaged by explosion / rocket
		IF DOES_OBJECT_EXIST init_portakabin[temp_int]
			IF HAS_OBJECT_BEEN_DAMAGED_BY_WEAPON init_portakabin[temp_int] WEAPONTYPE_EXPLOSION	
			OR HAS_OBJECT_BEEN_DAMAGED_BY_WEAPON init_portakabin[temp_int] WEAPONTYPE_ROCKETLAUNCHER
			OR HAS_OBJECT_BEEN_DAMAGED_BY_WEAPON init_portakabin[temp_int] WEAPONTYPE_GRENADE
			OR HAS_OBJECT_BEEN_DAMAGED_BY_WEAPON init_portakabin[temp_int] WEAPONTYPE_ROCKET_HS
			OR HAS_OBJECT_BEEN_DAMAGED_BY_WEAPON init_portakabin[temp_int] WEAPONTYPE_REMOTE_SATCHEL_CHARGE
				CLEAR_OBJECT_LAST_WEAPON_DAMAGE init_portakabin[temp_int]
				GET_OBJECT_HEALTH init_portakabin[temp_int] temp_int2
				temp_int2 += -500
				IF temp_int2 < 1
					SET_OBJECT_HEALTH init_portakabin[temp_int] 0
					ATTACH_MISSION_AUDIO_TO_OBJECT 3 init_portakabin[temp_int] 
					PLAY_MISSION_AUDIO 3
					BREAK_OBJECT init_portakabin[temp_int] FALSE
				ELSE
					SET_OBJECT_HEALTH init_portakabin[temp_int] temp_int2	
				ENDIF
			ENDIF
		ENDIF


		IF DOES_OBJECT_EXIST init_portakabin[temp_int]
			IF NOT HAS_OBJECT_BEEN_DAMAGED init_portakabin[temp_int]
				IF NOT IS_CAR_DEAD pcar
					IF IS_CHAR_IN_CAR scplayer pcar
						IF IS_VEHICLE_TOUCHING_OBJECT pcar init_portakabin[temp_int]
							IF last_car_inertia > 65000.0
								SET_OBJECT_HEALTH init_portakabin[temp_int] 0
								ATTACH_MISSION_AUDIO_TO_OBJECT 3 init_portakabin[temp_int] 
								PLAY_MISSION_AUDIO 3
								BREAK_OBJECT init_portakabin[temp_int] FALSE

								IF NOT IS_CAR_DEAD pcar
									IF IS_CHAR_IN_CAR scplayer pcar
										SET_CAR_FORWARD_SPEED pcar last_pcar_speed
										//WRITE_DEBUG DESTROYED1
									ENDIF
								ENDIF
								//WRITE_DEBUG_WITH_FLOAT last_car_inertia last_car_inertia
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF DOES_OBJECT_EXIST init_portakabin[temp_int]
			IF NOT HAS_OBJECT_BEEN_DAMAGED init_portakabin[temp_int]
				GET_OBJECT_HEALTH init_portakabin[temp_int] cabin_this_health[temp_int]

				IF cabin_this_health[temp_int] < cabin_last_health[temp_int]
					IF cabin_this_health[temp_int] > 0
						
						IF NOT IS_CAR_DEAD pcar
							IF IS_CHAR_IN_CAR scplayer pcar
								IF IS_VEHICLE_TOUCHING_OBJECT pcar init_portakabin[temp_int]
									IF last_pcar_speed > 5.0
										IF NOT IS_CAR_MODEL pcar DOZER
											temp_int2 = cabin_last_health[temp_int] - cabin_this_health[temp_int]
											temp_float =# temp_int2
											temp_float *= 0.5 // we'll add half the damage caused back onto the cabins health
											temp_int2 =# temp_float
											cabin_this_health[temp_int] += temp_int2
											SET_OBJECT_HEALTH init_portakabin[temp_int] cabin_this_health[temp_int]
										ELSE
											temp_int2 = cabin_last_health[temp_int] - cabin_this_health[temp_int]
											temp_int2 *= -30
											cabin_this_health[temp_int] += temp_int2
											IF cabin_this_health[temp_int] < 0
												cabin_this_health[temp_int] = 0
											ENDIF
											IF hit_portacabin_first_time = 0
												IF cabin_this_health[temp_int] > 10
													IF NOT IS_MESSAGE_BEING_DISPLAYED 
														PRINT_NOW GAR2_38 5000 1 // ram it again
														hit_portacabin_first_time = 1
													ENDIF
												ENDIF
											ENDIF	
											SET_OBJECT_HEALTH init_portakabin[temp_int] cabin_this_health[temp_int]	
										ENDIF
									ELSE
										SET_OBJECT_HEALTH init_portakabin[temp_int] cabin_last_health[temp_int]
									ENDIF
								ENDIF
							ENDIF
						ENDIF

						IF player_is_in_crane = 1
							SET_OBJECT_HEALTH init_portakabin[temp_int]	0
							ATTACH_MISSION_AUDIO_TO_OBJECT 3 init_portakabin[temp_int] 
							PLAY_MISSION_AUDIO 3
							BREAK_OBJECT init_portakabin[temp_int] FALSE
						ENDIF

						IF cabin_this_health[temp_int] = 0
							ATTACH_MISSION_AUDIO_TO_OBJECT 3 init_portakabin[temp_int] 
							PLAY_MISSION_AUDIO 3
							BREAK_OBJECT init_portakabin[temp_int] FALSE

							IF NOT IS_CAR_DEAD pcar
								IF IS_CHAR_IN_CAR scplayer pcar
									SET_CAR_FORWARD_SPEED pcar last_pcar_speed
									//WRITE_DEBUG DESTROYED1
								ENDIF
							ENDIF
						
						ENDIF
						

					ENDIF
				ENDIF

				cabin_last_health[temp_int] = cabin_this_health[temp_int]
			ENDIF
		ENDIF


		IF DOES_OBJECT_EXIST init_portakabin[temp_int]
			IF NOT HAS_OBJECT_BEEN_DAMAGED init_portakabin[temp_int]
				IF NOT IS_CAR_DEAD pcar
					IF IS_CHAR_IN_CAR scplayer pcar
						IF IS_CAR_MODEL pcar DOZER
							IF IS_VEHICLE_TOUCHING_OBJECT pcar init_portakabin[temp_int]
								GET_OBJECT_HEALTH init_portakabin[temp_int] temp_int2
								IF temp_int2 < 10
									SET_OBJECT_HEALTH init_portakabin[temp_int] 0
									ATTACH_MISSION_AUDIO_TO_OBJECT 3 init_portakabin[temp_int] 
									PLAY_MISSION_AUDIO 3
									BREAK_OBJECT init_portakabin[temp_int] FALSE
									IF NOT IS_CAR_DEAD pcar
										IF IS_CHAR_IN_CAR scplayer pcar
											SET_CAR_FORWARD_SPEED pcar last_pcar_speed
											//WRITE_DEBUG DESTROYED1
										ENDIF
									ENDIF
									//WRITE_DEBUG DESTROYED2
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	temp_int++
	ENDWHILE


	// spawn more baddies if portacabin gets damaged
	temp_int = 0
	WHILE temp_int < 6
		
		IF available_mission_peds > 0
			IF DOES_OBJECT_EXIST init_portakabin[temp_int]
				GET_OBJECT_HEALTH init_portakabin[temp_int] temp_int2
				IF temp_int2 < 995
					IF temp_int2 > 500
						IF cabin_peds_generated[temp_int] = 0
							GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS init_portakabin[temp_int] 3.252 -2.1084 0.0 x y z
							GOSUB is_mission_ped_in_area
							IF is_mission_ped_in_area_result = 0
								GET_GROUND_Z_FOR_3D_COORD x y z z
								GET_OBJECT_HEADING init_portakabin[temp_int] heading
								heading += 180.0
								CREATE_CHAR PEDTYPE_MISSION1 WMYCON	x y z spawned_ped
								SET_CHAR_HEADING spawned_ped heading
								SET_CHAR_HEALTH spawned_ped 50
								cabin_peds_generated[temp_int]++
								temp_int = 6
							ENDIF
						ENDIF
					ELSE
						
						IF NOT HAS_OBJECT_BEEN_DAMAGED init_portakabin[temp_int]
//							IF cabin_peds_generated[temp_int] < 2
//								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS init_portakabin[temp_int] 3.252 -2.1084 0.0 x y z
//								GOSUB is_mission_ped_in_area
//								IF is_mission_ped_in_area_result = 0
//									GET_OBJECT_HEADING init_portakabin[temp_int] heading
//									GET_GROUND_Z_FOR_3D_COORD x y z z
//									heading += 180.0
//									CREATE_CHAR PEDTYPE_MISSION1 WMYCON	x y z spawned_ped
//									SET_CHAR_HEADING spawned_ped heading
//									SET_CHAR_HEALTH spawned_ped 50
//									cabin_peds_generated[temp_int]++
//									temp_int = 6
//								ENDIF
//							ENDIF
						ELSE
							IF NOT cabin_peds_generated[temp_int] = 99
								GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS init_portakabin[temp_int] 0.0 0.0 0.0 x y z
								GET_OBJECT_HEADING init_portakabin[temp_int] heading
								GET_GROUND_Z_FOR_3D_COORD x y z z
								heading += 180.0
								CREATE_CHAR PEDTYPE_MISSION1 WMYCON	x y z spawned_ped
								SET_CHAR_HEADING spawned_ped heading
								SET_CHAR_HEALTH spawned_ped 50
								cabin_peds_generated[temp_int] = 99
								temp_int = 6
								
								// dive
								GET_CHAR_COORDINATES scplayer x2 y2 z2 
								vec_x = x - x2
								vec_y = y - y2
								GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int2
								IF NOT temp_int2 = 0
									vec_y *= -1.0
								ENDIF
								vec2_x = vec_y
								vec2_y = vec_x * -1.0
								TASK_DIVE_AND_GET_UP spawned_ped vec2_x vec2_y 1000
							ENDIF
						ENDIF
				
					ENDIF
				ENDIF	
			ENDIF
		ENDIF		 

		IF DOES_CHAR_EXIST spawned_ped	
			IF NOT IS_CHAR_DEAD spawned_ped
				// give weapon depending on how many portacabins have been destroyed
				IF cabins_destroyed > 0
					IF cabins_destroyed < 2
						GIVE_WEAPON_TO_CHAR spawned_ped WEAPONTYPE_SHOVEL 99999
					ELSE
						GIVE_WEAPON_TO_CHAR spawned_ped WEAPONTYPE_PISTOL 99999
					ENDIF
				ENDIF
				SET_CHAR_SHOOT_RATE spawned_ped 30
				SET_CHAR_ACCURACY spawned_ped 30
				SET_CHAR_DECISION_MAKER spawned_ped worker_dm
				ped_to_add = spawned_ped
				GOSUB add_ped_to_mission_peds
				baddie_spawn_timer = 0
			ENDIF
		ENDIF

		spawned_ped = 0

	temp_int++
	ENDWHILE



	// if there are no construction worker peds about then spawn a couple
	IF alarm_raised = 1
		IF available_mission_peds <= 4
			baddie_spawn_timer = 0
		ENDIF
		IF baddie_spawn_timer > 20000
			IF available_mission_peds > 4
				
				temp_float2 = 200.0
				temp_int2 = -1

				// find closest available portacabin
				temp_int = 0
				WHILE temp_int < 6
					IF DOES_OBJECT_EXIST init_portakabin[temp_int] 
						IF NOT HAS_OBJECT_BEEN_DAMAGED init_portakabin[temp_int]
							GET_OBJECT_COORDINATES init_portakabin[temp_int] x y z
							GET_CHAR_COORDINATES scplayer x2 y2 z2
							GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float
							IF temp_float < temp_float2
								temp_float2 = temp_float
								temp_int2 = temp_int
							ENDIF
						ENDIF
					ENDIF
					temp_int++
				ENDWHILE

				// if we found a spawn point then create the ped
				IF NOT temp_int2 = -1
					GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS init_portakabin[temp_int2] 3.252 -2.1084 0.0 x y z
					GOSUB is_mission_ped_in_area
					IF is_mission_ped_in_area_result = 0
						GET_GROUND_Z_FOR_3D_COORD x y z z
						GET_OBJECT_HEADING init_portakabin[temp_int2] heading
						heading += 180.0
						CREATE_CHAR PEDTYPE_MISSION1 WMYCON	x y z spawned_ped
						SET_CHAR_HEADING spawned_ped heading
						SET_CHAR_HEALTH spawned_ped 50
						IF cabins_destroyed > 2
							IF cabins_destroyed < 5
								GIVE_WEAPON_TO_CHAR spawned_ped WEAPONTYPE_SHOVEL 99999
							ELSE
								GIVE_WEAPON_TO_CHAR spawned_ped WEAPONTYPE_PISTOL 99999
							ENDIF
						ENDIF
						SET_CHAR_SHOOT_RATE spawned_ped 30
						SET_CHAR_ACCURACY spawned_ped 30
						SET_CHAR_DECISION_MAKER spawned_ped worker_dm
						ped_to_add = spawned_ped
						spawned_ped = 0
						baddie_spawn_timer = 0	
						GOSUB add_ped_to_mission_peds
					ENDIF
				ENDIF

			ENDIF
		ENDIF
	ENDIF


	// clear mission ped array if the ped is dead
	temp_int = 0
	WHILE temp_int < 10
		IF DOES_CHAR_EXIST mission_ped[temp_int]
			IF IS_CHAR_DEAD mission_ped[temp_int]
				MARK_CHAR_AS_NO_LONGER_NEEDED mission_ped[temp_int]
				mission_ped[temp_int] = 0
				mission_ped_flag[temp_int] = 0
			ENDIF
		ENDIF 
	temp_int++
	ENDWHILE

	GOSUB count_available_peds

	IF NOT IS_CAR_DEAD pcar
		GET_CAR_SPEED pcar last_pcar_speed
		GET_CAR_MASS pcar temp_float
		last_car_inertia = last_pcar_speed * temp_float
		//WRITE_DEBUG_WITH_FLOAT last_car_inertia last_car_inertia
	ENDIF

	// remove blips on dead cement mixers
	IF DOES_BLIP_EXIST cement_truck1_blip
		IF IS_CAR_DEAD cement_truck
			REMOVE_BLIP cement_truck1_blip
		ENDIF
	ENDIF
	IF DOES_BLIP_EXIST cement_truck2_blip
		IF IS_CAR_DEAD cement_truck2
			REMOVE_BLIP cement_truck2_blip
		ENDIF
	ENDIF

	
RETURN


// *************************************************************************************************************
//										MISSION SPECIFIC GOSUBS  
// *************************************************************************************************************
	LVAR_INT ped_to_add
	LVAR_INT temp_int_g1
	add_ped_to_mission_peds:
		temp_int_g1 = 0
		WHILE temp_int_g1 < 4
			IF mission_ped[temp_int_g1]	= 0
				mission_ped[temp_int_g1] = ped_to_add
				mission_ped_flag[temp_int_g1] = 0
				temp_int_g1 = 6
			ENDIF
		temp_int_g1++
		ENDWHILE
		GOSUB count_available_peds
		//GOSUB debug_print_mission_peds
	RETURN


	LVAR_INT ap_temp_int
	count_available_peds:
		available_mission_peds = 4
		ap_temp_int = 0
		WHILE ap_temp_int < 4
			IF DOES_CHAR_EXIST mission_ped[ap_temp_int]
				available_mission_peds--
			ENDIF
		ap_temp_int++
		ENDWHILE
	RETURN

	LVAR_INT debug_temp_int
	debug_print_mission_peds:

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "available peds"
		SAVE_NEWLINE_TO_DEBUG_FILE
		
		debug_temp_int = 0
		WHILE debug_temp_int < 10
			SAVE_NEWLINE_TO_DEBUG_FILE
			SAVE_STRING_TO_DEBUG_FILE "mission ped "
			SAVE_INT_TO_DEBUG_FILE debug_temp_int
			SAVE_STRING_TO_DEBUG_FILE " = "
			SAVE_INT_TO_DEBUG_FILE mission_ped[debug_temp_int]
		debug_temp_int++
		ENDWHILE

		SAVE_NEWLINE_TO_DEBUG_FILE
		SAVE_STRING_TO_DEBUG_FILE "available_mission_peds = "
		SAVE_INT_TO_DEBUG_FILE available_mission_peds
		SAVE_NEWLINE_TO_DEBUG_FILE
		 

	RETURN

	LVAR_INT impia_int
	LVAR_INT is_mission_ped_in_area_result
	is_mission_ped_in_area:
		is_mission_ped_in_area_result = 0
		impia_int = 0
		WHILE impia_int < 10
			IF NOT IS_CHAR_DEAD mission_ped[impia_int]
				IF LOCATE_CHAR_ON_FOOT_3D mission_ped[impia_int] x y z 1.5 1.5 3.0 FALSE
					is_mission_ped_in_area_result = 1
				ENDIF
			ENDIF
		impia_int++
		ENDWHILE
	RETURN


// =================================================================
// GOSUB: Next_Stage (sets flags and timers for next mission stage)
// =================================================================
	next_stage:
		m_stage++
		m_goals        = 0
		dialogue_flag  = 0
		help_flag	   = 0
		dialogue_timer = 0
		help_timer	   = 0
		TIMERA 		   = 0
		TIMERB		   = 0
	RETURN					

// *************************************************************************************************************
//										END OF LEVEL GOSUBS - fail, pass, cleanup  
// *************************************************************************************************************

// FAIL
mission_failed_DECON:

	IF IS_PLAYER_PLAYING player1
		IF LOCATE_CHAR_ANY_MEANS_3D	scplayer -2049.1990 250.3882 32.9084 2.4540 5.0420 2.3 FALSE
			SET_CHAR_COORDINATES scplayer -2045.1887 241.7544 34.9462 
			SET_CHAR_HEADING scplayer 186.3532			
		ENDIF
	ENDIF

	SWAP_NEAREST_BUILDING_MODEL -2049.1714 250.3193 34.4770 20.0 Hubhole2_SFSe Hubhole1_SFSe

//	CREATE_OBJECT_NO_OFFSET Hubhole1_SFSe -2049.1714 250.3193 34.4770 san_fran_hub_hole 
//	DONT_REMOVE_OBJECT san_fran_hub_hole

	PRINT_BIG M_FAIL 5000 1

RETURN

// PASS
mission_passed_DECON:

	IF DOES_OBJECT_EXIST cement_hole
		DELETE_OBJECT cement_hole
	ENDIF
//	CREATE_OBJECT_NO_OFFSET Hubhole3_SFSe -2049.1714 250.3193 34.4770	cement_hole
//	DONT_REMOVE_OBJECT cement_hole


//	IF DOES_OBJECT_EXIST new_hole_in_ground
//		DELETE_OBJECT new_hole_in_ground
//	ENDIF
//	CREATE_OBJECT_NO_OFFSET Hubhole2_SFSe -2049.1714 250.3193 33.0784 new_hole_in_ground
//	DONT_REMOVE_OBJECT new_hole_in_ground 

	//SWAP_NEAREST_BUILDING_MODEL -2049.1714 250.3193 34.4770 20.0 Hubhole2_SFSe Hubhole1_SFSe
	SWAP_NEAREST_BUILDING_MODEL -2049.1714 250.3193 34.4770 20.0 Hubhole2_SFSe Hubhole4_SFSe

	REGISTER_MISSION_PASSED GAR_2
//	PRINT_WITH_NUMBER_BIG ( M_PASS ) 10000 5000 1 //"Mission Passed!"
//	ADD_SCORE player1 10000
	
	START_NEW_SCRIPT synd_mission_loop
	REMOVE_BLIP garage_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ garage_blip_icon garage_contact_blip

	PLAYER_MADE_PROGRESS 1

	SET_INT_STAT PASSED_GARAGE2 1

	PRINT_BIG M_PASSD 5000 1
	PLAY_MISSION_PASSED_TUNE 1
	CLEAR_WANTED_LEVEL player1
	flag_garage_mission_counter ++

RETURN

// CLEANUP
mission_cleanup_DECON:
	
	START_NEW_SCRIPT cleanup_audio_lines
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	CLEAR_MISSION_AUDIO 3

	do_not_update_camera_crane1 = 0

	MARK_MODEL_AS_NO_LONGER_NEEDED PORTALOO
	MARK_MODEL_AS_NO_LONGER_NEEDED PORTAKABIN 
	MARK_MODEL_AS_NO_LONGER_NEEDED BARREL4
	MARK_MODEL_AS_NO_LONGER_NEEDED DOZER
	MARK_MODEL_AS_NO_LONGER_NEEDED CEMENT
	MARK_MODEL_AS_NO_LONGER_NEEDED DUMPER
	MARK_MODEL_AS_NO_LONGER_NEEDED BOBCAT
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYAP
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYCON
	MARK_MODEL_AS_NO_LONGER_NEEDED SHOVEL 
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
	//MARK_MODEL_AS_NO_LONGER_NEEDED CEMENT_IN_HOLE
	//REMOVE_ANIMATION ON_LOOKERS

	REMOVE_BLIP cabin_blip[0]
	REMOVE_BLIP cabin_blip[1]
	REMOVE_BLIP cabin_blip[2]
	REMOVE_BLIP cabin_blip[3]
	REMOVE_BLIP cabin_blip[4]
	REMOVE_BLIP cabin_blip[5]
	REMOVE_BLIP location_blip
	REMOVE_BLIP object_blip
	REMOVE_BLIP cement_truck1_blip
	REMOVE_BLIP cement_truck2_blip

	// from load scene 
//	MARK_MODEL_AS_NO_LONGER_NEEDED SHOPPIE4_SFS
//	MARK_MODEL_AS_NO_LONGER_NEEDED SCAFFOLDING_SFS
//	MARK_MODEL_AS_NO_LONGER_NEEDED MALL_01_SFS
//	MARK_MODEL_AS_NO_LONGER_NEEDED SHOPPIE6_SFS
//	MARK_MODEL_AS_NO_LONGER_NEEDED SHOPPIE6_SFS01
//	MARK_MODEL_AS_NO_LONGER_NEEDED MISSION_01_SFS	
//	MARK_MODEL_AS_NO_LONGER_NEEDED MISSION_02_SFS
	CLEAR_ONSCREEN_TIMER portacabin_timer


	// === RESTORE ENVIRONMENT SETTINGS ===
	//SWITCH_CAR_GENERATOR gen_car11 101 
	SET_WANTED_MULTIPLIER 1.0

	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_CAR_DENSITY_MULTIPLIER 1.0

	GET_GAME_TIMER timer_mobile_start

	flag_player_on_mission = 0
	MISSION_HAS_FINISHED
RETURN
	 

}