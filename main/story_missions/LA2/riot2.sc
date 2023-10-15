MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// *************************************** Riot 2 ******************************************
// ************************************* Desperados ****************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
SCRIPT_NAME riot2
// Mission start stuff
GOSUB mission_start_riot2
IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_riot2_failed
ENDIF
GOSUB mission_cleanup_riot2
MISSION_END
{ 
// Variables for mission
//people
LVAR_INT r2_flame r2_launcher r2_katana 
LVAR_INT r2_flats_lookout[6] 
LVAR_INT r2_NW_goons[6]
LVAR_INT r2_NE_goons[6]
LVAR_INT r2_SW_goons[6]
LVAR_INT r2_SE_goons[6]

LVAR_INT r2_junk[2]
LVAR_INT r2_car_mechanics[6]
LVAR_INT r2_garage[4]

LVAR_INT r2_climbers[6]
LVAR_INT r2_health
LVAR_INT r2_gun

LVAR_INT r2_random_car
LVAR_INT r2_ball



//blips
LVAR_INT r2_control_blip r2_final_blips[6]
LVAR_INT r2_flats_lookout_blips[6] r2_NW_goons_blips[6] r2_NE_goons_blips[6] r2_SW_goons_blips[6] r2_SE_goons_blips[6]
LVAR_INT r2_car_mechanics_blips[6] r2_climbers_blips[6] r2_garage_blips[4] 


//flags
LVAR_INT r2_goals r2_control_flag r2_skip_cutscene_flag r2_deathcheck_flag 
LVAR_INT r2_char_select[2] r2_char_select_flag
LVAR_INT r2_runner_control_flag
LVAR_INT r2_flats_lookout_control_flag 
LVAR_INT r2_flats_lookout_flag
LVAR_INT r2_NW_control_flag[3] r2_NE_control_flag[3] r2_SW_control_flag[3] r2_SE_control_flag[3]
LVAR_INT r2_char_health
LVAR_INT r2_og_control_flag
LVAR_INT r2_attack_flag
LVAR_INT r2_flag_cesar_in_group
LVAR_INT r2_random_number
LVAR_INT r2_triggering_launcher_death
LVAR_INT m5_NE_gang_timer m5_NW_gang_timer m5_SE_gang_timer m5_SW_gang_timer m5_timer_ended m5_timer_difference m5_timer_started
LVAR_INT r2_group_size 
LVAR_INT r2_checking_group_size
LVAR_INT random_time
LVAR_INT r2_health_check
LVAR_INT r2_char_accuracy
LVAR_INT r2_context

LVAR_FLOAT r2_anim_time
LVAR_INT r2_pickup_health


//speech
LVAR_INT r2_speech_goals r2_speech_control_flag r2_speech_flag r2_random_last_label 
LVAR_TEXT_LABEL r2_print_label[22] 
LVAR_INT r2_audio_label[22] 
LVAR_INT r2_last_label //r2_played_random_speech[2]   
LVAR_INT r2_slot1 r2_slot2 r2_slot_load r2_play_which_slot
LVAR_INT r2_storing_speech_goals_number r2_storing_speech_control_number
		 

//coords
LVAR_FLOAT r2_x r2_y r2_z

//sequences/decision makers/threat lists/attractors/groups								 
LVAR_INT r2_ped_decisions r2_empty_ped_decision_maker r2_group_decisions r2_seq r2_seq2 r2_seq_progress 

//debug

// ****************************************Mission Start************************************
mission_start_riot2:
flag_player_on_mission = 1
REGISTER_MISSION_GIVEN
LOAD_MISSION_TEXT riot2
IF flag_player_on_mission = 0
	CREATE_OBJECT BBALL_INGAME 0.0 0.0 0.0 r2_ball
	ADD_BLIP_FOR_COORD 1774.9 -1974.9 13.1 r2_control_blip
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1831.9 -2004.6 12.6 r2_flats_lookout[0]
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1831.9 -2004.6 12.6 r2_NW_goons[0]
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1831.9 -2004.6 12.6 r2_NE_goons[0]
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1831.9 -2004.6 12.6 r2_SW_goons[0]
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1831.9 -2004.6 12.6 r2_SE_goons[0]
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1831.9 -2004.6 12.6 r2_climbers[0]
	ADD_BLIP_FOR_CHAR r2_flats_lookout[0] r2_flats_lookout_blips[0]
	ADD_BLIP_FOR_CHAR r2_NW_goons[0] r2_NW_goons_blips[0]
	ADD_BLIP_FOR_CHAR r2_NE_goons[0] r2_NE_goons_blips[0]
	ADD_BLIP_FOR_CHAR r2_SW_goons[0] r2_SW_goons_blips[0]
	ADD_BLIP_FOR_CHAR r2_SE_goons[0] r2_SE_goons_blips[0]
	ADD_BLIP_FOR_CHAR r2_climbers[0] r2_climbers_blips[0]
ENDIF
 
CLEAR_PRINTS 
WAIT 0

// *************************************Set Flags/variables*********************************
r2_goals = 0 
r2_control_flag = 0 
r2_skip_cutscene_flag = 0 
r2_deathcheck_flag = 0 
r2_speech_flag = 0
r2_char_select[0] = LSV1
r2_char_select[1] = LSV2 
r2_char_select_flag = 0
r2_runner_control_flag = 2
r2_flats_lookout_control_flag = 0
r2_NW_control_flag[0] = 0 
r2_NW_control_flag[1] = 0 
r2_NW_control_flag[2] = 0 
r2_NE_control_flag[0] = 0 
r2_NE_control_flag[1] = 0 
r2_NE_control_flag[2] = 0 
r2_SW_control_flag[0] = 0 
r2_SW_control_flag[1] = 0 
r2_SW_control_flag[2] = 0 
r2_SE_control_flag[0] = 0
r2_SE_control_flag[1] = 0
r2_SE_control_flag[2] = 0
r2_char_health = 0
r2_og_control_flag = 0
r2_attack_flag = 0
r2_flag_cesar_in_group = 0

m5_NE_gang_timer = 0 
m5_NW_gang_timer = 0 
m5_SE_gang_timer = 0 
m5_SW_gang_timer = 0 
m5_timer_ended = 0 
m5_timer_difference = 0 
m5_timer_started = 0

r2_x = 0.0 
r2_y = 0.0 
r2_z = 0.0

r2_random_number = 0
r2_triggering_launcher_death = 0
r2_group_size = 0
r2_checking_group_size = 0

r2_speech_goals = 0 
//r2_played_random_speech[0] = 0 
//r2_played_random_speech[1] = 0 
r2_random_last_label = 0
r2_last_label = 0 
r2_slot1 = 0 
r2_slot2 = 0 
r2_slot_load = 0 
r2_play_which_slot = 0
r2_storing_speech_goals_number = 0 
r2_storing_speech_control_number = 0

r2_anim_time = 0.0

r2_health_check = 0

r2_char_accuracy = 60

r2_seq_progress = 0



// ****************************************START OF CUTSCENE********************************
MAKE_PLAYER_GANG_DISAPPEAR
SET_AREA_VISIBLE 1
LOAD_CUTSCENE RIOT_2
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
WAIT 0
//------------------REQUEST_MODELS ------------------------------
/////////////INFO//////////////
//    Hazer = Katanaman      //
//    Sunny = Launcher       //
//    Gal = Two Guns (flame) //
///////////////////////////////
				   

LOAD_SPECIAL_CHARACTER 1 cesar

//good gang models
REQUEST_MODEL VLA1 //Launcher OG - normal guy
REQUEST_MODEL VLA2 //Flame OG - baldy guy  
REQUEST_MODEL VLA3 //Katana OG - guy with sunglasses  
//REQUEST_MODEL FAM1 //2 goons along with player

//Baddie Gang models
//REQUEST_MODEL MEX //main baddie  
REQUEST_MODEL LSV1  
REQUEST_MODEL LSV2 
//REQUEST_MODEL LSV3 

REQUEST_MODEL KATANA 
REQUEST_MODEL DESERT_EAGLE 
REQUEST_MODEL TEC9 

LOAD_ALL_MODELS_NOW


/////////////////////////////// DECISION MAKER SHIT ////////////////////////////////////////////// 
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_MISSION2	   ////1 is good GUYS
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1 PEDTYPE_PLAYER1	   ////1 is good GUYS
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 PEDTYPE_MISSION1	   ////2 is bad GUYS
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 PEDTYPE_PLAYER1	   ////2 is bad GUYS
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION2 PEDTYPE_MISSION2
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1 PEDTYPE_MISSION1

//SET_PED_DENSITY_MULTIPLIER 0.0

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH r2_ped_decisions  
//CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE r2_ped_decisions EVENT_ACQUAINTANCE_PED_HATE 
//CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE r2_ped_decisions EVENT_ACQUAINTANCE_PED_DISLIKE 

COPY_CHAR_DECISION_MAKER DM_PED_EMPTY r2_empty_ped_decision_maker 

LOAD_GROUP_DECISION_MAKER DM_GROUP_MISSION_NORM r2_group_decisions 


CLEAR_AREA 2520.8 -1678.5 14.2 30.0 TRUE
LOAD_SCENE 2520.8 -1678.5 14.2
SET_CHAR_COORDINATES scplayer 2520.8 -1678.5 14.2
SET_CHAR_HEADING scplayer 68.6

CLEAR_AREA 2520.8 -1678.5 14.2 1.0 TRUE
CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 2519.8 -1677.5 13.7 cesar	
SET_CHAR_HEADING cesar 68.6
GIVE_WEAPON_TO_CHAR cesar WEAPONTYPE_DESERT_EAGLE 30000 
SET_CHAR_NEVER_TARGETTED cesar TRUE
ADD_ARMOUR_TO_CHAR cesar 100
SET_CHAR_RELATIONSHIP cesar ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1
SET_CHAR_ONLY_DAMAGED_BY_PLAYER cesar TRUE 
SET_CHAR_SUFFERS_CRITICAL_HITS cesar FALSE 

SET_CHAR_DECISION_MAKER cesar r2_ped_decisions

SWITCH_PED_ROADS_OFF 1945.8 -1956.5 0.0 1642.9 -2174.3 50.0

//Launcher OG
CLEAR_AREA 1784.6 -1969.5 13.1 30.0 TRUE
CREATE_CHAR PEDTYPE_MISSION1 VLA1 1784.6 -1969.5 13.1 r2_launcher	
SET_CHAR_HEADING r2_launcher 103.1 
GIVE_WEAPON_TO_CHAR r2_launcher WEAPONTYPE_TEC9 30000 
//GIVE_WEAPON_TO_CHAR r2_launcher WEAPONTYPE_ROCKETLAUNCHER 30000 
SET_CHAR_NEVER_TARGETTED r2_launcher TRUE
ADD_ARMOUR_TO_CHAR r2_launcher 100
SET_CHAR_RELATIONSHIP r2_launcher ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1
SET_CHAR_ONLY_DAMAGED_BY_PLAYER r2_launcher TRUE 
SET_CHAR_SUFFERS_CRITICAL_HITS r2_launcher FALSE 
SET_CHAR_DECISION_MAKER r2_launcher r2_empty_ped_decision_maker

//Flame OG  																				
CREATE_CHAR PEDTYPE_MISSION1 VLA2 1784.4 -1971.1 13.1 r2_flame	
SET_CHAR_HEADING r2_flame 52.8
GIVE_WEAPON_TO_CHAR r2_flame WEAPONTYPE_TEC9 30000 
//GIVE_WEAPON_TO_CHAR r2_flame WEAPONTYPE_FLAMETHROWER 30000 
SET_CHAR_NEVER_TARGETTED r2_flame TRUE
ADD_ARMOUR_TO_CHAR r2_flame 100
SET_CHAR_RELATIONSHIP r2_flame ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1
SET_CHAR_ONLY_DAMAGED_BY_PLAYER r2_flame TRUE 
SET_CHAR_SUFFERS_CRITICAL_HITS r2_flame FALSE 
SET_CHAR_DECISION_MAKER r2_flame r2_empty_ped_decision_maker

//Katana OG
CREATE_CHAR PEDTYPE_MISSION1 VLA3 1782.6 -1969.4 13.1 r2_katana	
SET_CHAR_HEADING r2_katana 250.2
GIVE_WEAPON_TO_CHAR r2_katana WEAPONTYPE_KATANA 30000 
SET_CHAR_NEVER_TARGETTED r2_katana TRUE
ADD_ARMOUR_TO_CHAR r2_katana 100
SET_CHAR_RELATIONSHIP r2_katana ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1
SET_CHAR_ONLY_DAMAGED_BY_PLAYER r2_katana TRUE 
SET_CHAR_SUFFERS_CRITICAL_HITS r2_katana FALSE
SET_CHAR_PROOFS r2_katana TRUE FALSE FALSE FALSE FALSE
SET_CHAR_DECISION_MAKER r2_katana r2_empty_ped_decision_maker

CHANGE_GARAGE_TYPE duf_las GARAGE_FOR_SCRIPT_TO_OPEN

SWITCH_EMERGENCY_SERVICES OFF 

//fudge crap coz ai sucks
//SET_GUNSHOT_SENSE_RANGE_FOR_RIOT2 25.0

CLOSE_GARAGE duf_las 

SET_ZONE_NO_COPS ELCO TRUE 

CREATE_PICKUP health PICKUP_ON_STREET_SLOW 1877.54 -1982.63 13.48 r2_pickup_health     

ENABLE_AMBIENT_CRIME FALSE

/*
//////////////////// DEBUG- for skipping driving bit //////////////
HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
SWITCH_WIDESCREEN OFF
MAKE_PLAYER_GANG_REAPPEAR
SET_CAMERA_BEHIND_PLAYER	
RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON
DO_FADE 0 FADE_IN
r2_goals = 1
*/

/*
////////////DEBUG - for starting at r2_goals = 3 ///////////////////
HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
SWITCH_WIDESCREEN OFF
MAKE_PLAYER_GANG_REAPPEAR
SET_CAMERA_BEHIND_PLAYER	
RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON
DO_FADE 0 FADE_IN

REMOVE_BLIP r2_control_blip 
SET_CHAR_COORDINATES scplayer 1922.2 -2073.9 12.5
SET_CHAR_HEADING scplayer 166.7
SET_CHAR_COORDINATES cesar 1921.6 -2077.1 12.5
SET_CHAR_HEADING cesar 5.1

REMOVE_CHAR_FROM_GROUP cesar
r2_goals = 3
*/

/*
//////////////////////DEBUG- for testing last cutscene//////////////
HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
SWITCH_WIDESCREEN OFF
MAKE_PLAYER_GANG_REAPPEAR
SET_CAMERA_BEHIND_PLAYER	
RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON
DO_FADE 0 FADE_IN

r2_goals = 8
r2_control_flag = 1
timera = 1000
REMOVE_CHAR_FROM_GROUP cesar
*/










mission_riot2_loop:
WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
        GOTO mission_riot2_passed  
	ENDIF


	ALTER_WANTED_LEVEL player1 0

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// DEATHCHECKS ///////////////////////////////////////////////////////////////////////////////////
	GOSUB r2_death_checks
	IF r2_deathcheck_flag = 1
		GOTO mission_riot2_failed
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// SETTING UP TIMERS FOR THE ANIMS ///////////////////////////////////////////////////////////////
// timer stuff ---------------------------
	GET_GAME_TIMER m5_timer_ended 
	m5_timer_difference = m5_timer_ended - m5_timer_started
	m5_timer_started = m5_timer_ended

	m5_NE_gang_timer += m5_timer_difference 
	m5_NW_gang_timer += m5_timer_difference 
	m5_SE_gang_timer += m5_timer_difference 
	m5_SW_gang_timer += m5_timer_difference 



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// INITIAL CUTSCENE AND DRIVE TO SEE OG'S ////////////////////////////////////////////////////////

////////////DEBUG//////////////////
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q
		SET_CHAR_COORDINATES scplayer 1776.0 -1988.1 12.9
		SET_CHAR_COORDINATES cesar 1776.0 -1990.1 12.9
	ENDIF
////////////DEBUG//////////////////
	GET_GROUP_SIZE Players_Group minutes r2_group_size

	IF r2_goals = 0 
		IF r2_control_flag = 0
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			r2_speech_goals = 0

			SET_PLAYER_CONTROL player1 OFF
			SWITCH_WIDESCREEN ON
			MAKE_PLAYER_GANG_DISAPPEAR
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			SET_FIXED_CAMERA_POSITION 2509.0 -1672.7 13.3 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2523.1 -1678.2 15.2 JUMP_CUT

			SHUT_ALL_CHARS_UP TRUE

			OPEN_SEQUENCE_TASK r2_seq
				TASK_GO_STRAIGHT_TO_COORD -1 2512.8 -1673.2 12.5 PEDMOVE_WALK -1
				TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 
			CLOSE_SEQUENCE_TASK r2_seq
			PERFORM_SEQUENCE_TASK cesar r2_seq
			CLEAR_SEQUENCE_TASK r2_seq	

			OPEN_SEQUENCE_TASK r2_seq
				TASK_GO_STRAIGHT_TO_COORD -1 2514.1 -1675.2 12.5 PEDMOVE_WALK -1
				TASK_TURN_CHAR_TO_FACE_CHAR -1 cesar 
			CLOSE_SEQUENCE_TASK r2_seq
			PERFORM_SEQUENCE_TASK scplayer r2_seq
			CLEAR_SEQUENCE_TASK r2_seq	

			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS				  
			    WAIT 0
			ENDWHILE
			GOSUB r2_death_checks
			IF r2_deathcheck_flag = 1
				GOTO mission_riot2_failed
			ENDIF

			//Ok, so what's the plan?
			r2_speech_goals = 1
			r2_speech_control_flag = 0
			GOSUB r2_dialogue_setup 
			r2_control_flag = 1

			r2_skip_cutscene_flag = 1
			SKIP_CUTSCENE_START
		ENDIF

		IF r2_control_flag = 1
			GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK task_status	 
			IF task_status = FINISHED_TASK
				//making them chat with each other
				TASK_PLAY_ANIM scplayer IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1
				r2_control_flag = 2
			ENDIF
		ENDIF

		IF r2_control_flag = 2
			IF r2_speech_goals = 0
				r2_skip_cutscene_flag = 0
				SKIP_CUTSCENE_END
				GOSUB r2_death_checks
				IF r2_deathcheck_flag = 1
					GOTO mission_riot2_failed
				ENDIF

				//if cutscene has been skipped
				IF r2_skip_cutscene_flag = 1
					CLEAR_PRINTS
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					r2_speech_goals = 0
					
					DO_FADE 500 FADE_OUT	
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					GOSUB r2_death_checks
					IF r2_deathcheck_flag = 1
						GOTO mission_riot2_failed
					ENDIF

					SET_CHAR_COORDINATES scplayer 2512.8 -1673.2 12.5 
					SET_CHAR_HEADING scplayer 40.6 
			
					SET_CHAR_COORDINATES cesar 2514.1 -1675.2 12.5 
					SET_CHAR_HEADING cesar 220.0 
				ENDIF
		
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			
				MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
				SET_GROUP_MEMBER Players_Group cesar
				r2_flag_cesar_in_group = 1
				SET_GROUP_DECISION_MAKER Players_Group r2_group_decisions
		
				SHUT_ALL_CHARS_UP FALSE
				
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SWITCH_WIDESCREEN OFF
				MAKE_PLAYER_GANG_REAPPEAR
				SET_CAMERA_BEHIND_PLAYER	
				RESTORE_CAMERA_JUMPCUT
				SET_PLAYER_CONTROL player1 ON
				PRINT_NOW ( R2_01 ) 60000 1 // Get a couple of Families to join your gang.
				
				//if cutscene has been skipped
				IF r2_skip_cutscene_flag = 1
					DO_FADE 500 FADE_IN	
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 
					GOSUB r2_death_checks
					IF r2_deathcheck_flag = 1
						GOTO mission_riot2_failed
					ENDIF
				ENDIF

				r2_control_flag = 3

			ENDIF
		ENDIF

	    //checking Cesar is still in players group
		IF r2_control_flag > 2 
			GOSUB r2_mc_strap_group
		ENDIF


		//waiting for player to get the correct number in gang
		IF r2_control_flag = 3 
			IF r2_group_size > 2  
				REMOVE_BLIP r2_control_blip 												 
				ADD_BLIP_FOR_COORD 1774.9 -1974.9 13.1 r2_control_blip
				SET_BLIP_AS_FRIENDLY r2_control_blip FALSE
			
				PRINT_NOW ( R2_13 ) 7000 1 // Go and meet up with the veterano's at Unity Station.
				timerb = 0
				r2_control_flag = 4
			ENDIF
		ENDIF
	
		//waiting for player to reach veterano's
		IF r2_control_flag = 4 
			/// DIALOGUE FOR THIS SECTION
			IF r2_speech_flag = 0
				IF timerb > 7000
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar TRUE
					// Shit's real serious, man, look at the streets, eh.
					r2_speech_goals = 2
					r2_speech_control_flag = 0
					GOSUB r2_dialogue_setup 
					r2_speech_flag = 1
				ENDIF
			ENDIF
			
			//waiting for first convo to end
			IF r2_speech_flag = 1
				IF r2_speech_goals = 0
					timerb = 0 
					r2_speech_flag = 2
				ENDIF
			ENDIF 				 

			//waiting for second convo to end
			IF r2_speech_flag = 2
				IF timerb > 7000
					// While we here, I, eerr, I have a question to ask you.
					r2_speech_goals = 3
					r2_speech_control_flag = 0
					GOSUB r2_dialogue_setup 
					r2_speech_flag = 3
				ENDIF
			ENDIF
		
			IF r2_speech_flag = 3 
				IF r2_speech_goals = 0
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE					
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE
					r2_speech_flag = 4
				ENDIF
			ENDIF

			
			/// MAIN BIT FOR THIS SECTION
			//checking player still has the correct amount of gang dudes
			IF r2_flag_cesar_in_group = 1
				GET_GROUP_SIZE Players_Group minutes r2_group_size
				IF r2_checking_group_size = 0
					IF r2_group_size < 3 
						REMOVE_BLIP r2_control_blip
						CLEAR_THIS_PRINT R2_13
						PRINT_NOW ( R2_01 ) 60000 1 // Get a couple of Families to join your gang.
						r2_checking_group_size = 1
					ENDIF
				ENDIF

				IF r2_checking_group_size = 1
					IF r2_group_size > 2  
						REMOVE_BLIP r2_control_blip
						ADD_BLIP_FOR_COORD 1774.9 -1974.9 13.1 r2_control_blip
						SET_BLIP_AS_FRIENDLY r2_control_blip FALSE
						CLEAR_THIS_PRINT R2_01 
						PRINT_NOW ( R2_13 ) 7000 1 // Go and meet up with the veterano's at Unity Station.
					 	r2_checking_group_size = 0
					ENDIF
				ENDIF
			ENDIF


			//waiting for player to reach the OG's
			IF r2_checking_group_size = 0
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1774.9 -1974.9 13.1 4.0 4.0 4.0 TRUE
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE
					r2_speech_flag = 0
					r2_control_flag = 0
					r2_goals = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// Player has reached OG's - Cutscene ////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	IF r2_goals = 1 
		//getting player and guys to leave their car
		IF r2_control_flag = 0 
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			r2_speech_goals = 0

			DO_FADE 500 FADE_OUT	
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE 
			GOSUB r2_death_checks
			IF r2_deathcheck_flag = 1
				GOTO mission_riot2_failed
			ENDIF

			CLEAR_AREA 1781.1 -1974.8 13.1 30.0 TRUE
		
			SHUT_ALL_CHARS_UP TRUE
			
			SET_PED_DENSITY_MULTIPLIER 0.0
			SET_PLAYER_CONTROL player1 OFF		
			SWITCH_WIDESCREEN ON
			MAKE_PLAYER_GANG_DISAPPEAR
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			REMOVE_CHAR_FROM_GROUP cesar
			
			REQUEST_MODEL BBALL_INGAME 
			REQUEST_ANIMATION BSKTBALL
			REQUEST_ANIMATION SMOKING
			REQUEST_ANIMATION GANGS
			LOAD_ALL_MODELS_NOW

			MAKE_PLAYER_GANG_DISAPPEAR 
			
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				SET_CHAR_COORDINATES scplayer 1781.1 -1974.8 13.1
				SET_CHAR_HEADING scplayer 325.3

				CLEAR_CHAR_TASKS_IMMEDIATELY cesar
				SET_CHAR_COORDINATES cesar 1781.8 -1976.4 13.1
				SET_CHAR_HEADING cesar 302.3
			ELSE
				STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer r2_random_car 
				SET_CAR_COORDINATES r2_random_car 1774.9 -1974.9 13.1 
				TASK_LEAVE_ANY_CAR scplayer 
				TASK_LEAVE_ANY_CAR cesar
			ENDIF 
		
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			SET_CHAR_COORDINATES scplayer 1781.1 -1974.8 13.1
			SET_CHAR_HEADING scplayer 325.3

			CLEAR_CHAR_TASKS_IMMEDIATELY cesar
			SET_CHAR_COORDINATES cesar 1781.8 -1976.4 13.1
			SET_CHAR_HEADING cesar 302.3
			
			OPEN_SEQUENCE_TASK r2_seq
				TASK_PLAY_ANIM -1 prtial_gngtlkA GANGS 4.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM -1 prtial_gngtlkB GANGS 4.0 FALSE FALSE FALSE FALSE -1
				//TASK_PLAY_ANIM -1 prtial_gngtlkC GANGS 4.0 FALSE FALSE FALSE FALSE -1
				TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
			CLOSE_SEQUENCE_TASK r2_seq
			PERFORM_SEQUENCE_TASK r2_launcher r2_seq
			CLEAR_SEQUENCE_TASK r2_seq	
			
			SET_FIXED_CAMERA_POSITION 1781.1 -1970.4 14.7 0.0 0.0 0.0  //looking at trio
			POINT_CAMERA_AT_POINT 1783.2 -1969.9 14.5 JUMP_CUT
		
			DO_FADE 500 FADE_IN	
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE 
			GOSUB r2_death_checks
			IF r2_deathcheck_flag = 1
				GOTO mission_riot2_failed
			ENDIF

			r2_control_flag = 1
			r2_skip_cutscene_flag = 1
			SKIP_CUTSCENE_START
		ENDIF
		
		IF r2_control_flag = 1
			// those Vagos, man, I'm gonna gut those cacos. | Raspalo hasta el hueso! 
			r2_speech_goals = 4
			r2_speech_control_flag = 0
			r2_random_last_label = 2
			GOSUB r2_dialogue_setup 
		
			r2_control_flag = 2
		ENDIF			

		IF r2_control_flag = 2	
			IF r2_speech_goals = 0
				SET_FIXED_CAMERA_POSITION 1784.5 -1967.7 14.7 0.0 0.0 0.0  //looking through trio towards player and cesar
				POINT_CAMERA_AT_POINT 1781.4 -1973.5 14.3 JUMP_CUT
				
				TASK_GO_STRAIGHT_TO_COORD scplayer 1782.3 -1972.8 13.1 PEDMOVE_WALK -1    
				
				TASK_TURN_CHAR_TO_FACE_CHAR r2_katana scplayer 

				TASK_GO_STRAIGHT_TO_COORD r2_flame 1783.8 -1972.4 13.1 PEDMOVE_WALK -1    
				
				OPEN_SEQUENCE_TASK r2_seq
					TASK_GO_STRAIGHT_TO_COORD -1 1783.3 -1973.0 13.1 PEDMOVE_WALK -1
				CLOSE_SEQUENCE_TASK r2_seq
				PERFORM_SEQUENCE_TASK cesar r2_seq
				CLEAR_SEQUENCE_TASK r2_seq	
		
				// Hey, carneles, how my Guerreros, eh? | Cesar! And you must be CJ.      
				r2_speech_goals = 4
				r2_speech_control_flag = 2
				r2_random_last_label = 4
				GOSUB r2_dialogue_setup 
			
				timera = 0
				r2_control_flag = 3
			ENDIF
		ENDIF


		IF r2_control_flag = 3
			IF r2_speech_goals = 0 	
				OPEN_SEQUENCE_TASK r2_seq
					TASK_PLAY_ANIM -1 prtial_gngtlkD GANGS 4.0 FALSE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK r2_seq
				PERFORM_SEQUENCE_TASK cesar r2_seq
				CLEAR_SEQUENCE_TASK r2_seq	
			
				r2_control_flag = 4
			ENDIF
		ENDIF

		IF r2_control_flag = 4
			IF r2_speech_goals = 0 
				GET_SCRIPT_TASK_STATUS cesar PERFORM_SEQUENCE_TASK task_status
				IF task_status = FINISHED_TASK
			 
					CLEAR_CHAR_TASKS r2_flame
					
					OPEN_SEQUENCE_TASK r2_seq
						TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
						TASK_PLAY_ANIM -1 prtial_gngtlkC GANGS 4.0 FALSE FALSE FALSE FALSE -1
						TASK_PLAY_ANIM -1 prtial_gngtlkE GANGS 4.0 FALSE FALSE FALSE FALSE -1
			 		CLOSE_SEQUENCE_TASK r2_seq
					PERFORM_SEQUENCE_TASK r2_flame r2_seq
					CLEAR_SEQUENCE_TASK r2_seq	

					// Cesar says you cool, so we cool, holmes.
					r2_speech_goals = 4
					r2_speech_control_flag = 4
					r2_random_last_label = 5
					GOSUB r2_dialogue_setup 

					r2_control_flag = 5 
				ENDIF
			ENDIF
		ENDIF

		IF r2_control_flag = 5
			IF r2_speech_goals = 0
				timera = 0
				r2_control_flag = 6 
			ENDIF
		ENDIF  
		
		IF r2_control_flag = 6
			IF timera > 1000	
				
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE
				GOSUB r2_death_checks
				IF r2_deathcheck_flag = 1
					GOTO mission_riot2_failed
				ENDIF
			
				CLEAR_AREA 1793.8 -1970.6 12.5 30.0 TRUE
				
				CLEAR_CHAR_TASKS scplayer 
				SET_CHAR_COORDINATES scplayer 1789.5 -1968.5 12.5
				SET_CHAR_HEADING scplayer 234.3 
				TASK_GO_STRAIGHT_TO_COORD scplayer 1798.2 -1974.4 12.5 PEDMOVE_WALK -1 

				CLEAR_CHAR_TASKS cesar 
				SET_CHAR_COORDINATES cesar 1789.0 -1970.6 12.5
				SET_CHAR_HEADING cesar 231.5 
				TASK_GO_STRAIGHT_TO_COORD cesar 1794.0 -1973.5 12.5 PEDMOVE_WALK -1 
		
				SET_CHAR_COORDINATES r2_launcher 1789.3 -1967.4 12.5
				SET_CHAR_HEADING r2_launcher 231.5 
				TASK_GO_STRAIGHT_TO_COORD r2_launcher 1796.3 -1970.7 12.5 PEDMOVE_WALK -1 

				SET_CHAR_COORDINATES r2_katana 1787.4 -1967.2 12.5
				SET_CHAR_HEADING r2_katana 234.5 
				TASK_GO_STRAIGHT_TO_COORD r2_katana 1792.9 -1972.3 12.5 PEDMOVE_WALK -1 

				SET_CHAR_COORDINATES r2_flame 1786.3 -1966.7 12.5
				SET_CHAR_HEADING r2_flame 251.5 
				TASK_GO_STRAIGHT_TO_COORD r2_flame 1793.8 -1970.6 12.5 PEDMOVE_WALK -1 

				//CREATING_ALL_THE_LOOKOUTS
				IF r2_flats_lookout_control_flag = 0
					GOSUB creating_flats_lookouts
					r2_flats_lookout_control_flag = 1
				ENDIF

				SET_FIXED_CAMERA_POSITION 1788.0 -1968.0 14.5 0.0 0.0 0.0  //looking over gang's shoulders towards flats
				POINT_CAMERA_AT_POINT 1799.5 -1973.8 13.6 JUMP_CUT
			
				DO_FADE 500 FADE_IN
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE
				GOSUB r2_death_checks
				IF r2_deathcheck_flag = 1
					GOTO mission_riot2_failed
				ENDIF
			
				// Ok. We will have to work our way through this neighborhood | to get to my house.
				r2_speech_goals = 4
				r2_speech_control_flag = 5
				r2_random_last_label = 6
				GOSUB r2_dialogue_setup 

				timera = 0
				r2_control_flag = 7
			ENDIF
		ENDIF

		IF r2_control_flag = 7 
			IF r2_speech_goals = 0 
				IF timera > 4000 
				
					// If we stick together those Vagos bendejos won't stand a chance! | Watch each others' backs, amigos. 
					r2_speech_goals = 4
					r2_speech_control_flag = 8
					r2_random_last_label = 10
					GOSUB r2_dialogue_setup 
				
					SET_FIXED_CAMERA_POSITION 1823.1 -1997.4 13.5 0.0 0.0 0.0  //looking at flats lookouts
					POINT_CAMERA_AT_POINT 1829.3 -2003.7 14.4 JUMP_CUT
					timera = 0
					r2_control_flag = 8
				ENDIF
			ENDIF
		ENDIF

		IF r2_control_flag = 8 
            IF NOT IS_CHAR_DEAD r2_flats_lookout[1]
				IF IS_OBJECT_ATTACHED r2_ball 
				            
					// idle bounce
					IF IS_CHAR_PLAYING_ANIM r2_flats_lookout[1] BBALL_idleloop
						GET_CHAR_ANIM_CURRENT_TIME r2_flats_lookout[1] BBALL_idleloop r2_anim_time
						IF IS_OBJECT_PLAYING_ANIM r2_ball BBALL_IDLELOOP_O
							SET_OBJECT_ANIM_CURRENT_TIME r2_ball BBALL_IDLELOOP_O r2_anim_time
						ELSE
							IF PLAY_OBJECT_ANIM r2_ball BBALL_IDLELOOP_O BSKTBALL 10000.0 FALSE FALSE
								SET_OBJECT_ANIM_SPEED r2_ball BBALL_IDLELOOP_O 0.0
							ENDIF
						ENDIF
					ENDIF
				ELSE
					ATTACH_OBJECT_TO_CHAR r2_ball r2_flats_lookout[1] 0.0 0.5 -1.0 0.0 0.0 0.0	
				ENDIF
			ENDIF

			IF r2_speech_goals = 0
				IF timera > 4000 
					SET_FIXED_CAMERA_POSITION 1827.4 -1998.7 13.1 0.0 0.0 0.0 //looking at flat lookout coming out of door
					POINT_CAMERA_AT_POINT 1830.2 -1995.2 13.4 JUMP_CUT
			
					IF NOT IS_CHAR_DEAD r2_flats_lookout[5] 
						OPEN_SEQUENCE_TASK r2_seq
							TASK_FOLLOW_PATH_NODES_TO_COORD -1 1831.1 -1995.9 12.6 PEDMOVE_WALK -1
							TASK_ACHIEVE_HEADING -1 180.0 
							TASK_PLAY_ANIM -1 M_smk_in SMOKING 4.0 FALSE FALSE FALSE FALSE -1
						CLOSE_SEQUENCE_TASK r2_seq
						PERFORM_SEQUENCE_TASK r2_flats_lookout[5] r2_seq
						CLEAR_SEQUENCE_TASK r2_seq	
					ENDIF
				
					// Hasta le muerte! | HASTA LE MUERTE!  
					r2_speech_goals = 4
					r2_speech_control_flag = 10
					r2_random_last_label = 12
					GOSUB r2_dialogue_setup 
				
				
					timera = 0
					r2_control_flag = 9
				ENDIF
			ENDIF
		ENDIF

		IF r2_control_flag = 9 
			IF r2_speech_goals = 0
				IF timera > 4000
					IF r2_speech_goals = 0 
						r2_skip_cutscene_flag = 0
						SKIP_CUTSCENE_END
						GOSUB r2_death_checks
						IF r2_deathcheck_flag = 1
							GOTO mission_riot2_failed
						ENDIF

						//if cutscene has been skipped
						IF r2_skip_cutscene_flag = 1
							CLEAR_PRINTS
							CLEAR_MISSION_AUDIO 1
							CLEAR_MISSION_AUDIO 2
							r2_speech_goals = 0
							
							DO_FADE 500 FADE_OUT	
							WHILE GET_FADING_STATUS
							    WAIT 0
							ENDWHILE 
							GOSUB r2_death_checks
							IF r2_deathcheck_flag = 1
								GOTO mission_riot2_failed
							ENDIF
						
							IF r2_flats_lookout_control_flag = 0
								GOSUB creating_flats_lookouts
								r2_flats_lookout_control_flag = 1
							ENDIF

							IF NOT IS_CHAR_DEAD r2_flats_lookout[5] 
								CLEAR_CHAR_TASKS_IMMEDIATELY r2_flats_lookout[5]
								SET_CHAR_COORDINATES r2_flats_lookout[5] 1831.1 -1995.9 12.6  
								SET_CHAR_HEADING r2_flats_lookout[5] 180.0 
								OPEN_SEQUENCE_TASK r2_seq
									TASK_PLAY_ANIM -1 M_smk_in SMOKING 4.0 FALSE FALSE FALSE FALSE -1
								CLOSE_SEQUENCE_TASK r2_seq
								PERFORM_SEQUENCE_TASK r2_flats_lookout[5] r2_seq
								CLEAR_SEQUENCE_TASK r2_seq	
							ENDIF

							CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
							SET_CHAR_COORDINATES scplayer 1793.8 -1970.6 12.5
							SET_CHAR_HEADING scplayer 234.3 
							
							CLEAR_CHAR_TASKS_IMMEDIATELY cesar
							SET_CHAR_COORDINATES cesar 1792.9 -1972.3 12.5
							SET_CHAR_HEADING cesar 231.5 
					
							CLEAR_CHAR_TASKS_IMMEDIATELY r2_launcher
							SET_CHAR_COORDINATES r2_launcher 1794.8 -1969.8 12.5
							SET_CHAR_HEADING r2_launcher 231.5 

							CLEAR_CHAR_TASKS_IMMEDIATELY r2_katana
							SET_CHAR_COORDINATES r2_katana 1791.8 -1971.1 12.5
							SET_CHAR_HEADING r2_katana 234.5 

							CLEAR_CHAR_TASKS_IMMEDIATELY r2_flame
							SET_CHAR_COORDINATES r2_flame 1792.5 -1970.4 12.5
							SET_CHAR_HEADING r2_flame 251.5 
						ENDIF
					
						DELETE_OBJECT r2_ball
						MARK_MODEL_AS_NO_LONGER_NEEDED BBALL_INGAME  
						
						IF NOT IS_CHAR_DEAD r2_flats_lookout[1]
							GIVE_WEAPON_TO_CHAR r2_flats_lookout[1] WEAPONTYPE_TEC9 3000
							CLEAR_CHAR_TASKS_IMMEDIATELY r2_flats_lookout[1]
						ENDIF
						IF NOT IS_CHAR_DEAD r2_flats_lookout[2]
							CLEAR_CHAR_TASKS_IMMEDIATELY r2_flats_lookout[1]
						ENDIF				
					
						IF NOT IS_CHAR_DEAD r2_flats_lookout[1]
							IF NOT IS_CHAR_DEAD r2_flats_lookout[2]
								TASK_CHAT_WITH_CHAR r2_flats_lookout[1] r2_flats_lookout[2] TRUE TRUE
								TASK_CHAT_WITH_CHAR r2_flats_lookout[2] r2_flats_lookout[1] FALSE TRUE
							ENDIF
						ENDIF

						IF NOT IS_CHAR_DEAD r2_flats_lookout[5]  
							GIVE_WEAPON_TO_CHAR r2_flats_lookout[5] WEAPONTYPE_TEC9 3000 
						ENDIF		 
						
						SET_CHAR_DECISION_MAKER r2_launcher r2_ped_decisions
						SET_CHAR_DECISION_MAKER r2_flame r2_ped_decisions
						SET_CHAR_DECISION_MAKER r2_katana r2_ped_decisions
						
						MAKE_PLAYER_GANG_REAPPEAR
						
						SET_GUNSHOT_SENSE_RANGE_FOR_RIOT2 15.0  

						REMOVE_ANIMATION GANGS
						
						SHUT_ALL_CHARS_UP FALSE
						
						REMOVE_BLIP r2_control_blip
						ADD_BLIP_FOR_CHAR cesar r2_control_blip
						SET_BLIP_AS_FRIENDLY r2_control_blip TRUE
						HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
						SWITCH_WIDESCREEN OFF
						MAKE_PLAYER_GANG_REAPPEAR
						SET_CAMERA_BEHIND_PLAYER	
						RESTORE_CAMERA_JUMPCUT
						SET_PLAYER_CONTROL player1 ON
						PRINT_NOW ( R2_03 ) 7000 1 //Help Cesar and the OG's clear out the neighbourhood.
						
						//if cutscene has been skipped
						IF r2_skip_cutscene_flag = 1
							DO_FADE 500 FADE_IN	
							WHILE GET_FADING_STATUS
							    WAIT 0
							ENDWHILE 
							GOSUB r2_death_checks
							IF r2_deathcheck_flag = 1
								GOTO mission_riot2_failed
							ENDIF
						ENDIF
						
						r2_control_flag = 0
						r2_goals = 2
						timerb = 0

					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// START OF THE MISSION - PART 1 - The Flats ////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	IF r2_goals = 2
		////////////// SECTION 1 - the flats ///////////////////
		//// THE LOOKOUTS -  need to be doing stuff (playing cards, shooting hoops, chatting or something?) ////

		//waiting//
		IF r2_flats_lookout_control_flag = 1
			IF r2_flats_lookout_flag < 6
				IF NOT IS_CHAR_DEAD r2_flats_lookout[r2_flats_lookout_flag]
					IF HAS_CHAR_SPOTTED_CHAR r2_flats_lookout[r2_flats_lookout_flag] scplayer
						IF NOT IS_CHAR_DEAD r2_flats_lookout[3]
							OPEN_SEQUENCE_TASK r2_seq
								TASK_GOTO_CHAR_OFFSET -1 scplayer -1 1.5 0.0
								SET_SEQUENCE_TO_REPEAT r2_seq 1 
							CLOSE_SEQUENCE_TASK r2_seq
							PERFORM_SEQUENCE_TASK r2_flats_lookout[3] r2_seq	
							CLEAR_SEQUENCE_TASK r2_seq
						ENDIF
								
						IF NOT IS_CHAR_DEAD r2_flats_lookout[4]
							OPEN_SEQUENCE_TASK r2_seq
								TASK_GOTO_CHAR_OFFSET -1 scplayer -1 3.0 0.0
								SET_SEQUENCE_TO_REPEAT r2_seq 1 
							CLOSE_SEQUENCE_TASK r2_seq
							PERFORM_SEQUENCE_TASK r2_flats_lookout[4] r2_seq	
							CLEAR_SEQUENCE_TASK r2_seq
						ENDIF
						
						r2_flats_lookout_control_flag = 3
					ENDIF
					
					GET_CHAR_HEALTH r2_flats_lookout[r2_flats_lookout_flag] r2_char_health
					IF r2_char_health < 100 
						r2_flats_lookout_control_flag = 4
					ENDIF	  
				ELSE
					r2_flats_lookout_control_flag = 4	
				ENDIF
				r2_flats_lookout_flag ++
			ELSE
				r2_flats_lookout_flag = 0
			ENDIF
		ENDIF

		//controlling//
		// having guys check out the player //
		IF r2_flats_lookout_control_flag = 3
			//checking if one of the lookout guys is dead
			IF r2_flats_lookout_flag < 6
				IF NOT IS_CHAR_DEAD r2_flats_lookout[r2_flats_lookout_flag]
					GET_CHAR_HEALTH r2_flats_lookout[r2_flats_lookout_flag] r2_char_health
					IF r2_char_health < 100 
						r2_flats_lookout_control_flag = 4
					ENDIF
					IF NOT IS_CHAR_DEAD r2_flats_lookout[0]
						IF HAS_CHAR_SPOTTED_CHAR r2_flats_lookout[0] scplayer
							r2_flats_lookout_control_flag = 4
						ENDIF
					ENDIF	 
					IF IS_PLAYER_TARGETTING_CHAR player1 r2_flats_lookout[r2_flats_lookout_flag] 
						r2_flats_lookout_control_flag = 4
					ENDIF
				ELSE
					r2_flats_lookout_control_flag = 4
				ENDIF	  
				r2_flats_lookout_flag ++
			ELSE
				r2_flats_lookout_flag = 0
			ENDIF
		ENDIF

		//lookout's kicking off - setting the scout off to wake up the rest
		IF r2_flats_lookout_control_flag = 4
			//at this point.. the runner will head off and wake up the neighbourhood

			IF r2_speech_goals = 0 
				IF NOT IS_CHAR_DEAD cesar 
					IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer cesar 60.0 60.0 FALSE
						// Northside Vagos assholes!
						r2_speech_goals = 5
						r2_speech_control_flag = 11
						r2_random_last_label = 12
						GOSUB r2_dialogue_setup 
					ENDIF
				ENDIF
			ENDIF
			
			r2_flats_lookout_control_flag = 5
		ENDIF

		IF r2_flats_lookout_control_flag > 3
			IF r2_SW_control_flag[0] = 0
			OR r2_SE_control_flag[0] = 0
			OR r2_NW_control_flag[0] = 0
			OR r2_NE_control_flag[0] = 0
				IF NOT IS_CHAR_DEAD r2_flats_lookout[0]
					IF r2_runner_control_flag = 2
						r2_runner_control_flag = 0
					ENDIF
					GOSUB waking_up_the_neighbourhood
				ENDIF
			ENDIF
			IF r2_SW_control_flag[0] > 0
				IF r2_SE_control_flag[0] > 0
					IF r2_NW_control_flag[0] > 0
						IF r2_NE_control_flag[0] > 0   
							IF NOT IS_CHAR_DEAD r2_flats_lookout[0]
								GET_SCRIPT_TASK_STATUS r2_flats_lookout[0] TASK_KILL_CHAR_ON_FOOT task_status
								IF task_status = FINISHED_TASK
									TASK_KILL_CHAR_ON_FOOT r2_flats_lookout[0] scplayer 
									r2_runner_control_flag = 3
								ENDIF 
							ENDIF
						ENDIF
					ENDIF
				ENDIF	
			ENDIF 
		ENDIF

		//clearing//
		IF r2_flats_lookout_control_flag = 5
			IF IS_CHAR_DEAD r2_flats_lookout[0]
				IF IS_CHAR_DEAD r2_flats_lookout[1]
					IF IS_CHAR_DEAD r2_flats_lookout[2]
						IF IS_CHAR_DEAD r2_flats_lookout[3]
							IF IS_CHAR_DEAD r2_flats_lookout[4]
								IF IS_CHAR_DEAD r2_flats_lookout[5]
									REMOVE_BLIP r2_flats_lookout_blips[0] 
									REMOVE_BLIP r2_flats_lookout_blips[1] 
									REMOVE_BLIP r2_flats_lookout_blips[2] 
									REMOVE_BLIP r2_flats_lookout_blips[3] 
									REMOVE_BLIP r2_flats_lookout_blips[4] 
									REMOVE_BLIP r2_flats_lookout_blips[5] 
									MARK_CHAR_AS_NO_LONGER_NEEDED r2_flats_lookout[0]
									MARK_CHAR_AS_NO_LONGER_NEEDED r2_flats_lookout[1]
									MARK_CHAR_AS_NO_LONGER_NEEDED r2_flats_lookout[2]
									MARK_CHAR_AS_NO_LONGER_NEEDED r2_flats_lookout[3]
									MARK_CHAR_AS_NO_LONGER_NEEDED r2_flats_lookout[4]
									MARK_CHAR_AS_NO_LONGER_NEEDED r2_flats_lookout[5]
									IF r2_SW_control_flag[0] = 0
									AND r2_SE_control_flag[0] = 0
									AND r2_NW_control_flag[0] = 0
									AND r2_NE_control_flag[0] = 0
										r2_SW_control_flag[0] = 1
									ENDIF
									REMOVE_ANIMATION BSKTBALL
									REMOVE_ANIMATION SMOKING
									r2_flats_lookout_control_flag = 6
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//removing blips//
		IF r2_flats_lookout_control_flag < 6
			IF IS_CHAR_DEAD r2_flats_lookout[0]
				REMOVE_BLIP r2_flats_lookout_blips[0] 
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_flats_lookout[0]
			ENDIF 	
			IF IS_CHAR_DEAD r2_flats_lookout[1]
				REMOVE_BLIP r2_flats_lookout_blips[1] 
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_flats_lookout[1]
			ENDIF 	
			IF IS_CHAR_DEAD r2_flats_lookout[2]
				REMOVE_BLIP r2_flats_lookout_blips[2] 
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_flats_lookout[2]
			ENDIF 	
			IF IS_CHAR_DEAD r2_flats_lookout[3]
				REMOVE_BLIP r2_flats_lookout_blips[3] 
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_flats_lookout[3]
			ENDIF 	
			IF IS_CHAR_DEAD r2_flats_lookout[4]
				REMOVE_BLIP r2_flats_lookout_blips[4] 
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_flats_lookout[4]
			ENDIF 	
			IF IS_CHAR_DEAD r2_flats_lookout[5]
				REMOVE_BLIP r2_flats_lookout_blips[5] 
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_flats_lookout[5]
			ENDIF 
		ENDIF	


		//////////////////////////////////////////////////////// NW houses ////////////////////////////////////////////////////
		//// House 0 ////
		//creating//
		IF r2_NW_control_flag[0] = 1
			GOSUB creating_NW_goons
			m5_NW_gang_timer = 0	
			r2_NW_control_flag[0] = 2 
		ENDIF	

		//clearing//
		IF r2_NW_control_flag[0] = 2 
			IF IS_CHAR_DEAD r2_NW_goons[0]
				IF IS_CHAR_DEAD r2_NW_goons[1]
					REMOVE_BLIP r2_NW_goons_blips[0]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NW_goons[0]
					REMOVE_BLIP r2_NW_goons_blips[1]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NW_goons[1]
					r2_NW_control_flag[0] = 3
				ENDIF
			ENDIF
		ENDIF

		//removing blips//
		IF r2_NW_control_flag[0] < 3 
			IF IS_CHAR_DEAD r2_NW_goons[0]
				REMOVE_BLIP r2_NW_goons_blips[0]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_NW_goons[0]
			ENDIF 	
			IF IS_CHAR_DEAD r2_NW_goons[1]
				REMOVE_BLIP r2_NW_goons_blips[1]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_NW_goons[1]
			ENDIF 
		ENDIF	

		//// House 1 ////
		//creating//
		IF r2_NW_control_flag[0] > 0
			IF r2_NW_control_flag[1] = 0
				IF m5_NW_gang_timer > 500
					r2_NW_control_flag[1] = 1
					GOSUB creating_NW_goons	
					r2_NW_control_flag[1] = 2
					m5_NW_gang_timer = 0
				ENDIF
			ENDIF
		ENDIF

		//clearing//
		IF r2_NW_control_flag[1] = 2 
			IF IS_CHAR_DEAD r2_NW_goons[2]
				IF IS_CHAR_DEAD r2_NW_goons[3]
					REMOVE_BLIP r2_NW_goons_blips[2]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NW_goons[2]
					REMOVE_BLIP r2_NW_goons_blips[3]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NW_goons[3]
					r2_NW_control_flag[1] = 3
				ENDIF
			ENDIF
		ENDIF
		
		//removing blips//
		IF r2_NW_control_flag[1] < 3 
			IF IS_CHAR_DEAD r2_NW_goons[2]
				REMOVE_BLIP r2_NW_goons_blips[2]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_NW_goons[2]
			ENDIF 	
			IF IS_CHAR_DEAD r2_NW_goons[3]
				REMOVE_BLIP r2_NW_goons_blips[3]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_NW_goons[3]
			ENDIF 
		ENDIF	

		//// House 2 ////
		//creating//
		IF r2_NW_control_flag[0] > 0
			IF r2_NW_control_flag[1] > 0
				IF r2_NW_control_flag[2] = 0
					IF m5_NW_gang_timer > 500
						r2_NW_control_flag[2] = 1
						GOSUB creating_NW_goons	
						r2_NW_control_flag[2] = 2
						m5_NW_gang_timer = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//clearing//
		IF r2_NW_control_flag[2] = 2 
			IF IS_CHAR_DEAD r2_NW_goons[4]
				IF IS_CHAR_DEAD r2_NW_goons[5]
					REMOVE_BLIP r2_NW_goons_blips[4]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NW_goons[4]
					REMOVE_BLIP r2_NW_goons_blips[5]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NW_goons[5]
					r2_NW_control_flag[2] = 3
				ENDIF
			ENDIF
		ENDIF
		
		//removing blips//
		IF r2_NW_control_flag[2] < 3 
			IF IS_CHAR_DEAD r2_NW_goons[4]
				REMOVE_BLIP r2_NW_goons_blips[4]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_NW_goons[4]
			ENDIF 	
			IF IS_CHAR_DEAD r2_NW_goons[5]
				REMOVE_BLIP r2_NW_goons_blips[5]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_NW_goons[5]
			ENDIF 
		ENDIF	

	    //triggering next house//
		IF r2_NW_control_flag[0] = 3
			IF r2_NW_control_flag[1] = 3
				IF r2_NW_control_flag[2] = 3
					IF r2_NE_control_flag[0] = 0
						r2_NE_control_flag[0] = 1
					ELSE
						IF r2_SW_control_flag[0] = 0
							r2_SW_control_flag[0] = 1
						ELSE
							IF r2_SE_control_flag[0] = 0
								r2_SE_control_flag[0] = 1
							ENDIF
						ENDIF
					ENDIF
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NW_goons[0]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NW_goons[1]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NW_goons[2]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NW_goons[3]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NW_goons[4]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NW_goons[5]
					REMOVE_BLIP r2_NW_goons_blips[0]
					REMOVE_BLIP r2_NW_goons_blips[1]
					REMOVE_BLIP r2_NW_goons_blips[2]
					REMOVE_BLIP r2_NW_goons_blips[3]
					REMOVE_BLIP r2_NW_goons_blips[4]
					REMOVE_BLIP r2_NW_goons_blips[5]
					r2_NW_control_flag[0] = 4
					r2_NW_control_flag[1] = 4
					r2_NW_control_flag[2] = 4
				ENDIF
			ENDIF
		ENDIF
	   	 


		//////////////////////////////////////////////////////// NE houses ////////////////////////////////////////////////////
		//// House 0 ////
		//creating//
		IF r2_NE_control_flag[0] = 1
			GOSUB creating_NE_goons
			m5_NE_gang_timer = 0	
			r2_NE_control_flag[0] = 2 
		ENDIF	

		//clearing//
		IF r2_NE_control_flag[0] = 2 
			IF IS_CHAR_DEAD r2_NE_goons[0]
				IF IS_CHAR_DEAD r2_NE_goons[1]
					REMOVE_BLIP r2_NE_goons_blips[0]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NE_goons[0]
					REMOVE_BLIP r2_NE_goons_blips[1]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NE_goons[1]
					r2_NE_control_flag[0] = 3
				ENDIF
			ENDIF
		ENDIF
		
		//removing blips//
		IF r2_NE_control_flag[0] < 3 
			IF IS_CHAR_DEAD r2_NE_goons[0]
				REMOVE_BLIP r2_NE_goons_blips[0]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_NE_goons[0]
			ENDIF 	
			IF IS_CHAR_DEAD r2_NE_goons[1]
				REMOVE_BLIP r2_NE_goons_blips[1]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_NE_goons[1]
			ENDIF 
		ENDIF	
		
		//// House 1 ////
		//creating//
		IF r2_NE_control_flag[0] > 0
			IF r2_NE_control_flag[1] = 0
				IF m5_NE_gang_timer > 500
					r2_NE_control_flag[1] = 1
					GOSUB creating_NE_goons	
					r2_NE_control_flag[1] = 2
					m5_NE_gang_timer = 0
				ENDIF
			ENDIF
		ENDIF

		//clearing//
		IF r2_NE_control_flag[1] = 2 
			IF IS_CHAR_DEAD r2_NE_goons[2]
				IF IS_CHAR_DEAD r2_NE_goons[3]
					REMOVE_BLIP r2_NE_goons_blips[2]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NE_goons[2]
					REMOVE_BLIP r2_NE_goons_blips[3]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NE_goons[3]
					r2_NE_control_flag[1] = 3
				ENDIF
			ENDIF
		ENDIF
		
		//removing blips//
		IF r2_NE_control_flag[1] < 3 
			IF IS_CHAR_DEAD r2_NE_goons[2]
				REMOVE_BLIP r2_NE_goons_blips[2]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_NE_goons[2]
			ENDIF 	
			IF IS_CHAR_DEAD r2_NE_goons[3]
				REMOVE_BLIP r2_NE_goons_blips[3]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_NE_goons[3]
			ENDIF 
		ENDIF	
		 
		//// House 2 ////
		//creating//
		IF r2_NE_control_flag[0] > 0
			IF r2_NE_control_flag[1] > 0
				IF r2_NE_control_flag[2] = 0
					IF m5_NE_gang_timer > 500
						r2_NE_control_flag[2] = 1
						GOSUB creating_NE_goons	
						r2_NE_control_flag[2] = 2
						m5_NE_gang_timer = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//clearing//
		IF r2_NE_control_flag[2] = 2 
			IF IS_CHAR_DEAD r2_NE_goons[4]
				IF IS_CHAR_DEAD r2_NE_goons[5]
					REMOVE_BLIP r2_NE_goons_blips[4]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NE_goons[4]
					REMOVE_BLIP r2_NE_goons_blips[5]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NE_goons[5]
					r2_NE_control_flag[2] = 3
				ENDIF
			ENDIF
		ENDIF
		
		//removing blips//
		IF r2_NE_control_flag[2] < 3 
			IF IS_CHAR_DEAD r2_NE_goons[4]
				REMOVE_BLIP r2_NE_goons_blips[4]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_NE_goons[4]
			ENDIF 	
			IF IS_CHAR_DEAD r2_NE_goons[5]
				REMOVE_BLIP r2_NE_goons_blips[5]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_NE_goons[5]
			ENDIF 
		ENDIF	
		
	    //triggering next house//
		IF r2_NE_control_flag[0] = 3
			IF r2_NE_control_flag[1] = 3
				IF r2_NE_control_flag[2] = 3
					IF r2_SE_control_flag[0] = 0
						r2_SE_control_flag[0] = 1
					ELSE
						IF r2_NW_control_flag[0] = 0
							r2_NW_control_flag[0] = 1
						ELSE
							IF r2_SW_control_flag[0] = 0
								r2_SW_control_flag[0] = 1
							ENDIF
						ENDIF
					ENDIF
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NE_goons[0]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NE_goons[1]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NE_goons[2]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NE_goons[3]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NE_goons[4]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_NE_goons[5]
					REMOVE_BLIP r2_NE_goons_blips[0]
					REMOVE_BLIP r2_NE_goons_blips[1]
					REMOVE_BLIP r2_NE_goons_blips[2]
					REMOVE_BLIP r2_NE_goons_blips[3]
					REMOVE_BLIP r2_NE_goons_blips[4]
					REMOVE_BLIP r2_NE_goons_blips[5]
					r2_NE_control_flag[0] = 4
					r2_NE_control_flag[1] = 4
					r2_NE_control_flag[2] = 4
				ENDIF
			ENDIF
		ENDIF
		
		
		 
		//////////////////////////////////////////////////////// SW houses ////////////////////////////////////////////////////
		//// House 0 ////
		//creating//
		IF r2_SW_control_flag[0] = 1
			GOSUB creating_SW_goons
			m5_SW_gang_timer = 0	
			r2_SW_control_flag[0] = 2 
		ENDIF	

		//clearing//
		IF r2_SW_control_flag[0] = 2 
			IF IS_CHAR_DEAD r2_SW_goons[0]
				IF IS_CHAR_DEAD r2_SW_goons[1]
					REMOVE_BLIP r2_SW_goons_blips[0]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SW_goons[0]
					REMOVE_BLIP r2_SW_goons_blips[1]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SW_goons[1]
					r2_SW_control_flag[0] = 3
				ENDIF
			ENDIF
		ENDIF
		
		//removing blips//
		IF r2_SW_control_flag[0] < 3 
			IF IS_CHAR_DEAD r2_SW_goons[0]
				REMOVE_BLIP r2_SW_goons_blips[0]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_SW_goons[0]
			ENDIF 	
			IF IS_CHAR_DEAD r2_SW_goons[1]
				REMOVE_BLIP r2_SW_goons_blips[1]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_SW_goons[1]
			ENDIF 
		ENDIF	
		
		//// House 1 ////
		//creating//
		IF r2_SW_control_flag[0] > 0
			IF r2_SW_control_flag[1] = 0
				IF m5_SW_gang_timer > 500
					r2_SW_control_flag[1] = 1
					GOSUB creating_SW_goons	
					r2_SW_control_flag[1] = 2
					m5_SW_gang_timer = 0
				ENDIF
			ENDIF
		ENDIF

		//clearing//
		IF r2_SW_control_flag[1] = 2 
			IF IS_CHAR_DEAD r2_SW_goons[2]
				IF IS_CHAR_DEAD r2_SW_goons[3]
					REMOVE_BLIP r2_SW_goons_blips[2]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SW_goons[2]
					REMOVE_BLIP r2_SW_goons_blips[3]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SW_goons[3]
					r2_SW_control_flag[1] = 3
				ENDIF
			ENDIF
		ENDIF
		
		//removing blips//
		IF r2_SW_control_flag[1] < 3 
			IF IS_CHAR_DEAD r2_SW_goons[2]
				REMOVE_BLIP r2_SW_goons_blips[2]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_SW_goons[2]
			ENDIF 	
			IF IS_CHAR_DEAD r2_SW_goons[3]
				REMOVE_BLIP r2_SW_goons_blips[3]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_SW_goons[3]
			ENDIF 
		ENDIF	
		
		
		//// House 2 ////
		//creating//
		IF r2_SW_control_flag[0] > 0
			IF r2_SW_control_flag[1] > 0
				IF r2_SW_control_flag[2] = 0
					IF m5_SW_gang_timer > 500
						r2_SW_control_flag[2] = 1
						GOSUB creating_SW_goons	
						r2_SW_control_flag[2] = 2
						m5_SW_gang_timer = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//clearing//
		IF r2_SW_control_flag[2] = 2 
			IF IS_CHAR_DEAD r2_SW_goons[4]
				IF IS_CHAR_DEAD r2_SW_goons[5]
					REMOVE_BLIP r2_SW_goons_blips[4]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SW_goons[4]
					REMOVE_BLIP r2_SW_goons_blips[5]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SW_goons[5]
					r2_SW_control_flag[2] = 3
				ENDIF
			ENDIF
		ENDIF
		
		//removing blips//
		IF r2_SW_control_flag[2] < 3 
			IF IS_CHAR_DEAD r2_SW_goons[4]
				REMOVE_BLIP r2_SW_goons_blips[4]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_SW_goons[4]
			ENDIF 	
			IF IS_CHAR_DEAD r2_SW_goons[5]
				REMOVE_BLIP r2_SW_goons_blips[5]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_SW_goons[5]
			ENDIF 
		ENDIF	
	   
	    //triggering next house//
		IF r2_SW_control_flag[0] = 3
			IF r2_SW_control_flag[1] = 3
				IF r2_SW_control_flag[2] = 3
					IF r2_NW_control_flag[0] = 0
						r2_NW_control_flag[0] = 1
					ELSE
						IF r2_SE_control_flag[0] = 0
							r2_SE_control_flag[0] = 1
						ELSE
							IF r2_NE_control_flag[0] = 0
								r2_NE_control_flag[0] = 1
							ENDIF
						ENDIF
					ENDIF
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SW_goons[0]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SW_goons[1]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SW_goons[2]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SW_goons[3]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SW_goons[4]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SW_goons[5]
					REMOVE_BLIP r2_SW_goons_blips[0]
					REMOVE_BLIP r2_SW_goons_blips[1]
					REMOVE_BLIP r2_SW_goons_blips[2]
					REMOVE_BLIP r2_SW_goons_blips[3]
					REMOVE_BLIP r2_SW_goons_blips[4]
					REMOVE_BLIP r2_SW_goons_blips[5]
					r2_SW_control_flag[0] = 4
					r2_SW_control_flag[1] = 4
					r2_SW_control_flag[2] = 4
				ENDIF
			ENDIF
		ENDIF
		
		

		//////////////////////////////////////////////////////// SE houses ////////////////////////////////////////////////////
		//// House 0 ////
		//creating//
		IF r2_SE_control_flag[0] = 1
			GOSUB creating_SE_goons
			m5_SE_gang_timer = 0	
			r2_SE_control_flag[0] = 2 
		ENDIF	

		//clearing//
		IF r2_SE_control_flag[0] = 2 
			IF IS_CHAR_DEAD r2_SE_goons[0]
				IF IS_CHAR_DEAD r2_SE_goons[1]
					REMOVE_BLIP r2_SE_goons_blips[0]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SE_goons[0]
					REMOVE_BLIP r2_SE_goons_blips[1]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SE_goons[1]
					r2_SE_control_flag[0] = 3
				ENDIF
			ENDIF
		ENDIF
		
		//removing blips//
		IF r2_SE_control_flag[0] < 3 
			IF IS_CHAR_DEAD r2_SE_goons[0]
				REMOVE_BLIP r2_SE_goons_blips[0]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_SE_goons[0]
			ENDIF 	
			IF IS_CHAR_DEAD r2_SE_goons[1]
				REMOVE_BLIP r2_SE_goons_blips[1]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_SE_goons[1]
			ENDIF 
		ENDIF	
		
		//// House 1 ////
		//creating//
		IF r2_SE_control_flag[0] > 0
			IF r2_SE_control_flag[1] = 0
				IF m5_SE_gang_timer > 500
					r2_SE_control_flag[1] = 1
					GOSUB creating_SE_goons	
					r2_SE_control_flag[1] = 2
					m5_SE_gang_timer = 0
				ENDIF
			ENDIF
		ENDIF

		//clearing//
		IF r2_SE_control_flag[1] = 2 
			IF IS_CHAR_DEAD r2_SE_goons[2]
				IF IS_CHAR_DEAD r2_SE_goons[3]
					REMOVE_BLIP r2_SE_goons_blips[2]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SE_goons[2]
					REMOVE_BLIP r2_SE_goons_blips[3]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SE_goons[3]
					r2_SE_control_flag[1] = 3
				ENDIF
			ENDIF
		ENDIF
		
		//removing blips//
		IF r2_SE_control_flag[1] < 3 
			IF IS_CHAR_DEAD r2_SE_goons[2]
				REMOVE_BLIP r2_SE_goons_blips[2]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_SE_goons[2]
			ENDIF 	
			IF IS_CHAR_DEAD r2_SE_goons[3]
				REMOVE_BLIP r2_SE_goons_blips[3]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_SE_goons[3]
			ENDIF 
		ENDIF	
		
		//// House 2 ////
		//creating//
		IF r2_SE_control_flag[0] > 0
			IF r2_SE_control_flag[1] > 0
				IF r2_SE_control_flag[2] = 0
					IF m5_SE_gang_timer > 500
						r2_SE_control_flag[2] = 1
						GOSUB creating_SE_goons	
						r2_SE_control_flag[2] = 2
						m5_SE_gang_timer = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//clearing//
		IF r2_SE_control_flag[2] = 2 
			IF IS_CHAR_DEAD r2_SE_goons[4]
				IF IS_CHAR_DEAD r2_SE_goons[5]
					REMOVE_BLIP r2_SE_goons_blips[4]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SE_goons[4]
					REMOVE_BLIP r2_SE_goons_blips[5]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SE_goons[5]
					r2_SE_control_flag[2] = 3
				ENDIF
			ENDIF
		ENDIF
		
		//removing blips//
		IF r2_SE_control_flag[2] < 3 
			IF IS_CHAR_DEAD r2_SE_goons[4]
				REMOVE_BLIP r2_SE_goons_blips[4]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_SE_goons[4]
			ENDIF 	
			IF IS_CHAR_DEAD r2_SE_goons[5]
				REMOVE_BLIP r2_SE_goons_blips[5]
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_SE_goons[5]
			ENDIF 
		ENDIF	
		
	    //triggering next house//
		IF r2_SE_control_flag[0] = 3
			IF r2_SE_control_flag[1] = 3
				IF r2_SE_control_flag[2] = 3
					IF r2_SW_control_flag[0] = 0
						r2_SW_control_flag[0] = 1
					ELSE
						IF r2_NE_control_flag[0] = 0
							r2_NE_control_flag[0] = 1
						ELSE
							IF r2_NW_control_flag[0] = 0
								r2_NW_control_flag[0] = 1
							ENDIF
						ENDIF
					ENDIF
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SE_goons[0]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SE_goons[1]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SE_goons[2]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SE_goons[3]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SE_goons[4]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_SE_goons[5]
					REMOVE_BLIP r2_SE_goons_blips[0]
					REMOVE_BLIP r2_SE_goons_blips[1]
					REMOVE_BLIP r2_SE_goons_blips[2]
					REMOVE_BLIP r2_SE_goons_blips[3]
					REMOVE_BLIP r2_SE_goons_blips[4]
					REMOVE_BLIP r2_SE_goons_blips[5]
					r2_SE_control_flag[0] = 4
					r2_SE_control_flag[1] = 4
					r2_SE_control_flag[2] = 4
				ENDIF
			ENDIF
		ENDIF




	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////// CONTROLLING THE AI OF THE OG'S AND CESAR //////////////////////////////////////////////////////
		///initial speech 
		IF r2_speech_flag = 0
			IF r2_og_control_flag < 6
				IF timerb > 7000
					IF NOT IS_CHAR_DEAD cesar 
						IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer cesar 60.0 60.0 FALSE
							IF r2_speech_goals = 0 
								SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar TRUE
								// Quickly, go! GO!
								r2_speech_goals = 5
								r2_speech_control_flag = 3
								r2_random_last_label = 4
								GOSUB r2_dialogue_setup
								r2_speech_flag = 1
							ENDIF 
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		/// moving them to the lookout place
		IF r2_og_control_flag = 0
			CLEAR_CHAR_TASKS r2_flame 
			CLEAR_CHAR_TASKS r2_launcher 
			CLEAR_CHAR_TASKS r2_katana 
			CLEAR_CHAR_TASKS cesar 
			TASK_FOLLOW_PATH_NODES_TO_COORD r2_flame 1824.9 -1986.7 12.6 PEDMOVE_WALK -2
			TASK_FOLLOW_PATH_NODES_TO_COORD r2_launcher 1822.3 -1986.7 12.6 PEDMOVE_WALK -2
			TASK_FOLLOW_PATH_NODES_TO_COORD r2_katana 1820.9 -1984.7 12.6 PEDMOVE_WALK -2
			TASK_FOLLOW_PATH_NODES_TO_COORD cesar 1824.5 -1984.0 12.6 PEDMOVE_WALK -2
			
			r2_og_control_flag = 1
		ENDIF

		/// moving them to the SW houses
		IF r2_og_control_flag = 1
			IF r2_flats_lookout_control_flag = 6
				CLEAR_CHAR_TASKS r2_flame 
				CLEAR_CHAR_TASKS r2_launcher 
				CLEAR_CHAR_TASKS r2_katana 
				CLEAR_CHAR_TASKS cesar 
				TASK_FOLLOW_PATH_NODES_TO_COORD r2_flame 1856.6 -2008.5 12.6 PEDMOVE_WALK -2
				TASK_FOLLOW_PATH_NODES_TO_COORD r2_launcher 1859.6 -2007.5 12.6 PEDMOVE_WALK -2
				TASK_FOLLOW_PATH_NODES_TO_COORD r2_katana 1853.6 -2007.5 12.6 PEDMOVE_WALK -2
				TASK_FOLLOW_PATH_NODES_TO_COORD cesar 1854.8 -2005.5 12.6 PEDMOVE_WALK -2
			
				IF NOT IS_CHAR_DEAD cesar 
					IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer cesar 60.0 60.0 FALSE
						IF r2_speech_goals = 0 
							// Follow me!
							r2_speech_goals = 5
							r2_speech_control_flag = 4
							r2_random_last_label = 5
							GOSUB r2_dialogue_setup
						ENDIF 
					ENDIF
				ENDIF
				
				r2_og_control_flag = 2
			ENDIF
			
			IF IS_CHAR_DEAD r2_flats_lookout[1]
				IF IS_CHAR_DEAD r2_flats_lookout[2]
					IF IS_CHAR_DEAD r2_flats_lookout[3]
						IF IS_CHAR_DEAD r2_flats_lookout[4]
							IF IS_CHAR_DEAD r2_flats_lookout[5]
								CLEAR_CHAR_TASKS r2_flame 
								CLEAR_CHAR_TASKS r2_launcher 
								CLEAR_CHAR_TASKS r2_katana 
								CLEAR_CHAR_TASKS cesar 
								TASK_FOLLOW_PATH_NODES_TO_COORD r2_flame 1856.6 -2008.5 12.6 PEDMOVE_WALK -2
								TASK_FOLLOW_PATH_NODES_TO_COORD r2_launcher 1859.6 -2007.5 12.6 PEDMOVE_WALK -2
								TASK_FOLLOW_PATH_NODES_TO_COORD r2_katana 1853.6 -2007.5 12.6 PEDMOVE_WALK -2
								TASK_FOLLOW_PATH_NODES_TO_COORD cesar 1854.8 -2005.5 12.6 PEDMOVE_WALK -2
						
								IF NOT IS_CHAR_DEAD cesar 
									IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer cesar 60.0 60.0 FALSE
										IF r2_speech_goals = 0 
											// Keep close!
											r2_speech_goals = 5
											r2_speech_control_flag = 5
											r2_random_last_label = 6
											GOSUB r2_dialogue_setup
										ENDIF 
									ENDIF
								ENDIF
				
								r2_og_control_flag = 2
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		/// moving them to the NW houses
		IF r2_og_control_flag = 2
			IF r2_SW_control_flag[0] = 4
				CLEAR_CHAR_TASKS r2_flame 
				CLEAR_CHAR_TASKS r2_launcher 
				CLEAR_CHAR_TASKS r2_katana 
				CLEAR_CHAR_TASKS cesar 
				TASK_FOLLOW_PATH_NODES_TO_COORD r2_flame 1858.8 -1993.7 12.6 PEDMOVE_WALK -2
				TASK_FOLLOW_PATH_NODES_TO_COORD r2_launcher 1858.8 -1991.6 12.6 PEDMOVE_WALK -2
				TASK_FOLLOW_PATH_NODES_TO_COORD r2_katana 1858.8 -1989.7 12.6 PEDMOVE_WALK -2
				TASK_FOLLOW_PATH_NODES_TO_COORD cesar 1858.8 -1987.7 12.6 PEDMOVE_WALK -2
			
				IF NOT IS_CHAR_DEAD cesar 
					IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer cesar 60.0 60.0 FALSE
						IF r2_speech_goals = 0 
							// Keep together!
							r2_speech_goals = 5
							r2_speech_control_flag = 6
							r2_random_last_label = 7
							GOSUB r2_dialogue_setup
						ENDIF 
					ENDIF
				ENDIF
						
				r2_og_control_flag = 3
			ENDIF
		ENDIF

		/// moving them to the NE houses
		IF r2_og_control_flag = 3
			IF r2_NW_control_flag[0] = 4
				CLEAR_CHAR_TASKS r2_flame 
				CLEAR_CHAR_TASKS r2_launcher 
				CLEAR_CHAR_TASKS r2_katana 
				CLEAR_CHAR_TASKS cesar 
				TASK_FOLLOW_PATH_NODES_TO_COORD r2_flame 1886.2 -1995.2 12.6 PEDMOVE_WALK -2
				TASK_FOLLOW_PATH_NODES_TO_COORD r2_launcher 1886.2 -1993.2 12.6 PEDMOVE_WALK -2
				TASK_FOLLOW_PATH_NODES_TO_COORD r2_katana 1886.2 -1991.2 12.6 PEDMOVE_WALK -2
				TASK_FOLLOW_PATH_NODES_TO_COORD cesar 1886.2 -1989.2 12.6 PEDMOVE_WALK -2
			
				IF NOT IS_CHAR_DEAD cesar 
					IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer cesar 60.0 60.0 FALSE
						IF r2_speech_goals = 0 
							// Stay close.
							r2_speech_goals = 5
							r2_speech_control_flag = 7
							r2_random_last_label = 8
							GOSUB r2_dialogue_setup
						ENDIF 
					ENDIF
				ENDIF
				
				r2_og_control_flag = 4
			ENDIF
		ENDIF

		/// moving them to the SE houses
		IF r2_og_control_flag = 4
			IF r2_NE_control_flag[0] = 4
				CLEAR_CHAR_TASKS r2_flame 
				CLEAR_CHAR_TASKS r2_launcher 
				CLEAR_CHAR_TASKS r2_katana 
				CLEAR_CHAR_TASKS cesar 
				TASK_FOLLOW_PATH_NODES_TO_COORD r2_flame 1903.1 -2009.3 12.6 PEDMOVE_WALK -2
				TASK_FOLLOW_PATH_NODES_TO_COORD r2_launcher 1905.1 -2009.3 12.6 PEDMOVE_WALK -2
				TASK_FOLLOW_PATH_NODES_TO_COORD r2_katana 1907.1 -2009.3 12.6 PEDMOVE_WALK -2
				TASK_FOLLOW_PATH_NODES_TO_COORD cesar 1909.1 -2009.3 12.6 PEDMOVE_WALK -2
			
				IF NOT IS_CHAR_DEAD cesar 
					IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer cesar 60.0 60.0 FALSE
						IF r2_speech_goals = 0 
							// Keep it tight!
							r2_speech_goals = 5
							r2_speech_control_flag = 8
							r2_random_last_label = 9
							GOSUB r2_dialogue_setup
						ENDIF 
					ENDIF
				ENDIF
				
				r2_og_control_flag = 5
			ENDIF
		ENDIF

		
		
		//// Heading to the second part of the mission
		IF r2_og_control_flag = 5
			IF r2_SE_control_flag[0] = 4 
				CLEAR_CHAR_TASKS r2_flame 
				CLEAR_CHAR_TASKS r2_launcher 
				CLEAR_CHAR_TASKS r2_katana 
				CLEAR_CHAR_TASKS cesar 
				OPEN_SEQUENCE_TASK r2_seq
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 1916.8 -2059.2 12.4 PEDMOVE_RUN -2
					TASK_GO_STRAIGHT_TO_COORD -1 1921.6 -2077.1 12.5 PEDMOVE_RUN -2
					TASK_ACHIEVE_HEADING -1 5.1
				CLOSE_SEQUENCE_TASK r2_seq
				PERFORM_SEQUENCE_TASK cesar r2_seq	
				CLEAR_SEQUENCE_TASK r2_seq
		
				OPEN_SEQUENCE_TASK r2_seq
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 1914.5 -2058.4 12.4 PEDMOVE_RUN -2
					TASK_GO_STRAIGHT_TO_COORD -1 1922.6 -2077.1 12.5 PEDMOVE_RUN -2
					TASK_ACHIEVE_HEADING -1 5.1
				CLOSE_SEQUENCE_TASK r2_seq
				PERFORM_SEQUENCE_TASK r2_flame r2_seq	
				CLEAR_SEQUENCE_TASK r2_seq

				OPEN_SEQUENCE_TASK r2_seq
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 1918.1 -2061.8 12.4 PEDMOVE_RUN -2
					TASK_GO_STRAIGHT_TO_COORD -1 1923.6 -2077.1 12.5 PEDMOVE_RUN -2
					TASK_ACHIEVE_HEADING -1 5.1
				CLOSE_SEQUENCE_TASK r2_seq
				PERFORM_SEQUENCE_TASK r2_launcher r2_seq	
				CLEAR_SEQUENCE_TASK r2_seq
				
				OPEN_SEQUENCE_TASK r2_seq
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 1919.8 -2059.6 12.4 PEDMOVE_RUN -2
					TASK_GO_STRAIGHT_TO_COORD -1 1924.6 -2077.1 12.5 PEDMOVE_RUN -2
					TASK_ACHIEVE_HEADING -1 5.1
				CLOSE_SEQUENCE_TASK r2_seq
				PERFORM_SEQUENCE_TASK r2_katana r2_seq	
				CLEAR_SEQUENCE_TASK r2_seq
				
				IF NOT IS_CHAR_DEAD cesar 
					IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer cesar 60.0 60.0 FALSE
						// That's the last of them, let's move out!
						r2_speech_goals = 5
						r2_speech_control_flag = 0
						r2_random_last_label = 1
						GOSUB r2_dialogue_setup 
					ENDIF
				ENDIF
				
				REMOVE_BLIP r2_control_blip
				ADD_BLIP_FOR_COORD 1922.8 -2075.0 12.5 r2_control_blip 

				r2_speech_flag = 0
				r2_og_control_flag = 6
			ENDIF
		ENDIF
		
		IF r2_og_control_flag = 6
		
			IF r2_speech_flag = 0
				IF r2_speech_goals = 0
					PRINT_NOW ( R2_04 ) 11000 1 //Head to the alleyway!
					r2_speech_flag = 1
				ENDIF
			ENDIF
				 
			IF LOCATE_CHAR_ON_FOOT_3D scplayer 1922.8 -2075.0 12.5 1.2 1.2 2.0 TRUE
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				r2_speech_goals = 0	
				r2_control_flag = 0
				r2_goals = 3
			ENDIF
		ENDIF
	ENDIF



	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////// SETTING BOTH GANGS TO HATE EACH OTHER /////////////////////////////////////////////////////////

	/*
	IF r2_attack_flag = 0
		IF r2_flats_lookout_control_flag > 3
			ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE r2_ped_decisions EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 FALSE TRUE 	
			ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE r2_ped_decisions EVENT_ACQUAINTANCE_PED_DISLIKE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 FALSE TRUE 	
			r2_attack_flag = 1
		ENDIF
		
		IF NOT IS_CHAR_DEAD r2_flats_lookout[0]
			IF HAS_CHAR_SPOTTED_CHAR cesar r2_flats_lookout[0]
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE r2_ped_decisions EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 FALSE TRUE 	
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE r2_ped_decisions EVENT_ACQUAINTANCE_PED_DISLIKE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 FALSE TRUE 	
				r2_attack_flag = 1
			ENDIF
		ENDIF
	ENDIF
	*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////  PART 2 - Cutscene in the back alleyway - STAGE 1//////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	IF r2_goals = 3
		IF r2_control_flag = 0
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			r2_speech_goals = 0

			DO_FADE 500 FADE_OUT	
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE 
			GOSUB r2_death_checks
			IF r2_deathcheck_flag = 1
				GOTO mission_riot2_failed
			ENDIF
		
			SET_PLAYER_CONTROL player1 OFF 
			SWITCH_WIDESCREEN ON
			MAKE_PLAYER_GANG_DISAPPEAR
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			
			SHUT_ALL_CHARS_UP TRUE
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cesar FALSE
			
			SET_CAR_DENSITY_MULTIPLIER 0.0
			
			IF NOT IS_GARAGE_CLOSED duf_las
				CLOSE_GARAGE duf_las 
			ENDIF

			REQUEST_MODEL GLENDALE
			REQUEST_MODEL ROCKETLA	 
			
			REQUEST_ANIMATION CAR
			REQUEST_ANIMATION SWAT
			REQUEST_ANIMATION CRACK
			REQUEST_ANIMATION CASINO
			LOAD_ALL_MODELS_NOW 
		
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE r2_ped_decisions EVENT_ACQUAINTANCE_PED_HATE  
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE r2_ped_decisions EVENT_ACQUAINTANCE_PED_DISLIKE  
			
			GOSUB r2_death_checks
			IF r2_deathcheck_flag = 1
				GOTO mission_riot2_failed
			ENDIF

			CLEAR_AREA 1897.0 -2103.8 12.3 300.0 TRUE

			//car that launcher hides behind
			CREATE_CAR GLENDALE 1897.0 -2103.8 12.3 r2_junk[0]																   
			SET_CAR_HEADING	r2_junk[0] 2.4																			   
			FREEZE_CAR_POSITION r2_junk[0] TRUE 
			LOCK_CAR_DOORS r2_junk[0] CARLOCK_LOCKED 
			SET_CAR_PROOFS r2_junk[0] TRUE TRUE TRUE TRUE TRUE ///debug - car should be made into a wreck 
			//EXPLODE_CAR r2_junk[0]
		
			//car that other guys are working on
			CREATE_CAR GLENDALE 1857.3 -2101.3 12.3 r2_junk[1]																   
			FREEZE_CAR_POSITION r2_junk[1] TRUE 
		   	SET_CAR_HEADING	r2_junk[1] 359.7																			   
			LOCK_CAR_DOORS r2_junk[1] CARLOCK_LOCKED 

			//guy underneath car
			GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
			CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1860.9 -2099.6 12.6 r2_car_mechanics[0]
			SET_CHAR_HEADING r2_car_mechanics[0] 270.0
			GIVE_WEAPON_TO_CHAR r2_car_mechanics[0] WEAPONTYPE_TEC9 3000 
			SET_CHAR_DECISION_MAKER r2_car_mechanics[0] r2_empty_ped_decision_maker
			TASK_PLAY_ANIM r2_car_mechanics[0] Fixn_Car_loop CAR 4.0 TRUE FALSE FALSE FALSE -1
			ADD_BLIP_FOR_CHAR r2_car_mechanics[0] r2_car_mechanics_blips[0]
			CHANGE_BLIP_SCALE r2_car_mechanics_blips[0] 1 					
			SET_CHAR_ACCURACY r2_car_mechanics[0] r2_char_accuracy
			ADD_ARMOUR_TO_CHAR r2_car_mechanics[0] 50

			//guy watching guy under the car
			GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
			CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1859.9 -2102.5 12.5 r2_car_mechanics[1]
			SET_CHAR_HEADING r2_car_mechanics[1] 48.0
			GIVE_WEAPON_TO_CHAR r2_car_mechanics[1] WEAPONTYPE_TEC9 3000 
			SET_CHAR_DECISION_MAKER r2_car_mechanics[1] r2_empty_ped_decision_maker
			ADD_BLIP_FOR_CHAR r2_car_mechanics[1] r2_car_mechanics_blips[1]
			CHANGE_BLIP_SCALE r2_car_mechanics_blips[1] 1 					
			SET_CHAR_ACCURACY r2_car_mechanics[1] r2_char_accuracy

			//guy ducking behind car (right as you look towards player)
			GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
			CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1855.6 -2102.6 12.5 r2_car_mechanics[2]
			SET_CHAR_HEADING r2_car_mechanics[2] 282.0
			GIVE_WEAPON_TO_CHAR r2_car_mechanics[2] WEAPONTYPE_TEC9 3000 
			SET_CHAR_DECISION_MAKER r2_car_mechanics[2] r2_empty_ped_decision_maker
			TASK_PLAY_ANIM r2_car_mechanics[2] weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
			ADD_BLIP_FOR_CHAR r2_car_mechanics[2] r2_car_mechanics_blips[2]
			CHANGE_BLIP_SCALE r2_car_mechanics_blips[2] 1 					
			SET_CHAR_ACCURACY r2_car_mechanics[2] r2_char_accuracy
			ADD_ARMOUR_TO_CHAR r2_car_mechanics[2] 50

			//guy ducking behind car (left as you look towards player)
			GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
			CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1855.6 -2099.6 12.5 r2_car_mechanics[3]
			SET_CHAR_HEADING r2_car_mechanics[3] 282.0
			GIVE_WEAPON_TO_CHAR r2_car_mechanics[3] WEAPONTYPE_TEC9 3000 
			SET_CHAR_DECISION_MAKER r2_car_mechanics[3] r2_empty_ped_decision_maker
			TASK_PLAY_ANIM r2_car_mechanics[3] weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
			ADD_BLIP_FOR_CHAR r2_car_mechanics[3] r2_car_mechanics_blips[3]
			CHANGE_BLIP_SCALE r2_car_mechanics_blips[3] 1 					
			SET_CHAR_ACCURACY r2_car_mechanics[3] r2_char_accuracy

			r2_skip_cutscene_flag = 1
			SKIP_CUTSCENE_START

			CLEAR_AREA 1918.1 -2076.9 12.5 30.0 TRUE
			
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			SET_CHAR_COORDINATES scplayer 1918.1 -2076.9 12.5   
			SET_CHAR_HEADING scplayer 171.1 
			TASK_GO_STRAIGHT_TO_COORD scplayer 1916.1 -2093.3 12.5 PEDMOVE_WALK -1    
		
			CLEAR_CHAR_TASKS_IMMEDIATELY cesar 
			SET_CHAR_COORDINATES cesar 1919.1 -2077.7 12.5   
			SET_CHAR_HEADING cesar 171.5 
			TASK_GO_STRAIGHT_TO_COORD cesar 1917.1 -2094.1 12.5 PEDMOVE_WALK -1 
			SET_CHAR_DECISION_MAKER cesar r2_empty_ped_decision_maker
		
			CLEAR_CHAR_TASKS_IMMEDIATELY r2_launcher 
			SET_CHAR_COORDINATES r2_launcher 1920.3 -2075.3 12.5   
			SET_CHAR_HEADING r2_launcher 189.5 
			GIVE_WEAPON_TO_CHAR r2_launcher WEAPONTYPE_ROCKETLAUNCHER 1 
			TASK_GO_STRAIGHT_TO_COORD r2_launcher 1918.3 -2091.7 12.5 PEDMOVE_WALK -1    
			SET_CHAR_DECISION_MAKER r2_launcher r2_empty_ped_decision_maker
		
			CLEAR_CHAR_TASKS_IMMEDIATELY r2_flame 
			SET_CHAR_COORDINATES r2_flame 1918.8 -2075.3 12.5   
			SET_CHAR_HEADING r2_flame 165.9 
			TASK_GO_STRAIGHT_TO_COORD r2_flame 1916.8 -2091.7 12.5 PEDMOVE_WALK -1    
			SET_CHAR_DECISION_MAKER r2_flame r2_empty_ped_decision_maker
		
			CLEAR_CHAR_TASKS_IMMEDIATELY r2_katana 
			SET_CHAR_COORDINATES r2_katana 1919.6 -2074.0 12.5   
			SET_CHAR_HEADING r2_katana 173.0 
			TASK_GO_STRAIGHT_TO_COORD r2_katana 1917.6 -2090.4 12.5 PEDMOVE_WALK -1    
			SET_CHAR_DECISION_MAKER r2_katana r2_empty_ped_decision_maker
		
			SET_FIXED_CAMERA_POSITION 1914.65 -2087.8 13.4 0.0 0.0 0.0
			//POINT_CAMERA_AT_CHAR scplayer FIXED JUMP_CUT
			POINT_CAMERA_AT_POINT 1917.9 -2080.7 13.0 JUMP_CUT //- debug for fixed cam if les wants to lose pan cam
			
			DO_FADE 500 FADE_IN	
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE 
			GOSUB r2_death_checks
			IF r2_deathcheck_flag = 1
				GOTO mission_riot2_failed
			ENDIF
		
			// That was the easy bit, eh.
			r2_speech_goals = 6
			r2_speech_control_flag = 0
			r2_random_last_label = 6
			GOSUB r2_dialogue_setup 
		
			timera = 0
			r2_control_flag = 1 
		ENDIF 


		IF r2_control_flag = 1 
			IF timera > 8000
				CLEAR_AREA 1916.9 -2101.1 12.6 30.0 TRUE
				
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				SET_CHAR_COORDINATES scplayer 1916.9 -2101.1 12.6   
				SET_CHAR_HEADING scplayer 90.0 
				TASK_GO_STRAIGHT_TO_COORD scplayer 1912.4 -2101.1 12.5 PEDMOVE_WALK -1    
			
				CLEAR_CHAR_TASKS_IMMEDIATELY cesar
				SET_CHAR_COORDINATES cesar 1916.9 -2102.1 12.6   
				SET_CHAR_HEADING cesar 90.0 
				TASK_GO_STRAIGHT_TO_COORD cesar 1912.4 -2102.1 12.5 PEDMOVE_WALK -1    
			
				CLEAR_CHAR_TASKS_IMMEDIATELY r2_launcher
				SET_CHAR_COORDINATES r2_launcher 1916.9 -2103.1 12.6   
				SET_CHAR_HEADING r2_launcher 90.0 
				TASK_GO_STRAIGHT_TO_COORD r2_launcher 1912.4 -2103.1 12.5 PEDMOVE_WALK -1    
			
				CLEAR_CHAR_TASKS_IMMEDIATELY r2_katana
				SET_CHAR_COORDINATES r2_katana 1916.9 -2100.1 12.6   
				SET_CHAR_HEADING r2_katana 90.0 
				TASK_GO_STRAIGHT_TO_COORD r2_katana 1912.4 -2100.1 12.5 PEDMOVE_WALK -1    
			
				CLEAR_CHAR_TASKS_IMMEDIATELY r2_flame
				SET_CHAR_COORDINATES r2_flame 1916.9 -2099.1 12.6   
				SET_CHAR_HEADING r2_flame 90.0 
				TASK_GO_STRAIGHT_TO_COORD r2_flame 1912.4 -2099.1 12.5 PEDMOVE_WALK -1    
			
				SET_FIXED_CAMERA_POSITION 1916.5 -2100.9 12.8 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1906.2 -2101.0 14.8 JUMP_CUT
				timera = 0
				r2_control_flag = 2
			ENDIF
		ENDIF
	
		IF r2_control_flag = 2 
			IF r2_speech_goals = 0 
				IF timera > 4000
					IF NOT IS_CHAR_DEAD r2_car_mechanics[0] 	
						OPEN_SEQUENCE_TASK r2_seq
							TASK_PLAY_ANIM -1 fixn_car_out CAR 4.0 FALSE FALSE FALSE FALSE -1
							TASK_SHOOT_AT_COORD -1 1916.5 -2100.9 12.8 3000
						CLOSE_SEQUENCE_TASK r2_seq
						PERFORM_SEQUENCE_TASK r2_car_mechanics[0] r2_seq	
						CLEAR_SEQUENCE_TASK r2_seq
					ENDIF
					
					IF NOT IS_CHAR_DEAD r2_car_mechanics[1] 	
						OPEN_SEQUENCE_TASK r2_seq
							TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 
							//TASK_PAUSE -1 1000
							TASK_TOGGLE_DUCK -1 TRUE
							TASK_SHOOT_AT_COORD -1 1916.5 -2100.9 12.8 3000
						CLOSE_SEQUENCE_TASK r2_seq
						PERFORM_SEQUENCE_TASK r2_car_mechanics[1] r2_seq	
						CLEAR_SEQUENCE_TASK r2_seq
					ENDIF  

					IF NOT IS_CHAR_DEAD r2_car_mechanics[2] 	
						OPEN_SEQUENCE_TASK r2_seq
							TASK_PAUSE -1 700 
							TASK_STAND_STILL -1 100
							TASK_SHOOT_AT_COORD -1 1916.5 -2100.9 12.8 3000
						CLOSE_SEQUENCE_TASK r2_seq
						PERFORM_SEQUENCE_TASK r2_car_mechanics[2] r2_seq	
						CLEAR_SEQUENCE_TASK r2_seq
					ENDIF  
					
					IF NOT IS_CHAR_DEAD r2_car_mechanics[3] 	
						OPEN_SEQUENCE_TASK r2_seq
							TASK_PAUSE -1 1000 
							TASK_STAND_STILL -1 100
							TASK_SHOOT_AT_COORD -1 1916.5 -2100.9 12.8 3000
						CLOSE_SEQUENCE_TASK r2_seq
						PERFORM_SEQUENCE_TASK r2_car_mechanics[3] r2_seq	
						CLEAR_SEQUENCE_TASK r2_seq
					ENDIF  

					// C'mon, I want to take my house back!
					r2_speech_goals = 6
					r2_speech_control_flag = 6
					r2_random_last_label = 7	
					GOSUB r2_dialogue_setup 
					
					SET_FIXED_CAMERA_POSITION 1866.1 -2101.8 13.4 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 1849.9 -2101.0 14.98 JUMP_CUT
					timera = 0
					r2_control_flag = 3
				ENDIF
			ENDIF
		ENDIF

		IF r2_control_flag = 3
			IF r2_speech_goals = 0 
				IF timera > 4000

					r2_skip_cutscene_flag = 0
					SKIP_CUTSCENE_END
					GOSUB r2_death_checks
					IF r2_deathcheck_flag = 1
						GOTO mission_riot2_failed
					ENDIF
					
					//mission has been skipped
					IF r2_skip_cutscene_flag = 1
						CLEAR_PRINTS
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						r2_speech_goals = 0	

						DO_FADE 500 FADE_OUT	
						WHILE GET_FADING_STATUS
						    WAIT 0
						ENDWHILE 
						GOSUB r2_death_checks
						IF r2_deathcheck_flag = 1
							GOTO mission_riot2_failed
						ENDIF

						CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
						SET_CHAR_COORDINATES scplayer 1912.4 -2101.1 12.5
						SET_CHAR_HEADING scplayer 90.0  
				
						CLEAR_CHAR_TASKS_IMMEDIATELY cesar
						SET_CHAR_COORDINATES cesar 1912.4 -2102.1 12.5
						SET_CHAR_HEADING cesar 90.0  

						CLEAR_CHAR_TASKS_IMMEDIATELY r2_launcher
						SET_CHAR_COORDINATES r2_launcher 1912.4 -2103.1 12.5
						SET_CHAR_HEADING r2_launcher 90.0  

						CLEAR_CHAR_TASKS_IMMEDIATELY r2_katana
						SET_CHAR_COORDINATES r2_katana 1912.4 -2100.1 12.5
						SET_CHAR_HEADING r2_katana 90.0  

						CLEAR_CHAR_TASKS_IMMEDIATELY r2_flame
						SET_CHAR_COORDINATES r2_flame 1912.4 -2099.1 12.5
						SET_CHAR_HEADING r2_flame 90.0  
					
						IF NOT IS_CHAR_DEAD r2_car_mechanics[0] 	
							CLEAR_CHAR_TASKS_IMMEDIATELY r2_car_mechanics[0]
						ENDIF
						IF NOT IS_CHAR_DEAD r2_car_mechanics[1] 	
							CLEAR_CHAR_TASKS_IMMEDIATELY r2_car_mechanics[1]
						ENDIF
						IF NOT IS_CHAR_DEAD r2_car_mechanics[2] 	
							CLEAR_CHAR_TASKS_IMMEDIATELY r2_car_mechanics[2]
						ENDIF
						IF NOT IS_CHAR_DEAD r2_car_mechanics[3] 	
							CLEAR_CHAR_TASKS_IMMEDIATELY r2_car_mechanics[3]
						ENDIF
					ENDIF 
					
					//cesar
					SET_CHAR_STAY_IN_SAME_PLACE cesar TRUE
					OPEN_SEQUENCE_TASK r2_seq2
						TASK_PLAY_ANIM -1 gun_stand PED 8.0 FALSE FALSE FALSE FALSE 10
						GENERATE_RANDOM_INT_IN_RANGE 1500 4000 r2_random_number
						TASK_SHOOT_AT_COORD -1 1871.1 -2102.3 14.6 r2_random_number
						TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
						GENERATE_RANDOM_INT_IN_RANGE 1500 4000 r2_random_number
						TASK_PAUSE -1 r2_random_number 
						SET_SEQUENCE_TO_REPEAT r2_seq2 1
					CLOSE_SEQUENCE_TASK r2_seq2
					
					OPEN_SEQUENCE_TASK r2_seq
						TASK_GO_STRAIGHT_TO_COORD -1 1898.7 -2102.1 12.5 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 90.0
						PERFORM_SEQUENCE_TASK -1 r2_seq2
					CLOSE_SEQUENCE_TASK r2_seq
					PERFORM_SEQUENCE_TASK cesar r2_seq	
					CLEAR_SEQUENCE_TASK r2_seq
					CLEAR_SEQUENCE_TASK r2_seq2
					
					//launcher
					CLEAR_CHAR_TASKS_IMMEDIATELY r2_launcher
					SET_CHAR_COORDINATES r2_launcher 1912.4 -2103.1 12.5
					SET_CHAR_HEADING r2_launcher 90.0  
					SET_CHAR_STAY_IN_SAME_PLACE r2_launcher TRUE
					OPEN_SEQUENCE_TASK r2_seq2
						TASK_PLAY_ANIM -1 gun_stand PED 8.0 FALSE FALSE FALSE FALSE 10
						GENERATE_RANDOM_INT_IN_RANGE 1500 4000 r2_random_number
						TASK_SHOOT_AT_COORD -1 1878.7 -2105.5 14.6 r2_random_number
						TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
						GENERATE_RANDOM_INT_IN_RANGE 1500 4000 r2_random_number
						TASK_PAUSE -1 r2_random_number 
						SET_SEQUENCE_TO_REPEAT r2_seq2 1
					CLOSE_SEQUENCE_TASK r2_seq2
					
					OPEN_SEQUENCE_TASK r2_seq
						TASK_GO_STRAIGHT_TO_COORD -1 1898.7 -2106.1 12.5 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 90.0
						IF NOT IS_CAR_DEAD r2_junk[1]  
							TASK_DESTROY_CAR -1 r2_junk[1] 
						ENDIF
						TASK_PAUSE -1 500
						TASK_PLAY_ANIM -1 manwinb CASINO 8.0 FALSE FALSE FALSE FALSE -1
						PERFORM_SEQUENCE_TASK -1 r2_seq2
					CLOSE_SEQUENCE_TASK r2_seq
					PERFORM_SEQUENCE_TASK r2_launcher r2_seq	
					CLEAR_SEQUENCE_TASK r2_seq
					CLEAR_SEQUENCE_TASK r2_seq2

					//flame guy - guy with two guns
					SET_CHAR_STAY_IN_SAME_PLACE r2_flame TRUE
					OPEN_SEQUENCE_TASK r2_seq2
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_R SWAT 8.0 FALSE FALSE FALSE FALSE -1
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_in_R SWAT 8.0 FALSE TRUE TRUE FALSE -1
						TASK_STAY_IN_SAME_PLACE -1 TRUE
						GENERATE_RANDOM_INT_IN_RANGE 1500 4000 r2_random_number
						TASK_SHOOT_AT_COORD -1 1891.2 -2099.6 14.0 r2_random_number
						TASK_STAY_IN_SAME_PLACE -1 FALSE
						//TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_out_R SWAT 8.0 FALSE TRUE TRUE FALSE -1
						TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM -1 1903.7 -2096.6 12.5 75.0 -1.0 swt_wllshoot_out_R SWAT 8.0 FALSE TRUE TRUE FALSE -1 
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_R_back SWAT 8.0 FALSE FALSE FALSE FALSE -1
						SET_SEQUENCE_TO_REPEAT r2_seq2 1
					CLOSE_SEQUENCE_TASK r2_seq2
				
					OPEN_SEQUENCE_TASK r2_seq
						TASK_GO_STRAIGHT_TO_COORD -1 1903.5 -2096.6 12.5 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 75.0
						PERFORM_SEQUENCE_TASK -1 r2_seq2
					CLOSE_SEQUENCE_TASK r2_seq
					PERFORM_SEQUENCE_TASK r2_flame r2_seq	
					CLEAR_SEQUENCE_TASK r2_seq
					CLEAR_SEQUENCE_TASK r2_seq2
					
					//katana guy 
					OPEN_SEQUENCE_TASK r2_seq
						TASK_GO_STRAIGHT_TO_COORD -1 1894.6 -2099.2 12.4 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 90.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 Crouch_Roll_L PED 8.0 FALSE TRUE TRUE FALSE -1
						TASK_GO_STRAIGHT_TO_COORD -1 1884.6 -2107.6 12.4 PEDMOVE_RUN -1
						TASK_GO_STRAIGHT_TO_COORD -1 1874.9 -2102.8 12.4 PEDMOVE_RUN -1
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 KO_spin_L PED 8.0 FALSE TRUE TRUE FALSE -1
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 crckidle4 CRACK 8.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK r2_seq
					PERFORM_SEQUENCE_TASK r2_katana r2_seq	
					CLEAR_SEQUENCE_TASK r2_seq

					//guy underneath car
					IF NOT IS_CHAR_DEAD r2_car_mechanics[0]
						SET_CHAR_STAY_IN_SAME_PLACE r2_car_mechanics[0] TRUE
						OPEN_SEQUENCE_TASK r2_seq2
							TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_L SWAT 8.0 FALSE FALSE FALSE FALSE -1
							TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_in_L SWAT 8.0 FALSE TRUE TRUE FALSE -1
							TASK_STAY_IN_SAME_PLACE -1 TRUE
							GENERATE_RANDOM_INT_IN_RANGE 1500 4000 r2_random_number
							TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer r2_random_number
							TASK_STAY_IN_SAME_PLACE -1 FALSE
							TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM -1 1872.8 -2096.1 12.5 275.0 -1.0 swt_wllshoot_out_L SWAT 8.0 FALSE TRUE TRUE FALSE -1
							TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_L_back SWAT 8.0 FALSE FALSE FALSE FALSE -1
							SET_SEQUENCE_TO_REPEAT r2_seq2 1
						CLOSE_SEQUENCE_TASK r2_seq2
					
						OPEN_SEQUENCE_TASK r2_seq
							TASK_GO_STRAIGHT_TO_COORD -1 1872.8 -2096.1 12.5 PEDMOVE_RUN -1
							TASK_ACHIEVE_HEADING -1 275.0
							PERFORM_SEQUENCE_TASK -1 r2_seq2
						CLOSE_SEQUENCE_TASK r2_seq
						PERFORM_SEQUENCE_TASK r2_car_mechanics[0] r2_seq	
						CLEAR_SEQUENCE_TASK r2_seq
						CLEAR_SEQUENCE_TASK r2_seq2
					ENDIF

					//guy watching guy under the car
					IF NOT IS_CHAR_DEAD r2_car_mechanics[1] 
						TASK_KILL_CHAR_ON_FOOT r2_car_mechanics[1] scplayer 
					ENDIF

					//guy ducking behind car (right as you look towards player)
					IF NOT IS_CHAR_DEAD r2_car_mechanics[2] 
						SET_CHAR_STAY_IN_SAME_PLACE r2_car_mechanics[2] TRUE
						OPEN_SEQUENCE_TASK r2_seq2
							TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_R SWAT 8.0 FALSE FALSE FALSE FALSE -1
							TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_in_R SWAT 8.0 FALSE TRUE TRUE FALSE -1
							TASK_STAY_IN_SAME_PLACE -1 TRUE
							GENERATE_RANDOM_INT_IN_RANGE 1500 4000 r2_random_number
							TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer r2_random_number
							TASK_STAY_IN_SAME_PLACE -1 FALSE
							TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM -1 1864.1 -2107.4 12.6 275.0 -0.1 swt_wllshoot_out_R SWAT 8.0 FALSE TRUE TRUE FALSE -1
							TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_R_back SWAT 8.0 FALSE FALSE FALSE FALSE -1
							SET_SEQUENCE_TO_REPEAT r2_seq2 1
						CLOSE_SEQUENCE_TASK r2_seq2
					
						OPEN_SEQUENCE_TASK r2_seq
							TASK_GO_STRAIGHT_TO_COORD -1 1864.1 -2107.4 12.6 PEDMOVE_RUN -1
							TASK_ACHIEVE_HEADING -1 275.0
							PERFORM_SEQUENCE_TASK -1 r2_seq2
						CLOSE_SEQUENCE_TASK r2_seq			   
						PERFORM_SEQUENCE_TASK r2_car_mechanics[2] r2_seq	
						CLEAR_SEQUENCE_TASK r2_seq
						CLEAR_SEQUENCE_TASK r2_seq2
					ENDIF

					//guy ducking behind car (left as you look towards player)
					IF NOT IS_CHAR_DEAD r2_car_mechanics[3] 
						TASK_KILL_CHAR_ON_FOOT r2_car_mechanics[3] scplayer 
					ENDIF

					//guards coming from house (right of the door as you look at it)
					GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
					CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1806.1 -2121.7 12.5 r2_car_mechanics[4]
					SET_CHAR_HEADING r2_car_mechanics[4] 357.8
					GIVE_WEAPON_TO_CHAR r2_car_mechanics[4] WEAPONTYPE_TEC9 3000 
					SET_CHAR_DECISION_MAKER r2_car_mechanics[4] r2_empty_ped_decision_maker
					OPEN_SEQUENCE_TASK r2_seq
						TASK_GO_STRAIGHT_TO_COORD -1 1837.5 -2102.3 12.5 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 275.0
						TASK_KILL_CHAR_ON_FOOT -1 scplayer
					CLOSE_SEQUENCE_TASK r2_seq			   
					PERFORM_SEQUENCE_TASK r2_car_mechanics[4] r2_seq	
					CLEAR_SEQUENCE_TASK r2_seq
					ADD_BLIP_FOR_CHAR r2_car_mechanics[4] r2_car_mechanics_blips[4]
					CHANGE_BLIP_SCALE r2_car_mechanics_blips[4] 1 					
					SET_CHAR_ACCURACY r2_car_mechanics[4] r2_char_accuracy

					//guards coming from house (left of the door as you look at it)
					GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
					CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1801.5 -2120.7 12.5 r2_car_mechanics[5]
					SET_CHAR_HEADING r2_car_mechanics[5] 357.8
					GIVE_WEAPON_TO_CHAR r2_car_mechanics[5] WEAPONTYPE_TEC9 3000 
					SET_CHAR_DECISION_MAKER r2_car_mechanics[5] r2_empty_ped_decision_maker
					OPEN_SEQUENCE_TASK r2_seq
						TASK_GO_STRAIGHT_TO_COORD -1 1833.8 -2097.8 12.5 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 275.0
						TASK_KILL_CHAR_ON_FOOT -1 scplayer
					CLOSE_SEQUENCE_TASK r2_seq			   
					PERFORM_SEQUENCE_TASK r2_car_mechanics[5] r2_seq	
					CLEAR_SEQUENCE_TASK r2_seq
					ADD_BLIP_FOR_CHAR r2_car_mechanics[5] r2_car_mechanics_blips[5]
					CHANGE_BLIP_SCALE r2_car_mechanics_blips[5] 1 					
					SET_CHAR_ACCURACY r2_car_mechanics[5] r2_char_accuracy
					ADD_ARMOUR_TO_CHAR r2_car_mechanics[5] 50

					//first guy will go to the wall and pop in and out
					GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
					CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1879.7 -2094.9 12.5  r2_garage[0]
					SET_CHAR_HEADING r2_garage[0] 180.0
					GIVE_WEAPON_TO_CHAR r2_garage[0] WEAPONTYPE_TEC9 3000 
					SET_CHAR_DECISION_MAKER r2_garage[0] r2_empty_ped_decision_maker
					SET_CHAR_STAY_IN_SAME_PLACE r2_garage[0] TRUE 
					SET_CHAR_ACCURACY r2_garage[0] r2_char_accuracy
					 
					//second guy 
					GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
					CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1878.7 -2094.9 12.5  r2_garage[1]
					SET_CHAR_HEADING r2_garage[1] 180.0
					GIVE_WEAPON_TO_CHAR r2_garage[1] WEAPONTYPE_TEC9 3000 
					SET_CHAR_DECISION_MAKER r2_garage[1] r2_empty_ped_decision_maker
					SET_CHAR_STAY_IN_SAME_PLACE r2_garage[1] TRUE 
					SET_CHAR_ACCURACY r2_garage[1] r2_char_accuracy
					ADD_ARMOUR_TO_CHAR r2_garage[1] 50

					//third guy will duck walk out
					GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
					CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1876.7 -2094.9 12.5  r2_garage[2]
					SET_CHAR_HEADING r2_garage[2] 180.0
					GIVE_WEAPON_TO_CHAR r2_garage[2] WEAPONTYPE_TEC9 3000 
					SET_CHAR_DECISION_MAKER r2_garage[2] r2_empty_ped_decision_maker
					SET_CHAR_STAY_IN_SAME_PLACE r2_garage[2] TRUE 
					SET_CHAR_ACCURACY r2_garage[2] r2_char_accuracy

					//fourth guy will duck but stay in same place
					GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
					CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1875.2 -2094.9 12.5  r2_garage[3]
					SET_CHAR_HEADING r2_garage[3] 180.0
					GIVE_WEAPON_TO_CHAR r2_garage[3] WEAPONTYPE_TEC9 3000 
					SET_CHAR_DECISION_MAKER r2_garage[3] r2_empty_ped_decision_maker
					SET_CHAR_PROOFS r2_garage[3] TRUE TRUE TRUE TRUE TRUE
					SET_CHAR_STAY_IN_SAME_PLACE r2_garage[3] TRUE 
					SET_CHAR_ACCURACY r2_garage[3] r2_char_accuracy
					ADD_ARMOUR_TO_CHAR r2_garage[3] 50

					REMOVE_BLIP r2_control_blip 
					ADD_BLIP_FOR_CHAR cesar r2_control_blip
					SET_BLIP_AS_FRIENDLY r2_control_blip TRUE

					SET_GUNSHOT_SENSE_RANGE_FOR_RIOT2 -1.0

					SHUT_ALL_CHARS_UP TRUE
					
					REMOVE_ANIMATION CAR
					SWITCH_WIDESCREEN OFF
					MAKE_PLAYER_GANG_REAPPEAR
					HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
					SET_PLAYER_CONTROL player1 ON
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT
					PRINT_NOW ( R2_05 ) 7000 1 //Stay close to Cesar and clear out the alleyway. 

					//if cutscene has been skipped
					IF r2_skip_cutscene_flag = 1
						DO_FADE 500 FADE_IN	
						WHILE GET_FADING_STATUS
						    WAIT 0
						ENDWHILE 
						GOSUB r2_death_checks
						IF r2_deathcheck_flag = 1
							GOTO mission_riot2_failed
						ENDIF
					ENDIF

					r2_control_flag = 0
					r2_goals = 4

				ENDIF
			ENDIF															 
		ENDIF
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// Waiting for all the baddies to die from - STAGE 1 /////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	IF r2_goals = 4
		//deathchecks for the guys peeking
		IF NOT IS_CHAR_DEAD r2_car_mechanics[0]
			IF IS_CHAR_IN_AREA_2D scplayer 1877.6 -2050.8 1815.3 -2171.4 FALSE
			OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR r2_car_mechanics[0] scplayer
				GET_SCRIPT_TASK_STATUS r2_car_mechanics[0] TASK_KILL_CHAR_ON_FOOT task_status	
				IF task_status = FINISHED_TASK	
					TASK_KILL_CHAR_ON_FOOT r2_car_mechanics[0] scplayer
				ENDIF
			ENDIF
		ENDIF
		
		IF NOT IS_CHAR_DEAD r2_car_mechanics[2]
			IF IS_CHAR_IN_AREA_2D scplayer 1872.6 -2050.8 1815.3 -2171.4 FALSE
			OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR r2_car_mechanics[2] scplayer
				GET_SCRIPT_TASK_STATUS r2_car_mechanics[2] TASK_KILL_CHAR_ON_FOOT task_status	
				IF task_status = FINISHED_TASK	
					TASK_KILL_CHAR_ON_FOOT r2_car_mechanics[2] scplayer
				ENDIF
			ENDIF
		ENDIF	 	
		
		IF NOT IS_CHAR_DEAD cesar
			GET_CHAR_HEALTH cesar r2_health_check
			IF r2_health_check < 50
				TASK_DIE_NAMED_ANIM cesar KO_SHOT_STOM PED 8.0 FALSE
			ENDIF
		ENDIF 
		
		IF NOT IS_CHAR_DEAD r2_flame
			GET_CHAR_HEALTH r2_flame r2_health_check
			IF r2_health_check < 50
				TASK_DIE_NAMED_ANIM r2_flame KO_SHOT_STOM PED 8.0 FALSE
			ENDIF
		ENDIF 


		// MAIN BIT
		IF r2_control_flag = 0
			GET_SCRIPT_TASK_STATUS r2_katana PERFORM_SEQUENCE_TASK task_status
			IF NOT task_status = FINISHED_TASK 
				GET_SEQUENCE_PROGRESS r2_katana r2_seq_progress
				IF r2_seq_progress >= 3
					OPEN_GARAGE duf_las
					r2_control_flag = 1
				ENDIF
			ENDIF
		ENDIF
		IF r2_control_flag = 1 
			GET_SCRIPT_TASK_STATUS r2_katana PERFORM_SEQUENCE_TASK task_status
			IF NOT task_status = FINISHED_TASK 
				GET_SEQUENCE_PROGRESS r2_katana r2_seq_progress
				IF r2_seq_progress >= 5
					//first guy will kill player 
					IF NOT IS_CHAR_DEAD r2_garage[0] 
						OPEN_SEQUENCE_TASK r2_seq2
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK r2_seq2
						
						OPEN_SEQUENCE_TASK r2_seq
							TASK_KILL_CHAR_ON_FOOT_TIMED -1 r2_katana 3000   
							TASK_GO_STRAIGHT_TO_COORD -1 1872.8 -2096.1 12.5 PEDMOVE_RUN -1
							TASK_ACHIEVE_HEADING -1 275.0
							PERFORM_SEQUENCE_TASK -1 r2_seq2
						CLOSE_SEQUENCE_TASK r2_seq			   
						PERFORM_SEQUENCE_TASK r2_garage[0] r2_seq	
						CLEAR_SEQUENCE_TASK r2_seq
						CLEAR_SEQUENCE_TASK r2_seq2
						ADD_BLIP_FOR_CHAR r2_garage[0] r2_garage_blips[0]
						CHANGE_BLIP_SCALE r2_garage_blips[0] 1 					
					ENDIF

					//second guy
					IF NOT IS_CHAR_DEAD r2_garage[1] 
						SET_CHAR_STAY_IN_SAME_PLACE r2_garage[1] FALSE 
						OPEN_SEQUENCE_TASK r2_seq
							TASK_KILL_CHAR_ON_FOOT_TIMED -1 r2_katana 3000   
							TASK_GO_STRAIGHT_TO_COORD -1 1872.8 -2096.1 12.5 PEDMOVE_RUN -1
							TASK_ACHIEVE_HEADING -1 275.0
							TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 Crouch_Roll_R PED 8.0 FALSE TRUE TRUE FALSE -1
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK r2_seq			   
						PERFORM_SEQUENCE_TASK r2_garage[1] r2_seq	
						CLEAR_SEQUENCE_TASK r2_seq
						ADD_BLIP_FOR_CHAR r2_garage[1] r2_garage_blips[1]
						CHANGE_BLIP_SCALE r2_garage_blips[1] 1 					
					ENDIF

					//third guy will duck walk out
					IF NOT IS_CHAR_DEAD r2_garage[2] 
						SET_CHAR_STAY_IN_SAME_PLACE r2_garage[2] FALSE 
						OPEN_SEQUENCE_TASK r2_seq
							TASK_KILL_CHAR_ON_FOOT_TIMED -1 r2_katana 3000   
							TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
							TASK_TOGGLE_DUCK -1 TRUE
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK r2_seq			   
						PERFORM_SEQUENCE_TASK r2_garage[2] r2_seq	
						CLEAR_SEQUENCE_TASK r2_seq
						SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_garage[2] TRUE 
						ADD_BLIP_FOR_CHAR r2_garage[2] r2_garage_blips[2]
						CHANGE_BLIP_SCALE r2_garage_blips[2] 1 					
					ENDIF

					//fourth guy will duck but stay in same place
					IF NOT IS_CHAR_DEAD r2_garage[3] 
						SET_CHAR_STAY_IN_SAME_PLACE r2_garage[3] FALSE 
						SET_CHAR_PROOFS r2_garage[3] FALSE FALSE FALSE FALSE FALSE
						OPEN_SEQUENCE_TASK r2_seq
							TASK_KILL_CHAR_ON_FOOT_TIMED -1 r2_katana 3000   
							TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
							TASK_TOGGLE_DUCK -1 TRUE
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK r2_seq			   
						PERFORM_SEQUENCE_TASK r2_garage[3] r2_seq	
						CLEAR_SEQUENCE_TASK r2_seq
						SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_garage[3] TRUE 
						ADD_BLIP_FOR_CHAR r2_garage[3] r2_garage_blips[3]
						CHANGE_BLIP_SCALE r2_garage_blips[3] 1 					
					ENDIF
				
					CLEAR_PRINTS
					//Hazer!
					r2_speech_goals = 7
					r2_speech_control_flag = 0
					GOSUB r2_dialogue_setup 

					 
					SET_CHAR_BLEEDING r2_katana TRUE
					timerb = 0
					r2_control_flag = 2
				ENDIF
			ENDIF
		ENDIF

		IF r2_control_flag = 2 
			IF timerb > 1000
				SET_CHAR_COLLISION r2_katana FALSE
				r2_control_flag = 3
			ENDIF
		ENDIF

		IF r2_control_flag = 3
			IF IS_CHAR_DEAD r2_car_mechanics[0] 
				IF IS_CHAR_DEAD r2_car_mechanics[1]
					IF IS_CHAR_DEAD r2_car_mechanics[2]
						IF IS_CHAR_DEAD r2_car_mechanics[3]
							IF IS_CHAR_DEAD r2_car_mechanics[4]
								IF IS_CHAR_DEAD r2_car_mechanics[5]
									IF IS_CHAR_DEAD r2_garage[0] 
										IF IS_CHAR_DEAD r2_garage[1] 
											IF IS_CHAR_DEAD r2_garage[2] 
												IF IS_CHAR_DEAD r2_garage[3] 
													REMOVE_BLIP r2_car_mechanics_blips[0] 
													REMOVE_BLIP r2_car_mechanics_blips[1] 
													REMOVE_BLIP r2_car_mechanics_blips[2] 
													REMOVE_BLIP r2_car_mechanics_blips[3] 
													REMOVE_BLIP r2_car_mechanics_blips[4] 
													REMOVE_BLIP r2_car_mechanics_blips[5] 
													REMOVE_BLIP r2_garage_blips[0]
													REMOVE_BLIP r2_garage_blips[1]
													REMOVE_BLIP r2_garage_blips[2]
													REMOVE_BLIP r2_garage_blips[3]
													MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[0] 
													MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[1] 
													MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[2] 
													MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[3] 
													MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[4] 
													MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[5] 
													MARK_CHAR_AS_NO_LONGER_NEEDED r2_garage[0]
													MARK_CHAR_AS_NO_LONGER_NEEDED r2_garage[1]
													MARK_CHAR_AS_NO_LONGER_NEEDED r2_garage[2]
													MARK_CHAR_AS_NO_LONGER_NEEDED r2_garage[3]
													timera = 0
													r2_control_flag = 0
													r2_goals = 5
												ENDIF
											ENDIF
										ENDIF
									ENDIF 
								ENDIF
							ENDIF
			 			ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	
		//removing blips//
		IF IS_CHAR_DEAD r2_car_mechanics[0] 
			MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[0]
			REMOVE_BLIP r2_car_mechanics_blips[0]
		ENDIF  
		IF IS_CHAR_DEAD r2_car_mechanics[1] 
			MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[1]
			REMOVE_BLIP r2_car_mechanics_blips[1]
		ENDIF  
		IF IS_CHAR_DEAD r2_car_mechanics[2] 
			MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[2]
			REMOVE_BLIP r2_car_mechanics_blips[2]
		ENDIF  
		IF IS_CHAR_DEAD r2_car_mechanics[3] 
			MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[3]
			REMOVE_BLIP r2_car_mechanics_blips[3]
		ENDIF  
		IF IS_CHAR_DEAD r2_car_mechanics[4] 
			MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[4]
			REMOVE_BLIP r2_car_mechanics_blips[4]
		ENDIF  
		IF IS_CHAR_DEAD r2_car_mechanics[5] 
			MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[5]
			REMOVE_BLIP r2_car_mechanics_blips[5]
		ENDIF  
		IF r2_control_flag > 1
			IF IS_CHAR_DEAD r2_garage[0] 
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_garage[0]
				REMOVE_BLIP r2_garage_blips[0]
			ENDIF  
			IF IS_CHAR_DEAD r2_garage[1] 
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_garage[1]
				REMOVE_BLIP r2_garage_blips[1]
			ENDIF  
			IF IS_CHAR_DEAD r2_garage[2] 
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_garage[2]
				REMOVE_BLIP r2_garage_blips[2]
			ENDIF  
			IF IS_CHAR_DEAD r2_garage[3] 
				MARK_CHAR_AS_NO_LONGER_NEEDED r2_garage[3]
				REMOVE_BLIP r2_garage_blips[3]
			ENDIF 
		ENDIF 
	ENDIF 

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// Starting Cutscene - STAGE 2 ///////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	IF r2_goals = 5
		IF r2_control_flag = 0
			IF timera > 2000
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				r2_speech_goals = 0
	
				SET_PLAYER_CONTROL player1 OFF 
				SWITCH_WIDESCREEN ON
				MAKE_PLAYER_GANG_DISAPPEAR
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			
				//deleting blown up car
				DELETE_CAR r2_junk[1]
				
				CLEAR_AREA 1905.1 -2103.9 12.5 60.0 TRUE
				
				SET_CHAR_COORDINATES scplayer 1905.1 -2103.9 12.5   
				SET_CHAR_HEADING scplayer 80.2 
			
				//cesar
				CLEAR_CHAR_TASKS cesar
				OPEN_SEQUENCE_TASK r2_seq
					TASK_GO_STRAIGHT_TO_COORD -1 1897.8 -2099.5 12.5 PEDMOVE_RUN -1
					TASK_GO_STRAIGHT_TO_COORD -1 1890.8 -2099.5 12.5 PEDMOVE_RUN -1
					TASK_GO_STRAIGHT_TO_COORD -1 1877.2 -2101.2 12.5 PEDMOVE_RUN -1
					TASK_ACHIEVE_HEADING -1 110.0
				CLOSE_SEQUENCE_TASK r2_seq
				PERFORM_SEQUENCE_TASK cesar r2_seq	
				CLEAR_SEQUENCE_TASK r2_seq

				//flame
				CLEAR_CHAR_TASKS_IMMEDIATELY r2_flame 
				SET_CHAR_COORDINATES r2_flame 1900.4 -2098.2 12.5   
				SET_CHAR_HEADING r2_flame 92.3
				OPEN_SEQUENCE_TASK r2_seq
					TASK_GO_STRAIGHT_TO_COORD -1 1875.7 -2102.4 12.5 PEDMOVE_RUN -1
					TASK_ACHIEVE_HEADING -1 95.0
					TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
				CLOSE_SEQUENCE_TASK r2_seq
				PERFORM_SEQUENCE_TASK r2_flame r2_seq										   
				CLEAR_SEQUENCE_TASK r2_seq

				r2_skip_cutscene_flag = 1
				SKIP_CUTSCENE_START
				
				SET_FIXED_CAMERA_POSITION 1900.4 -2099.6 12.8 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1879.4 -2107.7 17.9 JUMP_CUT 
				timera = 0
				r2_control_flag = 1
			ENDIF
		ENDIF

		IF r2_control_flag = 1
			IF timera > 3000
				CLEAR_CHAR_TASKS_IMMEDIATELY r2_flame 
				SET_CHAR_COORDINATES r2_flame 1875.7 -2102.4 12.5   
				SET_CHAR_HEADING r2_flame 95.0
				TASK_PLAY_ANIM r2_flame weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
			
				SET_CHAR_COORDINATES cesar 1881.2 -2099.6 12.5   
				SET_CHAR_HEADING cesar 69.4

				SET_CHAR_COORDINATES scplayer 1886.2 -2099.6 12.5   
				SET_CHAR_HEADING scplayer 69.4
				TASK_GO_STRAIGHT_TO_COORD scplayer 1877.6 -2099.8 12.5 PEDMOVE_WALK -1

				GOSUB r2_creating_climbers

				SET_FIXED_CAMERA_POSITION 1880.2 -2103.1 14.46 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1874.3 -2100.77 12.80 JUMP_CUT 
			
				// Shit, Hazer...
				r2_speech_goals = 8
				r2_speech_control_flag = 0
				GOSUB r2_dialogue_setup 

				timera = 0 
				r2_control_flag = 2
			ENDIF
		ENDIF
			
		IF r2_control_flag = 2
			IF r2_speech_goals = 0
				IF timera > 8000
					r2_skip_cutscene_flag = 0
					SKIP_CUTSCENE_END
					GOSUB r2_death_checks
					IF r2_deathcheck_flag = 1
						GOTO mission_riot2_failed
					ENDIF

					//if cutscene has been skipped
					IF r2_skip_cutscene_flag = 1
						CLEAR_PRINTS
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						r2_speech_goals = 0

						DO_FADE 500 FADE_OUT	
						WHILE GET_FADING_STATUS
						    WAIT 0
						ENDWHILE 
						GOSUB r2_death_checks
						IF r2_deathcheck_flag = 1
							GOTO mission_riot2_failed
						ENDIF

						GOSUB r2_creating_climbers
					
						CLEAR_CHAR_TASKS_IMMEDIATELY r2_flame 
						SET_CHAR_COORDINATES r2_flame 1875.7 -2102.4 12.5   
						SET_CHAR_HEADING r2_flame 95.0
						TASK_PLAY_ANIM r2_flame weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
					
						CLEAR_CHAR_TASKS_IMMEDIATELY cesar  
						SET_CHAR_COORDINATES cesar 1881.2 -2099.6 12.5   
						SET_CHAR_HEADING cesar 69.4
					
						CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
						SET_CHAR_COORDINATES scplayer 1879.7 -2101.7 12.5
						SET_CHAR_HEADING scplayer 90.0

						CLEAR_CHAR_TASKS_IMMEDIATELY r2_climbers[0]
						SET_CHAR_DECISION_MAKER r2_climbers[0] r2_ped_decisions
						SET_CHAR_COORDINATES r2_climbers[0] 1861.1 -2096.9 12.5
						SET_CHAR_HEADING r2_climbers[0] 270.3    

						GET_SCRIPT_TASK_STATUS r2_climbers[1] PERFORM_SEQUENCE_TASK task_status
						IF NOT task_status = FINISHED_TASK
							CLEAR_CHAR_TASKS_IMMEDIATELY r2_climbers[1] 
							TASK_PLAY_ANIM r2_climbers[1] weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
							OPEN_SEQUENCE_TASK r2_seq
								TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
								TASK_CLIMB -1 TRUE
								TASK_SHOOT_AT_COORD -1 1874.4 -2101.5 14.8 2000
								TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions
							CLOSE_SEQUENCE_TASK r2_seq			   
							PERFORM_SEQUENCE_TASK r2_climbers[1] r2_seq	
							CLEAR_SEQUENCE_TASK r2_seq
						ENDIF
		
						GET_SCRIPT_TASK_STATUS r2_climbers[2] PERFORM_SEQUENCE_TASK task_status
						IF NOT task_status = FINISHED_TASK
							CLEAR_CHAR_TASKS_IMMEDIATELY r2_climbers[2] 
							TASK_PLAY_ANIM r2_climbers[2] weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
							OPEN_SEQUENCE_TASK r2_seq
								TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
								TASK_PAUSE -1 2000
								TASK_CLIMB -1 TRUE
								TASK_SHOOT_AT_COORD -1 1874.4 -2101.5 14.8 2000
								TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions
							CLOSE_SEQUENCE_TASK r2_seq			   
							PERFORM_SEQUENCE_TASK r2_climbers[2] r2_seq	
							CLEAR_SEQUENCE_TASK r2_seq
						ENDIF
						
						CLEAR_CHAR_TASKS_IMMEDIATELY r2_climbers[3]
						SET_CHAR_DECISION_MAKER r2_climbers[3] r2_ped_decisions
						SET_CHAR_COORDINATES r2_climbers[3] 1859.1 -2106.0 14.0
						SET_CHAR_HEADING r2_climbers[3] 264.4   
						
						GET_SCRIPT_TASK_STATUS r2_climbers[4] PERFORM_SEQUENCE_TASK task_status
						IF NOT task_status = FINISHED_TASK
							CLEAR_CHAR_TASKS_IMMEDIATELY r2_climbers[4] 
							TASK_PLAY_ANIM r2_climbers[4] weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
							OPEN_SEQUENCE_TASK r2_seq
								TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
								TASK_CLIMB -1 TRUE
								TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions
							CLOSE_SEQUENCE_TASK r2_seq			   
							PERFORM_SEQUENCE_TASK r2_climbers[4] r2_seq	
							CLEAR_SEQUENCE_TASK r2_seq
						ENDIF  

						GET_SCRIPT_TASK_STATUS r2_climbers[5] PERFORM_SEQUENCE_TASK task_status
						IF NOT task_status = FINISHED_TASK
							CLEAR_CHAR_TASKS_IMMEDIATELY r2_climbers[5]
							TASK_PLAY_ANIM r2_climbers[5] weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
							OPEN_SEQUENCE_TASK r2_seq
								TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
								TASK_PAUSE -1 3000
								TASK_CLIMB -1 TRUE
								TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions
							CLOSE_SEQUENCE_TASK r2_seq			   
							PERFORM_SEQUENCE_TASK r2_climbers[5] r2_seq	
							CLEAR_SEQUENCE_TASK r2_seq
						ENDIF
					ENDIF	
					
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE r2_ped_decisions EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 FALSE TRUE 	
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE r2_ped_decisions EVENT_ACQUAINTANCE_PED_DISLIKE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 FALSE TRUE 	

					CREATE_PICKUP health PICKUP_ON_STREET_SLOW 1875.6 -2091.4 13.5 r2_health //debug - move inside garage
					CREATE_PICKUP_WITH_AMMO TEC9 PICKUP_ON_STREET_SLOW 48 1878.3 -2091.1 13.5 r2_gun //debug - move inside garage

					//cesar
					OPEN_SEQUENCE_TASK r2_seq2
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_R SWAT 8.0 FALSE FALSE FALSE FALSE -1
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_in_R SWAT 8.0 FALSE TRUE TRUE FALSE -1
						TASK_STAY_IN_SAME_PLACE -1 TRUE
						GENERATE_RANDOM_INT_IN_RANGE 1500 4000 r2_random_number
						TASK_SHOOT_AT_COORD -1 1864.9 -2101.2 14.0 r2_random_number
						TASK_STAY_IN_SAME_PLACE -1 FALSE
						TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM -1 1874.9 -2096.5 12.5 75.0 -0.1 swt_wllshoot_out_R SWAT 8.0 FALSE TRUE TRUE FALSE -1
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_R_back SWAT 8.0 FALSE FALSE FALSE FALSE -1
						SET_SEQUENCE_TO_REPEAT r2_seq2 1
					CLOSE_SEQUENCE_TASK r2_seq2
				
					OPEN_SEQUENCE_TASK r2_seq
						TASK_GO_STRAIGHT_TO_COORD -1 1874.9 -2096.5 12.5 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 75.0
						PERFORM_SEQUENCE_TASK -1 r2_seq2
					CLOSE_SEQUENCE_TASK r2_seq
					PERFORM_SEQUENCE_TASK cesar r2_seq	
					CLEAR_SEQUENCE_TASK r2_seq
					CLEAR_SEQUENCE_TASK r2_seq2
					
					//two guns 
					OPEN_SEQUENCE_TASK r2_seq2
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_L SWAT 8.0 FALSE FALSE FALSE FALSE -1
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_in_L SWAT 8.0 FALSE TRUE TRUE FALSE -1
						TASK_STAY_IN_SAME_PLACE -1 TRUE
						GENERATE_RANDOM_INT_IN_RANGE 1500 4000 r2_random_number
						TASK_SHOOT_AT_COORD -1 1862.6 -2104.1 14.0 r2_random_number
						TASK_STAY_IN_SAME_PLACE -1 FALSE
						TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM -1 1873.3 -2107.4 12.5 98.8 -0.1 swt_wllshoot_out_L SWAT 8.0 FALSE TRUE TRUE FALSE -1
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_L_back SWAT 8.0 FALSE FALSE FALSE FALSE -1
						SET_SEQUENCE_TO_REPEAT r2_seq2 1
					CLOSE_SEQUENCE_TASK r2_seq2
				
					OPEN_SEQUENCE_TASK r2_seq
						TASK_GO_STRAIGHT_TO_COORD -1 1873.3 -2107.4 12.5 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 98.8
						PERFORM_SEQUENCE_TASK -1 r2_seq2
					CLOSE_SEQUENCE_TASK r2_seq
					PERFORM_SEQUENCE_TASK r2_flame r2_seq	
					CLEAR_SEQUENCE_TASK r2_seq
					CLEAR_SEQUENCE_TASK r2_seq2
					
					//launcher
					GIVE_WEAPON_TO_CHAR r2_launcher WEAPONTYPE_ROCKETLAUNCHER 1
					OPEN_SEQUENCE_TASK r2_seq2
						TASK_PLAY_ANIM -1 gun_stand PED 8.0 FALSE FALSE FALSE FALSE 10
						GENERATE_RANDOM_INT_IN_RANGE 1500 4000 r2_random_number
						TASK_SHOOT_AT_COORD -1 1878.7 -2105.5 14.6 r2_random_number
						TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
						GENERATE_RANDOM_INT_IN_RANGE 1500 4000 r2_random_number
						TASK_PAUSE -1 r2_random_number 
						SET_SEQUENCE_TO_REPEAT r2_seq2 1
					CLOSE_SEQUENCE_TASK r2_seq2
					
					OPEN_SEQUENCE_TASK r2_seq
						TASK_GO_STRAIGHT_TO_COORD -1 1898.7 -2106.1 12.5 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 90.0
						TASK_SHOOT_AT_COORD -1 1852.1 -2096.6 16.5 10
						PERFORM_SEQUENCE_TASK -1 r2_seq2
					CLOSE_SEQUENCE_TASK r2_seq
					PERFORM_SEQUENCE_TASK r2_launcher r2_seq	
					CLEAR_SEQUENCE_TASK r2_seq
					CLEAR_SEQUENCE_TASK r2_seq2

					//two guys that kill Launcher dude
					GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
					CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1913.7 -2109.2 12.6 r2_car_mechanics[0]
					SET_CHAR_HEADING r2_car_mechanics[0] 90.5
					GIVE_WEAPON_TO_CHAR r2_car_mechanics[0] WEAPONTYPE_TEC9 3000 
					SET_CHAR_DECISION_MAKER r2_car_mechanics[0] r2_empty_ped_decision_maker
					SET_CHAR_STAY_IN_SAME_PLACE r2_car_mechanics[0] TRUE
					SET_CHAR_PROOFS r2_car_mechanics[0] TRUE TRUE TRUE TRUE TRUE 
					SET_CHAR_ACCURACY r2_car_mechanics[0] r2_char_accuracy
					ADD_ARMOUR_TO_CHAR r2_car_mechanics[0] 50
					
					GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
					CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1916.5 -2113.1 12.6 r2_car_mechanics[1]
					SET_CHAR_HEADING r2_car_mechanics[1] 20.1
					GIVE_WEAPON_TO_CHAR r2_car_mechanics[1] WEAPONTYPE_TEC9 3000 
					SET_CHAR_DECISION_MAKER r2_car_mechanics[1] r2_empty_ped_decision_maker
					SET_CHAR_STAY_IN_SAME_PLACE r2_car_mechanics[1] TRUE 
					SET_CHAR_PROOFS r2_car_mechanics[1] TRUE TRUE TRUE TRUE TRUE 
					SET_CHAR_ACCURACY r2_car_mechanics[1] r2_char_accuracy

					//guards coming from house (right of the door as you look at it)
					GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
					CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1806.1 -2121.7 12.5 r2_car_mechanics[4]
					SET_CHAR_HEADING r2_car_mechanics[4] 357.8
					GIVE_WEAPON_TO_CHAR r2_car_mechanics[4] WEAPONTYPE_TEC9 3000 
					SET_CHAR_DECISION_MAKER r2_car_mechanics[4] r2_ped_decisions
					OPEN_SEQUENCE_TASK r2_seq
						TASK_GO_STRAIGHT_TO_COORD -1 1837.5 -2102.3 12.5 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 275.0
						TASK_KILL_CHAR_ON_FOOT -1 scplayer
					CLOSE_SEQUENCE_TASK r2_seq			   
					PERFORM_SEQUENCE_TASK r2_car_mechanics[4] r2_seq	
					CLEAR_SEQUENCE_TASK r2_seq
					REMOVE_BLIP r2_car_mechanics_blips[4] 
					ADD_BLIP_FOR_CHAR r2_car_mechanics[4] r2_car_mechanics_blips[4]
					CHANGE_BLIP_SCALE r2_car_mechanics_blips[4] 1 					
					SET_CHAR_ACCURACY r2_car_mechanics[4] r2_char_accuracy

					//guards coming from house (left of the door as you look at it)
					GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
					CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1801.5 -2120.7 12.5 r2_car_mechanics[5]
					SET_CHAR_HEADING r2_car_mechanics[5] 357.8
					GIVE_WEAPON_TO_CHAR r2_car_mechanics[5] WEAPONTYPE_TEC9 3000 
					SET_CHAR_DECISION_MAKER r2_car_mechanics[5] r2_ped_decisions 
					OPEN_SEQUENCE_TASK r2_seq
						TASK_GO_STRAIGHT_TO_COORD -1 1833.8 -2097.8 12.5 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 275.0
						TASK_KILL_CHAR_ON_FOOT -1 scplayer
					CLOSE_SEQUENCE_TASK r2_seq			   
					PERFORM_SEQUENCE_TASK r2_car_mechanics[5] r2_seq	
					CLEAR_SEQUENCE_TASK r2_seq
					REMOVE_BLIP r2_car_mechanics_blips[5] 
					ADD_BLIP_FOR_CHAR r2_car_mechanics[5] r2_car_mechanics_blips[5]
					CHANGE_BLIP_SCALE r2_car_mechanics_blips[5] 1 					
					SET_CHAR_ACCURACY r2_car_mechanics[5] r2_char_accuracy
					ADD_ARMOUR_TO_CHAR r2_car_mechanics[5] 50
			
					SHUT_ALL_CHARS_UP FALSE
					
					SWITCH_WIDESCREEN OFF
					MAKE_PLAYER_GANG_REAPPEAR
					HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
					SET_PLAYER_CONTROL player1 ON
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT
					PRINT_NOW ( R2_06 ) 4000 1 //Defend Cesar.

					//if cutscene has been skipped
					IF r2_skip_cutscene_flag = 1
						DO_FADE 500 FADE_IN	
						WHILE GET_FADING_STATUS
						    WAIT 0
						ENDWHILE 
						GOSUB r2_death_checks
						IF r2_deathcheck_flag = 1
							GOTO mission_riot2_failed
						ENDIF
					ENDIF
					
					timera = 0
					r2_control_flag = 0
					r2_goals = 6
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	 



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// Waiting for all the baddies to die from - STAGE 2 /////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	IF r2_goals = 6 
		//deathchecks for the guys peeking
		IF NOT IS_CHAR_DEAD cesar
			GET_CHAR_HEALTH cesar r2_health_check
			IF r2_health_check < 50
				TASK_DIE_NAMED_ANIM cesar KO_SHOT_STOM PED 8.0 FALSE
			ENDIF
		ENDIF 
		
		IF NOT IS_CHAR_DEAD r2_flame
			GET_CHAR_HEALTH r2_flame r2_health_check
			IF r2_health_check < 50
				TASK_DIE_NAMED_ANIM r2_flame KO_SHOT_STOM PED 8.0 FALSE
			ENDIF
		ENDIF 

		//triggering the death of launcher
		IF r2_triggering_launcher_death = 0
			IF IS_CHAR_DEAD r2_climbers[0]
				IF IS_CHAR_DEAD r2_climbers[1]
					IF IS_CHAR_DEAD r2_climbers[2]
						IF IS_CHAR_DEAD r2_climbers[3]
							IF IS_CHAR_DEAD r2_climbers[4]
								IF IS_CHAR_DEAD r2_climbers[5]
									r2_triggering_launcher_death = 1
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		
		ENDIF 
			
		IF r2_triggering_launcher_death = 1
			IF timera > 15000
			OR r2_triggering_launcher_death = 1
			OR IS_CHAR_IN_AREA_2D scplayer 1893.6 -2043.3 1888.1 -2175.6 FALSE
				// Behind us!
				r2_speech_goals = 9
				GENERATE_RANDOM_INT_IN_RANGE 0 3 r2_speech_control_flag
				r2_random_last_label = r2_speech_control_flag + 1 
				GOSUB r2_dialogue_setup 
				
				IF NOT IS_CHAR_DEAD r2_car_mechanics[0] 
					REMOVE_BLIP r2_car_mechanics_blips[0] 
					ADD_BLIP_FOR_CHAR r2_car_mechanics[0] r2_car_mechanics_blips[0]
					CHANGE_BLIP_SCALE r2_car_mechanics_blips[0] 1
				ENDIF 					
				
				IF NOT IS_CHAR_DEAD r2_car_mechanics[1] 
					REMOVE_BLIP r2_car_mechanics_blips[1] 
					ADD_BLIP_FOR_CHAR r2_car_mechanics[1] r2_car_mechanics_blips[1]
					CHANGE_BLIP_SCALE r2_car_mechanics_blips[1] 1
				ENDIF
									
				r2_triggering_launcher_death = 2
			ENDIF
		ENDIF
			
		IF r2_triggering_launcher_death = 2 	
			IF IS_CHAR_ON_SCREEN r2_launcher 
				//two guys that kill Launcher dude
				IF NOT IS_CHAR_DEAD r2_car_mechanics[0]
					OPEN_SEQUENCE_TASK r2_seq
						TASK_GO_STRAIGHT_TO_COORD -1 1913.5 -2106.1 12.5 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 90.0
						TASK_SHOOT_AT_COORD -1 1900.8 -2106.2 12.6 1500
						TASK_KILL_CHAR_ON_FOOT -1 scplayer  
					CLOSE_SEQUENCE_TASK r2_seq
					PERFORM_SEQUENCE_TASK r2_car_mechanics[0] r2_seq	
					CLEAR_SEQUENCE_TASK r2_seq
					SET_CHAR_PROOFS r2_car_mechanics[0] FALSE FALSE FALSE FALSE FALSE 
				ENDIF
		
				IF NOT IS_CHAR_DEAD r2_car_mechanics[1]
					OPEN_SEQUENCE_TASK r2_seq
						TASK_GO_STRAIGHT_TO_COORD -1 1914.2 -2104.2 12.5 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 90.0
						TASK_SHOOT_AT_COORD -1 1900.8 -2106.2 12.6 1500
						TASK_KILL_CHAR_ON_FOOT -1 scplayer  
					CLOSE_SEQUENCE_TASK r2_seq
					PERFORM_SEQUENCE_TASK r2_car_mechanics[1] r2_seq	
					CLEAR_SEQUENCE_TASK r2_seq
					SET_CHAR_PROOFS r2_car_mechanics[1] FALSE FALSE FALSE FALSE FALSE 
				ENDIF
				
				//launcher dude
				GIVE_WEAPON_TO_CHAR r2_launcher WEAPONTYPE_ROCKETLAUNCHER 1
				OPEN_SEQUENCE_TASK r2_seq
					TASK_ACHIEVE_HEADING -1 269.0
					TASK_PAUSE -1 200 
					TASK_SHOOT_AT_COORD -1 1951.1 -2103.9 19.3 500 
					TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 KO_shot_face PED 8.0 FALSE TRUE TRUE FALSE -1
					TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 crckidle1 CRACK 1000.0 TRUE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK r2_seq
				PERFORM_SEQUENCE_TASK r2_launcher r2_seq	
				CLEAR_SEQUENCE_TASK r2_seq
				SET_CHAR_BLEEDING r2_launcher TRUE
					
				timera = 0
				r2_triggering_launcher_death = 3
			ENDIF
		ENDIF	  

		IF r2_control_flag = 0 
			IF IS_CHAR_DEAD r2_climbers[0]
				IF IS_CHAR_DEAD r2_climbers[1]
					IF IS_CHAR_DEAD r2_climbers[2]
						IF IS_CHAR_DEAD r2_climbers[3]
							IF IS_CHAR_DEAD r2_climbers[4]
								IF IS_CHAR_DEAD r2_climbers[5]
									IF IS_CHAR_DEAD r2_car_mechanics[4]
										IF IS_CHAR_DEAD r2_car_mechanics[5]
											OPEN_SEQUENCE_TASK r2_seq
												TASK_GO_STRAIGHT_TO_COORD -1 1895.3 -2102.2 12.6 PEDMOVE_RUN -1
												TASK_ACHIEVE_HEADING -1 274.7
												TASK_SHOOT_AT_COORD -1 1913.5 -2104.4 13.9 -2
											CLOSE_SEQUENCE_TASK r2_seq
											PERFORM_SEQUENCE_TASK cesar r2_seq	
											CLEAR_SEQUENCE_TASK r2_seq
																						
											OPEN_SEQUENCE_TASK r2_seq
												TASK_GO_STRAIGHT_TO_COORD -1 1895.6 -2104.2 12.5 PEDMOVE_RUN -1
												TASK_ACHIEVE_HEADING -1 271.0
												TASK_SHOOT_AT_COORD -1 1912.9 -2106.4 13.9 -2
											CLOSE_SEQUENCE_TASK r2_seq
											PERFORM_SEQUENCE_TASK r2_flame r2_seq	
											CLEAR_SEQUENCE_TASK r2_seq
											r2_control_flag = 1
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF 
		IF r2_control_flag = 1
			IF IS_CHAR_DEAD r2_car_mechanics[0]
				IF IS_CHAR_DEAD r2_car_mechanics[1]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[0] 
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[1] 
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[4] 
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[5] 
					REMOVE_ANIMATION CASINO
					timera = 0
					r2_control_flag = 0
					r2_goals = 7
				ENDIF
			ENDIF
		ENDIF

		//removing blips//
		IF IS_CHAR_DEAD r2_car_mechanics[0] 
			MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[0]
			REMOVE_BLIP r2_car_mechanics_blips[0]
		ENDIF  
		IF IS_CHAR_DEAD r2_car_mechanics[1] 
			MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[1]
			REMOVE_BLIP r2_car_mechanics_blips[1]
		ENDIF  
		IF IS_CHAR_DEAD r2_car_mechanics[4] 
			MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[4]
			REMOVE_BLIP r2_car_mechanics_blips[4]
		ENDIF  
		IF IS_CHAR_DEAD r2_car_mechanics[5] 
			MARK_CHAR_AS_NO_LONGER_NEEDED r2_car_mechanics[5]
			REMOVE_BLIP r2_car_mechanics_blips[5]
		ENDIF  
		IF IS_CHAR_DEAD r2_climbers[0] 
			MARK_CHAR_AS_NO_LONGER_NEEDED r2_climbers[0]
			REMOVE_BLIP r2_climbers_blips[0]
		ENDIF  
		IF IS_CHAR_DEAD r2_climbers[1] 
			MARK_CHAR_AS_NO_LONGER_NEEDED r2_climbers[1]
			REMOVE_BLIP r2_climbers_blips[1]
		ENDIF  
		IF IS_CHAR_DEAD r2_climbers[2] 
			MARK_CHAR_AS_NO_LONGER_NEEDED r2_climbers[2]
			REMOVE_BLIP r2_climbers_blips[2]
		ENDIF  
		IF IS_CHAR_DEAD r2_climbers[3] 
			MARK_CHAR_AS_NO_LONGER_NEEDED r2_climbers[3]
			REMOVE_BLIP r2_climbers_blips[3]
		ENDIF  
		IF IS_CHAR_DEAD r2_climbers[4] 
			MARK_CHAR_AS_NO_LONGER_NEEDED r2_climbers[4]
			REMOVE_BLIP r2_climbers_blips[4]
		ENDIF  
		IF IS_CHAR_DEAD r2_climbers[5] 
			MARK_CHAR_AS_NO_LONGER_NEEDED r2_climbers[5]
			REMOVE_BLIP r2_climbers_blips[5]
		ENDIF  
	ENDIF

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// Starting Cutscene - STAGE 3 ///////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	IF r2_goals = 7
		IF r2_control_flag = 0 
			IF timera > 2000
			
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE
				GOSUB r2_death_checks
				IF r2_deathcheck_flag = 1
					GOTO mission_riot2_failed
				ENDIF

				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				r2_speech_goals = 0
		
				SET_PLAYER_CONTROL player1 OFF 
				SWITCH_WIDESCREEN ON
				MAKE_PLAYER_GANG_DISAPPEAR
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			
				SHUT_ALL_CHARS_UP TRUE
				
				REMOVE_ANIMATION SWAT
				REQUEST_MODEL FLAME

				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE r2_ped_decisions EVENT_ACQUAINTANCE_PED_HATE  
				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE r2_ped_decisions EVENT_ACQUAINTANCE_PED_DISLIKE  
				
				LOAD_SCENE 1855.5 -2101.8 12.5 
				
				//lead guy on top of the house 
				//GENERATE_RANDOM_INT_IN_RANGE 0 3 r2_char_select_flag
				CREATE_CHAR PEDTYPE_MISSION2 LSV1 1805.0 -2126.0 17.8 r2_car_mechanics[0]
				SET_CHAR_HEADING r2_car_mechanics[0] 306.5
				SET_CHAR_STAY_IN_SAME_PLACE r2_car_mechanics[0] TRUE 
				ADD_ARMOUR_TO_CHAR r2_car_mechanics[0] 100
				SET_CHAR_SUFFERS_CRITICAL_HITS r2_car_mechanics[0] FALSE  
				GIVE_WEAPON_TO_CHAR r2_car_mechanics[0] WEAPONTYPE_ROCKETLAUNCHER 30000 
				SET_CHAR_DECISION_MAKER r2_car_mechanics[0] r2_empty_ped_decision_maker
				ADD_BLIP_FOR_CHAR r2_car_mechanics[0] r2_final_blips[0] 
				CHANGE_BLIP_SCALE r2_final_blips[0] 1
				SET_CHAR_ACCURACY r2_car_mechanics[0] r2_char_accuracy
				ADD_ARMOUR_TO_CHAR r2_car_mechanics[0] 100

				//flamethrower enemy
				GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
				CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1811.8 -2124.0 12.6 r2_car_mechanics[1]
				SET_CHAR_HEADING r2_car_mechanics[1] 287.5
				SET_CHAR_STAY_IN_SAME_PLACE r2_car_mechanics[1] TRUE 
				GIVE_WEAPON_TO_CHAR r2_car_mechanics[1] WEAPONTYPE_FLAMETHROWER 30000 
				SET_CHAR_DECISION_MAKER r2_car_mechanics[1] r2_empty_ped_decision_maker
				ADD_BLIP_FOR_CHAR r2_car_mechanics[1] r2_final_blips[1] 
				CHANGE_BLIP_SCALE r2_final_blips[1] 1
				SET_CHAR_ACCURACY r2_car_mechanics[1] r2_char_accuracy
				 
				//two guards coming from the garage area of cesars house 
				GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
				CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1795.0 -2126.7 12.5 r2_car_mechanics[2]
				SET_CHAR_HEADING r2_car_mechanics[2] 358.6
				GIVE_WEAPON_TO_CHAR r2_car_mechanics[2] WEAPONTYPE_TEC9 3000 
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_car_mechanics[2] TRUE 
				SET_CHAR_DECISION_MAKER r2_car_mechanics[2] r2_empty_ped_decision_maker
				ADD_BLIP_FOR_CHAR r2_car_mechanics[2] r2_final_blips[2] 
				CHANGE_BLIP_SCALE r2_final_blips[2] 1
				SET_CHAR_ACCURACY r2_car_mechanics[2] r2_char_accuracy

			
				GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
				CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1795.0 -2130.7 12.5 r2_car_mechanics[3]
				SET_CHAR_HEADING r2_car_mechanics[3] 357.8
				GIVE_WEAPON_TO_CHAR r2_car_mechanics[3] WEAPONTYPE_TEC9 3000 
				SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_car_mechanics[3] TRUE 
				SET_CHAR_DECISION_MAKER r2_car_mechanics[3] r2_empty_ped_decision_maker
				ADD_BLIP_FOR_CHAR r2_car_mechanics[3] r2_final_blips[3] 
				CHANGE_BLIP_SCALE r2_final_blips[3] 1
				SET_CHAR_ACCURACY r2_car_mechanics[3] r2_char_accuracy

				//guards coming from house (right of the door as you look at it)
				GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
				CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1806.1 -2121.7 12.5 r2_car_mechanics[4]
				SET_CHAR_HEADING r2_car_mechanics[4] 357.8
				GIVE_WEAPON_TO_CHAR r2_car_mechanics[4] WEAPONTYPE_TEC9 3000 
				SET_CHAR_STAY_IN_SAME_PLACE r2_car_mechanics[4] TRUE 
				SET_CHAR_DECISION_MAKER r2_car_mechanics[4] r2_empty_ped_decision_maker
				ADD_BLIP_FOR_CHAR r2_car_mechanics[4] r2_final_blips[4] 
				CHANGE_BLIP_SCALE r2_final_blips[4] 1
				SET_CHAR_ACCURACY r2_car_mechanics[4] r2_char_accuracy
				ADD_ARMOUR_TO_CHAR r2_car_mechanics[4] 100

				//guards coming from house (left of the door as you look at it)
				GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
				CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1801.5 -2120.7 12.5 r2_car_mechanics[5]
				SET_CHAR_HEADING r2_car_mechanics[5] 357.8
				GIVE_WEAPON_TO_CHAR r2_car_mechanics[5] WEAPONTYPE_TEC9 3000 
				SET_CHAR_STAY_IN_SAME_PLACE r2_car_mechanics[5] TRUE 
				SET_CHAR_DECISION_MAKER r2_car_mechanics[5] r2_empty_ped_decision_maker 
				ADD_BLIP_FOR_CHAR r2_car_mechanics[5] r2_final_blips[5] 
				CHANGE_BLIP_SCALE r2_final_blips[5] 1
				SET_CHAR_ACCURACY r2_car_mechanics[5] r2_char_accuracy
				ADD_ARMOUR_TO_CHAR r2_car_mechanics[5] 100

				CLEAR_AREA 1855.5 -2101.8 12.5 30.0 TRUE
				
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				SET_CHAR_COORDINATES scplayer 1855.5 -2101.8 12.5   
				SET_CHAR_HEADING scplayer 90.0 
				TASK_GO_STRAIGHT_TO_COORD scplayer 1846.6 -2102.8 12.5 PEDMOVE_WALK -1    

				CLEAR_CHAR_TASKS_IMMEDIATELY cesar
				SET_CHAR_COORDINATES cesar 1855.5 -2102.8 12.5   
				SET_CHAR_HEADING cesar 90.0 
				TASK_GO_STRAIGHT_TO_COORD cesar 1846.6 -2102.8 12.5 PEDMOVE_WALK -1    

				CLEAR_CHAR_TASKS_IMMEDIATELY r2_flame
				SET_CHAR_COORDINATES r2_flame 1855.5 -2100.8 12.5   
				SET_CHAR_HEADING r2_flame 90.0 
				TASK_GO_STRAIGHT_TO_COORD r2_flame 1846.6 -2100.8 12.5 PEDMOVE_WALK -1    
		
				SET_FIXED_CAMERA_POSITION 1846.6 -2101.8 13.88 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1853.9 -2101.4 12.7 JUMP_CUT 
			
				DO_FADE 500 FADE_IN
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE
				GOSUB r2_death_checks
				IF r2_deathcheck_flag = 1
					GOTO mission_riot2_failed
				ENDIF
			
				r2_skip_cutscene_flag = 1
				SKIP_CUTSCENE_START
			
				// Let's fucking finish this!
				r2_speech_goals = 10
				r2_speech_control_flag = 0
				GOSUB r2_dialogue_setup 
				
				timera = 0
				r2_control_flag = 1	
			ENDIF
		ENDIF

		IF r2_control_flag = 1
			IF r2_speech_goals = 0 
				IF timera > 4000 
					DO_FADE 500 FADE_OUT
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE
					GOSUB r2_death_checks
					IF r2_deathcheck_flag = 1
						GOTO mission_riot2_failed
					ENDIF
			
					LOAD_SCENE 1809.8 -2123.4 12.7
					
					SET_FIXED_CAMERA_POSITION 1816.2 -2116.8 13.16 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 1808.6 -2123.8 15.0 JUMP_CUT 
				
					DO_FADE 500 FADE_IN
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE
					GOSUB r2_death_checks
					IF r2_deathcheck_flag = 1
						GOTO mission_riot2_failed
					ENDIF
				
					// This is a Vagos neighborhood now! 
					r2_speech_goals = 11
					GENERATE_RANDOM_INT_IN_RANGE 0 3 r2_speech_control_flag
					r2_random_last_label = r2_speech_control_flag + 1 
					GOSUB r2_dialogue_setup 
					
					timera = 0 
					r2_control_flag = 2
				ENDIF
			ENDIF
		ENDIF 

		IF r2_control_flag = 2
			IF r2_speech_goals = 0 
				IF timera > 4000 
					SET_FIXED_CAMERA_POSITION 1816.2 -2116.8 13.16 0.0 0.0 0.0
					IF NOT IS_CHAR_DEAD r2_car_mechanics[1]
						POINT_CAMERA_AT_CHAR r2_car_mechanics[1] FIXED INTERPOLATION 
						TASK_SHOOT_AT_COORD r2_car_mechanics[1] 1831.6 -2118.9 12.5 4000
					ENDIF
					
					// Torch those Aztecas!
					r2_speech_goals = 12
					GENERATE_RANDOM_INT_IN_RANGE 0 3 r2_speech_control_flag
					r2_random_last_label = r2_speech_control_flag + 1 
					GOSUB r2_dialogue_setup 
					
					timera = 0 
					r2_control_flag = 3
				ENDIF
			ENDIF
		ENDIF 

		IF r2_control_flag = 3 
			IF r2_speech_goals = 0 
				IF timera > 4000 
					r2_skip_cutscene_flag = 0
					SKIP_CUTSCENE_END
					GOSUB r2_death_checks
					IF r2_deathcheck_flag = 1
						GOTO mission_riot2_failed
					ENDIF
					
					//mission has been skipped
					IF r2_skip_cutscene_flag = 1
						CLEAR_PRINTS
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						r2_speech_goals = 0
				
						DO_FADE 500 FADE_OUT	
						WHILE GET_FADING_STATUS
						    WAIT 0
						ENDWHILE 
						GOSUB r2_death_checks
						IF r2_deathcheck_flag = 1
							GOTO mission_riot2_failed
						ENDIF
					ENDIF
					
					//lead guy on top of the house
					IF NOT IS_CHAR_DEAD r2_car_mechanics[0] 
						OPEN_SEQUENCE_TASK r2_seq
							TASK_SHOOT_AT_COORD -1 1832.7 -2090.1 14.1 500
							TASK_PLAY_ANIM -1 gun_stand PED 8.0 FALSE FALSE FALSE FALSE 1000
							TASK_SHOOT_AT_COORD -1 1853.6 -2087.0 20.5 500
							TASK_PLAY_ANIM -1 gun_stand PED 8.0 FALSE FALSE FALSE TRUE 1000
							TASK_SHOOT_AT_COORD -1 1819.5 -2113.1 12.9 500
							TASK_PLAY_ANIM -1 gun_stand PED 8.0 FALSE FALSE FALSE FALSE 1000
							SET_SEQUENCE_TO_REPEAT r2_seq 1
						CLOSE_SEQUENCE_TASK r2_seq			   
						PERFORM_SEQUENCE_TASK r2_car_mechanics[0] r2_seq	
						CLEAR_SEQUENCE_TASK r2_seq
					ENDIF	
				
					//flamethrower enemy
					IF NOT IS_CHAR_DEAD r2_car_mechanics[1] 
						OPEN_SEQUENCE_TASK r2_seq
							TASK_SHOOT_AT_COORD -1 1831.6 -2118.9 12.5 2000
							TASK_PAUSE -1 2000 
							SET_SEQUENCE_TO_REPEAT r2_seq 1
						CLOSE_SEQUENCE_TASK r2_seq			   
						PERFORM_SEQUENCE_TASK r2_car_mechanics[1] r2_seq	
						CLEAR_SEQUENCE_TASK r2_seq
					ENDIF
				
					//two guards coming from the garage area of cesars house
					IF NOT IS_CHAR_DEAD r2_car_mechanics[2] 
						OPEN_SEQUENCE_TASK r2_seq
							TASK_TOGGLE_DUCK -1 TRUE
							TASK_GO_STRAIGHT_TO_COORD -1 1795.0 -2106.7 12.5 PEDMOVE_WALK -1
							TASK_ACHIEVE_HEADING -1 277.9
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK r2_seq			   
						PERFORM_SEQUENCE_TASK r2_car_mechanics[2] r2_seq	
						CLEAR_SEQUENCE_TASK r2_seq
					ENDIF
					
					IF NOT IS_CHAR_DEAD r2_car_mechanics[3] 
						OPEN_SEQUENCE_TASK r2_seq
							TASK_TOGGLE_DUCK -1 TRUE
							TASK_GO_STRAIGHT_TO_COORD -1 1792.0 -2103.7 12.5 PEDMOVE_WALK -1
							TASK_ACHIEVE_HEADING -1 277.9
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK r2_seq			   
						PERFORM_SEQUENCE_TASK r2_car_mechanics[3] r2_seq	
						CLEAR_SEQUENCE_TASK r2_seq
					ENDIF

					//guards coming from house (right of the door as you look at it)
					IF NOT IS_CHAR_DEAD r2_car_mechanics[4]
						OPEN_SEQUENCE_TASK r2_seq
							TASK_GO_STRAIGHT_TO_COORD -1 1809.4 -2116.3 12.5 PEDMOVE_RUN -1
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK r2_seq			   
						PERFORM_SEQUENCE_TASK r2_car_mechanics[4] r2_seq	
						CLEAR_SEQUENCE_TASK r2_seq
					ENDIF 

					//guards coming from house (left of the door as you look at it)
					IF NOT IS_CHAR_DEAD r2_car_mechanics[5]    
						OPEN_SEQUENCE_TASK r2_seq
							TASK_GO_STRAIGHT_TO_COORD -1 1809.4 -2111.8 12.5 PEDMOVE_RUN -1
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK r2_seq			   
						PERFORM_SEQUENCE_TASK r2_car_mechanics[5] r2_seq	
						CLEAR_SEQUENCE_TASK r2_seq
					ENDIF

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					SET_CHAR_COORDINATES scplayer 1833.9 -2104.2 12.5   
					SET_CHAR_HEADING scplayer 122.4 
				
					CLEAR_CHAR_TASKS_IMMEDIATELY cesar
					SET_CHAR_COORDINATES cesar 1833.0 -2106.6 12.5   
					SET_CHAR_HEADING cesar 122.3 
					SET_CHAR_STAY_IN_SAME_PLACE cesar TRUE 
					SET_CHAR_DECISION_MAKER cesar r2_ped_decisions
					 
					CLEAR_CHAR_TASKS_IMMEDIATELY r2_flame
					SET_CHAR_COORDINATES r2_flame 1834.4 -2102.0 12.5   
					SET_CHAR_HEADING r2_flame 99.6 
					SET_CHAR_STAY_IN_SAME_PLACE r2_flame TRUE 
					OPEN_SEQUENCE_TASK r2_seq
						TASK_GO_STRAIGHT_TO_COORD -1 1814.1 -2102.4 12.5 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 112.7
						TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
					CLOSE_SEQUENCE_TASK r2_seq
					PERFORM_SEQUENCE_TASK r2_flame r2_seq	
					CLEAR_SEQUENCE_TASK r2_seq

					MARK_CHAR_AS_NO_LONGER_NEEDED r2_climbers[0]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_climbers[1]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_climbers[2]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_climbers[3]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_climbers[4]
					MARK_CHAR_AS_NO_LONGER_NEEDED r2_climbers[5]

					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE r2_ped_decisions EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 FALSE TRUE 	
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE r2_ped_decisions EVENT_ACQUAINTANCE_PED_DISLIKE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 FALSE TRUE 	
					
					SHUT_ALL_CHARS_UP FALSE
					
					SWITCH_WIDESCREEN OFF
					MAKE_PLAYER_GANG_REAPPEAR
					HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
					SET_PLAYER_CONTROL player1 ON
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT
					PRINT_NOW ( R2_07 ) 4000 1 //Finish off the last Vagos.
				
					//if cutscene has been skipped
					IF r2_skip_cutscene_flag = 1
						DO_FADE 500 FADE_IN	
						WHILE GET_FADING_STATUS
						    WAIT 0
						ENDWHILE 
						GOSUB r2_death_checks
						IF r2_deathcheck_flag = 1
							GOTO mission_riot2_failed
						ENDIF
					ENDIF
					
					r2_control_flag = 0
					r2_goals = 8
				ENDIF
			ENDIF
		ENDIF
	ENDIF 


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// Waiting for all the baddies to die from - STAGE 3 /////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	IF r2_goals = 8
		IF r2_control_flag = 0
			IF IS_CHAR_DEAD r2_car_mechanics[0] 
				REMOVE_BLIP r2_final_blips[0]
			ENDIF
			IF IS_CHAR_DEAD r2_car_mechanics[1] 
				REMOVE_BLIP r2_final_blips[1]
			ENDIF
			IF IS_CHAR_DEAD r2_car_mechanics[2] 
				REMOVE_BLIP r2_final_blips[2]
			ENDIF
			IF IS_CHAR_DEAD r2_car_mechanics[3] 
				REMOVE_BLIP r2_final_blips[3]
			ENDIF
			IF IS_CHAR_DEAD r2_car_mechanics[4] 
				REMOVE_BLIP r2_final_blips[4]
			ENDIF
			IF IS_CHAR_DEAD r2_car_mechanics[5] 
				REMOVE_BLIP r2_final_blips[5]
			ENDIF
			
			IF IS_CHAR_DEAD r2_car_mechanics[0]
				IF IS_CHAR_DEAD r2_car_mechanics[1]
					IF IS_CHAR_DEAD r2_car_mechanics[2]
						IF IS_CHAR_DEAD r2_car_mechanics[3]
							IF IS_CHAR_DEAD r2_car_mechanics[4]
								IF IS_CHAR_DEAD r2_car_mechanics[5]
									timera = 0
									r2_control_flag = 1
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF		
					
		IF r2_control_flag = 1
			IF timera > 2000
				
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				GOSUB r2_death_checks
				IF r2_deathcheck_flag = 1
					GOTO mission_riot2_failed
				ENDIF

				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				r2_speech_goals = 0
			
				SET_PLAYER_CONTROL player1 OFF
				SWITCH_WIDESCREEN ON
				MAKE_PLAYER_GANG_DISAPPEAR
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
				
				SHUT_ALL_CHARS_UP TRUE
				
				//player
				LOAD_SCENE 1814.9 -2112.1 12.4
				CLEAR_AREA 1814.9 -2112.1 12.4 30.0 TRUE 
				
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				SET_CHAR_COORDINATES scplayer 1814.9 -2112.1 12.4 
				SET_CHAR_HEADING scplayer 127.9

				OPEN_SEQUENCE_TASK r2_seq
					TASK_GO_STRAIGHT_TO_COORD -1 1807.8 -2122.0 12.5 PEDMOVE_WALK -1
					TASK_TURN_CHAR_TO_FACE_CHAR -1 cesar 
				CLOSE_SEQUENCE_TASK r2_seq
				PERFORM_SEQUENCE_TASK scplayer r2_seq	
				CLEAR_SEQUENCE_TASK r2_seq
				
				//cesar
				CLEAR_CHAR_TASKS_IMMEDIATELY cesar
				SET_CHAR_COORDINATES cesar 1812.4 -2111.3 12.4
				SET_CHAR_HEADING cesar 127.9 

				OPEN_SEQUENCE_TASK r2_seq
					TASK_GO_STRAIGHT_TO_COORD -1 1804.8 -2120.2 12.5 PEDMOVE_WALK -1
					TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 
				CLOSE_SEQUENCE_TASK r2_seq
				PERFORM_SEQUENCE_TASK cesar r2_seq	
				CLEAR_SEQUENCE_TASK r2_seq
				
				//galavet
				CLEAR_CHAR_TASKS_IMMEDIATELY r2_flame
				SET_CHAR_COORDINATES r2_flame 1810.4 -2111.0 12.4   
				SET_CHAR_HEADING r2_flame 135.1 
				
				OPEN_SEQUENCE_TASK r2_seq
					TASK_GO_STRAIGHT_TO_COORD -1 1807.0 -2118.8 12.5 PEDMOVE_WALK -1
					TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 
				CLOSE_SEQUENCE_TASK r2_seq
				PERFORM_SEQUENCE_TASK r2_flame r2_seq	
				CLEAR_SEQUENCE_TASK r2_seq

				SET_FIXED_CAMERA_POSITION 1812.6 -2114.5 13.0 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1804.5 -2123.6 15.0 JUMP_CUT 

				DO_FADE 500 FADE_IN
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 
				GOSUB r2_death_checks
				IF r2_deathcheck_flag = 1
					GOTO mission_riot2_failed
				ENDIF

				r2_speech_goals = 13
				r2_speech_control_flag = 0
				r2_random_last_label = 4
				GOSUB r2_dialogue_setup 

				r2_control_flag = 2
			ENDIF
		ENDIF
		
		IF r2_control_flag = 2
			IF r2_speech_goals = 0  
				//player
				OPEN_SEQUENCE_TASK r2_seq
					TASK_TURN_CHAR_TO_FACE_CHAR -1 cesar 
				CLOSE_SEQUENCE_TASK r2_seq
				PERFORM_SEQUENCE_TASK scplayer r2_seq	
				CLEAR_SEQUENCE_TASK r2_seq
				
				//cesar
				OPEN_SEQUENCE_TASK r2_seq
					TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 
				CLOSE_SEQUENCE_TASK r2_seq
				PERFORM_SEQUENCE_TASK cesar r2_seq	
				CLEAR_SEQUENCE_TASK r2_seq
				
				r2_speech_goals = 13
				r2_speech_control_flag = 4
				r2_random_last_label = 8
				GOSUB r2_dialogue_setup
				r2_control_flag = 3
			ENDIF
		ENDIF 

		IF r2_control_flag = 3
			IF r2_speech_goals = 0
				OPEN_SEQUENCE_TASK r2_seq
					TASK_GO_STRAIGHT_TO_COORD -1 1794.8 -2124.5 12.5 PEDMOVE_WALK -1
					TASK_GO_STRAIGHT_TO_COORD -1 1794.8 -2137.2 12.5 PEDMOVE_WALK -1
				CLOSE_SEQUENCE_TASK r2_seq
				PERFORM_SEQUENCE_TASK cesar r2_seq	
				CLEAR_SEQUENCE_TASK r2_seq
					
				OPEN_SEQUENCE_TASK r2_seq
					TASK_GO_STRAIGHT_TO_COORD -1 1794.8 -2122.5 12.4 PEDMOVE_WALK -1
					TASK_GO_STRAIGHT_TO_COORD -1 1794.8 -2137.2 12.5 PEDMOVE_WALK -1
				CLOSE_SEQUENCE_TASK r2_seq
				PERFORM_SEQUENCE_TASK r2_flame r2_seq	
				CLEAR_SEQUENCE_TASK r2_seq
				timera = 0
				r2_control_flag = 4
			ENDIF
		ENDIF

		IF r2_control_flag = 4
			IF timera > 3000 
				SHUT_ALL_CHARS_UP FALSE
				
				DELETE_CHAR cesar
				DELETE_CHAR r2_flame   
				GOTO mission_riot2_passed
			ENDIF
		ENDIF
	ENDIF


	///ingame dialogue///
	GOSUB r2_overall_dialogue


GOTO mission_riot2_loop



 // **************************************** Mission riot2 failed ************************								 
mission_riot2_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN

   

// **************************************** mission riot2 passed *************************
mission_riot2_passed:
flag_riot_mission_counter ++
PRINT_WITH_NUMBER_BIG ( M_PASSR ) 60 5000 1 //"Mission Passed!"
AWARD_PLAYER_MISSION_RESPECT 60//amount of respect
REMOVE_BLIP sweet_contact_blip
// El Corona
SET_ZONE_GANG_STRENGTH ELCO1 GANG_SMEX 40
SET_ZONE_GANG_STRENGTH ELCO2 GANG_SMEX 40
SET_ZONE_GANG_STRENGTH ELCO1 GANG_NMEX 0
SET_ZONE_GANG_STRENGTH ELCO2 GANG_NMEX 0
SET_ZONE_GANG_STRENGTH ELCO1 GANG_GROVE 0
SET_ZONE_GANG_STRENGTH ELCO2 GANG_GROVE 0
// Little Mexico
SET_ZONE_GANG_STRENGTH LMEX1a GANG_SMEX 40
SET_ZONE_GANG_STRENGTH LMEX1b GANG_SMEX 40
SET_ZONE_GANG_STRENGTH LMEX1a GANG_NMEX 0
SET_ZONE_GANG_STRENGTH LMEX1b GANG_NMEX 0
SET_ZONE_GANG_STRENGTH LMEX1a GANG_GROVE 0
SET_ZONE_GANG_STRENGTH LMEX1b GANG_GROVE 0
PLAY_MISSION_PASSED_TUNE 1
CLEAR_WANTED_LEVEL PLAYER1
REGISTER_MISSION_PASSED RIOT_2
PLAYER_MADE_PROGRESS 1
RETURN
		

// ********************************** mission cleanup ************************************
mission_cleanup_riot2:
//SET_CAMERA_BEHIND_PLAYER 
IF IS_STRING_EMPTY $shop_name
	RESTORE_CAMERA_JUMPCUT
ENDIF
SET_ZONE_NO_COPS ELCO FALSE
IF NOT IS_CHAR_DEAD r2_katana 
	SET_CHAR_COLLISION r2_katana TRUE
	REMOVE_CHAR_ELEGANTLY r2_katana 
ENDIF
START_NEW_SCRIPT andys_door_madness
//GOSUB r2_closing_doors
MARK_MODEL_AS_NO_LONGER_NEEDED VLA1  
MARK_MODEL_AS_NO_LONGER_NEEDED VLA2 
MARK_MODEL_AS_NO_LONGER_NEEDED VLA3 
//MARK_MODEL_AS_NO_LONGER_NEEDED FAM1
//MARK_MODEL_AS_NO_LONGER_NEEDED MEX 
MARK_MODEL_AS_NO_LONGER_NEEDED LSV1
MARK_MODEL_AS_NO_LONGER_NEEDED LSV2
//MARK_MODEL_AS_NO_LONGER_NEEDED LSV3
//MARK_MODEL_AS_NO_LONGER_NEEDED SENTINEL
SWITCH_EMERGENCY_SERVICES ON
MARK_MODEL_AS_NO_LONGER_NEEDED KATANA 
MARK_MODEL_AS_NO_LONGER_NEEDED DESERT_EAGLE 
MARK_MODEL_AS_NO_LONGER_NEEDED TEC9 
MARK_MODEL_AS_NO_LONGER_NEEDED ROCKETLA
MARK_MODEL_AS_NO_LONGER_NEEDED FLAME 
MARK_MODEL_AS_NO_LONGER_NEEDED GLENDALE
MARK_MODEL_AS_NO_LONGER_NEEDED BBALL_INGAME
SWITCH_PED_ROADS_BACK_TO_ORIGINAL 1945.8 -1956.5 0.0 1642.9 -2174.3 50.0
REMOVE_PICKUP r2_health 
REMOVE_PICKUP r2_gun 
REMOVE_PICKUP r2_pickup_health
REMOVE_ANIMATION CAR
REMOVE_ANIMATION BSKTBALL
REMOVE_ANIMATION SMOKING
REMOVE_ANIMATION SWAT
REMOVE_ANIMATION CASINO
REMOVE_ANIMATION CRACK
REMOVE_ANIMATION GANGS
IF NOT IS_CHAR_DEAD scplayer 
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
ENDIF
SHUT_ALL_CHARS_UP FALSE
REMOVE_CHAR_ELEGANTLY cesar
UNLOAD_SPECIAL_CHARACTER 1	
SET_GUNSHOT_SENSE_RANGE_FOR_RIOT2 -1.0																	   
REMOVE_BLIP r2_control_blip
REMOVE_BLIP r2_final_blips[0]
REMOVE_BLIP r2_final_blips[1]
REMOVE_BLIP r2_final_blips[2]
REMOVE_BLIP r2_final_blips[3]
REMOVE_BLIP r2_final_blips[4]
REMOVE_BLIP r2_final_blips[5]
REMOVE_BLIP r2_flats_lookout_blips[0]
REMOVE_BLIP r2_flats_lookout_blips[1]
REMOVE_BLIP r2_flats_lookout_blips[2]
REMOVE_BLIP r2_flats_lookout_blips[3]
REMOVE_BLIP r2_flats_lookout_blips[4]
REMOVE_BLIP r2_flats_lookout_blips[5]
REMOVE_BLIP r2_NW_goons_blips[0]
REMOVE_BLIP r2_NW_goons_blips[1]
REMOVE_BLIP r2_NW_goons_blips[2]
REMOVE_BLIP r2_NW_goons_blips[3]
REMOVE_BLIP r2_NW_goons_blips[4]
REMOVE_BLIP r2_NW_goons_blips[5]
REMOVE_BLIP r2_NE_goons_blips[0]
REMOVE_BLIP r2_NE_goons_blips[1]
REMOVE_BLIP r2_NE_goons_blips[2]
REMOVE_BLIP r2_NE_goons_blips[3]
REMOVE_BLIP r2_NE_goons_blips[4]
REMOVE_BLIP r2_NE_goons_blips[5]
REMOVE_BLIP r2_SW_goons_blips[0]
REMOVE_BLIP r2_SW_goons_blips[1]
REMOVE_BLIP r2_SW_goons_blips[2]
REMOVE_BLIP r2_SW_goons_blips[3]
REMOVE_BLIP r2_SW_goons_blips[4]
REMOVE_BLIP r2_SW_goons_blips[5]
REMOVE_BLIP r2_SE_goons_blips[0]
REMOVE_BLIP r2_SE_goons_blips[1]
REMOVE_BLIP r2_SE_goons_blips[2]
REMOVE_BLIP r2_SE_goons_blips[3]
REMOVE_BLIP r2_SE_goons_blips[4]
REMOVE_BLIP r2_SE_goons_blips[5]
REMOVE_BLIP r2_car_mechanics_blips[0] 
REMOVE_BLIP r2_car_mechanics_blips[1] 
REMOVE_BLIP r2_car_mechanics_blips[2] 
REMOVE_BLIP r2_car_mechanics_blips[3] 
REMOVE_BLIP r2_car_mechanics_blips[4] 
REMOVE_BLIP r2_car_mechanics_blips[5] 
REMOVE_BLIP r2_climbers_blips[0] 
REMOVE_BLIP r2_climbers_blips[1] 
REMOVE_BLIP r2_climbers_blips[2] 
REMOVE_BLIP r2_climbers_blips[3] 
REMOVE_BLIP r2_climbers_blips[4] 
REMOVE_BLIP r2_climbers_blips[5] 
REMOVE_BLIP r2_garage_blips[0]
REMOVE_BLIP r2_garage_blips[1]
REMOVE_BLIP r2_garage_blips[2]
REMOVE_BLIP r2_garage_blips[3]
ENABLE_AMBIENT_CRIME TRUE
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
r2_death_checks:///////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF IS_CHAR_DEAD cesar
	CLEAR_PRINTS
	PRINT_NOW ( R2_08 ) 7000 1 //You killed Cesar!	
	r2_deathcheck_flag = 1	
ENDIF
IF IS_CHAR_DEAD r2_launcher
	CLEAR_PRINTS
	PRINT_NOW ( R2_09 ) 7000 1 //You killed larry the launcher!	
	r2_deathcheck_flag = 1	
ENDIF

IF IS_CHAR_DEAD r2_flame
	CLEAR_PRINTS
	PRINT_NOW ( R2_10 ) 7000 1 //You killed flameboy!	
	r2_deathcheck_flag = 1	
ENDIF

IF IS_CHAR_DEAD r2_katana
	CLEAR_PRINTS
	PRINT_NOW ( R2_11 ) 7000 1 //You killed katanacunt!	
	r2_deathcheck_flag = 1	
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
creating_flats_lookouts:////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//lookout 0	- leaning agaisnt the wall smoking - RUNNER!!!
GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1831.6 -2004.6 12.6 r2_flats_lookout[0]
SET_INFORM_RESPECTED_FRIENDS r2_flats_lookout[0] 8.0 6 
SET_CHAR_HEADING r2_flats_lookout[0] 90.0
GIVE_WEAPON_TO_CHAR r2_flats_lookout[0] WEAPONTYPE_TEC9 3000 
TASK_PLAY_ANIM r2_flats_lookout[0] M_smklean_loop SMOKING 4.0 TRUE FALSE FALSE FALSE -1
SET_CHAR_DECISION_MAKER r2_flats_lookout[0] r2_empty_ped_decision_maker
ADD_BLIP_FOR_CHAR r2_flats_lookout[0] r2_flats_lookout_blips[0]
CHANGE_BLIP_SCALE r2_flats_lookout_blips[0] 1 					
SET_CHAR_ACCURACY r2_flats_lookout[0] r2_char_accuracy
ADD_ARMOUR_TO_CHAR r2_flats_lookout[0] 100

//lookout 1 - playing basketball offense
GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1825.5 -2002.4 12.6 r2_flats_lookout[1]
SET_INFORM_RESPECTED_FRIENDS r2_flats_lookout[1] 8.0 6 
SET_CHAR_HEADING r2_flats_lookout[1] 89.7
TASK_PLAY_ANIM r2_flats_lookout[1] BBALL_idleloop BSKTBALL 4.0 TRUE FALSE FALSE FALSE -1
CREATE_OBJECT BBALL_INGAME 0.0 0.0 0.0 r2_ball
ADD_BLIP_FOR_CHAR r2_flats_lookout[1] r2_flats_lookout_blips[1]
CHANGE_BLIP_SCALE r2_flats_lookout_blips[1] 1 					
SET_CHAR_ACCURACY r2_flats_lookout[1] r2_char_accuracy

SET_CHAR_DECISION_MAKER r2_flats_lookout[1] r2_ped_decisions
SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_flats_lookout[1] TRUE 

//lookout 2 - playing basketball defense
GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1824.3 -2002.4 12.6 r2_flats_lookout[2]
SET_INFORM_RESPECTED_FRIENDS r2_flats_lookout[2] 8.0 6 
SET_CHAR_HEADING r2_flats_lookout[2] 270.0
GIVE_WEAPON_TO_CHAR r2_flats_lookout[2] WEAPONTYPE_TEC9 3000 
TASK_PLAY_ANIM r2_flats_lookout[2] BBALL_def_loop BSKTBALL 4.0 TRUE FALSE FALSE FALSE -1
SET_CHAR_DECISION_MAKER r2_flats_lookout[2] r2_ped_decisions
SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_flats_lookout[2] TRUE 
ADD_BLIP_FOR_CHAR r2_flats_lookout[2] r2_flats_lookout_blips[2]
CHANGE_BLIP_SCALE r2_flats_lookout_blips[2] 1 					
SET_CHAR_ACCURACY r2_flats_lookout[2] r2_char_accuracy
ADD_ARMOUR_TO_CHAR r2_flats_lookout[2] 100

//lookout 3 - watching the basketball right 
GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1827.7 -1999.1 12.6 r2_flats_lookout[3]
SET_INFORM_RESPECTED_FRIENDS r2_flats_lookout[3] 8.0 6 
SET_CHAR_HEADING r2_flats_lookout[3] 133.6
GIVE_WEAPON_TO_CHAR r2_flats_lookout[3] WEAPONTYPE_TEC9 3000 
SET_CHAR_DECISION_MAKER r2_flats_lookout[3] r2_ped_decisions
SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_flats_lookout[3] TRUE 
ADD_BLIP_FOR_CHAR r2_flats_lookout[3] r2_flats_lookout_blips[3]
CHANGE_BLIP_SCALE r2_flats_lookout_blips[3] 1 					
SET_CHAR_ACCURACY r2_flats_lookout[3] r2_char_accuracy

//lookout 4	- watching the basketball left
GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1828.2 -2000.4 12.6 r2_flats_lookout[4]
SET_INFORM_RESPECTED_FRIENDS r2_flats_lookout[4] 8.0 6 
SET_CHAR_HEADING r2_flats_lookout[4] 108.2
GIVE_WEAPON_TO_CHAR r2_flats_lookout[4] WEAPONTYPE_TEC9 3000 
SET_CHAR_DECISION_MAKER r2_flats_lookout[4] r2_ped_decisions
SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_flats_lookout[4] TRUE 
ADD_BLIP_FOR_CHAR r2_flats_lookout[4] r2_flats_lookout_blips[4]
CHANGE_BLIP_SCALE r2_flats_lookout_blips[4] 1 					
SET_CHAR_ACCURACY r2_flats_lookout[4] r2_char_accuracy
ADD_ARMOUR_TO_CHAR r2_flats_lookout[4] 100

//lookout 5 - walking to the fence then starting to smoke 
GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1834.3 -1995.9 12.6 r2_flats_lookout[5]
SET_INFORM_RESPECTED_FRIENDS r2_flats_lookout[5] 8.0 6 
SET_CHAR_HEADING r2_flats_lookout[5] 88.1
SET_CHAR_DECISION_MAKER r2_flats_lookout[5] r2_ped_decisions
SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_flats_lookout[5] TRUE 
ADD_BLIP_FOR_CHAR r2_flats_lookout[5] r2_flats_lookout_blips[5]
CHANGE_BLIP_SCALE r2_flats_lookout_blips[5] 1 					
SET_CHAR_ACCURACY r2_flats_lookout[5] r2_char_accuracy


////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
waking_up_the_neighbourhood:////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF r2_SW_control_flag[0] = 0
	r2_x = 1857.2  
	r2_y = -2020.6 
	r2_z =	12.6
ELSE
	IF r2_NW_control_flag[0] = 0
		r2_x = 1865.3  
		r2_y = -1992.2 
		r2_z =	12.6
	ELSE
		IF r2_NE_control_flag[0] = 0
			r2_x = 1899.3 
			r2_y = -1991.7
			r2_z =	12.6
		ELSE
			IF r2_SE_control_flag[0] = 0
				r2_x = 1908.3  
				r2_y = -2020.1
				r2_z =	12.6
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF NOT IS_CHAR_DEAD r2_flats_lookout[0]
	IF r2_runner_control_flag = 0
		OPEN_SEQUENCE_TASK r2_seq
			TASK_FOLLOW_PATH_NODES_TO_COORD -1 r2_x r2_y r2_z PEDMOVE_SPRINT -2 
		CLOSE_SEQUENCE_TASK r2_seq
		PERFORM_SEQUENCE_TASK r2_flats_lookout[0] r2_seq		
		CLEAR_SEQUENCE_TASK r2_seq
		r2_runner_control_flag = 1
	ENDIF
	IF r2_runner_control_flag = 1
		GET_SCRIPT_TASK_STATUS r2_flats_lookout[0] PERFORM_SEQUENCE_TASK task_status
		IF task_status = FINISHED_TASK
			IF r2_SW_control_flag[0] = 0
				SET_CHAR_SAY_CONTEXT r2_flats_lookout[0] CONTEXT_GLOBAL_COVER_ME r2_context
				r2_SW_control_flag[0] = 1
			ELSE
				IF r2_NW_control_flag[0] = 0
					SET_CHAR_SAY_CONTEXT r2_flats_lookout[0] CONTEXT_GLOBAL_COVER_ME r2_context
					r2_NW_control_flag[0] = 1
				ELSE
					IF r2_NE_control_flag[0] = 0
						SET_CHAR_SAY_CONTEXT r2_flats_lookout[0] CONTEXT_GLOBAL_COVER_ME r2_context
						r2_NE_control_flag[0] = 1
					ELSE
						IF r2_SE_control_flag[0] = 0
							SET_CHAR_SAY_CONTEXT r2_flats_lookout[0] CONTEXT_GLOBAL_COVER_ME r2_context
							r2_SE_control_flag[0] = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			r2_runner_control_flag = 2
		ENDIF	
	ENDIF 
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
creating_NW_goons://////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF r2_NW_control_flag[0] = 1
	//NW house 0
	ROTATE_OBJECT riot2_door[3] 270.0 360.0 FALSE

	//House 0 - Goon 0
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1850.3 -1990.0 12.6 r2_NW_goons[0]
	SET_INFORM_RESPECTED_FRIENDS r2_NW_goons[0] 8.0 6 
	SET_CHAR_HEADING r2_NW_goons[0] 270.0
	GIVE_WEAPON_TO_CHAR r2_NW_goons[0] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_NW_goons[0] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_NW_goons[0] TRUE 
	ADD_BLIP_FOR_CHAR r2_NW_goons[0] r2_NW_goons_blips[0]
	CHANGE_BLIP_SCALE r2_NW_goons_blips[0] 1 					
	SET_CHAR_ACCURACY r2_NW_goons[0] r2_char_accuracy
	ADD_ARMOUR_TO_CHAR r2_NW_goons[0] 100
	
	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 200
		TASK_GO_STRAIGHT_TO_COORD -1 1852.9 -1990.0 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1853.9 -1984.1 12.6 PEDMOVE_SPRINT -2  /// going to the north corner of house 0
		TASK_ACHIEVE_HEADING -1  355.4 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_NW_goons[0] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

	//House 0 - Goon 1
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1851.7 -1989.9 12.6 r2_NW_goons[1]
	SET_INFORM_RESPECTED_FRIENDS r2_NW_goons[1] 8.0 6 
	SET_CHAR_HEADING r2_NW_goons[1] 270.7
	GIVE_WEAPON_TO_CHAR r2_NW_goons[1] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_NW_goons[1] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_NW_goons[1] TRUE 
	ADD_BLIP_FOR_CHAR r2_NW_goons[1] r2_NW_goons_blips[1]
	CHANGE_BLIP_SCALE r2_NW_goons_blips[1] 1 					
	SET_CHAR_ACCURACY r2_NW_goons[1] r2_char_accuracy

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 400
		TASK_GO_STRAIGHT_TO_COORD -1 1852.9 -1990.0 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1854.5 -1985.1 12.6 PEDMOVE_SPRINT -2	/// going to the north corner of house 0
		TASK_ACHIEVE_HEADING -1  186.2 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_NW_goons[1] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

ENDIF

IF r2_NW_control_flag[1] = 1
	//NW house 1
	ROTATE_OBJECT riot2_door[4] 180.0 360.0 FALSE

	//House 1 - Goon 2
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1867.7 -1983.7 12.6 r2_NW_goons[2]
	SET_INFORM_RESPECTED_FRIENDS r2_NW_goons[2] 8.0 6 
	SET_CHAR_HEADING r2_NW_goons[2] 180.0
	GIVE_WEAPON_TO_CHAR r2_NW_goons[2] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_NW_goons[2] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_NW_goons[2] TRUE 
	ADD_BLIP_FOR_CHAR r2_NW_goons[2] r2_NW_goons_blips[2]
	CHANGE_BLIP_SCALE r2_NW_goons_blips[2] 1 					
	SET_CHAR_ACCURACY r2_NW_goons[2] r2_char_accuracy
	ADD_ARMOUR_TO_CHAR r2_NW_goons[2] 100

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 400
		TASK_GO_STRAIGHT_TO_COORD -1 1867.7 -1986.8 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1854.5 -1999.6 12.6 PEDMOVE_SPRINT -2	/// going to the south corner of house 0
		TASK_ACHIEVE_HEADING -1  198.8 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_NW_goons[2] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

	//House 1 - Goon 3 
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1867.7 -1984.7 12.6 r2_NW_goons[3]
	SET_INFORM_RESPECTED_FRIENDS r2_NW_goons[3] 8.0 6 
	SET_CHAR_HEADING r2_NW_goons[3] 180.0
	GIVE_WEAPON_TO_CHAR r2_NW_goons[3] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_NW_goons[3] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_NW_goons[3] TRUE 
	ADD_BLIP_FOR_CHAR r2_NW_goons[3] r2_NW_goons_blips[3]
	CHANGE_BLIP_SCALE r2_NW_goons_blips[3] 1 					
	SET_CHAR_ACCURACY r2_NW_goons[3] r2_char_accuracy

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 200
		TASK_GO_STRAIGHT_TO_COORD -1 1867.7 -1986.8 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1854.5 -1997.6 12.6 PEDMOVE_SPRINT -2	/// going to the south corner of house 0
		TASK_ACHIEVE_HEADING -1 315.1 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_NW_goons[3] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

ENDIF

IF r2_NW_control_flag[2] = 1
	//NW house 2
	ROTATE_OBJECT riot2_door[5] 0.0 360.0 FALSE

	//House 2 - Goon 4
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1866.2 -1999.7 12.6 r2_NW_goons[4]
	SET_INFORM_RESPECTED_FRIENDS r2_NW_goons[4] 8.0 6 
	SET_CHAR_HEADING r2_NW_goons[4] 0.0
	GIVE_WEAPON_TO_CHAR r2_NW_goons[4] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_NW_goons[4] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_NW_goons[4] TRUE 
	ADD_BLIP_FOR_CHAR r2_NW_goons[4] r2_NW_goons_blips[4]
	CHANGE_BLIP_SCALE r2_NW_goons_blips[4] 1 					
	SET_CHAR_ACCURACY r2_NW_goons[4] r2_char_accuracy
	ADD_ARMOUR_TO_CHAR r2_NW_goons[4] 100
	
	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 200
		TASK_GO_STRAIGHT_TO_COORD -1 1866.2 -1997.0 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1859.5 -1996.2 12.6 PEDMOVE_SPRINT -2	/// going to the west corner of house 2
		TASK_ACHIEVE_HEADING -1 148.0 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_NW_goons[4] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

	//House 2 - Goon 5
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1865.9 -1998.7 12.6 r2_NW_goons[5]
	SET_INFORM_RESPECTED_FRIENDS r2_NW_goons[5] 8.0 6 
	SET_CHAR_HEADING r2_NW_goons[5] 0.0
	GIVE_WEAPON_TO_CHAR r2_NW_goons[5] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_NW_goons[5] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_NW_goons[5] TRUE 
	ADD_BLIP_FOR_CHAR r2_NW_goons[5] r2_NW_goons_blips[5]
	CHANGE_BLIP_SCALE r2_NW_goons_blips[5] 1 					
	SET_CHAR_ACCURACY r2_NW_goons[5] r2_char_accuracy
	
	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 400
		TASK_GO_STRAIGHT_TO_COORD -1 1866.2 -1997.0 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1860.0 -1994.0 12.6 PEDMOVE_SPRINT -2	/// going to the west corner of house 2
		TASK_ACHIEVE_HEADING -1 280.2 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_NW_goons[5] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
creating_NE_goons://////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF r2_NE_control_flag[0] = 1
	//NE house 0
	ROTATE_OBJECT riot2_door[6] 180.0 360.0 FALSE
	
	//House 0 - Goon 0
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1900.1 -1983.5 12.6 r2_NE_goons[0]
	SET_INFORM_RESPECTED_FRIENDS r2_NE_goons[0] 8.0 6 
	SET_CHAR_HEADING r2_NE_goons[0] 180.0
	GIVE_WEAPON_TO_CHAR r2_NE_goons[0] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_NE_goons[0] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_NE_goons[0] TRUE 
	ADD_BLIP_FOR_CHAR r2_NE_goons[0] r2_NE_goons_blips[0]
	CHANGE_BLIP_SCALE r2_NE_goons_blips[0] 1 					
	SET_CHAR_ACCURACY r2_NE_goons[0] r2_char_accuracy
	ADD_ARMOUR_TO_CHAR r2_NE_goons[0] 100

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 400
		TASK_GO_STRAIGHT_TO_COORD -1 1900.1 -1987.4 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1905.0 -1987.2 12.6 PEDMOVE_SPRINT -2  /// going to the east corner of house 0
		TASK_ACHIEVE_HEADING -1 267.4 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_NE_goons[0] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

	//House 0 - Goon 1
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1900.1 -1984.5 12.6 r2_NE_goons[1]
	SET_INFORM_RESPECTED_FRIENDS r2_NE_goons[1] 8.0 6 
	SET_CHAR_HEADING r2_NE_goons[1] 180.7
	GIVE_WEAPON_TO_CHAR r2_NE_goons[1] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_NE_goons[1] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_NE_goons[1] TRUE 
	ADD_BLIP_FOR_CHAR r2_NE_goons[1] r2_NE_goons_blips[1]
	CHANGE_BLIP_SCALE r2_NE_goons_blips[1] 1 					
	SET_CHAR_ACCURACY r2_NE_goons[1] r2_char_accuracy

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 200
		TASK_GO_STRAIGHT_TO_COORD -1 1900.1 -1987.4 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1904.4 -1988.1 12.6 PEDMOVE_SPRINT -2  /// going to the east corner of house 0
		TASK_ACHIEVE_HEADING -1 108.9 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_NE_goons[1] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

ENDIF

IF r2_NE_control_flag[1] = 1
	//NE house 1
	ROTATE_OBJECT riot2_door[7] 90.0 360.0 FALSE

	//House 1 - Goon 2
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1915.8 -1993.5 12.6 r2_NE_goons[2]
	SET_INFORM_RESPECTED_FRIENDS r2_NE_goons[2] 8.0 6 
	SET_CHAR_HEADING r2_NE_goons[2] 90.0
	GIVE_WEAPON_TO_CHAR r2_NE_goons[2] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_NE_goons[2] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_NE_goons[2] TRUE 
	ADD_BLIP_FOR_CHAR r2_NE_goons[2] r2_NE_goons_blips[2]
	CHANGE_BLIP_SCALE r2_NE_goons_blips[2] 1 					
	SET_CHAR_ACCURACY r2_NE_goons[2] r2_char_accuracy
	ADD_ARMOUR_TO_CHAR r2_NE_goons[2] 100

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 400
		TASK_GO_STRAIGHT_TO_COORD -1 1912.1 -1993.2 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1911.9 -1998.5 12.6 PEDMOVE_SPRINT -2  /// going to the south corner of house 1
		TASK_ACHIEVE_HEADING -1 198.9 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_NE_goons[2] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	
	
	//House 1 - Goon 3 
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1914.8 -1993.5 12.6 r2_NE_goons[3]
	SET_INFORM_RESPECTED_FRIENDS r2_NE_goons[3] 8.0 6 
	SET_CHAR_HEADING r2_NE_goons[3] 90.0
	GIVE_WEAPON_TO_CHAR r2_NE_goons[3] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_NE_goons[3] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_NE_goons[3] TRUE 
	ADD_BLIP_FOR_CHAR r2_NE_goons[3] r2_NE_goons_blips[3]
	CHANGE_BLIP_SCALE r2_NE_goons_blips[3] 1 					
	SET_CHAR_ACCURACY r2_NE_goons[3] r2_char_accuracy

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 200
		TASK_GO_STRAIGHT_TO_COORD -1 1912.1 -1993.2 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1911.0 -1997.1 12.6 PEDMOVE_SPRINT -2  /// going to the south corner of house 1
		TASK_ACHIEVE_HEADING -1 36.7 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_NE_goons[3] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

ENDIF

IF r2_NE_control_flag[2] = 1
	//NE house 2
	ROTATE_OBJECT riot2_door[8] 0.0 360.0 FALSE

	//House 2 - Goon 4
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1898.3 -1999.9 12.6 r2_NE_goons[4]
	SET_INFORM_RESPECTED_FRIENDS r2_NE_goons[4] 8.0 6 
	SET_CHAR_HEADING r2_NE_goons[4] 0.0
	GIVE_WEAPON_TO_CHAR r2_NE_goons[4] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_NE_goons[4] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_NE_goons[4] TRUE 
	ADD_BLIP_FOR_CHAR r2_NE_goons[4] r2_NE_goons_blips[4]
	CHANGE_BLIP_SCALE r2_NE_goons_blips[4] 1 					
	SET_CHAR_ACCURACY r2_NE_goons[4] r2_char_accuracy
	ADD_ARMOUR_TO_CHAR r2_NE_goons[4] 100

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 400
		TASK_GO_STRAIGHT_TO_COORD -1 1898.4 -1996.8 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1891.8 -1996.0 12.6 PEDMOVE_SPRINT -2  /// going to the west corner of house 2
		TASK_ACHIEVE_HEADING -1 71.9 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_NE_goons[4] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

	//House 2 - Goon 5
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1898.3 -1998.9 12.6 r2_NE_goons[5]
	SET_INFORM_RESPECTED_FRIENDS r2_NE_goons[5] 8.0 6 
	SET_CHAR_HEADING r2_NE_goons[5] 0.0
	GIVE_WEAPON_TO_CHAR r2_NE_goons[5] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_NE_goons[5] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_NE_goons[5] TRUE 
	ADD_BLIP_FOR_CHAR r2_NE_goons[5] r2_NE_goons_blips[5]
	CHANGE_BLIP_SCALE r2_NE_goons_blips[5] 1 					
	SET_CHAR_ACCURACY r2_NE_goons[5] r2_char_accuracy

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 200
		TASK_GO_STRAIGHT_TO_COORD -1 1898.4 -1996.8 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1892.4 -1995.1 12.6 PEDMOVE_SPRINT -2  /// going to the west corner of house 2
		TASK_ACHIEVE_HEADING -1 302.4 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_NE_goons[5] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
creating_SW_goons://////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF r2_SW_control_flag[0] = 1
	//SW house 0
	ROTATE_OBJECT riot2_door[12] 270.0 360.0 FALSE

	//House 0 - Goon 0
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1850.4 -2019.5 12.6 r2_SW_goons[0]
	SET_INFORM_RESPECTED_FRIENDS r2_SW_goons[0] 8.0 6 
	SET_CHAR_HEADING r2_SW_goons[0] 270.0
	GIVE_WEAPON_TO_CHAR r2_SW_goons[0] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_SW_goons[0] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_SW_goons[0] TRUE 
	ADD_BLIP_FOR_CHAR r2_SW_goons[0] r2_SW_goons_blips[0]
	CHANGE_BLIP_SCALE r2_SW_goons_blips[0] 1 					
	SET_CHAR_ACCURACY r2_SW_goons[0] r2_char_accuracy
	ADD_ARMOUR_TO_CHAR r2_SW_goons[0] 100

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 400
		TASK_GO_STRAIGHT_TO_COORD -1 1854.1 -2019.4 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1855.3 -2010.4 12.6 PEDMOVE_SPRINT -2  /// going to the north corner of house 0
		TASK_ACHIEVE_HEADING -1 30.7 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_SW_goons[0] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

	//House 0 - Goon 1
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1851.7 -2019.5 12.6 r2_SW_goons[1]
	SET_INFORM_RESPECTED_FRIENDS r2_SW_goons[1] 8.0 6 
	SET_CHAR_HEADING r2_SW_goons[1] 270.7
	GIVE_WEAPON_TO_CHAR r2_SW_goons[1] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_SW_goons[1] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_SW_goons[1] TRUE 
	ADD_BLIP_FOR_CHAR r2_SW_goons[1] r2_SW_goons_blips[1]
	CHANGE_BLIP_SCALE r2_SW_goons_blips[1] 1 					
	SET_CHAR_ACCURACY r2_SW_goons[1] r2_char_accuracy

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 200
		TASK_GO_STRAIGHT_TO_COORD -1 1854.1 -2019.4 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1855.3 -2012.4 12.6 PEDMOVE_SPRINT -2  /// going to the north corner of house 0
		TASK_ACHIEVE_HEADING -1 195.4 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_SW_goons[1] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

ENDIF

IF r2_SW_control_flag[1] = 1
	//SW house 1
	ROTATE_OBJECT riot2_door[13] 90.0 360.0 FALSE

	//House 1 - Goon 2
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1866.7 -2021.1 12.6 r2_SW_goons[2]
	SET_INFORM_RESPECTED_FRIENDS r2_SW_goons[2] 8.0 6 
	SET_CHAR_HEADING r2_SW_goons[2] 90.0
	GIVE_WEAPON_TO_CHAR r2_SW_goons[2] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_SW_goons[2] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_SW_goons[2] TRUE 
	ADD_BLIP_FOR_CHAR r2_SW_goons[2] r2_SW_goons_blips[2]
	CHANGE_BLIP_SCALE r2_SW_goons_blips[2] 1 					
	SET_CHAR_ACCURACY r2_SW_goons[2] r2_char_accuracy
	ADD_ARMOUR_TO_CHAR r2_SW_goons[2] 100

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 400
		TASK_GO_STRAIGHT_TO_COORD -1 1863.3 -2021.2 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1862.9 -2011.7 12.6 PEDMOVE_SPRINT -2  /// going to the north corner of house 1
		TASK_ACHIEVE_HEADING -1 43.3 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_SW_goons[2] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

	//House 1 - Goon 3 
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1865.5 -2021.3 12.6 r2_SW_goons[3]
	SET_INFORM_RESPECTED_FRIENDS r2_SW_goons[3] 8.0 6 
	SET_CHAR_HEADING r2_SW_goons[3] 90.0
	GIVE_WEAPON_TO_CHAR r2_SW_goons[3] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_SW_goons[3] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_SW_goons[3] TRUE 
	ADD_BLIP_FOR_CHAR r2_SW_goons[3] r2_SW_goons_blips[3]
	CHANGE_BLIP_SCALE r2_SW_goons_blips[3] 1 					
	SET_CHAR_ACCURACY r2_SW_goons[3] r2_char_accuracy

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 200
		TASK_GO_STRAIGHT_TO_COORD -1 1863.3 -2021.2 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1863.4 -2013.9 12.6 PEDMOVE_SPRINT -2  /// going to the north corner of house 1
		TASK_ACHIEVE_HEADING -1 154.0 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_SW_goons[3] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

ENDIF

IF r2_SW_control_flag[2] = 1
	//SW house 2
	ROTATE_OBJECT riot2_door[14] 0.0 360.0 FALSE

	//House 2 - Goon 4
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1857.2 -2036.9 12.6 r2_SW_goons[4]
	SET_INFORM_RESPECTED_FRIENDS r2_SW_goons[4] 8.0 6 
	SET_CHAR_HEADING r2_SW_goons[4] 0.0
	GIVE_WEAPON_TO_CHAR r2_SW_goons[4] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_SW_goons[4] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_SW_goons[4] TRUE 
	ADD_BLIP_FOR_CHAR r2_SW_goons[4] r2_SW_goons_blips[4]
	CHANGE_BLIP_SCALE r2_SW_goons_blips[4] 1 					
	SET_CHAR_ACCURACY r2_SW_goons[4] r2_char_accuracy
	ADD_ARMOUR_TO_CHAR r2_SW_goons[4] 100

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 400
		TASK_GO_STRAIGHT_TO_COORD -1 1857.2 -2032.9 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1854.5 -2026.5 12.6 PEDMOVE_SPRINT -2  /// going to the south corner of house 0
		TASK_ACHIEVE_HEADING -1 198.8 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_SW_goons[4] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

	//House 2 - Goon 5
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1857.1 -2035.8 12.6 r2_SW_goons[5]
	SET_INFORM_RESPECTED_FRIENDS r2_SW_goons[5] 8.0 6 
	SET_CHAR_HEADING r2_SW_goons[5] 0.0
	GIVE_WEAPON_TO_CHAR r2_SW_goons[5] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_SW_goons[5] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_SW_goons[5] TRUE 
	ADD_BLIP_FOR_CHAR r2_SW_goons[5] r2_SW_goons_blips[5]
	CHANGE_BLIP_SCALE r2_SW_goons_blips[5] 1 					
	SET_CHAR_ACCURACY r2_SW_goons[5] r2_char_accuracy
	
	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 200
		TASK_GO_STRAIGHT_TO_COORD -1 1857.2 -2032.9 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1854.5 -2024.3 12.6 PEDMOVE_SPRINT -2  /// going to the south corner of house 0
		TASK_ACHIEVE_HEADING -1 346.3 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_SW_goons[5] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
creating_SE_goons://////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF r2_SE_control_flag[0] = 1
	//SE house 0
	ROTATE_OBJECT riot2_door[9] 270.0 360.0 FALSE

	//House 0 - Goon 0
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1899.3 -2019.5 12.6 r2_SE_goons[0]
	SET_INFORM_RESPECTED_FRIENDS r2_SE_goons[0] 8.0 6 
	SET_CHAR_HEADING r2_SE_goons[0] 270.0
	GIVE_WEAPON_TO_CHAR r2_SE_goons[0] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_SE_goons[0] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_SE_goons[0] TRUE 
	ADD_BLIP_FOR_CHAR r2_SE_goons[0] r2_SE_goons_blips[0]
	CHANGE_BLIP_SCALE r2_SE_goons_blips[0] 1 					
	SET_CHAR_ACCURACY r2_SE_goons[0] r2_char_accuracy
	ADD_ARMOUR_TO_CHAR r2_SE_goons[0] 100

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 400
		TASK_GO_STRAIGHT_TO_COORD -1 1902.1 -2019.7 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1903.2 -2012.3 12.6 PEDMOVE_SPRINT -2  /// going to the north corner of house 0
		TASK_ACHIEVE_HEADING -1 330.8 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_SE_goons[0] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	
	
	//House 0 - Goon 1
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1900.3 -2019.5 12.6 r2_SE_goons[1]
	SET_INFORM_RESPECTED_FRIENDS r2_SE_goons[1] 8.0 6 
	SET_CHAR_HEADING r2_SE_goons[1] 270.7
	GIVE_WEAPON_TO_CHAR r2_SE_goons[1] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_SE_goons[1] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_SE_goons[1] TRUE 
	ADD_BLIP_FOR_CHAR r2_SE_goons[1] r2_SE_goons_blips[1]
	CHANGE_BLIP_SCALE r2_SE_goons_blips[1] 1 					
	SET_CHAR_ACCURACY r2_SE_goons[1] r2_char_accuracy

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 200
		TASK_GO_STRAIGHT_TO_COORD -1 1902.1 -2019.7 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1903.2 -2013.6 12.6 PEDMOVE_SPRINT -2  /// going to the north corner of house 0
		TASK_ACHIEVE_HEADING -1 195.6 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_SE_goons[1] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

ENDIF

IF r2_SE_control_flag[1] = 1
	//SE house 1
	ROTATE_OBJECT riot2_door[10] 90.0 360.0 FALSE

	//House 1 - Goon 2
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1915.8 -2021.5 12.6 r2_SE_goons[2]
	SET_INFORM_RESPECTED_FRIENDS r2_SE_goons[2] 8.0 6 
	SET_CHAR_HEADING r2_SE_goons[2] 90.0
	GIVE_WEAPON_TO_CHAR r2_SE_goons[2] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_SE_goons[2] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_SE_goons[2] TRUE 
	ADD_BLIP_FOR_CHAR r2_SE_goons[2] r2_SE_goons_blips[2]
	CHANGE_BLIP_SCALE r2_SE_goons_blips[2] 1 					
	SET_CHAR_ACCURACY r2_SE_goons[2] r2_char_accuracy
	ADD_ARMOUR_TO_CHAR r2_SE_goons[2] 100

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 400
		TASK_GO_STRAIGHT_TO_COORD -1 1912.8 -2021.4 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1912.1 -2012.8 12.6 PEDMOVE_SPRINT -2  /// going to the north corner of house 1
		TASK_ACHIEVE_HEADING -1 21.8 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_SE_goons[2] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

	//House 1 - Goon 3 
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1914.8 -2021.5 12.6 r2_SE_goons[3]
	SET_INFORM_RESPECTED_FRIENDS r2_SE_goons[3] 8.0 6 
	SET_CHAR_HEADING r2_SE_goons[3] 90.0
	GIVE_WEAPON_TO_CHAR r2_SE_goons[3] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_SE_goons[3] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_SE_goons[3] TRUE 
	ADD_BLIP_FOR_CHAR r2_SE_goons[3] r2_SE_goons_blips[3]
	CHANGE_BLIP_SCALE r2_SE_goons_blips[3] 1 					
	SET_CHAR_ACCURACY r2_SE_goons[3] r2_char_accuracy

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 200
		TASK_GO_STRAIGHT_TO_COORD -1 1912.8 -2021.4 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1911.7 -2014.3 12.6 PEDMOVE_SPRINT -2  /// going to the north corner of house 1
		TASK_ACHIEVE_HEADING -1 171.0 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_SE_goons[3] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

ENDIF

IF r2_SE_control_flag[2] = 1
	//SE house 2
	ROTATE_OBJECT riot2_door[11] 0.0 360.0 FALSE

	//House 2 - Goon 4
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1906.1 -2036.9 12.6 r2_SE_goons[4]
	SET_INFORM_RESPECTED_FRIENDS r2_SE_goons[4] 8.0 6 
	SET_CHAR_HEADING r2_SE_goons[4] 0.0
	GIVE_WEAPON_TO_CHAR r2_SE_goons[4] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_SE_goons[4] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_SE_goons[4] TRUE 
	ADD_BLIP_FOR_CHAR r2_SE_goons[4] r2_SE_goons_blips[4]
	CHANGE_BLIP_SCALE r2_SE_goons_blips[4] 1 					
	SET_CHAR_ACCURACY r2_SE_goons[4] r2_char_accuracy
	ADD_ARMOUR_TO_CHAR r2_SE_goons[4] 100

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 400
		TASK_GO_STRAIGHT_TO_COORD -1 1906.0 -2033.5 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1907.3 -2033.3 12.6 PEDMOVE_SPRINT -2  /// going to the west corner of house 2
		TASK_ACHIEVE_HEADING -1 32.4 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_SE_goons[4] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

	//House 2 - Goon 5
	GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
	CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1906.1 -2035.9 12.6 r2_SE_goons[5]
	SET_INFORM_RESPECTED_FRIENDS r2_SE_goons[5] 8.0 6 
	SET_CHAR_HEADING r2_SE_goons[5] 0.0
	GIVE_WEAPON_TO_CHAR r2_SE_goons[5] WEAPONTYPE_TEC9 3000 
	SET_CHAR_DECISION_MAKER r2_SE_goons[5] r2_empty_ped_decision_maker
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE r2_SE_goons[5] TRUE 
	ADD_BLIP_FOR_CHAR r2_SE_goons[5] r2_SE_goons_blips[5]
	CHANGE_BLIP_SCALE r2_SE_goons_blips[5] 1 					
	SET_CHAR_ACCURACY r2_SE_goons[5] r2_char_accuracy

	OPEN_SEQUENCE_TASK r2_seq
		TASK_PAUSE -1 200
		TASK_GO_STRAIGHT_TO_COORD -1 1906.0 -2033.5 12.5 PEDMOVE_WALK -1
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 1906.3 -2030.3 12.6 PEDMOVE_SPRINT -2  /// going to the west corner of house 2
		TASK_ACHIEVE_HEADING -1 315.5 
		TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions 
	CLOSE_SEQUENCE_TASK r2_seq
	PERFORM_SEQUENCE_TASK r2_SE_goons[5] r2_seq
	CLEAR_SEQUENCE_TASK r2_seq	

ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
r2_mc_strap_group://///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF r2_flag_cesar_in_group = 1
    IF NOT IS_GROUP_MEMBER cesar Players_Group
        REMOVE_BLIP r2_control_blip
        ADD_BLIP_FOR_CHAR cesar r2_control_blip
		SET_BLIP_AS_FRIENDLY r2_control_blip TRUE
        r2_flag_cesar_in_group = 0
    ENDIF
ENDIF

IF r2_flag_cesar_in_group = 0 
    IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer cesar 8.0 8.0 8.0 FALSE
        IF NOT IS_GROUP_MEMBER cesar Players_Group
        	MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
        	SET_GROUP_MEMBER Players_Group cesar 
		ENDIF
        REMOVE_BLIP r2_control_blip
		IF r2_group_size > 2  
			ADD_BLIP_FOR_COORD 1774.9 -1974.9 13.1 r2_control_blip 
			SET_BLIP_AS_FRIENDLY r2_control_blip FALSE
		ENDIF
        r2_flag_cesar_in_group = 1
    ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
r2_creating_climbers:///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
	IF IS_CHAR_DEAD r2_climbers[0] 
		//climber 0 - 1st set right front 
		GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
		CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1860.1 -2094.8 14.0 r2_climbers[0]
		SET_CHAR_HEADING r2_climbers[0] 187.0
		GIVE_WEAPON_TO_CHAR r2_climbers[0] WEAPONTYPE_TEC9 3000 
		SET_CHAR_DECISION_MAKER r2_climbers[0] r2_empty_ped_decision_maker
		OPEN_SEQUENCE_TASK r2_seq
			TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PAUSE -1 4000
			TASK_CLIMB -1 TRUE
			TASK_SHOOT_AT_COORD -1 1874.4 -2101.5 14.8 2000
			TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions
		CLOSE_SEQUENCE_TASK r2_seq			   
		PERFORM_SEQUENCE_TASK r2_climbers[0] r2_seq	
		CLEAR_SEQUENCE_TASK r2_seq
		ADD_BLIP_FOR_CHAR r2_climbers[0] r2_climbers_blips[0]	
		CHANGE_BLIP_SCALE r2_climbers_blips[0] 1 					
		SET_CHAR_ACCURACY r2_climbers[0] r2_char_accuracy
		ADD_ARMOUR_TO_CHAR r2_climbers[0] 50
	ENDIF

	IF IS_CHAR_DEAD r2_climbers[1] 
		//climber 1 - 1st set right middle
		GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
		CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1857.3 -2094.8 14.0 r2_climbers[1]
		SET_CHAR_HEADING r2_climbers[1] 187.0
		GIVE_WEAPON_TO_CHAR r2_climbers[1] WEAPONTYPE_TEC9 3000 
		SET_CHAR_DECISION_MAKER r2_climbers[1] r2_empty_ped_decision_maker
		TASK_PLAY_ANIM r2_climbers[1] weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
		OPEN_SEQUENCE_TASK r2_seq
			TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PAUSE -1 6000
			TASK_CLIMB -1 TRUE
			TASK_SHOOT_AT_COORD -1 1874.4 -2101.5 14.8 2000
			TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions
		CLOSE_SEQUENCE_TASK r2_seq			   
		PERFORM_SEQUENCE_TASK r2_climbers[1] r2_seq	
		CLEAR_SEQUENCE_TASK r2_seq
		ADD_BLIP_FOR_CHAR r2_climbers[1] r2_climbers_blips[1]	
		CHANGE_BLIP_SCALE r2_climbers_blips[1] 1 					
		SET_CHAR_ACCURACY r2_climbers[1] r2_char_accuracy
	ENDIF

	IF IS_CHAR_DEAD r2_climbers[2] 
		//climber 2 - 1st set right back
		GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
		CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1854.3 -2094.8 14.0 r2_climbers[2]
		SET_CHAR_HEADING r2_climbers[2] 187.0
		GIVE_WEAPON_TO_CHAR r2_climbers[2] WEAPONTYPE_TEC9 3000 
		SET_CHAR_DECISION_MAKER r2_climbers[2] r2_empty_ped_decision_maker
		TASK_PLAY_ANIM r2_climbers[2] weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
		OPEN_SEQUENCE_TASK r2_seq
			TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PAUSE -1 8000
			TASK_CLIMB -1 TRUE
			TASK_SHOOT_AT_COORD -1 1874.4 -2101.5 14.8 2000
			TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions
		CLOSE_SEQUENCE_TASK r2_seq			   
		PERFORM_SEQUENCE_TASK r2_climbers[2] r2_seq	
		CLEAR_SEQUENCE_TASK r2_seq
		ADD_BLIP_FOR_CHAR r2_climbers[2] r2_climbers_blips[2]	
		CHANGE_BLIP_SCALE r2_climbers_blips[2] 1 					
		SET_CHAR_ACCURACY r2_climbers[2] r2_char_accuracy
		ADD_ARMOUR_TO_CHAR r2_climbers[2] 50
	ENDIF

	IF IS_CHAR_DEAD r2_climbers[3] 
		//climber 3 - 1st set left front
		GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
		CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1859.1 -2108.8 14.0 r2_climbers[3]
		SET_CHAR_HEADING r2_climbers[3] 2.0
		GIVE_WEAPON_TO_CHAR r2_climbers[3] WEAPONTYPE_TEC9 3000 
		SET_CHAR_DECISION_MAKER r2_climbers[3] r2_empty_ped_decision_maker
		TASK_PLAY_ANIM r2_climbers[3] weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
		OPEN_SEQUENCE_TASK r2_seq
			TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PAUSE -1 5000
			TASK_CLIMB -1 TRUE
			TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions
		CLOSE_SEQUENCE_TASK r2_seq			   
		PERFORM_SEQUENCE_TASK r2_climbers[3] r2_seq	
		CLEAR_SEQUENCE_TASK r2_seq
		ADD_BLIP_FOR_CHAR r2_climbers[3] r2_climbers_blips[3]	
		CHANGE_BLIP_SCALE r2_climbers_blips[3] 1 					
		SET_CHAR_ACCURACY r2_climbers[3] r2_char_accuracy
	ENDIF

	IF IS_CHAR_DEAD r2_climbers[4] 
		//climber 4 - 1st set left middle
		GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
		CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1856.3 -2108.8 14.0 r2_climbers[4]
		SET_CHAR_HEADING r2_climbers[4] 2.0
		GIVE_WEAPON_TO_CHAR r2_climbers[4] WEAPONTYPE_TEC9 3000 
		SET_CHAR_DECISION_MAKER r2_climbers[4] r2_empty_ped_decision_maker
		TASK_PLAY_ANIM r2_climbers[4] weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
		OPEN_SEQUENCE_TASK r2_seq
			TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PAUSE -1 7000
			TASK_CLIMB -1 TRUE
			TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions
		CLOSE_SEQUENCE_TASK r2_seq			   
		PERFORM_SEQUENCE_TASK r2_climbers[4] r2_seq	
		CLEAR_SEQUENCE_TASK r2_seq
		ADD_BLIP_FOR_CHAR r2_climbers[4] r2_climbers_blips[4]	
		CHANGE_BLIP_SCALE r2_climbers_blips[4] 1 					
		SET_CHAR_ACCURACY r2_climbers[4] r2_char_accuracy
		ADD_ARMOUR_TO_CHAR r2_climbers[4] 50
	ENDIF

	IF IS_CHAR_DEAD r2_climbers[5] 
		//climber 5 - 1st set left back
		GENERATE_RANDOM_INT_IN_RANGE 0 2 r2_char_select_flag
		CREATE_CHAR PEDTYPE_MISSION2 r2_char_select[r2_char_select_flag] 1853.3 -2108.8 14.0 r2_climbers[5]
		SET_CHAR_HEADING r2_climbers[5] 2.0
		GIVE_WEAPON_TO_CHAR r2_climbers[5] WEAPONTYPE_TEC9 3000 
		SET_CHAR_DECISION_MAKER r2_climbers[5] r2_empty_ped_decision_maker
		TASK_PLAY_ANIM r2_climbers[5] weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
		OPEN_SEQUENCE_TASK r2_seq
			TASK_PLAY_ANIM -1 weapon_crouch PED 4.0 FALSE FALSE FALSE TRUE -1
			TASK_PAUSE -1 9000
			TASK_CLIMB -1 TRUE
			TASK_SET_CHAR_DECISION_MAKER -1 r2_ped_decisions
		CLOSE_SEQUENCE_TASK r2_seq			   
		PERFORM_SEQUENCE_TASK r2_climbers[5] r2_seq	
		CLEAR_SEQUENCE_TASK r2_seq
		ADD_BLIP_FOR_CHAR r2_climbers[5] r2_climbers_blips[5]	
		CHANGE_BLIP_SCALE r2_climbers_blips[5] 1 					
		SET_CHAR_ACCURACY r2_climbers[5] r2_char_accuracy
	ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
r2_overall_dialogue:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF r2_speech_goals = 1 //Initial convo outside sweets
OR r2_speech_goals = 4 //cutscene with OG's
OR r2_speech_goals = 6 //cutscene in alleyway
OR r2_speech_goals = 8 //cutscene checking on how hazer is
OR r2_speech_goals = 9 // Behind us!
	IF r2_speech_control_flag < r2_last_label
		GOSUB r2_loading_dialogue
		GOSUB r2_playing_dialogue
		GOSUB r2_finishing_dialogue  
	ELSE
		r2_speech_goals = 0
	ENDIF
ENDIF

IF r2_speech_goals = 10 // Let's fucking finish this!
OR r2_speech_goals = 11 // This is a Vagos neighborhood now!
OR r2_speech_goals = 12 // Torch those Aztecas!
OR r2_speech_goals = 13 //final cutscene
	IF r2_speech_control_flag < r2_last_label
		GOSUB r2_loading_dialogue
		GOSUB r2_playing_dialogue
		GOSUB r2_finishing_dialogue  
	ELSE
		r2_speech_goals = 0
	ENDIF
ENDIF

IF r2_speech_goals = 5 //dialogue where cesar is shouting about killing the enemy in the huts
OR r2_speech_goals = 7 // Hazer!
	IF r2_speech_control_flag < r2_last_label
		GOSUB r2_loading_dialogue
		GOSUB r2_playing_dialogue
		IF NOT IS_CHAR_DEAD cesar
			GOSUB r2_finishing_dialogue  
		ELSE
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $r2_print_label[r2_speech_control_flag] 
			r2_slot1 = 0
			r2_slot2 = 0
		ENDIF
	ELSE
		r2_speech_goals = 0
	ENDIF
ENDIF


IF r2_speech_goals = 9 //Behind us!
	IF r2_speech_control_flag < r2_last_label
		GOSUB r2_loading_dialogue
		GOSUB r2_playing_dialogue
		IF NOT IS_CHAR_DEAD r2_launcher
			GOSUB r2_finishing_dialogue  
		ELSE
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT $r2_print_label[r2_speech_control_flag] 
			r2_slot1 = 0
			r2_slot2 = 0
		ENDIF
	ELSE
		r2_speech_goals = 0
	ENDIF
ENDIF

IF r2_goals = 0
	IF r2_control_flag > 2
		IF IS_GROUP_MEMBER cesar Players_Group
		

			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D cesar scplayer 10.0 10.0 8.0 FALSE
	            IF IS_CHAR_ON_FOOT scplayer
    	        AND IS_CHAR_ON_FOOT cesar
					IF r2_speech_goals = 2 //convo about tenpenny bringing the streets to its knees
					OR r2_speech_goals = 3 //convo of cesar asking carl if it's ok to marry kendl 
						IF r2_speech_control_flag < r2_last_label
							GOSUB r2_loading_dialogue
							GOSUB r2_playing_dialogue
							IF NOT IS_CHAR_DEAD cesar
								GOSUB r2_finishing_dialogue  
							ELSE
								CLEAR_MISSION_AUDIO 1
								CLEAR_MISSION_AUDIO 2
								CLEAR_THIS_PRINT $r2_print_label[r2_speech_control_flag] 
								r2_slot1 = 0
								r2_slot2 = 0
							ENDIF
						ELSE
							r2_speech_goals = 0
						ENDIF
					ENDIF
	            ENDIF   
                IF IS_CHAR_IN_ANY_CAR scplayer
                AND IS_CHAR_IN_ANY_CAR cesar
					IF r2_speech_goals = 2 //convo about tenpenny bringing the streets to its knees
					OR r2_speech_goals = 3 //convo of cesar asking carl if it's ok to marry kendl 
						IF r2_speech_control_flag < r2_last_label
							GOSUB r2_loading_dialogue
							GOSUB r2_playing_dialogue
							IF NOT IS_CHAR_DEAD cesar
								GOSUB r2_finishing_dialogue  
							ELSE
								CLEAR_MISSION_AUDIO 1
								CLEAR_MISSION_AUDIO 2
								CLEAR_THIS_PRINT $r2_print_label[r2_speech_control_flag] 
								r2_slot1 = 0
								r2_slot2 = 0
							ENDIF
						ELSE
							r2_speech_goals = 0
						ENDIF
					ENDIF
                ENDIF
			ENDIF

			/*		
			IF r2_speech_goals = 2 //convo about tenpenny bringing the streets to its knees
			OR r2_speech_goals = 3 //convo of cesar asking carl if it's ok to marry kendl 
				IF r2_speech_control_flag < r2_last_label
					GOSUB r2_loading_dialogue
					GOSUB r2_playing_dialogue
					IF NOT IS_CHAR_DEAD cesar
						GOSUB r2_finishing_dialogue  
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $r2_print_label[r2_speech_control_flag] 
						r2_slot1 = 0
						r2_slot2 = 0
					ENDIF
				ELSE
					r2_speech_goals = 0
				ENDIF
			ENDIF
			*/
		ELSE
			IF r2_speech_goals < 14 
				IF r2_speech_control_flag < r2_last_label
					r2_speech_control_flag ++
				ENDIF
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				CLEAR_THIS_PRINT $r2_print_label[r2_speech_control_flag]
				CLEAR_PRINTS
				r2_storing_speech_goals_number = r2_speech_goals 
				r2_storing_speech_control_number = r2_speech_control_flag
				r2_speech_goals = 14
				GENERATE_RANDOM_INT_IN_RANGE 0 4 r2_speech_control_flag
				r2_random_last_label = r2_speech_control_flag + 1 
				GOSUB r2_dialogue_setup
			ENDIF
		ENDIF

		IF r2_speech_goals = 14 //cesar is out of the group
			IF NOT IS_GROUP_MEMBER cesar Players_Group
				IF r2_speech_control_flag < r2_last_label
					GOSUB r2_loading_dialogue
					GOSUB r2_playing_dialogue
					IF NOT IS_CHAR_DEAD cesar
						GOSUB r2_finishing_dialogue  
					ELSE
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_THIS_PRINT $r2_print_label[r2_speech_control_flag] 
						r2_slot1 = 0
						r2_slot2 = 0
					ENDIF
				ELSE
					PRINT_NOW ( R2_12 ) 7000 1 // You have left Cesar behind.	
					r2_speech_goals = 15
				ENDIF
			ELSE
				PRINT_NOW ( R2_12 ) 7000 1 // You have left Cesar behind.
				r2_speech_goals = 15	
			ENDIF
		ENDIF

		IF r2_speech_goals = 15 //cesar has been out of the group and has returned
			IF IS_GROUP_MEMBER cesar Players_Group 
				r2_speech_goals = 16
				r2_speech_control_flag = 0
				CLEAR_PRINTS
				//GOSUB r2_dialogue_setup
			ENDIF
		ENDIF

		IF r2_speech_goals = 16 //cesar is back in group
			IF IS_GROUP_MEMBER cesar Players_Group 	
				timerb = 0
				r2_speech_goals = r2_storing_speech_goals_number
				r2_speech_control_flag = r2_storing_speech_control_number
				GOSUB r2_dialogue_setup
				IF r2_storing_speech_goals_number = 0
					IF r2_group_size < 3		
						PRINT_NOW ( R2_01 ) 60000 1 // Get a couple of Families to join your gang.
					ELSE
						PRINT_NOW ( R2_13 ) 7000 1 // Go and meet up with the veterano's at Unity Station.
					ENDIF	
					timerb = 0
				ENDIF
			ELSE
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				CLEAR_THIS_PRINT $r2_print_label[r2_speech_control_flag]
				r2_speech_goals = 14
				GENERATE_RANDOM_INT_IN_RANGE 0 4 r2_speech_control_flag
				r2_random_last_label = r2_speech_control_flag + 1 
				GOSUB r2_dialogue_setup
			ENDIF
		ENDIF
	ENDIF	
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
r2_dialogue_setup://////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF r2_speech_goals = 1
	$r2_print_label[0] = &ROT2_AA // Ok, so what's the plan?		
	$r2_print_label[1] = &ROT2_AB // We're going to meet three of my veterano's over at Unity Station.		
	$r2_print_label[2] = &ROT2_AD // Three, is that all?		
	$r2_print_label[3] = &ROT2_AE // Ok, I'm gonna get a couple Families boys to come along too.		

	r2_audio_label[0] = SOUND_ROT2_AA 
	r2_audio_label[1] = SOUND_ROT2_AB 
	r2_audio_label[2] = SOUND_ROT2_AD 
	r2_audio_label[3] = SOUND_ROT2_AE 
	r2_last_label = 4
ENDIF

IF r2_speech_goals = 2
	$r2_print_label[0] = &ROT2_BA // Shit's real serious, man, look at the streets, eh.
	$r2_print_label[1] = &ROT2_BB // Yeah, we better watch ourselves.
	$r2_print_label[2] = &ROT2_BC // Don't want to get caught on Ballas turf while this shit's going down.
	$r2_print_label[3] = &ROT2_BD // Man, Tenpenny's brought South Central to it's knees...

	r2_audio_label[0] = SOUND_ROT2_BA 
	r2_audio_label[1] = SOUND_ROT2_BB 
	r2_audio_label[2] = SOUND_ROT2_BC 
	r2_audio_label[3] = SOUND_ROT2_BD 
	r2_last_label = 4
ENDIF

IF r2_speech_goals = 3
	$r2_print_label[0] = &ROT2_CA // While we here, I, eerr, I have a question to ask you.
	$r2_print_label[1] = &ROT2_CB // Yeah? What?
	$r2_print_label[2] = &ROT2_CC // Well it's... it's personal.
	$r2_print_label[3] = &ROT2_CD // Come on, man, we're brothers, you and me.
	$r2_print_label[4] = &ROT2_CE // You can ask me anything.
	$r2_print_label[5] = &ROT2_CF // Ok, here goes.
	$r2_print_label[6] = &ROT2_CG // I want to ask Kendl the question.
	$r2_print_label[7] = &ROT2_CH // What's the problem?
	$r2_print_label[8] = &ROT2_CJ // Call her, dude, here, use my phone.
	$r2_print_label[9] = &ROT2_CK // Noo, holmes, THE question!
	$r2_print_label[10] = &ROT2_CL // What? Oh shit, you mean POP THE question. 
	$r2_print_label[11] = &ROT2_CM // Well, I'm ok with that, I appreciate you asking my permission and shit, but -
	$r2_print_label[12] = &ROT2_CN // Si. I know you cool.
	$r2_print_label[13] = &ROT2_CO // Is Sweet that is the problem, could you talk to him?
	$r2_print_label[14] = &ROT2_CP // Sure thing, Cesar, I'll talk to him.
	$r2_print_label[15] = &ROT2_CQ // Thank you, CJ, that means a lot to me.

	r2_audio_label[0] = SOUND_ROT2_CA 
	r2_audio_label[1] = SOUND_ROT2_CB 
	r2_audio_label[2] = SOUND_ROT2_CC 
	r2_audio_label[3] = SOUND_ROT2_CD 
	r2_audio_label[4] = SOUND_ROT2_CE 
	r2_audio_label[5] = SOUND_ROT2_CF 
	r2_audio_label[6] = SOUND_ROT2_CG 
	r2_audio_label[7] = SOUND_ROT2_CH 
	r2_audio_label[8] = SOUND_ROT2_CJ 
	r2_audio_label[9] = SOUND_ROT2_CK 
	r2_audio_label[10] = SOUND_ROT2_CL 
	r2_audio_label[11] = SOUND_ROT2_CM 
	r2_audio_label[12] = SOUND_ROT2_CN 
	r2_audio_label[13] = SOUND_ROT2_CO 
	r2_audio_label[14] = SOUND_ROT2_CP 
	r2_audio_label[15] = SOUND_ROT2_CQ 
	r2_last_label = 16
ENDIF
	
IF r2_speech_goals = 4
	$r2_print_label[0] = &ROT2_DA // Those Vagos, man, I'm gonna gut those cacos.
	$r2_print_label[1] = &ROT2_DB // Raspalo hasta el hueso!

	$r2_print_label[2] = &ROT2_DC // Hey, carneles, how my Guerreros, eh?
	$r2_print_label[3] = &ROT2_DD // Cesar! And you must be CJ.

	$r2_print_label[4] = &ROT2_DE // Cesar says you cool, so we cool, holmes.

	$r2_print_label[5] = &ROT2_DF // Ok. We will have to work our way through this neighborhood to get to my house. 

	//$r2_print_label[7] = &ROT2_DH // We will approach from the rear alleyway, ok?
	
	$r2_print_label[8] = &ROT2_DJ // If we stick together those Vagos bendejos won't stand a chance!
	$r2_print_label[9] = &ROT2_DK // Watch each others' backs, amigos.
	$r2_print_label[10] = &ROT2_DL // Hasta le muerte!
	$r2_print_label[11] = &ROT2_DM // HASTA LE MUERTE!
	
	r2_audio_label[0] = SOUND_ROT2_DA 
	r2_audio_label[1] = SOUND_ROT2_DB 
	r2_audio_label[2] = SOUND_ROT2_DC 
	r2_audio_label[3] = SOUND_ROT2_DD 
	r2_audio_label[4] = SOUND_ROT2_DE 
	r2_audio_label[5] = SOUND_ROT2_DF 
	//r2_audio_label[7] = SOUND_ROT2_DH 
	r2_audio_label[8] = SOUND_ROT2_DJ 
	r2_audio_label[9] = SOUND_ROT2_DK 
	r2_audio_label[10] = SOUND_ROT2_DL 
	r2_audio_label[11] = SOUND_ROT2_DM 
	r2_last_label = r2_random_last_label
ENDIF

IF r2_speech_goals = 5
	$r2_print_label[0] = &ROT2_EA // That's the last of them, let's move out!	DONE

	//$r2_print_label[1] = &ROT2_EB // Everybody ok? Let's hit 'em! 
	
	//$r2_print_label[2] = &ROT2_EC // Vagos fools didn't stand a chance!
	
	$r2_print_label[3] = &ROT2_ED // Quickly, go! GO!  							DONE
	$r2_print_label[4] = &ROT2_EE // Follow me!									DONE   
	$r2_print_label[5] = &ROT2_EF // Keep close!								DONE
	$r2_print_label[6] = &ROT2_EG // Keep together!								DONE
	$r2_print_label[7] = &ROT2_EH // Stay close.								DONE
	$r2_print_label[8] = &ROT2_EJ // Keep it tight!								DONE
	
	$r2_print_label[11] = &ROT2_EM // Northside Vagos assholes!	  				DONE
	
	$r2_print_label[12] = &ROT2_EN // Up ahead!
	
	//$r2_print_label[13] = &ROT2_EO // Nearly there!

	//$r2_print_label[14] = &ROT2_EP // Careful, we are close!
	
	//$r2_print_label[15] = &ROT2_EQ // Behind us!
	
	//$r2_print_label[16] = &ROT2_ER // Watch our backs!
	
	//$r2_print_label[17] = &ROT2_ES // Watch your backs!									 
	
	r2_audio_label[0] = SOUND_ROT2_EA 
	//r2_audio_label[1] = SOUND_ROT2_EB 
	//r2_audio_label[2] = SOUND_ROT2_EC 
	r2_audio_label[3] = SOUND_ROT2_ED 
	r2_audio_label[4] = SOUND_ROT2_EE 
	r2_audio_label[5] = SOUND_ROT2_EF 
	r2_audio_label[6] = SOUND_ROT2_EG 
	r2_audio_label[7] = SOUND_ROT2_EH 
	r2_audio_label[8] = SOUND_ROT2_EJ 
	r2_audio_label[11] = SOUND_ROT2_EM 
	r2_audio_label[12] = SOUND_ROT2_EN 
	//r2_audio_label[13] = SOUND_ROT2_EO 
	//r2_audio_label[14] = SOUND_ROT2_EP 
	//r2_audio_label[15] = SOUND_ROT2_EQ 
	//r2_audio_label[16] = SOUND_ROT2_ER 
	//r2_audio_label[17] = SOUND_ROT2_ES 
	r2_last_label = r2_random_last_label
ENDIF

	//$r2_print_label[9] = &ROT2_EK // Vagos boys!
	//$r2_print_label[10] = &ROT2_EL // LSV's!
	//$r2_print_label[18] = &ROT2_EV // Watch that alleyway!									 
	//$r2_print_label[19] = &ROT2_EX // Vagos to our left!
	//$r2_print_label[20] = &ROT2_EY // Northside on our right!
	//$r2_print_label[21] = &ROT2_ET // On the rooftops!
	//$r2_print_label[22] = &ROT2_EU // They're on the roofs!
	

IF r2_speech_goals = 6
	$r2_print_label[0] = &ROT2_FA // That was the easy bit, eh.
	$r2_print_label[1] = &ROT2_FB // Now we go into the viper's nest.
	$r2_print_label[2] = &ROT2_FC // This is where it gets tough.
	$r2_print_label[3] = &ROT2_FD // Luckily we have a little surprise up our sleeves, eh, Sunny!
	$r2_print_label[4] = &ROT2_FE // That's a rocket launcher! We'll bring the Natiional Guard  down on us! 
	$r2_print_label[5] = &ROT2_FG // Look around you, CJ, the whole city is a war zone!
	$r2_print_label[6] = &ROT2_FH // C'mon, I want to take my house back!

	r2_audio_label[0] = SOUND_ROT2_FA 
	r2_audio_label[1] = SOUND_ROT2_FB 
	r2_audio_label[2] = SOUND_ROT2_FC 
	r2_audio_label[3] = SOUND_ROT2_FD 
	r2_audio_label[4] = SOUND_ROT2_FE 
	r2_audio_label[5] = SOUND_ROT2_FG 
	r2_audio_label[6] = SOUND_ROT2_FH 
	r2_last_label = r2_random_last_label
ENDIF

IF r2_speech_goals = 7
	$r2_print_label[0] = &ROT2_GA // Hazer!

	r2_audio_label[0] = SOUND_ROT2_GA 
	r2_last_label = 1
ENDIF
	
IF r2_speech_goals = 8
	$r2_print_label[0] = &ROT2_GB // Shit, Hazer...
	$r2_print_label[1] = &ROT2_GC // He is pretty bad, Cesar...
	$r2_print_label[2] = &ROT2_GD // Heads up! More Vagos!
	$r2_print_label[3] = &ROT2_EW // They're coming over the walls!						 

	r2_audio_label[0] = SOUND_ROT2_GB 
	r2_audio_label[1] = SOUND_ROT2_GC 
	r2_audio_label[2] = SOUND_ROT2_GD 
	r2_audio_label[3] = SOUND_ROT2_EW 
	r2_last_label = 4
ENDIF

IF r2_speech_goals = 9
	$r2_print_label[0] = &ROT2_HA // Behind us!
	$r2_print_label[1] = &ROT2_HB // More Vagos Behind us!
	$r2_print_label[2] = &ROT2_HC // Northsiders behind us!

	r2_audio_label[0] = SOUND_ROT2_HA 
	r2_audio_label[1] = SOUND_ROT2_HB 
	r2_audio_label[2] = SOUND_ROT2_HC 
	r2_last_label = r2_random_last_label
ENDIF

IF r2_speech_goals = 10
	$r2_print_label[0] = &ROT2_JA // Let's fucking finish this!
	$r2_print_label[1] = &ROT2_JB // I'm with you, man, let's take 'em!

	r2_audio_label[0] = SOUND_ROT2_JA 
	r2_audio_label[1] = SOUND_ROT2_JB 
	r2_last_label = 2
ENDIF

IF r2_speech_goals = 11
	$r2_print_label[0] = &ROT2_KA // This is a Vagos neighborhood now!
	$r2_print_label[1] = &ROT2_KB // Vagos rule this varrio now - Aztecas are no more!
	$r2_print_label[2] = &ROT2_KC // Find a new home, assholes, Vagos own this 'hood!

	r2_audio_label[0] = SOUND_ROT2_KA 
	r2_audio_label[1] = SOUND_ROT2_KB 
	r2_audio_label[2] = SOUND_ROT2_KC 
	r2_last_label = r2_random_last_label
ENDIF
	
IF r2_speech_goals = 12
	$r2_print_label[0] = &ROT2_LA // Torch those Aztecas!
	$r2_print_label[1] = &ROT2_LB // Burn them!
	$r2_print_label[2] = &ROT2_LC // Burn, Aztecas, burn!

	r2_audio_label[0] = SOUND_ROT2_LA 
	r2_audio_label[1] = SOUND_ROT2_LB 
	r2_audio_label[2] = SOUND_ROT2_LC 
	r2_last_label = r2_random_last_label
ENDIF
	
IF r2_speech_goals = 13
	$r2_print_label[0] = &ROT2_MA // That's the last of 'em.
	$r2_print_label[1] = &ROT2_MB // How is Hazer?
	$r2_print_label[2] = &ROT2_MC // We need to get him to a hospital.
	$r2_print_label[3] = &ROT2_MD // I'll take him.

	$r2_print_label[4] = &ROT2_ME // CJ, you have done more than enough.
	$r2_print_label[5] = &ROT2_MF // You should get back to Grove.
	$r2_print_label[6] = &ROT2_MG // Sure thing, esse, I'll see you after all this has settled.
	$r2_print_label[7] = &ROT2_MH // Thank you, CJ and good luck, my friend.

	r2_audio_label[0] = SOUND_ROT2_MA 
	r2_audio_label[1] = SOUND_ROT2_MB 
	r2_audio_label[2] = SOUND_ROT2_MC 
	r2_audio_label[3] = SOUND_ROT2_MD 
	r2_audio_label[4] = SOUND_ROT2_ME 
	r2_audio_label[5] = SOUND_ROT2_MF 
	r2_audio_label[6] = SOUND_ROT2_MG 
	r2_audio_label[7] = SOUND_ROT2_MH 
	r2_last_label = r2_random_last_label
ENDIF

IF r2_speech_goals = 14
	$r2_print_label[0] = &CESX_BA // Wait up, CJ!
	$r2_print_label[1] = &CESX_BB // Hang ten, CJ!
	$r2_print_label[2] = &CESX_BC // Hold up!
	$r2_print_label[3] = &CESX_BD // Slow down, Carl!

	r2_audio_label[0] = SOUND_CESX_BA 
	r2_audio_label[1] = SOUND_CESX_BB 
	r2_audio_label[2] = SOUND_CESX_BC 
	r2_audio_label[3] = SOUND_CESX_BD 
 	r2_last_label = r2_random_last_label 
ENDIF

r2_slot_load = r2_speech_control_flag
r2_slot1 = 0
r2_slot2 = 0
r2_play_which_slot = 1
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
r2_loading_dialogue:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
IF r2_slot_load < r2_last_label 
	//slot 1
	IF r2_slot1 = 0
		LOAD_MISSION_AUDIO 1 r2_audio_label[r2_slot_load] 
		r2_slot_load ++ 
		r2_slot1 = 1
	ENDIF

	//slot 2		    
	IF r2_slot2 = 0
		LOAD_MISSION_AUDIO 2 r2_audio_label[r2_slot_load] 
		r2_slot_load ++ 
		r2_slot2 = 1
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
r2_playing_dialogue:////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//slot 1
IF r2_play_which_slot = 1 
	IF r2_slot1 = 1
		IF HAS_MISSION_AUDIO_LOADED 1	 
			PLAY_MISSION_AUDIO 1
			PRINT_NOW ( $r2_print_label[r2_speech_control_flag] ) 4500 1 
			r2_slot1 = 2
		ENDIF
	ENDIF
ENDIF

//slot 2
IF r2_play_which_slot = 2 
	IF r2_slot2 = 1
		IF HAS_MISSION_AUDIO_LOADED 2	 
			PLAY_MISSION_AUDIO 2
			PRINT_NOW ( $r2_print_label[r2_speech_control_flag] ) 4500 1 
			r2_slot2 = 2
		ENDIF
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
r2_finishing_dialogue://////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
//slot 1
IF r2_slot1 = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		CLEAR_THIS_PRINT $r2_print_label[r2_speech_control_flag]
		r2_speech_control_flag ++		
		r2_play_which_slot = 2
		r2_slot1 = 0
	ENDIF
ENDIF

//slot 2
IF r2_slot2 = 2
	IF HAS_MISSION_AUDIO_FINISHED 2
		CLEAR_THIS_PRINT $r2_print_label[r2_speech_control_flag]
		r2_speech_control_flag ++		
		r2_play_which_slot = 1
		r2_slot2 = 0
	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
}



























