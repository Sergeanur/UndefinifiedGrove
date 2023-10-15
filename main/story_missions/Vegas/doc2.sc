MISSION_START

// *****************************************************************************************
// ************************************ Doc2 - Jumper **************************************
// *****************************************************************************************
// ************************************* Paul Davis ****************************************
// *****************************************************************************************

SCRIPT_NAME DOC2

// Mission start stuff

GOSUB mission_start_doc2

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_doc2_failed
ENDIF

GOSUB mission_cleanup_doc2

MISSION_END
 
VAR_INT doc2_heal doc2_rnd d2_rnd

{
 
// Variables for mission

LVAR_INT pickup_truck pickup_blip new_heal old_heal value_heal v
LVAR_FLOAT truck_X truck_Y truck_Z 

LVAR_INT d2_ped[15] d2_flee[15] d2_ped_seq[15]

LVAR_INT d2_seq 
LVAR_INT d2_in_truck_1 	 
LVAR_INT hit_van 
LVAR_INT trig_jumper jump_point_blip betty_blip
LVAR_INT doc2_bleed 

// Doc G
LVAR_INT thedoc seq_doc seq_jump seq_sit d2_decision 
LVAR_INT pd_box1 pd_box2 pd_boxes
LVAR_INT d2_falling d2_mission_passed
LVAR_FLOAT jump_X jump_Y jump_Z	d2_ply_heading
LVAR_FLOAT pd_box_Z  
LVAR_INT d2_seq_cheer d2_seq_chant doc2_message_go_back
LVAR_INT d2_stolen d2_driver d2_direction
LVAR_INT d2_waiting_seq d2_in_truck	d2_medic_A d2_medic_B d2_medic_C d2_medic_D
LVAR_INT d2_ambu_A d2_ambu_B d2_conversation d2_truck_direction
LVAR_INT d2_selected
LVAR_TEXT_LABEL d2_print
LVAR_INT d2_audio
LVAR_INT d2_playing
LVAR_INT d2_TIMERC
LVAR_INT d2_already_played
LVAR_INT d2_break_the_loop 
LVAR_INT d2_when_he_jumps
LVAR_INT d2_playing_sfx d2_audio_sfx d2_seq_rnd
LVAR_INT d2_empty

VAR_INT d2_instance_health d2_anim_rnd 
 
// **************************************** Mission Start **********************************

mission_start_doc2:

d2_TIMERC = 0
d2_instance_health = 40
d2_anim_rnd = 0
doc2_heal   = 0
doc2_rnd    = 0
d2_rnd      = 0

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_WEAK d2_decision

SET_FADING_COLOUR 0 0 0

REGISTER_MISSION_GIVEN

flag_player_on_mission = 1

// *****************************************************************************************
// *																					   *
// *                                	  Requests   				   					   *
// *																					   *
// ***************************************************************************************** 
 
LOAD_MISSION_TEXT DOC2

SET_PLAYER_CONTROL player1 OFF

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

LOAD_CUTSCENE DOC_2
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE

CLEAR_AREA 2159.8992 1443.0424 9.8203 40.0 TRUE 
 
START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE

SET_PLAYER_CONTROL player1 OFF

SET_FADING_COLOUR 0 0 0
DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE
 
CLEAR_CUTSCENE

REQUEST_MODEL vbmyelv
REQUEST_MODEL VWFYPRO
REQUEST_MODEL WMOTR1
REQUEST_MODEL WMOprea
REQUEST_MODEL cellphone
REQUEST_MODEL HECK1
REQUEST_MODEL HECK2

REQUEST_ANIMATION ON_LOOKERS

LOAD_SPECIAL_CHARACTER 1 MADDOGG

WHILE NOT HAS_MODEL_LOADED vbmyelv
OR NOT HAS_MODEL_LOADED VWFYPRO
OR NOT HAS_MODEL_LOADED WMOTR1

	WAIT 0

ENDWHILE

WHILE NOT HAS_MODEL_LOADED WMOprea
OR NOT HAS_MODEL_LOADED cellphone
OR NOT HAS_MODEL_LOADED HECK1

	WAIT 0

ENDWHILE
							   
WHILE NOT HAS_MODEL_LOADED HECK2
OR NOT HAS_ANIMATION_LOADED ON_LOOKERS
OR NOT HAS_SPECIAL_CHARACTER_LOADED 1

	WAIT 0

ENDWHILE

SWITCH_ROADS_OFF 2090.9810 1388.5104 8.8203 2234.6863 1517.0773 10.8203

SWITCH_PED_ROADS_OFF 2090.9810 1388.5104 8.8203 2234.6863 1517.0773 10.8203

jump_X = 2163.6377
jump_Y = 1443.6627
jump_Z = 9.8203	 

// ****************************************END OF CUTSCENE**********************************

SET_CAR_DENSITY_MULTIPLIER 0.0

CLEAR_AREA 2161.3396 1442.5043 9.8203 20.0 TRUE

CLEAR_AREA 2107.1902 1409.1531 9.8203 10.0 TRUE
							
CREATE_CHAR PEDTYPE_CIVMALE SPECIAL01 2162.6200 1451.4747 23.1719 thedoc

SET_ANIM_GROUP_FOR_CHAR thedoc drunkman

SET_CHAR_HEADING thedoc 180.0000

SET_CHAR_HEALTH thedoc 1

OPEN_SEQUENCE_TASK d2_ped_seq[0]

	TASK_GO_STRAIGHT_TO_COORD -1 2154.1924 1437.9476 9.8203 PEDMOVE_WALK 8000
	TASK_TURN_CHAR_TO_FACE_CHAR -1 thedoc
	TASK_PLAY_ANIM -1 lkaround_in ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1
	TASK_PLAY_ANIM -1 lkaround_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 lkaround_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 lkaround_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 lkaround_out ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	SET_SEQUENCE_TO_REPEAT d2_ped_seq[0] 1
		
CLOSE_SEQUENCE_TASK d2_ped_seq[0]

OPEN_SEQUENCE_TASK d2_ped_seq[1]

	TASK_GO_STRAIGHT_TO_COORD -1 2164.5959 1435.1688 9.8203 PEDMOVE_WALK 8000
	TASK_TURN_CHAR_TO_FACE_CHAR -1 thedoc
	TASK_PLAY_ANIM -1 Pointup_in ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Pointup_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Pointup_shout ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Pointup_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Pointup_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	SET_SEQUENCE_TO_REPEAT d2_ped_seq[1] 1
		
CLOSE_SEQUENCE_TASK d2_ped_seq[1]

OPEN_SEQUENCE_TASK d2_ped_seq[2]

	TASK_GO_STRAIGHT_TO_COORD -1 2160.6958 1435.3804 9.8203 PEDMOVE_WALK 8000
	TASK_TURN_CHAR_TO_FACE_CHAR -1 thedoc
	TASK_PAUSE -1 100
	TASK_PLAY_ANIM -1 Pointup_in ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Pointup_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Pointup_shout ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Pointup_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Pointup_out ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	SET_SEQUENCE_TO_REPEAT d2_ped_seq[2] 1
		
CLOSE_SEQUENCE_TASK d2_ped_seq[2]

OPEN_SEQUENCE_TASK d2_ped_seq[3]

	TASK_GO_STRAIGHT_TO_COORD -1 2169.2629 1435.9274 9.8203 PEDMOVE_WALK 8000
	TASK_TURN_CHAR_TO_FACE_CHAR -1 thedoc
	TASK_PLAY_ANIM -1 Shout_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Shout_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Shout_02 ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Shout_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1
	TASK_PLAY_ANIM -1 Shout_01 ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	SET_SEQUENCE_TO_REPEAT d2_ped_seq[3] 1
		
CLOSE_SEQUENCE_TASK d2_ped_seq[3]

OPEN_SEQUENCE_TASK d2_ped_seq[4]

	TASK_GO_STRAIGHT_TO_COORD -1 2155.4204 1436.4200 9.8203 PEDMOVE_WALK 8000
	TASK_TURN_CHAR_TO_FACE_CHAR -1 thedoc
	TASK_PAUSE -1 500
	TASK_PLAY_ANIM -1 Shout_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Shout_01 ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Shout_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Shout_01 ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Shout_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	SET_SEQUENCE_TO_REPEAT d2_ped_seq[4] 1
		
CLOSE_SEQUENCE_TASK d2_ped_seq[4]

OPEN_SEQUENCE_TASK d2_ped_seq[5]

	TASK_GO_STRAIGHT_TO_COORD -1 2168.2898 1436.1907 9.8203 PEDMOVE_WALK 8000
	TASK_TURN_CHAR_TO_FACE_CHAR -1 thedoc
	TASK_PLAY_ANIM -1 Shout_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Shout_02 ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Shout_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Shout_01 ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 Shout_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1
	SET_SEQUENCE_TO_REPEAT d2_ped_seq[5] 1
		
CLOSE_SEQUENCE_TASK d2_ped_seq[5]

OPEN_SEQUENCE_TASK d2_ped_seq[6]

	TASK_GO_STRAIGHT_TO_COORD -1 2159.2529 1435.3115 9.8203 PEDMOVE_WALK 8000
	TASK_TURN_CHAR_TO_FACE_CHAR -1 thedoc
	TASK_PAUSE -1 500
	TASK_PLAY_ANIM -1 lkup_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 lkup_point ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 lkup_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 lkup_point ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1 
	TASK_PLAY_ANIM -1 lkup_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1
	SET_SEQUENCE_TO_REPEAT d2_ped_seq[6] 1
		
CLOSE_SEQUENCE_TASK d2_ped_seq[6]

OPEN_SEQUENCE_TASK seq_doc

	TASK_GO_STRAIGHT_TO_COORD -1 2165.6514 1452.0000 23.1721 PEDMOVE_WALK 8000
//	TASK_PAUSE -1 1000

	TASK_GO_STRAIGHT_TO_COORD -1 2160.5601 1452.0000 23.1719 PEDMOVE_WALK 8000
//	TASK_PAUSE -1 1000

	SET_SEQUENCE_TO_REPEAT seq_doc 1

CLOSE_SEQUENCE_TASK seq_doc

OPEN_SEQUENCE_TASK seq_jump

 	TASK_JUMP -1 FALSE
	
CLOSE_SEQUENCE_TASK seq_jump

OPEN_SEQUENCE_TASK seq_sit

	TASK_DUCK -1 -1
	SET_SEQUENCE_TO_REPEAT seq_sit 1
	
CLOSE_SEQUENCE_TASK seq_sit

OPEN_SEQUENCE_TASK d2_waiting_seq

	TASK_PLAY_ANIM -1 lkaround_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1	

	SET_SEQUENCE_TO_REPEAT d2_waiting_seq 1

CLOSE_SEQUENCE_TASK d2_waiting_seq

OPEN_SEQUENCE_TASK d2_seq_cheer

  	TASK_PLAY_ANIM -1 wave_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1

	TASK_WANDER_STANDARD -1

CLOSE_SEQUENCE_TASK d2_seq_cheer

OPEN_SEQUENCE_TASK d2_seq_chant

  	TASK_PLAY_ANIM -1 shout_loop ON_LOOKERS 1.0 FALSE FALSE FALSE FALSE -1

	TASK_WANDER_STANDARD -1

CLOSE_SEQUENCE_TASK d2_seq_chant 

CREATE_OBJECT_NO_OFFSET Smash_box_stay 2156.7190 1482.3524 19.1794  pd_box1

CREATE_OBJECT_NO_OFFSET Smash_box_brk 2156.7190 1482.3524 19.1794  pd_box2

doc2_heal = 100	 

// ****************************************************************
SET_CHAR_NEVER_TARGETTED thedoc TRUE

ADD_BLIP_FOR_COORD jump_X jump_Y jump_Z jump_point_blip

ADD_BLIP_FOR_COORD 1878.5455 2240.8198 9.8130 betty_blip

CHANGE_BLIP_DISPLAY jump_point_blip NEITHER

CHANGE_BLIP_DISPLAY betty_blip NEITHER

GENERATE_RANDOM_INT_IN_RANGE 0 5 doc2_rnd

// Randomise a time for Madd Dogg to jump off the building

SWITCH doc2_rnd

	CASE 0

		doc2_rnd = 16000

	BREAK

	CASE 1

		doc2_rnd = 18000

	BREAK

	CASE 2

		doc2_rnd = 20000

	BREAK

	CASE 3

		doc2_rnd = 22000

	BREAK

	CASE 4

		doc2_rnd = 24000

	BREAK

ENDSWITCH

OPEN_SEQUENCE_TASK d2_seq

	TASK_TURN_CHAR_TO_FACE_CHAR -1 thedoc
 	TASK_SCRATCH_HEAD -1
 	TASK_LOOK_ABOUT -1 4000
	TASK_PAUSE -1 1000
	SET_SEQUENCE_TO_REPEAT d2_seq 1

CLOSE_SEQUENCE_TASK d2_seq

v = 0

d2_rnd = 0

CREATE_CHAR PEDTYPE_CIVMALE WMOTR1 2154.1924 1437.9476 9.8203 d2_ped[0]

SET_CHAR_HEADING d2_ped[0] 341.9008

SET_CHAR_DECISION_MAKER d2_ped[0] d2_decision

PERFORM_SEQUENCE_TASK d2_ped[0] d2_ped_seq[0]

CREATE_CHAR PEDTYPE_CIVMALE vbmyelv 2164.5959 1435.1688 9.8203 d2_ped[1]

SET_CHAR_HEADING d2_ped[1] 8.2163

SET_CHAR_DECISION_MAKER d2_ped[1] d2_decision

PERFORM_SEQUENCE_TASK d2_ped[1] d2_ped_seq[1]

CREATE_CHAR PEDTYPE_CIVMALE HECK2 2160.6958 1435.3804 9.8203 d2_ped[2]

SET_CHAR_HEADING d2_ped[2] 8.2163

SET_CHAR_DECISION_MAKER d2_ped[2] d2_decision

PERFORM_SEQUENCE_TASK d2_ped[2] d2_ped_seq[2]

CREATE_CHAR PEDTYPE_CIVMALE WMOTR1 2169.2629 1435.9274 9.8203 d2_ped[3]

SET_CHAR_HEADING d2_ped[3] 8.2163

SET_CHAR_DECISION_MAKER d2_ped[3] d2_decision

PERFORM_SEQUENCE_TASK d2_ped[3] d2_ped_seq[3]

CREATE_CHAR PEDTYPE_CIVMALE VWFYPRO 2155.4204 1436.4200 9.8203 d2_ped[4]

SET_CHAR_HEADING d2_ped[4] 8.2163

SET_CHAR_DECISION_MAKER d2_ped[4] d2_decision

PERFORM_SEQUENCE_TASK d2_ped[4] d2_ped_seq[4]

CREATE_CHAR PEDTYPE_CIVMALE WMOprea 2168.2898 1436.1907 9.8203 d2_ped[5]

SET_CHAR_HEADING d2_ped[5] 8.2163

SET_CHAR_DECISION_MAKER d2_ped[5] d2_decision

PERFORM_SEQUENCE_TASK d2_ped[5] d2_ped_seq[5]

CREATE_CHAR PEDTYPE_CIVMALE HECK1 2159.2529 1435.3115 9.8203 d2_ped[6]

SET_CHAR_HEADING d2_ped[6] 2.0180  

SET_CHAR_DECISION_MAKER d2_ped[6] d2_decision

PERFORM_SEQUENCE_TASK d2_ped[6] d2_ped_seq[6]

d2_the_main_loop:

// Ambient Cars ***************************************************

REQUEST_MODEL walton
REQUEST_MODEL WMYMECH
REQUEST_MODEL smashboxpile

WHILE NOT HAS_MODEL_LOADED walton
OR NOT HAS_MODEL_LOADED WMYMECH
OR NOT HAS_MODEL_LOADED smashboxpile
	WAIT 0
ENDWHILE

CREATE_OBJECT smashboxpile 2112.7534 1403.9731 10.1484 pd_boxes

SET_OBJECT_HEADING pd_boxes 86.7997	

MARK_MODEL_AS_NO_LONGER_NEEDED smashboxpile

SET_CAR_MODEL_COMPONENTS walton 4 4

CREATE_CAR walton 2107.1902 1409.1531 9.8203 pickup_truck
MARK_MODEL_AS_NO_LONGER_NEEDED walton

SET_CAR_HEADING pickup_truck 180.0000 

// ****************************************************************

CHANGE_CAR_COLOUR pickup_truck 1 1

ADD_BLIP_FOR_CAR pickup_truck pickup_blip

SET_BLIP_AS_FRIENDLY pickup_blip TRUE

CHANGE_BLIP_DISPLAY pickup_blip BOTH

pd_box_Z = 0.0

IF NOT IS_CAR_DEAD pickup_truck

	ATTACH_OBJECT_TO_CAR pd_box1 pickup_truck -0.3 -1.0 pd_box_Z 0.0 0.0 0.0

	ATTACH_OBJECT_TO_CAR pd_box2 pickup_truck -0.3 -1.0 pd_box_Z 0.0 0.0 0.0

ENDIF

// Driver *********************************************************

CREATE_CHAR PEDTYPE_CIVMALE WMYMECH 2109.7932 1407.8574 9.8203 d2_driver
MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH

SET_CHAR_HEADING d2_driver 180.0000  

TASK_USE_MOBILE_PHONE d2_driver TRUE

GOSUB doc2_set_camera

IF NOT IS_CHAR_DEAD scplayer

	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
	
	SET_CHAR_COORDINATES scplayer 2162.4978 1434.3956 9.8203  

	SET_CHAR_HEADING scplayer 358.0000 

ENDIF

IF NOT IS_CHAR_DEAD thedoc

	CLEAR_CHAR_TASKS_IMMEDIATELY thedoc
	
	PERFORM_SEQUENCE_TASK thedoc d2_waiting_seq

ENDIF

CLEAR_AREA 2162.7048 1430.3086 9.8203 10.0 TRUE

SET_FIXED_CAMERA_POSITION 2162.5413 1423.8401 9.9728 0.0 0.0 0.0 // Bike from front
POINT_CAMERA_AT_POINT 2162.5583 1424.8029 10.2424 JUMP_CUT

SET_FADING_COLOUR 0 0 0
DO_FADE 0 FADE_IN

d2_playing = 2

d2_playing_sfx = 2

WAIT 1000

PRINT_NOW ( DOC2_X ) 5000 1 // ~s~Find a way to save Madd Dogg before he jumps!

TIMERB = 0
WHILE TIMERB < 2000
	WAIT 0
	IF IS_BUTTON_PRESSED PAD1 CROSS
	OR IS_BUTTON_PRESSED PAD1 CIRCLE
		GOTO d2_skip_the_cut
	ENDIF
ENDWHILE

IF NOT IS_CHAR_DEAD scplayer

	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

	TASK_ACHIEVE_HEADING scplayer 172.0000

ENDIF

TIMERB = 0
WHILE TIMERB < 2000
	WAIT 0
	IF IS_BUTTON_PRESSED PAD1 CROSS
	OR IS_BUTTON_PRESSED PAD1 CIRCLE
		GOTO d2_skip_the_cut
	ENDIF
ENDWHILE

d2_skip_the_cut:

IF NOT IS_CHAR_DEAD scplayer

	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

	SET_CHAR_HEADING scplayer 172.0000 

ENDIF

CLEAR_PRINTS

GOSUB doc2_restore_camera

SET_CAMERA_BEHIND_PLAYER

PRINT_NOW ( DOC2_01 ) 7000 1 // ~s~Use the ~b~pickup truck~s~ around the corner to rescue Madd Dogg!

WHILE NOT IS_CHAR_DEAD scplayer
AND NOT IS_CAR_DEAD pickup_truck
AND NOT IS_CHAR_DEAD thedoc

	WAIT 0

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
		
		GOTO mission_doc2_passed

	ENDIF

	d2_TIMERC ++

	GOSUB doc2_keys

	GOSUB d2_play_sample

	IF trig_jumper < 4
		
		IF d2_TIMERC > 200
		AND LOCATE_CHAR_ANY_MEANS_3D scplayer 2154.1924 1437.9476 9.8203 20.0 20.0 20.0 FALSE
		AND NOT IS_MESSAGE_BEING_DISPLAYED

			d2_choose_again:

			GENERATE_RANDOM_INT_IN_RANGE 0 9 d2_rnd

			IF d2_rnd = d2_already_played
			AND d2_break_the_loop < 3

				GOTO d2_choose_again

				d2_break_the_loop ++

			ENDIF

			d2_already_played = d2_rnd

			d2_break_the_loop = 0

			IF NOT IS_CHAR_DEAD d2_ped[0]

				IF LOCATE_CHAR_ANY_MEANS_3D d2_ped[0] 2154.1924 1437.9476 9.8203 2.0 2.0 2.0 FALSE
				AND NOT IS_MESSAGE_BEING_DISPLAYED
				AND d2_selected = 0 

					SWITCH d2_rnd

						CASE 0

							$d2_print = &DC2_AA	// Can I get your trainers?
							d2_audio = SOUND_DC2_AA
							GOSUB d2_load_sample

						BREAK

						CASE 1

							$d2_print = &DC2_AB	// You won’t be needing those fancy clothes.
							d2_audio = SOUND_DC2_AB
							GOSUB d2_load_sample

						BREAK

						CASE 2

							$d2_print = &DC2_AC	// Don’t take your clothes with y’!
							d2_audio = SOUND_DC2_AC
							GOSUB d2_load_sample

						BREAK

					ENDSWITCH

				ENDIF

			ENDIF

			IF NOT IS_CHAR_DEAD d2_ped[1]

				IF LOCATE_CHAR_ANY_MEANS_3D d2_ped[1] 2164.5959 1435.1688 9.8203 2.0 2.0 2.0 FALSE
				AND NOT IS_MESSAGE_BEING_DISPLAYED
				AND d2_selected = 0

					SWITCH d2_rnd

						CASE 3

							$d2_print = &DC2_AD	// A little more action!
							d2_audio = SOUND_DC2_AD
							GOSUB d2_load_sample

						BREAK

						CASE 4

							$d2_print = &DC2_AE	// The show must go on!
							d2_audio = SOUND_DC2_AE
							GOSUB d2_load_sample

						BREAK

						CASE 5

							$d2_print = &DC2_AF	// He’s gonna be pavement pizza!
							d2_audio = SOUND_DC2_AF
							GOSUB d2_load_sample

						BREAK

						CASE 6

							$d2_print = &DC2_AJ	// A little more action!
							d2_audio = SOUND_DC2_AJ
							GOSUB d2_load_sample

						BREAK

						CASE 7

							$d2_print = &DC2_AK	// The show must go on!
							d2_audio = SOUND_DC2_AK
							GOSUB d2_load_sample

						BREAK

						CASE 8

							$d2_print = &DC2_AL	// He’s gonna be pavement pizza!
							d2_audio = SOUND_DC2_AL
							GOSUB d2_load_sample

						BREAK

				    ENDSWITCH

				ENDIF

			ENDIF

			d2_TIMERC = 0

		ENDIF

	ENDIF

	IF NOT IS_CAR_DEAD pickup_truck
	AND NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD thedoc

		  	REPEAT 7 v

				IF NOT IS_CHAR_DEAD d2_ped[v]
				
					IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR d2_ped[v] scplayer
					AND d2_flee[v] = 0

						CLEAR_CHAR_TASKS d2_ped[v]

						TASK_GO_STRAIGHT_TO_COORD d2_ped[v] 2082.0046 1415.9094 9.8047 PEDMOVE_SPRINT 20000

						d2_flee[v] = 1
						
					ENDIF

					IF LOCATE_CHAR_ANY_MEANS_3D d2_ped[v] 2082.3811 1416.6362 9.8047 10.0 10.0 10.0 FALSE
					AND NOT d2_flee[v] = 3

						CLEAR_CHAR_TASKS d2_ped[v]

						TASK_WANDER_STANDARD d2_ped[v] 		
						
						d2_flee[v] = 3				
					
					ENDIF 

				ENDIF

			ENDREPEAT

			// Player is getting car then heading to jump point
			IF trig_jumper = 1
			OR trig_jumper = 0
					
				IF IS_CHAR_IN_CAR scplayer pickup_truck

					IF NOT IS_CHAR_DEAD d2_driver
					AND d2_stolen = 0

						CLEAR_CHAR_TASKS_IMMEDIATELY d2_driver

						TASK_KILL_CHAR_ON_FOOT d2_driver scplayer

						GENERATE_RANDOM_INT_IN_RANGE 0 2 d2_rnd

						SWITCH d2_rnd

							CASE 0

								$d2_print = &DOGG_GA	// Hey, that's my truck!
								d2_audio = SOUND_DOGG_GA
								GOSUB d2_load_sample

							BREAK

							CASE 1

								$d2_print = &DOGG_GB	// What the fuck you doing?
								d2_audio = SOUND_DOGG_GB
								GOSUB d2_load_sample

							BREAK

							CASE 2

								$d2_print = &DOGG_GC	// Hey! HEY!
								d2_audio = SOUND_DOGG_GC
								GOSUB d2_load_sample

							BREAK

						ENDSWITCH

						d2_stolen = 1				

					ENDIF

					CHANGE_BLIP_DISPLAY pickup_blip NEITHER
					CHANGE_BLIP_DISPLAY jump_point_blip BOTH
					// Player is in mission car
					trig_jumper = 1

				ELSE

					CHANGE_BLIP_DISPLAY pickup_blip BOTH
					CHANGE_BLIP_DISPLAY jump_point_blip NEITHER
					// Player not in mission car
					trig_jumper = 0
				
				ENDIF
				
				IF trig_jumper = 1				  
					IF LOCATE_CHAR_ANY_MEANS_3D scplayer jump_X jump_Y jump_Z 4.0 4.0 4.0 TRUE
					AND NOT IS_CHAR_DEAD scplayer

						SET_PLAYER_CONTROL player1 OFF

					 	trig_jumper = 2

						GET_CHAR_HEADING scplayer d2_ply_heading

						IF d2_ply_heading > 45.0
						AND d2_ply_heading < 145.0

							d2_truck_direction = 0

						ELSE

							d2_truck_direction = 1
								
						ENDIF 	

					ENDIF
				ENDIF

			ENDIF

			// Player has arrived at jump point
			IF trig_jumper = 2
	
				bollocks:

				GOSUB doc2_set_camera
								
				IF NOT IS_CHAR_DEAD thedoc

					CLEAR_CHAR_TASKS_IMMEDIATELY thedoc

			   		TASK_PLAY_ANIM thedoc IDLE_CHAT PED 1.0 FALSE FALSE FALSE FALSE -1

				ENDIF

 				SET_FIXED_CAMERA_POSITION 2162.6200 1450.2083 25.1198 0.0 0.0 0.0 // Madd Dogg walking
				POINT_CAMERA_AT_POINT 2162.6200 1451.1921 24.9414 JUMP_CUT

				GENERATE_RANDOM_INT_IN_RANGE 0 3 d2_rnd
				
				SWITCH d2_rnd

					CASE 0

						CLEAR_MISSION_AUDIO 1

					 	LOAD_MISSION_AUDIO 1 SOUND_DOGG_AA

						WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
							WAIT 0
						ENDWHILE

						IF NOT IS_CHAR_DEAD thedoc

							START_CHAR_FACIAL_TALK thedoc 2000

						ENDIF

						PLAY_MISSION_AUDIO 1

 						PRINT_NOW ( DOGG_AA ) 3000 1 // Stay back!

					BREAK
				
					CASE 1

						CLEAR_MISSION_AUDIO 1

					 	LOAD_MISSION_AUDIO 1 SOUND_DOGG_AC

						WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
							WAIT 0
						ENDWHILE

						IF NOT IS_CHAR_DEAD thedoc

							START_CHAR_FACIAL_TALK thedoc 2000

						ENDIF

						PLAY_MISSION_AUDIO 1
				
						PRINT_NOW ( DOGG_AC ) 3000 1 // I warn you, I’ll jump!

					BREAK
				
					CASE 2

						CLEAR_MISSION_AUDIO 1

					 	LOAD_MISSION_AUDIO 1 SOUND_DOGG_AG

						WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
							WAIT 0
						ENDWHILE
					
						IF NOT IS_CHAR_DEAD thedoc

							START_CHAR_FACIAL_TALK thedoc 2000

						ENDIF

						PLAY_MISSION_AUDIO 1
										
						PRINT_NOW ( DOGG_AG ) 3000 1 // I need brandy! Give me brandy!

					BREAK

				ENDSWITCH

				IF NOT IS_CHAR_DEAD d2_driver

					REMOVE_CHAR_ELEGANTLY d2_driver
							 
				ENDIF

				MARK_MODEL_AS_NO_LONGER_NEEDED cellphone

				MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH

				WAIT 1000

				TIMERB = 0
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1	
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
						d2_when_he_jumps = 1
						GOTO d2_skip_the_cut_1
					ENDIF
				ENDWHILE

				IF NOT IS_CHAR_DEAD thedoc

					STOP_CHAR_FACIAL_TALK thedoc

				ENDIF

				TIMERB = 0
				WHILE TIMERB < 2000
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
						d2_when_he_jumps = 1
						GOTO d2_skip_the_cut_1
					ENDIF
				ENDWHILE

		   		SET_INTERPOLATION_PARAMETERS 0.0 2000

 				SET_FIXED_CAMERA_POSITION 2162.6200 1450.2083 25.1198 0.0 0.0 0.0 // Madd Dogg walking
				POINT_CAMERA_AT_POINT 2162.6200 1450.4993 24.1631 INTERPOLATION

				TIMERB = 0
				WHILE TIMERB < 2000
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
						d2_when_he_jumps = 1
						GOTO d2_skip_the_cut_1
					ENDIF
				ENDWHILE

		   		SET_INTERPOLATION_PARAMETERS 0.0 2000

				SET_FIXED_CAMERA_POSITION 2162.6200 1445.0768 13.6736 0.0 0.0 0.0 // Madd Dogg and van
				POINT_CAMERA_AT_POINT 2162.6200 1445.8154 14.7476 INTERPOLATION

				TIMERB = 0
				WHILE TIMERB < 1000
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
						d2_when_he_jumps = 1
						GOTO d2_skip_the_cut_1
					ENDIF
				ENDWHILE

				PRINT_NOW ( DOC2_05 ) 3000 1 // ~s~Catch ~b~Madd Dogg ~s~when he jumps

				TIMERB = 0
				WHILE TIMERB < 3000
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
						d2_when_he_jumps = 1
						GOTO d2_skip_the_cut_1
					ENDIF
				ENDWHILE

				d2_skip_the_cut_1:

				IF d2_when_he_jumps = 1

					PRINT_NOW ( DOC2_05 ) 3000 1 // ~s~Catch ~b~Madd Dogg ~s~when he jumps

				ENDIF

				CLEAR_PRINTS

				IF NOT IS_CHAR_DEAD thedoc

					PERFORM_SEQUENCE_TASK thedoc seq_doc

				ENDIF
				
				IF d2_truck_direction = 0

					IF NOT IS_CAR_DEAD pickup_truck
														  
						SET_CAR_COORDINATES pickup_truck 2163.8879 1444.0000 9.8203
	 
						SET_CAR_HEADING pickup_truck 90.0000 

					ENDIF

				ELSE

					IF NOT IS_CAR_DEAD pickup_truck

						SET_CAR_COORDINATES pickup_truck 2163.8879 1444.0000 9.8203
	 
						SET_CAR_HEADING pickup_truck 270.0000 

					ENDIF

				ENDIF

				APPLY_BRAKES_TO_PLAYERS_CAR player1 OFF

				IF DOES_BLIP_EXIST jump_point_blip

					SET_COORD_BLIP_APPEARANCE jump_point_blip COORD_BLIP_APPEARANCE_FRIEND
					 
				ENDIF

			 	GOSUB doc2_restore_camera

				trig_jumper = 3

				TIMERA = 0
			ENDIF

	ENDIF

	IF NOT IS_CAR_DEAD pickup_truck
	AND NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD thedoc

			// Camera change if in jumper radius
			IF trig_jumper = 3
				
				IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2149.4067 1431.4969 9.8203 100.0 100.0 100.0 FALSE
					
					PRINT_NOW ( DOC2_10 ) 4000 1 // ~r~You let Madd Dogg die

					GOTO mission_doc2_failed	

				ENDIF
					
				IF IS_CHAR_IN_CAR scplayer pickup_truck

					IF d2_in_truck_1 = 0

						CHANGE_BLIP_DISPLAY pickup_blip NEITHER
						CHANGE_BLIP_DISPLAY jump_point_blip BOTH

						d2_in_truck_1 = 1

					ENDIF

				ELSE

					IF d2_in_truck_1 = 1
																
						PRINT_NOW ( DOC2_19 ) 4000 1 // ~s~Get back in the truck!

						CHANGE_BLIP_DISPLAY pickup_blip BOTH
						CHANGE_BLIP_DISPLAY jump_point_blip NEITHER

						d2_in_truck_1 = 0
					
					ENDIF
				
				ENDIF

				IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer jump_X jump_Y jump_Z 12.0 12.0 12.0 FALSE
				AND doc2_message_go_back = 1

					TIMERB = 0

					RESTORE_CAMERA_JUMPCUT

					doc2_message_go_back = 2

				ENDIF

				IF TIMERB > 1000
				AND doc2_message_go_back = 2
					
					PRINT_NOW ( DOC2_11 ) 4000 1 // ~s~Go back and rescue Madd Dogg!

					doc2_message_go_back = 0

				ENDIF


				IF LOCATE_CHAR_ANY_MEANS_3D scplayer jump_X jump_Y jump_Z 12.0 12.0 12.0 FALSE

					doc2_message_go_back = 1

					IF TIMERA < 40000
						  						    
	 				 	SET_FIXED_CAMERA_POSITION 2163.7021 1435.8701 37.0031 0.0 0.0 0.0 // Fixed camera for jumping
					 	POINT_CAMERA_AT_POINT 2163.6990 1436.4468 36.1861 JUMP_CUT

					ENDIF
				
					IF TIMERA > 10000
					AND TIMERA < 11000
 
						IF d2_selected = 0

							GENERATE_RANDOM_INT_IN_RANGE 0 3 d2_rnd
						
							SWITCH d2_rnd 

								CASE 0

									CLEAR_MISSION_AUDIO 1

								 	LOAD_MISSION_AUDIO 1 SOUND_DOGG_BA

									WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
										WAIT 0
									ENDWHILE
									
									PLAY_MISSION_AUDIO 1

									PRINT_NOW ( DOGG_BA ) 4000 1 // That's it, this is the end!

								BREAK

								CASE 1

									CLEAR_MISSION_AUDIO 1

								 	LOAD_MISSION_AUDIO 1 SOUND_DOGG_BB

									WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
										WAIT 0
									ENDWHILE

									PLAY_MISSION_AUDIO 1
																			     
									PRINT_NOW ( DOGG_BB ) 4000 1 // It's time, I'm going to do it!

								BREAK

								CASE 2

									CLEAR_MISSION_AUDIO 1

								 	LOAD_MISSION_AUDIO 1 SOUND_DOGG_BC

									WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
										WAIT 0
									ENDWHILE

									PLAY_MISSION_AUDIO 1
																			     
									PRINT_NOW ( DOGG_BC ) 4000 1 // It's time, I'm going to do it!

								BREAK

							ENDSWITCH
						
							d2_selected = 1
								
						ENDIF

					ENDIF

					IF TIMERA > doc2_rnd

						SET_PLAYER_CONTROL player1 ON

						IF NOT IS_CHAR_DEAD thedoc

							CLEAR_CHAR_TASKS_IMMEDIATELY thedoc

							SET_CHAR_HEADING thedoc 177.7059 

						ENDIF						

						SET_TIME_SCALE 0.4
 						 
	 				  	SET_FIXED_CAMERA_POSITION 2163.2200 1454.5334 35.0248 0.0 0.0 0.0 // Top down view of Madd Dogg jumping

					  	POINT_CAMERA_AT_POINT 2163.2268 1454.0627 34.1425 JUMP_CUT
						
						WAIT 500

						SET_OBJECT_COLLISION pd_box1 FALSE

						SET_OBJECT_COLLISION pd_box2 FALSE

						IF NOT IS_CHAR_DEAD thedoc

							PERFORM_SEQUENCE_TASK thedoc seq_jump

						ENDIF
						
						TIMERB = 0

						trig_jumper = 4

						CLEAR_MISSION_AUDIO 3

						LOAD_MISSION_AUDIO 3 SOUND_CRATE_LANDING

						WHILE NOT TIMERB > 2000

							WAIT 0

							IF TIMERB > 500
							AND d2_falling = 0

								IF NOT IS_CHAR_DEAD thedoc

									CLEAR_CHAR_TASKS thedoc

								ENDIF

								d2_falling = 1
								
							ENDIF

							IF NOT IS_CHAR_DEAD thedoc

								GET_CHAR_COORDINATES thedoc x y z

								IF z < 12.0000  
								AND TIMERA > 10

									TIMERA = 0

									ADD_BLOOD x y z 0.0 0.0 2.0 40 thedoc

								ENDIF

							ENDIF

							IF TIMERB > 1000

								GOSUB doc2_set_camera

								SET_FIXED_CAMERA_POSITION 2172.0024 1441.7015 10.2111 0.0 0.0 0.0 // Madd Dogg hits the ground

								POINT_CAMERA_AT_POINT 2171.0759 1442.0026 10.4368 JUMP_CUT

								SET_PLAYER_CONTROL player1 OFF

							ENDIF

							IF NOT IS_CAR_DEAD pickup_truck
								IF NOT IS_CHAR_DEAD thedoc
									IF NOT trig_jumper = 5

										GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pickup_truck 0.0 -1.5 0.5 truck_X truck_Y truck_Z

										IF LOCATE_CHAR_ANY_MEANS_3D thedoc truck_X truck_Y truck_Z 1.3 1.3 1.0 FALSE

											IF HAS_MISSION_AUDIO_LOADED 3	 

												PLAY_MISSION_AUDIO 3

											ENDIF

											pd_box_Z = -0.1			
											
											BREAK_OBJECT pd_box2 TRUE
																					
											SET_CHAR_COLLISION thedoc FALSE

											SET_CHAR_HEALTH thedoc 100
											
											CLEAR_CHAR_TASKS_IMMEDIATELY thedoc

											TASK_PLAY_ANIM_NON_INTERRUPTABLE thedoc KO_shot_front PED 2.0 FALSE FALSE FALSE TRUE -1
																						  
											ATTACH_CHAR_TO_CAR thedoc pickup_truck 0.0 -2.0 1.0 FACING_FORWARD 0.0 WEAPONTYPE_UNARMED

											trig_jumper = 5	

											IF NOT IS_CHAR_DEAD thedoc
											AND NOT IS_CAR_DEAD pickup_truck

												CREATE_FX_SYSTEM explosion_barrel truck_X truck_Y truck_Z TRUE hit_van

											  	PLAY_AND_KILL_FX_SYSTEM hit_van

												SHAKE_PAD PAD1 500 200

												SET_CAR_DENSITY_MULTIPLIER 2.0

												SET_CHAR_HEALTH thedoc 100

												DISPLAY_HUD TRUE
												
												GOSUB spawn_medics

											ENDIF
																		
										ENDIF 

									ENDIF

								ENDIF

							ENDIF

						ENDWHILE

						TIMERA = 0

						IF HAS_MISSION_AUDIO_FINISHED 3

							CLEAR_MISSION_AUDIO 3	

						ENDIF

						SET_TIME_SCALE 1.0

						GOSUB doc2_restore_camera // Player misses Madd Dogg

						IF NOT IS_CHAR_DEAD thedoc
						AND NOT IS_CAR_DEAD pickup_truck

						  	DETACH_CHAR_FROM_CAR thedoc
			
						  	ATTACH_CHAR_TO_CAR thedoc pickup_truck 0.0 -2.5 1.0 FACING_FORWARD 0.0 WEAPONTYPE_UNARMED

						ENDIF

						IF trig_jumper = 4

							IF NOT IS_CHAR_DEAD thedoc

								GET_CHAR_COORDINATES thedoc x y z

								ADD_BLOOD x y z 0.0 0.0 10.0 100 thedoc

								TASK_DIE thedoc

							ENDIF

						ELSE

							IF NOT IS_CAR_DEAD pickup_truck

								SET_CAR_DENSITY_MULTIPLIER 2.0

								GET_CAR_HEALTH pickup_truck new_heal

								SET_CAR_PROOFS pickup_truck TRUE FALSE FALSE FALSE TRUE

							ENDIF

							old_heal = new_heal

							DISPLAY_ONSCREEN_COUNTER_WITH_STRING d2_instance_health COUNTER_DISPLAY_BAR DOC2_07 

						    CHANGE_BLIP_DISPLAY betty_blip BOTH 
						    					
						    CHANGE_BLIP_DISPLAY jump_point_blip NEITHER

						   	REPEAT 7 v

								IF NOT IS_CHAR_DEAD d2_ped[v]

									CLEAR_CHAR_TASKS_IMMEDIATELY d2_ped[v]
									
									GENERATE_RANDOM_INT_IN_RANGE 0 2 d2_rnd

									IF d2_rnd = 0

										GENERATE_RANDOM_INT_IN_RANGE 10 200 d2_rnd

										WAIT d2_rnd

										IF NOT IS_CHAR_DEAD d2_ped[v]

											PERFORM_SEQUENCE_TASK d2_ped[v] d2_seq_cheer

										ENDIF
									
									ELSE

										GENERATE_RANDOM_INT_IN_RANGE 10 200 d2_rnd

										WAIT d2_rnd

										IF NOT IS_CHAR_DEAD d2_ped[v]

											PERFORM_SEQUENCE_TASK d2_ped[v] d2_seq_chant

										ENDIF

									ENDIF

								ENDIF

							ENDREPEAT 

							REMOVE_ANIMATION ON_LOOKERS

							WAIT 1000

							PRINT_NOW ( DOC2_06 ) 5000 1 // ~s~Drive Madd Dogg to ~y~Hospital ~s~CAREFULLY. Each bump reduces his chance of survival.

							TIMERA = 0

						ENDIF

					ENDIF
					 
				ENDIF

			ENDIF

			IF NOT IS_CAR_DEAD pickup_truck
			AND NOT IS_CHAR_DEAD thedoc

				IF trig_jumper = 5

					IF TIMERA > 5000

						IF d2_playing = 2
						AND d2_conversation = 0

							$d2_print = &DOGG_CA // Time to take your sorry ass to rehab you drunken idiot.
							d2_audio = SOUND_DOGG_CA
							GOSUB d2_load_sample	

							d2_conversation = 1

						ENDIF
						IF d2_playing = 2
						AND d2_conversation = 1

							$d2_print = &DOGG_CB // Can I rap, dude?
							d2_audio = SOUND_DOGG_CB
							GOSUB d2_load_sample	

							d2_conversation = 2

						ENDIF
						IF d2_playing = 2
						AND d2_conversation = 2

							$d2_print = &DOGG_CC // Best there ever was.
							d2_audio = SOUND_DOGG_CC
							GOSUB d2_load_sample	

							d2_conversation = 3

						ENDIF
						IF d2_playing = 2
						AND d2_conversation = 3

							$d2_print = &DOGG_CD // Second only to god on high, homeboy.
							d2_audio = SOUND_DOGG_CD
							GOSUB d2_load_sample	

							d2_conversation = 4

						ENDIF
						IF d2_playing = 2
						AND d2_conversation = 4

							$d2_print = &DOGG_CE // And the name's Carl.
							d2_audio = SOUND_DOGG_CE
							GOSUB d2_load_sample	

							d2_conversation = 5

						ENDIF
									 

						IF d2_playing = 2
						AND d2_conversation = 5
							
							$d2_print = &DOGG_CF // Thanks, Carl... (pukes).
							d2_audio = SOUND_DOGG_CF
							GOSUB d2_load_sample	

							d2_conversation = 6

						ENDIF

					ENDIF

					IF NOT IS_CHAR_DEAD scplayer
					AND NOT IS_CAR_DEAD pickup_truck

						IF NOT IS_CHAR_IN_CAR scplayer pickup_truck

						    IF d2_in_truck = 0

								d2_conversation = 6

								PRINT_NOW ( DOC2_19 ) 4000 1 // ~s~Get back in the truck!

								d2_in_truck = 1

							ENDIF

						ELSE 

							IF d2_in_truck = 1

								PRINT_NOW ( DOC2_20 ) 4000 1 // ~s~Drive Madd Dogg to the Hospital!

								d2_in_truck = 0

							ENDIF

						ENDIF

					ENDIF

					old_heal = new_heal

					GET_CAR_HEALTH pickup_truck new_heal

					IF NOT IS_CHAR_DEAD thedoc
					AND NOT IS_CHAR_DEAD scplayer

						IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR thedoc scplayer
		
							IF new_heal = old_heal 
							
								new_heal = new_heal - 50

							ENDIF	

						ENDIF

					ENDIF

					IF new_heal < old_heal

						value_heal = old_heal - new_heal

						d2_instance_health = d2_instance_health - value_heal	

						IF d2_instance_health < 0 

							d2_instance_health = 0

							CLEAR_PRINTS

							GENERATE_RANDOM_INT_IN_RANGE 0 3 d2_rnd
					
							SWITCH d2_rnd

								CASE 0
																				
									$d2_print = &DOGG_EA // It's the end!, Huuurrgghh!
									d2_audio = SOUND_DOGG_EA
									GOSUB d2_load_sample

								BREAK

								CASE 1
																									
																				
									$d2_print = &DOGG_EB // You tried, man, you triiiuurrgghh (cough) -urgh...
									d2_audio = SOUND_DOGG_EB
									GOSUB d2_load_sample

								BREAK

								CASE 2
																																	
									$d2_print = &DOGG_EC // Momma, 's'at you? Uugh!
									d2_audio = SOUND_DOGG_EC
									GOSUB d2_load_sample

								BREAK

							ENDSWITCH

							IF NOT IS_CAR_DEAD pickup_truck

								trig_jumper = 6

							ELSE

								REMOVE_CHAR_ELEGANTLY thedoc

								GOTO mission_doc2_failed
				
							ENDIF

							// *******************************************************************************************************
							// *																									 *
							// *										 Test in Progress 											 *
							// *																									 *
							// *******************************************************************************************************

							IF NOT IS_CHAR_DEAD thedoc

								SET_CHAR_BLEEDING thedoc FALSE

							ENDIF

							TIMERB = 0

						ENDIF

						IF doc2_bleed = 0
						AND NOT trig_jumper = 6

							CLEAR_PRINTS

							d2_conversation = 4

							doc2_bleed = 1

							TIMERB = 0

							SET_CHAR_BLEEDING thedoc TRUE

							IF d2_seq_rnd = 0 
							OR d2_seq_rnd = 1

								GENERATE_RANDOM_INT_IN_RANGE 0 3 d2_rnd

							ELSE

								GENERATE_RANDOM_INT_IN_RANGE 0 2 d2_rnd

							ENDIF
															
							SWITCH d2_seq_rnd

								CASE 0

									IF d2_rnd = 0
																																																								
										$d2_print = &DOGG_DC // Momma, is that you?
										d2_audio = SOUND_DOGG_DC
										GOSUB d2_load_sample

									ENDIF
							
									IF d2_rnd = 1
																																																															
										$d2_print = &DOGG_DD // Brandy! More Brandy!
										d2_audio = SOUND_DOGG_DD
										GOSUB d2_load_sample

									ENDIF

									IF d2_rnd = 2

										$d2_print = &DOGG_DI // OG Loc? You're dead, man, dead!
										d2_audio = SOUND_DOGG_DI
										GOSUB d2_load_sample																																																													

									ENDIF

								BREAK

								CASE 1

									IF d2_rnd = 0
										
										$d2_print = &DOGG_DJ // Where's my bitches... my bitches..?
										d2_audio = SOUND_DOGG_DJ
										GOSUB d2_load_sample 

									ENDIF

									IF d2_rnd = 1
																																																														
										$d2_print = &DOGG_DE // I'm a ghetto superstar!
										d2_audio = SOUND_DOGG_DE
										GOSUB d2_load_sample

									ENDIF

									IF d2_rnd = 2
																																																																						
										$d2_print = &DOGG_DF // Loc! LOC! I'm gonna kill you!
										d2_audio = SOUND_DOGG_DF
										GOSUB d2_load_sample

									ENDIF

								BREAK

								CASE 2

									IF d2_rnd = 0
																																																																						
										$d2_print = &DOGG_DG // Wha-? Whera am I? Why's it so dark?
										d2_audio = SOUND_DOGG_DG
										GOSUB d2_load_sample

									ENDIF

									IF d2_rnd = 1
										
										IF d2_conversation = 6
																																																																							
											$d2_print = &DOGG_DH // I'm cold, Carl, so cold!
											d2_audio = SOUND_DOGG_DH
											GOSUB d2_load_sample

										ELSE

											$d2_print = &DOGG_DG // Wha-? Whera am I? Why's it so dark?
											d2_audio = SOUND_DOGG_DG
											GOSUB d2_load_sample

										ENDIF

									ENDIF

								BREAK

								CASE 3
																															
									IF d2_rnd = 0
																																									
										$d2_print = &DOGG_DA // Oh, man, I'm gonna die!
										d2_audio = SOUND_DOGG_DA
										GOSUB d2_load_sample

									ENDIF

									IF d2_rnd = 1

										IF d2_conversation = 6
																																																	
											$d2_print = &DOGG_DB // I'm slipping away, Carl!
											d2_audio = SOUND_DOGG_DB
											GOSUB d2_load_sample

										ELSE

											$d2_print = &DOGG_DA // Oh, man, I'm gonna die!
											d2_audio = SOUND_DOGG_DA
											GOSUB d2_load_sample


										ENDIF

									ENDIF

								BREAK

							ENDSWITCH

							d2_seq_rnd ++

							IF d2_seq_rnd = 4
								
								d2_seq_rnd = 0

							ENDIF

							d2_conversation = 6

						ENDIF

					ENDIF
	
					IF doc2_bleed = 1
					AND TIMERB > 4000

						SET_CHAR_BLEEDING thedoc FALSE

						doc2_bleed = 0

					ENDIF
 
					IF IS_CHAR_IN_CAR scplayer pickup_truck
	 
	 					CHANGE_BLIP_DISPLAY pickup_blip NEITHER
						CHANGE_BLIP_DISPLAY betty_blip BOTH

					ELSE

						CHANGE_BLIP_DISPLAY pickup_blip BOTH
						CHANGE_BLIP_DISPLAY betty_blip NEITHER 
					
					ENDIF

					IF NOT IS_CAR_DEAD pickup_truck
					IF IS_CHAR_IN_CAR scplayer pickup_truck

						IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1878.5455 2240.8198 9.8130 4.0 4.0 4.0 TRUE
						AND NOT IS_CAR_DEAD pickup_truck

							SET_PLAYER_CONTROL player1 OFF

							IF NOT IS_CHAR_DEAD thedoc

								fucked_in_love:

								APPLY_BRAKES_TO_PLAYERS_CAR player1 ON

								GOSUB doc2_set_camera

								GET_CHAR_HEADING scplayer d2_ply_heading

								IF d2_ply_heading > 0.0
								AND d2_ply_heading < 180.0

									d2_direction = 0

								ELSE

									d2_direction = 1
										
								ENDIF
									
								CLEAR_AREA 1878.5455 2240.8198 9.8130 10.0 TRUE 

								DETACH_CHAR_FROM_CAR thedoc

								IF NOT IS_CHAR_DEAD thedoc														   

									SET_CHAR_COORDINATES thedoc 1876.3347 2236.8997 10.1250  
																			 
									SET_CHAR_HEADING thedoc 242.5344

								ENDIF
									
								IF NOT IS_CAR_DEAD pickup_truck

									SET_CAR_COORDINATES pickup_truck 1878.5455 2240.8198 9.8130 

									IF d2_direction = 0

										SET_CAR_HEADING pickup_truck 90.0000 

									ELSE 
	
										SET_CAR_HEADING pickup_truck 270.0000 

									ENDIF

								ENDIF					   

			 					SET_FIXED_CAMERA_POSITION 1854.5211 2272.7498 11.9889 0.0 0.0 0.0 // Outside the clinic
								POINT_CAMERA_AT_POINT 1854.8296 2271.8000 11.9363 JUMP_CUT

								WAIT 1000

								TIMERB = 0
								WHILE TIMERB <  2000
									WAIT 0
									IF IS_BUTTON_PRESSED PAD1 CROSS
									OR IS_BUTTON_PRESSED PAD1 CIRCLE
										GOTO d2_skip_the_cut_2
									ENDIF
								ENDWHILE

  								LVAR_INT d2_seq_help_A 

								IF NOT IS_CHAR_DEAD thedoc

									OPEN_SEQUENCE_TASK d2_seq_help_A

										TASK_GO_TO_COORD_ANY_MEANS -1 1878.4933 2235.1082 10.1250 PEDMOVE_RUN -1

										TASK_TURN_CHAR_TO_FACE_CHAR -1 thedoc

										TASK_PAUSE -1 2000

										TASK_TOGGLE_DUCK -1 TRUE

									CLOSE_SEQUENCE_TASK d2_seq_help_A

  								ENDIF
  															     
				 				CREATE_CHAR PEDTYPE_CIVMALE laemt1 1875.3372 2235.2271 10.1250 d2_medic_C

								SET_CHAR_HEADING d2_medic_C 329.5542
								
								TASK_TOGGLE_DUCK d2_medic_C TRUE
																   
								CREATE_CHAR PEDTYPE_CIVMALE laemt1 1886.0066 2235.0667 10.1250 d2_medic_D

								SET_CHAR_HEADING d2_medic_D 84.1029

								PERFORM_SEQUENCE_TASK d2_medic_D d2_seq_help_A

								SET_FIXED_CAMERA_POSITION 1872.4949 2243.8435 14.0672 0.0 0.0 0.0 // Outside the clinic
								POINT_CAMERA_AT_POINT 1873.3213 2243.2822 14.1094 JUMP_CUT
								
								WAIT 100
																																																																												
								CLEAR_MISSION_AUDIO 1

							 	LOAD_MISSION_AUDIO 1 SOUND_DOGG_FA // You get yourself straightened out,

								WHILE NOT HAS_MISSION_AUDIO_LOADED 1
									WAIT 0
								ENDWHILE

								PLAY_MISSION_AUDIO 1

								PRINT_NOW ( DOGG_FA ) 3000 1 // You get yourself straightened out, 

								SET_INTERPOLATION_PARAMETERS 0.0 6000

								SET_FIXED_CAMERA_POSITION 1872.4949 2243.8435 14.0672 0.0 0.0 0.0 // Outside the 
								POINT_CAMERA_AT_POINT 1872.9706 2243.1431 13.5352 INTERPOLATION

								TIMERB = 0
								WHILE NOT HAS_MISSION_AUDIO_FINISHED 1	
									WAIT 0
									IF IS_BUTTON_PRESSED PAD1 CROSS
									OR IS_BUTTON_PRESSED PAD1 CIRCLE
										GOTO d2_skip_the_cut_2
									ENDIF
								ENDWHILE
																																																																												
								CLEAR_MISSION_AUDIO 1

							 	LOAD_MISSION_AUDIO 1 SOUND_DOGG_FB // then we can get you back in the studio.

								WHILE NOT HAS_MISSION_AUDIO_LOADED 1
									WAIT 0
								ENDWHILE

								PLAY_MISSION_AUDIO 1
							
								PRINT_NOW ( DOGG_FB ) 3000 1 // then we can get you back in the studio. 

								TIMERB = 0
								WHILE NOT HAS_MISSION_AUDIO_FINISHED 1	
									WAIT 0
									IF IS_BUTTON_PRESSED PAD1 CROSS
									OR IS_BUTTON_PRESSED PAD1 CIRCLE
										GOTO d2_skip_the_cut_2
									ENDIF
								ENDWHILE

								TIMERB = 0
								WHILE TIMERB < 1000
									WAIT 0
									IF IS_BUTTON_PRESSED PAD1 CROSS
									OR IS_BUTTON_PRESSED PAD1 CIRCLE
										GOTO d2_skip_the_cut_2
									ENDIF
								ENDWHILE
																																																																												
								CLEAR_MISSION_AUDIO 1

							 	LOAD_MISSION_AUDIO 1 SOUND_DOGG_FC // Carl.

								WHILE NOT HAS_MISSION_AUDIO_LOADED 1
									WAIT 0
								ENDWHILE

								PLAY_MISSION_AUDIO 1

								PRINT_NOW ( DOGG_FC ) 3000 1 // Madd Dogg : Carl.

								TIMERB = 0
								WHILE NOT HAS_MISSION_AUDIO_FINISHED 1	
									WAIT 0
									IF IS_BUTTON_PRESSED PAD1 CROSS
									OR IS_BUTTON_PRESSED PAD1 CIRCLE
										GOTO d2_skip_the_cut_2
									ENDIF
								ENDWHILE
																																																																																				
							  	CLEAR_MISSION_AUDIO 1

							 	LOAD_MISSION_AUDIO 1 SOUND_DOGG_FD // What?

								WHILE NOT HAS_MISSION_AUDIO_LOADED 1
									WAIT 0
								ENDWHILE

								PLAY_MISSION_AUDIO 1

								PRINT_NOW ( DOGG_FD ) 3000 1 // Carl : What.

								TIMERB = 0
								WHILE NOT HAS_MISSION_AUDIO_FINISHED 1	
									WAIT 0
									IF IS_BUTTON_PRESSED PAD1 CROSS
									OR IS_BUTTON_PRESSED PAD1 CIRCLE
										GOTO d2_skip_the_cut_2
									ENDIF
								ENDWHILE
								
								TIMERB = 0
								WHILE TIMERB < 1000
									WAIT 0
									IF IS_BUTTON_PRESSED PAD1 CROSS
									OR IS_BUTTON_PRESSED PAD1 CIRCLE
										GOTO d2_skip_the_cut_2
									ENDIF
								ENDWHILE
																																																																																				
							  	CLEAR_MISSION_AUDIO 1

							 	LOAD_MISSION_AUDIO 1 SOUND_DOGG_FE // When I'm clean, I'm gona need a new manager.

								WHILE NOT HAS_MISSION_AUDIO_LOADED 1
									WAIT 0
								ENDWHILE

								PLAY_MISSION_AUDIO 1

								PRINT_NOW ( DOGG_FE ) 3000 1 // When I’m clean, I’m gona need a new manager.n

								TIMERB = 0
								WHILE NOT HAS_MISSION_AUDIO_FINISHED 1	
									WAIT 0
									IF IS_BUTTON_PRESSED PAD1 CROSS
									OR IS_BUTTON_PRESSED PAD1 CIRCLE
										GOTO d2_skip_the_cut_2
									ENDIF
								ENDWHILE
																																																																																																				
							  	CLEAR_MISSION_AUDIO 1

							 	LOAD_MISSION_AUDIO 1 SOUND_DOGG_FF // Thought I might look you up.

								WHILE NOT HAS_MISSION_AUDIO_LOADED 1
									WAIT 0
								ENDWHILE

								PLAY_MISSION_AUDIO 1

								PRINT_NOW ( DOGG_FF ) 2000 1 // Thought I might look you up.

								TIMERB = 0
								WHILE NOT HAS_MISSION_AUDIO_FINISHED 1	
									WAIT 0
									IF IS_BUTTON_PRESSED PAD1 CROSS
									OR IS_BUTTON_PRESSED PAD1 CIRCLE
										GOTO d2_skip_the_cut_2
									ENDIF
								ENDWHILE

								TIMERB = 0
								WHILE TIMERB < 1000
									WAIT 0
									IF IS_BUTTON_PRESSED PAD1 CROSS
									OR IS_BUTTON_PRESSED PAD1 CIRCLE
										GOTO d2_skip_the_cut_2
									ENDIF
								ENDWHILE

								IF NOT IS_CAR_DEAD pickup_truck
								AND NOT IS_CHAR_DEAD scplayer
									IF d2_direction = 0

										TASK_CAR_DRIVE_TO_COORD scplayer pickup_truck 1853.3019 2239.5503 9.8203 10.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS

									ELSE

										TASK_CAR_DRIVE_TO_COORD scplayer pickup_truck 1902.1683 2238.1360 9.8203 10.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS

									ENDIF
								
								ENDIF
																																																																																																				
							  	CLEAR_MISSION_AUDIO 1

							 	LOAD_MISSION_AUDIO 1 SOUND_DOGG_FG // You do that, homie.

								WHILE NOT HAS_MISSION_AUDIO_LOADED 1
									WAIT 0
								ENDWHILE

								PLAY_MISSION_AUDIO 1

								PRINT_NOW ( DOGG_FG ) 3000 1 // You do that, homie.

								TIMERB = 0
								WHILE NOT HAS_MISSION_AUDIO_FINISHED 1	
									WAIT 0
									IF IS_BUTTON_PRESSED PAD1 CROSS
									OR IS_BUTTON_PRESSED PAD1 CIRCLE
										GOTO d2_skip_the_cut_2
									ENDIF
								ENDWHILE

								TIMERB = 0
								WHILE TIMERB < 1000
									WAIT 0
									IF IS_BUTTON_PRESSED PAD1 CROSS
									OR IS_BUTTON_PRESSED PAD1 CIRCLE
										GOTO d2_skip_the_cut_2
									ENDIF
								ENDWHILE

								d2_skip_the_cut_2:

								GOSUB kill_the_medics

								IF NOT IS_CAR_DEAD pickup_truck

									SET_CAR_STATUS pickup_truck STATUS_PLAYER 
									
									IF d2_direction = 1

										SET_CAR_COORDINATES pickup_truck 1913.2384 2240.4014 9.8203  
				
										SET_CAR_HEADING pickup_truck 270.0000

									ELSE

										SET_CAR_COORDINATES pickup_truck 1867.2996 2260.5737 9.8203  
				
										SET_CAR_HEADING pickup_truck 0.0000

									ENDIF

								ENDIF

								CLEAR_ONSCREEN_COUNTER doc2_heal

								GOSUB doc2_restore_camera
								 
								APPLY_BRAKES_TO_PLAYERS_CAR player1 OFF

							ENDIF

						 	DELETE_CHAR thedoc

							IF DOES_BLIP_EXIST betty_blip
								REMOVE_BLIP betty_blip
							ENDIF

							GOTO mission_doc2_passed

						ENDIF

					ENDIF

				ENDIF 
				ENDIF
			
			ENDIF

	ENDIF

	IF trig_jumper = 6
	AND TIMERB > 3000

		SET_CHAR_BLEEDING thedoc FALSE

		REMOVE_CHAR_ELEGANTLY thedoc

		GOTO mission_doc2_failed

	ENDIF
	
ENDWHILE

GOSUB doc2_restore_camera

GOTO mission_doc2_failed

 // **************************************** Mission doc2 failed ***********************

mission_doc2_failed:

	IF d2_mission_passed = 0	

		PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"

		IF trig_jumper = 5

			CLEAR_ONSCREEN_COUNTER d2_instance_health

		ENDIF

		IF IS_CAR_DEAD pickup_truck

			IF trig_jumper < 5

				PRINT_NOW ( DOC2_09 ) 4000 1 //	~r~You won't be able to catch Madd Dogg now

			ELSE

				PRINT_NOW (	DOC2_10 ) 4000 1 //	~r~You let Madd Dogg die

			ENDIF

			IF NOT IS_CHAR_DEAD thedoc

			//	TASK_DIE thedoc	

			ENDIF

		ENDIF

		IF IS_CHAR_DEAD thedoc
		OR d2_instance_health = 0

			PRINT_NOW ( DOC2_10 ) 4000 1 //	~r~You let Madd Dogg die

		ENDIF

	ENDIF

//GOSUB doc2_fade_in

RETURN  

// **************************************** mission doc2 passed ************************

mission_doc2_passed:

	d2_mission_passed = 1

	IF DOES_BLIP_EXIST doc_contact_blip

		REMOVE_BLIP doc_contact_blip

	ENDIF

	flag_doc_mission_counter ++ //Used to terminate this mission loop in the main script. These varibles will be set up in the main.sc

	REGISTER_MISSION_PASSED ( DOC_2 ) //Used in the stats
	 
	PLAYER_MADE_PROGRESS 1

	PLAY_MISSION_PASSED_TUNE 1

	PRINT_WITH_NUMBER_BIG ( M_PASSR ) 10 5000 1 //"Mission Passed!"

	AWARD_PLAYER_MISSION_RESPECT 10 //amount of respect

	CLEAR_WANTED_LEVEL player1

RETURN

spawn_medics:				  

	// --------------------------------------------------------------------------------------------

	LVAR_INT d2_seq_medic_a d2_seq_medic_b d2_sequence_task

	REQUEST_MODEL laemt1
	REQUEST_MODEL ambulan

	WHILE NOT HAS_MODEL_LOADED laemt1
	OR NOT HAS_MODEL_LOADED ambulan
		WAIT 0
	ENDWHILE

	// --------------------------------------------------------------------------------------------
     
	CREATE_CHAR PEDTYPE_CIVMALE laemt1 1853.9961 2264.5537 9.9245 d2_medic_A

	SET_CHAR_HEADING d2_medic_A 261.1915

	CREATE_CHAR PEDTYPE_CIVMALE laemt1 1855.1562 2264.2864 9.8885 d2_medic_B

	SET_CHAR_HEADING d2_medic_B 62.9217

	CREATE_CAR ambulan 1889.0458 2256.1279 9.8203 d2_ambu_A

	SET_CAR_HEADING d2_ambu_A 359.5048  

	CREATE_CAR ambulan 1896.4932 2256.0691 9.8203 d2_ambu_B

	SET_CAR_HEADING d2_ambu_B 2.7445  

	// --------------------------------------------------------------------------------------------

	OPEN_SEQUENCE_TASK d2_sequence_task
								  
		TASK_GO_TO_COORD_ANY_MEANS -1 1853.9961 2264.5537 9.9245 PEDMOVE_WALK -1

		TASK_ACHIEVE_HEADING -1 242.9220

		IF NOT IS_CHAR_DEAD d2_medic_B
			TASK_PLAY_ANIM -1 IDLE_chat PED 1.0 TRUE FALSE FALSE FALSE -1
		ENDIF

		SET_SEQUENCE_TO_REPEAT d2_sequence_task 1

	CLOSE_SEQUENCE_TASK d2_sequence_task

	PERFORM_SEQUENCE_TASK d2_medic_A d2_sequence_task

	// --------------------------------------------------------------------------------------------

	CLEAR_SEQUENCE_TASK d2_sequence_task

	OPEN_SEQUENCE_TASK d2_sequence_task
								  
		TASK_GO_TO_COORD_ANY_MEANS -1 1855.1562 2264.2864 9.8885 PEDMOVE_WALK -1

		TASK_ACHIEVE_HEADING -1 62.9217

		SET_SEQUENCE_TO_REPEAT d2_sequence_task 1

	CLOSE_SEQUENCE_TASK d2_sequence_task

	PERFORM_SEQUENCE_TASK d2_medic_B d2_sequence_task

	// --------------------------------------------------------------------------------------------

	MARK_MODEL_AS_NO_LONGER_NEEDED ambulan

	// --------------------------------------------------------------------------------------------

RETURN

kill_the_medics:

	IF NOT IS_CHAR_DEAD d2_medic_A

		REMOVE_CHAR_ELEGANTLY d2_medic_A

	ENDIF

	IF NOT IS_CHAR_DEAD d2_medic_B

		REMOVE_CHAR_ELEGANTLY d2_medic_B

	ENDIF

	IF NOT IS_CHAR_DEAD d2_medic_C

		REMOVE_CHAR_ELEGANTLY d2_medic_C

	ENDIF

	IF NOT IS_CHAR_DEAD d2_medic_D

		REMOVE_CHAR_ELEGANTLY d2_medic_D

	ENDIF

RETURN

// ********************************** mission cleanup ***********************************

mission_cleanup_doc2:

	flag_player_on_mission = 0

	IF NOT IS_CHAR_DEAD thedoc

		REMOVE_CHAR_ELEGANTLY thedoc

	ENDIF

	IF DOES_BLIP_EXIST jump_point_blip
		REMOVE_BLIP jump_point_blip
	ENDIF

	IF DOES_BLIP_EXIST betty_blip
		REMOVE_BLIP betty_blip 
	ENDIF

	IF DOES_BLIP_EXIST pickup_blip
		REMOVE_BLIP pickup_blip
	ENDIF

	CLEAR_ONSCREEN_COUNTER d2_instance_health	
						   
	KILL_FX_SYSTEM hit_van

	SWITCH_ROADS_BACK_TO_ORIGINAL 2090.9810 1388.5104 8.8203 2234.6863 1517.0773 10.8203

	SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2090.9810 1388.5104 8.8203 2234.6863 1517.0773 10.8203

	MARK_MODEL_AS_NO_LONGER_NEEDED walton
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYCON
	MARK_MODEL_AS_NO_LONGER_NEEDED vbmyelv
	MARK_MODEL_AS_NO_LONGER_NEEDED VWFYPRO
	MARK_MODEL_AS_NO_LONGER_NEEDED WMOTR1
	MARK_MODEL_AS_NO_LONGER_NEEDED laemt1
	MARK_MODEL_AS_NO_LONGER_NEEDED ambulan
	MARK_MODEL_AS_NO_LONGER_NEEDED HECK1
	MARK_MODEL_AS_NO_LONGER_NEEDED HECK2
	MARK_MODEL_AS_NO_LONGER_NEEDED WMOprea
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH
	MARK_MODEL_AS_NO_LONGER_NEEDED cellphone

	UNLOAD_SPECIAL_CHARACTER 1

	REMOVE_ANIMATION ON_LOOKERS

	RELEASE_WEATHER

	GET_GAME_TIMER timer_mobile_start // Used to reset the mobile phone timer so it doesn't ring immediately after the mission

	MISSION_HAS_FINISHED

RETURN

doc2_set_camera:

	CLEAR_PRINTS

	SWITCH_WIDESCREEN ON
	SET_PLAYER_CONTROL player1 OFF

RETURN

doc2_restore_camera:

	CLEAR_PRINTS

	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	RESTORE_CAMERA_JUMPCUT
 
RETURN

doc2_fade_out:

	DO_FADE 500 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

doc2_fade_in:

	DO_FADE 500 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN
 
doc2_keys:

	LVAR_INT doc2_dummy_car

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_W

		IF NOT IS_CHAR_DEAD scplayer
	
			TASK_PLAY_ANIM scplayer KO_shot_face PED 2.0 FALSE FALSE FALSE TRUE -1

		ENDIF

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_C

		IF NOT IS_CHAR_DEAD scplayer
	
			TASK_PLAY_ANIM scplayer KO_shot_front PED 2.0 FALSE FALSE FALSE TRUE -1

		ENDIF

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_E

		IF NOT IS_CHAR_DEAD scplayer
	
			TASK_PLAY_ANIM scplayer KO_shot_stom PED 2.0 FALSE FALSE FALSE TRUE -1

		ENDIF

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D
		
		IF NOT IS_CHAR_DEAD scplayer

			IF IS_CHAR_IN_ANY_CAR scplayer
				
				STORE_CAR_CHAR_IS_IN scplayer doc2_dummy_car
						
			 	SET_CAR_COORDINATES doc2_dummy_car 1893.9584 2240.1084 9.8203

				SET_CAR_HEADING doc2_dummy_car 77.6686 

			ENDIF
			
		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_F

		VIEW_INTEGER_VARIABLE doc2_rnd doc2_rnd

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_B

		IF NOT IS_CAR_DEAD pickup_truck

			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS pickup_truck 0.0 -1.5 0.5 truck_X truck_Y truck_Z

		ENDIF
 									
		CREATE_FX_SYSTEM explosion_barrel truck_X truck_Y truck_Z TRUE hit_van

		PLAY_FX_SYSTEM hit_van

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_K

		IF NOT IS_CHAR_DEAD scplayer

			TASK_DIE scplayer

		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_Q

		IF NOT IS_CHAR_DEAD scplayer

			GET_CHAR_COORDINATES scplayer x y z

			ADD_BLOOD x y z 0.0 0.0 2.0 40 scplayer

		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_H

		IF NOT IS_CHAR_DEAD scplayer

			ADD_ARMOUR_TO_CHAR scplayer 100
			INCREASE_PLAYER_MAX_HEALTH player1 100

		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_P

		IF NOT IS_CHAR_DEAD thedoc
		AND NOT IS_CAR_DEAD pickup_truck
		AND NOT trig_jumper = 5
											
			BREAK_OBJECT pd_box2 TRUE

			CLEAR_CHAR_TASKS_IMMEDIATELY thedoc

			TASK_PLAY_ANIM_NON_INTERRUPTABLE thedoc KO_shot_front PED 2.0 FALSE FALSE FALSE TRUE -1

			ATTACH_CHAR_TO_CAR thedoc pickup_truck 0.0 -2.5 1.0 FACING_FORWARD 0.0 WEAPONTYPE_UNARMED

			trig_jumper = 5	

			DISPLAY_ONSCREEN_COUNTER_WITH_STRING doc2_heal COUNTER_DISPLAY_BAR DOC2_07

		    CHANGE_BLIP_DISPLAY betty_blip BOTH 					
		    CHANGE_BLIP_DISPLAY jump_point_blip NEITHER

		ENDIF
			
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_Z

		FORCE_WEATHER_NOW WEATHER_RAINY_COUNTRYSIDE 
			
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_U
		
		
	ENDIF

RETURN

d2_load_sample:

	CLEAR_MISSION_AUDIO 1

	LOAD_MISSION_AUDIO 1 d2_audio

	d2_playing = 0

RETURN

d2_play_sample:

	IF HAS_MISSION_AUDIO_LOADED 1
	AND d2_playing = 0

		PLAY_MISSION_AUDIO 1

		PRINT_NOW ( $d2_print ) 10000 1 

		d2_playing = 1

	ENDIF

	IF HAS_MISSION_AUDIO_FINISHED 1
	AND d2_playing = 1

		CLEAR_MISSION_AUDIO 1	

		CLEAR_THIS_PRINT $d2_print

		d2_playing = 2

	ENDIF
	
RETURN

}



/*


{---------------------------------------DOC2--------------------------------------------}

[DOC2_01:DOC2]
~s~Use the ~b~Van ~s~around the corner to rescue Madd Dogg!

[DOC2_05:DOC2]
~s~Catch ~y~Madd Dogg ~s~when he jumps

[DOC2_06:DOC2]			   
~s~Drive Madd Dogg to ~y~Hospital ~s~CAREFULLY. Each bump reduces his chance of survival.

[DOC2_07:DOC2]			   
Madd Dogg

[DOC2_09:DOC2]	
~r~You won't be able to catch Madd Dogg now

[DOC2_10:DOC2]	
~r~You let Madd Dogg die

[DOC2_11:DOC2]	
~s~Go back and rescue Madd Dogg!

[DOC2_15:DOC2]
~s~Go and save ~y~Madd Dogg ~s~before he jumps!

[DOC2_19:DOC2]
~s~Get back in the truck!

[DOC2_20:DOC2]
~s~Drive Madd Dogg to the Hospital!

[DOC2_16:DOC2]
GUY : Hey! that's my truck

[DOC2_17:DOC2]
GUY : Hey, Where you going with my truck!



*/