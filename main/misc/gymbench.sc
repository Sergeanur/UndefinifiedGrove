MISSION_START

// *****************************************************************************************
// ************************************* Gym Objects ***************************************
// *****************************************************************************************
// ************************************* Paul Davis ****************************************
// *****************************************************************************************

VAR_INT main_menu_bench bench_txt  

{

exercise_bench:

//////////////////////////////////////////////////////////////////////////////////
// Bench Press  //////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////

SCRIPT_NAME GYMBENC

LVAR_INT exercise_bench

//// bench variables
LVAR_FLOAT bpress_sin_y
LVAR_FLOAT bpress_cos_x
LVAR_FLOAT bpress_heading
LVAR_FLOAT bpress_degrees
LVAR_FLOAT bpressx
LVAR_FLOAT bpressy
LVAR_FLOAT bpressz
LVAR_FLOAT bweightx
LVAR_FLOAT bweighty
LVAR_FLOAT bweightz
LVAR_FLOAT bweight_heading
LVAR_FLOAT bpresscamx
LVAR_FLOAT bpresscamy
LVAR_FLOAT bpresscamz
LVAR_FLOAT bpresscamlookx
LVAR_FLOAT bpresscamlooky
LVAR_FLOAT bpresscamlookz
VAR_FLOAT weightbp
VAR_FLOAT bpress_resistance
LVAR_FLOAT powerbpress
LVAR_FLOAT reps_bpressfloat
LVAR_FLOAT incmusclestatbp
LVAR_INT weight_bpress
VAR_INT reps_bpress
LVAR_INT bench_inside
VAR_INT bench_weight
VAR_INT temp_bench_weight

VAR_INT bench_has_KD


//// bench variables

//// bench press specific flags
LVAR_INT startbpress_flag
LVAR_INT buttonpressbpress_flag
LVAR_INT selectweightbpress_flag
LVAR_INT repbpress_flag
LVAR_INT finishedbpress_flag
LVAR_INT bpressfirsttime_flag

//// bench press specific flags
LVAR_INT flag
LVAR_INT bpress_lagym

VAR_INT bench_audio bench_playing play_bench_once bench_sfx	bench_show_stat

VAR_FLOAT temp_muscle_stat 

bpress_resistance = 0.0

temp_muscle_stat = 0.0

bench_playing = 2

play_bench_once = 0

//bench press specific flags
startbpress_flag        = 0
buttonpressbpress_flag  = 0
selectweightbpress_flag = 0
repbpress_flag          = 0
finishedbpress_flag     = 0

bench_has_KD = 0

//new
animframe_bppickup   = 0.0
animframe_bpgetoff   = 0.0
animframe_freepickup = 0.0
animframe_freegetoff = 0.0

weight_bpress = 1
reps_bpress = 0

bench_txt = 0

bench_inside = 0

flag = 0

TIMERA = 0

TIMERB = 0

IF flag = 1

    CREATE_OBJECT_NO_OFFSET beachtowel01 0.0 0.0 0.0 exercise_bench

ENDIF

	IF DOES_OBJECT_EXIST exercise_bench

		GET_OBJECT_COORDINATES exercise_bench bpressx bpressy bpressz
		GET_OBJECT_COORDINATES exercise_bench bweightx bweighty bweightz
			
		GET_OBJECT_HEADING exercise_bench bweight_heading
		GET_OBJECT_HEADING exercise_bench bpress_heading

	ENDIF

	bpress_sin_y = 0.0
	bpress_cos_x = 0.0

	bpresscamx = 0.0
	bpresscamy = 0.0
	bpresscamz = 0.0

	weight_bpress = 1
	reps_bpress   = 0

	bpresscamz = bpressz + 1.70

	bpress_heading = bpress_heading - 180.0

	bpress_degrees = bpress_heading

	bpress_degrees = bpress_degrees - 270.0

	SIN bpress_degrees bpress_sin_y
	COS bpress_degrees bpress_cos_x

	// Offset for bike marker

	bpress_sin_y = bpress_sin_y * 1.0
	bpress_cos_x = bpress_cos_x * 1.0

	bpressx = bpressx + bpress_cos_x
	bpressy = bpressy + bpress_sin_y

	// Offset for Camera

	bpress_sin_y = bpress_sin_y * 3.0
	bpress_cos_x = bpress_cos_x * 3.0

	bpresscamx = bpressx + bpress_cos_x  
	bpresscamy = bpressy + bpress_sin_y

	bweight_heading = bweight_heading + 90.0

	// Offset for weight
	SIN bweight_heading bpress_sin_y
	COS bweight_heading bpress_cos_x

	bpress_sin_y = bpress_sin_y * 1.0
	bpress_cos_x = bpress_cos_x * 1.0
	
	bweightx = bweightx - bpress_cos_x
	bweighty = bweighty - bpress_sin_y

	CREATE_OBJECT KMB_BPRESS bweightx bweighty bweightz bpress_lagym

	ATTACH_OBJECT_TO_OBJECT bpress_lagym exercise_bench -0.45 0.45 0.90 90.0 180.0 270.0

	bpress_heading = bpress_heading - 180.0

	levelbar_bike = 1

	are_anims_loaded = 0

gym_bench_loop:

WAIT 0

	IF NOT DOES_OBJECT_EXIST exercise_bench

		DELETE_OBJECT bpress_lagym

		TERMINATE_THIS_SCRIPT

	ENDIF

    IF NOT IS_OBJECT_WITHIN_BRAIN_ACTIVATION_RANGE exercise_bench

		DELETE_OBJECT bpress_lagym

		TERMINATE_THIS_SCRIPT

	ENDIF


IF IS_PLAYER_PLAYING player1

//locate around the bpress area - locate around the bpress area - locate around the bpress area - 
IF playerexercising_flag = 0
AND NOT IS_CHAR_DEAD scplayer

	IF startbpress_flag = 0
	AND NOT IS_PLAYER_USING_JETPACK player1

		IF finishedbpress_flag = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer bpressx bpressy bpressz 1.2 1.2 4.0 FALSE

				IF bench_inside = 0
					PRINT_HELP_FOREVER ( GYM1_81 ) 
					bench_inside = 1
				ENDIF

				If IS_BUTTON_PRESSED PAD1 TRIANGLE

					IF flag_player_on_mission = 0
					OR gym_is_running = 1

						GET_CURRENT_DATE gym_day gym_month

						IF gym_day > gym_final_day
						OR gym_month > gym_final_month

							startbpress_flag = 1
							playerexercising_flag = 1

							SET_PLAYER_CONTROL player1 OFF

							SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer bpressx bpressy bpressz		
												
							SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

						 	SET_CHAR_HEADING scplayer bpress_heading
	
							gym_TIMERC = 0

						ELSE
							PRINT_NOW ( GYM1_1A ) 4000 1 //	You have worked out enough for today come back tommorow!
						ENDIF

					ELSE
						PRINT_NOW ( GYM1_90 ) 4000 1 // ~s~You look a bit busy, why don't you come back later.
					ENDIF

				ENDIF

			ELSE

				IF bench_inside = 1

					CLEAR_HELP

					bench_inside = 0

				ENDIF

			ENDIF

		ELSE

			IF NOT LOCATE_CHAR_ON_FOOT_2D scplayer bpressx bpressy 2.0 2.0 FALSE

				finishedbpress_flag = 0

			ENDIF

		ENDIF
	ENDIF

ENDIF

/////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////// BENCH PRESS //////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////

IF startbpress_flag = 1

	IF are_anims_loaded = 0
		
		REQUEST_ANIMATION BENCHPRESS

	  	CLEAR_MISSION_AUDIO 4

		LOAD_MISSION_AUDIO 4 SOUND_BANK_GYM

	   	WHILE NOT HAS_ANIMATION_LOADED BENCHPRESS
	   	OR NOT HAS_MISSION_AUDIO_LOADED 4

	   		WAIT 0

	   	ENDWHILE

		are_anims_loaded = 1

	ENDIF

ENDIF

IF startbpress_flag = 1
AND NOT IS_CHAR_DEAD scplayer
	
	IF playerexercising_flag = 1

		SET_CHAR_COLLISION scplayer FALSE

		SET_PLAYER_CONTROL player1 OFF

		SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer bpressx bpressy bpressz

	 	SET_FIXED_CAMERA_POSITION bpresscamx bpresscamy bpresscamz 0.0 0.0 0.0
	 	SET_CHAR_HEADING scplayer bpress_heading

		POINT_CAMERA_AT_CHAR scplayer FIXED JUMP_CUT

		playerexercising_flag = 2

	ENDIF

	//player help text
	IF playerexercising_flag = 2
	AND NOT IS_CHAR_DEAD scplayer

		PRINT_HELP_FOREVER GYM1_71
		
		// Create the menu.

		IF current_Language = 4
		OR current_Language = 2
			CREATE_MENU GYM1_E 31.0 180.0 184.0 1 TRUE TRUE FO_CENTRE main_menu_bench
			SET_MENU_COLUMN main_menu_bench 0 GYM1_72 GYM1_8 GYM1_9 GYM1_10 GYM1_11 GYM1_12 GYM1_13 GYM1_14 GYM1_15 GYM1_16 GYM1_17 DUMMY DUMMY
		ELSE
			CREATE_MENU GYM1_E 31.0 150.0 184.0 1 TRUE TRUE FO_CENTRE main_menu_bench
			SET_MENU_COLUMN main_menu_bench 0 GYM1_72 GYM1_8 GYM1_9 GYM1_10 GYM1_11 GYM1_12 GYM1_13 GYM1_14 GYM1_15 GYM1_16 GYM1_17 DUMMY DUMMY
		ENDIF
		SET_ACTIVE_MENU_ITEM main_menu_bench 0


		WHILE NOT IS_CHAR_DEAD scplayer

			WAIT 0

			gym_TIMERC ++

			IF IS_BUTTON_PRESSED PAD1 TRIANGLE  // Quit the benchpress entirely.
			AND gym_TIMERC > 20
				CLEAR_HELP
				DELETE_MENU main_menu_bench
				GOTO bench_quit_back
			ENDIF 

			IF IS_BUTTON_PRESSED PAD1 CROSS  // Start lifting weights.
				
				CLEAR_HELP
				
				GET_MENU_ITEM_SELECTED main_menu_bench weight_bpress

				weight_bpress++

				DELETE_MENU main_menu_bench


				GOTO bench_out_of_loop
		   	ENDIF 

		ENDWHILE

		bench_out_of_loop:

		//text
		IF weight_bpress = 1
			weightbp = 1.0 	      // 1.0
			incmusclestatbp = 5.0 // 0.8
		ENDIF

		IF weight_bpress = 2
			incmusclestatbp = 5.2
			weightbp = 2.0
		ENDIF

		IF weight_bpress = 3
			incmusclestatbp = 5.3
			weightbp = 3.0
		ENDIF

		IF weight_bpress = 4
			incmusclestatbp = 5.5
			weightbp = 4.0
		ENDIF

		IF weight_bpress = 5
			incmusclestatbp = 6.0
			weightbp = 5.0
		ENDIF

		IF weight_bpress = 6
			incmusclestatbp = 6.5
			weightbp = 6.0
		ENDIF

		IF weight_bpress = 7
			incmusclestatbp = 7.0
			weightbp = 7.0
		ENDIF

		IF weight_bpress = 8
			incmusclestatbp = 7.5
			weightbp = 8.0
		ENDIF

		IF weight_bpress = 9
			incmusclestatbp = 8.0
			weightbp = 9.0
		ENDIF

		IF weight_bpress = 10
			incmusclestatbp = 8.5
			weightbp = 10.0
		ENDIF

		IF NOT IS_CHAR_DEAD scplayer

			TASK_PLAY_ANIM scplayer gym_bp_geton BENCHPRESS 4.0 FALSE FALSE FALSE TRUE -1

		ENDIF

		WAIT 0

		IF NOT IS_CHAR_DEAD scplayer

			IF IS_CHAR_PLAYING_ANIM scplayer Gym_bp_geton
			AND NOT IS_CHAR_DEAD scplayer

				GET_CHAR_ANIM_CURRENT_TIME scplayer Gym_bp_geton animframe_bppickup
				WHILE NOT animframe_bppickup > 0.75
					WAIT 0
					IF NOT IS_CHAR_DEAD scplayer
						GET_CHAR_ANIM_CURRENT_TIME scplayer Gym_bp_geton animframe_bppickup
					ENDIF
				ENDWHILE

			  	IF HAS_MISSION_AUDIO_LOADED 4

			  		REPORT_MISSION_AUDIO_EVENT_AT_OBJECT exercise_bench SOUND_GYM_REST_WEIGHTS 

			  	ENDIF 

				/////////////////////////////////////////////////////////////////////////////////////////////

				FREEZE_OBJECT_POSITION bpress_lagym FALSE
				DETACH_OBJECT bpress_lagym 0.0 0.0 0.0 FALSE

				WAIT 0

				IF NOT IS_CHAR_DEAD scplayer
					TASK_PICK_UP_OBJECT scplayer bpress_lagym 0.0 0.0 -0.1 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL -1
				ENDIF
				/////////////////////////////////////////////////////////////////////////////////////////////
				playerexercising_flag = 4

			   	TIMERA = 0

			ENDIF

		ENDIF

		playerexercising_flag = 4

	ENDIF
						   
	//do the reps
	IF	playerexercising_flag = 4
	AND NOT IS_CHAR_DEAD scplayer

		IF bench_txt = 0
			CLEAR_HELP
            PRINT_HELP_FOREVER ( GYM1_37 )  
			bench_txt = 1
		ENDIF

		IF TIMERA > 6000
		AND bench_txt = 1
			CLEAR_HELP
            PRINT_HELP_FOREVER ( GYM1_25 )  
			bench_txt = 2
		ENDIF

		IF TIMERA > 9000
		AND bench_txt = 2
		
			CLEAR_HELP

		  	bench_txt = 101

		ENDIF

		IF IS_CHAR_PLAYING_ANIM scplayer Gym_bp_geton
			GET_CHAR_ANIM_CURRENT_TIME scplayer Gym_bp_geton animframe
			IF animframe = 1.0
				GOTO startbpresslabel
			ENDIF

		ELSE
			IF playernextset_flag = 1
			AND NOT IS_CHAR_DEAD scplayer

				reps_bpress = 0
                powerbar = 0

				CLEAR_ONSCREEN_COUNTER reps_bpress
				CLEAR_ONSCREEN_COUNTER powerbar
				GOTO startbpresslabel

				startbpresslabel:

				GET_FLOAT_STAT BODY_MUSCLE upperbodymusclestat_gym
				upperbodymusclestat_gym = upperbodymusclestat_gym / 30.0 // 250.0

				IF upperbodymusclestat_gym < 0.0
					upperbodymusclestat_gym = 0.0
				ENDIF

				bpress_resistance = upperbodymusclestat_gym + 30.0 //5

			 	IF bpress_resistance > 75.0
					bpress_resistance = 75.0
				ENDIF

				reps_bpressfloat =# reps_bpress
				animationtobeplayed = bpress_resistance - weightbp
				//animationtobeplayed = animationtobeplayed - reps_bpressfloat

				IF animationtobeplayed <= 18.0000

						TASK_PLAY_ANIM scplayer Gym_bp_up_B BENCHPRESS 8.0 FALSE FALSE FALSE TRUE -1

				ENDIF

				IF animationtobeplayed > 18.0000
					IF animationtobeplayed <= 24.0

							TASK_PLAY_ANIM scplayer Gym_bp_up_A BENCHPRESS 8.0 FALSE FALSE FALSE TRUE -1

					ENDIF
				ENDIF

				IF animationtobeplayed > 24.0

						TASK_PLAY_ANIM scplayer Gym_bp_up_smooth BENCHPRESS 8.0 FALSE FALSE FALSE TRUE -1

				ENDIF
				
				
				DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING powerbar    COUNTER_DISPLAY_BAR    1 GYM1_1	// Reps
				DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING reps_bpress COUNTER_DISPLAY_NUMBER 2 GYM1_2  // Weight

				animstate_flag = 1 //player starts playing pushing up animation
				playerexercising_flag = 5
			ENDIF
		ENDIF
	ENDIF

	//check which anim state the player is in
	//animstate_flag = 0 none
	//animstate_flag = 1 start pushing up
	//animstate_flag = 2 bar full, waiting for anim to finish
	//animstate_flag = 3 at top
	//animstate_flag = 4 coming back down

//	Gym_bp_up_A
//	Gym_bp_up_B
//	Gym_bp_up_smooth

	//bar moving
	IF playerexercising_flag = 5
	AND NOT IS_CHAR_DEAD scplayer

		///////////////stats that effect the player
		GET_FLOAT_STAT BODY_MUSCLE upperbodymusclestat_gym
		upperbodymusclestat_gym = upperbodymusclestat_gym / 30.0 // 175

		IF upperbodymusclestat_gym < 0.0
			upperbodymusclestat_gym = 0.0
		ENDIF

		bpress_resistance = upperbodymusclestat_gym + 20.0 // 5

	 	IF bpress_resistance > 75.0
			bpress_resistance = 75.0
		ENDIF

		IF bench_txt = 0
			CLEAR_HELP
			PRINT_HELP_FOREVER ( GYM1_37 )  
			bench_txt = 1
		ENDIF

		IF TIMERA > 6000
		AND bench_txt = 1
			CLEAR_HELP
            PRINT_HELP_FOREVER ( GYM1_25 )  
			bench_txt = 2
		ENDIF

		IF TIMERA > 9000
		AND bench_txt = 2
			CLEAR_HELP
		  	bench_txt = 101
		ENDIF

		IF animstate_flag = 1
		OR animstate_flag = 3
			powerbpress = powerbpress - weightbp
		ENDIF

		IF animstate_flag = 4 //come down a lot quicker once reached top
			powerbpress = powerbpress - 4.0
		ENDIF

		//when button is not pressed reset
		IF NOT IS_BUTTON_PRESSED PAD1 CROSS
			IF repbpress_flag = 1
				repbpress_flag = 2
			ENDIF
		ENDIF

		//when button is not pressed reset
		IF NOT IS_BUTTON_PRESSED PAD1 CIRCLE
			IF repbpress_flag = 3
				repbpress_flag = 0
			ENDIF
		ENDIF

		IF animstate_flag = 1
		OR animstate_flag = 3

		    //add value when button is pressed
			IF IS_BUTTON_PRESSED PAD1 CROSS
			AND	NOT IS_BUTTON_PRESSED PAD1 CIRCLE 
				IF repbpress_flag = 0
					powerbpress = powerbpress + bpress_resistance
					repbpress_flag = 1
				ENDIF
			ENDIF

			//next button
			IF IS_BUTTON_PRESSED PAD1 CIRCLE
			AND	NOT IS_BUTTON_PRESSED PAD1 CROSS
				IF repbpress_flag = 2
					powerbpress = powerbpress + bpress_resistance
					repbpress_flag = 3
				ENDIF
			ENDIF

		ENDIF

		IF powerbpress < 0.0
			powerbpress = 0.0
		ENDIF

		IF powerbpress > 25.0
			SET_CHAR_SAY_CONTEXT scplayer CONTEXT_GLOBAL_PAIN_CJ_STRAIN bench_sfx
		ENDIF

		IF powerbpress >= 100.0
			powerbpress = 100.0
			IF animstate_flag = 1
				animstate_flag = 2
			ENDIF
		ENDIF

		IF animstate_flag = 2
		AND NOT IS_CHAR_DEAD scplayer
			IF animvalue = 1.0
				repbpress_flag = 0
				animstate_flag = 3
				TIMERB = 0	//	use timerb to count how long the player has held for
			ENDIF
		ENDIF
	
		IF animstate_flag = 3
		AND NOT IS_CHAR_DEAD scplayer

			SWITCH weight_bpress

				CASE 1
					bench_weight = 40
				BREAK
				CASE 2
					bench_weight = 60
				BREAK
				CASE 3
					bench_weight = 80
				BREAK
				CASE 4
					bench_weight = 100
				BREAK
				CASE 5
					bench_weight = 120
				BREAK
				CASE 6
					bench_weight = 160
				BREAK
				CASE 7
					bench_weight = 200
				BREAK
				CASE 8
					bench_weight = 240
				BREAK
				CASE 9
					bench_weight = 280
				BREAK
				CASE 10
				bench_weight = 320
				BREAK

			ENDSWITCH

			GET_INT_STAT HEAVIEST_WEIGHT_BENCH_PRESS temp_bench_weight

			IF temp_bench_weight < bench_weight
				SET_INT_STAT HEAVIEST_WEIGHT_BENCH_PRESS bench_weight
			ENDIF

			///////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////
				SHAKE_PAD PAD1 200 200
			///////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////

			IF NOT reps_bpress >= 999999

				reps_bpress = reps_bpress + 1

			ENDIF

			GET_FLOAT_STAT BODY_MUSCLE temp_muscle_stat

			IF temp_muscle_stat < 1000.0
				//text
				IF weight_bpress = 1
					incmusclestatbp = 1.0 // 0.8
				ENDIF

				IF weight_bpress = 2
					incmusclestatbp = 1.2
				ENDIF

				IF weight_bpress = 3
					incmusclestatbp = 1.3
				ENDIF

				IF weight_bpress = 4
					incmusclestatbp = 1.5
				ENDIF

				IF weight_bpress = 5
					incmusclestatbp = 2.0
				ENDIF

				IF weight_bpress = 6
					incmusclestatbp = 2.5
				ENDIF

				IF weight_bpress = 7
					incmusclestatbp = 3.0
				ENDIF

				IF weight_bpress = 8
					incmusclestatbp = 4.0
				ENDIF

				IF weight_bpress = 9
					incmusclestatbp = 5.0
				ENDIF

				IF weight_bpress = 10
					incmusclestatbp = 6.0
				ENDIF

			ENDIF

			IF temp_muscle_stat < 800.0
				//text
				IF weight_bpress = 1
					incmusclestatbp = 2.0 // 0.8
				ENDIF

				IF weight_bpress = 2
					incmusclestatbp = 2.2
				ENDIF

				IF weight_bpress = 3
					incmusclestatbp = 2.3
				ENDIF

				IF weight_bpress = 4
					incmusclestatbp = 2.5
				ENDIF

				IF weight_bpress = 5
					incmusclestatbp = 3.0
				ENDIF

				IF weight_bpress = 6
					incmusclestatbp = 3.5
				ENDIF

				IF weight_bpress = 7
					incmusclestatbp = 4.0
				ENDIF

				IF weight_bpress = 8   
					incmusclestatbp = 5.0
				ENDIF

				IF weight_bpress = 9
					incmusclestatbp = 6.0
				ENDIF

				IF weight_bpress = 10
					incmusclestatbp = 7.0
				ENDIF

			ENDIF

			IF temp_muscle_stat < 600.0
				//text
				IF weight_bpress = 1
					incmusclestatbp = 3.0 // 0.8
				ENDIF

				IF weight_bpress = 2
					incmusclestatbp = 3.2
				ENDIF

				IF weight_bpress = 3
					incmusclestatbp = 3.3
				ENDIF

				IF weight_bpress = 4
					incmusclestatbp = 3.5
				ENDIF

				IF weight_bpress = 5
					incmusclestatbp = 4.0
				ENDIF

				IF weight_bpress = 6
					incmusclestatbp = 4.5
				ENDIF

				IF weight_bpress = 7
					incmusclestatbp = 5.0
				ENDIF

				IF weight_bpress = 8
					incmusclestatbp = 6.0
				ENDIF

				IF weight_bpress = 9
					incmusclestatbp = 7.0
				ENDIF

				IF weight_bpress = 10
					incmusclestatbp = 8.0
				ENDIF

			ENDIF

			IF temp_muscle_stat < 400.0
				//text
				IF weight_bpress = 1
					incmusclestatbp = 4.0 // 0.8
				ENDIF

				IF weight_bpress = 2
					incmusclestatbp = 4.2
				ENDIF

				IF weight_bpress = 3
					incmusclestatbp = 4.3
				ENDIF

				IF weight_bpress = 4
					incmusclestatbp = 4.5
				ENDIF

				IF weight_bpress = 5
					incmusclestatbp = 5.0
				ENDIF

				IF weight_bpress = 6
					incmusclestatbp = 5.5
				ENDIF

				IF weight_bpress = 7
					incmusclestatbp = 6.0
				ENDIF

				IF weight_bpress = 8
					incmusclestatbp = 7.0
				ENDIF

				IF weight_bpress = 9
					incmusclestatbp = 8.0
				ENDIF

				IF weight_bpress = 10
					incmusclestatbp = 9.0
				ENDIF

			ENDIF

			IF temp_muscle_stat < 200.0
				//text
				IF weight_bpress = 1
					incmusclestatbp = 5.0 // 0.8
				ENDIF

				IF weight_bpress = 2
					incmusclestatbp = 5.2
				ENDIF

				IF weight_bpress = 3
					incmusclestatbp = 5.3
				ENDIF

				IF weight_bpress = 4
					incmusclestatbp = 5.5
				ENDIF

				IF weight_bpress = 5
					incmusclestatbp = 6.0
				ENDIF

				IF weight_bpress = 6
					incmusclestatbp = 6.5
				ENDIF

				IF weight_bpress = 7
					incmusclestatbp = 7.0
				ENDIF

				IF weight_bpress = 8
					incmusclestatbp = 7.5
				ENDIF

				IF weight_bpress = 9
					incmusclestatbp = 8.0
				ENDIF

				IF weight_bpress = 10
					incmusclestatbp = 8.5
				ENDIF

			ENDIF

	       	IF bench_show_stat = 8

		   		bench_show_stat = 0

				INCREMENT_FLOAT_STAT BODY_MUSCLE incmusclestatbp
		
				gym_day_fitness = gym_day_fitness + incmusclestatbp

	       	ELSE

	       		INCREMENT_FLOAT_STAT_NO_MESSAGE BODY_MUSCLE incmusclestatbp

		   		bench_show_stat ++

				gym_day_fitness = gym_day_fitness + incmusclestatbp

	       	ENDIF

			IF gym_day_fitness > 200.0

				GET_CURRENT_DATE gym_final_day gym_final_month

				PRINT_NOW ( GYM1_1B ) 4000 1 // You have reached your limit for today come back tommorow!
				
			ENDIF

			GET_FLOAT_STAT FAT fatstat_gym
			IF fatstat_gym >= 1.0
				DECREMENT_FLOAT_STAT FAT 1.0
			ENDIF

			powerbar = 0

			SET_CHAR_SAY_CONTEXT scplayer CONTEXT_GLOBAL_PAIN_CJ_STRAIN_EXHALE bench_sfx

			TASK_PLAY_ANIM scplayer Gym_bp_down BENCHPRESS 8.0 FALSE FALSE FALSE TRUE -1

			animstate_flag = 4

		ENDIF
			
		IF animstate_flag = 4
		AND NOT IS_CHAR_DEAD scplayer

			IF powerbpress = 0.0
			AND NOT IS_CHAR_DEAD scplayer

				IF animvalue = 0.0
					
					//pick animation depending on stats and reps
					reps_bpressfloat =# reps_bpress
					animationtobeplayed = bpress_resistance - weightbp
					//animationtobeplayed = animationtobeplayed - reps_bpressfloat
					IF animationtobeplayed <= 18.0
					AND NOT IS_CHAR_DEAD scplayer
						TASK_PLAY_ANIM scplayer Gym_bp_up_B BENCHPRESS 8.0 FALSE FALSE FALSE TRUE -1
					ENDIF

					IF animationtobeplayed > 18.0
					AND NOT IS_CHAR_DEAD scplayer

						IF animationtobeplayed <= 24.0
							TASK_PLAY_ANIM scplayer Gym_bp_up_A BENCHPRESS 8.0 FALSE FALSE FALSE TRUE -1
						ENDIF

					ENDIF

					IF animationtobeplayed > 24.0
					AND NOT IS_CHAR_DEAD scplayer
						TASK_PLAY_ANIM scplayer Gym_bp_up_smooth BENCHPRESS 8.0 FALSE FALSE FALSE TRUE -1
					ENDIF
					animstate_flag = 1
				ENDIF
			ENDIF
		ENDIF

		//bar_animvalue =# powerbpressbar
		bar_animvalue = powerbpress
		bar_animvalue = bar_animvalue / 100.0
			
		IF animstate_flag > 0
		AND NOT IS_CHAR_DEAD scplayer 		
			//gets diff between what bars are at and what animation is at
			diffgym = bar_animvalue - animvalue
			diffgym /= 2.0

			IF diffgym < 0.05
			AND diffgym > -0.05
				animvalue = bar_animvalue
			ELSE
				animvalue += diffgym
			ENDIF
				
			IF animstate_flag = 4
			AND NOT IS_CHAR_DEAD scplayer

				tempfloat_gym = 1.0 - animvalue
				IF IS_CHAR_PLAYING_ANIM scplayer Gym_bp_down
				   	SET_CHAR_ANIM_CURRENT_TIME scplayer Gym_bp_down tempfloat_gym
				ENDIF
			ELSE
				IF IS_CHAR_PLAYING_ANIM scplayer Gym_bp_up_A
				   	SET_CHAR_ANIM_CURRENT_TIME scplayer Gym_bp_up_A animvalue
				ENDIF
				IF IS_CHAR_PLAYING_ANIM scplayer Gym_bp_up_B
				   	SET_CHAR_ANIM_CURRENT_TIME scplayer Gym_bp_up_B animvalue
				ENDIF
				IF IS_CHAR_PLAYING_ANIM scplayer Gym_bp_up_smooth
				   	SET_CHAR_ANIM_CURRENT_TIME scplayer Gym_bp_up_smooth animvalue
				ENDIF

			ENDIF
		ENDIF

    	powerbar =# powerbpress

		//If player wants to quit out

		IF IS_BUTTON_PRESSED PAD1 TRIANGLE  // Quit back to the weight selection screen.
		OR gym_day_fitness > 200.0

			IF NOT IS_CHAR_DEAD scplayer

				WHILE IS_BUTTON_PRESSED PAD1 CROSS
					WAIT 0
				ENDWHILE

				IF NOT IS_CHAR_DEAD scplayer
					TASK_PLAY_ANIM scplayer gym_bp_getoff BENCHPRESS 100.0 FALSE FALSE FALSE FALSE -1
				ENDIF

				powerbar = 0

				IF gym_day_fitness > 200.0000
               	    gym_day_fitness = 0.0
				ENDIF

				playerexercising_flag = 6

			ENDIF

		ENDIF

	ENDIF  
	
	IF playerexercising_flag = 6
	AND NOT IS_CHAR_DEAD scplayer 
		IF reps_bpress > 4
			IF IS_CHAR_PLAYING_ANIM scplayer gym_bp_getoff
			AND NOT IS_CHAR_DEAD scplayer

				GET_CHAR_ANIM_CURRENT_TIME scplayer gym_bp_getoff animframe_bpgetoff

					WHILE NOT animframe_bpgetoff > 0.26 
						WAIT 0 
						IF NOT IS_CHAR_DEAD scplayer
							GET_CHAR_ANIM_CURRENT_TIME scplayer gym_bp_getoff animframe_bpgetoff
						ENDIF
					ENDWHILE

					/////////////////////////////////////////////////////////////////////////////////////////////

				  	IF HAS_MISSION_AUDIO_LOADED 4
					AND play_bench_once = 0

				  		REPORT_MISSION_AUDIO_EVENT_AT_OBJECT exercise_bench SOUND_GYM_REST_WEIGHTS 

						play_bench_once = 1
							
					ENDIF	

					DROP_OBJECT scplayer FALSE 
					FREEZE_OBJECT_POSITION bpress_lagym TRUE

					DELETE_OBJECT bpress_lagym
					CREATE_OBJECT KMB_BPRESS bweightx bweighty bweightz bpress_lagym

					ATTACH_OBJECT_TO_OBJECT bpress_lagym exercise_bench -0.45 0.45 0.90 90.0 180.0 270.0

					/////////////////////////////////////////////////////////////////////////////////////////////

				IF NOT IS_CHAR_DEAD scplayer
					GET_CHAR_ANIM_CURRENT_TIME scplayer gym_bp_getoff animframe
				ENDIF
				IF animframe = 1.0

					TASK_PLAY_ANIM scplayer Gym_bp_celebrate BENCHPRESS 8.0 FALSE FALSE FALSE FALSE -1

					bench_quit_back:

					IF NOT IS_CHAR_DEAD scplayer

						startbpress_flag = 0
						animstate_flag = 0
						finishedbpress_flag = 1
						weight_bpress = 1
						reps_bpress = 0
						powerbar = 0
						animationtobeplayed = 0.0
						playerexercising_flag = 0
						powerbpress = 0.0
						weight_bpress = 1
						reps_bpress = 0

						GET_FLOAT_STAT BODY_MUSCLE upperbodymusclestat_gym
						IF upperbodymusclestat_gym >= 1000.0
							SET_FLOAT_STAT BODY_MUSCLE 1000.0
						ENDIF
						GET_FLOAT_STAT FAT fatstat_gym
						IF fatstat_gym <= 0.0
							SET_FLOAT_STAT FAT 0.0
						ENDIF

						///////////////////////////////////////////////////////////////////////////
						animframe_bppickup = 0.0
						animframe_bpgetoff = 0.0
						///////////////////////////////////////////////////////////////////////////

						CLEAR_ONSCREEN_COUNTER reps_bpress
						CLEAR_ONSCREEN_COUNTER powerbar
						RESTORE_CAMERA_JUMPCUT
						SET_PLAYER_CONTROL player1 ON

						SET_CHAR_COLLISION scplayer TRUE

						REMOVE_ANIMATION BENCHPRESS
						CLEAR_MISSION_AUDIO 4

						CLEAR_HELP

						DELETE_MENU main_menu_bench

						bench_txt = 0

						are_anims_loaded = 0

					ENDIF

				ENDIF
			ENDIF
		ELSE
			IF NOT IS_CHAR_DEAD scplayer
			IF IS_CHAR_PLAYING_ANIM scplayer gym_bp_getoff

				GET_CHAR_ANIM_CURRENT_TIME scplayer gym_bp_getoff animframe_bpgetoff
					WHILE NOT animframe_bpgetoff > 0.26
						WAIT 0
						IF NOT IS_CHAR_DEAD scplayer
							GET_CHAR_ANIM_CURRENT_TIME scplayer gym_bp_getoff animframe_bpgetoff
						ENDIF
					ENDWHILE
					/////////////////////////////////////////////////////////////////////////////////////////////

				  	IF HAS_MISSION_AUDIO_LOADED 4
					AND play_bench_once = 0

				  		REPORT_MISSION_AUDIO_EVENT_AT_OBJECT exercise_bench SOUND_GYM_REST_WEIGHTS 

						play_bench_once = 1
							
				  	ENDIF 

					DROP_OBJECT scplayer FALSE 
					FREEZE_OBJECT_POSITION bpress_lagym TRUE
					
					DELETE_OBJECT bpress_lagym				
					CREATE_OBJECT KMB_BPRESS bweightx bweighty bweightz bpress_lagym

					ATTACH_OBJECT_TO_OBJECT bpress_lagym exercise_bench -0.45 0.45 0.90 90.0 180.0 270.0

					/////////////////////////////////////////////////////////////////////////////////////////////

				IF NOT IS_CHAR_DEAD scplayer

					GET_CHAR_ANIM_CURRENT_TIME scplayer gym_bp_getoff animframe

				ENDIF

				IF animframe = 1.0

					animframe_bppickup = 0.0
					animframe_bpgetoff = 0.0
				
					startbpress_flag = 0
					animstate_flag = 0
					finishedbpress_flag = 1
					weight_bpress = 1
					reps_bpress = 0
					powerbar = 0
					animationtobeplayed = 0.0
					playerexercising_flag = 0
					powerbpress = 0.0
					weight_bpress = 1
					reps_bpress = 0

					CLEAR_ONSCREEN_COUNTER reps_bpress
			  		CLEAR_ONSCREEN_COUNTER powerbar
					RESTORE_CAMERA_JUMPCUT
				  	SET_PLAYER_CONTROL player1 ON

					SET_CHAR_COLLISION scplayer TRUE

					REMOVE_ANIMATION BENCHPRESS
					CLEAR_MISSION_AUDIO 4

					CLEAR_HELP

					DELETE_MENU main_menu_bench

					bench_txt = 0

					are_anims_loaded = 0

				ENDIF
			ENDIF

		ENDIF
		ENDIF

ENDIF
ENDIF	

ENDIF
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

GOTO gym_bench_loop

}


/*
VAR_INT gym_exbike
CREATE_OBJECT_NO_OFFSET cm_box 2494.1536 -1667.6479 12.3516 gym_exbike 
SET_OBJECT_HEADING gym_exbike 15.0
START_NEW_SCRIPT exercise_bike gym_exbike

VAR_INT gym_exbench
CREATE_OBJECT_NO_OFFSET beachtowel01 2486.5229 -1666.8066 12.3594 gym_exbench 
SET_OBJECT_HEADING gym_exbench 83.0163
START_NEW_SCRIPT exercise_bench gym_exbench

VAR_INT gym_extread
CREATE_OBJECT_NO_OFFSET beachtowel03 2492.3411 -1662.3772 12.3516 gym_extread 
SET_OBJECT_HEADING gym_extread 8.7289 
START_NEW_SCRIPT exercise_tread gym_extread	

VAR_INT gym_exdumb
CREATE_OBJECT_NO_OFFSET beachtowel03 2487.9500 -1670.7336 12.3594 gym_exdumb 
SET_OBJECT_HEADING gym_exdumb 65.0000
START_NEW_SCRIPT exercise_dumb_bell gym_exdumb */
																	 
/*
ALLOCATE_SCRIPT_TO_OBJECT exercise_bike cm_box 100
ALLOCATE_SCRIPT_TO_OBJECT exercise_bench beachtowel01 100
ALLOCATE_SCRIPT_TO_OBJECT exercise_tread beachtowel03 100 
ALLOCATE_SCRIPT_TO_OBJECT exercise_dumb_bell beachtowel04 100 */

MISSION_END
