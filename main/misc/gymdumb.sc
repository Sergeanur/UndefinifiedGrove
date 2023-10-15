MISSION_START

// *****************************************************************************************
// ************************************* Gym Objects ***************************************
// *****************************************************************************************
// ************************************* Paul Davis ****************************************
// *****************************************************************************************

VAR_INT main_menu_bell sub_menu_bell

{
exercise_dumb_bell:

SCRIPT_NAME GYMDUMB

LVAR_INT exercise_dumb_bell

// SEPERATE
LVAR_FLOAT dumbell_weights_heading
LVAR_FLOAT dumb_off_x
LVAR_FLOAT dumb_off_y

//////////	dumbell	variables
LVAR_FLOAT dumbellx
LVAR_FLOAT dumbelly
LVAR_FLOAT dumbellz
LVAR_FLOAT dumbellcamx
LVAR_FLOAT dumbellcamy
LVAR_FLOAT dumbellcamz
LVAR_FLOAT dumbell_sin_y
LVAR_FLOAT dumbell_cos_x
LVAR_FLOAT dumbell_heading
LVAR_FLOAT dumbell_degrees
LVAR_FLOAT dumbellcamlookx
LVAR_FLOAT dumbellcamlooky
LVAR_FLOAT dumbellcamlookz
VAR_INT dumbell_txt
VAR_FLOAT weightdb
VAR_INT reps_dumbell
VAR_INT weight_dumbell
VAR_FLOAT dumbell_resistance
VAR_FLOAT powerdumbell
VAR_FLOAT reps_dumbellfloat
VAR_FLOAT incmusclestatdb
//////////	dumbell	variables


// dumbell specific flags

LVAR_INT startdumbell_flag
LVAR_INT buttonpressdumbell_flag
LVAR_INT selectweightdumbell_flag
LVAR_INT repdumbell_flag
LVAR_INT finisheddumbell_flag
LVAR_INT dumbellfirsttime_flag
	   
LVAR_INT flag
LVAR_INT dumbell_lagym
LVAR_INT dbell_1
LVAR_INT dbell_2

LVAR_INT dumb_inside

VAR_INT dumb_has_KD

VAR_INT	temp_dumb_weight
VAR_INT	dumb_weight

VAR_INT dumb_front_back

VAR_FLOAT dumbell_player_heading dumbell_player_heading1

VAR_FLOAT dumbellx1
VAR_FLOAT dumbelly1

VAR_FLOAT dumbellcamx1 dumbellcamy1 temp_muscle_stat_dumb 

VAR_INT dumb_sfx dumb_show_stat
  
weight_dumbell = 1
reps_dumbell = 0
dumb_inside = 0
dumb_has_KD = 0

temp_muscle_stat_dumb = 0.0

TIMERA = 0

TIMERB = 0

	flag = 0

	IF flag = 1

	    CREATE_OBJECT_NO_OFFSET beachtowel04 0.0 0.0 0.0 exercise_dumb_bell

	ENDIF

	IF DOES_OBJECT_EXIST exercise_dumb_bell

		GET_OBJECT_COORDINATES exercise_dumb_bell dumbellx dumbelly dumbellz
			
		GET_OBJECT_HEADING exercise_dumb_bell dumbell_heading

	ENDIF

	IF NOT DOES_OBJECT_EXIST exercise_dumb_bell
	
		TERMINATE_THIS_SCRIPT

	ENDIF

   	dumb_off_x = dumbellx
   	dumb_off_y = dumbelly

	dumbellx1 = dumbellx
	dumbelly1 = dumbelly

	dumbellcamz = dumbellz + 1.70

	dumbell_degrees = dumbell_heading

   	dumbell_weights_heading = dumbell_heading - 90.00

   	dumbell_player_heading = dumbell_heading - 90.00

   	dumbell_player_heading1 = dumbell_heading + 90.00

	// Offset for bike marker

	SIN dumbell_degrees dumbell_sin_y
	COS dumbell_degrees dumbell_cos_x

	dumbell_sin_y = dumbell_sin_y * 0.95
	dumbell_cos_x = dumbell_cos_x * 0.95

	dumbellx = dumbellx - dumbell_cos_x
	dumbelly = dumbelly - dumbell_sin_y

	// Offset for Camera

	dumbell_sin_y = dumbell_sin_y * 3.5
	dumbell_cos_x = dumbell_cos_x * 3.5

	dumbellcamx = dumbellx + dumbell_cos_x  
	dumbellcamy = dumbelly + dumbell_sin_y	

	// Offset for bike marker

	SIN dumbell_degrees dumbell_sin_y
	COS dumbell_degrees dumbell_cos_x

	dumbell_sin_y = dumbell_sin_y * 0.95
	dumbell_cos_x = dumbell_cos_x * 0.95

	dumbellx1 = dumbellx1 + dumbell_cos_x
	dumbelly1 = dumbelly1 + dumbell_sin_y

	// Offset for Camera

	dumbell_sin_y = dumbell_sin_y * 3.5
	dumbell_cos_x = dumbell_cos_x * 3.5

	dumbellcamx1 = dumbellx1 - dumbell_cos_x  
	dumbellcamy1 = dumbelly1 - dumbell_sin_y


 	dumbell_heading = dumbell_heading + 180.0
	// Unfortunate Offset for dumbell

  	dumbell_degrees = dumbell_heading + 90.0

	SIN dumbell_degrees dumbell_sin_y
	COS dumbell_degrees dumbell_cos_x

	dumbell_sin_y = dumbell_sin_y * 0.2
	dumbell_cos_x = dumbell_cos_x * 0.2

	dumb_off_x = dumb_off_x + dumbell_cos_x
	dumb_off_y = dumb_off_y + dumbell_sin_y	 

   	CREATE_OBJECT kmb_dumbbell_R dumb_off_x dumb_off_y dumbellz dbell_1

	ATTACH_OBJECT_TO_OBJECT dbell_1 exercise_dumb_bell 0.05 0.2 0.3 0.0 90.0 90.0

   	CREATE_OBJECT kmb_dumbbell_L dumb_off_x dumb_off_y dumbellz dbell_2

	ATTACH_OBJECT_TO_OBJECT dbell_2 exercise_dumb_bell 0.05 -0.2 0.3 0.0 90.0 90.0

	dumbellz = dumbellz + 0.1

levelbar_bike = 1

are_anims_loaded = 0

ex_dumbell_loop:

WAIT 0

	IF NOT DOES_OBJECT_EXIST exercise_dumb_bell

		DELETE_OBJECT dbell_1

		DELETE_OBJECT dbell_2

		TERMINATE_THIS_SCRIPT

	ENDIF

    IF NOT IS_OBJECT_WITHIN_BRAIN_ACTIVATION_RANGE exercise_dumb_bell

		DELETE_OBJECT dbell_1

		DELETE_OBJECT dbell_2

		TERMINATE_THIS_SCRIPT

	ENDIF

IF IS_PLAYER_PLAYING player1
 //locate around the dumbell area - locate around the dumbell area - locate around the dumbell area - 
IF playerexercising_flag = 0

	IF startdumbell_flag = 0
	AND NOT IS_PLAYER_USING_JETPACK player1

		IF finisheddumbell_flag = 0

			IF LOCATE_CHAR_ON_FOOT_3D scplayer dumbellx dumbelly dumbellz 1.2 1.2 4.0 FALSE
			OR LOCATE_CHAR_ON_FOOT_3D scplayer dumbellx1 dumbelly1 dumbellz 1.2 1.2 4.0 FALSE

				IF LOCATE_CHAR_ON_FOOT_3D scplayer dumbellx dumbelly dumbellz 1.2 1.2 4.0 FALSE
					dumb_front_back = 0
				ELSE
					dumb_front_back = 1
				ENDIF

				IF dumb_inside = 0
					PRINT_HELP_FOREVER ( GYM1_85 )
					dumb_inside = 1
				ENDIF
								
				IF IS_BUTTON_PRESSED PAD1 TRIANGLE

					IF flag_player_on_mission = 0
					OR gym_is_running = 1

						GET_CURRENT_DATE gym_day gym_month

						IF gym_day > gym_final_day
						OR gym_month > gym_final_month
							
							startdumbell_flag = 1
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

				IF dumb_inside = 1

					CLEAR_HELP

					dumb_inside = 0

				ENDIF

			ENDIF

		ELSE
			IF NOT LOCATE_CHAR_ON_FOOT_2D scplayer dumbellx dumbelly 2.0 2.0 FALSE
			OR NOT LOCATE_CHAR_ON_FOOT_2D scplayer dumbellx1 dumbelly1 2.0 2.0 FALSE

				finisheddumbell_flag = 0

			ENDIF

		ENDIF
	ENDIF
ENDIF
										   

//////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////// DUMBELLS //////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////

IF startdumbell_flag = 1

	IF are_anims_loaded = 0
		
		REQUEST_ANIMATION FREEWEIGHTS

		LOAD_MISSION_TEXT GYM

		WHILE NOT HAS_ANIMATION_LOADED FREEWEIGHTS

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

		IF dumb_front_back = 0

			SET_PLAYER_CONTROL player1 OFF
			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer dumbellx dumbelly dumbellz
	 
		 	SET_FIXED_CAMERA_POSITION dumbellcamx dumbellcamy dumbellcamz 0.0 0.0 0.0
		 	SET_CHAR_HEADING scplayer dumbell_player_heading

		ELSE 

			SET_PLAYER_CONTROL player1 OFF
			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer dumbellx1 dumbelly1 dumbellz
	 
		 	SET_FIXED_CAMERA_POSITION dumbellcamx1 dumbellcamy1 dumbellcamz 0.0 0.0 0.0
		 	SET_CHAR_HEADING scplayer dumbell_player_heading1

		ENDIF

		POINT_CAMERA_AT_CHAR scplayer FIXED JUMP_CUT

		playerexercising_flag = 2
	
		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

	ENDIF

	//player help text
	IF playerexercising_flag = 2
	AND NOT IS_CHAR_DEAD scplayer

		PRINT_HELP_FOREVER GYM1_71
	
		// Create the menu.
		
		IF current_Language = 4
		OR current_Language = 2
			CREATE_MENU GYM1_G 31.0 180.0 184.0 1 TRUE TRUE FO_CENTRE main_menu_bell
			SET_MENU_COLUMN main_menu_bell 0 GYM1_72 GYM1_26 GYM1_27 GYM1_28 GYM1_29 GYM1_30 GYM1_31 GYM1_32 GYM1_33 GYM1_34 GYM1_35 DUMMY DUMMY
		ELSE
			CREATE_MENU GYM1_G 31.0 150.0 184.0 1 TRUE TRUE FO_CENTRE main_menu_bell
			SET_MENU_COLUMN main_menu_bell 0 GYM1_72 GYM1_26 GYM1_27 GYM1_28 GYM1_29 GYM1_30 GYM1_31 GYM1_32 GYM1_33 GYM1_34 GYM1_35 DUMMY DUMMY
		ENDIF
		SET_ACTIVE_MENU_ITEM main_menu_bell 0


		WHILE NOT IS_CHAR_DEAD scplayer

			WAIT 0

			gym_TIMERC ++

			IF IS_JAPANESE_VERSION

				IF IS_BUTTON_PRESSED PAD1 CROSS
				AND gym_TIMERC > 20
					CLEAR_HELP
					DELETE_MENU main_menu_bell
					GOTO bell_quit_back
				ENDIF 

				IF IS_BUTTON_PRESSED PAD1 CIRCLE

					CLEAR_HELP

					GET_MENU_ITEM_SELECTED main_menu_bell weight_dumbell

					DELETE_MENU main_menu_bell

					GOTO bell_out_of_loop
				ENDIF 

			ELSE

				IF IS_BUTTON_PRESSED PAD1 TRIANGLE
				AND gym_TIMERC > 20
					CLEAR_HELP
					DELETE_MENU main_menu_bell
					GOTO bell_quit_back
				ENDIF 

				IF IS_BUTTON_PRESSED PAD1 CROSS

					CLEAR_HELP

					GET_MENU_ITEM_SELECTED main_menu_bell weight_dumbell

					DELETE_MENU main_menu_bell

					GOTO bell_out_of_loop
				ENDIF 

			ENDIF 

		ENDWHILE

		bell_out_of_loop:

	 	selectweightdumbell_flag = 1  //added for fix 
        buttonpressdumbell_flag = 1 //added for fix

		playerexercising_flag = 3

	ENDIF

	// Player chooses weights
	IF playerexercising_flag = 3
	AND NOT IS_CHAR_DEAD scplayer

		//text
		IF weight_dumbell = 0
			weightdb = 1.0 // 0.7
			incmusclestatdb = 5.0
		ENDIF

		IF weight_dumbell = 1
			weightdb = 2.0 // 1.4
			incmusclestatdb = 5.2
		ENDIF

		IF weight_dumbell = 2
			weightdb = 3.0 // 2.1
			incmusclestatdb = 5.3
		ENDIF

		IF weight_dumbell = 3
			weightdb = 4.0 // 2.8 
			incmusclestatdb = 5.5
		ENDIF

		IF weight_dumbell = 4
			weightdb = 5.0 // 3.5
			incmusclestatdb = 6.0
		ENDIF

		IF weight_dumbell = 5
			weightdb = 6.0 // 4.2
			incmusclestatdb = 6.5
		ENDIF

		IF weight_dumbell = 6
			weightdb = 7.0 // 4.9			
			incmusclestatdb = 7.0
		ENDIF

		IF weight_dumbell = 7
			weightdb = 8.0 // 5.6
			incmusclestatdb = 7.5
		ENDIF

		IF weight_dumbell = 8
			weightdb = 9.0 // 6.3
			incmusclestatdb = 8.0
		ENDIF

		IF weight_dumbell = 9
			weightdb = 10.0 // 7.0
			incmusclestatdb = 8.5
		ENDIF 
					 
		IF NOT IS_CHAR_DEAD scplayer
			TASK_PLAY_ANIM scplayer gym_free_pickup FREEWEIGHTS 4.0 FALSE FALSE FALSE TRUE -1
		ENDIF

		WAIT 0

		IF NOT IS_CHAR_DEAD scplayer
		
			IF IS_CHAR_PLAYING_ANIM scplayer gym_free_pickup
			AND NOT IS_CHAR_DEAD scplayer

				GET_CHAR_ANIM_CURRENT_TIME scplayer gym_free_pickup animframe_freepickup

				WHILE NOT animframe_freepickup > 0.7789

					WAIT 0

					IF NOT IS_CHAR_DEAD scplayer
						GET_CHAR_ANIM_CURRENT_TIME scplayer gym_free_pickup animframe_freepickup
					ENDIF

				ENDWHILE

				IF NOT IS_CHAR_DEAD scplayer
													   
			    	//DELETE_OBJECT dumbell_lagym

					WAIT 0

					IF NOT IS_CHAR_DEAD scplayer

				    //	CREATE_OBJECT kmb_dumbbell_R dumb_off_x dumb_off_y dumbellz dbell_1
				    		  
			    	//	CREATE_OBJECT kmb_dumbbell_L dumb_off_x dumb_off_y dumbellz dbell_2

				    	TASK_PICK_UP_OBJECT scplayer dbell_1 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL -1

				    	TASK_PICK_UP_SECOND_OBJECT scplayer dbell_2  0.0 0.0 0.0 PED_HANDL HOLD_ORIENTATE_BONE_FULL NULL NULL -1

					ENDIF

				ENDIF

				playerexercising_flag = 4

			   	TIMERA = 0

			ENDIF

	   	ENDIF
		
	ENDIF	
					   
	//do the reps
	IF	playerexercising_flag = 4
	AND NOT IS_CHAR_DEAD scplayer

		IF IS_CHAR_PLAYING_ANIM scplayer gym_free_pickup
			GET_CHAR_ANIM_CURRENT_TIME scplayer gym_free_pickup animframe
			IF animframe = 1.0

				IF dumbell_txt = 0
					PRINT_HELP_FOREVER GYM1_36 
					dumbell_txt = 1
				ENDIF
				
//				/////////////////////////////////////////////////////////////////////////////////////////////
//				TASK_PICK_UP_OBJECT scplayer dumbell_lagym 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_PEDHEADING -1
//				/////////////////////////////////////////////////////////////////////////////////////////////

				GET_FLOAT_STAT BODY_MUSCLE upperbodymusclestat_gym
				upperbodymusclestat_gym = upperbodymusclestat_gym / 30.0

				IF upperbodymusclestat_gym < 0.0
					upperbodymusclestat_gym = 0.0
				ENDIF

				dumbell_resistance = upperbodymusclestat_gym + 30.0 // 5

			 	IF dumbell_resistance > 75.0
					dumbell_resistance = 75.0
				ENDIF

				reps_dumbellfloat =# reps_dumbell
				animationtobeplayed = dumbell_resistance - weightdb
				//animationtobeplayed = animationtobeplayed - reps_dumbellfloat

				IF animationtobeplayed <= 18.0
					TASK_PLAY_ANIM scplayer Gym_free_B FREEWEIGHTS 8.0 FALSE FALSE FALSE TRUE -1
				ENDIF

				IF animationtobeplayed > 18.0
					IF animationtobeplayed <= 24.0
						TASK_PLAY_ANIM scplayer Gym_free_A FREEWEIGHTS 8.0 FALSE FALSE FALSE TRUE -1
					ENDIF
				ENDIF

				IF animationtobeplayed > 24.0
					TASK_PLAY_ANIM scplayer Gym_free_up_smooth FREEWEIGHTS 8.0 FALSE FALSE FALSE TRUE -1
				ENDIF

				
				DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING powerbar COUNTER_DISPLAY_BAR        1 GYM1_1  // Weight
				DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING reps_dumbell COUNTER_DISPLAY_NUMBER 2 GYM1_2	// Reps
				
				animstate_flag = 1 //player starts playing pushing up animation

				playerexercising_flag = 5

			ENDIF
		ENDIF
	ENDIF
	
	//bar moving
	IF playerexercising_flag = 5
	AND NOT IS_CHAR_DEAD scplayer

		IF TIMERA > 4000
		AND dumbell_txt = 1
			CLEAR_HELP
			PRINT_HELP_FOREVER GYM1_25
			dumbell_txt = 2
		ENDIF

		IF TIMERA > 8000
		AND dumbell_txt = 2
			CLEAR_HELP
			dumbell_txt = 101
		ENDIF
		
		///////////////stats that effect the player
		GET_FLOAT_STAT BODY_MUSCLE upperbodymusclestat_gym
		upperbodymusclestat_gym = upperbodymusclestat_gym / 30.0

		IF upperbodymusclestat_gym < 0.0
			upperbodymusclestat_gym = 0.0
		ENDIF

		dumbell_resistance = upperbodymusclestat_gym + 20.0 // 5
	   	
		IF dumbell_resistance > 75.0
			dumbell_resistance = 75.0
		ENDIF

		IF animstate_flag = 1
		OR animstate_flag = 3
			powerdumbell = powerdumbell - weightdb
		ENDIF

		IF animstate_flag = 4 //come down a lot quicker once reached top
			powerdumbell = powerdumbell - 4.0
		ENDIF

		//when button is not pressed reset
		IF NOT IS_BUTTON_PRESSED PAD1 CIRCLE
			IF repdumbell_flag = 3
				repdumbell_flag = 0
			ENDIF
		ENDIF

		//when button is not pressed reset
		IF NOT IS_BUTTON_PRESSED PAD1 CROSS
			IF repdumbell_flag = 1
				repdumbell_flag = 2
			ENDIF
		ENDIF
		
	    IF animstate_flag = 1
	    OR animstate_flag = 3
			//add value when button is pressed

			IF IS_BUTTON_PRESSED PAD1 CROSS
			AND NOT IS_BUTTON_PRESSED PAD1 CIRCLE
				IF repdumbell_flag = 0
					powerdumbell = powerdumbell + dumbell_resistance
					repdumbell_flag = 1
				ENDIF
			ENDIF

			//next button
			IF IS_BUTTON_PRESSED PAD1 CIRCLE
			AND NOT IS_BUTTON_PRESSED PAD1 CROSS
				IF repdumbell_flag = 2
					powerdumbell = powerdumbell + dumbell_resistance
					repdumbell_flag = 3
				ENDIF
			ENDIF
 		ENDIF

		IF powerdumbell < 0.0
			powerdumbell = 0.0
		ENDIF
		
		IF powerdumbell >= 100.0
			powerdumbell = 100.0
			IF animstate_flag = 1
				animstate_flag = 2
			ENDIF
		ENDIF

		IF powerdumbell > 25.0
			SET_CHAR_SAY_CONTEXT scplayer CONTEXT_GLOBAL_PAIN_CJ_STRAIN dumb_sfx
		ENDIF

		IF animstate_flag = 2
			IF animvalue = 1.0
				repdumbell_flag = 0
				animstate_flag = 3
				TIMERB = 0	//	use timerb to count how long the player has held for
			ENDIF
		ENDIF
	
		IF animstate_flag = 3
		AND NOT IS_CHAR_DEAD scplayer

			IF NOT reps_dumbell >= 999999
         	    reps_dumbell = reps_dumbell + 1
			ENDIF

			IF current_Language = 0

				SWITCH weight_dumbell

					CASE 0
						dumb_weight = 20
					BREAK
					CASE 1
						dumb_weight = 30
					BREAK
					CASE 2
						dumb_weight = 40
					BREAK
					CASE 3
						dumb_weight = 50
					BREAK
					CASE 4
						dumb_weight = 60
					BREAK
					CASE 5
						dumb_weight = 70
					BREAK
					CASE 6
						dumb_weight = 80
					BREAK
					CASE 7
						dumb_weight = 90
					BREAK
					CASE 8
						dumb_weight = 100
					BREAK
					CASE 9
						dumb_weight = 110
					BREAK

				ENDSWITCH

			ELSE

				SWITCH weight_dumbell

					CASE 0
						dumb_weight = 10
					BREAK
					CASE 1
						dumb_weight = 14
					BREAK
					CASE 2
						dumb_weight = 18
					BREAK
					CASE 3
						dumb_weight = 22
					BREAK
					CASE 4
						dumb_weight = 26
					BREAK
					CASE 5
						dumb_weight = 30
					BREAK
					CASE 6
						dumb_weight = 34
					BREAK
					CASE 7
						dumb_weight = 38
					BREAK
					CASE 8
						dumb_weight = 42
					BREAK
					CASE 9
						dumb_weight = 44
					BREAK

				ENDSWITCH

			ENDIF

			GET_INT_STAT HEAVIEST_WEIGHT_DUMBELLS temp_dumb_weight

			IF temp_dumb_weight < dumb_weight
				SET_INT_STAT HEAVIEST_WEIGHT_DUMBELLS dumb_weight
			ENDIF

			GET_FLOAT_STAT BODY_MUSCLE temp_muscle_stat_dumb

			IF temp_muscle_stat_dumb < 1000.0
				
				IF weight_dumbell = 1
					incmusclestatdb = 1.0 
				ENDIF

				IF weight_dumbell = 2
					incmusclestatdb = 1.2
				ENDIF

				IF weight_dumbell = 3
					incmusclestatdb = 1.3
				ENDIF

				IF weight_dumbell = 4
					incmusclestatdb = 1.5
				ENDIF

				IF weight_dumbell = 5
					incmusclestatdb = 2.0
				ENDIF

				IF weight_dumbell = 6
					incmusclestatdb = 2.5
				ENDIF

				IF weight_dumbell = 7
					incmusclestatdb = 3.0
				ENDIF

				IF weight_dumbell = 8
					incmusclestatdb = 4.0
				ENDIF

				IF weight_dumbell = 9
					incmusclestatdb = 5.0
				ENDIF

				IF weight_dumbell = 10
					incmusclestatdb = 6.0
				ENDIF

			ENDIF

			IF temp_muscle_stat_dumb < 800.0
				
				IF weight_dumbell = 1
					incmusclestatdb = 2.0 
				ENDIF

				IF weight_dumbell = 2
					incmusclestatdb = 2.2
				ENDIF

				IF weight_dumbell = 3
					incmusclestatdb = 2.3
				ENDIF

				IF weight_dumbell = 4
					incmusclestatdb = 2.5
				ENDIF

				IF weight_dumbell = 5
					incmusclestatdb = 3.0
				ENDIF

				IF weight_dumbell = 6
					incmusclestatdb = 3.5
				ENDIF

				IF weight_dumbell = 7
					incmusclestatdb = 4.0
				ENDIF

				IF weight_dumbell = 8   
					incmusclestatdb = 5.0
				ENDIF

				IF weight_dumbell = 9
					incmusclestatdb = 6.0
				ENDIF

				IF weight_dumbell = 10
					incmusclestatdb = 7.0
				ENDIF

			ENDIF

			IF temp_muscle_stat_dumb < 600.0
				
				IF weight_dumbell = 1
					incmusclestatdb = 3.0 
				ENDIF

				IF weight_dumbell = 2
					incmusclestatdb = 3.2
				ENDIF

				IF weight_dumbell = 3
					incmusclestatdb = 3.3
				ENDIF

				IF weight_dumbell = 4
					incmusclestatdb = 3.5
				ENDIF

				IF weight_dumbell = 5
					incmusclestatdb = 4.0
				ENDIF

				IF weight_dumbell = 6
					incmusclestatdb = 4.5
				ENDIF

				IF weight_dumbell = 7
					incmusclestatdb = 5.0
				ENDIF

				IF weight_dumbell = 8
					incmusclestatdb = 6.0
				ENDIF

				IF weight_dumbell = 9
					incmusclestatdb = 7.0
				ENDIF

				IF weight_dumbell = 10
					incmusclestatdb = 8.0
				ENDIF

			ENDIF

			IF temp_muscle_stat_dumb < 400.0
				
				IF weight_dumbell = 1
					incmusclestatdb = 4.0 
				ENDIF

				IF weight_dumbell = 2
					incmusclestatdb = 4.2
				ENDIF

				IF weight_dumbell = 3
					incmusclestatdb = 4.3
				ENDIF

				IF weight_dumbell = 4
					incmusclestatdb = 4.5
				ENDIF

				IF weight_dumbell = 5
					incmusclestatdb = 5.0
				ENDIF

				IF weight_dumbell = 6
					incmusclestatdb = 5.5
				ENDIF

				IF weight_dumbell = 7
					incmusclestatdb = 6.0
				ENDIF

				IF weight_dumbell = 8
					incmusclestatdb = 7.0
				ENDIF

				IF weight_dumbell = 9
					incmusclestatdb = 8.0
				ENDIF

				IF weight_dumbell = 10
					incmusclestatdb = 9.0
				ENDIF

			ENDIF

			IF temp_muscle_stat_dumb < 200.0
				
				IF weight_dumbell = 1
					incmusclestatdb = 5.0
				ENDIF

				IF weight_dumbell = 2
					incmusclestatdb = 5.2
				ENDIF

				IF weight_dumbell = 3
					incmusclestatdb = 5.3
				ENDIF

				IF weight_dumbell = 4
					incmusclestatdb = 5.5
				ENDIF

				IF weight_dumbell = 5
					incmusclestatdb = 6.0
				ENDIF

				IF weight_dumbell = 6
					incmusclestatdb = 6.5
				ENDIF

				IF weight_dumbell = 7
					incmusclestatdb = 7.0
				ENDIF

				IF weight_dumbell = 8
					incmusclestatdb = 7.5
				ENDIF

				IF weight_dumbell = 9
					incmusclestatdb = 8.0
				ENDIF

				IF weight_dumbell = 10
					incmusclestatdb = 8.5
				ENDIF

			ENDIF

	       	IF dumb_show_stat = 8

		   		dumb_show_stat = 0
                INCREMENT_FLOAT_STAT BODY_MUSCLE incmusclestatdb
                gym_day_fitness = gym_day_fitness + incmusclestatdb

	       	ELSE

	       		INCREMENT_FLOAT_STAT_NO_MESSAGE BODY_MUSCLE incmusclestatdb
                dumb_show_stat ++
				gym_day_fitness = gym_day_fitness + incmusclestatdb

	       	ENDIF

			IF gym_day_fitness > 200.0
				GET_CURRENT_DATE gym_final_day gym_final_month
				PRINT_NOW ( GYM1_1B ) 4000 1 // You have reached your limit for today come back tommorow!
			ENDIF

			GET_FLOAT_STAT FAT fatstat_gym
			IF fatstat_gym >= 1.0
				DECREMENT_FLOAT_STAT FAT 1.0
			ENDIF

			///////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////
			SHAKE_PAD PAD1 200 200
			///////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////
			SET_CHAR_SAY_CONTEXT scplayer CONTEXT_GLOBAL_PAIN_CJ_STRAIN_EXHALE dumb_sfx
			///////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////
			TASK_PLAY_ANIM scplayer Gym_free_down FREEWEIGHTS 8.0 FALSE FALSE FALSE TRUE -1
			///////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////

			animstate_flag = 4

		ENDIF
			
		IF animstate_flag = 4
		AND NOT IS_CHAR_DEAD scplayer
			IF powerdumbell = 0.0
				IF animvalue = 0.0
					//if 10th rep then finish
					//
					
					//pick animation depending on stats and reps
					reps_dumbellfloat =# reps_dumbell
					animationtobeplayed = dumbell_resistance - weightdb
				   //	animationtobeplayed = animationtobeplayed - reps_dumbellfloat

					IF animationtobeplayed <= 18.0
						TASK_PLAY_ANIM scplayer Gym_free_B FREEWEIGHTS 8.0 FALSE FALSE FALSE TRUE -1
					ENDIF

					IF animationtobeplayed > 18.0
						IF animationtobeplayed <= 24.0
							TASK_PLAY_ANIM scplayer Gym_free_A FREEWEIGHTS 8.0 FALSE FALSE FALSE TRUE -1
						ENDIF
					ENDIF

					IF animationtobeplayed > 24.0
						TASK_PLAY_ANIM scplayer Gym_free_up_smooth FREEWEIGHTS 8.0 FALSE FALSE FALSE TRUE -1
					ENDIF
					//
					
					animstate_flag = 1

				ENDIF
			ENDIF
		ENDIF

		//bar_animvalue =# powerdumbellbar
		bar_animvalue = powerdumbell
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
				tempfloat_gym = 1.0 - animvalue
				IF IS_CHAR_PLAYING_ANIM scplayer Gym_free_down
				   	SET_CHAR_ANIM_CURRENT_TIME scplayer Gym_free_down tempfloat_gym
				ENDIF
			ELSE
				IF IS_CHAR_PLAYING_ANIM scplayer Gym_free_A
				   	SET_CHAR_ANIM_CURRENT_TIME scplayer Gym_free_A animvalue
				ENDIF
				IF IS_CHAR_PLAYING_ANIM scplayer Gym_free_B
				   	SET_CHAR_ANIM_CURRENT_TIME scplayer Gym_free_B animvalue
				ENDIF
				IF IS_CHAR_PLAYING_ANIM scplayer Gym_free_up_smooth
				   	SET_CHAR_ANIM_CURRENT_TIME scplayer Gym_free_up_smooth animvalue
				ENDIF

			ENDIF
		ENDIF

    	powerbar =# powerdumbell

		// If player wants to quit out...
		
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
		OR gym_day_fitness > 200.0

			powerbar = 0

			WHILE IS_BUTTON_PRESSED PAD1 CROSS
				WAIT 0
			ENDWHILE

			IF NOT IS_CHAR_DEAD scplayer
				TASK_PLAY_ANIM scplayer gym_free_putdown FREEWEIGHTS 100.0 FALSE FALSE FALSE FALSE -1
			ENDIF
			
			IF gym_day_fitness > 200.0000

				gym_day_fitness = 0.0

			ENDIF
  
			playerexercising_flag = 6

		ENDIF

	ENDIF  	

	IF playerexercising_flag = 6
	AND NOT IS_CHAR_DEAD scplayer

		IF reps_dumbell >= 0

			IF IS_CHAR_PLAYING_ANIM scplayer gym_free_putdown

				GET_CHAR_ANIM_CURRENT_TIME scplayer gym_free_putdown animframe_freegetoff
				 
				WHILE NOT animframe_freegetoff > 0.30

					WAIT 0

					IF NOT IS_CHAR_DEAD scplayer

						GET_CHAR_ANIM_CURRENT_TIME scplayer gym_free_putdown animframe_freegetoff

					ENDIF

				ENDWHILE

				/////////////////////////////////////////////////////////////////////////////////////////////

				DROP_OBJECT scplayer FALSE 

				DROP_SECOND_OBJECT scplayer FALSE

				ATTACH_OBJECT_TO_OBJECT dbell_1 exercise_dumb_bell 0.0 0.2 0.3 0.0 90.0 90.0

				ATTACH_OBJECT_TO_OBJECT dbell_2 exercise_dumb_bell 0.0 -0.2 0.3 0.0 90.0 90.0

			  	/*IF DOES_OBJECT_EXIST dumbell_lagym
			  		DELETE_OBJECT dumbell_lagym
			  	ENDIF

			   	IF DOES_OBJECT_EXIST dbell_1
			   	  	DELETE_OBJECT dbell_1
			   	ENDIF

				IF DOES_OBJECT_EXIST dbell_2
				  	DELETE_OBJECT dbell_2
				ENDIF    
				*/
			 	//CREATE_OBJECT KMB_DUMBBELL2 dumb_off_x dumb_off_y dumbellz dumbell_lagym
			   //	SET_OBJECT_HEADING dumbell_lagym dumbell_weights_heading
			 	//SET_OBJECT_COLLISION dumbell_lagym FALSE
							
				/////////////////////////////////////////////////////////////////////////////////////////////

				GET_CHAR_ANIM_CURRENT_TIME scplayer gym_free_putdown animframe

				IF animframe = 1.0
				AND NOT IS_CHAR_DEAD scplayer

					//BUILD_PLAYER_MODEL player1

					IF reps_dumbell > 4

						TASK_PLAY_ANIM scplayer Gym_free_celebrate FREEWEIGHTS 8.0 FALSE FALSE FALSE FALSE -1

					ENDIF

					bell_quit_back:

					startdumbell_flag = 0
					animstate_flag = 0
					finisheddumbell_flag = 1
					weight_dumbell = 1
					reps_dumbell = 0
					powerbar = 0
					animationtobeplayed = 0.0
					playerexercising_flag = 0
					powerdumbell = 0.0
					weight_dumbell = 1
					reps_dumbell = 0
					///////////////////////////////////////////////////////////////////////////
					animframe = 0.0
					animframe_freepickup = 0.0
					///////////////////////////////////////////////////////////////////////////
					
					GET_FLOAT_STAT BODY_MUSCLE upperbodymusclestat_gym

					IF upperbodymusclestat_gym >= 1000.0
						SET_FLOAT_STAT BODY_MUSCLE 1000.0
					ENDIF

					GET_FLOAT_STAT FAT fatstat_gym

					IF fatstat_gym <= 0.0
						SET_FLOAT_STAT FAT 0.0
					ENDIF

					IF NOT IS_CHAR_DEAD scplayer

						CLEAR_ONSCREEN_COUNTER reps_dumbell 
						CLEAR_ONSCREEN_COUNTER powerbar 
						RESTORE_CAMERA_JUMPCUT
						SET_PLAYER_CONTROL player1 ON

					    SET_CHAR_COLLISION scplayer TRUE

					ENDIF
							
					REMOVE_ANIMATION FREEWEIGHTS
					CLEAR_MISSION_AUDIO 4
					DELETE_MENU main_menu_bell
                    CLEAR_HELP
					dumbell_txt = 0
					are_anims_loaded = 0

				ENDIF
			ENDIF

		ELSE
  			startdumbell_flag = 0
			animstate_flag = 0
			finisheddumbell_flag = 1
			weight_dumbell = 1
			reps_dumbell = 0
			powerbar = 0
			animationtobeplayed = 0.0
			playerexercising_flag = 0
			powerdumbell = 0.0
			weight_dumbell = 1
			reps_dumbell = 0
			///////////////////////////////////////////////////////////////////////////
			animframe = 0.0
			animframe_freepickup = 0.0
			///////////////////////////////////////////////////////////////////////////
			GET_FLOAT_STAT BODY_MUSCLE upperbodymusclestat_gym

			IF upperbodymusclestat_gym >= 1000.0
				SET_FLOAT_STAT BODY_MUSCLE 1000.0
			ENDIF

			GET_FLOAT_STAT FAT fatstat_gym

			IF fatstat_gym <= 0.0
				SET_FLOAT_STAT FAT 0.0
			ENDIF

			CLEAR_ONSCREEN_COUNTER reps_dumbell 
	  		CLEAR_ONSCREEN_COUNTER powerbar 
			RESTORE_CAMERA_JUMPCUT
		  	SET_PLAYER_CONTROL player1 ON

			SET_CHAR_COLLISION scplayer TRUE
			DELETE_MENU main_menu_bell
            CLEAR_HELP

			dumbell_txt = 0
			are_anims_loaded = 0

		ENDIF

	ENDIF
	ENDIF
ENDIF
	
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////

GOTO ex_dumbell_loop

}	

MISSION_END


