MISSION_START
// *****************************************************************************************
// ***																					 ***
// *** Date: 18/06/2004 	Time: 14:35:45	   Name:  Chris Rothwell 					 ***
// ***																					 ***
// *** This is the "They Came From Uranus" arcade game.									 ***
// ***																					 ***
// *****************************************************************************************

GOSUB mission_none_start
GOSUB mission_none_cleanup
MISSION_END
 
// ************************************ MISSION START **************************************
{
mission_none_start:

flag_player_on_mission = 1

REGISTER_MISSION_GIVEN
SCRIPT_NAME none

WAIT 0

LOAD_MISSION_TEXT dual

SHUT_ALL_CHARS_UP TRUE
get_char_coordinates scplayer x y z
clear_area x y z 50.0 0

USE_TEXT_COMMANDS TRUE
REMOVE_TEXTURE_DICTIONARY
LOAD_TEXTURE_DICTIONARY ld_none

LOAD_SPRITE 1 ship
LOAD_SPRITE 2 tvcorn
LOAD_SPRITE 3 shoot
LOAD_SPRITE 4 light
LOAD_SPRITE 5 explm01
LOAD_SPRITE 6 explm02
LOAD_SPRITE 7 explm03
LOAD_SPRITE 8 explm04
LOAD_SPRITE 9 explm05
LOAD_SPRITE 10 explm06
LOAD_SPRITE 11 explm07
LOAD_SPRITE 12 explm08
LOAD_SPRITE 13 explm09
LOAD_SPRITE 14 explm10
LOAD_SPRITE 15 explm11
LOAD_SPRITE 16 explm12
LOAD_SPRITE 17 force
LOAD_SPRITE 18 warp
LOAD_SPRITE 19 thrust
LOAD_SPRITE 20 shpnorm
LOAD_SPRITE 21 shpwarp
LOAD_SPRITE 22 ship2
LOAD_SPRITE 23 ship3
LOAD_SPRITE 24 title

load_mission_audio 4 SOUND_BANK_TEMPEST
while not has_mission_audio_loaded 4
	wait 0
endwhile
REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_TEMPEST_TRACK_START

lvar_float pattern_move_1[10]
pattern_move_1[0] = 3.0
pattern_move_1[1] = 2.0
pattern_move_1[2] = 1.0
pattern_move_1[3] = 0.0
pattern_move_1[4] = -1.0
pattern_move_1[5] = -2.0
pattern_move_1[6] = -3.0
pattern_move_1[7] = -2.0
pattern_move_1[8] = -1.0
pattern_move_1[9] = -4.0

lvar_float pattern_rotation_1[10]
pattern_rotation_1[0] = 4.0
pattern_rotation_1[1] = 3.0
pattern_rotation_1[2] = 2.0
pattern_rotation_1[3] = -2.0
pattern_rotation_1[4] = -4.0
pattern_rotation_1[5] = -6.0
pattern_rotation_1[6] = -3.0
pattern_rotation_1[7] = -2.0
pattern_rotation_1[8] = -7.0
pattern_rotation_1[9] = -6.0

lvar_int pattern_timer_1[10]
pattern_timer_1[0] = 300
pattern_timer_1[1] = 200
pattern_timer_1[2] = 400
pattern_timer_1[3] = 600
pattern_timer_1[4] = 400
pattern_timer_1[5] = 500
pattern_timer_1[6] = 200
pattern_timer_1[7] = 300
pattern_timer_1[8] = 400
pattern_timer_1[9] = 6000

lvar_float pattern_move_2[10]
pattern_move_2[0] = 2.0
pattern_move_2[1] = 3.0
pattern_move_2[2] = 1.0
pattern_move_2[3] = 0.0
pattern_move_2[4] = 1.0
pattern_move_2[5] = 3.0
pattern_move_2[6] = -3.0
pattern_move_2[7] = -3.0
pattern_move_2[8] = 2.0
pattern_move_2[9] = -5.0

lvar_float pattern_rotation_2[10]
pattern_rotation_2[0] = 0.0
pattern_rotation_2[1] = 3.0
pattern_rotation_2[2] = 4.0
pattern_rotation_2[3] = 5.0
pattern_rotation_2[4] = 12.0
pattern_rotation_2[5] = 6.0
pattern_rotation_2[6] = 3.0
pattern_rotation_2[7] = 0.0
pattern_rotation_2[8] = -4.0
pattern_rotation_2[9] = -6.0

lvar_int pattern_timer_2[10]
pattern_timer_2[0] = 100
pattern_timer_2[1] = 400
pattern_timer_2[2] = 300
pattern_timer_2[3] = 500
pattern_timer_2[4] = 400
pattern_timer_2[5] = 200
pattern_timer_2[6] = 400
pattern_timer_2[7] = 400
pattern_timer_2[8] = 400
pattern_timer_2[9] = 6000

lvar_float pattern_move_3[10]
pattern_move_3[0] = 5.0
pattern_move_3[1] = -3.0
pattern_move_3[2] = 6.0
pattern_move_3[3] = -6.0
pattern_move_3[4] = 3.0
pattern_move_3[5] = -3.0
pattern_move_3[6] = 3.0
pattern_move_3[7] = -3.0
pattern_move_3[8] = 3.0
pattern_move_3[9] = -6.0

lvar_float pattern_rotation_3[10]
pattern_rotation_3[0] = 4.0
pattern_rotation_3[1] = 4.0
pattern_rotation_3[2] = 4.0
pattern_rotation_3[3] = 4.0
pattern_rotation_3[4] = 4.0
pattern_rotation_3[5] = 4.0
pattern_rotation_3[6] = 4.0
pattern_rotation_3[7] = 4.0
pattern_rotation_3[8] = 4.0
pattern_rotation_3[9] = 4.0

lvar_int pattern_timer_3[10]
pattern_timer_3[0] = 300
pattern_timer_3[1] = 300
pattern_timer_3[2] = 300
pattern_timer_3[3] = 300
pattern_timer_3[4] = 300
pattern_timer_3[5] = 300
pattern_timer_3[6] = 300
pattern_timer_3[7] = 300
pattern_timer_3[8] = 300
pattern_timer_3[9] = 300

lvar_float player_explosions_move_x[8] player_explosions_move_y[8]
player_explosions_move_x[0]	= 0.0
player_explosions_move_y[0]	= 5.0
player_explosions_move_x[1]	= 5.0
player_explosions_move_y[1]	= 5.0
player_explosions_move_x[2]	= 5.0
player_explosions_move_y[2]	= 0.0
player_explosions_move_x[3]	= -5.0
player_explosions_move_y[3]	= 0.0
player_explosions_move_x[4]	= -5.0
player_explosions_move_y[4]	= -5.0
player_explosions_move_x[5]	= 0.0
player_explosions_move_y[5]	= -5.0
player_explosions_move_x[6]	= 5.0
player_explosions_move_y[6]	= -5.0
player_explosions_move_x[7]	= -5.0
player_explosions_move_y[7]	= 5.0

lvar_text_label	$none_char[37]
$none_char[0]  = DUAL_0	// 0
$none_char[1]  = DUAL_1	// 1
$none_char[2]  = DUAL_2	// 2
$none_char[3]  = DUAL_3	// 3
$none_char[4]  = DUAL_4	// 4
			 	 
$none_char[5]  = DUAL_5	// 5
$none_char[6]  = DUAL_6	// 6
$none_char[7]  = DUAL_7	// 7
$none_char[8]  = DUAL_8	// 8
$none_char[9]  = DUAL_9	// 9
			  
$none_char[10] = DUAL_AA// A
$none_char[11] = DUAL_B	// B
$none_char[12] = DUAL_C	// C
$none_char[13] = DUAL_D	// D
$none_char[14] = DUAL_E	// E
		     		
$none_char[15] = DUAL_F	// F
$none_char[16] = DUAL_G	// G
$none_char[17] = DUAL_H	// H
$none_char[18] = DUAL_I	// I
$none_char[19] = DUAL_J	// J
		 		   
$none_char[20] = DUAL_K	// K
$none_char[21] = DUAL_L	// L
$none_char[22] = DUAL_M	// M
$none_char[23] = DUAL_N	// N
$none_char[24] = DUAL_O	// O
	  		
$none_char[25] = DUAL_P	// P
$none_char[26] = DUAL_Q	// Q
$none_char[27] = DUAL_R	// R
$none_char[28] = DUAL_S	// S
$none_char[29] = DUAL_T	// T
	  		
$none_char[30] = DUAL_U	// U
$none_char[31] = DUAL_V	// V
$none_char[32] = DUAL_W	// W
$none_char[33] = DUAL_X	// X
$none_char[34] = DUAL_Y	// Y
			  	 
$none_char[35] = DUAL_Z	// Z
$none_char[36] = DUAL_FS// .

var_int played_none_before
if played_none_before = 0
	var_int none_hiscore[10]
	none_hiscore[0] = 250000
	none_hiscore[1] = 100000
	none_hiscore[2] = 75000
	none_hiscore[3] = 50000
	none_hiscore[4] = 25000
	none_hiscore[5] = 10000
	none_hiscore[6] = 7500
	none_hiscore[7] = 5000
	none_hiscore[8] = 2500
	none_hiscore[9] = 1000

	var_text_label	$none_letter1[10] $none_letter2[10] $none_letter3[10]
	$none_letter1[0] = $none_char[10]
	$none_letter2[0] = $none_char[13]
	$none_letter3[0] = $none_char[35]

	$none_letter1[1] = $none_char[12]
	$none_letter2[1] = $none_char[10]
	$none_letter3[1] = $none_char[23]

	$none_letter1[2] = $none_char[18]
	$none_letter2[2] = $none_char[22]
	$none_letter3[2] = $none_char[34]

	$none_letter1[3] = $none_char[32]
	$none_letter2[3] = $none_char[18]
	$none_letter3[3] = $none_char[21]

	$none_letter1[4] = $none_char[13]
	$none_letter2[4] = $none_char[11]
	$none_letter3[4] = $none_char[25]

	$none_letter1[5] = $none_char[13]
	$none_letter2[5] = $none_char[10]
	$none_letter3[5] = $none_char[31]

	$none_letter1[6] = $none_char[13]
	$none_letter2[6] = $none_char[24]
	$none_letter3[6] = $none_char[13]

	$none_letter1[7] = $none_char[23]
	$none_letter2[7] = $none_char[15]
	$none_letter3[7] = $none_char[36]

	$none_letter1[8] = $none_char[20]
	$none_letter2[8] = $none_char[22]
	$none_letter3[8] = $none_char[11]

	$none_letter1[9] = $none_char[28]
	$none_letter2[9] = $none_char[19]
	$none_letter3[9] = $none_char[21]

	played_none_before = 1
endif

lvar_float title_x title_y
title_x = 336.4137
title_y = 58.2432 

lvar_float top_row_x top_row_y
top_row_x = 201.2250
top_row_y = 88.9168 

lvar_float next_letter_x score_dist_x next_row_y
next_letter_x = 29.7800
score_dist_x = 260.0
next_row_y = 28.5713

lvar_int pad1_dpadup_pressed pad1_dpaddown_pressed pad1_cross_pressed pad1_circle_pressed
pad1_dpadup_pressed = 0
pad1_dpaddown_pressed = 0
pad1_cross_pressed = 0
pad1_circle_pressed = 0

do_fade 0 fade_in
draw_rect 320.0 224.0 640.0 448.0 0 0 0 255

lvar_int menu_selection
menu_selection = 0

lvar_int new_high_score
new_high_score = -1

lvar_int a b c
repeat 60 a
	lvar_float dust_heading[60]	dust_distance[60]
	generate_random_float_in_range 0.0 300.0 dust_distance[a]
	generate_random_float_in_range 0.0 360.0 dust_heading[a]
endrepeat
lvar_float dust_speed
dust_speed = 8.0

none_frontend_loop://////////////////////////////////////////////////////////////////////////
	wait 0

	draw_rect 320.0 224.0 640.0 448.0 0 0 0 255
	get_game_timer game_timer

	none_score = 0

	GET_POSITION_OF_ANALOGUE_STICKS 0 left_stick_x left_stick_y temp_int temp_int

	IF IS_BUTTON_PRESSED PAD1 DPADUP
	OR left_stick_y < -100
		IF pad1_dpadup_pressed = 0
			IF menu_selection = 2
				menu_selection = 1
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_TEMPEST_HIGHLIGHT
			ELSE
				IF menu_selection = 1
					menu_selection = 0
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_TEMPEST_HIGHLIGHT
				ENDIF
			ENDIF
		ENDIF
		pad1_dpadup_pressed = 1
	ELSE
		IF IS_BUTTON_PRESSED PAD1 DPADDOWN
		OR left_stick_y > 100
			IF pad1_dpaddown_pressed = 0
				IF menu_selection = 0
					menu_selection = 1
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_TEMPEST_HIGHLIGHT
				ELSE
					IF menu_selection = 1
						menu_selection = 2
						REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_TEMPEST_HIGHLIGHT
					ENDIF
				ENDIF
			ENDIF
			pad1_dpaddown_pressed = 1
		ELSE
			pad1_dpaddown_pressed = 0
		ENDIF
		pad1_dpadup_pressed = 0
	ENDIF
	
	gosub setup_text_none
	if menu_selection = 0
		set_text_colour 255 0 0 255
	else
		set_text_colour 255 255 255 255
	endif	
	set_text_scale 1.0 3.0
	display_text 320.0 250.0 SPAC_04 //PLAY

	gosub setup_text_none
	if menu_selection = 1
		set_text_colour 255 0 0 255
	else
		set_text_colour 255 255 255 255
	endif	
	set_text_scale 1.0 3.0
	display_text 320.0 300.0 SPAC_06 //HISCORE
	
	gosub setup_text_none
	if menu_selection = 2
		set_text_colour 255 0 0 255
	else
		set_text_colour 255 255 255 255
	endif	
	set_text_scale 1.0 3.0
	display_text 320.0 350.0 SPAC_05 //QUIT

	repeat 60 a
		dust_distance[a] += 8.0

		COS dust_heading[a] x
		SIN dust_heading[a] y

		x *= dust_distance[a]
		y *= dust_distance[a]

		x += 320.0
		y += 224.0

		draw_rect x y 1.0 1.0 255 255 255 255
		
		if x > 640.0
		or x < 0.0
		or y < 0.0
		or y > 448.0
			dust_distance[a] = 10.0
			generate_random_float_in_range 0.0 360.0 dust_heading[a]
		endif

	endrepeat

	DRAW_SPRITE 24 320.0 140.0 256.0 128.0 220 220 220 255
	DRAW_SPRITE 2 160.0 112.0 320.0 224.0 150 150 150 255
	DRAW_SPRITE 2 480.0 112.0 -320.0 224.0 150 150 150 255
	DRAW_SPRITE 2 480.0 336.0 -320.0 -224.0 150 150 150 255
	DRAW_SPRITE 2 160.0 336.0 320.0 -224.0 150 150 150 255

	IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT

		IF pad1_cross_pressed = 0
			++ pad1_cross_pressed
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_TEMPEST_SELECT
			if menu_selection = 0//PLAY
				goto none_initialise_vars
			endif
			if menu_selection = 1//hiscore
				GOTO none_hiscore_loop
			endif
			if menu_selection = 2//quit
				REMOVE_TEXTURE_DICTIONARY
				do_fade 0 fade_out
				IF IS_JAPANESE_VERSION
					WHILE IS_BUTTON_PRESSED PAD1 CIRCLE
						WAIT 0
					ENDWHILE
				ELSE
					wait 0
				ENDIF
				IF IS_PLAYER_PLAYING player1
					set_player_control player1 on
				ENDIF
				do_fade 500 fade_in
				return
			endif
		ENDIF
	ELSE
		pad1_cross_pressed = 0
	ENDIF

		
goto none_frontend_loop//////////////////////////////////////////////////////////////////////////

none_initialise_vars:

lvar_float ship_heading
ship_heading = 90.0

repeat 10 a
	lvar_int projectile_alive[10]
	projectile_alive[a] = 0
	lvar_int explosions_flag[10]
	explosions_flag[a] = 0
	lvar_int enemy_alive[10]
	enemy_alive[a] = 0
	lvar_int enemy_projectile_alive[10]
	enemy_projectile_alive[a] = 0
endrepeat

lvar_int none_score
none_score = 0

lvar_int generate_enemy_timer
generate_enemy_timer = 0

lvar_int player_dead_flag
player_dead_flag = 0

repeat 8 a
	lvar_int player_explosions_flag[8]
	player_explosions_flag[a] = 0
endrepeat

lvar_int invulnerability_timer
invulnerability_timer = 0

lvar_int player_ship_alpha alpha_change
player_ship_alpha = 255
alpha_change = -50

lvar_int quit_game
quit_game = 0

lvar_int ship_health last_ship_health
ship_health = 100
last_ship_health = ship_health

lvar_int draw_sheilds sheild_alpha sheild_alpha_change
draw_sheilds = 0
sheild_alpha = 0
sheild_alpha_change = 100

lvar_int ship_lives
ship_lives = 3

lvar_int enemy_wave_killed display_bonus_score display_bonus_score_timer
enemy_wave_killed = 0
display_bonus_score = 0
display_bonus_score_timer = 0

lvar_int warp_pickup_collected do_warp_pickup do_warp none_level
warp_pickup_collected = 0
do_warp_pickup = 0
do_warp = 0
none_level = 1

lvar_int any_enemy_alive
any_enemy_alive = 0

lvar_float ship_distance
ship_distance = 190.0

dust_speed = 8.0

lvar_int left_stick_x left_stick_y temp_int
lvar_float temp_float


none_game_loop://////////////////////////////////////////////////////////////////////////

WAIT 0

draw_rect 320.0 224.0 640.0 448.0 0 0 0 255

GET_GAME_TIMER game_timer

repeat 60 a
	dust_distance[a] += dust_speed

	COS dust_heading[a] x
	SIN dust_heading[a] y

	x *= dust_distance[a]
	y *= dust_distance[a]

	x += 320.0
	y += 224.0

	//lvar_float bubble_scale
	//bubble_scale = dust_distance[a] / 8.0

	draw_rect x y 1.0 1.0 255 255 255 255
	
	if x > 640.0
	or x < 0.0
	or y < 0.0
	or y > 448.0
		dust_distance[a] = 10.0
		generate_random_float_in_range 0.0 360.0 dust_heading[a]
	endif

endrepeat

if player_dead_flag = 0
	if do_warp < 2
		if IS_BUTTON_PRESSED PAD1 DPADRIGHT
			ship_heading +=@ -2.65
			LIMIT_ANGLE ship_heading ship_heading
		else
			if IS_BUTTON_PRESSED PAD1 DPADLEFT
				ship_heading +=@ 2.65
				LIMIT_ANGLE ship_heading ship_heading
			else
				GET_POSITION_OF_ANALOGUE_STICKS PAD1 left_stick_x left_stick_y temp_int temp_int
				temp_float =# left_stick_x
				temp_float *= -1.0
				temp_float /= 25.0

				ship_heading += temp_float
				LIMIT_ANGLE ship_heading ship_heading
			endif
		endif

	endif

	COS ship_heading x
	SIN ship_heading y

	lvar_float ship_collision_x
	ship_collision_x = y * 22.0
	if ship_collision_x < 0.0
		ship_collision_x *= -1.0
	endif
	ship_collision_x += 26.0
	
	lvar_float ship_collision_y
	ship_collision_y = x * 22.0
	if ship_collision_y < 0.0
		ship_collision_y *= -1.0
	endif
	ship_collision_y += 26.0

	if do_warp = 2
		ship_distance -=@ 2.0
		dust_speed = 16.0
		if ship_distance < 20.0
			do_warp = 3
		endif
	else
		if do_warp = 3
			ship_distance +=@ 2.0
			if ship_distance > 190.0
				ship_distance = 190.0
				warp_pickup_collected = 0
				++ none_level
				do_warp = 0
			endif
		else
			dust_speed = 8.0
			ship_distance = 190.0
		endif
	endif

	x *= ship_distance
	y *= ship_distance

	lvar_float ship_position_x ship_position_y
	ship_position_x = x + 320.0
	ship_position_y = y + 224.0

	if invulnerability_timer > game_timer
		player_ship_alpha += alpha_change
		if player_ship_alpha > 255
			player_ship_alpha = 255
			alpha_change = -50
		endif
		if player_ship_alpha < 0
			player_ship_alpha = 0
			alpha_change = 50
		endif
	else
		player_ship_alpha = 255
	endif
	lvar_float ship_scale_x	ship_scale_y
	ship_scale_y = ship_distance / 5.9375
	ship_scale_x = ship_scale_y * 2.0
	heading = ship_heading + 90.0
	if do_warp < 2
		DRAW_SPRITE_WITH_ROTATION 20 ship_position_x ship_position_y ship_scale_x ship_scale_y heading 220 220 220 player_ship_alpha
	else
		DRAW_SPRITE_WITH_ROTATION 21 ship_position_x ship_position_y ship_scale_x ship_scale_y heading 220 220 220 player_ship_alpha
	endif

	
	if do_warp < 2
		
		IF IS_BUTTON_PRESSED PAD1 CIRCLE
			if pad1_circle_pressed = 0
				repeat 10 a
					if projectile_alive[a] = 0
						lvar_float projectile_heading[10]
						projectile_heading[a] = ship_heading
						lvar_float projectile_distance[10]
						projectile_distance[a] = 190.0
						projectile_alive[a] = 1
						lvar_float projectile_speed[10]
						projectile_speed[a] = -12.0
						lvar_float projectile_direction[10]
						projectile_direction[a] = heading
						a = 9
					endif
				endrepeat
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_TEMPEST_PLAYER_SHOOT
				pad1_circle_pressed = 1
			endif
		else
			pad1_circle_pressed = 0
		ENDIF
	endif
else
	if player_dead_flag = 1
		repeat 8 a	
			player_explosions_flag[a] = 1
			player_explosions_x[a] = ship_position_x
			player_explosions_y[a] = ship_position_y
		endrepeat
		REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_TEMPEST_EXPLOSION
		++ player_dead_flag
	endif
	if player_dead_flag = 2
		repeat 8 a	
			IF player_explosions_flag[a] > 0
				IF player_explosions_flag[a] = 1
					player_explosions_flag[a] = 5
				ENDIF
				lvar_float player_explosions_x[8] player_explosions_y[8]
				player_explosions_x[a] += player_explosions_move_x[a]
				player_explosions_y[a] += player_explosions_move_y[a]
				++ player_explosions_flag[a]
				IF player_explosions_flag[a] > 16
					player_explosions_flag[a] = 0
				ELSE
					DRAW_SPRITE player_explosions_flag[a] player_explosions_x[a] player_explosions_y[a] 32.0 32.0 220 220 220 255
				ENDIF
			ENDIF
		endrepeat
		temp_int = 0
		repeat 8 a
			temp_int +=	player_explosions_flag[a]
		endrepeat
		if temp_int = 0
			ship_heading = 90.0
			invulnerability_timer = game_timer + 2500
			draw_sheilds = 0
			sheild_alpha = 0
			sheild_alpha_change = 100
			last_ship_health = 100
			-- ship_lives
			if ship_lives > 0
				player_dead_flag = 0
				ship_health = 100
			else
				lvar_int gameover_timer
				gameover_timer = game_timer + 3000
				player_dead_flag = 3
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_TEMPEST_GAME_OVER
			endif
		endif
	endif
	if player_dead_flag = 3
		if gameover_timer > game_timer
			gosub setup_text_none
			display_text 320.0 100.0 SPAC_01// GAME OVER!
		else
			quit_game = 1
		endif
	endif
endif

//DRAW PROJECTILES///
repeat 10 a
	if projectile_alive[a] = 1
		projectile_distance[a] += projectile_speed[a]
		
		COS projectile_heading[a] x
		SIN projectile_heading[a] y

		x *= projectile_distance[a]
		y *= projectile_distance[a]

		lvar_float projectile_position_x[10] projectile_position_y[10]
		projectile_position_x[a] = x + 320.0
		projectile_position_y[a] = y + 224.0

		DRAW_SPRITE_WITH_ROTATION 3 projectile_position_x[a] projectile_position_y[a] 8.0 8.0 projectile_direction[a] 52 183 195 255
		if projectile_distance[a] < 10.0
			projectile_alive[a] = 0
		endif
	endif
endrepeat

if do_warp = 0
	lvar_int new_enemy_wave_timer
	if new_enemy_wave_timer < game_timer
		lvar_float generate_enemies_heading
		generate_random_float_in_range 0.0 360.0 generate_enemies_heading
		lvar_int wave_pattern
		generate_random_int_in_range 0 3 wave_pattern
		lvar_int enemies_to_generate
		enemy_wave_killed = 0
		enemies_to_generate = 10
		new_enemy_wave_timer = game_timer + 8000
	endif
else
	new_enemy_wave_timer = game_timer + 1000
endif

if enemies_to_generate > 0
	if generate_enemy_timer < game_timer
		repeat 10 a
			if enemy_alive[a] = 0
				lvar_float enemy_heading[10]
				//generate_random_float_in_range 0.0 360.0 enemy_heading[a]
				enemy_heading[a] = generate_enemies_heading//0.0
				lvar_float enemy_rotation[10]
				//generate_random_float_in_range -5.0 5.0 enemy_rotation[a]
				enemy_rotation[a] = 5.0
				lvar_float enemy_move[10]
				//generate_random_float_in_range 2.0 3.0 enemy_move[a]
				enemy_move[a] = 3.0
				lvar_float enemy_distance[10]
				enemy_distance[a] = 10.0
				lvar_int enemy_step[10]
				enemy_step[a] = 0
				lvar_int enemy_timer[10]
				enemy_timer[a] = game_timer + 500
				enemy_alive[a] = 1
				last_enemy_position_x[a] = 320.0
				last_enemy_position_y[a] = 224.0
				generate_enemy_timer = game_timer + 200
				-- enemies_to_generate
				a = 9
			endif
		endrepeat
	endif
endif

any_enemy_alive = 0
repeat 10 a
	if enemy_alive[a] = 1
		if enemy_step[a] < 10
			if enemy_timer[a] < game_timer
				temp_int = enemy_step[a]
				if wave_pattern = 0
					enemy_move[a] = pattern_move_1[temp_int]
					enemy_rotation[a] = pattern_rotation_1[temp_int]
					enemy_timer[a] = game_timer + pattern_timer_1[temp_int]
				endif
				if wave_pattern = 1
					enemy_move[a] = pattern_move_2[temp_int]
					enemy_rotation[a] = pattern_rotation_2[temp_int]
					enemy_timer[a] = game_timer + pattern_timer_2[temp_int]
				endif
				if wave_pattern = 2
					enemy_move[a] = pattern_move_3[temp_int]
					enemy_rotation[a] = pattern_rotation_3[temp_int]
					enemy_timer[a] = game_timer + pattern_timer_3[temp_int]
				endif
				++ enemy_step[a]
			endif
		endif

		any_enemy_alive = 1
		
		enemy_distance[a] += enemy_move[a]
		enemy_heading[a] += enemy_rotation[a]
		
		LIMIT_ANGLE enemy_heading[a] enemy_heading[a]
		COS enemy_heading[a] x
		SIN enemy_heading[a] y

		x *= enemy_distance[a]
		y *= enemy_distance[a]

		lvar_float enemy_position_x[10] enemy_position_y[10]
		lvar_float last_enemy_position_x[10] last_enemy_position_y[10]
		last_enemy_position_x[a] = enemy_position_x[a]
		last_enemy_position_y[a] = enemy_position_y[a]
		enemy_position_x[a] = x + 320.0
		enemy_position_y[a] = y + 224.0

		lvar_float enemy_scale
		enemy_scale = enemy_distance[a] / 5.0
		
		x = enemy_position_x[a] - last_enemy_position_x[a]
		y = enemy_position_y[a] - last_enemy_position_y[a]
		GET_HEADING_FROM_VECTOR_2D x y heading
		
		if wave_pattern = 0
			DRAW_SPRITE_WITH_ROTATION 1 enemy_position_x[a] enemy_position_y[a] enemy_scale enemy_scale heading 220 220 220 255
		else
			if wave_pattern = 1
				DRAW_SPRITE_WITH_ROTATION 22 enemy_position_x[a] enemy_position_y[a] enemy_scale enemy_scale heading 220 220 220 255
			else
				DRAW_SPRITE_WITH_ROTATION 23 enemy_position_x[a] enemy_position_y[a] enemy_scale enemy_scale heading 220 220 220 255
			endif
		endif

		x = ship_heading + 10.0
		y = ship_heading - 10.0
		if enemy_heading[a] < x
		and	enemy_heading[a] > y
			temp_int = 18 - none_level
			if temp_int < 4
				temp_int = 4
			endif
				generate_random_int_in_range 0 temp_int temp_int //Enemy Projectile spawn rng
				if temp_int = 0
					repeat 10 b
						if enemy_projectile_alive[b] = 0
							lvar_float enemy_projectile_heading[10]
							enemy_projectile_heading[b] = enemy_heading[a]
							lvar_float enemy_projectile_distance[10]
							enemy_projectile_distance[b] = enemy_distance[a]
							enemy_projectile_alive[b] = 1
							lvar_float enemy_projectile_speed[10]
							enemy_projectile_speed[b] = 6.0
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_TEMPEST_ENEMY_SHOOT
							b = 9
						endif
					endrepeat
				endif
		endif

		if enemy_distance[a] > 320.0
		or enemy_distance[a] < 10.0
			enemy_alive[a] = 0
		endif
		
		repeat 10 b
			if projectile_alive[b] = 1
				if DO_2D_RECTANGLES_COLLIDE projectile_position_x[b] projectile_position_y[b] 12.0 12.0 enemy_position_x[a] enemy_position_y[a] enemy_scale enemy_scale
					enemy_alive[a] = 0
					projectile_alive[b] = 0
					++ enemy_wave_killed
					if enemy_wave_killed = 10
						lvar_float bonus_score_x bonus_score_y
						bonus_score_x = enemy_position_x[a]
						bonus_score_y = enemy_position_y[a]
						display_bonus_score_timer = game_timer + 1500
						lvar_float warp_pickup_heading warp_pickup_distance
						warp_pickup_heading = enemy_heading[a]
						warp_pickup_distance = enemy_distance[a]
						++ do_warp_pickup
						display_bonus_score = 100 * none_level
						none_score += display_bonus_score
					else
						temp_int = 10 * none_level
						none_score += temp_int
					endif
					repeat 10 c
						IF explosions_flag[c] = 0
							LVAR_FLOAT explosions_x[10] explosions_y[10]
							explosions_x[c] = enemy_position_x[a]
							explosions_y[c] = enemy_position_y[a]
							++ explosions_flag[c]
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_TEMPEST_EXPLOSION
							c = 9
						ENDIF
					endrepeat
					b = 9
				endif
			endif
		endrepeat
	endif
endrepeat

if do_warp = 1
	if any_enemy_alive = 0
		REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_TEMPEST_WARP
		do_warp = 2
	endif
endif

//DRAW ENEMY PROJECTILES///
repeat 10 a
	if enemy_projectile_alive[a] = 1
		enemy_projectile_distance[a] += enemy_projectile_speed[a]

		COS enemy_projectile_heading[a] x
		SIN enemy_projectile_heading[a] y

		x *= enemy_projectile_distance[a]
		y *= enemy_projectile_distance[a]

		lvar_float enemy_projectile_position_x[10] enemy_projectile_position_y[10]
		enemy_projectile_position_x[a] = x + 320.0
		enemy_projectile_position_y[a] = y + 224.0

		DRAW_SPRITE 4 enemy_projectile_position_x[a] enemy_projectile_position_y[a] 8.0 8.0 244 0 0 255
		if not DO_2D_RECTANGLES_COLLIDE enemy_projectile_position_x[a] enemy_projectile_position_y[a] 8.0 8.0 320.0 224.0 640.0 448.0
			enemy_projectile_alive[a] = 0
		endif
		if player_dead_flag = 0
			if DO_2D_RECTANGLES_COLLIDE enemy_projectile_position_x[a] enemy_projectile_position_y[a] 8.0 8.0 ship_position_x ship_position_y ship_collision_x ship_collision_y//56.0 28.0
				//player dead
				if invulnerability_timer < game_timer
					ship_health -= 20
					//if player_dead_flag = 0
					//	player_dead_flag = 1
					//endif
				endif
				enemy_projectile_alive[a] = 0
				repeat 10 c
					IF explosions_flag[c] = 0
						explosions_x[c] = enemy_projectile_position_x[a]
						explosions_y[c] = enemy_projectile_position_y[a]
						++ explosions_flag[c]
						REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_TEMPEST_EXPLOSION
						c = 9
					ENDIF
				endrepeat
			endif
		endif
	endif
endrepeat

if player_dead_flag = 0
	if draw_sheilds > 0
		if draw_sheilds > 1
			sheild_alpha_change = 100
			-- draw_sheilds
		endif
		sheild_alpha += sheild_alpha_change
		if sheild_alpha > 255
			sheild_alpha = 255
			sheild_alpha_change = -20
		endif
		if sheild_alpha < 0
			sheild_alpha = 0
			sheild_alpha_change = 100
			draw_sheilds = 0
		endif
		heading = ship_heading + 90.0
		REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_TEMPEST_SHIELD_GLOW
		DRAW_SPRITE_WITH_ROTATION 17 ship_position_x ship_position_y 64.0 32.0 heading 220 220 220 sheild_alpha
	endif
endif

//DRAW EXPLOSIONS///
repeat 10 a
	IF explosions_flag[a] > 0
		IF explosions_flag[a] = 1
			explosions_flag[a] = 5
		ENDIF
		++ explosions_flag[a]
		IF explosions_flag[a] > 16
			explosions_flag[a] = 0
		ELSE
			DRAW_SPRITE explosions_flag[a] explosions_x[a] explosions_y[a] 32.0 32.0 220 220 220 255
		ENDIF
	ENDIF
endrepeat

if do_warp_pickup = 1
	warp_pickup_distance += 2.0

	LIMIT_ANGLE warp_pickup_heading warp_pickup_heading
	COS warp_pickup_heading x
	SIN warp_pickup_heading y

	x *= warp_pickup_distance
	y *= warp_pickup_distance

	lvar_float warp_pickup_x warp_pickup_y
	warp_pickup_x = x + 320.0
	warp_pickup_y = y + 224.0

	DRAW_SPRITE 18 warp_pickup_x warp_pickup_y 16.0 16.0 255 255 255 255
	if player_dead_flag = 0
		if DO_2D_RECTANGLES_COLLIDE warp_pickup_x warp_pickup_y 16.0 16.0 ship_position_x ship_position_y ship_collision_x ship_collision_y//64.0 32.0
			++ warp_pickup_collected
			if warp_pickup_collected = 1
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_TEMPEST_PICKUP1
			endif
			if warp_pickup_collected = 2
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_TEMPEST_PICKUP2
			endif
			if warp_pickup_collected = 3
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_TEMPEST_PICKUP3
				do_warp = 1
			endif
			do_warp_pickup = 0
		endif
	endif
	if not DO_2D_RECTANGLES_COLLIDE warp_pickup_x warp_pickup_y 16.0 16.0 320.0 224.0 640.0 448.0
		do_warp_pickup = 0
	endif
endif

if ship_health < 0
or ship_health = 0
	ship_health = 0
	if player_dead_flag = 0
		player_dead_flag = 1
	endif
endif

if ship_health < last_ship_health
	last_ship_health = ship_health
	++ draw_sheilds
endif

gosub setup_text_none
set_text_scale 0.6 2.6
display_text 95.0 45.0 NONE_01 //SCORE

gosub setup_text_none
set_text_scale 0.6 2.6
display_text_with_number 95.0 70.0 number none_score//320.0 50.0

gosub setup_text_none
set_text_scale 0.6 2.6
display_text 550.0 45.0 NONE_02 //LIVES

DRAW_SPRITE 20 527.2529 84.2723 48.0 24.0 220 220 220 255

gosub setup_text_none
set_text_centre off
set_text_scale 0.6 2.6
display_text_with_number 554.7283 70.0 NONE_03 ship_lives

gosub setup_text_none
set_text_scale 0.6 2.6
display_text 550.0 350.0 NONE_04 //HEALTH

gosub setup_text_none
set_text_scale 0.6 2.6
display_text_with_number 550.0 375.0 NONE_05 ship_health

gosub setup_text_none
set_text_scale 0.6 2.6
display_text 95.0 350.0 NONE_07 //LEVEL

gosub setup_text_none
set_text_scale 0.6 2.6
display_text_with_number 95.0 375.0 number none_level

if display_bonus_score_timer > game_timer
	gosub setup_text_none
	set_text_colour 0 180 180 255
	set_text_scale 0.5 1.5
	display_text_with_number bonus_score_x bonus_score_y number display_bonus_score
endif

if player_dead_flag = 0
	if do_warp > 0
		gosub setup_text_none
		display_text 320.0 100.0 NONE_06//WARPING
	endif
endif

DRAW_SPRITE 2 160.0 112.0 320.0 224.0 150 150 150 255 
DRAW_SPRITE 2 480.0 112.0 -320.0 224.0 150 150 150 255 
DRAW_SPRITE 2 480.0 336.0 -320.0 -224.0 150 150 150 255 
DRAW_SPRITE 2 160.0 336.0 320.0 -224.0 150 150 150 255 

IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
	quit_game = 1
ENDIF

if quit_game = 1
	if none_score >	none_hiscore[9]
		lvar_int letter	input_letter
		letter = 10
		input_letter = 0
		GOTO none_hiscore_loop
	else
		GOTO none_frontend_loop
	endif
endif

GOTO none_game_loop/////////////////////////////////////////////////////////////////////


none_hiscore_loop://////////////////////////////////////////////////////////////////////////

wait 0
draw_rect 320.0 224.0 640.0 448.0 0 0 0 255

get_game_timer game_timer

a = 9
while a > -1
	if none_score > none_hiscore[a]
		if not a = 9
			b = a + 1
			none_hiscore[b] = none_hiscore[a]
			$none_letter1[b] = $none_letter1[a]
			$none_letter2[b] = $none_letter2[a]
			$none_letter3[b] = $none_letter3[a]
		endif
		none_hiscore[a] = none_score
		$none_letter1[a] = $none_char[36]
		$none_letter2[a] = $none_char[36]
		$none_letter3[a] = $none_char[36]
		new_high_score = a
		if a = 0
			none_score = 0
		endif
	else
		none_score = 0
	endif
	-- a
endwhile

if new_high_score > -1
	if input_letter = 0
		$none_letter1[new_high_score] = $none_char[letter]
		$none_letter2[new_high_score] = $none_char[36]
		$none_letter3[new_high_score] = $none_char[36]
	endif
	if input_letter = 1
		$none_letter2[new_high_score] = $none_char[letter]
		$none_letter3[new_high_score] = $none_char[36]
	endif
	if input_letter = 2
		$none_letter3[new_high_score] = $none_char[letter]
	endif

	GET_POSITION_OF_ANALOGUE_STICKS PAD1 left_stick_x left_stick_y temp_int temp_int
	IF IS_BUTTON_PRESSED PAD1 DPADUP
	OR left_stick_y < -100
		if pad1_dpadup_pressed < game_timer
			IF letter < 36 
				++ letter
			ELSE
				letter = 0
			ENDIF
			pad1_dpadup_pressed = game_timer + 250
		endif
	else
		pad1_dpadup_pressed = game_timer - 250
	ENDIF

	IF IS_BUTTON_PRESSED PAD1 DPADDOWN
	OR left_stick_y > 100
		if pad1_dpaddown_pressed < game_timer
			IF letter > 0
				-- letter
			ELSE
				letter = 36
			ENDIF
			pad1_dpaddown_pressed = game_timer + 250
		endif
	else
		pad1_dpaddown_pressed = game_timer - 250
	ENDIF
	
	IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT

		IF pad1_cross_pressed = 0
			++ input_letter
			++ pad1_cross_pressed
			if input_letter = 3
				new_high_score = -1
			endif
		endif
	ELSE
		pad1_cross_pressed = 0
	ENDIF
ENDIF

repeat 60 a
	dust_distance[a] +=	8.0

	COS dust_heading[a] x
	SIN dust_heading[a] y

	x *= dust_distance[a]
	y *= dust_distance[a]

	x += 320.0
	y += 224.0

	draw_rect x y 1.0 1.0 255 255 255 255
	
	if x > 640.0
	or x < 0.0
	or y < 0.0
	or y > 448.0
		dust_distance[a] = 10.0
		generate_random_float_in_range 0.0 360.0 dust_heading[a]
	endif

endrepeat

gosub setup_text_none
set_text_font FONT_STANDARD
display_text title_x title_y SPAC_06 //HISCORE

lvar_float highscore_x highscore_y
highscore_x = top_row_x
highscore_y	= top_row_y

a = 0
while a < 10

	highscore_x = top_row_x
	
	//first letter
	gosub setup_text_none
	set_text_centre off
	set_text_justify on
	set_text_font FONT_STANDARD
	if new_high_score = a
		set_text_colour 0 155 0 255
	endif
	if $none_letter1[a] = $none_char[1]
	or $none_letter1[a] = $none_char[18]
		highscore_x += 8.0
	endif
	display_text highscore_x highscore_y $none_letter1[a]
	if $none_letter1[a] = $none_char[1]
	or $none_letter1[a] = $none_char[18]
		highscore_x -= 8.0
	endif

	highscore_x += next_letter_x
	
	//second letter
	gosub setup_text_none
	set_text_centre off
	set_text_justify on
	set_text_font FONT_STANDARD
	if new_high_score = a
		set_text_colour 0 155 0 255
	endif
	if $none_letter2[a] = $none_char[1]
	or $none_letter2[a] = $none_char[18]
		highscore_x += 8.0
	endif
	display_text highscore_x highscore_y $none_letter2[a]
	if $none_letter2[a] = $none_char[1]
	or $none_letter2[a] = $none_char[18]
		highscore_x -= 8.0
	endif

	highscore_x += next_letter_x
	
	//third letter
	gosub setup_text_none
	set_text_centre off
	set_text_justify on
	set_text_font FONT_STANDARD
	if new_high_score = a
		set_text_colour 0 155 0 255
	endif
	if $none_letter3[a] = $none_char[1]
	or $none_letter3[a] = $none_char[18]
		highscore_x += 8.0
	endif
	display_text highscore_x highscore_y $none_letter3[a]
	if $none_letter3[a] = $none_char[1]
	or $none_letter3[a] = $none_char[18]
		highscore_x -= 8.0
	endif

	highscore_x = top_row_x + score_dist_x
	
	//score
	gosub setup_text_none
	set_text_centre off
	set_text_right_justify on
	set_text_font FONT_STANDARD
	if new_high_score = a
		set_text_colour 0 155 0 255
	endif
	display_text_with_number highscore_x highscore_y NUMBER none_hiscore[a]//HISCORE

	highscore_y += next_row_y

	++ a
endwhile

DRAW_SPRITE 2 160.0 112.0 320.0 224.0 150 150 150 255 
DRAW_SPRITE 2 480.0 112.0 -320.0 224.0 150 150 150 255 
DRAW_SPRITE 2 480.0 336.0 -320.0 -224.0 150 150 150 255 
DRAW_SPRITE 2 160.0 336.0 320.0 -224.0 150 150 150 255 

if new_high_score = -1
	
	IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
		GOTO none_frontend_loop
	endif

	IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
		IF pad1_cross_pressed = 0
			++ pad1_cross_pressed
			GOTO none_frontend_loop
		endif
	ELSE
		pad1_cross_pressed = 0
	ENDIF
endif

GOTO none_hiscore_loop//////////////////////////////////////////////////////////////////////////



setup_text_none:///////////////////////////////////////////////////////////////////////////
	//FONT_BANK = SA stialized font
	//FONT_STANDARD	= italic normal font
	//FONT_SPACEAGE	= large print spacege style
	//FONT_HEADING = GTA mission passed font
	set_text_colour 180 180 180 255
	set_text_scale 1.0 3.0
	set_text_right_justify off
	set_text_justify off
	set_text_centre on
	set_text_wrapx 640.0
	set_text_proportional on
	set_text_background off
	set_text_dropshadow 0 0 0 0 180
	IF IS_JAPANESE_VERSION
		set_text_font FONT_STANDARD
	ELSE
		set_text_font FONT_SPACEAGE
	ENDIF
return//////////////////////////////////////////////////////////////////////////////////////



// *********************************** MISSION CLEANUP *************************************
mission_none_cleanup:

REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_TEMPEST_TRACK_STOP

CLEAR_THIS_PRINT BUSY

SHUT_ALL_CHARS_UP FALSE

flag_player_on_mission = 0

MISSION_HAS_FINISHED
RETURN
}

