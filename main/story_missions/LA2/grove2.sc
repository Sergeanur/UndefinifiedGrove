MISSION_START

// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// *************************************** Grove 2 *****************************************
// ************************************ Grove 4 Life  **************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME grove2

// Mission start stuff

GOSUB mission_start_grove2

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_grove2_failed
ENDIF

GOSUB mission_cleanup_grove2

MISSION_END 

// **************************************** Mission Start **********************************

{

mission_start_grove2:
// Cutscene
MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1

LOAD_MISSION_TEXT GROVE2	

SET_FADING_COLOUR 0 0 0

REGISTER_MISSION_GIVEN

SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

FORCE_WEATHER_NOW WEATHER_SUNNY_LA

SET_AREA_VISIBLE 1

LOAD_CUTSCENE GROVE2
 
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

RELEASE_WEATHER

LOAD_SPECIAL_CHARACTER 1 SWEET
REQUEST_MODEL AK47
REQUEST_MODEL GREENWOO

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1 
OR NOT HAS_MODEL_LOADED AK47
OR NOT HAS_MODEL_LOADED GREENWOO
	WAIT 0
ENDWHILE
					 
SET_AREA_VISIBLE 0	  

// *****************************************************************************************

SWITCH_WIDESCREEN OFF
 

// *****************************************************************************************

LVAR_INT g2_sweet_blip g2_a_var	g2_idlewood

LVAR_INT g2_message_displayed g2_start_txt g2_bobcat

LVAR_INT gang_strength1_grove1 gang_strength1_grove2

LVAR_INT gang_strength1_grove3 gang_strength1_grove4 

LVAR_INT gang_strength1_grove5

LVAR_INT g2_flag

VAR_INT g2_sweet_hlth g2_idlewood_off g2_rnd

LVAR_INT g2_audio

LVAR_INT g2_playing

LVAR_INT g2_switch[4]

LVAR_INT g2_end_level

LVAR_INT g2_display_txt

LVAR_INT g2_mission

LVAR_INT g2_grove

LVAR_INT g2_in_the_turf

LVAR_INT g2_in_sweets_car

LVAR_INT g2_fx_take_a_hood

LVAR_TEXT_LABEL g2_print  

VAR_TEXT_LABEL g2_lab

g2_sweet_hlth = 0

g2_idlewood_off = 0

g2_playing = 2

WAIT 0

IF NOT IS_CHAR_DEAD scplayer

	CLEAR_WANTED_LEVEL player1

//	SET_WANTED_MULTIPLIER 0.0

ENDIF

SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 TRUE

SET_GANG_WARS_ACTIVE TRUE

CAN_TRIGGER_GANG_WAR_WHEN_ON_A_MISSION TRUE

LOAD_SCENE 2497.324 -1684.744 12.44

SET_CHAR_COORDINATES scplayer 2497.324 -1684.744 12.44

SET_CHAR_HEADING scplayer 0.0

SET_CAMERA_BEHIND_PLAYER

RESTORE_CAMERA_JUMPCUT																										

SET_FADING_COLOUR 0 0 0
DO_FADE 1000 FADE_IN	

CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 2496.633 -1684.573 12.435 sweet

// Create car at Hub

CUSTOM_PLATE_FOR_NEXT_CAR GREENWOO &GROVE4L_

CREATE_CAR GREENWOO 2507.6990 -1671.7157 12.3823 g2_bobcat

CHANGE_CAR_COLOUR g2_bobcat 59 34 

SET_CAR_HEADING g2_bobcat 355.4549  

SET_CHAR_HEADING sweet 0.0

SET_ANIM_GROUP_FOR_CHAR sweet gang2

GIVE_WEAPON_TO_CHAR sweet WEAPONTYPE_AK47 30000 // set to infinite ammo

SET_CHAR_NEVER_TARGETTED sweet TRUE

SET_CHAR_SUFFERS_CRITICAL_HITS sweet FALSE

MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1

SET_GROUP_MEMBER Players_Group sweet

SET_CHAR_ACCURACY sweet 90

SET_CHAR_HEALTH sweet 1000

SET_CHAR_MAX_HEALTH sweet 1000

SET_CHAR_RELATIONSHIP sweet ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2

GET_CHAR_HEALTH sweet g2_sweet_hlth

g2_sweet_hlth = g2_sweet_hlth / 10

DISPLAY_ONSCREEN_COUNTER_WITH_STRING g2_sweet_hlth COUNTER_DISPLAY_BAR ( GR2_01 )  //"SWEETS HEALTH"

ADD_BLIP_FOR_CHAR sweet g2_sweet_blip

SET_BLIP_AS_FRIENDLY g2_sweet_blip TRUE 

ADD_BLIP_FOR_COORD 2002.8149 -1613.0845 12.3828 g2_idlewood

ADD_BLIP_FOR_COORD 2507.1843 -1672.9060 12.3824 g2_grove

CHANGE_BLIP_DISPLAY g2_sweet_blip NEITHER

CHANGE_BLIP_DISPLAY g2_grove NEITHER

// **************************************************************************************************
//
//                                     DGG for Grove Gang
//
// **************************************************************************************************

SET_CAR_DENSITY_MULTIPLIER 1.0

SET_PED_DENSITY_MULTIPLIER 2.0

TIMERA = 0

SET_ZONE_GANG_STRENGTH IWD3a GANG_GROVE 0
SET_ZONE_GANG_STRENGTH IWD3a GANG_FLAT 100

SET_ZONE_GANG_STRENGTH IWD3b GANG_GROVE 0
SET_ZONE_GANG_STRENGTH IWD3b GANG_FLAT 100

SET_ZONE_GANG_STRENGTH IWD1 GANG_GROVE 0
SET_ZONE_GANG_STRENGTH IWD1 GANG_FLAT 100

SET_ZONE_GANG_STRENGTH IWD4 GANG_GROVE 0
SET_ZONE_GANG_STRENGTH IWD4 GANG_FLAT 100

SET_SPECIFIC_ZONE_TO_TRIGGER_GANG_WAR IWD3a	 
SET_SPECIFIC_ZONE_TO_TRIGGER_GANG_WAR IWD3b 
SET_SPECIFIC_ZONE_TO_TRIGGER_GANG_WAR IWD1
SET_SPECIFIC_ZONE_TO_TRIGGER_GANG_WAR IWD4

SET_ZONE_NO_COPS IWD3a TRUE
SET_ZONE_NO_COPS IWD3b TRUE
SET_ZONE_NO_COPS IWD1 TRUE
SET_ZONE_NO_COPS IWD4 TRUE

WHILE NOT IS_CHAR_DEAD scplayer
	
	WAIT 0

	GOSUB grove2_keys
									   
	GOSUB g2_play_sample

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S

		GOTO mission_grove2_passed

	ENDIF
	
	IF NOT IS_CAR_DEAD g2_bobcat
	AND NOT IS_CHAR_DEAD scplayer
	
		IF IS_CHAR_IN_CAR scplayer g2_bobcat

			IF g2_in_sweets_car = 0

				SET_RADIO_CHANNEL RS_DISCO_FUNK_SOUL

				g2_in_sweets_car = 1

			ENDIF

		ENDIF

	ENDIF

	IF TIMERA > 4000
	AND g2_fx_take_a_hood = 1
		
		GENERATE_RANDOM_INT_IN_RANGE 0 4 g2_rnd

		SWITCH g2_rnd
		
			CASE 0

				$g2_print = &GRO2_DA	// C'mon, CJ, we gotta take more turf!			
				g2_audio = SOUND_GRO2_DA
				GOSUB g2_load_sample
				
			BREAK

			CASE 1

				$g2_print = &GRO2_DB	// We gotta hit another 'hood, keep those Ballas down!			
				g2_audio = SOUND_GRO2_DB
				GOSUB g2_load_sample
				
			BREAK

			CASE 2

				$g2_print = &GRO2_DC	// Which Ballas turf we gonna take next, CJ?			
				g2_audio = SOUND_GRO2_DC
				GOSUB g2_load_sample
				
			BREAK

			CASE 3

				$g2_print = &GRO2_DD	// We gotta take it to another Ballas neighborhood!			
				g2_audio = SOUND_GRO2_DD
				GOSUB g2_load_sample
				
			BREAK

		ENDSWITCH
		
		g2_fx_take_a_hood = 2

	ENDIF

	IF g2_playing = 2					 
	AND g2_fx_take_a_hood = 2
		
		GENERATE_RANDOM_INT_IN_RANGE 0 2 g2_rnd

		SWITCH g2_rnd
		
			CASE 0

				$g2_print = &GRO2_EA	// We gotta take another neighbourhood back!			
				g2_audio = SOUND_GRO2_EA
				GOSUB g2_load_sample
				
			BREAK

			CASE 1

				$g2_print = &GRO2_EB	// Let's go hoo-ride on these Ballas!			
				g2_audio = SOUND_GRO2_EB
				GOSUB g2_load_sample
				
			BREAK

		ENDSWITCH
		
		g2_fx_take_a_hood = 3

	ENDIF

	IF g2_playing = 2					 
	AND g2_fx_take_a_hood = 3

		PRINT_NOW ( GR2_10 ) 7000 1 // ~s~Take over another Idlewood hood.

		g2_fx_take_a_hood = 4

	ENDIF
										    
	IF g2_mission = 0

		GET_ZONE_GANG_STRENGTH IWD3a GANG_FLAT gang_strength1_grove1  
		GET_ZONE_GANG_STRENGTH IWD3b GANG_FLAT gang_strength1_grove2 
		GET_ZONE_GANG_STRENGTH IWD1 GANG_FLAT gang_strength1_grove4   
		GET_ZONE_GANG_STRENGTH IWD4 GANG_FLAT gang_strength1_grove5  

		IF g2_start_txt = 0
											 
			$g2_print = &GRO2_AA	// You got a plan?			
			g2_audio = SOUND_GRO2_AA
			GOSUB g2_load_sample
			
			g2_start_txt = 1

		ENDIF
		IF g2_playing = 2					 
		AND g2_start_txt = 1

			$g2_print = &GRO2_AB	// Yeah I've got a plan.		
			g2_audio = SOUND_GRO2_AB
			GOSUB g2_load_sample
			
			g2_start_txt = 2

		ENDIF						  
		IF g2_playing = 2
		AND g2_start_txt = 2

			$g2_print = &GRO2_AC	// We're gonna roll into Ballas turf and take it back for the Grove!		
			g2_audio = SOUND_GRO2_AC
			GOSUB g2_load_sample

			g2_start_txt = 3
			
		ENDIF
		IF g2_playing = 2
		AND g2_start_txt = 3

			$g2_print = &GRO2_AD	// Ok, let's get those motherfuckers!		
			g2_audio = SOUND_GRO2_AD
			GOSUB g2_load_sample

			g2_start_txt = 4
			
		ENDIF
		IF g2_playing = 2
		AND g2_start_txt = 4

		    PRINT_NOW ( GR2_09 ) 7000 1 // ~s~Go and take back ~y~Idlewood~s~!

			g2_start_txt = 5

		ENDIF

 		IF g2_in_the_turf = 1

			GENERATE_RANDOM_INT_IN_RANGE 0 4 g2_rnd

			SWITCH g2_rnd
			
				CASE 0

					$g2_print = &GRO2_CA	// Let's take this hood back for the Grove!			
					g2_audio = SOUND_GRO2_CA
					GOSUB g2_load_sample
					
				BREAK

				CASE 1

					$g2_print = &GRO2_CB	// Time to put in some work, CJ!			
					g2_audio = SOUND_GRO2_CB
					GOSUB g2_load_sample
					
				BREAK

				CASE 2

					$g2_print = &GRO2_CC	// Let's hit these fools!			
					g2_audio = SOUND_GRO2_CC
					GOSUB g2_load_sample
					
				BREAK

				CASE 3

					$g2_print = &GRO2_CE	// Hit 'em hard, CJ!		
					g2_audio = SOUND_GRO2_CE
					GOSUB g2_load_sample
					
				BREAK

			ENDSWITCH
			
			g2_in_the_turf = 2

		ENDIF

		IF g2_playing = 2					 
		AND g2_in_the_turf = 2

		    PRINT_NOW ( GR2_05 ) 4000 1	// ~s~Shoot some Ballas to start a war!
			
			g2_in_the_turf = 3

		ENDIF						  

		IF IS_PLAYER_IN_INFO_ZONE player1 IWD3a
		OR IS_PLAYER_IN_INFO_ZONE player1 IWD3b
		OR IS_PLAYER_IN_INFO_ZONE player1 IWD1
		OR IS_PLAYER_IN_INFO_ZONE player1 IWD4

			IF g2_message_displayed = 0

				g2_message_displayed = 1

				IF g2_in_the_turf = 0

					g2_in_the_turf = 1

				ENDIF

			ENDIF

			CHANGE_BLIP_DISPLAY g2_idlewood NEITHER

		ELSE

			IF g2_a_var = 1

				CHANGE_BLIP_DISPLAY g2_idlewood BOTH

			ENDIF

		ENDIF

		IF gang_strength1_grove1 = 0
		AND g2_switch[0] = 0

			GOSUB g2_took_the_turf

			g2_switch[0] = 1

		ENDIF
		IF gang_strength1_grove2 = 0
		AND g2_switch[1] = 0

			GOSUB g2_took_the_turf

			g2_switch[1] = 1

		ENDIF				    
		IF gang_strength1_grove4 = 0
		AND g2_switch[2] = 0

			GOSUB g2_took_the_turf

			g2_switch[2] = 1

		ENDIF
		IF gang_strength1_grove5 = 0
		AND g2_switch[3] = 0

			GOSUB g2_took_the_turf

			g2_switch[3] = 1

		ENDIF

	ENDIF

// *****************************************************************************************
// *																					   *
// *						          Sweet death check        				  			   *	
// *																					   *
// *****************************************************************************************

	IF NOT IS_CHAR_DEAD sweet

		GET_CHAR_HEALTH sweet g2_sweet_hlth

		g2_sweet_hlth = g2_sweet_hlth / 10
					
		IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet 30.0 30.0 FALSE

			IF g2_a_var = 0

				IF NOT IS_GROUP_MEMBER sweet Players_Group

					MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1

					SET_GROUP_MEMBER Players_Group sweet

				ENDIF

				CHANGE_BLIP_DISPLAY g2_sweet_blip NEITHER

				IF g2_mission = 0

				//	CHANGE_BLIP_DISPLAY g2_idlewood BOTH

				ENDIF

				IF g2_mission = 1

				//	CHANGE_BLIP_DISPLAY g2_grove BOTH

				ENDIF

				g2_idlewood_off = 0

				g2_a_var = 1

			ENDIF

		ELSE

			IF g2_a_var = 1

				PRINT_NOW ( GR2_02 ) 4000 1	// ~s~You have left Sweet behind go get him.

				CHANGE_BLIP_DISPLAY g2_sweet_blip BOTH

				IF g2_mission = 0

				//	CHANGE_BLIP_DISPLAY g2_idlewood NEITHER

				ENDIF

				IF g2_mission = 1

				//	CHANGE_BLIP_DISPLAY g2_grove NEITHER

				ENDIF

				g2_idlewood_off = 1

				g2_a_var = 0

			ENDIF
		
		ENDIF

	ELSE
		
		PRINT_NOW ( GR2_03 ) 4000 1 // ~r~Sweet's dead!

		GOTO mission_grove2_failed

	ENDIF

// *****************************************************************************************
// *																					   *
// *						    	Going back to Grove Street				  			   *	
// *																					   *
// *****************************************************************************************

	IF g2_mission = 1

		IF g2_display_txt = 1
		AND g2_playing = 2

			PRINT_NOW ( GR2_11 ) 7000 1 // ~s~Take Sweet back to Grove Street.

			g2_display_txt = 2

		ENDIF

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2507.1843 -1672.9060 12.3824 4.0 4.0 4.0 TRUE

			GOTO g2_end_cutscene	
		
		ENDIF

	ENDIF

ENDWHILE

GOTO mission_grove2_failed

// **************************************** Took the turf *********************************

g2_took_the_turf:

	g2_end_level ++

	IF g2_fx_take_a_hood = 0

	    TIMERA = 0

		g2_fx_take_a_hood = 1

	ENDIF

	IF g2_end_level = 2
		  
		$g2_print = &GRO2_FA	// We did it, CJ! let's bail back to Grove Street.
		g2_audio = SOUND_GRO2_FA
		GOSUB g2_load_sample

		g2_mission = 1

		CHANGE_BLIP_DISPLAY g2_grove BOTH

		CHANGE_BLIP_DISPLAY g2_idlewood NEITHER

		CLEAR_AREA 2507.1843 -1672.9060 12.3824 10.0 TRUE

		IF NOT IS_CAR_DEAD g2_bobcat

			IF LOCATE_CAR_3D g2_bobcat 2507.1843 -1672.9060 12.3824 4.0 4.0 4.0 FALSE 
			
				DELETE_CAR g2_bobcat

			ENDIF

		ENDIF

	ENDIF

RETURN

// **************************************** mission grove2 failed **************************

mission_grove2_failed:

	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"

RETURN

// **************************************** mission grove2 passed **************************

mission_grove2_passed:

	REMOVE_BLIP grove_contact_blip

	flag_grove_mission_counter ++

	PLAYER_MADE_PROGRESS 1 

	REGISTER_MISSION_PASSED ( GROVE_2 ) //Used in the stats 

	PRINT_WITH_NUMBER_BIG ( M_PASSS ) 10000 5000 1 //"Mission Passed!" //100 being the amount of cash
	ADD_SCORE player1 10000//amount of cash

	AWARD_PLAYER_MISSION_RESPECT 40 //amount of respect

	PLAY_MISSION_PASSED_TUNE 1

	CLEAR_WANTED_LEVEL player1

RETURN

// ********************************** mission cleanup ************************************

mission_cleanup_grove2:

	SET_ZONE_NO_COPS IWD3a FALSE
	SET_ZONE_NO_COPS IWD3b FALSE
	SET_ZONE_NO_COPS IWD1 FALSE
	SET_ZONE_NO_COPS IWD4 FALSE

	CLEAR_SPECIFIC_ZONES_TO_TRIGGER_GANG_WAR 
	 
	CAN_TRIGGER_GANG_WAR_WHEN_ON_A_MISSION FALSE

	SET_GANG_WARS_ACTIVE TRUE

	flag_player_on_mission = 0
	
	IF NOT IS_CHAR_DEAD sweet

		REMOVE_CHAR_ELEGANTLY sweet

	ENDIF

	IF DOES_BLIP_EXIST g2_sweet_blip

		REMOVE_BLIP g2_sweet_blip
			
	ENDIF

	IF DOES_BLIP_EXIST g2_idlewood

		REMOVE_BLIP g2_idlewood

	ENDIF

	IF DOES_BLIP_EXIST g2_grove

		REMOVE_BLIP g2_grove

	ENDIF

	CLEAR_ONSCREEN_COUNTER g2_sweet_hlth

	UNLOAD_SPECIAL_CHARACTER 1

	MARK_MODEL_AS_NO_LONGER_NEEDED AK47
	MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO

	REMOVE_ANIMATION GANGS	
												   
	GET_GAME_TIMER timer_mobile_start

	SET_CAR_DENSITY_MULTIPLIER 1.0

	SET_PED_DENSITY_MULTIPLIER 1.0

	MISSION_HAS_FINISHED

RETURN

// ********************************** camera stuff ************************************

grove2_set_camera:

	CLEAR_PRINTS

	SWITCH_WIDESCREEN ON
	SET_PLAYER_CONTROL player1 OFF

RETURN

grove2_restore_camera:

	CLEAR_PRINTS

	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER
 
RETURN

grove2_fade_out:

	DO_FADE 500 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

grove2_fade_in:

	DO_FADE 500 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

grove2_fade:

	DO_FADE 500 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

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

grove2_keys:

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_A

		gang_strength1_grove2 = 0
			
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D

	 	gang_strength1_grove1 = 0

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_F

		GOTO g2_end_cutscene

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_G
		
		SET_CHAR_COORDINATES scplayer 2487.6445 -1668.2043 12.3438 
		
		SET_CHAR_HEADING scplayer 265.0722 

		g2_end_level = 2

		g2_mission = 1

		CHANGE_BLIP_DISPLAY g2_grove BOTH

		CLEAR_AREA 2507.1843 -1672.9060 12.3824 10.0 TRUE

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_K

		GOSUB g2_took_the_turf

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_H

	ENDIF

RETURN

g2_end_cutscene:

	GOSUB grove2_fade_out
	GOSUB grove2_set_camera

	CLEAR_PRINTS

	REQUEST_ANIMATION GANGS

	WHILE NOT HAS_ANIMATION_LOADED GANGS
		WAIT 0
	ENDWHILE
	 
	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD sweet

		REMOVE_CHAR_FROM_GROUP sweet

		CLEAR_AREA 2512.7610 -1672.5204 12.5034 50.0 TRUE 

		IF IS_CHAR_IN_ANY_CAR scplayer

			SET_CHAR_COORDINATES scplayer 2496.5525 -1682.4392 12.3542

			SET_CHAR_HEADING scplayer 270.6489 

			WARP_CHAR_FROM_CAR_TO_COORD scplayer 2512.7610 -1672.5204 12.5034

		ELSE

			SET_CHAR_COORDINATES scplayer 2512.7610 -1672.5204 12.5034

		ENDIF

		IF NOT IS_CAR_DEAD g2_bobcat

			DELETE_CAR g2_bobcat

		ENDIF

		SET_CHAR_HEADING scplayer 240.2609 

		TASK_TURN_CHAR_TO_FACE_CHAR scplayer sweet

		IF IS_CHAR_IN_ANY_CAR sweet

			WARP_CHAR_FROM_CAR_TO_COORD sweet 2514.7876 -1673.9382 12.6861

		ELSE

			SET_CHAR_COORDINATES sweet 2514.7876 -1673.9382 12.6861

		ENDIF

		SET_CHAR_HEADING sweet 73.5014  

		TASK_TURN_CHAR_TO_FACE_CHAR sweet scplayer

		TASK_TOGGLE_PED_THREAT_SCANNER sweet FALSE FALSE FALSE
		
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

		CLEAR_CHAR_TASKS_IMMEDIATELY sweet

		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

		SET_CURRENT_CHAR_WEAPON sweet WEAPONTYPE_UNARMED

	ENDIF

	SET_PED_DENSITY_MULTIPLIER 0.0

	SET_FIXED_CAMERA_POSITION 2509.7981 -1672.1747 13.4192 0.0 0.0 0.0 // Player enters car
	POINT_CAMERA_AT_POINT 2510.7522 -1672.4573 13.5178 JUMP_CUT

	CLEAR_PRINTS

	GOSUB grove2_fade_in
	 
	IF NOT IS_CHAR_DEAD scplayer

		TASK_PLAY_ANIM scplayer PRTIAL_HNDSHK_01 GANGS 4.0 FALSE FALSE FALSE FALSE -1

		WAIT 500

	ENDIF

	IF NOT IS_CHAR_DEAD sweet

		TASK_PLAY_ANIM sweet PRTIAL_HNDSHK_01 GANGS 4.0 FALSE FALSE FALSE FALSE -1

		WAIT 500

	ENDIF

	CLEAR_MISSION_AUDIO 1

 	LOAD_MISSION_AUDIO 1 SOUND_GRO2_GA // You helped the Grove Street Families hammer those Ballas fools!

	WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
		WAIT 0
	ENDWHILE

	PLAY_MISSION_AUDIO 1

	PRINT_NOW ( GRO2_GA ) 4000 1 // You helped the Grove Street Families hammer those Ballas fools!

	TIMERB = 0
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO g2_skip_the_cut_10
		ENDIF
	ENDWHILE

	IF NOT IS_CHAR_DEAD sweet

		TASK_PLAY_ANIM sweet PRTIAL_gngtlkC GANGS 4.0 FALSE FALSE FALSE FALSE -1

	ENDIF

	CLEAR_MISSION_AUDIO 1

 	LOAD_MISSION_AUDIO 1 SOUND_GRO2_GB // Johnson boys rolled right over them!

	WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
		WAIT 0
	ENDWHILE

	PLAY_MISSION_AUDIO 1

	PRINT_NOW ( GRO2_GB ) 4000 1 // Johnson boys rolled right over them!

	TIMERB = 0
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO g2_skip_the_cut_10
		ENDIF
	ENDWHILE

	CLEAR_MISSION_AUDIO 1

 	LOAD_MISSION_AUDIO 1 SOUND_GRO2_GC // Ok, dog, I'm going to get some sleep, I'm beat.

	WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
		WAIT 0
	ENDWHILE

	PLAY_MISSION_AUDIO 1

	PRINT_NOW ( GRO2_GC ) 4000 1 // Ok, dog, I'm going to get some sleep, I'm beat.

	TIMERB = 0
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO g2_skip_the_cut_10
		ENDIF
	ENDWHILE

//	SET_FIXED_CAMERA_POSITION 2511.7495 -1673.3112 13.3167 0.0 0.0 0.0 // Player enters car
//	POINT_CAMERA_AT_POINT 2512.6335 -1672.9690 13.6351 JUMP_CUT
	  
	IF NOT IS_CHAR_DEAD sweet

		CLEAR_CHAR_TASKS_IMMEDIATELY sweet

		TASK_GO_TO_COORD_ANY_MEANS sweet 2513.6831 -1672.8765 12.5412 PEDMOVE_WALK -1

	ENDIF

	IF NOT IS_CHAR_DEAD scplayer 

		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

		TASK_LOOK_AT_CHAR scplayer sweet 10000

	ENDIF

	CLEAR_MISSION_AUDIO 1

 	LOAD_MISSION_AUDIO 1 SOUND_GRO2_GD // Here, I took this from one of those Ballas fools.

	WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
		WAIT 0
	ENDWHILE

	PLAY_MISSION_AUDIO 1

	PRINT_NOW ( GRO2_GD ) 4000 1 // Here, I took this from one of those Ballas fools.

	TIMERB = 0
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO g2_skip_the_cut_10
		ENDIF
	ENDWHILE

	IF NOT IS_CHAR_DEAD sweet

		TASK_PLAY_ANIM sweet PRTIAL_HNDSHK_biz_01 GANGS 4.0 FALSE FALSE FALSE FALSE -1

	ENDIF
	IF NOT IS_CHAR_DEAD scplayer

		TASK_PLAY_ANIM scplayer PRTIAL_HNDSHK_biz_01 GANGS 4.0 FALSE FALSE FALSE FALSE -1
											   
	ENDIF

	CLEAR_MISSION_AUDIO 1

 	LOAD_MISSION_AUDIO 1 SOUND_GRO2_GE // Probably base money, here's your half

	WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
		WAIT 0
	ENDWHILE

	PLAY_MISSION_AUDIO 1

	PRINT_NOW ( GRO2_GE ) 4000 1 // Probably base money, here's your half

	TIMERB = 0
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO g2_skip_the_cut_10
		ENDIF
	ENDWHILE
		
	IF NOT IS_CHAR_DEAD sweet

		CLEAR_CHAR_TASKS_IMMEDIATELY sweet

		TASK_GO_TO_COORD_ANY_MEANS sweet 2516.2642 -1675.2539 12.9287 PEDMOVE_WALK -1
		
	ENDIF

	TIMERB = 0
	WHILE TIMERB < 2000
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO g2_skip_the_cut_10
		ENDIF
	ENDWHILE

	GOSUB grove2_fade_out
	
	g2_skip_the_cut_10:

	REMOVE_ANIMATION GANGS

	DELETE_CHAR sweet

	IF NOT IS_CHAR_DEAD scplayer

		SET_CHAR_COORDINATES scplayer 2512.4070 -1672.1110 12.4895 

		SET_CHAR_HEADING scplayer 78.5658 

	ENDIF

	GOSUB grove2_restore_camera

	SET_PED_DENSITY_MULTIPLIER 1.0

	SET_CAMERA_BEHIND_PLAYER

	GOSUB grove2_fade_in

GOTO mission_grove2_passed

g2_load_sample:

	CLEAR_MISSION_AUDIO 1

	LOAD_MISSION_AUDIO 1 g2_audio

	g2_playing = 0

RETURN

g2_play_sample:

	IF HAS_MISSION_AUDIO_LOADED 1
	AND g2_playing = 0

		PLAY_MISSION_AUDIO 1

		PRINT_NOW ( $g2_print ) 10000 1  

		g2_playing = 1

	ENDIF

	IF HAS_MISSION_AUDIO_FINISHED 1
	AND g2_playing = 1

		CLEAR_MISSION_AUDIO 1	

		CLEAR_THIS_PRINT $g2_print

		g2_playing = 2

	ENDIF
	
RETURN

}

/*

{--------------------------------------GROVE2---------------------------------------------}

[GR2_01:GROVE2]
SWEETS HEALTH: 

[GR2_02:GROVE2]
~s~You have left Sweet behind go get him.

[GR2_03:GROVE2]
~r~Sweets dead!

[GR2_04:GROVE2]
Sweet : Let's do it Carl! shoot those mofo's

[GR2_05:GROVE2]
~s~Shoot some Ballas to start a war!

[GR2_06:GROVE2]
Sweet : Where we going, homie?

[GR2_07:GROVE2]
Carl : Ballas turf, you ready Dog?

[GR2_08:GROVE2]
Sweet : Let's get those motherfuckers!

[GR2_09:GROVE2]
~s~Go and take back Idlewood!
 
*/