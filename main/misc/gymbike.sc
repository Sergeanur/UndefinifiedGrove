MISSION_START

// *****************************************************************************************
// ************************************* Gym Objects ****************************************
// *****************************************************************************************
// ************************************* Paul Davis ****************************************
// *****************************************************************************************

VAR_INT main_menu_bike bike_txt gym_TIMERC

{

exercise_bike:

SCRIPT_NAME GYMBIKE

//////////////////////////////////////////////////////////////////////////////////
//object_script://////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////

LVAR_INT exercise_bike

LVAR_FLOAT exbike_sin_y
LVAR_FLOAT exbike_cos_x

LVAR_FLOAT exbike_degrees
LVAR_FLOAT exbike_heading




//////////////////bike variables
LVAR_FLOAT exbikex 
LVAR_FLOAT exbikey 
LVAR_FLOAT exbikez
LVAR_FLOAT exbikecamx 
LVAR_FLOAT exbikecamy 
LVAR_FLOAT exbikecamz
LVAR_FLOAT exbikelookcamx 
LVAR_FLOAT exbikelookcamy 
LVAR_FLOAT exbikelookcamz
LVAR_FLOAT speed_bike
LVAR_FLOAT animframe_bike
VAR_FLOAT ply_exbikex
VAR_FLOAT ply_exbikey
VAR_INT speedbar_bike //was an int linked to speed_bike
VAR_INT levelbar_bike 
VAR_INT distanceon_bike
LVAR_FLOAT bike_resistance //was an int
LVAR_FLOAT bike_levelresistance
LVAR_FLOAT animspeed_bike
VAR_FLOAT distancetoaddbike_gym
VAR_FLOAT distanceon_bikefloat
LVAR_FLOAT bikestat_gym
//////////////////bike variables

//////////exbike specific flags
LVAR_INT startexbike_flag
LVAR_INT unpauseplayeranimbike_flag
LVAR_INT repbike_flag
LVAR_INT bikestillanim_flag
LVAR_INT playbikeslow_flag
LVAR_INT playbikefast_flag
LVAR_INT playbikefaster_flag
LVAR_INT buttonpressgymbike_flag
LVAR_INT finishedbike_flag
VAR_INT bike_TIMERC


//////////exbike specific flags

VAR_FLOAT temp_ply_z

LVAR_INT bike_inside

VAR_INT bike_pedals

VAR_INT bike_speed_level

VAR_INT levelbar_bike_1

VAR_INT bike_has_KD

VAR_FLOAT expedalsx expedalsy

VAR_FLOAT bike_pedals_z

VAR_INT bike_shake_speed

VAR_INT bike_start_sound 

VAR_INT bike_show_stat

LVAR_INT flag

bike_start_sound = 0

temp_ply_z = 0.0

bike_shake_speed = 0

bike_TIMERC = 0

bike_has_KD = 0

exbikex = 0.0
exbikey = 0.0
exbikez = 0.0
exbikecamx = 0.0
exbikecamy = 0.0
exbikecamz = 0.0
exbikelookcamx = 0.0
exbikelookcamy = 0.0
exbikelookcamz = 0.0
speed_bike = 0.0
animframe_bike = 0.0
ply_exbikex = 0.0
ply_exbikey = 0.0
bike_resistance = 0.0
bike_levelresistance = 0.0
bikestat_gym = 0.0
bike_speed_level = 0

//exbike specific flags
startexbike_flag = 0
unpauseplayeranimbike_flag = 0
repbike_flag = 0
bikestillanim_flag = 0
playbikeslow_flag = 0
playbikefast_flag = 0
playbikefaster_flag = 0
buttonpressgymbike_flag = 0 
finishedbike_flag = 0

ply_exbikex = 0.0
ply_exbikey = 0.0
speedbar_bike = 0
levelbar_bike = 0
distanceon_bike = 0
distancetoaddbike_gym = 0.0
distanceon_bikefloat = 0.0

//exbike specific flags
startexbike_flag = 0
unpauseplayeranimbike_flag = 0
repbike_flag = 0
bikestillanim_flag = 0
playbikeslow_flag = 0
playbikefast_flag = 0
playbikefaster_flag = 0
buttonpressgymbike_flag = 0
finishedbike_flag = 0
levelbar_bike = 0

flag = 0
bike_inside = 0
bike_txt = 0

TIMERA = 0

TIMERB = 0

IF flag = 1

    CREATE_OBJECT_NO_OFFSET cm_box 0.0 0.0 0.0 exercise_bike

ENDIF

	IF DOES_OBJECT_EXIST exercise_bike

		GET_OBJECT_COORDINATES exercise_bike exbikex exbikey exbikez

		GET_OBJECT_COORDINATES exercise_bike exbikelookcamx exbikelookcamy exbikelookcamz
			
		GET_OBJECT_HEADING exercise_bike exbike_heading

	ENDIF

	bike_pedals_z = exbikez	+ 0.15

	exbike_degrees = exbike_heading

	exbike_degrees = exbike_degrees - 90.0

	SIN exbike_degrees exbike_sin_y
	COS exbike_degrees exbike_cos_x

	exbike_sin_y = exbike_sin_y * 0.05
	exbike_cos_x = exbike_cos_x * 0.05

	expedalsx = exbikex + exbike_cos_x
	expedalsy = exbikey + exbike_sin_y

	CREATE_OBJECT pedals expedalsx expedalsy bike_pedals_z bike_pedals
 	SET_OBJECT_HEADING bike_pedals exbike_heading

	exbikecamz = exbikez + 0.9

	exbike_degrees = exbike_heading

	// Offset for Camera
	exbike_degrees = exbike_degrees - 45.0

	SIN exbike_degrees exbike_sin_y
	COS exbike_degrees exbike_cos_x

	exbike_sin_y = exbike_sin_y * 2.8
	exbike_cos_x = exbike_cos_x * 2.8

	exbikecamx = exbikex + exbike_cos_x  
	exbikecamy = exbikey + exbike_sin_y

	exbike_heading = exbike_heading - 180.0

	// Getting markers Offset
	exbike_degrees = exbike_degrees + 45.0

	SIN exbike_degrees exbike_sin_y
	COS exbike_degrees exbike_cos_x

	exbike_sin_y = exbike_sin_y * 0.5
	exbike_cos_x = exbike_cos_x * 0.5

	exbikex = exbikex + exbike_cos_x
	exbikey = exbikey + exbike_sin_y

	exbike_degrees = exbike_heading

	exbike_degrees = exbike_degrees - 90.0

	SIN exbike_degrees exbike_sin_y
	COS exbike_degrees exbike_cos_x

	exbike_sin_y = exbike_sin_y * 0.50
	exbike_cos_x = exbike_cos_x * 0.50

	ply_exbikex = exbikex + exbike_cos_x  
	ply_exbikey = exbikey + exbike_sin_y

	exbikez = exbikez + 0.05

	speedbar_bike	= 0

	distanceon_bike = 0

	are_anims_loaded = 0

	gym_bike_loop:

	WAIT 0

	IF NOT DOES_OBJECT_EXIST exercise_bike
		DELETE_OBJECT bike_pedals
		TERMINATE_THIS_SCRIPT
	ENDIF

    IF NOT IS_OBJECT_WITHIN_BRAIN_ACTIVATION_RANGE exercise_bike
		DELETE_OBJECT bike_pedals
		TERMINATE_THIS_SCRIPT
	ENDIF

	IF NOT IS_CHAR_DEAD scplayer

	//locate around the gym area - locate around the exercise bike 
	IF startexbike_flag = 0

		IF finishedbike_flag = 0

			IF LOCATE_CHAR_ON_FOOT_3D scplayer exbikex exbikey exbikez 1.2 1.2 4.0 FALSE

				IF bike_inside = 0
					PRINT_HELP_FOREVER ( GYM1_81 ) 
					bike_inside = 1
				ENDIF

				IF IS_BUTTON_PRESSED PAD1 TRIANGLE

					IF flag_player_on_mission = 0
					OR gym_is_running = 1

						GET_CURRENT_DATE gym_day gym_month

						IF gym_day > gym_final_day
						OR gym_month > gym_final_month

							startexbike_flag = 1
							playerexercising_flag = 1
							gym_TIMERC = 0

						ELSE
							PRINT_NOW ( GYM1_1A ) 4000 1 //	You have worked out enough for today come back tommorow!
						ENDIF

				  	ELSE
						PRINT_NOW ( GYM1_90 ) 4000 1 // ~s~You look a bit busy, why don't you come back later.
				  	ENDIF
				ENDIF

			ELSE

				IF bike_inside = 1
					CLEAR_HELP	
					bike_inside = 0
				ENDIF
			ENDIF

		ELSE
			IF NOT LOCATE_CHAR_ON_FOOT_2D scplayer exbikex exbikey 2.0 2.0 FALSE
				finishedbike_flag = 0
			ENDIF
		ENDIF
	ENDIF

	///////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// EXERCISE BIKE /////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////

IF startexbike_flag = 1
		
	IF are_anims_loaded = 0
		
		REQUEST_ANIMATION GYMNASIUM

		WHILE NOT HAS_ANIMATION_LOADED GYMNASIUM
	        WAIT 0
		ENDWHILE

	   	CLEAR_MISSION_AUDIO 4
	 	LOAD_MISSION_AUDIO 4 SOUND_BANK_GYM

	 	WHILE NOT HAS_MISSION_AUDIO_LOADED 4
			WAIT 0
		ENDWHILE

		are_anims_loaded = 1
	ENDIF

	IF playerexercising_flag = 1
	AND NOT IS_CHAR_DEAD scplayer 

		SET_CHAR_COLLISION scplayer FALSE
		SET_PLAYER_CONTROL player1 OFF
		SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer ply_exbikex ply_exbikey exbikez
		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
	 	SET_FIXED_CAMERA_POSITION exbikecamx exbikecamy exbikecamz 0.0 0.0 0.0
	 	SET_CHAR_HEADING scplayer exbike_heading
		POINT_CAMERA_AT_POINT exbikelookcamx exbikelookcamy exbikecamz JUMP_CUT
	 	//POINT_CAMERA_AT_POINT exbikex exbikey exbikecamz JUMP_CUT

		playerexercising_flag = 2
	ENDIF

	//player getting onto bench anim
	IF playerexercising_flag = 2
	AND NOT IS_CHAR_DEAD scplayer 

		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

		TASK_PLAY_ANIM scplayer gym_bike_geton GYMNASIUM 4.0 FALSE FALSE FALSE TRUE -1

		PRINT_HELP_FOREVER GYM1_60  
		
		// Create the menu.
		
		IF current_Language = 4
		OR current_Language = 2
			CREATE_MENU GYM1_D 31.0 180.0 178.0 1 TRUE TRUE FO_CENTRE main_menu_bike
			SET_MENU_COLUMN main_menu_bike 0 GYM1_89 GYM1_61 GYM1_62 GYM1_63 GYM1_64 GYM1_65 GYM1_66 GYM1_67 GYM1_68 GYM1_69 GYM1_70 DUMMY DUMMY
		ELSE
			CREATE_MENU GYM1_D 31.0 150.0 178.0 1 TRUE TRUE FO_CENTRE main_menu_bike
			SET_MENU_COLUMN main_menu_bike 0 GYM1_89 GYM1_61 GYM1_62 GYM1_63 GYM1_64 GYM1_65 GYM1_66 GYM1_67 GYM1_68 GYM1_69 GYM1_70 DUMMY DUMMY
		ENDIF
		SET_ACTIVE_MENU_ITEM main_menu_bike 0

		WHILE NOT IS_CHAR_DEAD scplayer

			WAIT 0

			gym_TIMERC ++

			IF IS_BUTTON_PRESSED PAD1 TRIANGLE  // Quit the bike entirely.
            AND gym_TIMERC > 20
				CLEAR_HELP
				DELETE_MENU main_menu_bike
				levelbar_bike = 0
				GOTO bike_quit_back
		   	ENDIF 

			IF IS_BUTTON_PRESSED PAD1 CROSS
	
				CLEAR_HELP

				GET_MENU_ITEM_SELECTED main_menu_bike levelbar_bike

				levelbar_bike++
                DELETE_MENU main_menu_bike
				GOTO bike_out_of_loop
		   	ENDIF 
		ENDWHILE

		bike_out_of_loop:
        playerexercising_flag = 3
        TIMERB = 0

	ENDIF

	//when get on animation has finished start the next one 
	IF playerexercising_flag = 3
	AND NOT IS_CHAR_DEAD scplayer 

		IF IS_CHAR_PLAYING_ANIM scplayer gym_bike_geton
			GET_CHAR_ANIM_CURRENT_TIME scplayer gym_bike_geton animframe_bike
			IF animframe_bike = 1.0
				TASK_PLAY_ANIM scplayer gym_bike_slow GYMNASIUM 4.0 TRUE FALSE FALSE TRUE -1
			  	PLAY_OBJECT_ANIM bike_pedals Pedals_slow GYMNASIUM 4.0 TRUE -1
				playerexercising_flag = 4
			ENDIF
		ENDIF
	ENDIF

	//pause animation
	IF playerexercising_flag = 4 
	AND NOT IS_CHAR_DEAD scplayer 

		IF IS_CHAR_PLAYING_ANIM scplayer gym_bike_slow
			GET_CHAR_ANIM_CURRENT_TIME scplayer gym_bike_slow animframe_bike
			IF animframe_bike = 0.0
				SET_CHAR_ANIM_PLAYING_FLAG scplayer gym_bike_slow FALSE
				
				DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING speedbar_bike   COUNTER_DISPLAY_BAR    1 GYM1_1  // speed
				DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING levelbar_bike   COUNTER_DISPLAY_NUMBER 2 GYM1_4  // level
				DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING distanceon_bike COUNTER_DISPLAY_NUMBER 3 GYM1_3  // distance
			   	
				unpauseplayeranimbike_flag = 1
				playerexercising_flag = 5
				TIMERB = 0
			ENDIF
		ENDIF
	ENDIF

	//Button presses and speed of animations
	IF playerexercising_flag = 5
	AND NOT IS_CHAR_DEAD scplayer 

		IF bike_txt = 0
			PRINT_HELP_FOREVER GYM1_40
			bike_txt = 1
		ENDIF

		IF TIMERB > 4000
		AND bike_txt = 1
			CLEAR_HELP
			PRINT_HELP_FOREVER GYM1_41
			bike_txt = 2
		ENDIF

		IF TIMERB > 8000
		AND bike_txt = 2
 	        CLEAR_HELP
            bike_txt = 101
		ENDIF

		///////////////stats that effect incline and level bar
   		GET_FLOAT_STAT STAMINA bikestat_gym

		bikestat_gym = bikestat_gym / 100.0	
		bike_resistance = bike_levelresistance + bikestat_gym

		//////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////
		speed_bike = speed_bike - 0.7 // tiredness WAS 1.0
		//////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////

		IF IS_BUTTON_PRESSED PAD1 CROSS
			IF repbike_flag = 0
				speed_bike += bike_resistance
				repbike_flag = 1
			ENDIF
		ENDIF	

		
		
		//when button is not pressed reset
		
		IF NOT IS_BUTTON_PRESSED PAD1 CROSS
		 	IF repbike_flag = 1
			    repbike_flag = 0
			ENDIF
		ENDIF

		//keep bar within boundaries
		IF speed_bike < 0.0					//speed_bike 
			speed_bike = 0.0
		ENDIF
		
		IF speed_bike > 100.0
			speed_bike = 100.0
		ENDIF

		//un pause the player at start - done once each time player is on bike
		IF unpauseplayeranimbike_flag = 1
			IF IS_CHAR_PLAYING_ANIM scplayer gym_bike_slow
				SET_CHAR_ANIM_PLAYING_FLAG scplayer gym_bike_slow TRUE
				SET_CHAR_ANIM_SPEED scplayer gym_bike_slow 0.5
			  	SET_OBJECT_ANIM_SPEED bike_pedals Pedals_slow 0.5
				TIMERA = 0
				unpauseplayeranimbike_flag = 2
			ENDIF
		ENDIF

		IF unpauseplayeranimbike_flag = 2
		AND NOT IS_CHAR_DEAD scplayer 

			GET_FLOAT_STAT STAMINA bikestat_gym

			bikestat_gym = bikestat_gym / 200.0	

			IF bikestat_gym < 0.0
				bikestat_gym = 0.0
			ENDIF

			bike_speed_level =# bikestat_gym // 0 to 5

			levelbar_bike_1 = levelbar_bike - bike_speed_level // 0 to 10 minus 0 to 5

			//idle animation
			IF speed_bike = 0.0
				IF bikestillanim_flag = 0
					TASK_PLAY_ANIM scplayer gym_bike_still GYMNASIUM 4.0 TRUE FALSE FALSE TRUE -1
				  	PLAY_OBJECT_ANIM bike_pedals Pedals_still GYMNASIUM 4.0 TRUE -1
					playbikeslow_flag = 0
					playbikefast_flag = 0
					playbikefaster_flag = 0
					bikestillanim_flag = 1
				ENDIF
			ENDIF

			//play animations
			IF playbikeslow_flag = 0
				IF levelbar_bike_1 < 3
				AND NOT speed_bike = 0.0
					TASK_PLAY_ANIM scplayer gym_bike_slow GYMNASIUM 4.0 TRUE FALSE FALSE TRUE -1//100
				  	PLAY_OBJECT_ANIM bike_pedals Pedals_slow GYMNASIUM 4.0 TRUE -1
					playbikeslow_flag = 1
					playbikefast_flag = 0
					playbikefaster_flag = 0
					bikestillanim_flag = 0
				ENDIF
			ENDIF
			
			IF playbikefast_flag = 0
				IF levelbar_bike_1 > 2
				AND levelbar_bike_1 < 7
				AND NOT speed_bike = 0.0
					TASK_PLAY_ANIM scplayer gym_bike_fast GYMNASIUM 4.0 TRUE FALSE FALSE TRUE -1//100
			     	PLAY_OBJECT_ANIM bike_pedals Pedals_med GYMNASIUM 4.0 TRUE -1
					playbikeslow_flag = 0
					playbikefaster_flag = 0
					playbikefast_flag = 1
					bikestillanim_flag = 0
				ENDIF
			ENDIF
			
			IF playbikefaster_flag = 0
			  	IF levelbar_bike_1 > 6
			  	AND levelbar_bike_1 < 11
				AND NOT speed_bike = 0.0
					TASK_PLAY_ANIM scplayer gym_bike_faster GYMNASIUM 4.0 TRUE FALSE FALSE TRUE -1//100
				  	PLAY_OBJECT_ANIM bike_pedals Pedals_fast GYMNASIUM 4.0 TRUE -1
					playbikeslow_flag = 0
					playbikefast_flag = 0
					playbikefaster_flag = 1
					bikestillanim_flag = 0
				ENDIF
			ENDIF

			////////////////////LEVEL
			
			IF IS_BUTTON_PRESSED PAD1 DPADRIGHT

				IF buttonpressgymbike_flag = 0
					IF levelbar_bike > 0
					AND levelbar_bike < 10
						levelbar_bike++
                        
						IF HAS_MISSION_AUDIO_LOADED 4
	                        REPORT_MISSION_AUDIO_EVENT_AT_OBJECT exercise_bike SOUND_GYM_INCREASE_DIFFICULTY
						ENDIF

						speed_bike = speed_bike - 20.0

						IF speed_bike < 0.0					
						  	speed_bike = 0.0
						ENDIF

                        buttonpressgymbike_flag = 1
                    ENDIF
				ENDIF
			ENDIF
			
			IF IS_BUTTON_PRESSED PAD1 DPADLEFT
			    
				IF buttonpressgymbike_flag = 0
					IF levelbar_bike > 1
		            AND levelbar_bike < 11
		                levelbar_bike--
                        
						IF HAS_MISSION_AUDIO_LOADED 4
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT exercise_bike SOUND_GYM_INCREASE_DIFFICULTY
						ENDIF

                        buttonpressgymbike_flag = 2
        		    ENDIF
				ENDIF
			ENDIF
			 
			//do the check to make sure the button is not held down
			IF NOT IS_BUTTON_PRESSED PAD1 DPADRIGHT	
				IF buttonpressgymbike_flag = 1
					buttonpressgymbike_flag = 0
				ENDIF
			ENDIF

			IF NOT IS_BUTTON_PRESSED PAD1 DPADLEFT	
				IF buttonpressgymbike_flag = 2
					buttonpressgymbike_flag = 0
				ENDIF
			ENDIF
			//////////////////////
			
			
			IF levelbar_bike = 1
				bike_levelresistance = 6.5 // 6.5
			ENDIF	

			IF levelbar_bike = 2
				bike_levelresistance = 5.9 // 5.9
			ENDIF	
			
			IF levelbar_bike = 3
				bike_levelresistance = 5.0 // 5.0
			ENDIF	
			
			IF levelbar_bike = 4
				bike_levelresistance = 4.3 // 4.3
			ENDIF	

			IF levelbar_bike = 5
				bike_levelresistance = 3.7 // 3.7
			ENDIF	
			
			IF levelbar_bike = 6
				bike_levelresistance = 2.9 // 2.9
			ENDIF		

			IF levelbar_bike = 7
				bike_levelresistance = 2.2 // 2.2
			ENDIF	

			IF levelbar_bike = 8
				bike_levelresistance = 1.5 // 1.5
			ENDIF	
			
			IF levelbar_bike = 9
				bike_levelresistance = 1.1 // 1.1
			ENDIF

			IF levelbar_bike = 10
				bike_levelresistance = 0.7 // 0.7
			ENDIF		


			//animation and speed changes
			IF speed_bike >= 0.0
				IF speed_bike <= 10.0
					animspeed_bike = 0.9
				ENDIF
			ENDIF

			IF speed_bike >= 11.0 
				IF speed_bike <= 20.0
					animspeed_bike = 1.0
				ENDIF
			ENDIF

			IF speed_bike >= 21.0
				IF speed_bike <= 30.0
					animspeed_bike = 1.1
				ENDIF
			ENDIF
			
			IF speed_bike >= 31.0
				IF speed_bike <= 40.0
					animspeed_bike = 1.2
				ENDIF
			ENDIF			

			IF speed_bike >= 41.0
				IF speed_bike <= 50.0
					animspeed_bike = 1.3
				ENDIF
			ENDIF

			IF speed_bike >= 51.0
				IF speed_bike <= 60.0
					animspeed_bike = 1.4
				ENDIF
			ENDIF

			IF speed_bike >= 61.0
				IF speed_bike <= 70.0
					animspeed_bike = 1.5
				ENDIF
			ENDIF
			
			IF speed_bike >= 71.0
				IF speed_bike <= 80.0
					animspeed_bike = 1.6
				ENDIF
			ENDIF			

			IF speed_bike >= 81.0
				IF speed_bike <= 90.0
					animspeed_bike = 1.7
				ENDIF
			ENDIF

			IF speed_bike >= 91.0
				IF speed_bike <= 100.0
					animspeed_bike = 1.8
				ENDIF
			ENDIF
		
			IF IS_CHAR_PLAYING_ANIM	scplayer gym_bike_slow
			AND NOT IS_CHAR_DEAD scplayer 

				SET_CHAR_ANIM_SPEED scplayer gym_bike_slow animspeed_bike
			ENDIF

		    	IF IS_OBJECT_PLAYING_ANIM bike_pedals Pedals_slow
		    	AND NOT IS_CHAR_DEAD scplayer 

			  		SET_OBJECT_ANIM_SPEED bike_pedals Pedals_slow animspeed_bike
		    	ENDIF


			IF IS_CHAR_PLAYING_ANIM	scplayer gym_bike_fast
			AND NOT IS_CHAR_DEAD scplayer 

				SET_CHAR_ANIM_SPEED scplayer gym_bike_fast animspeed_bike
			ENDIF

		    	IF IS_OBJECT_PLAYING_ANIM bike_pedals Pedals_med
		    	AND NOT IS_CHAR_DEAD scplayer 

			  		SET_OBJECT_ANIM_SPEED bike_pedals Pedals_med animspeed_bike
		    	ENDIF

			IF IS_CHAR_PLAYING_ANIM	scplayer gym_bike_faster
			AND NOT IS_CHAR_DEAD scplayer 
		
				SET_CHAR_ANIM_SPEED scplayer gym_bike_faster animspeed_bike
			ENDIF

		   	IF IS_OBJECT_PLAYING_ANIM bike_pedals Pedals_fast
		   	AND NOT IS_CHAR_DEAD scplayer 
		   
		     	SET_OBJECT_ANIM_SPEED bike_pedals Pedals_fast animspeed_bike
		   	ENDIF

			// If player wants to quit out
			
			IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			OR gym_day_fitness > 200.0

				IF gym_day_fitness > 200.0000
             	    gym_day_fitness = 0.0
                ENDIF

				IF NOT IS_CHAR_DEAD scplayer

					bike_quit_back:
                    SHAKE_PAD PAD1 1 1
                    LVAR_INT bike_longest
					INCREMENT_INT_STAT DIST_EXERCISE_BIKE distanceon_bike
                    bike_longest = TIMERB
                    bike_longest = bike_longest / 1000
                    INCREMENT_INT_STAT LONGEST_EXERCISE_BIKE_TIME bike_longest

					IF NOT IS_CHAR_DEAD scplayer
                    	TASK_PLAY_ANIM scplayer gym_bike_getoff GYMNASIUM 4.0 FALSE FALSE FALSE FALSE -1
					  	PLAY_OBJECT_ANIM bike_pedals Pedals_still GYMNASIUM 4.0 FALSE -1
					ENDIF

					bike_quit_back_1:

					unpauseplayeranimbike_flag = 0
					repbike_flag = 0
					bikestillanim_flag = 0
					playbikeslow_flag =	0
					playbikefast_flag = 0
					playbikefaster_flag = 0
					buttonpressgymbike_flag = 0
					startexbike_flag = 0
					playerexercising_flag = 0
					levelbar_bike = 0

					distanceon_bike = 0
					distanceon_bikefloat = 0.0
					distancetoaddbike_gym = 0.0
					speedbar_bike = 0
					speed_bike = 0.0

					GET_FLOAT_STAT FAT fatstat_gym

					IF fatstat_gym <= 0.0
						SET_FLOAT_STAT FAT 0.0
					ENDIF

					IF DOES_OBJECT_EXIST exercise_bike
					  	SET_OBJECT_COLLISION exercise_bike TRUE
					ENDIF

					//shared flags
					animstate_flag = 0
					playernextset_flag = 0
					are_anims_loaded = 0

					REMOVE_ANIMATION GYMNASIUM
					CLEAR_MISSION_AUDIO 4
					
					CLEAR_ONSCREEN_COUNTER speedbar_bike
					CLEAR_ONSCREEN_COUNTER levelbar_bike
					CLEAR_ONSCREEN_COUNTER distanceon_bike

					RESTORE_CAMERA_JUMPCUT

					SET_PLAYER_CONTROL player1 OFF

					WAIT 0

					IF NOT IS_CHAR_DEAD scplayer
						SET_PLAYER_CONTROL player1 ON
					ENDIF

					finishedbike_flag = 1
                    
					DELETE_MENU main_menu_bike

					CLEAR_HELP

					SET_PLAYER_CONTROL player1 ON
					SET_CHAR_COLLISION scplayer TRUE

					WHILE IS_BUTTON_PRESSED PAD1 CROSS
						WAIT 0
					ENDWHILE
				ENDIF
			ENDIF
									 
			// Calcualte distances
			IF DOES_OBJECT_EXIST exercise_bike
				IF HAS_MISSION_AUDIO_LOADED 4
					IF speed_bike > 10.0
                   	    IF bike_start_sound = 0
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT exercise_bike SOUND_GYM_BIKE_START 
							bike_start_sound = 1
						ENDIF

					ELSE
						IF bike_start_sound = 1
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT exercise_bike SOUND_GYM_BIKE_STOP
							bike_start_sound = 0
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF TIMERA > 250
			 
				TIMERA -= 250
	 			
				distancetoaddbike_gym = speed_bike //distancetoaddbike_gym =# speed_bike
				distancetoaddbike_gym = distancetoaddbike_gym / 10.0

				//stat calculation stuff
				statbike_temp =# levelbar_bike
				statbike_temp /= 10.0
				statbike_temp += 1.0
				statbike_temp *= distancetoaddbike_gym 
							
				statbikestamina_ctr += statbike_temp //statbike_temp = what level your on * the distance its gone in that time

				///////////////////////////////////////////////////////////////////////////
				///////////////////////////////////////////////////////////////////////////
			    //	SHAKE_PAD PAD1 100 200
				///////////////////////////////////////////////////////////////////////////
				///////////////////////////////////////////////////////////////////////////

				IF statbikestamina_ctr >= 400.0 //1000.0

					statbikestamina_ctr -= 400.0 //1000.0

					GET_FLOAT_STAT FAT fatstat_gym

					IF fatstat_gym >= 1.0
						DECREMENT_FLOAT_STAT FAT 8.0
					ENDIF

			       	IF bike_show_stat = 3
 	                    bike_show_stat = 0
                        INCREMENT_FLOAT_STAT STAMINA 4.0
						gym_day_fitness = gym_day_fitness + 4.0
			       	ELSE																	  

			       		INCREMENT_FLOAT_STAT_NO_MESSAGE STAMINA 4.0
						gym_day_fitness = gym_day_fitness + 4.0
				   		bike_show_stat ++
			       	ENDIF
				ENDIF

				IF gym_day_fitness > 200.0
					GET_CURRENT_DATE gym_final_day gym_final_month
					PRINT_NOW ( GYM1_1B ) 4000 1 // You have reached your limit for today come back tommorow!
				ENDIF

				IF NOT distanceon_bike >= 999999
					distanceon_bikefloat += distancetoaddbike_gym
					distanceon_bike =# distanceon_bikefloat
				ENDIF   
				
			ENDIF   
			
		ENDIF

		bike_TIMERC ++

		bike_shake_speed = speedbar_bike - 100

		IF bike_shake_speed > -40 
			bike_shake_speed = -40
		ENDIF

		IF bike_TIMERC >= bike_shake_speed
			bike_TIMERC = 0
            speedbar_bike = speedbar_bike * 12
            speedbar_bike = speedbar_bike / 10
            SHAKE_PAD PAD1 100 speedbar_bike
		ENDIF

		speedbar_bike =# speed_bike

	ENDIF     

	ENDIF						 

ENDIF

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

GOTO gym_bike_loop

}


MISSION_END
