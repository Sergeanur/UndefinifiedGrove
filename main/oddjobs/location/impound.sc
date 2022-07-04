MISSION_START




{

police_impound_script:



 	


SCRIPT_NAME IMPND

LVAR_INT  start_impound request_models im_cop_Dec revise_ai_time revise_ai_timeb player_is_dangerous 
VAR_INT imp_cop[8] alarm_on last_cop_task[8] cop_task_flag[8] cop_timer[8] player_is_threat[8]
VAR_INT activate_doors



				CONST_INT   CALL_FOR_BACKUP			1
				CONST_INT   WARN_PLAYER				2
				CONST_INT   COP_ATTACK_PLAYER		3
				CONST_INT   LOST_PLAYER				4
				CONST_INT   AMBIENT					5
				CONST_INT   GO_TO_DEFENSE			6
				CONST_INT   INVESTIGATE_SOUND		7
				CONST_INT  	RETURN_TO_AMBIENT		8
				CONST_INT   IDENTIFY_ATTACKER		9

				player_is_threat[0] = 0
				player_is_threat[1] = 0
				player_is_threat[2] = 0
				player_is_threat[3] = 0
				player_is_threat[4] = 0
				player_is_threat[5] = 0
				player_is_threat[6] = 0
				player_is_threat[7] = 0



					 
				VAR_FLOAT cop_territory_xa1[4] cop_territory_ya1[4] cop_territory_za1[4] cop_territory_xb1[4] cop_territory_yb1[4] cop_territory_zb1[4]
				VAR_FLOAT cop_territory_xa2[4] cop_territory_ya2[4] cop_territory_za2[4] cop_territory_xb2[4] cop_territory_yb2[4] cop_territory_zb2[4]

				cop_territory_xa1[1] = 1540.6317 
				cop_territory_ya1[1] = -1603.1626
				cop_territory_za1[1] = 17.6564 
				cop_territory_xb1[1] = 1607.2704 
				cop_territory_yb1[1] = -1637.5281
				cop_territory_zb1[1] = 12.7740 
									   
				cop_territory_xa2[1] = 1613.3190 
				cop_territory_ya2[1] = -1633.6118
				cop_territory_za2[1] = 12.0770 
				cop_territory_xb2[1] = 1522.7300 
				cop_territory_yb2[1] = -1721.3983
				cop_territory_zb2[1] = 4.2191
				
				cop_territory_xa1[2] = -1572.8066
				cop_territory_ya1[2] = 646.8937 
				cop_territory_za1[2] = 10.5019 
				cop_territory_xb1[2] = -1641.4091
				cop_territory_yb1[2] = 687.6273 
				cop_territory_zb1[2] = 4.8704 
									   
				cop_territory_xa2[2] = -1700.9330
				cop_territory_ya2[2] = 680.0250 
				cop_territory_za2[2] = 27.7313 
				cop_territory_xb2[2] = -1638.9823
				cop_territory_yb2[2] = 699.3646 
				cop_territory_zb2[2] = 5.0489 
				
				cop_territory_xa1[3] = 2238.0095 
				cop_territory_ya1[3] = 2430.8906
				cop_territory_za1[3] = 11.7640  
				cop_territory_xb1[3] = 2304.2891
				cop_territory_yb1[3] = 2502.3215
				cop_territory_zb1[3] = -9.0538 

				cop_territory_xa2[3] = 2238.0095
				cop_territory_ya2[3] = 2430.8906
				cop_territory_za2[3] = 11.7640  
				cop_territory_xb2[3] = 2304.2891
				cop_territory_yb2[3] = 2502.3215
				cop_territory_zb2[3] = -9.0538 					   
					   
				VAR_FLOAT imp_barrier_x1[4]	imp_barrier_y1[4] imp_barrier_z1[4] imp_barrier_h1[4] imp_barrier_x2[4]	imp_barrier_y2[4] imp_barrier_z2[4] imp_barrier_h2[4]
				VAR_FLOAT imp_shutter_x1[4]	imp_shutter_y1[4] imp_shutter_z1[4] imp_shutter_h1[4] imp_shutter_x2[4]	imp_shutter_y2[4] imp_shutter_z2[4] imp_shutter_h2[4]
				LVAR_INT imp_shutter[2]

				imp_barrier_x1[1] = 1544.691
				imp_barrier_y1[1] =	-1630.75
				imp_barrier_z1[1] =	13.043
				imp_barrier_h1[1] =	0.0

				imp_barrier_x2[1] =	0.0
				imp_barrier_y2[1] =	0.0
				imp_barrier_z2[1] =	0.0
				imp_barrier_h2[1] =	0.0

				imp_barrier_x1[2] =	-1572.2028
				imp_barrier_y1[2] =	658.7618 
				imp_barrier_z1[2] =	6.8916 
				imp_barrier_h1[2] =	0.0
									
				imp_barrier_x2[2] =	-1701.4302
				imp_barrier_y2[2] =	687.7184 
				imp_barrier_z2[2] =	24.6525 
				imp_barrier_h2[2] =	180.0

				imp_barrier_x1[3] =	2238.1987  
				imp_barrier_y1[3] =	2450.3389
				imp_barrier_z1[3] =	10.5829 
				imp_barrier_h1[3] =	0.0

				imp_barrier_x2[3] =	0.0
				imp_barrier_y2[3] =	0.0
				imp_barrier_z2[3] =	0.0
				imp_barrier_h2[3] =	0.0


				imp_shutter_x1[1] = 1588.5029 
				imp_shutter_y1[1] =	-1637.8717										  
				imp_shutter_z1[1] =	14.5641 									  
				imp_shutter_h1[1] =	0.0												  

				imp_shutter_x2[1] =	0.0												  
				imp_shutter_y2[1] =	0.0												  
				imp_shutter_z2[1] =	0.0												  
				imp_shutter_h2[1] =	0.0
																					 
				imp_shutter_x1[2] =	-1631.7673										 
				imp_shutter_y1[2] =	688.4075 									  	
				imp_shutter_z1[2] =	8.5436   									       
				imp_shutter_h1[2] =	90.0												   
																					  
				imp_shutter_x2[2] =	0.0
				imp_shutter_y2[2] =	0.0 
				imp_shutter_z2[2] =	0.0 
				imp_shutter_h2[2] =	0.0

				imp_shutter_x1[3] =	2293.8301
				imp_shutter_y1[3] =	2498.8037
				imp_shutter_z1[3] =	4.4414 
				imp_shutter_h1[3] =	90.0

				imp_shutter_x2[3] =	2335.1785
				imp_shutter_y2[3] =	2443.6213										 
				imp_shutter_z2[3] =	6.9743 											 
				imp_shutter_h2[3] =	59.9993	 										 

				door_control[0] = 0
				door_control[1] = 0
				door_control[2] = 0
				door_control[3] = 0

VAR_FLOAT cop_x[10] cop_y[10] cop_z[10] cop_h[10]

//VAR_INT last_command i_count
	  
//VIEW_INTEGER_VARIABLE cop_threat_state[0] cop_threat_state[0]
//VIEW_INTEGER_VARIABLE cop_event_flag[0]	  cop_event_flag[0]
//VIEW_INTEGER_VARIABLE last_command last_command

IF edit_i = 90
	CREATE_OBJECT police_barrier 0.0 0.0 0.0 imp_barrier[0]
	CREATE_OBJECT kmb_shutter 0.0 0.0 0.0 imp_shutter[0]
	CREATE_RANDOM_CHAR 0.0 0.0 0.0 a_victim
	CREATE_RANDOM_CHAR 0.0 0.0 0.0 cop_to_speak
ENDIF

//LVAR_INT pswdig
//CREATE_OBJECT bb_pickup 2151.7854 2456.4775 9.6719 pswdig
//FREEZE_OBJECT_POSITION pswdig TRUE


	

impound_script_loop:

WAIT 0

IF IS_PLAYER_PLAYING player1
	
	






	LVAR_INT edit_i

//	cop_task = NOTHING
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_2
//		cop_task = LOOK_AT_PLAYER
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_4
//		cop_task = KILL_PLAYER
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_6
//		cop_task = RETURN_TO_BOOTH
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_O
//		player_is_dangerous = 1
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_L
//		player_is_dangerous = 0
//	ENDIF
//
//
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_A
//		IF NOT IS_CHAR_DEAD scplayer
//			SET_CHAR_COORDINATES scplayer 1579.4248 -1636.4630 22.5812
//		ENDIF
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
//		IF NOT IS_CHAR_DEAD scplayer
//			SET_CHAR_COORDINATES scplayer -1609.2340 667.2437 12.1901
//		ENDIF
//	ENDIF

//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_A
//		IF NOT IS_CHAR_DEAD scplayer
//			SET_CHAR_COORDINATES scplayer 2151.7854 2456.4775 9.6719
//		ENDIF
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
//		IF NOT IS_CHAR_DEAD scplayer
//			SET_CHAR_COORDINATES scplayer 1514.8018 -1624.8978 13.0469
//			SET_CHAR_HEADING scplayer 270.0
//		ENDIF
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_F
//		IF NOT IS_CHAR_DEAD scplayer
//			SET_CHAR_COORDINATES scplayer 2257.4055 1526.1510 9.8203
//			SET_CHAR_HEADING scplayer 270.0
//		ENDIF
//	ENDIF
//

			  



//
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_UP
//		IF DOES_OBJECT_EXIST imp_shutter[edit_i]
//			GET_OBJECT_COORDINATES imp_shutter[edit_i] x y z
//			y += 0.01
//			SET_OBJECT_COORDINATES imp_shutter[edit_i] x y z
//		ENDIF
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_DOWN
//		IF DOES_OBJECT_EXIST imp_shutter[edit_i]
//			GET_OBJECT_COORDINATES imp_shutter[edit_i] x y z
//			y -= 0.01
//			SET_OBJECT_COORDINATES imp_shutter[edit_i] x y z
//		ENDIF
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_LEFT
//		IF DOES_OBJECT_EXIST imp_shutter[edit_i]
//			GET_OBJECT_COORDINATES imp_shutter[edit_i] x y z
//			x -= 0.01
//			SET_OBJECT_COORDINATES imp_shutter[edit_i] x y z
//		ENDIF
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_RIGHT
//		IF DOES_OBJECT_EXIST imp_shutter[edit_i]
//			GET_OBJECT_COORDINATES imp_shutter[edit_i] x y z
//			x += 0.01
//			SET_OBJECT_COORDINATES imp_shutter[edit_i] x y z
//		ENDIF
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_M
//		IF DOES_OBJECT_EXIST imp_shutter[edit_i]
//			GET_OBJECT_COORDINATES imp_shutter[edit_i] x y z
//			z -= 0.01
//			SET_OBJECT_COORDINATES imp_shutter[edit_i] x y z
//		ENDIF
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_K
//		IF DOES_OBJECT_EXIST imp_shutter[edit_i]
//			GET_OBJECT_COORDINATES imp_shutter[edit_i] x y z
//			z += 0.01
//			SET_OBJECT_COORDINATES imp_shutter[edit_i] x y z
//		ENDIF
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_N
//		IF DOES_OBJECT_EXIST imp_shutter[edit_i]
//			GET_OBJECT_HEADING imp_shutter[edit_i] h
//			h -= 0.5
//			SET_OBJECT_HEADING imp_shutter[edit_i] h
//		ENDIF
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_J
//		IF DOES_OBJECT_EXIST imp_shutter[edit_i]
//			GET_OBJECT_HEADING imp_shutter[edit_i] h
//			h += 0.5
//			SET_OBJECT_HEADING imp_shutter[edit_i] h
//		ENDIF
//	ENDIF
//
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_0
//		edit_i = 0
//	ENDIF
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_1
//		edit_i = 1
//	ENDIF
//
//	LVAR_FLOAT h
//
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_F
//		GET_OBJECT_COORDINATES imp_shutter[edit_i] x y z
//		GET_OBJECT_HEADING imp_shutter[edit_i] h
//		SAVE_FLOAT_TO_DEBUG_FILE x
//		SAVE_NEWLINE_TO_DEBUG_FILE
//		SAVE_FLOAT_TO_DEBUG_FILE y
//		SAVE_NEWLINE_TO_DEBUG_FILE
//		SAVE_FLOAT_TO_DEBUG_FILE z
//		SAVE_NEWLINE_TO_DEBUG_FILE
//		SAVE_FLOAT_TO_DEBUG_FILE h
//		SAVE_NEWLINE_TO_DEBUG_FILE
//
//	ENDIF
//	
//


	IF start_impound = 0


		IF request_models = 0
 	//		SET_CAR_DENSITY_MULTIPLIER 0.0 //debug
			IF im_players_city = LEVEL_LOSANGELES			
				VAR_INT cop_model car_model
				REQUEST_MODEL LAPD1
				REQUEST_MODEL COLT45
				REQUEST_MODEL COPCARLA
				cop_model = LAPD1
				car_model = COPCARLA
			ENDIF
			IF im_players_city = LEVEL_SANFRANCISCO			
				REQUEST_MODEL SFPD1
				REQUEST_MODEL COLT45
				REQUEST_MODEL COPCARSF
				cop_model = SFPD1
				car_model = COPCARSF			
			ENDIF
			IF im_players_city = LEVEL_LASVEGAS			
				REQUEST_MODEL LVPD1
				REQUEST_MODEL COLT45
				REQUEST_MODEL COPCARVG
				cop_model = LVPD1
				car_model = COPCARVG
			ENDIF
		
			IF FLAG_PLAYER_ON_MISSION = 0
				impound_objects_loaded = 1
				REQUEST_MODEL police_barrier
				REQUEST_MODEL sfcopdr
				REQUEST_MODEL kmb_shutter
			ENDIF
	
			request_models = 2
		ENDIF

		IF request_models = 2
			IF HAS_MODEL_LOADED cop_model
			AND HAS_MODEL_LOADED COLT45			
			AND HAS_MODEL_LOADED car_model
				request_models = 0
				IF NOT flag_player_on_mission = 1
//					SET_PED_DENSITY_MULTIPLIER 0.3
//					SET_CAR_DENSITY_MULTIPLIER 0.3
				ENDIF
				COPY_CHAR_DECISION_MAKER DM_PED_EMPTY im_cop_dec
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE im_cop_dec EVENT_SHOT_FIRED TASK_SIMPLE_LOOK_AT_ENTITY_OR_COORD 100.0 100.0 100.0 0.0 FALSE TRUE

				LVAR_INT imp_barrier[2]
				CREATE_OBJECT_NO_OFFSET police_barrier imp_barrier_x1[impound_area] imp_barrier_y1[impound_area] imp_barrier_z1[impound_area] imp_barrier[0]   
				SET_OBJECT_ROTATION imp_barrier[0] -90.0 0.0 imp_barrier_h1[impound_area]
				IF NOT imp_barrier_x2[impound_area] = 0.0
					CREATE_OBJECT_NO_OFFSET police_barrier imp_barrier_x2[impound_area] imp_barrier_y2[impound_area] imp_barrier_z2[impound_area] imp_barrier[1]
					SET_OBJECT_ROTATION imp_barrier[1] -90.0 0.0 imp_barrier_h2[impound_area]
				ENDIF

				LVAR_INT door_model
				IF impound_area = 2
					door_model = sfcopdr
				ELSE
					door_model = kmb_shutter
				ENDIF
				CREATE_OBJECT_NO_OFFSET door_model imp_shutter_x1[impound_area] imp_shutter_y1[impound_area] imp_shutter_z1[impound_area] imp_shutter[0]   
				SET_OBJECT_ROTATION imp_shutter[0] 0.0 0.0 imp_shutter_h1[impound_area]
				IF NOT imp_shutter_x2[impound_area] = 0.0
					CREATE_OBJECT_NO_OFFSET door_model imp_shutter_x2[impound_area] imp_shutter_y2[impound_area] imp_shutter_z2[impound_area] imp_shutter[1]
					SET_OBJECT_ROTATION imp_shutter[1] 0.0 0.0 imp_shutter_h2[impound_area]
				ENDIF

				
				

//				CREATE_OBJECT_NO_OFFSET kmb_shutter 1588.497 -1638.154 14.59 2

//				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE im_cop_dec EVENT_SEEN_COP
//				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE im_cop_dec EVENT_ACQUAINTANCE_PED_DISLIKE
//				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE im_cop_dec EVENT_ACQUAINTANCE_PED_DISLIKE TASK_SIMPLE_GUN_CTRL 0.0 100.0 0.0 0.0 0 1
//				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE im_cop_dec EVENT_GUN_AIMED_AT
//				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE im_cop_dec EVENT_GUN_AIMED_AT TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 0.0 100.0 0.0 0 1

				IF impound_area = 1
					CREATE_CHAR PEDTYPE_MISSION1 cop_model 1544.3827 -1631.8492 12.3905 imp_cop[0] // barrier cop	
					SET_CHAR_HEADING imp_cop[0] 45.0
					CREATE_CHAR PEDTYPE_MISSION1 cop_model 1578.9567 -1634.1268 12.5547 imp_cop[2] // barrier cop	
					SET_CHAR_HEADING imp_cop[2] 0.0
					cop_x[0] = 1544.3827
					cop_y[0] = -1631.8492
					cop_z[0] = 12.3905
					cop_h[0] = 45.0
					cop_x[2] = 1544.3827
					cop_y[2] = 1578.9567 
					cop_z[2] = -1634.1268
					cop_h[2] = 12.554

					door_rotation[0] = -90.0
					door_rotation[2] = 0.0
				ENDIF

				IF impound_area = 2
					CREATE_CHAR PEDTYPE_MISSION1 cop_model -1700.8392 688.9841 23.9032 imp_cop[1] // barrier cop	
					SET_CHAR_HEADING imp_cop[1] 134.0
					CREATE_CHAR PEDTYPE_MISSION1 cop_model -1572.7050 657.5745 6.2446 imp_cop[0] // barrier cop	
					SET_CHAR_HEADING imp_cop[0] 304.0
					CREATE_CHAR PEDTYPE_MISSION1 cop_model -1616.8218 679.9485 6.1901 imp_cop[2] // shutter cop	
					SET_CHAR_HEADING imp_cop[2] 188.0
					cop_x[0] = -1572.7050
					cop_y[0] = 657.5745 
					cop_z[0] = 6.2446
					cop_h[0] = 304.0
					cop_x[1] = -1700.8392
					cop_y[1] = 688.9841
					cop_z[1] = 23.9032
					cop_h[1] = 134.0
					cop_x[2] = -1616.8218  
					cop_y[2] = 679.9485
					cop_z[2] = 6.1901
					cop_h[2] = 188.0

					door_rotation[0] = -90.0
					door_rotation[1] = -90.0
					door_rotation[2] = 0.0
				ENDIF

				IF impound_area = 3
					CREATE_CHAR PEDTYPE_MISSION1 cop_model 2238.5693 2449.4170 10.0372 imp_cop[0] // barrier cop	
					SET_CHAR_HEADING imp_cop[0] 90.0
					CREATE_CHAR PEDTYPE_MISSION1 cop_model 2250.2769 2489.3328 9.8203 imp_cop[2] // barrier cop	
					SET_CHAR_HEADING imp_cop[2] 90.0
					cop_x[0] = 2238.5693
					cop_y[0] = 2449.4170
					cop_z[0] = 10.0372
					cop_h[0] = 90.0
					cop_x[2] = 2250.2769
					cop_y[2] = 2489.3328
					cop_z[2] = 9.8203 
					cop_h[2] = 90.0


				ENDIF
				
				cop_i = 0
				WHILE cop_i < 3
					IF NOT IS_CHAR_DEAD imp_cop[cop_i]
						SET_CHAR_DECISION_MAKER imp_cop[cop_i] im_cop_dec
						GIVE_WEAPON_TO_CHAR imp_cop[cop_i] WEAPONTYPE_PISTOL 9999
						SET_CHAR_RELATIONSHIP imp_cop[cop_i] ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_PLAYER1 
						cop_task_flag[cop_i] = 0
						last_cop_task[cop_i] = AMBIENT
					ENDIF
//					
					cop_i ++
				ENDWHILE
				start_impound = 2
			ENDIF
		ENDIF
	ENDIF

	IF start_impound = 2



		GET_CHAR_COORDINATES scplayer player_x player_y player_z

//alarm response

//threat search

//seen dead cop
//seen player in impound

		LVAR_INT cop_i cop_task imp_task_Status cop_threat_drop_time imp_i threat_state_change revise_ai
		VAR_FLOAT imp_f
		VAR_INT player_in_impound
		VAR_INT i_see_you imp_sequence
//		VIEW_INTEGER_VARIABLE i_see_you spotted
//		VIEW_INTEGER_VARIABLE temp_var cop_event[0]
//		VIEW_INTEGER_VARIABLE cop_event[2] cop_event[2]
//		VIEW_INTEGER_VARIABLE cop_threat_level[0] cop_threat_level[0]
//		VIEW_INTEGER_VARIABLE impound_cops_created impound_cops_created
//		VIEW_INTEGER_VARIABLE alarm_set_off alarm_set_off
//		VIEW_INTEGER_VARIABLE last_cop_task[0] last_cop_task[0]
//		VIEW_INTEGER_VARIABLE last_cop_task[2] last_cop_task[2]
//		VIEW_INTEGER_VARIABLE last_cop_task[3] last_cop_task[3]
//		VIEW_INTEGER_VARIABLE last_cop_task[4] last_cop_task[4]
//
//		VIEW_INTEGER_VARIABLE cop_event[0] cop_event[0]
//		VIEW_INTEGER_VARIABLE cop_event[2] cop_event[2]
//		VIEW_INTEGER_VARIABLE cop_event[3] cop_event[3]


								  //		VIEW_INTEGER_VARIABLE blah i_see_you

		GOSUB gate_control

		IF TIMERA > revise_ai_timeb
			

			revise_ai_timeb = TIMERA + 90
			GOSUB check_player_in_impound
			

			IF impound_cops_created = 0
				IF player_in_impound = 1				
					impound_cops_created = 1
					GOSUB make_impound_cops
					
				ENDIF
			ENDIF
		ENDIF

		// ************************************** audio here ***************************
		LVAR_INT cop_to_speak cop_says_dialogue	shut_up_timer impound_objects_loaded

		IF flag_player_on_mission = 1
			IF impound_objects_loaded = 1
				impound_objects_loaded = 0
				MARK_MODEL_AS_NO_LONGER_NEEDED police_barrier
				MARK_MODEL_AS_NO_LONGER_NEEDED sfcopdr
				MARK_MODEL_AS_NO_LONGER_NEEDED kmb_shutter
			ENDIF							
		ENDIF

		IF cop_says_dialogue = 1
			CLEAR_MISSION_AUDIO 4
			GENERATE_RANDOM_INT_IN_RANGE 0 4 edit_i
			IF edit_i = 0
				LOAD_MISSION_AUDIO 4 SOUND_CES2_AE
			ENDIF
			IF edit_i = 1
				LOAD_MISSION_AUDIO 4 SOUND_CES2_AF
			ENDIF
			IF edit_i = 2
				LOAD_MISSION_AUDIO 4 SOUND_CES2_AG
			ENDIF
			cop_says_dialogue = 2
		ENDIF

		IF cop_says_dialogue = 2
			IF HAS_MISSION_AUDIO_LOADED 4
				IF NOT IS_CHAR_DEAD cop_to_speak
					ATTACH_MISSION_AUDIO_TO_CHAR 4 cop_to_speak	
					SHUT_CHAR_UP cop_to_speak TRUE
					DISABLE_CHAR_SPEECH cop_to_speak TRUE
					PLAY_MISSION_AUDIO 4
					cop_says_dialogue = 3
					shut_up_timer = TIMERA + 10000
				ENDIF
			ENDIF	
		ENDIF

		IF cop_says_dialogue = 3
			IF TIMERA > shut_up_timer
				cop_says_dialogue = 0	
				IF NOT IS_CHAR_DEAD cop_to_speak
					SHUT_CHAR_UP cop_to_speak FALSE
					DISABLE_CHAR_SPEECH cop_to_speak FALSE
				ENDIF
			ENDIF
		ENDIF

		IF cop_says_dialogue = 4
			CLEAR_MISSION_AUDIO 4
			GENERATE_RANDOM_INT_IN_RANGE 0 4 edit_i
			IF edit_i = 0
				LOAD_MISSION_AUDIO 4 SOUND_CES2_AA
			ENDIF
			IF edit_i = 1
				LOAD_MISSION_AUDIO 4 SOUND_CES2_AB
			ENDIF
			IF edit_i = 2
				LOAD_MISSION_AUDIO 4 SOUND_CES2_AC
			ENDIF
			cop_says_dialogue = 2
		ENDIF

		IF cop_says_dialogue = 5
			CLEAR_MISSION_AUDIO 4
			LOAD_MISSION_AUDIO 4 SOUND_SOLO_AA
			cop_says_dialogue = 2
		ENDIF

		IF alarm_on = 1
			IF HAS_MISSION_AUDIO_FINISHED 4
				IF HAS_MISSION_AUDIO_LOADED 4
					IF impound_area = 1
						ATTACH_MISSION_AUDIO_TO_OBJECT 4 imp_shutter[0] 
						PLAY_MISSION_AUDIO 4
						SET_MISSION_AUDIO_POSITION 4 1589.0597 -1694.7285 10.4887
						PLAY_MISSION_AUDIO 4
						SET_MISSION_AUDIO_POSITION 4 1541.5127 -1685.9425 10.1571
						PLAY_MISSION_AUDIO 4
					ENDIF
					IF impound_area = 2
						ATTACH_MISSION_AUDIO_TO_OBJECT 4 imp_shutter[0] 
						PLAY_MISSION_AUDIO 4
						SET_MISSION_AUDIO_POSITION 4 -1619.8726 742.7860 -2.1389
						PLAY_MISSION_AUDIO 4
						SET_MISSION_AUDIO_POSITION 4 -1594.0964 716.0110 -3.1454
						PLAY_MISSION_AUDIO 4
						SET_MISSION_AUDIO_POSITION 4 -1605.5176 672.6205 -3.2395
						PLAY_MISSION_AUDIO 4
					ENDIF
					IF impound_area = 3
						SET_MISSION_AUDIO_POSITION 4 2296.4333 2444.2952 7.0111
						PLAY_MISSION_AUDIO 4
						SET_MISSION_AUDIO_POSITION 4 2255.4041 2446.6760 5.6601
						PLAY_MISSION_AUDIO 4
						SET_MISSION_AUDIO_POSITION 4 2243.1069 2498.2246 8.5154
						PLAY_MISSION_AUDIO 4
						SET_MISSION_AUDIO_POSITION 4 2296.9270 2492.9019 0.2841
						PLAY_MISSION_AUDIO 4
					ENDIF


				ENDIF
			ENDIF
		ENDIF


		
		imp_i = 0
		VAR_INT cop_event[8]

		WHILE imp_i < 8
			IF NOT IS_CHAR_DEAD imp_cop[imp_i]
				IF NOT cop_event[imp_i] < 0
					GET_CHAR_HIGHEST_PRIORITY_EVENT imp_cop[imp_i] cop_event[imp_i]
					IF NOT cop_event[imp_i] = 0			
						IF cop_event[imp_i] = EVENT_SHOT_FIRED
						OR cop_event[imp_i] = EVENT_SHOT_FIRED_WHIZZED_BY
						OR cop_event[imp_i] = EVENT_DAMAGE
							cop_event[imp_i] = -2
							GET_CHAR_COORDINATES scplayer last_player_x last_player_y last_player_z
						ENDIF
						IF cop_event[imp_i] = EVENT_SOUND_QUIET
							IF LOCATE_CHAR_ANY_MEANS_CHAR_3D imp_cop[imp_I] scplayer 5.0 5.0 3.0 FALSE
								cop_event[imp_i] = -1
								GET_CHAR_COORDINATES scplayer last_player_x last_player_y last_player_z
							ENDIF
						ENDIF													
 					ENDIF
				ENDIF
			ENDIF
			imp_i ++
		ENDWHILE

			
		IF TIMERA > revise_ai_time
			revise_ai_time = TIMERA + 100		



			IF NOT IS_CHAR_DEAD imp_cop[cop_i]

				i_see_you = 0
				navigating = 0
				cop_task = last_cop_task[cop_i]
		
				

				
					GOSUB check_cop_see_player


	VAR_INT blah

//					GOSUB cop_decisions
					GOSUB do_cop_task
					cop_event[cop_i] = 0
			ELSE
				IF cop_to_speak = imp_cop[cop_i]
					IF NOT cop_says_dialogue = 0
					AND alarm_on = 0
						CLEAR_MISSION_AUDIO 4
					ENDIF
					cop_says_dialogue = 0
					cop_to_speak = 0					
				ENDIF
			ENDIF
			cop_i ++
			IF cop_i > 7
				cop_i = 0
			ENDIF
		ENDIF


		IF im_players_city = LEVEL_LOSANGELES
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 1579.4248 -1636.4630 14.5812 120.0 120.0 80.0 FALSE
				GOSUB impound_cleanup	
			ENDIF
		ENDIF
		IF im_players_city = LEVEL_SANFRANCISCO
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer -1624.7710 679.6637 8.5690 120.0 120.0 80.0 FALSE
				GOSUB impound_cleanup
			ENDIF
		ENDIF
		IF im_players_city = LEVEL_LASVEGAS
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2284.5920 2466.8379 12.2306 120.0 120.0 80.0 FALSE
				GOSUB impound_cleanup
			ENDIF
		ENDIF

			


	ENDIF
ELSE
	IF start_impound = 2
		GOSUB impound_cleanup
	ENDIF
ENDIF

GOTO impound_script_loop



make_impound_cops:

	IF impound_area = 3
		VAR_INT cop_car[4] impound_cops_created
		CREATE_CAR car_model 2285.5022 2473.7480 2.2660 cop_car[0]
		CREATE_CAR car_model 2314.3579 2475.8455 2.2660 cop_car[1] 
		CREATE_CAR car_model 2240.9712 2476.4846 -8.4476 cop_car[2]
		CREATE_CAR car_model 2303.0532 2431.7122 -8.4531 cop_car[3]
		SET_CAR_HEADING cop_car[0] 179.4722 
		SET_CAR_HEADING cop_car[1] 268.5290 
		SET_CAR_HEADING cop_car[2] 92.5631 
		SET_CAR_HEADING cop_car[3] 180.0971
		LOCK_CAR_DOORS cop_car[0] CARLOCK_LOCKOUT_PLAYER_ONLY
		LOCK_CAR_DOORS cop_car[1] CARLOCK_UNLOCKED
		LOCK_CAR_DOORS cop_car[2] CARLOCK_LOCKOUT_PLAYER_ONLY
		LOCK_CAR_DOORS cop_car[3] CARLOCK_UNLOCKED


		CREATE_CHAR_INSIDE_CAR cop_car[0] PEDTYPE_MISSION1 cop_model imp_cop[3]
		CREATE_CHAR_AS_PASSENGER cop_car[0]  PEDTYPE_MISSION1 cop_model 0 imp_cop[4]
		CREATE_CHAR PEDTYPE_MISSION1 cop_model 2278.1001 2425.4529 2.4766 imp_cop[5]
		CREATE_CHAR PEDTYPE_MISSION1 cop_model 2268.9055 2449.2458 -8.1953 imp_cop[6]

		SET_CHAR_HEADING imp_cop[5] 187.5721 
		SET_CHAR_HEADING imp_cop[6] 228.3262

		last_cop_task[3] = AMBIENT
		last_cop_task[4] = AMBIENT
		last_cop_task[5] = AMBIENT
		last_cop_task[6] = AMBIENT

		cop_task_flag[3] = 0
		cop_task_flag[4] = 0
		cop_task_flag[5] = 0
		cop_task_flag[6] = 0

		cop_i = 3
		WHILE cop_i < 7
			IF NOT IS_CHAR_DEAD imp_cop[cop_i]
				SET_CHAR_DECISION_MAKER imp_cop[cop_i] im_cop_dec
				GIVE_WEAPON_TO_CHAR imp_cop[cop_i] WEAPONTYPE_PISTOL 9999
				SET_CHAR_RELATIONSHIP imp_cop[cop_i] ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_PLAYER1 
			ENDIF
			cop_i ++
		ENDWHILE
	ENDIF

	IF impound_area = 2 //SF impound cops
		CREATE_CAR car_model -1600.1422 748.2594 -6.2344 cop_car[0]
		CREATE_CAR car_model -1616.2329 691.9449 -6.2422 cop_car[1] 
		CREATE_CAR car_model -1574.0609 717.9744 -6.2344 cop_car[2]
		CREATE_CAR car_model -1574.3047 726.8611 -6.2344 cop_car[3]
		SET_CAR_HEADING cop_car[0] 359.8067 
		SET_CAR_HEADING cop_car[1] 358.8804 
		SET_CAR_HEADING cop_car[2] 268.0333
		SET_CAR_HEADING cop_car[3] 268.3534
		LOCK_CAR_DOORS cop_car[0] CARLOCK_LOCKOUT_PLAYER_ONLY
		LOCK_CAR_DOORS cop_car[1] CARLOCK_UNLOCKED
		LOCK_CAR_DOORS cop_car[2] CARLOCK_LOCKOUT_PLAYER_ONLY
		LOCK_CAR_DOORS cop_car[3] CARLOCK_UNLOCKED


		CREATE_CHAR_INSIDE_CAR cop_car[0] PEDTYPE_MISSION1 cop_model imp_cop[3]
		CREATE_CHAR_AS_PASSENGER cop_car[0]  PEDTYPE_MISSION1 cop_model 0 imp_cop[4]
		CREATE_CHAR PEDTYPE_MISSION1 cop_model -1606.0673 701.3859 -5.9062 imp_cop[5]
		CREATE_CHAR PEDTYPE_MISSION1 cop_model -1615.9343 695.0814 -6.2422 imp_cop[6]

		SET_CHAR_HEADING imp_cop[5] 270.5721 
		SET_CHAR_HEADING imp_cop[6] 180.3262

		last_cop_task[3] = AMBIENT
		last_cop_task[4] = AMBIENT
		last_cop_task[5] = AMBIENT
		last_cop_task[6] = AMBIENT

		cop_task_flag[3] = 0
		cop_task_flag[4] = 0
		cop_task_flag[5] = 0
		cop_task_flag[6] = 0

		cop_i = 3
		WHILE cop_i < 8
			IF NOT IS_CHAR_DEAD imp_cop[cop_i]
				SET_CHAR_DECISION_MAKER imp_cop[cop_i] im_cop_dec
				GIVE_WEAPON_TO_CHAR imp_cop[cop_i] WEAPONTYPE_PISTOL 9999
				SET_CHAR_RELATIONSHIP imp_cop[cop_i] ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_PLAYER1 
			ENDIF
			cop_i ++
		ENDWHILE
		cop_i = 0
	ENDIF

  
  
  
 




	IF impound_area = 1
		CREATE_CAR car_model 1586.2542 -1671.7321 4.8916 cop_car[0]
		CREATE_CAR car_model 1601.5195 -1699.9248 4.8984 cop_car[1] 
		CREATE_CAR car_model 1565.4286 -1710.8098 4.8984 cop_car[2]
		CREATE_CAR car_model 1529.6749 -1688.1049 4.8970 cop_car[3]
		SET_CAR_HEADING cop_car[0] 90.8726 
		SET_CAR_HEADING cop_car[1] 268.6337 
		SET_CAR_HEADING cop_car[2] 177.2788
		SET_CAR_HEADING cop_car[3] 270.1410
		LOCK_CAR_DOORS cop_car[0] CARLOCK_LOCKOUT_PLAYER_ONLY
		LOCK_CAR_DOORS cop_car[1] CARLOCK_UNLOCKED
		LOCK_CAR_DOORS cop_car[2] CARLOCK_LOCKOUT_PLAYER_ONLY
		LOCK_CAR_DOORS cop_car[3] CARLOCK_UNLOCKED


		CREATE_CHAR_INSIDE_CAR cop_car[0] PEDTYPE_MISSION1 cop_model imp_cop[3]
		CREATE_CHAR_AS_PASSENGER cop_car[0]  PEDTYPE_MISSION1 cop_model 0 imp_cop[4]
		CREATE_CHAR PEDTYPE_MISSION1 cop_model 1527.1449 -1675.2994 4.8984 imp_cop[5]
		CREATE_CHAR PEDTYPE_MISSION1 cop_model 1603.7113 -1714.2941 5.2187 imp_cop[6]
		CREATE_CHAR PEDTYPE_MISSION1 cop_model 1608.2850 -1715.7789 5.2187 imp_cop[1]
		CREATE_CHAR PEDTYPE_MISSION1 cop_model 1532.6311 -1688.0100 4.8984 imp_cop[7]
		SET_CHAR_HEADING imp_cop[5] 90.5721 
		SET_CHAR_HEADING imp_cop[6] 242.3262
		SET_CHAR_HEADING imp_cop[1] 225.3262
		SET_CHAR_HEADING imp_cop[7] 90.3262

		last_cop_task[1] = AMBIENT
		last_cop_task[3] = AMBIENT
		last_cop_task[4] = AMBIENT
		last_cop_task[5] = AMBIENT
		last_cop_task[6] = AMBIENT
		last_cop_task[7] = AMBIENT

		cop_task_flag[3] = 0
		cop_task_flag[4] = 0
		cop_task_flag[5] = 0
		cop_task_flag[6] = 0
		cop_task_flag[7] = 0

		

		cop_i = 0
		WHILE cop_i < 8
			IF NOT IS_CHAR_DEAD imp_cop[cop_i]
				SET_CHAR_DECISION_MAKER imp_cop[cop_i] im_cop_dec
				GIVE_WEAPON_TO_CHAR imp_cop[cop_i] WEAPONTYPE_PISTOL 9999
				SET_CHAR_RELATIONSHIP imp_cop[cop_i] ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_PLAYER1 
			ENDIF
			cop_i ++
		ENDWHILE

		cop_i = 0

		
	ENDIF


RETURN





gate_control:


IF impound_area = 1

	//barrier 1

												
		SWITCH door_control[0]

			CASE 0

//				IF IS_COP_VEHICLE_IN_ANGLED_AREA_3D_NO_SAVE 1554.3199 -1632.6689 13.9475 1554.3199 -1620.7622 11.8805 20.0
				IF player_is_threat[0] = 0
					IF IS_COP_VEHICLE_IN_AREA_3D_NO_SAVE 1554.3199 -1632.6689 13.9475 1535.2715 -1620.7622 11.8805
//				IF IS_CHAR_IN_ANY_CAR scplayer
//					IF IS_CHAR_IN_ANY_POLICE_VEHICLE scplayer
//						IF IS_CHAR_IN_AREA_3D scplayer 1554.3199 -1632.6689 13.9475 1535.2715 -1620.7622 11.8805 FALSE
//							IF player_is_threat[0] = 0
								IF NOT IS_CHAR_DEAD imp_cop[0]
									IF IS_CHAR_IN_AREA_2D imp_cop[0] 1543.5955 -1631.2792 1545.0223 -1632.7041 FALSE
										door_control[0] = 1
										IF alarm_on = 0
											IF cop_says_dialogue = 0
												cop_says_dialogue = 1 //it's open, in ya go
												cop_to_speak = imp_cop[0]
											ENDIF
										ENDIF
									ENDIF
								ENDIF
//							ENDIF
//						ENDIF
					ENDIF
				ENDIF
				IF activate_doors = 1
					IF NOT IS_CAR_DEAD cop_car[1]
						IF IS_CAR_IN_AREA_2D cop_car[1] 1544.9884 -1622.2897 1556.1780 -1632.6199 FALSE
							door_control[0] = 1
						ENDIF	
					ENDIF
				ENDIF
			BREAK

			CASE 1
				door_rotation[0] += 0.5 			
				SET_OBJECT_ROTATION imp_barrier[0] door_rotation[0] 0.0 0.0
				IF door_rotation[0] >= 0.0
					door_control[0] = 2
				ENDIF
			BREAK

			CASE 2
				IF NOT IS_AREA_OCCUPIED 1554.3199 -1632.6689 13.9475 1535.2715 -1620.7622 11.8805 FALSE TRUE FALSE FALSE FALSE
					door_control[0] = 3
				ENDIF	
			BREAK

			CASE 3
				IF IS_AREA_OCCUPIED 1540.9379 -1623.6805 14.3551 1550.8867 -1631.6476 12.1949 FALSE TRUE FALSE FALSE FALSE
					door_control[0] = 1
//					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_barrier[0] SOUND_GARAGE_DOOR_START	
				ELSE
					door_rotation[0] -= 0.5 			
					SET_OBJECT_ROTATION imp_barrier[0] door_rotation[0] 0.0 0.0
					IF door_rotation[0] <= -90.0
						door_control[0] = 0
					ENDIF				
				ENDIF
			BREAK
		ENDSWITCH


		SWITCH door_control[2]

			CASE 0

				IF player_in_impound = 1
					IF IS_COP_VEHICLE_IN_AREA_3D_NO_SAVE 1592.2513 -1637.5898 17.1898 1578.3444 -1624.2728 11.9866
//					IF IS_CHAR_IN_ANY_CAR scplayer
//						IF IS_CHAR_IN_ANY_POLICE_VEHICLE scplayer
//							IF IS_CHAR_IN_AREA_3D scplayer 1592.2513 -1637.5898 17.1898 1578.3444 -1624.2728 11.9866 FALSE
								IF player_is_threat[2] = 0
									IF NOT IS_CHAR_DEAD imp_cop[2]
										IF IS_CHAR_IN_AREA_2D imp_cop[2] 1582.1528 -1637.6700 1577.5852 -1633.2352 FALSE
											door_control[2] = 1
											REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[0] SOUND_GARAGE_DOOR_START
											IF alarm_on = 0
												IF cop_says_dialogue = 0
													cop_says_dialogue = 1 //it's open, in ya go
													cop_to_speak = imp_cop[2]
												ENDIF
											ENDIF

										ENDIF
									ENDIF
								ENDIF
//							ENDIF
//						ENDIF
					ENDIF
				ENDIF
				IF IS_AREA_OCCUPIED 1581.7324 -1639.4629 16.4817 1599.9355 -1652.0717 7.8825 FALSE TRUE FALSE FALSE FALSE
					door_control[2] = 1	
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[0] SOUND_GARAGE_DOOR_START
				ENDIF
			BREAK
			
			CASE 1 								  


				GET_OBJECT_COORDINATES imp_shutter[0] x y door_height[2]
				door_height[2] += 0.0173
				door_rotation[2] += 0.9 			

				IF door_height[2] >= 16.4250
					door_control[2] = 2
				    door_height[2] = 16.4250
					door_rotation[2] = 90.0
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[0] SOUND_GARAGE_DOOR_STOP
				ENDIF
				SET_OBJECT_COORDINATES imp_shutter[0] x y door_height[2]
				SET_OBJECT_ROTATION imp_shutter[0] door_rotation[2] 0.0 imp_shutter_h1[1]
			BREAK

			CASE 2

				IF NOT IS_AREA_OCCUPIED 1602.0791 -1655.9133 6.5685 1582.7788 -1631.2616 17.1606 FALSE TRUE FALSE FALSE FALSE
					door_control[2] = 3
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[0] SOUND_GARAGE_DOOR_START	
				ENDIF
			BREAK
			
			CASE 3			

				GET_OBJECT_COORDINATES imp_shutter[0] x y door_height[2]
				door_height[2] -= 0.0155 			
				door_rotation[2] -= 0.9

				IF door_height[2] <= 14.5641
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[0] SOUND_GARAGE_DOOR_STOP
					door_control[2] = 0
					door_rotation[2] = 0.0
				    door_height[2] = 14.5641
				ENDIF

				SET_OBJECT_COORDINATES imp_shutter[0] x y door_height[2]
				SET_OBJECT_ROTATION imp_shutter[0] door_rotation[2] 0.0 imp_shutter_h1[1]

				IF IS_AREA_OCCUPIED 1602.0791 -1655.9133 6.5685 1582.7788 -1631.2616 17.1606 FALSE TRUE FALSE FALSE FALSE
					door_control[2] = 1
				ENDIF
			BREAK
		ENDSWITCH

	ENDIF


IF impound_area = 2

	//barrier 1												
		SWITCH door_control[0]

			CASE 0

//				IF IS_COP_VEHICLE_IN_ANGLED_AREA_3D_NO_SAVE 1554.3199 -1632.6689 13.9475 1554.3199 -1620.7622 11.8805 20.0
				IF player_is_threat[0] = 0
					IF IS_COP_VEHICLE_IN_AREA_3D_NO_SAVE -1566.1370 666.7503 5.7750 -1577.6016 658.2418 9.2455
//				IF IS_CHAR_IN_ANY_CAR scplayer
//					IF IS_CHAR_IN_ANY_POLICE_VEHICLE scplayer
//						IF IS_CHAR_IN_AREA_3D scplayer 1554.3199 -1632.6689 13.9475 1535.2715 -1620.7622 11.8805 FALSE
//							IF player_is_threat[0] = 0
								IF NOT IS_CHAR_DEAD imp_cop[0]
									IF IS_CHAR_IN_AREA_2D imp_cop[0] -1573.4056 656.8607 -1572.0332 658.1960 FALSE
										door_control[0] = 1
									ENDIF
									IF alarm_on = 0
										IF cop_says_dialogue = 0
											cop_says_dialogue = 1 //it's open, in ya go
											cop_to_speak = imp_cop[0]
										ENDIF
									ENDIF

								ENDIF
//							ENDIF
//						ENDIF
					ENDIF
				ENDIF
			BREAK

			CASE 1
				door_rotation[0] += 0.5 			
				SET_OBJECT_ROTATION imp_barrier[0] door_rotation[0] 0.0 0.0
				IF door_rotation[0] >= 0.0
					door_control[0] = 2
				ENDIF
			BREAK

			CASE 2
				IF NOT IS_AREA_OCCUPIED -1566.1370 666.7503 5.7750 -1577.6016 658.2418 9.2455 FALSE TRUE FALSE FALSE FALSE
					door_control[0] = 3
				ENDIF	
			BREAK

			CASE 3
				IF IS_AREA_OCCUPIED -1566.1370 666.7503 5.7750 -1577.6016 658.2418 9.2455 FALSE TRUE FALSE FALSE FALSE
					door_control[0] = 1
//					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_barrier[0] SOUND_GARAGE_DOOR_START	
				ELSE
					door_rotation[0] -= 0.5 			
					SET_OBJECT_ROTATION imp_barrier[0] door_rotation[0] 0.0 0.0
					IF door_rotation[0] <= -90.0
						door_control[0] = 0
					ENDIF				
				ENDIF
			BREAK
		ENDSWITCH

	//barrier 1												
		SWITCH door_control[1]

			CASE 0

//				IF IS_COP_VEHICLE_IN_ANGLED_AREA_3D_NO_SAVE 1554.3199 -1632.6689 13.9475 1554.3199 -1620.7622 11.8805 20.0
				IF player_is_threat[1] = 0
					IF IS_COP_VEHICLE_IN_AREA_3D_NO_SAVE -1688.2313 679.8141 20.5924 -1710.4834 687.9370 28.4141
//				IF IS_CHAR_IN_ANY_CAR scplayer
//					IF IS_CHAR_IN_ANY_POLICE_VEHICLE scplayer
//						IF IS_CHAR_IN_AREA_3D scplayer 1554.3199 -1632.6689 13.9475 1535.2715 -1620.7622 11.8805 FALSE
//							IF player_is_threat[0] = 0
								IF NOT IS_CHAR_DEAD imp_cop[1]
									IF IS_CHAR_IN_AREA_2D imp_cop[1] -1701.6477 689.5652 -1700.0862 688.2740 FALSE
										door_control[1] = 1
									ENDIF
									IF alarm_on = 0
										IF cop_says_dialogue = 0
											cop_says_dialogue = 1 //it's open, in ya go
											cop_to_speak = imp_cop[1]
										ENDIF
									ENDIF

								ENDIF

//							ENDIF
//						ENDIF
					ENDIF
				ENDIF
			BREAK

			CASE 1
				door_rotation[1] += 0.5 			
				SET_OBJECT_ROTATION imp_barrier[1] door_rotation[1] 0.0 180.0
				IF door_rotation[1] >= 0.0
					door_control[1] = 2
				ENDIF
			BREAK

			CASE 2
				IF NOT IS_AREA_OCCUPIED -1688.2313 679.8141 20.5924 -1710.4834 687.9370 28.4141 FALSE TRUE FALSE FALSE FALSE
					door_control[1] = 3
				ENDIF	
			BREAK

			CASE 3
				IF IS_AREA_OCCUPIED -1688.2313 679.8141 20.5924 -1710.4834 687.9370 28.4141 FALSE TRUE FALSE FALSE FALSE
					door_control[1] = 1
//					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_barrier[0] SOUND_GARAGE_DOOR_START	
				ELSE
					door_rotation[1] -= 0.5
//					VAR_FLOAT cunty
//					VIEW_FLOAT_VARIABLE cunty cunty
//					cunty = door_rotation[1] 			
					SET_OBJECT_ROTATION imp_barrier[1] door_rotation[1] 0.0 180.0
					IF door_rotation[1] <= -90.0
						door_control[1] = 0
					ENDIF				
				ENDIF
			BREAK
		ENDSWITCH
//
//		VAR_INT aa1 aa2 aa3 aa4	aa5
//
//		aa1 =  door_control[2]
//		aa2 = player_in_impound
//		aa3 = player_is_threat[2] 

//		VIEW_INTEGER_VARIABLE aa1 door_control[2]
//		VIEW_INTEGER_VARIABLE aa2 player_in_impound
//		VIEW_INTEGER_VARIABLE aa3 player_is_threat[2]
//		VIEW_INTEGER_VARIABLE aa4 cop_in_booth
//		VIEW_INTEGER_VARIABLE aa5 cop_car_in_area


		SWITCH door_control[2]

			CASE 0

				IF player_in_impound = 1
						IF IS_COP_VEHICLE_IN_AREA_3D_NO_SAVE -1639.5991 674.9066 10.3898 -1622.2977 710.0969 0.8401
								IF player_is_threat[2] = 0
									IF NOT IS_CHAR_DEAD imp_cop[2]
										IF IS_CHAR_IN_AREA_2D imp_cop[2]-1613.2528 679.4489 -1618.7600 688.0851 FALSE
//											aa4 = 1
											door_control[2] = 1
											REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[0] SOUND_GARAGE_DOOR_START
											IF alarm_on = 0
												IF cop_says_dialogue = 0
													cop_says_dialogue = 1 //it's open, in ya go
													cop_to_speak = imp_cop[2]
												ENDIF
											ENDIF

 
										ENDIF
									ENDIF
								ENDIF
						ENDIF

				ENDIF
				IF activate_doors = 1
					IF IS_AREA_OCCUPIED -1639.5991 674.9066 10.3898 -1622.2977 710.0969 0.8401 FALSE TRUE FALSE FALSE FALSE
						door_control[2] = 1
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[0] SOUND_GARAGE_DOOR_START	
					ENDIF
				ENDIF
				IF IS_AREA_OCCUPIED -1620.0607 715.3710 -4.5540 -1640.8809 691.9899 11.1863 FALSE TRUE FALSE FALSE FALSE
					door_control[2] = 1
				ENDIF

			BREAK
			
			CASE 1 								  


				GET_OBJECT_COORDINATES imp_shutter[0] x y door_height[2]
				door_height[2] += 0.0155 			

				IF door_height[2] >= 13.2513
					door_control[2] = 2
					    door_height[2] = 13.2513
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[0] SOUND_GARAGE_DOOR_STOP
				ENDIF
				SET_OBJECT_COORDINATES imp_shutter[0] x y door_height[2]
			BREAK

			CASE 2

				IF NOT IS_AREA_OCCUPIED -1639.5991 674.9066 10.3898 -1622.2977 710.0969 0.8401 FALSE TRUE FALSE FALSE FALSE
					door_control[2] = 3
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[0] SOUND_GARAGE_DOOR_START	
				ENDIF
			BREAK
			
			CASE 3			

				GET_OBJECT_COORDINATES imp_shutter[0] x y door_height[2]
				door_height[2] -= 0.0155 			

				IF door_height[2] <= 8.5436
					door_control[2] = 0
					door_rotation[2] = 0.0
				    door_height[2] = 8.5436
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[0] SOUND_GARAGE_DOOR_STOP
				ENDIF

				SET_OBJECT_COORDINATES imp_shutter[0] x y door_height[2]

				IF IS_AREA_OCCUPIED -1639.5991 674.9066 10.3898 -1622.2977 710.0969 0.8401 FALSE TRUE FALSE FALSE FALSE
					door_control[2] = 1
				ENDIF
			BREAK
		ENDSWITCH



	ENDIF



	IF impound_area = 3

		VAR_INT door_control[4] 
		VAR_FLOAT door_rotation[4] door_height[4]
		//barrier 1


		SWITCH door_control[0]

			CASE 0

				IF IS_CHAR_IN_ANY_CAR scplayer
					IF IS_CHAR_IN_ANY_POLICE_VEHICLE scplayer
						IF IS_CHAR_IN_AREA_3D scplayer 2250.3235 2449.5557 8.4616 2230.4111 2461.7905 17.8394 FALSE
							IF player_is_threat[0] = 0
								IF NOT IS_CHAR_DEAD imp_cop[0]
									IF IS_CHAR_IN_AREA_2D imp_cop[0] 2237.8535 2448.6084 2239.3853 2450.0144 FALSE
										door_control[0] = 1
										IF alarm_on = 0
											IF cop_says_dialogue = 0
												cop_says_dialogue = 1 //it's open, in ya go
												cop_to_speak = imp_cop[0]
											ENDIF
										ENDIF

									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				IF activate_doors = 1
					IF NOT IS_CAR_DEAD cop_car[0]
						IF IS_CAR_IN_AREA_2D cop_car[0] 2222.3162 2432.0117 2246.9561 2467.6531 FALSE
							door_control[0] = 1
						ENDIF	
					ENDIF
				ENDIF
			BREAK

			CASE 1
				door_rotation[0] += 0.5 			
				SET_OBJECT_ROTATION imp_barrier[0] door_rotation[0] 0.0 0.0
				IF door_rotation[0] >= 0.0
					door_control[0] = 2

				ENDIF
			BREAK

			CASE 2
				IF NOT IS_AREA_OCCUPIED 2250.3235 2449.5557 8.4616 2230.4111 2461.7905 17.8394 FALSE TRUE FALSE FALSE FALSE
					door_control[0] = 3	
				ENDIF	
			BREAK

			CASE 3
				IF IS_AREA_OCCUPIED 2250.3235 2449.5557 8.4616 2230.4111 2461.7905 17.8394 FALSE TRUE FALSE FALSE FALSE
					door_control[0] = 1	
				ELSE
					door_rotation[0] -= 0.5 			
					SET_OBJECT_ROTATION imp_barrier[0] door_rotation[0] 0.0 0.0
					IF door_rotation[0] <= -90.0
						door_control[0] = 0
					ENDIF				
				ENDIF
			BREAK
		ENDSWITCH


		SWITCH door_control[2]

			CASE 0

				IF player_in_impound = 1
					IF IS_CHAR_IN_ANY_CAR scplayer
						IF IS_CHAR_IN_ANY_POLICE_VEHICLE scplayer
							IF IS_CHAR_IN_AREA_3D scplayer 2237.8076 2486.7642 12.7579 2280.2107 2502.5955 6.2211 FALSE
								IF player_is_threat[2] = 0
									IF NOT IS_CHAR_DEAD imp_cop[2]
										IF IS_CHAR_IN_AREA_2D imp_cop[2] 2253.5149 2485.2751 2248.9004 2492.4160 FALSE
											door_control[2] = 1
											REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[0] SOUND_GARAGE_DOOR_START
											IF alarm_on = 0
												IF cop_says_dialogue = 0
													cop_says_dialogue = 1 //it's open, in ya go
													cop_to_speak = imp_cop[2]
												ENDIF
											ENDIF

										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				IF activate_doors = 1
					IF IS_AREA_OCCUPIED 2294.3706 2493.4470 6.6617 2305.7803 2504.3296 2.1808 FALSE TRUE FALSE FALSE FALSE
						door_control[2] = 1
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[0] SOUND_GARAGE_DOOR_START	
					ENDIF
				ENDIF
			BREAK
			
			CASE 1 								  


				GET_OBJECT_COORDINATES imp_shutter[0] x y door_height[2]
				door_height[2] += 0.0155 			

				IF door_height[2] >= 8.8513
					door_control[2] = 2
					    door_height[2] = 8.8513
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[0] SOUND_GARAGE_DOOR_STOP
				ENDIF
				SET_OBJECT_COORDINATES imp_shutter[0] x y door_height[2]
			BREAK

			CASE 2

				IF NOT IS_AREA_OCCUPIED 2286.1479 2503.0842 7.8689 2302.4321 2491.3528 1.8459 FALSE TRUE FALSE FALSE FALSE
					door_control[2] = 3
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[0] SOUND_GARAGE_DOOR_START	
				ENDIF
			BREAK
			
			CASE 3			

				GET_OBJECT_COORDINATES imp_shutter[0] x y door_height[2]
				door_height[2] -= 0.0155 			

				IF door_height[2] <= 4.4414
					door_control[2] = 0
					door_rotation[2] = 0.0
				    door_height[2] = 4.4414
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[0] SOUND_GARAGE_DOOR_STOP
				ENDIF

				SET_OBJECT_COORDINATES imp_shutter[0] x y door_height[2]

				IF IS_AREA_OCCUPIED 2286.1479 2503.0842 7.8689 2302.4321 2491.3528 1.8459 FALSE TRUE FALSE FALSE FALSE
					door_control[2] = 1
				ENDIF
			BREAK
		ENDSWITCH

		SWITCH door_control[3]

			CASE 0
				IF player_in_impound = 1
					IF IS_AREA_OCCUPIED 2333.4810 2439.5759 8.8878 2319.6880 2451.9998 2.1724 FALSE TRUE FALSE FALSE FALSE
						door_control[3] = 1
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[1] SOUND_GARAGE_DOOR_START
					ENDIF
				ENDIF
			BREAK	 								  

			CASE 1
				GET_OBJECT_COORDINATES imp_shutter[1] x y door_height[3]
				door_height[3] += 0.0155 			

				IF door_height[3] >= 11.6441
					door_control[3] = 2
					    door_height[3] = 11.6441
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[1] SOUND_GARAGE_DOOR_STOP
				ENDIF
				SET_OBJECT_COORDINATES imp_shutter[1] x y door_height[3]
			BREAK

			CASE 2
				IF NOT IS_AREA_OCCUPIED 2338.7073 2434.4585 11.8518 2330.9377 2452.4546 3.5886 FALSE TRUE FALSE FALSE FALSE
					door_control[3] = 3
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[1] SOUND_GARAGE_DOOR_START	
				ENDIF			
			BREAK

			CASE 3

				GET_OBJECT_COORDINATES imp_shutter[1] x y door_height[3]
				door_height[3] -= 0.0155 			

				IF door_height[3] <= 6.9743
					door_control[3] = 0
					door_rotation[3] = 0.0
				    door_height[3] = 6.9743
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT imp_shutter[0] SOUND_GARAGE_DOOR_STOP
				ENDIF

				SET_OBJECT_COORDINATES imp_shutter[1] x y door_height[3]

				IF IS_AREA_OCCUPIED 2338.7073 2434.4585 11.8518 2330.9377 2452.4546 3.5886 FALSE TRUE FALSE FALSE FALSE
					door_control[3] = 1
				ENDIF

			BREAK
		ENDSWITCH
	ENDIF

RETURN


/*
cop_decisions:

	cop_task = last_cop_task[cop_i]

	VAR_INT alarm_set_off 

	IF cop_task = WARN_PLAYER
	OR cop_task = KILL_PLAYER
		IF player_in_impound = 1
			cop_alert_level ++
			IF alarm_set_off = 0
				IF cop_alert_level > 25
					alarm_set_off = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF player_in_impound = 0
		cop_alert_level --
	ENDIF


	IF cop_event[cop_i] = -1
		IF cop_threat_level[cop_i] = 0
			cop_threat_level[cop_i] = 1		// noise heard
		ENDIF
	ENDIF

	IF cop_event[cop_i] = -2
		IF cop_threat_level[cop_i] < 2
			cop_threat_level[cop_i] = 2		// danger from player
		ENDIF
	ENDIF			

	IF i_see_you = 1
		IF alarm_set_off = 1
			cop_threat_level[cop_i] = 2	
		ENDIF
		IF cop_threat_level[cop_i] = 2
			cop_task = KILL_PLAYER
		ENDIF
		IF cop_threat_level[cop_i] = 1
			cop_task = WARN_PLAYER
		ENDIF
		IF player_in_impound = 1
			IF cop_threat_level[cop_i] = 0
				cop_threat_level[cop_i] = 1
				cop_task = WARN_PLAYER
			ENDIF	
		ENDIF
	ENDIF

	IF i_see_you = 0
		IF cop_task = KILL_PLAYER
			cop_task = SEEKING_PLAYER
		ENDIF
		IF cop_task = SEEKING_PLAYER
			IF LOCATE_CHAR_ANY_MEANS_3D imp_cop[cop_i] last_player_x last_player_y last_player_z 2.0 2.0 2.0 FALSE
				cop_task = COVER_POSITIONS
			ENDIF
		ENDIF	
		IF cop_threat_level[cop_i] > 0
			IF cop_event[cop_i] < 0
				IF NOT cop_task = 1
				AND NOT cop_task = LOOK_AT_PLAYER
					cop_task = LOOK_AT_PLAYER
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF alarm_set_off = 1
		IF cop_task = AMBIENT			
		OR cop_task = AMBIENT_GIVEN
			cop_task = DEFENSIVE_POSITIONS			
		ENDIF
	ENDIF

	cop_event[cop_i] = 0

	
	last_cop_task[cop_i] = cop_task	

RETURN

*/

LVAR_INT navigating
	  
do_cop_task:


	SWITCH cop_task
	
		CASE AMBIENT

			GOSUB do_ambient_stuff

			IF i_see_you = 1
			AND player_in_impound = 1
				last_cop_task[cop_i] = WARN_PLAYER
				cop_task_flag[cop_i] = 0
			ENDIF

			IF cop_event[cop_i] = -2
				GET_CHAR_COORDINATES imp_cop[cop_i] x y z
				imp_f = player_z - z
				IF imp_f < 3.0
					last_cop_task[cop_i] = IDENTIFY_ATTACKER
					player_is_threat[cop_i] = 1
					cop_task_flag[cop_i] = 0	
				ENDIF
			ENDIF

			IF cop_event[cop_i] = -1
				GET_CHAR_COORDINATES imp_cop[cop_i] x y z
				imp_f = player_z - z
				IF imp_f < 3.0
					last_cop_task[cop_i] = IDENTIFY_ATTACKER
					cop_task_flag[cop_i] = 0
				ENDIF

			ENDIF
			
			IF alarm_on = 1
				last_cop_task[cop_i] = GO_TO_DEFENSE
				cop_task_flag[cop_i] = 0	
			ENDIF	
		
		BREAK

		CASE WARN_PLAYER
			GOSUB navigate
			IF navigating = 0
				IF cop_task_flag[cop_i] = 0
					IF alarm_on = 0
						IF cop_says_dialogue = 0
							cop_to_speak = imp_cop[cop_i]
							cop_says_dialogue = 4
						ENDIF	
					ENDIF
					TASK_GOTO_CHAR_AIMING imp_cop[cop_i] scplayer 5.0 10.0
					cop_timer[cop_i] = TIMERA + 6000
					cop_task_flag[cop_i] = 1
				ENDIF
				IF cop_task_flag[cop_i] = 1
//	If no other cop is calling for backup ( cop_says_dialogue = 0 ), do so. Else is the alarm has gone off attack the player.
					IF TIMERA > cop_timer[cop_i]
						IF alarm_on = 0
							IF cop_says_dialogue = 0
								last_cop_task[cop_i] = CALL_FOR_BACKUP
								player_is_threat[cop_i] = 1
								cop_task_flag[cop_i] = 0
							ENDIF
						ELSE							
							last_cop_task[cop_i] = COP_ATTACK_PLAYER
							player_is_threat[cop_i] = 1
							cop_task_flag[cop_i] = 0								
						ENDIF								
					ENDIF					
				ENDIF
			ENDIF

			IF cop_event[cop_i] = -2				
				last_cop_task[cop_i] = COP_ATTACK_PLAYER
				player_is_threat[cop_i] = 1
				cop_task_flag[cop_i] = 0	
			ENDIF


		BREAK

		CASE COP_ATTACK_PLAYER
			GOSUB navigate
			IF navigating = 0
				IF cop_task_flag[cop_i] = 0
					cop_timer[cop_i] = TIMERA + 7000
					TASK_KILL_CHAR_ON_FOOT imp_cop[cop_i] scplayer
					cop_task_flag[cop_i] = 1
				ENDIF
				IF cop_task_flag[cop_i] = 1
					IF TIMERA > cop_timer[cop_i]
						IF alarm_on = 0
							IF cop_says_dialogue = 0
								last_cop_task[cop_i] = CALL_FOR_BACKUP
								player_is_threat[cop_i] = 1
								cop_task_flag[cop_i] = 0
							ENDIF
						ELSE							
							last_cop_task[cop_i] = COP_ATTACK_PLAYER
							player_is_threat[cop_i] = 1
							cop_task_flag[cop_i] = 0								
						ENDIF								
					ENDIF	
				ENDIF
			ENDIF
			IF i_see_you = 0
				blah ++
				last_cop_task[cop_i] = LOST_PLAYER
				cop_task_flag[cop_i] = 0		
			ENDIF
		BREAK

		CASE IDENTIFY_ATTACKER
			IF cop_task_flag[cop_i] = 0	
				GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_AIM_GUN_AT_COORD imp_task_status
				IF imp_task_status = FINISHED_TASK
					TASK_AIM_GUN_AT_COORD imp_cop[cop_i] last_player_x last_player_y last_player_z 5000
					cop_task_flag[cop_i] = 1
				ENDIF
			ENDIF
			IF cop_task_flag[cop_i] = 1
				GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_AIM_GUN_AT_COORD imp_task_status
				IF imp_task_status = FINISHED_TASK
					IF player_is_threat[cop_i] = 0	
						last_cop_task[cop_i] = RETURN_TO_AMBIENT
						cop_task_flag[cop_i] = 0					
					ENDIF
					IF player_is_threat[cop_i] = 1	
						last_cop_task[cop_i] = GO_TO_DEFENSE
						cop_task_flag[cop_i] = 0					
					ENDIF
				ENDIF
			ENDIF

			IF i_see_you = 1
			AND player_in_impound = 1
				IF player_is_threat[cop_i] = 0
					last_cop_task[cop_i] = WARN_PLAYER
					cop_task_flag[cop_i] = 0
				ELSE
					last_cop_task[cop_i] = COP_ATTACK_PLAYER
					cop_task_flag[cop_i] = 0
				ENDIF
			ENDIF

			IF cop_event[cop_i] = -2				
				last_cop_task[cop_i] = IDENTIFY_ATTACKER
				player_is_threat[cop_i] = 1
				cop_task_flag[cop_i] = 0
			ENDIF
		BREAK

		CASE LOST_PLAYER
			IF cop_task_flag[cop_i] = 0
				GOSUB navigate
				IF navigating = 0
					TASK_GO_TO_COORD_WHILE_AIMING imp_cop[cop_i] last_player_x last_player_y last_player_z PEDMOVE_RUN 1.0 5.0 -1 last_player_x last_player_y last_player_z
				ENDIF
				cop_task_flag[cop_i] = 1
			ENDIF
			IF cop_task_flag[cop_i] = 1
				GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_GO_TO_COORD_WHILE_AIMING imp_task_status
				IF imp_task_status = FINISHED_TASK
					TASK_PAUSE imp_cop[cop_i] 2000
					cop_task_flag[cop_i] = 2
				ENDIF
			ENDIF

			IF cop_task_flag[cop_i] = 2
				GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_PAUSE imp_task_status
				IF imp_task_status = FINISHED_TASK
					last_cop_task[cop_i] = GO_TO_DEFENSE
					cop_task_flag[cop_i] = 0
				ENDIF
			ENDIF

			IF i_see_you = 1
			AND player_in_impound = 1
				last_cop_task[cop_i] = COP_ATTACK_PLAYER
				cop_task_flag[cop_i] = 0
			ENDIF

			IF cop_event[cop_i] = -2				
				last_cop_task[cop_i] = IDENTIFY_ATTACKER
				cop_task_flag[cop_i] = 0	
			ENDIF

			IF cop_event[cop_i] = -1
				last_cop_task[cop_i] = IDENTIFY_ATTACKER
				cop_task_flag[cop_i] = 0
			ENDIF
					
		BREAK

		CASE CALL_FOR_BACKUP
			IF cop_task_flag[cop_i] = 0
				IF cop_says_dialogue = 0
					CLEAR_CHAR_TASKS_IMMEDIATELY imp_cop[cop_i]
					TASK_PLAY_ANIM_NON_INTERRUPTABLE imp_cop[cop_i] PHONE_talk PED 4.0 TRUE FALSE FALSE FALSE 4000
					cop_says_dialogue = 5
					cop_to_speak = imp_cop[cop_i]
					cop_task_flag[cop_i] = 1
				ENDIF
			ENDIF
			IF cop_task_flag[cop_i] = 1
				GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_PLAY_ANIM_NON_INTERRUPTABLE imp_task_status
				IF imp_task_status = FINISHED_TASK
					IF alarm_on = 0
						alarm_on = 1
						IF NOT IS_CHAR_DEAD a_victim
							FREEZE_CHAR_POSITION a_victim FALSE
							TASK_COWER a_victim
						ENDIF
						CLEAR_MISSION_AUDIO 4
						LOAD_MISSION_AUDIO 4 SOUND_SECURITY_ALARM
//						PRINT imp_1 5000 1  PLAY ALARM BELLS SOUND
						player_is_threat[0] = 1
						player_is_threat[1] = 1
						player_is_threat[2] = 1
						player_is_threat[3] = 1
						player_is_threat[4] = 1
						player_is_threat[5] = 1
						player_is_threat[6] = 1
						player_is_threat[7] = 1
						IF NOT IS_CHAR_DEAD imp_cop[1]
							SET_CURRENT_CHAR_WEAPON imp_cop[1] WEAPONTYPE_PISTOL
						ENDIF
						IF NOT IS_CHAR_DEAD imp_cop[6]
							SET_CURRENT_CHAR_WEAPON imp_cop[6] WEAPONTYPE_PISTOL
						ENDIF

						IF flag_player_on_mission = 0
						    ALTER_WANTED_LEVEL_NO_DROP Player1 3
						ENDIF
					ENDIF
					last_cop_task[cop_i] = COP_ATTACK_PLAYER
					cop_task_flag[cop_i] = 0
				ENDIF
			ENDIF
		BREAK

		CASE GO_TO_DEFENSE
			GOSUB defensive_positions
			IF i_see_you = 1
			AND player_in_impound = 1
				last_cop_task[cop_i] = COP_ATTACK_PLAYER
				cop_task_flag[cop_i] = 0
			ENDIF

			IF cop_event[cop_i] = -2				
				last_cop_task[cop_i] = IDENTIFY_ATTACKER
				cop_task_flag[cop_i] = 0	
			ENDIF

			IF cop_event[cop_i] = -1
				last_cop_task[cop_i] = IDENTIFY_ATTACKER
				cop_task_flag[cop_i] = 0
			ENDIF			
		BREAK

	  
	ENDSWITCH

RETURN

VAR_INT imp_seq2

do_ambient_stuff:
	IF impound_area = 1
		IF player_in_impound = 1		
			IF impound_cops_created = 1
				IF player_z < 12.0
					activate_doors = 1
					IF cop_task_flag[cop_i] = 0
						IF cop_i = 3
							FLUSH_ROUTE
							EXTEND_ROUTE 1583.3207 -1691.5746 5.2252
							EXTEND_ROUTE 1555.1051 -1692.1367 5.2252
							EXTEND_ROUTE 1526.5343 -1676.9684 4.8984

							OPEN_SEQUENCE_TASK imp_sequence
								TASK_PAUSE -1 3500
								TASK_LEAVE_ANY_CAR -1
								TASK_PAUSE -1 700
								TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE
								TASK_ACHIEVE_HEADING -1 63.3988
							CLOSE_SEQUENCE_TASK imp_sequence
							PERFORM_SEQUENCE_TASK imp_cop[cop_i] imp_sequence
							CLEAR_SEQUENCE_TASK imp_sequence
						ENDIF
						IF cop_i = 4
							FLUSH_ROUTE
							EXTEND_ROUTE 1582.7219 -1669.5596 4.8942
							EXTEND_ROUTE 1581.7651 -1690.7428 5.2187
							EXTEND_ROUTE 1556.0161 -1690.6990 5.2252
							EXTEND_ROUTE 1525.9700 -1674.6552 4.8984
							OPEN_SEQUENCE_TASK imp_sequence
								TASK_LEAVE_ANY_CAR -1
								TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE																							
								TASK_ACHIEVE_HEADING -1 92.6125
							CLOSE_SEQUENCE_TASK imp_sequence
							PERFORM_SEQUENCE_TASK imp_cop[cop_i] imp_sequence
							CLEAR_SEQUENCE_TASK imp_sequence
						ENDIF
						IF cop_i = 5
							OPEN_SEQUENCE_TASK imp_seq2
								IF NOT IS_CAR_DEAD cop_car[2]
									TASK_CAR_DRIVE_TO_COORD -1 cop_car[2] 1589.8777 -1644.1431 11.2316 10.0 MODE_ACCURATE 0 DRIVINGMODE_STOPFORCARS
									TASK_PAUSE -1 2000
									TASK_CAR_DRIVE_TO_COORD -1 cop_car[2] 1549.4426 -1627.9940 12.3905 10.0 MODE_ACCURATE 0 DRIVINGMODE_STOPFORCARS
								ENDIF
							CLOSE_SEQUENCE_TASK imp_seq2

							FLUSH_ROUTE
							EXTEND_ROUTE 1567.3925 -1706.8468 4.8984
							EXTEND_ROUTE 1566.8848 -1710.1624 4.8984							
							OPEN_SEQUENCE_TASK imp_sequence
								TASK_PAUSE -1 10000
								TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE								
								IF NOT IS_CAR_DEAD cop_car[2]
									TASK_ENTER_CAR_AS_DRIVER -1 cop_car[2] -1
									TASK_CAR_TEMP_ACTION -1 cop_car[2] TEMPACT_REVERSE_RIGHT 1000
									PERFORM_SEQUENCE_TASK -1 imp_seq2

								ENDIF
							CLOSE_SEQUENCE_TASK imp_sequence
							PERFORM_SEQUENCE_TASK imp_cop[cop_i] imp_sequence
							CLEAR_SEQUENCE_TASK imp_sequence
							CLEAR_SEQUENCE_TASK imp_seq2
							cop_task_flag[cop_i] = 1
						ENDIF
						IF cop_i = 6
						LVAR_INT a_victim
							CREATE_RANDOM_CHAR 1610.4458 -1720.4740 5.2252 a_victim
							SET_CHAR_HEADING a_victim 45.0

							TASK_HANDS_UP a_victim -2

							FREEZE_CHAR_POSITION a_victim TRUE
							SET_CURRENT_CHAR_WEAPON imp_cop[cop_i] WEAPONTYPE_UNARMED
							TASK_KILL_CHAR_ON_FOOT imp_cop[cop_i] a_victim
								
						ENDIF
						IF cop_i = 7
							IF NOT IS_CAR_DEAD cop_car[3]
								OPEN_CAR_DOOR cop_car[3] BONNET
							ENDIF
						ENDIF
						cop_task_flag[cop_i] = 1
					ENDIF												
				ENDIF
			ENDIF
		ENDIF
		IF cop_task_flag[cop_i] = 1 
			IF cop_i = 5
				IF cop_task_flag[cop_i] = 1
					IF NOT IS_CHAR_DEAD	imp_cop[5]		
						GET_SCRIPT_TASK_STATUS imp_cop[cop_i] -1 imp_task_status
						IF imp_task_status = FINISHED_TASK
							IF LOCATE_CHAR_IN_CAR_3D imp_cop[cop_i] 2242.1729 2461.9653 9.8203 4.0 4.0 4.0 FALSE
								MARK_CHAR_AS_NO_LONGER_NEEDED imp_cop[5]
								MARK_CAR_AS_NO_LONGER_NEEDED cop_car[1]
								IF door_rotation[0] > -25.0
									TASK_CAR_DRIVE_WANDER imp_cop[cop_i] cop_car[1] 20.0 DRIVINGMODE_STOPFORCARS
									cop_task_flag[cop_i] = 2
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF cop_i = 6
				IF NOT IS_CHAR_DEAD a_victim
					SET_CHAR_HEALTH a_victim 100

					GET_SCRIPT_TASK_STATUS a_victim TASK_HANDS_UP imp_Task_status
					IF imp_Task_status = FINISHED_TASK
						TASK_HANDS_UP a_victim -2
						IF NOT IS_CHAR_DEAD imp_cop[1]
							TASK_PLAY_ANIM imp_cop[1] fucku PED 4.0 FALSE FALSE FALSE FALSE -2
						ENDIF
					ENDIF


					
				ENDIF
			ENDIF			
		ENDIF
	ENDIF
	IF impound_area = 2
		IF impound_cops_created = 1
			IF player_z < -0.7114
				activate_doors = 1
				IF cop_task_flag[cop_i] = 0
					IF cop_i = 3
							FLUSH_ROUTE
							EXTEND_ROUTE -1601.9883 744.1652 -6.2422
							EXTEND_ROUTE -1593.3428 729.7554 -5.9062
							EXTEND_ROUTE -1593.4396 718.5184 -6.2344
						OPEN_SEQUENCE_TASK imp_sequence
							TASK_PAUSE -1 1000
							TASK_LEAVE_ANY_CAR -1
							TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE																											
							TASK_ACHIEVE_HEADING -1 146.3988
						CLOSE_SEQUENCE_TASK imp_sequence
						PERFORM_SEQUENCE_TASK imp_cop[cop_i] imp_sequence
						CLEAR_SEQUENCE_TASK imp_sequence
					ENDIF
					IF cop_i = 4

							FLUSH_ROUTE
							EXTEND_ROUTE -1592.1191 729.1914 -5.9062
							EXTEND_ROUTE -1592.7526 715.2950 -6.2344

						OPEN_SEQUENCE_TASK imp_sequence
							TASK_LEAVE_ANY_CAR -1
							TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE																					
							TASK_ACHIEVE_HEADING -1 103.6125
						CLOSE_SEQUENCE_TASK imp_sequence
						PERFORM_SEQUENCE_TASK imp_cop[cop_i] imp_sequence
						CLEAR_SEQUENCE_TASK imp_sequence
					ENDIF
					IF cop_i = 5
						OPEN_SEQUENCE_TASK imp_seq2
							IF NOT IS_CAR_DEAD cop_car[3]
								TASK_CAR_DRIVE_TO_COORD -1 cop_car[3] -1629.9249 694.9102 5.8578 10.0 MODE_ACCURATE 0 DRIVINGMODE_STOPFORCARS
								TASK_PAUSE -1 8000
								TASK_CAR_DRIVE_TO_COORD -1 cop_car[3] -1578.7953 662.8546 6.1874 10.0 MODE_ACCURATE 0 DRIVINGMODE_STOPFORCARS
							ENDIF
						CLOSE_SEQUENCE_TASK imp_seq2

							FLUSH_ROUTE
							EXTEND_ROUTE -1595.7108 698.4703 -5.9062
							EXTEND_ROUTE -1587.2083 701.9536 -5.9140
							EXTEND_ROUTE -1577.2008 720.1028 -6.2344
							EXTEND_ROUTE -1574.4144 720.1995 -6.2344						
						OPEN_SEQUENCE_TASK imp_sequence
							TASK_PAUSE -1 10000
							TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE																					
							IF NOT IS_CAR_DEAD cop_car[3]
								TASK_ENTER_CAR_AS_DRIVER -1 cop_car[3] -1
								TASK_CAR_TEMP_ACTION -1 cop_car[3] TEMPACT_REVERSE_RIGHT 1000
								PERFORM_SEQUENCE_TASK -1 imp_seq2

							ENDIF
						CLOSE_SEQUENCE_TASK imp_sequence
						PERFORM_SEQUENCE_TASK imp_cop[cop_i] imp_sequence
						CLEAR_SEQUENCE_TASK imp_sequence
						CLEAR_SEQUENCE_TASK imp_seq2
						cop_task_flag[cop_i] = 1
					ENDIF
					IF cop_i = 6
						IF NOT IS_CAR_DEAD cop_car[1]
							OPEN_CAR_DOOR cop_car[1] BONNET
						ENDIF							
					ENDIF

					cop_task_flag[cop_i] = 1
				ENDIF											
			ENDIF
			IF cop_task_flag[cop_i] = 1 
				IF cop_task_flag[cop_i] = 1
					IF NOT IS_CHAR_DEAD	imp_cop[5]		
						GET_SCRIPT_TASK_STATUS imp_cop[cop_i] -1 imp_task_status
						IF imp_task_status = FINISHED_TASK
							IF LOCATE_CHAR_IN_CAR_3D imp_cop[cop_i] 2242.1729 2461.9653 9.8203 4.0 4.0 4.0 FALSE
								MARK_CHAR_AS_NO_LONGER_NEEDED imp_cop[5]
								MARK_CAR_AS_NO_LONGER_NEEDED cop_car[1]
								IF door_rotation[0] > -25.0
									TASK_CAR_DRIVE_WANDER imp_cop[cop_i] cop_car[1] 20.0 DRIVINGMODE_STOPFORCARS
								ENDIF						
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				cop_task_flag[cop_i] = 2
			ENDIF	
		ENDIF
	ENDIF

	IF impound_area = 3		
		IF impound_cops_created = 1
			IF player_z < 4.3
			AND player_x > 2292.0
				activate_doors = 1
				IF cop_task_flag[cop_i] = 0
					IF cop_i = 3
							FLUSH_ROUTE
							EXTEND_ROUTE 2292.4128 2466.8594 2.5313
							EXTEND_ROUTE 2292.5491 2448.6233 2.5313
							EXTEND_ROUTE 2290.4387 2446.9446 2.5313
							EXTEND_ROUTE 2271.2473 2446.7988 2.5313
							EXTEND_ROUTE 2266.6177 2449.1538 2.5313
						OPEN_SEQUENCE_TASK imp_sequence
							TASK_PAUSE -1 1000
							TASK_LEAVE_ANY_CAR -1
							TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE																											
							TASK_ACHIEVE_HEADING -1 337.3988
						CLOSE_SEQUENCE_TASK imp_sequence
						PERFORM_SEQUENCE_TASK imp_cop[cop_i] imp_sequence
						CLEAR_SEQUENCE_TASK imp_sequence
					ENDIF
					IF cop_i = 4

							FLUSH_ROUTE
							EXTEND_ROUTE 2283.3777 2469.5615 2.5313
							EXTEND_ROUTE 2290.2227 2466.8884 2.5313
							EXTEND_ROUTE 2290.4868 2449.4009 2.5313
							EXTEND_ROUTE 2269.6228 2448.8000 2.5313
						OPEN_SEQUENCE_TASK imp_sequence
							TASK_LEAVE_ANY_CAR -1
							TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE																					
							TASK_ACHIEVE_HEADING -1 25.6125
						CLOSE_SEQUENCE_TASK imp_sequence
						PERFORM_SEQUENCE_TASK imp_cop[cop_i] imp_sequence
						CLEAR_SEQUENCE_TASK imp_sequence
					ENDIF
					IF cop_i = 5
						OPEN_SEQUENCE_TASK imp_seq2
							IF NOT IS_CAR_DEAD cop_car[1]
								TASK_CAR_DRIVE_TO_COORD -1 cop_car[1] 2300.1028 2494.4788 2.2660 10.0 MODE_ACCURATE 0 DRIVINGMODE_STOPFORCARS
								TASK_PAUSE -1 8000
								TASK_CAR_DRIVE_TO_COORD -1 cop_car[1] 2242.1729 2461.9653 9.8203 10.0 MODE_ACCURATE 0 DRIVINGMODE_STOPFORCARS
							ENDIF
						CLOSE_SEQUENCE_TASK imp_seq2

							FLUSH_ROUTE
							EXTEND_ROUTE 2258.9143 2447.8206 2.5313
							EXTEND_ROUTE 2258.3457 2467.5803 2.5313
							EXTEND_ROUTE 2298.0686 2467.7925 2.2660
							EXTEND_ROUTE 2313.5530 2474.0452 2.2660						
						OPEN_SEQUENCE_TASK imp_sequence
							TASK_PAUSE -1 10000
							TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE																					
							IF NOT IS_CAR_DEAD cop_car[1]
								TASK_ENTER_CAR_AS_DRIVER -1 cop_car[1] -1
								TASK_CAR_TEMP_ACTION -1 cop_car[1] TEMPACT_REVERSE_RIGHT 1000
								PERFORM_SEQUENCE_TASK -1 imp_seq2

							ENDIF
						CLOSE_SEQUENCE_TASK imp_sequence
						PERFORM_SEQUENCE_TASK imp_cop[cop_i] imp_sequence
						CLEAR_SEQUENCE_TASK imp_sequence
						CLEAR_SEQUENCE_TASK imp_seq2
						cop_task_flag[cop_i] = 1
					ENDIF
					cop_task_flag[cop_i] = 1
				ENDIF											
			ENDIF
			IF cop_task_flag[cop_i] = 1 
				IF cop_task_flag[cop_i] = 1
					IF NOT IS_CHAR_DEAD	imp_cop[5]		
						GET_SCRIPT_TASK_STATUS imp_cop[cop_i] -1 imp_task_status
						IF imp_task_status = FINISHED_TASK
							IF LOCATE_CHAR_IN_CAR_3D imp_cop[cop_i] 2242.1729 2461.9653 9.8203 4.0 4.0 4.0 FALSE
								MARK_CHAR_AS_NO_LONGER_NEEDED imp_cop[5]
								MARK_CAR_AS_NO_LONGER_NEEDED cop_car[1]
								IF door_rotation[0] > -25.0
									TASK_CAR_DRIVE_WANDER imp_cop[cop_i] cop_car[1] 20.0 DRIVINGMODE_STOPFORCARS
								ENDIF						
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				cop_task_flag[cop_i] = 2
			ENDIF	
		ENDIF
	ENDIF

RETURN





defensive_positions:


	IF impound_area = 1
		GET_CHAR_COORDINATES imp_cop[cop_i] x y z
		IF z > 16.3914
			IF NOT LOCATE_CHAR_ANY_MEANS_3D imp_cop[cop_i] 1553.0004 -1627.8368 12.3905 5.0 5.0 5.0 FALSE
				GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_GO_STRAIGHT_TO_COORD imp_task_status
				IF imp_task_status = FINISHED_TASK
					CLEAR_CHAR_TASKS imp_cop[cop_i]
					TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] 1553.0004 -1627.8368 12.3905 PEDMOVE_RUN -2
				ENDIF
			ELSE
				GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_AIM_GUN_AT_COORD imp_task_status
				IF imp_task_status = FINISHED_TASK  								
					IF last_player_z > 15.0
						TASK_AIM_GUN_AT_COORD imp_cop[cop_i] 1566.3292 -1628.9175 13.7858 8000
					ENDIF														
				ENDIF
			ENDIF
		ELSE
			IF x > 1580.8196 
			AND y > -1678.5176
				IF NOT LOCATE_CHAR_ANY_MEANS_3D imp_cop[cop_i] 1596.3882 -1665.7981 4.8604 5.0 5.0 5.0 FALSE
					GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_GO_STRAIGHT_TO_COORD imp_task_status
					IF imp_task_status = FINISHED_TASK
						CLEAR_CHAR_TASKS imp_cop[cop_i]
						TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] 1596.3882 -1665.7981 4.8604 PEDMOVE_RUN -2
					ENDIF
				ELSE
					GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_AIM_GUN_AT_COORD imp_task_status
					IF imp_task_status = FINISHED_TASK  								
						IF last_player_z > 10.5
							TASK_AIM_GUN_AT_COORD imp_cop[cop_i] 1588.9202 -1643.8271 13.2958 8000
						ELSE
							TASK_AIM_GUN_AT_COORD imp_cop[cop_i] 1551.4624 -1625.4535 12.3905 8000
						ENDIF														
					ENDIF
				ENDIF
			ELSE
				IF x > 1565.1006
					IF NOT LOCATE_CHAR_ANY_MEANS_3D imp_cop[cop_i] 1593.2103 -1702.3601 4.8970 5.0 5.0 5.0 FALSE
						GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_GO_STRAIGHT_TO_COORD imp_task_status
						IF imp_task_status = FINISHED_TASK
							CLEAR_CHAR_TASKS imp_cop[cop_i]
							TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] 1593.2103 -1702.3601 4.8970 PEDMOVE_RUN -2
						ENDIF
					ELSE
						GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_AIM_GUN_AT_COORD imp_task_status
						IF imp_task_status = FINISHED_TASK  								
							TASK_AIM_GUN_AT_COORD imp_cop[cop_i] 1589.4657 -1699.1357 5.6059 8000												
						ENDIF
					ENDIF					
				ELSE
					IF NOT LOCATE_CHAR_ANY_MEANS_3D imp_cop[cop_i] 1542.6835 -1706.7471 4.8984 5.0 5.0 5.0 FALSE
						GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_GO_STRAIGHT_TO_COORD imp_task_status
						IF imp_task_status = FINISHED_TASK
							CLEAR_CHAR_TASKS imp_cop[cop_i]
							TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] 1542.6835 -1706.7471 4.8984 PEDMOVE_RUN -2
						ENDIF
					ELSE
						GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_AIM_GUN_AT_COORD imp_task_status
						IF imp_task_status = FINISHED_TASK  								
							TASK_AIM_GUN_AT_COORD imp_cop[cop_i] 1550.0576 -1696.4437 6.4232 8000												
						ENDIF
					ENDIF	
				ENDIF
			ENDIF
							
		ENDIF
	ENDIF

	IF impound_area = 2
		GET_CHAR_COORDINATES imp_cop[cop_i] x y z
		IF z > 1.6789
			IF x < -1667.0586
				IF NOT LOCATE_CHAR_ANY_MEANS_3D imp_cop[cop_i] -1697.1847 684.1302 23.0100 5.0 5.0 5.0 FALSE
					GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_GO_STRAIGHT_TO_COORD imp_task_status
					IF imp_task_status = FINISHED_TASK
						CLEAR_CHAR_TASKS imp_cop[cop_i]
						TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] -1697.1847 684.1302 23.0100 PEDMOVE_RUN -2
					ENDIF
				ELSE
					GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_AIM_GUN_AT_COORD imp_task_status
					IF imp_task_status = FINISHED_TASK  								
						GENERATE_RANDOM_INT_IN_RANGE 0 2 imp_i
						IF imp_i = 0
							GENERATE_RANDOM_INT_IN_RANGE 5000 8000 imp_i
							TASK_AIM_GUN_AT_COORD imp_cop[cop_i] -1706.2533 683.8111 25.3122 imp_i												
						ELSE
							GENERATE_RANDOM_INT_IN_RANGE 5000 8000 imp_i
							TASK_AIM_GUN_AT_COORD imp_cop[cop_i] -1685.6909 683.6411 20.7149 imp_i
						ENDIF
					ENDIF
				ENDIF							
			ELSE
				IF x > -1604.5994
					IF NOT LOCATE_CHAR_ANY_MEANS_3D imp_cop[cop_i] -1576.9525 661.7159 6.1901 5.0 5.0 5.0 FALSE
						GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_GO_STRAIGHT_TO_COORD imp_task_status
						IF imp_task_status = FINISHED_TASK
							CLEAR_CHAR_TASKS imp_cop[cop_i]
							TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] -1576.9525 661.7159 6.1901 PEDMOVE_RUN -2
						ENDIF
					ELSE
						GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_AIM_GUN_AT_COORD imp_task_status
						IF imp_task_status = FINISHED_TASK  								
							GENERATE_RANDOM_INT_IN_RANGE 0 2 imp_i
							IF imp_i = 0
								GENERATE_RANDOM_INT_IN_RANGE 5000 8000 imp_i
								TASK_AIM_GUN_AT_COORD imp_cop[cop_i] -1584.8740 663.1520 7.414 imp_i												
							ELSE
								GENERATE_RANDOM_INT_IN_RANGE 5000 8000 imp_i
								TASK_AIM_GUN_AT_COORD imp_cop[cop_i] -1566.2065 664.1497 8.6210 imp_i
							ENDIF
						ENDIF
					ENDIF	
				ELSE
					IF NOT LOCATE_CHAR_ANY_MEANS_3D imp_cop[cop_i] -1629.6091 683.5222 6.1901 5.0 5.0 5.0 FALSE
						GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_GO_STRAIGHT_TO_COORD imp_task_status
						IF imp_task_status = FINISHED_TASK
							CLEAR_CHAR_TASKS imp_cop[cop_i]
							TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] -1629.6091 683.5222 6.1901 PEDMOVE_RUN -2
						ENDIF
					ELSE
						GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_AIM_GUN_AT_COORD imp_task_status
						IF imp_task_status = FINISHED_TASK  								
							GENERATE_RANDOM_INT_IN_RANGE 0 3 imp_i
							IF imp_i = 0
								GENERATE_RANDOM_INT_IN_RANGE 5000 8000 imp_i
								TASK_AIM_GUN_AT_COORD imp_cop[cop_i] -1629.2981 694.5806 6.6079 imp_i												
							ENDIF
							IF imp_i = 1
								GENERATE_RANDOM_INT_IN_RANGE 5000 8000 imp_i
								TASK_AIM_GUN_AT_COORD imp_cop[cop_i] -1617.5051 677.6022 7.9235 imp_i
							ENDIF
							IF imp_i = 2
								GENERATE_RANDOM_INT_IN_RANGE 5000 8000 imp_i
								TASK_AIM_GUN_AT_COORD imp_cop[cop_i] -1648.4879 682.1654 10.8535 imp_i
							ENDIF

						ENDIF
					ENDIF	
				ENDIF
			ENDIF
		ELSE
			IF x < -1598.6807
			AND y > 707.9230
				IF NOT LOCATE_CHAR_ANY_MEANS_3D imp_cop[cop_i] -1618.0671 742.9348 -6.2344 5.0 5.0 5.0 FALSE
					GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_GO_STRAIGHT_TO_COORD imp_task_status
					IF imp_task_status = FINISHED_TASK
						CLEAR_CHAR_TASKS imp_cop[cop_i]
						TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] -1618.0671 742.9348 -6.2344 PEDMOVE_RUN -2
					ENDIF
				ELSE
					GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_AIM_GUN_AT_COORD imp_task_status
					IF imp_task_status = FINISHED_TASK  								
						GENERATE_RANDOM_INT_IN_RANGE 0 2 imp_i
						IF imp_i = 0
							GENERATE_RANDOM_INT_IN_RANGE 5000 8000 imp_i
							TASK_AIM_GUN_AT_COORD imp_cop[cop_i] -1627.6671 738.3230 -5.0193 imp_i												
						ELSE
							GENERATE_RANDOM_INT_IN_RANGE 5000 8000 imp_i
							TASK_AIM_GUN_AT_COORD imp_cop[cop_i] -1601.4359 739.1880 -4.6960 imp_i
						ENDIF
					ENDIF
				ENDIF					
			ENDIF
			IF x > -1598.6807
			AND y > 707.9230
				IF NOT LOCATE_CHAR_ANY_MEANS_3D imp_cop[cop_i] -1581.8879 738.4942 -6.2422 5.0 5.0 5.0 FALSE
					GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_GO_STRAIGHT_TO_COORD imp_task_status
					IF imp_task_status = FINISHED_TASK
						CLEAR_CHAR_TASKS imp_cop[cop_i]
						TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] -1581.8879 738.4942 -6.2422 PEDMOVE_RUN -2
					ENDIF
				ELSE
					GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_AIM_GUN_AT_COORD imp_task_status
					IF imp_task_status = FINISHED_TASK  								
						GENERATE_RANDOM_INT_IN_RANGE 0 2 imp_i
						IF imp_i = 0
							GENERATE_RANDOM_INT_IN_RANGE 5000 8000 imp_i
							TASK_AIM_GUN_AT_COORD imp_cop[cop_i] -1594.0332 741.5932 -4.9811 imp_i												
						ELSE
							GENERATE_RANDOM_INT_IN_RANGE 5000 8000 imp_i
							TASK_AIM_GUN_AT_COORD imp_cop[cop_i] -1583.2504 724.5026 -5.0768 imp_i
						ENDIF
					ENDIF
				ENDIF	
			ENDIF

			IF y < 707.9230
				IF NOT LOCATE_CHAR_ANY_MEANS_3D imp_cop[cop_i] -1591.0927 688.5550 -6.2422 5.0 5.0 5.0 FALSE
					GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_GO_STRAIGHT_TO_COORD imp_task_status
					IF imp_task_status = FINISHED_TASK
						CLEAR_CHAR_TASKS imp_cop[cop_i]
						TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] -1591.0927 688.5550 -6.2422 PEDMOVE_RUN -2
					ENDIF
				ELSE
					GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_AIM_GUN_AT_COORD imp_task_status
					IF imp_task_status = FINISHED_TASK  								
						GENERATE_RANDOM_INT_IN_RANGE 0 2 imp_i
						IF imp_i = 0
							GENERATE_RANDOM_INT_IN_RANGE 5000 8000 imp_i
							TASK_AIM_GUN_AT_COORD imp_cop[cop_i] -1602.0853 687.7857 -4.6028 imp_i												
						ELSE
							GENERATE_RANDOM_INT_IN_RANGE 5000 8000 imp_i
							TASK_AIM_GUN_AT_COORD imp_cop[cop_i] -1582.9609 717.1259 -4.2141 imp_i
						ENDIF
					ENDIF
				ENDIF	
			ENDIF
		ENDIF
	ENDIF
				


	IF impound_area = 3
		GET_CHAR_COORDINATES imp_cop[cop_i] x y z
		IF z > 8.9783
			IF y < 2484.5981
				IF NOT LOCATE_CHAR_ANY_MEANS_3D imp_cop[cop_i] 2241.8794 2453.6025 9.820 5.0 5.0 5.0 FALSE
					GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_GO_STRAIGHT_TO_COORD imp_task_status
					IF imp_task_status = FINISHED_TASK
						CLEAR_CHAR_TASKS imp_cop[cop_i]
						TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] 2241.8794 2453.6025 9.820 PEDMOVE_RUN -2
					ENDIF
				ELSE						
					
					GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_AIM_GUN_AT_COORD imp_task_status
					IF imp_task_status = FINISHED_TASK  								
						GENERATE_RANDOM_INT_IN_RANGE 5000 8000 imp_i
						IF cop_task = GO_TO_DEFENSE
							TASK_AIM_GUN_AT_COORD imp_cop[cop_i] 2242.1157 2478.9800 10.8203 8000
						ELSE
							TASK_AIM_GUN_AT_COORD imp_cop[cop_i] last_player_x last_player_y last_player_z 8000								
						ENDIF  							
					ENDIF
				ENDIF
			ELSE
				IF NOT LOCATE_CHAR_ANY_MEANS_3D imp_cop[cop_i] 2243.2395 2495.0989 9.8203 5.0 5.0 5.0 FALSE
					GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_GO_STRAIGHT_TO_COORD imp_task_status
					IF imp_task_status = FINISHED_TASK
						CLEAR_CHAR_TASKS imp_cop[cop_i]
						TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] 2243.2395 2495.0989 9.8203 PEDMOVE_RUN -2
					ENDIF
				ELSE						
					GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_AIM_GUN_AT_COORD imp_task_status
					IF imp_task_status = FINISHED_TASK  								
						IF cop_task = GO_TO_DEFENSE
							IF last_player_z > 5.0
								TASK_AIM_GUN_AT_COORD imp_cop[cop_i] 2242.4529 2487.0601 11.7533 8000
							ELSE
								TASK_AIM_GUN_AT_COORD imp_cop[cop_i] 2268.1130 2498.4556 7.7616 8000
							ENDIF
						ELSE
							TASK_AIM_GUN_AT_COORD imp_cop[cop_i] last_player_x last_player_y last_player_z 8000								
						ENDIF  							
					ENDIF
				ENDIF
			ENDIF
		ELSE
			IF z > 2.1249
				IF y > 2458.3274
					IF NOT LOCATE_CHAR_ANY_MEANS_3D imp_cop[cop_i] 2306.3765 2495.5405 2.2660 5.0 5.0 5.0 FALSE
						GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_GO_STRAIGHT_TO_COORD imp_task_status
						IF imp_task_status = FINISHED_TASK
							CLEAR_CHAR_TASKS imp_cop[cop_i]
							TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] 2306.3765 2495.5405 2.2660 PEDMOVE_RUN -2
						ENDIF
					ELSE						
						GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_AIM_GUN_AT_COORD imp_task_status
						IF imp_task_status = FINISHED_TASK  								
			   				TASK_AIM_GUN_AT_COORD imp_cop[cop_i] 2295.1768 2471.7605 3.9550 8000								
						ENDIF  							
					ENDIF
				ELSE
					IF NOT LOCATE_CHAR_ANY_MEANS_3D imp_cop[cop_i] 2316.0601 2446.9119 2.2660 5.0 5.0 5.0 FALSE
						GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_GO_STRAIGHT_TO_COORD imp_task_status
						IF imp_task_status = FINISHED_TASK
							CLEAR_CHAR_TASKS imp_cop[cop_i]
							TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] 2316.0601 2446.9119 2.2660 PEDMOVE_RUN -2
						ENDIF
					ELSE						
						GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_AIM_GUN_AT_COORD imp_task_status
						IF imp_task_status = FINISHED_TASK  								
			   				TASK_AIM_GUN_AT_COORD imp_cop[cop_i] 2295.2158 2451.0876 4.3783 8000								
						ENDIF  													
					ENDIF
				ENDIF
			ELSE
				IF NOT LOCATE_CHAR_ANY_MEANS_3D imp_cop[cop_i] 2238.7532 2457.8313 -8.4531 5.0 5.0 5.0 FALSE
					GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_GO_STRAIGHT_TO_COORD imp_task_status
					IF imp_task_status = FINISHED_TASK
						CLEAR_CHAR_TASKS imp_cop[cop_i]
						TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] 2238.7532 2457.8313 -8.4531 PEDMOVE_RUN -2
					ENDIF
				ELSE						
					GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_AIM_GUN_AT_COORD imp_task_status
					IF imp_task_status = FINISHED_TASK  								
		   				TASK_AIM_GUN_AT_COORD imp_cop[cop_i] 2251.7988 2465.2363 -7.2173 8000								
					ENDIF  							
				ENDIF				
			ENDIF
		ENDIF
	ENDIF


RETURN


get_kill_task:

	IF impound_area = 3
		GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_KILL_CHAR_ON_FOOT imp_task_status
		IF imp_task_Status = FINISHED_TASK
			TASK_KILL_CHAR_ON_FOOT imp_cop[cop_i] scplayer
		ENDIF
	ENDIF
RETURN


navigate:

	IF impound_area = 3
		
		GET_CHAR_COORDINATES imp_cop[cop_i] x y z
		GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_GO_STRAIGHT_TO_COORD	imp_task_status
		IF imp_task_status = FINISHED_TASK
			GET_SCRIPT_TASK_STATUS imp_cop[cop_i] PERFORM_SEQUENCE_TASK	imp_task_status
			IF imp_task_status = FINISHED_TASK
				IF z > 8.9783
					IF y < 2481.7961
					// cop in booth
						IF IS_CHAR_IN_AREA_2D imp_cop[cop_i] 2237.8535 2448.6084 2239.3853 2450.0144 FALSE
							IF NOT IS_CHAR_IN_AREA_2D scplayer 2239.5725 2448.6160 2242.3625 2449.8511 FALSE
								TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] 2240.0337 2449.4182 9.8203 PEDMOVE_WALK -2
								navigating = 1
							ENDIF
						ENDIF
					ENDIF
					IF y >= 2481.7961
						IF IS_CHAR_IN_AREA_2D imp_cop[cop_i] 2253.5149 2485.2751 2248.9004 2492.4160 FALSE
							IF NOT IS_CHAR_IN_AREA_2D scplayer 2253.5149 2485.2751 2248.9004 2492.4160 FALSE
							AND NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D imp_cop[cop_i] scplayer 1.5 1.5 2.0 FALSE
								OPEN_SEQUENCE_TASK imp_sequence
									TASK_GO_STRAIGHT_TO_COORD -1 2250.1545 2486.4053 9.8203 PEDMOVE_WALK -2
									TASK_GO_STRAIGHT_TO_COORD -1 2247.8228 2487.0564 9.8203 PEDMOVE_WALK -2
									TASK_GO_STRAIGHT_TO_COORD -1 2246.2205 2488.3203 9.8203 PEDMOVE_WALK -2
								CLOSE_SEQUENCE_TASK imp_sequence
								PERFORM_SEQUENCE_TASK imp_cop[cop_i] imp_sequence
								CLEAR_SEQUENCE_TASK imp_sequence
								navigating = 1
							ENDIF
						ENDIF
					ENDIF	
				ENDIF
			ENDIF
		ENDIF
	ENDIF 

	IF impound_area = 1		

		GET_CHAR_COORDINATES imp_cop[cop_i] x y z	
		GET_SCRIPT_TASK_STATUS imp_cop[cop_i] TASK_GO_STRAIGHT_TO_COORD	imp_task_status
		IF imp_task_status = FINISHED_TASK
			GET_SCRIPT_TASK_STATUS imp_cop[cop_i] PERFORM_SEQUENCE_TASK	imp_task_status
			IF imp_task_status = FINISHED_TASK
				IF z > 12.0
					IF x < 1569.0
					// cop in booth
						IF IS_CHAR_IN_AREA_2D imp_cop[cop_i] 1543.7490 -1632.5879 1544.8206 -1631.4252 FALSE
							IF NOT IS_CHAR_IN_AREA_2D scplayer 1544.9664 -1632.8795 1546.4954 -1630.6456 FALSE
								TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] 1545.8812 -1631.9177 12.3905 PEDMOVE_WALK -2
								navigating = 1
							ENDIF
						ENDIF
					ELSE
						IF IS_CHAR_IN_AREA_2D imp_cop[cop_i] 1581.9529 -1637.5692 1577.3757 -1632.9922 FALSE
							IF NOT IS_CHAR_IN_AREA_2D scplayer 1581.9529 -1637.5692 1577.3757 -1632.9922 FALSE
							AND NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D imp_cop[cop_i] scplayer 1.5 1.5 2.0 FALSE
								FLUSH_ROUTE
								EXTEND_ROUTE 1578.5172 -1636.4945 12.5545
								EXTEND_ROUTE 1576.3663 -1636.3949 12.5539
								EXTEND_ROUTE 1573.9812 -1632.7644 12.3905




								OPEN_SEQUENCE_TASK imp_sequence
									TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE
								CLOSE_SEQUENCE_TASK imp_sequence
								PERFORM_SEQUENCE_TASK imp_cop[cop_i] imp_sequence
								CLEAR_SEQUENCE_TASK imp_sequence
								navigating = 1
							ENDIF
						ENDIF
					ENDIF	
				ENDIF
			ENDIF
		ENDIF
	ENDIF 


RETURN



return_to_booth_task:

	IF impound_area = 3	
		IF cop_i = 0
			IF NOT IS_CHAR_IN_AREA_2D imp_cop[cop_i] 2237.8535 2448.6084 2239.3853 2450.0144 FALSE
				IF NOT IS_CHAR_IN_AREA_2D imp_cop[cop_i] 2239.5725 2448.6160 2242.3625 2449.8511 FALSE
					TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] 2240.0337 2449.4182 9.8203 PEDMOVE_WALK -2
				ELSE
					TASK_GO_STRAIGHT_TO_COORD imp_cop[cop_i] 2238.5693 2449.4170 10.0372 PEDMOVE_WALK -2
				ENDIF				
			ENDIF
		ENDIF
	ENDIF

RETURN

check_player_in_impound:

	player_in_impound = 0
		
	IF impound_area = 3
		IF player_z > 3.9517
		AND player_z < 14.0 
			IF player_x > 2238.0
			AND player_x < 2299.0
				IF player_y > 2430.9158
				AND player_y < 2502.6809
					player_in_impound = 1
				ENDIF
			ENDIF
		ENDIF
		IF player_z <= 3.9517
			player_in_impound = 1
		ENDIF
	ENDIF

	IF impound_area = 2
		IF player_x > -1645.5334
		AND player_x < -1572.7845
			IF player_y > 646.8482
			AND player_y < 761.5287
				IF player_z < 11.4577
				AND player_z > -7.3486
					player_in_impound = 1
				ENDIF
			ENDIF
		ENDIF

		IF player_x > -1701.0127
		AND player_x < -1640.9906
			IF player_y > 674.5198
			AND player_y < 697.4974
				IF player_z > 6.0960
				AND player_z < 26.5941
					player_in_impound = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF impound_area = 1
		IF player_z < 11.7746
			IF player_x > 1524.0170
			AND player_x < 1612.4044
				IF player_y < -1633.5734
				AND player_y > -1722.0356
					player_in_impound = 1
				ENDIF
			ENDIF
		ELSE
			IF player_x > 1544.4266
			AND player_x < 1607.5883
				IF player_y < -1603.0154 
				AND player_y > -1637.6974
					player_in_impound = 1
				ENDIF
			ENDIF
		ENDIF

		IF player_x > 1581.4075
		AND player_x < 1605.2029
			IF player_y > -1666.7345
			AND player_y < -1637.6700
				IF player_z > 4.8451
				AND player_z < 13.6917
					player_in_impound = 1
				ENDIF
			ENDIF
		ENDIF

	ENDIF

RETURN

check_cop_see_player:


LVAR_FLOAT xcomp ycomp imp_heading cop_heading
VAR_FLOAT last_player_x last_player_y last_player_z

	IF NOT IS_CHAR_IN_ANY_POLICE_VEHICLE scplayer
	OR player_is_threat[cop_i] = 1
		IF HAS_CHAR_SPOTTED_CHAR imp_cop[cop_i] scplayer 

			
			GET_CHAR_COORDINATES imp_cop[cop_i] x y z
			GET_CHAR_HEADING imp_cop[cop_i] cop_heading

			xcomp = player_x - x
			ycomp = player_y - y

			GET_HEADING_FROM_VECTOR_2D XComp YComp imp_Heading

			cop_heading -= imp_heading

			IF cop_heading < -180.0								 
				cop_heading += 360.0
			ENDIF

			ABS cop_heading

			IF player_is_dangerous = 0
				IF cop_heading < 90.0
					GET_CHAR_COORDINATES scplayer last_player_x last_player_y last_player_z
					i_see_you = 1
				ENDIF
	//			i_see_you = 0
			ELSE
				IF cop_heading < 120.0
					GET_CHAR_COORDINATES scplayer last_player_x last_player_y last_player_z
					i_see_you = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF


RETURN

impound_cleanup:

	impound_cops_created = 0
	player_in_impound = 0
	activate_doors = 0

				player_is_threat[0] = 0
				player_is_threat[1] = 0
				player_is_threat[2] = 0
				player_is_threat[3] = 0
				player_is_threat[4] = 0
				player_is_threat[5] = 0
				player_is_threat[6] = 0
				player_is_threat[7] = 0

	start_impound = 0
	request_models = 0

	CLEAR_MISSION_AUDIO 4

	MARK_MODEL_AS_NO_LONGER_NEEDED cop_model
	MARK_MODEL_AS_NO_LONGER_NEEDED car_model
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45

	MARK_MODEL_AS_NO_LONGER_NEEDED police_barrier
	MARK_MODEL_AS_NO_LONGER_NEEDED sfcopdr
	MARK_MODEL_AS_NO_LONGER_NEEDED kmb_shutter

	REMOVE_DECISION_MAKER  im_cop_dec

	DELETE_OBJECT imp_barrier[0]
	DELETE_OBJECT imp_barrier[1]

	DELETE_OBJECT imp_shutter[0]
	DELETE_OBJECT imp_shutter[1]

	imp_i = 0
	WHILE imp_i < 8
		IF DOES_CHAR_EXIST imp_cop[imp_i]
			DELETE_CHAR imp_cop[imp_i]
		ENDIF
		cop_event[imp_i] = 0
		last_cop_task[imp_i] = 0
		imp_i ++
	ENDWHILE

	imp_i = 0
	WHILE imp_i < 4
		IF NOT IS_CAR_DEAD cop_car[imp_i]
			MARK_CAR_AS_NO_LONGER_NEEDED cop_car[imp_i]
		ENDIF
		imp_i ++
	ENDWHILE

	MARK_CHAR_AS_NO_LONGER_NEEDED a_victim

	door_control[0] = 0
	door_control[1] = 0
	door_control[2] = 0
	door_control[3] = 0

	alarm_on = 0

	TERMINATE_THIS_SCRIPT
	


RETURN

MISSION_END
 		  
}

