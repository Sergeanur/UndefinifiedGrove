MISSION_START	
					  
// *****************************************************************************************
// ************************************* synd4 ********************************************* 
// *****************************************************************************************

SCRIPT_NAME synd4
// Mission start stuff
GOSUB mission_start_synd4

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_synd4_failed
ENDIF		
												   
GOSUB mission_cleanup_synd4

MISSION_END

VAR_FLOAT off1_X off_X off1_Y off_Y	syn4_offsetX syn4_offsetY

VAR_INT syn4_rnd s4_has_phone s4_j_health s4_limo_hlth

{

// Variables for mission

LVAR_INT s4_guards[5] 

LVAR_INT syn4_jizzy v

LVAR_INT syn4_mission_stat syn4_seq_look 

//blips
LVAR_INT syn4_jizzy_blip syn4_girl

LVAR_INT syn4_sniper_blip syn4_ready s4_decision 

LVAR_INT jizzy_phone_cut s4_body_guard0 s4_body_guard1 s4_barrier 

LVAR_INT s4_group s4_decision_group the_phone_blip 

LVAR_INT s4_empty_decision s4_phone_is_spawned s4_seq_run

LVAR_INT s4_seq_backup

LVAR_INT s4_start_blip

LVAR_INT s4_door_blip syn4_skylight

LVAR_INT s4_limo[5]
								   
LVAR_INT s4_seq_corridor 

LVAR_INT s4_seq_shoot_at_player 

LVAR_INT s4_blip_for_the_phone s4_txt_skylight 

LVAR_INT seq_walk seq_vender s4_seq_club

LVAR_INT s4_jizzy_is_outside s4_seq_drive s4_seq_flee s4_jizzy_inside_limo

LVAR_INT s4_the_phone s4_sitdown_seq s4_sitdown_seq_1

LVAR_INT s4_seq_flee_1 s4_limo_driver

LVAR_INT s4_lost s4_ply_car s4_jizzy_indoors

LVAR_INT s4_gotten_ai s4_jizzy_buddy s4_decision_buddy 

LVAR_INT s4_damaged_goods s4_cars_health

LVAR_INT s4_jizzy_fleeing

LVAR_INT s4_TIMERC

LVAR_INT s4_jumped

LVAR_INT s4_leave_car

LVAR_INT s4_txt_display

LVAR_INT s4_audio

LVAR_INT s4_playing

LVAR_INT s4_guy_quit_a s4_guy_quit_b

LVAR_INT syn4_rnd_txt

LVAR_INT s4_bouncers

LVAR_INT s4_ambience[10] s4_seq_bar_walk[2]

LVAR_INT s4_stopping syn4_in_pdomes

LVAR_INT s4_driveby_once

LVAR_INT s4_guys_are_attacking

LVAR_TEXT_LABEL s4_print

VAR_INT s4_event


// ****************************************Mission Start*********************************************

mission_start_synd4: 

// *****************************************************************************************
// *																					   *
// *                                     Mission Start 				   					   *
// *																					   *
// *****************************************************************************************

off1_X = 0.0 
off_X = 0.0 
off1_Y = 0.0 
off_Y = 0.0
syn4_offsetX = 0.0
syn4_offsetY = 0.0

syn4_rnd= 0
s4_has_phone = 0
s4_j_health = 0
s4_limo_hlth = 0
s4_guys_are_attacking = 0

s4_playing = 2

LOAD_MISSION_TEXT SYN4

SWITCH_ENTRY_EXIT PDOMES TRUE

SWITCH_ENTRY_EXIT PDOMES2 FALSE

// Cutscene
SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

SET_AREA_VISIBLE 1										

SET_CHAR_AREA_VISIBLE scplayer 1
LOAD_SCENE -2031.0 149.0 29.0

LOAD_CUTSCENE SYND_4a
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
START_CUTSCENE

SET_FADING_COLOUR 0 0 0
DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE
   
SET_FADING_COLOUR 0 0 0
DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE  
  
CLEAR_CUTSCENE

SET_CHAR_AREA_VISIBLE scplayer 0

SET_AREA_VISIBLE 0

// *****************************************************************************************

IF NOT IS_CHAR_DEAD scplayer
 
 	SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer -2031.1965 161.1839 27.8516 
	
 	SET_CHAR_HEADING scplayer 268.0468 
 
  	SET_CAMERA_BEHIND_PLAYER

ENDIF

// *****************************************************************************************

SET_FADING_COLOUR 0 0 0

// *****************************************************************************************
// *																					   *
// *                                	  Requests   				   					   *
// *																					   *
// *****************************************************************************************

REQUEST_MODEL silenced
REQUEST_MODEL BMYBOUN
REQUEST_MODEL WMYBOUN
REQUEST_MODEL stretch
REQUEST_MODEL micro_uzi
REQUEST_MODEL broadway
REQUEST_MODEL stretch
REQUEST_MODEL WMYSGRD 
																			 
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_MISSION2 PEDTYPE_PLAYER1

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION2 PEDTYPE_MISSION2

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY s4_empty_decision

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH s4_decision

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH s4_decision_buddy

WHILE NOT HAS_MODEL_LOADED silenced
OR NOT HAS_MODEL_LOADED BMYBOUN
OR NOT HAS_MODEL_LOADED WMYBOUN
OR NOT HAS_MODEL_LOADED stretch
OR NOT HAS_MODEL_LOADED micro_uzi

	WAIT 0

ENDWHILE

WHILE NOT HAS_MODEL_LOADED stretch
OR NOT HAS_MODEL_LOADED broadway
OR NOT HAS_MODEL_LOADED WMYSGRD

	WAIT 0

ENDWHILE

LOAD_SCENE_IN_DIRECTION	-2031.1965 161.1839 27.8359 268.0468 

CLEAR_AREA -2624.5486 1412.1306 6.1094 10.0 TRUE

CREATE_GROUP DEFAULT_TASK_ALLOCATOR_STAND_STILL s4_group

LOAD_GROUP_DECISION_MAKER DM_GROUP_MISSION_NORM s4_decision_group 

SET_GROUP_DECISION_MAKER s4_group s4_decision_group 

// *****************************************************************************************

IF NOT IS_CHAR_DEAD scplayer

	OPEN_SEQUENCE_TASK s4_seq_backup

		FLUSH_ROUTE

		EXTEND_ROUTE -2643.9934 1405.8949 905.2734
			
		TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE
		
		TASK_KILL_CHAR_ON_FOOT -1 scplayer

		TASK_STAY_IN_SAME_PLACE -1 TRUE

		FLUSH_ROUTE															 

	CLOSE_SEQUENCE_TASK s4_seq_backup

ENDIF

LVAR_INT s4_seq_table[4]

// *********************************************************************************************
IF NOT IS_CHAR_DEAD scplayer
OPEN_SEQUENCE_TASK s4_seq_table[0]

	TASK_GO_TO_COORD_ANY_MEANS -1 -2664.2856 1420.9042 905.2812 PEDMOVE_WALK -1
	TASK_KILL_CHAR_ON_FOOT -1 scplayer

CLOSE_SEQUENCE_TASK s4_seq_table[0]

OPEN_SEQUENCE_TASK s4_seq_table[1]
		
	TASK_GO_TO_COORD_ANY_MEANS -1 -2662.2415 1418.2222 905.2770 PEDMOVE_WALK -1
	TASK_KILL_CHAR_ON_FOOT -1 scplayer

CLOSE_SEQUENCE_TASK s4_seq_table[1]

OPEN_SEQUENCE_TASK s4_seq_table[2]

	TASK_GO_TO_COORD_ANY_MEANS -1 -2671.8579 1420.6029 905.2812 PEDMOVE_WALK -1
	TASK_KILL_CHAR_ON_FOOT -1 scplayer

CLOSE_SEQUENCE_TASK s4_seq_table[2]

OPEN_SEQUENCE_TASK s4_seq_table[3]

	TASK_GO_TO_COORD_ANY_MEANS -1 -2667.2207 1421.7740 905.2812 PEDMOVE_WALK -1
	TASK_KILL_CHAR_ON_FOOT -1 scplayer

CLOSE_SEQUENCE_TASK s4_seq_table[3]

ENDIF
// *********************************************************************************************

OPEN_SEQUENCE_TASK seq_vender

	TASK_GO_TO_COORD_ANY_MEANS -1 -2674.1714 1394.5531 917.3582  PEDMOVE_WALK -1
	TASK_ACHIEVE_HEADING -1 94.4524 
	SET_SEQUENCE_TO_REPEAT seq_vender 1													 

CLOSE_SEQUENCE_TASK seq_vender
 
OPEN_SEQUENCE_TASK s4_seq_flee_1

	FLUSH_ROUTE	
		
	EXTEND_ROUTE -2666.1421 1420.5762 905.2770  
	EXTEND_ROUTE -2654.3296 1420.0924 905.2812 
	TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_SPRINT FOLLOW_ROUTE_ONCE

	FLUSH_ROUTE

CLOSE_SEQUENCE_TASK	s4_seq_flee_1

OPEN_SEQUENCE_TASK s4_seq_club

	FLUSH_ROUTE

	EXTEND_ROUTE -2663.8975 1420.5088 905.2812 
	EXTEND_ROUTE -2655.5420 1418.9049 905.2812 
	EXTEND_ROUTE -2648.7446 1416.9875 905.2812  
	EXTEND_ROUTE -2645.2756 1410.5466 905.2812    

	TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

	FLUSH_ROUTE

CLOSE_SEQUENCE_TASK	s4_seq_club

OPEN_SEQUENCE_TASK s4_seq_corridor

	FLUSH_ROUTE

	EXTEND_ROUTE -2627.8308 1404.9587 905.4609
	 
	EXTEND_ROUTE -2627.5139 1406.6074 905.4609 

	EXTEND_ROUTE -2635.9236 1408.3064 905.4609	
		
	TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE
	
	FLUSH_ROUTE

	TASK_PAUSE -1 4000

	FLUSH_ROUTE

	EXTEND_ROUTE -2629.4507 1406.7727 905.4609

	EXTEND_ROUTE -2626.7146 1403.4100 905.4609

	EXTEND_ROUTE -2629.2236 1402.5852 905.4598 

	EXTEND_ROUTE -2639.5127 1399.5688 911.4133 
	
	TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE
	
	FLUSH_ROUTE

CLOSE_SEQUENCE_TASK	s4_seq_corridor

OPEN_SEQUENCE_TASK s4_seq_run

	FLUSH_ROUTE

	EXTEND_ROUTE -2663.0244 1430.2712 911.3984  
	EXTEND_ROUTE -2677.8413 1429.6632 911.3921  
	EXTEND_ROUTE -2688.7573 1426.8804 905.4531  
	EXTEND_ROUTE -2686.4932 1422.2070 905.4531  

	EXTEND_ROUTE -2677.7693 1421.9478 905.4531 
	EXTEND_ROUTE -2664.3181 1423.6176 905.2734  
	EXTEND_ROUTE -2651.4082 1423.6007 905.2734  
	EXTEND_ROUTE -2642.1011 1416.0978 905.2812 
	
	TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_SPRINT FOLLOW_ROUTE_ONCE
	
	FLUSH_ROUTE

	EXTEND_ROUTE -2633.1062 1415.7209 905.4531 
	
	TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_SPRINT FOLLOW_ROUTE_ONCE
	
	FLUSH_ROUTE

CLOSE_SEQUENCE_TASK s4_seq_run

OPEN_SEQUENCE_TASK syn4_seq_look
 
	TASK_LOOK_ABOUT -1 4000

	TASK_PAUSE -1 1000

	TASK_SCRATCH_HEAD -1 

	SET_SEQUENCE_TO_REPEAT syn4_seq_look 1	
	
CLOSE_SEQUENCE_TASK syn4_seq_look

OPEN_SEQUENCE_TASK s4_seq_shoot_at_player

	TASK_STAY_IN_SAME_PLACE -1 TRUE

	TASK_SHOOT_AT_CHAR -1 scplayer -1

	SET_SEQUENCE_TO_REPEAT s4_seq_shoot_at_player 1

CLOSE_SEQUENCE_TASK s4_seq_shoot_at_player

// ** Limo's outside   *********************************************************************

CREATE_CAR stretch -2634.0613 1395.4983 6.1186 s4_limo[0]

SET_CAR_HEADING s4_limo[0] 3.5285 

CHANGE_CAR_COLOUR s4_limo[0] 0 0 

SET_CAN_BURST_CAR_TYRES s4_limo[0] FALSE

SET_CAR_HEALTH s4_limo[0] 2000

CREATE_CAR stretch -2613.4802 1416.5684 6.1432 s4_limo[1]

SET_CAR_HEADING s4_limo[1] 272.3133 

CHANGE_CAR_COLOUR s4_limo[1] 0 0 

SET_CAN_BURST_CAR_TYRES s4_limo[1] FALSE

SET_CAR_HEALTH s4_limo[1] 2000

CUSTOM_PLATE_FOR_NEXT_CAR broadway &HO_2_HO_

CREATE_CAR broadway -2611.4714 1409.0361 6.1335 s4_limo[2]

SET_CAR_HEADING s4_limo[2] 274.4727

CHANGE_CAR_COLOUR s4_limo[2] 4 4   

SET_CAR_HEALTH s4_limo[2] 1000

LVAR_INT s4_bouncer_a s4_bouncer_b

OPEN_SEQUENCE_TASK s4_bouncer_a

	TASK_GO_TO_COORD_ANY_MEANS -1 -2622.9812 1413.1068 6.1137 PEDMOVE_WALK -1

	TASK_ACHIEVE_HEADING -1 196.0190
	 
	SET_SEQUENCE_TO_REPEAT s4_bouncer_a 1		

CLOSE_SEQUENCE_TASK s4_bouncer_a

OPEN_SEQUENCE_TASK s4_bouncer_b

	TASK_GO_TO_COORD_ANY_MEANS -1 -2626.4087 1411.9604 6.1094 PEDMOVE_WALK -1

	TASK_ACHIEVE_HEADING -1 190.6977
	 
	SET_SEQUENCE_TO_REPEAT s4_bouncer_b 1		

CLOSE_SEQUENCE_TASK s4_bouncer_b
 
// ** Bouncers ar Door *********************************************************************

CREATE_CHAR PEDTYPE_MISSION2 BMYBOUN -2622.9812 1413.1068 6.1137 s4_body_guard0

SET_CHAR_HEADING s4_body_guard0 196.0190  

CREATE_CHAR PEDTYPE_MISSION2 WMYBOUN -2626.4087 1411.9604 6.1094 s4_body_guard1

SET_CHAR_HEADING s4_body_guard1 190.6977

GIVE_WEAPON_TO_CHAR s4_body_guard0 WEAPONTYPE_MICRO_UZI 30000

SET_CURRENT_CHAR_WEAPON s4_body_guard0 WEAPONTYPE_MICRO_UZI 

SET_CHAR_ACCURACY s4_body_guard0 90

GIVE_WEAPON_TO_CHAR s4_body_guard1 WEAPONTYPE_MICRO_UZI 30000

SET_CURRENT_CHAR_WEAPON s4_body_guard1 WEAPONTYPE_MICRO_UZI 

SET_CHAR_ACCURACY s4_body_guard1 90

SET_CHAR_DECISION_MAKER s4_body_guard0 s4_decision

SET_CHAR_DECISION_MAKER s4_body_guard1 s4_decision

PERFORM_SEQUENCE_TASK s4_body_guard0 s4_bouncer_a

PERFORM_SEQUENCE_TASK s4_body_guard1 s4_bouncer_b

ADD_BLIP_FOR_COORD -2623.9370 1410.7900 6.1152 s4_start_blip

ADD_BLIP_FOR_COORD -2636.3848 1402.4854 905.4609 s4_door_blip

SET_BLIP_ENTRY_EXIT s4_door_blip -2661.7397 1423.4828 100.0

CHANGE_BLIP_DISPLAY s4_door_blip NEITHER

// ** Peds at bar **************************************************************************

IF NOT IS_CHAR_DEAD scplayer

  	GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_PISTOL_SILENCED 200

  	SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PISTOL_SILENCED 
	 
ENDIF

TIMERA = 0

// *****************************************************************************************

SET_FADING_COLOUR 0 0 0				    

SET_FADING_COLOUR 0 0 0
DO_FADE 1000 FADE_IN

// *****************************************************************************************
		
PRINT_NOW ( SYN4_20 ) 9000 1 // ~s~Go pay Jizzy a visit at his ~y~private club~s~!

// *****************************************************************************************


WHILE NOT IS_CHAR_DEAD scplayer

	WAIT 0

	GOSUB syn4_keys

	GOSUB s4_play_sample

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S

    	GOTO mission_synd4_passed	  

	ENDIF

	IF s4_guys_are_attacking = 0

		IF NOT IS_CHAR_DEAD s4_body_guard0
			
			IF IS_PLAYER_TARGETTING_CHAR player1 s4_body_guard0

				IF NOT IS_CHAR_DEAD s4_body_guard0

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

					TASK_KILL_CHAR_ON_FOOT s4_body_guard0 scplayer

				ENDIF

				IF NOT IS_CHAR_DEAD s4_body_guard1

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

					TASK_KILL_CHAR_ON_FOOT s4_body_guard1 scplayer

				ENDIF

				s4_guys_are_attacking = 1

			ENDIF
			
		ENDIF

		IF NOT IS_CHAR_DEAD s4_body_guard1
			
			IF IS_PLAYER_TARGETTING_CHAR player1 s4_body_guard1

				IF NOT IS_CHAR_DEAD s4_body_guard0

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

					TASK_KILL_CHAR_ON_FOOT s4_body_guard0 scplayer

				ENDIF

				IF NOT IS_CHAR_DEAD s4_body_guard1

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

					TASK_KILL_CHAR_ON_FOOT s4_body_guard1 scplayer

				ENDIF
				
				s4_guys_are_attacking = 1

			ENDIF

		ENDIF

	ENDIF

	IF NOT IS_CHAR_DEAD scplayer 
	  	IF LOCATE_CHAR_ON_FOOT_3D scplayer -2623.9370 1410.7900 6.1152 1.2 1.2 1.2 TRUE 

			IF NOT IS_CHAR_DEAD scplayer

				SET_PLAYER_CONTROL player1 OFF

			ENDIF

			APPLY_BRAKES_TO_PLAYERS_CAR player1 ON

			SET_CHAR_HEADING scplayer 0.0000

			REMOVE_BLIP s4_start_blip
					
			GOSUB syn4_set_camera

			IF NOT IS_CHAR_DEAD s4_body_guard0
			AND NOT IS_CHAR_DEAD s4_body_guard1

				IF NOT HAS_CHAR_BEEN_DAMAGED_BY_CHAR s4_body_guard0 scplayer
				AND NOT HAS_CHAR_BEEN_DAMAGED_BY_CHAR s4_body_guard1 scplayer

					TASK_LOOK_AT_CHAR s4_body_guard1 scplayer 6000

					GOSUB syn4_set_camera

					IF NOT IS_CHAR_DEAD scplayer

						SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer -2624.1123 1410.4727 6.1149  

						SET_CHAR_HEADING scplayer 13.4662

					ENDIF

					TASK_LOOK_AT_CHAR s4_body_guard0 scplayer 6000
			 		 						     
					SET_FIXED_CAMERA_POSITION -2623.7146 1409.6417 7.7873 0.0 0.0 0.0 // Bouncers at door
					POINT_CAMERA_AT_POINT -2623.8706 1410.6276 7.7269 JUMP_CUT

					WAIT 1000

					GENERATE_RANDOM_INT_IN_RANGE 0 3 syn4_rnd

					//current getting stuck skipping the cutscenes here
					//SKIP_CUTSCENE_START

					SWITCH syn4_rnd

						CASE 0

							CLEAR_MISSION_AUDIO 1

						 	LOAD_MISSION_AUDIO 1 SOUND_SYN4_BA // Sorry, man, private function.

							WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
								WAIT 0
							ENDWHILE

							PLAY_MISSION_AUDIO 1

							PRINT_NOW ( SYN4_BA ) 4000 1 // Sorry, man, private function.

							TIMERB = 0
							WHILE TIMERB < 4000
								WAIT 0
								IF IS_BUTTON_PRESSED PAD1 CROSS
									GOTO s4_skip_the_cut
								ENDIF
							ENDWHILE

						BREAK

						CASE 1

							CLEAR_MISSION_AUDIO 1

						 	LOAD_MISSION_AUDIO 1 SOUND_SYN4_BB // Jizzy doesn’t want to be disturbed.

							WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
								WAIT 0
							ENDWHILE

							PLAY_MISSION_AUDIO 1

							PRINT_NOW ( SYN4_BB ) 4000 1 // Jizzy doesn’t want to be disturbed.

							TIMERB = 0
							WHILE TIMERB < 4000
								WAIT 0
								IF IS_BUTTON_PRESSED PAD1 CROSS
									GOTO s4_skip_the_cut
								ENDIF
							ENDWHILE
						
						BREAK

						CASE 2

							CLEAR_MISSION_AUDIO 1

						 	LOAD_MISSION_AUDIO 1 SOUND_SYN4_BC // You ain’t getting in!

							WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
								WAIT 0
							ENDWHILE

							PLAY_MISSION_AUDIO 1

							PRINT_NOW ( SYN4_BC ) 4000 1 // You ain’t getting in!

							TIMERB = 0
							WHILE TIMERB < 4000
								WAIT 0
								IF IS_BUTTON_PRESSED PAD1 CROSS
									GOTO s4_skip_the_cut
								ENDIF
							ENDWHILE

						BREAK

					ENDSWITCH

					TIMERB = 0
					WHILE TIMERB < 1000
						WAIT 0
						IF IS_BUTTON_PRESSED PAD1 CROSS
							s4_txt_skylight = 1
							GOTO s4_skip_the_cut
						ENDIF
					ENDWHILE

				ENDIF

			ENDIF 

			SET_FIXED_CAMERA_POSITION -2671.1270 1432.5940 25.4663 0.0 0.0 0.0 // Bouncers on door
			POINT_CAMERA_AT_POINT -2670.4299 1431.8813 25.3871 JUMP_CUT

			PRINT_NOW ( SYN4_12 ) 5000 1 // ~s~Use the ~y~skylight ~s~to get into the club!

			CREATE_BIRDS 2682.4202 1420.2371 28.0000 -2630.3650 1411.6986 28.0000 3 BIRDTYPE_SEAGULL

			TIMERB = 0
			WHILE TIMERB < 5000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
					GOTO s4_skip_the_cut
				ENDIF
			ENDWHILE

			s4_skip_the_cut:

			GOSUB syn4_restore_camera

			ADD_BLIP_FOR_COORD -2661.9243 1424.2776 22.8906 syn4_skylight

			IF s4_txt_skylight = 1

				PRINT_NOW ( SYN4_12 ) 5000 1 // ~s~Use the ~y~skylight ~s~to get into the club!

			ENDIF

			//currently getting stuck skipping cutscenes above
			//SKIP_CUTSCENE_END

			APPLY_BRAKES_TO_PLAYERS_CAR player1 OFF

			GOTO s4_pass_go			

		ENDIF
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2660.1450 1419.2113 22.8906 5.0 5.0 5.0 FALSE

		GOTO s4_pass_go

	ENDIF

ENDWHILE

GOTO mission_synd4_failed

s4_pass_go:

REQUEST_MODEL bmydrug
REQUEST_MODEL hmydrug
REQUEST_MODEL HFYRI
REQUEST_MODEL BFYPRO
REQUEST_MODEL BMYDJ
REQUEST_MODEL micro_uzi
REQUEST_MODEL VWFYWAI
REQUEST_MODEL COLT45
LOAD_SPECIAL_CHARACTER 1 jizzy
REQUEST_MODEL VHFYST3

REQUEST_ANIMATION SWAT

WHILE NOT HAS_MODEL_LOADED bmydrug
OR NOT HAS_MODEL_LOADED hmydrug
OR NOT HAS_MODEL_LOADED HFYRI
OR NOT HAS_MODEL_LOADED BFYPRO
OR NOT HAS_MODEL_LOADED BMYDJ

	WAIT 0

ENDWHILE

WHILE NOT HAS_MODEL_LOADED micro_uzi
OR NOT HAS_MODEL_LOADED VWFYWAI
OR NOT HAS_MODEL_LOADED COLT45
OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
OR NOT HAS_ANIMATION_LOADED SWAT
OR NOT HAS_MODEL_LOADED VHFYST3
	WAIT 0

ENDWHILE

WHILE 

	WAIT 0

ENDWHILE

CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 -2669.1270 1426.9191 905.4609 syn4_jizzy

SET_CHAR_AREA_VISIBLE syn4_jizzy 3

SET_ANIM_GROUP_FOR_CHAR syn4_jizzy gang1

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_SPECIAL PEDTYPE_PLAYER1

SET_CHAR_HEADING syn4_jizzy 238.0256  

SET_SENSE_RANGE syn4_jizzy 5.0

SET_CHAR_DECISION_MAKER syn4_jizzy s4_empty_decision

CREATE_CHAR PEDTYPE_CIVFEMALE BFYPRO -2667.6562 1425.8542 905.4609 syn4_girl

SET_CHAR_AREA_VISIBLE syn4_girl 3

SET_CHAR_HEADING syn4_girl 76.4221  

SET_CHAR_NEVER_TARGETTED syn4_girl TRUE

TASK_CHAT_WITH_CHAR syn4_jizzy syn4_girl TRUE TRUE

TASK_CHAT_WITH_CHAR syn4_girl syn4_jizzy FALSE TRUE

ADD_BLIP_FOR_CHAR syn4_jizzy syn4_jizzy_blip

CHANGE_BLIP_DISPLAY syn4_jizzy_blip NEITHER

// ************************************** Guards *********************************************
 
OPEN_SEQUENCE_TASK s4_sitdown_seq

	TASK_SIT_DOWN -1 200000

	TASK_LOOK_ABOUT -1 2000

	SET_SEQUENCE_TO_REPEAT s4_sitdown_seq 1

CLOSE_SEQUENCE_TASK s4_sitdown_seq
 
OPEN_SEQUENCE_TASK s4_sitdown_seq_1

	TASK_PAUSE -1 1000

	PERFORM_SEQUENCE_TASK -1 s4_sitdown_seq

CLOSE_SEQUENCE_TASK s4_sitdown_seq_1

CREATE_CHAR PEDTYPE_MISSION1 bmydrug -2665.2363 1429.8842 905.4609 s4_guards[0]

SET_CHAR_HEADING s4_guards[0] 133.1702  

PERFORM_SEQUENCE_TASK s4_guards[0] s4_sitdown_seq_1

CREATE_CHAR PEDTYPE_MISSION1 hmydrug -2667.1021 1430.0244 905.4609 s4_guards[1]

SET_CHAR_HEADING s4_guards[1] 180.8760  

PERFORM_SEQUENCE_TASK s4_guards[1] s4_sitdown_seq

CREATE_CHAR PEDTYPE_MISSION1 bmydrug -2665.0642 1428.4020 905.4609 s4_guards[2]

SET_CHAR_HEADING s4_guards[2] 106.0457

PERFORM_SEQUENCE_TASK s4_guards[2] s4_sitdown_seq

CREATE_CHAR PEDTYPE_MISSION1 BFYPRO -2668.1174 1429.9332 905.4609 s4_guards[3]

SET_CHAR_HEADING s4_guards[3] 180.8760

PERFORM_SEQUENCE_TASK s4_guards[3] s4_sitdown_seq_1

// ********************************************************************************************

OPEN_SEQUENCE_TASK s4_seq_bar_walk[0]

	TASK_GO_TO_COORD_ANY_MEANS -1 -2655.5076 1410.5660 905.2734 PEDMOVE_WALK -1

	TASK_ACHIEVE_HEADING -1 263.3926

	TASK_PAUSE -1 2000

	TASK_GO_TO_COORD_ANY_MEANS -1 -2655.8923 1406.6747 905.2734 PEDMOVE_WALK -1

	TASK_ACHIEVE_HEADING -1 181.7338

	TASK_PAUSE -1 2000

	SET_SEQUENCE_TO_REPEAT s4_seq_bar_walk[0] 1

CLOSE_SEQUENCE_TASK s4_seq_bar_walk[0]

OPEN_SEQUENCE_TASK s4_seq_bar_walk[1]

	TASK_GO_TO_COORD_ANY_MEANS -1 -2660.2676 1413.9594 905.2734 PEDMOVE_WALK -1

	TASK_ACHIEVE_HEADING -1 348.4510

	TASK_PAUSE -1 2000

	TASK_GO_TO_COORD_ANY_MEANS -1 -2662.8408 1413.7321 905.2808 PEDMOVE_WALK -1

	TASK_ACHIEVE_HEADING -1 96.5734

	TASK_PAUSE -1 2000

	SET_SEQUENCE_TO_REPEAT s4_seq_bar_walk[1] 1

CLOSE_SEQUENCE_TASK s4_seq_bar_walk[1]

// ********************************************************************************************

CREATE_CHAR PEDTYPE_MISSION3 VWFYWAI -2655.5076 1410.5660 905.2734 s4_ambience[0]

SET_CHAR_HEADING s4_ambience[0] 263.3926

CREATE_CHAR PEDTYPE_MISSION3 VWFYWAI -2660.2676 1413.9594 905.2734 s4_ambience[1]

SET_CHAR_HEADING s4_ambience[1] 348.4510 

PERFORM_SEQUENCE_TASK s4_ambience[0] s4_seq_bar_walk[0]

PERFORM_SEQUENCE_TASK s4_ambience[1] s4_seq_bar_walk[1]

CREATE_CHAR PEDTYPE_MISSION3 bmydrug -2657.2688 1403.5751 905.2812 s4_ambience[2]

SET_CHAR_HEADING s4_ambience[2] 271.0479

CREATE_CHAR PEDTYPE_MISSION3 BFYPRO -2654.6169 1404.6565 905.2812 s4_ambience[3]

SET_CHAR_HEADING s4_ambience[3] 87.3589 

TASK_CHAT_WITH_CHAR s4_ambience[2] s4_ambience[3] TRUE TRUE 

TASK_CHAT_WITH_CHAR s4_ambience[3] s4_ambience[2] TRUE TRUE 

// ********************************************************************************************

REPEAT 4 v

	IF NOT IS_CHAR_DEAD s4_guards[v]

		SET_CHAR_AREA_VISIBLE s4_guards[v] 3

		SET_CHAR_IS_TARGET_PRIORITY s4_guards[v] TRUE

		GIVE_WEAPON_TO_CHAR s4_guards[v] WEAPONTYPE_MICRO_UZI 30000

		SET_CURRENT_CHAR_WEAPON s4_guards[v] WEAPONTYPE_MICRO_UZI 

		SET_CHAR_ACCURACY s4_guards[v] 70

		SET_CHAR_DECISION_MAKER s4_guards[v] s4_empty_decision

	ENDIF

	IF NOT IS_CHAR_DEAD s4_ambience[v]

		SET_CHAR_AREA_VISIBLE s4_ambience[v] 3

	ENDIF

ENDREPEAT

flag_player_on_mission = 1

REGISTER_MISSION_GIVEN

ADD_BLIP_FOR_COORD -2655.0752 1431.7887 912.4016 the_phone_blip
SET_BLIP_ENTRY_EXIT the_phone_blip -2661.7397 1423.4828 100.0

CHANGE_BLIP_DISPLAY the_phone_blip NEITHER

// *************************************Set Flags/variables***********************************

SET_CAMERA_BEHIND_PLAYER

FLUSH_ROUTE

EXTEND_ROUTE -2653.2170 1424.8674 906.7266

TIMERA = 0

// *******************************************************************************************
//
//                                     OUTSIDE THE CLUB
//
// *******************************************************************************************
	
WHILE NOT IS_CHAR_DEAD scplayer

	WAIT 0

	IF s4_guys_are_attacking = 0

		IF NOT IS_CHAR_DEAD s4_body_guard0
			
			IF IS_PLAYER_TARGETTING_CHAR player1 s4_body_guard0

				IF NOT IS_CHAR_DEAD s4_body_guard0

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

					TASK_KILL_CHAR_ON_FOOT s4_body_guard0 scplayer

				ENDIF

				IF NOT IS_CHAR_DEAD s4_body_guard1

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

					TASK_KILL_CHAR_ON_FOOT s4_body_guard1 scplayer

				ENDIF

				s4_guys_are_attacking = 1

			ENDIF
			
		ENDIF

		IF NOT IS_CHAR_DEAD s4_body_guard1
			
			IF IS_PLAYER_TARGETTING_CHAR player1 s4_body_guard1

				IF NOT IS_CHAR_DEAD s4_body_guard0

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

					TASK_KILL_CHAR_ON_FOOT s4_body_guard0 scplayer

				ENDIF

				IF NOT IS_CHAR_DEAD s4_body_guard1

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

					TASK_KILL_CHAR_ON_FOOT s4_body_guard1 scplayer

				ENDIF
				
				s4_guys_are_attacking = 1

			ENDIF

		ENDIF

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
		
		GOTO mission_synd4_passed
		
	ENDIF

	GOSUB syn4_keys

	GOSUB s4_play_sample

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2661.0098 1415.3844 921.1953 20.0 20.0 20.0 FALSE
	AND jizzy_phone_cut = 0

		pussy_galore:

		SWITCH_ENTRY_EXIT PDOMES FALSE

		IF DOES_BLIP_EXIST s4_start_blip

			REMOVE_BLIP s4_start_blip

		ENDIF

		REMOVE_BLIP syn4_skylight

		ADD_BLIP_FOR_COORD -2667.3682 1427.4348 905.4609 syn4_sniper_blip
		SET_BLIP_ENTRY_EXIT syn4_sniper_blip -2661.7397 1423.4828 100.0

		IF NOT IS_CHAR_DEAD scplayer
			
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			
			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer -2661.0549 1416.0194 921.1953 
    
			SET_CHAR_HEADING scplayer 180.0000 

		ENDIF

		jizzy_phone_cut = 1
		
		IF NOT IS_CHAR_DEAD s4_body_guard0

			DELETE_CHAR s4_body_guard0

		ENDIF

		IF NOT IS_CHAR_DEAD s4_body_guard1

			DELETE_CHAR s4_body_guard1

		ENDIF

		IF NOT IS_CHAR_DEAD scplayer
			
			SET_CHAR_HEADING scplayer 197.6688 

		ENDIF

		PRINT_NOW ( SYN4_99 ) 4000 1 // ~s~Sneak up to ~y~Jizzy~s~, you need to get that number.

		SET_WANTED_MULTIPLIER 0.0

		GOTO s4_main_loop

	ENDIF

ENDWHILE

GOTO mission_synd4_failed

// *******************************************************************************************
// *																						 *
// *                                    INSIDE THE CLUB										 *
// *																						 *
// *******************************************************************************************
													
s4_main_loop:

WHILE NOT IS_CHAR_DEAD scplayer

	WAIT 0 
	
	GOSUB syn4_keys

	GOSUB s4_play_sample

	IF syn4_ready = 0

		IF s4_lost = 1

			syn4_in_pdomes = 1

	    	GOTO mission_synd4_failed

		ENDIF

		REPEAT 4 v

			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR s4_guards[v] scplayer

				PRINT_NOW ( SYN4_08 ) 4000 1 // ~r~You scared Jizzy before he made the call
				
				s4_lost = 1
					
				TIMERA = 0

			ENDIF

		ENDREPEAT

		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR syn4_jizzy scplayer

			PRINT_NOW ( SYN4_06 ) 4000 1 //	~r~You killed Jizzy before he made the call
							
			s4_lost = 1
				
			TIMERA = 0

		ENDIF

		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR syn4_girl scplayer

			PRINT_NOW ( SYN4_08 ) 4000 1 // ~r~You scared Jizzy before he made the call
							
			s4_lost = 1
				
			TIMERA = 0

		ENDIF

		// *******************************************************************************************
		// *																						 *
		// *                               Player is spotted by group								 *
		// *																						 *
		// *******************************************************************************************

	  /*IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2668.4612 1427.6218 905.4609 8.0 8.0 8.0 FALSE 

			PRINT_NOW ( SYN4_08 ) 4000 1 // ~r~You scared Jizzy before he made the call
							
			s4_lost = 1
				
			TIMERA = 0

		ENDIF */

		// *******************************************************************************************
		// *																						 *
		// *                                 Jizzy makes the call									 *
		// *																						 *
		// *******************************************************************************************
											 		 
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2668.7161 1421.8464 905.2770 15.0 15.0 15.0 FALSE
	 
			seaman_staines:

			SWITCH_ENTRY_EXIT PDOMES2 TRUE

		  	SET_FADING_COLOUR 0 0 0

			DO_FADE 500 FADE_OUT

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			REPEAT 4 v

				DELETE_CHAR s4_guards[v]

			ENDREPEAT

			IF NOT IS_CHAR_DEAD syn4_girl

				DELETE_CHAR syn4_girl

			ENDIF

			IF DOES_BLIP_EXIST syn4_jizzy_blip

				REMOVE_BLIP syn4_jizzy_blip

			ENDIF

			IF NOT IS_CHAR_DEAD syn4_jizzy

				DELETE_CHAR syn4_jizzy

			ENDIF

			LOAD_CUTSCENE SYND_4B
			 
			WHILE NOT HAS_CUTSCENE_LOADED
				WAIT 0
			ENDWHILE
			 
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

			GOSUB syn4_set_camera

			CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 -2669.1270 1426.9191 905.4609 syn4_jizzy

			SET_CHAR_AREA_VISIBLE syn4_jizzy 3

			SET_ANIM_GROUP_FOR_CHAR syn4_jizzy gang1

			SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_SPECIAL PEDTYPE_PLAYER1

			SET_CHAR_HEADING syn4_jizzy 238.0256  

			SET_SENSE_RANGE syn4_jizzy 5.0

			SET_CHAR_DECISION_MAKER syn4_jizzy s4_empty_decision

			CREATE_CHAR PEDTYPE_MISSION1 bmydrug -2665.2363 1429.8842 905.4609 s4_guards[0]

			SET_CHAR_HEADING s4_guards[0] 133.1702  

			PERFORM_SEQUENCE_TASK s4_guards[0] s4_sitdown_seq_1

			CREATE_CHAR PEDTYPE_MISSION1 hmydrug -2667.1021 1430.0244 905.4609 s4_guards[1]

			SET_CHAR_HEADING s4_guards[1] 180.8760  

			PERFORM_SEQUENCE_TASK s4_guards[1] s4_sitdown_seq

			CREATE_CHAR PEDTYPE_MISSION1 bmydrug -2665.0642 1428.4020 905.4609 s4_guards[2]

			SET_CHAR_HEADING s4_guards[2] 106.0457

			PERFORM_SEQUENCE_TASK s4_guards[2] s4_sitdown_seq

			CREATE_CHAR PEDTYPE_MISSION1 BFYPRO -2668.1174 1429.9332 905.4609 s4_guards[3]

			SET_CHAR_HEADING s4_guards[3] 180.8760

			PERFORM_SEQUENCE_TASK s4_guards[3] s4_sitdown_seq_1

			ADD_BLIP_FOR_CHAR syn4_jizzy syn4_jizzy_blip

			CHANGE_BLIP_DISPLAY syn4_jizzy_blip NEITHER

			UNLOAD_SPECIAL_CHARACTER 1

			REPEAT 4 v

				SET_CHAR_AREA_VISIBLE s4_guards[v] 3

				GIVE_WEAPON_TO_CHAR s4_guards[v] WEAPONTYPE_MICRO_UZI 30000

				SET_CURRENT_CHAR_WEAPON s4_guards[v] WEAPONTYPE_MICRO_UZI

			ENDREPEAT

			IF NOT IS_CHAR_DEAD syn4_jizzy 

				SET_FIXED_CAMERA_POSITION -2669.5706 1410.8710 907.8965 0.0 0.0 0.0 
				POINT_CAMERA_AT_CHAR syn4_jizzy FIXED JUMP_CUT

			ENDIF

			CREATE_CHAR PEDTYPE_MISSION1 BMYBOUN -2667.1516 1421.5535 905.2812 s4_jizzy_buddy

			SET_CHAR_HEADING s4_jizzy_buddy 275.4244 

			TASK_TOGGLE_PED_THREAT_SCANNER s4_jizzy_buddy FALSE FALSE FALSE

			SET_CHAR_AREA_VISIBLE s4_jizzy_buddy 3

			WAIT 100

			DO_FADE 0 FADE_IN

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			PRINT_NOW ( SYN4_05 ) 4000 1 // ~r~Jizzy's ~s~escaping, Hunt him down!
			
			IF NOT IS_CHAR_DEAD s4_jizzy_buddy

				PERFORM_SEQUENCE_TASK s4_jizzy_buddy s4_seq_club
	
			ENDIF

			REPEAT 4 v

				IF NOT IS_CHAR_DEAD s4_ambience[v]

					CLEAR_CHAR_TASKS_IMMEDIATELY s4_ambience[v]

					TASK_DUCK s4_ambience[v] -2

				ENDIF

			ENDREPEAT 

			IF NOT IS_CHAR_DEAD syn4_jizzy

				CLEAR_CHAR_TASKS syn4_jizzy 

			  	SET_CHAR_COORDINATES_DONT_WARP_GANG syn4_jizzy -2664.4619 1421.7664 905.2770  

			  	SET_CHAR_HEADING syn4_jizzy 249.6271 

				PERFORM_SEQUENCE_TASK syn4_jizzy s4_seq_club

				ADD_ARMOUR_TO_CHAR syn4_jizzy 150

				SET_CHAR_HEALTH syn4_jizzy 300

				SET_CHAR_MAX_HEALTH syn4_jizzy 300

				SET_CHAR_SUFFERS_CRITICAL_HITS syn4_jizzy FALSE 
				  
			ENDIF

			IF NOT IS_CHAR_DEAD scplayer
			
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
				
				SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer -2666.6382 1410.3051 905.2812  

				SET_CHAR_HEADING scplayer 0.0000

				SET_CAMERA_BEHIND_PLAYER 

			ENDIF

			WAIT 1000

			TIMERB = 0
			WHILE TIMERB < 3000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
					GOTO s4_the_skip
				ENDIF
			ENDWHILE
			
			s4_the_skip:

			GOSUB syn4_restore_camera

			LVAR_INT s4_peek_pillar s4_peek_pillar_3 s4_peek_pillar_1 s4_peek_pillar_2 

			LVAR_INT s4_guy_a s4_guy_c

			OPEN_SEQUENCE_TASK s4_peek_pillar_1

				TASK_GO_TO_COORD_ANY_MEANS -1 -2639.2773 1409.0222 905.4646 PEDMOVE_WALK -1
				TASK_ACHIEVE_HEADING -1 63.4408

			CLOSE_SEQUENCE_TASK s4_peek_pillar_1

			OPEN_SEQUENCE_TASK s4_peek_pillar

				PERFORM_SEQUENCE_TASK -1 s4_peek_pillar_1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_R SWAT 8.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_in_R SWAT 8.0 FALSE TRUE TRUE FALSE -1
				TASK_STAY_IN_SAME_PLACE -1 TRUE
				TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer 2000
				TASK_STAY_IN_SAME_PLACE -1 FALSE
				//TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM -1 enemyx_fa enemyy_fa enemyz_fa heading_fa -1.0 swt_wllshoot_out_R SWAT 8.0 FALSE TRUE TRUE FALSE -1 
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_out_R SWAT 8.0 FALSE TRUE TRUE FALSE -1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_R_back SWAT 8.0 FALSE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT s4_peek_pillar 1

			CLOSE_SEQUENCE_TASK s4_peek_pillar
				 
			OPEN_SEQUENCE_TASK s4_peek_pillar_3

				TASK_GO_TO_COORD_ANY_MEANS -1 -2639.3518 1402.7660 905.4646 PEDMOVE_WALK -1
				TASK_ACHIEVE_HEADING -1 73.2860

			CLOSE_SEQUENCE_TASK s4_peek_pillar_3

			OPEN_SEQUENCE_TASK s4_peek_pillar_2

				PERFORM_SEQUENCE_TASK -1 s4_peek_pillar_3
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_L SWAT 8.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_in_L SWAT 8.0 FALSE TRUE TRUE FALSE -1
				TASK_STAY_IN_SAME_PLACE -1 TRUE
				TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer 2000
				TASK_STAY_IN_SAME_PLACE -1 FALSE
				//TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM -1 enemyx_fa enemyy_fa enemyz_fa heading_fa -1.0 swt_wllshoot_out_R SWAT 8.0 FALSE TRUE TRUE FALSE -1 
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_out_L SWAT 8.0 FALSE TRUE TRUE FALSE -1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_L_back SWAT 8.0 FALSE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT s4_peek_pillar_2 1

			CLOSE_SEQUENCE_TASK s4_peek_pillar_2

			CREATE_CHAR PEDTYPE_MISSION1 BMYBOUN -2639.2773 1409.0222 905.4646 s4_guy_a

			SET_CHAR_AREA_VISIBLE s4_guy_a 1

			SET_CHAR_HEADING s4_guy_a 63.4408    
				   
			SET_CHAR_DECISION_MAKER s4_guy_a s4_empty_decision

			PERFORM_SEQUENCE_TASK s4_guy_a s4_peek_pillar

			CREATE_CHAR PEDTYPE_MISSION1 WMYBOUN -2639.3518 1402.7660 905.4646 s4_guy_c

			SET_CHAR_AREA_VISIBLE s4_guy_c 1

			SET_CHAR_HEADING s4_guy_c 73.2860  
				   
			SET_CHAR_DECISION_MAKER s4_guy_c s4_empty_decision

			PERFORM_SEQUENCE_TASK s4_guy_c s4_peek_pillar_2

			GIVE_WEAPON_TO_CHAR s4_guy_a WEAPONTYPE_MICRO_UZI 30000

			SET_CURRENT_CHAR_WEAPON s4_guy_a WEAPONTYPE_MICRO_UZI

			GIVE_WEAPON_TO_CHAR s4_guy_c WEAPONTYPE_MICRO_UZI 30000

			SET_CURRENT_CHAR_WEAPON s4_guy_c WEAPONTYPE_MICRO_UZI

			IF NOT IS_CHAR_DEAD s4_guards[0]

				SET_CHAR_COORDINATES_DONT_WARP_GANG s4_guards[0] -2671.7615 1420.3030 905.2812 

				SET_CHAR_HEADING s4_guards[0] 214.6866 

			ENDIF

			IF NOT IS_CHAR_DEAD s4_guards[1]

				SET_CHAR_COORDINATES_DONT_WARP_GANG s4_guards[1] -2665.0955 1421.3395 905.2812 

				SET_CHAR_HEADING s4_guards[1] 167.6077 

			ENDIF

			IF NOT IS_CHAR_DEAD syn4_jizzy

				CLEAR_CHAR_TASKS_IMMEDIATELY syn4_jizzy 

				CHANGE_BLIP_DISPLAY syn4_jizzy_blip NEITHER

				SET_CHAR_COORDINATES_DONT_WARP_GANG syn4_jizzy -2621.4648 1411.3376 905.4609  

//				SET_CHAR_COORDINATES_DONT_WARP_GANG syn4_jizzy -2621.4648 1411.3376 6.1094  

				FREEZE_CHAR_POSITION syn4_jizzy TRUE

				SET_CHAR_HEADING syn4_jizzy 277.7340 
				
				SET_CHAR_AREA_VISIBLE syn4_jizzy 0

				s4_jizzy_is_outside = 1

			ENDIF

			IF NOT IS_CHAR_DEAD s4_jizzy_buddy
				
				CLEAR_CHAR_TASKS_IMMEDIATELY s4_jizzy_buddy

				SET_CHAR_COORDINATES_DONT_WARP_GANG s4_jizzy_buddy -2625.8364 1404.7500 6.0938  

				SET_CHAR_HEADING s4_jizzy_buddy 279.9147   
								
				SET_CHAR_AREA_VISIBLE s4_jizzy_buddy 0

			ENDIF

			SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1

			REPEAT 4 v

				IF NOT IS_CHAR_DEAD s4_guards[v]

					CLEAR_CHAR_TASKS_IMMEDIATELY s4_guards[v]

					TASK_KILL_CHAR_ON_FOOT s4_guards[v] scplayer

				  	SET_CHAR_DECISION_MAKER s4_guards[v] s4_decision

				//	PERFORM_SEQUENCE_TASK s4_guards[v] s4_seq_table[v]

				ENDIF

			ENDREPEAT

			CHANGE_BLIP_DISPLAY s4_door_blip BOTH
			   
			CHANGE_BLIP_DISPLAY syn4_sniper_blip NEITHER

			SET_CAMERA_BEHIND_PLAYER

			syn4_mission_stat = 1

			syn4_ready = 1
			
		ENDIF

	ENDIF

	// ****************************************************************************************************
	// *																						 		  *
	// *                           	      Quit out of wall peeking code									  *
	// *																					  		      *
	// ****************************************************************************************************

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2640.9978 1406.2753 905.4609 8.0 8.0 8.0 FALSE 

		IF NOT IS_CHAR_DEAD s4_guy_a
		AND s4_guy_quit_a = 0			

			CLEAR_CHAR_TASKS_IMMEDIATELY s4_guy_a

			TASK_KILL_CHAR_ON_FOOT s4_guy_a scplayer

			s4_guy_quit_a = 1

		ENDIF

		IF NOT IS_CHAR_DEAD s4_guy_c
		AND s4_guy_quit_b = 0

			CLEAR_CHAR_TASKS_IMMEDIATELY s4_guy_c

			TASK_KILL_CHAR_ON_FOOT s4_guy_c scplayer

			s4_guy_quit_b = 1

		ENDIF

	ENDIF

	IF syn4_ready = 1
	 
		// ****************************************************************************************************
		// *																								  *
		// *                                   Player exits with the phone									  *
		// *																								  *
		// ****************************************************************************************************

		IF syn4_mission_stat = 1

		  	IF IS_CHAR_DEAD syn4_jizzy 
			AND s4_jizzy_indoors = 0

				IF DOES_BLIP_EXIST s4_door_blip
					REMOVE_BLIP s4_door_blip
				ENDIF 

				GOTO mission_synd4_passed
		  	
		  	ENDIF	

			IF NOT IS_CHAR_DEAD syn4_jizzy

				IF LOCATE_CHAR_ANY_MEANS_3D syn4_jizzy -2636.3848 1402.4854 905.4609 2.0 2.0 2.0 FALSE
				AND s4_jizzy_is_outside = 0

					IF NOT IS_CHAR_DEAD syn4_jizzy
					 
						CLEAR_CHAR_TASKS syn4_jizzy 

						CHANGE_BLIP_DISPLAY syn4_jizzy_blip NEITHER

						SET_CHAR_COORDINATES_DONT_WARP_GANG syn4_jizzy -2621.4648 1411.3376 6.1094  

						SET_CHAR_HEADING syn4_jizzy 277.7340 
									
						SET_CHAR_AREA_VISIBLE syn4_jizzy 0
	
						s4_jizzy_is_outside = 1

					ENDIF

				ENDIF
				
			ENDIF

			IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2624.7998 1411.5399 6.1015 4.0 4.0 4.0 FALSE
			OR LOCATE_CHAR_ANY_MEANS_3D scplayer -2661.8499 1426.3899 22.8985 4.0 4.0 4.0 FALSE

				GOSUB syn4_fade_out

				IF NOT IS_CHAR_DEAD syn4_jizzy
							
					SET_CHAR_COORDINATES_DONT_WARP_GANG syn4_jizzy -2621.4648 1411.3376 6.1094 
							
					FREEZE_CHAR_POSITION syn4_jizzy FALSE

				ENDIF

				SWITCH_ENTRY_EXIT PDOMES FALSE

				SWITCH_ENTRY_EXIT PDOMES2 FALSE

				MARK_ROAD_NODE_AS_DONT_WANDER -2686.4150 1235.5719 54.4297

				MARK_ROAD_NODE_AS_DONT_WANDER -2671.2976 1236.4919 54.4297

				// ** Limo's outside   *********************************************************************

				IF IS_CAR_DEAD s4_limo[2]

					DELETE_CAR s4_limo[2]

					CLEAR_AREA -2611.4714 1409.0361 6.1335 20.0 TRUE

					LVAR_INT s4_pizzaboy

					REQUEST_MODEL pizzaboy
					REQUEST_MODEL WMYPIZZ

					WHILE NOT HAS_MODEL_LOADED pizzaboy
					OR NOT HAS_MODEL_LOADED WMYPIZZ
						WAIT 0
					ENDWHILE

					CREATE_CAR pizzaboy -2611.4714 1409.0361 6.1335 s4_limo[2]
				
					SET_CAR_HEADING s4_limo[2] 274.4727

					CREATE_CHAR_INSIDE_CAR s4_limo[2] PEDTYPE_CIVMALE WMYPIZZ s4_pizzaboy

					DELETE_CHAR s4_jizzy_buddy	 

					IF NOT IS_CHAR_DEAD syn4_jizzy

						SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE syn4_jizzy KNOCKOFFBIKE_EASY

					ENDIF

				ENDIF
				
				// ** Driving Sequence *********************************************************************

				OPEN_SEQUENCE_TASK s4_seq_drive

					TASK_ENTER_CAR_AS_DRIVER -1 s4_limo[2] 5000

					TASK_PAUSE -1 1000

					TASK_CAR_DRIVE_WANDER -1 s4_limo[2] 20.0 DRIVINGMODE_AVOIDCARS

				CLOSE_SEQUENCE_TASK	s4_seq_drive

				OPEN_SEQUENCE_TASK s4_seq_flee

					TASK_LEAVE_CAR -1 s4_limo[2]

					TASK_KILL_CHAR_ON_FOOT -1 scplayer

				CLOSE_SEQUENCE_TASK	s4_seq_flee

				CHANGE_CAR_COLOUR s4_limo[2] 4 4	

				CLEAR_AREA -2621.8103 1398.7444 6.0860 20.0 TRUE

				REMOVE_BLIP s4_door_blip 

				IF NOT IS_CHAR_DEAD scplayer
				
					SET_CHAR_AREA_VISIBLE scplayer 0

					FORCE_WEATHER_NOW WEATHER_FOGGY_SF

					SET_AREA_VISIBLE 0
						 
					SET_CAMERA_BEHIND_PLAYER

					IF NOT IS_CHAR_DEAD syn4_jizzy

						SET_CHAR_COORDINATES_DONT_WARP_GANG syn4_jizzy -2624.4846 1411.3527 6.1015    

						SET_CHAR_HEADING syn4_jizzy 267.9166 

					ENDIF

					IF NOT IS_CHAR_DEAD s4_jizzy_buddy
						
						CLEAR_CHAR_TASKS_IMMEDIATELY s4_jizzy_buddy

						SET_CHAR_COORDINATES_DONT_WARP_GANG s4_jizzy_buddy -2620.6042 1406.9703 6.1016  
   
						SET_CHAR_HEADING s4_jizzy_buddy 277.7697   

						GIVE_WEAPON_TO_CHAR s4_jizzy_buddy WEAPONTYPE_PISTOL 30000

						SET_CURRENT_CHAR_WEAPON s4_jizzy_buddy WEAPONTYPE_PISTOL 

						SET_CHAR_DECISION_MAKER s4_jizzy_buddy s4_decision_buddy

						//ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE s4_decision_buddy EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_GANG_DRIVEBY 0.0 50.0 0.0 0.0 TRUE FALSE

						//ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE s4_decision_buddy EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_LOOK_AT_ENTITY_OR_COORD 0.0 50.0 0.0 0.0 TRUE FALSE

						SET_CHAR_ACCURACY s4_jizzy_buddy 30

						SET_CHAR_SHOOT_RATE s4_jizzy_buddy 30

					ENDIF

					DELETE_CHAR syn4_girl

					REPEAT 4 v
						
						DELETE_CHAR s4_guards[v]

					ENDREPEAT

					GOSUB syn4_set_camera

 					IF NOT IS_CHAR_DEAD syn4_jizzy
					AND NOT IS_CAR_DEAD s4_limo[2]

						SET_FIXED_CAMERA_POSITION -2626.0188 1411.5022 9.1763 0.0 0.0 0.0 
						POINT_CAMERA_AT_POINT -2625.0586 1411.3501 8.9421 JUMP_CUT

						CLEAR_CHAR_TASKS_IMMEDIATELY syn4_jizzy

						PERFORM_SEQUENCE_TASK syn4_jizzy s4_seq_drive

					ENDIF

					IF NOT IS_CHAR_DEAD s4_jizzy_buddy
						
						CLEAR_CHAR_TASKS_IMMEDIATELY s4_jizzy_buddy

						TASK_ENTER_CAR_AS_PASSENGER s4_jizzy_buddy s4_limo[2] 5000 0
																			    
					ENDIF

					GOSUB syn4_fade_in

					TIMERB = 0
					WHILE TIMERB < 4000
						WAIT 0
						
						
                        IF IS_BUTTON_PRESSED PAD1 CROSS
						AND IS_CHAR_DEAD s4_pizzaboy
							//GOTO s4_skip_the_cut_5
						ENDIF

					ENDWHILE 		
								
					s4_skip_the_cut_5:

					s4_jizzy_indoors = 1

					GOSUB syn4_restore_camera

					IF NOT IS_CHAR_DEAD scplayer

						SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer -2627.0999 1410.3988 6.1015      

						SET_CHAR_HEADING scplayer 279.1925 

					ENDIF

					LVAR_INT s4_buddy_seq_car

					OPEN_SEQUENCE_TASK s4_buddy_seq_car

						TASK_PAUSE -1 4000

						TASK_DRIVE_BY -1 scplayer -1 0.0 0.0 0.0 10.0 DRIVEBY_AI_ALL_DIRN FALSE 30

						SET_SEQUENCE_TO_REPEAT s4_buddy_seq_car 1

					CLOSE_SEQUENCE_TASK s4_buddy_seq_car

					IF NOT IS_CAR_DEAD s4_limo[2]					

						IF NOT IS_CHAR_DEAD s4_jizzy_buddy
						AND NOT IS_CHAR_DEAD syn4_jizzy

							IF NOT IS_CHAR_IN_CAR s4_jizzy_buddy s4_limo[2]

								WARP_CHAR_INTO_CAR_AS_PASSENGER s4_jizzy_buddy s4_limo[2] 0

							ENDIF
																										
							TASK_TOGGLE_PED_THREAT_SCANNER s4_jizzy_buddy TRUE TRUE TRUE

							PERFORM_SEQUENCE_TASK s4_jizzy_buddy s4_buddy_seq_car
							
						ENDIF

						IF NOT IS_CHAR_DEAD syn4_jizzy
						AND NOT IS_CAR_DEAD s4_limo[2]

							GIVE_WEAPON_TO_CHAR syn4_jizzy WEAPONTYPE_MICRO_UZI 30000

							SET_CURRENT_CHAR_WEAPON syn4_jizzy WEAPONTYPE_MICRO_UZI 

							SET_CHAR_ACCURACY syn4_jizzy 90

							IF NOT IS_CHAR_IN_CAR syn4_jizzy s4_limo[2]

								IF NOT IS_CHAR_DEAD s4_pizzaboy

									IF IS_CHAR_IN_ANY_CAR s4_pizzaboy

										DELETE_CHAR s4_pizzaboy
													   
									ENDIF

								ENDIF

								WARP_CHAR_INTO_CAR syn4_jizzy s4_limo[2] 

							ENDIF

						ENDIF

					ENDIF

					CLEAR_PRINTS	   

					CHANGE_BLIP_DISPLAY syn4_jizzy_blip BOTH    
						
					PRINT_NOW (SYN4_02 ) 10000 1 // ~r~Jizzy's ~s~escaping with the phone, hunt him down!

					TIMERA = 0

					SET_WANTED_MULTIPLIER 1.0

					syn4_mission_stat = 2 
				ENDIF	 

			ENDIF  

		ENDIF

		// ****************************************************************************************************
		// *																								  *
		// *                                          The Car Chase											  *
		// *																								  *
	 	// ****************************************************************************************************

		IF NOT IS_CHAR_DEAD syn4_jizzy

			GET_CHAR_COORDINATES syn4_jizzy off_X off_Y off1_X

			IF IS_CHAR_IN_ANY_CAR syn4_jizzy 

				STORE_CAR_CHAR_IS_IN_NO_SAVE syn4_jizzy car

			ENDIF

			GET_CHAR_HIGHEST_PRIORITY_EVENT syn4_jizzy s4_event

			IF s4_event = EVENT_DRAGGED_OUT_CAR

				CLEAR_CHAR_TASKS syn4_jizzy

				TASK_FLEE_CHAR_ANY_MEANS syn4_jizzy scplayer 200.0 9999999 TRUE 3000 1000 10.0

			ENDIF

			IF s4_event = EVENT_PED_ENTERED_MY_VEHICLE

				CLEAR_CHAR_TASKS syn4_jizzy

				TASK_FLEE_CHAR_ANY_MEANS syn4_jizzy scplayer 200.0 9999999 TRUE 3000 1000 10.0

			ENDIF

		ENDIF

	  	IF NOT IS_CHAR_DEAD s4_jizzy_buddy

			GET_CHAR_HIGHEST_PRIORITY_EVENT s4_jizzy_buddy s4_event

			IF s4_event = EVENT_PED_ENTERED_MY_VEHICLE

				CLEAR_CHAR_TASKS s4_jizzy_buddy
																	
				TASK_KILL_CHAR_ON_FOOT s4_jizzy_buddy scplayer

			ENDIF

			IF s4_event = EVENT_DRAGGED_OUT_CAR

				CLEAR_CHAR_TASKS s4_jizzy_buddy
																	
				TASK_KILL_CHAR_ON_FOOT s4_jizzy_buddy scplayer

			ENDIF

		ENDIF

		IF NOT IS_CAR_DEAD car
	
			GET_CAR_HEALTH car s4_cars_health

			IF s4_cars_health < 200

				s4_damaged_goods = 1

				IF NOT IS_CHAR_DEAD s4_jizzy_buddy
				AND s4_jumped = 0

					LVAR_INT s4_buddy_seq
						
					OPEN_SEQUENCE_TASK s4_buddy_seq

						TASK_LEAVE_CAR_IMMEDIATELY -1 car

						TASK_DIE -1

					CLOSE_SEQUENCE_TASK s4_buddy_seq

					PERFORM_SEQUENCE_TASK s4_jizzy_buddy s4_buddy_seq

					s4_jumped = 1

				ENDIF

			ELSE

				s4_damaged_goods = 0

			ENDIF

			IF IS_CAR_ON_FIRE car
			AND s4_leave_car = 0
			AND NOT IS_CHAR_DEAD syn4_jizzy

				LVAR_INT s4_seq_flee_player

				OPEN_SEQUENCE_TASK s4_seq_flee_player

					TASK_LEAVE_CAR_IMMEDIATELY -1 car

					TASK_FLEE_CHAR_ANY_MEANS -1 scplayer 200.0 9999999 TRUE 3000 1000 10.0

					SET_SEQUENCE_TO_REPEAT s4_seq_flee_player 1

				CLOSE_SEQUENCE_TASK s4_seq_flee_player

				SET_CHAR_DECISION_MAKER syn4_jizzy s4_empty_decision

				TIMERB = 0

				PERFORM_SEQUENCE_TASK syn4_jizzy s4_seq_flee_player

				s4_leave_car = 1

			ENDIF

		ENDIF

		IF syn4_mission_stat = 2

			IF IS_CAR_DEAD s4_limo[2]
			AND s4_jizzy_inside_limo = 0
			
				PRINT_NOW ( SYN4_32 ) 5000 1 // ~r~You blew up the phone

				GOTO mission_synd4_failed				
											  
			ENDIF

			IF NOT IS_CAR_DEAD s4_limo[2]
			AND NOT IS_CHAR_DEAD syn4_jizzy

				IF IS_CHAR_IN_CAR syn4_jizzy s4_limo[2]

					s4_jizzy_inside_limo = 0

				ELSE

					s4_jizzy_inside_limo = 1

				ENDIF

			ENDIF

			// ****************************************************************************************************
			// *																								  *
			// *                                      Alter Jizzy's Car Speed									  *
			// *																								  *
		 	// ****************************************************************************************************
			
			IF NOT IS_CHAR_DEAD syn4_jizzy 
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer off_X off_Y off1_X 20.0 20.0 20.0 FALSE

					IF NOT IS_CAR_DEAD s4_limo[2]
					AND s4_gotten_ai = 0

						 IF NOT IS_CAR_ON_FIRE s4_limo[2]

							IF s4_damaged_goods = 0

								TASK_CAR_MISSION syn4_jizzy s4_limo[2] -1 MISSION_CRUISE 25.0 DRIVINGMODE_AVOIDCARS

							ELSE

								TASK_CAR_MISSION syn4_jizzy s4_limo[2] -1 MISSION_CRUISE 32.0 DRIVINGMODE_AVOIDCARS

							ENDIF

							s4_gotten_ai = 1

						ENDIF

					ENDIF

				ELSE

					IF NOT IS_CAR_DEAD s4_limo[2]
					AND s4_gotten_ai = 1

						IF NOT IS_CAR_ON_FIRE s4_limo[2]

							IF s4_damaged_goods = 0

								TASK_CAR_MISSION syn4_jizzy s4_limo[2] -1 MISSION_CRUISE 23.0 DRIVINGMODE_AVOIDCARS

							ELSE

								TASK_CAR_MISSION syn4_jizzy s4_limo[2] -1 MISSION_CRUISE 28.0 DRIVINGMODE_AVOIDCARS

							ENDIF

							s4_gotten_ai = 0

						ENDIF
							
					ENDIF

				ENDIF

			ENDIF

			// ****************************************************************************************************
			// *																								  *
			// *                                          Jizzy gets away     									  *
			// *																								  *
		 	// ****************************************************************************************************

			IF NOT IS_CHAR_DEAD syn4_jizzy
			 
				IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer off_X off_Y off1_X 150.0 150.0 150.0 FALSE

					PRINT_NOW ( SYN4_29 ) 4000 1 // ~r~You let Jizzy escape with the phone.
		
					GOTO mission_synd4_failed				

				ENDIF

				IF NOT IS_CHAR_IN_ANY_CAR syn4_jizzy
				AND s4_jizzy_fleeing = 0

					s4_jizzy_fleeing = 1

				ENDIF
				
			ENDIF

		ENDIF

		IF NOT IS_CHAR_DEAD syn4_jizzy
		AND NOT IS_CAR_DEAD s4_limo[2]
		AND NOT IS_CHAR_DEAD scplayer

			IF IS_CHAR_IN_ANY_CAR scplayer

				STORE_CAR_CHAR_IS_IN scplayer s4_ply_car

				s4_TIMERC ++

				IF s4_TIMERC > 100

					CLEAR_CAR_LAST_DAMAGE_ENTITY s4_limo[2]

					s4_TIMERC = 0

				ENDIF

				IF NOT IS_CAR_DEAD s4_ply_car
				AND TIMERA > 12000

					IF HAS_CAR_BEEN_DAMAGED_BY_CAR s4_limo[2] s4_ply_car

						IF NOT IS_MESSAGE_BEING_DISPLAYED

							syn4_rnd_txt ++

							IF syn4_rnd_txt = 1

								$s4_print = &SYN4_AA	// Hey, I thought we was friends!
								s4_audio = SOUND_SYN4_AA
								GOSUB s4_load_sample

							ENDIF
							IF syn4_rnd_txt = 2

								$s4_print = &SYN4_AB	// You’re blowing your chance to be a playa!
								s4_audio = SOUND_SYN4_AB
								GOSUB s4_load_sample

							ENDIF
							IF syn4_rnd_txt = 3

								$s4_print = &SYN4_AC	// We can talk about this, homie!
								s4_audio = SOUND_SYN4_AC
								GOSUB s4_load_sample

							ENDIF
							
							IF syn4_rnd_txt = 4

								$s4_print = &SYN4_AD	// You're wrecking my ride!
								s4_audio = SOUND_SYN4_AD
								GOSUB s4_load_sample

							ENDIF
							IF syn4_rnd_txt = 5

								$s4_print = &SYN4_AE	// CJ, brother, you have to reconsider your position!
								s4_audio = SOUND_SYN4_AE
								GOSUB s4_load_sample

							ENDIF
							IF syn4_rnd_txt = 6

								$s4_print = &SYN4_AF	// I can fix anything for you in this town, anything!
								s4_audio = SOUND_SYN4_AF
								GOSUB s4_load_sample

							ENDIF
							
							IF syn4_rnd_txt = 7

								$s4_print = &SYN4_AG	// CJ, you’re a fool, A FOOL!
								s4_audio = SOUND_SYN4_AG
								GOSUB s4_load_sample

							ENDIF
							IF syn4_rnd_txt = 8

								$s4_print = &SYN4_AH	// I won’t forget this, CJ, you hear me?
								s4_audio = SOUND_SYN4_AH
								GOSUB s4_load_sample

							ENDIF
							IF syn4_rnd_txt = 9

								$s4_print = &SYN4_AJ	// You realise how much this paint job cost?
								s4_audio = SOUND_SYN4_AJ
								GOSUB s4_load_sample

							ENDIF
							IF syn4_rnd_txt = 10

								$s4_print = &SYN4_AK	// Back off, fool!
								s4_audio = SOUND_SYN4_AK
								GOSUB s4_load_sample

							ENDIF
							IF syn4_rnd_txt = 11

								$s4_print = &SYN4_AL	// I got money, have it!
								s4_audio = SOUND_SYN4_AL
								GOSUB s4_load_sample

								syn4_rnd_txt = 0

							ENDIF

							TIMERA = 0

							s4_TIMERC = 0

							CLEAR_CAR_LAST_DAMAGE_ENTITY s4_limo[2]

						ENDIF

					ENDIF

				ENDIF

			ENDIF

		ENDIF

		// ****************************************************************************************************
		// *																								  *
		// *                                         Spawn the phone      									  *
		// *																								  *
	 	// ****************************************************************************************************

	  	IF IS_CHAR_DEAD syn4_jizzy
	  	AND s4_phone_is_spawned = 0 

			IF DOES_BLIP_EXIST syn4_jizzy_blip

				REMOVE_BLIP syn4_jizzy_blip

			ENDIF

			REQUEST_MODEL mobile1993b

			WHILE NOT HAS_MODEL_LOADED mobile1993b
				WAIT 0
			ENDWHILE

			LVAR_FLOAT s4_z
			
			GET_GROUND_Z_FOR_3D_COORD off_X off_Y off1_X s4_z

			s4_z = s4_z + 0.5
						
			CREATE_OBJECT_NO_OFFSET mobile1993b off_X off_Y s4_z s4_the_phone

			ADD_BLIP_FOR_OBJECT s4_the_phone s4_blip_for_the_phone 

			PRINT_NOW ( SYN4_33 ) 4000 1 // ~s~Now collect the phone

			s4_phone_is_spawned = 1
	  	
	  	ENDIF	

		// ****************************************************************************************************
		// *																								  *
		// *                                       Draw the phones corona									  *
		// *																								  *
	 	// ****************************************************************************************************

	   	IF s4_phone_is_spawned = 1

			DRAW_CORONA off_X off_Y s4_z 0.2 CORONATYPE_TORUS FLARETYPE_NONE 200 200 200	

		ENDIF

		// ****************************************************************************************************
		// *																								  *
		// *                                       Collecting the phone  									  *
		// *																								  *
	 	// ****************************************************************************************************

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer off_X off_Y off1_X 1.2 1.2 2.0 FALSE
		AND s4_phone_is_spawned = 1
		AND NOT IS_CHAR_IN_ANY_CAR scplayer
		
			CLEAR_PRINTS

			DELETE_OBJECT s4_the_phone

			IF DOES_BLIP_EXIST s4_blip_for_the_phone

				REMOVE_BLIP s4_blip_for_the_phone

			ENDIF

			IF DOES_BLIP_EXIST syn4_jizzy_blip

				REMOVE_BLIP syn4_jizzy_blip

			ENDIF

			PRINT_NOW ( SYN4_11 ) 2000 1 // Phone collected!

			TIMERB = 0

			s4_phone_is_spawned = 2

		ENDIF

		IF s4_phone_is_spawned = 2
		AND TIMERB > 2000
		AND	NOT IS_CHAR_STUCK_UNDER_CAR	scplayer
			
			GOSUB syn4_set_camera
			
			s4_use_phone:

			REQUEST_MODEL cellphone

			WHILE NOT HAS_MODEL_LOADED cellphone
				WAIT 0
			ENDWHILE
			
			CLEAR_WANTED_LEVEL player1
	
			IF NOT IS_CHAR_DEAD scplayer

				CLEAR_CHAR_TASKS scplayer

				TASK_USE_MOBILE_PHONE scplayer TRUE
				
			ENDIF

			WAIT 2000

			GOSUB syn4_restore_camera

			syn4_ready = 2

			TIMERB = 0

		ENDIF

	ENDIF

	IF syn4_ready = 2

		IF IS_BUTTON_PRESSED PAD1 TRIANGLE

			s4_txt_display = 6

		    s4_txt_display = 6

		ENDIF

		IF s4_playing = 2
		AND s4_txt_display = 0

			IF NOT IS_CHAR_DEAD scplayer

				START_CHAR_FACIAL_TALK scplayer 10000

			ENDIF

			$s4_print = &MCES11A	// Yo Cesar!
			s4_audio = SOUND_MCES11A
			GOSUB s4_load_sample

			s4_txt_display = 1

		ENDIF

		IF s4_playing = 2
		AND s4_txt_display = 1
													
			IF NOT IS_CHAR_DEAD scplayer

				STOP_CHAR_FACIAL_TALK scplayer

			ENDIF

			$s4_print = &MCES11B	// Hey, dude, whassup?
			s4_audio = SOUND_MCES11B
			GOSUB s4_load_sample

			s4_txt_display = 2

		ENDIF

		IF s4_playing = 2
		AND s4_txt_display = 2

			IF NOT IS_CHAR_DEAD scplayer

				START_CHAR_FACIAL_TALK scplayer 10000

			ENDIF

			$s4_print = &MCES11C	// Listen; I need you to meet me at Pier 69.
			s4_audio = SOUND_MCES11C
			GOSUB s4_load_sample

			s4_txt_display = 3

		ENDIF

		IF s4_playing = 2
		AND s4_txt_display = 3
											
			IF NOT IS_CHAR_DEAD scplayer

				STOP_CHAR_FACIAL_TALK scplayer

			ENDIF

			$s4_print = &MCES11D	// We're going to take down the Loco Syndicate.
			s4_audio = SOUND_MCES11D
			GOSUB s4_load_sample

			s4_txt_display = 4

		ENDIF

		IF s4_playing = 2
		AND s4_txt_display = 4

			$s4_print = &MCES11E	// Ok, holmes. You need some help there?
			s4_audio = SOUND_MCES11E
			GOSUB s4_load_sample

			s4_txt_display = 5

		ENDIF


		IF s4_playing = 2
		AND s4_txt_display = 5

			IF NOT IS_CHAR_DEAD scplayer

				START_CHAR_FACIAL_TALK scplayer 10000

			ENDIF

			$s4_print = &MCES11F	// No, dude, I got it covered!
			s4_audio = SOUND_MCES11F
			GOSUB s4_load_sample

			s4_txt_display = 6

		ENDIF
		
		IF s4_playing = 2
		AND s4_txt_display = 6
												
			IF NOT IS_CHAR_DEAD scplayer

				STOP_CHAR_FACIAL_TALK scplayer

			ENDIF
			
			IF NOT IS_CHAR_DEAD scplayer

				TASK_USE_MOBILE_PHONE scplayer FALSE
				
			ENDIF

			GOSUB syn4_restore_camera
			 
			GOTO mission_synd4_passed

		ENDIF

	ENDIF

ENDWHILE

GOTO mission_synd4_failed

// ************************************************************************************************************
// *																						 				  *
// *                                   			   END MAIN LOOP				 							  *
// *																									      *
// ************************************************************************************************************

// Mission synd4 failed

mission_synd4_failed:

	IF DOES_BLIP_EXIST syn4_jizzy_blip
		REMOVE_BLIP syn4_jizzy_blip
	ENDIF 
	 
	IF DOES_BLIP_EXIST syn4_sniper_blip
		REMOVE_BLIP syn4_sniper_blip
	ENDIF 
	 
	IF DOES_BLIP_EXIST the_phone_blip
		REMOVE_BLIP the_phone_blip
	ENDIF 

	PRINT_BIG M_FAIL 5000 1
	
	IF syn4_mission_stat = 1
	OR syn4_in_pdomes = 1

		GOSUB syn4_fade_out

		IF NOT IS_CHAR_DEAD scplayer
			syn4_mission_stat = 1

			SET_AREA_VISIBLE 0

			REQUEST_COLLISION -2623.8054 1409.8163

			SET_CHAR_AREA_VISIBLE scplayer 0

			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer -2624.4607 1410.7056 6.1015 

			SET_CHAR_HEADING scplayer 176.8840 
			
			LOAD_SCENE -2624.4607 1410.7056 6.1015

		ENDIF
		
		GOSUB syn4_fade_in
	ENDIF

	SET_CAMERA_BEHIND_PLAYER


RETURN

// ************************************************************************************************************
// *																						 				  *
// *                                   			   Passed Mission				 							  *
// *																									      *
// ************************************************************************************************************

mission_synd4_passed:

	CLEAR_PRINTS

	IF DOES_BLIP_EXIST syn4_jizzy_blip
		REMOVE_BLIP syn4_jizzy_blip
	ENDIF 
	 
	IF DOES_BLIP_EXIST syn4_sniper_blip
		REMOVE_BLIP syn4_sniper_blip
	ENDIF 
	 																		
	IF DOES_BLIP_EXIST the_phone_blip
		REMOVE_BLIP the_phone_blip
	ENDIF 

	flag_synd_mission_counter ++

	PRINT_WITH_NUMBER_BIG ( M_PASSS ) 12000 5000 1 //"Mission Passed!" //100 being the amount of cash
	ADD_SCORE player1 12000//amount of cash
	REMOVE_BLIP synd_contact_blip

	AWARD_PLAYER_MISSION_RESPECT 30 //amount of respect

	PLAY_MISSION_PASSED_TUNE 1

	CLEAR_WANTED_LEVEL player1

	//PLAY_MISSION_PASSED_TUNE 1
	REGISTER_MISSION_PASSED ( SYND_4 )
	PLAYER_MADE_PROGRESS 1
	//START_NEW_SCRIPT synd4_mission_loop

	REMOVE_BLIP garage_contact_blip                                                                   
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT -1717.05 1280.91 6.23 garage_blip_icon garage_contact_blip


RETURN 		

// ************************************************************************************************************
// *																						 				  *
// *                                   			  Clean-up Mission				 							  *
// *																									      *
// ************************************************************************************************************

mission_cleanup_synd4:
	
	IF NOT IS_CHAR_DEAD scplayer

		STOP_CHAR_FACIAL_TALK scplayer

	ENDIF

	IF NOT IS_CHAR_DEAD syn4_jizzy

		REMOVE_CHAR_ELEGANTLY syn4_jizzy

	ENDIF

	SWITCH_ENTRY_EXIT PDOMES FALSE

	SWITCH_ENTRY_EXIT PDOMES2 FALSE

	SET_WANTED_MULTIPLIER 1.0

	UNMARK_ALL_ROAD_NODES_AS_DONT_WANDER

	IF DOES_BLIP_EXIST s4_blip_for_the_phone
		REMOVE_BLIP s4_blip_for_the_phone
	ENDIF

	IF DOES_BLIP_EXIST s4_start_blip
		REMOVE_BLIP s4_start_blip
	ENDIF

	IF DOES_BLIP_EXIST syn4_jizzy_blip
		REMOVE_BLIP syn4_jizzy_blip
	ENDIF 
	 
	IF DOES_BLIP_EXIST syn4_sniper_blip
		REMOVE_BLIP syn4_sniper_blip
	ENDIF 
	 
	IF DOES_BLIP_EXIST the_phone_blip
		REMOVE_BLIP the_phone_blip
	ENDIF

	IF DOES_BLIP_EXIST s4_door_blip
		REMOVE_BLIP s4_door_blip 
	ENDIF

	IF DOES_BLIP_EXIST syn4_skylight
		REMOVE_BLIP syn4_skylight
	ENDIF

	GET_GAME_TIMER timer_mobile_start // Used to reset the mobile phone timer so it doesn't ring immediately after the mission
		
	MARK_MODEL_AS_NO_LONGER_NEEDED silenced
	MARK_MODEL_AS_NO_LONGER_NEEDED bmydrug
	MARK_MODEL_AS_NO_LONGER_NEEDED hmydrug
	MARK_MODEL_AS_NO_LONGER_NEEDED BFYPRO
	MARK_MODEL_AS_NO_LONGER_NEEDED HFYRI
	MARK_MODEL_AS_NO_LONGER_NEEDED cellphone

	MARK_MODEL_AS_NO_LONGER_NEEDED micro_uzi
	MARK_MODEL_AS_NO_LONGER_NEEDED mobile1993b
	MARK_MODEL_AS_NO_LONGER_NEEDED warehouse_door2b
	MARK_MODEL_AS_NO_LONGER_NEEDED stretch
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYDJ

	MARK_MODEL_AS_NO_LONGER_NEEDED stretch
	MARK_MODEL_AS_NO_LONGER_NEEDED broadway
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYBOUN
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYBOUN
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYSGRD

	MARK_MODEL_AS_NO_LONGER_NEEDED VWFYWAI
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
	MARK_MODEL_AS_NO_LONGER_NEEDED pizzaboy
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYPIZZ
	MARK_MODEL_AS_NO_LONGER_NEEDED VHFYST3

	IF NOT IS_CHAR_DEAD scplayer
		CLEAR_WANTED_LEVEL player1 
	ENDIF

	REMOVE_ANIMATION SWAT

	UNLOAD_SPECIAL_CHARACTER 1

	flag_player_on_mission = 0

	RELEASE_WEATHER

	MISSION_HAS_FINISHED

RETURN

syn4_set_camera:

	IF NOT IS_CHAR_DEAD scplayer

		CLEAR_PRINTS

		SWITCH_WIDESCREEN ON
		SET_PLAYER_CONTROL player1 OFF

	ENDIF

RETURN

syn4_restore_camera:

	IF NOT IS_CHAR_DEAD scplayer

		CLEAR_PRINTS

		SET_CAMERA_BEHIND_PLAYER
		SWITCH_WIDESCREEN OFF
		SET_PLAYER_CONTROL player1 ON
		RESTORE_CAMERA_JUMPCUT

	ENDIF
 
RETURN

syn4_fade_out:

	DO_FADE 500 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

syn4_fade_in:

	DO_FADE 500 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

// ************************************************************************************************************
// *																						 				  *
// *                                   			  Keyboard Shortcuts			 							  *
// *																									      *
// ************************************************************************************************************

syn4_keys:

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_A
	
		IF NOT IS_CHAR_DEAD scplayer  

		   	SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer -2623.9771 1404.8123 6.1110 
			
		   	SET_CHAR_HEADING scplayer 352.6280

		ENDIF
 
 	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_Q
	
		IF NOT IS_CAR_DEAD s4_limo[2]

			FREEZE_CAR_POSITION s4_limo[2] TRUE

		ENDIF
 
 	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D

		VAR_INT the_area_name

		GET_AREA_VISIBLE the_area_name

		VIEW_INTEGER_VARIABLE the_area_name AREA_NAME

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_F

		CREATE_BIRDS -2616.1086 1408.6289 6.5000 -2618.4976 1415.8663 6.5000 3 BIRDTYPE_SEAGULL

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_G
	
		IF NOT IS_CHAR_DEAD scplayer  

		   	SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer -2661.1917 1426.1277 22.8906 
			
		   	SET_CHAR_HEADING scplayer 188.7666 

			SET_CAMERA_BEHIND_PLAYER

		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_E

		IF NOT IS_CHAR_DEAD scplayer

			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer -2661.4929 1403.0674 905.2770    

			SET_CHAR_HEADING scplayer 85.1708 

			SET_CAMERA_BEHIND_PLAYER

		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_K

		IF NOT IS_CHAR_DEAD scplayer

			VIEW_INTEGER_VARIABLE s4_j_health HEALTH

		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_Z

		GOTO s4_use_phone

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_W

		IF NOT IS_CAR_DEAD s4_limo[2]

			SET_CAR_HEALTH s4_limo[2] 200

		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_B

		IF NOT IS_CHAR_DEAD syn4_jizzy

			SET_CHAR_HEALTH syn4_jizzy 1

		ENDIF

		GOSUB syn4_set_camera
									   
		SET_CHAR_COORDINATES_DONT_WARP_GANG syn4_jizzy -2659.3823 1530.2482 53.9453

		SET_CHAR_HEADING syn4_jizzy	0.0000

		SET_FIXED_CAMERA_POSITION -2655.0933 1543.8457 55.7815 0.0 0.0 0.0 // Jizzy on the phone
		POINT_CAMERA_AT_POINT -2655.4446 1542.9185 55.6520  JUMP_CUT

		IF NOT IS_CHAR_DEAD syn4_jizzy

			CLEAR_CHAR_TASKS_IMMEDIATELY syn4_jizzy
			
	 		TASK_JUMP syn4_jizzy FALSE

		ENDIF

		WAIT 1000

		SET_TIME_SCALE 0.4

		IF NOT IS_CHAR_DEAD syn4_jizzy
			  
			SET_FIXED_CAMERA_POSITION -2630.0557 1557.2021 18.3729 0.0 0.0 0.0 // Jizzy on the phone
			POINT_CAMERA_AT_CHAR syn4_jizzy FIXED JUMP_CUT

		ENDIF
			
		TIMERB = 0
		WHILE TIMERB < 750
			WAIT 0
			IF IS_BUTTON_PRESSED PAD1 CROSS
				GOTO s4_save_the_deer2
			ENDIF
		ENDWHILE 

		SET_TIME_SCALE 1.0
			
		TIMERB = 0
		WHILE TIMERB < 1500
			WAIT 0
			IF IS_BUTTON_PRESSED PAD1 CROSS
				GOTO s4_save_the_deer2
			ENDIF
		ENDWHILE 

		s4_save_the_deer2:

		GOSUB syn4_restore_camera

		PRINT_NOW ( SYN4_29 ) 4000 1 // ~r~You let Jizzy escape with the phone.

	ENDIF

RETURN

s4_load_sample:

	CLEAR_MISSION_AUDIO 1

	LOAD_MISSION_AUDIO 1 s4_audio

	s4_playing = 0

RETURN

s4_play_sample:

	IF HAS_MISSION_AUDIO_LOADED 1
	AND s4_playing = 0

		PLAY_MISSION_AUDIO 1

		PRINT_NOW ( $s4_print ) 10000 1  

		s4_playing = 1

	ENDIF

	IF HAS_MISSION_AUDIO_FINISHED 1
	AND s4_playing = 1

		CLEAR_MISSION_AUDIO 1	

		CLEAR_THIS_PRINT $s4_print

		s4_playing = 2

	ENDIF
	
RETURN

}

/* 



{------------------------ SYNDICATE 4 --------------------------}

[SYN4_02:SYN4]
~r~Jizzy's ~s~escaping with the phone, hunt him down!

[SYN4_04:SYN4]
~s~Sneak down to ~y~Jizzy~s~ silently, you need to get that number.

[SYN4_05:SYN4]
~r~Jizzy's ~s~escaping, Hunt him down!

[SYN4_06:SYN4]
~r~You killed Jizzy before he made the call.

[SYN4_08:SYN4]
~r~You scared Jizzy before he made the call.

[SYN4_11:SYN4]
Phone collected!
							 
[SYN4_12:SYN4]
~s~Use the ~y~Skylight ~s~to get into the club!

[SYN4_13:SYN4]
We can't let you in man, private function tonight.

[SYN4_17:SYN4]
~r~You were spotted.

[SYN4_18:SYN4]
The guard has raised the alarm, be extra careful!

[SYN4_19:SYN4]
~s~Find a way past the ~r~guard~s~!

[SYN4_20:SYN4]
~s~Go pay Jizzy a visit at the ~y~Pleasure Domes club~s~!

[SYN4_28:SYN4]
~s~If the ~r~guards ~s~spot you, they'll call in back-up.

[SYN4_29:SYN4]
~r~You let Jizzy escape with the phone.

[SYN4_30:SYN4]
~s~Don't let the patroling guards spot you!

[SYN4_31:SYN4]
Limo Damage :

[SYN4_32:SYN4]
~r~You blew up the phone.

[SYN4_33:SYN4]			   
~s~Now collect the ~g~phone~s~.

[SYN4_CA:SYN4]
Yo Cesar!

[SYN4_CB:SYN4]
Hey, dude, whassup?

[SYN4_CC:SYN4]
Listen; I need you to meet me at Pier 69. 

[SYN4_CD:SYN4]
We're going to take down the Loco Syndicate.

[SYN4_CE:SYN4]
Ok, holmes. You need some help there?

[SYN4_CF:SYN4]
No, dude, I got it covered!




*/



