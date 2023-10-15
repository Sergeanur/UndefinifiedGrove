MISSION_START

// *****************************************************************************************
// This is the Space Monkey arcade game, not that you would know that from this file. 
// *****************************************************************************************

GOSUB mission_start_shtr

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_shtr_failed
ENDIF

exit:

DO_FADE 500 FADE_IN
WHILE GET_FADING_STATUS
WAIT 0
ENDWHILE
GOSUB mission_cleanup_shtr

MISSION_END
 
// variables *******************************************************************************

	VAR_INT unk_5566
	VAR_INT shtr_lstickx shtr_lsticky shtr_rstickx shtr_rsticky
	VAR_INT shtr_temp
	VAR_FLOAT shtr_tempF

// sprites

	// coordinates
		VAR_FLOAT shtr_plyr_x shtr_plyr_y shtr_plyr_col_x shtr_plyr_col_y
		
	// projectile stuff
		VAR_INT shtr_button1 shtr_player_alive shtr_heat_bar 
		VAR_INT shtr_reload_start shtr_reload_end shtr_reload_diff
	    
	// enemy stuff
		VAR_FLOAT shtr_enemy_col_x shtr_enemy_col_y

  	// parallax background 
		VAR_FLOAT shtr_layer_x shtr_origin shtr_layer_scroll_speed_top shtr_layer_scroll_speed_bottom shtr_fire_speed shtr_background_pos 
		VAR_INT shtr_sprite 
	// explosion timers
		VAR_INT shtr_start_time shtr_end_time shtr_time_diff  
		VAR_INT shtr_exp_start[16] shtr_exp_end[16] shtr_exp_diff[16]
		VAR_INT shtr_start_time_hit shtr_end_time_hit shtr_time_diff_hit
		
	// front end
		VAR_INT shtr_front_end shtr_lives shtr_score shtr_hi_score shtr_button_pressed
	// enemy spawn crap
		VAR_FLOAT shtr_range_start shtr_range_end 
		VAR_INT shtr_respawn shtr_respawn_time
	// arrayzzzz
		VAR_INT shtr_current_projectile shtr_current_projectile_fired shtr_projectile_alive[16]
		VAR_FLOAT shtr_projectile_x[16] shtr_projectile_y[16] shtr_projectile_origin[16] shtr_projectile_col_x shtr_projectile_col_y 

		VAR_INT shtr_up_projectile_alive[16]
		VAR_FLOAT shtr_up_projectile_x[16] shtr_up_projectile_y[16] shtr_up_projectile_origin[16]
		
		VAR_INT shtr_down_projectile_alive[16]
		VAR_FLOAT shtr_down_projectile_x[16] shtr_down_projectile_y[16] shtr_down_projectile_origin[16]   shtr_projectile_size
		VAR_FLOAT shtr_shot_angle  shtr_sin_shot_angle shtr_cos_shot_angle	shtr_x_add shtr_y_add


		VAR_INT shtr_current_enemy shtr_enemy_alive[16] shtr_random
		VAR_FLOAT shtr_enemy_x[16] shtr_enemy_y[16] shtr_enemy_origin[16] shtr_enemy_speed[16] 

	// sine wave stuff
		VAR_FLOAT shtr_sin_multiplier shtr_sin_cycle_range shtr_sin_degree_input shtr_sin_degree_output shtr_initial_y shtr_gen_y
		VAR_FLOAT shtr_gen_lo_y shtr_gen_hi_y shtr_amplitude
	// Pickups
		VAR_INT shtr_pickup_counter	shtr_pickup_collected shtr_pickup_current shtr_pickup_move_switch
		VAR_FLOAT shtr_pickup_x shtr_pickup_y 
		VAR_INT shtr_speed_pickup shtr_auto_pickup shtr_multi_pickup
		
		VAR_INT unk_5957

	// Enemy firing stuff
		VAR_INT shtr_fire_alive
		
		VAR_FLOAT unk_5960 unk_5961 unk_5962

		VAR_FLOAT shtr_enemy_shot_speed shtr_enemy_shot_x[8] shtr_enemy_shot_y[8] 
		VAR_INT shtr_enemy_shot_alive[8] shtr_shot
		VAR_INT shtr_help_diff shtr_help_end shtr_help_start	

	// ---- Hi Score Stuff
		VAR_TEXT_LABEL $shtr_char[37] 
		VAR_INT shtr_chars shtr_press
		VAR_INT shtr_draw_highlight

		VAR_INT shtr_hi_loop
		VAR_FLOAT shtr_hi_1_x[10] shtr_hi_2_x[10] shtr_hi_3_x[10] shtr_hi_4_x[10] 
		VAR_FLOAT shtr_hi_1_y[10] shtr_hi_2_y[10] shtr_hi_3_y[10] shtr_hi_4_y[10] 
		VAR_INT shtr_ranking shtr_score_update shtr_score_check shtr_destination_slot shtr_source_slot
		VAR_INT shtr_on_table shtr_level shtr_write
		VAR_INT shtr_played_before shtr_current shtr_num_gen shtr_used[30]

// mission start ***************************************************************************
mission_start_shtr:

flag_player_on_mission = 1

	IF flag_shtr_passed_1stime = 0
    	REGISTER_MISSION_GIVEN
	ENDIF


SCRIPT_NAME shtr

WAIT 0

SHUT_ALL_CHARS_UP TRUE

GET_CHAR_COORDINATES scplayer x y z

CLEAR_AREA x y z 50.0 0


SET_MUSIC_DOES_FADE FALSE
LOAD_MISSION_TEXT SHTR
LOAD_MISSION_AUDIO 4 SOUND_BANK_GOGO
WHILE NOT HAS_MISSION_AUDIO_LOADED 4
WAIT 0
ENDWHILE

FREEZE_CHAR_POSITION scplayer TRUE
SET_PLAYER_CONTROL player1 OFF
GET_CHAR_COORDINATES scplayer x y z
CLEAR_AREA x y z 25.0 TRUE


USE_TEXT_COMMANDS TRUE

LOAD_TEXTURE_DICTIONARY ld_shtr

LOAD_SPRITE 1  splsh//monktitle
LOAD_SPRITE 2  bstars//backstars
LOAD_SPRITE 3  fstar//frontstar
LOAD_SPRITE 4  hi_a//highlighteda
LOAD_SPRITE 5  hi_b//highlightedb
LOAD_SPRITE 6  hi_c//highlightedc
LOAD_SPRITE 7  un_a//unhighlighteda
LOAD_SPRITE 8  un_b//unhighlightedb
LOAD_SPRITE 9  un_c//unhighlightedc
LOAD_SPRITE 10 ship//monkeyship
LOAD_SPRITE 11 fire//playerfire
LOAD_SPRITE 12 ufo//ufo
LOAD_SPRITE 13 kami//suicideufo
LOAD_SPRITE	14 nmef//enemyfire
LOAD_SPRITE	15 ex1//explm11
LOAD_SPRITE	16 ex2//explm2
LOAD_SPRITE	17 ex3//explm5
LOAD_SPRITE	18 ex4//explm8
LOAD_SPRITE	19 cbarl//coldgunleft
LOAD_SPRITE	20 cbarm//coldgunmid
LOAD_SPRITE	21 cbarr//coldgunright
LOAD_SPRITE	22 hbarl//hotgunleft
LOAD_SPRITE	23 hbarm//hotgunmid
LOAD_SPRITE	24 hbarr//hotgunright
LOAD_SPRITE	25 tvcorn//hotgunleft
LOAD_SPRITE	26 tvl//hotgunmid
LOAD_SPRITE	27 tvr//hotgunright
LOAD_SPRITE	28 pa//Pickup Autofire
LOAD_SPRITE	29 pm2//Pickup Multi 2
LOAD_SPRITE	30 pm3//Pickup Multi 3
LOAD_SPRITE	31 ps1//Pickup Fire Speed 1
LOAD_SPRITE	32 ps2//Pickup Fire Speed 2
LOAD_SPRITE	33 ps3//Pickup Fire Speed 3

shtr_restart: 
    
	GOSUB shtr_init
    GOSUB shtr_hiscore_init
    SET_PED_DENSITY_MULTIPLIER 0.0
    SET_CAR_DENSITY_MULTIPLIER 0.0

	DRAW_SPRITE 25 160.0 112.0 320.0 224.0 150 150 150 255 
	DRAW_SPRITE 25 480.0 112.0 -320.0 224.0 150 150 150 255 
	DRAW_SPRITE 25 480.0 336.0 -320.0 -224.0 150 150 150 255 
	DRAW_SPRITE 25 160.0 336.0 320.0 -224.0 150 150 150 255
	
// main loop *******************************************************************************

{
timerb = 0
mission_shtr_loop:
    
	WAIT 0

	// draw parallax layers
	shtr_origin = shtr_background_pos
	shtr_sprite = 2
	shtr_layer_scroll_speed_top = 1.0 // speed up??
	GOSUB draw_bckgrnd_layer_top
	shtr_layer_scroll_speed_bottom = 1.0
	GOSUB draw_bckgrnd_layer_bottom
	shtr_background_pos = shtr_origin
	DO_FADE 0 FADE_IN
	GET_POSITION_OF_ANALOGUE_STICKS PAD1 shtr_lstickx shtr_lsticky shtr_rstickx shtr_rsticky


// front end
fe:


IF shtr_button_pressed = 1
	IF NOT IS_BUTTON_PRESSED PAD1 DPADUP
	AND NOT IS_BUTTON_PRESSED PAD1 DPADDOWN
	AND shtr_lsticky = 0
		shtr_button_pressed = 0
	ENDIF
ENDIF

IF shtr_front_end = 1 // play
	DRAW_SPRITE 1 320.0 164.0 386.0 192.0 150 150 150 255
	DRAW_SPRITE 4 320.0 312.0 64.0 32.0 150 150 150 255
	DRAW_SPRITE 8 320.0 344.0 128.0 32.0 150 150 150 255
	DRAW_SPRITE 9 320.0 376.0 64.0 32.0 150 150 150 255
	
	IF IS_BUTTON_PRESSED PAD1 CROSS
		REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_ACCEPT
		REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_TRACK_START
		GET_GAME_TIMER shtr_reload_start
		GET_GAME_TIMER shtr_help_start
		shtr_front_end = 0
	ENDIF
ENDIF

IF shtr_front_end = 2 // Hi Score
	DRAW_SPRITE 1 320.0 164.0 386.0 192.0 150 150 150 255
	DRAW_SPRITE 7 320.0 312.0 64.0 32.0 150 150 150 255
	DRAW_SPRITE 5 320.0 344.0 128.0 32.0 150 150 150 255
	DRAW_SPRITE 9 320.0 376.0 64.0 32.0 150 150 150 255
	
	IF IS_BUTTON_PRESSED PAD1 CROSS
	    REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_ACCEPT
		GET_GAME_TIMER shtr_help_start
		shtr_on_table = 4
		shtr_front_end = 4
	ENDIF
ENDIF

IF shtr_front_end = 3 // Exit
	DRAW_SPRITE 1 320.0 164.0 386.0 192.0 150 150 150 255
	DRAW_SPRITE 7 320.0 312.0 64.0 32.0 150 150 150 255
	DRAW_SPRITE 8 320.0 344.0 128.0 32.0 150 150 150 255
	DRAW_SPRITE 6 320.0 376.0 64.0 32.0 150 150 150 255
	
	IF IS_BUTTON_PRESSED PAD1 CROSS
    	REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_DECLINE
		DO_FADE 0 FADE_OUT
		GOTO exit
	ENDIF
ENDIF

IF shtr_button_pressed = 0
	IF shtr_front_end = 1
		
		IF IS_BUTTON_PRESSED PAD1 DPADUP
		OR shtr_lsticky < -64
			shtr_front_end = 3
			shtr_button_pressed = 1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_SELECT
			GOTO fe // delay / flicker without this
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 DPADDOWN
		OR shtr_lsticky > 64
			shtr_front_end = 2
			shtr_button_pressed = 1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_SELECT
			GOTO fe // delay / flicker without this
		ENDIF
	ENDIF
	
	IF shtr_front_end = 2
		
		IF IS_BUTTON_PRESSED PAD1 DPADUP
		OR shtr_lsticky < -64
			shtr_front_end = 1
			shtr_button_pressed = 1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_SELECT
			GOTO fe // delay / flicker without this
		ENDIF
		
		IF IS_BUTTON_PRESSED PAD1 DPADDOWN
		OR shtr_lsticky > 64
			shtr_front_end = 3
			shtr_button_pressed = 1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_SELECT
			GOTO fe // delay / flicker without this
		ENDIF
	ENDIF

	IF shtr_front_end = 3
		
		IF IS_BUTTON_PRESSED PAD1 DPADUP
		OR shtr_lsticky < -64
			shtr_front_end = 2
			shtr_button_pressed = 1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_SELECT
			GOTO fe // delay / flicker without this
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 DPADDOWN
		OR shtr_lsticky > 64
			shtr_front_end = 1
			shtr_button_pressed = 1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_SELECT
			GOTO fe // delay / flicker without this
		ENDIF
	ENDIF
ENDIF

// begin game

IF shtr_front_end = 0

// Player movement ------------------------------------------------------------
	GET_POSITION_OF_ANALOGUE_STICKS PAD1 shtr_lstickx shtr_lsticky shtr_rstickx shtr_rsticky
	// If player dies
	IF shtr_player_alive = 0
		GET_GAME_TIMER shtr_start_time
		IF shtr_lives > 0
			shtr_lives --
			shtr_range_start = 2.0
			shtr_range_end = 4.0
			shtr_heat_bar = 5
			shtr_player_alive = 2
			shtr_fire_alive = 0
		ENDIF
	ENDIF
	
	// draw player's ship every frame
	IF shtr_player_alive = 1
		DRAW_SPRITE 10 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 255
	ENDIF
	
	
	IF shtr_player_alive = 1
	OR shtr_player_alive = 3
		shtr_temp = shtr_lstickx / 20
		shtr_tempF =# shtr_temp
		shtr_plyr_x +=@ shtr_tempF
		shtr_temp = shtr_lsticky / 20
		shtr_tempF =# shtr_temp
		shtr_plyr_y +=@ shtr_tempF
	ENDIF

	// Move the player's ship.

	IF shtr_player_alive = 1
	OR shtr_player_alive = 3

		IF IS_BUTTON_PRESSED PAD1 DPADUP
			shtr_plyr_y -=@ 6.0
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 DPADDOWN
			shtr_plyr_y +=@ 6.0
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 DPADLEFT
			shtr_plyr_x -=@ 6.0
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 DPADRIGHT
			shtr_plyr_x +=@ 6.0
		ENDIF
	ENDIF		


	// set play area bounding 
	IF shtr_plyr_x < 80.0
		shtr_plyr_x = 80.0	 // shift this along?????
	ENDIF
	IF shtr_plyr_x > 560.0
		shtr_plyr_x = 560.0
	ENDIF
	IF shtr_plyr_y < 80.0
		shtr_plyr_y = 80.0
	ENDIF
	IF shtr_plyr_y > 368.0
		shtr_plyr_y = 368.0
	ENDIF
// ----------------------------------------------------------------------------



// Firing at the enemy --------------------------------------------------------
	IF shtr_button1 = 1
		
		GET_GAME_TIMER shtr_reload_end
		shtr_reload_diff = shtr_reload_end - shtr_reload_start
		
		IF shtr_auto_pickup	= 0
			
			IF NOT IS_BUTTON_PRESSED PAD1 CROSS
				IF shtr_reload_diff > shtr_speed_pickup
					shtr_button1 = 0
				ENDIF
			ENDIF
		
		ELSE
			IF NOT IS_BUTTON_PRESSED PAD1 CROSS
			OR shtr_reload_diff > shtr_speed_pickup
				shtr_button1 = 0
			ENDIF
		ENDIF
	ENDIF

	IF shtr_heat_bar < 7
		shtr_heat_bar = 7
	ENDIF
	shtr_heat_bar -= 7

	IF shtr_player_alive = 1
	OR shtr_player_alive = 3
		IF shtr_heat_bar < 1000
		   	IF shtr_button1 = 0
				
				IF IS_BUTTON_PRESSED PAD1 CROSS
				
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_PLAYER_FIRE
					COS shtr_shot_angle	shtr_cos_shot_angle
					shtr_x_add = shtr_fire_speed * shtr_cos_shot_angle
					SIN shtr_shot_angle shtr_sin_shot_angle
					shtr_y_add = shtr_fire_speed * shtr_sin_shot_angle
					
					IF shtr_projectile_alive[shtr_current_projectile_fired] = 0
						shtr_projectile_origin[shtr_current_projectile_fired] = shtr_plyr_x 
						shtr_projectile_y[shtr_current_projectile_fired] = shtr_plyr_y
						shtr_projectile_x[shtr_current_projectile_fired] = shtr_projectile_origin[shtr_current_projectile_fired] + 16.0
						shtr_projectile_alive[shtr_current_projectile_fired] = 1
					ENDIF
					
					IF shtr_multi_pickup = 1
					OR shtr_multi_pickup = 2
						IF shtr_up_projectile_alive[shtr_current_projectile_fired] = 0
							shtr_up_projectile_origin[shtr_current_projectile_fired] = shtr_plyr_x 
							shtr_up_projectile_y[shtr_current_projectile_fired] = shtr_plyr_y
							shtr_up_projectile_x[shtr_current_projectile_fired] = shtr_up_projectile_origin[shtr_current_projectile_fired] + 16.0
							shtr_up_projectile_alive[shtr_current_projectile_fired] = 1
						ENDIF
					ENDIF
					
					IF shtr_multi_pickup = 2
						IF shtr_down_projectile_alive[shtr_current_projectile_fired] = 0
							shtr_down_projectile_origin[shtr_current_projectile_fired] = shtr_plyr_x 
							shtr_down_projectile_y[shtr_current_projectile_fired] = shtr_plyr_y
							shtr_down_projectile_x[shtr_current_projectile_fired] = shtr_down_projectile_origin[shtr_current_projectile_fired] + 16.0
							shtr_down_projectile_alive[shtr_current_projectile_fired] = 1
						ENDIF
					ENDIF
					
					GET_GAME_TIMER shtr_reload_start
					
					shtr_current_projectile_fired++
					shtr_button1 = 1
					
					IF shtr_current_projectile_fired = 16
						shtr_current_projectile_fired = 0
					ENDIF
			   	ENDIF
			ENDIF
		ENDIF
	ENDIF
	GOSUB shtr_projectile_movement
	GOSUB shtr_collision
// ----------------------------------------------------------------------------

	IF shtr_pickup_collected = 0
		DRAW_SPRITE shtr_pickup_current shtr_pickup_x shtr_pickup_y 32.0 32.0 150 150 150 255
		IF shtr_pickup_move_switch = 0
			IF shtr_pickup_y < 368.0
			AND shtr_pickup_x > 80.0
				shtr_pickup_x += -1.0
				shtr_pickup_y += 1.0
			ELSE
				GENERATE_RANDOM_INT_IN_RANGE 0 4 shtr_pickup_move_switch
			ENDIF
		ENDIF
		IF shtr_pickup_move_switch = 1
			IF shtr_pickup_x > 80.0
			AND	shtr_pickup_y > 80.0
				shtr_pickup_x += -1.0
				shtr_pickup_y += -1.0
			ELSE
				GENERATE_RANDOM_INT_IN_RANGE 0 4 shtr_pickup_move_switch
			ENDIF
		ENDIF
		IF shtr_pickup_move_switch = 2
			IF shtr_pickup_y > 80.0
			AND	shtr_pickup_x < 560.0
				shtr_pickup_x += 1.0
				shtr_pickup_y += -1.0
			ELSE
				GENERATE_RANDOM_INT_IN_RANGE 0 4 shtr_pickup_move_switch
			ENDIF
		ENDIF
		IF shtr_pickup_move_switch = 3
			IF shtr_pickup_x < 560.0
			AND	shtr_pickup_y < 368.0
				shtr_pickup_x += 1.0
				shtr_pickup_y += 1.0
			ELSE
				GENERATE_RANDOM_INT_IN_RANGE 0 4 shtr_pickup_move_switch
			ENDIF
		ENDIF

		IF DO_2D_RECTANGLES_COLLIDE shtr_plyr_x shtr_plyr_y 32.0 32.0 shtr_pickup_x shtr_pickup_y 32.0 32.0
			IF shtr_range_end < 6.5
				shtr_range_start += 0.5
				shtr_range_end += 0.5
			ENDIF
			shtr_pickup_x = 0.0
			shtr_pickup_y = 0.0
			shtr_pickup_move_switch = 0
			IF shtr_pickup_current = 28 // Auto Fire
				shtr_auto_pickup = 1
				shtr_pickup_collected = 2
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_ACCEPT
			ENDIF
			IF shtr_pickup_current = 33 // Fire Speed #3
				shtr_speed_pickup = 50
				shtr_pickup_current = 28
				shtr_pickup_collected = 1
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_ACCEPT
			ENDIF
		   	IF shtr_pickup_current = 30 // Multi Fire #2 -- Three Way
				shtr_multi_pickup = 2
				shtr_pickup_current = 33
				shtr_pickup_collected = 1
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_ACCEPT
			ENDIF
		   	IF shtr_pickup_current = 32 // Fire Speed #2
				shtr_speed_pickup = 100
				shtr_pickup_current = 30
				shtr_pickup_collected = 1
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_ACCEPT
			ENDIF
		   	IF shtr_pickup_current = 29 // Multi Fire #1 -- Two Way
				shtr_multi_pickup = 1
				shtr_pickup_current = 32
				shtr_pickup_collected = 1
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_ACCEPT
			ENDIF
		   	IF shtr_pickup_current = 31 // Fire Speed #1
				shtr_speed_pickup = 150
				shtr_pickup_current = 29
				shtr_pickup_collected = 1
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_ACCEPT
			ENDIF
		ENDIF
	ENDIF
			


// Enemies and related crap ---------------------------------------------------

// Enemies spawn --------------------------------------------------------------
	
	IF shtr_respawn = 0
 		IF timerb > 500
			IF shtr_enemy_alive[0] = 0
				shtr_current_enemy = 0
				IF shtr_amplitude < 60.0
					shtr_amplitude += 5.0
				ENDIF
				GOSUB shtr_enemy_gen
				timerb = 0
				shtr_respawn++
			ENDIF
		ENDIF
	ENDIF
	IF shtr_respawn > 0
	AND shtr_respawn < 8
		shtr_current_enemy = shtr_respawn 
		GOSUB shtr_enemy_respawn
	ENDIF
	IF shtr_respawn = 8
		IF shtr_enemy_alive[0] = 0
		AND shtr_enemy_alive[1] = 0
		AND shtr_enemy_alive[2] = 0
		AND shtr_enemy_alive[3] = 0
			IF shtr_enemy_alive[4] = 0
			AND shtr_enemy_alive[5] = 0
			AND shtr_enemy_alive[6] = 0
			AND shtr_enemy_alive[7] = 0
				shtr_pickup_counter = 0
				shtr_random = 0
				shtr_respawn = 0
			ENDIF
		ENDIF
	ENDIF
// ----------------------------------------------------------------------------

// Enemies move ---------------------------------------------------------------
	GOSUB shtr_enemy_movement


// Enemies explode ------------------------------------------------------------
	GOSUB shtr_enemy_exp


// Enemies fire ------------------------------------------------------------
	GOSUB shtr_enemy_fire


// Player explodes ------------------------------------------------------------
	IF shtr_player_alive = 2
		IF shtr_fire_alive = 2
			GET_GAME_TIMER shtr_end_time
			shtr_time_diff = shtr_end_time - shtr_start_time
		   	IF shtr_time_diff >= 0
			AND shtr_time_diff < 100 
				DRAW_SPRITE	15 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 255
			ENDIF
			IF shtr_time_diff >= 100
			AND shtr_time_diff < 200 
				DRAW_SPRITE	16 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 255
			ENDIF
		   	IF shtr_time_diff >= 200
			AND shtr_time_diff < 300 
				DRAW_SPRITE	17 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 255
			ENDIF
		   	IF shtr_time_diff >= 300
			AND shtr_time_diff < 400 
				DRAW_SPRITE	18 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 255
			ENDIF
			IF shtr_time_diff > 499
				shtr_plyr_x = 80.0
				shtr_plyr_y = 224.0
				shtr_player_alive = 3
				shtr_fire_alive = 0
				shtr_pickup_counter = 0
				shtr_pickup_collected = 1
				shtr_pickup_current = 31
				shtr_speed_pickup = 250
				shtr_auto_pickup = 0 
				shtr_multi_pickup = 0
				shtr_pickup_move_switch = 0
				GET_GAME_TIMER shtr_start_time
			ENDIF
		ELSE
			GET_GAME_TIMER shtr_end_time
			shtr_time_diff = shtr_end_time - shtr_start_time
		   	IF shtr_time_diff >= 0
			AND shtr_time_diff < 100 
				DRAW_SPRITE	15 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 255
			ENDIF
			IF shtr_time_diff >= 100
			AND shtr_time_diff < 200 
				DRAW_SPRITE	16 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 255
			ENDIF
		   	IF shtr_time_diff >= 200
			AND shtr_time_diff < 300 
				DRAW_SPRITE	17 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 255
			ENDIF
		   	IF shtr_time_diff >= 300
			AND shtr_time_diff < 400 
				DRAW_SPRITE	18 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 255
			ENDIF
			IF shtr_time_diff > 499
				GET_GAME_TIMER shtr_start_time
				shtr_plyr_x = 80.0
				shtr_plyr_y = 224.0
				shtr_pickup_counter = 0
				shtr_pickup_collected = 1
				shtr_pickup_current = 31
				shtr_speed_pickup = 250
				shtr_auto_pickup = 0 
				shtr_multi_pickup = 0
				shtr_pickup_move_switch = 0
				shtr_player_alive = 3
			ENDIF
		ENDIF
	ENDIF
// ----------------------------------------------------------------------------

// Player respawns and flashes ------------------------------------------------
	IF shtr_player_alive = 3
		GET_GAME_TIMER shtr_end_time
		shtr_time_diff = shtr_end_time - shtr_start_time
	   	IF shtr_time_diff >= 0
		AND shtr_time_diff < 100
			DRAW_SPRITE 10 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 150
		ENDIF
		IF shtr_time_diff >= 200
		AND shtr_time_diff < 300
			DRAW_SPRITE 10 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 150
		ENDIF
		IF shtr_time_diff >= 400
		AND shtr_time_diff < 500
			DRAW_SPRITE 10 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 150
		ENDIF
		IF shtr_time_diff >= 600
		AND shtr_time_diff < 700
			DRAW_SPRITE 10 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 150
		ENDIF
		IF shtr_time_diff >= 800
		AND shtr_time_diff < 900
			DRAW_SPRITE 10 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 150
		ENDIF
		IF shtr_time_diff >= 1000
		AND shtr_time_diff < 1100
			DRAW_SPRITE 10 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 150
		ENDIF
		IF shtr_time_diff >= 1200
		AND shtr_time_diff < 1300
			DRAW_SPRITE 10 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 150
		ENDIF
		IF shtr_time_diff >= 1400
		AND shtr_time_diff < 1500
			DRAW_SPRITE 10 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 150
		ENDIF
		IF shtr_time_diff >= 1600
		AND shtr_time_diff < 1700
			DRAW_SPRITE 10 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 150
		ENDIF
		IF shtr_time_diff >= 1800
		AND shtr_time_diff < 1900
			DRAW_SPRITE 10 shtr_plyr_x shtr_plyr_y 32.0 32.0 150 150 150 150
		ENDIF
		IF shtr_time_diff >= 2000
			shtr_player_alive = 1
		ENDIF
	ENDIF
// ----------------------------------------------------------------------------
// GUI
	IF shtr_score > shtr_hi_score
		shtr_hi_score = shtr_score
		unk_5566 = 1
	ENDIF
	GOSUB shtr_text_gosub
	SET_TEXT_SCALE 0.6 1.2
	SET_TEXT_RIGHT_JUSTIFY ON
    DISPLAY_TEXT_WITH_NUMBER 560.0 48.0 SHTR_2b shtr_lives // lives left  
	GOSUB shtr_text_gosub
	SET_TEXT_CENTRE OFF
	SET_TEXT_JUSTIFY ON
	SET_TEXT_SCALE 0.6 1.2
	DISPLAY_TEXT_WITH_NUMBER 80.0 48.0 SHTR_2d shtr_score	// 1up score
	GOSUB shtr_text_gosub
	SET_TEXT_SCALE 0.6 1.2
	DISPLAY_TEXT_WITH_NUMBER 320.0 48.0 SHTR_2c shtr_hi_score // hi score
ENDIF

// Game Over ------------------------------------------------------------------

IF shtr_front_end = 0
	IF shtr_player_alive = 0
		IF shtr_lives = 0
			shtr_front_end = 4
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_TRACK_STOP
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_GAME_OVER
			shtr_on_table = 0
		ENDIF
	ENDIF
ENDIF

IF shtr_front_end = 4
	IF shtr_on_table = 0
		GOSUB shtr_text_gosub
		SET_TEXT_SCALE 2.0 4.0
		DISPLAY_TEXT 320.0 196.0 SHTR_3b // game over
		
		GET_GAME_TIMER shtr_start_time
		shtr_on_table = 1
	ENDIF
	IF shtr_on_table = 1
		GOSUB shtr_text_gosub
		SET_TEXT_SCALE 2.0 4.0
		DISPLAY_TEXT 320.0 196.0 SHTR_3b
		GET_GAME_TIMER shtr_end_time
		shtr_time_diff = shtr_end_time - shtr_start_time
		IF shtr_time_diff > 5000
			shtr_on_table = 2
		ENDIF
	ENDIF
	IF shtr_on_table = 2
		IF shtr_score < shtr_hi_s[9]  
			shtr_on_table = 4
		ELSE
			GOSUB shtr_check_scores
		ENDIF
	ENDIF
	IF shtr_on_table = 3
		GOSUB shtr_write_name
		GOSUB shtr_draw_scores
	ENDIF
	IF shtr_on_table = 4
		GOSUB shtr_draw_scores
	ENDIF
ENDIF

// Quit to front end ----------------------------------------------------------
IF shtr_front_end = 4
	IF IS_BUTTON_PRESSED PAD1 TRIANGLE
	AND NOT shtr_on_table = 1
		REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_DECLINE
		GOTO shtr_restart
	ENDIF
ENDIF

DRAW_SPRITE 25 160.0 112.0 320.0 224.0 150 150 150 255 
DRAW_SPRITE 25 480.0 112.0 -320.0 224.0 150 150 150 255 
DRAW_SPRITE 25 480.0 336.0 -320.0 -224.0 150 150 150 255 
DRAW_SPRITE 25 160.0 336.0 320.0 -224.0 150 150 150 255

IF shtr_front_end = 0
ENDIF

IF shtr_front_end = 4
	IF shtr_on_table = 4
		GET_GAME_TIMER shtr_help_end 
		shtr_help_diff = shtr_help_end - shtr_help_start

		IF shtr_help_diff < 10000
			DRAW_WINDOW 35.0 15.0 200.0 45.0 dummy SWIRLS_NONE
			SET_TEXT_CENTRE OFF
			SET_TEXT_WRAPX 230.0
			SET_TEXT_FONT FONT_STANDARD
			SET_TEXT_SCALE 0.5 1.8
			DISPLAY_TEXT 40.0 20.0 SH_BCK 
		ENDIF
	ENDIF
ENDIF

GOTO mission_shtr_loop


	
mission_shtr_failed:
PRINT_BIG M_FAIL 5000 1
RETURN

mission_shtr_passed:
RETURN

mission_cleanup_shtr:

CLEAR_THIS_PRINT BUSY
REMOVE_TEXTURE_DICTIONARY 
CLEAR_MISSION_AUDIO 4
SHUT_ALL_CHARS_UP FALSE
SET_MUSIC_DOES_FADE TRUE
REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_TRACK_STOP
IF IS_PLAYER_PLAYING player1
	IF LOCATE_CHAR_ON_FOOT_3D scplayer shooter1X shooter1Y shooter1Z 2.0 2.0 2.0	0
		y = shooter1Y + 2.0
		SET_CHAR_COORDINATES scplayer shooter1X  y shooter1Z
	ENDIF
	FREEZE_CHAR_POSITION scplayer FALSE
	SET_PLAYER_CONTROL player1 ON
	
ENDIF
flag_player_on_mission = 0

MISSION_HAS_FINISHED
RETURN

// Gosubs **********************************************************************************

// Onscreen text --------------------------------------------------------------
shtr_text_gosub:
	SET_TEXT_COLOUR 255 255 255 255
	SET_TEXT_SCALE 1.0 2.0
	SET_TEXT_CENTRE ON
	SET_TEXT_WRAPX 640.0
RETURN

// Draw background ------------------------------------------------------------
draw_bckgrnd_layer_top:
	shtr_layer_x = shtr_origin - 256.0
	DRAW_SPRITE	shtr_sprite shtr_layer_x 336.0 256.0 256.0 150 150 150 255
	shtr_layer_x = shtr_origin
	DRAW_SPRITE	shtr_sprite shtr_layer_x 336.0 256.0 256.0 150 150 150 255
	shtr_layer_x = shtr_origin + 256.0
	DRAW_SPRITE	shtr_sprite shtr_layer_x 336.0 256.0 256.0 150 150 150 255
	shtr_layer_x = shtr_origin + 512.0
	DRAW_SPRITE	shtr_sprite shtr_layer_x 336.0 256.0 256.0 150 150 150 255

	shtr_origin -=@ shtr_layer_scroll_speed_top
	IF shtr_origin < 0.0
		shtr_origin += 256.0
	ENDIF
RETURN

draw_bckgrnd_layer_bottom:
	shtr_layer_x = shtr_origin - 256.0
	DRAW_SPRITE	shtr_sprite shtr_layer_x 112.0 256.0 256.0 150 150 150 255
	shtr_layer_x = shtr_origin
	DRAW_SPRITE	shtr_sprite shtr_layer_x 112.0 256.0 256.0 150 150 150 255
	shtr_layer_x = shtr_origin + 256.0
	DRAW_SPRITE	shtr_sprite shtr_layer_x 112.0 256.0 256.0 150 150 150 255
	shtr_layer_x = shtr_origin + 512.0
	DRAW_SPRITE	shtr_sprite shtr_layer_x 112.0 256.0 256.0 150 150 150 255

	shtr_origin -=@ shtr_layer_scroll_speed_bottom
	IF shtr_origin < 0.0
		shtr_origin += 256.0
	ENDIF
RETURN

// Enemies --------------------------------------------------------------------

shtr_enemy_respawn:
	IF timerb > shtr_respawn_time
		IF shtr_enemy_alive[shtr_current_enemy] = 0
			shtr_enemy_x[shtr_current_enemy] = shtr_enemy_origin[0]
			shtr_enemy_speed[shtr_current_enemy] = shtr_enemy_speed[0]
			shtr_enemy_alive[shtr_current_enemy] = 1
			timerb = 0
			shtr_respawn++
		ENDIF
	ENDIF
RETURN

shtr_enemy_exp:
	shtr_current_enemy = 0
	WHILE shtr_current_enemy < 8
		IF shtr_enemy_alive[shtr_current_enemy] = 2
			GET_GAME_TIMER shtr_exp_end[shtr_current_enemy]
			shtr_exp_diff[shtr_current_enemy] = shtr_exp_end[shtr_current_enemy] - shtr_exp_start[shtr_current_enemy]
		   	IF shtr_exp_diff[shtr_current_enemy] >= 0
			AND shtr_exp_diff[shtr_current_enemy] < 100 
				DRAW_SPRITE	15 shtr_enemy_x[shtr_current_enemy] shtr_enemy_y[shtr_current_enemy] 32.0 32.0 150 150 150 255
			ENDIF
			IF shtr_exp_diff[shtr_current_enemy] >= 100
			AND shtr_exp_diff[shtr_current_enemy] < 200 
				DRAW_SPRITE	16 shtr_enemy_x[shtr_current_enemy] shtr_enemy_y[shtr_current_enemy] 32.0 32.0 150 150 150 255
			ENDIF
		   	IF shtr_exp_diff[shtr_current_enemy] >= 200
			AND shtr_exp_diff[shtr_current_enemy] < 300 
				DRAW_SPRITE	17 shtr_enemy_x[shtr_current_enemy] shtr_enemy_y[shtr_current_enemy] 32.0 32.0 150 150 150 255
			ENDIF
		   	IF shtr_exp_diff[shtr_current_enemy] >= 300
			AND shtr_exp_diff[shtr_current_enemy] < 400 
				DRAW_SPRITE	18 shtr_enemy_x[shtr_current_enemy] shtr_enemy_y[shtr_current_enemy] 32.0 32.0 150 150 150 255
			ENDIF
			IF shtr_exp_diff[shtr_current_enemy] > 499
				shtr_enemy_x[shtr_current_enemy] = 0.0
				shtr_enemy_y[shtr_current_enemy] = 0.0
				shtr_enemy_alive[shtr_current_enemy] = 0


			ENDIF
		ENDIF
		shtr_current_enemy++
	ENDWHILE
RETURN

shtr_enemy_gen:
	IF shtr_enemy_alive[0] = 0
		IF shtr_random = 0
			GENERATE_RANDOM_FLOAT_IN_RANGE 640.0 720.0 shtr_enemy_origin[0]
			shtr_enemy_x[0] = shtr_enemy_origin[0]
			GENERATE_RANDOM_FLOAT_IN_RANGE 240.0 640.0 shtr_sin_cycle_range
			GENERATE_RANDOM_FLOAT_IN_RANGE 80.0 368.0 shtr_gen_y
			shtr_gen_lo_y = shtr_gen_y - shtr_amplitude 
			IF shtr_gen_lo_y < 80.0
				shtr_gen_y += shtr_amplitude
			ENDIF
			shtr_gen_hi_y = shtr_gen_y + shtr_amplitude 
			IF shtr_gen_hi_y < 80.0
				shtr_gen_y -= shtr_amplitude
			ENDIF
			GENERATE_RANDOM_FLOAT_IN_RANGE shtr_range_start shtr_range_end shtr_enemy_speed[0]
			
			IF shtr_enemy_speed[0] >= 1.0
			AND	shtr_enemy_speed[0] < 1.5
				shtr_respawn_time = 500
			ENDIF
			IF shtr_enemy_speed[0] >= 1.5
			AND	shtr_enemy_speed[0] < 2.0
				shtr_respawn_time = 475
			ENDIF
			IF shtr_enemy_speed[0] >= 2.0
			AND	shtr_enemy_speed[0] < 2.5
				shtr_respawn_time = 450
			ENDIF
			IF shtr_enemy_speed[0] >= 2.5
			AND	shtr_enemy_speed[0] < 3.0
				shtr_respawn_time = 425
			ENDIF
			IF shtr_enemy_speed[0] >= 3.0
			AND	shtr_enemy_speed[0] < 3.5
				shtr_respawn_time = 400
			ENDIF
			IF shtr_enemy_speed[0] >= 3.5
			AND	shtr_enemy_speed[0] < 4.0
				shtr_respawn_time = 375
			ENDIF
			IF shtr_enemy_speed[0] >= 4.0
			AND	shtr_enemy_speed[0] < 4.5
				shtr_respawn_time = 350
			ENDIF
			IF shtr_enemy_speed[0] >= 4.5
			AND	shtr_enemy_speed[0] < 5.0
				shtr_respawn_time = 325
			ENDIF
			IF shtr_enemy_speed[0] >= 5.0
			AND	shtr_enemy_speed[0] < 5.5
				shtr_respawn_time = 300
			ENDIF
			IF shtr_enemy_speed[0] >= 5.5
			AND	shtr_enemy_speed[0] < 6.0
				shtr_respawn_time = 275
			ENDIF
			IF shtr_enemy_speed[0] >= 6.0
			AND	shtr_enemy_speed[0] < 6.5
				shtr_respawn_time = 250
			ENDIF
			IF shtr_enemy_speed[0] >= 6.5
			AND	shtr_enemy_speed[0] < 7.0
				shtr_respawn_time = 225
			ENDIF
			IF shtr_enemy_speed[0] >= 7.0
			AND	shtr_enemy_speed[0] < 7.5
				shtr_respawn_time = 200
			ENDIF
			IF shtr_enemy_speed[0] >= 7.5
			AND	shtr_enemy_speed[0] <= 8.0
				shtr_respawn_time = 175
			ENDIF
			GENERATE_RANDOM_INT_IN_RANGE 100 1500 unk_5957
			shtr_enemy_alive[0] = 1
			shtr_random = 1
		ENDIF 
	ENDIF
RETURN

shtr_enemy_movement:
	shtr_current_enemy = 0
	WHILE shtr_current_enemy < 8
		IF shtr_enemy_alive[shtr_current_enemy] = 1
		   	DRAW_SPRITE 12 shtr_enemy_x[shtr_current_enemy] shtr_enemy_y[shtr_current_enemy] 32.0 32.0 150 150 150 255
			shtr_enemy_x[shtr_current_enemy] -=@ shtr_enemy_speed[shtr_current_enemy]
			shtr_sin_multiplier = shtr_enemy_x[shtr_current_enemy] / shtr_sin_cycle_range
			shtr_sin_degree_input = shtr_sin_multiplier * 360.0 
			SIN shtr_sin_degree_input shtr_sin_degree_output 
			shtr_initial_y = shtr_sin_degree_output * shtr_amplitude 
			shtr_enemy_y[shtr_current_enemy] = shtr_initial_y + shtr_gen_y
			IF shtr_enemy_x[shtr_current_enemy] < 0.0
				shtr_enemy_x[shtr_current_enemy] = 0.0
				shtr_enemy_y[shtr_current_enemy] = 0.0
				IF shtr_score > 24
					IF shtr_score = shtr_hi_score
						IF shtr_score > shtr_hi_s[0]
						AND shtr_score > shtr_hi_s[1]
						AND shtr_score > shtr_hi_s[2]
						AND shtr_score > shtr_hi_s[3]
						AND shtr_score > shtr_hi_s[4]
							IF shtr_score > shtr_hi_s[5]
							AND shtr_score > shtr_hi_s[6]
							AND shtr_score > shtr_hi_s[7]
							AND shtr_score > shtr_hi_s[8]
							AND shtr_score > shtr_hi_s[9]
								shtr_hi_score -= 25
							ENDIF
						ENDIF
					ENDIF
					shtr_score -= 25
				ENDIF

				shtr_enemy_alive[shtr_current_enemy] = 0
		 	ENDIF
		ENDIF
		shtr_current_enemy++
	ENDWHILE
RETURN

// Projectiles ----------------------------------------------------------------

shtr_projectile_movement:
	shtr_current_projectile	= 1
	WHILE shtr_current_projectile < 16
		IF shtr_projectile_alive[shtr_current_projectile] = 1
			DRAW_SPRITE 11 shtr_projectile_x[shtr_current_projectile] shtr_projectile_y[shtr_current_projectile] shtr_projectile_size shtr_projectile_size 150 150 150 255
			shtr_projectile_x[shtr_current_projectile] +=@ shtr_fire_speed
			IF shtr_projectile_x[shtr_current_projectile] > 639.0
				shtr_projectile_y[shtr_current_projectile] = 0.0
				shtr_projectile_alive[shtr_current_projectile] = 0
			ENDIF
		ENDIF
		IF shtr_up_projectile_alive[shtr_current_projectile] = 1
			DRAW_SPRITE 11 shtr_up_projectile_x[shtr_current_projectile] shtr_up_projectile_y[shtr_current_projectile] shtr_projectile_size shtr_projectile_size 150 150 150 255
			shtr_up_projectile_x[shtr_current_projectile] +=@ shtr_x_add
			shtr_up_projectile_y[shtr_current_projectile] -=@ shtr_y_add
			IF shtr_up_projectile_x[shtr_current_projectile] > 639.0
				shtr_up_projectile_y[shtr_current_projectile] = 0.0
				shtr_up_projectile_alive[shtr_current_projectile] = 0
			ENDIF
		ENDIF
		IF shtr_down_projectile_alive[shtr_current_projectile] = 1
			DRAW_SPRITE 11 shtr_down_projectile_x[shtr_current_projectile] shtr_down_projectile_y[shtr_current_projectile] shtr_projectile_size shtr_projectile_size 150 150 150 255
			shtr_down_projectile_x[shtr_current_projectile] +=@ shtr_x_add
			shtr_down_projectile_y[shtr_current_projectile] +=@ shtr_y_add
			IF shtr_down_projectile_x[shtr_current_projectile] > 639.0
				shtr_down_projectile_y[shtr_current_projectile] = 0.0
				shtr_down_projectile_alive[shtr_current_projectile] = 0
			ENDIF
		ENDIF
		shtr_current_projectile++
	ENDWHILE
RETURN

shtr_enemy_fire:
	shtr_current_enemy = 0
	WHILE shtr_current_enemy < 8
		IF shtr_enemy_x[shtr_current_enemy] > 320.0
			IF shtr_shot = 0
				IF shtr_enemy_alive[shtr_current_enemy] = 1
					IF shtr_enemy_shot_alive[shtr_current_enemy] = 0
						shtr_enemy_shot_x[shtr_current_enemy] = shtr_enemy_x[shtr_current_enemy]	
						shtr_enemy_shot_y[shtr_current_enemy] = shtr_enemy_y[shtr_current_enemy]
						shtr_enemy_shot_alive[shtr_current_enemy] = 1
						shtr_shot++
						shtr_enemy_shot_speed = shtr_enemy_speed[shtr_current_enemy] * 1.5
						REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_ENEMY_FIRE
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF shtr_shot = 1
			IF shtr_enemy_shot_alive[shtr_current_enemy] = 1
				IF shtr_player_alive = 1
					IF DO_2D_RECTANGLES_COLLIDE shtr_plyr_x shtr_plyr_y 32.0 32.0 shtr_enemy_shot_x[shtr_current_enemy] shtr_enemy_shot_y[shtr_current_enemy] 8.0 8.0
						shtr_enemy_shot_alive[shtr_current_enemy] = 0
						shtr_enemy_shot_x[shtr_current_enemy] = 0.0
						shtr_enemy_shot_y[shtr_current_enemy] = 0.0
						shtr_shot = 0
						shtr_player_alive = 0
					ENDIF
				ENDIF
				DRAW_SPRITE 14 shtr_enemy_shot_x[shtr_current_enemy] shtr_enemy_shot_y[shtr_current_enemy] shtr_projectile_size shtr_projectile_size 150 150 150 255
				shtr_enemy_shot_x[shtr_current_enemy] -=@ shtr_enemy_shot_speed
				IF shtr_enemy_shot_x[shtr_current_enemy] < 0.0
					shtr_enemy_shot_alive[shtr_current_enemy] = 0
					shtr_enemy_shot_x[shtr_current_enemy] = 0.0
					shtr_enemy_shot_y[shtr_current_enemy] = 0.0
					shtr_shot = 0
				ENDIF 
			ENDIF
		ENDIF
		shtr_current_enemy++
	ENDWHILE
RETURN

// Collision ------------------------------------------------------------------
shtr_collision:
	shtr_current_enemy = 0
	WHILE shtr_current_enemy < 8
		IF shtr_enemy_alive[shtr_current_enemy] = 1
			shtr_current_projectile	= 1
			WHILE shtr_current_projectile < 16
				IF DO_2D_RECTANGLES_COLLIDE shtr_projectile_x[shtr_current_projectile] shtr_projectile_y[shtr_current_projectile] shtr_projectile_col_x shtr_projectile_col_y shtr_enemy_x[shtr_current_enemy] shtr_enemy_y[shtr_current_enemy] shtr_enemy_col_x shtr_enemy_col_y
					shtr_score += 100
					shtr_enemy_alive[shtr_current_enemy] = 2
					shtr_projectile_alive[shtr_current_projectile] = 0
					shtr_projectile_x[shtr_current_projectile] = 0.0 
					shtr_projectile_y[shtr_current_projectile] = 0.0
					GET_GAME_TIMER shtr_exp_start[shtr_current_enemy]
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_EXPLOSION
					shtr_pickup_counter++
					IF shtr_pickup_collected = 1
						IF shtr_pickup_counter = 8
							shtr_pickup_x = shtr_enemy_x[shtr_current_enemy]
							shtr_pickup_y = shtr_enemy_y[shtr_current_enemy]
							shtr_pickup_collected = 0
						ELSE
							shtr_pickup_x = 0.0
							shtr_pickup_y = 0.0
						ENDIF
					ENDIF
				ENDIF
				IF DO_2D_RECTANGLES_COLLIDE shtr_up_projectile_x[shtr_current_projectile] shtr_up_projectile_y[shtr_current_projectile] shtr_projectile_col_x shtr_projectile_col_y shtr_enemy_x[shtr_current_enemy] shtr_enemy_y[shtr_current_enemy] shtr_enemy_col_x shtr_enemy_col_y
					shtr_score += 100
					shtr_enemy_alive[shtr_current_enemy] = 2
					shtr_up_projectile_alive[shtr_current_projectile] = 0
					shtr_up_projectile_x[shtr_current_projectile] = 0.0 
					shtr_up_projectile_y[shtr_current_projectile] = 0.0
					GET_GAME_TIMER shtr_exp_start[shtr_current_enemy]
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_EXPLOSION
					shtr_pickup_counter++
					IF shtr_pickup_collected = 1
						IF shtr_pickup_counter = 8
							shtr_pickup_x = shtr_enemy_x[shtr_current_enemy]
							shtr_pickup_y = shtr_enemy_y[shtr_current_enemy]
							shtr_pickup_collected = 0
						ELSE
							shtr_pickup_x = 0.0
							shtr_pickup_y = 0.0
						ENDIF
					ENDIF
				ENDIF
				IF DO_2D_RECTANGLES_COLLIDE shtr_down_projectile_x[shtr_current_projectile] shtr_down_projectile_y[shtr_current_projectile] shtr_projectile_col_x shtr_projectile_col_y shtr_enemy_x[shtr_current_enemy] shtr_enemy_y[shtr_current_enemy] shtr_enemy_col_x shtr_enemy_col_y
					shtr_score += 100
					shtr_enemy_alive[shtr_current_enemy] = 2
					shtr_down_projectile_alive[shtr_current_projectile] = 0
					shtr_down_projectile_x[shtr_current_projectile] = 0.0 
					shtr_down_projectile_y[shtr_current_projectile] = 0.0
					GET_GAME_TIMER shtr_exp_start[shtr_current_enemy]
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_EXPLOSION
					shtr_pickup_counter++
					IF shtr_pickup_collected = 1
						IF shtr_pickup_counter = 8
							shtr_pickup_x = shtr_enemy_x[shtr_current_enemy]
							shtr_pickup_y = shtr_enemy_y[shtr_current_enemy]
							shtr_pickup_collected = 0
						ELSE
							shtr_pickup_x = 0.0
							shtr_pickup_y = 0.0
						ENDIF
					ENDIF
				ENDIF
				shtr_current_projectile++
			ENDWHILE
		ENDIF
		IF shtr_enemy_alive[shtr_current_enemy] = 1
			IF shtr_player_alive = 1
				IF DO_2D_RECTANGLES_COLLIDE shtr_plyr_x shtr_plyr_y 32.0 32.0 shtr_enemy_x[shtr_current_enemy] shtr_enemy_y[shtr_current_enemy] shtr_enemy_col_x shtr_enemy_col_y
					shtr_enemy_alive[shtr_current_enemy] = 2
					GET_GAME_TIMER shtr_exp_start[shtr_current_enemy]
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_EXPLOSION
					shtr_player_alive = 0
			   	ENDIF
			ENDIF
		ENDIF
		shtr_current_enemy++
	ENDWHILE
RETURN

// Hi Score-A-Rama ------------------------------------------------------------

shtr_check_scores: // (c) Graeme 2003
	shtr_ranking = 9
	shtr_score_update = FALSE
	shtr_score_check = TRUE
	// find which slot the new score has to be inserted into
	WHILE shtr_ranking >= 0
	AND shtr_score_check = TRUE
		IF shtr_score > shtr_hi_s[shtr_ranking]
			shtr_ranking--
			shtr_score_update = TRUE
			shtr_on_table = 3
			shtr_draw_highlight = 1
		ELSE
			shtr_score_check = FALSE
	    ENDIF
	ENDWHILE

 	IF shtr_score_update = TRUE
		shtr_ranking++
		// shift the other scores down
		shtr_destination_slot = 9
		shtr_source_slot = 8
		WHILE shtr_source_slot >= shtr_ranking
			$shtr_hi_1[shtr_destination_slot] = $shtr_hi_1[shtr_source_slot] // First letter of player name
			$shtr_hi_2[shtr_destination_slot] = $shtr_hi_2[shtr_source_slot] // Second letter of player name
			$shtr_hi_3[shtr_destination_slot] = $shtr_hi_3[shtr_source_slot] // Third letter of player name 
			shtr_hi_l[shtr_destination_slot] = shtr_hi_l[shtr_source_slot] // Level Number 	
			shtr_hi_s[shtr_destination_slot] = shtr_hi_s[shtr_source_slot] // Score
			shtr_destination_slot--
			shtr_source_slot--
		ENDWHILE
		// write the new score into the correct slot
		shtr_hi_s[shtr_ranking] = shtr_score
		shtr_hi_l[shtr_ranking] = shtr_level
		shtr_write = 0
		shtr_chars = 10 // A
	ENDIF
RETURN

shtr_write_name:
	
	GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
	IF NOT IS_BUTTON_PRESSED PAD1 DPADUP
	AND NOT IS_BUTTON_PRESSED PAD1 DPADDOWN
	AND NOT IS_BUTTON_PRESSED PAD1 DPADLEFT
	AND NOT IS_BUTTON_PRESSED PAD1 DPADRIGHT
	AND NOT IS_BUTTON_PRESSED PAD1 CROSS
		IF LStickY = 0
		AND LStickX = 0
			shtr_press = 0
		ENDIF
	ENDIF

	// Write first letter
	IF shtr_write = 0
		$shtr_hi_1[shtr_ranking] = $shtr_char[shtr_chars]
		$shtr_hi_2[shtr_ranking] = $shtr_char[10]
		$shtr_hi_3[shtr_ranking] = $shtr_char[10]
		IF shtr_press = 0
			
			IF IS_BUTTON_PRESSED PAD1 DPADUP
			OR IS_BUTTON_PRESSED PAD1 DPADRIGHT
			OR LStickX > 100
			OR LStickY < -100
				IF shtr_chars < 36 
					shtr_chars++
				ELSE
					shtr_chars = 0
				ENDIF
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_SELECT
				shtr_press = 1
			ENDIF
			
			IF IS_BUTTON_PRESSED PAD1 DPADDOWN
			OR IS_BUTTON_PRESSED PAD1 DPADLEFT
			OR LStickX < -100
			OR LStickY > 100
				IF shtr_chars > 0
					shtr_chars--
				ELSE
					shtr_chars = 36
				ENDIF
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_SELECT
				shtr_press = 1
			ENDIF
			
			IF IS_BUTTON_PRESSED PAD1 CROSS
				shtr_chars = 10
				shtr_write = 1
				shtr_press = 1
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_ACCEPT
			ENDIF
		ENDIF
	ENDIF
	// Write second letter
	IF shtr_write = 1
		$shtr_hi_2[shtr_ranking] = $shtr_char[shtr_chars]
		IF shtr_press = 0
			
			IF IS_BUTTON_PRESSED PAD1 DPADUP
			OR IS_BUTTON_PRESSED PAD1 DPADRIGHT
			OR LStickX > 100
			OR LStickY < -100
				IF shtr_chars < 36 
					shtr_chars++
				ELSE
					shtr_chars = 0
				ENDIF
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_SELECT
				shtr_press = 1
			ENDIF
			
			IF IS_BUTTON_PRESSED PAD1 DPADDOWN
			OR IS_BUTTON_PRESSED PAD1 DPADLEFT
			OR LStickX < -100
			OR LStickY > 100
				IF shtr_chars > 0
					shtr_chars--
				ELSE
					shtr_chars = 36
				ENDIF
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_SELECT
				shtr_press = 1
			ENDIF
			
			IF IS_BUTTON_PRESSED PAD1 CROSS
				shtr_chars = 10
				shtr_write = 2
				shtr_press = 1
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_ACCEPT
			ENDIF
		ENDIF
	ENDIF
	// Write third letter
	IF shtr_write = 2
		$shtr_hi_3[shtr_ranking] = $shtr_char[shtr_chars]
		IF shtr_press = 0

			IF IS_BUTTON_PRESSED PAD1 DPADUP
			OR IS_BUTTON_PRESSED PAD1 DPADRIGHT
			OR LStickX > 100
			OR LStickY < -100
				IF shtr_chars < 36 
					shtr_chars++
				ELSE
					shtr_chars = 0
				ENDIF
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_SELECT
				shtr_press = 1
			ENDIF
			
			IF IS_BUTTON_PRESSED PAD1 DPADDOWN
			OR IS_BUTTON_PRESSED PAD1 DPADLEFT
			OR LStickX < -100
			OR LStickY > 100
				IF shtr_chars > 0
					shtr_chars--
				ELSE
					shtr_chars = 36
				ENDIF
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_SELECT
				shtr_press = 1
			ENDIF
			
			IF IS_BUTTON_PRESSED PAD1 CROSS
				shtr_write = 3
				shtr_press = 1
				shtr_on_table = 4
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_GOGO_ACCEPT
			ENDIF
		ENDIF
	ENDIF
RETURN

shtr_draw_scores:
	shtr_hi_loop = 0

	WHILE shtr_hi_loop < 10
			//WAIT 0
		GOSUB shtr_text_gosub
		DISPLAY_TEXT shtr_hi_1_x[shtr_hi_loop] shtr_hi_1_y[shtr_hi_loop] $shtr_hi_1[shtr_hi_loop]	// First letter of player name
		GOSUB shtr_text_gosub
		DISPLAY_TEXT shtr_hi_2_x[shtr_hi_loop] shtr_hi_2_y[shtr_hi_loop] $shtr_hi_2[shtr_hi_loop]	// Second letter of player name
		GOSUB shtr_text_gosub
		DISPLAY_TEXT shtr_hi_3_x[shtr_hi_loop] shtr_hi_3_y[shtr_hi_loop] $shtr_hi_3[shtr_hi_loop] 	
		GOSUB shtr_text_gosub
		SET_TEXT_RIGHT_JUSTIFY ON
		DISPLAY_TEXT_WITH_NUMBER shtr_hi_4_x[shtr_hi_loop] shtr_hi_4_y[shtr_hi_loop] SHTR_LV shtr_hi_s[shtr_hi_loop]
		shtr_hi_loop ++
	ENDWHILE
	GOSUB shtr_text_gosub
	DISPLAY_TEXT 320.0 64.0 SHTR_2f
	IF shtr_lives = 0
	AND shtr_draw_highlight = 1
		IF shtr_write = 0
			GOSUB shtr_text_gosub
			SET_TEXT_COLOUR 0 255 0 255
			DISPLAY_TEXT shtr_hi_1_x[shtr_ranking] shtr_hi_1_y[shtr_ranking] $shtr_hi_1[shtr_ranking]
		ELSE
			GOSUB shtr_text_gosub
			SET_TEXT_COLOUR 255 0 0 255
			DISPLAY_TEXT shtr_hi_1_x[shtr_ranking] shtr_hi_1_y[shtr_ranking] $shtr_hi_1[shtr_ranking]
		ENDIF 
		// First letter of player name
		IF shtr_write = 1
			GOSUB shtr_text_gosub
			SET_TEXT_COLOUR 0 255 0 255
			DISPLAY_TEXT shtr_hi_2_x[shtr_ranking] shtr_hi_2_y[shtr_ranking] $shtr_hi_2[shtr_ranking]
		ELSE
			GOSUB shtr_text_gosub
			SET_TEXT_COLOUR 255 0 0 255
			DISPLAY_TEXT shtr_hi_2_x[shtr_ranking] shtr_hi_2_y[shtr_ranking] $shtr_hi_2[shtr_ranking]
		ENDIF 
		// Second letter of player name
		IF shtr_write = 2
			GOSUB shtr_text_gosub
			SET_TEXT_COLOUR 0 255 0 255
			DISPLAY_TEXT shtr_hi_3_x[shtr_ranking] shtr_hi_3_y[shtr_ranking] $shtr_hi_3[shtr_ranking]
		ELSE
			GOSUB shtr_text_gosub
			SET_TEXT_COLOUR 255 0 0 255
			DISPLAY_TEXT shtr_hi_3_x[shtr_ranking] shtr_hi_3_y[shtr_ranking] $shtr_hi_3[shtr_ranking]
		ENDIF 
		GOSUB shtr_text_gosub 
		SET_TEXT_COLOUR 255 0 0 255
		SET_TEXT_RIGHT_JUSTIFY ON
		DISPLAY_TEXT_WITH_NUMBER shtr_hi_4_x[shtr_ranking] shtr_hi_4_y[shtr_ranking] SHTR_LV shtr_hi_s[shtr_ranking]
	ENDIF
RETURN

shtr_generate_default_scores:

	GENERATE_RANDOM_INT_IN_RANGE 1 30 shtr_num_gen

	// Neil	(NF.)
	IF shtr_num_gen = 1
		IF shtr_used[1] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[23] 
			$shtr_hi_2[shtr_current] = $shtr_char[15] 
			$shtr_hi_3[shtr_current] = $shtr_char[36]
			shtr_used[1] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// Imran (IMY)
	IF shtr_num_gen = 2
		IF shtr_used[2] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[18] 
			$shtr_hi_2[shtr_current] = $shtr_char[22] 
			$shtr_hi_3[shtr_current] = $shtr_char[34]
			shtr_used[2] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// Willie (WIL)
	IF shtr_num_gen = 3
		IF shtr_used[3] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[32] 
			$shtr_hi_2[shtr_current] = $shtr_char[18] 
			$shtr_hi_3[shtr_current] = $shtr_char[21]
			shtr_used[3] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF 

	// Chris (CKR)
	IF shtr_num_gen = 4
		IF shtr_used[4] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[12] 
			$shtr_hi_2[shtr_current] = $shtr_char[20] 
			$shtr_hi_3[shtr_current] = $shtr_char[27]
			shtr_used[4] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF 

	// Andy	(DBP)
	IF shtr_num_gen = 5
		IF shtr_used[5] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[13] 
			$shtr_hi_2[shtr_current] = $shtr_char[11] 
			$shtr_hi_3[shtr_current] = $shtr_char[25]
			shtr_used[5] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF 

	// David (DAV)
	IF shtr_num_gen = 6
		IF shtr_used[6] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[13]  
			$shtr_hi_2[shtr_current] = $shtr_char[10]  
			$shtr_hi_3[shtr_current] = $shtr_char[31] 
			shtr_used[6] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF 

	// James (DOD)
	IF shtr_num_gen = 7
		IF shtr_used[7] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[13] 
			$shtr_hi_2[shtr_current] = $shtr_char[24] 
			$shtr_hi_3[shtr_current] = $shtr_char[13]
			shtr_used[7] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// Simon (SJL)
	IF shtr_num_gen = 8
		IF shtr_used[8] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[28] 
			$shtr_hi_2[shtr_current] = $shtr_char[19] 
			$shtr_hi_3[shtr_current] = $shtr_char[21]
			shtr_used[8] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF 

	// Steve (STE)
	IF shtr_num_gen = 9
		IF shtr_used[9] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[28] 
			$shtr_hi_2[shtr_current] = $shtr_char[29] 
			$shtr_hi_3[shtr_current] = $shtr_char[14]
			shtr_used[9] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF 

	// Judith (JUD)
	IF shtr_num_gen = 10
		IF shtr_used[10] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[19] 
			$shtr_hi_2[shtr_current] = $shtr_char[30] 
			$shtr_hi_3[shtr_current] = $shtr_char[13]
			shtr_used[10] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// Kev Bolt (KMB)
	IF shtr_num_gen = 11
		IF shtr_used[11] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[20] 
			$shtr_hi_2[shtr_current] = $shtr_char[22] 
			$shtr_hi_3[shtr_current] = $shtr_char[11]
			shtr_used[11] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF 

	// Graeme (GSW)
	IF shtr_num_gen = 12
		IF shtr_used[12] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[16] 
			$shtr_hi_2[shtr_current] = $shtr_char[28] 
			$shtr_hi_3[shtr_current] = $shtr_char[32]
			shtr_used[12] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF 

	// Dave W (DSW)
	IF shtr_num_gen = 13
		IF shtr_used[13] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[13] 
			$shtr_hi_2[shtr_current] = $shtr_char[28] 
			$shtr_hi_3[shtr_current] = $shtr_char[32]
			shtr_used[13] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// Woody (WDY)
	IF shtr_num_gen = 14
		IF shtr_used[14] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[32] 
			$shtr_hi_2[shtr_current] = $shtr_char[13] 
			$shtr_hi_3[shtr_current] = $shtr_char[34]
			shtr_used[14] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// Gary (GAZ)
	IF shtr_num_gen = 15
		IF shtr_used[15] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[16] 
			$shtr_hi_2[shtr_current] = $shtr_char[10] 
			$shtr_hi_3[shtr_current] = $shtr_char[35]
			shtr_used[15] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// Wayland (WAZ)
	IF shtr_num_gen = 16
		IF shtr_used[16] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[32] 
			$shtr_hi_2[shtr_current] = $shtr_char[10] 
			$shtr_hi_3[shtr_current] = $shtr_char[35]
			shtr_used[16] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// Kinch (KIN)
	IF shtr_num_gen = 17
		IF shtr_used[17] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[20] 
			$shtr_hi_2[shtr_current] = $shtr_char[18] 
			$shtr_hi_3[shtr_current] = $shtr_char[23]
			shtr_used[17] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// Bean (BEA)
	IF shtr_num_gen = 18
		IF shtr_used[18] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[11] 
			$shtr_hi_2[shtr_current] = $shtr_char[14] 
			$shtr_hi_3[shtr_current] = $shtr_char[10]
			shtr_used[18] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// Baxy (BAX)
	IF shtr_num_gen = 19
		IF shtr_used[19] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[11] 
			$shtr_hi_2[shtr_current] = $shtr_char[10] 
			$shtr_hi_3[shtr_current] = $shtr_char[33]
			shtr_used[19] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// (LOU)
	IF shtr_num_gen = 20
		IF shtr_used[20] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[21] 
			$shtr_hi_2[shtr_current] = $shtr_char[24] 
			$shtr_hi_3[shtr_current] = $shtr_char[30]
			shtr_used[20] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// (JNO)
	IF shtr_num_gen = 21
		IF shtr_used[21] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[19] 
			$shtr_hi_2[shtr_current] = $shtr_char[23] 
			$shtr_hi_3[shtr_current] = $shtr_char[24]
			shtr_used[21] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// (MYT)
	IF shtr_num_gen = 22
		IF shtr_used[22] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[22] 
			$shtr_hi_2[shtr_current] = $shtr_char[34] 
			$shtr_hi_3[shtr_current] = $shtr_char[29]
			shtr_used[22] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// (DEF)
	IF shtr_num_gen = 23
		IF shtr_used[23] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[13] 
			$shtr_hi_2[shtr_current] = $shtr_char[14] 
			$shtr_hi_3[shtr_current] = $shtr_char[15]
			shtr_used[23] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// (KHZ)
	IF shtr_num_gen = 24
		IF shtr_used[24] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[20] 
			$shtr_hi_2[shtr_current] = $shtr_char[17] 
			$shtr_hi_3[shtr_current] = $shtr_char[35]
			shtr_used[24] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// (MEO)
	IF shtr_num_gen = 25
		IF shtr_used[25] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[22] 
			$shtr_hi_2[shtr_current] = $shtr_char[14] 
			$shtr_hi_3[shtr_current] = $shtr_char[24]
			shtr_used[25] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// George (GFW)
	IF shtr_num_gen = 26
		IF shtr_used[26] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[16] 
			$shtr_hi_2[shtr_current] = $shtr_char[15] 
			$shtr_hi_3[shtr_current] = $shtr_char[32]
			shtr_used[26] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// Will (WRM)
	IF shtr_num_gen = 27
		IF shtr_used[27] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[32] 
			$shtr_hi_2[shtr_current] = $shtr_char[27] 
			$shtr_hi_3[shtr_current] = $shtr_char[22]
			shtr_used[27] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// Allan (A.W)
	IF shtr_num_gen = 28
		IF shtr_used[28] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[10] 
			$shtr_hi_2[shtr_current] = $shtr_char[36] 
			$shtr_hi_3[shtr_current] = $shtr_char[32]
			shtr_used[28] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

	// Richy (RIC)
	IF shtr_num_gen = 29
		IF shtr_used[29] = 0
			$shtr_hi_1[shtr_current] = $shtr_char[27] 
			$shtr_hi_2[shtr_current] = $shtr_char[18] 
			$shtr_hi_3[shtr_current] = $shtr_char[12]
			shtr_used[29] = 1
		ELSE
			GOTO shtr_generate_default_scores
		ENDIF
	ENDIF

RETURN

// Initialisation -------------------------------------------------------------
shtr_hiscore_init:
// ---- Hi Score Chars

		$shtr_char[0] 	= SHTR_0	// 0
		$shtr_char[1] 	= SHTR_1	// 1
		$shtr_char[2] 	= SHTR_2	// 2
		$shtr_char[3] 	= SHTR_3	// 3
		$shtr_char[4] 	= SHTR_4	// 4
					 	 
		$shtr_char[5] 	= SHTR_5	// 5
		$shtr_char[6] 	= SHTR_6	// 6
		$shtr_char[7] 	= SHTR_7	// 7
		$shtr_char[8] 	= SHTR_8	// 8
		$shtr_char[9] 	= SHTR_9	// 9
					  
		$shtr_char[10] 	= SHTR_A	// A
		$shtr_char[11] 	= SHTR_B	// B
		$shtr_char[12] 	= SHTR_C	// C
		$shtr_char[13] 	= SHTR_D	// D
		$shtr_char[14] 	= SHTR_E	// E
				     		
		$shtr_char[15] 	= SHTR_F	// F
		$shtr_char[16] 	= SHTR_G	// G
		$shtr_char[17] 	= SHTR_H	// H
		$shtr_char[18] 	= SHTR_I	// I
		$shtr_char[19] 	= SHTR_J	// J
				 		   
		$shtr_char[20] 	= SHTR_K	// K
		$shtr_char[21] 	= SHTR_L	// L
		$shtr_char[22] 	= SHTR_M	// M
		$shtr_char[23] 	= SHTR_N	// N
		$shtr_char[24] 	= SHTR_O	// O
			  		
		$shtr_char[25] 	= SHTR_P	// P
		$shtr_char[26] 	= SHTR_Q	// Q
		$shtr_char[27] 	= SHTR_R	// R
		$shtr_char[28] 	= SHTR_S	// S
		$shtr_char[29] 	= SHTR_T	// T
			  		
		$shtr_char[30] 	= SHTR_U	// U
		$shtr_char[31] 	= SHTR_V	// V
		$shtr_char[32] 	= SHTR_W	// W
		$shtr_char[33] 	= SHTR_X	// X
		$shtr_char[34] 	= SHTR_Y	// Y
					  	 
		$shtr_char[35] 	= SHTR_Z	// Z
		$shtr_char[36] 	= SHTR_PE	// .

// ---- Hi Score Coords

		shtr_hi_1_x[0] = 176.0 // First letter of player name
		shtr_hi_1_y[0] = 96.0 
		shtr_hi_2_x[0] = 224.0 // Second letter of player name
		shtr_hi_2_y[0] = 96.0
		shtr_hi_3_x[0] = 272.0 // Third letter of player name 
		shtr_hi_3_y[0] = 96.0
		shtr_hi_4_x[0] = 464.0 // Level with Number & score
		shtr_hi_4_y[0] = 96.0

		shtr_hi_1_x[1] = 176.0
		shtr_hi_1_y[1] = 128.0 
		shtr_hi_2_x[1] = 224.0
		shtr_hi_2_y[1] = 128.0
		shtr_hi_3_x[1] = 272.0
		shtr_hi_3_y[1] = 128.0
		shtr_hi_4_x[1] = 464.0
		shtr_hi_4_y[1] = 128.0

		shtr_hi_1_x[2] = 176.0
		shtr_hi_1_y[2] = 160.0 
		shtr_hi_2_x[2] = 224.0
		shtr_hi_2_y[2] = 160.0
		shtr_hi_3_x[2] = 272.0
		shtr_hi_3_y[2] = 160.0
		shtr_hi_4_x[2] = 464.0
		shtr_hi_4_y[2] = 160.0

		shtr_hi_1_x[3] = 176.0
		shtr_hi_1_y[3] = 192.0 
		shtr_hi_2_x[3] = 224.0
		shtr_hi_2_y[3] = 192.0
		shtr_hi_3_x[3] = 272.0
		shtr_hi_3_y[3] = 192.0
		shtr_hi_4_x[3] = 464.0
		shtr_hi_4_y[3] = 192.0

		shtr_hi_1_x[4] = 176.0
		shtr_hi_1_y[4] = 224.0 
		shtr_hi_2_x[4] = 224.0
		shtr_hi_2_y[4] = 224.0
		shtr_hi_3_x[4] = 272.0
		shtr_hi_3_y[4] = 224.0
		shtr_hi_4_x[4] = 464.0
		shtr_hi_4_y[4] = 224.0

		shtr_hi_1_x[5] = 176.0
		shtr_hi_1_y[5] = 256.0 
		shtr_hi_2_x[5] = 224.0
		shtr_hi_2_y[5] = 256.0
		shtr_hi_3_x[5] = 272.0
		shtr_hi_3_y[5] = 256.0
		shtr_hi_4_x[5] = 464.0
		shtr_hi_4_y[5] = 256.0

		shtr_hi_1_x[6] = 176.0
		shtr_hi_1_y[6] = 288.0 
		shtr_hi_2_x[6] = 224.0
		shtr_hi_2_y[6] = 288.0
		shtr_hi_3_x[6] = 272.0
		shtr_hi_3_y[6] = 288.0
		shtr_hi_4_x[6] = 464.0
		shtr_hi_4_y[6] = 288.0

		shtr_hi_1_x[7] = 176.0
		shtr_hi_1_y[7] = 320.0 
		shtr_hi_2_x[7] = 224.0
		shtr_hi_2_y[7] = 320.0
		shtr_hi_3_x[7] = 272.0
		shtr_hi_3_y[7] = 320.0
		shtr_hi_4_x[7] = 464.0
		shtr_hi_4_y[7] = 320.0

		shtr_hi_1_x[8] = 176.0
		shtr_hi_1_y[8] = 352.0 
		shtr_hi_2_x[8] = 224.0
		shtr_hi_2_y[8] = 352.0
		shtr_hi_3_x[8] = 272.0
		shtr_hi_3_y[8] = 352.0
		shtr_hi_4_x[8] = 464.0
		shtr_hi_4_y[8] = 352.0

		shtr_hi_1_x[9] = 176.0
		shtr_hi_1_y[9] = 384.0 
		shtr_hi_2_x[9] = 224.0
		shtr_hi_2_y[9] = 384.0
		shtr_hi_3_x[9] = 272.0
		shtr_hi_3_y[9] = 384.0
		shtr_hi_4_x[9] = 464.0
		shtr_hi_4_y[9] = 384.0

IF shtr_played_before = 0
	
	$shtr_hi_1[0] = $shtr_char[12] 
	$shtr_hi_2[0] = $shtr_char[36] 
	$shtr_hi_3[0] = $shtr_char[36] 
	shtr_hi_l[0] = 10
	shtr_hi_s[0] = 1000

	shtr_current = 1
	GOSUB shtr_generate_default_scores
	shtr_hi_l[1] = 9
	shtr_hi_s[1] = 900

	shtr_current = 2
	GOSUB shtr_generate_default_scores
	shtr_hi_l[2] = 8
	shtr_hi_s[2] = 800

	shtr_current = 3
	GOSUB shtr_generate_default_scores
	shtr_hi_l[3] = 7
	shtr_hi_s[3] = 700

	shtr_current = 4
	GOSUB shtr_generate_default_scores
	shtr_hi_l[4] = 6
	shtr_hi_s[4] = 600

	shtr_current = 5
	GOSUB shtr_generate_default_scores
	shtr_hi_l[5] = 5
	shtr_hi_s[5] = 500

	shtr_current = 6
	GOSUB shtr_generate_default_scores
	shtr_hi_l[6] = 4
	shtr_hi_s[6] = 400

	shtr_current = 7
	GOSUB shtr_generate_default_scores
	shtr_hi_l[7] = 3
	shtr_hi_s[7] = 300

	shtr_current = 8
	GOSUB shtr_generate_default_scores
	shtr_hi_l[8] = 2
	shtr_hi_s[8] = 200

	shtr_current = 9
	GOSUB shtr_generate_default_scores
	shtr_hi_l[9] = 1
	shtr_hi_s[9] = 100

	shtr_played_before = 1
ENDIF
shtr_hi_score = shtr_hi_s[0]

RETURN



shtr_init:
// player weapon
	// flags
		shtr_button1 = 1
		shtr_player_alive = 1
		shtr_front_end = 1
		shtr_score = 0
		shtr_lives = 2
		shtr_button_pressed = 0
	// projectile speed
		shtr_fire_speed = 15.0
		shtr_plyr_x = 80.0
		shtr_plyr_y = 224.0
		shtr_projectile_size = 8.0
		shtr_shot_angle = 10.0
	// enemy speed
		shtr_range_start = 1.0
		shtr_range_end = 3.0

	// enemy projectile
		shtr_fire_alive = 0
		unk_5962 = 7.0
		shtr_shot = 0

	// Pickups
		shtr_pickup_counter = 0
		shtr_pickup_collected = 1
		shtr_pickup_current = 31
		shtr_speed_pickup = 250
		shtr_auto_pickup = 0 
		shtr_multi_pickup = 0
		shtr_pickup_move_switch = 0


// ---- HiScore Vars
		shtr_write = 0
		shtr_draw_highlight = 0

		shtr_enemy_x[0] = 660.0
		shtr_enemy_x[1] = 660.0
		shtr_enemy_x[2] = 660.0
		shtr_enemy_x[3] = 660.0

		shtr_heat_bar = 0

		shtr_start_time = 0 
		shtr_end_time = 0 
		shtr_time_diff = 0 
		shtr_start_time_hit = 0 
		shtr_end_time_hit = 0 
		shtr_time_diff_hit = 0

		shtr_current_projectile = 1

		shtr_projectile_alive[1] = 0
		shtr_projectile_alive[2] = 0
		shtr_projectile_alive[3] = 0
		shtr_projectile_alive[4] = 0
		shtr_projectile_alive[5] = 0
		shtr_projectile_alive[6] = 0
		shtr_projectile_alive[7] = 0
		shtr_projectile_alive[8] = 0

		shtr_up_projectile_alive[1] = 0
		shtr_up_projectile_alive[2] = 0
		shtr_up_projectile_alive[3] = 0
		shtr_up_projectile_alive[4] = 0
		shtr_up_projectile_alive[5] = 0
		shtr_up_projectile_alive[6] = 0
		shtr_up_projectile_alive[7] = 0
		shtr_up_projectile_alive[8] = 0

		shtr_down_projectile_alive[1] = 0
		shtr_down_projectile_alive[2] = 0
		shtr_down_projectile_alive[3] = 0
		shtr_down_projectile_alive[4] = 0
		shtr_down_projectile_alive[5] = 0
		shtr_down_projectile_alive[6] = 0
		shtr_down_projectile_alive[7] = 0
		shtr_down_projectile_alive[8] = 0

		shtr_enemy_alive[0] = 0
		shtr_enemy_alive[1] = 0
		shtr_enemy_alive[2] = 0
		shtr_enemy_alive[3] = 0
		shtr_enemy_alive[4] = 0
		shtr_enemy_alive[5] = 0
		shtr_enemy_alive[6] = 0
		shtr_enemy_alive[7] = 0

		shtr_enemy_shot_alive[0] = 0
		shtr_enemy_shot_alive[1] = 0
		shtr_enemy_shot_alive[2] = 0
		shtr_enemy_shot_alive[3] = 0
		shtr_enemy_shot_alive[4] = 0
		shtr_enemy_shot_alive[5] = 0
		shtr_enemy_shot_alive[6] = 0
		shtr_enemy_shot_alive[7] = 0

		shtr_respawn = 0
		shtr_random = 0

		shtr_enemy_col_x = 32.0  
		shtr_enemy_col_y = 32.0

		shtr_projectile_col_x = 8.0
		shtr_projectile_col_y = 8.0

		shtr_plyr_col_x = 32.0
		shtr_plyr_col_y	= 32.0

		shtr_amplitude = 0.0

RETURN


// the end *********************************************************************************
}																								 
/*
{--------------SHOOTER---------------------------}

[SHTR_1a:SHTR]
START

[SHTR_1b:SHTR]
HI-SCORES

[SHTR_1c:SHTR]
EXIT

[SHTR_2a:SHTR]
BEGIN!

[SHTR_2b:SHTR]
LIVES ~1~

[SHTR_2c:SHTR]
HI-SCORE ~1~

[SHTR_2d:SHTR]
1UP ~1~

[SHTR_2e:SHTR]
BONUS

[SHTR_3a:SHTR]
CONTINUE?

[SHTR_3b:SHTR]
GAME OVER!

[SHTR_3c:SHTR]
ENTER NAME :

[SHTR_0:SHTR]
0
[SHTR_1:SHTR]
1
[SHTR_2:SHTR]
2
[SHTR_3:SHTR]
3
[SHTR_4:SHTR]
4

[SHTR_5:SHTR]
5
[SHTR_6:SHTR]
6
[SHTR_7:SHTR]
7
[SHTR_8:SHTR]
8
[SHTR_9:SHTR]
9

[SHTR_A:SHTR]
A
[SHTR_B:SHTR]
B
[SHTR_C:SHTR]
C
[SHTR_D:SHTR]
D
[SHTR_E:SHTR]
E

[SHTR_F:SHTR]
F
[SHTR_G:SHTR]
G
[SHTR_H:SHTR]
H
[SHTR_I:SHTR]
I
[SHTR_J:SHTR]
J

[SHTR_K:SHTR]
K
[SHTR_L:SHTR]
L
[SHTR_M:SHTR]
M
[SHTR_N:SHTR]
N
[SHTR_O:SHTR]
O

[SHTR_P:SHTR]
P
[SHTR_Q:SHTR]
Q
[SHTR_R:SHTR]
R
[SHTR_S:SHTR]
S
[SHTR_T:SHTR]
T

[SHTR_U:SHTR]
U
[SHTR_V:SHTR]
V
[SHTR_W:SHTR]
W
[SHTR_X:SHTR]
X
[SHTR_Y:SHTR]
Y

[SHTR_Z:SHTR]
Z
[SHTR_PE:SHTR]
.





*/













































