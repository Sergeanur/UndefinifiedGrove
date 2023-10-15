MISSION_START
// *****************************************************************************************
// ******************************************* OTB ***************************************** 
// *****************************************************************************************

// Mission start stuff
GOSUB mission_start_otb

GOSUB mission_cleanup_otb

MISSION_END
 

// ****************************************Mission Start************************************
{
mission_start_otb:

flag_player_on_mission = 1

//REGISTER_MISSION_GIVEN
SCRIPT_NAME OTB

WAIT 0

LOAD_MISSION_TEXT OTB
USE_TEXT_COMMANDS TRUE
LOAD_TEXTURE_DICTIONARY ld_otb2
load_mission_audio 4 SOUND_BANK_OTB

LOAD_SPRITE 1 butna
LOAD_SPRITE 2 butnb
LOAD_SPRITE 3 butnc

LOAD_SPRITE 4 ric1
LOAD_SPRITE 5 ric2
LOAD_SPRITE 6 ric3
LOAD_SPRITE 7 ric4
LOAD_SPRITE 8 ric5

LOAD_SPRITE 9 backbet

LOAD_SPRITE 10 butnao
LOAD_SPRITE 11 butnbo

LVAR_FLOAT button_gap
button_gap = 58.8281

LVAR_FLOAT button_x button_y[5] rider_colour_x rider_colour_Y horse_name_x horse_name_y
button_x = 225.0068

button_y[0] = 131.5202
button_y[1] = button_y[0] + button_gap
button_y[2] = button_y[1] + button_gap
button_y[3] = button_y[2] + button_gap
button_y[4] = button_y[3] + button_gap

LVAR_FLOAT dollar_gap_x dollar_gap_y
dollar_gap_x = 93.8682
dollar_gap_y = 42.34

LVAR_FLOAT dollar_x[8] dollar_y[8]

dollar_x[0] = 463.313
dollar_y[0] = 191.6589

dollar_x[1] = dollar_x[0] + dollar_gap_x
dollar_y[1] = dollar_y[0]

dollar_x[2] = dollar_x[0] 
dollar_y[2] = dollar_y[0] + dollar_gap_y

dollar_x[3] = dollar_x[0] + dollar_gap_x
dollar_y[3] = dollar_y[0] + dollar_gap_y

dollar_x[4] = dollar_x[2]
dollar_y[4] = dollar_y[2] + dollar_gap_y

dollar_x[5] = dollar_x[4] + dollar_gap_x
dollar_y[5] = dollar_y[4]

dollar_x[6] = dollar_x[4]
dollar_y[6] = dollar_y[4] + dollar_gap_y

dollar_x[7] = dollar_x[4] + dollar_gap_x
dollar_y[7] = dollar_y[4] + dollar_gap_y

LVAR_FLOAT stake_x stake_y
stake_x = 510.0702 
stake_y = 385.0263 

CONST_INT UNSELECTED    0
CONST_INT SELECTED      1
CONST_INT PRESSED_DOWN  2

LVAR_INT button_state[5]
button_state[0] = SELECTED
button_state[1] = UNSELECTED
button_state[2] = UNSELECTED
button_state[3] = UNSELECTED
button_state[4] = UNSELECTED


LVAR_INT pad1_up_pressed pad1_down_pressed pad1_left_pressed pad1_right_pressed pad1_cross_pressed pad1_triangle_pressed
pad1_up_pressed = 1
pad1_down_pressed = 1
pad1_left_pressed = 1
pad1_right_pressed = 1
pad1_cross_pressed = 1
pad1_triangle_pressed = 1

LVAR_INT depressed_button_timer
depressed_button_timer = 0

LVAR_INT v77 // unused
v77 = 0

LVAR_INT button_state2[8]
button_state2[0] = UNSELECTED
button_state2[1] = UNSELECTED
button_state2[2] = UNSELECTED
button_state2[3] = UNSELECTED
button_state2[4] = UNSELECTED
button_state2[5] = UNSELECTED
button_state2[6] = UNSELECTED
button_state2[7] = UNSELECTED

LVAR_INT place_bet_state
place_bet_state = 0

LVAR_INT cash_stake
cash_stake = 0

LVAR_INT dollar_cash_stake[8]
dollar_cash_stake[0] = 0
dollar_cash_stake[1] = 5
dollar_cash_stake[2] = 25
dollar_cash_stake[3] = 100
dollar_cash_stake[4] = 250
dollar_cash_stake[5] = 1000
dollar_cash_stake[6] = 5000
dollar_cash_stake[7] = 10000

LVAR_INT players_cash

lVAR_TEXT_LABEL $horse_names[167]
$horse_names[0]   = HORS__1
$horse_names[1]   = HORS__2
$horse_names[2]   = HORS__3
$horse_names[3]   = HORS__4
$horse_names[4]   = HORS__5
$horse_names[5]   = HORS__6
$horse_names[6]   = HORS__7
$horse_names[7]   = HORS__8
$horse_names[8]   = HORS__9
$horse_names[9]   = HORS_10
$horse_names[10]  = HORS_11
$horse_names[11]  = HORS_12
$horse_names[12]  = HORS_13
$horse_names[13]  = HORS_14
$horse_names[14]  = HORS_15
$horse_names[15]  = HORS_16
$horse_names[16]  = HORS_17
$horse_names[17]  = HORS_18
$horse_names[18]  = HORS_19
$horse_names[19]  = HORS_20
$horse_names[20]  = HORS_21
$horse_names[21]  = HORS_22
$horse_names[22]  = HORS_23
$horse_names[23]  = HORS_24
$horse_names[24]  = HORS_25
$horse_names[25]  = HORS_26
$horse_names[26]  = HORS_27
$horse_names[27]  = HORS_28
$horse_names[28]  = HORS_29
$horse_names[29]  = HORS_30
$horse_names[30]  = HORS_31
$horse_names[31]  = HORS_32
$horse_names[32]  = HORS_33
$horse_names[33]  = HORS_34
$horse_names[34]  = HORS_35
$horse_names[35]  = HORS_36
$horse_names[36]  = HORS_37
$horse_names[37]  = HORS_38
$horse_names[38]  = HORS_39
$horse_names[39]  = HORS_40
$horse_names[40]  = HORS_41
$horse_names[41]  = HORS_42
$horse_names[42]  = HORS_43
$horse_names[43]  = HORS_44
$horse_names[44]  = HORS_45
$horse_names[45]  = HORS_46
$horse_names[46]  = HORS_47
$horse_names[47]  = HORS_48
$horse_names[48]  = HORS_49
$horse_names[49]  = HORS_50
$horse_names[50]  = HORS_51
$horse_names[51]  = HORS_52
$horse_names[52]  = HORS_53
$horse_names[53]  = HORS_54
$horse_names[54]  = HORS_55
$horse_names[55]  = HORS_56
$horse_names[56]  = HORS_57
$horse_names[57]  = HORS_58
$horse_names[58]  = HORS_59
$horse_names[59]  = HORS_60
$horse_names[60]  = HORS_61
$horse_names[61]  = HORS_62
$horse_names[62]  = HORS_63
$horse_names[63]  = HORS_64
$horse_names[64]  = HORS_65
$horse_names[65]  = HORS_66
$horse_names[66]  = HORS_67
$horse_names[67]  = HORS_68
$horse_names[68]  = HORS_69
$horse_names[69]  = HORS_70
$horse_names[70]  = HORS_71
$horse_names[71]  = HORS_72
$horse_names[72]  = HORS_73
$horse_names[73]  = HORS_74
$horse_names[74]  = HORS_75
$horse_names[75]  = HORS_76
$horse_names[76]  = HORS_77
$horse_names[77]  = HORS_78
$horse_names[78]  = HORS_79
$horse_names[79]  = HORS_80
$horse_names[80]  = HORS_81
$horse_names[81]  = HORS_82
$horse_names[82]  = HORS_83
$horse_names[83]  = HORS_84
$horse_names[84]  = HORS_85
$horse_names[85]  = HORS_86
$horse_names[86]  = HORS_87
$horse_names[87]  = HORS_88
$horse_names[88]  = HORS_89
$horse_names[89]  = HORS_90
$horse_names[90]  = HORS_91
$horse_names[91]  = HORS_92
$horse_names[92]  = HORS_93
$horse_names[93]  = HORS_94
$horse_names[94]  = HORS_95
$horse_names[95]  = HORS_96
$horse_names[96]  = HORS_97
$horse_names[97]  = HORS_98
$horse_names[98]  = HORS_99
$horse_names[99]  = HORS100
$horse_names[100] = HORS101
$horse_names[101] = HORS102
$horse_names[102] = HORS103
$horse_names[103] = HORS104
$horse_names[104] = HORS105
$horse_names[105] = HORS106
$horse_names[106] = HORS107
$horse_names[107] = HORS108
$horse_names[108] = HORS109
$horse_names[109] = HORS110
$horse_names[110] = HORS111
$horse_names[111] = HORS112
$horse_names[112] = HORS113
$horse_names[113] = HORS114
$horse_names[114] = HORS115
$horse_names[115] = HORS116
$horse_names[116] = HORS117
$horse_names[117] = HORS118
$horse_names[118] = HORS119
$horse_names[119] = HORS120
$horse_names[120] = HORS121
$horse_names[121] = HORS122
$horse_names[122] = HORS123
$horse_names[123] = HORS124
$horse_names[124] = HORS125
$horse_names[125] = HORS126
$horse_names[126] = HORS127
$horse_names[127] = HORS128
$horse_names[128] = HORS129
$horse_names[129] = HORS130
$horse_names[130] = HORS131
$horse_names[131] = HORS132
$horse_names[132] = HORS133
$horse_names[133] = HORS134
$horse_names[134] = HORS135
$horse_names[135] = HORS136
$horse_names[136] = HORS137
$horse_names[137] = HORS138
$horse_names[138] = HORS139
$horse_names[139] = HORS140
$horse_names[140] = HORS141
$horse_names[141] = HORS142
$horse_names[142] = HORS143
$horse_names[143] = HORS144
$horse_names[144] = HORS145
$horse_names[145] = HORS146
$horse_names[146] = HORS147
$horse_names[147] = HORS148
$horse_names[148] = HORS149
$horse_names[149] = HORS150
$horse_names[150] = HORS151
$horse_names[151] = HORS152
$horse_names[152] = HORS153
$horse_names[153] = HORS154
$horse_names[154] = HORS155
$horse_names[155] = HORS156
$horse_names[156] = HORS157
$horse_names[157] = HORS158
$horse_names[158] = HORS159
$horse_names[159] = HORS160
$horse_names[160] = HORS161
$horse_names[161] = HORS162
$horse_names[162] = HORS163
$horse_names[163] = HORS164
$horse_names[164] = HORS165
$horse_names[165] = HORS166
$horse_names[166] = HORS167

LVAR_INT horses[5]
horses[0] = -1
horses[1] = -1
horses[2] = -1
horses[3] = -1
horses[4] = -1

GENERATE_RANDOM_INT_IN_RANGE 0 167 horses[0]

WHILE horses[0] = horses[1]
OR horses[1] = -1
	GENERATE_RANDOM_INT_IN_RANGE 0 167 horses[1]
ENDWHILE

WHILE horses[0] = horses[2]
OR horses[1] = horses[2]
OR horses[2] = -1
	GENERATE_RANDOM_INT_IN_RANGE 0 167 horses[2]
ENDWHILE

WHILE horses[0] = horses[3]
OR horses[1] = horses[3]
OR horses[2] = horses[3]
OR horses[3] = -1
	GENERATE_RANDOM_INT_IN_RANGE 0 167 horses[3]
ENDWHILE

WHILE horses[0] = horses[4]
OR horses[1] = horses[4]
OR horses[2] = horses[4]
OR horses[3] = horses[4]
OR horses[4] = -1
	GENERATE_RANDOM_INT_IN_RANGE 0 167 horses[4]
ENDWHILE

LVAR_INT horse_counter1 horse_counter2 horses_ability[5]
horses_ability[0] = 1
horses_ability[1] = 1
horses_ability[2] = 1
horses_ability[3] = 1
horses_ability[4] = 1
horse_counter1 = 0

WHILE horse_counter1 < 5
	
	horse_counter2 = 0
	
	WHILE horse_counter2 < 5
		IF horses[horse_counter1] < horses[horse_counter2]
			++ horses_ability[horse_counter1]
		ENDIF
		++ horse_counter2
	ENDWHILE
	
	++ horse_counter1

ENDWHILE

horse_counter1 = 0

LVAR_INT horse[5]
WHILE horse_counter1 < 5
	IF horses_ability[horse_counter1] = 1
		horse[0] = horses[horse_counter1]
	ENDIF
	IF horses_ability[horse_counter1] = 2
		horse[1] = horses[horse_counter1]
	ENDIF
	IF horses_ability[horse_counter1] = 3
		horse[2] = horses[horse_counter1]
	ENDIF
	IF horses_ability[horse_counter1] = 4
		horse[3] = horses[horse_counter1]
	ENDIF
	IF horses_ability[horse_counter1] = 5
		horse[4] = horses[horse_counter1]
	ENDIF
	++ horse_counter1
ENDWHILE


LVAR_INT horse_odds[5]
GENERATE_RANDOM_INT_IN_RANGE  2  4 horse_odds[0]
GENERATE_RANDOM_INT_IN_RANGE  4  6 horse_odds[1]
GENERATE_RANDOM_INT_IN_RANGE  6  9 horse_odds[2]
GENERATE_RANDOM_INT_IN_RANGE  9 11 horse_odds[3]
GENERATE_RANDOM_INT_IN_RANGE 11 13 horse_odds[4]

lvar_int goto_horse_race
goto_horse_race = 0

while not has_mission_audio_loaded 4
	wait 0
endwhile

DO_FADE 0 FADE_in

DRAW_RECT  320.0 224.0 640.0 448.0 0 0 0 255


betting_otb_loop:
    
	WAIT 0
	
	GET_GAME_TIMER game_timer
	
	
	DRAW_SPRITE 9 320.0 224.0 640.0 448.0 150 150 150 255  // Render the tv screen backdrop.
	

	// Render the "your cash" text at top right.

 	GOSUB setup_text_otb

	SET_TEXT_SCALE 0.759 2.8293
	SET_TEXT_CENTRE ON
	DISPLAY_TEXT 204.407 39.1245 HRACE01

	LVAR_FLOAT otb_text_pos_x
	LVAR_INT str_width
	otb_text_pos_x = 60.1776

 	GOSUB setup_text_otb
	SET_TEXT_SCALE 0.527 2.0

	GET_STRING_WIDTH XSELECT str_width

	DISPLAY_TEXT otb_text_pos_x 395.7635 XSELECT
	temp_float =# str_width
	otb_text_pos_x += temp_float
	otb_text_pos_x += 8.0

 	GOSUB setup_text_otb
	SET_TEXT_SCALE 0.527 2.0

	IF depressed_button_timer = 0
		GET_STRING_WIDTH TRIEXIT str_width
		DISPLAY_TEXT otb_text_pos_x 395.7635 TRIEXIT
	ELSE
		GET_STRING_WIDTH TRIBACK str_width
		DISPLAY_TEXT otb_text_pos_x 395.7635 TRIBACK
	ENDIF

	temp_float =# str_width
	otb_text_pos_x += temp_float
	otb_text_pos_x += 8.0
	
 	GOSUB setup_text_otb
	SET_TEXT_SCALE 0.527 2.0
	IF depressed_button_timer = 0
		DISPLAY_TEXT otb_text_pos_x 395.7635 DPADNUD
	ELSE
		DISPLAY_TEXT otb_text_pos_x 395.7635 DPADNAV
	ENDIF


	STORE_SCORE player1 players_cash
 	GOSUB setup_text_otb

	
	SET_TEXT_SCALE 0.4753 2.2289
	SET_TEXT_CENTRE ON
	DISPLAY_TEXT_WITH_NUMBER 508.6669 18.0933 YOURCSH players_cash  // YOUR CASH: $~1~


	LVAR_INT pad1_leftstick_x pad1_leftstick_y pad1_rightstick_x pad1_rightstick_y
	GET_POSITION_OF_ANALOGUE_STICKS PAD1 pad1_leftstick_x pad1_leftstick_y pad1_rightstick_x pad1_rightstick_y

	IF depressed_button_timer = 0
		IF pad1_leftstick_y < -100
		OR IS_BUTTON_PRESSED PAD1 DPADUP
			IF pad1_up_pressed = UNSELECTED
				IF button_state[1] = SELECTED
					button_state[1] = UNSELECTED
					button_state[0] = SELECTED
				ENDIF
				IF button_state[2] = SELECTED
					button_state[2] = UNSELECTED
					button_state[1] = SELECTED
				ENDIF
				IF button_state[3] = SELECTED
					button_state[3] = UNSELECTED
					button_state[2] = SELECTED
				ENDIF
				IF button_state[4] = SELECTED
					button_state[4] = UNSELECTED
					button_state[3] = SELECTED
				ENDIF
				pad1_up_pressed = 1
			ENDIF
		ELSE
			pad1_up_pressed = 0
		ENDIF

		IF pad1_leftstick_y > 100
		OR IS_BUTTON_PRESSED PAD1 DPADDOWN
			IF pad1_down_pressed = 0
				IF button_state[3] = SELECTED
					button_state[3] = UNSELECTED
					button_state[4] = SELECTED
				ENDIF
				IF button_state[2] = SELECTED
					button_state[2] = UNSELECTED
					button_state[3] = SELECTED
				ENDIF
				IF button_state[1] = SELECTED
					button_state[1] = UNSELECTED
					button_state[2] = SELECTED
				ENDIF
				IF button_state[0] = SELECTED
					button_state[0] = UNSELECTED
					button_state[1] = SELECTED
				ENDIF
				pad1_down_pressed = 1
			ENDIF
		ELSE
			pad1_down_pressed = 0
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 CROSS
			IF pad1_cross_pressed = 0
				IF button_state[0] = SELECTED
					button_state[1] = PRESSED_DOWN
					button_state[2] = PRESSED_DOWN
					button_state[3] = PRESSED_DOWN
					button_state[4] = PRESSED_DOWN
				ENDIF
				IF button_state[1] = SELECTED
					button_state[0] = PRESSED_DOWN
					button_state[2] = PRESSED_DOWN
					button_state[3] = PRESSED_DOWN
					button_state[4] = PRESSED_DOWN
				ENDIF
				IF button_state[2] = SELECTED
					button_state[0] = PRESSED_DOWN
					button_state[1] = PRESSED_DOWN
					button_state[3] = PRESSED_DOWN
					button_state[4] = PRESSED_DOWN
				ENDIF
				IF button_state[3] = SELECTED
					button_state[0] = PRESSED_DOWN
					button_state[1] = PRESSED_DOWN
					button_state[2] = PRESSED_DOWN
					button_state[4] = PRESSED_DOWN
				ENDIF
				IF button_state[4] = SELECTED
					button_state[0] = PRESSED_DOWN
					button_state[1] = PRESSED_DOWN
					button_state[2] = PRESSED_DOWN
					button_state[3] = PRESSED_DOWN
				ENDIF
				pad1_cross_pressed = 1
				button_state2[0] = SELECTED
				depressed_button_timer++
				ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_AMMUNATION_BUY_WEAPON
			ENDIF
		ELSE
			pad1_cross_pressed = 0
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			IF pad1_triangle_pressed = 0
				pad1_triangle_pressed = 1
				DO_FADE 0 fade_out
				WAIT 0
				RETURN
			ENDIF
		ELSE
			pad1_triangle_pressed = 0
		ENDIF
	ENDIF

	// Draw the rider selection buttons.
	
	LVAR_INT k
	k = 0
	
	WHILE k < 5
		
		IF button_state[k] = UNSELECTED
			DRAW_SPRITE	1 button_x button_y[k] 330.0 64.0 150 150 150 255
		ENDIF
		
		IF button_state[k] = SELECTED
			DRAW_SPRITE	10 button_x button_y[k] 330.0 64.0 150 150 150 255
		ENDIF
		
		IF button_state[k] = PRESSED_DOWN
			DRAW_SPRITE	1 button_x button_y[k] 330.0 64.0 50 50 50 255
		ENDIF
		
		rider_colour_x = button_x - 130.0
		rider_colour_y = button_y[k] - 8.0

		LVAR_INT temp_int
		temp_int = k + 4

		DRAW_SPRITE temp_int rider_colour_x rider_colour_y 64.0 64.0 150 150 150 255
		
		horse_name_x = button_x - 105.0
		horse_name_y = button_y[k] - 24.0

		GOSUB setup_text_otb
		SET_TEXT_SCALE 0.63 3.0

		lvar_int r g b a

		IF button_state[k] = SELECTED
			get_hud_colour HUD_COLOUR_RED r g b a
			SET_TEXT_COLOUR r g b a
		ENDIF

		temp_int = horse[k]

		DISPLAY_TEXT horse_name_x horse_name_y $horse_names[temp_int]
		horse_name_x += 254.2121

		GOSUB setup_text_otb

		SET_TEXT_SCALE 0.7 3.0
		SET_TEXT_RIGHT_JUSTIFY ON

		IF button_state[k] = SELECTED
			get_hud_colour HUD_COLOUR_RED r g b a
			SET_TEXT_COLOUR r g b a
		ENDIF

		DISPLAY_TEXT_WITH_NUMBER horse_name_x horse_name_y ODDS horse_odds[k] // Odds.

		++k
	ENDWHILE

	IF depressed_button_timer = 1
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			IF pad1_triangle_pressed = 0
				IF button_state[0] = SELECTED
					button_state[1] = UNSELECTED
					button_state[2] = UNSELECTED
					button_state[3] = UNSELECTED
					button_state[4] = UNSELECTED
				ENDIF
				IF button_state[1] = SELECTED
					button_state[0] = UNSELECTED
					button_state[2] = UNSELECTED
					button_state[3] = UNSELECTED
					button_state[4] = UNSELECTED
				ENDIF
				IF button_state[2] = SELECTED
					button_state[0] = UNSELECTED
					button_state[1] = UNSELECTED
					button_state[3] = UNSELECTED
					button_state[4] = UNSELECTED
				ENDIF
				IF button_state[3] = SELECTED
					button_state[0] = UNSELECTED
					button_state[1] = UNSELECTED
					button_state[2] = UNSELECTED
					button_state[4] = UNSELECTED
				ENDIF
				IF button_state[4] = SELECTED
					button_state[0] = UNSELECTED
					button_state[1] = UNSELECTED
					button_state[2] = UNSELECTED
					button_state[3] = UNSELECTED
				ENDIF
				pad1_triangle_pressed = 1
				button_state2[0] = UNSELECTED
				button_state2[1] = UNSELECTED
				button_state2[2] = UNSELECTED
				button_state2[3] = UNSELECTED
				button_state2[4] = UNSELECTED
				button_state2[5] = UNSELECTED
				button_state2[6] = UNSELECTED
				button_state2[7] = UNSELECTED
				place_bet_state = 0
				depressed_button_timer -= 1
				ADD_SCORE player1 cash_stake
				cash_stake = 0
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_OTB_INCREASE_BET 
			ENDIF
		ELSE
			pad1_triangle_pressed = 0
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 CROSS

			IF pad1_cross_pressed = 0

				IF NOT IS_CHAR_DEAD scplayer
					GET_CHAR_COORDINATES scplayer player_x player_y player_z
				ENDIF

				IF place_bet_state > 0
				AND cash_stake > 0
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_OTB_PLACE_BET
					goto_horse_race = 1
				ENDIF
			
				// CLEAR THE CASH WITH THE $0 BUTTON
				IF button_state2[0] = SELECTED
					button_state2[0] = PRESSED_DOWN
					ADD_SCORE player1 cash_stake
					cash_stake = dollar_cash_stake[0]
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_OTB_BET_ZERO
				ENDIF

				// ADD MONEY TO PLAYERS BET
				k = 1
				WHILE k < 8

					IF button_state2[k] = SELECTED
						button_state2[k] = PRESSED_DOWN
						temp_int = dollar_cash_stake[k] - 1
						lvar_int temp_int2
						temp_int2 = cash_stake + dollar_cash_stake[k]
						IF IS_SCORE_GREATER player1 temp_int
						and temp_int2 < 9999999
							cash_stake += dollar_cash_stake[k]
							temp_int = dollar_cash_stake[k] * -1
							ADD_SCORE player1 temp_int
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_OTB_INCREASE_BET
						ELSE
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_OTB_NO_CASH
						ENDIF
					ENDIF
					++k
				ENDWHILE
				pad1_cross_pressed = 1
			ENDIF

		ELSE
			IF pad1_cross_pressed = 1
				//RESET THE BUTTONS GRAPHIC WHEN BUTTON RELEASED
				k = 0
				WHILE k < 8
					IF button_state2[k] = PRESSED_DOWN
						button_state2[k] = SELECTED
					ENDIF
					++k
				ENDWHILE
				pad1_cross_pressed = 0
			ENDIF
    
			// Move up and down on the horse selection panel.
				
			IF pad1_leftstick_x < -100
			OR IS_BUTTON_PRESSED PAD1 DPADLEFT
				IF pad1_left_pressed = 0
					pad1_left_pressed = 1
					GOSUB LeftPressed
				ENDIF
			ELSE
				pad1_left_pressed = 0
			ENDIF

			IF pad1_leftstick_x > 100
			OR IS_BUTTON_PRESSED PAD1 DPADRIGHT
				IF pad1_right_pressed = 0
					pad1_right_pressed = 1
					GOSUB RightPressed
				ENDIF
			ELSE
				pad1_right_pressed = 0
			ENDIF
			
			IF pad1_leftstick_y < -100
			OR IS_BUTTON_PRESSED PAD1 DPADUP
				IF pad1_up_pressed = 0
					pad1_up_pressed = 1
					GOSUB UpPressed
				ENDIF
			ELSE
				pad1_up_pressed = 0
			ENDIF

			IF pad1_leftstick_y > 100
			OR IS_BUTTON_PRESSED PAD1 DPADDOWN
				IF pad1_down_pressed = 0
					pad1_down_pressed = 1
					GOSUB DownPressed
				ENDIF
			ELSE
				pad1_down_pressed = 0
			ENDIF
		ENDIF
	ENDIF

	k = 0
	WHILE k < 8
		IF button_state2[k] = UNSELECTED
			DRAW_SPRITE 2 dollar_x[k] dollar_y[k] 90.0 64.0 150 150 150 255
		ENDIF
		IF button_state2[k] = SELECTED
			DRAW_SPRITE 11 dollar_x[k] dollar_y[k] 90.0 64.0 150 150 150 255
		ENDIF
		IF button_state2[k] = PRESSED_DOWN
			DRAW_SPRITE 3 dollar_x[k] dollar_y[k] 90.0 64.0 150 150 150 255
		ENDIF

		GOSUB setup_text_otb

		SET_TEXT_CENTRE ON
		SET_TEXT_SCALE 0.58 2.4957

		LVAR_FLOAT temp_float
		temp_float = dollar_y[k] - 28.3404
		IF button_state2[k] > UNSELECTED
			get_hud_colour HUD_COLOUR_RED r g b a
			SET_TEXT_COLOUR r g b a
		ENDIF

		DISPLAY_TEXT_WITH_NUMBER dollar_x[k] temp_float DOLLAR dollar_cash_stake[k]
		k++
	ENDWHILE

	IF place_bet_state = 0
		IF cash_stake > 0
			DRAW_SPRITE 1 510.0702 373.3544 176.8987 50.8555 150 150 150 255 
		ELSE
			DRAW_SPRITE 1 510.0702 373.3544 176.8987 50.8555 50 50 50 255
		ENDIF
	ELSE
		DRAW_SPRITE 10 510.0702 373.3544 176.8987 50.8555 150 150 150 255 
	ENDIF

	GOSUB setup_text_otb
	IF current_Language = LANGUAGE_GERMAN
	OR current_Language = LANGUAGE_ITALIAN
		SET_TEXT_SCALE 0.5 2.996
	ELSE
		SET_TEXT_SCALE 0.609 2.996
	ENDIF

	SET_TEXT_CENTRE ON

	IF place_bet_state > 0
		get_hud_colour HUD_COLOUR_RED r g b a
		SET_TEXT_COLOUR r g b a
	ENDIF

	DISPLAY_TEXT 509.6968 351.0 PLACBET

	GOSUB setup_text_otb
	SET_TEXT_SCALE 0.8 3.3297
	SET_TEXT_CENTRE ON
	DISPLAY_TEXT_WITH_NUMBER 509.2133 84.9941 DOLLAR cash_stake

	if goto_horse_race = 1
		goto the_horse_race
	endif

    GOTO betting_otb_loop


the_horse_race:


REMOVE_TEXTURE_DICTIONARY

LOAD_TEXTURE_DICTIONARY ld_otb

LOAD_SPRITE 1 bckgrnd
LOAD_SPRITE 2 bushes
LOAD_SPRITE 3 bride1
LOAD_SPRITE 4 bride2
LOAD_SPRITE 5 bride3
LOAD_SPRITE 6 bride4
LOAD_SPRITE 7 bride5
LOAD_SPRITE 8 bride6
LOAD_SPRITE 9 bride7
LOAD_SPRITE 10 bride8

LOAD_SPRITE 11 trees
LOAD_SPRITE 12 fen
LOAD_SPRITE 13 pole2

LOAD_SPRITE 14 rride1
LOAD_SPRITE 15 rride2
LOAD_SPRITE 16 rride3
LOAD_SPRITE 17 rride4
LOAD_SPRITE 18 rride5
LOAD_SPRITE 19 rride6
LOAD_SPRITE 20 rride7
LOAD_SPRITE 21 rride8

LOAD_SPRITE 22 yride1
LOAD_SPRITE 23 yride2
LOAD_SPRITE 24 yride3
LOAD_SPRITE 25 yride4
LOAD_SPRITE 26 yride5
LOAD_SPRITE 27 yride6
LOAD_SPRITE 28 yride7
LOAD_SPRITE 29 yride8

LOAD_SPRITE 30 pride1
LOAD_SPRITE 31 pride2
LOAD_SPRITE 32 pride3
LOAD_SPRITE 33 pride4
LOAD_SPRITE 34 pride5
LOAD_SPRITE 35 pride6
LOAD_SPRITE 36 pride7
LOAD_SPRITE 37 pride8

LOAD_SPRITE 38 gride1
LOAD_SPRITE 39 gride2
LOAD_SPRITE 40 gride3
LOAD_SPRITE 41 gride4
LOAD_SPRITE 42 gride5
LOAD_SPRITE 43 gride6
LOAD_SPRITE 44 gride7
LOAD_SPRITE 45 gride8

LOAD_SPRITE 46 tvcorn
LOAD_SPRITE 47 tvl
LOAD_SPRITE 48 tvr

LOAD_SPRITE 49 blue

LOAD_SPRITE 50 hrs1
LOAD_SPRITE 51 hrs2
LOAD_SPRITE 52 hrs3
LOAD_SPRITE 53 hrs4
LOAD_SPRITE 54 hrs5
LOAD_SPRITE 55 hrs6
LOAD_SPRITE 56 hrs7
LOAD_SPRITE 57 hrs8


LVAR_INT horse_speed_change_timer[5]
horse_speed_change_timer[0] = 0
horse_speed_change_timer[1] = 0
horse_speed_change_timer[2] = 0
horse_speed_change_timer[3] = 0
horse_speed_change_timer[4] = 0

LVAR_INT is_horse_moving[5]
is_horse_moving[0] = 0
is_horse_moving[1] = 0
is_horse_moving[2] = 0
is_horse_moving[3] = 0
is_horse_moving[4] = 0

//SCALES OFF EACH HORSE BY 10% AS THEY MOVE FURTHER AWAY
LVAR_FLOAT horse_scale[5] ten_percent
horse_scale[4] = 128.0
ten_percent	= horse_scale[4] / 10.0
horse_scale[3] = horse_scale[4] - ten_percent
ten_percent	= horse_scale[3] / 10.0
horse_scale[2] = horse_scale[3] - ten_percent
ten_percent	= horse_scale[2] / 10.0
horse_scale[1] = horse_scale[2] - ten_percent
ten_percent	= horse_scale[1] / 10.0
horse_scale[0] = horse_scale[1] - ten_percent

LVAR_FLOAT rider_scale[5]
rider_scale[4] = 64.0
ten_percent	= rider_scale[4] / 10.0
rider_scale[3] = rider_scale[4] - ten_percent
ten_percent	= rider_scale[3] / 10.0
rider_scale[2] = rider_scale[3] - ten_percent
ten_percent	= rider_scale[2] / 10.0
rider_scale[1] = rider_scale[2] - ten_percent
ten_percent	= rider_scale[1] / 10.0
rider_scale[0] = rider_scale[1] - ten_percent

//PUTS EACH HORSE 10% BEHIND THE ONE IN FRONT
LVAR_FLOAT horse_x[5] horse_y[5]
horse_x[0] = 150.0
horse_x[1] = 150.0
horse_x[2] = 150.0
horse_x[3] = 150.0
horse_x[4] = 150.0

horse_y[4] = 300.0
ten_percent	= horse_y[4] / 10.0
horse_y[3] = horse_y[4] - ten_percent
ten_percent	= horse_y[3] / 10.0
horse_y[2] = horse_y[3] - ten_percent
ten_percent	= horse_y[2] / 10.0
horse_y[1] = horse_y[2] - ten_percent
ten_percent	= horse_y[1] / 10.0
horse_y[0] = horse_y[1] - ten_percent

LVAR_FLOAT rider_x[5] rider_y[5]
rider_x[0] = 159.0
rider_x[1] = 159.0
rider_x[2] = 159.0
rider_x[3] = 159.0
rider_x[4] = 159.0

rider_y[4] = 261.0
ten_percent	= rider_y[4] / 10.0
rider_y[3] = rider_y[4] - ten_percent
ten_percent	= rider_y[3] / 10.0
rider_y[2] = rider_y[3] - ten_percent
ten_percent	= rider_y[2] / 10.0
rider_y[1] = rider_y[2] - ten_percent
ten_percent	= rider_y[1] / 10.0
rider_y[0] = rider_y[1] - ten_percent

LVAR_FLOAT horse_target_speed[5]
LVAR_FLOAT horse_speed[5]
horse_speed[0] = 0.0
horse_speed[1] = 0.0
horse_speed[2] = 0.0
horse_speed[3] = 0.0
horse_speed[4] = 0.0

//SETS A STARTING FRAME OF ANIMATION FOR EACH HORSE AND THEIR STARTING POSITION
LVAR_INT horse_anim_frame[5]
LVAR_INT sprite_address sprite
horse_anim_frame[0] = 2
horse_anim_frame[1] = 5
horse_anim_frame[2] = 6
horse_anim_frame[3] = 1
horse_anim_frame[4] = 4

LVAR_INT anim_starting_addr[5]
anim_starting_addr[0] = 3
anim_starting_addr[1] = 14
anim_starting_addr[2] = 22
anim_starting_addr[3] = 30
anim_starting_addr[4] = 38

//RANDOM DURATION OF RACE
LVAR_INT race_duration
//GENERATE_RANDOM_INT_IN_RANGE 20000 30000 race_duration
GET_GAME_TIMER game_timer
race_duration = game_timer + 25000

LVAR_FLOAT finish_line_x	actual_finish_line_x
finish_line_x = 512.0
actual_finish_line_x = finish_line_x + 124.0

LVAR_INT	horse_finish_position[5]
horse_finish_position[0] = 0
horse_finish_position[1] = 0
horse_finish_position[2] = 0
horse_finish_position[3] = 0
horse_finish_position[4] = 0

LVAR_FLOAT horse_text_position_y[5]

LVAR_INT first_place_flag second_place_flag third_place_flag forth_place_flag fifth_place_flag
first_place_flag = 0
second_place_flag = 0
third_place_flag = 0
forth_place_flag = 0
fifth_place_flag = 0

LVAR_FLOAT origin layer_x layer_scroll_speed
LVAR_FLOAT background_pos trees_pos bushes_pos fence_pos
background_pos = 0.0
trees_pos = 0.0
bushes_pos = 0.0
fence_pos = 0.0

LVAR_INT stop_everything_moving
stop_everything_moving = 0

LVAR_FLOAT text_origin_x text_origin_y text_position_x text_position_y rect_x rect_y
text_origin_x = 454.3640
text_origin_y = 152.8744

LVAR_INT race_winnings
race_winnings = 0

LVAR_INT horse_colour_R[5] horse_colour_G[5] horse_colour_B[5]

horse_colour_R[0] = 42	//blue
horse_colour_G[0] = 96
horse_colour_B[0] = 228

horse_colour_R[1] = 209	//pink
horse_colour_G[1] = 116
horse_colour_B[1] = 116

horse_colour_R[2] = 222	//yellow
horse_colour_G[2] = 185
horse_colour_B[2] = 103

horse_colour_R[3] = 133	//purple
horse_colour_G[3] = 65
horse_colour_B[3] = 190 

horse_colour_R[4] = 65	//green
horse_colour_G[4] = 190
horse_colour_B[4] = 151

REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_OTB_TRACK_START

lvar_int play_win_lose_sound
play_win_lose_sound = 0

lvar_int horse_anim_timer[5]
horse_anim_timer[0] = 12
horse_anim_timer[1] = 34
horse_anim_timer[2] = 0
horse_anim_timer[3] = 55
horse_anim_timer[4] = 20

DRAW_RECT 320.0 224.0 640.0 448.0 0 0 0 255

race_otb_loop:

	WAIT 0
	
	GET_GAME_TIMER game_timer

	IF stop_everything_moving = 0
		k = 0
		WHILE k < 5
			//GENERATES A RANDOM TARGET SPEED FOR THE HORSE EVERY SECOND OR TWO
			IF horse_speed_change_timer[k] < game_timer
				GENERATE_RANDOM_FLOAT_IN_RANGE -0.8 1.0 horse_target_speed[k]//need to change these figures to use the odds
				LVAR_INT random_int
				GENERATE_RANDOM_INT_IN_RANGE 1500 2500 random_int
				horse_speed_change_timer[k] = game_timer + random_int
				is_horse_moving[k] = 1
			ENDIF

			//INTERPOLATES HORSES SPEED TO THE NEW TARGET SPEED
			IF is_horse_moving[k] = 1
				IF horse_target_speed[k] < horse_speed[k]
					horse_speed[k] -=@ 0.01
					IF horse_target_speed[k] > horse_speed[k]
						horse_speed[k] = horse_target_speed[k]
						++ is_horse_moving[k]
					ENDIF
				ENDIF
				IF horse_target_speed[k] > horse_speed[k]
					horse_speed[k] +=@ 0.01
					IF horse_target_speed[k] < horse_speed[k]
						horse_speed[k] = horse_target_speed[k]
						++ is_horse_moving[k]
					ENDIF
				ENDIF
			ENDIF

			//ACTUALLY MOVES THE HORSE ON THE X PLANE
			IF is_horse_moving[k] > 0
				horse_x[k] +=@ horse_speed[k]
			ENDIF

			//MAKE SURE THE HORSE DOESN'T GO OFF THE EDGES OF THE SCREEN
			IF horse_x[k] < 80.0
				horse_x[k] = 80.0
			ENDIF

			IF horse_x[k] > 520.0
				horse_x[k] = 520.0
			ENDIF
			++ k
		ENDWHILE
	ENDIF

	//DRAW THE SCENE

	origin = background_pos
	y_offset = -76.0
	y_scale = 72.0 
	sprite = 1
	layer_scroll_speed = 4.0
	GOSUB draw_paralax_layer
	background_pos = origin

	origin = bushes_pos
	y_offset = -64.0 
	y_scale = 128.0 
	sprite = 2
	layer_scroll_speed = 5.0
	GOSUB draw_paralax_layer
	bushes_pos = origin

	origin = trees_pos
	y_offset = 0.0 
	y_scale = 256.0 
	sprite = 11
	layer_scroll_speed = 6.0
	GOSUB draw_paralax_layer
	trees_pos = origin
	
	IF race_duration < game_timer
		//DRAW FINISH LINE SPRITE
		IF finish_line_x > -200.0
			IF stop_everything_moving = 0
				finish_line_x -=@ 6.0
			ENDIF
			DRAW_SPRITE	13 finish_line_x 224.0 256.0 256.0 150 150 150 255
			actual_finish_line_x = finish_line_x + 124.0
		ENDIF

		//CHECK WHO WON THE RACE
		k = 0
		WHILE k < 5
			IF horse_finish_position[k] = 0
				IF horse_x[k] > actual_finish_line_x
					IF first_place_flag = 0
						horse_finish_position[k] = 1
						++ first_place_flag
					ELSE
						IF second_place_flag = 0
							horse_finish_position[k] = 2
							++ second_place_flag
						ELSE
							IF third_place_flag = 0
								horse_finish_position[k] = 3
								++ third_place_flag
							ELSE
								IF forth_place_flag = 0
									horse_finish_position[k] = 4
									++ forth_place_flag
								ELSE
									IF fifth_place_flag = 0
										horse_finish_position[k] = 5
										REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_OTB_TRACK_STOP
										++ stop_everything_moving
										++ fifth_place_flag
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			++ k
		ENDWHILE
	ENDIF

	
	//DRAW HORSE SPRITES
	k = 0
	WHILE k < 5
		sprite_address = 50 + horse_anim_frame[k]
		DRAW_SPRITE sprite_address horse_x[k] horse_y[k] horse_scale[k] horse_scale[k] 150 150 150 255
		sprite_address = anim_starting_addr[k] + horse_anim_frame[k]
		rider_x[k] = horse_x[k] + 9.0
		temp_float = rider_scale[k] / 2.0
		
		DRAW_SPRITE sprite_address rider_x[k] rider_y[k] rider_scale[k] temp_float 150 150 150 255
		
		IF stop_everything_moving = 0
			if horse_anim_timer[k] < game_timer
				++ horse_anim_frame[k]
				IF horse_anim_frame[k] > 7
					horse_anim_frame[k] = 0
				ENDIF
				horse_anim_timer[k] = game_timer + 67//HORSE ANIMATION IS 15 FPS
			endif
		ENDIF
		++ k
	ENDWHILE

	
	//DRAW FOREGROUND PARALAX
	origin = fence_pos
	y_offset = 96.0 
	y_scale = 64.0 
	sprite = 12
	layer_scroll_speed = 8.0
	GOSUB draw_paralax_layer
	fence_pos = origin

	DRAW_SPRITE 46 160.0 112.0 320.0 224.0 150 150 150 255 
	DRAW_SPRITE 46 480.0 112.0 -320.0 224.0 150 150 150 255 
	DRAW_SPRITE 46 480.0 336.0 -320.0 -224.0 150 150 150 255 
	DRAW_SPRITE 46 160.0 336.0 320.0 -224.0 150 150 150 255 
	DRAW_SPRITE 47 160.0 0.0 320.0 224.0 150 150 150 255 
	DRAW_SPRITE 47 160.0 448.0 320.0 224.0 150 150 150 255 
	DRAW_SPRITE 48 480.0 0.0 320.0 224.0 150 150 150 255 
	DRAW_SPRITE 48 480.0 448.0 320.0 224.0 150 150 150 255 
	

	IF horse_finish_position[0] > 0
	OR horse_finish_position[1] > 0
	OR horse_finish_position[2] > 0
	OR horse_finish_position[3] > 0
	OR horse_finish_position[4] > 0
		rect_x = text_origin_x + -25.0222
		rect_y = text_origin_y + 21.6946
		DRAW_RECT rect_x rect_y 355.0807 119.5942 47 62 90 200 
	ELSE
		rect_x = text_origin_x + -139.2772
		rect_y = text_origin_y + -56.1882
		DRAW_SPRITE 49 rect_x rect_y 280.2137 22.6679 150 150 150 255 

		text_position_x = text_origin_x - 157.3474
		text_position_y = text_origin_y - 68.5107
	 	GOSUB setup_text_otb
	   	set_text_justify off
		set_text_right_justify on
		DISPLAY_TEXT text_position_x text_position_y YOURHSE
		
		text_position_x = text_origin_x - 142.4242
		text_position_y += 12.7439
		DRAW_RECT text_position_x text_position_y 12.0 12.0 0 0 0 255

		k = 0
		WHILE k < 5
			IF button_state[k] = SELECTED
				DRAW_RECT text_position_x text_position_y 10.0 10.0 horse_colour_R[k] horse_colour_G[k] horse_colour_B[k] 255
			ENDIF
			++ k
		ENDWHILE

		text_position_x += 10.8216
		text_position_y -= 12.7439

	 	GOSUB setup_text_otb

		k = 0
		WHILE k < 5
			IF button_state[k] = SELECTED
				temp_int = horse[k]
				DISPLAY_TEXT text_position_x text_position_y $horse_names[temp_int]
			ENDIF
			++ k
		ENDWHILE
	ENDIF


	// Render race results.
	
	LVAR_FLOAT winning_info_x
	winning_info_x = text_origin_x + -23.3585
	
	k = 0
	WHILE k < 5
	
	 	IF horse_finish_position[k] > 0
			
			text_position_x = winning_info_x - 50.0
			text_position_y = text_origin_y - 50.0
			temp_float =# horse_finish_position[k]
			temp_float *= 23.5038
			text_position_y += temp_float
			DRAW_RECT text_position_x text_position_y 12.0 12.0 0 0 0 255
			DRAW_RECT text_position_x text_position_y 10.0 10.0 horse_colour_R[k] horse_colour_G[k] horse_colour_B[k] 255

			text_position_x = winning_info_x - 39.1244
			text_position_y = text_origin_y - 63.4833

			GOSUB setup_text_otb

			IF horse_finish_position[k] = 1
				get_hud_colour HUD_COLOUR_RED r g b a
				SET_TEXT_COLOUR r g b a
			ENDIF

			temp_float =# horse_finish_position[k]
			temp_float *= 23.5038

			horse_text_position_y[k] = text_position_y + temp_float

			temp_int = horse[k]

			SET_TEXT_SCALE 0.54 2.4961 
			DISPLAY_TEXT text_position_x horse_text_position_y[k] $horse_names[temp_int]

			IF button_state[k] = SELECTED
				text_position_x = winning_info_x - 64.0 
				GOSUB setup_text_otb
				SET_TEXT_JUSTIFY OFF 
				SET_TEXT_RIGHT_JUSTIFY ON
				SET_TEXT_SCALE 0.54 2.4961 
				DISPLAY_TEXT text_position_x horse_text_position_y[k] YOURHSE
			ENDIF

			text_position_x = winning_info_x - -170.5457 
			text_position_y = text_origin_y - 63.4833 
			GOSUB setup_text_otb 
			SET_TEXT_RIGHT_JUSTIFY ON 

			IF horse_finish_position[k] = 1 
				get_hud_colour HUD_COLOUR_RED r g b a
				SET_TEXT_COLOUR r g b a
			ENDIF

			temp_float =# horse_finish_position[k]
			temp_float *= 23.5038
			horse_text_position_y[k] = text_position_y  + temp_float
			SET_TEXT_SCALE 0.54 2.4961 

			IF horse_finish_position[k] = 1 
				DISPLAY_TEXT text_position_x horse_text_position_y[k] FIRST
			ENDIF

			IF horse_finish_position[k] = 2 
				DISPLAY_TEXT text_position_x horse_text_position_y[k] SECOND
			ENDIF

			IF horse_finish_position[k] = 3 
				DISPLAY_TEXT text_position_x horse_text_position_y[k] THIRD
			ENDIF

			IF horse_finish_position[k] > 3  
				DISPLAY_TEXT_WITH_NUMBER text_position_x horse_text_position_y[k] NTH horse_finish_position[k]
			ENDIF
		ENDIF
		++ k
	ENDWHILE


	// DISPLAY TEXT TELLING PLAYER WHAT HE BET AND WON ON COMPLETION OF RACE
	
	IF horse_finish_position[0] > 0
	AND horse_finish_position[1] > 0
	AND horse_finish_position[2] > 0
	AND horse_finish_position[3] > 0
	AND horse_finish_position[4] > 0
		
		rect_x = text_origin_x + 44.4599
		rect_y = text_origin_y + 218.4174 
		DRAW_SPRITE 49 rect_x rect_y 155.396 52.7081 150 150 150 255 
		text_position_x = text_origin_x - 39.2243 
		text_position_y = text_origin_y - -190.9665 
		GOSUB setup_text_otb 
		SET_TEXT_SCALE 0.5 2.4961 
		DISPLAY_TEXT_WITH_NUMBER text_position_x text_position_y YOURSTK cash_stake 

		// Work out player winnings.

		k = 0
		WHILE k < 5
			IF horse_finish_position[k] = 1
			AND	button_state[k] = SELECTED
				race_winnings = cash_stake * horse_odds[k]
			ENDIF
			++ k
		ENDWHILE

		text_position_y += 25.0
		GOSUB setup_text_otb 
		SET_TEXT_SCALE 0.5 2.4961 
		DISPLAY_TEXT_WITH_NUMBER text_position_x text_position_y YOUWIN race_winnings 
		rect_x = text_origin_x + -129.2806 
		rect_y = text_origin_y + 259.5944 
		DRAW_SPRITE 49 rect_x rect_y 239.5561 12.6761 150 150 150 255 
		GOSUB setup_text_otb 
		SET_TEXT_SCALE 0.5496 2.1344 
		SET_TEXT_CENTRE ON 
		DISPLAY_TEXT 330.2087 403.5891 XTOCONT
 	
		IF play_win_lose_sound = 0
			IF race_winnings > 0
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_OTB_WIN
			else
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_OTB_LOSE
			endif
			play_win_lose_sound = 1
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 CROSS
			IF pad1_cross_pressed = 0
				pad1_cross_pressed = 1
				increment_int_stat MONEY_SPENT_GAMBLING	cash_stake
				
				IF race_winnings > 0
					REGISTER_INT_STAT BIGGEST_GAMBLING_WIN race_winnings
					increment_int_stat MONEY_WON_GAMBLING race_winnings
					race_winnings += cash_stake
					ADD_SCORE player1 race_winnings
				else
					REGISTER_INT_STAT BIGGEST_GAMBLING_LOSS cash_stake
				ENDIF
				do_fade 0 fade_out
				wait 0
				RETURN
			ENDIF
		ELSE
			pad1_cross_pressed = 0
		ENDIF
	ENDIF

GOTO race_otb_loop


// mission cleanup

mission_cleanup_otb:

    REMOVE_TEXTURE_DICTIONARY

    DO_FADE 500 FADE_IN

    flag_player_on_mission = 0

	MISSION_HAS_FINISHED
RETURN
		

draw_paralax_layer:
	LVAR_FLOAT y_scale
	LVAR_FLOAT y_offset
	y_offset +=	224.0
	layer_x = origin - 256.0
	DRAW_SPRITE	sprite layer_x y_offset 256.0 y_scale 150 150 150 255
	layer_x = origin
	DRAW_SPRITE	sprite layer_x y_offset 256.0 y_scale 150 150 150 255
	layer_x = origin + 256.0
	DRAW_SPRITE	sprite layer_x y_offset 256.0 y_scale 150 150 150 255
	layer_x = origin + 512.0
	DRAW_SPRITE	sprite layer_x y_offset 256.0 y_scale 150 150 150 255

	IF stop_everything_moving = 0
		origin -=@ layer_scroll_speed
	ENDIF
	IF origin < 0.0
		origin += 256.0
	ENDIF
RETURN



setup_text_otb:
    
    SET_TEXT_COLOUR 180 180 180 255
	SET_TEXT_SCALE 0.6146 2.4961
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_JUSTIFY OFF
	SET_TEXT_CENTRE OFF
	SET_TEXT_WRAPX 640.0
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_BACKGROUND OFF
	SET_TEXT_DROPSHADOW 2 0 0 0 180
RETURN

LeftPressed:

IF button_state2[1] = SELECTED
	button_state2[1] = UNSELECTED
	button_state2[0] = SELECTED
	RETURN
ENDIF

IF button_state2[3] = SELECTED
	button_state2[3] = UNSELECTED
	button_state2[2] = SELECTED
	RETURN
ENDIF

IF button_state2[5] = SELECTED
	button_state2[5] = UNSELECTED
	button_state2[4] = SELECTED
	RETURN
ENDIF

IF button_state2[7] = SELECTED
	button_state2[7] = UNSELECTED
	button_state2[6] = SELECTED
	RETURN
ENDIF

RETURN

RightPressed:

IF button_state2[0] = SELECTED
	button_state2[0] = UNSELECTED
	button_state2[1] = SELECTED
	RETURN
ENDIF

IF button_state2[2] = SELECTED
	button_state2[2] = UNSELECTED
	button_state2[3] = SELECTED
	RETURN
ENDIF

IF button_state2[4] = SELECTED
	button_state2[4] = UNSELECTED
	button_state2[5] = SELECTED
	RETURN
ENDIF

IF button_state2[6] = SELECTED
	button_state2[6] = UNSELECTED
	button_state2[7] = SELECTED
	RETURN
ENDIF

RETURN

UpPressed:

IF button_state2[2] = SELECTED
	button_state2[2] = UNSELECTED
	button_state2[0] = SELECTED
	RETURN
ENDIF

IF button_state2[3] = SELECTED
	button_state2[3] = UNSELECTED
	button_state2[1] = SELECTED
	RETURN
ENDIF

IF button_state2[4] = SELECTED
	button_state2[4] = UNSELECTED
	button_state2[2] = SELECTED
	RETURN
ENDIF

IF button_state2[5] = SELECTED
	button_state2[5] = UNSELECTED
	button_state2[3] = SELECTED
	RETURN
ENDIF

IF button_state2[6] = SELECTED
	button_state2[6] = UNSELECTED
	button_state2[4] = SELECTED
	RETURN
ENDIF

IF button_state2[7] = SELECTED
	button_state2[7] = UNSELECTED
	button_state2[5] = SELECTED
RETURN
ENDIF

IF place_bet_state = 1
	place_bet_state = 0
	button_state2[6] = SELECTED
ENDIF
IF place_bet_state = 2
	place_bet_state = 0
	button_state2[7] = SELECTED
ENDIF

RETURN

DownPressed:

IF button_state2[0] = SELECTED
	button_state2[0] = UNSELECTED
	button_state2[2] = SELECTED
	RETURN
ENDIF

IF button_state2[1] = SELECTED
	button_state2[1] = UNSELECTED
	button_state2[3] = SELECTED
	RETURN
ENDIF

IF button_state2[2] = SELECTED
	button_state2[2] = UNSELECTED
	button_state2[4] = SELECTED
	RETURN
ENDIF

IF button_state2[3] = SELECTED
	button_state2[3] = UNSELECTED
	button_state2[5] = SELECTED
	RETURN
ENDIF

IF button_state2[4] = SELECTED
	button_state2[4] = UNSELECTED
	button_state2[6] = SELECTED
	RETURN
ENDIF

IF button_state2[5] = SELECTED
	button_state2[5] = UNSELECTED
	button_state2[7] = SELECTED
	RETURN
ENDIF

IF cash_stake > 0
	IF button_state2[6] = SELECTED
		button_state2[6] = UNSELECTED
		place_bet_state = 1
		RETURN
	ENDIF
	IF button_state2[7] = SELECTED
		button_state2[7] = UNSELECTED
		place_bet_state = 2
		RETURN
	ENDIF
ENDIF

RETURN


}