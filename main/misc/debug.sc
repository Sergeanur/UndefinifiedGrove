MISSION_START

VAR_FLOAT x_float_m y_float_m z_float_m text_x text_y text_z text_h	player_heading_debug debug_car_heading
VAR_FLOAT cut_offsetX cut_offsetY cut_offsetZ
VAR_INT carcolour_flag1 carcolour_flag2 carcolour_counter1 carcolour_counter2 carcolour_counter3 carcolour_counter4
VAR_INT magic_car record_car1
VAR_INT initial_create_car widscreen_flag debug_number is_player_in_mission_menu1 text_debug_num change_area_code
VAR_INT counter_create_car cheat_mode_on mission_debug_page
VAR_INT button_pressed_warp button_pressed_warp_odd button_pressed_main relative_button_press button_pressed_cutscene_main   
VAR_INT repeat_button_press text_button_pressed	initial_pickup debug_active 
VAR_INT no_cars repeat_butt_press print_stuff_button print_stuff_counter
VAR_INT counter_create_pickup magic_pickup test_heli1_created debug_cutscene_number cutscene_index
VAR_INT weather_crap add_just_the_once_though debug_game_timer visible_area cut_doesnt_have_text
VAR_INT is_stunt_jump_debug	is_rightshock_pressed 
VAR_FLOAT corona_x corona_y corona_z stunt_jump_debug_X stunt_jump_debug_Y stunt_jump_debug_Z /*bulldozerY_d dumperY_d*/
VAR_FLOAT RStickX_F RStickY_F truthX_d debug_heading TEXT_POS_X TEXT_POS_Y	TEXT_POS_Y_STEP	TEXT_SCALEX TEXT_SCALEY
VAR_FLOAT driving_schoolx_d basketballx_d limox_d directorx_d valetx_d heistX_d vcrashX_d desert2X_d strap2X_d
VAR_FLOAT goto_thereX goto_thereY goto_thereZ syndX_d stealX_d	casinoX_d cesarX_d docX_d pimpY_d
VAR_FLOAT sweetX_d ryderY_d smokeX_d strapX_d wuziX_d introY_d	bcrashX_d hitchX_d pilotX_d	TheheistX_d
VAR_FLOAT mansionX_d crashY_d traceX_d[4] zeroX_d bcesarX_d scrashX_d garageX_d desertX_d catx_d
VAR_TEXT_LABEL $new_cut $cuttext
VAR_FLOAT second_playerX second_playerY second_playerZ
VAR_INT second_playercreated COL1_R COL1_G COL1_B COL2_R COL2_G COL2_B cut_visible_area[84]	cut_visible_area2[75] cut_visible_area3[23]
VAR_TEXT_LABEL $cutscene_name[84] $cutscene_file[84] $cutscene_text[84]
VAR_TEXT_LABEL $cutscene_name2[75] $cutscene_file2[75] $cutscene_text2[75] 
VAR_TEXT_LABEL $cutscene_name3[23] $cutscene_file3[23] 
VAR_INT debug_visible_area
VAR_INT iAudioDebug


//--- AUDIO DEBUG CONSTS
CONST_INT TEXT1				0
CONST_INT TEXT2				1
CONST_INT GET_PED_1		 	2
CONST_INT GENERATE_PED_1	3
CONST_INT GET_PED_2			4
CONST_INT GENERATE_PED_2 	5
CONST_INT FACING			6
CONST_INT CHAT				7
CONST_INT END				8

CONST_INT AD_BUTTON_NONE		0
CONST_INT AD_BUTTON_DPADUP 		1
CONST_INT AD_BUTTON_DPADDOWN 	2
CONST_INT AD_BUTTON_DPADLEFT 	3
CONST_INT AD_BUTTON_DPADRIGHT 	4
CONST_INT AD_BUTTON_CROSS		5

// ******************PAGE1*********************

// DROPPED CUTS- CRSAH2A, BCRAS2 don't think there's ever been a BCRAS2??


$cutscene_file[0] = PROLOG1
cut_visible_area[0]	= 14
$cutscene_name[0] = CUT01
$cutscene_text[0] = INTRO1

$cutscene_file[1] = INTRO1A
cut_visible_area[1]	= 3
$cutscene_name[1] = CUT02
$cutscene_text[1] = INTRO1

$cutscene_file[2] = INTRO1B
cut_visible_area[2]	= 0
$cutscene_name[2] = CUT03
$cutscene_text[2] = INTRO1

$cutscene_file[3] = INTRO2A
cut_visible_area[3]	= 2
$cutscene_name[3] = CUT04
$cutscene_text[3] = INTRO2

$cutscene_file[4] = SWEET1A
cut_visible_area[4]	= 0
$cutscene_name[4] = CUT05
$cutscene_text[4] = SWEET1

$cutscene_file[5] = SWEET1B
cut_visible_area[5]	= 1
$cutscene_name[5] = CUT06
$cutscene_text[5] = SWEET1B

$cutscene_file[6] = SWEET3A
cut_visible_area[6]	= 1
$cutscene_name[6] = CUT07
$cutscene_text[6] = SWEET2

$cutscene_file[7] = SWEET3B
cut_visible_area[7]	= 0
$cutscene_name[7] = CUT08
$cutscene_text[7] = SWEET2

$cutscene_file[8] = SWEET2A
cut_visible_area[8]	= 0
$cutscene_name[8] = CUT09
$cutscene_text[8] = SWEET3

$cutscene_file[9] = SWEET2B
cut_visible_area[9]	= 0
$cutscene_name[9] = CUT10
$cutscene_text[9] = SWEET3

$cutscene_file[10] = SWEET4A
cut_visible_area[10] = 0
$cutscene_name[10] = CUT11
$cutscene_text[10] = SWEET4

$cutscene_file[11] = SWEET5A
cut_visible_area[11] = 1
$cutscene_name[11] = CUT12
$cutscene_text[11] = SWEET5

$cutscene_file[12] = SWEET6A
cut_visible_area[12] = 1
$cutscene_name[12] = CUT13
$cutscene_text[12] = SWEET6

$cutscene_file[13] = SWEET6B
cut_visible_area[13] = 0
$cutscene_name[13] = CUT14
$cutscene_text[13] = SWEET6

$cutscene_file[14] = SWEET7A
cut_visible_area[14] = 0
$cutscene_name[14] = CUT15
$cutscene_text[14] = SWEET7

$cutscene_file[15] = SMOKE1A
cut_visible_area[15] = 0
$cutscene_name[15] = CUT16
$cutscene_text[15] = SMOKE1

$cutscene_file[16] = SMOKE1B
cut_visible_area[16] = 0
$cutscene_name[16] = CUT17
$cutscene_text[16] = SMOKE1

$cutscene_file[17] = SMOKE2A
cut_visible_area[17] = 0
$cutscene_name[17] = CUT18 
$cutscene_text[17] = SMOKE2

$cutscene_file[18] = SMOKE2B
cut_visible_area[18] = 0
$cutscene_name[18] = CUT19
$cutscene_text[18] = SMOKE2

$cutscene_file[19] = SMOKE3A
cut_visible_area[19] = 0
$cutscene_name[19] = CUT20
$cutscene_text[19] = SMOKE3

$cutscene_file[20] = SMOKE4A
cut_visible_area[20] = 0
$cutscene_name[20] = CUT21
$cutscene_text[20] = SMOKE4

$cutscene_file[21] = RYDER1A
cut_visible_area[21] = 0
$cutscene_name[21] = CUT22
$cutscene_text[21] = RYDER1

$cutscene_file[22] = RYDER2A
cut_visible_area[22] = 0
$cutscene_name[22] = CUT23
$cutscene_text[22] = RYDER2

$cutscene_file[23] = RYDER3A
cut_visible_area[23] = 2
$cutscene_name[23] = CUT24
$cutscene_text[23] = RYDER3

$cutscene_file[24] = STRAP1A
cut_visible_area[24] = 0
$cutscene_name[24] = CUT25
$cutscene_text[24] = STRAP1

$cutscene_file[25] = STRAP2A
cut_visible_area[25] = 10
$cutscene_name[25] = CUT26
$cutscene_text[25] = STRAP2

$cutscene_file[26] = STRAP3A
cut_visible_area[26] = 10
$cutscene_name[26] = CUT27
$cutscene_text[26] = STRAP3

$cutscene_file[27] = STRAP4A
cut_visible_area[27] = 0
$cutscene_name[27] = CUT28
$cutscene_text[27] = STRAP4

$cutscene_file[28] = Strp4b1
cut_visible_area[28] = 3
$cutscene_name[28] = CUT29
$cutscene_text[28] = STRAP4

$cutscene_file[29] = Strp4b2 //2nd part
cut_visible_area[29] = 0
$cutscene_name[29] = CUT135
$cutscene_text[29] = STRAP4

$cutscene_file[30] = CRASH1A
cut_visible_area[30] = 17
$cutscene_name[30] = CUT30
$cutscene_text[30] = CRASH1

$cutscene_file[31] = CRASH2A
cut_visible_area[31] = 0
$cutscene_name[31] = CUT31
$cutscene_text[31] = CRASH2

$cutscene_file[32] = CRASH3A
cut_visible_area[32] = 0
$cutscene_name[32] = CUT32
$cutscene_text[32] = CRASH3

$cutscene_file[33] = CESAR1A
cut_visible_area[33] = 0
$cutscene_name[33] = CUT33
$cutscene_text[33] = CESAR1

$cutscene_file[34] = CESAR2A
cut_visible_area[34] = 0
$cutscene_name[34] = CUT34
$cutscene_text[34] = CESAR2

$cutscene_file[35] = FINAL1A
cut_visible_area[35] = 1
$cutscene_name[35] = CUT35
$cutscene_text[35] = LAFIN1

$cutscene_file[36] = FINAL2A
cut_visible_area[36] = 1
$cutscene_name[36] = CUT36
$cutscene_text[36] = LAFIN2

$cutscene_file[37] = FINAL2B
cut_visible_area[37] = 0
$cutscene_name[37] = CUT37
$cutscene_text[37] = LAFIN2

$cutscene_file[38] = BCRAS1 
cut_visible_area[38] = 0
$cutscene_name[38] = CUT38
$cutscene_text[38] = BCRASH1

$cutscene_file[39] = BCESAR2
cut_visible_area[39] = 2
$cutscene_name[39] = CUT39
$cutscene_text[39] = BCESAR2

$cutscene_file[40] = BCESAR4
cut_visible_area[40] = 0
$cutscene_name[40] = CUT40
$cutscene_text[40] = BCESAR4

$cutscene_file[41] = BCESA4W
cut_visible_area[41] = 0
$cutscene_name[41] = CUT41
$cutscene_text[41] = BCESAR4

$cutscene_file[42] = BCESA5W 
cut_visible_area[42] = 0
$cutscene_name[42] = CUT42
$cutscene_text[42] = BCESAR4

$cutscene_file[43] = CAT_1
cut_visible_area[43] = 1
$cutscene_name[43] = CUT43
$cutscene_text[43] = CAT

$cutscene_file[44] = CAT_2
cut_visible_area[44] = 0
$cutscene_name[44] = CUT44
$cutscene_text[44] = CAT

$cutscene_file[45] = CAT_3
cut_visible_area[45] = 0
$cutscene_name[45] = CUT45
$cutscene_text[45] = CAT

$cutscene_file[46] = CAT_4
cut_visible_area[46] = 0
$cutscene_name[46] = CUT46
$cutscene_text[46] = CAT

$cutscene_file[47] = DESERT1
cut_visible_area[47] = 0
$cutscene_name[47] = CUT47
$cutscene_text[47] = TORENO1

$cutscene_file[48] = DESERT2
cut_visible_area[48] = 0
$cutscene_name[48] = CUT48
$cutscene_text[48] = TORENO2

$cutscene_file[49] = DESERT3
cut_visible_area[49] = 0
$cutscene_name[49] = CUT49
$cutscene_text[49] = DSERT3

$cutscene_file[50] = DESERT4
cut_visible_area[50] = 0
$cutscene_name[50] = CUT50
$cutscene_text[50] = DSERT4

$cutscene_file[51] = DESERT6
cut_visible_area[51] = 0
$cutscene_name[51] = CUT52
$cutscene_text[51] = DSERT6

$cutscene_file[52] = DESERT8
cut_visible_area[52] = 0
$cutscene_name[52] = CUT54
$cutscene_text[52] = DSERT8

$cutscene_file[53] = DESERT9
cut_visible_area[53] = 0
$cutscene_name[53] = CUT55
$cutscene_text[53] = DSERT9

$cutscene_file[54] = DES_10A
cut_visible_area[54] = 0
$cutscene_name[54] = CUT56
$cutscene_text[54] = DSERT10

$cutscene_file[55] = DES_10B
cut_visible_area[55] = 0
$cutscene_name[55] = CUT57
$cutscene_text[55] = DSERT10

$cutscene_file[56] = FARL_2A		
cut_visible_area[56] = 3
$cutscene_name[56] = CUT60
$cutscene_text[56] = FARLIE2

$cutscene_file[57] = FARL_3A		
cut_visible_area[57] = 3
$cutscene_name[57] = CUT61
$cutscene_text[57] = FARLIE3

$cutscene_file[58] = FARL_3B
cut_visible_area[58] = 0
$cutscene_name[58] = CUT62
$cutscene_text[58] = FARLIE3

$cutscene_file[59] = FARL_4A
cut_visible_area[59] = 1
$cutscene_name[59] = CUT63
$cutscene_text[59] = FARLIE4

$cutscene_file[60] = FARL_5A
cut_visible_area[60] = 1
$cutscene_name[60] = CUT64
$cutscene_text[60] = FARLIE5

$cutscene_file[61] = GARAG1B
cut_visible_area[61] = 1
$cutscene_name[61] = CUT65
$cutscene_text[61] = GARAGE1

$cutscene_file[62] = GARAG1C
cut_visible_area[62] = 1
$cutscene_name[62] = CUT66
$cutscene_text[62] = GARAGE1

$cutscene_file[63] = GARAG3A
cut_visible_area[63] = 1
$cutscene_name[63] = CUT67
$cutscene_text[63] = GARAGE2

$cutscene_file[64] = SCRASH1
cut_visible_area[64] = 1
$cutscene_name[64] = CUT69
$cutscene_text[64] = VALET1

$cutscene_file[65] = SCRASH2
cut_visible_area[65] = 1
$cutscene_name[65] = CUT70
$cutscene_text[65] = SCRASH2

$cutscene_file[66] = STEAL_1
cut_visible_area[66] = 1
$cutscene_name[66] = CUT71
$cutscene_text[66] = STEAL1

$cutscene_file[67] = STEAL_2
cut_visible_area[67] = 1
$cutscene_name[67] = CUT72
$cutscene_text[67] = STEAL2

$cutscene_file[68] = STEAL_4
cut_visible_area[68] = 1
$cutscene_name[68] = CUT74
$cutscene_text[68] = STEAL4

$cutscene_file[69] = STEAL_5
cut_visible_area[69] = 1
$cutscene_name[69] = CUT75
$cutscene_text[69] = STEAL5

$cutscene_file[70] = SYND_2A							   
cut_visible_area[70] = 1
$cutscene_name[70] = CUT76
$cutscene_text[70] = SYN2

$cutscene_file[71] = SYND_2B							   
cut_visible_area[71] = 3
$cutscene_name[71] = CUT77
$cutscene_text[71] = SYN2

$cutscene_file[72] = SYND_3A							   
cut_visible_area[72] = 0
$cutscene_name[72] = CUT78
$cutscene_text[72] = SYN3

$cutscene_file[73] = SYND_4A							   
cut_visible_area[73] = 1
$cutscene_name[73] = CUT79
$cutscene_text[73] = SYN4

$cutscene_file[74] = SYND_7
cut_visible_area[74] = 1
$cutscene_name[74] = CUT80
$cutscene_text[74] = SYN7

$cutscene_file[75] = TRUTH_1
cut_visible_area[75] = 12
$cutscene_name[75] = CUT81
$cutscene_text[75] = TRU1

$cutscene_file[76] = TRUTH_2
cut_visible_area[76] = 0
$cutscene_name[76] = CUT82
$cutscene_text[76] = TRU2

$cutscene_file[77] = WOOZI1A
cut_visible_area[77] = 1
$cutscene_name[77] = CUT83
$cutscene_text[77] = WUZI1

$cutscene_file[78] = WOOZIE2
cut_visible_area[78] = 1
$cutscene_name[78] = CUT84
$cutscene_text[78] = WUZI2

$cutscene_file[79] = WOOZIE4
cut_visible_area[79] = 1
$cutscene_name[79] = CUT86
$cutscene_text[79] = WUZI4

$cutscene_file[80] = ZERO_1
cut_visible_area[80] = 6
$cutscene_name[80] = CUT87
$cutscene_text[80] = ZERO1

$cutscene_file[81] = ZERO_2
cut_visible_area[81] = 6
$cutscene_name[81] = CUT88
$cutscene_text[81] = ZERO2

$cutscene_file[82] = ZERO_3
cut_visible_area[82] = 6
$cutscene_name[82] = CUT89
$cutscene_text[82] = ZERO3

$cutscene_file[83] = ZERO_4
cut_visible_area[83] = 6
$cutscene_name[83] = CUT90
$cutscene_text[83] = ZERO4
					 
// ******************PAGE2*********************

$cutscene_file2[0] = W2_ALT
cut_visible_area2[0] = 1
$cutscene_name2[0] = CUT91
$cutscene_text2[0] = WUZI2

$cutscene_file2[1] = BCESAR5
cut_visible_area2[1] = 0
$cutscene_name2[1] = CUT92
$cutscene_text2[1] = BCESAR4

$cutscene_file2[2] = D8_ALT
cut_visible_area2[2] = 0
$cutscene_name2[2] = CUT94
$cutscene_text2[2] = DSERT8

$cutscene_file2[3] = D10_ALT
cut_visible_area2[3] = 0
$cutscene_name2[3] = CUT95
$cutscene_text2[3] = DSERT10

$cutscene_file2[4] = SYND_3B
cut_visible_area2[4] = 0
$cutscene_name2[4] = CUT96
$cutscene_text2[4] = SYN3

$cutscene_file2[5] = SYND_4B
cut_visible_area2[5] = 3
$cutscene_name2[5] = CUT97
$cutscene_text2[5] = SYN4

$cutscene_file2[6] = CAS_1a
cut_visible_area2[6] = 11
$cutscene_name2[6] = CUT98 
$cutscene_text2[6] = CASINO1

$cutscene_file2[7] = WOOZI1B
cut_visible_area2[7] = 1
$cutscene_name2[7] = CUT83
$cutscene_text2[7] = WUZI1

$cutscene_file2[8] = CAS_2 
cut_visible_area2[8] = 11
$cutscene_name2[8] = CUT100
$cutscene_text2[8] = CASINO2

$cutscene_file2[9] = CAS_3 
cut_visible_area2[9] = 11
$cutscene_name2[9] = CUT101
$cutscene_text2[9] = CASINO3

$cutscene_file2[10] = CAS_4a 
cut_visible_area2[10] = 0
$cutscene_name2[10] = CUT102
$cutscene_text2[10] = CASINO4

$cutscene_file2[11] = CAS_4b 
cut_visible_area2[11] = 0
$cutscene_name2[11] = CUT103
$cutscene_text2[11] = CASINO4

$cutscene_file2[12] = CAS_4c
cut_visible_area2[12] = 2
$cutscene_name2[12] = CUT104
$cutscene_text2[12] = CASINO4

$cutscene_file2[13] = CAS_5a
cut_visible_area2[13] = 2
$cutscene_name2[13] = CUT105
$cutscene_text2[13] = CASINO5

$cutscene_file2[14] = CAS_6a
cut_visible_area2[14] = 2
$cutscene_name2[14] = CUT106
$cutscene_text2[14] = CASINO6

$cutscene_file2[15] = CAS6b_1 
cut_visible_area2[15] = 1
$cutscene_name2[15] = CUT107
$cutscene_text2[15] = CASINO6

$cutscene_file2[16] = CAS_7b 
cut_visible_area2[16] = 11
$cutscene_name2[16] = CUT109
$cutscene_text2[16] = CASINO7

$cutscene_file2[17] = Cas_9a1 
cut_visible_area2[17] = 2
$cutscene_name2[17] = CUT110
$cutscene_text2[17] = CASINO9

$cutscene_file2[18] = CAS_11a
cut_visible_area2[18] = 2
$cutscene_name2[18] = CUT111
$cutscene_text2[18] = CASIN10

$cutscene_file2[19] = HEIST1a
cut_visible_area2[19] = 10
$cutscene_name2[19] = CUT112
$cutscene_text2[19] = HEIST1

$cutscene_file2[20] = HEIST2a 
cut_visible_area2[20] = 10
$cutscene_name2[20] = CUT113
$cutscene_text2[20] = HEIST3

$cutscene_file2[21] = HEIST4a 
cut_visible_area2[21] = 10
$cutscene_name2[21] = CUT115
$cutscene_text2[21] = HEIST2

$cutscene_file2[22] = HEIST5a 
cut_visible_area2[22] = 10
$cutscene_name2[22] = CUT116
$cutscene_text2[22] = HEIST4

$cutscene_file2[23] = HEIST6a 
cut_visible_area2[23] = 10
$cutscene_name2[23] = CUT117
$cutscene_text2[23] = HEIST5

$cutscene_file2[24] = HEIST8a 
cut_visible_area2[24] = 0
$cutscene_name2[24] = CUT118
$cutscene_text2[24] = HEIST9

$cutscene_file2[25] = CRASHV1 
cut_visible_area2[25] = 0
$cutscene_name2[25] = CUT119
$cutscene_text2[25] = VCR1

$cutscene_file2[26] = CRASV2A 
cut_visible_area2[26] = 0
$cutscene_name2[26] = CUT120
$cutscene_text2[26] = VCR2

$cutscene_file2[27] = DOC_2  
cut_visible_area2[27] = 0
$cutscene_name2[27] = CUT121
$cutscene_text2[27] = DOC2

$cutscene_file2[28] = BHILL1 
cut_visible_area2[28] = 11
$cutscene_name2[28] = CUT122
$cutscene_text2[28] = MAN_1

$cutscene_file2[29] = BHILL2 
cut_visible_area2[29] = 5
$cutscene_name2[29] = CUT123
$cutscene_text2[29] = MAN_2

$cutscene_file2[30] = BHILL3a
cut_visible_area2[30] = 5
$cutscene_name2[30] = CUT124
$cutscene_text2[30] = MAN_3

$cutscene_file2[31] = BHILL3b 
cut_visible_area2[31] = 0
$cutscene_name2[31] = CUT125
$cutscene_text2[31] = MAN_3

$cutscene_file2[32] = BHILL3c 
cut_visible_area2[32] = 0
$cutscene_name2[32] = CUT126
$cutscene_text2[32] = MAN_3

$cutscene_file2[33] = BHILL5a 
cut_visible_area2[33] = 5
$cutscene_name2[33] = CUT127
$cutscene_text2[33] = MAN_5

$cutscene_file2[34] = BHILL5b 
cut_visible_area2[34] = 3
$cutscene_name2[34] = CUT128
$cutscene_text2[34] = MAN_5

$cutscene_file2[35] = GROVE1a 
cut_visible_area2[35] = 3
$cutscene_name2[35] = CUT129
$cutscene_text2[35] = GROVE1

$cutscene_file2[36] = GROVE1b 
cut_visible_area2[36] = 3
$cutscene_name2[36] = CUT130
$cutscene_text2[36] = GROVE1

$cutscene_file2[37] = GROVE1c 
cut_visible_area2[37] = 2
$cutscene_name2[37] = CUT131
$cutscene_text2[37] = GROVE1

$cutscene_file2[38] = GROVE2  
cut_visible_area2[38] = 1
$cutscene_name2[38] = CUT132
$cutscene_text2[38] = GROVE2

$cutscene_file2[39] = RIOT_1A  
cut_visible_area2[39] = 5
$cutscene_name2[39] = CUT133
$cutscene_text2[39] = RIOT1

$cutscene_file2[40] = RIOT_2 
cut_visible_area2[40] = 1
$cutscene_name2[40] = CUT134
$cutscene_text2[40] = RIOT2

$cutscene_file2[41] = RIOT_4a
cut_visible_area2[41] = 1
$cutscene_name2[41] = CUT137
$cutscene_text2[41] = RIOT4

$cutscene_file2[42] = RIOT_4b 
cut_visible_area2[42] = 0
$cutscene_name2[42] = CUT138
$cutscene_text2[42] = RIOT4

$cutscene_file2[43] = RIOT_4c 
cut_visible_area2[43] = 2
$cutscene_name2[43] = CUT139
$cutscene_text2[43] = RIOT4

$cutscene_file2[44] = RIOT_4d 
cut_visible_area2[44] = 2
$cutscene_name2[44] = CUT140
$cutscene_text2[44] = RIOT4

$cutscene_file2[45] = Riot4e1 
cut_visible_area2[45] = 0
$cutscene_name2[45] = CUT141
$cutscene_text2[45] = RIOT4

$cutscene_file2[46] = EPILOG  
cut_visible_area2[46] = 3
$cutscene_name2[46] = CUT142
$cutscene_text2[46] = RIOT4

$cutscene_file2[47] = SWEET1C
cut_visible_area2[47] = 3
$cutscene_name2[47] = CUT143
$cutscene_text2[47] = SWEET1B

$cutscene_file2[48] = PROLOG2
cut_visible_area2[48] = 0
$cutscene_name2[48] = CUT150
$cutscene_text2[48] = INTRO1

$cutscene_file2[49] = PROLOG3 
cut_visible_area2[49] = 0
$cutscene_name2[49] = CUT151
$cutscene_text2[49] = INTRO1

$cutscene_file2[50] = BCRAS2 
cut_visible_area2[50] = 0
$cutscene_name2[50] = CUT152
$cutscene_text2[50] = BCRASH1

$cutscene_file2[51] = DATE1A //DATE 1A PIZZA //Offset: x 370.0 y -125.0 z 1001.52
cut_visible_area2[51] = 5
$cutscene_name2[51] = CUT_D1
$cutscene_text2[51] = SWEET3

$cutscene_file2[52] = DATE1B //DATE 1B PIZZA //Offset: x 370.0 y -125.0 z 1001.52
cut_visible_area2[52] = 5
$cutscene_name2[52] = CUT_D2
$cutscene_text2[52] = SWEET3

$cutscene_file2[53] = DATE1A //DATE 1A BURGER //Offset: X367.891 Y-67.591 Z1002.516
cut_visible_area2[53] = 10
$cutscene_name2[53] = CUT_D3
$cutscene_text2[53] = SWEET3

$cutscene_file2[54] = DATE1B //DATE 1B BURGER //Offset: X367.891 Y-67.591 Z1002.516
cut_visible_area2[54] = 10
$cutscene_name2[54] = CUT_D4
$cutscene_text2[54] = SWEET3

$cutscene_file2[55] = DATE2A //DATE 2A CHICKEN //Offset: X374.478 Y-8.415 Z1002.86
cut_visible_area2[55] = 9
$cutscene_name2[55] = CUT_D5
$cutscene_text2[55] = SWEET3

$cutscene_file2[56] = DATE2B //DATE 2B CHICKEN //Offset: X374.478 Y-8.415 Z1002.86
cut_visible_area2[56] = 9
$cutscene_name2[56] = CUT_D6
$cutscene_text2[56] = SWEET3

$cutscene_file2[57] = DATE3A //DATE 3A DINER BIG //Offset: X449.41 Y-86.83 Z1000.53
cut_visible_area2[57] = 4
$cutscene_name2[57] = CUT_D7
$cutscene_text2[57] = SWEET3

$cutscene_file2[58] = DATE3B //DATE 3B DINER BIG //Offset: X449.41 Y-86.83 Z1000.53
cut_visible_area2[58] = 4
$cutscene_name2[58] = CUT_D8
$cutscene_text2[58] = SWEET3

$cutscene_file2[59] = DATE4A //DATE 4A DINER SMALL //Offset: X449.41 Y-108.24 Z1000.528
cut_visible_area2[59] = 5
$cutscene_name2[59] = CUT_D9
$cutscene_text2[59] = SWEET3

$cutscene_file2[60] = DATE4B //DATE 4B DINER SMALL //Offset: X449.41 Y-108.24 Z1000.528
cut_visible_area2[60] = 5
$cutscene_name2[60] = CUT_D10
$cutscene_text2[60] = SWEET3

$cutscene_file2[61] = DATE5A //DATE 5A REST BOOTH //Offset: X441.871 Y-60.839 Z1000.675
cut_visible_area2[61] = 6
$cutscene_name2[61] = CUT_D11
$cutscene_text2[61] = SWEET3

$cutscene_file2[62] = DATE5B //DATE 5B REST BOOTH //Offset: X441.871 Y-60.839 Z1000.675
cut_visible_area2[62] = 6
$cutscene_name2[62] = CUT_D12
$cutscene_text2[62] = SWEET3

$cutscene_file2[63] = DATE5A //DATE 5A REST TABLE //Offset: x445.381 y-14.147 z1001.731
cut_visible_area2[63] = 1
$cutscene_name2[63] = CUT_D13
$cutscene_text2[63] = SWEET3

$cutscene_file2[64] = DATE5B //DATE 5B REST TABLE //Offset: x445.381 y-14.147 z1001.731
cut_visible_area2[64] = 1
$cutscene_name2[64] = CUT_D14
$cutscene_text2[64] = SWEET3

$cutscene_file2[65] = DATE6A //DATE 6A BAR1	//Offset: X498.536 Y-18.2  Z1000.651
cut_visible_area2[65] = 17
$cutscene_name2[65] = CUT_D15
$cutscene_text2[65] = SWEET3

$cutscene_file2[66] = DATE6B //DATE 6A BAR1	//Offset: X498.536 Y-18.2  Z1000.651
cut_visible_area2[66] = 17
$cutscene_name2[66] = CUT_D16
$cutscene_text2[66] = SWEET3

$cutscene_file2[67] = DATE6A //DATE 6A BAR2	//Offset: X490.718 Y-79.168 Z998.76
cut_visible_area2[67] = 11
$cutscene_name2[67] = CUT_D17
$cutscene_text2[67] = SWEET3

$cutscene_file2[68] = DATE6B //DATE 6A BAR2	//Offset: X490.718 Y-79.168 Z998.76
cut_visible_area2[68] = 11
$cutscene_name2[68] = CUT_D18
$cutscene_text2[68] = SWEET3

$cutscene_file2[69] = RIOT_1B 
cut_visible_area2[69] = 5
$cutscene_name2[69] = CUT133
$cutscene_text2[69] = RIOT1

$cutscene_file2[70] = Riot4e2 
cut_visible_area2[70] = 0
$cutscene_name2[70] = CUT141
$cutscene_text2[70] = RIOT4

$cutscene_file2[71] = CAS6b_2 
cut_visible_area2[71] = 1
$cutscene_name2[71] = CUT107
$cutscene_text2[71] = CASINO6

$cutscene_file2[72] = Cas_9a2 
cut_visible_area2[72] = 2
$cutscene_name2[72] = CUT110
$cutscene_text2[72] = CASINO9

$cutscene_file2[73] = CRASV2B 
cut_visible_area2[73] = 0
$cutscene_name2[73] = CUT120
$cutscene_text2[73] = VCR2

$cutscene_file2[74] = CUTTEST 
cut_visible_area2[74] = 0
$cutscene_name2[74] = CUTTEST
$cutscene_text2[74] = SWEET3


// ******************PAGE3*********************

$cutscene_file3[0] = SC_02 //-outside, projects
cut_visible_area3[0] = 0
$cutscene_name3[0] = SC_02

$cutscene_file3[1] = SC_03 //-outside, grove street alleyway
cut_visible_area3[1] = 0
$cutscene_name3[1] = SC_03

$cutscene_file3[2] = SC_04 //-ryders house, INTRO2a
cut_visible_area3[2] = 2
$cutscene_name3[2] = SC_04

$cutscene_file3[3] = SC_05 //-outside, down an alley in SF
cut_visible_area3[3] = 0
$cutscene_name3[3] = SC_05

$cutscene_file3[4] = SC_06 //-outside, grove street alleyway
cut_visible_area3[4] = 0
$cutscene_name3[4] = SC_06

$cutscene_file3[5] = SC_07 //-outside, in liberty city
cut_visible_area3[5] = 0
$cutscene_name3[5] = SC_07

$cutscene_file3[6] = SC_08 //-outside in liberty city
cut_visible_area3[6] = 0
$cutscene_name3[6] = SC_08 

$cutscene_file3[7] = SC_09 //-in salvatores office (not built) 
cut_visible_area3[7] = 0
$cutscene_name3[7] = SC_09

$cutscene_file3[8] = SC_10 //-outside a rehab clinic 
cut_visible_area3[8] = 0
$cutscene_name3[8] = SC_10

$cutscene_file3[9] = SC_11 //-outside the donut diner 
cut_visible_area3[9] = 0
$cutscene_name3[9] = SC_11

$cutscene_file3[10] = SC_12 //-outside down an alleyway in liberty city 
cut_visible_area3[10] = 0
$cutscene_name3[10] = SC_12

$cutscene_file3[11] = SC_13 //-outside, by a phone in LS 
cut_visible_area3[11] = 0
$cutscene_name3[11] = SC_13

$cutscene_file3[12] = SC_13V //-outside on a wall or bench
cut_visible_area3[12] = 0
$cutscene_name3[12] = SC_13V

$cutscene_file3[13] = SC_14 //- inside UK recording studio, not built yet
cut_visible_area3[13] = 5
$cutscene_name3[13] = SC_14

$cutscene_file3[14] = SC_15 //-outside by a hole, same as the hernandez burial
cut_visible_area3[14] = 0
$cutscene_name3[14] = SC_15

$cutscene_file3[15] = SC_16 //- sals office in liberty
cut_visible_area3[15] = 0
$cutscene_name3[15] = SC_16

$cutscene_file3[16] = SC_18 //-in the airport
cut_visible_area3[16] = 0
$cutscene_name3[16] = SC_18

$cutscene_file3[17] = SC_19 //-on the streets of LS, driving (straight)
cut_visible_area3[17] = 0
$cutscene_name3[17] = SC_19

$cutscene_file3[18] = SC_20 //-Sals office, in vegas
cut_visible_area3[18] = 2
$cutscene_name3[18] = SC_20

$cutscene_file3[19] = SC_21 //-outside moms house, LS
cut_visible_area3[19] = 0
$cutscene_name3[19] = SC_21

$cutscene_file3[20] = SC_21B //-outside moms house, LS
cut_visible_area3[20] = 0
$cutscene_name3[20] = SC_21B

$cutscene_file3[21] = SC_22 //-outside in liberty city (static)
cut_visible_area3[21] = 0
$cutscene_name3[21] = SC_22

$cutscene_file3[22] = SC_23 //-outside, not sure where
cut_visible_area3[22] = 0
$cutscene_name3[22] = SC_23





second_playercreated = 0
relative_button_press = 0
initial_create_car = 0
counter_create_car = landstal 
counter_create_pickup = gun_dildo1 
button_pressed_warp = 0 
button_pressed_main = 0
debug_cutscene_number = 0 
button_pressed_cutscene_main = 0
add_just_the_once_though = 0
button_pressed_warp_odd = 0
test_heli1_created = 0
initial_pickup = 0
print_stuff_button = 0
print_stuff_counter = 0
carcolour_flag1 = 0
carcolour_flag2 = 0
carcolour_counter1 = 0
carcolour_counter2 = 0
carcolour_counter3 = 0
carcolour_counter4 = 0
is_stunt_jump_debug = 0
is_rightshock_pressed = 0
widscreen_flag = 0
cheat_mode_on = 0
weather_crap = 0
disable_debug = 0

introY_d = introY + 6.0
sweetX_d = sweetX - 5.0
ryderY_d = ryderY + 5.0
smokeX_d = smokeX + 5.0
strapX_d = strapX - 5.0
strap2X_d = strap2X - 5.0
crashY_d = crashY + 5.0
cesarX_d = cesarX - 5.0

TEXT_POS_Y = 20.0

truthX_d = truthX - 5.0
bcrashX_d = bcrashX - 5.0
bcesarX_d = bcesarX - 5.0
catx_d = catX[0] - 0.5
wuziX_d = wuziX + 5.0
syndX_d = syndX	+ 5.0
stealX_d = stealX + 5.0 
scrashX_d = scrashX + 5.0
garageX_d = garageX + 5.0
traceX_d[0] = traceX[0] + 5.0
traceX_d[1] = traceX[1] + 5.0
traceX_d[2] = traceX[2] + 5.0
traceX_d[3] = traceX[3] + 5.0

desertX_d = desertX - 5.0
desert2X_d = desert2X - 5.0
pilotX_d = pilotX - 5.0
casinoX_d = casinoX + 5.0
TheheistX_d = TheheistX + 5.0
heistX_d = heistX - 5.0
vcrashX_d = vcrashX + 5.0
docX_d = docX - 5.0
 
//mansionX_d = mansionX - 3.5
mansionX_d = mansionY + 3.5

driving_schoolX_d =	driving_schoolX - 5.0
zeroX_d = zeroX - 5.0
  
basketballx_d = basketballx - 5.0
limox_d = limox - 5.0
directorx_d = directorx - 5.0
valetx_d = valetx - 5.0

corona_x = 10.0
corona_y = 10.0
corona_z = 10.0

pimpY_d	= pimpY - 5.0
hitchX_d = hitchX + 7.0

//bulldozerY_d = bulldozerY - 8.0 
//dumperY_d = dumperY	- 8.0
 
goto_thereX = sweetX_d
goto_thereY = sweetY
goto_thereZ	= sweetZ

SCRIPT_NAME debug

debug_active = 1

DEBUG_OFF
//LOAD_ALL_PATH_NODES_FOR_DEBUG

SET_DEATHARREST_STATE OFF

//IF IS_PLAYER_PLAYING player1
	//SET_PLAYER_NEVER_GETS_TIRED Player1 TRUE
//ENDIF

VAR_INT coord_index
coord_index = 0

mission_start_debug:

WAIT 0

IF disable_debug = 1  // used to disable entire debug script - used in 2 player pool
	GOTO mission_start_debug
ENDIF


GET_GAME_TIMER debug_game_timer

GOTO skip_this_debug_bit
	CREATE_PICKUP_WITH_AMMO counter_create_pickup PICKUP_ONCE 2000 x_float_m y_float_m z_float_m magic_pickup
	CREATE_CAR counter_create_car x_float_m y_float_m z_float_m magic_car
	CREATE_CAR counter_create_car x_float_m y_float_m z_float_m record_car1
skip_this_debug_bit:

IF IS_PLAYER_PLAYING player1 //ENDIF AT THE BOTTOM OF THE SCRIPT

// TURN BEAT DISPLAY ON/OFF - For audio guys //////////////////////////
	IF flag_player_on_mission = 0
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_1
			IF beat_display_script_started = 0
				IF IS_PLAYER_PLAYING player1
					IF NOT IS_CHAR_IN_ANY_CAR scplayer
						REQUEST_MODEL SAVANNA 
						WHILE NOT HAS_MODEL_LOADED SAVANNA
							WAIT 0
						ENDWHILE
						VAR_INT beat_display_car
						CREATE_CAR SAVANNA 1795.1271 -1903.7428 12.4004 beat_display_car
						IF IS_PLAYER_PLAYING player1
							WARP_CHAR_INTO_CAR scplayer beat_display_car
						ENDIF
						MARK_CAR_AS_NO_LONGER_NEEDED beat_display_car
					ELSE
						SET_CHAR_COORDINATES scplayer 1795.1271 -1903.7428 12.4004
					ENDIF
					WAIT 1000
					START_NEW_SCRIPT beat_display
				ENDIF
			ENDIF
		ENDIF
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_2
			IF beat_display_script_started = 1
				bd_terminate_script = 1
			ENDIF
		ENDIF
	ENDIF


	// Create second Player	////////////////////////////////////////
	IF flag_player_on_mission = 0
		IF second_playercreated = 0
			IF IS_BUTTON_PRESSED PAD2 RIGHTSHOULDER2
			AND IS_BUTTON_PRESSED PAD2 SQUARE	
			  
				//SWITCH_WIDESCREEN ON
			  	second_playercreated = 1

				GET_CHAR_COORDINATES scplayer second_playerX second_playerY second_playerZ
				second_playerX = second_playerX + 2.0
				CREATE_PLAYER 1 second_playerX second_playerY second_playerZ player2


				WAIT 500
			ENDIF
		ENDIF
			
		IF second_playercreated = 1
			IF IS_BUTTON_PRESSED PAD2 RIGHTSHOULDER2
			AND IS_BUTTON_PRESSED PAD2 SQUARE
			    
				DELETE_PLAYER player2
				second_playercreated = 0
				WAIT 500
			ENDIF
		ENDIF
	ENDIF

// HEALTH CHEAT FOR KEYBOARD IF IT IS IN LEVEL DESIGN MODE /////////////////////////////////////////////
	if is_ps2_keyboard_key_pressed ps2_key_h
		set_char_health scplayer 100
		add_armour_to_char scplayer 100
		if is_char_in_any_car scplayer
			var_int temporary_car temporary_car_model
			STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer temporary_car
			set_car_health temporary_car 1000
			get_car_model temporary_car temporary_car_model
			if not is_char_on_any_bike scplayer
				if not is_this_model_a_boat temporary_car_model
					if not is_this_model_a_heli temporary_car_model
						if not is_this_model_a_plane temporary_car_model
							fix_car_tyre temporary_car front_left_wheel //front_left_wheel//rear_right_wheel
							fix_car_tyre temporary_car front_right_wheel //front_left_wheel//rear_right_wheel
							fix_car_tyre temporary_car rear_left_wheel //front_left_wheel//rear_right_wheel
							fix_car_tyre temporary_car rear_right_wheel //front_left_wheel//rear_right_wheel
						endif
					endif
				endif
			endif
		endif
	endif

// TURN CHEAT MODE ON/OFF /////////////////////////////////////////////
	
	IF NOT IS_2PLAYER_GAME_GOING_ON

		IF IS_BUTTON_PRESSED PAD2 TRIANGLE
		AND cheat_mode_on = 0
			WHILE IS_BUTTON_PRESSED PAD2 TRIANGLE
				WAIT 0

			ENDWHILE 
			PRINT_NOW CHEATON 2000 1//CHEAT MODE ON
			DEBUG_ON
			cheat_mode_on = 1
		ENDIF

		IF IS_BUTTON_PRESSED PAD2 TRIANGLE
		AND cheat_mode_on = 1
			WHILE IS_BUTTON_PRESSED PAD2 TRIANGLE
					WAIT 0

			ENDWHILE
			PRINT_NOW CHEATOF 2000 1//CHEAT MODE OFF
			DEBUG_OFF
			cheat_mode_on = 0
		ENDIF



	/// TURN PEDS AND CARS ON AND OFF /////////////////////////////////////
			IF IS_BUTTON_PRESSED PAD2 SELECT
			AND repeat_butt_press = 0
			AND no_cars = 0
				SET_CAR_DENSITY_MULTIPLIER 0.0
				SET_PED_DENSITY_MULTIPLIER 0.0
				PRINT_NOW CARSOFF 2000 1
				DO_FADE 0 FADE_IN
				SWITCH_WIDESCREEN OFF
				no_cars = 1
				repeat_butt_press = 1
			ENDIF

			IF IS_BUTTON_PRESSED PAD2 SELECT
			AND repeat_butt_press = 0
			AND no_cars = 1
				SET_CAR_DENSITY_MULTIPLIER 1.0
				SET_PED_DENSITY_MULTIPLIER 1.0
				PRINT_NOW CARS_ON 2000 1
				DO_FADE 0 FADE_IN
				SWITCH_WIDESCREEN OFF
				no_cars = 0
				repeat_butt_press = 1
			ENDIF

			IF NOT IS_BUTTON_PRESSED PAD2 SELECT
				IF repeat_butt_press = 1
					repeat_butt_press = 0
				ENDIF
			ENDIF

	// CREATE A PICKUP ////////////////////////////////////////////////////
		IF NOT IS_BUTTON_PRESSED PAD2 RIGHTSHOULDER1

			IF IS_BUTTON_PRESSED PAD2 DPADDOWN
			OR IS_BUTTON_PRESSED PAD2 DPADUP
				IF IS_PLAYER_PLAYING player1
					GET_CHAR_COORDINATES scplayer x_float_m y_float_m z_float_m
					GET_CHAR_HEADING scplayer player_heading_debug
				ENDIF
				IF player_heading_debug < 45.0
				AND player_heading_debug > 0.0
					y_float_m += 5.0
					debug_car_heading = 90.0
				ENDIF
				IF player_heading_debug < 360.0
				AND player_heading_debug > 315.0
					y_float_m += 5.0
					debug_car_heading = 90.0
				ENDIF
				IF player_heading_debug < 135.0
				AND player_heading_debug > 45.0
					x_float_m -= 5.0
					debug_car_heading = 180.0
				ENDIF
				IF player_heading_debug < 225.0
				AND player_heading_debug > 135.0
					y_float_m -= 5.0
					debug_car_heading = 270.0
				ENDIF
				IF player_heading_debug < 315.0
				AND player_heading_debug > 225.0
					x_float_m += 5.0
					debug_car_heading = 0.0
				ENDIF
				z_float_m = z_float_m + 1.0
				GET_GROUND_Z_FOR_3D_COORD x_float_m y_float_m z_float_m	z_float_m

				IF NOT IS_PLAYER_PLAYING player1
					GOTO mission_start_debug
				ENDIF
				
				next_pickup:

				WAIT 0
		 			
				IF IS_BUTTON_PRESSED PAD2 DPADUP
					WHILE IS_BUTTON_PRESSED PAD2 DPADUP
							WAIT 0
					ENDWHILE							  
					++ counter_create_pickup

					IF counter_create_pickup = missile
						counter_create_pickup = colt45
					ENDIF
					
					IF counter_create_pickup = 329
						counter_create_pickup = cellphone
					ENDIF

					IF counter_create_pickup = 340
						counter_create_pickup = chnsaw
					ENDIF

					IF counter_create_pickup = 354
						counter_create_pickup = ak47
					ENDIF

					IF counter_create_pickup = 332
						counter_create_pickup = golfclub
					ENDIF

					IF counter_create_pickup > gun_para
						counter_create_pickup = gun_dildo1
					ENDIF

				ENDIF

				IF IS_BUTTON_PRESSED PAD2 DPADDOWN
					WHILE IS_BUTTON_PRESSED PAD2 DPADDOWN
							WAIT 0
					ENDWHILE
					-- counter_create_pickup

					IF counter_create_pickup < gun_dildo1 
						counter_create_pickup = gun_para
					ENDIF

					IF counter_create_pickup = 329
						counter_create_pickup = gun_boxbig
					ENDIF
					
					IF counter_create_pickup = 340
						counter_create_pickup = katana
					ENDIF

					IF counter_create_pickup = 354
						counter_create_pickup = mp5lng
					ENDIF
					
					IF counter_create_pickup = 332
						counter_create_pickup = brassknuckle
					ENDIF

					IF counter_create_pickup = missile
						counter_create_pickup = molotov
					ENDIF

					ENDIF
				
					REMOVE_PICKUP magic_pickup
					MARK_MODEL_AS_NO_LONGER_NEEDED counter_create_pickup

					IF NOT IS_MODEL_AVAILABLE counter_create_pickup
						GOTO next_pickup
					ENDIF

					REQUEST_MODEL counter_create_pickup
					PRINT_NOW LOADCAR 250 1 //"Loading vehicle, press pad2 leftshoulder1 to cancel"
					LOAD_ALL_MODELS_NOW
								
					WHILE NOT HAS_MODEL_LOADED counter_create_pickup
						WAIT 0
						
						PRINT_NOW LOADCAR 80 1 //"Loading vehicle, press pad2 leftshoulder1 to cancel"
						
						IF IS_BUTTON_PRESSED PAD2 LEFTSHOULDER1
							GOTO next_pickup
						ENDIF
				
					ENDWHILE

					z_float_m = z_float_m + 0.8
					CREATE_PICKUP_WITH_AMMO counter_create_pickup PICKUP_ONCE 2000 x_float_m y_float_m z_float_m magic_pickup
					CLEAR_THIS_BIG_PRINT NUMBER
					PRINT_WITH_NUMBER_BIG NUMBER counter_create_pickup 500 4
					MARK_MODEL_AS_NO_LONGER_NEEDED counter_create_pickup

				ENDIF

			ENDIF
			
		ENDIF
	ENDIF //2 PLAYER FLAG

	IF NOT IS_2PLAYER_GAME_GOING_ON
	// CREATE A CAR //////////////////////////////////////////

		next_carzzz:

		IF NOT IS_DEBUG_CAMERA_ON
			IF IS_BUTTON_PRESSED PAD2 RIGHTSTICKX
			OR IS_BUTTON_PRESSED PAD2 RIGHTSTICKY

				WAIT 40

				GET_POSITION_OF_ANALOGUE_STICKS PAD2 LStickX LStickY RStickX RStickY

				IF RStickX > 100
					IF initial_create_car = 1
						++ counter_create_car
					ENDIF

					IF initial_create_car = 0
						counter_create_car = landstal
						initial_create_car = 1
					ENDIF

					initial_create_car = 1

					IF counter_create_car > 611
						counter_create_car = landstal
					ENDIF

				ENDIF
				
				IF RStickX < -100
					IF initial_create_car = 1
						-- counter_create_car
					ENDIF

					IF initial_create_car = 0
						counter_create_car = 611
						initial_create_car = 1
					ENDIF

					initial_create_car = 1

					IF counter_create_car < landstal
						counter_create_car = 611
					ENDIF
					
				ENDIF
				
				IF RStickY < -100
					IF initial_create_car = 1
						counter_create_car = counter_create_car + 10
					ENDIF

					IF initial_create_car = 0
						counter_create_car = landstal
						initial_create_car = 1
					ENDIF

					initial_create_car = 1

					IF counter_create_car > 611
						counter_create_car = landstal
					ENDIF
					
					IF counter_create_car = infernus
						counter_create_car = voodoo	 
					ENDIF

 				ENDIF

				IF RStickY > 100
					IF initial_create_car = 1
						counter_create_car = counter_create_car - 10
					ENDIF

					IF initial_create_car = 0
						counter_create_car = 611
						initial_create_car = 1
					ENDIF

					initial_create_car = 1

					IF counter_create_car < landstal
						counter_create_car = 611
					ENDIF
					

					IF counter_create_car = infernus
						counter_create_car = manana	 
					ENDIF



				ENDIF
			
				IF NOT IS_MODEL_AVAILABLE counter_create_car
					CLEAR_THIS_BIG_PRINT NUMBER
					PRINT_WITH_NUMBER_BIG NUMBER counter_create_car 500 4
					GOTO next_carzzz
				ENDIF

				IF IS_PLAYER_PLAYING player1
					GET_CHAR_COORDINATES scplayer x_float_m y_float_m z_float_m
					GET_CHAR_HEADING scplayer player_heading_debug
				ENDIF
				IF player_heading_debug < 45.0
				AND player_heading_debug > 0.0
					y_float_m += 5.0
					debug_car_heading = 90.0
				ENDIF
				IF player_heading_debug < 360.0
				AND player_heading_debug > 315.0
					y_float_m += 5.0
					debug_car_heading = 90.0
				ENDIF
				IF player_heading_debug < 135.0
				AND player_heading_debug > 45.0
					x_float_m -= 5.0
					debug_car_heading = 180.0
				ENDIF
				IF player_heading_debug < 225.0
				AND player_heading_debug > 135.0
					y_float_m -= 5.0
					debug_car_heading = 270.0
				ENDIF
				IF player_heading_debug < 315.0
				AND player_heading_debug > 225.0
					x_float_m += 5.0
					debug_car_heading = 0.0
				ENDIF
				z_float_m = z_float_m + 0.6
				GET_GROUND_Z_FOR_3D_COORD x_float_m y_float_m z_float_m	z_float_m

				IF NOT IS_PLAYER_PLAYING player1
					
					GOTO mission_start_debug
				ENDIF
				
				IF RStickX > 100
				OR RStickX < -100
				OR RStickY > 100
				OR RStickY < -100	
					
					IF IS_PLAYER_PLAYING player1 
						IF NOT IS_CAR_DEAD magic_car
							IF NOT IS_CHAR_IN_CAR scplayer magic_car
								DELETE_CAR magic_car
							ENDIF
						ENDIF
					ENDIF
					MARK_CAR_AS_NO_LONGER_NEEDED magic_car
					MARK_MODEL_AS_NO_LONGER_NEEDED counter_create_car


					REQUEST_MODEL counter_create_car
					PRINT_NOW LOADCAR 250 1 //"Loading vehicle, press pad2 leftshoulder1 to cancel"
					LOAD_ALL_MODELS_NOW
	 
					WHILE NOT HAS_MODEL_LOADED counter_create_car
						WAIT 0
						
						PRINT_NOW LOADCAR 80 1 //"Loading vehicle, press pad2 leftshoulder1 to cancel"
						
						IF IS_BUTTON_PRESSED PAD2 LEFTSHOULDER1
							GOTO next_carzzz
						ENDIF
				
					ENDWHILE

					CREATE_CAR counter_create_car x_float_m y_float_m z_float_m magic_car
					CLEAR_THIS_BIG_PRINT NUMBER
					PRINT_WITH_NUMBER_BIG NUMBER counter_create_car 500 4
					SET_CAR_HEADING	magic_car debug_car_heading
					//GET_CAR_COLOURS magic_car carcolour_counter1 carcolour_counter2
					GET_AREA_VISIBLE debug_visible_area
					SET_VEHICLE_AREA_VISIBLE magic_car debug_visible_area
					LOCK_CAR_DOORS magic_car CARLOCK_UNLOCKED

					MARK_MODEL_AS_NO_LONGER_NEEDED counter_create_car
					MARK_CAR_AS_NO_LONGER_NEEDED magic_car
				ENDIF

				WAIT 0

			ENDIF
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////			 CREATE A CHAR 			//////////////////////////////////////////////////////////////////
	//VAR_INT magic_ped
	//VAR_INT initial_create_ped counter_create_ped
	//VAR_FLOAT debug_ped_heading
	//
	//GOTO skip_this_ped_creater
	//	CREATE_CHAR PEDTYPE_CIVMALE counter_create_car x_float_m y_float_m z_float_m magic_ped
	//skip_this_ped_creater:
	//
	//		next_charzzz:
	//
	//		IF NOT IS_DEBUG_CAMERA_ON
	//			IF IS_BUTTON_PRESSED PAD2 LEFTSTICKX
	//			OR IS_BUTTON_PRESSED PAD2 LEFTSTICKY
	//				
	//				WAIT 40
	//
	//				GET_POSITION_OF_ANALOGUE_STICKS PAD2 LStickX LStickY RStickX RStickY
	//
	//				IF LStickX > 100
	//					IF initial_create_ped = 1
	//						++ counter_create_ped
	//					ENDIF
	//
	//					IF initial_create_ped = 0
	//						counter_create_ped = 0
	//						initial_create_ped = 1
	//					ENDIF
	//
	//					initial_create_ped = 1
	//
	//					IF counter_create_ped > 173
	//						counter_create_ped = 0
	//					ENDIF
	//
	//					// buggy peds
	//					IF counter_create_ped < 9
	//						counter_create_ped = 9
	//					ENDIF
	//					IF counter_create_ped = 29
	//						counter_create_ped = 30	 
	//					ENDIF
	//					IF counter_create_ped = 32
	//						counter_create_ped = 33	 
	//					ENDIF
	//					IF counter_create_ped = 36
	//						counter_create_ped = 37	 
	//					ENDIF
	//					IF counter_create_ped = 38
	//						counter_create_ped = 39	 
	//					ENDIF
	//					IF counter_create_ped = 39
	//						counter_create_ped = 40	 
	//					ENDIF
	//					IF counter_create_ped = 52
	//						counter_create_ped = 53	 
	//					ENDIF
	//					IF counter_create_ped = 56
	//						counter_create_ped = 57	 
	//					ENDIF
	//					IF counter_create_ped = 59
	//						counter_create_ped = 60	 
	//					ENDIF
	//					IF counter_create_ped = 60
	//						counter_create_ped = 61	 
	//					ENDIF
	//					IF counter_create_ped = 71
	//						counter_create_ped = 72	 
	//					ENDIF
	//					IF counter_create_ped = 72
	//						counter_create_ped = 73	 
	//					ENDIF
	//					IF counter_create_ped = 82
	//						counter_create_ped = 83	 
	//					ENDIF
	//					IF counter_create_ped = 83
	//						counter_create_ped = 84	 
	//					ENDIF
	//					IF counter_create_ped = 84
	//						counter_create_ped = 85	 
	//					ENDIF
	//
	//				ENDIF
	//				
	//				IF LStickX < -100
	//					IF initial_create_ped = 1
	//						-- counter_create_ped
	//					ENDIF
	//
	//					IF initial_create_ped = 0
	//						counter_create_ped = 173
	//						initial_create_ped = 1
	//					ENDIF
	//
	//					initial_create_ped = 1
	//
	//					IF counter_create_ped < 0
	//						counter_create_ped = 173
	//					ENDIF
	//
	//					// buggy peds 
	//					IF counter_create_ped = 84
	//						counter_create_ped = 83	 
	//					ENDIF
	//					IF counter_create_ped = 83
	//						counter_create_ped = 82	 
	//					ENDIF
	//					IF counter_create_ped = 82
	//						counter_create_ped = 81	 
	//					ENDIF
	//					IF counter_create_ped = 72
	//						counter_create_ped = 71	 
	//					ENDIF
	//					IF counter_create_ped = 71
	//						counter_create_ped = 70	 
	//					ENDIF
	//					IF counter_create_ped = 60
	//						counter_create_ped = 59	 
	//					ENDIF
	//					IF counter_create_ped = 59
	//						counter_create_ped = 58	 
	//					ENDIF
	//					IF counter_create_ped = 56
	//						counter_create_ped = 55	 
	//					ENDIF
	//					IF counter_create_ped = 52
	//						counter_create_ped = 51	 
	//					ENDIF
	//					IF counter_create_ped = 39
	//						counter_create_ped = 38	 
	//					ENDIF
	//					IF counter_create_ped = 38
	//						counter_create_ped = 37	 
	//					ENDIF
	//					IF counter_create_ped = 36
	//						counter_create_ped = 35	 
	//					ENDIF
	//					IF counter_create_ped = 32
	//						counter_create_ped = 31	 
	//					ENDIF
	//					IF counter_create_ped = 29
	//						counter_create_ped = 28	 
	//					ENDIF
	//					IF counter_create_ped < 9
	//						counter_create_ped = 173
	//					ENDIF
	//
	//
	//
	//				ENDIF
	//						
	//				IF NOT IS_MODEL_AVAILABLE counter_create_ped
	//					CLEAR_THIS_BIG_PRINT NUMBER
	//					PRINT_WITH_NUMBER_BIG NUMBER counter_create_ped 500 4
	//					GOTO next_charzzz
	//				ENDIF
	//
	//				IF IS_PLAYER_PLAYING player1
	//					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -1.0 2.0 0.0 x_float_m y_float_m z_float_m
	//					GET_CHAR_HEADING scplayer player_heading_debug
	//				ENDIF
	//
	//				z_float_m = z_float_m + 0.6
	//				GET_GROUND_Z_FOR_3D_COORD x_float_m y_float_m z_float_m	z_float_m
	//
	//				IF NOT IS_PLAYER_PLAYING player1
	//					GOTO mission_start_debug
	//				ENDIF
	//				
	//				IF LStickX > 100
	//				OR LStickX < -100
	//					
	//					IF IS_PLAYER_PLAYING player1 
	//						IF NOT IS_CHAR_DEAD magic_ped
	//							DELETE_CHAR magic_ped
	//						ENDIF
	//					ENDIF
	//
	//					MARK_CHAR_AS_NO_LONGER_NEEDED magic_ped
	//					MARK_MODEL_AS_NO_LONGER_NEEDED counter_create_ped
	//
	//					REQUEST_MODEL counter_create_ped
	//					PRINT_NOW LOADCAR 250 1 //"Loading vehicle, press pad2 leftshoulder1 to cancel"
	//					LOAD_ALL_MODELS_NOW
	//	 
	//					WHILE NOT HAS_MODEL_LOADED counter_create_ped
	//						WAIT 0
	//						
	//						PRINT_NOW LOADCAR 80 1 //"Loading vehicle, press pad2 leftshoulder1 to cancel"
	//						
	//						IF IS_BUTTON_PRESSED PAD2 LEFTSHOULDER1
	//							GOTO next_charzzz
	//						ENDIF
	//				
	//					ENDWHILE
	//
	//					CREATE_CHAR PEDTYPE_CIVMALE counter_create_ped x_float_m y_float_m z_float_m magic_ped
	//					TASK_STAY_IN_SAME_PLACE magic_ped TRUE
	//					CLEAR_THIS_BIG_PRINT NUMBER
	//					PRINT_WITH_NUMBER_BIG NUMBER counter_create_ped 500 4
	//					debug_ped_heading = player_heading_debug - 120.0
	//					SET_CHAR_HEADING magic_ped debug_ped_heading
	//
	//					MARK_MODEL_AS_NO_LONGER_NEEDED counter_create_ped
	//					//MARK_CHAR_AS_NO_LONGER_NEEDED magic_ped
	//				ENDIF
	//
	//								
	//				WAIT 0
	//
	//			ENDIF
	//			
	//			IF NOT IS_CHAR_DEAD magic_ped
	//				IF NOT IS_CHAR_DEAD scplayer
	//					IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D magic_ped scplayer 10.0 10.0 FALSE
	//						MARK_CHAR_AS_NO_LONGER_NEEDED magic_ped	
	//						MARK_MODEL_AS_NO_LONGER_NEEDED counter_create_ped
	//					ENDIF
	//				ENDIF
	//			ENDIF
	//
	//		ENDIF

	// CHANGE PLAYERS CAR COLOURS //////////////////////////////////////////

			IF NOT IS_BUTTON_PRESSED PAD2 CROSS
				IF IS_BUTTON_PRESSED PAD2 LEFTSTICKX
				OR IS_BUTTON_PRESSED PAD2 LEFTSTICKY

					GET_POSITION_OF_ANALOGUE_STICKS PAD2 LStickX LStickY RStickX RStickY

					IF NOT IS_CAR_DEAD magic_car
						IF LStickX > 50
							carcolour_counter1 ++
							IF carcolour_counter1 >	126
								carcolour_counter1 = 0
							ENDIF
						ENDIF

						IF LStickX < -50
							carcolour_counter1 --
							IF carcolour_counter1 <	0
								carcolour_counter1 = 126
							ENDIF
						ENDIF

						IF LStickY < -50
							carcolour_counter2 ++
							IF carcolour_counter2 >	126
								carcolour_counter2 = 0
							ENDIF
						ENDIF

						IF LStickY > 50
							carcolour_counter2 --
							IF carcolour_counter2 <	0
								carcolour_counter2 = 126
							ENDIF
						ENDIF
						IF NOT IS_CAR_DEAD magic_car
							CHANGE_CAR_COLOUR magic_car carcolour_counter1 carcolour_counter2
						ENDIF
						PRINT_WITH_2_NUMBERS_NOW ( COLOURS ) carcolour_counter1 carcolour_counter2 1000 1
						WAIT 150

					ENDIF

				ENDIF
			ENDIF

			IF IS_BUTTON_PRESSED PAD2 CROSS
				IF IS_BUTTON_PRESSED PAD2 LEFTSTICKX
				OR IS_BUTTON_PRESSED PAD2 LEFTSTICKY

					GET_POSITION_OF_ANALOGUE_STICKS PAD2 LStickX LStickY RStickX RStickY

					IF NOT IS_CAR_DEAD magic_car
						IF LStickX > 50
							carcolour_counter3 ++
							IF carcolour_counter3 >	126
								carcolour_counter3 = 0
							ENDIF
						ENDIF

						IF LStickX < -50
							carcolour_counter3 --
							IF carcolour_counter3 <	0
								carcolour_counter3 = 126
							ENDIF
						ENDIF

						IF LStickY < -50
							carcolour_counter4 ++
							IF carcolour_counter4 >	126
								carcolour_counter4 = 0
							ENDIF
						ENDIF

						IF LStickY > 50
							carcolour_counter4 --
							IF carcolour_counter4 <	0
								carcolour_counter4 = 126
							ENDIF
						ENDIF
						IF NOT IS_CAR_DEAD magic_car
							//CHANGE_CAR_COLOUR magic_car carcolour_counter1 carcolour_counter2
							SET_EXTRA_CAR_COLOURS magic_car carcolour_counter3 carcolour_counter4
						ENDIF
						PRINT_WITH_2_NUMBERS_NOW ( COLOUR2 ) carcolour_counter3 carcolour_counter4 1000 1
						WAIT 150
					ENDIF
				ENDIF

			ENDIF


		ENDIF //IF NOT IS_DEBUG_CAMERA_ON


	// DISPLAY WIDESCREEN MODE
				IF IS_BUTTON_PRESSED PAD2 RIGHTSHOULDER1
				AND	IS_BUTTON_PRESSED PAD2 CROSS
					IF widscreen_flag = 0
						SWITCH_WIDESCREEN ON
						SET_NEAR_CLIP 0.2
						DO_FADE 0 FADE_IN
						widscreen_flag = 1
					ELSE
						SWITCH_WIDESCREEN OFF
						IF IS_PLAYER_PLAYING player1
							SET_PLAYER_CONTROL player1 ON
							RESTORE_CAMERA
							DO_FADE 0 FADE_IN
							//SET_NEAR_CLIP 0.9
						ENDIF
						widscreen_flag = 0
					ENDIF
					WHILE IS_BUTTON_PRESSED PAD2 CROSS
						WAIT 0
					ENDWHILE
				ENDIF


	// CAR RECORDING
	{
		//IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_R
		//IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
			//IF IS_BUTTON_PRESSED PAD1 DPADUP
			//OR IS_BUTTON_PRESSED PAD1 DPADDOWN
			IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_CTRL
				
				IF IS_PLAYER_PLAYING player1

					IF recording = 0
						IF IS_CHAR_IN_ANY_CAR scplayer
							STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer record_car1   
							IF NOT IS_CAR_DEAD record_car1
								IF IS_RECORDING_GOING_ON_FOR_CAR record_car1
									STOP_RECORDING_CARS
								ENDIF
								recording = 1
							ENDIF				
						ENDIF	
					ELSE
						IF NOT IS_CAR_DEAD record_car1 
							IF recording = 1 	
								IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_CTRL 
									WHILE IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_CTRL
										WAIT 0
									ENDWHILE 
									PRINT_NOW ( CARREC1 ) 2000 1
									IF NOT IS_CAR_DEAD record_car1
										START_RECORDING_CAR record_car1 0
									ENDIF
									WAIT 0
									TIMERB = 0 
									recording = 2
								ENDIF
							ENDIF

							IF recording = 2 
								IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_CTRL
									WHILE IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_CTRL
										WAIT 0
									ENDWHILE
									CLEAR_PRINTS
									PRINT_NOW ( CARREC2 ) 2000 1
									IF NOT IS_CAR_DEAD record_car1 
										IF IS_RECORDING_GOING_ON_FOR_CAR record_car1
											STOP_RECORDING_CARS
										ENDIF
									ENDIF
								 	recording = 1
								ENDIF
							ENDIF
						ELSE
							IF recording = 1
							OR recording = 2
								//IF NOT IS_CAR_DEAD record_car1
									//IF IS_RECORDING_GOING_ON_FOR_CAR record_car1
										STOP_RECORDING_CARS
									//ENDIF
								//ENDIF
								PRINT_NOW ( CARREC2 ) 2000 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		//ENDIF										

		IF IS_PLAYER_PLAYING player1
			IF recording = 1
			OR recording = 2
				IF IS_CAR_DEAD record_car1
				OR NOT IS_CHAR_IN_ANY_CAR scplayer
					recording = 0
					//IF IS_RECORDING_GOING_ON_FOR_CAR record_car1
						STOP_RECORDING_CARS
					//ENDIF
					PRINT_NOW ( CARREC2 ) 2000 1
				ENDIF
				IF recording = 2 
					IF NOT IS_CAR_DEAD record_car1
						IF NOT IS_RECORDING_GOING_ON_FOR_CAR record_car1
							recording = 0
							PRINT_NOW ( CARREC2 ) 2000 1
						ELSE
							IF TIMERB > 1500
								PRINT_NOW ( rec ) 1000 1
								TIMERB = 0
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

///////////////////////////////////////////////////////////////////////

	ENDIF // 2 PLAYER FLAG

///////////////////////////////////////////////////////////////////////
	IF NOT IS_2PLAYER_GAME_GOING_ON

		IF cheat_mode_on = 1	   // ONLY DO STUFF IF CHEATMODE IS ON ///

	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	///////// WRITE 3D LOCATE COORDINATES TO TEMP_DEBUG.TXT ///////////////
	///////////////////////////////////////////////////////////////////////
	// TURN CHEAT MODE ON AND PRESS PAD1 L1 & L2 AND PAD2 CROSS TO START.//
	// CONTROLS ARE ON PAD1				 								 //
	// LEFT STICK = POSITION			 								 //
	// RIGHT STICK = RESIZE				 								 //
	// HOLD L2 OR R2 = MAKE THE MOVEMENT/RESIZE LESS SENSITIVE.		 	 //
	// HOLD L1 OR R1 = MOVE/RESIZE THE Z AXI							 //
	// R3 = TO OUTPUT COORDS TO TEMP_DEBUG.TXT					 		 //
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
			IF IS_PLAYER_PLAYING player1
				IF is_stunt_jump_debug = 0
					IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
					AND IS_BUTTON_PRESSED PAD1 LEFTSHOULDER2
					AND IS_BUTTON_PRESSED PAD2 CROSS
						is_stunt_jump_debug = 1
						GET_CHAR_COORDINATES scplayer stunt_jump_debug_X stunt_jump_debug_Y stunt_jump_debug_Z
						SET_PLAYER_CONTROL player1 OFF
						corona_x = 1.0
						corona_y = 1.0
						corona_z = 1.0
					ENDIF
				ELSE
					GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
					IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
						VAR_FLOAT LStickX_F LStickY_F
						LStickY_F =# LStickY
						IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER2
							LStickY_F /= 5.0
						ENDIF
						LStickY_F /= 100.0
						LStickY_F *= -1.0
						stunt_jump_debug_Z += LStickY_F
					ELSE
						LStickX_F =# LStickX
						LStickY_F =# LStickY
						IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER2
							LStickX_F /= 5.0
						ENDIF
						IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER2
							LStickY_F /= 5.0
						ENDIF
						LStickX_F /= 100.0
						LStickY_F /= 100.0
						LStickY_F *= -1.0
						stunt_jump_debug_X += LStickX_F
						stunt_jump_debug_Y += LStickY_F
					ENDIF
					
					IF IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER1
						RStickY_F =# RStickY
						IF IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER2
							RStickY_F /= 5.0
						ENDIF
						RStickY_F /= 100.0
						RStickY_F *= -1.0
						corona_z += RStickY_F
					ELSE
						RStickX_F =# RStickX
						RStickY_F =# RStickY
						IF IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER2
							RStickX_F /= 5.0
						ENDIF
						IF IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER2
							RStickY_F /= 5.0
						ENDIF
						RStickX_F /= 100.0
						RStickY_F /= 100.0
						RStickY_F *= -1.0
						corona_x += RStickX_F
						corona_y += RStickY_F
					ENDIF
					IF LOCATE_CHAR_ANY_MEANS_3D scplayer stunt_jump_debug_X stunt_jump_debug_Y stunt_jump_debug_Z corona_x corona_y corona_z 0
					ENDIF

					IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
						IF is_rightshock_pressed = 0
							SAVE_NEWLINE_TO_DEBUG_FILE
							SAVE_FLOAT_TO_DEBUG_FILE stunt_jump_debug_X
							SAVE_NEWLINE_TO_DEBUG_FILE
							SAVE_FLOAT_TO_DEBUG_FILE stunt_jump_debug_Y
							SAVE_NEWLINE_TO_DEBUG_FILE
							SAVE_FLOAT_TO_DEBUG_FILE stunt_jump_debug_Z
							SAVE_NEWLINE_TO_DEBUG_FILE
							SAVE_FLOAT_TO_DEBUG_FILE corona_x 
							SAVE_FLOAT_TO_DEBUG_FILE corona_y 
							SAVE_FLOAT_TO_DEBUG_FILE corona_z
							SAVE_NEWLINE_TO_DEBUG_FILE
							PRINT_NOW TEXXYZ3 800 1 // Writing coordinates to file...
							is_rightshock_pressed = 1
						ENDIF
					ELSE
						IF is_rightshock_pressed = 1
							is_rightshock_pressed = 0
						ENDIF
					ENDIF
					IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
					AND IS_BUTTON_PRESSED PAD1 LEFTSHOULDER2
					AND IS_BUTTON_PRESSED PAD2 CROSS
						SET_PLAYER_CONTROL player1 ON
						is_stunt_jump_debug = 0
					ENDIF
				ENDIF
			ENDIF
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////


		ELSE //IS DEBUG OFF


	///	FORCE WEATHER /////////////////////////////////////////////////////
			IF IS_BUTTON_PRESSED PAD2 LEFTSHOULDER2
			OR IS_BUTTON_PRESSED PAD2 RIGHTSHOULDER2

				IF IS_BUTTON_PRESSED PAD2 LEFTSHOULDER2
					weather_crap ++
					IF weather_crap > 23
						weather_crap = 0	   
					ENDIF
					GOTO change_the_weather_cycle
				ENDIF
				IF IS_BUTTON_PRESSED PAD2 RIGHTSHOULDER2
				AND IS_BUTTON_PRESSED PAD2 CROSS
					weather_crap --
					IF weather_crap < 0
						weather_crap = 23		   
					ENDIF
					GOTO change_the_weather_cycle
				ENDIF
				 
				GOTO mission_start_debug
				
				change_the_weather_cycle:
					
				IF weather_crap = 0
					RELEASE_WEATHER 
					PRINT_NOW ( WEATH0 ) 1000 1 //CHEAT MODE ON //WEATHER_SUNNY_SMOG
					WAIT 300
				ENDIF

				IF weather_crap = 1
					FORCE_WEATHER_NOW WEATHER_SUNNY_LA
					PRINT_NOW ( WEATH1 ) 1000 1 //CHEAT MODE ON
					WAIT 300
				ENDIF

				IF weather_crap = 2
					FORCE_WEATHER_NOW WEATHER_CLOUDY_LA
					PRINT_NOW ( WEATH2 ) 1000 1 //CHEAT MODE ON
					WAIT 300
				ENDIF

				IF weather_crap = 3
					FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_LA
					PRINT_NOW ( WEATH3 ) 1000 1 //CHEAT MODE ON
					WAIT 300
				ENDIF

				IF weather_crap = 4
					FORCE_WEATHER_NOW WEATHER_SUNNY_SMOG_LA
					PRINT_NOW ( WEATH4 ) 1000 1 //CHEAT MODE ON
					WAIT 300
				ENDIF

				IF weather_crap = 5
					FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_SMOG_LA
					PRINT_NOW ( WEATH5 ) 1000 1 //CHEAT MODE ON
					WAIT 300
				ENDIF

				IF weather_crap = 6
					FORCE_WEATHER_NOW WEATHER_RAINY_COUNTRYSIDE
					PRINT_NOW ( WEATH6 ) 1000 1 //CHEAT MODE ON //WEATHER_SUNNY_SMOG
					WAIT 300
				ENDIF

				IF weather_crap = 7
					FORCE_WEATHER_NOW WEATHER_CLOUDY_COUNTRYSIDE
					PRINT_NOW ( WEATH7 ) 1000 1 //CHEAT MODE ON //WEATHER_EXTRASUNNY_SMOG
					WAIT 300
				ENDIF

				IF weather_crap = 8
					FORCE_WEATHER_NOW WEATHER_SUNNY_COUNTRYSIDE
					PRINT_NOW ( WEATH8 ) 1000 1 //CHEAT MODE ON //WEATHER_SANDSTORM
					WAIT 300
				ENDIF

				IF weather_crap = 9
					FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_COUNTRYSIDE
					PRINT_NOW ( WEATH9 ) 1000 1 //CHEAT MODE ON //WEATHER_EXTRACOLOURS
					WAIT 300
				ENDIF
				

				IF weather_crap = 10
					FORCE_WEATHER_NOW WEATHER_FOGGY_SF
					PRINT_NOW ( WEATH10 ) 1000 1 //CHEAT MODE ON //WEATHER_EXTRACOLOURS
					WAIT 300
				ENDIF                                      

				IF weather_crap = 11
					FORCE_WEATHER_NOW WEATHER_SUNNY_SF
					PRINT_NOW ( WEATH11 ) 1000 1 //CHEAT MODE ON //WEATHER_EXTRACOLOURS
					WAIT 300
				ENDIF

				IF weather_crap = 12
					FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_SF
					PRINT_NOW ( WEATH12 ) 1000 1 //CHEAT MODE ON //WEATHER_EXTRACOLOURS
					WAIT 300
				ENDIF

				IF weather_crap = 13
					FORCE_WEATHER_NOW WEATHER_CLOUDY_SF
					PRINT_NOW ( WEATH13 ) 1000 1 //CHEAT MODE ON //WEATHER_EXTRACOLOURS
					WAIT 300
				ENDIF

				IF weather_crap = 14
					FORCE_WEATHER_NOW WEATHER_RAINY_SF
					PRINT_NOW ( WEATH14 ) 1000 1 //CHEAT MODE ON //WEATHER_EXTRACOLOURS
					WAIT 300
				ENDIF

				IF weather_crap = 15
					FORCE_WEATHER_NOW WEATHER_SANDSTORM_DESERT                       
					PRINT_NOW ( WEATH15 ) 1000 1 //CHEAT MODE ON //WEATHER_EXTRACOLOURS
					WAIT 300
				ENDIF

				IF weather_crap = 16
					FORCE_WEATHER_NOW WEATHER_SUNNY_DESERT
					PRINT_NOW ( WEATH16 ) 1000 1 //CHEAT MODE ON //WEATHER_EXTRACOLOURS
					WAIT 300
				ENDIF

				IF weather_crap = 17
					FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT
					PRINT_NOW ( WEATH17 ) 1000 1 //CHEAT MODE ON //WEATHER_EXTRACOLOURS
					WAIT 300
				ENDIF


				IF weather_crap = 18
					FORCE_WEATHER_NOW WEATHER_SUNNY_VEGAS
					PRINT_NOW ( WEATH18 ) 1000 1 //CHEAT MODE ON //WEATHER_EXTRACOLOURS
					WAIT 300
				ENDIF
				IF weather_crap = 19
					FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_VEGAS
					PRINT_NOW ( WEATH19 ) 1000 1 //CHEAT MODE ON //WEATHER_EXTRACOLOURS
					WAIT 300
				ENDIF
				IF weather_crap = 20
					FORCE_WEATHER_NOW WEATHER_CLOUDY_VEGAS
					PRINT_NOW ( WEATH20 ) 1000 1 //CHEAT MODE ON //WEATHER_EXTRACOLOURS
					WAIT 300
				ENDIF
	 			IF weather_crap = 21
					FORCE_WEATHER_NOW WEATHER_EXTRACOLOURS_1
					PRINT_NOW ( WEATH21 ) 1000 1 //CHEAT MODE ON //WEATHER_EXTRACOLOURS1
					WAIT 300
				ENDIF
				IF weather_crap = 22
					FORCE_WEATHER_NOW WEATHER_EXTRACOLOURS_2
					PRINT_NOW ( WEATH22 ) 1000 1 //CHEAT MODE ON //WEATHER_EXTRACOLOURS2
					WAIT 300
				ENDIF
				IF weather_crap = 23
					FORCE_WEATHER_NOW WEATHER_UNDERWATER
					PRINT_NOW ( WEATH23 ) 1000 1 //CHEAT MODE ON //WEATHER_EXTRACOLOURS2
					WAIT 300
				ENDIF
				

				IF NOT IS_PLAYER_PLAYING player1
					GOTO mission_start_debug
				ENDIF
				WHILE IS_BUTTON_PRESSED PAD2 LEFTSHOULDER2
				OR IS_BUTTON_PRESSED PAD2 RIGHTSHOULDER2
					WAIT 0
				ENDWHILE
			ENDIF
		ENDIF	  
		
	ENDIF // 2 PLAYER FLAG	
	  
///////////////////////////////////////////////////////////////////////

/// WRITE PLAYER COORDS TO TEMP_DEBUG.TXT /////////////////////////////
		IF IS_PLAYER_PLAYING player1
			IF NOT IS_DEBUG_CAMERA_ON
				IF IS_BUTTON_PRESSED PAD2 LEFTSHOULDER1
				AND text_button_pressed = 0
					GET_CHAR_COORDINATES scplayer text_x text_y text_z
					GET_GROUND_Z_FOR_3D_COORD text_x text_y text_z text_z
					GET_CHAR_HEADING scplayer text_h
					SAVE_FLOAT_TO_DEBUG_FILE text_x
					SAVE_FLOAT_TO_DEBUG_FILE text_y
					SAVE_FLOAT_TO_DEBUG_FILE text_z
					SAVE_FLOAT_TO_DEBUG_FILE text_h
					SAVE_NEWLINE_TO_DEBUG_FILE
					IF NOT IS_2PLAYER_GAME_GOING_ON
						PRINT_NOW TEXXYZ1 1000 1 // Writing coordinates to file...
					ENDIF
					text_button_pressed = 1
				ENDIF

				IF NOT IS_BUTTON_PRESSED PAD2 LEFTSHOULDER1
				AND text_button_pressed = 1
					text_button_pressed = 0
				ENDIF
			
	///////////////////////////////////////////////////////////////////////


	///	WRITE DEBUG CAMERA COORDS TO TEMP_DEBUG.TXT ///////////////////////
			ELSE
				IF IS_BUTTON_PRESSED PAD2 LEFTSHOULDER1
				AND repeat_button_press = 0
					IF NOT IS_2PLAYER_GAME_GOING_ON
						PRINT_NOW TEXXYZ2 1000 1 // Writing coordinates to file...
					ENDIF

					GET_CHAR_COORDINATES scplayer text_x text_y text_z
					GET_GROUND_Z_FOR_3D_COORD text_x text_y text_z text_z
					GET_CHAR_HEADING scplayer text_h
					SAVE_FLOAT_TO_DEBUG_FILE text_x
					SAVE_FLOAT_TO_DEBUG_FILE text_y
					SAVE_FLOAT_TO_DEBUG_FILE text_z
					SAVE_FLOAT_TO_DEBUG_FILE text_h
					SAVE_NEWLINE_TO_DEBUG_FILE
					
					GET_DEBUG_CAMERA_COORDINATES text_x text_y text_z
					SAVE_FLOAT_TO_DEBUG_FILE text_x
					SAVE_FLOAT_TO_DEBUG_FILE text_y
					SAVE_FLOAT_TO_DEBUG_FILE text_z
					GET_DEBUG_CAMERA_POINT_AT text_x text_y text_z
					SAVE_FLOAT_TO_DEBUG_FILE text_x
					SAVE_FLOAT_TO_DEBUG_FILE text_y
					SAVE_FLOAT_TO_DEBUG_FILE text_z
					SAVE_NEWLINE_TO_DEBUG_FILE
					repeat_button_press = 1
				ENDIF

				IF NOT IS_BUTTON_PRESSED PAD2 LEFTSHOULDER2
					IF repeat_button_press = 1
						repeat_button_press = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF
///////////////////////////////////////////////////////////////////////
				   
				
// ********************************************************************************************************************
// MISSION SKIP *******************************************************************************************************

IF NOT IS_2PLAYER_GAME_GOING_ON

	IF IS_PLAYER_PLAYING player1

		IF IS_BUTTON_PRESSED PAD2 RIGHTSHOCK
		OR is_player_in_mission_menu1 = 1
			IF NOT is_player_in_mission_menu1 = 0
				WHILE IS_BUTTON_PRESSED PAD2 RIGHTSHOCK
					WAIT 0
				ENDWHILE
			ENDIF

			USE_TEXT_COMMANDS TRUE
			is_player_in_mission_menu1 = 1

			WHILE is_player_in_mission_menu1 = 1
				WAIT 0
					
				GOSUB debug_menu_text
				GOSUB check_mission_debug_pad
				GOSUB check_mission_debug_pad2
				GOSUB check_mission_debug_pad3
				GOSUB quit_out_of_mission_debug

				IF IS_BUTTON_PRESSED PAD2 CROSS
					IF mission_debug_page = 0
						GOSUB debug_page1
						GOSUB load_and_warp_player
					ENDIF
					IF mission_debug_page = 1
						GOSUB debug_page2
						GOSUB load_and_warp_player
					ENDIF
					IF mission_debug_page = 2
						GOSUB debug_page6
						GOSUB load_and_warp_player
					ENDIF
					IF mission_debug_page = 3
						GOSUB debug_page3
						cut_doesnt_have_text = 0
						GOSUB Start_cutscene
					ENDIF
					IF mission_debug_page = 4
						GOSUB debug_page4
						cut_doesnt_have_text = 0
						GOSUB Start_cutscene
					ENDIF
					IF mission_debug_page = 5
						GOSUB debug_page5
						cut_doesnt_have_text = 1
						GOSUB Start_cutscene
					ENDIF
				ENDIF

			ENDWHILE

		ENDIF
	ENDIF

ENDIF

// --- AUDIO DEBUG
IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_F1
	IF iAudioDebug = 0
		START_NEW_SCRIPT Audio_debug
		iAudioDebug = 1
	ENDIF
ENDIF

GOTO mission_start_debug


MISSION_END 

}

{

load_and_warp_player:

	IF IS_PLAYER_PLAYING player1
		IF change_area_code = 1
			SET_AREA_VISIBLE 12
			SET_CHAR_AREA_VISIBLE scplayer 12
		ELSE
			SET_CHAR_AREA_VISIBLE scplayer 0
			SET_AREA_VISIBLE 0
		ENDIF
		IF change_area_code = 2
			SET_AREA_VISIBLE 10
			SET_CHAR_AREA_VISIBLE scplayer 10	
		ENDIF
		IF change_area_code = 3
			SET_AREA_VISIBLE 3
			SET_CHAR_AREA_VISIBLE scplayer 3	
		ENDIF
		IF change_area_code = 4
			SET_AREA_VISIBLE 1
			SET_CHAR_AREA_VISIBLE scplayer 1	
		ENDIF
		IF change_area_code = 5
			SET_AREA_VISIBLE 17
			SET_CHAR_AREA_VISIBLE scplayer 17	
		ENDIF
		change_area_code = 0
		LOAD_SCENE goto_thereX goto_thereY goto_thereZ
		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer goto_thereX goto_thereY goto_thereZ	
		ELSE
			SET_CHAR_COORDINATES scplayer goto_thereX goto_thereY goto_thereZ	
		ENDIF
		SET_CHAR_HEADING scplayer debug_heading
		SET_CAMERA_BEHIND_PLAYER
		is_player_in_mission_menu1 = 0
	ENDIF

RETURN			   


Start_cutscene:

	IF IS_PLAYER_PLAYING player1
		DO_FADE 1000 FADE_OUT

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		
		IF cut_doesnt_have_text = 0
			LOAD_MISSION_TEXT $cuttext
		ENDIF

		SET_AREA_VISIBLE visible_area

		LOAD_CUTSCENE $new_cut
		 
		WHILE NOT HAS_CUTSCENE_LOADED
			WAIT 0
		ENDWHILE

		IF $new_cut = DATE1AP
		OR $new_cut = DATE1BP
		OR $new_cut = DATE1AB
		OR $new_cut = DATE1BB
			GET_AREA_VISIBLE debug_visible_area
			IF IS_PLAYER_PLAYING player1
				IF debug_visible_area = 5		
					SET_EXTRA_COLOURS 4 FALSE
					SET_CUTSCENE_OFFSET 370.0 -125.0 1001.52
					SET_CHAR_COORDINATES scplayer 370.0 -125.0 1001.52
					LOAD_SCENE 370.0 -125.0 1001.52
				ELSE
					SET_EXTRA_COLOURS 4 FALSE
					SET_CUTSCENE_OFFSET 367.891 -67.591 1001.516
					SET_CHAR_COORDINATES scplayer 367.891 -67.591 1001.516
					LOAD_SCENE 367.891 -67.591 1001.516
				ENDIF
			ENDIF
			GOTO skip_offset_bit
		ENDIF 

		IF $new_cut = DATE2A
		OR $new_cut = DATE2B
			SET_EXTRA_COLOURS 4 FALSE
		ENDIF

		IF $new_cut = DATE3A
		OR $new_cut = DATE3B
			SET_EXTRA_COLOURS 4 FALSE
		ENDIF

		IF $new_cut = DATE4A
		OR $new_cut = DATE4B
			SET_EXTRA_COLOURS 4 FALSE
		ENDIF

		IF $new_cut = DATE5A
		OR $new_cut = DATE5B
			GET_AREA_VISIBLE debug_visible_area
			IF IS_PLAYER_PLAYING player1
				IF debug_visible_area = 6
					SET_EXTRA_COLOURS 4 FALSE
					SET_CUTSCENE_OFFSET 441.871 -60.839 1000.675
					SET_CHAR_COORDINATES scplayer 441.871 -60.839 1000.675
					LOAD_SCENE 441.871 -60.839 1000.675
				ELSE
					SET_EXTRA_COLOURS 4 FALSE
					SET_CUTSCENE_OFFSET 445.381 -14.147 1001.731
					SET_CHAR_COORDINATES scplayer 445.381 -14.147 1001.731
					LOAD_SCENE 445.381 -14.147 1001.731
				ENDIF
			ENDIF
			GOTO skip_offset_bit
		ENDIF

		IF $new_cut = DATE6A
		OR $new_cut = DATE6B
			GET_AREA_VISIBLE debug_visible_area
			IF IS_PLAYER_PLAYING player1
				IF debug_visible_area = 17
					SET_EXTRA_COLOURS 5 FALSE
					SET_CUTSCENE_OFFSET 498.536 -18.2  1000.651
					SET_CHAR_COORDINATES scplayer 498.536 -18.2 1000.651
					LOAD_SCENE 498.536 -18.2 1000.651
				ELSE
					SET_EXTRA_COLOURS 6 FALSE
					SET_CUTSCENE_OFFSET 490.718 -79.168 998.76
					SET_CHAR_COORDINATES scplayer 490.718 -79.168 998.76
					LOAD_SCENE 490.718 -79.168 998.76
				ENDIF
			ENDIF
			GOTO skip_offset_bit
		ENDIF


		GET_CUTSCENE_OFFSET cut_offsetX	cut_offsetY cut_offsetZ
		
		cut_offsetZ = cut_offsetZ + 5.0

		IF IS_PLAYER_PLAYING player1
			SET_CHAR_COORDINATES scplayer cut_offsetX cut_offsetY cut_offsetZ
			LOAD_SCENE cut_offsetX cut_offsetY cut_offsetZ
		ENDIF
		
		skip_offset_bit:
		 
		START_CUTSCENE

		DO_FADE 1000 FADE_IN
		  
		WHILE NOT HAS_CUTSCENE_FINISHED
			WAIT 0
		ENDWHILE
		 
		CLEAR_CUTSCENE
		CLEAR_EXTRA_COLOURS FALSE

		DO_FADE 0 FADE_OUT

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		SET_AREA_VISIBLE 0

		DO_FADE 1000 FADE_IN

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
	ENDIF

RETURN


check_mission_debug_pad:

	GET_POSITION_OF_ANALOGUE_STICKS PAD2 LStickX LStickY RStickX RStickY

	IF IS_BUTTON_PRESSED PAD2 DPADDOWN
	OR LStickY > 10					
		TIMERB = 0
		WHILE TIMERB < 70
			WAIT 0
			GOSUB debug_menu_text
		ENDWHILE
	
		button_pressed_main ++
		IF mission_debug_page = 0
			IF button_pressed_main > 83  //MISSIONS
				button_pressed_main = 0	   
			ENDIF
		ENDIF
		IF mission_debug_page = 1
			IF button_pressed_main > 15  //MISSIONS
				button_pressed_main = 0	   
			ENDIF
		ENDIF
		IF mission_debug_page = 2
			IF button_pressed_main > 82  //ODDJOBS
				button_pressed_main = 0	   
			ENDIF
		ENDIF
		IF mission_debug_page = 3
			IF button_pressed_main > 83  //CUTSCENES
				button_pressed_main = 0	   
			ENDIF
		ENDIF
		IF mission_debug_page = 4
			IF button_pressed_main > 74  //CUTSCENES
				button_pressed_main = 0	   
			ENDIF
		ENDIF
		IF mission_debug_page = 5
			IF button_pressed_main > 22  //CUTSCENES
				button_pressed_main = 0	   
			ENDIF
		ENDIF
		
	ENDIF

	IF IS_BUTTON_PRESSED PAD2 DPADUP
	OR LStickY < -10
		TIMERB = 0
		WHILE TIMERB < 70
			WAIT 0
			GOSUB debug_menu_text
		ENDWHILE
		
		button_pressed_main --
		IF mission_debug_page = 0
			IF button_pressed_main < 0
				button_pressed_main = 83  //MISSIONS	   
			ENDIF						    
		ENDIF
		IF mission_debug_page = 1
			IF button_pressed_main < 0
				button_pressed_main = 15  //MISSIONS	   
			ENDIF						    
		ENDIF
		IF mission_debug_page = 2
			IF button_pressed_main < 0
				button_pressed_main = 82  //ODDJOBS	   
			ENDIF
		ENDIF
		IF mission_debug_page = 3
			IF button_pressed_main < 0
				button_pressed_main = 83  //CUTSCENES	   
			ENDIF						    
		ENDIF
		IF mission_debug_page = 4
			IF button_pressed_main < 0
				button_pressed_main = 74  //CUTSCENES	   
			ENDIF						    
		ENDIF
		IF mission_debug_page = 5
			IF button_pressed_main < 0
				button_pressed_main = 22  //CUTSCENES	   
			ENDIF						    
		ENDIF

	ENDIF

RETURN

check_mission_debug_pad2:

	GET_POSITION_OF_ANALOGUE_STICKS PAD2 LStickX LStickY RStickX RStickY

	IF IS_BUTTON_PRESSED PAD2 DPADRIGHT
	OR LStickX > 10
		TIMERB = 0
		WHILE TIMERB < 70
			WAIT 0
			GOSUB debug_menu_text
		ENDWHILE

		button_pressed_main = button_pressed_main + 28
		
		//which_debug_row ++

		//IF which_debug_row > 2
		//	which_debug_row = 0
		//ENDIF

		IF mission_debug_page = 0
			IF button_pressed_main > 83 
				button_pressed_main -= 84  //MISSIONS	   
			ENDIF						     
		ENDIF
		IF mission_debug_page = 1
			IF button_pressed_main > 15 
				button_pressed_main = 0	   //MISSIONS
			ENDIF						     
		ENDIF
		IF mission_debug_page = 2
			IF button_pressed_main > 82
				button_pressed_main = 0	   //ODDJOBS
			ENDIF
		ENDIF
		IF mission_debug_page = 3
			IF button_pressed_main > 83 
				button_pressed_main -= 84  //CUTSCENES
			ENDIF						     
		ENDIF
		IF mission_debug_page = 4
			IF button_pressed_main > 74 
				button_pressed_main = 0  //CUTSCENES
			ENDIF						     
		ENDIF
		IF mission_debug_page = 5
			IF button_pressed_main > 22 
				button_pressed_main = 0  //CUTSCENES
			ENDIF						     
		ENDIF

	ENDIF

	IF IS_BUTTON_PRESSED PAD2 DPADLEFT
	OR LStickX < -10
		TIMERB = 0
		WHILE TIMERB < 70
			WAIT 0
			GOSUB debug_menu_text
		ENDWHILE

		button_pressed_main = button_pressed_main - 28
		
		//which_debug_row --

		//IF which_debug_row < 0
		//	which_debug_row = 2
		//ENDIF

		IF mission_debug_page = 0
			IF button_pressed_main < 0
				button_pressed_main += 84  //MISSIONS	   
			ENDIF						     
		ENDIF
		IF mission_debug_page = 1
			IF button_pressed_main < 0
				button_pressed_main = 15   //MISSIONS	   
			ENDIF						     
		ENDIF 
		IF mission_debug_page = 2
			IF button_pressed_main < 0
				button_pressed_main = 82   //ODDJOBS	   
			ENDIF
		ENDIF
		IF mission_debug_page = 3
			IF button_pressed_main < 0
				button_pressed_main += 84   //CUTSCENES	   
			ENDIF						     
		ENDIF
		IF mission_debug_page = 4
			IF button_pressed_main < 0
				button_pressed_main = 74   //CUTSCENES	   
			ENDIF
		ENDIF
		IF mission_debug_page = 5
			IF button_pressed_main < 0
				button_pressed_main = 22   //CUTSCENES	   
			ENDIF
		ENDIF

	ENDIF


RETURN

check_mission_debug_pad3:

	IF IS_BUTTON_PRESSED PAD2 RIGHTSHOULDER1
		WHILE IS_BUTTON_PRESSED PAD2 RIGHTSHOULDER1
			WAIT 0
			GOSUB debug_menu_text
		ENDWHILE
		
		mission_debug_page ++
		IF mission_debug_page > 5
			mission_debug_page = 0   
		ENDIF

		button_pressed_main = 0
		
	ENDIF

	IF IS_BUTTON_PRESSED PAD2 LEFTSHOULDER1
		WHILE IS_BUTTON_PRESSED PAD2 LEFTSHOULDER1
			WAIT 0
			GOSUB debug_menu_text
		ENDWHILE

		mission_debug_page --
		IF mission_debug_page < 0
			mission_debug_page = 5	   
		ENDIF

		button_pressed_main = 0

	ENDIF

RETURN


}

debug_menu_text:

	text_debug_num = 0
	TEXT_POS_X = 20.0
	TEXT_POS_Y = 20.0
	TEXT_POS_Y_STEP = 14.0
	TEXT_SCALEX = 0.4
	TEXT_SCALEY	= 1.6
	COL1_R = 200
	COL1_G = 200
	COL1_B = 200
	COL2_R = 130
	COL2_G = 130
	COL2_B = 130

	DRAW_RECT 320.0 240.0 640.0 480.0 0 0 0 220
	SET_TEXT_WRAPX 600.0

	SET_TEXT_COLOUR COL2_R COL2_G COL2_B 255	
	SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
	DISPLAY_TEXT 40.0 415.0 DEBUGT1

	IF button_pressed_main = text_debug_num
		SET_TEXT_COLOUR COL1_R COL1_G COL1_B 255
		SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
	ELSE
		SET_TEXT_COLOUR COL2_R COL2_G COL2_B 255	
		SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
	ENDIF

	IF mission_debug_page = 0 //PAGE 1 MISSIONS****************************************************************************************************
							
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y INTRO_1 
			
		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y INTRO_2
 
		GOSUB text_set_up  

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SWEET_1

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SWEET1B

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SWEET_3

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SWEET_2 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SWEET_4
	
		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SWEET_5
	
		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SWEET_6

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CRASH_2
	
		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SWEET_7
	
		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CRASH_1

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CRASH_3

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SMOKE_1

		GOSUB text_set_up
	
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SMOKE_2

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SMOKE_3

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SMOKE_4

		GOSUB text_set_up
	
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y STRAP_1

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y STRAP_2

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y STRAP_3

		GOSUB text_set_up
	
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y STRAP_4

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y RYDER_1

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y RYDER_3

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y RYDER_2

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CESAR_1
	
		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y LA1FIN1

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y LA1FIN2

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y BCRASH1 

	// Centre *************************************************************************
		TEXT_POS_X = 210.0
		TEXT_POS_Y = 20.0
		TEXT_POS_Y_STEP = 14.0
		SET_TEXT_WRAPX 640.0
		COL1_R = 200
		COL1_G = 200
		COL1_B = 200
		COL2_R = 130
		COL2_G = 130
		COL2_B = 130
		text_debug_num ++

		IF button_pressed_main = text_debug_num
			SET_TEXT_COLOUR COL1_R COL1_G COL1_B 255
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ELSE
			SET_TEXT_COLOUR COL2_R COL2_G COL2_B 255	
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ENDIF

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CAT_1 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CAT_2 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CAT_3 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CAT_4 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y TRUTH_1 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y TRUTH_2  

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y BCESAR4 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y BCES4_2 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y BCESAR2 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y GAR_1 

		GOSUB text_set_up
	
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y GAR_2

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SCRA_1 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SCRA_2 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y WUZI_1 //WUZI 1

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y FAR_4 //WUZI 3

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y FAR_5 //WUZI 4

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y WUZI_2 //WUZI 5

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y WUZI_4 //WUZI 7

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SYND_1  //Syndicate 1

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SYND_2 //Syndicate 2

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y FAR_2 //Syndicate 3

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y FAR_3 //Syndicate 4

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SYND_3 //Syndicate 5

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SYND_4 //Syndicate 6 
   	 
		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SYND_5 //Syndicate 7

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SYND_6 //Syndicate 8

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SYND_7 //Syndicate 9

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y STEAL_1 

		// Right hand side *************************************************************
		TEXT_POS_X = 420.0
		TEXT_POS_Y = 20.0
		TEXT_POS_Y_STEP = 14.0
		SET_TEXT_WRAPX 640.0
		COL1_R = 200
		COL1_G = 200
		COL1_B = 200
		COL2_R = 130
		COL2_G = 130
		COL2_B = 130
		text_debug_num ++

		IF button_pressed_main = text_debug_num
			SET_TEXT_COLOUR COL1_R COL1_G COL1_B 255
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ELSE
			SET_TEXT_COLOUR COL2_R COL2_G COL2_B 255	
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ENDIF

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y STEAL_2  

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y STEAL_4 

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y STEAL_5

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y ZERO_1 

		GOSUB text_set_up
		
	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y ZERO_2 

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y ZERO_4 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y FAR_1

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y TRACE_1 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y DESERT1

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y DESERT2 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y DESERT3 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y DESERT4 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y DESERT5

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y DESERT6 

		GOSUB text_set_up
		
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y DESERT9

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y DESERT8 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y DESER10   

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CASINO1 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CASEEN2

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CASINO3 

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CASINO4 
		
		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CASINO5

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CASINO6

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CASINO7

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CASINO9

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CASIN10

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y VCRASH1

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y VCRASH2

		text_debug_num ++

		SET_TEXT_SCALE 0.6 1.7
		SET_TEXT_WRAPX 640.0
		SET_TEXT_COLOUR 150 150 150 255
		DISPLAY_TEXT 220.0 5.0 MPAGE1

	ENDIF
	

	IF mission_debug_page = 1  //PAGE 2 MISSIONS******************************************************************************************************

		TEXT_POS_X = 25.0
		TEXT_POS_Y = 20.0
		TEXT_POS_Y_STEP = 14.0
		SET_TEXT_WRAPX 640.0
		COL1_R = 200
		COL1_G = 200
		COL1_B = 200
		COL2_R = 130
		COL2_G = 130
		COL2_B = 130
		text_debug_num = 0

		IF button_pressed_main = text_debug_num
			SET_TEXT_COLOUR COL1_R COL1_G COL1_B 255
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ELSE
			SET_TEXT_COLOUR COL2_R COL2_G COL2_B 255	
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ENDIF

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y DOC_2

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y HEIST_1
 		
 		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y HEIST_3

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y HEIST_2
 			
		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y HEIST_4
		
		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y HEIST_5
		
		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y HEIST_9
		
		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y MAN_1

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y MAN_2

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y MAN_3

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y MAN_5

		GOSUB text_set_up
		
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y GROVE_1

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y GROVE_2

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y RIOT_1

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y RIOT_2

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y RIOT_4

		text_debug_num ++

		SET_TEXT_SCALE 0.6 1.7
		SET_TEXT_WRAPX 640.0
		SET_TEXT_COLOUR 150 150 150 255
		DISPLAY_TEXT 220.0 5.0 MPAGE2

	ENDIF
	
 IF mission_debug_page = 2  //PAGE 3 ODDJOBS***************************************************************************************************

		TEXT_POS_X = 25.0
		TEXT_POS_Y = 20.0
		TEXT_POS_Y_STEP = 14.0
		SET_TEXT_WRAPX 640.0
		COL1_R = 200
		COL1_G = 200
		COL1_B = 200
		COL2_R = 130
		COL2_G = 130
		COL2_B = 130
		text_debug_num = 0

		IF button_pressed_main = text_debug_num
			SET_TEXT_COLOUR COL1_R COL1_G COL1_B 255
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ELSE
			SET_TEXT_COLOUR COL2_R COL2_G COL2_B 255	
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ENDIF

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y GYM1_A //"GYM"

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y GYM1_B //"GYM"

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y GYM1_C //"GYM"

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CHICN //"Chicken drive through"

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y BURGERS //"BURGER SHOP"

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PIZZAS //"PIZZA SHOP"

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CLOTHES //"LA Clothes Shop"

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y BARBER //"Barber Shop"

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y AMMULA //"LA AmmuNation"

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CARDB1 //"Car Mods"

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CARDB2 //"Car Mods2"

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y ROUL //"roulette"

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SLOTM //"Slot machine"

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y WHEELO //wheel of fortune

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y BJACK //black jack

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y OTB //"OTB"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y POOL //"Pool"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y BBALL //"basket ball"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y LOWR //"lowrider"

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CONS1 //"Console game 1"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y QUARRY //Quarry

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y TRUCK //Trucking

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y BLOOD //Blood ring

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y MOUNTN //"Mountian bike"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PIMP //PIMP

		GOSUB text_set_up
	
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y BIKES //Bike school 

		GOSUB text_set_up
		
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y BOATS //Boat school

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y COUR1 //CourierLA

	// Centre *************************************************************************
		TEXT_POS_X = 260.0
		TEXT_POS_Y = 20.0
		TEXT_POS_Y_STEP = 14.0
		SET_TEXT_WRAPX 640.0
		COL1_R = 200
		COL1_G = 200
		COL1_B = 200
		COL2_R = 130
		COL2_G = 130
		COL2_B = 130
		text_debug_num ++

		IF button_pressed_main = text_debug_num
			SET_TEXT_COLOUR COL1_R COL1_G COL1_B 255
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ELSE
			SET_TEXT_COLOUR COL2_R COL2_G COL2_B 255	
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ENDIF

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y COUR2 //CourierSF

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y COUR3 //CourierLV

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y FREIGH //Frieght Train

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y BURG //Burglary

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y KICK //Kickstart

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y VALET //VALET

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SF_MODS //San Fran PP mods

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y IMPEXP //Import Export

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y BMXODD //BMX

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y DANCEOD //DANCING

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y HOTRING //HOTRING

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y TPLAY1 //"2 Player 4"
	
		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y TPLAY2 //"2 Player 8"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y TPLAY3 //"2 Player 9"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y TPLAY4 //"2 Player 10"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y TPLAY5 //"2 Player 11"
	
		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y TPLAY6 //"2 Player 12"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y TPLAY7 //"2 Player 9"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y TPLAY8 //"2 Player 10"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y TPLAY9 //"2 Player 11"
	
		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y TPLAY10 //"2 Player 12"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y BOTTY //"Triathalon"
		
		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP3 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP4 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP5 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP6 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP7 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP8 //"PROPERTY"


		// Right hand side *************************************************************
		TEXT_POS_X = 420.0
		TEXT_POS_Y = 20.0
		TEXT_POS_Y_STEP = 14.0
		SET_TEXT_WRAPX 640.0
		COL1_R = 200
		COL1_G = 200
		COL1_B = 200
		COL2_R = 130
		COL2_G = 130
		COL2_B = 130
		text_debug_num ++

		
		IF button_pressed_main = text_debug_num
			SET_TEXT_COLOUR COL1_R COL1_G COL1_B 255
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ELSE
			SET_TEXT_COLOUR COL2_R COL2_G COL2_B 255	
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ENDIF

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP9 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP10 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP11 //"PROPERTY"
		
		GOSUB text_set_up
		
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP12 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP13 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP14 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP15 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP16 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP17 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP18 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP19 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP20 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP21 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP22 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP23 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP24 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP25 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP26 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP27 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP28 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP29 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP30 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y PROP31 //"PROPERTY"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y RACET1 //"RACETOUR"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y RACET2 //"RACETOUR"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y RACET3 //"RACETOUR"

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y RACET4 //"RACETOUR"

		text_debug_num ++

		SET_TEXT_SCALE 0.6 1.7
		SET_TEXT_WRAPX 640.0
		SET_TEXT_COLOUR 150 150 150 255
		DISPLAY_TEXT 220.0 5.0 MPAGE3

	ENDIF

	SET_TEXT_SCALE 0.4 1.6
	SET_TEXT_WRAPX 640.0
	SET_TEXT_COLOUR 150 150 150 255
	

	IF mission_debug_page = 3  //PAGE 3***************************************************************************************************

		TEXT_POS_X = 25.0
		TEXT_POS_Y = 20.0
		TEXT_POS_Y_STEP = 14.0
		SET_TEXT_WRAPX 640.0
		COL1_R = 200
		COL1_G = 200
		COL1_B = 200
		COL2_R = 130
		COL2_G = 130
		COL2_B = 130
		text_debug_num = 0

		IF button_pressed_main = text_debug_num
			SET_TEXT_COLOUR COL1_R COL1_G COL1_B 255
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ELSE
			SET_TEXT_COLOUR COL2_R COL2_G COL2_B 255	
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ENDIF

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT01

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT02 

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT03 

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT04

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT05

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT06

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT07

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT08

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT09

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT10

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT11

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT12

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT13

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT14

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT15
 		
 		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT16
		
		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT17
		
		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT18
	
		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT19
		
		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT20
		
		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT21
		
		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT22
		
		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT23

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT24

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT25

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT26

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT27

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT28

	// Centre *************************************************************************
		TEXT_POS_X = 260.0
		TEXT_POS_Y = 20.0
		TEXT_POS_Y_STEP = 14.0
		SET_TEXT_WRAPX 640.0
		COL1_R = 200
		COL1_G = 200
		COL1_B = 200
		COL2_R = 130
		COL2_G = 130
		COL2_B = 130
		text_debug_num ++

		IF button_pressed_main = text_debug_num
			SET_TEXT_COLOUR COL1_R COL1_G COL1_B 255
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ELSE
			SET_TEXT_COLOUR COL2_R COL2_G COL2_B 255	
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ENDIF

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT29

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT29

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT30
 
		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT31

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT32

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT33

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT34

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT35

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT36

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT37

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT38

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT39

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT40

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT41

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT42

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT43

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT44

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT45

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT46

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT47

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT48

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT49

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT50

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT52

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT54

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT55

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT56

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT57

	// Right hand side *************************************************************
		TEXT_POS_X = 420.0
		TEXT_POS_Y = 20.0
		TEXT_POS_Y_STEP = 14.0
		SET_TEXT_WRAPX 640.0
		COL1_R = 200
		COL1_G = 200
		COL1_B = 200
		COL2_R = 130
		COL2_G = 130
		COL2_B = 130
		text_debug_num ++

		IF button_pressed_main = text_debug_num
			SET_TEXT_COLOUR COL1_R COL1_G COL1_B 255
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ELSE
			SET_TEXT_COLOUR COL2_R COL2_G COL2_B 255	
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ENDIF

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT60 

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT61 

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT62 

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT63

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT64 

		GOSUB text_set_up
		
	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT65 

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT66  

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT67

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT69 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT70

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT71  

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT72  

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT74

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT75 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT76 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT77 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT78 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT79   

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT80 

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT81

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT82 

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT83 
		
		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT84

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT86

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT87

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT88

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT89

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT90

		text_debug_num ++

		SET_TEXT_SCALE 0.6 1.7
		SET_TEXT_WRAPX 640.0
		SET_TEXT_COLOUR 150 150 150 255
		DISPLAY_TEXT 220.0 5.0 MPAGE4

	ENDIF


	IF mission_debug_page = 4  //PAGE 4******************************************************************************************************

		TEXT_POS_X = 25.0
		TEXT_POS_Y = 20.0
		TEXT_POS_Y_STEP = 14.0
		SET_TEXT_WRAPX 640.0
		COL1_R = 200
		COL1_G = 200
		COL1_B = 200
		COL2_R = 130
		COL2_G = 130
		COL2_B = 130
		text_debug_num = 0

		IF button_pressed_main = text_debug_num
			SET_TEXT_COLOUR COL1_R COL1_G COL1_B 255
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ELSE
			SET_TEXT_COLOUR COL2_R COL2_G COL2_B 255	
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ENDIF

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT91

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT92

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT94

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT95

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT96

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT97

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT98

		GOSUB text_set_up
																					
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT83

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT100

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT101

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT102

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT103

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT104

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT105

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT106

		GOSUB text_set_up
			
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT107

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT109

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT110

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT111

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT112

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT113
 
		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT115

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT116

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT117

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT118

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT119

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT120

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT121



		// Centre *************************************************************************
		TEXT_POS_X = 260.0
		TEXT_POS_Y = 20.0
		TEXT_POS_Y_STEP = 14.0
		SET_TEXT_WRAPX 640.0
		COL1_R = 200
		COL1_G = 200
		COL1_B = 200
		COL2_R = 130
		COL2_G = 130
		COL2_B = 130
		text_debug_num ++

		IF button_pressed_main = text_debug_num
			SET_TEXT_COLOUR COL1_R COL1_G COL1_B 255
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ELSE
			SET_TEXT_COLOUR COL2_R COL2_G COL2_B 255	
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ENDIF

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT122

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT123

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT124

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT125

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT126

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT127

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT128

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT129

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT130

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT131

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT132

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT133

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT134

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT137

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT138

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT139

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT140

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT141

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT142

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT143

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT150

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT151

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT152

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT_D1

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT_D2

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT_D3

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT_D4

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT_D5
		
		// Right hand side *************************************************************
		TEXT_POS_X = 420.0
		TEXT_POS_Y = 20.0
		TEXT_POS_Y_STEP = 14.0
		SET_TEXT_WRAPX 640.0
		COL1_R = 200
		COL1_G = 200
		COL1_B = 200
		COL2_R = 130																
		COL2_G = 130
		COL2_B = 130
		text_debug_num ++
																					
		IF button_pressed_main = text_debug_num
			SET_TEXT_COLOUR COL1_R COL1_G COL1_B 255
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ELSE																		
			SET_TEXT_COLOUR COL2_R COL2_G COL2_B 255	
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ENDIF
																					
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT_D6

		GOSUB text_set_up
		
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT_D7

		GOSUB text_set_up
		
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT_D8

		GOSUB text_set_up
		
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT_D9

		GOSUB text_set_up
		
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT_D10

		GOSUB text_set_up
		
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT_D11

		GOSUB text_set_up
		
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT_D12

		GOSUB text_set_up
		
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT_D13

		GOSUB text_set_up
		
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT_D14

		GOSUB text_set_up
		
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT_D15

		GOSUB text_set_up
		
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT_D16

		GOSUB text_set_up
		
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT_D17

		GOSUB text_set_up
		
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT_D18

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT133

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT141

		GOSUB text_set_up
					
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT107
		
		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT110

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUT120

		GOSUB text_set_up
																					
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y CUTTEST

		text_debug_num ++
																					
		SET_TEXT_SCALE 0.6 1.7
		SET_TEXT_WRAPX 640.0
		SET_TEXT_COLOUR 150 150 150 255
		DISPLAY_TEXT 220.0 5.0 MPAGE5

	ENDIF


	IF mission_debug_page = 5  //PAGE 5******************************************************************************************************

		TEXT_POS_X = 25.0
		TEXT_POS_Y = 20.0
		TEXT_POS_Y_STEP = 14.0
		SET_TEXT_WRAPX 640.0
		COL1_R = 200
		COL1_G = 200
		COL1_B = 200
		COL2_R = 130
		COL2_G = 130
		COL2_B = 130
		text_debug_num = 0

		IF button_pressed_main = text_debug_num
			SET_TEXT_COLOUR COL1_R COL1_G COL1_B 255
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ELSE
			SET_TEXT_COLOUR COL2_R COL2_G COL2_B 255	
			SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
		ENDIF

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_02

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_03

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_04

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_05

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_06

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_07

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_08

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_09

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_10

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_11

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_12

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_13

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_13V

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_14

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_15

		GOSUB text_set_up
			
		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_16

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_18

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_19

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_20

		GOSUB text_set_up

		DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_21

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_21B
 
		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_22

		GOSUB text_set_up

	   	DISPLAY_TEXT TEXT_POS_X TEXT_POS_Y SC_23

		text_debug_num ++

		SET_TEXT_SCALE 0.6 1.7
		SET_TEXT_WRAPX 640.0
		SET_TEXT_COLOUR 150 150 150 255
		DISPLAY_TEXT 220.0 5.0 MPAGE6

	ENDIF


RETURN


text_set_up:

	text_debug_num ++
	TEXT_POS_Y = TEXT_POS_Y + TEXT_POS_Y_STEP
	SET_TEXT_WRAPX 640.0
	COL1_R = 200
	COL1_G = 200
	COL1_B = 200
	COL2_R = 130
	COL2_G = 130
	COL2_B = 130

	IF button_pressed_main = text_debug_num
		SET_TEXT_COLOUR COL1_R COL1_G COL1_B 255
		SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
	ELSE
		SET_TEXT_COLOUR COL2_R COL2_G COL2_B 255	
		SET_TEXT_SCALE TEXT_SCALEX TEXT_SCALEY
	ENDIF

RETURN

quit_out_of_mission_debug:

	IF IS_BUTTON_PRESSED PAD2 RIGHTSHOCK
		WHILE IS_BUTTON_PRESSED PAD2 RIGHTSHOCK
			WAIT 0
		ENDWHILE

		is_player_in_mission_menu1 = 0
	ENDIF

RETURN



debug_page1: //MISSIONS PAGE1**************************************************************************************************

		FAIL_CURRENT_MISSION
		GOSUB terminate_all_scripts

		debug_number = 0

	IF button_pressed_main = debug_number 
		flag_intro_mission_counter = 0
		START_NEW_SCRIPT intro_mission_loop //"INTRO mission 1" //CRAIGF //BMX Bandits
		REMOVE_BLIP intro_contact_blip
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT introX introY introZ intro_blip_icon intro_contact_blip
		SET_LA_RIOTS OFF
		SET_INT_STAT CITIES_PASSED 0
		SET_MAX_WANTED_LEVEL 4
		TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME MOB_LA1
		START_NEW_SCRIPT cell_phone_LA1
		TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME MOB_RAN
		START_NEW_SCRIPT cell_phone_random
		SWITCH_ENTRY_EXIT ammun1 FALSE
		SWITCH_ENTRY_EXIT ammun2 FALSE
		SWITCH_ENTRY_EXIT ammun3 FALSE
		SWITCH_ENTRY_EXIT ammun4 FALSE
		SWITCH_ENTRY_EXIT ammun5 FALSE

		SWITCH_ENTRY_EXIT barbers FALSE 
		SWITCH_ENTRY_EXIT barber2 FALSE 
		SWITCH_ENTRY_EXIT barber3 FALSE 
		SWITCH_ENTRY_EXIT FDpiza FALSE
		SWITCH_ENTRY_EXIT fdchick FALSE
		SWITCH_ENTRY_EXIT fdburg FALSE
		SWITCH_ENTRY_EXIT tattoo FALSE

		SWITCH_ENTRY_EXIT cschp	FALSE
		SWITCH_ENTRY_EXIT cssprt FALSE
		SWITCH_ENTRY_EXIT lacs1	FALSE
		SWITCH_ENTRY_EXIT clothgp FALSE
		SWITCH_ENTRY_EXIT csdesgn FALSE
		SWITCH_ENTRY_EXIT csexl	FALSE
		
		SWITCH_ENTRY_EXIT gym1 FALSE
		SWITCH_ENTRY_EXIT gym2 FALSE
		SWITCH_ENTRY_EXIT gym3 FALSE
		
		SWITCH_ENTRY_EXIT PDOMES FALSE
		SWITCH_ENTRY_EXIT PDOMES2 FALSE
		
		SWITCH_ENTRY_EXIT MADDOGS FALSE
		SWITCH_ENTRY_EXIT MDDOGS FALSE
			  
		DEACTIVATE_GARAGE bodLAwN 		  
	    DEACTIVATE_GARAGE modlast 		  
	    DEACTIVATE_GARAGE mdsSFSe 		  
	    DEACTIVATE_GARAGE mds1SFS //PP CAR GARAGE 
	    DEACTIVATE_GARAGE vEcmod
		goto_thereX = introX 
		goto_thereY = introY_d 
		goto_thereZ = introZ
		debug_heading = 180.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_intro_mission_counter = 1
		START_NEW_SCRIPT intro_mission_loop //"INTRO mission 2" //CRAIGF //Haircut, Gym + robbery
		REMOVE_BLIP intro_contact_blip
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT ryderX ryderY ryderZ ryder_blip_icon intro_contact_blip
		goto_thereX = ryderX 
		goto_thereY = ryderY_d 
		goto_thereZ = ryderZ
		debug_heading = 165.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_sweet_mission_counter = 0
		START_NEW_SCRIPT sweet_mission_loop //"Sweet mission 1" //CRAIGF //Tagging
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip
		goto_thereX = sweetX_d 
		goto_thereY = sweetY 
		goto_thereZ = sweetZ
		debug_heading =	260.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_sweet_mission_counter = 1
		START_NEW_SCRIPT sweet_mission_loop //"Sweet mission 1B" //CRAIGF //Clean
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip
		goto_thereX = sweetX_d 
		goto_thereY = sweetY 
		goto_thereZ = sweetZ
		debug_heading =	260.0
	ENDIF
		debug_number ++	   
	IF button_pressed_main = debug_number 
		flag_sweet_mission_counter = 2
		START_NEW_SCRIPT sweet_mission_loop //"Sweet mission 3" //CRAIG F //Chicken Wings
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip
		goto_thereX = sweetX_d 
		goto_thereY = sweetY 
		goto_thereZ = sweetZ
		debug_heading =	260.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_sweet_mission_counter = 3
		START_NEW_SCRIPT sweet_mission_loop //"Sweet mission 2" //CRAIGF //Guns Guns Guns
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip
		goto_thereX = sweetX_d 
		goto_thereY = sweetY 
		goto_thereZ = sweetZ
		debug_heading =	260.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_sweet_mission_counter = 4
		START_NEW_SCRIPT sweet_mission_loop //"Sweet mission 4" //CHRIS M //Getto Drive by
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip
		goto_thereX = sweetX_d 
		goto_thereY = sweetY 
		goto_thereZ = sweetZ
		debug_heading =	260.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_sweet_mission_counter = 5
		START_NEW_SCRIPT sweet_mission_loop //"Sweet mission 5" //KEV B //RESCUE
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip
		goto_thereX = sweetX_d 
		goto_thereY = sweetY 
		goto_thereZ = sweetZ
		debug_heading =	260.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_sweet_mission_counter = 6
		sw6_mission_attempts = 0
		START_NEW_SCRIPT sweet_mission_loop //"Sweet mission 6" //NEIL //Lowrider comp	part 1
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip
		goto_thereX = sweetX_d 
		goto_thereY = sweetY 
		goto_thereZ = sweetZ
		debug_heading =	260.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_sweet_mission_counter = 7
		flag_mob_la1[4] = 1
		START_NEW_SCRIPT sweet_mission_loop //"Sweet mission 7" //WILLIE M //Doberman
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT 1365.2507 -1280.1200 12.5469 sweet_blip_icon sweet_contact_blip
		SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_FLAT PEDTYPE_PLAYER1
		goto_thereX = 1361.3253  
		goto_thereY = -1279.5175   
		goto_thereZ = 12.3828 
		debug_heading =	269.3503
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_sweet_mission_counter = 8
		SET_INT_STAT RESPECT_TOTAL 100
		START_NEW_SCRIPT sweet_mission_loop //"Sweet mission 8" //STEVE T //Grave Misfortune
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip
		goto_thereX = sweetX_d 
		goto_thereY = sweetY 
		goto_thereZ = sweetZ
		debug_heading =	260.0
	ENDIF
	   	debug_number ++
	IF button_pressed_main = debug_number 
		flag_crash_mission_counter = 0
		START_NEW_SCRIPT crash_mission_loop //"Crash mission 1" //CHRIS R //Burning Desire
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT crashX crashY crashZ crash_blip_icon crash_contact_blip
		goto_thereX = crashX 
		goto_thereY = crashY_d 
		goto_thereZ = crashZ
		debug_heading =	180.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_crash_mission_counter = 1
  		START_NEW_SCRIPT crash_mission_loop //"Crash mission 3" //IMRAN //Docks shootout
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT crashX crashY crashZ crash_blip_icon crash_contact_blip
		goto_thereX = crashX
		goto_thereY = crashY_d 
		goto_thereZ = crashZ
		debug_heading =	180.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_smoke_mission_counter = 0
		START_NEW_SCRIPT smoke_mission_loop //"Smoke mission 1" //ANDY //Payback
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT smokeX smokeY smokeZ smoke_blip_icon smoke_contact_blip
		goto_thereX = smokeX_d 
		goto_thereY = smokeY 
		goto_thereZ = smokeZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_smoke_mission_counter = 1
		START_NEW_SCRIPT smoke_mission_loop //"Smoke mission 2" //JUDITH //Northen Mexican chase
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT smokeX smokeY smokeZ smoke_blip_icon smoke_contact_blip
		goto_thereX = smokeX_d 
		goto_thereY = smokeY 
		goto_thereZ = smokeZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_smoke_mission_counter = 2
		START_NEW_SCRIPT smoke_mission_loop //"Smoke mission 3" //IMRAN //Train chase
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT smokeX smokeY smokeZ smoke_blip_icon smoke_contact_blip
		goto_thereX = smokeX_d 
		goto_thereY = smokeY 
		goto_thereZ = smokeZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_smoke_mission_counter = 3
		START_NEW_SCRIPT smoke_mission_loop //"Smoke mission 4" //IMRAN //Dodgy dealings
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT smokeX smokeY smokeZ smoke_blip_icon smoke_contact_blip
		goto_thereX = smokeX_d 
		goto_thereY = smokeY 
		goto_thereZ = smokeZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_strap_mission_counter = 0
		START_NEW_SCRIPT strap_mission_loop //"Strap mission 1" //JUDITH //GTS
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT strapX strapY strapZ strap_blip_icon strap_contact_blip
		goto_thereX = strapX_d 
		goto_thereY = strapY 
		goto_thereZ = strapZ
		debug_heading = 275.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_strap_mission_counter = 1
		START_NEW_SCRIPT strap_mission_loop //"Strap mission 2" //IMRAN //Steal from DocG
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT strapX strapY strapZ strap_blip_icon strap_contact_blip
		goto_thereX = strapX_d 
		goto_thereY = strapY 
		goto_thereZ = strapZ
		debug_heading = 275.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_strap_mission_counter = 2
		START_NEW_SCRIPT strap_mission_loop //"Strap mission 3" //ANDY //Kill G's manager
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT strapX strapY strapZ strap_blip_icon strap_contact_blip
		goto_thereX = strapX_d 
		goto_thereY = strapY 
		goto_thereZ = strapZ
		debug_heading = 275.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_strap_mission_counter = 4
		strap4_mission_passed_once_flag = 1
		START_NEW_SCRIPT strap_mission_loop //"Strap mission 4" //ANDY //House party
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT strap2X strap2Y strap2Z strap_blip_icon strap_contact_blip
		goto_thereX = strap2X_d
		goto_thereY = strap2Y  
		goto_thereZ = strap2Z
		debug_heading = 275.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_ryder_mission_counter = 0
		START_NEW_SCRIPT ryder_mission_loop //"Ryder mission 1" //DAVE //Burglary
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT ryderX ryderY ryderZ ryder_blip_icon ryder_contact_blip
		goto_thereX = ryderX 
		goto_thereY = ryderY_d 
		goto_thereZ = ryderZ
		debug_heading =	165.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_ryder_mission_counter = 1
		START_NEW_SCRIPT ryder_mission_loop //"Ryder mission 3" //CHRIS R //Ammo Train Truck
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT ryderX ryderY ryderZ ryder_blip_icon ryder_contact_blip
		goto_thereX = ryderX 
		goto_thereY = ryderY_d 
		goto_thereZ = ryderZ
		debug_heading =	165.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_ryder_mission_counter = 2
		START_NEW_SCRIPT ryder_mission_loop //"Ryder mission 2" //NEIL //National Guard
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT ryderX ryderY ryderZ ryder_blip_icon ryder_contact_blip
		goto_thereX = ryderX 
		goto_thereY = ryderY_d 
		goto_thereZ = ryderZ
		debug_heading =	165.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_cesar_mission_counter = 0
		START_NEW_SCRIPT cesar_mission_loop //"Cesar mission 1" //CHRIS M //Race
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT cesarX cesarY cesarZ cesar_blip_icon cesar_contact_blip
		CHANGE_BLIP_DISPLAY cesar_contact_blip BLIP_ONLY 
		goto_thereX = cesarX_d 
		goto_thereY = cesarY 
		goto_thereZ = cesarZ
		debug_heading =	256.0
	ENDIF
	/*
		debug_number ++
	IF button_pressed_main = debug_number //"Cesar mission 2" //KEV B //Impound
		flag_cesar_mission_counter = 1
		flag_mob_la1[3] = 1
		START_NEW_SCRIPT cesar_mission_loop // TEST STUFF
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT cesarX cesarY cesarZ cesar_blip_icon cesar_contact_blip
		CHANGE_BLIP_DISPLAY cesar_contact_blip BLIP_ONLY
		goto_thereX = cesarX_d 
		goto_thereY = cesarY 
		goto_thereZ = cesarZ
		debug_heading =	256.0
	ENDIF
	*/
		debug_number ++
	IF button_pressed_main = debug_number //"LA final mission 1" //IMRAN //Motel deal
		flag_la1fin1_mission_counter = 0
		trigger_final_LA1_missions = 1
		flag_sweet_mission_counter = 9
		flag_smoke_mission_counter = 4
		flag_strap_mission_counter = 5
		flag_ryder_mission_counter = 3
		flag_crash_mission_counter = 2
		flag_cesar_mission_counter = 1	
		START_NEW_SCRIPT la1fin1_mission_loop // TEST STUFF
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip
		goto_thereX = sweetX_d 
		goto_thereY = sweetY 
		goto_thereZ = sweetZ
		debug_heading =	260.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number //"LA final mission 2" //KEV B //Killers cutlass
		flag_la1fin1_mission_counter = 1
		trigger_final_LA1_missions = 1
		flag_sweet_mission_counter = 9
		flag_smoke_mission_counter = 4
		flag_strap_mission_counter = 5
		flag_ryder_mission_counter = 3
		flag_crash_mission_counter = 2
		flag_cesar_mission_counter = 1
		START_NEW_SCRIPT la1fin1_mission_loop // TEST STUFF
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip
		goto_thereX = sweetX_d 
		goto_thereY = sweetY 
		goto_thereZ = sweetZ
		debug_heading =	260.0
	ENDIF
	// **********************************BADLANDS
		debug_number ++
	IF button_pressed_main = debug_number //"crash mission 1" //CHRIS M //Witness protection
		flag_bcrash_mission_counter = 0
		cat_counter = 0
		flag_trailor_cutscene = 1
		flag_truth_mission_counter = 0
		START_NEW_SCRIPT bcrash_mission_loop // TEST STUFF
		created_save_blips = 0
		REMOVE_PICKUP grove_save_pickup[13]
		CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[13] save_pickupY[13] save_pickupZ[13] grove_save_pickup[13] //BADLANDS TRAILOR//remove
		number_of_save_icons = 14
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT bcrashX bcrashY bcrashZ crash_blip_icon bcrash_contact_blip
		REMOVE_IPL Barriers1
		SET_INT_STAT CITIES_PASSED 1
		SET_MAX_WANTED_LEVEL 5
		TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME MOB_CAT
		START_NEW_SCRIPT cell_phone_cat
		SWITCH_ENTRY_EXIT ammun1 TRUE
		SWITCH_ENTRY_EXIT ammun2 TRUE
		SWITCH_ENTRY_EXIT ammun3 TRUE
		SWITCH_ENTRY_EXIT ammun4 TRUE
		SWITCH_ENTRY_EXIT ammun5 TRUE

		SWITCH_ENTRY_EXIT barbers TRUE 
		SWITCH_ENTRY_EXIT barber2 TRUE 
		SWITCH_ENTRY_EXIT barber3 TRUE 
		SWITCH_ENTRY_EXIT FDpiza TRUE
		SWITCH_ENTRY_EXIT fdchick TRUE
		SWITCH_ENTRY_EXIT fdburg TRUE

		SWITCH_ENTRY_EXIT cschp	TRUE
		SWITCH_ENTRY_EXIT cssprt TRUE
		SWITCH_ENTRY_EXIT lacs1	TRUE
		SWITCH_ENTRY_EXIT clothgp TRUE
		SWITCH_ENTRY_EXIT csdesgn TRUE
		SWITCH_ENTRY_EXIT csexl	TRUE
		
		SWITCH_ENTRY_EXIT gym1 TRUE
		SWITCH_ENTRY_EXIT gym2 TRUE
		SWITCH_ENTRY_EXIT gym3 TRUE
		goto_thereX = bcrashX_d 
		goto_thereY = bcrashY 
		goto_thereZ = bcrashZ
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		START_NEW_SCRIPT cat_mission_loop //"Catalina mission 1" //NEIL //Rob liquor store
//		IF DOES_CHAR_EXIST catalina
//			REMOVE_CHAR_ELEGANTLY catalina
//			DELETE_CHAR	catalina
//		ENDIF
//		GOSUB remove_catalina
		flag_cat_mission_counter = 0
		flag_truth_mission_counter = 1
		flag_trailor_cutscene = 1
//		catalina_contact_blip_state	= 0
//		catalina_generation_flag = 1
//		cat_played_cut_first = 0
		cat_counter = 0
		flag_mob_cat[3] = 1
//		cat_mission_ended[0] = 1
		flag_cat_mission1_passed = 0
		flag_catcutscene_counter = 1
		flag_trailor_cutscene = 1
		//temp_float_1 = catY[5] - 4.0
		goto_thereX = catX[5] 
		goto_thereY = catY[5] 
		goto_thereZ = catZ[5]
		debug_heading = 0.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		START_NEW_SCRIPT cat_mission_loop //"Catalina mission 2" //KEV B //Rob Bank
//		IF DOES_CHAR_EXIST catalina
//			REMOVE_CHAR_ELEGANTLY catalina
//			DELETE_CHAR	catalina
//		ENDIF
//		GOSUB remove_catalina
		flag_cat_mission_counter = 1
		flag_truth_mission_counter = 1
		flag_trailor_cutscene = 1
//		catalina_contact_blip_state	= 0
//		catalina_generation_flag = 1
//		cat_played_cut_first = 0
		flag_cat_mission2_passed = 0
//		flag_catcutscene_counter = 1
		flag_trailor_cutscene = 1
		cat_counter = 1
		flag_mob_cat[3] = 1
//		cat_mission_ended[0] = 1
		goto_thereX = catx_d 
		goto_thereY = catY[0] 
		goto_thereZ = catZ[0]
		debug_heading = 337.2648
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		START_NEW_SCRIPT cat_mission_loop //"Catalina mission 3" //CHRIS //Rob Petrol station
//		IF DOES_CHAR_EXIST catalina
//			REMOVE_CHAR_ELEGANTLY catalina
//			DELETE_CHAR	catalina
//		ENDIF
//		GOSUB remove_catalina
		flag_cat_mission_counter = 2
		flag_truth_mission_counter = 1
		flag_trailor_cutscene = 1
//		catalina_contact_blip_state	= 0
//		catalina_generation_flag = 1
//		cat_played_cut_first = 0
		flag_cat_mission3_passed = 0
		flag_trailor_cutscene = 1
//		flag_catcutscene_counter = 1
		cat_counter = 2
		flag_mob_cat[3] = 1
//		cat_mission_ended[0] = 1
		goto_thereX = catx_d 
		goto_thereY = catY[0] 
		goto_thereZ = catZ[0]
		debug_heading = 337.2648
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		START_NEW_SCRIPT cat_mission_loop //"Catalina mission 4" //KEV W //Rob OTB
//		IF DOES_CHAR_EXIST catalina
//			REMOVE_CHAR_ELEGANTLY catalina
//			DELETE_CHAR	catalina
//		ENDIF
//		GOSUB remove_catalina
		flag_cat_mission_counter = 3
		flag_truth_mission_counter = 1
//		catalina_contact_blip_state	= 0
//		catalina_generation_flag = 1
//		cat_played_cut_first = 0
		flag_trailor_cutscene = 1
		flag_cat_mission4_passed = 0
//		flag_catcutscene_counter = 1
		cat_counter = 3
		flag_mob_cat[3] = 1
//		cat_mission_ended[0] = 1
		goto_thereX = catx_d 
		goto_thereY = catY[0] 
		goto_thereZ = catZ[0]
		debug_heading = 337.2648
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_truth_mission_counter = 0
		flag_trigger_trailor_cut = 1
		START_NEW_SCRIPT cat_mission_loop
		START_NEW_SCRIPT truth_mission_loop //"truth mission 1" //IMRAN //Body Harvest
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT truth2X truth2Y truth2Z truth_blip_icon truth_contact_blip //HOTEL
		goto_thereX = -2195.72  
		goto_thereY = -2255.89  
		goto_thereZ = 29.05
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_truth_mission_counter = 1
		flag_cat_mission1_passed = 1
		flag_cat_mission2_passed = 1
		flag_cat_mission3_passed = 1
		flag_cat_mission4_passed = 1
		flag_bcesar_mission_counter = 10
		START_NEW_SCRIPT truth_mission_loop //"truth mission 2" //ANDY //Full head of green
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT truthX truthY truthZ truth_blip_icon truth_contact_blip
		goto_thereX = truthX_d 
		goto_thereY = truthY 
		goto_thereZ = truthZ
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_bcesar_mission_counter = 0
		cat_counter = 4
	   	START_NEW_SCRIPT bcesar_mission_loop //"bcesar mission 4" //KEV B //Badlands Race1 
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT bcesarX bcesarY bcesarZ cesar_blip_icon bcesar_contact_blip
		CHANGE_BLIP_DISPLAY bcesar_contact_blip BLIP_ONLY
		goto_thereX = bcesarX_d 
		goto_thereY = bcesarY 
		goto_thereZ = bcesarZ
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_bcesar_mission_counter = 5
		cat_counter = 4
	   	START_NEW_SCRIPT bcesar_mission_loop //"bcesar mission 4" //KEV B //Badlands Race2 
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT -513.9356 -188.3140 77.4599 cesar_blip_icon bcesar_contact_blip
		goto_thereX = -513.9356  
		goto_thereY = -188.3140 
		goto_thereZ = 77.4599 
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		courier_timer = 400
		cat_counter = 3
		goto_thereX = bcesarX_d 
		goto_thereY = bcesarY 
		goto_thereZ = bcesarZ
	ENDIF
	// **********************************SAN FRAN
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_garage_mission_counter = 0
		START_NEW_SCRIPT garage_mission_loop //"Garage mission 1" //KEV W //Welcome to San Fran
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ RADAR_SPRITE_CJ garage_contact_blip
		created_save_blips = 0
		REMOVE_PICKUP grove_save_pickup[15]
		CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[15] save_pickupY[15] save_pickupZ[15] grove_save_pickup[15] //SAN FRAN GARAGE//remove
		number_of_save_icons = 16
		SET_INT_STAT CITIES_PASSED 1
		SET_MAX_WANTED_LEVEL 5
		flag_truth_mission_counter = 2
		TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME MOB_SF
		START_NEW_SCRIPT cell_phone_sanfran
		TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME MOB_RAN
		START_NEW_SCRIPT cell_phone_random
		SWITCH_ENTRY_EXIT ammun1 TRUE
		SWITCH_ENTRY_EXIT ammun2 TRUE
		SWITCH_ENTRY_EXIT ammun3 TRUE
		SWITCH_ENTRY_EXIT ammun4 TRUE
		SWITCH_ENTRY_EXIT ammun5 TRUE

		SWITCH_ENTRY_EXIT barbers TRUE 
		SWITCH_ENTRY_EXIT barber2 TRUE 
		SWITCH_ENTRY_EXIT barber3 TRUE 
		SWITCH_ENTRY_EXIT FDpiza TRUE
		SWITCH_ENTRY_EXIT fdchick TRUE
		SWITCH_ENTRY_EXIT fdburg TRUE

		SWITCH_ENTRY_EXIT cschp	TRUE
		SWITCH_ENTRY_EXIT cssprt TRUE
		SWITCH_ENTRY_EXIT lacs1	TRUE
		SWITCH_ENTRY_EXIT clothgp TRUE
		SWITCH_ENTRY_EXIT csdesgn TRUE
		SWITCH_ENTRY_EXIT csexl	TRUE
		
		SWITCH_ENTRY_EXIT gym1 TRUE
		SWITCH_ENTRY_EXIT gym2 TRUE
		SWITCH_ENTRY_EXIT gym3 TRUE
		goto_thereX = garageX_d 
		goto_thereY = garageY 
		goto_thereZ = garageZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_garage_mission_counter = 1
		flag_scrash_mission_counter = 1
		START_NEW_SCRIPT garage_mission_loop //"Garage mission 2" //NEIL //Demolition
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ RADAR_SPRITE_CJ garage_contact_blip
		goto_thereX = garageX_d 
		goto_thereY = garageY 
		goto_thereZ = garageZ
		debug_heading = 90.0
	ENDIF

		debug_number ++
	IF button_pressed_main = debug_number 
		flag_scrash_mission_counter = 0
		START_NEW_SCRIPT scrash_mission_loop //"Scrash mission 1" //KEV B //Plant The Drugs
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT scrashX scrashY scrashZ crash_blip_icon scrash_contact_blip
		goto_thereX = scrashX_d  
		goto_thereY = scrashY  
		goto_thereZ = scrashZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_scrash_mission_counter = 1
		flag_Synd_mission_counter = 6
		START_NEW_SCRIPT scrash_mission_loop //"Scrash mission 2" //CHRIS R	//Follow the ped
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT scrashX scrashY scrashZ crash_blip_icon scrash_contact_blip
		goto_thereX = scrashX_d 
		goto_thereY = scrashY
		goto_thereZ = scrashZ
		debug_heading = 90.0
	ENDIF
				
		debug_number ++
	IF button_pressed_main = debug_number
		flag_wuzi_mission_counter = 0
	   	START_NEW_SCRIPT wuzi_mission_loop //"Wuzi mission 1" //KEV B	//Meet the People
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT wuziX wuziY wuziZ wuzi_blip_icon wuzi_contact_blip
		goto_thereX = wuziX_d 
		goto_thereY = wuziY 
		goto_thereZ = wuziZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_wuzi_mission_counter = 1
	   	START_NEW_SCRIPT wuzi_mission_loop //"Wuzi mission 3" PAUL D //Airport Pickup
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT wuziX wuziY wuziZ wuzi_blip_icon wuzi_contact_blip
		goto_thereX = wuziX_d 
		goto_thereY = wuziY 
		goto_thereZ = wuziZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_wuzi_mission_counter = 2
	   	START_NEW_SCRIPT wuzi_mission_loop //"Wuzi mission 4" JUDITH //Cross Country Decoy
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT wuziX wuziY wuziZ wuzi_blip_icon wuzi_contact_blip
		goto_thereX = wuziX_d 
		goto_thereY = wuziY 
		goto_thereZ = wuziZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_wuzi_mission_counter = 3
	   	START_NEW_SCRIPT wuzi_mission_loop //"Wuzi mission 5" //JUDITH //Swimming with the Sharks
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT wuziX wuziY wuziZ wuzi_blip_icon wuzi_contact_blip
		goto_thereX = wuziX_d 
		goto_thereY = wuziY 
		goto_thereZ = wuziZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_wuzi_mission_counter = 4
		START_NEW_SCRIPT wuzi_mission_loop //"Wuzi mission 7" //ANDY //Storm Freighter
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT wuziX wuziY wuziZ wuzi_blip_icon wuzi_contact_blip
		goto_thereX = wuziX_d 
		goto_thereY = wuziY 
		goto_thereZ = wuziZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_synd_mission_counter = 0
		START_NEW_SCRIPT synd_mission_loop //"Syndicate mission 1" //CHRIS M //Recon
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ garage_blip_icon garage_contact_blip
		goto_thereX = garageX_d 
		goto_thereY = garageY 
		goto_thereZ = garageZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_synd_mission_counter = 1
		syn2_mission_attempts = 0
		START_NEW_SCRIPT synd_mission_loop //"Syndicate mission 2"	//NEIL //Blonde Ambition
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ garage_blip_icon garage_contact_blip
		goto_thereX = garageX_d 
		goto_thereY = garageY 
		goto_thereZ = garageZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number //"Syndicate mission 3" JUDITH //Bike Bust Up
		flag_synd_mission_counter = 3
		START_NEW_SCRIPT Synd_mission_loop // TEST STUFF
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT syndX syndY syndZ synd_blip_icon synd_contact_blip
		goto_thereX = syndX_d 
		goto_thereY = syndY 
		goto_thereZ = syndZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number 
		flag_synd_mission_counter = 4
		START_NEW_SCRIPT Synd_mission_loop //"Syndicate mission 4" KEV W //Hostage
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT syndX syndY syndZ synd_blip_icon synd_contact_blip
		goto_thereX = syndX_d 
		goto_thereY = syndY 
		goto_thereZ = syndZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_synd_mission_counter = 5
		START_NEW_SCRIPT Synd_mission_loop //"Syndicate mission 5"	//JUDITH //Outrider
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ synd_blip_icon synd_contact_blip
		goto_thereX = garageX_d 
		goto_thereY = garageY 
		goto_thereZ = garageZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_synd_mission_counter = 6
		flag_scrash_mission_counter = 2
		START_NEW_SCRIPT synd_mission_loop //"Syndicate mission 6"	//PAUL //Call to arms
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ garage_blip_icon garage_contact_blip
		goto_thereX = garageX_d 
		goto_thereY = garageY 
		goto_thereZ = garageZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_mob_sanfran[7] = 1
		flag_synd_mission_counter = 7
		START_NEW_SCRIPT synd_mission_loop //"Syndicate mission 7"	//ANDY //Pier69
		//ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ garage_blip_icon garage_contact_blip
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT -1717.05 1280.91 6.23 garage_blip_icon garage_contact_blip
		goto_thereX = -1713.6208  
		goto_thereY = 1282.9164  
		goto_thereZ = 6.1875 
		debug_heading = 125.4326
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_synd_mission_counter = 8
		START_NEW_SCRIPT synd_mission_loop //"Syndicate mission 8"	//STEVE T //Toreno's Plane
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ garage_blip_icon garage_contact_blip
		goto_thereX = garageX_d 
		goto_thereY = garageY 
		goto_thereZ = garageZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_synd_mission_counter = 9
		flag_wuzi_mission_counter = 5
		START_NEW_SCRIPT synd_mission_loop //"Syndicate mission 9"	//PAUL D	//Los Cabras Crack Lab
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ garage_blip_icon garage_contact_blip
		goto_thereX = garageX_d 
		goto_thereY = garageY 
		goto_thereZ = garageZ
		debug_heading = 90.0
	ENDIF


		debug_number ++
	IF button_pressed_main = debug_number
		flag_steal_mission_counter = 0
		START_NEW_SCRIPT steal_mission_loop //"steal mission 1" //CHRIS M //Follow Steal
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT stealX stealY stealZ steal_blip_icon steal_contact_blip
		goto_thereX = stealX_d 
		goto_thereY = stealY 
		goto_thereZ = stealZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_steal_mission_counter = 1
		START_NEW_SCRIPT steal_mission_loop //"steal mission 2" //ANDY	//Steal from Car Showroom
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT stealX stealY stealZ steal_blip_icon steal_contact_blip
		goto_thereX = stealX_d 
		goto_thereY = stealY 
		goto_thereZ = stealZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_steal_mission_counter = 2
		START_NEW_SCRIPT steal_mission_loop //"steal mission 4" //NEIL	//Car Crane
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT stealX stealY stealZ steal_blip_icon steal_contact_blip
		goto_thereX = stealX_d 
		goto_thereY = stealY 
		goto_thereZ = stealZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_steal_mission_counter = 3
		START_NEW_SCRIPT steal_mission_loop //"steal mission 5" //ANDY	//Stinger Trap
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT stealX stealY stealZ steal_blip_icon steal_contact_blip
		goto_thereX = stealX_d 
		goto_thereY = stealY 
		goto_thereZ = stealZ
		debug_heading = 90.0
	ENDIF


		debug_number ++
	IF button_pressed_main = debug_number
		flag_zero_mission_counter = 0
		START_NEW_SCRIPT zero_mission_loop //"zero mission 1" //KEV W //Scramble
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT zeroX zeroY zeroZ zero_blip_icon zero_contact_blip
		goto_thereX = zeroX_d 
		goto_thereY = zeroY 
		goto_thereZ = zeroZ
		debug_heading = 260.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_zero_mission_counter = 1
		START_NEW_SCRIPT zero_mission_loop //"zero mission 2" //KEV W //Rolling Thunder
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT zeroX zeroY zeroZ zero_blip_icon zero_contact_blip
		goto_thereX = zeroX_d 
		goto_thereY = zeroY 
		goto_thereZ = zeroZ
		debug_heading = 260.0
	ENDIF
	/*
		debug_number ++
	IF button_pressed_main = debug_number
		flag_zero_mission_counter = 2
		START_NEW_SCRIPT zero_mission_loop //"zero mission 3" //KEV W //Tanked Up
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT zeroX zeroY zeroZ zero_blip_icon zero_contact_blip
		goto_thereX = zeroX_d 
		goto_thereY = zeroY 
		goto_thereZ = zeroZ
		debug_heading = 260.0
	ENDIF
	*/
		debug_number ++
	IF button_pressed_main = debug_number
		flag_zero_mission_counter = 2
		START_NEW_SCRIPT zero_mission_loop //"zero mission 4" //NEIL //Return Fire
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT zeroX zeroY zeroZ zero_blip_icon zero_contact_blip
		goto_thereX = zeroX_d 
		goto_thereY = zeroY 
		goto_thereZ = zeroZ
		debug_heading = 260.0
	ENDIF

		debug_number ++
	IF button_pressed_main = debug_number // ANDY //The Tests
		flag_garage_mission_counter = 1
		START_NEW_SCRIPT trace_mission_loop // TEST STUFF
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT testsX testsY testsZ RADAR_SPRITE_SCHOOL dschool_contact_blip
		//SET_BLIP_ENTRY_EXIT	dschool_contact_blip -2026.4767 -99.8392 10.0
		CHANGE_BLIP_DISPLAY dschool_contact_blip BLIP_ONLY
		goto_thereX = -2025.58  
		goto_thereY = -93.66  
		goto_thereZ = 34.17
		debug_heading = 180.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		driving_test_passed = 1
		START_NEW_SCRIPT trace_mission_loop //"trace mission 1" //CHRIS R //Race Tournament
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT traceX[0] traceY[0] traceZ[0] trace_blip_icon trace_contact_blip[0]
		goto_thereX = traceX_d[0] 
		goto_thereY = traceY[0] 
		goto_thereZ = traceZ[0]
		debug_heading = 270.0
	ENDIF
	// **********************************DESERT
		debug_number ++
	IF button_pressed_main = debug_number
		flag_desert_mission_counter = 0
		START_NEW_SCRIPT desert_mission_loop //"desert mission 1" //IMRAN //Monster Mash
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT desertX desertY desertZ desert_blip_icon desert_contact_blip
		created_save_blips = 0
		REMOVE_PICKUP grove_save_pickup[16]
		CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[16] save_pickupY[16] save_pickupZ[16] grove_save_pickup[16] //TORENOS RANCH//remove
		number_of_save_icons = 17
		REMOVE_IPL Barriers2
		SET_INT_STAT CITIES_PASSED 2
		SET_MAX_WANTED_LEVEL 6
		flag_mob_sanfran[5] = 0
		flag_mob_sanfran[7] = 0
		TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME MOB_VEG
		START_NEW_SCRIPT cell_phone_vegas
		TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME MOB_SF
		START_NEW_SCRIPT cell_phone_sanfran
		TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME MOB_RAN
		START_NEW_SCRIPT cell_phone_random
		SWITCH_ENTRY_EXIT ammun1 TRUE
		SWITCH_ENTRY_EXIT ammun2 TRUE
		SWITCH_ENTRY_EXIT ammun3 TRUE
		SWITCH_ENTRY_EXIT ammun4 TRUE
		SWITCH_ENTRY_EXIT ammun5 TRUE

		SWITCH_ENTRY_EXIT barbers TRUE 
		SWITCH_ENTRY_EXIT barber2 TRUE 
		SWITCH_ENTRY_EXIT barber3 TRUE 
		SWITCH_ENTRY_EXIT FDpiza TRUE
		SWITCH_ENTRY_EXIT fdchick TRUE
		SWITCH_ENTRY_EXIT fdburg TRUE

		SWITCH_ENTRY_EXIT cschp	TRUE
		SWITCH_ENTRY_EXIT cssprt TRUE
		SWITCH_ENTRY_EXIT lacs1	TRUE
		SWITCH_ENTRY_EXIT clothgp TRUE
		SWITCH_ENTRY_EXIT csdesgn TRUE
		SWITCH_ENTRY_EXIT csexl	TRUE
		
		SWITCH_ENTRY_EXIT gym1 TRUE
		SWITCH_ENTRY_EXIT gym2 TRUE
		SWITCH_ENTRY_EXIT gym3 TRUE
		goto_thereX = desertX_d 
		goto_thereY = desertY 
		goto_thereZ = desertZ
		debug_heading = 275.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_desert_mission_counter = 1
		flag_mob_sanfran[5] = 1
		START_NEW_SCRIPT desert_mission_loop //"desert mission 2" //ANDY //Jump linerunner //HIGHJACK
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT desertX desertY desertZ desert_blip_icon desert_contact_blip
		goto_thereX = desertX_d 
		goto_thereY = desertY 
		goto_thereZ = desertZ
		debug_heading = 275.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_desert_mission_counter = 2
		flag_mob_sanfran[7] = 1
		START_NEW_SCRIPT desert_mission_loop //"desert mission 3" //NEIL //Contraband  //INTERDICTION
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT desertX desertY desertZ desert_blip_icon desert_contact_blip
		goto_thereX = desertX_d 
		goto_thereY = desertY 
		goto_thereZ = desertZ
		debug_heading = 275.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_desert_mission_counter = 3
		START_NEW_SCRIPT desert_mission_loop //"desert mission 4" //CRAIG //Learning to Fly
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT desertX desertY desertZ desert_blip_icon desert_contact_blip
		goto_thereX = desertX_d 
		goto_thereY = desertY 
		goto_thereZ = desertZ
		debug_heading = 275.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_desert_mission_counter = 4
		START_NEW_SCRIPT pilot_school_loop //"desert mission 5" //JUDITH //Pilot School
		START_NEW_SCRIPT desert_mission_loop
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT pilotx piloty pilotz RADAR_SPRITE_SCHOOL pilot_contact_blip
		CHANGE_BLIP_DISPLAY pilot_contact_blip BLIP_ONLY
		goto_thereX = 413.5500   
		goto_thereY = 2533.5701   
		goto_thereZ = 18.1484
		debug_heading = 275.0	
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_desert_mission_counter = 5
		pilot_test_passed = 1
		START_NEW_SCRIPT desert_mission_loop //"desert mission 6" //CHRISR //N.O.E.
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT desert2X desert2Y desert2Z desert_blip_icon desert_contact_blip
		goto_thereX = desert2X_d 
		goto_thereY = desert2Y 
		goto_thereZ = desert2Z
		debug_heading = 275.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_desert_mission_counter = 6
		START_NEW_SCRIPT desert_mission_loop //"desert mission 9" //C3 Shootout
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT desert2X desert2Y desert2Z desert_blip_icon desert_contact_blip
		goto_thereX = desert2X_d 
		goto_thereY = desert2Y 
		goto_thereZ = desert2Z
		debug_heading = 275.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_desert_mission_counter = 7
		START_NEW_SCRIPT desert_mission_loop //"desert mission 8" //IMRAN //Steal Jetpack
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT desert2X desert2Y desert2Z desert_blip_icon desert_contact_blip
		goto_thereX = desert2X_d 
		goto_thereY = desert2Y 
		goto_thereZ = desert2Z
		debug_heading = 275.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_mob_vegas[11] = 1
		flag_desert_mission_counter = 8
		START_NEW_SCRIPT desert_mission_loop //"desert mission 10" //Train Heist
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT desert2X desert2Y desert2Z desert_blip_icon desert_contact_blip
		goto_thereX = desert2X_d 
		goto_thereY = desert2Y 
		goto_thereZ = desert2Z
		debug_heading = 275.0
	ENDIF
	// **********************************VEGAS
		debug_number ++
	IF button_pressed_main = debug_number
		flag_casino_mission_counter = 0
		START_NEW_SCRIPT casino_mission_loop //"casino mission 1" //CHRIS M	 //Wind up
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT casinoX casinoY casinoZ casino_blip_icon casino_contact_blip
		SET_BLIP_ENTRY_EXIT	casino_contact_blip 2024.3904 1008.6202 20.0
		created_save_blips = 0
		REMOVE_PICKUP grove_save_pickup[17]
		CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[17] save_pickupY[17] save_pickupZ[17] grove_save_pickup[17] //TRIAD CASINO//remove
		number_of_save_icons = 18
		REMOVE_BLIP save_house_blip[17]
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT save_pickupX[17] save_pickupY[17] save_pickupZ[17] RADAR_SPRITE_SAVEHOUSE save_house_blip[17]
		CHANGE_BLIP_DISPLAY save_house_blip[17] BLIP_ONLY
		SET_INT_STAT CITIES_PASSED 2
		SET_MAX_WANTED_LEVEL 6
		flag_mob_vegas[10] = 1
		flag_desert_mission_counter = 9
		TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME MOB_VEG
		START_NEW_SCRIPT cell_phone_vegas
		TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME MOB_RAN
		START_NEW_SCRIPT cell_phone_random
		SWITCH_ENTRY_EXIT ammun1 TRUE
		SWITCH_ENTRY_EXIT ammun2 TRUE
		SWITCH_ENTRY_EXIT ammun3 TRUE
		SWITCH_ENTRY_EXIT ammun4 TRUE
		SWITCH_ENTRY_EXIT ammun5 TRUE

		SWITCH_ENTRY_EXIT barbers TRUE 
		SWITCH_ENTRY_EXIT barber2 TRUE 
		SWITCH_ENTRY_EXIT barber3 TRUE 
		SWITCH_ENTRY_EXIT FDpiza TRUE
		SWITCH_ENTRY_EXIT fdchick TRUE
		SWITCH_ENTRY_EXIT fdburg TRUE

		SWITCH_ENTRY_EXIT cschp	TRUE
		SWITCH_ENTRY_EXIT cssprt TRUE
		SWITCH_ENTRY_EXIT lacs1	TRUE
		SWITCH_ENTRY_EXIT clothgp TRUE
		SWITCH_ENTRY_EXIT csdesgn TRUE
		SWITCH_ENTRY_EXIT csexl	TRUE
		
		SWITCH_ENTRY_EXIT gym1 TRUE
		SWITCH_ENTRY_EXIT gym2 TRUE
		SWITCH_ENTRY_EXIT gym3 TRUE
		goto_thereX = 2024.3904 
		goto_thereY = 1008.6202
		goto_thereZ = 9.8127
		debug_heading = 90.0
	ENDIF
		debug_number ++	
	IF button_pressed_main = debug_number
		flag_casino_mission_counter = 1
		START_NEW_SCRIPT casino_mission_loop //"casino mission 2" //PAUL  //Kickstart Quarry
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT casinoX casinoY casinoZ casino_blip_icon casino_contact_blip
		SET_BLIP_ENTRY_EXIT	casino_contact_blip 2024.3904 1008.6202 20.0
		goto_thereX = 2024.3904 
		goto_thereY = 1008.6202
		goto_thereZ = 9.8127
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_casino_mission_counter = 2
		START_NEW_SCRIPT casino_mission_loop //"casino mission 3" //STEVE //Fake Chips
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT casinoX casinoY casinoZ casino_blip_icon casino_contact_blip
		SET_BLIP_ENTRY_EXIT	casino_contact_blip 2024.3904 1008.6202 20.0
		goto_thereX = 2024.3904 
		goto_thereY = 1008.6202
		goto_thereZ = 9.8127
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_casino_mission_counter = 3
		START_NEW_SCRIPT casino_mission_loop //"casino mission 4" //DAVE //Paul & Maccer
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT 2026.6028 1007.7353 9.8127 casino_blip_icon casino_contact_blip
		//SET_BLIP_ENTRY_EXIT	casino_contact_blip 2187.2856 1678.4614	20.0
		goto_thereX = 2032.0428  
		goto_thereY = 1006.4272 
		goto_thereZ = 9.8203
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_mob_vegas[2] = 1
		flag_casino_mission_counter = 4
		START_NEW_SCRIPT casino_mission_loop //"casino mission 5" //IMRAN //Hospital Hi Jinx 
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT heistX heistY heistZ heist_blip_icon casino_contact_blip
		SET_BLIP_ENTRY_EXIT	casino_contact_blip 2187.2856 1678.4614	20.0
		goto_thereX = 2187.2856  
		goto_thereY = 1678.4614  
		goto_thereZ = 10.1055
		debug_heading = 270.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_casino_mission_counter = 5
		START_NEW_SCRIPT casino_mission_loop //"casino mission 6" //SIMON //Abattoir
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT heistX heistY heistZ heist_blip_icon casino_contact_blip
		SET_BLIP_ENTRY_EXIT	casino_contact_blip 2187.2856 1678.4614 20.0
		goto_thereX = 2187.2856 
		goto_thereY = 1678.4614
		goto_thereZ = 10.1055
		debug_heading = 270.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_casino_mission_counter = 6
		START_NEW_SCRIPT casino_mission_loop //"casino mission 7" //IMRAN //Clear & Present Danger
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT casinoX casinoY casinoZ casino_blip_icon casino_contact_blip
		SET_BLIP_ENTRY_EXIT	casino_contact_blip 2026.6028 1007.7353 20.0
		goto_thereX = 2024.3904 
		goto_thereY = 1008.6202
		goto_thereZ = 9.8127
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_mob_vegas[3] = 1
		flag_casino_mission_counter = 7
		START_NEW_SCRIPT casino_mission_loop //"casino mission 9" //SIMON //Freefalln
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT heistX heistY heistZ heist_blip_icon casino_contact_blip
		SET_BLIP_ENTRY_EXIT	casino_contact_blip 2187.2856 1678.4614 20.0
		goto_thereX = 2187.2856 
		goto_thereY = 1678.4614
		goto_thereZ = 10.1055
		debug_heading = 270.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_casino_mission_counter = 8
		flag_vcrash_mission_counter = 2
		flag_mob_vegas[4] = 1
		START_NEW_SCRIPT casino_mission_loop //"casino mission 10" //WILLIE //St Marks Bistro
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT heistX heistY heistZ heist_blip_icon casino_contact_blip
		SET_BLIP_ENTRY_EXIT	casino_contact_blip 2187.2856 1678.4614 20.0
		goto_thereX = 2187.2856 
		goto_thereY = 1678.4614
		goto_thereZ = 10.1055
		debug_heading = 270.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_vcrash_mission_counter = 0
		START_NEW_SCRIPT vcrash_mission_loop //"vcrash mission 1" //CHRIS R //Uber Chase
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT vcrashX vcrashY vcrashZ crash_blip_icon vcrash_contact_blip
		goto_thereX = vcrashX_d 
		goto_thereY = vcrashY 
		goto_thereZ = vcrashZ
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_vcrash_mission_counter = 1
		flag_mob_vegas[0] = 1
		START_NEW_SCRIPT vcrash_mission_loop //"vcrash mission 2" //IMRAN //High Noon
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT -378.7596 2235.8594 41.4288 crash_blip_icon vcrash_contact_blip
		goto_thereX = -380.7596  
		goto_thereY = 2235.8594  
		goto_thereZ = 41.4288
		debug_heading = 90.0
	ENDIF
		debug_number ++


RETURN



debug_page2: //MISSIONS PAGE2**************************************************************************************************

	FAIL_CURRENT_MISSION
	GOSUB terminate_all_scripts


		debug_number = 0

	IF button_pressed_main = debug_number
		flag_doc_mission_counter = 0
		START_NEW_SCRIPT doc_mission_loop //"doc mission 2" //PAUL	 //Jumper
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT docX docY docZ doc_blip_icon doc_contact_blip
		goto_thereX = docX_d 
		goto_thereY = docY 
		goto_thereZ = docZ
		debug_heading = 270.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_heist_mission_counter = 0
		START_NEW_SCRIPT heist_mission_loop //"heist mission 1" //STEVE //Photo Plans
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT TheheistX TheheistY TheheistZ Theheist_blip_icon Theheist_contact_blip
		SET_BLIP_ENTRY_EXIT Theheist_contact_blip 2024.3904 1008.6202 20.0
		goto_thereX = 2024.3904  
		goto_thereY = 1008.6202  
		goto_thereZ = 9.8127
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_heist_mission_counter = 1
		START_NEW_SCRIPT heist_mission_loop //"heist mission 3" //DAVE //Girlfiend 
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT TheheistX TheheistY TheheistZ Theheist_blip_icon Theheist_contact_blip
		SET_BLIP_ENTRY_EXIT Theheist_contact_blip 2024.3904 1008.6202 20.0
		goto_thereX = 2024.3904 
		goto_thereY = 1008.6202
		goto_thereZ = 9.8127
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_heist_mission_counter = 2
		START_NEW_SCRIPT heist_mission_loop //"heist mission 2" //DAVE //Dam charges
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT TheheistX TheheistY TheheistZ Theheist_blip_icon Theheist_contact_blip
		SET_BLIP_ENTRY_EXIT Theheist_contact_blip 2024.3904 1008.6202 20.0
		goto_thereX = 2024.3904 
		goto_thereY = 1008.6202
		goto_thereZ = 9.8127
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_heist_mission_counter = 3
		START_NEW_SCRIPT heist_mission_loop //"heist mission 4" //CHRIS R //Street Hawk
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT TheheistX TheheistY TheheistZ Theheist_blip_icon Theheist_contact_blip
		SET_BLIP_ENTRY_EXIT Theheist_contact_blip 2024.3904 1008.6202 20.0
		goto_thereX = 2024.3904 
		goto_thereY = 1008.6202
		goto_thereZ = 9.8127
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_heist_mission_counter = 4
		START_NEW_SCRIPT heist_mission_loop //"heist mission 5" //WILLIE //Steal Heli Magnate
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT TheheistX TheheistY TheheistZ Theheist_blip_icon Theheist_contact_blip
		SET_BLIP_ENTRY_EXIT Theheist_contact_blip 2024.3904 1008.6202 20.0
		goto_thereX = 2024.3904 
		goto_thereY = 1008.6202
		goto_thereZ = 9.8127
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_heist_mission_counter = 5
		flag_casino_mission_counter = 9
		keycard_aquired_from_millie = 1
		flag_mob_vegas[6] = 1
		START_NEW_SCRIPT heist_mission_loop //"heist mission 9" //PAUL D //The Heist
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT TheheistX TheheistY TheheistZ Theheist_blip_icon Theheist_contact_blip
		SET_BLIP_ENTRY_EXIT Theheist_contact_blip 2024.3904 1008.6202 20.0
		goto_thereX = 2024.3904 
		goto_thereY = 1008.6202
		goto_thereZ = 9.8127
		debug_heading = 90.0
	ENDIF
	// **********************************LA2
		debug_number ++
	IF button_pressed_main = debug_number
		flag_mansion_mission_counter = 0
		START_NEW_SCRIPT mansion_mission_loop //"mansion mission 1" //SIMON //Take Back G's Mansion
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT casinoX casinoY casinoZ casino_blip_icon casino_contact_blip
		SET_BLIP_ENTRY_EXIT casino_contact_blip 2024.3904 1008.6202 20.0
		TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME MOBLA2
		START_NEW_SCRIPT cell_phone_LA2
		TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME MOB_RAN
		START_NEW_SCRIPT cell_phone_random
		SET_INT_STAT CITIES_PASSED 3
		SET_MAX_WANTED_LEVEL 6
		SWITCH_ENTRY_EXIT ammun1 TRUE
		SWITCH_ENTRY_EXIT ammun2 TRUE
		SWITCH_ENTRY_EXIT ammun3 TRUE
		SWITCH_ENTRY_EXIT ammun4 TRUE
		SWITCH_ENTRY_EXIT ammun5 TRUE

		SWITCH_ENTRY_EXIT barbers TRUE 
		SWITCH_ENTRY_EXIT barber2 TRUE 
		SWITCH_ENTRY_EXIT barber3 TRUE 
		SWITCH_ENTRY_EXIT FDpiza TRUE
		SWITCH_ENTRY_EXIT fdchick TRUE
		SWITCH_ENTRY_EXIT fdburg TRUE

		SWITCH_ENTRY_EXIT cschp	TRUE
		SWITCH_ENTRY_EXIT cssprt TRUE
		SWITCH_ENTRY_EXIT lacs1	TRUE
		SWITCH_ENTRY_EXIT clothgp TRUE
		SWITCH_ENTRY_EXIT csdesgn TRUE
		SWITCH_ENTRY_EXIT csexl	TRUE
		
		SWITCH_ENTRY_EXIT gym1 TRUE
		SWITCH_ENTRY_EXIT gym2 TRUE
		SWITCH_ENTRY_EXIT gym3 TRUE
		goto_thereX = 2024.3904 
		goto_thereY = 1008.6202
		goto_thereZ = 9.8127
		debug_heading = 90.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_mansion_mission_counter = 1
		START_NEW_SCRIPT mansion_mission_loop //"mansion mission 2" //CRAIG //Steal Harrier
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT mansionX mansionY mansionZ mansion_blip_icon mansion_contact_blip
		goto_thereX = mansionX 
		goto_thereY = mansionX_d
		goto_thereZ = mansionZ
		debug_heading = 180.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_mansion_mission_counter = 2
		START_NEW_SCRIPT mansion_mission_loop //"mansion mission 3" //PAUL //Pick up sweet
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT mansionX mansionY mansionZ mansion_blip_icon mansion_contact_blip
		goto_thereX = mansionX 
		goto_thereY = mansionX_d
		goto_thereZ = mansionZ
		debug_heading = 180.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_mansion_mission_counter = 3
		START_NEW_SCRIPT mansion_mission_loop //"mansion mission 4" //SIMON //Take down MC Strap
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT mansionX mansionY mansionZ mansion_blip_icon mansion_contact_blip
		goto_thereX = mansionX 
		goto_thereY = mansionX_d
		goto_thereZ = mansionZ
		debug_heading = 180.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_grove_mission_counter = 0
		START_NEW_SCRIPT grove_mission_loop //"grove mission 1" //WILLIE //Beat Down on b Dup
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT introX introY introZ intro_blip_icon intro_contact_blip
		goto_thereX = introX 
		goto_thereY = introY_d 
		goto_thereZ = introZ
		debug_heading = 180.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_grove_mission_counter = 1
		START_NEW_SCRIPT grove_mission_loop //"grove mission 2" //PAUL //Grove 4 life
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip
		goto_thereX = sweetX_d 
		goto_thereY = sweetY 
		goto_thereZ = sweetZ
		debug_heading = 270.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_riot_mission_counter = 0
		START_NEW_SCRIPT riot_mission_loop //"riot mission 1" //CRAIG //RIOT!
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT mansionX mansionY mansionZ mansion_blip_icon mansion_contact_blip
		goto_thereX = mansionX 
		goto_thereY = mansionX_d
		goto_thereZ = mansionZ
		debug_heading = 180.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_riot_mission_counter = 1
		SET_INT_STAT RESPECT_TOTAL 100
		START_NEW_SCRIPT riot_mission_loop //"riot mission 2" //ANDY //DESPERADOS
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip
		SET_INT_STAT RESPECT_TOTAL 1000
		goto_thereX = sweetX_d 
		goto_thereY = sweetY 
		goto_thereZ = sweetZ
		debug_heading =	260.0 
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_riot_mission_counter = 2
		flag_mob_LA2[2] = 1
		flag_mob_LA2[3] = 1
		finaleB_played_first_time_round = 0
		SET_LA_RIOTS ON
		START_NEW_SCRIPT riot_mission_loop //"riot mission 4" //IMRAN //CARTER BLOCK
		ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip
		goto_thereX = sweetX_d 
		goto_thereY = sweetY 
		goto_thereZ = sweetZ
		debug_heading =	260.0
	ENDIF
		debug_number ++

RETURN


debug_page3: // **********************************CUTSCENES**********************************************************************

	FAIL_CURRENT_MISSION
	GOSUB terminate_all_scripts

	debug_number = 0
	cutscene_index = 0


	check_cutscene1:

	IF button_pressed_main = debug_number
		PRINT_BIG ( $cutscene_name[cutscene_index] ) 2000 4 //INTRO - cut1 (PROLOG)
		$new_cut = $cutscene_file[cutscene_index]
		visible_area = cut_visible_area[cutscene_index]
		$cuttext = $cutscene_text[cutscene_index]
	ENDIF
		debug_number ++

	cutscene_index ++
	IF cutscene_index < 84
		GOTO check_cutscene1	
	ENDIF

RETURN


debug_page4: // **********************************CUTSCENES**********************************************************************

	FAIL_CURRENT_MISSION
	GOSUB terminate_all_scripts

	debug_number = 0
	cutscene_index = 0


	check_cutscene2:

	IF button_pressed_main = debug_number
		PRINT_BIG ( $cutscene_name2[cutscene_index] ) 2000 4 
		$new_cut = $cutscene_file2[cutscene_index]
		visible_area = cut_visible_area2[cutscene_index]
		$cuttext = $cutscene_text2[cutscene_index]
	ENDIF
		debug_number ++

	cutscene_index ++
	IF cutscene_index < 75
		GOTO check_cutscene2	
	ENDIF

RETURN

debug_page5: // **********************************CUTSCENES**********************************************************************

	FAIL_CURRENT_MISSION
	GOSUB terminate_all_scripts

	debug_number = 0
	cutscene_index = 0


	check_cutscene3:

	IF button_pressed_main = debug_number
		PRINT_BIG ( $cutscene_name3[cutscene_index] ) 2000 4 
		$new_cut = $cutscene_file3[cutscene_index]
		visible_area = cut_visible_area3[cutscene_index]
	ENDIF
		debug_number ++

	cutscene_index ++
	IF cutscene_index < 23
		GOTO check_cutscene3	
	ENDIF

RETURN

debug_page6: //MISC	*********************************************************************************************************************

	FAIL_CURRENT_MISSION
	GOSUB terminate_all_scripts

		debug_number = 0
				
	IF button_pressed_main = debug_number	  //100!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
		flag_mob_la1[6] = 1
	    switch_the_gym_interiors_off = 0
		PRINT_BIG ( GYM1_A ) 1000 4 //"GYM"
		goto_thereX = 2224.5811      
		goto_thereY = -1721.1486     
		goto_thereZ = 12.5584 
		debug_heading = 271.2687
		SWITCH_ENTRY_EXIT gym1 TRUE
		SWITCH_ENTRY_EXIT gym2 TRUE
		SWITCH_ENTRY_EXIT gym3 TRUE
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_mob_la1[6] = 1
	    switch_the_gym_interiors_off = 0
		PRINT_BIG ( GYM1_B ) 1000 4 //Vegas Gym 
		goto_thereX = 1963.85             
		goto_thereY = 2294.82        
		goto_thereZ = 15.45 
		debug_heading = 275.0
		SWITCH_ENTRY_EXIT gym1 TRUE
		SWITCH_ENTRY_EXIT gym2 TRUE
		SWITCH_ENTRY_EXIT gym3 TRUE
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		flag_mob_la1[6] = 1
	    switch_the_gym_interiors_off = 0
		PRINT_BIG ( GYM1_C ) 1000 4 //San Fran Gym 
		goto_thereX = -2265.9006              
		goto_thereY = -155.7526         
		goto_thereZ = 34.3047 
		debug_heading = 85.0
		SWITCH_ENTRY_EXIT gym1 TRUE
		SWITCH_ENTRY_EXIT gym2 TRUE
		SWITCH_ENTRY_EXIT gym3 TRUE
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( CHICN ) 1000 4 //"Chicken drive through"  
		goto_thereX = 2427.2546     
		goto_thereY = -1517.8105     
		goto_thereZ = 23.3873 
		debug_heading = 42.6546
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( BURGERS ) 1000 4 //"BURGER SHOP"  
		goto_thereX = 813.0       
		goto_thereY = -1630.0      
		goto_thereZ = 13.0
		debug_heading = 322.156
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( PIZZAS ) 1000 4 //"PIZZA SHOP"  
		goto_thereX = 2102.083  
		goto_thereY = -1805.798      
		goto_thereZ = 12.594
		debug_heading = 0.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( CLOTHES ) 1000 4 //"LA Clothes Shop"  
		goto_thereX = 2112.0     
		goto_thereY = -1214.0     
		goto_thereZ = 23.0 
		debug_heading = 0.0
	ENDIF
		debug_number ++
	 IF button_pressed_main = debug_number
		PRINT_BIG ( BARBER ) 1000 4 //"Barber Shop"  
		goto_thereX = 2074.37      
		goto_thereY = -1800.72      
		goto_thereZ = 12.56  
		debug_heading = 90.90
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( AMMULA ) 1000 4 //"LA AmmuNation"  
		goto_thereX = 1364.394     
		goto_thereY = -1279.724     
		goto_thereZ = 12.59 
		debug_heading = 270.0
	ENDIF
	 debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( CARDB1 ) 1000 4 //"Car Mods LA"    
		goto_thereX = 1053.4816    
		goto_thereY = -1039.2621   
		goto_thereZ = 30.9710 
		debug_heading =	47.3931
		stop_gargae_for_neil = 0
	ENDIF
	 debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( CARDB2 ) 1000 4 //"Car Mods lowrider"    
		goto_thereX = 2643.3457     
		goto_thereY = -2007.2150    
		goto_thereZ = 12.3750  
		debug_heading =	187.5727
		stop_gargae_for_neil = 0
	ENDIF
	 debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( ROUL ) 1000 4 //"roulette" 
		goto_thereX = RouletteX      
		goto_thereY = RouletteY     
		goto_thereZ = RouletteZ
		change_area_code = 2
		debug_heading = 0.0
	ENDIF
	 debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( SLOTM ) 1000 4 //"slot machines" 
		goto_thereX = banditx      
		goto_thereY = bandity     
		goto_thereZ = banditz
		change_area_code = 4
		debug_heading = 170.4
	ENDIF
		 debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( WHEELO ) 1000 4 //wheel of fortune
		goto_thereX = poolX      
		goto_thereY = poolY     
		goto_thereZ = poolZ
		change_area_code = 1
		debug_heading = 0.0
	ENDIF  

	 debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( BJACK ) 1000 4 //black jack 
		goto_thereX = RouletteX      
		goto_thereY = RouletteY     
		goto_thereZ = RouletteZ
		change_area_code = 2
		debug_heading = 0.0
	ENDIF

	 debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( OTB ) 1000 4 //"OTB" 
		goto_thereX = otbx      
		goto_thereY = otby     
		goto_thereZ = otbz
		change_area_code = 3
		debug_heading = 0.0
	ENDIF
	 debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( POOL ) 1000 4 //"Pool" 
		goto_thereX = poolX      
		goto_thereY = poolY     
		goto_thereZ = poolZ
		change_area_code = 1
		debug_heading = 0.0
	ENDIF
	 debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( BBALL ) 1000 4 //"BASKETBALL" 
		goto_thereX = basketballx      
		goto_thereY = basketbally     
		goto_thereZ = basketballz
		bball_unlocked = 1
		debug_heading = 0.0
	ENDIF
	 debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( LOWR ) 1000 4 //"Lowrider"
		goto_thereX =   1815.4730 //2059.8159    
		goto_thereY =   -1926.1912 //-1901.9498    
		goto_thereZ = 	12.5461 //12.5469 
		debug_heading = 90.0 //348.6162
		lowrider_minigame_unlocked = 1
		// create a lowrider car so you don't need to find one
		REQUEST_MODEL SAVANNA
		WHILE NOT HAS_MODEL_LOADED SAVANNA
			WAIT 0
		ENDWHILE		   
		CREATE_CAR SAVANNA 1812.4294 -1891.6572 12.4062 car
		SET_CAR_HEADING car 90.0
		SET_CAR_HYDRAULICS car TRUE
		MARK_MODEL_AS_NO_LONGER_NEEDED SAVANNA
		MARK_CAR_AS_NO_LONGER_NEEDED car
	ENDIF
	 debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( CONS1 ) 1000 4 //"console game1"
		goto_thereX = 502.1866       
		goto_thereY = -4.1644      
		goto_thereZ = 999.6719 
		debug_heading = 177.5629
		change_area_code = 5
	ENDIF

	 debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( QUARRY ) 1000 4 //Quarry
		START_NEW_SCRIPT quarry_loop 
		goto_thereX = quarryX        
		goto_thereY = quarryY   
		goto_thereZ = quarryZ
		debug_heading = 90.0
	ENDIF

	 debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( TRUCK ) 1000 4 //Trucking
		START_NEW_SCRIPT trucking_loop 
		goto_thereX = truckX        
		goto_thereY = truckY   
		goto_thereZ = truckZ
		debug_heading = 0.0
	ENDIF
	 debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( BLOODB ) 1000 4 //Blood Bowl 
		goto_thereX = -2116.4280            
		goto_thereY = -441.3636       
		goto_thereZ = 34.5343
		debug_heading = 270.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( MOUNTN ) 1000 4 //"Mountian bike"
		SET_INT_STAT CYCLE_SKILL 601 
		goto_thereX = -2307.5000      
		goto_thereY = -1659.7040     
		goto_thereZ = 483.1310 
		debug_heading = 31.1541
	ENDIF
	 debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( PIMP ) 3000 4 //PIMPING 
		goto_thereX = pimpX          
		goto_thereY = pimpY_d     
		goto_thereZ = pimpZ
		debug_heading = 0.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( BIKES ) 1000 4 //Bike school 
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT bikesX bikesY bikesZ RADAR_SPRITE_SCHOOL bike_school_blip
		CHANGE_BLIP_DISPLAY bike_school_blip BLIP_ONLY
		START_NEW_SCRIPT bikes_school_loop
		goto_thereX = bikesX          
		goto_thereY = bikesY     
		goto_thereZ = bikesZ
		debug_heading = 0.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( BOATS ) 1000 4 //Boat school 
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT boatsX boatsY boatsZ RADAR_SPRITE_SCHOOL boat_school_blip
		CHANGE_BLIP_DISPLAY boat_school_blip BLIP_ONLY
		START_NEW_SCRIPT boats_school_loop
		goto_thereX = boatsX         
		goto_thereY = boatsY    
		goto_thereZ = boatsZ
		debug_heading = 0.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( COUR1 ) 1000 4 //CourierLA 
		goto_thereX = 1358.7238            
		goto_thereY = -1744.9690         
		goto_thereZ = 12.5625   
		debug_heading = 183.7364
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( COUR2 ) 1000 4 //CourierSF 
		goto_thereX = -2592.1309            
		goto_thereY = 61.8500         
		goto_thereZ = 3.3359   
		debug_heading = 349.2679
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( COUR3 ) 1000 4 //CourierLV 
		goto_thereX = 1887.8136           
		goto_thereY = 2099.3940        
		goto_thereZ = 10.0547  
		debug_heading = 179.4337
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( FREIGH ) 1000 4 //Frieght Trian 
		goto_thereX = 1722.8541             
		goto_thereY = -1968.9330        
		goto_thereZ = 13.1208  
		debug_heading = 216.5953
		SET_INT_STAT CITIES_PASSED 2
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( BURG ) 1000 4 //Burglary 
		goto_thereX = 2258.0            
		goto_thereY = -1796.0       
		goto_thereZ = 14.0 
		debug_heading = 74.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( KICK ) 1000 4 //Kickstart 
		goto_thereX = 1101.0336              
		goto_thereY = 1609.8754         
		goto_thereZ = 11.5546 
		debug_heading = 180.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( VALET ) 1000 4 //Valet 
//		START_NEW_SCRIPT valet_script
		valet_oddjob_opened = 1
		valet_unlocked = 1
		IF IS_PLAYER_PLAYING player1
            GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP Player1 valet valet CLOTHES_TEX_EXTRA1          
            BUILD_PLAYER_MODEL player1
        ENDIF
		goto_thereX = valetx             
		goto_thereY = valety        
		goto_thereZ = valetz 
		debug_heading = 74.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( SF_MODS ) 1000 4 //San Fran PP mods 
//		START_NEW_SCRIPT valet_script
		goto_thereX = -2710.7603               
		goto_thereY = 217.5245            
		goto_thereZ = 3.1797   
		debug_heading = 88.2793
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( IMPEXP ) 1000 4 //import/export 
		START_NEW_SCRIPT import_export_script
		import_export_is_active = 1
		disable_crane = 0
		goto_thereX = -1574.7085                
		goto_thereY = 132.0894             
		goto_thereZ = 2.5547   
		debug_heading = 88.2793
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( BMXODD ) 1000 4 //BMX 
		goto_thereX = 1944.5170                
		goto_thereY = -1367.8845             
		goto_thereZ = 17.5781    
		debug_heading = 189.0523
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( DANCEOD ) 1000 4 //DANCING 
		//START_NEW_SCRIPT Dance_minigame 488.0048 -14.0754 999.6797 180.0 DANCE_TRACK_HIPHOP DANCE_RANDOM_PARTNER
		goto_thereX = 492.0140                 
		goto_thereY = -20.6075              
		goto_thereZ = 999.6719     
		debug_heading = 30.5670
		change_area_code = 5
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( HOTRING ) 1000 4 //HOTRING 
		goto_thereX = 2691.2712                    
		goto_thereY = -1696.1896                 
		goto_thereZ = 9.2759     
		debug_heading = 200.0
	ENDIF


	 debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( TPLAY1 ) 1000 4 //2player Ram 
		goto_thereX = 1481.9551     
		goto_thereY = -1656.1458     
		goto_thereZ = 13.5469
		debug_heading = 0.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( TPLAY2 ) 1000 4 //2player bike 
		goto_thereX = 1196.4912       
		goto_thereY = 249.3210       
		goto_thereZ = 19.0618
		debug_heading = 0.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( TPLAY3 ) 1000 4 //2player cars 
		goto_thereX = -2102.8484       
		goto_thereY = 653.8868       
		goto_thereZ = 51.8671
		debug_heading = 0.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( TPLAY4 ) 1000 4 //2player heli 
		goto_thereX = -252.9156       
		goto_thereY = 2583.7788       
		goto_thereZ = 63.0703
		debug_heading = 0.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( TPLAY5 ) 1000 4 //2player peds 
		goto_thereX = 2510.6331       
		goto_thereY = 1207.9175       
		goto_thereZ = 10.3281
		debug_heading = 0.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( TPLAY6 ) 1000 4 //Run-around LS
		goto_thereX = 2069.3376       
		goto_thereY = -1556.9296       
		goto_thereZ = 12.9243
		debug_heading = 0.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( TPLAY7 ) 1000 4 //Run-around LV 
		goto_thereX = 2138.1689        
		goto_thereY = 1483.5952        
		goto_thereZ = 10.3203
		debug_heading = 0.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( TPLAY8 ) 1000 4 //Run-around SF 
		goto_thereX = -2197.6665        
		goto_thereY = 292.0621        
		goto_thereZ = 34.6230
		debug_heading = 0.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( TPLAY9 ) 1000 4 //Run-around DE 
		goto_thereX = -1520.8643        
		goto_thereY = 2608.2073        
		goto_thereZ = 55.3437
		debug_heading = 0.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( TPLAY10 ) 1000 4 //Run-around CO 
		goto_thereX = 711.0688        
		goto_thereY = -569.3774        
		goto_thereZ = 15.8359
		debug_heading = 0.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( BOTTY ) 1000 4 //Triathalon 
		SET_INT_STAT CYCLE_SKILL 500
		SET_INT_STAT STAMINA 500
		SET_INT_STAT CITIES_PASSED 3
		goto_thereX = 184.9203         
		goto_thereY = -1873.1268         
		goto_thereZ = 1.6817
		debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP3 ) 1000 4 //Property 
		goto_thereX = propertyX[3]         
		goto_thereY = propertyY[3]         
		goto_thereZ = propertyZ[3]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP4 ) 1000 4 //Property 
		goto_thereX = propertyX[4]         
		goto_thereY = propertyY[4]         
		goto_thereZ = propertyZ[4]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP5 ) 1000 4 //Property 
		goto_thereX = propertyX[5]         
		goto_thereY = propertyY[5]         
		goto_thereZ = propertyZ[5]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP6 ) 1000 4 //Property 
		goto_thereX = propertyX[6]         
		goto_thereY = propertyY[6]         
		goto_thereZ = propertyZ[6]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP7 ) 1000 4 //Property 
		goto_thereX = propertyX[7]         
		goto_thereY = propertyY[7]         
		goto_thereZ = propertyZ[7]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP8 ) 1000 4 //Property 
		goto_thereX = propertyX[8]         
		goto_thereY = propertyY[8]         
		goto_thereZ = propertyZ[8]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP9 ) 1000 4 //Property 
		goto_thereX = propertyX[9]         
		goto_thereY = propertyY[9]         
		goto_thereZ = propertyZ[9]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP10 ) 1000 4 //Property 
		goto_thereX = propertyX[10]         
		goto_thereY = propertyY[10]         
		goto_thereZ = propertyZ[10]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP11 ) 1000 4 //Property 
		goto_thereX = propertyX[11]         
		goto_thereY = propertyY[11]         
		goto_thereZ = propertyZ[11]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP12 ) 1000 4 //Property 
		goto_thereX = propertyX[12]         
		goto_thereY = propertyY[12]         
		goto_thereZ = propertyZ[12]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP13 ) 1000 4 //Property 
		goto_thereX = propertyX[13]         
		goto_thereY = propertyY[13]         
		goto_thereZ = propertyZ[13]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP14 ) 1000 4 //Property 
		goto_thereX = propertyX[14]         
		goto_thereY = propertyY[14]         
		goto_thereZ = propertyZ[14]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( PROP15 ) 1000 4 //Property 
		goto_thereX = propertyX[15]         
		goto_thereY = propertyY[15]         
		goto_thereZ = propertyZ[15]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( PROP16 ) 1000 4 //Property 
		goto_thereX = propertyX[16]         
		goto_thereY = propertyY[16]         
		goto_thereZ = propertyZ[16]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP17 ) 1000 4 //Property 
		goto_thereX = propertyX[17]         
		goto_thereY = propertyY[17]         
		goto_thereZ = propertyZ[17]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP18 ) 1000 4 //Property 
		goto_thereX = propertyX[18]         
		goto_thereY = propertyY[18]         
		goto_thereZ = propertyZ[18]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP19 ) 1000 4 //Property 
		goto_thereX = propertyX[19]         
		goto_thereY = propertyY[19]         
		goto_thereZ = propertyZ[19]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP20 ) 1000 4 //Property 
		goto_thereX = propertyX[20]         
		goto_thereY = propertyY[20]         
		goto_thereZ = propertyZ[20]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		PRINT_BIG ( PROP21 ) 1000 4 //Property 
		goto_thereX = propertyX[21]         
		goto_thereY = propertyY[21]         
		goto_thereZ = propertyZ[21]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP22 ) 1000 4 //Property 
		goto_thereX = propertyX[22]         
		goto_thereY = propertyY[22]         
		goto_thereZ = propertyZ[22]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP23 ) 1000 4 //Property 
		goto_thereX = propertyX[23]         
		goto_thereY = propertyY[23]         
		goto_thereZ = propertyZ[23]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP24 ) 1000 4 //Property 
		goto_thereX = propertyX[24]         
		goto_thereY = propertyY[24]         
		goto_thereZ = propertyZ[24]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP25 ) 1000 4 //Property 
		goto_thereX = propertyX[25]         
		goto_thereY = propertyY[25]         
		goto_thereZ = propertyZ[25]
		//debug_heading = 130.0
	ENDIF
	debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP25 ) 1000 4 //Property 
		goto_thereX = propertyX[26]         
		goto_thereY = propertyY[26]         
		goto_thereZ = propertyZ[26]
		//debug_heading = 130.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP25 ) 1000 4 //Property 
		goto_thereX = propertyX[27]         
		goto_thereY = propertyY[27]         
		goto_thereZ = propertyZ[27]
		//debug_heading = 130.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP25 ) 1000 4 //Property 
		goto_thereX = propertyX[28]         
		goto_thereY = propertyY[28]         
		goto_thereZ = propertyZ[28]
		//debug_heading = 130.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP25 ) 1000 4 //Property 
		goto_thereX = propertyX[29]         
		goto_thereY = propertyY[29]         
		goto_thereZ = propertyZ[29]
		//debug_heading = 130.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP25 ) 1000 4 //Property 
		goto_thereX = propertyX[30]         
		goto_thereY = propertyY[30]         
		goto_thereZ = propertyZ[30]
		//debug_heading = 130.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP25 ) 1000 4 //Property 
		goto_thereX = propertyX[31]         
		goto_thereY = propertyY[31]         
		goto_thereZ = propertyZ[31]
		//debug_heading = 130.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP25 ) 1000 4 //RACE 
		driving_test_passed = 1
		flag_mob_sanfran[3] = 1
		START_NEW_SCRIPT trace_mission_loop
		goto_thereX = traceX[0]          
		goto_thereY = traceY[0]          
		goto_thereZ = traceZ[0]
		//debug_heading = 130.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP25 ) 1000 4 //RACE 
		driving_test_passed = 1
		flag_mob_sanfran[3] = 1
		START_NEW_SCRIPT trace_mission_loop
		goto_thereX = traceX[1]          
		goto_thereY = traceY[1]          
		goto_thereZ = traceZ[1]
		//debug_heading = 130.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP25 ) 1000 4 //RACE 
		driving_test_passed = 1
		flag_mob_sanfran[3] = 1
		START_NEW_SCRIPT trace_mission_loop
		goto_thereX = traceX[2]          
		goto_thereY = traceY[2]          
		goto_thereZ = traceZ[2]
		//debug_heading = 130.0
	ENDIF
		debug_number ++
	IF button_pressed_main = debug_number
		//PRINT_BIG ( PROP25 ) 1000 4 //RACE 
		driving_test_passed = 1
		flag_mob_sanfran[3] = 1
		START_NEW_SCRIPT trace_mission_loop
		goto_thereX = traceX[3]          
		goto_thereY = traceY[3]          
		goto_thereZ = traceZ[3]
		//debug_heading = 130.0
	ENDIF

	debug_number ++
RETURN


/*
final2b wip / finish today
intro2a layout done/ifas and camera exported
strap4b layout done/needs export                                                   
sweet5a layout done/ifa’s exported/needs camera export
sweet6b layout done/ifas exported/needs camera export
crash1a (A/B/C)  wip / finish today                                                                 
*/

//strap3a layout done/camera exported/needs ifa export

 


terminate_all_scripts:

	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME INT
 	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME SWEET
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME CRASH
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME RYDER
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME SMOKE
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME STRAP
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME CESAR
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME LA1FIN
	
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME BCRASH
	
	//GOSUB remove_catalina
	REMOVE_BLIP cat_contact_blip
	//catalina_contact_blip_state	= 0
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME CAT
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME TRU
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME BCESAR
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME SYND
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME WUZI
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME STEAL
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME DSCHOO
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME TRACE
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME ZERO
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME SCRASH
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME GARAGE

	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME DESERT

	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME CASINO
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME VCRASH
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME DOC
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME HEIST

	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME MANSION
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME GROVE
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME RIOT

	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME CONSOLE

	//TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME BANDI_M
	//TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME POOLH
	//TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME LOWRI

	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME BSCHOO
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME BIKES
	//TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME BLOODR
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME TRUCKS 
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME QUARRYS
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME BIKES
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME BSCHOO
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME VALET
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME IMPEXPM
	//TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME DANCE
	//TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME TRI


	REMOVE_BLIP	intro_contact_blip
	REMOVE_BLIP sweet_contact_blip
	REMOVE_BLIP	crash_contact_blip
	REMOVE_BLIP	ryder_contact_blip
	REMOVE_BLIP	smoke_contact_blip
	REMOVE_BLIP	strap_contact_blip
	REMOVE_BLIP	cesar_contact_blip

	REMOVE_BLIP truth_contact_blip
	REMOVE_BLIP cat_contact_blip
	REMOVE_BLIP	bcrash_contact_blip
	REMOVE_BLIP	bcesar_contact_blip

	REMOVE_BLIP synd_contact_blip
	REMOVE_BLIP wuzi_contact_blip
	REMOVE_BLIP steal_contact_blip
	REMOVE_BLIP	trace_contact_blip[0]
	REMOVE_BLIP	trace_contact_blip[1]
	REMOVE_BLIP	trace_contact_blip[2]
	REMOVE_BLIP	trace_contact_blip[3]
	REMOVE_BLIP	zero_contact_blip
	REMOVE_BLIP desert_contact_blip
	REMOVE_BLIP	garage_contact_blip
	REMOVE_BLIP scrash_contact_blip
	REMOVE_BLIP dschool_contact_blip

	REMOVE_BLIP casino_contact_blip
	REMOVE_BLIP	heist_contact_blip
	REMOVE_BLIP	vcrash_contact_blip
	REMOVE_BLIP	doc_contact_blip

	REMOVE_BLIP mansion_contact_blip
	REMOVE_BLIP pilot_contact_blip
	REMOVE_BLIP	Theheist_contact_blip

	REMOVE_BLIP save_house_blip[7]

	REMOVE_BLIP boat_school_blip
	REMOVE_BLIP bike_school_blip

	flag_player_on_mission = 0
	WAIT 0

RETURN


///	WRITE PLAYER COORDS RELATIVE TO THE CLOSET CAR TO TEMP_DEBUG.TXT //
//		IF IS_BUTTON_PRESSED PAD2 LEFTSHOULDER1
//		AND IS_BUTTON_PRESSED PAD2 LEFTSHOULDER2
//			IF relative_button_press = 0
//				PRINT_NOW TEXTXYZ 800 1 // Writing coordinates to file...
//				GET_CHAR_COORDINATES scplayer player_x player_y player_z
//				text_x = player_x - 5.0
//				text_y = player_y - 5.0
//				x = player_x + 5.0
//				y = player_y + 5.0
//				GET_RANDOM_CAR_OF_TYPE_IN_AREA text_x text_y x y -1 car
//				IF NOT car = -1
//					GET_CAR_COORDINATES	car	text_x text_y text_z
//					x = player_x - text_x
//					y = player_y - text_y
//					z = player_z - text_z
//					SAVE_FLOAT_TO_DEBUG_FILE x
//					SAVE_FLOAT_TO_DEBUG_FILE y
//					SAVE_FLOAT_TO_DEBUG_FILE z
//					SAVE_FLOAT_TO_DEBUG_FILE 9.9999
//					SAVE_FLOAT_TO_DEBUG_FILE 9.9999
//					SAVE_NEWLINE_TO_DEBUG_FILE
//					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car x y z corona_x corona_y corona_z
//					MARK_CAR_AS_NO_LONGER_NEEDED car
//					car = -1
//				ELSE
//					SAVE_FLOAT_TO_DEBUG_FILE 9.9999
//					SAVE_NEWLINE_TO_DEBUG_FILE
//				ENDIF
//				relative_button_press = 1
//			ENDIF
//			DRAW_CORONA corona_x corona_y corona_z 0.2 CORONATYPE_CIRCLE FLARETYPE_NONE 250 0 0
//		ENDIF
//
//		IF NOT IS_BUTTON_PRESSED PAD2 LEFTSHOULDER1
//			IF NOT IS_BUTTON_PRESSED PAD2 LEFTSHOULDER2
//				IF relative_button_press = 1
//					relative_button_press = 0
//				ENDIF
//			ENDIF
//		ENDIF
///////////////////////////////////////////////////////////////////////


///	PLAYER INVULNERABILITY (SOMETIMES) ////////////////////////////////
//		IF invulnerability_on = 0
//			IF IS_BUTTON_PRESSED PAD2 START
//				DO_FADE 100 FADE_IN
//				invulnerability_on = 1
//			ENDIF
//		ENDIF
//
//		IF invulnerability_on = 2
//			IF IS_BUTTON_PRESSED PAD2 START
//				DO_FADE 100 FADE_IN
//				invulnerability_on = 3
//			ENDIF
//		ENDIF
//
//		IF NOT IS_BUTTON_PRESSED PAD2 START
//			IF invulnerability_on = 1
//				invulnerability_on = 2
//			ENDIF
//			IF invulnerability_on = 3
//				invulnerability_on = 0
//			ENDIF
//		ENDIF
///////////////////////////////////////////////////////////////////////


///	EXPLODE PLAYERS HEAD //////////////////////////////////////////////
//	IF IS_BUTTON_PRESSED PAD2 CROSS
//	AND IS_BUTTON_PRESSED PAD2 SQUARE
//		IF NOT IS_CHAR_IN_ANY_CAR scplayer
//			EXPLODE_PLAYER_HEAD player1
//		ELSE
//			STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car_colour2
//			IF NOT IS_CAR_DEAD car_colour2
//				EXPLODE_CAR car_colour2
//			ENDIF
//		ENDIF
//		GOTO mission_start_debug
//	ENDIF
///////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////
// GET RACE COORDS ////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
//VAR_INT write_coord
//VAR_FLOAT x2 y2 z2 distance
//IF IS_PLAYER_PLAYING player1
//	IF IS_CHAR_IN_ANY_CAR scplayer
//		IF IS_BUTTON_PRESSED PAD1 CIRCLE
//			VAR_INT button_pressed
//			IF button_pressed = 1
//				PRINT_NOW TEXXYZ1 800 1 // Writing coordinates to file...
//				++ write_coord
//				button_pressed = 0
//			ENDIF
//		ELSE
//			IF button_pressed = 0
//				button_pressed = 1
//			ENDIF
//		ENDIF
//	ENDIF
//
//	IF write_coord > 0
//		IF IS_CHAR_IN_ANY_CAR scplayer
//			GET_CHAR_COORDINATES scplayer X Y Z
//			GET_DISTANCE_BETWEEN_COORDS_3D X Y Z x2 y2 z2 distance
//			IF distance > 100.0
//			OR write_coord = 2
//				PRINT_NOW TEXTXYZ 800 1 // Writing coordinates to file...
//				SAVE_NEWLINE_TO_DEBUG_FILE
//				SAVE_STRING_TO_DEBUG_FILE "coord_x["
//				SAVE_INT_TO_DEBUG_FILE coord_index
//				SAVE_STRING_TO_DEBUG_FILE "] = "
//				SAVE_FLOAT_TO_DEBUG_FILE x
//				SAVE_NEWLINE_TO_DEBUG_FILE
//				SAVE_STRING_TO_DEBUG_FILE "coord_y["
//				SAVE_INT_TO_DEBUG_FILE coord_index
//				SAVE_STRING_TO_DEBUG_FILE "] = "
//				SAVE_FLOAT_TO_DEBUG_FILE y
//				SAVE_NEWLINE_TO_DEBUG_FILE
//				SAVE_STRING_TO_DEBUG_FILE "coord_z["
//				SAVE_INT_TO_DEBUG_FILE coord_index
//				SAVE_STRING_TO_DEBUG_FILE "] = "
//				SAVE_FLOAT_TO_DEBUG_FILE z
//				SAVE_NEWLINE_TO_DEBUG_FILE
//				++ coord_index
//				x2 = x
//				y2 = y
//				z2 = z
//				IF write_coord = 2
//					write_coord = 1
//				ENDIF
//			ENDIF
//		ENDIF
//	ENDIF
//ENDIF
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////


// CREATE A CAR - right shoulder1//////////////////////////////////////////
/*	
		IF IS_BUTTON_PRESSED PAD2 RIGHTSHOULDER1
			WHILE IS_BUTTON_PRESSED PAD2 RIGHTSHOULDER1
				WAIT 0
			ENDWHILE
			IF IS_PLAYER_PLAYING player1
				GET_CHAR_COORDINATES scplayer x_float_m y_float_m z_float_m
				GET_CHAR_HEADING scplayer player_heading_debug
			ENDIF
			IF player_heading_debug < 45.0
			AND player_heading_debug > 0.0
				y_float_m += 5.0
				debug_car_heading = 90.0
			ENDIF
			IF player_heading_debug < 360.0
			AND player_heading_debug > 315.0
				y_float_m += 5.0
				debug_car_heading = 90.0
			ENDIF
			IF player_heading_debug < 135.0
			AND player_heading_debug > 45.0
				x_float_m -= 5.0
				debug_car_heading = 180.0
			ENDIF
			IF player_heading_debug < 225.0
			AND player_heading_debug > 135.0
				y_float_m -= 5.0
				debug_car_heading = 270.0
			ENDIF
			IF player_heading_debug < 315.0
			AND player_heading_debug > 225.0
				x_float_m += 5.0
				debug_car_heading = 0.0
			ENDIF
			z_float_m = z_float_m + 0.6
			GET_GROUND_Z_FOR_3D_COORD x_float_m y_float_m z_float_m	z_float_m

			IF NOT IS_PLAYER_PLAYING player1
				GOTO mission_start_debug
			ENDIF
			

			next_carzzz:

			WAIT 0

			IF NOT IS_BUTTON_PRESSED PAD2 CROSS

				IF initial_create_car = 1
					++ counter_create_car
				ENDIF

				IF initial_create_car = 0
					counter_create_car = landstal
					initial_create_car = 1
				ENDIF

				IF counter_create_car > 236
					counter_create_car = landstal
				ENDIF
				
				IF counter_create_car = chopper	//POLICE CHOPPER
			   		counter_create_car = angel
				ENDIF

				IF counter_create_car =	airtrain	
				OR counter_create_car = deaddodo	
					counter_create_car = speeder
				ENDIF

				IF counter_create_car = infernus
					counter_create_car = voodoo	 
				ENDIF

			ELSE

				IF initial_create_car = 1
					-- counter_create_car
				ENDIF

				IF initial_create_car = 0
					counter_create_car = 236
					initial_create_car = 1
				ENDIF

				//initial_create_car = 1

				IF counter_create_car < landstal
					counter_create_car = 236
				ENDIF
				
				IF counter_create_car = chopper	//POLICE CHOPPER
					counter_create_car = cuban
				ENDIF

				IF counter_create_car = infernus
					counter_create_car = manana	 
				ENDIF

				IF counter_create_car =	airtrain	
				OR counter_create_car = deaddodo	
					counter_create_car = gangbur
				ENDIF

			ENDIF

			IF NOT IS_BUTTON_PRESSED PAD2 SQUARE

				DELETE_CAR magic_car
				MARK_CAR_AS_NO_LONGER_NEEDED magic_car
				MARK_MODEL_AS_NO_LONGER_NEEDED counter_create_car

				IF NOT IS_MODEL_AVAILABLE counter_create_car
					GOTO next_carzzz
				ENDIF
				REQUEST_MODEL counter_create_car
				PRINT_NOW LOADCAR 250 1 //"Loading vehicle, press pad2 leftshoulder1 to cancel"
				LOAD_ALL_MODELS_NOW
				
				WHILE NOT HAS_MODEL_LOADED counter_create_car
					WAIT 0
					
					PRINT_NOW LOADCAR 80 1 //"Loading vehicle, press pad2 leftshoulder1 to cancel"
					
					IF IS_BUTTON_PRESSED PAD2 LEFTSHOULDER1
						GOTO next_carzzz
					ENDIF
					
			
				ENDWHILE

				CREATE_CAR counter_create_car x_float_m y_float_m z_float_m magic_car
				CLEAR_THIS_BIG_PRINT NUMBER
				PRINT_WITH_NUMBER_BIG NUMBER counter_create_car 500 4
				SET_CAR_HEADING	magic_car debug_car_heading
				
				LOCK_CAR_DOORS magic_car CARLOCK_UNLOCKED

				MARK_MODEL_AS_NO_LONGER_NEEDED counter_create_car
				MARK_CAR_AS_NO_LONGER_NEEDED magic_car
			ELSE
				counter_create_car = counter_create_car + 10
				PRINT_WITH_NUMBER_BIG NUMBER counter_create_car 500 4	
			ENDIF

		ENDIF

*/


/*********************************************************************************************************************************************
************************************************						**********************************************************************
************************************************     AUDIO DEBUG	 	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
Audio_debug:
    SCRIPT_NAME ADBUG

	//--- INTERNALS
	LVAR_INT iPed1 iPed2
	LVAR_INT iState iSubState
	LVAR_FLOAT fX fY fZ 
	LVAR_INT iTemp iTemp2 iButtonDown iCurrentButton iPedCounter
	//--- Parameter passing Fudge
	IF iTemp > 0
	   	CREATE_CHAR PEDTYPE_CIVMALE 0 0.0 0.0 0.0 iPed1
		CREATE_CHAR PEDTYPE_CIVMALE 0 0.0 0.0 0.0 iPed2
	ENDIF

   iAudioDebug = 1

	//---MAIN LOOP---
Audio_debug_Main_Loop:

	WAIT 50

	GOSUB Audio_Debug_GetPadStatus

	IF IS_PLAYER_PLAYING PLAYER1
		GOSUB Audio_debug_State_Machine
	ELSE
		GOSUB Audio_debug_CleanUp
	ENDIF

GOTO Audio_debug_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
Audio_debug_State_Machine:
	SWITCH iState	   	
   		CASE 0 //---STATE 0 
			GOSUB Audio_debug_State0
		BREAK
	ENDSWITCH

RETURN
/********************************************
				STATE 0
********************************************/
Audio_debug_State0:  
	SWITCH iSubState

	CASE TEXT1
		WRITE_DEBUG PLEASE_ENTER_FIRST_PED
		iSubState = GET_PED_1
	BREAK

	CASE GET_PED_1
		
		IF iCurrentButton = AD_BUTTON_DPADRIGHT
			++iPedCounter  
		ENDIF
	
		IF iCurrentButton = AD_BUTTON_DPADLEFT
			--iPedCounter  
		ENDIF

		IF iCurrentButton = AD_BUTTON_DPADUP
			iPedCounter += 10
		ENDIF

		IF iCurrentButton = AD_BUTTON_DPADDOWN
			iPedCounter -= 10
		ENDIF

		IF iPedCounter < 0
			iPedCounter = 0
		ENDIF

 		CLEAR_THIS_BIG_PRINT NUMBER
		PRINT_WITH_NUMBER_BIG NUMBER iPedCounter 500 4

		IF iCurrentButton = AD_BUTTON_CROSS
			iSubState = GENERATE_PED_1
		ENDIF

	BREAK

	CASE GENERATE_PED_1		
		IF IS_MODEL_AVAILABLE iPedCounter
			 
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 1.0 2.0 0.0 fX fY fZ
			GET_GROUND_Z_FOR_3D_COORD fX fY fZ fZ
			CLEAR_AREA fX fY fZ 5.0 TRUE

			REQUEST_MODEL iPedCounter
			LOAD_ALL_MODELS_NOW
 			WHILE NOT HAS_MODEL_LOADED iPedCounter
 				REQUEST_MODEL iPedCounter
 				WAIT 0
 			ENDWHILE

 			CREATE_CHAR PEDTYPE_CIVMALE iPedCounter fX fY fZ iPed1
			iSubState = TEXT2
 		ELSE
			WRITE_DEBUG_WITH_INT PED_NOT_FOUND iPedCounter 
			iSubState = TEXT1
 		ENDIF
	BREAK

	CASE TEXT2
		WRITE_DEBUG PLEASE_ENTER_SECOND_PED
		iSubState = GET_PED_2
	BREAK

	
	CASE GET_PED_2

		IF iCurrentButton = AD_BUTTON_DPADRIGHT
			++iPedCounter  
		ENDIF
	
		IF iCurrentButton = AD_BUTTON_DPADLEFT
			--iPedCounter  
		ENDIF

		IF iCurrentButton = AD_BUTTON_DPADUP
			iPedCounter += 10
		ENDIF

		IF iCurrentButton = AD_BUTTON_DPADDOWN
			iPedCounter -= 10
		ENDIF

		IF iPedCounter < 0
			iPedCounter = 0
		ENDIF

 		CLEAR_THIS_BIG_PRINT NUMBER
		PRINT_WITH_NUMBER_BIG NUMBER iPedCounter 500 4

		IF iCurrentButton = AD_BUTTON_CROSS
			iSubState = GENERATE_PED_2
		ENDIF
	BREAK
	
	CASE GENERATE_PED_2		
		IF IS_MODEL_AVAILABLE iPedCounter
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -1.0 2.0 0.0 fX fY fZ
			GET_GROUND_Z_FOR_3D_COORD fX fY fZ fZ
			CLEAR_AREA fX fY fZ 5.0 TRUE

			REQUEST_MODEL iPedCounter
			LOAD_ALL_MODELS_NOW
 			WHILE NOT HAS_MODEL_LOADED iPedCounter
 				REQUEST_MODEL iPedCounter
 				WAIT 0
 			ENDWHILE

 			CREATE_CHAR PEDTYPE_CIVMALE iPedCounter fX fY fZ iPed2
			iSubState = FACING
 		ELSE
			WRITE_DEBUG_WITH_INT PED_NOT_FOUND iPedCounter 
			iSubState = TEXT2
 		ENDIF
	BREAK

	CASE FACING
		IF NOT IS_CHAR_DEAD iPed1
		AND NOT IS_CHAR_DEAD iPed2
		AND NOT IS_CHAR_DEAD scplayer
		 	TASK_TURN_CHAR_TO_FACE_CHAR iPed1 iPed2
			TASK_TURN_CHAR_TO_FACE_CHAR iPed2 iPed1
			iSubState = CHAT
		ENDIF
	BREAK

	CASE CHAT
		IF NOT IS_CHAR_DEAD iPed1
		AND NOT IS_CHAR_DEAD iPed2
		AND NOT IS_CHAR_DEAD scplayer
			GET_SCRIPT_TASK_STATUS iPed1 TASK_TURN_CHAR_TO_FACE_CHAR iTemp 
			GET_SCRIPT_TASK_STATUS iPed2 TASK_TURN_CHAR_TO_FACE_CHAR iTemp2 
			IF iTemp = FINISHED_TASK
			AND iTemp2 = FINISHED_TASK
		   		GET_SCRIPT_TASK_STATUS iPed1 TASK_CHAT_WITH_CHAR iTemp
				IF iTemp = FINISHED_TASK
					TASK_CHAT_WITH_CHAR iPed1 iPed2 TRUE TRUE
				ENDIF

				GET_SCRIPT_TASK_STATUS iPed2 TASK_CHAT_WITH_CHAR iTemp
				IF iTemp = FINISHED_TASK		 
					TASK_CHAT_WITH_CHAR iPed2 iPed1 FALSE TRUE		
				ENDIF

				iSubState = END

			ENDIF
		ENDIF	
	BREAK

	CASE END
		IF NOT IS_CHAR_DEAD iPed1
		AND NOT IS_CHAR_DEAD iPed2
		AND NOT IS_CHAR_DEAD scplayer
			GET_CHAR_MODEL iPed1 iTemp 
			MARK_MODEL_AS_NO_LONGER_NEEDED iTemp
			GET_CHAR_MODEL iPed2 iTemp 	 
			MARK_MODEL_AS_NO_LONGER_NEEDED iTemp

			MARK_CHAR_AS_NO_LONGER_NEEDED iPed1
			MARK_CHAR_AS_NO_LONGER_NEEDED iPed2

			WRITE_DEBUG AUDIO_PED_COMPLETED
			GOSUB Audio_debug_CleanUp
		ENDIF
	BREAK

	ENDSWITCH

RETURN
/********************************************
			GET PAD STATUS
********************************************/
Audio_Debug_GetPadStatus:

SWITCH iButtonDown

	CASE AD_BUTTON_NONE 
		
		IF IS_BUTTON_PRESSED PAD1 DPADUP
			iCurrentButton = AD_BUTTON_DPADUP
			iButtonDown = AD_BUTTON_DPADUP			
			BREAK
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 DPADDOWN
			iCurrentButton = AD_BUTTON_DPADDOWN
			iButtonDown = AD_BUTTON_DPADDOWN
			BREAK
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 DPADLEFT
			iCurrentButton = AD_BUTTON_DPADLEFT
			iButtonDown = AD_BUTTON_DPADLEFT
			BREAK
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 DPADRIGHT
			iCurrentButton = AD_BUTTON_DPADRIGHT
			iButtonDown = AD_BUTTON_DPADRIGHT
			BREAK
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 CROSS
			iCurrentButton = AD_BUTTON_CROSS
			iButtonDown = AD_BUTTON_CROSS
			BREAK
		ENDIF

	BREAK

	CASE AD_BUTTON_DPADUP
		iCurrentButton = AD_BUTTON_NONE
		IF NOT IS_BUTTON_PRESSED PAD1 DPADUP
			iButtonDown = AD_BUTTON_NONE
			BREAK
		ENDIF
	BREAK

	CASE AD_BUTTON_DPADDOWN
		iCurrentButton = AD_BUTTON_NONE
		IF NOT IS_BUTTON_PRESSED PAD1 DPADDOWN
			iButtonDown = AD_BUTTON_NONE
			BREAK
		ENDIF
	BREAK

	CASE AD_BUTTON_DPADLEFT
		iCurrentButton = AD_BUTTON_NONE
		IF NOT IS_BUTTON_PRESSED PAD1 DPADLEFT
			iButtonDown = AD_BUTTON_NONE
			BREAK
		ENDIF
	BREAK

	CASE AD_BUTTON_DPADRIGHT
		iCurrentButton = AD_BUTTON_NONE
		IF NOT IS_BUTTON_PRESSED PAD1 DPADRIGHT
			iButtonDown = AD_BUTTON_NONE
			BREAK
		ENDIF
	BREAK


	CASE AD_BUTTON_CROSS
		iCurrentButton = AD_BUTTON_NONE
		IF NOT IS_BUTTON_PRESSED PAD1 CROSS	
			iButtonDown = AD_BUTTON_NONE
			BREAK
		ENDIF
	BREAK

ENDSWITCH

RETURN
/*******************************************
				CLEAN UP 
********************************************/
Audio_debug_CleanUp:
	IF NOT IS_CHAR_DEAD iPed1
		GET_CHAR_MODEL iPed1 iTemp 
		MARK_MODEL_AS_NO_LONGER_NEEDED iTemp
		MARK_CHAR_AS_NO_LONGER_NEEDED iPed1
	ENDIF

	IF NOT IS_CHAR_DEAD iPed2
		GET_CHAR_MODEL iPed2 iTemp 	 
		MARK_MODEL_AS_NO_LONGER_NEEDED iTemp
		MARK_CHAR_AS_NO_LONGER_NEEDED iPed2
	ENDIF

	iAudioDebug = 0

	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
				RUN DEBUG
********************************************/
Audio_debug_Debug:
	WRITE_DEBUG_WITH_INT STATE iState
	WRITE_DEBUG_WITH_INT SUBSTATE iSubState
	WRITE_DEBUG_WITH_INT TIMERB	TIMERB
	WRITE_DEBUG_WITH_INT TIMERA	TIMERA
RETURN

}
