MISSION_START

// ------------------------------------------------------------------------------------------------
// Bike School: Las Vegas

{

SCRIPT_NAME bskool

// Mission start stuff
GOSUB mission_start_bskool

	IF HAS_DEATHARREST_BEEN_EXECUTED 
		GOSUB mission_failed_bskool
	ENDIF

GOSUB mission_cleanup_bskool							  

MISSION_END
 
// ------------------------------------------------------------------------------------------------
// Initialize Mission...

mission_start_bskool:
	
	CLEAR_THIS_PRINT M_FAIL
	IF flag_bikeschool_passed_1stime = 0
    	REGISTER_MISSION_GIVEN
	ENDIF

	flag_player_on_mission = 1
	

	LOAD_MISSION_TEXT BS

	SET_FADING_COLOUR 0 0 0
	
	WAIT 0

// ---- Impossible 'IF'... 
	IF flag_player_on_mission = 0
		CREATE_CAR BF400 car_posx car_posy perfect_positionz instructor_bike
		CREATE_CHAR_INSIDE_CAR instructor_bike PEDTYPE_CIVFEMALE WMYMECH bs_instructor
		CREATE_OBJECT_NO_OFFSET trafficcone 0.0 0.0 100.0 trafficcones[20]
		CREATE_OBJECT_NO_OFFSET trafficcone 0.0 0.0 100.0 trafficcones[30]
		CREATE_OBJECT landjump2 car_posx car_posy perfect_positionz bs_ramp
	ENDIF

// ------------------------------------------------------------------------------------------------
// Set Flags & Vars

//Boat School-  all gold, all silver, all bronze reward vars

LVAR_INT playback_flag /*pause_flag*/ playback_selection


LVAR_INT bs_blip_flag  bs_last_played

LVAR_INT instructor_bike instructor_bike_heading_int instructor_bike_health	total_instructor_bike_health
LVAR_FLOAT instructor_bike_heading instructor_bike_speed instructor_bike_follow_heading
LVAR_FLOAT instructor_bike_roll 
LVAR_INT instructor_bike_roll_score instructor_bike_roll_plus_minus 
LVAR_INT instructor_bike_dead_flag
LVAR_INT bs_instructor
LVAR_INT bs_wanted sfx_video
LVAR_INT bs_r bs_g bs_b bs_a

LVAR_INT bs_ramp

LVAR_INT bs_control_flag bs_direction_flag 
LVAR_INT bs_print_top_scores_flag

LVAR_INT bs_checkpoint
 
LVAR_INT test_timer pausing_flag
LVAR_INT bs_fade_flag bs_alpha
LVAR_INT bs_which_medal_displayed bs_which_score_displayed
LVAR_INT bs_old_score 

LVAR_INT bs_timer_diff bs_timer_end bs_timer_start

// To be added to initial.sc


playback_flag = 0   ///needs to be set to 0
test_timer = 0
pausing_flag = 0
bs_control_flag = 0
bs_fade_flag = 0
bs_alpha = 0
bs_which_medal_displayed = 0
bs_which_score_displayed = 0
bs_old_score = 0
sfx_video = 0

IF bs_open_tests = 0
	bs_open_tests = 1
ENDIF

mission_selection = 1  //needs to be set to 1
start_coordsx = 1130.44 //Set my own starting position ********************** 
start_coordsy = 1341.0
 
 

noticeboard_x = 1173.9303 // Set my own noticeboard **********************  
noticeboard_y = 1351.0663 
noticeboard_z = 9.9219

SET_CHAR_COORDINATES scplayer 1171.5206 1351.1826 9.9219


// ------------------------------------------------------------------------------------------------
// Request Models...

REQUEST_MODEL BF400
REQUEST_MODEL FCR900
REQUEST_MODEL PCJ600
REQUEST_MODEL trafficcone
REQUEST_MODEL landjump2

REQUEST_MODEL NRG500

REQUEST_MODEL SANCHEZ
REQUEST_MODEL WMYMECH

WHILE NOT HAS_MODEL_LOADED BF400
OR NOT HAS_MODEL_LOADED FCR900
OR NOT HAS_MODEL_LOADED PCJ600
OR NOT HAS_MODEL_LOADED trafficcone 
OR NOT HAS_MODEL_LOADED landjump2 
	WAIT 0
ENDWHILE


WHILE NOT HAS_MODEL_LOADED SANCHEZ
OR NOT HAS_MODEL_LOADED WMYMECH
OR NOT HAS_MODEL_LOADED NRG500
	WAIT 0
ENDWHILE

// ------------------------------------------------------------------------------------------------
// Load GUI Art...

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


// ------------------------------------------------------------------------------------------------
// Pre Selection Stuff...
//DO_FADE 500 FADE_IN
	//WHILE GET_FADING_STATUS
		//WAIT 0
	//ENDWHILE
	SET_PLAYER_CONTROL player1 ON
//PRINT_NOW ( BS_X_1 ) 3000 1 //Walk into the locate to access the television. 
//ADD_BLIP_FOR_COORD noticeboard_x noticeboard_y noticeboard_z bs_blip_flag 
DISPLAY_HUD FALSE
GOTO bs_noticeboard_setup

// ------------------------------------------------------------------------------------------------
// Setup The Noticeboard...
bs_noticeboard_setup:
	//clearing old shit away
	SET_PLAYER_CONTROL player1 OFF
	CLEAR_HELP
	CLEAR_PRINTS
//	SET_CAR_DENSITY_MULTIPLIER 0.0
//	SET_PED_DENSITY_MULTIPLIER 0.0
	STORE_WANTED_LEVEL player1 bs_wanted
	CLEAR_WANTED_LEVEL player1

	//preparing for noticeboard
	GOSUB bs_setting_up_variables
	playback_flag = 0
	bs_control_flag = 0
	USE_TEXT_COMMANDS TRUE

	bs_alpha = 255
	bs_fade_flag = 2


	IF bs_open_tests < 7 
		mission_selection = bs_last_played
	ENDIF


// ------------------------------------------------------------------------------------------------
// Draw Mission Select GUI...

	DO_FADE 500 FADE_IN

	WHILE GET_FADING_STATUS
		CLEAR_PRINTS
		GOSUB bs_draw_tv_screen
		WAIT 0
	ENDWHILE

	GOSUB bs_draw_tv_screen
	DISPLAY_RADAR FALSE

// ------------------------------------------------------------------------------------------------
// Using Mission Select GUI...
bs_mission_selection_loop:
		WAIT 0

		GOSUB bs_draw_tv_screen

		IF sfx_video = 0
			LOAD_MISSION_AUDIO 3 SOUND_VIDEOTAPE_NOISE
			sfx_video = 1
		ENDIF

		IF sfx_video = 1
			IF HAS_MISSION_AUDIO_LOADED 3
	 			sfx_video = 2
			ENDIF
		ENDIF
		
		IF sfx_video = 2 
		   PLAY_MISSION_AUDIO 3
		   sfx_video = 3
		ENDIF
		
		IF bs_control_flag = 0 
			GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
			
			IF LStickX < -100
			OR IS_BUTTON_PRESSED PAD1 DPADLEFT
				bs_last_played = bs_last_played - 1
				mission_selection = mission_selection - 1

				IF bs_open_tests > 1 
					IF playback_flag < 4 
						playback_flag = 3 //overriding playback
					ENDIF
				ENDIF	
			ENDIF

			IF LStickX > 100
			OR IS_BUTTON_PRESSED PAD1 DPADRIGHT
				bs_last_played = bs_last_played + 1
				mission_selection = mission_selection + 1

				IF bs_open_tests > 1 
					IF playback_flag < 4
						playback_flag = 3 //overriding playback	
					ENDIF
				ENDIF
			ENDIF								

			IF mission_selection < 1
				mission_selection = bs_open_tests
				bs_last_played = bs_open_tests
			ENDIF

			IF mission_selection > bs_open_tests
				bs_last_played = 1
				mission_selection = 1
			ENDIF
		
			WHILE IS_BUTTON_PRESSED PAD1 DPADLEFT

				WAIT 0 
                GOSUB bs_draw_tv_screen
			ENDWHILE 
		
			WHILE IS_BUTTON_PRESSED PAD1 DPADRIGHT

				WAIT 0 
                GOSUB bs_draw_tv_screen
			ENDWHILE 

			WHILE LStickX < -100  

				WAIT 0 
                GOSUB bs_draw_tv_screen
                GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
			ENDWHILE 
			 
			WHILE LStickX > 100  

				WAIT 0 
                GOSUB bs_draw_tv_screen
                GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
			ENDWHILE
		
			//triggering mission
			IF IS_BUTTON_PRESSED PAD1 CROSS
				IF playback_flag < 4
					playback_flag = 3
				ENDIF
				CLEAR_MISSION_AUDIO 3
				sfx_video = 0
				GOTO bs_start_mission
			ENDIF
		ENDIF

		//opening all the missions
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Q
			bs_open_tests = 6
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_5
			bs_open_tests = 6
			bs_wheelie_best_score = 100 
			bs_stoppie_best_score = 100
			bs_the360_best_score = 100 
			bs_the180_best_score = 100
			bs_jumpstop_best_score = 100 
			bs_jumpstoppie_best_score = 100
		ENDIF

		IF bs_wheelie_best_score > 69 
		AND bs_stoppie_best_score > 69
		AND bs_the360_best_score > 69 
		AND bs_the180_best_score > 69
		AND bs_jumpstop_best_score > 69 
		AND bs_jumpstoppie_best_score > 69
			bs_passed = 1
		ENDIF

		IF flag_bikeschool_passed_1stime = 0
			IF bs_passed = 1
				GOSUB bs_mini_cleanup
				GOTO mission_passed_bs
			ENDIF
		ENDIF

		//quitting the driving school
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			CLEAR_MISSION_AUDIO 3
			sfx_video = 0 
			GOTO mission_failed_bskool
		ENDIF

	GOSUB bs_watching_demo

GOTO bs_mission_selection_loop

// ------------------------------------------------------------------------------------------------
// Start the Tests...

bs_start_mission:
	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
		GOSUB bs_draw_tv_screen
		WAIT 0
	ENDWHILE 

	IF NOT IS_CAR_DEAD instructor_bike 
		STOP_PLAYBACK_RECORDED_CAR instructor_bike
	ENDIF

	DELETE_CAR instructor_bike
	DELETE_CHAR bs_instructor
	CLEAR_AREA noticeboard_x noticeboard_y noticeboard_z 300.0 TRUE
	CLEAR_ONSCREEN_TIMER car_timer
	FREEZE_ONSCREEN_TIMER FALSE
//	DELETE_CAR dummy_car1
//	DELETE_CAR dummy_car2
	DELETE_CAR instructor_bike
//	DELETE_OBJECT ramp1
//	DELETE_OBJECT ramp2

	GOSUB bs_deleting_cones // Need my own section for this????????????????????

	playback_flag = 5 ///making sure playback flag doesn't fuck up missions
	 
	DISPLAY_RADAR TRUE


// ------------------------------------------------------------------------------------------------
// The 360...

IF mission_selection = 1 

	bs_refresh_360:

	GOSUB bs_start_initialise_stuff

	bs_setup_360:

	perfect_heading = 180.0
	car_timer = 16000
	playback_selection = 181

	GOSUB bs_setting_up_variables
	
	//creating cars
	CREATE_CAR BF400 perfect_positionx perfect_positiony perfect_positionz instructor_bike
	SET_CAR_HEADING instructor_bike 180.0

	GOSUB bs_creating_cones
	
	IF playback_flag = 0
			IF NOT IS_CAR_DEAD instructor_bike
				CREATE_CHAR_INSIDE_CAR instructor_bike PEDTYPE_CIVFEMALE WMYMECH bs_instructor
			ENDIF
			SET_FIXED_CAMERA_POSITION 1125.66 1334.4 22.5 0.0 0.0 0.0
			POINT_CAMERA_AT_CAR instructor_bike FIXED JUMP_CUT
		RETURN
	ENDIF

	//setting up finishing point
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike 0.0 0.0 0.0 finish_rightx finish_righty perfect_positionz  

	CLEAR_PRINTS

	PRINT_NOW BS_A_1 5000 4 

	GOSUB bs_stop_initialise_stuff

	//starting challenge
	bs_the_360_loop:
	WAIT 0
		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_bike_dead_flag = 2
			GOTO bs_after_scores_360																		  
		ENDIF 
   

		//checking car isnt dead
		GOSUB instructor_bike_dead
		IF instructor_bike_dead_flag = 1 
			GOTO mission_failed_bskool
		ENDIF

		//checking car has moved and player is pressing cross or square
		IF car_started = 0

			PRINT_NOW BS_A_1 5000 4 
			
			GOSUB bs_has_car_started

		//player is going
		ELSE
			IF NOT IS_CAR_DEAD instructor_bike
				
				//checking timer hasnt ran to 0
				GOSUB bs_car_timer_0

				//checking how far through the circle a player is
				GET_CAR_HEADING instructor_bike perfect_heading 
				//clockwise
				IF variablec = 0
					IF perfect_heading > 80.0 
						IF perfect_heading < 90.0 
							variablec = 1
							bs_direction_flag = 1
						ENDIF
					ENDIF
				ENDIF
				IF bs_direction_flag = 1
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
							bs_direction_flag = 2
							variablec = 1
						ENDIF
					ENDIF
				ENDIF
				IF bs_direction_flag = 2
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
				OR NOT IS_CHAR_IN_CAR scplayer instructor_bike
				OR car_timer = 0

					GOSUB bs_freeze_car_pos
				
					//CALCULATIONS FOR PLAYER SCORE
					 
					//position score 
					GOSUB bs_position_score_calcs					 

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

					//losing points for hitting cones 
					GOSUB bs_damage_cones_calcs

					//checking overall score is greater than 0 and clearing prints
					GOSUB bs_checking_overall_score

					//checking overall score against the best score at present
					IF overall_score > bs_the360_best_score  	
						bs_old_score = bs_the360_best_score 
						bs_the360_best_score = overall_score
						bs_print_top_scores_flag = 1
						GOSUB bs_medal_check
						IF bs_which_medal_displayed = 4 // gold
							IF bs_the360_goldachieved = 0
								bs_the360_goldachieved = 1
								bs_the360_silverachieved = 1
								bs_the360_bronzeachieved = 1
							ENDIF
						ENDIF

						IF bs_which_medal_displayed = 3 // silver
							IF bs_the360_silverachieved = 0
								bs_the360_silverachieved = 1
								bs_the360_bronzeachieved = 1
							ENDIF
						ENDIF
						
						IF bs_which_medal_displayed = 2 //bronze
						   IF bs_the360_bronzeachieved = 0
								bs_the360_bronzeachieved = 1
							ENDIF
						ENDIF
					ELSE
						bs_which_medal_displayed = 0	
					ENDIF 	
				
					//opening next level
					IF bs_open_tests = 1
						IF overall_score > 69
							bs_print_top_scores_flag = 2
							bs_open_tests = 2
							instructor_bike_dead_flag = 2
							bs_last_played ++
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BIKE_AWARD_TRACK_START
						ENDIF
					ENDIF

					//printing scores onscreen
					timera = 0 
					WHILE timera > -1
						
						WAIT 0												    

						//changing camera position 
						GOSUB bs_setting_up_camera			

						//checking player hasnt left car
						IF NOT bs_print_top_scores_flag = 2
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								instructor_bike_dead_flag = 2
								GOTO bs_after_scores_360																		  
							ENDIF 
						ENDIF
				    						
						//displaying scores
						GOSUB bs_display_head_pos_dam_text				   

						//checking if player has skipped watching the scores
						GOSUB bs_skip_scores
						IF finished_watching_scores = 1
							GOTO bs_after_scores_360
						ENDIF
					ENDWHILE											    

					//reseting for another try
					bs_after_scores_360:

					GOSUB bs_mini_cleanup
					GOSUB bs_deleting_cones
																		  
					IF instructor_bike_dead_flag = 2 
						GOTO bs_noticeboard_setup
					ELSE
						GOTO bs_refresh_360 
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
	GOTO bs_the_360_loop 
ENDIF

// ------------------------------------------------------------------------------------------------
// The 180...

IF mission_selection = 2 

	bs_refresh_180:

	GOSUB bs_start_initialise_stuff

	bs_setup_180:
			 
	perfect_heading = 0.0
	car_timer = 11000
	playback_selection = 182

	GOSUB bs_setting_up_variables

	//creating cars
	CREATE_CAR PCJ600 perfect_positionx perfect_positiony perfect_positionz instructor_bike
	SET_CAR_HEADING instructor_bike 180.0

	//creating cones
	GOSUB bs_creating_cones
	
	IF playback_flag = 0
		IF NOT IS_CAR_DEAD instructor_bike
			CREATE_CHAR_INSIDE_CAR instructor_bike PEDTYPE_CIVFEMALE WMYMECH bs_instructor
		ENDIF
		SET_FIXED_CAMERA_POSITION 1130.66 1264.4 14.5 0.0 0.0 0.0
		POINT_CAMERA_AT_CAR instructor_bike FIXED JUMP_CUT
		RETURN
	ENDIF

	//setting up the point the player MUST cross
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike -2.0 82.0 0.0 area_check1ax area_check1ay player_z 
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike 2.0 88.0 0.0 area_check1bx area_check1by player_z 

	//setting up finishing point
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike 0.0 0.0 0.0 finish_rightx finish_righty perfect_positionz  
	CLEAR_PRINTS

	PRINT_NOW BS_B_1 5000 4 // ~s~To do a 180, accelerate to top speed, press the R1 button to slide around the cone and then return. 
	GOSUB bs_stop_initialise_stuff

	//starting challenge
	bs_the_180_loop:
	WAIT 0
		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_bike_dead_flag = 2
			GOTO bs_after_scores_180																		  
		ENDIF 

		//checking car has moved and player is pressing cross or square
		IF car_started = 0
			PRINT_NOW BS_B_1 5000 4  // ~s~To do a 180, accelerate to top speed, press the R1 button to slide around the cone and then return. 
			GOSUB bs_has_car_started
		//player is going
		ELSE
			IF NOT IS_CAR_DEAD instructor_bike
				
				//checking timer hasnt ran to 0
				GOSUB bs_car_timer_0

				//checking player goes round a lap
				IF variablec = 0 
					IF IS_CAR_IN_AREA_2D instructor_bike area_check1ax area_check1ay area_check1bx area_check1by FALSE
						variablec = 1
					ENDIF
				ENDIF	  
			
			
				//checking car is stopped or not
				IF IS_CAR_STOPPED instructor_bike
				OR NOT IS_CHAR_IN_CAR scplayer instructor_bike

					GOSUB bs_freeze_car_pos
				
					//CALCULATIONS FOR PLAYER SCORE
					 
					//position score 
					GOSUB bs_position_score_calcs					 
					IF variablec = 0
						position_score = 0
					ENDIF	 

					//heading - perfect heading is 0
					GET_CAR_HEADING instructor_bike instructor_bike_heading
					instructor_bike_heading_int =# instructor_bike_heading
					
					IF instructor_bike_heading_int = 0
						heading_score = 100
						GOTO bs_done_heading_calcs_180
					ENDIF 
					IF instructor_bike_heading_int = 360
						heading_score = 100
						GOTO bs_done_heading_calcs_180
					ENDIF 

					IF instructor_bike_heading_int > 0
						IF instructor_bike_heading_int < 21
							heading_score = 100
						ENDIF
					ENDIF 
				
					IF instructor_bike_heading_int > 179
						IF instructor_bike_heading_int < 340
							instructor_bike_heading_int = instructor_bike_heading_int - 180
							variableb =# instructor_bike_heading_int
							variableb *= 0.62 
					   		heading_score =# variableb 
							GOTO bs_done_heading_calcs_180
						ENDIF
					ENDIF  

					IF instructor_bike_heading_int > 339
						IF instructor_bike_heading_int < 360
							heading_score = 100
						ENDIF
					ENDIF 
					
					IF instructor_bike_heading_int > 20
						IF instructor_bike_heading_int < 180
							variableb =# instructor_bike_heading_int
							variableb *= 0.62 //0.62 = 99(which is highest score possible) / 60 ( which is 180 - 20)
							variablea =# variableb
						 	heading_score = 100 - variablea 
							GOTO bs_done_heading_calcs_180
						ENDIF
					ENDIF  
					
					bs_done_heading_calcs_180:
					IF heading_score < 1
						heading_score = 0
					ENDIF 

					overall_score = position_score + heading_score				
					overall_score /= 2  
					IF position_score = 0
						overall_score = 0
					ENDIF	 

					//losing points for hitting cones 
					GOSUB bs_damage_cones_calcs

					//checking overall score is greater than 0 and clearing prints
					GOSUB bs_checking_overall_score

					//checking overall score against the best score at present
					IF overall_score > bs_the180_best_score   	
						bs_old_score = bs_the180_best_score 
						bs_the180_best_score = overall_score
						bs_print_top_scores_flag = 1
						GOSUB bs_medal_check
						IF bs_which_medal_displayed = 4 // gold
							IF bs_the180_goldachieved = 0
								bs_the180_goldachieved = 1
								bs_the180_silverachieved = 1
								bs_the180_bronzeachieved = 1
							ENDIF
						ENDIF

						IF bs_which_medal_displayed = 3 // silver
							IF bs_the180_silverachieved = 0
								bs_the180_silverachieved = 1
								bs_the180_bronzeachieved = 1
							ENDIF
						ENDIF
						
						IF bs_which_medal_displayed = 2 //bronze
						   IF bs_the180_bronzeachieved = 0
								bs_the180_bronzeachieved = 1
							ENDIF
						ENDIF

					ELSE
						bs_which_medal_displayed = 0	
					ENDIF 	

					//opening next level
					IF bs_open_tests = 2
						IF overall_score > 69
							bs_print_top_scores_flag = 2
							bs_open_tests = 3
							instructor_bike_dead_flag = 2
							bs_last_played ++
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BIKE_AWARD_TRACK_START
						ENDIF
					ENDIF

					//printing scores onscreen
					timera = 0 
					WHILE timera > -1
						
						WAIT 0

						//changing camera position 
						GOSUB bs_setting_up_camera			

						//checking player hasnt left car
						IF NOT bs_print_top_scores_flag = 2
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								instructor_bike_dead_flag = 2
								GOTO bs_after_scores_180																		  
							ENDIF
						ENDIF
   					
						//displaying scores
						GOSUB bs_display_head_pos_dam_text

						//checking if player has skipped watching the scores
						GOSUB bs_skip_scores
						IF finished_watching_scores = 1
							GOTO bs_after_scores_180
						ENDIF
					ENDWHILE

					//reseting for another try
					bs_after_scores_180:
					GOSUB bs_mini_cleanup
					 
					GOSUB bs_deleting_cones

					IF instructor_bike_dead_flag = 2 
						GOTO bs_noticeboard_setup
					ELSE
						GOTO bs_refresh_180 
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
	GOTO bs_the_180_loop 
ENDIF

// ------------------------------------------------------------------------------------------------
// The Wheelie
IF mission_selection = 3

	bs_refresh_wheelie:
	GOSUB bs_start_initialise_stuff

	bs_setup_wheelie:

	//////setting up variables for this specific challenge/////
	perfect_heading = 180.0
	car_timer = 9000
	playback_selection = 183 

	GOSUB bs_setting_up_variables

	//creating cars
	CREATE_CAR FCR900 perfect_positionx perfect_positiony perfect_positionz instructor_bike
	SET_CAR_HEADING instructor_bike 180.0

	//creating cones
	GOSUB bs_creating_cones
  	
	// Demo Playback Camera
	IF playback_flag = 0
		IF NOT IS_CAR_DEAD instructor_bike
			CREATE_CHAR_INSIDE_CAR instructor_bike PEDTYPE_CIVFEMALE WMYMECH bs_instructor
		ENDIF
		SET_FIXED_CAMERA_POSITION 1125.66 1283.4 10.5 0.0 0.0 0.0
		POINT_CAMERA_AT_CAR instructor_bike FIXED JUMP_CUT
		RETURN
	ENDIF
	
	//setting up area check
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike -3.5 56.0 0.0 area_check1ax area_check1ay player_z 
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike 3.5 84.0 0.0 area_check1bx area_check1by player_z

	//setting up finishing point
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike 0.0 124.0 0.0 finish_rightx finish_righty perfect_positionz  
	

	CLEAR_PRINTS
	
	PRINT_NOW BS_C_1 5000 4  
 
	GOSUB bs_stop_initialise_stuff

	bs_wheelie_loop:
	WAIT 0 
	
		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_bike_dead_flag = 2
			GOTO bs_after_scores_wheelie																		  
		ENDIF 
	
		//checking car has moved and player is pressing cross or square
		IF car_started = 0

		    PRINT_NOW BS_C_1 5000 4  // Do a Wheelie along the coned section. Accelerate to top speed, then double tap and hold the accelerator.

			GOSUB bs_has_car_started	

		//player is going
		ELSE
			IF NOT IS_CAR_DEAD instructor_bike
				
				//checking timer hasnt ran to 0
				GOSUB bs_car_timer_0

				// Makes sure player is in area
				IF variablec = 0 
					IF IS_CAR_IN_AREA_2D instructor_bike area_check1ax area_check1ay area_check1bx area_check1by FALSE
						variablec = 1
					ENDIF
				ENDIF
				IF variablec = 1
					IF NOT IS_CAR_IN_AREA_2D instructor_bike area_check1ax area_check1ay area_check1bx area_check1by FALSE
						GET_CHAR_COORDINATES scplayer player_x player_y player_z
						IF player_x > area_check1ax
						OR player_x < area_check1bx
						OR player_y > area_check1ay
							GOSUB bs_freeze_car_pos
						ELSE
							variablec = 2
						ENDIF
					ENDIF
					IF NOT IS_PLAYER_PERFORMING_WHEELIE player1
						GOSUB bs_freeze_car_pos
					ENDIF 
				ENDIF
	


				// Follow Player's heading 
				GET_CAR_HEADING instructor_bike instructor_bike_heading
				instructor_bike_follow_heading = instructor_bike_heading
				IF NOT instructor_bike_follow_heading = 0.0
					IF instructor_bike_follow_heading < 360.0
					AND instructor_bike_follow_heading > 180.0 
						instructor_bike_follow_heading += 0.1	
					ENDIF
					IF instructor_bike_follow_heading = 360.0
						instructor_bike_follow_heading > 0.0
					ENDIF
					IF instructor_bike_follow_heading <= 180.0
					AND instructor_bike_follow_heading > 0.0 
						instructor_bike_follow_heading -= 0.1	
					ENDIF
				ENDIF
				
				//checking car is stopped or not
				IF IS_CAR_STOPPED instructor_bike
				OR NOT IS_CHAR_IN_CAR scplayer instructor_bike
					GOSUB bs_freeze_car_pos
					
					perfect_positionx = finish_rightx
					perfect_positiony = finish_righty
					
					IF variablec > 0 
						GOSUB bs_position_score_calcs
					ELSE
						position_score = 0
					ENDIF				
					
					//heading - perfect heading is 0
					GET_CAR_HEADING instructor_bike instructor_bike_heading
					instructor_bike_heading_int =# instructor_bike_heading

					IF variablec > 0
						IF instructor_bike_heading_int = 0
							heading_score = 0
							GOTO bs_done_heading_calcs_wheelie
						ENDIF 
						IF instructor_bike_heading_int = 180
							heading_score = 100
							GOTO bs_done_heading_calcs_wheelie
						ENDIF 
						IF instructor_bike_heading_int = 360
							heading_score = 0
							GOTO bs_done_heading_calcs_wheelie
						ENDIF 
						IF instructor_bike_heading_int > 0
							IF instructor_bike_heading_int < 175
								variableb =# instructor_bike_heading_int
								variableb *= 0.56 //0.56 = 99(which is highest score possible) / 175 ( which is 175 - 0)
								heading_score =# variableb 
								GOTO bs_done_heading_calcs_wheelie
							ENDIF
						ENDIF  
						IF instructor_bike_heading_int > 174
							IF instructor_bike_heading_int < 186
								heading_score = 100
							ENDIF
						ENDIF 
						IF instructor_bike_heading_int > 185
							IF instructor_bike_heading_int < 360
								instructor_bike_heading_int = instructor_bike_heading_int - 180
								variableb =# instructor_bike_heading_int
								variableb *= 0.56 	
								variablea =# variableb
							 	heading_score = 100 - variablea 
								GOTO bs_done_heading_calcs_wheelie
							ENDIF
						ENDIF
					ELSE
						heading_score = 0
						GOTO bs_done_heading_calcs_wheelie
					ENDIF	 
									    
					bs_done_heading_calcs_wheelie:
					IF heading_score < 1
						heading_score = 0
					ENDIF 
					
					overall_score = position_score + heading_score				
					overall_score /= 2  
					IF position_score = 0
						overall_score = 0
					ENDIF	 

					//losing points for hitting cones 
					GOSUB bs_damage_cones_calcs
													 
					//checking overall score is greater than 0 and clearing prints
					GOSUB bs_checking_overall_score

					//checking overall score against the best score at present
					IF overall_score > bs_wheelie_best_score  	
						bs_old_score = bs_wheelie_best_score 
						bs_wheelie_best_score = overall_score
						bs_print_top_scores_flag = 1
						GOSUB bs_medal_check
						IF bs_which_medal_displayed = 4 // gold
							IF bs_wheelie_goldachieved = 0
								bs_wheelie_goldachieved = 1
								bs_wheelie_silverachieved = 1
								bs_wheelie_bronzeachieved = 1
							ENDIF
						ENDIF

						IF bs_which_medal_displayed = 3 // silver
							IF bs_wheelie_silverachieved = 0
								bs_wheelie_silverachieved = 1
								bs_wheelie_bronzeachieved = 1
							ENDIF
						ENDIF
						
						IF bs_which_medal_displayed = 2 //bronze
						   IF bs_wheelie_bronzeachieved = 0
								bs_wheelie_bronzeachieved = 1
							ENDIF
						ENDIF

					ELSE
						bs_which_medal_displayed = 0	
					ENDIF 	
					
					//opening next level
					IF bs_open_tests = 3
						IF overall_score > 69
							bs_print_top_scores_flag = 2
							bs_open_tests = 4
							instructor_bike_dead_flag = 2
							bs_last_played ++
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BIKE_AWARD_TRACK_START
						ENDIF
					ENDIF

					//printing scores onscreen
					timera = 0 
					WHILE timera > -1
						
						WAIT 0

						//changing camera position 
						GOSUB bs_setting_up_camera			

						//checking player hasnt left car
						IF NOT bs_print_top_scores_flag = 2
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								instructor_bike_dead_flag = 2
								GOTO bs_after_scores_wheelie																		  
							ENDIF 	 
						ENDIF
						
						//displaying scores
						GOSUB bs_display_head_pos_dam_text

						//checking if player has skipped watching the scores
						GOSUB bs_skip_scores
						IF finished_watching_scores = 1
							GOTO bs_after_scores_wheelie
						ENDIF
					ENDWHILE
					
					//reseting for another try
					bs_after_scores_wheelie:
					
					GOSUB bs_deleting_cones
					
					GOSUB bs_mini_cleanup
					 
					IF instructor_bike_dead_flag = 2 
						GOTO bs_noticeboard_setup
					ELSE
						GOTO bs_refresh_wheelie 
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
	GOTO bs_wheelie_loop
ENDIF

// ------------------------------------------------------------------------------------------------
// The Stoppie

IF mission_selection = 5

	bs_refresh_stoppie:
	GOSUB bs_start_initialise_stuff

	bs_setup_stoppie:

	//////setting up variables for this specific challenge/////
	perfect_heading = 180.0
	car_timer = 9000
	playback_selection = 184 

	GOSUB bs_setting_up_variables

	//creating cars
	CREATE_CAR FCR900 perfect_positionx perfect_positiony perfect_positionz instructor_bike
	SET_CAR_HEADING instructor_bike 180.0

	//creating cones
	GOSUB bs_creating_cones
  	
	// Demo Playback Camera
	IF playback_flag = 0
		IF NOT IS_CAR_DEAD instructor_bike
			CREATE_CHAR_INSIDE_CAR instructor_bike PEDTYPE_CIVFEMALE WMYMECH bs_instructor
		ENDIF
		SET_FIXED_CAMERA_POSITION 1125.66 1283.4 10.5 0.0 0.0 0.0
		POINT_CAMERA_AT_CAR instructor_bike FIXED JUMP_CUT
		RETURN
	ENDIF
	
	//setting up area check
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike -3.5 56.0 0.0 area_check1ax area_check1ay player_z 
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike 3.5 84.0 0.0 area_check1bx area_check1by player_z

	//setting up finishing point
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike 0.0 124.0 0.0 finish_rightx finish_righty perfect_positionz  
	

	CLEAR_PRINTS
	
	PRINT_NOW BS_D_1 5000 4 
	 
	GOSUB bs_stop_initialise_stuff

	bs_stoppie_loop:
	WAIT 0 
	
		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_bike_dead_flag = 2
			GOTO bs_after_scores_stoppie																		  
		ENDIF 
	
		//checking car has moved and player is pressing cross or square
		IF car_started = 0
		    PRINT_NOW BS_D_1 5000 4  
			GOSUB bs_has_car_started	

		//player is going
		ELSE
			IF NOT IS_CAR_DEAD instructor_bike
				
				//checking timer hasnt ran to 0
				GOSUB bs_car_timer_0

				// Makes sure player is in area
				IF variablec = 0 
					IF IS_CAR_IN_AREA_2D instructor_bike area_check1ax area_check1ay area_check1bx area_check1by FALSE
						variablec = 1
					ENDIF
				ENDIF
				IF variablec = 1
					IF NOT IS_CAR_IN_AREA_2D instructor_bike area_check1ax area_check1ay area_check1bx area_check1by FALSE
						GET_CHAR_COORDINATES scplayer player_x player_y player_z
						IF player_x > area_check1ax
						OR player_x < area_check1bx
						OR player_y > area_check1ay
							GOSUB bs_freeze_car_pos
						ELSE
//						ENDIF
//						IF player_y < area_check1by
							variablec = 2
						ENDIF
					ENDIF
					IF NOT IS_PLAYER_PERFORMING_stoppie player1
						GOSUB bs_freeze_car_pos
					ENDIF 
				ENDIF
	


				// Follow Player's heading 
				GET_CAR_HEADING instructor_bike instructor_bike_heading
				instructor_bike_follow_heading = instructor_bike_heading
				IF NOT instructor_bike_follow_heading = 0.0
					IF instructor_bike_follow_heading < 360.0
					AND instructor_bike_follow_heading > 180.0 
						instructor_bike_follow_heading += 0.1	
					ENDIF
					IF instructor_bike_follow_heading = 360.0
						instructor_bike_follow_heading > 0.0
					ENDIF
					IF instructor_bike_follow_heading <= 180.0
					AND instructor_bike_follow_heading > 0.0 
						instructor_bike_follow_heading -= 0.1	
					ENDIF
				ENDIF
				GET_CAR_SPEED instructor_bike instructor_bike_speed
				//checking car is stopped or not
				//				IF instructor_bike_speed = 0.0
				IF IS_CAR_STOPPED instructor_bike
				OR NOT IS_CHAR_IN_CAR scplayer instructor_bike
					GOSUB bs_freeze_car_pos
					
					perfect_positionx = finish_rightx
					perfect_positiony = finish_righty

					IF variablec > 0 
						GOSUB bs_position_score_calcs
					ELSE
						position_score = 0
					ENDIF				
					
					//heading - perfect heading is 0
					GET_CAR_HEADING instructor_bike instructor_bike_heading
					instructor_bike_heading_int =# instructor_bike_heading
					IF variablec > 0
						IF instructor_bike_heading_int = 0
							heading_score = 0
							GOTO bs_done_heading_calcs_stoppie
						ENDIF 
						IF instructor_bike_heading_int = 180
							heading_score = 100
							GOTO bs_done_heading_calcs_stoppie
						ENDIF 
						IF instructor_bike_heading_int = 360
							heading_score = 0
							GOTO bs_done_heading_calcs_stoppie
						ENDIF 
						IF instructor_bike_heading_int > 0
							IF instructor_bike_heading_int < 175
								variableb =# instructor_bike_heading_int
								variableb *= 0.56 //0.56 = 99(which is highest score possible) / 175 ( which is 175 - 0)
								heading_score =# variableb 
								GOTO bs_done_heading_calcs_stoppie
							ENDIF
						ENDIF  
						IF instructor_bike_heading_int > 174
							IF instructor_bike_heading_int < 186
								heading_score = 100
							ENDIF
						ENDIF 
						IF instructor_bike_heading_int > 185
							IF instructor_bike_heading_int < 360
								instructor_bike_heading_int = instructor_bike_heading_int - 180
								variableb =# instructor_bike_heading_int
								variableb *= 0.56 	
								variablea =# variableb
							 	heading_score = 100 - variablea 
								GOTO bs_done_heading_calcs_stoppie
							ENDIF
						ENDIF 
					ELSE
						heading_score = 0
						GOTO bs_done_heading_calcs_stoppie
					ENDIF

				  	bs_done_heading_calcs_stoppie:
					IF heading_score < 1
						heading_score = 0
					ENDIF 
					
					overall_score = position_score + heading_score				
					overall_score /= 2  
					IF position_score = 0
						overall_score = 0
					ENDIF	 

					//losing points for hitting cones 
					GOSUB bs_damage_cones_calcs
													 
					//checking overall score is greater than 0 and clearing prints
					GOSUB bs_checking_overall_score

					//checking overall score against the best score at present
					IF overall_score > bs_stoppie_best_score  	
						bs_old_score = bs_stoppie_best_score 
						bs_stoppie_best_score = overall_score
						bs_print_top_scores_flag = 1
						GOSUB bs_medal_check
						IF bs_which_medal_displayed = 4 // gold
							IF bs_stoppie_goldachieved = 0
								bs_stoppie_goldachieved = 1
								bs_stoppie_silverachieved = 1
								bs_stoppie_bronzeachieved = 1
							ENDIF
						ENDIF

						IF bs_which_medal_displayed = 3 // silver
							IF bs_stoppie_silverachieved = 0
								bs_stoppie_silverachieved = 1
								bs_stoppie_bronzeachieved = 1
							ENDIF
						ENDIF
						
						IF bs_which_medal_displayed = 2 //bronze
						   IF bs_stoppie_bronzeachieved = 0
								bs_stoppie_bronzeachieved = 1
							ENDIF
						ENDIF

					ELSE
						bs_which_medal_displayed = 0	
					ENDIF 	
					
					//opening next level
					IF bs_open_tests = 5
						IF overall_score > 69
							bs_print_top_scores_flag = 2
							bs_open_tests = 6
							instructor_bike_dead_flag = 2
							bs_last_played ++
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BIKE_AWARD_TRACK_START
						ENDIF
					ENDIF
 
					//printing scores onscreen
					timera = 0 
					WHILE timera > -1
						
						WAIT 0

						//changing camera position 
						GOSUB bs_setting_up_camera			

						//checking player hasnt left car
						IF NOT bs_print_top_scores_flag = 2
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								instructor_bike_dead_flag = 2
								GOTO bs_after_scores_stoppie																		  
							ENDIF 
						ENDIF

						//displaying scores
						GOSUB bs_display_head_pos_dam_text

						//checking if player has skipped watching the scores
						GOSUB bs_skip_scores
						IF finished_watching_scores = 1
							GOTO bs_after_scores_stoppie
						ENDIF
					ENDWHILE
					
					//reseting for another try
					bs_after_scores_stoppie:
					
					GOSUB bs_deleting_cones
					
					GOSUB bs_mini_cleanup
					 
					IF instructor_bike_dead_flag = 2 
						GOTO bs_noticeboard_setup
					ELSE
						GOTO bs_refresh_stoppie 
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
	GOTO bs_stoppie_loop
ENDIF


// ----------------------------------------------------------------------
// Jump & Stop in Area

IF mission_selection = 4

	///////////////remember to unload cars at end of level/////////////

	bs_refresh_jump_and_stop:
	GOSUB bs_start_initialise_stuff


	bs_setup_jump_and_stop:

	//////setting up variables for this specific challenge/////
	perfect_heading = 180.0
	car_timer = 9000
	playback_selection = 185

	GOSUB bs_setting_up_variables


	//creating cars
	CREATE_CAR SANCHEZ perfect_positionx perfect_positiony perfect_positionz instructor_bike
	SET_CAR_HEADING instructor_bike perfect_heading

	//setting up area check
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike -2.5 46.0 2.0 area_check1ax area_check1ay player_z 
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike 2.5 50.0 2.0 area_check1bx area_check1by player_z

	//creating cones
	GOSUB bs_creating_cones

	IF playback_flag = 0
		IF NOT IS_CAR_DEAD instructor_bike
			CREATE_CHAR_INSIDE_CAR instructor_bike PEDTYPE_CIVFEMALE WMYMECH bs_instructor
		ENDIF
		SET_FIXED_CAMERA_POSITION 1127.66 1288.4 12.75 0.0 0.0 0.0
		POINT_CAMERA_AT_CAR instructor_bike FIXED JUMP_CUT
		RETURN
	ENDIF
	
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike 0.0 124.0 0.0 finish_rightx finish_righty perfect_positionz  
	CLEAR_PRINTS

	PRINT_NOW ( BS_E_1 ) 5000 4 // Drive to the end of the track, a tyre will blow out half way there.  
	GOSUB bs_stop_initialise_stuff

	bs_jump_stop_loop:
	WAIT 0
	
		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_bike_dead_flag = 2
			GOTO bs_after_scores_jump_and_stop																		  
		ENDIF 
		
		IF car_started = 0
			PRINT_NOW ( BS_E_1 ) 5000 4 // Drive to the end of the track, a tyre will blow out half way there.~n~For extra control, release the accelerate button. 
			GOSUB bs_has_car_started	

		//player is going
		ELSE
			IF NOT IS_CAR_DEAD instructor_bike
				
				
				//checking timer hasnt ran to 0
				GOSUB bs_car_timer_0 

				// Makes sure player is in area
				IF variablec = 0 
					IF IS_CAR_IN_AREA_2D instructor_bike area_check1ax area_check1ay area_check1bx area_check1by FALSE
						variablec = 1
					ENDIF
				ENDIF
				IF variablec = 1
					IF NOT IS_CAR_IN_AREA_2D instructor_bike area_check1ax area_check1ay area_check1bx area_check1by FALSE
						GET_CHAR_COORDINATES scplayer player_x player_y player_z
						IF player_x > area_check1ax
						OR player_x < area_check1bx
						OR player_y > area_check1ay
							GOSUB bs_freeze_car_pos
						ELSE
//						ENDIF
//						IF player_y < area_check1by
							variablec = 2
						ENDIF
					ENDIF
  				ENDIF

				
				//checking car is stopped or not
				IF IS_CAR_STOPPED instructor_bike
				OR NOT IS_CHAR_IN_CAR scplayer instructor_bike

					GOSUB bs_freeze_car_pos
				
					//CALCULATIONS FOR PLAYER SCORE

					//how close player is to perfect position 
						perfect_positionx = finish_rightx
						perfect_positiony = finish_righty

					IF variablec > 0 
						GOSUB bs_position_score_calcs
					ELSE
						position_score = 0
					ENDIF

					//heading - perfect heading is 180
					GET_CAR_HEADING instructor_bike instructor_bike_heading
					instructor_bike_heading_int =# instructor_bike_heading
					IF variablec > 0
						IF instructor_bike_heading_int = 0
							heading_score = 0
							GOTO bs_done_heading_calcs_jump_and_stop
						ENDIF 
						IF instructor_bike_heading_int = 180
							heading_score = 100
							GOTO bs_done_heading_calcs_jump_and_stop
						ENDIF 
						IF instructor_bike_heading_int = 360
							heading_score = 0
							GOTO bs_done_heading_calcs_jump_and_stop
						ENDIF 

						IF instructor_bike_heading_int > 0
							IF instructor_bike_heading_int < 175
								variableb =# instructor_bike_heading_int
								variableb *= 0.56 //0.56 = 99(which is highest score possible) / 175 ( which is 175 - 0)
								heading_score =# variableb 
								GOTO bs_done_heading_calcs_jump_and_stop
							ENDIF
						ENDIF  

						IF instructor_bike_heading_int > 174
							IF instructor_bike_heading_int < 186
								heading_score = 100
							ENDIF
						ENDIF 
							  
						IF instructor_bike_heading_int > 185
							IF instructor_bike_heading_int < 360
								instructor_bike_heading_int = instructor_bike_heading_int - 180
								variableb =# instructor_bike_heading_int
								variableb *= 0.56 	
								variablea =# variableb
							 	heading_score = 100 - variablea 
								GOTO bs_done_heading_calcs_jump_and_stop
							ENDIF
						ENDIF 
					ELSE
						heading_score = 0
						GOTO bs_done_heading_calcs_jump_and_stop
					ENDIF 
				    

					bs_done_heading_calcs_jump_and_stop:
					IF heading_score < 1
						heading_score = 0
					ENDIF 
					
					overall_score = position_score + heading_score				
					overall_score /= 2  
					IF position_score = 0
						overall_score = 0
					ENDIF	 

					//losing points for hitting cones 
					GOSUB bs_damage_cones_calcs
													 
					//checking overall score is greater than 0 and clearing prints
					GOSUB bs_checking_overall_score

					//checking overall score against the best score at present
					IF overall_score > bs_jumpstop_best_score 	
						bs_old_score = bs_jumpstop_best_score 
						bs_jumpstop_best_score = overall_score
						bs_print_top_scores_flag = 1
						GOSUB bs_medal_check
						IF bs_which_medal_displayed = 4 // gold
							IF bs_jumpstop_goldachieved = 0
								bs_jumpstop_goldachieved = 1
								bs_jumpstop_silverachieved = 1
								bs_jumpstop_bronzeachieved = 1
							ENDIF
						ENDIF

						IF bs_which_medal_displayed = 3 // silver
							IF bs_jumpstop_silverachieved = 0
								bs_jumpstop_silverachieved = 1
								bs_jumpstop_bronzeachieved = 1
							ENDIF
						ENDIF
						
						IF bs_which_medal_displayed = 2 //bronze
						   IF bs_jumpstop_bronzeachieved = 0
								bs_jumpstop_bronzeachieved = 1
							ENDIF
						ENDIF

					ELSE
						bs_which_medal_displayed = 0	
					ENDIF 	

					//Disable Stoppie tests 

					//opening next level
					IF bs_open_tests = 4
						IF overall_score > 69
							bs_print_top_scores_flag = 2
							bs_open_tests = 5
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BIKE_AWARD_TRACK_START
							instructor_bike_dead_flag = 2
							bs_last_played ++
						ENDIF
					ENDIF

					//printing scores onscreen
					timera = 0 
					WHILE timera > -1
						
						WAIT 0

						//changing camera position 
						GOSUB bs_setting_up_camera			

						//checking player hasnt left car
						IF NOT bs_print_top_scores_flag = 2
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								instructor_bike_dead_flag = 2
								GOTO bs_after_scores_jump_and_stop																		  
							ENDIF 
						ENDIF
		
						//displaying scores
						GOSUB bs_display_head_pos_dam_text

						//checking if player has skipped watching the scores
						GOSUB bs_skip_scores
						IF finished_watching_scores = 1
							GOTO bs_after_scores_jump_and_stop
						ENDIF
					ENDWHILE
					
					//reseting for another try
					bs_after_scores_jump_and_stop:
					GOSUB bs_mini_cleanup
					 
					GOSUB bs_deleting_cones

					IF instructor_bike_dead_flag = 2 
						GOTO bs_noticeboard_setup
					ELSE
						GOTO bs_refresh_jump_and_stop 
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
		
	GOTO bs_jump_stop_loop 
ENDIF
// ----------------------------------------------------------------------
// Jump & Stoppie into Area
IF mission_selection = 6

	///////////////remember to unload cars at end of level/////////////

	bs_refresh_jump_and_stoppie:
	GOSUB bs_start_initialise_stuff


	bs_setup_jump_and_stoppie:

	//////setting up variables for this specific challenge/////
	perfect_heading = 180.0
	car_timer = 9000
	playback_selection = 186

	GOSUB bs_setting_up_variables


	//creating cars
	CREATE_CAR NRG500 perfect_positionx perfect_positiony perfect_positionz instructor_bike
	SET_CAR_HEADING instructor_bike perfect_heading

	//setting up area check
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike -2.5 46.0 2.0 area_check1ax area_check1ay player_z 
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike 2.5 52.0 2.0 area_check1bx area_check1by player_z

	//creating cones
	GOSUB bs_creating_cones

	IF playback_flag = 0
		IF NOT IS_CAR_DEAD instructor_bike
			CREATE_CHAR_INSIDE_CAR instructor_bike PEDTYPE_CIVFEMALE WMYMECH bs_instructor
		ENDIF
		SET_FIXED_CAMERA_POSITION 1122.66 1265.4 15.75 0.0 0.0 0.0
		POINT_CAMERA_AT_CAR instructor_bike FIXED JUMP_CUT
		RETURN
	ENDIF
	
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike 0.0 114.0 0.0 finish_rightx finish_righty perfect_positionz  
	CLEAR_PRINTS

	PRINT_NOW ( BS_F_1 ) 5000 4 // Drive to the end of the track, a tyre will blow out half way there.  
	GOSUB bs_stop_initialise_stuff

	bs_jump_stoppie_loop:
	WAIT 0
	
		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_bike_dead_flag = 2
			GOTO bs_after_scores_jump_and_stoppie																		  
		ENDIF 
		
		IF car_started = 0
			PRINT_NOW ( BS_F_1 ) 5000 4 // Drive to the end of the track, a tyre will blow out half way there.~n~For extra control, release the accelerate button. 
			GOSUB bs_has_car_started	

		//player is going
		ELSE
			IF NOT IS_CAR_DEAD instructor_bike
				
				
				//checking timer hasnt ran to 0
				GOSUB bs_car_timer_0 

				// Makes sure player is in area
				IF variablec = 0 
					IF IS_CAR_IN_AREA_2D instructor_bike area_check1ax area_check1ay area_check1bx area_check1by FALSE
						variablec = 1
					ENDIF
				ENDIF
				IF variablec = 1
					IF NOT IS_CAR_IN_AREA_2D instructor_bike area_check1ax area_check1ay area_check1bx area_check1by FALSE
						GET_CHAR_COORDINATES scplayer player_x player_y player_z
						IF player_x > area_check1ax
						OR player_x < area_check1bx
						OR player_y > area_check1ay
							GOSUB bs_freeze_car_pos
						ELSE
							variablec = 2
						ENDIF
					ENDIF
  				ENDIF

				IF variablec = 2
					IF NOT IS_CAR_IN_AIR_PROPER instructor_bike
						GET_GAME_TIMER bs_timer_start	
						variablec = 3
					ENDIF
				ENDIF

				IF variablec = 3
					GET_GAME_TIMER bs_timer_end
					bs_timer_diff = bs_timer_end - bs_timer_start
					IF bs_timer_diff > 250	
						IF NOT IS_PLAYER_PERFORMING_STOPPIE player1
						OR IS_PLAYER_PERFORMING_WHEELIE player1
							GOSUB bs_freeze_car_pos
						ENDIF	
					ENDIF
				ENDIF		


				//checking car is stopped or not
				IF IS_CAR_STOPPED instructor_bike
				OR NOT IS_CHAR_IN_CAR scplayer instructor_bike

					GOSUB bs_freeze_car_pos
				
					//CALCULATIONS FOR PLAYER SCORE

					//how close player is to perfect position 
						perfect_positionx = finish_rightx
						perfect_positiony = finish_righty

					IF variablec > 0 
						GOSUB bs_position_score_calcs
					ELSE
						position_score = 0
					ENDIF

					//heading - perfect heading is 180
					GET_CAR_HEADING instructor_bike instructor_bike_heading
					instructor_bike_heading_int =# instructor_bike_heading
					IF variablec > 0
						IF instructor_bike_heading_int = 0
							heading_score = 0
							GOTO bs_done_heading_calcs_jump_and_stoppie
						ENDIF 
						IF instructor_bike_heading_int = 180
							heading_score = 100
							GOTO bs_done_heading_calcs_jump_and_stoppie
						ENDIF 
						IF instructor_bike_heading_int = 360
							heading_score = 0
							GOTO bs_done_heading_calcs_jump_and_stoppie
						ENDIF 

						IF instructor_bike_heading_int > 0
							IF instructor_bike_heading_int < 175
								variableb =# instructor_bike_heading_int
								variableb *= 0.56 //0.56 = 99(which is highest score possible) / 175 ( which is 175 - 0)
								heading_score =# variableb 
								GOTO bs_done_heading_calcs_jump_and_stoppie
							ENDIF
						ENDIF  

						IF instructor_bike_heading_int > 174
							IF instructor_bike_heading_int < 186
								heading_score = 100
							ENDIF
						ENDIF 
							  
						IF instructor_bike_heading_int > 185
							IF instructor_bike_heading_int < 360
								instructor_bike_heading_int = instructor_bike_heading_int - 180
								variableb =# instructor_bike_heading_int
								variableb *= 0.56 	
								variablea =# variableb
							 	heading_score = 100 - variablea 
								GOTO bs_done_heading_calcs_jump_and_stoppie
							ENDIF
						ENDIF 
					ELSE
						heading_score = 0
						GOTO bs_done_heading_calcs_jump_and_stoppie
					ENDIF 
				    

					bs_done_heading_calcs_jump_and_stoppie:
					IF heading_score < 1
						heading_score = 0
					ENDIF 
					
					overall_score = position_score + heading_score				
					overall_score /= 2  
					IF position_score = 0
						overall_score = 0
					ENDIF	 

					//losing points for hitting cones 
					GOSUB bs_damage_cones_calcs
													 
					//checking overall score is greater than 0 and clearing prints
					GOSUB bs_checking_overall_score

					//checking overall score against the best score at present
					IF overall_score > bs_jumpstoppie_best_score 	
						bs_old_score = bs_jumpstoppie_best_score 
						bs_jumpstoppie_best_score = overall_score
						bs_print_top_scores_flag = 1
						GOSUB bs_medal_check

						IF bs_which_medal_displayed = 4 // gold
							IF bs_jumpstoppie_goldachieved = 0
								bs_jumpstoppie_goldachieved = 1
								bs_jumpstoppie_silverachieved = 1
								bs_jumpstoppie_bronzeachieved = 1
							ENDIF
						ENDIF

						IF bs_which_medal_displayed = 3 // silver
							IF bs_jumpstoppie_silverachieved = 0
								bs_jumpstoppie_silverachieved = 1
								bs_jumpstoppie_bronzeachieved = 1
							ENDIF
						ENDIF
						
						IF bs_which_medal_displayed = 2 //bronze
						   IF bs_jumpstoppie_bronzeachieved = 0
								bs_jumpstoppie_bronzeachieved = 1
							ENDIF
						ENDIF

					ELSE
						bs_which_medal_displayed = 0	
					ENDIF 	

					// bike school passed!
					IF bs_passed = 0
						IF bs_open_tests = 6
							IF overall_score > 69
								bs_print_top_scores_flag = 2
								bs_passed = 1
								//bs_open_tests = 7
								instructor_bike_dead_flag = 2
								REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BIKE_AWARD_TRACK_START
							ENDIF
						ENDIF
					ENDIF


					//printing scores onscreen
					timera = 0 
					WHILE timera > -1
						WAIT 0

						//changing camera position 
						GOSUB bs_setting_up_camera			

						//checking player hasnt left car
						IF NOT bs_print_top_scores_flag = 2
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								instructor_bike_dead_flag = 2
								GOTO bs_after_scores_jump_and_stoppie																		  
							ENDIF 
						ENDIF
		
						//displaying scores
						GOSUB bs_display_head_pos_dam_text

						//checking if player has skipped watching the scores
						GOSUB bs_skip_scores
						IF finished_watching_scores = 1
							GOTO bs_after_scores_jump_and_stoppie
						ENDIF
					ENDWHILE
					
					//reseting for another try
					bs_after_scores_jump_and_stoppie:
					
					GOSUB bs_mini_cleanup
					 
					GOSUB bs_deleting_cones

					IF instructor_bike_dead_flag = 2 
						IF bs_passed = 1
							IF flag_bikeschool_passed_1stime = 0
								DO_FADE 1000 FADE_OUT
								WHILE GET_FADING_STATUS
								 	WAIT 0
								ENDWHILE
								SET_CHAR_COORDINATES scplayer 1171.5206 1351.1826 9.9219
								SET_CHAR_HEADING scplayer 11.0
							   	SET_CAMERA_BEHIND_PLAYER
								RESTORE_CAMERA_JUMPCUT
				
							   	DO_FADE 2000 FADE_IN
							   	WHILE GET_FADING_STATUS
								 	WAIT 0
								ENDWHILE

								GOTO mission_passed_bs
							ENDIF	
						ENDIF

						GOTO bs_noticeboard_setup
					ELSE
						GOTO bs_refresh_jump_and_stoppie 
					ENDIF
				ENDIF
			ENDIF
		ENDIF  
		
	GOTO bs_jump_stoppie_loop 
ENDIF

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// Mission failed
mission_failed_bskool:
CLEAR_MISSION_AUDIO 3
REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BIKE_AWARD_TRACK_STOP
CLEAR_PRINTS

RETURN

   

// mission passed
mission_passed_bs:
DO_FADE 500 FADE_IN

IF flag_bikeschool_passed_1stime = 0
    REGISTER_ODDJOB_MISSION_PASSED
    PLAYER_MADE_PROGRESS 1
    flag_bikeschool_passed_1stime = 1
	INCREMENT_FLOAT_STAT BIKE_SKILL 50.0
ENDIF
CLEAR_PRINTS
PRINT_BIG ( BS_Z_7 ) 5000 5 //"Mission Passed!"
PLAY_MISSION_PASSED_TUNE 2 
CLEAR_WANTED_LEVEL player1
RETURN
		


// mission cleanup
mission_cleanup_bskool:	
//ALTER_WANTED_LEVEL player1 bs_wanted
//SET_PLAYER_CONTROL player1 on

CLEAR_ONSCREEN_TIMER car_timer
USE_TEXT_COMMANDS FALSE
DISPLAY_HUD TRUE
DISPLAY_RADAR TRUE
RELEASE_WEATHER
MARK_MODEL_AS_NO_LONGER_NEEDED trafficcone
MARK_MODEL_AS_NO_LONGER_NEEDED landjump2
MARK_MODEL_AS_NO_LONGER_NEEDED FCR900
MARK_MODEL_AS_NO_LONGER_NEEDED BF400
MARK_MODEL_AS_NO_LONGER_NEEDED PCJ600
MARK_MODEL_AS_NO_LONGER_NEEDED NRG500
MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH
//REMOVE_BLIP bs_blip_flag

IF bs_gold_rewardgiven = 0
	IF bs_the360_goldachieved = 1
		IF bs_the180_goldachieved = 1
			IF bs_wheelie_goldachieved = 1
				IF bs_stoppie_goldachieved = 1
					IF bs_jumpstop_goldachieved = 1
						IF bs_jumpstoppie_goldachieved = 1
							
							bs_gold_rewardgiven = 1
							IF bs_silver_rewardgiven = 0
								bs_silver_rewardgiven = 1
							ENDIF
							IF bs_bronze_rewardgiven = 0
								bs_bronze_rewardgiven = 1
							ENDIF
						
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF bs_silver_rewardgiven = 0
	IF bs_the360_silverachieved = 1
		IF bs_the180_silverachieved = 1
			IF bs_wheelie_silverachieved = 1
				IF bs_stoppie_silverachieved = 1
					IF bs_jumpstop_silverachieved = 1
						IF bs_jumpstoppie_silverachieved = 1
							
							bs_silver_rewardgiven = 1
							IF bs_bronze_rewardgiven = 0
								bs_bronze_rewardgiven = 1
							ENDIF
						
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF bs_bronze_rewardgiven = 0
	IF bs_the360_bronzeachieved = 1
		IF bs_the180_bronzeachieved = 1
			IF bs_wheelie_bronzeachieved = 1
				IF bs_stoppie_bronzeachieved = 1
					IF bs_jumpstop_bronzeachieved = 1
						IF bs_jumpstoppie_bronzeachieved = 1
							bs_bronze_rewardgiven = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF bs_gold_rewardgiven = 1
	SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR bs_gold_generator TRUE
  	SWITCH_CAR_GENERATOR bs_gold_generator 101
	INCREMENT_FLOAT_STAT BIKE_SKILL 200.0
   	bs_gold_rewardgiven = 2
ENDIF

IF bs_silver_rewardgiven = 1
	SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR bs_silver_generator TRUE
 	SWITCH_CAR_GENERATOR bs_silver_generator 101
	INCREMENT_FLOAT_STAT BIKE_SKILL 100.0
   	bs_silver_rewardgiven = 2
ENDIF

IF bs_bronze_rewardgiven = 1
	SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR bs_bronze_generator TRUE
   	SWITCH_CAR_GENERATOR bs_bronze_generator 101
   	bs_bronze_rewardgiven = 2
ENDIF

flag_player_on_mission = 0
MISSION_HAS_FINISHED
RETURN



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////                      GOSUBS                             ///////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


bs_draw_tv_screen://////////////////////////////////////////////////////////////////////

	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 8 320.0 225.0 612.0 438.0 0 0 0 bs_alpha

	//FADE_OUT
	IF bs_fade_flag = 1 
		IF bs_alpha < 255
			bs_alpha = bs_alpha + 5 
			IF bs_alpha > 255
				bs_alpha = 255
			ENDIF
		ELSE
			bs_fade_flag = 2
		ENDIF
	ENDIF

	//FADE_IN
	IF bs_fade_flag = 3
		IF bs_alpha > 0
			bs_alpha = bs_alpha - 5 
			IF bs_alpha < 0
				bs_alpha = 0
			ENDIF
		ELSE
			bs_fade_flag = 0
		ENDIF
	ENDIF
				

	//which test
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 8 320.0 0.0 640.0 197.0 0 0 0 255 
	GOSUB bs_test_name_text 
	SET_TEXT_COLOUR 190 190 190 255
	
	IF mission_selection = 1 
		DISPLAY_TEXT 322.0 41.0 BS_A //The 360 
		IF bs_the360_best_score > 69 
			bs_which_score_displayed = bs_the360_best_score 
		ELSE
			bs_which_score_displayed = bs_the360_best_score
			GOSUB bs_small_onscreen_text 
			SET_TEXT_RIGHT_JUSTIFY FALSE
			SET_TEXT_CENTRE TRUE
			SET_TEXT_COLOUR 166 202 240 255
			DISPLAY_TEXT 320.0 80.0 BS_Z_3 // Get over 70%
		ENDIF
		GOSUB bs_drawing_medal 
	ENDIF
	IF mission_selection = 2 
		DISPLAY_TEXT 322.0 41.0 BS_B //The 180 
		IF bs_the180_best_score > 69 
			bs_which_score_displayed = bs_the180_best_score
		ELSE
			bs_which_score_displayed = bs_the180_best_score
			GOSUB bs_small_onscreen_text 
			SET_TEXT_RIGHT_JUSTIFY OFF
			SET_TEXT_CENTRE TRUE
			SET_TEXT_COLOUR 166 202 240 255
			DISPLAY_TEXT 320.0 80.0 BS_Z_3 // Get over 70%
		ENDIF  
		GOSUB bs_drawing_medal 
	ENDIF
	IF mission_selection = 3 
		DISPLAY_TEXT 322.0 41.0 BS_C //The Wheelie
		IF bs_wheelie_best_score > 69
			bs_which_score_displayed = bs_wheelie_best_score 
		ELSE
			bs_which_score_displayed = bs_wheelie_best_score
			GOSUB bs_small_onscreen_text 
			SET_TEXT_RIGHT_JUSTIFY OFF
			SET_TEXT_CENTRE TRUE
			SET_TEXT_COLOUR 166 202 240 255
			DISPLAY_TEXT 320.0 80.0 BS_Z_3 // Get over 70%
		ENDIF 
		GOSUB bs_drawing_medal 
	ENDIF
	IF mission_selection = 5 
		DISPLAY_TEXT 322.0 41.0 BS_D //The Stoppie
		IF bs_stoppie_best_score > 69
			bs_which_score_displayed = bs_stoppie_best_score 
		ELSE
			bs_which_score_displayed = bs_stoppie_best_score
			GOSUB bs_small_onscreen_text 
			SET_TEXT_RIGHT_JUSTIFY OFF
			SET_TEXT_CENTRE TRUE
			SET_TEXT_COLOUR 166 202 240 255
			DISPLAY_TEXT 320.0 80.0 BS_Z_3 // Get over 70%
		ENDIF 
		GOSUB bs_drawing_medal 
	ENDIF
	IF mission_selection = 4 	   														 
		DISPLAY_TEXT 322.0 41.0 BS_E //Jump & Stop
		IF bs_jumpstop_best_score > 69
			bs_which_score_displayed = bs_jumpstop_best_score 
		ELSE
			bs_which_score_displayed = bs_jumpstop_best_score
			GOSUB bs_small_onscreen_text 
			SET_TEXT_RIGHT_JUSTIFY OFF
			SET_TEXT_CENTRE TRUE
			SET_TEXT_COLOUR 166 202 240 255
			DISPLAY_TEXT 320.0 80.0 BS_Z_3 // Get over 70%
		ENDIF 
		GOSUB bs_drawing_medal 
	ENDIF
	IF mission_selection = 6 
		DISPLAY_TEXT 322.0 41.0 BS_F //Jump & Stoppie 
		IF bs_jumpstoppie_best_score > 69
			bs_which_score_displayed = bs_jumpstoppie_best_score
		ELSE
			bs_which_score_displayed = bs_jumpstoppie_best_score
			GOSUB bs_small_onscreen_text 
			SET_TEXT_RIGHT_JUSTIFY OFF
			SET_TEXT_CENTRE TRUE
			SET_TEXT_COLOUR 166 202 240 255
			DISPLAY_TEXT 320.0 80.0 BS_Z_3 // Get over 70%
		ENDIF  
		GOSUB bs_drawing_medal 
	ENDIF
    
	GOSUB bs_small_onscreen_text 
	SET_TEXT_COLOUR 248 211 7 255
	IF current_Language = LANGUAGE_FRENCH
		SET_TEXT_SCALE 0.32 1.25 
		DISPLAY_TEXT 70.0 360.0 BS_X_4
	ELSE
		SET_TEXT_SCALE 0.32 1.25 
		DISPLAY_TEXT 70.0 360.0 BS_X_4
	ENDIF

	IF NOT bs_control_flag = 1
		GOSUB bs_small_onscreen_text 
		SET_TEXT_COLOUR 248 211 7 255
		IF current_Language = LANGUAGE_FRENCH
			SET_TEXT_SCALE 0.32 1.25 
			DISPLAY_TEXT 70.0 340.0 BS_X_5
		ELSE
			SET_TEXT_SCALE 0.32 1.25 
			DISPLAY_TEXT 70.0 340.0 BS_X_5
		ENDIF

		IF NOT bs_open_tests = 1
			GOSUB bs_small_onscreen_text 
			SET_TEXT_COLOUR 248 211 7 255
			IF current_Language = LANGUAGE_FRENCH
				SET_TEXT_SCALE 0.32 1.25 
				DISPLAY_TEXT 437.0 340.0 BS_X_3
			ELSE
				SET_TEXT_SCALE 0.32 1.25 
				DISPLAY_TEXT 437.0 340.0 BS_X_3
			ENDIF
		ENDIF

		IF bs_open_tests = 1 
			GOSUB bs_small_onscreen_text 
			SET_TEXT_COLOUR 173 173 173 125
			IF current_Language = LANGUAGE_FRENCH
				SET_TEXT_SCALE 0.32 1.25 
				DISPLAY_TEXT 437.0 340.0 BS_X_3
			ELSE
				SET_TEXT_SCALE 0.32 1.25 
				DISPLAY_TEXT 437.0 340.0 BS_X_3
			ENDIF
		ENDIF
	ELSE
		GOSUB bs_small_onscreen_text 
		SET_TEXT_COLOUR 248 211 7 255
		IF current_Language = LANGUAGE_FRENCH
			SET_TEXT_SCALE 0.32 1.25 
			DISPLAY_TEXT 437.0 340.0 BS_X_3
		ELSE
			SET_TEXT_SCALE 0.32 1.25 
			DISPLAY_TEXT 437.0 340.0 BS_X_3
		ENDIF
	ENDIF

	GOSUB bs_small_onscreen_text 
	SET_TEXT_SCALE 0.32 1.25 
	SET_TEXT_COLOUR 255 255 255 255
	DISPLAY_TEXT 150.0 360.0 BS_X04

	IF NOT bs_control_flag = 1
		GOSUB bs_small_onscreen_text 
		SET_TEXT_COLOUR 255 255 255 255
		SET_TEXT_SCALE 0.32 1.25 
		DISPLAY_TEXT 150.0 340.0 BS_X05
		IF NOT bs_open_tests = 1 
			GOSUB bs_small_onscreen_text 
			SET_TEXT_COLOUR 255 255 255 255
			SET_TEXT_SCALE 0.32 1.25 
			DISPLAY_TEXT 537.4 340.0 BS_X03
		ENDIF
		IF bs_open_tests = 1 
			GOSUB bs_small_onscreen_text 
			SET_TEXT_COLOUR 173 173 173 125
			SET_TEXT_SCALE 0.32 1.25 
			DISPLAY_TEXT 537.4 340.0 BS_X03
		ENDIF
	ELSE
		GOSUB bs_small_onscreen_text 
		SET_TEXT_COLOUR 255 255 255 255
		SET_TEXT_SCALE 0.32 1.25 
		DISPLAY_TEXT 537.4 340.0 BS_X03
	ENDIF

	SET_TEXT_SCALE 0.52 1.45 

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

bs_drawing_medal://////////////////////////////////////////////////////////////////////////
    //background for medal, ribbons and score 
    SET_SPRITES_DRAW_BEFORE_FADE TRUE
    DRAW_SPRITE 8 320.0 430.0 640.0 250.0 0 0 0 255

	//best score
	GOSUB bs_small_onscreen_text 
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_COLOUR 166 202 240 255
	IF bs_which_score_displayed > 69
		SET_TEXT_RIGHT_JUSTIFY OFF
		SET_TEXT_CENTRE ON
		DISPLAY_TEXT_WITH_NUMBER 320.0 80.0 BS_Y_6 bs_which_score_displayed
	ENDIF
	
	//no medal
	IF bs_which_score_displayed < 70
		bs_which_medal_displayed = 1
	ENDIF	 
	
	//bronze
	IF bs_which_score_displayed > 69
		IF bs_which_score_displayed < 85
			bs_which_medal_displayed = 2
		ENDIF
	ENDIF

	//silver
	IF bs_which_score_displayed > 84
		IF bs_which_score_displayed < 100
			bs_which_medal_displayed = 3
		ENDIF
	ENDIF

	//gold
	IF bs_which_score_displayed = 100
		bs_which_medal_displayed = 4
	ENDIF	 

	IF bs_which_medal_displayed > 1
		SET_SPRITES_DRAW_BEFORE_FADE TRUE
		DRAW_SPRITE 4 250.0 306.0 -60.0 60.0 180 180 180 255
		SET_SPRITES_DRAW_BEFORE_FADE TRUE
		DRAW_SPRITE 4 395.0 306.0 60.0 60.0 180 180 180 255
	ELSE
		SET_SPRITES_DRAW_BEFORE_FADE TRUE
		DRAW_SPRITE 7 250.0 306.0 -60.0 60.0 180 180 180 255
		SET_SPRITES_DRAW_BEFORE_FADE TRUE
		DRAW_SPRITE 7 395.0 306.0 60.0 60.0 180 180 180 255
	ENDIF

	//which medal awarded
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	IF bs_which_medal_displayed = 1 
		DRAW_SPRITE	6 320.0 307.0 110.0 95.0 160 160 160 255
		GOSUB bs_small_onscreen_text 
		SET_TEXT_COLOUR 166 202 240 255
		SET_TEXT_RIGHT_JUSTIFY OFF
		SET_TEXT_CENTRE ON
		DISPLAY_TEXT 320.0 375.0 BS_Z_4
	ENDIF

	//bronze
	IF bs_which_medal_displayed = 2 
		DRAW_SPRITE	1 320.0 307.0 110.0 95.0 160 160 160 255
		GOSUB bs_small_onscreen_text 
		SET_TEXT_COLOUR 166 202 240 255
		SET_TEXT_RIGHT_JUSTIFY OFF
		SET_TEXT_CENTRE ON
		DISPLAY_TEXT 320.0 375.0 BS_Y_12
	ENDIF

	//silver
	IF bs_which_medal_displayed = 3
		DRAW_SPRITE	2 320.0 307.0 110.0 95.0 160 160 160 255
		GOSUB bs_small_onscreen_text 
		SET_TEXT_COLOUR 166 202 240 255
		SET_TEXT_RIGHT_JUSTIFY OFF
		SET_TEXT_CENTRE ON
		DISPLAY_TEXT 320.0 375.0 BS_Y_11
	ENDIF

	//gold
	IF bs_which_medal_displayed = 4 
		DRAW_SPRITE	3 320.0 307.0 110.0 95.0 160 160 160 255
		GOSUB bs_small_onscreen_text 
		SET_TEXT_COLOUR 166 202 240 255
		SET_TEXT_RIGHT_JUSTIFY OFF
		SET_TEXT_CENTRE ON
		DISPLAY_TEXT 320.0 375.0 BS_Y_10
	ENDIF

RETURN/////////////////////////////////////////////////////////////////////////////////////



// ------------------------------------------------------------------------------------------------
// Play back demos of each test...
bs_watching_demo:

	IF playback_flag = 0 
		IF mission_selection = 1
			GOSUB bs_setup_360
		ENDIF
		IF mission_selection = 2
			GOSUB bs_setup_180
		ENDIF
		IF mission_selection = 3
			GOSUB bs_setup_wheelie
		ENDIF
		IF mission_selection = 5
			GOSUB bs_setup_stoppie
		ENDIF
		IF mission_selection = 4
			GOSUB bs_setup_jump_and_stop
		ENDIF
		IF mission_selection = 6
			GOSUB bs_setup_jump_and_stoppie
		ENDIF

		playback_flag = 1
	ENDIF

	IF playback_flag = 1 
		//loading in car recordings.
		IF NOT IS_CAR_DEAD instructor_bike
			IF NOT HAS_CAR_RECORDING_BEEN_LOADED playback_selection
				REQUEST_CAR_RECORDING playback_selection 
			ELSE
				//playing back car recordings	
				START_PLAYBACK_RECORDED_CAR instructor_bike playback_selection
				bs_fade_flag = 3 //FADE_IN
				playback_flag = 2
			ENDIF
		ENDIF
	ENDIF

	//waiting for playback to finish
	IF playback_flag = 2 
		IF NOT IS_CAR_DEAD instructor_bike 
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR instructor_bike
				bs_fade_flag = 1 //FADE_OUT
				playback_flag = 4
			ENDIF
		ELSE
			bs_fade_flag = 1 //FADE_OUT
			playback_flag = 4
		ENDIF
	ENDIF

	//overriding playback
	IF playback_flag = 3
		bs_fade_flag = 1 //FADE_OUT
		playback_flag = 4
	ENDIF
	
	//waiting for screen to FADE_OUT and cleaning everything up
	IF playback_flag = 4
		IF bs_fade_flag = 2

			STOP_PLAYBACK_RECORDED_CAR instructor_bike 
			DELETE_CAR instructor_bike
			DELETE_CHAR bs_instructor
			CLEAR_AREA noticeboard_x noticeboard_y noticeboard_z 300.0 TRUE
			CLEAR_ONSCREEN_TIMER car_timer
			FREEZE_ONSCREEN_TIMER FALSE
			DELETE_CAR instructor_bike
			GOSUB bs_deleting_cones
			playback_flag = 0
		ENDIF
	ENDIF		 			
RETURN

// ------------------------------------------------------------------------------------------------

bs_small_onscreen_text:///////////////////////////////////////////////////////////////////////
	SET_TEXT_DRAW_BEFORE_FADE TRUE
	SET_TEXT_JUSTIFY ON
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_WRAPX 1000.0
   	SET_TEXT_PROPORTIONAL ON // was on
	SET_TEXT_CENTRE OFF
	SET_TEXT_BACKGROUND OFF
	SET_TEXT_SCALE 0.52 1.45
	SET_TEXT_DROPSHADOW 2 0 0 0 255
	SET_TEXT_FONT FONT_SPACEAGE
RETURN/////////////////////////////////////////////////////////////////////////////////////

bs_small_backend_text:////////////////////////////////////////////////////////////////////////
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


bs_which_course_text:///////////////////////////////////////////////////////////////////////
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

bs_test_name_text:///////////////////////////////////////////////////////////////////////
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

bs_menu_scores_onscreen_text://///////////////////////////////////////////////////////////////
	SET_TEXT_DRAW_BEFORE_FADE TRUE
	SET_TEXT_JUSTIFY ON
	SET_TEXT_WRAPX 1000.0
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_CENTRE ON
	SET_TEXT_BACKGROUND OFF
	SET_TEXT_SCALE 1.0 3.8
	SET_TEXT_DROPSHADOW 2 0 0 0 255
	SET_TEXT_FONT FONT_SPACEAGE
RETURN/////////////////////////////////////////////////////////////////////////////////////

bs_start_initialise_stuff:////////////////////////////////////////////////////////////////////
	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
	    WAIT 0
	ENDWHILE 
//	IF mission_selection = 16 
//		SET_CAR_DENSITY_MULTIPLIER 1.0
//		SET_PED_DENSITY_MULTIPLIER 1.0
//	ELSE
//		SET_CAR_DENSITY_MULTIPLIER 0.0
//		SET_PED_DENSITY_MULTIPLIER 0.0
//	ENDIF	
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER
	
	//FORCE_WEATHER_NOW WEATHER_EXTRA_SUNNY
	//SET_AREA_VISIBLE 0

RETURN/////////////////////////////////////////////////////////////////////////////////////
	
bs_setting_up_variables://////////////////////////////////////////////////////////////////////
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
	instructor_bike_speed = 0.0
	instructor_bike_heading = 0.0
	instructor_bike_follow_heading = 0.0
	instructor_bike_heading_int = 0
	instructor_bike_health = 0
	instructor_bike_dead_flag = 0
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
	//perfect_positionz = 9.0
	player_car_damage = 0 
	players_distance_from_perfectpos = 0.0
	position_score = 0
	time_score = 0
	total_instructor_bike_health = 0
	total_dummy_car1_health = 0
	total_dummy_car2_health = 0
	total_car_damage = 0
	variablea = 0
	variableb = 0.0
	variablec = 0
	variabled = 0
	where_to_place_cones = 0.0
	instructor_bike_roll = 0.0
	instructor_bike_roll_score = 0
	instructor_bike_roll_plus_minus = 0
	bs_print_top_scores_flag = 0
	bs_direction_flag = 0
RETURN/////////////////////////////////////////////////////////////////////////////////////

bs_stop_initialise_stuff://///////////////////////////////////////////////////////////////////
	WARP_CHAR_INTO_CAR scplayer instructor_bike
	LOCK_CAR_DOORS instructor_bike CARLOCK_LOCKED_PLAYER_INSIDE
	WAIT 0
	DISPLAY_RADAR TRUE
	RESTORE_CAMERA_JUMPCUT    
	SET_CAMERA_BEHIND_PLAYER 
	DO_FADE 500 FADE_IN
	WHILE GET_FADING_STATUS
	    WAIT 0
	ENDWHILE 
	SET_PLAYER_CONTROL player1 ON
RETURN/////////////////////////////////////////////////////////////////////////////////////

instructor_bike_dead:///////////////////////////////////////////////////////////////////////
	IF NOT IS_CAR_DEAD instructor_bike
		IF car_started = 1 
			IF NOT IS_CHAR_IN_CAR scplayer instructor_bike
				instructor_bike_dead_flag = 1
			ENDIF
		ENDIF
	ELSE  
		instructor_bike_dead_flag = 1
	ENDIF
RETURN/////////////////////////////////////////////////////////////////////////////////////

bs_has_car_started:///////////////////////////////////////////////////////////////////////////
	IF NOT IS_CAR_DEAD instructor_bike
		IF IS_CHAR_IN_CAR scplayer instructor_bike
			GET_CAR_SPEED instructor_bike instructor_bike_speed 
		ENDIF
	ENDIF

	IF IS_BUTTON_PRESSED PAD1 CROSS
	OR IS_BUTTON_PRESSED PAD1 SQUARE
		IF instructor_bike_speed > 0.1
			CLEAR_PRINTS
			IF mission_selection = 1
			OR mission_selection = 2
			OR mission_selection = 3
			OR mission_selection = 5
			OR mission_selection = 4
			OR mission_selection = 6
				DISPLAY_ONSCREEN_TIMER car_timer TIMER_DOWN  // Time
			ENDIF 
			car_started = 1
		ENDIF
	ENDIF
	
RETURN/////////////////////////////////////////////////////////////////////////////////////

bs_car_timer_0:///////////////////////////////////////////////////////////////////////////////
	IF car_timer = 0
		SET_PLAYER_CONTROL player1 off
		APPLY_BRAKES_TO_PLAYERS_CAR player1 TRUE
	ENDIF	 
RETURN/////////////////////////////////////////////////////////////////////////////////////

bs_freeze_car_pos:////////////////////////////////////////////////////////////////////////////
	SET_PLAYER_CONTROL player1 off
	//FREEZE_CAR_POSITION instructor_bike TRUE
	FREEZE_ONSCREEN_TIMER TRUE
RETURN/////////////////////////////////////////////////////////////////////////////////////

bs_position_score_calcs://////////////////////////////////////////////////////////////////////
	GET_CAR_COORDINATES instructor_bike player_x player_y player_z  
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

bs_damage_score_calcs:////////////////////////////////////////////////////////////////////////
	GET_CAR_HEALTH instructor_bike instructor_bike_health  
	player_car_damage = 1000 - instructor_bike_health
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

bs_damage_cones_calcs:////////////////////////////////////////////////////////////////////////
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
	OR mission_selection = 5
		WHILE trafficcone_counter < 30
			IF HAS_OBJECT_BEEN_DAMAGED trafficcones[trafficcone_counter]
				total_car_damage += 10
			ENDIF
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	IF mission_selection = 4
	OR mission_selection = 6 
		WHILE trafficcone_counter < 14
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

bs_checking_overall_score:////////////////////////////////////////////////////////////////////
	IF overall_score < 1
		overall_score = 0
	ENDIF 

	CLEAR_PRINTS
RETURN/////////////////////////////////////////////////////////////////////////////////////

bs_setting_up_camera://///////////////////////////////////////////////////////////////////////
	IF NOT IS_CAR_DEAD instructor_bike 
		IF camera_position_int = 0
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike 10.0 0.0 5.0 camera_positionx camera_positiony camera_positionz
			camera_positionz = 15.0
			SET_FIXED_CAMERA_POSITION camera_positionx camera_positiony camera_positionz 0.0 0.0 0.0 
			POINT_CAMERA_AT_CAR instructor_bike FIXED INTERPOLATION
			SET_INTERPOLATION_PARAMETERS 50.0 2000
			//driving stats
			//INCREMENT_FLOAT_STAT DRIVING_SKILL 50.0
			camera_position_int = 1
		ENDIF
		IF timera > 3000 
			IF timera < 6000
				IF camera_position_int = 1
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike 0.0 10.0 5.0 camera_positionx camera_positiony camera_positionz
					camera_positionz = 15.0
					SET_FIXED_CAMERA_POSITION camera_positionx camera_positiony camera_positionz 0.0 0.0 0.0 
					POINT_CAMERA_AT_CAR instructor_bike FIXED INTERPOLATION
					SET_INTERPOLATION_PARAMETERS 50.0 2000
					camera_position_int = 2
				ENDIF
			ENDIF
		ENDIF
		IF timera > 6000 
			IF camera_position_int = 2
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike -10.0 0.0 5.0 camera_positionx camera_positiony camera_positionz
				camera_positionz = 15.0
				SET_FIXED_CAMERA_POSITION camera_positionx camera_positiony camera_positionz 0.0 0.0 0.0 
				POINT_CAMERA_AT_CAR instructor_bike FIXED INTERPOLATION
				SET_INTERPOLATION_PARAMETERS 50.0 2000
				camera_position_int = 3
			ENDIF
		ENDIF
		IF timera > 9000
			camera_position_int = 1
			timera = 0
		ENDIF
	ENDIF
RETURN/////////////////////////////////////////////////////////////////////////////////////

bs_medal_check:////////////////////////////////////////////////////////////////////////////
	bs_which_medal_displayed = 0
	//bronze
	IF overall_score > 69
		IF overall_score < 85
			IF bs_old_score > 69 
			AND bs_old_score < 85 
				bs_which_medal_displayed = 0
			ELSE	
				bs_which_medal_displayed = 2
			ENDIF
		ENDIF
	ENDIF
	//silver
	IF overall_score > 84
		IF overall_score < 100
			IF bs_old_score > 84 
			AND bs_old_score < 100 
				bs_which_medal_displayed = 0
			ELSE	
				bs_which_medal_displayed = 3
			ENDIF
		ENDIF
	ENDIF
	//gold
	IF overall_score = 100
		IF bs_old_score = 100
		//OR bs_jumpstoppie_best_score = 100
			bs_which_medal_displayed = 0
		ELSE
			bs_which_medal_displayed = 4
		ENDIF
	ENDIF
RETURN/////////////////////////////////////////////////////////////////////////////////////

bs_display_head_pos_dam_text://///////////////////////////////////////////////////////////////

	DISPLAY_HUD FALSE
	DISPLAY_RADAR FALSE
   	CLEAR_PRINTS
	CLEAR_ONSCREEN_TIMER car_timer
		
	IF bs_which_medal_displayed < 2  // no medal,  2 is bronze

		DRAW_WINDOW 160.0 220.0 490.0 400.0 BS_Z_8 SWIRLS_BOTH 

	ELSE

		DRAW_WINDOW 160.0 200.0 490.0 400.0 DUMMY SWIRLS_BOTH 

	ENDIF


  	// DRAWING THE SCORE NAMES //
	
	GOSUB bs_small_backend_text
	GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE bs_r bs_g bs_b bs_a
	SET_TEXT_COLOUR bs_r bs_g bs_b bs_a
	DISPLAY_TEXT 170.0 255.0 BS_Y_1
	
	GOSUB bs_small_backend_text
	SET_TEXT_RIGHT_JUSTIFY ON
	SET_TEXT_COLOUR 255 255 255 255
	DISPLAY_TEXT_WITH_NUMBER 475.0 255.0 BS_Y_6 heading_score
	
	GOSUB bs_small_backend_text
	GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE bs_r bs_g bs_b bs_a
	SET_TEXT_COLOUR bs_r bs_g bs_b bs_a
	DISPLAY_TEXT 170.0 275.0 BS_Y_2
	
	GOSUB bs_small_backend_text
	SET_TEXT_RIGHT_JUSTIFY ON
	SET_TEXT_COLOUR 255 255 255 255
	DISPLAY_TEXT_WITH_NUMBER 475.0 275.0 BS_Y_6 position_score
	
	GOSUB bs_small_backend_text
	GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE bs_r bs_g bs_b bs_a
	SET_TEXT_COLOUR bs_r bs_g bs_b bs_a
	DISPLAY_TEXT 170.0 295.0 BS_Y_3
	
	GOSUB bs_small_backend_text
	SET_TEXT_RIGHT_JUSTIFY ON
	GET_HUD_COLOUR HUD_COLOUR_RED bs_r bs_g bs_b bs_a
	SET_TEXT_COLOUR bs_r bs_g bs_b bs_a
	DISPLAY_TEXT_WITH_NUMBER 475.0 295.0 BS_Y_6 total_car_damage
	
	GOSUB bs_small_backend_text
	GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE bs_r bs_g bs_b bs_a
	SET_TEXT_COLOUR bs_r bs_g bs_b bs_a
	DISPLAY_TEXT 170.0 315.0 BS_Y_5
	
	GOSUB bs_small_backend_text
	SET_TEXT_RIGHT_JUSTIFY ON
	SET_TEXT_COLOUR 255 255 255 255
	DISPLAY_TEXT_WITH_NUMBER 475.0 315.0 BS_Y_6 overall_score


	// DISPLAYING MEDALS //

	IF bs_print_top_scores_flag = 1	
	OR bs_print_top_scores_flag = 2 
		IF bs_which_medal_displayed > 0
					
			DRAW_SPRITE	4 250.0 200.0 -60.0 60.0 180 180 180 255
			DRAW_SPRITE	4 392.0 200.0 60.0 60.0 180 180 180 255

			IF bs_which_medal_displayed = 2
				DRAW_SPRITE	1 320.0 200.0 110.0 95.0 160 160 160 255  // bronze
			ENDIF
			
			IF bs_which_medal_displayed = 3
				DRAW_SPRITE	2 320.0 200.0 110.0 95.0 160 160 160 255  // silver
			ENDIF
			
			IF bs_which_medal_displayed = 4
				DRAW_SPRITE	3 320.0 200.0 110.0 95.0 160 160 160 255  // gold
			ENDIF
	
			//NEW CERTIFICATE AWARDED!
			GOSUB bs_test_name_text
			SET_TEXT_EDGE 2 0 0 0 255 
			SET_TEXT_SCALE 1.0 3.4
			GET_HUD_COLOUR HUD_COLOUR_YELLOW bs_r bs_g bs_b bs_a
			SET_TEXT_COLOUR bs_r bs_g bs_b bs_a
			DISPLAY_TEXT 323.0 65.0 BS_Y_9  //NEW CERTIFICATE AWARDED!

		ENDIF
	ENDIF

	IF bs_print_top_scores_flag = 1
	OR bs_print_top_scores_flag = 2 
		// NEW HIGH SCORE
		GOSUB bs_test_name_text
		SET_TEXT_EDGE 2 0 0 0 255  
		SET_TEXT_SCALE 1.0 3.4
		SET_TEXT_COLOUR 255 255 255 255
		DISPLAY_TEXT 323.0 110.0 BS_Y_8  //NEW HIGH SCORE!
	ENDIF

	IF NOT bs_print_top_scores_flag = 2
		GOSUB bs_small_backend_text
		SET_TEXT_SCALE 0.52 1.45
		GET_HUD_COLOUR HUD_COLOUR_WHITE bs_r bs_g bs_b bs_a
		SET_TEXT_COLOUR bs_r bs_g bs_b bs_a
		DISPLAY_TEXT 330.0 345.0 BS_X05

		GOSUB bs_small_backend_text
		SET_TEXT_SCALE 0.52 1.45
		GET_HUD_COLOUR HUD_COLOUR_YELLOW bs_r bs_g bs_b bs_a
		SET_TEXT_COLOUR bs_r bs_g bs_b bs_a
		DISPLAY_TEXT 170.0 345.0 BS_Z_5
		
		GOSUB bs_small_backend_text
		SET_TEXT_SCALE 0.52 1.45
		GET_HUD_COLOUR HUD_COLOUR_WHITE bs_r bs_g bs_b bs_a
		SET_TEXT_COLOUR bs_r bs_g bs_b bs_a
		DISPLAY_TEXT 330.0 365.0 BS_X04
		
		GOSUB bs_small_backend_text
		SET_TEXT_SCALE 0.52 1.45
		GET_HUD_COLOUR HUD_COLOUR_YELLOW bs_r bs_g bs_b bs_a
		SET_TEXT_COLOUR bs_r bs_g bs_b bs_a
		DISPLAY_TEXT 170.0 365.0 BS_Z_6
	ELSE
		IF NOT bs_open_tests = 6
			GOSUB bs_small_backend_text
			SET_TEXT_SCALE 0.52 1.45
			SET_TEXT_COLOUR 255 255 255 255
			DISPLAY_TEXT 170.0 345.0 BS_Z_9
		ENDIF

		GOSUB bs_small_backend_text
		SET_TEXT_SCALE 0.52 1.45
		GET_HUD_COLOUR HUD_COLOUR_WHITE bs_r bs_g bs_b bs_a
		SET_TEXT_COLOUR bs_r bs_g bs_b bs_a
		DISPLAY_TEXT 330.0 365.0 BS_X05

		GOSUB bs_small_backend_text
		SET_TEXT_SCALE 0.52 1.45
		GET_HUD_COLOUR HUD_COLOUR_YELLOW bs_r bs_g bs_b bs_a
		SET_TEXT_COLOUR bs_r bs_g bs_b bs_a
		DISPLAY_TEXT 170.0 365.0 BS_Z_5
	ENDIF

RETURN/////////////////////////////////////////////////////////////////////////////////////

bs_skip_scores:///////////////////////////////////////////////////////////////////////////////

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

bs_mini_cleanup://////////////////////////////////////////////////////////////////////////////
	REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BIKE_AWARD_TRACK_STOP
	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
		IF flag_bikeschool_passed_1stime = 0
			IF bs_passed = 1
				GOSUB bs_draw_tv_screen
			ENDIF
		ENDIF
		CLEAR_PRINTS
	    WAIT 0
	ENDWHILE
	//SET_AREA_VISIBLE 3
	CLEAR_PRINTS
	CLEAR_THIS_BIG_PRINT BS_Y_8 
	CLEAR_THIS_BIG_PRINT BS_Y_9 
	APPLY_BRAKES_TO_PLAYERS_CAR player1 FALSE 
	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
	IF instructor_bike_dead_flag = 2
		CLEAR_PRINTS
		IF IS_CHAR_IN_ANY_CAR scplayer 
			WARP_CHAR_FROM_CAR_TO_COORD scplayer 1171.5206 1351.1826 9.9219
		ELSE
			SET_CHAR_COORDINATES scplayer 1171.5206 1351.1826 9.9219
		ENDIF
	ELSE
		IF IS_CHAR_IN_ANY_CAR scplayer 
			WARP_CHAR_FROM_CAR_TO_COORD scplayer 1171.5206 1351.1826 9.9219
		ELSE
			SET_CHAR_COORDINATES scplayer 1171.5206 1351.1826 9.9219
		ENDIF
	ENDIF
	SET_CHAR_HEADING scplayer 5.0
//	WAIT 0
	DELETE_CAR instructor_bike
	CLEAR_AREA 1171.5206 1351.1826 9.9219 2.0 TRUE
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER
//	WAIT 0
	CLEAR_ONSCREEN_TIMER car_timer
	FREEZE_ONSCREEN_TIMER FALSE
	
RETURN/////////////////////////////////////////////////////////////////////////////////////

bs_deleting_cones:////////////////////////////////////////////////////////////////////////////
	trafficcone_counter = 0
	WHILE trafficcone_counter < 46
		DELETE_OBJECT trafficcones[trafficcone_counter]
		trafficcone_counter ++
	ENDWHILE
	IF DOES_OBJECT_EXIST bs_ramp
		DELETE_OBJECT bs_ramp
	ENDIF
RETURN/////////////////////////////////////////////////////////////////////////////////////

bs_creating_cones:////////////////////////////////////////////////////////////////////////////

	trafficcone_counter = 0
	IF mission_selection = 1
		WHILE trafficcone_counter < 15
			CREATE_OBJECT_NO_OFFSET trafficcone 0.0 0.0 500.0 trafficcones[trafficcone_counter] 
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	IF mission_selection = 2
		WHILE trafficcone_counter < 25
			CREATE_OBJECT_NO_OFFSET trafficcone 0.0 0.0 500.0 trafficcones[trafficcone_counter] 
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	
	IF mission_selection = 3
	OR mission_selection = 5
		WHILE trafficcone_counter < 30
			CREATE_OBJECT_NO_OFFSET trafficcone 0.0 0.0 500.0 trafficcones[trafficcone_counter] 
			trafficcone_counter ++
		ENDWHILE
	ENDIF
	IF mission_selection = 4
	OR mission_selection = 6
		WHILE trafficcone_counter < 14
			CREATE_OBJECT_NO_OFFSET trafficcone 0.0 0.0 500.0 trafficcones[trafficcone_counter] 
			trafficcone_counter ++
		ENDWHILE
		CREATE_OBJECT_NO_OFFSET landjump2 0.0 0.0 500.0 bs_ramp 
		SET_OBJECT_COLLISION bs_ramp TRUE 
	ENDIF



	//placing cones for the 360
	IF mission_selection = 1
		cone_circle_radius = 6.0
		//creating a pure circle
		where_to_place_cones = 0.0
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike 0.0 1.0 -0.2 circle_start_x circle_start_y cone_coords_z 

		bs_creating_cone_circle4:

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
			GOTO bs_creating_cone_circle4
		ENDIF

//		trafficcone_counter = 0
//		WHILE trafficcone_counter < 15
//			SET_OBJECT_COLLISION trafficcones[trafficcone_counter] TRUE 	  
//			SET_OBJECT_DYNAMIC trafficcones[trafficcone_counter] TRUE
//			trafficcone_counter ++
//		ENDWHILE
	ENDIF

	//placing cones for the 180 
	IF mission_selection = 2
		//creating the semi circle at the end of the straight 
		cone_circle_radius = 6.0
		where_to_place_cones = 90.0
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_bike 0.0 86.0 -0.2 circle_start_x circle_start_y cone_coords_z 
		bs_creating_cone_circle3:

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
			GOTO bs_creating_cone_circle3
		ENDIF

		//RUNWAY UP TO CIRCLE
		//1st set left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[7] instructor_bike -7.0 74.0 -0.2

		//1st set right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[8] instructor_bike 7.0 74.0 -0.2

		//2nd set left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[9] instructor_bike -7.0 78.0 -0.2

		//2nd set right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[10] instructor_bike 7.0 78.0 -0.2

		//3rd set left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[11] instructor_bike -7.0 82.0 -0.2

		//3rd set right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[12] instructor_bike 7.0 82.0 -0.2

		//4th set left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[13] instructor_bike -7.0 86.0 -0.2

		//4th set right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[14] instructor_bike 7.0 86.0 -0.2

		//3rd centre cone
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[15] instructor_bike 0.0 82.0 -0.2

		//AROUND CAR
		//front left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[16] instructor_bike -3.0 4.0 -0.2

		//front right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[17] instructor_bike 3.0 4.0 -0.2

		//left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[18] instructor_bike -3.0 0.0 -0.2

		//right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[19] instructor_bike 3.0 0.0 -0.2

		//back left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[20] instructor_bike -3.0 -4.0 -0.2

		//back right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[21] instructor_bike 3.0 -4.0 -0.2

		//back of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[22] instructor_bike 0.0 -4.0 -0.2
	
		//EXTRA CENTRE CONES....
		//1st centre cone
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[23] instructor_bike 0.0 74.0 -0.2

		//2nd centre cone
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[24] instructor_bike 0.0 78.0 -0.2
		
//		trafficcone_counter = 0
//		WHILE trafficcone_counter < 25
//			SET_OBJECT_COLLISION trafficcones[trafficcone_counter] TRUE 	  
//			SET_OBJECT_DYNAMIC trafficcones[trafficcone_counter] TRUE
//			trafficcone_counter ++
//		ENDWHILE
	ENDIF

	//placing cones for Wheelie and Stoppie
	IF mission_selection = 3
	OR mission_selection = 5
		//AROUND CAR
		//front left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[0] instructor_bike -3.0 4.0 -0.2

		//front right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[1] instructor_bike 3.0 4.0 -0.2

		//left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[2] instructor_bike -3.0 0.0 -0.2

		//right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[3] instructor_bike 3.0 0.0 -0.2

		//back left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[4] instructor_bike -3.0 -4.0 -0.2

		//back right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[5] instructor_bike 3.0 -4.0 -0.2

		//back of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[6] instructor_bike 0.0 -4.0 -0.2

		// Runway
		//1st set left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[7]  instructor_bike -3.5 56.0 -0.2

		//1st set right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[8]  instructor_bike 3.5 56.0 -0.2

		//2nd set left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[9]  instructor_bike -3.5 60.0 -0.2

		//2nd set right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[10] instructor_bike 3.5 60.0 -0.2

		//3rd set left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[11] instructor_bike -3.5 64.0 -0.2

		//3rd set right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[12] instructor_bike 3.5 64.0 -0.2

		//4th set left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[13] instructor_bike -3.5 68.0 -0.2

		//4th set right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[14] instructor_bike 3.5 68.0 -0.2

		//5th set left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[15] instructor_bike -3.5 72.0 -0.2

		//5th set right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[16] instructor_bike 3.5 72.0 -0.2

		//6th set left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[17] instructor_bike -3.5 76.0 -0.2

		//6th set right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[18] instructor_bike 3.5 76.0 -0.2

		//7th set left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[19] instructor_bike -3.5 80.0 -0.2

		//7th set right   
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[20] instructor_bike 3.5 80.0 -0.2	

		//8th set left
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[21] instructor_bike -3.5 84.0 -0.2

		//8th set right
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[22] instructor_bike 3.5 84.0 -0.2

		// End of course
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[23] instructor_bike -3.0 120.0 -0.2

		//front right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[24] instructor_bike 3.0 120.0 -0.2

		//left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[25] instructor_bike -3.0 124.0 -0.2

		//right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[26] instructor_bike 3.0 124.0 -0.2

		//back left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[27] instructor_bike -3.0 128.0 -0.2

		//back right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[28] instructor_bike 3.0 128.0 -0.2

		//back of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[29] instructor_bike 0.0 128.0 -0.2

//		trafficcone_counter = 0
//		WHILE trafficcone_counter < 30
//			SET_OBJECT_COLLISION trafficcones[trafficcone_counter] TRUE 	  
//			SET_OBJECT_DYNAMIC trafficcones[trafficcone_counter] TRUE
//			trafficcone_counter ++
//		ENDWHILE

	ENDIF
		
	//placing cones for Jump And Stop
	IF mission_selection = 4
	OR mission_selection = 6
		//AROUND CAR
		//front left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[0] instructor_bike -3.0 4.0 -0.2

		//front right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[1] instructor_bike 3.0 4.0 -0.2

		//left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[2] instructor_bike -3.0 0.0 -0.2

		//right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[3] instructor_bike 3.0 0.0 -0.2

		//back left of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[4] instructor_bike -3.0 -4.0 -0.2

		//back right of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[5] instructor_bike 3.0 -4.0 -0.2

		//back of car
		PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[6] instructor_bike 0.0 -4.0 -0.2


		PLACE_OBJECT_RELATIVE_TO_CAR bs_ramp instructor_bike 0.0 48.0 -0.2

		IF mission_selection = 4
			// End of course
			PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[7] instructor_bike -3.0 120.0 -0.2

			//front right of car
			PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[8] instructor_bike 3.0 120.0 -0.2

			//left of car
			PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[9] instructor_bike -3.0 124.0 -0.2

			//right of car
			PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[10] instructor_bike 3.0 124.0 -0.2

			//back left of car
			PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[11] instructor_bike -3.0 128.0 -0.2

			//back right of car
			PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[12] instructor_bike 3.0 128.0 -0.2

			//back of car
			PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[13] instructor_bike 0.0 128.0 -0.2
		ENDIF
		IF mission_selection = 6
			// End of course
			PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[7] instructor_bike -3.0 110.0 -0.2

			//front right of car
			PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[8] instructor_bike 3.0 110.0 -0.2

			//left of car
			PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[9] instructor_bike -3.0 114.0 -0.2

			//right of car
			PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[10] instructor_bike 3.0 114.0 -0.2

			//back left of car
			PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[11] instructor_bike -3.0 118.0 -0.2

			//back right of car
			PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[12] instructor_bike 3.0 118.0 -0.2

			//back of car
			PLACE_OBJECT_RELATIVE_TO_CAR trafficcones[13] instructor_bike 0.0 118.0 -0.2
		ENDIF
//		trafficcone_counter = 0
//		WHILE trafficcone_counter < 14
//			SET_OBJECT_COLLISION trafficcones[trafficcone_counter] TRUE 	  
//			SET_OBJECT_DYNAMIC trafficcones[trafficcone_counter] TRUE
//			trafficcone_counter ++
//		ENDWHILE

	ENDIF

  

RETURN/////////////////////////////////////////////////////////////////////////////////////


}
