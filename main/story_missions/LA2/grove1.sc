MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// *************************************** Grove 1 *****************************************
// ********************************** Beat Down on B-Dup ***********************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME grove1

// Mission start stuff

GOSUB mission_start_grove1

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_grove1_failed
ENDIF

GOSUB mission_cleanup_grove1

MISSION_END

{
 
// Variables for mission 
// general stuff
LVAR_INT tough_decisionmaker_grove1

LVAR_INT tough_duck_dm_grove1

LVAR_INT dbup_house_blip_grove1

// players group stuff
LVAR_INT player_had_group_warning_grove1

// sweet stuff
LVAR_INT sweet_grove1 sweet_blip_grove1

VAR_INT sweets_health_grove1 // This has to be declared as a GLOBAL VAR as it is used in a counter onscreen!!

LVAR_INT park_blip_grove1

// bdups house guards
LVAR_INT counter_dbup_guards_dead

LVAR_INT bdup_guard1_grove1 bdup_guard1_blip_grove1 bdup_guard1_dead_grove1

LVAR_INT bdup_guard2_grove1 bdup_guard2_blip_grove1 bdup_guard2_dead_grove1

LVAR_INT bdup_guard3_grove1 bdup_guard3_blip_grove1 bdup_guard3_dead_grove1

LVAR_INT bdup_guard4_grove1 bdup_guard4_blip_grove1 bdup_guard4_dead_grove1

LVAR_INT bdup_guard5_grove1 bdup_guard5_blip_grove1 bdup_guard5_dead_grove1 guard5_seq_grove1

LVAR_INT bdup_guard6_grove1 bdup_guard6_blip_grove1 bdup_guard6_dead_grove1 guard6_seq_grove1 

LVAR_INT bdup_guard7_grove1 bdup_guard7_blip_grove1 bdup_guard7_dead_grove1 guard7_seq_grove1

LVAR_INT bdup_guard8_grove1 bdup_guard8_blip_grove1 bdup_guard8_dead_grove1 guard8_seq_grove1

LVAR_INT flag_kill_player_grove1 // used for when the player is playing the dbup house part

LVAR_INT dbup_guys_got_ai_grove1 // used to set dbup and his crew to kill player

// big bear
LVAR_INT big_bear_grove1

// flag for flying vehicles
LVAR_INT flag_has_flying_message_grove1

// flag to say wheather player watched cutscene or not
LVAR_INT cut_watched_grove1

// car that is used for checks for cutscenes
LVAR_INT car2_grove1 bdup_car_grove1 

// gang zone stuff
LVAR_INT gang_strenth1_grove1 gang_strenth2_grove1 gang_strenth3_grove1 gang_strenth4_grove1 flag_player_in_area_grove1

LVAR_INT flag_player_taken_terr_grove1
 
// car check stuff
LVAR_INT stored_car_grove1 max_passenger_grove1 passenger_count_grove1 no_of_space_grove1 had_room_message_grove1

// flag to tell player gang war is off
LVAR_INT flag_gang_war_off_grove1

LVAR_INT old_house_blip_grove1 dummy_car_grove1

// stored gang war values
LVAR_INT stored_nmex_strength_grove1 stored_smex_strength_grove1

// Audio stuff
LVAR_INT grove1_index grove1_audio_is_playing grove1_cutscene_flag grove1_chat_switch grove1_audio_chat[17]

VAR_TEXT_LABEL $grove1_chat[17]

LVAR_INT flag_start_audio1_grove1 flag_start_audio2_grove1 flag_start_audio3_grove1

LVAR_INT flag_print_on_grove1 // used to remove a print after the audio for no space or a plane is done

// player gang strenth stuff
LVAR_INT player_gang_strenth_grove1

// Sweet audio for room in vehicles
LVAR_INT room_audio_playing_grove1

// car wheel check
LVAR_INT flag_car_on_wheels_grove1

LVAR_INT guard1_attacking_coord_grove1 guard1_attacking_player_grove1 guard2_attacking_coord_grove1 guard2_attacking_player_grove1
LVAR_INT guard3_attacking_coord_grove1 guard3_attacking_player_grove1 guard4_attacking_coord_grove1 guard4_attacking_player_grove1
LVAR_INT guard5_attacking_coord_grove1 guard5_attacking_player_grove1 guard6_attacking_coord_grove1 guard6_attacking_player_grove1
LVAR_INT guard7_attacking_coord_grove1 guard7_attacking_player_grove1 guard8_attacking_coord_grove1 guard8_attacking_player_grove1

// ************************************* SETS UP THE AUDIO FOR THE MISSIO *************************

grove1_chat_switch:

SWITCH grove1_chat_switch		   

	CONST_INT GROVE1_CHAT1 0
	CONST_INT GROVE1_CHAT2 1
	CONST_INT GROVE1_CHAT3 2
	CONST_INT GROVE1_CHAT4 3
			
	CASE GROVE1_CHAT1

		$grove1_chat[0] = &GRO1_FA	//What were you thinking back there, bro?
		$grove1_chat[1] = &GRO1_FB	//I’m tired, man, real tired.
		$grove1_chat[2] = &GRO1_FC	//Tired of putting the work in and still shit don’t get better.
		$grove1_chat[3] = &GRO1_FD	//Tired of seeing my family fall apart.
		$grove1_chat[4] = &GRO1_FE	//Sweet, man, you got more heart than that.
		$grove1_chat[5] = &GRO1_FF	//Sure things are screwed up now, but we fittin’ to turn a corner, man.
		$grove1_chat[6] = &GRO1_FG	//The day is comin’ when the Johnson family will be at the top. 
		$grove1_chat[7] = &GRO1_FH	//And it’s coming real soon.
		$grove1_chat[8] = &GRO1_FJ	//I hear you, CJ, you’re there for us, I know that.
				
		grove1_audio_chat[0] = SOUND_GRO1_FA  //What were you thinking back there, bro?
		grove1_audio_chat[1] = SOUND_GRO1_FB  //I’m tired, man, real tired.
		grove1_audio_chat[2] = SOUND_GRO1_FC  //Tired of putting the work in and still shit don’t get better.
		grove1_audio_chat[3] = SOUND_GRO1_FD  //Tired of seeing my family fall apart.
		grove1_audio_chat[4] = SOUND_GRO1_FE  //Sweet, man, you got more heart than that.
		grove1_audio_chat[5] = SOUND_GRO1_FF  //Sure things are screwed up now, but we fittin’ to turn a corner, man.
		grove1_audio_chat[6] = SOUND_GRO1_FG  //The day is comin’ when the Johnson family will be at the top. 
		grove1_audio_chat[7] = SOUND_GRO1_FH  //And it’s coming real soon.
		grove1_audio_chat[8] = SOUND_GRO1_FJ  //I hear you, CJ, you’re there for us, I know that.
		
		cell_index_end = 8
	BREAK


	CASE GROVE1_CHAT2

		$grove1_chat[0] = &GRO1_JA	//Oh man, Glen Park!
		$grove1_chat[1] = &GRO1_JB	//Heart of Kilo Trays country, dude.
		$grove1_chat[2] = &GRO1_JC	//Fuck it, I’m down, and that fool had it coming too long.
		$grove1_chat[3] = &GRO1_JD	//We’ll take the whole neighbourhood apart!
		$grove1_chat[4] = &GRO1_JE	//Word. Let’s roll.
		 
		grove1_audio_chat[0] = SOUND_GRO1_JA	//Oh man, Glen Park!
		grove1_audio_chat[1] = SOUND_GRO1_JB	//Heart of Kilo Trays country, dude.
		grove1_audio_chat[2] = SOUND_GRO1_JC	//Fuck it, I’m down, and that fool had it coming too long.
		grove1_audio_chat[3] = SOUND_GRO1_JD	//We’ll take the whole neighbourhood apart!
		grove1_audio_chat[4] = SOUND_GRO1_JE	//Word. Let’s roll.

		cell_index_end = 4
	BREAK

	CASE GROVE1_CHAT3

		$grove1_chat[0] = &GRO1_LA	//That’s the neighbourhood sewn up!
		$grove1_chat[1] = &GRO1_LB	//No sign of B Dup though, dog.
		$grove1_chat[2] = &GRO1_LC	//Yo, check that place over there.
		$grove1_chat[3] = &GRO1_LD	//That’s it, I know it!
		
		grove1_audio_chat[0] = SOUND_GRO1_LA	//That’s the neighbourhood sewn up!
		grove1_audio_chat[1] = SOUND_GRO1_LB	//No sign of B Dup though, dog.
		grove1_audio_chat[2] = SOUND_GRO1_LC	//Yo, check that place over there.
		grove1_audio_chat[3] = SOUND_GRO1_LD	//That’s it, I know it!
		
		cell_index_end = 3

	BREAK

	CASE GROVE1_CHAT4

		$grove1_chat[0] = &GRO1_KA  //Hit those Ballas hard!
		$grove1_chat[1] = &GRO1_KB  //Johnson boys rollin’ through!
		
		grove1_audio_chat[0] = SOUND_GRO1_KA  //Hit those Ballas hard!
		grove1_audio_chat[1] = SOUND_GRO1_KB  //Johnson boys rollin’ through!
	
		cell_index_end = 1
		
	BREAK

ENDSWITCH

RETURN
				   
// **************************************** Mission Start **********************************

mission_start_grove1:

flag_player_on_mission = 1

// gang zone stuff
gang_strenth1_grove1 = 50

gang_strenth2_grove1 = 50

gang_strenth3_grove1 = 50

gang_strenth4_grove1 = 50

flag_player_in_area_grove1 = 0

// sweets stuff
sweets_health_grove1 = 1000

// players group stuff
player_had_group_warning_grove1 = 0

// bdups house guards
counter_dbup_guards_dead = 0

bdup_guard1_dead_grove1 = 0

bdup_guard2_dead_grove1 = 0

bdup_guard3_dead_grove1 = 0

bdup_guard4_dead_grove1 = 0

bdup_guard5_dead_grove1 = 0

bdup_guard6_dead_grove1 = 0

bdup_guard7_dead_grove1 = 0

bdup_guard8_dead_grove1 = 0

flag_kill_player_grove1 = 0

dbup_guys_got_ai_grove1 = 0

// flaying vehicle message
flag_has_flying_message_grove1 = 0

// flag for watching cut
cut_watched_grove1 = 0

// Territory stuff
flag_player_taken_terr_grove1 = 0

// car variable stuff
max_passenger_grove1 = 0

passenger_count_grove1 = 0 

no_of_space_grove1 = 0

had_room_message_grove1 = 0

flag_gang_war_off_grove1 = 0

// audio stuff
flag_start_audio1_grove1 = 0
flag_start_audio2_grove1 = 0 
flag_start_audio3_grove1 = 0

flag_print_on_grove1 = 0

// gang war 
stored_nmex_strength_grove1 = 0
stored_smex_strength_grove1 = 0
player_gang_strenth_grove1 = 0

// sweet room message for vehicles
room_audio_playing_grove1 = 0

// car wheel check
flag_car_on_wheels_grove1 = 0

guard1_attacking_coord_grove1 = 0
guard1_attacking_player_grove1 = 0 
guard2_attacking_coord_grove1 = 0 
guard2_attacking_player_grove1 = 0
guard3_attacking_coord_grove1 = 0 
guard3_attacking_player_grove1 = 0 
guard4_attacking_coord_grove1 = 0 
guard4_attacking_player_grove1 = 0
guard5_attacking_coord_grove1 = 0 
guard5_attacking_player_grove1 = 0 
guard6_attacking_coord_grove1 = 0 
guard6_attacking_player_grove1 = 0
guard7_attacking_coord_grove1 = 0 
guard7_attacking_player_grove1 = 0 
guard8_attacking_coord_grove1 = 0 
guard8_attacking_player_grove1 = 0

REGISTER_MISSION_GIVEN

LOAD_MISSION_TEXT GROVE1

// ****************************************START OF CUTSCENE********************************

WAIT 0

SET_AREA_VISIBLE 3

LOAD_CUTSCENE GROVE1A
 
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

SET_AREA_VISIBLE 0

// *****************************************END OF CUTSCENE*********************************
SET_SCRIPT_LIMIT_TO_GANG_SIZE 5

// stuff to fool compiler
GOTO fool_compiler_stuff 
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 sweet_blip_grove1

	OPEN_SEQUENCE_TASK guard5_seq_grove1 
		TASK_STAY_IN_SAME_PLACE -1 TRUE
		TASK_KILL_CHAR_ON_FOOT -1 TRUE
	CLOSE_SEQUENCE_TASK guard5_seq_grove1

	OPEN_SEQUENCE_TASK guard6_seq_grove1 
		TASK_STAY_IN_SAME_PLACE -1 TRUE
		TASK_KILL_CHAR_ON_FOOT -1 TRUE
	CLOSE_SEQUENCE_TASK guard6_seq_grove1

	OPEN_SEQUENCE_TASK guard7_seq_grove1 
		TASK_STAY_IN_SAME_PLACE -1 TRUE
		TASK_KILL_CHAR_ON_FOOT -1 TRUE
	CLOSE_SEQUENCE_TASK guard7_seq_grove1

	OPEN_SEQUENCE_TASK guard8_seq_grove1 
		TASK_STAY_IN_SAME_PLACE -1 TRUE
		TASK_KILL_CHAR_ON_FOOT -1 TRUE
	CLOSE_SEQUENCE_TASK guard8_seq_grove1 

fool_compiler_stuff:

// Decision Makers
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH tough_decisionmaker_grove1

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH tough_duck_dm_grove1
ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE tough_duck_dm_grove1 EVENT_SHOT_FIRED_WHIZZED_BY TASK_SIMPLE_DUCK_WHILE_SHOTS_WHIZZING 0.0 100.0 100.0 100.0 FALSE TRUE

LOAD_SPECIAL_CHARACTER 1 SWEET
REQUEST_MODEL BALLAS3
REQUEST_MODEL BALLAS1
REQUEST_MODEL AK47
REQUEST_MODEL INFERNUS

LOAD_MISSION_AUDIO 2 SOUND_GRO1_GA

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1 
OR NOT HAS_MODEL_LOADED AK47
OR NOT HAS_MODEL_LOADED BALLAS3 
OR NOT HAS_MODEL_LOADED BALLAS1
OR NOT HAS_MODEL_LOADED	INFERNUS
OR NOT HAS_MISSION_AUDIO_LOADED 2

	WAIT 0

ENDWHILE

SET_CHAR_COORDINATES scplayer 2497.324 -1684.744 12.44
SET_CHAR_HEADING scplayer 0.0
SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT

LOAD_SCENE 2497.324 -1684.744 12.44 

CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 2496.633 -1684.573 12.435 sweet_grove1
SET_CHAR_HEADING sweet_grove1 0.0
SET_CHAR_DECISION_MAKER sweet_grove1 DM_PED_EMPTY
SET_ANIM_GROUP_FOR_CHAR sweet_grove1 gang2
GIVE_WEAPON_TO_CHAR sweet_grove1 WEAPONTYPE_AK47 30000 // set to infinate ammo
SET_CHAR_NEVER_TARGETTED sweet_grove1 TRUE
SET_CHAR_SUFFERS_CRITICAL_HITS sweet_grove1 FALSE
SET_GROUP_FOLLOW_STATUS Players_Group TRUE
SET_GROUP_MEMBER Players_Group sweet_grove1
SET_CHAR_ACCURACY sweet_grove1 90
SET_CHAR_HEALTH sweet_grove1 1000
SET_CHAR_MAX_HEALTH sweet_grove1 1000
SET_CHAR_RELATIONSHIP sweet_grove1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2

SET_CHAR_PROOFS sweet_grove1 TRUE TRUE TRUE TRUE TRUE

SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 TRUE 

SWITCH_WIDESCREEN OFF

SET_FADING_COLOUR 0 0 0
DO_FADE 1000 FADE_IN

WHILE NOT GET_FADING_STATUS

	WAIT 0

//	IF IS_CHAR_DEAD sweet_grove1
//		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
//		GOTO mission_grove1_failed
//	ENDIF
	
ENDWHILE

SET_PLAYER_CONTROL player1 ON

IF IS_CHAR_DEAD sweet_grove1
	PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
	GOTO mission_grove1_failed
ELSE
	SET_CHAR_PROOFS sweet_grove1 FALSE FALSE FALSE FALSE FALSE
ENDIF

PRINT_NOW (GM1_18) 8000 1 //"Get to Bdups house!"
ADD_BLIP_FOR_COORD 2293.0325 -1795.7672 12.5469 old_house_blip_grove1

blob_flag = 1

TIMERA = 0

grove1_index = 0
grove1_audio_is_playing = 0
grove1_cutscene_flag = 0
grove1_chat_switch = GROVE1_CHAT1
GOSUB grove1_chat_switch

room_audio_playing_grove1 = 0
flag_car_on_wheels_grove1 = 0

WHILE NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2293.0325 -1795.7672 12.5469 4.0 4.0 4.0 blob_flag
OR NOT IS_GROUP_MEMBER sweet_grove1 Players_Group
OR NOT flag_car_on_wheels_grove1 = 1 

	WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
    	GOTO mission_grove1_passed  
	ENDIF

	IF IS_CHAR_IN_ANY_CAR scplayer
		STORE_CAR_CHAR_IS_IN scplayer stored_car_grove1

		IF NOT IS_CAR_DEAD stored_car_grove1

			IF IS_VEHICLE_ON_ALL_WHEELS stored_car_grove1
				flag_car_on_wheels_grove1 = 1
			ELSE
				flag_car_on_wheels_grove1 = 0
			ENDIF

		ENDIF

	ELSE
		
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2293.0325 -1795.7672 12.5469 4.0 4.0 4.0 FALSE

			IF IS_GROUP_MEMBER sweet_grove1 Players_Group
				flag_car_on_wheels_grove1 = 1
			ELSE
				flag_car_on_wheels_grove1 = 0
			ENDIF
		
		ELSE
			flag_car_on_wheels_grove1 = 0
		ENDIF 
		
	ENDIF	
	
	// Starts talking code
	IF flag_start_audio1_grove1 = 0

		IF TIMERA >= 5000
			CLEAR_THIS_PRINT (GM1_18) 
			flag_start_audio1_grove1 = 1
		ENDIF

	ENDIF

	IF flag_start_audio1_grove1 = 1 
			
		IF NOT IS_CHAR_DEAD sweet_grove1
		
			IF IS_GROUP_MEMBER sweet_grove1 Players_Group
				
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D sweet_grove1 scplayer 10.0 10.0 8.0 FALSE
					
					IF IS_CHAR_ON_FOOT scplayer
                    AND IS_CHAR_ON_FOOT sweet_grove1                                               
						GOSUB load_and_play_audio_grove1
					ENDIF

					IF IS_CHAR_IN_ANY_CAR scplayer
					AND IS_CHAR_IN_ANY_CAR sweet_grove1
						GOSUB load_and_play_audio_grove1
					ENDIF	 
					
				ENDIF

			ELSE
				CLEAR_MISSION_AUDIO 1

				CLEAR_THIS_PRINT (GRO1_FA)
				CLEAR_THIS_PRINT (GRO1_FB)
				CLEAR_THIS_PRINT (GRO1_FC)
				CLEAR_THIS_PRINT (GRO1_FD)
				CLEAR_THIS_PRINT (GRO1_FE)
				CLEAR_THIS_PRINT (GRO1_FF)
				CLEAR_THIS_PRINT (GRO1_FG)
				CLEAR_THIS_PRINT (GRO1_FH)
				CLEAR_THIS_PRINT (GRO1_FJ)
			ENDIF
			
		ELSE

			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2

			CLEAR_PRINTS
			
			PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"

			IF player_had_group_warning_grove1 = 1 
		   		REMOVE_BLIP sweet_blip_grove1
			ENDIF

			GOTO mission_grove1_failed

		ENDIF
		
	ENDIF    

	IF IS_CHAR_DEAD sweet_grove1
		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"

		IF player_had_group_warning_grove1 = 1 
	   		REMOVE_BLIP sweet_blip_grove1
		ENDIF

		GOTO mission_grove1_failed
	ELSE

		GET_CHAR_HEALTH sweet_grove1 sweets_health_grove1
		sweets_health_grove1 = sweets_health_grove1 / 10
		
		// clear the not enough room print   
		IF flag_print_on_grove1 = 1
		
			IF HAS_MISSION_AUDIO_FINISHED 2
				CLEAR_THIS_PRINT (GRO1_GA)
				flag_print_on_grove1 = 0
			ENDIF

		ENDIF  

		IF NOT IS_GROUP_MEMBER sweet_grove1 Players_Group
		AND player_had_group_warning_grove1 = 0 			
		   	REMOVE_BLIP old_house_blip_grove1			
			ADD_BLIP_FOR_CHAR sweet_grove1 sweet_blip_grove1
			SET_BLIP_AS_FRIENDLY sweet_blip_grove1 TRUE 
			PRINT_NOW (GM1_2) 8000 1 //"You have left Sweet behind go get him."
			blob_flag = 0
			flag_has_flying_message_grove1 = 0
			player_had_group_warning_grove1 = 1
		ENDIF

		IF player_had_group_warning_grove1 = 1
			
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet_grove1 8.0 8.0 FALSE

				IF NOT IS_CHAR_IN_FLYING_VEHICLE scplayer

					IF IS_CHAR_IN_ANY_CAR scplayer
						STORE_CAR_CHAR_IS_IN scplayer stored_car_grove1
						GET_MAXIMUM_NUMBER_OF_PASSENGERS stored_car_grove1 max_passenger_grove1
						GET_NUMBER_OF_PASSENGERS stored_car_grove1 passenger_count_grove1

						no_of_space_grove1 =  max_passenger_grove1 - passenger_count_grove1 

						IF no_of_space_grove1 >= 1
							SET_GROUP_FOLLOW_STATUS Players_Group TRUE
							SET_GROUP_MEMBER Players_Group sweet_grove1
							REMOVE_BLIP sweet_blip_grove1
						   	ADD_BLIP_FOR_COORD 2293.0325 -1795.7672 12.5469 old_house_blip_grove1
							PRINT_NOW (GM1_18) 8000 1 //"Get to Bdups house!"
							blob_flag = 1
							had_room_message_grove1 = 0
							player_had_group_warning_grove1 = 0
						ELSE

							IF had_room_message_grove1 = 0

								IF HAS_MISSION_AUDIO_FINISHED 1 
									PLAY_MISSION_AUDIO 2
									PRINT_NOW (GRO1_GA) 8000 1 //"Homes there ain't enough room get a bigger vehicle!"
									START_CHAR_FACIAL_TALK sweet_grove1 999999
									SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 TRUE
								ENDIF

								room_audio_playing_grove1 = 1
								flag_print_on_grove1 = 1
								had_room_message_grove1 = 1
							ELSE

								IF room_audio_playing_grove1 = 1

									IF HAS_MISSION_AUDIO_FINISHED 2
										STOP_CHAR_FACIAL_TALK sweet_grove1
										CLEAR_THIS_PRINT (GRO1_GA)
										SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 FALSE
										room_audio_playing_grove1 = 0
									ENDIF

								ENDIF

							ENDIF

						ENDIF  

					ELSE
						SET_GROUP_FOLLOW_STATUS Players_Group TRUE
						SET_GROUP_MEMBER Players_Group sweet_grove1
						REMOVE_BLIP sweet_blip_grove1
					   	ADD_BLIP_FOR_COORD 2293.0325 -1795.7672 12.5469 old_house_blip_grove1
						PRINT_NOW (GM1_18) 8000 1 //"Get to Bdups house!"
						blob_flag = 1
						player_had_group_warning_grove1 = 0
				
					ENDIF

				ELSE
	
					IF flag_has_flying_message_grove1 = 0

						IF HAS_MISSION_AUDIO_FINISHED 1
							PLAY_MISSION_AUDIO 2
							PRINT_NOW (GRO1_GA) 8000 1 //"Homes there ain't enough room get a bigger vehicle!"
							START_CHAR_FACIAL_TALK sweet_grove1 999999
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 TRUE
						ENDIF

						room_audio_playing_grove1 = 1
						flag_print_on_grove1 = 1
						flag_has_flying_message_grove1 = 1
					ELSE
						
						IF room_audio_playing_grove1 = 1

							IF HAS_MISSION_AUDIO_FINISHED 2
								STOP_CHAR_FACIAL_TALK sweet_grove1
								CLEAR_THIS_PRINT (GRO1_GA)
								SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 FALSE
								room_audio_playing_grove1 = 0
							ENDIF

						ENDIF

					ENDIF

				ENDIF

			ENDIF		

		ENDIF

	ENDIF

ENDWHILE

CLEAR_MISSION_AUDIO 1
CLEAR_PRINTS

REMOVE_BLIP old_house_blip_grove1

SET_PLAYER_CONTROL player1 OFF
SET_POLICE_IGNORE_PLAYER player1 ON
SET_EVERYONE_IGNORE_PLAYER player1 ON

IF NOT IS_CHAR_DEAD sweet_grove1 
	SET_CHAR_PROOFS sweet_grove1 TRUE TRUE TRUE TRUE TRUE
ELSE
	PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
	GOTO mission_grove1_failed
ENDIF

SET_FADING_COLOUR 0 0 0
DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS

	WAIT 0

	IF IS_CHAR_DEAD sweet_grove1
		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
		GOTO mission_grove1_failed
	ENDIF

ENDWHILE

MAKE_PLAYER_GANG_DISAPPEAR

CLEAR_AREA 2294.323 -1797.048 12.586 7.0 TRUE 

IF NOT IS_CHAR_IN_ANY_CAR scplayer
	CLEAR_AREA 2294.323 -1797.048 12.586 3.0 TRUE 
	SET_CHAR_COORDINATES scplayer 2294.323 -1797.048 12.586
	SET_CHAR_HEADING scplayer 270.0 
ELSE
	STORE_CAR_CHAR_IS_IN scplayer dummy_car_grove1
	WARP_CHAR_FROM_CAR_TO_COORD scplayer 2294.323 -1797.048 12.586
	
	WHILE IS_CHAR_IN_ANY_CAR scplayer 
	
		WAIT 0

		IF IS_CHAR_DEAD sweet_grove1
			PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
			GOTO mission_grove1_failed
		ENDIF 
		
	ENDWHILE 
	
	SET_CHAR_HEADING scplayer 270.0	
ENDIF 
 
IF NOT IS_CHAR_DEAD sweet_grove1

	IF NOT IS_CHAR_IN_ANY_CAR sweet_grove1
		CLEAR_AREA 2294.323 -1794.911 12.586 3.0 TRUE
		CLEAR_CHAR_TASKS_IMMEDIATELY sweet_grove1
		SET_CHAR_COORDINATES sweet_grove1 2294.323 -1794.911 12.586
		SET_CHAR_HEADING sweet_grove1 270.0
	ELSE
		STORE_CAR_CHAR_IS_IN sweet_grove1 dummy_car_grove1
		WARP_CHAR_FROM_CAR_TO_COORD sweet_grove1 2294.323 -1794.911 12.586
		
		WHILE IS_CHAR_IN_ANY_CAR sweet_grove1 
		
			WAIT 0

			IF IS_CHAR_DEAD sweet_grove1
				PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
				GOTO mission_grove1_failed
			ENDIF 
			
		ENDWHILE 
		
		SET_CHAR_HEADING sweet_grove1 270.0
		
	ENDIF

ELSE
	PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
	GOTO mission_grove1_failed
ENDIF

CLEAR_AREA 2294.323 -1797.048 12.586 6.0 TRUE 

IF DOES_VEHICLE_EXIST dummy_car_grove1

	IF NOT IS_CAR_DEAD dummy_car_grove1
		CLEAR_AREA 2299.243 -1784.565 12.591 3.0 FALSE 
		SET_CAR_COORDINATES dummy_car_grove1 2299.243 -1784.565 12.591
		SET_CAR_HEADING dummy_car_grove1 0.0
	ENDIF

ENDIF

// *********************** CUT SCENE OF PLAYER AND SWEET AT BDUPS OLD PLACE ***********************

MAKE_PLAYER_SAFE_FOR_CUTSCENE player1

SET_AREA_VISIBLE 3

LOAD_CUTSCENE GROVE1b
 
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

SET_AREA_VISIBLE 0

// **************************************** END OF CUT SCENE **************************************

SWITCH_WIDESCREEN OFF
SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT

MAKE_PLAYER_GANG_REAPPEAR

LOAD_SCENE 2294.323 -1797.048 12.586

SET_FADING_COLOUR 0 0 0
DO_FADE 1500 FADE_IN

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

IF NOT IS_CHAR_DEAD sweet_grove1 
	SET_CHAR_PROOFS sweet_grove1 FALSE FALSE FALSE FALSE FALSE
ELSE
	PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
	GOTO mission_grove1_failed
ENDIF

SET_PLAYER_CONTROL player1 ON
SET_EVERYONE_IGNORE_PLAYER player1 OFF
SET_POLICE_IGNORE_PLAYER player1 OFF

PRINT_NOW (GM1_1) 5000 1 //"Go to Glen Park."
ADD_SPRITE_BLIP_FOR_COORD 1988.316 -1199.675 19.135 RADAR_SPRITE_GANG_G park_blip_grove1

SET_ZONE_GANG_STRENGTH GLN1 GANG_FLAT 40
SET_ZONE_GANG_STRENGTH GLN1 GANG_GROVE 0

GET_ZONE_GANG_STRENGTH GLN1 GANG_NMEX stored_nmex_strength_grove1
GET_ZONE_GANG_STRENGTH GLN1 GANG_SMEX stored_smex_strength_grove1

SET_ZONE_GANG_STRENGTH GLN1 GANG_NMEX 0
SET_ZONE_GANG_STRENGTH GLN1 GANG_SMEX 0

SET_SPECIFIC_ZONE_TO_TRIGGER_GANG_WAR GLN1

SET_WANTED_MULTIPLIER 0.5
SET_GANG_WARS_ACTIVE TRUE

CAN_TRIGGER_GANG_WAR_WHEN_ON_A_MISSION TRUE

TIMERB = 0

grove1_index = 0
grove1_audio_is_playing = 0
grove1_cutscene_flag = 0
grove1_chat_switch = GROVE1_CHAT2
GOSUB grove1_chat_switch

room_audio_playing_grove1 = 0

WHILE NOT flag_player_in_area_grove1 = 1 
OR NOT IS_GROUP_MEMBER sweet_grove1 Players_Group

	WAIT 0

	// mission audio
	IF flag_start_audio2_grove1 = 0
		
		IF TIMERB >= 5000
			CLEAR_THIS_PRINT (GM1_1)
			flag_start_audio2_grove1 = 1
		ENDIF

	ENDIF

	IF flag_start_audio2_grove1 = 1

		IF NOT IS_CHAR_DEAD sweet_grove1
			
			IF IS_GROUP_MEMBER sweet_grove1 Players_Group

				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D sweet_grove1 scplayer 10.0 10.0 8.0 FALSE
					
					IF IS_CHAR_ON_FOOT scplayer
                    AND IS_CHAR_ON_FOOT sweet_grove1                                               
						GOSUB load_and_play_audio_grove1
					ENDIF

					IF IS_CHAR_IN_ANY_CAR scplayer
					AND IS_CHAR_IN_ANY_CAR sweet_grove1
						GOSUB load_and_play_audio_grove1
					ENDIF	 
					
				ENDIF

			ELSE
				CLEAR_MISSION_AUDIO 1

				CLEAR_THIS_PRINT (GRO1_JA)
				CLEAR_THIS_PRINT (GRO1_JB)
				CLEAR_THIS_PRINT (GRO1_JC)
				CLEAR_THIS_PRINT (GRO1_JD)
				CLEAR_THIS_PRINT (GRO1_JE)
			ENDIF
			
		ELSE

			CLEAR_MISSION_AUDIO 1

			CLEAR_PRINTS

			REMOVE_BLIP park_blip_grove1
			
			PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"

			IF player_had_group_warning_grove1 = 1 
		   		REMOVE_BLIP sweet_blip_grove1
			ENDIF

			GOTO mission_grove1_failed

		ENDIF

	ENDIF
	 
	IF IS_PLAYER_IN_INFO_ZONE player1 GLN1
		flag_player_in_area_grove1 = 1
	ELSE	 
		flag_player_in_area_grove1 = 0
	ENDIF 

	IF IS_CHAR_DEAD sweet_grove1
		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"

		IF player_had_group_warning_grove1 = 1 
	   		REMOVE_BLIP sweet_blip_grove1
		ENDIF

		GOTO mission_grove1_failed
	ELSE

		GET_CHAR_HEALTH sweet_grove1 sweets_health_grove1
		sweets_health_grove1 = sweets_health_grove1 / 10

		// clear the not enough room print   
		IF flag_print_on_grove1 = 1
		
			IF HAS_MISSION_AUDIO_FINISHED 2
				CLEAR_THIS_PRINT (GRO1_GA)
				flag_print_on_grove1 = 0
			ENDIF

		ENDIF

		IF NOT IS_GROUP_MEMBER sweet_grove1 Players_Group
		AND player_had_group_warning_grove1 = 0 			
		   //	REMOVE_BLIP park_blip_grove1			
			ADD_BLIP_FOR_CHAR sweet_grove1 sweet_blip_grove1
			SET_BLIP_AS_FRIENDLY sweet_blip_grove1 TRUE 
			PRINT_NOW (GM1_2) 8000 1 //"You have left Sweet behind go get him."
			blob_flag = 0
			flag_has_flying_message_grove1 = 0
			player_had_group_warning_grove1 = 1
		ENDIF

		IF player_had_group_warning_grove1 = 1
			
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet_grove1 8.0 8.0 FALSE

				IF NOT IS_CHAR_IN_FLYING_VEHICLE scplayer

					IF IS_CHAR_IN_ANY_CAR scplayer
						STORE_CAR_CHAR_IS_IN scplayer stored_car_grove1
						GET_MAXIMUM_NUMBER_OF_PASSENGERS stored_car_grove1 max_passenger_grove1
						GET_NUMBER_OF_PASSENGERS stored_car_grove1 passenger_count_grove1

						no_of_space_grove1 =  max_passenger_grove1 - passenger_count_grove1 

						IF no_of_space_grove1 >= 1
							SET_GROUP_FOLLOW_STATUS Players_Group TRUE
							SET_GROUP_MEMBER Players_Group sweet_grove1
							REMOVE_BLIP sweet_blip_grove1
						   //	ADD_BLIP_FOR_COORD 1985.176 -1233.743 19.135 park_blip_grove1
							blob_flag = 1
							had_room_message_grove1 = 0
							player_had_group_warning_grove1 = 0
						ELSE

							IF had_room_message_grove1 = 0
								
								IF HAS_MISSION_AUDIO_FINISHED 1
									PLAY_MISSION_AUDIO 2
									PRINT_NOW (GRO1_GA) 8000 1 //"Homes there ain't enough room get a bigger vehicle!"
									START_CHAR_FACIAL_TALK sweet_grove1 999999
									SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 TRUE
								ENDIF

								room_audio_playing_grove1 = 1
								flag_print_on_grove1 = 1
								had_room_message_grove1 = 1
							ELSE

								IF room_audio_playing_grove1 = 1

									IF HAS_MISSION_AUDIO_FINISHED 2
										STOP_CHAR_FACIAL_TALK sweet_grove1
										CLEAR_THIS_PRINT (GRO1_GA)
										SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 FALSE
										room_audio_playing_grove1 = 0
									ENDIF

								ENDIF
								
							ENDIF

						ENDIF  

					ELSE
						SET_GROUP_FOLLOW_STATUS Players_Group TRUE
						SET_GROUP_MEMBER Players_Group sweet_grove1
						REMOVE_BLIP sweet_blip_grove1
					   //	ADD_BLIP_FOR_COORD 1985.176 -1233.743 19.135 park_blip_grove1
						blob_flag = 1
						player_had_group_warning_grove1 = 0
				
					ENDIF

				ELSE
	
					IF flag_has_flying_message_grove1 = 0

						IF HAS_MISSION_AUDIO_FINISHED 1
							PLAY_MISSION_AUDIO 2
							PRINT_NOW (GRO1_GA) 8000 1 //"Homes there ain't enough room get a bigger vehicle!"
							START_CHAR_FACIAL_TALK sweet_grove1 999999
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 TRUE
						ENDIF

						room_audio_playing_grove1 = 1
						flag_print_on_grove1 = 1
						flag_has_flying_message_grove1 = 1
					ELSE

						IF room_audio_playing_grove1 = 1

							IF HAS_MISSION_AUDIO_FINISHED 2
								STOP_CHAR_FACIAL_TALK sweet_grove1
								CLEAR_THIS_PRINT (GRO1_GA)
								SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 FALSE
								room_audio_playing_grove1 = 0
							ENDIF

						ENDIF

					ENDIF

				ENDIF

			ENDIF		

		ENDIF

	ENDIF
	
ENDWHILE

PRINT_NOW (GM1_3) 8000 1 //"Attack the Ballas and take over their territory."

TIMERB = 0

grove1_index = 0
grove1_audio_is_playing = 0
grove1_cutscene_flag = 0
grove1_chat_switch = GROVE1_CHAT4
GOSUB grove1_chat_switch

IF NOT IS_CHAR_DEAD sweet_grove1
	GET_CHAR_HEALTH sweet_grove1 sweets_health_grove1
ELSE
	CLEAR_MISSION_AUDIO 1
	CLEAR_PRINTS
	REMOVE_BLIP park_blip_grove1
	PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"

	IF player_had_group_warning_grove1 = 1 
		REMOVE_BLIP sweet_blip_grove1
	ENDIF

	GOTO mission_grove1_failed
ENDIF

sweets_health_grove1 = sweets_health_grove1 / 10
DISPLAY_ONSCREEN_COUNTER_WITH_STRING sweets_health_grove1 COUNTER_DISPLAY_BAR (GM1_7)  //"SWEETS HEALTH"

room_audio_playing_grove1 = 0
 									
// waiting for the player to take over the zone
WHILE NOT flag_player_taken_terr_grove1 = 1
OR NOT IS_GROUP_MEMBER sweet_grove1 Players_Group 

	WAIT 0

	IF flag_start_audio3_grove1 = 0

		IF TIMERB >= 5000
			CLEAR_THIS_PRINT (GM1_3)
			flag_start_audio3_grove1 = 1
		ENDIF

	ENDIF

	IF flag_start_audio3_grove1 = 1
		
		IF NOT IS_CHAR_DEAD sweet_grove1
			
			IF IS_GROUP_MEMBER sweet_grove1 Players_Group

				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D sweet_grove1 scplayer 10.0 10.0 8.0 FALSE
					
					IF IS_CHAR_ON_FOOT scplayer
                    AND IS_CHAR_ON_FOOT sweet_grove1                                               
						GOSUB load_and_play_audio_grove1
					ENDIF

					IF IS_CHAR_IN_ANY_CAR scplayer
					AND IS_CHAR_IN_ANY_CAR sweet_grove1
						GOSUB load_and_play_audio_grove1
					ENDIF	 
					
				ENDIF

			ELSE
				CLEAR_MISSION_AUDIO 1

				CLEAR_THIS_PRINT (GRO1_KA)
				CLEAR_THIS_PRINT (GRO1_KB)
				
			ENDIF
			
		ELSE

			CLEAR_MISSION_AUDIO 1

			CLEAR_PRINTS

			REMOVE_BLIP park_blip_grove1
			
			PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"

			IF player_had_group_warning_grove1 = 1 
		   		REMOVE_BLIP sweet_blip_grove1
			ENDIF

			GOTO mission_grove1_failed

		ENDIF

	ENDIF
	
	GET_ZONE_GANG_STRENGTH GLN1 GANG_FLAT gang_strenth1_grove1
	GET_ZONE_GANG_STRENGTH GLN1 GANG_GROVE player_gang_strenth_grove1
			
	IF IS_CHAR_DEAD sweet_grove1
		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"

		IF player_had_group_warning_grove1 = 1 
	   		REMOVE_BLIP sweet_blip_grove1
		ENDIF

		GOTO mission_grove1_failed
	ELSE

		GET_CHAR_HEALTH sweet_grove1 sweets_health_grove1
		sweets_health_grove1 = sweets_health_grove1 / 10

		// clear the not enough room print   
		IF flag_print_on_grove1 = 1
		
			IF HAS_MISSION_AUDIO_FINISHED 2
				CLEAR_THIS_PRINT (GRO1_GA)
				flag_print_on_grove1 = 0
			ENDIF

		ENDIF

		IF NOT IS_GROUP_MEMBER sweet_grove1 Players_Group
		AND player_had_group_warning_grove1 = 0 						
			ADD_BLIP_FOR_CHAR sweet_grove1 sweet_blip_grove1
			SET_BLIP_AS_FRIENDLY sweet_blip_grove1 TRUE 
			PRINT_NOW (GM1_2) 8000 1 //"You have left Sweet behind go get him."
			blob_flag = 0
			flag_has_flying_message_grove1 = 0
			player_had_group_warning_grove1 = 1
		ENDIF

		IF player_had_group_warning_grove1 = 1
			
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet_grove1 8.0 8.0 FALSE

				IF NOT IS_CHAR_IN_FLYING_VEHICLE scplayer

					IF IS_CHAR_IN_ANY_CAR scplayer
						STORE_CAR_CHAR_IS_IN scplayer stored_car_grove1
						GET_MAXIMUM_NUMBER_OF_PASSENGERS stored_car_grove1 max_passenger_grove1
						GET_NUMBER_OF_PASSENGERS stored_car_grove1 passenger_count_grove1

						no_of_space_grove1 =  max_passenger_grove1 - passenger_count_grove1 

						IF no_of_space_grove1 >= 1
							SET_GROUP_FOLLOW_STATUS Players_Group TRUE
							SET_GROUP_MEMBER Players_Group sweet_grove1
							REMOVE_BLIP sweet_blip_grove1

//							//IF flag_player_in_area_grove1 = 0
//							IF flag_gang_war_off_grove1 = 1
//						  //		ADD_BLIP_FOR_COORD 1985.176 -1233.743 19.135 park_blip_grove1
//							ENDIF

							blob_flag = 1
							had_room_message_grove1 = 0
							player_had_group_warning_grove1 = 0
						ELSE

							IF had_room_message_grove1 = 0

								IF HAS_MISSION_AUDIO_FINISHED 1
									PLAY_MISSION_AUDIO 2
									PRINT_NOW (GRO1_GA) 8000 1 //"Homes there ain't enough room get a bigger vehicle!"
									START_CHAR_FACIAL_TALK sweet_grove1 999999
									SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 TRUE
								ENDIF

								room_audio_playing_grove1 = 1
								flag_print_on_grove1 = 1
								had_room_message_grove1 = 1
							ELSE

								IF room_audio_playing_grove1 = 1

									IF HAS_MISSION_AUDIO_FINISHED 2
										STOP_CHAR_FACIAL_TALK sweet_grove1
										CLEAR_THIS_PRINT (GRO1_GA)
										SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 FALSE
										room_audio_playing_grove1 = 0
									ENDIF

								ENDIF

							ENDIF

						ENDIF  

					ELSE
						SET_GROUP_FOLLOW_STATUS Players_Group TRUE
						SET_GROUP_MEMBER Players_Group sweet_grove1
						REMOVE_BLIP sweet_blip_grove1

//						IF flag_player_in_area_grove1 = 0
//						 //	ADD_BLIP_FOR_COORD 1985.176 -1233.743 
//						ENDIF

						blob_flag = 1
						player_had_group_warning_grove1 = 0
					ENDIF

				ELSE

					IF flag_has_flying_message_grove1 = 0

						IF HAS_MISSION_AUDIO_FINISHED 1
							PLAY_MISSION_AUDIO 2
							PRINT_NOW (GRO1_GA) 8000 1 //"Homes there ain't enough room get a bigger vehicle!"
							START_CHAR_FACIAL_TALK sweet_grove1 999999
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 TRUE
						ENDIF

						room_audio_playing_grove1 = 1
						flag_print_on_grove1 = 1
						flag_has_flying_message_grove1 = 1
					ELSE
							
						IF room_audio_playing_grove1 = 1

							IF HAS_MISSION_AUDIO_FINISHED 2
								STOP_CHAR_FACIAL_TALK sweet_grove1
								CLEAR_THIS_PRINT (GRO1_GA)
								SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 FALSE
								room_audio_playing_grove1 = 0
							ENDIF

						ENDIF

					ENDIF

				ENDIF

			ENDIF	 
		   
		ENDIF

	ENDIF

	IF gang_strenth1_grove1 = 0
		flag_player_taken_terr_grove1 = 1
	ENDIF

	IF IS_PLAYER_IN_INFO_ZONE player1 GLN1
		flag_player_in_area_grove1 = 1
	ELSE
		
		IF NOT IS_GANG_WAR_GOING_ON
		
			IF IS_GROUP_MEMBER sweet_grove1 Players_Group

				IF player_gang_strenth_grove1 = 0

					IF flag_gang_war_off_grove1 = 0
						PRINT_NOW (GM1_16) 8000 1 //"The enemy have retaken the territory!"	
						flag_gang_war_off_grove1 = 1
					ENDIF

				ENDIF

			ENDIF

		ELSE
			flag_gang_war_off_grove1 = 0
		ENDIF

	ENDIF

ENDWHILE

REMOVE_BLIP park_blip_grove1

CLEAR_THIS_PRINT (GM1_16)
CLEAR_THIS_PRINT (GRO1_GA)
CLEAR_THIS_PRINT (GM1_2)
CLEAR_THIS_PRINT (GM1_4)
CLEAR_THIS_PRINT (GM1_3) 

CAN_TRIGGER_GANG_WAR_WHEN_ON_A_MISSION FALSE

WAIT 5000

// ********************************* Small cutscene showing Bdups house *****************

GOSUB check_player_is_safe

WHILE NOT player_is_completely_safe = 1

	WAIT 0

	IF IS_CHAR_DEAD sweet_grove1 
		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
		REMOVE_BLIP sweet_blip_grove1
		GOTO mission_grove1_failed
	ENDIF

	GOSUB check_player_is_safe

ENDWHILE

SET_PLAYER_CONTROL player1 OFF
SET_POLICE_IGNORE_PLAYER player1 ON
SET_EVERYONE_IGNORE_PLAYER player1 ON

IF NOT IS_CHAR_DEAD sweet_grove1 
	SET_CHAR_PROOFS sweet_grove1 TRUE TRUE TRUE TRUE TRUE
ELSE
	PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
	GOTO mission_grove1_failed
ENDIF

SET_FADING_COLOUR 0 0 0
DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS

	WAIT 0

//	IF IS_CHAR_DEAD sweet_grove1
//		
//		CLEAR_MISSION_AUDIO 1
//
//		CLEAR_PRINTS
//
//		REMOVE_BLIP park_blip_grove1
//		
//		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
//
//		IF player_had_group_warning_grove1 = 1 
//	   		REMOVE_BLIP sweet_blip_grove1
//		ENDIF
//
//	   	GOTO mission_grove1_failed
//
//	ENDIF

ENDWHILE

MAKE_PLAYER_GANG_DISAPPEAR

IF NOT IS_CHAR_IN_ANY_CAR scplayer
	LOAD_SCENE 2038.184 -1143.772 23.455 
	CLEAR_AREA 2038.184 -1143.772 23.455 3.0 TRUE 
	SET_CHAR_COORDINATES scplayer 2038.184 -1143.772 23.455
	SET_CHAR_HEADING scplayer 56.5
ELSE
	STORE_CAR_CHAR_IS_IN scplayer dummy_car_grove1
	WARP_CHAR_FROM_CAR_TO_COORD scplayer 2038.184 -1143.772 23.455
	
	WHILE IS_CHAR_IN_ANY_CAR scplayer
		
		WAIT 0

		IF IS_CHAR_DEAD sweet_grove1
//			REMOVE_BLIP park_blip_grove1
//			PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
//
//			IF player_had_group_warning_grove1 = 1 
//		   		REMOVE_BLIP sweet_blip_grove1
//			ENDIF
//
//			GOTO mission_grove1_failed

		ENDIF

		IF IS_CAR_DEAD dummy_car_grove1
//			PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
//
//			IF player_had_group_warning_grove1 = 1 
//		   		REMOVE_BLIP sweet_blip_grove1
//			ENDIF
//
//			GOTO mission_grove1_failed

		ENDIF

		
	ENDWHILE
	
	SET_CHAR_HEADING scplayer 56.5

ENDIF

IF NOT IS_CHAR_DEAD sweet_grove1 

	IF NOT IS_CHAR_IN_ANY_CAR sweet_grove1
		CLEAR_AREA 2037.048 -1144.896 23.487 3.0 TRUE 
		SET_CHAR_COORDINATES scplayer 2037.048 -1144.896 23.487
		SET_CHAR_HEADING sweet_grove1 56.5
	ELSE
		STORE_CAR_CHAR_IS_IN sweet_grove1 dummy_car_grove1
		WARP_CHAR_FROM_CAR_TO_COORD sweet_grove1 2037.048 -1144.896 23.487
		
		WHILE IS_CHAR_IN_ANY_CAR sweet_grove1
			
			WAIT 0

			IF IS_CHAR_DEAD sweet_grove1
//				REMOVE_BLIP park_blip_grove1
//				PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
//
//				IF player_had_group_warning_grove1 = 1 
//			   		REMOVE_BLIP sweet_blip_grove1
//				ENDIF
//
//				GOTO mission_grove1_failed

			ENDIF

			IF IS_CAR_DEAD dummy_car_grove1
//				PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
//
//				IF player_had_group_warning_grove1 = 1 
//			   		REMOVE_BLIP sweet_blip_grove1
//				ENDIF
//
//				GOTO mission_grove1_failed

			ENDIF

			
		ENDWHILE
		
		SET_CHAR_HEADING sweet_grove1 56.5

	ENDIF

ENDIF

CLEAR_AREA 2038.184 -1143.772 23.455 6.0 TRUE

SWITCH_WIDESCREEN ON

CLEAR_AREA 2006.3556 -1125.6630 25.6347 1.0 TRUE 
SET_FIXED_CAMERA_POSITION 2006.3556 -1125.6630 25.6347 0.0 0.0 0.0
POINT_CAMERA_AT_POINT 2005.6663 -1124.9659 25.8323 JUMP_CUT

// Create car
CLEAR_AREA 1992.437 -1120.185 25.813 4.0 FALSE 
CREATE_CAR INFERNUS 1992.437 -1120.185 25.813 bdup_car_grove1
SET_CAR_HEADING bdup_car_grove1 260.0
LOCK_CAR_DOORS bdup_car_grove1 CARLOCK_LOCKED

REQUEST_COLLISION 1994.668 -1119.022
LOAD_SCENE_IN_DIRECTION 1994.668 -1119.022 25.813 56.5  

// creates Bdups guard
CLEAR_AREA 1994.668 -1119.022 25.813 2.0 FALSE 
CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 1994.668 -1119.022 25.813 bdup_guard1_grove1 // 
GIVE_WEAPON_TO_CHAR bdup_guard1_grove1 WEAPONTYPE_AK47 30000 // set to infinate ammo
SET_CHAR_HEADING bdup_guard1_grove1 207.710
SET_CHAR_DECISION_MAKER bdup_guard1_grove1 tough_duck_dm_grove1
TASK_PLAY_ANIM bdup_guard1_grove1 WEAPON_CROUCH PED 4.0 TRUE FALSE FALSE FALSE -1
SET_CHAR_STAY_IN_SAME_PLACE bdup_guard1_grove1 TRUE
TASK_KILL_CHAR_ON_FOOT bdup_guard1_grove1 scplayer 
SET_CHAR_IS_TARGET_PRIORITY bdup_guard1_grove1 TRUE
SET_CHAR_RELATIONSHIP bdup_guard1_grove1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_RELATIONSHIP bdup_guard1_grove1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1

CLEAR_AREA 2005.848 -1116.500 26.179 2.0 FALSE 
CREATE_CHAR PEDTYPE_MISSION2 BALLAS3 2005.848 -1116.500 26.179 bdup_guard2_grove1 //
GIVE_WEAPON_TO_CHAR bdup_guard2_grove1 WEAPONTYPE_AK47 30000 // set to infinate ammo
SET_CHAR_HEADING bdup_guard2_grove1 185.395
SET_CHAR_DECISION_MAKER bdup_guard2_grove1 tough_duck_dm_grove1
TASK_PLAY_ANIM bdup_guard2_grove1 WEAPON_CROUCH PED 4.0 TRUE FALSE FALSE FALSE -1
SET_CHAR_STAY_IN_SAME_PLACE bdup_guard2_grove1 TRUE
TASK_KILL_CHAR_ON_FOOT bdup_guard2_grove1 scplayer
SET_CHAR_IS_TARGET_PRIORITY bdup_guard2_grove1 TRUE
SET_CHAR_RELATIONSHIP bdup_guard2_grove1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_RELATIONSHIP bdup_guard2_grove1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1

CLEAR_AREA 2002.672 -1116.134 26.172 2.0 FALSE 
CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 2002.672 -1116.134 26.172 bdup_guard3_grove1 //
GIVE_WEAPON_TO_CHAR bdup_guard3_grove1 WEAPONTYPE_AK47 30000 // set to infinate ammo
SET_CHAR_HEADING bdup_guard3_grove1 200.691
SET_CHAR_DECISION_MAKER bdup_guard3_grove1 tough_duck_dm_grove1
TASK_PLAY_ANIM bdup_guard3_grove1 WEAPON_CROUCH PED 4.0 TRUE FALSE FALSE FALSE -1
SET_CHAR_STAY_IN_SAME_PLACE bdup_guard3_grove1 TRUE
TASK_KILL_CHAR_ON_FOOT bdup_guard3_grove1 scplayer
SET_CHAR_IS_TARGET_PRIORITY bdup_guard3_grove1 TRUE
SET_CHAR_RELATIONSHIP bdup_guard3_grove1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_RELATIONSHIP bdup_guard3_grove1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1

CLEAR_AREA 1988.934 -1120.135 25.813 2.0 FALSE 
CREATE_CHAR PEDTYPE_MISSION2 BALLAS3 1988.934 -1120.135 25.813 bdup_guard4_grove1 //
GIVE_WEAPON_TO_CHAR bdup_guard4_grove1 WEAPONTYPE_AK47 30000 // set to infinate ammo
SET_CHAR_HEADING bdup_guard4_grove1 220.163
SET_CHAR_DECISION_MAKER bdup_guard4_grove1 tough_duck_dm_grove1
SET_CHAR_STAY_IN_SAME_PLACE bdup_guard4_grove1 TRUE
TASK_KILL_CHAR_ON_FOOT bdup_guard4_grove1 scplayer
SET_CHAR_IS_TARGET_PRIORITY bdup_guard4_grove1 TRUE
SET_CHAR_RELATIONSHIP bdup_guard4_grove1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_RELATIONSHIP bdup_guard4_grove1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
TASK_PLAY_ANIM bdup_guard3_grove1 WEAPON_CROUCH PED 4.0 TRUE FALSE FALSE FALSE -1

CLEAR_AREA 1994.062 -1115.618 25.813 2.0 FALSE 
CREATE_CHAR PEDTYPE_MISSION2 BALLAS3 1994.062 -1115.618 25.813 bdup_guard5_grove1
GIVE_WEAPON_TO_CHAR bdup_guard5_grove1 WEAPONTYPE_AK47 30000 // set to infinate ammo
SET_CHAR_HEADING bdup_guard5_grove1 235.0
SET_CHAR_DECISION_MAKER bdup_guard5_grove1 tough_decisionmaker_grove1
SET_CHAR_STAY_IN_SAME_PLACE bdup_guard5_grove1 TRUE
SET_CHAR_IS_TARGET_PRIORITY bdup_guard5_grove1 TRUE
SET_CHAR_RELATIONSHIP bdup_guard5_grove1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_RELATIONSHIP bdup_guard5_grove1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1

CLEAR_AREA 1997.480 -1114.998 25.813 2.0 FALSE 
CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 1997.480 -1118.998 25.813 bdup_guard6_grove1
GIVE_WEAPON_TO_CHAR bdup_guard6_grove1 WEAPONTYPE_AK47 30000 // set to infinate ammo
SET_CHAR_HEADING bdup_guard6_grove1 176.0
SET_CHAR_DECISION_MAKER bdup_guard6_grove1 tough_decisionmaker_grove1
SET_CHAR_STAY_IN_SAME_PLACE bdup_guard6_grove1 TRUE
SET_CHAR_IS_TARGET_PRIORITY bdup_guard6_grove1 TRUE
SET_CHAR_RELATIONSHIP bdup_guard6_grove1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_RELATIONSHIP bdup_guard6_grove1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1

CLEAR_AREA 2008.344 -1113.057 25.584 2.0 FALSE  
CREATE_CHAR PEDTYPE_MISSION2 BALLAS3 2008.344 -1113.057 25.584 bdup_guard7_grove1
GIVE_WEAPON_TO_CHAR bdup_guard7_grove1 WEAPONTYPE_AK47 30000 // set to infinate ammo
SET_CHAR_HEADING bdup_guard7_grove1 177.195
SET_CHAR_DECISION_MAKER bdup_guard7_grove1 tough_decisionmaker_grove1
SET_CHAR_STAY_IN_SAME_PLACE bdup_guard7_grove1 TRUE
SET_CHAR_IS_TARGET_PRIORITY bdup_guard7_grove1 TRUE
SET_CHAR_RELATIONSHIP bdup_guard7_grove1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_RELATIONSHIP bdup_guard7_grove1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1

CLEAR_AREA 2014.768 -1111.447 25.235 2.0 FALSE 
CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 2014.768 -1111.447 25.235 bdup_guard8_grove1
GIVE_WEAPON_TO_CHAR bdup_guard8_grove1 WEAPONTYPE_AK47 30000 // set to infinate ammo
SET_CHAR_HEADING bdup_guard8_grove1 164.890
SET_CHAR_DECISION_MAKER bdup_guard8_grove1 tough_decisionmaker_grove1
SET_CHAR_IS_TARGET_PRIORITY bdup_guard8_grove1 TRUE
SET_CHAR_RELATIONSHIP bdup_guard8_grove1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_RELATIONSHIP bdup_guard8_grove1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1

SET_FADING_COLOUR  0 0 0
DO_FADE 2000 FADE_IN

grove1_index = 0
grove1_audio_is_playing = 0
grove1_cutscene_flag = 0
grove1_chat_switch = GROVE1_CHAT3
GOSUB grove1_chat_switch

GOSUB load_and_play_audio_grove1

WHILE GET_FADING_STATUS

	WAIT 0

//	IF NOT IS_CHAR_DEAD sweet_grove1
//		GOSUB load_and_play_audio_grove1
//	ELSE
//		CLEAR_PRINTS
//		CLEAR_MISSION_AUDIO 1
//		CLEAR_MISSION_AUDIO 2
//		REMOVE_BLIP park_blip_grove1
//		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
//
//		IF player_had_group_warning_grove1 = 1 
//		   	REMOVE_BLIP sweet_blip_grove1
//		ENDIF
//
//		GOTO mission_grove1_failed
//
//	ENDIF
//
//	GOSUB guard_death_check_grove1

ENDWHILE

//grove1_index = 0
//grove1_audio_is_playing = 0
//grove1_cutscene_flag = 0
//grove1_chat_switch = GROVE1_CHAT3
//GOSUB grove1_chat_switch

IF NOT IS_CHAR_DEAD sweet_grove1
	GOSUB load_and_play_audio_grove1
ELSE
	CLEAR_PRINTS
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	REMOVE_BLIP park_blip_grove1
	PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"

	IF player_had_group_warning_grove1 = 1 
	   	REMOVE_BLIP sweet_blip_grove1
	ENDIF

	GOTO mission_grove1_failed
ENDIF

SKIP_CUTSCENE_START
 
WHILE NOT grove1_index = 3 

	WAIT 0

	GOSUB guard_death_check_grove1

	IF NOT IS_CHAR_DEAD sweet_grove1
		GOSUB load_and_play_audio_grove1
	ELSE
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		REMOVE_BLIP park_blip_grove1
		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"

		IF player_had_group_warning_grove1 = 1 
		   	REMOVE_BLIP sweet_blip_grove1
		ENDIF

		GOTO mission_grove1_failed

	ENDIF

	IF grove1_index > 0

		IF dbup_guys_got_ai_grove1 = 0 
			GOSUB give_dbup_ai_grove1
		ENDIF

	ENDIF

ENDWHILE

SKIP_CUTSCENE_END

CLEAR_PRINTS
CLEAR_MISSION_AUDIO 1
CLEAR_MISSION_AUDIO 2

SET_FADING_COLOUR 0 0 0
DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

MAKE_PLAYER_GANG_REAPPEAR

IF bdup_guard1_dead_grove1 = 0
	
	IF NOT IS_CHAR_DEAD bdup_guard1_grove1
		ADD_BLIP_FOR_CHAR bdup_guard1_grove1 bdup_guard1_blip_grove1
	ELSE
		REMOVE_BLIP bdup_guard1_blip_grove1
		++ counter_dbup_guards_dead
		bdup_guard1_dead_grove1 = 1
	ENDIF

ENDIF

IF bdup_guard2_dead_grove1 = 0
	
	IF NOT IS_CHAR_DEAD bdup_guard2_grove1
		ADD_BLIP_FOR_CHAR bdup_guard2_grove1 bdup_guard2_blip_grove1
	ELSE
		REMOVE_BLIP bdup_guard2_blip_grove1
		++ counter_dbup_guards_dead
		bdup_guard2_dead_grove1 = 1
	ENDIF

ENDIF

IF bdup_guard3_dead_grove1 = 0
	
	IF NOT IS_CHAR_DEAD bdup_guard3_grove1
		ADD_BLIP_FOR_CHAR bdup_guard3_grove1 bdup_guard3_blip_grove1
	ELSE
		REMOVE_BLIP bdup_guard3_blip_grove1
		++ counter_dbup_guards_dead
		bdup_guard3_dead_grove1 = 1
	ENDIF

ENDIF

IF bdup_guard4_dead_grove1 = 0
	
	IF NOT IS_CHAR_DEAD bdup_guard4_grove1
		ADD_BLIP_FOR_CHAR bdup_guard4_grove1 bdup_guard4_blip_grove1
	ELSE
		REMOVE_BLIP bdup_guard4_blip_grove1
		++ counter_dbup_guards_dead
		bdup_guard4_dead_grove1 = 1
	ENDIF

ENDIF

IF bdup_guard5_dead_grove1 = 0
	
	IF NOT IS_CHAR_DEAD bdup_guard5_grove1
		ADD_BLIP_FOR_CHAR bdup_guard5_grove1 bdup_guard5_blip_grove1
	ELSE
		REMOVE_BLIP bdup_guard5_blip_grove1
		++ counter_dbup_guards_dead
		bdup_guard5_dead_grove1 = 1
	ENDIF

ENDIF

IF bdup_guard6_dead_grove1 = 0
	
	IF NOT IS_CHAR_DEAD bdup_guard6_grove1
		ADD_BLIP_FOR_CHAR bdup_guard6_grove1 bdup_guard6_blip_grove1
	ELSE
		REMOVE_BLIP bdup_guard6_blip_grove1
		++ counter_dbup_guards_dead
		bdup_guard6_dead_grove1 = 1
	ENDIF

ENDIF

IF bdup_guard7_dead_grove1 = 0
	
	IF NOT IS_CHAR_DEAD bdup_guard7_grove1
		ADD_BLIP_FOR_CHAR bdup_guard7_grove1 bdup_guard7_blip_grove1
	ELSE
		REMOVE_BLIP bdup_guard7_blip_grove1
		++ counter_dbup_guards_dead
		bdup_guard7_dead_grove1 = 1
	ENDIF

ENDIF

IF bdup_guard8_dead_grove1 = 0
	
	IF NOT IS_CHAR_DEAD bdup_guard8_grove1
		ADD_BLIP_FOR_CHAR bdup_guard8_grove1 bdup_guard8_blip_grove1
	ELSE
		REMOVE_BLIP bdup_guard8_blip_grove1
		++ counter_dbup_guards_dead
		bdup_guard8_dead_grove1 = 1
	ENDIF

ENDIF

SWITCH_WIDESCREEN OFF
SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT

SET_FADING_COLOUR 0 0 0
DO_FADE 1000 FADE_IN

WHILE GET_FADING_STATUS

	WAIT 0

//	IF IS_CHAR_DEAD sweet_grove1
//		REMOVE_BLIP park_blip_grove1
//		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
//
//		IF player_had_group_warning_grove1 = 1 
//		   	REMOVE_BLIP sweet_blip_grove1
//		ENDIF
//
//		GOTO mission_grove1_failed
//
//	ENDIF
//
//	GOSUB guard_death_check_grove1

ENDWHILE

SET_PLAYER_CONTROL player1 ON
SET_POLICE_IGNORE_PLAYER player1 OFF
SET_EVERYONE_IGNORE_PLAYER player1 OFF

IF NOT IS_CHAR_DEAD sweet_grove1 
	SET_CHAR_PROOFS sweet_grove1 FALSE FALSE FALSE FALSE FALSE	
ELSE
	PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
	GOTO mission_grove1_failed
ENDIF

PRINT_NOW (GM1_9) 8000 1 //"Kill Bdups Guards!"

room_audio_playing_grove1 = 0
							 
// waiting for all bdups guys to be dead
WHILE NOT counter_dbup_guards_dead = 8
OR NOT IS_GROUP_MEMBER sweet_grove1 Players_Group

	WAIT 0

	GET_CHAR_COORDINATES scplayer player_x player_y player_z

	IF IS_CHAR_DEAD sweet_grove1
		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"

		IF player_had_group_warning_grove1 = 1 
	   		REMOVE_BLIP sweet_blip_grove1
		ENDIF

		GOTO mission_grove1_failed
	ELSE

		GET_CHAR_HEALTH sweet_grove1 sweets_health_grove1
		sweets_health_grove1 = sweets_health_grove1 / 10

		// clear the not enough room print   
		IF flag_print_on_grove1 = 1
		
			IF HAS_MISSION_AUDIO_FINISHED 2
				CLEAR_THIS_PRINT (GRO1_GA)
				flag_print_on_grove1 = 0
			ENDIF

		ENDIF

		IF NOT IS_GROUP_MEMBER sweet_grove1 Players_Group
		AND player_had_group_warning_grove1 = 0 						
			ADD_BLIP_FOR_CHAR sweet_grove1 sweet_blip_grove1
			SET_BLIP_AS_FRIENDLY sweet_blip_grove1 TRUE 
			PRINT_NOW (GM1_2) 8000 1 //"You have left Sweet behind go get him."
			blob_flag = 0
			flag_has_flying_message_grove1 = 0
			
			IF NOT IS_CHAR_DEAD bdup_guard1_grove1 
				REMOVE_BLIP bdup_guard1_blip_grove1
			ENDIF

			IF NOT IS_CHAR_DEAD bdup_guard2_grove1 
				REMOVE_BLIP bdup_guard2_blip_grove1
			ENDIF

			IF NOT IS_CHAR_DEAD bdup_guard3_grove1 
				REMOVE_BLIP bdup_guard3_blip_grove1
			ENDIF

			IF NOT IS_CHAR_DEAD bdup_guard4_grove1 
				REMOVE_BLIP bdup_guard4_blip_grove1
			ENDIF

			IF NOT IS_CHAR_DEAD bdup_guard5_grove1 
				REMOVE_BLIP bdup_guard5_blip_grove1
			ENDIF

			IF NOT IS_CHAR_DEAD bdup_guard6_grove1 
				REMOVE_BLIP bdup_guard6_blip_grove1
			ENDIF

			IF NOT IS_CHAR_DEAD bdup_guard7_grove1 
				REMOVE_BLIP bdup_guard7_blip_grove1
			ENDIF

			IF NOT IS_CHAR_DEAD bdup_guard8_grove1 
				REMOVE_BLIP bdup_guard8_blip_grove1
			ENDIF

			player_had_group_warning_grove1 = 1
		ENDIF

		IF player_had_group_warning_grove1 = 1
			
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet_grove1 8.0 8.0 FALSE

				IF NOT IS_CHAR_IN_FLYING_VEHICLE scplayer

					IF IS_CHAR_IN_ANY_CAR scplayer
						STORE_CAR_CHAR_IS_IN scplayer stored_car_grove1
						GET_MAXIMUM_NUMBER_OF_PASSENGERS stored_car_grove1 max_passenger_grove1
						GET_NUMBER_OF_PASSENGERS stored_car_grove1 passenger_count_grove1

						no_of_space_grove1 =  max_passenger_grove1 - passenger_count_grove1 

						IF no_of_space_grove1 >= 1
							SET_GROUP_FOLLOW_STATUS Players_Group TRUE
							SET_GROUP_MEMBER Players_Group sweet_grove1
							REMOVE_BLIP sweet_blip_grove1

							IF NOT IS_CHAR_DEAD bdup_guard1_grove1 
								ADD_BLIP_FOR_CHAR bdup_guard1_grove1 bdup_guard1_blip_grove1
							ENDIF

							IF NOT IS_CHAR_DEAD bdup_guard2_grove1 
								ADD_BLIP_FOR_CHAR bdup_guard2_grove1 bdup_guard2_blip_grove1
							ENDIF

							IF NOT IS_CHAR_DEAD bdup_guard3_grove1 
								ADD_BLIP_FOR_CHAR bdup_guard3_grove1 bdup_guard3_blip_grove1
							ENDIF

							IF NOT IS_CHAR_DEAD bdup_guard4_grove1 
								ADD_BLIP_FOR_CHAR bdup_guard4_grove1 bdup_guard4_blip_grove1
							ENDIF

							IF NOT IS_CHAR_DEAD bdup_guard5_grove1 
								ADD_BLIP_FOR_CHAR bdup_guard5_grove1 bdup_guard5_blip_grove1
							ENDIF

							IF NOT IS_CHAR_DEAD bdup_guard6_grove1 
								ADD_BLIP_FOR_CHAR bdup_guard6_grove1 bdup_guard6_blip_grove1
							ENDIF

							IF NOT IS_CHAR_DEAD bdup_guard7_grove1 
								ADD_BLIP_FOR_CHAR bdup_guard7_grove1 bdup_guard7_blip_grove1
							ENDIF

							IF NOT IS_CHAR_DEAD bdup_guard8_grove1 
								ADD_BLIP_FOR_CHAR bdup_guard8_grove1 bdup_guard8_blip_grove1
							ENDIF
							
							blob_flag = 1
							had_room_message_grove1 = 0
							player_had_group_warning_grove1 = 0
						ELSE

							IF had_room_message_grove1 = 0

								IF HAS_MISSION_AUDIO_FINISHED 1
									PLAY_MISSION_AUDIO 2
									PRINT_NOW (GRO1_GA) 8000 1 //"Homes there ain't enough room get a bigger vehicle!"
									START_CHAR_FACIAL_TALK sweet_grove1 999999
									SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 TRUE
								ENDIF

								room_audio_playing_grove1 = 1
								flag_print_on_grove1 = 1
								had_room_message_grove1 = 1
							ELSE
								
								IF room_audio_playing_grove1 = 1

									IF HAS_MISSION_AUDIO_FINISHED 2
										STOP_CHAR_FACIAL_TALK sweet_grove1
										CLEAR_THIS_PRINT (GRO1_GA)
										SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 FALSE
										room_audio_playing_grove1 = 0
									ENDIF

								ENDIF

							ENDIF

						ENDIF  

					ELSE

						SET_GROUP_FOLLOW_STATUS Players_Group TRUE
						SET_GROUP_MEMBER Players_Group sweet_grove1
						REMOVE_BLIP sweet_blip_grove1
						blob_flag = 1

						IF NOT IS_CHAR_DEAD bdup_guard1_grove1 
							ADD_BLIP_FOR_CHAR bdup_guard1_grove1 bdup_guard1_blip_grove1
						ENDIF

						IF NOT IS_CHAR_DEAD bdup_guard2_grove1 
							ADD_BLIP_FOR_CHAR bdup_guard2_grove1 bdup_guard2_blip_grove1
						ENDIF

						IF NOT IS_CHAR_DEAD bdup_guard3_grove1 
							ADD_BLIP_FOR_CHAR bdup_guard3_grove1 bdup_guard3_blip_grove1
						ENDIF

						IF NOT IS_CHAR_DEAD bdup_guard4_grove1 
							ADD_BLIP_FOR_CHAR bdup_guard4_grove1 bdup_guard4_blip_grove1
						ENDIF

						IF NOT IS_CHAR_DEAD bdup_guard5_grove1 
							ADD_BLIP_FOR_CHAR bdup_guard5_grove1 bdup_guard5_blip_grove1
						ENDIF

						IF NOT IS_CHAR_DEAD bdup_guard6_grove1 
							ADD_BLIP_FOR_CHAR bdup_guard6_grove1 bdup_guard6_blip_grove1
						ENDIF

						IF NOT IS_CHAR_DEAD bdup_guard7_grove1 
							ADD_BLIP_FOR_CHAR bdup_guard7_grove1 bdup_guard7_blip_grove1
						ENDIF

						IF NOT IS_CHAR_DEAD bdup_guard8_grove1 
							ADD_BLIP_FOR_CHAR bdup_guard8_grove1 bdup_guard8_blip_grove1
						ENDIF

						player_had_group_warning_grove1 = 0
					ENDIF

				ELSE

					IF flag_has_flying_message_grove1 = 0

						IF HAS_MISSION_AUDIO_FINISHED 1
							PLAY_MISSION_AUDIO 2
							PRINT_NOW (GRO1_GA) 8000 1 //"Homes there ain't enough room get a bigger vehicle!"
							START_CHAR_FACIAL_TALK sweet_grove1 999999
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 TRUE
						ENDIF

						room_audio_playing_grove1 = 1
						flag_print_on_grove1 = 1
						flag_has_flying_message_grove1 = 1
					ELSE

						IF room_audio_playing_grove1 = 1

							IF HAS_MISSION_AUDIO_FINISHED 2
								STOP_CHAR_FACIAL_TALK sweet_grove1
								CLEAR_THIS_PRINT (GRO1_GA)
								SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 FALSE
								room_audio_playing_grove1 = 0
							ENDIF

						ENDIF
											
					ENDIF

			   	ENDIF

			ENDIF

		ENDIF

	ENDIF

	IF bdup_guard1_dead_grove1 = 0
	
		IF IS_CHAR_DEAD bdup_guard1_grove1
			REMOVE_BLIP bdup_guard1_blip_grove1
			++ counter_dbup_guards_dead
			bdup_guard1_dead_grove1 = 1
		ELSE

			IF flag_kill_player_grove1 = 0 

				IF IS_PLAYER_TARGETTING_CHAR player1 bdup_guard1_grove1
				OR HAS_CHAR_SPOTTED_CHAR bdup_guard1_grove1 scplayer 
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON bdup_guard1_grove1 WEAPONTYPE_ANYWEAPON
					flag_kill_player_grove1 = 1
				ENDIF
				
			ELSE
				
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bdup_guard1_grove1 100.0 100.0 30.0 FALSE

					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bdup_guard1_grove1 29.0 29.0 30.0 FALSE

						IF guard1_attacking_player_grove1 = 0						
							CLEAR_CHAR_TASKS bdup_guard1_grove1						 
							TASK_KILL_CHAR_ON_FOOT bdup_guard1_grove1 scplayer
							TASK_STAY_IN_SAME_PLACE bdup_guard1_grove1 TRUE
							guard1_attacking_player_grove1 = 1
						ENDIF

					ELSE
						
						IF guard1_attacking_coord_grove1 = 0
							CLEAR_CHAR_TASKS bdup_guard1_grove1
							TASK_STAY_IN_SAME_PLACE bdup_guard1_grove1 TRUE
							TASK_SET_IGNORE_WEAPON_RANGE_FLAG bdup_guard1_grove1 TRUE
							TASK_SHOOT_AT_CHAR bdup_guard1_grove1 scplayer -1
							guard1_attacking_coord_grove1 = 1
						ENDIF

					ENDIF										

				ELSE
					
					IF guard1_attacking_coord_grove1 = 1
					OR guard1_attacking_player_grove1 = 1  
						CLEAR_CHAR_TASKS bdup_guard1_grove1
						TASK_STAY_IN_SAME_PLACE bdup_guard1_grove1 TRUE
						guard1_attacking_player_grove1 = 0
						guard1_attacking_coord_grove1 = 0
					ENDIF

				ENDIF  	 

			ENDIF

		ENDIF
		
	ENDIF
	
	IF bdup_guard2_dead_grove1 = 0
	
		IF IS_CHAR_DEAD bdup_guard2_grove1
			REMOVE_BLIP bdup_guard2_blip_grove1
			++ counter_dbup_guards_dead
			bdup_guard2_dead_grove1 = 1
		ELSE

			IF flag_kill_player_grove1 = 0 

				IF IS_PLAYER_TARGETTING_CHAR player1 bdup_guard2_grove1
				OR HAS_CHAR_SPOTTED_CHAR bdup_guard2_grove1 scplayer
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON bdup_guard2_grove1 WEAPONTYPE_ANYWEAPON
					flag_kill_player_grove1 = 1
				ENDIF	 

			ELSE

				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bdup_guard2_grove1 100.0 100.0 30.0 FALSE

					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bdup_guard2_grove1 29.0 29.0 30.0 FALSE

						IF guard2_attacking_player_grove1 = 0						
							CLEAR_CHAR_TASKS bdup_guard2_grove1						 
							TASK_KILL_CHAR_ON_FOOT bdup_guard2_grove1 scplayer
							TASK_STAY_IN_SAME_PLACE bdup_guard2_grove1 TRUE
							guard2_attacking_player_grove1 = 1
						ENDIF

					ELSE
						
						IF guard2_attacking_coord_grove1 = 0
							CLEAR_CHAR_TASKS bdup_guard2_grove1
							TASK_STAY_IN_SAME_PLACE bdup_guard2_grove1 TRUE
							TASK_SET_IGNORE_WEAPON_RANGE_FLAG bdup_guard2_grove1 TRUE
							TASK_SHOOT_AT_CHAR bdup_guard2_grove1 scplayer -1
							guard2_attacking_coord_grove1 = 1
						ENDIF

					ENDIF										

				ELSE
					
					IF guard2_attacking_coord_grove1 = 1
					OR guard2_attacking_player_grove1 = 1  
						CLEAR_CHAR_TASKS bdup_guard2_grove1
						TASK_STAY_IN_SAME_PLACE bdup_guard2_grove1 TRUE
						guard2_attacking_player_grove1 = 0
						guard2_attacking_coord_grove1 = 0
					ENDIF

				ENDIF
				
			ENDIF

		ENDIF
		
	ENDIF
	
	IF bdup_guard3_dead_grove1 = 0
	
		IF IS_CHAR_DEAD bdup_guard3_grove1
			REMOVE_BLIP bdup_guard3_blip_grove1
			++ counter_dbup_guards_dead
			bdup_guard3_dead_grove1 = 1
		ELSE

			IF flag_kill_player_grove1 = 0 

				IF IS_PLAYER_TARGETTING_CHAR player1 bdup_guard3_grove1
				OR HAS_CHAR_SPOTTED_CHAR bdup_guard3_grove1 scplayer
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON bdup_guard3_grove1 WEAPONTYPE_ANYWEAPON
					flag_kill_player_grove1 = 1
				ENDIF	 

			ELSE

				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bdup_guard3_grove1 100.0 100.0 30.0 FALSE

					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bdup_guard3_grove1 29.0 29.0 30.0 FALSE

						IF guard3_attacking_player_grove1 = 0						
							CLEAR_CHAR_TASKS bdup_guard3_grove1						 
							TASK_KILL_CHAR_ON_FOOT bdup_guard3_grove1 scplayer
							TASK_STAY_IN_SAME_PLACE bdup_guard3_grove1 TRUE
							guard3_attacking_player_grove1 = 1
						ENDIF

					ELSE
						
						IF guard3_attacking_coord_grove1 = 0
							CLEAR_CHAR_TASKS bdup_guard3_grove1
							TASK_STAY_IN_SAME_PLACE bdup_guard3_grove1 TRUE
							TASK_SET_IGNORE_WEAPON_RANGE_FLAG bdup_guard3_grove1 TRUE
							TASK_SHOOT_AT_CHAR bdup_guard3_grove1 scplayer -1
							guard3_attacking_coord_grove1 = 1
						ENDIF

					ENDIF										

				ELSE
					
					IF guard3_attacking_coord_grove1 = 1
					OR guard3_attacking_player_grove1 = 1  
						CLEAR_CHAR_TASKS bdup_guard3_grove1
						TASK_STAY_IN_SAME_PLACE bdup_guard3_grove1 TRUE
						guard3_attacking_player_grove1 = 0
						guard3_attacking_coord_grove1 = 0
					ENDIF

				ENDIF

			ENDIF
			
		ENDIF
		
	ENDIF
	
	IF bdup_guard4_dead_grove1 = 0
	
		IF IS_CHAR_DEAD bdup_guard4_grove1
			REMOVE_BLIP bdup_guard4_blip_grove1
			++ counter_dbup_guards_dead
			bdup_guard4_dead_grove1 = 1
		ELSE
			
			IF flag_kill_player_grove1 = 0 

				IF IS_PLAYER_TARGETTING_CHAR player1 bdup_guard4_grove1
				OR HAS_CHAR_SPOTTED_CHAR bdup_guard4_grove1 scplayer
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON bdup_guard4_grove1 WEAPONTYPE_ANYWEAPON
					flag_kill_player_grove1 = 1
				ENDIF	 

			ELSE

				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bdup_guard4_grove1 100.0 100.0 30.0 FALSE

					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bdup_guard4_grove1 29.0 29.0 30.0	FALSE

						IF guard4_attacking_player_grove1 = 0						
							CLEAR_CHAR_TASKS bdup_guard4_grove1						 
							TASK_KILL_CHAR_ON_FOOT bdup_guard4_grove1 scplayer
							TASK_STAY_IN_SAME_PLACE bdup_guard4_grove1 TRUE
							guard4_attacking_player_grove1 = 1
						ENDIF

					ELSE
						
						IF guard4_attacking_coord_grove1 = 0
							CLEAR_CHAR_TASKS bdup_guard4_grove1
							TASK_STAY_IN_SAME_PLACE bdup_guard4_grove1 TRUE
							TASK_SET_IGNORE_WEAPON_RANGE_FLAG bdup_guard4_grove1 TRUE
							TASK_SHOOT_AT_CHAR bdup_guard4_grove1 scplayer -1
							guard4_attacking_coord_grove1 = 1
						ENDIF

					ENDIF										

				ELSE
					
					IF guard4_attacking_coord_grove1 = 1
					OR guard4_attacking_player_grove1 = 1  
						CLEAR_CHAR_TASKS bdup_guard4_grove1
						TASK_STAY_IN_SAME_PLACE bdup_guard4_grove1 TRUE
						guard4_attacking_player_grove1 = 0
						guard4_attacking_coord_grove1 = 0
					ENDIF

				ENDIF

			ENDIF

		ENDIF
		
	ENDIF
	
	IF bdup_guard5_dead_grove1 = 0
	
		IF IS_CHAR_DEAD bdup_guard5_grove1
			REMOVE_BLIP bdup_guard5_blip_grove1
			++ counter_dbup_guards_dead
			bdup_guard5_dead_grove1 = 1
		ELSE

			IF flag_kill_player_grove1 = 0 

				IF IS_PLAYER_TARGETTING_CHAR player1 bdup_guard5_grove1
				OR HAS_CHAR_SPOTTED_CHAR bdup_guard5_grove1 scplayer
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON bdup_guard5_grove1 WEAPONTYPE_ANYWEAPON
					flag_kill_player_grove1 = 1
				ENDIF	 

			ELSE
				
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bdup_guard5_grove1 100.0 100.0 30.0 FALSE

					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bdup_guard5_grove1 29.0 29.0 30.0 FALSE

						IF guard5_attacking_player_grove1 = 0						
							CLEAR_CHAR_TASKS bdup_guard5_grove1						 
							TASK_KILL_CHAR_ON_FOOT bdup_guard5_grove1 scplayer
							TASK_STAY_IN_SAME_PLACE bdup_guard5_grove1 TRUE
							guard5_attacking_player_grove1 = 1
						ENDIF

					ELSE
						
						IF guard5_attacking_coord_grove1 = 0
							CLEAR_CHAR_TASKS bdup_guard5_grove1
							TASK_STAY_IN_SAME_PLACE bdup_guard5_grove1 TRUE
							TASK_SET_IGNORE_WEAPON_RANGE_FLAG bdup_guard5_grove1 TRUE
							TASK_SHOOT_AT_CHAR bdup_guard5_grove1 scplayer -1
							guard5_attacking_coord_grove1 = 1
						ENDIF

					ENDIF										

				ELSE
					
					IF guard5_attacking_coord_grove1 = 1
					OR guard5_attacking_player_grove1 = 1  
						CLEAR_CHAR_TASKS bdup_guard5_grove1
						TASK_STAY_IN_SAME_PLACE bdup_guard5_grove1 TRUE
						guard5_attacking_player_grove1 = 0
						guard5_attacking_coord_grove1 = 0
					ENDIF

				ENDIF

			ENDIF

		ENDIF
		
	ENDIF
	
	IF bdup_guard6_dead_grove1 = 0
	
		IF IS_CHAR_DEAD bdup_guard6_grove1
			REMOVE_BLIP bdup_guard6_blip_grove1
			++ counter_dbup_guards_dead
			bdup_guard6_dead_grove1 = 1
		ELSE

			IF flag_kill_player_grove1 = 0 

				IF IS_PLAYER_TARGETTING_CHAR player1 bdup_guard6_grove1
				OR HAS_CHAR_SPOTTED_CHAR bdup_guard6_grove1 scplayer
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON bdup_guard6_grove1 WEAPONTYPE_ANYWEAPON
					flag_kill_player_grove1 = 1
				ENDIF	 

			ELSE

				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bdup_guard6_grove1 100.0 100.0 30.0 FALSE

					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bdup_guard6_grove1 29.0 29.0 30.0 FALSE

						IF guard6_attacking_player_grove1 = 0						
							CLEAR_CHAR_TASKS bdup_guard6_grove1						 
							TASK_KILL_CHAR_ON_FOOT bdup_guard6_grove1 scplayer
							TASK_STAY_IN_SAME_PLACE bdup_guard6_grove1 TRUE
							guard6_attacking_player_grove1 = 1
						ENDIF

					ELSE
						
						IF guard6_attacking_coord_grove1 = 0
							CLEAR_CHAR_TASKS bdup_guard6_grove1
							TASK_STAY_IN_SAME_PLACE bdup_guard6_grove1 TRUE
							TASK_SET_IGNORE_WEAPON_RANGE_FLAG bdup_guard6_grove1 TRUE
							TASK_SHOOT_AT_CHAR bdup_guard6_grove1 scplayer -1
							guard6_attacking_coord_grove1 = 1
						ENDIF

					ENDIF										

				ELSE
					
					IF guard6_attacking_coord_grove1 = 1
					OR guard6_attacking_player_grove1 = 1  
						CLEAR_CHAR_TASKS bdup_guard6_grove1
						TASK_STAY_IN_SAME_PLACE bdup_guard6_grove1 TRUE
						guard6_attacking_player_grove1 = 0
						guard6_attacking_coord_grove1 = 0
					ENDIF

				ENDIF

			ENDIF

		ENDIF
		
	ENDIF
	
	IF bdup_guard7_dead_grove1 = 0
	
		IF IS_CHAR_DEAD bdup_guard7_grove1
			REMOVE_BLIP bdup_guard7_blip_grove1
			++ counter_dbup_guards_dead
			bdup_guard7_dead_grove1 = 1
		ELSE

			IF flag_kill_player_grove1 = 0 

				IF IS_PLAYER_TARGETTING_CHAR player1 bdup_guard7_grove1
				OR HAS_CHAR_SPOTTED_CHAR bdup_guard7_grove1 scplayer
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON bdup_guard7_grove1 WEAPONTYPE_ANYWEAPON
					flag_kill_player_grove1 = 1
				ENDIF	 

			ELSE
				
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bdup_guard7_grove1 100.0 100.0 30.0 FALSE

					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bdup_guard7_grove1 29.0 29.0 30.0 FALSE

						IF guard7_attacking_player_grove1 = 0						
							CLEAR_CHAR_TASKS bdup_guard7_grove1						 
							TASK_KILL_CHAR_ON_FOOT bdup_guard7_grove1 scplayer
							TASK_STAY_IN_SAME_PLACE bdup_guard7_grove1 TRUE
							guard7_attacking_player_grove1 = 1
						ENDIF

					ELSE
						
						IF guard7_attacking_coord_grove1 = 0
							CLEAR_CHAR_TASKS bdup_guard7_grove1
							TASK_STAY_IN_SAME_PLACE bdup_guard7_grove1 TRUE
							TASK_SET_IGNORE_WEAPON_RANGE_FLAG bdup_guard7_grove1 TRUE
							TASK_SHOOT_AT_CHAR bdup_guard7_grove1 scplayer -1
							guard7_attacking_coord_grove1 = 1
						ENDIF

					ENDIF										

				ELSE
					
					IF guard7_attacking_coord_grove1 = 1
					OR guard7_attacking_player_grove1 = 1  
						CLEAR_CHAR_TASKS bdup_guard7_grove1
						TASK_STAY_IN_SAME_PLACE bdup_guard7_grove1 TRUE
						guard7_attacking_player_grove1 = 0
						guard7_attacking_coord_grove1 = 0
					ENDIF

				ENDIF

			ENDIF

		ENDIF
		
	ENDIF

	IF bdup_guard8_dead_grove1 = 0
	
		IF IS_CHAR_DEAD bdup_guard8_grove1
			REMOVE_BLIP bdup_guard8_blip_grove1
			++ counter_dbup_guards_dead
			bdup_guard8_dead_grove1 = 1
		ELSE

			IF flag_kill_player_grove1 = 0 

				IF IS_PLAYER_TARGETTING_CHAR player1 bdup_guard8_grove1
				OR HAS_CHAR_SPOTTED_CHAR bdup_guard8_grove1 scplayer
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON bdup_guard8_grove1 WEAPONTYPE_ANYWEAPON
					flag_kill_player_grove1 = 1
				ENDIF	 

			ELSE
				
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bdup_guard8_grove1 100.0 100.0 30.0 FALSE

					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bdup_guard8_grove1 29.0 29.0 30.0 FALSE

						IF guard8_attacking_player_grove1 = 0						
							CLEAR_CHAR_TASKS bdup_guard8_grove1						 
							TASK_KILL_CHAR_ON_FOOT bdup_guard8_grove1 scplayer
							TASK_STAY_IN_SAME_PLACE bdup_guard8_grove1 TRUE
							guard8_attacking_player_grove1 = 1
						ENDIF

					ELSE
						
						IF guard8_attacking_coord_grove1 = 0
							CLEAR_CHAR_TASKS bdup_guard8_grove1
							TASK_STAY_IN_SAME_PLACE bdup_guard8_grove1 TRUE
							TASK_SET_IGNORE_WEAPON_RANGE_FLAG bdup_guard8_grove1 TRUE
							TASK_SHOOT_AT_CHAR bdup_guard8_grove1 scplayer -1
							guard8_attacking_coord_grove1 = 1
						ENDIF

					ENDIF										

				ELSE
					
					IF guard8_attacking_coord_grove1 = 1
					OR guard8_attacking_player_grove1 = 1  
						CLEAR_CHAR_TASKS bdup_guard8_grove1
						TASK_STAY_IN_SAME_PLACE bdup_guard8_grove1 TRUE
						guard8_attacking_player_grove1 = 0
						guard8_attacking_coord_grove1 = 0
					ENDIF

				ENDIF

			ENDIF

		ENDIF
		
	ENDIF

   	IF flag_kill_player_grove1 = 1 
		
		IF dbup_guys_got_ai_grove1 = 0 
			GOSUB give_dbup_ai_grove1
		ENDIF

	ENDIF

	 
ENDWHILE

CLEAR_MISSION_AUDIO 1
CLEAR_PRINTS

PRINT_NOW (GM1_15) 8000 1 //"Go and confront Bdup!"
ADD_BLIP_FOR_COORD 2000.269 -1117.084 25.821 dbup_house_blip_grove1
blob_flag = 1

room_audio_playing_grove1 = 0
						   
WHILE NOT LOCATE_CHAR_ON_FOOT_3D scplayer 2000.269 -1117.084 25.821 2.0 2.0 2.0 blob_flag
OR NOT IS_GROUP_MEMBER sweet_grove1 Players_Group

	WAIT 0

	IF IS_CHAR_DEAD sweet_grove1
		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"

		IF player_had_group_warning_grove1 = 1 
	   		REMOVE_BLIP sweet_blip_grove1
		ENDIF

		GOTO mission_grove1_failed
	ELSE

		GET_CHAR_HEALTH sweet_grove1 sweets_health_grove1
		sweets_health_grove1 = sweets_health_grove1 / 10

		// clear the not enough room print   
		IF flag_print_on_grove1 = 1
		
			IF HAS_MISSION_AUDIO_FINISHED 2
				CLEAR_THIS_PRINT (GRO1_GA)
				flag_print_on_grove1 = 0
			ENDIF

		ENDIF

		IF NOT IS_GROUP_MEMBER sweet_grove1 Players_Group
		AND player_had_group_warning_grove1 = 0 			
			REMOVE_BLIP dbup_house_blip_grove1			
			ADD_BLIP_FOR_CHAR sweet_grove1 sweet_blip_grove1
			SET_BLIP_AS_FRIENDLY sweet_blip_grove1 TRUE 
			PRINT_NOW (GM1_2) 8000 1 //"You have left Sweet behind go get him."
			blob_flag = 0
			flag_has_flying_message_grove1 = 0
			player_had_group_warning_grove1 = 1
		ENDIF

		IF player_had_group_warning_grove1 = 1
			
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet_grove1 8.0 8.0 FALSE

				IF NOT IS_CHAR_IN_FLYING_VEHICLE scplayer

					IF IS_CHAR_IN_ANY_CAR scplayer
						STORE_CAR_CHAR_IS_IN scplayer stored_car_grove1
						GET_MAXIMUM_NUMBER_OF_PASSENGERS stored_car_grove1 max_passenger_grove1
						GET_NUMBER_OF_PASSENGERS stored_car_grove1 passenger_count_grove1

						no_of_space_grove1 =  max_passenger_grove1 - passenger_count_grove1 

						IF no_of_space_grove1 >= 1
							SET_GROUP_FOLLOW_STATUS Players_Group TRUE
							SET_GROUP_MEMBER Players_Group sweet_grove1
							REMOVE_BLIP sweet_blip_grove1 
							ADD_BLIP_FOR_COORD 2000.269 -1117.084 25.821 dbup_house_blip_grove1
							blob_flag = 1
							had_room_message_grove1 = 0
							player_had_group_warning_grove1 = 0
						ELSE

							IF had_room_message_grove1 = 0

								IF HAS_MISSION_AUDIO_FINISHED 1
									PLAY_MISSION_AUDIO 2
									PRINT_NOW (GRO1_GA) 8000 1 //"Homes there ain't enough room get a bigger vehicle!"
									START_CHAR_FACIAL_TALK sweet_grove1 999999
									SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 TRUE
								ENDIF

								room_audio_playing_grove1 = 1
								flag_print_on_grove1 = 1
								had_room_message_grove1 = 1
							ELSE

								IF room_audio_playing_grove1 = 1

									IF HAS_MISSION_AUDIO_FINISHED 2
										STOP_CHAR_FACIAL_TALK sweet_grove1
										CLEAR_THIS_PRINT (GRO1_GA)
										SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 FALSE
										room_audio_playing_grove1 = 0
									ENDIF

								ENDIF

							ENDIF

						ENDIF

					ELSE
						SET_GROUP_FOLLOW_STATUS Players_Group TRUE
						SET_GROUP_MEMBER Players_Group sweet_grove1
						REMOVE_BLIP sweet_blip_grove1 
						ADD_BLIP_FOR_COORD 2000.269 -1117.084 25.821 dbup_house_blip_grove1
						blob_flag = 1
						player_had_group_warning_grove1 = 0
					ENDIF

				ELSE

					IF flag_has_flying_message_grove1 = 0

						IF HAS_MISSION_AUDIO_FINISHED 1
							PLAY_MISSION_AUDIO 2
							PRINT_NOW (GRO1_GA) 8000 1 //"Homes there ain't enough room get a bigger vehicle!"
							START_CHAR_FACIAL_TALK sweet_grove1 999999
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 TRUE
						ENDIF

						room_audio_playing_grove1 = 1
						flag_print_on_grove1 = 1
						flag_has_flying_message_grove1 = 1
					ELSE
						
						IF room_audio_playing_grove1 = 1

							IF HAS_MISSION_AUDIO_FINISHED 2
								STOP_CHAR_FACIAL_TALK sweet_grove1
								CLEAR_THIS_PRINT (GRO1_GA)
								SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 FALSE
								room_audio_playing_grove1 = 0
							ENDIF

						ENDIF

					ENDIF

				ENDIF

			ENDIF
			
		ENDIF

	ENDIF

ENDWHILE

CLEAR_ONSCREEN_COUNTER sweets_health_grove1
REMOVE_BLIP dbup_house_blip_grove1
CLEAR_PRINTS 

// ********************************************** MID MISSION ANIMATED CUT ********************************************
SET_PLAYER_CONTROL player1 OFF
SET_EVERYONE_IGNORE_PLAYER player1 TRUE
SET_POLICE_IGNORE_PLAYER player1 TRUE

IF NOT IS_CHAR_DEAD sweet_grove1 
	SET_CHAR_PROOFS sweet_grove1 TRUE TRUE TRUE TRUE TRUE
ELSE
	PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
	GOTO mission_grove1_failed
ENDIF

SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS

	WAIT 0

//	IF IS_CHAR_DEAD sweet_grove1
//		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
//
//		IF player_had_group_warning_grove1 = 1 
//	   		REMOVE_BLIP sweet_blip_grove1
//		ENDIF
//
//		GOTO mission_grove1_failed
//	ENDIF

ENDWHILE

MAKE_PLAYER_GANG_DISAPPEAR

DELETE_CHAR sweet_grove1
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS3
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
MARK_MODEL_AS_NO_LONGER_NEEDED AK47

SET_AREA_VISIBLE 11

MAKE_PLAYER_SAFE_FOR_CUTSCENE player1
SET_CHAR_COORDINATES scplayer 1044.8799 -1334.3197 12.5500 

//At B-dups new place

SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS

	WAIT 0

ENDWHILE

SET_AREA_VISIBLE 2

LOAD_CUTSCENE GROVE1c
 
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

SET_AREA_VISIBLE 0

SET_PLAYER_CONTROL player1 OFF

// ************** BIG BEAR CUTSCENE *******************

LOAD_SPECIAL_CHARACTER 1 SWEET
LOAD_SPECIAL_CHARACTER 2 BBTHIN
REQUEST_ANIMATION GANGS

LOAD_MISSION_AUDIO 1 SOUND_GRO1_BA 
LOAD_MISSION_AUDIO 2 SOUND_GRO1_BB

WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
OR NOT HAS_SPECIAL_CHARACTER_LOADED 2
OR NOT HAS_ANIMATION_LOADED GANGS
OR NOT HAS_MISSION_AUDIO_LOADED 1
OR NOT HAS_MISSION_AUDIO_LOADED 2

	WAIT 0

ENDWHILE
 
SWITCH_WIDESCREEN ON

SWITCH_PED_ROADS_OFF 1965.968 -1096.668 20.0 2081.465 -1163.309 30.0

CLEAR_AREA 2003.217 -1132.552 24.278 2.0 FALSE 
CREATE_CAR INFERNUS 2003.217 -1132.552 24.278 car2_grove1
SET_CAR_PROOFS car2_grove1 TRUE TRUE TRUE TRUE TRUE 
SET_CAR_HEADING car2_grove1 90.0 

CREATE_CHAR_AS_PASSENGER car2_grove1 PEDTYPE_MISSION1 SPECIAL02 0 big_bear_grove1
SET_CHAR_PROOFS big_bear_grove1 TRUE TRUE TRUE TRUE TRUE
SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_bear_grove1 TRUE	

REQUEST_COLLISION 2002.105 -1129.403 
  
CLEAR_AREA 2002.105 -1129.403 24.484 1.0 TRUE 
SET_CHAR_COORDINATES scplayer 2002.105 -1129.403 24.484
SET_CHAR_HEADING scplayer 270.0
SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE

LOAD_SCENE 2002.105 -1129.403 24.484  

CLEAR_AREA 2003.105 -1129.403 24.484 1.0 TRUE 
CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 2003.105 -1129.403 24.484 sweet_grove1
SET_CHAR_PROOFS sweet_grove1 TRUE TRUE TRUE TRUE TRUE 
SET_CHAR_HEADING sweet_grove1 90.0
SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 TRUE

CLEAR_AREA 2016.976 -1130.804 24.620 60.0 TRUE
SET_FIXED_CAMERA_POSITION 2006.7113 -1126.8674 24.6168 0.0 0.0 0.0
POINT_CAMERA_AT_POINT 2006.0042 -1127.5514 24.7956 JUMP_CUT

SET_FADING_COLOUR 0 0 0

DO_FADE 1500 FADE_IN

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

SKIP_CUTSCENE_START

IF NOT IS_CHAR_DEAD sweet_grove1
	TASK_PLAY_ANIM sweet_grove1 hndshkfa GANGS 4.0 FALSE FALSE FALSE FALSE -1
	PLAY_MISSION_AUDIO 1
	PRINT_NOW (GRO1_BA) 10000 1 //"I’ll take care of Bear, man.
	START_CHAR_FACIAL_TALK sweet_grove1 999999
ELSE
	CLEAR_PRINTS
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
	GOTO mission_grove1_failed
ENDIF

TASK_PLAY_ANIM scplayer hndshkfa GANGS 4.0 FALSE FALSE FALSE FALSE -1

WHILE NOT HAS_MISSION_AUDIO_FINISHED 1

	WAIT 0

	IF IS_CHAR_DEAD sweet_grove1
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
		GOTO mission_grove1_failed
	ENDIF

	IF IS_CHAR_DEAD big_bear_grove1
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
		GOTO mission_grove1_failed
	ENDIF

	IF IS_CAR_DEAD car2_grove1
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
		GOTO mission_grove1_failed
	ENDIF

ENDWHILE

CLEAR_MISSION_AUDIO 1
CLEAR_THIS_PRINT (GRO1_BA)

IF NOT IS_CHAR_DEAD sweet_grove1
	STOP_CHAR_FACIAL_TALK sweet_grove1
ELSE
	CLEAR_PRINTS
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
	GOTO mission_grove1_failed
ENDIF

LOAD_MISSION_AUDIO 1 SOUND_GRO1_BC

PLAY_MISSION_AUDIO 2
PRINT_NOW (GRO1_BB) 10000 1 //"Ok. See you in while, Big Bear!
START_CHAR_FACIAL_TALK scplayer 999999

WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
OR NOT HAS_MISSION_AUDIO_LOADED 1

	WAIT 0

	IF IS_CHAR_DEAD sweet_grove1
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
		GOTO mission_grove1_failed
	ENDIF

	IF IS_CHAR_DEAD big_bear_grove1
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
		GOTO mission_grove1_failed
	ENDIF

	IF IS_CAR_DEAD car2_grove1
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
		GOTO mission_grove1_failed
	ENDIF
	
ENDWHILE

CLEAR_THIS_PRINT (GRO1_BB)
CLEAR_MISSION_AUDIO 2
STOP_CHAR_FACIAL_TALK scplayer

LOAD_MISSION_AUDIO 2 SOUND_GRO1_BD

PLAY_MISSION_AUDIO 1
PRINT_NOW (GRO1_BC) 10000 1 //"Where we going, Sweet?"

IF NOT IS_CHAR_DEAD big_bear_grove1
	START_CHAR_FACIAL_TALK big_bear_grove1 999999
ELSE
	CLEAR_PRINTS
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
	GOTO mission_grove1_failed
ENDIF   

TASK_ACHIEVE_HEADING scplayer 180.0

IF NOT IS_CAR_DEAD car2_grove1

	IF NOT IS_CHAR_DEAD sweet_grove1
		TASK_ENTER_CAR_AS_DRIVER sweet_grove1 car2_grove1 -1
	ELSE
		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
		GOTO mission_grove1_failed
	ENDIF

ELSE
	PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
	GOTO mission_grove1_failed
ENDIF

WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
OR NOT HAS_MISSION_AUDIO_LOADED 2

	WAIT 0

	IF IS_CHAR_DEAD sweet_grove1
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
		GOTO mission_grove1_failed
	ENDIF

	IF IS_CHAR_DEAD big_bear_grove1
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
		GOTO mission_grove1_failed
	ELSE

		IF HAS_MISSION_AUDIO_FINISHED 1
			STOP_CHAR_FACIAL_TALK big_bear_grove1
		ENDIF
		
	ENDIF

	IF IS_CAR_DEAD car2_grove1
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2 
		PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
		GOTO mission_grove1_failed
	ENDIF

ENDWHILE

CLEAR_THIS_PRINT (GRO1_BC)
CLEAR_MISSION_AUDIO 1

IF IS_CHAR_DEAD big_bear_grove1
	CLEAR_PRINTS
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
	GOTO mission_grove1_failed
ELSE
	STOP_CHAR_FACIAL_TALK big_bear_grove1
ENDIF

LOAD_MISSION_AUDIO 1 SOUND_GRO1_BE

PLAY_MISSION_AUDIO 2
PRINT_NOW (GRO1_BD) 10000 1 //"Someplace we can get the old Bear back, man.

IF NOT IS_CHAR_DEAD sweet_grove1
	START_CHAR_FACIAL_TALK sweet_grove1 999999
ELSE
	CLEAR_PRINTS
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
	GOTO mission_grove1_failed
ENDIF

WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
OR NOT HAS_MISSION_AUDIO_LOADED 1

	WAIT 0

	IF IS_CHAR_DEAD sweet_grove1
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
		GOTO mission_grove1_failed
	ELSE

		IF HAS_MISSION_AUDIO_FINISHED 2
			STOP_CHAR_FACIAL_TALK sweet_grove1
		ENDIF
		
	ENDIF

	IF IS_CHAR_DEAD big_bear_grove1
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
		GOTO mission_grove1_failed
	ENDIF

	IF IS_CAR_DEAD car2_grove1
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2 
		PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
		GOTO mission_grove1_failed
	ENDIF

ENDWHILE

CLEAR_THIS_PRINT (GRO1_BD)
CLEAR_MISSION_AUDIO 2

IF IS_CHAR_DEAD sweet_grove1
	CLEAR_PRINTS
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
	GOTO mission_grove1_failed
ELSE
	STOP_CHAR_FACIAL_TALK sweet_grove1
ENDIF

PLAY_MISSION_AUDIO 1
PRINT_NOW (GRO1_BE) 10000 1 //"Aigh’t. I’m down for that...

IF NOT IS_CHAR_DEAD big_bear_grove1
	START_CHAR_FACIAL_TALK big_bear_grove1 999999
ELSE
	CLEAR_PRINTS
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
	GOTO mission_grove1_failed
ENDIF

WHILE NOT IS_CHAR_IN_ANY_CAR sweet_grove1

	WAIT 0

	IF HAS_MISSION_AUDIO_FINISHED 1
		CLEAR_THIS_PRINT (GRO1_BE)

		IF NOT IS_CHAR_DEAD big_bear_grove1
			STOP_CHAR_FACIAL_TALK big_bear_grove1
		ELSE
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
			GOTO mission_grove1_failed
		ENDIF

	ENDIF 

	IF IS_CHAR_DEAD sweet_grove1
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
		GOTO mission_grove1_failed
	ENDIF

	IF IS_CHAR_DEAD big_bear_grove1
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
		GOTO mission_grove1_failed
	ENDIF

	IF IS_CAR_DEAD car2_grove1
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2 
		PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
		GOTO mission_grove1_failed
	ENDIF

ENDWHILE

IF NOT IS_CAR_DEAD car2_grove1
	SET_CAR_DRIVING_STYLE car2_grove1 2
	SET_CAR_CRUISE_SPEED car2_grove1 30.0
	CAR_GOTO_COORDINATES car2_grove1 1946.0 -1132.0 25.0
ELSE
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	CLEAR_PRINTS
	PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
	GOTO mission_grove1_failed
ENDIF

WHILE NOT HAS_MISSION_AUDIO_FINISHED 1

	WAIT 0

	IF IS_CHAR_DEAD sweet_grove1
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		PRINT_NOW (GM1_4) 5000 1 //"Sweets dead!"
		GOTO mission_grove1_failed
	ENDIF

	IF IS_CHAR_DEAD big_bear_grove1
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
		GOTO mission_grove1_failed
	ENDIF

	IF IS_CAR_DEAD car2_grove1
		CLEAR_PRINTS
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2 
		PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
		GOTO mission_grove1_failed
	ENDIF

ENDWHILE

CLEAR_THIS_PRINT (GRO1_BE)
CLEAR_MISSION_AUDIO 1
CLEAR_MISSION_AUDIO 2

IF NOT IS_CHAR_DEAD big_bear_grove1
	STOP_CHAR_FACIAL_TALK big_bear_grove1
ELSE
	CLEAR_PRINTS
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	PRINT_NOW (GM1_12) 8000 1 //"Big Bear is dead!"
	GOTO mission_grove1_failed
ENDIF

WAIT 2000

REMOVE_CHAR_ELEGANTLY big_bear_grove1
REMOVE_CHAR_ELEGANTLY sweet_grove1
DELETE_CAR car2_grove1

MAKE_PLAYER_GANG_REAPPEAR

SET_PLAYER_CONTROL player1 ON
SWITCH_WIDESCREEN OFF
SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT
SET_EVERYONE_IGNORE_PLAYER player1 FALSE
SET_POLICE_IGNORE_PLAYER player1 FALSE

SWITCH_PED_ROADS_BACK_TO_ORIGINAL 1965.968 -1096.668 20.0 2081.465 -1163.309 30.0

cut_watched_grove1 = 1
SKIP_CUTSCENE_END

IF cut_watched_grove1 = 0

	CLEAR_PRINTS

	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2

	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_OUT
	
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	MAKE_PLAYER_GANG_REAPPEAR

	SWITCH_WIDESCREEN OFF

	CLEAR_PRINTS
	
	DELETE_CHAR big_bear_grove1
	DELETE_CHAR sweet_grove1
	DELETE_CAR car2_grove1

	SWITCH_PED_ROADS_BACK_TO_ORIGINAL 1965.968 -1096.668 20.0 2081.465 -1163.309 30.0
	
	SET_CHAR_COORDINATES scplayer 2002.105 -1129.403 24.484
	SET_CHAR_HEADING scplayer 180.0
	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT

	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_IN

	WHILE GET_FADING_STATUS

		WAIT 0

	ENDWHILE

ENDIF

SWITCH_WIDESCREEN OFF
SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
SET_PLAYER_CONTROL player1 ON
SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT
SET_EVERYONE_IGNORE_PLAYER player1 OFF
SET_POLICE_IGNORE_PLAYER player1 OFF 

		
GOTO mission_grove1_passed

 // **************************************** Mission grove1 failed ************************

mission_grove1_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"

SET_ZONE_GANG_STRENGTH GLN1 GANG_FLAT 40
SET_ZONE_GANG_STRENGTH GLN1 GANG_GROVE 0

SET_ZONE_GANG_STRENGTH GLN1 GANG_NMEX stored_nmex_strength_grove1
SET_ZONE_GANG_STRENGTH GLN1 GANG_SMEX stored_smex_strength_grove1

CAN_TRIGGER_GANG_WAR_WHEN_ON_A_MISSION FALSE

RETURN

   

// **************************************** mission grove1 passed *************************

mission_grove1_passed:

flag_grove_mission_counter ++
REGISTER_MISSION_PASSED (GROVE_1) //Used in the stats
PLAYER_MADE_PROGRESS 1

PRINT_WITH_NUMBER_BIG ( M_PASSR ) 10 5000 1 //"Mission Passed!"
AWARD_PLAYER_MISSION_RESPECT  40 //amount of respect
CLEAR_WANTED_LEVEL player1
PLAY_MISSION_PASSED_TUNE 1

REMOVE_BLIP grove_contact_blip
REMOVE_BLIP intro_contact_blip

ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ grove_blip_icon grove_contact_blip

RETURN
		

// ********************************** mission cleanup ************************************

mission_cleanup_grove1:

REMOVE_CHAR_ELEGANTLY sweet_grove1

flag_player_on_mission = 0

SWITCH_PED_ROADS_BACK_TO_ORIGINAL 1965.968 -1096.668 20.0 2081.465 -1163.309 30.0

SET_AREA_VISIBLE 0

CLEAR_SPECIFIC_ZONES_TO_TRIGGER_GANG_WAR

GET_GAME_TIMER timer_mobile_start

CLEAR_ONSCREEN_COUNTER sweets_health_grove1

UNLOAD_SPECIAL_CHARACTER 1
UNLOAD_SPECIAL_CHARACTER 2
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS3
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
MARK_MODEL_AS_NO_LONGER_NEEDED AK47
MARK_MODEL_AS_NO_LONGER_NEEDED INFERNUS

REMOVE_ANIMATION GANGS

// old house blip
REMOVE_BLIP old_house_blip_grove1

// park stuff
REMOVE_BLIP park_blip_grove1

// sweet
REMOVE_BLIP sweet_blip_grove1

// bdup
REMOVE_BLIP dbup_house_blip_grove1

// guard blips
REMOVE_BLIP bdup_guard1_blip_grove1
REMOVE_BLIP	bdup_guard2_blip_grove1
REMOVE_BLIP	bdup_guard3_blip_grove1
REMOVE_BLIP	bdup_guard4_blip_grove1
REMOVE_BLIP	bdup_guard5_blip_grove1
REMOVE_BLIP	bdup_guard6_blip_grove1
REMOVE_BLIP	bdup_guard7_blip_grove1
REMOVE_BLIP	bdup_guard8_blip_grove1

// sequences
CLEAR_SEQUENCE_TASK guard5_seq_grove1
CLEAR_SEQUENCE_TASK guard6_seq_grove1
CLEAR_SEQUENCE_TASK guard7_seq_grove1
CLEAR_SEQUENCE_TASK guard8_seq_grove1

// Decision Makers
REMOVE_DECISION_MAKER tough_duck_dm_grove1
REMOVE_DECISION_MAKER tough_decisionmaker_grove1

CAN_TRIGGER_GANG_WAR_WHEN_ON_A_MISSION FALSE

MISSION_HAS_FINISHED
RETURN

// gives dbup guys ai to attack player
give_dbup_ai_grove1:

IF NOT IS_CHAR_DEAD bdup_guard5_grove1

	OPEN_SEQUENCE_TASK guard5_seq_grove1
		TASK_GO_STRAIGHT_TO_COORD -1 1996.934 -1122.258 25.760 PEDMOVE_RUN -1
		TASK_TOGGLE_DUCK -1 TRUE 
		TASK_STAY_IN_SAME_PLACE -1 TRUE
//		TASK_KILL_CHAR_ON_FOOT -1 scplayer

	CLOSE_SEQUENCE_TASK guard5_seq_grove1
	
	CLEAR_CHAR_TASKS bdup_guard5_grove1
	PERFORM_SEQUENCE_TASK bdup_guard5_grove1 guard5_seq_grove1
	CLEAR_SEQUENCE_TASK guard5_seq_grove1  

ENDIF

IF NOT IS_CHAR_DEAD bdup_guard6_grove1

	OPEN_SEQUENCE_TASK guard6_seq_grove1
		TASK_GO_STRAIGHT_TO_COORD -1 2004.069 -1122.884 25.584 PEDMOVE_RUN -1
		TASK_TOGGLE_DUCK -1 TRUE
		TASK_STAY_IN_SAME_PLACE -1 TRUE 
//		TASK_KILL_CHAR_ON_FOOT -1 scplayer
	CLOSE_SEQUENCE_TASK guard6_seq_grove1
	
	CLEAR_CHAR_TASKS bdup_guard6_grove1
	PERFORM_SEQUENCE_TASK bdup_guard6_grove1 guard6_seq_grove1
	CLEAR_SEQUENCE_TASK guard6_seq_grove1

ENDIF

IF NOT IS_CHAR_DEAD bdup_guard7_grove1

	OPEN_SEQUENCE_TASK guard7_seq_grove1
		TASK_GO_STRAIGHT_TO_COORD -1 2006.758 -1122.695 25.493 PEDMOVE_RUN -1
		TASK_TOGGLE_DUCK -1 TRUE
		TASK_STAY_IN_SAME_PLACE -1 TRUE 
//		TASK_KILL_CHAR_ON_FOOT -1 scplayer
	CLOSE_SEQUENCE_TASK guard7_seq_grove1
	
	CLEAR_CHAR_TASKS bdup_guard7_grove1
	PERFORM_SEQUENCE_TASK bdup_guard7_grove1 guard7_seq_grove1
	CLEAR_SEQUENCE_TASK guard7_seq_grove1

ENDIF

IF NOT IS_CHAR_DEAD bdup_guard8_grove1

	OPEN_SEQUENCE_TASK guard8_seq_grove1
		TASK_GO_STRAIGHT_TO_COORD -1 2015.458 -1121.923 25.243 PEDMOVE_RUN -1
		TASK_TOGGLE_DUCK -1 TRUE
		TASK_STAY_IN_SAME_PLACE -1 TRUE 
//		TASK_KILL_CHAR_ON_FOOT -1 scplayer
	CLOSE_SEQUENCE_TASK guard8_seq_grove1
	
	CLEAR_CHAR_TASKS bdup_guard8_grove1
	PERFORM_SEQUENCE_TASK bdup_guard8_grove1 guard8_seq_grove1
	CLEAR_SEQUENCE_TASK guard8_seq_grove1
ENDIF

dbup_guys_got_ai_grove1 = 1

RETURN

load_and_play_audio_grove1:

	IF grove1_audio_is_playing = 0
	OR grove1_audio_is_playing = 1
		IF grove1_index <= cell_index_end
			GOSUB play_grove1_audio
		ENDIF
	ENDIF

	IF grove1_audio_is_playing = 2
		IF HAS_MISSION_AUDIO_FINISHED 1
			GOSUB stop_mouths_move_grove1
			grove1_audio_is_playing = 0
			grove1_index ++
			grove1_cutscene_flag = 0
			CLEAR_PRINTS	
		ENDIF
	ENDIF

RETURN

play_grove1_audio:

	IF grove1_audio_is_playing = 0
		LOAD_MISSION_AUDIO 1 grove1_audio_chat[grove1_index]
		grove1_audio_is_playing = 1
	ENDIF
	IF grove1_audio_is_playing = 1
		IF HAS_MISSION_AUDIO_LOADED 1

			IF HAS_MISSION_AUDIO_FINISHED 2
				PRINT_NOW ( $grove1_chat[grove1_index] ) 4000 1 //Dummy message"
				PLAY_MISSION_AUDIO 1
				GOSUB make_mouths_move_grove1
				grove1_audio_is_playing = 2
			ENDIF

		ENDIF
	ENDIF	
	
RETURN

make_mouths_move_grove1:

	IF grove1_chat_switch = GROVE1_CHAT1

		IF grove1_index = 0
		OR grove1_index = 4
		OR grove1_index = 5
		OR grove1_index = 6
		OR grove1_index = 7
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
			START_CHAR_FACIAL_TALK scplayer 999999
		ELSE
			
			IF NOT IS_CHAR_DEAD sweet_grove1
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 TRUE
				START_CHAR_FACIAL_TALK sweet_grove1 999999
			ENDIF

		ENDIF

	ENDIF

	IF grove1_chat_switch = GROVE1_CHAT2

		IF grove1_index = 0
		OR grove1_index = 2
		OR grove1_index = 3
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
			START_CHAR_FACIAL_TALK scplayer 999999
		ELSE
			
			IF NOT IS_CHAR_DEAD sweet_grove1
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 TRUE
				START_CHAR_FACIAL_TALK sweet_grove1 999999
			ENDIF

		ENDIF

	ENDIF

	IF grove1_chat_switch = GROVE1_CHAT3

		IF grove1_index = 1
		OR grove1_index = 3
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
			START_CHAR_FACIAL_TALK scplayer 999999
		ELSE
			
			IF NOT IS_CHAR_DEAD sweet_grove1
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 TRUE
				START_CHAR_FACIAL_TALK sweet_grove1 999999
			ENDIF

		ENDIF

	ENDIF

	IF grove1_chat_switch = GROVE1_CHAT4

		IF grove1_index = 1
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
			START_CHAR_FACIAL_TALK scplayer 999999
		ELSE
			
			IF NOT IS_CHAR_DEAD sweet_grove1
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 TRUE
				START_CHAR_FACIAL_TALK sweet_grove1 999999
			ENDIF

		ENDIF

	ENDIF

RETURN

stop_mouths_move_grove1:

	IF grove1_chat_switch = GROVE1_CHAT1

		IF grove1_index = 0
		OR grove1_index = 4
		OR grove1_index = 5
		OR grove1_index = 6
		OR grove1_index = 7
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
			STOP_CHAR_FACIAL_TALK scplayer
		ELSE
			
			IF NOT IS_CHAR_DEAD sweet_grove1
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 FALSE
				STOP_CHAR_FACIAL_TALK sweet_grove1
			ENDIF

		ENDIF

	ENDIF

	IF grove1_chat_switch = GROVE1_CHAT2

		IF grove1_index = 0
		OR grove1_index = 2
		OR grove1_index = 3
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
			STOP_CHAR_FACIAL_TALK scplayer
		ELSE
			
			IF NOT IS_CHAR_DEAD sweet_grove1
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 FALSE
				STOP_CHAR_FACIAL_TALK sweet_grove1
			ENDIF

		ENDIF

	ENDIF

	IF grove1_chat_switch = GROVE1_CHAT3

		IF grove1_index = 1
		OR grove1_index = 3
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
			STOP_CHAR_FACIAL_TALK scplayer
		ELSE
			
			IF NOT IS_CHAR_DEAD sweet_grove1
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 FALSE
				STOP_CHAR_FACIAL_TALK sweet_grove1
			ENDIF

		ENDIF

	ENDIF


	IF grove1_chat_switch = GROVE1_CHAT4

		IF grove1_index = 1
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
			STOP_CHAR_FACIAL_TALK scplayer
		ELSE
			
			IF NOT IS_CHAR_DEAD sweet_grove1
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet_grove1 FALSE
				STOP_CHAR_FACIAL_TALK sweet_grove1
			ENDIF

		ENDIF

	ENDIF

RETURN

// does the guards death checks for the cutscenes
guard_death_check_grove1:

IF bdup_guard1_dead_grove1 = 0
	
		IF IS_CHAR_DEAD bdup_guard1_grove1
			REMOVE_BLIP bdup_guard1_blip_grove1
			++ counter_dbup_guards_dead
			bdup_guard1_dead_grove1 = 1
		ELSE

			IF flag_kill_player_grove1 = 0 

				IF IS_PLAYER_TARGETTING_CHAR player1 bdup_guard1_grove1
				OR HAS_CHAR_SPOTTED_CHAR bdup_guard1_grove1 scplayer 
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON bdup_guard1_grove1 WEAPONTYPE_ANYWEAPON
					flag_kill_player_grove1 = 1
				ENDIF	 

			ENDIF

		ENDIF
		
	ENDIF
	
	IF bdup_guard2_dead_grove1 = 0
	
		IF IS_CHAR_DEAD bdup_guard2_grove1
			REMOVE_BLIP bdup_guard2_blip_grove1
			++ counter_dbup_guards_dead
			bdup_guard2_dead_grove1 = 1
		ELSE

			IF flag_kill_player_grove1 = 0 

				IF IS_PLAYER_TARGETTING_CHAR player1 bdup_guard2_grove1
				OR HAS_CHAR_SPOTTED_CHAR bdup_guard2_grove1 scplayer
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON bdup_guard2_grove1 WEAPONTYPE_ANYWEAPON
					flag_kill_player_grove1 = 1
				ENDIF	 

			ENDIF

		ENDIF
		
	ENDIF
	
	IF bdup_guard3_dead_grove1 = 0
	
		IF IS_CHAR_DEAD bdup_guard3_grove1
			REMOVE_BLIP bdup_guard3_blip_grove1
			++ counter_dbup_guards_dead
			bdup_guard3_dead_grove1 = 1
		ELSE

			IF flag_kill_player_grove1 = 0 

				IF IS_PLAYER_TARGETTING_CHAR player1 bdup_guard3_grove1
				OR HAS_CHAR_SPOTTED_CHAR bdup_guard3_grove1 scplayer
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON bdup_guard3_grove1 WEAPONTYPE_ANYWEAPON
					flag_kill_player_grove1 = 1
				ENDIF	 

			ENDIF

		ENDIF
		
	ENDIF
	
	IF bdup_guard4_dead_grove1 = 0
	
		IF IS_CHAR_DEAD bdup_guard4_grove1
			REMOVE_BLIP bdup_guard4_blip_grove1
			++ counter_dbup_guards_dead
			bdup_guard4_dead_grove1 = 1
		ELSE
			
			IF flag_kill_player_grove1 = 0 

				IF IS_PLAYER_TARGETTING_CHAR player1 bdup_guard4_grove1
				OR HAS_CHAR_SPOTTED_CHAR bdup_guard4_grove1 scplayer
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON bdup_guard4_grove1 WEAPONTYPE_ANYWEAPON
					flag_kill_player_grove1 = 1
				ENDIF	 

			ENDIF

		ENDIF
		
	ENDIF
	
	IF bdup_guard5_dead_grove1 = 0
	
		IF IS_CHAR_DEAD bdup_guard5_grove1
			REMOVE_BLIP bdup_guard5_blip_grove1
			++ counter_dbup_guards_dead
			bdup_guard5_dead_grove1 = 1
		ELSE

			IF flag_kill_player_grove1 = 0 

				IF IS_PLAYER_TARGETTING_CHAR player1 bdup_guard5_grove1
				OR HAS_CHAR_SPOTTED_CHAR bdup_guard5_grove1 scplayer
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON bdup_guard5_grove1 WEAPONTYPE_ANYWEAPON
					flag_kill_player_grove1 = 1
				ENDIF	 

			ENDIF

		ENDIF
		
	ENDIF
	
	IF bdup_guard6_dead_grove1 = 0
	
		IF IS_CHAR_DEAD bdup_guard6_grove1
			REMOVE_BLIP bdup_guard6_blip_grove1
			++ counter_dbup_guards_dead
			bdup_guard6_dead_grove1 = 1
		ELSE

			IF flag_kill_player_grove1 = 0 

				IF IS_PLAYER_TARGETTING_CHAR player1 bdup_guard6_grove1
				OR HAS_CHAR_SPOTTED_CHAR bdup_guard6_grove1 scplayer
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON bdup_guard6_grove1 WEAPONTYPE_ANYWEAPON
					flag_kill_player_grove1 = 1
				ENDIF	 

			ENDIF

		ENDIF
		
	ENDIF
	
	IF bdup_guard7_dead_grove1 = 0
	
		IF IS_CHAR_DEAD bdup_guard7_grove1
			REMOVE_BLIP bdup_guard7_blip_grove1
			++ counter_dbup_guards_dead
			bdup_guard7_dead_grove1 = 1
		ELSE

			IF flag_kill_player_grove1 = 0 

				IF IS_PLAYER_TARGETTING_CHAR player1 bdup_guard7_grove1
				OR HAS_CHAR_SPOTTED_CHAR bdup_guard7_grove1 scplayer
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON bdup_guard7_grove1 WEAPONTYPE_ANYWEAPON
					flag_kill_player_grove1 = 1
				ENDIF	 

			ENDIF

		ENDIF
		
	ENDIF

	IF bdup_guard8_dead_grove1 = 0
	
		IF IS_CHAR_DEAD bdup_guard8_grove1
			REMOVE_BLIP bdup_guard8_blip_grove1
			++ counter_dbup_guards_dead
			bdup_guard8_dead_grove1 = 1
		ELSE

			IF flag_kill_player_grove1 = 0 

				IF IS_PLAYER_TARGETTING_CHAR player1 bdup_guard8_grove1
				OR HAS_CHAR_SPOTTED_CHAR bdup_guard8_grove1 scplayer
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON bdup_guard8_grove1 WEAPONTYPE_ANYWEAPON
					flag_kill_player_grove1 = 1
				ENDIF	 

			ENDIF

		ENDIF
		
	ENDIF

RETURN

}


