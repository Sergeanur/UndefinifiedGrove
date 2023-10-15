MISSION_START

// ****************************************************************************************************
// This is the fucking bee game "Bee Be Gone". Not that you would know that from the text in this file.
// ****************************************************************************************************

GOSUB mission_start_grav

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_failed_grav
ENDIF

GOSUB mission_cleanup_grav
MISSION_END
{
 
// variables *******************************************************************************

	// States of the fucking game.

	CONST_INT PLAYING_GAME 0
	CONST_INT PLAY_SELECTED 1
	CONST_INT HI_SCORE_SELECTED 2 
	CONST_INT EXIT_SELECTED 3
	CONST_INT INACTIVE 4

	CONST_INT GAME_OVER_INIT 0
	CONST_INT GAME_OVER_WAITING 1
	CONST_INT DECIDE_HI_SCORES_SCREEN 2
	CONST_INT ENTERING_NAME 3
	CONST_INT SHOWING_HIGH_SCORES 4

	VAR_INT unk_6190[10]

// sprites

	// control
		
		LVAR_INT grav_lstickx grav_lsticky grav_rstickx grav_rsticky
		LVAR_INT grav_add grav_add_smooth 
	
	// convert integer to floating point	
		LVAR_FLOAT grav_fl_add grav_fl_add_l grav_fl_add_r grav_rotation grav_rotation_step

	// player co-ordinates	
		VAR_FLOAT grav_plyr_x grav_plyr_y 
		VAR_FLOAT grav_speed_x grav_speed_y grav_dead_x grav_dead_y

		LVAR_FLOAT grav_jump_x grav_jump_y 
	// player stuff
		LVAR_INT grav_player_alive grav_plyr grav_jump_f grav_position grav_lives  
		LVAR_INT grav_success grav_pickup  grav_score grav_temp_score grav_temp_score2 grav_hi_score
		LVAR_INT grav_button_pressed grav_level_display grav_level_up
	// front end stuff
		VAR_INT nGameState grav_ground grav_next_level
	// sprite collision sizes
		LVAR_FLOAT grav_spr_x grav_spr_y
	// movement
		LVAR_FLOAT grav_speed grav_gravity  grav_collision_w grav_collision_h
	// platforms
		LVAR_FLOAT grav_plat_x[101] grav_plat_y[101] 
		LVAR_FLOAT grav_pick_x[101] grav_pick_y[101] 
		LVAR_FLOAT grav_ground_x[101] grav_ground_y[101] 

		VAR_INT	grav_pickups grav_total_pickups grav_level grav_temp1 grav_temp2
		VAR_INT grav_pickups_display grav_buzz

		


		LVAR_FLOAT grav_dist_temp grav_dist_temp_max grav_bias_line grav_layer_y grav_origin 
		LVAR_INT grav_current_plat 

		VAR_FLOAT grav_velocity_x grav_velocity_y grav_temp_1 grav_temp_2 grav_velocity_mag_sqr grav_velocity_mag
		VAR_FLOAT grav_plyr_dead_speed grav_fire_heading
		
		VAR_INT grav_countdown grav_countdown_temp grav_countdown_secs	
		VAR_INT grav_countdown_end grav_countdown_diff grav_countdown_start grav_countdown_max
		VAR_INT grav_help_end grav_help_diff grav_help_start
		// ---- Hi Score Stuff
		VAR_TEXT_LABEL $grav_char[37] 
		VAR_INT grav_chars grav_press grav_temp
		VAR_INT grav_draw_highlight
		VAR_INT grav_time_diff grav_end_time grav_start_time
		VAR_INT grav_hi_loop
		VAR_FLOAT grav_hi_1_x[10] grav_hi_2_x[10] grav_hi_3_x[10] grav_hi_4_x[10]
		VAR_FLOAT grav_hi_1_y[10] grav_hi_2_y[10] grav_hi_3_y[10] grav_hi_4_y[10] 
		VAR_INT grav_ranking grav_score_update grav_score_check grav_destination_slot grav_source_slot
		VAR_INT grav_on_table grav_write
		VAR_INT grav_played_before grav_current grav_num_gen grav_used[30]


		VAR_FLOAT grav_screen_x grav_screen_y grav_temp_x grav_temp_y grav_screen_temp_y

// mission start ***************************************************************************
mission_start_grav:

flag_player_on_mission = 1

REGISTER_MISSION_GIVEN
SCRIPT_NAME grav

WAIT 0

LOAD_MISSION_TEXT grav
LOAD_MISSION_AUDIO 4 SOUND_BANK_BEE
WHILE NOT HAS_MISSION_AUDIO_LOADED 4
WAIT 0
ENDWHILE


FREEZE_CHAR_POSITION scplayer TRUE
SET_PLAYER_CONTROL player1 OFF
GET_CHAR_COORDINATES scplayer x y z
CLEAR_AREA x y z 50.0 0

SHUT_ALL_CHARS_UP TRUE

USE_TEXT_COMMANDS TRUE
SET_TEXT_CENTRE ON
SET_TEXT_SCALE 3.0 3.0

LOAD_TEXTURE_DICTIONARY ld_grav
LOAD_SPRITE 1 sky
LOAD_SPRITE 2 beea
LOAD_SPRITE 3 flwra
LOAD_SPRITE 4 ghost
LOAD_SPRITE 5 leaf
LOAD_SPRITE 6 flwr
LOAD_SPRITE 7 tvcorn
LOAD_SPRITE 8 tvl
LOAD_SPRITE 9 tvr
LOAD_SPRITE 10 thorn
LOAD_SPRITE 11 bee2
LOAD_SPRITE 12 bee1
LOAD_SPRITE 13 hive
LOAD_SPRITE 14 timer
LOAD_SPRITE 15 bumble
LOAD_SPRITE 16 playw
LOAD_SPRITE 17 playy
LOAD_SPRITE 18 hiscorew
LOAD_SPRITE 19 hiscorey
LOAD_SPRITE 20 exitw
LOAD_SPRITE 21 exity

// ---- Hi Score Chars

		$grav_char[0] 	= GRAV_0	// 0
		$grav_char[1] 	= GRAV_1	// 1
		$grav_char[2] 	= GRAV_2	// 2
		$grav_char[3] 	= GRAV_3	// 3
		$grav_char[4] 	= GRAV_4	// 4
					 	 
		$grav_char[5] 	= GRAV_5	// 5
		$grav_char[6] 	= GRAV_6	// 6
		$grav_char[7] 	= GRAV_7	// 7
		$grav_char[8] 	= GRAV_8	// 8
		$grav_char[9] 	= GRAV_9	// 9
					  
		$grav_char[10] 	= GRAV_A	// A
		$grav_char[11] 	= GRAV_B	// B
		$grav_char[12] 	= GRAV_C	// C
		$grav_char[13] 	= GRAV_D	// D
		$grav_char[14] 	= GRAV_E	// E
				     		
		$grav_char[15] 	= GRAV_F	// F
		$grav_char[16] 	= GRAV_G	// G
		$grav_char[17] 	= GRAV_H	// H
		$grav_char[18] 	= GRAV_I	// I
		$grav_char[19] 	= GRAV_J	// J
				 		   
		$grav_char[20] 	= GRAV_K	// K
		$grav_char[21] 	= GRAV_L	// L
		$grav_char[22] 	= GRAV_M	// M
		$grav_char[23] 	= GRAV_N	// N
		$grav_char[24] 	= GRAV_O	// O
			  		
		$grav_char[25] 	= GRAV_P	// P
		$grav_char[26] 	= GRAV_Q	// Q
		$grav_char[27] 	= GRAV_R	// R
		$grav_char[28] 	= GRAV_S	// S
		$grav_char[29] 	= GRAV_T	// T
			  		
		$grav_char[30] 	= GRAV_U	// U
		$grav_char[31] 	= GRAV_V	// V
		$grav_char[32] 	= GRAV_W	// W
		$grav_char[33] 	= GRAV_X	// X
		$grav_char[34] 	= GRAV_Y	// Y
					  	 
		$grav_char[35] 	= GRAV_Z	// Z
		$grav_char[36] 	= GRAV_PE	// .

// ---- Hi Score Coords

		grav_hi_1_x[0] = 176.0 // First letter of player name
		grav_hi_1_y[0] = 96.0 
		grav_hi_2_x[0] = 224.0 // Second letter of player name
		grav_hi_2_y[0] = 96.0
		grav_hi_3_x[0] = 272.0 // Third letter of player name 
		grav_hi_3_y[0] = 96.0
		grav_hi_4_x[0] = 464.0 // Level with Number & score
		grav_hi_4_y[0] = 96.0

		grav_hi_1_x[1] = 176.0
		grav_hi_1_y[1] = 128.0 
		grav_hi_2_x[1] = 224.0
		grav_hi_2_y[1] = 128.0
		grav_hi_3_x[1] = 272.0
		grav_hi_3_y[1] = 128.0
		grav_hi_4_x[1] = 464.0
		grav_hi_4_y[1] = 128.0

		grav_hi_1_x[2] = 176.0
		grav_hi_1_y[2] = 160.0 
		grav_hi_2_x[2] = 224.0
		grav_hi_2_y[2] = 160.0
		grav_hi_3_x[2] = 272.0
		grav_hi_3_y[2] = 160.0
		grav_hi_4_x[2] = 464.0
		grav_hi_4_y[2] = 160.0

		grav_hi_1_x[3] = 176.0
		grav_hi_1_y[3] = 192.0 
		grav_hi_2_x[3] = 224.0
		grav_hi_2_y[3] = 192.0
		grav_hi_3_x[3] = 272.0
		grav_hi_3_y[3] = 192.0
		grav_hi_4_x[3] = 464.0
		grav_hi_4_y[3] = 192.0

		grav_hi_1_x[4] = 176.0
		grav_hi_1_y[4] = 224.0 
		grav_hi_2_x[4] = 224.0
		grav_hi_2_y[4] = 224.0
		grav_hi_3_x[4] = 272.0
		grav_hi_3_y[4] = 224.0
		grav_hi_4_x[4] = 464.0
		grav_hi_4_y[4] = 224.0

		grav_hi_1_x[5] = 176.0
		grav_hi_1_y[5] = 256.0 
		grav_hi_2_x[5] = 224.0
		grav_hi_2_y[5] = 256.0
		grav_hi_3_x[5] = 272.0
		grav_hi_3_y[5] = 256.0
		grav_hi_4_x[5] = 464.0
		grav_hi_4_y[5] = 256.0

		grav_hi_1_x[6] = 176.0
		grav_hi_1_y[6] = 288.0 
		grav_hi_2_x[6] = 224.0
		grav_hi_2_y[6] = 288.0
		grav_hi_3_x[6] = 272.0
		grav_hi_3_y[6] = 288.0
		grav_hi_4_x[6] = 464.0
		grav_hi_4_y[6] = 288.0

		grav_hi_1_x[7] = 176.0
		grav_hi_1_y[7] = 320.0 
		grav_hi_2_x[7] = 224.0
		grav_hi_2_y[7] = 320.0
		grav_hi_3_x[7] = 272.0
		grav_hi_3_y[7] = 320.0
		grav_hi_4_x[7] = 464.0
		grav_hi_4_y[7] = 320.0

		grav_hi_1_x[8] = 176.0
		grav_hi_1_y[8] = 352.0 
		grav_hi_2_x[8] = 224.0
		grav_hi_2_y[8] = 352.0
		grav_hi_3_x[8] = 272.0
		grav_hi_3_y[8] = 352.0
		grav_hi_4_x[8] = 464.0
		grav_hi_4_y[8] = 352.0

		grav_hi_1_x[9] = 176.0
		grav_hi_1_y[9] = 384.0 
		grav_hi_2_x[9] = 224.0
		grav_hi_2_y[9] = 384.0
		grav_hi_3_x[9] = 272.0
		grav_hi_3_y[9] = 384.0
		grav_hi_4_x[9] = 464.0
		grav_hi_4_y[9] = 384.0

IF grav_played_before = 0
	
	$grav_hi_1[0] = $grav_char[12] 
	$grav_hi_2[0] = $grav_char[36] 
	$grav_hi_3[0] = $grav_char[36] 
	grav_hi_l[0] = 10
	grav_hi_s[0] = 1000

	grav_current = 1
	GOSUB grav_generate_default_scores
	grav_hi_l[1] = 9
	grav_hi_s[1] = 900

	grav_current = 2
	GOSUB grav_generate_default_scores
	grav_hi_l[2] = 8
	grav_hi_s[2] = 800

	grav_current = 3
	GOSUB grav_generate_default_scores
	grav_hi_l[3] = 7
	grav_hi_s[3] = 700

	grav_current = 4
	GOSUB grav_generate_default_scores
	grav_hi_l[4] = 6
	grav_hi_s[4] = 600

	grav_current = 5
	GOSUB grav_generate_default_scores
	grav_hi_l[5] = 5
	grav_hi_s[5] = 500

	grav_current = 6
	GOSUB grav_generate_default_scores
	grav_hi_l[6] = 4
	grav_hi_s[6] = 400

	grav_current = 7
	GOSUB grav_generate_default_scores
	grav_hi_l[7] = 3
	grav_hi_s[7] = 300

	grav_current = 8
	GOSUB grav_generate_default_scores
	grav_hi_l[8] = 2
	grav_hi_s[8] = 200

	grav_current = 9
	GOSUB grav_generate_default_scores
	grav_hi_l[9] = 1
	grav_hi_s[9] = 100

	grav_played_before = 1
ENDIF
grav_hi_score = grav_hi_s[0]



grav_restart:

		grav_next_level = 1
		grav_score = 0
		grav_level = 0
		nGameState = PLAY_SELECTED
		grav_lives = 2
		grav_player_alive = 1
		grav_rotation = 180.0
		grav_rotation_step = 2.0
		grav_screen_y = 3648.0
		grav_plyr_dead_speed = 2.5
		grav_countdown_max = 120000
		grav_pickups=0
		grav_speed_x=0.0
		grav_speed_y=0.0
		grav_temp_score2= 0
		
		GET_GAME_TIMER grav_countdown_start
		grav_ground = 0




// main loop *******************************************************************************

mission_grav_loop:
    
	WAIT 0

	DRAW_SPRITE 1 128.0 128.0 256.0 256.0 150 150 150 255
	DRAW_SPRITE 1 128.0 384.0 256.0 256.0 150 150 150 255
	DRAW_SPRITE 1 384.0 128.0 256.0 256.0 150 150 150 255
	DRAW_SPRITE 1 384.0 384.0 256.0 256.0 150 150 150 255
	DRAW_SPRITE 1 640.0 128.0 256.0 256.0 150 150 150 255
	DRAW_SPRITE 1 640.0 384.0 256.0 256.0 150 150 150 255

    DO_FADE 0 FADE_IN

    GET_POSITION_OF_ANALOGUE_STICKS PAD1 grav_lstickx grav_lsticky grav_rstickx grav_rsticky
    
	// front end
    
	grav_fe:
    
		    
	IF grav_button_pressed = 1  // Detect the release of the input for the main menu. 
		IF NOT IS_BUTTON_PRESSED PAD1 DPADUP
		AND NOT IS_BUTTON_PRESSED PAD1 DPADDOWN
		AND grav_lsticky = 0
			grav_button_pressed = 0
		ENDIF
    ENDIF

	
	// Render the main menu.

	IF nGameState = PLAY_SELECTED
        
		DRAW_SPRITE 15 320.0 196.0 256.0 128.0 150 150 150 255  // Ready to bumble logo.

		DRAW_SPRITE 17 320.0 312.0 64.0 32.0 150 150 150 255  // Play is selected, others unselected.
        DRAW_SPRITE 18 320.0 344.0 128.0 32.0 150 150 150 255
        DRAW_SPRITE 20 320.0 376.0 64.0 32.0 150 150 150 255
        
		IF IS_JAPANESE_VERSION
			IF IS_BUTTON_PRESSED PAD1 CIRCLE

				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_ACCEPT
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_TRACK_START
				GET_GAME_TIMER grav_help_start
				nGameState = PLAYING_GAME
				timera = 0
			ENDIF		
        ELSE
			IF IS_BUTTON_PRESSED PAD1 CROSS

				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_ACCEPT
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_TRACK_START
				GET_GAME_TIMER grav_help_start
				nGameState = PLAYING_GAME
				timera = 0
			ENDIF
		ENDIF
    ENDIF

    IF nGameState = HI_SCORE_SELECTED
	   
	    DRAW_SPRITE 15 320.0 196.0 256.0 128.0 150 150 150 255  // Ready to bumble logo.
		
		DRAW_SPRITE 16 320.0 312.0 64.0 32.0 150 150 150 255
		DRAW_SPRITE 19 320.0 344.0 128.0 32.0 150 150 150 255   // Hi Scores is selected, others unselected.
		DRAW_SPRITE 20 320.0 376.0 64.0 32.0 150 150 150 255
		IF IS_JAPANESE_VERSION
			IF IS_BUTTON_PRESSED PAD1 CIRCLE

				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_ACCEPT
				GET_GAME_TIMER grav_help_start
				grav_on_table = SHOWING_HIGH_SCORES
				nGameState = INACTIVE
			ENDIF
		ELSE		
			IF IS_BUTTON_PRESSED PAD1 CROSS

				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_ACCEPT
				GET_GAME_TIMER grav_help_start
				grav_on_table = SHOWING_HIGH_SCORES
				nGameState = INACTIVE
			ENDIF
		ENDIF
    ENDIF

    IF nGameState = EXIT_SELECTED
	   
	    DRAW_SPRITE 15 320.0 196.0 256.0 128.0 150 150 150 255  // Ready to bumble logo.
		
		DRAW_SPRITE 16 320.0 312.0 64.0 32.0 150 150 150 255
		DRAW_SPRITE 18 320.0 344.0 128.0 32.0 150 150 150 255
		DRAW_SPRITE 21 320.0 376.0 64.0 32.0 150 150 150 255    // Exit is selected, others unselected.
		IF IS_JAPANESE_VERSION
			IF IS_BUTTON_PRESSED PAD1 CIRCLE
			
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_DECLINE
				DO_FADE 0 FADE_OUT
				GOTO mission_cleanup_grav
			ENDIF
		ELSE
			IF IS_BUTTON_PRESSED PAD1 CROSS
			
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_DECLINE
				DO_FADE 0 FADE_OUT
				GOTO mission_cleanup_grav
			ENDIF
		ENDIF
    ENDIF


IF grav_button_pressed = 0
	IF nGameState = PLAY_SELECTED

		IF IS_BUTTON_PRESSED PAD1 DPADUP
		OR grav_lsticky < -64
			nGameState = EXIT_SELECTED
			grav_button_pressed = 1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_SELECT
			GOTO grav_fe // delay / flicker without this
		ENDIF
		
		IF IS_BUTTON_PRESSED PAD1 DPADDOWN
		OR grav_lsticky > 64
			nGameState = HI_SCORE_SELECTED
			grav_button_pressed = 1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_SELECT
			GOTO grav_fe // delay / flicker without this
		ENDIF
	    
	ENDIF
	
	IF nGameState = HI_SCORE_SELECTED
		
		IF IS_BUTTON_PRESSED PAD1 DPADUP
		OR grav_lsticky < -64
			nGameState = PLAY_SELECTED
			grav_button_pressed = 1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_SELECT
			GOTO grav_fe // delay / flicker without this
		ENDIF
		
		IF IS_BUTTON_PRESSED PAD1 DPADDOWN
		OR grav_lsticky > 64
			nGameState = EXIT_SELECTED
			grav_button_pressed = 1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_SELECT
			GOTO grav_fe // delay / flicker without this
		ENDIF
		
	ENDIF

	IF nGameState = EXIT_SELECTED
		
		IF IS_BUTTON_PRESSED PAD1 DPADUP
		OR grav_lsticky < -64
			nGameState = HI_SCORE_SELECTED
			grav_button_pressed = 1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_SELECT
			GOTO grav_fe // delay / flicker without this
		ENDIF
		
		IF IS_BUTTON_PRESSED PAD1 DPADDOWN
		OR grav_lsticky > 64
			nGameState = PLAY_SELECTED
			grav_button_pressed = 1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_SELECT
			GOTO grav_fe // delay / flicker without this
		ENDIF
		
	ENDIF
ENDIF

IF nGameState = PLAYING_GAME
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_1
		grav_lives ++
	ENDIF

	GET_POSITION_OF_ANALOGUE_STICKS PAD1 grav_lstickx grav_lsticky grav_rstickx grav_rsticky
    //Make bee movement frame independent

    // draw player every frame in air - change sprite with heading
	
	IF grav_player_alive = 1
		IF grav_buzz = 12
			IF timerb > 10
				grav_buzz = 11
				timerb = 0
			ENDIF
		ELSE
			IF timerb > 10
				grav_buzz = 12
				timerb = 0
			ENDIF
		ENDIF	
		

		// Manage visual tilting of the bee. If they are not holding a direction, ease the bee back to no tilt.

		IF grav_lstickx = 0
			IF NOT IS_BUTTON_PRESSED PAD1 DPADLEFT
			AND NOT IS_BUTTON_PRESSED PAD1 DPADRIGHT
				IF grav_rotation > 180.0
					grav_rotation -= grav_rotation_step
				ENDIF
				
				IF grav_rotation < 180.0
					grav_rotation += grav_rotation_step
				ENDIF
			ENDIF
		ENDIF

		IF grav_lstickx > 0
		AND grav_lstickx < 64
			IF grav_rotation < 195.0
				grav_rotation += grav_rotation_step
			ENDIF
		ENDIF

		IF grav_lstickx < 0
		AND grav_lstickx > -64
			IF grav_rotation > 165.0
				grav_rotation -= grav_rotation_step
			ENDIF
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 DPADRIGHT
			IF grav_rotation < 210.0
				grav_rotation += 4.0
				IF grav_rotation < 180.0
					grav_rotation += 4.0
				ENDIF
			ENDIF
		ENDIF

		IF grav_lstickx > 63
		AND grav_lstickx < 130
			IF grav_rotation < 210.0
				grav_rotation += 4.0
				IF grav_rotation < 180.0
					grav_rotation += 4.0
				ENDIF
			ENDIF
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 DPADLEFT
			IF grav_rotation > 150.0
				grav_rotation -= 4.0
				IF grav_rotation > 180.0
					grav_rotation -= 4.0
				ENDIF
			ENDIF
		ENDIF

		IF grav_lstickx < -63
		AND grav_lstickx > -130
			IF grav_rotation > 150.0
				grav_rotation -= 4.0
				IF grav_rotation > 180.0
					grav_rotation -= 4.0
				ENDIF
			ENDIF
		ENDIF
	ENDIF

IF grav_next_level = 1
	grav_plyr_x = 300.0
	grav_plyr_y = 4096.0		
	GOSUB grav_make_level
	GOSUB grav_player_start
	grav_plyr_x	= grav_dead_x
	grav_plyr_y = grav_dead_y
	grav_next_level = 0
	grav_level_up = 1
	DO_FADE 0 FADE_IN
	
	WHILE GET_FADING_STATUS
		DRAW_SPRITE 1 128.0 128.0 256.0 256.0 150 150 150 255
		DRAW_SPRITE 1 128.0 384.0 256.0 256.0 150 150 150 255
		DRAW_SPRITE 1 384.0 128.0 256.0 256.0 150 150 150 255
		DRAW_SPRITE 1 384.0 384.0 256.0 256.0 150 150 150 255
		DRAW_SPRITE 1 640.0 128.0 256.0 256.0 150 150 150 255
		DRAW_SPRITE 1 640.0 384.0 256.0 256.0 150 150 150 255
		
		DRAW_SPRITE 7 160.0 112.0 320.0 224.0 150 150 150 255 
		DRAW_SPRITE 7 480.0 112.0 -320.0 224.0 150 150 150 255 
		DRAW_SPRITE 7 480.0 336.0 -320.0 -224.0 150 150 150 255 
		DRAW_SPRITE 7 160.0 336.0 320.0 -224.0 150 150 150 255
	WAIT 0
	
	ENDWHILE
	GET_GAME_TIMER grav_countdown_start
ENDIF

IF grav_next_level = 0
	GOSUB grav_update_screen
	IF grav_player_alive = 1 
		GOSUB grav_checkcollision
		GOSUB grav_update_player
	ENDIF
	GOSUB grav_draw_screen
ENDIF



// player dead
	IF grav_player_alive = 0
		IF grav_lives > 0
			timera = 0
			grav_lives --
			grav_player_alive = 2
			GOSUB grav_player_dead
		ENDIF
	ENDIF
	IF grav_player_alive = 2
		IF DO_2D_RECTANGLES_COLLIDE grav_plyr_x grav_plyr_y 4.0 4.0 grav_dead_x grav_dead_y 4.0 4.0
			grav_player_alive = 1
			grav_rotation = 180.0
			grav_plyr_x = grav_dead_x
			grav_plyr_y = grav_dead_y
		ENDIF
	ENDIF

 	// Game Over ------------------------------------------------------------------

	IF nGameState = PLAYING_GAME
		IF grav_player_alive = 0
			IF grav_lives = 0
				nGameState = INACTIVE
				grav_on_table = GAME_OVER_INIT
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_TRACK_STOP
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_GAME_OVER
			ENDIF
		ENDIF
	ENDIF
ENDIF

// quit mid-game
IF nGameState = INACTIVE

	IF IS_JAPANESE_VERSION
		IF IS_BUTTON_PRESSED PAD1 CROSS
		AND NOT grav_on_table = GAME_OVER_WAITING
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_TRACK_STOP
			GOTO grav_restart
		ENDIF
	ELSE
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
		AND NOT grav_on_table = GAME_OVER_WAITING
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_TRACK_STOP
			GOTO grav_restart
		ENDIF
	ENDIF
ENDIF
	
IF nGameState = INACTIVE
	IF grav_on_table = GAME_OVER_INIT
		GOSUB grav_text_gosub
		SET_TEXT_SCALE 2.0 4.0
		DISPLAY_TEXT 320.0 196.0 GR_A_5  // game over
		GET_GAME_TIMER grav_start_time
		grav_on_table = GAME_OVER_WAITING
	ENDIF
	
	IF grav_on_table = GAME_OVER_WAITING
		GOSUB grav_text_gosub
		SET_TEXT_SCALE 2.0 4.0
		DISPLAY_TEXT 320.0 196.0 GR_A_5  // game over
		GET_GAME_TIMER grav_end_time
		GET_GAME_TIMER grav_end_time
		grav_time_diff = grav_end_time - grav_start_time
		
		IF grav_time_diff > 5000
			grav_on_table = DECIDE_HI_SCORES_SCREEN
		ENDIF
	ENDIF
	
	// Go to displaying high scores or entering your name.

	IF grav_on_table = DECIDE_HI_SCORES_SCREEN
		IF grav_score < grav_hi_s[9]  
			grav_on_table = SHOWING_HIGH_SCORES
		ELSE
			GOSUB grav_check_scores
		ENDIF
	ENDIF
	
	IF grav_on_table = ENTERING_NAME
		GOSUB grav_write_name
		GOSUB grav_draw_scores
	ENDIF
	
	IF grav_on_table = SHOWING_HIGH_SCORES
		GOSUB grav_draw_scores
	ENDIF
ENDIF

  
 // hud - need to do this properly - namco style -> 1up xxxxxxx hi xxxxxxx life xx ?????
IF nGameState = PLAYING_GAME

	GOSUB grav_text_gosub
    DISPLAY_TEXT_WITH_NUMBER 560.0 390.0 VP15 grav_lives // lives left 
	DRAW_SPRITE_WITH_ROTATION 2 560.0 390.0 44.0 44.0 180.0 150 150 150 150
    DRAW_SPRITE_WITH_ROTATION 12 560.0 390.0 36.0 36.0 180.0 150 150 150 150 
	
	GOSUB grav_text_gosub
	DISPLAY_TEXT_WITH_NUMBER 90.0 390.0 VP15 grav_pickups_display	// 1up score
	DRAW_SPRITE_WITH_ROTATION 3 90.0 390.0 40.0 40.0 180.0 150 150 150 150
    DRAW_SPRITE_WITH_ROTATION 6 90.0 390.0 32.0 32.0 180.0 150 150 150 150
	grav_countdown_secs = grav_countdown / 1000
	minutes = grav_countdown_secs / 60
	grav_countdown_temp = minutes * 60
	grav_countdown_secs -= grav_countdown_temp
	GOSUB grav_text_gosub
	IF grav_countdown_secs > 9
	    DISPLAY_TEXT_WITH_2_NUMBERS 320.0 390.0 TIME minutes grav_countdown_secs//  ~1~:~1~
	ELSE
	    DISPLAY_TEXT_WITH_2_NUMBERS 320.0 390.0 TIME_0 minutes grav_countdown_secs//  ~1~:0~1~
	ENDIF
	DRAW_SPRITE_WITH_ROTATION 14 320.0 390.0 40.0 40.0 180.0 150 150 150 150
	GOSUB grav_text_gosub
	DISPLAY_TEXT_WITH_NUMBER 320.0 70.0 VP15 grav_score	// 1up score
	DRAW_SPRITE_WITH_ROTATION 13 320.0 70.0 40.0 40.0 180.0 150 150 150 150
		
	GET_GAME_TIMER grav_countdown_end
	grav_countdown_diff = grav_countdown_end - grav_countdown_start
	
	IF grav_countdown_diff < grav_countdown_max
		grav_countdown = grav_countdown_max - grav_countdown_diff
	ELSE
		IF grav_player_alive = 1
			IF grav_lives > 0
				grav_lives--
				grav_next_level = 1
				GET_GAME_TIMER grav_countdown_start
			ELSE
				grav_player_alive = 0
				nGameState = INACTIVE
				grav_on_table = GAME_OVER_INIT
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_TRACK_STOP
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_GAME_OVER
			ENDIF
		ENDIF
	ENDIF
ENDIF

	DRAW_SPRITE 7 160.0 112.0 320.0 224.0 150 150 150 255 
	DRAW_SPRITE 7 480.0 112.0 -320.0 224.0 150 150 150 255 
	DRAW_SPRITE 7 480.0 336.0 -320.0 -224.0 150 150 150 255 
	DRAW_SPRITE 7 160.0 336.0 320.0 -224.0 150 150 150 255

	IF nGameState = PLAYING_GAME
		GET_GAME_TIMER grav_help_end 
		grav_help_diff = grav_help_end - grav_help_start
		
		IF grav_help_diff < 10000
			DRAW_WINDOW 35.0 24.0 249.0 90.0 dummy SWIRLS_NONE
			SET_TEXT_CENTRE OFF
			SET_TEXT_WRAPX 249.0
			SET_TEXT_FONT FONT_STANDARD
			SET_TEXT_SCALE 0.5 1.8
			DISPLAY_TEXT 40.0 29.0 GR_NAV
		ENDIF
	ENDIF

    /*IF nGameState = INACTIVE
		IF grav_on_table = SHOWING_HIGH_SCORES
			
			GET_GAME_TIMER grav_help_end 
			grav_help_diff = grav_help_end - grav_help_start
			
			IF grav_help_diff < 10000
				DRAW_WINDOW 35.0 24.0 239.0 50.0 dummy SWIRLS_NONE
				SET_TEXT_CENTRE OFF
				SET_TEXT_WRAPX 239.0
				SET_TEXT_FONT FONT_STANDARD
				SET_TEXT_SCALE 0.5 1.8
				DISPLAY_TEXT 40.0 29.0 GR_BCK
			ENDIF
		ENDIF
	ENDIF*/

GOTO mission_grav_loop


	
// mission plat failed *********************************************************************

mission_failed_grav:
PRINT_BIG M_FAIL 5000 1
RETURN

// mission plat passed *********************************************************************

mission_passed_grav:

RETURN

// mission cleanup *************************************************************************

mission_cleanup_grav:

CLEAR_MISSION_AUDIO 4

CLEAR_THIS_PRINT BUSY

SHUT_ALL_CHARS_UP FALSE


CLEAR_ONSCREEN_COUNTER grav_countdown

IF IS_PLAYER_PLAYING player1
	IF LOCATE_CHAR_ON_FOOT_3D scplayer gravX gravY gravZ 2.0 2.0 2.0	0
		y = gravY + 2.0
		SET_CHAR_COORDINATES scplayer gravX  y gravZ
	ENDIF
	FREEZE_CHAR_POSITION scplayer FALSE
	SET_PLAYER_CONTROL player1 ON
	DO_FADE 1500 FADE_IN
ENDIF

SET_MUSIC_DOES_FADE TRUE
REMOVE_TEXTURE_DICTIONARY

flag_player_on_mission = 0

MISSION_HAS_FINISHED
RETURN


// gosubs **********************************************************************************
grav_text_gosub:
	SET_TEXT_COLOUR 168 142 51 255
	SET_TEXT_SCALE 1.0 2.0
	SET_TEXT_CENTRE ON
	SET_TEXT_WRAPX 640.0
RETURN

grav_player_start:
	grav_dist_temp_max = 99999.9
	grav_current_plat = 0
	WHILE grav_current_plat < 51
	AND NOT grav_ground_x[grav_current_plat] = 999.0
	AND NOT grav_ground_x[grav_current_plat] < 0.1
		grav_temp_y = grav_plyr_y
		IF grav_temp_y > 3000.0
			IF grav_temp_y < 4100.0
				GET_DISTANCE_BETWEEN_COORDS_2D grav_plyr_x grav_plyr_y grav_ground_x[grav_current_plat] grav_ground_y[grav_current_plat] grav_dist_temp
				IF grav_dist_temp < grav_dist_temp_max
					grav_dist_temp_max = grav_dist_temp
					grav_dead_x = grav_ground_x[grav_current_plat]
					grav_dead_y = grav_ground_y[grav_current_plat] - 32.0
				ENDIF
			ENDIF
		ENDIF
		grav_current_plat++
		SET_TEXT_COLOUR 168 142 51 255
		SET_TEXT_SCALE 1.0 2.0
		SET_TEXT_CENTRE ON
		SET_TEXT_WRAPX 640.0
		grav_level_display = grav_level	+ 1
		DISPLAY_TEXT_WITH_NUMBER 320.0 224.0 GR_LVL grav_level_display
		DRAW_SPRITE 7 160.0 112.0 320.0 224.0 150 150 150 255 
		DRAW_SPRITE 7 480.0 112.0 -320.0 224.0 150 150 150 255 
		DRAW_SPRITE 7 480.0 336.0 -320.0 -224.0 150 150 150 255 
		DRAW_SPRITE 7 160.0 336.0 320.0 -224.0 150 150 150 255
		WAIT 0
	ENDWHILE
RETURN

grav_player_dead:
	grav_dist_temp_max = 99999.9
	grav_dist_temp = 0.0
	grav_current_plat = 0
	WHILE grav_current_plat < 51//Finds the nearest platform to go to
	AND NOT grav_ground_x[grav_current_plat] = 999.0
	AND NOT grav_ground_x[grav_current_plat] < 0.1
		grav_temp_y = grav_ground_y[grav_current_plat] - grav_screen_y
		IF grav_temp_y > -200.00
		AND grav_temp_y <600.00
			GET_DISTANCE_BETWEEN_COORDS_2D grav_plyr_x grav_plyr_y grav_ground_x[grav_current_plat] grav_ground_y[grav_current_plat] grav_dist_temp
			IF grav_dist_temp < grav_dist_temp_max
				grav_dist_temp_max = grav_dist_temp		

				grav_dead_x = grav_ground_x[grav_current_plat]
				grav_dead_y = grav_ground_y[grav_current_plat] - 32.0

			ENDIF
		ENDIF
		
		grav_current_plat++
	ENDWHILE
	grav_velocity_x = grav_dead_x - grav_plyr_x
	grav_velocity_y = grav_dead_y - grav_plyr_y

	grav_temp_1 = grav_velocity_x * grav_velocity_x
	grav_temp_2 = grav_velocity_y * grav_velocity_y

	grav_velocity_mag_sqr = grav_temp_1 + grav_temp_2
	SQRT grav_velocity_mag_sqr grav_velocity_mag

	// get "speed"
	grav_temp_1 = grav_velocity_x / grav_velocity_mag
	grav_temp_2 = grav_velocity_y / grav_velocity_mag
	grav_speed_x = grav_plyr_dead_speed * grav_temp_1
	grav_speed_y = grav_plyr_dead_speed * grav_temp_2


RETURN


grav_checkcollision:

	// Check collisions with pickups
	grav_current_plat = 0
	WHILE grav_current_plat < 51
	AND NOT grav_pick_x[grav_current_plat] = 999.0
		grav_temp_y = grav_ground_y[grav_current_plat] - grav_screen_y
		IF grav_temp_y > -64.00
		AND grav_temp_y <480.00
			IF DO_2D_RECTANGLES_COLLIDE grav_plyr_x grav_plyr_y 32.0 44.0 grav_pick_x[grav_current_plat] grav_pick_y[grav_current_plat] 32.0 32.0
				grav_pick_x[grav_current_plat] = 999.0
				IF NOT grav_pickups_display = 1 
					grav_score += 10
					grav_pickups ++
					grav_pickups_display --
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_PICKUP
				ELSE
					grav_score += grav_pickups
					grav_countdown /= 100
					grav_score += grav_countdown
					grav_pickups = 0
					grav_level++
					grav_temp_score = grav_score - grav_temp_score2
					grav_temp_score2 = grav_temp_score
					IF grav_temp_score > 665
						REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_ACCEPT
						grav_lives ++
					ENDIF	
					//grav_countdown_max += 20000
					grav_next_level = 1	
					timera = 0
				ENDIF
				grav_pick_x[grav_current_plat]=-999.00
				RETURN
			ENDIF
		ENDIF
		grav_current_plat++
	ENDWHILE

	// Check collisions with ground
	grav_current_plat = 0
	WHILE grav_current_plat < 51
	AND NOT grav_ground_x[grav_current_plat] = 999.0
		grav_temp_y = grav_ground_y[grav_current_plat] - grav_screen_y
		IF grav_temp_y > -64.00
		AND grav_temp_y <480.00
			IF DO_2D_RECTANGLES_COLLIDE grav_plyr_x grav_plyr_y 32.0 44.0 grav_ground_x[grav_current_plat] grav_ground_y[grav_current_plat] 96.0 32.0
				grav_temp_y = grav_plyr_y + 12.0
				IF grav_temp_y < grav_ground_y[grav_current_plat]
					IF grav_ground = 0
						IF NOT IS_BUTTON_PRESSED PAD1 CROSS
						OR grav_speed_y < 0.0
							grav_temp_x = grav_ground_x[grav_current_plat] - 52.0
							IF NOT grav_plyr_x < grav_temp_x
								grav_temp_x = grav_ground_x[grav_current_plat] + 52.0
								IF NOT grav_plyr_x > grav_temp_x
									//grav_speed_x *= -0.1
								   	grav_speed_y *= -0.1
									grav_plyr_y = grav_ground_y[grav_current_plat]
									grav_plyr_y -= 38.0
									grav_ground = 1
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ELSE
					grav_speed_x*=-0.5
					grav_speed_y*=-0.5
					grav_plyr_y += 4.0
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_DROP
				ENDIF
			ELSE
				grav_ground = 0
			ENDIF
		ENDIF
		grav_current_plat++
	ENDWHILE

		// Check collisions with platforms
	grav_current_plat = 0
	WHILE grav_current_plat < 100
	AND NOT grav_plat_x[grav_current_plat] = 999.0
		grav_temp_y = grav_plat_y[grav_current_plat] - grav_screen_y
		IF grav_temp_y > -64.00
		AND grav_temp_y <480.00
			IF DO_2D_RECTANGLES_COLLIDE grav_plyr_x grav_plyr_y 32.0 44.0 grav_plat_x[grav_current_plat] grav_plat_y[grav_current_plat] 128.0 40.0
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_ZAP
				grav_level_up = 0
				grav_player_alive = 0
				timera = 0
			ENDIF
		ENDIF
		grav_current_plat++
	ENDWHILE

RETURN


grav_make_level:
	DO_FADE 0 FADE_OUT
	
	WHILE GET_FADING_STATUS
		DRAW_SPRITE 1 128.0 128.0 256.0 256.0 150 150 150 255
		DRAW_SPRITE 1 128.0 384.0 256.0 256.0 150 150 150 255
		DRAW_SPRITE 1 384.0 128.0 256.0 256.0 150 150 150 255
		DRAW_SPRITE 1 384.0 384.0 256.0 256.0 150 150 150 255
		DRAW_SPRITE 1 640.0 128.0 256.0 256.0 150 150 150 255
		DRAW_SPRITE 1 640.0 384.0 256.0 256.0 150 150 150 255

		DRAW_SPRITE 7 160.0 112.0 320.0 224.0 150 150 150 255 
		DRAW_SPRITE 7 480.0 112.0 -320.0 224.0 150 150 150 255 
		DRAW_SPRITE 7 480.0 336.0 -320.0 -224.0 150 150 150 255 
		DRAW_SPRITE 7 160.0 336.0 320.0 -224.0 150 150 150 255
	WAIT 0
	
	ENDWHILE
	// generate platforms
	grav_current_plat = 0
	grav_temp1 = grav_level
	grav_temp1++
	grav_temp1 *= 10
	//grav_temp1 += 10

	IF grav_temp1 > 50
		grav_temp1 = 50
	ENDIF

	WHILE grav_current_plat < grav_temp1
	//grav_temp1
 		GENERATE_RANDOM_INT_IN_RANGE 2 7 grav_temp
		grav_temp *= 80
		grav_temp_x =# grav_temp

 		GENERATE_RANDOM_INT_IN_RANGE 0 30 grav_temp2
		grav_temp2 *= 128 

		grav_plat_x[grav_current_plat] = grav_temp_x
		grav_plat_y[grav_current_plat] =# grav_temp2	  

		grav_current_plat++
		SET_TEXT_COLOUR 168 142 51 255
		SET_TEXT_SCALE 1.0 2.0
		SET_TEXT_CENTRE ON
		SET_TEXT_WRAPX 640.0

		grav_level_display = grav_level	+ 1
		DISPLAY_TEXT_WITH_NUMBER 320.0 224.0 GR_LVL grav_level_display

		DRAW_SPRITE 7 160.0 112.0 320.0 224.0 150 150 150 255 
		DRAW_SPRITE 7 480.0 112.0 -320.0 224.0 150 150 150 255 
		DRAW_SPRITE 7 480.0 336.0 -320.0 -224.0 150 150 150 255 
		DRAW_SPRITE 7 160.0 336.0 320.0 -224.0 150 150 150 255

		WAIT 0
	ENDWHILE

	grav_plat_x[grav_current_plat] = 999.0
	grav_plat_y[grav_current_plat] = 999.0

	// generate pickups
	
	grav_current_plat = 0

	WHILE grav_current_plat < grav_temp1
	//grav_temp1
 		GENERATE_RANDOM_INT_IN_RANGE 2 7 grav_temp
		grav_temp *= 80
		grav_temp_x =# grav_temp
		
		GENERATE_RANDOM_INT_IN_RANGE 0 32 grav_temp2
		grav_temp2 *= 128
		grav_temp2+=64 
		
		grav_pick_x[grav_current_plat] = grav_temp_x
		grav_pick_y[grav_current_plat] =# grav_temp2	  

		grav_temp_x -= 48.0 
		grav_temp2 += 32 

		grav_ground_x[grav_current_plat] = grav_temp_x
		grav_ground_y[grav_current_plat] =# grav_temp2

		grav_current_plat++

		SET_TEXT_COLOUR 168 142 51 255
		SET_TEXT_SCALE 1.0 2.0
		SET_TEXT_CENTRE ON
		SET_TEXT_WRAPX 640.0
		grav_level_display = grav_level	+ 1
		DISPLAY_TEXT_WITH_NUMBER 320.0 224.0 GR_LVL grav_level_display

		DRAW_SPRITE 7 160.0 112.0 320.0 224.0 150 150 150 255 
		DRAW_SPRITE 7 480.0 112.0 -320.0 224.0 150 150 150 255 
		DRAW_SPRITE 7 480.0 336.0 -320.0 -224.0 150 150 150 255 
		DRAW_SPRITE 7 160.0 336.0 320.0 -224.0 150 150 150 255

		WAIT 0
	ENDWHILE

	grav_pick_x[grav_current_plat] = 999.0
	grav_pick_y[grav_current_plat] = 999.0
	grav_ground_x[grav_current_plat]=999.0
	grav_ground_y[grav_current_plat]=999.0

	grav_total_pickups=grav_temp1
	grav_pickups_display = grav_total_pickups

	grav_current_plat = grav_temp1
	grav_plat_x[grav_current_plat]=52.0
	grav_plat_y[grav_current_plat]=4096.0
	grav_current_plat++
	grav_plat_x[grav_current_plat]=164.0
	grav_plat_y[grav_current_plat]=4096.0
	grav_current_plat++
	grav_plat_x[grav_current_plat]=268.0
	grav_plat_y[grav_current_plat]=4096.0
	grav_current_plat++
	grav_plat_x[grav_current_plat]=372.0
	grav_plat_y[grav_current_plat]=4096.0
	grav_current_plat++
	grav_plat_x[grav_current_plat]=476.0
	grav_plat_y[grav_current_plat]=4096.0
	grav_current_plat++
	grav_plat_x[grav_current_plat]=580.0
	grav_plat_y[grav_current_plat]=4096.0
	grav_current_plat++
	//grav_current_plat = grav_temp1
	grav_plat_x[grav_current_plat]=52.0
	grav_plat_y[grav_current_plat]=0.0
	grav_current_plat++
	grav_plat_x[grav_current_plat]=164.0
	grav_plat_y[grav_current_plat]=0.0
	grav_current_plat++
	grav_plat_x[grav_current_plat]=268.0
	grav_plat_y[grav_current_plat]=0.0
	grav_current_plat++
	grav_plat_x[grav_current_plat]=372.0
	grav_plat_y[grav_current_plat]=0.0
	grav_current_plat++
	grav_plat_x[grav_current_plat]=476.0
	grav_plat_y[grav_current_plat]=0.0
	grav_current_plat++
	grav_plat_x[grav_current_plat]=580.0
	grav_plat_y[grav_current_plat]=0.0
	grav_current_plat++
	grav_plat_x[grav_current_plat]=999.0
	grav_plat_y[grav_current_plat]=999.0
RETURN

grav_update_player:
	
	IF grav_player_alive = 1
		
		IF grav_plyr_x < 50.0  // Clamp to sides of screen.
			grav_plyr_x = 50.0
		ENDIF	
		
		IF grav_plyr_x > 590.0
			grav_plyr_x = 590.0
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 CROSS   // Accelerate up.
		
			IF grav_ground = 1
				grav_ground = 0
				grav_speed_y += 4.0
			ENDIF
			IF grav_speed_y < 20.0
				grav_speed_y += 0.4
			ENDIF
		ENDIF
		IF IS_BUTTON_PRESSED PAD1 SQUARE
			IF grav_ground = 0
				IF grav_speed_y > 0.0
					grav_speed_y -= 0.2
				ENDIF
			ENDIF
		ENDIF

		// Add friction.

		grav_temp_x = grav_speed_x
		grav_temp_x /= 4.0
		grav_speed_x -= grav_temp_x

		IF IS_BUTTON_PRESSED PAD1 DPADLEFT
			grav_lstickx = -127
		ENDIF
		
		IF IS_BUTTON_PRESSED PAD1 DPADRIGHT
			grav_lstickx = 127
		ENDIF
		
		// Directional movement.
								   
		grav_temp_x =# grav_lstickx
		grav_temp_x /= 128.0
		grav_speed_x += grav_temp_x

		grav_plyr_x += grav_speed_x
			
		
		// Gravity

		IF grav_ground = 0
			IF grav_speed_y > -20.0			
				grav_speed_y -= 0.2
			ENDIF
  		ENDIF

		grav_plyr_y -= grav_speed_y

	ENDIF
			
RETURN

grav_draw_screen:
    
	DRAW_SPRITE 1 128.0 128.0 256.0 256.0 150 150 150 255
	DRAW_SPRITE 1 128.0 384.0 256.0 256.0 150 150 150 255
	DRAW_SPRITE 1 384.0 128.0 256.0 256.0 150 150 150 255
	DRAW_SPRITE 1 384.0 384.0 256.0 256.0 150 150 150 255
	DRAW_SPRITE 1 640.0 128.0 256.0 256.0 150 150 150 255
	DRAW_SPRITE 1 640.0 384.0 256.0 256.0 150 150 150 255

	// Draw platforms
	grav_current_plat = 0
	WHILE grav_current_plat < 100
	AND NOT grav_plat_x[grav_current_plat] = 999.0
		grav_temp_y = grav_plat_y[grav_current_plat] - grav_screen_y
		IF grav_temp_y > -64.00
			AND grav_temp_y <480.00
				DRAW_SPRITE	10 grav_plat_x[grav_current_plat] grav_temp_y 128.0 64.0 150 150 150 255
		ENDIF

		grav_current_plat++
	ENDWHILE

	// Draw pickups
	grav_current_plat = 0
	WHILE grav_current_plat < 100
	AND NOT grav_pick_x[grav_current_plat] = 999.0
		grav_temp_y = grav_pick_y[grav_current_plat] - grav_screen_y
		IF grav_temp_y > -64.00
			AND grav_temp_y <480.00
			DRAW_SPRITE	6 grav_pick_x[grav_current_plat] grav_temp_y 32.0 32.0 150 150 150 255
		ENDIF

		grav_current_plat++
	ENDWHILE

	// Draw ground
	grav_current_plat = 0
	WHILE grav_current_plat < 100
	AND NOT grav_ground_x[grav_current_plat] = 999.0
		grav_temp_y = grav_ground_y[grav_current_plat] - grav_screen_y
		IF grav_temp_y > -64.00
			AND grav_temp_y <480.00
			DRAW_SPRITE 5 grav_ground_x[grav_current_plat] grav_temp_y 128.0 32.0 150 150 150 255
		ENDIF
		grav_current_plat++
	ENDWHILE

	// Draw ship
	
	grav_temp_x = grav_plyr_x - grav_screen_x
	grav_temp_y = grav_plyr_y - grav_screen_y
    
	IF grav_player_alive = 1
		IF NOT IS_BUTTON_PRESSED PAD1 CROSS
			DRAW_SPRITE_WITH_ROTATION 12 grav_temp_x grav_temp_y 48.0 48.0 grav_rotation 150 150 150 255
		ELSE
			DRAW_SPRITE_WITH_ROTATION grav_buzz grav_temp_x grav_temp_y 48.0 48.0 grav_rotation 150 150 150 255
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BEE_BUZZ
		ENDIF
	ELSE
		IF grav_player_alive = 2
			DRAW_SPRITE_WITH_ROTATION 4 grav_temp_x grav_temp_y 48.0 48.0 180.0 150 150 150 255
			grav_plyr_x +=@ grav_speed_x 
			grav_plyr_y +=@ grav_speed_y
		ENDIF
	ENDIF

RETURN

grav_update_screen:
  //	grav_temp_x = grav_plyr_x - grav_screen_x
	grav_temp_y = grav_plyr_y - grav_screen_y 
	IF grav_temp_y < 200.0
	   //	grav_screen_y -= 1.0
		grav_screen_temp_y = 200.0 - grav_temp_y
	  	grav_screen_y -= grav_screen_temp_y
	ENDIF
	IF grav_temp_y > 300.0
	 //	grav_screen_y += 1.0
		grav_screen_temp_y = grav_temp_y - 300.0 
  		grav_screen_y += grav_screen_temp_y
	ENDIF


RETURN
RETURN


// Hi Score-A-Rama

grav_check_scores: // (c) Graeme 2003
	grav_ranking = 9
	grav_score_update = FALSE
	grav_score_check = TRUE
	// find which slot the new score has to be inserted into
	WHILE grav_ranking >= 0
	AND grav_score_check = TRUE
		IF grav_score > grav_hi_s[grav_ranking]
			grav_ranking--
			grav_score_update = TRUE
			grav_on_table = ENTERING_NAME
			grav_draw_highlight = 1
		ELSE
			grav_score_check = FALSE
	    ENDIF
	ENDWHILE

 	IF grav_score_update = TRUE
		grav_ranking++
		// shift the other scores down
		grav_destination_slot = 9
		grav_source_slot = 8
		WHILE grav_source_slot >= grav_ranking
			$grav_hi_1[grav_destination_slot] = $grav_hi_1[grav_source_slot] // First letter of player name
			$grav_hi_2[grav_destination_slot] = $grav_hi_2[grav_source_slot] // Second letter of player name
			$grav_hi_3[grav_destination_slot] = $grav_hi_3[grav_source_slot] // Third letter of player name 
			grav_hi_l[grav_destination_slot] = grav_hi_l[grav_source_slot] // Level Number 	
			grav_hi_s[grav_destination_slot] = grav_hi_s[grav_source_slot] // Score
			grav_destination_slot--
			grav_source_slot--
		ENDWHILE
		// write the new score into the correct slot
		grav_hi_s[grav_ranking] = grav_score
		grav_hi_l[grav_ranking] = grav_level
		grav_write = 0
		grav_chars = 10 // A
	ENDIF
RETURN

grav_write_name:
	
	GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
	
	IF IS_JAPANESE_VERSION
		IF NOT IS_BUTTON_PRESSED PAD1 DPADUP
		AND NOT IS_BUTTON_PRESSED PAD1 DPADDOWN
		AND NOT IS_BUTTON_PRESSED PAD1 DPADLEFT
		AND NOT IS_BUTTON_PRESSED PAD1 DPADRIGHT
		AND NOT IS_BUTTON_PRESSED PAD1 CIRCLE
			IF LStickY = 0
			AND LStickX = 0
				grav_press = 0
			ENDIF
		ENDIF
	ELSE
		IF NOT IS_BUTTON_PRESSED PAD1 DPADUP
		AND NOT IS_BUTTON_PRESSED PAD1 DPADDOWN
		AND NOT IS_BUTTON_PRESSED PAD1 DPADLEFT
		AND NOT IS_BUTTON_PRESSED PAD1 DPADRIGHT
		AND NOT IS_BUTTON_PRESSED PAD1 CROSS
			IF LStickY = 0
			AND LStickX = 0
				grav_press = 0
			ENDIF
		ENDIF
	ENDIF
	
	// Write first letter
	
	IF grav_write = 0
		$grav_hi_1[grav_ranking] = $grav_char[grav_chars]
		$grav_hi_2[grav_ranking] = $grav_char[10]
		$grav_hi_3[grav_ranking] = $grav_char[10]
		
		IF grav_press = 0
			IF IS_BUTTON_PRESSED PAD1 DPADUP 
		    OR IS_BUTTON_PRESSED PAD1 DPADRIGHT
			OR LStickX > 100
			OR LStickY < -100
				IF grav_chars < 36 
					grav_chars++
				ELSE
					grav_chars = 0
				ENDIF
				grav_press = 1
			ENDIF
			
			IF IS_BUTTON_PRESSED PAD1 DPADDOWN
		    OR IS_BUTTON_PRESSED PAD1 DPADLEFT
			OR LStickX < -100
			OR LStickY > 100
				IF grav_chars > 0
					grav_chars--
				ELSE
					grav_chars = 36
				ENDIF
				grav_press = 1
			ENDIF
			
			IF IS_JAPANESE_VERSION
				IF IS_BUTTON_PRESSED PAD1 CIRCLE
					grav_chars = 10
					grav_write = 1
					grav_press = 1
				ENDIF
			ELSE
				IF IS_BUTTON_PRESSED PAD1 CROSS
					grav_chars = 10
					grav_write = 1
					grav_press = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// Write second letter
	
	IF grav_write = 1
		$grav_hi_2[grav_ranking] = $grav_char[grav_chars]
		IF grav_press = 0
		
			IF IS_BUTTON_PRESSED PAD1 DPADUP 
		    OR IS_BUTTON_PRESSED PAD1 DPADRIGHT
			OR LStickX > 100
			OR LStickY < -100
				IF grav_chars < 36 
					grav_chars++
				ELSE
					grav_chars = 0
				ENDIF
				grav_press = 1
			ENDIF

			IF IS_BUTTON_PRESSED PAD1 DPADDOWN
		    OR IS_BUTTON_PRESSED PAD1 DPADLEFT
			OR LStickX < -100
			OR LStickY > 100
				IF grav_chars > 0
					grav_chars--
				ELSE
					grav_chars = 36
				ENDIF
				grav_press = 1
			ENDIF
			IF IS_JAPANESE_VERSION
				IF IS_BUTTON_PRESSED PAD1 CIRCLE
					grav_chars = 10
					grav_write = 2
					grav_press = 1
				ENDIF
			ELSE
				IF IS_BUTTON_PRESSED PAD1 CROSS
					grav_chars = 10
					grav_write = 2
					grav_press = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// Write third letter
	
	IF grav_write = 2
		$grav_hi_3[grav_ranking] = $grav_char[grav_chars]
		IF grav_press = 0
			
			IF IS_BUTTON_PRESSED PAD1 DPADUP 
		    OR IS_BUTTON_PRESSED PAD1 DPADRIGHT
			OR LStickX > 100
			OR LStickY < -100
				IF grav_chars < 36 
					grav_chars++
				ELSE
					grav_chars = 0
				ENDIF
				grav_press = 1
			ENDIF

			IF IS_BUTTON_PRESSED PAD1 DPADDOWN
		    OR IS_BUTTON_PRESSED PAD1 DPADLEFT
			OR LStickX < -100
			OR LStickY > 100
				IF grav_chars > 0
					grav_chars--
				ELSE
					grav_chars = 36
				ENDIF
				grav_press = 1
			ENDIF
			
			IF IS_JAPANESE_VERSION
				IF IS_BUTTON_PRESSED PAD1 CIRCLE
					grav_write = 3
					grav_press = 1
					grav_on_table = SHOWING_HIGH_SCORES
				ENDIF
			ELSE
				IF IS_BUTTON_PRESSED PAD1 CROSS
					grav_write = 3
					grav_press = 1
					grav_on_table = SHOWING_HIGH_SCORES
				ENDIF
			ENDIF
		ENDIF
	ENDIF
RETURN

grav_draw_scores:
	
	grav_hi_loop = 0
	
	WHILE grav_hi_loop < 10
		GOSUB grav_text_gosub
		DISPLAY_TEXT grav_hi_1_x[grav_hi_loop] grav_hi_1_y[grav_hi_loop] $grav_hi_1[grav_hi_loop]	// First letter of player name
		GOSUB grav_text_gosub
		DISPLAY_TEXT grav_hi_2_x[grav_hi_loop] grav_hi_2_y[grav_hi_loop] $grav_hi_2[grav_hi_loop]	// Second letter of player name
		GOSUB grav_text_gosub
		DISPLAY_TEXT grav_hi_3_x[grav_hi_loop] grav_hi_3_y[grav_hi_loop] $grav_hi_3[grav_hi_loop] 	// Third letter of player name
		GOSUB grav_text_gosub
		SET_TEXT_RIGHT_JUSTIFY ON
		DISPLAY_TEXT_WITH_NUMBER grav_hi_4_x[grav_hi_loop] grav_hi_4_y[grav_hi_loop] grav_LV grav_hi_s[grav_hi_loop]
		grav_hi_loop++
	ENDWHILE
	
	GOSUB grav_text_gosub
	DISPLAY_TEXT 320.0 64.0 GRAV_2F  // HI-SCORE

	IF grav_lives = 0
	AND grav_draw_highlight = 1
		IF grav_write = 0
			GOSUB grav_text_gosub
			SET_TEXT_COLOUR 255 64 0 255
			DISPLAY_TEXT grav_hi_1_x[grav_ranking] grav_hi_1_y[grav_ranking] $grav_hi_1[grav_ranking]
		ELSE
			GOSUB grav_text_gosub
			SET_TEXT_COLOUR 255 128 0 255
			DISPLAY_TEXT grav_hi_1_x[grav_ranking] grav_hi_1_y[grav_ranking] $grav_hi_1[grav_ranking]
		ENDIF 
		// First letter of player name
		IF grav_write = 1
			GOSUB grav_text_gosub
			SET_TEXT_COLOUR 255 64 0 255
			DISPLAY_TEXT grav_hi_2_x[grav_ranking] grav_hi_2_y[grav_ranking] $grav_hi_2[grav_ranking]
		ELSE
			GOSUB grav_text_gosub
			SET_TEXT_COLOUR 255 128 0 255
			DISPLAY_TEXT grav_hi_2_x[grav_ranking] grav_hi_2_y[grav_ranking] $grav_hi_2[grav_ranking]
		ENDIF 
		// Second letter of player name
		IF grav_write = 2
			GOSUB grav_text_gosub
			SET_TEXT_COLOUR 255 64 0 255
			DISPLAY_TEXT grav_hi_3_x[grav_ranking] grav_hi_3_y[grav_ranking] $grav_hi_3[grav_ranking]
		ELSE
			GOSUB grav_text_gosub
			SET_TEXT_COLOUR 255 128 0 255
			DISPLAY_TEXT grav_hi_3_x[grav_ranking] grav_hi_3_y[grav_ranking] $grav_hi_3[grav_ranking]
		ENDIF 
		 
		GOSUB grav_text_gosub
		SET_TEXT_RIGHT_JUSTIFY ON
		SET_TEXT_COLOUR 255 128 0 255
		DISPLAY_TEXT_WITH_NUMBER grav_hi_4_x[grav_ranking] grav_hi_4_y[grav_ranking] grav_LV grav_hi_s[grav_ranking]
	ENDIF
RETURN

grav_generate_default_scores:

	GENERATE_RANDOM_INT_IN_RANGE 1 30 grav_num_gen

	// Neil	(NF.)
	IF grav_num_gen = 1
		IF grav_used[1] = 0
			$grav_hi_1[grav_current] = $grav_char[23] 
			$grav_hi_2[grav_current] = $grav_char[15] 
			$grav_hi_3[grav_current] = $grav_char[36]
			grav_used[1] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// Imran (IMY)
	IF grav_num_gen = 2
		IF grav_used[2] = 0
			$grav_hi_1[grav_current] = $grav_char[18] 
			$grav_hi_2[grav_current] = $grav_char[22] 
			$grav_hi_3[grav_current] = $grav_char[34]
			grav_used[2] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// Willie (WIL)
	IF grav_num_gen = 3
		IF grav_used[3] = 0
			$grav_hi_1[grav_current] = $grav_char[32] 
			$grav_hi_2[grav_current] = $grav_char[18] 
			$grav_hi_3[grav_current] = $grav_char[21]
			grav_used[3] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF 

	// Chris (CKR)
	IF grav_num_gen = 4
		IF grav_used[4] = 0
			$grav_hi_1[grav_current] = $grav_char[12] 
			$grav_hi_2[grav_current] = $grav_char[20] 
			$grav_hi_3[grav_current] = $grav_char[27]
			grav_used[4] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF 

	// Andy	(DBP)
	IF grav_num_gen = 5
		IF grav_used[5] = 0
			$grav_hi_1[grav_current] = $grav_char[13] 
			$grav_hi_2[grav_current] = $grav_char[11] 
			$grav_hi_3[grav_current] = $grav_char[25]
			grav_used[5] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF 

	// David (DAV)
	IF grav_num_gen = 6
		IF grav_used[6] = 0
			$grav_hi_1[grav_current] = $grav_char[13]  
			$grav_hi_2[grav_current] = $grav_char[10]  
			$grav_hi_3[grav_current] = $grav_char[31] 
			grav_used[6] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF 

	// James (DOD)
	IF grav_num_gen = 7
		IF grav_used[7] = 0
			$grav_hi_1[grav_current] = $grav_char[13] 
			$grav_hi_2[grav_current] = $grav_char[24] 
			$grav_hi_3[grav_current] = $grav_char[13]
			grav_used[7] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// Simon (SJL)
	IF grav_num_gen = 8
		IF grav_used[8] = 0
			$grav_hi_1[grav_current] = $grav_char[28] 
			$grav_hi_2[grav_current] = $grav_char[19] 
			$grav_hi_3[grav_current] = $grav_char[21]
			grav_used[8] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF 

	// Steve (STE)
	IF grav_num_gen = 9
		IF grav_used[9] = 0
			$grav_hi_1[grav_current] = $grav_char[28] 
			$grav_hi_2[grav_current] = $grav_char[29] 
			$grav_hi_3[grav_current] = $grav_char[14]
			grav_used[9] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF 

	// Judith (JUD)
	IF grav_num_gen = 10
		IF grav_used[10] = 0
			$grav_hi_1[grav_current] = $grav_char[19] 
			$grav_hi_2[grav_current] = $grav_char[30] 
			$grav_hi_3[grav_current] = $grav_char[13]
			grav_used[10] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// Kev Bolt (KMB)
	IF grav_num_gen = 11
		IF grav_used[11] = 0
			$grav_hi_1[grav_current] = $grav_char[20] 
			$grav_hi_2[grav_current] = $grav_char[22] 
			$grav_hi_3[grav_current] = $grav_char[11]
			grav_used[11] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF 

	// Graeme (GSW)
	IF grav_num_gen = 12
		IF grav_used[12] = 0
			$grav_hi_1[grav_current] = $grav_char[16] 
			$grav_hi_2[grav_current] = $grav_char[28] 
			$grav_hi_3[grav_current] = $grav_char[32]
			grav_used[12] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF 

	// Dave W (DSW)
	IF grav_num_gen = 13
		IF grav_used[13] = 0
			$grav_hi_1[grav_current] = $grav_char[13] 
			$grav_hi_2[grav_current] = $grav_char[28] 
			$grav_hi_3[grav_current] = $grav_char[32]
			grav_used[13] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// Woody (WDY)
	IF grav_num_gen = 14
		IF grav_used[14] = 0
			$grav_hi_1[grav_current] = $grav_char[32] 
			$grav_hi_2[grav_current] = $grav_char[13] 
			$grav_hi_3[grav_current] = $grav_char[34]
			grav_used[14] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// Gary (GAZ)
	IF grav_num_gen = 15
		IF grav_used[15] = 0
			$grav_hi_1[grav_current] = $grav_char[16] 
			$grav_hi_2[grav_current] = $grav_char[10] 
			$grav_hi_3[grav_current] = $grav_char[35]
			grav_used[15] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// Wayland (WAZ)
	IF grav_num_gen = 16
		IF grav_used[16] = 0
			$grav_hi_1[grav_current] = $grav_char[32] 
			$grav_hi_2[grav_current] = $grav_char[10] 
			$grav_hi_3[grav_current] = $grav_char[35]
			grav_used[16] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// Kinch (KIN)
	IF grav_num_gen = 17
		IF grav_used[17] = 0
			$grav_hi_1[grav_current] = $grav_char[20] 
			$grav_hi_2[grav_current] = $grav_char[18] 
			$grav_hi_3[grav_current] = $grav_char[23]
			grav_used[17] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// Bean (BEA)
	IF grav_num_gen = 18
		IF grav_used[18] = 0
			$grav_hi_1[grav_current] = $grav_char[11] 
			$grav_hi_2[grav_current] = $grav_char[14] 
			$grav_hi_3[grav_current] = $grav_char[10]
			grav_used[18] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// Baxy (BAX)
	IF grav_num_gen = 19
		IF grav_used[19] = 0
			$grav_hi_1[grav_current] = $grav_char[11] 
			$grav_hi_2[grav_current] = $grav_char[10] 
			$grav_hi_3[grav_current] = $grav_char[33]
			grav_used[19] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// (LOU)
	IF grav_num_gen = 20
		IF grav_used[20] = 0
			$grav_hi_1[grav_current] = $grav_char[21] 
			$grav_hi_2[grav_current] = $grav_char[24] 
			$grav_hi_3[grav_current] = $grav_char[30]
			grav_used[20] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// (JNO)
	IF grav_num_gen = 21
		IF grav_used[21] = 0
			$grav_hi_1[grav_current] = $grav_char[19] 
			$grav_hi_2[grav_current] = $grav_char[23] 
			$grav_hi_3[grav_current] = $grav_char[24]
			grav_used[21] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// (MYT)
	IF grav_num_gen = 22
		IF grav_used[22] = 0
			$grav_hi_1[grav_current] = $grav_char[22] 
			$grav_hi_2[grav_current] = $grav_char[34] 
			$grav_hi_3[grav_current] = $grav_char[29]
			grav_used[22] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// (DEF)
	IF grav_num_gen = 23
		IF grav_used[23] = 0
			$grav_hi_1[grav_current] = $grav_char[13] 
			$grav_hi_2[grav_current] = $grav_char[14] 
			$grav_hi_3[grav_current] = $grav_char[15]
			grav_used[23] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// (KHZ)
	IF grav_num_gen = 24
		IF grav_used[24] = 0
			$grav_hi_1[grav_current] = $grav_char[20] 
			$grav_hi_2[grav_current] = $grav_char[17] 
			$grav_hi_3[grav_current] = $grav_char[35]
			grav_used[24] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// (MEO)
	IF grav_num_gen = 25
		IF grav_used[25] = 0
			$grav_hi_1[grav_current] = $grav_char[22] 
			$grav_hi_2[grav_current] = $grav_char[14] 
			$grav_hi_3[grav_current] = $grav_char[24]
			grav_used[25] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// George (GFW)
	IF grav_num_gen = 26
		IF grav_used[26] = 0
			$grav_hi_1[grav_current] = $grav_char[16] 
			$grav_hi_2[grav_current] = $grav_char[15] 
			$grav_hi_3[grav_current] = $grav_char[32]
			grav_used[26] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF	 

	// Will (WRM)
	IF grav_num_gen = 27
		IF grav_used[27] = 0
			$grav_hi_1[grav_current] = $grav_char[32] 
			$grav_hi_2[grav_current] = $grav_char[27] 
			$grav_hi_3[grav_current] = $grav_char[22]
			grav_used[27] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// Allan (A.W)
	IF grav_num_gen = 28
		IF grav_used[28] = 0
			$grav_hi_1[grav_current] = $grav_char[10] 
			$grav_hi_2[grav_current] = $grav_char[36] 
			$grav_hi_3[grav_current] = $grav_char[32]
			grav_used[28] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF

	// Richy (RIC)
	IF grav_num_gen = 29
		IF grav_used[29] = 0
			$grav_hi_1[grav_current] = $grav_char[27] 
			$grav_hi_2[grav_current] = $grav_char[18] 
			$grav_hi_3[grav_current] = $grav_char[12]
			grav_used[29] = 1
		ELSE
			GOTO grav_generate_default_scores
		ENDIF
	ENDIF
RETURN




// the end *********************************************************************************
}

/*
[GR_A_1:GRAV]
1UP ~1~

[GR_A_2:GRAV]
HI-SCORE ~1~

[GR_A_3:GRAV]
LIVES ~1~

[GR_A_4:GRAV]
BEGIN!

[GR_A_5:GRAV]
GAME OVER!

[GR_A_6:GRAV]

[GR_A_7:GRAV]

[GR_A_8:GRAV]


[GRAV_0:GRAV]
0
[GRAV_1:GRAV]
1
[GRAV_2:GRAV]
2
[GRAV_3:GRAV]
3
[GRAV_4:GRAV]
4

[GRAV_5:GRAV]
5
[GRAV_6:GRAV]
6
[GRAV_7:GRAV]
7
[GRAV_8:GRAV]
8
[GRAV_9:GRAV]
9

[GRAV_A:GRAV]
A
[GRAV_B:GRAV]
B
[GRAV_C:GRAV]
C
[GRAV_D:GRAV]
D
[GRAV_E:GRAV]
E

[GRAV_F:GRAV]
F
[GRAV_G:GRAV]
G
[GRAV_H:GRAV]
H
[GRAV_I:GRAV]
I
[GRAV_J:GRAV]
J

[GRAV_K:GRAV]
K
[GRAV_L:GRAV]
L
[GRAV_M:GRAV]
M
[GRAV_N:GRAV]
N
[GRAV_O:GRAV]
O

[GRAV_P:GRAV]
P
[GRAV_Q:GRAV]
Q
[GRAV_R:GRAV]
R
[GRAV_S:GRAV]
S
[GRAV_T:GRAV]
T

[GRAV_U:GRAV]
U
[GRAV_V:GRAV]
V
[GRAV_W:GRAV]
W
[GRAV_X:GRAV]
X
[GRAV_Y:GRAV]
Y

[GRAV_Z:GRAV]
Z
[GRAV_PE:GRAV]
.


*/				   