MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************Driving School**********************************
// ***************************************** learning to drive *****************************
// *****************************************************************************************
// *****************************************************************************************
SCRIPT_NAME dskool

// Mission start stuff
GOSUB mission_start_dskool
IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_failed_dskool
ENDIF
GOSUB mission_cleanup_dskool							  
MISSION_END
{ 
// ****************************************Mission Start************************************
mission_start_dskool:
flag_player_on_mission = 1
IF driving_test_passed = 0
	REGISTER_MISSION_GIVEN
ENDIF
LOAD_MISSION_TEXT DS
IF flag_player_on_mission = 0
	CREATE_CAR BANSHEE car_posx car_posy perfect_positionz instructor_car
	CREATE_CAR BANSHEE car_posx car_posy perfect_positionz dummy_car1
	CREATE_CAR BANSHEE car_posx car_posy perfect_positionz dummy_car2
	CREATE_OBJECT_NO_OFFSET trafficcone 0.0 0.0 100.0 trafficcones[20]
	CREATE_OBJECT_NO_OFFSET trafficcone 0.0 0.0 100.0 trafficcones[30]
	CREATE_OBJECT ad_jump car_posx car_posy perfect_positionz ramp1
	CREATE_OBJECT ad_jump car_posx car_posy perfect_positionz ramp2
	CREATE_OBJECT ad_jump car_posx car_posy perfect_positionz f1_stinger
	CREATE_RANDOM_CHAR 0.0 0.0 100.0 f1_test_crash_dummy 
	CREATE_RANDOM_CHAR 0.0 0.0 100.0 f1_test_crash_dummy2 
ENDIF

SET_FADING_COLOUR 0 0 0
CLEAR_PRINTS
CLEAR_THIS_PRINT_BIG_NOW 1
WAIT 0

// *************************************Set Flags/variables*********************************
DO_FADE 500 FADE_OUT
WHILE GET_FADING_STATUS
    WAIT 0
ENDWHILE 


LVAR_INT playback_flag  
LVAR_INT d1_blip_flag
LVAR_FLOAT instructor_car_roll 
LVAR_INT instructor_car_roll_score instructor_car_roll_plus_minus 					 
LVAR_INT f1_control_flag f1_direction_flag 
LVAR_INT f1_print_top_scores_flag
LVAR_INT f1_checkpoint
 
LVAR_INT f1_fade_flag f1_alpha 
LVAR_INT f1_which_medal_displayed f1_which_score_displayed
LVAR_INT f1_old_score 
LVAR_INT f1_area_code
LVAR_INT f1_empty_decisions
LVAR_INT f1_last_played
LVAR_INT f1_r f1_g f1_b f1_alpha2
LVAR_INT f1_test_crash_dummy f1_test_crash_dummy2
LVAR_INT ramp1
LVAR_INT ramp2
LVAR_INT f1_stinger

VAR_INT f1_bronze_award
VAR_INT f1_silver_award
VAR_INT f1_gold_award

LVAR_INT f1_camera_mode

//speech
LVAR_INT f1_speech_goals f1_speech_control_flag f1_speech_flag f1_random_last_label 
LVAR_TEXT_LABEL f1_print_label[7] 
LVAR_INT f1_audio_label[7] 
LVAR_INT f1_last_label //f1_played_random_speech[2]   
LVAR_INT f1_slot1 f1_slot2 f1_slot_load f1_play_which_slot


playback_flag = 0   ///needs to be set to 0
f1_control_flag = 0
f1_fade_flag = 0
f1_alpha = 0
f1_alpha2 = 0
f1_which_medal_displayed = 0
f1_which_score_displayed = 0
f1_old_score = 0
f1_area_code = 0

mission_selection = 1  //needs to be set to 1
f1_last_played = 1
start_coordsx = -2050.6 //these define the starting position of each track.
start_coordsy = -130.0

noticeboard_x = -2031.2   
noticeboard_y = -118.0 
noticeboard_z = 1034.2

f1_speech_goals = 0 
f1_speech_control_flag = 0 
f1_speech_flag = 0 
f1_random_last_label = 0
f1_last_label = 0
f1_slot1 = 0 
f1_slot2 = 0 
f1_slot_load = 0 
f1_play_which_slot = 0

f1_camera_mode = 0

COPY_CHAR_DECISION_MAKER DM_PED_EMPTY f1_empty_decisions

REQUEST_MODEL infernus
REQUEST_MODEL blistac
REQUEST_MODEL banshee
REQUEST_MODEL COPCARSF
REQUEST_MODEL SFPD1
REQUEST_MODEL trafficcone
REQUEST_MODEL temp_stinger2
REQUEST_MODEL garys_luv_ramp
REQUEST_MODEL ad_jump
REQUEST_MODEL ad_roadmark1 // for the U shape line
REQUEST_MODEL ad_roadmark2 // for the longer C shape
REQUEST_MODEL ad_finish //for burn and lap

REQUEST_MODEL taxi
REQUEST_MODEL supergt

LOAD_TEXTURE_DICTIONARY LD_drv

LOAD_SPRITE 1 bronze
LOAD_SPRITE 2 silver
LOAD_SPRITE 3 gold
LOAD_SPRITE 4 ribb
LOAD_SPRITE 5 tvcorn
LOAD_SPRITE 6 naward
LOAD_SPRITE 7 ribbw
LOAD_SPRITE 8 blkdot
LOAD_SPRITE 9 tvbase
LOAD_SPRITE 10 nawtxt

LOAD_ALL_MODELS_NOW
LOAD_SCENE -2031.1 -118.2 1034.2
CLEAR_THIS_BIG_PRINT FAR_1  // Back to School
ENABLE_AMBIENT_CRIME FALSE

SWITCH_ROADS_OFF -2015.37 -76.08 10.0 -2078.3 -66.75 50.0 

CLEAR_WANTED_LEVEL player1 

DEACTIVATE_GARAGE bodLAwN
DEACTIVATE_GARAGE modlast
DEACTIVATE_GARAGE mdsSFSe
IF japcar_mod_garage_open = 1
	DEACTIVATE_GARAGE mds1SFS
	DEACTIVATE_GARAGE vEcmod 
ENDIF
SET_NO_RESPRAYS ON




///DEBUG ---- put back in cutscene, take out this flag/////////
//f1_which_missions_are_open_flag = 16
///////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////// CUTSCENE - PLAYED ONCE ///////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////

IF f1_scripted_cut = 0
	WHILE f1_scripted_cut = 0
		WAIT 0 

		IF f1_control_flag = 0 
			f1_which_missions_are_open_flag = 1 

			SET_PLAYER_CONTROL player1 OFF		
			SWITCH_WIDESCREEN ON
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			SET_CHAR_COORDINATES scplayer -2031.7 -116.4 1034.2	
			SET_CHAR_HEADING scplayer 86.9
			
			//creating a secretary
			REQUEST_MODEL WMYMECH
			LOAD_ALL_MODELS_NOW
			LVAR_INT f1_secretary
			CLEAR_AREA -2033.2 -116.5 1034.2 30.0 TRUE 
			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH -2033.2 -116.5 1034.2 f1_secretary 
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH f1_secretary TRUE 

			SET_CHAR_DECISION_MAKER f1_secretary f1_empty_decisions  

			SET_CHAR_HEADING f1_secretary 270.0
			SET_CHAR_NEVER_TARGETTED f1_secretary TRUE 
			SET_CHAR_KEEP_TASK f1_secretary TRUE
			TASK_STAND_STILL f1_secretary -2  
			
			SET_FIXED_CAMERA_POSITION -2030.7 -115.8 1035.8 0.0 0.0 0.0  
			POINT_CAMERA_AT_POINT -2033.9 -116.1 1035.7 JUMP_CUT

			//cutscene dialogue
			f1_speech_goals = 1
			f1_speech_control_flag = 0
			GOSUB f1_dialogue_setup 

			IF NOT IS_CHAR_DEAD f1_secretary 
				START_CHAR_FACIAL_TALK f1_secretary 20000
			ENDIF

			DO_FADE 1000 FADE_IN
			SKIP_CUTSCENE_START
			timera = 0 
			f1_control_flag = 1  
		ENDIF
		
		IF f1_control_flag = 1 
			IF f1_speech_goals = 0
				SKIP_CUTSCENE_END
				IF NOT IS_CHAR_DEAD f1_secretary 
					STOP_CHAR_FACIAL_TALK f1_secretary
				ENDIF
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				f1_speech_goals = 0

				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				CLEAR_PRINTS 
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SWITCH_WIDESCREEN OFF	
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				SET_PLAYER_CONTROL player1 ON
				f1_control_flag = 0
				f1_scripted_cut = 1
				
				DO_FADE 500 FADE_IN
				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				GOTO mission_failed_dskool
			ENDIF
		ENDIF

		GOSUB f1_overall_dialogue

	ENDWHILE
ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////// NOTICEBOARD //////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
noticeboard_setup:

//clearing old shit away
SET_PLAYER_CONTROL player1 OFF
CLEAR_HELP
CLEAR_PRINTS
SET_CAR_DENSITY_MULTIPLIER 0.0
SET_PED_DENSITY_MULTIPLIER 0.0

//preparing for noticeboard
GOSUB setting_up_variables
playback_flag = 0
f1_control_flag = 0
USE_TEXT_COMMANDS TRUE

f1_alpha = 255
f1_fade_flag = 2

IF f1_which_missions_are_open_flag < 17 
	mission_selection = f1_last_played
ENDIF
IF mission_selection = 3
	mission_selection = 4
ENDIF	
IF mission_selection = 6
	mission_selection = 7
ENDIF	
IF mission_selection = 8
	mission_selection = 9
ENDIF	
IF mission_selection = 12
	mission_selection = 13
ENDIF	

SET_AREA_VISIBLE 0
SET_CHAR_AREA_VISIBLE scplayer 0 

SET_CHAR_COORDINATES scplayer -2035.4 -119.1 33.2
LOAD_SCENE -2035.4 -119.1 33.2 

//loading in sound
LOAD_MISSION_AUDIO 3 SOUND_VIDEOTAPE_NOISE
WHILE NOT HAS_MISSION_AUDIO_LOADED 3
	WAIT 0
ENDWHILE
PLAY_MISSION_AUDIO 3

CLEAR_EXTRA_COLOURS FALSE

DISPLAY_HUD FALSE
DISPLAY_RADAR FALSE

DO_FADE 500 FADE_IN
GOSUB f1_drawing_tv_screen
WHILE GET_FADING_STATUS
	WAIT 0
	GOSUB f1_drawing_tv_screen
ENDWHILE
GOSUB f1_drawing_tv_screen

//////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////// MISSION SELECTION ////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
mission_selection_loop:
WAIT 0

		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	        GOTO mission_passed_dskool  
		ENDIF

		GOSUB f1_drawing_tv_screen
		
		//////CHOOSING WHICH MISSION
		IF f1_control_flag = 0 
			
			//choosing which mission
			GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
			IF LStickX < -100
			OR IS_BUTTON_PRESSED PAD1 DPADLEFT

				f1_last_played = f1_last_played - 1
				mission_selection = mission_selection - 1

				IF f1_last_played = 3
					f1_last_played = 2
				ENDIF	
				IF f1_last_played = 6
					f1_last_played = 5
				ENDIF	
				IF f1_last_played = 8
					f1_last_played = 7
				ENDIF
				IF f1_last_played = 12
					f1_last_played = 11
				ENDIF
				
				
				IF mission_selection = 3
					mission_selection = 2
				ENDIF	
				IF mission_selection = 6
					mission_selection = 5
				ENDIF	
				IF mission_selection = 8
					mission_selection = 7
				ENDIF	
				IF mission_selection = 12
					mission_selection = 11
				ENDIF

				IF f1_which_missions_are_open_flag > 1 
					IF playback_flag < 4 
						playback_flag = 3 //overriding playback
					ENDIF
				ENDIF
			ENDIF

			IF LStickX > 100 
			OR IS_BUTTON_PRESSED PAD1 DPADRIGHT

				f1_last_played = f1_last_played + 1
				mission_selection = mission_selection + 1
				IF f1_last_played = 3
					f1_last_played = 4
				ENDIF	
				IF f1_last_played = 6
					f1_last_played = 7
				ENDIF	
				IF f1_last_played = 8
					f1_last_played = 9
				ENDIF	
				IF f1_last_played = 12
					f1_last_played = 13
				ENDIF
				
				IF mission_selection = 3
					mission_selection = 4
				ENDIF	
				IF mission_selection = 6
					mission_selection = 7
				ENDIF	
				IF mission_selection = 8
					mission_selection = 9
				ENDIF	
				IF mission_selection = 12
					mission_selection = 13
				ENDIF
				
				
				IF f1_which_missions_are_open_flag > 1 
					IF playback_flag < 4
						playback_flag = 3 //overriding playback	
					ENDIF
				ENDIF
			ENDIF								
			
			IF mission_selection < 1
				mission_selection = f1_which_missions_are_open_flag
				f1_last_played = f1_which_missions_are_open_flag 
			ENDIF

			IF mission_selection > f1_which_missions_are_open_flag
				mission_selection = 1
				f1_last_played = 1
			ENDIF
			

			IF f1_which_missions_are_open_flag > 1
				WHILE LStickX < -100
					WAIT 0

					GOSUB f1_drawing_tv_screen
					GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
				ENDWHILE
				WHILE LStickX > 100
		            WAIT 0

					GOSUB f1_drawing_tv_screen
		            GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
				ENDWHILE
			ENDIF 
		
			WHILE IS_BUTTON_PRESSED PAD1 DPADLEFT
				WAIT 0

				GOSUB f1_drawing_tv_screen 
			ENDWHILE  

			WHILE IS_BUTTON_PRESSED PAD1 DPADRIGHT
				
				WAIT 0

				GOSUB f1_drawing_tv_screen 
			ENDWHILE 

		
			//triggering mission
			IF IS_BUTTON_PRESSED PAD1 CROSS
				IF mission_selection = 4 
				OR mission_selection = 7 
				OR mission_selection = 9 
				OR mission_selection = 13 
					f1_control_flag = 1	
				ELSE
					IF playback_flag < 4
						playback_flag = 3
					ENDIF
					GOTO start_mission
				ENDIF
			ENDIF
		ENDIF

		////SELECTING WHETHER TO GO RIGHT OR LEFT ON SOME MISSIONS
		IF f1_control_flag = 1
			IF playback_flag < 4
				playback_flag = 3
			ENDIF
			IF playback_flag = 0 
				playback_flag = 5
			ENDIF
				
			GOSUB which_course_text 
			SET_TEXT_COLOUR 255 255 255 255
		   	DISPLAY_TEXT 320.0 160.0 DS1_59 //Which course?

			GOSUB which_course_text 
			SET_TEXT_CENTRE OFF
			SET_TEXT_COLOUR 255 255 255 255
			DISPLAY_TEXT 100.0 205.0 DS1_60  

			GOSUB which_course_text 
			SET_TEXT_CENTRE OFF
			SET_TEXT_COLOUR 255 255 255 255
			DISPLAY_TEXT 101.0 231.0 DS1_61
						
			GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
			IF LStickX < -100
			OR IS_BUTTON_PRESSED PAD1 DPADLEFT
				GOTO start_mission
			ENDIF
				
			IF LStickX > 100 
			OR IS_BUTTON_PRESSED PAD1 DPADRIGHT 
				IF mission_selection = 4 
					mission_selection = 3
				ENDIF
				IF mission_selection = 7 
					mission_selection = 6
				ENDIF
				IF mission_selection = 9 
					mission_selection = 8
				ENDIF
				IF mission_selection = 13 
					mission_selection = 12
				ENDIF
				GOTO start_mission
			ENDIF
		ENDIF

		//opening all the missions
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q 
			IF driving_test_passed = 0
				f1_the360_best_score = 100
				f1_the180_best_score = 100
				f1_whiprightterminate_best_score = 100
				f1_popcontrol_best_score = 100
				f1_burnlapright_best_score = 100
				f1_conecoilright_best_score = 100
				f1_the90_best_score = 100 
				f1_wheelieweave_best_score = 100
				f1_spinrightgo_best_score = 100
				f1_pittechnique_best_score = 100
				f1_alleyoop_best_score = 100 
				f1_cityslicking_best_score = 100  
				SWITCH_CAR_GENERATOR f1_chunky 101
				SWITCH_CAR_GENERATOR f1_dbp 101
				driving_test_passed = 1
			ENDIF	
			f1_which_missions_are_open_flag = 16
		ENDIF
		
		//quitting the driving school
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			GOSUB f1_drawing_tv_screen
			DO_FADE 500 FADE_OUT
			WHILE GET_FADING_STATUS
				GOSUB f1_drawing_tv_screen
				WAIT 0
				GOSUB f1_drawing_tv_screen
			ENDWHILE
			GOSUB f1_drawing_tv_screen

			CLEAR_MISSION_AUDIO 3

			CLEAR_PRINTS
			CLEAR_EXTRA_COLOURS TRUE

			SET_CHAR_AREA_VISIBLE scplayer 3
			SET_AREA_VISIBLE 3
			REQUEST_COLLISION -2031.1 -118.2
			LOAD_SCENE -2031.1 -118.2 1034.2
			
			SET_CHAR_COORDINATES scplayer -2029.7 -115.5 1034.2
			SET_CHAR_HEADING scplayer 0.0

			IF NOT IS_CAR_DEAD instructor_car
				IF IS_PLAYBACK_GOING_ON_FOR_CAR instructor_car
					STOP_PLAYBACK_RECORDED_CAR instructor_car 
				ENDIF
			ENDIF
			DELETE_CAR instructor_car
			CLEAR_AREA -2051.0 -174.0 34.0 300.0 TRUE
			CLEAR_ONSCREEN_TIMER car_timer
			FREEZE_ONSCREEN_TIMER FALSE
			DELETE_CHAR f1_test_crash_dummy 
			DELETE_CHAR f1_test_crash_dummy2 
			DELETE_CAR dummy_car1
			DELETE_CAR dummy_car2
			DELETE_OBJECT ramp1
			DELETE_OBJECT ramp2
			DELETE_OBJECT f1_stinger
			REMOVE_CAR_RECORDING 1
			REMOVE_CAR_RECORDING 2
			REMOVE_CAR_RECORDING 3
			REMOVE_CAR_RECORDING 4
			REMOVE_CAR_RECORDING 5
			REMOVE_CAR_RECORDING 7
			REMOVE_CAR_RECORDING 9
			REMOVE_CAR_RECORDING 10
			REMOVE_CAR_RECORDING 11
			REMOVE_CAR_RECORDING 13
			REMOVE_CAR_RECORDING 14
			REMOVE_CAR_RECORDING 15
			REMOVE_CAR_RECORDING 16
			GOSUB deleting_cones

			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT

			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			GOTO mission_failed_dskool
		ENDIF

	GOSUB watching_demo

GOTO mission_selection_loop
start_mission:
DO_FADE 500 FADE_OUT
WHILE GET_FADING_STATUS
	WAIT 0
	GOSUB f1_drawing_tv_screen
ENDWHILE 
IF NOT IS_CAR_DEAD instructor_car 
	STOP_PLAYBACK_RECORDED_CAR instructor_car
ENDIF
CLEAR_MISSION_AUDIO 3
DELETE_CAR instructor_car
CLEAR_AREA noticeboard_x noticeboard_y noticeboard_z 300.0 TRUE
CLEAR_ONSCREEN_TIMER car_timer
FREEZE_ONSCREEN_TIMER FALSE
DELETE_CHAR f1_test_crash_dummy
DELETE_CHAR f1_test_crash_dummy2
DELETE_CAR dummy_car1
DELETE_CAR dummy_car2
DELETE_CAR instructor_car
DELETE_OBJECT ramp1
DELETE_OBJECT ramp2
DELETE_OBJECT f1_stinger
GOSUB deleting_cones
playback_flag = 5 ///making sure playback flag doesn't DSTART up missions 
DISPLAY_HUD TRUE
DISPLAY_RADAR TRUE

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////The "360" - Doing a burnout donut//////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF mission_selection = 1 

	refresh_360:

	GOSUB start_initialise_stuff

	setup_360:


	perfect_heading = 180.0
	car_timer = 11000
	GOSUB setting_up_variables
	
	//creating cars
	CREATE_CAR infernus perfect_positionx perfect_positiony perfect_positionz instructor_car
	SET_CAR_HEADING instructor_car 180.0

	GOSUB creating_cones
	
	IF playback_flag = 0
			SET_FIXED_CAMERA_POSITION -2028.3 -143.0 38.4 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2052.3 -129.9 34.2 JUMP_CUT
			IF NOT IS_CAR_DEAD instructor_car 
				CREATE_RANDOM_CHAR_AS_DRIVER instructor_car f1_test_crash_dummy
			ENDIF   
		RETURN
	ENDIF

	//setting up finishing point
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 0.0 0.0 0.0 finish_rightx finish_righty perfect_positionz  

	CLEAR_PRINTS

	PRINT_BIG DS1_88 5000 4 
	
	PRINT_NOW ( DS1_44 ) 5000 4 // Use the rear wheel drive car to do a burnout donut. 
	GOSUB stop_initialise_stuff

	//starting challenge
	the_360_loop:
	WAIT 0
		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_car_dead_flag = 2
			GOTO after_scores_360																		  
		ENDIF 

		//checking car isnt dead
		GOSUB instructor_car_dead
		IF instructor_car_dead_flag = 1 
			GOTO mission_failed_dskool
		ENDIF

		//checking car has moved and player is pressing cross or square
		IF car_started = 0
			IF NOT instructor_car_dead_flag = 2
				PRINT_BIG DS1_88 5000 4  
    			PRINT_NOW ( DS1_44 ) 5000 4 // Use the rear wheel drive car to do a burnout donut. 
			ENDIF
			GOSUB has_car_started
		//player is going
		ELSE
			IF NOT IS_CAR_DEAD instructor_car
				
				//checking timer hasnt ran to 0
				GOSUB car_timer_0

				//checking how far through the circle a player is
				GET_CAR_HEADING instructor_car perfect_heading 
				//clockwise
				IF variablec = 0
					IF perfect_heading > 80.0 
						IF perfect_heading < 90.0 
							variablec = 1
							f1_direction_flag = 1
						ENDIF
					ENDIF
				ENDIF
				IF f1_direction_flag = 1
					IF variablec = 1
						IF perfect_heading > 350.0 
							IF perfect_heading < 360.0 
								variablec = 2
							ENDIF
						ENDIF
					ENDIF
					IF variablec = 2
						IF perfect_heading > 270.0 
							IF perfect_heading < 280.0 
								variablec = 3
							ENDIF
						ENDIF
					ENDIF
					IF variablec = 3
						IF perfect_heading > 170.0 
							IF perfect_heading < 180.0 
								variablec = 4
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				//anti-clockwise
				IF variablec = 0
					IF perfect_heading > 270.0 
						IF perfect_heading < 280.0 
							f1_direction_flag = 2
							variablec = 1
						ENDIF
					ENDIF
				ENDIF
				IF f1_direction_flag = 2
					IF variablec = 1
						IF perfect_heading > 350.0 
							IF perfect_heading < 360.0 
								variablec = 2
							ENDIF
						ENDIF
					ENDIF
					IF variablec = 2
						IF perfect_heading > 80.0 
							IF perfect_heading < 90.0 
								variablec = 3
							ENDIF
						ENDIF
					ENDIF
					IF variablec = 3
						IF perfect_heading > 170.0 
							IF perfect_heading < 180.0 
								variablec = 4
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				//checking car is stopped or not
				IF NOT IS_BUTTON_PRESSED PAD1 CROSS
				OR NOT IS_BUTTON_PRESSED PAD1 SQUARE
				OR car_timer = 0

					GOSUB freeze_car_pos
				
					//CALCULATIONS FOR PLAYER SCORE
					 
					//position score 
					GOSUB position_score_calcs					 

					//heading - perfect heading is 0
					IF variablec = 4
						heading_score = 100
					ENDIF	 
					IF variablec = 3
						heading_score = 75
					ENDIF	 
					IF variablec = 2
						heading_score = 50
					ENDIF	 
					IF variablec = 1
						heading_score = 25
					ENDIF	 
					IF variablec = 0
						heading_score = 0
					ENDIF	 

					overall_score = position_score + heading_score				
					overall_score /= 2  
					IF position_score = 0
						overall_score = 0
					ENDIF	
					IF variablec = 0
						overall_score = 0
					ENDIF	
					 

					//losing points for hitting cones 
					GOSUB damage_cones_calcs

					//checking overall score is greater than 0 and clearing prints
					GOSUB checking_overall_score

					//checking overall score against the best score at present
					IF overall_score > f1_the360_best_score  	
						f1_old_score = f1_the360_best_score 
						f1_the360_best_score = overall_score
						f1_print_top_scores_flag = 1
						GOSUB f1_medal_check
					ELSE
						f1_which_medal_displayed = 0	
					ENDIF 	
				
					//opening next level
					IF f1_which_missions_are_open_flag = 1
						IF overall_score > 69
							f1_print_top_scores_flag = 2
							f1_which_missions_are_open_flag = 2
							instructor_car_dead_flag = 2
							f1_last_played = 2
						ENDIF
					ENDIF

					//printing scores onscreen
					timera = 0
					the_360_scores_loop:
						WAIT 0												    

						//changing camera position 
						GOSUB setting_up_camera			

						IF NOT f1_print_top_scores_flag = 2
							//checking player hasnt left car
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								instructor_car_dead_flag = 2
								GOTO after_scores_360																		  
							ENDIF 									    
						ENDIF

						//displaying scores
						GOSUB display_head_pos_dam_text				   

						//checking if player has skipped watching the scores
						GOSUB skip_scores
						IF finished_watching_scores = 1
							GOTO after_scores_360
						ENDIF
					GOTO the_360_scores_loop											    

					//reseting for another try
					after_scores_360:
					GOSUB mini_cleanup
					 
					GOSUB deleting_cones
																		  
					IF instructor_car_dead_flag = 2 
						CLEAR_PRINTS
						GOTO noticeboard_setup
					ELSE
						GOTO refresh_360 
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
	GOTO the_360_loop 
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
completed_360:
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////The "180" - doing a handbrake turn and coming back towards the player//////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF mission_selection = 2 

	refresh_180:

	GOSUB start_initialise_stuff

	setup_180:
			 
	perfect_heading = 0.0
	car_timer = 11000
	GOSUB setting_up_variables

	//creating cars
	CREATE_CAR blistac perfect_positionx perfect_positiony perfect_positionz instructor_car
	SET_CAR_HEADING instructor_car 180.0

	//creating cones
	GOSUB creating_cones
	
	IF playback_flag = 0
		SET_FIXED_CAMERA_POSITION -2060.2 -227.3 36.6 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -2049.5 -210.6 34.0 JUMP_CUT
			IF NOT IS_CAR_DEAD instructor_car 
				CREATE_RANDOM_CHAR_AS_DRIVER instructor_car f1_test_crash_dummy
			ENDIF   
		RETURN
	ENDIF

	//setting up the point the player MUST cross
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car -2.0 82.0 0.0 area_check1ax area_check1ay player_z 
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 2.0 88.0 0.0 area_check1bx area_check1by player_z 

	//setting up finishing point
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 0.0 0.0 0.0 finish_rightx finish_righty perfect_positionz  
	CLEAR_PRINTS

	PRINT_BIG DSTART 5000 4 
	
	PRINT_NOW ( DS1_45 ) 5000 4 // To do a 180, accelerate to top speed, press the R1 button to handbrake around the cone and then return. 
	GOSUB stop_initialise_stuff

	//starting challenge
	the_180_loop:
	WAIT 0
		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_car_dead_flag = 2
			GOTO after_scores_180																		  
		ENDIF 

		//checking car isnt dead
		GOSUB instructor_car_dead
		IF instructor_car_dead_flag = 1 
			GOTO mission_failed_dskool
		ENDIF

		//checking car has moved and player is pressing cross or square
		IF car_started = 0
			IF NOT instructor_car_dead_flag = 2
				PRINT_BIG DSTART 5000 4 
				PRINT_NOW ( DS1_45 ) 5000 4 // To do a 180, accelerate to top speed, press the R1 button to handbrake around the cone and then return. 
			ENDIF
			GOSUB has_car_started
		//player is going
		ELSE
			IF NOT IS_CAR_DEAD instructor_car
				
				//checking timer hasnt ran to 0
				GOSUB car_timer_0

				//checking player goes round a lap
				IF variablec = 0 
					IF IS_CAR_IN_AREA_2D instructor_car area_check1ax area_check1ay area_check1bx area_check1by FALSE
						variablec = 1
					ENDIF
				ENDIF	  
			
			
				//checking car is stopped or not
				IF IS_CAR_STOPPED instructor_car

					GOSUB freeze_car_pos
				
					//CALCULATIONS FOR PLAYER SCORE
					 
					//position score 
					GOSUB position_score_calcs					 
					IF variablec = 0
						position_score = 0
					ENDIF	 

					//heading - perfect heading is 0
					GET_CAR_HEADING instructor_car instructor_car_heading
					instructor_car_heading_int =# instructor_car_heading
					
					IF instructor_car_heading_int = 0
						heading_score = 100
						GOTO done_heading_calcs_180
					ENDIF 
					IF instructor_car_heading_int = 360
						heading_score = 100
						GOTO done_heading_calcs_180
					ENDIF 

					IF instructor_car_heading_int > 0
						IF instructor_car_heading_int < 21
							heading_score = 100
						ENDIF
					ENDIF 
				
					IF instructor_car_heading_int > 179
						IF instructor_car_heading_int < 340
							variablea = instructor_car_heading_int - 180
							variableb =# variablea
							variableb *= 0.62 
					   		heading_score =# variableb 
							GOTO done_heading_calcs_180
						ENDIF
					ENDIF  

					IF instructor_car_heading_int > 339
						IF instructor_car_heading_int < 360
							heading_score = 100
						ENDIF
					ENDIF 
					
					IF instructor_car_heading_int > 20
						IF instructor_car_heading_int < 180
							variableb =# instructor_car_heading_int
							variableb *= 0.62 //0.62 = 99(which is highest score possible) / 60 ( which is 180 - 20)
							variablea =# variableb
						 	heading_score = 100 - variablea 
							GOTO done_heading_calcs_180
						ENDIF
					ENDIF  
					
					done_heading_calcs_180:
					IF heading_score < 1
						heading_score = 0
					ENDIF 

					overall_score = position_score + heading_score				
					overall_score /= 2  
					IF position_score = 0
						overall_score = 0
					ENDIF	 

					//losing points for hitting cones 
					GOSUB damage_cones_calcs

					//checking overall score is greater than 0 and clearing prints
					GOSUB checking_overall_score

					//checking overall score against the best score at present
					IF overall_score > f1_the180_best_score   	
						f1_old_score = f1_the180_best_score 
						f1_the180_best_score = overall_score
						f1_print_top_scores_flag = 1
						GOSUB f1_medal_check
					ELSE
						f1_which_medal_displayed = 0	
					ENDIF 	

					//opening next level
					IF f1_which_missions_are_open_flag = 2
						IF overall_score > 69
							f1_print_top_scores_flag = 2
							f1_which_missions_are_open_flag = 4
							instructor_car_dead_flag = 2
							f1_last_played = 4
						ENDIF
					ENDIF

					//printing scores onscreen
					timera = 0
					the_180_scores_loop:
						WAIT 0

						//changing camera position 
						GOSUB setting_up_camera			

						IF NOT f1_print_top_scores_flag = 2
							//checking player hasnt left car
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								instructor_car_dead_flag = 2
								GOTO after_scores_180																		  
							ENDIF 									    
						ENDIF
					
						//displaying scores
						GOSUB display_head_pos_dam_text

						//checking if player has skipped watching the scores
						GOSUB skip_scores
						IF finished_watching_scores = 1
							GOTO after_scores_180
						ENDIF
					GOTO the_180_scores_loop 

					//reseting for another try
					after_scores_180:
					GOSUB mini_cleanup
					 
					GOSUB deleting_cones

					IF instructor_car_dead_flag = 2 
						CLEAR_PRINTS
						GOTO noticeboard_setup
					ELSE
						GOTO refresh_180 
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
	GOTO the_180_loop 
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////Whip and Terminate//////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF mission_selection = 3
OR mission_selection = 4

	refresh_whip_and_terminate:
	GOSUB start_initialise_stuff

	setup_whip_and_terminate:
																											

	//////setting up variables for this specific challenge/////
	perfect_heading = 180.0
	car_timer = 6000
	GOSUB setting_up_variables

	IF mission_selection = 3
		perfect_positionx += 20.0
	ENDIF
	IF mission_selection = 4
		perfect_positionx -= 20.0
	ENDIF

	//creating cars
	CREATE_CAR banshee perfect_positionx perfect_positiony perfect_positionz instructor_car
	SET_CAR_HEADING instructor_car perfect_heading

	//creating cones
	GOSUB creating_cones
  	
	IF playback_flag = 0
		SET_FIXED_CAMERA_POSITION -2022.6 -173.4 36.2 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -2034.2 -165.4 34.5 JUMP_CUT
			IF NOT IS_CAR_DEAD instructor_car 
				CREATE_RANDOM_CHAR_AS_DRIVER instructor_car f1_test_crash_dummy
			ENDIF   
		RETURN
	ENDIF

	//setting up finishing point
	IF mission_selection = 3
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 40.0 37.0 0.0 finish_rightx finish_righty perfect_positionz  
	ELSE
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car -40.0 37.0 0.0 finish_leftx finish_lefty perfect_positionz  
	ENDIF
	CLEAR_PRINTS
	
	PRINT_BIG DSTART 5000 4  
	
	PRINT_NOW ( DS1_32 ) 5000 4 // Powerslide around a tight corner and stop in the allocated area. 
	GOSUB stop_initialise_stuff

	whip_and_terminate_loop:
	WAIT 0 
	
		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_car_dead_flag = 2
			GOTO after_scores_whip_and_terminate																		  
		ENDIF 
	
		//checking car isnt dead
		GOSUB instructor_car_dead
		IF instructor_car_dead_flag = 1 
			GOTO mission_failed_dskool
		ENDIF

		//checking car has moved and player is pressing cross or square
		IF car_started = 0
			IF NOT instructor_car_dead_flag = 2
				PRINT_BIG DSTART 5000 4   
				PRINT_NOW ( DS1_32 ) 5000 4 // Powerslide around a tight corner and stop in the allocated area. 
			ENDIF
			GOSUB has_car_started	

		//player is going
		ELSE
			IF NOT IS_CAR_DEAD instructor_car
				
				//checking timer hasnt ran to 0
				GOSUB car_timer_0

				//checking car is stopped or not
				IF IS_CAR_STOPPED instructor_car
					GOSUB freeze_car_pos
					
					//position calculations 
					IF mission_selection = 3
						perfect_positionx = finish_rightx
						perfect_positiony = finish_righty
					ELSE
						perfect_positionx = finish_leftx
						perfect_positiony = finish_lefty
					ENDIF	

					GOSUB position_score_calcs				
					
					//heading - perfect heading is 90 for right / 270 for left
					GET_CAR_HEADING instructor_car instructor_car_heading
					instructor_car_heading_int =# instructor_car_heading
					
					IF instructor_car_heading_int = 0
						heading_score = 0
						GOTO done_heading_calcs_whip_and_terminate
					ENDIF 
					IF instructor_car_heading_int = 360
						heading_score = 0
						GOTO done_heading_calcs_whip_and_terminate
					ENDIF 
					
					IF mission_selection = 3
						IF instructor_car_heading_int = 90
							heading_score = 100
							GOTO done_heading_calcs_whip_and_terminate
						ENDIF 

						IF instructor_car_heading_int > 0
							IF instructor_car_heading_int < 85
								variableb =# instructor_car_heading_int
								variableb *= 1.16 //1.16 = 99(which is highest score possible) / 85 ( which is 85 - 0)
								heading_score =# variableb 
								GOTO done_heading_calcs_whip_and_terminate
							ENDIF
						ENDIF  

						IF instructor_car_heading_int > 84
							IF instructor_car_heading_int < 96
								heading_score = 100
							ENDIF
						ENDIF 
							  
						IF instructor_car_heading_int > 95
							IF instructor_car_heading_int < 360
								variablea = instructor_car_heading_int - 90
								variableb =# variablea
								variableb *= 1.16
								variablea =# variableb
							 	heading_score = 100 - variablea 
								GOTO done_heading_calcs_whip_and_terminate
							ENDIF
						ENDIF 
					
					ELSE
					
						IF instructor_car_heading_int = 270
							heading_score = 100
							GOTO done_heading_calcs_whip_and_terminate
						ENDIF 

						IF instructor_car_heading_int > 0
							IF instructor_car_heading_int < 265
								variableb =# instructor_car_heading_int
								variableb *= 0.37 //0.37 = 99(which is highest score possible) / 265 ( which is 265 - 0)
								heading_score =# variableb 
								GOTO done_heading_calcs_whip_and_terminate
							ENDIF
						ENDIF  

						IF instructor_car_heading_int > 264
							IF instructor_car_heading_int < 276
								heading_score = 100
							ENDIF
						ENDIF 
							  
						IF instructor_car_heading_int > 275
							IF instructor_car_heading_int < 360
								variablea = instructor_car_heading_int - 270
								variableb =# variablea
								variableb *= 1.16
								variablea =# variableb
							 	heading_score = 100 - variablea 
								GOTO done_heading_calcs_whip_and_terminate
							ENDIF
						ENDIF 
					ENDIF 
				    
					done_heading_calcs_whip_and_terminate:
					IF heading_score < 1
						heading_score = 0
					ENDIF 
					
					overall_score = position_score + heading_score				
					overall_score /= 2  
					IF position_score = 0
						overall_score = 0
					ENDIF	 

					//losing points for hitting cones 
					GOSUB damage_cones_calcs
													 
					//checking overall score is greater than 0 and clearing prints
					GOSUB checking_overall_score

					//checking overall score against the best score at present
					IF overall_score > f1_whiprightterminate_best_score  	
						f1_old_score = f1_whiprightterminate_best_score 
						f1_whiprightterminate_best_score = overall_score
						f1_print_top_scores_flag = 1
						GOSUB f1_medal_check
					ELSE
						f1_which_medal_displayed = 0	
					ENDIF 	
					
					//opening next level
					IF f1_which_missions_are_open_flag = 4
						IF overall_score > 69
							f1_print_top_scores_flag = 2
							f1_which_missions_are_open_flag = 5
							instructor_car_dead_flag = 2
							f1_last_played = 5
						ENDIF
					ENDIF


					//printing scores onscreen
					timera = 0
					whip_and_terminate_scores_loop:
						WAIT 0

						//changing camera position 
						GOSUB setting_up_camera			

						IF NOT f1_print_top_scores_flag = 2
							//checking player hasnt left car
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								instructor_car_dead_flag = 2
								GOTO after_scores_whip_and_terminate																		  
							ENDIF 									    
						ENDIF

						//displaying scores
						GOSUB display_head_pos_dam_text

						//checking if player has skipped watching the scores
						GOSUB skip_scores
						IF finished_watching_scores = 1
							GOTO after_scores_whip_and_terminate
						ENDIF
					GOTO whip_and_terminate_scores_loop
					
					//reseting for another try
					after_scores_whip_and_terminate:
					
					GOSUB mini_cleanup
					
					GOSUB deleting_cones
					 
					IF instructor_car_dead_flag = 2 
						CLEAR_PRINTS
						GOTO noticeboard_setup
					ELSE
						GOTO refresh_whip_and_terminate 
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
	GOTO whip_and_terminate_loop
ENDIF

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////Controlling a burst tyre////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF mission_selection = 5

	///////////////remember to unload cars at end of level/////////////

	refresh_pop_control:
	GOSUB start_initialise_stuff


	setup_pop_control:

	//////setting up variables for this specific challenge/////
	perfect_heading = 180.0
	car_timer = 6000
	GOSUB setting_up_variables


	//creating cars
	CREATE_CAR COPCARSF perfect_positionx perfect_positiony perfect_positionz instructor_car
	SET_CAR_HEADING instructor_car perfect_heading

	//getting coords to burst tyres 
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 2.0 38.0 -0.5 perfect_positionx perfect_positiony perfect_positionz
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car -2.0 40.0 -0.5 car_posx car_posy perfect_positionz

	//creating stinger
	CREATE_OBJECT temp_stinger2 -2050.8 -167.0 34.6 f1_stinger  
	SET_OBJECT_HEADING f1_stinger 90.0 
	
	//creating cones
	GOSUB creating_cones

	IF playback_flag = 0
			SET_FIXED_CAMERA_POSITION -2047.7 -243.3 37.0 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2049.7 -227.4 34.1 JUMP_CUT
			IF NOT IS_CAR_DEAD instructor_car 
				CREATE_CHAR_INSIDE_CAR instructor_car PEDTYPE_MISSION1 SFPD1 f1_test_crash_dummy
			ENDIF   
		RETURN
	ENDIF
	
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 0.0 104.0 0.0 finish_rightx finish_righty perfect_positionz  
	CLEAR_PRINTS

	PRINT_BIG DSTART 5000 4  
	
	PRINT_NOW ( DS1_30 ) 5000 4 // Drive to the end of the track, a tyre will blow out half way there.  
	GOSUB stop_initialise_stuff

	pop_control_loop:
	WAIT 0
	
		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_car_dead_flag = 2
			GOTO after_scores_pop_control																		  
		ENDIF 
		
		//checking car isnt dead
		GOSUB instructor_car_dead
		IF instructor_car_dead_flag = 1 
			GOTO mission_failed_dskool
		ENDIF
		
		IF car_started = 0
			IF NOT instructor_car_dead_flag = 2
				PRINT_BIG DSTART 5000 4  
				PRINT_NOW ( DS1_30 ) 5000 4 // Drive to the end of the track, a tyre will blow out half way there.~n~For extra control, release the accelerate button. 
			ENDIF
			GOSUB has_car_started	

		//player is going
		ELSE
			IF NOT IS_CAR_DEAD instructor_car
				
				//checking timer hasnt ran to 0
				GOSUB car_timer_0
				
				//bursting players tyres
				
				IF IS_CAR_IN_AREA_2D instructor_car perfect_positionx perfect_positiony car_posx car_posy FALSE 
					IF NOT IS_CAR_TYRE_BURST instructor_car REAR_RIGHT_WHEEL
						BURST_CAR_TYRE instructor_car REAR_RIGHT_WHEEL
					ENDIF
				ENDIF
		
				//checking car is stopped or not
				IF IS_CAR_STOPPED instructor_car
					GOSUB freeze_car_pos
				
					//CALCULATIONS FOR PLAYER SCORE

					//how close player is to perfect position 
					perfect_positionx = finish_rightx 
					perfect_positiony = finish_righty

					GET_CAR_COORDINATES instructor_car player_x player_y player_z  
					GET_DISTANCE_BETWEEN_COORDS_2D player_x player_y perfect_positionx perfect_positiony players_distance_from_perfectpos  
					IF players_distance_from_perfectpos > 1.0
						IF players_distance_from_perfectpos < 10.0 
								players_distance_from_perfectpos -= 1.0	 
								players_distance_from_perfectpos *= 11.00 //10.70 = 99(which is highest score possible) / 9.00 ( which is 10 - 1.0)
							variablea =# players_distance_from_perfectpos 
							position_score = 100 - variablea
						ELSE
							position_score = 0
						ENDIF
					ELSE
						position_score = 100
					ENDIF
					IF NOT IS_CAR_TYRE_BURST instructor_car REAR_RIGHT_WHEEL
						position_score = 0
					ENDIF	 

					//heading - perfect heading is 180
					GET_CAR_HEADING instructor_car instructor_car_heading
					instructor_car_heading_int =# instructor_car_heading
					
					IF instructor_car_heading_int = 0
						heading_score = 0
						GOTO done_heading_calcs_pop_control
					ENDIF 
					IF instructor_car_heading_int = 180
						heading_score = 100
						GOTO done_heading_calcs_pop_control
					ENDIF 
					IF instructor_car_heading_int = 360
						heading_score = 0
						GOTO done_heading_calcs_pop_control
					ENDIF 

					IF instructor_car_heading_int > 0
						IF instructor_car_heading_int < 175
							variableb =# instructor_car_heading_int
							variableb *= 0.56 //0.56 = 99(which is highest score possible) / 175 ( which is 175 - 0)
							heading_score =# variableb 
							GOTO done_heading_calcs_pop_control
						ENDIF
					ENDIF  

					IF instructor_car_heading_int > 174
						IF instructor_car_heading_int < 186
							heading_score = 100
						ENDIF
					ENDIF 
						  
					IF instructor_car_heading_int > 185
						IF instructor_car_heading_int < 360
							variablea = instructor_car_heading_int - 180
							variableb =# variablea
							variableb *= 0.56 	
							variablea =# variableb
						 	heading_score = 100 - variablea 
							GOTO done_heading_calcs_pop_control
						ENDIF
					ENDIF  

					done_heading_calcs_pop_control:
					IF heading_score < 1
						heading_score = 0
					ENDIF 
					
					overall_score = position_score + heading_score				
					overall_score /= 2  
					IF position_score = 0
						overall_score = 0
					ENDIF	 

					//losing points for hitting cones 
					GOSUB damage_cones_calcs
													 
					//checking overall score is greater than 0 and clearing prints
					GOSUB checking_overall_score

					//checking overall score against the best score at present
					IF overall_score > f1_popcontrol_best_score 	
						f1_old_score = f1_popcontrol_best_score 
						f1_popcontrol_best_score = overall_score
						f1_print_top_scores_flag = 1
						GOSUB f1_medal_check
					ELSE
						f1_which_medal_displayed = 0	
					ENDIF 	

					//opening next level
					IF f1_which_missions_are_open_flag = 5
						IF overall_score > 69
							f1_print_top_scores_flag = 2
							f1_which_missions_are_open_flag = 7
							instructor_car_dead_flag = 2
							f1_last_played = 7
						ENDIF
					ENDIF

					//printing scores onscreen
					timera = 0
					pop_control_scores_loop:
						WAIT 0

						//changing camera position 
						GOSUB setting_up_camera			

						IF NOT f1_print_top_scores_flag = 2
							//checking player hasnt left car
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								instructor_car_dead_flag = 2
								GOTO after_scores_pop_control																		  
							ENDIF 									    
						ENDIF
		
						//displaying scores
						GOSUB display_head_pos_dam_text

						//checking if player has skipped watching the scores
						GOSUB skip_scores
						IF finished_watching_scores = 1
							GOTO after_scores_pop_control
						ENDIF
					GOTO pop_control_scores_loop
					
					//reseting for another try
					after_scores_pop_control:
					GOSUB mini_cleanup
					
					DELETE_OBJECT f1_stinger 
					GOSUB deleting_cones

					IF instructor_car_dead_flag = 2 
						CLEAR_PRINTS
						GOTO noticeboard_setup
					ELSE
						GOTO refresh_pop_control 
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
		
	GOTO pop_control_loop 
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////Burn and lap.///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF mission_selection = 6
OR mission_selection = 7

	refresh_burn_and_lap:
	GOSUB start_initialise_stuff

	setup_burn_and_lap:

	//////setting up variables for this specific challenge/////
	perfect_heading = 180.0
	car_timer = 0
	GOSUB setting_up_variables

	//creating cars
	CREATE_CAR banshee perfect_positionx perfect_positiony perfect_positionz instructor_car
	SET_CAR_HEADING instructor_car perfect_heading

	//creating cones
	GOSUB creating_cones

	IF mission_selection = 6
		//end of first straight  
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car -4.0 50.0 0.0 area_check1ax area_check1ay player_z 
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 4.0 44.0 0.0 area_check1bx area_check1by player_z 
		//start of second straight
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 12.0 38.0.0 0.0 area_check2ax area_check2ay player_z 
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 4.0 44.0 0.0 area_check2bx area_check2by player_z 
		//end of second straight
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 4.0 2.0 0.0 area_check3ax area_check3ay player_z 
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 12.0 -4.0 0.0 area_check3bx area_check3by player_z 
		//end of lap
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 4.0 2.0 0.0 area_check4ax area_check4ay player_z 
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car -4.0 8.0 0.0 area_check4bx area_check4by player_z 

		CREATE_OBJECT ad_finish -2050.5 -135.5 34.35 f1_stinger  
		//SET_OBJECT_HEADING f1_stinger 90.0 
	ENDIF														   	 

	//moving car to other side if burning left and getting area check coords
	IF mission_selection = 7
		//end of first straight  
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 4.0 44.0 0.0 area_check1ax area_check1ay player_z 
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 12.0 50.0 0.0 area_check1bx area_check1by player_z 
		//start of second straight
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 4.0 44.0 0.0 area_check2ax area_check2ay player_z 
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car -4.0 38.0 0.0 area_check2bx area_check2by player_z 
		//end of second straight
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 4.0 2.0 0.0 area_check3ax area_check3ay player_z 
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car -4.0 -4.0 0.0 area_check3bx area_check3by player_z 
		//end of lap
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 4.0 2.0 0.0 area_check4ax area_check4ay player_z 
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 12.0 8.0 0.0 area_check4bx area_check4by player_z 
		//moving car
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 8.0 0.0 0.0 car_posx car_posy player_z
		
		CREATE_OBJECT ad_finish -2058.5 -135.5 34.35 f1_stinger  
		
		SET_CAR_COORDINATES instructor_car car_posx car_posy player_z 
		SET_CAR_HEADING instructor_car perfect_heading
	ENDIF

	IF playback_flag = 0
			SET_FIXED_CAMERA_POSITION -2054.6 -109.1 36.4 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2054.6 -122.0 34.2 JUMP_CUT
			IF NOT IS_CAR_DEAD instructor_car 
				CREATE_RANDOM_CHAR_AS_DRIVER instructor_car f1_test_crash_dummy
			ENDIF   
		RETURN
	ENDIF
	CLEAR_PRINTS
	
	PRINT_BIG DSTART 5000 4  
	
	PRINT_NOW ( DS1_29 ) 5000 4 // Do 5 laps in as quick a time as possible.~n~The target time is 27 seconds. 
	GOSUB stop_initialise_stuff

	burn_and_lap_loop:
	WAIT 0 
		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_car_dead_flag = 2
			GOTO after_scores_burn_lap																		  
		ENDIF 
		
		//checking car isnt dead
		GOSUB instructor_car_dead
		IF instructor_car_dead_flag = 1 
			GOTO mission_failed_dskool
		ENDIF
		
		//checking car has moved and player is pressing cross or square
		IF car_started = 0
			IF NOT instructor_car_dead_flag = 2
				PRINT_BIG DSTART 5000 4   
				PRINT_NOW ( DS1_29 ) 5000 4 // Do 5 laps in as quick a time as possible.~n~The target time is 27 seconds. 
			ENDIF
			GOSUB has_car_started	
		
		//player is going
		ELSE
			IF NOT IS_CAR_DEAD instructor_car
				
				//calculating to see if player has gone over the alloted time
				IF car_timer > 90000 //double the time allowed
					SET_PLAYER_CONTROL player1 OFF
					//SET_CAR_TEMP_ACTION instructor_car TEMPACT_HANDBRAKESTRAIGHT 5000
				ENDIF
				
				//lap counter
				//checking player goes round a lap
				IF variablea = 0 						
					IF IS_CAR_IN_AREA_2D instructor_car area_check1ax area_check1ay area_check1bx area_check1by FALSE
						variablea = 1
					ENDIF
				ENDIF	  
				
				IF variablea = 1
					IF IS_CAR_IN_AREA_2D instructor_car area_check2ax area_check2ay area_check2bx area_check2by FALSE
						variablea = 2
					ENDIF
				ENDIF
				
				IF variablea = 2
					IF IS_CAR_IN_AREA_2D instructor_car area_check3ax area_check3ay area_check3bx area_check3by FALSE
						variablea = 3
					ENDIF
				ENDIF
			
				IF variablea = 3 
					IF IS_CAR_IN_AREA_2D instructor_car area_check4ax area_check4ay area_check4bx area_check4by FALSE
						lap_counter ++
						variablea = 0
					ENDIF
				ENDIF
				CLEAR_PRINTS

				IF lap_counter < 4
					variabled = lap_counter + 1 
					PRINT_WITH_NUMBER_NOW ( DS1_73 ) variabled 5000 4 // Lap ~1~ of ~1~ 	  	
				ELSE
					PRINT_NOW ( DS1_74 ) 5000 4 // Final Lap	  	
				ENDIF

				//checking car is stopped or not
				IF IS_CAR_STOPPED instructor_car
				OR lap_counter = 5
					GOSUB freeze_car_pos
				
					//CALCULATIONS FOR PLAYER SCORE
								 
					//calculating overall time
					variablea = car_timer
					 
					overall_secs = variablea / 1000
					overall_millisecs = overall_secs * 1000 
					variablec = variablea - overall_millisecs
					overall_millisecs = variablec / 10 
					
					//calculating overall score
					time_score = 0

					IF lap_counter < 5
						time_score = 0
					ELSE
						IF variablea < 36000
							time_score = 100
						ENDIF

						IF variablea >= 36000
							IF variablea < 40000
								variablec = variablea - 35999
								variableb =# variablec 
								variableb *= 0.0075	    //(100 - 70) / (40000 - 35999)  
								variablea =# variableb
							 	time_score = 100 - variablea 
							ENDIF
						ENDIF

						IF variablea >= 40000
							IF variablea < 45000
								variablec = variablea - 39999
								variableb =# variablec 
								variableb *= 0.0138		//(70 - 0) / (45000 - 39999)  
								variablea =# variableb
							 	time_score = 70 - variablea 
							ENDIF
						ENDIF

						IF variablea >= 45000
							time_score = 0
						ENDIF	
					ENDIF
							 
					IF time_score < 1
						time_score = 0
					ENDIF
					IF time_score > 99
						time_score = 100
					ENDIF

					overall_score = time_score
						
					//losing points for hitting cones 
					GOSUB damage_cones_calcs
													 
					//checking overall score is greater than 0 and clearing prints
					GOSUB checking_overall_score

					//checking overall score against the best score at present
					IF overall_score > f1_burnlapright_best_score  	
						f1_old_score = f1_burnlapright_best_score 
						f1_burnlapright_best_score = overall_score
						f1_print_top_scores_flag = 1
						GOSUB f1_medal_check
					ELSE
						f1_which_medal_displayed = 0	
					ENDIF 	

					//opening next level
					IF f1_which_missions_are_open_flag = 7
						IF overall_score > 69
							f1_print_top_scores_flag = 2
							f1_which_missions_are_open_flag = 9 
							instructor_car_dead_flag = 2
							f1_last_played = 9
						ENDIF
					ENDIF

					//printing scores onscreen
					lap_counter = 6 //stopping the 5th rectangle being drawn 
					timera = 0
					burn_lap_scores_loop:
						WAIT 0

						//changing camera position 
						GOSUB setting_up_camera			

						IF NOT f1_print_top_scores_flag = 2
							//checking player hasnt left car
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								instructor_car_dead_flag = 2
								GOTO after_scores_burn_lap																		  
							ENDIF 									    
						ENDIF
						
						//displaying scores
						GOSUB display_head_pos_dam_text
						
						//checking if player has skipped watching the scores
						GOSUB skip_scores
						IF finished_watching_scores = 1
							GOTO after_scores_burn_lap
						ENDIF
					GOTO burn_lap_scores_loop

					//reseting for another try
					after_scores_burn_lap:
					GOSUB mini_cleanup
					 
					DELETE_OBJECT f1_stinger
					GOSUB deleting_cones

					IF instructor_car_dead_flag = 2 
						CLEAR_PRINTS
						GOTO noticeboard_setup
					ELSE
						GOTO refresh_burn_and_lap 
					ENDIF
				ENDIF
			ENDIF
		ENDIF  

	GOTO burn_and_lap_loop 
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////// Cone Coil////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF mission_selection = 8
OR mission_selection = 9

	refresh_cone_coil:
	GOSUB start_initialise_stuff
	
	setup_cone_coil:
	REQUEST_MODEL banshee

	WHILE NOT HAS_MODEL_LOADED banshee
		WAIT 0
	ENDWHILE
	
	//////setting up variables for this specific challenge/////
	perfect_heading = 180.0
	car_timer = 11000
	GOSUB setting_up_variables

	//creating cars
	CREATE_CAR banshee perfect_positionx perfect_positiony perfect_positionz instructor_car
	SET_CAR_HEADING instructor_car perfect_heading

	CREATE_OBJECT ad_roadmark1 -2050.0 -172.5 34.35 f1_stinger
	SET_OBJECT_HEADING f1_stinger 180.0 
	
	//creating cones
	GOSUB creating_cones

	IF playback_flag = 0
		SET_FIXED_CAMERA_POSITION -2042.1 -223.4 36.3 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2049.1 -210.0 34.8 JUMP_CUT
			IF NOT IS_CAR_DEAD instructor_car 
				CREATE_RANDOM_CHAR_AS_DRIVER instructor_car f1_test_crash_dummy
			ENDIF   
		RETURN
	ENDIF

	//setting up the checkpoint
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS trafficcones[15] 0.0 5.0 0.0 cone_coords_x cone_coords_y cone_coords_z  

	//setting up finishing point
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 0.0 0.0 0.0 finish_rightx finish_righty perfect_positionz  
	CLEAR_PRINTS

	PRINT_BIG DSTART 5000 4  
	
	PRINT_NOW ( DS1_36 ) 5000 4 // Weave through the cones quickly and then return to the start position. 
	GOSUB stop_initialise_stuff
	
	cone_coil_loop:
	WAIT 0 

		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_car_dead_flag = 2
			GOTO after_scores_cone_coil																		  
		ENDIF 

		//checking car isnt dead
		GOSUB instructor_car_dead
		IF instructor_car_dead_flag = 1 
			GOTO mission_failed_dskool
		ENDIF

		//checking car has moved and player is pressing cross or square
		IF car_started = 0
			IF NOT instructor_car_dead_flag = 2
				
				PRINT_BIG DSTART 5000 4  
				PRINT_NOW ( DS1_36 ) 5000 4 // Weave through the cones quickly and then return to the start position. 
			ENDIF
			GOSUB has_car_started	

		//player is going
		ELSE
			IF NOT IS_CAR_DEAD instructor_car
				
				//checking car havent left area
				IF NOT IS_CAR_IN_AREA_2D instructor_car -2042.0 -126.0 -2058.0 -220.0 FALSE
					SET_PLAYER_CONTROL player1 OFF
					//SET_CAR_TEMP_ACTION instructor_car TEMPACT_HANDBRAKESTRAIGHT 5000
				ENDIF
				
				//checking player has gone through marker
				IF lap_counter = 0
					IF LOCATE_CAR_3D instructor_car cone_coords_x cone_coords_y cone_coords_z 4.0 4.0 4.0 TRUE
						lap_counter = 1
					ENDIF 	
				ENDIF
				IF lap_counter = 1
					IF LOCATE_STOPPED_CAR_3D instructor_car perfect_positionx perfect_positiony perfect_positionz 4.0 4.0 4.0 TRUE 
						lap_counter = 2
					ENDIF 
				ENDIF			   

				//checking timer hasnt ran to 0
				GOSUB car_timer_0

				//checking car is stopped or not
				IF IS_CAR_STOPPED instructor_car
					GOSUB freeze_car_pos
					
					//position score calculations
					IF lap_counter > 0
						GOSUB position_score_calcs
					ELSE
						position_score = 0
					ENDIF
						

					//heading - perfect heading is 0
					GET_CAR_HEADING instructor_car instructor_car_heading
					instructor_car_heading_int =# instructor_car_heading
					
					IF instructor_car_heading_int = 0
						heading_score = 100
						GOTO done_heading_calcs_cone_coil
					ENDIF 
					IF instructor_car_heading_int = 360
						heading_score = 100
						GOTO done_heading_calcs_cone_coil
					ENDIF 

					IF instructor_car_heading_int > 0
						IF instructor_car_heading_int < 21
							heading_score = 100
						ENDIF
					ENDIF 
				
					IF instructor_car_heading_int > 179
						IF instructor_car_heading_int < 340
							variablea = instructor_car_heading_int - 180
							variableb =# variablea
							variableb *= 0.62 
					   		heading_score =# variableb 
							GOTO done_heading_calcs_cone_coil
						ENDIF
					ENDIF  

					IF instructor_car_heading_int > 339
						IF instructor_car_heading_int < 360
							heading_score = 100
						ENDIF
					ENDIF 
					
					IF instructor_car_heading_int > 20
						IF instructor_car_heading_int < 180
							variableb =# instructor_car_heading_int
							variableb *= 0.62 //0.62 = 99(which is highest score possible) / 60 ( which is 180 - 20)
							variablea =# variableb
						 	heading_score = 100 - variablea 
							GOTO done_heading_calcs_cone_coil
						ENDIF
					ENDIF  
					
					done_heading_calcs_cone_coil:
					IF heading_score < 1
						heading_score = 0
					ENDIF 
					

					overall_score = position_score + heading_score				
					overall_score /= 2  
					IF position_score = 0
						overall_score = 0
					ENDIF	 

					//losing points for hitting cones 
					GOSUB damage_cones_calcs

					//checking overall score is greater than 0 and clearing prints
					GOSUB checking_overall_score

					//checking overall score against the best score at present
					IF overall_score > f1_conecoilright_best_score  	
						f1_old_score = f1_conecoilright_best_score 
						f1_conecoilright_best_score = overall_score
						f1_print_top_scores_flag = 1
						GOSUB f1_medal_check
					ELSE
						f1_which_medal_displayed = 0	
					ENDIF 	

					//opening next level
					IF f1_which_missions_are_open_flag = 9
						IF overall_score > 69
							f1_print_top_scores_flag = 2
							f1_which_missions_are_open_flag = 10
							instructor_car_dead_flag = 2
							f1_last_played = 10
						ENDIF
					ENDIF
					
					//printing scores onscreen
					timera = 0
					cone_coil_scores_loop:
						WAIT 0

						//changing camera position 
						GOSUB setting_up_camera			

						IF NOT f1_print_top_scores_flag = 2
							//checking player hasnt left car
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								instructor_car_dead_flag = 2
								GOTO after_scores_cone_coil																		  
							ENDIF 									    
						ENDIF
					
						//displaying scores
						GOSUB display_head_pos_dam_text

						//checking if player has skipped watching the scores
						GOSUB skip_scores
						IF finished_watching_scores = 1
							GOTO after_scores_cone_coil
						ENDIF
					GOTO cone_coil_scores_loop

					//reseting for another try
					after_scores_cone_coil:
					GOSUB mini_cleanup
					 
					DELETE_OBJECT f1_stinger
					GOSUB deleting_cones

					IF instructor_car_dead_flag = 2 
						CLEAR_PRINTS
						GOTO noticeboard_setup
					ELSE
						GOTO refresh_cone_coil 
					ENDIF
				ENDIF
			ENDIF
		ENDIF  

	GOTO cone_coil_loop 
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////The "90" Sliding a car inbetween two cars to park it in a tiny space.//////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF mission_selection = 10 				   


	refresh_90:

	GOSUB start_initialise_stuff
	
	setup_90:
	

	///////////////remember to unload cars at end of level/////////////
	
	
	//////setting up variables for this specific challenge/////
	perfect_heading = 270.0
	car_timer = 6000
	GOSUB setting_up_variables
	
	//creating cars for the challenge
	car_posx = perfect_positionx + 6.0
	car_posy = perfect_positiony - 75.0 
	 					
	CREATE_CAR BANSHEE car_posx car_posy perfect_positionz dummy_car1	//closest to water
	SET_CAR_HEADING dummy_car1 perfect_heading
	FREEZE_CAR_POSITION dummy_car1 TRUE 

	car_posx = perfect_positionx - 6.0
	car_posy = perfect_positiony - 75.0 

	CREATE_CAR BANSHEE car_posx car_posy perfect_positionz dummy_car2	//furthest from water
	SET_CAR_HEADING dummy_car2 perfect_heading
	FREEZE_CAR_POSITION dummy_car2 TRUE 
	
	car_posx = perfect_positionx 
	car_posy = perfect_positiony 

	CREATE_CAR BANSHEE car_posx car_posy perfect_positionz instructor_car
	SET_CAR_HEADING instructor_car 180.0
	IF playback_flag = 0
		SET_FIXED_CAMERA_POSITION -2045.8 -210.5 35.3 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2053.5 -198.1 34.2 JUMP_CUT
			IF NOT IS_CAR_DEAD instructor_car 
				CREATE_RANDOM_CHAR_AS_DRIVER instructor_car f1_test_crash_dummy
			ENDIF   
		RETURN
	ENDIF
	CLEAR_PRINTS

	PRINT_BIG DSTART 5000 4  
	
	PRINT_NOW ( DS1_1 ) 5000 4 // To do a 90, slide the car sideways into the parking space within five seconds.
	GOSUB stop_initialise_stuff

	//starting challenge
	the_90_loop:
	WAIT 0
		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_car_dead_flag = 2
			GOTO after_scores_90																		  
		ENDIF 

		//checking car isnt dead
		GOSUB instructor_car_dead
		IF instructor_car_dead_flag = 1 
			GOTO mission_failed_dskool
		ENDIF

		//checking car has moved and player is pressing cross or square
		IF car_started = 0
			IF NOT instructor_car_dead_flag = 2
				PRINT_BIG DSTART 5000 4  
				PRINT_NOW ( DS1_1 ) 5000 4 // To do a 90, slide the car sideways into the parking space within five seconds.
			ENDIF
			GOSUB has_car_started
		//player is going
		ELSE
			IF NOT IS_CAR_DEAD instructor_car
				
				//checking timer hasnt ran to 0
				GOSUB car_timer_0

				//checking car is stopped or not
				IF IS_CAR_STOPPED instructor_car

					GOSUB freeze_car_pos
				
					//CALCULATIONS FOR PLAYER SCORE

					//how close player is to perfect position 
					perfect_positiony -= 75.0
					GET_CAR_COORDINATES instructor_car player_x player_y player_z  
					GET_DISTANCE_BETWEEN_COORDS_2D player_x player_y perfect_positionx perfect_positiony players_distance_from_perfectpos  
					IF players_distance_from_perfectpos > 0.5
						IF players_distance_from_perfectpos < 5.0 
								players_distance_from_perfectpos -= 0.5	 
								players_distance_from_perfectpos *= 22.0 // 22.0 = 99(which is highest score possible) / 4.5 ( which is 5 - 0.5)
							variablea =# players_distance_from_perfectpos 
							position_score = 100 - variablea
						ELSE
							position_score = 0
						ENDIF
					ELSE
						position_score = 100
					ENDIF 
					overall_score += position_score 


					//heading - perfect heading is 90 or 270
					GET_CAR_HEADING instructor_car instructor_car_heading
					instructor_car_heading_int =# instructor_car_heading
					
					IF instructor_car_heading_int = 0
						heading_score = 0
						GOTO done_heading_calcs_90
					ENDIF 
					IF instructor_car_heading_int = 90
						heading_score = 100
						GOTO done_heading_calcs_90
					ENDIF 
					IF instructor_car_heading_int = 180
						heading_score = 0
						GOTO done_heading_calcs_90
					ENDIF 
					IF instructor_car_heading_int = 270
						heading_score = 100
						GOTO done_heading_calcs_90
					ENDIF 

					IF instructor_car_heading_int > 0
						IF instructor_car_heading_int < 85
							variableb =# instructor_car_heading_int
							variableb *= 1.16 //1.16 = 99(which is highest score possible) / 85	( which is 85 - 0)
							heading_score =# variableb 
							GOTO done_heading_calcs_90
						ENDIF
					ENDIF  

					IF instructor_car_heading_int > 84
						IF instructor_car_heading_int < 96
							heading_score = 100
						ENDIF
					ENDIF 
						  
					IF instructor_car_heading_int > 95
						IF instructor_car_heading_int < 180
							variablea = instructor_car_heading_int - 90
							variableb =# variablea
							variableb *= 1.16 	
							variablea =# variableb
						 	heading_score = 100 - variablea 
							GOTO done_heading_calcs_90
						ENDIF
					ENDIF  

					IF instructor_car_heading_int > 180
						IF instructor_car_heading_int < 265
							variablea = instructor_car_heading_int - 180
							variableb =# variablea
							variableb *= 1.16 	
							heading_score =# variableb 
							GOTO done_heading_calcs_90
						ENDIF
					ENDIF  
				    
				    IF instructor_car_heading_int > 264
						IF instructor_car_heading_int < 276
							heading_score = 100
						ENDIF
					ENDIF 
					
					IF instructor_car_heading_int > 275
						IF instructor_car_heading_int < 360
							variablea = instructor_car_heading_int - 270
							variableb =# variablea
							variableb *= 1.16 	
							variablea =# variableb
						 	heading_score = 100 - variablea 
							GOTO done_heading_calcs_90
						ENDIF
					ENDIF  

					done_heading_calcs_90:
					IF heading_score < 1
						heading_score = 0
					ENDIF 
					
					overall_score = position_score + heading_score				
					overall_score /= 2  
					IF position_score = 0
						overall_score = 0
					ENDIF	 

					//how much damage done to players car
					GOSUB damage_score_calcs				

					//how much damage done to other cars
					//car 1
					other_car_damage = 200
					IF NOT IS_CAR_DEAD dummy_car1 
						GET_CAR_HEALTH dummy_car1 dummy_car1_health
						total_dummy_car1_health = 1000 - dummy_car1_health 
						total_dummy_car1_health /= 10
						IF total_dummy_car1_health > 100
							total_dummy_car1_health = 100
						ENDIF 
					ENDIF 
						
					//car 2
					IF NOT IS_CAR_DEAD dummy_car2 
						GET_CAR_HEALTH dummy_car2 dummy_car2_health
						total_dummy_car2_health = 1000 - dummy_car2_health 
						total_dummy_car2_health /= 10
						IF total_dummy_car2_health > 100
							total_dummy_car2_health = 100
						ENDIF 
					ENDIF	
				
					other_car_damage = total_dummy_car1_health + total_dummy_car2_health

					total_car_damage = player_car_damage + other_car_damage
					overall_score -= total_car_damage

					//checking overall score is greater than 0 and clearing prints
					GOSUB checking_overall_score

					//checking overall score against the best score at present
					IF overall_score > f1_the90_best_score     
						f1_old_score = f1_the90_best_score 
						f1_the90_best_score = overall_score
						f1_print_top_scores_flag = 1
						GOSUB f1_medal_check
					ELSE
						f1_which_medal_displayed = 0	
					ENDIF 	

					//opening next level
					IF f1_which_missions_are_open_flag = 10
						IF overall_score > 69
							f1_print_top_scores_flag = 2
							f1_which_missions_are_open_flag = 11
							instructor_car_dead_flag = 2
							f1_last_played = 11
						ENDIF
					ENDIF
						 
					//printing scores onscreen
					timera = 0
					the_90_scores_loop:
						WAIT 0

						//changing camera position 
						GOSUB setting_up_camera			

						IF NOT f1_print_top_scores_flag = 2
							//checking player hasnt left car
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								instructor_car_dead_flag = 2
								GOTO after_scores_90																		  
							ENDIF 									    
						ENDIF
						
						//displaying scores
						GOSUB display_head_pos_dam_text
					
						//checking if player has skipped watching the scores
						GOSUB skip_scores
						IF finished_watching_scores = 1
							GOTO after_scores_90
						ENDIF
					GOTO the_90_scores_loop 
					
					//reseting for another try
					after_scores_90:
					GOSUB mini_cleanup
					
					DELETE_CAR dummy_car1 
					DELETE_CAR dummy_car2 
					IF instructor_car_dead_flag = 2 
						CLEAR_PRINTS
						GOTO noticeboard_setup
					ELSE
						GOTO refresh_90 
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
			  	 
	GOTO the_90_loop 
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 								   
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////Wheelie Weave///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF mission_selection = 11

	refresh_wheelie_weave:

	GOSUB start_initialise_stuff

	setup_wheelie_weave:


	//////setting up variables for this specific challenge/////
	perfect_heading = 180.0
	car_timer = 0
	GOSUB setting_up_variables

	//creating instructors cars
	CREATE_CAR BANSHEE perfect_positionx perfect_positiony perfect_positionz instructor_car
	SET_CAR_HEADING instructor_car perfect_heading

	// creating jump
	car_posx = perfect_positionx + 1.0
	car_posy = perfect_positiony - 25.0
	perfect_heading = 90.0
	CREATE_OBJECT garys_luv_ramp car_posx car_posy perfect_positionz ramp2  
	SET_OBJECT_HEADING ramp2 270.0 
	//SET_OBJECT_COLLISION ramp2 TRUE
	//SET_OBJECT_DYNAMIC ramp2 TRUE
	
	//setting up the point the player will cross and start calculating car roll
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car -50.0 30.0 0.0 area_check1ax area_check1ay player_z 
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 50.0 200.0 0.0 area_check1bx area_check1by player_z 


	IF playback_flag = 0
		SET_FIXED_CAMERA_POSITION -2052.6 -138.4 34.9 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2044.2 -208.2 37.1 JUMP_CUT
			IF NOT IS_CAR_DEAD instructor_car 
				CREATE_RANDOM_CHAR_AS_DRIVER instructor_car f1_test_crash_dummy
			ENDIF   

		RETURN
	ENDIF
	CLEAR_PRINTS

	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 0.0 100.0 0.0 finish_rightx finish_righty perfect_positionz

	PRINT_BIG DSTART 5000 4  
	
	PRINT_NOW ( DS1_34 ) 5000 4 // Keep the car on two wheels until the end of the track. 
	GOSUB stop_initialise_stuff

	wheelie_weave_loop:
	WAIT 0

		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_car_dead_flag = 2
			GOTO after_scores_wheelie_weave																		  
		ENDIF
		
		//checking car isnt dead
		GOSUB instructor_car_dead
		IF instructor_car_dead_flag = 1 
			GOTO mission_failed_dskool
		ENDIF
		 
		//player is going
		IF car_started = 0
			IF NOT instructor_car_dead_flag = 2
				
				PRINT_BIG DSTART 5000 4  
				
				PRINT_NOW ( DS1_34 ) 5000 4 // Keep the car on two wheels until the end of the track. 
			ENDIF
			GOSUB has_car_started	
		ELSE
			IF NOT IS_CAR_DEAD instructor_car

				//drawing locate
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer finish_rightx finish_righty perfect_positionz 4.0 4.0 4.0 TRUE
				ENDIF
				
				//debug
				IF IS_CAR_IN_AREA_2D instructor_car area_check1ax area_check1ay area_check1bx area_check1by FALSE  
					GET_CAR_ROLL instructor_car variableb
					IF variableb < 4.0 
						IF variableb > -4.0 
							APPLY_BRAKES_TO_PLAYERS_CAR player1 ON
						ENDIF
					ENDIF
				ENDIF 

				//checking car is stopped or not
				IF IS_CAR_STOPPED instructor_car
					GOSUB freeze_car_pos
				
					//checking car is stopped or not
					IF IS_CAR_STOPPED instructor_car
						GOSUB freeze_car_pos
					
						//CALCULATIONS FOR PLAYER SCORE

						//how close player is to perfect position 
						perfect_positionx = finish_rightx 
						perfect_positiony = finish_righty

						GET_CAR_COORDINATES instructor_car player_x player_y player_z  
						GET_DISTANCE_BETWEEN_COORDS_2D player_x player_y perfect_positionx perfect_positiony players_distance_from_perfectpos  
						IF players_distance_from_perfectpos > 1.0
							IF players_distance_from_perfectpos < 10.0 
									players_distance_from_perfectpos -= 1.0	 
									players_distance_from_perfectpos *= 11.00 //10.70 = 99(which is highest score possible) / 9.00 ( which is 10 - 1.0)
								variablea =# players_distance_from_perfectpos 
								position_score = 100 - variablea
							ELSE
								position_score = 0
							ENDIF
						ELSE
							position_score = 100
						ENDIF 

  						//heading - perfect heading is 180
						GET_CAR_HEADING instructor_car instructor_car_heading
						instructor_car_heading_int =# instructor_car_heading
						
						IF instructor_car_heading_int = 0
							heading_score = 0
							GOTO done_heading_calcs_wheelie_weave
						ENDIF 
						IF instructor_car_heading_int = 180
							heading_score = 100
							GOTO done_heading_calcs_wheelie_weave
						ENDIF 
						IF instructor_car_heading_int = 360
							heading_score = 0
							GOTO done_heading_calcs_wheelie_weave
						ENDIF 

						IF instructor_car_heading_int > 0
							IF instructor_car_heading_int < 175
								variableb =# instructor_car_heading_int
								variableb *= 0.56 //0.56 = 99(which is highest score possible) / 175 ( which is 175 - 0)
								heading_score =# variableb 
								GOTO done_heading_calcs_wheelie_weave
							ENDIF
						ENDIF  

						IF instructor_car_heading_int > 174
							IF instructor_car_heading_int < 186
								heading_score = 100
							ENDIF
						ENDIF 
							  
						IF instructor_car_heading_int > 185
							IF instructor_car_heading_int < 360
								variablea = instructor_car_heading_int - 180
								variableb =# variablea
								variableb *= 0.56 	
								variablea =# variableb
							 	heading_score = 100 - variablea 
								GOTO done_heading_calcs_wheelie_weave
							ENDIF
						ENDIF  
					    

						done_heading_calcs_wheelie_weave:
						IF heading_score < 1
							heading_score = 0
						ENDIF 
						
						overall_score = position_score + heading_score				
						overall_score /= 2  
						IF position_score = 0
							overall_score = 0
						ENDIF	 

						//how much damage done to players car
						GOSUB damage_score_calcs
						total_car_damage = player_car_damage 
						overall_score -= total_car_damage


						//checking overall score is greater than 0 and clearing prints
						GOSUB checking_overall_score

						//checking overall score against the best score at present
						IF overall_score > f1_wheelieweave_best_score 	
							f1_old_score = f1_wheelieweave_best_score 
							f1_wheelieweave_best_score = overall_score
							f1_print_top_scores_flag = 1
							GOSUB f1_medal_check
						ELSE
							f1_which_medal_displayed = 0	
						ENDIF 	

						//opening next level
						IF f1_which_missions_are_open_flag = 11
							IF overall_score > 69
								
								f1_print_top_scores_flag = 2
								f1_which_missions_are_open_flag = 13
								instructor_car_dead_flag = 2
								f1_last_played = 13
							
							ENDIF
						ENDIF


						//printing scores onscreen
						timera = 0
						wheelie_weave_scores_loop:
							WAIT 0

							//changing camera position 
							GOSUB setting_up_camera			

							IF NOT f1_print_top_scores_flag = 2
								//checking player hasnt left car
								IF IS_BUTTON_PRESSED PAD1 TRIANGLE
									instructor_car_dead_flag = 2
									GOTO after_scores_wheelie_weave																		  
								ENDIF 									    
							ENDIF

							//displaying scores
							GOSUB display_head_pos_dam_text
						
							//checking if player has skipped watching the scores
							GOSUB skip_scores
							IF finished_watching_scores = 1
								GOTO after_scores_wheelie_weave
							ENDIF
						GOTO wheelie_weave_scores_loop

				//reseting for another try
						after_scores_wheelie_weave:

						GOSUB mini_cleanup
						
						DELETE_OBJECT ramp2

						IF instructor_car_dead_flag = 2 
							CLEAR_PRINTS
							GOTO noticeboard_setup
						ELSE
							GOTO refresh_wheelie_weave 
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	GOTO wheelie_weave_loop
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////The reverse spin and go.////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF mission_selection = 12
OR mission_selection = 13

	///////////////remember to unload cars at end of level/////////////
	refresh_spin_go:
	GOSUB start_initialise_stuff

	setup_spin_go:


	//////setting up variables for this specific challenge/////
	perfect_heading = 0.0
	car_timer = 6000
	GOSUB setting_up_variables


	//creating cars
	CREATE_CAR taxi perfect_positionx perfect_positiony perfect_positionz instructor_car
	SET_CAR_HEADING instructor_car perfect_heading
	SET_FREEBIES_IN_VEHICLE instructor_car FALSE 

	//creating cones
	GOSUB creating_cones

	IF playback_flag = 0
		SET_FIXED_CAMERA_POSITION -2051.9 -185.5 37.8 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2052.2 -152.9 34.3 JUMP_CUT
			IF NOT IS_CAR_DEAD instructor_car 
				CREATE_RANDOM_CHAR_AS_DRIVER instructor_car f1_test_crash_dummy
			ENDIF   
		RETURN
	ENDIF

	//setting up the perfect finish points
	IF mission_selection = 12
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 6.5 -40.0 0.0 finish_rightx finish_righty perfect_positionz  
	ELSE
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car -6.5 -40.0 0.0 finish_leftx finish_lefty perfect_positionz  
	ENDIF
	CLEAR_PRINTS

	PRINT_BIG DSREVE 5000 4 
	PRINT_NOW DS1_28 5000 4  
	GOSUB stop_initialise_stuff

	spin_and_go_loop:
	WAIT 0 
		
		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_car_dead_flag = 2
			GOTO after_scores_spin_go																		  
		ENDIF 
				
		//checking car isnt dead
		GOSUB instructor_car_dead
		IF instructor_car_dead_flag = 1 
			GOTO mission_failed_dskool
		ENDIF
		
		//checking car has moved and player is pressing cross or square
		IF car_started = 0
			IF NOT instructor_car_dead_flag = 2
				PRINT_BIG DSREVE 5000 4  
				PRINT_NOW ( DS1_28 ) 5000 4 // Use the front wheel drive car to reverse then quickly spin around 180 degrees.~n~Press the L2 button and R2 button together to look behind.
			ENDIF
			GOSUB has_car_started	
		//player is going
		ELSE
			IF NOT IS_CAR_DEAD instructor_car
				
				//checking timer hasnt ran to 0
				GOSUB car_timer_0

				//checking car is stopped or not
				IF IS_CAR_STOPPED instructor_car
					
					GOSUB freeze_car_pos

					//position score calculations
					IF mission_selection = 12
						perfect_positionx = finish_rightx
						perfect_positiony = finish_righty
					ELSE
						perfect_positionx = finish_leftx
						perfect_positiony = finish_lefty
					ENDIF	

					GOSUB position_score_calcs

					IF mission_selection = 12
						IF NOT IS_CAR_IN_AREA_2D instructor_car -2046.9 -161.4 -2041.2 -174.5 FALSE
							position_score = 0
						ENDIF
					ELSE
						IF NOT IS_CAR_IN_AREA_2D instructor_car -2054.2 -161.4 -2060.0 -174.5 FALSE
							position_score = 0
						ENDIF
					ENDIF		
									
					//heading - perfect heading is 180
					GET_CAR_HEADING instructor_car instructor_car_heading
					instructor_car_heading_int =# instructor_car_heading
					
					IF instructor_car_heading_int = 0
						heading_score = 0
						GOTO done_heading_calcs_spin_go
					ENDIF 
					IF instructor_car_heading_int = 180
						heading_score = 100
						GOTO done_heading_calcs_spin_go
					ENDIF 
					IF instructor_car_heading_int = 360
						heading_score = 0
						GOTO done_heading_calcs_spin_go
					ENDIF 

					IF instructor_car_heading_int > 0
						IF instructor_car_heading_int < 175
							variableb =# instructor_car_heading_int
							variableb *= 0.56 //0.56 = 99(which is highest score possible) / 175 ( which is 175 - 0)
							heading_score =# variableb 
							GOTO done_heading_calcs_spin_go
						ENDIF
					ENDIF  

					IF instructor_car_heading_int > 174
						IF instructor_car_heading_int < 186
							heading_score = 100
						ENDIF
					ENDIF 
						  
					IF instructor_car_heading_int > 185
						IF instructor_car_heading_int < 360
							variablea = instructor_car_heading_int - 180
							variableb =# variablea
							variableb *= 0.56 	
							variablea =# variableb
						 	heading_score = 100 - variablea 
							GOTO done_heading_calcs_spin_go
						ENDIF
					ENDIF  
				    
					
					done_heading_calcs_spin_go:
					IF heading_score < 1
						heading_score = 0
					ENDIF 
					
					overall_score = position_score + heading_score				
					overall_score /= 2  
					IF position_score = 0
						overall_score = 0
					ENDIF	 
				
					//losing points for hitting cones 
					GOSUB damage_cones_calcs
													 
					//checking overall score is greater than 0 and clearing prints
					GOSUB checking_overall_score
					
					//checking overall score against the best score at present
					IF overall_score > f1_spinrightgo_best_score 	
						f1_old_score = f1_spinrightgo_best_score 
						f1_spinrightgo_best_score = overall_score
						f1_print_top_scores_flag = 1
						GOSUB f1_medal_check
					ELSE
						f1_which_medal_displayed = 0	
					ENDIF 	

					//opening next level
					IF f1_which_missions_are_open_flag = 13
						IF overall_score > 69
							f1_print_top_scores_flag = 2
							f1_which_missions_are_open_flag = 14
							instructor_car_dead_flag = 2
							f1_last_played = 14
						ENDIF
					ENDIF

					
					//printing scores onscreen
					timera = 0
					spin_go_scores_loop:
						WAIT 0

						//changing camera position 
						GOSUB setting_up_camera			

						IF NOT f1_print_top_scores_flag = 2
							//checking player hasnt left car
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								instructor_car_dead_flag = 2
								GOTO after_scores_spin_go																		  
							ENDIF 									    
						ENDIF
					
						//displaying scores
						GOSUB display_head_pos_dam_text

						//checking if player has skipped watching the scores
						GOSUB skip_scores
						IF finished_watching_scores = 1
							GOTO after_scores_spin_go
						ENDIF
					GOTO spin_go_scores_loop
					
					//reseting for another try
					after_scores_spin_go:
					GOSUB mini_cleanup
					 
					GOSUB deleting_cones

					IF instructor_car_dead_flag = 2 
						CLEAR_PRINTS
						GOTO noticeboard_setup
					ELSE
						GOTO refresh_spin_go 
					ENDIF
				ENDIF
			ENDIF
		ENDIF  

	GOTO spin_and_go_loop 

ENDIF
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////P. I. T. Technique//////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF mission_selection = 14
	///////////////remember to unload cars at end of level/////////////
	refresh_pit_technique:
	GOSUB start_initialise_stuff

	setup_pit_technique:

	//////setting up variables for this specific challenge/////
	perfect_heading = 180.0
	car_timer = 0
	GOSUB setting_up_variables

	//creating instructors cars
	CREATE_CAR COPCARSF perfect_positionx perfect_positiony perfect_positionz instructor_car
	SET_CAR_HEADING instructor_car perfect_heading
	SET_FREEBIES_IN_VEHICLE instructor_car FALSE 

	// creating COPCARSF car
	car_posx = perfect_positionx
	car_posy = perfect_positiony - 10.0 
	 					
	CREATE_CAR COPCARSF car_posx car_posy perfect_positionz dummy_car1 
	SET_CAR_HEADING dummy_car1 perfect_heading
	CREATE_RANDOM_CHAR_AS_DRIVER dummy_car1 f1_test_crash_dummy2
	SET_CAR_CRUISE_SPEED dummy_car1 0.0

	CREATE_OBJECT ad_roadmark2 -2045.0 -177.5 34.35 f1_stinger
	SET_OBJECT_HEADING f1_stinger 180.0 

	CREATE_OBJECT ad_roadmark2 -2057.0 -177.5 34.35 ramp1

	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car -12.0 -4.0 0.0 area_check1ax area_check1ay player_z 
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 12.0 100.0 0.0 area_check1bx area_check1by player_z 

	IF playback_flag = 0
			SET_FIXED_CAMERA_POSITION -2047.0 -220.2 36.0 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2050.2 -193.9 32.5 JUMP_CUT
			IF NOT IS_CAR_DEAD instructor_car 
				CREATE_CHAR_INSIDE_CAR instructor_car PEDTYPE_MISSION1 SFPD1 f1_test_crash_dummy
			ENDIF   
		RETURN
	ENDIF
	CLEAR_PRINTS
		
	PRINT_BIG DSTART 5000 4  
	
	PRINT_NOW ( DS1_35 ) 5000 4 // Perform a P.I.T. Technique to spin the other car around with minimum damage.~n~Finish as close to the other car as possible. 
	GOSUB stop_initialise_stuff


	pit_technique_loop:
	WAIT 0
		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_car_dead_flag = 2
			GOTO after_scores_pit_technique																		  
		ENDIF 

		//checking car isnt dead
		GOSUB instructor_car_dead
		IF instructor_car_dead_flag = 1 
			GOTO mission_failed_dskool
		ENDIF

		/*
		IF NOT IS_CAR_DEAD dummy_car1
			IF NOT IS_RECORDING_GOING_ON_FOR_CAR dummy_car1 
				IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SHIFT
					START_RECORDING_CAR dummy_car1 3  
				ENDIF
			ELSE
				IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_M
					STOP_RECORDING_CAR dummy_car1
				ENDIF 
			ENDIF
		ENDIF
		*/

		//player is going
		IF car_started = 0
			IF NOT instructor_car_dead_flag = 2
	   			
				PRINT_BIG DSTART 5000 4  
				
				PRINT_NOW ( DS1_35 ) 5000 4 // Perform a P.I.T. Technique to spin the other car around with minimum damage.~n~Finish as close to the other car as possible. 
			ENDIF
			GOSUB has_car_started
		ELSE
			IF NOT IS_CAR_DEAD instructor_car
				IF NOT IS_CAR_DEAD dummy_car1 
					//checking cars havent left area
					IF NOT IS_CAR_IN_AREA_2D dummy_car1 area_check1ax area_check1ay area_check1bx area_check1by FALSE
						FREEZE_CAR_POSITION dummy_car1 TRUE
						SET_PLAYER_CONTROL player1 OFF
						//SET_CAR_TEMP_ACTION instructor_car TEMPACT_HANDBRAKESTRAIGHT 5000
					ENDIF
					
					IF NOT IS_CAR_IN_AREA_2D instructor_car area_check1ax area_check1ay area_check1bx area_check1by FALSE
						FREEZE_CAR_POSITION dummy_car1 TRUE
						SET_PLAYER_CONTROL player1 OFF
						//SET_CAR_TEMP_ACTION instructor_car TEMPACT_HANDBRAKESTRAIGHT 5000
					ENDIF
					
					//checking car hasnt spun around too much
					GET_CAR_HEADING dummy_car1 instructor_car_heading 

					IF instructor_car_heading > 340.0 
					OR instructor_car_heading < 20.0
						FREEZE_CAR_POSITION dummy_car1 TRUE
						SET_PLAYER_CONTROL player1 OFF
						//SET_CAR_TEMP_ACTION instructor_car TEMPACT_HANDBRAKESTRAIGHT 5000
					ENDIF
				 
					//checking car is stopped or not
					IF IS_CAR_STOPPED instructor_car
						FREEZE_CAR_POSITION dummy_car1 TRUE
						GOSUB freeze_car_pos
						
						//CALCULATIONS FOR PLAYER SCORE
						//how close player is to perfect position 
						GET_CAR_COORDINATES dummy_car1 perfect_positionx perfect_positiony perfect_positionz 
						GET_CAR_COORDINATES instructor_car player_x player_y player_z  
						GET_DISTANCE_BETWEEN_COORDS_2D player_x player_y perfect_positionx perfect_positiony players_distance_from_perfectpos  
						
						IF players_distance_from_perfectpos > 4.0
							IF players_distance_from_perfectpos < 11.0 
								players_distance_from_perfectpos -= 4.0	 
								players_distance_from_perfectpos *= 14.14 //14.14 = 99(which is highest score possible) / 7 ( which is 11 - 4)
								variablea =# players_distance_from_perfectpos 
								position_score = 100 - variablea
							ELSE
								position_score = 0
							ENDIF
						ELSE
							position_score = 100
						ENDIF 
						overall_score += position_score 
						
						//heading - perfect heading is 0
						GET_CAR_HEADING dummy_car1 instructor_car_heading
						instructor_car_heading_int =# instructor_car_heading
						
						IF instructor_car_heading_int = 0
							heading_score = 100
							GOTO done_heading_calcs_pit_technique
						ENDIF 
						IF instructor_car_heading_int = 360
							heading_score = 100
							GOTO done_heading_calcs_pit_technique
						ENDIF 

						IF instructor_car_heading_int > 0
							IF instructor_car_heading_int < 21
								heading_score = 100
							ENDIF
						ENDIF 
					
						IF instructor_car_heading_int > 179
							IF instructor_car_heading_int < 340
								variablea = instructor_car_heading_int - 180
								variableb =# variablea
								variableb *= 0.62 
						   		heading_score =# variableb 
								GOTO done_heading_calcs_pit_technique
							ENDIF
						ENDIF  

						IF instructor_car_heading_int > 339
							IF instructor_car_heading_int < 360
								heading_score = 100
							ENDIF
						ENDIF 
						
						IF instructor_car_heading_int > 20
							IF instructor_car_heading_int < 180
								variableb =# instructor_car_heading_int
								variableb *= 0.62 //0.62 = 99(which is highest score possible) / 60 ( which is 180 - 20)
								variablea =# variableb
							 	heading_score = 100 - variablea 
								GOTO done_heading_calcs_pit_technique
							ENDIF
						ENDIF  
							  
						done_heading_calcs_pit_technique:
						IF heading_score < 1
							heading_score = 0
						ENDIF 
						
						overall_score = position_score + heading_score				
						overall_score /= 2  
						IF position_score = 0
							overall_score = 0
						ENDIF	 

						//how much damage done to players car
						GOSUB damage_score_calcs
						IF player_car_damage < 3
							player_car_damage = 0
						ENDIF
						total_car_damage = player_car_damage 
						overall_score -= total_car_damage

						//checking overall score is greater than 0 and clearing prints
						GOSUB checking_overall_score

						//checking overall score against the best score at present
						IF overall_score > f1_pittechnique_best_score 	
							f1_old_score = f1_pittechnique_best_score 
							f1_pittechnique_best_score = overall_score
							f1_print_top_scores_flag = 1
							GOSUB f1_medal_check
						ELSE
							f1_which_medal_displayed = 0	
						ENDIF 	

						//opening next level
						IF f1_which_missions_are_open_flag = 14
							IF overall_score > 69
								f1_print_top_scores_flag = 2
								f1_which_missions_are_open_flag = 15
								instructor_car_dead_flag = 2
								f1_last_played = 15
							ENDIF
						ENDIF

						
						//printing scores onscreen
						timera = 0
						pit_technique_scores_loop:
							WAIT 0

							//changing camera position 
							GOSUB setting_up_camera			

							IF NOT f1_print_top_scores_flag = 2
								//checking player hasnt left car
								IF IS_BUTTON_PRESSED PAD1 TRIANGLE
									instructor_car_dead_flag = 2
									GOTO after_scores_pit_technique																		  
								ENDIF 									    
							ENDIF

							//displaying scores
							GOSUB display_head_pos_dam_text
						
							//checking if player has skipped watching the scores
							GOSUB skip_scores
							IF finished_watching_scores = 1
								GOTO after_scores_pit_technique
							ENDIF
						GOTO pit_technique_scores_loop
						//reseting for another try
						after_scores_pit_technique:

						GOSUB mini_cleanup

						DELETE_CAR dummy_car1 
						DELETE_OBJECT f1_stinger
						DELETE_OBJECT ramp1
						DELETE_CHAR f1_test_crash_dummy2

						IF instructor_car_dead_flag = 2 
							CLEAR_PRINTS
							GOTO noticeboard_setup
						ELSE
							GOTO refresh_pit_technique 
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

	GOTO pit_technique_loop
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////Alley oop///////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF mission_selection = 15
	
	refresh_swift_escape:
	GOSUB start_initialise_stuff

	setup_swift_escape:

	//////setting up variables for this specific challenge/////
	perfect_heading = 180.0
	car_timer = 6000
	GOSUB setting_up_variables

	//creating instructors cars
	car_posy = perfect_positiony + 20.0 
	CREATE_CAR BANSHEE perfect_positionx car_posy perfect_positionz instructor_car
	SET_CAR_HEADING instructor_car perfect_heading
	SET_CAR_CAN_BE_VISIBLY_DAMAGED instructor_car FALSE

	// creating jump
	car_posx = perfect_positionx
	car_posy = perfect_positiony - 55.0
	perfect_heading = 90.0
	CREATE_OBJECT ad_jump car_posx car_posy perfect_positionz ramp1  
	SET_OBJECT_HEADING ramp1 180.0 
	//SET_OBJECT_COLLISION ramp1 TRUE

	// creating cars to jump
	car_posy = perfect_positiony - 75.0 
	 					
	CREATE_CAR INFERNUS car_posx car_posy perfect_positionz dummy_car1	//closest to ramp
	SET_CAR_HEADING dummy_car1 perfect_heading
	FREEZE_CAR_POSITION dummy_car1 TRUE 

	car_posy = perfect_positiony - 80.0 

	CREATE_CAR INFERNUS car_posx car_posy perfect_positionz dummy_car2	//furthest from ramp
	SET_CAR_HEADING dummy_car2 perfect_heading
	FREEZE_CAR_POSITION dummy_car2 TRUE 


	IF playback_flag = 0
		SET_FIXED_CAMERA_POSITION -2061.2 -158.9 37.5 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2040.3 -197.0 35.1 JUMP_CUT
			IF NOT IS_CAR_DEAD instructor_car 
				CREATE_RANDOM_CHAR_AS_DRIVER instructor_car f1_test_crash_dummy
			ENDIF   
		RETURN
	ENDIF
	CLEAR_PRINTS

	PRINT_BIG DSTART 5000 4 
	PRINT_NOW DS1_33 5000 4 
						
	GOSUB stop_initialise_stuff

	swift_escape_loop:
	WAIT 0
		
		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_car_dead_flag = 2
			GOTO after_scores_swift_escape																		  
		ENDIF 

		//checking car isnt dead
		GOSUB instructor_car_dead
		IF instructor_car_dead_flag = 1 
			GOTO mission_failed_dskool
		ENDIF

		//player is going
		IF car_started = 0
			IF NOT instructor_car_dead_flag = 2
				
				PRINT_BIG DSTART 5000 4  
				PRINT_NOW DS1_33 5000 4  
												
			ENDIF
			GOSUB has_car_started	
		ELSE
			IF NOT IS_CAR_DEAD instructor_car
				
				//checking timer hasnt ran to 0
				GOSUB car_timer_0
			
				//checking player has gone through marker
				IF lap_counter = 0
					IF IS_CAR_IN_AREA_3D instructor_car -2047.0 -186.0 37.2 -2054.0 -190.0 39.0 FALSE   
						lap_counter = 1
					ENDIF 	
				ENDIF
				IF lap_counter = 1
					SET_CAR_HEALTH instructor_car 1000
				
					IF NOT IS_CAR_IN_AIR_PROPER instructor_car 
						IF IS_CAR_UPSIDEDOWN instructor_car
							SET_CAR_HEALTH instructor_car 700
							SET_CAR_CAN_BE_VISIBLY_DAMAGED instructor_car TRUE 
							lap_counter = 2
						ENDIF
					ENDIF
				ENDIF	 
				
				//checking if car went upside down
				IF NOT IS_CAR_IN_AIR_PROPER instructor_car 
					IF IS_CAR_UPSIDEDOWN instructor_car
						APPLY_BRAKES_TO_PLAYERS_CAR player1 ON
					ENDIF
				ENDIF
				
				//checking car hasnt left area
				IF NOT IS_CAR_IN_AREA_2D instructor_car -2042.0 -100.0 -2058.0 -280.0 FALSE	   ////IF CHANGING THE X, CHANGE THE CAR_AREA ABOVE
					SET_PLAYER_CONTROL player1 OFF
				ENDIF
			
				//checking car hasnt spun around too much
				GET_CAR_HEADING instructor_car instructor_car_heading 

				IF instructor_car_heading > 200.0 
				OR instructor_car_heading < 160.0
					variableb = 1.0
				ENDIF
				 
				//checking car has done a full barrel roll
				GET_CAR_ROLL instructor_car instructor_car_roll

				//check if car is going clockwise or anticlockwise
				IF instructor_car_roll_score = 0
					IF instructor_car_roll > 5.0 
						instructor_car_roll_plus_minus = 1
					ENDIF
					IF instructor_car_roll < -5.0 
						instructor_car_roll_plus_minus = 2
					ENDIF
				ENDIF
				
				//scores for a clockwise roll				
				IF instructor_car_roll_plus_minus = 1
					
					IF instructor_car_roll_score = 0 
						IF instructor_car_roll > 90.0
							instructor_car_roll_score = 1
						ENDIF
					ENDIF	
					IF instructor_car_roll_score = 1
						IF instructor_car_roll > 170.0
							instructor_car_roll_score = 2
						ENDIF
					ENDIF
					IF instructor_car_roll_score = 2
						IF instructor_car_roll < 0.0
							IF instructor_car_roll > -170.0
								instructor_car_roll_score = 3
							ENDIF
						ENDIF
					ENDIF
					IF instructor_car_roll_score = 3
						IF instructor_car_roll < 0.0
							IF instructor_car_roll > -90.0
								instructor_car_roll_score = 4
							ENDIF
						ENDIF
						IF instructor_car_roll > 10.0 
							instructor_car_roll_score = 0
						ENDIF 
					ENDIF
					IF instructor_car_roll_score = 4
						IF instructor_car_roll < 0.0
							IF instructor_car_roll > -5.0
								instructor_car_roll_score = 5
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				//scores for a anti-clockwise roll				
				IF instructor_car_roll_plus_minus = 2
					
					IF instructor_car_roll_score = 0 
						IF instructor_car_roll < -90.0
							instructor_car_roll_score = 1
						ENDIF
					ENDIF	
					IF instructor_car_roll_score = 1
						IF instructor_car_roll < -170.0
							instructor_car_roll_score = 2
						ENDIF
					ENDIF
					IF instructor_car_roll_score = 2
					    IF instructor_car_roll > 0.0
							IF instructor_car_roll < 170.0
								instructor_car_roll_score = 3
							ENDIF
						ENDIF
					ENDIF
					IF instructor_car_roll_score = 3
					    IF instructor_car_roll > 0.0
							IF instructor_car_roll < 90.0
								instructor_car_roll_score = 4
							ENDIF
						ENDIF
						IF instructor_car_roll < -10.0 
							instructor_car_roll_score = 0
						ENDIF 
					ENDIF
					IF instructor_car_roll_score = 4
					    IF instructor_car_roll > 0.0
							IF instructor_car_roll < 5.0
								instructor_car_roll_score = 5
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				//checking car is stopped or not
				IF IS_CAR_STOPPED instructor_car
					GOSUB freeze_car_pos
				
					//CALCULATIONS FOR PLAYER SCORE//

					//checking car has done a full roll
					IF instructor_car_roll_score = 5
						position_score = 100
					ENDIF	 
					IF instructor_car_roll_score = 4
						position_score = 80
					ENDIF	 
					IF instructor_car_roll_score = 3
						position_score = 60
					ENDIF	 
					IF instructor_car_roll_score = 2
						position_score = 40
					ENDIF	 
					IF instructor_car_roll_score = 1
						position_score = 20
					ENDIF	 
					IF instructor_car_roll_score = 0
						position_score = 0
					ENDIF	
					
					//checking player has gone through marker
					IF lap_counter = 0
						position_score = 0
					ENDIF
						 
					//checking if car went upside down
					IF IS_CAR_UPSIDEDOWN instructor_car
						position_score = 0
					ENDIF	 
					
					//checking car hasnt left area
					IF NOT IS_CAR_IN_AREA_2D instructor_car -2042.0 -215.0 -2058.0 -280.0 FALSE	    ////IF CHANGING THE X, CHANGE THE CAR_AREA ABOVE
						position_score = 0
					ENDIF	 
			
					//heading - perfect heading is 180
					GET_CAR_HEADING instructor_car instructor_car_heading
					instructor_car_heading_int =# instructor_car_heading
					
					IF instructor_car_heading_int = 0
						heading_score = 0
						GOTO done_heading_calcs_swift_escape
					ENDIF 
					IF instructor_car_heading_int = 180
						heading_score = 100
						GOTO done_heading_calcs_swift_escape
					ENDIF 
					IF instructor_car_heading_int = 360
						heading_score = 0
						GOTO done_heading_calcs_swift_escape
					ENDIF 

					IF instructor_car_heading_int > 0
						IF instructor_car_heading_int < 175
							variableb =# instructor_car_heading_int
							variableb *= 0.56 //0.56 = 99(which is highest score possible) / 175 ( which is 175 - 0)
							heading_score =# variableb 
							GOTO done_heading_calcs_swift_escape
						ENDIF
					ENDIF  

					IF instructor_car_heading_int > 174
						IF instructor_car_heading_int < 186
							heading_score = 100
						ENDIF
					ENDIF 
						  
					IF instructor_car_heading_int > 185
						IF instructor_car_heading_int < 360
							variablea = instructor_car_heading_int - 180
							variableb =# variablea
							variableb *= 0.56 	
							variablea =# variableb
						 	heading_score = 100 - variablea 
							GOTO done_heading_calcs_swift_escape
						ENDIF
					ENDIF  
					
					done_heading_calcs_swift_escape:
					IF heading_score < 1
						heading_score = 0
					ENDIF 
					
					overall_score = position_score + heading_score				
					overall_score /= 2  
					IF position_score = 0
						overall_score = 0
					ENDIF	 

					//how much damage done to players car
					GOSUB damage_score_calcs
					IF player_car_damage < 2
						player_car_damage = 0
					ENDIF
					total_car_damage = player_car_damage 
					overall_score -= total_car_damage
					

					//checking overall score is greater than 0 and clearing prints
					GOSUB checking_overall_score

					//checking overall score against the best score at present
					IF overall_score > f1_alleyoop_best_score 
						f1_old_score = f1_alleyoop_best_score 
						f1_alleyoop_best_score = overall_score
						f1_print_top_scores_flag = 1
						GOSUB f1_medal_check
					ELSE
						f1_which_medal_displayed = 0	
					ENDIF 	

					//opening next level
					IF f1_which_missions_are_open_flag = 15
						IF overall_score > 69
							f1_print_top_scores_flag = 2
							f1_which_missions_are_open_flag = 16
							instructor_car_dead_flag = 2
							f1_last_played = 16
						ENDIF
					ENDIF

					//printing scores onscreen
					timera = 0
					swift_escape_scores_loop:
						WAIT 0

						//changing camera position 
						GOSUB setting_up_camera			

						IF NOT f1_print_top_scores_flag = 2
							//checking player hasnt left car
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								instructor_car_dead_flag = 2
								GOTO after_scores_swift_escape																		  
							ENDIF 									    
						ENDIF
		
						//displaying scores
						GOSUB display_head_pos_dam_text

						//checking if player has skipped watching the scores
						GOSUB skip_scores
						IF finished_watching_scores = 1
							GOTO after_scores_swift_escape
						ENDIF
					GOTO swift_escape_scores_loop

			//reseting for another try
					after_scores_swift_escape:

					GOSUB mini_cleanup
					
					DELETE_CAR dummy_car1 
					DELETE_CAR dummy_car2 
					DELETE_OBJECT ramp1

					IF instructor_car_dead_flag = 2 
						CLEAR_PRINTS
						GOTO noticeboard_setup
					ELSE
						GOTO refresh_swift_escape 
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
	GOTO swift_escape_loop
ENDIF
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////City Slicking///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF mission_selection = 16
	
	refresh_city_slicking:
	GOSUB start_initialise_stuff
	
	setup_city_slicking:

	//////setting up variables for this specific challenge/////
	perfect_heading = 180.0
	car_timer = 0
	GOSUB setting_up_variables
	//creating car position 
	perfect_positionx = -2046.7 
	perfect_positiony = -90.2 
	perfect_positionz = 33.9

	//finishing point 
	cone_coords_x = -1724.3  
	cone_coords_y = 1294.0 
	cone_coords_z = 6.0


	/*
   	/////////////////DEBUG FOR WANTING TO TEST SOMETHING IN THE CITY SLICKING RACE USE THESE COORDS
	cone_coords_x = -2028.0  
	cone_coords_y = 33.9
	cone_coords_z = 33.9
	*/

	//creating cars
	CREATE_CAR supergt perfect_positionx perfect_positiony perfect_positionz instructor_car
	SET_CAR_HEADING instructor_car 0.0
	SET_CAR_WATERTIGHT instructor_car TRUE

	IF playback_flag = 0
		SET_FIXED_CAMERA_POSITION -2017.3 -45.0 34.41 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2039.0 -100.3 37.4 JUMP_CUT
			IF NOT IS_CAR_DEAD instructor_car 
				CREATE_RANDOM_CHAR_AS_DRIVER instructor_car f1_test_crash_dummy
			ENDIF   
		RETURN
	ENDIF
	CLEAR_PRINTS
	
	SET_CAR_DENSITY_MULTIPLIER 0.5 
	
	LOAD_SCENE perfect_positionx perfect_positiony perfect_positionz 
	
	ADD_BLIP_FOR_COORD cone_coords_x cone_coords_y cone_coords_z d1_blip_flag

	//create_checkpoint
	CREATE_CHECKPOINT CHECKPOINT_TUBE cone_coords_x cone_coords_y cone_coords_z perfect_positionx perfect_positiony perfect_positionz 5.0 f1_checkpoint 

	PRINT_BIG DSTART 5000 4  
	
	PRINT_NOW ( DS1_31 ) 5000 4 // Drive to the other side of the city and back without damaging the car.~n~The target time is 120 seconds. 
	GOSUB stop_initialise_stuff

	city_slicking_loop:
	WAIT 0

		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_car_dead_flag = 2
			GOTO after_scores_city_slicking																		  
		ENDIF 

		//checking car isnt dead
		GOSUB instructor_car_dead
		IF instructor_car_dead_flag = 1 
			GOTO mission_failed_dskool
		ENDIF
		IF instructor_car_dead_flag = 2
		   GOTO after_scores_city_slicking
		ENDIF
		//player is going
		IF car_started = 0
			IF NOT instructor_car_dead_flag = 2
	   			
				PRINT_BIG DSTART 5000 4  
				PRINT_NOW ( DS1_31 ) 5000 4 // Drive to the other side of the city and back without damaging the car.~n~The target time is 120 seconds. 
			ENDIF
			GOSUB has_car_started	
		ELSE
			IF NOT IS_CAR_DEAD instructor_car

				//calculating to see if player has gone over the alloted time
				IF car_timer > 260000 //double the time allowed
					SET_PLAYER_CONTROL player1 OFF
				ENDIF
				
				//waiting for player to reach first lap
				IF lap_counter = 0
					IF LOCATE_CAR_3D instructor_car cone_coords_x cone_coords_y cone_coords_z 4.0 4.0 4.0 FALSE
	  					REMOVE_BLIP d1_blip_flag 
						ADD_BLIP_FOR_COORD perfect_positionx perfect_positiony perfect_positionz d1_blip_flag
	  					DELETE_CHECKPOINT f1_checkpoint 
						CREATE_CHECKPOINT CHECKPOINT_ENDTUBE perfect_positionx perfect_positiony perfect_positionz perfect_positionx perfect_positiony perfect_positionz 5.0 f1_checkpoint 
						lap_counter = 1
					ENDIF 	
				ENDIF
				IF lap_counter = 1
					IF LOCATE_CAR_3D instructor_car perfect_positionx perfect_positiony perfect_positionz 4.0 4.0 4.0 FALSE 
						REMOVE_BLIP d1_blip_flag
						lap_counter = 2
					ENDIF 
				ENDIF			   

				CLEAR_PRINTS	  	

				//checking car is stopped or not
				IF IS_CAR_STOPPED instructor_car
				OR lap_counter = 2
				OR IS_CAR_IN_WATER instructor_car
				OR car_timer >= 140000 
					GOSUB freeze_car_pos
					DELETE_CHECKPOINT f1_checkpoint
					//CALCULATIONS FOR PLAYER SCORE
					
					//calculating overall time
					variablea = car_timer
					 										  
					overall_secs = variablea / 1000
					overall_millisecs = overall_secs * 1000 
					variablec = variablea - overall_millisecs
					overall_millisecs = variablec / 10 
					
					//calculating overall score
					time_score = 0


					IF lap_counter < 2						 
						time_score = 0
					ELSE
						IF variablea < 100000
							time_score = 100
						ENDIF

						IF variablea >= 100000
							IF variablea < 120000
								variablec = variablea - 99999
								variableb =# variablec 
								variableb *= 0.0015	    //(100 - 70) / (120000 - 99999)
								variablea =# variableb
							 	time_score = 100 - variablea 
							ENDIF
						ENDIF

						IF variablea >= 120000
							IF variablea < 140000
								variablec = variablea - 119999
								variableb =# variablec 
								variableb *= 0.0035		//(70 - 0) / (140000 - 119999)
								variablea =# variableb
							 	time_score = 70 - variablea 
							ENDIF
						ENDIF

						IF variablea >= 140000
							time_score = 0
						ENDIF	

					ENDIF

					IF time_score < 1						   
						time_score = 0
					ENDIF
					IF time_score > 99
						time_score = 100
					ENDIF

					overall_score = time_score

					//how much damage done to players car
					GOSUB damage_score_calcs
					total_car_damage = player_car_damage 
					overall_score -= total_car_damage
													 
					//checking overall score is greater than 0 and clearing prints
					GOSUB checking_overall_score

					//checking overall score against the best score at present
					IF overall_score > f1_cityslicking_best_score 	
						f1_old_score = f1_cityslicking_best_score 
						f1_cityslicking_best_score = overall_score
						f1_print_top_scores_flag = 1
						GOSUB f1_medal_check
					ELSE
						f1_which_medal_displayed = 0	
					ENDIF 	

					//printing scores onscreen
					lap_counter = 5
					timera = 0
					city_slicking_scores_loop:
						WAIT 0

						//changing camera position 
						GOSUB setting_up_camera			
					
						IF NOT f1_print_top_scores_flag = 2
							//checking player hasnt left car
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								instructor_car_dead_flag = 2
								GOTO after_scores_city_slicking																		  
							ENDIF 									    
						ENDIF

						//displaying scores
						GOSUB display_head_pos_dam_text
					
						//checking if player has skipped watching the scores
						GOSUB skip_scores
						IF finished_watching_scores = 1
							GOTO after_scores_city_slicking
						ENDIF
					GOTO city_slicking_scores_loop
					
					//reseting for another try
					after_scores_city_slicking:


					//opening next level
					IF driving_test_passed = 0
						IF f1_which_missions_are_open_flag = 16
							IF overall_score > 69

								DO_FADE 500 FADE_OUT
								WHILE GET_FADING_STATUS
									WAIT 0
								ENDWHILE
							
								SET_AREA_VISIBLE 3
								SET_CHAR_AREA_VISIBLE scplayer 3 
								CLEAR_EXTRA_COLOURS TRUE
								REQUEST_COLLISION -2031.1 -118.2 
								LOAD_SCENE -2031.1 -118.2 1034.2

								CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
								SET_CHAR_COORDINATES scplayer -2029.7 -115.5 1034.2
								SET_CHAR_HEADING scplayer 0.0
								CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
								
								DELETE_CAR instructor_car
								CLEAR_AREA -2051.0 -174.0 34.0 300.0 TRUE
								CLEAR_ONSCREEN_TIMER car_timer
								FREEZE_ONSCREEN_TIMER FALSE
								
								SET_CAMERA_BEHIND_PLAYER
								RESTORE_CAMERA_JUMPCUT
								DO_FADE 500 FADE_IN
								WHILE GET_FADING_STATUS
									WAIT 0
								ENDWHILE
								GOTO mission_passed_dskool
							ENDIF
						ENDIF
					ENDIF

					GOSUB mini_cleanup
					REMOVE_BLIP d1_blip_flag
					DELETE_CHECKPOINT f1_checkpoint
					SET_CAR_DENSITY_MULTIPLIER 1.0

					IF instructor_car_dead_flag = 2 
						CLEAR_PRINTS
						GOTO noticeboard_setup
					ELSE
						GOTO refresh_city_slicking 
					ENDIF
				ENDIF
			ENDIF
		ENDIF  

	GOTO city_slicking_loop 
ENDIF
				
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// Mission failed
mission_failed_dskool:
CLEAR_PRINTS
IF instructor_car_dead_flag = 1
	PRINT_BIG M_FAIL 5000 1
ENDIF

RETURN

// mission passed
mission_passed_dskool:
	IF driving_test_passed = 0
		REMOVE_BLIP dschool_contact_blip
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT testsX testsY testsZ RADAR_SPRITE_SCHOOL dschool_contact_blip
		CHANGE_BLIP_DISPLAY dschool_contact_blip BLIP_ONLY
		
		SWITCH_CAR_GENERATOR f1_chunky 101
		SWITCH_CAR_GENERATOR f1_dbp 101
	
		CLEAR_PRINTS
		PRINT_WITH_NUMBER_BIG ( DS_PASS ) 15 5000 1 //"Mission Passed!"
		INCREMENT_FLOAT_STAT DRIVING_SKILL 40.0

		PLAY_MISSION_PASSED_TUNE 2
		CLEAR_WANTED_LEVEL PLAYER1
		//REMOVE_BLIP trace_contact_blip
		//ADD_SPRITE_BLIP_FOR_CONTACT_POINT traceX traceY traceZ trace_blip_icon trace_contact_blip
		REGISTER_MISSION_PASSED FAR_1
		PLAYER_MADE_PROGRESS 1
		driving_test_passed = 1
	ENDIF
RETURN

// mission cleanup
mission_cleanup_dskool:
//SET_CAMERA_BEHIND_PLAYER 
IF f1_bronze_award = 0
	IF f1_the360_best_score >= 70
		IF f1_the180_best_score >= 70
			IF f1_whiprightterminate_best_score >= 70
				IF f1_popcontrol_best_score >= 70
					IF f1_burnlapright_best_score >= 70
						IF f1_conecoilright_best_score >= 70
							IF f1_the90_best_score >= 70 
								IF f1_wheelieweave_best_score >= 70
									IF f1_spinrightgo_best_score >= 70
										IF f1_pittechnique_best_score >= 70
											IF f1_alleyoop_best_score >= 70 
												IF f1_cityslicking_best_score >= 70  
													SWITCH_CAR_GENERATOR f1_bronze_car_gen 101
													f1_bronze_award = 1
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
	ENDIF
ENDIF		
	 
IF f1_silver_award = 0
	IF f1_the360_best_score >= 85
		IF f1_the180_best_score >= 85
			IF f1_whiprightterminate_best_score >= 85
				IF f1_popcontrol_best_score >= 85
					IF f1_burnlapright_best_score >= 85
						IF f1_conecoilright_best_score >= 85
							IF f1_the90_best_score >= 85 
								IF f1_wheelieweave_best_score >= 85
									IF f1_spinrightgo_best_score >= 85
										IF f1_pittechnique_best_score >= 85
											IF f1_alleyoop_best_score >= 85 
												IF f1_cityslicking_best_score >= 85  
													SWITCH_CAR_GENERATOR f1_silver_car_gen 101
													f1_silver_award = 1
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
	ENDIF
ENDIF

IF f1_gold_award = 0
	IF f1_the360_best_score = 100
		IF f1_the180_best_score = 100
			IF f1_whiprightterminate_best_score = 100
				IF f1_popcontrol_best_score = 100
					IF f1_burnlapright_best_score = 100
						IF f1_conecoilright_best_score = 100
							IF f1_the90_best_score = 100 
								IF f1_wheelieweave_best_score = 100
									IF f1_spinrightgo_best_score = 100
										IF f1_pittechnique_best_score = 100
											IF f1_alleyoop_best_score = 100 
												IF f1_cityslicking_best_score = 100  
													SWITCH_CAR_GENERATOR f1_gold_car_gen 101
													f1_gold_award = 1
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
	ENDIF
ENDIF


//IF IS_STRING_EMPTY $shop_name
//	RESTORE_CAMERA_JUMPCUT
//ENDIF
//SET_PLAYER_CONTROL player1 on
CLEAR_ONSCREEN_TIMER car_timer
USE_TEXT_COMMANDS FALSE
RELEASE_WEATHER
MARK_MODEL_AS_NO_LONGER_NEEDED banshee
MARK_MODEL_AS_NO_LONGER_NEEDED taxi
MARK_MODEL_AS_NO_LONGER_NEEDED trafficcone
MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH
MARK_MODEL_AS_NO_LONGER_NEEDED blistac
MARK_MODEL_AS_NO_LONGER_NEEDED infernus
MARK_MODEL_AS_NO_LONGER_NEEDED supergt
MARK_MODEL_AS_NO_LONGER_NEEDED COPCARSF
MARK_MODEL_AS_NO_LONGER_NEEDED SFPD1
MARK_MODEL_AS_NO_LONGER_NEEDED temp_stinger2
MARK_MODEL_AS_NO_LONGER_NEEDED garys_luv_ramp
MARK_MODEL_AS_NO_LONGER_NEEDED ad_jump
MARK_MODEL_AS_NO_LONGER_NEEDED ad_roadmark1
MARK_MODEL_AS_NO_LONGER_NEEDED ad_roadmark2
MARK_MODEL_AS_NO_LONGER_NEEDED ad_finish 
SWITCH_ROADS_BACK_TO_ORIGINAL -2015.37 -76.08 10.0 -2078.3 -66.75 50.0 
ALLOW_FIXED_CAMERA_COLLISION FALSE
REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DRIVING_AWARD_TRACK_STOP
ACTIVATE_GARAGE bodLAwN
ACTIVATE_GARAGE modlast
ACTIVATE_GARAGE mdsSFSe
IF japcar_mod_garage_open = 1
	ACTIVATE_GARAGE mds1SFS
	ACTIVATE_GARAGE vEcmod 
ENDIF
SET_NO_RESPRAYS OFF
IF NOT IS_CHAR_DEAD scplayer 
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
ENDIF
SHUT_ALL_CHARS_UP FALSE

REMOVE_TEXTURE_DICTIONARY  
SET_CAR_DENSITY_MULTIPLIER 1.0
DISPLAY_HUD TRUE
DISPLAY_RADAR TRUE
REMOVE_BLIP d1_blip_flag
DELETE_CHECKPOINT f1_checkpoint
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

f1_drawing_tv_screen://////////////////////////////////////////////////////////////////////

	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 8 320.0 225.0 612.0 438.0 0 0 0 f1_alpha

	//FADE_OUT
	IF f1_fade_flag = 1 
		IF f1_alpha < 255
			f1_alpha = f1_alpha + 5 
			IF f1_alpha > 255
				f1_alpha = 255
			ENDIF
		ELSE
			f1_fade_flag = 2
		ENDIF
	ENDIF

	//FADE_IN
	IF f1_fade_flag = 3
		IF f1_alpha > 0
			f1_alpha = f1_alpha - 5 
			IF f1_alpha < 0
				f1_alpha = 0
			ENDIF
		ELSE
			f1_fade_flag = 0
		ENDIF
	ENDIF
				

	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 8 320.0 0.0 640.0 197.0 0 0 0 255

	//which test
	GOSUB test_name_text 
	SET_TEXT_COLOUR 255 255 255 255

	IF mission_selection = 1 
		DISPLAY_TEXT 322.0 31.0 DS_P //The 360 
		f1_which_score_displayed = f1_the360_best_score 
		GOSUB f1_drawing_medal 
	ENDIF
	IF mission_selection = 2 
		DISPLAY_TEXT 322.0 31.0 DS_O //The 180 
		f1_which_score_displayed = f1_the180_best_score  
		GOSUB f1_drawing_medal 
	ENDIF
	IF mission_selection = 3 
		DISPLAY_TEXT 322.0 31.0 DS_I //Whip and Terminate
		f1_which_score_displayed = f1_whiprightterminate_best_score  
		GOSUB f1_drawing_medal 
	ENDIF
	IF mission_selection = 4 
		DISPLAY_TEXT 322.0 31.0 DS_I //Whip and Terminate
		f1_which_score_displayed = f1_whiprightterminate_best_score  
		GOSUB f1_drawing_medal 
	ENDIF
	IF mission_selection = 5 	   														 
		DISPLAY_TEXT 322.0 31.0 DS_F //Pop and Control
		f1_which_score_displayed = f1_popcontrol_best_score  
		GOSUB f1_drawing_medal 
	ENDIF
	IF mission_selection = 6 
		DISPLAY_TEXT 322.0 31.0 DS_E //Burn and Lap 
		f1_which_score_displayed = f1_burnlapright_best_score  
		GOSUB f1_drawing_medal 
	ENDIF
	IF mission_selection = 7 
		DISPLAY_TEXT 322.0 31.0 DS_E //Burn and Lap 
		f1_which_score_displayed = f1_burnlapright_best_score  
		GOSUB f1_drawing_medal 
	ENDIF
	IF mission_selection = 8 
		DISPLAY_TEXT 322.0 31.0 DS_N //Cone Coil 
		f1_which_score_displayed = f1_conecoilright_best_score  
		GOSUB f1_drawing_medal 
	ENDIF
	IF mission_selection = 9 
		DISPLAY_TEXT 322.0 31.0 DS_N //Cone Coil 
		f1_which_score_displayed = f1_conecoilright_best_score  
		GOSUB f1_drawing_medal 
	ENDIF
	IF mission_selection = 10 
		DISPLAY_TEXT 322.0 31.0 DS_A //The 90
		f1_which_score_displayed = f1_the90_best_score  
		GOSUB f1_drawing_medal 
	ENDIF
	IF mission_selection = 11 
		DISPLAY_TEXT 322.0 31.0 DS_K //Wheelie Weave
		f1_which_score_displayed = f1_wheelieweave_best_score  
		GOSUB f1_drawing_medal 
	ENDIF
	IF mission_selection = 12 
		DISPLAY_TEXT 322.0 31.0 DS_C //Spin and Go
		f1_which_score_displayed = f1_spinrightgo_best_score  
		GOSUB f1_drawing_medal 
	ENDIF
	IF mission_selection = 13 
		DISPLAY_TEXT 322.0 31.0 DS_C //Spin and Go
		f1_which_score_displayed = f1_spinrightgo_best_score  
		GOSUB f1_drawing_medal 
	ENDIF
	IF mission_selection = 14 
		DISPLAY_TEXT 322.0 31.0 DS_L //P. I. T. Technique
		f1_which_score_displayed = f1_pittechnique_best_score  
		GOSUB f1_drawing_medal 
	ENDIF
	IF mission_selection = 15 
		DISPLAY_TEXT 322.0 31.0 DS_J //Alley Oop
		f1_which_score_displayed = f1_alleyoop_best_score  
		GOSUB f1_drawing_medal 
	ENDIF
	IF mission_selection = 16 
		DISPLAY_TEXT 322.0 31.0 DS_G //City Slicking
		f1_which_score_displayed = f1_cityslicking_best_score  
		GOSUB f1_drawing_medal 
	ENDIF

	GOSUB small_onscreen_text 
	GET_HUD_COLOUR HUD_COLOUR_YELLOW f1_r f1_g f1_b f1_alpha2
	SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
	IF current_Language = LANGUAGE_FRENCH
		SET_TEXT_SCALE 0.32 1.25 
		DISPLAY_TEXT 70.0 360.0 DS1_53
	ELSE
		SET_TEXT_SCALE 0.32 1.25 
		DISPLAY_TEXT 70.0 360.0 DS1_53
	ENDIF

	//drawing help text
	IF NOT f1_control_flag = 1 //waiting to select which facing track

		GOSUB small_onscreen_text 
		GET_HUD_COLOUR HUD_COLOUR_YELLOW f1_r f1_g f1_b f1_alpha2

		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2

		IF current_Language = LANGUAGE_FRENCH
			SET_TEXT_SCALE 0.32 1.25
			DISPLAY_TEXT 70.0 340.0 DS1_54 	//START
		ELSE
			SET_TEXT_SCALE 0.32 1.25
			DISPLAY_TEXT 70.0 340.0 DS1_54 	//START
		ENDIF
		
		IF NOT f1_which_missions_are_open_flag = 1
			GOSUB small_onscreen_text 
			GET_HUD_COLOUR HUD_COLOUR_YELLOW f1_r f1_g f1_b f1_alpha2
			SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2

			IF current_Language = LANGUAGE_FRENCH
				SET_TEXT_SCALE 0.32 1.25
				DISPLAY_TEXT 437.0 340.0 DS1_52 	//Navigate
			ELSE
				SET_TEXT_SCALE 0.32 1.25
				DISPLAY_TEXT 437.0 340.0 DS1_52 	//Navigate
			ENDIF
			
		ELSE
			GOSUB small_onscreen_text 			
			GET_HUD_COLOUR HUD_COLOUR_GREY f1_r f1_g f1_b f1_alpha2	//navigate greyed out
			SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
		   	
			IF current_Language = LANGUAGE_FRENCH
				SET_TEXT_SCALE 0.32 1.25
				DISPLAY_TEXT 437.0 340.0 DS1_52 	//Navigate
			ELSE
				SET_TEXT_SCALE 0.32 1.25
				DISPLAY_TEXT 437.0 340.0 DS1_52 	//Navigate
			ENDIF
		ENDIF
	ENDIF
	
	// New section to draw the joypad buttons separate from the help text
	GOSUB small_onscreen_text 
	SET_TEXT_SCALE 0.32 1.25  // Set scale for PC only
	GET_HUD_COLOUR HUD_COLOUR_WHITE f1_r f1_g f1_b f1_alpha2
	SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
	DISPLAY_TEXT 150.0 360.0 DS1_63
   	
	IF NOT f1_control_flag = 1 //waiting to select which facing track
		GOSUB small_onscreen_text
		GET_HUD_COLOUR HUD_COLOUR_WHITE f1_r f1_g f1_b f1_alpha2
		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
	    SET_TEXT_SCALE 0.32 1.25  // Set scale for PC only
		DISPLAY_TEXT 150.0 340.0 SCH_PRS
	
		IF NOT f1_which_missions_are_open_flag = 1
			GOSUB small_onscreen_text
			GET_HUD_COLOUR HUD_COLOUR_WHITE f1_r f1_g f1_b f1_alpha2
			SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
			SET_TEXT_SCALE 0.32 1.25  // Set scale for PC only
			DISPLAY_TEXT 537.4 340.0 DS1_62
		ENDIF
		
		IF  f1_which_missions_are_open_flag = 1
			GOSUB small_onscreen_text
			GET_HUD_COLOUR HUD_COLOUR_WHITE f1_r f1_g f1_b f1_alpha2
			SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
			SET_TEXT_SCALE 0.32 1.25  // Set scale for PC only
			DISPLAY_TEXT 537.4 340.0 DS1_62
		ENDIF
	ENDIF

	SET_TEXT_SCALE 0.52 1.45  // return scale, just in case.
	

	//TV SCREEN HUD
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 5 160.0 112.0 320.0 224.0 150 150 150 255
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 5 160.0 317.0 320.0 -224.0 150 150 150 255
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 5 480.0 112.0 -320.0 224.0 150 150 150 255
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 5 480.0 317.0 -320.0 -224.0 150 150 150 255
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 9 160.0 435.0 320.0 17.0 150 150 150 255
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 9 480.0 435.0 -320.0 17.0 150 150 150 255


RETURN/////////////////////////////////////////////////////////////////////////////////////

f1_drawing_medal://////////////////////////////////////////////////////////////////////////
    //background for medal, ribbons and score 
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 8 320.0 430.0 640.0 250.0 0 0 0 255

	//no medal
	IF f1_which_score_displayed < 70
		f1_which_medal_displayed = 1
	ENDIF	 
	
	//bronze
	IF f1_which_score_displayed > 69
		IF f1_which_score_displayed < 85
			f1_which_medal_displayed = 2
		ENDIF
	ENDIF

	//silver
	IF f1_which_score_displayed > 84
		IF f1_which_score_displayed < 100
			f1_which_medal_displayed = 3
		ENDIF
	ENDIF

	//gold
	IF f1_which_score_displayed = 100
		f1_which_medal_displayed = 4
	ENDIF	 

	//best score
	GOSUB small_onscreen_text 
	SET_TEXT_RIGHT_JUSTIFY OFF
	GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE f1_r f1_g f1_b f1_alpha2
	SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2

	IF f1_which_medal_displayed > 1 
		DISPLAY_TEXT_WITH_NUMBER 217.0 75.0 DS1_58 f1_which_score_displayed // Record Score ~1~%
	ELSE
		IF current_Language = LANGUAGE_ENGLISH
	    	DISPLAY_TEXT 175.0 75.0 DS1_67  //Get over 70% to pass
		ENDIF
		IF current_Language = LANGUAGE_FRENCH
	    	DISPLAY_TEXT 83.0 75.0 DS1_67  //Get over 70% to pass
		ENDIF
		IF current_Language = LANGUAGE_GERMAN
	    	DISPLAY_TEXT 89.0 75.0 DS1_67  //Get over 70% to pass
		ENDIF
		IF current_Language = LANGUAGE_ITALIAN
	    	DISPLAY_TEXT 147.0 75.0 DS1_67  //Get over 70% to pass
		ENDIF
		IF current_Language = LANGUAGE_SPANISH
	    	DISPLAY_TEXT 65.0 75.0 DS1_67  //Get over 70% to pass
		ENDIF
	ENDIF

	//which medal awarded//

	IF f1_which_medal_displayed > 1
		SET_SPRITES_DRAW_BEFORE_FADE TRUE
		DRAW_SPRITE	4 250.0 306.0 -60.0 60.0 180 180 180 255
		SET_SPRITES_DRAW_BEFORE_FADE TRUE
		DRAW_SPRITE	4 395.0 306.0 60.0 60.0 180 180 180 255
	ELSE
		SET_SPRITES_DRAW_BEFORE_FADE TRUE
		DRAW_SPRITE	7 250.0 306.0 -60.0 60.0 180 180 180 255
		SET_SPRITES_DRAW_BEFORE_FADE TRUE
		DRAW_SPRITE	7 395.0 306.0 60.0 60.0 180 180 180 255
	ENDIF

	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	IF f1_which_medal_displayed = 1 //no medal
		IF current_Language = LANGUAGE_ENGLISH
			DRAW_SPRITE	6 320.0 307.0 110.0 95.0 160 160 160 255
		ELSE
			DRAW_SPRITE	10 320.0 307.0 110.0 95.0 160 160 160 255
		ENDIF
		GOSUB small_onscreen_text 
		GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE f1_r f1_g f1_b f1_alpha2
		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
	    DISPLAY_TEXT 266.0 375.0 DS1_68
	ENDIF
	IF f1_which_medal_displayed = 2 
		DRAW_SPRITE	1 320.0 307.0 110.0 95.0 160 160 160 255
		GOSUB small_onscreen_text 
		GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE f1_r f1_g f1_b f1_alpha2
		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
	    DISPLAY_TEXT 280.0 375.0 DS1_57
	ENDIF
	IF f1_which_medal_displayed = 3 
		DRAW_SPRITE	2 320.0 307.0 110.0 95.0 160 160 160 255
		GOSUB small_onscreen_text 
		GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE f1_r f1_g f1_b f1_alpha2
		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
	    DISPLAY_TEXT 283.0 375.0 DS1_56
	ENDIF
	IF f1_which_medal_displayed = 4 
		DRAW_SPRITE	3 320.0 307.0 110.0 95.0 160 160 160 255
		GOSUB small_onscreen_text 
		GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE f1_r f1_g f1_b f1_alpha2
		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
	    DISPLAY_TEXT 294.0 375.0 DS1_55
	ENDIF

RETURN/////////////////////////////////////////////////////////////////////////////////////

watching_demo://///////////////////////////////////////////////////////////////////////////
//setting up each individual mission for the playback and starting playback
	IF playback_flag = 0 
		IF mission_selection = 1
			GOSUB setup_360
		ENDIF
		IF mission_selection = 2
			GOSUB setup_180
		ENDIF
		IF mission_selection = 4
			GOSUB setup_whip_and_terminate
		ENDIF
		IF mission_selection = 5
			GOSUB setup_pop_control
		ENDIF
		IF mission_selection = 7
			GOSUB setup_burn_and_lap
		ENDIF
		IF mission_selection = 9
			GOSUB setup_cone_coil
		ENDIF
		IF mission_selection = 10
			GOSUB setup_90
		ENDIF
		IF mission_selection = 11
			GOSUB setup_wheelie_weave
		ENDIF
		IF mission_selection = 13
			GOSUB setup_spin_go
		ENDIF
		IF mission_selection = 14
			GOSUB setup_pit_technique
		ENDIF
		IF mission_selection = 15
			GOSUB setup_swift_escape
		ENDIF
		IF mission_selection = 16
			GOSUB setup_city_slicking
		ENDIF
		playback_flag = 1
	ENDIF

	IF playback_flag = 1 
		//loading in car recordings.
		
		
		IF mission_selection = 14  //NEED TWO CARS FOR P.I.T. TECHNIQUE
			IF NOT IS_CAR_DEAD instructor_car
				IF NOT HAS_CAR_RECORDING_BEEN_LOADED 14
					REQUEST_CAR_RECORDING 14 
				ENDIF
				IF NOT HAS_CAR_RECORDING_BEEN_LOADED 3 
					REQUEST_CAR_RECORDING 3
				ENDIF
				IF HAS_CAR_RECORDING_BEEN_LOADED 14 
					IF HAS_CAR_RECORDING_BEEN_LOADED 3  
						//playing back car recordings	
						//LOAD_SCENE -2052.3 -129.9 34.2
						START_PLAYBACK_RECORDED_CAR instructor_car 14
						IF NOT IS_CAR_DEAD dummy_car1
							START_PLAYBACK_RECORDED_CAR dummy_car1 3
						ENDIF
						f1_fade_flag = 3 //FADE_IN
						playback_flag = 2
					ENDIF	
				ENDIF
			ENDIF
		ELSE

			IF NOT IS_CAR_DEAD instructor_car
				IF NOT HAS_CAR_RECORDING_BEEN_LOADED mission_selection
					REQUEST_CAR_RECORDING mission_selection 
				ELSE
					//playing back car recordings	
					//LOAD_SCENE -2052.3 -129.9 34.2
					START_PLAYBACK_RECORDED_CAR instructor_car mission_selection
					f1_fade_flag = 3 //FADE_IN
					playback_flag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	//waiting for playback to finish
	IF playback_flag = 2 
		IF NOT IS_CAR_DEAD instructor_car 
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR instructor_car
				f1_fade_flag = 1 //FADE_OUT
				playback_flag = 4
			ENDIF
		ELSE
			f1_fade_flag = 1 //FADE_OUT
			playback_flag = 4
		ENDIF
	ENDIF

	//overriding playback
	IF playback_flag = 3
		f1_fade_flag = 1 //FADE_OUT
		playback_flag = 4
	ENDIF
	
	//IF playback_flag = 4
	//AND f1_control_flag = 1
	//	playback_flag = 0
	//ENDIF

	//waiting for screen to FADE_OUT and cleaning everything up
	IF playback_flag = 4
		IF f1_fade_flag = 2
			IF mission_selection = 14  //NEED TWO CARS FOR P.I.T. TECHNIQUE 
				IF NOT IS_CAR_DEAD dummy_car1 
					STOP_PLAYBACK_RECORDED_CAR dummy_car1
				ENDIF
			ENDIF
			STOP_PLAYBACK_RECORDED_CAR instructor_car 
			DELETE_CAR instructor_car
			CLEAR_AREA noticeboard_x noticeboard_y noticeboard_z 300.0 TRUE
			CLEAR_ONSCREEN_TIMER car_timer
			FREEZE_ONSCREEN_TIMER FALSE
			DELETE_CHAR f1_test_crash_dummy 
			DELETE_CHAR f1_test_crash_dummy2 
			DELETE_CAR dummy_car1
			DELETE_CAR dummy_car2
			DELETE_CAR instructor_car
			DELETE_OBJECT ramp1
			DELETE_OBJECT ramp2
			DELETE_OBJECT f1_stinger
			REMOVE_CAR_RECORDING 1
			REMOVE_CAR_RECORDING 2
			REMOVE_CAR_RECORDING 3
			REMOVE_CAR_RECORDING 4
			REMOVE_CAR_RECORDING 5
			REMOVE_CAR_RECORDING 7
			REMOVE_CAR_RECORDING 9
			REMOVE_CAR_RECORDING 10
			REMOVE_CAR_RECORDING 11
			REMOVE_CAR_RECORDING 13
			REMOVE_CAR_RECORDING 14
			REMOVE_CAR_RECORDING 15
			REMOVE_CAR_RECORDING 16
			GOSUB deleting_cones
			playback_flag = 0
		ENDIF
	ENDIF		 			
RETURN/////////////////////////////////////////////////////////////////////////////////////

small_onscreen_text:///////////////////////////////////////////////////////////////////////
	SET_TEXT_DRAW_BEFORE_FADE TRUE
	SET_TEXT_JUSTIFY ON
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_WRAPX 1000.0
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_CENTRE OFF
	SET_TEXT_BACKGROUND OFF
	SET_TEXT_SCALE 0.52 1.45
	SET_TEXT_DROPSHADOW 2 0 0 0 255
	SET_TEXT_FONT FONT_SPACEAGE
RETURN/////////////////////////////////////////////////////////////////////////////////////

small_backend_text:////////////////////////////////////////////////////////////////////////
	SET_TEXT_JUSTIFY ON
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_WRAPX 1000.0
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_CENTRE OFF
	SET_TEXT_BACKGROUND OFF
	SET_TEXT_SCALE 0.52 1.45
	SET_TEXT_DROPSHADOW 2 0 0 0 255
	SET_TEXT_FONT FONT_SPACEAGE
RETURN/////////////////////////////////////////////////////////////////////////////////////

which_course_text:///////////////////////////////////////////////////////////////////////
	SET_TEXT_DRAW_BEFORE_FADE TRUE
	SET_TEXT_JUSTIFY ON
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_WRAPX 1000.0
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_CENTRE ON
	SET_TEXT_BACKGROUND OFF
	SET_TEXT_SCALE 0.8 2.6
	SET_TEXT_DROPSHADOW 2 0 0 0 255
RETURN/////////////////////////////////////////////////////////////////////////////////////

test_name_text:///////////////////////////////////////////////////////////////////////
	SET_TEXT_DRAW_BEFORE_FADE TRUE
	SET_TEXT_JUSTIFY ON
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_WRAPX 1000.0
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_CENTRE ON
	SET_TEXT_BACKGROUND OFF
	SET_TEXT_SCALE 1.2 4.0
	SET_TEXT_DROPSHADOW 3 0 0 0 255
	SET_TEXT_FONT FONT_HEADING
RETURN/////////////////////////////////////////////////////////////////////////////////////

start_initialise_stuff:////////////////////////////////////////////////////////////////////
	DO_FADE 500 FADE_OUT

	WHILE GET_FADING_STATUS
	    WAIT 0
	ENDWHILE 
	IF mission_selection = 16 
		SET_CAR_DENSITY_MULTIPLIER 1.0
		SET_PED_DENSITY_MULTIPLIER 1.0
	ELSE
		SET_CAR_DENSITY_MULTIPLIER 0.0
		SET_PED_DENSITY_MULTIPLIER 0.0
	ENDIF	
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER
	FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_SF
	SET_AREA_VISIBLE 0
	SET_CHAR_AREA_VISIBLE scplayer 0 
	CLEAR_EXTRA_COLOURS FALSE
RETURN/////////////////////////////////////////////////////////////////////////////////////
	
setting_up_variables://////////////////////////////////////////////////////////////////////
	area_check1ax = 0.0
	area_check1ay = 0.0
	area_check1bx = 0.0
	area_check1by = 0.0
	area_check2ax = 0.0
	area_check2ay = 0.0
	area_check2bx = 0.0
	area_check2by = 0.0
	area_check3ax = 0.0
	area_check3ay = 0.0
	area_check3bx = 0.0
	area_check3by = 0.0
	area_check4ax = 0.0
	area_check4ay = 0.0
	area_check4bx = 0.0
	area_check4by = 0.0
	button_pressed = 0
	car_posx = 0.0
	car_posy = 0.0
	cone_coords_x = 0.0
	cone_coords_y = 0.0
	cone_coords_z = 0.0
	cone_circle_radius = 10.0
	car_started = 0
	circle_start_x = 0.0
	circle_start_y = 0.0
	camera_positionx = 0.0
	camera_positiony = 0.0
	camera_positionz = 0.0
	camera_position_int = 0
	dummy_car1_health = 0
	dummy_car2_health = 0
	finish_leftx = 0.0
	finish_lefty = 0.0
	finish_rightx = 0.0
	finish_righty = 0.0
	finished_watching_scores = 0
	heading_score = 0
	instructor_car_speed = 0.0
	instructor_car_heading = 0.0
	instructor_car_heading_int = 0
	instructor_car_health = 0
	instructor_car_dead_flag = 0
	lap_counter = 0
	lap1_secs = 0
	lap1_millisecs = 0
	lap2_secs = 0
	lap2_millisecs = 0
	lap3_secs = 0
	lap3_millisecs = 0
	lap4_secs = 0
	lap4_millisecs = 0
	lap5_secs = 0
	lap5_millisecs = 0
	overall_lap1 = 0
	overall_lap2 = 0
	overall_lap3 = 0
	overall_lap4 = 0
	overall_lap5 = 0
	overall_secs = 0
	overall_millisecs = 0
	overall_score = 0
	other_car_damage = 0
	perfect_positionx = start_coordsx
	perfect_positiony = start_coordsy
	GET_GROUND_Z_FOR_3D_COORD perfect_positionx perfect_positiony 50.0 perfect_positionz
	player_car_damage = 0 
	players_distance_from_perfectpos = 0.0
	position_score = 0
	time_score = 0
	total_instructor_car_health = 0
	total_dummy_car1_health = 0
	total_dummy_car2_health = 0
	total_car_damage = 0
	variablea = 0
	variableb = 0.0
	variablec = 0
	variabled = 0
	where_to_place_cones = 0.0
	instructor_car_roll = 0.0
	instructor_car_roll_score = 0
	instructor_car_roll_plus_minus = 0
	f1_print_top_scores_flag = 0
	f1_direction_flag = 0
RETURN/////////////////////////////////////////////////////////////////////////////////////

stop_initialise_stuff://///////////////////////////////////////////////////////////////////
	WARP_CHAR_INTO_CAR scplayer instructor_car
	LOCK_CAR_DOORS instructor_car CARLOCK_LOCKED_PLAYER_INSIDE
	WAIT 0
	RESTORE_CAMERA_JUMPCUT    
	SET_CAMERA_BEHIND_PLAYER 
	CLEAR_WANTED_LEVEL player1
	DO_FADE 500 FADE_IN
	WHILE GET_FADING_STATUS
	    WAIT 0
	ENDWHILE 
	SET_PLAYER_CONTROL player1 on
RETURN/////////////////////////////////////////////////////////////////////////////////////

instructor_car_dead:///////////////////////////////////////////////////////////////////////
	IF NOT IS_CAR_DEAD instructor_car
		IF car_started = 1 
			IF NOT IS_CHAR_IN_CAR scplayer instructor_car
				instructor_car_dead_flag = 1
			ENDIF
		ENDIF
	ELSE
		IF IS_CAR_IN_WATER instructor_car 
			instructor_car_dead_flag = 2
		ELSE
			instructor_car_dead_flag = 1
		ENDIF
	ENDIF
RETURN/////////////////////////////////////////////////////////////////////////////////////

has_car_started:///////////////////////////////////////////////////////////////////////////
	IF NOT IS_CAR_DEAD instructor_car
		IF IS_CHAR_IN_CAR scplayer instructor_car
			GET_CAR_SPEED instructor_car instructor_car_speed 
		ENDIF
	ENDIF
	IF IS_BUTTON_PRESSED PAD1 CROSS 
	OR IS_BUTTON_PRESSED PAD1 SQUARE
		IF instructor_car_speed > 0.5
			CLEAR_PRINTS
			IF mission_selection = 10
			OR mission_selection = 12
			OR mission_selection = 13
			OR mission_selection = 5
			OR mission_selection = 3
			OR mission_selection = 4 
				
				DISPLAY_ONSCREEN_TIMER car_timer TIMER_DOWN
				SET_TIMER_BEEP_COUNTDOWN_TIME car_timer 3 
			ENDIF 
			IF mission_selection = 15
			OR mission_selection = 8
			OR mission_selection = 9
			OR mission_selection = 2
			OR mission_selection = 1
				DISPLAY_ONSCREEN_TIMER car_timer TIMER_DOWN
				SET_TIMER_BEEP_COUNTDOWN_TIME car_timer 3 
			ENDIF 

			IF mission_selection = 6
			OR mission_selection = 7
			OR mission_selection = 16
				DISPLAY_ONSCREEN_TIMER car_timer TIMER_UP
			ENDIF 
			IF mission_selection = 14
				IF NOT IS_CAR_DEAD dummy_car1
					SET_CAR_FORWARD_SPEED dummy_car1 6.0
					SET_CAR_TEMP_ACTION dummy_car1 TEMPACT_GOFORWARD 864000000	
				ENDIF
			ENDIF
			car_started = 1
		ENDIF
	ENDIF
RETURN/////////////////////////////////////////////////////////////////////////////////////

car_timer_0:///////////////////////////////////////////////////////////////////////////////
	IF car_timer = 0
		SET_PLAYER_CONTROL player1 off
		APPLY_BRAKES_TO_PLAYERS_CAR player1 TRUE
	ENDIF	 
RETURN/////////////////////////////////////////////////////////////////////////////////////

freeze_car_pos:////////////////////////////////////////////////////////////////////////////
	SET_PLAYER_CONTROL player1 off
	FREEZE_CAR_POSITION instructor_car TRUE
	FREEZE_ONSCREEN_TIMER TRUE
	REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DRIVING_AWARD_TRACK_START
RETURN/////////////////////////////////////////////////////////////////////////////////////

position_score_calcs://////////////////////////////////////////////////////////////////////
	GET_CAR_COORDINATES instructor_car player_x player_y player_z  
	GET_DISTANCE_BETWEEN_COORDS_2D player_x player_y perfect_positionx perfect_positiony players_distance_from_perfectpos  
	IF players_distance_from_perfectpos > 0.5
		IF players_distance_from_perfectpos < 10.0 
				players_distance_from_perfectpos -= 0.5	 
				players_distance_from_perfectpos *= 10.42 //10.42 = 99(which is highest score possible) / 9.5 ( which is 10 - 0.5)
			variablea =# players_distance_from_perfectpos 
			position_score = 100 - variablea
		ELSE
			position_score = 0
		ENDIF
	ELSE
		position_score = 100
	ENDIF 
	overall_score += position_score 
RETURN/////////////////////////////////////////////////////////////////////////////////////

damage_score_calcs:////////////////////////////////////////////////////////////////////////
	GET_CAR_HEALTH instructor_car instructor_car_health  
	player_car_damage = 1000 - instructor_car_health
	//debug - changed damage from 10 to 2 to make the missions harder
	//player_car_damage /= 10
	IF mission_selection = 16
		player_car_damage /= 10
	ELSE
		player_car_damage /= 2
	ENDIF
	IF player_car_damage > 100
		player_car_damage = 100
	ENDIF	 
RETURN/////////////////////////////////////////////////////////////////////////////////////

damage_cones_calcs:////////////////////////////////////////////////////////////////////////
	total_car_damage = 0
	trafficcone_counter = 0

	IF mission_selection = 1 
		WHILE trafficcone_counter < 15
			IF HAS_OBJECT_BEEN_DAMAGED trafficcones[trafficcone_counter]
				total_car_damage += 10
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	IF mission_selection = 2 
		WHILE trafficcone_counter < 25
			IF HAS_OBJECT_BEEN_DAMAGED trafficcones[trafficcone_counter]
				total_car_damage += 10
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	IF mission_selection = 3 
	OR mission_selection = 4
		WHILE trafficcone_counter < 46
			IF HAS_OBJECT_BEEN_DAMAGED trafficcones[trafficcone_counter]
				total_car_damage += 10
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	IF mission_selection = 5 
		WHILE trafficcone_counter < 37
			IF HAS_OBJECT_BEEN_DAMAGED trafficcones[trafficcone_counter]
				total_car_damage += 10
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	IF mission_selection = 6 
	OR mission_selection = 7
		WHILE trafficcone_counter < 42
			IF HAS_OBJECT_BEEN_DAMAGED trafficcones[trafficcone_counter]
				total_car_damage += 10
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	IF mission_selection = 8
	OR mission_selection = 9
		WHILE trafficcone_counter < 30
			IF HAS_OBJECT_BEEN_DAMAGED trafficcones[trafficcone_counter]
				total_car_damage += 10
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	IF mission_selection = 12 
	OR mission_selection = 13
		WHILE trafficcone_counter < 31
			IF HAS_OBJECT_BEEN_DAMAGED trafficcones[trafficcone_counter]
				total_car_damage += 10
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	ENDIF

	//debug - increasing damage score?
	total_car_damage = total_car_damage * 2
	
	IF total_car_damage > 90
		total_car_damage = 100
	ENDIF 

	overall_score -= total_car_damage
RETURN/////////////////////////////////////////////////////////////////////////////////////

checking_overall_score:////////////////////////////////////////////////////////////////////
	IF overall_score < 1
		overall_score = 0
	ENDIF 
	CLEAR_PRINTS
RETURN/////////////////////////////////////////////////////////////////////////////////////

setting_up_camera://///////////////////////////////////////////////////////////////////////
	IF NOT IS_CAR_DEAD instructor_car 
		IF camera_position_int = 0
			GET_CAR_COORDINATES instructor_car camera_positionx camera_positiony camera_positionz 
			camera_positionz += 5.0
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 15.0 0.0 5.0 camera_positionx camera_positiony z
			SET_FIXED_CAMERA_POSITION camera_positionx camera_positiony camera_positionz 0.0 0.0 0.0 
			GET_PLAYER_IN_CAR_CAMERA_MODE f1_camera_mode 
			IF f1_camera_mode = 1
			OR f1_camera_mode = 2
			OR f1_camera_mode = 3
				POINT_CAMERA_AT_CAR instructor_car FIXED INTERPOLATION
			ELSE
				POINT_CAMERA_AT_CAR instructor_car FIXED JUMP_CUT
			ENDIF
			SET_INTERPOLATION_PARAMETERS 50.0 2000
			ALLOW_FIXED_CAMERA_COLLISION TRUE
			camera_position_int = 1
		ENDIF
		IF timera > 3000 
			IF timera < 6000
				IF camera_position_int = 1
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 0.0 15.0 5.0 camera_positionx camera_positiony z
					SET_FIXED_CAMERA_POSITION camera_positionx camera_positiony camera_positionz 0.0 0.0 0.0 
					POINT_CAMERA_AT_CAR instructor_car FIXED INTERPOLATION
					SET_INTERPOLATION_PARAMETERS 50.0 2000
					camera_position_int = 2
				ENDIF
			ENDIF
		ENDIF
		IF timera > 6000 
			IF camera_position_int = 2
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car -15.0 0.0 5.0 camera_positionx camera_positiony z
				SET_FIXED_CAMERA_POSITION camera_positionx camera_positiony camera_positionz 0.0 0.0 0.0 
				POINT_CAMERA_AT_CAR instructor_car FIXED INTERPOLATION
				SET_INTERPOLATION_PARAMETERS 50.0 2000
				camera_position_int = 3
			ENDIF
		ENDIF
	ENDIF
RETURN/////////////////////////////////////////////////////////////////////////////////////

f1_medal_check:////////////////////////////////////////////////////////////////////////////
	f1_which_medal_displayed = 0
	//bronze
	IF overall_score > 69
		IF overall_score < 85
			IF f1_old_score > 69 
			AND f1_old_score < 85 
				f1_which_medal_displayed = 0
			ELSE	
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				f1_which_medal_displayed = 2
			ENDIF
		ENDIF
	ENDIF
	//silver
	IF overall_score > 84
		IF overall_score < 100
			IF f1_old_score > 84 
			AND f1_old_score < 100 
				f1_which_medal_displayed = 0
			ELSE	
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				f1_which_medal_displayed = 3
			ENDIF
		ENDIF
	ENDIF
	//gold
	IF overall_score = 100
		IF f1_old_score = 100
			f1_which_medal_displayed = 0
		ELSE
			INCREMENT_FLOAT_STAT DRIVING_SKILL 30.0
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
			f1_which_medal_displayed = 4
		ENDIF
	ENDIF
RETURN/////////////////////////////////////////////////////////////////////////////////////

display_head_pos_dam_text://///////////////////////////////////////////////////////////////

	DISPLAY_HUD FALSE
	DISPLAY_RADAR FALSE
   	CLEAR_PRINTS
	CLEAR_ONSCREEN_TIMER car_timer


	IF f1_which_medal_displayed < 2  // no medal,  2 is bronze
		DRAW_WINDOW 160.0 220.0 490.0 400.0 DS1_65 SWIRLS_BOTH
	ELSE
		DRAW_WINDOW 160.0 200.0 490.0 400.0 DUMMY SWIRLS_BOTH
	ENDIF


  	// DRAWING THE SCORE NAMES
	IF mission_selection = 6  	
	OR mission_selection = 7
	OR mission_selection = 16

		GOSUB small_backend_text 
		GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE f1_r f1_g f1_b f1_alpha2
		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
		DISPLAY_TEXT 180.0 255.0 DS1_72 
		
		GOSUB small_backend_text 
		SET_TEXT_RIGHT_JUSTIFY ON
		SET_TEXT_COLOUR 255 255 255 255
		
		IF overall_millisecs > 9
			DISPLAY_TEXT_WITH_2_NUMBERS 450.0 255.0 DS1_21 overall_secs overall_millisecs
		ELSE
			DISPLAY_TEXT_WITH_2_NUMBERS 450.0 255.0 DS1_22 overall_secs overall_millisecs
		ENDIF

		
		GOSUB small_backend_text 
		GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE f1_r f1_g f1_b f1_alpha2
		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
		DISPLAY_TEXT 180.0 275.0 DS1_23 

		GOSUB small_backend_text 
		SET_TEXT_RIGHT_JUSTIFY ON
		SET_TEXT_COLOUR 255 255 255 255
		DISPLAY_TEXT_WITH_NUMBER 450.0 275.0 DS1_69 time_score
	ELSE
		GOSUB small_backend_text 
		GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE f1_r f1_g f1_b f1_alpha2
		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
		DISPLAY_TEXT 180.0 255.0 DS1_5

		GOSUB small_backend_text 
		SET_TEXT_RIGHT_JUSTIFY ON
		SET_TEXT_COLOUR 255 255 255 255
		DISPLAY_TEXT_WITH_NUMBER 450.0 255.0 DS1_69 heading_score

		GOSUB small_backend_text 
		GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE f1_r f1_g f1_b f1_alpha2
		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
		DISPLAY_TEXT 180.0 275.0 DS1_6

		GOSUB small_backend_text 
		SET_TEXT_RIGHT_JUSTIFY ON
		SET_TEXT_COLOUR 255 255 255 255
		DISPLAY_TEXT_WITH_NUMBER 450.0 275.0 DS1_69 position_score

	ENDIF

		GOSUB small_backend_text 
		GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE f1_r f1_g f1_b f1_alpha2
		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
		DISPLAY_TEXT 180.0 295.0 DS1_7

		GOSUB small_backend_text 
		SET_TEXT_RIGHT_JUSTIFY ON
		GET_HUD_COLOUR HUD_COLOUR_RED f1_r f1_g f1_b f1_alpha2
		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
		DISPLAY_TEXT_WITH_NUMBER 450.0 295.0 DS1_70 total_car_damage

		GOSUB small_backend_text 
		GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE f1_r f1_g f1_b f1_alpha2
		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
		DISPLAY_TEXT 180.0 315.0 DS1_10

		GOSUB small_backend_text 
		SET_TEXT_RIGHT_JUSTIFY ON
		SET_TEXT_COLOUR 255 255 255 255
		DISPLAY_TEXT_WITH_NUMBER 450.0 315.0 DS1_69 overall_score


	// DISPLAYING MEDALS
	IF f1_print_top_scores_flag = 1	
	OR f1_print_top_scores_flag = 2 
		IF f1_which_medal_displayed > 0
			
			DRAW_SPRITE	4 250.0 199.0 -60.0 60.0 180 180 180 255
			DRAW_SPRITE	4 392.0 199.0 60.0 60.0 180 180 180 255

			//bronze
			IF f1_which_medal_displayed = 2
				DRAW_SPRITE	1 320.0 200.0 110.0 95.0 160 160 160 255
			ENDIF
			//silver
			IF f1_which_medal_displayed = 3
				DRAW_SPRITE	2 320.0 200.0 110.0 95.0 160 160 160 255
			ENDIF
			//gold
			IF f1_which_medal_displayed = 4
				DRAW_SPRITE	3 320.0 200.0 110.0 95.0 160 160 160 255
			ENDIF
	
			//NEW CERTIFICATE AWARDED!
			GOSUB test_name_text
			SET_TEXT_EDGE 2 0 0 0 255  
			SET_TEXT_SCALE 1.0 3.4
			GET_HUD_COLOUR HUD_COLOUR_YELLOW f1_r f1_g f1_b f1_alpha2
			SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
			DISPLAY_TEXT 323.0 65.0 DS1_47  //NEW CERTIFICATE AWARDED!
		ENDIF
	ENDIF

	IF f1_print_top_scores_flag = 1
	OR f1_print_top_scores_flag = 2 
		// NEW HIGH SCORE
		GOSUB test_name_text
		SET_TEXT_EDGE 2 0 0 0 255  
		SET_TEXT_SCALE 1.0 3.4
		SET_TEXT_COLOUR 255 255 255 255
		DISPLAY_TEXT 323.0 110.0 DS1_46  //NEW HIGH SCORE!
	ENDIF

	IF NOT f1_print_top_scores_flag = 2
		GOSUB small_backend_text 
		SET_TEXT_SCALE 0.52 1.45
		GET_HUD_COLOUR HUD_COLOUR_YELLOW f1_r f1_g f1_b f1_alpha2
		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
		DISPLAY_TEXT 340.0 345.0 SCH_PRS

		GOSUB small_backend_text 
		SET_TEXT_SCALE 0.52 1.45
		GET_HUD_COLOUR HUD_COLOUR_YELLOW f1_r f1_g f1_b f1_alpha2
		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
		DISPLAY_TEXT 180.0 345.0 DS1_66

		GOSUB small_backend_text 
		SET_TEXT_SCALE 0.52 1.45
		GET_HUD_COLOUR HUD_COLOUR_YELLOW f1_r f1_g f1_b f1_alpha2
		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
		DISPLAY_TEXT 340.0 365.0 DS1_63

		GOSUB small_backend_text 
		SET_TEXT_SCALE 0.52 1.45
		GET_HUD_COLOUR HUD_COLOUR_YELLOW f1_r f1_g f1_b f1_alpha2
		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
		DISPLAY_TEXT 180.0 365.0 DS1_53
	ELSE
		GOSUB small_backend_text 
		SET_TEXT_SCALE 0.52 1.45
		SET_TEXT_COLOUR 255 255 255 255
		DISPLAY_TEXT 180.0 345.0 DS1_71

		GOSUB small_backend_text 
		SET_TEXT_SCALE 0.52 1.45
		GET_HUD_COLOUR HUD_COLOUR_YELLOW f1_r f1_g f1_b f1_alpha2
		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
		DISPLAY_TEXT 340.0 365.0 SCH_PRS

		GOSUB small_backend_text 
		SET_TEXT_SCALE 0.52 1.45
		GET_HUD_COLOUR HUD_COLOUR_YELLOW f1_r f1_g f1_b f1_alpha2
		SET_TEXT_COLOUR f1_r f1_g f1_b f1_alpha2
		DISPLAY_TEXT 180.0 365.0 DS1_66
	ENDIF
RETURN

skip_scores:///////////////////////////////////////////////////////////////////////////////
	
	IF IS_BUTTON_PRESSED PAD1 CROSS
		IF button_pressed = 1
			button_pressed = 0
			finished_watching_scores = 1
		ENDIF
	ELSE
		IF button_pressed = 0
			button_pressed = 1
		ENDIF
	ENDIF
RETURN/////////////////////////////////////////////////////////////////////////////////////

mini_cleanup://////////////////////////////////////////////////////////////////////////////
	REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DRIVING_AWARD_TRACK_STOP
	CLEAR_PRINTS
	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
	    WAIT 0
	ENDWHILE
	CLEAR_PRINTS
	CLEAR_THIS_BIG_PRINT DS1_46 
	CLEAR_THIS_BIG_PRINT DS1_47 
	ALLOW_FIXED_CAMERA_COLLISION FALSE
	APPLY_BRAKES_TO_PLAYERS_CAR player1 FALSE 
	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
	IF instructor_car_dead_flag = 2
		CLEAR_PRINTS
		IF IS_CHAR_IN_ANY_CAR scplayer 
			WARP_CHAR_FROM_CAR_TO_COORD scplayer noticeboard_x noticeboard_y noticeboard_z
		ELSE
			SET_CHAR_COORDINATES scplayer noticeboard_x noticeboard_y noticeboard_z
		ENDIF
	ELSE
		IF IS_CHAR_IN_ANY_CAR scplayer 
			WARP_CHAR_FROM_CAR_TO_COORD scplayer noticeboard_x noticeboard_y noticeboard_z
		ELSE
			SET_CHAR_COORDINATES scplayer noticeboard_x noticeboard_y noticeboard_z
		ENDIF
	ENDIF
	CLEAR_PRINTS
	WAIT 0
	CLEAR_PRINTS
	DELETE_CAR instructor_car
	CLEAR_AREA noticeboard_x noticeboard_y noticeboard_z 300.0 TRUE
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER
	CLEAR_PRINTS
	WAIT 0
	CLEAR_PRINTS
	CLEAR_ONSCREEN_TIMER car_timer
	FREEZE_ONSCREEN_TIMER FALSE
	DISPLAY_HUD TRUE
	DISPLAY_RADAR TRUE
RETURN/////////////////////////////////////////////////////////////////////////////////////

deleting_cones:////////////////////////////////////////////////////////////////////////////
	CLEAR_PRINTS
	trafficcone_counter = 0
	WHILE trafficcone_counter < 46
		CLEAR_PRINTS
		DELETE_OBJECT trafficcones[trafficcone_counter]
		trafficcone_counter ++
	ENDWHILE
RETURN/////////////////////////////////////////////////////////////////////////////////////

creating_cones:////////////////////////////////////////////////////////////////////////////

	trafficcone_counter = 0
	IF mission_selection = 1
		WHILE trafficcone_counter < 15
			CREATE_OBJECT_NO_OFFSET trafficcone 0.0 0.0 100.0 trafficcones[trafficcone_counter] 
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	IF mission_selection = 2
		WHILE trafficcone_counter < 25
			CREATE_OBJECT_NO_OFFSET trafficcone 0.0 0.0 100.0 trafficcones[trafficcone_counter] 
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	
	IF mission_selection = 3
	OR mission_selection = 4
		WHILE trafficcone_counter < 46
			CREATE_OBJECT_NO_OFFSET trafficcone 0.0 0.0 100.0 trafficcones[trafficcone_counter] 
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	IF mission_selection = 5
		WHILE trafficcone_counter < 37
			CREATE_OBJECT_NO_OFFSET trafficcone 0.0 0.0 100.0 trafficcones[trafficcone_counter] 
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	IF mission_selection = 6
	OR mission_selection = 7
		WHILE trafficcone_counter < 42
			CREATE_OBJECT_NO_OFFSET trafficcone 0.0 0.0 100.0 trafficcones[trafficcone_counter] 
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	IF mission_selection = 8
	OR mission_selection = 9
		WHILE trafficcone_counter < 30
			CREATE_OBJECT_NO_OFFSET trafficcone 0.0 0.0 100.0 trafficcones[trafficcone_counter] 
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	IF mission_selection = 12
	OR mission_selection = 13
		WHILE trafficcone_counter < 31
			CREATE_OBJECT_NO_OFFSET trafficcone 0.0 0.0 100.0 trafficcones[trafficcone_counter] 
			trafficcone_counter ++
		ENDWHILE
	ENDIF

	//placing cones for the 360
	IF mission_selection = 1
		cone_circle_radius = 6.0
		//creating a pure circle
		where_to_place_cones = 0.0
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 0.0 1.0 -0.5 circle_start_x circle_start_y cone_coords_z 
		GET_GROUND_Z_FOR_3D_COORD circle_start_x circle_start_y 40.0 cone_coords_z 	
		cone_coords_z += 0.4

		creating_cone_circle4:

		SIN where_to_place_cones cone_coords_x
		cone_coords_x = cone_coords_x * cone_circle_radius
		cone_coords_x = cone_coords_x + circle_start_x
		COS where_to_place_cones cone_coords_y
		cone_coords_y = cone_coords_y * cone_circle_radius
		cone_coords_y = cone_coords_y + circle_start_y

		IF where_to_place_cones = 0.0
			SET_OBJECT_COORDINATES trafficcones[0] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 24.0
			SET_OBJECT_COORDINATES trafficcones[1] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 48.0
			SET_OBJECT_COORDINATES trafficcones[2] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 72.0
			SET_OBJECT_COORDINATES trafficcones[3] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 96.0
			SET_OBJECT_COORDINATES trafficcones[4] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 120.0
			SET_OBJECT_COORDINATES trafficcones[5] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 144.0
			SET_OBJECT_COORDINATES trafficcones[6] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 168.0
			SET_OBJECT_COORDINATES trafficcones[7] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 192.0
			SET_OBJECT_COORDINATES trafficcones[8] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 216.0
			SET_OBJECT_COORDINATES trafficcones[9] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF
	
		IF where_to_place_cones = 240.0
			SET_OBJECT_COORDINATES trafficcones[10] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 264.0
			SET_OBJECT_COORDINATES trafficcones[11] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 288.0
			SET_OBJECT_COORDINATES trafficcones[12] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 312.0
			SET_OBJECT_COORDINATES trafficcones[13] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 336.0
			SET_OBJECT_COORDINATES trafficcones[14] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		where_to_place_cones = where_to_place_cones + 24.0
		IF where_to_place_cones < 360.0
			GOTO creating_cone_circle4
		ENDIF
	ENDIF

	//placing cones for the 180 
	IF mission_selection = 2
		//creating the semi circle at the end of the straight 
		cone_circle_radius = 6.0
		where_to_place_cones = 90.0
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 0.0 86.0 -0.5 circle_start_x circle_start_y cone_coords_z 
		GET_GROUND_Z_FOR_3D_COORD circle_start_x circle_start_y 40.0 cone_coords_z
		cone_coords_z += 0.4

		creating_cone_circle3:

		SIN where_to_place_cones cone_coords_x
		cone_coords_x = cone_coords_x * cone_circle_radius
		cone_coords_x = cone_coords_x + circle_start_x
		COS where_to_place_cones cone_coords_y
		cone_coords_y = cone_coords_y * cone_circle_radius
		cone_coords_y = cone_coords_y + circle_start_y

		IF where_to_place_cones = 120.0
			SET_OBJECT_COORDINATES trafficcones[0] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 150.0
			SET_OBJECT_COORDINATES trafficcones[1] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 180.0
			SET_OBJECT_COORDINATES trafficcones[2] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 210.0
			SET_OBJECT_COORDINATES trafficcones[3] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 240.0
			SET_OBJECT_COORDINATES trafficcones[4] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		where_to_place_cones = where_to_place_cones + 30.0
		IF where_to_place_cones < 271.0
			GOTO creating_cone_circle3
		ENDIF

		//RUNWAY UP TO CIRCLE
		//1st set left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[7] instructor_car -7.0 74.0 -0.5

		//1st set right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[8] instructor_car 7.0 74.0 -0.5

		//2nd set left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[9] instructor_car -7.0 78.0 -0.5

		//2nd set right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[10] instructor_car 7.0 78.0 -0.5

		//3rd set left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[11] instructor_car -7.0 82.0 -0.5

		//3rd set right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[12] instructor_car 7.0 82.0 -0.5

		//4th set left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[13] instructor_car -7.0 86.0 -0.5

		//4th set right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[14] instructor_car 7.0 86.0 -0.5

		//3rd centre cone
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[15] instructor_car 0.0 82.0 -0.5

		//AROUND CAR
		//front left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[16] instructor_car -3.0 4.0 -0.5

		//front right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[17] instructor_car 3.0 4.0 -0.5

		//left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[18] instructor_car -3.0 0.0 -0.5

		//right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[19] instructor_car 3.0 0.0 -0.5

		//back left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[20] instructor_car -3.0 -4.0 -0.5

		//back right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[21] instructor_car 3.0 -4.0 -0.5

		//back of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[22] instructor_car 0.0 -4.0 -0.5
	
		//EXTRA CENTRE CONES....
		//1st centre cone
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[23] instructor_car 0.0 74.0 -0.5

		//2nd centre cone
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[24] instructor_car 0.0 78.0 -0.5
	
		trafficcone_counter = 7 
		WHILE trafficcone_counter < 25
			IF DOES_OBJECT_EXIST trafficcones[trafficcone_counter] 
				GET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z
				GET_GROUND_Z_FOR_3D_COORD cone_coords_x cone_coords_y 40.0 cone_coords_z  
				cone_coords_z += 0.4
				SET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z 
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	ENDIF

	//placing cones for reverse spin and go
	IF mission_selection = 12
	OR mission_selection = 13 
		//AROUND CAR
		//front of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[0] instructor_car 0.0 4.0 -0.5

		//front left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[1] instructor_car -3.0 4.0 -0.5

		//front right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[2] instructor_car 3.0 4.0 -0.5

		//left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[3] instructor_car -3.0 0.0 -0.5

		//right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[4] instructor_car 3.0 0.0 -0.5

		//back left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[5] instructor_car -3.0 -4.0 -0.5

		//back right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[6] instructor_car 3.0 -4.0 -0.5

		//RUNWAY BEHIND CAR TO CURVE
		//1st set behind car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[7] instructor_car -3.0 -8.0 -0.5

		//1st set behind car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[8] instructor_car 3.0 -8.0 -0.5

		//2nd set behind car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[9] instructor_car -3.0 -12.0 -0.5

		//2nd set behind car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[10] instructor_car 3.0 -12.0 -0.5

		//3rd set behind car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[11] instructor_car -3.0 -16.0 -0.5

		//3rd set behind car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[12] instructor_car 3.0 -16.0 -0.5

		//4th set behind car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[13] instructor_car -3.0 -20.0 -0.5

		//4th set behind car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[14] instructor_car 3.0 -20.0 -0.5
	
		trafficcone_counter = 0 
		WHILE trafficcone_counter < 15
			IF DOES_OBJECT_EXIST trafficcones[trafficcone_counter] 
				GET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z
				GET_GROUND_Z_FOR_3D_COORD cone_coords_x cone_coords_y 40.0 cone_coords_z  
				cone_coords_z += 0.4
				SET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z 
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	ENDIF

	//creating the duplicate cones
	IF mission_selection = 12
		//START OF CURVE
		//1st set curve car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[15] instructor_car -3.0 -24.0 -0.5

		//1st set curve car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[16] instructor_car 6.0 -22.0 -0.5
		
		//2nd set curve car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[17] instructor_car 0.0 -28.0 -0.5

		//2nd set curve car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[18] instructor_car 8.0 -24.0 -0.5
		
		//3rd set curve car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[19] instructor_car 3.0 -30.0 -0.5

		//3rd set curve car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[20] instructor_car 10.0 -26.0 -0.5

		//4th set curve car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[21] instructor_car 4.0 -32.0 -0.5

		//4th cone curve car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[22] instructor_car 10.0 -30.0 -0.5
	   
		//END STRAIGHT
		//1st set of end straight right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[23] instructor_car 9.0 -32.0 -0.5
	   
		//2nd set of end straight left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[24] instructor_car 4.0 -36.0 -0.5
	
		//2nd set of end straight right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[25] instructor_car 9.0 -36.0 -0.5

		//3rd set of end straight left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[26] instructor_car 4.0 -40.0 -0.5

		//3rd set of end straight right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[27] instructor_car 9.0 -40.0 -0.5

		//4th set of end straight left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[28] instructor_car 4.0 -44.0 -0.5

		//4th set of end straight right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[29] instructor_car 9.0 -44.0 -0.5

		//last cone 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[30] instructor_car 6.5 -44.0 -0.5
	
		trafficcone_counter = 15 
		WHILE trafficcone_counter < 31
			IF DOES_OBJECT_EXIST trafficcones[trafficcone_counter] 
				GET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z
				GET_GROUND_Z_FOR_3D_COORD cone_coords_x cone_coords_y 40.0 cone_coords_z  
				cone_coords_z += 0.4
				SET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z 
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	IF mission_selection = 13
		//START OF CURVE
		//1st set curve car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[15] instructor_car 3.0 -24.0 -0.5

		//1st set curve car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[16] instructor_car -6.0 -22.0 -0.5
		
		//2nd set curve car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[17] instructor_car -0.0 -28.0 -0.5

		//2nd set curve car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[18] instructor_car -8.0 -24.0 -0.5
		
		//3rd set curve car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[19] instructor_car -3.0 -30.0 -0.5

		//3rd set curve car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[20] instructor_car -10.0 -26.0 -0.5

		//4th set curve car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[21] instructor_car -4.0 -32.0 -0.5

		//4th cone curve car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[22] instructor_car -10.0 -30.0 -0.5
	   
		//END STRAIGHT
		//1st set of end straight right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[23] instructor_car -9.0 -32.0 -0.5

		//2nd set of end straight left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[24] instructor_car -4.0 -36.0 -0.5

		//2nd set of end straight right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[25] instructor_car -9.0 -36.0 -0.5

		//3rd set of end straight left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[26] instructor_car -4.0 -40.0 -0.5

		//3rd set of end straight right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[27] instructor_car -9.0 -40.0 -0.5

		//4th set of end straight left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[28] instructor_car -4.0 -44.0 -0.5

		//4th set of end straight right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[29] instructor_car -9.0 -44.0 -0.5

		//last cone 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[30] instructor_car -6.5 -44.0 -0.5

		trafficcone_counter = 15 
		WHILE trafficcone_counter < 31
			IF DOES_OBJECT_EXIST trafficcones[trafficcone_counter] 
				GET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z
				GET_GROUND_Z_FOR_3D_COORD cone_coords_x cone_coords_y 40.0 cone_coords_z  
				cone_coords_z += 0.4
				SET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z 
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	ENDIF

	//placing cones for burn and lap
	IF mission_selection = 6
	OR mission_selection = 7
		
		//AROUND CAR
		//front left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[0] instructor_car -4.0 8.0 -0.5

		//front right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[1] instructor_car 4.0 8.0 -0.5

		//left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[2] instructor_car -4.0 2.0 -0.5

		//right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[3] instructor_car 4.0 2.0 -0.5

		//back left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[4] instructor_car -4.0 -4.0 -0.5
		
		//RUNWAY IN FRONT TO CURVE
		//1st set front car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[5] instructor_car -4.0 14.0 -0.5

		//1st set front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[6] instructor_car 4.0 14.0 -0.5

		//2nd set front car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[7] instructor_car -4.0 20.0 -0.5

		//2nd set front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[8] instructor_car 4.0 20.0 -0.5

		//3rd set front car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[9] instructor_car -4.0 26.0 -0.5

		//3rd set front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[10] instructor_car 4.0 26.0 -0.5

		//4th set front car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[11] instructor_car -4.0 32.0 -0.5

		//4th set front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[12] instructor_car 4.0 32.0 -0.5

		//5th set front car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[13] instructor_car -4.0 38.0 -0.5

		//5th set front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[14] instructor_car 4.0 38.0 -0.5

		//6th set front car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[15] instructor_car -4.0 44.0 -0.5

		//6th set front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[16] instructor_car 4.0 44.0 -0.5

		//6th set curve car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[17] instructor_car -4.0 50.0 -0.5
	
		trafficcone_counter = 0 
		WHILE trafficcone_counter < 18
			IF DOES_OBJECT_EXIST trafficcones[trafficcone_counter] 
				GET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z
				GET_GROUND_Z_FOR_3D_COORD cone_coords_x cone_coords_y 40.0 cone_coords_z  
				cone_coords_z += 0.4
				SET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z 
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	
		//creating the semi circle at the end of the first straight 
		where_to_place_cones = 90.0
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 4.0 50.0 -0.5 circle_start_x circle_start_y cone_coords_z 
		GET_GROUND_Z_FOR_3D_COORD circle_start_x circle_start_y 40.0 cone_coords_z
		cone_coords_z += 0.4

		creating_cone_circle:

		SIN where_to_place_cones cone_coords_x
		cone_coords_x = cone_coords_x * cone_circle_radius
		cone_coords_x = cone_coords_x + circle_start_x
		COS where_to_place_cones cone_coords_y
		cone_coords_y = cone_coords_y * cone_circle_radius
		cone_coords_y = cone_coords_y + circle_start_y

		IF where_to_place_cones = 126.0
			SET_OBJECT_COORDINATES trafficcones[18] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 144.0
			SET_OBJECT_COORDINATES trafficcones[19] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 162.0
			SET_OBJECT_COORDINATES trafficcones[20] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 180.0
			SET_OBJECT_COORDINATES trafficcones[21] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 198.0
			SET_OBJECT_COORDINATES trafficcones[22] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 216.0
			SET_OBJECT_COORDINATES trafficcones[23] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 234.0
			SET_OBJECT_COORDINATES trafficcones[24] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF
		where_to_place_cones = where_to_place_cones + 18.0
		IF where_to_place_cones < 271.0
			GOTO creating_cone_circle
		ENDIF
	
		//Otherside of lane
		//far front right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[25] instructor_car 12.0 8.0 -0.5

		//far right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[26] instructor_car 12.0 2.0 -0.5

		//far back right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[27] instructor_car 12.0 -4.0 -0.5

		//RUNWAY IN FRONT TO CURVE otherside of lane
		//1st set far front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[28] instructor_car 12.0 14.0 -0.5

		//2nd set far front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[29] instructor_car 12.0 20.0 -0.5

		//3rd set far front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[30] instructor_car 12.0 26.0 -0.5

		//4th set far front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[31] instructor_car 12.0 32.0 -0.5

		//5th set far front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[32] instructor_car 12.0 38.0 -0.5

		//6th set far front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[33] instructor_car 12.0 44.0 -0.5

		//6th set far curve car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[34] instructor_car 12.0 50.0 -0.5
	
		trafficcone_counter = 25 
		WHILE trafficcone_counter < 35
			IF DOES_OBJECT_EXIST trafficcones[trafficcone_counter] 
				GET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z
				GET_GROUND_Z_FOR_3D_COORD cone_coords_x cone_coords_y 40.0 cone_coords_z  
				cone_coords_z += 0.4
				SET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z 
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	
		//new circle coordinates
		where_to_place_cones = 270.0
	    GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 4.0 -4.0 -0.5 circle_start_x circle_start_y cone_coords_z
	   	GET_GROUND_Z_FOR_3D_COORD circle_start_x circle_start_y 40.0 cone_coords_z
		cone_coords_z += 0.4

		//creating the 2nd semi circle at the end of the second straight
		creating_cone_circle2:

		SIN where_to_place_cones cone_coords_x
		cone_coords_x = cone_coords_x * cone_circle_radius
		cone_coords_x = cone_coords_x + circle_start_x
		COS where_to_place_cones cone_coords_y
		cone_coords_y = cone_coords_y * cone_circle_radius
		cone_coords_y = cone_coords_y + circle_start_y

		IF where_to_place_cones = 306.0
			SET_OBJECT_COORDINATES trafficcones[35] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 324.0
			SET_OBJECT_COORDINATES trafficcones[36] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 342.0
			SET_OBJECT_COORDINATES trafficcones[37] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 360.0
			SET_OBJECT_COORDINATES trafficcones[38] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 378.0
			SET_OBJECT_COORDINATES trafficcones[39] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 396.0
			SET_OBJECT_COORDINATES trafficcones[40] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 414.0
			SET_OBJECT_COORDINATES trafficcones[41] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF
		
		where_to_place_cones = where_to_place_cones + 18.0
		IF where_to_place_cones < 451.0
			GOTO creating_cone_circle2
		ENDIF
	ENDIF

	//placing cones for pop control
	IF mission_selection = 5	
		//right stinger
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[0] instructor_car 2.0 40.0 -0.5
		//left stinger
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[1] instructor_car -2.0 40.0 -0.5

		//front right of stinger
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[2] instructor_car 3.0 44.0 -0.5

		//front left of stinger
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[3] instructor_car -3.0 44.0 -0.5

		//2nd front right of stinger
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[4] instructor_car 3.0 48.0 -0.5

		//2nd front left of stinger
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[5] instructor_car -3.0 48.0 -0.5

		//3rd front right of stinger
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[6] instructor_car 3.0 52.0 -0.5
		
		//3rd front left of stinger
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[7] instructor_car -3.0 52.0 -0.5

		//4th front right of stinger
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[8] instructor_car 3.0 56.0 -0.5
		
		//4th front left of stinger
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[9] instructor_car -3.0 56.0 -0.5

		//1st curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[10] instructor_car 4.0 60.0 -0.5
		
		//1st curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[11] instructor_car -2.0 60.0 -0.5

		//2nd curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[12] instructor_car 5.0 64.0 -0.5
		
		//2nd curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[13] instructor_car -1.0 64.0 -0.5
		
		//3rd curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[14] instructor_car 6.0 68.0 -0.5
		
		//3rd curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[15] instructor_car 0.0 68.0 -0.5

		//1st straight bit of curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[16] instructor_car 6.0 72.0 -0.5
		
		//1st straight bit of curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[17] instructor_car 0.0 72.0 -0.5

		//2nd straight bit of curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[18] instructor_car 6.0 76.0 -0.5
		
		//2nd straight bit of curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[19] instructor_car 0.0 76.0 -0.5

		//3rd straight bit of curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[20] instructor_car 6.0 80.0 -0.5
		
		//3rd straight bit of curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[21] instructor_car 0.0 80.0 -0.5
		
		//1st end curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[22] instructor_car 5.0 84.0 -0.5
		
		//1st end curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[23] instructor_car -1.0 84.0 -0.5

		//2nd end curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[24] instructor_car 4.0 88.0 -0.5
		
		//2nd end curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[25] instructor_car -2.0 88.0 -0.5

		//3rd end curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[26] instructor_car 3.0 92.0 -0.5
		
		//3rd end curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[27] instructor_car -3.0 92.0 -0.5

		//1st set final right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[28] instructor_car 3.0 96.0 -0.5
		
		//1st set final left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[29] instructor_car -3.0 96.0 -0.5

		//2nd set final right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[30] instructor_car 3.0 100.0 -0.5
		
		//2nd set final left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[31] instructor_car -3.0 100.0 -0.5

		//3rd set final right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[32] instructor_car 3.0 104.0 -0.5
												   
		//3rd set final left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[33] instructor_car -3.0 104.0 -0.5

		//4th set final right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[34] instructor_car 3.0 108.0 -0.5
		
		//4th set final left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[35] instructor_car -3.0 108.0 -0.5

		//last cone
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[36] instructor_car 0.0 108.0 -0.5

		trafficcone_counter = 0 
		WHILE trafficcone_counter < 37
			IF DOES_OBJECT_EXIST trafficcones[trafficcone_counter] 
				GET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z
				GET_GROUND_Z_FOR_3D_COORD cone_coords_x cone_coords_y 40.0 cone_coords_z  
				cone_coords_z += 0.4
				SET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z 
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	ENDIF

	//placing cones for Whip and Terminate
	IF mission_selection = 3
	OR mission_selection = 4
	
		//AROUND CAR
		//front left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[0] instructor_car -4.0 4.0 -0.5

		//front right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[1] instructor_car 4.0 4.0 -0.5

		//left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[2] instructor_car -4.0 0.0 -0.5

		//right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[3] instructor_car 4.0 0.0 -0.5

		//back left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[4] instructor_car -4.0 -4.0 -0.5

		//back right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[5] instructor_car 4.0 -4.0 -0.5

		//back of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[6] instructor_car 0.0 -4.0 -0.5
		
		//RUNWAY IN FRONT TO CURVE
		//1st set front car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[7] instructor_car -4.0 8.0 -0.5
												  
		//1st set front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[8] instructor_car 4.0 8.0 -0.5

		//2nd set front car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[9] instructor_car -4.0 12.0 -0.5

		//2nd set front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[10] instructor_car 4.0 12.0 -0.5

		//3rd set front car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[11] instructor_car -4.0 16.0 -0.5

		//3rd set front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[12] instructor_car 4.0 16.0 -0.5

		//4th set front car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[13] instructor_car -4.0 20.0 -0.5

		//4th set front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[14] instructor_car 4.0 20.0 -0.5

		//5th set front car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[15] instructor_car -4.0 24.0 -0.5

		//5th set front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[16] instructor_car 4.0 24.0 -0.5

		//6th set front car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[17] instructor_car -4.0 28.0 -0.5
																				  
		//6th set front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[18] instructor_car 4.0 28.0 -0.5

		//7th set front car left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[19] instructor_car -4.0 32.0 -0.5
		
		//7th set front car right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[20] instructor_car 4.0 32.0 -0.5

		trafficcone_counter = 0 
		WHILE trafficcone_counter < 21
			IF DOES_OBJECT_EXIST trafficcones[trafficcone_counter] 
				GET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z
				GET_GROUND_Z_FOR_3D_COORD cone_coords_x cone_coords_y 40.0 cone_coords_z  
				cone_coords_z += 0.4
				SET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z 
			ENDIF
			trafficcone_counter ++
		ENDWHILE
		
		//creating the semi circle at the end of the first straight 
		where_to_place_cones = 90.0
		IF mission_selection = 3 
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car 4.0 30.0 -0.5 circle_start_x circle_start_y cone_coords_z 
		ELSE
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car -4.0 30.0 -0.5 circle_start_x circle_start_y cone_coords_z 
		ENDIF
		GET_GROUND_Z_FOR_3D_COORD circle_start_x circle_start_y 40.0 cone_coords_z
		cone_coords_z += 0.4

		creating_cone_quart_circle:

		SIN where_to_place_cones cone_coords_x
		cone_coords_x = cone_coords_x * cone_circle_radius
		cone_coords_x = cone_coords_x + circle_start_x
		COS where_to_place_cones cone_coords_y
		cone_coords_y = cone_coords_y * cone_circle_radius
		cone_coords_y = cone_coords_y + circle_start_y
	ENDIF

	IF mission_selection = 3 
		IF where_to_place_cones = 126.0
			SET_OBJECT_COORDINATES trafficcones[21] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 144.0
			SET_OBJECT_COORDINATES trafficcones[22] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 162.0
			SET_OBJECT_COORDINATES trafficcones[23] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

	ENDIF
	IF mission_selection = 4
		IF where_to_place_cones = 198.0
			SET_OBJECT_COORDINATES trafficcones[21] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 216.0
			SET_OBJECT_COORDINATES trafficcones[22] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF									  

		IF where_to_place_cones = 234.0
			SET_OBJECT_COORDINATES trafficcones[23] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF
	ENDIF
	
	IF mission_selection = 3 
	OR mission_selection = 4
		IF where_to_place_cones = 180.0
			SET_OBJECT_COORDINATES trafficcones[24] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		where_to_place_cones = where_to_place_cones + 18.0
		IF mission_selection = 3 
			IF where_to_place_cones < 181.0
				GOTO creating_cone_quart_circle
			ENDIF
		ELSE
			IF where_to_place_cones < 235.0
				GOTO creating_cone_quart_circle
			ENDIF
		ENDIF
	ENDIF
	IF mission_selection = 3 
		//1st set curve left (left as car faces stopping point)
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[25] instructor_car 8.0 40.0 -0.5
																				  
		//1st set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[26] instructor_car 8.0 32.0 -0.5

		//2nd set curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[27] instructor_car 12.0 40.0 -0.5
		
		//2nd set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[28] instructor_car 12.0 32.0 -0.5

		//3rd set curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[29] instructor_car 16.0 40.0 -0.5
	   
		//3rd set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[30] instructor_car 16.0 32.0 -0.5

		//bring them in narrower
		//4th set curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[31] instructor_car 20.0 40.0 -0.5

		//4th set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[32] instructor_car 20.0 33.0 -0.5

		//5th set curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[33] instructor_car 24.0 40.0 -0.5

		//5th set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[34] instructor_car 24.0 33.0 -0.5

		//bring them in narrower
		//6th set curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[35] instructor_car 28.0 40.0 -0.5

		//6th set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[36] instructor_car 28.0 34.0 -0.5

		//7th set curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[37] instructor_car 32.0 40.0 -0.5

		//7th set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[38] instructor_car 32.0 34.0 -0.5

		//final resting place
		//8th set curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[39] instructor_car 36.0 39.0 -0.5

		//8th set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[40] instructor_car 36.0 35.0 -0.5

		//9th set curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[41] instructor_car 40.0 39.0 -0.5

		//9th set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[42] instructor_car 40.0 35.0 -0.5

		//8th set curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[43] instructor_car 44.0 39.0 -0.5

		//8th set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[44] instructor_car 44.0 35.0 -0.5

		//end cone 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[45] instructor_car 44.0 37.0 -0.5
	
		trafficcone_counter = 25 
		WHILE trafficcone_counter < 46
			IF DOES_OBJECT_EXIST trafficcones[trafficcone_counter] 
				GET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z
				GET_GROUND_Z_FOR_3D_COORD cone_coords_x cone_coords_y 40.0 cone_coords_z  
				cone_coords_z += 0.4
				SET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z 
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	IF mission_selection = 4 
		//1st set curve left (left as car faces stopping point)
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[25] instructor_car -8.0 40.0 -0.5
																				  
		//1st set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[26] instructor_car -8.0 32.0 -0.5

		//2nd set curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[27] instructor_car -12.0 40.0 -0.5
		
		//2nd set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[28] instructor_car -12.0 32.0 -0.5

		//3rd set curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[29] instructor_car -16.0 40.0 -0.5
	   
		//3rd set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[30] instructor_car -16.0 32.0 -0.5

		//bring them in narrower
		//4th set curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[31] instructor_car -20.0 40.0 -0.5

		//4th set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[32] instructor_car -20.0 33.0 -0.5

		//5th set curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[33] instructor_car -24.0 40.0 -0.5

		//5th set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[34] instructor_car -24.0 33.0 -0.5

		//bring them in narrower
		//6th set curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[35] instructor_car -28.0 40.0 -0.5

		//6th set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[36] instructor_car -28.0 34.0 -0.5

		//7th set curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[37] instructor_car -32.0 40.0 -0.5

		//7th set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[38] instructor_car -32.0 34.0 -0.5

		//final resting place
		//8th set curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[39] instructor_car -36.0 39.0 -0.5

		//8th set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[40] instructor_car -36.0 35.0 -0.5

		//9th set curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[41] instructor_car -40.0 39.0 -0.5

		//9th set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[42] instructor_car -40.0 35.0 -0.5

		//8th set curve left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[43] instructor_car -44.0 39.0 -0.5

		//8th set curve right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[44] instructor_car -44.0 35.0 -0.5

		//end cone 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[45] instructor_car -44.0 37.0 -0.5

		trafficcone_counter = 25 
		WHILE trafficcone_counter < 46
			IF DOES_OBJECT_EXIST trafficcones[trafficcone_counter] 
				GET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z
				GET_GROUND_Z_FOR_3D_COORD cone_coords_x cone_coords_y 40.0 cone_coords_z  
				cone_coords_z += 0.4
				SET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z 
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	
	//Placing cones for Cone Coil
	IF mission_selection = 8
	//clockwise
		// 1st line 1st left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[0] instructor_car -8.0 20.0 -0.5

		// 1st line 2nd left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[1] instructor_car -6.0 20.0 -0.5

		// 1st line 3rd left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[2] instructor_car -4.0 20.0 -0.5

		// 1st line 4th left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[3] instructor_car -2.0 20.0 -0.5

		// 1st line 5th left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[4] instructor_car 0.0 20.0 -0.5

		// 2nd line 1st left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[5] instructor_car 7.0 40.0 -0.5

		// 2nd line 2nd left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[6] instructor_car 5.0 40.0 -0.5
		
		// 2nd line 3rd left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[7] instructor_car 3.0 40.0 -0.5
												  
		// 2nd line 4th left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[8] instructor_car 1.0 40.0 -0.5

		// 2nd line 5th left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[9] instructor_car -1.0 40.0 -0.5

		// 3rd line 1st left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[10] instructor_car -8.0 60.0 -0.5

		// 3rd line 2nd left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[11] instructor_car -6.0 60.0 -0.5

		// 3rd line 3rd left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[12] instructor_car -4.0 60.0 -0.5

		// 3rd line 4th left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[13] instructor_car -2.0 60.0 -0.5

		// 3rd line 5th left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[14] instructor_car 0.0 60.0 -0.5
		/*
		// 4th line 1st left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[15] instructor_car 6.0 80.0 -0.5

		// 4th line 2nd left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[16] instructor_car 4.0 80.0 -0.5

		// 4th line 3rd left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[17] instructor_car 2.0 80.0 -0.5

		// 4th line 4th left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[18] instructor_car 0.0 80.0 -0.5

		// 4th line 5th left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[19] instructor_car -2.0 80.0 -0.5
		*/

		trafficcone_counter = 0 
		WHILE trafficcone_counter < 15
			IF DOES_OBJECT_EXIST trafficcones[trafficcone_counter] 
				GET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z
				GET_GROUND_Z_FOR_3D_COORD cone_coords_x cone_coords_y 40.0 cone_coords_z  
				cone_coords_z += 0.4
				SET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z 
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	IF mission_selection = 9
	//anti-clockwise
		// 1st line 1st left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[0] instructor_car 7.0 20.0 -0.5

		// 1st line 2nd left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[1] instructor_car 5.0 20.0 -0.5

		// 1st line 3rd left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[2] instructor_car 3.0 20.0 -0.5

		// 1st line 4th left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[3] instructor_car 1.0 20.0 -0.5

		// 1st line 5th left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[4] instructor_car -1.0 20.0 -0.5

		// 2nd line 1st left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[5] instructor_car -8.0 40.0 -0.5

		// 2nd line 2nd left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[6] instructor_car -6.0 40.0 -0.5
		
		// 2nd line 3rd left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[7] instructor_car -4.0 40.0 -0.5
												  
		// 2nd line 4th left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[8] instructor_car -2.0 40.0 -0.5

		// 2nd line 5th left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[9] instructor_car 0.0 40.0 -0.5

		// 3rd line 1st left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[10] instructor_car 7.0 60.0 -0.5

		// 3rd line 2nd left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[11] instructor_car 5.0 60.0 -0.5

		// 3rd line 3rd left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[12] instructor_car 3.0 60.0 -0.5

		// 3rd line 4th left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[13] instructor_car 1.0 60.0 -0.5

		// 3rd line 5th left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[14] instructor_car -1.0 60.0 -0.5
		/*
		// 4th line 1st left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[15] instructor_car -6.0 80.0 -0.5

		// 4th line 2nd left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[16] instructor_car -4.0 80.0 -0.5

		// 4th line 3rd left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[17] instructor_car -2.0 80.0 -0.5

		// 4th line 4th left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[18] instructor_car 0.0 80.0 -0.5

		// 4th line 5th left 
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[19] instructor_car 2.0 80.0 -0.5
		*/
	
		trafficcone_counter = 0 
		WHILE trafficcone_counter < 15
			IF DOES_OBJECT_EXIST trafficcones[trafficcone_counter] 
				GET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z
				GET_GROUND_Z_FOR_3D_COORD cone_coords_x cone_coords_y 40.0 cone_coords_z  
				cone_coords_z += 0.4
				SET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z 
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	ENDIF

	IF mission_selection = 8
	OR mission_selection = 9
		//creating the semi circle at the end of the straight 
		cone_circle_radius = 8.0
		where_to_place_cones = 90.0
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_car -0.5 81.0 -0.5 circle_start_x circle_start_y cone_coords_z 
		GET_GROUND_Z_FOR_3D_COORD circle_start_x circle_start_y 40.0 cone_coords_z
		cone_coords_z += 0.4

		creating_cone_circle5:

		SIN where_to_place_cones cone_coords_x
		cone_coords_x = cone_coords_x * cone_circle_radius
		cone_coords_x = cone_coords_x + circle_start_x
		COS where_to_place_cones cone_coords_y
		cone_coords_y = cone_coords_y * cone_circle_radius
		cone_coords_y = cone_coords_y + circle_start_y

		IF where_to_place_cones = 90.0
			SET_OBJECT_COORDINATES trafficcones[23] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 120.0
			SET_OBJECT_COORDINATES trafficcones[24] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 150.0
			SET_OBJECT_COORDINATES trafficcones[25] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 180.0
			SET_OBJECT_COORDINATES trafficcones[26] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 210.0
			SET_OBJECT_COORDINATES trafficcones[27] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 240.0
			SET_OBJECT_COORDINATES trafficcones[28] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF

		IF where_to_place_cones = 270.0
			SET_OBJECT_COORDINATES trafficcones[29] cone_coords_x cone_coords_y cone_coords_z 
		ENDIF


		where_to_place_cones = where_to_place_cones + 30.0
		IF where_to_place_cones < 271.0
			GOTO creating_cone_circle5
		ENDIF
	
		//Last cone
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[15] instructor_car 0.0 80.0 -0.5

		//AROUND CAR
		//front left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[16] instructor_car -3.0 4.0 -0.5

		//front right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[17] instructor_car 3.0 4.0 -0.5

		//left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[18] instructor_car -3.0 0.0 -0.5

		//right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[19] instructor_car 3.0 0.0 -0.5

		//back left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[20] instructor_car -3.0 -4.0 -0.5

		//back right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[21] instructor_car 3.0 -4.0 -0.5

		//back of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[22] instructor_car 0.0 -4.0 -0.5
	
		trafficcone_counter = 15 
		WHILE trafficcone_counter < 23
			IF DOES_OBJECT_EXIST trafficcones[trafficcone_counter] 
				GET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z
				GET_GROUND_Z_FOR_3D_COORD cone_coords_x cone_coords_y 40.0 cone_coords_z  
				cone_coords_z += 0.4
				SET_OBJECT_COORDINATES trafficcones[trafficcone_counter] cone_coords_x cone_coords_y cone_coords_z 
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	ENDIF
RETURN/////////////////////////////////////////////////////////////////////////////////////

f1_overall_dialogue:///////////////////////////////////////////////////////////////////////
IF f1_speech_goals = 1 //cutscene dialogue
	IF f1_speech_control_flag < f1_last_label
		GOSUB f1_loading_dialogue
		GOSUB f1_playing_dialogue
		GOSUB f1_finishing_dialogue  
	ELSE
		IF NOT IS_CHAR_DEAD f1_secretary 
			STOP_CHAR_FACIAL_TALK f1_secretary
		ENDIF
		f1_speech_goals = 0
	ENDIF
ENDIF
RETURN/////////////////////////////////////////////////////////////////////////////////////

f1_dialogue_setup://////////////////////////////////////////////////////////
IF f1_speech_goals = 1
	$f1_print_label[0] = &MEC_D1 // Welcome to the Advanced Driving School
	$f1_print_label[1] = &MEC_D2 // To pass you must score BRONZE or higher in all 11 tests.		
	$f1_print_label[2] = &MEC_D3 // To view a demonstration of each test, please use the TV over there.
	$f1_print_label[3] = &MEC_D4 // Passing a test will unlock the next test in the training program.
	$f1_print_label[4] = &MEC_D5 // You can come back and check your scores or take new tests at any time.

	f1_audio_label[0] = SOUND_MEC_D1 
	f1_audio_label[1] = SOUND_MEC_D2 
	f1_audio_label[2] = SOUND_MEC_D3 
	f1_audio_label[3] = SOUND_MEC_D4 
	f1_audio_label[4] = SOUND_MEC_D5 
	f1_last_label = 5
ENDIF

f1_slot_load = f1_speech_control_flag
f1_slot1 = 0
f1_slot2 = 0
f1_play_which_slot = 1
RETURN/////////////////////////////////////////////////////////////////////////////////////


f1_loading_dialogue:////////////////////////////////////////////////////////
IF f1_slot_load < f1_last_label 
	//slot 1
	IF f1_slot1 = 0
		LOAD_MISSION_AUDIO 1 f1_audio_label[f1_slot_load] 
		f1_slot_load ++ 
		f1_slot1 = 1
	ENDIF

	//slot 2		    
	IF f1_slot2 = 0
		LOAD_MISSION_AUDIO 2 f1_audio_label[f1_slot_load] 
		f1_slot_load ++ 
		f1_slot2 = 1
	ENDIF
ENDIF
RETURN//////////////////////////////////////////////////////////////////////


f1_playing_dialogue:////////////////////////////////////////////////////////
//slot 1
IF f1_play_which_slot = 1 
	IF f1_slot1 = 1
		IF HAS_MISSION_AUDIO_LOADED 1	 
			PLAY_MISSION_AUDIO 1
			PRINT_NOW ( $f1_print_label[f1_speech_control_flag] ) 4500 1 
			f1_slot1 = 2
		ENDIF
	ENDIF
ENDIF

//slot 2
IF f1_play_which_slot = 2 
	IF f1_slot2 = 1
		IF HAS_MISSION_AUDIO_LOADED 2	 
			PLAY_MISSION_AUDIO 2
			PRINT_NOW ( $f1_print_label[f1_speech_control_flag] ) 4500 1 
			f1_slot2 = 2
		ENDIF
	ENDIF
ENDIF
RETURN//////////////////////////////////////////////////////////////////////

f1_finishing_dialogue://////////////////////////////////////////////////////
//slot 1
IF f1_slot1 = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		CLEAR_THIS_PRINT $f1_print_label[f1_speech_control_flag]
		f1_speech_control_flag ++		
		f1_play_which_slot = 2
		f1_slot1 = 0
	ENDIF
ENDIF

//slot 2
IF f1_slot2 = 2
	IF HAS_MISSION_AUDIO_FINISHED 2
		CLEAR_THIS_PRINT $f1_print_label[f1_speech_control_flag]
		f1_speech_control_flag ++		
		f1_play_which_slot = 1
		f1_slot2 = 0
	ENDIF
ENDIF
RETURN//////////////////////////////////////////////////////////////////////
}
