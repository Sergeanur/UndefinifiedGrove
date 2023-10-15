MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* SWEET 1 *******************************************
// ********************************** TAG THE TURF *****************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME SWEET1

// Mission start stuff

GOSUB mission_start_sweet1

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_sweet1_failed
ENDIF

GOSUB mission_cleanup_sweet1

MISSION_END

{ 
// Variables for mission

VAR_INT spray_can_pickup sweet1_audio_chat[8] sweet1_audio_is_playing sweet1_index sweet1_chat_switch sweet1_cutscene_flag get_in_counter_swee1
LVAR_INT sweet_car_blip_removed jump_help6 tag_cutscene_skipped gang_follow_seq_2 sweet_car_frozen help_for_gangs played_mission_audio_sweet1
LVAR_INT sweet1_tag1 sweet1_tag2 sweet1_tag3 sweet1_tag4 added_sweet1_blip gang_hassle gang_follow_seq_1 sweet1_cell_index_end
LVAR_INT jump_help1 jump_help2 jump_help3 jump_help4 jump_help5 sweet1_flat1 sweet1_flat2 sweet_car_blip sweet_deleted help_for_tagging
LVAR_INT tag_percentage1 tag_percentage2 small_flats_gang stop_sweet_blip start_tagging	Return_Progress	played_last_line_of_audio
LVAR_INT sweet1_sequence_1 sweet1_sequence_2 sweet1_sequence_3 sweet_tagged_tag Returned_Status Returned_Progress blank_sweet
LVAR_INT sweet_in_tag_position1 sweet_in_tag_position2 sweet_in_tag_position3 tag_percentage3_tagged ReturnStatus
LVAR_FLOAT tag_postionX tag_postionY tag_postionZ sweet1X sweet1Y sweet1Z tag_area3aX tag_area3aY tag_area3bX tag_area3bY
LVAR_FLOAT tag_area1aX tag_area1aY tag_area1bX tag_area1bY	tag_area2aX tag_area2aY tag_area2bX tag_area2bY
LVAR_FLOAT tag_area4aX tag_area4aY tag_area4bX tag_area4bY Retured_anim_time
VAR_TEXT_LABEL $sweet1_chat[8]

CLEAR_CHAR_TASKS scplayer

// **************************************** Mission Start **********************************


sweet1_chat_switch:

SWITCH sweet1_chat_switch		   

	CONST_INT SWEET1_CHAT1 0
	CONST_INT SWEET1_CHAT2 1

	CASE SWEET1_CHAT1
		$sweet1_chat[0] = &SWE1_AA //Hey wait up.
		$sweet1_chat[1] = &SWE1_AB //Thought you'd hang with your brother?
		$sweet1_chat[2] = &SWE1_AC //Ease up, homie, it's been difficult.
		$sweet1_chat[3] = &SWE1_AD //You wanna drive?
		$sweet1_chat[4] = &SWE1_AE //Yeah, sure.

		sweet1_audio_chat[0] = SOUND_SWE1_AA //Hey wait up.
		sweet1_audio_chat[1] = SOUND_SWE1_AB //Thought you'd hang with your brother?
		sweet1_audio_chat[2] = SOUND_SWE1_AC //Ease up, homie, it's been difficult.
		sweet1_audio_chat[3] = SOUND_SWE1_AD //You wanna drive?
		sweet1_audio_chat[4] = SOUND_SWE1_AE //Yeah, sure.

		sweet1_cell_index_end = 4
	BREAK

	CASE SWEET1_CHAT2
		$sweet1_chat[0] = &SWE1_BN //Like riding a bike!
		$sweet1_chat[1] = &SWE1_BO //Yeah, it all comes back, man.
		$sweet1_chat[2] = &SWE1_BP //Yo, how you doing for greens?
		$sweet1_chat[3] = &SWE1_BQ //Check it, I’m lean, man.
		$sweet1_chat[4] = &SWE1_BX //CRASH took my big greens, left me with small change...
		$sweet1_chat[5] = &SWE1_BT //Here, get yourself a beer or something.
		$sweet1_chat[6] = &SWE1_BU //I’ll catch you later.

		sweet1_audio_chat[0] = SOUND_SWE1_BN //Like riding a bike!
		sweet1_audio_chat[1] = SOUND_SWE1_BO //Yeah, it all comes back, man.
		sweet1_audio_chat[2] = SOUND_SWE1_BP //Yo, how you doing for greens?
		sweet1_audio_chat[3] = SOUND_SWE1_BQ //Check it, I’m lean, man.
		sweet1_audio_chat[4] = SOUND_SWE1_BX //CRASH took my big greens, left me with small change...
		sweet1_audio_chat[5] = SOUND_SWE1_BT //Here, get yourself a beer or something.
		sweet1_audio_chat[6] = SOUND_SWE1_BU //I’ll catch you later.

		sweet1_cell_index_end = 6
	BREAK

	
//W {Whilst Driving – Minor damage to Sweet’s car} - versions		
/*
		
//W { SWEET gets first tag – Player has to get the one across the road } - versions		
SWE1_AP	//I’ll tag this block – follow me in the car.
SWE1_AQ	//I’ll get this one – follow me in thecar.
SWE1_AR	//Yo, watch my back while I tag this sucker.
		
//W { Go to next tag location in enemy turf } - versions		
SWE1_AS	//C’mon, let’s cruise into Ballas territory.
SWE1_AT	//Yo, let’s check out Ballas turf.
SWE1_AU	//C’mon, CJ, check out Ballas turf with me.
		
//W { Sweet drives off and leaves Player to it.} - versions		
SWE1_AV	//You mark up here, I’ll go off and do another ‘hood.
SWE1_AW	//You get these, I’ll go tag another block.
SWE1_AX	//You ok to do this while I go check around the block?
				
//W { Sweet picks up Player when done}* CD Player being chased by Ballas - versions		
SWE1_BG	//YO, CJ, GET IN!
SWE1_BH	//CARL, QUICK, GET IN!
SWE1_BJ	//CARL, GET YOUR ASS IN THIS CAR!
		
//W { Sweet picks up Player when done}* CD Player not being chased. - versions		
SWE1_BK	//Hey, CJ, you done? Hop in!
SWE1_BL	//You done here? – Get in!
SWE1_BM	//Yo, CJ, get in!
*/		

ENDSWITCH

RETURN

GOTO dont_create_this_shit
CREATE_PICKUP_WITH_AMMO SPRAYCAN PICKUP_ON_STREET_SLOW 1000 2493.52 -1700.83 14.27 spray_can_pickup
dont_create_this_shit:

mission_start_sweet1:

REGISTER_MISSION_GIVEN

flag_player_on_mission = 1

WAIT 0

LOAD_MISSION_TEXT SWEET1 

// ****************************************START OF CUTSCENE********************************

// fades the screen in 


CLEAR_AREA 2527.9 -1667.7 14.1 10.0 TRUE

LOAD_CUTSCENE SWEET1A
 
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


// ****************************************END OF CUTSCENE**********************************


//VIEW_INTEGER_VARIABLE tag_percentage tag_percentage

jump_help1 = 0
jump_help2 = 0
jump_help3 = 0
jump_help4 = 0
jump_help5 = 0
jump_help6 = 0
start_tagging = 0
Returned_Progress = 0
sweet_tagged_tag = 0
small_flats_gang = 0
sweet_car_blip_removed = 0
sweet_deleted = 0
gang_hassle = 0
help_for_tagging = 0
sweet_in_tag_position1 = 0
sweet_in_tag_position2 = 0
sweet_in_tag_position3 = 0
tag_percentage3_tagged = 0
tag_cutscene_skipped = 0
sweet_car_frozen = 0
help_for_gangs = 0
played_mission_audio_sweet1 = 0
get_in_counter_swee1 = 0

tag_area1aX = 2038.7583 //LARGE AREA1
tag_area1aY = -1624.4189 	
tag_area1bX = 2074.5027 
tag_area1bY	= -1662.7673

tag_area2aX = 2345.68 //LARGE AREA2 
tag_area2aY = -1515.79	
tag_area2bX = 2423.70 
tag_area2bY	= -1393.28

tag_area3aX = 2401.60 //ROOF TOP TAG     
tag_area3aY = -1550.80 
tag_area3bX = 2396.33  
tag_area3bY	= -1555.12

tag_area4aX = 2100.9722 //BRIDGE TAG
tag_area4aY = -1646.6226 
tag_area4bX = 2105.2639 
tag_area4bY	= -1656.7118

	SET_ZONE_GANG_STRENGTH IWD1 GANG_FLAT 10 
	SET_ZONE_GANG_STRENGTH IWD2 GANG_FLAT 10
	SET_ZONE_GANG_STRENGTH IWD3a GANG_FLAT 10
	SET_ZONE_GANG_STRENGTH IWD3b GANG_FLAT 10
	SET_ZONE_GANG_STRENGTH IWD4 GANG_FLAT 10
	SET_ZONE_GANG_STRENGTH IWD5 GANG_FLAT 10

	SET_ZONE_GANG_STRENGTH ELS1a GANG_FLAT 10
	SET_ZONE_GANG_STRENGTH ELS1b GANG_FLAT 10
	SET_ZONE_GANG_STRENGTH ELS2	 GANG_FLAT 10
	SET_ZONE_GANG_STRENGTH ELS3a GANG_FLAT 10
	SET_ZONE_GANG_STRENGTH ELS3b GANG_FLAT 10
	SET_ZONE_GANG_STRENGTH ELS4	 GANG_FLAT 10

LOAD_SPECIAL_CHARACTER 1 sweet
LOAD_SPECIAL_CHARACTER 10 smoke
REQUEST_MODEL SPRAYCAN
REQUEST_MODEL GREENWOO
REQUEST_MODEL ballas1
REQUEST_MODEL ballas2
 
LOAD_ALL_MODELS_NOW

WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
OR NOT HAS_SPECIAL_CHARACTER_LOADED 10
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED SPRAYCAN
OR NOT HAS_MODEL_LOADED GREENWOO
OR NOT HAS_MODEL_LOADED ballas1
OR NOT HAS_MODEL_LOADED	ballas2
	WAIT 0

ENDWHILE

sweet1_index = 0
sweet1_audio_is_playing = 0
sweet1_cutscene_flag = 0
sweet1_chat_switch = SWEET1_CHAT1
GOSUB sweet1_chat_switch

SET_CHAR_COORDINATES scplayer 2516.53 -1671.37 12.88  
SET_CHAR_HEADING scplayer 61.28
GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_SPRAYCAN 30000
SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_SPRAYCAN

CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 2518.07 -1668.82 12.85 sweet
SET_ANIM_GROUP_FOR_CHAR sweet gang2
SET_CHAR_HEADING sweet 103.76
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY blank_sweet
SET_CHAR_DECISION_MAKER sweet blank_sweet
SET_CHAR_NEVER_TARGETTED sweet TRUE
GIVE_WEAPON_TO_CHAR sweet WEAPONTYPE_SPRAYCAN 30000
SET_CURRENT_CHAR_WEAPON sweet WEAPONTYPE_SPRAYCAN 
SET_CHAR_SUFFERS_CRITICAL_HITS sweet FALSE
SET_CHAR_HEALTH sweet 500
SET_CHAR_MAX_HEALTH	sweet 500
SET_CHAR_CANT_BE_DRAGGED_OUT sweet TRUE
SET_CHAR_STAY_IN_CAR_WHEN_JACKED sweet TRUE
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR sweet FALSE

SWITCH_CAR_GENERATOR gen_car7 0
CLEAR_AREA 2518.07 -1668.82 12.85 10.0 TRUE

CUSTOM_PLATE_FOR_NEXT_CAR GREENWOO &GROVE4L_ 
CREATE_CAR GREENWOO 2508.16 -1666.47 13.0 sweet_car
SET_CAN_BURST_CAR_TYRES sweet_car FALSE
CHANGE_CAR_COLOUR sweet_car 59 34
SET_CAR_HEADING sweet_car 16.0
ADD_BLIP_FOR_CAR sweet_car sweet_car_blip
SET_BLIP_AS_FRIENDLY sweet_car_blip TRUE
SET_RADIO_CHANNEL RS_DISCO_FUNK_SOUL

CREATE_CHAR PEDTYPE_MISSION1 SPECIAL10 2520.10 -1669.58 13.10 big_smoke
SET_ANIM_GROUP_FOR_CHAR big_smoke fatman 
SET_CHAR_HEADING big_smoke 173.99
TASK_GO_STRAIGHT_TO_COORD big_smoke 2518.5093 -1676.1243 13.3855 PEDMOVE_WALK 10000

SET_TAG_STATUS_IN_AREA tag_area1aX tag_area1aY tag_area1bX tag_area1bY FALSE
SET_TAG_STATUS_IN_AREA tag_area2aX tag_area2aY tag_area2bX tag_area2bY FALSE
SET_TAG_STATUS_IN_AREA tag_area3aX tag_area3aY tag_area3bX tag_area3bY FALSE
SET_TAG_STATUS_IN_AREA tag_area4aX tag_area4aY tag_area4bX tag_area4bY FALSE
GET_PERCENTAGE_TAGGED_IN_AREA tag_area1aX tag_area1aY tag_area1bX tag_area1bY tag_percentage


SWITCH_WIDESCREEN ON //Short cut of sweet and player
SET_PLAYER_CONTROL player1 OFF
CLEAR_AREA 2518.07 -1668.82 12.85 40.0 TRUE
SET_NEAR_CLIP 0.2

SET_FIXED_CAMERA_POSITION 2512.0100 -1671.2073 14.2722 0.0 0.0 0.0 //shot of Sweet & Smoke
POINT_CAMERA_AT_POINT 2512.9377 -1670.8423 14.1948 JUMP_CUT

LOAD_SCENE 2515.42 -1671.19 14.46

TASK_GO_STRAIGHT_TO_COORD sweet 2513.8118 -1669.5271 12.5348 PEDMOVE_WALK 10000
TASK_GO_STRAIGHT_TO_COORD scplayer 2512.64 -1671.07 12.50 PEDMOVE_WALK 10000

DO_FADE 500 FADE_IN

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

tag_cutscene_skipped = 0
SKIP_CUTSCENE_START

WHILE NOT sweet1_index = 1 //Hey wait up.
	WAIT 0

		GOSUB load_and_play_audio_sweet1
		IF sweet1_cutscene_flag = 0
			IF sweet1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD sweet
					CLEAR_LOOK_AT scplayer
					CLEAR_LOOK_AT sweet
					TASK_LOOK_AT_CHAR scplayer sweet 8000
					TASK_LOOK_AT_CHAR sweet scplayer 8000
					//START_CHAR_FACIAL_TALK sweet 5000
				ENDIF
				sweet1_cutscene_flag = 1
			ENDIF
		ENDIF

ENDWHILE
IF NOT IS_CHAR_DEAD sweet
	//STOP_CHAR_FACIAL_TALK sweet
	TASK_TURN_CHAR_TO_FACE_CHAR scplayer sweet
ENDIF

CLEAR_AREA 2512.26 -1667.83 12.56 30.0 TRUE
CAMERA_RESET_NEW_SCRIPTABLES
CAMERA_PERSIST_TRACK TRUE                   
CAMERA_PERSIST_POS TRUE                       
CAMERA_SET_VECTOR_MOVE 2523.4170 -1664.9910 15.1254 2522.9614 -1667.4600 15.2254 13000 TRUE
CAMERA_SET_VECTOR_TRACK 2513.6946 -1670.6090 13.8556 2514.2966 -1669.3425 13.9372 13000 TRUE

WHILE NOT sweet1_index = 2 //Thought you'd hang with your brother?
	WAIT 0

		GOSUB load_and_play_audio_sweet1
		IF sweet1_cutscene_flag = 0
			IF sweet1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD sweet
					//START_CHAR_FACIAL_TALK scplayer 5000
					//SET_FIXED_CAMERA_POSITION 2516.1646 -1670.9469 12.9003 0.0 0.0 0.0 //shot of Sweet
					//POINT_CAMERA_AT_POINT 2515.2185 -1670.7294 13.1402 JUMP_CUT
				ENDIF
				sweet1_cutscene_flag = 1
			ENDIF
		ENDIF

ENDWHILE
IF NOT IS_CHAR_DEAD sweet
	//STOP_CHAR_FACIAL_TALK scplayer
ENDIF


WHILE NOT sweet1_index = 3 //Ease up, homie, it's been difficult.
	WAIT 0

		GOSUB load_and_play_audio_sweet1
		IF sweet1_cutscene_flag = 0
			IF sweet1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD sweet
					//START_CHAR_FACIAL_TALK sweet 5000
					TASK_PLAY_ANIM sweet IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE 6000
				ENDIF
				sweet1_cutscene_flag = 1
			ENDIF
		ENDIF

ENDWHILE
IF NOT IS_CHAR_DEAD sweet
	//STOP_CHAR_FACIAL_TALK sweet
ENDIF

WHILE NOT sweet1_index = 4 //You wanna drive?
	WAIT 0

		GOSUB load_and_play_audio_sweet1
		IF sweet1_cutscene_flag = 0
			IF sweet1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD sweet
					//START_CHAR_FACIAL_TALK sweet 5000
				ENDIF
				sweet1_cutscene_flag = 1
			ENDIF
		ENDIF

ENDWHILE
IF NOT IS_CHAR_DEAD sweet
	//STOP_CHAR_FACIAL_TALK sweet
ENDIF

WHILE NOT sweet1_index = 5 //Yeah, sure.
	WAIT 0

		GOSUB load_and_play_audio_sweet1
		IF sweet1_cutscene_flag = 0
			IF sweet1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD sweet
					IF NOT IS_CAR_DEAD sweet_car
						//START_CHAR_FACIAL_TALK sweet 10000
						SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
						TASK_GO_STRAIGHT_TO_COORD scplayer 2510.2705 -1669.8031 12.4092 PEDMOVE_WALK 10000
						SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
						TASK_ENTER_CAR_AS_PASSENGER sweet sweet_car 20000 0
					ENDIF
				ENDIF
				sweet1_cutscene_flag = 1
			ENDIF
		ENDIF

ENDWHILE

WAIT 1000

tag_cutscene_skipped = 1
SKIP_CUTSCENE_END

CLEAR_MISSION_AUDIO 1
CLEAR_MISSION_AUDIO 2
//IF NOT IS_CHAR_DEAD sweet
	//STOP_CHAR_FACIAL_TALK sweet
	//STOP_CHAR_FACIAL_TALK scplayer
//ENDIF
DELETE_CHAR big_smoke
UNLOAD_SPECIAL_CHARACTER 10

ADD_BLIP_FOR_COORD 2089.01 -1649.08 12.8 stop_sweet_blip
REMOVE_BLIP	stop_sweet_blip

IF tag_cutscene_skipped = 0
	SET_CHAR_COORDINATES scplayer 2511.68 -1670.33 12.46   
	SET_CHAR_HEADING scplayer 62.36
	CLEAR_LOOK_AT scplayer
	//SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
	IF NOT IS_CHAR_DEAD sweet
		IF NOT IS_CAR_DEAD sweet_car
			CLEAR_AREA 2510.05 -1666.61 12.57 1.0 TRUE
			SET_CHAR_COORDINATES sweet 2510.05 -1666.61 12.57    
			SET_CHAR_HEADING sweet 53.89
			CLEAR_LOOK_AT sweet
			CLEAR_CHAR_TASKS_IMMEDIATELY sweet
			SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
			TASK_ENTER_CAR_AS_PASSENGER sweet sweet_car 20000 0
		ENDIF
	ENDIF
ENDIF

IF NOT IS_CHAR_DEAD sweet
	CLEAR_AREA 2510.05 -1666.61 12.57 1.0 TRUE
	SET_CHAR_COORDINATES sweet 2510.05 -1666.61 12.57    
	SET_CHAR_HEADING sweet 53.89
ENDIF
CLEAR_CHAR_TASKS scplayer
CAMERA_RESET_NEW_SCRIPTABLES
SET_CAMERA_BEHIND_PLAYER
SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL player1 ON
RESTORE_CAMERA_JUMPCUT


PRINT_NOW ( SWE1_A ) 7000 1 // Get in Sweets Car.

added_sweet1_blip = 0

blob_flag = 0

IF IS_CAR_DEAD sweet_car
	PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
	GOTO mission_sweet1_failed
ENDIF

//GOTO skip_to_final_sweet_cut

WHILE NOT LOCATE_CAR_3D sweet_car 2089.01 -1649.08 12.54 4.0 4.0 4.0 blob_flag
OR NOT IS_CHAR_SITTING_IN_CAR scplayer sweet_car
OR NOT IS_VEHICLE_ON_ALL_WHEELS sweet_car
	WAIT 0


	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		GOTO mission_sweet1_passed
	ENDIF

	IF IS_CHAR_DEAD sweet 
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed
	ENDIF

	IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SPRAYCAN
		PRINT_NOW (SWE1SPF) 10000 1 //~r~You need a spraycan!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ELSE
		IF IS_CHAR_IN_CAR scplayer sweet_car
			IF sweet_car_blip_removed = 0
				REMOVE_BLIP sweet_car_blip
				REMOVE_BLIP	stop_sweet_blip
				ADD_BLIP_FOR_COORD 2089.01 -1649.08 12.8 stop_sweet_blip
				PRINT_NOW ( HOOD3_A ) 5000 1 // spray over the rival gang tags with you're own tag. 
				blob_flag = 1
				sweet_car_blip_removed = 1
			ENDIF
		ELSE
			IF sweet_car_blip_removed = 1
				REMOVE_BLIP sweet_car_blip
				REMOVE_BLIP	stop_sweet_blip
				ADD_BLIP_FOR_CAR sweet_car sweet_car_blip
				SET_BLIP_AS_FRIENDLY sweet_car_blip TRUE
				GOSUB get_back_in_the_car
				PRINT_NOW ( SWE1_S ) 5000 1 //~s~Get back in ~b~Sweets car!
				blob_flag = 0
				sweet_car_blip_removed = 0
			ENDIF
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD sweet 
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed
	ENDIF

	IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SPRAYCAN
		PRINT_NOW (SWE1SPF) 10000 1 //~r~You need a spraycan!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ENDIF

ENDWHILE

SET_PLAYER_CONTROL player1 OFF

SET_FIXED_CAMERA_POSITION 2082.5332 -1650.2444 14.8007 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT 2083.5154 -1650.2024 14.6181 JUMP_CUT

//TAG CUTSCENE***************************************************************************************************************************

REQUEST_ANIMATION GRAFFITI  // graffiti_Chkout

GET_NEAREST_TAG_POSITION 2100.84 -1648.71 12.42 tag_postionX tag_postionY tag_postionZ

IF NOT IS_CAR_DEAD sweet_car
	OPEN_SEQUENCE_TASK sweet1_sequence_1 //TAG1
		TASK_LEAVE_CAR -1 sweet_car
		TASK_GO_STRAIGHT_TO_COORD -1 2100.48 -1649.14 12.47 PEDMOVE_WALK 20000
		TASK_SHOOT_AT_COORD -1 tag_postionX tag_postionY tag_postionZ 15000
	CLOSE_SEQUENCE_TASK sweet1_sequence_1
ENDIF

SET_TAG_STATUS_IN_AREA tag_area1aX tag_area1aY tag_area1bX tag_area1bY FALSE
SET_TAG_STATUS_IN_AREA tag_area4aX tag_area4aY tag_area4bX tag_area4bY FALSE

GET_PERCENTAGE_TAGGED_IN_AREA tag_area1aX tag_area1aY tag_area1bX tag_area1bY tag_percentage
SET_CHAR_ACCURACY sweet 90
SET_CHAR_SHOOT_RATE sweet 100
sweet_tagged_tag = 0

SWITCH_WIDESCREEN ON

IF NOT IS_CHAR_DEAD	sweet
	IF NOT IS_CAR_DEAD sweet_car
		TASK_LEAVE_CAR sweet sweet_car
	ENDIF
ENDIF

WAIT 600

IF NOT IS_CAR_DEAD sweet_car
	TASK_LEAVE_CAR scplayer sweet_car
ENDIF

WAIT 500

DO_FADE 1000 FADE_OUT 

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

WHILE NOT HAS_ANIMATION_LOADED GRAFFITI
	WAIT 0
ENDWHILE

LOAD_MISSION_AUDIO 1 SOUND_SWE1_CA //There's another two Balla tags in this hood.
LOAD_MISSION_AUDIO 2 SOUND_SWE1_AR //Yo, watch my back while I tag this sucker.

IF IS_CHAR_IN_ANY_CAR scplayer
	WARP_CHAR_FROM_CAR_TO_COORD scplayer 2094.70 -1652.05 12.65  
ELSE
	SET_CHAR_COORDINATES scplayer 2094.70 -1652.05 12.65
ENDIF
		 
SET_CHAR_HEADING scplayer 302.0
CLEAR_CHAR_TASKS scplayer
TASK_GO_STRAIGHT_TO_COORD scplayer 2099.12 -1651.26 12.63 PEDMOVE_WALK 20000 

IF NOT IS_CHAR_DEAD	sweet
	IF IS_CHAR_IN_ANY_CAR sweet
		WARP_CHAR_FROM_CAR_TO_COORD sweet 2095.80 -1649.86 12.70  
	ELSE
		SET_CHAR_COORDINATES sweet 2095.80 -1649.86 12.70
	ENDIF 
	SET_CHAR_HEADING sweet 277.0
	CLEAR_CHAR_TASKS sweet
	PERFORM_SEQUENCE_TASK sweet sweet1_sequence_1
	CLEAR_SEQUENCE_TASK sweet1_sequence_1
ENDIF

SET_FIXED_CAMERA_POSITION 2095.5315 -1659.4850 13.3156 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT 2095.7422 -1658.5193 13.4672 JUMP_CUT

CLEAR_AREA 2094.20 -1652.05 12.65 10.0 TRUE

WAIT 500

DO_FADE 500 FADE_IN

IF NOT IS_CHAR_DEAD sweet
	CLEAR_LOOK_AT scplayer
	TASK_LOOK_AT_CHAR scplayer sweet 4500
ENDIF

tag_cutscene_skipped = 0

SKIP_CUTSCENE_START

WAIT 2000

SET_FIXED_CAMERA_POSITION 2102.1096 -1647.6721 13.1493 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT 2101.3691 -1648.3153 13.3438 JUMP_CUT



	WHILE NOT HAS_MISSION_AUDIO_LOADED 2
		WAIT 0
	
		IF IS_CHAR_DEAD	sweet
			PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
			GOTO mission_sweet1_failed
		ENDIF

		IF IS_CAR_DEAD sweet_car
			PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
			GOTO mission_sweet1_failed
		ENDIF

	ENDWHILE

	PLAY_MISSION_AUDIO 2
	PRINT_NOW (SWE1_AR) 10000 1 //Yo, watch my back while I tag this sucker
	IF NOT IS_CHAR_DEAD sweet
		START_CHAR_FACIAL_TALK sweet 3000
	ENDIF

	WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
		WAIT 0

		IF IS_CHAR_DEAD	sweet
			PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
			GOTO mission_sweet1_failed
		ENDIF

		IF IS_CAR_DEAD sweet_car
			PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
			GOTO mission_sweet1_failed
		ENDIF

	ENDWHILE
	CLEAR_PRINTS

	IF NOT IS_CHAR_DEAD sweet
		STOP_CHAR_FACIAL_TALK sweet
	ENDIF

IF NOT IS_CHAR_DEAD sweet
	GET_SEQUENCE_PROGRESS sweet Return_Progress
ENDIF

WHILE NOT Return_Progress = 2
	WAIT 0

	IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SPRAYCAN
		PRINT_NOW (SWE1SPF) 10000 1 //~r~You need a spraycan!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CHAR_DEAD	sweet
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ENDIF
	GET_SEQUENCE_PROGRESS sweet Return_Progress

ENDWHILE

//SET_FIXED_CAMERA_POSITION 2097.6716 -1651.5294 14.3508 0.0 0.0 0.0 
//POINT_CAMERA_AT_POINT 2098.4343 -1650.9133 14.1545 JUMP_CUT

CAMERA_RESET_NEW_SCRIPTABLES
CAMERA_PERSIST_TRACK TRUE                   
CAMERA_PERSIST_POS TRUE                       
CAMERA_SET_VECTOR_MOVE 2097.2688 -1648.2150 14.4973 2098.3191 -1650.5167 14.3662 10000 TRUE
CAMERA_SET_VECTOR_TRACK 2101.6265 -1649.5768 13.7631 2101.8901 -1647.9247 13.7316 10000 TRUE

PRINT_HELP_FOREVER SWE1_D  
help_for_tagging = 1
TIMERA = 0


	WHILE NOT tag_percentage = 100 //TAG1
		WAIT 0
		
		GET_PERCENTAGE_TAGGED_IN_AREA tag_area4aX tag_area4aY tag_area4bX tag_area4bY tag_percentage
		
		IF IS_CHAR_DEAD	sweet
			PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
			GOTO mission_sweet1_failed
		ENDIF

		IF IS_CAR_DEAD sweet_car
			PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
			GOTO mission_sweet1_failed
		ENDIF
		
		IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SPRAYCAN
			PRINT_NOW (SWE1SPF) 10000 1 //~r~You need a spraycan!
			GOTO mission_sweet1_failed
		ENDIF

		IF help_for_tagging = 1
			IF TIMERA > 5000
				PRINT_HELP SWE1_F //keep the ~o~ button pressed until the tag is completely sprayed over.
				TIMERA = 0
				help_for_tagging = 2
			ELSE
				PRINT_HELP_FOREVER SWE1_D  
			ENDIF
		ENDIF

	ENDWHILE

	WAIT 1000
	IF NOT IS_CHAR_DEAD sweet
		SET_CHAR_HEADING sweet 280.0
		TASK_PLAY_ANIM sweet GRAFFITI_CHKOUT GRAFFITI 4.0 FALSE FALSE FALSE FALSE -1 
		GET_SCRIPT_TASK_STATUS sweet TASK_PLAY_ANIM ReturnStatus
	ENDIF
	
	WHILE NOT ReturnStatus = FINISHED_TASK
		WAIT 0

		IF IS_CHAR_DEAD	sweet
			PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
			GOTO mission_sweet1_failed
		ENDIF
		GET_SCRIPT_TASK_STATUS sweet TASK_PLAY_ANIM ReturnStatus

		IF IS_CAR_DEAD sweet_car
			PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
			GOTO mission_sweet1_failed
		ENDIF
			
		IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SPRAYCAN
			PRINT_NOW (SWE1SPF) 10000 1 //~r~You need a spraycan!
			GOTO mission_sweet1_failed
		ENDIF

		IF help_for_tagging = 1
			IF TIMERA > 5000
				PRINT_HELP SWE1_F //keep the ~o~ button pressed until the tag is completely sprayed over.
				TIMERA = 0
				help_for_tagging = 2
			ELSE
				PRINT_HELP_FOREVER SWE1_D  
			ENDIF
		ENDIF

	ENDWHILE
	
	CAMERA_RESET_NEW_SCRIPTABLES
	CLEAR_HELP

	IF NOT IS_CHAR_DEAD sweet
		TASK_GO_STRAIGHT_TO_COORD sweet 2099.10 -1650.02 12.57 PEDMOVE_WALK 20000
	ENDIF
	IF NOT IS_CHAR_DEAD sweet
		CLEAR_LOOK_AT sweet
		CLEAR_LOOK_AT scplayer
		TASK_LOOK_AT_CHAR scplayer sweet 4500
		TASK_LOOK_AT_CHAR sweet scplayer 4500
	ENDIF

	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		WAIT 0
	
		IF IS_CHAR_DEAD	sweet
			PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
			GOTO mission_sweet1_failed
		ENDIF

		IF IS_CAR_DEAD sweet_car
			PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
			GOTO mission_sweet1_failed
		ENDIF

	ENDWHILE

	PLAY_MISSION_AUDIO 1
	PRINT_NOW (SWE1_CA) 10000 1 //There's another two Balla tags in this hood.
	IF NOT IS_CHAR_DEAD sweet
		START_CHAR_FACIAL_TALK sweet 3000
	ENDIF

	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0

		IF IS_CHAR_DEAD	sweet
			PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
			GOTO mission_sweet1_failed
		ENDIF

		IF IS_CAR_DEAD sweet_car
			PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
			GOTO mission_sweet1_failed
		ENDIF

	ENDWHILE
	IF NOT IS_CHAR_DEAD sweet
		STOP_CHAR_FACIAL_TALK sweet
	ENDIF
	CLEAR_PRINTS
	TASK_GO_STRAIGHT_TO_COORD scplayer 2097.56 -1651.36 12.71 PEDMOVE_WALK 20000
	
	WAIT 500

tag_cutscene_skipped = 1
SKIP_CUTSCENE_END

CLEAR_PRINTS
CLEAR_MISSION_AUDIO 1
CLEAR_MISSION_AUDIO 2

LOAD_MISSION_AUDIO 2 SOUND_SWE1_CB //You go get 'em and I'll keep the engine running.

CAMERA_RESET_NEW_SCRIPTABLES

IF tag_cutscene_skipped = 0
	DO_FADE 500 FADE_OUT 

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
	
	SET_TAG_STATUS_IN_AREA tag_area4aX tag_area4aY tag_area4bX tag_area4bY TRUE

	SET_CHAR_COORDINATES scplayer 2097.56 -1651.36 12.71
	SET_CHAR_HEADING scplayer 99.0
	IF NOT IS_CHAR_DEAD	sweet
		CLEAR_CHAR_TASKS_IMMEDIATELY sweet
		SET_CHAR_COORDINATES sweet 2099.10 -1650.02 12.57
		SET_CHAR_HEADING sweet 99.0
	ENDIF
	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	RESTORE_CAMERA_JUMPCUT

	DO_FADE 700 FADE_IN 
	
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
ENDIF

	IF NOT IS_CHAR_DEAD	sweet 
		IF NOT IS_CAR_DEAD sweet_car
			TASK_ENTER_CAR_AS_PASSENGER sweet sweet_car 15000 0
		ENDIF
	ENDIF

	REMOVE_BLIP sweet_car_blip
	REMOVE_BLIP	stop_sweet_blip
	CLEAR_LOOK_AT scplayer
	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	RESTORE_CAMERA_JUMPCUT
	REMOVE_BLIP stop_sweet_blip
	ADD_BLIP_FOR_COORD 2068.31 -1654.00 14.3 sweet1_tag2 //On house
	CHANGE_BLIP_COLOUR sweet1_tag2 1
	ADD_BLIP_FOR_COORD 2047.31 -1634.65 13.8 sweet1_tag3 //Down alleyway
	CHANGE_BLIP_COLOUR sweet1_tag3 1
	REMOVE_ANIMATION GRAFFITI
	
	IF NOT IS_CAR_DEAD sweet_car
		LOCK_CAR_DOORS sweet_car CARLOCK_LOCKOUT_PLAYER_ONLY
	ENDIF
	sweet_tagged_tag = 1

	GET_PERCENTAGE_TAGGED_IN_AREA tag_area1aX tag_area1aY tag_area1bX tag_area1bY tag_percentage

    SET_TAG_STATUS_IN_AREA tag_area1aX tag_area1aY tag_area1bX tag_area1bY FALSE

    played_last_line_of_audio = 0

    WHILE NOT tag_percentage = 100
	WAIT 0

	IF played_last_line_of_audio = 0
		IF HAS_MISSION_AUDIO_LOADED 2
			PLAY_MISSION_AUDIO 2
			PRINT_NOW (SWE1_CB) 2500 1 //You go get 'em and I'll keep the engine running.
			played_last_line_of_audio = 1
		ENDIF
	ELSE
		IF played_last_line_of_audio = 1
			IF HAS_MISSION_AUDIO_FINISHED 2
				PRINT_NOW ( SWE1_X ) 6000 1 // ~s~Spray over the remaining tags.
				played_last_line_of_audio = 2
			ENDIF
		ENDIF
	ENDIF

	GET_PERCENTAGE_TAGGED_IN_AREA tag_area1aX tag_area1aY tag_area1bX tag_area1bY tag_percentage

	GET_PERCENTAGE_TAGGED_IN_AREA 2068.8760 -1654.5895 2063.5444 -1650.0127 tag_percentage1	//On house
	GET_PERCENTAGE_TAGGED_IN_AREA 2044.8356 -1633.9858 2047.1448 -1638.9565 tag_percentage2	//Down alleyway

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CHAR_DEAD sweet
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed
	ENDIF

	IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SPRAYCAN
		PRINT_NOW (SWE1SPF) 10000 1 //~r~You need a spraycan!
		GOTO mission_sweet1_failed
	ENDIF

	IF LOCATE_CHAR_ON_FOOT_2D scplayer 2043.68 -1635.73 4.0 4.0 FALSE
	OR LOCATE_CHAR_ON_FOOT_2D scplayer 2067.99 -1652.61 4.0 4.0 FALSE
		IF help_for_tagging = 0
			IF TIMERA > 1000
				PRINT_HELP SWE1_D  
				TIMERA = 0
				help_for_tagging = 1
			ENDIF
		ENDIF
		IF help_for_tagging = 1
			IF TIMERA > 5000
				PRINT_HELP SWE1_F //keep the ~o~ button pressed until the tag is completely sprayed over.
				TIMERA = 0
				help_for_tagging = 2
			ENDIF
		ENDIF
	ENDIF

	IF tag_percentage1 = 100
		REMOVE_BLIP sweet1_tag2
	ENDIF
	IF tag_percentage2 = 100
		REMOVE_BLIP sweet1_tag3
	ENDIF

ENDWHILE

IF NOT IS_CAR_DEAD sweet_car
	REMOVE_BLIP sweet1_tag2
	REMOVE_BLIP sweet1_tag3
	ADD_BLIP_FOR_CAR sweet_car sweet_car_blip
	SET_BLIP_AS_FRIENDLY sweet_car_blip TRUE
	LOCK_CAR_DOORS sweet_car CARLOCK_UNLOCKED
ENDIF

PRINT_NOW ( SWE1_S ) 6000 1 //~s~Get back in ~b~Sweets car!

IF IS_CHAR_DEAD	sweet
	PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
	GOTO mission_sweet1_failed
ENDIF

LOAD_MISSION_AUDIO 1 SOUND_SWE1_AS //C’mon, let’s cruise into Ballas territory.

WHILE NOT IS_CHAR_IN_CAR scplayer sweet_car
	WAIT 0

	IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SPRAYCAN
		PRINT_NOW (SWE1SPF) 10000 1 //~r~You need a spraycan!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CHAR_DEAD	sweet
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ENDIF

	IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SPRAYCAN
		PRINT_NOW (SWE1SPF) 10000 1 //~r~You need a spraycan!
		GOTO mission_sweet1_failed
	ENDIF

ENDWHILE


added_sweet1_blip = 0
help_for_tagging = 0
TIMERA = 0
blob_flag = 1
REMOVE_BLIP sweet_car_blip
ADD_BLIP_FOR_COORD 2338.74 -1500.31 22.83 sweet1_tag1


WHILE NOT HAS_MISSION_AUDIO_LOADED 1
	WAIT 0

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CHAR_DEAD sweet
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed	
	ENDIF

	IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SPRAYCAN
		PRINT_NOW (SWE1SPF) 10000 1 //~r~You need a spraycan!
		GOTO mission_sweet1_failed
	ENDIF

ENDWHILE

PLAY_MISSION_AUDIO 1
PRINT_NOW ( SWE1_AS ) 3000 1 // C’mon, let’s cruise into Ballas territory.

WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
	WAIT 0
	
	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CHAR_DEAD sweet
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed	
	ENDIF

	IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SPRAYCAN
		PRINT_NOW (SWE1SPF) 10000 1 //~r~You need a spraycan!
		GOTO mission_sweet1_failed
	ENDIF

ENDWHILE



IF IS_CHAR_DEAD	sweet
	PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
	GOTO mission_sweet1_failed
ENDIF

IF IS_CAR_DEAD sweet_car
	PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
	GOTO mission_sweet1_failed
ENDIF


TIMERA = 0

WHILE NOT LOCATE_CAR_3D sweet_car 2338.74 -1500.31 22.83 4.0 4.0 4.0 blob_flag
OR NOT IS_CHAR_SITTING_IN_CAR scplayer sweet_car
OR NOT IS_VEHICLE_ON_ALL_WHEELS sweet_car
	WAIT 0

	IF help_for_gangs = 0
		IF TIMERA > 10000
			PRINT_HELP SWE1_Z1
			help_for_gangs = 1
		ENDIF
	ENDIF
		
	IF IS_CHAR_DEAD	sweet
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed
	ENDIF

	IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SPRAYCAN
		PRINT_NOW (SWE1SPF) 10000 1 //~r~You need a spraycan!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ELSE
		IF IS_CHAR_IN_CAR scplayer sweet_car
			IF sweet_car_blip_removed = 0
				REMOVE_BLIP	sweet1_tag1
				REMOVE_BLIP sweet_car_blip
				ADD_BLIP_FOR_COORD 2338.74 -1500.31 22.83 sweet1_tag1
				PRINT_NOW ( HOOD3_B ) 5000 1 // Go into enemy territory and spray over the rival gang tags
				sweet_car_blip_removed = 1
				blob_flag = 1
			ENDIF
		ELSE
			IF sweet_car_blip_removed = 1
				REMOVE_BLIP sweet_car_blip
				REMOVE_BLIP sweet1_tag1
				ADD_BLIP_FOR_CAR sweet_car sweet_car_blip
				SET_BLIP_AS_FRIENDLY sweet_car_blip TRUE
				GOSUB get_back_in_the_car
				PRINT_NOW ( SWE1_S ) 6000 1 //~s~Get back in ~b~Sweets car!
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
				STOP_CHAR_FACIAL_TALK scplayer
				blob_flag = 0
				sweet_car_blip_removed = 0
			ENDIF
		ENDIF
	ENDIF

	IF help_for_tagging	= 0
		IF TIMERA > 3000
			PRINT_NOW ( HOOD3_B ) 5000 1 // Go into enemy territory and spray over the rival gang tags
			help_for_tagging = 1
		ENDIF
	ENDIF	

	IF IS_CHAR_DEAD sweet 
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed
	ENDIF

	IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SPRAYCAN
		PRINT_NOW (SWE1SPF) 10000 1 //~r~You need a spraycan!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ENDIF

ENDWHILE

LOAD_MISSION_AUDIO 1 SOUND_SWE1_AV

REMOVE_BLIP sweet_car_blip
REMOVE_BLIP	sweet1_tag1

ADD_BLIP_FOR_COORD 2396.21 -1469.80 24.9 sweet1_tag3
CHANGE_BLIP_COLOUR sweet1_tag3 1
ADD_BLIP_FOR_COORD 2353.22 -1506.54 24.7 sweet1_tag4
CHANGE_BLIP_COLOUR sweet1_tag4 1


// Sweet fucks off cut*****************************************************************************************************************
SWITCH_WIDESCREEN ON

SET_FIXED_CAMERA_POSITION 2329.6750 -1499.9113 25.8505 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT 2330.6533 -1499.9260 25.6440 JUMP_CUT

	SET_PLAYER_CONTROL player1 OFF

	WAIT 100

	IF NOT IS_CAR_DEAD sweet_car
		IF IS_CHAR_IN_CAR scplayer sweet_car
			TASK_LEAVE_CAR scplayer sweet_car
		ENDIF
	ENDIF
	
WHILE NOT HAS_MISSION_AUDIO_LOADED 1
	WAIT 0

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CHAR_DEAD sweet
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed	
	ENDIF

ENDWHILE

PLAY_MISSION_AUDIO 1
PRINT_NOW ( SWE1_AV ) 10000 1 // You mark up here, I’ll go off and do another ‘hood

WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
	WAIT 0
	
	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CHAR_DEAD sweet
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed	
	ENDIF

ENDWHILE


WHILE IS_CHAR_IN_CAR scplayer sweet_car
	WAIT 0

	IF NOT IS_CHAR_DEAD	sweet 
		IF NOT IS_CAR_DEAD sweet_car
		ENDIF
	ENDIF

ENDWHILE

IF NOT IS_CHAR_DEAD	sweet
	IF NOT IS_CAR_DEAD sweet_car
		LOCK_CAR_DOORS sweet_car CARLOCK_LOCKOUT_PLAYER_ONLY 
		//TASK_SHUFFLE_TO_NEXT_CAR_SEAT sweet sweet_car
		TASK_CAR_DRIVE_WANDER sweet sweet_car 20.0 DRIVINGMODE_AVOIDCARS
	ENDIF
ENDIF		

help_for_tagging = 0
help_for_gangs = 0
TIMERA = 0

SET_TAG_STATUS_IN_AREA tag_area2aX tag_area2aY tag_area2bX tag_area2bY FALSE
GET_PERCENTAGE_TAGGED_IN_AREA tag_area2aX tag_area2aY tag_area2bX tag_area2bY tag_percentage

WAIT 1000

SET_CAMERA_BEHIND_PLAYER
SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL player1 ON
RESTORE_CAMERA_JUMPCUT

WHILE NOT tag_percentage = 100 //SPRAY 2 BALLAS TAGS************************************************************************************
	WAIT 0

	IF help_for_tagging	= 0
		IF TIMERA > 2500
			PRINT_NOW ( SWE1_M ) 6000 1 //Find and spray over the tags.
			LOAD_MISSION_AUDIO 1 SOUND_SWE1_BA //What the fuck?
			LOAD_MISSION_AUDIO 2 SOUND_SWE1_BE //Get that fool!
			help_for_tagging = 1
		ENDIF
	ENDIF

	GET_PERCENTAGE_TAGGED_IN_AREA tag_area2aX tag_area2aY tag_area2bX tag_area2bY tag_percentage

	GET_PERCENTAGE_TAGGED_IN_AREA 2396.21 -1469.80 2387.95 -1463.55 tag_percentage1	//Alleyway
	GET_PERCENTAGE_TAGGED_IN_AREA 2353.22 -1506.54 2354.88 -1511.58 tag_percentage2	//Mex resturant

	IF small_flats_gang = 0
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2395.61 -1470.52 50.0 50.0 FALSE  //FLATS MEMBERS
			CREATE_CHAR PEDTYPE_MISSION1 ballas1 2400.45 -1470.39 22.97 sweet1_flat1
			SET_CHAR_HEADING sweet1_flat1  82.40
			CREATE_CHAR PEDTYPE_MISSION1 ballas2 2396.48 -1469.90 22.99 sweet1_flat2
			SET_CHAR_HEADING sweet1_flat2 262.64
			
			TASK_CHAT_WITH_CHAR sweet1_flat2 sweet1_flat1 FALSE TRUE
			TASK_CHAT_WITH_CHAR sweet1_flat1 sweet1_flat2 TRUE TRUE

			OPEN_SEQUENCE_TASK gang_follow_seq_1 //Flat1
				TASK_GOTO_CHAR_OFFSET -1 scplayer -1 0.5 90.0
				SET_SEQUENCE_TO_REPEAT gang_follow_seq_1 1 
			CLOSE_SEQUENCE_TASK gang_follow_seq_1

			OPEN_SEQUENCE_TASK gang_follow_seq_2 //Flat2
				TASK_GOTO_CHAR_OFFSET -1 scplayer -1 0.5 270.0
				SET_SEQUENCE_TO_REPEAT gang_follow_seq_2 1 
			CLOSE_SEQUENCE_TASK gang_follow_seq_2 
			small_flats_gang = 1	
		ENDIF
	ELSE

		IF gang_hassle = 0
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2395.61 -1470.52 20.0 17.0 FALSE //FLATS MEMBERS
				IF NOT IS_CHAR_DEAD sweet1_flat1
					IF NOT IS_CHAR_DEAD sweet1_flat2
						SWITCH_WIDESCREEN ON //sweet's back**********************************************************************************************
						SET_PLAYER_CONTROL player1 OFF
						SET_FIXED_CAMERA_POSITION 2400.3840 -1472.2081 23.9349 0.0 0.0 0.0 // look at Flats
						POINT_CAMERA_AT_POINT 2399.4900 -1471.7634 23.9880 JUMP_CUT
						PRINT_HELP SWE1_H
						WAIT 500
						SKIP_CUTSCENE_START
						WAIT 6500
						SKIP_CUTSCENE_END
						SWITCH_WIDESCREEN OFF
						SET_PLAYER_CONTROL player1 ON
						RESTORE_CAMERA_JUMPCUT
					ENDIF
				ENDIF
				gang_hassle = 1
			ENDIF
		ENDIF

		IF gang_hassle = 1
			IF help_for_gangs = 0
				IF TIMERA > 2000
					PRINT_HELP SWE1_I 
					help_for_gangs = 1
				ENDIF
			ENDIF

			IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2395.61 -1470.52 5.0 5.0 FALSE //FLATS MEMBERS		
				IF NOT IS_CHAR_DEAD	sweet1_flat1
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet1_flat1 TRUE
					TASK_STAND_STILL sweet1_flat1 0
					PERFORM_SEQUENCE_TASK sweet1_flat1 gang_follow_seq_1
					CLEAR_SEQUENCE_TASK	gang_follow_seq_1
				ENDIF
				IF NOT IS_CHAR_DEAD	sweet1_flat2
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet1_flat2 TRUE
					TASK_STAND_STILL sweet1_flat2 0
					PERFORM_SEQUENCE_TASK sweet1_flat2 gang_follow_seq_2
					CLEAR_SEQUENCE_TASK	gang_follow_seq_2
					IF HAS_MISSION_AUDIO_LOADED 1
						PLAY_MISSION_AUDIO 1
						PRINT_NOW ( SWE1_BA ) 2000 1 //What the fuck?	
					ENDIF
					TIMERA = 0
				ENDIF
				gang_hassle = 2
			ENDIF
		ENDIF

		IF gang_hassle = 2
			IF tag_percentage1 = 100
			OR TIMERA > 5000
				IF NOT IS_CHAR_DEAD	sweet1_flat1
					TASK_KILL_CHAR_ON_FOOT sweet1_flat1 scplayer
				ENDIF
				IF NOT IS_CHAR_DEAD	sweet1_flat2
					TASK_KILL_CHAR_ON_FOOT sweet1_flat2 scplayer
					IF HAS_MISSION_AUDIO_LOADED 2
						PLAY_MISSION_AUDIO 2
						PRINT_NOW ( SWE1_BE ) 2000 1 //Get that fool!	
					ENDIF
				ENDIF
				gang_hassle = 3
			ENDIF
		ENDIF

		IF gang_hassle = 3
			IF HAS_MISSION_AUDIO_FINISHED 2
				IF NOT IS_CHAR_DEAD	sweet1_flat1
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet1_flat1 FALSE
				ENDIF
				IF NOT IS_CHAR_DEAD	sweet1_flat2
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet1_flat2 FALSE
				ENDIF
				gang_hassle = 4
			ENDIF
		ENDIF

	ENDIF 
		
	IF tag_percentage1 = 100
		REMOVE_BLIP sweet1_tag3
	ENDIF

	IF tag_percentage2 = 100
		REMOVE_BLIP sweet1_tag4
	ENDIF
	
	IF LOCATE_CHAR_ON_FOOT_2D scplayer 2353.30 -1508.18 3.0 3.0 FALSE
	AND jump_help6 = 0
		PRINT_HELP SWE1_G //Spraying over a rival tags will gain you respect from your gang.
		TIMERA = 0
		jump_help6 = 1		
	ENDIF
 
	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CHAR_DEAD sweet
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed	
	ENDIF
	
	IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SPRAYCAN
		PRINT_NOW (SWE1SPF) 10000 1 //~r~You need a spraycan!
		GOTO mission_sweet1_failed
	ENDIF

	IF sweet_car_frozen = 0
		IF NOT IS_CHAR_DEAD sweet
			IF NOT IS_CAR_DEAD sweet_car
				IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer sweet_car 30.0 30.0 10.0 FALSE
				AND NOT IS_CAR_ON_SCREEN sweet_car
					CLEAR_CHAR_TASKS sweet 
					SET_CAR_COORDINATES sweet_car 2487.1721 -1666.3010 -100.3438
					FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION sweet_car TRUE
					FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION sweet TRUE
					sweet_car_frozen = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF

ENDWHILE


REMOVE_BLIP sweet1_tag3
REMOVE_BLIP	sweet1_tag4
REMOVE_BLIP	sweet1_tag1
ADD_BLIP_FOR_COORD 2374.0 -1534.1 23.0 sweet1_tag1
PRINT_NOW ( SWE1_Z ) 5000 1 // ~s~go to the ~y~blip~s~ to find the next tag. 


SET_TAG_STATUS_IN_AREA tag_area3aX tag_area3aY tag_area3bX tag_area3bY FALSE
GET_PERCENTAGE_TAGGED_IN_AREA tag_area3aX tag_area3aY tag_area3bX tag_area3bY tag_percentage

REQUEST_CAR_RECORDING 207

WHILE NOT tag_percentage = 100 //SPRAY LAST TAG******************************************************************************************
	WAIT 0

	IF help_for_tagging	= 0
		IF TIMERA > 2500
			PRINT_NOW ( SWE1_M ) 6000 1 //Find and spray over the tags.
			help_for_tagging = 1
		ENDIF
	ENDIF

	GET_PERCENTAGE_TAGGED_IN_AREA tag_area3aX tag_area3aY tag_area3bX tag_area3bY tag_percentage //roof top

	IF tag_percentage = 100
		REMOVE_BLIP sweet1_tag2
	ENDIF
			
	IF jump_help1 = 0
		IF LOCATE_CHAR_ON_FOOT_3D scplayer 2374.0 -1534.1 23.0 1.4 1.4 2.0 TRUE
			
			PRINT_HELP JUMPH1  

			REMOVE_BLIP sweet1_tag1
			REMOVE_BLIP	sweet1_tag2
			ADD_BLIP_FOR_COORD 2395.43 -1551.69 26.98 sweet1_tag2 //Roof
			CHANGE_BLIP_COLOUR sweet1_tag2 1
			jump_help1 = 1
		ENDIF
	ENDIF

	IF LOCATE_CHAR_ON_FOOT_2D scplayer 2352.3 -1552.1 3.0 3.0 FALSE
	AND jump_help1 = 0

		PRINT_HELP JUMPH1  
		
		jump_help1 = 1
	ENDIF

	IF LOCATE_CHAR_ON_FOOT_2D scplayer 2420.7 -1572.1 3.0 3.0 FALSE
	AND jump_help1 = 0
		
		PRINT_HELP JUMPH1  
		
		jump_help1 = 1
	ENDIF

	IF LOCATE_CHAR_ON_FOOT_2D scplayer 2373.5 -1547.2 3.0 3.0 FALSE
	AND jump_help2 = 0
		PRINT_HELP RADAR5 //up arrows
		jump_help2 = 1
	ENDIF

	IF LOCATE_CHAR_ON_FOOT_2D scplayer 2378.7546 -1555.8896 3.0 3.0 FALSE
	AND jump_help5 = 0
		
		PRINT_HELP JUMPH2  

		REMOVE_BLIP sweet1_tag1
		REMOVE_BLIP	sweet1_tag2
		ADD_BLIP_FOR_COORD 2395.43 -1551.69 26.98 sweet1_tag2 //Roof
		CHANGE_BLIP_COLOUR sweet1_tag2 1
		jump_help5 = 1
	ENDIF
 
	IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SPRAYCAN
		PRINT_NOW (SWE1SPF) 10000 1 //~r~You need a spraycan!
		GOTO mission_sweet1_failed
	ENDIF
	
	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CHAR_DEAD sweet
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed	
	ENDIF

	IF sweet_car_frozen = 0
		IF NOT IS_CHAR_DEAD sweet
			IF NOT IS_CAR_DEAD sweet_car
				IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer sweet_car 100.0 100.0 10.0 FALSE
				OR NOT IS_CAR_ON_SCREEN sweet_car
					CLEAR_CHAR_TASKS sweet 
					SET_CAR_COORDINATES sweet_car 2487.1721 -1666.3010 -100.3438
					FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION sweet_car TRUE
					FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION sweet TRUE
					sweet_car_frozen = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF

ENDWHILE

//skip_to_final_sweet_cut:

REMOVE_BLIP sweet1_tag2

	IF NOT IS_CAR_DEAD sweet_car
		IF NOT IS_CHAR_DEAD sweet
			IF sweet_car_frozen = 1
				FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION sweet_car FALSE
				FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION sweet FALSE
			ENDIF
			SET_CAR_COORDINATES sweet_car 2337.86 -1467.21 22.82
			SET_CAR_HEADING sweet_car 180.0
			WAIT 100
			IF NOT IS_CAR_DEAD sweet_car
				IF NOT IS_CHAR_DEAD sweet
					CLEAR_CHAR_TASKS_IMMEDIATELY sweet
					TASK_WARP_CHAR_INTO_CAR_AS_DRIVER sweet	sweet_car
					SET_CHAR_CAN_BE_SHOT_IN_VEHICLE sweet FALSE
					REMOVE_BLIP sweet1_tag1
					LOCK_CAR_DOORS sweet_car CARLOCK_UNLOCKED
				ENDIF
			ENDIF
		ENDIF
	ENDIF

WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 207
	WAIT 0
	
	REQUEST_CAR_RECORDING 207
	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CHAR_DEAD sweet
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed	
	ENDIF

ENDWHILE

IF NOT IS_CAR_DEAD sweet_car
	IF NOT IS_CHAR_DEAD	sweet
		IF HAS_CAR_RECORDING_BEEN_LOADED 207
			CLEAR_CHAR_TASKS_IMMEDIATELY sweet
			WAIT 0
			IF NOT IS_CAR_DEAD sweet_car
				IF NOT IS_CHAR_DEAD	sweet
					TASK_WARP_CHAR_INTO_CAR_AS_DRIVER sweet	sweet_car
				ENDIF
			ENDIF
			START_PLAYBACK_RECORDED_CAR sweet_car 207
		ENDIF	
	ENDIF
ENDIF

WAIT 2000

IF NOT IS_CAR_DEAD sweet_car
	REMOVE_BLIP sweet_car_blip
	ADD_BLIP_FOR_CAR sweet_car sweet_car_blip
	SET_BLIP_AS_FRIENDLY sweet_car_blip TRUE
ENDIF

SET_PLAYER_CONTROL player1 OFF
CLEAR_HELP

CLEAR_AREA 2378.3225 -1526.1534 23.8690 50.0 TRUE

//SET_FIXED_CAMERA_POSITION 2337.1609 -1527.9457 23.7488 0.0 0.0 0.0 // look at car
//POINT_CAMERA_AT_POINT 2337.8621 -1527.2365 23.6769 JUMP_CUT

DO_FADE 300 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

LOAD_SCENE_IN_DIRECTION	2385.4443 -1529.3350 24.0351 82.7482

SWITCH_WIDESCREEN ON //sweet's back**********************************************************************************************

SET_FIXED_CAMERA_POSITION 2386.4436 -1529.3130 24.0696 0.0 0.0 0.0 // look at car2
POINT_CAMERA_AT_POINT 2385.4443 -1529.3350 24.0351 JUMP_CUT

DO_FADE 300 FADE_IN

TIMERA = 0

LOAD_MISSION_AUDIO 1 SOUND_SWE1_BH

WHILE NOT TIMERA > 3500	
	WAIT 0

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CHAR_DEAD sweet
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed	
	ENDIF

ENDWHILE

//CAMERA_RESET_NEW_SCRIPTABLES
SET_FIXED_CAMERA_POSITION 2386.4436 -1529.3130 24.0696 0.0 0.0 0.0 // look at car2
POINT_CAMERA_AT_POINT 2385.4443 -1529.3350 24.0351 JUMP_CUT

IF NOT IS_CAR_DEAD sweet_car
	REPORT_MISSION_AUDIO_EVENT_AT_CAR sweet_car SOUND_SWEETS_HORN
ENDIF

WHILE NOT HAS_MISSION_AUDIO_LOADED 1
	WAIT 0

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CHAR_DEAD sweet
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed	
	ENDIF

ENDWHILE

//WAIT 500

IF NOT IS_CAR_DEAD sweet_car
	REPORT_MISSION_AUDIO_EVENT_AT_CAR sweet_car SOUND_SWEETS_HORN
ENDIF

PLAY_MISSION_AUDIO 1
PRINT_NOW ( SWE1_BH ) 10000 1 // CARL, QUICK, GET IN!

WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
	WAIT 0
	
	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CHAR_DEAD sweet
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed	
	ENDIF

ENDWHILE

IF NOT IS_CAR_DEAD sweet_car
	IF NOT IS_CHAR_DEAD	sweet
		CLEAR_CHAR_TASKS_IMMEDIATELY sweet
		WARP_CHAR_INTO_CAR_AS_PASSENGER sweet sweet_car 0
	ENDIF
ENDIF

CAMERA_RESET_NEW_SCRIPTABLES

IF NOT IS_CHAR_DEAD	sweet1_flat1
	CLEAR_CHAR_TASKS sweet1_flat1
	TASK_WANDER_STANDARD sweet1_flat1	
ENDIF

IF NOT IS_CHAR_DEAD	sweet1_flat2
	CLEAR_CHAR_TASKS sweet1_flat2
	TASK_WANDER_STANDARD sweet1_flat2	
ENDIF

blob_flag = 0

sweet_car_blip_removed = 0

SWITCH_WIDESCREEN OFF //sweet's back
SET_PLAYER_CONTROL player1 ON
RESTORE_CAMERA_JUMPCUT

PRINT_NOW ( SWE1_S ) 6000 1 //~s~Get back in ~b~Sweets car!
REMOVE_BLIP sweet_car_blip
REMOVE_BLIP sweet1_tag1
IF NOT IS_CAR_DEAD sweet_car
	ADD_BLIP_FOR_CAR sweet_car sweet_car_blip
	SET_BLIP_AS_FRIENDLY sweet_car_blip TRUE
ENDIF

LOAD_MISSION_AUDIO 1 SOUND_SWEX_AH	//Get us back to the hood, CJ!			


WHILE NOT LOCATE_CAR_3D sweet_car 2504.7354 -1672.4498 12.3848 4.0 4.0 4.0 blob_flag //Drive Sweet home
OR NOT IS_CHAR_SITTING_IN_CAR scplayer sweet_car
OR NOT IS_VEHICLE_ON_ALL_WHEELS sweet_car
	WAIT 0

	IF IS_CHAR_DEAD sweet
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed	
	ENDIF

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ELSE
		IF IS_CHAR_IN_CAR scplayer sweet_car
			IF played_mission_audio_sweet1 = 0
				IF HAS_MISSION_AUDIO_LOADED 1
					PLAY_MISSION_AUDIO 1   	
					PRINT_NOW ( SWEX_AH ) 3000 1 //Get us back to the hood, CJ!
					TIMERA = 0
					played_mission_audio_sweet1 = 1
				ENDIF
			ENDIF
			IF sweet_car_blip_removed = 0
				REMOVE_BLIP	sweet1_tag1
				REMOVE_BLIP sweet_car_blip
				ADD_BLIP_FOR_COORD 2504.7354 -1672.4498 12.3848 sweet1_tag1
				IF TIMERA > 3000
					PRINT_NOW ( SWE1_B ) 6000 1 // Go back to Sweets gaff.
					sweet_car_blip_removed = 1
				ENDIF
				blob_flag = 1
			ENDIF
		ELSE
			IF sweet_car_blip_removed = 1
				REMOVE_BLIP sweet_car_blip
				REMOVE_BLIP sweet1_tag1
				ADD_BLIP_FOR_CAR sweet_car sweet_car_blip
				SET_BLIP_AS_FRIENDLY sweet_car_blip TRUE
				GOSUB get_back_in_the_car
				PRINT_NOW ( SWE1_S ) 6000 1 //~s~Get back in ~b~Sweets car!
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
				STOP_CHAR_FACIAL_TALK scplayer
				blob_flag = 0
				sweet_car_blip_removed = 0
			ENDIF
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD sweet 
		PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
		GOTO mission_sweet1_failed
	ENDIF

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
		GOTO mission_sweet1_failed
	ENDIF

ENDWHILE

//skip_to_final_sweet_cut:

SWITCH_WIDESCREEN ON
SET_PLAYER_CONTROL player1 OFF

DO_FADE 1000 FADE_OUT 

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

REQUEST_ANIMATION GANGS

WHILE NOT HAS_ANIMATION_LOADED GANGS
	WAIT 0
ENDWHILE
	
SET_NEAR_CLIP 0.2

IF NOT IS_CHAR_DEAD	sweet 
	HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE sweet TRUE
ENDIF

SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED	
HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
REMOVE_WEAPON_FROM_CHAR	scplayer WEAPONTYPE_SPRAYCAN

IF NOT IS_CHAR_DEAD sweet
	STOP_CHAR_FACIAL_TALK sweet
	STOP_CHAR_FACIAL_TALK scplayer
ENDIF

sweet1_index = 0
sweet1_audio_is_playing = 0
sweet1_cutscene_flag = 0
sweet1_chat_switch = SWEET1_CHAT2
GOSUB sweet1_chat_switch

SET_FIXED_CAMERA_POSITION 2509.9500 -1672.0947 13.7315 0.0 0.0 0.0 //Sweet/player
POINT_CAMERA_AT_POINT 2510.8914 -1672.4194 13.8204 JUMP_CUT

CAMERA_RESET_NEW_SCRIPTABLES
CAMERA_PERSIST_TRACK TRUE                   
CAMERA_PERSIST_POS TRUE             
CAMERA_SET_VECTOR_MOVE  2510.1528 -1673.5514 14.2267 2509.9971 -1671.8660 13.9831 18000 TRUE
CAMERA_SET_VECTOR_TRACK 2510.9673 -1672.9948 14.0633 2510.8853 -1672.3169 13.8960 18000 TRUE


	MARK_CAR_AS_NO_LONGER_NEEDED sweet_car

	CLEAR_AREA 2511.3518 -1672.14 12.4588 1.0 TRUE
	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
	IF NOT IS_CHAR_IN_ANY_CAR scplayer
		SET_CHAR_COORDINATES scplayer 2511.3518 -1672.14 12.4588 
	ELSE
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 2511.3518 -1672.14 12.4588
	ENDIF
	SET_CHAR_HEADING scplayer 180.0

	CLEAR_AREA 2511.3518 -1673.14 12.4588 1.0 TRUE
	IF NOT IS_CHAR_DEAD sweet
		CLEAR_CHAR_TASKS_IMMEDIATELY sweet
		IF NOT IS_CHAR_IN_ANY_CAR sweet
			SET_CHAR_COORDINATES sweet 2511.3518 -1673.14 12.4588 
		ELSE
			WARP_CHAR_FROM_CAR_TO_COORD sweet 2511.3518 -1673.14 12.4588
		ENDIF
		SET_CHAR_HEADING sweet 0.0
	ENDIF

DO_FADE 1000 FADE_IN 

WAIT 700

Retured_anim_time = 0.0

sweet1_cutscene_flag = 0
WHILE NOT sweet1_index = 1 //Like riding a bike!
	WAIT 0

		GOSUB load_and_play_audio_sweet1
		IF sweet1_cutscene_flag = 0
			IF sweet1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD sweet
					CLEAR_LOOK_AT scplayer
					TASK_LOOK_AT_CHAR scplayer sweet 20000
				ENDIF
				sweet1_cutscene_flag = 1
			ENDIF
		ENDIF

ENDWHILE

sweet1_cutscene_flag = 0
WHILE NOT sweet1_index = 2 //Yeah, it all comes back, man.
	WAIT 0

		GOSUB load_and_play_audio_sweet1
		IF sweet1_cutscene_flag = 0
			IF sweet1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD sweet
				ENDIF
				sweet1_cutscene_flag = 1
			ENDIF
		ENDIF

ENDWHILE

sweet1_cutscene_flag = 0
WHILE NOT sweet1_index = 3 //Yo, how you doing for greens?
	WAIT 0

		GOSUB load_and_play_audio_sweet1
		IF sweet1_cutscene_flag = 0
			IF sweet1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD sweet
				ENDIF
				sweet1_cutscene_flag = 1
			ENDIF
		ENDIF

ENDWHILE

sweet1_cutscene_flag = 0
WHILE NOT sweet1_index = 4 //Check it, I’m lean, man.
	WAIT 0

		GOSUB load_and_play_audio_sweet1
		IF sweet1_cutscene_flag = 0
			IF sweet1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD sweet
					TASK_PLAY_ANIM scplayer IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE 6000
				ENDIF
				sweet1_cutscene_flag = 1
			ENDIF
		ENDIF

ENDWHILE


sweet1_cutscene_flag = 0
WHILE NOT sweet1_index = 5 //CRASH took my big greens, left me with small change...
	WAIT 0

		GOSUB load_and_play_audio_sweet1
		IF sweet1_cutscene_flag = 0
			IF sweet1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD sweet
					//TASK_PLAY_ANIM scplayer IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE 6000
				ENDIF
				sweet1_cutscene_flag = 1
			ENDIF
		ENDIF

ENDWHILE

sweet1_cutscene_flag = 0
WHILE NOT sweet1_index = 6 //Here, get yourself a beer or something.
	WAIT 0

		GOSUB load_and_play_audio_sweet1
		IF sweet1_cutscene_flag = 0
			IF sweet1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD sweet
					//START_CHAR_FACIAL_TALK sweet 5000
					IF NOT IS_CHAR_DEAD sweet
						TASK_PLAY_ANIM sweet hndshkfa GANGS 4.0 FALSE FALSE FALSE FALSE -1
						TASK_PLAY_ANIM scplayer hndshkfa GANGS 4.0 FALSE FALSE FALSE FALSE -1
					ENDIF
				ENDIF
				sweet1_cutscene_flag = 1
			ENDIF
		ENDIF

ENDWHILE

GET_SCRIPT_TASK_STATUS scplayer TASK_PLAY_ANIM ReturnStatus

WHILE NOT ReturnStatus = FINISHED_TASK
	WAIT 0

	GET_SCRIPT_TASK_STATUS scplayer TASK_PLAY_ANIM ReturnStatus

ENDWHILE


sweet1_cutscene_flag = 0
WHILE NOT sweet1_index = 7 //I’ll catch you later.
	WAIT 0

		GOSUB load_and_play_audio_sweet1
		IF sweet1_cutscene_flag = 0
			IF sweet1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD sweet
					CLEAR_LOOK_AT scplayer
					TASK_LOOK_AT_CHAR scplayer sweet 2000
					TASK_GO_STRAIGHT_TO_COORD sweet 2517.3972 -1677.3524 13.2548 PEDMOVE_WALK 20000
				ENDIF
				sweet1_cutscene_flag = 1
			ENDIF
		ENDIF

ENDWHILE

WAIT 1500

CLEAR_LOOK_AT scplayer

HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
CAMERA_RESET_NEW_SCRIPTABLES
DELETE_CHAR sweet
SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL player1 ON
RESTORE_CAMERA_JUMPCUT


GOTO mission_sweet1_passed



 // **************************************** Mission sweet1 failed ***********************

mission_sweet1_failed:

	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"

	SET_TAG_STATUS_IN_AREA tag_area1aX tag_area1aY tag_area1bX tag_area1bY FALSE
	SET_TAG_STATUS_IN_AREA tag_area2aX tag_area2aY tag_area2bX tag_area2bY FALSE
	SET_TAG_STATUS_IN_AREA tag_area3aX tag_area3aY tag_area3bX tag_area3bY FALSE
	SET_TAG_STATUS_IN_AREA tag_area4aX tag_area4aY tag_area4bX tag_area4bY FALSE

	IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SPRAYCAN
		REMOVE_WEAPON_FROM_CHAR	scplayer WEAPONTYPE_SPRAYCAN
	ENDIF

RETURN

   

// **************************************** mission sweet1 passed ************************

mission_sweet1_passed:

	flag_sweet_mission_counter ++ //Used to terminate this mission loop in the main script. These varibles will be set up in the main.sc
	
	IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SPRAYCAN
		REMOVE_WEAPON_FROM_CHAR	scplayer WEAPONTYPE_SPRAYCAN
		GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_SPRAYCAN 1000
	ELSE
		GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_SPRAYCAN 1000
	ENDIF

	bball_unlocked = 1

	REMOVE_PICKUP spray_can_pickup
	CREATE_PICKUP_WITH_AMMO SPRAYCAN PICKUP_ON_STREET_SLOW 1000 2492.5054 -1700.8246 1017.8 spray_can_pickup
	PLAYER_MADE_PROGRESS 1
	REGISTER_MISSION_PASSED ( SWEET_1 ) //Used in the stats 
	PRINT_WITH_NUMBER_BIG ( M_PASSS ) 200 5000 1 //"Mission Passed!"
	AWARD_PLAYER_MISSION_RESPECT 3
	ADD_SCORE player1 200
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1

RETURN
		


// ********************************** mission cleanup ***********************************

mission_cleanup_sweet1:

	MARK_MODEL_AS_NO_LONGER_NEEDED SPRAYCAN
	MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
	MARK_MODEL_AS_NO_LONGER_NEEDED ballas1
	MARK_MODEL_AS_NO_LONGER_NEEDED ballas2
	MARK_CHAR_AS_NO_LONGER_NEEDED sweet1_flat1
	MARK_CHAR_AS_NO_LONGER_NEEDED sweet1_flat2
	MARK_CHAR_AS_NO_LONGER_NEEDED sweet 
	GET_GAME_TIMER timer_mobile_start //Used to reset the mobile phone timer so it doesn't ring immediately after the mission
	SWITCH_CAR_GENERATOR gen_car7 101
	REMOVE_BLIP sweet1_tag1
	REMOVE_BLIP sweet1_tag2
	REMOVE_BLIP sweet1_tag3
	REMOVE_BLIP sweet1_tag4
	REMOVE_BLIP sweet_car_blip
	REMOVE_BLIP stop_sweet_blip
	REMOVE_ANIMATION GRAFFITI
	REMOVE_ANIMATION GANGS
	REMOVE_CHAR_ELEGANTLY sweet
	REMOVE_CAR_RECORDING 207
	UNLOAD_SPECIAL_CHARACTER 1
	UNLOAD_SPECIAL_CHARACTER 10
	IF NOT IS_CAR_DEAD sweet_car
		SET_CAN_BURST_CAR_TYRES sweet_car TRUE
	ENDIF

	SET_ZONE_GANG_STRENGTH IWD1 GANG_FLAT 30 
	SET_ZONE_GANG_STRENGTH IWD2 GANG_FLAT 30
	SET_ZONE_GANG_STRENGTH IWD3a GANG_FLAT 30
	SET_ZONE_GANG_STRENGTH IWD3b GANG_FLAT 30
	SET_ZONE_GANG_STRENGTH IWD4 GANG_FLAT 30
	SET_ZONE_GANG_STRENGTH IWD5 GANG_FLAT 30

	SET_ZONE_GANG_STRENGTH ELS1a GANG_FLAT 30
	SET_ZONE_GANG_STRENGTH ELS1b GANG_FLAT 30
	SET_ZONE_GANG_STRENGTH ELS2	 GANG_FLAT 30
	SET_ZONE_GANG_STRENGTH ELS3a GANG_FLAT 30
	SET_ZONE_GANG_STRENGTH ELS3b GANG_FLAT 30
	SET_ZONE_GANG_STRENGTH ELS4	 GANG_FLAT 30
	flag_player_on_mission = 0

	MISSION_HAS_FINISHED

RETURN


get_back_in_the_car:
	
	CLEAR_MISSION_AUDIO 2
	IF get_in_counter_swee1 = 0
		LOAD_MISSION_AUDIO 2 SOUND_SWE1_BM	//Get in, nigga!
	ENDIF

	IF get_in_counter_swee1 = 1
		LOAD_MISSION_AUDIO 2 SOUND_SWEX_BS	//CJ, for once, don't be a punk!
	ENDIF

	IF get_in_counter_swee1 = 2
		LOAD_MISSION_AUDIO 2 SOUND_SWEX_BP	//Don't be a buster, CJ!
	ENDIF

	IF get_in_counter_swee1 = 3			
		LOAD_MISSION_AUDIO 2 SOUND_SWE1_BG // CJ, GET IN!
	ENDIF

	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
	STOP_CHAR_FACIAL_TALK scplayer

	CLEAR_MISSION_AUDIO 1
	WHILE NOT HAS_MISSION_AUDIO_LOADED 2
		WAIT 0
		IF IS_CHAR_DEAD sweet 
			//PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
			//GOTO mission_sweet1_failed
			RETURN
		ENDIF

		IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SPRAYCAN
			//PRINT_NOW (SWE1SPF) 10000 1 //~r~You need a spraycan!
			//GOTO mission_sweet1_failed
			RETURN
		ENDIF

		IF IS_CAR_DEAD sweet_car
			//PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
			//GOTO mission_sweet1_failed
			RETURN
		ENDIF

	ENDWHILE

	PLAY_MISSION_AUDIO 2 
	  	
	IF get_in_counter_swee1 = 0
		PRINT_NOW ( SWE1_BM ) 3000 1 //Get in, nigga!	
	ENDIF
	IF get_in_counter_swee1 = 1
		PRINT_NOW ( SWEX_BS ) 3000 1 //	CJ, for once, don't be a punk!
	ENDIF
	IF get_in_counter_swee1 = 2
		PRINT_NOW ( SWEX_BP ) 3000 1 //Don't be a buster, CJ!
	ENDIF
	IF get_in_counter_swee1 = 3
		PRINT_NOW ( SWE1_BG ) 3000 1 // CJ, GET IN!
	ENDIF

	WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
		WAIT 0
		IF IS_CHAR_DEAD sweet 
			//PRINT_NOW (SWE1_P) 10000 1 //~r~Sweet is dead!
			//GOTO mission_sweet1_failed
			RETURN
		ENDIF

		IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_SPRAYCAN
			//PRINT_NOW (SWE1SPF) 10000 1 //~r~You need a spraycan!
			//GOTO mission_sweet1_failed
			RETURN
		ENDIF

		IF IS_CAR_DEAD sweet_car
			//PRINT_NOW (SWE1_Q) 10000 1 //~r~You destroyed Sweets ride!
			//GOTO mission_sweet1_failed
			RETURN
		ENDIF

	ENDWHILE

	get_in_counter_swee1 ++

	IF get_in_counter_swee1 > 3
		get_in_counter_swee1 = 0
	ENDIF

RETURN

load_and_play_audio_sweet1:

	IF sweet1_audio_is_playing = 0
	OR sweet1_audio_is_playing = 1
		IF sweet1_index <= sweet1_cell_index_end
			IF TIMERA > 200
				GOSUB play_sweet1_audio
			ENDIF
		ENDIF
	ENDIF

	IF sweet1_audio_is_playing = 2
		IF HAS_MISSION_AUDIO_FINISHED 1
			GOSUB stop_talking_sweet1
			sweet1_audio_is_playing = 0
			sweet1_index ++
			sweet1_cutscene_flag = 0
			CLEAR_PRINTS
			TIMERA = 0	
		ENDIF
	ENDIF

RETURN

play_sweet1_audio:

	IF sweet1_audio_is_playing = 0
		LOAD_MISSION_AUDIO 1 sweet1_audio_chat[sweet1_index]
		sweet1_audio_is_playing = 1
	ENDIF
	IF sweet1_audio_is_playing = 1
		IF HAS_MISSION_AUDIO_LOADED 1
			PRINT_NOW ( $sweet1_chat[sweet1_index] ) 4000 1 //Let's head over to B-Dup's crib.
			PLAY_MISSION_AUDIO 1
			GOSUB start_talking_sweet1
			sweet1_audio_is_playing = 2
		ENDIF
	ENDIF	
	
RETURN

  
start_talking_sweet1:
	/*
	IF sweet1_audio_chat[sweet1_index] = SOUND_SWE1_SA
	OR sweet1_audio_chat[sweet1_index] = SOUND_SWE1_SB
		IF NOT IS_CHAR_DEAD	crackhead1
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH crackhead1 TRUE
			START_CHAR_FACIAL_TALK crackhead1 3000
			RETURN
		ENDIF   
	ENDIF
	*/  

	IF sweet1_audio_chat[sweet1_index] = SOUND_SWE1_AB
	OR sweet1_audio_chat[sweet1_index] = SOUND_SWE1_AE
	OR sweet1_audio_chat[sweet1_index] = SOUND_SWE1_BO
	OR sweet1_audio_chat[sweet1_index] = SOUND_SWE1_BQ
	OR sweet1_audio_chat[sweet1_index] = SOUND_SWE1_BX
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
		START_CHAR_FACIAL_TALK scplayer 2000
	ELSE
		IF NOT IS_CHAR_DEAD	sweet
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet TRUE
			START_CHAR_FACIAL_TALK sweet 2000
		ENDIF
	ENDIF


RETURN


stop_talking_sweet1:
	/*
	IF sweet1_audio_chat[sweet1_index] = SOUND_SWE1_SA
	OR sweet1_audio_chat[sweet1_index] = SOUND_SWE1_SB
		IF NOT IS_CHAR_DEAD	crackhead1
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH crackhead1 FALSE
			STOP_CHAR_FACIAL_TALK crackhead1
			RETURN
		ENDIF   
	ENDIF
	*/

	IF sweet1_audio_chat[sweet1_index] = SOUND_SWE1_AB
	OR sweet1_audio_chat[sweet1_index] = SOUND_SWE1_AE
	OR sweet1_audio_chat[sweet1_index] = SOUND_SWE1_BO
	OR sweet1_audio_chat[sweet1_index] = SOUND_SWE1_BQ
	OR sweet1_audio_chat[sweet1_index] = SOUND_SWE1_BX
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
		STOP_CHAR_FACIAL_TALK scplayer
	ELSE
		IF NOT IS_CHAR_DEAD	sweet
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet FALSE
			STOP_CHAR_FACIAL_TALK sweet
		ENDIF
	ENDIF

RETURN





 
}