MISSION_START

// *****************************************************************************************
// ************************************* Gym L.A *******************************************
// *****************************************************************************************
// ************************************ Paul Davis *****************************************
// *****************************************************************************************

// Mission start stuff OMYKARA WMYKARA
							
SCRIPT_NAME gymsf

DO_FADE 0 FADE_OUT

GOSUB mission_start_gymsf

SET_FADING_COLOUR 0 0 0

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_gymsf_failed
ENDIF										

GOSUB mission_cleanup_gymsf

MISSION_END

{

LVAR_INT SF_white_boxer
LVAR_INT SF_black_boxer
LVAR_INT SF_boxer_A
LVAR_INT SF_boxer_B
LVAR_INT SF_DJ
LVAR_INT gym_hlth
LVAR_INT gym_decision_none
LVAR_INT gym_decision_tough
LVAR_INT seq_fight_a seq_fight_b
LVAR_INT gymsf_teach
LVAR_INT gymsf_inside[3]
LVAR_INT sf_seq_dj_boxer sf_seq_black_boxer sf_seq_white_boxer
LVAR_INT sf_has_move

LVAR_INT seq_punchbag seq_punchbag1 seq_punchbag2

LVAR_INT gymsf_display

LVAR_INT gymsf_help_txt

LVAR_INT gym_decision

LVAR_INT sf_left_ring

LVAR_FLOAT gym_sf_muscle

VAR_INT gym_sf_defeated	player_been_taught_move[3] 

LVAR_TEXT_LABEL sf_print
LVAR_INT sf_audio
LVAR_INT sf_playing

// ****************************************Mission Start************************************

mission_start_gymsf:

IF NOT IS_CHAR_DEAD scplayer

	LISTEN_TO_PLAYER_GROUP_COMMANDS scplayer FALSE

ENDIF

gym_is_running = 1

sf_playing = 2

flag_player_on_mission = 1

INCREMENT_INT_STAT VISITS_TO_GYM 1

REGISTER_MISSION_GIVEN

LOAD_MISSION_TEXT GYM

// *****************************************************************************************
// *																					   *
// *                                	  Requests   				   					   *
// *																					   *
// *****************************************************************************************

REQUEST_MODEL WMYJG
REQUEST_MODEL OMYKARA
REQUEST_MODEL WMYlG
REQUEST_MODEL OMOkung
REQUEST_MODEL WMYKARA

WHILE NOT HAS_MODEL_LOADED WMYJG
OR NOT HAS_MODEL_LOADED OMYKARA
OR NOT HAS_MODEL_LOADED WMYlG
OR NOT HAS_MODEL_LOADED OMOkung
OR NOT HAS_MODEL_LOADED WMYKARA

	WAIT 0

ENDWHILE

REQUEST_ANIMATION PARK	   
REQUEST_ANIMATION FIGHT_C
REQUEST_ANIMATION GYMNASIUM

WHILE NOT HAS_ANIMATION_LOADED PARK
OR NOT HAS_ANIMATION_LOADED FIGHT_C
OR NOT HAS_ANIMATION_LOADED GYMNASIUM

	WAIT 0

ENDWHILE

CLEAR_MISSION_AUDIO 4

LOAD_MISSION_AUDIO 4 SOUND_BANK_GYM

WHILE NOT HAS_MISSION_AUDIO_LOADED 4

	WAIT 0

ENDWHILE

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY gym_decision_none

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_NORM gym_decision

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH gym_decision_tough

// ************************************** Declare Variables Values ***************************				   

// DJ 
OPEN_SEQUENCE_TASK seq_punchbag

	TASK_GO_TO_COORD_ANY_MEANS -1 768.0640 -41.5728 999.6865 PEDMOVE_WALK -1

	TASK_ACHIEVE_HEADING -1 358.0992 

	TASK_LOOK_ABOUT -1 -2
	
CLOSE_SEQUENCE_TASK seq_punchbag

// Black Boxer
OPEN_SEQUENCE_TASK seq_punchbag1

	TASK_GO_TO_COORD_ANY_MEANS -1 770.3961 -23.0853 999.5938 PEDMOVE_WALK -1

	TASK_ACHIEVE_HEADING -1 87.3049     
			
    TASK_PLAY_ANIM -1 Tai_Chi_Loop PARK 4.0 TRUE FALSE FALSE FALSE -1
	
CLOSE_SEQUENCE_TASK seq_punchbag1

// White Boxer
OPEN_SEQUENCE_TASK seq_punchbag2

	TASK_PAUSE -1 1000

	TASK_GO_TO_COORD_ANY_MEANS -1 766.3062 -23.0554 999.5938 PEDMOVE_WALK -1
 
	TASK_ACHIEVE_HEADING -1 272.4196
			
    TASK_PLAY_ANIM -1 Tai_Chi_Loop PARK 4.0 TRUE FALSE FALSE FALSE -1
	
CLOSE_SEQUENCE_TASK seq_punchbag2
							    
OPEN_SEQUENCE_TASK seq_fight_a
 
   	TASK_PAUSE -1 1000
							  
  	TASK_GO_TO_COORD_ANY_MEANS -1 769.8199 -36.4933 999.6865 PEDMOVE_WALK -1

  	TASK_ACHIEVE_HEADING -1 88.5480 

	TASK_PLAY_ANIM -1 FightC_IDLE FIGHT_C 4.0 TRUE FALSE FALSE FALSE -1

 	SET_SEQUENCE_TO_REPEAT seq_fight_a 1
	
CLOSE_SEQUENCE_TASK seq_fight_a

OPEN_SEQUENCE_TASK seq_fight_b
 
 	TASK_GO_TO_COORD_ANY_MEANS -1 767.0570 -37.0465 999.6865 PEDMOVE_WALK -1

  	TASK_ACHIEVE_HEADING -1 270.0000 

	TASK_PLAY_ANIM -1 FightC_IDLE FIGHT_C 4.0 TRUE FALSE FALSE FALSE -1

  	SET_SEQUENCE_TO_REPEAT seq_fight_b 1
	
CLOSE_SEQUENCE_TASK seq_fight_b

// ****************************************************************************************
// *							      Dj Boxer						  				  	  *
// ****************************************************************************************

OPEN_SEQUENCE_TASK sf_seq_dj_boxer
							  
	TASK_GO_TO_COORD_ANY_MEANS -1 768.0640 -41.5728 999.6865 PEDMOVE_WALK -1

	TASK_ACHIEVE_HEADING -1 358.0992

	TASK_LOOK_ABOUT -1 -2

	SET_SEQUENCE_TO_REPEAT sf_seq_dj_boxer 1

CLOSE_SEQUENCE_TASK sf_seq_dj_boxer

// DJ
CREATE_CHAR PEDTYPE_CIVMALE OMOkung 768.0640 -41.5728 999.6865 SF_DJ
SET_CHAR_HEADING SF_DJ 358.0992   
PERFORM_SEQUENCE_TASK SF_DJ sf_seq_dj_boxer
SET_CHAR_DECISION_MAKER SF_DJ gym_decision
GIVE_MELEE_ATTACK_TO_CHAR SF_DJ MCOMBO_UNARMED_3 6

// ****************************************************************************************
// *							     Black Boxer						  				  *
// ****************************************************************************************

OPEN_SEQUENCE_TASK sf_seq_black_boxer
							  
	TASK_GO_TO_COORD_ANY_MEANS -1 770.3961 -23.0853 999.5938 PEDMOVE_WALK -1

	TASK_ACHIEVE_HEADING -1 87.3049     
			
    TASK_PLAY_ANIM -1 Tai_Chi_Loop PARK 4.0 TRUE FALSE FALSE FALSE -1

	SET_SEQUENCE_TO_REPEAT sf_seq_black_boxer 1

CLOSE_SEQUENCE_TASK sf_seq_black_boxer

// Black Boxer
CREATE_CHAR PEDTYPE_CIVMALE WMYJG 770.3961 -23.0853 999.5938 SF_black_boxer
SET_CHAR_HEADING SF_black_boxer 87.3049  
PERFORM_SEQUENCE_TASK SF_black_boxer sf_seq_black_boxer
SET_CHAR_DECISION_MAKER SF_black_boxer gym_decision_tough
GIVE_MELEE_ATTACK_TO_CHAR SF_black_boxer MCOMBO_UNARMED_3 6

// ****************************************************************************************
// *							     White Boxer						  				  *
// ****************************************************************************************

OPEN_SEQUENCE_TASK sf_seq_white_boxer
							  
	TASK_PAUSE -1 1000

	TASK_GO_TO_COORD_ANY_MEANS -1 766.3062 -23.0554 999.5938 PEDMOVE_WALK -1
 
	TASK_ACHIEVE_HEADING -1 272.4196
			
    TASK_PLAY_ANIM -1 Tai_Chi_Loop PARK 4.0 TRUE FALSE FALSE FALSE -1

	SET_SEQUENCE_TO_REPEAT sf_seq_white_boxer 1	

CLOSE_SEQUENCE_TASK sf_seq_white_boxer

// White Boxer
CREATE_CHAR PEDTYPE_CIVMALE WMYlG 766.3062 -23.0554 999.5938 SF_white_boxer
SET_CHAR_HEADING SF_white_boxer 272.4196    
PERFORM_SEQUENCE_TASK SF_white_boxer sf_seq_white_boxer
SET_CHAR_DECISION_MAKER SF_white_boxer gym_decision_tough
GIVE_MELEE_ATTACK_TO_CHAR SF_white_boxer MCOMBO_UNARMED_3 6

// ****************************************************************************************
// *							        Others   						  				  *
// ****************************************************************************************

// Boxer A
CREATE_CHAR PEDTYPE_CIVMALE OMYKARA 769.8199 -36.4933 999.6865 SF_boxer_A
SET_CHAR_HEADING SF_boxer_A 95.1885   
PERFORM_SEQUENCE_TASK SF_boxer_A seq_fight_a
SET_CHAR_DECISION_MAKER SF_boxer_A gym_decision_tough
GIVE_MELEE_ATTACK_TO_CHAR SF_boxer_A MCOMBO_UNARMED_3 6

// Boxer B
CREATE_CHAR PEDTYPE_CIVMALE WMYKARA 766.1017 -37.3952 999.6865 SF_boxer_B
SET_CHAR_HEADING SF_boxer_B 271.1251   
PERFORM_SEQUENCE_TASK SF_boxer_B seq_fight_b
SET_CHAR_DECISION_MAKER SF_boxer_B gym_decision_tough
GIVE_MELEE_ATTACK_TO_CHAR SF_boxer_B MCOMBO_UNARMED_3 6

// ************************************** Main Mission Loop **********************************

GOSUB gymsf_fade_in

IF gym_day > gym_final_day
OR gym_month > gym_final_month

	gym_day_fitness = 0.0

ENDIF

gymsf_loop:

WAIT 0

GOSUB sf_play_sample

IF sf_has_move = 0  

	IF NOT IS_CHAR_DEAD SF_boxer_B
		IF IS_CHAR_PLAYING_ANIM SF_boxer_B FightC_Spar

			SET_CHAR_ANIM_CURRENT_TIME SF_boxer_B FightC_Spar 0.45

			sf_has_move = 1

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

	GOTO mission_gymsf_failed	

ENDIF

// **********************************************************************************************************
// *																										*
// *                                           Reset Locates			  									*	
// *																										*
// **********************************************************************************************************

// Dj Boxer 
IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 769.7938 -42.1122 999.6865 2.5 2.5 4.0 FALSE

	gymsf_inside[2] = 0	

ENDIF

// **********************************************************************************************************
// *																										*
// *                                                DJ				   										*
// *																										*
// **********************************************************************************************************

IF NOT IS_CHAR_DEAD SF_DJ

	IF gymsf_inside[2] = 0
	AND LOCATE_CHAR_ANY_MEANS_3D SF_DJ 768.0640 -41.5728 999.6865 1.0 1.0 1.2 FALSE	

		IF LOCATE_STOPPED_CHAR_ANY_MEANS_3D scplayer 769.7938 -42.1122 999.6865 0.6 0.6 1.0 TRUE
		ENDIF
	
		IF LOCATE_STOPPED_CHAR_ANY_MEANS_3D scplayer 769.7938 -42.1122 999.6865 1.2 1.2 1.2 FALSE
		AND NOT IS_CHAR_DEAD SF_DJ
			
			IF NOT IS_CHAR_DEAD SF_DJ
			AND NOT IS_CHAR_DEAD scplayer

				SET_PLAYER_CONTROL player1 OFF

				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				
				TASK_TURN_CHAR_TO_FACE_CHAR SF_DJ scplayer    
				
				TASK_TURN_CHAR_TO_FACE_CHAR scplayer SF_DJ 

			ENDIF
			
			IF player_been_taught_move[0] = 0 

				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_KUNG_1

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1

				PRINT_NOW ( KUNG_1 ) 3000 1 // So, you wish to become a warrior!

		   ENDIF

			IF player_been_taught_move[0] = 1

				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_KUNG_4

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1

				PRINT_NOW ( KUNG_4 ) 3000 1 // Would you like to spar with your master?

			//	PRINT_NOW ( GYM1_82 ) 7000 1 // Do you want to spar?

			ENDIF

			PRINT_HELP_FOREVER ( GYM1_73 ) //Press ~<~ to answer NO or ~>~ for YES

			WHILE NOT IS_CHAR_DEAD scplayer

				WAIT 0

				// YES
				IF IS_BUTTON_PRESSED PAD1 DPADRIGHT
				
					GET_FLOAT_STAT BODY_MUSCLE gym_sf_muscle

					IF gym_sf_muscle > 349.0

						gymsf_teach = 1

						CLEAR_HELP

						GOTO gymsf_next_step2

					ELSE

						gymsf_teach = 0

						gymsf_inside[2] = 1

						CLEAR_HELP

						CLEAR_MISSION_AUDIO 1

					 	LOAD_MISSION_AUDIO 1 SOUND_KUNG_3

						WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
							WAIT 0
						ENDWHILE

						PLAY_MISSION_AUDIO 1

						PRINT_NOW ( KUNG_3 ) 3000 1 // You are weak, like a wilted weed! Condition your body!

					 //	PRINT_NOW ( GYM1_91 ) 5000 1 // Your too weak to fight me, build some muscles and come back.

						SET_PLAYER_CONTROL player1 ON

						TIMERA = 0
						
						gymsf_display = 1

						GOTO gymsf_loop

					ENDIF
					
				ENDIF

				// NO
				IF IS_BUTTON_PRESSED PAD1 DPADLEFT
					gymsf_teach = 0

					gymsf_inside[2] = 1

					CLEAR_HELP

					CLEAR_MISSION_AUDIO 1

				 	LOAD_MISSION_AUDIO 1 SOUND_KUNG_2

					WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
						WAIT 0
					ENDWHILE

					PLAY_MISSION_AUDIO 1

					PRINT_NOW ( KUNG_2 ) 3000 1 // You are weak, like a wilted weed! Condition your body!

				   //	PRINT_NOW ( GYM1_75 ) 3000 1 // Ok, I can see your in a hurry

					IF NOT IS_CHAR_DEAD SF_DJ

						PERFORM_SEQUENCE_TASK SF_DJ sf_seq_dj_boxer

					ENDIF

					SET_PLAYER_CONTROL player1 ON

					GOTO gymsf_loop

				ENDIF

			ENDWHILE

			CLEAR_HELP

			gymsf_next_step2:

			CLEAR_HELP

			CLEAR_PRINTS

			SET_PLAYER_CYCLE_WEAPON_BUTTON player1 FALSE

			SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

			IF gymsf_teach = 1

				GOSUB gymsf_fade_out

				DELETE_CHAR SF_boxer_A
				DELETE_CHAR SF_boxer_B
 
				IF NOT IS_CHAR_DEAD SF_DJ

					SET_CHAR_COORDINATES_DONT_WARP_GANG SF_DJ 771.4357 -36.9728 999.6865  

					SET_CHAR_HEADING SF_DJ 88.1801

					SET_CHAR_HEALTH	SF_DJ 1000

					SET_CHAR_MAX_HEALTH SF_DJ 1000	 

				ENDIF

				IF NOT IS_CHAR_DEAD scplayer

					SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 764.6453 -37.1052 999.6865  

					SET_CHAR_HEADING scplayer 273.9816

				ENDIF

				IF NOT IS_CHAR_DEAD SF_DJ

					SET_CHAR_DECISION_MAKER SF_DJ gym_decision_tough

					TASK_KILL_CHAR_ON_FOOT SF_DJ scplayer

					// Give the boxer the moves
					GIVE_MELEE_ATTACK_TO_CHAR SF_DJ MCOMBO_UNARMED_3 6

					SET_CHAR_SHOOT_RATE	SF_DJ 100

				ENDIF

				SET_CAMERA_BEHIND_PLAYER 

				SET_PLAYER_CONTROL player1 ON

				IF player_been_taught_move[0] = 0 

					PRINT_HELP_FOREVER ( GYM1106 ) // Defeat your opponent to learn new moves if you leave the ring you lose! 
				
				ENDIF

				IF player_been_taught_move[0] = 1 
					PRINT_HELP_FOREVER GYM1_84  
				ENDIF
		  		  
				GOSUB gymsf_fade_in

				sf_left_ring = 0

				// *******************************************************************************************************************
				// *																												 *
				// *										    Fighting in the ring   												 *
				// *																												 *
				// *******************************************************************************************************************

				IF HAS_MISSION_AUDIO_LOADED 4
				AND NOT IS_CHAR_DEAD scplayer

					REPORT_MISSION_AUDIO_EVENT_AT_CHAR scplayer SOUND_GYM_BOXING_BELL

				ENDIF

				WHILE NOT IS_CHAR_DEAD scplayer

					WAIT 0

					IF player_been_taught_move[0] = 1

						IF gymsf_help_txt = 0
							TIMERA = 0
							CLEAR_HELP
							PRINT_HELP_FOREVER ( GYM1_94 )  
							gymsf_help_txt = 1
						ENDIF

						IF NOT IS_GERMAN_GAME

							IF TIMERA > 6000
							AND gymsf_help_txt = 1
								CLEAR_HELP
								PRINT_HELP_FOREVER ( GYM1_95 )  
								TIMERA = 0
								gymsf_help_txt = 2
							ENDIF

							IF TIMERA > 6000
							AND gymsf_help_txt = 2
								CLEAR_HELP
								PRINT_HELP_FOREVER ( GYM1_96 )  
								TIMERA = 0
								gymsf_help_txt = 3
							ENDIF

						ELSE

							IF TIMERA > 6000
							AND gymsf_help_txt = 1
								CLEAR_HELP
								PRINT_HELP_FOREVER ( GYM1_96 )  
								TIMERA = 0
								gymsf_help_txt = 3
							ENDIF

						ENDIF

						IF TIMERA > 6000
						AND gymsf_help_txt = 3

							CLEAR_HELP

							TIMERA = 0

							gymsf_help_txt = 0

						ENDIF

					ENDIF

					IF NOT IS_CHAR_DEAD SF_DJ

						IF NOT LOCATE_CHAR_ANY_MEANS_3D SF_DJ 768.4611 -36.6685 999.6865 6.0 6.0 6.0 FALSE

							GOTO sf_fight2_over	

						ENDIF

					ENDIF

					IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 768.4611 -36.6685 999.6865 6.0 6.0 6.0 FALSE

						GOSUB gymsf_fade_out

						DELETE_CHAR SF_DJ

						// DJ													    
						CREATE_CHAR PEDTYPE_CIVMALE OMOkung 768.0640 -41.5728 999.6865 SF_DJ

						SET_CHAR_HEADING SF_DJ 358.0992   
						PERFORM_SEQUENCE_TASK SF_DJ sf_seq_dj_boxer
						SET_CHAR_DECISION_MAKER SF_DJ gym_decision_tough
						GIVE_MELEE_ATTACK_TO_CHAR SF_DJ MCOMBO_UNARMED_3 6

						// ****************************************************************************************
						// *							        Others   						  				  *
						// ****************************************************************************************

						// Boxer A
						CREATE_CHAR PEDTYPE_CIVMALE OMYKARA 769.8199 -36.4933 999.6865 SF_boxer_A
						SET_CHAR_HEADING SF_boxer_A 95.1885   
						PERFORM_SEQUENCE_TASK SF_boxer_A seq_fight_a
						SET_CHAR_DECISION_MAKER SF_boxer_A gym_decision_tough
						GIVE_MELEE_ATTACK_TO_CHAR SF_boxer_A MCOMBO_UNARMED_3 6

						// Boxer B
						CREATE_CHAR PEDTYPE_CIVMALE WMYKARA 766.1017 -37.3952 999.6865 SF_boxer_B
						SET_CHAR_HEADING SF_boxer_B 271.1251   
						PERFORM_SEQUENCE_TASK SF_boxer_B seq_fight_b
						SET_CHAR_DECISION_MAKER SF_boxer_B gym_decision_tough
						GIVE_MELEE_ATTACK_TO_CHAR SF_boxer_B MCOMBO_UNARMED_3 6

						IF NOT IS_CHAR_DEAD scplayer

							SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 762.7560 -37.2153 999.6865   
							
							SET_CHAR_HEADING scplayer 90.0000

							SET_PLAYER_CONTROL player1 ON

						ENDIF

						CLEAR_PRINTS

						CLEAR_HELP

						GOSUB gymsf_fade_in

						IF player_been_taught_move[0] = 0

							PRINT_NOW ( GYM1_92 ) 5000 1 // ~r~You left the ring and forfeited

						ENDIF

						GOTO gymsf_loop

					ENDIF

					IF IS_CHAR_DEAD SF_DJ

						GOTO sf_fight2_over

					ENDIF

				ENDWHILE

				sf_fight2_over:

				CLEAR_HELP

				IF player_been_taught_move[0] = 1

					GOSUB gymsf_fade_out

					GOTO gymsf_skip_cutscene

				ENDIF

				GOSUB gymsf_fade_out

				DELETE_CHAR SF_DJ

				CLEAR_AREA 768.1746 -36.6986 999.6865 5.0 TRUE

				// DJ
				CREATE_CHAR PEDTYPE_CIVMALE OMOkung 768.0640 -41.5728 999.6865 SF_DJ

				SET_CHAR_HEADING SF_DJ 358.0992   

				SET_CHAR_DECISION_MAKER SF_DJ gym_decision_none

				IF NOT IS_CHAR_DEAD SF_DJ

					SET_CHAR_COORDINATES_DONT_WARP_GANG SF_DJ 771.4357 -36.9728 999.6865  

					SET_CHAR_HEADING SF_DJ 88.1801

				ENDIF

				IF NOT IS_CHAR_DEAD scplayer

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

					SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 764.6453 -37.1052 999.6865  

					SET_CHAR_HEADING scplayer 273.9816

				ENDIF

				IF NOT IS_CHAR_DEAD SF_DJ

					SET_CHAR_DECISION_MAKER SF_DJ DM_PED_EMPTY

				ENDIF

		 		GOSUB gymsf_set_camera
				
				SET_FIXED_CAMERA_POSITION 762.8773 -35.4754 1001.5452 0.0 0.0 0.0 // High bike
				POINT_CAMERA_AT_POINT 763.8198 -35.7030 1001.3005 JUMP_CUT

				GOSUB gymsf_fade_in

				PRINT_NOW ( KUNG_5 ) 4000 1 // Heres the new moves watch and learn

				// ****************************** Audio *********************************************************

				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_KUNG_5 // Absorb with the eyes, young student, and you will learn!

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

				SET_FIXED_CAMERA_POSITION 767.4280 -39.4061 1000.4160 0.0 0.0 0.0 // High bike
				POINT_CAMERA_AT_POINT 768.1919 -38.7609 1000.4003 JUMP_CUT

				PRINT_NOW ( GYM1_97 ) 2000 1 // Running Attack

				TIMERA = 0
				WHILE TIMERA < 2000
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					ENDIF
				ENDWHILE 

				// ****************************** Audio *********************************************************

				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_KUNG_6 // Strike like an atomic cobra!

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

				IF NOT IS_CHAR_DEAD SF_DJ

					TASK_PLAY_ANIM SF_DJ FIGHTC_M FIGHT_C 4.0 FALSE TRUE TRUE FALSE -1

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

					SET_FIXED_CAMERA_POSITION 768.0062 -35.1731 1000.2761 0.0 0.0 0.0 // High bike
					POINT_CAMERA_AT_POINT 768.7570 -35.8325 1000.3144 JUMP_CUT

					PRINT_NOW ( GYM1_98 ) 2000 1 // Ground Attack

					TIMERA = 0
					WHILE TIMERA < 2000
						WAIT 0
						IF IS_BUTTON_PRESSED PAD1 CROSS
						ENDIF
					ENDWHILE 

					// ****************************** Audio *********************************************************

					CLEAR_MISSION_AUDIO 1

				 	LOAD_MISSION_AUDIO 1 SOUND_KUNG_7 // Nothing clever here, just kick’em while they’re down!

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

					IF NOT IS_CHAR_DEAD SF_DJ

						TASK_PLAY_ANIM SF_DJ FIGHTC_G FIGHT_C 4.0 FALSE TRUE TRUE FALSE -1

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

				SET_FIXED_CAMERA_POSITION 772.6448 -38.0110 1000.4780 0.0 0.0 0.0 // High bike
				POINT_CAMERA_AT_POINT 771.6790 -37.7522 1000.4850 JUMP_CUT

				PRINT_NOW ( GYM1_99 ) 2000 1 // Combo Attack

				TIMERA = 0
				WHILE TIMERA < 2000
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					ENDIF
				ENDWHILE 

				// ****************************** Audio *********************************************************

				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_KUNG_8 // Become the avalanche and bury your opponent!

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


				IF NOT IS_CHAR_DEAD SF_DJ
					
					FREEZE_CHAR_POSITION SF_DJ TRUE  

					TASK_PLAY_ANIM SF_DJ FIGHTC_3 FIGHT_C 4.0 FALSE TRUE TRUE FALSE -1

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

				gymsf_skip_cutscene:

				GOSUB gymsf_fade_out

				DELETE_CHAR SF_DJ

				// DJ
				CREATE_CHAR PEDTYPE_CIVMALE OMOkung 768.0640 -41.5728 999.6865 SF_DJ
				
				SET_CHAR_HEADING SF_DJ 358.0992   
				PERFORM_SEQUENCE_TASK SF_DJ sf_seq_dj_boxer
				SET_CHAR_DECISION_MAKER SF_DJ gym_decision_tough
				GIVE_MELEE_ATTACK_TO_CHAR SF_DJ MCOMBO_UNARMED_3 6

				// Boxer A
				CREATE_CHAR PEDTYPE_CIVMALE OMYKARA 769.8199 -36.4933 999.6865 SF_boxer_A
				SET_CHAR_HEADING SF_boxer_A 95.1885   
				PERFORM_SEQUENCE_TASK SF_boxer_A seq_fight_a
				SET_CHAR_DECISION_MAKER SF_boxer_A gym_decision_tough
				GIVE_MELEE_ATTACK_TO_CHAR SF_boxer_A MCOMBO_UNARMED_3 6

				// Boxer B
				CREATE_CHAR PEDTYPE_CIVMALE WMYKARA 766.1017 -37.3952 999.6865 SF_boxer_B
				SET_CHAR_HEADING SF_boxer_B 271.1251   
				PERFORM_SEQUENCE_TASK SF_boxer_B seq_fight_b
				SET_CHAR_DECISION_MAKER SF_boxer_B gym_decision_tough
				GIVE_MELEE_ATTACK_TO_CHAR SF_boxer_B MCOMBO_UNARMED_3 6

				GOSUB gymsf_restore_camera

				IF NOT IS_CHAR_DEAD scplayer

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

					SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 765.9402 -41.6020 999.6865     
					
					SET_CHAR_HEADING scplayer 265.0070

					SET_PLAYER_CONTROL player1 ON

					SET_CAMERA_BEHIND_PLAYER

				ENDIF

				GOSUB gymsf_fade_in

			 /*	IF player_been_taught_move[0] = 1

					GOSUB gymsf_fade_in

				ENDIF	*/

				IF gym_sf_defeated = 0

					PLAYER_MADE_PROGRESS 1

					gym_sf_defeated = 1

				ENDIF

				IF player_been_taught_move[0] = 0 

					PRINT_NOW ( GYM1_93 ) 3000 1 // New moves learned!

				ENDIF

				GIVE_MELEE_ATTACK_TO_CHAR scplayer MCOMBO_UNARMED_3 6

				SET_PLAYER_CYCLE_WEAPON_BUTTON player1 TRUE

				player_been_taught_move[0] = 1
				player_been_taught_move[1] = 0
				player_been_taught_move[2] = 0

			ENDIF

		ENDIF

	ENDIF

ENDIF
/*
IF NOT IS_CHAR_DEAD SF_DJ

	GET_CHAR_HEALTH SF_DJ gym_hlth

	IF gym_hlth < 100

		SET_CHAR_HEALTH SF_DJ 100	

	ENDIF

ENDIF */
  
GOTO gymsf_loop

///////////////////////////////////////// EXIT EXERCISE	//////////////////////////////////////

// Mission gymsf failed

mission_gymsf_failed:

RETURN

// mission gymsf 1 passed

mission_gymsf_passed:

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

mission_cleanup_gymsf:

	IF NOT IS_CHAR_DEAD scplayer

		LISTEN_TO_PLAYER_GROUP_COMMANDS scplayer TRUE

	ENDIF

	MARK_MODEL_AS_NO_LONGER_NEEDED WMYJG
	MARK_MODEL_AS_NO_LONGER_NEEDED OMYKARA
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYlG
	MARK_MODEL_AS_NO_LONGER_NEEDED OMOkung
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYKARA

	REMOVE_ANIMATION GYMNASIUM
	REMOVE_ANIMATION PARK
	REMOVE_ANIMATION FIGHT_C

	CLEAR_MISSION_AUDIO 4

	gym_is_running = 0

	flag_player_on_mission = 0

	MISSION_HAS_FINISHED

RETURN

gymsf_set_camera:

	SWITCH_WIDESCREEN ON
	SET_PLAYER_CONTROL player1 OFF
	SET_EVERYONE_IGNORE_PLAYER player1 TRUE

RETURN

gymsf_restore_camera:

	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	RESTORE_CAMERA_JUMPCUT
	SET_EVERYONE_IGNORE_PLAYER player1 FALSE
 
RETURN

gymsf_fade_out:

	DO_FADE 500 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0

		IF IS_BUTTON_PRESSED PAD1 CROSS
			DO_FADE 1 FADE_OUT
		ENDIF

	ENDWHILE

RETURN

gymsf_fade_in:

	DO_FADE 500 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

sf_load_sample:

	CLEAR_MISSION_AUDIO 1

	LOAD_MISSION_AUDIO 1 sf_audio

	sf_playing = 0

RETURN

sf_play_sample:

	IF HAS_MISSION_AUDIO_LOADED 1
	AND sf_playing = 0

		PLAY_MISSION_AUDIO 1

		PRINT_NOW ( $sf_print ) 10000 1 

		sf_playing = 1

	ENDIF

	IF HAS_MISSION_AUDIO_FINISHED 1
	AND sf_playing = 1

		CLEAR_MISSION_AUDIO 1	

		CLEAR_THIS_PRINT $sf_print

		sf_playing = 2

	ENDIF
	
RETURN

}
