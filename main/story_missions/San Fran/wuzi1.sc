MISSION_START
// *****************************************************************************************
// **************************************** wuzi1 ******************************************
// *****************************************************************************************  
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME wuzi1
// Mission start stuff
GOSUB mission_start_wuzi1

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_wuzi1_failed
ENDIF

GOSUB mission_cleanup_wuzi1

MISSION_END



{ 
// Variables for mission

LVAR_INT wz1_ped_decisions wz1_wuzi wz1_mission_fail wz1_wuzi_alive wz1_scene_flag	wz1_enter_cut wz1_button_pressed wz1_time
LVAR_INT wz1_cut_flag 
LVAR_INT journey_audio_flag

//objects
LVAR_INT wz1_keypad wz1_bin[4] wz1_chinaGar[2] wz1_lanterns[5] wz1_table[7] wz1_stalls[6] wz1_vent[2]

//flags
LVAR_INT wz1_flag wuzi_in_group	check_wuzi_in_group	wz1_cutscene_flag wz1_mini_flag 
LVAR_INT wz1_cutscene_skipped wz1_escape_flag wz1_add_blip wz1_trigger_rollers wz1_dead_tongs
LVAR_INT wz1_car_alive[2] wz1_sniper_flag wz1_player_in_car wz1_advance_flag wz1_advance_time wz1_tong_advanced[4]
LVAR_INT wz1_exit wz1_door_opens wz1_tong_rolls_out	wz1_create_tong_wave3 wz1_dead_tong_wave3 wz1_calculate_wuzi_health
LVAR_INT wz1_next_tong_time	wz1_r
//vehicles
LVAR_INT wz1_burncar1 wz1_burncar2 wz1_tongcar[3] wz1_getaway_car wz1_carattack[2]

//blips

LVAR_INT wz1_goto_blip wz1_wuzi_blip wz1_car_blip[2] wz1_tong_blip[18]

// generic vars
LVAR_INT wz1_var1 wz1_i wz1_int

//sequences
LVAR_INT wz1_seq wz1_seq2

//chars
LVAR_INT wz1_triad[4] wz1_tong[18] wz1_mediator

//groups
LVAR_INT wz1_tong_group

//other
LVAR_INT wz1_task_progress wz1_cut_time wz1_escape_timer wz1_health wz1_random 
LVAR_INT wz1_tong_group_attack_time wz1_this_tong_attack_time[8] wz_i wz1_tong_target[18]
VAR_INT wz1_health_bar 
LVAR_INT wz1_upper_check wz1_threats_in_area wz1_timer wz1_dead_tong wz1_task_status wz1_players_weapon
//wz1_sniper_aim_time
//groups
LVAR_INT wz1_tonggroup

//decisions
LVAR_INT wz1_group_dec

//events
LVAR_INT wz1_event wz1_tong_event

//coords
LVAR_FLOAT wz1_tong_node_x[4] wz1_tong_node_y[4] wz1_tong_node_z[4] wz1_tong_advance_x[4] wz1_tong_advance_y[4] wz1_tong_advance_z[4] 
LVAR_FLOAT wz1_float1 wz1_float2 wz1_float3 wuzi_x wuzi_y wuzi_z

LVAR_INT temp_audio	wuzi_insult[6]




// ****************************************Mission Start************************************
mission_start_wuzi1:


temp_audio = 15
wuzi_insult[0] = 33
wuzi_insult[1] = 38
wuzi_insult[2] = 39
wuzi_insult[3] = 40
wuzi_insult[4] = 41
wuzi_insult[5] = 42
lose_face_message_printed = 0

//SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 TRUE

	LOAD_MISSION_TEXT wuzi1

	CLEAR_THIS_PRINT M_FAIL
	flag_player_on_mission = 1
	REGISTER_MISSION_GIVEN

	IF wz1_flag = 99
		CREATE_CHAR PEDTYPE_GANG_GROVE SPECIAL01 2752.3408 -1948.8669 16.3125 wz1_wuzi
		CREATE_CAR ESPERANT 0.0 0.0 0.0 wz1_getaway_car
		CREATE_GROUP DEFAULT_TASK_ALLOCATOR_STAND_STILL wz1_tong_group
		ADD_BLIP_FOR_CHAR wz1_wuzi wz1_goto_blip
		ADD_BLIP_FOR_CHAR wz1_wuzi wz1_car_blip[0]
		ADD_BLIP_FOR_CHAR wz1_wuzi wz1_wuzi_blip
	ENDIF

	


	wz1_mission_loop:

		WAIT 0

		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	    	GOTO mission_wuzi1_passed  
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_X
	    	SET_CHAR_COORDINATES scplayer -2175.7351 653.6188 48.4453
			IF NOT IS_CHAR_DEAD wz1_wuzi 
				SET_CHAR_COORDINATES wz1_wuzi -2176.7351 653.6188 48.4453
			ENDIF
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Z
	    	SET_CHAR_COORDINATES scplayer -2247.7490 649.4075 48.4375
			IF NOT IS_CHAR_DEAD wz1_wuzi 
				SET_CHAR_COORDINATES wz1_wuzi -2246.7490 649.4075 48.4375
			ENDIF
		ENDIF





		SWITCH wz1_flag

			CASE 0
				// Mocap cut scene for Wuzi 
				GOSUB wz1_cutscene_1
				wz1_flag = 1
			BREAK

			CASE 1
				GOSUB wz1_initialise_stuff
				wz1_flag = 2
			BREAK


			CASE 2
				//Take Wuzi to China Town meet 
				GOSUB wz1_scene1
			BREAK

			CASE 3
				//Wuzi and player discover the meet has been ambushed
				GOSUB wz1_cutscene_2
			BREAK

			CASE 4
				//Get the car
				GOSUB wz1_scene2
			BREAK

			CASE 5
				//Sniper and more Tong turn up.
				GOSUB wz1_cutscene_3
			BREAK
//
			CASE 6
				//Drive back to hub
				GOSUB wz1_Scene3
			BREAK
//
			CASE 7
				//Player enters car with Wuzi and rams attacking Tong cars.
				GOSUB wz1_cutscene_4
			BREAK
//
			// destroy attacking Tong cars
			CASE 8
				GOSUB wz1_Scene4
			BREAK

			CASE 9
				//Wuzi leaves car - fade - mission passed
				GOSUB wz1_cutscene_5
			BREAK

			CASE 10
				GOTO mission_wuzi1_passed
			BREAK



		ENDSWITCH

		GOSUB wz1_fail_checks
		GOSUB wz1_mission_checks
		GOSUB wuzi1_audio

		// controls the attacking tongs

		GET_CHAR_COORDINATES scplayer player_x player_y player_z
		GET_CHAR_COORDINATES scplayer wuzi_x wuzi_y wuzi_z


		IF wz1_flag = 2
		OR wz1_flag = 4
		OR wz1_flag = 6
			GOSUB tong_events
			GOSUB wuzi_comments
			GOSUB wuzi_health
			GOSUB tong_threat_scan
		ENDIF



		IF wz1_mission_fail = 1
			GOTO mission_wuzi1_failed
		ENDIF

	GOTO wz1_mission_loop

RETURN


wz1_fail_checks:

	IF wz1_wuzi_alive = 1
		IF IS_CHAR_DEAD wz1_wuzi
			CLEAR_PRINTS
			PRINT WZ1_40 5000 1
			wz1_mission_fail = 1
			GOTO mission_wuzi1_failed
		ENDIF
	ENDIF

RETURN

wz1_mission_checks:


	IF check_wuzi_in_group = 1
		IF wuzi_in_group = 1
			IF NOT IS_CHAR_DEAD wz1_wuzi
				IF NOT IS_GROUP_MEMBER wz1_wuzi players_group 
					wuzi_in_group = 0
					REMOVE_BLIP wz1_wuzi_blip
					ADD_BLIP_FOR_CHAR wz1_wuzi wz1_wuzi_blip
					SET_BLIP_AS_FRIENDLY wz1_wuzi_blip TRUE
					CHANGE_BLIP_DISPLAY wz1_goto_blip NEITHER
					CLEAR_PRINTS
					PRINT WZ1_41 4000 1
				ENDIF
			ENDIF
		ENDIF
		IF wuzi_in_group = 0
			IF NOT IS_CHAR_DEAD wz1_wuzi
				IF IS_GROUP_MEMBER wz1_wuzi players_group
					wuzi_in_group = 1
				ELSE
					IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer wz1_wuzi 6.0 6.0 FALSE
						MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
						SET_GROUP_MEMBER players_group wz1_wuzi
						LISTEN_TO_PLAYER_GROUP_COMMANDS wz1_wuzi FALSE
						REMOVE_BLIP wz1_wuzi_blip
						CHANGE_BLIP_DISPLAY wz1_goto_blip BOTH
						wuzi_in_group = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF


RETURN

 

wz1_initialise_stuff:

	wz1_upper_check = 4
	LOAD_SPECIAL_CHARACTER 1 WUZIMU
	REQUEST_MODEL MICRO_UZI
	REQUEST_MODEL DNB1
	REQUEST_MODEL DNB2
	REQUEST_MODEL DNB3
	REQUEST_MODEL TRIADA
	REQUEST_MODEL TRIADB
	REQUEST_MODEL SNIPER
	REQUEST_MODEL SENTINEL
	REQUEST_MODEL SANCHEZ
	REQUEST_MODEL OMOST
	REQUEST_ANIMATION wuzi
	REQUEST_CAR_RECORDING 169
	REQUEST_CAR_RECORDING 139

	WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
	OR NOT HAS_MODEL_LOADED MICRO_UZI
	OR NOT HAS_MODEL_LOADED SNIPER
	OR NOT HAS_MODEL_LOADED DNB1
	OR NOT HAS_MODEL_LOADED DNB2
	OR NOT HAS_MODEL_LOADED DNB3
		WAIT 0
	ENDWHILE

	WHILE NOT HAS_MODEL_LOADED SENTINEL
	OR NOT HAS_MODEL_LOADED SANCHEZ
	OR NOT HAS_MODEL_LOADED TRIADA
	OR NOT HAS_MODEL_LOADED TRIADB
	OR NOT HAS_MODEL_LOADED OMOST
		WAIT 0
	ENDWHILE

	WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 169
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 139
		WAIT 0
	ENDWHILE

 	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1
 	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION2 PEDTYPE_PLAYER1
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_MISSION2
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1 PEDTYPE_MISSION1
			
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH wz1_ped_decisions

	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE wz1_ped_decisions EVENT_GUN_AIMED_AT
	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE wz1_ped_decisions EVENT_DAMAGE
	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE wz1_ped_decisions EVENT_SHOT_FIRED_WHIZZED_BY
	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE wz1_ped_decisions EVENT_SHOT_FIRED
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE wz1_ped_decisions EVENT_GUN_AIMED_AT TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 0 1	
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE wz1_ped_decisions EVENT_DAMAGE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 0 1

	IF DOES_OBJECT_EXIST china_town_gate
		DELETE_OBJECT china_town_gate
	ENDIF


	CREATE_OBJECT_NO_OFFSET ct_gatexr -2179.353 661.232 50.214 china_town_gate
	SET_OBJECT_COLLISION_DAMAGE_EFFECT china_town_gate FALSE

	MARK_MODEL_AS_NO_LONGER_NEEDED ct_gatexr

	CREATE_OBJECT_NO_OFFSET ct_stall1 -2213.413 640.908 48.43 wz1_stalls[5]
	SET_OBJECT_HEADING wz1_stalls[5] 90.0

	CREATE_OBJECT_NO_OFFSET ct_table -2201.436 647.109 48.413 wz1_table[2]

/*
	CREATE_OBJECT_NO_OFFSET ct_lanterns -2195.99 643.712 57.0 wz1_lanterns[0]
	CREATE_OBJECT_NO_OFFSET ct_lanterns -2214.6907 642.7629 57.0581 wz1_lanterns[1]
	CREATE_OBJECT_NO_OFFSET ct_lanterns -2202.0398 643.3145 56.2913 wz1_lanterns[2]
	CREATE_OBJECT_NO_OFFSET ct_lanterns -2192.4521 643.5444 57.7205 wz1_lanterns[3]
	CREATE_OBJECT_NO_OFFSET ct_lanterns -2184.5847 643.5825 58.0148 wz1_lanterns[4]

	CREATE_OBJECT_NO_OFFSET ct_stall2 -2185.327 638.335 50.874 wz1_stalls[0]
	SET_OBJECT_HEADING wz1_stalls[0] 90.0

	CREATE_OBJECT_NO_OFFSET ct_stall2 -2201.121 638.335 50.874 wz1_stalls[1]
	SET_OBJECT_HEADING wz1_stalls[1] 90.0

	CREATE_OBJECT_NO_OFFSET ct_stall2 -2208.512 632.216 50.874 wz1_stalls[2]

	CREATE_OBJECT_NO_OFFSET ct_stall1 -2193.073 638.112 48.43 wz1_stalls[3]
	SET_OBJECT_HEADING wz1_stalls[3] 90.0

	CREATE_OBJECT_NO_OFFSET ct_stall1 -2193.073 646.033 48.43 wz1_stalls[4]



	CREATE_OBJECT_NO_OFFSET ct_table -2208.478 629.487 48.413 wz1_table[0]

	CREATE_OBJECT_NO_OFFSET ct_table -2208.478 634.959 48.413 wz1_table[1]

	

	CREATE_OBJECT_NO_OFFSET ct_table -2203.851 638.328 48.413 wz1_table[3]
	SET_OBJECT_HEADING wz1_table[3] 270.0

	CREATE_OBJECT_NO_OFFSET ct_table -2198.379 638.328 48.413 wz1_table[4]
	SET_OBJECT_HEADING wz1_table[4] 90.0

	CREATE_OBJECT_NO_OFFSET ct_table -2188.038 638.328 48.413 wz1_table[5]
	SET_OBJECT_HEADING wz1_table[5] 270.0

	CREATE_OBJECT_NO_OFFSET ct_table -2182.566 638.328 48.413 wz1_table[6]					
	SET_OBJECT_HEADING wz1_table[6] 90.0

	CREATE_OBJECT_NO_OFFSET ct_vent -2172.085 638.053 53.117 wz1_vent[0]
	SET_OBJECT_HEADING wz1_vent[0] 90.0

	CREATE_OBJECT_NO_OFFSET ct_vent -2220.204 638.053 53.117 wz1_vent[1]
*/


	CREATE_OBJECT_NO_OFFSET chinaTgarageDoor -2184.415 711.556 54.523 wz1_chinaGar[0]
	CREATE_OBJECT_NO_OFFSET chinaTgarageDoor -2178.87 711.556 54.523 wz1_chinaGar[1]

	CREATE_OBJECT_NO_OFFSET tmp_bin -2183.968 647.055 49.185 wz1_bin[0]
	CREATE_OBJECT_NO_OFFSET tmp_bin -2172.813 654.019 49.185 wz1_bin[1]
	CREATE_OBJECT_NO_OFFSET tmp_bin -2172.813 649.293 49.185 wz1_bin[2]
	SET_OBJECT_HEADING wz1_bin[1] 270.0
	SET_OBJECT_HEADING wz1_bin[2] 270.0
 //	CREATE_OBJECT_NO_OFFSET kmb_keypad -2172.099 660.187 49.784	wz1_keypad
//	SET_OBJECT_HEADING wz1_keypad 90.0

	SET_OBJECT_HEADING china_town_gate 0.0

	SET_WANTED_MULTIPLIER 0.2
	ALTER_WANTED_LEVEL Player1 0

	check_wuzi_in_group = 1
	SWITCH_EMERGENCY_SERVICES OFF


	wz1_escape_flag = 99

	wz1_tong_node_x[0] = -2199.0
	wz1_tong_node_y[0] = 645.4869
	wz1_tong_node_z[0] = 48.4453 

	wz1_tong_advance_x[0] = -2194.6797 
	wz1_tong_advance_y[0] = 646.1572 
	wz1_tong_advance_z[0] = 48.4453 

	wz1_tong_node_x[1] = -2199.0
	wz1_tong_node_y[1] = 640.8279 
	wz1_tong_node_z[1] = 48.4453 
							
	wz1_tong_advance_x[1] = -2194.0432
	wz1_tong_advance_y[1] = 638.7194  
	wz1_tong_advance_z[1] = 48.4453 

	wz1_tong_node_x[2] = -2195.8389
	wz1_tong_node_y[2] = 635.4390 
	wz1_tong_node_z[2] = 48.4453  
							
	wz1_tong_advance_x[2] = -2189.3674
	wz1_tong_advance_y[2] = 636.7801 
	wz1_tong_advance_z[2] = 48.4453  

	wz1_tong_node_x[3] = -2213.0051
	wz1_tong_node_y[3] = 638.9453 
	wz1_tong_node_z[3] = 48.4459 
							
	wz1_tong_advance_x[3] = -2204.7781
	wz1_tong_advance_y[3] = 639.4221 
	wz1_tong_advance_z[3] = 48.4453




		CREATE_CHAR PEDTYPE_MISSION2 SPECIAL01 -2155.4622 645.8904 51.3516 wz1_wuzi
//		LISTEN_TO_PLAYER_GROUP_COMMANDS wz1_wuzi FALSE
		audio_actor[2] = wz1_wuzi

		wz1_wuzi_alive = 1

		GIVE_WEAPON_TO_CHAR wz1_wuzi WEAPONTYPE_MICRO_UZI 5000
		SET_CURRENT_CHAR_WEAPON wz1_wuzi WEAPONTYPE_UNARMED

		SET_CHAR_HEADING wz1_wuzi 180.0
		SET_CHAR_HEALTH wz1_wuzi 250
		SET_CHAR_MAX_HEALTH wz1_wuzi 250
		wz1_health = 250
		SET_CHAR_SUFFERS_CRITICAL_HITS wz1_wuzi FALSE

		UNLOAD_SPECIAL_CHARACTER 1

		ADD_BLIP_FOR_COORD -2244.9954 642.8972 48.4077 wz1_goto_blip
		

		IF NOT IS_CHAR_DEAD scplayer
			SET_CHAR_COORDINATES scplayer -2155.2659 645.0895 51.3516
			SET_CHAR_HEADING scplayer 270.0
		ENDIF

		IF NOT IS_CHAR_DEAD wz1_wuzi
			SET_ANIM_GROUP_FOR_CHAR wz1_wuzi blindman
			SET_CHAR_DECISION_MAKER wz1_wuzi wz1_ped_decisions
			SET_CHAR_NEVER_TARGETTED wz1_wuzi TRUE
			MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
			SET_GROUP_MEMBER players_group wz1_wuzi
//			LISTEN_TO_PLAYER_GROUP_COMMANDS wz1_wuzi FALSE
			wuzi_in_group = 1
		ENDIF

		SET_PLAYER_CONTROL player1 ON
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT

		LOAD_SCENE_IN_DIRECTION -2155.2659 645.0895 51.3516 270.0
		REQUEST_COLLISION -2155.0 645.0

		DO_FADE 1000 FADE_IN

		LOAD_SCENE -2155.2659 645.0895 51.3
		
		wz1_flag = 2
		wz1_cut_flag = 0
		
		
//		CLEAR_MISSION_AUDIO 1		
//		LOAD_MISSION_AUDIO 1 SOUND_FAR4_AA		
				








RETURN




// *****************************************************************************************
// ******************************** MOCAP cutscene *****************************************
// *****************************************************************************************


wz1_cutscene_1:



		LOAD_MISSION_TEXT WUZI1

		SET_PLAYER_CONTROL player1 OFF

		SET_AREA_VISIBLE 1


		LOAD_CUTSCENE WOOZI1A
		 
		WHILE NOT HAS_CUTSCENE_LOADED
		            WAIT 0
		ENDWHILE
		 
		START_CUTSCENE

		DO_FADE 1000 FADE_IN
		  
		WHILE NOT HAS_CUTSCENE_FINISHED
		            WAIT 0
		ENDWHILE

		IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
		    LVAR_INT skip_it
			skip_it = 1
		ENDIF

		DO_FADE 0 FADE_OUT

		WHILE GET_FADING_STATUS
		            WAIT 0
		ENDWHILE

		CLEAR_CUTSCENE

		IF skip_it = 0

				LOAD_CUTSCENE WOOZI1B
		 
				WHILE NOT HAS_CUTSCENE_LOADED
		            WAIT 0
				ENDWHILE
			 
				START_CUTSCENE

				DO_FADE 1000 FADE_IN
			  
				WHILE NOT HAS_CUTSCENE_FINISHED
		            WAIT 0
				ENDWHILE

				DO_FADE 0 FADE_OUT

				WHILE GET_FADING_STATUS
		            WAIT 0
				ENDWHILE

				CLEAR_CUTSCENE
		ENDIF

		SET_AREA_VISIBLE 0




		// ******************************************END OF CUTSCENE********************************

		

		SET_FADING_COLOUR 0 0 0
		WAIT 0


		 	
		

RETURN




// *****************************************************************************************
// ************************* Take wuzi to the Tong/Triad meet ******************************
// *****************************************************************************************

wz1_scene1:

//LVAR_INT audio_slot_played
//	IF audio_slot_played = 0
//		IF HAS_MISSION_AUDIO_LOADED 1
//			PLAY_MISSION_AUDIO 1
//			audio_slot_played = 1
//		ENDIF
//	ENDIF


	IF wz1_scene_flag = 0
		IF wz1_mini_flag = 0
			play_audio = 1
			play_audio_for = 2
			wz1_mini_flag = 1
			IF DOES_OBJECT_EXIST china_town_gate
				SET_OBJECT_ROTATION china_town_gate 0.0 0.0 330.0 
			ENDIF

		ENDIF
		IF wz1_mini_flag = 1
			IF play_audio = 0
				PRINT WZ1_1 8000 1
				wz1_mini_flag = 2
			ENDIF
		ENDIF
		IF wz1_mini_flag = 2
			GET_CHAR_COORDINATES scplayer player_x player_y player_z
			IF journey_audio_flag = 0
				IF player_y < 627.0
				OR player_y > 657.0
					journey_audio_flag = 1
				ENDIF
			ENDIF

			IF journey_audio_flag = 1
				IF play_audio = 0
//					IF NOT IS_CHAR_DEAD wz1_wuzi
//						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer wz1_wuzi 8.0 8.0 8.0 FALSE
							play_audio = 3
							play_audio_for = 2
							journey_audio_flag = 2
//						ENDIF
//					ENDIF
				ENDIF
			ENDIF

			IF journey_audio_flag = 2				
				IF player_x < -2164.0
					journey_audio_flag = 3
				ENDIF
			ENDIF	

			IF journey_audio_flag = 3
				IF play_audio = 0
//					IF NOT IS_CHAR_DEAD wz1_wuzi
//						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer wz1_wuzi 8.0 8.0 8.0 FALSE
							play_audio = 5
							play_audio_for = 3
							journey_audio_flag = 4
//						ENDIF
//					ENDIF
				ENDIF
			ENDIF

			IF journey_audio_flag = 4				
				IF player_y < 707.0
				AND player_y > 587.0
					journey_audio_flag = 5
				ENDIF
			ENDIF	

			IF journey_audio_flag = 5
				IF play_audio = 0
//					IF NOT IS_CHAR_DEAD wz1_wuzi
//						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer wz1_wuzi 8.0 8.0 8.0 FALSE
							play_audio = 9
							play_audio_for = 2
							journey_audio_flag = 6
//						ENDIF
//					ENDIF
				ENDIF
			ENDIF

			IF journey_audio_flag = 7
				IF TIMERA > wz1_cut_time
					TASK_LEAVE_ANY_CAR scplayer	
					journey_audio_flag = 8
				ENDIF
			ENDIF

			IF journey_audio_flag = 8
				IF NOT IS_CHAR_IN_ANY_CAR scplayer
					SET_PLAYER_CONTROL player1 ON
					journey_audio_flag = 11
					IF NOT IS_CHAR_DEAD wz1_wuzi				
						REMOVE_CHAR_FROM_GROUP wz1_wuzi						
					ENDIF
				ELSE
					GET_SCRIPT_TASK_STATUS scplayer TASK_LEAVE_ANY_CAR wz1_task_Status
					IF wz1_task_status = FINISHED_TASK
						TASK_LEAVE_ANY_CAR scplayer
					ENDIF
				ENDIF
			ENDIF

			LVAR_INT play_wuzi_arrive_message

			IF play_wuzi_arrive_message = 1
				IF audio_flag = 1
				AND play_audio = 0
					play_audio = 11
					play_audio_for = 2					
					play_wuzi_arrive_message = 2
				ENDIF
			ENDIF

			IF play_wuzi_arrive_message = 2
				IF audio_flag = 1
				AND play_audio = 0
					PRINT WZ1_B4 5000 1
					wz1_scene_flag = 1
					play_wuzi_arrive_message = 3					
				ENDIF	
			ENDIF

					

			IF journey_audio_flag < 7
				IF wuzi_in_group = 1
					IF NOT IS_CHAR_DEAD wz1_wuzi		
						IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2248.0771 651.3163 48.3815 4.0 4.0 4.0 TRUE
							IF NOT LOCATE_CHAR_ANY_MEANS_3D wz1_wuzi -2248.0771 651.3163 48.3815 6.0 6.0 6.0 FALSE
								PRINT_NOW WZ1_2 1000 1
							ELSE
								play_wuzi_arrive_message = 1
								IF IS_CHAR_IN_ANY_CAR scplayer
									GET_CAR_CHAR_IS_USING scplayer car
									SET_PLAYER_CONTROL player1 OFF
//									OPEN_SEQUENCE_TASK wz1_seq
										TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 600
//										TASK_LEAVE_ANY_CAR -1
//									CLOSE_SEQUENCE_TASK wz1_seq
//									PERFORM_SEQUENCE_TASK scplayer wz1_seq
//									CLEAR_SEQUENCE_TASK wz1_seq
									wz1_cut_time = TIMERA + 600

									IF IS_CHAR_IN_ANY_CAR wz1_wuzi
										OPEN_SEQUENCE_TASK wz1_seq
											TASK_PAUSE -1 1000
											TASK_LEAVE_ANY_CAR -1
										CLOSE_SEQUENCE_TASK wz1_seq
										PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
										CLEAR_SEQUENCE_TASK wz1_seq	
									ENDIF

								ENDIF
//								play_audio_for = 0
								audio_flag = 6
								REMOVE_BLIP wz1_goto_blip
								journey_audio_flag = 7
					 			check_wuzi_in_group = 0
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF wz1_scene_flag = 1
		IF NOT IS_CHAR_IN_ANY_CAR scplayer		
			IF NOT IS_CHAR_DEAD wz1_wuzi

				OPEN_SEQUENCE_TASK wz1_seq
					TASK_PAUSE -1 300
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 -2244.4392 643.4277 48.4375 PEDMOVE_WALK -2
					TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
				CLOSE_SEQUENCE_TASK wz1_seq
				PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
				CLEAR_SEQUENCE_TASK wz1_seq	
				wz1_walk_comment = 20 // was 1
				wz1_scene_flag = 2
			ENDIF				
		ENDIF		
	ENDIF

	IF wz1_scene_flag = 2
	LVAR_INT play_laundry_comment player_leaves_Car
	LVAR_FLOAT wz1_float

		IF play_laundry_comment = 0
			GET_CHAR_COORDINATES scplayer player_x player_y player_z
			LVAR_INT run_ped[7]
			IF player_y < 646.0
				CREATE_RANDOM_CHAR -2182.9692 645.0695 48.4453 run_ped[0]
				CREATE_RANDOM_CHAR -2183.6428 642.2832 48.4453 run_ped[1]
				CREATE_RANDOM_CHAR -2190.5073 636.5036 48.4429 run_ped[2]
				CREATE_RANDOM_CHAR -2200.6467 641.6252 48.4429 run_ped[3]
				CREATE_RANDOM_CHAR -2200.6218 642.8997 48.4429 run_ped[4]
				CREATE_RANDOM_CHAR -2223.2432 644.5712 48.4448 run_ped[5]
				CREATE_RANDOM_CHAR -2235.8137 642.5659 48.4472 run_ped[6]

				flee_time_start = TIMERA

				
				OPEN_SEQUENCE_TASK wz1_seq
					TASK_PAUSE -1 2000
					TASK_SMART_FLEE_POINT -1 -2181.9692 645.0695 48.4453 80.0 30000
				CLOSE_SEQUENCE_TASK wz1_seq
				PERFORM_SEQUENCE_TASK run_ped[0] wz1_seq
				CLEAR_SEQUENCE_TASK wz1_seq

				TASK_GO_STRAIGHT_TO_COORD run_ped[1] -2190.9778 646.0287 48.4453 PEDMOVE_WALK -2

				TASK_GO_STRAIGHT_TO_COORD run_ped[2] -2192.9639 644.2189 48.4453 PEDMOVE_WALK -2


				OPEN_SEQUENCE_TASK wz1_seq
					TASK_TURN_CHAR_TO_FACE_CHAR -1 run_ped[4]
					TASK_PLAY_ANIM -1 IDLE_chat PED 8.0 FALSE FALSE FALSE FALSE 6000
				CLOSE_SEQUENCE_TASK wz1_seq
				PERFORM_SEQUENCE_TASK run_ped[3] wz1_seq
				CLEAR_SEQUENCE_TASK wz1_seq

				OPEN_SEQUENCE_TASK wz1_seq
					TASK_TURN_CHAR_TO_FACE_CHAR -1 run_ped[3]
					TASK_PLAY_ANIM -1 IDLE_chat PED 8.0 FALSE FALSE FALSE FALSE 6000
				CLOSE_SEQUENCE_TASK wz1_seq
				PERFORM_SEQUENCE_TASK run_ped[4] wz1_seq
				CLEAR_SEQUENCE_TASK wz1_seq

				TASK_GO_STRAIGHT_TO_COORD run_ped[5] -2200.9414 644.4352 48.4429 PEDMOVE_WALK -2

				TASK_GO_STRAIGHT_TO_COORD run_ped[6] -2213.1707 638.7413 48.4496 PEDMOVE_WALK -2


				
				play_laundry_comment = 1
			ENDIF
		ENDIF

		IF play_laundry_comment > 0
			LVAR_INT flee_time_start flee_time_passed flee_trigger
			flee_time_passed = TIMERA - flee_time_start
			IF flee_trigger = 0
				IF flee_time_passed > 3500
					IF NOT IS_CHAR_DEAD run_ped[1]
						TASK_SMART_FLEE_POINT run_ped[1] -2181.9692 645.0695 48.4453 80.0 30000	
					ENDIF

					flee_trigger = 1
				ENDIF
			ENDIF

			IF flee_trigger = 1
				IF flee_time_passed > 6000
					IF NOT IS_CHAR_DEAD run_ped[2]
						OPEN_SEQUENCE_TASK wz1_seq
							TASK_SMART_FLEE_POINT -1 -2181.9692 645.0695 48.4453 80.0 5000	
							TASK_FALL_AND_GET_UP -1 0 500
							TASK_SMART_FLEE_POINT -1 -2181.9692 645.0695 48.4453 80.0 30000
						CLOSE_SEQUENCE_TASK wz1_seq
						PERFORM_SEQUENCE_TASK run_ped[2] wz1_seq
						CLEAR_SEQUENCE_TASK wz1_seq	
					ENDIF	
					flee_trigger = 2
				ENDIF
			ENDIF

			IF flee_trigger = 2
				IF flee_time_passed > 9000
					IF NOT IS_CHAR_DEAD run_ped[3]
						TASK_SMART_FLEE_POINT run_ped[3] -2181.9692 645.0695 48.4453 80.0 30000	
					ENDIF	
					flee_trigger = 3
				ENDIF
			ENDIF

			IF flee_trigger = 3
				IF flee_time_passed > 8000
					IF NOT IS_CHAR_DEAD run_ped[4]

						OPEN_SEQUENCE_TASK wz1_seq
							TASK_SMART_FLEE_POINT -1 -2181.9692 645.0695 48.4453 80.0 3000	
							TASK_FALL_AND_GET_UP -1 0 500
							TASK_SMART_FLEE_POINT -1 -2181.9692 645.0695 48.4453 80.0 30000
						CLOSE_SEQUENCE_TASK wz1_seq
						PERFORM_SEQUENCE_TASK run_ped[4] wz1_seq
						CLEAR_SEQUENCE_TASK wz1_seq
					ENDIF
					flee_trigger = 4
				ENDIF
			ENDIF

			IF flee_trigger = 4
				IF flee_time_passed > 10000
					IF NOT IS_CHAR_DEAD run_ped[5]
						TASK_SMART_FLEE_POINT run_ped[5] -2181.9692 645.0695 48.4453 80.0 30000	
					ENDIF
					flee_trigger = 5
				ENDIF
			ENDIF

			IF flee_trigger = 5
				IF flee_time_passed > 12000
					IF NOT IS_CHAR_DEAD run_ped[6]
						TASK_SMART_FLEE_POINT run_ped[6] -2181.9692 645.0695 48.4453 80.0 30000	
					ENDIF
					flee_trigger = 6
				ENDIF
			ENDIF

			IF flee_trigger = 6
				IF flee_time_passed > 52000
					wz1_i = 0
					WHILE wz1_i < 7
						IF NOT IS_CHAR_DEAD run_ped[wz1_i]
							TASK_PAUSE run_ped[wz1_i] 1
							MARK_CHAR_AS_NO_LONGER_NEEDED run_ped[wz1_i]
							TASK_WANDER_STANDARD run_ped[wz1_i]
							SWITCH_PED_ROADS_OFF -2239.2510 636.6675 53.1933 -2165.0466 720.0577 49.5456
						ENDIF
						wz1_i ++
					ENDWHILE
					flee_trigger = 7
				ENDIF
			ENDIF

					
						
					
		ENDIF

		IF play_laundry_comment = 1
			IF flee_time_passed > 12000
			AND flee_time_passed < 22000
				IF NOT IS_CHAR_DEAD wz1_wuzi
					GET_CHAR_COORDINATES wz1_wuzi x y z
					IF x > -2240.2764
					AND x < -2227.0
						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D wz1_wuzi scplayer 8.0 8.0 8.0 FALSE
							CLEAR_CHAR_TASKS wz1_wuzi
							TASK_PAUSE wz1_wuzi 1
							FLUSH_ROUTE
							
							EXTEND_ROUTE -2232.9993 643.0717 48.4466	
							EXTEND_ROUTE -2213.8877 643.3788 48.4462
							EXTEND_ROUTE -2172.9575 644.4861 48.4453
							OPEN_SEQUENCE_TASK wz1_seq							
								TASK_SCRATCH_HEAD -1
								TASK_LOOK_AT_CHAR -1 scplayer 5000
								TASK_PAUSE -1 1000
								TASK_PAUSE -1 0
								TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE							   								
								TASK_FALL_AND_GET_UP -1 0 500
							CLOSE_SEQUENCE_TASK wz1_seq
							PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
							CLEAR_SEQUENCE_TASK wz1_seq

							//TASK_GO_STRAIGHT_TO_COORD -1 -2214.0289 640.9926 48.4491 PEDMOVE_WALK -2	


							play_audio = 69
							play_audio_for = 4
							play_laundry_comment = 2
							wz1_walk_comment = 21 //was 3

						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF



		LVAR_INT wz1_walk_comment
//		VIEW_INTEGER_VARIABLE wz1_walk_comment wz1_walk_comment
//		VIEW_FLOAT_VARIABLE x x
		IF wz1_walk_comment = 1
			IF NOT IS_CHAR_DEAD wz1_wuzi
				GET_SCRIPT_TASK_STATUS wz1_wuzi -1 wz1_task_progress
				IF wz1_task_progress = FINISHED_TASK
					IF play_audio = 0
					AND audio_flag = 1
						OPEN_SEQUENCE_TASK wz1_seq							
							TASK_PLAY_ANIM -1 Wuzi_grnd_chk wuzi 4.0 FALSE 0 0 0 -1
							TASK_PAUSE -1 1000							
							TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
							TASK_PLAY_ANIM -1 IDLE_chat ped 4.0 FALSE 0 0 0 4000
							TASK_GO_STRAIGHT_TO_COORD -1 -2214.0831 641.1605 48.4489 PEDMOVE_WALK -2
						CLOSE_SEQUENCE_TASK wz1_seq
						PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
						CLEAR_SEQUENCE_TASK wz1_seq

						CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE wz1_ped_decisions EVENT_POTENTIAL_WALK_INTO_OBJECT

						play_audio = 73
						play_audio_for = 3
						wz1_walk_comment = 20
					ENDIF
				ENDIF
			ENDIF
		ENDIF
			
		// alternative run down alley
		IF wz1_walk_comment = 20
			IF NOT IS_CHAR_DEAD wz1_wuzi
				GET_SCRIPT_TASK_STATUS wz1_wuzi -1 wz1_task_progress
				IF wz1_task_progress = FINISHED_TASK
					IF play_audio = 0
					AND audio_flag = 1
						FLUSH_ROUTE
						
						EXTEND_ROUTE -2232.9993 643.0717 48.4466	
						EXTEND_ROUTE -2213.8877 643.3788 48.4462
						EXTEND_ROUTE -2172.9575 644.4861 48.4453


						LVAR_INT wz1_player_near
						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D wz1_wuzi scplayer 13.0 13.0 10.0 FALSE
							wz1_player_near = 1
						ELSE
							wz1_player_near = 0
						ENDIF

						OPEN_SEQUENCE_TASK wz1_seq							
							TASK_PLAY_ANIM -1 Wuzi_grnd_chk wuzi 4.0 FALSE 0 0 0 -1
							TASK_PAUSE -1 1000
							IF wz1_player_near = 1
								TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
								TASK_PLAY_ANIM -1 IDLE_chat ped 4.0 FALSE 0 0 0 3500
							ENDIF
							TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE							
						CLOSE_SEQUENCE_TASK wz1_seq
						PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
						CLEAR_SEQUENCE_TASK wz1_seq

						CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE wz1_ped_decisions EVENT_POTENTIAL_WALK_INTO_OBJECT

					

						play_audio = 73
						play_audio_for = 3
						wz1_walk_comment = 21
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF wz1_walk_comment = 21
			IF NOT IS_CHAR_DEAD wz1_wuzi
			LVAR_INT hits_wall sample_wuzi				
//			VIEW_INTEGER_VARIABLE hits_wall hits_wall
				GET_SCRIPT_TASK_STATUS wz1_wuzi PERFORM_SEQUENCE_TASK wz1_task_progress
//				IF wz1_task_progress = PERFORMING_TASK
//					IF hits_wall = 0
//						GET_SEQUENCE_PROGRESS wz1_wuzi wz1_task_progress //wuzi1: GET_SEQUENCE_PROGRESS- Ped doesn't have a primary task
//						IF wz1_task_progress = 5
//							hits_wall = 1
//							SET_CHAR_SAY_CONTEXT wz1_wuzi CONTEXT_GLOBAL_PAIN_HIGH sample_wuzi
//						ENDIF
//					ENDIF
//				ENDIF
				GET_CHAR_COORDINATES wz1_wuzi x y z
				IF x > -2181.2764
					OPEN_SEQUENCE_TASK wz1_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -2172.9575 644.4861 48.4453 PEDMOVE_RUN -2
						TASK_FALL_AND_GET_UP -1 0 500
					CLOSE_SEQUENCE_TASK wz1_seq
					PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
					CLEAR_SEQUENCE_TASK wz1_seq

					wz1_walk_comment = 22
				ENDIF
				IF x < -2243.0
				AND wz1_wuzi_got_lost = 1
				// stopp wuzi getting lost at end of alleyway - gets stuck in alcoves
				LVAR_INT wz1_wuzi_got_lost
					wz1_wuzi_got_lost = 2
					FLUSH_ROUTE
					
					EXTEND_ROUTE -2244.8306 643.3638 48.439
					EXTEND_ROUTE -2232.9993 643.0717 48.4466	
					EXTEND_ROUTE -2213.8877 643.3788 48.4462
					EXTEND_ROUTE -2172.9575 644.4861 48.4453

					OPEN_SEQUENCE_TASK wz1_seq							
						TASK_PAUSE -1 1000
						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE							
					CLOSE_SEQUENCE_TASK wz1_seq
					PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
					CLEAR_SEQUENCE_TASK wz1_seq						
				ENDIF
				IF x > -2240.0
					IF wz1_wuzi_got_lost = 0
					OR wz1_wuzi_got_lost = 2
						wz1_wuzi_got_lost = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF wz1_walk_comment = 22 
			IF NOT IS_CHAR_DEAD wz1_wuzi
				GET_SCRIPT_TASK_STATUS wz1_wuzi PERFORM_SEQUENCE_TASK wz1_task_progress
				IF wz1_task_progress = PERFORMING_TASK
					IF hits_wall = 0
						GET_SEQUENCE_PROGRESS wz1_wuzi wz1_seq //wuzi1: GET_SEQUENCE_PROGRESS- Ped doesn't have a primary task
						IF wz1_seq = 1 
						OR wz1_task_progress = FINISHED_TASK
							SET_CHAR_SAY_CONTEXT_IMPORTANT wz1_wuzi CONTEXT_GLOBAL_PAIN_LOW TRUE FALSE FALSE sample_wuzi
							wz1_walk_comment = 23
							hits_wall = 1
						ENDIF
					ENDIF
				ENDIF		
			ENDIF
		ENDIF

		IF wz1_walk_comment = 23
			IF NOT IS_CHAR_DEAD wz1_wuzi
				GET_SCRIPT_TASK_STATUS wz1_wuzi PERFORM_SEQUENCE_TASK wz1_task_progress
				IF wz1_task_progress = FINISHED_TASK
					OPEN_SEQUENCE_TASK wz1_seq
						TASK_SCRATCH_HEAD -1
						TASK_LOOK_AT_CHAR -1 scplayer 5000
						TASK_PAUSE -1 4000
						TASK_GO_STRAIGHT_TO_COORD -1 -2176.1836 653.9911 48.4453 PEDMOVE_WALK -2
					CLOSE_SEQUENCE_TASK wz1_seq
					PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
					CLEAR_SEQUENCE_TASK wz1_seq
					play_audio = 78
					play_audio_for = 4
					wz1_walk_comment = 10
				ENDIF
			ENDIF
		ENDIF
					

/*


//		IF wz1_walk_comment = 2
//			IF NOT IS_CHAR_DEAD wz1_wuzi
//				GET_SCRIPT_TASK_STATUS wz1_wuzi PERFORM_SEQUENCE_TASK wz1_task_progress
//				IF wz1_task_progress = FINISHED_TASK
//					IF flee_time_passed > 12000
//						IF flee_time_passed < 22000
//							IF NOT IS_CHAR_DEAD wz1_wuzi
//								IF LOCATE_CHAR_ANY_MEANS_CHAR_3D wz1_wuzi scplayer 8.0 8.0 8.0 FALSE
//									CLEAR_CHAR_TASKS wz1_wuzi
//									TASK_PAUSE wz1_wuzi 1
//									OPEN_SEQUENCE_TASK wz1_seq							
//										TASK_SCRATCH_HEAD -1
//										TASK_LOOK_AT_CHAR -1 scplayer 5000
//										TASK_PAUSE -1 3000
//										TASK_GO_STRAIGHT_TO_COORD -1 -2214.0289 640.9926 48.4491 PEDMOVE_WALK -2								
//									CLOSE_SEQUENCE_TASK wz1_seq
//									PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
//									CLEAR_SEQUENCE_TASK wz1_seq
//
//
//									play_audio = 69
//									play_audio_for = 4
//									play_laundry_comment = 2
//									wz1_walk_comment = 3
//								ENDIF
//							ENDIF
//						ELSE
//							OPEN_SEQUENCE_TASK wz1_seq							
//								TASK_GO_STRAIGHT_TO_COORD -1 -2214.0289 640.9926 48.4491 PEDMOVE_WALK -2								
//							CLOSE_SEQUENCE_TASK wz1_seq
//							PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
//							CLEAR_SEQUENCE_TASK wz1_seq							
//							wz1_walk_comment = 3
//						ENDIF
//					ENDIF
//				ENDIF
//			ENDIF
//		ENDIF


		IF wz1_walk_comment = 3
			IF NOT IS_CHAR_DEAD wz1_wuzi
				GET_CHAR_COORDINATES WZ1_WUZI x y z
				
//				IF LOCATE_CHAR_ANY_MEANS_3D wz1_wuzi -2214.3831 641.1605 48.4489 1.4 1.4 2.0 FALSE
				GET_SCRIPT_TASK_STATUS wz1_wuzi PERFORM_SEQUENCE_TASK wz1_task_progress
				IF wz1_task_progress = FINISHED_TASK
				OR x > -2214.5051
					IF play_audio = 0
						OPEN_SEQUENCE_TASK wz1_seq	
							TASK_PLAY_ANIM -1 HIT_wall ped 4.0 FALSE 1 1 0 -1
							TASK_SCRATCH_HEAD -1						
						CLOSE_SEQUENCE_TASK wz1_seq
						PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
						CLEAR_SEQUENCE_TASK wz1_seq
						play_audio = 77
			
						play_audio_delay = TIMERA + 700
						wz1_walk_comment = 4
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE wz1_ped_decisions EVENT_POTENTIAL_WALK_INTO_OBJECT TASK_COMPLEX_WALK_ROUND_OBJECT 100.0 100.0 100.0 100.0 0 1
					ENDIF
				ELSE
					GET_SCRIPT_TASK_STATUS wz1_wuzi PERFORM_SEQUENCE_TASK wz1_task_progress
					IF wz1_task_progress = FINISHED_TASK
						OPEN_SEQUENCE_TASK wz1_seq							
							TASK_GO_STRAIGHT_TO_COORD -1 -2214.0831 641.1605 48.4489 PEDMOVE_WALK -2
							CLOSE_SEQUENCE_TASK wz1_seq
						PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
						CLEAR_SEQUENCE_TASK wz1_seq
					ENDIF
				ENDIF
			ENDIF
		ENDIF
   
		IF wz1_walk_comment = 4
			IF play_audio = 0
				IF NOT IS_CHAR_DEAD wz1_wuzi
					OPEN_SEQUENCE_TASK wz1_seq							
						TASK_GO_STRAIGHT_TO_COORD -1 -2214.8560 636.1031 48.4528 PEDMOVE_WALK -2
					CLOSE_SEQUENCE_TASK wz1_seq
					PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
					CLEAR_SEQUENCE_TASK wz1_seq
					wz1_walk_comment = 5
				ENDIF				
			ENDIF
		ENDIF

		IF wz1_walk_comment = 5
			IF NOT IS_CHAR_DEAD wz1_wuzi
				IF LOCATE_CHAR_ANY_MEANS_3D wz1_wuzi -2214.8560 636.1031 48.4528 1.4 1.4 2.0 FALSE
					GET_SCRIPT_TASK_STATUS wz1_wuzi PERFORM_SEQUENCE_TASK wz1_task_progress
					IF wz1_task_progress = FINISHED_TASK
						IF play_audio = 0
							OPEN_SEQUENCE_TASK wz1_seq
								TASK_PLAY_ANIM -1 Wuzi_Greet_Wuzi wuzi 4.0 FALSE 1 1 0 -1
								TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
								TASK_PLAY_ANIM -1 IDLE_chat ped 4.0 FALSE 1 1 0 1500
								TASK_GO_STRAIGHT_TO_COORD -1 -2192.6733 643.7750 48.4453 PEDMOVE_WALK -2
							CLOSE_SEQUENCE_TASK wz1_seq
							PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
							CLEAR_SEQUENCE_TASK wz1_seq
	
				//-2201.9026 646.1537 48.4429

							play_audio = 78
							play_audio_for = 4
							wz1_walk_comment = 8
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF wz1_walk_comment = 6
			IF NOT IS_CHAR_DEAD wz1_wuzi
				IF LOCATE_CHAR_ANY_MEANS_3D wz1_wuzi -2203.4272 644.2248 48.4429 3.0 3.0 2.0 FALSE	
					play_audio = 82
					wz1_walk_comment = 7
				ENDIF
			ENDIF
		ENDIF
		
		IF wz1_walk_comment = 7
			IF NOT IS_CHAR_DEAD wz1_wuzi
				IF play_audio = 0
					GET_SCRIPT_TASK_STATUS wz1_wuzi PERFORM_SEQUENCE_TASK wz1_task_progress
					IF wz1_task_progress = FINISHED_TASK 
						OPEN_SEQUENCE_TASK wz1_seq
							TASK_ACHIEVE_HEADING -1 0.0
							TASK_PLAY_ANIM -1 walkstart_idle_01 wuzi 4.0 TRUE 1 1 0 2500
							TASK_LOOK_AT_CHAR -1 scplayer 2000
							TASK_STAND_STILL -1 1500
							TASK_GO_STRAIGHT_TO_COORD -1 -2192.6733 643.7750 48.4453 PEDMOVE_WALK -2
						CLOSE_SEQUENCE_TASK wz1_seq
						PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
						CLEAR_SEQUENCE_TASK wz1_seq

						play_audio = 83
						play_audio_delay = TIMERA + 1500
						wz1_walk_comment = 8
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		IF wz1_walk_comment = 8
			IF NOT IS_CHAR_DEAD wz1_wuzi
					GET_SCRIPT_TASK_STATUS wz1_wuzi PERFORM_SEQUENCE_TASK wz1_task_progress
					IF wz1_task_progress = FINISHED_TASK
						OPEN_SEQUENCE_TASK wz1_seq
							TASK_LOOK_AT_CHAR -1 scplayer 5000
							TASK_LOOK_ABOUT -1 5000
							TASK_PLAY_ANIM -1 wuzi_follow wuzi 4.0 FALSE 1 1 0 -1
							TASK_GO_STRAIGHT_TO_COORD -1 -2178.9399 643.2767 48.4453 PEDMOVE_WALK -2
						CLOSE_SEQUENCE_TASK wz1_seq
						PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
						CLEAR_SEQUENCE_TASK wz1_seq

						play_audio = 84
						play_audio_for = 5
						wz1_walk_comment = 9
					ENDIF
				ENDIF
		ENDIF

		IF wz1_walk_comment = 9
			IF NOT IS_CHAR_DEAD wz1_wuzi
				GET_SCRIPT_TASK_STATUS wz1_wuzi PERFORM_SEQUENCE_TASK wz1_task_progress
				IF wz1_task_progress = FINISHED_TASK
					OPEN_SEQUENCE_TASK wz1_seq
						TASK_SCRATCH_HEAD -1
						TASK_LOOK_AT_CHAR -1 scplayer 5000
						TASK_PAUSE -1 5000
						TASK_GO_STRAIGHT_TO_COORD -1 -2175.8721 659.7667 49.4793 PEDMOVE_WALK -2
					CLOSE_SEQUENCE_TASK wz1_seq
					PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
					CLEAR_SEQUENCE_TASK wz1_seq
					play_audio = 89
					play_audio_for = 3
					wz1_walk_comment = 10
				ENDIF
			ENDIF
		ENDIF


*/ 			 

					   









//			IF player_x > -2238.0
//			AND player_x < -2220.0
//				IF NOT IS_CHAR_DEAD wz1_wuzi
//					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D wz1_wuzi scplayer 8.0 8.0 8.0 FALSE
//						play_audio = 13
//						play_audio_for = 2
//						play_laundry_comment = 1
//					ENDIF
//				ENDIF
//			ENDIF
		
//		IF play_laundry_comment = 1
//			GET_CHAR_COORDINATES scplayer player_x player_y player_z
//			IF player_x > -2210.0
//			AND player_x < -2190.0
//				IF NOT IS_CHAR_DEAD wz1_wuzi
//					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D wz1_wuzi scplayer 8.0 8.0 8.0 FALSE
//						play_audio = 69
//						play_audio_for = 5
//						play_laundry_comment = 2
//					ENDIF
//				ENDIF
//			ENDIF
//		ENDIF
		IF NOT IS_CHAR_DEAD wz1_wuzi
			IF wz1_add_blip = 0
				IF NOT IS_CHAR_IN_ANY_CAR wz1_wuzi
					ADD_BLIP_FOR_CHAR wz1_wuzi wz1_wuzi_blip
					SET_BLIP_AS_FRIENDLY wz1_wuzi_blip TRUE
					wz1_add_blip = 1
				ENDIF
			ENDIF

			IF play_audio = 0
			AND audio_flag = 1
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2175.8721 659.7667 49.4793 6.0 6.0 6.0 FALSE
				OR IS_CHAR_IN_AREA_3D scplayer -2191.5132 711.8201 63.4591 -2171.8594 648.7307 48.1132 FALSE
					wz1_flag = 3
					wz1_cut_flag = 0
					wz1_skip_time = TIMERA + 1500
					REMOVE_CHAR_FROM_GROUP wz1_wuzi
					REMOVE_BLIP wz1_wuzi_blip
					wz1_i = 0
						WHILE wz1_i < 7
							IF DOES_CHAR_EXIST run_ped[wz1_i]
								DELETE_CHAR run_ped[wz1_i]
							ENDIF
							wz1_i ++
						ENDWHILE

				ENDIF
			ENDIF
		ENDIF
	ENDIF
RETURN














// *****************************************************************************************
// ************************* Cut of Wuzi and player discovering ambush *********************
// *****************************************************************************************



wz1_cutscene_2:
	
	SET_WANTED_MULTIPLIER 0.1
	SET_PED_DENSITY_MULTIPLIER 0.0
	SET_CAR_DENSITY_MULTIPLIER 0.0
	SET_POLICE_IGNORE_PLAYER Player1 ON

	IF wz1_door_opens = 1		
//		IF ROTATE_OBJECT china_town_gate 300.0 0.8 FALSE			
//		ENDIF
	ENDIF

	IF wz1_enter_cut = 0
		wz1_enter_cut = 1
		IF IS_BUTTON_PRESSED PAD1 CROSS
			wz1_button_pressed = 1
		ENDIF
	ENDIF

	IF IS_BUTTON_PRESSED PAD1 CROSS
		IF wz1_button_pressed = 0
			IF TIMERA > wz1_skip_time
				wz1_cut_flag = 13
				wz1_cut_time = 0
				audio_flag = 6
			ENDIF
		ENDIF 			
	ELSE
		IF wz1_button_pressed = 1
			wz1_button_pressed = 0
		ENDIF
	ENDIF


	IF wz1_cut_flag = 0
		SET_NEAR_CLIP 0.1
		SET_PED_DENSITY_MULTIPLIER 0.0
		SET_CAR_DENSITY_MULTIPLIER 0.0

			SET_USES_COLLISION_OF_CLOSEST_OBJECT_OF_TYPE -2175.2129 662.7180 50.8582 10.5 ct_gatexr FALSE
//			SET_USES_COLLISION_OF_CLOSEST_OBJECT_OF_TYPE -2175.2129 662.7180 50.8582 10.5 china_town_gateb FALSE


		GET_CURRENT_CHAR_WEAPON scplayer wz1_players_weapon



		SWITCH_WIDESCREEN ON
		
			SET_PLAYER_CONTROL player1 OFF

		SET_FIXED_CAMERA_POSITION -2172.2515 660.9875 48.5695 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -2173.0393 660.4017 48.7593 JUMP_CUT
		SET_OBJECT_COLLISION_DAMAGE_EFFECT china_town_gate TRUE

		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

		REQUEST_CAR_RECORDING 169




			CREATE_CAR SENTINEL -2183.5747 705.0761 52.8984 wz1_burncar2
			CREATE_CAR SENTINEL -2174.4292 703.2332 52.8918 wz1_burncar1 
			SET_CAR_HEADING wz1_burncar1 218.7637
			SET_CAR_HEADING wz1_burncar2 347.2325

			FREEZE_CAR_POSITION wz1_burncar1 TRUE 
			FREEZE_CAR_POSITION wz1_burncar2 TRUE 

//				SET_CAR_HEALTH wz1_burncar1 100
//				SET_CAR_HEALTH wz1_burncar2 100

			DAMAGE_CAR_DOOR wz1_burncar1 BONNET
			DAMAGE_CAR_DOOR wz1_burncar1 BONNET
			DAMAGE_CAR_DOOR wz1_burncar1 BONNET
			DAMAGE_CAR_DOOR wz1_burncar1 FRONT_RIGHT_DOOR
			DAMAGE_CAR_DOOR wz1_burncar1 FRONT_RIGHT_DOOR
			DAMAGE_CAR_DOOR wz1_burncar1 FRONT_RIGHT_DOOR
			DAMAGE_CAR_DOOR wz1_burncar1 REAR_RIGHT_DOOR
			DAMAGE_CAR_DOOR wz1_burncar1 BOOT
			DAMAGE_CAR_DOOR wz1_burncar1 BOOT
			DAMAGE_CAR_DOOR wz1_burncar1 BOOT		
			DAMAGE_CAR_DOOR wz1_burncar1 FRONT_LEFT_DOOR
			DAMAGE_CAR_DOOR wz1_burncar1 FRONT_LEFT_DOOR

			DAMAGE_CAR_PANEL wz1_burncar1 FRONT_RIGHT_PANEL
			DAMAGE_CAR_PANEL wz1_burncar1 FRONT_RIGHT_PANEL
			DAMAGE_CAR_PANEL wz1_burncar1 FRONT_BUMPER
			DAMAGE_CAR_PANEL wz1_burncar1 WINDSCREEN_PANEL
			DAMAGE_CAR_PANEL wz1_burncar1 WINDSCREEN_PANEL
			DAMAGE_CAR_PANEL wz1_burncar1 FRONT_RIGHT_PANEL
			DAMAGE_CAR_PANEL wz1_burncar1 FRONT_RIGHT_PANEL
			DAMAGE_CAR_PANEL wz1_burncar1 FRONT_BUMPER

			DAMAGE_CAR_DOOR wz1_burncar2 BONNET
			DAMAGE_CAR_DOOR wz1_burncar2 BONNET
			DAMAGE_CAR_DOOR wz1_burncar2 FRONT_RIGHT_DOOR
			DAMAGE_CAR_DOOR wz1_burncar2 FRONT_RIGHT_DOOR
			DAMAGE_CAR_DOOR wz1_burncar2 REAR_RIGHT_DOOR
			DAMAGE_CAR_PANEL wz1_burncar2 FRONT_RIGHT_PANEL
			DAMAGE_CAR_PANEL wz1_burncar2 FRONT_RIGHT_PANEL
			DAMAGE_CAR_PANEL wz1_burncar2 FRONT_BUMPER
			DAMAGE_CAR_DOOR wz1_burncar2 BOOT
			DAMAGE_CAR_DOOR wz1_burncar2 BOOT
			DAMAGE_CAR_DOOR wz1_burncar2 BONNET
			DAMAGE_CAR_DOOR wz1_burncar2 BONNET
			DAMAGE_CAR_DOOR wz1_burncar2 FRONT_LEFT_DOOR
			DAMAGE_CAR_DOOR wz1_burncar2 FRONT_LEFT_DOOR
			DAMAGE_CAR_PANEL wz1_burncar2 FRONT_RIGHT_PANEL
			DAMAGE_CAR_PANEL wz1_burncar2 FRONT_RIGHT_PANEL
			DAMAGE_CAR_PANEL wz1_burncar2 FRONT_BUMPER

			OPEN_CAR_DOOR wz1_burncar1 FRONT_RIGHT_DOOR
			OPEN_CAR_DOOR wz1_burncar1 REAR_RIGHT_DOOR

			OPEN_CAR_DOOR wz1_burncar2 FRONT_RIGHT_DOOR

			BURST_CAR_TYRE wz1_burncar2 1
			POP_CAR_BOOT wz1_burncar2

			FORCE_CAR_LIGHTS wz1_burncar1 2


			EXPLODE_CAR_IN_CUTSCENE_SHAKE_AND_BITS wz1_burncar1	FALSE FALSE FALSE
			EXPLODE_CAR_IN_CUTSCENE_SHAKE_AND_BITS wz1_burncar2 FALSE FALSE	FALSE

			CLEAR_AREA -2180.4426 703.3992 55.7186 20.0 FALSE
			CLEAR_AREA -2175.9473 660.2386 49.7032 10.0 FALSE

			LVAR_INT wz1_Fire1 wz1_fire2

			START_SCRIPT_FIRE -2183.5747 705.0761 52.8984 3 2 wz1_Fire1
			START_SCRIPT_FIRE -2174.4292 703.2332 52.8918 5 2 wz1_Fire2

			SET_MAX_FIRE_GENERATIONS 0
			
	
		IF NOT IS_CHAR_DEAD wz1_wuzi
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			CLEAR_CHAR_TASKS_IMMEDIATELY wz1_wuzi
			SET_CHAR_COORDINATES wz1_wuzi -2176.3301 654.2678 48.4453
			SET_CHAR_COORDINATES scplayer -2175.3301 653.2678 48.4453
			SET_CHAR_HEADING scplayer 280.0
			SET_CHAR_HEADING wz1_wuzi 270.0
			TASK_GO_STRAIGHT_TO_COORD scplayer -2173.9517 657.6135 49.8762 PEDMOVE_WALK -2
			OPEN_SEQUENCE_TASK wz1_seq
				TASK_GO_STRAIGHT_TO_COORD -1 -2174.4684 658.3790 48.4453 PEDMOVE_WALK -2
				TASK_ACHIEVE_HEADING -1 72.0
				TASK_PLAY_ANIM -1 Wuzi_Greet_Wuzi wuzi 4.0 FALSE 1 1 0 -1
			CLOSE_SEQUENCE_TASK wz1_seq
			PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
			CLEAR_SEQUENCE_TASK wz1_seq

//				TASK_PLAY_ANIM -1 HIT_wall ped 4.0 FALSE 1 1 0 -1

			force_audio = 1 


			play_audio_global = 1
			play_timed_audio = 15
			play_timed_audio_for = 12

			audio_time[0] = 395 
			audio_time[1] = 4202 
			audio_time[2] = 7380 
			audio_time[3] = 13000 
			audio_time[4] = 14516
			audio_time[5] = 17352
			audio_time[6] = 19617
			audio_time[7] = 21861
			audio_time[8] = 23540
			audio_time[9] = 26894
			audio_time[10] = 28934
			audio_time[11] = 33619



			audio_to_play[0] = 15
			audio_to_play[1] = 16
			audio_to_play[2] = 17
			audio_to_play[3] = 18
			audio_to_play[4] = 19
			audio_to_play[5] = 20
			audio_to_play[6] = 21
			audio_to_play[7] = 22
			audio_to_play[8] = 23
			audio_to_play[9] = 24
			audio_to_play[10] = 25
			audio_to_play[11] = 26
 
		ENDIF
		CLEAR_AREA_OF_CARS -2168.5305 717.8694 68.3455 -2254.3792 608.9255 44.7364
		wz1_cut_time = TIMERA + 7880
		wz1_cut_flag = 1
	ENDIF

	IF wz1_cut_flag = 1
//		IF NOT IS_CHAR_DEAD wz1_wuzi
//			GET_SCRIPT_TASK_STATUS wz1_wuzi PERFORM_SEQUENCE_TASK wz1_task_progress
//			IF wz1_task_progress = FINISHED_TASK
//				GET_SCRIPT_TASK_STATUS wz1_wuzi TASK_SCRATCH_HEAD wz1_task_progress
//				IF wz1_task_progress = FINISHED_TASK
//					TASK_SCRATCH_HEAD wz1_wuzi
//				ENDIF
//			ENDIF
//		ENDIF
		IF TIMERA > wz1_cut_time
//		IF play_audio = 18

 
			


//
//			SET_FIXED_CAMERA_POSITION -2172.8328 660.8203 50.5007 0.0 0.0 0.0
//			POINT_CAMERA_AT_POINT -2173.6914 660.5081 50.0946 JUMP_CUT				
			FLUSH_ROUTE
 
//			SET_CHAR_COORDINATES scplayer -2173.5269 659.7289 48.4453
//			SET_CHAR_HEADING scplayer 27.427
 			    
			IF NOT IS_CHAR_DEAD wz1_wuzi
//				CLEAR_CHAR_TASKS_IMMEDIATELY wz1_wuzi
//				SET_CHAR_COORDINATES wz1_wuzi -2174.6211 658.9733 48.4453
//				SET_CHAR_HEADING wz1_wuzi 20.1793

				EXTEND_ROUTE -2175.9392 662.5935 48.7831
				EXTEND_ROUTE -2175.3887 658.0797 48.4453
				OPEN_SEQUENCE_TASK wz1_seq
					TASK_FOLLOW_POINT_ROUTE	-1 PEDMOVE_WALK 0
				CLOSE_SEQUENCE_TASK wz1_seq
				PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
				CLEAR_SEQUENCE_TASK wz1_seq

			ENDIF

			FLUSH_ROUTE
//			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			EXTEND_ROUTE -2174.7104 658.7460 48.4453
			EXTEND_ROUTE -2175.9392 662.5935 48.7831
			OPEN_SEQUENCE_TASK wz1_seq
				TASK_PAUSE -1 1200
				TASK_FOLLOW_POINT_ROUTE	-1 PEDMOVE_WALK 0
			CLOSE_SEQUENCE_TASK wz1_seq
			PERFORM_SEQUENCE_TASK scplayer wz1_seq
			CLEAR_SEQUENCE_TASK wz1_seq
//			PRINT WZ1_6 4000 1
			wz1_cut_time = TIMERA + 3000
			wz1_cut_flag = 2
			wz1_door_opens = 1
		ENDIF
	ENDIF

	IF wz1_cut_flag = 2

		IF TIMERA > wz1_cut_time
			wz1_cut_flag = 3

		ENDIF	
	ENDIF

	IF wz1_cut_flag = 3
		IF NOT IS_CHAR_DEAD wz1_wuzi

			SET_FIXED_CAMERA_POSITION -2176.838 696.095 53.828 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2178.171 693.774 54.725 JUMP_CUT	
			
//			PRINT WZ1_7 3766 1
//			PRINT WZ1_8 3000 1
//			PRINT WZ1_9 3000 1
//			PRINT WZ1_10 3000 1

			CREATE_CHAR PEDTYPE_CIVMALE TRIADA -2177.462 698.151 52.923 wz1_triad[0]
			CREATE_CHAR PEDTYPE_CIVMALE TRIADB -2182.4417 704.3199 52.8984 wz1_triad[1]
			CREATE_CHAR PEDTYPE_CIVMALE TRIADA -2176.5515 702.9572 52.8984 wz1_triad[2]
			CREATE_CHAR PEDTYPE_CIVMALE TRIADB -2178.4675 707.6988 52.8971 wz1_triad[3]

			SET_CHAR_HEADING wz1_triad[0] 77.5
			SET_CHAR_HEADING wz1_triad[1] 229.9210
			SET_CHAR_HEADING wz1_triad[2] 148.6859
			SET_CHAR_HEADING wz1_triad[3] 12.4516

			MARK_MODEL_AS_NO_LONGER_NEEDED TRIADA
			MARK_MODEL_AS_NO_LONGER_NEEDED TRIADB

			TASK_DIE wz1_triad[1]
			TASK_DIE wz1_triad[2]
			TASK_DIE wz1_triad[3]

			

			

//-2180.9851 701.2921 54.7007 -2171.5364 693.7770 51.6882

			IF NOT IS_CHAR_DEAD wz1_wuzi

				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				CLEAR_CHAR_TASKS_IMMEDIATELY wz1_wuzi


				SET_CHAR_COORDINATES scplayer -2177.034 690.892 52.90
				SET_CHAR_COORDINATES wz1_wuzi -2177.995 690.775 52.90 
				SET_CHAR_HEADING wz1_wuzi 0.0
				SET_CHAR_HEADING scplayer 0.0

				TASK_PLAY_ANIM scplayer CS_Plyr_pt1 wuzi 1000.0 FALSE 1 1 0 -2
				TASK_PLAY_ANIM wz1_wuzi CS_Wuzi_pt1 wuzi 1000.0 FALSE 1 1 0 -2
			ENDIF

			wz1_cut_flag = 4
			wz1_cut_time = TIMERA + 3000
		ENDIF
	ENDIF

	IF wz1_cut_flag = 4
		IF TIMERA > wz1_cut_time
			IF NOT IS_CHAR_DEAD wz1_triad[0]
				SET_CHAR_COORDINATES wz1_triad[0] -2177.602 698.151 53.0
				SET_CHAR_HEADING wz1_triad[0] 77.5
				TASK_PLAY_ANIM_NON_INTERRUPTABLE wz1_triad[0] CS_Dead_Guy wuzi 1000.0 FALSE 0 0 1 -2
				FREEZE_CHAR_POSITION wz1_triad[0] TRUE
				SET_CHAR_COLLISION wz1_triad[0] FALSE
				wz1_cut_time = TIMERA + 766
				wz1_cut_flag = 5
			ENDIF
		ENDIF
	ENDIF





	IF wz1_cut_flag = 5
		IF TIMERA > wz1_cut_time		
			IF NOT IS_CHAR_DEAD wz1_wuzi
				wz1_cut_flag = 6


//					SET_FIXED_CAMERA_POSITION -2177.008 700.78 53.343 0.0 0.0 0.0
//					POINT_CAMERA_AT_POINT -2177.917 695.802 53.209 JUMP_CUT

				SET_FIXED_CAMERA_POSITION -2178.1331 693.2726 54.0799 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2178.1304 694.2693 54.0000 JUMP_CUT
			
				SET_CHAR_COORDINATES scplayer -2177.034 694.913 52.87
				SET_CHAR_COORDINATES wz1_wuzi -2177.994 696.476 52.87 

				SET_CHAR_HEADING wz1_wuzi 0.0

				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				CLEAR_CHAR_TASKS_IMMEDIATELY wz1_wuzi

				TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer CS_Plyr_pt2 wuzi 1000.0 FALSE 1 1 0 -2
				TASK_PLAY_ANIM_NON_INTERRUPTABLE wz1_wuzi CS_Wuzi_pt2 wuzi 1000.0 FALSE 1 1 0 -2
				wz1_cut_time = TIMERA + 2234 
			ENDIF
		ENDIF
	ENDIF

	IF wz1_cut_flag = 6
		IF TIMERA > wz1_cut_time
			IF NOT IS_CHAR_DEAD wz1_triad[0]
				SET_CHAR_COORDINATES wz1_triad[0] -2177.602 698.151 53.0
				TASK_PLAY_ANIM_NON_INTERRUPTABLE wz1_triad[0] CS_Dead_Guy wuzi 1000.0 FALSE 0 0 1 -2
				FREEZE_CHAR_POSITION wz1_triad[0] TRUE
				SET_CHAR_COLLISION wz1_triad[0] FALSE
				wz1_cut_time = TIMERA + 3833
				wz1_cut_flag = 7
			ENDIF
		ENDIF
	ENDIF

	IF wz1_cut_flag = 7
		IF TIMERA > wz1_cut_time		
			IF NOT IS_CHAR_DEAD wz1_wuzi
				


				SET_FIXED_CAMERA_POSITION -2177.7976 695.7769 54.2318 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2177.6267 696.7596 54.3015 JUMP_CUT


				wz1_cut_time = TIMERA + 3000
				wz1_cut_flag = 8

				IF NOT IS_CHAR_DEAD wz1_wuzi
				    GIVE_WEAPON_TO_CHAR wz1_wuzi WEAPONTYPE_MICRO_UZI 5000	
				ENDIF


			
			CREATE_CAR SENTINEL -2177.5242 642.8537 48.4453 wz1_tongcar[0]
//			MARK_MODEL_AS_NO_LONGER_NEEDED SENTINEL

			wz1_door_opens = 0
			SET_OBJECT_HEADING china_town_gate 0.0
			
			

			CREATE_CHAR_INSIDE_CAR wz1_tongcar[0] PEDTYPE_MISSION1 DNB1 wz1_tong[0]
			CREATE_CHAR_AS_PASSENGER wz1_tongcar[0] PEDTYPE_MISSION1 DNB2 0 wz1_tong[1] 
			CREATE_CHAR_AS_PASSENGER wz1_tongcar[0] PEDTYPE_MISSION1 DNB3 1 wz1_tong[2]
			CREATE_CHAR_AS_PASSENGER wz1_tongcar[0] PEDTYPE_MISSION1 DNB2 2 wz1_tong[3]

			GIVE_WEAPON_TO_CHAR wz1_tong[0] WEAPONTYPE_MICRO_UZI 500
			GIVE_WEAPON_TO_CHAR wz1_tong[1] WEAPONTYPE_MICRO_UZI 500
			GIVE_WEAPON_TO_CHAR wz1_tong[2] WEAPONTYPE_MICRO_UZI 500
			GIVE_WEAPON_TO_CHAR wz1_tong[3] WEAPONTYPE_MICRO_UZI 500

			SET_CHAR_ACCURACY wz1_tong[0] 60
			SET_CHAR_ACCURACY wz1_tong[1] 60
			SET_CHAR_ACCURACY wz1_tong[2] 60
			SET_CHAR_ACCURACY wz1_tong[3] 60

			SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1
			SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_MISSION2


			//try car_goto_coord
	 
			 
			

			SET_CAR_FORWARD_SPEED wz1_tongcar[0] 20.0
	   
//				CREATE_GROUP DEFAULT_TASK_ALLOCATOR_STAND_STILL wz1_tongGroup	

//				SET_GROUP_SEPARATION_RANGE wz1_tongGroup 1000.0
			
//				SET_GROUP_LEADER wz1_tongGroup wz1_tong[0] 
//				SET_GROUP_MEMBER wz1_tongGroup wz1_tong[1] 
//				SET_GROUP_MEMBER wz1_tongGroup wz1_tong[2] 
//				SET_GROUP_MEMBER wz1_tongGroup wz1_tong[3] 

//				LOAD_GROUP_DECISION_MAKER DM_GROUP_MISSION_NORM wz1_group_dec
//				SET_GROUP_DECISION_MAKER wz1_tongGroup wz1_group_dec

			SET_CHAR_DECISION_MAKER wz1_tong[0] wz1_ped_decisions
			SET_CHAR_DECISION_MAKER wz1_tong[1] wz1_ped_decisions
			SET_CHAR_DECISION_MAKER wz1_tong[2] wz1_ped_decisions
			SET_CHAR_DECISION_MAKER wz1_tong[3] wz1_ped_decisions

//				CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE wz1_group_dec EVENT_ACQUAINTANCE_PED_HATE
//				ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE wz1_group_dec EVENT_ACQUAINTANCE_PED_HATE TASK_GROUP_KILL_THREATS_BASIC 0.0 100.0 0.0 0.0 1 1		

			CREATE_CHAR PEDTYPE_CIVMALE TRIADA -2180.2654 712.9368 52.9050 wz1_mediator
			audio_actor[3] = wz1_mediator
			SET_CHAR_HEADING wz1_mediator 180.0

			

			ENDIF
		ENDIF
	ENDIF

	IF wz1_cut_flag = 8
		IF TIMERA > wz1_cut_time
			IF NOT IS_CHAR_DEAD wz1_mediator

				IF NOT IS_CHAR_DEAD wz1_wuzi
					SET_CURRENT_CHAR_WEAPON wz1_wuzi WEAPONTYPE_MICRO_UZI
					TASK_AIM_GUN_AT_CHAR wz1_wuzi wz1_mediator 4000
				ENDIF
					
				TASK_AIM_GUN_AT_CHAR scplayer wz1_mediator 4000
//				CLEAR_PRINTS
//				PRINT WZ1_12 3000 1
//				PRINT WZ1_13 5000 1				
				wz1_cut_time = TIMERA + 2000
				wz1_cut_flag = 9
				open_gate_now = 1
				
			ENDIF
		ENDIF
	ENDIF

	LVAR_INT open_Gate_now close_gate_now
//	VIEW_INTEGER_VARIABLE open_sound_started open_sound_started

	IF wz1_cut_flag = 9				
		IF TIMERA > wz1_cut_time
			IF NOT IS_CHAR_DEAD wz1_mediator
				TASK_HANDS_UP wz1_mediator 5000
				// player looks around when car screeches

				OPEN_SEQUENCE_TASK wz1_seq
					TASK_PAUSE -1 6000
					TASK_PLAY_ANIM -1 flee_lkaround_01 PED 8.0 FALSE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK wz1_seq
				PERFORM_SEQUENCE_TASK scplayer wz1_seq
				CLEAR_SEQUENCE_TASK wz1_seq
				wz1_cut_time = TIMERA + 8000
				wz1_cut_flag = 10
				open_sound_started = 3
				LVAR_INT close_gate_time
				close_gate_time = TIMERA + 8500
				close_Gate_now = 1
			ENDIF
			effect_to_play[0] = SOUND_REVERB_CAR_SCREECH
			effect_time_to_play[0] = TIMERA + 6000
		ENDIF
	ENDIF

	IF open_gate_now = 1
		IF open_sound_started = 0
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -2178.5544 704.7785 54.2349 SOUND_GARAGE_DOOR_START
			open_sound_started = 1
		ENDIF		

		IF SLIDE_OBJECT wz1_chinaGar[1] -2178.87 711.556 57.523 0.0 0.0 0.08 FALSE
			LVAR_INT open_sound_started		
			IF open_sound_started = 1
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -2178.5544 704.7785 54.2349 SOUND_GARAGE_DOOR_STOP
				open_sound_started = 2
				open_gate_now = 2
			ENDIF	
		ENDIF
	ENDIF

	IF close_gate_now = 1
		IF TIMERA > close_gate_time
			close_gate_now = 2
		ENDIF
	ENDIF
 
	IF close_gate_now = 2	
		IF open_sound_started = 3
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -2178.5544 704.7785 54.2349 SOUND_GARAGE_DOOR_START
			open_sound_started = 4
		ENDIF		
		
		IF SLIDE_OBJECT wz1_chinaGar[1] -2178.87 711.556 54.523 0.0 0.0 0.08 FALSE					
			IF open_sound_started = 4
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -2178.5544 704.7785 54.2349 SOUND_GARAGE_DOOR_STOP	
				open_sound_started = 5
			ENDIF	
		ENDIF
	ENDIF




	IF wz1_cut_flag = 10

			
		IF TIMERA > wz1_cut_time
//			PRINT WZ1_15 4000 1				
			wz1_cut_time = TIMERA + 1000
			wz1_cut_flag = 11
		ENDIF
	ENDIF

	IF wz1_cut_flag = 11
		
	  	IF TIMERA > wz1_cut_time

			SET_FIXED_CAMERA_POSITION -2172.6741 674.1689 53.0772 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2173.2683 673.4052 52.8255 JUMP_CUT

//			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -2178.5544 704.7785 54.2349 SOUND_GARAGE_DOOR_STOP

			IF NOT IS_CHAR_DEAD wz1_triad[0]
				SET_CHAR_HEALTH wz1_triad[0] 0
			ENDIF

			IF NOT IS_CAR_DEAD wz1_tongcar[0]
			AND NOT IS_CHAR_DEAD wz1_tong[0]
				START_PLAYBACK_RECORDED_CAR wz1_tongcar[0] 169

//				OPEN_SEQUENCE_TASK wz1_seq
//					TASK_CAR_DRIVE_TO_COORD -1 wz1_tongcar[0] -2176.9468 668.3360 50.3436 20.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
//					TASK_CAR_TEMP_ACTION -1 wz1_tongcar[0] TEMPACT_SWERVERIGHT 1000 
//				CLOSE_SEQUENCE_TASK wz1_seq
//				PERFORM_SEQUENCE_TASK wz1_tong[0] wz1_seq
//				CLEAR_SEQUENCE_TASK wz1_seq

			ENDIF

			SET_OBJECT_COLLISION_DAMAGE_EFFECT china_town_gate TRUE
			wz1_cut_flag = 14
			wz1_cut_time = TIMERA + 600

		ENDIF
	ENDIF

	IF wz1_cut_flag = 14
		IF TIMERA > wz1_cut_time
			BREAK_OBJECT china_town_gate FALSE
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -2177.4216 662.1031 51.0185 SOUND_CAR_SMASH_GATE
			wz1_cut_flag = 12
			wz1_cut_time = TIMERA + 2800
		ENDIF
	ENDIF

	IF wz1_cut_flag = 12
		IF TIMERA > wz1_cut_time	
			IF NOT IS_CHAR_DEAD wz1_tong[0]	
				IF NOT IS_CAR_DEAD wz1_tongcar[0]
					TASK_EVERYONE_LEAVE_CAR wz1_tongcar[0]
					wz1_cut_flag = 13
					wz1_cut_time = TIMERA + 2000

				   	FLUSH_ROUTE
				   	EXTEND_ROUTE -2178.4717 676.4617 48.8898
					EXTEND_ROUTE -2178.4717 695.4617 52.8898
				   


				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF wz1_cut_flag = 13

		IF NOT IS_CHAR_DEAD wz1_tong[0]
			IF NOT IS_CHAR_IN_ANY_CAR wz1_tong[0]
				GET_SCRIPT_TASK_STATUS wz1_tong[0] PERFORM_SEQUENCE_TASK wz1_task_progress
				IF wz1_task_progress = FINISHED_TASK
				   	OPEN_SEQUENCE_TASK wz1_seq
				   		TASK_TOGGLE_DUCK -1 TRUE
				   		TASK_FOLLOW_POINT_ROUTE	-1 PEDMOVE_WALK 0
				   	CLOSE_SEQUENCE_TASK	wz1_seq
				   	PERFORM_SEQUENCE_TASK wz1_tong[0] wz1_seq
				   	CLEAR_SEQUENCE_TASK wz1_seq
				ENDIF
			ENDIF
		ENDIF

		IF TIMERA > wz1_cut_time
			wz1_cut_flag = 15


			DO_FADE 1000 FADE_OUT

			wz1_cut_time = TIMERA + 1000
		ENDIF
	ENDIF

	IF wz1_cut_flag = 15
		IF TIMERA > wz1_cut_time			

			wz1_door_opens = 0
			
			IF DOES_OBJECT_EXIST china_town_Gate
				DELETE_OBJECT china_town_gate
			ENDIF
			IF NOT IS_CAR_DEAD wz1_tongcar[0]
				SET_CAR_COORDINATES wz1_tongcar[0] -2174.7273 673.9934 51.8769
				SET_CAR_HEADING wz1_tongcar[0] 337.6978
			ENDIF

			REQUEST_MODEL TRIADA
			REQUEST_MODEL TRIADB
			REQUEST_CAR_RECORDING 169
			WHILE NOT HAS_MODEL_LOADED TRIADA
			OR NOT HAS_MODEL_LOADED TRIADB
			OR NOT HAS_CAR_RECORDING_BEEN_LOADED 169
				WAIT 0
			ENDWHILE

//					GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_MICRO_UZI 5000

			IF IS_CAR_DEAD wz1_tongcar[0]
				CREATE_CAR SENTINEL -2174.7273 673.9934 51.8769 wz1_tongcar[0]
			ENDIF

			IF NOT IS_CAR_DEAD wz1_tongcar[0]
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR wz1_tongcar[0]
					START_PLAYBACK_RECORDED_CAR wz1_tongcar[0] 169
				ENDIF
				SKIP_TO_END_AND_STOP_PLAYBACK_RECORDED_CAR wz1_tongcar[0]
			ENDIF

			DELETE_CHAR wz1_tong[0]
			DELETE_CHAR wz1_tong[1]
			DELETE_CHAR wz1_tong[2]
			DELETE_CHAR wz1_tong[3]
					

			IF NOT IS_CHAR_DEAD wz1_tong[0]
				IF IS_CHAR_IN_ANY_CAR wz1_tong[0]
					WARP_CHAR_FROM_CAR_TO_COORD wz1_tong[0] -2176.6494 669.7497 50.7274
				ELSE 
					SET_CHAR_COORDINATES wz1_tong[0] -2176.6494 669.7497 50.7274
				ENDIF
			ELSE
				CREATE_CHAR	PEDTYPE_MISSION1 DNB1 -2176.6494 669.7497 50.7274 wz1_tong[0]
			ENDIF

			SET_CHAR_HEADING wz1_tong[0] 34.4985

			IF NOT IS_CHAR_DEAD wz1_tong[1]
				IF IS_CHAR_IN_ANY_CAR wz1_tong[1]
					WARP_CHAR_FROM_CAR_TO_COORD wz1_tong[1] -2172.7710 671.1902 51.1101
				ELSE 
					SET_CHAR_COORDINATES wz1_tong[1] -2172.7710 671.1902 51.1101
				ENDIF
			ELSE
				CREATE_CHAR	PEDTYPE_MISSION1 DNB2 -2172.7710 671.1902 51.1101 wz1_tong[1]
			ENDIF

			SET_CHAR_HEADING wz1_tong[1] 28.6454



			IF NOT IS_CHAR_DEAD wz1_tong[2]
				IF IS_CHAR_IN_ANY_CAR wz1_tong[2]
					WARP_CHAR_FROM_CAR_TO_COORD wz1_tong[2] -2176.1431 676.2279 52.4792
				ELSE 
					SET_CHAR_COORDINATES wz1_tong[2] -2176.1431 676.2279 52.4792
				ENDIF
			ELSE
				CREATE_CHAR	PEDTYPE_MISSION1 DNB3 -2176.1431 676.2279 52.4792 wz1_tong[2]
			ENDIF

			SET_CHAR_HEADING wz1_tong[2] 6.8518

			IF NOT IS_CHAR_DEAD wz1_tong[3]
				IF IS_CHAR_IN_ANY_CAR wz1_tong[3]
					WARP_CHAR_FROM_CAR_TO_COORD wz1_tong[3] -2177.8198 675.7360 52.3458
				ELSE 
					SET_CHAR_COORDINATES wz1_tong[3] -2177.8198 675.7360 52.3458
				ENDIF
			ELSE
				CREATE_CHAR	PEDTYPE_MISSION1 DNB2 -2177.8198 675.7360 52.3458 wz1_tong[3]
			ENDIF

			SET_CHAR_HEADING wz1_tong[3] 6.2257

			GIVE_WEAPON_TO_CHAR wz1_tong[0] WEAPONTYPE_MICRO_UZI 5000
			GIVE_WEAPON_TO_CHAR wz1_tong[1] WEAPONTYPE_MICRO_UZI 5000
			GIVE_WEAPON_TO_CHAR wz1_tong[2] WEAPONTYPE_MICRO_UZI 5000
			GIVE_WEAPON_TO_CHAR wz1_tong[3] WEAPONTYPE_MICRO_UZI 5000

			SET_CHAR_ACCURACY wz1_tong[0] 60
			SET_CHAR_ACCURACY wz1_tong[1] 60
			SET_CHAR_ACCURACY wz1_tong[2] 60
			SET_CHAR_ACCURACY wz1_tong[3] 60

			SET_INFORM_RESPECTED_FRIENDS wz1_tong[0] 30.0 8
			SET_INFORM_RESPECTED_FRIENDS wz1_tong[1] 30.0 8
			SET_INFORM_RESPECTED_FRIENDS wz1_tong[2] 30.0 8
			SET_INFORM_RESPECTED_FRIENDS wz1_tong[3] 30.0 8

			FLUSH_ROUTE
			EXTEND_ROUTE -2178.0042 685.6806 52.8942

			TASK_FOLLOW_POINT_ROUTE wz1_tong[3] PEDMOVE_RUN FOLLOW_ROUTE_ONCE

			FLUSH_ROUTE
			EXTEND_ROUTE -2176.0042 685.6806 52.8942

			TASK_FOLLOW_POINT_ROUTE wz1_tong[2] PEDMOVE_RUN FOLLOW_ROUTE_ONCE

			SET_SENSE_RANGE wz1_tong[0] 40.0
			SET_SENSE_RANGE wz1_tong[1] 40.0
			SET_SENSE_RANGE wz1_tong[2] 40.0
			SET_SENSE_RANGE wz1_tong[3] 40.0


			DELETE_CHAR wz1_triad[0]
			DELETE_CHAR wz1_triad[1]
			DELETE_CHAR wz1_triad[2]
			DELETE_CHAR wz1_triad[3]

			ADD_BLIP_FOR_CHAR wz1_tong[0] wz1_tong_blip[0]
			ADD_BLIP_FOR_CHAR wz1_tong[1] wz1_tong_blip[1]
			ADD_BLIP_FOR_CHAR wz1_tong[2] wz1_tong_blip[2]
			ADD_BLIP_FOR_CHAR wz1_tong[3] wz1_tong_blip[3]





			CREATE_CHAR PEDTYPE_CIVMALE TRIADA -2177.9463 670.3361 50.8814 wz1_triad[0]
			CREATE_CHAR PEDTYPE_CIVMALE TRIADB -2182.4417 704.3199 52.8984 wz1_triad[1]
			CREATE_CHAR PEDTYPE_CIVMALE TRIADA -2176.5515 702.9572 52.8984 wz1_triad[2]
			CREATE_CHAR PEDTYPE_CIVMALE TRIADB -2178.4675 707.6988 52.8971 wz1_triad[3]
			SET_CHAR_HEADING wz1_triad[0] 77.5
			SET_CHAR_HEADING wz1_triad[1] 229.9210
			SET_CHAR_HEADING wz1_triad[2] 148.6859
			SET_CHAR_HEADING wz1_triad[3] 12.4516							 


			MARK_MODEL_AS_NO_LONGER_NEEDED TRIADA
			MARK_MODEL_AS_NO_LONGER_NEEDED TRIADB

			TASK_DIE wz1_triad[0]
			TASK_DIE wz1_triad[1]
			TASK_DIE wz1_triad[2]
			TASK_DIE wz1_triad[3]


			SET_CHAR_DECISION_MAKER wz1_tong[0] wz1_ped_decisions
			SET_CHAR_DECISION_MAKER wz1_tong[1] wz1_ped_decisions
			SET_CHAR_DECISION_MAKER wz1_tong[2] wz1_ped_decisions
			SET_CHAR_DECISION_MAKER wz1_tong[3] wz1_ped_decisions

			SET_CHAR_ALLOWED_TO_DUCK wz1_tong[0] FALSE
			SET_CHAR_ALLOWED_TO_DUCK wz1_tong[1] FALSE

			SET_CHAR_STAY_IN_SAME_PLACE wz1_tong[0] TRUE
			SET_CHAR_STAY_IN_SAME_PLACE wz1_tong[1] TRUE

//					CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE wz1_group_dec EVENT_ACQUAINTANCE_PED_HATE
//					ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE wz1_group_dec EVENT_ACQUAINTANCE_PED_HATE TASK_GROUP_KILL_THREATS_BASIC 0.0 100.0 0.0 0.0 1 1	

			IF NOT IS_CAR_DEAD wz1_burncar1
				CREATE_CAR SENTINEL -2174.4292 703.2332 52.8918 wz1_burncar1
				SET_CAR_HEADING wz1_burncar1 218.7637
				EXPLODE_CAR_IN_CUTSCENE wz1_burncar1
			ENDIF

			IF NOT IS_CAR_DEAD wz1_burncar2
				CREATE_CAR SENTINEL -2183.5747 705.0761 52.8984 wz1_burncar2
				SET_CAR_HEADING wz1_burncar2 347.2325
				EXPLODE_CAR_IN_CUTSCENE wz1_burncar2
			ENDIF
		
			
			MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI					
			MARK_MODEL_AS_NO_LONGER_NEEDED DNB1
			MARK_MODEL_AS_NO_LONGER_NEEDED DNB3
			

			IF DOES_OBJECT_EXIST wz1_chinaGar[1]
				SET_OBJECT_COORDINATES wz1_chinaGar[1] -2178.87 711.556 54.523
			ENDIF

			WAIT 1000
																	  



			CLEAR_PRINTS

//			PRINT wz1_B1 3000 1

			SET_USES_COLLISION_OF_CLOSEST_OBJECT_OF_TYPE -2175.2129 662.7180 50.8582 10.5 ct_gatexr TRUE
			SET_USES_COLLISION_OF_CLOSEST_OBJECT_OF_TYPE -2175.2129 662.7180 50.8582 10.5 china_town_gateb TRUE
			

			wz1_flag = 4
			wz1_scene_flag = 0
			wz1_tong_event = 1
			wz1_escape_flag = 0
			wz1_calculate_wuzi_health = 1
			play_audio_global = 0



			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			SET_PLAYER_CONTROL player1 ON
			SWITCH_WIDESCREEN OFF
			IF NOT IS_CHAR_DEAD wz1_wuzi
				CLEAR_CHAR_TASKS_IMMEDIATELY wz1_wuzi
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				SET_CHAR_COORDINATES scplayer -2178.9717 704.3181 52.8971
				SET_CHAR_COORDINATES wz1_wuzi -2180.4231 703.6827 52.8971
				SET_CHAR_HEADING scplayer 190.0
				SET_CHAR_HEADING wz1_wuzi 196.0
				SET_CHAR_HEALTH wz1_wuzi 250
				SET_CURRENT_CHAR_WEAPON wz1_wuzi WEAPONTYPE_MICRO_UZI
			ENDIF

			wz1_health_bar = 100
			DISPLAY_ONSCREEN_COUNTER_WITH_STRING wz1_health_bar COUNTER_DISPLAY_BAR wz1_19
			IF NOT IS_CHAR_DEAD wz1_wuzi
				ADD_BLIP_FOR_CHAR wz1_wuzi wz1_wuzi_blip
				SET_BLIP_AS_FRIENDLY wz1_wuzi_blip TRUE

					MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
					SET_GROUP_MEMBER players_group wz1_wuzi
//					LISTEN_TO_PLAYER_GROUP_COMMANDS wz1_wuzi FALSE
			ENDIF

			SET_CURRENT_CHAR_WEAPON scplayer wz1_players_weapon

			last_wuzi_insult_time = TIMERA + 3000
			force_audio = 0

			REMOVE_ANIMATION wuzi

			DO_FADE 1000 FADE_IN
			WAIT 1000
			
					
		ENDIF
	ENDIF

RETURN










// *****************************************************************************************
// ************************* Get wuzi out of the ambush area *******************************
// *****************************************************************************************


wz1_scene2:
	
	   
LVAR_INT play_instructions
	IF play_instructions = 0
		IF play_audio = 0
			play_audio = 30
			play_instructions = 1
			check_wuzi_in_group = 1
		ENDIF
	ENDIF

	IF play_instructions = 1
		IF play_audio = 0
			PRINT wz1_b1 4000 1
			play_instructions = 2
		ENDIF
	ENDIF

//	IF DOES_CHAR_EXIST wz1_tong[5]
//		wz1_dead_tong = 0
//		wz1_i = 0
//		WHILE  wz1_i < 8
//			IF IS_CHAR_DEAD wz1_tong[wz1_i]
//				wz1_dead_tong ++
//			ENDIF
//			IF NOT IS_CHAR_DEAD wz1_tong[wz1_i]
//				IF IS_CHAR_STUCK_UNDER_CAR wz1_tong[wz1_i]
//					TASK_DIE wz1_tong[wz1_i]
//				ENDIF
//			ENDIF
//			wz1_i ++
//		ENDWHILE
//	ENDIF

	
	LVAR_INT lose_face_message_printed
//	VIEW_INTEGER_VARIABLE lose_face_message_printed lose_face_message_printed

	IF wuzi_in_group = 1
		IF audio_flag = 1
				IF NOT IS_CHAR_DEAD wz1_wuzi
					IF lose_face_message_printed = 0
						IF NOT IS_CHAR_IN_AREA_3D scplayer -2170.1848 719.1667 63.8637 -2239.2356 594.3701 37.2048 FALSE
						AND NOT IS_CHAR_IN_AREA_3D wz1_wuzi -2170.1848 719.1667 63.8637 -2239.2356 594.3701 37.2048 FALSE
						
							PRINT WZ1_B3 5000 1
							lose_face_message_printed = 1
							
						ENDIF
					//get back to kill the bad guys or Qoozie will lose face.
					ENDIF

					IF lose_face_message_printed = 1
						IF IS_CHAR_IN_AREA_3D scplayer -2170.1848 719.1667 63.8637 -2239.2356 594.3701 37.2048 FALSE
						AND IS_CHAR_IN_AREA_3D wz1_wuzi -2170.1848 719.1667 63.8637 -2239.2356 594.3701 37.2048 FALSE
							lose_face_message_printed = 0				
						ENDIF

						IF NOT IS_CHAR_IN_AREA_3D scplayer -2100.1848 779.1667 93.8637 -2279.2356 534.3701 7.2048 FALSE
						AND NOT IS_CHAR_IN_AREA_3D wz1_wuzi -2100.1848 779.1667 93.8637 -2279.2356 534.3701 7.2048 FALSE
							PRINT VAL_A9 4000 1	
							wz1_mission_fail = 1						
						ENDIF

					ENDIF										 
			ENDIF
		ENDIF
	ENDIF


//	IF wz1_dead_tong = 8
	IF IS_CHAR_IN_AREA_3D scplayer -2179.7759 654.5721 51.7195 -2172.2871 632.6477 48.3312 FALSE
		wz1_flag = 5 
		wz1_cut_flag = 8
		wz1_escape_flag = 7
		wz1_time = TIMERA + 2000
		wz1_enter_cut = 0
		wz1_button_pressed = 0
		force_audio = 1
		LVAR_INT wz1_skip_time
		wz1_skip_time = TIMERA + 2500
	ENDIF

												
RETURN








// *****************************************************************************************
// ************************* Cutscene of more tongs turning up *****************************
// *****************************************************************************************


wz1_cutscene_3:

	IF wz1_enter_cut = 0
		wz1_enter_cut = 1
		IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			wz1_button_pressed = 1
		ENDIF
	ENDIF

	IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
		IF wz1_button_pressed = 0
			IF TIMERA > wz1_skip_time
				wz1_cut_flag = 8
				wz1_cut_time = 0
				audio_flag = 6
			ENDIF
		ENDIF 			
	ELSE
		IF wz1_button_pressed = 1
			wz1_button_pressed = 0
		ENDIF
	ENDIF



	IF wz1_cut_flag = 0
		IF TIMERA > wz1_time
			wz1_cut_flag = 1
			play_audio = 47
			SET_PLAYER_CONTROL player1 OFF
			SWITCH_WIDESCREEN ON		 
			SET_FIXED_CAMERA_POSITION -2176.9365 641.8041 49.2832 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2177.2947 642.7374 49.3052 JUMP_CUT
			CLEAR_AREA_OF_CARS -2179.2488 659.2075 50.1335 -2172.2627 641.8954 50.377
					
			IF NOT IS_CAR_DEAD wz1_tongcar[0]			  
				IF IS_CAR_IN_AREA_3D wz1_tongcar[0] -2182.0940 653.1942 50.6565 -2173.9512 636.2528 47.0812 FALSE
					SET_CAR_COORDINATES wz1_tongcar[0] -2175.7152 636.0 48.445
					SET_CAR_HEADING wz1_tongcar[0] 306.9228
				ENDIF
			ENDIF

			CREATE_CHAR PEDTYPE_MISSION1 DNB1 -2218.2251 636.9910 53.9509 wz1_tong[8]
			SET_INFORM_RESPECTED_FRIENDS wz1_tong[8] 30.0 8
			SET_CHAR_STAY_IN_SAME_PLACE wz1_tong[8]	TRUE
			SET_CHAR_ACCURACY wz1_tong[8] 100 
			SET_CHAR_DECISION_MAKER wz1_tong[8] wz1_ped_decisions
			GIVE_WEAPON_TO_CHAR wz1_tong[8] WEAPONTYPE_SNIPERRIFLE 9999
			SET_CHAR_SHOOT_RATE wz1_tong[8] 20
			TASK_AIM_GUN_AT_COORD wz1_tong[8] -2182.9172 645.9363 48.9215 -2
			SET_CHAR_HEADING wz1_tong[8] 289.0
			TASK_TOGGLE_DUCK wz1_tong[8] TRUE


			IF NOT IS_CAR_DEAD wz1_tongcar[1]
				IF IS_CAR_IN_AREA_2D wz1_tongcar[1] -2187.0344 633.1110 -2172.2617 660.5647 FALSE
					SET_CAR_COORDINATES wz1_tongcar[1] -2176.3875 641.4572 50.5019
					SET_CAR_HEADING wz1_tongcar[1] 320.0
				ENDIF
			ENDIF
			IF NOT IS_CAR_DEAD wz1_tongcar[2]
				IF IS_CAR_IN_AREA_2D wz1_tongcar[2] -2187.0344 633.1110 -2172.2617 660.5647 FALSE					
					SET_CAR_COORDINATES wz1_tongcar[2] -2175.3831 640.9232 50.5734
					SET_CAR_HEADING wz1_tongcar[2] 320.0
				ENDIF
			ENDIF

		ENDIF
	ENDIF
	
	IF wz1_cut_flag = 1
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer	
		SET_FIXED_CAMERA_POSITION -2176.9365 641.8041 49.2832 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -2177.2947 642.7374 49.3052 JUMP_CUT

		IF NOT IS_CHAR_DEAD wz1_wuzi
			CLEAR_CHAR_TASKS_IMMEDIATELY wz1_wuzi
			SET_CHAR_COORDINATES wz1_wuzi -2175.6972 650.9794 48.4429
			TASK_GO_STRAIGHT_TO_COORD wz1_wuzi -2179.9583 641.7931 48.4453 PEDMOVE_WALK -2			 
		ENDIF

		SET_CHAR_COORDINATES scplayer -2176.8972 650.9794 48.4429
		TASK_GO_STRAIGHT_TO_COORD scplayer -2180.9583 641.7931 48.4453 PEDMOVE_WALK -2
		wz1_cut_flag = 2
		wz1_time = TIMERA + 2500
	ENDIF

	IF wz1_cut_flag = 2
		IF TIMERA > wz1_time
			IF NOT IS_CHAR_DEAD	wz1_tong[8]
				TASK_SHOOT_AT_COORD wz1_tong[8] -2179.9246 646.6900 48.4548 200
			ENDIF
			wz1_time = TIMERA + 300
			wz1_cut_flag = 3
			wz1_mini_flag = 0
		ENDIF
	ENDIF

	IF wz1_cut_flag = 3
//		GET_SCRIPT_TASK_STATUS scplayer TASK_GO_STRAIGHT_TO_COORD wz1_task_status
//		IF wz1_task_status = FINISHED_TASK
		IF wz1_mini_flag = 0
			IF TIMERA > wz1_time
	//			SET_FIXED_CAMERA_POSITION -2183.6958 643.2665 48.6687 0.0 0.0 0.0
	//			POINT_CAMERA_AT_POINT -2182.8809 643.8264 48.8182 JUMP_CUT			
				TASK_DIVE_AND_GET_UP scplayer -1.0 -1.0 1000 
				IF NOT IS_CHAR_DEAD wz1_wuzi
						TASK_COWER wz1_wuzi
				ENDIF
				wz1_time = TIMERA + 400
				wz1_mini_flag = 1
			ENDIF
		ENDIF
		IF wz1_mini_flag = 1
			IF TIMERA > wz1_time
				wz1_cut_flag = 4
				play_audio = 36
				wz1_time = TIMERA + 1000
			ENDIF
		ENDIF
				

	ENDIF



	IF wz1_cut_flag = 4
		IF TIMERA > wz1_time	
			SET_FIXED_CAMERA_POSITION -2220.5515 639.9709 53.7095 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2219.6382 639.5733 53.7963 JUMP_CUT

			SET_CHAR_COORDINATES scplayer -2178.2976 648.3584 48.4429  
			SET_CHAR_HEADING scplayer 133.2868
			IF NOT IS_CHAR_DEAD wz1_tong[8]
				TASK_AIM_GUN_AT_COORD wz1_tong[8] -2184.7087 635.0774 49.4781 -2
			ENDIF

 			wz1_time = TIMERA + 2500
			wz1_cut_flag = 5

//		GET_SCRIPT_TASK_STATUS scplayer TASK_DIVE_AND_GET_UP wz1_task_status
//		IF wz1_task_status = FINISHED_TASK
//			wz1_cut_flag = 1
//		ENDIF
		ENDIF
	ENDIF

	IF wz1_cut_flag = 5
		IF TIMERA > wz1_time	

			CREATE_CHAR PEDTYPE_MISSION1 DNB2 -2219.5867 643.4511 48.4472 wz1_tong[9]
			CREATE_CHAR PEDTYPE_MISSION1 DNB3 -2223.5867 642.4511 48.4472 wz1_tong[10]
			CREATE_CHAR PEDTYPE_MISSION1 DNB2 -2227.5867 644.4511 48.4472 wz1_tong[11]
			CREATE_CHAR PEDTYPE_MISSION1 DNB3 -2230.5867 643.4511 48.4472 wz1_tong[12]

			SET_INFORM_RESPECTED_FRIENDS wz1_tong[9] 30.0 8
			SET_INFORM_RESPECTED_FRIENDS wz1_tong[10] 30.0 8
			SET_INFORM_RESPECTED_FRIENDS wz1_tong[11] 30.0 8
			SET_INFORM_RESPECTED_FRIENDS wz1_tong[12] 30.0 8

			SET_CHAR_ACCURACY wz1_tong[9] 80
			SET_CHAR_ACCURACY wz1_tong[10] 80
			SET_CHAR_ACCURACY wz1_tong[11] 80
			SET_CHAR_ACCURACY wz1_tong[12] 85

			SET_CHAR_DECISION_MAKER wz1_tong[9] wz1_ped_decisions
			SET_CHAR_DECISION_MAKER wz1_tong[10] wz1_ped_decisions
			SET_CHAR_DECISION_MAKER wz1_tong[11] wz1_ped_decisions
			SET_CHAR_DECISION_MAKER wz1_tong[12] wz1_ped_decisions


			TASK_GO_STRAIGHT_TO_COORD wz1_tong[9] -2194.9016 645.9846 48.4453 PEDMOVE_RUN -2 
			FLUSH_ROUTE
			EXTEND_ROUTE -2200.2095 644.2397 48.4429 
			EXTEND_ROUTE -2199.9395 641.5246 48.4429
			
			SET_CHAR_STAY_IN_SAME_PLACE wz1_tong[9]	TRUE
			SET_CHAR_STAY_IN_SAME_PLACE wz1_tong[10] TRUE
			SET_CHAR_STAY_IN_SAME_PLACE wz1_tong[11] TRUE
			SET_CHAR_STAY_IN_SAME_PLACE wz1_tong[12] TRUE

			GIVE_WEAPON_TO_CHAR wz1_tong[9] WEAPONTYPE_MICRO_UZI 5000
			GIVE_WEAPON_TO_CHAR wz1_tong[10] WEAPONTYPE_MICRO_UZI 5000
			GIVE_WEAPON_TO_CHAR wz1_tong[11] WEAPONTYPE_MICRO_UZI 5000
			GIVE_WEAPON_TO_CHAR wz1_tong[12] WEAPONTYPE_MICRO_UZI 5000

			OPEN_SEQUENCE_TASK wz1_seq
				TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN 0
				TASK_ACHIEVE_HEADING -1 270.0
			CLOSE_SEQUENCE_TASK	wz1_seq
			PERFORM_SEQUENCE_TASK wz1_tong[10] wz1_seq
			CLEAR_SEQUENCE_TASK wz1_seq

			TASK_GO_STRAIGHT_TO_COORD wz1_tong[11] -2204.8511 641.7612 48.4429 PEDMOVE_RUN -2
			TASK_GO_STRAIGHT_TO_COORD wz1_tong[12] -2214.3706 641.3669 48.4515 PEDMOVE_RUN -2

			wz1_time = TIMERA + 500
			wz1_cut_flag = 6
		ENDIF
	ENDIF

	IF wz1_cut_flag = 6
		IF TIMERA > wz1_time	
			SET_FIXED_CAMERA_POSITION -2229.4363 644.2267 48.4907 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2228.4866 643.9835 48.6883 JUMP_CUT	
			play_audio = 46
			wz1_time = TIMERA + 3000
			wz1_cut_flag = 7
		ENDIF
	ENDIF

	IF wz1_cut_flag = 7
		IF TIMERA > wz1_time
			wz1_cut_flag = 8
		ENDIF
	ENDIF

	IF wz1_cut_flag = 8
		IF NOT DOES_CHAR_EXIST wz1_tong[8]
			CREATE_CHAR PEDTYPE_MISSION1 DNB3 -2218.2251 636.9910 53.9509 wz1_tong[8]
			SET_CHAR_STAY_IN_SAME_PLACE wz1_tong[8]	TRUE 
			SET_CHAR_DECISION_MAKER wz1_tong[8] wz1_ped_decisions
			GIVE_WEAPON_TO_CHAR wz1_tong[8] WEAPONTYPE_SNIPERRIFLE 9999
			SET_CHAR_SHOOT_RATE wz1_tong[8] 20
			SET_CHAR_HEADING wz1_tong[8] 289.0
			TASK_TOGGLE_DUCK wz1_tong[8] TRUE			
		ENDIF 

		IF NOT IS_CHAR_DEAD wz1_tong[8]
			SET_CHAR_ACCURACY wz1_tong[8] 50
		ENDIF

		DELETE_CHAR wz1_tong[9]
		DELETE_CHAR wz1_tong[10]
		DELETE_CHAR wz1_tong[11]
		DELETE_CHAR wz1_tong[12]

		CREATE_CHAR PEDTYPE_MISSION1 DNB1 -2203.4910 647.0372 48.4453 wz1_tong[9]
		CREATE_CHAR PEDTYPE_MISSION1 DNB2 -2200.6946 637.1932 48.4429 wz1_tong[10]
		CREATE_CHAR PEDTYPE_MISSION1 DNB3 -2196.9429 635.1917 48.4429 wz1_tong[11]
		CREATE_CHAR PEDTYPE_MISSION1 DNB1 -2214.3779 641.6099 48.4484 wz1_tong[12]

		SET_CHAR_HEADING wz1_tong[9] 255.1494
		SET_CHAR_HEADING wz1_tong[10] 285.6451
		SET_CHAR_HEADING wz1_tong[11] 295.4749
		SET_CHAR_HEADING wz1_tong[12] 277.3081

		SET_CHAR_ACCURACY wz1_tong[9] 60
		SET_CHAR_ACCURACY wz1_tong[10] 60
		SET_CHAR_ACCURACY wz1_tong[11] 60
		SET_CHAR_ACCURACY wz1_tong[12] 60

		SET_CHAR_SHOOT_RATE	wz1_tong[9] 85
		SET_CHAR_SHOOT_RATE	wz1_tong[10] 85
		SET_CHAR_SHOOT_RATE	wz1_tong[11] 85
		SET_CHAR_SHOOT_RATE	wz1_tong[12] 85

		TASK_DUCK wz1_tong[9] 1000
		TASK_DUCK wz1_tong[11] 3000

		SET_CHAR_STAY_IN_SAME_PLACE wz1_tong[9]	TRUE
		SET_CHAR_STAY_IN_SAME_PLACE wz1_tong[10] TRUE
		SET_CHAR_STAY_IN_SAME_PLACE wz1_tong[11] TRUE
		SET_CHAR_STAY_IN_SAME_PLACE wz1_tong[12] TRUE

			SET_SENSE_RANGE wz1_tong[9] 40.0
			SET_SENSE_RANGE wz1_tong[10] 40.0
			SET_SENSE_RANGE wz1_tong[11] 40.0
			SET_SENSE_RANGE wz1_tong[12] 40.0

		GIVE_WEAPON_TO_CHAR wz1_tong[9] WEAPONTYPE_MICRO_UZI 5000
		GIVE_WEAPON_TO_CHAR wz1_tong[10] WEAPONTYPE_MICRO_UZI 5000
		GIVE_WEAPON_TO_CHAR wz1_tong[11] WEAPONTYPE_MICRO_UZI 5000
		GIVE_WEAPON_TO_CHAR wz1_tong[12] WEAPONTYPE_MICRO_UZI 5000

		CREATE_CHAR PEDTYPE_MISSION1 DNB1 -2205.3440 648.4099 48.4429 wz1_tong[13]	

		SET_INFORM_RESPECTED_FRIENDS wz1_tong[13] 30.0 8

		GIVE_WEAPON_TO_CHAR wz1_tong[13] WEAPONTYPE_MICRO_UZI 500
		SET_CHAR_HEADING wz1_tong[13] 270.0
		SET_CHAR_STAY_IN_SAME_PLACE wz1_tong[13] TRUE

//		SET_CHAR_COORDINATES scplayer -2181.3103 639.2708 48.4453 
//		SET_CHAR_HEADING scplayer 61.1766

		ADD_BLIP_FOR_CHAR wz1_tong[9] wz1_tong_blip[9]
		ADD_BLIP_FOR_CHAR wz1_tong[10] wz1_tong_blip[10]
		ADD_BLIP_FOR_CHAR wz1_tong[11] wz1_tong_blip[11]
		ADD_BLIP_FOR_CHAR wz1_tong[12] wz1_tong_blip[12]
		ADD_BLIP_FOR_CHAR wz1_tong[13] wz1_tong_blip[13]

		IF NOT IS_CHAR_DEAD wz1_tong[8]
			REMOVE_BLIP wz1_tong_blip[8]
			ADD_BLIP_FOR_CHAR wz1_tong[8] wz1_tong_blip[8]
		ENDIF

		SET_CHAR_DECISION_MAKER wz1_tong[9] wz1_ped_decisions
		SET_CHAR_DECISION_MAKER wz1_tong[10] wz1_ped_decisions
		SET_CHAR_DECISION_MAKER wz1_tong[11] wz1_ped_decisions	
		SET_CHAR_DECISION_MAKER wz1_tong[12] wz1_ped_decisions
		SET_CHAR_DECISION_MAKER wz1_tong[13] wz1_ped_decisions

//		IF NOT IS_CHAR_DEAD wz1_wuzi
//			SET_CHAR_COORDINATES wz1_wuzi -2178.2976 648.3584 48.4429  
//			SET_CHAR_HEADING wz1_wuzi 133.2868
//			TASK_TOGGLE_DUCK wz1_wuzi TRUE
//		ENDIF

//		SET_CAMERA_BEHIND_PLAYER
//		RESTORE_CAMERA_JUMPCUT
//		SWITCH_WIDESCREEN OFF
//		SET_PLAYER_CONTROL player1 ON
		wz1_flag = 6
		wz1_tong_event = 4
		wz1_advance_time = TIMERA + 4000
		wz1_escape_flag = 8
		
		wz1_scene_flag = 0
		lose_face_message_printed = 0
		force_audio = 0
	ENDIF
RETURN


	   

// *****************************************************************************************
// ****************************** Get Wuzi to getaway car **********************************
// *****************************************************************************************


wz1_scene3:


	IF wuzi_in_group = 1
		IF audio_flag = 1
				IF NOT IS_CHAR_DEAD wz1_wuzi
					IF lose_face_message_printed = 0
						IF NOT IS_CHAR_IN_AREA_3D scplayer -2170.1848 719.1667 63.8637 -2239.2356 594.3701 37.2048 FALSE
						AND NOT IS_CHAR_IN_AREA_3D wz1_wuzi -2170.1848 719.1667 63.8637 -2239.2356 594.3701 37.2048 FALSE
						
							PRINT WZ1_B3 5000 1
							lose_face_message_printed = 1
							
						ENDIF
					//get back to kill the bad guys or Qoozie will lose face.
					ENDIF

					IF lose_face_message_printed = 1
						IF IS_CHAR_IN_AREA_3D scplayer -2170.1848 719.1667 63.8637 -2239.2356 594.3701 37.2048 FALSE
						AND IS_CHAR_IN_AREA_3D wz1_wuzi -2170.1848 719.1667 63.8637 -2239.2356 594.3701 37.2048 FALSE
							lose_face_message_printed = 0				
						ENDIF

						IF NOT IS_CHAR_IN_AREA_3D scplayer -2100.1848 779.1667 93.8637 -2279.2356 534.3701 7.2048 FALSE
						AND NOT IS_CHAR_IN_AREA_3D wz1_wuzi -2100.1848 779.1667 93.8637 -2279.2356 534.3701 7.2048 FALSE
							PRINT VAL_A9 4000 1	
							wz1_mission_fail = 1						
						ENDIF

					ENDIF										 

			ENDIF
		ENDIF
	ENDIF

	LVAR_INT watch_sniper

	IF watch_sniper = 0
		IF NOT IS_CHAR_DEAD wz1_tong[8]
			IF IS_CHAR_SHOOTING wz1_tong[8]
				IF play_audio = 0
					play_audio = 36
					watch_sniper = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF watch_sniper = 1
		IF NOT IS_CHAR_DEAD wz1_tong[8]
			IF IS_CHAR_SHOOTING wz1_tong[8]
				IF play_audio = 0
					play_audio = 43
					watch_sniper = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	
	IF wz1_dead_tong_wave3 = 10
		wz1_flag = 7
		wz1_cut_flag = 0
		wz1_cut_time = TIMERA + 2500


	MARK_MODEL_AS_NO_LONGER_NEEDED TRIADA
	MARK_MODEL_AS_NO_LONGER_NEEDED TRIADB
	MARK_MODEL_AS_NO_LONGER_NEEDED SNIPER
	MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
	REMOVE_ANIMATION wuzi
	REMOVE_CAR_RECORDING 169
	REMOVE_CAR_RECORDING 139
	lose_face_message_printed = 0
	


	ENDIF 

												
RETURN









// *****************************************************************************************
// ************* Player enters car with Wuzi and rams attacking Tong cars ******************
// *****************************************************************************************


wz1_cutscene_4:

//    SET_USES_COLLISION_OF_CLOSEST_OBJECT_OF_TYPE -2248.2129 643.7180 50.8582 2.5 Tramstop_SF FALSE

	IF wz1_cut_flag = 0
		IF audio_flag = 1
			IF play_audio = 0
				IF NOT IS_CHAR_DEAD wz1_wuzi
					REMOVE_CHAR_FROM_GROUP wz1_wuzi
				ENDIF
				CLEAR_ONSCREEN_COUNTER wz1_health_bar
				
				
				REQUEST_ANIMATION GANGS
				REQUEST_ANIMATION CAR_CHAT
				REQUEST_CAR_RECORDING 136
				REQUEST_CAR_RECORDING 137
				REQUEST_CAR_RECORDING 138
				play_audio = 55
				play_audio_global = 1
				force_audio = 1
				wz1_cut_flag = 6
				wuzi_in_group = 0
			ENDIF
		ENDIF

//		IF NOT IS_CHAR_DEAD wz1_wuzi
//			CLEAR_CHAR_TASKS_IMMEDIATELY wz1_wuzi
							
//			SET_CHAR_COORDINATES wz1_wuzi -2191.6741 642.7552 48.4453
//			SET_CHAR_HEADING wz1_wuzi 90.0
//			TASK_GO_STRAIGHT_TO_COORD wz1_wuzi -2211.0906 643.0065 48.44 PEDMOVE_WALK -2 
//		ENDIF


	ENDIF

//	IF wz1_cut_flag = 1
//		IF TIMERA > wz1_cut_time
//			SET_PLAYER_CONTROL player1 OFF
//			SWITCH_WIDESCREEN ON
//			SET_FIXED_CAMERA_POSITION -2194.4580 645.8234 49.4058 0.0 0.0 0.0
//			POINT_CAMERA_AT_POINT -2195.4114 645.5325 49.4835 JUMP_CUT
//
//			IF NOT IS_CAR_DEAD wz1_getaway_car
//				LOCK_CAR_DOORS wz1_getaway_car CARLOCK_UNLOCKED
//				FREEZE_CAR_POSITION wz1_getaway_car FALSE
//				SET_CAR_COORDINATES wz1_getaway_car -2218.9304 642.3626 48.4475
//				SET_CAR_HEADING wz1_getaway_car 267.1711
//			ENDIF
//
//			
//			
//			IF IS_CHAR_IN_ANY_CAR scplayer
//				GET_CAR_CHAR_IS_USING scplayer car
//			ENDIF
//
//			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
//
//			SET_CHAR_COORDINATES scplayer -2196.1609 644.3362 48.4453
//			SET_CHAR_HEADING scplayer 90.0
//			GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_MICRO_UZI 20
//
//			TASK_TIRED scplayer 3000
//			IF NOT IS_CHAR_DEAD wz1_wuzi
//				CLEAR_CHAR_TASKS_IMMEDIATELY wz1_wuzi
//				TASK_TOGGLE_DUCK wz1_wuzi FALSE				
//				SET_CHAR_COORDINATES wz1_wuzi -2191.6741 642.7552 48.4453
//				SET_CHAR_HEADING wz1_wuzi 90.0
//				TASK_GO_STRAIGHT_TO_COORD wz1_wuzi -2211.0906 643.0065 48.44 PEDMOVE_WALK -2 
//			ENDIF
//
//			IF NOT IS_CAR_DEAD car
//				DELETE_CAR car
//			ENDIF
//
//LVAR_INT wz1_ped0
//
//			CREATE_CHAR PEDTYPE_MISSION4 OMOST -2206.2307 651.4228 48.4453 wz1_ped0
//			TASK_GO_STRAIGHT_TO_COORD wz1_ped0 -2205.4429 642.3512 48.4453 PEDMOVE_RUN -1 
//
//			wz1_cut_flag = 2
//			wz1_cut_time = TIMERA + 2300
//		ENDIF	
//	ENDIF

//	IF wz1_cut_flag = 2
//		IF TIMERA > wz1_cut_time
//			IF NOT IS_CHAR_DEAD wz1_ped0
//				TASK_AIM_GUN_AT_CHAR scplayer wz1_ped0 3000
//				TASK_HANDS_UP wz1_ped0 3000
//				wz1_cut_flag = 3
//				wz1_cut_time = TIMERA + 1500
//			ENDIF
//		ENDIF
//	ENDIF
//
//	IF wz1_cut_flag = 3
//		IF TIMERA > wz1_cut_time
//			// print "Relax CJ...come let's take that Tong car and get out of here."
//			wz1_cut_flag = 4
//			wz1_cut_time = TIMERA + 1800
//		ENDIF
//	ENDIF
//
//	IF wz1_cut_flag = 4
//		IF TIMERA > wz1_cut_time
//			TASK_GO_STRAIGHT_TO_COORD scplayer -2197.5609 644.3362 48.4453 PEDMOVE_WALK -2
//			wz1_cut_flag = 5
//			wz1_cut_time = TIMERA + 1000
//		ENDIF
//	ENDIF
//
//	IF wz1_cut_flag = 5
//		IF TIMERA > wz1_cut_time
//			TASK_PLAY_ANIM scplayer prtial_gngtlkA GANGS 4.0 FALSE 0 0 0 -1
//			IF NOT IS_CHAR_DEAD wz1_ped0
//				TASK_SMART_FLEE_POINT wz1_ped0 -2203.8962 642.1242 49.7291 30.0 8000
//			ENDIF
//
//			wz1_cut_flag = 6
//			wz1_cut_time = TIMERA + 4000
//			REQUEST_CAR_RECORDING 136
//			REQUEST_CAR_RECORDING 137
//			REQUEST_CAR_RECORDING 138
//		ENDIF
//	ENDIF

	IF wz1_cut_flag = 6
		IF audio_flag = 1
			IF play_audio = 0		
				IF TIMERA > wz1_cut_time
				AND HAS_CAR_RECORDING_BEEN_LOADED 136
				AND HAS_CAR_RECORDING_BEEN_LOADED 137
				AND HAS_CAR_RECORDING_BEEN_LOADED 138

					force_audio = 0

					IF NOT IS_CAR_DEAD wz1_getaway_car
						LOCK_CAR_DOORS wz1_getaway_car CARLOCK_UNLOCKED
						FREEZE_CAR_POSITION wz1_getaway_car FALSE
						SET_CAR_COORDINATES wz1_getaway_car -2218.9304 642.3626 48.4475
						SET_CAR_HEADING wz1_getaway_car 267.1711
					ENDIF

					SET_PLAYER_CONTROL player1 OFF
					SWITCH_WIDESCREEN ON
					

					IF IS_CHAR_IN_ANY_CAR scplayer
						GET_CAR_CHAR_IS_USING scplayer car
					ENDIF

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

					IF NOT IS_CHAR_DEAD wz1_wuzi
						CLEAR_CHAR_TASKS_IMMEDIATELY wz1_wuzi
						TASK_TOGGLE_DUCK wz1_wuzi FALSE				
					ENDIF

					IF NOT IS_CAR_DEAD car
						DELETE_CAR car
					ENDIF

							 
					SET_FIXED_CAMERA_POSITION -2212.1252 644.6052 51.6509 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT -2213.0623 644.5379 51.3082 JUMP_CUT			
		 
					IF NOT IS_CAR_DEAD wz1_getaway_car

						SET_CHAR_COORDINATES scplayer -2212.3801 643.0919 48.4466
						SET_CHAR_HEADING scplayer 180.0
						TASK_ENTER_CAR_AS_DRIVER scplayer wz1_getaway_car -2
						SET_CAR_PROOFS wz1_getaway_car TRUE TRUE TRUE TRUE TRUE

						IF NOT IS_CHAR_DEAD wz1_wuzi
										 
							SET_CHAR_SHOOT_RATE wz1_wuzi 80
							SET_CHAR_RELATIONSHIP wz1_wuzi ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
							SET_CHAR_COORDINATES wz1_wuzi -2217.7805 640.9980 48.4549
							SET_CHAR_HEADING wz1_wuzi 77.0
							TASK_ENTER_CAR_AS_PASSENGER wz1_wuzi wz1_getaway_car 2600 0
						ENDIF
					ENDIF

					wz1_i = 0
					WHILE wz1_i < 18
						MARK_CHAR_AS_NO_LONGER_NEEDED wz1_tong[wz1_i]
						wz1_i ++
					ENDWHILE
					

					CREATE_CAR SENTINEL -2245.3433 715.4953 48.4375 wz1_carattack[0] 
					SET_CAR_HEADING wz1_carattack[0] 180.0
					CREATE_CHAR_INSIDE_CAR wz1_carattack[0] PEDTYPE_MISSION1 DNB2 wz1_tong[0]
					CREATE_CHAR_AS_PASSENGER wz1_carattack[0] PEDTYPE_MISSION1 DNB3 0 wz1_tong[1] 
					GIVE_WEAPON_TO_CHAR wz1_tong[1] WEAPONTYPE_MICRO_UZI 5000
					START_PLAYBACK_RECORDED_CAR wz1_carattack[0] 136
		//			SET_CAR_FORWARD_SPEED wz1_carattack[0] 25.0

		//			TASK_CAR_DRIVE_TO_COORD wz1_tong[0] wz1_carattack[0] -2245.4707 630.0079 48.4375 25.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH 

		//			 TASK_PLAY_ANIM scplayer CAR_Sc1_FL CAR_CHAT 4.0 FALSE FALSE FALSE FALSE -1
		//					TASK_TOGGLE_PED_THREAT_SCANNER wz1_tong[15] 1 1 1
					

					CREATE_CAR SENTINEL -2251.6763 612.6026 42.2590 wz1_carattack[1]
					SET_CAR_HEADING wz1_carattack[1] 0.0
					CREATE_CHAR_INSIDE_CAR wz1_carattack[1] PEDTYPE_MISSION1 DNB1 wz1_tong[2]
					CREATE_CHAR_AS_PASSENGER wz1_carattack[1] PEDTYPE_MISSION1 DNB2 0 wz1_tong[3] 
					GIVE_WEAPON_TO_CHAR wz1_tong[3] WEAPONTYPE_MICRO_UZI 5000
		//					TASK_TOGGLE_PED_THREAT_SCANNER wz1_tong[17] 1 1 1

					SET_CHAR_DECISION_MAKER wz1_tong[0] wz1_ped_decisions
					SET_CHAR_DECISION_MAKER wz1_tong[1] wz1_ped_decisions
					SET_CHAR_DECISION_MAKER wz1_tong[2] wz1_ped_decisions
					SET_CHAR_DECISION_MAKER wz1_tong[3] wz1_ped_decisions

					CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE wz1_ped_decisions EVENT_ACQUAINTANCE_PED_HATE
					CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE wz1_ped_decisions EVENT_VEHICLE_ON_FIRE
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE wz1_ped_decisions EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_GANG_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE

					wz1_cut_flag = 7
					wz1_cut_time = TIMERA + 2600
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF wz1_cut_flag = 7
		IF TIMERA > wz1_cut_time
						
			SET_FIXED_CAMERA_POSITION -2217.8694 642.1810 49.7208 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2218.8203 642.4485 49.5654 JUMP_CUT
			wz1_cut_flag = 8
		ENDIF
	ENDIF

	IF wz1_cut_flag = 8
		IF NOT IS_CAR_DEAD wz1_carattack[0]
		AND NOT IS_CHAR_DEAD wz1_tong[0] 
			GET_CAR_COORDINATES wz1_carattack[0] x y z
			IF y < 653.8901
//				TASK_CAR_TEMP_ACTION wz1_tong[0] wz1_carattack[0] TEMPACT_HANDBRAKESTRAIGHT 3000				
				
				wz1_cut_flag = 9
			ENDIF
		ENDIF
	ENDIF

	IF wz1_cut_flag = 9
		IF IS_CHAR_IN_ANY_CAR scplayer
			IF NOT IS_CAR_DEAD wz1_getaway_car
//				SET_CAR_TEMP_ACTION wz1_getaway_car TEMPACT_REVERSE 20000
			ENDIF
			TASK_PLAY_ANIM scplayer CAR_Sc1_FL CAR_CHAT 4.0 FALSE FALSE FALSE FALSE 3000
			play_audio = 60
			wz1_cut_flag = 10
			wz1_cut_time = TIMERA + 3200
		ENDIF
	ENDIF

	IF wz1_cut_flag = 10
		IF TIMERA > wz1_cut_time
			IF NOT IS_CAR_DEAD wz1_getaway_car
				SET_CAR_HEAVY wz1_getaway_car TRUE
				
				START_PLAYBACK_RECORDED_CAR wz1_getaway_car 138
				IF NOT IS_CAR_DEAD wz1_carattack[0]
//					STOP_RECORDING_CAR wz1_carattack[0]
					STOP_PLAYBACK_RECORDED_CAR wz1_carattack[0]
					START_PLAYBACK_RECORDED_CAR wz1_carattack[0] 137
				ENDIF
//				TASK_CAR_TEMP_ACTION scplayer wz1_getaway_car TEMPACT_REVERSE 20000				
				wz1_cut_flag = 11
				wz1_cut_time = TIMERA + 2000
			ENDIF
		ENDIF
	ENDIF

	IF wz1_cut_flag = 11
		IF TIMERA > wz1_cut_time
			SET_FIXED_CAMERA_POSITION -2251.0203 649.9792 52.2440 0.0 0.0 0.0
			IF NOT IS_CAR_DEAD wz1_carattack[0] 
		   		GET_CAR_COORDINATES wz1_carattack[0] x y z
				POINT_CAMERA_AT_POINT x y z JUMP_CUT
			ENDIF
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -2243.9182 642.9590 49.6897 SOUND_CAR_SMASH_CAR
			wz1_cut_flag = 12
			wz1_cut_time = TIMERA + 800
		ENDIF
	ENDIF

	IF wz1_cut_flag = 12
		IF TIMERA > wz1_cut_time
			wz1_cam_time_start = TIMERA
			wz1_cut_flag = 13
			LVAR_FLOAT gap_x gap_y
			LVAR_INT wz1_cam_time_start wz1_time_change 
			wz1_cut_time = TIMERA + 2500
		ENDIF
	ENDIF

	IF wz1_cut_flag = 13
		wz1_time_change = TIMERA - wz1_cam_time_start
		wz1_float1 =# wz1_time_change
		wz1_float2 = wz1_float1 / 2500.0
		IF NOT IS_CAR_DEAD wz1_getaway_car
	   	AND NOT IS_CAR_DEAD wz1_carattack[0]
	   		GET_CAR_COORDINATES wz1_carattack[0] x y z
	   		GET_CAR_COORDINATES wz1_getaway_car wuzi_x wuzi_y wuzi_z
	   		gap_x = wuzi_x - x
	   		gap_y = wuzi_y - y
			gap_x *= wz1_float2
			gap_y *= wz1_float2
			gap_x /= 2.0
			gap_y /= 2.0
			gap_x += x 
			gap_y += y
			POINT_CAMERA_AT_POINT gap_x gap_y z JUMP_CUT
		ENDIF
		IF TIMERA > wz1_cut_time
			wz1_cut_flag = 14
		ENDIF
	ENDIF

	IF wz1_cut_flag = 14
		SWITCH_WIDESCREEN OFF
		SET_PLAYER_CONTROL player1 ON
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		IF NOT IS_CAR_DEAD wz1_getaway_car
			SET_CAR_HEAVY wz1_getaway_car FALSE
			SET_CAR_PROOFS wz1_getaway_car FALSE FALSE FALSE FALSE FALSE 
		ENDIF
		wz1_flag = 8
		wz1_scene_flag = 0
		play_audio = 63
		REMOVE_BLIP wz1_wuzi_blip
//		IF NOT IS_CHAR_DEAD wz1_wuzi
//			MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
//			SET_GROUP_MEMBER players_group wz1_wuzi
//			LISTEN_TO_PLAYER_GROUP_COMMANDS wz1_wuzi FALSE
//		ENDIF
		
//		wuzi_in_group = 1
	ENDIF
			

			
RETURN

  

// *****************************************************************************************
// ****************************** Destroy attacking Tong cars ******************************
// *****************************************************************************************


wz1_scene4:

	IF wz1_scene_flag = 0

		SET_POLICE_IGNORE_PLAYER Player1 OFF
		SET_PED_DENSITY_MULTIPLIER 1.0
		SET_CAR_DENSITY_MULTIPLIER 1.0

		IF NOT IS_CHAR_DEAD wz1_wuzi
			SET_CHAR_RELATIONSHIP wz1_wuzi ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
			SET_CHAR_SHOOT_RATE wz1_wuzi 80
			GIVE_WEAPON_TO_CHAR wz1_wuzi WEAPONTYPE_MICRO_UZI 99999
		ENDIF
		IF NOT IS_CAR_DEAD wz1_getaway_car
			IF NOT IS_CAR_DEAD wz1_carattack[0]
			AND NOT IS_CHAR_DEAD wz1_tong[0]

				TASK_CAR_MISSION wz1_tong[0] wz1_carattack[0] wz1_getaway_car MISSION_RAMPLAYER_FARAWAY 20.0 DRIVINGMODE_PLOUGHTHROUGH
//					SET_CAR_CRUISE_SPEED wz1_carattack[0] 20.0
//					SET_CAR_MISSION wz1_carattack[0] MISSION_RAMPLAYER_FARAWAY
				SET_CAR_HEALTH wz1_carattack[0] 800
				ADD_BLIP_FOR_CHAR wz1_tong[0] wz1_car_blip[0]
			ENDIF
			IF NOT IS_CAR_DEAD wz1_carattack[1] 
			AND NOT IS_CHAR_DEAD wz1_tong[2]

				TASK_CAR_MISSION wz1_tong[2] wz1_carattack[1] wz1_getaway_car MISSION_RAMPLAYER_FARAWAY 20.0 DRIVINGMODE_PLOUGHTHROUGH
//					SET_CAR_CRUISE_SPEED wz1_carattack[1] 20.0
//					SET_CAR_MISSION wz1_carattack[1] MISSION_RAMPLAYER_FARAWAY
				SET_CAR_HEALTH wz1_carattack[1] 800
				ADD_BLIP_FOR_CHAR wz1_tong[2] wz1_car_blip[1]
			ENDIF
		ENDIF
		IF NOT IS_CAR_DEAD wz1_getaway_car
			SET_CAR_HEALTH wz1_getaway_car 3000
			SET_CAN_BURST_CAR_TYRES wz1_getaway_car FALSE
		ENDIF	
		wz1_car_alive[0] = 1
		wz1_car_alive[1] = 1
		check_wuzi_in_group = 1
		wuzi_in_group = 0
		wz1_scene_flag = 1
		SET_PED_DENSITY_MULTIPLIER 1.0
		SET_CAR_DENSITY_MULTIPLIER 1.0
	ENDIF

	// Checks while Wuzi attempts to blow up attacking enemy cars.
	IF wz1_scene_flag = 1

		//If player gets out car make Wuzi follow

		LVAR_INT wuzi_command wz1_thats_one_down
		IF wuzi_command = 0
			IF play_audio = 0
		
				wuzi_command = 1
				PRINT WZ1_37 4000 1
			ENDIF
		ENDIF

		IF wz1_car_alive[0] = 1
			IF IS_CHAR_DEAD wz1_tong[0]
				IF wz1_thats_one_down = 0
				   wz1_thats_one_down = 1
				   play_audio = 64
				ENDIF	
				REMOVE_BLIP wz1_car_blip[0]
				wz1_car_alive[0] = 0
			ENDIF
		ENDIF
		IF wz1_car_alive[1] = 1
			IF IS_CHAR_DEAD wz1_tong[2]
				IF wz1_thats_one_down = 0
				   wz1_thats_one_down = 1
				   play_audio = 64
				ENDIF
				REMOVE_BLIP wz1_car_blip[1]
				wz1_car_alive[1] = 0
			ENDIF
		ENDIF
		IF wz1_car_alive[0] = 0
		AND wz1_car_alive[1] = 0
			IF NOT IS_CHAR_DEAD wz1_wuzi
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer wz1_wuzi 5.0 5.0 5.0 FALSE
				
					play_audio = 65
					play_audio_for = 3
				
					wz1_scene_flag = 2
					REMOVE_BLIP wz1_goto_blip
					ADD_BLIP_FOR_COORD -2151.5891 639.5962 51.218 wz1_goto_blip
					check_wuzi_in_group = 1
					wuzi_in_group = 0

				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF wz1_scene_flag = 2
		IF play_audio = 0
		LVAR_INT wz1_instructions
			IF wz1_instructions = 0
				wz1_instructions = 1
				PRINT WZ1_39 4000 1
			ENDIF
		ENDIF
		IF wuzi_in_group = 1
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2150.1650 642.3364 51.2188 4.0 4.0 4.0 TRUE
				IF NOT IS_CHAR_DEAD wz1_wuzi 
					IF LOCATE_CHAR_ANY_MEANS_3D wz1_wuzi -2150.1650 642.3364 51.2188 8.0 8.0 8.0 FALSE
						wz1_flag = 9
						wz1_cut_flag = 0
						play_audio = 68
					ENDIF
				ENDIF				
			ENDIF					
		ENDIF
	ENDIF
RETURN






// *****************************************************************************************
// ************* Wuzi gets out car - fade - game over **************************************
// *****************************************************************************************


wz1_cutscene_5:

	IF wz1_cut_flag = 0
		check_wuzi_in_group = 0
		SET_PLAYER_CONTROL player1 OFF
		IF IS_CHAR_IN_ANY_CAR scplayer
			GET_CAR_CHAR_IS_USING scplayer car
			TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000			
		ENDIF
		wz1_cut_time = TIMERA + 3000
		wz1_cut_flag = 1
	ENDIF

	IF wz1_cut_flag = 1
		IF play_audio = 0
			SET_FIXED_CAMERA_POSITION -2156.8723 649.7653 51.6094 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2156.3618 648.9217 51.7758 JUMP_CUT
			SWITCH_WIDESCREEN ON
			IF IS_CHAR_IN_ANY_CAR scplayer
				GET_CAR_CHAR_IS_USING scplayer car
				SET_CAR_COORDINATES car -2150.1650 642.3364 51.2188
				SET_CAR_HEADING car 0.0
			ENDIF

			IF NOT IS_CHAR_DEAD wz1_wuzi
				FLUSH_ROUTE
				EXTEND_ROUTE -2155.1499 645.1982 51.2293
				EXTEND_ROUTE -2162.0232 645.1555 51.7499

				IF IS_CHAR_IN_ANY_CAR wz1_wuzi
					REMOVE_CHAR_FROM_GROUP wz1_wuzi
					OPEN_SEQUENCE_TASK wz1_seq
						TASK_LEAVE_ANY_CAR -1
						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN 0
					CLOSE_SEQUENCE_TASK wz1_seq
					PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq 
					CLEAR_SEQUENCE_TASK wz1_seq
				ELSE
					TASK_FOLLOW_POINT_ROUTE wz1_wuzi PEDMOVE_RUN 0					
				ENDIF
			ENDIF
			wz1_cut_flag = 2
			wz1_cut_time = TIMERA + 5000													
		ENDIF
	ENDIF

	IF wz1_cut_flag = 2
		IF TIMERA > wz1_cut_time
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN OFF
			IF NOT IS_CHAR_DEAD wz1_wuzi
				DELETE_CHAR wz1_wuzi
			ENDIF
			wz1_wuzi_alive = 0
			SET_PLAYER_CONTROL PLAYER1 ON
			wz1_flag = 10
		ENDIF
	ENDIF
RETURN

			
		 

tong_events:

	IF wz1_tong_event > 0
		wz1_i = 0
		wz1_dead_tong_wave3 = 0
		WHILE wz1_i < 18
			IF DOES_CHAR_EXIST wz1_tong[wz1_i]
				IF IS_CHAR_DEAD wz1_tong[wz1_i]
					REMOVE_BLIP wz1_tong_blip[wz1_i]
					IF wz1_i > 7
						wz1_dead_tong_wave3 ++
					ENDIF
				ENDIF
			ENDIF
			wz1_i ++
		ENDWHILE
	ENDIF

	IF wz1_tong_event = 1
		IF player_y < 676.0
		OR wuzi_y < 676.0
			CREATE_CAR SANCHEZ -2191.2742 644.4189 48.4453 wz1_tongcar[1]
			CREATE_CAR SANCHEZ -2200.2051 644.3219 48.4429 wz1_tongcar[2]
			SET_CAR_HEADING wz1_tongcar[1] 270.0
			SET_CAR_HEADING wz1_tongcar[2] 270.0
			MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ

			REQUEST_CAR_RECORDING 139

			CREATE_CHAR_INSIDE_CAR wz1_tongcar[1] PEDTYPE_MISSION1 DNB3 wz1_tong[4]
			CREATE_CHAR_AS_PASSENGER wz1_tongcar[1] PEDTYPE_MISSION1 DNB1 0 wz1_tong[5]
			
			 
			CREATE_CHAR_INSIDE_CAR wz1_tongcar[2] PEDTYPE_MISSION1 DNB2 wz1_tong[6]
			CREATE_CHAR_AS_PASSENGER wz1_tongcar[2] PEDTYPE_MISSION1 DNB3 0 wz1_tong[7]

			MARK_MODEL_AS_NO_LONGER_NEEDED DNB1
			MARK_MODEL_AS_NO_LONGER_NEEDED DNB2

			GIVE_WEAPON_TO_CHAR wz1_tong[4] WEAPONTYPE_MICRO_UZI 500
			GIVE_WEAPON_TO_CHAR wz1_tong[5] WEAPONTYPE_MICRO_UZI 500
			GIVE_WEAPON_TO_CHAR wz1_tong[6] WEAPONTYPE_MICRO_UZI 500
			GIVE_WEAPON_TO_CHAR wz1_tong[7] WEAPONTYPE_MICRO_UZI 500

			SET_CHAR_ACCURACY wz1_tong[4] 80
			SET_CHAR_ACCURACY wz1_tong[5] 85
			SET_CHAR_ACCURACY wz1_tong[6] 90
			SET_CHAR_ACCURACY wz1_tong[7] 85

			ADD_BLIP_FOR_CHAR wz1_tong[4] wz1_tong_blip[4]
			ADD_BLIP_FOR_CHAR wz1_tong[5] wz1_tong_blip[5]
			ADD_BLIP_FOR_CHAR wz1_tong[6] wz1_tong_blip[6]
			ADD_BLIP_FOR_CHAR wz1_tong[7] wz1_tong_blip[7]

			SET_CHAR_DROPS_WEAPONS_WHEN_DEAD wz1_tong[4] FALSE
			SET_CHAR_DROPS_WEAPONS_WHEN_DEAD wz1_tong[5] FALSE
			SET_CHAR_DROPS_WEAPONS_WHEN_DEAD wz1_tong[6] FALSE
			SET_CHAR_DROPS_WEAPONS_WHEN_DEAD wz1_tong[7] FALSE

			SET_CAR_FORWARD_SPEED wz1_tongcar[1] 20.0
			SET_CAR_FORWARD_SPEED wz1_tongcar[2] 20.0
	   
			SET_CHAR_DECISION_MAKER wz1_tong[4] wz1_ped_decisions
			SET_CHAR_DECISION_MAKER wz1_tong[5] wz1_ped_decisions
			SET_CHAR_DECISION_MAKER wz1_tong[6] wz1_ped_decisions
			SET_CHAR_DECISION_MAKER wz1_tong[7] wz1_ped_decisions

			SET_SENSE_RANGE wz1_tong[4] 40.0
			SET_SENSE_RANGE wz1_tong[5] 40.0
			SET_SENSE_RANGE wz1_tong[6] 40.0
			SET_SENSE_RANGE wz1_tong[7] 40.0

			SET_INFORM_RESPECTED_FRIENDS wz1_tong[4] 30.0 8
			SET_INFORM_RESPECTED_FRIENDS wz1_tong[5] 30.0 8
			SET_INFORM_RESPECTED_FRIENDS wz1_tong[6] 30.0 8
			SET_INFORM_RESPECTED_FRIENDS wz1_tong[7] 30.0 8

			TASK_KINDA_STAY_IN_SAME_PLACE wz1_tong[4] TRUE
			TASK_KINDA_STAY_IN_SAME_PLACE wz1_tong[5] TRUE
			TASK_KINDA_STAY_IN_SAME_PLACE wz1_tong[6] TRUE
			TASK_KINDA_STAY_IN_SAME_PLACE wz1_tong[7] TRUE

//			CREATE_GROUP DEFAULT_TASK_ALLOCATOR_STAND_STILL wz1_tong_group
//			SET_GROUP_LEADER wz1_tong_group wz1_tong[4]   
//			SET_GROUP_MEMBER wz1_tong_group wz1_tong[5] 
//			SET_GROUP_MEMBER wz1_tong_group wz1_tong[6] 
//			SET_GROUP_MEMBER wz1_tong_group wz1_tong[7] 

			OPEN_SEQUENCE_TASK wz1_seq
				TASK_CAR_DRIVE_TO_COORD -1 wz1_tongcar[1] -2177.1646 645.6417 48.4453 15.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
				TASK_CAR_TEMP_ACTION -1 wz1_tongcar[1] TEMPACT_HANDBRAKETURNLEFT 2000 
				TASK_LEAVE_ANY_CAR -1
				TASK_GO_STRAIGHT_TO_COORD -1 -2172.8650 652.5621 48.4429 PEDMOVE_RUN 4000
				TASK_STAY_IN_SAME_PLACE -1 TRUE
			CLOSE_SEQUENCE_TASK wz1_seq
			PERFORM_SEQUENCE_TASK wz1_tong[4] wz1_seq
			CLEAR_SEQUENCE_TASK wz1_seq

			OPEN_SEQUENCE_TASK wz1_seq
				TASK_CAR_DRIVE_TO_COORD -1 wz1_tongcar[2] -2174.8108 644.3727 48.4453 15.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
				TASK_CAR_TEMP_ACTION -1 wz1_tongcar[2] TEMPACT_HANDBRAKETURNLEFT 2000 
				TASK_LEAVE_ANY_CAR -1
				TASK_GO_STRAIGHT_TO_COORD -1 -2176.9900 650.9326 48.4429 PEDMOVE_RUN 3000
				TASK_TOGGLE_DUCK -1 TRUE
			CLOSE_SEQUENCE_TASK wz1_seq
			PERFORM_SEQUENCE_TASK wz1_tong[6] wz1_seq
			CLEAR_SEQUENCE_TASK wz1_seq

			wz1_tong_event = 2
		ENDIF
	ENDIF

	IF wz1_tong_event = 2
		
		wz1_var1 = 0
		IF NOT IS_CHAR_DEAD wz1_tong[4]
			GET_CAR_CHAR_IS_USING wz1_tong[4] wz1_var1
		ENDIF

		IF wz1_var1	= -1
		OR IS_CHAR_DEAD wz1_tong[4]
			IF NOT IS_CHAR_DEAD wz1_tong[5]
				IF IS_CHAR_IN_ANY_CAR wz1_tong[5]
					TASK_LEAVE_ANY_CAR wz1_tong[5]
				ENDIF
			ENDIF
			IF NOT IS_CHAR_DEAD wz1_tong[6]
				IF IS_CHAR_IN_ANY_CAR wz1_tong[6]
					TASK_LEAVE_ANY_CAR wz1_tong[6]
				ENDIF
			ENDIF
			IF NOT IS_CHAR_DEAD wz1_tong[7]
				IF IS_CHAR_IN_ANY_CAR wz1_tong[7]
					TASK_LEAVE_ANY_CAR wz1_tong[7]
				ENDIF
			ENDIF


			wz1_tong_event = 3
		ENDIF			
	ENDIF

	IF wz1_tong_event = 4

		// tong hiding behind corner rolls out
		IF wz1_tong_rolls_out = 0
			IF NOT IS_CHAR_DEAD wz1_tong[13]			
				IF player_x < -2193.0
				OR wuzi_x < -2193.0
					wz1_tong_rolls_out = 1
				
					OPEN_SEQUENCE_TASK wz1_seq
						TASK_PLAY_ANIM -1 Crouch_Roll_R PED 8.0 FALSE TRUE TRUE FALSE -1
						TASK_STAY_IN_SAME_PLACE -1 TRUE
						TASK_TOGGLE_DUCK -1 TRUE
					CLOSE_SEQUENCE_TASK wz1_seq
					PERFORM_SEQUENCE_TASK wz1_tong[13] wz1_seq
					CLEAR_SEQUENCE_TASK wz1_seq
				ENDIF
			ENDIF
		ENDIF

		// tong attack player hard if he gets in a vehicle
		IF IS_CHAR_IN_AREA_3D scplayer -2171.8572 658.2869 52.1667 -2240.3035 631.7034 48.0251 FALSE
			IF IS_CHAR_IN_ANY_CAR scplayer
				IF wz1_player_in_car = 0
					wz1_player_in_car = 1
					wz1_i = 9
					WHILE wz1_i < 13
						IF NOT IS_CHAR_DEAD wz1_tong[wz1_i]
							SET_CHAR_ACCURACY wz1_tong[wz1_i] 100
							TASK_SHOOT_AT_CHAR wz1_tong[wz1_i] scplayer -2
						ENDIF
						wz1_i ++
					ENDWHILE
				ENDIF
			ELSE
				IF wz1_player_in_car = 1
					wz1_player_in_car = 0
					wz1_i = 9
					WHILE wz1_i < 13
						IF NOT IS_CHAR_DEAD wz1_tong[wz1_i]
							SET_CHAR_ACCURACY wz1_tong[wz1_i] 80
							CLEAR_CHAR_TASKS wz1_tong[wz1_i]
						ENDIF
						wz1_i ++
					ENDWHILE
				ENDIF
			ENDIF
		ENDIF

		// tong slowly advance down the alley
		IF wz1_advance_flag = 0
			IF TIMERA > wz1_advance_time
				GENERATE_RANDOM_INT_IN_RANGE 9 13 wz1_i
				wz1_exit = 0
				WHILE wz1_exit < 4
					wz1_int = wz1_i - 9
					IF NOT IS_CHAR_DEAD wz1_tong[wz1_i]
					AND wz1_tong_advanced[wz1_int] = 0
						wz1_tong_advanced[wz1_int] = 1
						FLUSH_ROUTE
						EXTEND_ROUTE wz1_tong_node_x[wz1_int] wz1_tong_node_y[wz1_int] wz1_tong_node_z[wz1_int] 
						EXTEND_ROUTE wz1_tong_advance_x[wz1_int] wz1_tong_advance_y[wz1_int] wz1_tong_advance_z[wz1_int]
						TASK_FOLLOW_POINT_ROUTE wz1_tong[wz1_i] PEDMOVE_RUN FOLLOW_ROUTE_ONCE
						wz1_exit = 4
					ELSE
						wz1_i ++
						IF wz1_i > 12
							wz1_i = 9
						ENDIF
					ENDIF
					wz1_exit ++				
				ENDWHILE
				wz1_advance_time = TIMERA + 10000
			ENDIF
		ENDIF

		// more tong turn up when player runs beyond a certain point or has killed a certain number of existing tong
		IF wz1_create_tong_wave3 = 0
			IF player_x < -2197.0
			OR wz1_dead_tong_wave3 > 1
				wz1_create_tong_wave3 = 1
				CREATE_CHAR PEDTYPE_MISSION1 DNB3 -2205.8611 625.2820 48.4396 wz1_tong[14]	
				CREATE_CHAR PEDTYPE_MISSION1 DNB1 -2207.0764 627.3953 48.4384 wz1_tong[15]
				GIVE_WEAPON_TO_CHAR wz1_tong[14] WEAPONTYPE_MICRO_UZI 500
				GIVE_WEAPON_TO_CHAR wz1_tong[15] WEAPONTYPE_MICRO_UZI 500
				FLUSH_ROUTE
				EXTEND_ROUTE -2205.8076 626.6268 48.4429
				EXTEND_ROUTE -2205.8489 635.5339 48.4453
				TASK_FOLLOW_POINT_ROUTE wz1_tong[14] PEDMOVE_RUN FOLLOW_ROUTE_ONCE
				FLUSH_ROUTE
				EXTEND_ROUTE -2205.8076 626.6268 48.4429
				EXTEND_ROUTE -2204.5803 634.2507 48.4453
				TASK_FOLLOW_POINT_ROUTE wz1_tong[15] PEDMOVE_RUN FOLLOW_ROUTE_ONCE

				CREATE_CAR SENTINEL -2267.3218 659.5334 48.2969 wz1_getaway_car
				SET_CAR_PROOFS wz1_getaway_car TRUE TRUE TRUE TRUE TRUE
				SET_CAR_HEADING	wz1_getaway_car 344.0
				LOCK_CAR_DOORS wz1_getaway_car CARLOCK_LOCKOUT_PLAYER_ONLY
				CREATE_CHAR_INSIDE_CAR wz1_getaway_car PEDTYPE_MISSION1 DNB1 wz1_tong[16]
				CREATE_CHAR_AS_PASSENGER wz1_getaway_car PEDTYPE_MISSION1 DNB2 0 wz1_tong[17]
				GIVE_WEAPON_TO_CHAR wz1_tong[16] WEAPONTYPE_MICRO_UZI 500
				GIVE_WEAPON_TO_CHAR wz1_tong[17] WEAPONTYPE_MICRO_UZI 500
				SET_CHAR_DECISION_MAKER wz1_tong[14] wz1_ped_decisions
				SET_CHAR_DECISION_MAKER wz1_tong[15] wz1_ped_decisions
				SET_CHAR_DECISION_MAKER wz1_tong[16] wz1_ped_decisions
				SET_CHAR_DECISION_MAKER wz1_tong[17] wz1_ped_decisions
				ADD_BLIP_FOR_CHAR wz1_tong[14] wz1_tong_blip[14]
				ADD_BLIP_FOR_CHAR wz1_tong[15] wz1_tong_blip[15]
				ADD_BLIP_FOR_CHAR wz1_tong[16] wz1_tong_blip[16]
				ADD_BLIP_FOR_CHAR wz1_tong[17] wz1_tong_blip[17]
				SET_SENSE_RANGE wz1_tong[14] 40.0
				SET_SENSE_RANGE wz1_tong[15] 40.0
				SET_SENSE_RANGE wz1_tong[16] 40.0
				SET_SENSE_RANGE wz1_tong[17] 40.0

				SET_INFORM_RESPECTED_FRIENDS wz1_tong[14] 30.0 8
				SET_INFORM_RESPECTED_FRIENDS wz1_tong[15] 30.0 8
				SET_INFORM_RESPECTED_FRIENDS wz1_tong[16] 30.0 8
				SET_INFORM_RESPECTED_FRIENDS wz1_tong[17] 30.0 8

				START_PLAYBACK_RECORDED_CAR wz1_getaway_car 139
			ENDIF
		ENDIF

		IF wz1_create_tong_wave3 = 1
			IF NOT IS_CAR_DEAD wz1_getaway_car
				GET_CAR_COORDINATES wz1_getaway_car x y z
				IF x > -2227.0						
					wz1_create_tong_wave3 = 2
					IF NOT IS_CHAR_DEAD wz1_tong[16]
						FLUSH_ROUTE
						EXTEND_ROUTE -2214.6328 642.2872 48.447
						OPEN_SEQUENCE_TASK wz1_seq
							TASK_LEAVE_ANY_CAR -1
							TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
						CLOSE_SEQUENCE_TASK wz1_seq
						PERFORM_SEQUENCE_TASK wz1_tong[16] wz1_seq						 
						CLEAR_SEQUENCE_TASK wz1_seq
					ENDIF
					IF NOT IS_CHAR_DEAD wz1_tong[16]
						FLUSH_ROUTE
						EXTEND_ROUTE -2214.3730 639.3536 48.4511
						OPEN_SEQUENCE_TASK wz1_seq
							TASK_LEAVE_ANY_CAR -1
							TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
						CLOSE_SEQUENCE_TASK wz1_seq
						PERFORM_SEQUENCE_TASK wz1_tong[17] wz1_seq						 			
						CLEAR_SEQUENCE_TASK wz1_seq
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF wz1_create_tong_wave3 = 2
			IF NOT IS_CAR_DEAD wz1_getaway_car 			
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR  wz1_getaway_car
					wz1_create_tong_wave3 = 3
					FREEZE_CAR_POSITION wz1_getaway_car TRUE
				ENDIF
			ENDIF
		ENDIF

	ENDIF


	


RETURN	
 
			
wuzi_comments:
	IF NOT IS_CHAR_DEAD wz1_wuzi


		LVAR_INT insults_given wuzi_i last_wuzi_insult_time
		IF IS_CHAR_SHOOTING wz1_wuzi
//		OR wuzi_been_hit = 1
			IF TIMERA > last_wuzi_insult_time
				IF play_audio = 0
					IF insults_given < 6
						GENERATE_RANDOM_INT_IN_RANGE 0 6 wuzi_i
						WHILE wuzi_insult[wuzi_i] = 0
							wuzi_i ++
							IF wuzi_i > 5
								wuzi_i = 0
							ENDIF
						ENDWHILE
						insults_given ++
						play_audio = wuzi_insult[wuzi_i]
						wuzi_insult[wuzi_i] = 0
						wuzi_been_hit = 0
						last_wuzi_insult_time = TIMERA + 8000
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

RETURN
			
							


wuzi_events:
/*
IF NOT IS_CHAR_DEAD wz1_wuzi


//	LVAR_INT insults_given wuzi_i last_wuzi_insult_time
	IF IS_CHAR_SHOOTING wz1_wuzi
	OR wuzi_been_hit = 1
		IF TIMERA > last_wuzi_insult_time
			IF play_audio = 0
				IF insults_given < 6
					GENERATE_RANDOM_INT_IN_RANGE 0 6 wuzi_i
					WHILE wuzi_insult[wuzi_i] = 0
						wuzi_i ++
						IF wuzi_i > 5
							wuzi_i = 0
						ENDIF
					ENDWHILE
					insults_given ++
					play_audio = wuzi_insult[wuzi_i]
					wuzi_insult[wuzi_i] = 0
					wuzi_been_hit = 0
					last_wuzi_insult_time = TIMERA + 8000
				ENDIF
			ENDIF
		ENDIF
	ENDIF
				

	

	IF wz1_escape_flag = 0
		REQUEST_ANIMATION SWAT
		wz1_escape_flag = 1
	ENDIF
	
	IF wz1_escape_flag = 1
		IF HAS_ANIMATION_LOADED SWAT
			wz1_escape_flag = 2
		ENDIF
	ENDIF

	IF wz1_escape_flag = 2
	AND NOT IS_MESSAGE_BEING_DISPLAYED

		play_audio = 30
		play_audio_for = 2
		
		OPEN_SEQUENCE_TASK wz1_seq
			TASK_GO_STRAIGHT_TO_COORD -1 -2179.8970 695.2466 52.8984 PEDMOVE_RUN -2
			TASK_ACHIEVE_HEADING -1 180.0
			TASK_PLAY_ANIM -1 swt_wllpk_R SWAT 8.0 FALSE FALSE FALSE FALSE -1
		CLOSE_SEQUENCE_TASK wz1_seq
		PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
		CLEAR_SEQUENCE_TASK wz1_seq
		wz1_escape_flag = 3
	ENDIF
	IF wz1_escape_flag = 3

		GET_SCRIPT_TASK_STATUS wz1_wuzi PERFORM_SEQUENCE_TASK wz1_task_progress
		IF wz1_task_progress = FINISHED_TASK
		AND play_audio = 0
			play_audio = 34

			SET_SENSE_RANGE wz1_wuzi 15.0

//			PRINT wz1_18 3000 1
			OPEN_SEQUENCE_TASK wz1_seq
				TASK_GO_STRAIGHT_TO_COORD -1 -2173.8037 686.5518 52.8963 PEDMOVE_RUN -2
				TASK_TOGGLE_DUCK -1 TRUE
				TASK_SHOOT_AT_COORD -1 -2177.3369 678.3412 54.594 3000
			CLOSE_SEQUENCE_TASK wz1_seq
			PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
			CLEAR_SEQUENCE_TASK wz1_seq
			wz1_escape_flag = 4
			wz1_escape_timer = TIMERA + 8000
		ENDIF
	ENDIF
	IF wz1_escape_flag = 4
		IF TIMERA > wz1_escape_timer 
			FLUSH_ROUTE
			EXTEND_ROUTE -2175.0884 686.2910 52.8953  
			EXTEND_ROUTE -2176.9126 672.3929 51.4392 
			OPEN_SEQUENCE_TASK wz1_seq
				TASK_FOLLOW_POINT_ROUTE	-1 PEDMOVE_WALK 0
				TASK_SHOOT_AT_COORD -1 -2174.8970 648.7093 50.2127 3000
			CLOSE_SEQUENCE_TASK wz1_seq
			PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
			CLEAR_SEQUENCE_TASK wz1_Seq

			
			wz1_escape_timer = TIMERA + 10000
			wz1_escape_flag = 5
		ENDIF
	ENDIF
	IF wz1_escape_flag = 5
		IF TIMERA > wz1_escape_timer
								  
		OPEN_SEQUENCE_TASK wz1_seq
			TASK_GO_STRAIGHT_TO_COORD -1 -2178.0400 662.1946 48.6751 PEDMOVE_RUN -2
			TASK_SHOOT_AT_COORD -1 -2174.5613 642.6210 50.3702 3000
		CLOSE_SEQUENCE_TASK wz1_seq
		PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
		CLEAR_SEQUENCE_TASK wz1_Seq
		wz1_escape_timer = TIMERA + 10000
		wz1_escape_flag = 6
		ENDIF
	ENDIF

	IF wz1_escape_flag = 6
		IF TIMERA > wz1_escape_timer
			OPEN_SEQUENCE_TASK wz1_seq
				TASK_GO_STRAIGHT_TO_COORD -1 -2178.7188 649.5121 48.4429 PEDMOVE_RUN -2
			CLOSE_SEQUENCE_TASK wz1_seq
			PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
			CLEAR_SEQUENCE_TASK wz1_Seq
			wz1_escape_flag = 7
		ENDIF
	ENDIF

	IF wz1_escape_flag = 8
		wz1_escape_timer = TIMERA + 5000
		wz1_escape_flag = 9
	ENDIF

	IF wz1_escape_flag = 9
		IF TIMERA > wz1_escape_timer
			wz1_escape_flag = 11
			wz1_escape_timer = TIMERA + 3000
		ENDIF
	ENDIF

	IF wz1_escape_flag = 11
		IF TIMERA > wz1_escape_timer
			wz1_escape_flag = 12
		ENDIF
	ENDIF

	IF wz1_escape_flag = 12
		IF NOT IS_CHAR_DEAD wz1_wuzi
		AND play_audio = 0
			play_audio = 44			
			OPEN_SEQUENCE_TASK wz1_seq2
				TASK_TOGGLE_DUCK -1 FALSE
				TASK_SHOOT_AT_COORD -1 -2210.0557 640.7878 49.6149 3000
				TASK_TOGGLE_DUCK -1 TRUE
				TASK_PAUSE -1 2000				
				SET_SEQUENCE_TO_REPEAT wz1_seq2 2
			CLOSE_SEQUENCE_TASK wz1_seq2
 
			OPEN_SEQUENCE_TASK wz1_seq
				TASK_FOLLOW_PATH_NODES_TO_COORD -1 -2191.2148 645.9471 48.445 PEDMOVE_WALK -2	
				PERFORM_SEQUENCE_TASK -1 wz1_seq2
			CLOSE_SEQUENCE_TASK wz1_seq
			PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
			CLEAR_SEQUENCE_TASK wz1_seq
			CLEAR_SEQUENCE_TASK wz1_seq2
			wz1_escape_flag = 13
		ENDIF
	ENDIF

	IF wz1_escape_flag = 13
		IF NOT IS_CHAR_DEAD wz1_wuzi
			GET_SCRIPT_TASK_STATUS wz1_wuzi PERFORM_SEQUENCE_TASK wz1_task_status
			IF wz1_task_status = FINISHED_TASK
				wz1_escape_timer = TIMERA + 5000
				wz1_escape_flag = 14
			ENDIF
		ENDIF
	ENDIF

	IF wz1_escape_flag = 14
		IF TIMERA > wz1_escape_timer
			wz1_escape_flag = 16	
		ENDIF	
	ENDIF

	IF wz1_escape_flag = 15
		IF TIMERA > wz1_escape_timer

			wz1_escape_flag = 16
		ENDIF
	ENDIF

	IF wz1_escape_flag = 16
		IF NOT IS_CHAR_DEAD wz1_wuzi
		AND play_audio = 0
			CLEAR_HELP
			play_audio = 45
			OPEN_SEQUENCE_TASK wz1_seq2
				TASK_TOGGLE_DUCK -1 FALSE
				TASK_SHOOT_AT_COORD -1 -2212.8311 641.2944 50.2922 3000
				TASK_TOGGLE_DUCK -1 TRUE
				TASK_PAUSE -1 2000				
				SET_SEQUENCE_TO_REPEAT wz1_seq2 2
			CLOSE_SEQUENCE_TASK wz1_seq2
 
			OPEN_SEQUENCE_TASK wz1_seq
				TASK_FOLLOW_PATH_NODES_TO_COORD -1 -2200.2542 639.6259 48.4453 PEDMOVE_WALK -2	
				PERFORM_SEQUENCE_TASK -1 wz1_seq2
			CLOSE_SEQUENCE_TASK wz1_seq
			PERFORM_SEQUENCE_TASK wz1_wuzi wz1_seq
			CLEAR_SEQUENCE_TASK wz1_seq
			CLEAR_SEQUENCE_TASK wz1_seq2
			wz1_escape_flag = 17
		ENDIF
	ENDIF

				



ENDIF
*/
RETURN	 


wuzi_health:
	IF wz1_calculate_wuzi_health = 1
		IF NOT IS_CHAR_DEAD wz1_wuzi
			LVAR_INT old_health wuzi_been_hit
			old_health = wz1_health
			GET_CHAR_HEALTH wz1_wuzi wz1_health
			IF NOT wz1_health = old_health
				old_health = wz1_health
				wuzi_been_hit = 1
			ENDIF
			wz1_health_bar = wz1_health * 100
			wz1_health_bar /= 250
		ENDIF
	ENDIF
RETURN

tong_threat_scan:


/*
	IF TIMERA > wz1_next_tong_time
		wz1_next_tong_time = TIMERA + 100

		wz1_r ++
		IF wz1_r > 17
			wz1_r = 0
		ENDIF


		IF DOES_CHAR_EXIST wz1_tong[wz1_r]
			IF NOT IS_CHAR_DEAD wz1_tong[wz1_r]
//				CLEAR_CHAR_TASKS wz1_tong[wz1_r]
				GET_SCRIPT_TASK_STATUS wz1_tong[wz1_r] -1 wz1_task_Status
				wz1_i = 0
				IF wz1_task_Status = FINISHED_TASK
					wz1_i = 1
				ENDIF
				IF IS_CHAR_DUCKING wz1_tong[wz1_r]
					wz1_i = 1
				ENDIF
				IF wz1_i = 1	
					IF NOT IS_CHAR_IN_ANY_CAR wz1_tong[wz1_r]
						IF NOT IS_CHAR_DEAD wz1_wuzi
							GET_CHAR_COORDINATES wz1_tong[wz1_r] x y z
							GET_DISTANCE_BETWEEN_COORDS_2D x y wuzi_x wuzi_y wz1_float1
							GET_DISTANCE_BETWEEN_COORDS_2D x y player_X player_Y wz1_float2
							wz1_float3 = wz1_float1 - wz1_float2
							ABS wz1_float3
							IF wz1_float3 < 4.0
								GENERATE_RANDOM_INT_IN_RANGE 0 2 wz1_i
								IF wz1_i = 0
									wz1_float1 += 5.0
								ELSE
									wz1_float1 -= 5.0
								ENDIF
							ENDIF
								
							IF NOT IS_CHAR_RESPONDING_TO_EVENT wz1_tong[wz1_r] EVENT_ACQUAINTANCE_PED_HATE
								wz1_tong_target[wz1_r] = 0	
							ENDIF
									
							IF wz1_float1 > wz1_float2
								IF wz1_tong_target[wz1_r] = 1
								OR wz1_tong_target[wz1_r] = 0
									wz1_tong_target[wz1_r] = 2 //player
									CLEAR_ALL_CHAR_RELATIONSHIPS wz1_tong[wz1_r] ACQUAINTANCE_TYPE_PED_HATE
									CLEAR_ALL_CHAR_RELATIONSHIPS wz1_tong[wz1_r] ACQUAINTANCE_TYPE_PED_HATE //was like
									CLEAR_CHAR_TASKS wz1_tong[wz1_r]
									SET_CHAR_RELATIONSHIP wz1_tong[wz1_r] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
									SET_CHAR_RELATIONSHIP wz1_tong[wz1_r] ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION2
								ENDIF
							ELSE
								IF wz1_tong_target[wz1_r] = 2
								OR wz1_tong_target[wz1_r] = 0
									wz1_tong_target[wz1_r] = 1 //player
									CLEAR_ALL_CHAR_RELATIONSHIPS wz1_tong[wz1_r] ACQUAINTANCE_TYPE_PED_HATE
									CLEAR_ALL_CHAR_RELATIONSHIPS wz1_tong[wz1_r] ACQUAINTANCE_TYPE_PED_HATE //was like
									CLEAR_CHAR_TASKS wz1_tong[wz1_r]
									SET_CHAR_RELATIONSHIP wz1_tong[wz1_r] ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_PLAYER1
									SET_CHAR_RELATIONSHIP wz1_tong[wz1_r] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2
								ENDIF
							ENDIF
						ENDIF
  					ENDIF
				ENDIF				
			ENDIF
		ENDIF
	ENDIF

*/


RETURN
//
//tong_attack:
// 
//
//
//	IF TIMERA > wz1_tong_group_attack_time
//		IF NOT IS_CHAR_DEAD wz1_wuzi
//			wz1_tong_group_attack_time = TIMERA + 300
//			wz_i = 0
//			WHILE wz_i < wz1_upper_check
//				
//				IF NOT IS_CHAR_DEAD wz1_tong[wz_i]
//					IF TIMERA > wz1_this_tong_attack_time[wz_i]
//						GENERATE_RANDOM_INT_IN_RANGE 0 3000 wz1_random
//						wz1_this_tong_attack_time[wz_i] = 3000 + wz1_random
//						wz1_this_tong_attack_time[wz_i] += TIMERA
//						GENERATE_RANDOM_INT_IN_RANGE 0 2 wz1_random
//						IF wz1_random = 0
//							IF HAS_CHAR_SPOTTED_CHAR wz1_tong[wz_i] scplayer				
//								IF NOT wz1_tong_target[wz_i] = 1
//									TASK_KILL_CHAR_ON_FOOT wz1_tong[wz_i] scplayer
//									wz1_tong_target[wz_i] = 1
//								ENDIF
//							ELSE
//								IF NOT wz1_tong_target[wz_i] = 1
//									TASK_KILL_CHAR_ON_FOOT wz1_tong[wz_i] wz1_wuzi
//									wz1_tong_target[wz_i] = 1
//								ENDIF
//							ENDIF
//						ELSE
//							IF HAS_CHAR_SPOTTED_CHAR wz1_tong[wz_i] wz1_wuzi
//								IF NOT wz1_tong_target[wz_i] = 1
//									TASK_KILL_CHAR_ON_FOOT wz1_tong[wz_i] wz1_wuzi
//									wz1_tong_target[wz_i] = 1
//								ENDIF
//							ELSE
//								IF NOT wz1_tong_target[wz_i] = 1
//									TASK_KILL_CHAR_ON_FOOT wz1_tong[wz_i] scplayer
//									wz1_tong_target[wz_i] = 1
//								ENDIF
//							ENDIF
//						ENDIF
//					ENDIF
//				ENDIF
//				wz_i ++
//			ENDWHILE
//		ENDIF
//	ENDIF													 
//
//RETURN



wuzi1_audio:



	// SOUND EFFECTS WORK HERE

	IF NOT effect_to_play[0] = 0

		IF effect_flag = 0
			CLEAR_MISSION_AUDIO 3
			LOAD_MISSION_AUDIO 3 effect_to_play[0]			
			effect_flag = 1
		ENDIF

		IF effect_flag = 1
			IF HAS_MISSION_AUDIO_LOADED 3
				IF TIMERA > effect_time_to_play[0]
					PLAY_MISSION_AUDIO 3
					effect_flag = 2					
				ENDIF
			ENDIF
		ENDIF

		IF effect_flag = 2
		OR IS_SKIP_CUTSCENE_BUTTON_PRESSED
			IF HAS_MISSION_AUDIO_FINISHED 3
				CLEAR_MISSION_AUDIO 3
				effect_to_play[0] = effect_to_play[1]
				effect_to_play[1] = 0
				effect_time_to_play[0] = effect_time_to_play[1]
				effect_time_to_play[1] = 0
				effect_flag = 0
			ENDIF
		ENDIF	
	ENDIF

	LVAR_INT effect_flag effect_to_play[2] effect_time_to_play[2]














	LVAR_INT play_timed_audio play_timed_audio_flag audio_time_start audio_timer_flag audio_time[20] audio_to_play[20] play_timed_audio_for
	LVAR_INT audio_plays_global // flag for peds do speech anims but dialogue not attached to ped.
 


	// play timed audio
	IF NOT play_timed_audio = 0
		IF play_timed_audio_flag = 0
			play_timed_audio_flag = 1
			audio_time_start = TIMERA
			audio_timer_flag = 0
			play_delay = audio_time_start + audio_time[audio_timer_flag]
		ENDIF	

		IF play_timed_audio_flag = 1
			IF TIMERA > play_delay
			AND play_audio = 0
				play_audio = audio_to_play[audio_timer_flag]
				audio_timer_flag ++
				play_timed_audio_for --
				IF play_timed_audio_for = 0
					play_timed_audio = 0
					play_timed_audio_flag = 0
				ELSE
					play_delay = audio_time_start + audio_time[audio_timer_flag]
				ENDIF
			ENDIF
		ENDIF
	ENDIF
		

		


	SWITCH audio_flag




	

		CASE 0 //first time game starts


			
			LVAR_TEXT_LABEL audio_text[100]
			LVAR_INT audio_sound[100] audio_slot[3] play_slot  
			LVAR_INT next_audio  
			LVAR_INT audio_flag play_audio play_audio_for 
			VAR_INT play_audio_global // make sounds not attached to char

			

			LVAR_INT audio_for_char[100] audio_actor[7] play_audio_now
			LVAR_INT actor_int this_actor loaded_audio play_delay audio_i audio_char audio_count force_audio play_audio_delay

//			$audio_text[0] = &CAT4_BI

			IF audio_flag = 1
				CREATE_CHAR PEDTYPE_GANG_GROVE SPECIAL01 2752.3408 -1948.8669 16.3125 this_actor
				CREATE_CHAR PEDTYPE_GANG_GROVE SPECIAL01 2752.3408 -1948.8669 16.3125 audio_char
			ENDIF


			audio_sound[1] = SOUND_WUZ1_AA
			audio_sound[2] = SOUND_WUZ1_AB
			audio_sound[3] = SOUND_WUZ1_CA
			audio_sound[4] = SOUND_WUZ1_CB
			audio_sound[5] = SOUND_WUZ1_CC
			audio_sound[6] = SOUND_WUZ1_CD
			audio_sound[7] = SOUND_WUZ1_CE
			audio_sound[8] = SOUND_WUZ1_CF
			audio_sound[9] = SOUND_WUZ1_CG
			audio_sound[10] = SOUND_WUZ1_CH
			audio_sound[11] = SOUND_WUZ1_DA
			audio_sound[12] = SOUND_WUZ1_DD
			audio_sound[13] = SOUND_WUZ1_DB
			audio_sound[14] = SOUND_WUZ1_DC
			audio_sound[15] = SOUND_WUZ1_LA
			audio_sound[16] = SOUND_WUZ1_LC
			audio_sound[17] = SOUND_WUZ1_KD
			audio_sound[18] = SOUND_WUZ1_MA
			audio_sound[19] = SOUND_WUZ1_MB
			audio_sound[20] = SOUND_WUZ1_MC
			audio_sound[21] = SOUND_WUZ1_MD
			audio_sound[22] = SOUND_WUZ1_ME
			audio_sound[23] = SOUND_WUZ1_MF
			audio_sound[24] = SOUND_WUZ1_MG
			audio_sound[25] = SOUND_WUZ1_MH
			audio_sound[26] = SOUND_WUZ1_MJ
			audio_sound[27] = SOUND_WUZ1_
			audio_sound[28] = SOUND_WUZ1_
			audio_sound[29] = SOUND_WUZ1_
			audio_sound[30] = SOUND_WUZ1_NA
			audio_sound[31] = SOUND_WUZ1_NB
			audio_sound[32] = SOUND_WUZ1_NC
			audio_sound[33] = SOUND_WUZ1_ND
			audio_sound[34] = SOUND_WUZ1_NE
			audio_sound[35] = SOUND_WUZ1_NF
			audio_sound[36] = SOUND_WUZ1_NG
			audio_sound[37] = SOUND_WUZ1_NH
			audio_sound[38] = SOUND_WUZ1_NJ
			audio_sound[39] = SOUND_WUZ1_NK
			audio_sound[40] = SOUND_WUZ1_NL
			audio_sound[41] = SOUND_WUZ1_NM
			audio_sound[42] = SOUND_WUZ1_NN
			audio_sound[43] = SOUND_WUZ1_NO
			audio_sound[44] = SOUND_WUZ1_NP
			audio_sound[45] = SOUND_WUZ1_NQ
			audio_sound[46] = SOUND_WUZ1_OA
			audio_sound[47] = SOUND_WUZ1_OB
			audio_sound[48] = SOUND_WUZ1_OC
			audio_sound[49] = SOUND_WUZ1_OD
			audio_sound[50] = SOUND_WUZ1_OE
			audio_sound[51] = SOUND_WUZ1_OF
			audio_sound[52] = SOUND_WUZ1_OG
			audio_sound[53] = SOUND_WUZ1_OH
			audio_sound[54] = SOUND_WUZ1_OJ
			audio_sound[55] = SOUND_WUZ1_OK
			audio_sound[56] = SOUND_WUZ1_OL
			audio_sound[57] = SOUND_WUZ1_OM
			audio_sound[58] = SOUND_WUZ1_ON
			audio_sound[59] = SOUND_WUZ1_OO
			audio_sound[60] = SOUND_WUZ1_OP
			audio_sound[61] = SOUND_WUZ1_OQ
			audio_sound[62] = SOUND_WUZ1_OR
			audio_sound[63] = SOUND_WUZ1_PA
			audio_sound[64] = SOUND_WUZ1_PB
			audio_sound[65] = SOUND_WUZ1_QA
			audio_sound[66] = SOUND_WUZ1_RA
			audio_sound[67] = SOUND_WUZ1_QB
			audio_sound[68] = SOUND_WUZ1_RD
			audio_sound[69] = SOUND_WUZ1_EA
			audio_sound[70] = SOUND_WUZ1_EB
			audio_sound[71] = SOUND_WUZ1_EC
			audio_sound[72] = SOUND_WUZ1_EE
			audio_sound[73] = SOUND_WUZ1_GJ
			audio_sound[74] = SOUND_WUZ1_JA
			audio_sound[75] = SOUND_WUZ1_JB
			audio_sound[76] = SOUND_WUZ1_JC
			audio_sound[77] = SOUND_WUZ1_GC
			audio_sound[78] = SOUND_WUZ1_KA
			audio_sound[79] = SOUND_WUZ1_KB
			audio_sound[80] = SOUND_WUZ1_KC
			audio_sound[81] = SOUND_WUZ1_KD
			audio_sound[82] = SOUND_WUZ1_GE
			audio_sound[83] = SOUND_WUZ1_GF 
			audio_sound[84] = SOUND_WUZ1_FA 
			audio_sound[85] = SOUND_WUZ1_FB 
			audio_sound[86] = SOUND_WUZ1_FC 
			audio_sound[87] = SOUND_WUZ1_FD 
			audio_sound[88] = SOUND_WUZ1_FE
			audio_sound[89] = SOUND_WUZ1_HA 
			audio_sound[90] = SOUND_WUZ1_HB
			audio_sound[91] = SOUND_WUZ1_HC
			audio_sound[92] = SOUND_WUZ1_OM
			audio_sound[93] = SOUND_WUZ1_OK








			$audio_text[1] = &WUZ1_AA //We’ll need a ride – mine’s getting some bodywork done to it.
			$audio_text[2] = &WUZ1_AB //Ok, not a problem
			$audio_text[3] = &WUZ1_CA //The Blood Feather Triad have a storehouse around the block.
			$audio_text[4] = &WUZ1_CB //We shall see what excuses they have to offer.
			$audio_text[5] = &WUZ1_CC //Ok.  So what’s all this talk of business?
			$audio_text[6] = &WUZ1_CD //Some small time Vietnamese gangs have been making trouble lately. 
			$audio_text[7] = &WUZ1_CE //We’re not sure why they are gaining any courage now, but I’m nervous about the situation.
			$audio_text[9] = &WUZ1_CG //How do I fit in all of this?
			$audio_text[10] = &WUZ1_CH //You’re an outsider.
			$audio_text[11] = &WUZ1_DA //This is the place!
			$audio_text[12] = &WUZ1_DD //Come on, it’s this way.
			$audio_text[13] = &WUZ1_DB //What’s that smell?
			$audio_text[14] = &WUZ1_DC //Laundry.
			$audio_text[15] = &WUZ1_LA //Ah, we’re here! This way!
			$audio_text[16] = &WUZ1_LC //Drop by Emmet’s and get strapped!
			$audio_text[17] = &WUZ1_KD //Drop by Emmet’s and get strapped!
			$audio_text[18] = &WUZ1_MA //Drop by Emmet’s and get strapped!
			$audio_text[19] = &WUZ1_MB //Drop by Emmet’s and get strapped!
			$audio_text[20] = &WUZ1_MC //Drop by Emmet’s and get strapped!
			$audio_text[21] = &WUZ1_MD //Drop by Emmet’s and get strapped!
			$audio_text[22] = &WUZ1_ME //Drop by Emmet’s and get strapped!
			$audio_text[23] = &WUZ1_MF //Drop by Emmet’s and get strapped
			$audio_text[24] = &WUZ1_MG //Drop by Emmet’s and get strapped
			$audio_text[25] = &WUZ1_MH //Drop by Emmet’s and get strapped
			$audio_text[26] = &WUZ1_MJ //Drop by Emmet’s and get strapped
			$audio_text[28] = &WUZ1_MH //Drop by Emmet’s and get strapped!
			$audio_text[29] = &WUZ1_MJ //Drop by Emmet’s and get strapped!
			$audio_text[30] = &WUZ1_NA //The Triads must have vengeance!
			$audio_text[31] = &WUZ1_NB //CJ, follow me and stick close!
			$audio_text[32] = &WUZ1_NC //Ready?
			$audio_text[33] = &WUZ1_ND //DIE YOU MOTHERFUCKING SONS OF WHORES!
			$audio_text[34] = &WUZ1_NE //Looks clear to me, go! Go!
			$audio_text[35] = &WUZ1_NF //I think I got them all!
			$audio_text[36] = &WUZ1_NG //Hell’s sewers, that was a sniper bullet!
			$audio_text[37] = &WUZ1_NH //Damn, I err, got something in my eyes, is it safe to move?
			$audio_text[38] = &WUZ1_NJ //YOUR MOTHERS WERE DEMON SLUTS!
			$audio_text[39] = &WUZ1_NK //YOUR BLOOD WILL RUN LIKE WINE!
			$audio_text[40] = &WUZ1_NL //EAT THIS HOT LEADEN SHIT!
			$audio_text[41] = &WUZ1_NM //YOU WILL WANDER AS GHOSTS!
			$audio_text[42] = &WUZ1_NN //CURSED ASSHOLES!
			$audio_text[43] = &WUZ1_NO //CJ, Take out that sniper!
			$audio_text[44] = &WUZ1_NP //Cover me, CJ, I’m moving up!
			$audio_text[45] = &WUZ1_NQ //I’m breaking cover, CJ, spray ‘em!
			$audio_text[46] = &WUZ1_OA //Oh fuck, man, more up ahead!
			$audio_text[47] = &WUZ1_OB //Shit, man, they really want you dead!
			$audio_text[48] = &WUZ1_OC //I see him.  He on the roof!
			$audio_text[49] = &WUZ1_OD //Ok, clear, Woozie, GO!
			$audio_text[50] = &WUZ1_OE //Break for it, Woozie!
			$audio_text[51] = &WUZ1_OF //I got your back, go!
			$audio_text[52] = &WUZ1_OG //Hold up, Woozie, we still got trouble!
			$audio_text[53] = &WUZ1_OH //Lay low!
			$audio_text[54] = &WUZ1_OJ //Woozie, keep it down, we still got trouble!
			$audio_text[55] = &WUZ1_OK //In the car, quick!
			$audio_text[56] = &WUZ1_OL //Get on the back of the bike!
			$audio_text[57] = &WUZ1_OM //On the bike, quick!
			$audio_text[58] = &WUZ1_ON //Let’s get the fuck out of here!
			$audio_text[59] = &WUZ1_OO //Let’s get the hell out of here!
			$audio_text[60] = &WUZ1_OP //We got more company!
			$audio_text[61] = &WUZ1_OQ //Cars on our tail!
			$audio_text[62] = &WUZ1_OR //More of them, in cars!
			$audio_text[63] = &WUZ1_PA //You drive, I’ll send them back to the sewers!
			$audio_text[64] = &WUZ1_PB //You drive, I’ll send them back to the sewers!
			$audio_text[65] = &WUZ1_QA //You drive, I’ll send them back to the sewers!
			$audio_text[66] = &WUZ1_RA //You drive, I’ll send them back to the sewers!
			$audio_text[67] = &WUZ1_QB //You drive, I’ll send them back to the sewers!
			$audio_text[68] = &WUZ1_RD //You drive, I’ll send them back to the sewers!
			$audio_text[69] = &WUZ1_EA //Hey, what’s with all these people?
			$audio_text[70] = &WUZ1_EB //Where are they going?
			$audio_text[71] = &WUZ1_EC //Something isn’t right here.  We should be careful…
			$audio_text[72] = &WUZ1_EE //Follow me.
			$audio_text[73] = &WUZ1_GJ //Now where’s that loose cobble?
			$audio_text[74] = &WUZ1_JA //What you doing?					
			$audio_text[75] = &WUZ1_JB //Just, eerr, checking the, um, y’know.  
			$audio_text[77] = &WUZ1_GC //Hmmm, yes, intriguing...
			$audio_text[78] = &WUZ1_KA //What’s wrong?  You lost?  Need a hand? 
			$audio_text[79] = &WUZ1_KB //NO!
			$audio_text[80] = &WUZ1_KC //No, I was just, you know, getting the feel of the place.
			$audio_text[81] = &WUZ1_KD //Stick Close!
			$audio_text[82] = &WUZ1_GE //Stick Close!
			$audio_text[83] = &WUZ1_GF //Stick Close!
			$audio_text[84] = &WUZ1_FA //Stick Close!
			$audio_text[85] = &WUZ1_FB //Stick Close!
			$audio_text[86] = &WUZ1_FC //Stick Close!
			$audio_text[87] = &WUZ1_FD //Stick Close!
			$audio_text[88] = &WUZ1_FE //Stick Close!
			$audio_text[89] = &WUZ1_HA //Stick Close!
			$audio_text[90] = &WUZ1_HB //Stick Close!
			$audio_text[91] = &WUZ1_HC //Stick Close!
			$audio_text[92] = &WUZ1_OM //Stick Close!
			$audio_text[93] = &WUZ1_OK //Stick Close!







			audio_for_char[1] = 2
			audio_for_char[2] = 1
			audio_for_char[3] = 2
			audio_for_char[4] = 2
			audio_for_char[5] = 1
			audio_for_char[6] = 2
			audio_for_char[7] = 2
			audio_for_char[8] = 2
			audio_for_char[9] = 1
			audio_for_char[10] = 2
			audio_for_char[11] = 2
			audio_for_char[12] = 2
			audio_for_char[13] = 1
			audio_for_char[14] = 2
			audio_for_char[15] = 2
			audio_for_char[16] = 2
			audio_for_char[17] = 2
			audio_for_char[18] = 1
			audio_for_char[19] = 2
			audio_for_char[20] = 2
			audio_for_char[21] = 1
			audio_for_char[22] = 2
			audio_for_char[23] = 3
			audio_for_char[24] = 2
			audio_for_char[25] = 3
			audio_for_char[26] = 3
			audio_for_char[27] = 2
			audio_for_char[28] = 2
			audio_for_char[29] = 2
			audio_for_char[30] = 2
			audio_for_char[31] = 2
			audio_for_char[32] = 2
			audio_for_char[33] = 1
			audio_for_char[34] = 2
			audio_for_char[35] = 2
			audio_for_char[36] = 0
			audio_for_char[37] = 2
			audio_for_char[38] = 1
			audio_for_char[39] = 2
			audio_for_char[40] = 2
			audio_for_char[41] = 2
			audio_for_char[42] = 2
			audio_for_char[43] = 2
			audio_for_char[44] = 2
			audio_for_char[45] = 2
			audio_for_char[46] = 0
			audio_for_char[47] = 2
			audio_for_char[48] = 2
			audio_for_char[49] = 2
			audio_for_char[50] = 2			  
			audio_for_char[51] = 2			  
			audio_for_char[52] = 2			  
			audio_for_char[53] = 1			  
			audio_for_char[54] = 2			  
			audio_for_char[55] = 2			  
			audio_for_char[56] = 1			  
			audio_for_char[57] = 1			  
			audio_for_char[58] = 1			  
			audio_for_char[59] = 2			  
			audio_for_char[60] = 1			  
			audio_for_char[61] = 2			  
			audio_for_char[62] = 2			  
			audio_for_char[63] = 1
			audio_for_char[64] = 2
			audio_for_char[65] = 2
			audio_for_char[66] = 1
			audio_for_char[67] = 1
			audio_for_char[68] = 1
			audio_for_char[69] = 2
			audio_for_char[70] = 1
			audio_for_char[71] = 2
			audio_for_char[72] = 2
			audio_for_char[73] = 2
			audio_for_char[74] = 1
			audio_for_char[75] = 2
			audio_for_char[76] = 2
			audio_for_char[77] = 2
			audio_for_char[78] = 1
			audio_for_char[79] = 2
			audio_for_char[80] = 2
			audio_for_char[81] = 2
			audio_for_char[82] = 2
			audio_for_char[83] = 2
			audio_for_char[84] = 2
			audio_for_char[85] = 1
			audio_for_char[86] = 2
			audio_for_char[87] = 2
			audio_for_char[88] = 2
			audio_for_char[89] = 2
			audio_for_char[90] = 1
			audio_for_char[91] = 2
			audio_for_char[92] = 1
			audio_for_char[93] = 1
								 
			audio_actor[1] = scplayer


			
			//1 = catalina
			//2 = player

			audio_flag = 1
//			play_audio = 0

			LOAD_MISSION_AUDIO 1 audio_sound[1]
			LOAD_MISSION_AUDIO 2 audio_sound[2]

			audio_slot[1] = 1
			audio_slot[2] = 2


		BREAK

		CASE 1 //waiting to play audio
			
			IF NOT play_audio = 0
				IF lose_face_message_printed = 0
					IF TIMERA > play_audio_delay
						IF HAS_MISSION_AUDIO_FINISHED 1
						AND HAS_MISSION_AUDIO_FINISHED 2
							IF audio_slot[1] = play_audio
								play_slot = 1
							ELSE
								IF audio_slot[2] = play_audio
									play_slot = 2
								ELSE
									play_slot = 1
									audio_slot[1] = play_audio
									CLEAR_MISSION_AUDIO 1
									LOAD_MISSION_AUDIO 1 audio_sound[play_audio]
									//audio hasn't been requested yet
								ENDIF
							ENDIF			

							IF HAS_MISSION_AUDIO_LOADED play_slot
								actor_int = audio_for_char[play_audio]
								this_actor = audio_actor[actor_int]
								IF NOT force_audio = 1 //otherwise audio will not play if no mission peds are nearby
									audio_i = 1
									audio_count = 0
									WHILE audio_i < 7
										audio_char = audio_actor[audio_i]
										IF NOT audio_char = 0
											IF NOT audio_char = this_actor
												IF NOT IS_CHAR_DEAD this_actor
													IF NOT IS_CHAR_DEAD audio_char
														IF LOCATE_CHAR_ANY_MEANS_CHAR_3D this_actor audio_char 10.0 10.0 10.0 FALSE
															audio_count ++
														ENDIF
													ENDIF
												ENDIF
											ENDIF
										ENDIF
										audio_i++
									ENDWHILE
								ENDIF

								IF force_audio = 1
								OR audio_count > 0
								OR audio_for_char[play_audio] = 0
									IF NOT audio_for_char[play_audio] = 0
										IF NOT IS_CHAR_DEAD this_actor
											IF play_audio_global = 0   
												ATTACH_MISSION_AUDIO_TO_CHAR play_slot this_actor								 
											ENDIF
											IF NOT IS_CHAR_TALKING this_actor
												play_audio_now = 1
												START_CHAR_FACIAL_TALK this_actor 15000
											ELSE
												DISABLE_CHAR_SPEECH this_actor FALSE
											ENDIF
										ENDIF
									ENDIF

									IF audio_for_char[play_audio] = 0
										play_audio_now = 1
									ENDIF

									IF play_audio_now = 1
										IF NOT this_actor = 0
											IF NOT IS_CHAR_DEAD this_Actor
												SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH this_actor TRUE
											ENDIF
	//										VAR_INT cuntyy
	//										cuntyy = this_actor
	//										VIEW_INTEGER_VARIABLE cuntyy cuntyy
	//										VIEW_INTEGER_VARIABLE scplayer scplayer
							  
										ENDIF
										PLAY_MISSION_AUDIO play_slot
										CLEAR_PRINTS
										PRINT $audio_text[play_audio] 10000 1
										audio_flag ++
										play_audio_now = 0

										play_audio ++
										next_audio = play_audio

										// if the other slot doesn't already have the next audio loaded, then load it.
										IF NOT audio_sound[play_audio] = 0
											IF play_slot = 1									
												IF NOT audio_slot[2] = play_audio
													LOAD_MISSION_AUDIO 2 audio_sound[play_audio]	
													audio_slot[2] = play_audio
												ENDIF
											ELSE
												IF NOT audio_slot[1] = play_audio
													LOAD_MISSION_AUDIO 1 audio_sound[play_audio]	
													audio_slot[1] = play_audio
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ELSE
									audio_flag = 6
								ENDIF
							ELSE
								LOAD_MISSION_AUDIO play_slot audio_sound[play_audio]
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		BREAK

		 


		CASE 2 // check if audio has/should finish
			IF HAS_MISSION_AUDIO_FINISHED play_slot
				IF NOT IS_CHAR_DEAD this_actor
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH this_actor FALSE
				ENDIF

				audio_flag++
			ELSE
				IF DOES_CHAR_EXIST this_actor
					IF IS_CHAR_DEAD this_actor
						audio_flag++
					ENDIF
				ENDIF
			ENDIF
		BREAK

		CASE 3 //clear audio
			IF NOT IS_CHAR_DEAD this_actor
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH this_actor FALSE
			ENDIF
			CLEAR_MISSION_AUDIO play_slot
			audio_slot[play_slot] = 0
			CLEAR_PRINTS 
			IF NOT IS_CHAR_DEAD this_actor
				STOP_CHAR_FACIAL_TALK this_actor
				ENABLE_CHAR_SPEECH this_actor
			ENDIF
			audio_flag++
		BREAK

		CASE 4 //request next audio

			IF NOT IS_CHAR_DEAD this_actor
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH this_actor FALSE
			ENDIF

			play_audio ++
			IF NOT audio_sound[play_audio] = 0 
				LOAD_MISSION_AUDIO play_slot audio_sound[play_audio]
				audio_slot[play_slot] = play_audio
			ENDIF
			
			play_audio_for -= 1
			IF NOT play_audio_for > 0
				play_audio = 0
				play_audio_for = 0				
			ELSE
				play_audio = next_audio
			ENDIF
			audio_flag = 1
		BREAK

		CASE 5 // clear all for cut scene skip

			IF NOT IS_CHAR_DEAD this_actor
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH this_actor FALSE
			ENDIF

			audio_flag = 1
			play_audio = 0
			play_audio_for = 0
			play_timed_audio = 0
			play_timed_audio_for = 0
			play_audio_global = 0
			CLEAR_MISSION_AUDIO play_slot
			audio_slot[play_slot] = 0
			CLEAR_PRINTS
			IF NOT IS_CHAR_DEAD this_actor
				ENABLE_CHAR_SPEECH this_actor
			ENDIF

		BREAK

		CASE 6 // clear all for cut scene skip (fade)

			IF NOT IS_CHAR_DEAD this_actor
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH this_actor FALSE
			ENDIF

			audio_flag = 2
			play_audio = 0
			play_audio_for = 0
			play_timed_audio = 0
			play_timed_audio_for = 0
			play_audio_global = 0
			audio_slot[play_slot] = 0
			CLEAR_PRINTS
			IF NOT IS_CHAR_DEAD this_actor
				ENABLE_CHAR_SPEECH this_actor
			ENDIF

		BREAK


	ENDSWITCH
RETURN







	
// Mission wuzi1 failed
mission_wuzi1_failed:
	PRINT_BIG M_FAIL 5000 1
RETURN

   		  

// mission wuzi1 passed
mission_wuzi1_passed:
	PRINT_WITH_NUMBER_BIG ( M_PASSS ) 5000 5000 1 //"Mission Passed!"
	ADD_SCORE Player1 5000 //amount of cash reward
	AWARD_PLAYER_MISSION_RESPECT 10 //amount of respect
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1
	PLAYER_MADE_PROGRESS 1
	REGISTER_MISSION_PASSED ( WUZI_1 ) //Used in the stats 

	flag_wuzi_mission_counter ++
RETURN
		


// mission cleanup
mission_cleanup_wuzi1:

	wz1_i = 0
	WHILE wz1_i < 18
		REMOVE_BLIP	wz1_tong_blip[wz1_i]
		IF NOT IS_CHAR_DEAD wz1_tong[wz1_i]
			TASK_KILL_CHAR_ON_FOOT wz1_tong[wz1_i] scplayer
			SET_CHAR_KEEP_TASK wz1_tong[wz1_i] TRUE
			MARK_CHAR_AS_NO_LONGER_NEEDED wz1_tong[wz1_i]
		ENDIF
		wz1_i ++
	ENDWHILE





	CLEAR_HELP

	IF NOT IS_CAR_DEAD wz1_getaway_car
			SET_CAR_HEAVY wz1_getaway_car FALSE
			SET_CAR_PROOFS wz1_getaway_car FALSE FALSE FALSE FALSE FALSE 
			LOCK_CAR_DOORS wz1_getaway_car CARLOCK_UNLOCKED
	ENDIF

	IF IS_PLAYER_PLAYING player1
		SET_PLAYER_CONTROL player1 ON
	ENDIF

//	SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 FALSE

	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_CAR_DENSITY_MULTIPLIER 1.0

	UNLOAD_SPECIAL_CHARACTER 1
	MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
	MARK_MODEL_AS_NO_LONGER_NEEDED DNB1
	MARK_MODEL_AS_NO_LONGER_NEEDED DNB2
	MARK_MODEL_AS_NO_LONGER_NEEDED DNB3
	MARK_MODEL_AS_NO_LONGER_NEEDED SNIPER
	MARK_MODEL_AS_NO_LONGER_NEEDED SENTINEL

	MARK_MODEL_AS_NO_LONGER_NEEDED TRIADA
	MARK_MODEL_AS_NO_LONGER_NEEDED TRIADB
	MARK_MODEL_AS_NO_LONGER_NEEDED OMOST
	MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
	REMOVE_ANIMATION SWAT
	REMOVE_ANIMATION wuzi
	REMOVE_ANIMATION GANGS
	REMOVE_ANIMATION CAR_CHAT

	REMOVE_CAR_RECORDING 136
	REMOVE_CAR_RECORDING 137
	REMOVE_CAR_RECORDING 138
	REMOVE_CAR_RECORDING 139
	REMOVE_CAR_RECORDING 169

	SWITCH_PED_ROADS_BACK_TO_ORIGINAL -2239.2510 636.6675 53.1933 -2165.0466 720.0577 49.5456

	CLEAR_ONSCREEN_COUNTER wz1_health_bar
	SWITCH_RANDOM_TRAINS ON
	SET_WANTED_MULTIPLIER 1.0
	IF NOT IS_CHAR_DEAD scplayer
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
	ENDIF


	IF NOT IS_CHAR_DEAD wz1_wuzi
//		MARK_CHAR_AS_NO_LONGER_NEEDED wz1_wuzi
		REMOVE_CHAR_ELEGANTLY wz1_wuzi
	ENDIF

	REMOVE_BLIP wz1_goto_blip
	REMOVE_BLIP	wz1_wuzi_blip
	REMOVE_BLIP wz1_car_blip[0]
	REMOVE_BLIP wz1_car_blip[1]

	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_CAR_DENSITY_MULTIPLIER 1.0

	REMOVE_SCRIPT_FIRE wz1_Fire1
	REMOVE_SCRIPT_FIRE wz1_Fire2

	REMOVE_DECISION_MAKER wz1_ped_decisions

	SWITCH_EMERGENCY_SERVICES ON


	flag_player_on_mission = 0

			SET_USES_COLLISION_OF_CLOSEST_OBJECT_OF_TYPE -2175.2129 662.7180 50.8582 10.5 ct_gatexr TRUE
			SET_USES_COLLISION_OF_CLOSEST_OBJECT_OF_TYPE -2175.2129 662.7180 50.8582 10.5 china_town_gateb TRUE

	MISSION_HAS_FINISHED



RETURN










}