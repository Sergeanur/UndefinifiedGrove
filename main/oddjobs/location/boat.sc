MISSION_START
// ***********************************************************************
// ***************************** boat scripts ****************************
// ***********************************************************************

SCRIPT_NAME boat

// Mission start stuff
GOSUB mission_start_boat
IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_boat_failed
ENDIF
GOSUB mission_cleanup_boat
MISSION_END
{

IF flag_player_on_mission = 0
	
	// fool compiler double check
	
	IF boat_compiler_fool = 13
		CREATE_CAR cheetah 0.0 0.0 0.0 instructor_boat
		CREATE_CAR cheetah 5.0 5.0 10.0 instructor_heli
	  	CREATE_CHAR PEDTYPE_MISSION1 omoboat 20.0 20.0 20.0 instructor_helipilot
		CREATE_CHAR PEDTYPE_MISSION1 omoboat 30.0 30.0 20.0 instructor_captain
	    CREATE_CHAR PEDTYPE_MISSION1 omoboat 30.0 30.0 20.0 boat_blocker1driver
		CREATE_CHAR PEDTYPE_MISSION1 omoboat 30.0 30.0 20.0 boat_blocker2driver
   		CREATE_CAR cheetah 0.0 0.0 0.0 boat_blocker1
		CREATE_CAR cheetah 0.0 0.0 0.0 boat_blocker2
		CREATE_CHAR PEDTYPE_MISSION1 omoboat 30.0 30.0 20.0 boat_blocker3driver
   		CREATE_CAR cheetah 0.0 0.0 0.0 boat_blocker3


	   	buoy_counter = 0
		WHILE buoy_counter < 13
			//bouys - sic. It's written incorrectly in object.dat
			CREATE_OBJECT_NO_OFFSET bouy 0.0 0.0 100.0 boat_buoys[buoy_counter] 
			CREATE_OBJECT_NO_OFFSET waterjump1 0.0 0.0 150.0 boat_ramps[buoy_counter]

			buoy_counter ++
		ENDWHILE
 	ENDIF
ENDIF

//Globals?
VAR_INT boat_mission_selection

VAR_INT boat_introcutscene // global, as we only want player to see it once

VAR_INT boatpb_flag //boat_pause_flag
VAR_INT boat_control_flag //boat_direction_flag
VAR_INT boat_fade_flag boat_alpha

VAR_INT boat_which_medal_displayed boat_which_score_displayed

LVAR_INT sfx_video // tape noise 
LVAR_INT sfx_gull // gull screech

// Local Variables for mission

LVAR_INT boat_compiler_fool

//peds and vehicles plus related
LVAR_INT instructor_boat

LVAR_INT boat_captain 
LVAR_INT instructor_captain

LVAR_INT boat_blocker1
LVAR_INT boat_blocker2
LVAR_INT boat_blocker1driver
LVAR_INT boat_blocker2driver
LVAR_INT boat_blocker3
LVAR_INT boat_blocker3driver


LVAR_INT instructor_heli
LVAR_INT instructor_helipilot
LVAR_INT total_boat_damage
LVAR_FLOAT boat_speed


//blips

LVAR_INT boat_tvb
LVAR_INT buoyr_b
LVAR_INT buoyl_b
LVAR_INT buoyls_b
LVAR_INT buoyrs_b

//flags and arrays

LVAR_INT boat_captswap
LVAR_INT boat_buoys[50]
LVAR_INT boat_ramps[50]
LVAR_INT buoy_counter
LVAR_INT ramp_counter

LVAR_INT boat_accelerate
LVAR_INT boat_simplecircuit
LVAR_INT boat_slalom
LVAR_INT boat_skijump
LVAR_INT boat_hover

LVAR_INT boat_started
LVAR_INT instructor_boat_dead_flag
LVAR_INT finished_watching_boat_scores
LVAR_INT boat_button_pressed


LVAR_INT boat_print_top_scores_flag
LVAR_INT overall_boat_score
LVAR_INT boat_old_score

LVAR_INT b1_r b1_g b1_b b1_alpha2

//coords
LVAR_FLOAT boat_playerstartx boat_playerstarty boat_playerstartz boat_playerstarth
LVAR_FLOAT boat_captainx boat_captainy boat_captainz boat_captainh
LVAR_FLOAT boat_tvx boat_tvy boat_tvz 
LVAR_FLOAT instructor_boatx instructor_boaty instructor_boatz

LVAR_FLOAT boat_camera_positionx
LVAR_FLOAT boat_camera_positiony 
LVAR_FLOAT boat_camera_positionz 
LVAR_INT boat_camera_position_int 

LVAR_FLOAT boat_targetX
LVAR_FLOAT boat_targetY
LVAR_FLOAT boat_targetZ

LVAR_FLOAT buoyr_x 
LVAR_FLOAT buoyr_y
LVAR_FLOAT buoyr_Z

LVAR_FLOAT buoyl_x
LVAR_FLOAT buoyl_y
LVAR_FLOAT buoyl_z

LVAR_FLOAT boat_rampX
LVAR_FLOAT boat_rampY
LVAR_FLOAT boat_rampZ

//timers

VAR_INT boat_timer
LVAR_INT shown_help
LVAR_INT completion_time

//medals
LVAR_INT boat_bronze
LVAR_INT boat_silver
LVAR_INT boat_gold

LVAR_INT boat_accelerate_qualify
LVAR_INT boat_accelerate_bronze
LVAR_INT boat_accelerate_silver
LVAR_INT boat_accelerate_gold

LVAR_INT boat_simplecircuit_qualify
LVAR_INT boat_simplecircuit_bronze
LVAR_INT boat_simplecircuit_silver
LVAR_INT boat_simplecircuit_gold

LVAR_INT boat_slalom_qualify
LVAR_INT boat_slalom_bronze
LVAR_INT boat_slalom_silver
LVAR_INT boat_slalom_gold

LVAR_INT boat_skijump_qualify
LVAR_INT boat_skijump_bronze
LVAR_INT boat_skijump_silver
LVAR_INT boat_skijump_gold

LVAR_INT boat_hover_qualify
LVAR_INT boat_hover_bronze
LVAR_INT boat_hover_silver
LVAR_INT boat_hover_gold

LVAR_INT boat_best_score_medal
LVAR_INT time_penalty


//Score Output calculation bits and bobs.

LVAR_FLOAT dboat_accelerate_qualify
LVAR_FLOAT dboat_simplecircuit_qualify
LVAR_FLOAT dboat_slalom_qualify
LVAR_FLOAT dboat_skijump_qualify
LVAR_FLOAT dboat_hover_qualify
LVAR_FLOAT dboat_which_score_displayed
LVAR_FLOAT dtime_penalty
LVAR_FLOAT dcompletion_time
LVAR_FLOAT doverall_boat_score
LVAR_FLOAT boat_jumpdist1
LVAR_FLOAT boat_jumpdist2
LVAR_FLOAT boat_jumpdist3
LVAR_FLOAT boat_bestjumpdist


LVAR_INT temp_int
LVAR_INT boat_help1

// Sound
LVAR_INT buoy_sound

//Damage Checking
LVAR_INT boat_damagetaken

//debug	and new fixes
LVAR_INT boat_schoolpassed 
LVAR_INT boat_language


// ****************************************Mission Start************************************
mission_start_boat:

flag_player_on_mission = 1

IF boat_passed_once = 0
	REGISTER_MISSION_GIVEN
ENDIF

LOAD_MISSION_TEXT BOAT
SHOW_UPDATE_STATS FALSE
DISPLAY_CAR_NAMES FALSE
DISPLAY_ZONE_NAMES FALSE


IF IS_PLAYER_PLAYING player1
	SET_PLAYER_CAN_DO_DRIVE_BY player1 FALSE
ENDIF

WAIT 0


// *************************************Set Flags/variables*********************************

buoy_sound = 0 //SOUND_PART_MISSION_COMPLETE


boat_schoolpassed = 0

sfx_video = 0
sfx_gull = 0


boat_help1 = 0
boat_compiler_fool = 0
boat_mission_selection = 1

boat_tvx = -2215.5803  
boat_tvy = 2391.7
boat_tvz = 3.965

boat_playerstartx = -2185.3347 
boat_playerstarty = 2410.4092 
boat_playerstartz = 3.9752 
boat_playerstarth = 122.2585 


boat_captainx = -2216.3269 
boat_captainy =  2393.209
boat_captainz =  3.9
boat_captainh = 238.0
boat_captswap = 0


//Various tests
boat_accelerate = 0
boat_simplecircuit = 0
boat_slalom = 0
boat_skijump = 0
boat_hover = 0

boat_which_medal_displayed = 0
boat_which_score_displayed = 0
boat_old_score = 0

boat_best_score_medal = 0


//Test coords
boat_targetX = 0.0
boat_targetY = 0.0
boat_targetZ = 0.0


//Test parameters
//boat_accelerate_best_score = 60000
boat_accelerate_qualify = 12000
boat_accelerate_bronze = 12000
boat_accelerate_silver = 10500
boat_accelerate_gold = 9700

//boat_simplecircuit_best_score = 80000
boat_simplecircuit_qualify = 40000
boat_simplecircuit_bronze = 40000
boat_simplecircuit_silver = 37000
boat_simplecircuit_gold = 33000

//boat_slalom_best_score = 180000
boat_slalom_qualify = 120000
boat_slalom_bronze = 120000

boat_slalom_silver = 85000
boat_slalom_gold = 80000

//boat_skijump_best_score = 10
boat_skijump_qualify = 55//55
boat_skijump_bronze = 55//55
boat_skijump_silver = 63
boat_skijump_gold = 67

//boat_hover_best_score = 200000
boat_hover_qualify = 180000
boat_hover_bronze = 180000
boat_hover_silver = 150000
boat_hover_gold = 130000


SET_FADING_COLOUR 0 0 0
WAIT 0

//------------------REQUEST_MODELS ------------------------------

REQUEST_MODEL omoboat
REQUEST_MODEL dinghy 
REQUEST_MODEL polmav
REQUEST_MODEL coastg
REQUEST_MODEL marquis
REQUEST_MODEL tropic
REQUEST_MODEL vortex

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED omoboat
	OR NOT HAS_MODEL_LOADED dinghy
	OR NOT HAS_MODEL_LOADED polmav
	OR NOT HAS_MODEL_LOADED coastg
	WAIT 0
ENDWHILE


WHILE NOT HAS_MODEL_LOADED marquis
	OR NOT HAS_MODEL_LOADED tropic
   	OR NOT HAS_MODEL_LOADED vortex
   	WAIT 0
ENDWHILE


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


// debug settings
SET_CHAR_COORDINATES scplayer boat_playerstartx boat_playerstarty boat_playerstartz
SET_CHAR_HEADING scplayer boat_playerstarth
SET_CHAR_DROWNS_IN_WATER scplayer FALSE 

CREATE_CHAR PEDTYPE_MISSION1 omoboat boat_captainx boat_captainy boat_captainz boat_captain
SET_CHAR_HEADING boat_captain boat_captainh
SET_CHAR_NEVER_TARGETTED boat_captain TRUE

// Intro cutscene. Need only display once.

IF boat_introcutscene = 0
   boat_introcutscene = 50
ENDIF // boat_introcutscene = 0 condition check

//player has watched cutscene, now waiting on him to view tests.
boat_waiting_to_view_tests:
WAIT 0
	IF boat_introcutscene = 50
		GOTO boat_noticeboard_setup

		IF IS_CHAR_DEAD boat_captain
			CLEAR_HELP
			GOTO mission_boat_failed
		ENDIF

	ENDIF

GOTO boat_waiting_to_view_tests


///////////////////////////////NOTICEBOARD///////////////////////////////////////

boat_noticeboard_setup:
//clearing old shit away
DISPLAY_HUD FALSE
DISPLAY_RADAR FALSE

SET_PLAYER_CONTROL player1 OFF
CLEAR_HELP
CLEAR_PRINTS
SET_CAR_DENSITY_MULTIPLIER 0.0
SET_PED_DENSITY_MULTIPLIER 0.0

//preparing for noticeboard
GOSUB boat_setting_up_variables
boatpb_flag = 0
boat_control_flag = 0
USE_TEXT_COMMANDS TRUE

boat_alpha = 255
boat_fade_flag = 2

//boat_which_missions_are_open_flag = 5   //UNLOCK ALL MISSIONS FOR TESTING

IF boat_which_missions_are_open_flag < 6
	boat_mission_selection = boat_which_missions_are_open_flag
ENDIF 


GOSUB boat_drawing_tv_screen
DISPLAY_HUD FALSE
DISPLAY_RADAR FALSE
SET_AREA_VISIBLE 0

DO_FADE 500 FADE_IN

boat_mission_selection_loop:
WAIT 0
   GOSUB boat_drawing_tv_screen

		//Audio//
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
		  // write_debug aud
		   PLAY_MISSION_AUDIO 3
		   sfx_video = 3
		ENDIF
		//////////////////////////

		FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_COUNTRYSIDE
		SET_TIME_OF_DAY 12 50
		
		ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_DISABLE_HELI_AUDIO

		//CHOOSING WHICH MISSION//
		IF boat_control_flag = 0 
			
			//choosing which mission
			//IF IS_BUTTON_PRESSED PAD1 DPADLEFT 
			GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
			IF LStickX < -100
			OR IS_BUTTON_PRESSED PAD1 DPADLEFT
				//boat_mission_selection = boat_mission_selection - 1
				/*
				IF boat_mission_selection = 3
					boat_mission_selection = 2
				ENDIF	
				IF boat_mission_selection = 6
					boat_mission_selection = 5
				ENDIF	
				IF boat_mission_selection = 8
					boat_mission_selection = 7
				ENDIF	
				IF boat_mission_selection = 12
					boat_mission_selection = 11
				ENDIF  */
				IF boat_which_missions_are_open_flag > 1 		
					boat_mission_selection = boat_mission_selection - 1

					IF boatpb_flag < 4 							
						boatpb_flag = 3 //overriding playback
					ENDIF
				ENDIF	
			ENDIF

			IF LStickX > 100 
			OR IS_BUTTON_PRESSED PAD1 DPADRIGHT
			   //	boat_mission_selection = boat_mission_selection + 1
				/*IF boat_mission_selection = 3
					boat_mission_selection = 4
				ENDIF	
				IF boat_mission_selection = 6
					boat_mission_selection = 7
				ENDIF	
				IF boat_mission_selection = 8
					boat_mission_selection = 9
				ENDIF	
				IF boat_mission_selection = 12
					boat_mission_selection = 13
				ENDIF  */
				IF boat_which_missions_are_open_flag > 1 	  
					boat_mission_selection = boat_mission_selection + 1

					IF boatpb_flag < 4
						boatpb_flag = 3 //overriding playback	
					ENDIF
				ENDIF
			ENDIF								
		
			IF boat_mission_selection < 1
				boat_mission_selection = boat_which_missions_are_open_flag
			ENDIF

			IF boat_mission_selection > boat_which_missions_are_open_flag
				boat_mission_selection = 1
			ENDIF


			 
			IF boat_which_missions_are_open_flag > 1 
			WHILE LStickX < -100
			            WAIT 0
			            GOSUB boat_drawing_tv_screen
			            GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
			ENDWHILE  
			WHILE LStickX > 100
			            WAIT 0
			            GOSUB boat_drawing_tv_screen
			            GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
			ENDWHILE
		
			
			WHILE IS_BUTTON_PRESSED PAD1 DPADLEFT
				WAIT 0
				GOSUB boat_drawing_tv_screen 
			ENDWHILE  
			WHILE IS_BUTTON_PRESSED PAD1 DPADRIGHT
				WAIT 0
				GOSUB boat_drawing_tv_screen 
			ENDWHILE  
			
			ENDIF

			//triggering mission
			IF IS_BUTTON_PRESSED PAD1 CROSS
				/*
				IF boat_mission_selection = 4 
				OR boat_mission_selection = 7 
				OR boat_mission_selection = 9 
				OR boat_mission_selection = 13 
					boat_control_flag = 1	
				ELSE */
					IF boatpb_flag < 4
						boatpb_flag = 3
					ENDIF
					////WRITE_DEBUG xpressed
					CLEAR_MISSION_AUDIO 3
					sfx_video = 0
					GOTO boat_start_mission
				
			ENDIF

			//opening all the missions
			IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_q
				boat_which_missions_are_open_flag = 5
			ENDIF

			//quitting the boat school
			IF IS_BUTTON_PRESSED PAD1 TRIANGLE
				GOTO mission_boat_failed
			ENDIF

		ENDIF


	GOSUB boat_watching_demo

GOTO boat_mission_selection_loop





boat_start_mission:
DO_FADE 500 FADE_OUT
WHILE GET_FADING_STATUS
	WAIT 0
	GOSUB boat_drawing_tv_screen
ENDWHILE 
IF NOT IS_CAR_DEAD instructor_boat 
	STOP_PLAYBACK_RECORDED_CAR instructor_boat
ENDIF
RESTORE_CAMERA_JUMPCUT
DELETE_CAR instructor_boat
DELETE_CAR instructor_heli
DELETE_CHAR instructor_helipilot
DELETE_CHAR instructor_captain
GOSUB boat_deletingbuoys
boatpb_flag = 5 ///making sure playback flag doesn't fuck up missions 
DISPLAY_HUD TRUE
DISPLAY_RADAR TRUE



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////TEST1 The accelerate - Head in a straight line, accelerate another boat then return////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF boat_mission_selection = 1 
	
	boat_refresh_accelerate:

 	GOSUB boat_startsetup

	boat_setup_accelerate:

	SET_PLAYER_CONTROL player1 ON
	////WRITE_DEBUG playing

	//boat_perfect_heading = 180.0
    //boat_timer = 20000
	GOSUB boat_setting_up_variables


	//CREATE_CAR dinghy -2323.6272 2105.2903 0.0 instructor_boat 
	//SET_CAR_HEADING instructor_boat 90.0

	CREATE_CAR POLMAV -3303.6272 2105.2903 200.0 instructor_heli
	DELETE_CHAR instructor_helipilot
	CREATE_CHAR_INSIDE_CAR instructor_heli PEDTYPE_MISSION1 omoboat instructor_helipilot

	boat_help1 = 0
	
	CLEAR_CHAR_TASKS instructor_helipilot
	SET_HELI_BLADES_FULL_SPEED instructor_heli
	SET_CAR_CRUISE_SPEED instructor_heli 250.0
	HELI_GOTO_COORDS instructor_heli -2303.6272 2105.2903 200.0 200.0 200.0
	//DELETE_CHAR instructor_captain
	CREATE_CAR coastg -2323.6272 2105.2903 0.0 instructor_boat 
	CHANGE_CAR_COLOUR instructor_boat 1 114
	SET_CAR_HEADING instructor_boat 90.0
	CREATE_CHAR_INSIDE_CAR instructor_boat PEDTYPE_MISSION1 omoboat instructor_captain

  






	GOSUB boat_createbuoys

	

	IF boatpb_flag = 0
		

			ATTACH_CAMERA_TO_VEHICLE_LOOK_AT_VEHICLE instructor_heli 10.0 10.0 0.0 instructor_boat 0.0 JUMP_CUT

		   //SET_FIXED_CAMERA_POSITION -2310.6272 2105.2903 20.8322 0.0 0.0 0.0
		   //POINT_CAMERA_AT_CAR instructor_boat FIXED JUMP_CUT
		RETURN
	ENDIF

   


  

	//CLEAR_PRINTS
	//PRINT_NOW ( BOAT_1 ) 5000 4 // Use this boat to accelerate the other boat then bring it back here. 

	GOSUB boat_stopsetup

	//RESTORE_CAMERA_JUMPCUT				   
	
	PRINT_HELP ( BOAT_H2 )

	//starting challenge, setting up targets etc.
	
	SET_PLAYER_CONTROL player1 ON  // new
	 ADD_BLIP_FOR_OBJECT boat_buoys[0] buoyl_b
			ADD_BLIP_FOR_OBJECT boat_buoys[1] buoyr_b

			ADD_BLIP_FOR_OBJECT boat_buoys[6] buoyls_b
			ADD_BLIP_FOR_OBJECT boat_buoys[7] buoyrs_b

			CHANGE_BLIP_COLOUR buoyl_b YELLOW
			CHANGE_BLIP_COLOUR buoyr_b YELLOW

			CHANGE_BLIP_COLOUR buoyls_b	YELLOW
			CHANGE_BLIP_COLOUR buoyrs_b	YELLOW
	
	IF NOT IS_CAR_DEAD instructor_boat
		GET_CAR_COORDINATES instructor_boat instructor_boatx instructor_boaty instructor_boatz
		boat_targetX = instructor_boatX  - 195.0
		boat_targetY = instructor_boatY 
		boat_targetZ = instructor_boatZ
		//WRITE_DEBUG_WITH_FLOAT boatX boat_targetX
	ENDIF

	///


	boat_accelerate_loop:

	WAIT 0

	IF NOT IS_CAR_DEAD instructor_heli
	IF NOT IS_CAR_DEAD instructor_boat

		//GET_CAR_COORDINATES APB_heli1 APB_heli1x APB_heli1y APB_heli1z   //Helicopter
       	GET_CAR_COORDINATES instructor_boat instructor_boatx instructor_boaty instructor_boatz //instructor_boat
                
        //vc2_xDiff = vc2_tpCx - APB_heli1x
        //vc2_yDiff = APB_heli1y - vc2_tpCy

        //GET_HEADING_FROM_VECTOR_2D vc2_yDiff vc2_xDiff APB_heli1_orientation
                
        //SET_HELI_ORIENTATION APB_heli1 APB_heli1_orientation
                
		instructor_boatx = instructor_boatx - 150.0
		instructor_boaty = instructor_boaty - 150.0
       	
        HELI_GOTO_COORDS instructor_heli instructor_boatx instructor_boaty instructor_boatz 120.0 120.0
	ENDIF
	ENDIF

	//checking player hasnt left car
	IF IS_BUTTON_PRESSED PAD1 TRIANGLE
		instructor_boat_dead_flag = 2
	   //	REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_STOP
		GOTO after_scores_boat_accelerate																	  
	ENDIF 

	//checking car isnt dead
	GOSUB instructor_boat_dead
	IF instructor_boat_dead_flag = 1 
		GOTO mission_boat_failed
	ENDIF

	IF boat_accelerate = 0
	   PRINT_NOW BOAT_A4 5000 1
	   IF NOT IS_CAR_DEAD instructor_boat
			GET_CAR_HEALTH instructor_boat boat_damagetaken
		   //	WRITE_DEBUG_WITH_INT sd boat_damagetaken
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 CROSS
		OR IS_BUTTON_PRESSED PAD1 SQUARE
			SYNC_WATER

			boat_gold = boat_accelerate_gold
			boat_silver = boat_accelerate_silver
			boat_bronze = boat_accelerate_bronze
		   
			boat_accelerate = 1
			
			boat_timer = 0 // 30 secs
		    CLEAR_ONSCREEN_TIMER boat_timer

		   	DISPLAY_ONSCREEN_TIMER_WITH_STRING boat_timer TIMER_UP BOAT_T1
			
			GET_OBJECT_COORDINATES boat_buoys[0] buoyl_x buoyl_y buoyl_z
	 		GET_OBJECT_COORDINATES boat_buoys[1] buoyr_x buoyr_y buoyr_z
			ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z SOUND_CLAXON_START
			timera = 0 

		ENDIF	

	ENDIF

	IF boat_accelerate = 1
		
		IF NOT IS_CAR_DEAD instructor_boat

			GET_CAR_SPEED instructor_boat boat_speed
			
			IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y -30.0 FALSE
				//CLEAR_PRINTS
				PRINT_NOW BOAT_32 4000 1 // in region

				IF boat_speed < 7.0

				 //ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound

 				 CLEAR_PRINTS 
				 PRINT_NOW BOAT_33 4000 1  // finished
				 

		  		 boat_accelerate = 99 // test over
				 ENDIF
			
			ENDIF

			IF timera > 4500
				IF NOT IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y -30.0 FALSE
					CLEAR_PRINTS
				
				ENDIF
			ENDIF

			IF boat_help1 = 0
				IF timera > 4500
					boat_help1 = 1
					PRINT_HELP BOAT_A5  
				ENDIF
			ENDIF
		ENDIF
		
		IF boat_timer > 60000
		   CLEAR_PRINTS
		   PRINT_NOW BOAT_34 4000 1// time over
		   boat_accelerate = 99	// test over
		ENDIF

	ENDIF


			
		IF boat_accelerate = 99	// test over
			GOSUB freeze_boat_pos
			GOSUB checking_boat_score

			//checking overall score against the best score at present
					IF overall_boat_score < boat_accelerate_best_score  	
						boat_old_score = boat_accelerate_best_score 
						boat_accelerate_best_score = overall_boat_score
						boat_print_top_scores_flag = 1
						GOSUB boat_medal_check
				
						// log player's medal achievements for rewards section. If the player receives a silver or gold 
						// medal for a test they automatically have a bronze too, so that if the player completes the boat
						// school with 3 bronzes, a silver and a gold they will receive the "all-bronze" reward upon exiting the 
						// boat school.
						IF boat_which_medal_displayed = 4 // gold
							IF boat_accelerate_goldachieved = 0
								boat_accelerate_goldachieved = 1
								boat_accelerate_silverachieved = 1
								boat_accelerate_bronzeachieved = 1
								//WRITE_DEBUG gold1 
							ENDIF
						ENDIF

						IF boat_which_medal_displayed = 3 // silver
							IF boat_accelerate_silverachieved = 0
								boat_accelerate_silverachieved = 1
								boat_accelerate_bronzeachieved = 1
								//WRITE_DEBUG silver1
							ENDIF
						ENDIF
						
						IF boat_which_medal_displayed = 2 //bronze
						   IF boat_accelerate_bronzeachieved = 0
								boat_accelerate_bronzeachieved = 1
								//WRITE_DEBUG	bronze1
							ENDIF
						ENDIF


					ELSE
						boat_which_medal_displayed = 0	
					ENDIF 	
				
					//opening next level
					IF boat_which_missions_are_open_flag = 1
						IF overall_boat_score < boat_accelerate_qualify
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_START
						   	boat_print_top_scores_flag = 2
							boat_which_missions_are_open_flag = 2
							instructor_boat_dead_flag = 2
						ENDIF
					ENDIF



					//printing scores onscreen
					timera = 0 
					WHILE timera > -1 //10000
						WAIT 0												    

						//changing camera position 
						GOSUB boat_setting_up_camera			

						//checking player hasnt left car
						IF IS_BUTTON_PRESSED PAD1 TRIANGLE
							instructor_boat_dead_flag = 2
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_STOP
							GOTO after_scores_boat_accelerate																		  
						ENDIF 									    
					
						//displaying scores
						GOSUB display_overall_boat_score_text				   

						//checking if player has skipped watching the scores
						GOSUB skip_boat_scores
						IF finished_watching_boat_scores = 1
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_STOP
						   //	write_debug stop
							GOTO after_scores_boat_accelerate
						ENDIF
					ENDWHILE	

					 //reseting for another try
					after_scores_boat_accelerate:
					GOSUB boat_mini_cleanup
					 
					GOSUB boat_deletingbuoys
																		  
					IF instructor_boat_dead_flag = 2 
						GOTO boat_noticeboard_setup
					ELSE
						GOTO boat_refresh_accelerate
					ENDIF
		
		ENDIF // boat_accelerate = 99 condition check

	GOTO boat_accelerate_loop 

ENDIF
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////// TEST2             SIMPLE CIRCUIT             //////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF boat_mission_selection = 2 
	
	boat_refresh_simplecircuit:

 	GOSUB boat_startsetup

	boat_setup_simplecircuit:

	SET_PLAYER_CONTROL player1 ON
	////WRITE_DEBUG playing

	//boat_perfect_heading = 180.0
    //boat_timer = 20000
	GOSUB boat_setting_up_variables



	CREATE_CAR POLMAV -2303.6272 2105.2903 200.0 instructor_heli  // z used to be 10
	DELETE_CHAR instructor_helipilot
	CREATE_CHAR_INSIDE_CAR instructor_heli PEDTYPE_MISSION1 omoboat instructor_helipilot
	CLEAR_CHAR_TASKS instructor_helipilot
	SET_HELI_BLADES_FULL_SPEED instructor_heli
	SET_CAR_CRUISE_SPEED instructor_heli 250.0
	HELI_GOTO_COORDS instructor_heli -2303.6272 2105.2903 200.0 200.0 200.0		// last 3 params used to be 10
	//DELETE_CHAR instructor_captain

	CREATE_CAR coastg -2578.6709 2050.8447  0.0 instructor_boat 
	CHANGE_CAR_COLOUR instructor_boat 1 114

	SET_CAR_HEADING instructor_boat 0.0//90.0
	CREATE_CHAR_INSIDE_CAR instructor_boat PEDTYPE_MISSION1 omoboat instructor_captain



  






	GOSUB boat_createbuoys

	

	IF boatpb_flag = 0
		

			ATTACH_CAMERA_TO_VEHICLE_LOOK_AT_VEHICLE instructor_heli 10.0 10.0 0.0 instructor_boat 0.0 JUMP_CUT

		   //SET_FIXED_CAMERA_POSITION -2310.6272 2105.2903 20.8322 0.0 0.0 0.0
		   //POINT_CAMERA_AT_CAR instructor_boat FIXED JUMP_CUT
		RETURN
	ENDIF

  
	GOSUB boat_stopsetup

	//RESTORE_CAMERA_JUMPCUT

	PRINT_HELP BOAT_H2

	//starting challenge, setting up targets etc.
	
	SET_PLAYER_CONTROL player1 ON  // new

	//Getting ifrst pair of buoy marker position for angled area command so we can check that the player has passed through them
	
	IF NOT IS_CAR_DEAD instructor_boat
		GET_CAR_COORDINATES instructor_boat instructor_boatx instructor_boaty instructor_boatz
		GET_OBJECT_COORDINATES boat_buoys[0] buoyl_x buoyl_y buoyl_z
	 	GET_OBJECT_COORDINATES boat_buoys[1] buoyr_x buoyr_y buoyr_z
		ADD_BLIP_FOR_OBJECT boat_buoys[0] buoyl_b
		CHANGE_BLIP_COLOUR buoyl_b YELLOW
		ADD_BLIP_FOR_OBJECT boat_buoys[1] buoyr_b
	 	CHANGE_BLIP_COLOUR buoyr_b YELLOW
		
		ADD_BLIP_FOR_OBJECT boat_buoys[2] buoyls_b
		ADD_BLIP_FOR_OBJECT boat_buoys[3] buoyrs_b
		
		GOSUB blip_management


	 		//WRITE_DEBUG_WITH_FLOAT boatX boat_targetX
	ENDIF




	boat_simplecircuit_loop:

	WAIT 0


		
																									 

		IF NOT IS_CAR_DEAD instructor_heli
		IF NOT IS_CAR_DEAD instructor_boat

				//GET_CAR_COORDINATES APB_heli1 APB_heli1x APB_heli1y APB_heli1z   //Helicopter
           		GET_CAR_COORDINATES instructor_boat instructor_boatx instructor_boaty instructor_boatz //instructor_boat
                        
            	//vc2_xDiff = vc2_tpCx - APB_heli1x
            	//vc2_yDiff = APB_heli1y - vc2_tpCy

            	//GET_HEADING_FROM_VECTOR_2D vc2_yDiff vc2_xDiff APB_heli1_orientation
                        
            	//SET_HELI_ORIENTATION APB_heli1 APB_heli1_orientation
                        
				instructor_boatx = instructor_boatx - 150.0					 // used to be +15 for x and y
				instructor_boaty = instructor_boaty - 150.0
           	 
            	HELI_GOTO_COORDS instructor_heli instructor_boatx instructor_boaty instructor_boatz 200.0 200.0	   // used to be 20
		ENDIF
		ENDIF


		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_boat_dead_flag = 2
		   //	REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_STOP
			GOTO after_scores_boat_simplecircuit																  
		ENDIF 

		//checking car isnt dead
		GOSUB instructor_boat_dead
		IF instructor_boat_dead_flag = 1 
			GOTO mission_boat_failed
		ENDIF

	IF boat_simplecircuit = 0

		PRINT_NOW BOAT_B4 4000 1  
		
		IF IS_BUTTON_PRESSED PAD1 CROSS
		OR IS_BUTTON_PRESSED PAD1 SQUARE

			SYNC_WATER
			boat_gold = boat_simplecircuit_gold
			boat_silver = boat_simplecircuit_silver
			boat_bronze = boat_simplecircuit_bronze

			boat_simplecircuit = 5
		  //	ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z SOUND_RACE_START_GO

			boat_timer = 0 
		   
			CLEAR_ONSCREEN_TIMER boat_timer

		   	DISPLAY_ONSCREEN_TIMER_WITH_STRING boat_timer TIMER_UP BOAT_T1
		ENDIF	
	ENDIF


	IF boat_simplecircuit > 0
		IF boat_timer > 600000
		   boat_simplecircuit = 99	// test over
		ENDIF

	ENDIF

	IF boat_simplecircuit = 5
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!
				 CLEAR_HELP 
				 PRINT_HELP BOAT_B5  

				 boat_simplecircuit = 10 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[2] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[3] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[2] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[3] buoyr_b
		  

				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[4] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[5] buoyrs_b
			   	 GOSUB blip_management



				 //WRITE_DEBUG  next23
			ENDIF

		ENDIF  
	ENDIF

	IF boat_simplecircuit = 10
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_simplecircuit = 15 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[4] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[5] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[4] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[5] buoyr_b
			     
			     REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[6] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[7] buoyrs_b
			   	 GOSUB blip_management

				// WRITE_DEBUG next45
			ENDIF
		ENDIF  
	ENDIF

	IF boat_simplecircuit = 15
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!
	  			 boat_simplecircuit = 20 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[6] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[7] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[6] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[7] buoyr_b
				 
				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[8] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[9] buoyrs_b
			   	 GOSUB blip_management

				// WRITE_DEBUG next67
			ENDIF
		ENDIF  
	ENDIF

	IF boat_simplecircuit = 20
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_simplecircuit = 25 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[8] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[9] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[8] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[9] buoyr_b
			   	 
			   	 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[10] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[11] buoyrs_b
			   	 GOSUB blip_management

				// WRITE_DEBUG next89
			ENDIF
		ENDIF  
	ENDIF

	IF boat_simplecircuit = 25
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_simplecircuit = 30 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[10] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[11] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[10] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[11] buoyr_b
			  	 
			  	 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[12] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[13] buoyrs_b
			   	 GOSUB blip_management

				// WRITE_DEBUG next1011
			ENDIF
		ENDIF  
	ENDIF



	IF boat_simplecircuit = 30
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_simplecircuit = 35 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[12] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[13] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[12] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[13] buoyr_b
				 
				  REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[14] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[15] buoyrs_b
			   	 GOSUB blip_management

				// WRITE_DEBUG next1213
			ENDIF
		ENDIF  
	ENDIF

	IF boat_simplecircuit = 35
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_simplecircuit = 40 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[14] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[15] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[14] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[15] buoyr_b
				  
				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 //ADD_BLIP_FOR_OBJECT boat_buoys[16] buoyls_b
				 //ADD_BLIP_FOR_OBJECT boat_buoys[17] buoyrs_b
			   	 GOSUB blip_management

				// WRITE_DEBUG next1415
			ENDIF
		ENDIF  
	ENDIF



	IF boat_simplecircuit = 40
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_33 1000 1 // checkpoint ping finished!

	  			 boat_simplecircuit = 99 // go to complete
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 //GET_OBJECT_COORDINATES boat_buoys[0] buoyl_x buoyl_y buoyl_z
 				 //GET_OBJECT_COORDINATES boat_buoys[1] buoyr_x buoyr_y buoyr_z
				 //ADD_BLIP_FOR_OBJECT boat_buoys[0] buoyl_b
				 //ADD_BLIP_FOR_OBJECT boat_buoys[1] buoyr_b
			ENDIF
		ENDIF  
	ENDIF




			
		IF boat_simplecircuit = 99	// test over
			GOSUB freeze_boat_pos
			GOSUB checking_boat_score

			//checking overall score against the best score at present
					IF overall_boat_score < boat_simplecircuit_best_score  	
						boat_old_score = boat_simplecircuit_best_score 
						boat_simplecircuit_best_score = overall_boat_score
						boat_print_top_scores_flag = 1
						GOSUB boat_medal_check

						// log player's medal achievements for rewards section. If the player receives a silver or gold 
						// medal for a test they automatically have a bronze too, so that if the player completes the boat
						// school with 3 bronzes, a silver and a gold they will receive the "all-bronze" reward upon exiting the 
						// boat school.
						IF boat_which_medal_displayed = 4 // gold
							IF boat_simplecircuit_goldachieved = 0
								boat_simplecircuit_goldachieved = 1
								boat_simplecircuit_silverachieved = 1
								boat_simplecircuit_bronzeachieved = 1
								//WRITE_DEBUG gold1 
							ENDIF
						ENDIF

						IF boat_which_medal_displayed = 3 // silver
							IF boat_simplecircuit_silverachieved = 0
								boat_simplecircuit_silverachieved = 1
								boat_simplecircuit_bronzeachieved = 1
								//WRITE_DEBUG silver1
							ENDIF
						ENDIF
						
						IF boat_which_medal_displayed = 2 //bronze
						   IF boat_simplecircuit_bronzeachieved = 0
								boat_simplecircuit_bronzeachieved = 1
								//WRITE_DEBUG	bronze1
							ENDIF
						ENDIF

					ELSE
				   	boat_which_medal_displayed = 0	
					ENDIF 	 	
				
					//opening next level
					IF boat_which_missions_are_open_flag = 2
						IF overall_boat_score < boat_simplecircuit_qualify
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_START

							boat_print_top_scores_flag = 2
							boat_which_missions_are_open_flag = 3
							instructor_boat_dead_flag = 2
						ENDIF
					ENDIF



					//printing scores onscreen
					timera = 0 
					WHILE timera > -1 //< 10000
						WAIT 0												    

						//changing camera position 
						GOSUB boat_setting_up_camera			

						//checking player hasnt left car
						If IS_BUTTON_PRESSED PAD1 TRIANGLE

							instructor_boat_dead_flag = 2
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_STOP
							GOTO after_scores_boat_simplecircuit																		  
						ENDIF 									    
					
						//displaying scores
						GOSUB display_overall_boat_score_text				   

						//checking if player has skipped watching the scores
						GOSUB skip_boat_scores
						IF finished_watching_boat_scores = 1
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_STOP

							GOTO after_scores_boat_simplecircuit
						ENDIF
					ENDWHILE	

					 //reseting for another try
					after_scores_boat_simplecircuit:
					GOSUB boat_mini_cleanup
					 
					GOSUB boat_deletingbuoys
																		  
					IF instructor_boat_dead_flag = 2 
						GOTO boat_noticeboard_setup
					ELSE
						GOTO boat_refresh_simplecircuit
					ENDIF





		
		ENDIF // boat_simplecircuit = 99 condition check

		
		

			
		



	GOTO boat_simplecircuit_loop 

ENDIF
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////TEST3 Smoked Slalom //////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF boat_mission_selection = 3


boat_refresh_slalom:

 	GOSUB boat_startsetup

	boat_setup_slalom:

	SET_PLAYER_CONTROL player1 ON
	////WRITE_DEBUG playing

	//boat_perfect_heading = 180.0
    //boat_timer = 20000
	GOSUB boat_setting_up_variables



	CREATE_CAR POLMAV -2303.6272 2105.2903 200.0 instructor_heli
	DELETE_CHAR instructor_helipilot
	CREATE_CHAR_INSIDE_CAR instructor_heli PEDTYPE_MISSION1 omoboat instructor_helipilot
	CLEAR_CHAR_TASKS instructor_helipilot
	SET_HELI_BLADES_FULL_SPEED instructor_heli
	SET_CAR_CRUISE_SPEED instructor_heli 250.0
	HELI_GOTO_COORDS instructor_heli -2303.6272 2105.2903 200.0 200.0 200.0
	//DELETE_CHAR instructor_captain

	
	CREATE_CAR dinghy -2124.1255 2438.3518  0.0 instructor_boat 
	CHANGE_CAR_COLOUR instructor_boat 114 1

	SET_CAR_HEADING instructor_boat 180.0
	CREATE_CHAR_INSIDE_CAR instructor_boat PEDTYPE_MISSION1 omoboat instructor_captain



  






	GOSUB boat_createbuoys

	

	IF boatpb_flag = 0

			ATTACH_CAMERA_TO_VEHICLE_LOOK_AT_VEHICLE instructor_heli 10.0 10.0 0.0 instructor_boat 0.0 JUMP_CUT

		   //SET_FIXED_CAMERA_POSITION -2310.6272 2105.2903 20.8322 0.0 0.0 0.0
		   //POINT_CAMERA_AT_CAR instructor_boat FIXED JUMP_CUT
		RETURN
	ENDIF

  
	GOSUB boat_stopsetup

	//RESTORE_CAMERA_JUMPCUT


	//starting challenge, setting up targets etc.

	PRINT_HELP BOAT_H2  
	SET_PLAYER_CONTROL player1 ON  // new

	//Getting ifrst pair of buoy marker position for angled area command so we can check that the player has passed through them
	
	IF NOT IS_CAR_DEAD instructor_boat
		GET_CAR_COORDINATES instructor_boat instructor_boatx instructor_boaty instructor_boatz
		GET_OBJECT_COORDINATES boat_buoys[0] buoyl_x buoyl_y buoyl_z
	 	GET_OBJECT_COORDINATES boat_buoys[1] buoyr_x buoyr_y buoyr_z
		ADD_BLIP_FOR_OBJECT boat_buoys[0] buoyl_b
		ADD_BLIP_FOR_OBJECT boat_buoys[1] buoyr_b
		 CHANGE_BLIP_COLOUR buoyl_b YELLOW
				 CHANGE_BLIP_COLOUR buoyr_b YELLOW

		ADD_BLIP_FOR_OBJECT boat_buoys[2] buoyls_b
		ADD_BLIP_FOR_OBJECT boat_buoys[3] buoyrs_b
		
		GOSUB blip_management


		/*
		DELETE_CHAR boat_blocker1driver
		DELETE_CHAR boat_blocker2driver
		DELETE_CAR boat_blocker1
		DELETE_CAR boat_blocker2
		CREATE_CAR marquis -2100.8364 2247.8623 0.0 boat_blocker1 
		SET_CAR_HEADING boat_blocker1 122.0
		CREATE_CHAR_INSIDE_CAR boat_blocker1 PEDTYPE_MISSION1 omoboat boat_blocker1driver
		  */
	 		
	 		//WRITE_DEBUG_WITH_FLOAT boatX boat_targetX
	ENDIF



	sfx_gull = 0


	boat_slalom_loop:

	WAIT 0


		
		IF sfx_gull = 0
			LOAD_MISSION_AUDIO 3 SOUND_GULL_SCREECH
			sfx_gull = 1
		ENDIF

		IF sfx_gull = 1
		 	IF HAS_MISSION_AUDIO_LOADED 3
				sfx_gull = 2
		 	ENDIF
		ENDIF


																									 

		IF NOT IS_CAR_DEAD instructor_heli
		IF NOT IS_CAR_DEAD instructor_boat

				//GET_CAR_COORDINATES APB_heli1 APB_heli1x APB_heli1y APB_heli1z   //Helicopter
           		GET_CAR_COORDINATES instructor_boat instructor_boatx instructor_boaty instructor_boatz //instructor_boat
                        
            	//vc2_xDiff = vc2_tpCx - APB_heli1x
            	//vc2_yDiff = APB_heli1y - vc2_tpCy

            	//GET_HEADING_FROM_VECTOR_2D vc2_yDiff vc2_xDiff APB_heli1_orientation
                        
            	//SET_HELI_ORIENTATION APB_heli1 APB_heli1_orientation
                        
				instructor_boatx = instructor_boatx - 150.0//150.0
				instructor_boaty = instructor_boaty - 150.0//150.0
           	 
            	HELI_GOTO_COORDS instructor_heli instructor_boatx instructor_boaty instructor_boatz 200.0 200.0//200.0 200.0
		ENDIF
		ENDIF


		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_boat_dead_flag = 2
			//REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_STOP
			GOTO after_scores_boat_slalom																  
		ENDIF 

		//checking car isnt dead
		GOSUB instructor_boat_dead
		IF instructor_boat_dead_flag = 1 
			GOTO mission_boat_failed
		ENDIF


	
	

	IF boat_slalom = 0
		PRINT_NOW BOAT_C1 4000 1

		IF IS_BUTTON_PRESSED PAD1 CROSS
		OR IS_BUTTON_PRESSED PAD1 SQUARE

			SYNC_WATER
			boat_gold = boat_slalom_gold
			boat_silver = boat_slalom_silver
			boat_bronze = boat_slalom_bronze
			ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z SOUND_RACE_START_GO

			boat_slalom = 5
			boat_timer = 0 
		   	CLEAR_ONSCREEN_TIMER boat_timer

		   	DISPLAY_ONSCREEN_TIMER_WITH_STRING boat_timer TIMER_UP BOAT_T1
		ENDIF	
	ENDIF


	IF boat_slalom > 0
		IF boat_timer > 600000
		   boat_slalom = 99	// test over
		ENDIF

	ENDIF

	IF boat_slalom = 5
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_slalom = 10 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[2] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[3] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[2] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[3] buoyr_b

		   	  	 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[4] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[5] buoyrs_b
			   	 GOSUB blip_management

			ENDIF
		ENDIF  
	ENDIF

	IF boat_slalom = 10
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_slalom = 15 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[4] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[5] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[4] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[5] buoyr_b
				  	 
				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[6] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[7] buoyrs_b
			   	 GOSUB blip_management

			ENDIF
		ENDIF  
	ENDIF

	IF boat_slalom = 15
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_slalom = 20 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[6] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[7] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[6] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[7] buoyr_b
				  	 
				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[8] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[9] buoyrs_b
			   	 GOSUB blip_management

				 IF NOT IS_CAR_DEAD boat_blocker3
				 IF NOT IS_CHAR_DEAD boat_blocker3driver
					 IF sfx_gull = 2
						//WRITE_DEBUG aud2
						PLAY_MISSION_AUDIO 3
				 	 ENDIF
				 TASK_CAR_DRIVE_TO_COORD boat_blocker3driver boat_blocker3 -2013.4762 1748.4989 0.0 18.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
				  //WRITE_DEBUG going3
				 ENDIF
				 ENDIF

			ENDIF
		ENDIF  
	ENDIF


	IF boat_slalom = 20
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_slalom = 25 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[8] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[9] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[8] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[9] buoyr_b

			     REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[10] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[11] buoyrs_b
			   	 GOSUB blip_management

			ENDIF
		ENDIF  
	ENDIF

	
	IF boat_slalom = 25
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_slalom = 30 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[10] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[11] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[10] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[11] buoyr_b
				   
				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[12] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[13] buoyrs_b
			   	 GOSUB blip_management

			ENDIF
		ENDIF  
	ENDIF

	IF boat_slalom = 30
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_slalom = 35 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[12] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[13] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[12] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[13] buoyr_b
				  
				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[14] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[15] buoyrs_b
			   	 GOSUB blip_management

			ENDIF
		ENDIF  
	ENDIF

	IF boat_slalom = 35
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_slalom = 40 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[14] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[15] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[14] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[15] buoyr_b
				  
				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[16] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[17] buoyrs_b
			   	 GOSUB blip_management

			ENDIF
		ENDIF  
	ENDIF

	IF boat_slalom = 40
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_slalom = 45 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[16] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[17] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[16] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[17] buoyr_b
			     
			     REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[18] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[19] buoyrs_b
			   	 GOSUB blip_management

			ENDIF
		ENDIF  
	ENDIF
	IF boat_slalom = 45
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_slalom = 50 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[18] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[19] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[18] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[19] buoyr_b
			    
			     REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[20] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[21] buoyrs_b
			   	 GOSUB blip_management


			ENDIF
		ENDIF  
	ENDIF
	IF boat_slalom = 50
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_slalom = 55 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[20] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[21] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[20] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[21] buoyr_b
				 
				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[22] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[23] buoyrs_b
			   	 GOSUB blip_management

			ENDIF
		ENDIF  
	ENDIF

	IF boat_slalom = 55
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_slalom = 60 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[22] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[23] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[22] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[23] buoyr_b
				 
				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[24] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[25] buoyrs_b
			   	 GOSUB blip_management

			ENDIF
		ENDIF  
	ENDIF

	IF boat_slalom = 60
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_slalom = 65 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[24] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[25] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[24] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[25] buoyr_b
				 
				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[26] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[27] buoyrs_b
			   	 GOSUB blip_management

				 IF NOT IS_CAR_DEAD boat_blocker2
				 IF NOT IS_CHAR_DEAD boat_blocker2driver

					 IF sfx_gull = 2
						//WRITE_DEBUG aud2
						PLAY_MISSION_AUDIO 3
				 	 ENDIF

				

				 TASK_CAR_DRIVE_TO_COORD boat_blocker2driver boat_blocker2 -2763.1055 1954.1290 0.0 10.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
				 // WRITE_DEBUG going2
				 ENDIF
				 ENDIF
			ENDIF
		ENDIF  
	ENDIF
	IF boat_slalom = 65
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_slalom = 70 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[26] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[27] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[26] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[27] buoyr_b
				   
				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[0] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[1] buoyrs_b
			   	 GOSUB blip_management

				 IF NOT IS_CAR_DEAD boat_blocker1
				 IF NOT IS_CHAR_DEAD boat_blocker1driver

					 IF sfx_gull = 2
						//WRITE_DEBUG aud2
						PLAY_MISSION_AUDIO 3
				 	 ENDIF


				 TASK_CAR_DRIVE_TO_COORD boat_blocker1driver boat_blocker1 -2763.1055 1954.1290 0.0 15.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
				 // WRITE_DEBUG going1
				 ENDIF
				 ENDIF
			ENDIF
		ENDIF  
	ENDIF

	IF boat_slalom = 70
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_slalom = 80 // go to final buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[0] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[1] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[0] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[1] buoyr_b
				 CHANGE_BLIP_COLOUR buoyl_b	YELLOW
				 CHANGE_BLIP_COLOUR buoyr_b	YELLOW


				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				  //CHANGE_BLIP_COLOUR buoyl_b YELLOW
				 //CHANGE_BLIP_COLOUR buoyr_b YELLOW

			ENDIF
		ENDIF  
	ENDIF



	IF boat_slalom = 80
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_33 1000 1 // checkpoint ping!

	  			 boat_slalom = 99 // go to complete
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 //GET_OBJECT_COORDINATES boat_buoys[0] buoyl_x buoyl_y buoyl_z
 				 //GET_OBJECT_COORDINATES boat_buoys[1] buoyr_x buoyr_y buoyr_z
				 //ADD_BLIP_FOR_OBJECT boat_buoys[0] buoyl_b
				 //ADD_BLIP_FOR_OBJECT boat_buoys[1] buoyr_b
			ENDIF
		ENDIF  
	ENDIF




			
		IF boat_slalom = 99	// test over
			GOSUB freeze_boat_pos
			GOSUB checking_boat_score

			//checking overall score against the best score at present
					IF overall_boat_score < boat_slalom_best_score  	
						boat_old_score = boat_slalom_best_score 
						boat_slalom_best_score = overall_boat_score
						boat_print_top_scores_flag = 1
						GOSUB boat_medal_check

						// log player's medal achievements for rewards section. If the player receives a silver or gold 
						// medal for a test they automatically have a bronze too, so that if the player completes the boat
						// school with 3 bronzes, a silver and a gold they will receive the "all-bronze" reward upon exiting the 
						// boat school.
						IF boat_which_medal_displayed = 4 // gold
							IF boat_slalom_goldachieved = 0
								boat_slalom_goldachieved = 1
								boat_slalom_silverachieved = 1
								boat_slalom_bronzeachieved = 1
								//WRITE_DEBUG gold1 
							ENDIF
						ENDIF

						IF boat_which_medal_displayed = 3 // silver
							IF boat_slalom_silverachieved = 0
								boat_slalom_silverachieved = 1
								boat_slalom_bronzeachieved = 1
								//WRITE_DEBUG silver1
							ENDIF
						ENDIF
						
						IF boat_which_medal_displayed = 2 //bronze
						   IF boat_slalom_bronzeachieved = 0
								boat_slalom_bronzeachieved = 1
								//WRITE_DEBUG	bronze1
							ENDIF
						ENDIF


					ELSE
						boat_which_medal_displayed = 0	
					ENDIF 	 	
				
					//opening next level
					IF boat_which_missions_are_open_flag = 3
						IF overall_boat_score < boat_slalom_qualify
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_START

							boat_print_top_scores_flag = 2
							boat_which_missions_are_open_flag = 4
							instructor_boat_dead_flag = 2
						ENDIF
					ENDIF



					//printing scores onscreen
					timera = 0 
					WHILE timera > -1 //< 10000
						WAIT 0												    

						//changing camera position 
						GOSUB boat_setting_up_camera			

						//checking player hasnt left car
						IF IS_BUTTON_PRESSED PAD1 TRIANGLE
							instructor_boat_dead_flag = 2
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_STOP
							GOTO after_scores_boat_slalom																		  
						ENDIF 									    
					
						//displaying scores
						GOSUB display_overall_boat_score_text				   

						//checking if player has skipped watching the scores
						GOSUB skip_boat_scores
						IF finished_watching_boat_scores = 1
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_STOP

							GOTO after_scores_boat_slalom
						ENDIF
					ENDWHILE	

					 //reseting for another try
					after_scores_boat_slalom:
					GOSUB boat_mini_cleanup
					 
					GOSUB boat_deletingbuoys
																		  
					IF instructor_boat_dead_flag = 2 
						GOTO boat_noticeboard_setup
					ELSE
						GOTO boat_refresh_slalom
					ENDIF





		
		ENDIF // boat_slalom = 99 condition check

		
		

			
		



	GOTO boat_slalom_loop 


ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////// TEST4    SKIJUMP  ////////////////////////////////////////////////////////////////////////////////////////////////////

IF boat_mission_selection = 4 
	
	boat_refresh_skijump:

 	GOSUB boat_startsetup

	boat_setup_skijump:
//	DO_FADE 500 FADE_OUT
//		WHILE GET_FADING_STATUS
//				 WAIT 0
//		ENDWHILE


	SET_PLAYER_CONTROL player1 ON
	////WRITE_DEBUG playing

	//boat_perfect_heading = 180.0
    //boat_timer = 20000
	GOSUB boat_setting_up_variables


	//CREATE_CAR dinghy -2323.6272 2105.2903 0.0 instructor_boat 
	//SET_CAR_HEADING instructor_boat 90.0

	CREATE_CAR POLMAV -3303.6272 2105.2903 200.0 instructor_heli
	DELETE_CHAR instructor_helipilot
	CREATE_CHAR_INSIDE_CAR instructor_heli PEDTYPE_MISSION1 omoboat instructor_helipilot
	
	CLEAR_CHAR_TASKS instructor_helipilot
	SET_HELI_BLADES_FULL_SPEED instructor_heli
	SET_CAR_CRUISE_SPEED instructor_heli 250.0
	HELI_GOTO_COORDS instructor_heli -2303.6272 2105.2903 200.0 200.0 200.0
	//DELETE_CHAR instructor_captain
	
	CREATE_CAR vortex -2584.3164 2213.4749 0.0 instructor_boat 
	CHANGE_CAR_COLOUR instructor_boat 78 14
	SET_CAR_HEADING instructor_boat 250.0
	CREATE_CHAR_INSIDE_CAR instructor_boat PEDTYPE_MISSION1 omoboat instructor_captain

	

  






	GOSUB boat_createbuoys



	// New test section
	IF NOT IS_CAR_DEAD instructor_boat
	  	 SET_CAR_COORDINATES instructor_boat -2318.0493 2312.3018 10.0
  		 SET_CAR_HEADING instructor_boat 174.6078 

	ENDIF

	

	IF boatpb_flag = 0
		
			ATTACH_CAMERA_TO_VEHICLE_LOOK_AT_VEHICLE instructor_heli 10.0 10.0 0.0 instructor_boat 0.0 JUMP_CUT

		   //SET_FIXED_CAMERA_POSITION -2310.6272 2105.2903 20.8322 0.0 0.0 0.0
		   //POINT_CAMERA_AT_CAR instructor_boat FIXED JUMP_CUT
		RETURN
	ENDIF

   GOSUB boat_stopsetup

	PRINT_HELP BOAT_H2  

	SET_PLAYER_CONTROL player1 ON  // new

	IF NOT IS_CAR_DEAD instructor_boat
	  	boat_targetX = 0.0
		boat_targetY = 0.0
		boat_targetZ = 0.0
		boat_rampX = 0.0
		boat_rampY = 0.0
		boat_rampZ = 0.0
	ENDIF

	boat_skijump_loop:
	WAIT 0

		IF NOT IS_CAR_DEAD instructor_heli
		IF NOT IS_CAR_DEAD instructor_boat

				//GET_CAR_COORDINATES APB_heli1 APB_heli1x APB_heli1y APB_heli1z   //Helicopter
           		GET_CAR_COORDINATES instructor_boat instructor_boatx instructor_boaty instructor_boatz //instructor_boat
                        
            	//vc2_xDiff = vc2_tpCx - APB_heli1x
            	//vc2_yDiff = APB_heli1y - vc2_tpCy

            	//GET_HEADING_FROM_VECTOR_2D vc2_yDiff vc2_xDiff APB_heli1_orientation
                        
            	//SET_HELI_ORIENTATION APB_heli1 APB_heli1_orientation
                        
				instructor_boatx = instructor_boatx - 150.0
				instructor_boaty = instructor_boaty - 150.0
           	 
            	HELI_GOTO_COORDS instructor_heli instructor_boatx instructor_boaty instructor_boatz 120.0 120.0
		ENDIF
		ENDIF


		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_boat_dead_flag = 2
		   //	REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_STOP
			GOTO after_scores_boat_skijump																	  
		ENDIF 

		//checking car isnt dead
		GOSUB instructor_boat_dead
		IF instructor_boat_dead_flag = 1 
			GOTO mission_boat_failed
		ENDIF


	
	

	IF boat_skijump = 0
		PRINT_NOW BOAT_D1 4000 1

		IF IS_BUTTON_PRESSED PAD1 CROSS
		OR IS_BUTTON_PRESSED PAD1 SQUARE

			SYNC_WATER
			boat_gold = boat_skijump_gold
			boat_silver = boat_skijump_silver
			boat_bronze = boat_skijump_bronze

		    CLEAR_ONSCREEN_TIMER boat_timer
			boat_timer = 46000 // 41 secs
			boat_skijump = 1

		    DISPLAY_ONSCREEN_TIMER_WITH_STRING boat_timer TIMER_DOWN BOAT_T1

			REMOVE_BLIP buoyl_b
			REMOVE_BLIP buoyr_b
			ADD_BLIP_FOR_OBJECT boat_buoys[0] buoyl_b
			ADD_BLIP_FOR_OBJECT boat_buoys[1] buoyr_b
			 CHANGE_BLIP_COLOUR buoyl_b YELLOW
				 CHANGE_BLIP_COLOUR buoyr_b YELLOW

			GET_OBJECT_COORDINATES boat_buoys[0] buoyl_x buoyl_y buoyl_z
	 		GET_OBJECT_COORDINATES boat_buoys[1] buoyr_x buoyr_y buoyr_z
			ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z SOUND_RACE_START_GO


		ENDIF	
	ENDIF

	IF boat_skijump = 1
		
		IF NOT IS_CAR_DEAD instructor_boat
			IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_3D scplayer buoyl_x buoyl_y 1.0 buoyr_x buoyr_y 3.0 10.0 FALSE // height of ramp
				ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				REMOVE_BLIP buoyl_b
				REMOVE_BLIP buoyr_b
				GET_CAR_SPEED instructor_boat boat_speed
				boat_skijump = 2
				GET_OBJECT_COORDINATES boat_buoys[2] buoyl_x buoyl_y buoyl_z
				GET_OBJECT_COORDINATES boat_buoys[3] buoyr_x buoyr_y buoyr_z
				

				timerb = 0
			ENDIF			
		ENDIF
	ENDIF

	IF boat_skijump	= 2
		
	   	IF NOT IS_CAR_DEAD instructor_boat
		//SET_CAR_ROLL instructor_boat 0.0
		IF timerb > 1000

	
		   //IF IS_CAR_IN_WATER instructor_boat
		   GET_CAR_COORDINATES instructor_boat boat_targetx boat_targety boat_targetz 			
			IF boat_targetz < 1.0
			   //	WRITE_DEBUG inwater
				IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 600.0 FALSE 
				   ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				   boat_skijump = 3
				   timerb = 0
				   //WRITE_DEBUG landed

				ENDIF

				IF NOT IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 600.0 FALSE 
					   					
					boat_skijump = 4
					timerb = 0
					//WRITE_DEBUG invalid
					
				ENDIF
			ENDIF
		
		ENDIF
		ENDIF
	ENDIF

	IF boat_skijump = 3
		GET_OBJECT_COORDINATES boat_ramps[0] boat_rampX boat_rampy boat_rampz
	   	GET_DISTANCE_BETWEEN_COORDS_2D boat_rampX boat_rampY boat_targetX boat_targety boat_bestjumpdist

		// test area
		//boat_bestjumpdist = 63.00

		WHILE timerb < 1000
			WAIT 0
		ENDWHILE
		boat_skijump = 99


	ENDIF

	IF boat_skijump = 4
		WHILE timerb < 1000
			WAIT 0
		ENDWHILE
		boat_bestjumpdist = 0.0
		//WRITE_DEBUG invalid

		boat_skijump = 99
	ENDIF

	IF boat_skijump > 0
		IF boat_timer < 1//30000
		   CLEAR_PRINTS
		   PRINT_NOW BOAT_34 4000 1// time over
		   boat_skijump = 99	// test over
		   WAIT 3000
		   GOTO after_scores_boat_skijump

		ENDIF
	ENDIF


			
		IF boat_skijump = 99	// test over
			GOSUB freeze_boat_pos
			GOSUB checking_boat_score
			//WRITE_DEBUG_WITH_INT overall overall_boat_score
			//WRITE_DEBUG_WITH_INT best boat_skijump_best_score

			//checking overall score against the best score at present
					IF overall_boat_score > boat_skijump_best_score 
					OR overall_boat_score > boat_skijump_best_score 	
						boat_old_score = boat_skijump_best_score 
						boat_skijump_best_score = overall_boat_score
						boat_print_top_scores_flag = 1
						//WRITE_DEBUG mcheck
						GOSUB boat_medal_check

						// log player's medal achievements for rewards section. If the player receives a silver or gold 
						// medal for a test they automatically have a bronze too, so that if the player completes the boat
						// school with 3 bronzes, a silver and a gold they will receive the "all-bronze" reward upon exiting the 
						// boat school.
						IF boat_which_medal_displayed = 4 // gold
							IF boat_skijump_goldachieved = 0
								boat_skijump_goldachieved = 1
								boat_skijump_silverachieved = 1
								boat_skijump_bronzeachieved = 1
								//WRITE_DEBUG gold1 
							ENDIF
						ENDIF

						IF boat_which_medal_displayed = 3 // silver
							IF boat_skijump_silverachieved = 0
								boat_skijump_silverachieved = 1
								boat_skijump_bronzeachieved = 1
								//WRITE_DEBUG silver1
							ENDIF
						ENDIF
						
						IF boat_which_medal_displayed = 2 //bronze
						   IF boat_skijump_bronzeachieved = 0
								boat_skijump_bronzeachieved = 1
								//WRITE_DEBUG	bronze1
							ENDIF
						ENDIF

					ELSE
						boat_which_medal_displayed = 0	
					ENDIF 		
				
					//opening next level
					IF boat_which_missions_are_open_flag = 4
						
						IF overall_boat_score > boat_skijump_qualify
						OR overall_boat_score = boat_skijump_qualify
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_START

							boat_print_top_scores_flag = 2
							boat_which_missions_are_open_flag = 5
							instructor_boat_dead_flag = 2
						ENDIF
						/*
						IF boat_bestjumpdist < boat_skijump_qualify
							boat_print_top_scores_flag = 2
							boat_which_missions_are_open_flag = 5
							instructor_boat_dead_flag = 2
						ENDIF
						*/
					ENDIF



					//printing scores onscreen
					timera = 0 
					WHILE timera > -1//< 10000
						WAIT 0												    

						//changing camera position 
						GOSUB boat_setting_up_camera			

						//checking player hasnt left car
						IF IS_BUTTON_PRESSED PAD1 TRIANGLE
							instructor_boat_dead_flag = 2
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_STOP
							GOTO after_scores_boat_skijump																		  
						ENDIF 									    
					
						//displaying scores
						GOSUB display_overall_boat_score_text				   

						//checking if player has skipped watching the scores
						GOSUB skip_boat_scores
						IF finished_watching_boat_scores = 1
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_STOP

							GOTO after_scores_boat_skijump
						ENDIF
					ENDWHILE	

					 //reseting for another try
					after_scores_boat_skijump:
					GOSUB boat_mini_cleanup
					 
					GOSUB boat_deletingbuoys
																		  
					IF instructor_boat_dead_flag = 2 
						GOTO boat_noticeboard_setup
					ELSE
						GOTO boat_refresh_skijump
					ENDIF





		
		ENDIF // boat_skijump = 99 condition check

		
		

			
		



	GOTO boat_skijump_loop 

ENDIF

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////TEST5 Hover Ups and downs	 working  LAND, SEA and AIR
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF boat_mission_selection = 5


boat_refresh_hover:

 	GOSUB boat_startsetup

	boat_setup_hover:

	SET_PLAYER_CONTROL player1 ON
	////WRITE_DEBUG playing

	//boat_perfect_heading = 180.0
    //boat_timer = 20000
	GOSUB boat_setting_up_variables



	CREATE_CAR POLMAV -2303.6272 2105.2903 200.0 instructor_heli
	DELETE_CHAR instructor_helipilot
	CREATE_CHAR_INSIDE_CAR instructor_heli PEDTYPE_MISSION1 omoboat instructor_helipilot
	CLEAR_CHAR_TASKS instructor_helipilot
	SET_HELI_BLADES_FULL_SPEED instructor_heli
	SET_CAR_CRUISE_SPEED instructor_heli 250.0
	HELI_GOTO_COORDS instructor_heli -2303.6272 2105.2903 200.0 200.0 200.0
	//DELETE_CHAR instructor_captain


 	// Old coords before someone stuck rocks in !

	CREATE_CAR vortex -2014.2671 2365.4294 6.0509 instructor_boat 
   	CHANGE_CAR_COLOUR instructor_boat 6 2
   	SET_CAR_HEADING instructor_boat 111.0
	
	CREATE_CHAR_INSIDE_CAR instructor_boat PEDTYPE_MISSION1 omoboat instructor_captain

	GOSUB boat_createbuoys

	IF boatpb_flag = 0
			ATTACH_CAMERA_TO_VEHICLE_LOOK_AT_VEHICLE instructor_heli 10.0 10.0 0.0 instructor_boat 0.0 JUMP_CUT
		RETURN
	ENDIF

  
	GOSUB boat_stopsetup

	//starting challenge, setting up targets etc.

	PRINT_HELP BOAT_H2  
	SET_PLAYER_CONTROL player1 ON  // new

	//Getting ifrst pair of buoy marker position for angled area command so we can check that the player has passed through them
	
	IF NOT IS_CAR_DEAD instructor_boat
		GET_CAR_COORDINATES instructor_boat instructor_boatx instructor_boaty instructor_boatz
		GET_OBJECT_COORDINATES boat_buoys[0] buoyl_x buoyl_y buoyl_z
	 	GET_OBJECT_COORDINATES boat_buoys[1] buoyr_x buoyr_y buoyr_z
		ADD_BLIP_FOR_OBJECT boat_buoys[0] buoyl_b
		ADD_BLIP_FOR_OBJECT boat_buoys[1] buoyr_b

		ADD_BLIP_FOR_OBJECT boat_buoys[2] buoyls_b
		ADD_BLIP_FOR_OBJECT boat_buoys[3] buoyrs_b
		
		GOSUB blip_management
//		CHANGE_BLIP_SCALE buoyls_b 1
//		CHANGE_BLIP_SCALE buoyrs_b 1
//
//		CHANGE_BLIP_COLOUR buoyl_b YELLOW
//		CHANGE_BLIP_COLOUR buoyr_b YELLOW
//		CHANGE_BLIP_COLOUR buoyls_b YELLOW
//		CHANGE_BLIP_COLOUR buoylr_b YELLOW


		FREEZE_CAR_POSITION instructor_boat TRUE
		/*
		DELETE_CHAR boat_blocker1driver
		DELETE_CHAR boat_blocker2driver
		DELETE_CAR boat_blocker1
		DELETE_CAR boat_blocker2
		CREATE_CAR marquis -2100.8364 2247.8623 0.0 boat_blocker1 
		SET_CAR_HEADING boat_blocker1 122.0
		CREATE_CHAR_INSIDE_CAR boat_blocker1 PEDTYPE_MISSION1 omoboat boat_blocker1driver
		  */
	 		
	 		//WRITE_DEBUG_WITH_FLOAT boatX boat_targetX
	ENDIF


	sfx_gull = 0


	boat_hover_loop:

	WAIT 0
		/*
		IF NOT IS_CAR_DEAD instructor_boat
		GET_CAR_HEALTH instructor_boat boat_damagetaken
		WRITE_DEBUG_WITH_INT bd boat_damagetaken
		ENDIF
		*/

		IF sfx_gull = 0
			LOAD_MISSION_AUDIO 3 SOUND_GULL_SCREECH
			sfx_gull = 1
		ENDIF

		IF sfx_gull = 1
		 	IF HAS_MISSION_AUDIO_LOADED 3
				sfx_gull = 2
		 	ENDIF
		ENDIF



		IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_w
			boat_hover 	= 99
		ENDIF

		
																									 

		IF NOT IS_CAR_DEAD instructor_heli					 
		IF NOT IS_CAR_DEAD instructor_boat

				//GET_CAR_COORDINATES APB_heli1 APB_heli1x APB_heli1y APB_heli1z   //Helicopter
           		GET_CAR_COORDINATES instructor_boat instructor_boatx instructor_boaty instructor_boatz //instructor_boat
                        
            	//vc2_xDiff = vc2_tpCx - APB_heli1x
            	//vc2_yDiff = APB_heli1y - vc2_tpCy

            	//GET_HEADING_FROM_VECTOR_2D vc2_yDiff vc2_xDiff APB_heli1_orientation
                        
            	//SET_HELI_ORIENTATION APB_heli1 APB_heli1_orientation
                        
				instructor_boatx = instructor_boatx - 150.0
				instructor_boaty = instructor_boaty - 150.0
           	 
            	HELI_GOTO_COORDS instructor_heli instructor_boatx instructor_boaty instructor_boatz 200.0 200.0
		ENDIF
		ENDIF


		//checking player hasnt left car
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			instructor_boat_dead_flag = 2
			GOTO after_scores_boat_hover																  
		ENDIF 

		//checking car isnt dead
		GOSUB instructor_boat_dead
		IF instructor_boat_dead_flag = 1 
			GOTO mission_boat_failed
		ENDIF


	IF boat_hover = 0
		PRINT_NOW BOAT_C1 4000 1

		IF IS_BUTTON_PRESSED PAD1 CROSS
		OR IS_BUTTON_PRESSED PAD1 SQUARE

			SYNC_WATER
			boat_gold = boat_hover_gold
			boat_silver = boat_hover_silver
			boat_bronze = boat_hover_bronze
			ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z SOUND_RACE_START_GO

			boat_hover = 5
			boat_timer = 0 
		   	CLEAR_ONSCREEN_TIMER boat_timer

		   	DISPLAY_ONSCREEN_TIMER_WITH_STRING boat_timer TIMER_UP BOAT_T1
			FREEZE_CAR_POSITION instructor_boat FALSE

		ENDIF	
	ENDIF


	IF boat_hover > 0
		IF boat_timer > 600000
		   boat_hover = 99	// test over
		ENDIF

	ENDIF

	IF boat_hover = 5
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 10 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[2] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[3] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[2] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[3] buoyr_b

				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[4] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[5] buoyrs_b
			   	 GOSUB blip_management


			ENDIF
		ENDIF  
	ENDIF

	IF boat_hover = 10
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 15 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[4] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[5] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[4] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[5] buoyr_b

				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[6] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[7] buoyrs_b
				 GOSUB blip_management
				

			ENDIF
		ENDIF  
	ENDIF

	IF boat_hover = 15
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 20 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[6] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[7] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[6] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[7] buoyr_b
				
				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[8] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[9] buoyrs_b
				 	 GOSUB blip_management



//				 IF NOT IS_CAR_DEAD boat_blocker3
//				 IF NOT IS_CHAR_DEAD boat_blocker3driver
//				 TASK_CAR_DRIVE_TO_COORD boat_blocker3driver boat_blocker3 -2013.4762 1748.4989 0.0 18.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
//				  //WRITE_DEBUG going3
//				 ENDIF
//				 ENDIF

			ENDIF
		ENDIF  
	ENDIF


	IF boat_hover = 20
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 25 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[8] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[9] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[8] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[9] buoyr_b
				
				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[10] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[11] buoyrs_b
				 	 GOSUB blip_management

			ENDIF
		ENDIF  
	ENDIF

	
	IF boat_hover = 25
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 30 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[10] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[11] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[10] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[11] buoyr_b

				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[12] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[13] buoyrs_b
		 	 	 GOSUB blip_management

				 
			   //	boat_hover = 99

			ENDIF
		ENDIF  
	ENDIF

	IF boat_hover = 30
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 35 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[12] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[13] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[12] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[13] buoyr_b

				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[14] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[15] buoyrs_b
		 	 	 GOSUB blip_management


			ENDIF
		ENDIF  
	ENDIF

	IF boat_hover = 35
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 40 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[14] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[15] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[14] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[15] buoyr_b

				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[16] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[17] buoyrs_b
		 	 	 GOSUB blip_management


			ENDIF
		ENDIF  
	ENDIF

	IF boat_hover = 40
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 45 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[16] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[17] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[16] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[17] buoyr_b

				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[18] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[19] buoyrs_b
		 	 	 GOSUB blip_management


			ENDIF
		ENDIF  
	ENDIF
	IF boat_hover = 45
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 50 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[18] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[19] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[18] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[19] buoyr_b

				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[20] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[21] buoyrs_b
		 	 	 GOSUB blip_management

			ENDIF
		ENDIF  
	ENDIF
	IF boat_hover = 50
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 55 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[20] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[21] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[20] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[21] buoyr_b

				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[22] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[23] buoyrs_b
		 	 	 GOSUB blip_management

			ENDIF
		ENDIF  
	ENDIF

	IF boat_hover = 55
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 60 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[22] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[23] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[22] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[23] buoyr_b


				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[24] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[25] buoyrs_b
		 	 	 GOSUB blip_management

			ENDIF
		ENDIF  
	ENDIF

	IF boat_hover = 60
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 65 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[24] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[25] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[24] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[25] buoyr_b

				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[26] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[27] buoyrs_b
		 	 	 GOSUB blip_management


				 IF NOT IS_CAR_DEAD boat_blocker2
				 IF NOT IS_CHAR_DEAD boat_blocker2driver
					 IF sfx_gull = 2
						//WRITE_DEBUG aud2
						PLAY_MISSION_AUDIO 3
				 	 ENDIF

				 TASK_CAR_DRIVE_TO_COORD boat_blocker2driver boat_blocker2 -2763.1055 1954.1290 0.0 10.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
				 // WRITE_DEBUG going2
				 ENDIF
				 ENDIF
			ENDIF
		ENDIF  
	ENDIF
	IF boat_hover = 65
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 70 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[26] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[27] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[26] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[27] buoyr_b


				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[28] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[29] buoyrs_b
		 	 	 GOSUB blip_management


				 IF NOT IS_CAR_DEAD boat_blocker1
				 IF NOT IS_CHAR_DEAD boat_blocker1driver
					 IF sfx_gull = 2
						//WRITE_DEBUG aud2
						PLAY_MISSION_AUDIO 3
				 	 ENDIF

				 TASK_CAR_DRIVE_TO_COORD boat_blocker1driver boat_blocker1 -2763.1055 1954.1290 0.0 15.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
				 // WRITE_DEBUG going1
				 ENDIF
				 ENDIF
			ENDIF
		ENDIF  
	ENDIF

	IF boat_hover = 70
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 75 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[28] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[29] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[28] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[29] buoyr_b


				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[30] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[31] buoyrs_b
		 	 	 GOSUB blip_management


				 IF NOT IS_CAR_DEAD boat_blocker1
				 IF NOT IS_CHAR_DEAD boat_blocker1driver
				 
				 TASK_CAR_DRIVE_TO_COORD boat_blocker1driver boat_blocker1 -2763.1055 1954.1290 0.0 15.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
				 // WRITE_DEBUG going1
				 ENDIF
				 ENDIF
			ENDIF
		ENDIF  
	ENDIF												  

	IF boat_hover = 75
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 80 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[30] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[31] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[30] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[31] buoyr_b


				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[32] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[33] buoyrs_b
		 	 	 GOSUB blip_management


				 IF NOT IS_CAR_DEAD boat_blocker1
				 IF NOT IS_CHAR_DEAD boat_blocker1driver
					 IF sfx_gull = 2
						//WRITE_DEBUG aud2
						PLAY_MISSION_AUDIO 3
				 	 ENDIF

				 TASK_CAR_DRIVE_TO_COORD boat_blocker1driver boat_blocker1 -2763.1055 1954.1290 0.0 15.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
				 // WRITE_DEBUG going1
				 ENDIF
				 ENDIF
			ENDIF
		ENDIF  
	ENDIF
	IF boat_hover = 80
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 82 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[32] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[33] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[32] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[33] buoyr_b


				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[34] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[35] buoyrs_b
		 	 	 GOSUB blip_management


				 IF NOT IS_CAR_DEAD boat_blocker1
				 IF NOT IS_CHAR_DEAD boat_blocker1driver
					
				 TASK_CAR_DRIVE_TO_COORD boat_blocker1driver boat_blocker1 -2763.1055 1954.1290 0.0 15.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
				 // WRITE_DEBUG going1
				 ENDIF
				 ENDIF
			ENDIF
		ENDIF  
	ENDIF

	IF boat_hover = 82
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 84 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[34] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[35] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[34] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[35] buoyr_b


				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[36] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[37] buoyrs_b
		 	 	 GOSUB blip_management


				 IF NOT IS_CAR_DEAD boat_blocker1
				 IF NOT IS_CHAR_DEAD boat_blocker1driver
					 IF sfx_gull = 2
						//WRITE_DEBUG aud2
						PLAY_MISSION_AUDIO 3
				 	 ENDIF

				 TASK_CAR_DRIVE_TO_COORD boat_blocker1driver boat_blocker1 -2763.1055 1954.1290 0.0 15.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
				 // WRITE_DEBUG going1
				 ENDIF
				 ENDIF
			ENDIF
		ENDIF  
	ENDIF


	IF boat_hover = 84
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 86 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[36] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[37] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[36] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[37] buoyr_b


				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[38] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[39] buoyrs_b
		 	 	 GOSUB blip_management


				 IF NOT IS_CAR_DEAD boat_blocker1
				 IF NOT IS_CHAR_DEAD boat_blocker1driver
				 TASK_CAR_DRIVE_TO_COORD boat_blocker1driver boat_blocker1 -2763.1055 1954.1290 0.0 15.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
				 // WRITE_DEBUG going1
				 ENDIF
				 ENDIF
			ENDIF
		ENDIF  
	ENDIF

	IF boat_hover = 86
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 88 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[38] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[39] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[38] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[39] buoyr_b


				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[40] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[41] buoyrs_b
		 	 	 GOSUB blip_management


				 IF NOT IS_CAR_DEAD boat_blocker1
				 IF NOT IS_CHAR_DEAD boat_blocker1driver
				 TASK_CAR_DRIVE_TO_COORD boat_blocker1driver boat_blocker1 -2763.1055 1954.1290 0.0 15.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
				 // WRITE_DEBUG going1
				 ENDIF
				 ENDIF
			ENDIF
		ENDIF  
	ENDIF


	IF boat_hover = 88
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 90 // go to next buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[40] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[41] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[40] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[41] buoyr_b


				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[42] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[43] buoyrs_b
		 	 	 GOSUB blip_management


				 IF NOT IS_CAR_DEAD boat_blocker1
				 IF NOT IS_CHAR_DEAD boat_blocker1driver
				 TASK_CAR_DRIVE_TO_COORD boat_blocker1driver boat_blocker1 -2763.1055 1954.1290 0.0 15.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
				 // WRITE_DEBUG going1
				 ENDIF
				 ENDIF
			ENDIF
		ENDIF  
	ENDIF


	IF boat_hover = 90
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 91 // go to final buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[42] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[43] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[42] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[43] buoyr_b
				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
			   //	 ADD_BLIP_FOR_OBJECT boat_buoys[44] buoyls_b
			   //	 ADD_BLIP_FOR_OBJECT boat_buoys[45] buoyrs_b
		 	 	 GOSUB blip_management

			ENDIF
		ENDIF  
	ENDIF

	IF boat_hover = 91
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_33 1000 1 // checkpoint ping!

	  			 boat_hover = 99//92 // go to final buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
//				 GET_OBJECT_COORDINATES boat_buoys[44] buoyl_x buoyl_y buoyl_z
// 				 GET_OBJECT_COORDINATES boat_buoys[45] buoyr_x buoyr_y buoyr_z
//				 ADD_BLIP_FOR_OBJECT boat_buoys[44] buoyl_b
//				 ADD_BLIP_FOR_OBJECT boat_buoys[45] buoyr_b
//				 REMOVE_BLIP buoyls_b
//				 REMOVE_BLIP buoyrs_b
//				 ADD_BLIP_FOR_OBJECT boat_buoys[46] buoyls_b
//				 ADD_BLIP_FOR_OBJECT boat_buoys[47] buoyrs_b
//		 	 	 GOSUB blip_management

			ENDIF
		ENDIF  
	ENDIF


	IF boat_hover = 92
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 93 // go to final buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[46] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[47] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[46] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[47] buoyr_b
				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[48] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[49] buoyrs_b
		 	 	 GOSUB blip_management

			ENDIF
		ENDIF  
	ENDIF


	IF boat_hover = 93
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 94 // go to final buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[48] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[49] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[48] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[49] buoyr_b
				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[0] buoyls_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[1] buoyrs_b
		 	 	 GOSUB blip_management

			ENDIF
		ENDIF  
	ENDIF


	IF boat_hover = 94
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_31 1000 1 // checkpoint ping!

	  			 boat_hover = 97 // go to final buoy pair
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 GET_OBJECT_COORDINATES boat_buoys[0] buoyl_x buoyl_y buoyl_z
 				 GET_OBJECT_COORDINATES boat_buoys[1] buoyr_x buoyr_y buoyr_z
				 ADD_BLIP_FOR_OBJECT boat_buoys[0] buoyl_b
				 ADD_BLIP_FOR_OBJECT boat_buoys[1] buoyr_b
				 REMOVE_BLIP buoyls_b
				 REMOVE_BLIP buoyrs_b
				 GOSUB blip_management

			ENDIF
		ENDIF  
	ENDIF


	//////////////////////////////////Last pair
	IF boat_hover = 97
		IF NOT IS_CAR_DEAD instructor_boat

	 		IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_2D scplayer buoyl_x buoyl_y buoyr_x buoyr_y 3.0 FALSE
	  			 //WRITE_DEBUG buoypair
				 ADD_ONE_OFF_SOUND buoyl_x buoyl_y buoyl_z buoy_sound
				 PRINT_NOW BOAT_33 1000 1 // checkpoint ping!

	  			 boat_hover = 99 // go to complete
				 REMOVE_BLIP buoyl_b
				 REMOVE_BLIP buoyr_b
				 //GET_OBJECT_COORDINATES boat_buoys[0] buoyl_x buoyl_y buoyl_z
 				 //GET_OBJECT_COORDINATES boat_buoys[1] buoyr_x buoyr_y buoyr_z
				 //ADD_BLIP_FOR_OBJECT boat_buoys[0] buoyl_b
				 //ADD_BLIP_FOR_OBJECT boat_buoys[1] buoyr_b
			ENDIF
		ENDIF  
	ENDIF




			
		IF boat_hover = 99	// test over
			 REMOVE_BLIP buoyl_b
			 REMOVE_BLIP buoyr_b
			 REMOVE_BLIP buoyls_b
			 REMOVE_BLIP buoyrs_b


			GOSUB freeze_boat_pos
			GOSUB checking_boat_score

			//checking overall score against the best score at present
					IF overall_boat_score < boat_hover_best_score  	
						boat_old_score = boat_hover_best_score 
						boat_hover_best_score = overall_boat_score
						boat_print_top_scores_flag = 1
						GOSUB boat_medal_check 

						// log player's medal achievements for rewards section. If the player receives a silver or gold 
						// medal for a test they automatically have a bronze too, so that if the player completes the boat
						// school with 3 bronzes, a silver and a gold they will receive the "all-bronze" reward upon exiting the 
						// boat school.
						IF boat_which_medal_displayed = 4 // gold
							IF boat_hover_goldachieved = 0
								boat_hover_goldachieved = 1
								boat_hover_silverachieved = 1
								boat_hover_bronzeachieved = 1
								//WRITE_DEBUG gold1 
							ENDIF
						ENDIF

						IF boat_which_medal_displayed = 3 // silver
							IF boat_hover_silverachieved = 0
								boat_hover_silverachieved = 1
								boat_hover_bronzeachieved = 1
								//WRITE_DEBUG silver1
							ENDIF
						ENDIF
						
						IF boat_which_medal_displayed = 2 //bronze
						   IF boat_hover_bronzeachieved = 0
								boat_hover_bronzeachieved = 1
								//WRITE_DEBUG	bronze1
							ENDIF
						ENDIF

					
					ELSE		
						boat_which_medal_displayed = 0	
					ENDIF 	
						//opening next level
					IF boat_which_missions_are_open_flag = 5
						IF overall_boat_score < boat_hover_qualify
							boat_print_top_scores_flag = 2
							boat_which_missions_are_open_flag = 5
							// All tests complete!
							//GOTO mission_boat_passed
							boat_schoolpassed = 1
							
							

							instructor_boat_dead_flag = 2
						ENDIF
					ENDIF
	
				



					//printing scores onscreen
					timera = 0 
					WHILE timera > -1//< 10000
						WAIT 0												    

						//changing camera position 
						GOSUB boat_setting_up_camera			

						//checking player hasnt left car
						IF IS_BUTTON_PRESSED PAD1 TRIANGLE
							instructor_boat_dead_flag = 2
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_STOP
							GOTO after_scores_boat_hover																		  
						ENDIF 									    
					
						//displaying scores
						GOSUB display_overall_boat_score_text				   

						//checking if player has skipped watching the scores
						GOSUB skip_boat_scores
						IF finished_watching_boat_scores = 1
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_STOP

							GOTO after_scores_boat_hover
						ENDIF
					ENDWHILE	

					 //reseting for another try
					after_scores_boat_hover:
					GOSUB boat_mini_cleanup

					/*
						//opening next level
					IF boat_which_missions_are_open_flag = 5
						IF overall_boat_score < boat_hover_qualify
							boat_print_top_scores_flag = 2
							boat_which_missions_are_open_flag = 5
							// All tests complete!
							//GOTO mission_boat_passed
					   
					   
					 		
							IF boat_passed_once = 0
								//instructor_boat_dead_flag = 2
								DO_FADE 1000 FADE_OUT
								WHILE GET_FADING_STATUS
								 	WAIT 0
								ENDWHILE
	
							   //	SET_CHAR_COORDINATES scplayer boat_playerstartx boat_playerstarty boat_playerstartz
							   	SET_CHAR_HEADING scplayer boat_playerstarth
							   	SET_CAMERA_BEHIND_PLAYER
								
								LOAD_SCENE -2185.3347 2410.4092 3.9752						
							   	DO_FADE 2000 FADE_IN
							   	WHILE GET_FADING_STATUS
								 	WAIT 0
								ENDWHILE
								SET_CAMERA_BEHIND_PLAYER
								RESTORE_CAMERA_JUMPCUT


								GOTO mission_boat_passed
								//PRINT_WITH_NUMBER_BIG M_PASS 30000 5000 1

							   //	PLAYER_MADE_PROGRESS 1

							   //	REGISTER_ODDJOB_MISSION_PASSED
								//boat_passed_once = 1
							ENDIF
							

							instructor_boat_dead_flag = 2
						ENDIF
					ENDIF
					*/


					
					 
					GOSUB boat_deletingbuoys
																		  
					IF instructor_boat_dead_flag = 2 

							IF boat_schoolpassed = 1
					  
					    		IF boat_passed_once = 0
								//instructor_boat_dead_flag = 2
								DO_FADE 1000 FADE_OUT
								WHILE GET_FADING_STATUS
								 	WAIT 0
								ENDWHILE
	
							   //	SET_CHAR_COORDINATES scplayer boat_playerstartx boat_playerstarty boat_playerstartz
							   	SET_CHAR_HEADING scplayer boat_playerstarth
							   	SET_CAMERA_BEHIND_PLAYER
								RESTORE_CAMERA_JUMPCUT

								
								LOAD_SCENE -2185.3347 2410.4092 3.9752						
							   	DO_FADE 2000 FADE_IN
							   	WHILE GET_FADING_STATUS
								 	WAIT 0
								ENDWHILE
								//SET_CAMERA_BEHIND_PLAYER
								//RESTORE_CAMERA_JUMPCUT


								GOTO mission_boat_passed
								//PRINT_WITH_NUMBER_BIG M_PASS 30000 5000 1

							   //	PLAYER_MADE_PROGRESS 1

							   //	REGISTER_ODDJOB_MISSION_PASSED
								//boat_passed_once = 1
						   	   ENDIF

								
							ENDIF



						GOTO boat_noticeboard_setup
					ELSE
						GOTO boat_refresh_hover
					ENDIF





		
		ENDIF // boat_hover = 99 condition check

		
		

			
		



	GOTO boat_hover_loop 


ENDIF
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////







// Gosub Section-----------------------------------------------------------------------------------------------------------------


boat_setting_up_variables:
	boat_accelerate = 0
	boat_simplecircuit = 0
	boat_slalom = 0
	boat_skijump = 0
	boat_hover = 0
	boat_timer = 0
	boat_started = 0
	instructor_boat_dead_flag = 0
	finished_watching_boat_scores = 0
	boat_button_pressed = 0
	boat_print_top_scores_flag = 0
	overall_boat_score = 0
	total_boat_damage = 1000
	boat_camera_positionx = 0.0
	boat_camera_positiony = 0.0
	boat_camera_positionz = 0.0
	boat_camera_position_int = 0
	shown_help = 0
	completion_time = 0
	boat_speed = 0.0
	boat_gold = 0
	boat_silver = 0
	boat_bronze = 0
	//boat_qualify = 0
	time_penalty = 0
	boat_targetX = 0.0
	boat_targetY = 0.0
	boat_targetZ = 0.0




RETURN

//
//

skip_boat_scores:
	IF IS_BUTTON_PRESSED PAD1 CROSS
		IF boat_button_pressed = 1
			boat_button_pressed = 0
			finished_watching_boat_scores = 1
		ENDIF
	ELSE
		IF boat_button_pressed = 0
			boat_button_pressed = 1
		ENDIF
	ENDIF
	
RETURN


//
//

freeze_boat_pos:
	CLEAR_HELP
	SET_PLAYER_CONTROL player1 off
	
	IF boat_mission_selection = 1
	 FREEZE_ONSCREEN_TIMER TRUE
	 //FREEZE_CAR_POSITION instructor_boat TRUE
	 BOAT_STOP instructor_boat
	 SET_CAR_FORWARD_SPEED instructor_boat 0.0
	 FREEZE_CAR_POSITION instructor_boat FALSE

	 ANCHOR_BOAT instructor_boat TRUE
	 FREEZE_CAR_POSITION instructor_boat FALSE


	ENDIF

	IF boat_mission_selection > 1
		 FREEZE_ONSCREEN_TIMER TRUE

		 timerb = 0
		 WHILE timerb < 1000
			WAIT 0
		 ENDWHILE
		 IF NOT IS_CAR_DEAD instructor_boat
			//FREEZE_CAR_POSITION instructor_boat TRUE
			IF NOT boat_mission_selection = 4
				// IF NOT boat_mission_selection = 3
					IF NOT boat_mission_selection = 5
						BOAT_STOP instructor_boat
					ENDIF
				// ENDIF
			ENDIF

			SET_CAR_FORWARD_SPEED instructor_boat 0.0
			FREEZE_CAR_POSITION instructor_boat FALSE

			IF NOT boat_mission_selection = 4
				//IF NOT boat_mission_selection = 3
					IF NOT boat_mission_selection = 5
		 				ANCHOR_BOAT instructor_boat TRUE
					ENDIF
				//ENDIF
			ENDIF
			FREEZE_CAR_POSITION instructor_boat FALSE

		 ENDIF
	ENDIF

RETURN

//
//

instructor_boat_dead:
	IF NOT IS_CAR_DEAD instructor_boat
		IF boat_started = 1 
			IF NOT IS_CHAR_IN_CAR scplayer instructor_boat
				instructor_boat_dead_flag = 1
			ENDIF
		ENDIF
	ELSE  
		instructor_boat_dead_flag = 1
	ENDIF
RETURN

//
//

checking_boat_score:

//IF boat_mission_selection = 1

	IF NOT boat_mission_selection = 4

	completion_time = boat_timer
	//WRITE_DEBUG_WITH_INT dam completion_time

	time_penalty = 0

	
	total_boat_damage = 1000//  Remnant of hacked driving school
	
	
	
	IF NOT IS_CAR_DEAD instructor_boat
	GET_CAR_HEALTH instructor_boat boat_damagetaken
	//WRITE_DEBUG_WITH_INT nd boat_damagetaken
	ENDIF

	IF boat_mission_selection = 5
	   boat_damagetaken = boat_damagetaken + 120
	ENDIF


	IF boat_damagetaken < 1000
	time_penalty = 5000
	ENDIF

	IF boat_damagetaken < 995
	time_penalty = 7000
	ENDIF

	IF boat_damagetaken < 990
	time_penalty = 10000
	ENDIF

	
	



	overall_boat_score = completion_time + time_penalty
	
	ENDIF

	IF boat_mission_selection = 4

	completion_time = boat_timer
	//WRITE_DEBUG_WITH_INT dam completion_time

	time_penalty = 0
	total_boat_damage = 1000//  Remnant of hacked driving school

	IF NOT IS_CAR_DEAD instructor_boat
	GET_CAR_HEALTH instructor_boat boat_damagetaken
	//WRITE_DEBUG_WITH_INT nd boat_damagetaken
	ENDIF

	IF boat_damagetaken < 995
	time_penalty = 5000
	ENDIF

	IF boat_damagetaken < 990
	time_penalty = 10000
	ENDIF

	IF boat_damagetaken < 985
	time_penalty = 15000
	ENDIF


  
	overall_boat_score =# boat_bestjumpdist

	
	//overall_boat_score = overall_boat_score - time_penalty  Taken this out as boat invariably takes damage hitting ramp.
	

	ENDIF

//ENDIF

RETURN


//
//


boat_setting_up_camera:
 IF NOT IS_CAR_DEAD instructor_boat
//		IF boat_print_top_scores_flag = 2
//			PRINT_NOW ( BOAT_48 ) 12000 1 //Press ~X~ to continue.
//		ELSE
//			PRINT_NOW ( BOAT_3 ) 12000 1 //Press ~X~ to retry.~N~Press ~T~ to return to Driving School.  
//		ENDIF
		IF boat_camera_position_int = 0
			GET_CAR_COORDINATES instructor_boat boat_camera_positionx boat_camera_positiony boat_camera_positionz 
			boat_camera_positionz += 5.0
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_boat 10.0 0.0 5.0 boat_camera_positionx boat_camera_positiony boat_camera_positionz
			SET_FIXED_CAMERA_POSITION boat_camera_positionx boat_camera_positiony boat_camera_positionz 0.0 0.0 0.0 
			POINT_CAMERA_AT_CAR instructor_boat FIXED INTERPOLATION
			SET_INTERPOLATION_PARAMETERS 50.0 2000
			//driving stats
			//INCREMENT_FLOAT_STAT DRIVING_SKILL 50.0
			boat_camera_position_int = 1
		ENDIF
		IF timera > 3000 
			IF timera < 6000
				IF boat_camera_position_int = 1
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_boat 0.0 10.0 5.0 boat_camera_positionx boat_camera_positiony boat_camera_positionz
					SET_FIXED_CAMERA_POSITION boat_camera_positionx boat_camera_positiony boat_camera_positionz 0.0 0.0 0.0 
					POINT_CAMERA_AT_CAR instructor_boat FIXED INTERPOLATION
					SET_INTERPOLATION_PARAMETERS 50.0 2000
					boat_camera_position_int = 2
				ENDIF
			ENDIF
		ENDIF
		IF timera > 6000 
			IF boat_camera_position_int = 2
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS instructor_boat -10.0 0.0 5.0 boat_camera_positionx boat_camera_positiony boat_camera_positionz
				SET_FIXED_CAMERA_POSITION boat_camera_positionx boat_camera_positiony boat_camera_positionz 0.0 0.0 0.0 
				POINT_CAMERA_AT_CAR instructor_boat FIXED INTERPOLATION
				SET_INTERPOLATION_PARAMETERS 50.0 2000
				boat_camera_position_int = 3
			ENDIF
		ENDIF
		IF timera > 9000
			boat_camera_position_int = 1
			timera = 0

		ENDIF
	ENDIF
RETURN

//
//





boat_medal_check:////////////////////////////////////////////////////////////////////////////
	boat_which_medal_displayed = 0
	//bronze
	/*
	IF overall_boat_score > 69
		IF overall_boat_score < 85
			IF boat_old_score > 69 
			AND boat_old_score < 85 
				boat_which_medal_displayed = 0
			ELSE	
				boat_which_medal_displayed = 2
			ENDIF
		ENDIF
	ENDIF
	*/

	IF NOT boat_mission_selection = 4
		IF overall_boat_score < boat_bronze
			IF overall_boat_score > boat_silver
				IF boat_old_score < boat_bronze 
				AND boat_old_score > boat_silver
					boat_which_medal_displayed = 0
				ELSE
					boat_which_medal_displayed = 2	//bronze
				ENDIF
			ENDIF
		ENDIF




		//silver
		IF overall_boat_score < boat_silver
			IF overall_boat_score > boat_gold
				IF boat_old_score < boat_silver
				AND boat_old_score > boat_gold 
					boat_which_medal_displayed = 0
				ELSE	
					boat_which_medal_displayed = 3
				ENDIF
			ENDIF
		ENDIF

		//gold
		IF overall_boat_score < boat_gold
			IF boat_old_score < boat_gold
				boat_which_medal_displayed = 0
			ELSE
				boat_which_medal_displayed = 4
			ENDIF
		ENDIF
	ENDIF






    // special case for skijump

	IF boat_mission_selection = 4
		IF overall_boat_score > boat_bronze
		OR overall_boat_score = boat_bronze
			IF overall_boat_score < boat_silver
				IF boat_old_score > boat_bronze 
				AND boat_old_score < boat_silver
					boat_which_medal_displayed = 0
				ELSE
					boat_which_medal_displayed = 2
				ENDIF
			ENDIF
		ENDIF




		//silver
		IF overall_boat_score > boat_silver
		OR overall_boat_score = boat_silver

			IF overall_boat_score < boat_gold
				IF boat_old_score > boat_silver
				AND boat_old_score < boat_gold 
					boat_which_medal_displayed = 0
				ELSE	
					boat_which_medal_displayed = 3
				ENDIF
			ENDIF
		ENDIF

		//gold
		IF overall_boat_score > boat_gold
		OR overall_boat_score = boat_gold

			IF boat_old_score > boat_gold
				boat_which_medal_displayed = 0
			ELSE
				boat_which_medal_displayed = 4
			ENDIF
		ENDIF
	  //	write_debug_with_int bmd boat_which_medal_displayed

	ENDIF





RETURN



display_overall_boat_score_text:

	DISPLAY_HUD FALSE
	DISPLAY_RADAR FALSE

	IF boat_which_medal_displayed < 2  // no medal,  2 is bronze
		GOSUB boat_getlanguage

		IF boat_language = 4
			DRAW_WINDOW 122.0 240.0 520.0 400.0 BOAT_75 SWIRLS_BOTH
		ELSE
			DRAW_WINDOW 152.0 240.0 490.0 400.0 BOAT_75 SWIRLS_BOTH
		ENDIF

	ELSE
		GOSUB boat_getlanguage
		IF boat_language = 4
			DRAW_WINDOW 122.0 220.0 520.0 400.0 DUMMY SWIRLS_BOTH	//no text window  Medal
		ELSE
			DRAW_WINDOW 152.0 220.0 490.0 400.0 DUMMY SWIRLS_BOTH	//no text window  Medal
		ENDIF
	ENDIF
	

	//PERFECT HEADING
	GOSUB boat_small_backend_text
	
	//PERFECT POSITION
	GOSUB boat_small_backend_text
	
	IF total_boat_damage > 0 

		CLEAR_PRINTS

		// Results breakdown text //
	 		
	  	//Completion time
		GOSUB boat_small_backend_text
		SET_TEXT_BACKGROUND OFF
		
		GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE b1_r b1_g b1_b b1_alpha2   
		SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2

		dcompletion_time = 0.0
		dcompletion_time =# completion_time
		dcompletion_time = dcompletion_time / 1000.0

		IF NOT boat_mission_selection = 4
			GOSUB boat_getlanguage
			IF boat_language = 4
		    	DISPLAY_TEXT_WITH_FLOAT 145.0 275.0 boat_7 dcompletion_time 2//
			ELSE
		    	DISPLAY_TEXT_WITH_FLOAT 180.0 275.0 boat_7 dcompletion_time 2//
			ENDIF
		ENDIF

		//Damage penalty
		GOSUB boat_small_backend_text
		
		GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE b1_r b1_g b1_b b1_alpha2   
		SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2

	   	dtime_penalty = 0.0
		dtime_penalty =# time_penalty
		dtime_penalty = dtime_penalty / 1000.0

		IF NOT boat_mission_selection = 4
			GOSUB boat_getlanguage
			IF boat_language = 4
				DISPLAY_TEXT_WITH_FLOAT 145.0 295.0 boat_9 dtime_penalty 2//
			ELSE
				DISPLAY_TEXT_WITH_FLOAT 180.0 295.0 boat_9 dtime_penalty 2//
			ENDIF
		ENDIF
		
		
		//Overall Score
		GOSUB boat_small_backend_text

		IF boat_print_top_scores_flag = 1
		OR boat_print_top_scores_flag = 2
			GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE b1_r b1_g b1_b b1_alpha2   
			SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2
		ELSE
			GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE b1_r b1_g b1_b b1_alpha2   
			SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2
		ENDIF

		doverall_boat_score	= 0.0
		doverall_boat_score =# overall_boat_score
		doverall_boat_score = doverall_boat_score / 1000.0

		IF NOT boat_mission_selection = 4
			GOSUB boat_getlanguage
			IF boat_language = 4
				DISPLAY_TEXT_WITH_FLOAT 145.0 315.0 boat_10 doverall_boat_score 2 // Display final score.
			ELSE
				DISPLAY_TEXT_WITH_FLOAT 180.0 315.0 boat_10 doverall_boat_score 2 // Display final score.
			ENDIF

		ENDIF

		IF boat_mission_selection = 4

			IF boat_bestjumpdist > 0.0
				GOSUB boat_getlanguage
				IF boat_language = 4
					DISPLAY_TEXT_WITH_FLOAT 145.0 315.0 boat_11 boat_bestjumpdist 2
				ELSE
					IF boat_language = 3
						DISPLAY_TEXT_WITH_FLOAT 175.0 315.0 boat_11 boat_bestjumpdist 2
					ENDIF
					IF NOT boat_language = 3
						IF NOT boat_language = 4
							DISPLAY_TEXT_WITH_FLOAT 180.0 315.0 boat_11 boat_bestjumpdist 2
						ENDIF
					ENDIF
				ENDIF
			ELSE
				GOSUB boat_getlanguage
				IF boat_language = 4
					DISPLAY_TEXT 145.0 315.0 boat_12	// 	Jump invalid!
				ELSE
					DISPLAY_TEXT 180.0 315.0 boat_12	// 	Jump invalid!
				ENDIF
			ENDIF
		ENDIF


		// Actual results. This is new as it's more visually pleasing to separate the these from their descriptors above.

		//Completion time
		GOSUB boat_small_backend_text

		SET_TEXT_COLOUR 255 255 255 255

		dcompletion_time = 0.0
		dcompletion_time =# completion_time
		dcompletion_time = dcompletion_time / 1000.0

		IF NOT boat_mission_selection = 4
			GOSUB boat_getlanguage
			IF boat_language = 4
				DISPLAY_TEXT_WITH_FLOAT 425.0 275.0 boat_77 dcompletion_time 2//
			ELSE
				DISPLAY_TEXT_WITH_FLOAT 390.0 275.0 boat_77 dcompletion_time 2//
			ENDIF
		ENDIF

		//Damage penalty
		GOSUB boat_small_backend_text

		GET_HUD_COLOUR HUD_COLOUR_RED b1_r b1_g b1_b b1_alpha2   
		SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2


	   //	SET_TEXT_COLOUR 255 255 255 255	// white
	   	dtime_penalty = 0.0
		dtime_penalty =# time_penalty
		dtime_penalty = dtime_penalty / 1000.0

		IF dtime_penalty > 0.0
			GET_HUD_COLOUR HUD_COLOUR_RED b1_r b1_g b1_b b1_alpha2   
			SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2
		ELSE
			GET_HUD_COLOUR HUD_COLOUR_GREY b1_r b1_g b1_b b1_alpha2   
			SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2
		ENDIF

		IF NOT boat_mission_selection = 4
			GOSUB boat_getlanguage
			IF boat_language = 4
				DISPLAY_TEXT_WITH_FLOAT 425.0 295.0 boat_77 dtime_penalty 2//
			ELSE
				DISPLAY_TEXT_WITH_FLOAT 390.0 295.0 boat_77 dtime_penalty 2//
			ENDIF
		ENDIF
		
		//Overall Score
		GOSUB boat_small_backend_text
		IF boat_print_top_scores_flag = 1 ///new high score 
		OR boat_print_top_scores_flag = 2 
			SET_TEXT_COLOUR 255 255 255 255	 //hud white
		ELSE
			SET_TEXT_COLOUR 255 255 255 255	
		ENDIF

		doverall_boat_score	= 0.0
		doverall_boat_score =# overall_boat_score
		doverall_boat_score = doverall_boat_score / 1000.0
		
		IF NOT boat_mission_selection = 4
			GOSUB boat_getlanguage
			IF boat_language = 4
				DISPLAY_TEXT_WITH_FLOAT 425.0 315.0 boat_77 doverall_boat_score 2//
			ELSE
				DISPLAY_TEXT_WITH_FLOAT 390.0 315.0 boat_77 doverall_boat_score 2//
			ENDIF
		ENDIF

		IF boat_mission_selection = 4
			IF boat_bestjumpdist > 0.0
				GOSUB boat_getlanguage
				IF boat_language = 4
					DISPLAY_TEXT_WITH_FLOAT 425.0 315.0 BOAT_80 boat_bestjumpdist 2//
				ELSE
					DISPLAY_TEXT_WITH_FLOAT 390.0 315.0 BOAT_80 boat_bestjumpdist 2//
				ENDIF
			ELSE
			ENDIF
		ENDIF


		//Help text and buttons
		SET_TEXT_SCALE 1.45 2.0
		
		GET_HUD_COLOUR HUD_COLOUR_YELLOW b1_r b1_g b1_b b1_alpha2   
		SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2

   	 	DISPLAY_TEXT 180.0 342.0 boat_74

		SET_TEXT_SCALE 1.45 2.0
  		DISPLAY_TEXT 180.0 362.0 boat_73
		
		GOSUB boat_small_backend_text
		SET_TEXT_SCALE 0.52 1.45  // return scale for help text
	   	GET_HUD_COLOUR HUD_COLOUR_YELLOW b1_r b1_g b1_b b1_alpha2   
		SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2
		DISPLAY_TEXT 215.0 345.0 boat_76  // continue

		GOSUB boat_small_backend_text
	   	GET_HUD_COLOUR HUD_COLOUR_YELLOW b1_r b1_g b1_b b1_alpha2   
		SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2
		DISPLAY_TEXT 215.0 365.0 boat_53  // exit


		// Medals / Ribbons / Congratulatory messages //

		IF boat_print_top_scores_flag = 1
		OR boat_print_top_scores_flag = 2 
			GOSUB boat_test_name_text
			SET_TEXT_SCALE 1.0 3.4
	
			SET_TEXT_COLOUR 255 255 255 255
			DISPLAY_TEXT 323.0 110.0 boat_46  //NEW HIGH SCORE!
		ENDIF
		
		IF boat_print_top_scores_flag = 1
		OR boat_print_top_scores_flag = 2 
			IF boat_which_medal_displayed > 0 

				//MEDAL
				//ribbons

			    DRAW_SPRITE	4 250.0 219.0 -60.0 60.0 180 180 180 255
			    DRAW_SPRITE	4 392.0 219.0  60.0 60.0 180 180 180 255

			   	//SET_SPRITES_DRAW_BEFORE_FADE TRUE
			  	//bronze
				IF boat_which_medal_displayed = 2
					DRAW_SPRITE	1 320.0 220.0 110.0 95.0 160 160 160 255
				ENDIF
				//silver
				IF boat_which_medal_displayed = 3
					DRAW_SPRITE	2 320.0 220.0 110.0 95.0 160 160 160 255
				ENDIF
				//gold
				IF boat_which_medal_displayed = 4
					DRAW_SPRITE	3 320.0 220.0 110.0 95.0 160 160 160 255
				ENDIF

					
				GOSUB boat_test_name_text
			   	SET_TEXT_SCALE 1.0 3.4

				GET_HUD_COLOUR HUD_COLOUR_YELLOW b1_r b1_g b1_b b1_alpha2   
				SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2
				DISPLAY_TEXT 323.0 65.0 boat_47  //NEW CERTIFICATE AWARDED!
			ENDIF
		ENDIF
	ELSE
		GOSUB boat_small_backend_text
		SET_TEXT_COLOUR 134 155 184 255
		
		dcompletion_time = 0.0
		dcompletion_time =# completion_time
		dcompletion_time = dcompletion_time / 1000.0

		DISPLAY_TEXT_WITH_FLOAT 30.0 55.0 BOAT_7 dcompletion_time 2

		GOSUB boat_small_backend_text

		IF boat_print_top_scores_flag = 1
		OR boat_print_top_scores_flag = 2 
			SET_TEXT_COLOUR 107 148 49 255
		ELSE
			SET_TEXT_COLOUR 134 155 184 255
		ENDIF

		doverall_boat_score	= 0.0
		doverall_boat_score =# overall_boat_score
		doverall_boat_score = doverall_boat_score / 1000.0

		DISPLAY_TEXT_WITH_FLOAT 30.0 65.0 BOAT_10 doverall_boat_score 2
		
		IF boat_print_top_scores_flag = 1
		OR boat_print_top_scores_flag = 2 
			GOSUB boat_small_backend_text
			SET_TEXT_COLOUR 107 148 49 255
			DISPLAY_TEXT 260.0 65.0 BOAT_46 
		ENDIF

		IF boat_print_top_scores_flag = 1
		OR boat_print_top_scores_flag = 2 
			IF boat_which_medal_displayed > 0
				SET_SPRITES_DRAW_BEFORE_FADE TRUE 
				DRAW_SPRITE 4 68.0 145.0 -60.0 60.0 180 180 180 255 
				SET_SPRITES_DRAW_BEFORE_FADE TRUE 
				DRAW_SPRITE 4 210.0 145.0 60.0 60.0 180 180 180 255 
				SET_SPRITES_DRAW_BEFORE_FADE TRUE 
				IF boat_which_medal_displayed = 2
					DRAW_SPRITE	1 138.0 145.0 110.0 95.0 160 160 160 255
				ENDIF
				IF boat_which_medal_displayed = 3
					DRAW_SPRITE	2 138.0 145.0 110.0 95.0 160 160 160 255
				ENDIF
				IF boat_which_medal_displayed = 4
					DRAW_SPRITE	3 138.0 145.0 110.0 95.0 160 160 160 255
				ENDIF
				GOSUB boat_small_backend_text
				SET_TEXT_COLOUR 107 148 49 255
				DISPLAY_TEXT 260.0 172.0 BOAT_47 
			ENDIF
		ENDIF

	ENDIF

	CLEAR_ONSCREEN_TIMER boat_timer
	
RETURN

//
//

boat_mini_cleanup://////////////////////////////////////////////////////////////////////////////
   	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
	    WAIT 0
	ENDWHILE
	DISPLAY_HUD TRUE
	DISPLAY_RADAR TRUE
	//SET_AREA_VISIBLE 3
	CLEAR_PRINTS
	CLEAR_HELP
	CLEAR_THIS_BIG_PRINT boat_46 
	CLEAR_THIS_BIG_PRINT boat_47 
	APPLY_BRAKES_TO_PLAYERS_CAR player1 FALSE 
	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
	IF instructor_boat_dead_flag = 2
		CLEAR_PRINTS
		IF IS_CHAR_IN_ANY_CAR scplayer 
			WARP_CHAR_FROM_CAR_TO_COORD scplayer boat_playerstartx boat_playerstarty boat_playerstartz
		ELSE
			SET_CHAR_COORDINATES scplayer boat_playerstartx boat_playerstarty boat_playerstartz
		ENDIF
	ELSE
		IF IS_CHAR_IN_ANY_CAR scplayer 
			WARP_CHAR_FROM_CAR_TO_COORD scplayer boat_playerstartx boat_playerstarty boat_playerstartz
		ELSE
			SET_CHAR_COORDINATES scplayer boat_playerstartx boat_playerstarty boat_playerstartz
		ENDIF
	ENDIF
	WAIT 0
	DELETE_CAR instructor_boat
	DELETE_CAR instructor_heli
	DELETE_CHAR instructor_captain
	REMOVE_BLIP buoyl_b
	REMOVE_BLIP buoyr_b
	REMOVE_BLIP buoyls_b
	REMOVE_BLIP buoyrs_b



	//CLEAR_AREA noticeboard_x noticeboard_y noticeboard_z 300.0 TRUE
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER
	WAIT 0
	CLEAR_ONSCREEN_TIMER boat_timer
	FREEZE_ONSCREEN_TIMER FALSE
RETURN

boat_drawing_tv_screen://////////////////////////////////////////////////////////////////////

	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 8 320.0 225.0 612.0 438.0 0 0 0 boat_alpha

	//FADE_OUT
	IF boat_fade_flag = 1 
		IF boat_alpha < 255
			boat_alpha = boat_alpha + 20//5 
			IF boat_alpha > 255
				boat_alpha = 255
			ENDIF
		ELSE
			boat_fade_flag = 2
		ENDIF
	ENDIF

	//FADE_IN
	IF boat_fade_flag = 3
		IF boat_alpha > 0
			boat_alpha = boat_alpha - 5 
			IF boat_alpha < 0
				boat_alpha = 0
			ENDIF
		ELSE
			boat_fade_flag = 0
		ENDIF
	ENDIF
				 
	//which test
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 8 320.0 0.0 640.0 197.0 0 0 0 255 
	GOSUB boat_test_name_text 
	
	SET_TEXT_COLOUR 255 255 255 255	//white
	IF boat_mission_selection = 1 
		DISPLAY_TEXT 322.0 31.0 BOAT_A //Basic Seamanship
		
		//PRINT_BIG BOAT_A 10000 7
		boat_which_score_displayed = boat_accelerate_best_score
	   
		GOSUB boat_drawing_medal 
	ENDIF
	
	IF boat_mission_selection = 2 
	   	DISPLAY_TEXT 322.0 31.0 BOAT_B  //Simple Circuit
		//PRINT_BIG BOAT_B 10000 7

		boat_which_score_displayed = boat_simplecircuit_best_score  
		GOSUB boat_drawing_medal 
	ENDIF
	
	IF boat_mission_selection = 3 
		DISPLAY_TEXT 322.0 31.0 BOAT_C  //Smoked Slalom
		//PRINT_BIG BOAT_C 10000 7

		boat_which_score_displayed = boat_slalom_best_score  
		GOSUB boat_drawing_medal 
	ENDIF
	
	IF boat_mission_selection = 4 
		DISPLAY_TEXT 322.0 31.0 BOAT_D  //skijump
		//PRINT_BIG BOAT_D 10000 7

		boat_which_score_displayed = boat_skijump_best_score  
		GOSUB boat_drawing_medal 
	ENDIF
	
	IF boat_mission_selection = 5 
		//PRINT_BIG BOAT_E 10000 7
	   														 
		DISPLAY_TEXT 322.0 31.0 BOAT_E  //Land, Sea and Air
		boat_which_score_displayed = boat_hover_best_score  
		GOSUB boat_drawing_medal 
	ENDIF

	
	GOSUB boat_small_onscreen_text
	
	GET_HUD_COLOUR HUD_COLOUR_YELLOW b1_r b1_g b1_b b1_alpha2   
	SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2

	GOSUB boat_getlanguage

	IF boat_language = 1
		DISPLAY_TEXT 96.0 375.0 BOAT_53 
	ELSE
		DISPLAY_TEXT 105.0 375.0 BOAT_53 
	ENDIF

	IF NOT boat_control_flag = 1
		GOSUB boat_small_onscreen_text
	
		GET_HUD_COLOUR HUD_COLOUR_YELLOW b1_r b1_g b1_b b1_alpha2   
		SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2
		GOSUB boat_getlanguage

		IF boat_language = 1
			DISPLAY_TEXT 96.0 325.0 BOAT_54 
		ELSE
			DISPLAY_TEXT 105.0 325.0 BOAT_54 
		ENDIF

		IF NOT boat_which_missions_are_open_flag = 1
			GOSUB boat_small_onscreen_text

			GET_HUD_COLOUR HUD_COLOUR_YELLOW b1_r b1_g b1_b b1_alpha2   
			SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2
			GOSUB boat_getlanguage

			IF boat_language = 1
				DISPLAY_TEXT 96.0 350.0 BOAT_52 
			ELSE
				DISPLAY_TEXT 105.0 350.0 BOAT_52 
			ENDIF
		ENDIF

		IF boat_which_missions_are_open_flag = 1
			GOSUB boat_small_onscreen_text

			GET_HUD_COLOUR HUD_COLOUR_GREY b1_r b1_g b1_b b1_alpha2   
			SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2
			GOSUB boat_getlanguage

			IF boat_language = 1
				DISPLAY_TEXT 96.0 350.0 BOAT_52 
			ELSE
				DISPLAY_TEXT 105.0 350.0 BOAT_52 
			ENDIF
		ENDIF
	ELSE
		GOSUB boat_small_onscreen_text

		GET_HUD_COLOUR HUD_COLOUR_YELLOW b1_r b1_g b1_b b1_alpha2   
		SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2
		GOSUB boat_getlanguage

		IF boat_language = 1
			DISPLAY_TEXT 96.0 350.0 BOAT_52 
		ELSE
			DISPLAY_TEXT 105.0 350.0 BOAT_52 
		ENDIF
	ENDIF

	GOSUB boat_small_onscreen_text

	SET_TEXT_SCALE 1.45 2.0 
	SET_TEXT_COLOUR 248 211 7 255

	DISPLAY_TEXT 70.0 372.0 BOAT_73 

	IF NOT boat_control_flag = 1
		GOSUB boat_small_onscreen_text

		GET_HUD_COLOUR HUD_COLOUR_YELLOW b1_r b1_g b1_b b1_alpha2   
		SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2
		SET_TEXT_SCALE 1.45 2.0
		DISPLAY_TEXT 70.0 322.0 BOAT_74

		IF NOT boat_which_missions_are_open_flag = 1
			GOSUB boat_small_onscreen_text

			GET_HUD_COLOUR HUD_COLOUR_YELLOW b1_r b1_g b1_b b1_alpha2   
			SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2
			SET_TEXT_SCALE 1.45 2.0
			DISPLAY_TEXT 61.4 347.0 BOAT_72
		ENDIF

		IF boat_which_missions_are_open_flag = 1
			GOSUB boat_small_onscreen_text

			GET_HUD_COLOUR HUD_COLOUR_YELLOW b1_r b1_g b1_b b1_alpha2   
			SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2
			SET_TEXT_SCALE 1.45 2.0
			DISPLAY_TEXT 61.4 347.0 BOAT_72
		ENDIF
	ELSE
		GOSUB boat_small_onscreen_text

		GET_HUD_COLOUR HUD_COLOUR_YELLOW b1_r b1_g b1_b b1_alpha2   
		SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2
		SET_TEXT_SCALE 1.45 2.0
		DISPLAY_TEXT 61.4 347.0 BOAT_72
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

boat_drawing_medal://////////////////////////////////////////////////////////////////////////
    //background for medal, ribbons and score 
    SET_SPRITES_DRAW_BEFORE_FADE TRUE
    DRAW_SPRITE 8 320.0 430.0 640.0 250.0 0 0 0 255

	//best score
	GOSUB boat_small_onscreen_text 
	SET_TEXT_RIGHT_JUSTIFY OFF

	GET_HUD_COLOUR HUD_COLOUR_LIGHTBLUE b1_r b1_g b1_b b1_alpha2   
	SET_TEXT_COLOUR b1_r b1_g b1_b b1_alpha2
 	

	IF boat_mission_selection = 1
		IF boat_accelerate_best_score < boat_accelerate_qualify
		
			dboat_which_score_displayed = 0.0
			dboat_which_score_displayed =# boat_which_score_displayed
			dboat_which_score_displayed = dboat_which_score_displayed / 1000.0

			GOSUB boat_getlanguage

			SWITCH boat_language
			CASE LANGUAGE_ENGLISH
				DISPLAY_TEXT_WITH_FLOAT 217.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			CASE LANGUAGE_FRENCH
				DISPLAY_TEXT_WITH_FLOAT 200.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			CASE LANGUAGE_GERMAN
				DISPLAY_TEXT_WITH_FLOAT 217.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			CASE LANGUAGE_ITALIAN
				DISPLAY_TEXT_WITH_FLOAT 187.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			CASE LANGUAGE_SPANISH
				DISPLAY_TEXT_WITH_FLOAT 193.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			ENDSWITCH
		
		ELSE

			temp_int = 0
			dboat_accelerate_qualify = 0.0
		    
			dboat_accelerate_qualify =# boat_accelerate_qualify
			dboat_accelerate_qualify = dboat_accelerate_qualify / 1000.0

			GOSUB boat_getlanguage
			SWITCH boat_language
			CASE LANGUAGE_ENGLISH
				DISPLAY_TEXT_WITH_FLOAT 137.0 75.0 boat_62 dboat_accelerate_qualify 1
				BREAK
			CASE LANGUAGE_FRENCH
				DISPLAY_TEXT_WITH_FLOAT 77.0 75.0 boat_62 dboat_accelerate_qualify 1
				BREAK
			CASE LANGUAGE_GERMAN
				DISPLAY_TEXT_WITH_FLOAT 77.0 75.0 boat_62 dboat_accelerate_qualify 1
				BREAK
			CASE LANGUAGE_ITALIAN
				DISPLAY_TEXT_WITH_FLOAT 97.0 75.0 boat_62 dboat_accelerate_qualify 1
				BREAK
			CASE LANGUAGE_SPANISH
				SET_TEXT_SCALE 0.45 1.35
				DISPLAY_TEXT_WITH_FLOAT 93.0 75.0 boat_62 dboat_accelerate_qualify 1
				SET_TEXT_SCALE 0.52 1.45
				BREAK
			ENDSWITCH
			
		ENDIF


		//no medal
		IF boat_which_score_displayed > boat_accelerate_bronze
			boat_which_medal_displayed = 1
		ENDIF	 
		
		//bronze
		IF boat_which_score_displayed < boat_accelerate_bronze
			IF boat_which_score_displayed > boat_accelerate_silver
				boat_which_medal_displayed = 2
			ENDIF
		ENDIF

		//silver
		IF boat_which_score_displayed < boat_accelerate_silver
			IF boat_which_score_displayed > boat_accelerate_gold
				boat_which_medal_displayed = 3
			ENDIF
		ENDIF

		//gold
		IF boat_which_score_displayed < boat_accelerate_gold
			boat_which_medal_displayed = 4
		ENDIF	
	ENDIF


	IF boat_mission_selection = 2
		IF boat_simplecircuit_best_score < boat_simplecircuit_qualify

			dboat_which_score_displayed = 0.0
			dboat_which_score_displayed =# boat_which_score_displayed
			dboat_which_score_displayed = dboat_which_score_displayed / 1000.0

			GOSUB boat_getlanguage

			SWITCH boat_language
			CASE LANGUAGE_ENGLISH
				DISPLAY_TEXT_WITH_FLOAT 217.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			CASE LANGUAGE_FRENCH
				DISPLAY_TEXT_WITH_FLOAT 200.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			CASE LANGUAGE_GERMAN
				DISPLAY_TEXT_WITH_FLOAT 217.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			CASE LANGUAGE_ITALIAN
				DISPLAY_TEXT_WITH_FLOAT 187.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			CASE LANGUAGE_SPANISH
				DISPLAY_TEXT_WITH_FLOAT 190.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			ENDSWITCH
		
		ELSE
			
			dboat_simplecircuit_qualify = 0.0
		    //temp_int = boat_accelerate_qualify / 10
			dboat_simplecircuit_qualify =# boat_simplecircuit_qualify
			dboat_simplecircuit_qualify = dboat_simplecircuit_qualify / 1000.0

			//Convert to minutes
			//dboat_simplecircuitqualify = dboat_simplecircuit_qualify / 60

			GOSUB boat_getlanguage
			SWITCH boat_language
			CASE LANGUAGE_ENGLISH
				DISPLAY_TEXT_WITH_FLOAT 137.0 75.0 boat_62 dboat_simplecircuit_qualify 1
				BREAK
			CASE LANGUAGE_FRENCH
				DISPLAY_TEXT_WITH_FLOAT 77.0 75.0 boat_62 dboat_simplecircuit_qualify 1
				BREAK
			CASE LANGUAGE_GERMAN
				DISPLAY_TEXT_WITH_FLOAT 77.0 75.0 boat_62 dboat_simplecircuit_qualify 1
				BREAK
			CASE LANGUAGE_ITALIAN
				DISPLAY_TEXT_WITH_FLOAT 97.0 75.0 boat_62 dboat_simplecircuit_qualify 1
				BREAK
			CASE LANGUAGE_SPANISH
				SET_TEXT_SCALE 0.45 1.35
				DISPLAY_TEXT_WITH_FLOAT 88.0 75.0 boat_62 dboat_simplecircuit_qualify 1
				SET_TEXT_SCALE 0.52 1.45
				BREAK
			ENDSWITCH
			
		ENDIF


		//no medal
  		IF boat_which_score_displayed > boat_simplecircuit_bronze
			boat_which_medal_displayed = 1
		ENDIF	 
		
		//bronze
		IF boat_which_score_displayed < boat_simplecircuit_bronze
			IF boat_which_score_displayed > boat_simplecircuit_silver
				boat_which_medal_displayed = 2
			ENDIF
		ENDIF

		//silver
		IF boat_which_score_displayed < boat_simplecircuit_silver
			IF boat_which_score_displayed > boat_simplecircuit_gold
				boat_which_medal_displayed = 3
			ENDIF
		ENDIF

		//gold
		IF boat_which_score_displayed < boat_simplecircuit_gold
			boat_which_medal_displayed = 4
		ENDIF	
	ENDIF


	IF boat_mission_selection = 3
		IF boat_slalom_best_score < boat_slalom_qualify

			dboat_which_score_displayed = 0.0
			dboat_which_score_displayed =# boat_which_score_displayed
			dboat_which_score_displayed = dboat_which_score_displayed / 1000.0

			GOSUB boat_getlanguage
			
			SWITCH boat_language
			CASE LANGUAGE_ENGLISH
				DISPLAY_TEXT_WITH_FLOAT 217.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			CASE LANGUAGE_FRENCH
				DISPLAY_TEXT_WITH_FLOAT 200.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			CASE LANGUAGE_GERMAN
				DISPLAY_TEXT_WITH_FLOAT 217.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			CASE LANGUAGE_ITALIAN
				DISPLAY_TEXT_WITH_FLOAT 187.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			CASE LANGUAGE_SPANISH
				DISPLAY_TEXT_WITH_FLOAT 190.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			ENDSWITCH

		ELSE
			
			dboat_slalom_qualify = 0.0
		   //temp_int = boat_accelerate_qualify / 10
			dboat_slalom_qualify =# boat_slalom_qualify
			dboat_slalom_qualify = dboat_slalom_qualify / 1000.0

			//Convert to minutes
			dboat_slalom_qualify = dboat_slalom_qualify / 60.0

			GOSUB boat_getlanguage
			
			SWITCH boat_language
			CASE LANGUAGE_ENGLISH
				DISPLAY_TEXT_WITH_FLOAT 142.0 75.0 boat_63 dboat_slalom_qualify 1
				BREAK
			CASE LANGUAGE_FRENCH
				DISPLAY_TEXT_WITH_FLOAT 87.0 75.0 boat_63 dboat_slalom_qualify 1
				BREAK
			CASE LANGUAGE_GERMAN
				DISPLAY_TEXT_WITH_FLOAT 83.0 75.0 boat_63 dboat_slalom_qualify 1
				BREAK
			CASE LANGUAGE_ITALIAN
				DISPLAY_TEXT_WITH_FLOAT 112.0 75.0 boat_63 dboat_slalom_qualify 1
				BREAK
			CASE LANGUAGE_SPANISH
				SET_TEXT_SCALE 0.45 1.35
				DISPLAY_TEXT_WITH_FLOAT 103.0 75.0 boat_63 dboat_slalom_qualify 1
				SET_TEXT_SCALE 0.52 1.45
				BREAK
			ENDSWITCH
			
		ENDIF


		//no medal
  		IF boat_which_score_displayed > boat_slalom_bronze
			boat_which_medal_displayed = 1
		ENDIF	 
		
		//bronze
		IF boat_which_score_displayed < boat_slalom_bronze
			IF boat_which_score_displayed > boat_slalom_silver
				boat_which_medal_displayed = 2
			ENDIF
		ENDIF

		//silver
		IF boat_which_score_displayed < boat_slalom_silver
			IF boat_which_score_displayed > boat_slalom_gold
				boat_which_medal_displayed = 3
			ENDIF
		ENDIF

		//gold
		IF boat_which_score_displayed < boat_slalom_gold
			boat_which_medal_displayed = 4
		ENDIF	
	ENDIF

	IF boat_mission_selection = 4
		IF boat_skijump_best_score > boat_skijump_qualify

			dboat_which_score_displayed = 0.0
			dboat_which_score_displayed =# boat_which_score_displayed
			dboat_which_score_displayed = dboat_which_score_displayed / 1000.0

			GOSUB boat_getlanguage
            
			SWITCH boat_language
			CASE LANGUAGE_ENGLISH
				DISPLAY_TEXT_WITH_NUMBER 185.0 75.0 boat_60 boat_skijump_best_score
				BREAK
			CASE LANGUAGE_FRENCH
				DISPLAY_TEXT_WITH_NUMBER 167.0 75.0 boat_60 boat_skijump_best_score
				BREAK
			CASE LANGUAGE_GERMAN
				DISPLAY_TEXT_WITH_NUMBER 167.0 75.0 boat_60 boat_skijump_best_score
				BREAK
			CASE LANGUAGE_ITALIAN
				DISPLAY_TEXT_WITH_NUMBER 177.0 75.0 boat_60 boat_skijump_best_score
				BREAK
			CASE LANGUAGE_SPANISH
				DISPLAY_TEXT_WITH_NUMBER 160.0 75.0 boat_60 boat_skijump_best_score
				BREAK
			ENDSWITCH

		ELSE
			
			dboat_skijump_qualify = 0.0
		    //temp_int = boat_accelerate_qualify / 10
			dboat_skijump_qualify =# boat_skijump_qualify
			dboat_skijump_qualify = dboat_skijump_qualify / 1000.0

			GOSUB boat_getlanguage
			SWITCH boat_language
			CASE LANGUAGE_ENGLISH
				DISPLAY_TEXT_WITH_NUMBER 153.0 75.0 boat_61 boat_skijump_qualify
				BREAK
			CASE LANGUAGE_FRENCH
				DISPLAY_TEXT_WITH_NUMBER 103.0 75.0 boat_61 boat_skijump_qualify
				BREAK
			CASE LANGUAGE_GERMAN
				DISPLAY_TEXT_WITH_NUMBER 93.0 75.0 boat_61 boat_skijump_qualify
				BREAK
			CASE LANGUAGE_ITALIAN
				DISPLAY_TEXT_WITH_NUMBER 123.0 75.0 boat_61 boat_skijump_qualify
				BREAK
			CASE LANGUAGE_SPANISH
				DISPLAY_TEXT_WITH_NUMBER 103.0 75.0 boat_61 boat_skijump_qualify
				BREAK
			ENDSWITCH
		ENDIF


		//no medal
  		IF boat_which_score_displayed < boat_skijump_bronze
			boat_which_medal_displayed = 1
		ENDIF	 
		
		//bronze
		IF boat_which_score_displayed > boat_skijump_bronze
		OR boat_which_score_displayed = boat_skijump_bronze

			IF boat_which_score_displayed < boat_skijump_silver
				boat_which_medal_displayed = 2
			ENDIF
		ENDIF

		//silver
		IF boat_which_score_displayed > boat_skijump_silver
		OR boat_which_score_displayed = boat_skijump_silver

			IF boat_which_score_displayed < boat_skijump_gold
				boat_which_medal_displayed = 3
			ENDIF
		ENDIF

		//gold
		IF boat_which_score_displayed > boat_skijump_gold
		OR boat_which_score_displayed = boat_skijump_gold

			boat_which_medal_displayed = 4
		ENDIF	
	ENDIF


	IF boat_mission_selection = 5
		IF boat_hover_best_score < boat_hover_qualify

			dboat_which_score_displayed = 0.0
			dboat_which_score_displayed =# boat_which_score_displayed
			dboat_which_score_displayed = dboat_which_score_displayed / 1000.0

			GOSUB boat_getlanguage
			SWITCH boat_language
			CASE LANGUAGE_ENGLISH
				DISPLAY_TEXT_WITH_FLOAT 217.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			CASE LANGUAGE_FRENCH
				DISPLAY_TEXT_WITH_FLOAT 200.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			CASE LANGUAGE_GERMAN
				DISPLAY_TEXT_WITH_FLOAT 217.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			CASE LANGUAGE_ITALIAN
				DISPLAY_TEXT_WITH_FLOAT 187.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			CASE LANGUAGE_SPANISH
				DISPLAY_TEXT_WITH_FLOAT 190.0 75.0 boat_58 dboat_which_score_displayed 2
				BREAK
			ENDSWITCH
		ELSE
			
			dboat_hover_qualify = 0.0
		    //temp_int = boat_accelerate_qualify / 10
			dboat_hover_qualify =# boat_hover_qualify
			dboat_hover_qualify = dboat_hover_qualify / 1000.0

			//Convert to minutes
			dboat_hover_qualify = dboat_hover_qualify / 60.0

			GOSUB boat_getlanguage
			SWITCH boat_language
			CASE LANGUAGE_ENGLISH
				DISPLAY_TEXT_WITH_FLOAT 144.0 75.0 boat_63 dboat_hover_qualify 1
				BREAK
			CASE LANGUAGE_FRENCH
				DISPLAY_TEXT_WITH_FLOAT 87.0 75.0 boat_63 dboat_hover_qualify 1
				BREAK
			CASE LANGUAGE_GERMAN
				DISPLAY_TEXT_WITH_FLOAT 88.0 75.0 boat_63 dboat_hover_qualify 1
				BREAK
			CASE LANGUAGE_ITALIAN
				DISPLAY_TEXT_WITH_FLOAT 117.0 75.0 boat_63 dboat_hover_qualify 1
				BREAK
			CASE LANGUAGE_SPANISH
				SET_TEXT_SCALE 0.45 1.35
				DISPLAY_TEXT_WITH_FLOAT 103.0 75.0 boat_63 dboat_hover_qualify 1
				SET_TEXT_SCALE 0.52 1.45
				BREAK
			ENDSWITCH
		ENDIF


		//no medal
  		IF boat_which_score_displayed > boat_hover_bronze
			boat_which_medal_displayed = 1
		ENDIF	 
		
		//bronze
		IF boat_which_score_displayed < boat_hover_bronze
			IF boat_which_score_displayed > boat_hover_silver
				boat_which_medal_displayed = 2
			ENDIF
		ENDIF

		//silver
		IF boat_which_score_displayed < boat_hover_silver
			IF boat_which_score_displayed > boat_hover_gold
				boat_which_medal_displayed = 3
			ENDIF
		ENDIF

		//gold
		IF boat_which_score_displayed < boat_hover_gold
			boat_which_medal_displayed = 4
		ENDIF	
	ENDIF
	
	IF boat_which_medal_displayed > 1
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
	IF boat_which_medal_displayed = 1 //no medal
		DRAW_SPRITE	6 320.0 307.0 110.0 95.0 160 160 160 255
		GOSUB boat_small_onscreen_text
		SET_TEXT_COLOUR 166 202 240 255
		GOSUB boat_getlanguage

		SWITCH boat_language
		CASE LANGUAGE_ENGLISH
			DISPLAY_TEXT 266.0 375.0 BOAT_64
			BREAK
		CASE LANGUAGE_FRENCH
			DISPLAY_TEXT 216.0 375.0 BOAT_64
			BREAK
		CASE LANGUAGE_GERMAN
			DISPLAY_TEXT 233.0 375.0 BOAT_64
			BREAK
		CASE LANGUAGE_ITALIAN
			DISPLAY_TEXT 216.0 375.0 BOAT_64
			BREAK
		CASE LANGUAGE_SPANISH
			DISPLAY_TEXT 216.0 375.0 BOAT_64
			BREAK
		ENDSWITCH
	ENDIF
	
	//bronze
	IF boat_which_medal_displayed = 2 
		DRAW_SPRITE	1 320.0 307.0 110.0 95.0 160 160 160 255
		GOSUB boat_small_onscreen_text
		SET_TEXT_COLOUR 166 202 240 255
		GOSUB boat_getlanguage

		SWITCH boat_language
		CASE LANGUAGE_ENGLISH
			DISPLAY_TEXT 280.0 375.0 BOAT_57
			BREAK
		CASE LANGUAGE_FRENCH
			DISPLAY_TEXT 280.0 375.0 BOAT_57
			BREAK
		CASE LANGUAGE_GERMAN
			DISPLAY_TEXT 280.0 375.0 BOAT_57
			BREAK
		CASE LANGUAGE_ITALIAN
			DISPLAY_TEXT 280.0 375.0 BOAT_57
			BREAK
		CASE LANGUAGE_SPANISH
			DISPLAY_TEXT 280.0 375.0 BOAT_57
			BREAK
		ENDSWITCH
	ENDIF

	//silver
	IF boat_which_medal_displayed = 3 
		DRAW_SPRITE	2 320.0 307.0 110.0 95.0 160 160 160 255
		GOSUB boat_small_onscreen_text
		SET_TEXT_COLOUR 166 202 240 255
		GOSUB boat_getlanguage

		SWITCH boat_language
		CASE LANGUAGE_ENGLISH
			DISPLAY_TEXT 283.0 375.0 BOAT_56
			BREAK
		CASE LANGUAGE_FRENCH
			DISPLAY_TEXT 278.0 375.0 BOAT_56
			BREAK
		CASE LANGUAGE_GERMAN
			DISPLAY_TEXT 283.0 375.0 BOAT_56
			BREAK
		CASE LANGUAGE_ITALIAN
			DISPLAY_TEXT 273.0 375.0 BOAT_56
			BREAK
		CASE LANGUAGE_SPANISH
			DISPLAY_TEXT 286.0 375.0 BOAT_56
			BREAK
		ENDSWITCH
	ENDIF
	
	//gold
	IF boat_which_medal_displayed = 4 
		DRAW_SPRITE	3 320.0 307.0 110.0 95.0 160 160 160 255
		GOSUB boat_small_onscreen_text
		SET_TEXT_COLOUR 166 202 240 255
		GOSUB boat_getlanguage

		SWITCH boat_language
		CASE LANGUAGE_ENGLISH
			DISPLAY_TEXT 294.0 375.0 BOAT_55
			BREAK
		CASE LANGUAGE_FRENCH
			DISPLAY_TEXT 307.0 375.0 BOAT_55
			BREAK
		CASE LANGUAGE_GERMAN
			DISPLAY_TEXT 294.0 375.0 BOAT_55
			BREAK
		CASE LANGUAGE_ITALIAN
			DISPLAY_TEXT 300.0 375.0 BOAT_55
			BREAK
		CASE LANGUAGE_SPANISH
			DISPLAY_TEXT 300.0 375.0 BOAT_55
			BREAK
		ENDSWITCH
	ENDIF

RETURN/////////////////////////////////////////////////////////////////////////////////////

boat_watching_demo://///////////////////////////////////////////////////////////////////////////
//setting up each individual mission for the playback and starting playback
	IF boatpb_flag = 0 
		////WRITE_DEBUG inwd
		IF boat_mission_selection = 1
			GOSUB boat_setup_accelerate
		ENDIF
		
		IF boat_mission_selection = 2
			GOSUB boat_setup_simplecircuit
		ENDIF
		
		IF boat_mission_selection = 3
			GOSUB boat_setup_slalom
		ENDIF
		
		IF boat_mission_selection = 4
			GOSUB boat_setup_skijump
		ENDIF

		
		IF boat_mission_selection = 5
			GOSUB boat_setup_hover
		ENDIF
		/*
		IF boat_mission_selection = 7
			GOSUB setup_burn_and_lap
		ENDIF
		IF boat_mission_selection = 9
			GOSUB setup_cone_coil
		ENDIF
		IF boat_mission_selection = 10
			GOSUB setup_90
		ENDIF
		IF boat_mission_selection = 11
			GOSUB setup_wheelie_weave
		ENDIF
		IF boat_mission_selection = 13
			GOSUB setup_spin_go
		ENDIF
		IF boat_mission_selection = 14
			GOSUB setup_pit_technique
		ENDIF
		IF boat_mission_selection = 15
			GOSUB setup_swift_escape
		ENDIF
		IF boat_mission_selection = 16
			GOSUB setup_city_slicking
		ENDIF
		*/
		boatpb_flag = 1
	ENDIF

	IF boatpb_flag = 1
		////WRITE_DEBUG playback//test!
		//loading in car recordings.
		IF NOT IS_CAR_DEAD instructor_boat

			IF boat_mission_selection = 1
			IF NOT HAS_CAR_RECORDING_BEEN_LOADED 752
				REQUEST_CAR_RECORDING 752 
			ELSE
				//playing back car recordings
				SYNC_WATER	
				START_PLAYBACK_RECORDED_CAR instructor_boat 752
				////WRITE_DEBUG demo
				boat_fade_flag = 3 //FADE_IN
				boatpb_flag = 2
				
			ENDIF
			ENDIF


			IF boat_mission_selection = 2
			IF NOT HAS_CAR_RECORDING_BEEN_LOADED 755
				REQUEST_CAR_RECORDING 755
			ELSE
				//playing back car recordings	
				SYNC_WATER
				START_PLAYBACK_RECORDED_CAR instructor_boat 755
				////WRITE_DEBUG demo
				boat_fade_flag = 3 //FADE_IN
				boatpb_flag = 2
				
			ENDIF
			ENDIF

			IF boat_mission_selection = 3
			IF NOT HAS_CAR_RECORDING_BEEN_LOADED 757
				REQUEST_CAR_RECORDING 757
			ELSE
				//playing back car recordings	
				SYNC_WATER
				START_PLAYBACK_RECORDED_CAR instructor_boat 757
				////WRITE_DEBUG demo
				boat_fade_flag = 3 //FADE_IN
				boatpb_flag = 2
				
			ENDIF
			ENDIF

			IF boat_mission_selection = 4
			IF NOT HAS_CAR_RECORDING_BEEN_LOADED 759
				REQUEST_CAR_RECORDING 759
			ELSE

				SYNC_WATER	
				START_PLAYBACK_RECORDED_CAR instructor_boat 759
				////WRITE_DEBUG demo
				boat_fade_flag = 3 //FADE_IN
				boatpb_flag = 2
				
			ENDIF
			ENDIF

			IF boat_mission_selection = 5
			IF NOT HAS_CAR_RECORDING_BEEN_LOADED 760
				REQUEST_CAR_RECORDING 760
			ELSE
				//playing back car recordings
				SYNC_WATER	
				START_PLAYBACK_RECORDED_CAR instructor_boat 760
				////WRITE_DEBUG demo
				boat_fade_flag = 3 //FADE_IN
				boatpb_flag = 2
				
			ENDIF
			ENDIF
																													  
		ENDIF
	ENDIF

	//waiting for playback to finish
	IF boatpb_flag = 2 	//Should be 2
		IF NOT IS_CAR_DEAD instructor_boat 

	   
			
	  		IF NOT IS_CAR_DEAD instructor_heli

				//GET_CAR_COORDINATES APB_heli1 APB_heli1x APB_heli1y APB_heli1z   //Helicopter
           		GET_CAR_COORDINATES instructor_boat instructor_boatx instructor_boaty instructor_boatz //instructor_boat
                        
                
                IF boat_mission_selection = 1                       
					instructor_boatx = instructor_boatx - 35.0	// use +40 to show off glitch	-45 to fix
					instructor_boaty = instructor_boaty - 30.0	//								-40 to fix
           	 	ENDIF

				IF boat_mission_selection = 2
					instructor_boatx = instructor_boatx - 15.0	// use +40 to show off glitch	-45 to fix
					instructor_boaty = instructor_boaty - 30.0//40.0
				ENDIF

      			IF boat_mission_selection = 3                      
					instructor_boatx = instructor_boatx - 13.0	// use +40 to show off glitch	-45 to fix
					instructor_boaty = instructor_boaty - 13.0	//								-40 to fix
           	 	ENDIF

				IF boat_mission_selection = 4                      
					instructor_boatx = instructor_boatx - 10.0	// use +40 to show off glitch	-15 to fix
					instructor_boaty = instructor_boaty - 20.0	//								-15 to fix
           	 	ENDIF
				 
				IF boat_mission_selection = 5                     
					instructor_boatx = instructor_boatx - 10.0	// use +40 to show off glitch	-10 to fix
					instructor_boaty = instructor_boaty - 20.0	//								-20 to fix
           	 	ENDIF


				SET_CAR_COORDINATES instructor_heli instructor_boatx instructor_boaty 10.0	 //+ 6 to fix
            	//HELI_GOTO_COORDS instructor_heli instructor_boatx instructor_boaty instructor_boatz 10.0 10.0
				//HELI_FOLLOW_ENTITY instructor_heli -1 instructor_boat 10.0
			ENDIF
				

			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR instructor_boat
			  	////WRITE_DEBUG recording
				boat_fade_flag = 1 //FADE_OUT
				boatpb_flag = 4//4
			ENDIF


		ELSE
			boat_fade_flag = 1 //FADE_OUT
			boatpb_flag = 4
		ENDIF
	ENDIF

	//overriding playback
	IF boatpb_flag = 3
		boat_fade_flag = 1 //FADE_OUT
		boatpb_flag = 4
	ENDIF
	
	//waiting for screen to FADE_OUT and cleaning everything up
	IF boatpb_flag = 4
		IF boat_fade_flag = 2
			STOP_PLAYBACK_RECORDED_CAR instructor_boat 
			RESTORE_CAMERA_JUMPCUT
			//DELETE_CAR instructor_boat
			//DELETE_CAR instructor_heli

		   	//RESTORE_CAMERA_JUMPCUT
			
		   //	CLEAR_AREA noticeboard_x noticeboard_y noticeboard_z 300.0 TRUE
			CLEAR_ONSCREEN_TIMER boat_timer
			FREEZE_ONSCREEN_TIMER FALSE
		   //	DELETE_CAR dummy_car1
		   //	DELETE_CAR dummy_car2
			DELETE_CAR instructor_heli
			DELETE_CAR instructor_boat
		   //	DELETE_OBJECT ramp1
		   //	DELETE_OBJECT ramp2
			
			GOSUB boat_deletingbuoys
			
			boatpb_flag = 0
			CLEAR_ONSCREEN_TIMER boat_timer
			FREEZE_ONSCREEN_TIMER FALSE

		  		
			//WRITE_DEBUG delete
			DELETE_CAR instructor_boat
			DELETE_CAR instructor_heli
			DELETE_CHAR instructor_helipilot
			DELETE_CHAR instructor_captain
			DELETE_CAR boat_blocker1
			DELETE_CAR boat_blocker2
			DELETE_CAR boat_blocker3
			DELETE_CHAR boat_blocker1driver
			DELETE_CHAR boat_blocker2driver
			DELETE_CHAR boat_blocker3driver

		ENDIF
	ENDIF		 			
RETURN/////////////////////////////////////////////////////////////////////////////////////

boat_small_onscreen_text:///////////////////////////////////////////////////////////////////////
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

boat_small_backend_text:////////////////////////////////////////////////////////////////////////
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


boat_which_course_text:///////////////////////////////////////////////////////////////////////
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

boat_test_name_text:///////////////////////////////////////////////////////////////////////
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

boat_ms_onscreen_text://///////////////////////////////////////////////////////////////
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




boat_stopsetup://///////////////////////////////////////////////////////////////////
	SET_PLAYER_CONTROL player1 OFF // new
    DELETE_CHAR instructor_captain
	DELETE_CHAR boat_blocker1driver
	DELETE_CHAR boat_blocker2driver
	DELETE_CAR boat_blocker1
	DELETE_CAR boat_blocker2
	DELETE_CAR boat_blocker3
	DELETE_CHAR boat_blocker3driver

	CLEAR_ONSCREEN_TIMER boat_timer

	CREATE_CAR tropic -2100.8364 2247.8623 0.0 boat_blocker1 
	SET_CAR_HEADING boat_blocker1 122.0															   
	CREATE_CHAR_INSIDE_CAR boat_blocker1 PEDTYPE_MISSION1 omoboat boat_blocker1driver
	CREATE_CAR marquis -1996.1267 2125.1694 0.0 boat_blocker2 
	SET_CAR_HEADING boat_blocker2 160.0
	CREATE_CHAR_INSIDE_CAR boat_blocker2 PEDTYPE_MISSION1 omoboat boat_blocker2driver
	CREATE_CAR tropic -2251.1707 2129.2910 0.0 boat_blocker3 
	SET_CAR_HEADING boat_blocker3 182.0
	CREATE_CHAR_INSIDE_CAR boat_blocker3 PEDTYPE_MISSION1 omoboat boat_blocker3driver    



	WARP_CHAR_INTO_CAR scplayer instructor_boat
	LOCK_CAR_DOORS instructor_boat CARLOCK_LOCKED_PLAYER_INSIDE
	
	WAIT 1000
	
	DO_FADE 2000 FADE_OUT
	WHILE GET_FADING_STATUS					 
	    WAIT 0
	ENDWHILE 

	RESTORE_CAMERA_JUMPCUT    				  
	SET_CAMERA_BEHIND_PLAYER 					    
   //	WRITE_DEBUG pish

	IF boat_mission_selection = 4  
		 LOAD_SCENE -2318.0493 2312.3018 10.0
		 WAIT 0
	ENDIF



	DO_FADE 2000 FADE_IN
	WHILE GET_FADING_STATUS
	    WAIT 0
	ENDWHILE 
	//RESTORE_CAMERA_JUMPCUT    
	//SET_CAMERA_BEHIND_PLAYER
    //SET_PLAYER_CONTROL player1 on




	RETURN/////////////////////////////////////////////////////////////////////////////////////


boat_startsetup:////////////////////////////////////////////////////////////////////
	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
	    WAIT 0
	ENDWHILE 
	IF boat_mission_selection = 16 
		SET_CAR_DENSITY_MULTIPLIER 1.0
		SET_PED_DENSITY_MULTIPLIER 1.0
	ELSE
		SET_CAR_DENSITY_MULTIPLIER 0.0
		SET_PED_DENSITY_MULTIPLIER 0.0
	ENDIF	
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER
	FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_COUNTRYSIDE
	SET_AREA_VISIBLE 0
	DELETE_CHAR instructor_captain
	DELETE_CHAR boat_blocker1driver
	DELETE_CHAR boat_blocker2driver
	DELETE_CAR boat_blocker1
	DELETE_CAR boat_blocker2
	DELETE_CAR boat_blocker3
	DELETE_CHAR boat_blocker3driver



RETURN/////////////////////////////////////////////////////////////////////////////////////



blip_management:
	CHANGE_BLIP_SCALE buoyls_b 1
	CHANGE_BLIP_SCALE buoyrs_b 1

	CHANGE_BLIP_COLOUR buoyl_b YELLOW
	CHANGE_BLIP_COLOUR buoyr_b YELLOW
	CHANGE_BLIP_COLOUR buoyls_b YELLOW
	CHANGE_BLIP_COLOUR buoyrs_b YELLOW
RETURN



boat_createbuoys:


IF boat_mission_selection = 1
	
			buoy_counter = 0

	 		WHILE buoy_counter < 8
				//bouys - sic. It's written incorrectly in object.dat
				CREATE_OBJECT_NO_OFFSET bouy 0.0 0.0 100.0 boat_buoys[buoy_counter] 
			
				SET_OBJECT_COLLISION boat_buoys[buoy_counter] TRUE 	 
				SET_OBJECT_DYNAMIC boat_buoys[buoy_counter] TRUE
				buoy_counter ++
			ENDWHILE
			
			IF NOT IS_CAR_DEAD instructor_boat
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[0] instructor_boat 20.0 180.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[1] instructor_boat -20.0 180.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[2] instructor_boat 20.0 190.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[3] instructor_boat -20.0 190.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[4] instructor_boat 20.0 200.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[5] instructor_boat -20.0 200.0 0.0	 // Parallel to boat start
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[6] instructor_boat 20.0 210.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[7] instructor_boat -20.0 210.0 0.0
					 /*
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[8] instructor_boat -10.0 210.0 0.0	// Perpendicular to boat start
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[9] instructor_boat 10.0 210.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[10] instructor_boat -20.0 210.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[11] instructor_boat 20.0 210.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[12] instructor_boat 0.0 210.0 0.0
					 */																						  

			ENDIF

ENDIF


IF boat_mission_selection = 2

			buoy_counter = 0

			WHILE buoy_counter < 16
				//bouys - sic. It's written incorrectly in object.dat
				CREATE_OBJECT_NO_OFFSET bouy 0.0 0.0 100.0 boat_buoys[buoy_counter] 
			
				SET_OBJECT_COLLISION boat_buoys[buoy_counter] TRUE 	 
				SET_OBJECT_DYNAMIC boat_buoys[buoy_counter] TRUE
				buoy_counter ++
			ENDWHILE
			
			IF NOT IS_CAR_DEAD instructor_boat
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[0] instructor_boat 0.0 80.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[1] instructor_boat 15.0 70.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[2] instructor_boat 55.0 145.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[3] instructor_boat 70.0 130.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[4] instructor_boat 150.0 110.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[5] instructor_boat 160.0 130.0 0.0
					
					
					
					
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[6] instructor_boat 250.0 80.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[7] instructor_boat 240.0 60.0 0.0








					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[8] instructor_boat 300.0 00.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[9] instructor_boat 290.0 -20.0 0.0

					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[10] instructor_boat 380.0 -150.0 0.0 //380.0 -150.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[11] instructor_boat 360.0 -150.0 0.0//380.0 -155.0 0.0

					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[12] instructor_boat 320.0 -300.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[13] instructor_boat 340.0 -325.0 0.0

					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[14] instructor_boat 230.0 -280.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[15] instructor_boat 210.0 -300.0 0.0


			ENDIF

ENDIF


IF boat_mission_selection = 3

			buoy_counter = 0

			WHILE buoy_counter < 28
				//bouys - sic. It's written incorrectly in object.dat
				CREATE_OBJECT_NO_OFFSET bouy 0.0 0.0 100.0 boat_buoys[buoy_counter] 
			
				SET_OBJECT_COLLISION boat_buoys[buoy_counter] TRUE 	 
				SET_OBJECT_DYNAMIC boat_buoys[buoy_counter] TRUE
				buoy_counter ++
			ENDWHILE
			
			IF NOT IS_CAR_DEAD instructor_boat
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[0] instructor_boat 0.0 100.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[1] instructor_boat 15.0 100.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[2] instructor_boat 40.0 200.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[3] instructor_boat 55.0 190.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[4] instructor_boat 0.0 300.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[5] instructor_boat -15.0 290.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[6] instructor_boat 140.0 320.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[7] instructor_boat 140.0 335.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[8] instructor_boat 220.0 370.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[9] instructor_boat 210.0 385.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[10] instructor_boat 210.0 470.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[11] instructor_boat 195.0 460.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[12] instructor_boat 195.0 570.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[13] instructor_boat 180.0 570.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[14] instructor_boat 95.0 570.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[15] instructor_boat 95.0 585.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[16] instructor_boat 15.0 500.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[17] instructor_boat 0.0 515.0 0.0

			   		PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[18] instructor_boat -90.0 585.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[19] instructor_boat -90.0 570.0 0.0

					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[20] instructor_boat -160.0 500.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[21] instructor_boat -150.0 485.0 0.0

					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[22] instructor_boat -240.0 430.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[23] instructor_boat -230.0 415.0 0.0

					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[24] instructor_boat -140.0 330.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[25] instructor_boat -140.0 315.0 0.0

					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[26] instructor_boat -30.0 220.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[27] instructor_boat -40.0 205.0 0.0



			ENDIF

ENDIF


IF boat_mission_selection = 4

			buoy_counter = 0

			WHILE buoy_counter < 20
				//bouys - sic. It's written incorrectly in object.dat
				CREATE_OBJECT_NO_OFFSET bouy 0.0 0.0 100.0 boat_buoys[buoy_counter] 
			
				SET_OBJECT_COLLISION boat_buoys[buoy_counter] TRUE 	 
				SET_OBJECT_DYNAMIC boat_buoys[buoy_counter] TRUE
				buoy_counter ++
			ENDWHILE

			ramp_counter = 0


			WHILE ramp_counter < 1
			
				CREATE_OBJECT_NO_OFFSET waterjumpx2 20.0 20.0 150.0 boat_ramps[ramp_counter]

				ramp_counter ++
			ENDWHILE
			
			
			IF NOT IS_CAR_DEAD instructor_boat
				  	
				  	PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[0] instructor_boat 91.0 480.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[1] instructor_boat 109.0 480.0 0.0
					
			

					PLACE_OBJECT_RELATIVE_TO_CAR boat_ramps[0] instructor_boat 100.0 480.0 0.0

					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[2] instructor_boat 75.0 500.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[3] instructor_boat 125.0 500.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[4] instructor_boat 75.0 510.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[5] instructor_boat 125.0 510.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[6] instructor_boat 75.0 520.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[7] instructor_boat 125.0 520.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[8] instructor_boat 75.0 530.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[9] instructor_boat 125.0 530.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[10] instructor_boat 75.0 540.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[11] instructor_boat 125.0 540.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[12] instructor_boat 75.0 550.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[13] instructor_boat 125.0 550.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[14] instructor_boat 75.0 560.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[15] instructor_boat 125.0 560.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[16] instructor_boat 75.0 570.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[17] instructor_boat 125.0 570.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[18] instructor_boat 75.0 580.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[19] instructor_boat 125.0 580.0 0.0



			ENDIF



			/*
			WHILE ramp_counter < 1
			
				CREATE_OBJECT_NO_OFFSET waterjumpx2 20.0 20.0 150.0 boat_ramps[ramp_counter]

				ramp_counter ++
			ENDWHILE
			
			
			IF NOT IS_CAR_DEAD instructor_boat
				  	
				  	PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[0] instructor_boat -9.0 180.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[1] instructor_boat 9.0 180.0 0.0
					
			

					PLACE_OBJECT_RELATIVE_TO_CAR boat_ramps[0] instructor_boat 0.0 180.0 0.0

					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[2] instructor_boat -25.0 200.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[3] instructor_boat 25.0 200.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[4] instructor_boat -25.0 210.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[5] instructor_boat 25.0 210.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[6] instructor_boat -25.0 220.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[7] instructor_boat 25.0 220.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[8] instructor_boat -25.0 230.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[9] instructor_boat 25.0 230.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[10] instructor_boat -25.0 240.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[11] instructor_boat 25.0 240.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[12] instructor_boat -25.0 250.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[13] instructor_boat 25.0 250.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[14] instructor_boat -25.0 260.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[15] instructor_boat 25.0 260.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[16] instructor_boat -25.0 270.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[17] instructor_boat 25.0 270.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[18] instructor_boat -25.0 280.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[19] instructor_boat 25.0 280.0 0.0



			ENDIF
			*/
ENDIF



IF boat_mission_selection = 5		 //friday

			buoy_counter = 0

			WHILE buoy_counter < 44//50
				//bouys - sic. It's written incorrectly in object.dat
				CREATE_OBJECT_NO_OFFSET bouy 0.0 0.0 100.0 boat_buoys[buoy_counter] 
			
				SET_OBJECT_COLLISION boat_buoys[buoy_counter] TRUE 	 
				SET_OBJECT_DYNAMIC boat_buoys[buoy_counter] TRUE
				buoy_counter ++
			ENDWHILE

			ramp_counter = 0

			WHILE ramp_counter < 3
			
				CREATE_OBJECT_NO_OFFSET waterjumpx2 20.0 20.0 150.0 boat_ramps[ramp_counter]

				ramp_counter ++
			ENDWHILE
			
			
			IF NOT IS_CAR_DEAD instructor_boat
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[0] instructor_boat 25.0 100.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[1] instructor_boat 10.0 100.0 0.0
					
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[2] instructor_boat -30.0 170.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[3] instructor_boat -50.0 160.0 0.0

					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[4] instructor_boat -100.0 190.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[5] instructor_boat -120.0 170.0 0.0
					
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[6] instructor_boat -170.0 260.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[7] instructor_boat -188.0 260.0 0.0
				
					// squalo starts on water pos.
					//PLACE_OBJECT_RELATIVE_TO_CAR boat_ramps[0] instructor_boat -179.0 360.0 0.0
					
					
				  	//squalo starts on beach
					PLACE_OBJECT_RELATIVE_TO_CAR boat_ramps[0] instructor_boat -179.0 360.0 -6.509

					
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[8] instructor_boat -170.0 360.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[9] instructor_boat -188.0 360.0 0.0
					
		   			PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[10] instructor_boat -180.0 500.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[11] instructor_boat -195.0 500.0 0.0
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[12] instructor_boat -50.0 600.0 0.0 
					PLACE_OBJECT_RELATIVE_TO_CAR boat_buoys[13] instructor_boat -30.0 600.0 0.0

					SET_OBJECT_COORDINATES boat_ramps[1] -2682.1592 1946.4122 0.9
					SET_OBJECT_HEADING boat_ramps[1] 180.0
					SET_OBJECT_COORDINATES boat_buoys[14] -2689.5728 1944.8639 0.0 
					SET_OBJECT_COORDINATES boat_buoys[15] -2674.3542 1944.6071 0.0 


				 	SET_OBJECT_COORDINATES boat_ramps[2] -2681.7461 1608.7939 0.9
					SET_OBJECT_HEADING boat_ramps[2] 180.0
					SET_OBJECT_COORDINATES boat_buoys[16] -2689.5728 1607.7939 0.0 
					SET_OBJECT_COORDINATES boat_buoys[17] -2674.3542 1607.7939 0.0 

					
					SET_OBJECT_COORDINATES boat_buoys[18] -2689.2698 1533.2819 0.0
					SET_OBJECT_COORDINATES boat_buoys[19] -2674.6404 1530.1200 0.0

					SET_OBJECT_COORDINATES boat_buoys[20] -2553.7000 1494.3916 0.0 
					SET_OBJECT_COORDINATES boat_buoys[21] -2550.7422 1481.4166 0.0

					
					SET_OBJECT_COORDINATES boat_buoys[22] -2453.7000 1494.3916 0.0 
					SET_OBJECT_COORDINATES boat_buoys[23] -2450.7422 1481.4166 0.0
	
					SET_OBJECT_COORDINATES boat_buoys[24] -2353.7000 1474.3916 0.0 
					SET_OBJECT_COORDINATES boat_buoys[25] -2350.7422 1461.4166 0.0

					SET_OBJECT_COORDINATES boat_buoys[26] -2284.2417 1547.5811 0.0 
					SET_OBJECT_COORDINATES boat_buoys[27] -2270.8926 1550.7651 0.0

					SET_OBJECT_COORDINATES boat_buoys[28] -2334.2417 1647.5811 0.0 
					SET_OBJECT_COORDINATES boat_buoys[29] -2320.8926 1650.7651 0.0

					SET_OBJECT_COORDINATES boat_buoys[30] -2284.2417 1747.5811 0.0 
					SET_OBJECT_COORDINATES boat_buoys[31] -2270.8926 1750.7651 0.0

					SET_OBJECT_COORDINATES boat_buoys[32] -2139.3936 1821.2628 0.0 
					SET_OBJECT_COORDINATES boat_buoys[33] -2134.5808 1808.7853 0.0
														  
					SET_OBJECT_COORDINATES boat_buoys[34] -2039.3936 1761.2628 0.0 
					SET_OBJECT_COORDINATES boat_buoys[35] -2034.5808 1748.7853 0.0

					SET_OBJECT_COORDINATES boat_buoys[36] -1939.3936 1841.2628 0.0 
					SET_OBJECT_COORDINATES boat_buoys[37] -1934.5808 1828.7853 0.0

					SET_OBJECT_COORDINATES boat_buoys[38] -1809.3936 1801.2628 0.0 
					SET_OBJECT_COORDINATES boat_buoys[39] -1804.5808 1788.7853 0.0


					SET_OBJECT_COORDINATES boat_buoys[40] -1831.8530 1909.1050 0.0 
					SET_OBJECT_COORDINATES boat_buoys[41] -1847.2532 1906.1124 0.0

					SET_OBJECT_COORDINATES boat_buoys[42] -1879.0018 2073.6008 0.0 
					SET_OBJECT_COORDINATES boat_buoys[43] -1893.7864 2068.1663 0.0

					//New seg
					//SET_OBJECT_COORDINATES boat_buoys[44] -1874.8081 2140.4998 0.0
				   //	SET_OBJECT_COORDINATES boat_buoys[45] -1887.9290 2150.6289 0.0 
				   //	SET_OBJECT_COORDINATES boat_buoys[46] -1927.5781 2281.2673 20.0 
				   //	SET_OBJECT_COORDINATES boat_buoys[47] -1937.5028 2282.2891 20.0
				   //	SET_OBJECT_COORDINATES boat_buoys[48] -1975.4021 2302.5916 22.0
				   //	SET_OBJECT_COORDINATES boat_buoys[49] -1970.9758 2310.3611 22.0
		
					
			ENDIF

ENDIF





RETURN

//
//

boat_deletingbuoys:
	buoy_counter = 0

	WHILE buoy_counter < 50
		DELETE_OBJECT boat_buoys[buoy_counter]
		DELETE_OBJECT boat_ramps[buoy_counter]
		buoy_counter ++
	ENDWHILE

RETURN




boat_getlanguage:
	//boat_language = 0 // set to English by default
	GET_CURRENT_LANGUAGE boat_language
RETURN

	
// Mission boat failed

mission_boat_failed:


LOAD_SCENE_IN_DIRECTION	-2185.3347 2410.4092 3.9752 122.2585 


CLEAR_HELP
CLEAR_PRINTS
SET_CHAR_HEADING scplayer boat_playerstarth

SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT

RETURN

   
// mission boat passed
mission_boat_passed:

IF boat_passed_once = 0
CLEAR_MISSION_AUDIO 3

PRINT_BIG BOAT_P2 5000 1
PRINT BOAT_P1 5000 1
PLAY_MISSION_PASSED_TUNE 2
//ADD_SCORE player1 30000
CLEAR_WANTED_LEVEL player1
//PLAY_MISSION_PASSED_TUNE 1

PLAYER_MADE_PROGRESS 1
REGISTER_ODDJOB_MISSION_PASSED
	boat_passed_once = 1
ENDIF

/*
IF boat_gold_rewardgiven = 0
	IF boat_accelerate_goldachieved = 1
		IF boat_simplecircuit_goldachieved = 1
			IF boat_slalom_goldachieved = 1
				IF boat_skijump_goldachieved = 1
					IF boat_hover_goldachieved = 1
					
						boat_gold_rewardgiven = 1
						boat_silver_rewardgiven = 99
						boat_bronze_rewardgiven = 99
					
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF boat_silver_rewardgiven = 0
	IF boat_accelerate_silverachieved = 1
		IF boat_simplecircuit_silverachieved = 1
			IF boat_slalom_silverachieved = 1
				IF boat_skijump_silverachieved = 1
					IF boat_hover_silverachieved = 1
					
						boat_silver_rewardgiven = 1
						boat_bronze_rewardgiven = 99
					
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF


IF boat_bronze_rewardgiven = 0
	IF boat_accelerate_bronzeachieved = 1
		IF boat_simplecircuit_bronzeachieved = 1
			IF boat_slalom_bronzeachieved = 1
				IF boat_skijump_bronzeachieved = 1
					IF boat_hover_bronzeachieved = 1
						
						boat_bronze_rewardgiven = 1
			
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF


IF boat_gold_rewardgiven = 1
   WRITE_DEBUG goldinalltests
   boat_gold_rewardgiven = 2
ENDIF


IF boat_silver_rewardgiven = 1
   WRITE_DEBUG silverinalltests
   boat_silver_rewardgiven = 2
ENDIF



IF boat_bronze_rewardgiven = 1
   WRITE_DEBUG bronzeinalltests
   boat_bronze_rewardgiven = 2
ENDIF
*/


RETURN
		

// mission cleanup
mission_cleanup_boat:
ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_ENABLE_HELI_AUDIO
REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_AWARD_TRACK_STOP


SET_PLAYER_CAN_DO_DRIVE_BY player1 TRUE


SET_CHAR_DROWNS_IN_WATER scplayer TRUE




CLEAR_MISSION_AUDIO 3




//REMOVE_BLIP
//CLEAR_ONSCREEN_COUNTER
//CLEAR_ONSCREEN_TIMER

DISPLAY_HUD TRUE
DISPLAY_RADAR TRUE
DISPLAY_CAR_NAMES TRUE
DISPLAY_ZONE_NAMES TRUE


//SET_DEATHARREST_STATE ON
CLEAR_ONSCREEN_TIMER boat_timer

//REMOVE_BLIP boat_tvb
REMOVE_BLIP buoyl_b
REMOVE_BLIP buoyr_b
REMOVE_BLIP buoyls_b
REMOVE_BLIP buoyrs_b



MARK_MODEL_AS_NO_LONGER_NEEDED omoboat



MARK_CHAR_AS_NO_LONGER_NEEDED instructor_captain
MARK_CHAR_AS_NO_LONGER_NEEDED boat_captain


DELETE_CAR instructor_boat
DELETE_CAR instructor_heli
DELETE_CHAR boat_blocker1driver
DELETE_CHAR boat_blocker2driver
DELETE_CAR boat_blocker1
DELETE_CAR boat_blocker2
DELETE_CAR boat_blocker3
DELETE_CHAR boat_blocker3driver

MARK_MODEL_AS_NO_LONGER_NEEDED DINGHY
MARK_MODEL_AS_NO_LONGER_NEEDED POLMAV
MARK_MODEL_AS_NO_LONGER_NEEDED COASTG
MARK_MODEL_AS_NO_LONGER_NEEDED MARQUIS
MARK_MODEL_AS_NO_LONGER_NEEDED TROPIC

MARK_MODEL_AS_NO_LONGER_NEEDED vortex
 

RELEASE_WEATHER
//SET_CHAR_HEADING scplayer boat_playerstarth
//SET_CAMERA_BEHIND_PLAYER 
//RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON


GET_GAME_TIMER timer_mobile_start



IF boat_gold_rewardgiven = 0
	IF boat_accelerate_goldachieved = 1
		IF boat_simplecircuit_goldachieved = 1
			IF boat_slalom_goldachieved = 1
				IF boat_skijump_goldachieved = 1
					IF boat_hover_goldachieved = 1
					
						boat_gold_rewardgiven = 1
						boat_silver_rewardgiven = 99
						boat_bronze_rewardgiven = 99
					
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF boat_silver_rewardgiven = 0
	IF boat_accelerate_silverachieved = 1
		IF boat_simplecircuit_silverachieved = 1
			IF boat_slalom_silverachieved = 1
				IF boat_skijump_silverachieved = 1
					IF boat_hover_silverachieved = 1
					
						boat_silver_rewardgiven = 1
						boat_bronze_rewardgiven = 99
					
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF


IF boat_bronze_rewardgiven = 0
	IF boat_accelerate_bronzeachieved = 1
		IF boat_simplecircuit_bronzeachieved = 1
			IF boat_slalom_bronzeachieved = 1
				IF boat_skijump_bronzeachieved = 1
					IF boat_hover_bronzeachieved = 1
						
						boat_bronze_rewardgiven = 1
			
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF


IF boat_gold_rewardgiven = 1
   //WRITE_DEBUG goldinalltests
  	SWITCH_CAR_GENERATOR boat_gold_generator 101
	SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR boat_gold_generator TRUE

	SWITCH_CAR_GENERATOR boat_silver_generator 101
	SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR boat_silver_generator TRUE


	SWITCH_CAR_GENERATOR boat_bronze_generator 101
	SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR boat_bronze_generator TRUE

   boat_gold_rewardgiven = 2
ENDIF


IF boat_silver_rewardgiven = 1
   //WRITE_DEBUG silverinalltests
 	SWITCH_CAR_GENERATOR boat_silver_generator 101
	SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR boat_silver_generator TRUE


	SWITCH_CAR_GENERATOR boat_bronze_generator 101
	SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR boat_bronze_generator TRUE


   boat_silver_rewardgiven = 2
ENDIF



IF boat_bronze_rewardgiven = 1
  // WRITE_DEBUG bronzeinalltests
   SWITCH_CAR_GENERATOR boat_bronze_generator 101
   SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR boat_bronze_generator TRUE


   boat_bronze_rewardgiven = 2
ENDIF





flag_player_on_mission = 0
MISSION_HAS_FINISHED
RETURN

}
		
/*
[BOAT_T1:BOAT]
time:

[BOAT_A:BOAT]
basic seamanship

[BOAT_B:BOAT]
plot a course

[BOAT_C:BOAT]
fresh slalom

[BOAT_C1:BOAT]
~s~Navigate through the centre of the each pair of buoys in as quick a time as possible. 

[BOAT_D:BOAT]
flying fish

[BOAT_D1:BOAT]
~s~ Power towards the jump ramp and land within the parallel buoys to register a valid jump.

[BOAT_E:BOAT]
land, sea and air

[BOAT_3:BOAT]
~s~Press ~T~ to return to the Boat School.~N~Press ~X~ to retry.

[BOAT_7:BOAT]
Finish Time:  ~1~:~1~s

[BOAT_9:BOAT]
Damage Penalty: ~1~:~1~s 

[BOAT_10:BOAT]
Overall Time: ~1~:~1~s

[BOAT_11:BOAT]
Jump Distance: ~1~:~1~m

[BOAT_12:BOAT]
Jump Invalid!

[BOAT_31:BOAT]
~y~Checkpoint

[BOAT_32:BOAT]
~s~In stopping region

[BOAT_33:BOAT]
~s~Finished!

[BOAT_34:BOAT]
~r~Time over!

[BOAT_46:BOAT]
new record!

[BOAT_47:BOAT]
new certificate awarded!

[BOAT_48:BOAT]
~s~Press ~X~ to continue.

[BOAT_52:BOAT]
Navigate

[BOAT_53:BOAT]
Exit

[BOAT_54:BOAT]
Start

[BOAT_55:BOAT]
GOLD 

[BOAT_56:BOAT]
SILVER 

[BOAT_57:BOAT]
BRONZE 

[BOAT_58:BOAT]
record time ~1~:~1~s

[BOAT_59:BOAT]
finish under ~1~.~1~s to qualify 

[BOAT_60:BOAT]
longest jump ~1~ meters

[BOAT_61:BOAT]
jump over ~1~ meters to qualify

[BOAT_62:BOAT]
finish under ~1~ seconds to qualify

[BOAT_63:BOAT]
finish under ~1~ minutes to qualify 

[BOAT_64:BOAT]
NO AWARD

[BOAT_72]
~<~~>~



  */




/*
VAR_INT boat_which_missions_are_open_flag
boat_which_missions_are_open_flag = 1

VAR_INT boat_accelerate_best_score
VAR_INT boat_simplecircuit_best_score
VAR_INT boat_slalom_best_score
VAR_INT boat_skijump_best_score
VAR_INT boat_hover_best_score

boat_accelerate_best_score = 60000
boat_simplecircuit_best_score = 80000
boat_slalom_best_score = 180000
boat_skijump_best_score = 10
boat_hover_best_score = 200000

VAR_INT boat_passed_once


VAR_INT pimp_passed_once
VAR_INT courierLA_passed_once
VAR_INT courierLV_passed_once
VAR_INT courierSF_passed_once
*/