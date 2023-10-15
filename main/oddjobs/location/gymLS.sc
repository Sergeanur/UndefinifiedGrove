MISSION_START

// *****************************************************************************************
// ************************************* Gym L.A *******************************************
// *****************************************************************************************

// Mission start stuff
							
SCRIPT_NAME gymls							 

GOSUB mission_start_gymls

SET_FADING_COLOUR 0 0 0

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_gymls_failed
ENDIF										

GOSUB mission_cleanup_gymls

MISSION_END

{

LVAR_INT boxer1_lsgym
LVAR_INT boxer2_lsgym
LVAR_INT boxer3_lsgym
LVAR_INT spec_lsgym
LVAR_INT sensei_lsgym
LVAR_INT sensei1_lsgym
LVAR_INT seq_punchbag1
LVAR_INT seq_punchbag2
LVAR_INT gym_hlth
LVAR_INT gym_decision_none
LVAR_INT gym_teach
LVAR_INT gym_inside[3]
LVAR_INT ls_has_move
LVAR_INT ls_cheer[3]

LVAR_INT seq_punchbag seq_boxer seq_dj

LVAR_INT gymls_display

LVAR_INT gym_help_txt

LVAR_INT gym_decision_tough

LVAR_INT gym_decision_norm

LVAR_INT gymls_inside_ring

VAR_INT ls_has_the_knuckle_duster

LVAR_FLOAT gym_ls_muscle

VAR_INT gym_ls_defeated	 

LVAR_TEXT_LABEL ls_print

LVAR_INT ls_audio

LVAR_INT ls_playing

LVAR_INT ls_cheering

LVAR_INT ls_sfx

// ****************************************Mission Start************************************

REGISTER_MISSION_GIVEN

mission_start_gymls:

IF NOT IS_CHAR_DEAD scplayer

	LISTEN_TO_PLAYER_GROUP_COMMANDS scplayer FALSE

ENDIF

flag_player_on_mission = 1

ls_has_the_knuckle_duster = 0

INCREMENT_INT_STAT VISITS_TO_GYM 1

gym_is_running = 1

LOAD_MISSION_TEXT GYM

// *****************************************************************************************
// *																					   *
// *                                	  Requests   				   					   *
// *																					   *
// *****************************************************************************************

REQUEST_MODEL vbmybox
REQUEST_MODEL vwmybox
REQUEST_MODEL BMYDJ
REQUEST_ANIMATION RIOT
REQUEST_ANIMATION FIGHT_B
REQUEST_ANIMATION INT_SHOP
REQUEST_ANIMATION GYMNASIUM

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_ANIMATION_LOADED GYMNASIUM
OR NOT HAS_MODEL_LOADED vbmybox
OR NOT HAS_MODEL_LOADED vwmybox

	WAIT 0

ENDWHILE

WHILE NOT HAS_MODEL_LOADED BMYDJ
OR NOT HAS_ANIMATION_LOADED RIOT
OR NOT HAS_ANIMATION_LOADED FIGHT_B
OR NOT HAS_ANIMATION_LOADED INT_SHOP

	WAIT 0

ENDWHILE

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY gym_decision_none

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_NORM gym_decision_norm

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH gym_decision_tough

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_MISSION2 PEDTYPE_PLAYER1

CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE gym_decision_norm EVENT_GUN_AIMED_AT

CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE gym_decision_tough EVENT_GUN_AIMED_AT

CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE gym_decision_norm EVENT_DAMAGE

CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE gym_decision_norm EVENT_SHOT_FIRED

CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE gym_decision_norm EVENT_ACQUAINTANCE_PED_DISLIKE

CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE gym_decision_tough EVENT_ACQUAINTANCE_PED_DISLIKE

ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE gym_decision_norm EVENT_GUN_AIMED_AT TASK_SIMPLE_HANDS_UP 100.0 100.0 100.0 100.0 TRUE TRUE

ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE gym_decision_norm EVENT_DAMAGE TASK_SIMPLE_DUCK 100.0 100.0 100.0 100.0 TRUE TRUE

ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE gym_decision_norm EVENT_SHOT_FIRED TASK_SIMPLE_LOOK_AT_ENTITY_OR_COORD 100.0 100.0 100.0 100.0 TRUE TRUE

ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE gym_decision_tough EVENT_GUN_AIMED_AT TASK_SIMPLE_HANDS_UP 100.0 100.0 100.0 100.0 TRUE TRUE

ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE gym_decision_tough EVENT_SHOT_FIRED TASK_SIMPLE_LOOK_AT_ENTITY_OR_COORD 100.0 100.0 100.0 100.0 TRUE TRUE

ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE gym_decision_norm EVENT_ACQUAINTANCE_PED_DISLIKE TASK_SIMPLE_LOOK_AT_ENTITY_OR_COORD 0.0 100.0 0.0 0.0 TRUE TRUE

ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE gym_decision_tough EVENT_ACQUAINTANCE_PED_DISLIKE TASK_SIMPLE_LOOK_AT_ENTITY_OR_COORD 0.0 100.0 0.0 0.0 TRUE TRUE

// ************************************** Declare Variables Values ***************************				   

OPEN_SEQUENCE_TASK seq_punchbag1

	TASK_GO_TO_COORD_ANY_MEANS -1 762.8681 7.4928 999.7141 PEDMOVE_WALK -1

	TASK_ACHIEVE_HEADING -1 13.3401     
			
	TASK_PLAY_ANIM -1 RIOT_PUNCHES RIOT 4.0 FALSE FALSE FALSE FALSE -1

	SET_SEQUENCE_TO_REPEAT seq_punchbag1 1
	
CLOSE_SEQUENCE_TASK seq_punchbag1

OPEN_SEQUENCE_TASK seq_dj

	TASK_GO_TO_COORD_ANY_MEANS -1 765.2909 16.0319 999.6980 PEDMOVE_WALK -1
 
	TASK_ACHIEVE_HEADING -1 198.8789

	SET_SEQUENCE_TO_REPEAT seq_dj 1
	
CLOSE_SEQUENCE_TASK seq_dj

OPEN_SEQUENCE_TASK seq_boxer

	TASK_GO_TO_COORD_ANY_MEANS -1 767.2571 14.4044 999.6998 PEDMOVE_WALK -1
 
	TASK_ACHIEVE_HEADING -1 263.2938
			
	TASK_PLAY_ANIM -1 gymshadowbox GYMNASIUM 4.0 FALSE FALSE FALSE FALSE -1

	SET_SEQUENCE_TO_REPEAT seq_boxer 1
	
CLOSE_SEQUENCE_TASK seq_boxer

OPEN_SEQUENCE_TASK ls_cheer[0]

	TASK_PLAY_ANIM -1 RIOT_shout RIOT 4.0 FALSE FALSE FALSE FALSE -1

	TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 

	TASK_PAUSE -1 1000 

	SET_SEQUENCE_TO_REPEAT ls_cheer[0] 1
	
CLOSE_SEQUENCE_TASK ls_cheer[0]

OPEN_SEQUENCE_TASK ls_cheer[1]
	
	TASK_PAUSE -1 500 
			
	TASK_PLAY_ANIM -1 RIOT_shout RIOT 4.0 FALSE FALSE FALSE FALSE -1

	TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 

	SET_SEQUENCE_TO_REPEAT ls_cheer[1] 1
	
CLOSE_SEQUENCE_TASK ls_cheer[1]

OPEN_SEQUENCE_TASK ls_cheer[2]
	
	TASK_PAUSE -1 750 
			
	TASK_PLAY_ANIM -1 RIOT_shout RIOT 4.0 FALSE FALSE FALSE FALSE -1

	TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 

	SET_SEQUENCE_TO_REPEAT ls_cheer[2] 1
	
CLOSE_SEQUENCE_TASK ls_cheer[2]

CREATE_CHAR PEDTYPE_MISSION2 vwmybox 761.6263 11.4876 1000.1639 boxer1_lsgym
SET_CHAR_HEADING boxer1_lsgym 128.1297
SET_CHAR_DECISION_MAKER boxer1_lsgym gym_decision_norm 
SET_CHAR_ONLY_DAMAGED_BY_PLAYER boxer1_lsgym TRUE
SHUT_CHAR_UP boxer1_lsgym TRUE

CREATE_CHAR PEDTYPE_MISSION2 vwmybox 760.3430 10.1973 999.7099 boxer2_lsgym 
SET_CHAR_HEADING boxer2_lsgym 310.2206
SET_CHAR_DECISION_MAKER boxer2_lsgym gym_decision_norm 
SET_CHAR_ONLY_DAMAGED_BY_PLAYER boxer2_lsgym TRUE
SHUT_CHAR_UP boxer1_lsgym TRUE

OPEN_SEQUENCE_TASK seq_punchbag

	TASK_GO_TO_COORD_ANY_MEANS -1 761.6263 11.4876 1000.1639 PEDMOVE_WALK -1

	TASK_ACHIEVE_HEADING -1 128.1297 

	IF NOT IS_CHAR_DEAD boxer1_lsgym 

		TASK_KILL_CHAR_ON_FOOT -1 boxer2_lsgym

	ENDIF

	SET_SEQUENCE_TO_REPEAT seq_punchbag 1
	
CLOSE_SEQUENCE_TASK seq_punchbag

OPEN_SEQUENCE_TASK seq_punchbag2
 
	TASK_GO_TO_COORD_ANY_MEANS -1 760.3430 10.1973 999.7099 PEDMOVE_WALK -1

	TASK_ACHIEVE_HEADING -1 310.2206

	IF NOT IS_CHAR_DEAD boxer1_lsgym

		TASK_KILL_CHAR_ON_FOOT -1 boxer1_lsgym

	ENDIF

	SET_SEQUENCE_TO_REPEAT seq_punchbag2 1
	
CLOSE_SEQUENCE_TASK seq_punchbag2

PERFORM_SEQUENCE_TASK boxer1_lsgym seq_punchbag  

PERFORM_SEQUENCE_TASK boxer2_lsgym seq_punchbag2 

// Dj ringside
CREATE_CHAR PEDTYPE_MISSION2 BMYDJ 762.8681 7.4928 999.7141 spec_lsgym
SET_CHAR_HEADING spec_lsgym 13.3401 
PERFORM_SEQUENCE_TASK spec_lsgym seq_punchbag1 
SET_CHAR_DECISION_MAKER spec_lsgym gym_decision_tough 

// Black Boxer						  
CREATE_CHAR PEDTYPE_MISSION2 vbmybox 767.2571 14.4044 999.6998 sensei1_lsgym
SET_CHAR_HEADING sensei1_lsgym 263.2938  
SET_CHAR_DECISION_MAKER sensei1_lsgym gym_decision_tough
PERFORM_SEQUENCE_TASK sensei1_lsgym seq_boxer 
GIVE_MELEE_ATTACK_TO_CHAR sensei1_lsgym MCOMBO_UNARMED_2 6

CLEAR_MISSION_AUDIO 4

LOAD_MISSION_AUDIO 4 SOUND_BANK_GYM

WHILE NOT HAS_MISSION_AUDIO_LOADED 4

	WAIT 0

ENDWHILE

// ************************************** Main Mission Loop **********************************

TIMERA = 0

ls_playing = 2

IF gym_day > gym_final_day
OR gym_month > gym_final_month

	gym_day_fitness = 0.0

ENDIF

gymls_loop:

WAIT 0

GOSUB ls_play_sample

IF ls_has_move = 0  

	IF NOT IS_CHAR_DEAD boxer1_lsgym
		IF IS_CHAR_PLAYING_ANIM boxer1_lsgym gym_shadowbox

			SET_CHAR_ANIM_CURRENT_TIME boxer1_lsgym gymshadowbox 0.0

		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD boxer2_lsgym
		IF IS_CHAR_PLAYING_ANIM boxer2_lsgym gym_shadowbox

			SET_CHAR_ANIM_CURRENT_TIME boxer2_lsgym gymshadowbox 0.5
			
		ENDIF
	ENDIF

	ls_has_move = 1

ENDIF

// **********************************************************************************************************
// *																										*
// *                                         Player in ring fight 							   				*
// *																										*
// **********************************************************************************************************

IF IS_CHAR_DEAD boxer1_lsgym
AND TIMERA > 20000

	IF NOT IS_CHAR_DEAD boxer2_lsgym

		CLEAR_CHAR_TASKS boxer2_lsgym

		TASK_DUCK boxer2_lsgym 20000

		TIMERA = 0

	ENDIF

ENDIF

IF IS_CHAR_DEAD boxer2_lsgym
AND TIMERA > 20000

	IF NOT IS_CHAR_DEAD boxer1_lsgym

		CLEAR_CHAR_TASKS boxer1_lsgym

		TASK_DUCK boxer1_lsgym 20000
		
		TIMERA = 0	

	ENDIF

ENDIF

IF LOCATE_CHAR_ANY_MEANS_3D scplayer 760.8566 11.0630 1000.1639 3.0 3.0 2.0 FALSE

	IF gymls_inside_ring = 0

		IF NOT IS_CHAR_DEAD boxer1_lsgym

			CLEAR_CHAR_TASKS_IMMEDIATELY boxer1_lsgym

			SET_CHAR_DECISION_MAKER boxer1_lsgym gym_decision_tough

			TASK_KILL_CHAR_ON_FOOT boxer1_lsgym scplayer

		ENDIF

		IF NOT IS_CHAR_DEAD boxer2_lsgym

			CLEAR_CHAR_TASKS_IMMEDIATELY boxer2_lsgym

			SET_CHAR_DECISION_MAKER boxer2_lsgym gym_decision_tough

			TASK_KILL_CHAR_ON_FOOT boxer2_lsgym scplayer

		ENDIF
		
		gymls_inside_ring = 1

	ENDIF

ELSE

	IF gymls_inside_ring = 1

		IF NOT IS_CHAR_DEAD boxer1_lsgym
		AND NOT IS_CHAR_DEAD boxer2_lsgym

			CLEAR_CHAR_TASKS_IMMEDIATELY boxer1_lsgym

			SET_CHAR_DECISION_MAKER boxer1_lsgym gym_decision_norm

			PERFORM_SEQUENCE_TASK boxer1_lsgym seq_punchbag 
		
			CLEAR_CHAR_TASKS_IMMEDIATELY boxer2_lsgym

			SET_CHAR_DECISION_MAKER boxer2_lsgym gym_decision_norm

			PERFORM_SEQUENCE_TASK boxer2_lsgym seq_punchbag2

		ENDIF

		gymls_inside_ring = 0

	ENDIF

ENDIF

// **********************************************************************************************************
// *																										*
// *                                              Clean Up      							   				*
// *																										*
// **********************************************************************************************************

GET_CHAR_AREA_VISIBLE scplayer main_visible_area

IF main_visible_area = 0

  	GOTO mission_gymls_failed	

ENDIF

// Black Boxer 
IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 767.1368 12.5110 999.7036 2.5 2.5 4.0 FALSE

	gym_inside[1] = 0	

ENDIF

// **********************************************************************************************************
// *																										*
// *                                           Black Boxer  												*
// *																										*
// **********************************************************************************************************

IF NOT IS_CHAR_DEAD sensei1_lsgym

IF gym_inside[1] = 0
AND LOCATE_CHAR_ANY_MEANS_3D sensei1_lsgym 767.2571 14.4044 999.6998 1.0 1.0 1.2 FALSE	

	IF LOCATE_STOPPED_CHAR_ANY_MEANS_3D scplayer 767.1368 12.5110 999.7036 0.6 0.6 1.0 TRUE
	ENDIF

	IF LOCATE_STOPPED_CHAR_ANY_MEANS_3D scplayer 767.1368 12.5110 999.7036 1.2 1.2 1.2 FALSE
	AND NOT IS_CHAR_DEAD sensei1_lsgym

		IF NOT IS_CHAR_DEAD sensei1_lsgym
		AND NOT IS_CHAR_DEAD scplayer

			SET_PLAYER_CONTROL player1 OFF

			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

			CLEAR_CHAR_TASKS_IMMEDIATELY sensei1_lsgym
			
			TASK_TURN_CHAR_TO_FACE_CHAR sensei1_lsgym scplayer    
			
			TASK_TURN_CHAR_TO_FACE_CHAR scplayer sensei1_lsgym 

		ENDIF

		IF player_been_taught_move[1] = 0 

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_BBOX_1

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			PRINT_NOW ( BBOX_1 ) 3000 1 // Yo, you want to learn some new moves?

		ENDIF

		IF player_been_taught_move[1] = 1

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_BBOX_4

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			PRINT_NOW ( BBOX_4 ) 3000 1 // Hey, you want to go a round with me?

		ENDIF

		PRINT_HELP_FOREVER ( TALK_1 ) //Press ~<~ to answer NO or ~>~ for YES

		WHILE NOT IS_CHAR_DEAD scplayer

			WAIT 0

			IF IS_BUTTON_PRESSED PAD1 DPADRIGHT								

				GET_FLOAT_STAT BODY_MUSCLE gym_ls_muscle

				IF gym_ls_muscle > 349.0
										
					gym_teach = 1
					CLEAR_HELP
					GOTO gym_next_step_1

				ELSE

					gym_teach = 0

					gym_inside[1] = 1

					CLEAR_HELP

					CLEAR_MISSION_AUDIO 1

				 	LOAD_MISSION_AUDIO 1 SOUND_BBOX_3

					WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
						WAIT 0
					ENDWHILE

					PLAY_MISSION_AUDIO 1

					PRINT_NOW ( BBOX_3 ) 3000 1 // Man, you're an embarrasment! Get yourself some muscles first!

					SET_PLAYER_CONTROL player1 ON
						
					IF NOT IS_CHAR_DEAD sensei1_lsgym

						PERFORM_SEQUENCE_TASK sensei1_lsgym seq_boxer 

					ENDIF

					TIMERA = 0

					gymls_display = 1

					GOTO gymls_loop

				ENDIF

			ENDIF
							
			IF IS_BUTTON_PRESSED PAD1 DPADLEFT

				gym_teach = 0

				gym_inside[1] = 1

				CLEAR_HELP

				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_BBOX_2

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1

				PRINT_NOW ( BBOX_2 ) 3000 1 // Suit yourself, holmes, but the streets are mean, dude!

				SET_PLAYER_CONTROL player1 ON

				IF NOT IS_CHAR_DEAD sensei1_lsgym

					PERFORM_SEQUENCE_TASK sensei1_lsgym seq_boxer 

				ENDIF

				GOTO gymls_loop

			ENDIF

		ENDWHILE

		gym_next_step_1:

		CLEAR_HELP

		CLEAR_PRINTS

		SET_PLAYER_CYCLE_WEAPON_BUTTON player1 FALSE

		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

		GOSUB gym_fade_out

		DELETE_CHAR spec_lsgym
		DELETE_CHAR boxer1_lsgym
		DELETE_CHAR boxer2_lsgym

		CREATE_CHAR PEDTYPE_MISSION2 vwmybox 761.6934 4.2203 999.7099 boxer1_lsgym
		SET_CHAR_HEADING boxer1_lsgym 171.4614 
		PERFORM_SEQUENCE_TASK boxer1_lsgym ls_cheer[0]

		CREATE_CHAR PEDTYPE_MISSION2 vwmybox 764.7962 0.7163 999.7150 boxer2_lsgym 
		SET_CHAR_HEADING boxer2_lsgym 93.8066
		PERFORM_SEQUENCE_TASK boxer2_lsgym ls_cheer[1]

		CREATE_CHAR PEDTYPE_MISSION2 BMYDJ 759.5085 4.2343 999.7051 spec_lsgym
		SET_CHAR_HEADING spec_lsgym 189.7654 
		PERFORM_SEQUENCE_TASK spec_lsgym ls_cheer[2]

		IF NOT IS_CHAR_DEAD sensei1_lsgym

			SET_CHAR_COORDINATES_DONT_WARP_GANG sensei1_lsgym 762.6160 2.1235 1000.5942  

			SET_CHAR_HEADING sensei1_lsgym 135.5749

			SET_CHAR_HEALTH	sensei1_lsgym 1000

			SET_CHAR_MAX_HEALTH sensei1_lsgym 1000	 

		ENDIF

		IF NOT IS_CHAR_DEAD scplayer

			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 759.0147 -1.7235 1000.5942  

			SET_CHAR_HEADING scplayer 312.9747

		ENDIF

		IF NOT IS_CHAR_DEAD sensei1_lsgym

			SET_CHAR_DECISION_MAKER sensei1_lsgym gym_decision_tough

			TASK_KILL_CHAR_ON_FOOT sensei1_lsgym scplayer

			// Give the boxer the moves
			GIVE_MELEE_ATTACK_TO_CHAR sensei1_lsgym MCOMBO_UNARMED_2 6

			SET_CHAR_SHOOT_RATE	sensei1_lsgym 100

		ENDIF

		SET_CAMERA_BEHIND_PLAYER 

		SET_PLAYER_CONTROL player1 ON
		
		IF player_been_taught_move[1] = 0 

			PRINT_HELP_FOREVER GYM1101 // Defeat your opponent to learn a new moves! 
			
		ENDIF

		IF player_been_taught_move[1] = 1 
			PRINT_HELP_FOREVER GYM1_84  
		ENDIF

		GOSUB gym_fade_in

		// *******************************************************************************************************************
		// *																												 *
		// *										    Fighting in the ring   												 *
		// *																												 *
		// *******************************************************************************************************************

		TIMERA = 0

		TIMERB = 0

		IF HAS_MISSION_AUDIO_LOADED 4
		AND NOT IS_CHAR_DEAD scplayer

			REPORT_MISSION_AUDIO_EVENT_AT_CHAR scplayer SOUND_GYM_BOXING_BELL

		ENDIF

		WHILE NOT IS_CHAR_DEAD scplayer

			WAIT 0

			IF NOT IS_CHAR_DEAD boxer2_lsgym

				IF NOT LOCATE_CHAR_ANY_MEANS_3D boxer2_lsgym 761.0182 0.6609 1000.5942 6.0 6.0 6.0 FALSE

					GOTO ls_fight2_over	

				ENDIF

			ENDIF

	  	 	IF NOT IS_CHAR_DEAD boxer1_lsgym
				
				IF TIMERB > 3000
		 		AND ls_cheering = 0

				//	SET_CHAR_SAY_CONTEXT boxer1_lsgym CONTEXT_GLOBAL_BOXING_CHEER ls_sfx 

					ls_cheering = 1

				ENDIF

				TIMERB = 0

			ENDIF

			IF NOT IS_CHAR_DEAD boxer2_lsgym

				IF TIMERB > 3000
				AND ls_cheering = 1

				//	SET_CHAR_SAY_CONTEXT boxer2_lsgym CONTEXT_GLOBAL_BOXING_CHEER ls_sfx 

					ls_cheering = 0

				ENDIF

				TIMERB = 0

			ENDIF  

			IF player_been_taught_move[1] = 1

				IF gym_help_txt = 0
					TIMERA = 0
                    CLEAR_HELP
					PRINT_HELP_FOREVER ( GYM1_94 )  
					gym_help_txt = 1
				ENDIF

				IF NOT IS_GERMAN_GAME

					IF TIMERA > 6000
					AND gym_help_txt = 1
						CLEAR_HELP
						PRINT_HELP_FOREVER ( GYM1_95 )  
						TIMERA = 0
						gym_help_txt = 2
					ENDIF

					IF TIMERA > 6000
					AND gym_help_txt = 2
						CLEAR_HELP
						PRINT_HELP_FOREVER ( GYM1_96 )  
						TIMERA = 0
						gym_help_txt = 3
					ENDIF

				ELSE

					IF TIMERA > 6000
					AND gym_help_txt = 1
						CLEAR_HELP
						PRINT_HELP_FOREVER ( GYM1_96 )  
						TIMERA = 0
						gym_help_txt = 3
					ENDIF

				ENDIF

				IF TIMERA > 6000
				AND gym_help_txt = 3

					CLEAR_HELP

					TIMERA = 0

					gym_help_txt = 0

				ENDIF

			ENDIF

			IF IS_CHAR_DEAD sensei1_lsgym
				
				GOTO ls_fight2_over

			ENDIF

		ENDWHILE

		ls_fight2_over:

		CLEAR_HELP

		IF player_been_taught_move[1] = 1

			GOTO gym_skip_cutscene

		ENDIF

		GOSUB gym_fade_out
		
		DELETE_CHAR sensei1_lsgym

		CLEAR_AREA 760.7445 0.6538 1000.5942 10.0 TRUE

 		GOSUB gym_set_camera

		CREATE_CHAR PEDTYPE_MISSION2 vbmybox 762.6160 2.1235 1000.5942 sensei1_lsgym

		SET_CHAR_HEADING sensei1_lsgym 135.5749  

		SET_CHAR_DECISION_MAKER sensei1_lsgym gym_decision_none

		CLEAR_CHAR_TASKS_IMMEDIATELY sensei1_lsgym

		SET_CHAR_DECISION_MAKER sensei1_lsgym gym_decision_none

		GIVE_MELEE_ATTACK_TO_CHAR sensei1_lsgym MCOMBO_UNARMED_2 6

		IF NOT IS_CHAR_DEAD scplayer

			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 759.0147 -1.7235 1000.5942  

			SET_CHAR_HEADING scplayer 312.9747

		ENDIF

		DELETE_CHAR boxer1_lsgym

		DELETE_CHAR boxer2_lsgym

		DELETE_CHAR spec_lsgym

		SET_FIXED_CAMERA_POSITION 764.0964 -4.8216 1003.0615 0.0 0.0 0.0 // High bike
		POINT_CAMERA_AT_POINT 763.5206 -4.0516 1002.7866 JUMP_CUT

		GOSUB gym_fade_in

		PRINT_NOW ( BBOX_5 ) 4000 1 // Heres the new moves watch and learn

		// ****************************** Audio *********************************************************

		CLEAR_MISSION_AUDIO 1

	 	LOAD_MISSION_AUDIO 1 SOUND_BBOX_5 // Pay attention and you might learn something.

		WHILE NOT HAS_MISSION_AUDIO_LOADED 1
			WAIT 0
			IF IS_BUTTON_PRESSED PAD1 CROSS
			ENDIF
		ENDWHILE

		PLAY_MISSION_AUDIO 1

		// **********************************************************************************************

		WAIT 1000

			TIMERA = 0
			WHILE TIMERA < 2000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				ENDIF
			ENDWHILE 

			SET_FIXED_CAMERA_POSITION 762.5212 -0.4172 1001.8427 0.0 0.0 0.0 // High bike
			POINT_CAMERA_AT_POINT 762.5431 0.5793 1001.7621 JUMP_CUT

			PRINT_NOW ( GYM1_97 ) 2000 1 // Running Attack

			TIMERA = 0
			WHILE TIMERA < 2000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				ENDIF
			ENDWHILE 

			// ****************************** Audio *********************************************************

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_BBOX_6 // Charge in and battter your opponent!

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				ENDIF
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			// **********************************************************************************************

			PRINT_NOW ( GYM1_94 ) 4000 1  

			TIMERA = 0
			WHILE TIMERA < 4000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				ENDIF
			ENDWHILE 

			IF NOT IS_CHAR_DEAD sensei1_lsgym

				TASK_PLAY_ANIM sensei1_lsgym FIGHTB_M FIGHT_B 4.0 FALSE TRUE TRUE FALSE -1

			ENDIF

			IF NOT IS_CHAR_DEAD scplayer

				TASK_PLAY_ANIM scplayer fucku PED 4.0 FALSE TRUE TRUE FALSE -1

			ENDIF

			TIMERA = 0
			WHILE TIMERA < 3000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				ENDIF
			ENDWHILE  

			IF NOT IS_GERMAN_GAME
	  
				SET_FIXED_CAMERA_POSITION 759.6699 1.9955 1001.0680 0.0 0.0 0.0 // High bike
				POINT_CAMERA_AT_POINT 760.6637 2.0051 1001.1784 JUMP_CUT

				PRINT_NOW ( GYM1_98 ) 2000 1 // Ground Attack

				TIMERA = 0
				WHILE TIMERA < 2000
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					ENDIF
				ENDWHILE 

				// ****************************** Audio *********************************************************

				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_BBOX_7 // Make sure your opponent is down AND out!

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					ENDIF
				ENDWHILE

				PLAY_MISSION_AUDIO 1

					// **********************************************************************************************

				PRINT_NOW ( GYM1_95 ) 4000 1  

				TIMERA = 0
				WHILE TIMERA < 4000
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					ENDIF
				ENDWHILE 

				IF NOT IS_CHAR_DEAD sensei1_lsgym

					TASK_PLAY_ANIM sensei1_lsgym FIGHTB_G FIGHT_B 4.0 FALSE TRUE TRUE FALSE -1

				ENDIF

				IF NOT IS_CHAR_DEAD scplayer

					TASK_PLAY_ANIM scplayer fucku PED 4.0 FALSE TRUE TRUE FALSE -1

				ENDIF

				TIMERA = 0
				WHILE TIMERA < 3000
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					ENDIF
				ENDWHILE

			ENDIF
			 
			SET_FIXED_CAMERA_POSITION 764.5368 3.1767 1002.3495 0.0 0.0 0.0 // High bike
			POINT_CAMERA_AT_POINT 763.7495 2.6046 1002.1198 JUMP_CUT

			PRINT_NOW ( GYM1_99 ) 2000 1 // Combo Attack

			TIMERA = 0
			WHILE TIMERA < 2000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				ENDIF
			ENDWHILE 

			// ****************************** Audio *********************************************************

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_BBOX_8 // Never give your opponent time to recover!

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				ENDIF
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			// **********************************************************************************************

			PRINT_NOW ( GYM1_96 ) 4000 1  

			TIMERA = 0
			WHILE TIMERA < 4000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				ENDIF
			ENDWHILE 

			IF NOT IS_CHAR_DEAD sensei1_lsgym
				
				FREEZE_CHAR_POSITION sensei1_lsgym TRUE  

			//	TASK_PLAY_ANIM_WITH_FLAGS sensei1_lsgym FIGHTB_3 FIGHT_B 4.0 FALSE FALSE FALSE FALSE -1 FALSE TRUE

				TASK_PLAY_ANIM sensei1_lsgym FIGHTB_3 FIGHT_B 4.0 FALSE TRUE TRUE FALSE -1

			ENDIF

			IF NOT IS_CHAR_DEAD scplayer

				TASK_PLAY_ANIM scplayer fucku PED 4.0 FALSE FALSE FALSE TRUE -1

			ENDIF

			TIMERA = 0
			WHILE TIMERA < 2000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				ENDIF
			ENDWHILE 

			gym_skip_cutscene:

			DELETE_CHAR spec_lsgym
			DELETE_CHAR boxer1_lsgym
			DELETE_CHAR boxer2_lsgym

			GOSUB gym_fade_out

			CREATE_CHAR PEDTYPE_MISSION2 vwmybox 761.6263 11.4876 1000.1639 boxer1_lsgym
			SET_CHAR_HEADING boxer1_lsgym 128.1297
			SET_CHAR_DECISION_MAKER boxer1_lsgym gym_decision_norm 
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER boxer1_lsgym TRUE
			SHUT_CHAR_UP boxer1_lsgym TRUE

			CREATE_CHAR PEDTYPE_MISSION2 vwmybox 760.3430 10.1973 999.7099 boxer2_lsgym 
			SET_CHAR_HEADING boxer2_lsgym 310.2206
			SET_CHAR_DECISION_MAKER boxer2_lsgym gym_decision_norm 
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER boxer2_lsgym TRUE
			SHUT_CHAR_UP boxer1_lsgym TRUE

			PERFORM_SEQUENCE_TASK boxer1_lsgym seq_punchbag  

			PERFORM_SEQUENCE_TASK boxer2_lsgym seq_punchbag2 

			CREATE_CHAR PEDTYPE_MISSION2 BMYDJ 762.8681 7.4928 999.7141 spec_lsgym
			SET_CHAR_HEADING spec_lsgym 13.3401 
			PERFORM_SEQUENCE_TASK spec_lsgym seq_punchbag1
			SET_CHAR_DECISION_MAKER spec_lsgym gym_decision_tough

			DELETE_CHAR sensei1_lsgym

			// Black Boxer
			CREATE_CHAR PEDTYPE_MISSION2 vbmybox 767.2571 14.4044 999.6998 sensei1_lsgym
			SET_CHAR_HEADING sensei1_lsgym 263.2938  
			SET_CHAR_DECISION_MAKER sensei1_lsgym gym_decision_tough
			PERFORM_SEQUENCE_TASK sensei1_lsgym seq_boxer 

			GIVE_MELEE_ATTACK_TO_CHAR sensei1_lsgym MCOMBO_UNARMED_2 6

			IF NOT IS_CHAR_DEAD scplayer

				SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 765.6662 2.7963 999.7180 
				
				SET_CHAR_HEADING scplayer 277.3348 

			ENDIF

			GOSUB gym_restore_camera

			GOSUB gym_fade_in

			IF gym_ls_defeated = 0

				PLAYER_MADE_PROGRESS 1

				gym_ls_defeated = 1

			ENDIF

			// Give the player the moves
			GIVE_MELEE_ATTACK_TO_CHAR scplayer MCOMBO_UNARMED_2 6

			IF player_been_taught_move[1] = 0 

				PRINT_NOW ( GYM1_93 ) 3000 1 // New moves learned!

			ENDIF

			player_been_taught_move[0] = 0
			player_been_taught_move[1] = 1
			player_been_taught_move[2] = 0

			SET_PLAYER_CYCLE_WEAPON_BUTTON player1 TRUE

		ENDIF
 
	ENDIF

ENDIF
  
GOTO gymls_loop

mission_gymls_failed:

RETURN

// mission gymls 1 passed

mission_gymls_passed:

	PRINT_WITH_NUMBER_BIG M_PASS 30000 5000 1
	ADD_SCORE player1 30000
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1

RETURN

// mission cleanup

mission_cleanup_gymls:

	IF NOT IS_CHAR_DEAD scplayer

		LISTEN_TO_PLAYER_GROUP_COMMANDS scplayer TRUE

	ENDIF

	MARK_MODEL_AS_NO_LONGER_NEEDED vbmybox
	MARK_MODEL_AS_NO_LONGER_NEEDED vwmybox
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYDJ

	REMOVE_ANIMATION GYMNASIUM
	REMOVE_ANIMATION RIOT
	REMOVE_ANIMATION FIGHT_B
	REMOVE_ANIMATION INT_SHOP

	CLEAR_MISSION_AUDIO 4

	gym_is_running = 0

	flag_player_on_mission = 0

	MISSION_HAS_FINISHED

RETURN

gym_set_camera:

	CLEAR_HELP

	CLEAR_PRINTS

	SWITCH_WIDESCREEN ON
	SET_PLAYER_CONTROL player1 OFF
	SET_EVERYONE_IGNORE_PLAYER player1 TRUE

RETURN

gym_restore_camera:

	CLEAR_HELP

	CLEAR_PRINTS

	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	RESTORE_CAMERA_JUMPCUT
	SET_EVERYONE_IGNORE_PLAYER player1 FALSE
 
RETURN

gym_fade_out:

	DO_FADE 500 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0

		IF IS_BUTTON_PRESSED PAD1 CROSS
			DO_FADE 1 FADE_OUT
		ENDIF

	ENDWHILE

RETURN

gym_fade_in:

	DO_FADE 500 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

ls_load_sample:

	CLEAR_MISSION_AUDIO 1

	LOAD_MISSION_AUDIO 1 ls_audio

	ls_playing = 0

RETURN

ls_play_sample:

	IF HAS_MISSION_AUDIO_LOADED 1
	AND ls_playing = 0

		PLAY_MISSION_AUDIO 1

		PRINT_NOW ( $ls_print ) 10000 1 

		ls_playing = 1

	ENDIF

	IF HAS_MISSION_AUDIO_FINISHED 1
	AND ls_playing = 1

		CLEAR_MISSION_AUDIO 1	

		CLEAR_THIS_PRINT $ls_print

		ls_playing = 2

	ENDIF
	
RETURN

}
