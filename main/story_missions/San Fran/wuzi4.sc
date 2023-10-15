MISSION_START
// *****************************************************************************************
// ***************************** wuzi4 ************************************ 
// *****************************************************************************************
// *****************************************************************************************
// ***                                                                                   ***
// *****************************************************************************************

SCRIPT_NAME wuzi5

// Mission start stuff
GOSUB mission_start_wuzi5
IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_wuzi5_failed
ENDIF
GOSUB mission_cleanup_wuzi5
MISSION_END
{
// Variables for mission

//people
LVAR_INT w5_chopper w5_chopper_pilot w5_goons[17] w5_dinghys[4] w5_smoke w5_rocket_launcher w5_refugees[4] w5_grenades w5_health
LVAR_INT w5_head_honcho w5_cabin_crew[5] w5_box
LVAR_INT w5_armour w5_gun 
LVAR_INT w5_kat_anim w5_kat_lh
LVAR_INT w5_refugee_container
LVAR_INT w5_refugee_container_LD
LVAR_INT w5_refugee_container_RD
LVAR_INT w5_refugee_container_padlock

//blips
LVAR_INT w5_blip w5_goons_blip[9]  //w5_blip2
  

//flags
LVAR_INT w5_goals w5_control_flag w5_skip_cutscene_flag w5_deathcheck_flag  
LVAR_INT w5_creating_goons_flag w5_has_goons_been_created  
LVAR_INT w5_refugees_flag  
LVAR_INT w5_char_select[2] w5_char_select_flag w5_player_health
LVAR_INT w5_where_player_control_flag w5_random_number 
LVAR_INT w5_water_flag
LVAR_INT w5_goons_AI_flag[9]

//speech
LVAR_INT w5_speech_goals w5_speech_flag w5_speech_control_flag w5_random_last_label 
LVAR_TEXT_LABEL w5_print_label[6] 
LVAR_INT w5_audio_label[6] 
LVAR_INT w5_last_label 
LVAR_INT w5_slot1 w5_slot2 w5_slot_load w5_play_which_slot

//coords
LVAR_FLOAT w5_x w5_y w5_z
LVAR_FLOAT w5_anim_time
LVAR_FLOAT w5_fXPos w5_fYPos w5_fZPos w5_fXVel	w5_fYVel  w5_fZVel w5_fRed w5_fGreen w5_fBlue w5_fAlpha w5_fSize w5_fLife //for the chopper particles









//sequences/decision makers/threat lists/attractors/groups
LVAR_INT w5_seq w5_ped_decisions w5_empty_decisions w5_stealth_decisions

//debug
LVAR_INT w5_debug_health

// ****************************************Mission Start************************************
mission_start_wuzi5:
flag_player_on_mission = 1
REGISTER_MISSION_GIVEN
LOAD_MISSION_TEXT WUZI4
IF flag_player_on_mission = 0
	CREATE_CHAR PEDTYPE_MISSION5 TRIADB -2433.5 1536.3 16.4 w5_goons[w5_creating_goons_flag] 
	CREATE_CHAR PEDTYPE_MISSION5 TRIADB -2433.5 1536.3 16.4 w5_rocket_launcher
	CREATE_CHAR PEDTYPE_MISSION5 TRIADB -2389.2 1537.1 1.1 w5_refugees[w5_refugees_flag]
	CREATE_PICKUP_WITH_AMMO  grenade PICKUP_ON_STREET_SLOW 6 -2412.4 1547.9 25.0 w5_grenades
	CREATE_PICKUP health PICKUP_ON_STREET_SLOW -2427.8 1547.6 22.1 w5_health
	ADD_BLIP_FOR_CHAR w5_goons[w5_creating_goons_flag] w5_goons_blip[w5_creating_goons_flag]
	CREATE_CAR DINGHY -2430.7 1563.6 0.0 w5_dinghys[0]
	CREATE_CAR DINGHY -2442.4 1564.6 0.0 w5_dinghys[1]
	CREATE_CAR DINGHY -2426.9 1570.6 0.0 w5_dinghys[2]
ENDIF
CLEAR_PRINTS
WAIT 0

// *************************************Set Flags/variables*********************************
w5_goals = 0
w5_control_flag = 0				    
w5_skip_cutscene_flag = 0 
w5_deathcheck_flag = 0 
w5_speech_flag = 0
w5_creating_goons_flag = 0
w5_has_goons_been_created = 0
w5_refugees_flag = 0
w5_char_select[0] = DNB1
w5_char_select[1] = DNB2
//w5_char_select[2] = DNB2OS
w5_char_select_flag = 0
w5_player_health = 0
w5_where_player_control_flag = 0
w5_random_number = 0
w5_water_flag = 0

w5_goons_AI_flag[0] = 0
w5_goons_AI_flag[1] = 0
w5_goons_AI_flag[2] = 0
w5_goons_AI_flag[3] = 0
w5_goons_AI_flag[4] = 0
w5_goons_AI_flag[5] = 0
w5_goons_AI_flag[6] = 0
w5_goons_AI_flag[7] = 0
w5_goons_AI_flag[8] = 0

w5_fXPos = 0.0 
w5_fYPos = 0.0 
w5_fZPos = 0.0 
w5_fXVel = 0.0	
w5_fYVel = 0.0 
w5_fZVel = 0.0 
w5_fRed = 0.0 
w5_fGreen = 0.0 
w5_fBlue = 0.0 
w5_fAlpha = 0.0 
w5_fSize = 0.0 
w5_fLife = 0.0


// ****************************************START OF CUTSCENE********************************
MAKE_PLAYER_GANG_DISAPPEAR
SET_AREA_VISIBLE 1
LOAD_CUTSCENE WOOZIE4
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
SET_AREA_VISIBLE 0
CLEAR_CUTSCENE
SET_PLAYER_CONTROL player1 OFF
MAKE_PLAYER_GANG_REAPPEAR
// ****************************************END OF CUTSCENE**********************************

SET_FADING_COLOUR 0 0 0
FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_SF
WAIT 0
//------------------REQUEST_MODELS ------------------------------

REQUEST_MODEL TRIADB
REQUEST_MODEL MAVERICK
REQUEST_ANIMATION CAR_CHAT  

LOAD_ALL_MODELS_NOW

REMOVE_GROUP Players_Group
SET_PLAYER_GROUP_RECRUITMENT player1 FALSE

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 PEDTYPE_PLAYER1

COPY_CHAR_DECISION_MAKER DM_PED_EMPTY w5_empty_decisions 
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH w5_ped_decisions  
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_STEAL w5_stealth_decisions

CLEAR_AREA -2167.8 634.9 69.7 5.0 TRUE
CREATE_CAR MAVERICK -2167.8 634.9 69.7 w5_chopper
SET_CAR_HEADING w5_chopper 92.3
SET_CAR_PROOFS w5_chopper TRUE TRUE TRUE TRUE TRUE  

CREATE_CHAR_INSIDE_CAR w5_chopper PEDTYPE_MISSION5 TRIADB w5_chopper_pilot
SET_CHAR_NEVER_TARGETTED w5_chopper_pilot TRUE  
SET_LOAD_COLLISION_FOR_CHAR_FLAG w5_chopper_pilot TRUE 
SET_CHAR_DECISION_MAKER w5_chopper_pilot w5_empty_decisions 

CLEAR_AREA -2168.4 639.0 69.9 1.0 TRUE
SET_CHAR_COORDINATES scplayer -2168.4 639.0 69.9 
SET_CHAR_HEADING scplayer 189.4
LOAD_SCENE -2168.4 639.0 69.9 

//FREEZE_CAR_POSITION w5_chopper TRUE 
//LOCK_CAR_DOORS w5_chopper CARLOCK_LOCKED
//PRINT_NOW ( WUZ4_01 ) 7000 1 //Go and meet the pilot at the airport.

/*
///////////////debug////////////////
w5_goals = 3
REQUEST_MODEL ROCKETLA
REQUEST_MODEL TEC9
REQUEST_MODEL DNB1
REQUEST_MODEL DNB2
//REQUEST_MODEL DNB2OS
REQUEST_MODEL GRENADE
REQUEST_MODEL KNIFECUR
REQUEST_MODEL CHROMEGUN
REQUEST_MODEL OMOST
LOAD_ALL_MODELS_NOW
SET_PLAYER_CONTROL player1 ON
SWITCH_WIDESCREEN OFF
MAKE_PLAYER_GANG_REAPPEAR
RESTORE_CAMERA_JUMPCUT
SET_CAMERA_BEHIND_PLAYER
DO_FADE 0 FADE_IN 
///////////////debug////////////////
*/

/*
///////////////debug////////////////
w5_goals = 2
REQUEST_MODEL ROCKETLA
REQUEST_MODEL TEC9
REQUEST_MODEL DNB1
REQUEST_MODEL DNB2
REQUEST_MODEL DNB2OS
REQUEST_MODEL GRENADE
REQUEST_MODEL KNIFECUR
REQUEST_MODEL CHROMEGUN
REQUEST_MODEL OMOST
LOAD_ALL_MODELS_NOW
SET_CHAR_COORDINATES scplayer -2330.6 1518.3 1.0
WHILE w5_has_goons_been_created < 2
	GOSUB w5_creating_goons
ENDWHILE 
//GOSUB w5_creating_refugees
*/

//SET_CHAR_COORDINATES scplayer -2371.9 1547.2 3.0


/*
/////////////DEBUG////////////
VAR_INT w5_where_player_control_flag 
VIEW_INTEGER_VARIABLE w5_where_player_control_flag w5_where_player_control_flag 
/////////////DEBUG////////////
*/
/*
/////////////DEBUG////////////
w5_goals = 7
CREATE_CHAR PEDTYPE_MISSION5 TRIADB -2373.5 1555.8 1.1 w5_refugees[0]
CREATE_CHAR PEDTYPE_MISSION5 TRIADB -2373.4 1553.1 1.1 w5_refugees[1]
CREATE_CHAR PEDTYPE_MISSION5 TRIADB -2373.4 1553.1 1.1 w5_refugees[2]
CREATE_CHAR PEDTYPE_MISSION5 TRIADB -2367.5 1552.2 1.1 w5_refugees[3]
/////////////DEBUG////////////
*/

mission_wuzi5_loop:
WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
        GOTO mission_wuzi5_passed  
	ENDIF

	ALTER_WANTED_LEVEL player1 0

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// DEATHCHECKS ///////////////////////////////////////////////////////////////////////////////////
	GOSUB w5_death_checks
	IF w5_deathcheck_flag = 1
		GOTO mission_wuzi5_failed
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// Initial Cutscene on roof //////////////////////////////////////////////////////////////////////

	IF w5_goals = 0
		IF w5_control_flag = 0

			//IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer -1246.1 -36.4 13.1 1.2 1.2 2.0 TRUE     
				CLEAR_PRINTS	 
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				w5_speech_goals = 0
				
				SET_PLAYER_CONTROL player1 OFF
				SWITCH_WIDESCREEN ON
				MAKE_PLAYER_GANG_DISAPPEAR
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
				SET_FIXED_CAMERA_POSITION -2172.7 636.0 71.2 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2161.7 631.4 72.4 JUMP_CUT
				
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE

				//LOCK_CAR_DOORS w5_chopper CARLOCK_UNLOCKED
				//FREEZE_CAR_POSITION w5_chopper FALSE

				OPEN_SEQUENCE_TASK w5_seq
					TASK_ENTER_CAR_AS_PASSENGER -1 w5_chopper -1 0						  
				CLOSE_SEQUENCE_TASK w5_seq
				PERFORM_SEQUENCE_TASK scplayer w5_seq
				CLEAR_SEQUENCE_TASK w5_seq
				
				LOAD_SCENE_IN_DIRECTION -2168.7 634.9 70.3 248.0  
				
				DO_FADE 500 FADE_IN
				WHILE GET_FADING_STATUS				  
				    WAIT 0
				ENDWHILE
				GOSUB w5_death_checks
				IF w5_deathcheck_flag = 1
					GOTO mission_wuzi5_failed
				ENDIF	
																	
				w5_skip_cutscene_flag = 1
				SKIP_CUTSCENE_START
				
				w5_control_flag = 1
			//ENDIF
		ENDIF
		
		IF w5_control_flag = 1 
			IF IS_CHAR_IN_ANY_CAR scplayer 
				TASK_PLAY_ANIM w5_chopper_pilot CAR_Sc1_FL CAR_CHAT 8.0 FALSE FALSE FALSE FALSE 8000 //driver speaking
				TASK_PLAY_ANIM scplayer CAR_Sc1_FR CAR_CHAT 6.0 FALSE FALSE FALSE FALSE 8500 //passenger speaking	
				
				//cutscene dialogue between pilot and player
				w5_speech_goals = 1
				w5_speech_control_flag = 0
				GOSUB w5_dialogue_setup 

				timera = 0
				w5_control_flag = 2
			ENDIF
		ENDIF

		IF w5_control_flag = 2
			IF timera > 9000
				HELI_GOTO_COORDS w5_chopper -2167.8 634.9 80.7 0.0 80.7
				timera = 0
				w5_control_flag = 3
			ENDIF  
		ENDIF 
		
		IF w5_control_flag = 3 
			IF w5_speech_goals = 0 
				w5_skip_cutscene_flag = 0
				SKIP_CUTSCENE_END
				GOSUB w5_death_checks
				IF w5_deathcheck_flag = 1
					GOTO mission_wuzi5_failed
				ENDIF
			
				IF w5_skip_cutscene_flag = 1
					CLEAR_PRINTS	 
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					w5_speech_goals = 0
				ENDIF

				DO_FADE 1000 FADE_OUT 
				WHILE GET_FADING_STATUS
					WAIT 0
					REQUEST_CAR_RECORDING 25
				ENDWHILE 
				GOSUB w5_death_checks
				IF w5_deathcheck_flag = 1
					GOTO mission_wuzi5_failed
				ENDIF
				
				REQUEST_MODEL MINIGUN
				REQUEST_MODEL ROCKETLA
				REQUEST_MODEL TEC9
				REQUEST_MODEL DNB1
				REQUEST_MODEL DNB2
				//REQUEST_MODEL DNB2OS

				REQUEST_CAR_RECORDING 25
				
				LOAD_ALL_MODELS_NOW
				
				CLEAR_PRINTS 
				CLEAR_CHAR_TASKS scplayer

				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
			
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
				ATTACH_CHAR_TO_CAR scplayer w5_chopper -1.4 1.0 -0.1 FACING_LEFT 90.0 WEAPONTYPE_MINIGUN
			
				START_PLAYBACK_RECORDED_CAR w5_chopper 25
				  
				SKIP_IN_PLAYBACK_RECORDED_CAR w5_chopper 750.0

				SET_HELI_BLADES_FULL_SPEED w5_chopper

				REMOVE_ANIMATION CAR_CHAT
				
				SET_PLAYER_CONTROL player1 ON
				SWITCH_WIDESCREEN OFF
				MAKE_PLAYER_GANG_REAPPEAR
				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER
				SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
				IF NOT IS_CAR_DEAD w5_chopper 
					SET_CAR_PROOFS w5_chopper TRUE TRUE TRUE TRUE TRUE
				ENDIF
				
				LOAD_SCENE_IN_DIRECTION -1735.7 1303.5 7.1 120.8  
			
				//ADD_BLIP_FOR_COORD -2437.6 1541.5 6.4 w5_blip
					
				DO_FADE 2000 FADE_IN
				WHILE GET_FADING_STATUS
					WAIT 0
					REQUEST_CAR_RECORDING 25
				ENDWHILE 
				GOSUB w5_death_checks
				IF w5_deathcheck_flag = 1
					GOTO mission_wuzi5_failed
				ENDIF
			
				timera = 0
				w5_control_flag = 0
				w5_goals = 1

			ENDIF
		ENDIF
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// Heli flying around freighter //////////////////////////////////////////////////////////////////

	IF w5_goals = 1
		
		//////////////DEBUG///////////////
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q 
			IF w5_has_goons_been_created < 2	
				GOSUB w5_creating_goons_part1
			ELSE
				IF NOT IS_CAR_DEAD w5_chopper
					IF IS_PLAYBACK_GOING_ON_FOR_CAR w5_chopper
						STOP_PLAYBACK_RECORDED_CAR w5_chopper
						MAKE_HELI_COME_CRASHING_DOWN w5_chopper
					ENDIF	  
					w5_control_flag = 3
				ENDIF
			ENDIF
		ENDIF
		//////////////DEBUG///////////////
		
		// SPEECH FOR THIS SECTION
		IF w5_speech_flag = 0
			IF timera > 6000
				//2nd convo between pilot and player				
				w5_speech_goals = 2
				w5_speech_control_flag = 0
				GOSUB w5_dialogue_setup 
				w5_speech_flag = 1
			ENDIF
		ENDIF
		
		//creating crowd 
		IF w5_has_goons_been_created < 2
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2409.1 1541.0 200.0 200.0 FALSE
				GOSUB w5_creating_goons_part1
			ENDIF
		ELSE
			//controlling goons ai
			GOSUB w5_goons_ai
				
			//blippage
			GOSUB w5_death_kills
		ENDIF
		 

		//////////////////////////////////////////////////////////////////////////////////////
		////// blowing up the helicopter when it is in the correct place /////////////////////
		IF NOT IS_CAR_DEAD w5_chopper 
			IF w5_control_flag = 0		
				IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2287.1 1582.8 3.0 3.0 FALSE	
				  
					//Pilot telling player he is getting shot at
					w5_speech_goals = 3
					w5_speech_control_flag = 0
					GOSUB w5_dialogue_setup 

					SET_PLAYBACK_SPEED w5_chopper 0.6
					w5_control_flag = 1
				ENDIF
			ENDIF
			
			IF w5_control_flag = 1		
				IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2483.3 1509.6 3.0 3.0 FALSE	
					
					GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
					CLEAR_AREA -2429.5 1536.8 30.8 1.0 TRUE 
					CREATE_CHAR PEDTYPE_MISSION5 w5_char_select[w5_char_select_flag] -2429.5 1536.8 30.8 w5_rocket_launcher
					GIVE_WEAPON_TO_CHAR w5_rocket_launcher WEAPONTYPE_ROCKETLAUNCHER 12
					SET_CHAR_STAY_IN_SAME_PLACE w5_rocket_launcher TRUE
					SET_CHAR_PROOFS w5_rocket_launcher TRUE TRUE TRUE TRUE TRUE 

					OPEN_SEQUENCE_TASK w5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -2431.0 1530.4 30.8 PEDMOVE_RUN -1
						TASK_DESTROY_CAR -1 w5_chopper
					CLOSE_SEQUENCE_TASK w5_seq
					PERFORM_SEQUENCE_TASK w5_rocket_launcher w5_seq
					CLEAR_SEQUENCE_TASK w5_seq
					
					timera = 0
					w5_control_flag = 2
				ENDIF	
			ENDIF

			IF w5_control_flag = 2
				//IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2462. 3.0 3.0 FALSE
				IF timera > 1800	
					//RPG RPG
					w5_speech_goals = 4
					w5_speech_control_flag = 0
					w5_random_last_label = 2
					GOSUB w5_dialogue_setup 
					
					w5_control_flag = 3
				ENDIF
			ENDIF 

			IF w5_control_flag = 3
				IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2401.0 1499.1 3.0 3.0 FALSE	
					SHAKE_PAD PAD1 10000 200	
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS w5_chopper 0.0 4.0 0.0 w5_x w5_y w5_z						
					ADD_EXPLOSION w5_x w5_y w5_z EXPLOSION_HELI
					SET_CAR_HEALTH w5_chopper 300
					w5_control_flag = 4		
				ENDIF
			ENDIF

			//add smoke particle effect every frame
			IF w5_control_flag > 3  
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS w5_chopper 0.0 0.0 0.0 w5_x w5_y w5_z						
				//x
				GENERATE_RANDOM_FLOAT_IN_RANGE -2.0 2.0 w5_fXPos
				w5_fXPos += w5_x  
				//y
				GENERATE_RANDOM_FLOAT_IN_RANGE -2.0 2.0 w5_fyPos
				w5_fYPos += w5_y
				//z
				w5_fZPos = w5_z 
				//x velocity
				GENERATE_RANDOM_FLOAT_IN_RANGE -1.5 1.5 w5_fXVel
				//y velocity
				GENERATE_RANDOM_FLOAT_IN_RANGE -1.5 1.5 w5_fYVel
				//z velocity
				GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 1.0 w5_fZVel
				w5_fRed = 0.0
				w5_fGreen = 0.0
				w5_fBlue = 0.0
				w5_fAlpha = 0.2
				w5_fSize = 1.0
				w5_fLife = 1.0
				ADD_SMOKE_PARTICLE w5_fXPos w5_fYPos w5_fZPos w5_fXVel w5_fYVel w5_fZVel w5_fRed w5_fGreen w5_fBlue w5_fAlpha w5_fSize w5_fLife
			ENDIF

			
			IF w5_control_flag = 4
				IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2381.0 1489.0 3.0 3.0 FALSE
					CLEAR_PRINTS	 
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					w5_speech_goals = 0
				
					SET_PLAYER_CONTROL player1 OFF
					SWITCH_WIDESCREEN ON
					MAKE_PLAYER_GANG_DISAPPEAR
					HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
					
					DETACH_CHAR_FROM_CAR scplayer
					CLEAR_AREA -2351.7 1487.4 1.0 1.0 TRUE
					SET_CHAR_COORDINATES scplayer -2351.7 1487.4 1.0

					//We're hit! We're going down! Brace for impact! 
					w5_speech_goals = 4
					w5_speech_control_flag = 2
					w5_random_last_label = 5
					GOSUB w5_dialogue_setup 

					SET_PLAYBACK_SPEED w5_chopper 1.0
				
					SET_FIXED_CAMERA_POSITION -2353.5 1450.2 82.3 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT -2365.3 1471.5 64.7 JUMP_CUT
					
					w5_skip_cutscene_flag = 1
					SKIP_CUTSCENE_START
					w5_control_flag = 5
				ENDIF
			ENDIF

			IF w5_control_flag = 5
				IF LOCATE_CAR_2D w5_chopper -2360.0 1481.5 5.0 5.0 FALSE
					//STOP_PLAYBACK_RECORDED_CAR w5_chopper	   
					//MAKE_HELI_COME_CRASHING_DOWN w5_chopper
					w5_control_flag = 6
				ENDIF
			ENDIF
		ENDIF

		IF w5_control_flag < 7
			IF IS_CAR_IN_WATER w5_chopper
			
				w5_skip_cutscene_flag = 0			
				SKIP_CUTSCENE_END
				GOSUB w5_death_checks
				IF w5_deathcheck_flag = 1
					GOTO mission_wuzi5_failed
				ENDIF

				//skipping cutscene
				IF w5_skip_cutscene_flag = 1			
					CLEAR_PRINTS	 
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					w5_speech_goals = 0
				
					DO_FADE 500 FADE_OUT 
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
				
					IF NOT IS_CAR_DEAD w5_chopper 
						SKIP_TO_END_AND_STOP_PLAYBACK_RECORDED_CAR w5_chopper
					ENDIF 
				ENDIF

				SHAKE_PAD PAD1 0 0
				
				DO_FADE 1000 FADE_OUT 
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
		
				CLEAR_PRINTS	 
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				w5_speech_goals = 0
				
				SET_PLAYER_CONTROL player1 OFF
				SWITCH_WIDESCREEN ON
				MAKE_PLAYER_GANG_DISAPPEAR
				//HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			
				SHUT_ALL_CHARS_UP TRUE
				
				DETACH_CHAR_FROM_CAR scplayer
				MARK_CHAR_AS_NO_LONGER_NEEDED w5_chopper_pilot 
				MARK_MODEL_AS_NO_LONGER_NEEDED MINIGUN
				MARK_MODEL_AS_NO_LONGER_NEEDED ROCKETLA
				MARK_MODEL_AS_NO_LONGER_NEEDED MAVERICK
				MARK_MODEL_AS_NO_LONGER_NEEDED TRIADB

				REQUEST_MODEL GRENADE
				REQUEST_MODEL KNIFECUR
				REQUEST_MODEL CHROMEGUN
				LOAD_ALL_MODELS_NOW

				REMOVE_ALL_CHAR_WEAPONS scplayer
				GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_KNIFE 1 
				SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_KNIFE  
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				CLEAR_AREA -2351.7 1487.4 0.0 1.0 TRUE
				SET_CHAR_COORDINATES scplayer -2351.7 1487.4 -1.0 
				SET_CHAR_HEADING scplayer 318.0

				IF NOT IS_CHAR_DEAD w5_goons[0] 
					CLEAR_CHAR_TASKS w5_goons[0]
				ENDIF
				IF NOT IS_CHAR_DEAD w5_goons[1] 
					CLEAR_CHAR_TASKS w5_goons[1]
					//SET_CHAR_STAY_IN_SAME_PLACE w5_goons[1] FALSE 
					//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[1] TRUE 
				ENDIF
				IF NOT IS_CHAR_DEAD w5_goons[2] 
					CLEAR_CHAR_TASKS w5_goons[2]
					//SET_CHAR_STAY_IN_SAME_PLACE w5_goons[2] FALSE 
					//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[2] TRUE 
				ENDIF
				IF NOT IS_CHAR_DEAD w5_goons[3] 
					CLEAR_CHAR_TASKS w5_goons[3]
					//SET_CHAR_STAY_IN_SAME_PLACE w5_goons[3] FALSE 
					//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[3] TRUE 
				ENDIF
				IF NOT IS_CHAR_DEAD w5_goons[4] 
					CLEAR_CHAR_TASKS w5_goons[4]
					//SET_CHAR_STAY_IN_SAME_PLACE w5_goons[4] FALSE 
					//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[4] TRUE 
				ENDIF
				IF NOT IS_CHAR_DEAD w5_goons[5] 
					CLEAR_CHAR_TASKS w5_goons[5]
					TASK_TOGGLE_DUCK w5_goons[5] TRUE
				ENDIF
				IF NOT IS_CHAR_DEAD w5_goons[7] 
					CLEAR_CHAR_TASKS w5_goons[7]
				ENDIF
			
				REMOVE_BLIP w5_goons_blip[0]
				REMOVE_BLIP w5_goons_blip[1]
				REMOVE_BLIP w5_goons_blip[2]
				REMOVE_BLIP w5_goons_blip[3]
				REMOVE_BLIP w5_goons_blip[4]
				REMOVE_BLIP w5_goons_blip[5]
				REMOVE_BLIP w5_goons_blip[6]
				REMOVE_BLIP w5_goons_blip[7]
				REMOVE_BLIP w5_goons_blip[8]
							
				ADD_BLIP_FOR_COORD -2329.1 1528.7 0.0 w5_blip
				DELETE_CHAR w5_rocket_launcher
				
				GET_CHAR_HEALTH scplayer w5_player_health
				IF w5_player_health > 25
					w5_player_health /= 4 
					w5_player_health *= 3 

					IF w5_player_health < 25
						w5_player_health = 25
				    ENDIF	 
					SET_CHAR_HEALTH scplayer w5_player_health
				ENDIF
			
				//SET_FIXED_CAMERA_POSITION -2347.6 1489.3 1.0 0.0 0.0 0.0
				//POINT_CAMERA_AT_POINT -2360.6 1486.1 -1.6 JUMP_CUT
			
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SWITCH_WIDESCREEN OFF
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				SET_PLAYER_CONTROL player1 ON
				CLEAR_CHAR_TASKS scplayer

				CLEAR_PRINTS
				DRAW_SPHERE -2329.1 1528.7 0.0 4.0
				DO_FADE 2000 FADE_IN 
				WHILE GET_FADING_STATUS
					DRAW_SPHERE -2329.1 1528.7 0.0 4.0
					WAIT 0
					DRAW_SPHERE -2329.1 1528.7 0.0 4.0
				ENDWHILE 
				
				//Oh, man, I hurt! | Shit, lost all my weapons!
				w5_speech_goals = 12
				w5_speech_control_flag = 0
				GOSUB w5_dialogue_setup 

				w5_speech_flag = 0
				w5_control_flag = 0
				w5_goals = 2
			ENDIF
		ENDIF
	ENDIF   



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////Player has to swim to the boat /////////////////////////////////////////////////////////////////
	IF w5_goals = 2
		IF w5_control_flag = 0
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2329.1 1528.7 0.0 4.0 4.0 4.0 TRUE 
				DO_FADE 500 FADE_OUT 
				timera = 0
				w5_control_flag = 1
			ELSE
				IF w5_speech_flag = 0 
					IF w5_speech_goals = 0
			 			PRINT_NOW ( WUZ4_02 ) 7000 1 //Swim over to the freighter.
						w5_speech_flag = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF w5_control_flag = 1
			IF timera > 500
				CLEAR_PRINTS	 
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				w5_speech_goals = 0
			
				SET_PLAYER_CONTROL player1 OFF
				SWITCH_WIDESCREEN ON
				MAKE_PLAYER_GANG_DISAPPEAR
				//HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			
				REQUEST_MODEL kmb_container_red
			
				LOAD_ALL_MODELS_NOW

				REQUEST_ANIMATION SWAT
				WHILE NOT HAS_ANIMATION_LOADED SWAT
					WAIT 0
				ENDWHILE
				
				//creating dummy collision to stand on
				CREATE_OBJECT kmb_container_red -2323.9 1527.3 14.0 w5_box //was 13.0 height before railing were added
				SET_OBJECT_COLLISION w5_box TRUE
				SET_OBJECT_DYNAMIC w5_box FALSE 
				SET_OBJECT_VISIBLE w5_box FALSE 
				
				WAIT 0 
				GOSUB w5_death_checks
				IF w5_deathcheck_flag = 1
					GOTO mission_wuzi5_failed
				ENDIF

				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				CLEAR_AREA -2323.2 1530.2 16.9 1.0 TRUE
				SET_CHAR_COORDINATES scplayer -2323.2 1530.2 16.9 
				SET_CHAR_HEADING scplayer 3.3 
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			
				//right hand side of first bit of boxes
				IF NOT IS_CHAR_DEAD w5_goons[0] 
					CLEAR_AREA -2350.6 1561.2 22.1 1.0 TRUE
					SET_CHAR_COORDINATES w5_goons[0] -2350.6 1561.2 22.1 
					SET_CHAR_HEADING w5_goons[0] 4.7 
					TASK_PLAY_ANIM w5_goons[0] WEAPON_crouch PED 4.0 TRUE FALSE FALSE FALSE -1
				ENDIF
			
				//left hand side of first bit of boxes
				IF NOT IS_CHAR_DEAD w5_goons[1] 
					CLEAR_AREA -2357.1 1529.2 25.0 1.0 TRUE
					SET_CHAR_COORDINATES w5_goons[1] -2357.1 1529.2 25.0 
					SET_CHAR_HEADING w5_goons[1] 180.9 
					TASK_LOOK_ABOUT w5_goons[1] -2 
				ENDIF
			
				//left hand side of boat, start of mazey bit
				DELETE_CHAR w5_goons[2] 
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
				CLEAR_AREA -2388.3 1553.1 25.0 1.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2388.3 1553.1 25.0 w5_goons[2]  
				SET_CHAR_HEADING w5_goons[2] 0.0
				SET_CHAR_DECISION_MAKER w5_goons[2] w5_ped_decisions
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[2] TRUE 
				GIVE_WEAPON_TO_CHAR w5_goons[2] WEAPONTYPE_TEC9 30000

				//right hand side of boat, start of mazey bit
				DELETE_CHAR w5_goons[3] 
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
				CLEAR_AREA -2390.9 1552.6 25.0 1.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2390.9 1552.6 25.0 w5_goons[3]  
				SET_CHAR_HEADING w5_goons[3] 0.0
				SET_CHAR_DECISION_MAKER w5_goons[3] w5_ped_decisions
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[3] TRUE 
				GIVE_WEAPON_TO_CHAR w5_goons[3] WEAPONTYPE_TEC9 30000

				//middle of maze - guy who is going to inform everyone
				DELETE_CHAR w5_goons[4] 
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
				CLEAR_AREA -2389.8 1554.9 25.0 1.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2389.8 1554.9 25.0 w5_goons[4]  
				SET_CHAR_HEADING w5_goons[4] 220.6
				SET_CHAR_DECISION_MAKER w5_goons[4] w5_ped_decisions
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[4] TRUE 
				GIVE_WEAPON_TO_CHAR w5_goons[4] WEAPONTYPE_TEC9 30000

				//Getting these two to chat to each other
				IF NOT IS_CHAR_DEAD w5_goons[3] 
					IF NOT IS_CHAR_DEAD w5_goons[4] 
						TASK_CHAT_WITH_CHAR w5_goons[4] w5_goons[3] TRUE TRUE 
						TASK_CHAT_WITH_CHAR w5_goons[3] w5_goons[4] FALSE TRUE 
					ENDIF
				ENDIF
		
				//hiding next to the grenades
				DELETE_CHAR w5_goons[5] 
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
				CLEAR_AREA -2407.3 1547.6 25.0 1.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2407.3 1547.6 25.0 w5_goons[5]  
				SET_CHAR_HEADING w5_goons[5] 319.2
				SET_CHAR_DECISION_MAKER w5_goons[5] w5_ped_decisions
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[5] TRUE 
				GIVE_WEAPON_TO_CHAR w5_goons[5] WEAPONTYPE_TEC9 30000

				//left hand side of last bit of maze popping in/out
				DELETE_CHAR w5_goons[6] 
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
				CLEAR_AREA -2420.4 1553.4 25.0 1.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2420.4 1553.4 25.0 w5_goons[6]  
				SET_CHAR_HEADING w5_goons[6] 180.0
				SET_CHAR_DECISION_MAKER w5_goons[6] w5_empty_decisions
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[6] TRUE 
				GIVE_WEAPON_TO_CHAR w5_goons[6] WEAPONTYPE_TEC9 30000
															
				//at end of long bit at start of last bit of maze											 
				DELETE_CHAR w5_goons[7] 
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
				CLEAR_AREA -2427.6 1532.3 25.0 1.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2427.6 1532.3 25.0 w5_goons[7]  
				SET_CHAR_HEADING w5_goons[7] 275.0
				SET_CHAR_DECISION_MAKER w5_goons[7] w5_ped_decisions 
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[7] TRUE 
				GIVE_WEAPON_TO_CHAR w5_goons[7] WEAPONTYPE_TEC9 30000
														    
				//right hand side of last bit of maze
				DELETE_CHAR w5_goons[8] 
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
				CLEAR_AREA -2427.9 1553.9 25.0 1.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2427.9 1553.9 25.0 w5_goons[8]  
				SET_CHAR_HEADING w5_goons[8] 183.8
				SET_CHAR_DECISION_MAKER w5_goons[8] w5_ped_decisions
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[8] TRUE 
				GIVE_WEAPON_TO_CHAR w5_goons[8] WEAPONTYPE_TEC9 30000
				
				//w5_goons[9] is the warden
				
				//first guy you have to kill
				CLEAR_AREA -2334.5 1533.3 16.3 1.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION2 DNB1 -2334.5 1533.3 16.3 w5_goons[10]
				SET_CHAR_HEADING w5_goons[10] 116.3 
				GIVE_WEAPON_TO_CHAR w5_goons[10] WEAPONTYPE_SHOTGUN 3000
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[10] TRUE
				SET_CHAR_DECISION_MAKER w5_goons[10] w5_ped_decisions
			
				//guy on top of the boxes that won't move
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
				CLEAR_AREA -2401.8 1530.3 30.8 1.0 TRUE
			 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2401.8 1530.3 30.8 w5_goons[11]  
				SET_CHAR_HEADING w5_goons[11] 0.0
				SET_CHAR_DECISION_MAKER w5_goons[11] w5_ped_decisions
				GIVE_WEAPON_TO_CHAR w5_goons[11] WEAPONTYPE_TEC9 30000
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[11] TRUE
				
				//guy on top of the box that will move
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
				CLEAR_AREA -2405.5 1530.3 30.8 1.0 TRUE
			 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2405.5 1530.3 30.8 w5_goons[12]  
				SET_CHAR_HEADING w5_goons[12] 0.0
				SET_CHAR_DECISION_MAKER w5_goons[12] w5_empty_decisions
				TASK_PLAY_ANIM w5_goons[12] weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
				GIVE_WEAPON_TO_CHAR w5_goons[12] WEAPONTYPE_TEC9 30000

				//first guy hiding in hole at end of maze  
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
				CLEAR_AREA -2426.5 1548.8 22.2 1.0 TRUE
			 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2426.5 1548.8 22.2 w5_goons[13]  
				SET_CHAR_HEADING w5_goons[13] 180.0
				SET_CHAR_DECISION_MAKER w5_goons[13] w5_empty_decisions
				SET_CHAR_STAY_IN_SAME_PLACE w5_goons[13] TRUE 
				GIVE_WEAPON_TO_CHAR w5_goons[13] WEAPONTYPE_TEC9 30000

				//second guy hiding in hole at end of maze  
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
				CLEAR_AREA -2422.5 1548.8 22.2 1.0 TRUE
			 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2422.5 1548.8 22.2 w5_goons[14]  
				SET_CHAR_HEADING w5_goons[14] 180.0
				SET_CHAR_DECISION_MAKER w5_goons[14] w5_empty_decisions
				SET_CHAR_STAY_IN_SAME_PLACE w5_goons[14] TRUE 
				GIVE_WEAPON_TO_CHAR w5_goons[14] WEAPONTYPE_TEC9 30000
			
				//guy standing on the small boxes at front righthandside of the first bit of boxes
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
				CLEAR_AREA -2339.2 1555.5 24.7 1.0 TRUE
			 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2339.2 1555.5 24.7 w5_goons[15]  
				SET_CHAR_HEADING w5_goons[15] 348.3
				SET_CHAR_DECISION_MAKER w5_goons[15] w5_ped_decisions
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[15] TRUE 
				GIVE_WEAPON_TO_CHAR w5_goons[15] WEAPONTYPE_TEC9 30000
				TASK_LOOK_ABOUT w5_goons[15] -2

				//guy standing at far righthandside of the first bit of boxes
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
				CLEAR_AREA -2360.0 1560.3 25.0 1.0 TRUE
			 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2360.0 1560.3 25.0 w5_goons[16]  
				SET_CHAR_HEADING w5_goons[16] 26.4
				SET_CHAR_DECISION_MAKER w5_goons[16] w5_ped_decisions
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[16] TRUE 
				GIVE_WEAPON_TO_CHAR w5_goons[16] WEAPONTYPE_TEC9 30000
				TASK_LOOK_ABOUT w5_goons[16] -2

				OPEN_SEQUENCE_TASK w5_seq
					TASK_GO_STRAIGHT_TO_COORD -1 -2334.7 1529.8 16.3 PEDMOVE_WALK -1
					TASK_ACHIEVE_HEADING -1 126.8
					TASK_LOOK_ABOUT -1 -2  
				CLOSE_SEQUENCE_TASK w5_seq
				PERFORM_SEQUENCE_TASK w5_goons[10] w5_seq
				CLEAR_SEQUENCE_TASK w5_seq

				SET_FIXED_CAMERA_POSITION -2332.5 1534.4 18.1 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2342.5 1513.7 15.33 JUMP_CUT

				DO_FADE 500 FADE_IN 
										  
				//Can you see any survivors?  
				w5_speech_goals = 5
				w5_speech_control_flag = 0
				w5_random_last_label = 1
				GOSUB w5_dialogue_setup 
		
				timera = 0
				w5_control_flag = 2
			ENDIF
		ENDIF

		IF w5_control_flag = 2
			IF timera > 2000
				w5_skip_cutscene_flag = 1
				SKIP_CUTSCENE_START
				w5_control_flag = 3
			ENDIF
		ENDIF
	
		IF w5_control_flag = 3
			IF w5_speech_goals = 0 
				IF timera > 3200
			 
					//No. Nobody's getting out of that alive!  
					w5_speech_goals = 5
					w5_speech_control_flag = 1
					w5_random_last_label = 2
					GOSUB w5_dialogue_setup 
					
					w5_control_flag = 4
				ENDIF
			ENDIF
		ENDIF
		
		IF w5_control_flag = 4 
			IF timera > 6000  
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
				OPEN_SEQUENCE_TASK w5_seq
					//TASK_PAUSE -1 500 
					TASK_CLIMB -1 TRUE
					TASK_ACHIEVE_HEADING  -1 85.1
				CLOSE_SEQUENCE_TASK w5_seq
				PERFORM_SEQUENCE_TASK scplayer w5_seq
				CLEAR_SEQUENCE_TASK w5_seq
			
				SET_FIXED_CAMERA_POSITION -2320.4 1535.2 18.8 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2354.4 1508.1 18.9 JUMP_CUT
			
				w5_control_flag = 5
			ENDIF
		ENDIF


		IF w5_control_flag = 5
			IF timera > 8500
				w5_skip_cutscene_flag = 0
				SKIP_CUTSCENE_END

				IF w5_skip_cutscene_flag = 1
					CLEAR_PRINTS	 
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					w5_speech_goals = 0
					
					DO_FADE 500 FADE_OUT 
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					GOSUB w5_death_checks
					IF w5_deathcheck_flag = 1
						GOTO mission_wuzi5_failed
					ENDIF
				ENDIF  
							
				IF NOT IS_CHAR_DEAD w5_goons[10] 
					SET_CHAR_DECISION_MAKER w5_goons[10] w5_stealth_decisions
				ENDIF 
			
				IF NOT IS_CHAR_DEAD w5_goons[15] 
					SET_CHAR_DECISION_MAKER w5_goons[15] w5_stealth_decisions
				ENDIF 

				IF NOT IS_CHAR_DEAD w5_goons[16] 
					SET_CHAR_DECISION_MAKER w5_goons[16] w5_stealth_decisions
				ENDIF 

				IF NOT IS_CHAR_DEAD w5_goons[0] 
					SET_CHAR_DECISION_MAKER w5_goons[0] w5_stealth_decisions
				ENDIF 

				IF NOT IS_CHAR_DEAD w5_goons[1] 
					SET_CHAR_DECISION_MAKER w5_goons[1] w5_stealth_decisions
				ENDIF 

				CLEAR_CHAR_TASKS scplayer
				DELETE_OBJECT w5_box
				MARK_MODEL_AS_NO_LONGER_NEEDED kmb_container_red
				CLEAR_AREA -2323.1 1531.7 16.3 1.0 TRUE
				SET_CHAR_COORDINATES scplayer -2323.1 1531.7 16.3
				SET_CHAR_HEADING scplayer 85.2
				
				CLEAR_PRINTS
				PRINT_NOW ( WUZ4_10 ) 7000 1 //Make your way down into the hull of the ship.  Be quiet for as long as possible!
				REMOVE_BLIP w5_blip
				ADD_BLIP_FOR_COORD -2437.6 1541.5 6.4 w5_blip 

				MARK_CAR_AS_NO_LONGER_NEEDED w5_chopper 
				
				SHUT_ALL_CHARS_UP FALSE
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
				
				//HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SWITCH_WIDESCREEN OFF
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				SET_PLAYER_CONTROL player1 ON
				
				//skipping cutscene
				IF w5_skip_cutscene_flag = 1
					DO_FADE 500 FADE_IN 
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					GOSUB w5_death_checks
					IF w5_deathcheck_flag = 1
						GOTO mission_wuzi5_failed
					ENDIF
				ENDIF
				
				w5_speech_flag = 0
				w5_control_flag = 0
				w5_goals = 3
			ENDIF
		ENDIF 
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////Going down to see the hull//////////////////////////////////////////////////////////////////////
	IF w5_goals = 3
		//////////DEBUG//////////
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q
			SET_CHAR_COORDINATES scplayer -2437.5 1538.4 10.7
		ENDIF 
		//////////DEBUG//////////
			
		//waiting for player to reach hull
		IF w5_control_flag = 0
			IF IS_CHAR_IN_AREA_3D scplayer -2428.6 1532.6 12.2 -2441.3 1549.9 8.0 FALSE 
				//drawing fake locate
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2437.6 1541.5 6.4 1.2 1.2 1.2 TRUE
				ENDIF
			
				IF NOT IS_PLAYER_CLIMBING player1  
					SET_PLAYER_CONTROL player1 OFF
					w5_control_flag = 1
				ENDIF
			ELSE
				//////// WAITING FOR THE PLAYER TO ALERT THE REST OF THE BOAT ///
				GOSUB w5_guys_at_start_of_maze
			
				//drawing fake locate
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2437.6 1541.5 6.4 1.2 1.2 1.2 TRUE
				ENDIF
			ENDIF
		ENDIF
			
		IF w5_control_flag = 1
			//drawing fake locate
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2437.6 1541.5 6.4 1.2 1.2 1.2 TRUE
			ENDIF
	
			IF IS_CHAR_STOPPED scplayer
				DO_FADE 500 FADE_OUT	
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				GOSUB w5_death_checks
				IF w5_deathcheck_flag = 1
					GOTO mission_wuzi5_failed
				ENDIF	
				
				SHUT_ALL_CHARS_UP TRUE
				
				//warden down in with refugees
			 	CLEAR_AREA -2374.4 1548.5 1.1 1.0 TRUE
			 	CREATE_CHAR PEDTYPE_MISSION2 DNB2 -2374.4 1548.5 1.1 w5_goons[9]  
				SET_CHAR_HEADING w5_goons[9] 299.2
				SET_CHAR_DECISION_MAKER w5_goons[9] w5_empty_decisions
				//SET_CHAR_STAY_IN_SAME_PLACE w5_goons[9] TRUE 
				GIVE_WEAPON_TO_CHAR w5_goons[9] WEAPONTYPE_GRENADE 1
				ADD_ARMOUR_TO_CHAR w5_goons[9] 100 
			
				REMOVE_ANIMATION SWAT
				MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
				MARK_MODEL_AS_NO_LONGER_NEEDED KNIFECUR
				DELETE_OBJECT w5_box 
				MARK_MODEL_AS_NO_LONGER_NEEDED kmb_container_red
				
				//creating the refugees
				REQUEST_ANIMATION PARK
				WHILE NOT HAS_ANIMATION_LOADED PARK
					WAIT 0
				ENDWHILE
		
				//placing pick-ups for the player
				CREATE_PICKUP_WITH_AMMO TEC9 PICKUP_ON_STREET_SLOW 48 -2428.4 1536.3 2.1 w5_gun
				CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW -2425.3 1536.4 2.1 w5_armour
				
				//deleting all the old guys from upstairs
				w5_creating_goons_flag = 0
				w5_delete_goons2:
				WAIT 0
				IF w5_creating_goons_flag < 15
					IF NOT w5_creating_goons_flag = 9  
						DELETE_CHAR w5_goons[w5_creating_goons_flag]
					ENDIF
					w5_creating_goons_flag ++
					GOTO w5_delete_goons2 
				ENDIF

				CLEAR_PRINTS	 
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				w5_speech_goals = 0
			
				REQUEST_MODEL CJ_CHRIS_CRATE
				REQUEST_MODEL CJ_CHRIS_CRATE_LD
				REQUEST_MODEL CJ_CHRIS_CRATE_RD
				REQUEST_MODEL CJ_Padlock
				REQUEST_MODEL OMOBOAT

				LOAD_ALL_MODELS_NOW
				
				//creating container for refugees
				CLEAR_AREA -2368.9 1551.9 1.1 10.0 TRUE
				CREATE_OBJECT CJ_CHRIS_CRATE -2368.9 1551.9 1.1 w5_refugee_container
				SET_OBJECT_HEADING w5_refugee_container 270.0 

				CREATE_OBJECT CJ_CHRIS_CRATE_LD -2371.55 1552.66 1.245 w5_refugee_container_LD
				SET_OBJECT_HEADING w5_refugee_container_LD 270.0 

				CREATE_OBJECT CJ_CHRIS_CRATE_RD -2371.55 1551.14 1.245 w5_refugee_container_RD
				SET_OBJECT_HEADING w5_refugee_container_RD 270.0 

				CREATE_OBJECT CJ_Padlock -2371.75 1551.9 2.1 w5_refugee_container_padlock
				SET_OBJECT_HEADING w5_refugee_container_padlock 270.0

				//first guy with box who goes after player
			 	CLEAR_AREA -2408.4 1553.1 1.1 1.0 TRUE
			 	CREATE_CHAR PEDTYPE_MISSION2 DNB1 -2408.4 1553.1 1.1 w5_goons[0]  
				SET_CHAR_HEADING w5_goons[0] 151.3
				SET_CHAR_DECISION_MAKER w5_goons[0] w5_empty_decisions
				TASK_SET_IGNORE_WEAPON_RANGE_FLAG w5_goons[0] TRUE
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[0] TRUE 
				GIVE_WEAPON_TO_CHAR w5_goons[0] WEAPONTYPE_TEC9 30000
				

				//second guy with box who runs to hide behind the boxes
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
			 	CLEAR_AREA -2404.8 1555.1 1.1 1.0 TRUE
			 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2404.8 1555.1 1.1 w5_goons[1]  
				SET_ANIM_GROUP_FOR_CHAR w5_goons[1] gang2
				SET_CHAR_HEADING w5_goons[1] 102.0
				SET_CHAR_DECISION_MAKER w5_goons[1] w5_empty_decisions
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[1] TRUE 
				GIVE_WEAPON_TO_CHAR w5_goons[1] WEAPONTYPE_TEC9 30000
	
				//third guy with box who runs to tell the warden
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
			 	CLEAR_AREA -2401.3 1555.1 1.1 1.0 TRUE
			 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2401.3 1555.1 1.1 w5_goons[2]  
				SET_ANIM_GROUP_FOR_CHAR w5_goons[2] gang1
				SET_CHAR_HEADING w5_goons[2] 90.0
				SET_CHAR_DECISION_MAKER w5_goons[2] w5_empty_decisions
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[2] TRUE 
				GIVE_WEAPON_TO_CHAR w5_goons[2] WEAPONTYPE_TEC9 30000
	
				//guy hiding in the middle of the maze by himself 
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
			 	CLEAR_AREA -2389.4 1553.4 1.1 1.0 TRUE
			 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2389.4 1553.4 1.1 w5_goons[3]  
				SET_CHAR_HEADING w5_goons[3] 180.0
				SET_CHAR_DECISION_MAKER w5_goons[3] w5_empty_decisions
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[3] TRUE 
				GIVE_WEAPON_TO_CHAR w5_goons[3] WEAPONTYPE_TEC9 30000
	
				//2 guys who are going to blow up the box and come through
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
			 	CLEAR_AREA -2404.7 1545.5 1.1 1.0 TRUE
			 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2404.7 1545.5 1.1 w5_goons[4]  
				SET_CHAR_HEADING w5_goons[4] 266.2
				SET_CHAR_DECISION_MAKER w5_goons[4] w5_empty_decisions
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[4] TRUE 
				GIVE_WEAPON_TO_CHAR w5_goons[4] WEAPONTYPE_TEC9 30000

				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
			 	CLEAR_AREA -2406.4 1544.7 1.1 1.0 TRUE
			 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2406.4 1544.7 1.1 w5_goons[5]  
				SET_CHAR_HEADING w5_goons[5] 256.3
				SET_CHAR_DECISION_MAKER w5_goons[5] w5_empty_decisions
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_goons[5] TRUE 
				GIVE_WEAPON_TO_CHAR w5_goons[5] WEAPONTYPE_TEC9 30000
	
				IF NOT IS_CHAR_DEAD w5_goons[9] 
					OPEN_SEQUENCE_TASK w5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -2372.5 1551.0 1.1 PEDMOVE_WALK -1
					CLOSE_SEQUENCE_TASK w5_seq
					PERFORM_SEQUENCE_TASK w5_goons[9] w5_seq
					CLEAR_SEQUENCE_TASK w5_seq
					REMOVE_BLIP w5_blip 
					ADD_BLIP_FOR_CHAR w5_goons[9] w5_blip
				ENDIF
				
				//create refugee leader guy - this guy needs to be in the box to make sure player isn't throwing grenades at it
			 	CLEAR_AREA -2370.3 1551.9 1.2 30.0 TRUE
				CREATE_CHAR PEDTYPE_MISSION5 OMOBOAT -2370.3 1551.9 1.2 w5_refugees[3]
				SET_CHAR_HEADING w5_refugees[3] 96.2
				SET_CHAR_DECISION_MAKER w5_refugees[3] w5_empty_decisions
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER w5_refugees[3] TRUE 
				SET_CHAR_NEVER_TARGETTED w5_refugees[3] TRUE

				SET_FIXED_CAMERA_POSITION -2375.2 1555.9 1.6 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2368.9 1547.7 3.8 JUMP_CUT

				SWITCH_WIDESCREEN ON
				MAKE_PLAYER_GANG_DISAPPEAR
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			
				//setting player in place
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
				CLEAR_AREA -2435.6 1551.1 4.0 1.0 TRUE
				SET_CHAR_COORDINATES scplayer -2435.6 1551.1 4.0
				SET_CHAR_HEADING scplayer 280.8  
				
				DO_FADE 500 FADE_IN	
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				
				w5_skip_cutscene_flag = 1
				SKIP_CUTSCENE_START
				timera = 0
				w5_control_flag = 2
			ENDIF
		ENDIF
			
		IF w5_control_flag = 2  
			IF NOT IS_CHAR_DEAD w5_goons[9]
				IF LOCATE_CHAR_ANY_MEANS_2D w5_goons[9] -2372.5 1551.0 1.0 1.0 FALSE 
					//Keep it down, you want to bring the snakehead down here?
					w5_speech_goals = 6
					w5_speech_control_flag = 0
					GOSUB w5_dialogue_setup
					w5_control_flag = 3
				ENDIF
			ENDIF
		ENDIF 

		IF w5_control_flag = 3
			IF w5_speech_goals = 0   
				IF NOT IS_CHAR_DEAD w5_goons[9] 
					OPEN_SEQUENCE_TASK w5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -2376.8 1546.7 1.1 PEDMOVE_WALK -1
					CLOSE_SEQUENCE_TASK w5_seq
					PERFORM_SEQUENCE_TASK w5_goons[9] w5_seq
					CLEAR_SEQUENCE_TASK w5_seq
					w5_control_flag = 4
				ENDIF
			ENDIF
		ENDIF

		IF w5_control_flag = 4  
			IF timera > 6000 
				DO_FADE 500 FADE_OUT	
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				GOSUB w5_death_checks
				IF w5_deathcheck_flag = 1
					GOTO mission_wuzi5_failed
				ENDIF	
				
				IF NOT IS_CHAR_DEAD w5_goons[0]
					OPEN_SEQUENCE_TASK w5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -2410.7 1548.2 1.1 PEDMOVE_WALK -1
						TASK_ACHIEVE_HEADING -1 90.0
					CLOSE_SEQUENCE_TASK w5_seq
					PERFORM_SEQUENCE_TASK w5_goons[0] w5_seq
					CLEAR_SEQUENCE_TASK w5_seq
				ENDIF 
				
				IF NOT IS_CHAR_DEAD w5_goons[1]
					OPEN_SEQUENCE_TASK w5_seq
						TASK_PAUSE -1 10
						TASK_GO_STRAIGHT_TO_COORD -1 -2409.9 1551.0 1.1 PEDMOVE_WALK -1
						TASK_ACHIEVE_HEADING -1 181.1
					CLOSE_SEQUENCE_TASK w5_seq
					PERFORM_SEQUENCE_TASK w5_goons[1] w5_seq
					CLEAR_SEQUENCE_TASK w5_seq
				ENDIF 

				IF NOT IS_CHAR_DEAD w5_goons[2]
					OPEN_SEQUENCE_TASK w5_seq
						TASK_PAUSE -1 15
						TASK_GO_STRAIGHT_TO_COORD -1 -2406.3 1554.0 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2406.7 1553.8 1.1 PEDMOVE_WALK -1
						//TASK_ACHIEVE_HEADING -1 90.0
					CLOSE_SEQUENCE_TASK w5_seq
					PERFORM_SEQUENCE_TASK w5_goons[2] w5_seq
					CLEAR_SEQUENCE_TASK w5_seq
				ENDIF 
			
				SET_FIXED_CAMERA_POSITION -2414.9 1547.8 2.3 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2409.7 1549.5 1.9 JUMP_CUT
			
				DO_FADE 500 FADE_IN	
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				GOSUB w5_death_checks
				IF w5_deathcheck_flag = 1
					GOTO mission_wuzi5_failed
				ENDIF	
			
				//Not long until we're drinking cola in the free West, eh 
				w5_speech_goals = 7
				w5_speech_control_flag = 0
				w5_random_last_label = 1
				GOSUB w5_dialogue_setup 

				timera = 0
				w5_control_flag = 5
			ENDIF
		ENDIF
	
		IF w5_control_flag = 5
			IF NOT IS_CHAR_DEAD w5_goons[0]
				GET_SCRIPT_TASK_STATUS w5_goons[0] PERFORM_SEQUENCE_TASK task_status
				IF task_status = FINISHED_TASK
				
					IF NOT IS_CHAR_DEAD w5_goons[0] 
						OPEN_SEQUENCE_TASK w5_seq
							TASK_AIM_GUN_AT_COORD -1 -2417.5 1548.2 2.1 5000  
							TASK_KILL_CHAR_ON_FOOT -1 scplayer 
						CLOSE_SEQUENCE_TASK w5_seq
						PERFORM_SEQUENCE_TASK w5_goons[0] w5_seq
						CLEAR_SEQUENCE_TASK w5_seq
					ENDIF
					
					IF NOT IS_CHAR_DEAD w5_goons[1] 
						OPEN_SEQUENCE_TASK w5_seq
							TASK_GO_STRAIGHT_TO_COORD -1 -2405.0 1551.0 1.1 PEDMOVE_RUN -1
							TASK_ACHIEVE_HEADING -1 261.2
							TASK_CLIMB -1 TRUE							
							TASK_ACHIEVE_HEADING -1 90.0
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK w5_seq
						PERFORM_SEQUENCE_TASK w5_goons[1] w5_seq
						CLEAR_SEQUENCE_TASK w5_seq
					ENDIF
					
					IF NOT IS_CHAR_DEAD w5_goons[2] 
						OPEN_SEQUENCE_TASK w5_seq
							TASK_GO_STRAIGHT_TO_COORD -1 -2394.0 1554.4 1.1 PEDMOVE_SPRINT -1
							TASK_GO_STRAIGHT_TO_COORD -1 -2393.5 1545.1 1.1 PEDMOVE_SPRINT -1
							TASK_ACHIEVE_HEADING -1 0.0
						CLOSE_SEQUENCE_TASK w5_seq
						PERFORM_SEQUENCE_TASK w5_goons[2] w5_seq
						CLEAR_SEQUENCE_TASK w5_seq
					ENDIF

					//Hey, who the fuck are you?
					w5_speech_goals = 7
					w5_speech_control_flag = 1
					w5_random_last_label = 2
					GOSUB w5_dialogue_setup 

					w5_control_flag = 6
				ENDIF
			ENDIF
		ENDIF

		IF w5_control_flag = 6
			IF w5_speech_goals = 0
				timera = 0 
				w5_control_flag = 7	
			ENDIF
		ENDIF

		IF w5_control_flag = 7	
			IF timera > 800	
				w5_skip_cutscene_flag = 0
				SKIP_CUTSCENE_END
				GOSUB w5_death_checks
				IF w5_deathcheck_flag = 1
					GOTO mission_wuzi5_failed
				ENDIF
				
				//if cutscene is skipped
				IF w5_skip_cutscene_flag = 1
					CLEAR_PRINTS	 
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					w5_speech_goals = 0
					
					DO_FADE 500 FADE_OUT 
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					GOSUB w5_death_checks
					IF w5_deathcheck_flag = 1
						GOTO mission_wuzi5_failed
					ENDIF
					
					IF NOT IS_CHAR_DEAD w5_goons[0] 
						CLEAR_CHAR_TASKS_IMMEDIATELY w5_goons[0] 
						CLEAR_AREA -2410.7 1548.2 1.1 1.0 TRUE
						SET_CHAR_COORDINATES w5_goons[0] -2410.7 1548.2 1.1
						SET_CHAR_HEADING w5_goons[0] 90.0  
					ENDIF
				
					IF NOT IS_CHAR_DEAD w5_goons[1] 
						CLEAR_CHAR_TASKS_IMMEDIATELY w5_goons[1] 
						CLEAR_AREA -2405.0 1551.0 1.1 1.0 TRUE
						SET_CHAR_COORDINATES w5_goons[1] -2405.0 1551.0 1.1
						SET_CHAR_HEADING w5_goons[1] 90.0  
					ENDIF
			
					IF NOT IS_CHAR_DEAD w5_goons[2] 
						CLEAR_CHAR_TASKS_IMMEDIATELY w5_goons[2] 
						CLEAR_AREA -2393.5 1545.1 1.1 1.0 TRUE
						SET_CHAR_COORDINATES w5_goons[2] -2393.5 1545.1 1.1
						SET_CHAR_HEADING w5_goons[2] 0.0  
					ENDIF
				ENDIF
				
				CLEAR_MISSION_AUDIO 3

				IF NOT IS_CHAR_DEAD w5_goons[0]
					SET_CHAR_DECISION_MAKER w5_goons[0] w5_ped_decisions
				ENDIF

				IF NOT IS_CHAR_DEAD w5_goons[1]
					SET_CHAR_DECISION_MAKER w5_goons[1] w5_ped_decisions
				ENDIF
			
				IF NOT IS_CHAR_DEAD w5_goons[2]
					SET_CHAR_DECISION_MAKER w5_goons[2] w5_ped_decisions
				ENDIF
	
				IF NOT IS_CHAR_DEAD w5_goons[3]
					SET_CHAR_DECISION_MAKER w5_goons[3] w5_ped_decisions
				ENDIF

				IF NOT IS_CHAR_DEAD w5_goons[4]
					SET_CHAR_DECISION_MAKER w5_goons[4] w5_ped_decisions
				ENDIF

				IF NOT IS_CHAR_DEAD w5_goons[5]
					SET_CHAR_DECISION_MAKER w5_goons[5] w5_ped_decisions
				ENDIF

				IF NOT IS_CHAR_DEAD w5_goons[9]
					CLEAR_CHAR_TASKS w5_goons[9] 
					CLEAR_AREA -2377.0 1535.6 1.1 1.0 TRUE
					SET_CHAR_COORDINATES w5_goons[9] -2377.0 1535.6 1.1
					SET_CHAR_HEADING w5_goons[9] 90.0  
				ENDIF 
			
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
				CLEAR_AREA -2435.6 1551.1 4.0 1.0 TRUE
				SET_CHAR_COORDINATES scplayer -2435.6 1551.1 4.0
				SET_CHAR_HEADING scplayer 280.8  

				SHUT_ALL_CHARS_UP FALSE

				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SWITCH_WIDESCREEN OFF
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				SET_PLAYER_CONTROL player1 ON
				CLEAR_PRINTS
				PRINT_NOW ( WUZ4_05 ) 4000 1 //Take out the refugees guard.
			
				//skipping cutscene
				IF w5_skip_cutscene_flag = 1
					DO_FADE 500 FADE_IN 
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					GOSUB w5_death_checks
					IF w5_deathcheck_flag = 1
						GOTO mission_wuzi5_failed
					ENDIF
				ENDIF
				w5_speech_flag = 0
				w5_control_flag = 0
				w5_goals = 4
			ENDIF
		ENDIF
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////Going down to see the refugees//////////////////////////////////////////////////////////////////
	IF w5_goals = 4


	///////////////////// SPEECH FOR THIS SECTION //////////////////////////
		IF w5_control_flag < 5
			IF IS_CHAR_IN_AREA_3D scplayer -2381.7 1546.2 0.0 -2365.1 1556.1 6.5 FALSE
				IF w5_speech_goals = 0
					IF w5_speech_flag = 0
						// Hey you, help us!
						w5_speech_goals = 9
						w5_speech_control_flag = 0
						w5_random_last_label = 1
						GOSUB w5_dialogue_setup 
						w5_speech_flag = 1
					ENDIF
				ENDIF

				IF w5_speech_goals = 0
					IF w5_speech_flag = 1
						//Hey, please, help us!
						w5_speech_goals = 9
						w5_speech_control_flag = 1
						w5_random_last_label = 2
						GOSUB w5_dialogue_setup 
						w5_speech_flag = 2
					ENDIF 
				ENDIF
			ENDIF
		ENDIF



	///////////////////// REFUGEE DEATH CHECK //////////////////////////
		IF IS_CHAR_DEAD w5_refugees[3]
			CLEAR_PRINTS
			PRINT_NOW ( WUZ4_03 ) 4000 1 //One of the refugees died!
			GOTO mission_wuzi5_failed
		ENDIF


	//////////////////// MAIN BIT OF THE MISSION ////////////////////////////
		IF w5_control_flag = 0
			///////////////DEBUG////////////////////
			IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q 
				IF NOT IS_CHAR_DEAD w5_goons[9] 
					EXPLODE_CHAR_HEAD w5_goons[9]
				ENDIF 
				SET_CHAR_COORDINATES scplayer -2374.0 1551.0 2.0
			ENDIF
			///////////////DEBUG////////////////////

			IF NOT IS_CHAR_DEAD w5_goons[9]
				IF IS_CHAR_IN_AREA_3D scplayer -2370.4 1533.6 1.1 -2408.5 1537.4 5.2 FALSE
					//CLEAR_CHAR_LAST_DAMAGE_ENTITY w5_goons[9]
					TASK_SHOOT_AT_COORD w5_goons[9] -2400.1 1535.3 1.1 500
			
					//Stick this up your ass!
					w5_speech_goals = 8
					w5_speech_control_flag = 0
					GOSUB w5_dialogue_setup 

					w5_control_flag = 1
				ENDIF

				IF IS_CHAR_IN_AREA_3D scplayer -2375.1 1556.2 1.1 -2379.2 1537.3 5.2 FALSE
					//CLEAR_CHAR_LAST_DAMAGE_ENTITY w5_goons[9]
					TASK_SHOOT_AT_COORD w5_goons[9] -2377.0 1552.4 1.1 500
			
					//Stick this up your ass!
					w5_speech_goals = 8
					w5_speech_control_flag = 0
					GOSUB w5_dialogue_setup 

					w5_control_flag = 1
				ENDIF

				IF NOT IS_CHAR_IN_AREA_3D scplayer -2375.1 1556.2 1.1 -2379.2 1537.3 5.2 FALSE
					IF NOT IS_CHAR_IN_AREA_3D scplayer -2370.4 1533.6 1.1 -2408.5 1537.4 5.2 FALSE 
						IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR w5_goons[9] scplayer 
							REMOVE_WEAPON_FROM_CHAR w5_goons[9] WEAPONTYPE_GRENADE
							GIVE_WEAPON_TO_CHAR w5_goons[9] WEAPONTYPE_TEC9 30000
							SET_CURRENT_CHAR_WEAPON w5_goons[9] WEAPONTYPE_TEC9 
							TASK_KILL_CHAR_ON_FOOT w5_goons[9] scplayer 
							w5_control_flag = 3
						ENDIF
					ENDIF
				ENDIF
			ELSE
				w5_control_flag = 3
			ENDIF
		ENDIF
						
		IF w5_control_flag = 1
			IF NOT IS_CHAR_DEAD w5_goons[9]
				GET_SCRIPT_TASK_STATUS w5_goons[9] TASK_SHOOT_AT_COORD task_status 
				IF task_status = FINISHED_TASK
					GIVE_WEAPON_TO_CHAR w5_goons[9] WEAPONTYPE_TEC9 30000
					OPEN_SEQUENCE_TASK w5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -2377.4 1539.1 1.1 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 90.0
					CLOSE_SEQUENCE_TASK w5_seq
					PERFORM_SEQUENCE_TASK w5_goons[9] w5_seq
					CLEAR_SEQUENCE_TASK w5_seq
					w5_control_flag = 2
				ENDIF
			ELSE
				w5_control_flag = 3
			ENDIF	
		ENDIF
	
		IF w5_control_flag = 2
			IF NOT IS_CHAR_DEAD w5_goons[9]
				GET_SCRIPT_TASK_STATUS w5_goons[9] PERFORM_SEQUENCE_TASK task_status 
				IF task_status = FINISHED_TASK
					IF IS_CHAR_IN_AREA_3D scplayer -2370.4 1533.6 0.0 -2408.5 1537.4 4.0 FALSE
					OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR scplayer w5_goons[9]
						OPEN_SEQUENCE_TASK w5_seq
							TASK_WEAPON_ROLL -1 FALSE
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK w5_seq
						PERFORM_SEQUENCE_TASK w5_goons[9] w5_seq
						CLEAR_SEQUENCE_TASK w5_seq
						w5_control_flag = 3
					ENDIF 
				
					IF IS_CHAR_IN_AREA_3D scplayer -2375.1 1556.2 1.1 -2379.2 1537.3 5.2 FALSE
						OPEN_SEQUENCE_TASK w5_seq
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK w5_seq
						PERFORM_SEQUENCE_TASK w5_goons[9] w5_seq
						CLEAR_SEQUENCE_TASK w5_seq
						w5_control_flag = 3
					ENDIF 
				ENDIF
			ELSE
				w5_control_flag = 3
			ENDIF	
		ENDIF

		IF w5_control_flag = 3
			IF IS_CHAR_DEAD w5_goons[9]
				MAKE_OBJECT_TARGETTABLE w5_refugee_container_padlock TRUE
				REMOVE_BLIP w5_blip
				ADD_BLIP_FOR_OBJECT w5_refugee_container_padlock w5_blip
				IF DOES_OBJECT_EXIST w5_refugee_container_padlock 		  ///debug
					GET_OBJECT_HEALTH w5_refugee_container_padlock w5_debug_health
					IF NOT w5_debug_health < 999  
						PRINT_NOW ( WUZ4_09 ) 11000 1 //Shoot the padlock on the front of the container.
					ENDIF
				ENDIF
				w5_control_flag = 4
			ENDIF
		ENDIF

		//waiting for player to destroy padlock
		IF w5_control_flag = 4
			IF DOES_OBJECT_EXIST w5_refugee_container_padlock 		  ///debug
				GET_OBJECT_HEALTH w5_refugee_container_padlock w5_debug_health
				IF w5_debug_health < 999  
					BREAK_OBJECT w5_refugee_container_padlock FALSE 
					timera = 0 
					w5_control_flag = 5
				ENDIF
			ELSE
				timera = 0
				w5_control_flag = 5	
			ENDIF
		ENDIF


		IF w5_control_flag = 5
			IF timera > 1000
				/*
				DO_FADE 500 FADE_OUT 
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				GOSUB w5_death_checks
				IF w5_deathcheck_flag = 1
					GOTO mission_wuzi5_failed
				ENDIF
				*/
				CLEAR_PRINTS	 
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				w5_speech_goals = 0
				
				CLEAR_PRINTS 
				SET_PLAYER_CONTROL player1 OFF
				SWITCH_WIDESCREEN ON
				MAKE_PLAYER_GANG_DISAPPEAR

				SHUT_ALL_CHARS_UP TRUE

				DELETE_OBJECT w5_refugee_container_padlock
				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE w5_empty_decisions EVENT_POTENTIAL_WALK_INTO_PED
				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE w5_empty_decisions EVENT_POTENTIAL_WALK_INTO_OBJECT
			
				MARK_MODEL_AS_NO_LONGER_NEEDED grenade
				
				LOAD_MISSION_AUDIO 3 SOUND_BANK_DA_NANG
				WHILE NOT HAS_MISSION_AUDIO_LOADED 3
					WAIT 0 
				ENDWHILE  
				
				REQUEST_MODEL KATANA	
				REQUEST_MODEL OMOKUNG
				REQUEST_MODEL OMOST
				REQUEST_ANIMATION GANGS

				LOAD_ALL_MODELS_NOW

				GOSUB w5_creating_refugees
					
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			 	CLEAR_AREA -2373.9 1552.1 1.1 1.0 TRUE
				SET_CHAR_COORDINATES scplayer -2373.9 1552.1 1.1
				SET_CHAR_HEADING scplayer 267.0  

				SET_FIXED_CAMERA_POSITION -2375.0 1547.2 2.0 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2370.9 1555.3 2.7 JUMP_CUT
				
				//creating last guys
				/*
				//bodyguard 1 in cabin leaning against the wall 
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
			 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2477.0 1550.6 32.2 w5_cabin_crew[0] 
				SET_CHAR_HEADING w5_cabin_crew[0] 162.8
				SET_CHAR_DECISION_MAKER w5_cabin_crew[0] w5_empty_decisions
				GIVE_WEAPON_TO_CHAR w5_cabin_crew[0] WEAPONTYPE_TEC9 3000
				SET_CHAR_STAY_IN_SAME_PLACE w5_cabin_crew[0] TRUE

				//bodyguard 2 in cabin next to head honcho 
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
			 	CLEAR_AREA -2470.8 1544.8 32.2 1.0 TRUE
			 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2470.8 1544.8 32.2 w5_cabin_crew[1] 
				SET_CHAR_HEADING w5_cabin_crew[1] 270.0
				SET_CHAR_DECISION_MAKER w5_cabin_crew[1] w5_ped_decisions
				GIVE_WEAPON_TO_CHAR w5_cabin_crew[1] WEAPONTYPE_TEC9 3000
				SET_CHAR_STAY_IN_SAME_PLACE w5_cabin_crew[1] TRUE
				ADD_BLIP_FOR_CHAR w5_cabin_crew[1] w5_blip2
				CHANGE_BLIP_SCALE w5_blip2 1
				*/

				//bodyguard 3 on stairs right  
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
			 	CLEAR_AREA -2473.3 1536.3 27.8 1.0 TRUE
			 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2473.3 1536.3 27.8 w5_cabin_crew[2] 
				SET_CHAR_HEADING w5_cabin_crew[2] 12.6
				SET_CHAR_DECISION_MAKER w5_cabin_crew[2] w5_ped_decisions
				GIVE_WEAPON_TO_CHAR w5_cabin_crew[2] WEAPONTYPE_TEC9 3000
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_cabin_crew[2] TRUE

				//bodyguard 4 on stairs left 
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
			 	CLEAR_AREA -2476.3 1536.3 27.8 1.0 TRUE
			 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2476.3 1536.3 27.8 w5_cabin_crew[3] 
				SET_CHAR_HEADING w5_cabin_crew[3] 321.5
				SET_CHAR_DECISION_MAKER w5_cabin_crew[3] w5_ped_decisions
				GIVE_WEAPON_TO_CHAR w5_cabin_crew[3] WEAPONTYPE_TEC9 3000
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_cabin_crew[3] TRUE

				//bodyguard 5 on walkway 
				GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
			 	CLEAR_AREA -2470.3 1546.9 22.6 1.0 TRUE
			 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2470.3 1546.9 22.6 w5_cabin_crew[4] 
				SET_CHAR_HEADING w5_cabin_crew[4] 265.4
				SET_CHAR_DECISION_MAKER w5_cabin_crew[4] w5_ped_decisions
				GIVE_WEAPON_TO_CHAR w5_cabin_crew[4] WEAPONTYPE_TEC9 3000
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_cabin_crew[4] TRUE

				//head honcho with katana 
			 	CLEAR_AREA -2471.5 1548.2 32.2 1.0 TRUE
			 	CREATE_CHAR PEDTYPE_MISSION2 OMOKUNG -2471.5 1548.2 32.2 w5_head_honcho  
				SET_CHAR_HEADING w5_head_honcho 130.0
				GIVE_WEAPON_TO_CHAR w5_head_honcho WEAPONTYPE_KATANA 1
				SET_CHAR_SHOOT_RATE w5_head_honcho 70 
				//SET_CHAR_KINDA_STAY_IN_SAME_PLACE w5_head_honcho TRUE
				REMOVE_BLIP w5_blip
				ADD_BLIP_FOR_CHAR w5_head_honcho w5_blip
				CHANGE_BLIP_SCALE w5_blip 1
				SET_CHAR_DECISION_MAKER w5_head_honcho w5_empty_decisions

				TASK_PLAY_ANIM_NON_INTERRUPTABLE w5_head_honcho Tai_Chi_Loop PARK 4.0 TRUE FALSE FALSE FALSE -1
				
				
				/*
				DO_FADE 500 FADE_IN 
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				GOSUB w5_death_checks
				IF w5_deathcheck_flag = 1
					GOTO mission_wuzi5_failed
				ENDIF
				*/
			
				w5_skip_cutscene_flag = 1
				SKIP_CUTSCENE_START

				//playing the doors opening sound
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -2371.55 1552.66 1.245 SOUND_DA_NANG_CONTAINER_OPEN
				timera = 0
				w5_control_flag = 6
			ENDIF
		ENDIF
			

		IF w5_control_flag = 6
			//OPENING doors
			IF DOES_OBJECT_EXIST w5_refugee_container_LD
				ROTATE_OBJECT w5_refugee_container_LD 180.0 2.0 FALSE
			ENDIF

			IF DOES_OBJECT_EXIST w5_refugee_container_RD
				ROTATE_OBJECT w5_refugee_container_RD 360.0 2.0 FALSE
			ENDIF
		
			IF NOT IS_CHAR_DEAD w5_refugees[3] 				
				OPEN_SEQUENCE_TASK w5_seq
					TASK_PAUSE -1 1000
					TASK_GO_STRAIGHT_TO_COORD -1 -2372.8 1551.8 1.1 PEDMOVE_WALK -1
					TASK_GO_STRAIGHT_TO_COORD -1 -2374.4 1551.9 1.1 PEDMOVE_WALK -1
					TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 
				CLOSE_SEQUENCE_TASK w5_seq
				PERFORM_SEQUENCE_TASK w5_refugees[3] w5_seq
				CLEAR_SEQUENCE_TASK w5_seq
			ENDIF
			
			IF timera > 2000 
				IF NOT IS_CHAR_DEAD w5_refugees[0] 				
					OPEN_SEQUENCE_TASK w5_seq
						TASK_PAUSE -1 900
				 		TASK_GO_STRAIGHT_TO_COORD -1 -2372.8 1552.1 1.1 PEDMOVE_WALK -1
				 		TASK_GO_STRAIGHT_TO_COORD -1 -2373.4 1553.3 1.1 PEDMOVE_WALK -1
						TASK_PLAY_ANIM -1 idle_tired PED 4.0 FALSE FALSE FALSE FALSE 4000
					CLOSE_SEQUENCE_TASK w5_seq
					PERFORM_SEQUENCE_TASK w5_refugees[0] w5_seq
					CLEAR_SEQUENCE_TASK w5_seq
				ENDIF

			   	IF NOT IS_CHAR_DEAD w5_refugees[1] 				
					OPEN_SEQUENCE_TASK w5_seq
						TASK_PAUSE -1 2000
				 		TASK_GO_STRAIGHT_TO_COORD -1 -2372.8 1552.1 1.1 PEDMOVE_WALK -1
				 		TASK_GO_STRAIGHT_TO_COORD -1 -2374.1 1550.6 1.1 PEDMOVE_WALK -1
						TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 
					CLOSE_SEQUENCE_TASK w5_seq
					PERFORM_SEQUENCE_TASK w5_refugees[1] w5_seq
					CLEAR_SEQUENCE_TASK w5_seq
				ENDIF
			
				IF NOT IS_CHAR_DEAD w5_refugees[2] 				
					OPEN_SEQUENCE_TASK w5_seq
						TASK_PAUSE -1 2500
				 		TASK_GO_STRAIGHT_TO_COORD -1 -2372.8 1552.1 1.1 PEDMOVE_WALK -1
				 		TASK_ACHIEVE_HEADING -1 6.7
						//TASK_PLAY_ANIM -1 idle_tired PED 4.0 FALSE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK w5_seq
					PERFORM_SEQUENCE_TASK w5_refugees[2] w5_seq
					CLEAR_SEQUENCE_TASK w5_seq
				ENDIF

		 		CLEAR_AREA -2376.1 1552.5 1.1 1.0 TRUE
				SET_CHAR_COORDINATES scplayer -2376.1 1552.5 1.1
				SET_CHAR_HEADING scplayer 264.4   
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

				SET_FIXED_CAMERA_POSITION -2376.9 1554.3 2.0 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2370.6 1549.7 2.7 JUMP_CUT
		 
				timera = 0
				w5_control_flag = 7
			ENDIF
		ENDIF

		IF w5_control_flag = 7
			IF NOT IS_CHAR_DEAD w5_refugees[3] 
				GET_SCRIPT_TASK_STATUS w5_refugees[3] PERFORM_SEQUENCE_TASK task_status	 
				IF task_status = FINISHED_TASK
				
					OPEN_SEQUENCE_TASK w5_seq
						TASK_PLAY_ANIM -1 prtial_gngtlkE GANGS 4.0 FALSE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK w5_seq
					PERFORM_SEQUENCE_TASK w5_refugees[3] w5_seq
					CLEAR_SEQUENCE_TASK w5_seq
					
					//refugees talking to player
					w5_speech_goals = 10
					w5_speech_control_flag = 0
					w5_random_last_label = 2
					GOSUB w5_dialogue_setup

					w5_control_flag = 8
				ENDIF
			ENDIF
		ENDIF 

		IF w5_control_flag = 8
			IF timera > 6000
				IF w5_speech_goals = 0
					DO_FADE 500 FADE_OUT 
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					GOSUB w5_death_checks
					IF w5_deathcheck_flag = 1
						GOTO mission_wuzi5_failed
					ENDIF
					
					LOAD_SCENE -2465.5 1547.1 22.7

					IF NOT IS_CHAR_DEAD w5_cabin_crew[4] 
						OPEN_SEQUENCE_TASK w5_seq
							TASK_GO_STRAIGHT_TO_COORD -1 -2468.9 1547.2 22.7 PEDMOVE_WALK -1
							TASK_GO_STRAIGHT_TO_COORD -1 -2465.5 1547.1 22.7 PEDMOVE_WALK -1
						CLOSE_SEQUENCE_TASK w5_seq
						PERFORM_SEQUENCE_TASK w5_cabin_crew[4] w5_seq
						CLEAR_SEQUENCE_TASK w5_seq
					ENDIF	  

					SET_FIXED_CAMERA_POSITION -2460.0 1552.1 22.9 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT -2468.1 1543.8 25.9 JUMP_CUT
					
					DO_FADE 500 FADE_IN 
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					GOSUB w5_death_checks
					IF w5_deathcheck_flag = 1
						GOTO mission_wuzi5_failed
					ENDIF

					//playing doors opening sound.
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION -2468.0 1547.3 22.8 SOUND_DA_NANG_HEAVY_DOOR_OPEN

					timera = 0
					w5_control_flag = 9
				ENDIF
			ENDIF
		ENDIF
		
		IF w5_control_flag = 9
			IF DOES_OBJECT_EXIST w5_storm_freighter_door 
				ROTATE_OBJECT w5_storm_freighter_door 90.0 2.0 FALSE
			ENDIF
			
			IF timera > 6000
				DO_FADE 500 FADE_OUT 
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				GOSUB w5_death_checks
				IF w5_deathcheck_flag = 1
					GOTO mission_wuzi5_failed
				ENDIF
				
				LOAD_SCENE -2471.5 1548.2 32.2

				SET_FIXED_CAMERA_POSITION -2476.5 1546.5 33.8 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2462.6 1546.8 32.7 JUMP_CUT
				
				DO_FADE 500 FADE_IN 
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				GOSUB w5_death_checks
				IF w5_deathcheck_flag = 1
					GOTO mission_wuzi5_failed
				ENDIF

				//The Snakehead is up on the bridge...
				w5_speech_goals = 10
				w5_speech_control_flag = 2
				w5_random_last_label = 3
				GOSUB w5_dialogue_setup 
				
				timera = 0
				w5_control_flag = 10
			ENDIF
		ENDIF
	
		IF w5_control_flag = 10
			IF timera > 7000
				w5_skip_cutscene_flag = 0
				SKIP_CUTSCENE_END
				GOSUB w5_death_checks
				IF w5_deathcheck_flag = 1
					GOTO mission_wuzi5_failed
				ENDIF
		
				IF w5_skip_cutscene_flag = 1
					CLEAR_PRINTS	 
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					w5_speech_goals = 0
				
					DO_FADE 500 FADE_OUT 
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					GOSUB w5_death_checks
					IF w5_deathcheck_flag = 1
						GOTO mission_wuzi5_failed
					ENDIF
				
				ENDIF
					
				CLEAR_MISSION_AUDIO 3

				IF NOT IS_CHAR_DEAD w5_refugees[0]
					CLEAR_CHAR_TASKS_IMMEDIATELY w5_refugees[0] 
					CLEAR_AREA -2373.4 1553.3 1.1 1.0 TRUE
					SET_CHAR_COORDINATES w5_refugees[0] -2373.4 1553.3 1.1
					SET_CHAR_HEADING w5_refugees[0] 120.0
					OPEN_SEQUENCE_TASK w5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -2377.1 1546.4 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2377.2 1535.3 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2404.0 1534.8 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2407.6 1544.3 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2393.1 1546.5 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2393.4 1554.2 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2405.2 1554.5 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2432.3 1544.7 1.1 PEDMOVE_WALK -1
					CLOSE_SEQUENCE_TASK w5_seq
					PERFORM_SEQUENCE_TASK w5_refugees[0] w5_seq
					CLEAR_SEQUENCE_TASK w5_seq
				ENDIF
			
				IF NOT IS_CHAR_DEAD w5_refugees[1]
					CLEAR_CHAR_TASKS_IMMEDIATELY w5_refugees[1] 
					CLEAR_AREA -2374.1 1550.6 1.1 1.0 TRUE
					SET_CHAR_COORDINATES w5_refugees[1] -2374.1 1550.6 1.1
					SET_CHAR_HEADING w5_refugees[1] 63.1
					OPEN_SEQUENCE_TASK w5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -2377.1 1546.4 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2377.2 1535.3 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2404.0 1534.8 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2407.6 1544.3 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2393.1 1546.5 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2393.4 1554.2 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2405.2 1554.5 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2430.6 1543.7 1.1 PEDMOVE_WALK -1
					CLOSE_SEQUENCE_TASK w5_seq
					PERFORM_SEQUENCE_TASK w5_refugees[1] w5_seq
					CLEAR_SEQUENCE_TASK w5_seq
				ENDIF

				IF NOT IS_CHAR_DEAD w5_refugees[2]
					CLEAR_CHAR_TASKS_IMMEDIATELY w5_refugees[2] 
					CLEAR_AREA -2372.8 1552.1 1.1 1.0 TRUE
					SET_CHAR_COORDINATES w5_refugees[2] -2372.8 1552.1 1.1
					SET_CHAR_HEADING w5_refugees[2] 90.0
					OPEN_SEQUENCE_TASK w5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -2377.1 1546.4 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2377.2 1535.3 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2404.0 1534.8 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2407.6 1544.3 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2393.1 1546.5 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2393.4 1554.2 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2405.2 1554.5 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2430.4 1545.8 1.1 PEDMOVE_WALK -1
					CLOSE_SEQUENCE_TASK w5_seq
					PERFORM_SEQUENCE_TASK w5_refugees[2] w5_seq
					CLEAR_SEQUENCE_TASK w5_seq
				ENDIF

				IF NOT IS_CHAR_DEAD w5_refugees[3]
					CLEAR_CHAR_TASKS_IMMEDIATELY w5_refugees[3] 
					CLEAR_AREA -2374.4 1551.9 1.1 1.0 TRUE
					SET_CHAR_COORDINATES w5_refugees[3] -2374.4 1551.9 1.1
					SET_CHAR_HEADING w5_refugees[3] 90.0
					OPEN_SEQUENCE_TASK w5_seq
						TASK_GO_STRAIGHT_TO_COORD -1 -2377.1 1546.4 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2377.2 1535.3 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2404.0 1534.8 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2407.6 1544.3 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2393.1 1546.5 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2393.4 1554.2 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2405.2 1554.5 1.1 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 -2428.4 1544.6 1.1 PEDMOVE_WALK -1
					CLOSE_SEQUENCE_TASK w5_seq
					PERFORM_SEQUENCE_TASK w5_refugees[3] w5_seq
					CLEAR_SEQUENCE_TASK w5_seq
				ENDIF

				IF DOES_OBJECT_EXIST w5_refugee_container_LD
					ROTATE_OBJECT w5_refugee_container_LD 180.0 360.0 FALSE
				ENDIF

				IF DOES_OBJECT_EXIST w5_refugee_container_RD
					ROTATE_OBJECT w5_refugee_container_RD 360.0 360.0 FALSE
				ENDIF
				
				IF DOES_OBJECT_EXIST w5_storm_freighter_door 
					ROTATE_OBJECT w5_storm_freighter_door 90.0 100.0 FALSE
				ENDIF
					
				IF NOT IS_CHAR_DEAD w5_cabin_crew[4] 
					CLEAR_AREA -2466.9 1546.9 22.7 30.0 TRUE
					SET_CHAR_COORDINATES w5_cabin_crew[4] -2466.9 1546.9 22.7
				ENDIF
				IF NOT IS_CHAR_DEAD w5_head_honcho 
					CLEAR_CHAR_TASKS w5_head_honcho
				ENDIF
				
				MARK_MODEL_AS_NO_LONGER_NEEDED CJ_Padlock
				
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE w5_empty_decisions EVENT_POTENTIAL_WALK_INTO_OBJECT TASK_COMPLEX_WALK_ROUND_OBJECT 0.0 100.0 0.0 0.0 FALSE TRUE 	
			
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE w5_empty_decisions EVENT_POTENTIAL_WALK_INTO_PED TASK_COMPLEX_AVOID_OTHER_PED_WHILE_WANDERING 0.0 100.0 0.0 0.0 FALSE TRUE 	

				SHUT_ALL_CHARS_UP FALSE

				SET_CHAR_HEALTH scplayer 100 

				REMOVE_ANIMATION PARK
				REMOVE_ANIMATION GANGS
				CLEAR_PRINTS
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SWITCH_WIDESCREEN OFF
				MAKE_PLAYER_GANG_REAPPEAR
				RESTORE_CAMERA_JUMPCUT
				SET_PLAYER_CONTROL player1 ON
				PRINT_NOW ( BRIDGE ) 7000 1 //Leave no one alive on the bridge! 
			
				//skipping cutscene
				IF w5_skip_cutscene_flag = 1
					DO_FADE 500 FADE_IN 
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					GOSUB w5_death_checks
					IF w5_deathcheck_flag = 1
						GOTO mission_wuzi5_failed
					ENDIF
				ENDIF
			
				w5_control_flag = 0
				w5_goals = 5
			ENDIF
		ENDIF
	ENDIF



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////Player has to make his way up to kill the katanaboss////////////////////////////////////////////

	IF w5_goals = 5
		// REFUGEE DEATH CHECKS
		IF w5_goals = 5 
			IF w5_refugees_flag < 4
				IF IS_CHAR_DEAD w5_refugees[w5_refugees_flag]
					CLEAR_PRINTS
					PRINT_NOW ( WUZ4_03 ) 4000 1 //One of the refugees died!	  
					GOTO mission_wuzi5_failed
				ENDIF
				w5_refugees_flag ++
			ELSE
				w5_refugees_flag = 0
			ENDIF
		ENDIF

		// MAIN BIT
		IF w5_control_flag = 0
			///////////////DEBUG////////////////////
			IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q 
				SET_CHAR_COORDINATES scplayer -2477.4 1537.0 27.8
			ENDIF
			///////////////DEBUG////////////////////
		
			IF IS_CHAR_IN_AREA_3D scplayer -2476.4 1538.0 29.8 -2479.2 1550.5 35.2 FALSE  
				DO_FADE 1000 FADE_OUT 
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				GOSUB w5_death_checks
				IF w5_deathcheck_flag = 1
					GOTO mission_wuzi5_failed
				ENDIF
			
				CLEAR_PRINTS	 
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				w5_speech_goals = 0
			
				/*
				IF NOT IS_CHAR_DEAD w5_cabin_crew[1] 
					CLEAR_CHAR_TASKS_IMMEDIATELY w5_cabin_crew[1] 
		 			CLEAR_AREA -2472.3 1552.6 32.2 1.0 TRUE
					SET_CHAR_COORDINATES w5_cabin_crew[1] -2472.3 1552.6 32.2 
					SET_CHAR_HEADING w5_cabin_crew[1] 145.8 
					SET_CHAR_DECISION_MAKER w5_cabin_crew[1] w5_empty_decisions
				ENDIF
				*/

	 			CLEAR_AREA -2466.9 1546.9 22.7 30.0 TRUE

	 			CLEAR_AREA -2478.4 1539.0 29.5 1.0 TRUE
				SET_CHAR_COORDINATES scplayer -2478.4 1539.0 29.5
				SET_CHAR_HEADING scplayer 0.0 
				SET_PLAYER_CONTROL player1 OFF
				SWITCH_WIDESCREEN ON
				MAKE_PLAYER_GANG_DISAPPEAR
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

				SHUT_ALL_CHARS_UP TRUE

				REQUEST_MODEL Katana_Anim
				REQUEST_MODEL Katana_LHand
				REQUEST_ANIMATION MISC

				LOAD_ALL_MODELS_NOW
			
				CREATE_OBJECT Katana_Anim -2472.6 1538.5 32.3 w5_kat_anim
					
				IF NOT IS_CHAR_DEAD w5_head_honcho
					CLEAR_CHAR_TASKS_IMMEDIATELY w5_head_honcho
					CLEAR_AREA -2472.3 1551.5 32.2 1.0 TRUE
					SET_CHAR_COORDINATES w5_head_honcho -2472.3 1551.5 32.2
					SET_CHAR_HEADING w5_head_honcho 135.0  
					CREATE_OBJECT Katana_LHand -2117.0 654.0 59.7 w5_kat_lh
					TASK_PICK_UP_OBJECT	w5_head_honcho w5_kat_lh 0.0 0.0 0.0 PED_HANDL HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE
					
				ENDIF
				IF NOT IS_CHAR_DEAD w5_cabin_crew[2]
					SET_CHAR_DECISION_MAKER w5_cabin_crew[2] w5_empty_decisions	
					CLEAR_CHAR_TASKS_IMMEDIATELY w5_cabin_crew[2]
				ENDIF
			
				IF NOT IS_CHAR_DEAD w5_cabin_crew[3]
					SET_CHAR_DECISION_MAKER w5_cabin_crew[3] w5_empty_decisions	
					CLEAR_CHAR_TASKS_IMMEDIATELY w5_cabin_crew[3]
				ENDIF

				IF NOT IS_CHAR_DEAD w5_cabin_crew[4]
					SET_CHAR_DECISION_MAKER w5_cabin_crew[4] w5_empty_decisions	
					CLEAR_CHAR_TASKS_IMMEDIATELY w5_cabin_crew[4]
				ENDIF
				
				IF NOT IS_CHAR_DEAD w5_head_honcho
					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS w5_head_honcho 0.0 4.0 -1.0 w5_x w5_y w5_z
				ENDIF
				OPEN_SEQUENCE_TASK w5_seq
					TASK_GO_STRAIGHT_TO_COORD -1 -2477.9 1543.7 31.7 PEDMOVE_WALK -1
					TASK_GO_STRAIGHT_TO_COORD -1 w5_x w5_y w5_z PEDMOVE_WALK -1
					IF NOT IS_CHAR_DEAD w5_head_honcho 
						TASK_TURN_CHAR_TO_FACE_CHAR -1 w5_head_honcho
					ENDIF
				CLOSE_SEQUENCE_TASK w5_seq
				PERFORM_SEQUENCE_TASK scplayer w5_seq
				CLEAR_SEQUENCE_TASK w5_seq
				
				SET_FIXED_CAMERA_POSITION -2472.8 1554.0 33.5 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2474.5 1542.2 33.2 JUMP_CUT

				DO_FADE 1000 FADE_IN 
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				GOSUB w5_death_checks
				IF w5_deathcheck_flag = 1
					GOTO mission_wuzi5_failed
				ENDIF
				
				timera = 0 
				w5_skip_cutscene_flag = 1
				SKIP_CUTSCENE_START
				
				w5_control_flag = 1
			ENDIF
		ENDIF
		
		IF w5_control_flag = 1
			GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK task_status
			IF task_status = FINISHED_TASK
			
				//Enough! We settle this here!
				w5_speech_goals = 13
				w5_speech_control_flag = 0
				GOSUB w5_dialogue_setup 

				CLEAR_AREA w5_x w5_y w5_z 1.0 TRUE 
				SET_CHAR_COORDINATES scplayer w5_x w5_y w5_z
				SET_CHAR_HEADING scplayer 315.0  

				IF NOT IS_CHAR_DEAD w5_head_honcho 
					IF DOES_OBJECT_EXIST w5_kat_lh
						DELETE_OBJECT w5_kat_lh  
						GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS w5_head_honcho 0.0 2.0 -1.0 w5_x w5_y w5_z
						SET_OBJECT_COORDINATES w5_kat_anim w5_x w5_y w5_z 
						SET_OBJECT_HEADING w5_kat_anim 315.0
						GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS w5_head_honcho 0.0 4.0 0.0 w5_x w5_y w5_z
						CLEAR_CHAR_TASKS_IMMEDIATELY w5_head_honcho
						TASK_PLAY_ANIM w5_head_honcho KAT_Throw_K MISC 1000.0 FALSE FALSE FALSE FALSE -1
						CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
						TASK_PLAY_ANIM scplayer KAT_Throw_P MISC 1000.0 FALSE FALSE FALSE FALSE -1
						
						SET_FIXED_CAMERA_POSITION -2475.1 1547.1 33.3 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT -2472.2 1553.0 33.7 JUMP_CUT
						
						w5_control_flag = 3
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	
		IF w5_control_flag = 3
			IF DOES_OBJECT_EXIST w5_kat_anim

				IF NOT IS_CHAR_DEAD w5_head_honcho
					IF IS_CHAR_PLAYING_ANIM scplayer KAT_Throw_P
						GET_CHAR_ANIM_CURRENT_TIME scplayer KAT_Throw_P w5_anim_time
						IF IS_OBJECT_PLAYING_ANIM w5_kat_anim KAT_Throw_O
							SET_OBJECT_ANIM_CURRENT_TIME w5_kat_anim KAT_Throw_O w5_anim_time
						ELSE
							IF PLAY_OBJECT_ANIM w5_kat_anim KAT_Throw_O MISC 10000.0 FALSE FALSE
								SET_OBJECT_ANIM_SPEED w5_kat_anim KAT_Throw_O 0.0
							ENDIF
						ENDIF
					ENDIF

					IF IS_CHAR_PLAYING_ANIM scplayer KAT_Throw_P
						GET_CHAR_ANIM_CURRENT_TIME scplayer KAT_Throw_P w5_anim_time 
						IF w5_anim_time = 1.0
							DELETE_OBJECT w5_kat_anim
							w5_control_flag = 4
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF w5_control_flag = 4
			IF w5_speech_goals = 0 
				IF timera > 8000 
					w5_skip_cutscene_flag = 0
					SKIP_CUTSCENE_END
					GOSUB w5_death_checks
					IF w5_deathcheck_flag = 1
						GOTO mission_wuzi5_failed
					ENDIF
					
					IF w5_skip_cutscene_flag = 1 
						CLEAR_PRINTS	 				
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						w5_speech_goals = 0
					
						DO_FADE 500 FADE_OUT 
						WHILE GET_FADING_STATUS
						    WAIT 0
						ENDWHILE 
						GOSUB w5_death_checks
						IF w5_deathcheck_flag = 1
							GOTO mission_wuzi5_failed
						ENDIF
				
						DELETE_OBJECT w5_kat_lh
						DELETE_OBJECT w5_kat_anim
						IF NOT IS_CHAR_DEAD w5_head_honcho 
							CLEAR_CHAR_TASKS_IMMEDIATELY w5_head_honcho
						ENDIF
						CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
						CLEAR_AREA -2475.5 1546.7 32.2 1.0 TRUE
						SET_CHAR_COORDINATES scplayer -2475.5 1546.7 32.2 
						SET_CHAR_HEADING scplayer 315.0

					ENDIF
					
					IF NOT IS_CHAR_DEAD w5_refugees[0] 
						CLEAR_CHAR_TASKS_IMMEDIATELY w5_refugees[0] 
						CLEAR_AREA -2439.5 1561.3 16.3 1.0 TRUE
						SET_CHAR_COORDINATES w5_refugees[0] -2439.5 1561.3 16.3
						SET_CHAR_HEADING w5_refugees[0] 11.0
					ENDIF
				
					IF NOT IS_CHAR_DEAD w5_refugees[1]
						CLEAR_CHAR_TASKS_IMMEDIATELY w5_refugees[1] 
						CLEAR_AREA -2437.7 1561.5 16.3 1.0 TRUE
						SET_CHAR_COORDINATES w5_refugees[1] -2437.7 1561.5 16.3
						SET_CHAR_HEADING w5_refugees[1] 177.5
					ENDIF
					
					IF NOT IS_CHAR_DEAD w5_refugees[2]
						CLEAR_CHAR_TASKS_IMMEDIATELY w5_refugees[2] 
						CLEAR_AREA -2438.8 1560.0 16.3 1.0 TRUE
						SET_CHAR_COORDINATES w5_refugees[2] -2438.8 1560.0 16.3
						SET_CHAR_HEADING w5_refugees[2] 183.9
					ENDIF
					
					IF NOT IS_CHAR_DEAD w5_refugees[3]
						CLEAR_CHAR_TASKS_IMMEDIATELY w5_refugees[3] 
						CLEAR_AREA -2436.6 1561.8 16.3 1.0 TRUE
						SET_CHAR_COORDINATES w5_refugees[3] -2436.6 1561.8 16.3
						SET_CHAR_HEADING w5_refugees[3] 9.5
					ENDIF
						
					IF NOT IS_CHAR_DEAD w5_head_honcho
						CLEAR_AREA -2472.7 1551.4 32.2 1.0 TRUE
						SET_CHAR_COORDINATES w5_head_honcho -2472.7 1551.4 32.2 
						TASK_KILL_CHAR_ON_FOOT w5_head_honcho scplayer 
						SET_CHAR_DECISION_MAKER w5_head_honcho w5_ped_decisions
					ENDIF

					/*
					IF NOT IS_CHAR_DEAD w5_cabin_crew[1] 
						//SET_CHAR_COORDINATES w5_cabin_crew[1] -24472.2 1553.6 32.2 
						//SET_CHAR_HEADING w5_cabin_crew[1] 137.7 
						SET_CHAR_HEALTH w5_cabin_crew[1] 25 
					ENDIF
					*/
					IF NOT IS_CHAR_DEAD w5_cabin_crew[2]
						SET_CHAR_DECISION_MAKER w5_cabin_crew[2] w5_ped_decisions	
						CLEAR_CHAR_TASKS_IMMEDIATELY w5_cabin_crew[2]
					ENDIF
				
					IF NOT IS_CHAR_DEAD w5_cabin_crew[3]
						SET_CHAR_DECISION_MAKER w5_cabin_crew[3] w5_ped_decisions	
						CLEAR_CHAR_TASKS_IMMEDIATELY w5_cabin_crew[3]
					ENDIF

					IF NOT IS_CHAR_DEAD w5_cabin_crew[4]
						SET_CHAR_DECISION_MAKER w5_cabin_crew[4] w5_ped_decisions	
						CLEAR_CHAR_TASKS_IMMEDIATELY w5_cabin_crew[4]
					ENDIF

					SHUT_ALL_CHARS_UP FALSE
					
					GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_KATANA 1
					HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
					SET_PLAYER_CONTROL player1 ON
					SWITCH_WIDESCREEN OFF
					MAKE_PLAYER_GANG_REAPPEAR
					RESTORE_CAMERA_JUMPCUT
					SET_CAMERA_BEHIND_PLAYER
					SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_KATANA  
					//skipping cutscene
					IF w5_skip_cutscene_flag = 1
						DO_FADE 500 FADE_IN 
						WHILE GET_FADING_STATUS
						    WAIT 0
						ENDWHILE 
						GOSUB w5_death_checks
						IF w5_deathcheck_flag = 1
							GOTO mission_wuzi5_failed
						ENDIF
					ENDIF
					
					w5_control_flag = 5
				ENDIF
			ENDIF
		ENDIF

		IF IS_CHAR_DEAD w5_head_honcho
			REMOVE_BLIP w5_blip
		ENDIF
	
		/*
		IF IS_CHAR_DEAD w5_cabin_crew[1]
			REMOVE_BLIP w5_blip2
		ENDIF
		*/

		IF IS_CHAR_DEAD w5_head_honcho
			CLEAR_PRINTS 
			//
			
			REMOVE_BLIP w5_blip
			IF NOT IS_CHAR_DEAD w5_refugees[3]
				ADD_BLIP_FOR_CHAR w5_refugees[3] w5_blip
				SET_BLIP_AS_FRIENDLY w5_blip TRUE
			ENDIF  
			PRINT_NOW ( WUZ4_11 ) 4000 1 //Go back and meet the refugees.  They will lower some dinghys into the water.
			w5_control_flag = 0
			timera = 0	
			w5_goals = 6
			
			/*
			IF IS_CHAR_DEAD w5_cabin_crew[1]
				CLEAR_PRINTS 
				SET_PLAYER_CONTROL player1 OFF
				w5_control_flag = 0
				timera = 0	
				w5_goals = 6
			ELSE
				GET_SCRIPT_TASK_STATUS w5_cabin_crew[1] TASK_KILL_CHAR_ON_FOOT task_status
				IF task_status = FINISHED_TASK
					TASK_KILL_CHAR_ON_FOOT w5_cabin_crew[1] scplayer 
				ENDIF
			ENDIF
			*/
		ENDIF
	ENDIF




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////Final Cutscene showing player and refugees escaping ////////////////////////////////////////////
	IF w5_goals = 6
		IF w5_control_flag = 0
			IF w5_refugees_flag < 4
				IF IS_CHAR_DEAD w5_refugees[w5_refugees_flag]
					CLEAR_PRINTS
					PRINT_NOW ( WUZ4_03 ) 4000 1 //One of the refugees died!
					GOTO mission_wuzi5_failed
				ENDIF
				w5_refugees_flag ++
			ELSE
				w5_refugees_flag = 0
			ENDIF
	
			IF NOT IS_CHAR_DEAD w5_refugees[3] 
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer w5_refugees[3] 4.0 4.0 4.0 FALSE
					REMOVE_BLIP w5_blip
					SET_PLAYER_CONTROL player1 OFF
					w5_control_flag = 1
				ENDIF
			ENDIF
		ENDIF
	
		IF w5_control_flag = 1
			DO_FADE 2000 FADE_OUT
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE 
			w5_control_flag = 2
		ENDIF 
		IF w5_control_flag = 2
			CLEAR_PRINTS	 
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			w5_speech_goals = 0
		
			SWITCH_WIDESCREEN ON
			MAKE_PLAYER_GANG_DISAPPEAR
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			
			SHUT_ALL_CHARS_UP TRUE

			SET_FIXED_CAMERA_POSITION -2425.8 1562.4 1.3 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2451.2 1582.3 3.4 JUMP_CUT

			REQUEST_MODEL DINGHY
			LOAD_ALL_MODELS_NOW
			
 			CLEAR_AREA -2430.7 1563.6 0.0 5.0 TRUE
			CREATE_CAR DINGHY -2430.7 1563.6 0.0 w5_dinghys[0]
 			CLEAR_AREA -2442.4 1564.6 0.0 5.0 TRUE
			CREATE_CAR DINGHY -2442.4 1564.6 0.0 w5_dinghys[1]
 			CLEAR_AREA -2426.9 1570.6 0.0 5.0 TRUE
			CREATE_CAR DINGHY -2426.9 1570.6 0.0 w5_dinghys[2]

			IF NOT IS_CHAR_DEAD w5_refugees[0]
				ATTACH_CHAR_TO_CAR w5_refugees[0] w5_dinghys[1] 0.5 -1.0 1.0 FACING_FORWARD 360.0 WEAPONTYPE_UNARMED 
			ENDIF	

			IF NOT IS_CHAR_DEAD w5_refugees[1] 
				ATTACH_CHAR_TO_CAR w5_refugees[1] w5_dinghys[1] -0.5 -1.0 1.0 FACING_FORWARD 360.0 WEAPONTYPE_UNARMED
			ENDIF
			
			IF NOT IS_CHAR_DEAD w5_refugees[2]
				WARP_CHAR_INTO_CAR w5_refugees[2] w5_dinghys[1]
			ENDIF

			IF NOT IS_CHAR_DEAD w5_refugees[3]								
				WARP_CHAR_INTO_CAR w5_refugees[3] w5_dinghys[2]
			ENDIF
			
			WARP_CHAR_INTO_CAR scplayer w5_dinghys[0]
					
			//Thank you for everything!
			w5_speech_goals = 11
			w5_speech_control_flag = 0
			GOSUB w5_dialogue_setup 
	
			DO_FADE 2000 FADE_IN
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE 
			timera = 0 
			w5_control_flag = 3
		ENDIF

		IF w5_control_flag = 3
			IF timera > 2000
				IF NOT IS_CAR_DEAD w5_dinghys[1]
					SET_CAR_TEMP_ACTION w5_dinghys[1] TEMPACT_GOFORWARD 5000
				ENDIF	 
				IF NOT IS_CAR_DEAD w5_dinghys[2]
					SET_CAR_TEMP_ACTION w5_dinghys[2] TEMPACT_GOFORWARD 5000
				ENDIF
				w5_control_flag = 4
			ENDIF
		ENDIF
		IF w5_control_flag = 4
			IF timera > 6000
				DELETE_CAR w5_dinghys[1]
				DELETE_CAR w5_dinghys[2]
				DELETE_CHAR w5_refugees[0] 
				DELETE_CHAR w5_refugees[1] 
				DELETE_CHAR w5_refugees[2] 
				DELETE_CHAR w5_refugees[3] 
				GOTO mission_wuzi5_passed
			ENDIF
		ENDIF 	 
	ENDIF


	


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// What happens when player falls in water ///////////////////////////////////////////////////////
	IF w5_goals > 2
		IF w5_water_flag = 0
			IF IS_CHAR_IN_WATER scplayer 
				REMOVE_BLIP w5_blip 
				//REMOVE_BLIP w5_blip2
				ADD_BLIP_FOR_COORD -2329.1 1528.7 0.0 w5_blip

				CLEAR_PRINTS
				PRINT_NOW ( WUZ4_02 ) 7000 1 //Swim over to the freighter.
				w5_water_flag = 1
			ENDIF
		ENDIF

		IF w5_water_flag = 1
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer -2329.1 1528.7 0.0 4.0 4.0 4.0 TRUE
				CLEAR_PRINTS 
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE 
				
				CLEAR_PRINTS	 
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				w5_speech_goals = 0

				SET_PLAYER_CONTROL player1 OFF
				SWITCH_WIDESCREEN ON
				MAKE_PLAYER_GANG_DISAPPEAR
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
				
				/*
				MARK_MODEL_AS_NO_LONGER_NEEDED KATANA
				MARK_MODEL_AS_NO_LONGER_NEEDED KNIFECUR
				MARK_MODEL_AS_NO_LONGER_NEEDED OMOKUNG
				MARK_MODEL_AS_NO_LONGER_NEEDED DNB1
				MARK_MODEL_AS_NO_LONGER_NEEDED DNB2
				*/
				//creating dummy collision to stand on
				CREATE_OBJECT kmb_container_red -2323.9 1527.3 14.0 w5_box
				SET_OBJECT_COLLISION w5_box TRUE
				SET_OBJECT_DYNAMIC w5_box FALSE 
				SET_OBJECT_VISIBLE w5_box FALSE 
				
				WAIT 0 
				GOSUB w5_death_checks
				IF w5_deathcheck_flag = 1
					GOTO mission_wuzi5_failed
				ENDIF

				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				CLEAR_AREA -2323.2 1530.2 16.9 1.0 TRUE
				SET_CHAR_COORDINATES scplayer -2323.2 1530.2 16.9
				SET_CHAR_HEADING scplayer 3.3 
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

				OPEN_SEQUENCE_TASK w5_seq
					//TASK_PAUSE -1 500 
					TASK_CLIMB -1 TRUE
					TASK_ACHIEVE_HEADING -1 90.0
				CLOSE_SEQUENCE_TASK w5_seq
				PERFORM_SEQUENCE_TASK scplayer w5_seq
				CLEAR_SEQUENCE_TASK w5_seq

				SET_FIXED_CAMERA_POSITION -2320.7 1529.8 17.6 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2339.6 1537.4 22.5 JUMP_CUT

				DO_FADE 500 FADE_IN
				w5_water_flag = 2
			ENDIF
		ENDIF

		IF w5_water_flag = 2
			GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK task_status	 
			IF task_status = FINISHED_TASK 
				CLEAR_AREA -2323.6 1531.9 16.3 1.0 TRUE
				SET_CHAR_COORDINATES scplayer -2323.6 1531.9 16.3
				SET_CHAR_HEADING scplayer 95.3
				DELETE_OBJECT w5_box
				REMOVE_BLIP w5_blip
				IF w5_goals = 3
					IF w5_control_flag = 0
						ADD_BLIP_FOR_COORD -2437.9 1551.9 16.3 w5_blip
						PRINT_NOW ( WUZ4_01 ) 7000 1 //Make your way down into the hull of the ship.
					ENDIF
				ENDIF
				IF w5_goals = 4
					IF w5_control_flag < 4
						IF NOT IS_CHAR_DEAD w5_goons[9]
							ADD_BLIP_FOR_CHAR w5_goons[9] w5_blip	
							PRINT_NOW ( WUZ4_05 ) 4000 1 //Take out the refugees guard.
						ENDIF
					ELSE
						IF DOES_OBJECT_EXIST w5_refugee_container_padlock 		  
							ADD_BLIP_FOR_OBJECT w5_refugee_container_padlock w5_blip
							PRINT_NOW ( WUZ4_09 ) 11000 1 //Shoot the padlock on the front of the container.
						ENDIF
					ENDIF
				ENDIF
				IF w5_goals = 5
					IF NOT IS_CHAR_DEAD w5_head_honcho
						ADD_BLIP_FOR_CHAR w5_head_honcho w5_blip
						CHANGE_BLIP_SCALE w5_blip 1
					ENDIF
				
					/*
					IF NOT IS_CHAR_DEAD w5_cabin_crew[1]
						ADD_BLIP_FOR_CHAR w5_cabin_crew[1] w5_blip2
						CHANGE_BLIP_SCALE w5_blip2 1	
					ENDIF
					*/
					PRINT_NOW ( BRIDGE ) 7000 1 //Leave no one alive on the bridge!
				ENDIF

				IF w5_goals = 6
					IF NOT IS_CHAR_DEAD w5_refugees[3]
						ADD_BLIP_FOR_CHAR w5_refugees[3] w5_blip
						SET_BLIP_AS_FRIENDLY w5_blip TRUE
					ENDIF  
					PRINT_NOW ( WUZ4_11 ) 4000 1 //Go back and meet the refugees.  They will lower some dinghys into the water.
				ENDIF

				MARK_MODEL_AS_NO_LONGER_NEEDED kmb_container_red
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE		
				SWITCH_WIDESCREEN OFF
				MAKE_PLAYER_GANG_REAPPEAR
				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER
				SET_PLAYER_CONTROL player1 ON	
				w5_water_flag = 0
			ENDIF
		ENDIF
	ENDIF


	///ingame dialogue///
	GOSUB w5_overall_dialogue


GOTO mission_wuzi5_loop


	
// Mission wuzi5 failed
mission_wuzi5_failed:
PRINT_BIG M_FAIL 5000 1
RETURN

   
// mission wuzi5 passed
mission_wuzi5_passed:
flag_wuzi_mission_counter ++
REMOVE_BLIP wuzi_contact_blip
//flag_wuzi5_mission1_passed = 1
PRINT_WITH_NUMBER_BIG ( M_PASSS ) 15000 5000 1 //"Mission Passed!" //100 being the amount of cash
ADD_SCORE player1 15000//amount of cash
AWARD_PLAYER_MISSION_RESPECT 30//amount of respect

PLAY_MISSION_PASSED_TUNE 1
CLEAR_WANTED_LEVEL PLAYER1
REGISTER_MISSION_PASSED WUZI_4
PLAYER_MADE_PROGRESS 1												   
//START_NEW_SCRIPT wuzi5_mission_loop								   
RETURN																   
																	   

// mission cleanup														
mission_cleanup_wuzi5:
//SET_CAMERA_BEHIND_PLAYER 
IF IS_STRING_EMPTY $shop_name
	RESTORE_CAMERA_JUMPCUT
ENDIF
CLEAR_MISSION_AUDIO 3
REMOVE_BLIP w5_blip
//REMOVE_BLIP w5_blip2
REMOVE_BLIP w5_goons_blip[0]
REMOVE_BLIP w5_goons_blip[1]
REMOVE_BLIP w5_goons_blip[2]
REMOVE_BLIP w5_goons_blip[3]
REMOVE_BLIP w5_goons_blip[4]
REMOVE_BLIP w5_goons_blip[5]
REMOVE_BLIP w5_goons_blip[6]
REMOVE_BLIP w5_goons_blip[7]
REMOVE_BLIP w5_goons_blip[8]
MARK_MODEL_AS_NO_LONGER_NEEDED MINIGUN
MARK_MODEL_AS_NO_LONGER_NEEDED TEC9
MARK_MODEL_AS_NO_LONGER_NEEDED ROCKETLA
MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
MARK_MODEL_AS_NO_LONGER_NEEDED KNIFECUR
MARK_MODEL_AS_NO_LONGER_NEEDED KATANA
MARK_MODEL_AS_NO_LONGER_NEEDED GRENADE
MARK_MODEL_AS_NO_LONGER_NEEDED MAVERICK
MARK_MODEL_AS_NO_LONGER_NEEDED DINGHY
MARK_MODEL_AS_NO_LONGER_NEEDED TRIADB
MARK_MODEL_AS_NO_LONGER_NEEDED OMOST
MARK_MODEL_AS_NO_LONGER_NEEDED OMOKUNG
MARK_MODEL_AS_NO_LONGER_NEEDED OMOBOAT
MARK_MODEL_AS_NO_LONGER_NEEDED DNB1
MARK_MODEL_AS_NO_LONGER_NEEDED DNB2
//MARK_MODEL_AS_NO_LONGER_NEEDED DNB2OS
MARK_MODEL_AS_NO_LONGER_NEEDED kmb_container_red
MARK_MODEL_AS_NO_LONGER_NEEDED Katana_Anim
MARK_MODEL_AS_NO_LONGER_NEEDED Katana_LHand
MARK_MODEL_AS_NO_LONGER_NEEDED CJ_CHRIS_CRATE
MARK_MODEL_AS_NO_LONGER_NEEDED CJ_CHRIS_CRATE_LD
MARK_MODEL_AS_NO_LONGER_NEEDED CJ_CHRIS_CRATE_RD
MARK_MODEL_AS_NO_LONGER_NEEDED CJ_Padlock
RELEASE_WEATHER
IF NOT IS_CHAR_DEAD scplayer 
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
ENDIF
SHUT_ALL_CHARS_UP FALSE
REMOVE_ANIMATION CAR_CHAT
REMOVE_ANIMATION SWAT
REMOVE_ANIMATION PARK
REMOVE_ANIMATION MISC
REMOVE_ANIMATION GANGS
DETACH_CHAR_FROM_CAR scplayer
REMOVE_PICKUP w5_grenades 
REMOVE_PICKUP w5_health 
REMOVE_PICKUP w5_armour 
REMOVE_PICKUP w5_gun 
GET_GAME_TIMER timer_mobile_start
flag_player_on_mission = 0
MISSION_HAS_FINISHED 
RETURN



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////                      GOSUBS                             ///////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
w5_death_checks:////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF w5_goals = 0
	IF IS_CHAR_DEAD w5_chopper_pilot  
		CLEAR_PRINTS
		PRINT_NOW ( WUZ4_07 ) 7000 1 //You killed the helicopter pilot!	
		w5_deathcheck_flag = 1
	ENDIF

	IF IS_CAR_DEAD w5_chopper  
		CLEAR_PRINTS
		PRINT_NOW ( WUZ4_08 ) 7000 1 //You destoyed the helicopter!	
		w5_deathcheck_flag = 1
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
w5_creating_goons_part1:///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF w5_has_goons_been_created = 0
	CREATE_PICKUP_WITH_AMMO TEC9 PICKUP_ON_STREET_SLOW 48 -2414.9 1538.7 26.0 w5_grenades
	CREATE_PICKUP health PICKUP_ON_STREET_SLOW -2412.4 1547.9 26.0 w5_health

	//guy who runs to the left side of first bit of boxes
	CLEAR_AREA -2355.2 1547.0 25.0 1.0 TRUE
	GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2355.2 1547.0 25.0 w5_goons[0]  
	SET_CHAR_HEADING w5_goons[0] 198.7
	SET_CHAR_DECISION_MAKER w5_goons[0] w5_ped_decisions
	SET_CHAR_STAY_IN_SAME_PLACE w5_goons[0] TRUE 

	//guy who runs to the right side of first bit of boxes
	CLEAR_AREA -2355.1 1545.0 25.0 1.0 TRUE
	GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2355.1 1545.0 25.0 w5_goons[1]  
	SET_CHAR_HEADING w5_goons[1] 0.6
	SET_CHAR_DECISION_MAKER w5_goons[1] w5_ped_decisions
	SET_CHAR_STAY_IN_SAME_PLACE w5_goons[1] TRUE 

	//making them chat with each other
	TASK_CHAT_WITH_CHAR w5_goons[0] w5_goons[1] TRUE TRUE 
	TASK_CHAT_WITH_CHAR w5_goons[1] w5_goons[0] FALSE TRUE

	//first guy on second set of crates
	CLEAR_AREA -2373.0 1549.3 30.8 1.0 TRUE
	GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2373.0 1549.3 30.8 w5_goons[2]  
	SET_CHAR_HEADING w5_goons[2] 355.7
	SET_CHAR_DECISION_MAKER w5_goons[2] w5_ped_decisions
	SET_CHAR_STAY_IN_SAME_PLACE w5_goons[2] TRUE 

	//second guy on second set of crates
	CLEAR_AREA -2396.3 1560.26 30.8 1.0 TRUE
	GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2396.3 1560.26 30.8 w5_goons[3]  
	SET_CHAR_HEADING w5_goons[3] 356.2
	SET_CHAR_DECISION_MAKER w5_goons[3] w5_ped_decisions
	SET_CHAR_STAY_IN_SAME_PLACE w5_goons[3] TRUE 

	//First guy next to cabins
	CLEAR_AREA -2478.2 1560.9 16.3 1.0 TRUE
	GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2478.2 1560.9 16.3 w5_goons[4]  
	SET_CHAR_HEADING w5_goons[4] 11.0
	SET_CHAR_DECISION_MAKER w5_goons[4] w5_ped_decisions
	SET_CHAR_STAY_IN_SAME_PLACE w5_goons[4] TRUE 

	//standing on the platform at the back of the boat
	CLEAR_AREA -2500.5 1551.0 23.2 1.0 TRUE
	GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2500.5 1551.0 23.2 w5_goons[5]  
	SET_CHAR_HEADING w5_goons[5] 339.2
	SET_CHAR_DECISION_MAKER w5_goons[5] w5_ped_decisions
	SET_CHAR_STAY_IN_SAME_PLACE w5_goons[5] TRUE 

	//lead guy of last two on platform coming out to shoot at player 
	CLEAR_AREA -2493.7 1537.4 23.2 1.0 TRUE
	GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2493.7 1537.4 23.2 w5_goons[6]  
	SET_CHAR_HEADING w5_goons[6] 82.5
	SET_CHAR_DECISION_MAKER w5_goons[6] w5_empty_decisions
	SET_CHAR_STAY_IN_SAME_PLACE w5_goons[6] TRUE 
											 
	//rear guy of last two on platform coming out to shoot at player 
	CLEAR_AREA -2492.2 1537.3 23.2 1.0 TRUE
	GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2492.2 1537.3 23.2 w5_goons[7]  
	SET_CHAR_HEADING w5_goons[7] 78.2
	SET_CHAR_DECISION_MAKER w5_goons[7] w5_ped_decisions 
	SET_CHAR_STAY_IN_SAME_PLACE w5_goons[7] TRUE 
										    
	//last guy before the Rocket Launcher dude
	CLEAR_AREA -2453.8 1529.6 27.9 1.0 TRUE
	GENERATE_RANDOM_INT_IN_RANGE 0 2 w5_char_select_flag
 	CREATE_CHAR PEDTYPE_MISSION2 w5_char_select[w5_char_select_flag] -2453.8 1529.6 27.9 w5_goons[8]  
	SET_CHAR_HEADING w5_goons[8] 174.6
	SET_CHAR_DECISION_MAKER w5_goons[8] w5_ped_decisions
	SET_CHAR_STAY_IN_SAME_PLACE w5_goons[8] TRUE 

	w5_has_goons_been_created = 1
ENDIF
											   
IF w5_has_goons_been_created = 1	
	IF w5_creating_goons_flag < 9
		IF NOT IS_CHAR_DEAD w5_goons[w5_creating_goons_flag]
			GIVE_WEAPON_TO_CHAR w5_goons[w5_creating_goons_flag] WEAPONTYPE_TEC9 3000
			ADD_BLIP_FOR_CHAR w5_goons[w5_creating_goons_flag] w5_goons_blip[w5_creating_goons_flag]
			CHANGE_BLIP_SCALE w5_goons_blip[w5_creating_goons_flag] 1
		ENDIF
		w5_creating_goons_flag ++
	ELSE
		w5_has_goons_been_created = 2
	ENDIF
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
w5_goons_ai:///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
//guy who runs to the left side of first bit of boxes
IF NOT IS_CHAR_DEAD w5_goons[0]
	IF w5_goons_AI_flag[0] = 0
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2283.6 1582.6 3.0 3.0 FALSE
			OPEN_SEQUENCE_TASK w5_seq
				TASK_SET_IGNORE_WEAPON_RANGE_FLAG -1 TRUE
				TASK_GO_TO_COORD_WHILE_SHOOTING -1 -2355.1 1560.0 25.0 PEDMOVE_RUN 0.5 1.0 scplayer
				TASK_ACHIEVE_HEADING -1 360.0
				IF NOT IS_CAR_DEAD w5_chopper 
					TASK_DESTROY_CAR -1 w5_chopper
				ENDIF  
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[0] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
	 		w5_goons_AI_flag[0] = 1
		ENDIF
	ENDIF
	IF w5_goons_AI_flag[0] = 1
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2392.0 1588.0 3.0 3.0 FALSE
			OPEN_SEQUENCE_TASK w5_seq
				TASK_PAUSE -1 500  
				TASK_GO_STRAIGHT_TO_COORD -1 -2357.6 1529.6 25.0 PEDMOVE_RUN -1
				TASK_ACHIEVE_HEADING -1 183.9
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[0] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
			w5_goons_AI_flag[0] = 2
		ENDIF
	ENDIF
	IF w5_goons_AI_flag[0] = 2
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2402.6 1499.2 3.0 3.0 FALSE
			OPEN_SEQUENCE_TASK w5_seq
				IF NOT IS_CAR_DEAD w5_chopper 
					TASK_DESTROY_CAR -1 w5_chopper
				ENDIF  
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[0] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
	 		w5_goons_AI_flag[0] = 3
		ENDIF
	ENDIF
ENDIF

//guy who runs to the right side of first bit of boxes
IF NOT IS_CHAR_DEAD w5_goons[1]
	IF w5_goons_AI_flag[1] = 0
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2283.6 1582.6 3.0 3.0 FALSE
			OPEN_SEQUENCE_TASK w5_seq
				TASK_SET_IGNORE_WEAPON_RANGE_FLAG -1 TRUE
				TASK_GO_TO_COORD_WHILE_SHOOTING -1 -2348.2 1546.4 25.0 PEDMOVE_RUN 0.5 1.0 scplayer
				TASK_GO_TO_COORD_WHILE_SHOOTING -1 -2344.1 1555.8 25.0 PEDMOVE_RUN 0.5 1.0 scplayer
				TASK_ACHIEVE_HEADING -1 360.0
				IF NOT IS_CAR_DEAD w5_chopper 
					TASK_DESTROY_CAR -1 w5_chopper
				ENDIF  
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[1] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
	 		w5_goons_AI_flag[1] = 1
		ENDIF
	ENDIF
	IF w5_goons_AI_flag[1] = 1
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2392.0 1588.0 3.0 3.0 FALSE
			OPEN_SEQUENCE_TASK w5_seq
				TASK_GO_STRAIGHT_TO_COORD -1 -2350.5 1531.5 25.0 PEDMOVE_RUN -1
				TASK_ACHIEVE_HEADING -1 155.6
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[1] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
			w5_goons_AI_flag[1] = 2
		ENDIF
	ENDIF
	IF w5_goons_AI_flag[1] = 2
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2402.6 1499.2 3.0 3.0 FALSE
			OPEN_SEQUENCE_TASK w5_seq
				IF NOT IS_CAR_DEAD w5_chopper 
					TASK_DESTROY_CAR -1 w5_chopper
				ENDIF  
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[1] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
	 		w5_goons_AI_flag[1] = 3
		ENDIF
	ENDIF
ENDIF

//first guy on second set of crates
IF NOT IS_CHAR_DEAD w5_goons[2]
	IF w5_goons_AI_flag[2] = 0
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2283.6 1582.6 3.0 3.0 FALSE
			OPEN_SEQUENCE_TASK w5_seq
				TASK_SET_IGNORE_WEAPON_RANGE_FLAG -1 TRUE
				TASK_GO_TO_COORD_WHILE_SHOOTING -1 -2372.1 1557.4 31.8 PEDMOVE_RUN 0.5 1.0 scplayer
				TASK_ACHIEVE_HEADING -1 360.0
				IF NOT IS_CAR_DEAD w5_chopper 
					TASK_DESTROY_CAR -1 w5_chopper
				ENDIF  
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[2] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
	 		w5_goons_AI_flag[2] = 1
		ENDIF
	ENDIF

	IF w5_goons_AI_flag[2] = 1
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2365.4 1586.9 3.0 3.0 FALSE
			OPEN_SEQUENCE_TASK w5_seq
				TASK_GO_TO_COORD_WHILE_SHOOTING -1 -2394.4 1558.4 31.8 PEDMOVE_RUN 0.5 1.0 scplayer
				TASK_ACHIEVE_HEADING -1 66.2
				TASK_JUMP -1 TRUE
				TASK_GO_TO_COORD_WHILE_SHOOTING -1 -2423.0 1561.0 31.8 PEDMOVE_RUN 0.5 1.0 scplayer
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[2] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
	 		w5_goons_AI_flag[2] = 2
		ENDIF
	ENDIF

	IF w5_goons_AI_flag[2] = 2
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2473.8 1585.5 3.0 3.0 FALSE
			OPEN_SEQUENCE_TASK w5_seq
				TASK_ACHIEVE_HEADING -1 177.3
				TASK_JUMP -1 TRUE
				TASK_GO_STRAIGHT_TO_COORD -1 -2422.1 1547.9 22.1 PEDMOVE_RUN -1
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[2] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
	 		w5_goons_AI_flag[2] = 3
		ENDIF
	ENDIF
ENDIF

//second guy on second set of crates
IF NOT IS_CHAR_DEAD w5_goons[3]
	IF w5_goons_AI_flag[3] = 0
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2351.3 1586.2 3.0 3.0 FALSE
			OPEN_SEQUENCE_TASK w5_seq
				TASK_SET_IGNORE_WEAPON_RANGE_FLAG -1 TRUE
				TASK_GO_TO_COORD_WHILE_SHOOTING -1 -2426.7 1560.3 31.8 PEDMOVE_RUN 0.5 1.0 scplayer
				IF NOT IS_CAR_DEAD w5_chopper 
					TASK_DESTROY_CAR -1 w5_chopper
				ENDIF  
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[3] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
	 		w5_goons_AI_flag[3] = 1
		ENDIF
	ENDIF

	IF w5_goons_AI_flag[3] = 1
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2504.6 	.8 3.0 3.0 FALSE
			OPEN_SEQUENCE_TASK w5_seq
				TASK_GO_STRAIGHT_TO_COORD -1 -2437.7 1560.7 16.3 PEDMOVE_RUN -1
				TASK_GO_STRAIGHT_TO_COORD -1 -2435.9 1550.9 16.3 PEDMOVE_RUN -1
				TASK_ACHIEVE_HEADING -1 180.0
				TASK_GO_STRAIGHT_TO_COORD -1 -2435.9 1532.3 16.3 PEDMOVE_RUN -1
				TASK_GO_STRAIGHT_TO_COORD -1 -2437.5 1528.7 16.3 PEDMOVE_RUN -1
				IF NOT IS_CAR_DEAD w5_chopper 
					TASK_DESTROY_CAR -1 w5_chopper
				ENDIF  
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[3] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
	 		w5_goons_AI_flag[3] = 2
		ENDIF
	ENDIF
ENDIF

//First guy next to cabins
IF NOT IS_CHAR_DEAD w5_goons[4]
	IF w5_goons_AI_flag[4] = 0
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2448.6 1590.3 3.0 3.0 FALSE
			OPEN_SEQUENCE_TASK w5_seq
				TASK_SET_IGNORE_WEAPON_RANGE_FLAG -1 TRUE
				TASK_GO_TO_COORD_WHILE_SHOOTING -1 -2494.1 1560.7 16.3 PEDMOVE_RUN 0.5 1.0 scplayer
				TASK_GO_TO_COORD_WHILE_SHOOTING -1 -2505.2 1557.4 16.3 PEDMOVE_RUN 0.5 1.0 scplayer
				TASK_GO_TO_COORD_WHILE_SHOOTING -1 -2505.5 1532.1 16.3 PEDMOVE_RUN 0.5 1.0 scplayer
				IF NOT IS_CAR_DEAD w5_chopper 
					TASK_DESTROY_CAR -1 w5_chopper
				ENDIF  
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[4] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
	 		w5_goons_AI_flag[4] = 1
		ENDIF
	ENDIF
ENDIF

//standing on the platform at the back of the boat
IF NOT IS_CHAR_DEAD w5_goons[5]
	IF w5_goons_AI_flag[5] = 0
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2448.6 1590.3 3.0 3.0 FALSE
			OPEN_SEQUENCE_TASK w5_seq
				TASK_SET_IGNORE_WEAPON_RANGE_FLAG -1 TRUE
				IF NOT IS_CAR_DEAD w5_chopper 
					TASK_DESTROY_CAR -1 w5_chopper
				ENDIF  
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[5] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
	 		w5_goons_AI_flag[5] = 1
		ENDIF
	ENDIF
ENDIF

//lead guy of last two on platform coming out to shoot at player 
IF NOT IS_CHAR_DEAD w5_goons[6]
	IF w5_goons_AI_flag[6] = 0
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2493.5 1571.8 3.0 3.0 FALSE
			OPEN_SEQUENCE_TASK w5_seq
				TASK_SET_IGNORE_WEAPON_RANGE_FLAG -1 TRUE
				TASK_GO_STRAIGHT_TO_COORD -1 -2501.4 1539.5 23.2 PEDMOVE_RUN -1
				IF NOT IS_CAR_DEAD w5_chopper 
					TASK_DESTROY_CAR -1 w5_chopper
				ENDIF  
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[6] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
	 		w5_goons_AI_flag[6] = 1
		ENDIF
	ENDIF
ENDIF

//rear guy of last two on platform coming out to shoot at player 
IF NOT IS_CHAR_DEAD w5_goons[7]
	IF w5_goons_AI_flag[7] = 0
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2493.5 1571.8 3.0 3.0 FALSE
			OPEN_SEQUENCE_TASK w5_seq
				TASK_SET_IGNORE_WEAPON_RANGE_FLAG -1 TRUE
				TASK_GO_STRAIGHT_TO_COORD -1 -2500.9 1535.4 23.2 PEDMOVE_RUN -1
				IF NOT IS_CAR_DEAD w5_chopper 
					TASK_DESTROY_CAR -1 w5_chopper
				ENDIF  
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[7] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
	 		w5_goons_AI_flag[7] = 1
		ENDIF
	ENDIF
ENDIF

//last guy before the Rocket Launcher dude
IF NOT IS_CHAR_DEAD w5_goons[8]
	IF w5_goons_AI_flag[8] = 0
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2493.5 1571.8 3.0 3.0 FALSE
			OPEN_SEQUENCE_TASK w5_seq
				TASK_SET_IGNORE_WEAPON_RANGE_FLAG -1 TRUE
				IF NOT IS_CAR_DEAD w5_chopper 
					TASK_DESTROY_CAR -1 w5_chopper
				ENDIF  
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[8] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
	 		w5_goons_AI_flag[8] = 1
		ENDIF
	ENDIF
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
w5_death_kills:////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF w5_creating_goons_flag < 9
	IF IS_CHAR_DEAD w5_goons[w5_creating_goons_flag]
		REMOVE_BLIP w5_goons_blip[w5_creating_goons_flag]
	ENDIF
	w5_creating_goons_flag ++
ELSE
	w5_creating_goons_flag = 0
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
w5_creating_refugees://////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
	CLEAR_AREA -2369.3 1551.9 1.2 1.0 TRUE
	CREATE_CHAR PEDTYPE_MISSION5 OMOST -2369.3 1551.9 1.2 w5_refugees[0]
	SET_CHAR_HEADING w5_refugees[0] 96.2
	SET_CHAR_STAY_IN_SAME_PLACE w5_refugees[0] TRUE
	SET_CHAR_NEVER_TARGETTED w5_refugees[0] TRUE
	//TASK_PLAY_ANIM_NON_INTERRUPTABLE w5_refugees[0] crckidle4 CRACK 8.0 TRUE FALSE FALSE FALSE -1 
	SET_CHAR_DECISION_MAKER w5_refugees[0] w5_empty_decisions

	CLEAR_AREA -2368.3 1551.9 1.2 1.0 TRUE
	CREATE_CHAR PEDTYPE_MISSION5 OMOST -2368.3 1551.9 1.2 w5_refugees[1]
	SET_CHAR_HEADING w5_refugees[1] 96.2
	SET_CHAR_STAY_IN_SAME_PLACE w5_refugees[1] TRUE
	SET_CHAR_NEVER_TARGETTED w5_refugees[1] TRUE
	//TASK_PLAY_ANIM_NON_INTERRUPTABLE w5_refugees[1] weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
	SET_CHAR_DECISION_MAKER w5_refugees[1] w5_empty_decisions

	CLEAR_AREA -2367.3 1551.9 1.2 1.0 TRUE
	CREATE_CHAR PEDTYPE_MISSION5 OMOBOAT -2367.3 1551.9 1.2 w5_refugees[2]
	SET_CHAR_HEADING w5_refugees[2] 96.2
	SET_CHAR_STAY_IN_SAME_PLACE w5_refugees[2] TRUE
	SET_CHAR_NEVER_TARGETTED w5_refugees[2] TRUE
	//TASK_PLAY_ANIM_NON_INTERRUPTABLE w5_refugees[2] M_smklean_loop SMOKING 4.0 TRUE FALSE FALSE TRUE -1
	SET_CHAR_DECISION_MAKER w5_refugees[2] w5_empty_decisions
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
w5_guys_at_start_of_maze://////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF w5_where_player_control_flag = 0
	IF IS_CHAR_IN_AREA_3D scplayer -2282.4 1510.9 50.0 -2534.6 1578.5 15.0 FALSE 
		IF IS_CHAR_SHOOTING scplayer
			IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PISTOL_SILENCED
				w5_where_player_control_flag = 1
			ENDIF
		ENDIF
	ENDIF	
	
	IF IS_CHAR_IN_AREA_2D scplayer -2385.2 1539.7 -2393.0 1558.9 FALSE
		w5_where_player_control_flag = 1
	ENDIF
	IF NOT IS_CHAR_DEAD w5_goons[10]	
		IF IS_CHAR_SHOOTING w5_goons[10]
			w5_where_player_control_flag = 1  
		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD w5_goons[15] 
		IF IS_CHAR_SHOOTING w5_goons[15]
			w5_where_player_control_flag = 1  
		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD w5_goons[16] 
		IF IS_CHAR_SHOOTING w5_goons[16]
			w5_where_player_control_flag = 1  
		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD w5_goons[0] 
		IF IS_CHAR_SHOOTING w5_goons[0]
			w5_where_player_control_flag = 1  
		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD w5_goons[1] 
		IF IS_CHAR_SHOOTING w5_goons[1]
			w5_where_player_control_flag = 1  
		ENDIF
	ENDIF
ENDIF

IF w5_where_player_control_flag = 1
	IF NOT IS_CHAR_DEAD w5_goons[10] 
		TASK_TURN_CHAR_TO_FACE_CHAR w5_goons[10] scplayer
	ENDIF
	
	IF NOT IS_CHAR_DEAD w5_goons[15] 
		TASK_TURN_CHAR_TO_FACE_CHAR w5_goons[15] scplayer
	ENDIF

	IF NOT IS_CHAR_DEAD w5_goons[16] 
		TASK_TURN_CHAR_TO_FACE_CHAR w5_goons[16] scplayer
	ENDIF
	
	IF NOT IS_CHAR_DEAD w5_goons[0] 
		OPEN_SEQUENCE_TASK w5_seq
			TASK_SET_CHAR_DECISION_MAKER -1 w5_empty_decisions
			TASK_GO_STRAIGHT_TO_COORD -1 -2351.0 1549.4 22.2 PEDMOVE_RUN -1
			TASK_ACHIEVE_HEADING -1 180.0  
			TASK_CLIMB -1 TRUE
			TASK_GO_STRAIGHT_TO_COORD -1 -2351.2 1547.1 25.0 PEDMOVE_RUN -1
			TASK_SET_CHAR_DECISION_MAKER -1 w5_ped_decisions
			TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
		CLOSE_SEQUENCE_TASK w5_seq
		PERFORM_SEQUENCE_TASK w5_goons[0] w5_seq
		CLEAR_SEQUENCE_TASK w5_seq
	ENDIF

	IF NOT IS_CHAR_DEAD w5_goons[1] 
		TASK_TURN_CHAR_TO_FACE_CHAR w5_goons[1] scplayer
	ENDIF

	IF NOT IS_CHAR_DEAD w5_goons[2] 
		OPEN_SEQUENCE_TASK w5_seq
			TASK_GO_STRAIGHT_TO_COORD -1 -2385.5 1543.1 25.0 PEDMOVE_RUN -1
			TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
		CLOSE_SEQUENCE_TASK w5_seq
		PERFORM_SEQUENCE_TASK w5_goons[2] w5_seq
		CLEAR_SEQUENCE_TASK w5_seq
	ENDIF
	  
	IF NOT IS_CHAR_DEAD w5_goons[3] 
		OPEN_SEQUENCE_TASK w5_seq
			TASK_GO_STRAIGHT_TO_COORD -1 -2385.8 1540.6 25.0 PEDMOVE_RUN -1
			TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
		CLOSE_SEQUENCE_TASK w5_seq
		PERFORM_SEQUENCE_TASK w5_goons[3] w5_seq
		CLEAR_SEQUENCE_TASK w5_seq
	ENDIF
	IF NOT IS_CHAR_DEAD w5_goons[4] 
		OPEN_SEQUENCE_TASK w5_seq
			TASK_GO_STRAIGHT_TO_COORD -1 -2392.1 1556.0 25.0 PEDMOVE_RUN -1
			TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
		CLOSE_SEQUENCE_TASK w5_seq
		PERFORM_SEQUENCE_TASK w5_goons[4] w5_seq
		CLEAR_SEQUENCE_TASK w5_seq
	ENDIF

	//PRINT_NOW ( WUZ4_25 ) 3000 1 //ENEMY: There he is, get him!
	w5_where_player_control_flag = 2
ENDIF

//waiting to be able to trigger next bit
IF w5_where_player_control_flag = 2 
	IF NOT IS_CHAR_DEAD w5_goons[12]
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR w5_goons[12] scplayer 
			w5_where_player_control_flag = 3
		ENDIF 
	ELSE
		w5_where_player_control_flag = 3 
	ENDIF
	
	
	IF IS_CHAR_IN_AREA_2D scplayer -2400.0 1561.0 -2409.8 1540.0 FALSE
		w5_where_player_control_flag = 3 
	ENDIF
ENDIF
 
IF w5_where_player_control_flag = 3 
	IF NOT IS_CHAR_DEAD w5_goons[12]
		OPEN_SEQUENCE_TASK w5_seq
			TASK_TOGGLE_DUCK -1 TRUE
			TASK_PAUSE -1 500
			TASK_GO_STRAIGHT_TO_COORD -1 -2401.1 1539.8 25.0 PEDMOVE_RUN -1
			TASK_KILL_CHAR_ON_FOOT -1 scplayer
		CLOSE_SEQUENCE_TASK w5_seq
		PERFORM_SEQUENCE_TASK w5_goons[12] w5_seq
		CLEAR_SEQUENCE_TASK w5_seq
	ENDIF
	w5_where_player_control_flag = 4
ENDIF

//triggering last bit of maze
IF w5_where_player_control_flag = 4
		IF IS_CHAR_IN_AREA_2D scplayer -2416.4 1535.3 -2431.6 1542.1 FALSE 
		IF NOT IS_CHAR_DEAD w5_goons[6] 
			OPEN_SEQUENCE_TASK w5_seq
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_L SWAT 8.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_in_L SWAT 8.0 FALSE TRUE TRUE FALSE -1
				TASK_STAY_IN_SAME_PLACE -1 TRUE
				GENERATE_RANDOM_INT_IN_RANGE 1500 4000 w5_random_number
				TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer w5_random_number
				TASK_STAY_IN_SAME_PLACE -1 FALSE
				TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM -1 -2420.4 1553.4 25.0 180.0 -0.1 swt_wllshoot_out_L SWAT 8.0 FALSE TRUE TRUE FALSE -1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_L_back SWAT 8.0 FALSE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT w5_seq 1
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[6] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
		ENDIF
	
		IF NOT IS_CHAR_DEAD w5_goons[13] 
			OPEN_SEQUENCE_TASK w5_seq
				TASK_CLIMB -1 TRUE
				TASK_SET_CHAR_DECISION_MAKER -1 w5_ped_decisions
				TASK_KILL_CHAR_ON_FOOT -1 scplayer
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[13] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
		ENDIF
		
		IF NOT IS_CHAR_DEAD w5_goons[14] 
			OPEN_SEQUENCE_TASK w5_seq
				TASK_PAUSE -1 1500
				TASK_CLIMB -1 TRUE
				TASK_SET_CHAR_DECISION_MAKER -1 w5_ped_decisions
				TASK_KILL_CHAR_ON_FOOT -1 scplayer
			CLOSE_SEQUENCE_TASK w5_seq
			PERFORM_SEQUENCE_TASK w5_goons[14] w5_seq
			CLEAR_SEQUENCE_TASK w5_seq
		ENDIF
		w5_where_player_control_flag = 5
	ENDIF
ENDIF

IF w5_where_player_control_flag = 5
	IF IS_CHAR_IN_AREA_2D scplayer -2418.9 1546.4 -2435.6 1559.0 FALSE 
	OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR w5_goons[6] scplayer	
		IF NOT IS_CHAR_DEAD w5_goons[6]
			TASK_KILL_CHAR_ON_FOOT w5_goons[6] scplayer
			w5_where_player_control_flag = 6
		ENDIF
	ENDIF
ENDIF	 	

///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
w5_dialogue_setup://///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF w5_speech_goals = 1
	$w5_print_label[0] = &WUZ4_KA // Hey CJ, what you doing here?
	$w5_print_label[1] = &WUZ4_KB // Just bumped into Woozie on his way out.
	$w5_print_label[2] = &WUZ4_KC // He filled me in, thought I'd roll along with you!
	$w5_print_label[3] = &WUZ4_KD // Well we ain't rollin', we're whirling! 
	$w5_print_label[4] = &WUZ4_KE // Strap in and strap up!

	w5_audio_label[0] = SOUND_WUZ4_KA
	w5_audio_label[1] = SOUND_WUZ4_KB
	w5_audio_label[2] = SOUND_WUZ4_KC
	w5_audio_label[3] = SOUND_WUZ4_KD
	w5_audio_label[4] = SOUND_WUZ4_KE
	w5_last_label = 5
ENDIF

IF w5_speech_goals = 2
	$w5_print_label[0] = &WUZ4_LA // Where we headed?
	$w5_print_label[1] = &WUZ4_LB // It's moored out in the bay!
	$w5_print_label[2] = &WUZ4_LC // Oh yeah, I see it!
	$w5_print_label[3] = &WUZ4_LD // You better lock and load, they'll be on their guard!
	$w5_print_label[4] = &WUZ4_LE // Locked and loaded!

	w5_audio_label[0] = SOUND_WUZ4_LA
	w5_audio_label[1] = SOUND_WUZ4_LB
	w5_audio_label[2] = SOUND_WUZ4_LC
	w5_audio_label[3] = SOUND_WUZ4_LD
	w5_audio_label[4] = SOUND_WUZ4_LE
	w5_last_label = 5
ENDIF

IF w5_speech_goals = 3
	$w5_print_label[0] = &WUZ4_MA // Holy fuck, they're gunning for us already!
	$w5_print_label[1] = &WUZ4_MB // I see 'em!
	$w5_print_label[2] = &WUZ4_MC // They're all over the containers!
	$w5_print_label[3] = &WUZ4_MD // I see 'em, dude, I see 'em!
	$w5_print_label[4] = &WUZ4_ME // Hit those Da Nang bastards!

	w5_audio_label[0] = SOUND_WUZ4_MA
	w5_audio_label[1] = SOUND_WUZ4_MB
	w5_audio_label[2] = SOUND_WUZ4_MC
	w5_audio_label[3] = SOUND_WUZ4_MD
	w5_audio_label[4] = SOUND_WUZ4_ME
	w5_last_label = 5
ENDIF

IF w5_speech_goals = 4
	$w5_print_label[0] = &WUZ4_NA // RPG! RPG!
	$w5_print_label[1] = &WUZ4_NB // Where? Which side?	//debug - doesn't print atm... check when dialogue is in
	$w5_print_label[2] = &WUZ4_NC // We're hit!
	$w5_print_label[3] = &WUZ4_NE // We're going down!
	$w5_print_label[4] = &WUZ4_NF // Brace for impact!

	w5_audio_label[0] = SOUND_WUZ4_NA
	w5_audio_label[1] = SOUND_WUZ4_NB
	w5_audio_label[2] = SOUND_WUZ4_NC
	w5_audio_label[3] = SOUND_WUZ4_NE
	w5_audio_label[4] = SOUND_WUZ4_NF
	w5_last_label = w5_random_last_label
ENDIF

IF w5_speech_goals = 5
	$w5_print_label[0] = &WUZ4_AA // Can you see any survivors?
	$w5_print_label[1] = &WUZ4_AB // No. Nobody's getting out of that alive!

	w5_audio_label[0] = SOUND_WUZ4_AA
	w5_audio_label[1] = SOUND_WUZ4_AB
	w5_last_label = w5_random_last_label
ENDIF

IF w5_speech_goals = 6
	$w5_print_label[0] = &WUZ4_FA // Keep it down, you want to bring the snakehead down here?

	w5_audio_label[0] = SOUND_WUZ4_FA
	w5_last_label = 1
ENDIF

IF w5_speech_goals = 7
	$w5_print_label[0] = &WUZ4_CA // Not long until we're drinking cola in the free West, eh!
	$w5_print_label[1] = &WUZ4_CB // Hey, who the fuck are you?

	w5_audio_label[0] = SOUND_WUZ4_CA
	w5_audio_label[1] = SOUND_WUZ4_CB
	w5_last_label = w5_random_last_label
ENDIF

IF w5_speech_goals = 8
	$w5_print_label[0] = &WUZ4_HA // Stick this up your ass!

	w5_audio_label[0] = SOUND_WUZ4_HA
	w5_last_label = 1
ENDIF

IF w5_speech_goals = 9
	$w5_print_label[0] = &WUZ4_JA // Hey you, help us!
	$w5_print_label[1] = &WUZ4_JB // Hey, please, help us!

	w5_audio_label[0] = SOUND_WUZ4_JA
	w5_audio_label[1] = SOUND_WUZ4_JB
	w5_last_label = w5_random_last_label
ENDIF

IF w5_speech_goals = 10
	$w5_print_label[0] = &WUZ4_JC // Please, the snakehead tricked us, we're virtual prisoners.
	$w5_print_label[1] = &WUZ4_JD // Please help us escape!
	$w5_print_label[2] = &WUZ4_JE // The Snakehead is up on the bridge...

	w5_audio_label[0] = SOUND_WUZ4_JC
	w5_audio_label[1] = SOUND_WUZ4_JD
	w5_audio_label[2] = SOUND_WUZ4_JE
	w5_last_label = w5_random_last_label
ENDIF

IF w5_speech_goals = 11
	$w5_print_label[0] = &WUZ4_JF // Thank you for everything!

	w5_audio_label[0] = SOUND_WUZ4_JF
	w5_last_label = 1
ENDIF

IF w5_speech_goals = 12
	$w5_print_label[0] = &WUZ4_OA // Oh, man, I hurt!
	$w5_print_label[1] = &WUZ4_ZA // Damn, lost everything but my blade!

	w5_audio_label[0] = SOUND_WUZ4_OA
	w5_audio_label[1] = SOUND_WUZ4_ZA
	w5_last_label = 2
ENDIF

IF w5_speech_goals = 13
	$w5_print_label[0] = &WUZ4_PA // Enough! We settle this here!

	w5_audio_label[0] = SOUND_WUZ4_PA
	w5_last_label = 1
ENDIF

//REMOVED DIALOGUE
//[WUZ4_BA:WUZI4]Keep it down, you want to bring the snakehead down here?
//[WUZ4_CC:WUZI4]Go get the snakehead!
//[WUZ4_GA:WUZI4]Not long until we're drinking cola in the free West, eh!
//[WUZ4_GB:WUZI4]Hey, who the fuck are you?
//[WUZ4_GC:WUZI4]Go get the snakehead!
//[WUZ4_DA:WUZI4]Stick this up your ass!
//[WUZ4_ND:WUZI4] // (Sirens, whines, clattering gears etc.)

w5_slot_load = w5_speech_control_flag
w5_slot1 = 0
w5_slot2 = 0
w5_play_which_slot = 1
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
w5_overall_dialogue:///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF w5_speech_goals = 1 //cutscene dialogue between pilot and player
OR w5_speech_goals = 2 //2nd convo between pilot and player
OR w5_speech_goals = 3 //Pilot telling player he is getting shot at
OR w5_speech_goals = 4 //Pilot telling player about the missile and that the heli is going down.
OR w5_speech_goals = 5 //Two guards checking the wreckage
	IF w5_speech_control_flag < w5_last_label
		GOSUB w5_loading_dialogue
		GOSUB w5_playing_dialogue
		GOSUB w5_finishing_dialogue  
	ELSE
		w5_speech_goals = 0
	ENDIF
ENDIF	


IF w5_speech_goals = 6 //Keep it down, you want to bring the snakehead down here?
OR w5_speech_goals = 7 //3 guards talking to each other it the cutscene.
OR w5_speech_goals = 10 //refugees talking to player in cutscene after being released.
OR w5_speech_goals = 11 //Thank you for everything!
OR w5_speech_goals = 12 //Oh, man, I hurt!  | Shit, lost all my weapons!
	IF w5_speech_control_flag < w5_last_label
		GOSUB w5_loading_dialogue
		GOSUB w5_playing_dialogue
		GOSUB w5_finishing_dialogue  
	ELSE
		w5_speech_goals = 0
	ENDIF
ENDIF	

IF w5_speech_goals = 8 //Stick this up your ass!
	IF w5_speech_control_flag < w5_last_label
		GOSUB w5_loading_dialogue
		GOSUB w5_playing_dialogue
		IF NOT IS_CHAR_DEAD w5_goons[9]
			GOSUB w5_finishing_dialogue  
		ELSE
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $w5_print_label[w5_speech_control_flag] 
			w5_slot1 = 0
			w5_slot2 = 0
		ENDIF
	ELSE
		w5_speech_goals = 0
	ENDIF
ENDIF	

IF w5_speech_goals = 9 //Refugees pleading with player.
	IF w5_speech_control_flag < w5_last_label
		GOSUB w5_loading_dialogue
		GOSUB w5_playing_dialogue
		IF NOT IS_CHAR_DEAD w5_refugees[3]
			GOSUB w5_finishing_dialogue  
		ELSE
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $w5_print_label[w5_speech_control_flag] 
			w5_slot1 = 0
			w5_slot2 = 0
		ENDIF
	ELSE
		w5_speech_goals = 0
	ENDIF
ENDIF	

IF w5_speech_goals = 13 //Snakehead talking 
	IF w5_speech_control_flag < w5_last_label
		GOSUB w5_loading_dialogue
		GOSUB w5_playing_dialogue
		IF NOT IS_CHAR_DEAD w5_head_honcho
			GOSUB w5_finishing_dialogue  
		ELSE
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $w5_print_label[w5_speech_control_flag] 
			w5_slot1 = 0
			w5_slot2 = 0
		ENDIF
	ELSE
		w5_speech_goals = 0
	ENDIF
ENDIF	
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
w5_loading_dialogue:///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
IF w5_slot_load < w5_last_label 
	//slot 1
	IF w5_slot1 = 0
		LOAD_MISSION_AUDIO 1 w5_audio_label[w5_slot_load] 
		w5_slot_load ++ 
		w5_slot1 = 1
	ENDIF

	//slot 2
	IF w5_slot2 = 0
		LOAD_MISSION_AUDIO 2 w5_audio_label[w5_slot_load] 
		w5_slot_load ++ 
		w5_slot2 = 1
	ENDIF  
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
w5_playing_dialogue:///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
//slot 1			   
IF w5_play_which_slot = 1 
	IF w5_slot1 = 1
		IF HAS_MISSION_AUDIO_LOADED 1	 
			PLAY_MISSION_AUDIO 1
			PRINT_NOW ( $w5_print_label[w5_speech_control_flag] ) 4500 1 //
  			w5_slot1 = 2
		ENDIF
	ENDIF
ENDIF
			   
//slot 2
IF w5_play_which_slot = 2 				  
	IF w5_slot2 = 1					    
		IF HAS_MISSION_AUDIO_LOADED 2	 
			PLAY_MISSION_AUDIO 2		   
			PRINT_NOW ( $w5_print_label[w5_speech_control_flag] ) 4500 1 //
			w5_slot2 = 2
		ENDIF
	ENDIF
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
w5_finishing_dialogue://///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////
//slot 1
IF w5_slot1 = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		CLEAR_THIS_PRINT $w5_print_label[w5_speech_control_flag]
		w5_speech_control_flag ++		
		w5_play_which_slot = 2
		w5_slot1 = 0
	ENDIF
ENDIF

//slot 2
IF w5_slot2 = 2
	IF HAS_MISSION_AUDIO_FINISHED 2
		CLEAR_THIS_PRINT $w5_print_label[w5_speech_control_flag]
		w5_speech_control_flag ++		
		w5_play_which_slot = 1
		w5_slot2 = 0
	ENDIF
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////
RETURN/////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////


}
								 




