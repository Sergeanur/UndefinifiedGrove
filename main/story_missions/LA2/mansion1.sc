MISSION_START

// ########################
// ##
// ##	Mansion1.sc 
// ##
// ##	Home in the hills
// ##
// ## 	Simon Lashley
// ##
// ######################## 

SCRIPT_NAME mansio1

GOSUB mission_mansion1_START

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_mansion1_FAILED
ENDIF

GOSUB mission_mansion1_CLEANUP

MISSION_END


{ 
// decision makers
LVAR_INT empty_dm_M1 movement_dm_M1 cover_dm_M1 ganggirl_dm_M1 bar_dm_M1 players_group_dm_M1

// timers
LVAR_INT this_frame_time_M1 last_frame_time_M1 time_diff_M1

// general
LVAR_INT pointer_M1 players_group_M1 sequence_M1 sequence_progress_M1 goto_blip_M1 current_area_M1 cutscene_flag_M1 task_status_M1 health_pickup_M1 

// fail text
LVAR_INT fail_text_flag_M1
LVAR_TEXT_LABEL fail_text_M1

// audio stuff
LVAR_INT audio_sound_label_M1[10] audio_speaker_M1[10] audio_pointer_M1
LVAR_TEXT_LABEL audio_text_label_M1[10] 



mission_mansion1_START:

	REGISTER_MISSION_GIVEN
	flag_player_on_mission = 1

	SET_CHAR_COORDINATES scplayer 2175.4116 1681.5483 9.8203
	SET_CHAR_HEADING scplayer 90.0 
	SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2233.5 1712.8 10.0

	LOAD_MISSION_TEXT MAN_1
	
	SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
	SET_SCRIPT_LIMIT_TO_GANG_SIZE 0

	iTerminateAllAmbience = 1	

// **************************************** START OF INTRO CUTSCENE

	SET_AREA_VISIBLE 11

	LOAD_CUTSCENE BHILL1
	 
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

// **************************************** END OF INTRO CUTSCENE	
					 
	DISPLAY_ZONE_NAMES FALSE
	
	SET_SCRIPT_LIMIT_TO_GANG_SIZE 99

LVAR_INT cutscene_plane_M1
LVAR_FLOAT plane_y_M1

	// audio
	START_NEW_SCRIPT start_audio_controller

	LOAD_SCENE_IN_DIRECTION 2171.1375 1269.3567 32.8078	0.0

	REQUEST_MODEL ANDROM
	WHILE NOT HAS_MODEL_LOADED ANDROM
		WAIT 0
	ENDWHILE

	SET_CHAR_AREA_VISIBLE scplayer 0

	CREATE_CAR ANDROM 2144.0 1600.0 50.0 cutscene_plane_M1
	SET_CAR_HEADING cutscene_plane_M1 180.0
	SET_RADIO_CHANNEL RS_OFF 
	
   	SET_PLANE_UNDERCARRIAGE_UP cutscene_plane_M1 TRUE
	FREEZE_CAR_POSITION cutscene_plane_M1 TRUE
	SET_VEHICLE_AREA_VISIBLE cutscene_plane_M1 0

	WARP_CHAR_INTO_CAR scplayer cutscene_plane_M1

	cutscene_flag_M1 = 0

	FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT

	SET_TIME_OF_DAY 06 00
	SET_FIXED_CAMERA_POSITION 2171.1375 1269.3567 32.8078 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 2170.9983 1270.3312 32.9838 JUMP_CUT	

	SET_RESPAWN_POINT_FOR_DURATION_OF_MISSION 1581.3737 1768.9958 9.8203
	
	SWITCH_WIDESCREEN ON
	
	SET_HEATHAZE_EFFECT TRUE

	TIMERA = 0
	WHILE TIMERA < 1000
		WAIT 0
	ENDWHILE

SKIP_CUTSCENE_START

	plane_y_M1 = 1600.0
	DO_FADE 1000 FADE_IN
	WHILE GET_FADING_STATUS
		IF NOT IS_CAR_DEAD cutscene_plane_M1
			plane_y_M1 -= 1.5
			SET_CAR_COORDINATES cutscene_plane_M1 2144.0 plane_y_M1 50.0
		ENDIF
		WAIT 0
	ENDWHILE
	
	TIMERA = 0
	WHILE TIMERA < 9000
		IF NOT IS_CAR_DEAD cutscene_plane_M1
			plane_y_M1 -= 1.5
			SET_CAR_COORDINATES cutscene_plane_M1 2144.0 plane_y_M1 50.0
		ENDIF
		WAIT 0
	ENDWHILE

	cutscene_flag_M1 = 1

SKIP_CUTSCENE_END
	
	DO_FADE 1000 FADE_OUT  
	WHILE GET_FADING_STATUS
		IF NOT IS_CAR_DEAD cutscene_plane_M1
			plane_y_M1 -= 1.5
			SET_CAR_COORDINATES cutscene_plane_M1 2144.0 plane_y_M1 50.0
		ENDIF
		WAIT 0
	ENDWHILE

   	SET_HEATHAZE_EFFECT FALSE	
	
	IF IS_CHAR_IN_ANY_CAR scplayer
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 1598.9507 -785.3270 350.1654
	ELSE
		SET_CHAR_COORDINATES scplayer 1598.9507 -785.3270 350.1654 
	ENDIF

	FREEZE_CHAR_POSITION scplayer TRUE

	MARK_MODEL_AS_NO_LONGER_NEEDED ANDROM
	MARK_CAR_AS_NO_LONGER_NEEDED cutscene_plane_M1
	
	//triads 
	REQUEST_MODEL TRIADA
	REQUEST_MODEL TRIADB
	
	// lsv 
	REQUEST_MODEL LSV1
	REQUEST_MODEL LSV2

	// weapons
	REQUEST_MODEL M4		// triad
	REQUEST_MODEL MP5LNG	// lsv

	// objects - plane interior
	REQUEST_MODEL CARGO_REAR 
	REQUEST_MODEL D9_RAMP
	REQUEST_MODEL CELLPHONE

	// parachute stuff - requested to make parachute script run quicker
	REQUEST_MODEL PARACHUTE
	REQUEST_MODEL PARA_PACK
	REQUEST_ANIMATION PARACHUTE
	STREAM_SCRIPT parachute.sc

	WHILE NOT HAS_MODEL_LOADED TRIADA
	OR NOT HAS_MODEL_LOADED TRIADB																							     
	OR NOT HAS_MODEL_LOADED LSV1
	OR NOT HAS_MODEL_LOADED LSV2
	OR NOT HAS_MODEL_LOADED M4
	OR NOT HAS_MODEL_LOADED MP5LNG
		WAIT 0
	ENDWHILE

	WHILE NOT HAS_MODEL_LOADED CARGO_REAR
	OR NOT HAS_MODEL_LOADED D9_RAMP
	OR NOT HAS_MODEL_LOADED CELLPHONE
	OR NOT HAS_MODEL_LOADED PARACHUTE
	OR NOT HAS_MODEL_LOADED PARA_PACK
	OR NOT HAS_ANIMATION_LOADED PARACHUTE
		WAIT 0
	ENDWHILE

	WHILE NOT HAS_STREAMED_SCRIPT_LOADED parachute.sc
		WAIT 0
	ENDWHILE

	
	fail_text_flag_M1 = 0

	// default decision makers
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY empty_dm_M1

	/*
	custom decision makers(all based on the empty decision maker - and all used inside the mansion);

		movement_dm_M1 used by the traids when moving
		- will make them stop moving and shoot at peds if they are damaged or spot someone they hate

		cover_dm_M1 used by triads when in cover mode
		- will make triads respond to a few more events than the movement dm

	*/

	// triad custom decision makers
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY movement_dm_M1
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE movement_dm_M1 EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT_STAND_STILL 0.0 100.0 0.0 0.0 FALSE TRUE
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE movement_dm_M1 EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_INFORM_RESPECTED_FRIENDS 0.0 100.0 0.0 0.0 FALSE TRUE
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE movement_dm_M1 EVENT_DAMAGE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 FALSE TRUE

   	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY cover_dm_M1
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE cover_dm_M1 EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT_STAND_STILL 0.0 100.0 0.0 0.0 FALSE TRUE
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE cover_dm_M1 EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_INFORM_RESPECTED_FRIENDS 0.0 100.0 0.0 0.0 FALSE TRUE
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE cover_dm_M1 EVENT_DAMAGE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 FALSE TRUE
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE cover_dm_M1 EVENT_SHOT_FIRED TASK_SIMPLE_LOOK_AT_ENTITY_OR_COORD 0.0 100.0 0.0 0.0 FALSE TRUE
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE cover_dm_M1 EVENT_SEEN_PANICKED_PED TASK_SIMPLE_LOOK_AT_ENTITY_OR_COORD 10.0 10.0 10.0 10.0 FALSE TRUE
   

	// gang girl decision maker
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY ganggirl_dm_M1
	// triad & player stuff
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE ganggirl_dm_M1 EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_FLEE_ANY_MEANS 0.0 100.0 0.0 0.0 FALSE TRUE
	// lsv
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE ganggirl_dm_M1 EVENT_ACQUAINTANCE_PED_DISLIKE TASK_COMPLEX_FLEE_ANY_MEANS 0.0 100.0 0.0 0.0 FALSE TRUE
	// both
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE ganggirl_dm_M1 EVENT_DAMAGE TASK_COMPLEX_SMART_FLEE_ENTITY 0.0 100.0 0.0 0.0 FALSE TRUE   	
	
	LOAD_GROUP_DECISION_MAKER DM_GROUP_MISSION_NORM players_group_dm_M1
	CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE players_group_dm_M1 EVENT_DAMAGE
	ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE players_group_dm_M1 EVENT_DAMAGE TASK_GROUP_KILL_THREATS_BASIC 0.0 100.0 0.0 0.0 FALSE TRUE


	/*
		RELATIONSHIPS
	*/
	// triads
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1 PEDTYPE_MISSION1 
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_MISSION2
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1 PEDTYPE_PLAYER1

	// los santos vargos
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 PEDTYPE_MISSION1
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 PEDTYPE_PLAYER1

	// gang girls
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3 PEDTYPE_PLAYER1 		// player 
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3 PEDTYPE_MISSION1 		// triads
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_MISSION3 PEDTYPE_MISSION2 	// lsv
	
	// players group
	GET_PLAYER_GROUP player1 players_group_M1
	SET_GROUP_DEFAULT_TASK_ALLOCATOR players_group_M1 DEFAULT_TASK_ALLOCATOR_FOLLOW_ANY_MEANS
	SET_GROUP_DECISION_MAKER players_group_M1 players_group_dm_M1



// FOOL COMPILER
LVAR_INT never_run_this_M1
never_run_this_M1 = 0

IF never_run_this_M1 = 1
 	// chars
 	CREATE_CHAR PEDTYPE_CIVMALE TRIADA 0.0 0.0 0.0 triad_w3_M1[other_triad_M1]
	CREATE_CHAR PEDTYPE_CIVMALE TRIADA 0.0 0.0 0.0 lsv_guard_M1
	CREATE_CHAR PEDTYPE_CIVMALE TRIADA 0.0 0.0 0.0 lsv_outside_M1[pointer_M1]
	CREATE_CHAR PEDTYPE_CIVMALE TRIADA 0.0 0.0 0.0 lsv_room_M1[pointer_M1]
	CREATE_CHAR PEDTYPE_CIVMALE TRIADA 0.0 0.0 0.0 lsv_spawn_M1[pointer_M1]
	CREATE_CHAR PEDTYPE_CIVMALE TRIADA 0.0 0.0 0.0 lsv_girl_M1[pointer_M1]
	// objects
	CREATE_OBJECT CARDBOARDBOX2 0.0 0.0 0.0 lsv_jump_box_M1[pointer_M1]
	//blips
	ADD_BLIP_FOR_CHAR triad_w3_M1[pointer_M1] triad_w3_blip_M1[pointer_M1]
ENDIF	



	SWITCH_AMBIENT_PLANES FALSE
	SET_POLICE_IGNORE_PLAYER player1 TRUE


// ##########################################################################################################
// ##
// ## OUTSIDE MANSION
// ##
// ##########################################################################################################



LVAR_INT triad_w1_M1[4] triad_w1_status_M1[4] triad_w1_cut_status_M1[2]

	FORCE_WEATHER_NOW WEATHER_SUNNY_LA
	SET_TIME_OF_DAY 03 00

// ##############################################
// ##
// ## PLAYER ON THE PHONE
// ##
// ##############################################

	CREATE_OBJECT_NO_OFFSET CARGO_REAR 1595.0 -785.0 350.0 cargo_plane_M1
	SET_OBJECT_HEADING cargo_plane_M1 90.0
	FREEZE_OBJECT_POSITION cargo_plane_M1 TRUE

	CREATE_OBJECT_NO_OFFSET D9_RAMP 1595.03 -784.98 348.758 cargo_ramp_M1
	SET_OBJECT_HEADING cargo_ramp_M1 90.0									   
	FREEZE_OBJECT_POSITION cargo_ramp_M1 TRUE
						
	FREEZE_CHAR_POSITION scplayer FALSE
	SET_CHAR_COORDINATES scplayer 1594.3934 -784.7317 349.0 
	SET_CHAR_HEADING scplayer 36.9506

	// triad 0 
	CREATE_CHAR PEDTYPE_MISSION1 TRIADA 1595.3031 -783.4741 349.0 triad_w2_M1[0]
	SET_CHAR_HEADING triad_w2_M1[0] 205.0
	SET_CHAR_AREA_VISIBLE triad_w2_M1[0] 0
	GIVE_WEAPON_TO_CHAR triad_w2_M1[0] WEAPONTYPE_M4 30000
	SET_CURRENT_CHAR_WEAPON triad_w2_M1[0] WEAPONTYPE_M4
	SET_CHAR_NEVER_TARGETTED triad_w2_M1[0] TRUE
	SET_CHAR_DECISION_MAKER triad_w2_M1[0] empty_dm_M1
	SET_CHAR_SIGNAL_AFTER_KILL triad_w2_M1[0] FALSE

	CREATE_OBJECT para_pack 1595.3031 -783.4741 349.0 fake_parachute_M1[0]
	TASK_PICK_UP_OBJECT triad_w2_M1[0] fake_parachute_M1[0] -0.11 -0.05 0.0 PED_TORSO HOLD_ORIENTATE_BONE_FULL NULL NULL 0

	SET_CHAR_PROOFS triad_w2_M1[0] TRUE FALSE FALSE FALSE FALSE

	triad_w2_wait_M1[0] = 0
	triad_w2_status_M1[0] = 0

	// triad 1	
	CREATE_CHAR PEDTYPE_MISSION1 TRIADB 1596.9702 -786.8875 349.0 triad_w2_M1[1]
	SET_CHAR_HEADING triad_w2_M1[1] 2.0 
	SET_CHAR_AREA_VISIBLE triad_w2_M1[1] 0	
	GIVE_WEAPON_TO_CHAR triad_w2_M1[1] WEAPONTYPE_M4 30000
	SET_CURRENT_CHAR_WEAPON triad_w2_M1[1] WEAPONTYPE_M4
	SET_CHAR_NEVER_TARGETTED triad_w2_M1[1] TRUE
	SET_CHAR_DECISION_MAKER triad_w2_M1[1] empty_dm_M1
	SET_CHAR_SIGNAL_AFTER_KILL triad_w2_M1[1] FALSE

	CREATE_OBJECT para_pack 1596.9702 -786.8875 349.0 fake_parachute_M1[1]
	TASK_PICK_UP_OBJECT triad_w2_M1[1] fake_parachute_M1[1] -0.11 -0.05 0.0 PED_TORSO HOLD_ORIENTATE_BONE_FULL NULL NULL 0

	SET_CHAR_PROOFS triad_w2_M1[1] TRUE FALSE FALSE FALSE FALSE
	
	triad_w2_wait_M1[1] = 1500
	triad_w2_status_M1[1] = 0

	// triad 2
	CREATE_CHAR PEDTYPE_MISSION1 TRIADB 1598.4480 -782.9297 349.0  triad_w2_M1[2]
	SET_CHAR_HEADING triad_w2_M1[2] 175.0 
	SET_CHAR_AREA_VISIBLE triad_w2_M1[2] 0
	GIVE_WEAPON_TO_CHAR triad_w2_M1[2] WEAPONTYPE_M4 30000
	SET_CURRENT_CHAR_WEAPON triad_w2_M1[2] WEAPONTYPE_M4
	SET_CHAR_NEVER_TARGETTED triad_w2_M1[2] TRUE
	SET_CHAR_DECISION_MAKER triad_w2_M1[2] empty_dm_M1
	SET_CHAR_SIGNAL_AFTER_KILL triad_w2_M1[2] FALSE

	CREATE_OBJECT para_pack 1598.4480 -782.9297 349.0 fake_parachute_M1[2]
	TASK_PICK_UP_OBJECT triad_w2_M1[2] fake_parachute_M1[2] -0.11 -0.05 0.0 PED_TORSO HOLD_ORIENTATE_BONE_FULL NULL NULL 0

	SET_CHAR_PROOFS triad_w2_M1[2] TRUE FALSE FALSE FALSE FALSE

	triad_w2_wait_M1[2] = 3000
	triad_w2_status_M1[2] = 0

	// triad 3
	CREATE_CHAR PEDTYPE_MISSION1 TRIADA 1599.0344 -786.9792 349.0  triad_w2_M1[3]
	SET_CHAR_HEADING triad_w2_M1[3] 347.0
	SET_CHAR_AREA_VISIBLE triad_w2_M1[3] 0
	GIVE_WEAPON_TO_CHAR triad_w2_M1[3] WEAPONTYPE_M4 30000
	SET_CURRENT_CHAR_WEAPON triad_w2_M1[3] WEAPONTYPE_M4
	SET_CHAR_NEVER_TARGETTED triad_w2_M1[3] TRUE
	SET_CHAR_DECISION_MAKER triad_w2_M1[3] empty_dm_M1
	SET_CHAR_SIGNAL_AFTER_KILL triad_w2_M1[3] FALSE

	CREATE_OBJECT para_pack 1599.0344 -786.9792 349.0 fake_parachute_M1[3]
	TASK_PICK_UP_OBJECT triad_w2_M1[3] fake_parachute_M1[3] -0.11 -0.05 0.0 PED_TORSO HOLD_ORIENTATE_BONE_FULL NULL NULL 0

	SET_CHAR_PROOFS triad_w2_M1[3] TRUE FALSE FALSE FALSE FALSE

	triad_w2_wait_M1[3] = 5000
	triad_w2_status_M1[3] = 0
	

	IF cutscene_flag_M1 = 0
		GOTO mission_mansion1_MAIN_skipped_cutscenes
	ENDIF
	cutscene_flag_M1 = 0
	

	LOAD_SCENE 1597.6151 -784.5765 350.0 
							   
	SET_FIXED_CAMERA_POSITION 1598.9507 -785.3270 350.1654 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 1598.3513 -784.5302 350.0890 JUMP_CUT

	TIMERA = 0
	WHILE TIMERA < 500
		WAIT 0
	ENDWHILE

	CAMERA_SET_SHAKE_SIMULATION_SIMPLE 1 50000.0 3.0


LVAR_INT current_weapon_M1 has_weapon_M1
	// store current weapon
	GET_CURRENT_CHAR_WEAPON scplayer current_weapon_M1

	// remove brassknuckle
	IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_BRASSKNUCKLE
	    REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_BRASSKNUCKLE
	    has_weapon_M1 = 1
	    REQUEST_MODEL BRASSKNUCKLE
	    WHILE NOT HAS_MODEL_LOADED BRASSKNUCKLE
	          WAIT 0
	    ENDWHILE
	ELSE
		has_weapon_M1 = 0
	ENDIF

	SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

	DO_FADE 500 FADE_IN
 	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

SKIP_CUTSCENE_START

	OPEN_SEQUENCE_TASK sequence_M1
		TASK_STAND_STILL -1 1500
		TASK_GO_STRAIGHT_TO_COORD -1 1598.0504 -784.5205 349.0 PEDMOVE_WALK -1
		TASK_ACHIEVE_HEADING -1 257.9061
		TASK_STAY_IN_SAME_PLACE -1 TRUE
		TASK_USE_MOBILE_PHONE -1 TRUE
	CLOSE_SEQUENCE_TASK sequence_M1	
	
	PERFORM_SEQUENCE_TASK scplayer sequence_M1
	CLEAR_SEQUENCE_TASK sequence_M1

	$audio_text_label_M1[0] = &MKND02A		// Hello.
	audio_sound_label_M1[0]	= SOUND_MKND02A
	audio_speaker_M1[0] 	= scplayer
	$audio_text_label_M1[1] = &MKND02B		// Hey, what's up, Carl?
	audio_sound_label_M1[1]	= SOUND_MKND02B
	audio_speaker_M1[1] 	= -1
	$audio_text_label_M1[2] = &MKND02C		// Hey Kendl, wassup?
	audio_sound_label_M1[2]	= SOUND_MKND02C
	audio_speaker_M1[2] 	= scplayer
	$audio_text_label_M1[3] = &MKND02D		// Nothing - just thought I'd wish you luck.
	audio_sound_label_M1[3]	= SOUND_MKND02D
	audio_speaker_M1[3] 	= -1
	$audio_text_label_M1[4] = &MKND02E		// We're gonna drive back to San Fierro first,
	audio_sound_label_M1[4]	= SOUND_MKND02E
	audio_speaker_M1[4] 	= -1
	$audio_text_label_M1[5] = &MKND02F		// Wanna check things out there before we hook up with you,
	audio_sound_label_M1[5]	= SOUND_MKND02F
	audio_speaker_M1[5] 	= -1
	$audio_text_label_M1[6] = &MKND02G		// Alright cool, you be careful
	audio_sound_label_M1[6]	= SOUND_MKND02G
	audio_speaker_M1[6] 	= scplayer
	$audio_text_label_M1[7] = &MKND02H		// I'll be fine, I got Cesar and Madd Dogg to keep me safe
	audio_sound_label_M1[7]	= SOUND_MKND02H
	audio_speaker_M1[7] 	= -1
	$audio_text_label_M1[8] = &MKND02J		// You be careful.
	audio_sound_label_M1[8]	= SOUND_MKND02J
	audio_speaker_M1[8] 	= -1
	$audio_text_label_M1[9] = &MKND02K		// I will. I'll see you in Los Santos
	audio_sound_label_M1[9]	= SOUND_MKND02K
	audio_speaker_M1[9] 	= scplayer

	audio_pointer_M1 = 0

	WHILE sequence_progress_M1 = 0
		WAIT 0
		GET_SEQUENCE_PROGRESS scplayer sequence_progress_M1						
	ENDWHILE

	load_sample = SOUND_PED_MOBRING
	$load_text = &MOBRING
	START_NEW_SCRIPT audio_load_and_play 0 99

	WHILE NOT SLOT_status[preload_slot] = -3
		WAIT 0
	ENDWHILE		
	SLOT_status[preload_slot] = -4

	GET_SEQUENCE_PROGRESS scplayer sequence_progress_M1

	WHILE NOT sequence_progress_M1 = 4
		WAIT 0
		GET_SEQUENCE_PROGRESS scplayer sequence_progress_M1						
	ENDWHILE 

	WAIT 2250
   

	WHILE audio_pointer_M1 < 10 

		load_sample = audio_sound_label_M1[audio_pointer_M1]
		$load_text = $audio_text_label_M1[audio_pointer_M1]
		
		IF NOT audio_speaker_M1[audio_pointer_M1] = -1
			START_NEW_SCRIPT audio_load_and_play 2 100 audio_speaker_M1[audio_pointer_M1]	
		ELSE		
			START_NEW_SCRIPT audio_load_and_play 0 100 
		ENDIF
		
		WHILE NOT SLOT_status[preload_slot] = -3
			WAIT 0
		ENDWHILE			
		SLOT_status[preload_slot] = -4

		audio_pointer_M1 ++

	ENDWHILE

	WHILE NOT SLOT_status[current_slot] = -4
	OR NOT SLOT_status[preload_slot] = -4  
		WAIT 0
	ENDWHILE

	TASK_USE_MOBILE_PHONE scplayer FALSE

	WAIT 2000

	IF NOT IS_CHAR_DEAD triad_w2_M1[2]
		TASK_TURN_CHAR_TO_FACE_CHAR scplayer triad_w2_M1[2]
		TASK_TURN_CHAR_TO_FACE_CHAR triad_w2_M1[2] scplayer
	ENDIF

	load_sample = SOUND_MAN1_CA		// We're going to go in first, clear the place out so the rest of our outfit can move in	
	$load_text = &MAN1_CA
	START_NEW_SCRIPT audio_load_and_play 1 100 scplayer
	
	WHILE NOT SLOT_status[preload_slot] = -3
		WAIT 0
	ENDWHILE		
	SLOT_status[preload_slot] = -4

	load_sample = SOUND_MAN1_CB		// Element of surprise	
	$load_text = &MAN1_CB
	START_NEW_SCRIPT audio_load_and_play 2 100 triad_w2_M1[2]

	WHILE NOT SLOT_status[preload_slot] = -3
		WAIT 0
	ENDWHILE
	SLOT_status[preload_slot] = -4

	load_sample = SOUND_MAN1_CC		// 	
	$load_text = &MAN1_CC
	START_NEW_SCRIPT audio_load_and_play 1 100 scplayer

	WHILE NOT SLOT_status[current_slot] = -4
	OR NOT SLOT_status[preload_slot] = -4  
		WAIT 0
	ENDWHILE

	TIMERA = 0
	WHILE TIMERA < 1000
		WAIT 0
	ENDWHILE

	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	cutscene_flag_M1 = 1

SKIP_CUTSCENE_END

	CLEAR_PRINTS

	DO_FADE 0 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	// give back brassknuckle
	IF has_weapon_M1 = 1
	    GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_BRASSKNUCKLE 1
	    MARK_MODEL_AS_NO_LONGER_NEEDED BRASSKNUCKLE
	ENDIF
	// give back current weapon
	SET_CURRENT_CHAR_WEAPON scplayer current_weapon_M1
      	
	CAMERA_RESET_NEW_SCRIPTABLES
	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
	MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE

	// clear audio
	IF SLOT_status[0] >= 0
		SLOT_status[0] = -2
	ENDIF
	IF SLOT_status[0] >= 0
		SLOT_status[1] = -2
	ENDIF

	SET_CHAR_COORDINATES scplayer 1096.5624 -825.7372 85.9453

	IF cutscene_flag_M1 = 0
		GOTO mission_mansion1_MAIN_skipped_cutscenes
	ENDIF
	cutscene_flag_M1 = 0

// ##############################################
// ##
// ## VINEWOOD CUTSCENE
// ##
// ##############################################

 
	// fake target for cutscene
	ai_target_x = 1347.4069   
    ai_target_y = -811.7421
    ai_target_z = 96.9707  

	SET_FIXED_CAMERA_POSITION 1385.5676 -810.5753 78.1164 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 1385.4976 -809.5790 78.0669 JUMP_CUT

	triad_w1_status_M1[0] = 0
	triad_w1_status_M1[1] = 0
	triad_w1_status_M1[2] = 0
	triad_w1_status_M1[3] = 0

	triad_w1_cut_status_M1[0] = 0
	triad_w1_cut_status_M1[1] = 0

	LOAD_SCENE 1385.5676 -810.5753 78.1164 
	
	TIMERA = 0
	WHILE TIMERA < 800
		WAIT 0
	ENDWHILE

	DO_FADE 500 FADE_IN 

	CREATE_CHAR PEDTYPE_MISSION1 TRIADA 1485.5360 -818.0389 130.0 triad_w1_M1[0]  
	SET_CHAR_HEADING triad_w1_M1[0] 90.0
	SET_CHAR_AREA_VISIBLE triad_w1_M1[0] 0
	START_NEW_STREAMED_SCRIPT parachute.sc -1 -25.0 10.0 -3.0 -1.0 5.0 6.5 TRUE triad_w1_M1[0]
	STREAM_SCRIPT parachute.sc

LVAR_INT triad_w1_2_active_M1
SKIP_CUTSCENE_START

	TIMERA = 0
	WHILE  TIMERA < 1000
		WAIT 0
		SET_TIME_OF_DAY 03 00
	ENDWHILE
	TIMERA = 0
	triad_w1_2_active_M1 = 0

	CAMERA_RESET_NEW_SCRIPTABLES
	CAMERA_PERSIST_TRACK TRUE 
	CAMERA_PERSIST_POS TRUE
  	CAMERA_SET_VECTOR_MOVE 1385.5676 -810.5753 78.1164 1368.4948 -855.6013 75.1238 12000 TRUE
	CAMERA_SET_VECTOR_TRACK 1385.4976 -809.5790 78.0669 1369.0121 -854.7825 75.3725 12000 TRUE

	//WHILE cam_pos_X_M1 > 1368.4948
	WHILE TIMERA < 14000
		WAIT 0	

		IF triad_w1_2_active_M1 = 0
		AND TIMERA > 3000
			CREATE_CHAR PEDTYPE_MISSION1 TRIADB 1485.5360 -818.0389 130.0 triad_w1_M1[1]  
			SET_CHAR_HEADING triad_w1_M1[1] 90.0
			SET_CHAR_AREA_VISIBLE triad_w1_M1[1] 0
			START_NEW_STREAMED_SCRIPT parachute.sc -1 -25.0 10.0 -3.0 -1.0 5.0 6.5 TRUE triad_w1_M1[1]
			STREAM_SCRIPT parachute.sc
			triad_w1_2_active_M1 = 1	
		ENDIF

		SET_TIME_OF_DAY 03 00
	ENDWHILE

	DO_FADE 1000 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	cutscene_flag_M1 = 1	

SKIP_CUTSCENE_END

	DO_FADE 0 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	CAMERA_RESET_NEW_SCRIPTABLES
	RESTORE_CAMERA_JUMPCUT

	IF cutscene_flag_M1 = 0
		GOTO mission_mansion1_MAIN_skipped_cutscenes
	ENDIF
	cutscene_flag_M1 = 0

// ##############################################
// ##
// ## LANDING ON HELIPAD CUTSCENE
// ##
// ##############################################

LVAR_FLOAT cam_X_M1	cam_Y_M1 cam_Z_M1 cam_angle_M1 cam_radius_M1
LVAR_FLOAT cam_L_X_M1 cam_L_Y_M1 cam_L_Z_M1
LVAR_FLOAT new_x_M1 new_y_M1
LVAR_FLOAT cos_angle_M1 sin_angle_M1

	cam_angle_M1 = 35.0
	cam_radius_M1 = 20.0
	cam_Z_M1 = 100.2

	// helipad
	ai_target_x = 1291.2661   
  	ai_target_y = -788.1172
  	ai_target_z = 96.7736

	cam_L_X_M1 = 1291.1534 
	cam_L_Y_M1 = -788.1339
	cam_L_Z_M1 = 95.0
	
	LOAD_SCENE_IN_DIRECTION 1291.2661 -788.1172 96.7736	122.0
	
	REQUEST_MODEL opmans01_cunte
	REQUEST_MODEL CEhollyhil06
	REQUEST_MODEL sunset22_LAwN

	WHILE NOT HAS_MODEL_LOADED opmans01_cunte
	OR NOT HAS_MODEL_LOADED CEhollyhil06
	OR NOT HAS_MODEL_LOADED sunset22_LAwN
		WAIT 0
	ENDWHILE
		 
	triad_total_M1 = 2

	TIMERA = 0
	WHILE TIMERA < 500
		WAIT 0
		GOSUB mission_mansion1_SUB_remove_triad_w1
	ENDWHILE

	CREATE_FX_SYSTEM smoke_flare 1291.4536 -775.3071 95.5 TRUE signal_flare_M1[0]
    CREATE_FX_SYSTEM smoke_flare 1303.2109 -788.2426 95.5 TRUE signal_flare_M1[1]
    CREATE_FX_SYSTEM smoke_flare 1291.1592 -800.4006 95.5 TRUE signal_flare_M1[2]

	CREATE_CHAR PEDTYPE_MISSION1 TRIADB 1323.1700 -783.7548 117.0 triad_w1_M1[2]
	//CREATE_CHAR PEDTYPE_MISSION1 TRIADB 1304.3013 -782.1478 109.0 triad_w1_M1[2]  
	SET_CHAR_HEADING triad_w1_M1[2] 90.0
	SET_CHAR_AREA_VISIBLE triad_w1_M1[2] 0
	GIVE_WEAPON_TO_CHAR triad_w1_M1[2] WEAPONTYPE_M4 30000
	START_NEW_STREAMED_SCRIPT parachute.sc -1 -2.0 1.0 -3.0 -1.0 1.5 3.2 TRUE triad_w1_M1[2]
	STREAM_SCRIPT parachute.sc
	SET_CHAR_VELOCITY triad_w1_M1[2] 0.0 0.0 2.0 
	SET_CHAR_DECISION_MAKER triad_w1_M1[2] empty_dm_M1
															//124.0
	CREATE_CHAR PEDTYPE_MISSION1 TRIADA 1323.4418 -798.6427 120.0 triad_w1_M1[3]
	//CREATE_CHAR PEDTYPE_MISSION1 TRIADA 1302.0941 -797.1603 107.5 triad_w1_M1[3]  
	SET_CHAR_HEADING triad_w1_M1[3] 90.0
	SET_CHAR_AREA_VISIBLE triad_w1_M1[3] 0
	GIVE_WEAPON_TO_CHAR triad_w1_M1[3] WEAPONTYPE_M4 30000
	START_NEW_STREAMED_SCRIPT parachute.sc -1 -2.0 1.0 -3.0 -1.0 1.0 2.5 TRUE triad_w1_M1[3]
	STREAM_SCRIPT parachute.sc
	SET_CHAR_VELOCITY triad_w1_M1[3] 0.0 0.0 2.0 
	SET_CHAR_DECISION_MAKER triad_w1_M1[3] empty_dm_M1
	
	DO_FADE 1000 FADE_IN
	WHILE GET_FADING_STATUS
		WAIT 0
		GOSUB mission_mansion1_SUB_remove_triad_w1
		GOSUB mission_mansion1_SUB_rotate_camera
	ENDWHILE

        		
LVAR_INT signal_flare_M1[3]

SKIP_CUTSCENE_START

	TIMERA = 0
	WHILE triad_w1_cut_status_M1[0] < 8
		WAIT 0
	
		GOSUB mission_mansion1_SUB_triad_w1_2
		GOSUB mission_mansion1_SUB_triad_w1_3

		SET_TIME_OF_DAY 03 00

		// remove_triads from intro cutscene
		GOSUB mission_mansion1_SUB_remove_triad_w1

		GOSUB mission_mansion1_SUB_rotate_camera 
	ENDWHILE

	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

SKIP_CUTSCENE_END

mission_mansion1_MAIN_skipped_cutscenes:

	DO_FADE 0 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	MARK_MODEL_AS_NO_LONGER_NEEDED opmans01_cunte
	MARK_MODEL_AS_NO_LONGER_NEEDED CEhollyhil06

	CAMERA_RESET_NEW_SCRIPTABLES
	RESTORE_CAMERA_JUMPCUT

	PLAY_FX_SYSTEM signal_flare_M1[0]
	PLAY_FX_SYSTEM signal_flare_M1[1]
	PLAY_FX_SYSTEM signal_flare_M1[2]
	
	// helipad
	ai_target_x = 1291.2661   
  	ai_target_y = -788.1172
  	ai_target_z = 96.7736

// #####################
// ##
// ## inside the plane
// ##
// #####################


LVAR_INT cargo_plane_M1	cargo_ramp_M1 player_box_M1
LVAR_INT fake_player_parachute_M1

mission_mansion1_MAIN_in_plane:
	
	
	REQUEST_MODEL GUN_PARA
	REQUEST_MODEL CARDBOARDBOX2
	LOAD_MISSION_AUDIO 3 SOUND_BANK_CARGO_PLANE
	 
	WHILE NOT HAS_MODEL_LOADED GUN_PARA
	OR NOT HAS_MODEL_LOADED CARDBOARDBOX2
	OR NOT HAS_MISSION_AUDIO_LOADED 3
		WAIT 0
		triad_total_M1 = 4
		GOSUB mission_mansion1_SUB_remove_triad_w1
	ENDWHILE
	
	$audio_text_label_M1[0] = &MAN1_DA		// You ever jumped before?.
	audio_sound_label_M1[0]	= SOUND_MAN1_DA
	audio_speaker_M1[0] 	= triad_W2_M1[0]
	$audio_text_label_M1[1] = &MAN1_DB		// Nah, You?
	audio_sound_label_M1[1]	= SOUND_MAN1_DB
	audio_speaker_M1[1] 	= triad_W2_M1[1]
	$audio_text_label_M1[2] = &MAN1_DC		// Nah?
	audio_sound_label_M1[2]	= SOUND_MAN1_DC
	audio_speaker_M1[2] 	= triad_W2_M1[0]
	$audio_text_label_M1[3] = &MAN1_DD		// When we land we're going feel invincible
	audio_sound_label_M1[3]	= SOUND_MAN1_DD
	audio_speaker_M1[3] 	= triad_W2_M1[1]
	$audio_text_label_M1[4] = &MAN1_DE		// I AM INVINCIBLE
	audio_sound_label_M1[4]	= SOUND_MAN1_DE
	audio_speaker_M1[4] 	= triad_W2_M1[0]
	$audio_text_label_M1[5] = &MAN1_DF		// Hey, wait up
	audio_sound_label_M1[5]	= SOUND_MAN1_DF
	audio_speaker_M1[5] 	= triad_W2_M1[1]
	$audio_text_label_M1[6] = &MAN1_DG		// WhaaaHOOOOOO!
	audio_sound_label_M1[6]	= SOUND_MAN1_DG
	audio_speaker_M1[6] 	= triad_W2_M1[1]
	$audio_text_label_M1[7] = &MAN1_DH		// WhaaaHEEEEEE!
	audio_sound_label_M1[7]	= SOUND_MAN1_DH
	audio_speaker_M1[7] 	= triad_W2_M1[2]
	$audio_text_label_M1[8] = &MAN1_DJ		// WhaaaHOOOOOO!
	audio_sound_label_M1[8]	= SOUND_MAN1_DJ
	audio_speaker_M1[8] 	= triad_W2_M1[3]

	CREATE_OBJECT_NO_OFFSET CARDBOARDBOX2 1601.7587 -785.1198 349.95 player_box_M1
	SET_OBJECT_HEADING player_box_M1 90.0									   
	FREEZE_OBJECT_POSITION player_box_M1 TRUE
	SET_OBJECT_VISIBLE player_box_M1 FALSE
	SET_OBJECT_COLLISION player_box_M1 FALSE

	RESTORE_CAMERA_JUMPCUT
	ATTACH_CHAR_TO_OBJECT scplayer player_box_M1 0.0 0.0 0.0 FACING_FORWARD 40.0 WEAPONTYPE_UNARMED 	
	
	player_status_M1 = 0

	LOAD_SCENE 1601.7587 -785.1198 351.0 
	
	MARK_CHAR_AS_NO_LONGER_NEEDED lsv_guard_M1 

	SET_PLAYER_DUCK_BUTTON player1 FALSE
	SET_PLAYER_JUMP_BUTTON player1 FALSE
	SET_PLAYER_FIRE_BUTTON player1 FALSE

	// triads in the plane
LVAR_INT triad_w2_M1[4] triad_w2_status_M1[4] triad_w2_wait_M1[4]
LVAR_INT triad_w3_M1[3]  triad_w3_blip_M1[3] triad_status_w3_M1[3]
LVAR_INT fake_parachute_M1[4]


	SWITCH_WIDESCREEN OFF
	DISPLAY_RADAR FALSE
	DISPLAY_HUD FALSE

	// variables
	turbulence_length_M1 = 2000
	turbulence_status_M1 = 0

	cargo_door_X_M1 = 0.0

	TIMERA = 0
	WHILE TIMERA < 500
		WAIT 0
		GOSUB mission_mansion1_SUB_remove_triad_w1
	ENDWHILE

	FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_LA
		
	SET_PLAYER_CONTROL player1 ON

	DO_FADE 500 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
		GOSUB mission_mansion1_SUB_remove_triad_w1
	ENDWHILE

LVAR_INT conversation_flag_M1

	TIMERA = 0	
	conversation_flag_M1 = 0
	audio_pointer_M1 = 0

	WHILE audio_pointer_M1 < 5

		WAIT 0

		GOSUB mission_mansion1_SUB_remove_triad_w1
		IF TIMERA > 1000
			GOSUB mission_mansion1_SUB_open_cargo_door
		ENDIF

		SWITCH conversation_flag_M1
			CASE 0
				IF NOT TIMERA > 800
					BREAK
				ENDIF

			CASE 1
				load_sample = audio_sound_label_M1[audio_pointer_M1]
				$load_text = $audio_text_label_M1[audio_pointer_M1]
				START_NEW_SCRIPT audio_load_and_play 2 100 audio_speaker_M1[audio_pointer_M1]	
				conversation_flag_M1 = 2
	
			CASE 2
				IF NOT SLOT_status[preload_slot] = -3
					BREAK
				ENDIF			
				
				SLOT_status[preload_slot] = -4
				audio_pointer_M1 ++
				conversation_flag_M1 = 1			

		ENDSWITCH
			
	ENDWHILE

	TIMERA = 0
	GET_GAME_TIMER last_frame_time_M1

mission_mansion1_MAIN_in_plane_loop:

	WAIT 0

 	// timers
	GET_GAME_TIMER this_frame_time_M1
	time_diff_M1 = this_frame_time_M1 - last_frame_time_M1 
	last_frame_time_M1 = this_frame_time_M1

	// player timer
	player_timer_M1 += time_diff_M1

	// set traid timers
	pointer_M1 = 0
	WHILE pointer_M1 < 4
		triad_W2_timer_M1[pointer_M1] += time_diff_M1
		pointer_M1 ++
	ENDWHILE

	// controls player for cutscene jumping out of the plane
   	GOSUB mission_mansion1_sub_player_parachute

	// controls movement and actions of wave 2 triads
	GOSUB mission_mansion1_SUB_triad_W2_controller

	// remove_triads from intro cutscene (if still alive depends on how fast player skipped cutscene)
	GOSUB mission_mansion1_SUB_remove_triad_w1

	IF player_status_M1 < 4
		SET_TIME_OF_DAY 03 00 
	ELSE
		player_status_M1 = 0
		
		SWITCH_ENTRY_EXIT maddogs FALSE 
		SWITCH_ENTRY_EXIT mddogs FALSE

		CREATE_PICKUP BODYARMOUR PICKUP_ONCE 1292.7 -769.0 95.8 health_pickup_M1
			
		GOTO mission_mansion1_MAIN_outside
	ENDIF 

GOTO mission_mansion1_MAIN_in_plane_loop


// ######################
// ##
// ## outside the plane
// ##
// ######################


mission_mansion1_MAIN_outside:

	triad_alive_w2_M1 = 4

	lsv_model_M1[0] = LSV1
	lsv_model_M1[1] = LSV2
	lsv_model_M1[2] = LSV1
	lsv_model_M1[3] = LSV2


mission_mansion1_MAIN_outside_loop:

	WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
        GOTO mission_mansion1_PASSED  
	ENDIF
	
	IF fail_text_flag_M1 = 1
		GOTO mission_mansion1_FAILED
	ENDIF

 	// timers
	GET_GAME_TIMER this_frame_time_M1
	time_diff_M1 = this_frame_time_M1 - last_frame_time_M1 
	last_frame_time_M1 = this_frame_time_M1

	// player timer
	player_timer_M1 += time_diff_M1

	// set traid timers
	pointer_M1 = 0
	WHILE pointer_M1 < 3
		triad_W2_timer_M1[pointer_M1] += time_diff_M1
		triad_timer_w3_M1[pointer_M1] += time_diff_M1
		pointer_M1 ++
	ENDWHILE
	triad_W2_timer_M1[3] += time_diff_M1

	// lsv timers
	pointer_M1 = 0
	WHILE pointer_M1 < 6
		lsv_timer_M1[pointer_M1] += time_diff_M1
		pointer_M1 ++
	ENDWHILE

	// player outside controller
	GOSUB mission_mansion1_SUB_player_outside

	// wave 2 triads
	GOSUB mission_mansion1_SUB_triad_W2_controller

	// wave 3 triads
	IF player_status_M1 > 5
		GOSUB mission_mansion1_SUB_triad_W3_controller
	ENDIF

	// lsv guards
	IF roof_lsv_active_M1 < 2
		GOSUB mission_mansion1_SUB_flying_bullets 
	ELSE
		GOSUB mission_mansion1_SUB_lsv_climbers // 4 guys who climb over the edge
	ENDIF

	// used to stop player area check being run more than once a frame
	checked_player_location_M1 = 0

	GET_AREA_VISIBLE current_area_M1
	IF NOT current_area_M1 = 0

		IF LOCATE_CHAR_ON_FOOT_2D scplayer 1257.9954 -785.4104 5.0 5.0 FALSE 
		
			current_area_M1 = 0
			WHILE current_area_M1 = 0 
				GET_CHAR_AREA_VISIBLE scplayer current_area_M1
				WAIT 0
			ENDWHILE  

			DO_FADE 0 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			
			// clear mansion of chars (usually just one that enters through the entry exit)
			CLEAR_AREA_OF_CHARS 1288.0 -772.0 1080.0 1260.5 -787.5 1100.0
			
			GOTO mission_mansion1_main_inside	
		
		ENDIF 
	ENDIF  

GOTO mission_mansion1_MAIN_outside_loop 


// ##########################################################################################################
// ##
// ## OUTSIDE MANSION SUB's
// ##
// ##########################################################################################################

LVAR_INT player_status_M1 player_timer_M1


// player inside plane parachutes out
mission_mansion1_sub_player_parachute:

	SWITCH player_status_M1 
	
		CASE 0
			IF TIMERA > 8300
				DETACH_CHAR_FROM_CAR scplayer
				//SWITCH_WIDESCREEN ON
								
				SET_PLAYER_CONTROL player1 OFF
				TASK_GO_TO_COORD_ANY_MEANS scplayer 1584.2921 -784.9141 347.0 PEDMOVE_SPRINT -1
				
				//GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_M4 30
				
				IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_PARACHUTE
					GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_PARACHUTE 1
				ENDIF
				SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PARACHUTE
												
				turbulence_status_M1 = 2
				player_status_M1 ++
			ENDIF
		BREAK

		CASE 1
			RESTORE_CAMERA_JUMPCUT
			SET_CAMERA_BEHIND_PLAYER
 			
			player_status_M1 ++
		BREAK

		CASE 2 
			IF LOCATE_CHAR_ON_FOOT_2D scplayer 1584.2921 -784.9141 2.75 2.75 FALSE
				TASK_JUMP scplayer TRUE
				player_timer_M1 = 0
				turbulence_status_M1 = 3
				SHAKE_CAM 0
				player_status_M1 ++
			ENDIF	
		BREAK

		CASE 3 
			IF player_timer_M1 > 700
				//para_force_chute_open = 1
				player_timer_M1 = 0				

				DISPLAY_ZONE_NAMES TRUE
				
				DELETE_OBJECT cargo_plane_M1
				DELETE_OBJECT cargo_ramp_M1
				DELETE_OBJECT player_box_M1

				MARK_MODEL_AS_NO_LONGER_NEEDED CARGO_REAR
				MARK_MODEL_AS_NO_LONGER_NEEDED D9_RAMP

				SET_PLAYER_CONTROL player1 ON

				SET_PLAYER_DUCK_BUTTON player1 TRUE
				SET_PLAYER_JUMP_BUTTON player1 TRUE
				SET_PLAYER_FIRE_BUTTON player1 TRUE
				
				DISPLAY_RADAR TRUE
				DISPLAY_HUD TRUE

				PRINT_NOW MAN1_03 8000 1
				ADD_BLIP_FOR_COORD 1290.4906 -788.3314 95.4531 goto_blip_M1 
				player_status_M1 ++

			ENDIF
		BREAK

	ENDSWITCH
RETURN 

// ###############################################################################################################

// subroutine runs once player has jumped from the plane
mission_mansion1_SUB_player_outside:

	SWITCH player_status_M1
		// check for player landing
		CASE 0
			IF player_landed = 2
				player_status_M1 = 2
			ELSE
				IF player_landed = 3
					player_status_M1 ++
				ENDIF
			ENDIF
		BREAK
		// player has pressed triangle
		CASE 1
			IF NOT IS_CHAR_IN_AIR scplayer
				player_status_M1 ++
			ENDIF
		BREAK
		// player has landed
		CASE 2
			//SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_M4
			location_status_M1 = 0
			GOSUB mission_mansion1_SUB_ped_location
			
			IF NOT IS_CHAR_DEAD scplayer 
				IF NOT player_location_M1 = -1
					IF player_location_M1 < 6
						player_status_M1 = 5
					ELSE
						PRINT_NOW MAN1_04 8000 1
						player_status_M1 = 4
					ENDIF		
				ELSE
					PRINT_NOW MAN1_04 8000 1
					player_status_M1 = 3
				ENDIF
			ENDIF
			// make triads and lsv able to kill each other
			pointer_M1 = 0
			WHILE pointer_M1 < 4

				IF NOT IS_CHAR_DEAD lsv_outside_M1[pointer_M1]
					SET_CHAR_PROOFS lsv_outside_M1[pointer_M1] FALSE FALSE FALSE FALSE FALSE
				ENDIF
				pointer_M1 ++
			ENDWHILE

		BREAK
		// player not landed on the mansion
		CASE 3
			location_status_M1 = 0
			GOSUB mission_mansion1_SUB_ped_location			
			IF NOT player_location_M1 = -1
				player_status_M1 ++
			ENDIF		
		BREAK
		// player on mansion but not on the roof
		CASE 4
			location_status_M1 = 0
			GOSUB mission_mansion1_SUB_ped_location			
			IF NOT player_location_M1 = -1
			AND player_location_M1 < 6

				player_status_M1 ++
			ENDIF		
		BREAK
		// player on mansion roof
		CASE 5
			REMOVE_BLIP goto_blip_M1
			
			IF triad_alive_w2_M1 = 0
				PRINT_NOW MAN1_06 8000 1
			ELSE
				PRINT_NOW MAN1_05 8000 1
			ENDIF			

			CLEAR_WANTED_LEVEL player1
			SET_MAX_WANTED_LEVEL 0

			CREATE_CHAR PEDTYPE_MISSION1 TRIADA 1000.0 -785.0 200.0 triad_w3_M1[0]
			SET_CHAR_HEADING triad_w3_M1[0] 270.0
			SET_CHAR_AREA_VISIBLE triad_w3_M1[0] 0
			ADD_BLIP_FOR_CHAR triad_w3_M1[0] triad_w3_blip_M1[0]
			CHANGE_BLIP_DISPLAY triad_w3_blip_M1[0] BLIP_ONLY
			SET_BLIP_AS_FRIENDLY triad_w3_blip_M1[0] TRUE 
 
			GIVE_WEAPON_TO_CHAR triad_w3_M1[0] WEAPONTYPE_M4 30000
			SET_CURRENT_CHAR_WEAPON triad_w3_M1[0] WEAPONTYPE_M4
			SET_CHAR_NEVER_TARGETTED triad_w3_M1[0] TRUE
			SET_CHAR_DECISION_MAKER triad_w3_M1[0] empty_dm_M1

			triad_status_w3_M1[0] = 0
			triad_timer_w3_M1[0]  = 0
			START_NEW_STREAMED_SCRIPT parachute.sc -1 -26.0 14.0 -3.0 -1.0 5.25 6.75 TRUE triad_w3_M1[0]
			STREAM_SCRIPT parachute.sc

			 
			CREATE_CHAR PEDTYPE_MISSION1 TRIADB 1000.0 -770.0 200.0 triad_w3_M1[1]
			SET_CHAR_HEADING triad_w3_M1[1] 270.0
			SET_CHAR_AREA_VISIBLE triad_w3_M1[1] 0
			ADD_BLIP_FOR_CHAR triad_w3_M1[1] triad_w3_blip_M1[1]
			CHANGE_BLIP_DISPLAY triad_w3_blip_M1[1] BLIP_ONLY
			SET_BLIP_AS_FRIENDLY triad_w3_blip_M1[1] TRUE 

			GIVE_WEAPON_TO_CHAR triad_w3_M1[1] WEAPONTYPE_M4 30000
			SET_CURRENT_CHAR_WEAPON triad_w3_M1[1] WEAPONTYPE_M4
			SET_CHAR_NEVER_TARGETTED triad_w3_M1[1] TRUE
			SET_CHAR_DECISION_MAKER triad_w3_M1[1] empty_dm_M1

			triad_status_w3_M1[1] = 0
			triad_timer_w3_M1[1]  = 0
			START_NEW_STREAMED_SCRIPT parachute.sc -1 -26.0 14.0 -3.0 -1.0 5.25 6.75 TRUE triad_w3_M1[1]
			STREAM_SCRIPT parachute.sc

			
			CREATE_CHAR PEDTYPE_MISSION1 TRIADA 1000.0 -800.0 200.0 triad_w3_M1[2]
			SET_CHAR_HEADING triad_w3_M1[2] 270.0
			SET_CHAR_AREA_VISIBLE triad_w3_M1[2] 0
			ADD_BLIP_FOR_CHAR triad_w3_M1[2] triad_w3_blip_M1[2]
			CHANGE_BLIP_DISPLAY triad_w3_blip_M1[2] BLIP_ONLY
			SET_BLIP_AS_FRIENDLY triad_w3_blip_M1[2] TRUE 

			GIVE_WEAPON_TO_CHAR triad_w3_M1[2] WEAPONTYPE_M4 30000
			SET_CURRENT_CHAR_WEAPON triad_w3_M1[2] WEAPONTYPE_M4
			SET_CHAR_NEVER_TARGETTED triad_w3_M1[2] TRUE
			SET_CHAR_DECISION_MAKER triad_w3_M1[2] empty_dm_M1

			triad_status_w3_M1[2] = 0
			triad_timer_w3_M1[2]  = 0
			START_NEW_STREAMED_SCRIPT parachute.sc -1 -26.0 14.0 -3.0 -1.0 5.25 6.75 TRUE triad_w3_M1[2]
			STREAM_SCRIPT parachute.sc

			triad_landed_w3_M1 = 0

			IF NOT IS_CHAR_DEAD triad_w2_M1[0]
				 SET_CHAR_HEALTH triad_w2_M1[0] 20
			ENDIF
			IF NOT IS_CHAR_DEAD triad_w2_M1[1]
				 SET_CHAR_HEALTH triad_w2_M1[1] 40
			ENDIF
			IF NOT IS_CHAR_DEAD triad_w2_M1[2]
				 SET_CHAR_HEALTH triad_w2_M1[2] 30
			ENDIF
			IF NOT IS_CHAR_DEAD triad_w2_M1[3]
				 SET_CHAR_HEALTH triad_w2_M1[3] 25
			ENDIF
			
			player_status_M1 ++
		BREAK
		
		CASE 6
			IF triad_landed_w3_M1 = 3
				
				location_status_M1 = 0
				GOSUB mission_mansion1_SUB_ped_location
				
				IF player_location_M1 < 6
				AND NOT player_location_M1 = -1 	
					PRINT_NOW MAN1_09 8000 1
					player_status_M1 = 8
				ELSE
					PRINT_NOW MAN1_10 8000 1
					player_status_M1 = 7 
				ENDIF

			ENDIF
		BREAK

		CASE 7
			location_status_M1 = 0
			GOSUB mission_mansion1_SUB_ped_location

			IF player_location_M1 < 6
			AND NOT player_location_M1 = -1
				PRINT_NOW MAN1_09 8000 1
				player_status_M1 = 8
			ENDIF
		BREAK

		CASE 8
			GOSUB mission_mansion1_SUB_add_w3_to_player_group
			
			roof_lsv_active_M1 = 3
			ADD_BLIP_FOR_COORD 1257.9954 -785.4104 91.0302 goto_blip_M1
			SWITCH_ENTRY_EXIT maddogs TRUE

			player_status_M1 ++
		BREAK

		CASE 9
			location_status_M1 = 0
			GOSUB mission_mansion1_SUB_ped_location

			IF player_location_M1 = -1
			OR player_location_M1 > 10
				GOSUB mission_mansion1_SUB_remove_w3_from_player_group
				player_status_M1 ++
			ENDIF
		BREAK

		CASE 10
			location_status_M1 = 0
			GOSUB mission_mansion1_SUB_ped_location

			IF NOT player_location_M1 = -1
			AND player_location_M1 <= 10
				GOSUB mission_mansion1_SUB_add_w3_to_player_group
				player_status_M1 --
			ENDIF
		BREAK

	ENDSWITCH
RETURN


LVAR_INT lsv_guard_M1
LVAR_INT triad_W2_timer_M1[4] triad_timer_w3_M1[3]
LVAR_INT triad_total_M1 triad_alive_w2_M1 triad_landed_w3_M1

// -- wave 1

// parachuting triad who plants the flare in front of the camera and shoots the guard
mission_mansion1_SUB_triad_w1_2:

	IF NOT IS_CHAR_DEAD triad_w1_M1[2] 
		SWITCH triad_w1_cut_status_M1[0]

			CASE 0
				IF IS_CHAR_PLAYING_ANIM triad_w1_M1[2] PARA_land
					triad_w1_status_M1[2] = 1
					triad_w1_cut_status_M1[0] ++
				ELSE
					GET_CHAR_COORDINATES triad_w1_M1[2] x y z
					IF z < 99.0
						camera_status_M1 = 1
					ENDIF

				ENDIF
			BREAK

			CASE 1
				IF NOT IS_CHAR_PLAYING_ANIM triad_w1_M1[2] PARA_land

					OPEN_SEQUENCE_TASK sequence_M1
						
						TASK_TOGGLE_DUCK -1 TRUE 
						//TASK_GO_STRAIGHT_TO_COORD -1 1291.5193 -779.1869 95.4531 PEDMOVE_RUN -1
						TASK_GO_STRAIGHT_TO_COORD -1 1291.5193 -776.0716 95.4531 PEDMOVE_RUN -1
  						TASK_STAND_STILL -1 400
  						TASK_STAND_STILL -1 400
						TASK_GO_STRAIGHT_TO_COORD -1 1280.9952 -780.3347 95.4610 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 110.0

  					CLOSE_SEQUENCE_TASK sequence_M1	
					
					PERFORM_SEQUENCE_TASK triad_w1_M1[2] sequence_M1
					CLEAR_SEQUENCE_TASK sequence_M1					
					
					triad_w1_cut_status_M1[0] ++
				ENDIF
			BREAK

			CASE 2
				GET_SEQUENCE_PROGRESS triad_w1_M1[2] sequence_progress_M1
				IF sequence_progress_M1 = 4
					PLAY_FX_SYSTEM signal_flare_M1[0]
					TIMERA = 0
					triad_w1_cut_status_M1[0] ++	
				ENDIF	
			BREAK

			CASE 3
				IF TIMERA > 1500
 
					triad_w1_cut_status_M1[0] ++

					CREATE_CHAR PEDTYPE_MISSION2 LSV1 1281.4127 -785.8877 91.0302 lsv_guard_M1
					SET_CHAR_HEADING lsv_guard_M1 90.0
					SET_CHAR_AREA_VISIBLE lsv_guard_M1 0 
					TASK_GO_STRAIGHT_TO_COORD lsv_guard_M1 1268.3365 -785.9549 94.9688 PEDMOVE_WALK -1
					SET_CHAR_DECISION_MAKER lsv_guard_M1 empty_dm_M1
					GIVE_WEAPON_TO_CHAR lsv_guard_M1 WEAPONTYPE_MP5 1
					SET_CURRENT_CHAR_WEAPON lsv_guard_M1 WEAPONTYPE_MP5
				ENDIF
 			BREAK

			CASE 4
				IF cam_angle_M1 < 190.0
					BREAK
				ENDIF
					
				TIMERA = 0
				triad_w1_cut_status_M1[0] ++

			CASE 5
				IF TIMERA > 2000
					IF NOT IS_CHAR_DEAD lsv_guard_M1
						GET_CHAR_COORDINATES lsv_guard_M1 x y z
					ENDIF
					z += 1.0
					TASK_SHOOT_AT_COORD triad_w1_M1[2] x y z 100 
					TIMERA  = 0
					triad_w1_cut_status_M1[0] ++	
				ENDIF	
			BREAK

			CASE 6
				IF TIMERA > 50
					IF NOT IS_CHAR_DEAD lsv_guard_M1
						EXPLODE_CHAR_HEAD lsv_guard_M1
					ENDIF
					triad_w1_cut_status_M1[0] ++
				ENDIF
			BREAK

			CASE 7
				IF TIMERA > 2000
					triad_w1_cut_status_M1[0] ++	
				ENDIF
			BREAK

		ENDSWITCH
	ELSE
		MARK_CHAR_AS_NO_LONGER_NEEDED triad_w1_M1[2]
		triad_w1_cut_status_M1[0] = -1
	ENDIF
RETURN

// ###############################################################################################################

// the other parachuting triad that plants flares
mission_mansion1_SUB_triad_w1_3:
	IF NOT IS_CHAR_DEAD triad_w1_M1[3] 
		SWITCH triad_w1_cut_status_M1[1]

			CASE 0
				IF IS_CHAR_PLAYING_ANIM triad_w1_M1[3] PARA_land
					triad_w1_status_M1[3] = 1
					triad_w1_cut_status_M1[1] ++
				ENDIF
			BREAK

			CASE 1
				IF NOT IS_CHAR_PLAYING_ANIM triad_w1_M1[3] PARA_land

					OPEN_SEQUENCE_TASK sequence_M1
						
						TASK_TOGGLE_DUCK -1 TRUE 
						TASK_GO_STRAIGHT_TO_COORD -1 1291.2129 -799.4603 95.4531 PEDMOVE_RUN -1
  						TASK_STAND_STILL -1 400
  						TASK_STAND_STILL -1 400
						TASK_GO_STRAIGHT_TO_COORD -1 1302.1963 -788.2992 95.4531 PEDMOVE_RUN -1
  						TASK_STAND_STILL -1 400
  						TASK_STAND_STILL -1 400
						TASK_GO_STRAIGHT_TO_COORD -1 1292.3834 -787.4926 95.4476 PEDMOVE_RUN -1

					CLOSE_SEQUENCE_TASK sequence_M1	
					
					PERFORM_SEQUENCE_TASK triad_w1_M1[3] sequence_M1
					CLEAR_SEQUENCE_TASK sequence_M1					

					triad_w1_cut_status_M1[1] ++
				ENDIF
			BREAK

			CASE 2
				GET_SEQUENCE_PROGRESS triad_w1_M1[3] sequence_progress_M1
				IF sequence_progress_M1 = 3
					PLAY_FX_SYSTEM signal_flare_M1[2]
					triad_w1_cut_status_M1[1] ++	
				ENDIF	
			BREAK

			CASE 3
				GET_SEQUENCE_PROGRESS triad_w1_M1[3] sequence_progress_M1
				IF sequence_progress_M1 = 6
					PLAY_FX_SYSTEM signal_flare_M1[1]
					triad_w1_cut_status_M1[1] ++	
				ENDIF	
			BREAK

		ENDSWITCH
	ELSE
		MARK_CHAR_AS_NO_LONGER_NEEDED triad_w1_M1[3]
		triad_w1_cut_status_M1[1] = -1
	ENDIF
RETURN

// ###############################################################################################################

// used for removing the first wave of triads used in the cutscenes
mission_mansion1_SUB_remove_triad_w1:

	pointer_M1 = 0
	WHILE pointer_M1 < triad_total_M1

		IF NOT triad_w1_status_M1[pointer_M1] = -1 
			IF NOT IS_CHAR_DEAD triad_w1_M1[pointer_M1] 
				SWITCH triad_w1_status_M1[pointer_M1]

					CASE 0
						IF IS_CHAR_PLAYING_ANIM triad_w1_M1[pointer_M1] PARA_land
							triad_w1_status_M1[pointer_M1] ++
						ENDIF
					BREAK

					CASE 1
						IF NOT IS_CHAR_PLAYING_ANIM triad_w1_M1[pointer_M1] PARA_land
							TASK_DIE triad_w1_M1[pointer_M1]
							triad_w1_status_M1[pointer_M1] ++
						ENDIF
					BREAK

				ENDSWITCH
			ELSE
				MARK_CHAR_AS_NO_LONGER_NEEDED triad_w1_M1[pointer_M1]
				triad_w1_status_M1[pointer_M1] = -1
			ENDIF
		ENDIF
		pointer_M1 ++
	ENDWHILE
 
RETURN

// ###############################################################################################################

// wave 2

// controls the triads who jump from the plane with the player and land on the roof
mission_mansion1_SUB_triad_W2_controller:

	pointer_M1 = 0
	WHILE pointer_M1 < 4

		IF NOT triad_w2_status_M1[pointer_M1] = -1
		
			IF NOT IS_CHAR_DEAD triad_w2_M1[pointer_M1] 
		
				SWITCH triad_w2_status_M1[pointer_M1] 
				
					// wait for there turn to run
					CASE 0
						IF TIMERA > triad_w2_wait_M1[pointer_M1]
							IF pointer_M1 = 0
							OR pointer_M1 = 2
								TASK_GO_TO_COORD_ANY_MEANS triad_w2_M1[pointer_M1] 1585.2931 -786.5728 347.0 PEDMOVE_RUN -1
							ELSE
								IF pointer_M1 = 1
									load_sample = SOUND_MAN1_DF
									$load_text = &MAN1_DF
									START_NEW_SCRIPT audio_load_and_play 2 100 audio_speaker_M1[audio_pointer_M1]
								ENDIF
								TASK_GO_TO_COORD_ANY_MEANS triad_w2_M1[pointer_M1] 1585.3225 -783.2303 347.0 PEDMOVE_RUN -1
							ENDIF
							triad_w2_status_M1[pointer_M1] ++
						ENDIF 
					BREAK

					// jump if near edge
					CASE 1
						GOSUB mission_mansion1_SUB_jumping_audio
//						IF pointer_M1 = 0
//						OR pointer_M1 = 2
//							IF LOCATE_CHAR_ON_FOOT_2D triad_w2_M1[pointer_M1] 1585.2931 -786.5728 1.2 1.2 FALSE
//								TASK_JUMP triad_w2_M1[pointer_M1] TRUE
//								triad_W2_timer_M1[pointer_M1] = 0
//								triad_w2_status_M1[pointer_M1] ++					
//							ENDIF
//						ELSE
//							IF LOCATE_CHAR_ON_FOOT_2D triad_w2_M1[pointer_M1] 1585.3225 -783.2303 1.2 1.2 FALSE
//								TASK_JUMP triad_w2_M1[pointer_M1] TRUE
//								triad_W2_timer_M1[pointer_M1] = 0
//								triad_w2_status_M1[pointer_M1] ++					
//							ENDIF
//						ENDIF		
					BREAK		
				
					// remove fake parachute and start parachute script
					CASE 2
						IF triad_W2_timer_M1[pointer_M1] > 1200
							
							DROP_OBJECT triad_w2_M1[pointer_M1] FALSE
																	 
							triad_W2_timer_M1[pointer_M1] = 0				
							GOSUB mission_mansion1_SUB_give_w2_parachute

							triad_w2_status_M1[pointer_M1] ++
						ENDIF
					BREAK

					// delete fake parachute (must be done next frame after triad has dropped it)
					CASE 3
						DELETE_OBJECT fake_parachute_M1[pointer_M1]
						triad_w2_status_M1[pointer_M1] ++
					BREAK

					// wait around 45 seconds before checking if triads have landed (takes them roughly about 55)
					CASE 4
						IF triad_W2_timer_M1[pointer_M1] >= 40000
							IF pointer_M1 = 0
								GOSUB mission_mansion1_SUB_create_lsv_on_roof
   							ENDIF
							triad_w2_status_M1[pointer_M1] ++		
						ENDIF	
					BREAK

					// check to see if triad is playing landed anim
					CASE 5
						IF IS_CHAR_PLAYING_ANIM triad_w2_M1[pointer_M1] PARA_land
	 						triad_w2_status_M1[pointer_M1] ++
						ENDIF
					BREAK

					// check to see if triad has stopped playing landed anim
					CASE 6
						IF NOT IS_CHAR_PLAYING_ANIM triad_w2_M1[pointer_M1] PARA_land
							IF roof_lsv_active_M1 < 2
								roof_lsv_active_M1 = 2
								GOSUB mission_masnion1_SUB_lsv_attack  
							ENDIF
							SET_CHAR_PROOFS triad_w2_M1[pointer_M1] FALSE FALSE FALSE FALSE FALSE 
							triad_w2_status_M1[pointer_M1] = pointer_M1 + 7
						ENDIF
					BREAK

					// triad_W2_M1[0]
					CASE 7 
						SET_CHAR_ALLOWED_TO_DUCK triad_W2_M1[0] FALSE
						OPEN_SEQUENCE_TASK sequence_M1
							TASK_GO_TO_COORD_ANY_MEANS -1 1289.6985 -771.6924 94.9540 PEDMOVE_RUN -1
							TASK_ACHIEVE_HEADING -1 100.0
							TASK_TOGGLE_DUCK -1 FALSE
							TASK_STAY_IN_SAME_PLACE -1 TRUE
							TASK_SET_CHAR_DECISION_MAKER -1 cover_dm_M1
						CLOSE_SEQUENCE_TASK sequence_M1	
						PERFORM_SEQUENCE_TASK triad_W2_M1[0] sequence_M1
						CLEAR_SEQUENCE_TASK sequence_M1
						triad_w2_status_M1[pointer_M1] = 11
					BREAK

					// triad_W2_M1[1]
					CASE 8 
						OPEN_SEQUENCE_TASK sequence_M1
							TASK_GO_TO_COORD_ANY_MEANS -1 1285.8242 -772.1167 94.9530 PEDMOVE_RUN -1
							TASK_ACHIEVE_HEADING -1 115.0
							TASK_TOGGLE_DUCK -1 TRUE
							TASK_STAY_IN_SAME_PLACE -1 TRUE
							TASK_SET_CHAR_DECISION_MAKER -1 cover_dm_M1
						CLOSE_SEQUENCE_TASK sequence_M1	
						PERFORM_SEQUENCE_TASK triad_W2_M1[1] sequence_M1
						CLEAR_SEQUENCE_TASK sequence_M1
						triad_w2_status_M1[pointer_M1] = 11
					BREAK

					// triad_W2_M1[2]
					CASE 9 
						SET_CHAR_ALLOWED_TO_DUCK triad_W2_M1[2] FALSE
						OPEN_SEQUENCE_TASK sequence_M1
							TASK_GO_TO_COORD_ANY_MEANS -1 1277.8701 -789.9062 95.4531 PEDMOVE_RUN -1
							TASK_ACHIEVE_HEADING -1 90.0
							TASK_TOGGLE_DUCK -1 FALSE
							TASK_STAY_IN_SAME_PLACE -1 TRUE
							TASK_SET_CHAR_DECISION_MAKER -1 cover_dm_M1
						CLOSE_SEQUENCE_TASK sequence_M1	
						PERFORM_SEQUENCE_TASK triad_W2_M1[2] sequence_M1
						CLEAR_SEQUENCE_TASK sequence_M1
						triad_w2_status_M1[pointer_M1] = 11
					BREAK

					// triad_W2_M1[3]
					CASE 10 
						OPEN_SEQUENCE_TASK sequence_M1
							TASK_GO_TO_COORD_ANY_MEANS -1 1278.6093 -783.3164 95.4531 PEDMOVE_RUN -1
							TASK_ACHIEVE_HEADING -1 95.0
							TASK_TOGGLE_DUCK -1 TRUE
							TASK_STAY_IN_SAME_PLACE -1 TRUE
							TASK_SET_CHAR_DECISION_MAKER -1 cover_dm_M1
						CLOSE_SEQUENCE_TASK sequence_M1	
						PERFORM_SEQUENCE_TASK triad_W2_M1[3] sequence_M1
						CLEAR_SEQUENCE_TASK sequence_M1
						triad_w2_status_M1[pointer_M1] = 11
					BREAK

				ENDSWITCH
			ELSE
				MARK_CHAR_AS_NO_LONGER_NEEDED triad_w2_M1[pointer_M1]
				triad_alive_w2_M1 --
				triad_w2_status_M1[pointer_M1] = -1 
			ENDIF
		ENDIF 	
		
		pointer_M1 ++
	ENDWHILE

RETURN

// ###############################################################################################################

// plays the correct dialogue as the triads jump
mission_mansion1_SUB_jumping_audio:

	SWITCH pointer_M1
		CASE 0
		CASE 2
			IF NOT LOCATE_CHAR_ON_FOOT_2D triad_w2_M1[pointer_M1] 1585.2931 -786.5728 1.2 1.2 FALSE
				BREAK			
			ENDIF

			IF pointer_M1 = 2
				load_sample = SOUND_MAN1_DH		// WhaaaHEEEEEE!
				$load_text = &MAN1_DH
				START_NEW_SCRIPT audio_load_and_play 2 100 triad_w2_M1[pointer_M1]
			ENDIF

			
			TASK_JUMP triad_w2_M1[pointer_M1] TRUE
			triad_W2_timer_M1[pointer_M1] = 0
			triad_w2_status_M1[pointer_M1] ++
		BREAK

		CASE 1
		CASE 3
			IF NOT LOCATE_CHAR_ON_FOOT_2D triad_w2_M1[pointer_M1] 1585.3225 -783.2303 1.2 1.2 FALSE
				BREAK			
			ENDIF

			IF pointer_M1 = 1
				load_sample = SOUND_MAN1_DG		// WhaaaHOOOOOO!
				$load_text = &MAN1_DG
			ELSE
				load_sample = SOUND_MAN1_DJ		// WhoooHOOOOOO!
				$load_text = &MAN1_DJ
			ENDIF

			START_NEW_SCRIPT audio_load_and_play 2 100 triad_w2_M1[pointer_M1]
			TASK_JUMP triad_w2_M1[pointer_M1] TRUE
			triad_W2_timer_M1[pointer_M1] = 0
			triad_w2_status_M1[pointer_M1] ++
		BREAK

	ENDSWITCH


RETURN

// ###############################################################################################################

// sets the different parachuting speeds for the triads so the split up a bit
mission_mansion1_SUB_give_w2_parachute:

	SWITCH pointer_M1
		CASE 0
			START_NEW_STREAMED_SCRIPT parachute.sc -1 -29.0 14.0 -3.0 -1.0 5.25 6.75 TRUE triad_w2_M1[0]
			STREAM_SCRIPT parachute.sc
		BREAK
		CASE 1
			START_NEW_STREAMED_SCRIPT parachute.sc -1 -28.0 14.0 -3.0 -1.0 5.25 6.75 TRUE triad_w2_M1[1]
			STREAM_SCRIPT parachute.sc
		BREAK
		CASE 2
			START_NEW_STREAMED_SCRIPT parachute.sc -1 -27.0 14.0 -3.0 -1.0 5.25 6.75 TRUE triad_w2_M1[2]
			STREAM_SCRIPT parachute.sc
		BREAK
		CASE 3
			START_NEW_STREAMED_SCRIPT parachute.sc -1 -26.0 14.0 -3.0 -1.0 5.25 6.75 TRUE triad_w2_M1[3]
			STREAM_SCRIPT parachute.sc
		BREAK
	ENDSWITCH

RETURN

// ###############################################################################################################

mission_mansion1_SUB_triad_w3_controller:

	pointer_M1 = 0

	WHILE pointer_M1 < 3
		
		IF NOT IS_CHAR_DEAD triad_w3_M1[pointer_M1]
			SWITCH triad_status_w3_M1[pointer_M1] 

				CASE 0
					IF triad_timer_w3_M1[pointer_M1] >= 35000
						IF pointer_M1 = 0
							location_status_M1 = 0
							GOSUB mission_mansion1_SUB_ped_location

							IF player_location_M1 < 6
							AND NOT player_location_M1 = -1
								PRINT_NOW MAN1_07 8000 1
							ELSE
								PRINT_NOW MAN1_08 8000 1 
							ENDIF
						ENDIF
						triad_status_w3_M1[pointer_M1] ++		
					ENDIF	
				BREAK

				// check to see if triad is playing landed anim
				CASE 1
					IF IS_CHAR_PLAYING_ANIM triad_w3_M1[pointer_M1] PARA_land
						triad_status_w3_M1[pointer_M1] ++
					ENDIF
				BREAK

				// check to see if triad has stopped playing landed anim
				CASE 2
					IF NOT IS_CHAR_PLAYING_ANIM triad_w3_M1[pointer_M1] PARA_land
						triad_landed_w3_M1 ++
						triad_status_w3_M1[pointer_M1] = pointer_M1 + 3		
					ENDIF
				BREAK
				
				CASE 3
					OPEN_SEQUENCE_TASK sequence_M1
						TASK_GO_TO_COORD_ANY_MEANS -1 1279.6809 -786.5801 95.4531 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 86.7391
						TASK_TOGGLE_DUCK -1 FALSE
						TASK_STAY_IN_SAME_PLACE -1 TRUE
						TASK_SET_CHAR_DECISION_MAKER -1 cover_dm_M1
					CLOSE_SEQUENCE_TASK sequence_M1	
					PERFORM_SEQUENCE_TASK triad_W3_M1[0] sequence_M1
					CLEAR_SEQUENCE_TASK sequence_M1
					triad_status_w3_M1[pointer_M1] = 6
				BREAK

				CASE 4
					OPEN_SEQUENCE_TASK sequence_M1
						TASK_GO_TO_COORD_ANY_MEANS -1 1289.5647 -775.1545 95.4476 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 76.6970
						TASK_TOGGLE_DUCK -1 FALSE
						TASK_STAY_IN_SAME_PLACE -1 TRUE
						TASK_SET_CHAR_DECISION_MAKER -1 cover_dm_M1
					CLOSE_SEQUENCE_TASK sequence_M1	
					PERFORM_SEQUENCE_TASK triad_W3_M1[1] sequence_M1
					CLEAR_SEQUENCE_TASK sequence_M1
					triad_status_w3_M1[pointer_M1] = 6
				BREAK

				CASE 5
					OPEN_SEQUENCE_TASK sequence_M1
						TASK_GO_TO_COORD_ANY_MEANS -1 1281.6827 -779.4835 95.4531 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 84.4451
						TASK_TOGGLE_DUCK -1 FALSE
						TASK_STAY_IN_SAME_PLACE -1 TRUE
						TASK_SET_CHAR_DECISION_MAKER -1 cover_dm_M1
					CLOSE_SEQUENCE_TASK sequence_M1	
					PERFORM_SEQUENCE_TASK triad_W3_M1[2] sequence_M1
					CLEAR_SEQUENCE_TASK sequence_M1
					triad_status_w3_M1[pointer_M1] = 6
				BREAK

			ENDSWITCH
		ELSE
			fail_text_flag_M1 = 1
			$fail_text_M1 = MAN1_11
		ENDIF

		pointer_M1 ++
	ENDWHILE

RETURN

// ###############################################################################################################

mission_mansion1_SUB_add_w3_to_player_group:

	pointer_M1 = 0
	
	WHILE pointer_M1 < 3

		IF NOT IS_CHAR_DEAD triad_W3_M1[pointer_M1] 
			CLEAR_CHAR_TASKS_IMMEDIATELY triad_w3_M1[pointer_M1]
			TASK_STAY_IN_SAME_PLACE triad_w3_M1[pointer_M1] FALSE
			SET_GROUP_MEMBER players_group_M1 triad_W3_M1[pointer_M1]
			SET_CHAR_NEVER_LEAVES_GROUP triad_W3_M1[pointer_M1] TRUE
		ENDIF
		pointer_M1 ++
	ENDWHILE

RETURN

// ###############################################################################################################

mission_mansion1_SUB_remove_w3_from_player_group:

	pointer_M1 = 0
	
	WHILE pointer_M1 < 3

		IF NOT IS_CHAR_DEAD triad_W3_M1[pointer_M1] 
			CLEAR_CHAR_TASKS triad_w3_M1[pointer_M1]
			TASK_STAY_IN_SAME_PLACE triad_w3_M1[pointer_M1] FALSE
			REMOVE_CHAR_FROM_GROUP triad_W3_M1[pointer_M1]
		ENDIF
		pointer_M1 ++
	ENDWHILE	

RETURN

// ###############################################################################################################

LVAR_INT lsv_outside_M1[6] lsv_status_M1[6] lsv_model_M1[6] lsv_timer_M1[6] lsv_jump_box_M1[6]
LVAR_FLOAT lsv_X_M1 lsv_Y_M1 lsv_Z_M1 lsv_heading_M1
LVAR_INT random_M1 random_pointer_M1
LVAR_INT roof_lsv_active_M1

mission_mansion1_SUB_create_lsv_on_roof:
	
	CREATE_CHAR PEDTYPE_MISSION2 LSV1 1268.0691 -783.4940 94.9610 lsv_outside_M1[0]
	SET_CHAR_HEADING lsv_outside_M1[0] 275.8136
	SET_CHAR_HEALTH lsv_outside_M1[0] 200	
	SET_CHAR_MAX_HEALTH lsv_outside_M1[0] 200
	SET_CHAR_DECISION_MAKER lsv_outside_M1[0] empty_dm_M1
   	GIVE_WEAPON_TO_CHAR lsv_outside_M1[0] WEAPONTYPE_MP5 30000
	SET_CURRENT_CHAR_WEAPON lsv_outside_M1[0] WEAPONTYPE_MP5
	SET_CHAR_PROOFS lsv_outside_M1[0] TRUE FALSE FALSE FALSE FALSE							
	SET_CHAR_IS_TARGET_PRIORITY lsv_outside_M1[0] TRUE
	SET_CHAR_SIGNAL_AFTER_KILL lsv_outside_M1[0] FALSE
	lsv_status_M1[0] = -2

	CREATE_CHAR PEDTYPE_MISSION2 LSV2 1271.1556 -780.8453 94.9663 lsv_outside_M1[1]
	SET_CHAR_HEADING lsv_outside_M1[1] 254.8986
	SET_CHAR_HEALTH lsv_outside_M1[1] 200	
	SET_CHAR_MAX_HEALTH lsv_outside_M1[1] 200
   	SET_CHAR_DECISION_MAKER lsv_outside_M1[1] empty_dm_M1
   	GIVE_WEAPON_TO_CHAR lsv_outside_M1[1] WEAPONTYPE_MP5 30000
	SET_CURRENT_CHAR_WEAPON lsv_outside_M1[1] WEAPONTYPE_MP5
	SET_CHAR_PROOFS lsv_outside_M1[1] TRUE FALSE FALSE FALSE FALSE
	SET_CHAR_IS_TARGET_PRIORITY lsv_outside_M1[1] TRUE
	SET_CHAR_SIGNAL_AFTER_KILL lsv_outside_M1[1] FALSE
	lsv_status_M1[1] = -2

	CREATE_CHAR PEDTYPE_MISSION2 LSV1 1267.7373 -777.1963 94.9577 lsv_outside_M1[2]
	SET_CHAR_HEADING lsv_outside_M1[2] 302.1641
	SET_CHAR_HEALTH lsv_outside_M1[2] 200	
	SET_CHAR_MAX_HEALTH lsv_outside_M1[2] 200
	SET_CHAR_DECISION_MAKER lsv_outside_M1[2] empty_dm_M1
   	GIVE_WEAPON_TO_CHAR lsv_outside_M1[2] WEAPONTYPE_MP5 30000
	SET_CURRENT_CHAR_WEAPON lsv_outside_M1[2] WEAPONTYPE_MP5
	SET_CHAR_IS_TARGET_PRIORITY lsv_outside_M1[2] TRUE
	SET_CHAR_SIGNAL_AFTER_KILL lsv_outside_M1[2] FALSE
	lsv_status_M1[2] = -2

	CREATE_CHAR PEDTYPE_MISSION2 LSV2 1272.5341 -774.9141 94.9466 lsv_outside_M1[3]
	SET_CHAR_HEADING lsv_outside_M1[3] 249.0140
	SET_CHAR_HEALTH lsv_outside_M1[3] 200	
	SET_CHAR_MAX_HEALTH lsv_outside_M1[3] 200
   	SET_CHAR_DECISION_MAKER lsv_outside_M1[3] empty_dm_M1
   	GIVE_WEAPON_TO_CHAR lsv_outside_M1[3] WEAPONTYPE_MP5 30000
	SET_CURRENT_CHAR_WEAPON lsv_outside_M1[3] WEAPONTYPE_MP5
	SET_CHAR_IS_TARGET_PRIORITY lsv_outside_M1[3] TRUE
	SET_CHAR_SIGNAL_AFTER_KILL lsv_outside_M1[3] FALSE
	lsv_status_M1[3] = -2

	IF player_status_M1 < 2
		SET_CHAR_PROOFS lsv_outside_M1[0] TRUE FALSE FALSE FALSE FALSE
		SET_CHAR_PROOFS lsv_outside_M1[1] TRUE FALSE FALSE FALSE FALSE
		SET_CHAR_PROOFS lsv_outside_M1[2] TRUE FALSE FALSE FALSE FALSE
		SET_CHAR_PROOFS lsv_outside_M1[3] TRUE FALSE FALSE FALSE FALSE
	ENDIF

	TIMERB = 3000
	roof_lsv_active_M1 = 1

RETURN

// ###############################################################################################################

mission_masnion1_SUB_lsv_attack:
	IF NOT IS_CHAR_DEAD lsv_outside_M1[0]
		SET_CHAR_DECISION_MAKER lsv_outside_M1[0] cover_dm_M1
		SET_CHAR_PROOFS lsv_outside_M1[0] FALSE FALSE FALSE FALSE FALSE
	ENDIF
	IF NOT IS_CHAR_DEAD lsv_outside_M1[1]
		SET_CHAR_DECISION_MAKER lsv_outside_M1[1] cover_dm_M1
		SET_CHAR_PROOFS lsv_outside_M1[1] FALSE FALSE FALSE FALSE FALSE
	ENDIF
	IF NOT IS_CHAR_DEAD lsv_outside_M1[2]
		SET_CHAR_DECISION_MAKER lsv_outside_M1[2] cover_dm_M1
		SET_CHAR_PROOFS lsv_outside_M1[2] FALSE FALSE FALSE FALSE FALSE
	ENDIF
	IF NOT IS_CHAR_DEAD lsv_outside_M1[3]
		SET_CHAR_DECISION_MAKER lsv_outside_M1[3] cover_dm_M1
		SET_CHAR_PROOFS lsv_outside_M1[3] FALSE FALSE FALSE FALSE FALSE
	ENDIF
RETURN

// ###############################################################################################################

mission_mansion1_SUB_lsv_climbers:

	pointer_M1 = 0
	WHILE pointer_M1 < 4

		IF NOT IS_CHAR_DEAD lsv_outside_M1[pointer_M1]
		
			SWITCH lsv_status_M1[pointer_M1]

				CASE 0
					IF lsv_timer_M1[pointer_M1] > 100
						DELETE_OBJECT lsv_jump_box_M1[pointer_M1]
						lsv_status_M1[pointer_M1] ++
					ENDIF
				BREAK

				CASE 1
					GET_SEQUENCE_PROGRESS lsv_outside_M1[pointer_M1] sequence_progress_M1
					IF sequence_progress_M1 >= 2
						lsv_status_M1[pointer_M1] ++
					ENDIF
				BREAK

				CASE 2
					GET_CHAR_COORDINATES lsv_outside_M1[pointer_M1] x y z
					lsv_status_M1[pointer_M1] ++ 
				BREAK

			ENDSWITCH

		ELSE
		
			IF NOT lsv_status_M1[pointer_M1] = -1
				lsv_timer_M1[pointer_M1] = 0
				lsv_status_M1[pointer_M1] = -1
			ELSE
				IF lsv_timer_M1[pointer_M1] > 2000

					REMOVE_CHAR_ELEGANTLY lsv_outside_M1[pointer_M1]
					 
					IF roof_lsv_active_M1 < 3
						// find out where player is so ped can respawn
						location_status_M1 = 0
						GOSUB mission_mansion1_SUB_ped_location

					
						IF NOT player_location_M1 = -1
						OR NOT rough_player_location_M1 = 0

							// get random hidden position to spawn at
							random_pointer_M1 = pointer_M1
							IF player_location_M1 = -1
								IF NOT rough_player_location_M1 = 0	
									GOSUB mission_mansion1_SUB_rough_roof_position
								ENDIF
							ELSE	
								GOSUB mission_mansion1_SUB_random_roof_position
							ENDIF

							// box platform that the gang will jump from
							CREATE_OBJECT cardboardbox2 lsv_X_M1 lsv_Y_M1 93.0 lsv_jump_box_M1[pointer_M1]
							SET_OBJECT_DYNAMIC lsv_jump_box_M1[pointer_M1] FALSE
							SET_OBJECT_VISIBLE lsv_jump_box_M1[pointer_M1] FALSE
							FREEZE_OBJECT_POSITION lsv_jump_box_M1[pointer_M1] TRUE

						   	CREATE_CHAR PEDTYPE_MISSION2 lsv_model_M1[pointer_M1] lsv_X_M1 lsv_Y_M1 lsv_Z_M1 lsv_outside_M1[pointer_M1] 
						   	SET_CHAR_IS_TARGET_PRIORITY lsv_outside_M1[pointer_M1] TRUE
							
							SET_CHAR_HEALTH lsv_outside_M1[pointer_M1] 200	
							SET_CHAR_MAX_HEALTH lsv_outside_M1[pointer_M1] 200

						   	SET_CHAR_HEADING lsv_outside_M1[pointer_M1] lsv_heading_M1
						   	SET_CHAR_DECISION_MAKER lsv_outside_M1[pointer_M1] empty_dm_M1
						   	GIVE_WEAPON_TO_CHAR lsv_outside_M1[pointer_M1] WEAPONTYPE_MP5 30000
							SET_CURRENT_CHAR_WEAPON lsv_outside_M1[pointer_M1] WEAPONTYPE_MP5
						
							SET_CHAR_ACCURACY lsv_outside_M1[pointer_M1] 70
							SET_CHAR_SHOOT_RATE lsv_outside_M1[pointer_M1] 55
							SET_CHAR_SIGNAL_AFTER_KILL lsv_outside_M1[pointer_M1] FALSE
							
							IF pointer_M1 < 4
								IF NOT player_location_M1 = -1
								OR rough_player_location_M1 > 0
									GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS lsv_outside_M1[pointer_M1] 0.0 3.5 0.0 x y z 
									
									OPEN_SEQUENCE_TASK sequence_M1
										TASK_CLIMB -1 TRUE
										TASK_TOGGLE_DUCK -1 TRUE
										TASK_GO_TO_COORD_ANY_MEANS -1 x y 94.95 PEDMOVE_RUN -1
										TASK_STAY_IN_SAME_PLACE -1 FALSE
										TASK_SET_CHAR_DECISION_MAKER -1 cover_dm_M1
									CLOSE_SEQUENCE_TASK sequence_M1	
									PERFORM_SEQUENCE_TASK lsv_outside_M1[pointer_M1] sequence_M1
									CLEAR_SEQUENCE_TASK sequence_M1
									lsv_timer_M1[pointer_M1] = 0
									lsv_status_M1[pointer_M1] = 0 // jumping onto the roof
								ELSE
									TASK_STAY_IN_SAME_PLACE lsv_outside_M1[pointer_M1] TRUE
									TASK_SET_CHAR_DECISION_MAKER lsv_outside_M1[pointer_M1] cover_dm_M1
									lsv_timer_M1[pointer_M1] = 0
									lsv_status_M1[pointer_M1] = 3 
								ENDIF
							ELSE
								// task kill player
							ENDIF
						ENDIF
					ENDIF
	 			ENDIF
			ENDIF
		ENDIF

		pointer_M1 ++

	ENDWHILE
	
RETURN

// ###############################################################################################################


 

// chooses a random position for the lsv to spawn at ( the random places will depend on the location of the player)
mission_mansion1_SUB_random_roof_position:

	SWITCH player_location_M1
		
		CASE 0
			GENERATE_RANDOM_INT_IN_RANGE 0 5 random_M1
		BREAK
		CASE 1
		CASE 10
			GENERATE_RANDOM_INT_IN_RANGE 2 5 random_M1
		BREAK
		CASE 2
		CASE 9
			GENERATE_RANDOM_INT_IN_RANGE 3 5 random_M1
		BREAK
		CASE 3
		CASE 6
			GENERATE_RANDOM_INT_IN_RANGE 0 3 random_M1
		BREAK

		CASE 4
		CASE 7
			GENERATE_RANDOM_INT_IN_RANGE 0 2 random_M1
		BREAK

		CASE 5
		CASE 8
			GENERATE_RANDOM_INT_IN_RANGE 0 4 random_M1
			IF random_M1 > 1
				random_M1 ++
			ENDIF
		BREAK

		DEFAULT
			random_M1 = 5 
		BREAK

	ENDSWITCH
	
	IF random_M1 < 5
		// spawn on side
		random_pointer_M1 *= 5
		random_M1 += random_pointer_M1

		lsv_Z_M1 = 94.0
	ELSE
		// spawn on roof
		random_M1 *= 4
		random_M1 += random_pointer_M1
		lsv_Z_M1 = 95.0	
	ENDIF 

	GOSUB mission_mansion1_SUB_spawn_locations
RETURN

// ###############################################################################################################

mission_mansion1_SUB_rough_roof_position:

	SWITCH rough_player_location_M1
		
		CASE 1
			GENERATE_RANDOM_INT_IN_RANGE 0 5 random_M1	
		BREAK
			
		CASE 2
			GENERATE_RANDOM_INT_IN_RANGE 3 5 random_M1
		BREAK	
			
		CASE 3
			GENERATE_RANDOM_INT_IN_RANGE 2 5 random_M1
		BREAK	
			
		CASE 4	
			GENERATE_RANDOM_INT_IN_RANGE 0 2 random_M1
		BREAK
		
		CASE 5	
			GENERATE_RANDOM_INT_IN_RANGE 0 3 random_M1
		BREAK

		CASE -1
			random_M1 = 5 
		BREAK

	ENDSWITCH
	
	IF random_M1 < 5
		// spawn on side
		random_pointer_M1 *= 5
		random_M1 += random_pointer_M1
		lsv_Z_M1 = 94.0
	ELSE
		// spawn on roof
		random_M1 *= 4
		random_M1 += random_pointer_M1
		lsv_Z_M1 = 95.0	
	ENDIF 

	GOSUB mission_mansion1_SUB_spawn_locations

RETURN

// ###############################################################################################################

mission_mansion1_SUB_spawn_locations:

	SWITCH random_M1
		CASE 0
			lsv_X_M1 = 1262.0
			lsv_Y_M1 = -770.5
			lsv_heading_M1 = 180.0
		BREAK	
		CASE 1
			lsv_X_M1 = 1266.0
			lsv_Y_M1 = -770.5
			lsv_heading_M1 = 180.0
		BREAK
		CASE 2
			lsv_X_M1 = 1257.0
			lsv_Y_M1 = -784.0
			lsv_heading_M1 = 270.0
		BREAK	
		CASE 3
			lsv_X_M1 = 1269.0
			lsv_Y_M1 = -789.5
			lsv_heading_M1 = 0.0
		BREAK
		CASE 4
			lsv_X_M1 = 1265.0
			lsv_Y_M1 = -789.5
			lsv_heading_M1 = 0.0
		BREAK		
		CASE 5
			lsv_X_M1 = 1267.0
			lsv_Y_M1 = -770.5
			lsv_heading_M1 = 180.0
		BREAK	
		CASE 6
			lsv_X_M1 = 1263.0
			lsv_Y_M1 = -770.5
			lsv_heading_M1 = 180.0
		BREAK
		CASE 7
			lsv_X_M1 = 1257.0
			lsv_Y_M1 = -781.0
			lsv_heading_M1 = 270.0
		BREAK	
		CASE 8
			lsv_X_M1 = 1264.0
			lsv_Y_M1 = -789.5
			lsv_heading_M1 = 0.0
		BREAK
		CASE 9
			lsv_X_M1 = 1268.0
			lsv_Y_M1 = -789.5
			lsv_heading_M1 = 0.0
		BREAK
		CASE 10
			lsv_X_M1 = 1264.0
			lsv_Y_M1 = -770.5
			lsv_heading_M1 = 180.0
		BREAK
		CASE 11 
			lsv_X_M1 = 1268.0
			lsv_Y_M1 = -770.5
			lsv_heading_M1 = 180.0
		BREAK
		CASE 12 
			lsv_X_M1 = 1257.0
			lsv_Y_M1 = -778.0
			lsv_heading_M1 = 270.0
		BREAK
		CASE 13 
			lsv_X_M1 = 1267.0
			lsv_Y_M1 = -789.5
			lsv_heading_M1 = 0.0 
		BREAK
		CASE 14
			lsv_X_M1 = 1263.0
			lsv_Y_M1 = -789.5
			lsv_heading_M1 = 0.0 
		BREAK
		CASE 15
			lsv_X_M1 = 1269.0
			lsv_Y_M1 = -770.5
			lsv_heading_M1 = 180.0
		BREAK
		CASE 16 
			lsv_X_M1 = 1265.0
			lsv_Y_M1 = -770.5
			lsv_heading_M1 = 180.0
		BREAK	
		CASE 17 
			lsv_X_M1 = 1257.0
			lsv_Y_M1 = -775.0
			lsv_heading_M1 = 270.0
		BREAK	
		CASE 18 
			lsv_X_M1 = 1262.0
			lsv_Y_M1 = -789.5
			lsv_heading_M1 = 0.0 
		BREAK	
		CASE 19
			lsv_X_M1 = 1266.0
			lsv_Y_M1 = -789.5
			lsv_heading_M1 = 0.0 
		BREAK
		// on roof spawn areas
		CASE 20
			lsv_X_M1 = 1268.0691
			lsv_Y_M1 = -783.4940
			lsv_heading_M1 = 270.0
		BREAK
		CASE 21
			lsv_X_M1 = 1271.1556
			lsv_Y_M1 = -780.8453
			lsv_heading_M1 = 270.0
		BREAK
		CASE 22
			lsv_X_M1 = 1267.7373
			lsv_Y_M1 = -777.1963
			lsv_heading_M1 = 270.0
		BREAK
		CASE 23
			lsv_X_M1 = 1272.5341
			lsv_Y_M1 = -774.9141
			lsv_heading_M1 = 270.0
		BREAK
	ENDSWITCH

RETURN

// ###############################################################################################################

LVAR_INT location_M1 location_status_M1	location_Z_status_M1 
LVAR_INT player_location_M1 checked_player_location_M1 rough_player_location_M1 
LVAR_INT lsv_location_M1 lsv_id_M1
LVAR_FLOAT locate_X_M1 locate_Y_M1 locate_Z_M1

// makes bullets fly past the player
mission_mansion1_SUB_flying_bullets:

	IF TIMERB > 700

		GET_CHAR_COORDINATES scplayer player_x player_y player_z
		GET_DISTANCE_BETWEEN_COORDS_2D 1269.6676 -778.8566 player_x player_y distance
		IF player_z > 105.0
		AND distance > 65.0
		AND distance < 130.0

			GENERATE_RANDOM_FLOAT_IN_RANGE -6.0 6.1 x
			IF x > 0.0
				x += 1.5
			ELSE
				x -= 1.5
			ENDIF
			GENERATE_RANDOM_FLOAT_IN_RANGE -6.0 6.1 z
			IF z > 0.0
				z += 1.5
			ELSE
				z -= 1.5
			ENDIF		  
			
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer x -10.0 z player_x player_y player_z 
			FIRE_SINGLE_BULLET 1269.6676 -778.8566 94.9557 player_x player_y player_z 0
		ENDIF
		
		GENERATE_RANDOM_INT_IN_RANGE 100 401 random_M1

		TIMERB = random_M1 
	ENDIF

RETURN



// script is used for finding the location of a ped on the mansion
mission_mansion1_SUB_ped_location:
	
	IF location_status_M1 = 0

		IF checked_player_location_M1 = 0
			player_location_M1 = -1	
			rough_player_location_M1 = 0
			GET_CHAR_COORDINATES scplayer locate_X_M1 locate_Y_M1 locate_Z_M1			 
		ELSE
			RETURN // player has already been checked this frame so script knows where he is
		ENDIF
		 
	ELSE
		lsv_location_M1 = -1
		GET_CHAR_COORDINATES lsv_outside_M1[lsv_id_M1] locate_X_M1 locate_Y_M1 locate_Z_M1
	ENDIF 

	
	IF locate_Z_M1 > 94.5
		location_Z_status_M1 = 0
	ELSE
		IF locate_Z_M1 > 90.5
			location_Z_status_M1 = 1
		ELSE
			IF locate_Z_M1 > 86.0
				location_Z_status_M1 = 2
			ELSE
				location_Z_status_M1 = 3
			ENDIF
		ENDIF
	ENDIF

	SWITCH location_Z_status_M1
	 
		// mansion roof
		CASE 0
			IF locate_Y_M1 > -802.2
			AND locate_Y_M1 < -766.75
			AND locate_X_M1 > 1257.9
			AND locate_X_M1 < 1304.4
			
				IF locate_Y_M1 > -774.2
					IF locate_X_M1 < 1262.7
						location_M1 = 2	
					ELSE
						location_M1 = 1
					ENDIF
				ELSE
					IF locate_Y_M1 < -785.85
						IF locate_X_M1 < 1262.7
							location_M1 = 4
						ELSE
							location_M1 = 3
						ENDIF
					ELSE
						IF locate_X_M1 < 1262.7
							location_M1 = 5
						ELSE
							location_M1 = 0
						ENDIF
					ENDIF
				ENDIF
			ELSE
				location_M1 = -1 // not on mansion
			ENDIF
		BREAK
		
		// balcony
		CASE 1			
			IF locate_Y_M1 > -793.0
			AND locate_Y_M1 < -767.6
			AND locate_X_M1 > 1251.4
			AND locate_X_M1 < 1295.7
			
				IF locate_Y_M1 > -776.25
					IF locate_X_M1 < 1265.9
						location_M1 = 9	
					ELSE
						location_M1 = 10
					ENDIF
				ELSE
					IF locate_Y_M1 < -783.5
						IF locate_X_M1 < 1265.9
							location_M1 = 7	
						ELSE
							location_M1 = 6
						ENDIF
					ELSE
						location_M1 = 8
					ENDIF
				ENDIF
					
			ELSE
				location_M1 = -1 // not on mansion
			ENDIF
		BREAK
														 
		// swimming pool area
		CASE 2
			IF locate_Y_M1 > -814.0 
			AND locate_Y_M1 < -785.0
			AND locate_X_M1 > 1251.8
			AND locate_X_M1 < 1303.5
			
				IF locate_Y_M1 > -793.6 
					IF locate_X_M1 > 1298.8
						location_M1 = 13
					ELSE
						IF locate_X_M1 > 1259.4
							location_M1 = 12
						ELSE
							location_M1 = 11
						ENDIF
					ENDIF 
				ELSE
					IF locate_Y_M1 > -807.1
						IF locate_X_M1 > 1298.8
							location_M1 = 16
						ELSE
							IF locate_X_M1 > 1259.4
								location_M1 = 15
							ELSE
								location_M1 = 14
							ENDIF
						ENDIF
					ELSE
						IF locate_X_M1 > 1298.8
							location_M1 = 19
						ELSE
							IF locate_X_M1 > 1259.4
								location_M1 = 18
							ELSE
								location_M1 = 17
							ENDIF
						ENDIF
					ENDIF
				ENDIF

			ELSE
				location_M1 = -1 // not on mansion
			ENDIF
		BREAK

		// garage area
		CASE 3
		 	IF locate_Y_M1 > -834.35
			AND locate_Y_M1 < -787.3
			AND locate_X_M1 > 1239.9
			AND locate_X_M1 < 1308.4
			
				IF locate_Y_M1 < -804.2511
					IF locate_X_M1 < 1254.75 
						location_M1 = 23
					ELSE
						IF locate_X_M1 < 1298.6
							location_M1 = 24
						ELSE
							location_M1 = 25
						ENDIF
					ENDIF
				ELSE
					IF locate_X_M1 < 1254.75 
						location_M1 = 20
					ELSE
						IF locate_X_M1 < 1298.6
							location_M1 = 21
						ELSE
							location_M1 = 22
						ENDIF
					ENDIF
				ENDIF

			ELSE
				location_M1 = -1 // not on mansion
			ENDIF
		BREAK

	ENDSWITCH

	// get a rough idea where he might be
	IF location_M1 = -1

		IF locate_Z_M1 > 91.5
			
			rough_player_location_M1 = 0

			IF locate_X_M1 > 1279.3507
				rough_player_location_M1 += 1
			ENDIF	
			IF locate_Y_M1 > -766.75
				rough_player_location_M1 += 2
			ENDIF
			IF locate_Y_M1 < -802.2
				rough_player_location_M1 += 4
			ENDIF
			
		ELSE
			rough_player_location_M1 = -1 // just spawn on the roof?
		ENDIF 
	ENDIF

	IF location_status_M1 = 0 // player
		player_location_M1 = location_M1
		checked_player_location_M1 = 1
	ELSE
		lsv_location_M1 = location_M1	
	ENDIF

RETURN

// ###############################################################################################################

LVAR_INT camera_status_M1
mission_mansion1_SUB_rotate_camera:

	SWITCH camera_status_M1
		CASE 1
			IF cam_angle_M1 < 190.0
				cam_angle_M1 += 0.34
				cam_Z_M1 -= 0.006
				
				cam_L_Y_M1 += 0.01
				cam_radius_M1 += 0.01
			ENDIF		
		BREAK
	ENDSWITCH	
	
	
	COS cam_angle_M1 cos_angle_M1
	SIN cam_angle_M1 sin_angle_M1

	new_x_M1 = cam_radius_M1 * cos_angle_M1 
	new_y_M1 = cam_radius_M1 * sin_angle_M1

	cam_X_M1 = cam_L_X_M1 + new_x_M1
	cam_Y_M1 = cam_L_Y_M1 + new_y_M1

	SET_FIXED_CAMERA_POSITION cam_X_M1 cam_Y_M1 cam_Z_M1 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT cam_L_X_M1 cam_L_Y_M1 cam_L_Z_M1 JUMP_CUT	

RETURN

// ###############################################################################################################
// opens the cargo door of the plane
LVAR_INT cargo_door_status_M1
LVAR_FLOAT cargo_door_X_M1 
mission_mansion1_SUB_open_cargo_door:
	
	SWITCH cargo_door_status_M1
		CASE 0
			REPORT_MISSION_AUDIO_EVENT_AT_OBJECT cargo_ramp_M1 SOUND_CARGO_PLANE_DOOR_START
			cargo_door_status_M1 ++
		CASE 1	
			IF cargo_door_X_M1 > -20.0
				cargo_door_X_M1 += -0.1
				SET_OBJECT_ROTATION cargo_ramp_M1 cargo_door_X_M1 0.0 90.0
			ELSE
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT cargo_ramp_M1 SOUND_CARGO_PLANE_DOOR_STOP
				cargo_door_status_M1 ++
			ENDIF
	ENDSWITCH
RETURN


// ###############################################################################################################

// plane turbulence for intro cutscene
LVAR_INT turbulence_status_M1 turbulence_length_M1 turbulence_limit_M1 turbulence_strength_M1 rumble_strength_M1
mission_mansion1_plane_turbulence:

	SWITCH turbulence_status_M1
	 
		CASE 0
			IF TIMERB >= turbulence_length_M1
				GENERATE_RANDOM_INT_IN_RANGE 4000 6001 turbulence_limit_M1
				TIMERB = 0
				turbulence_status_M1 ++ 
			ELSE
				SHAKE_CAM turbulence_strength_M1
			ENDIF
		BREAK

		CASE 1
			IF TIMERB >= turbulence_limit_M1  
				GENERATE_RANDOM_INT_IN_RANGE 40 51 turbulence_strength_M1 
				SHAKE_CAM turbulence_strength_M1
				GENERATE_RANDOM_INT_IN_RANGE 2500 5001 turbulence_length_M1
				rumble_strength_M1 = turbulence_strength_M1 * 2
				rumble_strength_M1 += 150 
				SHAKE_PAD PAD1 turbulence_length_M1 rumble_strength_M1
				TIMERB = 0
				turbulence_status_M1 --
			ELSE
				SHAKE_CAM 5
			ENDIF
		BREAK

		CASE 2
			SHAKE_CAM 20
		BREAK

	ENDSWITCH

RETURN

// ###############################################################################################################

mission_mansion1_SUB_cleanup_outside:
	
	KILL_FX_SYSTEM signal_flare_M1[0]
	KILL_FX_SYSTEM signal_flare_M1[1]
	KILL_FX_SYSTEM signal_flare_M1[2]

	REMOVE_BLIP goto_blip_M1
	REMOVE_BLIP triad_w3_blip_M1[0]
	REMOVE_BLIP triad_w3_blip_M1[1]
	REMOVE_BLIP triad_w3_blip_M1[2]

	REMOVE_PICKUP health_pickup_M1

	pointer_M1 = 0
	WHILE pointer_M1 < 4
		MARK_CHAR_AS_NO_LONGER_NEEDED triad_w1_M1[pointer_M1]
		MARK_CHAR_AS_NO_LONGER_NEEDED triad_w2_M1[pointer_M1]
		MARK_CHAR_AS_NO_LONGER_NEEDED lsv_outside_M1[pointer_M1]
		pointer_M1 ++
	ENDWHILE

	MARK_CHAR_AS_NO_LONGER_NEEDED lsv_guard_M1
	
	MARK_MODEL_AS_NO_LONGER_NEEDED PARACHUTE
	MARK_MODEL_AS_NO_LONGER_NEEDED PARA_PACK
   	MARK_MODEL_AS_NO_LONGER_NEEDED GUN_PARA
	REMOVE_ANIMATION PARACHUTE

	MARK_MODEL_AS_NO_LONGER_NEEDED CARGO_REAR
	MARK_MODEL_AS_NO_LONGER_NEEDED D9_RAMP
	MARK_MODEL_AS_NO_LONGER_NEEDED CARDBOARDBOX2

	MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
RETURN



























		  


































































































































































// ##########################################################################################################
// ##
// ## INSIDE MANSION
// ##
// ##########################################################################################################

LVAR_INT lsv_inside_M1[20] lsv_inside_status_M1[20] lsv_inside_timer_M1[20] 
LVAR_INT lsv_room_M1[10] lsv_room_status_M1[10] lsv_room_timer_M1[10]
LVAR_INT lsv_girl_M1[4] lsv_girl_status_M1[4] lsv_girl_timer_M1[4]

LVAR_INT big_poppa_M1 big_poppa_blip_M1 big_poppa_active_M1

LVAR_INT lsv_spawn_sound_M1[4] lsv_spawn_sample_M1
LVAR_TEXT_LABEL lsv_spawn_text_M1[4] 

mission_mansion1_MAIN_inside:
	
	// remove triads from the players group
	GOSUB mission_mansion1_SUB_remove_w3_from_player_group
	// cleanup stuff from outside (mainly to free up memory)
	GOSUB mission_mansion1_SUB_cleanup_outside	

	REMOVE_DECISION_MAKER players_group_dm_M1

	// stop player getting out the mansion until the mission is passed
	DISABLE_ALL_ENTRY_EXITS TRUE

	LOAD_SCENE 1267.4294 -781.5715 1090.8906
	
	// people in bar once chasing big poppa
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY bar_dm_M1
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE bar_dm_M1 EVENT_DAMAGE TASK_COMPLEX_SMART_FLEE_ENTITY 0.0 100.0 100.0 0.0 FALSE TRUE
  	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE bar_dm_M1 EVENT_SHOT_FIRED TASK_SIMPLE_DUCK 0.0 100.0 100.0 0.0 FALSE TRUE
  	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE bar_dm_M1 EVENT_GUN_AIMED_AT TASK_SIMPLE_HANDS_UP 0.0 100.0 0.0 0.0 FALSE TRUE

	REQUEST_MODEL LSV3
	REQUEST_MODEL BFYST
	REQUEST_MODEL GANGRL3

	WHILE NOT HAS_MODEL_LOADED LSV3
	OR NOT HAS_MODEL_LOADED BFYST
	OR NOT HAS_MODEL_LOADED GANGRL3
		WAIT 0
	ENDWHILE
	
	CREATE_PICKUP HEALTH PICKUP_ONCE 1278.8804 -810.5082 1086.0 health_pickup_M1

	SET_FIXED_CAMERA_POSITION 1266.8071 -782.0668 1092.6021 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 1267.7859 -781.8895 1092.4996 JUMP_CUT
	SWITCH_WIDESCREEN ON

	// player
	SET_PLAYER_CONTROL player1 OFF
	SET_CHAR_COORDINATES scplayer 1267.7850 -781.2607 1090.8984 
	SET_CHAR_HEADING scplayer 185.3630
	player_status_M1 = 0

  

	// triads
	IF NOT IS_CHAR_DEAD triad_w3_M1[0]
		SET_CHAR_COORDINATES triad_w3_M1[0] 1263.0872 -782.9864 1090.8984
		REMOVE_BLIP triad_w3_blip_M1[0] 
	ELSE 
		CREATE_CHAR	PEDTYPE_MISSION1 TRIADA 1263.0872 -782.9864 1090.8984 triad_w3_M1[0]
		GIVE_WEAPON_TO_CHAR triad_w3_M1[0] WEAPONTYPE_M4 30000
		SET_CURRENT_CHAR_WEAPON triad_w3_M1[0] WEAPONTYPE_M4
		SET_CHAR_NEVER_TARGETTED triad_w3_M1[0] TRUE 
	ENDIF

	SET_CHAR_HEADING triad_w3_M1[0] 269.9343
	SET_CHAR_HEALTH triad_w3_M1[0] 300
	SET_CHAR_AREA_VISIBLE triad_w3_M1[0] 5
	SET_CHAR_DECISION_MAKER triad_w3_M1[0] empty_dm_M1
	SET_CHAR_SIGNAL_AFTER_KILL triad_w3_M1[0] FALSE
	SET_CHAR_ACCURACY triad_w3_M1[0] 75
	SET_CHAR_SHOOT_RATE triad_w3_M1[0] 60

	triad_model_w3_M1[0] = TRIADA
	triad_status_w3_M1[0] = 0


	IF NOT IS_CHAR_DEAD triad_w3_M1[1]
		SET_CHAR_COORDINATES triad_w3_M1[1] 1261.9670 -778.3527 1090.8984 
		REMOVE_BLIP triad_w3_blip_M1[1]
	ELSE 
		CREATE_CHAR	PEDTYPE_MISSION1 TRIADB 1261.9670 -778.3527 1090.8984 triad_w3_M1[1]
		GIVE_WEAPON_TO_CHAR triad_w3_M1[1] WEAPONTYPE_M4 30000
		SET_CURRENT_CHAR_WEAPON triad_w3_M1[1] WEAPONTYPE_M4
		SET_CHAR_NEVER_TARGETTED triad_w3_M1[1] TRUE 
	ENDIF

	SET_CHAR_HEADING triad_w3_M1[1] 258.1764
	SET_CHAR_HEALTH triad_w3_M1[1] 300
	SET_CHAR_AREA_VISIBLE triad_w3_M1[1] 5
	SET_CHAR_DECISION_MAKER triad_w3_M1[1] empty_dm_M1
	SET_CHAR_SIGNAL_AFTER_KILL triad_w3_M1[1] FALSE
	SET_CHAR_ACCURACY triad_w3_M1[1] 75
	SET_CHAR_SHOOT_RATE triad_w3_M1[1] 60
	triad_status_w3_M1[1] = 1

	triad_model_w3_M1[1] = TRIADB


	// triad who gets shot
	IF NOT IS_CHAR_DEAD triad_w3_M1[2]
		SET_CHAR_COORDINATES triad_w3_M1[2] 1267.9357 -782.3963 1090.9384 
		REMOVE_BLIP triad_w3_blip_M1[2]
	ELSE 
		CREATE_CHAR	PEDTYPE_MISSION1 TRIADB 1267.9357 -782.3963 1090.9384 triad_w3_M1[2]
		GIVE_WEAPON_TO_CHAR triad_w3_M1[2] WEAPONTYPE_M4 30000
		SET_CURRENT_CHAR_WEAPON triad_w3_M1[2] WEAPONTYPE_M4
		SET_CHAR_NEVER_TARGETTED triad_w3_M1[2] TRUE 
	ENDIF

	SET_CHAR_HEADING triad_w3_M1[2] 7.0205
	SET_CHAR_HEALTH triad_w3_M1[2] 300
	SET_CHAR_AREA_VISIBLE triad_w3_M1[2] 5
	SET_CHAR_DECISION_MAKER triad_w3_M1[2] empty_dm_M1


	pointer_M1 = 0
	WHILE pointer_M1 < 20
		lsv_inside_status_M1[pointer_M1] = -2
		pointer_M1 ++
	ENDWHILE

	pointer_M1 = 0
	WHILE pointer_M1 < 10
		lsv_room_status_M1[pointer_M1] = -2
		pointer_M1 ++
	ENDWHILE

	lsv_girl_status_M1[0] = -2
	lsv_girl_status_M1[1] = -2
	lsv_girl_status_M1[2] = -2
	lsv_girl_status_M1[3] = -2

LVAR_INT 	room_pointer_M1 cover_pointer_M1
LVAR_FLOAT 	triad_X_M1[16] triad_Y_M1[16] triad_Z_M1[16] triad_heading_M1[16]

	// variables		     
	// corridor
	triad_X_M1[0] 		= 1282.4808		// triad 0 corridor
	triad_Y_M1[0] 		= -783.0235
	triad_Z_M1[0] 		= 1088.9297
	triad_heading_M1[0]	= 178.1922
	triad_X_M1[1] 		= 1283.5040   	// triad 1 corridor     
	triad_Y_M1[1] 		= -782.3303
	triad_Z_M1[1] 		= 1088.9297
	triad_heading_M1[1]	= 179.0122
	
	// room
	room_pointer_M1 = 2 
	
	triad_X_M1[2] 		= 1280.7053 	// room 1              
	triad_Y_M1[2] 		= -789.9243
	triad_Z_M1[2] 		= 1088.9297
	triad_heading_M1[2]	= 86.8595
	triad_X_M1[3] 		= 1283.7900  	// room 2            
	triad_Y_M1[3] 		= -798.3011
	triad_Z_M1[3] 		= 1088.9297
	triad_heading_M1[3]	= 282.0571
	triad_X_M1[4] 		= 1280.7804 	// room 3          
	triad_Y_M1[4] 		= -801.7443
	triad_Z_M1[4] 		= 1088.9297
	triad_heading_M1[4]	= 94.8903
	triad_X_M1[5] 		= 1283.5311 	// room 4          
	triad_Y_M1[5] 		= -810.2070
	triad_Z_M1[5] 		= 1088.9303
	triad_heading_M1[5]	= 277.6203
	triad_X_M1[6] 		= 1280.7235  	// room 5         
	triad_Y_M1[6] 		= -813.7949
	triad_Z_M1[6] 		= 1088.9303
	triad_heading_M1[6]	= 92.4805
	triad_X_M1[7] 		= 1283.6414  	// room 6         
	triad_Y_M1[7] 		= -822.3738
	triad_Z_M1[7] 		= 1088.9303
	triad_heading_M1[7]	= 281.8632
	
	// cover
	cover_pointer_M1 = 8
	
	triad_X_M1[8] 		= 1283.4906		// room 1 cover poinr                
	triad_Y_M1[8] 		= -785.1840
	triad_Z_M1[8] 		= 1088.9297
	triad_heading_M1[8]	= 178.5884
	triad_X_M1[9] 		= 1281.0492		// room 2 cover point              
	triad_Y_M1[9] 		= -792.4401
	triad_Z_M1[9] 		= 1088.9297
	triad_heading_M1[9]	= 182.7310
	triad_X_M1[10] 		= 1283.6973		// room 3 cover point           
	triad_Y_M1[10] 		= -799.8134
	triad_Z_M1[10] 		= 1088.9297
	triad_heading_M1[10]= 177.1231
	triad_X_M1[11] 		= 1280.9006		// room 4 cover poinr           
	triad_Y_M1[11] 		= -804.8507
	triad_Z_M1[11] 		= 1088.9297
	triad_heading_M1[11]= 184.0353
	triad_X_M1[12] 		= 1283.5002		// room 5 cover point           
	triad_Y_M1[12] 		= -812.0164
	triad_Z_M1[12] 		= 1088.9297
	triad_heading_M1[12]= 175.5500
	triad_X_M1[13] 		= 1280.8479   	// room 6 cover point
	triad_Y_M1[13] 		= -816.8154
	triad_Z_M1[13] 		= 1088.9297
	triad_heading_M1[13]= 188.1912
	
	// balcony
	triad_X_M1[14] 		= 1283.6543		// balcony position 0        
	triad_Y_M1[14] 		= -831.0235
	triad_Z_M1[14] 		= 1088.9297
	triad_heading_M1[14]= 71.1923
	triad_X_M1[15] 		= 1282.4268  	// balcony position 1       
	triad_Y_M1[15] 		= -834.7235
	triad_Z_M1[15] 		= 1088.9297
	triad_heading_M1[15]= 92.1225


	// triad audio
	GENERATE_RANDOM_INT_IN_RANGE 0 4 clear_audio_pointer_M1

	// triad 0
	triad_clear_sound_M1[0]	= SOUND_MAN1_FC
	$triad_clear_text_M1[0]	= &MAN1_FC
	triad_clear_sound_M1[1]	= SOUND_MAN1_FD
	$triad_clear_text_M1[1]	= &MAN1_FD
	triad_clear_sound_M1[2]	= SOUND_MAN1_FE
	$triad_clear_text_M1[2]	= &MAN1_FE
	triad_clear_sound_M1[3]	= SOUND_MAN1_FF
	$triad_clear_text_M1[3]	= &MAN1_FF

	// triad 1
	triad_clear_sound_M1[4]	= SOUND_MAN1_FK
	$triad_clear_text_M1[4]	= &MAN1_FK
	triad_clear_sound_M1[5]	= SOUND_MAN1_FL
	$triad_clear_text_M1[5]	= &MAN1_FL
	triad_clear_sound_M1[6]	= SOUND_MAN1_FM
	$triad_clear_text_M1[6]	= &MAN1_FM
	triad_clear_sound_M1[7]	= SOUND_MAN1_FN
	$triad_clear_text_M1[7]	= &MAN1_FN
	
			
	// lsv
	// in plant
	CREATE_CHAR PEDTYPE_MISSION2 LSV1 1275.6401 -776.5879 1089.7109 lsv_inside_M1[0]  
	SET_CHAR_HEADING lsv_inside_M1[0] 127.9642
	SET_CHAR_HEALTH lsv_inside_M1[0] 200	
	SET_CHAR_MAX_HEALTH lsv_inside_M1[0] 200	
	SET_CHAR_AREA_VISIBLE lsv_inside_M1[0] 5
	GIVE_WEAPON_TO_CHAR lsv_inside_M1[0] WEAPONTYPE_MP5 30000
	SET_CURRENT_CHAR_WEAPON lsv_inside_M1[0] WEAPONTYPE_MP5
	SET_CHAR_ACCURACY lsv_inside_M1[0] 35
	SET_CHAR_SHOOT_RATE lsv_inside_M1[0] 50
	TASK_TOGGLE_DUCK lsv_inside_M1[0] TRUE
	SET_CHAR_DECISION_MAKER lsv_inside_M1[0] empty_dm_M1
	lsv_inside_status_M1[0] = 0

	// in plant
	CREATE_CHAR PEDTYPE_MISSION2 LSV3 1281.1083 -776.7111 1089.7039 lsv_inside_M1[1]  
	SET_CHAR_HEADING lsv_inside_M1[1] 99.3570
	SET_CHAR_HEALTH lsv_inside_M1[1] 200	
	SET_CHAR_MAX_HEALTH lsv_inside_M1[1] 200
	SET_CHAR_AREA_VISIBLE lsv_inside_M1[1] 5
	GIVE_WEAPON_TO_CHAR lsv_inside_M1[1] WEAPONTYPE_MP5 30000
	SET_CURRENT_CHAR_WEAPON lsv_inside_M1[1] WEAPONTYPE_MP5
	SET_CHAR_ACCURACY lsv_inside_M1[1] 35
	SET_CHAR_SHOOT_RATE lsv_inside_M1[1] 60
	TASK_TOGGLE_DUCK lsv_inside_M1[1] TRUE
	SET_CHAR_DECISION_MAKER lsv_inside_M1[1] empty_dm_M1	 
	lsv_inside_status_M1[1] = 0

	// rolls out
	CREATE_CHAR PEDTYPE_MISSION2 LSV1 1281.6173 -784.6133 1088.9297 lsv_inside_M1[2]  
	SET_CHAR_HEADING lsv_inside_M1[2] 90.0
	SET_CHAR_HEALTH lsv_inside_M1[2] 200	
	SET_CHAR_MAX_HEALTH lsv_inside_M1[2] 200
	SET_CHAR_AREA_VISIBLE lsv_inside_M1[2] 5
	GIVE_WEAPON_TO_CHAR lsv_inside_M1[2] WEAPONTYPE_MP5 30000
	SET_CURRENT_CHAR_WEAPON lsv_inside_M1[2] WEAPONTYPE_MP5
	SET_CHAR_ACCURACY lsv_inside_M1[2] 35
	SET_CHAR_SHOOT_RATE lsv_inside_M1[2] 70
	SET_CHAR_DECISION_MAKER lsv_inside_M1[2] empty_dm_M1	
	lsv_inside_status_M1[2] = 0

	// runs out
	CREATE_CHAR PEDTYPE_MISSION2 LSV3 1283.1903 -786.4687 1088.9297 lsv_inside_M1[3]  
	SET_CHAR_HEADING lsv_inside_M1[3] 0.0
	SET_CHAR_HEALTH lsv_inside_M1[3] 200	
	SET_CHAR_MAX_HEALTH lsv_inside_M1[3] 200
	SET_CHAR_AREA_VISIBLE lsv_inside_M1[3] 5
	GIVE_WEAPON_TO_CHAR lsv_inside_M1[3] WEAPONTYPE_MP5 30000
	SET_CURRENT_CHAR_WEAPON lsv_inside_M1[3] WEAPONTYPE_MP5
	SET_CHAR_ACCURACY lsv_inside_M1[3] 35
	SET_CHAR_SHOOT_RATE lsv_inside_M1[3] 50	 
	SET_CHAR_DECISION_MAKER lsv_inside_M1[3] empty_dm_M1
 	lsv_inside_status_M1[3] = 0

	// lsv spawn guys
	last_spawn_point_M1 = -1

	room_X_M1[0] 		= 1275.3822   
	room_Y_M1[0] 		= -782.1158
	room_Z_M1[0] 		= 1088.9297
	room_heading_M1[0] 	= 270.0
	room_X_M1[1] 		= 1290.6211
	room_Y_M1[1] 		= -797.5714
	room_Z_M1[1] 		= 1088.9297
	room_heading_M1[1] 	= 90.0
	room_X_M1[2] 		= 1276.3329   
	room_Y_M1[2] 		= -813.5852
	room_Z_M1[2] 		= 1088.9303
	room_heading_M1[2] 	= 270.0
	room_X_M1[3] 		= 1274.8613     
	room_Y_M1[3] 		= -825.8968
	room_Z_M1[3] 		= 1088.9297
	room_heading_M1[3] 	= 270.0
	room_X_M1[4] 		= 1226.3091     
	room_Y_M1[4] 		= -832.0841
	room_Z_M1[4] 		= 1083.0078
	room_heading_M1[4] 	= 180.0
	room_X_M1[5] 		= 1288.0243     
	room_Y_M1[5] 		= -821.6524
	room_Z_M1[5] 		= 1088.9297
	room_heading_M1[5] 	= 90.0
		
	// lsv spawn audio
	lsv_spawn_sound_M1[0]	= SOUND_MAN1_AA
	$lsv_spawn_text_M1[0]	= &MAN1_AA
	lsv_spawn_sound_M1[1]	= SOUND_MAN1_AB
	$lsv_spawn_text_M1[1]	= &MAN1_AB
	lsv_spawn_sound_M1[2]	= SOUND_MAN1_AC
	$lsv_spawn_text_M1[2]	= &MAN1_AC
	lsv_spawn_sound_M1[3]	= SOUND_MAN1_AD
	$lsv_spawn_text_M1[3]	= &MAN1_AD
	
	lsv_spawn_sample_M1 = 0

	big_poppa_active_M1	= 0

	SWITCH_AUDIO_ZONE MADDOGl TRUE

	STOP_CHAR_FACIAL_TALK scplayer
	
	DO_FADE 1000 FADE_IN	   
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	TIMERA = 0
	cutscene_flag_M1 = 0

	load_sample = SOUND_MAN1_EA
	$load_text = &MAN1_EA
	START_NEW_SCRIPT audio_load_and_play 2 100 triad_w3_M1[2]

SKIP_CUTSCENE_START

	WHILE TIMERA < 4000
		WAIT 0
	ENDWHILE

	IF NOT IS_CHAR_DEAD lsv_inside_M1[0]
		TASK_TOGGLE_DUCK lsv_inside_M1[0] FALSE
		cutscene_flag_M1 = 1
	ENDIF

	WHILE TIMERA < 4400
		WAIT 0
	ENDWHILE

	IF NOT IS_CHAR_DEAD lsv_inside_M1[0]
		TASK_SHOOT_AT_COORD lsv_inside_M1[0] 1267.7284 -782.9617 1092.6895 300
		cutscene_flag_M1 = 2 
	ENDIF  

	WHILE TIMERA < 4700
		WAIT 0
	ENDWHILE
	
	IF NOT IS_CHAR_DEAD lsv_inside_M1[0]
	AND NOT IS_CHAR_DEAD triad_w3_M1[2]
		CLEAR_CHAR_TASKS lsv_inside_M1[0]
		TASK_TOGGLE_DUCK lsv_inside_M1[0] TRUE
		EXPLODE_CHAR_HEAD triad_w3_M1[2]
		cutscene_flag_M1 = 3
	ENDIF	

	TASK_ACHIEVE_HEADING scplayer 270.0

	WHILE TIMERA < 5400
		WAIT 0
	ENDWHILE
	
SKIP_CUTSCENE_END

	SWITCH cutscene_flag_M1
		
		CASE 2
			IF NOT IS_CHAR_DEAD lsv_inside_M1[0]
				CLEAR_CHAR_TASKS lsv_inside_M1[0]
			ENDIF
		CASE 1
			IF NOT IS_CHAR_DEAD lsv_inside_M1[0]
				TASK_TOGGLE_DUCK lsv_inside_M1[0] TRUE
			ENDIF	
		CASE 0
			IF NOT IS_CHAR_DEAD triad_w3_M1[2]
				EXPLODE_CHAR_HEAD triad_w3_M1[2]
			ENDIF
		BREAK	

	ENDSWITCH

	SET_CHAR_HEADING scplayer 270.0
	
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	
	load_sample = SOUND_MAN1_EB
	$load_text = &MAN1_EB
	START_NEW_SCRIPT audio_load_and_play 2 101 triad_w3_M1[0]	
	
	TIMERB = 0
	overall_text_status_M1 = 1 // playing
	text_status_M1[0] = 1
	current_text_M1 = -1

	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER
								 
 	IF NOT IS_CHAR_DEAD triad_w3_M1[0]
		OPEN_SEQUENCE_TASK sequence_M1
			TASK_GO_STRAIGHT_TO_COORD -1 1266.8584 -782.4518 1090.8984 PEDMOVE_RUN -1
			TASK_ACHIEVE_HEADING -1 270.5538
			TASK_TOGGLE_DUCK -1 TRUE
			TASK_STAY_IN_SAME_PLACE -1 TRUE
			TASK_SET_CHAR_DECISION_MAKER -1 cover_dm_M1
		CLOSE_SEQUENCE_TASK sequence_M1	
		
		PERFORM_SEQUENCE_TASK triad_w3_M1[0] sequence_M1
		CLEAR_SEQUENCE_TASK sequence_M1
	ENDIF		
		 
	IF NOT IS_CHAR_DEAD triad_w3_M1[1]	 
  		OPEN_SEQUENCE_TASK sequence_M1
			TASK_GO_STRAIGHT_TO_COORD -1 1268.0199 -779.9092 1090.8984 PEDMOVE_RUN -1
			TASK_ACHIEVE_HEADING -1 267.5782
			TASK_TOGGLE_DUCK -1 TRUE
			TASK_STAY_IN_SAME_PLACE -1 TRUE
			TASK_SET_CHAR_DECISION_MAKER -1 cover_dm_M1
		CLOSE_SEQUENCE_TASK sequence_M1	
		
		PERFORM_SEQUENCE_TASK triad_w3_M1[1] sequence_M1
		CLEAR_SEQUENCE_TASK sequence_M1
	ENDIF

	IF NOT IS_CHAR_DEAD lsv_inside_M1[0]
		SET_CHAR_DECISION_MAKER lsv_inside_M1[0] cover_dm_M1	
	ENDIF

	IF NOT IS_CHAR_DEAD lsv_inside_M1[1]
		SET_CHAR_DECISION_MAKER lsv_inside_M1[1] cover_dm_M1	
	ENDIF

	IF NOT IS_CHAR_DEAD lsv_inside_M1[2]
  		OPEN_SEQUENCE_TASK sequence_M1
			TASK_STAND_STILL -1 1000
			TASK_WEAPON_ROLL -1 TRUE
			TASK_SET_CHAR_DECISION_MAKER -1 cover_dm_M1
		CLOSE_SEQUENCE_TASK sequence_M1	
		
		PERFORM_SEQUENCE_TASK lsv_inside_M1[2] sequence_M1
		CLEAR_SEQUENCE_TASK sequence_M1
	ENDIF

	IF NOT IS_CHAR_DEAD lsv_inside_M1[3]
  		OPEN_SEQUENCE_TASK sequence_M1
			TASK_STAND_STILL -1 500
			TASK_GO_STRAIGHT_TO_COORD -1 1283.2208 -779.9120 1088.9297 PEDMOVE_RUN -1 
			TASK_ACHIEVE_HEADING -1 90.0
			TASK_TOGGLE_DUCK -1 TRUE
			TASK_SET_CHAR_DECISION_MAKER -1 cover_dm_M1
		CLOSE_SEQUENCE_TASK sequence_M1	
		
		PERFORM_SEQUENCE_TASK lsv_inside_M1[3] sequence_M1
		CLEAR_SEQUENCE_TASK sequence_M1
	ENDIF

	GET_GAME_TIMER last_frame_time_M1
	player_status_M1 = 0 

mission_mansion1_MAIN_inside_loop:

	WAIT 0

 	// timers
	GET_GAME_TIMER this_frame_time_M1
	time_diff_M1 = this_frame_time_M1 - last_frame_time_M1 
	last_frame_time_M1 = this_frame_time_M1

	// triads
	triad_timer_w3_M1[0] += time_diff_M1 
	triad_timer_w3_M1[1] += time_diff_M1

	// lsv timers (for removing bodies)
	pointer_M1 = 0
	WHILE pointer_M1 < 20
		lsv_inside_timer_M1[pointer_M1] += time_diff_M1
		pointer_M1 ++
	ENDWHILE

	// lsv timers (for removing bodies)
	pointer_M1 = 0
	WHILE pointer_M1 < 10
		lsv_room_timer_M1[pointer_M1] += time_diff_M1
		pointer_M1 ++
	ENDWHILE

	lsv_spawn_timer_M1[0] += time_diff_M1 
	lsv_spawn_timer_M1[1] += time_diff_M1
	lsv_spawn_timer_M1[2] += time_diff_M1
	
	lsv_girl_timer_M1[0] += time_diff_M1 
	lsv_girl_timer_M1[1] += time_diff_M1
	lsv_girl_timer_M1[2] += time_diff_M1 
	lsv_girl_timer_M1[3] += time_diff_M1

	GOSUB mission_mansion1_SUB_player_inside

	GOSUB mission_mansion1_SUB_triad_w3_controler
	
	GOSUB mission_mansion1_SUB_clean_up_lsv_inside

	GOSUB mission_mansion1_SUB_text_controller

	IF player_status_M1 > 0
		GOSUB mission_mansion1_SUB_lsv_spawn_controller
	ENDIF

	IF big_poppa_active_M1 = 1
		GOSUB mission_mansion1_SUB_big_poppa_controller
	ENDIF

	IF NOT setup_bar_M1 = 0
		GOSUB mission_mansion1_SUB_setup_bar
	ENDIF

	// player gone outside for chase section
	IF big_poppa_status_M1 = 2
		GOTO mission_mansion1_MAIN_outside_chase 
	ENDIF

	run_this_frame_M1 = 0

GOTO mission_mansion1_MAIN_inside_loop



// ##########################################################################################################
// ##
// ## INSIDE MANSION SUB's
// ##
// ##########################################################################################################

LVAR_INT random_pos_M1
mission_mansion1_SUB_player_inside:

	SWITCH player_status_M1

		CASE 0
			IF IS_CHAR_IN_AREA_2D scplayer 1285.9373 -783.2331 1279.3602 -779.1484 FALSE
			OR triad_status_w3_M1[0] = 6
			OR triad_status_w3_M1[1] = 7	
				
				// room 1
				CREATE_CHAR PEDTYPE_MISSION2 LSV1 1271.9536 -786.6414 1088.9280 lsv_room_M1[0]
				SET_CHAR_HEADING lsv_room_M1[0] 248.4550
				SET_CHAR_AREA_VISIBLE lsv_room_M1[0] 5
				GIVE_WEAPON_TO_CHAR lsv_room_M1[0] WEAPONTYPE_MP5 30000
				SET_CURRENT_CHAR_WEAPON lsv_room_M1[0] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY lsv_room_M1[0] 45
				SET_CHAR_SHOOT_RATE lsv_room_M1[0] 35
				TASK_STAY_IN_SAME_PLACE lsv_room_M1[0] TRUE
				SET_CHAR_DECISION_MAKER lsv_room_M1[0] cover_dm_M1
				lsv_room_status_M1[0] = 0

				CREATE_CHAR PEDTYPE_MISSION2 LSV3 1272.1650 -790.5964 1088.9283 lsv_room_M1[1]
				SET_CHAR_HEADING lsv_room_M1[1] 277.2843
				SET_CHAR_AREA_VISIBLE lsv_room_M1[1] 5
				GIVE_WEAPON_TO_CHAR lsv_room_M1[1] WEAPONTYPE_MP5 30000
				SET_CURRENT_CHAR_WEAPON lsv_room_M1[1] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY lsv_room_M1[1] 45
				SET_CHAR_SHOOT_RATE lsv_room_M1[1] 35
				TASK_STAY_IN_SAME_PLACE lsv_room_M1[1] TRUE
				SET_CHAR_DECISION_MAKER lsv_room_M1[1] cover_dm_M1
				lsv_room_status_M1[1] = 0


				// room 2
				CREATE_CHAR PEDTYPE_MISSION2 LSV3 1287.4893 -788.2037 1088.9297 lsv_room_M1[2]
				SET_CHAR_HEADING lsv_room_M1[2] 176.3381
				SET_CHAR_AREA_VISIBLE lsv_room_M1[2] 5
				GIVE_WEAPON_TO_CHAR lsv_room_M1[2] WEAPONTYPE_MP5 30000
				SET_CURRENT_CHAR_WEAPON lsv_room_M1[2] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY lsv_room_M1[2] 50
				SET_CHAR_SHOOT_RATE lsv_room_M1[2] 35
				TASK_STAY_IN_SAME_PLACE lsv_room_M1[2] TRUE
				SET_CHAR_DECISION_MAKER lsv_room_M1[2] cover_dm_M1
				lsv_room_status_M1[2] = 0

				CREATE_CHAR PEDTYPE_MISSION2 LSV1 1292.4562 -788.2022 1088.9297 lsv_room_M1[3]
				SET_CHAR_HEADING lsv_room_M1[3] 145.1614
				SET_CHAR_AREA_VISIBLE lsv_room_M1[3] 5
				GIVE_WEAPON_TO_CHAR lsv_room_M1[3] WEAPONTYPE_MP5 30000
				SET_CURRENT_CHAR_WEAPON lsv_room_M1[3] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY lsv_room_M1[3] 50
				SET_CHAR_SHOOT_RATE lsv_room_M1[3] 35
				TASK_STAY_IN_SAME_PLACE lsv_room_M1[3] TRUE
				SET_CHAR_DECISION_MAKER lsv_room_M1[3] cover_dm_M1
				lsv_room_status_M1[3] = 0

				
				// room 3
				CREATE_CHAR PEDTYPE_MISSION2 LSV1 1271.3894 -800.9039 1088.9270 lsv_room_M1[4]
				SET_CHAR_HEADING lsv_room_M1[4] 263.9924
				SET_CHAR_AREA_VISIBLE lsv_room_M1[4] 5
				GIVE_WEAPON_TO_CHAR lsv_room_M1[4] WEAPONTYPE_MP5 30000
				SET_CURRENT_CHAR_WEAPON lsv_room_M1[4] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY lsv_room_M1[4] 50
				SET_CHAR_SHOOT_RATE lsv_room_M1[4] 35
				TASK_STAY_IN_SAME_PLACE lsv_room_M1[4] TRUE
				SET_CHAR_DECISION_MAKER lsv_room_M1[4] cover_dm_M1
				lsv_room_status_M1[4] = 0
 

				// room 4
				CREATE_CHAR PEDTYPE_MISSION2 LSV1 1291.7476 -810.5742 1088.9303 lsv_room_M1[5]
				SET_CHAR_HEADING lsv_room_M1[5] 87.4676
				SET_CHAR_AREA_VISIBLE lsv_room_M1[5] 5
				GIVE_WEAPON_TO_CHAR lsv_room_M1[5] WEAPONTYPE_MP5 30000
				SET_CURRENT_CHAR_WEAPON lsv_room_M1[5] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY lsv_room_M1[5] 50
				SET_CHAR_SHOOT_RATE lsv_room_M1[5] 35
				TASK_STAY_IN_SAME_PLACE lsv_room_M1[5] TRUE
				SET_CHAR_DECISION_MAKER lsv_room_M1[5] cover_dm_M1
				lsv_room_status_M1[5] = 0

				CREATE_CHAR PEDTYPE_MISSION2 LSV3 1289.5624 -801.5370 1088.9297 lsv_room_M1[6]
				SET_CHAR_HEADING lsv_room_M1[6] 155.5354
				SET_CHAR_AREA_VISIBLE lsv_room_M1[6] 5
				GIVE_WEAPON_TO_CHAR lsv_room_M1[6] WEAPONTYPE_MP5 30000
				SET_CURRENT_CHAR_WEAPON lsv_room_M1[6] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY lsv_room_M1[6] 50
				SET_CHAR_SHOOT_RATE lsv_room_M1[6] 35
				TASK_STAY_IN_SAME_PLACE lsv_room_M1[6] TRUE
				SET_CHAR_DECISION_MAKER lsv_room_M1[6] cover_dm_M1
				lsv_room_status_M1[6] = 0
  
 				
 				// room 5
				CREATE_CHAR PEDTYPE_MISSION2 LSV3 1272.7966 -812.8729 1088.9303 lsv_room_M1[7]
				SET_CHAR_HEADING lsv_room_M1[7] 263.3181
				SET_CHAR_AREA_VISIBLE lsv_room_M1[7] 5
				GIVE_WEAPON_TO_CHAR lsv_room_M1[7] WEAPONTYPE_MP5 30000
				SET_CURRENT_CHAR_WEAPON lsv_room_M1[7] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY lsv_room_M1[7] 60
				SET_CHAR_SHOOT_RATE lsv_room_M1[7] 45
				TASK_STAY_IN_SAME_PLACE lsv_room_M1[7] TRUE
				SET_CHAR_DECISION_MAKER lsv_room_M1[7] cover_dm_M1
				lsv_room_status_M1[7] = 0
 
				
				// room 6
				CREATE_CHAR PEDTYPE_MISSION2 LSV3 1292.8157 -821.3403 1088.9303 lsv_room_M1[8]
				SET_CHAR_HEADING lsv_room_M1[8] 96.7033
				SET_CHAR_AREA_VISIBLE lsv_room_M1[8] 5
				GIVE_WEAPON_TO_CHAR lsv_room_M1[8] WEAPONTYPE_MP5 30000
				SET_CURRENT_CHAR_WEAPON lsv_room_M1[8] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY lsv_room_M1[8] 60
				SET_CHAR_SHOOT_RATE lsv_room_M1[8] 45
				TASK_STAY_IN_SAME_PLACE lsv_room_M1[8] TRUE
				SET_CHAR_DECISION_MAKER lsv_room_M1[8] cover_dm_M1
				lsv_room_status_M1[8] = 0

				CREATE_CHAR PEDTYPE_MISSION2 LSV1 1292.1702 -814.6696 1088.9303 lsv_room_M1[9]
				SET_CHAR_HEADING lsv_room_M1[9] 136.5314
				SET_CHAR_AREA_VISIBLE lsv_room_M1[9] 5
				GIVE_WEAPON_TO_CHAR lsv_room_M1[9] WEAPONTYPE_MP5 30000
				SET_CURRENT_CHAR_WEAPON lsv_room_M1[9] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY lsv_room_M1[9] 60
				SET_CHAR_SHOOT_RATE lsv_room_M1[9] 45
				TASK_STAY_IN_SAME_PLACE lsv_room_M1[9] TRUE
				SET_CHAR_DECISION_MAKER lsv_room_M1[9] cover_dm_M1
				lsv_room_status_M1[9] = 0
  			
  				lsv_spawn_status_M1[0] = -1
				lsv_spawn_status_M1[1] = -1
				lsv_spawn_status_M1[2] = -1

				lsv_spawn_timer_M1[0] = 8000
				lsv_spawn_timer_M1[1] = 4000
				lsv_spawn_timer_M1[2] = 0

				extra_man_M1 = 1
				spawn_time_M1 = 8000

				player_status_M1 ++

			ENDIF
 		BREAK

		CASE 1
			area_X1_M1 = 1280.15
			area_Y1_M1 = -791.93
			area_X2_M1 = 1284.45 
			area_Y2_M1 = -795.42
			GOSUB mission_mansion1_SUB_area_check

			IF in_area_M1 = 0
				BREAK
			ENDIF
			
			MARK_CHAR_AS_NO_LONGER_NEEDED triad_w3_M1[2]

			GENERATE_RANDOM_INT_IN_RANGE 0 2 random_pos_M1
			IF random_pos_M1 = 0
				// roll out guy
				CREATE_CHAR PEDTYPE_MISSION2 LSV1 1278.6074 -801.3676 1088.9297 lsv_inside_M1[4]
				// ganggirl
				CREATE_CHAR PEDTYPE_MISSION3 GANGRL3 1286.2810 -809.3582 1088.9297 lsv_girl_M1[0]  
			ELSE
				CREATE_CHAR PEDTYPE_MISSION2 LSV1 1286.2810 -809.3582 1088.9297 lsv_inside_M1[4]
				CREATE_CHAR PEDTYPE_MISSION3 GANGRL3 1278.6074 -801.3676 1088.9297 lsv_girl_M1[0]
			ENDIF
			 
			SET_CHAR_HEADING lsv_inside_M1[4] 0.0
			SET_CHAR_HEALTH lsv_inside_M1[4] 200	
			SET_CHAR_MAX_HEALTH lsv_inside_M1[4] 200
			SET_CHAR_AREA_VISIBLE lsv_inside_M1[4] 5
			GIVE_WEAPON_TO_CHAR lsv_inside_M1[4] WEAPONTYPE_MP5 30000
			SET_CURRENT_CHAR_WEAPON lsv_inside_M1[4] WEAPONTYPE_MP5
			SET_CHAR_ACCURACY lsv_inside_M1[4] 70
			SET_CHAR_SHOOT_RATE lsv_inside_M1[4] 60
			SET_CHAR_DECISION_MAKER lsv_inside_M1[4] cover_dm_M1	
			// so he rolls correct way
			IF random_pos_M1 = 0
				TASK_WEAPON_ROLL lsv_inside_M1[4] TRUE  
			ELSE
				TASK_WEAPON_ROLL lsv_inside_M1[4] FALSE
			ENDIF 
			lsv_inside_status_M1[4] = 0			
			
			
			SET_CHAR_HEADING lsv_girl_M1[0] 0.0
			SET_CHAR_AREA_VISIBLE lsv_girl_M1[0] 5
			SET_CHAR_DECISION_MAKER lsv_girl_M1[0] ganggirl_dm_M1
		 	lsv_girl_status_M1[0] = 0
			
			player_status_M1 ++
			
		
		CASE 2
			area_X1_M1 = 1284.9392
			area_Y1_M1 = -799.5270
			area_X2_M1 = 1279.6704 
			area_Y2_M1 = -803.5135
			GOSUB mission_mansion1_SUB_area_check

			IF in_area_M1 = 0
				BREAK
			ENDIF

			text_status_M1[2] = 1
			
			ADD_BLIP_FOR_COORD 1227.2264 -834.4195 1083.0078 goto_blip_M1
			SET_BLIP_ENTRY_EXIT goto_blip_M1 1258.3376 -785.4264 91.0313
			SET_COORD_BLIP_APPEARANCE goto_blip_M1 COORD_BLIP_APPEARANCE_ENEMY
			   
			extra_man_M1 = 0
   			spawn_time_M1 = 10000
   			
   			player_status_M1 ++


		CASE 3		
			area_X1_M1 = 1284.94
			area_Y1_M1 = -823.43
			area_X2_M1 = 1280.15 
			area_Y2_M1 = -818.35
			GOSUB mission_mansion1_SUB_area_check

			IF in_area_M1 = 0
				BREAK
			ENDIF 

			CREATE_CHAR PEDTYPE_MISSION2 LSV3 1267.9357 -836.5694 1084.6328 lsv_inside_M1[5]  
			SET_CHAR_HEADING lsv_inside_M1[5] 312.4274
			SET_CHAR_HEALTH lsv_inside_M1[5] 200	
			SET_CHAR_MAX_HEALTH lsv_inside_M1[5] 200
			SET_CHAR_AREA_VISIBLE lsv_inside_M1[5] 5
			GIVE_WEAPON_TO_CHAR lsv_inside_M1[5] WEAPONTYPE_MP5 30000
			SET_CURRENT_CHAR_WEAPON lsv_inside_M1[5] WEAPONTYPE_MP5
			SET_CHAR_ACCURACY lsv_inside_M1[5] 60
			SET_CHAR_SHOOT_RATE lsv_inside_M1[5] 60
			TASK_TOGGLE_DUCK lsv_inside_M1[5] TRUE
			SET_CHAR_DECISION_MAKER lsv_inside_M1[5] cover_dm_M1	 
			lsv_inside_status_M1[5] = 0

			CREATE_CHAR PEDTYPE_MISSION2 LSV1 1272.9401 -837.6696 1084.6328 lsv_inside_M1[6]  
			SET_CHAR_HEADING lsv_inside_M1[6] 325.2802
			SET_CHAR_HEALTH lsv_inside_M1[6] 200	
			SET_CHAR_MAX_HEALTH lsv_inside_M1[6] 200
			SET_CHAR_AREA_VISIBLE lsv_inside_M1[6] 5
			GIVE_WEAPON_TO_CHAR lsv_inside_M1[6] WEAPONTYPE_MP5 30000
			SET_CURRENT_CHAR_WEAPON lsv_inside_M1[6] WEAPONTYPE_MP5
			SET_CHAR_ACCURACY lsv_inside_M1[6] 60
			SET_CHAR_SHOOT_RATE lsv_inside_M1[6] 60
			TASK_TOGGLE_DUCK lsv_inside_M1[6] TRUE
			SET_CHAR_DECISION_MAKER lsv_inside_M1[6] cover_dm_M1	 
			lsv_inside_status_M1[6] = 0

			CREATE_CHAR PEDTYPE_MISSION2 LSV3 1278.9363 -837.8117 1084.6328 lsv_inside_M1[7]  
			SET_CHAR_HEADING lsv_inside_M1[7] 354.8666
			SET_CHAR_HEALTH lsv_inside_M1[7] 200	
			SET_CHAR_MAX_HEALTH lsv_inside_M1[7] 200		
			SET_CHAR_AREA_VISIBLE lsv_inside_M1[7] 5
			GIVE_WEAPON_TO_CHAR lsv_inside_M1[7] WEAPONTYPE_MP5 30000
			SET_CURRENT_CHAR_WEAPON lsv_inside_M1[7] WEAPONTYPE_MP5
			SET_CHAR_ACCURACY lsv_inside_M1[7] 60
			SET_CHAR_SHOOT_RATE lsv_inside_M1[7] 60
			TASK_TOGGLE_DUCK lsv_inside_M1[7] TRUE
			SET_CHAR_DECISION_MAKER lsv_inside_M1[7] cover_dm_M1	 
			lsv_inside_status_M1[7] = 0
  
  
			CREATE_CHAR PEDTYPE_MISSION2 LSV1 1293.4338 -837.4308 1084.6328 lsv_inside_M1[8]  
			SET_CHAR_HEADING lsv_inside_M1[8] 84.6034
			SET_CHAR_HEALTH lsv_inside_M1[8] 200	
			SET_CHAR_MAX_HEALTH lsv_inside_M1[8] 200			
			SET_CHAR_AREA_VISIBLE lsv_inside_M1[8] 5
			GIVE_WEAPON_TO_CHAR lsv_inside_M1[8] WEAPONTYPE_MP5 30000
			SET_CURRENT_CHAR_WEAPON lsv_inside_M1[8] WEAPONTYPE_MP5
			SET_CHAR_ACCURACY lsv_inside_M1[8] 60
			SET_CHAR_SHOOT_RATE lsv_inside_M1[8] 60
			TASK_TOGGLE_DUCK lsv_inside_M1[8] TRUE
			SET_CHAR_DECISION_MAKER lsv_inside_M1[8] cover_dm_M1	 
			lsv_inside_status_M1[8] = 0

			CREATE_CHAR PEDTYPE_MISSION2 LSV3 1288.7882 -832.1437 1084.6328 lsv_inside_M1[9]  
			SET_CHAR_HEADING lsv_inside_M1[9] 93.3971
			SET_CHAR_HEALTH lsv_inside_M1[9] 200	
			SET_CHAR_MAX_HEALTH lsv_inside_M1[9] 200
			SET_CHAR_AREA_VISIBLE lsv_inside_M1[9] 5
			GIVE_WEAPON_TO_CHAR lsv_inside_M1[9] WEAPONTYPE_MP5 30000
			SET_CURRENT_CHAR_WEAPON lsv_inside_M1[9] WEAPONTYPE_MP5
			SET_CHAR_ACCURACY lsv_inside_M1[9] 60
			SET_CHAR_SHOOT_RATE lsv_inside_M1[9] 60
			TASK_TOGGLE_DUCK lsv_inside_M1[9] TRUE
			SET_CHAR_DECISION_MAKER lsv_inside_M1[9] cover_dm_M1	 
			lsv_inside_status_M1[9] = 0

  
  			CREATE_CHAR PEDTYPE_MISSION2 LSV1 1279.4520 -818.7137 1084.6328 lsv_inside_M1[10]  
			SET_CHAR_HEADING lsv_inside_M1[10] 169.7489
			SET_CHAR_HEALTH lsv_inside_M1[10] 200	
			SET_CHAR_MAX_HEALTH lsv_inside_M1[10] 200			
			SET_CHAR_AREA_VISIBLE lsv_inside_M1[10] 5
			GIVE_WEAPON_TO_CHAR lsv_inside_M1[10] WEAPONTYPE_MP5 30000
			SET_CURRENT_CHAR_WEAPON lsv_inside_M1[10] WEAPONTYPE_MP5
			SET_CHAR_ACCURACY lsv_inside_M1[10] 30
			SET_CHAR_SHOOT_RATE lsv_inside_M1[10] 50
			TASK_TOGGLE_DUCK lsv_inside_M1[10] TRUE
			SET_CHAR_DECISION_MAKER lsv_inside_M1[10] cover_dm_M1	 
			lsv_inside_status_M1[10] = 0


  			// gang girl
  			CREATE_CHAR PEDTYPE_MISSION3 BFYST 1292.7357 -832.9896 1084.6328 lsv_girl_M1[1]
			SET_CHAR_HEADING lsv_girl_M1[1] 90.0
			SET_CHAR_AREA_VISIBLE lsv_girl_M1[1] 5
			SET_CHAR_DECISION_MAKER lsv_girl_M1[1] ganggirl_dm_M1
		 	lsv_girl_status_M1[1] = 0

			CREATE_CHAR PEDTYPE_MISSION3 GANGRL3 1277.9844 -813.5414 1084.6403 lsv_girl_M1[2]
			SET_CHAR_HEADING lsv_girl_M1[2] 180.0
			SET_CHAR_AREA_VISIBLE lsv_girl_M1[2] 5
			SET_CHAR_DECISION_MAKER lsv_girl_M1[2] ganggirl_dm_M1
		 	lsv_girl_status_M1[2] = 0
			
			player_status_M1 ++

		CASE 4
			IF IS_CHAR_IN_AREA_2D scplayer 1266.5333 -831.5337 1261.2825 -838.8454 FALSE			

	  			// guy in dining room
	  			CREATE_CHAR PEDTYPE_MISSION2 LSV3 1253.7582 -826.5505 1083.0078 lsv_inside_M1[11]  
				SET_CHAR_HEADING lsv_inside_M1[11] 197.3586
				SET_CHAR_HEALTH lsv_inside_M1[11] 200	
				SET_CHAR_MAX_HEALTH lsv_inside_M1[11] 200
				SET_CHAR_AREA_VISIBLE lsv_inside_M1[11] 5
				GIVE_WEAPON_TO_CHAR lsv_inside_M1[11] WEAPONTYPE_MP5 30000
				SET_CURRENT_CHAR_WEAPON lsv_inside_M1[11] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY lsv_inside_M1[11] 60
				SET_CHAR_SHOOT_RATE lsv_inside_M1[11] 70
				TASK_TOGGLE_DUCK lsv_inside_M1[11] TRUE
				SET_CHAR_DECISION_MAKER lsv_inside_M1[11] cover_dm_M1	 
				TASK_GO_STRAIGHT_TO_COORD lsv_inside_M1[11] 1255.4991 -831.5446 1083.0078 PEDMOVE_RUN -1 
				lsv_inside_status_M1[11] = 0

				
				// guy in door way
	  			CREATE_CHAR PEDTYPE_MISSION2 LSV1 1233.7047 -827.7943 1083.0078 lsv_inside_M1[12]  
				SET_CHAR_HEADING lsv_inside_M1[12] 180.0
				SET_CHAR_HEALTH lsv_inside_M1[12] 200	
				SET_CHAR_MAX_HEALTH lsv_inside_M1[12] 200				
				SET_CHAR_AREA_VISIBLE lsv_inside_M1[12] 5
				GIVE_WEAPON_TO_CHAR lsv_inside_M1[12] WEAPONTYPE_MP5 30000
				SET_CURRENT_CHAR_WEAPON lsv_inside_M1[12] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY lsv_inside_M1[12] 60
				SET_CHAR_SHOOT_RATE lsv_inside_M1[12] 70
				SET_CHAR_DECISION_MAKER lsv_inside_M1[12] empty_dm_M1	  
				lsv_inside_status_M1[12] = 0

		  		OPEN_SEQUENCE_TASK sequence_M1
					TASK_GO_STRAIGHT_TO_COORD -1 1234.1042 -832.2075 1083.0078 PEDMOVE_RUN -1
					TASK_GO_STRAIGHT_TO_COORD -1 1238.8474 -833.2389 1083.0078 PEDMOVE_RUN -1 
					TASK_TOGGLE_DUCK -1 TRUE
					TASK_STAY_IN_SAME_PLACE -1 TRUE
					TASK_SET_CHAR_DECISION_MAKER -1 cover_dm_M1
				CLOSE_SEQUENCE_TASK sequence_M1	
				PERFORM_SEQUENCE_TASK lsv_inside_M1[12] sequence_M1
				CLEAR_SEQUENCE_TASK sequence_M1

				// Big Poppa
				CREATE_CHAR PEDTYPE_MISSION2 LSV2 1227.2264 -834.4195 1083.0078 big_poppa_M1
				SET_CHAR_HEADING big_poppa_M1 270.0
				SET_CHAR_PROOFS big_poppa_M1 TRUE TRUE TRUE TRUE TRUE
				SET_CHAR_DECISION_MAKER big_poppa_M1 empty_dm_M1
				SET_CHAR_NEVER_TARGETTED big_poppa_M1 TRUE

				REMOVE_BLIP goto_blip_M1
				ADD_BLIP_FOR_CHAR big_poppa_M1 big_poppa_blip_M1
				SET_BLIP_ENTRY_EXIT big_poppa_blip_M1 1258.3376 -785.4264 91.0313 				
				CHANGE_BLIP_DISPLAY big_poppa_blip_M1 MARKER_ONLY

		  		poppa_X_M1 = 1227.3147
				poppa_Y_M1 = -831.5784
		  		
		  		OPEN_SEQUENCE_TASK sequence_M1
					TASK_WEAPON_ROLL -1 TRUE
					TASK_STAND_STILL -1 2000 
					TASK_GO_STRAIGHT_TO_COORD -1 poppa_X_M1 poppa_Y_M1 1083.0078 PEDMOVE_RUN -1
				CLOSE_SEQUENCE_TASK sequence_M1	
				PERFORM_SEQUENCE_TASK big_poppa_M1 sequence_M1
				CLEAR_SEQUENCE_TASK sequence_M1
				
				poppa_posX_M1 = 1225.1938
				poppa_posY_M1 = -816.5573
				poppa_pos_heading_M1 = 270.0
				big_poppa_status_M1 = 0
								
				load_sample = SOUND_MAN1_BA
				$load_text = &MAN1_BA
				START_NEW_SCRIPT audio_load_and_play 2 200 big_poppa_M1

				big_poppa_active_M1 = 1
				overall_text_status_M1 = -1
 				
 				// gang girl
	  			CREATE_CHAR PEDTYPE_MISSION3 BFYST 1240.8347 -826.6857 1082.1562 lsv_girl_M1[3]
				SET_CHAR_HEADING lsv_girl_M1[3] 143.0862
				SET_CHAR_AREA_VISIBLE lsv_girl_M1[3] 5
				SET_CHAR_DECISION_MAKER lsv_girl_M1[3] ganggirl_dm_M1
			 	lsv_girl_status_M1[3] = 0
								  				
				player_status_M1 ++	

			ENDIF

		
		
		CASE 5
			//IF IS_CHAR_IN_AREA_2D scplayer 1229.9338 -835.5339 1224.8817 -838.8135 FALSE
			IF IS_CHAR_IN_AREA_3D scplayer 1229.9338 -835.5339 1082.5 1224.8817 -838.8135 1085.5 FALSE
				
				IF told_to_follow_M1 = 0
					PRINT_NOW MAN1_21 8000 1
					told_to_follow_M1 = 1
				ENDIF

				IF NOT IS_CHAR_DEAD big_poppa_M1
					CLEAR_CHAR_TASKS_IMMEDIATELY big_poppa_M1
					SET_CHAR_COORDINATES big_poppa_M1 poppa_posX_M1 poppa_posY_M1 1083.01
					SET_CHAR_HEADING big_poppa_M1 poppa_pos_heading_M1
					poppa_X_M1 = 1231.2821
					poppa_Y_M1 = -816.6354
					TASK_GO_STRAIGHT_TO_COORD big_poppa_M1 poppa_X_M1 poppa_Y_M1 1083.0148 PEDMOVE_RUN -1
					poppa_posX_M1 = 1256.5833
					poppa_posY_M1 = -812.8542
					poppa_pos_heading_M1 = 270.0
					big_poppa_status_M1 = 0

				ENDIF
				player_status_M1 ++

			ENDIF
		BREAK

		CASE 6
			//IF IS_CHAR_IN_AREA_2D scplayer 1228.6797 -829.9222 1224.8832 -825.2996 FALSE
			IF IS_CHAR_IN_AREA_3D scplayer 1228.6797 -829.9222 1082.5 1224.8832 -825.2996 1085.5 FALSE
				// guy who rolls out
	  			CREATE_CHAR PEDTYPE_MISSION2 LSV1 1228.8076 -812.9333 1083.0078 lsv_inside_M1[13]  
				SET_CHAR_HEADING lsv_inside_M1[13] 180.0
				SET_CHAR_HEALTH lsv_inside_M1[13] 200	
				SET_CHAR_MAX_HEALTH lsv_inside_M1[13] 200
				SET_CHAR_AREA_VISIBLE lsv_inside_M1[13] 5
				GIVE_WEAPON_TO_CHAR lsv_inside_M1[13] WEAPONTYPE_MP5 30000
				SET_CURRENT_CHAR_WEAPON lsv_inside_M1[13] WEAPONTYPE_MP5
				SET_CHAR_ACCURACY lsv_inside_M1[13] 60
				SET_CHAR_SHOOT_RATE lsv_inside_M1[13] 70
				SET_CHAR_DECISION_MAKER lsv_inside_M1[13] cover_dm_M1	  
				TASK_WEAPON_ROLL lsv_inside_M1[13] TRUE
				lsv_inside_status_M1[13] = 0

				player_status_M1 ++
			ENDIF
		BREAK

		CASE 7
			//IF IS_CHAR_IN_AREA_2D scplayer 1229.7991 -810.6401 1224.8843 -819.0158 FALSE		
			IF IS_CHAR_IN_AREA_3D scplayer 1229.7991 -810.6401 1082.5 1224.8843 -819.0158 1085.5 FALSE
				
				IF NOT IS_CHAR_DEAD big_poppa_M1
 					CLEAR_CHAR_TASKS_IMMEDIATELY big_poppa_M1
 					SET_CHAR_COORDINATES big_poppa_M1 poppa_posX_M1 poppa_posY_M1 1083.01
					SET_CHAR_HEADING big_poppa_M1 poppa_pos_heading_M1
					poppa_X_M1 = 1262.6278
					poppa_Y_M1 = -805.6454
					
					OPEN_SEQUENCE_TASK sequence_M1
						TASK_GO_STRAIGHT_TO_COORD -1 1261.4445 -812.2836 1083.0148 PEDMOVE_RUN -1
						TASK_GO_STRAIGHT_TO_COORD -1 poppa_X_M1 poppa_Y_M1 1083.0148 PEDMOVE_RUN -1
					CLOSE_SEQUENCE_TASK sequence_M1	
					PERFORM_SEQUENCE_TASK big_poppa_M1 sequence_M1
					CLEAR_SEQUENCE_TASK sequence_M1
					
					poppa_posX_M1 = 1266.9276
					poppa_posY_M1 = -801.9423
					poppa_pos_heading_M1 = 270.0
					big_poppa_status_M1 = 0

				ENDIF
				player_status_M1 ++
			ENDIF
		BREAK

		CASE 8
			//IF IS_CHAR_IN_AREA_2D scplayer 1254.2812 -810.6465 1264.0446 -813.9205 FALSE 		
			IF IS_CHAR_IN_AREA_3D scplayer 1254.2812 -810.6465 1082.5 1264.0446 -813.9205 1085.5 FALSE
				
				IF NOT IS_CHAR_DEAD big_poppa_M1
 					CLEAR_CHAR_TASKS_IMMEDIATELY big_poppa_M1
 					SET_CHAR_COORDINATES big_poppa_M1 poppa_posX_M1 poppa_posY_M1 1083.01
					SET_CHAR_HEADING big_poppa_M1 poppa_pos_heading_M1
					poppa_X_M1 = 1274.6344
					poppa_Y_M1 = -801.9045
					TASK_GO_STRAIGHT_TO_COORD big_poppa_M1 poppa_X_M1 poppa_Y_M1 1083.0148 PEDMOVE_RUN -1
					poppa_posX_M1 = 1257.3879
					poppa_posY_M1 = -789.8594
					poppa_pos_heading_M1 = 90.0
					big_poppa_status_M1 = 0

					load_sample = SOUND_MAN1_BB
					$load_text = &MAN1_BB
					START_NEW_SCRIPT audio_load_and_play 2 200 big_poppa_M1

				ENDIF
				player_status_M1 ++

				GOSUB mission_mansion1_SUB_clean_mansion_part1
			ENDIF
		BREAK

		CASE 9 
			//IF IS_CHAR_IN_AREA_2D scplayer 1280.8965 -792.8988 1285.1869 -786.7327 FALSE
			IF IS_CHAR_IN_AREA_3D scplayer 1278.4686 -791.9025 1082.5 1285.1869 -786.7327 1085.5 FALSE

				IF NOT IS_CHAR_DEAD big_poppa_M1
 					CLEAR_CHAR_TASKS_IMMEDIATELY big_poppa_M1
 					SET_CHAR_COORDINATES big_poppa_M1 poppa_posX_M1 poppa_posY_M1 1083.01
					SET_CHAR_HEADING big_poppa_M1 poppa_pos_heading_M1
					poppa_X_M1 = 1249.6646
					poppa_Y_M1 = -782.4513

					OPEN_SEQUENCE_TASK sequence_M1
						TASK_GO_STRAIGHT_TO_COORD -1 1250.0712 -788.7680 1083.0148 PEDMOVE_RUN -1
						TASK_GO_STRAIGHT_TO_COORD -1 poppa_X_M1 poppa_Y_M1 1083.0148 PEDMOVE_RUN -1
					CLOSE_SEQUENCE_TASK sequence_M1	
					PERFORM_SEQUENCE_TASK big_poppa_M1 sequence_M1
					CLEAR_SEQUENCE_TASK sequence_M1
											
					poppa_posX_M1 = 1265.6135
					poppa_posY_M1 = -769.7585
					poppa_pos_heading_M1 = 270.0
					big_poppa_status_M1 = 0

					load_sample = SOUND_MAN1_BC
					$load_text = &MAN1_BC
					START_NEW_SCRIPT audio_load_and_play 2 200 big_poppa_M1

				ENDIF
				player_status_M1 ++

			ENDIF
		BREAK

		CASE 10
			//IF IS_CHAR_IN_AREA_2D scplayer 1251.2849 -775.3042 1247.0049 -765.7498 FALSE 
			IF IS_CHAR_IN_AREA_3D scplayer 1251.2849 -775.3042 1082.5 1247.0049 -765.7498 1085.5 FALSE

				IF NOT IS_CHAR_DEAD big_poppa_M1
 					CLEAR_CHAR_TASKS_IMMEDIATELY big_poppa_M1
 					SET_CHAR_COORDINATES big_poppa_M1 poppa_posX_M1 poppa_posY_M1 1083.01
					SET_CHAR_HEADING big_poppa_M1 poppa_pos_heading_M1
					poppa_X_M1 = 1269.6736
					poppa_Y_M1 = -774.7773

					OPEN_SEQUENCE_TASK sequence_M1
						TASK_GO_STRAIGHT_TO_COORD -1 1269.4000 -769.4953 1083.0148 PEDMOVE_RUN -1
						TASK_GO_STRAIGHT_TO_COORD -1 poppa_X_M1 poppa_Y_M1 1083.0148 PEDMOVE_RUN -1
					CLOSE_SEQUENCE_TASK sequence_M1	
					PERFORM_SEQUENCE_TASK big_poppa_M1 sequence_M1
					CLEAR_SEQUENCE_TASK sequence_M1
											
					poppa_posX_M1 = 1280.1145	   
					poppa_posY_M1 = -781.6223
					poppa_pos_heading_M1 = 270.0
					big_poppa_status_M1 = 0
				ENDIF
				player_status_M1 ++

			ENDIF
		BREAK

		CASE 11
			//IF IS_CHAR_IN_AREA_2D scplayer 1260.2893 -765.4207 1267.5243 -774.8633 FALSE 
			IF IS_CHAR_IN_AREA_3D scplayer 1260.2893 -765.4207 1082.5 1267.5243 -774.8633 1085.5 FALSE

				IF NOT IS_CHAR_DEAD big_poppa_M1
 					CLEAR_CHAR_TASKS_IMMEDIATELY big_poppa_M1
 					SET_CHAR_COORDINATES big_poppa_M1 poppa_posX_M1 poppa_posY_M1 1083.01
					SET_CHAR_HEADING big_poppa_M1 poppa_pos_heading_M1
					poppa_X_M1 = 1301.0813
					poppa_Y_M1 = -786.3077

					OPEN_SEQUENCE_TASK sequence_M1
						TASK_GO_STRAIGHT_TO_COORD -1 1288.8064 -780.5795 1083.0148 PEDMOVE_RUN -1
						TASK_GO_STRAIGHT_TO_COORD -1 1300.8389 -781.0582 1083.0148 PEDMOVE_RUN -1
						TASK_GO_STRAIGHT_TO_COORD -1 poppa_X_M1 poppa_Y_M1 1083.0148 PEDMOVE_RUN -1
					CLOSE_SEQUENCE_TASK sequence_M1	
					PERFORM_SEQUENCE_TASK big_poppa_M1 sequence_M1
					CLEAR_SEQUENCE_TASK sequence_M1
											
					poppa_posX_M1 = -1.0
					big_poppa_status_M1 = 0

					load_sample = SOUND_MAN1_BD
					$load_text = &MAN1_BD
					START_NEW_SCRIPT audio_load_and_play 2 200 big_poppa_M1

				ENDIF
				player_status_M1 ++
			ENDIF
		BREAK

	ENDSWITCH


RETURN 

// ###############################################################################################################


// used to stop dialogue text overwriting mission text and vice versa
LVAR_INT text_status_M1[4] overall_text_status_M1 current_text_M1

mission_mansion1_SUB_text_controller:

	IF overall_text_status_M1 = 0
	
		pointer_M1 = 0
		
		WHILE pointer_M1 < 4
			IF text_status_M1[pointer_M1] = 1

				SWITCH pointer_M1
				
					CASE 0 // Search mansion for big poppa
						PRINT_NOW MAN1_13 8000 1
						overall_text_status_M1 = 1
						current_text_M1 = pointer_M1
						TIMERB = 0
						pointer_M1 = 4
					BREAK

					CASE 1 // Take corridor we will take rooms
						IF NOT IS_CHAR_DEAD triad_w3_M1[0]
							
							IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer triad_w3_M1[0] 35.0 35.0 4.0 FALSE 
								IF NOT IS_CHAR_DEAD triad_w3_M1[1]
									load_sample = SOUND_MAN1_FA
									$load_text = &MAN1_FA
								ELSE
									load_sample = SOUND_MAN1_FB
									$load_text = &MAN1_FB
								ENDIF
								START_NEW_SCRIPT audio_load_and_play 2 100 triad_w3_M1[0]
							ENDIF
							overall_text_status_M1 = 1
						ELSE
							IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer triad_w3_M1[1] 35.0 35.0 4.0 FALSE							
								load_sample = SOUND_MAN1_FJ
								$load_text = &MAN1_FJ
								START_NEW_SCRIPT audio_load_and_play 2 100 triad_w3_M1[1]
							ENDIF
							overall_text_status_M1 = 1
						ENDIF

						IF overall_text_status_M1 = 1
							current_text_M1 = pointer_M1
							TIMERB = 0
							pointer_M1 = 4
						ENDIF

					BREAK

					CASE 2 // The owner is downstairs
						PRINT_NOW MAN1_20 8000 1
						overall_text_status_M1 = 1
						current_text_M1 = pointer_M1
						TIMERB = 0
						pointer_M1 = 4
					BREAK

					CASE 3 // hold the balcony
						IF NOT IS_CHAR_DEAD triad_w3_M1[0]
							IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer triad_w3_M1[0] 35.0 35.0 4.0 FALSE
								IF NOT IS_CHAR_DEAD triad_w3_M1[1]
									load_sample = SOUND_MAN1_FG
									$load_text = &MAN1_FG
								ELSE
									load_sample = SOUND_MAN1_FH
									$load_text = &MAN1_FH
								ENDIF
								START_NEW_SCRIPT audio_load_and_play 2 100 triad_w3_M1[0]
							ENDIF
							overall_text_status_M1 = 1
						ELSE
							IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer triad_w3_M1[1] 35.0 35.0 4.0 FALSE
								load_sample = SOUND_MAN1_FO
								$load_text = &MAN1_FO
								START_NEW_SCRIPT audio_load_and_play 2 100 triad_w3_M1[1]
							ENDIF 
							overall_text_status_M1 = 1
						ENDIF
						
						IF overall_text_status_M1 = 1
							current_text_M1 = pointer_M1
							TIMERB = 0
							pointer_M1 = 4
						ENDIF

				ENDSWITCH
			ENDIF

			pointer_M1 ++
		ENDWHILE
			
	ELSE
		IF overall_text_status_M1 = 1
			IF TIMERB > 8000
				IF NOT current_text_M1 = -1 
					text_status_M1[current_text_M1] = -1
				ENDIF
				overall_text_status_M1 = 0
			ENDIF
		ENDIF
	ENDIF

RETURN




// ###############################################################################################################

LVAR_INT in_area_M1
LVAR_FLOAT area_X1_M1 area_Y1_M1 area_X2_M1 area_Y2_M1

mission_mansion1_SUB_area_check:

	in_area_M1 = 0

	// player
	IF IS_CHAR_IN_AREA_2D scplayer area_X1_M1 area_Y1_M1 area_X2_M1 area_Y2_M1 FALSE
		in_area_M1 = 1	
		RETURN
	ENDIF

	// triad 0
	IF NOT IS_CHAR_DEAD triad_w3_M1[0]
		IF IS_CHAR_IN_AREA_2D triad_w3_M1[0] area_X1_M1 area_Y1_M1 area_X2_M1 area_Y2_M1 FALSE
			in_area_M1 = 1	
			RETURN
		ENDIF
	ENDIF

	// triad 1
	IF NOT IS_CHAR_DEAD triad_w3_M1[1]
		IF IS_CHAR_IN_AREA_2D triad_w3_M1[1] area_X1_M1 area_Y1_M1 area_X2_M1 area_Y2_M1 FALSE
			in_area_M1 = 1	
			RETURN
		ENDIF
	ENDIF

RETURN

// ###############################################################################################################

LVAR_INT triad_movement_w3_M1[2] triad_moveto_w3_M1[2] triad_room_status_w3_M1[2] triad_model_w3_M1[2]
LVAR_INT this_triad_M1 other_triad_M1
mission_mansion1_SUB_triad_w3_controler:

	this_triad_M1  = 0
	other_triad_M1 = 1

	WHILE this_triad_M1 < 2

		IF NOT IS_CHAR_DEAD triad_w3_M1[this_triad_M1]

			SWITCH triad_status_w3_M1[this_triad_M1]

				/*
				   	###########################################################

						START STUFF

				   	###########################################################
				*/

				CASE 0
				CASE 1 
					IF NOT lsv_inside_status_M1[0] = -1
					OR NOT lsv_inside_status_M1[1] = -1
					OR NOT lsv_inside_status_M1[2] = -1
					OR NOT lsv_inside_status_M1[3] = -1
						BREAK
					ENDIF
					
					triad_status_w3_M1[this_triad_M1] += 2 
				BREAK

				CASE 2
				CASE 3
					CLEAR_CHAR_TASKS triad_w3_M1[this_triad_M1] 
					SET_CHAR_DECISION_MAKER triad_w3_M1[this_triad_M1] movement_dm_M1
					triad_movement_w3_M1[this_triad_M1] = -1  
					triad_moveto_w3_M1[this_triad_M1] 	= this_triad_M1	 
					triad_status_w3_M1[this_triad_M1] 	+= 2
				BREAK
				
				CASE 4 
				CASE 5
					IF NOT triad_movement_w3_M1[this_triad_M1] = 3  
						GOSUB mission_mansion1_SUB_triad_movement
					ELSE
						CLEAR_CHAR_TASKS triad_w3_M1[this_triad_M1] 
						SET_CHAR_DECISION_MAKER triad_w3_M1[this_triad_M1] cover_dm_M1
						TASK_TOGGLE_DUCK triad_w3_M1[this_triad_M1] TRUE
						goto_M1 = triad_moveto_w3_M1[this_triad_M1]  
						TASK_ACHIEVE_HEADING triad_w3_M1[this_triad_M1] triad_heading_M1[goto_M1]

						triad_movement_w3_M1[this_triad_M1] = -1
						triad_status_w3_M1[this_triad_M1] 	+= 2
					ENDIF
				BREAK
								
				
				LVAR_INT audio_played_M1
				CASE 6 // check for corridor being active before moving
				CASE 7
					IF player_status_M1 > 0 // TEMP //> 1

						triad_status_w3_M1[this_triad_M1] += 2

						// audio
						IF audio_played_M1 = 0
							text_status_M1[1] = 1
							audio_played_M1 = 1
						ENDIF

					ENDIF
				BREAK


				/*
				   	###########################################################

						CORRIDOR STUFF

				   	###########################################################
				*/

				/*
					CASE 8 	is used for sending the triad into a room (just the movement)
							it will wait until another triad (if alive? is covering before they move)
				*/
				CASE 8 
					IF NOT IS_CHAR_DEAD triad_w3_M1[other_triad_M1]
						IF NOT triad_status_w3_M1[other_triad_M1] = 13  
							BREAK
						ENDIF
					ENDIF

					//CLEAR_CHAR_TASKS triad_w3_M1[this_triad_M1] 
					SET_CHAR_DECISION_MAKER triad_w3_M1[this_triad_M1] movement_dm_M1
					triad_movement_w3_M1[this_triad_M1] = -1  
					triad_moveto_w3_M1[this_triad_M1] 	= room_pointer_M1
					triad_status_w3_M1[this_triad_M1] 	+= 2
				BREAK

				/*
					CASE 9 	send a triad to cover the room
				*/
				CASE 9
					//CLEAR_CHAR_TASKS triad_w3_M1[this_triad_M1] 
					SET_CHAR_DECISION_MAKER triad_w3_M1[this_triad_M1] movement_dm_M1
					triad_movement_w3_M1[this_triad_M1] = -1
					triad_moveto_w3_M1[this_triad_M1] 	= cover_pointer_M1
					triad_status_w3_M1[this_triad_M1] += 2				
				BREAK
				
				/*
					CASE 10 check for the triad arriving at the room
				*/				
				CASE 10 
					IF NOT triad_movement_w3_M1[this_triad_M1] = 3 
						GOSUB mission_mansion1_SUB_triad_movement
					ELSE
						triad_room_status_w3_M1[this_triad_M1] = 1
						triad_movement_w3_M1[this_triad_M1] = -1
						triad_status_w3_M1[this_triad_M1] 	+= 2
					ENDIF				
				BREAK	

				/*
					CASE 11 triad moving to cover room 
				*/				
				CASE 11
					IF NOT triad_movement_w3_M1[this_triad_M1] = 3 
						GOSUB mission_mansion1_SUB_triad_movement
					ELSE

						//CLEAR_CHAR_TASKS triad_w3_M1[this_triad_M1] 
						SET_CHAR_DECISION_MAKER triad_w3_M1[this_triad_M1] cover_dm_M1
						
						TASK_STAY_IN_SAME_PLACE triad_w3_M1[this_triad_M1] TRUE
						TASK_TOGGLE_DUCK triad_w3_M1[this_triad_M1] TRUE
						
						triad_movement_w3_M1[this_triad_M1] = -1
						
						cover_pointer_M1 ++
						triad_status_w3_M1[this_triad_M1] 	+= 2
					ENDIF
				BREAK	
										
				/*
					CASE 12 move inside the room from the corridor 
				*/
				CASE 12
					IF NOT triad_movement_w3_M1[this_triad_M1] = 3 
						GOSUB mission_mansion1_SUB_triad_movement
					ELSE
 
						//CLEAR_CHAR_TASKS triad_w3_M1[this_triad_M1] 
						SET_CHAR_DECISION_MAKER triad_w3_M1[this_triad_M1] cover_dm_M1
						
						TASK_STAY_IN_SAME_PLACE triad_w3_M1[this_triad_M1] TRUE
						TASK_TOGGLE_DUCK triad_w3_M1[this_triad_M1] TRUE 
						triad_movement_w3_M1[this_triad_M1] = -1
						triad_clearing_w3_M1[this_triad_M1] = -1

						triad_status_w3_M1[this_triad_M1] += 2
					ENDIF					
				BREAK

				/*
					CASE 13 triad covers room and if other triad dies takes his place 
				*/				
				CASE 13
					IF NOT IS_CHAR_DEAD triad_w3_M1[other_triad_M1]
						IF NOT triad_status_w3_M1[other_triad_M1] = 11  
							BREAK
						ENDIF
					ENDIF
					
					triad_status_w3_M1[this_triad_M1] -= 5
				BREAK
			
				/*
					CASE 14 triad clearing room 
				*/				
				CASE 14 
					GOSUB mission_mansion1_SUB_triad_clear_room
					IF room_clear_M1 = 1
						room_clear_M1 = 0
						//CLEAR_CHAR_TASKS triad_w3_M1[this_triad_M1] 
						SET_CHAR_DECISION_MAKER triad_w3_M1[this_triad_M1] movement_dm_M1
						
						TASK_TOGGLE_DUCK triad_w3_M1[this_triad_M1] FALSE
						
						triad_room_status_w3_M1[this_triad_M1] = 0
						triad_movement_w3_M1[this_triad_M1] = -1  
						triad_status_w3_M1[this_triad_M1] 	++
					ENDIF
				BREAK

				/*
					CASE 15 triad runs out from room into corridor 
				*/
				CASE 15
					IF NOT triad_movement_w3_M1[this_triad_M1] = 3 
						GOSUB mission_mansion1_SUB_triad_movement
					ELSE

						IF room_pointer_M1 = 8 // send triads to balcony
							
							triad_status_w3_M1[this_triad_M1] = 16
							IF NOT IS_CHAR_DEAD triad_w3_M1[other_triad_M1]
								triad_status_w3_M1[other_triad_M1] = 16
							ENDIF
							
							text_status_M1[3] = 1

					   	ELSE
							triad_movement_w3_M1[this_triad_M1] = -1
							
							IF NOT IS_CHAR_DEAD triad_w3_M1[other_triad_M1] 
								triad_moveto_w3_M1[this_triad_M1] = cover_pointer_M1
								triad_status_w3_M1[this_triad_M1] -= 4
							ELSE
								triad_status_w3_M1[this_triad_M1] -= 7
							ENDIF
						ENDIF
					ENDIF
				BREAK

				/*
				   	###########################################################

						BALCONY STUFF

				   	###########################################################
				*/

				CASE 16
					SET_CHAR_DECISION_MAKER triad_w3_M1[this_triad_M1] movement_dm_M1
					triad_movement_w3_M1[this_triad_M1] = -1
					goto_M1 = 14 + this_triad_M1
					triad_moveto_w3_M1[this_triad_M1] = goto_M1
					triad_status_w3_M1[this_triad_M1] ++	
				BREAK

				CASE 17
					IF NOT triad_movement_w3_M1[this_triad_M1] = 3 
						GOSUB mission_mansion1_SUB_triad_movement
					ELSE
						SET_CHAR_DECISION_MAKER triad_w3_M1[this_triad_M1] cover_dm_M1
						
						TASK_STAY_IN_SAME_PLACE triad_w3_M1[this_triad_M1] TRUE
						IF triad_moveto_w3_M1[this_triad_M1] = 14
							TASK_TOGGLE_DUCK triad_w3_M1[this_triad_M1] TRUE
						ENDIF

						goto_M1 = triad_moveto_w3_M1[this_triad_M1]  
						TASK_ACHIEVE_HEADING triad_w3_M1[this_triad_M1] triad_heading_M1[goto_M1]
						triad_status_w3_M1[this_triad_M1] ++ // finished just run decision maker until dead or mission passed
					ENDIF
				BREAK	

			ENDSWITCH
		ELSE
			IF NOT triad_status_w3_M1[this_triad_M1] = -2
				SWITCH triad_status_w3_M1[this_triad_M1]

					DEFAULT
						triad_timer_w3_M1[this_triad_M1] = 0
						triad_status_w3_M1[this_triad_M1] = -1
					BREAK

					CASE -1
						IF triad_timer_w3_M1[this_triad_M1] < 10000
							BREAK
						ENDIF

						REMOVE_CHAR_ELEGANTLY triad_w3_M1[this_triad_M1]
						MARK_MODEL_AS_NO_LONGER_NEEDED triad_model_w3_M1[this_triad_M1] 

						triad_status_w3_M1[this_triad_M1] --

			   ENDSWITCH
			ENDIF
		ENDIF

		this_triad_M1  ++
		other_triad_M1 --

	ENDWHILE	

RETURN

// ###############################################################################################################

LVAR_INT goto_M1
LVAR_FLOAT goto_X_M1 goto_Y_M1 goto_Z_M1
mission_mansion1_SUB_triad_movement:

	goto_M1 = triad_moveto_w3_M1[this_triad_M1]

	goto_X_M1 = triad_X_M1[goto_M1]
	goto_Y_M1 = triad_Y_M1[goto_M1]
	goto_Z_M1 = triad_Z_M1[goto_M1]

	IF triad_room_status_w3_M1[this_triad_M1] = 1
		SWITCH room_pointer_M1
			CASE 2
			CASE 4
			CASE 6
				goto_X_M1 -= 3.5
			BREAK
			CASE 3
			CASE 5
			CASE 7
				goto_X_M1 += 3.5
			BREAK
		ENDSWITCH 
	ENDIF
	
	SWITCH triad_movement_w3_M1[this_triad_M1]

		CASE -1 // start
			IF IS_CHAR_RESPONDING_TO_EVENT triad_w3_M1[this_triad_M1] -1
				TASK_STAY_IN_SAME_PLACE triad_w3_M1[this_triad_M1] TRUE
				triad_movement_w3_M1[this_triad_M1] = 0 // not moving
			ELSE
				//CLEAR_CHAR_TASKS triad_w3_M1[this_triad_M1] 
				TASK_STAY_IN_SAME_PLACE triad_w3_M1[this_triad_M1] FALSE
				TASK_TOGGLE_DUCK triad_w3_M1[this_triad_M1] FALSE 
				 
				TASK_GO_STRAIGHT_TO_COORD triad_w3_M1[this_triad_M1] goto_X_M1 goto_Y_M1 goto_Z_M1 PEDMOVE_RUN -2
				triad_movement_w3_M1[this_triad_M1] = 1 // moving 
			ENDIF
		BREAK

		CASE 0 // shooting 
			IF NOT IS_CHAR_RESPONDING_TO_EVENT triad_w3_M1[this_triad_M1] -1
				triad_timer_w3_M1[this_triad_M1] = 0
				triad_movement_w3_M1[this_triad_M1] = 2 // timer just incase triad stops then starts shooting again 
			ENDIF
		BREAK

		CASE 1 // moving
			IF IS_CHAR_RESPONDING_TO_EVENT triad_w3_M1[this_triad_M1] -1
				//CLEAR_CHAR_TASKS triad_w3_M1[this_triad_M1]
				//TASK_STAY_IN_SAME_PLACE triad_w3_M1[this_triad_M1] TRUE
				triad_movement_w3_M1[this_triad_M1] = -1 
			ELSE
				
				IF LOCATE_CHAR_ON_FOOT_3D triad_w3_M1[this_triad_M1] goto_X_M1 goto_Y_M1 goto_Z_M1 1.2 1.2 1.5 FALSE 
					triad_movement_w3_M1[this_triad_M1] = 3 // reached destination
				ELSE
					GET_SCRIPT_TASK_STATUS triad_w3_M1[this_triad_M1] TASK_GO_STRAIGHT_TO_COORD task_status_M1
					IF task_status_M1 = FINISHED_TASK
						CLEAR_CHAR_TASKS triad_w3_M1[this_triad_M1] 
						triad_movement_w3_M1[this_triad_M1] = -1 	
					ENDIF
				ENDIF
			ENDIF
		BREAK

		CASE 2 // temp timer before setting triad moving again
			IF IS_CHAR_RESPONDING_TO_EVENT triad_w3_M1[this_triad_M1] -1
				triad_movement_w3_M1[this_triad_M1] = 0 // not moving
			ELSE
				IF triad_timer_w3_M1[this_triad_M1] > 500
					triad_movement_w3_M1[this_triad_M1] = -1 // restart moving
				ENDIF
			ENDIF
		BREAK

	ENDSWITCH

RETURN

// ###############################################################################################################

LVAR_INT triad_clearing_w3_M1[2]
mission_mansion1_SUB_triad_clear_room:

	SWITCH triad_clearing_w3_M1[this_triad_M1]
		CASE -1
			IF NOT IS_CHAR_RESPONDING_TO_EVENT triad_w3_M1[this_triad_M1] -1
				triad_timer_w3_M1[this_triad_M1] = 0
				triad_clearing_w3_M1[this_triad_M1] ++ 	
			ENDIF
		BREAK
		
		CASE 0
			IF IS_CHAR_RESPONDING_TO_EVENT triad_w3_M1[this_triad_M1] -1
				triad_clearing_w3_M1[this_triad_M1] --
			ELSE
				IF triad_timer_w3_M1[this_triad_M1] > 300
					triad_clearing_w3_M1[this_triad_M1] ++	
				ENDIF
			ENDIF 	
		BREAK
		
		CASE 1
			GOSUB mission_mansion1_SUB_room_clear_check
			IF room_clear_M1 = 0
				triad_timer_w3_M1[this_triad_M1] = 150
				triad_clearing_w3_M1[this_triad_M1] --
			ENDIF
		BREAK 
	ENDSWITCH

RETURN

// ###############################################################################################################

// checks for the triad clearing the room and plays the corrent audio sample (only if players close by?)
LVAR_INT room_clear_M1
LVAR_FLOAT room_check_TL_X room_check_TL_Y room_check_BR_X room_check_BR_Y  

LVAR_INT triad_clear_sound_M1[8] clear_audio_pointer_M1
LVAR_TEXT_LABEL triad_clear_text_M1[8]

mission_mansion1_SUB_room_clear_check:

	room_clear_M1 = 0

	// check guys in that room are dead
	SWITCH room_pointer_M1
		CASE 2 // room 1
			
			IF lsv_room_status_M1[0] = -1
			AND lsv_room_status_M1[1] = -1
				room_clear_M1 = 1
				
				room_check_TL_X	= 1271.3411
				room_check_TL_Y	= -785.5189
				room_check_BR_X	= 1278.6420
				room_check_BR_Y = -798.1946
 			ENDIF
		BREAK
		CASE 3
			IF lsv_room_status_M1[2] = -1
			AND lsv_room_status_M1[3] = -1
				room_clear_M1 = 1

				room_check_TL_X	= 1285.9703
				room_check_TL_Y	= -785.5347
				room_check_BR_X	= 1293.1008
				room_check_BR_Y = -799.4673
			ENDIF
		BREAK
		CASE 4
			IF lsv_room_status_M1[4] = -1
				room_clear_M1 = 1

				room_check_TL_X	= 1271.4166
				room_check_TL_Y	= -799.7592
				room_check_BR_X	= 1278.6387
				room_check_BR_Y = -810.0243
			ENDIF
		BREAK
		CASE 5
			IF lsv_room_status_M1[5] = -1
			AND lsv_room_status_M1[6] = -1
				room_clear_M1 = 1

				room_check_TL_X	= 1285.9594
				room_check_TL_Y	= -800.8917
				room_check_BR_X	= 1293.1208
				room_check_BR_Y = -811.5349
			ENDIF
		BREAK
		CASE 6
			IF lsv_room_status_M1[7] = -1
				room_clear_M1 = 1

				room_check_TL_X	= 1271.4344
				room_check_TL_Y	= -811.7203
				room_check_BR_X	= 1278.5967
				room_check_BR_Y = -822.8848
			ENDIF
		BREAK
		CASE 7
			IF lsv_room_status_M1[8] = -1
			AND lsv_room_status_M1[9] = -1
				room_clear_M1 = 1

				room_check_TL_X	= 1285.9441
				room_check_TL_Y	= -812.8881
				room_check_BR_X	= 1293.2382
				room_check_BR_Y = -823.5223
			ENDIF
		BREAK
	ENDSWITCH

	// check non-room lsv
	IF room_clear_M1 = 1
		pointer_M1 = 0
		WHILE pointer_M1 < 20

			IF NOT IS_CHAR_DEAD lsv_inside_M1[pointer_M1]
				IF IS_CHAR_IN_AREA_2D lsv_inside_M1[pointer_M1] room_check_TL_X room_check_TL_Y room_check_BR_X room_check_BR_Y FALSE
					room_clear_M1 = 0
					pointer_M1 = 20 // quit out of while loop
				ENDIF 	
			ENDIF

			pointer_M1 ++
		ENDWHILE
	ENDIF
		
	// check spawn lsv
	IF room_clear_M1 = 1
		pointer_M1 = 0
		WHILE pointer_M1 < 3

			IF NOT IS_CHAR_DEAD lsv_spawn_M1[pointer_M1]
				IF IS_CHAR_IN_AREA_2D lsv_spawn_M1[pointer_M1] room_check_TL_X room_check_TL_Y room_check_BR_X room_check_BR_Y FALSE
					room_clear_M1 = 0
					pointer_M1 = 2 // quit out of while loop
				ENDIF 	
			ENDIF

			pointer_M1 ++
		ENDWHILE
	ENDIF
	
	// play audio
	IF room_clear_M1 = 1
		
		room_pointer_M1 ++

		IF NOT IS_MESSAGE_BEING_DISPLAYED
			
			IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer triad_w3_M1[this_triad_M1] 35.0 35.0 4.0 FALSE

				IF this_triad_M1 = 0
					load_sample = triad_clear_sound_M1[clear_audio_pointer_M1]
					$load_text = $triad_clear_text_M1[clear_audio_pointer_M1]
				ELSE
					clear_audio_pointer_M1 += 4
					load_sample = triad_clear_sound_M1[clear_audio_pointer_M1]
					$load_text = $triad_clear_text_M1[clear_audio_pointer_M1]
					clear_audio_pointer_M1 -= 4
				ENDIF
				
				START_NEW_SCRIPT audio_load_and_play 2 100 triad_w3_M1[this_triad_M1]

				clear_audio_pointer_M1 ++
				IF clear_audio_pointer_M1 = 4
					clear_audio_pointer_M1 = 0
				ENDIF

			ENDIF

		ENDIF
	ENDIF
	 

RETURN

// ###############################################################################################################

mission_mansion1_SUB_clean_up_lsv_inside:

	// non room lsv
	pointer_M1 = 0
	WHILE pointer_M1 < 20
	   SWITCH lsv_inside_status_M1[pointer_M1]
			CASE 0
				IF NOT IS_CHAR_DEAD lsv_inside_M1[pointer_M1]
					BREAK
				ELSE
					lsv_inside_timer_M1[pointer_M1] = 0
					lsv_inside_status_M1[pointer_M1] = 1
				ENDIF
			CASE 1
				IF lsv_inside_timer_M1[pointer_M1] > 2500 
					MARK_CHAR_AS_NO_LONGER_NEEDED lsv_inside_M1[pointer_M1]
					lsv_inside_status_M1[pointer_M1] = -1
				ENDIF 
			BREAK
		ENDSWITCH
		pointer_M1 ++
	ENDWHILE
	
	// room lsv
	pointer_M1 = 0
	WHILE pointer_M1 < 10
	   SWITCH lsv_room_status_M1[pointer_M1]
			CASE 0
				IF NOT IS_CHAR_DEAD lsv_room_M1[pointer_M1]
					BREAK
				ELSE
					lsv_room_timer_M1[pointer_M1] = 0
					lsv_room_status_M1[pointer_M1] = 1
				ENDIF
			CASE 1
				IF lsv_room_timer_M1[pointer_M1] > 1800 
					MARK_CHAR_AS_NO_LONGER_NEEDED lsv_room_M1[pointer_M1]
					lsv_room_status_M1[pointer_M1] = -1
				ENDIF 
			BREAK
		ENDSWITCH
		pointer_M1 ++
	ENDWHILE

	// gang girls
	pointer_M1 = 0
	WHILE pointer_M1 < 4
	   SWITCH lsv_girl_status_M1[pointer_M1]
			CASE 0
				IF NOT IS_CHAR_DEAD lsv_girl_M1[pointer_M1]
					BREAK
				ELSE
					lsv_girl_timer_M1[pointer_M1] = 0
					lsv_girl_status_M1[pointer_M1] = 1
				ENDIF
			CASE 1
				IF lsv_girl_timer_M1[pointer_M1] > 1800 
					MARK_CHAR_AS_NO_LONGER_NEEDED lsv_girl_M1[pointer_M1]
					lsv_girl_status_M1[pointer_M1] = -1
				ENDIF 
			BREAK
		ENDSWITCH
		pointer_M1 ++
	ENDWHILE
	
RETURN

// ###############################################################################################################

LVAR_INT ped_area_M1 spawn_point_M1 run_this_frame_M1 extra_man_M1 random_room_M1 
mission_mansion1_SUB_get_spawn_points:
	
	GET_CHAR_COORDINATES scplayer x y z

	IF z > 1089.0 // player upstairs
		IF y > -784.0 // start section
			ped_area_M1 = 0	
		ELSE
			IF x < 1271.0
			OR x > 1294.0
				ped_area_M1 = 0	
			ELSE
				IF y > -824.0
					IF x < 1279.0 // room 1,3,5
						IF y > -799.0
							ped_area_M1 = 1
						ELSE
							IF y > -811.0 
								ped_area_M1 = 3
							ELSE
								ped_area_M1 = 5
							ENDIF
						ENDIF
					ELSE
						IF x > 1285.0 // room 2,4,6
							IF y > -800.0 
								ped_area_M1 = 2
							ELSE
								IF y > -812.0 
									ped_area_M1 = 4
								ELSE
									ped_area_M1 = 6
								ENDIF
							ENDIF
						ELSE
							IF y > -806.0
								ped_area_M1 = 7
							ELSE
								ped_area_M1 = 8
							ENDIF	
						ENDIF
					ENDIF
				ELSE
					ped_area_M1 = 9
				ENDIF 
			ENDIF   
		ENDIF
	ELSE
		stop_lsv_spawn_M1 = 1
		ped_area_M1 = 10 // downstairs
	ENDIF
	
	IF extra_man_M1 = 1
		GENERATE_RANDOM_INT_IN_RANGE 0 2 random_room_M1
	ELSE
		random_room_M1 = 0
	ENDIF
			
	spawn_point_M1 = 0
	IF NOT stop_lsv_spawn_M1 = 1
		SWITCH ped_area_M1
			
			CASE 0 // player is in start area
				SET_BIT	spawn_point_M1 1
				SET_BIT	spawn_point_M1 2
			BREAK
			
			CASE 1 // player is in room 1
			CASE 2
			CASE 3
			CASE 7
				IF random_room_M1 = 0
					SET_BIT	spawn_point_M1 2
				ELSE
					SET_BIT	spawn_point_M1 5
				ENDIF
				SET_BIT	spawn_point_M1 3
			BREAK

			CASE 4 // player is in room 4
			CASE 5
				SET_BIT	spawn_point_M1 1
				SET_BIT	spawn_point_M1 3
			BREAK
			
			CASE 6 // player is in room 6
				SET_BIT	spawn_point_M1 1
				SET_BIT	spawn_point_M1 0
			BREAK
			
			CASE 8 // bottom of corridor
			CASE 9
				SET_BIT	spawn_point_M1 0
				SET_BIT	spawn_point_M1 4
			BREAK 
			CASE 10 // downstairs
				SET_BIT	spawn_point_M1 2
				SET_BIT	spawn_point_M1 4
			BREAK

		ENDSWITCH
	ENDIF

	run_this_frame_M1 = 1
RETURN

// ###############################################################################################################

LVAR_INT lsv_spawn_M1[3] lsv_spawn_status_M1[3] lsv_spawn_timer_M1[3] lsv_spawn_model_M1[3] lsv_spawn_pointer_M1 last_spawn_point_M1 spawn_time_M1 stop_lsv_spawn_M1 
LVAR_FLOAT room_X_M1[6] room_Y_M1[6] room_Z_M1[6] room_heading_M1[6] 
mission_mansion1_SUB_lsv_spawn_controller:

	lsv_spawn_pointer_M1 = 0

	WHILE lsv_spawn_pointer_M1 < 3
		
		IF NOT IS_CHAR_DEAD lsv_spawn_M1[lsv_spawn_pointer_M1] 	
			
			SWITCH lsv_spawn_status_M1[lsv_spawn_pointer_M1]
				CASE 1
					IF lsv_spawn_sample_M1 < 4
						IF NOT IS_MESSAGE_BEING_DISPLAYED 
							IF LOCATE_CHAR_ANY_MEANS_CHAR_2D lsv_spawn_M1[lsv_spawn_pointer_M1] scplayer 15.0 15.0 FALSE
								
								load_sample = lsv_spawn_sound_M1[lsv_spawn_sample_M1]
								$load_text = $lsv_spawn_text_M1[lsv_spawn_sample_M1]
								START_NEW_SCRIPT audio_load_and_play 2 50 lsv_spawn_M1[lsv_spawn_pointer_M1]	
								
								lsv_spawn_status_M1[lsv_spawn_pointer_M1] = 0
								lsv_spawn_sample_M1 ++
							ENDIF
						ENDIF
					ENDIF
				BREAK 
			ENDSWITCH
		ELSE
			
			SWITCH lsv_spawn_status_M1[lsv_spawn_pointer_M1]
			
				CASE -1
					IF lsv_spawn_timer_M1[lsv_spawn_pointer_M1] < spawn_time_M1
						BREAK
					ENDIF
					REMOVE_CHAR_ELEGANTLY lsv_spawn_M1[lsv_spawn_pointer_M1]
					
					IF lsv_spawn_pointer_M1 = 2
						IF extra_man_M1 = 0 
							lsv_spawn_status_M1[lsv_spawn_pointer_M1] ++
						ELSE
							lsv_spawn_status_M1[lsv_spawn_pointer_M1] --
						ENDIF
					ENDIF

				CASE -2
	
					IF NOT stop_lsv_spawn_M1 = 1

						IF run_this_frame_M1 = 0
							GOSUB mission_mansion1_SUB_get_spawn_points
						ENDIF

						pointer_M1 = 0
						WHILE pointer_M1 < 6

							IF IS_BIT_SET spawn_point_M1 pointer_M1
								IF NOT pointer_M1 = last_spawn_point_M1
									
									IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY room_X_M1[pointer_M1] room_Y_M1[pointer_M1] room_Z_M1[pointer_M1] 1.5 1.5 0.0
										
										CREATE_CHAR PEDTYPE_MISSION2 LSV1 room_X_M1[pointer_M1] room_Y_M1[pointer_M1] room_Z_M1[pointer_M1] lsv_spawn_M1[lsv_spawn_pointer_M1]
										SET_CHAR_HEALTH lsv_spawn_M1[lsv_spawn_pointer_M1] 200	
										SET_CHAR_MAX_HEALTH lsv_spawn_M1[lsv_spawn_pointer_M1] 200
										SET_CHAR_HEADING lsv_spawn_M1[lsv_spawn_pointer_M1] room_heading_M1[pointer_M1]
										SET_CHAR_AREA_VISIBLE lsv_spawn_M1[lsv_spawn_pointer_M1] 5
										
										GIVE_WEAPON_TO_CHAR lsv_spawn_M1[lsv_spawn_pointer_M1] WEAPONTYPE_MP5 30000
										SET_CURRENT_CHAR_WEAPON lsv_spawn_M1[lsv_spawn_pointer_M1] WEAPONTYPE_MP5
										SET_CHAR_DECISION_MAKER lsv_spawn_M1[lsv_spawn_pointer_M1] cover_dm_M1
										SET_CHAR_DROPS_WEAPONS_WHEN_DEAD lsv_spawn_M1[lsv_spawn_pointer_M1] FALSE
										//TASK_TOGGLE_DUCK lsv_spawn_M1[lsv_spawn_pointer_M1] TRUE
										TASK_KILL_CHAR_ON_FOOT lsv_spawn_M1[lsv_spawn_pointer_M1] scplayer 
										
										SET_CHAR_ACCURACY lsv_spawn_M1[lsv_spawn_pointer_M1] 50
										SET_CHAR_SHOOT_RATE lsv_spawn_M1[lsv_spawn_pointer_M1] 60 

										IF lsv_spawn_pointer_M1 = 0
											lsv_spawn_status_M1[lsv_spawn_pointer_M1] = 1									 
										ELSE
											lsv_spawn_status_M1[lsv_spawn_pointer_M1] = 0
										ENDIF						
										last_spawn_point_M1 = pointer_M1
										pointer_M1 = 5
									ENDIF 
								ENDIF  
							ENDIF
						ENDIF
						pointer_M1 ++
					ENDWHILE					
				BREAK

				DEFAULT 
					lsv_spawn_timer_M1[lsv_spawn_pointer_M1] = 0 
					lsv_spawn_status_M1[lsv_spawn_pointer_M1] = -1

			ENDSWITCH
		ENDIF 	
		lsv_spawn_pointer_M1 ++
	ENDWHILE 			

RETURN

// #########################################################################################################

LVAR_INT big_poppa_status_M1 told_to_follow_M1 
LVAR_FLOAT poppa_X_M1 poppa_Y_M1
LVAR_FLOAT poppa_posX_M1 poppa_posY_M1 poppa_pos_heading_M1 
mission_mansion1_SUB_big_poppa_controller:

	IF NOT IS_CHAR_DEAD big_poppa_M1
		SWITCH big_poppa_status_M1

			CASE 0
				IF NOT LOCATE_CHAR_ON_FOOT_2D big_poppa_M1 poppa_X_M1 poppa_Y_M1 1.0 1.0 FALSE
					BREAK
				ENDIF

				IF NOT poppa_posX_M1 = -1.0 
					IF told_to_follow_M1 = 0
						PRINT_NOW MAN1_21 8000 1
						told_to_follow_M1 = 1
					ENDIF 
					CLEAR_CHAR_TASKS_IMMEDIATELY big_poppa_M1
					SET_CHAR_COORDINATES big_poppa_M1 poppa_posX_M1 poppa_posY_M1 1083.01
					SET_CHAR_HEADING big_poppa_M1 poppa_pos_heading_M1
				ELSE
					DISABLE_ALL_ENTRY_EXITS FALSE
					SWITCH_ENTRY_EXIT mddogs TRUE
					ADD_BLIP_FOR_COORD 1298.7405 -795.9946 1083.0078 goto_blip_M1
					SET_BLIP_ENTRY_EXIT goto_blip_M1 1258.3376 -785.4264 91.0313
  
					PRINT_NOW MAN1_22 8000 1
					SET_CHAR_COORDINATES big_poppa_M1 1261.0309 -815.6660 83.1484
					SET_CHAR_HEADING big_poppa_M1 65.0097 
					SET_CHAR_AREA_VISIBLE big_poppa_M1 0
					FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION big_poppa_M1 TRUE
					big_poppa_status_M1 = 1

					BREAK
				ENDIF 			
				big_poppa_status_M1 = -1		
			BREAK

			CASE 1
				GET_AREA_VISIBLE current_area_M1
				IF current_area_M1 = 0
					big_poppa_status_M1 = 2
				ENDIF	 
			
				
		ENDSWITCH
	ENDIF

RETURN

// ###############################################################################################################

LVAR_INT setup_bar_M1 
mission_mansion1_SUB_clean_mansion_part1:

	// triads
	DELETE_CHAR triad_w3_M1[0]
	DELETE_CHAR triad_w3_M1[1]
	MARK_MODEL_AS_NO_LONGER_NEEDED TRIADA
	MARK_MODEL_AS_NO_LONGER_NEEDED TRIADB
	MARK_MODEL_AS_NO_LONGER_NEEDED M4

	// lsv
	pointer_M1 = 0
	WHILE pointer_M1 < 10
		DELETE_CHAR lsv_room_M1[pointer_M1]
		pointer_M1 ++
	ENDWHILE

	pointer_M1 = 0
	WHILE pointer_M1 < 13
		MARK_CHAR_AS_NO_LONGER_NEEDED lsv_inside_M1[pointer_M1]
		pointer_M1 ++
	ENDWHILE

	MARK_CHAR_AS_NO_LONGER_NEEDED lsv_spawn_M1[0]
	MARK_CHAR_AS_NO_LONGER_NEEDED lsv_spawn_M1[1]
	MARK_CHAR_AS_NO_LONGER_NEEDED lsv_spawn_M1[2]

	MARK_CHAR_AS_NO_LONGER_NEEDED lsv_girl_M1[0]
	MARK_CHAR_AS_NO_LONGER_NEEDED lsv_girl_M1[1]
	MARK_CHAR_AS_NO_LONGER_NEEDED lsv_girl_M1[2]
	MARK_CHAR_AS_NO_LONGER_NEEDED lsv_girl_M1[3]

	MARK_MODEL_AS_NO_LONGER_NEEDED BFYST
	MARK_MODEL_AS_NO_LONGER_NEEDED GANGRL3

	REMOVE_DECISION_MAKER ganggirl_dm_M1
	REMOVE_DECISION_MAKER movement_dm_M1

	REQUEST_MODEL BMYBE
	REQUEST_MODEL BMYDJ
	REQUEST_MODEL BFYPRO
	REQUEST_MODEL BFYBE
	setup_bar_M1 = 1

RETURN

// ###############################################################################################################

LVAR_INT bar_peds_M1[5]
mission_mansion1_SUB_setup_bar:

	SWITCH setup_bar_M1
	
		CASE 1
				
			CREATE_CHAR PEDTYPE_MISSION2 LSV3 1260.0638 -793.2798 1083.2415 lsv_inside_M1[14]  
			SET_CHAR_HEADING lsv_inside_M1[14] 290.4863
			SET_CHAR_HEALTH lsv_inside_M1[14] 200	
			SET_CHAR_MAX_HEALTH lsv_inside_M1[14] 200			
			SET_CHAR_AREA_VISIBLE lsv_inside_M1[14] 5
			GIVE_WEAPON_TO_CHAR lsv_inside_M1[14] WEAPONTYPE_MP5 30000
			SET_CURRENT_CHAR_WEAPON lsv_inside_M1[14] WEAPONTYPE_MP5
			SET_CHAR_ACCURACY lsv_inside_M1[14] 80
			SET_CHAR_SHOOT_RATE lsv_inside_M1[14] 60
			SET_CHAR_DECISION_MAKER lsv_inside_M1[14] cover_dm_M1	 
			lsv_inside_status_M1[14] = 0
				
			CREATE_CHAR PEDTYPE_MISSION2 LSV3 1257.7017 -767.9689 1083.0078 lsv_inside_M1[15]  
			SET_CHAR_HEADING lsv_inside_M1[15] 119.2357
			SET_CHAR_HEALTH lsv_inside_M1[15] 200	
			SET_CHAR_MAX_HEALTH lsv_inside_M1[15] 200
			SET_CHAR_AREA_VISIBLE lsv_inside_M1[15] 5
			GIVE_WEAPON_TO_CHAR lsv_inside_M1[15] WEAPONTYPE_MP5 30000
			SET_CURRENT_CHAR_WEAPON lsv_inside_M1[15] WEAPONTYPE_MP5
			SET_CHAR_ACCURACY lsv_inside_M1[15] 70
			SET_CHAR_SHOOT_RATE lsv_inside_M1[15] 50
			SET_CHAR_DECISION_MAKER lsv_inside_M1[15] cover_dm_M1	 
			lsv_inside_status_M1[15] = 0

			CREATE_CHAR PEDTYPE_MISSION2 LSV3 1289.8137 -778.2963 1083.0078 lsv_inside_M1[16]  
			SET_CHAR_HEADING lsv_inside_M1[16] 67.6874
			SET_CHAR_HEALTH lsv_inside_M1[16] 200	
			SET_CHAR_MAX_HEALTH lsv_inside_M1[16] 200
			SET_CHAR_AREA_VISIBLE lsv_inside_M1[16] 5
			GIVE_WEAPON_TO_CHAR lsv_inside_M1[16] WEAPONTYPE_MP5 30000
			SET_CURRENT_CHAR_WEAPON lsv_inside_M1[16] WEAPONTYPE_MP5
			SET_CHAR_ACCURACY lsv_inside_M1[16] 70
			SET_CHAR_SHOOT_RATE lsv_inside_M1[16] 60
			SET_CHAR_DECISION_MAKER lsv_inside_M1[16] cover_dm_M1	 
			lsv_inside_status_M1[16] = 0
				
			CREATE_CHAR PEDTYPE_MISSION2 LSV3 1282.8859 -779.3019 1083.0148 lsv_inside_M1[17]  
			SET_CHAR_HEADING lsv_inside_M1[17] 57.0420
			SET_CHAR_HEALTH lsv_inside_M1[17] 200	
			SET_CHAR_MAX_HEALTH lsv_inside_M1[17] 200
			SET_CHAR_AREA_VISIBLE lsv_inside_M1[17] 5
			GIVE_WEAPON_TO_CHAR lsv_inside_M1[17] WEAPONTYPE_MP5 30000
			SET_CURRENT_CHAR_WEAPON lsv_inside_M1[17] WEAPONTYPE_MP5
			SET_CHAR_ACCURACY lsv_inside_M1[17] 70
			SET_CHAR_SHOOT_RATE lsv_inside_M1[17] 60
			SET_CHAR_DECISION_MAKER lsv_inside_M1[17] cover_dm_M1	 
			lsv_inside_status_M1[17] = 0
				
			setup_bar_M1 ++

		CASE 2
		
			IF NOT HAS_MODEL_LOADED BMYBE
			OR NOT HAS_MODEL_LOADED BMYDJ
			OR NOT HAS_MODEL_LOADED BFYPRO
			OR NOT HAS_MODEL_LOADED BFYBE
		 		BREAK
			ENDIF
	
			CREATE_CHAR PEDTYPE_MISSION3 BMYDJ 1275.1547 -788.5061 1083.0078 bar_peds_M1[0]
			SET_CHAR_HEADING bar_peds_M1[0] 152.5500
			SET_CHAR_AREA_VISIBLE bar_peds_M1[0] 5
			SET_CHAR_DECISION_MAKER bar_peds_M1[0] bar_dm_M1

			CREATE_CHAR PEDTYPE_MISSION3 BFYPRO 1274.5399 -790.0915 1083.0078 bar_peds_M1[1]
			SET_CHAR_HEADING bar_peds_M1[1] 349.0896
			SET_CHAR_AREA_VISIBLE bar_peds_M1[1] 5
			SET_CHAR_DECISION_MAKER bar_peds_M1[1] bar_dm_M1

			CREATE_CHAR PEDTYPE_MISSION3 BMYBE 1273.2789 -788.6833 1083.0078 bar_peds_M1[2]
			SET_CHAR_HEADING bar_peds_M1[2] 221.2157
			SET_CHAR_AREA_VISIBLE bar_peds_M1[2] 5
			SET_CHAR_DECISION_MAKER bar_peds_M1[2] bar_dm_M1

			CREATE_CHAR PEDTYPE_MISSION3 BFYBE 1261.4993 -788.9477 1083.0148 bar_peds_M1[3]
			SET_CHAR_HEADING bar_peds_M1[3] 154.0331
			SET_CHAR_AREA_VISIBLE bar_peds_M1[3] 5
			SET_CHAR_DECISION_MAKER bar_peds_M1[3] bar_dm_M1

			CREATE_CHAR PEDTYPE_MISSION3 BMYBE 1260.8873 -790.4300 1083.0148 bar_peds_M1[4]
			SET_CHAR_HEADING bar_peds_M1[4] 332.9453
			SET_CHAR_AREA_VISIBLE bar_peds_M1[4] 5
			SET_CHAR_DECISION_MAKER bar_peds_M1[4] bar_dm_M1

			setup_bar_M1 = 0

	ENDSWITCH

RETURN











// ##########################################################################################################
// ##
// ## OUTSIDE MANSION CHASE
// ##
// ##########################################################################################################

LVAR_INT big_poppa_car_M1 player_car_M1
mission_mansion1_MAIN_outside_chase:

	CLEAR_PRINTS

	current_area_M1 = 5
	WHILE current_area_M1 = 5 
		WAIT 0
		GET_CHAR_AREA_VISIBLE scplayer current_area_M1
	ENDWHILE  

	SWITCH_AUDIO_ZONE MADDOGl FALSE
	
	DO_FADE 0 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
	
	SWITCH_ENTRY_EXIT maddogs FALSE 
	SWITCH_ENTRY_EXIT mddogs FALSE
	
	CLEAR_AREA_OF_CARS 1239.9044 -800.9069 80.0 1261.7509 -817.2160 90.0

	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON
	
	GOSUB mission_mansion1_SUB_clean_mansion_part2

	SET_CAR_DENSITY_MULTIPLIER 0.5

	REQUEST_MODEL PHOENIX
	REQUEST_MODEL WINDSOR
	REQUEST_CAR_RECORDING 708 

	WHILE NOT HAS_MODEL_LOADED PHOENIX 
	OR NOT HAS_MODEL_LOADED WINDSOR
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 708 
		WAIT 0
	ENDWHILE	

	LOAD_SCENE 1265.0092 -816.3813 83.6305 
	
	CREATE_CAR PHOENIX 1250.5194 -812.1237 83.1484 big_poppa_car_M1 
	SET_CAR_HEADING big_poppa_car_M1 162.4757 
	
	CUSTOM_PLATE_FOR_NEXT_CAR WINDSOR J_LOMAX
	SET_CAR_MODEL_COMPONENTS WINDSOR 1 1
	CREATE_CAR WINDSOR 1244.0258 -808.5705 83.1484 player_car_M1 
	SET_CAR_HEADING player_car_M1 177.4448 	
  	CHANGE_CAR_COLOUR player_car_M1 126 0

	SET_RADIO_CHANNEL RS_DISCO_FUNK_SOUL

	IF NOT IS_CHAR_DEAD big_poppa_M1
		CLEAR_CHAR_TASKS_IMMEDIATELY big_poppa_M1
		SET_CHAR_DECISION_MAKER big_poppa_M1 empty_dm_M1
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION big_poppa_M1 FALSE
		SET_CHAR_AREA_VISIBLE big_poppa_M1 0
		SET_CHAR_COORDINATES big_poppa_M1 1268.6998 -813.0891 83.1484
		SET_CHAR_HEADING big_poppa_M1 90.0
		REMOVE_BLIP big_poppa_blip_M1 
   	ENDIF


	SET_CHAR_COORDINATES scplayer 1268.6998 -813.0891 83.1484
	SET_CHAR_HEADING scplayer 90.0

	CAMERA_RESET_NEW_SCRIPTABLES
	CAMERA_SET_SHAKE_SIMULATION_SIMPLE 1 50000.0 0.5
	SET_FIXED_CAMERA_POSITION 1265.0092 -816.3813 83.6305 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 1264.0457 -816.1189 83.6842 JUMP_CUT

	DO_FADE 500 FADE_IN
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	IF NOT IS_CHAR_DEAD big_poppa_M1
	AND NOT IS_CAR_DEAD big_poppa_car_M1
		OPEN_SEQUENCE_TASK sequence_M1
			TASK_STAND_STILL -1 1500
			TASK_ENTER_CAR_AS_DRIVER -1 big_poppa_car_M1 9999
		CLOSE_SEQUENCE_TASK sequence_M1	
		
		PERFORM_SEQUENCE_TASK big_poppa_M1 sequence_M1
		CLEAR_SEQUENCE_TASK sequence_M1	

		OPEN_SEQUENCE_TASK sequence_M1
			TASK_STAND_STILL -1 7000
			TASK_GO_STRAIGHT_TO_COORD -1 1251.6967 -812.2870 83.1484 PEDMOVE_RUN -2
			TASK_LOOK_AT_CHAR -1 big_poppa_M1 1500 
		CLOSE_SEQUENCE_TASK sequence_M1	
	ENDIF


	
	PERFORM_SEQUENCE_TASK scplayer sequence_M1
	CLEAR_SEQUENCE_TASK sequence_M1	

	TIMERA = 0
	WHILE TIMERA < 1500
		WAIT 0
	ENDWHILE


					
	CAMERA_PERSIST_TRACK TRUE 
	CAMERA_PERSIST_POS TRUE
	CAMERA_SET_VECTOR_MOVE 1265.0092 -816.3813 83.6305 1253.4476 -815.4342 83.4116 7500 TRUE
	CAMERA_SET_VECTOR_TRACK 1264.0457 -816.1189 83.6842 1252.8605 -814.6353 83.5422 7500 TRUE
	
	cutscene_flag_M1 = 0 
	WHILE cutscene_flag_M1 = 0
		WAIT 0
		IF NOT IS_CHAR_DEAD big_poppa_M1
			IF IS_CHAR_IN_ANY_CAR big_poppa_M1
				cutscene_flag_M1 = 1
			ENDIF
		ENDIF
	ENDWHILE

	IF NOT IS_CAR_DEAD big_poppa_car_M1
		START_PLAYBACK_RECORDED_CAR big_poppa_car_M1 708
		SET_CAR_FORWARD_SPEED big_poppa_car_M1 40.0
		LOCK_CAR_DOORS big_poppa_car_M1 CARLOCK_LOCKED
		IF NOT IS_CHAR_DEAD big_poppa_M1
			SET_CHAR_PROOFS big_poppa_M1 FALSE FALSE FALSE FALSE FALSE
		ENDIF
	ENDIF

	
	TIMERA = 0
	WHILE TIMERA < 5000
		WAIT 0
	ENDWHILE



	IF NOT IS_CAR_DEAD big_poppa_car_M1
		ADD_BLIP_FOR_CAR big_poppa_car_M1 big_poppa_blip_M1
	ENDIF 
	
	PRINT_NOW MAN1_23 8000 1

	SET_PLAYER_CONTROL player1 ON
	
	SWITCH_WIDESCREEN OFF

	CAMERA_RESET_NEW_SCRIPTABLES
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER
 
 	SET_MAX_WANTED_LEVEL 6
	SET_CAR_DENSITY_MULTIPLIER 0.5 

mission_mansion1_MAIN_outside_chase_loop:

	WAIT 0

	IF NOT IS_CHAR_DEAD big_poppa_M1
		IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer big_poppa_M1 250.0 250.0 FALSE
			fail_text_flag_M1 = 1
			$fail_text_M1 = MAN1_24
			GOTO mission_mansion1_FAILED
		ENDIF
		IF IS_CHAR_IN_ANY_CAR scplayer
			STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer player_temp_car_M1
		ENDIF

		GOSUB mission_mansion1_SUB_big_poppas_car
	ELSE
		// shot of mansion
		// 1221.7875 -856.6817 99.5425 1222.3300 -855.9062 99.2196
		
		REMOVE_BLIP big_poppa_blip_M1

		TIMERA = 0
		WHILE TIMERA < 2000
			WAIT 0
		ENDWHILE

		DO_FADE 0 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		LVAR_INT radio_station_M1		
		GET_RADIO_CHANNEL radio_station_M1
		SET_RADIO_CHANNEL RS_OFF

		SET_PLAYER_CONTROL player1 OFF
		SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
		CLEAR_WANTED_LEVEL player1
		
		SWITCH_WIDESCREEN ON

		LVAR_INT player_temp_car_M1 temp_car_health_M1
		IF NOT IS_CAR_DEAD player_temp_car_M1
			GET_CAR_HEALTH player_temp_car_M1 temp_car_health_M1 
			IF temp_car_health_M1 < 500 	
				SET_CAR_HEALTH player_temp_car_M1 500
			ENDIF
			SET_CAR_FORWARD_SPEED player_temp_car_M1 0.0
		ENDIF

		REQUEST_MODEL drg_nu_ext
		WHILE NOT HAS_MODEL_LOADED drg_nu_ext
			WAIT 0
		ENDWHILE
		
		SET_FIXED_CAMERA_POSITION 1221.7875 -856.6817 99.5425 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 1222.3300 -855.9062 99.2196 JUMP_CUT
		LOAD_SCENE_IN_DIRECTION 1248.9076 -804.6260 87.5350 297.0012 

		TIMERA = 0
		WHILE TIMERA < 500
			WAIT 0
		ENDWHILE
	
		
		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS 
			WAIT 0
		ENDWHILE

	SKIP_CUTSCENE_START

		PRINT_NOW MAN1_30 7000 1

		TIMERA = 0
		WHILE TIMERA < 7000
			WAIT 0
		ENDWHILE

		DO_FADE 500 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

	SKIP_CUTSCENE_END

		DO_FADE 0 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		
		MARK_MODEL_AS_NO_LONGER_NEEDED drg_nu_ext
		
		RESTORE_CAMERA_JUMPCUT
		
		SET_PLAYER_CONTROL player1 ON
		
		SWITCH_WIDESCREEN OFF

		GET_CHAR_COORDINATES scplayer player_x player_y player_z
		GET_CHAR_HEADING scplayer heading

		LOAD_SCENE_IN_DIRECTION player_x player_y player_z heading 

		TIMERA = 0
		WHILE TIMERA < 500
			WAIT 0
		ENDWHILE

		SET_RADIO_CHANNEL radio_station_M1 
		
		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS 
			WAIT 0
		ENDWHILE

		SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
		
		GOTO mission_mansion1_PASSED	
	ENDIF
	 
GOTO mission_mansion1_MAIN_outside_chase_loop 

// ###############################################################################################################

LVAR_INT poppas_car_status_M1
mission_mansion1_SUB_big_poppas_car:

	IF NOT IS_CAR_DEAD big_poppa_car_M1
	
		SWITCH poppas_car_status_M1
			CASE 0
				IF IS_PLAYBACK_GOING_ON_FOR_CAR big_poppa_car_M1
					BREAK
				ENDIF
				
				SET_CAR_CRUISE_SPEED big_poppa_car_M1 25.0
				TASK_CAR_DRIVE_WANDER big_poppa_M1 big_poppa_car_M1 25.0 DRIVINGMODE_AVOIDCARS

				poppas_car_status_M1 ++
			BREAK
		ENDSWITCH
	ENDIF

RETURN
				  
// ###############################################################################################################

mission_mansion1_SUB_clean_mansion_part2:

	REMOVE_BLIP goto_blip_M1
	
	MARK_MODEL_AS_NO_LONGER_NEEDED LSV1
	MARK_MODEL_AS_NO_LONGER_NEEDED LSV3
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYBE
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYDJ
	MARK_MODEL_AS_NO_LONGER_NEEDED BFYPRO
	MARK_MODEL_AS_NO_LONGER_NEEDED BFYBE
   
	MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG

	REMOVE_DECISION_MAKER cover_dm_M1


RETURN



// ##########################################################################################################
// ##
// ## MAIN
// ##
// ##########################################################################################################

mission_mansion1_FAILED:
	
	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"

	SWITCH_ENTRY_EXIT maddogs FALSE 
	SWITCH_ENTRY_EXIT mddogs FALSE

	IF fail_text_flag_M1 = 1
		PRINT_NOW $fail_text_M1 8000 1
	ENDIF

RETURN

// ###############################################################################################################   

mission_mansion1_PASSED:

	pointer_M1 = 0
	WHILE pointer_M1 < 16

		IF NOT IS_CHAR_DEAD lsv_inside_M1[pointer_M1] 
			CLEAR_CHAR_TASKS lsv_inside_M1[pointer_M1]
			SET_CHAR_ACCURACY lsv_inside_M1[pointer_M1] 20
			SET_CHAR_SHOOT_RATE lsv_inside_M1[pointer_M1] 25
			TASK_STAY_IN_SAME_PLACE lsv_inside_M1[pointer_M1] TRUE
			TASK_KILL_CHAR_ON_FOOT lsv_inside_M1[pointer_M1] scplayer
			SET_CHAR_KEEP_TASK lsv_inside_M1[pointer_M1] TRUE
		ELSE
			MARK_CHAR_AS_NO_LONGER_NEEDED lsv_inside_M1[pointer_M1]
		ENDIF
		pointer_M1 ++

	ENDWHILE

	pointer_M1 = 0
	WHILE pointer_M1 < 10

		IF NOT IS_CHAR_DEAD lsv_room_M1[pointer_M1] 
			CLEAR_CHAR_TASKS lsv_room_M1[pointer_M1]
			SET_CHAR_ACCURACY lsv_inside_M1[pointer_M1] 20
			SET_CHAR_SHOOT_RATE lsv_inside_M1[pointer_M1] 25
			TASK_STAY_IN_SAME_PLACE lsv_room_M1[pointer_M1] TRUE
			TASK_KILL_CHAR_ON_FOOT lsv_room_M1[pointer_M1] scplayer
			SET_CHAR_KEEP_TASK lsv_room_M1[pointer_M1] TRUE
		ELSE
			MARK_CHAR_AS_NO_LONGER_NEEDED lsv_room_M1[pointer_M1]
		ENDIF
		pointer_M1 ++

	ENDWHILE

	pointer_M1 = 0
	WHILE pointer_M1 < 2

		IF NOT IS_CHAR_DEAD lsv_spawn_M1[pointer_M1] 
			CLEAR_CHAR_TASKS lsv_spawn_M1[pointer_M1]
			TASK_KILL_CHAR_ON_FOOT lsv_spawn_M1[pointer_M1] scplayer
			SET_CHAR_KEEP_TASK lsv_spawn_M1[pointer_M1] TRUE
		ELSE
			MARK_CHAR_AS_NO_LONGER_NEEDED lsv_spawn_M1[pointer_M1]
		ENDIF
		pointer_M1 ++

	ENDWHILE

	SWITCH_ENTRY_EXIT maddogs TRUE 
	SWITCH_ENTRY_EXIT mddogs TRUE

	flag_mansion_mission_counter ++
	REGISTER_MISSION_PASSED ( MAN_1 ) //Used in the stats 
	PLAYER_MADE_PROGRESS 1

	// car generator for sparrow on mansion roof
	SWITCH_CAR_GENERATOR car_gen_sparrow   101

	// imy stuff
	PRINT_WITH_NUMBER_BIG ( M_PASSR ) 4 5000 1 //"Mission Passed!"
	AWARD_PLAYER_MISSION_RESPECT 40//amount of respect
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1

	//stuff for craig
	REMOVE_BLIP casino_contact_blip
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT save_pickupX[1] save_pickupY[1] save_pickupZ[1] RADAR_SPRITE_SAVEHOUSE save_house_blip[1]
	CHANGE_BLIP_DISPLAY save_house_blip[1] BLIP_ONLY
RETURN
		
// ###############################################################################################################

mission_mansion1_CLEANUP:

	DISPLAY_ZONE_NAMES TRUE
	
	SWITCH_AUDIO_ZONE MADDOGl FALSE
	
	IF IS_PLAYER_PLAYING player1
		SET_PLAYER_GROUP_RECRUITMENT player1 TRUE
		SET_SCRIPT_LIMIT_TO_GANG_SIZE 99
	ENDIF

	START_NEW_SCRIPT terminate_audio_controller
	
	REMOVE_BLIP goto_blip_M1
	REMOVE_BLIP big_poppa_blip_M1 
	
	DISABLE_ALL_ENTRY_EXITS FALSE

	SWITCH_AMBIENT_PLANES TRUE

	SET_MAX_WANTED_LEVEL 6

	SET_CAR_DENSITY_MULTIPLIER 1.0

	para_force_chute_open = 0
	
	KILL_FX_SYSTEM signal_flare_M1[0]
	KILL_FX_SYSTEM signal_flare_M1[1]
	KILL_FX_SYSTEM signal_flare_M1[2]
																 
	//REMOVE_PICKUP armour_pickup_M1
	//REMOVE_PICKUP health_pickup_M1
		
	REMOVE_BLIP goto_blip_M1
	
	pointer_M1 = 0
	WHILE pointer_M1 < 4
		MARK_CHAR_AS_NO_LONGER_NEEDED triad_w1_M1[pointer_M1]
		MARK_CHAR_AS_NO_LONGER_NEEDED triad_w2_M1[pointer_M1]
		MARK_CHAR_AS_NO_LONGER_NEEDED lsv_outside_M1[pointer_M1]
		pointer_M1 ++
	ENDWHILE

	pointer_M1 = 0
	WHILE pointer_M1 < 3
		MARK_CHAR_AS_NO_LONGER_NEEDED triad_w3_M1[pointer_M1]
		REMOVE_BLIP triad_w3_blip_M1[pointer_M1] 
		pointer_M1 ++
	ENDWHILE

	MARK_CHAR_AS_NO_LONGER_NEEDED lsv_guard_M1
	
	MARK_CHAR_AS_NO_LONGER_NEEDED big_poppa_M1

	MARK_CAR_AS_NO_LONGER_NEEDED big_poppa_car_M1 
	MARK_CAR_AS_NO_LONGER_NEEDED player_car_M1 

	MARK_MODEL_AS_NO_LONGER_NEEDED TRIADA
	MARK_MODEL_AS_NO_LONGER_NEEDED TRIADB
	MARK_MODEL_AS_NO_LONGER_NEEDED LSV1
	MARK_MODEL_AS_NO_LONGER_NEEDED LSV2
	MARK_MODEL_AS_NO_LONGER_NEEDED LSV3
	MARK_MODEL_AS_NO_LONGER_NEEDED BFYST
	MARK_MODEL_AS_NO_LONGER_NEEDED GANGRL3
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYBE
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYDJ
	MARK_MODEL_AS_NO_LONGER_NEEDED BFYPRO
	MARK_MODEL_AS_NO_LONGER_NEEDED BFYBE

	MARK_MODEL_AS_NO_LONGER_NEEDED WINDSOR
	MARK_MODEL_AS_NO_LONGER_NEEDED PHOENIX

	MARK_MODEL_AS_NO_LONGER_NEEDED M4
	MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
	
	MARK_MODEL_AS_NO_LONGER_NEEDED PARACHUTE
	MARK_MODEL_AS_NO_LONGER_NEEDED PARA_PACK
   	MARK_MODEL_AS_NO_LONGER_NEEDED GUN_PARA

	MARK_MODEL_AS_NO_LONGER_NEEDED CARGO_REAR
	MARK_MODEL_AS_NO_LONGER_NEEDED D9_RAMP
	MARK_MODEL_AS_NO_LONGER_NEEDED CARDBOARDBOX2

	MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE

	MARK_MODEL_AS_NO_LONGER_NEEDED drg_nu_ext

	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED parachute.sc

	REMOVE_ANIMATION PARACHUTE

	GET_GAME_TIMER timer_mobile_start
	
	flag_player_on_mission = 0
	MISSION_HAS_FINISHED
RETURN

}


/*

[MAN1_20:MAN_1]
~r~Big Poppa~s~ is downstairs, take him out..

[MAN1_21:MAN_1]
~r~Big Poppa~s~ is making a run for it, chase after him..

[MAN1_22:MAN_1]
~r~Big Poppa~s~ has left the ~y~mansion~s~, follow him..

[MAN1_23:MAN_1]
~s~Chase down ~r~Big Poppa~s~ and run him off the road.

[MAN1_24:MAN_1]
~r~You lost Big Poppa.
*/

