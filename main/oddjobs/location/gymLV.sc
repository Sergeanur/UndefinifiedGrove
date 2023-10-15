MISSION_START

// *****************************************************************************************
// ************************************* Gym L.A *******************************************
// *****************************************************************************************
// ************************************ Paul Davis *****************************************
// *****************************************************************************************

// Mission start stuff
							
SCRIPT_NAME gymlv							 

GOSUB mission_start_gymlv

SET_FADING_COLOUR 0 0 0

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_gymlv_failed
ENDIF										

GOSUB mission_cleanup_gymlv

MISSION_END

{

LVAR_INT LV_white_boxer
LVAR_INT LV_black_boxer
LVAR_INT LV_boxer_A
LVAR_INT LV_boxer_B
LVAR_INT LV_boxer_C
LVAR_INT LV_DJ
LVAR_INT gym_hlth
LVAR_INT gym_decision_none
LVAR_INT seq_fight_a seq_fight_b
LVAR_INT gymlv_teach
LVAR_INT gymlv_inside[3]
LVAR_INT lv_seq_white_boxer lv_seq_black_boxer lv_seq_dj_boxer
LVAR_INT gym_decision_tough

LVAR_INT seq_punchbag seq_punchbag1 seq_punchbag2  

LVAR_INT gymlv_display

LVAR_INT gymlv_help_txt	gymlv_cower

LVAR_FLOAT gym_lv_muscle

VAR_INT gym_lv_defeated	

LVAR_TEXT_LABEL lv_print
LVAR_INT lv_audio
LVAR_INT lv_playing

LVAR_INT lv_cheer[3]


// ****************************************Mission Start************************************

mission_start_gymlv:

IF NOT IS_CHAR_DEAD scplayer

	LISTEN_TO_PLAYER_GROUP_COMMANDS scplayer FALSE

ENDIF

gym_is_running = 1

flag_player_on_mission = 1

INCREMENT_INT_STAT VISITS_TO_GYM 1

REGISTER_MISSION_GIVEN

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
REQUEST_ANIMATION FIGHT_D
REQUEST_ANIMATION GYMNASIUM
REQUEST_ANIMATION INT_SHOP

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_ANIMATION_LOADED GYMNASIUM
OR NOT HAS_MODEL_LOADED vbmybox
OR NOT HAS_MODEL_LOADED vwmybox

	WAIT 0

ENDWHILE

WHILE NOT HAS_MODEL_LOADED BMYDJ
OR NOT HAS_ANIMATION_LOADED RIOT
OR NOT HAS_ANIMATION_LOADED FIGHT_D
OR NOT HAS_ANIMATION_LOADED INT_SHOP

	WAIT 0

ENDWHILE

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY gym_decision_none

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH gym_decision_tough

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1

// ************************************** Declare Variables Values ***************************				   

OPEN_SEQUENCE_TASK lv_cheer[0]

	TASK_PLAY_ANIM -1 RIOT_shout RIOT 4.0 FALSE FALSE FALSE FALSE -1

	TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 

	TASK_PAUSE -1 1000 

	SET_SEQUENCE_TO_REPEAT lv_cheer[0] 1
	
CLOSE_SEQUENCE_TASK lv_cheer[0]

OPEN_SEQUENCE_TASK lv_cheer[1]
	
	TASK_PAUSE -1 500 
			
	TASK_PLAY_ANIM -1 RIOT_shout RIOT 4.0 FALSE FALSE FALSE FALSE -1

	TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 

	SET_SEQUENCE_TO_REPEAT lv_cheer[1] 1
	
CLOSE_SEQUENCE_TASK lv_cheer[1]

OPEN_SEQUENCE_TASK lv_cheer[2]
	
	TASK_PAUSE -1 750 
			
	TASK_PLAY_ANIM -1 RIOT_shout RIOT 4.0 FALSE FALSE FALSE FALSE -1

	TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 

	SET_SEQUENCE_TO_REPEAT lv_cheer[2] 1
	
CLOSE_SEQUENCE_TASK lv_cheer[2]

// DJ 
OPEN_SEQUENCE_TASK seq_punchbag

	TASK_GO_TO_COORD_ANY_MEANS -1 773.8429 -61.5409 999.7184 PEDMOVE_WALK -1

	TASK_ACHIEVE_HEADING -1 93.0279 
			
	TASK_PLAY_ANIM -1 gymshadowbox GYMNASIUM 4.0 TRUE FALSE FALSE FALSE -1
	
CLOSE_SEQUENCE_TASK seq_punchbag

// Black Boxer
OPEN_SEQUENCE_TASK seq_punchbag1

	TASK_GO_TO_COORD_ANY_MEANS -1 771.2518 -69.3033 999.6562 PEDMOVE_WALK -1

	TASK_ACHIEVE_HEADING -1 85.5162     
	
CLOSE_SEQUENCE_TASK seq_punchbag1

// White Boxer
OPEN_SEQUENCE_TASK seq_punchbag2

	TASK_GO_TO_COORD_ANY_MEANS -1 762.3508 -78.2144 999.6562 PEDMOVE_WALK -1
 
	TASK_ACHIEVE_HEADING -1 359.2498

    TASK_PLAY_ANIM -1 shop_pay INT_SHOP 4.0 TRUE FALSE FALSE FALSE -1
	
CLOSE_SEQUENCE_TASK seq_punchbag2

// ****************************************************************************************
// *							     White Boxer						  				  *
// ****************************************************************************************

OPEN_SEQUENCE_TASK lv_seq_white_boxer
							  
	TASK_GO_TO_COORD_ANY_MEANS -1 762.3508 -78.2144 999.6562 PEDMOVE_WALK -1
	TASK_ACHIEVE_HEADING -1 359.2498
	TASK_PLAY_ANIM -1 shop_pay INT_SHOP 4.0 TRUE FALSE FALSE FALSE -1 
	SET_SEQUENCE_TO_REPEAT lv_seq_white_boxer 1

CLOSE_SEQUENCE_TASK lv_seq_white_boxer

CREATE_CHAR PEDTYPE_CIVMALE vwmybox 762.3508 -78.2144 999.6562 LV_white_boxer
SET_CHAR_HEADING LV_white_boxer 359.2498    
SET_CHAR_DECISION_MAKER LV_white_boxer gym_decision_tough
PERFORM_SEQUENCE_TASK LV_white_boxer lv_seq_white_boxer
GIVE_MELEE_ATTACK_TO_CHAR LV_white_boxer MCOMBO_UNARMED_4 6

// ****************************************************************************************
// *							        Others   						  				  *
// ****************************************************************************************

// Boxer A
CREATE_CHAR PEDTYPE_CIVMALE vwmybox 766.2026 -69.2071 1000.5692 LV_boxer_A
SET_CHAR_HEADING LV_boxer_A 330.7023   
SET_CHAR_DECISION_MAKER LV_boxer_A gym_decision_none

// Boxer B
CREATE_CHAR PEDTYPE_CIVMALE vbmybox 767.1087 -67.9132 1000.5692 LV_boxer_B
SET_CHAR_HEADING LV_boxer_B 129.3961   
SET_CHAR_DECISION_MAKER LV_boxer_B gym_decision_none

// ****************************************************************************************

OPEN_SEQUENCE_TASK seq_fight_a
 
	TASK_PAUSE -1 1000

	TASK_PLAY_ANIM -1 gymshadowbox GYMNASIUM 4.0 TRUE FALSE FALSE FALSE -1
	
CLOSE_SEQUENCE_TASK seq_fight_a

OPEN_SEQUENCE_TASK seq_fight_b

	TASK_PLAY_ANIM -1 gymshadowbox GYMNASIUM 4.0 TRUE FALSE FALSE FALSE -1
	
CLOSE_SEQUENCE_TASK seq_fight_b

// ****************************************************************************************

PERFORM_SEQUENCE_TASK LV_boxer_A seq_fight_a

PERFORM_SEQUENCE_TASK LV_boxer_B seq_fight_b

// ************************************** Main Mission Loop *******************************

CLEAR_MISSION_AUDIO 4

LOAD_MISSION_AUDIO 4 SOUND_BANK_GYM

WHILE NOT HAS_MISSION_AUDIO_LOADED 4

	WAIT 0

ENDWHILE

lv_playing = 2

IF gym_day > gym_final_day
OR gym_month > gym_final_month

	gym_day_fitness = 0.0

ENDIF

gymlv_loop:

WAIT 0

GOSUB lv_play_sample

// ****************************************************************************************

IF gymlv_cower = 0

	IF IS_CHAR_DEAD LV_boxer_A

		IF NOT IS_CHAR_DEAD LV_boxer_B

			CLEAR_CHAR_TASKS_IMMEDIATELY LV_boxer_B 

			TASK_DUCK LV_boxer_B -2		

			gymlv_cower = 1

		ENDIF

	ENDIF

	IF IS_CHAR_DEAD LV_boxer_B

		IF NOT IS_CHAR_DEAD LV_boxer_A

			CLEAR_CHAR_TASKS_IMMEDIATELY LV_boxer_A 

			TASK_DUCK LV_boxer_A -2		

			gymlv_cower = 1

		ENDIF

	ENDIF

ENDIF

// **********************************************************************************************************
// *																										*
// *                                             Clean Up      												*
// *																										*
// **********************************************************************************************************

GET_CHAR_AREA_VISIBLE scplayer main_visible_area

IF main_visible_area = 0

	GOTO mission_gymlv_failed	

ENDIF

// **********************************************************************************************************
// *																										*
// *                                          Reset Locates													*	
// *																										*
// **********************************************************************************************************

// White Boxer
IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 763.3589 -76.7606 999.6562 2.5 2.5 4.0 FALSE

	gymlv_inside[0] = 0	

ENDIF

// **********************************************************************************************************
// *																										*
// *                                           White Boxer  												*
// *																										*
// **********************************************************************************************************

IF NOT IS_CHAR_DEAD LV_white_boxer

	IF gymlv_inside[0] = 0
	AND LOCATE_CHAR_ANY_MEANS_3D LV_white_boxer 762.3508 -78.2144 999.6562 1.0 1.0 1.2 FALSE

		IF LOCATE_STOPPED_CHAR_ANY_MEANS_3D scplayer 763.3589 -76.7606 999.6562 0.6 0.6 1.0 TRUE

		ENDIF

		IF LOCATE_STOPPED_CHAR_ANY_MEANS_3D scplayer 763.3589 -76.7606 999.6562 1.2 1.2 1.2 FALSE
		AND NOT IS_CHAR_DEAD LV_white_boxer

			IF NOT IS_CHAR_DEAD LV_white_boxer
			AND NOT IS_CHAR_DEAD scplayer
				
				SET_PLAYER_CONTROL player1 OFF

				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

				CLEAR_CHAR_TASKS LV_white_boxer
				
				TASK_TURN_CHAR_TO_FACE_CHAR LV_white_boxer scplayer    
				
				TASK_TURN_CHAR_TO_FACE_CHAR scplayer LV_white_boxer 

			ENDIF

			IF player_been_taught_move[2] = 0 

				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_WBOX_1

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1

				PRINT_NOW ( WBOX_1 ) 3000 1 // Hey, you want to learn some new moves?

			ENDIF

			IF player_been_taught_move[2] = 1

				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_WBOX_4

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1

				PRINT_NOW ( WBOX_4 ) 3000 1 // You think you can last a round with me?

			ENDIF

			PRINT_HELP_FOREVER ( GYM1_73 ) //Press ~<~ to answer NO or ~>~ for YES

			WHILE NOT IS_CHAR_DEAD scplayer

				WAIT 0

				// YES
				IF IS_BUTTON_PRESSED PAD1 DPADRIGHT

					GET_FLOAT_STAT BODY_MUSCLE gym_lv_muscle

					IF gym_lv_muscle > 349.0
											
						gymlv_teach = 1

						CLEAR_HELP

						GOTO gymlv_next_step

					ELSE

						gymlv_teach = 0

						gymlv_inside[0] = 1

						CLEAR_HELP

						CLEAR_MISSION_AUDIO 1

					 	LOAD_MISSION_AUDIO 1 SOUND_WBOX_3

						WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
							WAIT 0
						ENDWHILE

						PLAY_MISSION_AUDIO 1

						PRINT_NOW ( WBOX_3 ) 3000 1 // You need a fighter’s physique! Go put on some muscle!

						SET_PLAYER_CONTROL player1 ON
						
						TIMERA = 0

						gymlv_display = 1

						GOTO gymlv_loop

					ENDIF
	
				ENDIF
				
				// NO
				IF IS_BUTTON_PRESSED PAD1 DPADLEFT

					gymlv_teach = 0

					gymlv_inside[0] = 1

					CLEAR_HELP

					CLEAR_MISSION_AUDIO 1

				 	LOAD_MISSION_AUDIO 1 SOUND_WBOX_2

					WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
						WAIT 0
					ENDWHILE

					PLAY_MISSION_AUDIO 1

					PRINT_NOW ( WBOX_2 ) 3000 1 // Your choice, man, but I know you’ll be back!

					SET_PLAYER_CONTROL player1 ON

					GOTO gymlv_loop

				ENDIF

			ENDWHILE

			CLEAR_HELP

			gymlv_next_step:

			CLEAR_HELP

			CLEAR_PRINTS

			SET_PLAYER_CYCLE_WEAPON_BUTTON player1 FALSE

			SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

			IF gymlv_teach = 1

				GOSUB gymLV_fade_out

				DELETE_CHAR LV_boxer_A

				DELETE_CHAR LV_boxer_B

				DELETE_CHAR LV_white_boxer

				// Boxer A
				CREATE_CHAR PEDTYPE_CIVMALE vbmybox 767.9493 -64.6707 999.6562 LV_boxer_A
				SET_CHAR_HEADING LV_boxer_A 166.9465   
				PERFORM_SEQUENCE_TASK LV_boxer_A lv_cheer[0]

				// Boxer B
				CREATE_CHAR PEDTYPE_CIVMALE vwmybox 770.7913 -67.3238 999.6562 LV_boxer_B
				SET_CHAR_HEADING LV_boxer_B 90.0184   
				PERFORM_SEQUENCE_TASK LV_boxer_B lv_cheer[1]

				// Boxer C
				CREATE_CHAR PEDTYPE_CIVMALE vbmybox 770.4339 -69.5049 999.6562 LV_boxer_C
				SET_CHAR_HEADING LV_boxer_C 91.3393   
				PERFORM_SEQUENCE_TASK LV_boxer_C lv_cheer[2]

				IF NOT IS_CHAR_DEAD scplayer

						CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

						CREATE_CHAR PEDTYPE_MISSION1 vwmybox 768.2548 -67.2063 1000.5692 LV_white_boxer	

						SET_CHAR_HEADING LV_white_boxer 145.0000    

						SET_CHAR_HEALTH LV_white_boxer 1000

						SET_CHAR_MAX_HEALTH LV_white_boxer 1000

					    SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 764.8033 -70.2273 1000.5692 
						
						SET_CHAR_HEADING scplayer 310.0000 
													
						SET_CHAR_DECISION_MAKER LV_white_boxer gym_decision_tough

						TASK_KILL_CHAR_ON_FOOT LV_white_boxer scplayer

						GIVE_MELEE_ATTACK_TO_CHAR LV_white_boxer MCOMBO_UNARMED_4 6

						SET_CHAR_SHOOT_RATE	LV_white_boxer 100

						RESTORE_CAMERA_JUMPCUT

						SET_CAMERA_BEHIND_PLAYER 

					GOSUB gymLV_fade_in

				ENDIF

				SET_PLAYER_CONTROL player1 ON

				IF player_been_taught_move[2] = 0 

					PRINT_HELP_FOREVER ( GYM1101 ) // Defeat your opponent to learn a new moves! 
					
				ENDIF

				IF player_been_taught_move[2] = 1 
					PRINT_HELP_FOREVER GYM1_84  
				ENDIF

				GOSUB gymLV_fade_in

				// *******************************************************************************************************************
				// *																												 *
				// *										    Fighting in the ring   												 *
				// *																												 *
				// *******************************************************************************************************************

				GET_CHAR_COORDINATES scplayer x y z

				IF HAS_MISSION_AUDIO_LOADED 4
				AND NOT IS_CHAR_DEAD scplayer

					REPORT_MISSION_AUDIO_EVENT_AT_CHAR scplayer SOUND_GYM_BOXING_BELL

				ENDIF

				WHILE NOT IS_CHAR_DEAD scplayer

					WAIT 0	

					IF player_been_taught_move[2] = 1

						IF gymlv_help_txt = 0
							TIMERA = 0
							CLEAR_HELP
							PRINT_HELP_FOREVER ( GYM1_94 )  
							gymlv_help_txt = 1
						ENDIF

						IF NOT IS_GERMAN_GAME

							IF TIMERA > 6000
							AND gymlv_help_txt = 1
								CLEAR_HELP
								PRINT_HELP_FOREVER ( GYM1_95 )  
								TIMERA = 0
								gymlv_help_txt = 2
							ENDIF

							IF TIMERA > 6000
							AND gymlv_help_txt = 2
								CLEAR_HELP
								PRINT_HELP_FOREVER ( GYM1_96 )  
								TIMERA = 0
								gymlv_help_txt = 3
							ENDIF

						ELSE

							IF TIMERA > 6000
							AND gymlv_help_txt = 1
								CLEAR_HELP
								PRINT_HELP_FOREVER ( GYM1_96 )  
								TIMERA = 0
								gymlv_help_txt = 3
							ENDIF

						ENDIF

						IF TIMERA > 6000
						AND gymlv_help_txt = 3

							CLEAR_HELP

							TIMERA = 0

							gymlv_help_txt = 0

						ENDIF

					ENDIF

					IF NOT IS_CHAR_DEAD scplayer 
						
						GET_CHAR_COORDINATES scplayer x y z

					ENDIF

					IF IS_CHAR_DEAD LV_white_boxer
						
						GOTO lv_fight2_over

					ENDIF

					IF z < 1000.7000

						GOSUB gymlv_fade_out

						DELETE_CHAR LV_white_boxer

						DELETE_CHAR LV_boxer_A

						DELETE_CHAR LV_boxer_B

						DELETE_CHAR LV_boxer_C

						IF NOT IS_CHAR_DEAD scplayer

							CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

						    SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 762.8540 -69.5929 999.6562  
							
							SET_CHAR_HEADING scplayer 103.4243 

						ENDIF

						CREATE_CHAR PEDTYPE_CIVMALE vwmybox 762.3508 -78.2144 999.6562 LV_white_boxer
						SET_CHAR_HEADING LV_white_boxer 359.2498
						SET_CHAR_DECISION_MAKER LV_white_boxer gym_decision_none
						PERFORM_SEQUENCE_TASK LV_white_boxer lv_seq_white_boxer
						GIVE_MELEE_ATTACK_TO_CHAR LV_white_boxer MCOMBO_UNARMED_4 6

						// ****************************************************************************************
						// *							        Others   						  				  *
						// ****************************************************************************************

						// Boxer A
						CREATE_CHAR PEDTYPE_CIVMALE vwmybox 766.2026 -69.2071 1000.5692 LV_boxer_A
						SET_CHAR_HEADING LV_boxer_A 330.7023   
						PERFORM_SEQUENCE_TASK LV_boxer_A seq_fight_a

						// Boxer B
						CREATE_CHAR PEDTYPE_CIVMALE vbmybox 767.1087 -67.9132 1000.5692 LV_boxer_B
						SET_CHAR_HEADING LV_boxer_B 129.3961   
						PERFORM_SEQUENCE_TASK LV_boxer_B seq_fight_b

						CLEAR_PRINTS

						CLEAR_HELP

						GOSUB gymlv_fade_in

						IF player_been_taught_move[2] = 0

							PRINT_NOW ( GYM1_92 ) 5000 1 // ~r~You left the ring and forfeited

						ENDIF

						GOTO gymlv_loop

					ENDIF

				ENDWHILE

				lv_fight2_over:

				CLEAR_HELP

				IF player_been_taught_move[2] = 1

					GOTO gymLV_skip_cutscene

				ENDIF

				GOSUB gymLV_fade_out
				
		 		GOSUB gymLV_set_camera

				CLEAR_AREA 768.2548 -67.2063 1000.5692 10.0 TRUE

				DELETE_CHAR LV_white_boxer

				DELETE_CHAR LV_boxer_A

				DELETE_CHAR LV_boxer_B

				DELETE_CHAR LV_boxer_C

				CREATE_CHAR PEDTYPE_CIVMALE vwmybox 768.2548 -67.2063 1000.5692 LV_white_boxer
				SET_CHAR_DECISION_MAKER LV_white_boxer gym_decision_none
				GIVE_MELEE_ATTACK_TO_CHAR LV_white_boxer MCOMBO_UNARMED_4 6

				IF NOT IS_CHAR_DEAD LV_white_boxer
				AND NOT IS_CHAR_DEAD scplayer

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

					CLEAR_CHAR_TASKS LV_white_boxer

				    SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 764.8033 -70.2273 1000.5692 
				
					SET_CHAR_COORDINATES_DONT_WARP_GANG LV_white_boxer 768.2548 -67.2063 1000.5692
					
					SET_CHAR_HEADING scplayer 310.0000 

					SET_CHAR_HEADING LV_white_boxer 145.0000 

				ENDIF
				
				SET_FIXED_CAMERA_POSITION 765.1049 -74.2975 1003.5909 0.0 0.0 0.0 // High bike
				POINT_CAMERA_AT_POINT 765.3495 -73.4547 1003.1116 JUMP_CUT

				GOSUB gymLV_fade_in

				PRINT_NOW ( WBOX_5 ) 4000 1 // Heres the new moves watch and learn

				// ****************************** Audio *********************************************************

				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_WBOX_5 // Watch this, it will come in handy.

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

				SET_FIXED_CAMERA_POSITION 767.7358 -70.2958 1001.4927 0.0 0.0 0.0 // High bike
				POINT_CAMERA_AT_POINT 767.9329 -69.3157 1001.4697 JUMP_CUT

				PRINT_NOW ( GYM1_97 ) 2000 1 // Running Attack

				TIMERA = 0
				WHILE TIMERA < 2000
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					ENDIF
				ENDWHILE 

				// ****************************** Audio *********************************************************

				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_WBOX_6 // Rush your opponent and strike fast!

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

				IF NOT IS_CHAR_DEAD LV_white_boxer

					TASK_PLAY_ANIM LV_white_boxer FIGHTD_M FIGHT_D 4.0 FALSE TRUE TRUE FALSE -1

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

					SET_FIXED_CAMERA_POSITION 765.0073 -67.3258 1001.4648 0.0 0.0 0.0 // High bike
					POINT_CAMERA_AT_POINT 766.0051 -67.2777 1001.4188 JUMP_CUT

					PRINT_NOW ( GYM1_98 ) 2000 1 // Ground Attack

					TIMERA = 0
					WHILE TIMERA < 2000
						WAIT 0
						IF IS_BUTTON_PRESSED PAD1 CROSS
						ENDIF
					ENDWHILE 

					// ****************************** Audio *********************************************************

					CLEAR_MISSION_AUDIO 1

				 	LOAD_MISSION_AUDIO 1 SOUND_WBOX_7 // You gotta press home your advantage!

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

					IF NOT IS_CHAR_DEAD LV_white_boxer

						TASK_PLAY_ANIM LV_white_boxer FIGHTD_G FIGHT_D 4.0 FALSE TRUE TRUE FALSE -1

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

				SET_FIXED_CAMERA_POSITION 770.4639 -65.7272 1002.2308 0.0 0.0 0.0 // High bike
				POINT_CAMERA_AT_POINT 769.6946 -66.3243 1002.0034 JUMP_CUT

				PRINT_NOW ( GYM1_99 ) 2000 1 // Combo Attack

				IF NOT IS_CHAR_DEAD LV_white_boxer
				AND NOT IS_CHAR_DEAD scplayer

					TASK_TURN_CHAR_TO_FACE_CHAR LV_white_boxer scplayer

				ENDIF

				TIMERA = 0
				WHILE TIMERA < 2000
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					ENDIF
				ENDWHILE 

				// ****************************** Audio *********************************************************

				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_WBOX_8 // Just keep piling on the pressure – don’t give your opponent time to fight back!

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


				IF NOT IS_CHAR_DEAD LV_white_boxer
					
					FREEZE_CHAR_POSITION LV_white_boxer TRUE  

					TASK_PLAY_ANIM LV_white_boxer FIGHTD_3 FIGHT_D 4.0 FALSE TRUE TRUE FALSE -1

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
			
				gymLV_skip_cutscene:  

				GOSUB gymLV_fade_out

				IF gym_lv_defeated = 0

					PLAYER_MADE_PROGRESS 1

					gym_lv_defeated = 1

				ENDIF

				DELETE_CHAR LV_white_boxer

				DELETE_CHAR LV_boxer_A

				DELETE_CHAR LV_boxer_B

				IF NOT IS_CHAR_DEAD scplayer

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

				    SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 762.8540 -69.5929 999.6562  
					
					SET_CHAR_HEADING scplayer 103.4243 

				ENDIF

				CREATE_CHAR PEDTYPE_CIVMALE vwmybox 762.3508 -78.2144 999.6562 LV_white_boxer
				SET_CHAR_HEADING LV_white_boxer 359.2498    
				SET_CHAR_DECISION_MAKER LV_white_boxer gym_decision_tough
				PERFORM_SEQUENCE_TASK LV_white_boxer lv_seq_white_boxer
				GIVE_MELEE_ATTACK_TO_CHAR LV_white_boxer MCOMBO_UNARMED_4 6

				// ****************************************************************************************
				// *							        Others   						  				  *
				// ****************************************************************************************

				// Boxer A
				CREATE_CHAR PEDTYPE_CIVMALE vwmybox 766.2026 -69.2071 1000.5692 LV_boxer_A
				SET_CHAR_HEADING LV_boxer_A 330.7023   
				PERFORM_SEQUENCE_TASK LV_boxer_A seq_fight_a
				SET_CHAR_DECISION_MAKER LV_boxer_A gym_decision_none

				// Boxer B
				CREATE_CHAR PEDTYPE_CIVMALE vbmybox 767.1087 -67.9132 1000.5692 LV_boxer_B
				SET_CHAR_HEADING LV_boxer_B 129.3961   
				PERFORM_SEQUENCE_TASK LV_boxer_B seq_fight_b
				SET_CHAR_DECISION_MAKER LV_boxer_B gym_decision_none

				GOSUB gymLV_restore_camera
				
				GOSUB gymLV_fade_in
				
				GIVE_MELEE_ATTACK_TO_CHAR scplayer MCOMBO_UNARMED_4 6

				IF player_been_taught_move[2] = 0 

					PRINT_NOW ( GYM1_93 ) 3000 1 // New moves learned!

				ENDIF

				player_been_taught_move[0] = 0
				player_been_taught_move[1] = 0
				player_been_taught_move[2] = 1

				SET_PLAYER_CYCLE_WEAPON_BUTTON player1 TRUE

			ENDIF

		ENDIF
	ENDIF

ENDIF
/*
IF NOT IS_CHAR_DEAD LV_white_boxer

	GET_CHAR_HEALTH LV_white_boxer gym_hlth

	IF gym_hlth < 100

		SET_CHAR_HEALTH LV_white_boxer 100	

	ENDIF

ENDIF
*/  
GOTO gymlv_loop

///////////////////////////////////////// EXIT EXERCISE	//////////////////////////////////////

// Mission gymlv failed

mission_gymlv_failed:

RETURN

// mission gymlv 1 passed

mission_gymlv_passed:

	//flag_XXXX_mission1_passed = 1
	PRINT_WITH_NUMBER_BIG M_PASS 30000 5000 1
	ADD_SCORE player1 30000
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1
	//REGISTER_MISSION_PASSED XXXX
	//PLAYER_MADE_PROGRESS 1
	//START_NEW_SCRIPT XXXX_missionX_loop

RETURN

// mission cleanup

mission_cleanup_gymlv:

	IF NOT IS_CHAR_DEAD scplayer

		LISTEN_TO_PLAYER_GROUP_COMMANDS scplayer TRUE

	ENDIF

	MARK_MODEL_AS_NO_LONGER_NEEDED vbmybox
	MARK_MODEL_AS_NO_LONGER_NEEDED vwmybox
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYDJ

	REMOVE_ANIMATION GYMNASIUM
	REMOVE_ANIMATION RIOT
	REMOVE_ANIMATION FIGHT_D
	REMOVE_ANIMATION INT_SHOP

	CLEAR_MISSION_AUDIO 4

	gym_is_running = 0

	flag_player_on_mission = 0

MISSION_HAS_FINISHED

RETURN

gymLV_set_camera:

	SWITCH_WIDESCREEN ON
	SET_PLAYER_CONTROL player1 OFF
	SET_EVERYONE_IGNORE_PLAYER player1 TRUE

RETURN

gymLV_restore_camera:

	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	RESTORE_CAMERA_JUMPCUT
	SET_EVERYONE_IGNORE_PLAYER player1 FALSE

 
RETURN

gymLV_fade_out:

	DO_FADE 500 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0

		IF IS_BUTTON_PRESSED PAD1 CROSS
			DO_FADE 1 FADE_OUT
		ENDIF

	ENDWHILE

RETURN

gymLV_fade_in:

	DO_FADE 500 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

lv_load_sample:

	CLEAR_MISSION_AUDIO 1

	LOAD_MISSION_AUDIO 1 lv_audio

	lv_playing = 0

RETURN

lv_play_sample:

	IF HAS_MISSION_AUDIO_LOADED 1
	AND lv_playing = 0

		PLAY_MISSION_AUDIO 1

		PRINT_NOW ( $lv_print ) 10000 1 

		lv_playing = 1

	ENDIF

	IF HAS_MISSION_AUDIO_FINISHED 1
	AND lv_playing = 1

		CLEAR_MISSION_AUDIO 1	

		CLEAR_THIS_PRINT $lv_print

		lv_playing = 2

	ENDIF
	
RETURN

}


// GIVE_MELEE_ATTACK_TO_CHAR scplayer MCOMBO_UNARMED_4 4
