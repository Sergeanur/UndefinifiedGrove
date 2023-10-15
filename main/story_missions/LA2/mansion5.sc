MISSION_START

// ##########################
// ##
// ##	Mansion5.sc 
// ##
// ##	Cut Throat Business
// ##
// ## 	Simon Lashley
// ##
// ########################## 

SCRIPT_NAME MANSON5

GOSUB mission_mansion5_START

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_mansion5_FAILED
ENDIF

GOSUB mission_mansion5_CLEANUP

MISSION_END

{

//chars
LVAR_INT md_M5 og_M5						   
// blips
LVAR_INT p_blip_M5 md_blip_M5 og_blip_M5 		

// gameplay vehicles
LVAR_INT p_vortex_M5 md_vortex_M5 og_vortex_M5 	
LVAR_INT p_kart_M5 md_kart_M5 og_kart_M5 

// mansion stuff
LVAR_INT man_bullet_M5 man_quad_M5 man_status_M5 

// general
LVAR_INT pointer_M5 sequence_M5 goto_blip_M5 players_group_M5 empty_dm_M5 cutscene_status_M5 mission_passed_M5 
LVAR_FLOAT cam_pos_X_M5 cam_pos_Y_M5 cam_pos_Z_M5 cam_look_X_M5 cam_look_Y_M5 cam_look_Z_M5
LVAR_FLOAT speed_inc_M5

VAR_INT trip_skip_M5 
// fail text
LVAR_INT fail_text_flag_M5
LVAR_TEXT_LABEL	fail_text_M5


mission_mansion5_START:

	REGISTER_MISSION_GIVEN
	flag_player_on_mission = 1

	// text
	LOAD_MISSION_TEXT MAN_5

	SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
	SET_SCRIPT_LIMIT_TO_GANG_SIZE 0
			
// **************************************** START OF CUTSCENE

	SET_AREA_VISIBLE 5

	LOAD_CUTSCENE BHILL5a
	 
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

	SET_AREA_VISIBLE 0

// **************************************** END OF CUTSCENE

	SET_SCRIPT_LIMIT_TO_GANG_SIZE 99
	
	// audio
	START_NEW_SCRIPT start_audio_controller
	
	// weather
	FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_LA

	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY empty_dm_M5

	// special
	LOAD_SPECIAL_CHARACTER 1 MADDOGG
	//REQUEST_MODEL VBMYCR // temp madd dogg
		
	// cars
	REQUEST_MODEL BFINJECT
	//REQUEST_MODEL QUAD

	WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1 
	OR NOT HAS_MODEL_LOADED BFINJECT
	//OR NOT HAS_MODEL_LOADED QUAD
		WAIT 0
	ENDWHILE

	CLEAR_AREA 1252.4712 -805.5187 83.1484 400.0 TRUE

  	LOAD_SCENE 1252.4712 -805.5187 83.1484 
	REQUEST_COLLISION 1252.4712 -805.5187


	// player
	SET_CHAR_COORDINATES scplayer 1252.4712 -805.5187 83.1484
	SET_CHAR_HEADING scplayer 144.1434 
	GET_PLAYER_GROUP player1 players_group_M5
	SET_GROUP_DEFAULT_TASK_ALLOCATOR players_group_M5 DEFAULT_TASK_ALLOCATOR_FOLLOW_ANY_MEANS

	// madd dogg
	CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 1254.3110 -810.8859 83.1484 md_M5
	SET_CHAR_HEADING md_M5 60.7435 
	SET_CHAR_NEVER_LEAVES_GROUP md_M5 TRUE
	SET_GROUP_MEMBER players_group_M5 md_M5
	SET_CHAR_DROWNS_IN_WATER md_M5 TRUE
	SET_CHAR_DECISION_MAKER md_M5 empty_dm_M5
	SET_CHAR_NEVER_TARGETTED md_M5 TRUE
	SET_CHAR_CANT_BE_DRAGGED_OUT md_M5 TRUE

	ADD_BLIP_FOR_CHAR md_M5 md_blip_M5
	SET_BLIP_AS_FRIENDLY md_blip_M5 TRUE 
	CHANGE_BLIP_DISPLAY md_blip_M5 BLIP_ONLY
	

	// mansion vehicles
	CUSTOM_PLATE_FOR_NEXT_CAR BFINJECT LA5H_L3Y
	CUSTOM_PLATE_DESIGN_FOR_NEXT_CAR BFINJECT CARPLATE_DESIGN_LA
	CREATE_CAR BFINJECT 1248.9071 -804.3519 83.1484 man_bullet_M5
	SET_CAR_HEADING man_bullet_M5 180.0
	CHANGE_CAR_COLOUR man_bullet_M5 0 3

//	CREATE_CAR QUAD 1305.8279 -797.5150 83.1477 man_quad_M5
//	SET_CAR_HEADING man_quad_M5 180.0 
	 
	ADD_BLIP_FOR_COORD -12.2064 -1117.8765 7.0705 goto_blip_M5

	// audio stuff
	audio_status_M5 = 0
	//slot_status_M5 = 0

	audio_pointer_M5 = 0
	
	// start audio
	$audio_text_label_M5[0] = &MAN5_AA
	audio_sound_label_M5[0]= SOUND_MAN5_AA
	audio_speaker_M5[0] = scplayer
	$audio_text_label_M5[1] = &MAN5_AB
	audio_sound_label_M5[1]= SOUND_MAN5_AB
	audio_speaker_M5[1] = scplayer
	$audio_text_label_M5[2] = &MAN5_AC
	audio_sound_label_M5[2]= SOUND_MAN5_AC
	audio_speaker_M5[2] = md_M5

	// car banter
	$audio_text_label_M5[3] = &MAN5_BA
	audio_sound_label_M5[3]= SOUND_MAN5_BA
	audio_speaker_M5[3] = md_M5
	$audio_text_label_M5[4] = &MAN5_BB
	audio_sound_label_M5[4]= SOUND_MAN5_BB
	audio_speaker_M5[4] = scplayer
	$audio_text_label_M5[5] = &MAN5_BC
	audio_sound_label_M5[5]= SOUND_MAN5_BC
	audio_speaker_M5[5] = md_M5
	$audio_text_label_M5[6] = &MAN5_BD
	audio_sound_label_M5[6]= SOUND_MAN5_BD
	audio_speaker_M5[6] = scplayer									  
	$audio_text_label_M5[7] = &MAN5_BE
	audio_sound_label_M5[7]= SOUND_MAN5_BE
	audio_speaker_M5[7] = scplayer
	$audio_text_label_M5[8] = &MAN5_BF
	audio_sound_label_M5[8]= SOUND_MAN5_BF
	audio_speaker_M5[8] = scplayer
	$audio_text_label_M5[9] = &MAN5_BG
	audio_sound_label_M5[9]= SOUND_MAN5_BG
	audio_speaker_M5[9] = md_M5
	$audio_text_label_M5[10] = &MAN5_BH
	audio_sound_label_M5[10]= SOUND_MAN5_BH
	audio_speaker_M5[10] = scplayer
	$audio_text_label_M5[11] = &MAN5_BJ
	audio_sound_label_M5[11]= SOUND_MAN5_BJ
	audio_speaker_M5[11] = scplayer
	$audio_text_label_M5[12] = &MAN5_BK
	audio_sound_label_M5[12]= SOUND_MAN5_BK
	audio_speaker_M5[12] = scplayer
	$audio_text_label_M5[13] = &MAN5_BL
	audio_sound_label_M5[13]= SOUND_MAN5_BL
	audio_speaker_M5[13] = scplayer
	$audio_text_label_M5[14] = &MAN5_BM
	audio_sound_label_M5[14]= SOUND_MAN5_BM
	audio_speaker_M5[14] = scplayer
	$audio_text_label_M5[15] = &MAN5_BN
	audio_sound_label_M5[15]= SOUND_MAN5_BN
	audio_speaker_M5[15] = md_M5

	// approach interview
	$audio_text_label_M5[16] = &MAN5_CA
	audio_sound_label_M5[16]= SOUND_MAN5_CA
	audio_speaker_M5[16] = scplayer
	$audio_text_label_M5[17] = &MAN5_CB
	audio_sound_label_M5[17]= SOUND_MAN5_CB
	audio_speaker_M5[17] = scplayer

	// cutscene 
	$audio_text_label_M5[18] = &MAN5_DA
	audio_sound_label_M5[18]= SOUND_MAN5_DA
	audio_speaker_M5[18] = md_M5
	$audio_text_label_M5[19] = &MAN5_DB
	audio_sound_label_M5[19]= SOUND_MAN5_DB
	audio_speaker_M5[19] = md_M5
	$audio_text_label_M5[20] = &MAN5_DC
	audio_sound_label_M5[20]= SOUND_MAN5_DC
	audio_speaker_M5[20] = md_M5
	$audio_text_label_M5[21] = &MAN5_DD
	audio_sound_label_M5[21]= SOUND_MAN5_DD
	audio_speaker_M5[21] = md_M5

	$audio_text_label_M5[22] = &MAN5_EA
	audio_sound_label_M5[22]= SOUND_MAN5_EA
	audio_speaker_M5[22] = scplayer
	$audio_text_label_M5[23] = &MAN5_EB
	audio_sound_label_M5[23]= SOUND_MAN5_EB
	audio_speaker_M5[23] = scplayer

	SET_PLAYER_CONTROL player1 ON
	SWITCH_WIDESCREEN OFF	
	DO_FADE 0 FADE_IN

	
	md_text_status_M5 = 0
	man_status_M5 = 0
	interview_status_M5 = 0

	mission_passed_M5 = 0

	TIMERB = 0
	PRINT_NOW MAN5_G0 7500 1

// ################ player and madd dogg going to marker

// ################ 
LVAR_INT visible_area_M5

mission_mansion5_MAIN_getting_to_og:

	WAIT 0

	IF NOT IS_CHAR_DEAD md_M5
		
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	        GOTO mission_mansion5_PASSED
	    ELSE
	    	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_W
	        	IF IS_CHAR_IN_ANY_CAR scplayer
					GET_CAR_CHAR_IS_USING scplayer p_vehicle_M5
	        		IF IS_CHAR_IN_CAR md_M5 p_vehicle_M5
	        			SET_CAR_COORDINATES p_vehicle_M5 -127.1875 -1260.7383 2.0469
	        		ENDIF 
				ENDIF
			ENDIF
  		ENDIF

		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -12.2064 -1117.8765 60.0 60.0 FALSE
			
			GET_CHAR_COORDINATES md_M5 x y z
			GET_DISTANCE_BETWEEN_COORDS_2D -12.2064 -1117.8765 x y distance
			IF distance < 75.0

 				CLEAR_MISSION_AUDIO 1
								
				MARK_CAR_AS_NO_LONGER_NEEDED man_bullet_M5
				//MARK_CAR_AS_NO_LONGER_NEEDED man_quad_M5

				MARK_MODEL_AS_NO_LONGER_NEEDED BFINJECT 
				MARK_MODEL_AS_NO_LONGER_NEEDED QUAD

 				DO_FADE 0 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				
				SET_PLAYER_CONTROL player1 OFF
				SWITCH_WIDESCREEN ON

				// player
				IF IS_CHAR_IN_ANY_CAR scplayer
					WARP_CHAR_FROM_CAR_TO_COORD scplayer -13.4832 -1139.6417 7.0664
				ELSE
					SET_CHAR_COORDINATES scplayer -13.4832 -1139.6417 7.0664
				ENDIF
				SET_CHAR_HEADING scplayer 347.9809
					
				// md
				IF NOT IS_CHAR_DEAD md_M5
					REMOVE_CHAR_FROM_GROUP md_M5
					IF IS_CHAR_IN_ANY_CAR md_M5
						WARP_CHAR_FROM_CAR_TO_COORD md_M5 -11.4883 -1140.9708 6.8764 
					ELSE
						SET_CHAR_COORDINATES md_M5 -11.4883 -1140.9708 6.8764 
					ENDIF
					SET_CHAR_HEADING md_M5 359.6364
				ENDIF

				GOTO mission_mansion5_CUT_interview
			ELSE
				IF md_text_status_M5 < 2 
					md_text_status_M5 = 2
				ENDIF
				GOSUB mission_mansion5_SUB_md_text	
			ENDIF
		ELSE
			GOSUB mission_mansion5_SUB_md_blip
			GOSUB mission_mansion5_SUB_md_text
			GOSUB mission_mansion5_SUB_man_tidy
			GOSUB mission_mansion5_SUB_interview_setup
			IF TIMERB > 8000
				GOSUB mission_mansion5_AUDIO_dialogue
			ENDIF
		ENDIF

		
		// interior check
		GET_AREA_VISIBLE visible_area_M5  
		IF NOT visible_area_M5 = 0
			CLEAR_PRINTS
			
//			GET_POSITION_OF_ENTRY_EXIT_CHAR_USED scplayer x y z heading
//			GET_CLOSEST_CHAR_NODE x y z ped_node_X_M5 ped_node_Y_M5 ped_node_Z_M5
			
			FREEZE_CHAR_POSITION md_M5 TRUE
			
			REMOVE_BLIP goto_blip_M5
			REMOVE_BLIP md_blip_M5
			REMOVE_CHAR_FROM_GROUP md_M5
			
			GOTO mission_mansion5_MAIN_md_interior 	
		ENDIF

	ELSE
		fail_text_flag_M5 = 1
		$fail_text_M5 = MAN5_F0		
		GOTO mission_mansion5_FAILED
	ENDIF

GOTO mission_mansion5_MAIN_getting_to_og



LVAR_INT md_blip_status_M5 p_vehicle_M5
mission_mansion5_SUB_md_blip:

	SWITCH md_blip_status_M5
		CASE 0
			IF IS_CHAR_IN_ANY_CAR scplayer
				GET_CAR_CHAR_IS_USING scplayer p_vehicle_M5
				IF IS_CHAR_IN_CAR md_M5 p_vehicle_M5
					IF trip_skip_M5 = 1
						SET_UP_SKIP	-126.6673 -1260.8041 2.0469 280.0
					ENDIF

					REMOVE_BLIP md_blip_M5
					md_blip_status_M5 ++ 
				ENDIF
			ENDIF 	 	
		BREAK

		CASE 1
			IF NOT IS_CHAR_IN_ANY_CAR md_M5
				CLEAR_SKIP
				ADD_BLIP_FOR_CHAR md_M5 md_blip_M5
				SET_BLIP_AS_FRIENDLY md_blip_M5 TRUE
				CHANGE_BLIP_DISPLAY md_blip_M5 BLIP_ONLY
				md_blip_status_M5 --
			ENDIF
		BREAK
	ENDSWITCH 

RETURN


LVAR_INT md_text_status_M5
mission_mansion5_SUB_md_text:

	SWITCH md_text_status_M5
	
		CASE 0
			GET_CHAR_COORDINATES scplayer player_x player_y player_z
			GET_CHAR_COORDINATES md_M5 x y z

			GET_DISTANCE_BETWEEN_COORDS_2D player_x player_y x y distance
			IF distance > 75.0
				PRINT_NOW MAN5_G1 8000 1
				TIMERA = 0
				md_text_status_M5 ++
			ENDIF
		BREAK

		CASE 1
			IF TIMERA > 20000
				md_text_status_M5 --
			ENDIF
		BREAK

		CASE 2
			PRINT_NOW MAN5_G2 8000 1
			TIMERA = 0
			md_text_status_M5 ++
		BREAK

		CASE 3
			IF TIMERA > 20000
				md_text_status_M5 = 0
			ENDIF
		BREAK

	ENDSWITCH
	 
RETURN


mission_mansion5_SUB_man_tidy:

	IF man_status_M5 = 0
		GET_CHAR_COORDINATES scplayer player_x player_y player_z
		
		IF NOT IS_CAR_DEAD man_bullet_M5
			GET_CAR_COORDINATES man_bullet_M5 x y z 
			GET_DISTANCE_BETWEEN_COORDS_2D player_x player_y x y distance
			IF distance > 150.0
				MARK_CAR_AS_NO_LONGER_NEEDED man_bullet_M5
				MARK_MODEL_AS_NO_LONGER_NEEDED BFINJECT
				man_status_M5 ++
			ENDIF
		ENDIF

	ENDIF
							 
RETURN

LVAR_INT interview_status_M5
mission_mansion5_SUB_interview_setup:

	SWITCH interview_status_M5
	
		CASE 0
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer -12.2064 -1117.8765 250.0 250.0 FALSE
				
				// og loc
				LOAD_SPECIAL_CHARACTER 2 OGLOC

				REQUEST_MODEL SBFYRI // newsreader? 
				REQUEST_MODEL NEWSVAN
				REQUEST_MODEL VORTEX
				interview_status_M5 ++
			ENDIF
		BREAK

		CASE 1
			IF NOT HAS_SPECIAL_CHARACTER_LOADED 2  
			OR NOT HAS_MODEL_LOADED SBFYRI
			OR NOT HAS_MODEL_LOADED NEWSVAN
			OR NOT HAS_MODEL_LOADED VORTEX
				BREAK
			ELSE
				interview_status_M5 ++
			ENDIF

		CASE 2
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer -12.2064 -1117.8765 150.0 150.0 FALSE

				// og
				CREATE_CHAR PEDTYPE_SPECIAL SPECIAL02 -6.4990 -1109.1108 6.5441 og_M5
				SET_CHAR_HEADING og_M5 333.1959  
				FREEZE_CHAR_POSITION og_M5 TRUE
				SET_CHAR_PROOFS og_M5 TRUE TRUE TRUE TRUE TRUE
				SET_CHAR_DECISION_MAKER og_M5 empty_dm_M5
				SET_CHAR_NEVER_TARGETTED og_M5 TRUE
				SET_CHAR_CANT_BE_DRAGGED_OUT og_M5 TRUE

				// reporter
				CREATE_CHAR PEDTYPE_MISSION1 SBFYRI -7.1740 -1108.2 6.4475 reporter_M5
				SET_CHAR_HEADING reporter_M5 210.4687 
				FREEZE_CHAR_POSITION reporter_M5 TRUE
				SET_CHAR_PROOFS reporter_M5 TRUE TRUE TRUE TRUE TRUE

				// newsvan
				CREATE_CAR NEWSVAN -12.3019 -1108.9266 6.8370 newsvan_M5
				SET_CAR_HEADING newsvan_M5 153.1079  	
				FREEZE_CAR_POSITION newsvan_M5 TRUE 
				CHANGE_CAR_COLOUR newsvan_M5 1 1
				SET_CAR_PROOFS newsvan_M5 TRUE TRUE TRUE TRUE TRUE

				// vortex	
				CREATE_CAR VORTEX -7.4398 -1116.8502 7.0583 p_vortex_M5
				SET_CAR_HEADING p_vortex_M5 278.5895
				FREEZE_CAR_POSITION p_vortex_M5 TRUE
				CHANGE_CAR_COLOUR p_vortex_M5 1 1
				SET_CAR_PROOFS p_vortex_M5 TRUE TRUE TRUE TRUE TRUE
				CREATE_CAR VORTEX -4.8620 -1120.4532 7.0799 md_vortex_M5
				SET_CAR_HEADING md_vortex_M5 266.0038
				FREEZE_CAR_POSITION md_vortex_M5 TRUE
				CHANGE_CAR_COLOUR md_vortex_M5 1 1
				SET_CAR_PROOFS md_vortex_M5 TRUE TRUE TRUE TRUE TRUE
				CREATE_CAR VORTEX -7.0309 -1111.9088 6.7260 og_vortex_M5
				SET_CAR_HEADING og_vortex_M5 269.4002
				FREEZE_CAR_POSITION og_vortex_M5 TRUE
				CHANGE_CAR_COLOUR og_vortex_M5 8 0
				SET_CAR_PROOFS og_vortex_M5 TRUE TRUE TRUE TRUE TRUE

				interview_status_M5 ++
			ENDIF
		BREAK

	ENDSWITCH

RETURN


LVAR_FLOAT ped_node_X_M5 ped_node_Y_M5 ped_node_Z_M5

mission_mansion5_MAIN_md_interior:

	WAIT 0

	IF NOT IS_CHAR_DEAD md_M5	
		GET_CHAR_AREA_VISIBLE scplayer visible_area_M5 
		IF visible_area_M5 = 0
			FREEZE_CHAR_POSITION md_M5 FALSE
			SET_CHAR_NEVER_LEAVES_GROUP md_M5 TRUE
			SET_GROUP_MEMBER players_group_M5 md_M5	
			
			ADD_BLIP_FOR_CHAR md_M5 md_blip_M5
			SET_BLIP_AS_FRIENDLY md_blip_M5 TRUE 
			CHANGE_BLIP_DISPLAY md_blip_M5 BLIP_ONLY

			ADD_BLIP_FOR_COORD -12.2064 -1117.8765 7.0705 goto_blip_M5
			GOTO mission_mansion5_MAIN_getting_to_og
		ENDIF
	ELSE
		// just in for safety reasons
		fail_text_flag_M5 = 1
		$fail_text_M5 = MAN5_F0		
		GOTO mission_mansion5_FAILED
	ENDIF

GOTO mission_mansion5_MAIN_md_interior 









// ################ cutscene of og being interviewed

// ################

LVAR_INT reporter_M5 newsvan_M5
mission_mansion5_CUT_interview:

	// while loops sets up the interview scene incase it wasnt loaded
	WHILE interview_status_M5 < 3
		WAIT 0
		GOSUB mission_mansion5_SUB_interview_setup 
	ENDWHILE

	CLEAR_PRINTS
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	
	DELETE_CAR man_bullet_M5
	
	REQUEST_MODEL MARQUIS
	REQUEST_MODEL REEFER
	REQUEST_MODEL WATERJUMPX2

	REQUEST_CAR_RECORDING 700 // og vortex
	REQUEST_CAR_RECORDING 701 // md vortex

	WHILE NOT HAS_MODEL_LOADED MARQUIS
	OR NOT HAS_MODEL_LOADED REEFER
	OR NOT HAS_MODEL_LOADED WATERJUMPX2
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 700
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 701
		WAIT 0
	ENDWHILE

	// everyone was frozen to stop the player breaking the cutscene
	IF NOT IS_CHAR_DEAD og_M5
		FREEZE_CHAR_POSITION og_M5 FALSE
	ENDIF
	IF NOT IS_CHAR_DEAD reporter_M5
		FREEZE_CHAR_POSITION reporter_M5 FALSE
	ENDIF

	IF NOT IS_CAR_DEAD newsvan_M5
		FREEZE_CAR_POSITION newsvan_M5 FALSE
	ENDIF
	IF NOT IS_CAR_DEAD p_vortex_M5
		FREEZE_CAR_POSITION p_vortex_M5 FALSE
	ENDIF
	IF NOT IS_CAR_DEAD md_vortex_M5
		FREEZE_CAR_POSITION md_vortex_M5 FALSE
	ENDIF
	IF NOT IS_CAR_DEAD og_vortex_M5	
		FREEZE_CAR_POSITION og_vortex_M5 FALSE
	ENDIF

	// trip skip
	IF trip_skip_M5 = 0
		trip_skip_M5 ++
	ENDIF
	
	SET_FIXED_CAMERA_POSITION -5.0753 -1108.2650 7.9567 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -6.0040 -1108.6302 7.8925 JUMP_CUT

	cutscene_status_M5 = 0

	CLEAR_AREA -12.3019 -1108.9266 6.8370 500.0 TRUE

	CLEAR_SKIP

	audio_status_M5 = 6
	audio_pointer_M5 = 18

	DO_FADE 0 FADE_IN


SKIP_CUTSCENE_START

// og just talking
	TIMERA = 0
	WHILE TIMERA < 2000
		WAIT 0
		GOSUB mission_mansion5_AUDIO_dialogue 
	ENDWHILE

    CAMERA_RESET_NEW_SCRIPTABLES
    CAMERA_PERSIST_TRACK TRUE 
    CAMERA_SET_VECTOR_TRACK -6.0040 -1108.6302 7.8925 -5.5642 -1108.8347 7.8925 2000 TRUE

	IF NOT IS_CHAR_DEAD og_M5
	AND NOT IS_CHAR_DEAD md_M5
	AND NOT IS_CHAR_DEAD reporter_M5
		TASK_TURN_CHAR_TO_FACE_CHAR og_M5 md_M5
		TASK_TURN_CHAR_TO_FACE_CHAR reporter_M5 md_M5
	ENDIF

	TIMERA = 0
	WHILE TIMERA < 1800
		WAIT 0
		GOSUB mission_mansion5_AUDIO_dialogue
	ENDWHILE


// getting in the vortex

	// player sequence
	IF NOT IS_CAR_DEAD p_vortex_M5
		OPEN_SEQUENCE_TASK sequence_M5
			TASK_GO_STRAIGHT_TO_COORD -1 -8.0333 -1118.5197 7.0531 PEDMOVE_RUN -2
			TASK_ENTER_CAR_AS_DRIVER -1 p_vortex_M5 -2 
		CLOSE_SEQUENCE_TASK sequence_M5	
		
		PERFORM_SEQUENCE_TASK scplayer sequence_M5
		CLEAR_SEQUENCE_TASK sequence_M5
	ENDIF

	// md sequence
	IF NOT IS_CHAR_DEAD md_M5
	AND NOT IS_CAR_DEAD md_vortex_M5
		OPEN_SEQUENCE_TASK sequence_M5
			TASK_GO_STRAIGHT_TO_COORD -1 -4.9313 -1122.0544 7.0188 PEDMOVE_RUN -2 
			TASK_ENTER_CAR_AS_DRIVER -1 md_vortex_M5 -2 
		CLOSE_SEQUENCE_TASK sequence_M5	
		
		PERFORM_SEQUENCE_TASK md_M5 sequence_M5
		CLEAR_SEQUENCE_TASK sequence_M5
	ENDIF

	TIMERA = 0
	WHILE TIMERA < 250
		WAIT 0
		GOSUB mission_mansion5_AUDIO_dialogue
	ENDWHILE

	// md sequence
	IF NOT IS_CHAR_DEAD og_M5
	AND NOT IS_CAR_DEAD og_vortex_M5
		TASK_ENTER_CAR_AS_DRIVER og_M5 og_vortex_M5 -2 
	ENDIF

	pointer_M5 = 0
	WHILE pointer_M5 = 0
		WAIT 0
		IF NOT IS_CHAR_DEAD og_M5
		AND NOT IS_CAR_DEAD og_vortex_M5	
			IF IS_CHAR_IN_CAR og_M5 og_vortex_M5
				pointer_M5 = 1
				SET_CAR_FORWARD_SPEED og_vortex_M5 8.0
			ENDIF
		ENDIF
		GOSUB mission_mansion5_AUDIO_dialogue
	ENDWHILE
		 
	GET_ACTIVE_CAMERA_COORDINATES cam_pos_X_M5 cam_pos_Y_M5 cam_pos_Z_M5 
	GET_ACTIVE_CAMERA_POINT_AT cam_look_X_M5 cam_look_Y_M5 cam_look_Z_M5
	CAMERA_RESET_NEW_SCRIPTABLES
	SET_FIXED_CAMERA_POSITION cam_pos_X_M5 cam_pos_Y_M5 cam_pos_Z_M5 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT  cam_look_X_M5 cam_look_Y_M5 cam_look_Z_M5 JUMP_CUT

	TIMERA = 0
	TIMERB = 0
	pointer_M5 = 0
	WHILE TIMERA < 4500
		WAIT 0
		cam_pos_X_M5 += 0.0085
		cam_pos_Y_M5 -= 0.05
		cam_look_X_M5 += 0.0085
		cam_look_Y_M5 -= 0.05
		SET_FIXED_CAMERA_POSITION cam_pos_X_M5 cam_pos_Y_M5 cam_pos_Z_M5 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT  cam_look_X_M5 cam_look_Y_M5 cam_look_Z_M5 JUMP_CUT
		IF TIMERB > 800
		AND pointer_M5 = 0
			IF NOT IS_CAR_DEAD og_vortex_M5
				SET_CAR_FORWARD_SPEED og_vortex_M5 0.0
				SET_CAR_COORDINATES og_vortex_M5 -15.1910 -1107.9696 7.0385
				SET_CAR_HEADING og_vortex_M5 345.9100
				pointer_M5 ++
			ENDIF
		ENDIF
		
		GOSUB mission_mansion5_AUDIO_dialogue	
	ENDWHILE

	TIMERA = 0
	WHILE TIMERA < 1800
		WAIT 0
		GOSUB mission_mansion5_AUDIO_dialogue
	ENDWHILE

	cutscene_status_M5 = 1

SKIP_CUTSCENE_END

	// audio
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2

	$audio_text_label_M5[0] = &MAN5_FA
	audio_sound_label_M5[0]= SOUND_MAN5_FA
	$audio_text_label_M5[1] = &MAN5_FB
	audio_sound_label_M5[1]= SOUND_MAN5_FB
	$audio_text_label_M5[2] = &MAN5_FC
	audio_sound_label_M5[2]= SOUND_MAN5_FC
	$audio_text_label_M5[3] = &MAN5_FD
	audio_sound_label_M5[3]= SOUND_MAN5_FD
	$audio_text_label_M5[4] = &MAN5_FE
	audio_sound_label_M5[4]= SOUND_MAN5_FE
	$audio_text_label_M5[5] = &MAN5_FF
	audio_sound_label_M5[5]= SOUND_MAN5_FF
	$audio_text_label_M5[6] = &MAN5_FG
	audio_sound_label_M5[6]= SOUND_MAN5_FG
	$audio_text_label_M5[7] = &MAN5_FH
	audio_sound_label_M5[7]= SOUND_MAN5_FH
	$audio_text_label_M5[8] = &MAN5_FJ
	audio_sound_label_M5[8]= SOUND_MAN5_FJ

	audio_status_M5 = 8
	audio_pointer_M5 = 0

	IF cutscene_status_M5 = 0
		DO_FADE 0 FADE_OUT

		// og
		IF NOT IS_CHAR_DEAD og_M5
		AND NOT IS_CAR_DEAD og_vortex_M5
			IF NOT IS_CHAR_IN_CAR og_M5 og_vortex_M5
				CLEAR_CHAR_TASKS og_M5
				WARP_CHAR_INTO_CAR og_M5 og_vortex_M5
			ENDIF
		ENDIF

		// md
		IF NOT IS_CHAR_DEAD md_M5
		AND NOT IS_CAR_DEAD md_vortex_M5
			IF NOT IS_CHAR_IN_CAR md_M5 md_vortex_M5
				CLEAR_CHAR_TASKS md_M5
				WARP_CHAR_INTO_CAR md_M5 md_vortex_M5
			ENDIF
		ENDIF
		
		// player
		IF NOT IS_CHAR_DEAD scplayer
		AND NOT IS_CAR_DEAD p_vortex_M5
			IF NOT IS_CHAR_IN_CAR scplayer p_vortex_M5
				CLEAR_CHAR_TASKS scplayer
				WARP_CHAR_INTO_CAR scplayer p_vortex_M5
			ENDIF
		ENDIF		
		CAMERA_RESET_NEW_SCRIPTABLES
		DO_FADE 0 FADE_IN
	ENDIF
	
	PRINT_NOW MAN5_G4 8000 1
	
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER

	IF NOT IS_CHAR_DEAD reporter_M5
		FREEZE_CHAR_POSITION reporter_M5 FALSE
		SET_CHAR_PROOFS reporter_M5 FALSE FALSE FALSE FALSE FALSE
		MARK_CHAR_AS_NO_LONGER_NEEDED reporter_M5
	ENDIF
	IF NOT IS_CAR_DEAD newsvan_M5
		FREEZE_CAR_POSITION newsvan_M5 FALSE
		SET_CAR_PROOFS newsvan_M5 FALSE FALSE FALSE FALSE FALSE
		MARK_CAR_AS_NO_LONGER_NEEDED newsvan_M5
	ENDIF

	MARK_MODEL_AS_NO_LONGER_NEEDED SBFYRI
	MARK_MODEL_AS_NO_LONGER_NEEDED NEWSVAN	 

	IF NOT IS_CAR_DEAD og_vortex_M5 
	AND NOT IS_CAR_DEAD md_vortex_M5

		SET_CAR_PROOFS og_vortex_M5 FALSE FALSE FALSE TRUE FALSE
		SET_CAR_PROOFS md_vortex_M5 FALSE FALSE FALSE TRUE FALSE

		SYNC_WATER
		START_PLAYBACK_RECORDED_CAR og_vortex_M5 700	
		START_PLAYBACK_RECORDED_CAR md_vortex_M5 701

		REMOVE_BLIP goto_blip_M5
		REMOVE_BLIP md_blip_M5
		ADD_BLIP_FOR_CAR og_vortex_M5 og_blip_M5
		ADD_BLIP_FOR_CAR md_vortex_M5 md_blip_M5
		SET_BLIP_AS_FRIENDLY md_blip_M5 TRUE
	ENDIF

	IF NOT IS_CHAR_DEAD og_M5
	AND NOT IS_CHAR_DEAD md_M5
	AND NOT IS_CAR_DEAD p_vortex_M5
		SET_CHAR_PROOFS og_M5 FALSE FALSE FALSE TRUE FALSE
		SET_CHAR_PROOFS md_M5 FALSE FALSE FALSE TRUE FALSE
		SET_CAR_PROOFS p_vortex_M5 FALSE FALSE FALSE FALSE FALSE		
	ENDIF

LVAR_INT boats_M5[8] waterjump_M5


	CREATE_CAR MARQUIS 64.6248 -1577.0203 1.0 boats_M5[0]
	SET_CAR_HEADING boats_M5[0] 120.3362
	ANCHOR_BOAT boats_M5[0] TRUE 

	CREATE_CAR MARQUIS 33.6099 -1568.4618 1.0 boats_M5[1]
	SET_CAR_HEADING boats_M5[1] 284.2679
	ANCHOR_BOAT boats_M5[1] TRUE 

	CREATE_CAR REEFER 44.5020 -1623.9836 1.0 boats_M5[2]
	SET_CAR_HEADING boats_M5[2] 75.0
	ANCHOR_BOAT boats_M5[2] TRUE

	CREATE_CAR MARQUIS 27.4441 -1654.8044 1.0 boats_M5[3]
	SET_CAR_HEADING boats_M5[3] 60.0
	ANCHOR_BOAT boats_M5[3] TRUE

	CREATE_CAR MARQUIS 65.1404 -1664.8699 1.0 boats_M5[4]
	SET_CAR_HEADING boats_M5[4] 95.0
	ANCHOR_BOAT boats_M5[4] TRUE  

	CREATE_CAR REEFER 53.4007 -1694.9565 1.0 boats_M5[5]
	SET_CAR_HEADING boats_M5[5] 231.9938
	ANCHOR_BOAT boats_M5[5] TRUE

	CREATE_CAR REEFER 33.4799 -1697.4623 1.0 boats_M5[6]
	SET_CAR_HEADING boats_M5[6] 106.8489
	ANCHOR_BOAT boats_M5[6] TRUE 
	 
	CREATE_CAR MARQUIS 57.1376 -1767.3013 1.0 boats_M5[7]
	SET_CAR_HEADING boats_M5[7] 286.5185 
	ANCHOR_BOAT boats_M5[7] TRUE 

	CREATE_OBJECT_NO_OFFSET WATERJUMPX2 137.9184 -1895.0603 0.9 waterjump_M5 
	SET_OBJECT_HEADING waterjump_M5 235.0

	// recording stuff
	md_status_M5 = 0
	og_status_M5 = 0

	TIMERA = 0

mission_mansion5_MAIN_vortex_chase:

	WAIT 0

	IF NOT IS_CHAR_DEAD md_M5

		IF NOT IS_CHAR_DEAD og_M5
	
			GET_CHAR_COORDINATES scplayer player_x player_y player_z
			GOSUB mission_mansion5_SUB_md_vortex_speed
			GOSUB mission_mansion5_SUB_og_vortex_speed

			GET_CHAR_COORDINATES og_M5 x y z
			GET_DISTANCE_BETWEEN_COORDS_2D x y player_x player_y distance
			IF distance >= 240.0
				fail_text_flag_M5 = 1
				$fail_text_M5 = MAN5_F2		
				GOTO mission_mansion5_FAILED
			ENDIF

			// check if players on the pier
			IF IS_CHAR_IN_AREA_3D scplayer 820.3243 -1918.4197 11.8624 852.5646 -1894.7251 13.0 FALSE
				GOTO mission_mansion5_CUT_on_pier
			ENDIF

			GOSUB mission_mansion5_AUDIO_dialogue
		ELSE
			fail_text_flag_M5 = 1
			$fail_text_M5 = MAN5_F3 		
			GOTO mission_mansion5_FAILED
		ENDIF

	ELSE
		fail_text_flag_M5 = 1
		$fail_text_M5 = MAN5_F0		
		GOTO mission_mansion5_FAILED
	ENDIF


GOTO mission_mansion5_MAIN_vortex_chase








// ################ cutscene on pier

// ################

mission_mansion5_CUT_on_pier:

	DO_FADE 0 FADE_OUT
	 
	SET_RADIO_CHANNEL RS_OFF
	
	SET_PED_DENSITY_MULTIPLIER 0.3

	// player
	IF NOT IS_CAR_DEAD p_vortex_M5
		SET_CAR_HEALTH p_vortex_M5 2000
		SET_CAR_FORWARD_SPEED p_vortex_M5 0.0
		SET_CAR_COORDINATES p_vortex_M5 824.9831 -1843.1456 11.6654
		SET_CAR_HEADING p_vortex_M5 192.0245
	ENDIF

	// md
	IF NOT IS_CAR_DEAD md_vortex_M5
		IF IS_PLAYBACK_GOING_ON_FOR_CAR md_vortex_M5
			STOP_PLAYBACK_RECORDED_CAR md_vortex_M5
		ENDIF
		SET_CAR_FORWARD_SPEED md_vortex_M5 0.0
		SET_CAR_COORDINATES md_vortex_M5 846.0862 -1842.4211 11.6367
		SET_CAR_HEADING md_vortex_M5 170.9990
		REMOVE_BLIP md_blip_M5
	ENDIF							  

	// og
	IF NOT IS_CAR_DEAD og_vortex_M5
		IF IS_PLAYBACK_GOING_ON_FOR_CAR og_vortex_M5
			STOP_PLAYBACK_RECORDED_CAR og_vortex_M5
		ENDIF
		SET_CAR_FORWARD_SPEED og_vortex_M5 0.0
		SET_CAR_COORDINATES og_vortex_M5 832.5709 -2045.7067 11.8672
		SET_CAR_HEADING og_vortex_M5 180.0

		REMOVE_BLIP og_blip_M5
	ENDIF

	MARK_CHAR_AS_NO_LONGER_NEEDED reporter_M5

	pointer_M5 = 0
	WHILE pointer_M5 < 8
		MARK_CAR_AS_NO_LONGER_NEEDED boats_M5[pointer_M5]
		pointer_M5 ++
	ENDWHILE
	MARK_OBJECT_AS_NO_LONGER_NEEDED waterjump_M5

	MARK_MODEL_AS_NO_LONGER_NEEDED MARQUIS 
	MARK_MODEL_AS_NO_LONGER_NEEDED REEFER
	MARK_MODEL_AS_NO_LONGER_NEEDED WATERJUMPX2

	REMOVE_CAR_RECORDING 700
	REMOVE_CAR_RECORDING 701

	REQUEST_MODEL KART

	REQUEST_CAR_RECORDING 702 
	REQUEST_CAR_RECORDING 703
	REQUEST_CAR_RECORDING 704 
	REQUEST_CAR_RECORDING 705

	WHILE NOT HAS_MODEL_LOADED KART
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 702
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 703
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 704
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 705
		WAIT 0
	ENDWHILE

	CREATE_CAR KART 832.4061 -2046.0251 11.8672 p_kart_M5
	SET_CAR_HEADING p_kart_M5 343.6256

	CREATE_CAR KART 835.5082 -2047.9781 11.8672 md_kart_M5
	SET_CAR_HEADING md_kart_M5 0.0

	CREATE_CAR KART 839.2866 -2050.3223 11.8672 og_kart_M5
	SET_CAR_HEADING og_kart_M5 21.8433
	FREEZE_CAR_POSITION og_kart_M5 TRUE

	//SET_FIXED_CAMERA_POSITION 839.6265 -2056.3994 12.9973 0.0 0.0 0.0
	//POINT_CAMERA_AT_POINT 839.5886 -2055.4185 12.8065 JUMP_CUT

	cam_angle_M5 = 15.0
	cam_radius_M5 = 10.0
	cam_Z_M5 = 15.0
				   
	cam_L_X_M5 = 835.5082     
	cam_L_Y_M5 = -2047.9781
	cam_L_Z_M5 = 11.8672

	GOSUB mission_mansion5_SUB_rotate_camera
	 
	IF NOT IS_CAR_DEAD og_vortex_M5 
		IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR og_vortex_M5
			START_PLAYBACK_RECORDED_CAR og_vortex_M5 702		
		ENDIF 
	ENDIF

	SWITCH_WIDESCREEN ON
	SET_PLAYER_CONTROL player1 OFF

	DO_FADE 0 FADE_IN

	TIMERA = 0
	WHILE TIMERA < 1000
		WAIT 0
		GOSUB mission_mansion5_SUB_rotate_camera
	ENDWHILE
	
	cutscene_status_M5 = 0

SKIP_CUTSCENE_START

	TIMERA = 0
	WHILE TIMERA < 3400
		WAIT 0
		GOSUB mission_mansion5_SUB_rotate_camera
	ENDWHILE

//	SET_FIXED_CAMERA_POSITION 840.8270 -2048.5452 12.7166 0.0 0.0 0.0
//	POINT_CAMERA_AT_POINT 840.1081 -2049.2051 12.4981 JUMP_CUT		

	// player
	IF NOT IS_CAR_DEAD p_vortex_M5
		SET_CAR_COORDINATES p_vortex_M5 827.2987 -2036.8510 11.8672 
		SET_CAR_HEADING p_vortex_M5 176.4310 
	ENDIF
 
	IF IS_CHAR_IN_ANY_CAR scplayer
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 828.7999 -2036.7567 11.8672 
	ELSE
		SET_CHAR_COORDINATES scplayer 828.7999 -2036.7567 11.8672
	ENDIF
	SET_CHAR_HEADING scplayer 129.8786 

	// md
	IF NOT IS_CAR_DEAD md_vortex_M5
		SET_CAR_COORDINATES md_vortex_M5 846.0646 -2039.7368 11.8672  
 		SET_CAR_HEADING md_vortex_M5 162.7406
	ENDIF

	IF NOT IS_CHAR_DEAD md_M5
		IF IS_CHAR_IN_ANY_CAR md_M5
			WARP_CHAR_FROM_CAR_TO_COORD md_M5 844.8756 -2038.4553 11.8672 
		ELSE
			SET_CHAR_COORDINATES md_M5 844.8756 -2038.4553 11.8672
		ENDIF
		SET_CHAR_HEADING md_M5 132.7607
	ENDIF 

	pointer_M5 = 0
	WHILE pointer_M5 = 0
		WAIT 0
		IF NOT IS_CAR_DEAD og_vortex_M5 
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR og_vortex_M5
				pointer_M5 = 1		
			ENDIF 
		ENDIF
		GOSUB mission_mansion5_SUB_rotate_camera
	ENDWHILE

 	//SET_FIXED_CAMERA_POSITION 831.6954 -2041.4028 13.5385 0.0 0.0 0.0
	//POINT_CAMERA_AT_POINT 832.1078 -2042.2263 13.1490 JUMP_CUT	

	IF NOT IS_CHAR_DEAD og_M5
	AND NOT IS_CAR_DEAD og_kart_M5
		OPEN_SEQUENCE_TASK sequence_M5
			TASK_LEAVE_ANY_CAR -1
			TASK_ENTER_CAR_AS_DRIVER -1 og_kart_M5 -2   
		CLOSE_SEQUENCE_TASK sequence_M5	
		
		PERFORM_SEQUENCE_TASK og_M5 sequence_M5
		CLEAR_SEQUENCE_TASK sequence_M5		
	ENDIF

	pointer_M5 = 0
	WHILE pointer_M5 = 0
		WAIT 0
		IF NOT IS_CHAR_DEAD og_M5
		AND NOT IS_CAR_DEAD og_kart_M5 
			IF IS_CHAR_IN_CAR og_M5 og_kart_M5
				pointer_M5 = 1		
			ENDIF 
		ENDIF
		GOSUB mission_mansion5_SUB_rotate_camera
	ENDWHILE

	IF NOT IS_CAR_DEAD og_kart_M5 
		IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR og_kart_M5
			START_PLAYBACK_RECORDED_CAR og_kart_M5 703		
		ENDIF 
	ENDIF
		
	IF NOT IS_CAR_DEAD p_kart_M5 
		TASK_ENTER_CAR_AS_DRIVER scplayer p_kart_M5 -2 
	ENDIF

	IF NOT IS_CHAR_DEAD md_M5
	AND NOT IS_CAR_DEAD md_kart_M5
		TASK_ENTER_CAR_AS_DRIVER md_M5 md_kart_M5 -2 
	ENDIF

	TIMERA = 0
	WHILE TIMERA < 4500
		WAIT 0
		GOSUB mission_mansion5_SUB_rotate_camera
	ENDWHILE
	
	cutscene_status_M5 = 1

SKIP_CUTSCENE_END

	IF cutscene_status_M5 = 0
		DO_FADE 0 FADE_OUT
	ENDIF

	// player
	IF NOT IS_CAR_DEAD p_kart_M5
		IF NOT IS_CHAR_IN_CAR scplayer p_kart_M5
			IF IS_CHAR_IN_ANY_CAR scplayer
				WARP_CHAR_FROM_CAR_TO_COORD scplayer 0.0 0.0 0.0
			ENDIF
			WARP_CHAR_INTO_CAR scplayer p_kart_M5
		ENDIF
	ENDIF

	IF NOT IS_CAR_DEAD p_vortex_M5
		SET_CAR_COORDINATES p_vortex_M5 827.2987 -2036.8510 11.8672 
		SET_CAR_HEADING p_vortex_M5 176.4310 
	ENDIF

	// md
	IF NOT IS_CHAR_DEAD md_M5
	AND NOT IS_CAR_DEAD md_kart_M5
		IF NOT IS_CHAR_IN_CAR md_M5 md_kart_M5
			IF IS_CHAR_IN_ANY_CAR md_M5
				WARP_CHAR_FROM_CAR_TO_COORD md_M5 0.0 0.0 0.0
			ENDIF
			WARP_CHAR_INTO_CAR md_M5 md_kart_M5
		ENDIF
	ENDIF
	
	IF NOT IS_CAR_DEAD md_vortex_M5
		SET_CAR_COORDINATES md_vortex_M5 846.0646 -2039.7368 11.8672  
 		SET_CAR_HEADING md_vortex_M5 162.7406
	ENDIF

	// og
	IF NOT IS_CHAR_DEAD og_M5
	AND NOT IS_CAR_DEAD og_kart_M5
		FREEZE_CAR_POSITION og_kart_M5 FALSE
		IF NOT IS_CHAR_IN_CAR og_M5 og_kart_M5
			IF IS_CHAR_IN_ANY_CAR og_M5
				WARP_CHAR_FROM_CAR_TO_COORD og_M5 0.0 0.0 0.0
			ENDIF
			WARP_CHAR_INTO_CAR og_M5 og_kart_M5
		ENDIF
	ENDIF

	IF NOT IS_CAR_DEAD og_vortex_M5
		IF IS_PLAYBACK_GOING_ON_FOR_CAR og_vortex_M5
			SKIP_TO_END_AND_STOP_PLAYBACK_RECORDED_CAR og_vortex_M5 
		ENDIF
	ENDIF

	md_status_M5 = 0
	og_status_M5 = 0
	og_rec_total_M5 = 0.0

	ambient_status_M5 = 0
	van_status_M5 = 0


	IF NOT IS_CAR_DEAD og_kart_M5 
		IF IS_PLAYBACK_GOING_ON_FOR_CAR og_kart_M5
			STOP_PLAYBACK_RECORDED_CAR og_kart_M5
		ENDIF
		
		START_PLAYBACK_RECORDED_CAR og_kart_M5 704 
		ADD_BLIP_FOR_CAR og_kart_M5 og_blip_M5 
	ENDIF
	IF NOT IS_CAR_DEAD md_kart_M5 
		IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR md_kart_M5
			START_PLAYBACK_RECORDED_CAR md_kart_M5 705	 		 
		ENDIF
		ADD_BLIP_FOR_CAR md_kart_M5 md_blip_M5
		SET_BLIP_AS_FRIENDLY md_blip_M5 TRUE 
	ENDIF

	audio_status_M5 = 10
	audio_pointer_M5 = 4

	DO_FADE 0 FADE_IN

	SET_PLAYER_CONTROL player1 ON
	SWITCH_WIDESCREEN OFF

	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER

	PRINT_NOW MAN5_G5 8000 1

	TIMERA = 0

mission_mansion5_MAIN_kart_chase:

	WAIT 0

	IF NOT IS_CHAR_DEAD md_M5

		IF NOT IS_CHAR_DEAD og_M5
	
			GET_CHAR_COORDINATES scplayer player_x player_y player_z
			GOSUB mission_mansion5_SUB_md_kart_speed
			GOSUB mission_mansion5_SUB_og_kart_speed

			GOSUB mission_mansion5_SUB_ambient_controller
			GOSUB mission_mansion5_SUB_guard_vans

			IF mission_passed_M5 = 1
				GOTO mission_mansion5_MAIN_final_cut
			ELSE
				IF NOT IS_CHAR_DEAD og_M5
					GET_CHAR_COORDINATES og_M5 x y z
					GET_DISTANCE_BETWEEN_COORDS_2D x y player_x player_y distance
					IF distance >= 130.0
						fail_text_flag_M5 = 1
						$fail_text_M5 = MAN5_F2	
						GOTO mission_mansion5_FAILED
					ENDIF
				ENDIF
			ENDIF

			GOSUB mission_mansion5_AUDIO_dialogue
		ELSE
			fail_text_flag_M5 = 1
			$fail_text_M5 = MAN5_F3		
			GOTO mission_mansion5_FAILED
		ENDIF
	ELSE
		fail_text_flag_M5 = 1
		$fail_text_M5 = MAN5_F0		
		GOTO mission_mansion5_FAILED
	ENDIF


GOTO mission_mansion5_MAIN_kart_chase



LVAR_INT players_vehicle_M5
mission_mansion5_MAIN_final_cut:

	DO_FADE 0 FADE_OUT

	SET_RADIO_CHANNEL RS_OFF

	REMOVE_BLIP md_blip_M5
	REMOVE_BLIP og_blip_M5
	
	IF IS_CHAR_IN_ANY_CAR scplayer
		GET_CAR_CHAR_IS_USING scplayer players_vehicle_M5
		SET_CAR_FORWARD_SPEED players_vehicle_M5 0.0
	ENDIF
	
	IF NOT IS_CHAR_DEAD md_M5
		MARK_CHAR_AS_NO_LONGER_NEEDED md_M5
		MARK_MODEL_AS_NO_LONGER_NEEDED VBMYCR
	ENDIF
	
	IF NOT IS_CAR_DEAD md_kart_M5
		IF IS_PLAYBACK_GOING_ON_FOR_CAR md_kart_M5
			STOP_PLAYBACK_RECORDED_CAR md_kart_M5
		ENDIF
		MARK_CAR_AS_NO_LONGER_NEEDED md_kart_M5
	ENDIF
		
 	SET_FIXED_CAMERA_POSITION 1045.3710 -1221.8405 18.9362 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 1046.2407 -1221.4474 18.6378 JUMP_CUT
	
	CLEAR_AREA_OF_CARS 1071.5037 -1195.5579 18.6451 1044.9419 -1242.4774 14.9051 

	IF NOT IS_CAR_DEAD og_kart_M5
		IF IS_PLAYBACK_GOING_ON_FOR_CAR og_kart_M5
			UNPAUSE_PLAYBACK_RECORDED_CAR og_kart_M5
			SET_PLAYBACK_SPEED og_kart_M5 1.0
		ENDIF	
	ENDIF	

	SWITCH_WIDESCREEN ON
	SET_PLAYER_CONTROL player1 OFF
	
	DO_FADE 500 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

SKIP_CUTSCENE_START

	pointer_M5 = 0
	WHILE pointer_M5 = 0
		WAIT 0
		IF NOT IS_CAR_DEAD og_kart_M5
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR og_kart_M5
				pointer_M5 = 1
			ENDIF
		ELSE
			pointer_M5 = 1
		ENDIF
	ENDWHILE

SKIP_CUTSCENE_END

	DO_FADE 0 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

// **************************************** START OF OUTRO CUTSCENE

	SET_AREA_VISIBLE 3

	MAKE_PLAYER_SAFE_FOR_CUTSCENE player1

	SWITCH_STREAMING OFF

	LOAD_CUTSCENE BHILL5b
	 
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

	SET_AREA_VISIBLE 0

// **************************************** END OF OUTRO CUTSCENE	

	LOAD_SCENE 1180.7324 -1159.1143 22.8578
	REQUEST_COLLISION 1180.7324 -1159.1143
	 
	IF IS_CHAR_IN_ANY_CAR scplayer
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 1180.7324 -1159.1143 22.8578
	ELSE
		SET_CHAR_COORDINATES_NO_OFFSET scplayer 1180.7324 -1159.1143 22.8578
	ENDIF
	SET_CHAR_HEADING scplayer 25.4307

	TIMERA = 0
	WHILE TIMERA < 400
		WAIT 0
	ENDWHILE

	DO_FADE 500 FADE_IN

	SET_PLAYER_CONTROL player1 ON
	SWITCH_WIDESCREEN OFF

	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER

	GOTO mission_mansion5_PASSED







												  



// ################ car recording controllers

// ################

LVAR_FLOAT X1_M5 Y1_M5 X2_M5 Y2_M5 X3_M5 Y3_M5 distance1_M5 distance2_M5 distance3_M5 og_rec_total_M5

LVAR_INT md_status_M5
LVAR_FLOAT md_rec_speed_M5




mission_mansion5_SUB_md_vortex_speed:

//	IF NOT IS_CAR_DEAD md_vortex_M5	
//
//		GET_CAR_COORDINATES md_vortex_M5 x y z
//
//		GET_DISTANCE_BETWEEN_COORDS_2D player_x player_y x y distance2_M5
//		  
//		IF distance2_M5 < 4.0
//			speed_inc_M5 = -0.17
//		ELSE 
//			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS md_vortex_M5 0.0 8.0 0.0 X1_M5 Y1_M5 z
//			GET_DISTANCE_BETWEEN_COORDS_2D player_x player_y X1_M5 Y1_M5 distance1_M5
//			speed_inc_M5 = distance2_M5 - distance1_M5
//			speed_inc_M5 /= 60.0
//		ENDIF
//
//		md_rec_speed_M5 += speed_inc_M5
//
//		IF md_rec_speed_M5 < 0.0
//			md_rec_speed_M5 = 0.0
//		ELSE
//			IF md_rec_speed_M5 > 1.0
//				md_rec_speed_M5 = 1.0
//			ENDIF
//		ENDIF
//
//		SET_PLAYBACK_SPEED md_vortex_M5 md_rec_speed_M5
//		
//	ENDIF

	IF NOT IS_CAR_DEAD md_vortex_M5
	AND NOT IS_CHAR_DEAD md_M5
		
		IF md_status_M5 = 0
	
			IF IS_PLAYBACK_GOING_ON_FOR_CAR md_vortex_M5 
			
				IF LOCATE_CHAR_IN_CAR_CHAR_2D md_M5 scplayer 6.0 6.0 FALSE
					md_rec_speed_M5 -= 0.2	
				ELSE
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS md_vortex_M5 0.0 4.0 0.0 X1_M5 Y1_M5 z

					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS md_vortex_M5 2.0 2.0 0.0 X2_M5 Y2_M5 z
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS md_vortex_M5 -2.0 2.0 0.0 X3_M5 Y3_M5 z

					GET_DISTANCE_BETWEEN_COORDS_2D X1_M5 Y1_M5 player_x player_y distance1_M5
					distance1_M5 *= 2.0
					 
					GET_DISTANCE_BETWEEN_COORDS_2D X2_M5 Y2_M5 player_x player_y distance2_M5
					GET_DISTANCE_BETWEEN_COORDS_2D X3_M5 Y3_M5 player_x player_y distance3_M5

					speed_inc_M5 = distance1_M5 - distance2_M5
					speed_inc_M5 -= distance3_M5
					speed_inc_M5 /= 300.0
					speed_inc_M5 *= -1.0

					md_rec_speed_M5 += speed_inc_M5
				ENDIF

				IF md_rec_speed_M5 > 1.0
					md_rec_speed_M5 = 1.0
				ELSE
					IF md_rec_speed_M5 < 0.0
						md_rec_speed_M5 = 0.0
					ENDIF
				ENDIF

				//VIEW_FLOAT_VARIABLE md_rec_speed_M5 md_vortex_speed_M5
				SET_PLAYBACK_SPEED md_vortex_M5 md_rec_speed_M5 

			ELSE
				SET_CAR_FORWARD_SPEED md_vortex_M5 0.0
				md_status_M5 = -1 // finished recording	
			ENDIF
		ENDIF

	ENDIF

RETURN


LVAR_INT og_status_M5
LVAR_FLOAT og_rec_speed_M5
mission_mansion5_SUB_og_vortex_speed:

	IF NOT IS_CAR_DEAD og_vortex_M5
		
		IF og_status_M5 = 0
	
			IF IS_PLAYBACK_GOING_ON_FOR_CAR og_vortex_M5

				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS og_vortex_M5 0.0 -18.0 0.0 X1_M5 Y1_M5 z

				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS og_vortex_M5 2.0 -11.0 0.0 X2_M5 Y2_M5 z
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS og_vortex_M5 -2.0 -11.0 0.0 X3_M5 Y3_M5 z

				GET_DISTANCE_BETWEEN_COORDS_2D X1_M5 Y1_M5 player_x player_y distance1_M5
				distance1_M5 *= 2.0
				 
				GET_DISTANCE_BETWEEN_COORDS_2D X2_M5 Y2_M5 player_x player_y distance2_M5
				GET_DISTANCE_BETWEEN_COORDS_2D X3_M5 Y3_M5 player_x player_y distance3_M5

				speed_inc_M5 = distance1_M5 - distance2_M5
				speed_inc_M5 -= distance3_M5
				speed_inc_M5 /= 300.0

				og_rec_speed_M5 += speed_inc_M5

				IF og_rec_speed_M5 > 1.4
					og_rec_speed_M5 = 1.4
				ELSE
					IF og_rec_speed_M5 < 0.9
						og_rec_speed_M5 = 0.9
					ENDIF
				ENDIF

				//VIEW_FLOAT_VARIABLE og_rec_speed_M5 og_vortex_speed_M5
				SET_PLAYBACK_SPEED og_vortex_M5 og_rec_speed_M5
				
			ELSE
				SET_CAR_FORWARD_SPEED og_vortex_M5 0.0
				og_status_M5 = -1 // finished recording	
			ENDIF
		ENDIF
 
	ENDIF

RETURN


// KARTS

mission_mansion5_SUB_md_kart_speed:


//	IF NOT IS_CAR_DEAD md_kart_M5	
//
//		GET_CAR_COORDINATES md_kart_M5 x y z
//
//		GET_DISTANCE_BETWEEN_COORDS_2D player_x player_y x y distance2_M5
//		  
//		IF distance2_M5 < 4.0
//			speed_inc_M5 = -0.15
//		ELSE 
//			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS md_kart_M5 0.0 8.0 0.0 X1_M5 Y1_M5 z
//			GET_DISTANCE_BETWEEN_COORDS_2D player_x player_y X1_M5 Y1_M5 distance1_M5
//			speed_inc_M5 = distance2_M5 - distance1_M5
//			speed_inc_M5 /= 60.0
//		ENDIF
//
//		md_rec_speed_M5 += speed_inc_M5
//
//		IF md_rec_speed_M5 < 0.0
//			md_rec_speed_M5 = 0.0
//		ELSE
//			IF md_rec_speed_M5 > 1.0
//				md_rec_speed_M5 = 1.0
//			ENDIF
//		ENDIF
//
//		SET_PLAYBACK_SPEED md_kart_M5 md_rec_speed_M5
//		
//	ENDIF
//


	IF NOT IS_CAR_DEAD md_kart_M5
	AND NOT IS_CHAR_DEAD md_M5
		
		IF md_status_M5 = 0
	
			IF IS_PLAYBACK_GOING_ON_FOR_CAR md_kart_M5 
			
				IF LOCATE_CHAR_IN_CAR_CHAR_2D md_M5 scplayer 6.0 6.0 FALSE
					md_rec_speed_M5 -= 0.2	
				ELSE
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS md_kart_M5 0.0 4.0 0.0 X1_M5 Y1_M5 z

					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS md_kart_M5 2.0 2.0 0.0 X2_M5 Y2_M5 z
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS md_kart_M5 -2.0 2.0 0.0 X3_M5 Y3_M5 z

					GET_DISTANCE_BETWEEN_COORDS_2D X1_M5 Y1_M5 player_x player_y distance1_M5
					distance1_M5 *= 2.0
					 
					GET_DISTANCE_BETWEEN_COORDS_2D X2_M5 Y2_M5 player_x player_y distance2_M5
					GET_DISTANCE_BETWEEN_COORDS_2D X3_M5 Y3_M5 player_x player_y distance3_M5

					speed_inc_M5 = distance1_M5 - distance2_M5
					speed_inc_M5 -= distance3_M5
					speed_inc_M5 /= 150.0
					speed_inc_M5 *= -1.0

					md_rec_speed_M5 += speed_inc_M5
				ENDIF

				IF md_rec_speed_M5 > 1.05
					md_rec_speed_M5 = 1.05
				ELSE
					IF md_rec_speed_M5 < 0.0
						md_rec_speed_M5 = 0.0
					ENDIF
				ENDIF

				//VIEW_FLOAT_VARIABLE md_rec_speed_M5 md_kart_speed_M5
				SET_PLAYBACK_SPEED md_kart_M5 md_rec_speed_M5 

			ELSE
				SET_CAR_FORWARD_SPEED md_kart_M5 0.0
				md_status_M5 = -1 // finished recording	
			ENDIF
		ENDIF

	ENDIF

RETURN


mission_mansion5_SUB_og_kart_speed:

	IF NOT IS_CAR_DEAD og_kart_M5
		
		IF og_status_M5 = 0
	
			IF IS_PLAYBACK_GOING_ON_FOR_CAR og_kart_M5

				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS og_kart_M5 0.0 -18.0 0.0 X1_M5 Y1_M5 z

				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS og_kart_M5 2.0 -11.0 0.0 X2_M5 Y2_M5 z
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS og_kart_M5 -2.0 -11.0 0.0 X3_M5 Y3_M5 z

				GET_DISTANCE_BETWEEN_COORDS_2D X1_M5 Y1_M5 player_x player_y distance1_M5
				distance1_M5 *= 2.0
				 
				GET_DISTANCE_BETWEEN_COORDS_2D X2_M5 Y2_M5 player_x player_y distance2_M5
				GET_DISTANCE_BETWEEN_COORDS_2D X3_M5 Y3_M5 player_x player_y distance3_M5

				speed_inc_M5 = distance1_M5 - distance2_M5
				speed_inc_M5 -= distance3_M5
				speed_inc_M5 /= 300.0

				og_rec_speed_M5 += speed_inc_M5

				IF og_rec_speed_M5 > 1.2
					og_rec_speed_M5 = 1.2
				ELSE
					IF og_rec_speed_M5 < 0.6
						og_rec_speed_M5 = 0.6
					ENDIF
				ENDIF

				SET_PLAYBACK_SPEED og_kart_M5 og_rec_speed_M5
				
			ELSE
				SET_CAR_FORWARD_SPEED og_kart_M5 0.0
				og_kart_M5 = -1 // finished recording	
			ENDIF
		ENDIF
 
	ENDIF

RETURN


LVAR_INT camera_status_M5
LVAR_FLOAT cam_X_M5	cam_Y_M5 cam_Z_M5 cam_angle_M5 cam_radius_M5
LVAR_FLOAT cam_L_X_M5 cam_L_Y_M5 cam_L_Z_M5
LVAR_FLOAT new_x_M5 new_y_M5
LVAR_FLOAT cos_angle_M5 sin_angle_M5

mission_mansion5_SUB_rotate_camera:
    
	SWITCH camera_status_M5
		CASE 0
			IF cam_angle_M5 < 255.0
				cam_angle_M5 += 0.35
//				IF cam_angle_M5 >= 360.0
//					cam_angle_M5 -= 360.0
//				ENDIF 
				//cam_Z_M5 -= 0.006
				
				//cam_L_Y_M5 += 0.01
				//cam_radius_M5 += 0.01
			ENDIF		
		BREAK
	ENDSWITCH	
	
	
	COS cam_angle_M5 cos_angle_M5
	SIN cam_angle_M5 sin_angle_M5

	new_x_M5 = cam_radius_M5 * cos_angle_M5 
	new_y_M5 = cam_radius_M5 * sin_angle_M5

	cam_X_M5 = cam_L_X_M5 + new_x_M5
	cam_Y_M5 = cam_L_Y_M5 + new_y_M5

	SET_FIXED_CAMERA_POSITION cam_X_M5 cam_Y_M5 cam_Z_M5 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT cam_L_X_M5 cam_L_Y_M5 cam_L_Z_M5 JUMP_CUT	

RETURN



  
// ################ ambient kart stuff

// ################

LVAR_INT ambient_status_M5
LVAR_INT a_runner_M5 a_hotdog_M5

mission_mansion5_SUB_ambient_controller:

	IF NOT IS_CHAR_DEAD og_M5
		
		SWITCH ambient_status_M5

			CASE 0
				IF LOCATE_CHAR_IN_CAR_2D og_M5 748.4916 -1812.3613 2.0 2.0 FALSE
					IF NOT IS_LINE_OF_SIGHT_CLEAR player_x player_y player_z 748.4916 -1812.3613 12.0297 TRUE FALSE FALSE FALSE FALSE
						// 
					ENDIF

					REQUEST_MODEL HOTDOG

					ambient_status_M5 ++
				ENDIF
			BREAK

			CASE 1
				IF LOCATE_CHAR_IN_CAR_2D og_M5 662.4983 -1791.8445 2.0 2.0 FALSE
					
					IF HAS_MODEL_LOADED HOTDOG
						IF NOT IS_LINE_OF_SIGHT_CLEAR player_x player_y player_z 532.9616 -1768.6559 4.6980 TRUE FALSE FALSE FALSE FALSE
							CREATE_CAR HOTDOG 532.9616 -1768.6559 4.6980 a_hotdog_M5
							SET_CAR_HEADING a_hotdog_M5 270.0		
						ENDIF
					ENDIF
						
					//MARK_CHAR_AS_NO_LONGER_NEEDED a_runner_M5 // ped that runs across path

					SWITCH_ROADS_OFF 226.1833 -1631.0071 40.0 546.4532 -1423.9912 12.0 
					SWITCH_ROADS_OFF 510.3661 -1431.6556 10.0 645.8312 -1387.3116 20.0
					SWITCH_ROADS_OFF 646.6142 -1389.0164 10.0 620.9656 -1310.4675 20.0
					SWITCH_ROADS_OFF 620.9656 -1310.4675 10.0 947.8110 -1335.0125 20.0
					SWITCH_ROADS_OFF 948.2631 -1335.4061 10.0 934.2357 -1229.9386 20.0
					SWITCH_ROADS_OFF 804.0421 -1329.1908 10.0 789.3583 -1159.0081 24.0

					ambient_status_M5 ++
				ENDIF
			BREAK

			CASE 2
				IF LOCATE_CHAR_IN_CAR_2D og_M5 394.1295 -1801.1277 2.0 2.0 FALSE
					MARK_MODEL_AS_NO_LONGER_NEEDED HOTDOG
					MARK_CAR_AS_NO_LONGER_NEEDED a_hotdog_M5
					
					
					ambient_status_M5 ++
				ENDIF
			BREAK

			CASE 3
				IF LOCATE_CHAR_IN_CAR_2D og_M5 1030.7583 -1223.5798 2.0 2.0 FALSE
					PAUSE_PLAYBACK_RECORDED_CAR og_kart_M5
					// mission passed
					ambient_status_M5 ++
				ELSE
					BREAK
				ENDIF

			CASE 4
				IF NOT IS_CHAR_DEAD og_M5
					GET_CHAR_COORDINATES og_M5 x y z
					GET_DISTANCE_BETWEEN_COORDS_2D x y player_x player_y distance
					IF distance <= 40.0
						mission_passed_M5 = 1	
					ENDIF
				ENDIF
			BREAK				
		ENDSWITCH

	ENDIF


RETURN



LVAR_INT van_M5[2] van_status_M5
mission_mansion5_SUB_guard_vans:

	SWITCH van_status_M5

		CASE 0
			GET_DISTANCE_BETWEEN_COORDS_2D player_x player_y 1064.5472 -1218.0834 distance 
			IF distance < 250.0
				REQUEST_MODEL BURRITO
				van_status_M5 ++
			ENDIF
		BREAK

		CASE 1
			IF HAS_MODEL_LOADED BURRITO
				GET_DISTANCE_BETWEEN_COORDS_2D player_x player_y 1064.5472 -1218.0834 distance 
				IF distance < 80.0
					CREATE_CAR BURRITO 1064.5472 -1218.0834 15.9280 van_M5[0]
					SET_CAR_HEADING van_M5[0] 191.9481
					CREATE_CAR BURRITO 1050.5997 -1222.6919 15.8704 van_M5[1]
					SET_CAR_HEADING van_M5[1] 111.6362
					van_status_M5 ++
				ENDIF
			ENDIF
		BREAK

	ENDSWITCH

RETURN


// ################ audio dialogue

// ################
LVAR_INT audio_status_M5 audio_pointer_M5 audio_sound_label_M5[24] audio_speaker_M5[24]
LVAR_TEXT_LABEL audio_text_label_M5[24]

LVAR_INT soundlabel_M5
LVAR_TEXT_LABEL textlabel_M5
  
mission_mansion5_AUDIO_dialogue:

//	VIEW_INTEGER_VARIABLE SLOT_status[0] SLOT_status[0]
//	VIEW_INTEGER_VARIABLE SLOT_status[1] SLOT_status[1]

	SWITCH audio_status_M5 

		// start dialogue
		CASE 0
			IF audio_pointer_M5 < 3
				load_sample = audio_sound_label_M5[audio_pointer_M5]
				$load_text = $audio_text_label_M5[audio_pointer_M5]
				START_NEW_SCRIPT audio_load_and_play 2 100 audio_speaker_M5[audio_pointer_M5]
				audio_pointer_M5 ++
				audio_status_M5 ++
				//TIMERA = 0
			ELSE
				audio_status_M5 = 2
				//SLOT_status[preload_slot] = -3
				BREAK
			ENDIF
		CASE 1
			IF SLOT_status[preload_slot] = -3
				audio_status_M5 = 0
				SLOT_status[preload_slot] = -4
			ENDIF
		BREAK	 

		// in car dialogue
		CASE 2
			IF interview_status_M5 = 3
				IF LOCATE_CHAR_ANY_MEANS_2D scplayer -12.2064 -1117.8765 120.0 120.0 FALSE
					audio_status_M5 += 2
					audio_pointer_M5 = 16
					BREAK
				ENDIF
 			ENDIF
			
			IF md_blip_status_M5 = 1 
				IF audio_pointer_M5 < 16
					load_sample = audio_sound_label_M5[audio_pointer_M5]
					$load_text = $audio_text_label_M5[audio_pointer_M5]
					START_NEW_SCRIPT audio_load_and_play 2 100 audio_speaker_M5[audio_pointer_M5]
					audio_pointer_M5 ++
					audio_status_M5 ++
					//TIMERA = 0
				ENDIF
			ELSE
				IF NOT audio_pointer_M5 = 3 
					IF NOT SLOT_status[0] < 0
						SLOT_status[0] = -2
					ENDIF
					IF NOT SLOT_status[0] < 0
						SLOT_status[1] = -2
					ENDIF
					//audio_pointer_M5 --
				ENDIF
				BREAK 
			ENDIF
		CASE 3
			IF SLOT_status[preload_slot] = -3
				audio_status_M5 = 2
				SLOT_status[preload_slot] = -4
			ENDIF
		BREAK
					
		// in car dialogue
		CASE 4			
			IF audio_pointer_M5 < 18
				load_sample = audio_sound_label_M5[audio_pointer_M5]
				$load_text = $audio_text_label_M5[audio_pointer_M5]
				START_NEW_SCRIPT audio_load_and_play 2 100 audio_speaker_M5[audio_pointer_M5]
				audio_pointer_M5 ++
				audio_status_M5 ++
				//TIMERA = 0
			ENDIF
		CASE 5
			IF SLOT_status[preload_slot] = -3
				audio_status_M5 = 4
				SLOT_status[preload_slot] = -4
			ENDIF
		BREAK

		// cutscene dialogue
		CASE 6			
			IF audio_pointer_M5 < 24
				load_sample = audio_sound_label_M5[audio_pointer_M5]
				$load_text = $audio_text_label_M5[audio_pointer_M5]
				START_NEW_SCRIPT audio_load_and_play 2 100 audio_speaker_M5[audio_pointer_M5] 
				audio_pointer_M5 ++
				audio_status_M5 ++
				//TIMERA = 0
			ENDIF
		CASE 7
			IF SLOT_status[preload_slot] = -3
				audio_status_M5 = 6
				SLOT_status[preload_slot] = -4
			ENDIF
		BREAK

		// hovercraft
		CASE 8			
			IF audio_pointer_M5 < 4
				IF TIMERA > 8000
					IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer og_M5 60.0 60.0 FALSE
						load_sample = audio_sound_label_M5[audio_pointer_M5]
						$load_text = $audio_text_label_M5[audio_pointer_M5]
						START_NEW_SCRIPT audio_load_and_play 2 100 og_M5 
						audio_pointer_M5 ++
						audio_status_M5 ++
						TIMERA = 0
					ELSE
						TIMERA = 7000
					ENDIF
				ENDIF
			ENDIF
		CASE 9
			IF SLOT_status[preload_slot] = -3
				audio_status_M5 = 8
				SLOT_status[preload_slot] = -4
			ENDIF
		BREAK

		// go karts
		CASE 10			
			IF audio_pointer_M5 < 9
				IF TIMERA > 8000
					IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer og_M5 50.0 50.0 FALSE
						load_sample = audio_sound_label_M5[audio_pointer_M5]
						$load_text = $audio_text_label_M5[audio_pointer_M5]
						START_NEW_SCRIPT audio_load_and_play 2 100 og_M5 
						audio_pointer_M5 ++
						audio_status_M5 ++
						TIMERA = 0
					ELSE
						TIMERA = 7000
					ENDIF
				ENDIF
			ENDIF
		CASE 11
			IF SLOT_status[preload_slot] = -3
				audio_status_M5 = 10
				SLOT_status[preload_slot] = -4
			ENDIF
		BREAK

	ENDSWITCH

RETURN

  
// ###########################
// ##
// ## END OF MISSION SCRIPTS
// ##
// ###########################


mission_mansion5_PASSED:
	
	flag_mansion_mission_counter ++ //Used to terminate this mission loop in the main script. These varibles will be set up in the main.sc
	REGISTER_MISSION_PASSED ( MAN_5 ) //Used in the stats 
	PLAYER_MADE_PROGRESS 1

	// go kart and hovercraft generators
	SWITCH_CAR_GENERATOR car_gen_go_kart[0]   101
	SWITCH_CAR_GENERATOR car_gen_go_kart[1]   101
	SWITCH_CAR_GENERATOR car_gen_go_kart[2]   101
	SWITCH_CAR_GENERATOR car_gen_go_kart[3]   101
	SWITCH_CAR_GENERATOR car_gen_go_kart[4]   101
	SWITCH_CAR_GENERATOR car_gen_go_kart[5]   101
	SWITCH_CAR_GENERATOR car_gen_go_kart[6]   101
	SWITCH_CAR_GENERATOR car_gen_go_kart[7]   101
	SWITCH_CAR_GENERATOR car_gen_go_kart[8]   101
	SWITCH_CAR_GENERATOR car_gen_go_kart[9]   101
	SWITCH_CAR_GENERATOR car_gen_go_kart[10]  101
	SWITCH_CAR_GENERATOR car_gen_go_kart[11]  101
	SWITCH_CAR_GENERATOR car_gen_go_kart[12]  101
	SWITCH_CAR_GENERATOR car_gen_go_kart[13]  101
	SWITCH_CAR_GENERATOR car_gen_go_kart[14]  101

	SWITCH_CAR_GENERATOR car_gen_hover[0]     101
	SWITCH_CAR_GENERATOR car_gen_hover[1]     101
	SWITCH_CAR_GENERATOR car_gen_hover[2]     101
	SWITCH_CAR_GENERATOR car_gen_hover[3]     101
	SWITCH_CAR_GENERATOR car_gen_hover[4]     101
	SWITCH_CAR_GENERATOR car_gen_hover[5]     101
	
	// imy stuff
	PRINT_WITH_NUMBER_BIG ( M_PASSR ) 4 5000 1 //"Mission Passed!"
	AWARD_PLAYER_MISSION_RESPECT 50 //amount of respect
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1

	// stuff for craig
	REMOVE_BLIP mansion_contact_blip
RETURN



mission_mansion5_FAILED:
	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
	IF fail_text_flag_M5 = 1
		PRINT_NOW $fail_text_M5 5000 1
	ENDIF
RETURN



mission_mansion5_CLEANUP:

	IF IS_PLAYER_PLAYING player1
		SET_PLAYER_GROUP_RECRUITMENT player1 TRUE
		SET_SCRIPT_LIMIT_TO_GANG_SIZE 99
	ENDIF

	START_NEW_SCRIPT terminate_audio_controller

	SET_PED_DENSITY_MULTIPLIER 1.0

	SWITCH_ROADS_BACK_TO_ORIGINAL 226.1833 -1631.0071 40.0 546.4532 -1423.9912 12.0 
	SWITCH_ROADS_BACK_TO_ORIGINAL 510.3661 -1431.6556 10.0 645.8312 -1387.3116 20.0
	SWITCH_ROADS_BACK_TO_ORIGINAL 646.6142 -1389.0164 10.0 620.9656 -1310.4675 20.0
	SWITCH_ROADS_BACK_TO_ORIGINAL 620.9656 -1310.4675 10.0 947.8110 -1335.0125 20.0
	SWITCH_ROADS_BACK_TO_ORIGINAL 948.2631 -1335.4061 10.0 934.2357 -1229.9386 20.0
	SWITCH_ROADS_BACK_TO_ORIGINAL 804.0421 -1329.1908 10.0 789.3583 -1159.0081 24.0
	
	REMOVE_BLIP goto_blip_M5
	REMOVE_BLIP md_blip_M5
	REMOVE_BLIP og_blip_M5

	IF NOT IS_CHAR_DEAD md_M5 
		REMOVE_CHAR_FROM_GROUP md_M5
	ENDIF
	
	
	REMOVE_CHAR_ELEGANTLY md_M5
	REMOVE_CHAR_ELEGANTLY og_M5	
	
	UNLOAD_SPECIAL_CHARACTER 1 // madd
	UNLOAD_SPECIAL_CHARACTER 2 // og

	MARK_CHAR_AS_NO_LONGER_NEEDED reporter_M5
	IF NOT IS_CHAR_DEAD reporter_M5
		FREEZE_CHAR_POSITION reporter_M5 FALSE
		SET_CHAR_PROOFS reporter_M5 FALSE FALSE FALSE FALSE FALSE
	ENDIF

	MARK_CAR_AS_NO_LONGER_NEEDED newsvan_M5
	IF NOT IS_CAR_DEAD newsvan_M5
		FREEZE_CAR_POSITION newsvan_M5 FALSE
		SET_CAR_PROOFS newsvan_M5 FALSE FALSE FALSE FALSE FALSE
	ENDIF

	MARK_CAR_AS_NO_LONGER_NEEDED p_vortex_M5

	IF NOT IS_CAR_DEAD md_vortex_M5
		IF IS_PLAYBACK_GOING_ON_FOR_CAR md_vortex_M5
			STOP_PLAYBACK_RECORDED_CAR md_vortex_M5
		ENDIF
	ENDIF
	MARK_CAR_AS_NO_LONGER_NEEDED md_vortex_M5

	IF NOT IS_CAR_DEAD og_vortex_M5
		IF IS_PLAYBACK_GOING_ON_FOR_CAR og_vortex_M5
			STOP_PLAYBACK_RECORDED_CAR og_vortex_M5
		ENDIF
	ENDIF
	MARK_CAR_AS_NO_LONGER_NEEDED og_vortex_M5

	MARK_CAR_AS_NO_LONGER_NEEDED p_kart_M5

	IF NOT IS_CAR_DEAD md_kart_M5
		IF IS_PLAYBACK_GOING_ON_FOR_CAR md_kart_M5
			STOP_PLAYBACK_RECORDED_CAR md_kart_M5
		ENDIF
	ENDIF
	MARK_CAR_AS_NO_LONGER_NEEDED md_kart_M5

	IF NOT IS_CAR_DEAD og_kart_M5
		IF IS_PLAYBACK_GOING_ON_FOR_CAR og_kart_M5
			STOP_PLAYBACK_RECORDED_CAR og_kart_M5
		ENDIF
	ENDIF
	MARK_CAR_AS_NO_LONGER_NEEDED og_kart_M5

	MARK_CAR_AS_NO_LONGER_NEEDED man_bullet_M5
	//MARK_CAR_AS_NO_LONGER_NEEDED man_quad_M5
	MARK_CAR_AS_NO_LONGER_NEEDED a_hotdog_M5
	MARK_CAR_AS_NO_LONGER_NEEDED van_M5[0]
	MARK_CAR_AS_NO_LONGER_NEEDED van_M5[1]

	pointer_M5 = 0
	WHILE pointer_M5 < 8
		MARK_CAR_AS_NO_LONGER_NEEDED boats_M5[pointer_M5]
		pointer_M5 ++
	ENDWHILE

	MARK_OBJECT_AS_NO_LONGER_NEEDED waterjump_M5
	
	MARK_MODEL_AS_NO_LONGER_NEEDED SBFYRI
	//MARK_MODEL_AS_NO_LONGER_NEEDED VBMYCR

	MARK_MODEL_AS_NO_LONGER_NEEDED BFINJECT
	//MARK_MODEL_AS_NO_LONGER_NEEDED QUAD
	MARK_MODEL_AS_NO_LONGER_NEEDED VORTEX
	MARK_MODEL_AS_NO_LONGER_NEEDED KART
	MARK_MODEL_AS_NO_LONGER_NEEDED NEWSVAN
	MARK_MODEL_AS_NO_LONGER_NEEDED MARQUIS
	MARK_MODEL_AS_NO_LONGER_NEEDED REEFER
	MARK_MODEL_AS_NO_LONGER_NEEDED HOTDOG
	MARK_MODEL_AS_NO_LONGER_NEEDED BURRITO

	MARK_MODEL_AS_NO_LONGER_NEEDED WATERJUMPX2
				
	flag_player_on_mission = 0
			
	MISSION_HAS_FINISHED
RETURN

}

/*
*/
												