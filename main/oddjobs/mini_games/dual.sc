MISSION_START
// *****************************************************************************************
// ***																					 ***
// *** Date: 18/06/2004 	Time: 14:35:45	   Name:  Chris Rothwell 					 ***
// ***																					 ***
// *** Mission: DUALITY CONSOLE GAME 	 												 ***
// ***																					 ***
// *****************************************************************************************

GOSUB mission_dual_start

GOSUB mission_dual_cleanup

MISSION_END
 
// ************************************ MISSION START **************************************
{
mission_dual_start:

flag_player_on_mission = 1

//REGISTER_MISSION_GIVEN
SCRIPT_NAME dual

WAIT 0

LOAD_MISSION_TEXT dual
LVAR_INT mission_blip mission_flag mission_timer sequence_task players_car temp_int	a b
LVAR_FLOAT h x2 y2 z2 x3 y3 z3 speed temp_float

USE_TEXT_COMMANDS TRUE


REMOVE_TEXTURE_DICTIONARY
LOAD_TEXTURE_DICTIONARY ld_spac
SHUT_ALL_CHARS_UP TRUE
get_char_coordinates scplayer x y z
clear_area x y z 50.0 0

//SET_MUSIC_DOES_FADE FALSE

CONST_INT BACKGROUND 1
CONST_INT STARLAYER 2
CONST_INT TV_BORDER 3
CONST_INT PLAYER_SHIP 4
CONST_INT THRUSTERS 5

CONST_INT DARK_STAR 7
CONST_INT LIGHT_STAR 8
CONST_INT EXPLOSION1 9
CONST_INT EXPLOSION2 10
CONST_INT EXPLOSION3 11
CONST_INT EXPLOSION4 12

CONST_INT WHITE_S 13
CONST_INT SHOT_BALL 14
CONST_INT HEALTH_BAR 15
CONST_INT POWER_BAR 16
CONST_INT DUALITY_LOGO 17
CONST_INT BLACKSPRITE 18

LOAD_SPRITE BACKGROUND backgnd
LOAD_SPRITE STARLAYER layer
LOAD_SPRITE TV_BORDER tvcorn
LOAD_SPRITE PLAYER_SHIP rockshp
LOAD_SPRITE THRUSTERS thrustg

LOAD_SPRITE DARK_STAR dark
LOAD_SPRITE LIGHT_STAR light
LOAD_SPRITE EXPLOSION1 ex1
LOAD_SPRITE EXPLOSION2 ex2
LOAD_SPRITE EXPLOSION3 ex3
LOAD_SPRITE EXPLOSION4 ex4

LOAD_SPRITE WHITE_S white
LOAD_SPRITE SHOT_BALL shoot
LOAD_SPRITE HEALTH_BAR health
LOAD_SPRITE POWER_BAR power
LOAD_SPRITE DUALITY_LOGO duality
LOAD_SPRITE BLACKSPRITE black

load_mission_audio 4 SOUND_BANK_DUAL
while not has_mission_audio_loaded 4
	wait 0
endwhile
REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_TRACK_START

lvar_int random_menu
generate_random_int_in_range 0 3 random_menu


switch random_menu
case 0
//top
lvar_float x_pos[12] y_pos[12] x_scale[12] y_scale[12]
x_pos[0]   = 160.0 //BLACKSPRITE
y_pos[0]   = 224.0
x_scale[0] = 320.0
y_scale[0] = 500.0

x_pos[1]   = 480.0 //WHITE_S
y_pos[1]   = 224.0
x_scale[1] = 320.0
y_scale[1] = 500.0

x_pos[2] = 320.0 //DUALITY_LOGO
y_pos[2] = 156.0 
x_scale[2] = 256.0 
y_scale[2] = 128.0 

x_pos[3] = 452.2068 //DARK_STAR
y_pos[3] = 90.0 
x_scale[3] = 32.0 
y_scale[3] = 32.0 

x_pos[4] = 166.5889 //LIGHT_STAR
y_pos[4] = 216.6436 
x_scale[4] = 32.0 
y_scale[4] = 32.0 

x_pos[5] = 262.0 //PLAY
y_pos[5] = 300.0 
x_scale[5] = 1.0 
y_scale[5] = 3.0 

x_pos[6] = 370.0 //QUIT
x_scale[6] = 0.9 
y_scale[6] = 3.0 

y_pos[7] = 342.0 
x_scale[7] = 320.0 
y_scale[7] = 229.7492 

x_pos[8] = 284.4967 //SELECTION - STAR - WHITE TOP LEFT
y_pos[8] = 314.9901 
x_scale[8] = 320.0 
y_scale[8] = 320.0 

x_pos[9] = 351.9814 //SELECTION - STAR - DARK BOTTOM RIGHT
y_pos[9] = 356.8015 
break 
case 1
//centred
x_pos[0] = 160.0 
y_pos[0] = 224.0 
x_scale[0] = 320.0 
y_scale[0] = 500.0 

x_pos[1] = 480.0 
y_pos[1] = 224.0 
x_scale[1] = 320.0 
y_scale[1] = 500.0 

x_pos[2] = 320.0 
y_pos[2] = 219.5610 
x_scale[2] = 256.0 
y_scale[2] = 128.0 

x_pos[3] = 452.2068 
y_pos[3] = 215.0997 
x_scale[3] = 32.0 
y_scale[3] = 32.0 

x_pos[4] = 166.5889 
y_pos[4] = 216.0905 
x_scale[4] = 32.0 
y_scale[4] = 32.0 

x_pos[5] = 265.0504 
y_pos[5] = 70.0389 
x_scale[5] = 1.0 
y_scale[5] = 3.0 

x_pos[6] = 370.0 
x_scale[6] = 0.9 
y_scale[6] = 3.0 

y_pos[7] = 342.0 
x_scale[7] = 320.0 
y_scale[7] = 91.2330 

x_pos[8] = 284.4967 
y_pos[8] = 84.2511 
x_scale[8] = 320.0 
y_scale[8] = 296.2502 

x_pos[9] = 351.9814 
y_pos[9] = 356.8015 
break
case 2
//bottom
x_pos[0] = 160.0 
y_pos[0] = 224.0 
x_scale[0] = 320.0 
y_scale[0] = 500.0 

x_pos[1] = 480.0 
y_pos[1] = 224.0 
x_scale[1] = 320.0 
y_scale[1] = 500.0 

x_pos[2] = 320.0 
y_pos[2] = 296.0475 
x_scale[2] = 256.0 
y_scale[2] = 128.0 

x_pos[3] = 464.3830 
y_pos[3] = 354.7318 
x_scale[3] = 32.0 
y_scale[3] = 32.0 

x_pos[4] = 166.5889 
y_pos[4] = 216.0905 
x_scale[4] = 32.0 
y_scale[4] = 32.0 

x_pos[5] = 265.0504 
y_pos[5] = 70.0389 
x_scale[5] = 1.0 
y_scale[5] = 3.0 

x_pos[6] = 370.0 
x_scale[6] = 0.9 
y_scale[6] = 3.0 

y_pos[7] = 119.7153 
x_scale[7] = 320.0 
y_scale[7] = 135.5804 

x_pos[8] = 284.4967 
y_pos[8] = 84.2511 
x_scale[8] = 320.0 
y_scale[8] = 320.0 

x_pos[9] = 351.9814 
y_pos[9] = 134.4938 
break
endswitch

x_pos[10] = 33.8609 
x_scale[9] = 160.1144
x_scale[10] = 230.0

x_pos[11] = 38.1753 
x_scale[11] = 0.5014 
y_scale[11] = 1.8889 

IF IS_XBOX_VERSION
	y_pos[10] = 33.1114 
	y_scale[9] = 109.8235 
	y_scale[10] = 75.3475 
	y_pos[11] = 35.4681 
ELSE
	y_pos[10] = 18.1114 
	y_scale[9] = 94.8235 
	y_scale[10] = 60.3475 
	y_pos[11] = 20.4681 
ENDIF

lvar_int menu_selection
menu_selection = 0

lvar_int pad1_cross_pressed	pad1_dpadleft_pressed pad1_dpadright_pressed pad1_dpadup_pressed pad1_dpaddown_pressed
pad1_cross_pressed = 1
pad1_dpadleft_pressed = 1
pad1_dpadright_pressed = 1
pad1_dpadup_pressed = 1
pad1_dpaddown_pressed = 1

lvar_text_label	$dual_char[37]
$dual_char[0]  = DUAL_0	// 0
$dual_char[1]  = DUAL_1	// 1
$dual_char[2]  = DUAL_2	// 2
$dual_char[3]  = DUAL_3	// 3
$dual_char[4]  = DUAL_4	// 4
			 	 
$dual_char[5]  = DUAL_5	// 5
$dual_char[6]  = DUAL_6	// 6
$dual_char[7]  = DUAL_7	// 7
$dual_char[8]  = DUAL_8	// 8
$dual_char[9]  = DUAL_9	// 9
			  
$dual_char[10] = DUAL_AA// A
$dual_char[11] = DUAL_B	// B
$dual_char[12] = DUAL_C	// C
$dual_char[13] = DUAL_D	// D
$dual_char[14] = DUAL_E	// E
		     		
$dual_char[15] = DUAL_F	// F
$dual_char[16] = DUAL_G	// G
$dual_char[17] = DUAL_H	// H
$dual_char[18] = DUAL_I	// I
$dual_char[19] = DUAL_J	// J
		 		   
$dual_char[20] = DUAL_K	// K
$dual_char[21] = DUAL_L	// L
$dual_char[22] = DUAL_M	// M
$dual_char[23] = DUAL_N	// N
$dual_char[24] = DUAL_O	// O
	  		
$dual_char[25] = DUAL_P	// P
$dual_char[26] = DUAL_Q	// Q
$dual_char[27] = DUAL_R	// R
$dual_char[28] = DUAL_S	// S
$dual_char[29] = DUAL_T	// T
	  		
$dual_char[30] = DUAL_U	// U
$dual_char[31] = DUAL_V	// V
$dual_char[32] = DUAL_W	// W
$dual_char[33] = DUAL_X	// X
$dual_char[34] = DUAL_Y	// Y
			  	 
$dual_char[35] = DUAL_Z	// Z
$dual_char[36] = DUAL_FS// .

var_int played_dual_before
if played_dual_before = 0
	var_int dark_hiscore[10]
	dark_hiscore[0]	= -10000
	dark_hiscore[1]	= -7500
	dark_hiscore[2]	= -5000
	dark_hiscore[3]	= -2500
	dark_hiscore[4]	= -1000

	dark_hiscore[5]	= -750
	dark_hiscore[6]	= -500
	dark_hiscore[7]	= -250
	dark_hiscore[8]	= -175
	dark_hiscore[9]	= -100

	var_int light_hiscore[10]
	light_hiscore[0] = 10000
	light_hiscore[1] = 7500
	light_hiscore[2] = 5000
	light_hiscore[3] = 2500
	light_hiscore[4] = 1000

	light_hiscore[5] = 750
	light_hiscore[6] = 500
	light_hiscore[7] = 250
	light_hiscore[8] = 175
	light_hiscore[9] = 100

	var_text_label	$dark_letter1[10] $dark_letter2[10] $dark_letter3[10]
	$dark_letter1[0] = $dual_char[13]
	$dark_letter2[0] = $dual_char[19]
	$dark_letter3[0] = $dual_char[36]

	$dark_letter1[1] = $dual_char[20]
	$dark_letter2[1] = $dual_char[27]
	$dark_letter3[1] = $dual_char[34]

	$dark_letter1[2] = $dual_char[28]
	$dark_letter2[2] = $dual_char[18]
	$dark_letter3[2] = $dual_char[28]

	$dark_letter1[3] = $dual_char[32]
	$dark_letter2[3] = $dual_char[13]
	$dark_letter3[3] = $dual_char[34]

	$dark_letter1[4] = $dual_char[13]
	$dark_letter2[4] = $dual_char[10]
	$dark_letter3[4] = $dual_char[23]

	$dark_letter1[5] = $dual_char[21]
	$dark_letter2[5] = $dual_char[27]
	$dark_letter3[5] = $dual_char[16]

	$dark_letter1[6] = $dual_char[23]
	$dark_letter2[6] = $dual_char[24]
	$dark_letter3[6] = $dual_char[23]

	$dark_letter1[7] = $dual_char[16]
	$dark_letter2[7] = $dual_char[10]
	$dark_letter3[7] = $dual_char[35]

	$dark_letter1[8] = $dual_char[1]
	$dark_letter2[8] = $dual_char[0]
	$dark_letter3[8] = $dual_char[1]

	$dark_letter1[9] = $dual_char[19]
	$dark_letter2[9] = $dual_char[30]
	$dark_letter3[9] = $dual_char[13]

	var_text_label	$light_letter1[10] $light_letter2[10] $light_letter3[10]
	$light_letter1[0] = $dual_char[10]
	$light_letter2[0] = $dual_char[13]
	$light_letter3[0] = $dual_char[35]

	$light_letter1[1] = $dual_char[12]
	$light_letter2[1] = $dual_char[10]
	$light_letter3[1] = $dual_char[23]

	$light_letter1[2] = $dual_char[18]
	$light_letter2[2] = $dual_char[22]
	$light_letter3[2] = $dual_char[34]

	$light_letter1[3] = $dual_char[32]
	$light_letter2[3] = $dual_char[18]
	$light_letter3[3] = $dual_char[21]

	$light_letter1[4] = $dual_char[13]
	$light_letter2[4] = $dual_char[11]
	$light_letter3[4] = $dual_char[25]

	$light_letter1[5] = $dual_char[13]
	$light_letter2[5] = $dual_char[10]
	$light_letter3[5] = $dual_char[31]

	$light_letter1[6] = $dual_char[13]
	$light_letter2[6] = $dual_char[24]
	$light_letter3[6] = $dual_char[13]

	$light_letter1[7] = $dual_char[23]
	$light_letter2[7] = $dual_char[15]
	$light_letter3[7] = $dual_char[36]

	$light_letter1[8] = $dual_char[20]
	$light_letter2[8] = $dual_char[22]
	$light_letter3[8] = $dual_char[11]

	$light_letter1[9] = $dual_char[28]
	$light_letter2[9] = $dual_char[19]
	$light_letter3[9] = $dual_char[21]

	played_dual_before = 1
endif

lvar_int new_high_score
new_high_score = -1

lvar_int highscore_table
highscore_table	= 0

lvar_float title_x title_y
title_x = 321.4137
title_y = 58.2432 

lvar_float top_row_x top_row_y
top_row_x = 201.2250
top_row_y = 88.9168 

lvar_float next_letter_x score_dist_x next_row_y
next_letter_x = 29.7800
score_dist_x = 230.5296
next_row_y = 28.5713

//lvar_int duality_ending	duality_ending_timer
//duality_ending = 0
//duality_ending_timer = 0


dual_frontend_loop://////////////////////////////////////////////////////////////////////////
	wait 0

	do_fade 0 fade_in
	get_game_timer game_timer

	dual_score = 0

	GET_POSITION_OF_ANALOGUE_STICKS PAD1 left_stick_x left_stick_y temp_int temp_int
	
	IF IS_BUTTON_PRESSED PAD1 DPADRIGHT
	OR left_stick_x > 100
		if pad1_dpadright_pressed = 0
			if menu_selection = 0//PLAY
				menu_selection = 1//QUIT
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_MENU_DESELECT
			else
				if menu_selection = 2//HISCORE - WHITE
					menu_selection = 3//HISCORE - BLACKSPRITE
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_MENU_DESELECT
				endif
			endif
		endif
		pad1_dpadright_pressed = 1
	else
		IF IS_BUTTON_PRESSED PAD1 DPADLEFT
		OR left_stick_x < -100
			if pad1_dpadright_pressed = 0
				if menu_selection = 1//QUIT
					menu_selection = 0//PLAY
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_MENU_DESELECT
				else
					if menu_selection = 3//HISCORE - BLACKSPRITE
						menu_selection = 2//HISCORE - WHITE
						REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_MENU_DESELECT
					endif
				endif
			endif
			pad1_dpadleft_pressed = 1
		else
			IF IS_BUTTON_PRESSED PAD1 DPADUP
			OR left_stick_y < -100
				if pad1_dpadup_pressed = 0
					if menu_selection = 2//HISCORE - WHITE
						menu_selection = 0//PLAY
						REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_MENU_DESELECT
					else
						if menu_selection = 3//HISCORE - BLACKSPRITE
							menu_selection = 1//QUIT
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_MENU_DESELECT
						endif
					endif
				endif
				pad1_dpadup_pressed = 1
			else
				IF IS_BUTTON_PRESSED PAD1 DPADDOWN
				OR left_stick_y > 100
					if pad1_dpaddown_pressed = 0
						if menu_selection = 0//PLAY
							menu_selection = 2//HISCORE - WHITE
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_MENU_DESELECT
						else
							if menu_selection = 1//QUIT
								menu_selection = 3//HISCORE - BLACKSPRITE
								REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_MENU_DESELECT
							endif
						endif
					endif
					pad1_dpaddown_pressed = 1
				else
					pad1_dpaddown_pressed = 0
				endif
				pad1_dpadup_pressed = 0
			endif	
			pad1_dpadleft_pressed = 0
		endif
		pad1_dpadright_pressed = 0
	endif

	
	DRAW_SPRITE BLACKSPRITE x_pos[0] y_pos[0] x_scale[0] y_scale[0] 255 255 255 255
	DRAW_SPRITE WHITE_S x_pos[1] y_pos[1] x_scale[1] y_scale[1] 255 255 255 255
	DRAW_SPRITE DUALITY_LOGO x_pos[2] y_pos[2] 256.0 128.0 255 255 255 255
	DRAW_SPRITE DARK_STAR x_pos[3] y_pos[3] 32.0 32.0 150 150 150 255
	DRAW_SPRITE LIGHT_STAR x_pos[4] y_pos[4] 32.0 32.0 255 255 255 255

//	if duality_ending = 0
		gosub setup_text_dual
		set_text_centre OFF
		set_text_right_justify on
		set_text_colour 255 255 255 255
		if current_language = LANGUAGE_SPANISH
			set_text_scale x_scale[6] y_scale[6]
		else
			set_text_scale x_scale[5] y_scale[5]
		endif
		display_text x_pos[5] y_pos[5] SPAC_04 //PLAY

		gosub setup_text_dual
		set_text_centre OFF
		set_text_justify on
		set_text_colour 0 0 0 255
		if current_language = LANGUAGE_SPANISH
			set_text_scale x_scale[6] y_scale[6]
		else
			set_text_scale x_scale[5] y_scale[5]
		endif
		display_text x_pos[6] y_pos[5] SPAC_05 //QUIT

		gosub setup_text_dual
		set_text_centre OFF
		set_text_right_justify on
		set_text_colour 255 255 255 255
		if current_language = LANGUAGE_SPANISH
			set_text_scale x_scale[6] y_scale[6]
		else
			set_text_scale x_scale[5] y_scale[5]
		endif
		display_text x_pos[5] y_pos[7] SPAC_06 //HISCORE

		gosub setup_text_dual
		set_text_centre OFF
		set_text_justify on
		set_text_colour 0 0 0 255
		if current_language = LANGUAGE_SPANISH
			set_text_scale x_scale[6] y_scale[6]
		else
			set_text_scale x_scale[5] y_scale[5]
		endif
		display_text x_pos[6] y_pos[7] SPAC_06 //HISCORE

		if menu_selection = 0//PLAY
			DRAW_SPRITE LIGHT_STAR x_pos[8] y_pos[8] 16.0 16.0 255 255 255 255
		endif
		if menu_selection = 1//QUIT
			DRAW_SPRITE DARK_STAR x_pos[9] y_pos[8] 16.0 16.0 255 255 255 255
		endif
		if menu_selection = 2//HISCORE - WHITE
			DRAW_SPRITE LIGHT_STAR x_pos[8] y_pos[9] 16.0 16.0 255 255 255 255
		endif
		if menu_selection = 3//HISCORE - BLACKSPRITE
			DRAW_SPRITE DARK_STAR x_pos[9] y_pos[9] 16.0 16.0 255 255 255 255
		endif
	
		DRAW_SPRITE TV_BORDER 160.0 112.0 320.0 224.0 150 150 150 255 
		DRAW_SPRITE TV_BORDER 480.0 112.0 -320.0 224.0 150 150 150 255 
		DRAW_SPRITE TV_BORDER 480.0 336.0 -320.0 -224.0 150 150 150 255 
		DRAW_SPRITE TV_BORDER 160.0 336.0 320.0 -224.0 150 150 150 255

		IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
			IF pad1_cross_pressed = 0
				++ pad1_cross_pressed
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_MENU_SELECT
				if menu_selection = 0//PLAY
					GOTO initialise_game_variables
				endif
				if menu_selection = 1//QUIT
					REMOVE_TEXTURE_DICTIONARY
					set_player_control player1 on
					do_fade 0 fade_out
					wait 0
					do_fade 500 fade_in
					return
				endif
				if menu_selection = 2//HISCORE - WHITE
					generate_random_float_in_range -15.0 15.0 temp_float
					last_ship_position_x = temp_float
					generate_random_float_in_range -15.0 15.0 temp_float
					last_ship_position_y = temp_float
					ship_position_x = 0.0
					ship_position_y = 0.0
					highscore_table = 1
					GOTO dual_hiscore_loop
				endif
				if menu_selection = 3//HISCORE - BLACKSPRITE
					generate_random_float_in_range -15.0 15.0 temp_float
					last_ship_position_x = temp_float
					generate_random_float_in_range -15.0 15.0 temp_float
					last_ship_position_y = temp_float
					ship_position_x = 0.0
					ship_position_y = 0.0
					highscore_table = 0
					GOTO dual_hiscore_loop
				endif
			ENDIF
		ELSE
			pad1_cross_pressed = 0
		ENDIF

	draw_window x_pos[10] y_pos[10] x_scale[10] y_scale[10] DUMMY SWIRLS_NONE
	gosub setup_text_dual
	set_text_centre OFF
	set_text_wrapx x_scale[10]
	set_text_font FONT_STANDARD
	set_text_scale x_scale[11] y_scale[11]
	display_text x_pos[11] y_pos[11] SPAC_10

GOTO dual_frontend_loop//////////////////////////////////////////////////////////////////////////


initialise_game_variables://////////////////////////////////////////////////////////////////////////

LVAR_FLOAT background_x[4] background_y[4] layer_x layer_y
background_x[0] = 0.0
background_y[0] = 0.0
background_x[1] = 0.0
background_y[1] = 0.0

LVAR_FLOAT ship_position_x ship_position_y
ship_position_x	= 0.0
ship_position_y	= 0.0

VAR_FLOAT ship_velocity_x ship_velocity_y
ship_velocity_x	= 0.0
ship_velocity_y	= 0.0

LVAR_FLOAT ship_forces_x ship_forces_y
ship_forces_x = 0.0
ship_forces_y = 0.0

LVAR_FLOAT ship_heading
ship_heading = 0.1

LVAR_FLOAT ship_mass_kg
ship_mass_kg = 100.0

LVAR_FLOAT last_ship_position_x	last_ship_position_y
last_ship_position_x = 0.0
last_ship_position_y = 0.0

LVAR_INT draw_thruster
draw_thruster = 0

LVAR_FLOAT step_x[5] step_y[4]
step_x[0] =	-512.0
step_x[1] = -256.0
step_x[2] =    0.0
step_x[3] =  256.0
step_x[4] =  512.0
step_y[0] = -384.0
step_y[1] = -128.0
step_y[2] =  128.0
step_y[3] =  384.0

LVAR_FLOAT gravity_position_x[16] gravity_position_y[16] gravity_mass[16]
gravity_position_x[0] =	200.0
gravity_position_y[0] =	200.0
gravity_mass[0]	= -200000000000.0

gravity_position_x[1] =	-300.0
gravity_position_y[1] =	-300.0
gravity_mass[1]	= -200000000000.0

gravity_position_x[2] =	-300.0
gravity_position_y[2] =	300.0
gravity_mass[2]	= -200000000000.0

gravity_position_x[3] =	300.0
gravity_position_y[3] =	-300.0
gravity_mass[3]	= -200000000000.0

gravity_position_x[4] =	0.1
gravity_position_y[4] =	300.0
gravity_mass[4]	= -200000000000.0

gravity_position_x[5] =	0.1
gravity_position_y[5] =	-300.0
gravity_mass[5]	= 200000000000.0

gravity_position_x[6] =	300.0
gravity_position_y[6] =	0.1
gravity_mass[6]	= 200000000000.0

gravity_position_x[7] =	-300.0
gravity_position_y[7] =	0.1
gravity_mass[7]	= 200000000000.0

gravity_position_x[8] =	0.1
gravity_position_y[8] =	500.0
gravity_mass[8]	= 200000000000.0

gravity_position_x[9] =	0.1
gravity_position_y[9] =	-500.0
gravity_mass[9]	= 200000000000.0

gravity_position_x[10] = -500.0
gravity_position_y[10] = -500.0
gravity_mass[10] = -200000000000.0

gravity_position_x[11] = 500.0
gravity_position_y[11] = 500.0
gravity_mass[11] = 200000000000.0

gravity_position_x[12] = 500.0
gravity_position_y[12] = -500.0
gravity_mass[12] = -200000000000.0

gravity_position_x[13] = -500.0
gravity_position_y[13] = -500.0
gravity_mass[13] = 200000000000.0

gravity_position_x[14] = 0.1
gravity_position_y[14] = 500.0
gravity_mass[14] = -200000000000.0

gravity_position_x[15] = 0.1
gravity_position_y[15] = -500.0
gravity_mass[15] = 200000000000.0

LVAR_INT total_gravity_wells
total_gravity_wells = 16

LVAR_FLOAT ship_scale
ship_scale = 32.0

lvar_float universal_gravity
universal_gravity = 6.67259 * 0.1//UNIVERSAL GRAVITY CONSTANT
universal_gravity *= 0.1
universal_gravity *= 0.1
universal_gravity *= 0.1
universal_gravity *= 0.1
universal_gravity *= 0.1

LVAR_FLOAT power_pill_x[20] power_pill_y[20]
a = 0
WHILE a < 20
	power_pill_x[a] = 1000.0
	power_pill_y[a] = 1000.0
	++ a
ENDWHILE

LVAR_FLOAT random_floats_x[16] random_floats_y[16]
a = 0
WHILE a < total_gravity_wells
	GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 1.0 random_floats_x[a]
	GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 1.0 random_floats_y[a]
	++ a
ENDWHILE

LVAR_INT explosions_flag[10]
a = 0
WHILE a < 10
	explosions_flag[a] = 0
	++ a
ENDWHILE

LVAR_INT dual_score
dual_score = 0

set_player_control player1 off


lvar_float ship_power ship_health
ship_power = 100.0
ship_health = 100.0

lvar_float tile_size
tile_size = 256.0

lvar_int quit_game
quit_game = 0

GET_GAME_TIMER game_timer
lvar_int help_prompt_timer
help_prompt_timer = game_timer + 4000
lvar_int ship_dead_timer
ship_dead_timer = game_timer

lvar_int pad1_triangle_pressed
pad1_triangle_pressed = 0


dual_game_loop://////////////////////////////////////////////////////////////////////////


LVAR_INT game_timer_last
game_timer_last	= game_timer

WAIT 0

//THIS GAME NEEDS ITS OWN TIME SYSTEM////
GET_GAME_TIMER game_timer
LVAR_INT frame_time
frame_time = game_timer	- game_timer_last
LVAR_FLOAT time_step_fl
time_step_fl =# frame_time
time_step_fl /= 1.5
time_step_fl /= 1000.0

//RESET FORCES ACTING ON SHIP FOR THIS FRAME////
ship_forces_x = 0.0
ship_forces_y = 0.0

//BUILD UP SHIP POWER SLOWLY////
ship_power +=@ 0.06
if ship_power > 100.0
	ship_power = 100.0
endif

LVAR_INT left_stick_x left_stick_y

//CONTROL CHECKS////
IF IS_BUTTON_PRESSED PAD1 TRIANGLE
	if pad1_triangle_pressed = 1
		pad1_triangle_pressed = 0
		quit_game = 1
	endif
else
	pad1_triangle_pressed = 1
ENDIF
	IF IS_BUTTON_PRESSED PAD1 DPADRIGHT
    	ship_heading +=@ 5.0
	ELSE
		IF IS_BUTTON_PRESSED PAD1 DPADLEFT
    		ship_heading +=@ -5.0
		ELSE
			GET_POSITION_OF_ANALOGUE_STICKS PAD1 left_stick_x left_stick_y temp_int temp_int
			temp_float =# left_stick_x
			temp_float /= 15.0
    		ship_heading += temp_float
		ENDIF
	ENDIF
	WHILE ship_heading < 0.1
		ship_heading += 360.0
	ENDWHILE
	WHILE ship_heading > 360.0
		ship_heading -= 360.0
	ENDWHILE
    

//CALCULATE GRAVITY WELLS////
a = 0
WHILE a < total_gravity_wells
	//FORCE_TO_APPLY = (6.67259 10-11 * (SHIP_MASS * GRAVITY_MASS)) / (DISTANCE\2)
	gravity_position_x[a] += random_floats_x[a]
	gravity_position_y[a] += random_floats_y[a]
	GET_DISTANCE_BETWEEN_COORDS_2D ship_position_x ship_position_y gravity_position_x[a] gravity_position_y[a] distance
	lvar_int kill_gravity_well
	kill_gravity_well = 0
	if distance > 700.0
		kill_gravity_well = 1
	endif
	if distance < 65.0
		if DO_2D_RECTANGLES_COLLIDE ship_position_x ship_position_y 32.0 32.0 gravity_position_x[a] gravity_position_y[a] 32.0 32.0
			if gravity_mass[a] > 0.0
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_TOUCH_DARK
				ship_health -=@ 1.0
				if ship_health < 0.0
					ship_health = 0.0
					b = 0
					WHILE b < 10
						IF explosions_flag[b] = 0
							explosions_x[b] = ship_position_x
							explosions_y[b] = ship_position_y
							++ explosions_flag[b]
							b = 9
						ENDIF
						++ b
					ENDWHILE
					ship_dead_timer = game_timer + 3000
				endif
			else
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_TOUCH_LIGHT
				ship_health +=@ 1.0
				if ship_health > 100.0
					ship_health = 100.0
				endif
			endif
		endif
	endif
	b = 0
	WHILE b < 10
		lvar_int projectile_life[10]
		if projectile_life[b] > game_timer
			if DO_2D_RECTANGLES_COLLIDE projectile_position_x[b] projectile_position_y[b] 12.0 12.0 gravity_position_x[a] gravity_position_y[a] 32.0 32.0
				kill_gravity_well = 2
				if ship_dead_timer < game_timer
					IF gravity_mass[a] > 0.0
						dual_score += 10
					ELSE
						dual_score -= 10
					ENDIF
				endif
				b = 9
			endif
		endif
		++ b
	ENDWHILE
	if kill_gravity_well > 0
		IF kill_gravity_well = 2
			b = 0
			WHILE b < 10
				IF explosions_flag[b] = 0
					LVAR_FLOAT explosions_x[10] explosions_y[10]
					explosions_x[b] = gravity_position_x[a]
					explosions_y[b] = gravity_position_y[a]
					++ explosions_flag[b]
					b = 9
				ENDIF
				++ b
			ENDWHILE
		ENDIF
		GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
		IF temp_int = 0
			GENERATE_RANDOM_FLOAT_IN_RANGE 340.0 700.0 x
			GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
			IF temp_int = 0
				x *= -1.0
			ENDIF
			GENERATE_RANDOM_FLOAT_IN_RANGE -700.0 700.0 y
		else
			GENERATE_RANDOM_FLOAT_IN_RANGE -700.0 700.0 x
			GENERATE_RANDOM_FLOAT_IN_RANGE 340.0 700.0 y
			GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
			IF temp_int = 0
				y *= -1.0
			ENDIF
		endif
		GENERATE_RANDOM_FLOAT_IN_RANGE -5.0 5.0 random_floats_x[a]
		GENERATE_RANDOM_FLOAT_IN_RANGE -5.0 5.0  random_floats_y[a]
		gravity_position_x[a] =	ship_position_x + x
		gravity_position_y[a] =	ship_position_y + y
		GET_DISTANCE_BETWEEN_COORDS_2D ship_position_x ship_position_y gravity_position_x[a] gravity_position_y[a] distance
	else
		temp_float = gravity_mass[a] * ship_mass_kg
		temp_float *= universal_gravity
		z = distance *	distance
		temp_float /= z
		LVAR_FLOAT vector_x vector_y
		vector_x = gravity_position_x[a] - ship_position_x
		vector_y = gravity_position_y[a] - ship_position_y
		vector_x /= distance
		vector_y /=	distance
		x2 = vector_x * temp_float
		y2 = vector_y * temp_float
		ship_scale = 32.0
		IF x2 > 50000.0
			x2 = 50000.0
			//ship_scale = 64.0
		ENDIF
		IF x2 < -50000.0
			x2 = -50000.0
			//ship_scale = 64.0
		ENDIF 
		IF y2 > 50000.0
			y2 = 50000.0
			//ship_scale = 64.0
		ENDIF
		IF y2 < -50000.0
			y2 = -50000.0
			//ship_scale = 64.0
		ENDIF
		ship_forces_x += x2
		ship_forces_y += y2
	endif
	++ a
ENDWHILE

if ship_dead_timer < game_timer
	
	IF IS_BUTTON_PRESSED PAD1 CROSS  // Thrusters.

		IF ship_power > 0.2
		OR ship_power = 0.2
			//THIS TURNS A HEADING IN DEGREES INTO A VECTOR
			SIN ship_heading x
			COS ship_heading y

			//ADD THE FORCE OF THE THRUSTERS TO THE TOTAL FORCES ACTING ON THE SHIP
			x *= -20000.0
			y *= 20000.0
			ship_forces_x += x
			ship_forces_y += y
			draw_thruster += 50
			IF draw_thruster > 200
				draw_thruster = 200
			ENDIF
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_THRUST
			ship_power -=@ 0.2
		ENDIF
	ELSE
		draw_thruster -= 20
		IF draw_thruster < 0
			draw_thruster = 0
		ENDIF
	ENDIF

	IF ship_power > 0.1
	OR ship_power = 0.1
		IF IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER1
			//THIS TURNS A HEADING IN DEGREES INTO A VECTOR
			temp_float = ship_heading
			temp_float += 90.0
			IF temp_float > 360.0
				temp_float -= 360.0
			ENDIF
			SIN temp_float x
			COS temp_float y

			//ADD THE FORCE OF THE THRUSTERS TO THE TOTAL FORCES ACTING ON THE SHIP
			x *= -6000.0
			y *= 6000.0
			ship_forces_x += x
			ship_forces_y += y
			ship_power -=@ 0.1
		ENDIF
	ENDIF

	IF ship_power > 0.1
	OR ship_power = 0.1
		IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
			//THIS TURNS A HEADING IN DEGREES INTO A VECTOR
			temp_float = ship_heading
			temp_float -= 90.0
			IF temp_float < 0.0
				temp_float += 360.0
			ENDIF
			SIN temp_float x
			COS temp_float y

			//ADD THE FORCE OF THE THRUSTERS TO THE TOTAL FORCES ACTING ON THE SHIP
			x *= -6000.0
			y *= 6000.0
			ship_forces_x += x
			ship_forces_y += y
			ship_power -=@ 0.1
		ENDIF
	ENDIF

	//A VISCOUS DRAG FORCE IS APPLIED BY MULTIPLYING A DAMPING CONSTANT, 40.0, WITH 
	//THE VELOCITY OF THE SHIP AND SUBTRACTING THAT FORCE FROM THE ACCUMULATOR.
	LVAR_FLOAT drag_x drag_y
	drag_x = ship_velocity_x * 40.0
	drag_y = ship_velocity_y * 40.0
	ship_forces_x -= drag_x
	ship_forces_y -= drag_y

	// DETERMINE THE NEW VELOCITY FOR THE SHIP
	//ship_velocity_x += ((frame_time/1000) * (ship_forces_x * ship_mass_kg))
	//ship_velocity_y += ((frame_time/1000) * (ship_forces_y * ship_mass_kg))
	ship_forces_x /= ship_mass_kg
	ship_forces_y /= ship_mass_kg
	ship_forces_x *= time_step_fl
	ship_forces_y *= time_step_fl
	ship_velocity_x += ship_forces_x
	ship_velocity_y += ship_forces_y

	IF ship_power > 1.0
	OR ship_power = 1.0
		
		IF IS_BUTTON_PRESSED PAD1 CIRCLE
			lvar_int shoot_timer
			if shoot_timer < game_timer
				gosub create_projectile
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_SHOOT
				shoot_timer = game_timer + 250
				ship_power -=@ 1.0
				if dual_score > 0
					-- dual_score
				endif
			endif
		ENDIF
	ENDIF
endif

// STORE THE OLD POSITION
last_ship_position_x = ship_position_x
last_ship_position_y = ship_position_y
// SET THE NEW POSITION	OF THE SHIP
//ship_position_x += (frame_time/1000) * ship_velocity_x
//ship_position_y += (frame_time/1000) * ship_velocity_y
IF ship_velocity_x > 300.0
	ship_velocity_x = 300.0
ENDIF
IF ship_velocity_x < -300.0
	ship_velocity_x = -300.0
ENDIF
IF ship_velocity_y > 300.0
	ship_velocity_y = 300.0
ENDIF
IF ship_velocity_y < -300.0
	ship_velocity_y = -300.0
ENDIF
x = time_step_fl * ship_velocity_x
y = time_step_fl * ship_velocity_y
ship_position_x += x
ship_position_y += y

//DRAW PARALAX SCROLLING LAYERS///
a = 0
while a < 3
	x = last_ship_position_x - ship_position_x
	y = last_ship_position_y - ship_position_y
	x *= 0.6
	y *= 0.6
	temp_float =# a
	temp_float += 1.0
	temp_float *= 0.3
	x *= temp_float
	y *= temp_float
	tile_size = 512.0 *	temp_float

	if a = 0
		x /= 2.0
		y /= 2.0
		background_x[a] += x
		background_y[a]	+= y
	else
		background_x[a] += x
		background_y[a]	+= y
	endif
	temp_float = tile_size / 2.0
	IF background_x[a] > temp_float
		background_x[a] -= tile_size
	ENDIF
	temp_float = tile_size * -1.0
	IF background_x[a] < temp_float
		background_x[a] += tile_size
	ENDIF
	temp_float = tile_size * -1.0
	IF background_y[a] > temp_float
		background_y[a] -= tile_size
	ENDIF
	temp_float = tile_size * -1.0
	IF background_y[a] < temp_float
		background_y[a] += tile_size
	ENDIF
	temp_float = 640.0 + tile_size
	lvar_float x_tiles_fl
	x_tiles_fl = temp_float / tile_size
	lvar_int x_tiles
	x_tiles =# x_tiles_fl
	temp_float =# x_tiles
	x_tiles_fl -= temp_float
	if x_tiles_fl > 0.0
		++ x_tiles
	endif
	temp_float = 448.0 + tile_size
	lvar_float y_tiles_fl
	y_tiles_fl = temp_float / tile_size
	lvar_int y_tiles
	y_tiles =# y_tiles_fl
	temp_float =# y_tiles
	y_tiles_fl -= temp_float
	if y_tiles_fl > 0.0
		++ y_tiles
	endif
	++ x_tiles
	++ y_tiles
	layer_x	= background_x[a]
	layer_y	= background_y[a]
	LVAR_INT x_index y_index
	y_index = 0
	WHILE y_index < y_tiles
		x_index = 0
		WHILE x_index < x_tiles
			if a = 0
				DRAW_SPRITE	BACKGROUND layer_x layer_y tile_size tile_size 150 150 150 255//256.0 256.0 255 255 255 255
			else
				DRAW_SPRITE	STARLAYER layer_x layer_y tile_size tile_size 150 150 150 255//256.0 256.0 255 255 255 255
			endif
			layer_x += tile_size
			++ x_index
		ENDWHILE
		layer_x = background_x[a]
		layer_y += tile_size
		++ y_index
	ENDWHILE
	++ a
endwhile

//DRAW GRAVITY WELLS///
a = 0
WHILE a < total_gravity_wells
	x = gravity_position_x[a] - ship_position_x
	y = gravity_position_y[a] - ship_position_y
	x += 320.0
	y += 224.0
	IF gravity_mass[a] > 0.0
		DRAW_SPRITE DARK_STAR x y 32.0 32.0 150 150 150 255
	ELSE
		DRAW_SPRITE LIGHT_STAR x y 32.0 32.0 255 255 255 255
	ENDIF
	++ a
ENDWHILE

//GENERATE AND DRAW POWER PILLS
a = 0
WHILE a < 20
	GET_DISTANCE_BETWEEN_COORDS_2D ship_position_x ship_position_y power_pill_x[a] power_pill_y[a] distance
	IF distance > 600.0
	OR DO_2D_RECTANGLES_COLLIDE ship_position_x ship_position_y 32.0 32.0 power_pill_x[a] power_pill_y[a] 18.0 18.0
		IF DO_2D_RECTANGLES_COLLIDE ship_position_x ship_position_y 32.0 32.0 power_pill_x[a] power_pill_y[a] 18.0 18.0
			if ship_dead_timer < game_timer
				//PLAY PICKUP SOUND
				if a < 10
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_PICKUP_LIGHT
					dual_score += 5
				else
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_PICKUP_DARK
					dual_score -= 5
				endif
				ship_power += 25.0
				if ship_power > 100.0
					ship_power = 100.0
				endif
			endif
		ENDIF
		GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
		IF temp_int = 0
			GENERATE_RANDOM_FLOAT_IN_RANGE 340.0 700.0 x
			GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
			IF temp_int = 0
				x *= -1.0
			ENDIF
			GENERATE_RANDOM_FLOAT_IN_RANGE -700.0 700.0 y
			GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
		else
			GENERATE_RANDOM_FLOAT_IN_RANGE -700.0 700.0 x
			GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
			GENERATE_RANDOM_FLOAT_IN_RANGE 340.0 700.0 y
			GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
			IF temp_int = 0
				y *= -1.0
			ENDIF
		endif
		power_pill_x[a] = ship_position_x + x
		power_pill_y[a] = ship_position_y + y
	endif
	x = power_pill_x[a] - ship_position_x
	y = power_pill_y[a] - ship_position_y
	x += 320.0
	y += 224.0
	if a < 10
		DRAW_SPRITE LIGHT_STAR x y 16.0 16.0 255 255 255 255
	else
		DRAW_SPRITE DARK_STAR x y 16.0 16.0 150 150 150 255
	endif
	++ a
ENDWHILE

//DRAW EXPLOSIONS///
a = 0
WHILE a < 10
	IF explosions_flag[a] > 0
		x = explosions_x[a] - ship_position_x
		y = explosions_y[a] - ship_position_y
		x += 320.0
		y += 224.0
		IF explosions_flag[a] = 1
			LVAR_INT explosions_timer[10]
			explosions_timer[a] = game_timer + 500
			explosions_flag[a] = EXPLOSION1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_EXPLOSION_SHORT
		ENDIF
		//IF explosions_timer[a] < game_timer
			explosions_timer[a] = game_timer + 500
			++ explosions_flag[a]
		//ENDIF
		IF explosions_flag[a] > EXPLOSION4
			explosions_flag[a] = 0
		ELSE
			DRAW_SPRITE explosions_flag[a] x y 32.0 32.0 150 150 150 255
		ENDIF
	ENDIF
	++ a
ENDWHILE

//DRAW PROJECTILES///
a = 0
WHILE a < 10
	if projectile_life[a] > game_timer
		x = time_step_fl * projectile_velocity_x[a]
		y = time_step_fl * projectile_velocity_y[a]
		projectile_position_x[a] += x
		projectile_position_y[a] += y

		x = projectile_position_x[a] - ship_position_x
		y = projectile_position_y[a] - ship_position_y
		x += 320.0
		y += 224.0
		heading = ship_heading
		DRAW_SPRITE SHOT_BALL x y 8.0 8.0 150 150 150 255
	endif
	++ a
ENDWHILE

if ship_dead_timer < game_timer
	//DRAW THE SHIP SPRITE IN THE CENTRE OF THE SCREEN
	DRAW_SPRITE_WITH_ROTATION PLAYER_SHIP 320.0 224.0 ship_scale ship_scale ship_heading 150 150 150 255

	//SHIP HEALTH HUD BAR
	temp_float = ship_health / 2.0
	y = 400.0 - temp_float
	DRAW_SPRITE WHITE_S 50.0 350.0 14.0 104.0 0 0 0 255
	DRAW_SPRITE HEALTH_BAR 50.0 y 10.0 ship_health 200 200 200 255

	//SHIP POWER HUD BAR
	temp_float = ship_power / 2.0
	y = 400.0 - temp_float
	DRAW_SPRITE WHITE_S 70.0 350.0 14.0 104.0 0 0 0 255
	DRAW_SPRITE POWER_BAR 70.0 y 10.0 ship_power 200 200 200 255

	//THIS TURNS A HEADING IN DEGREES INTO A VECTOR
	temp_float = ship_heading
	temp_float += 180.0
	IF temp_float > 360.0
		temp_float -= 360.0
	ENDIF
	SIN temp_float x
	COS temp_float y
	x *= -1.0
	x *= 24.0
	y *= 24.0
	x += 320.0
	y += 224.0

	IF draw_thruster > 0
		DRAW_SPRITE_WITH_ROTATION THRUSTERS X Y 16.0 16.0 ship_heading draw_thruster draw_thruster draw_thruster 255
	ENDIF
else
	b = 0
	WHILE b < 10
		IF explosions_flag[b] = 0
			explosions_x[b] = ship_position_x
			explosions_y[b] = ship_position_y
			++ explosions_flag[b]
			b = 9
		ENDIF
		++ b
	ENDWHILE
	gosub setup_text_dual
	REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_GAME_OVER
	display_text 320.0 100.0 SPAC_01
	temp_int = game_timer + 1000
	if ship_dead_timer < temp_int
		quit_game = 1
	endif
endif

gosub setup_text_dual
if dual_score > -1
	display_text_with_number 320.0 50.0 SPAC_02	dual_score
else
	temp_int = dual_score * -1
	SET_TEXT_COLOUR 0 0 0 255
	SET_TEXT_EDGE 1 150 150 150 255
	display_text_with_number 320.0 50.0 SPAC_02	temp_int
endif

DRAW_SPRITE TV_BORDER 160.0 112.0 320.0 224.0 150 150 150 255 
DRAW_SPRITE TV_BORDER 480.0 112.0 -320.0 224.0 150 150 150 255 
DRAW_SPRITE TV_BORDER 480.0 336.0 -320.0 -224.0 150 150 150 255 
DRAW_SPRITE TV_BORDER 160.0 336.0 320.0 -224.0 150 150 150 255

if help_prompt_timer > game_timer
	gosub setup_text_dual
	if current_language = LANGUAGE_GERMAN
	or current_language = LANGUAGE_FRENCH
		draw_window x_pos[10] y_pos[10] 175.1144 y_scale[9] DUMMY SWIRLS_NONE
		set_text_wrapx 175.1144
	else
		draw_window x_pos[10] y_pos[10] x_scale[9] y_scale[9] DUMMY SWIRLS_NONE
		set_text_wrapx x_scale[9]
	endif
	set_text_centre OFF
	//set_text_justify on
	set_text_font FONT_STANDARD
	//set_text_colour 200 200 200 255
	if current_language = LANGUAGE_GERMAN
	or current_language = LANGUAGE_FRENCH
		set_text_scale 0.4 y_scale[11]
	else
		set_text_scale x_scale[11] y_scale[11]
	endif
	display_text x_pos[11] y_pos[11] SPAC_11
endif

if quit_game = 1
	if dual_score >	light_hiscore[9]
		lvar_int letter	input_letter
		letter = 10
		input_letter = 0
		highscore_table = 1
		GOTO dual_hiscore_loop
	else
		if dual_score <	dark_hiscore[9]
			letter = 10
			input_letter = 0
			highscore_table = 0
			GOTO dual_hiscore_loop
		else
			GOTO dual_frontend_loop
		endif
	endif
endif

GOTO dual_game_loop


dual_hiscore_loop:

wait 0

get_game_timer game_timer

if highscore_table = 0
	a = 9
	while a > -1
		if dual_score < dark_hiscore[a]
			if not a = 9
				b = a + 1
				dark_hiscore[b] = dark_hiscore[a]
				$dark_letter1[b] = $dark_letter1[a]
				$dark_letter2[b] = $dark_letter2[a]
				$dark_letter3[b] = $dark_letter3[a]
			endif
			dark_hiscore[a] = dual_score
			$dark_letter1[a] = $dual_char[36]
			$dark_letter2[a] = $dual_char[36]
			$dark_letter3[a] = $dual_char[36]
			new_high_score = a
			if a = 0
				dual_score = 0
			endif
		else
			dual_score = 0
		endif
		-- a
	endwhile
else
	a = 9
	while a > -1
		if dual_score > light_hiscore[a]
			if not a = 9
				b = a + 1
				light_hiscore[b] = light_hiscore[a]
				$light_letter1[b] = $light_letter1[a]
				$light_letter2[b] = $light_letter2[a]
				$light_letter3[b] = $light_letter3[a]
			endif
			light_hiscore[a] = dual_score
			$light_letter1[a] = $dual_char[36]
			$light_letter2[a] = $dual_char[36]
			$light_letter3[a] = $dual_char[36]
			new_high_score = a
			if a = 0
				dual_score = 0
			endif
		else
			dual_score = 0
		endif
		-- a
	endwhile
endif

if new_high_score > -1
	if highscore_table = 0
		if input_letter = 0
			$dark_letter1[new_high_score] = $dual_char[letter]
			$dark_letter2[new_high_score] = $dual_char[36]
			$dark_letter3[new_high_score] = $dual_char[36]
		endif
		if input_letter = 1
			$dark_letter2[new_high_score] = $dual_char[letter]
			$dark_letter3[new_high_score] = $dual_char[36]
		endif
		if input_letter = 2
			$dark_letter3[new_high_score] = $dual_char[letter]
		endif
	else
		if input_letter = 0
			$light_letter1[new_high_score] = $dual_char[letter]
			$light_letter2[new_high_score] = $dual_char[36]
			$light_letter3[new_high_score] = $dual_char[36]
		endif
		if input_letter = 1
			$light_letter2[new_high_score] = $dual_char[letter]
			$light_letter3[new_high_score] = $dual_char[36]
		endif
		if input_letter = 2
			$light_letter3[new_high_score] = $dual_char[letter]
		endif
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
			++input_letter
			++pad1_cross_pressed
			if input_letter = 3
				new_high_score = -1
			endif
		endif
	ELSE
		pad1_cross_pressed = 0
	ENDIF
ENDIF

if highscore_table = 0
	a = 0
	while a < 10
		lvar_int table_hiscore[10]
		table_hiscore[a] = dark_hiscore[a] * -1
		lvar_text_label	$table_letter1[10] $table_letter2[10] $table_letter3[10]
		$table_letter1[a] = $dark_letter1[a]
		$table_letter2[a] = $dark_letter2[a]
		$table_letter3[a] = $dark_letter3[a]
		++ a
	endwhile
else
	a = 0
	while a < 10
		table_hiscore[a] = light_hiscore[a]
		$table_letter1[a] = $light_letter1[a]
		$table_letter2[a] = $light_letter2[a]
		$table_letter3[a] = $light_letter3[a]
		++ a
	endwhile
endif

//DRAW PARALAX SCROLLING LAYERS///
a = 0
while a < 3
	x = last_ship_position_x - ship_position_x
	y = last_ship_position_y - ship_position_y
	x *= 0.6
	y *= 0.6
	temp_float =# a
	temp_float += 1.0
	temp_float *= 0.3
	x *= temp_float
	y *= temp_float
	tile_size = 512.0 *	temp_float

	if a = 0
		x /= 2.0
		y /= 2.0
		background_x[a] += x
		background_y[a]	+= y
	else
		background_x[a] += x
		background_y[a]	+= y
	endif
	temp_float = tile_size / 2.0
	IF background_x[a] > temp_float
		background_x[a] -= tile_size
	ENDIF
	temp_float = tile_size * -1.0
	IF background_x[a] < temp_float
		background_x[a] += tile_size
	ENDIF
	temp_float = tile_size * -1.0
	IF background_y[a] > temp_float
		background_y[a] -= tile_size
	ENDIF
	temp_float = tile_size * -1.0
	IF background_y[a] < temp_float
		background_y[a] += tile_size
	ENDIF
	temp_float = 640.0 + tile_size
	x_tiles_fl = temp_float / tile_size
	x_tiles =# x_tiles_fl
	temp_float =# x_tiles
	x_tiles_fl -= temp_float
	if x_tiles_fl > 0.0
		++ x_tiles
	endif
	temp_float = 448.0 + tile_size
	y_tiles_fl = temp_float / tile_size
	y_tiles =# y_tiles_fl
	temp_float =# y_tiles
	y_tiles_fl -= temp_float
	if y_tiles_fl > 0.0
		++ y_tiles
	endif
	++ x_tiles
	++ y_tiles
	layer_x	= background_x[a]
	layer_y	= background_y[a]
	y_index = 0
	WHILE y_index < y_tiles
		x_index = 0
		WHILE x_index < x_tiles
			if a = 0
				DRAW_SPRITE	BACKGROUND layer_x layer_y tile_size tile_size 150 150 150 255//256.0 256.0 255 255 255 255
			else
				DRAW_SPRITE	STARLAYER layer_x layer_y tile_size tile_size 150 150 150 255//256.0 256.0 255 255 255 255
			endif
			layer_x += tile_size
			++ x_index
		ENDWHILE
		layer_x = background_x[a]
		layer_y += tile_size
		++ y_index
	ENDWHILE
	++ a
endwhile

gosub setup_text_dual
if highscore_table = 0
	set_text_colour 0 0 0 255
	set_text_edge 1 150 150 150 255
endif
set_text_font FONT_STANDARD
display_text title_x title_y SPAC_06 //HISCORE

lvar_float highscore_x highscore_y
highscore_x = top_row_x
highscore_y	= top_row_y

a = 0
while a < 10

	highscore_x = top_row_x
	
	//first letter
	gosub setup_text_dual
	set_text_centre off
	set_text_justify on
	set_text_font FONT_STANDARD
	if highscore_table = 0
		set_text_colour 0 0 0 255
		set_text_edge 1 150 150 150 255
	endif
	if new_high_score = a
		set_text_colour 0 155 0 255
	endif
	if $table_letter1[a] = $dual_char[1]
	or $table_letter1[a] = $dual_char[18]
		highscore_x += 8.0
	endif
	display_text highscore_x highscore_y $table_letter1[a]
	if $table_letter1[a] = $dual_char[1]
	or $table_letter1[a] = $dual_char[18]
		highscore_x -= 8.0
	endif

	highscore_x += next_letter_x
	
	//second letter
	gosub setup_text_dual
	set_text_centre off
	set_text_justify on
	set_text_font FONT_STANDARD
	if highscore_table = 0
		set_text_colour 0 0 0 255
		set_text_edge 1 150 150 150 255
	endif
	if new_high_score = a
		set_text_colour 0 155 0 255
	endif
	if $table_letter2[a] = $dual_char[1]
	or $table_letter2[a] = $dual_char[18]
		highscore_x += 8.0
	endif
	display_text highscore_x highscore_y $table_letter2[a]
	if $table_letter2[a] = $dual_char[1]
	or $table_letter2[a] = $dual_char[18]
		highscore_x -= 8.0
	endif

	highscore_x += next_letter_x
	
	//third letter
	gosub setup_text_dual
	set_text_centre off
	set_text_justify on
	set_text_font FONT_STANDARD
	if highscore_table = 0
		set_text_colour 0 0 0 255
		set_text_edge 1 150 150 150 255
	endif
	if new_high_score = a
		set_text_colour 0 155 0 255
	endif
	if $table_letter3[a] = $dual_char[1]
	or $table_letter3[a] = $dual_char[18]
		highscore_x += 8.0
	endif
	display_text highscore_x highscore_y $table_letter3[a]
	if $table_letter3[a] = $dual_char[1]
	or $table_letter3[a] = $dual_char[18]
		highscore_x -= 8.0
	endif

	highscore_x = top_row_x + score_dist_x
	
	//score
	gosub setup_text_dual
	set_text_centre off
	set_text_right_justify on
	set_text_font FONT_STANDARD
	if highscore_table = 0
		set_text_colour 0 0 0 255
		set_text_edge 1 150 150 150 255
	endif
	if new_high_score = a
		set_text_colour 0 155 0 255
	endif
	display_text_with_number highscore_x highscore_y NUMBER table_hiscore[a]//HISCORE

	highscore_y += next_row_y

	++ a
endwhile

DRAW_SPRITE TV_BORDER 160.0 112.0 320.0 224.0 150 150 150 255 
DRAW_SPRITE TV_BORDER 480.0 112.0 -320.0 224.0 150 150 150 255 
DRAW_SPRITE TV_BORDER 480.0 336.0 -320.0 -224.0 150 150 150 255 
DRAW_SPRITE TV_BORDER 160.0 336.0 320.0 -224.0 150 150 150 255

if new_high_score = -1
	
	IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
		GOTO dual_frontend_loop
	endif
	
	IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
	    IF pad1_cross_pressed = 0
			++pad1_cross_pressed
			GOTO dual_frontend_loop
		endif
	ELSE
		pad1_cross_pressed = 0
	ENDIF

else
	draw_window x_pos[10] y_pos[10] x_scale[10] y_scale[10] DUMMY SWIRLS_NONE
	gosub setup_text_dual
	set_text_centre OFF
	set_text_wrapx x_scale[10]
	set_text_font FONT_STANDARD
	set_text_scale x_scale[11] y_scale[11]
	display_text x_pos[11] y_pos[11] SPAC_13
endif

GOTO dual_hiscore_loop



create_projectile:
a = 0
WHILE a < 10
	if projectile_life[a] < game_timer
		temp_float = ship_heading
		SIN temp_float x
		COS temp_float y
		LVAR_FLOAT projectile_position_x[10] projectile_position_y[10]
		projectile_position_x[a] = ship_position_x
		projectile_position_y[a] = ship_position_y
		LVAR_FLOAT projectile_velocity_x[10] projectile_velocity_y[10]
		x *= -300.0
		y *= 300.0
		projectile_velocity_x[a] = x + ship_velocity_x
		projectile_velocity_y[a] = y + ship_velocity_y
		projectile_life[a] = game_timer + 1600
		return
	endif
	++ a
ENDWHILE

return


setup_text_dual:///////////////////////////////////////////////////////////////////////////
	set_text_colour 180 180 180 255
	set_text_scale 1.0 3.0
	set_text_right_justify off
	set_text_justify off
	set_text_centre on
	set_text_wrapx 640.0
	set_text_proportional on
	set_text_background off
	set_text_dropshadow 0 0 0 0 180
	set_text_font FONT_SPACEAGE
return//////////////////////////////////////////////////////////////////////////////////////
	

// *********************************** MISSION CLEANUP *************************************
mission_dual_cleanup:

REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_DUAL_TRACK_STOP
if is_player_playing player1
	if locate_char_on_foot_3d scplayer spaceX spaceY spaceZ 2.0 2.0 2.0	0
		y = spaceY + 2.0
		set_char_coordinates scplayer spaceX y spaceZ
	endif
endif

SHUT_ALL_CHARS_UP FALSE

flag_player_on_mission = 0

CLEAR_THIS_PRINT BUSY

MISSION_HAS_FINISHED
RETURN
}

