MISSION_START

// *****************************************************************************************
// *************************************       SYNDICATE 7	   *****************************
// *************************************  Bomb the crack lab   *****************************												  
// *****************************************************************************************
// *****************************************************************************************

// Mission start stuff
SCRIPT_NAME syn7	

GOSUB mission_start_syn7

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_syn7_failed
ENDIF

GOSUB mission_cleanup_syn7

MISSION_END												  

{

// ****************************************Mission Start************************************

mission_start_syn7:

LVAR_INT s7_dm_weak
LVAR_INT s7_dm_empty
LVAR_INT s7_dm_tough
LVAR_INT s7_buddy
LVAR_INT s7_bomb_car
LVAR_INT s7_bomb_car_blip
LVAR_INT s7_bombshop_blip
LVAR_INT s7_gates
LVAR_INT s7_gate_guard[3]
LVAR_INT s7_front_gate
LVAR_INT s7_ramp
LVAR_INT v
LVAR_INT s7_mission
LVAR_INT s7_get_back
LVAR_INT s7_gate_inside[10]
LVAR_INT s7_cavalry[10]
LVAR_INT s7_ramp_blip
LVAR_INT s7_plant_car_blip
LVAR_INT s7_msg_displayed
LVAR_INT s7_red
LVAR_INT s7_seq_leave
LVAR_INT s7_exit_door_blip
LVAR_INT s7_died_in_explosion
LVAR_INT s7_door_not_ramp
LVAR_INT s7_escape_txt
LVAR_INT s7_gate_closing
LVAR_INT s7_courtyard_bike
LVAR_INT s7_escape_ramp
LVAR_INT s7_home_blip
LVAR_INT s7_at_gate
LVAR_INT s7_gate_guard_blip[2]
LVAR_INT s7_workers[8]
LVAR_INT s7_desk_guy_txt
LVAR_INT s7_exit_guys[10]
LVAR_INT s7_bike_1
LVAR_INT s7_bike_2
LVAR_INT s7_truck_blip
LVAR_INT s7_backtrack[5]
LVAR_INT s7_start_call
LVAR_INT s4_guards_are_shooting
LVAR_INT s4_guards_are_shooting_2
LVAR_INT s7_sequence

VAR_FLOAT s7_rnd
VAR_FLOAT s7_velocity

VAR_INT s7_timer
VAR_INT s7_in_compound
VAR_INT	s7_rnd_1

LVAR_INT s7_playing

LVAR_INT s7_audio

LVAR_INT s7_dont_print_again

LVAR_INT s7_area

LVAR_INT s7_health

LVAR_INT s7_gate_stop

LVAR_INT s7_drive_in

LVAR_INT s7_roll_out

LVAR_INT s7_respawned

LVAR_INT s7_sequence_task

LVAR_TEXT_LABEL s7_print

LOAD_MISSION_TEXT SYN7

// Cutscene

SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE
 
REQUEST_IPL CRACK

SET_VISIBILITY_OF_CLOSEST_OBJECT_OF_TYPE -2166.86 -236.50 40.86 40.0 crackfact_SFS TRUE
SET_VISIBILITY_OF_CLOSEST_OBJECT_OF_TYPE -2185.49 -215.55 34.31 40.0 CF_ext_dem_SFS FALSE
SET_VISIBILITY_OF_CLOSEST_OBJECT_OF_TYPE -2166.86 -236.50 40.86 40.0 LODcrackfact_SFS TRUE
SET_VISIBILITY_OF_CLOSEST_OBJECT_OF_TYPE -2185.49 -215.55 34.31 40.0 LODext_dem_SFS FALSE

SET_AREA_VISIBLE 1

SET_CHAR_AREA_VISIBLE scplayer 1

LOAD_SCENE -2031.0 149.0 29.0

LOAD_CUTSCENE SYND_7
 
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
 
 	SET_CHAR_COORDINATES scplayer -2031.1965 161.1839 27.8516 
	
 	SET_CHAR_HEADING scplayer 268.0468 
 
  	SET_CAMERA_BEHIND_PLAYER

	SET_PLAYER_CONTROL player1 OFF

ENDIF

// *****************************************************************************************

SET_FADING_COLOUR 0 0 0

flag_player_on_mission = 1

s7_mission = 0

s7_timer = 40000

s7_playing = 2

REGISTER_MISSION_GIVEN

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY s7_dm_empty
  	
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_WEAK s7_dm_weak

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH s7_dm_tough

WAIT 0

GOTO compiler_skip
  
s7_spawn_workers:

	LVAR_INT s7_seq_anim[8]

	// ----------------------------------------------------------------------------------------------

	// Workers Sequences
	OPEN_SEQUENCE_TASK s7_seq_anim[1]
								  
		TASK_GO_TO_COORD_ANY_MEANS -1 -2160.5334 -247.0836 35.5234 PEDMOVE_WALK -1

	 	TASK_PLAY_ANIM -1 wash_up INT_HOUSE 1.0 TRUE FALSE FALSE FALSE -1

		SET_SEQUENCE_TO_REPEAT s7_seq_anim[1] 1

	CLOSE_SEQUENCE_TASK s7_seq_anim[1]

	// ----------------------------------------------------------------------------------------------

	OPEN_SEQUENCE_TASK s7_seq_anim[2]
								  
		TASK_GO_TO_COORD_ANY_MEANS -1 -2163.7666 -249.1202 35.5234 PEDMOVE_WALK -1

		TASK_PAUSE -1 1000

	 	TASK_PLAY_ANIM -1 wash_up INT_HOUSE 1.0 TRUE FALSE FALSE FALSE -1

		SET_SEQUENCE_TO_REPEAT s7_seq_anim[2] 1

	CLOSE_SEQUENCE_TASK s7_seq_anim[2]

	// ----------------------------------------------------------------------------------------------

	OPEN_SEQUENCE_TASK s7_seq_anim[3]
								  
		TASK_GO_TO_COORD_ANY_MEANS -1 -2167.6311 -249.1143 35.5234 PEDMOVE_WALK -1

		TASK_PAUSE -1 500

	 	TASK_PLAY_ANIM -1 wash_up INT_HOUSE 1.0 TRUE FALSE FALSE FALSE -1

		SET_SEQUENCE_TO_REPEAT s7_seq_anim[3] 1

	CLOSE_SEQUENCE_TASK s7_seq_anim[3]

	// ---------------------------------------------------------------------------------------------- 
	
	OPEN_SEQUENCE_TASK s7_seq_anim[4]
							  
		TASK_GO_TO_COORD_ANY_MEANS -1 -2157.0000 -248.9899 35.5234 PEDMOVE_WALK -1

		TASK_PAUSE -1 250

	 	TASK_PLAY_ANIM -1 wash_up INT_HOUSE 1.0 TRUE FALSE FALSE FALSE -1

		SET_SEQUENCE_TO_REPEAT s7_seq_anim[4] 1

	CLOSE_SEQUENCE_TASK s7_seq_anim[4]

	// ----------------------------------------------------------------------------------------------

   	// Workers inside the Crack Lab
	CREATE_CHAR PEDTYPE_MISSION1 BMYCG -2146.0449 -242.5144 35.5234 s7_workers[0]

	SET_CHAR_HEADING s7_workers[0] 90.4330

	SET_CHAR_WEAPON_SKILL s7_workers[0] WEAPONSKILL_PRO

	GIVE_WEAPON_TO_CHAR s7_workers[0] WEAPONTYPE_PISTOL 9999

	SET_CHAR_ACCURACY s7_workers[0] 30

	SET_CHAR_SHOOT_RATE s7_workers[0] 30

	SET_CHAR_DECISION_MAKER s7_workers[0] s7_dm_tough

	// ----------------------------------------------------------------------------------------------

	CREATE_CHAR PEDTYPE_CIVFEMALE WFYCRK -2160.5334 -247.0836 35.5234 s7_workers[1]

	SET_CHAR_HEADING s7_workers[1] 176.3351

	PERFORM_SEQUENCE_TASK s7_workers[1] s7_seq_anim[1]

	// ----------------------------------------------------------------------------------------------

	CREATE_CHAR PEDTYPE_CIVFEMALE WFYCRK -2163.7666 -249.1202 35.5234 s7_workers[2]

	SET_CHAR_HEADING s7_workers[2] 11.5708

	PERFORM_SEQUENCE_TASK s7_workers[2] s7_seq_anim[2]

	// ----------------------------------------------------------------------------------------------   
	   
	CREATE_CHAR PEDTYPE_CIVMALE HMYCM -2167.6311 -249.1143 35.5234 s7_workers[3]

	SET_CHAR_HEADING s7_workers[3] 1.3110

	PERFORM_SEQUENCE_TASK s7_workers[3] s7_seq_anim[3]

	// ----------------------------------------------------------------------------------------------

	CREATE_CHAR PEDTYPE_CIVMALE HMYCM -2157.0000 -248.9899 35.5234 s7_workers[4]

	SET_CHAR_HEADING s7_workers[4] 0.8891 

	PERFORM_SEQUENCE_TASK s7_workers[4] s7_seq_anim[4]

	// ----------------------------------------------------------------------------------------------

	REPEAT 5 v

		IF NOT v = 0

			IF NOT IS_CHAR_DEAD s7_workers[v]

				SET_CHAR_IS_TARGET_PRIORITY s7_workers[v] FALSE

		   		SET_CHAR_DECISION_MAKER s7_workers[v] s7_dm_weak

			ENDIF
				
		ENDIF
	
	ENDREPEAT	  

RETURN

compiler_skip:

SET_GROUP_SEPARATION_RANGE Players_Group 100.0

SWITCH_PED_ROADS_OFF -2175.8174 -55.8818 27.0000 -2059.2053 -99.4399 37.0000 

SWITCH_ROADS_OFF -2175.8174 -55.8818 27.0000 -2059.2053 -99.4399 37.0000 

SWITCH_PED_ROADS_OFF -2107.9805 -71.8985 30.0000 -2160.2563 -89.7108 37.0000 

TIMERA = 0

SET_CAMERA_BEHIND_PLAYER

IF NOT IS_CHAR_DEAD scplayer

	SET_CHAR_HEADING scplayer 280.0

ENDIF

ADD_BLIP_FOR_COORD -1688.9010 1035.95 43.7187 s7_bombshop_blip

REQUEST_MODEL tampa
REQUEST_MODEL WMYMECH
REQUEST_ANIMATION CAR 
REQUEST_MODEL cellphone
REQUEST_MODEL health
REQUEST_MODEL COLT45

WHILE NOT HAS_MODEL_LOADED tampa
OR NOT HAS_MODEL_LOADED WMYMECH
OR NOT HAS_ANIMATION_LOADED CAR
OR NOT HAS_MODEL_LOADED cellphone
OR NOT HAS_MODEL_LOADED health
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED COLT45
	WAIT 0
ENDWHILE

// ----------------------------------------------------------------------------------------------

CREATE_PICKUP health PICKUP_ONCE -2182.6523 -247.3813 36.4000 s7_health

DO_FADE 1000 FADE_IN

SET_PLAYER_CONTROL player1 ON

// ----------------------------------------------------------------------------------------------

CREATE_OBJECT wongs_gate -2127.0000 -80.8000 34.3281 s7_front_gate

SET_OBJECT_HEADING s7_front_gate 180.0

// ----------------------------------------------------------------------------------------------

CREATE_OBJECT DYN_RAMP -2175.2000 -209.7745 34.3281 s7_ramp

SET_OBJECT_HEADING s7_ramp 85.6854  

// ----------------------------------------------------------------------------------------------

CUSTOM_PLATE_FOR_NEXT_CAR tampa &TIMEBOMB

CREATE_CAR tampa -1688.9010 1035.95 44.6000 s7_bomb_car

SET_VEHICLE_DIRT_LEVEL s7_bomb_car 12.0000

FREEZE_CAR_POSITION s7_bomb_car TRUE

SET_CAN_BURST_CAR_TYRES s7_bomb_car FALSE

SET_CAR_HEADING s7_bomb_car 90.0

SET_CAR_HEALTH s7_bomb_car 1000

// ----------------------------------------------------------------------------------------------

CREATE_CHAR PEDTYPE_CIVMALE WMYMECH -1689.12 1038.2 44.2000 s7_buddy

SET_CHAR_HEALTH s7_buddy 800

SET_CHAR_MAX_HEALTH s7_buddy 800

SET_CHAR_NEVER_TARGETTED s7_buddy TRUE

SET_CHAR_COLLISION s7_buddy FALSE

SET_CHAR_HEADING s7_buddy 0.0

TASK_PLAY_ANIM s7_buddy Fixn_Car_Loop CAR 4.0 TRUE FALSE FALSE TRUE 0

SET_CHAR_ACCURACY s7_buddy 100

SET_CHAR_DECISION_MAKER s7_buddy s7_dm_empty 

// ----------------------------------------------------------------------------------------------

// Density Multipliers

SET_PED_DENSITY_MULTIPLIER 0.5

SET_CAR_DENSITY_MULTIPLIER 1.0

// Relationshipe

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1 PEDTYPE_MISSION1

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1 PEDTYPE_MISSION1

TIMERB = 0

LOAD_MISSION_AUDIO 3 SOUND_MOBRING // Ok, Carl, can you hear me ok?

WHILE NOT HAS_MISSION_AUDIO_LOADED 3	 
	WAIT 0
ENDWHILE

PLAY_MISSION_AUDIO 3

GOSUB s7_set_camera
		    
SET_FIXED_CAMERA_POSITION -2029.1335 164.0932 29.0052 0.0 0.0 0.0
POINT_CAMERA_AT_POINT -2029.7780 163.3323 28.9291 JUMP_CUT

IF NOT IS_CHAR_DEAD scplayer

	TASK_USE_MOBILE_PHONE scplayer TRUE

ENDIF

WAIT 2000

PRINT_NOW ( MTG01A ) 4000 1 // Ok, Carl, can you hear me ok?

CLEAR_MISSION_AUDIO 1

LOAD_MISSION_AUDIO 1 SOUND_MTG01A // Ok, Carl, can you hear me ok?

WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
	WAIT 0
ENDWHILE

PLAY_MISSION_AUDIO 1

TIMERB = 0
WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
    WAIT 0
ENDWHILE

WAIT 500

GOSUB s7_restore_camera

// ******************************************** START MAIN LOOP **********************************

WHILE NOT IS_CHAR_DEAD scplayer

	WAIT 0
		
	GOSUB s7_keys

	GOSUB s7_play_sample

	IF IS_BUTTON_PRESSED PAD1 TRIANGLE
	AND NOT s7_start_call = 8
	AND NOT s7_start_call = 0

		s7_start_call = 7	

	ENDIF
	IF s7_playing = 2
	AND s7_start_call = 0

		$s7_print = &MTG01B	// CJ?
		s7_audio = SOUND_MTG01B
		GOSUB s7_load_sample
 
		s7_start_call = 1

	ENDIF
	IF s7_playing = 2
	AND s7_start_call = 1
				
		IF NOT IS_CHAR_DEAD scplayer

			START_CHAR_FACIAL_TALK scplayer 5000

		ENDIF

		$s7_print = &MTG01C	// Yeah, who's this?
		s7_audio = SOUND_MTG01C
		GOSUB s7_load_sample

		s7_start_call = 2

	ENDIF
	IF s7_playing = 2
	AND s7_start_call = 2
								
		IF NOT IS_CHAR_DEAD scplayer

			STOP_CHAR_FACIAL_TALK scplayer

		ENDIF

		$s7_print = &MTG01D	// I work for Woozie he told me to call you.
		s7_audio = SOUND_MTG01D
		GOSUB s7_load_sample
 
		s7_start_call = 3

	ENDIF
	IF s7_playing = 2
	AND s7_start_call = 3
						
		IF NOT IS_CHAR_DEAD scplayer

			START_CHAR_FACIAL_TALK scplayer 5000

		ENDIF

		$s7_print = &MTG01E	// Yeah? Whattup?
		s7_audio = SOUND_MTG01E
		GOSUB s7_load_sample

		s7_start_call = 4

	ENDIF
	IF s7_playing = 2
	AND s7_start_call = 4
								
		IF NOT IS_CHAR_DEAD scplayer

			STOP_CHAR_FACIAL_TALK scplayer

		ENDIF

		$s7_print = &MTG01F	// I'm rigging a car with explosives so you can take out the crack factory!
		s7_audio = SOUND_MTG01F
		GOSUB s7_load_sample
 
		s7_start_call = 5

	ENDIF
	IF s7_playing = 2
	AND s7_start_call = 5

		$s7_print = &MTG01G	// Drop 'round the garage Downtown.
		s7_audio = SOUND_MTG01G
		GOSUB s7_load_sample
 
		s7_start_call = 6

	ENDIF
	IF s7_playing = 2
	AND s7_start_call = 6
						
		IF NOT IS_CHAR_DEAD scplayer

			START_CHAR_FACIAL_TALK scplayer 5000

		ENDIF

		$s7_print = &MTG01H	// Ok. Be with you any second.
		s7_audio = SOUND_MTG01H
		GOSUB s7_load_sample

		s7_start_call = 7

	ENDIF
	IF s7_playing = 2
	AND s7_start_call = 7
						
		IF NOT IS_CHAR_DEAD scplayer

			STOP_CHAR_FACIAL_TALK scplayer

		ENDIF

		IF NOT IS_CHAR_DEAD scplayer

			TASK_USE_MOBILE_PHONE scplayer FALSE

		ENDIF

		MARK_MODEL_AS_NO_LONGER_NEEDED cellphone

		PRINT_NOW ( SYN7_01 ) 9000 1 // ~s~Go and pick up the wired car from the ~y~Bomb Shop~s~.

		s7_start_call = 8

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S

		GOTO mission_syn7_passed

	ENDIF

	GET_CHAR_SPEED scplayer s7_velocity

	// **********************************************************************************************
	// *																							*
	// *								  Cutscene in Bomb-Shops									*	
	// *																							*
	// **********************************************************************************************

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2129.3079 -129.0417 34.3203 50.0 50.0 500.0 FALSE
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer -2149.6243 -244.8758 35.5234 98.0 98.0 500.0 FALSE

		s7_in_compound = 1

		SET_WANTED_MULTIPLIER 0.0

	ELSE

		s7_in_compound = 0

		SET_WANTED_MULTIPLIER 1.0

	ENDIF

	IF s7_mission = 0
	AND NOT IS_CHAR_DEAD scplayer
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1699.4839 1030.6508 44.2187 4.0 4.0 4.0 TRUE

			IF NOT IS_CHAR_DEAD scplayer

				SET_PLAYER_CONTROL player1 OFF

			ENDIF

			IF IS_CHAR_IN_ANY_CAR scplayer 

				STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car

				TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000

			ENDIF
				
			s7_start_call = 8

			GOSUB s7_set_camera

			// Outside camera of Bomb Shop
		    
			SET_FIXED_CAMERA_POSITION -1721.0710 1024.8037 49.7941 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1720.1615 1025.1863 49.6318 JUMP_CUT

			IF IS_CHAR_IN_ANY_CAR scplayer

				TASK_LEAVE_ANY_CAR scplayer

			 	WAIT 2000

			ELSE

				TASK_GO_STRAIGHT_TO_COORD scplayer -1696.49 1034.98 45.11 PEDMOVE_WALK 2000

			ENDIF

			GOSUB s7_fade_out
						
		 	LOAD_MISSION_AUDIO 3 SOUND_BANK_MECHANIC

			WHILE NOT HAS_MISSION_AUDIO_LOADED 3	 
				WAIT 0
			ENDWHILE

			REQUEST_MODEL WFYCRK   
			REQUEST_MODEL HMYCM		
			REQUEST_MODEL BMYCG 
			REQUEST_MODEL hmyst	
			REQUEST_MODEL hmyri
			REQUEST_MODEL micro_uzi
			REQUEST_MODEL VOODOO
			REQUEST_ANIMATION INT_HOUSE

			WHILE NOT HAS_MODEL_LOADED WFYCRK
			OR NOT HAS_MODEL_LOADED HMYCM
			OR NOT HAS_MODEL_LOADED BMYCG
			OR NOT HAS_MODEL_LOADED hmyst
			OR NOT HAS_MODEL_LOADED hmyri
				WAIT 0
			ENDWHILE 

			WHILE NOT HAS_MODEL_LOADED micro_uzi
			OR NOT HAS_MODEL_LOADED VOODOO
			OR NOT HAS_ANIMATION_LOADED INT_HOUSE
				WAIT 0
			ENDWHILE 

			REQUEST_CAR_RECORDING 483

			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 483
				WAIT 0
			ENDWHILE

			LVAR_INT s7_carl_walk

			IF NOT IS_CHAR_DEAD s7_buddy

				OPEN_SEQUENCE_TASK s7_carl_walk

					TASK_GO_STRAIGHT_TO_COORD -1 -1692.0675 1036.1962 44.2109 PEDMOVE_WALK -2
			
					TASK_TURN_CHAR_TO_FACE_CHAR -1 s7_buddy

					TASK_TOGGLE_DUCK -1 TRUE

				CLOSE_SEQUENCE_TASK s7_carl_walk

			ENDIF

			// CREATE CAR 
			IF NOT IS_CAR_DEAD s7_bomb_car
			  
				IF NOT IS_CHAR_IN_CAR scplayer s7_bomb_car 
				 	IF IS_CHAR_IN_ANY_CAR scplayer

						WARP_CHAR_FROM_CAR_TO_COORD scplayer -1693.3206 1034.3199 44.2109

					ENDIF 
				ENDIF	

				SET_CHAR_COORDINATES scplayer -1693.3206 1034.3199 44.2109
				SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

				SET_CHAR_HEADING scplayer 329.4165 

				PERFORM_SEQUENCE_TASK scplayer s7_carl_walk

			ENDIF
			
			WAIT 1500

			CLEAR_PRINTS
  
			// Carl walks up and watches guy under car
			SET_FIXED_CAMERA_POSITION -1694.2156 1036.7203 45.3716 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1693.2362 1036.8014 45.1866 JUMP_CUT

			DO_FADE 1000 FADE_IN

		 	TIMERA = 0

			IF NOT IS_CAR_DEAD s7_bomb_car

				ADD_BLIP_FOR_CAR s7_bomb_car s7_bomb_car_blip
															   
				CHANGE_BLIP_DISPLAY s7_bomb_car_blip NEITHER

				SET_BLIP_AS_FRIENDLY s7_bomb_car_blip TRUE

			ENDIF

			// *************** Camera showing guy Under car ***********************************************
										     
			//	SET_FIXED_CAMERA_POSITION -1693.4517 1037.2605 44.9357 0.0 0.0 0.0
			//	POINT_CAMERA_AT_POINT -1692.4723 1037.1040 44.8083 JUMP_CUT

			IF NOT IS_CHAR_DEAD s7_buddy

				REPORT_MISSION_AUDIO_EVENT_AT_CHAR s7_buddy SOUND_MECHANIC_ATTACH_CAR_BOMB

			ENDIF

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_SYN7_AA

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			PRINT_NOW ( SYN7_AA ) 3000 1 // Hey, dude, is it ready?

			TIMERA = 0
			WHILE TIMERA < 2000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
					GOTO s7_skip_the_cutscene
				ENDIF
			ENDWHILE

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_SYN7_AB

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			PRINT_NOW ( SYN7_AB ) 2000 1 // She's all set. Get in.
			
			TIMERA = 0
			WHILE TIMERA < 1000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
					GOTO s7_skip_the_cutscene
				ENDIF
			ENDWHILE

			LVAR_INT s7_buddy_seq

			IF NOT IS_CHAR_DEAD scplayer

				OPEN_SEQUENCE_TASK s7_buddy_seq
							 
					TASK_PLAY_ANIM -1 Fixn_Car_Out CAR 4.0 FALSE FALSE FALSE TRUE 0

				CLOSE_SEQUENCE_TASK s7_buddy_seq

			ENDIF

			IF NOT IS_CHAR_DEAD scplayer
			AND NOT IS_CAR_DEAD s7_bomb_car
			
			 	TASK_TOGGLE_DUCK scplayer FALSE

				TASK_GO_STRAIGHT_TO_COORD scplayer -1690.4226 1034.0026 44.2109 PEDMOVE_WALK 6000
			
				TASK_ENTER_CAR_AS_DRIVER scplayer s7_bomb_car -1

			ENDIF

			TIMERA = 0
			WHILE TIMERA < 500
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
					GOTO s7_skip_the_cutscene
				ENDIF
			ENDWHILE
			
			IF NOT IS_CHAR_DEAD s7_buddy

				PERFORM_SEQUENCE_TASK s7_buddy s7_buddy_seq

			ENDIF 
									
			IF NOT IS_CHAR_DEAD s7_buddy

				REPORT_MISSION_AUDIO_EVENT_AT_CHAR s7_buddy SOUND_MECHANIC_SLIDE_OUT

			ENDIF

			TIMERA = 0
			WHILE TIMERA < 1500
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
					GOTO s7_skip_the_cutscene
				ENDIF
			ENDWHILE

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_SYN7_AC

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			PRINT_NOW ( SYN7_AC ) 3000 1 // She's packing enough to take out that crack lab.
			
			IF NOT IS_CHAR_DEAD s7_buddy

				START_CHAR_FACIAL_TALK s7_buddy 5000

			ENDIF
							   
			// *************** Camera showing Guys in car ***********************************************
			SET_FIXED_CAMERA_POSITION -1690.1716 1033.2664 45.4626 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1689.6400 1034.1102 45.3904 JUMP_CUT 
				
			IF NOT IS_CHAR_DEAD s7_buddy

				SET_CHAR_COLLISION s7_buddy TRUE
			
				TASK_TURN_CHAR_TO_FACE_CHAR s7_buddy scplayer

			ENDIF 

			IF NOT IS_CAR_DEAD s7_bomb_car

			  	SET_CAR_COORDINATES s7_bomb_car -1688.9010 1034.95 43.7187 
				
			 	IF NOT IS_CHAR_DEAD s7_buddy 

				 	TASK_LOOK_AT_CHAR scplayer s7_buddy 5000

					TASK_PLAY_ANIM s7_buddy IDLE_chat PED 1.0 FALSE FALSE FALSE FALSE -1

					SET_CHAR_COORDINATES s7_buddy -1689.12 1036.9 44.3187

					SET_CHAR_HEADING s7_buddy 180.0

				ENDIF  

				SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

			ENDIF

			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
					GOTO s7_skip_the_cutscene
				ENDIF
			ENDWHILE 
					
			IF NOT IS_CHAR_DEAD s7_buddy

				STOP_CHAR_FACIAL_TALK s7_buddy

			ENDIF
						 
			TIMERA = 0
			WHILE TIMERA < 2500
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
					GOTO s7_skip_the_cutscene
				ENDIF
			ENDWHILE 

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_SYN7_AD

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			PRINT_NOW ( SYN7_AD ) 4000 1 // I've wired in a delay timer, to give you time to get out.
						
			IF NOT IS_CHAR_DEAD s7_buddy

				START_CHAR_FACIAL_TALK s7_buddy 5000

			ENDIF

			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
					GOTO s7_skip_the_cutscene
				ENDIF
			ENDWHILE 
								
			IF NOT IS_CHAR_DEAD s7_buddy

				STOP_CHAR_FACIAL_TALK s7_buddy

			ENDIF

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_SYN7_AE

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			IF NOT IS_CHAR_DEAD scplayer

				START_CHAR_FACIAL_TALK scplayer 5000

			ENDIF

			PLAY_MISSION_AUDIO 1

			PRINT_NOW ( SYN7_AE ) 4000 1 // Ok, thanks, dude.
			 
			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
					GOTO s7_skip_the_cutscene
				ENDIF
			ENDWHILE

			IF NOT IS_CHAR_DEAD scplayer

				STOP_CHAR_FACIAL_TALK scplayer

			ENDIF

			IF NOT IS_CAR_DEAD s7_bomb_car
				
			 	IF NOT IS_CHAR_DEAD s7_buddy 

					CLEAR_CHAR_TASKS s7_buddy 

				 	TASK_GO_TO_COORD_ANY_MEANS s7_buddy -1683.1704 1037.3420 45.2173 PEDMOVE_WALK -1

				ENDIF  

			ENDIF

			TIMERA = 0
			WHILE TIMERA < 1000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
					GOTO s7_skip_the_cutscene
				ENDIF
			ENDWHILE 

			GOSUB s7_fade_out

			CLEAR_PRINTS
   
			CLEAR_AREA -1704.6931 1027.4761 44.2110 30.0 TRUE

			LOAD_SCENE_IN_DIRECTION -1696.5847 1038.4976 44.2173 150.0018 

			IF NOT IS_CAR_DEAD s7_bomb_car

				FREEZE_CAR_POSITION s7_bomb_car FALSE

				START_PLAYBACK_RECORDED_CAR s7_bomb_car 483

			ENDIF

			// The car when it's outside the bomb shop
			SET_FIXED_CAMERA_POSITION -1696.99 1045.54 48.8 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1701.21 1036.55 45.70 JUMP_CUT

			GOSUB s7_fade_in

			TIMERA = 0
			WHILE TIMERA < 2000

				WAIT 0

				IF IS_BUTTON_PRESSED PAD1 CROSS

					GOTO s7_skip_the_cutscene
					
				ENDIF

			ENDWHILE 

			s7_skip_the_cutscene:

			IF NOT IS_CHAR_DEAD scplayer

				STOP_CHAR_FACIAL_TALK scplayer

			ENDIF

			DELETE_CHAR s7_buddy

			REMOVE_ANIMATION CAR

			IF NOT IS_CAR_DEAD s7_bomb_car

				FREEZE_CAR_POSITION s7_bomb_car FALSE

				IF NOT IS_CHAR_IN_CAR scplayer s7_bomb_car 

				  	WARP_CHAR_INTO_CAR scplayer s7_bomb_car

				ENDIF	

			ENDIF 

			IF NOT IS_CAR_DEAD s7_bomb_car

				IF IS_PLAYBACK_GOING_ON_FOR_CAR s7_bomb_car

					SKIP_TO_END_AND_STOP_PLAYBACK_RECORDED_CAR s7_bomb_car

				ELSE

					CLEAR_AREA -1704.6150 1026.2019 44.1953 20.0 TRUE

					SET_CAR_COORDINATES s7_bomb_car -1704.6150 1026.2019 44.1953    

					SET_CAR_HEADING s7_bomb_car 168.8963 

				ENDIF

			ENDIF

			TIMERA = 0

			REMOVE_BLIP s7_bombshop_blip

			CHANGE_BLIP_DISPLAY s7_bomb_car_blip NEITHER

			ADD_BLIP_FOR_COORD -2128.1201 -83.3848 34.3140 s7_gates

			GOSUB s7_restore_camera

			PRINT_NOW ( SYN7_03 ) 7000 1 // ~s~ Park the car inside the ~y~Crack lab

			LVAR_INT s4_guard_gate_a s4_guard_gate_b

			// ----------------------------------------------------------------------------------------------

			OPEN_SEQUENCE_TASK s4_guard_gate_a
										  
				TASK_GO_TO_COORD_ANY_MEANS -1 -2128.8479 -79.3918 34.2565 PEDMOVE_WALK -1
				TASK_ACHIEVE_HEADING -1 0.0000
				SET_SEQUENCE_TO_REPEAT s4_guard_gate_a 1

			CLOSE_SEQUENCE_TASK s4_guard_gate_a

			// ----------------------------------------------------------------------------------------------

			OPEN_SEQUENCE_TASK s4_guard_gate_b
										  
				TASK_GO_TO_COORD_ANY_MEANS -1 -2128.8479 -79.3918 34.2565 PEDMOVE_WALK -1
				TASK_ACHIEVE_HEADING -1 0.0000
				SET_SEQUENCE_TO_REPEAT s4_guard_gate_b 1

			CLOSE_SEQUENCE_TASK s4_guard_gate_b

			// ----------------------------------------------------------------------------------------------

			CREATE_CHAR PEDTYPE_MISSION1 BMYCG -2128.8479 -79.3918 34.2565 s7_gate_guard[0]

			GIVE_WEAPON_TO_CHAR s7_gate_guard[0] WEAPONTYPE_MICRO_UZI 1000

			SET_CHAR_HEADING s7_gate_guard[0] 0.0000

			// ----------------------------------------------------------------------------------------------

			CREATE_CHAR PEDTYPE_MISSION1 hmyst -2128.8479 -79.3918 34.2565 s7_gate_guard[1]

			GIVE_WEAPON_TO_CHAR s7_gate_guard[1] WEAPONTYPE_MICRO_UZI 1000

			SET_CHAR_HEADING s7_gate_guard[1] 0.0000
			
			PERFORM_SEQUENCE_TASK s7_gate_guard[0] s4_guard_gate_a

			PERFORM_SEQUENCE_TASK s7_gate_guard[1] s4_guard_gate_b

			// ----------------------------------------------------------------------------------------------

			REPEAT 2 v

				SET_CHAR_ACCURACY s7_gate_guard[v] 40

				SET_CHAR_DECISION_MAKER s7_gate_guard[v] s7_dm_tough

				SET_CHAR_RELATIONSHIP s7_gate_guard[v] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1	 

				SET_CHAR_RELATIONSHIP s7_gate_guard[v] ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1	

				SET_SENSE_RANGE s7_gate_guard[v] 150.0

				SET_CHAR_IS_TARGET_PRIORITY s7_gate_guard[v] TRUE

			ENDREPEAT
			
			s7_mission = 1 	

		ENDIF
	ENDIF

	// **********************************************************************************************
	// *																							*
	// *								   If all the guys are dead									*	
	// *																							*
	// **********************************************************************************************

	IF s7_mission = 1

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2124.5007 -77.7719 34.1719 50.0 50.0 50.0 FALSE
		AND NOT IS_CHAR_DEAD s7_gate_guard[0]
		AND NOT IS_CHAR_DEAD s7_gate_guard[1]

			IF s7_at_gate = 0

				GOSUB s7_spawn_workers

				PRINT_NOW ( SYN7_52 ) 4000 1 // Kill the ~r~Guards, that will get their attention

				s7_at_gate = 1

				IF DOES_BLIP_EXIST s7_gates

					CHANGE_BLIP_DISPLAY s7_gates NEITHER

				ENDIF

				IF NOT IS_CHAR_DEAD s7_gate_guard[0]

					ADD_BLIP_FOR_CHAR s7_gate_guard[0] s7_gate_guard_blip[0]

				ENDIF

				IF NOT IS_CHAR_DEAD s7_gate_guard[1]

					ADD_BLIP_FOR_CHAR s7_gate_guard[1] s7_gate_guard_blip[1]

				ENDIF

			ENDIF

		ENDIF


	 	IF NOT IS_CHAR_DEAD s7_gate_guard[1]

			IF LOCATE_CHAR_ANY_MEANS_3D s7_gate_guard[1] -2126.9109 -79.3953 34.2567 10.0 10.0 10.0 FALSE

				s4_guards_are_shooting_2 = 1

			ELSE

				SWITCH_PED_ROADS_BACK_TO_ORIGINAL -2175.8174 -55.8818 27.0000 -2059.2053 -99.4399 37.0000 

				SWITCH_PED_ROADS_BACK_TO_ORIGINAL -2107.9805 -71.8985 30.0000 -2160.2563 -89.7108 37.0000 
					
				s4_guards_are_shooting_2 = 0

			ENDIF

		ENDIF 

	 	IF NOT IS_CHAR_DEAD s7_gate_guard[0]

			IF LOCATE_CHAR_ANY_MEANS_3D s7_gate_guard[0] -2126.9109 -79.3953 34.2567 10.0 10.0 10.0 FALSE

				s4_guards_are_shooting = 1

			ELSE

				SWITCH_PED_ROADS_BACK_TO_ORIGINAL -2175.8174 -55.8818 27.0000 -2059.2053 -99.4399 37.0000 

				SWITCH_PED_ROADS_BACK_TO_ORIGINAL -2107.9805 -71.8985 30.0000 -2160.2563 -89.7108 37.0000
				 		
				s4_guards_are_shooting = 0

			ENDIF

		ENDIF  

		IF s7_at_gate = 1

			IF IS_CHAR_DEAD s7_gate_guard[0]

				IF DOES_BLIP_EXIST s7_gate_guard_blip[0]

					REMOVE_BLIP s7_gate_guard_blip[0]

				ENDIF

			ENDIF

			IF IS_CHAR_DEAD s7_gate_guard[1]

				IF DOES_BLIP_EXIST s7_gate_guard_blip[1]

					REMOVE_BLIP s7_gate_guard_blip[1]

				ENDIF

			ENDIF

		ENDIF

		GET_AREA_VISIBLE s7_area

		IF IS_CHAR_DEAD	s7_gate_guard[0]
		AND IS_CHAR_DEAD s7_gate_guard[1]
		AND s7_area = 0

			GOSUB s7_fade_out

			REQUEST_ANIMATION ON_LOOKERS

			WHILE NOT HAS_ANIMATION_LOADED ON_LOOKERS
				WAIT 0
			ENDWHILE

			GOSUB s7_set_camera

	  		CREATE_CHAR PEDTYPE_MISSION1 hmyri -2125.8706 -97.1422 34.3281 s7_gate_inside[2]

			SET_CHAR_WEAPON_SKILL s7_gate_inside[2] WEAPONSKILL_PRO

			GIVE_WEAPON_TO_CHAR s7_gate_inside[2] WEAPONTYPE_PISTOL 9999

			TASK_PLAY_ANIM s7_gate_inside[2] point_loop ON_LOOKERS 8.0 TRUE FALSE FALSE TRUE 3000

			IF s4_guards_are_shooting_2 = 1
			OR s4_guards_are_shooting = 1
						
				SET_FIXED_CAMERA_POSITION -2113.1504 -72.3184 40.9064 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2113.9177 -72.8274 40.5167 JUMP_CUT

			ELSE

				SET_FIXED_CAMERA_POSITION -2125.6421 -95.0874 35.6371 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2125.7625 -96.0758 35.7291 JUMP_CUT	

			ENDIF

			GOSUB s7_fade_in
			
			GENERATE_RANDOM_INT_IN_RANGE 0 3 s7_rnd_1

			//SKIP_CUTSCENE_START

			SWITCH s7_rnd_1

				CASE 0
					
					CLEAR_MISSION_AUDIO 1

				 	LOAD_MISSION_AUDIO 1 SOUND_SYN7_BA

					WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
						WAIT 0
					ENDWHILE

					PLAY_MISSION_AUDIO 1

					PRINT_NOW ( SYN7_BA ) 4000 1 // Hey, some loco basta blasting on our boys!
				
				BREAK
				
				CASE 1
					
					CLEAR_MISSION_AUDIO 1

				 	LOAD_MISSION_AUDIO 1 SOUND_SYN7_BB

					WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
						WAIT 0
					ENDWHILE

					PLAY_MISSION_AUDIO 1
			
					PRINT_NOW ( SYN7_BB ) 4000 1 // It's a hit!
				
				BREAK
				
				CASE 2
					
					CLEAR_MISSION_AUDIO 1

				 	LOAD_MISSION_AUDIO 1 SOUND_SYN7_BC

					WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
						WAIT 0
					ENDWHILE

					PLAY_MISSION_AUDIO 1
			
					PRINT_NOW ( SYN7_BC ) 4000 1 // We're being hit!
				
				BREAK				

			ENDSWITCH

			WAIT 4000

			IF NOT IS_CHAR_DEAD scplayer
			AND NOT IS_CAR_DEAD s7_bomb_car

				IF IS_CHAR_IN_CAR scplayer s7_bomb_car

					CLEAR_AREA -2127.7991 -68.8104 34.1719 10.0 TRUE

				  	SET_CAR_COORDINATES s7_bomb_car -2127.7991 -68.8104 34.1719  

				  	SET_CAR_HEADING s7_bomb_car 180.0000
						
				ENDIF

			ENDIF

			CLEAR_AREA -2129.2561 -74.4958 34.1719 100.0 TRUE

			// ----------------------------------------------------------------------------------------------

	  		CREATE_CHAR PEDTYPE_MISSION1 BMYCG -2119.0107 -99.2082 34.3281 s7_gate_inside[0]

			GIVE_WEAPON_TO_CHAR s7_gate_inside[0] WEAPONTYPE_MICRO_UZI 1000

	  		CREATE_CHAR PEDTYPE_MISSION1 hmyst -2121.1731 -108.9207 34.3281 s7_gate_inside[1]

			GIVE_WEAPON_TO_CHAR s7_gate_inside[1] WEAPONTYPE_MICRO_UZI 1000

	  		CREATE_CHAR PEDTYPE_MISSION1 hmyst -2104.2000 -162.4759 34.3273 s7_gate_inside[3]

			GIVE_WEAPON_TO_CHAR s7_gate_inside[3] WEAPONTYPE_MICRO_UZI 1000

	  		CREATE_CHAR PEDTYPE_MISSION1 hmyri -2100.0959 -157.8681 34.3273 s7_gate_inside[4]

			SET_CHAR_WEAPON_SKILL s7_gate_inside[4] WEAPONSKILL_PRO

			GIVE_WEAPON_TO_CHAR s7_gate_inside[4] WEAPONTYPE_PISTOL 9999

			CREATE_CHAR PEDTYPE_MISSION1 BMYCG -2162.6350 -224.1719 35.5156  s7_gate_inside[5]

			SET_CHAR_HEADING s7_gate_inside[5] 350.5417

			GIVE_WEAPON_TO_CHAR s7_gate_inside[5] WEAPONTYPE_MICRO_UZI 1000

			// ----------------------------------------------------------------------------------------------

			REPEAT 6 v

				IF NOT IS_CHAR_DEAD s7_gate_inside[v]
								   
					SET_CHAR_SHOOT_RATE s7_gate_inside[v] 30

					SET_CHAR_ACCURACY s7_gate_inside[v] 30

					SET_CHAR_IS_TARGET_PRIORITY s7_gate_inside[v] TRUE

					SET_CHAR_DECISION_MAKER s7_gate_inside[v] s7_dm_tough 

					SET_CHAR_RELATIONSHIP s7_gate_inside[v] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1	 

					SET_CHAR_RELATIONSHIP s7_gate_inside[v] ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1	

				ENDIF

			ENDREPEAT

			LVAR_INT s7_seq_gate

			OPEN_SEQUENCE_TASK s7_seq_gate 

				TASK_GO_STRAIGHT_TO_COORD -1 -2126.1443 -82.2896 34.3203 PEDMOVE_RUN -1

				TASK_GOTO_CHAR -1 scplayer -1 5.0

			CLOSE_SEQUENCE_TASK s7_seq_gate

			PERFORM_SEQUENCE_TASK s7_gate_inside[0] s7_seq_gate

			PERFORM_SEQUENCE_TASK s7_gate_inside[1] s7_seq_gate		   

			IF NOT IS_CHAR_DEAD s7_gate_inside[2]
				 
				CLEAR_CHAR_TASKS_IMMEDIATELY s7_gate_inside[2]

				TASK_PLAY_ANIM s7_gate_inside[2] point_loop ON_LOOKERS 8.0 TRUE FALSE FALSE TRUE 3000

			ENDIF

			TASK_GOTO_CHAR s7_gate_inside[3] scplayer -1 5.0

			TASK_GOTO_CHAR s7_gate_inside[4] scplayer -1 5.0

			SET_FIXED_CAMERA_POSITION -2119.4 -92.22 34.61 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2170.17 -161.04 46.68 JUMP_CUT

			GENERATE_RANDOM_INT_IN_RANGE 0 3 s7_rnd_1

			SWITCH s7_rnd_1

				CASE 0

					CLEAR_MISSION_AUDIO 1

				 	LOAD_MISSION_AUDIO 1 SOUND_SYN7_CA

					WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
						WAIT 0
					ENDWHILE

					PLAY_MISSION_AUDIO 1
			
					PRINT_NOW ( SYN7_CA ) 4000 1 // Get out there and kill the fool!
				
				BREAK
				
				CASE 1

					CLEAR_MISSION_AUDIO 1

				 	LOAD_MISSION_AUDIO 1 SOUND_SYN7_CB

					WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
						WAIT 0
					ENDWHILE

					PLAY_MISSION_AUDIO 1
			
					PRINT_NOW ( SYN7_CB ) 4000 1 // Open the gates and waste the dumb fuck!
				
				BREAK
				
				CASE 2

					CLEAR_MISSION_AUDIO 1

				 	LOAD_MISSION_AUDIO 1 SOUND_SYN7_CC

					WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
						WAIT 0
					ENDWHILE

					PLAY_MISSION_AUDIO 1
			
					PRINT_NOW ( SYN7_CC ) 4000 1 // Blast the moron, open the gate!
				
				BREAK				

			ENDSWITCH
					 
			WAIT 4000

			GOSUB s7_restore_camera

			REMOVE_BLIP s7_gates

		    ADD_BLIP_FOR_COORD -2175.2434 -209.7745 34.3281 s7_ramp_blip

			IF NOT IS_CAR_DEAD s7_bomb_car
			AND NOT IS_CHAR_DEAD scplayer

				IF NOT IS_CHAR_IN_CAR scplayer s7_bomb_car 

				  	CHANGE_BLIP_DISPLAY s7_ramp_blip NEITHER

				ENDIF	

			ENDIF 

			TIMERB = 0

			//SKIP_CUTSCENE_END

			REPORT_MISSION_AUDIO_EVENT_AT_OBJECT s7_front_gate SOUND_MESH_GATE_OPEN_START

			s7_mission = 2

		ENDIF

	ENDIF

	// **********************************************************************************************
	// *																							*
	// * 							          Open the gate           								*		
	// *																							*
	// **********************************************************************************************

	IF s7_mission = 2
	AND NOT IS_CAR_DEAD s7_bomb_car
	AND NOT IS_CHAR_DEAD scplayer

		IF DOES_OBJECT_EXIST s7_front_gate

			GET_OBJECT_COORDINATES s7_front_gate x y z

			IF x < -2117.0000  
				
				SLIDE_OBJECT s7_front_gate -2117.0000 -80.8000 34.3281 0.20 0.0 0.0 FALSE

			ELSE

				IF s7_gate_stop = 0

					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT s7_front_gate SOUND_MESH_GATE_OPEN_STOP

					s7_gate_stop = 1

				ENDIF

			ENDIF

		ENDIF

		// **********************************************************************************************
		// *																							*
		// * 							       Tell Player to use ramp    								*		
		// *																							*
		// **********************************************************************************************

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2138.2124 -201.8113 34.3281 20.0 20.0 20.0 FALSE
		AND s7_msg_displayed = 0

			PRINT_NOW ( SYN7_35 ) 4000 1 // ~s~Use the ~y~Ramp ~s~to get into the crack lab
			
			REPEAT 6 v

				DELETE_CHAR s7_gate_inside[v]

			ENDREPEAT

			REPEAT 2 v

				DELETE_CHAR s7_gate_guard[v]

			ENDREPEAT

			s7_msg_displayed = 1 

		ENDIF

		// **********************************************************************************************
		// *																							*
		// * 							   Player hits the Warehoouse ramp								*		
		// *																							*
		// **********************************************************************************************

		IF IS_CHAR_IN_CAR scplayer s7_bomb_car

			IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2181.7603 -209.4146 35.5234 4.0 4.0 4.0 FALSE

				PRINT_NOW ( SYN7_34 ) 6000 1 // ~s~Park the car near the ~y~Chemical containers~s~.

				REMOVE_BLIP s7_ramp_blip

				ADD_BLIP_FOR_COORD -2184.0588 -263.4215 35.5234 s7_plant_car_blip

				s7_mission = 3   

			ENDIF

			IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2184.9829 -218.1104 35.5234 4.0 4.0 4.0 FALSE

				PRINT_NOW ( SYN7_34 ) 6000 1 // ~s~Park the car near the ~y~Chemical containers~s~.

				REMOVE_BLIP s7_ramp_blip

				ADD_BLIP_FOR_COORD -2184.0588 -263.4215 35.5234 s7_plant_car_blip

				s7_mission = 3   

			ENDIF

		ENDIF

	ENDIF

	// **********************************************************************************************
	// *																							*
	// *								  Player is in the bomb zone								*	
	// *																							*
	// **********************************************************************************************

	IF s7_mission = 3
	AND NOT IS_CAR_DEAD s7_bomb_car
	AND NOT IS_CHAR_DEAD scplayer

		IF IS_CHAR_IN_CAR scplayer s7_bomb_car

			IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2150.5271 -241.7461 35.5234 4.0 4.0 4.0 FALSE
			AND NOT IS_CHAR_DEAD s7_workers[0]
			AND s7_desk_guy_txt = 0

				GENERATE_RANDOM_INT_IN_RANGE 0 5 s7_rnd_1

				SWITCH s7_rnd_1

					CASE 0

						$s7_print = &SYN7_DA	// Who the fuck are you!
						s7_audio = SOUND_SYN7_DA
						GOSUB s7_load_sample
					
					BREAK
					
					CASE 1

						$s7_print = &SYN7_DB	// What the fuck is he doing?
						s7_audio = SOUND_SYN7_DB
						GOSUB s7_load_sample
					
					BREAK
					
					CASE 2

						$s7_print = &SYN7_DC	// This ain't no carpark!
						s7_audio = SOUND_SYN7_DC
						GOSUB s7_load_sample				
											
					BREAK				
					
					CASE 3

						$s7_print = &SYN7_DD	// Shit, I'm so fried I'm seeing shit!
						s7_audio = SOUND_SYN7_DD
						GOSUB s7_load_sample
				
					BREAK																		
					
					CASE 4

						$s7_print = &SYN7_DE	// Have I missed something here?
						s7_audio = SOUND_SYN7_DE
						GOSUB s7_load_sample
											
					BREAK	

				ENDSWITCH
 
				s7_desk_guy_txt = 1

			ENDIF

			IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2184.0588 -263.4215 35.5234 4.0 4.0 4.0 s7_red

				PRINT_HELP_FOREVER ( SYN7_20 ) 

				s7_red = 0
				
				IF IS_BUTTON_PRESSED PAD1 CIRCLE

					WAIT 1000

					GOSUB s7_fade_out

					s7_mission = 4

					CLEAR_HELP

					IF NOT IS_CAR_DEAD s7_bomb_car

						FREEZE_CAR_POSITION s7_bomb_car TRUE
						
						SET_CAR_LIGHTS_ON s7_bomb_car FALSE

						OPEN_SEQUENCE_TASK s7_seq_leave

							TASK_LEAVE_CAR -1 s7_bomb_car	

						CLOSE_SEQUENCE_TASK s7_seq_leave		 

					ENDIF

					// **********************************************************************************************
					// *																							*
					// *							   Create guys inside the lab  									*	
					// *																							*
					// **********************************************************************************************

					LVAR_INT s7_seq_balcony

					OPEN_SEQUENCE_TASK s7_seq_balcony

						TASK_FOLLOW_PATH_NODES_TO_COORD -1 -2146.0422 -259.7312 39.7195 PEDMOVE_RUN -2

						TASK_FOLLOW_PATH_NODES_TO_COORD -1 -2146.2290 -267.7039 39.7195 PEDMOVE_RUN -2

						TASK_FOLLOW_PATH_NODES_TO_COORD -1 -2156.5100 -268.1435 39.7195 PEDMOVE_RUN -2
						
					CLOSE_SEQUENCE_TASK s7_seq_balcony

					// ----------------------------------------------------------------------------------------------

					// Guy on the balcony
					CREATE_CHAR PEDTYPE_MISSION1 BMYCG -2140.9307 -253.9011 39.7195 s7_cavalry[0]

					SET_CHAR_HEADING s7_cavalry[0] 150.6317

					PERFORM_SEQUENCE_TASK s7_cavalry[0] s7_seq_balcony

					CLEAR_SEQUENCE_TASK s7_seq_balcony

					// ----------------------------------------------------------------------------------------------

					OPEN_SEQUENCE_TASK s7_seq_balcony

						FLUSH_ROUTE

						EXTEND_ROUTE -2157.7261 -231.4555 35.5234 
						EXTEND_ROUTE -2153.7488 -236.6368 35.5234 
						EXTEND_ROUTE -2153.6233 -246.5058 35.5234  

						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

						FLUSH_ROUTE 

						TASK_FOLLOW_PATH_NODES_TO_COORD -1 -2162.2639 -260.4177 35.5234 PEDMOVE_RUN -2

						TASK_FOLLOW_PATH_NODES_TO_COORD -1 -2167.7651 -261.4950 35.5234 PEDMOVE_RUN -2

					CLOSE_SEQUENCE_TASK s7_seq_balcony

					// ----------------------------------------------------------------------------------------------

					CREATE_CHAR PEDTYPE_MISSION1 hmyst -2162.2356 -230.9540 35.5220 s7_cavalry[1]

					SET_CHAR_HEADING s7_cavalry[1] 231.2343

					PERFORM_SEQUENCE_TASK s7_cavalry[1] s7_seq_balcony

					CLEAR_SEQUENCE_TASK s7_seq_balcony

					// ----------------------------------------------------------------------------------------------

					OPEN_SEQUENCE_TASK s7_seq_balcony

						FLUSH_ROUTE

						EXTEND_ROUTE -2157.7261 -231.4555 35.5234 
						EXTEND_ROUTE -2153.7488 -236.6368 35.5234 
						EXTEND_ROUTE -2153.6233 -246.5058 35.5234  

						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

						FLUSH_ROUTE 
						
						TASK_FOLLOW_PATH_NODES_TO_COORD -1 -2154.9226 -248.2299 35.5234 PEDMOVE_RUN -2

					CLOSE_SEQUENCE_TASK s7_seq_balcony

					// ----------------------------------------------------------------------------------------------

					CREATE_CHAR PEDTYPE_MISSION1 hmyri -2168.3367 -228.4060 35.5220 s7_cavalry[2]

					SET_CHAR_HEADING s7_cavalry[2] 294.4890

					PERFORM_SEQUENCE_TASK s7_cavalry[2] s7_seq_balcony

					CLEAR_SEQUENCE_TASK s7_seq_balcony

					// ----------------------------------------------------------------------------------------------

					OPEN_SEQUENCE_TASK s7_seq_balcony

						FLUSH_ROUTE

						EXTEND_ROUTE -2157.7261 -231.4555 35.5234 
						EXTEND_ROUTE -2153.7488 -236.6368 35.5234 
						EXTEND_ROUTE -2153.6233 -246.5058 35.5234  

						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

						FLUSH_ROUTE 
						
						TASK_FOLLOW_PATH_NODES_TO_COORD -1 -2156.8347 -234.2320 35.5234 PEDMOVE_RUN -2

					CLOSE_SEQUENCE_TASK s7_seq_balcony

					// ----------------------------------------------------------------------------------------------

					CREATE_CHAR PEDTYPE_MISSION1 hmyri -2168.7397 -231.3480 35.5234 s7_cavalry[3]

					SET_CHAR_HEADING s7_cavalry[3] 278.3553

					PERFORM_SEQUENCE_TASK s7_cavalry[3] s7_seq_balcony

					CLEAR_SEQUENCE_TASK s7_seq_balcony

					// ----------------------------------------------------------------------------------------------

					// Guy guarding exit doorway 
					CREATE_CHAR PEDTYPE_MISSION1 hmyri -2162.4172 -227.2437 35.5143 s7_cavalry[4]

					SET_CHAR_HEADING s7_cavalry[4] 196.0546

					TASK_STAY_IN_SAME_PLACE s7_cavalry[4] TRUE

					// ----------------------------------------------------------------------------------------------

					REPEAT 5 v
														   
						SET_CHAR_SHOOT_RATE s7_cavalry[v] 30

						SET_CHAR_ACCURACY s7_cavalry[v] 30

						SET_CHAR_IS_TARGET_PRIORITY s7_cavalry[v] TRUE

						IF v = 4 

							SET_CHAR_WEAPON_SKILL s7_cavalry[v] WEAPONSKILL_PRO

							GIVE_WEAPON_TO_CHAR s7_cavalry[v] WEAPONTYPE_PISTOL 9999

						ELSE

							GIVE_WEAPON_TO_CHAR s7_cavalry[v] WEAPONTYPE_MICRO_UZI 30000

							SET_CURRENT_CHAR_WEAPON s7_cavalry[v] WEAPONTYPE_MICRO_UZI 

					   	ENDIF

						SET_CHAR_DECISION_MAKER s7_cavalry[v] s7_dm_tough

						SET_CHAR_RELATIONSHIP s7_cavalry[v] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1

						SET_CHAR_RELATIONSHIP s7_cavalry[v] ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1
							
					ENDREPEAT
																
					GOSUB s7_set_camera

					DELETE_CHAR s7_workers[1]
					DELETE_CHAR s7_workers[2]
					DELETE_CHAR s7_workers[3]
					DELETE_CHAR s7_workers[4]

					LOAD_SCENE_IN_DIRECTION -2150.4399 -239.1755 35.5234 52.2777 

					//Setting Up The Camera Positioning
					SET_FIXED_CAMERA_POSITION -2152.8604 -236.6111 37.0321 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT -2153.6233 -235.9684 36.9637 JUMP_CUT

					IF NOT IS_CHAR_DEAD s7_cavalry[4]

						TASK_PLAY_ANIM s7_cavalry[4] point_loop ON_LOOKERS 8.0 FALSE FALSE FALSE FALSE 4000						

					ENDIF

					WAIT 500

					GOSUB s7_fade_in

					PRINT_NOW ( SYN7_37 ) 4000 1 // ~s~Get out of the warehouse before the timer runs out

					SET_WANTED_MULTIPLIER 0.0

					WAIT 4000

					GOSUB s7_restore_camera

					IF NOT IS_CAR_DEAD s7_bomb_car
					AND NOT IS_CHAR_DEAD scplayer
													
						TASK_LEAVE_CAR scplayer s7_bomb_car
							
					ENDIF

					REMOVE_BLIP s7_plant_car_blip

					DISPLAY_ONSCREEN_TIMER_WITH_STRING s7_timer TIMER_DOWN SYN7_18 // BOMB : ~1~ 

					ADD_BLIP_FOR_COORD -2162.9644 -225.5678 35.5156 s7_exit_door_blip

					TIMERA = 0

					CLEAR_WANTED_LEVEL player1

				ENDIF

			ELSE

				CLEAR_HELP

				s7_red = 1

			ENDIF 

		ELSE

			CLEAR_HELP

		ENDIF

	ENDIF

	// **********************************************************************************************
	// *																							*
	// *								 At the warehouse exit door									*	
	// *																							*
	// **********************************************************************************************

	IF s7_mission = 4

		IF TIMERA > 4000
		AND s7_escape_txt = 0

			s7_escape_txt = 1

		ENDIF 

		IF s7_timer = 0

			s7_died_in_explosion = 1

			GOTO explosion			

		ENDIF

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2162.9644 -225.5678 35.5156 1.2 1.2 1.2 FALSE
		OR LOCATE_CHAR_ANY_MEANS_3D scplayer -2186.1660 -216.9555 35.5143 6.0 6.0 6.0 FALSE

			GOSUB s7_fade_out

			GOSUB s7_set_camera

			LOAD_SCENE -2187.5046 -263.5027 36.5551

			LOAD_SCENE_IN_DIRECTION -2157.0593 -262.1108 35.5156 89.9913  

			REMOVE_BLIP s7_exit_door_blip

			ADD_BLIP_FOR_COORD -2127.2002 -84.9324 34.3273 s7_gates			

			CLEAR_ONSCREEN_TIMER s7_timer

			//Setting Up The Camera Positioning
			SET_FIXED_CAMERA_POSITION -2168.9700 -262.7310 36.3878 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2169.9675 -262.7543 36.4530 JUMP_CUT

			GOSUB s7_fade_in

			IF NOT IS_CAR_DEAD s7_bomb_car

				EXPLODE_CAR_IN_CUTSCENE_SHAKE_AND_BITS s7_bomb_car TRUE TRUE TRUE

			ENDIF

			WAIT 100

			ADD_EXPLOSION -2187.5046 -263.5027 36.5551  EXPLOSION_GRENADE

			WAIT 500

			ADD_EXPLOSION -2187.1799 -263.8283 39.8421  EXPLOSION_GRENADE 

			WAIT 500

			ADD_EXPLOSION -2187.1799 -263.8283 42.8421  EXPLOSION_GRENADE

			WAIT 500

			ADD_EXPLOSION -2187.4321 -259.6525 42.0391  EXPLOSION_GRENADE
			ADD_EXPLOSION -2188.3992 -266.6203 42.6058  EXPLOSION_GRENADE

			WAIT 500

			ADD_EXPLOSION -2185.5955 -265.3064 47.5696  EXPLOSION_GRENADE

			WAIT 500

			ADD_EXPLOSION -2184.7742 -262.4794 37.4383  EXPLOSION_GRENADE
			ADD_EXPLOSION -2187.5740 -262.5659 39.7252  EXPLOSION_GRENADE

			WAIT 500	  

			// ***********************************************************************************************
			// *																						     *
			// *									  Door Cutscene										     *
			// *																						     *
			// ***********************************************************************************************

			IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2163.6 -225.62 36.0 10.0 10.0 10.0 FALSE // Door
			   	 
				IF IS_CHAR_IN_ANY_CAR scplayer

					STORE_CAR_CHAR_IS_IN scplayer car

					WARP_CHAR_FROM_CAR_TO_COORD scplayer -2162.7344 -225.3373 35.4984
						
					CLEAR_AREA -2162.7344 -225.3373 35.4984 10.0 TRUE

					DELETE_CAR car
				
				ELSE

					SET_CHAR_COORDINATES scplayer -2162.7344 -225.3373 35.4984

				ENDIF

				SET_CHAR_HEADING scplayer 0.0000

				s7_door_not_ramp = 0

			ENDIF

			// ***********************************************************************************************
			// *																						     *
			// *									 Ramp Cutscene										     *
			// *																						     *
			// ***********************************************************************************************

			IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2186.1660 -216.9555 35.5143 10.0 10.0 10.0 FALSE // Ramps
							   	 
				IF IS_CHAR_IN_ANY_CAR scplayer

					STORE_CAR_CHAR_IS_IN scplayer car

					WARP_CHAR_FROM_CAR_TO_COORD scplayer -2162.7344 -225.3373 35.4984
						
					CLEAR_AREA -2162.7344 -225.3373 35.4984 10.0 TRUE

					DELETE_CAR car
				
				ELSE

					SET_CHAR_COORDINATES scplayer -2162.7344 -225.3373 35.4984

				ENDIF

				SET_CHAR_COORDINATES scplayer -2181.3308 -217.6694 35.5078 

				SET_CHAR_HEADING scplayer 286.3482

				s7_door_not_ramp = 1

			ENDIF

			GOSUB s7_fade_out 

			REMOVE_IPL CRACK

			SET_VISIBILITY_OF_CLOSEST_OBJECT_OF_TYPE -2166.86 -236.50 40.86 40.0 crackfact_SFS FALSE

			SET_VISIBILITY_OF_CLOSEST_OBJECT_OF_TYPE -2185.49 -215.55 34.31 40.0 CF_ext_dem_SFS TRUE
			SET_VISIBILITY_OF_CLOSEST_OBJECT_OF_TYPE -2166.86 -236.50 40.86 40.0 LODcrackfact_SFS FALSE
			SET_VISIBILITY_OF_CLOSEST_OBJECT_OF_TYPE -2185.49 -215.55 34.31 40.0 LODext_dem_SFS TRUE
						
		   	WAIT 1000

			IF s7_door_not_ramp = 0

				IF NOT IS_CHAR_DEAD scplayer

					SET_CHAR_COORDINATES scplayer -2160.7290 -220.9850 34.3203

					SET_CHAR_HEADING scplayer 336.7796  

				ENDIF

			ENDIF

			IF s7_door_not_ramp = 1

				IF NOT IS_CHAR_DEAD scplayer
				
					SET_CHAR_COORDINATES scplayer -2175.5127 -214.8866 34.3203  

					SET_CHAR_HEADING scplayer 298.3929  

				ENDIF

			ENDIF
			
			GOSUB s7_cleanup 

			LOAD_SCENE_IN_DIRECTION -2162.2827 -220.5315 34.3203 324.4546 

			GOSUB s7_restore_camera

			CREATE_CHAR PEDTYPE_MISSION1 BMYCG -2132.0437 -168.6656 34.3273 s7_exit_guys[0]

			SET_CHAR_HEADING s7_exit_guys[0] 168.3635

			TASK_GO_STRAIGHT_TO_COORD s7_exit_guys[0] -2141.7656 -206.9254 34.3203 PEDMOVE_RUN -1

			CREATE_CHAR PEDTYPE_MISSION1 hmyst -2136.8782 -167.5268 34.3203 s7_exit_guys[1]

			SET_CHAR_HEADING s7_exit_guys[1] 172.0629

			TASK_GO_STRAIGHT_TO_COORD s7_exit_guys[1] -2141.7656 -206.9254 34.3203 PEDMOVE_RUN -1

			CREATE_CAR VOODOO -2124.6057 -87.9687 34.3203 s7_bike_1
			
			SET_CAN_BURST_CAR_TYRES s7_bike_1 FALSE

			SET_CAR_CRUISE_SPEED s7_bike_1 40.0

		  	SET_CAR_HEADING s7_bike_1 231.2455
		  	 
			CREATE_CHAR_INSIDE_CAR s7_bike_1 PEDTYPE_MISSION1 BMYCG s7_exit_guys[2]

			CREATE_CHAR_AS_PASSENGER s7_bike_1 PEDTYPE_MISSION1 BMYCG 0 s7_exit_guys[3]

			CREATE_CHAR PEDTYPE_MISSION1 BMYCG -2103.2910 -133.9342 34.3273 s7_exit_guys[4]

			SET_CHAR_HEADING s7_exit_guys[4] 170.6763
			
			CREATE_CHAR PEDTYPE_MISSION1 hmyst -2107.3704 -146.9669 34.3273 s7_exit_guys[5]

			SET_CHAR_HEADING s7_exit_guys[5] 174.6227
						
			CREATE_CHAR PEDTYPE_MISSION1 hmyst -2145.1157 -181.3902 37.6913 s7_exit_guys[6]

			SET_CHAR_HEADING s7_exit_guys[6] 176.6880
			
			TASK_STAY_IN_SAME_PLACE s7_exit_guys[6] TRUE  

			TASK_TOGGLE_DUCK s7_exit_guys[6] TRUE

			CREATE_CHAR PEDTYPE_MISSION1 hmyst -2111.8081 -159.8649 34.3273 s7_exit_guys[7]

			SET_CHAR_HEADING s7_exit_guys[7] 138.7297 

			TASK_STAY_IN_SAME_PLACE s7_exit_guys[7] TRUE

			REPEAT 8 v 

				GIVE_WEAPON_TO_CHAR s7_exit_guys[v] WEAPONTYPE_MICRO_UZI 1000

				SET_CHAR_ACCURACY s7_exit_guys[v] 30

				SET_CHAR_SHOOT_RATE s7_exit_guys[v] 30

				SET_CHAR_DECISION_MAKER s7_exit_guys[v] s7_dm_tough

				SET_CHAR_RELATIONSHIP s7_exit_guys[v] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1	 

				SET_CHAR_RELATIONSHIP s7_exit_guys[v] ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1	

				SET_SENSE_RANGE s7_exit_guys[v] 70.0

				SET_CHAR_IS_TARGET_PRIORITY s7_exit_guys[v] TRUE
				
			ENDREPEAT

			REQUEST_CAR_RECORDING 485
	
			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 485

				WAIT 0
					
			ENDWHILE

			IF NOT IS_CAR_DEAD s7_bike_1

				START_PLAYBACK_RECORDED_CAR s7_bike_1 485

				PAUSE_PLAYBACK_RECORDED_CAR s7_bike_1

			ENDIF	

			GOSUB s7_fade_in
			 
			IF s7_died_in_explosion = 1
			AND NOT IS_CHAR_DEAD scplayer

				explosion:

				GET_CHAR_COORDINATES scplayer x y z

				REPEAT 4 v

					TASK_DIE scplayer
					
					GET_CHAR_COORDINATES scplayer x y z

					GENERATE_RANDOM_FLOAT_IN_RANGE -3.0 3.0 s7_rnd
					x = x + s7_rnd
					y = y + s7_rnd

					ADD_EXPLOSION x y z EXPLOSION_GRENADE
	
					GET_CHAR_COORDINATES scplayer x y z

					GENERATE_RANDOM_FLOAT_IN_RANGE -3.0 3.0 s7_rnd
					x = x + s7_rnd
					y = y + s7_rnd

					ADD_EXPLOSION x y z EXPLOSION_GRENADE

					GET_CHAR_COORDINATES scplayer x y z

					GENERATE_RANDOM_FLOAT_IN_RANGE -3.0 3.0 s7_rnd
					x = x + s7_rnd
					y = y + s7_rnd

					ADD_EXPLOSION x y z EXPLOSION_GRENADE

					WAIT 500

				ENDREPEAT		 

				GOTO mission_syn7_failed

			ENDIF

			PRINT_NOW ( SYN7_23 ) 4000 1 // ~s~Get safely out of the courtyard

			s7_mission = 5

		ENDIF

	ENDIF

	// **********************************************************************************************
	// *																							*
	// *								   Leaving the compound										*	
	// *																							*
	// **********************************************************************************************

	IF s7_mission = 5

		// **********************************************************************************************
		// *																							*
		// *								  Guy rolls out from corner									*	
		// *																							*
		// **********************************************************************************************
			
		IF s7_roll_out = 0
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2118.5403 -169.3188 34.3273 10.0 10.0 10.0 FALSE 
			OR LOCATE_CHAR_ANY_MEANS_3D scplayer -2118.5403 -169.3188 34.3273 10.0 10.0 10.0 FALSE 

				IF NOT IS_CHAR_DEAD s7_exit_guys[7]

					OPEN_SEQUENCE_TASK s7_sequence_task

						TASK_STAY_IN_SAME_PLACE -1 FALSE

						TASK_TOGGLE_DUCK -1 TRUE

						TASK_WEAPON_ROLL -1 FALSE	

						TASK_TOGGLE_DUCK -1 TRUE
					
					CLOSE_SEQUENCE_TASK s7_sequence_task

				    PERFORM_SEQUENCE_TASK s7_exit_guys[7] s7_sequence_task

					CLEAR_SEQUENCE_TASK s7_sequence_task

				ENDIF

				s7_roll_out = 1

			ENDIF
		ENDIF

		// **********************************************************************************************
		// *																							*
		// *								  Car drives in to compound									*	
		// *																							*
		// **********************************************************************************************
				
		IF s7_drive_in = 1

			IF NOT IS_CAR_DEAD s7_bike_1
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR s7_bike_1
			
					IF NOT IS_CHAR_DEAD s7_exit_guys[2]
					
						TASK_LEAVE_CAR s7_exit_guys[2] s7_bike_1

					ENDIF	

					IF NOT IS_CHAR_DEAD s7_exit_guys[3]

						TASK_LEAVE_CAR s7_exit_guys[3] s7_bike_1

					ENDIF
						
					s7_drive_in = 2

				ENDIF
			ENDIF

		ENDIF

		IF IS_CHAR_IN_AREA_3D scplayer -2097.7227 -109.9725 30.0000 -2155.9922 -122.7420 40.0000 FALSE
		AND s7_drive_in = 0

			IF NOT IS_CAR_DEAD s7_bike_1
			AND IS_PLAYBACK_GOING_ON_FOR_CAR s7_bike_1

				IF NOT IS_CAR_DEAD s7_bike_1

					UNPAUSE_PLAYBACK_RECORDED_CAR s7_bike_1

				ENDIF					

			ENDIF

			s7_drive_in = 1

		ENDIF

		IF s7_in_compound = 0
		AND s7_drive_in = 0

			IF NOT IS_CAR_DEAD s7_bike_1
			AND IS_PLAYBACK_GOING_ON_FOR_CAR s7_bike_1

				IF NOT IS_CAR_DEAD s7_bike_1

					UNPAUSE_PLAYBACK_RECORDED_CAR s7_bike_1

				ENDIF					

			ENDIF

			TIMERA = 0

			s7_gate_closing = 1

			s7_drive_in = 1

		ENDIF
			
		// **********************************************************************************************
		// *																							*
		// *								   	Shut the gates       									*	
		// *																							*
		// **********************************************************************************************

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2127.3696 -85.4970 35.5234 20.0 20.0 10.0 FALSE 
		AND s7_gate_closing = 0

			TIMERA = 0

			s7_gate_closing = 1

			PRINT_NOW ( SYN7_13 ) 3000 1 // ~s~They've shut the gate!

		ENDIF		

		IF TIMERA > 3000
		AND s7_gate_closing = 1

			IF NOT IS_CAR_DEAD s7_bike_1

				IF s7_respawned = 0

					ADD_BLIP_FOR_CAR s7_bike_1 s7_truck_blip
					 
					SET_BLIP_AS_FRIENDLY s7_truck_blip TRUE

				ENDIF

			ENDIF
			
			LVAR_INT s7_temp_gen

			ADD_BLIP_FOR_COORD -2145.9626 -117.1968 38.1988 s7_escape_ramp

			CHANGE_BLIP_DISPLAY s7_escape_ramp NEITHER

			REMOVE_BLIP s7_gates

			PRINT_NOW ( SYN7_38 ) 5000 1  // ~s~Use the ~b~Car ~s~to ramp over the wall.

			s7_gate_closing = 2

			CREATE_CHAR PEDTYPE_MISSION1 HMYST -2107.3704 -146.9669 34.3273 s7_backtrack[0]

			SET_CHAR_HEADING s7_backtrack[0] 174.6227
						
			CREATE_CHAR PEDTYPE_MISSION1 BMYCG -2107.3704 -146.9669 34.3273 s7_backtrack[1]

			SET_CHAR_HEADING s7_backtrack[1] 174.6227

			CREATE_CHAR PEDTYPE_MISSION1 HMYST -2136.1091 -180.0674 34.3203 s7_backtrack[2]

			SET_CHAR_HEADING s7_backtrack[2] 297.2213
						
			CREATE_CHAR PEDTYPE_MISSION1 BMYCG -2140.6001 -169.5873 34.3203 s7_backtrack[3]

			SET_CHAR_HEADING s7_backtrack[3] 258.2443
						
			CREATE_CHAR PEDTYPE_MISSION1 BMYCG -2143.4626 -147.7724 35.3922 s7_backtrack[4]

			SET_CHAR_HEADING s7_backtrack[4] 209.4012

			REPEAT 5 v 

				GIVE_WEAPON_TO_CHAR s7_backtrack[v] WEAPONTYPE_MICRO_UZI 1000

				SET_CHAR_DECISION_MAKER s7_backtrack[v] s7_dm_tough

				SET_CHAR_RELATIONSHIP s7_backtrack[v] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1	 

				SET_CHAR_RELATIONSHIP s7_backtrack[v] ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1	

				SET_SENSE_RANGE s7_backtrack[v] 70.0

				SET_CHAR_IS_TARGET_PRIORITY s7_backtrack[v] TRUE
				
			ENDREPEAT

		ENDIF

		IF TIMERA > 10000
		AND s7_gate_closing = 2

			s7_gate_closing = 3

		ENDIF

		IF IS_CAR_DEAD s7_bike_1

			IF DOES_BLIP_EXIST s7_truck_blip
				
				REMOVE_BLIP s7_truck_blip

			ENDIF

			CHANGE_BLIP_DISPLAY s7_escape_ramp BOTH

		ENDIF

		IF NOT IS_CAR_DEAD s7_bike_1

			IF NOT IS_CHAR_IN_CAR scplayer s7_bike_1
			AND s7_gate_closing > 1

				CHANGE_BLIP_DISPLAY s7_truck_blip BOTH

				IF DOES_BLIP_EXIST s7_escape_ramp 
					CHANGE_BLIP_DISPLAY s7_escape_ramp NEITHER
				ENDIF

			ELSE

				CHANGE_BLIP_DISPLAY s7_truck_blip NEITHER

				IF DOES_BLIP_EXIST s7_escape_ramp
					CHANGE_BLIP_DISPLAY s7_escape_ramp BOTH
				ENDIF

			ENDIF

		ENDIF

		IF s7_in_compound = 0

			IF DOES_BLIP_EXIST s7_gates

				REMOVE_BLIP s7_gates

			ENDIF

			IF DOES_BLIP_EXIST s7_escape_ramp

				REMOVE_BLIP s7_escape_ramp

			ENDIF

			IF DOES_BLIP_EXIST s7_escape_ramp

				REMOVE_BLIP s7_truck_blip

			ENDIF

			s7_mission = 6			

			ADD_BLIP_FOR_COORD -2029.6029 156.7249 27.8429 s7_home_blip

			PRINT_NOW ( SYN7_39 ) 6000 1 // ~s~Get back to the ~y~Garage

		ENDIF

		// **********************************************************************************************
		// *																							*
		// *								      Ramp over to safety  									*	
		// *																							*
		// **********************************************************************************************

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2149.3635 -111.5658 40.9064 3.0 3.0 3.0 FALSE 
		OR LOCATE_CHAR_ANY_MEANS_3D scplayer -2143.4290 -111.1119 41.0391 3.0 3.0 3.0 FALSE 
			
			IF s7_velocity > 10.0
			AND IS_CHAR_IN_ANY_CAR scplayer

				STORE_CAR_CHAR_IS_IN scplayer car

				SET_CAR_FORWARD_SPEED car 25.0

				SWITCH_WIDESCREEN ON

				CLEAR_HELP
				
				CLEAR_PRINTS

				SET_TIME_SCALE 0.4

				SET_CHAR_HEADING scplayer 0.6201 

				SET_FIXED_CAMERA_POSITION -2133.0652 -103.2940 37.6357 0.0 0.0 0.0

				POINT_CAMERA_AT_CHAR scplayer FIXED JUMP_CUT
				
				WAIT 1700
				
				SET_TIME_SCALE 1.0

				SWITCH_WIDESCREEN OFF

				RESTORE_CAMERA_JUMPCUT
				
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2146.6755 -90.7008 40.5966 4.0 4.0 6.0 FALSE

					IF NOT IS_CHAR_DEAD scplayer

						SET_CHAR_COORDINATES scplayer -2152.4226 -75.3105 34.3203 

						SET_CHAR_HEADING scplayer 357.8863 

					ENDIF

					SET_CAMERA_BEHIND_PLAYER

					IF NOT IS_CAR_DEAD car

						SET_CAR_FORWARD_SPEED car 0.0

					ENDIF

				ENDIF

	   		ENDIF

		ENDIF	

	ENDIF

	// **********************************************************************************************
	// *																							*
	// *							     	Close the gate 		 									*	
	// *																							*
	// **********************************************************************************************


	IF DOES_OBJECT_EXIST s7_front_gate

		GET_OBJECT_COORDINATES s7_front_gate x y z

		IF x > -2127.0000  
		AND s7_gate_closing = 1

			SLIDE_OBJECT s7_front_gate -2127.0000 -80.8000 34.3281 0.20 0.0 0.0 FALSE

		ENDIF

	ENDIF

	// **********************************************************************************************
	// *																							*
	// *							    Heading back to the garage 									*	
	// *																							*
	// **********************************************************************************************


	IF s7_mission = 6

		IF DOES_BLIP_EXIST s7_truck_blip
			
			REMOVE_BLIP s7_truck_blip

		ENDIF

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2029.6029 156.7249 27.8429 4.0 4.0 4.0 TRUE

			REMOVE_BLIP s7_home_blip

			GOTO mission_syn7_passed
					
		ENDIF		

	ENDIF

	// **********************************************************************************************
	// *																							*
	// *								   In car out of car blips									*	
	// *																							*
	// **********************************************************************************************
	
	IF NOT IS_CAR_DEAD s7_bomb_car
	AND NOT s7_mission = 0
	AND NOT s7_mission > 3

		IF NOT IS_CHAR_IN_CAR scplayer s7_bomb_car
																						 
			PRINT_NOW ( SYN7_32 ) 100 1 // ~s~Get back in the car!

			IF s7_get_back = 0
														    
				CHANGE_BLIP_DISPLAY s7_bomb_car_blip BOTH

				IF s7_mission = 1						   
					CHANGE_BLIP_DISPLAY s7_gates NEITHER
				ENDIF

				IF s7_mission = 2
					CHANGE_BLIP_DISPLAY s7_ramp_blip NEITHER   
				ENDIF

				IF s7_mission = 3
					CHANGE_BLIP_DISPLAY s7_plant_car_blip NEITHER
				ENDIF

				s7_get_back = 1

			ENDIF  

		ELSE 
			
			IF s7_get_back = 1

				CHANGE_BLIP_DISPLAY s7_bomb_car_blip NEITHER

				IF s7_mission = 1
					CHANGE_BLIP_DISPLAY s7_gates BOTH
				ENDIF

				IF s7_mission = 2
					CHANGE_BLIP_DISPLAY s7_ramp_blip BOTH
				ENDIF

				IF s7_mission = 3
					CHANGE_BLIP_DISPLAY s7_plant_car_blip BOTH
				ENDIF

				s7_get_back = 0

			ENDIF

		ENDIF

	ENDIF

	// **********************************************************************************************
	// *																							*
	// *								  Car gets destroyed     									*	
	// *																							*
	// **********************************************************************************************

	IF IS_CAR_DEAD s7_bomb_car
	AND s7_mission < 4

		PRINT_NOW ( SYN7_36 ) 4000 1 // ~r~The car has been destroyed!
		
		GOTO mission_syn7_failed		

	ENDIF

	// **********************************************************************************************
	// *																							*
	// *								  Car gets destroyed     									*	
	// *																							*
	// **********************************************************************************************

	IF IS_CAR_DEAD s7_bike_1
	AND s7_mission >= 5
	AND s7_gate_closing >= 2
	
		IF NOT s7_mission = 6

			PRINT_NOW ( SYN7_53 ) 4000 1 // ~s~Find another ~b~car ~s~so you can stunt jump out of the yard!

		ENDIF

		IF DOES_BLIP_EXIST s7_truck_blip

			REMOVE_BLIP s7_truck_blip

		ENDIF

		IF s7_in_compound = 1

			MARK_CAR_AS_NO_LONGER_NEEDED s7_bike_1

			s7_respawned = 1

			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer -2116.2642 -200.0631 34.3273 30.0 30.0 30.0 FALSE

				CLEAR_AREA -2116.2642 -200.0631 34.3273 10.0 TRUE

				CREATE_CAR VOODOO -2116.2642 -200.0631 34.3273 s7_bike_1
				
				SET_CAN_BURST_CAR_TYRES s7_bike_1 FALSE

				SET_CAR_CRUISE_SPEED s7_bike_1 40.0

			  	SET_CAR_HEADING s7_bike_1 352.5713
			  	
	 			ADD_BLIP_FOR_CAR s7_bike_1 s7_truck_blip
					 
				SET_BLIP_AS_FRIENDLY s7_truck_blip TRUE
			
			ELSE

				CLEAR_AREA -2116.2642 -200.0631 34.3273 10.0 TRUE

				CREATE_CAR VOODOO -2124.6057 -87.9687 34.3203 s7_bike_1
				
				SET_CAN_BURST_CAR_TYRES s7_bike_1 FALSE

				SET_CAR_CRUISE_SPEED s7_bike_1 40.0

			  	SET_CAR_HEADING s7_bike_1 231.2455 

	 			ADD_BLIP_FOR_CAR s7_bike_1 s7_truck_blip
					 
				SET_BLIP_AS_FRIENDLY s7_truck_blip TRUE

			ENDIF

		ENDIF

	ENDIF

ENDWHILE

GOTO mission_syn7_failed

// ******************************************************************************************************
// *																									*
// *  									  Delete Guards / Workers     									*
// *																									*
// ******************************************************************************************************

s7_cleanup:

	REPEAT 5 v

		DELETE_CHAR s7_workers[v]

	ENDREPEAT
	 
	REPEAT 5 v

		DELETE_CHAR s7_cavalry[v]

	ENDREPEAT

RETURN

// ******************************************************************************************************
// *																									*
// *  										   MISSION PASSED              								*
// *																									*
// ******************************************************************************************************

mission_syn7_passed:

	REMOVE_BLIP garage_contact_blip

	REMOVE_IPL Barriers2

	PRINT_WITH_NUMBER_BIG ( M_PASSS ) 25000 5000 1 //"Mission Passed!" //100 being the amount of cash
	ADD_SCORE player1 25000//amount of cash

	AWARD_PLAYER_MISSION_RESPECT 60 //amount of respect

	CLEAR_WANTED_LEVEL player1

	PLAY_MISSION_PASSED_TUNE 1

	PLAYER_MADE_PROGRESS 1

	REGISTER_MISSION_PASSED ( SYND_7 ) // Yay Ka-Boom-Boom(syn7-PD)

	flag_synd_mission_counter++

	SET_INT_STAT CITIES_PASSED 2

	SET_INT_STAT PASSED_SYNDICATE_AND_WOOZIE 1

	//Gant Bridge  ( Golden Gate )
	SWITCH_ROADS_ON -2696.4641 1239.8665 40.7599 -2665.3591 2190.9604 70.8125 // Main Section.
	SWITCH_ROADS_ON -2740.6941 2233.6179 40.8431 -2720.9102 2338.2244 80.4822 // Country Section.
	SWITCH_ROADS_ON -2695.5842 1237.9807 40.7328 -2664.4170 1454.7675 60.8126

	// The Panopticon ( Red metal bridge in D6  )
	SWITCH_ROADS_ON -995.0013 -416.2032 30.4207 -940.5399 -251.8564 40.6762

	//Red County ( E5 Red curved bridge )
	SWITCH_ROADS_ON -205.8387 250.7443 7.2472 -131.0039 481.8496 15.9152

	//Flint County ( E7 small road bridge )
	SWITCH_ROADS_ON -100.7515 -927.8298 18.0 -68.3752 -891.9871 14.0

	//Hampton Barns            ( F5 Hexagonal Style framing )
	SWITCH_ROADS_ON 609.7595 327.3437 15.8783 429.8884 616.0168 20.2890
	SWITCH_ROADS_ON 317.1688 707.7672 7.0 437.5726 709.0657 20.5578
	SWITCH_ROADS_ON 391.1194 640.0150 7.0 402.2627 664.7980 18.5098

	SWITCH_ROADS_ON 289.2904 636.3991 7.8675 409.4943 702.3849 20.0345
	SWITCH_ROADS_ON 300.3153 718.7909 7.7846 316.7906 781.0926 14.0795 
	SWITCH_ROADS_ON 254.9982 837.0290 10.1731 270.7453 929.2404 30.2553
	SWITCH_ROADS_ON 210.7811 939.2068 10.9062 249.4799 959.1111 30.2141

	SWITCH_ROADS_ON 230.4545 946.0961 20.6674 255.9772 969.2755 30.4776
	SWITCH_ROADS_ON 249.4279 899.7975 10.5871 268.6826 933.5995 30.3975
	SWITCH_ROADS_ON 312.1081 694.1089 6.0 324.0811 733.0005 10.0
	SWITCH_ROADS_ON 324.4526 804.9198 9.6186 332.8747 814.3560 14.3925

	//Montgomery Intersection ( I5 potential hotspot! )
	SWITCH_ROADS_ON 1690.8192 376.5103 28.1103 1730.2230 445.2955 30.8414
	SWITCH_ROADS_ON 1643.5355 227.3723 27.4457 1673.0623 295.5788 30.0815 
	SWITCH_ROADS_ON 1673.7654 388.1013 40.2331         1815.8619 804.9291 10.0 // Bridge section.
	SWITCH_ROADS_ON 1705.1558 308.3448 20.0  1710.9475 316.4094 23.5612 // Slip Road.

	//Wee metal side bridge in E8 near Flint Intersection
	SWITCH_ROADS_ON -12.7067 -1522.4554 1.0 80.8463 -1517.1113 5.0
	SWITCH_ROADS_ON -16.3392 -1532.8817 0.0394 69.3401 -1523.7710 5.9220 

	//Complicated tunnel bit from F8 to G8
	SWITCH_ROADS_ON 618.7253 -1189.6063 18.0 623.5441 -1161.9812 22.0 //Main Section blocker
	SWITCH_ROADS_ON -33.4208 -1341.8403 9.0 35.3764 -1303.9479 13.0 //Close Southbound traffic
	SWITCH_ROADS_ON -41.2393 -1385.8701 8.0 -3.5883 -1368.8558 10.5 //Fiddly section to stop northbound traffic but keep ring round open

	//Garver Bridge  ( Forth Road ) 
	SWITCH_ROADS_ON -1690.7048 539.6102 30.3278 -1100.5674 1140.5695 50.7350
	SWITCH_ROADS_ON -1799.5405 379.7155 16.0 -1780.1991 392.2779 18.0
	SWITCH_ROADS_ON -1092.4293 1286.5054 30.0 -1077.0385 1319.4948 35.0
	SWITCH_ROADS_ON -1860.1334 314.7891 38.0 -1638.5630 557.4354 40.0
	SWITCH_ROADS_ON -1737.3331 455.9431 30.3573 -1710.3633 500.6261 40.4891
	SWITCH_ROADS_ON -1689.2291 513.0995 30.2597 -1679.1241 524.8383 40.2500
	SWITCH_ROADS_ON -1742.9060 500.7302 30.4679 -1650.3119 551.8201 40.7455

	SWITCH_ROADS_OFF -1761.9504 507.8931 35.0533 -1751.3606 531.5917 41.3335 //SAN FRAN ROAD BACK OFF

RETURN
		
// ******************************************************************************************************
// *																									*
// *  									    MISSION FAILED    	         								*
// *																									*
// ******************************************************************************************************

mission_syn7_failed:

	PRINT_BIG ( M_FAIL ) 5000 1 

	IF NOT IS_CHAR_DEAD scplayer
		
		IF HAS_CHAR_BEEN_ARRESTED scplayer

			OVERRIDE_NEXT_RESTART -1605.7917 716.8598 11.0241 355.2978 // Cop Shop

		ENDIF

	ELSE

		OVERRIDE_NEXT_RESTART -2670.2854 616.4364 13.4531 183.1042 // Hospital

	ENDIF

RETURN

// ******************************************************************************************************
// *																									*
// *  									   MISSION CLEAN-UP               								*
// *																									*
// ******************************************************************************************************

mission_cleanup_syn7:
								
	IF NOT IS_CHAR_DEAD scplayer

		STOP_CHAR_FACIAL_TALK scplayer

	ENDIF

	// Density Multipliers
	REMOVE_CAR_RECORDING 483
	REMOVE_CAR_RECORDING 485


	SET_PED_DENSITY_MULTIPLIER 1.0

	SET_CAR_DENSITY_MULTIPLIER 1.0

	CLEAR_ONSCREEN_TIMER s7_timer

	SWITCH_ROADS_BACK_TO_ORIGINAL -2175.8174 -55.8818 27.0000 -2059.2053 -99.4399 37.0000 

	SWITCH_PED_ROADS_BACK_TO_ORIGINAL -2175.8174 -55.8818 27.0000 -2059.2053 -99.4399 37.0000 

	SWITCH_PED_ROADS_BACK_TO_ORIGINAL -2107.9805 -71.8985 30.0000 -2160.2563 -89.7108 37.0000 

	IF DOES_BLIP_EXIST s7_bombshop_blip

		REMOVE_BLIP s7_bombshop_blip

	ENDIF

	IF DOES_BLIP_EXIST s7_bomb_car_blip

		REMOVE_BLIP s7_bomb_car_blip

	ENDIF

	IF DOES_BLIP_EXIST s7_gates

		REMOVE_BLIP s7_gates

	ENDIF

	IF DOES_BLIP_EXIST s7_ramp_blip			  

		REMOVE_BLIP s7_ramp_blip

	ENDIF

	IF DOES_BLIP_EXIST s7_plant_car_blip

		REMOVE_BLIP s7_plant_car_blip

	ENDIF

	IF DOES_BLIP_EXIST s7_exit_door_blip

		REMOVE_BLIP s7_exit_door_blip

	ENDIF

	IF DOES_BLIP_EXIST s7_escape_ramp

		REMOVE_BLIP s7_escape_ramp

	ENDIF

	IF DOES_BLIP_EXIST s7_truck_blip
		
		REMOVE_BLIP s7_truck_blip

	ENDIF
	
	REPEAT 2 v

		IF DOES_BLIP_EXIST s7_gate_guard_blip[v]
			
			REMOVE_BLIP s7_gate_guard_blip[v]

		ENDIF

	ENDREPEAT 

	IF IS_ANY_PICKUP_AT_COORDS -2182.6523 -247.3813 36.4000

		REMOVE_PICKUP s7_health

	ENDIF

  	flag_player_on_mission = 0

	SET_GROUP_SEPARATION_RANGE Players_Group 30.0

	GET_GAME_TIMER timer_mobile_start

	REMOVE_ALL_SCRIPT_FIRES

	MARK_MODEL_AS_NO_LONGER_NEEDED tampa
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH

	REMOVE_ANIMATION CAR
	REMOVE_ANIMATION ON_LOOKERS
	REMOVE_ANIMATION INT_HOUSE

	MARK_MODEL_AS_NO_LONGER_NEEDED BMYCG
	MARK_MODEL_AS_NO_LONGER_NEEDED hmyst
	MARK_MODEL_AS_NO_LONGER_NEEDED hmyri
	MARK_MODEL_AS_NO_LONGER_NEEDED micro_uzi
	MARK_MODEL_AS_NO_LONGER_NEEDED VOODOO
	MARK_MODEL_AS_NO_LONGER_NEEDED DYN_RAMP
	MARK_MODEL_AS_NO_LONGER_NEEDED cellphone

	MARK_MODEL_AS_NO_LONGER_NEEDED WFYCRK
	MARK_MODEL_AS_NO_LONGER_NEEDED HMYCM

	MARK_MODEL_AS_NO_LONGER_NEEDED MOLOTOV

	MARK_MODEL_AS_NO_LONGER_NEEDED health

	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45

	MISSION_HAS_FINISHED

RETURN

s7_set_camera:

	IF NOT IS_CHAR_DEAD scplayer

		CLEAR_PRINTS
		CLEAR_HELP

		SWITCH_WIDESCREEN ON
		SET_PLAYER_CONTROL player1 OFF

	ENDIF

RETURN

s7_restore_camera:

	IF NOT IS_CHAR_DEAD scplayer

		CLEAR_PRINTS
		CLEAR_HELP

		SWITCH_WIDESCREEN OFF
		SET_PLAYER_CONTROL player1 ON

		RESTORE_CAMERA_JUMPCUT
		SET_CAMERA_BEHIND_PLAYER

	ENDIF
 
RETURN

s7_fade_out:

	DO_FADE 500 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

s7_fade_in:

	DO_FADE 500 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

// *****************************************************************************************
// *																					   *
// *                                   Keyboard shortcuts								   *
// *																					   *
// *****************************************************************************************

s7_keys:

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_Y
	
		IF NOT IS_CHAR_DEAD scplayer

			SET_CHAR_COORDINATES scplayer -1705.8823 1028.9674 44.1953  

			SET_CHAR_HEADING scplayer 299.1340

			s7_start_call = 8

		ENDIF

		IF NOT IS_CHAR_DEAD scplayer

			TASK_USE_MOBILE_PHONE scplayer FALSE

		ENDIF

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D

		IF NOT IS_CHAR_DEAD scplayer

			SET_CHAR_COORDINATES scplayer -2064.0569 -68.6422 34.1563  
  
			SET_CHAR_HEADING scplayer 94.0454
	
		ENDIF

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_F

		REMOVE_IPL CRACK
		
		REQUEST_IPL FUKDCRAK

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_K

		IF NOT IS_CHAR_DEAD scplayer

			SET_CHAR_COORDINATES scplayer -2064.0569 -68.6422 34.1563  
  
			SET_CHAR_HEADING scplayer 94.0454
	
		ENDIF

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_L

		s7_mission = 5

	ENDIF

RETURN 

s7_load_sample:

	CLEAR_MISSION_AUDIO 1

	LOAD_MISSION_AUDIO 1 s7_audio

	s7_playing = 0

RETURN

s7_play_sample:

	IF HAS_MISSION_AUDIO_LOADED 1
	AND s7_playing = 0

		PLAY_MISSION_AUDIO 1

		PRINT_NOW ( $s7_print ) 10000 1  

		s7_playing = 1

	ENDIF

	IF HAS_MISSION_AUDIO_FINISHED 1
	AND s7_playing = 1

		CLEAR_MISSION_AUDIO 1	

		CLEAR_THIS_PRINT $s7_print

		s7_playing = 2

	ENDIF
	
RETURN

}			   	  

/*
{ ---------------- Blow Up Crack Factory "Syndicate 7" -------------}
[SYN7_01:SYN7]  
~s~Go and pick up the wired car from the ~y~Bomb Shop~s~.

[SYN7_03:SYN7]  
~s~ Park the car inside the ~y~Crack lab~s~.

[SYN7_05:SYN7]  
~s~ Bomb activated.

[SYN7_6:SYN7]
~o~ = Activate bomb, but only do so when car is in position.

[SYN7_8:SYN7]
~s~Get as far away from the crack lab as possible! 

[SYN7_9:SYN7]
~r~The blast did not destroy the crack lab!  

[SYN7_13:SYN7]
~s~They've shut the gate!

[SYN7_18:SYN7]
Bomb : ~1~ 

[SYN7_20:SYN7]
~o~ = Activate bomb.

[SYN7_23:SYN7]
~s~Escape out the ~y~Main gates~s~.

[SYN7_24:SYN7]
~r~You got caught in the blast!

[SYN7_29:SYN7]
~s~Get back in the ~b~car ~s~and set the bomb!

[SYN7_32:SYN7]
~s~Get back in the ~b~car~s~!

[SYN7_34:SYN7]
~s~Park the car near the ~y~Chemical containers~s~.								  

[SYN7_35:SYN7]
~s~Use the ~y~Ramp ~s~to get into the crack lab.

[SYN7_36:SYN7]
~r~The car has been destroyed!

[SYN7_37:SYN7]
~s~Get out of the warehouse before the timer runs out.

[SYN7_38:SYN7]
~s~Use the ~b~Car ~s~to ramp over the wall.
							    
[SYN7_39:SYN7]
~s~Get back to the ~y~Garage~s~.

[SYN7_41:SYN7]
Hey, dude, is it ready?

[SYN7_42:SYN7]
She's all set. Get in.

[SYN7_43:SYN7]
She's packing enough to take out that crack lab.

[SYN7_44:SYN7]
I've wired in a delay timer, to give you time to get out.
	    
[SYN7_45:SYN7]
Ok, thanks, dude.

[SYN7_46:SYN7]
Hey, some loco basta blasting on our boys!

[SYN7_47:SYN7]
It's a hit!

[SYN7_48:SYN7]
We're being hit!

[SYN7_49:SYN7]
Get out there and kill the fool!

[SYN7_50:SYN7]
Open the gates and waste the dumb fuck!

[SYN7_51:SYN7]
Blast the moron, open the gate!

[SYN7_52:SYN7]
~s~Kill the ~r~Guards~s~, that will get their attention.

[SYN7_53:SYN7]
What the fuck is going on!

[SYN7_54:SYN7]
Hey! you can't drive that in here.

[SYN7_55:SYN7]
This ain't no car park, get the fuck out!



*/






			    