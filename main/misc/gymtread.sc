MISSION_START

// *****************************************************************************************
// ************************************* Gym Objects ****************************************
// *****************************************************************************************
// ************************************* Paul Davis ****************************************
// *****************************************************************************************

VAR_INT main_menu_tread tread_txt


{

exercise_tread:

//////////////////////////////////////////////////////////////////////////////////
//object_script://////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////

SCRIPT_NAME GYMTREA

LVAR_INT exercise_tread


////////// treadmill variables

LVAR_FLOAT treadmillx
LVAR_FLOAT treadmilly
LVAR_FLOAT treadmillz
LVAR_FLOAT treadmillcamx
LVAR_FLOAT treadmillcamy
LVAR_FLOAT treadmillcamz
LVAR_FLOAT treadmilllookcamx
LVAR_FLOAT treadmilllookcamy
LVAR_FLOAT treadmilllookcamz
LVAR_FLOAT treadmillheading
LVAR_FLOAT tread_degrees
LVAR_FLOAT tread_sin_y
LVAR_FLOAT tread_cos_x
LVAR_FLOAT animframe_tread 
LVAR_FLOAT animspeed_tread 
LVAR_INT switchtonextlevel_tread
LVAR_FLOAT speed_tread 
LVAR_FLOAT tread_resistance 
 
LVAR_FLOAT incline_leveltread
LVAR_FLOAT level_treadresitance
LVAR_FLOAT distancetoadd_tread
LVAR_FLOAT distanceon_treadfloat
LVAR_FLOAT sprintstat_gym

LVAR_INT incline_treadbar
LVAR_FLOAT incline_treadfloat
LVAR_FLOAT levelbar_float
LVAR_INT tread_inside
////////// treadmill variables
VAR_INT levelbar_tread
VAR_INT speed_treadbar 
VAR_INT incline_tread
VAR_INT distanceon_tread

VAR_INT tread_has_KD

LVAR_INT flag

VAR_INT tread_TIMERC

VAR_INT tread_shake_speed 

VAR_INT tread_start_sound tread_show_stat

tread_shake_speed = 0

tread_start_sound = 0

tread_TIMERC = 0

levelbar_tread = 0
speed_treadbar = 0
incline_tread = 0
distanceon_tread = 0

tread_has_KD = 0

starttreadmill_flag = 0
incline_tread_flag = 0
inclinebutton_flag = 0
reptread_flag = 0
buttonpressgymtread_flag = 0
playerstarttread_flag = 0
playwalkanim_flag = 0
playjoganim_flag = 0
playsprintanim_flag = 0
finishedtread_flag = 0
incline_leveltread = 1.0
levelbar_tread = 1
incline_tread = 1
playerexercising_flag = 0
distanceon_tread = 0
distanceon_treadfloat = 0.0
distancetoadd_tread = 0.0
are_anims_loaded = 0
finishedtread_flag = 1

tread_inside = 0

TIMERA = 0

TIMERB = 0

	flag = 0

	IF flag = 1

	    CREATE_OBJECT_NO_OFFSET beachtowel03 0.0 0.0 0.0 exercise_tread

	ENDIF

	IF DOES_OBJECT_EXIST exercise_tread

		GET_OBJECT_COORDINATES exercise_tread treadmillx treadmilly treadmillz

		GET_OBJECT_COORDINATES exercise_tread treadmilllookcamx treadmilllookcamy treadmilllookcamz
			
		GET_OBJECT_HEADING exercise_tread treadmillheading

	ENDIF

	incline_leveltread = 1.0
	levelbar_tread = 1
	incline_tread = 1
	levelbar_float = 1.0

	treadmillcamz = treadmillz + 1.50
	treadmilllookcamz = treadmillz + 0.75
	
	tread_degrees = treadmillheading

	// Offset for Camera

	tread_degrees = tread_degrees - 60.0

	SIN tread_degrees tread_sin_y
	COS tread_degrees tread_cos_x

	tread_sin_y = tread_sin_y * 3.0
	tread_cos_x = tread_cos_x * 3.0

	treadmillcamx = treadmillx - tread_cos_x  
	treadmillcamy = treadmilly - tread_sin_y

	// Offset for Marker

	tread_degrees = treadmillheading + 90.0

	SIN tread_degrees tread_sin_y
	COS tread_degrees tread_cos_x

	tread_sin_y = tread_sin_y * 1.5
	tread_cos_x = tread_cos_x * 1.5

	treadmillx = treadmillx - tread_cos_x
	treadmilly = treadmilly - tread_sin_y 

	treadmillz = treadmillz + 0.1

	are_anims_loaded = 0

gym_tread_loop:

WAIT 0

IF NOT DOES_OBJECT_EXIST exercise_tread

	TERMINATE_THIS_SCRIPT

ENDIF

IF NOT IS_CHAR_DEAD scplayer

//////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////	   TREADMILL	//////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////

//locate around treadmill
IF playerexercising_flag = 0
AND NOT IS_CHAR_DEAD scplayer

	IF starttreadmill_flag = 0

		IF finishedtread_flag = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer treadmillx treadmilly treadmillz 1.2 1.2 4.0 FALSE

				IF tread_inside = 0
					PRINT_HELP_FOREVER ( GYM1_81 ) 
					tread_inside = 1
				ENDIF

				IF IS_BUTTON_PRESSED PAD1 TRIANGLE

					IF flag_player_on_mission = 0
					OR gym_is_running = 1

						GET_CURRENT_DATE gym_day gym_month

						IF gym_day > gym_final_day
						OR gym_month > gym_final_month

							starttreadmill_flag = 1
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

				IF tread_inside = 1	
					CLEAR_HELP		
					tread_inside = 0
				ENDIF	
			ENDIF
		ELSE
			IF NOT LOCATE_CHAR_ON_FOOT_2D scplayer treadmillx treadmilly 2.0 2.0 FALSE
				finishedtread_flag = 0
			ENDIF
		ENDIF
	ENDIF
	
ENDIF

IF starttreadmill_flag = 1
AND NOT IS_CHAR_DEAD scplayer

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
		SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer treadmillx treadmilly treadmillz
 		SET_CHAR_HEADING scplayer treadmillheading		  
		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
	 	SET_FIXED_CAMERA_POSITION treadmillcamx treadmillcamy treadmillcamz 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT treadmilllookcamx treadmilllookcamy treadmilllookcamz JUMP_CUT

		playerexercising_flag = 2

	ENDIF

	//player getting onto treadmill
	IF playerexercising_flag = 2
		
		PRINT_HELP_FOREVER GYM1_60  
		  	
		// Create the menu.

		IF current_Language = 4
		OR current_Language = 2
			CREATE_MENU GYM1_F 31.0 180.0 184.0 1 TRUE TRUE FO_CENTRE main_menu_tread
			SET_MENU_COLUMN main_menu_tread 0 GYM1_89 GYM1_61 GYM1_62 GYM1_63 GYM1_64 GYM1_65 GYM1_66 GYM1_67 GYM1_68 GYM1_69 GYM1_70 DUMMY DUMMY
		ELSE
			CREATE_MENU GYM1_F 31.0 150.0 184.0 1 TRUE TRUE FO_CENTRE main_menu_tread
			SET_MENU_COLUMN main_menu_tread 0 GYM1_89 GYM1_61 GYM1_62 GYM1_63 GYM1_64 GYM1_65 GYM1_66 GYM1_67 GYM1_68 GYM1_69 GYM1_70 DUMMY DUMMY
		ENDIF
		SET_ACTIVE_MENU_ITEM main_menu_tread 0


		WHILE NOT IS_CHAR_DEAD scplayer

			WAIT 0

			gym_TIMERC++

			IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			AND gym_TIMERC > 20
				CLEAR_HELP
				DELETE_MENU main_menu_tread
				GOTO tread_quit_back
			ENDIF 

			IF IS_BUTTON_PRESSED PAD1 CROSS

				CLEAR_HELP

				GET_MENU_ITEM_SELECTED main_menu_tread levelbar_tread

				levelbar_tread++
				DELETE_MENU main_menu_tread
				TIMERA = 0
				GOTO tread_out_of_loop
		   	ENDIF 

		ENDWHILE

		tread_out_of_loop:

	  	IF tread_helpflag = 0
			PRINT_HELP GYM1_42
			WAIT 4000
			tread_helpflag = 1
		ENDIF

		IF tread_helpflag = 1	
			PRINT_HELP GYM1_43 
			WAIT 5000
			tread_helpflag = 2
		ENDIF
		
		IF tread_helpflag = 2
			PRINT_HELP ( GYM1_45 )
			WAIT 4000
			tread_helpflag = 4
			TIMERA = 0
		ENDIF	

		IF NOT IS_CHAR_DEAD scplayer
			TASK_PLAY_ANIM scplayer gym_tread_geton GYMNASIUM 4.0 FALSE FALSE FALSE TRUE -1
		ENDIF
		WAIT 0

		playerexercising_flag = 3

	ENDIF

	    // When get on animation has finished start the next one 
		IF playerexercising_flag = 3
		AND NOT IS_CHAR_DEAD scplayer

			IF IS_CHAR_PLAYING_ANIM scplayer gym_tread_geton
				GET_CHAR_ANIM_CURRENT_TIME scplayer gym_tread_geton animframe_tread
				IF animframe_tread = 1.0
					TASK_PLAY_ANIM scplayer gym_tread_walk GYMNASIUM 8.0 TRUE FALSE FALSE TRUE -1
					
					DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING speed_treadbar   COUNTER_DISPLAY_BAR    1 GYM1_1  // speed
					DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING levelbar_tread   COUNTER_DISPLAY_NUMBER 2 GYM1_4  // level
 					DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING distanceon_tread COUNTER_DISPLAY_NUMBER 3 GYM1_3  // distance
					
					speed_tread = 30.0
					playerexercising_flag = 4
				ENDIF
			ENDIF
		ENDIF

		// Button presses and speed of animations
		IF playerexercising_flag = 4
		AND NOT IS_CHAR_DEAD scplayer

	   		///////////////stats that effect incline and level bar

		    sprintstat_gym = sprintstat_gym / 500.0	
			lowerbodymusclestat_gym = lowerbodymusclestat_gym / 750.0

			sprintstat_gym = sprintstat_gym + lowerbodymusclestat_gym
			tread_resistance = level_treadresitance	+ sprintstat_gym

			
		   	//level bar
			speed_tread = speed_tread - incline_leveltread					

		
			IF IS_BUTTON_PRESSED PAD1 CROSS
				IF reptread_flag = 0
					speed_tread = speed_tread + tread_resistance
					reptread_flag = 1
				ENDIF
			ENDIF

			//when button is not pressed reset
			IF NOT IS_BUTTON_PRESSED PAD1 CROSS
				IF reptread_flag = 1
					reptread_flag = 0
				ENDIF
			ENDIF

			//keep bar within boundaries
			IF speed_tread < 0.0
				speed_tread = 0.0
			ENDIF
			
			IF speed_tread > 100.0
				speed_tread = 100.0
			ENDIF
			
			///////////////////////////////////////			
			//Player setting level of the treadmill
			//////////////////////INCLINE

			IF IS_BUTTON_PRESSED PAD1 DPADUP
			/*	
				//if player is at incline of 1 go to 2
				IF inclinebutton_flag = 0
					IF incline_tread_flag = 0
						incline_leveltread = 2.5
						incline_tread = 2
						ADD_ONE_OFF_SOUND treadmillx treadmilly treadmillz SOUND_IMRAN_ARM_BOMB
						incline_tread_flag = 1
						inclinebutton_flag = 1
					ENDIF
				ENDIF
				
				//if player is at incline of 2 go to 3
				IF inclinebutton_flag = 0
					IF incline_tread_flag = 1
						incline_leveltread = 3.5
						incline_tread = 3
						ADD_ONE_OFF_SOUND treadmillx treadmilly treadmillz SOUND_IMRAN_ARM_BOMB
						incline_tread_flag = 2
						inclinebutton_flag = 1
					ENDIF
				ENDIF
			*/
			ENDIF

			//incline going down
			IF IS_BUTTON_PRESSED PAD1 DPADDOWN
			/*
				//if player is at incline of 3 go to 2
				IF inclinebutton_flag = 0
					IF incline_tread_flag = 2
						incline_leveltread = 2.5
						incline_tread = 2
						ADD_ONE_OFF_SOUND treadmillx treadmilly treadmillz SOUND_IMRAN_ARM_BOMB
						incline_tread_flag = 1
						inclinebutton_flag = 2
					ENDIF
				ENDIF
				
				//if player is at incline of 2 go to 1
				IF inclinebutton_flag = 0
					IF incline_tread_flag = 1
						incline_leveltread = 1.5
						incline_tread = 1
						ADD_ONE_OFF_SOUND treadmillx treadmilly treadmillz SOUND_IMRAN_ARM_BOMB
						incline_tread_flag = 0
						inclinebutton_flag = 2
					ENDIF
				ENDIF
			*/
			ENDIF
			
			//do the check to make sure the button is not held down
			IF NOT IS_BUTTON_PRESSED PAD1 DPADUP
				IF inclinebutton_flag = 1
					inclinebutton_flag = 0
				ENDIF
			ENDIF

			IF NOT IS_BUTTON_PRESSED PAD1 DPADDOWN
				IF inclinebutton_flag = 2
					inclinebutton_flag = 0
				ENDIF
			ENDIF


			// Changing level of treadmill.

			IF IS_BUTTON_PRESSED PAD1 DPADRIGHT

				IF buttonpressgymtread_flag = 0
					IF levelbar_tread > 0
					AND levelbar_tread < 10
						levelbar_tread++
                        
						IF HAS_MISSION_AUDIO_LOADED 4
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT exercise_tread SOUND_GYM_INCREASE_DIFFICULTY
						ENDIF

						speed_tread = speed_tread - 10.0

						IF speed_tread < 0.0
						  	speed_tread = 0.0
						ENDIF

                        buttonpressgymtread_flag = 1
                    ENDIF
				ENDIF
			ENDIF
                                    
			
			IF IS_BUTTON_PRESSED PAD1 DPADLEFT
			    IF buttonpressgymtread_flag = 0
					IF levelbar_tread > 1
		            AND levelbar_tread < 11
		                
						levelbar_tread--
                        
						IF HAS_MISSION_AUDIO_LOADED 4
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT exercise_tread SOUND_GYM_INCREASE_DIFFICULTY
						ENDIF

                        buttonpressgymtread_flag = 2
        		    ENDIF
				ENDIF
			ENDIF
			 
			//do the check to make sure the button is not held down
			IF NOT IS_BUTTON_PRESSED PAD1 DPADRIGHT
				IF buttonpressgymtread_flag = 1
					buttonpressgymtread_flag = 0
				ENDIF
			ENDIF

			IF NOT IS_BUTTON_PRESSED PAD1 DPADLEFT
				IF buttonpressgymtread_flag = 2
					buttonpressgymtread_flag = 0
				ENDIF
			ENDIF
			
			IF levelbar_tread = 1
				levelbar_float = 1.0
				level_treadresitance = 9.5 //was 12	 start_tread_resistance
			ENDIF	

			IF levelbar_tread = 2
				levelbar_float = 2.0
				level_treadresitance = 8.5
			ENDIF	
			
			IF levelbar_tread = 3
				levelbar_float = 3.0
				level_treadresitance = 7.5
			ENDIF	
			
			IF levelbar_tread = 4
				levelbar_float = 4.0
				level_treadresitance = 6.5
			ENDIF	

			IF levelbar_tread = 5
				levelbar_float = 5.0
				level_treadresitance = 5.5
			ENDIF	
			
			IF levelbar_tread = 6
				levelbar_float = 6.0
				level_treadresitance = 4.5
			ENDIF		

			IF levelbar_tread = 7
				levelbar_float = 7.0
				level_treadresitance = 3.5
			ENDIF	

			IF levelbar_tread = 8
				levelbar_float = 8.0
				level_treadresitance = 2.5
			ENDIF	
			
			IF levelbar_tread = 9
				levelbar_float = 9.0
				level_treadresitance = 1.5
			ENDIF

			IF levelbar_tread = 10
				levelbar_float = 10.0
				level_treadresitance = 0.5
			ENDIF		
			

			//player has started			

			IF playerstarttread_flag = 0
				IF speed_tread > 0.0
				
					ADD_ONE_OFF_SOUND treadmillx treadmilly treadmillz SOUND_IMRAN_ARM_BOMB
				 	TIMERB = 0
					playerstarttread_flag = 1
				ENDIF
			ENDIF

			//if player falls off
			IF speed_tread <= 0.0
			AND NOT IS_CHAR_DEAD scplayer

				IF IS_CHAR_PLAYING_ANIM	scplayer gym_tread_walk
					TASK_PLAY_ANIM scplayer gym_walk_falloff GYMNASIUM 100.0 FALSE FALSE FALSE FALSE -1
				ENDIF

				IF IS_CHAR_PLAYING_ANIM	scplayer gym_tread_jog
					TASK_PLAY_ANIM scplayer gym_jog_falloff GYMNASIUM 100.0 FALSE FALSE FALSE FALSE -1
				ENDIF

				IF IS_CHAR_PLAYING_ANIM	scplayer gym_tread_sprint
				    TASK_PLAY_ANIM scplayer gym_tread_falloff GYMNASIUM 100.0 FALSE	FALSE FALSE FALSE -1
				ENDIF
				
				GET_FLOAT_STAT FAT fatstat_gym
				IF fatstat_gym <= 0.0
					SET_FLOAT_STAT FAT 0.0
				ENDIF
			 				
				starttreadmill_flag = 0
				incline_tread_flag = 0
				inclinebutton_flag = 0
				reptread_flag = 0
				buttonpressgymtread_flag = 0
				playerstarttread_flag = 0
				playwalkanim_flag = 0//0
				playjoganim_flag = 0//0
				playsprintanim_flag = 0//0
				finishedtread_flag = 0
				incline_leveltread = 1.0
				levelbar_tread = 0
				incline_tread = 1
				playerexercising_flag = 0
				distanceon_tread = 0
				distanceon_treadfloat = 0.0
				distancetoadd_tread = 0.0

				are_anims_loaded = 0
				  
				CLEAR_ONSCREEN_COUNTER speed_treadbar
				CLEAR_ONSCREEN_COUNTER levelbar_tread
				CLEAR_ONSCREEN_COUNTER distanceon_tread

				SET_PLAYER_CONTROL player1 ON
				RESTORE_CAMERA_JUMPCUT

				finishedtread_flag = 1

			   	SET_CHAR_COLLISION scplayer TRUE

				REMOVE_ANIMATION GYMNASIUM
				CLEAR_MISSION_AUDIO 4

				DELETE_MENU main_menu_tread
				
				CLEAR_HELP
			ENDIF

		IF starttreadmill_flag = 1

			//play animations
			IF playwalkanim_flag = 0
			AND NOT IS_CHAR_DEAD scplayer

				IF levelbar_tread > 0
				AND levelbar_tread < 3
					TASK_PLAY_ANIM scplayer gym_tread_walk GYMNASIUM 100.0 TRUE FALSE FALSE TRUE -1 
					playwalkanim_flag = 1
					playjoganim_flag = 0
					playsprintanim_flag = 0
				ENDIF
			ENDIF
			
			IF playjoganim_flag = 0
			AND NOT IS_CHAR_DEAD scplayer

				IF levelbar_tread > 2
				AND levelbar_tread < 7
					TASK_PLAY_ANIM scplayer gym_tread_jog GYMNASIUM 100.0 TRUE FALSE FALSE TRUE -1
					playwalkanim_flag = 0
					playsprintanim_flag = 0
					playjoganim_flag = 1
				ENDIF
			ENDIF
			
			IF playsprintanim_flag = 0
			AND NOT IS_CHAR_DEAD scplayer

				IF levelbar_tread > 6
				AND levelbar_tread < 11
					TASK_PLAY_ANIM scplayer gym_tread_sprint GYMNASIUM 100.0 TRUE FALSE FALSE TRUE -1
					playwalkanim_flag = 0
					playjoganim_flag = 0
					playsprintanim_flag = 1
				ENDIF
			ENDIF
        ENDIF

		    //Animation speeds
			
			IF speed_tread >= 0.0
				IF speed_tread <= 10.0
					animspeed_tread = 0.9
				ENDIF
			ENDIF

			IF speed_tread >= 11.0 
				IF speed_tread <= 20.0
					animspeed_tread = 0.97
				ENDIF
			ENDIF

			IF speed_tread >= 21.0
				IF speed_tread <= 30.0
					animspeed_tread = 1.04
				ENDIF
			ENDIF
			
			IF speed_tread >= 31.0
				IF speed_tread <= 40.0
					animspeed_tread = 1.11
				ENDIF
			ENDIF			

			IF speed_tread >= 41.0
				IF speed_tread <= 50.0
					animspeed_tread = 1.18
				ENDIF
			ENDIF

			IF speed_tread >= 51.0
				IF speed_tread <= 60.0
					animspeed_tread = 1.25
				ENDIF
			ENDIF

			IF speed_tread >= 61.0
				IF speed_tread <= 70.0
					animspeed_tread = 1.32
				ENDIF
			ENDIF
			
			IF speed_tread >= 71.0
				IF speed_tread <= 80.0
					animspeed_tread = 1.39
		 		ENDIF
			ENDIF			

			IF speed_tread >= 81.0
				IF speed_tread <= 90.0
					animspeed_tread = 1.46
				ENDIF
			ENDIF

			IF speed_tread >= 91.0
				IF speed_tread <= 100.0
					animspeed_tread = 1.53
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD scplayer

				IF IS_CHAR_PLAYING_ANIM	scplayer gym_tread_walk
					SET_CHAR_ANIM_SPEED scplayer gym_tread_walk	animspeed_tread
				ENDIF

				IF IS_CHAR_PLAYING_ANIM	scplayer gym_tread_jog
					SET_CHAR_ANIM_SPEED scplayer gym_tread_jog animspeed_tread
				ENDIF

				IF IS_CHAR_PLAYING_ANIM	scplayer gym_tread_sprint
					SET_CHAR_ANIM_SPEED scplayer gym_tread_sprint animspeed_tread
				ENDIF
			ENDIF
			
			// Calcualte distances
			IF DOES_OBJECT_EXIST exercise_tread
					
				IF HAS_MISSION_AUDIO_LOADED 4
					IF speed_tread > 10.0

						IF tread_start_sound = 0
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT exercise_tread SOUND_GYM_RUNNING_MACHINE_START 
							tread_start_sound = 1
						ENDIF

					ELSE

						IF tread_start_sound = 1
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT exercise_tread SOUND_GYM_RUNNING_MACHINE_STOP
							tread_start_sound = 0
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF TIMERB > 250 

				TIMERB -= 250
							
				distancetoadd_tread = speed_tread //distancetoadd_tread =# speed_tread
				distancetoadd_tread = distancetoadd_tread * levelbar_float				
				distancetoadd_tread = distancetoadd_tread / 80.0 //was 10.0

				//stat calculation stuff
				stattread_temp =# levelbar_tread
				stattread_temp /= 10.0
				stattread_temp += 1.0
				stattread_temp *= distancetoadd_tread 

				stattreadstamina_ctr += stattread_temp //statbike_temp = what level your on * the distance its gone in that time

				IF stattreadstamina_ctr >= 170.0 //250.0//1000.0 
				 
					stattreadstamina_ctr -= 170.0 //250.0//1000.0

					GET_FLOAT_STAT FAT fatstat_gym

					IF fatstat_gym >= 1.0
						DECREMENT_FLOAT_STAT FAT 8.0
					ENDIF

			       	IF tread_show_stat = 3
                     	tread_show_stat = 0
						INCREMENT_FLOAT_STAT STAMINA 4.0
						gym_day_fitness = gym_day_fitness + 4.0

			       	ELSE
			       		INCREMENT_FLOAT_STAT_NO_MESSAGE STAMINA 4.0
						gym_day_fitness = gym_day_fitness + 4.0
				   		tread_show_stat++
			       	ENDIF
				ENDIF
					
				IF gym_day_fitness > 200.0
					GET_CURRENT_DATE gym_final_day gym_final_month
					PRINT_NOW ( GYM1_1B ) 4000 1 // You have reached your limit for today come back tommorow!
				ENDIF

				incline_treadfloat =# incline_tread
				stattread_temp *= incline_treadfloat

				distanceon_treadfloat += distancetoadd_tread
				distanceon_tread =# distanceon_treadfloat

			ENDIF    

			IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			OR gym_day_fitness > 200.0

				IF gym_day_fitness > 200.0000
					gym_day_fitness = 0.0
				ENDIF

				IF NOT IS_CHAR_DEAD scplayer
					
					TASK_PLAY_ANIM scplayer gym_tread_getoff GYMNASIUM 100.0 FALSE FALSE FALSE FALSE -1

					tread_quit_back:

					SHAKE_PAD PAD1 1 1

					LVAR_INT tread_longest

					INCREMENT_INT_STAT DIST_TREADMILL distanceon_tread

					tread_longest = TIMERA

					tread_longest = tread_longest / 1000

					INCREMENT_INT_STAT LONGEST_TREADMILL_TIME tread_longest

					CLEAR_HELP

					DELETE_MENU main_menu_tread

					starttreadmill_flag = 0
					incline_tread_flag = 0
					inclinebutton_flag = 0
					reptread_flag = 0
					buttonpressgymtread_flag = 0
					playerstarttread_flag = 0
					playwalkanim_flag = 0
					playjoganim_flag = 0
					playsprintanim_flag = 0
					finishedtread_flag = 0
					incline_leveltread = 1.0
					levelbar_tread = 1
					incline_tread = 1
					playerexercising_flag = 0
					distanceon_tread = 0
					distanceon_treadfloat = 0.0
					distancetoadd_tread = 0.0
					are_anims_loaded = 0

					CLEAR_ONSCREEN_COUNTER speed_treadbar
					CLEAR_ONSCREEN_COUNTER levelbar_tread
					CLEAR_ONSCREEN_COUNTER distanceon_tread

					IF NOT IS_CHAR_DEAD scplayer
	                    SET_PLAYER_CONTROL player1 ON
						SET_CHAR_COLLISION scplayer TRUE
					ENDIF

					REMOVE_ANIMATION GYMNASIUM
					CLEAR_MISSION_AUDIO 4
					RESTORE_CAMERA_JUMPCUT
					finishedtread_flag = 1

					GET_FLOAT_STAT BODY_MUSCLE upperbodymusclestat_gym
					IF upperbodymusclestat_gym >= 1000.0
						SET_FLOAT_STAT BODY_MUSCLE 1000.0
					ENDIF
					GET_FLOAT_STAT FAT fatstat_gym
					IF fatstat_gym <= 0.0
						SET_FLOAT_STAT FAT 0.0
					ENDIF

					WHILE IS_BUTTON_PRESSED PAD1 TRIANGLE
						WAIT 0
					ENDWHILE
				ENDIF
					
			ENDIF

		tread_TIMERC ++

		tread_shake_speed = speed_treadbar - 100

		IF tread_shake_speed > -40 
			tread_shake_speed = -40
		ENDIF

		IF tread_TIMERC >= tread_shake_speed
			tread_TIMERC = 0
			speed_treadbar = speed_treadbar * 12
			speed_treadbar = speed_treadbar / 10
			SHAKE_PAD PAD1 100 speed_treadbar
		ENDIF

		speed_treadbar =# speed_tread

		ENDIF     
		 
    ENDIF
ENDIF

GOTO gym_tread_loop

}

MISSION_END

