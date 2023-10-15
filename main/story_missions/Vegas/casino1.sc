MISSION_START
// ------------------------------------------------------------------------------------------------
// Casino Mission 1: Wind Up

{

SCRIPT_NAME CASINO1

// Begin...
GOSUB mission_start_ca1

	IF HAS_DEATHARREST_BEEN_EXECUTED
		GOSUB mission_failed_ca1
	ENDIF

GOSUB mission_cleanup_ca1

MISSION_END
// ------------------------------------------------------------------------------------------------
// Variables
// ---- Flags
	LVAR_INT ca1_stage ca1_cut ca1_in_car ca1_choice ca1_freeway
	LVAR_INT ca1_handbrake ca1_air ca1_burnout ca1_speed ca1_stunt ca1_swerve
	LVAR_INT ca1_swerve_anim ca1_brake ca1_anim_step ca1_miss ca1_help_counter
	
	VAR_INT ca1_test 
// ---- Blips
	LVAR_INT ca1_punk_drop_blip 
// ---- Timers / Counters
	LVAR_INT ca1_timer_diff ca1_timer_end ca1_timer_start  ca1_timer_diff_add	ca1_count_var
	LVAR_INT ca1_swerve_counter	ca1_swerve_spook_count
	LVAR_INT ca1_speed_counter ca1_burnout_counter
	VAR_INT ca1_spooked
	LVAR_INT ca1_timer_diff1 ca1_timer_end1 ca1_timer_start1
	LVAR_INT ca1_timer_diff2 ca1_timer_end2 ca1_timer_start2
	LVAR_INT ca1_timer_diff3 ca1_timer_end3 ca1_timer_start3
	LVAR_FLOAT ca1_timer_diff_add_fl ca1_count_var_fl ca1_truck_speed
	LVAR_INT ca1_h_timer_diff ca1_h_timer_end ca1_h_timer_start
	LVAR_INT ca1_brake_diff ca1_brake_end ca1_brake_start ca1_ticker
// ---- Coords
	LVAR_FLOAT ca1_punk_drop_x ca1_punk_drop_y ca1_punk_drop_z
	LVAR_FLOAT ca1_min_x ca1_min_y ca1_min_z ca1_max_x ca1_max_y ca1_max_z
	LVAR_FLOAT ca1_offset_x ca1_offset_y ca1_offset_z
	
// ---- Input Stuff
	LVAR_INT ca1_l_x ca1_l_y ca1_r_x ca1_r_y ca1_l_x_prev

// ---- Anim Gosub Stuff...
	LVAR_FLOAT ca1_anim_time
	LVAR_INT ca1_player_status
  	//LVAR_INT tyd2car_low tyd2car_med tyd2car_high tyd2car_bump tyd2car_turnr tyd2car_turnl
  	
// ---- Dialogue Stuff
	LVAR_INT ca1_dialogue_playing  
	VAR_INT ca1_random_dialogue ca1_random_dialogue_last 
	LVAR_INT ca1_text_timer_end ca1_text_timer_diff ca1_text_timer_start
	LVAR_TEXT_LABEL ca1_text[67]
	LVAR_INT ca1_audio[67] ca1_audio_slot ca1_alt_slot ca1_counter ca1_ahead_counter ca1_audio_playing 

					

// ---- Cameras
	LVAR_FLOAT ca1_cam_x[4] ca1_cam_y[4] ca1_cam_z[4]

// ---- Objects

// ---- Entities
	LVAR_INT ca1_punk_decision_idx ca1_door

		
// ------------------------------------------------------------------------------------------------
// -------- Vehicles
		ca1_truck_request:
			LVAR_INT ca1_truck ca1_truck_blip ca1_punk ca1_punk_health 
			LVAR_FLOAT ca1_truck_x ca1_truck_y ca1_truck_z ca1_truck_h
			LVAR_FLOAT ca1_truck_h_add ca1_truck_h_start ca1_truck_h_end ca1_truck_h_diff
			LOAD_SCENE ca1_truck_x ca1_truck_y ca1_truck_z
			LOAD_SPECIAL_CHARACTER 1 sindaco
			REQUEST_MODEL FELTZER
			REQUEST_ANIMATION CAR


			LOAD_ALL_MODELS_NOW
			WHILE NOT HAS_MODEL_LOADED FELTZER 
			OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
			OR NOT HAS_ANIMATION_LOADED CAR
				WAIT 0
			ENDWHILE
		RETURN
		ca1_truck_create:
		ca1_truck_x = 1903.8574
		ca1_truck_y = 974.1888 
		ca1_truck_z = 9.8127 
		ca1_truck_h = 177.4801
		SET_RADIO_CHANNEL RS_COUNTRY
		CREATE_CAR FELTZER ca1_truck_x ca1_truck_y ca1_truck_z ca1_truck
		SET_CAR_HEADING ca1_truck ca1_truck_h
		SET_CAN_BURST_CAR_TYRES ca1_truck FALSE
		//SET_CAR_CAN_BE_VISIBLY_DAMAGED ca1_truck FALSE
		CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 0.0 0.0 0.0 ca1_punk 
		ATTACH_CHAR_TO_CAR ca1_punk ca1_truck 0.0 1.266 0.391 FACING_FORWARD 0.0 WEAPONTYPE_UNARMED
		SET_CHAR_HEALTH ca1_punk 300
		TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_low CAR 4.0 FALSE FALSE FALSE TRUE 0
		//TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_low CAR 4.0 FALSE FALSE FALSE TRUE 2000
		LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY ca1_punk_decision_idx
		SET_CHAR_DECISION_MAKER ca1_punk ca1_punk_decision_idx
		CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE ca1_punk_decision_idx EVENT_POTENTIAL_GET_RUN_OVER
		CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE ca1_punk_decision_idx EVENT_POTENTIAL_WALK_INTO_PED
		CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE ca1_punk_decision_idx EVENT_POTENTIAL_WALK_INTO_VEHICLE
		SET_CAR_HEALTH ca1_truck 1000
		RETURN
		ADD_BLIP_FOR_CAR ca1_truck ca1_truck_blip
		ADD_BLIP_FOR_COORD ca1_punk_drop_x ca1_punk_drop_y ca1_punk_drop_z ca1_punk_drop_blip

		ca1_truck_delete:
		MARK_MODEL_AS_NO_LONGER_NEEDED FELTZER
		RETURN

		ca1_goon_create2:
			LVAR_INT ca1_goon ca1_goon_flag	ca1_goon_status
			LVAR_FLOAT ca1_goon_x ca1_goon_y ca1_goon_z ca1_goon_h
			REQUEST_MODEL TRIADB
			WHILE NOT HAS_MODEL_LOADED TRIADB 
				WAIT 0
			ENDWHILE
			ca1_goon_x = 1911.0243
			ca1_goon_y = 956.8084 
			ca1_goon_z = 9.8203 
			ca1_goon_h = 95.5462 
			CREATE_CHAR PEDTYPE_CIVMALE TRIADB ca1_goon_x ca1_goon_y ca1_goon_z ca1_goon
			SET_CHAR_HEADING ca1_goon ca1_goon_h 
		RETURN
	   	ca1_goon_delete:
	   		DELETE_CHAR ca1_goon	
	   	RETURN

// ------------------------------------------------------------------------------------------------
// Start Mission
mission_start_ca1:
	REGISTER_MISSION_GIVEN
	flag_player_on_mission = 1
	WAIT 0
// ------------------------------------------------------------------------------------------------
// Initialize Variables
// ---- Flags
	ca1_stage = -1
	ca1_cut = 0
	ca1_in_car = 0

	ca1_dialogue_playing = 0
	ca1_random_dialogue = 0

	ca1_anim_step = 0
	ca1_miss = 0
	ca1_handbrake = 0
	ca1_air = 0
	ca1_burnout = 0
	ca1_speed = 0
	ca1_stunt = 0
	ca1_swerve = 0
	ca1_swerve_anim = 0
	ca1_l_x_prev = 0
	ca1_freeway = 0
	ca1_help_counter = 0
	
	ca1_spooked = 0
	ca1_speed_counter = 0
	ca1_burnout_counter = 0
	ca1_swerve_spook_count = 0

	ca1_goon_flag = 1
// ---- Sequences

// ---- Counter Var
	ca1_count_var_fl = 0.1
// ---- Coords
	ca1_punk_drop_x = 1904.75 
	ca1_punk_drop_y = 960.74 
	ca1_punk_drop_z = 9.36
// ---- Camera Coords
	ca1_cam_x[0] = 1427.4 
	ca1_cam_y[0] = 2666.4
	ca1_cam_z[0] = 13.01

// ---- Dialogue Flags
	ca1_audio_slot = 1
	ca1_alt_slot = 2
	ca1_counter = 0
	ca1_ahead_counter = 1
	ca1_audio_playing = 0


// ---- Dialogue Text
	$ca1_text[1] = CAS1_AA // You, you're crazy! You're a dead man, A DEAD MAN!
	$ca1_text[2] = CAS1_AB // You're in no position to make threats.
	$ca1_text[3] = CAS1_AC // Who are you?
	$ca1_text[4] = CAS1_AD // Fuck you, man!
	$ca1_text[5] = CAS1_AE // Have it your way.

	$ca1_text[6] = CAS1_BA // You're messing with the wrong people!
	$ca1_text[7] = CAS1_BB // You are so dead, do you hear me? DEAD!
	$ca1_text[8] = CAS1_BC // You've signed your own death warrant!
	$ca1_text[9] = CAS1_BD // You think this is scaring me, huh?
	$ca1_text[10] = CAS1_BE // It takes more than a little drive to get ME blabbing!

	$ca1_text[11] = CAS1_CA // L-look, we can come to some arrangement!
	$ca1_text[12] = CAS1_CB // Ok, I can see you're serious!
	$ca1_text[13] = CAS1_CC // Look, pal, this ain't funny no more!
	$ca1_text[14] = CAS1_CD // Hey! Stop this insanity RIGHT NOW!
	$ca1_text[15] = CAS1_CE // Oh my god, you're a maniac!

	$ca1_text[16] = CAS1_DA // Aargh! I don't wanna die, I don't wana die!
	$ca1_text[17] = CAS1_DB // FUCK YOU! FUCK YOU-HOO-hooooo! (descends into sobbing)
	$ca1_text[18] = CAS1_DC // Oh god, my bowels just let go!
	$ca1_text[19] = CAS1_DD // Oh Mother Mary, I've had enough, oh god...
	$ca1_text[20] = CAS1_DE // Ok, you've made your point, No More!

	$ca1_text[21] = CAS1_EA // Oh man, this is fast!
	$ca1_text[22] = CAS1_EB // Slow down!
	$ca1_text[23] = CAS1_EC // Hell this is fast!
	$ca1_text[24] = CAS1_ED // Holy Fuck!
	$ca1_text[25] = CAS1_EE // Mother Mary!
	$ca1_text[26] = CAS1_EF // Oh Christ! OH CHRIST!
	$ca1_text[27] = CAS1_EG // Oh my god, oh-my-god!
	$ca1_text[28] = CAS1_EH // Hey, whatcha doing?
	$ca1_text[29] = CAS1_EI // You think you're smart, BUT YOU'RE NOT!
	$ca1_text[30] = CAS1_EJ // This shit don't scare me, ashole!
	$ca1_text[31] = CAS1_EK // Where you going now?!
	$ca1_text[32] = CAS1_EL // Somebody, anybody, HELP!
	$ca1_text[33] = CAS1_EM // Mind the pedestrians!
	$ca1_text[34] = CAS1_EN // Jesus Christ that was close!
	$ca1_text[35] = CAS1_EO // You trying to fuck us both?
	$ca1_text[36] = CAS1_EP // Mother Mary, that was lucky!
	$ca1_text[37] = CAS1_EQ // You're gonnna kill us both!
	//$ca1_text[38] = CAS1_ER // Oh God, I'm still alive!
	$ca1_text[39] = CAS1_ES // Thank god! THANK GOD!
	$ca1_text[40] = CAS1_ET // Jesus! JESUS!
	$ca1_text[41] = CAS1_EU // Are you TRYING to hit things?!
	$ca1_text[42] = CAS1_EV // Oh...jesus....fuck you...
	//$ca1_text[43] = CAS1_EW // Aarrgghh!
	$ca1_text[44] = CAS1_EX // Mother... is that you..?
	$ca1_text[45] = CAS1_EY // Holy fuck! My legs are on fire!
	$ca1_text[46] = CAS1_EZ // Shit, my crotch! MY CROTCH!
	$ca1_text[47] = CAS1_FA // Holy Mother, we're on fire!
	$ca1_text[48] = CAS1_FB // FIRE! FIRE!

	$ca1_text[49] = CAS1_GA // The family will make you pay for this!
	$ca1_text[50] = CAS1_GB // Which family?
	$ca1_text[51] = CAS1_GC // The Sindacco family, you sick maniac!
	$ca1_text[52] = CAS1_GD // That's all I wanted to hear!
	$ca1_text[53] = CAS1_GE // What the-? Oh shit...

	$ca1_text[54] = CAS1_HA // Oh god, thank god...
	$ca1_text[55] = CAS1_HB // Though I walk in the shadow of the valley of death...
	$ca1_text[56] = CAS1_HC // Mummy... mummy...
	$ca1_text[57] = CAS1_HD // (whimpering)
	$ca1_text[58] = CAS1_HE // I've lead such a sinful life, I'm sorry, so sorry...

	$ca1_text[59] = CAS1_JA // Whoa, CJ, he's messed himself!
	$ca1_text[60] = CAS1_JB // Yeah he should blab like a baby, now!

	$ca1_text[61] = CAS1b00 // Hey, who the fuck are you? Untie me, I've been here for ages.
	$ca1_text[62] = CAS1b02 // We're gonna take a little drive.
	$ca1_text[63] = CAS1b03 // H-hey, don't jerk me around here, you've had your fun.
	$ca1_text[64] = CAS1b04 // No, I think I'll leave you right where you are.

	$ca1_text[65] = CAS1b05 // You got any idea in that pea brain head of yours who the fuck I am?
	$ca1_text[66] = CAS1b06 // No, but I'm going to find out.


// ---- Dialogue Audio
	ca1_audio[1] = SOUND_CAS1_AA // You, you're crazy! You're a dead man, A DEAD MAN!
	ca1_audio[2] = SOUND_CAS1_AB // You're in no position to make threats.
	ca1_audio[3] = SOUND_CAS1_AC // Who are you?
	ca1_audio[4] = SOUND_CAS1_AD // Fuck you, man!
	ca1_audio[5] = SOUND_CAS1_AE // Have it your way.

	ca1_audio[6] = SOUND_CAS1_BA // You're messing with the wrong people!
	ca1_audio[7] = SOUND_CAS1_BB // You are so dead, do you hear me? DEAD!
	ca1_audio[8] = SOUND_CAS1_BC // You've signed your own death warrant!
	ca1_audio[9] = SOUND_CAS1_BD // You think this is scaring me, huh?
	ca1_audio[10] = SOUND_CAS1_BE // It takes more than a little drive to get ME blabbing!

	ca1_audio[11] = SOUND_CAS1_CA // L-look, we can come to some arrangement!
	ca1_audio[12] = SOUND_CAS1_CB // Ok, I can see you're serious!
	ca1_audio[13] = SOUND_CAS1_CC // Look, pal, this ain't funny no more!
	ca1_audio[14] = SOUND_CAS1_CD // Hey! Stop this insanity RIGHT NOW!
	ca1_audio[15] = SOUND_CAS1_CE // Oh my god, you're a maniac!

	ca1_audio[16] = SOUND_CAS1_DA // Aargh! I don't wanna die, I don't wana die!
	ca1_audio[17] = SOUND_CAS1_DB // FUCK YOU! FUCK YOU-HOO-hooooo! (descends into sobbing)
	ca1_audio[18] = SOUND_CAS1_DC // Oh god, my bowels just let go!
	ca1_audio[19] = SOUND_CAS1_DD // Oh Mother Mary, I've had enough, oh god...
	ca1_audio[20] = SOUND_CAS1_DE // Ok, you've made your point, No More!

	ca1_audio[21] = SOUND_CAS1_EA // Oh man, this is fast!
	ca1_audio[22] = SOUND_CAS1_EB // Slow down!
	ca1_audio[23] = SOUND_CAS1_EC // Hell this is fast!
	ca1_audio[24] = SOUND_CAS1_ED // Holy Fuck!
	ca1_audio[25] = SOUND_CAS1_EE // Mother Mary!
	ca1_audio[26] = SOUND_CAS1_EF // Oh Christ! OH CHRIST!
	ca1_audio[27] = SOUND_CAS1_EG // Oh my god, oh-my-god!
	ca1_audio[28] = SOUND_CAS1_EH // Hey, whatcha doing?
	ca1_audio[29] = SOUND_CAS1_EI // You think you're smart, BUT YOU'RE NOT!
	ca1_audio[30] = SOUND_CAS1_EJ // This shit don't scare me, ashole!
	ca1_audio[31] = SOUND_CAS1_EK // Where you going now?!
	ca1_audio[32] = SOUND_CAS1_EL // Somebody, anybody, HELP!
	ca1_audio[33] = SOUND_CAS1_EM // Mind the pedestrians!
	ca1_audio[34] = SOUND_CAS1_EN // Jesus Christ that was close!
	ca1_audio[35] = SOUND_CAS1_EO // You trying to fuck us both?
	ca1_audio[36] = SOUND_CAS1_EP // Mother Mary, that was lucky!
	ca1_audio[37] = SOUND_CAS1_EQ // You're gonnna kill us both!
	//ca1_audio[38] = SOUND_CAS1_ER // Oh God, I'm still alive!
	ca1_audio[39] = SOUND_CAS1_ES // Thank god! THANK GOD!
	ca1_audio[40] = SOUND_CAS1_ET // Jesus! JESUS!
	ca1_audio[41] = SOUND_CAS1_EU // Are you TRYING to hit things?!
	ca1_audio[42] = SOUND_CAS1_EV // Oh...jesus....fuck you...
	//ca1_audio[43] = SOUND_CAS1_EW // Aarrgghh!
	ca1_audio[44] = SOUND_CAS1_EX // Mother... is that you..?
	ca1_audio[45] = SOUND_CAS1_EY // Holy fuck! My legs are on fire!
	ca1_audio[46] = SOUND_CAS1_EZ // Shit, my crotch! MY CROTCH!
	ca1_audio[47] = SOUND_CAS1_FA // Holy Mother, we're on fire!
	ca1_audio[48] = SOUND_CAS1_FB // FIRE! FIRE!

	ca1_audio[49] = SOUND_CAS1_GA // The family will make you pay for this!
	ca1_audio[50] = SOUND_CAS1_GB // Which family?
	ca1_audio[51] = SOUND_CAS1_GC // The Sindacco family, you sick maniac!
	ca1_audio[52] = SOUND_CAS1_GD // That's all I wanted to hear!
	ca1_audio[53] = SOUND_CAS1_GE // What the-? Oh shit...

	ca1_audio[54] = SOUND_CAS1_HA // Oh god, thank god...
	ca1_audio[55] = SOUND_CAS1_HB // Though I walk in the shadow of the valley of death...
	ca1_audio[56] = SOUND_CAS1_HC // Mummy... mummy...
	ca1_audio[57] = SOUND_CAS1_HD // (whimpering)
	ca1_audio[58] = SOUND_CAS1_HE // I've lead such a sinful life, I'm sorry, so sorry...

	ca1_audio[59] = SOUND_CAS1_JA // Whoa, CJ, he's messed himself!
	ca1_audio[60] = SOUND_CAS1_JB // Yeah he should blab like a baby, now!

	ca1_audio[61] = SOUND_CAS1b00 // Hey, who the fuck are you? Untie me, I've been here for ages.
	ca1_audio[62] = SOUND_CAS1b02 // We're gonna take a little drive.
	ca1_audio[63] = SOUND_CAS1b03 // H-hey, don't jerk me around here, you've had your fun.
	ca1_audio[64] = SOUND_CAS1b04 // No, I think I'll leave you right where you are.

	ca1_audio[65] = SOUND_CAS1b05 // Hey, HEY! Don't be fucking stupid! Do you know who I am?
	ca1_audio[66] = SOUND_CAS1b06 // No, but I'm going to find out.



// ------------------------------------------------------------------------------------------------
// Request Models 
LOAD_MISSION_TEXT CASINO1
SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
DO_FADE 500 FADE_OUT 
WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE 
// ------------------------------------------------------------------------------------------------
// ---- Cuts
// Cut #2

//LOAD_CUTSCENE CAS_1b
// 
//WHILE NOT HAS_CUTSCENE_LOADED
//WAIT 0
//ENDWHILE
// 
//START_CUTSCENE
//
//DO_FADE 1000 FADE_IN
//  
//WHILE NOT HAS_CUTSCENE_FINISHED
//WAIT 0
//ENDWHILE
//
//DO_FADE 0 FADE_OUT
//WHILE GET_FADING_STATUS
//WAIT 0
//ENDWHILE
//
// 
//CLEAR_CUTSCENE
//
//SET_PLAYER_CONTROL player1 OFF

// Cut #1
//SET_CHAR_COORDINATES scplayer 1907.8259 976.4828 9.8127
//SET_CHAR_HEADING scplayer 136.9741
SET_AREA_VISIBLE 11

LOAD_CUTSCENE CAS_1a
 
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

SET_PLAYER_CONTROL player1 OFF
ALTER_WANTED_LEVEL player1 0
SET_AREA_VISIBLE 0
SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2016.4222 1017.1024 10.0 //TRIAD CASINO

SET_FIRST_PERSON_IN_CAR_CAMERA_MODE FALSE

//LOAD_SCENE ca1_truck_x ca1_truck_y ca1_truck_z
SET_FIXED_CAMERA_POSITION 1908.1558 977.3071 10.2294 0.0 0.0 0.0
POINT_CAMERA_AT_POINT 1907.4884 976.5921 10.4377 JUMP_CUT
CLEAR_AREA 1903.2698 955.9500 9.8203 30.0 TRUE
SET_CHAR_COORDINATES scplayer 1909.8259 977.4828 9.8127
SET_CHAR_HEADING scplayer 136.9741

//WHILE IS_CHAR_WAITING_FOR_WORLD_COLLISION scplayer
//WAIT 0
//ENDWHILE
//SET_CHAR_COORDINATES scplayer 1909.8259 977.4828 9.8127
//SET_CHAR_HEADING scplayer 136.9741



REQUEST_MODEL trdcsgrgdoor_lvs
LOAD_ALL_MODELS_NOW
WHILE NOT HAS_MODEL_LOADED trdcsgrgdoor_lvs
WAIT 0
ENDWHILE
GOSUB ca1_truck_request
GOSUB ca1_truck_create
//LOAD_SCENE ca1_truck_x ca1_truck_y ca1_truck_z
LOAD_SCENE_IN_DIRECTION	1908.1558 977.3071 10.2294 136.9741

// Opening cutscene stuff
/*
Player walks to: 1905.4496 973.9371 9.8127 136.9741
Second camera position: 
Final camera position: 
*/
//SET_PLAYER_CONTROL player1 OFF
SWITCH_WIDESCREEN ON

WAIT 150
DO_FADE 1500 FADE_IN 
WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE 
IF NOT IS_CHAR_DEAD ca1_punk
	TASK_LOOK_AT_CHAR scplayer ca1_punk 100000
ENDIF
ca1_scripted_cut:
WAIT 0
// ---- Load & Play Dialogue...
IF NOT ca1_counter = 0
	IF ca1_audio_playing = 0
		IF HAS_MISSION_AUDIO_LOADED ca1_alt_slot
			CLEAR_MISSION_AUDIO ca1_alt_slot
		ENDIF
		ca1_audio_playing = 1
	ENDIF

	IF ca1_audio_playing = 1
		LOAD_MISSION_AUDIO ca1_audio_slot ca1_audio[ca1_counter]
//		GOSUB ca1_dialogue_pos
//		ATTACH_MISSION_AUDIO_TO_PED ca1_audio_slot ca1_audio_char
		
		ca1_audio_playing = 2
	ENDIF

	IF ca1_audio_playing = 2
	 	IF HAS_MISSION_AUDIO_LOADED ca1_audio_slot
			IF ca1_counter = 61
			OR ca1_counter = 63
				IF NOT IS_CHAR_DEAD	ca1_punk
					START_CHAR_FACIAL_TALK ca1_punk 10000
				ENDIF
			ENDIF
			IF ca1_counter = 62
			OR ca1_counter = 64
				IF NOT IS_CHAR_DEAD	scplayer
					START_CHAR_FACIAL_TALK scplayer 10000
				ENDIF
			ENDIF
			PLAY_MISSION_AUDIO ca1_audio_slot
			PRINT_NOW $ca1_text[ca1_counter] 10000 1
			ca1_audio_playing = 3
		ENDIF
	ENDIF

	IF ca1_audio_playing = 3
		IF HAS_MISSION_AUDIO_FINISHED ca1_audio_slot
			CLEAR_THIS_PRINT $ca1_text[ca1_counter]
			IF ca1_counter = 61
			OR ca1_counter = 63
				IF NOT IS_CHAR_DEAD	ca1_punk
					STOP_CHAR_FACIAL_TALK ca1_punk
				ENDIF
			ENDIF
			IF ca1_counter = 62
			OR ca1_counter = 64
				IF NOT IS_CHAR_DEAD	scplayer
					STOP_CHAR_FACIAL_TALK scplayer
				ENDIF
			ENDIF
			IF ca1_audio_slot = 1
				ca1_audio_slot = 2
				ca1_alt_slot = 1
			ELSE
				ca1_audio_slot = 1
				ca1_alt_slot = 2
			ENDIF
			ca1_counter = 0
			ca1_audio_playing = 0
			IF ca1_cut = 0
				ca1_cut = 1
			ENDIF
			IF ca1_cut = 4
				ca1_cut = 5
			ENDIF

			IF ca1_cut = 3
				ca1_cut = 4
			ENDIF
			IF ca1_cut = 7
				ca1_cut = 8
			ENDIF
		ELSE
			IF NOT HAS_MISSION_AUDIO_LOADED ca1_alt_slot
				IF ca1_counter < 66
					ca1_ahead_counter = ca1_counter + 1
					LOAD_MISSION_AUDIO ca1_alt_slot ca1_audio[ca1_ahead_counter]
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF  


IF NOT IS_CHAR_DEAD ca1_punk
	IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_low
		GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_low ca1_anim_time
		IF ca1_anim_time = 1.0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_low CAR 4.0 FALSE FALSE FALSE TRUE 0 // need loop length
		ENDIF
	ENDIF
ENDIF

IF ca1_cut > 0
	IF IS_BUTTON_PRESSED PAD1 CROSS 
		ca1_cut = 10
	ENDIF
ENDIF


IF ca1_cut = 0
	IF ca1_audio_playing = 0
	AND ca1_counter = 0
		ca1_counter = 61 
	ENDIF
ENDIF

IF ca1_cut = 1
	TASK_GO_STRAIGHT_TO_COORD scplayer 1905.4496 973.9371 9.8127 PEDMOVE_WALK -2

	ca1_cut = 2	
ENDIF

IF ca1_cut = 2
	GET_SCRIPT_TASK_STATUS scplayer TASK_GO_STRAIGHT_TO_COORD ca1_player_status
	IF ca1_player_status = FINISHED_TASK
		IF NOT IS_CHAR_DEAD ca1_punk
			TASK_TURN_CHAR_TO_FACE_CHAR scplayer ca1_punk
			SET_FIXED_CAMERA_POSITION 1903.0051 974.7813 11.1887 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 1903.7317 974.0975 11.2553 JUMP_CUT
			ca1_cut = 3
		ENDIF	
	ENDIF
ENDIF

IF ca1_cut = 3
	IF ca1_audio_playing = 0
	AND ca1_counter = 0
		ca1_counter = 62 
	ENDIF
ENDIF

IF ca1_cut = 4
OR ca1_cut = 5
	SLIDE_OBJECT fourdragons_door 1903.383 967.62 15.438 0.0 0.0 0.1 FALSE
ENDIF

IF ca1_cut = 9
	SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.05 FALSE
ENDIF

IF ca1_cut = 4
	IF ca1_audio_playing = 0
	AND ca1_counter = 0
		ca1_counter = 63 
	ENDIF
ENDIF

IF ca1_cut = 5
	IF NOT IS_CAR_DEAD ca1_truck
		TASK_ENTER_CAR_AS_DRIVER scplayer ca1_truck -2
		ca1_cut = 6
	ENDIF
ENDIF

IF ca1_cut = 6
	GET_SCRIPT_TASK_STATUS scplayer TASK_ENTER_CAR_AS_DRIVER ca1_player_status
	IF ca1_player_status = FINISHED_TASK
		SET_FIXED_CAMERA_POSITION 1906.5278 970.1409 10.4330 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 1905.8225 970.8365 10.5694 JUMP_CUT
		SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.1 FALSE
		ca1_cut = 7
	ENDIF
ENDIF

IF ca1_cut = 7
	IF ca1_audio_playing = 0
	AND ca1_counter = 0
		ca1_counter = 64 
	ENDIF
ENDIF

IF ca1_cut = 8
	IF NOT IS_CAR_DEAD ca1_truck
		TASK_CAR_DRIVE_TO_COORD scplayer ca1_truck 1903.2698 955.9500 9.8203 10.0 MODE_ACCURATE FALSE DRIVINGMODE_AVOIDCARS
		ca1_cut = 9
	ENDIF
ENDIF

IF ca1_cut = 9
	GET_SCRIPT_TASK_STATUS scplayer TASK_CAR_DRIVE_TO_COORD ca1_player_status
	IF ca1_player_status = FINISHED_TASK
		ca1_cut = 10
	ENDIF
ENDIF


IF NOT ca1_cut = 10
	GOTO ca1_scripted_cut
ENDIF

//DO_FADE 250 FADE_OUT 
//WHILE GET_FADING_STATUS
//	WAIT 0
//ENDWHILE 

IF NOT IS_CAR_DEAD ca1_truck
	SET_OBJECT_COORDINATES fourdragons_door 1903.383 967.82 11.438
	SET_CAR_COORDINATES ca1_truck 1903.2698 955.9500 9.8203
	SET_CAR_HEADING ca1_truck 180.7793
	IF IS_CHAR_IN_CAR scplayer ca1_truck
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 1903.2698 955.9500 9.8203	
	ENDIF
	WARP_CHAR_INTO_CAR scplayer ca1_truck
ENDIF 
MARK_MODEL_AS_NO_LONGER_NEEDED trdcsgrgdoor_lvs
SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT
CLEAR_LOOK_AT scplayer
SWITCH_WIDESCREEN OFF
//DO_FADE 250 FADE_IN 
//WHILE GET_FADING_STATUS
//	WAIT 0
//ENDWHILE
SET_PLAYER_CONTROL player1 ON

ca1_cut = 0


//VIEW_INTEGER_VARIABLE ca1_test ca1_test
// ------------------------------------------------------------------------------------------------
// Main Loop
ca1_main_loop:

WAIT 0

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	GOTO mission_passed_ca1
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SPACE
	ca1_spooked = 100
ENDIF

// ---- Load & Play Dialogue...
IF NOT ca1_counter = 0
	IF ca1_audio_playing = 0
		IF HAS_MISSION_AUDIO_LOADED ca1_alt_slot
			CLEAR_MISSION_AUDIO ca1_alt_slot
		ENDIF
		ca1_audio_playing = 1
	ENDIF

	IF ca1_audio_playing = 1
		LOAD_MISSION_AUDIO ca1_audio_slot ca1_audio[ca1_counter]
//		GOSUB ca1_dialogue_pos
//		ATTACH_MISSION_AUDIO_TO_PED ca1_audio_slot ca1_audio_char
		ca1_audio_playing = 2
	ENDIF

	IF ca1_audio_playing = 2
	 	IF HAS_MISSION_AUDIO_LOADED ca1_audio_slot
			PLAY_MISSION_AUDIO ca1_audio_slot
			PRINT_NOW $ca1_text[ca1_counter] 10000 1
			ca1_audio_playing = 3
		ENDIF
	ENDIF

	IF ca1_audio_playing = 3
		IF HAS_MISSION_AUDIO_FINISHED ca1_audio_slot
			CLEAR_THIS_PRINT $ca1_text[ca1_counter]
			IF ca1_audio_slot = 1
				ca1_audio_slot = 2
				ca1_alt_slot = 1
			ELSE
				ca1_audio_slot = 1
				ca1_alt_slot = 2
			ENDIF
			ca1_counter = 0
			ca1_audio_playing = 0
		ELSE
			IF NOT HAS_MISSION_AUDIO_LOADED ca1_alt_slot
				IF ca1_counter < 66
					ca1_ahead_counter = ca1_counter + 1
					LOAD_MISSION_AUDIO ca1_alt_slot ca1_audio[ca1_ahead_counter]
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF  


IF IS_CHAR_IN_ZONE scplayer PINT
OR IS_CHAR_IN_ZONE scplayer BINT
OR IS_CHAR_IN_ZONE scplayer JTN
OR IS_CHAR_IN_ZONE scplayer JTS
OR IS_CHAR_IN_ZONE scplayer JTW
OR IS_CHAR_IN_ZONE scplayer JTE
	IF ca1_dialogue_playing = 0
	AND ca1_freeway = 0
		PRINT ( CAS1_10 ) 5000 1 // Stay off the freeway
		ALTER_WANTED_LEVEL_NO_DROP player1 3
		ca1_freeway = 1
		//ca1_dialogue_playing = 1
	ENDIF
	IF ca1_freeway = 1
		IF NOT IS_WANTED_LEVEL_GREATER player1 2
			ca1_freeway = 0
		ENDIF
	ENDIF
ENDIF



IF ca1_stage = -1
	SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.05 FALSE
	IF flag_cas1_passed_1stime = 0
		IF NOT IS_CHAR_DEAD scplayer
		AND NOT IS_CAR_DEAD ca1_truck
			IF IS_CHAR_IN_CAR scplayer ca1_truck
				IF ca1_random_dialogue = 0
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 65	
						ca1_random_dialogue ++
						GET_GAME_TIMER ca1_text_timer_start

					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						GET_GAME_TIMER ca1_text_timer_start								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 1
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 66	
						ca1_random_dialogue ++
						ca1_stage = 0
						GET_GAME_TIMER ca1_text_timer_start
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						GET_GAME_TIMER ca1_text_timer_start								
					ENDIF 
				ENDIF
//				IF ca1_random_dialogue = 2
//					IF ca1_audio_playing = 0
//					AND ca1_counter = 0
//						ca1_counter = 3 // CARL: Who are you?
//						ca1_random_dialogue ++
//						GET_GAME_TIMER ca1_text_timer_start
//					ENDIF
//					GET_GAME_TIMER ca1_text_timer_end 
//					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
//					IF ca1_text_timer_diff > 500
//						GET_GAME_TIMER ca1_text_timer_start								
//					ENDIF 
//				ENDIF
//				IF ca1_random_dialogue = 3
//					IF ca1_audio_playing = 0
//					AND ca1_counter = 0
//						ca1_counter = 4	// JOHNNY SINDACCO: Fuck you, man!
//						ca1_random_dialogue ++
//						GET_GAME_TIMER ca1_text_timer_start
//					ENDIF
//					GET_GAME_TIMER ca1_text_timer_end 
//					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
//					IF ca1_text_timer_diff > 500
//						GET_GAME_TIMER ca1_text_timer_start								
//					ENDIF 
//				ENDIF
//				IF ca1_random_dialogue = 4
//					IF ca1_audio_playing = 0
//					AND ca1_counter = 0
//						ca1_counter = 5	// CARL: Have it your way.
//						ca1_random_dialogue ++
//						ca1_stage = 0
//						GET_GAME_TIMER ca1_text_timer_start
//					ENDIF
//					GET_GAME_TIMER ca1_text_timer_end 
//					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
//					IF ca1_text_timer_diff > 500
//						GET_GAME_TIMER ca1_text_timer_start
//														
//					ENDIF 
//				ENDIF
			ENDIF
		ENDIF
	ELSE
		SET_OBJECT_COORDINATES fourdragons_door 1903.383 967.82 11.438
		ca1_stage = 0		 
	ENDIF
ENDIF

// Taking Hef's car...
IF ca1_stage = 0
	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CAR_DEAD ca1_truck
		IF IS_CHAR_IN_CAR scplayer ca1_truck
			IF ca1_audio_playing = 0
			AND ca1_counter = 0
				CLEAR_PRINTS
				PRINT ( CAS1_11 ) 5000 1 // Scare the shit out of him, but don't kill him. 
				PRINT ( CAS1_12 ) 5000 1
				ca1_dialogue_playing = 2
				ca1_stage = 2
				ca1_in_car = 1
			ENDIF
		ENDIF	 
	ENDIF
ENDIF

// Making sure the player stays in the car...
IF ca1_stage < 5

	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CAR_DEAD ca1_truck
		
		IF ca1_in_car = 0
			
			IF IS_CHAR_SITTING_IN_CAR scplayer ca1_truck
				CLEAR_PRINTS
				SET_FIRST_PERSON_IN_CAR_CAMERA_MODE FALSE
				GET_GAME_TIMER ca1_text_timer_start
				REMOVE_BLIP ca1_truck_blip
				REMOVE_BLIP ca1_punk_drop_blip
				IF ca1_stage > 0
				AND ca1_stage < 4
					CLEAR_ONSCREEN_COUNTER ca1_spooked
					DISPLAY_ONSCREEN_COUNTER_WITH_STRING ca1_spooked COUNTER_DISPLAY_BAR ( CAS1_05 )//( CAS1_05 ) // Spook-O-Meter
				ENDIF
				IF ca1_stage = 4
					ADD_BLIP_FOR_COORD ca1_punk_drop_x ca1_punk_drop_y ca1_punk_drop_z ca1_punk_drop_blip
				ENDIF
				ca1_in_car = 1
			ENDIF
		ENDIF	
		IF ca1_in_car = 1
		   	IF NOT IS_CHAR_SITTING_IN_CAR scplayer ca1_truck
				CLEAR_PRINTS
				SET_FIRST_PERSON_IN_CAR_CAMERA_MODE TRUE
				REMOVE_BLIP ca1_truck_blip
				ADD_BLIP_FOR_CAR ca1_truck ca1_truck_blip
				SET_BLIP_AS_FRIENDLY ca1_truck_blip	TRUE
				IF ca1_stage > 0
				AND ca1_stage < 4
					CLEAR_ONSCREEN_COUNTER ca1_spooked
				ENDIF
				IF ca1_stage = 4
					REMOVE_BLIP ca1_punk_drop_blip
				ENDIF
				PRINT_NOW ( CAS1_01 ) 7500 2 // Get back in the car
			 	ca1_in_car = 0 
		  	ENDIF	 
		ENDIF
	ENDIF
ENDIF

// 
IF ca1_stage = 2
	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD ca1_punk
		DISPLAY_ONSCREEN_COUNTER_WITH_STRING ca1_spooked COUNTER_DISPLAY_BAR ( CAS1_05 )//( CAS1_05 ) // Spook-O-Meter
		GENERATE_RANDOM_INT_IN_RANGE 0 4 ca1_choice
		IF ca1_choice = 0
			PRINT_HELP ( CAS1_06 )
			ca1_stage = 3
		ENDIF
		IF ca1_choice = 1
			PRINT_HELP ( CAS1_07 )
			ca1_stage = 3
		ENDIF
		IF ca1_choice = 2
			PRINT_HELP ( CAS1_08 )
			ca1_stage = 3
		ENDIF
		IF ca1_choice = 3
			PRINT_HELP ( CAS1_09 )
			ca1_stage = 3
		ENDIF
		GET_GAME_TIMER ca1_h_timer_start
	ENDIF
ENDIF 

// Start spooking...
IF ca1_stage = 3
	IF NOT IS_CAR_DEAD ca1_truck
	AND NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD ca1_punk
		IF ca1_goon_flag = 0
			IF NOT IS_CHAR_DEAD ca1_goon
				IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 1910.75 960.74 10.86 15.0 15.0 15.0 FALSE
					IF NOT IS_CHAR_ON_SCREEN ca1_goon
						GOSUB ca1_goon_delete
						ca1_goon_flag = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF	
		IF IS_CAR_DOOR_DAMAGED ca1_truck BONNET
			TASK_DIE ca1_punk
			DETACH_CHAR_FROM_CAR ca1_punk
			PRINT_NOW ( CAS1_04 ) 5000 1 // You killed him!
			GOTO mission_failed_ca1
		ENDIF
		IF IS_CHAR_IN_CAR scplayer ca1_truck
			IF ca1_help_counter < 8
				// Help text
				GET_GAME_TIMER ca1_h_timer_end
				ca1_h_timer_diff = ca1_h_timer_end - ca1_h_timer_start
				IF ca1_h_timer_diff > 30000
					GENERATE_RANDOM_INT_IN_RANGE 0 4 ca1_choice
					IF ca1_choice = 0
						PRINT_HELP ( CAS1_06 )
						GET_GAME_TIMER ca1_h_timer_start
					ENDIF
					IF ca1_choice = 1
						PRINT_HELP ( CAS1_07 )
						GET_GAME_TIMER ca1_h_timer_start
					ENDIF
					IF ca1_choice = 2
						PRINT_HELP ( CAS1_08 )
						GET_GAME_TIMER ca1_h_timer_start
					ENDIF
					IF ca1_choice = 3
						PRINT_HELP ( CAS1_09 )
						GET_GAME_TIMER ca1_h_timer_start
					ENDIF
					ca1_help_counter++
				ENDIF
			ENDIF 
			// Limit
			IF ca1_spooked > 100
				ca1_spooked = 100
				ca1_random_dialogue = 0
				CLEAR_ONSCREEN_COUNTER ca1_spooked
				ADD_BLIP_FOR_COORD ca1_punk_drop_x ca1_punk_drop_y ca1_punk_drop_z ca1_punk_drop_blip
				GET_GAME_TIMER ca1_timer_start1
				ca1_stage = 4
			ENDIF	
//			// Countdown...
//			IF ca1_spooked >= 2
//				GET_GAME_TIMER ca1_timer_end1
//				ca1_timer_diff1 = ca1_timer_end1 - ca1_timer_start1
//				IF ca1_timer_diff1 > 3000
//					GET_GAME_TIMER ca1_timer_start1
//					ca1_spooked -= 2
//				ENDIF
//			ENDIF
			// Check for hand brake turns...
			IF ca1_handbrake = 0
			AND ca1_burnout = 0
			AND ca1_air = 0
				IF ca1_truck_speed > 15.0
					IF IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER1 // Should be changed to "IS_BRAKE_PRESSED"... ?
						GET_CAR_HEADING ca1_truck ca1_truck_h_start	
						ca1_truck_h_diff = 0.0
						ca1_handbrake = 1  
					ENDIF
				ENDIF
			ENDIF
			IF ca1_handbrake = 1
				IF IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER1
					GET_CAR_HEADING ca1_truck ca1_truck_h_end
					ca1_truck_h_add = ca1_truck_h_end - ca1_truck_h_start
					ca1_truck_h_diff += ca1_truck_h_add
					GET_CAR_HEADING ca1_truck ca1_truck_h_start
				ENDIF
				IF NOT IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER1
					
					IF ca1_truck_h_diff < 0.0
						ca1_truck_h_diff += 360.0
					ENDIF
					IF ca1_truck_h_diff > 0.0
						IF ca1_truck_h_diff > 14.0
						AND ca1_truck_h_diff < 210.0
							ca1_spooked += 7
						ELSE
							IF ca1_truck_h_diff > 90.0
							AND ca1_truck_h_diff < 270.0
								ca1_spooked += 3
							ENDIF	
						ENDIF
					ENDIF
					ca1_handbrake = 0
				ENDIF
			ENDIF
			// Check for jumps...
			IF ca1_air = 0
			AND ca1_handbrake = 0
			AND ca1_burnout = 0
				IF IS_CAR_IN_AIR_PROPER ca1_truck
					GET_GAME_TIMER ca1_timer_start
					ca1_air = 1
				ENDIF
			ENDIF
			IF ca1_air = 1 
				IF NOT IS_CAR_IN_AIR_PROPER ca1_truck
					GET_GAME_TIMER ca1_timer_end
					ca1_timer_diff = ca1_timer_end - ca1_timer_start
					ca1_timer_diff /= 250 
					ca1_spooked += ca1_timer_diff
					ca1_air = 0
				ENDIF
			ENDIF
			// Check for burnouts... ... Grrr
			IF ca1_burnout = 0
			AND ca1_handbrake = 0
			AND ca1_air = 0
			AND ca1_burnout_counter < 20
				IF ca1_truck_speed < 1.0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					AND IS_BUTTON_PRESSED PAD1 SQUARE
						GET_GAME_TIMER ca1_timer_end
						ca1_timer_diff = ca1_timer_end - ca1_timer_start
						IF ca1_timer_diff > 3000
							ca1_timer_diff_add_fl = 0.0
							ca1_count_var_fl = 0.15
							ca1_burnout = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF	
			IF ca1_burnout = 1
				GET_POSITION_OF_ANALOGUE_STICKS PAD1 ca1_l_x ca1_l_y ca1_r_x ca1_r_y
				IF NOT IS_BUTTON_PRESSED PAD1 SQUARE
					ca1_burnout = 0
				ENDIF
				IF NOT IS_BUTTON_PRESSED PAD1 CROSS
					ca1_burnout = 0
				ENDIF
				IF IS_BUTTON_PRESSED PAD1 CROSS
				AND IS_BUTTON_PRESSED PAD1 SQUARE
					IF NOT ca1_l_x = ca1_l_x_prev
						IF ca1_timer_diff_add_fl >= 0.0
						AND ca1_count_var_fl >= 0.0 
							GET_GAME_TIMER ca1_timer_start
							ca1_timer_diff_add_fl += ca1_count_var_fl
							ca1_count_var_fl -= 0.025
							ca1_timer_diff_add =# ca1_timer_diff_add_fl
							ca1_spooked += ca1_timer_diff_add
							ca1_burnout_counter += ca1_timer_diff_add
							ca1_l_x_prev = ca1_l_x
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			// Speed...
			GET_CAR_SPEED ca1_truck ca1_truck_speed
			IF ca1_speed = 0 
				IF ca1_truck_speed > 30.0
					IF ca1_speed_counter < 40
						GET_GAME_TIMER ca1_timer_start2
						ca1_speed = 1
					ENDIF
				ENDIF
			ENDIF
			IF ca1_speed = 1
				GET_GAME_TIMER ca1_timer_end2
				ca1_timer_diff2 = ca1_timer_end2 - ca1_timer_start2
				IF ca1_timer_diff2 > 600
					GET_GAME_TIMER ca1_timer_start2
					ca1_speed_counter += 1
					ca1_spooked += 1
				ENDIF
				IF ca1_truck_speed < 30.0
					ca1_speed = 0
				ENDIF 
			ENDIF
			// Stunts (from hj.sc)...
			IF ca1_stunt = 0
				IF stunt_flags_hj > 0
					ca1_count_var = cash_reward / 5 
					ca1_spooked += ca1_count_var
					ca1_stunt = 1
				ENDIF
			ENDIF
			IF ca1_stunt = 1
				IF stunt_flags_hj = 0
					ca1_stunt = 0
				ENDIF
			ENDIF 
			// Swerve
			IF ca1_swerve = 0
			AND ca1_brake = 0
				IF ca1_truck_speed > 15.0
					GET_POSITION_OF_ANALOGUE_STICKS PAD1 ca1_l_x ca1_l_y ca1_r_x ca1_r_y
					IF NOT ca1_l_x = 0
						ca1_swerve_counter++
					 	IF ca1_swerve_counter > 20
							IF ca1_l_x > 96
							OR ca1_l_x < -96
							   	IF ca1_swerve_anim = 0
									IF ca1_swerve_spook_count < 20
										ca1_spooked++
										ca1_swerve_spook_count++
									ENDIF
									IF ca1_l_x > 0 
										IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_turnl
											GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_turnl ca1_anim_time
											IF ca1_anim_time = 1.0
												TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_turnr CAR 4.0 FALSE FALSE FALSE TRUE 0 // need loop length
												ca1_test = 1
												ca1_swerve_counter = 0
										 		ca1_swerve_anim = 1
											ENDIF
										ELSE
											IF NOT IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_turnr
												TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_turnr CAR 4.0 FALSE FALSE FALSE TRUE 0
												ca1_swerve_counter = 0
											 	ca1_swerve_anim = 1
											ENDIF
										ENDIF
									ENDIF
									IF ca1_l_x < 0
										IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_turnr
											GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_turnr ca1_anim_time
											IF ca1_anim_time = 1.0
												TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_turnl CAR 4.0 FALSE FALSE FALSE TRUE 0 // need loop length
												ca1_test = 1
												ca1_swerve_counter = 0
										 		ca1_swerve_anim = 1
											ENDIF
										ELSE
											IF NOT IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_turnl
												TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_turnl CAR 4.0 FALSE FALSE FALSE TRUE 0
												ca1_swerve_counter = 0
												ca1_swerve_anim = 1
											ENDIF
										ENDIF
									ENDIF
							   	ENDIF	
					  		ENDIF
						ENDIF
					ELSE
						ca1_swerve_anim = 0
						ca1_swerve_counter = 0
		  			ENDIF
				ELSE
					ca1_swerve_anim = 0
					ca1_swerve_counter = 0
			   	ENDIF
			ENDIF
			// Braking
			IF ca1_brake = 0
				IF ca1_truck_speed > 15.0
					IF NOT IS_BUTTON_PRESSED PAD1 CROSS
						IF IS_BUTTON_PRESSED PAD1 SQUARE
						OR IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER1
							ca1_brake = 1
						ENDIF
					ENDIF					
				ENDIF
			ENDIF
			IF ca1_brake = 1
				IF NOT IS_BUTTON_PRESSED PAD1 CROSS
					IF IS_BUTTON_PRESSED PAD1 SQUARE
					OR IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER1
						ca1_ticker ++
						IF ca1_ticker > 12
							TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_bump CAR 4.0 FALSE FALSE FALSE TRUE 0
							GET_GAME_TIMER ca1_brake_start
							ca1_brake = 2
						ENDIF
					ENDIF
				ENDIF
		   	ENDIF
			IF ca1_brake = 2
				GET_GAME_TIMER ca1_brake_end
				ca1_brake_diff = ca1_brake_end - ca1_brake_start
				IF ca1_brake_diff > 1000
					IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_bump
						GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_bump ca1_anim_time
						IF ca1_anim_time = 1.0
							ca1_spooked ++
							ca1_ticker = 0
							ca1_brake = 0
						ENDIF
					ENDIF
				ENDIF
			ENDIF																						  
			// Near Misses
			IF ca1_truck_speed > 30.0
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS ca1_truck 0.0 4.0 0.0 ca1_offset_x ca1_offset_y ca1_offset_z
				ca1_min_x = ca1_offset_x - 0.1
				ca1_min_y = ca1_offset_y - 0.1
				ca1_min_z = ca1_offset_z - 0.1
				ca1_max_x = ca1_offset_x + 0.1
				ca1_max_y = ca1_offset_y + 0.1
				ca1_max_z = ca1_offset_z + 0.1
				IF ca1_miss = 0
					IF IS_AREA_OCCUPIED ca1_min_x ca1_min_y ca1_min_z ca1_max_x ca1_max_y ca1_max_z FALSE TRUE FALSE FALSE FALSE
						GET_GAME_TIMER ca1_timer_start3
						ca1_miss = 1
					ENDIF
				ENDIF
				IF ca1_miss = 1
					GET_GAME_TIMER ca1_timer_end3
					ca1_timer_diff3 = ca1_timer_end3 - ca1_timer_start3
					IF ca1_timer_diff3 > 50
						IF IS_AREA_OCCUPIED ca1_min_x ca1_min_y ca1_min_z ca1_max_x ca1_max_y ca1_max_z FALSE TRUE FALSE FALSE FALSE
							ca1_spooked ++
							ca1_miss = 0
						ELSE
							ca1_miss = 0
						ENDIF
					ENDIF
				ENDIF
			ENDIF			
			// Dialogue
			IF ca1_air = 1
			OR ca1_handbrake = 1
			OR ca1_burnout = 1
			OR ca1_swerve_anim = 1
				IF ca1_dialogue_playing	= 0
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 5000
						GENERATE_RANDOM_INT_IN_RANGE 0 6 ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 1
						IF ca1_random_dialogue = ca1_random_dialogue_last
							IF ca1_random_dialogue < 6 
								ca1_random_dialogue ++
							ELSE
								ca1_random_dialogue --
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF ca1_dialogue_playing	= 1
				IF ca1_random_dialogue = 0
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 27 // JOHNNY SINDACCO: Oh my god, oh-my-god!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 1
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 28 // JOHNNY SINDACCO: Hey, whatcha doing?
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 2
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 29 // JOHNNY SINDACCO: You think you’re smart – BUT YOU’RE NOT!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 3
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 30 // JOHNNY SINDACCO: This shit don’t scare me, asshole!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 4
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 31 // JOHNNY SINDACCO: Where you going now?!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 5
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 32 // JOHNNY SINDACCO: Somebody, anybody, HELP!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 6
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 33 // JOHNNY SINDACCO: Mind the pedestrians!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 7
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 21 // JOHNNY SINDACCO: Oh man, this IS fasst!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 8
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 22	// JOHNNY SINDACCO: Slow down, for chrissakes!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 9
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 23 // JOHNNY SINDACCO: Too fast, TOO FARGHST!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 10
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 45 // JOHNNY SINDACCO: Holy fuck! My legs are on fire!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 11
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 46 // JOHNNY SINDACCO: Shit, my crotch! MY CROTCH!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 12
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 47 // JOHNNY SINDACCO: Holy Mother, we’re on fire!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 13
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 48 // JOHNNY SINDACCO: FIRE! FIRE!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 14
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 24 // JOHNNY SINDACCO: Holy Fuck!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 15
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 25 // JOHNNY SINDACCO: Mother Mary!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 16
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 26	// JOHNNY SINDACCO: Oh Christ! OH CHRIST!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 17
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 34 // JOHNNY SINDACCO: Jesus Christ that was close!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 18
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 35	// JOHNNY SINDACCO: You trying to fuck us both?
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 19
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 36 // JOHNNY SINDACCO: Mother Mary, that was lucky!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 20
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 37 // JOHNNY SINDACCO: You’re gonnna kill us both!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 21
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 40 // JOHNNY SINDACCO: Oh God, I’m still alive!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 22
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 39 // JOHNNY SINDACCO: Thank god! THANK GOD!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 23
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 40 // JOHNNY SINDACCO: Jesus! JESUS!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 24
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 41 // JOHNNY SINDACCO: Are you TRYING to hit things?!

					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 25
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 42	// JOHNNY SINDACCO: Oh…jesus….fuck you…
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 26
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 44	// JOHNNY SINDACCO: Aarrgghh!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 27
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 44	// JOHNNY SINDACCO: Mother… is that you..?
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 28
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 6	// JOHNNY SINDACCO: You're messing with the wrong people!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 29
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 7	// JOHNNY SINDACCO: You are so dead, do you hear me? DEAD!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 30
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 8	// JOHNNY SINDACCO: You’ve signed your own death warrant!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 31
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 9	// JOHNNY SINDACCO: You think this is scaring me, huh?
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 32
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 10 // JOHNNY SINDACCO: It takes more than a little drive to get ME blabbing!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				 IF ca1_random_dialogue = 33
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 11 // JOHNNY SINDACCO: L-look, we can come to some arrangement!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 34
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 12 // JOHNNY SINDACCO: Ok, I can see you’re serious – pull over!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 35
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 13 // JOHNNY SINDACCO: Look, pal, this ain’t funny no more!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 36
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 14 // JOHNNY SINDACCO: Hey! Stop this insanity RIGHT NOW!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 37
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 15 // JOHNNY SINDACCO: Oh my god, you’re a maniac!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				 IF ca1_random_dialogue = 38
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 16 // JOHNNY SINDACCO: Aargh! I don’t wanna die, I don’t wana die!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 39
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 17 // JOHNNY SINDACCO: FUCK YOU! FUCK YOU-HOO-hooooo! (descends into sobbing)
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 40
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 18 // JOHNNY SINDACCO: Oh god, my bowels just let go!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 41
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 19 // JOHNNY SINDACCO: Oh Mother Mary, I’ve had enough, oh god...
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
				IF ca1_random_dialogue = 42
					IF ca1_audio_playing = 0
					AND ca1_counter = 0
						ca1_counter = 20 // JOHNNY SINDACCO: Ok, you’ve made your point – No More!
					ENDIF
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 500
						ca1_random_dialogue_last = ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 2								
					ENDIF 
				ENDIF
			ENDIF
			IF ca1_dialogue_playing = 2
				GET_GAME_TIMER ca1_text_timer_end 
				ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
				IF ca1_text_timer_diff > 500
					ca1_dialogue_playing = 0
				ENDIF
			ENDIF
			// Play Babbling
			IF ca1_dialogue_playing = 0	
				GET_GAME_TIMER ca1_text_timer_end 
				ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
				IF ca1_text_timer_diff > 10000
					IF ca1_spooked >= 0
					AND ca1_spooked < 34
						GENERATE_RANDOM_INT_IN_RANGE 28 32 ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 1
						IF ca1_random_dialogue = ca1_random_dialogue_last
							IF ca1_random_dialogue < 32 
								ca1_random_dialogue ++
							ELSE
								IF ca1_random_dialogue > 28
									ca1_random_dialogue --
								ENDIF
							ENDIF
						ENDIF
					ENDIF
					IF ca1_spooked > 33
					AND ca1_spooked < 67
						GENERATE_RANDOM_INT_IN_RANGE 33 37 ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 1
						IF ca1_random_dialogue = ca1_random_dialogue_last
							IF ca1_random_dialogue < 37 
								ca1_random_dialogue ++
							ELSE
								IF ca1_random_dialogue > 33
									ca1_random_dialogue --
								ENDIF
							ENDIF
						ENDIF
					ENDIF
					IF ca1_spooked > 66
					AND ca1_spooked < 100
						GENERATE_RANDOM_INT_IN_RANGE 38 42 ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 1
						IF ca1_random_dialogue = ca1_random_dialogue_last
							IF ca1_random_dialogue < 42 
								ca1_random_dialogue ++
							ELSE
								IF ca1_random_dialogue > 38
									ca1_random_dialogue --
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF ca1_speed = 1
				IF ca1_dialogue_playing	= 0
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 5000
						GENERATE_RANDOM_INT_IN_RANGE 7 9 ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 1
						IF ca1_random_dialogue = ca1_random_dialogue_last
							IF ca1_random_dialogue < 9 
								ca1_random_dialogue ++
							ELSE
								IF ca1_random_dialogue > 7
									ca1_random_dialogue --
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF ca1_stunt = 1
				IF ca1_dialogue_playing	= 0
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 5000
						GENERATE_RANDOM_INT_IN_RANGE 14 16 ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 1
						IF ca1_random_dialogue = ca1_random_dialogue_last
							IF ca1_random_dialogue < 16 
								ca1_random_dialogue ++
							ELSE
								IF ca1_random_dialogue > 14
									ca1_random_dialogue --
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF ca1_brake = 2
			OR ca1_miss = 1
				IF ca1_dialogue_playing	= 0
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 5000
						GENERATE_RANDOM_INT_IN_RANGE 17 24 ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 1
						IF ca1_random_dialogue = ca1_random_dialogue_last
							IF ca1_random_dialogue < 24 
								ca1_random_dialogue ++
							ELSE
								IF ca1_random_dialogue > 17
									ca1_random_dialogue --
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF IS_CAR_ON_FIRE ca1_truck
				IF ca1_dialogue_playing	= 0
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 5000
						GENERATE_RANDOM_INT_IN_RANGE 10 13 ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 1
						IF ca1_random_dialogue = ca1_random_dialogue_last
							IF ca1_random_dialogue < 13 
								ca1_random_dialogue ++
							ELSE
								IF ca1_random_dialogue > 10
									ca1_random_dialogue --
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF IS_CAR_UPSIDEDOWN ca1_truck
				IF ca1_dialogue_playing	= 0
					GET_GAME_TIMER ca1_text_timer_end 
					ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
					IF ca1_text_timer_diff > 5000
						GENERATE_RANDOM_INT_IN_RANGE 25 27 ca1_random_dialogue
						GET_GAME_TIMER ca1_text_timer_start
						ca1_dialogue_playing = 1
						IF ca1_random_dialogue = ca1_random_dialogue_last
							IF ca1_random_dialogue < 27 
								ca1_random_dialogue ++
							ELSE
								IF ca1_random_dialogue > 25
									ca1_random_dialogue --
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF			
 		ENDIF
	ENDIF
ENDIF

// Writhing On The Bonnet...
IF NOT IS_CHAR_DEAD ca1_punk
   	IF ca1_spooked >= 0
	AND ca1_spooked < 34
		IF ca1_swerve_anim = 0
		AND ca1_brake = 0
			IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_low
				GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_low ca1_anim_time
				IF ca1_anim_time = 1.0
					TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_low CAR 4.0 FALSE FALSE FALSE TRUE 0 // need loop length
					ca1_test = 1
				ENDIF
			ELSE
				IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_turnr
					GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_turnr ca1_anim_time
					IF ca1_anim_time = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_low CAR 4.0 FALSE FALSE FALSE TRUE 0
					ENDIF
				ENDIF
				IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_turnl
					GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_turnl ca1_anim_time
					IF ca1_anim_time = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_low CAR 4.0 FALSE FALSE FALSE TRUE 0
					ENDIF
				ENDIF
				IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_bump
					GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_bump ca1_anim_time
					IF ca1_anim_time = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_low CAR 4.0 FALSE FALSE FALSE TRUE 0
					ENDIF
				ENDIF
				IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_med
					GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_med ca1_anim_time
					IF ca1_anim_time = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_low CAR 4.0 FALSE FALSE FALSE TRUE 0 // need loop length
						ca1_test = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF ca1_spooked > 33
	AND ca1_spooked < 67
		IF ca1_swerve_anim = 0
		AND ca1_brake = 0
			IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_med
				GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_med ca1_anim_time
				IF ca1_anim_time = 1.0
					TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_med CAR 4.0 FALSE FALSE FALSE TRUE 0 // need loop length
					ca1_test = 2
				ENDIF
			ELSE
				IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_turnr
					GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_turnr ca1_anim_time
					IF ca1_anim_time = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_med CAR 4.0 FALSE FALSE FALSE TRUE 0
					ENDIF
				ENDIF
				IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_turnl
					GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_turnl ca1_anim_time
					IF ca1_anim_time = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_med CAR 4.0 FALSE FALSE FALSE TRUE 0
					ENDIF
				ENDIF
				IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_bump
					GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_bump ca1_anim_time
					IF ca1_anim_time = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_med CAR 4.0 FALSE FALSE FALSE TRUE 0
					ENDIF
				ENDIF
				IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_high
					GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_high ca1_anim_time
					IF ca1_anim_time > 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_med CAR 4.0 FALSE FALSE FALSE TRUE 0
					ENDIF
				ENDIF
				IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_low
					GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_low ca1_anim_time
					IF ca1_anim_time = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_med CAR 4.0 FALSE FALSE FALSE TRUE 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF ca1_spooked > 66
	AND ca1_spooked < 100
		IF ca1_swerve_anim = 0
		AND ca1_brake = 0
			IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_high
				GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_high ca1_anim_time
				IF ca1_anim_time = 1.0
				   	TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_high CAR 4.0 FALSE FALSE FALSE TRUE 0 // need loop length
					ca1_test = 3
				ENDIF
			ELSE
				IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_turnr
					GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_turnr ca1_anim_time
					IF ca1_anim_time = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_high CAR 4.0 FALSE FALSE FALSE TRUE 0
					ENDIF
				ENDIF
				IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_turnl
					GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_turnl ca1_anim_time
					IF ca1_anim_time = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_high CAR 4.0 FALSE FALSE FALSE TRUE 0
					ENDIF
				ENDIF
				IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_bump
					GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_bump ca1_anim_time
					IF ca1_anim_time = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_high CAR 4.0 FALSE FALSE FALSE TRUE 0
					ENDIF
				ENDIF
				IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_med
					GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_med ca1_anim_time
					IF ca1_anim_time = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_high CAR 4.0 FALSE FALSE FALSE TRUE 0 // need loop length
						ca1_test = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

// Writhe after spooked... 
IF ca1_stage > 3
	IF ca1_anim_step = 0
		TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_high CAR 4.0 FALSE FALSE FALSE TRUE 0
		ca1_anim_step = 1
	ENDIF
	IF ca1_anim_step = 1
		IF IS_CHAR_PLAYING_ANIM ca1_punk tyd2car_high
			GET_CHAR_ANIM_CURRENT_TIME ca1_punk tyd2car_high ca1_anim_time
			IF ca1_anim_time = 1.0
				TASK_PLAY_ANIM_NON_INTERRUPTABLE ca1_punk tyd2car_high CAR 4.0 FALSE FALSE FALSE TRUE 0 // need loop length
				ca1_test = 2
			ENDIF
		ENDIF
	ENDIF
ENDIF


// Drop him off at the hotel...
IF ca1_stage = 4
	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CAR_DEAD ca1_truck
		IF IS_CHAR_IN_CAR scplayer ca1_truck
			IF ca1_random_dialogue = 0
				IF ca1_audio_playing = 0
				AND ca1_counter = 0
					ca1_counter = 49 // JOHNNY SINDACCO:(sobbing) The family will make you pay for this!
					ca1_random_dialogue ++
					GET_GAME_TIMER ca1_text_timer_start
				ENDIF
				GET_GAME_TIMER ca1_text_timer_end 
				ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
				IF ca1_text_timer_diff > 500
					
					GET_GAME_TIMER ca1_text_timer_start								
				ENDIF 
			ENDIF
			IF ca1_random_dialogue = 1
				IF ca1_audio_playing = 0
				AND ca1_counter = 0
					ca1_counter = 50 // CARL: Which family?
					ca1_random_dialogue ++
					GET_GAME_TIMER ca1_text_timer_start
				ENDIF
				GET_GAME_TIMER ca1_text_timer_end 
				ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
				IF ca1_text_timer_diff > 500
					
					GET_GAME_TIMER ca1_text_timer_start								
				ENDIF 
			ENDIF
			IF ca1_random_dialogue = 2
				IF ca1_audio_playing = 0
				AND ca1_counter = 0
					ca1_counter = 51 // JOHNNY SINDACCO: The Sindacco family you idiot!
					ca1_random_dialogue ++
					GET_GAME_TIMER ca1_text_timer_start
				ENDIF
				GET_GAME_TIMER ca1_text_timer_end 
				ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
				IF ca1_text_timer_diff > 500
					GET_GAME_TIMER ca1_text_timer_start								
				ENDIF 
			ENDIF
			IF ca1_random_dialogue = 3
				IF ca1_audio_playing = 0
				AND ca1_counter = 0
					ca1_counter = 52 // CARL: That’s all I wanted to hear!
					ca1_random_dialogue ++
					GET_GAME_TIMER ca1_text_timer_start
				ENDIF
				GET_GAME_TIMER ca1_text_timer_end 
				ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
				IF ca1_text_timer_diff > 500
					GET_GAME_TIMER ca1_text_timer_start								
				ENDIF 
			ENDIF
			IF ca1_random_dialogue = 4
				IF ca1_audio_playing = 0
				AND ca1_counter = 0
					ca1_counter = 53 // JOHNNY SINDACCO: What the -? Oh shit...
					ca1_random_dialogue ++
					GET_GAME_TIMER ca1_text_timer_start
				ENDIF
				GET_GAME_TIMER ca1_text_timer_end 
				ca1_text_timer_diff = ca1_text_timer_end - ca1_text_timer_start
				IF ca1_text_timer_diff > 500
					GET_GAME_TIMER ca1_text_timer_start
					//PRINT ( CAS1_03 ) 7000 1 // Take him back
				ENDIF 
			ENDIF
			IF ca1_random_dialogue = 5
				IF ca1_audio_playing = 0
				AND ca1_counter = 0
					PRINT ( CAS1_03 ) 7000 1 // Take him back
					ca1_random_dialogue ++
				ENDIF
			ENDIF
		ENDIF
		IF IS_CHAR_IN_CAR scplayer ca1_truck
			IF ca1_goon_flag = 1
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1910.75 960.74 10.86 100.0 100.0 100.0 FALSE
					ca1_goon_flag = 2
				ENDIF
			ENDIF
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer ca1_punk_drop_x ca1_punk_drop_y ca1_punk_drop_z 4.0 4.0 4.0 TRUE
				SET_PLAYER_CONTROL player1 OFF
				IF IS_CHAR_IN_ANY_CAR scplayer 
					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
					TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000
					IF NOT IS_CHAR_DEAD ca1_punk 
					AND NOT IS_CHAR_DEAD scplayer
						DO_FADE 300 FADE_OUT
						WHILE GET_FADING_STATUS
						WAIT 0
						ENDWHILE
						CLEAR_AREA ca1_punk_drop_x ca1_punk_drop_y ca1_punk_drop_z 35.0 TRUE
						GOSUB ca1_goon_create2
						ca1_stage = 5
						ca1_cut = 0
				 	ENDIF
				ENDIF
		  	ENDIF
		ENDIF					    
	ENDIF
ENDIF	 
	
// Ending cutscene...
IF ca1_stage = 5
	IF ca1_cut > 0
	AND	ca1_cut < 3
		SLIDE_OBJECT fourdragons_door 1903.383 967.62 15.438 0.0 0.0 0.1 FALSE
	ENDIF
	IF ca1_cut = 0
		IF NOT IS_CAR_DEAD ca1_truck
		AND NOT IS_CHAR_DEAD ca1_goon
			SET_PLAYER_CONTROL player1 OFF
			SWITCH_WIDESCREEN ON
			SET_FIXED_CAMERA_POSITION 1907.1100 953.0226 10.9148 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 1906.7131 953.9378 10.9839 JUMP_CUT
			REQUEST_CAR_RECORDING 179
			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 179
				WAIT 0
			ENDWHILE

			IF NOT IS_CAR_DEAD ca1_truck
			AND NOT IS_CHAR_DEAD ca1_goon
				START_PLAYBACK_RECORDED_CAR ca1_truck 179
				PAUSE_PLAYBACK_RECORDED_CAR ca1_truck
				DO_FADE 300 FADE_IN
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE
				IF NOT IS_CAR_DEAD ca1_truck
				AND NOT IS_CHAR_DEAD ca1_goon
					TASK_LEAVE_CAR scplayer ca1_truck
					TASK_ENTER_CAR_AS_DRIVER ca1_goon ca1_truck -1
					ca1_cut = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF ca1_cut = 1
		IF NOT SLIDE_OBJECT fourdragons_door 1903.383 967.62 15.438 0.0 0.0 0.05 FALSE
		ENDIF
		IF NOT IS_CAR_DEAD ca1_truck
		AND NOT IS_CHAR_DEAD ca1_goon
			IF IS_CHAR_ON_FOOT scplayer
				GET_SCRIPT_TASK_STATUS scplayer TASK_TURN_CHAR_TO_FACE_CHAR ca1_player_status
				IF ca1_player_status = FINISHED_TASK
					TASK_TURN_CHAR_TO_FACE_CHAR scplayer ca1_goon
					TASK_LOOK_AT_CHAR scplayer ca1_goon -2
				ENDIF
			ENDIF
			IF IS_CHAR_IN_CAR ca1_goon ca1_truck
				CLEAR_LOOK_AT scplayer
				TASK_GO_STRAIGHT_TO_COORD scplayer 1900.7269 951.1921 9.8203 PEDMOVE_WALK -2
//				POINT_CAMERA_AT_CAR ca1_truck FIXED INTERPOLATION
				SET_FIXED_CAMERA_POSITION 1901.2620 961.8007 11.2363 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1901.6202 960.8673 11.2165 JUMP_CUT
			   	UNPAUSE_PLAYBACK_RECORDED_CAR ca1_truck
				ca1_cut = 2
			ELSE
				GET_SCRIPT_TASK_STATUS ca1_goon TASK_ENTER_CAR_AS_DRIVER ca1_goon_status
				IF ca1_goon_status = FINISHED_TASK
					TASK_ENTER_CAR_AS_DRIVER ca1_goon ca1_truck -1
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF ca1_cut = 2
		IF NOT IS_CAR_DEAD ca1_truck
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR ca1_truck
				GET_GAME_TIMER ca1_timer_start
				ca1_cut = 3
			ENDIF
		ENDIF
	ENDIF
	IF ca1_cut = 3
	   	SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.2 FALSE
		GET_GAME_TIMER ca1_timer_end
		ca1_timer_diff = ca1_timer_end - ca1_timer_start
		IF ca1_timer_diff > 1250
			ca1_cut = 4
		ENDIF
	ENDIF
	IF ca1_cut = 4
		SET_OBJECT_COORDINATES fourdragons_door 1903.383 967.82 11.438
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		SET_PLAYER_CONTROL player1 ON
		SWITCH_WIDESCREEN OFF
		RESTORE_CAMERA_JUMPCUT
		SET_CAMERA_BEHIND_PLAYER
		// When goons have done their thing give control back to the player
		ca1_cut = 5
		//ca1_stage = 0
		GOTO mission_passed_ca1
	ENDIF
ENDIF


// If punk dies...
IF ca1_stage > -2
	IF NOT IS_CHAR_DEAD ca1_punk
		GET_CHAR_HEALTH ca1_punk ca1_punk_health
		IF ca1_punk_health = 0
			TASK_DIE ca1_punk
			DETACH_CHAR_FROM_CAR ca1_punk
			PRINT_NOW ( CAS1_04 ) 5000 1 // You killed him!
			GOTO mission_failed_ca1
		ENDIF
	ENDIF
	IF IS_CAR_IN_WATER ca1_truck
		ca1_test = 999
		TASK_DIE ca1_punk
		DETACH_CHAR_FROM_CAR ca1_punk
		PRINT_NOW ( CAS1_04 ) 5000 1 // You killed him!
		GOTO mission_failed_ca1
	ENDIF
ENDIF


GOTO ca1_main_loop 
// ------------------------------------------------------------------------------------------------
// Mission Failed
mission_failed_ca1:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN

// ------------------------------------------------------------------------------------------------
// Mission Passed
mission_passed_ca1:

flag_casino_mission_counter ++
REGISTER_MISSION_PASSED ( CASINO1 )
PLAYER_MADE_PROGRESS 1
//PRINT_WITH_NUMBER_BIG ( M_PASS ) 5000 5000 1 //"Mission Passed!"
////SET_PLAYER_GROUP_RECRUITMENT player1 TRUE
//ADD_SCORE player1 5000
//AWARD_PLAYER_MISSION_RESPECT 10
PRINT_WITH_NUMBER_BIG ( M_PASSS ) 5000 5000 1 //"Mission Passed!"
ADD_SCORE player1 5000 //amount of cash reward
AWARD_PLAYER_MISSION_RESPECT 10 //amount of respect
PLAY_MISSION_PASSED_TUNE 1
CLEAR_WANTED_LEVEL player1
RETURN

// ------------------------------------------------------------------------------------------------
// Mission Cleanup
mission_cleanup_ca1:
// ---- Entities
		GOSUB ca1_truck_delete
		IF NOT IS_CHAR_DEAD ca1_punk
			TASK_DIVE_FROM_ATTACHMENT_AND_GET_UP ca1_punk 10000
		ENDIF
		SET_OBJECT_COORDINATES fourdragons_door 1903.383 967.62 11.438
// ---- Blips
		REMOVE_BLIP ca1_truck_blip
		REMOVE_BLIP ca1_punk_drop_blip
		IF ca1_stage = 5
			DELETE_CHAR ca1_goon
			DELETE_CHAR ca1_punk
			DELETE_CAR ca1_truck
		ENDIF
// ---- Models
		MARK_MODEL_AS_NO_LONGER_NEEDED FELTZER
		MARK_MODEL_AS_NO_LONGER_NEEDED TRIADB
		UNLOAD_SPECIAL_CHARACTER 1
		REMOVE_ANIMATION CAR
		REMOVE_DECISION_MAKER ca1_punk_decision_idx
		SET_FIRST_PERSON_IN_CAR_CAMERA_MODE TRUE
// ----	Clear Script Stuff
		CLEAR_ONSCREEN_COUNTER ca1_spooked
		IF flag_cas1_passed_1stime = 0
			flag_cas1_passed_1stime = 1
		ENDIF
		flag_player_on_mission = 0
		GET_GAME_TIMER timer_mobile_start						
		MISSION_HAS_FINISHED
RETURN
// ------------------------------------------------------------------------------------------------

}




/*

[CAS1_01:CASINO1]
~s~Get back in the ~b~car~s~.

[CAS1_02:CASINO1]
~s~Scare him into talking.

[CAS1_03:CASINO1]
~s~Take him back to the ~y~casino~s~.

[CAS1_04:CASINO1]
~r~You killed him!

[CAS1_05:CASINO1]
Scare-O-Meter

[CAS1_06:CASINO1]
High speeds are sure to scare him.

[CAS1_07:CASINO1]
Try some hand-brake turns.

[CAS1_08:CASINO1]
Insane stunts will get him shook up.

[CAS1_09:CASINO1]
Driving on the wrong side of the road will unsettle him.








W {Player gets control - Mission proper starts}
 
[CAS1_AA:CASINO1]
JOHNNY SINDACCO: You – you’re crazy! You’re a dead man, A DEAD MAN!

[CAS1_AB:CASINO1]
CARL: You’re in no position to make threats.

[CAS1_AC:CASINO1]
CARL: Who are you?

[CAS1_AD:CASINO1]
JOHNNY SINDACCO: Fuck you, man!

[CAS1_AE:CASINO1]
CARL: Have it your way.


W {Level 1 Wailing - Scared and still trying to taunt the player}

[CAS1_BA:CASINO1]
JOHNNY SINDACCO: You're messing with the wrong people!

[CAS1_BB:CASINO1]
JOHNNY SINDACCO: You are so dead, do you hear me? DEAD!

[CAS1_BC:CASINO1]
JOHNNY SINDACCO: You’ve signed your own death warrant!

[CAS1_BD:CASINO1]
JOHNNY SINDACCO: You think this is scaring me, huh?

[CAS1_BE:CASINO1]
JOHNNY SINDACCO: It takes more than a little drive to get ME blabbing!

W {Level 2 Wailing – Plain scared}

[CAS1_CA:CASINO1]
JOHNNY SINDACCO: L-look, we can come to some arrangement!

[CAS1_CB:CASINO1]
JOHNNY SINDACCO: Ok, I can see you’re serious – pull over!

[CAS1_CC:CASINO1]
JOHNNY SINDACCO: Look, pal, this ain’t funny no more!

[CAS1_CD:CASINO1]
JOHNNY SINDACCO: Hey! Stop this insanity RIGHT NOW!

[CAS1_CE:CASINO1]
JOHNNY SINDACCO: Oh my god, you’re a maniac!

W {Level 3 Wailing – Begging for life}

[CAS1_DA:CASINO1]
JOHNNY SINDACCO: Aargh! I don’t wanna die, I don’t wana die!

[CAS1_DB:CASINO1]
JOHNNY SINDACCO: FUCK YOU! FUCK YOU-HOO-hooooo! (descends into sobbing)

[CAS1_DC:CASINO1]
JOHNNY SINDACCO: Oh god, my bowels just let go!

[CAS1_DD:CASINO1]
JOHNNY SINDACCO: Oh Mother Mary, I’ve had enough, oh god…

[CAS1_DE:CASINO1]
JOHNNY SINDACCO: Ok, you’ve made your point – No More!

W {Johnny Caves...}

[CAS1_GA:CASINO1]
JOHNNY SINDACCO:(sobbing) The family will make you pay for this!

[CAS1_GB:CASINO1]
CARL: Which family?

[CAS1_GC:CASINO1]
JOHNNY SINDACCO: The Sindacco family you idiot!

[CAS1_GD:CASINO1]
CARL: That’s all I wanted to hear!

[CAS1_GE:CASINO1]
JOHNNY SINDACCO: What the -? Oh shit...

W {General sobbing for the journey back to the Casino.}

[CAS1_FA:CASINO1]
JOHNNY SINDACCO: Oh god, thank god…

[CAS1_FB:CASINO1]
JOHNNY SINDACCO: Though I walk in the shadow of the valley of death…

[CAS1_FC:CASINO1]
JOHNNY SINDACCO: Mummy…mummy…*sobs*

[CAS1_FD:CASINO1]
JOHNNY SINDACCO: I’ve lead such a sinful life – I’m sorry, so sorry…

W {Reaction to: High Speed}

[CAS1_EA:CASINO1]
JOHNNY SINDACCO: Oh man, this IS fasst!

[CAS1_EB:CASINO1]
JOHNNY SINDACCO: Slow down, for chrissakes!

[CAS1_EC:CASINO1]
JOHNNY SINDACCO: Too fast, TOO FARGHST!

W {Reaction to: Stunt Jumps}

[CAS1_ED:CASINO1]
JOHNNY SINDACCO: Holy Fuck!

[CAS1_EE:CASINO1]
JOHNNY SINDACCO: Mother Mary!

[CAS1_EF:CASINO1]
JOHNNY SINDACCO: Oh Christ! OH CHRIST!

W {Reaction to: Other stunts – burnouts, 180’s etc.}

[CAS1_EG:CASINO1]
JOHNNY SINDACCO: Oh my god, oh-my-god!

[CAS1_EH:CASINO1]
JOHNNY SINDACCO: Hey, whatcha doing?

[CAS1_EI:CASINO1]
JOHNNY SINDACCO: You think you’re smart – BUT YOU’RE NOT!

[CAS1_EJ:CASINO1]
JOHNNY SINDACCO: That shit don’t scare me, asshole!

[CAS1_EK:CASINO1]
JOHNNY SINDACCO: Where you going now?!

[CAS1_EL:CASINO1]
JOHNNY SINDACCO: Somebody, anybody, HELP!

[CAS1_EM:CASINO1]
JOHNNY SINDACCO: Mind the pedestrians!

W {Reaction to: Near misses, collisions that don’t kill him etc.}

[CAS1_EN:CASINO1]
JOHNNY SINDACCO: Jesus Christ that was close!

[CAS1_EO:CASINO1]
JOHNNY SINDACCO: You trying to fuck us both?

[CAS1_EP:CASINO1]
JOHNNY SINDACCO: Mother Mary, that was lucky!

[CAS1_EQ:CASINO1]
JOHNNY SINDACCO: You’re gonnna kill us both!

[CAS1_ER:CASINO1]
JOHNNY SINDACCO: Oh God, I’m still alive!

[CAS1_ES:CASINO1]
JOHNNY SINDACCO: Thank god! THANK GOD!

[CAS1_ET:CASINO1]
JOHNNY SINDACCO: Jesus! JESUS!

[CAS1_EU:CASINO1]
JOHNNY SINDACCO: Are you TRYING to hit things?!

 

W {Reaction to: Car upsidedown – DEAD!}


[CAS1_EV:CASINO1]
JOHNNY SINDACCO: Oh…jesus….fuck you…

[CAS1_EW:CASINO1]
JOHNNY SINDACCO: Aarrgghh!

[CAS1_EX:CASINO1]
JOHNNY SINDACCO: Mother… is that you..?

 

W {Reaction to: Car on fire!}


[CAS1_EY:CASINO1]
JOHNNY SINDACCO: Holy fuck! My legs are on fire!

[CAS1_EZ:CASINO1]
JOHNNY SINDACCO: Shit, my crotch! MY CROTCH!

[CAS1_FA:CASINO1]
JOHNNY SINDACCO: Holy Mother, we’re on fire!

[CAS1_FB:CASINO1]
JOHNNY SINDACCO: FIRE! FIRE!

 

W {Player drops a broken Johnny back at the Casino}

 
[CAS1_JA:CASINO1]
TRAID GOON 1: Whoa, CJ, he’s messed himself!

[CAS1_JB:CASINO1]
CARL: Yeah he should blab like a baby, now!



*/