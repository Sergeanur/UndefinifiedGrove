MISSION_START

SCRIPT_NAME initil2


VAR_INT	sequence_task_obj
VAR_INT temp_integer_1 temp_integer_2 temp_integer_3 temp_integer_4 an
VAR_FLOAT temp_float_1 temp_float_2 temp_float_3 x_temp y_temp z_temp

//SLOT MACHINE
VAR_INT	cherry
VAR_INT	grape
VAR_INT	r_69
VAR_INT	bell
VAR_INT	bar1_o
VAR_INT	bar2_o
cherry = 25
grape  = 100
r_69   = 250
bell   = 500
bar1_o = 1000
bar2_o = 5000

VAR_INT reel1_winnings[18] reel2_winnings[18] reel3_winnings[18]
reel1_winnings[0]  = bar2_o
reel1_winnings[1]  = r_69
reel1_winnings[2]  = bar1_o
reel1_winnings[3]  = bell
reel1_winnings[4]  = cherry
reel1_winnings[5]  = grape
reel1_winnings[6]  = cherry
reel1_winnings[7]  = grape
reel1_winnings[8]  = bell
reel1_winnings[9]  = r_69
reel1_winnings[10] = bell
reel1_winnings[11] = bar1_o
reel1_winnings[12] = cherry
reel1_winnings[13] = grape
reel1_winnings[14] = r_69
reel1_winnings[15] = grape
reel1_winnings[16] = bell
reel1_winnings[17] = cherry

reel2_winnings[0] = r_69
reel2_winnings[1] = bell
reel2_winnings[2] = cherry
reel2_winnings[3] = grape
reel2_winnings[4] = bar2_o
reel2_winnings[5] = bell
reel2_winnings[6] = grape
reel2_winnings[7] = cherry
reel2_winnings[8] = bell
reel2_winnings[9] = bar1_o
reel2_winnings[10] = grape
reel2_winnings[11] = r_69
reel2_winnings[12] = cherry
reel2_winnings[13] = bar1_o
reel2_winnings[14] = cherry
reel2_winnings[15] = r_69
reel2_winnings[16] = bell
reel2_winnings[17] = grape

reel3_winnings[0] = grape
reel3_winnings[1] = cherry
reel3_winnings[2] = r_69
reel3_winnings[3] = bell
reel3_winnings[4] = grape
reel3_winnings[5] = bell
reel3_winnings[6] = r_69
reel3_winnings[7] = grape
reel3_winnings[8] = cherry
reel3_winnings[9] = bar1_o
reel3_winnings[10] = bar2_o
reel3_winnings[11] = cherry
reel3_winnings[12] = bell
reel3_winnings[13] = r_69
reel3_winnings[14] = grape
reel3_winnings[15] = bar1_o
reel3_winnings[16] = bell
reel3_winnings[17] = cherry

var_float reel_sprite_x[5] reel_sprite_y[5]
reel_sprite_x[0] = 261.3555 
reel_sprite_y[0] = 399.3732 

reel_sprite_x[1] = 219.0498 
reel_sprite_y[1] = 356.6364 

reel_sprite_x[2] = 431.2757 
reel_sprite_y[2] = 424.3356 

//ROULETTE
var_float rou_text_x[4] rou_text_y[4]
var_float rou_x_scale[2] rou_y_scale[2]
rou_text_x[0] = 36.3650 
rou_text_y[0] = 240.1570 
rou_x_scale[0] = 0.4714 
rou_y_scale[0] = 2.5077 

rou_text_x[1] = 29.3763 
rou_text_y[1] = 20.0589 
rou_x_scale[1] = 0.6253 
rou_y_scale[1] = 2.7876 

rou_text_x[2] = 28.5106 
rou_text_y[2] = 220.0782 

rou_text_x[3] = 157.2242 
rou_text_y[3] = 409.3602 

var_int roulette_triggered
roulette_triggered = 0

VAR_FLOAT spot1_topleft_x spot1_topleft_y spot36_bottomright_x spot36_bottomright_y
spot1_topleft_x	= -0.316
spot1_topleft_y	= 0.497
spot36_bottomright_x = 0.408
spot36_bottomright_y = -1.342

x = spot1_topleft_x	- spot36_bottomright_x
y = spot1_topleft_y	- spot36_bottomright_y

VAR_FLOAT spot_size_x spot_size_y
spot_size_x	= x / 6.0
spot_size_y = y / 24.0

VAR_FLOAT spot1_x spot1_y spot1_z
spot1_x	= spot1_topleft_x - spot_size_x
spot1_y	= spot1_topleft_y - spot_size_y
spot1_z = -0.176

VAR_FLOAT spot_selection_x spot_selection_y
spot_selection_x = 0.0
spot_selection_y = 0.0

VAR_FLOAT chip_position_x chip_position_y
chip_position_x = 0.0
chip_position_y = 0.0

VAR_FLOAT spot_increment_x spot_increment_y
spot_increment_x = 0.0
spot_increment_y = 0.0

VAR_INT roulette_flag wheel_rotation_timer
roulette_flag = 0
wheel_rotation_timer = 0

VAR_FLOAT selected_spot_x[151] selected_spot_y[151]
selected_spot_x[0]   = -2.0  //0         // 35/1 BETS
selected_spot_y[0]   = 2.0
selected_spot_x[1]   = 0.0	 //1
selected_spot_y[1]   = 0.0
selected_spot_x[2]   = -2.0	 //2
selected_spot_y[2]   = 0.0
selected_spot_x[3]   = -4.0	 //3
selected_spot_y[3]   = 0.0
selected_spot_x[4]   = 0.0	 //4
selected_spot_y[4]   = -2.0
selected_spot_x[5]   = -2.0	 //5
selected_spot_y[5]   = -2.0
selected_spot_x[6]   = -4.0	 //6
selected_spot_y[6]   = -2.0
selected_spot_x[7]   = 0.0	 //7
selected_spot_y[7]   = -4.0
selected_spot_x[8]   = -2.0	 //8
selected_spot_y[8]   = -4.0
selected_spot_x[9]   = -4.0	 //9
selected_spot_y[9]   = -4.0
selected_spot_x[10]  = 0.0	 //10
selected_spot_y[10]  = -6.0
selected_spot_x[11]  = -2.0	 //11
selected_spot_y[11]  = -6.0
selected_spot_x[12]  = -4.0	 //12
selected_spot_y[12]  = -6.0
selected_spot_x[13]  = 0.0	 //13
selected_spot_y[13]  = -8.0
selected_spot_x[14]  = -2.0	 //14
selected_spot_y[14]  = -8.0
selected_spot_x[15]  = -4.0	 //15
selected_spot_y[15]  = -8.0
selected_spot_x[16]  = 0.0	 //16
selected_spot_y[16]  = -10.0
selected_spot_x[17]  = -2.0	 //17
selected_spot_y[17]  = -10.0
selected_spot_x[18]  = -4.0	 //18
selected_spot_y[18]  = -10.0
selected_spot_x[19]  = 0.0	 //19
selected_spot_y[19]  = -12.0
selected_spot_x[20]  = -2.0	 //20
selected_spot_y[20]  = -12.0
selected_spot_x[21]  = -4.0	 //21
selected_spot_y[21]  = -12.0
selected_spot_x[22]  = 0.0	 //22
selected_spot_y[22]  = -14.0
selected_spot_x[23]  = -2.0	 //23
selected_spot_y[23]  = -14.0
selected_spot_x[24]  = -4.0	 //24
selected_spot_y[24]  = -14.0
selected_spot_x[25]  = 0.0	 //25
selected_spot_y[25]  = -16.0
selected_spot_x[26]  = -2.0	 //26
selected_spot_y[26]  = -16.0
selected_spot_x[27]  = -4.0	 //27
selected_spot_y[27]  = -16.0
selected_spot_x[28]  = 0.0	 //28
selected_spot_y[28]  = -18.0
selected_spot_x[29]  = -2.0	 //29
selected_spot_y[29]  = -18.0
selected_spot_x[30]  = -4.0	 //30
selected_spot_y[30]  = -18.0
selected_spot_x[31]  = 0.0	 //31
selected_spot_y[31]  = -20.0
selected_spot_x[32]  = -2.0	 //32
selected_spot_y[32]  = -20.0
selected_spot_x[33]  = -4.0	 //33
selected_spot_y[33]  = -20.0
selected_spot_x[34]  = 0.0	 //34
selected_spot_y[34]  = -22.0
selected_spot_x[35]  = -2.0	 //35
selected_spot_y[35]  = -22.0
selected_spot_x[36]  = -4.0	 //36
selected_spot_y[36]  = -22.0
selected_spot_x[37]  = -1.0  //1_2         // 17/1 BETS - LEFT TO RIGHT
selected_spot_y[37]  = 0.0
selected_spot_x[38]  = -3.0	 //2_3
selected_spot_y[38]  = 0.0
selected_spot_x[39]  = -1.0	 //4_5
selected_spot_y[39]  = -2.0
selected_spot_x[40]  = -3.0	 //5_6
selected_spot_y[40]  = -2.0
selected_spot_x[41]  = -1.0	 //7_8
selected_spot_y[41]  = -4.0
selected_spot_x[42]  = -3.0	 //8_9
selected_spot_y[42]  = -4.0
selected_spot_x[43]  = -1.0	 //10_11
selected_spot_y[43]  = -6.0
selected_spot_x[44]  = -3.0	 //11_12
selected_spot_y[44]  = -6.0
selected_spot_x[45]  = -1.0	 //13_14
selected_spot_y[45]  = -8.0
selected_spot_x[46]  = -3.0	 //14_15
selected_spot_y[46]  = -8.0
selected_spot_x[47]  = -1.0	 //16_17
selected_spot_y[47]  = -10.0
selected_spot_x[48]  = -3.0	 //17_18
selected_spot_y[48]  = -10.0
selected_spot_x[49]  = -1.0	 //19_20
selected_spot_y[49]  = -12.0
selected_spot_x[50]  = -3.0	 //20_21
selected_spot_y[50]  = -12.0
selected_spot_x[51]  = -1.0	 //22_23
selected_spot_y[51]  = -14.0
selected_spot_x[52]  = -3.0	 //23_24
selected_spot_y[52]  = -14.0
selected_spot_x[53]  = -1.0	 //25_26
selected_spot_y[53]  = -16.0
selected_spot_x[54]  = -3.0	 //26_27
selected_spot_y[54]  = -16.0
selected_spot_x[55]  = -1.0	 //28_29
selected_spot_y[55]  = -18.0
selected_spot_x[56]  = -3.0	 //29_30
selected_spot_y[56]  = -18.0
selected_spot_x[57]  = -1.0	 //31_32
selected_spot_y[57]  = -20.0
selected_spot_x[58]  = -3.0	 //32_33
selected_spot_y[58]  = -20.0
selected_spot_x[59]  = -1.0	 //34_35
selected_spot_y[59]  = -22.0
selected_spot_x[60]  = -3.0	 //35_36
selected_spot_y[60]  = -22.0
selected_spot_x[61]  = 0.0   //1_4         // 17/1 BETS - UP TO DOWN
selected_spot_y[61]  = -1.0
selected_spot_x[62]  = -2.0	 //2_5
selected_spot_y[62]  = -1.0
selected_spot_x[63]  = -4.0	 //3_6
selected_spot_y[63]  = -1.0
selected_spot_x[64]  = 0.0	 //4_7
selected_spot_y[64]  = -3.0
selected_spot_x[65]  = -2.0	 //5_8
selected_spot_y[65]  = -3.0
selected_spot_x[66]  = -4.0	 //6_9
selected_spot_y[66]  = -3.0
selected_spot_x[67]  = 0.0	 //7_10
selected_spot_y[67]  = -5.0
selected_spot_x[68]  = -2.0	 //8_11
selected_spot_y[68]  = -5.0
selected_spot_x[69]  = -4.0	 //9_12
selected_spot_y[69]  = -5.0
selected_spot_x[70]  = 0.0	 //10_13
selected_spot_y[70]  = -7.0
selected_spot_x[71]  = -2.0	 //11_14
selected_spot_y[71]  = -7.0
selected_spot_x[72]  = -4.0	 //12_15
selected_spot_y[72]  = -7.0
selected_spot_x[73]  = 0.0	 //13_16
selected_spot_y[73]  = -9.0
selected_spot_x[74]  = -2.0	 //14_17
selected_spot_y[74]  = -9.0
selected_spot_x[75]  = -4.0	 //15_18
selected_spot_y[75]  = -9.0
selected_spot_x[76]  = 0.0	 //16_19
selected_spot_y[76]  = -11.0
selected_spot_x[77]  = -2.0	 //17_20
selected_spot_y[77]  = -11.0
selected_spot_x[78]  = -4.0	 //18_21
selected_spot_y[78]  = -11.0
selected_spot_x[79]  = 0.0	 //19_22
selected_spot_y[79]  = -13.0
selected_spot_x[80]  = -2.0	 //20_23
selected_spot_y[80]  = -13.0
selected_spot_x[81]  = -4.0	 //21_24
selected_spot_y[81]  = -13.0
selected_spot_x[82]  = 0.0	 //22_25
selected_spot_y[82]  = -15.0
selected_spot_x[83]  = -2.0	 //23_26
selected_spot_y[83]  = -15.0
selected_spot_x[84]  = -4.0	 //24_27
selected_spot_y[84]  = -15.0
selected_spot_x[85]  = 0.0	 //25_28
selected_spot_y[85]  = -17.0
selected_spot_x[86]  = -2.0	 //26_29
selected_spot_y[86]  = -17.0
selected_spot_x[87]  = -4.0	 //27_30
selected_spot_y[87]  = -17.0
selected_spot_x[88]  = 0.0	 //28_31
selected_spot_y[88]  = -19.0
selected_spot_x[89]  = -2.0	 //29_32
selected_spot_y[89]  = -19.0
selected_spot_x[90]  = -4.0	 //30_33
selected_spot_y[90]  = -19.0
selected_spot_x[91]  = 0.0	 //31_34
selected_spot_y[91]  = -21.0
selected_spot_x[92]  = -2.0	 //32_35
selected_spot_y[92]  = -21.0
selected_spot_x[93]  = -4.0	 //33_36
selected_spot_y[93]  = -21.0
selected_spot_x[94]  = -5.0  //1_2_3         // 11/1 BETS
selected_spot_y[94]  = 0.0
selected_spot_x[95]  = -5.0	 //4_5_6
selected_spot_y[95]  = -2.0
selected_spot_x[96]  = -5.0	 //7_8_9
selected_spot_y[96]  = -4.0
selected_spot_x[97]  = -5.0	 //10_11_12
selected_spot_y[97]  = -6.0
selected_spot_x[98]  = -5.0	 //13_14_15
selected_spot_y[98]  = -8.0
selected_spot_x[99]  = -5.0	 //16_17_18
selected_spot_y[99]  = -10.0
selected_spot_x[100] = -5.0	 //19_20_21
selected_spot_y[100] = -12.0
selected_spot_x[101] = -5.0	 //22_23_24
selected_spot_y[101] = -14.0
selected_spot_x[102] = -5.0	 //25_26_27
selected_spot_y[102] = -16.0
selected_spot_x[103] = -5.0	 //28_29_30
selected_spot_y[103] = -18.0
selected_spot_x[104] = -5.0	 //31_32_33
selected_spot_y[104] = -20.0
selected_spot_x[105] = -5.0	 //34_35_36
selected_spot_y[105] = -22.0
selected_spot_x[106] = -1.0  //1_2_4_5         // 8/1 BETS LEFT - RIGHT
selected_spot_y[106] = -1.0
selected_spot_x[107] = -3.0	 //2_3_5_6
selected_spot_y[107] = -1.0
selected_spot_x[108] = -1.0	 //4_5_7_8
selected_spot_y[108] = -3.0
selected_spot_x[109] = -3.0	 //5_6_8_9
selected_spot_y[109] = -3.0
selected_spot_x[110] = -1.0	 //7_8_10_11
selected_spot_y[110] = -5.0
selected_spot_x[111] = -3.0	 //8_9_11_12
selected_spot_y[111] = -5.0
selected_spot_x[112] = -1.0	 //10_11_13_14
selected_spot_y[112] = -7.0
selected_spot_x[113] = -3.0	 //11_12_14_15
selected_spot_y[113] = -7.0
selected_spot_x[114] = -1.0	 //13_14_16_17
selected_spot_y[114] = -9.0
selected_spot_x[115] = -3.0	 //14_15_17_18
selected_spot_y[115] = -9.0
selected_spot_x[116] = -1.0	 //16_17_19_20
selected_spot_y[116] = -11.0
selected_spot_x[117] = -3.0	 //17_18_20_21
selected_spot_y[117] = -11.0
selected_spot_x[118] = -1.0	 //19_20_22_23
selected_spot_y[118] = -13.0
selected_spot_x[119] = -3.0	 //20_21_23_24
selected_spot_y[119] = -13.0
selected_spot_x[120] = -1.0	 //22_23_25_26
selected_spot_y[120] = -15.0
selected_spot_x[121] = -3.0	 //23_24_26_27
selected_spot_y[121] = -15.0
selected_spot_x[122] = -1.0	 //25_26_28_29
selected_spot_y[122] = -17.0
selected_spot_x[123] = -3.0	 //26_27_29_30
selected_spot_y[123] = -17.0
selected_spot_x[124] = -1.0	 //28_29_31_32
selected_spot_y[124] = -19.0
selected_spot_x[125] = -3.0	 //29_30_32_33
selected_spot_y[125] = -19.0
selected_spot_x[126] = -1.0	 //31_32_34_35
selected_spot_y[126] = -21.0
selected_spot_x[127] = -3.0	 //32_33_35_36
selected_spot_y[127] = -21.0
selected_spot_x[128] = 0.0   //2to1_left         // 2/1 BETS BOTTOM ROW - LEFT TO RIGHT
selected_spot_y[128] = -24.0
selected_spot_x[129] = -2.0	 //2to1_middle
selected_spot_y[129] = -24.0
selected_spot_x[130] = -4.0	 //2to1_right
selected_spot_y[130] = -24.0
selected_spot_x[131] = 2.0   //1st_12         // 1ST 2ND 3RD 12 BETS - TOP TO BOTTOM
selected_spot_y[131] = -3.0
selected_spot_x[132] = 2.0	 //2nd_12
selected_spot_y[132] = -11.0
selected_spot_x[133] = 2.0	 //3rd_12
selected_spot_y[133] = -19.0
selected_spot_x[134] = 4.0   //1_to_18         //EVEN MONEY BETS
selected_spot_y[134] = -1.0
selected_spot_x[135] = 4.0	 //19_to_36
selected_spot_y[135] = -21.0
selected_spot_x[136] = 4.0	 //even
selected_spot_y[136] = -5.0
selected_spot_x[137] = 4.0	 //odd
selected_spot_y[137] = -17.0
selected_spot_x[138] = 4.0	 //red
selected_spot_y[138] = -9.0
selected_spot_x[139] = 4.0	 //black
selected_spot_y[139] = -13.0
selected_spot_x[140] = -5.0  //1_2_3_4_5_6         // 5/1 BETS
selected_spot_y[140] = -1.0
selected_spot_x[141] = -5.0	 //4_5_6_7_8_9
selected_spot_y[141] = -3.0
selected_spot_x[142] = -5.0	 //7_8_9_10_11_12
selected_spot_y[142] = -5.0
selected_spot_x[143] = -5.0	 //10_11_12_13_14_15
selected_spot_y[143] = -7.0
selected_spot_x[144] = -5.0	 //13_14_15_16_17_18
selected_spot_y[144] = -9.0
selected_spot_x[145] = -5.0	 //16_17_18_19_20_21
selected_spot_y[145] = -11.0
selected_spot_x[146] = -5.0	 //19_20_21_22_23_24
selected_spot_y[146] = -13.0
selected_spot_x[147] = -5.0	 //22_23_24_25_26_27
selected_spot_y[147] = -15.0
selected_spot_x[148] = -5.0	 //25_26_27_28_29_30
selected_spot_y[148] = -17.0
selected_spot_x[149] = -5.0	 //28_29_30_31_32_33
selected_spot_y[149] = -19.0
selected_spot_x[150] = -5.0	 //31_32_33_34_35_36
selected_spot_y[150] = -21.0

VAR_FLOAT sprite_x sprite_y
sprite_x = 345.3592 
sprite_y = 55.3183 

var_int no_more_bets_b[3]
no_more_bets_b[0] = SOUND_B_NMB_1	//No more bets, please.
no_more_bets_b[1] = SOUND_B_NMB_2	//No more bets, ladies and gentlemen, please.
no_more_bets_b[2] = SOUND_B_NMB_3	//No more bets, people.

var_int no_money_b[3]
no_money_b[0] = SOUND_B_NEM_1	//Sir doesn't have sufficient money to back another bet.
no_money_b[1] = SOUND_B_NEM_2	//Sorry, sir, you do not have enough funds.
no_money_b[2] = SOUND_B_NEM_3	//You appear to have insufficient funds to continue betting.

var_int gambling_stat_too_low_b[3]
gambling_stat_too_low_b[0] = SOUND_B_REG_1	//I'm sorry, sir, regulars only.
gambling_stat_too_low_b[1] = SOUND_B_REG_2	//The house does not recognise your limit at this table, sir.

var_int player_wins_b[6]
player_wins_b[0] = SOUND_B_PWIN1	//You win.
player_wins_b[1] = SOUND_B_PWIN2	//You win, sir, well done.
player_wins_b[2] = SOUND_B_PWIN3	//Congratulations sir!
player_wins_b[3] = SOUND_B_WINS1	//Another win for sir!
player_wins_b[4] = SOUND_B_WINS2	//Congratulations, sir, you're having quite a run!
player_wins_b[5] = SOUND_B_WINS3	//I hope sir's luck holds!

var_int player_leaves_b[2]
player_leaves_b[0] = SOUND_B_THX_1	//Thankyou, sir, have a nice day!
player_leaves_b[1] = SOUND_B_THX_2	//Thankyou for playing, sir.

var_int place_bets_b[2]
place_bets_b[0] = SOUND_B_BET_1	//Place your bets!
place_bets_b[1] = SOUND_B_BET_2	//Place your bets, ladies and gentlemen.

var_int roulette_result_b[37]
roulette_result_b[0] = SOUND_B_NUM_0	//Zero!
roulette_result_b[1] = SOUND_B_NUM_1	//Red - 1
roulette_result_b[2] = SOUND_B_NUM_2	//Black - 2
roulette_result_b[3] = SOUND_B_NUM_3	//Red - 3
roulette_result_b[4] = SOUND_B_NUM_4	//Black - 4
roulette_result_b[5] = SOUND_B_NUM_5	//Red - 5
roulette_result_b[6] = SOUND_B_NUM_6	//Black - 6
roulette_result_b[7] = SOUND_B_NUM_7	//Red - 7
roulette_result_b[8] = SOUND_B_NUM_8	//Black - 8
roulette_result_b[9] = SOUND_B_NUM_9	//Red - 9
roulette_result_b[10] = SOUND_B_NUM10	//Black - 10
roulette_result_b[11] = SOUND_B_NUM11	//Black - 11
roulette_result_b[12] = SOUND_B_NUM12	//Red - 12
roulette_result_b[13] = SOUND_B_NUM13	//Black - 13
roulette_result_b[14] = SOUND_B_NUM14	//Red - 14
roulette_result_b[15] = SOUND_B_NUM15	//Black - 15
roulette_result_b[16] = SOUND_B_NUM16	//Red - 16
roulette_result_b[17] = SOUND_B_NUM17	//Black - 17
roulette_result_b[18] = SOUND_B_NUM18	//Red - 18
roulette_result_b[19] = SOUND_B_NUM19	//Red - 19
roulette_result_b[20] = SOUND_B_NUM20	//Black - 20
roulette_result_b[21] = SOUND_B_NUM21	//Red - 21
roulette_result_b[22] = SOUND_B_NUM22	//Black - 22
roulette_result_b[23] = SOUND_B_NUM23	//Red - 23
roulette_result_b[24] = SOUND_B_NUM24	//Black - 24
roulette_result_b[25] = SOUND_B_NUM25	//Red - 25
roulette_result_b[26] = SOUND_B_NUM26	//Black - 26
roulette_result_b[27] = SOUND_B_NUM27	//Red - 27
roulette_result_b[28] = SOUND_B_NUM28	//Black - 28
roulette_result_b[29] = SOUND_B_NUM29	//Black - 29
roulette_result_b[30] = SOUND_B_NUM30	//Red - 30
roulette_result_b[31] = SOUND_B_NUM31	//Black - 31
roulette_result_b[32] = SOUND_B_NUM32	//Red - 32
roulette_result_b[33] = SOUND_B_NUM33	//Black - 33
roulette_result_b[34] = SOUND_B_NUM34	//Red - 34
roulette_result_b[35] = SOUND_B_NUM35	//Black - 35
roulette_result_b[36] = SOUND_B_NUM36	//Red - 36

var_int player_gets_credit_b[3]
player_gets_credit_b[0] = SOUND_B_LEND1	//An offer of credit has been made, sir.
player_gets_credit_b[1] = SOUND_B_LEND2	//The house is prepared to offer you credit, sir.
player_gets_credit_b[2] = SOUND_B_LEND3	//The house recognises sir's credit rating.

var_int no_more_bets_w[3]
no_more_bets_w[0] = SOUND_W_NMB_1	//No more bets, please.
no_more_bets_w[1] = SOUND_W_NMB_2	//No more bets, ladies and gentlemen, please.
no_more_bets_w[2] = SOUND_W_NMB_3	//No more bets, people.

var_int no_money_w[3]
no_money_w[0] = SOUND_W_NEM_1	//Sir doesn't have sufficient money to back another bet.
no_money_w[1] = SOUND_W_NEM_2	//Sorry, sir, you do not have enough funds.
no_money_w[2] = SOUND_W_NEM_3	//You appear to have insufficient funds to continue betting.

var_int gambling_stat_too_low_w[3]
gambling_stat_too_low_w[0] = SOUND_W_REG_1	//I'm sorry, sir, regulars only.
gambling_stat_too_low_w[1] = SOUND_W_REG_2	//The house does not recognise your limit at this table, sir.

var_int player_wins_w[6]
player_wins_w[0] = SOUND_W_PWIN1	//You win.
player_wins_w[1] = SOUND_W_PWIN2	//You win, sir, well done.
player_wins_w[2] = SOUND_W_PWIN3	//Congratulations sir!
player_wins_w[3] = SOUND_W_WINS1	//Another win for sir!
player_wins_w[4] = SOUND_W_WINS2	//Congratulations, sir, you're having quite a run!
player_wins_w[5] = SOUND_W_WINS3	//I hope sir's luck holds!

var_int player_leaves_w[2]
player_leaves_w[0] = SOUND_W_THX_1	//Thankyou, sir, have a nice day!
player_leaves_w[1] = SOUND_W_THX_2	//Thankyou for playing, sir.

var_int place_bets_w[2]
place_bets_w[0] = SOUND_W_BET_1	//Place your bets!
place_bets_w[1] = SOUND_W_BET_2	//Place your bets, ladies and gentlemen.

var_int roulette_result_w[37]
roulette_result_w[0] = SOUND_W_NUM_0	//Zero!
roulette_result_w[1] = SOUND_W_NUM_1	//Red - 1
roulette_result_w[2] = SOUND_W_NUM_2	//Black - 2
roulette_result_w[3] = SOUND_W_NUM_3	//Red - 3
roulette_result_w[4] = SOUND_W_NUM_4	//Black - 4
roulette_result_w[5] = SOUND_W_NUM_5	//Red - 5
roulette_result_w[6] = SOUND_W_NUM_6	//Black - 6
roulette_result_w[7] = SOUND_W_NUM_7	//Red - 7
roulette_result_w[8] = SOUND_W_NUM_8	//Black - 8
roulette_result_w[9] = SOUND_W_NUM_9	//Red - 9
roulette_result_w[10] = SOUND_W_NUM10	//Black - 10
roulette_result_w[11] = SOUND_W_NUM11	//Black - 11
roulette_result_w[12] = SOUND_W_NUM12	//Red - 12
roulette_result_w[13] = SOUND_W_NUM13	//Black - 13
roulette_result_w[14] = SOUND_W_NUM14	//Red - 14
roulette_result_w[15] = SOUND_W_NUM15	//Black - 15
roulette_result_w[16] = SOUND_W_NUM16	//Red - 16
roulette_result_w[17] = SOUND_W_NUM17	//Black - 17
roulette_result_w[18] = SOUND_W_NUM18	//Red - 18
roulette_result_w[19] = SOUND_W_NUM19	//Red - 19
roulette_result_w[20] = SOUND_W_NUM20	//Black - 20
roulette_result_w[21] = SOUND_W_NUM21	//Red - 21
roulette_result_w[22] = SOUND_W_NUM22	//Black - 22
roulette_result_w[23] = SOUND_W_NUM23	//Red - 23
roulette_result_w[24] = SOUND_W_NUM24	//Black - 24
roulette_result_w[25] = SOUND_W_NUM25	//Red - 25
roulette_result_w[26] = SOUND_W_NUM26	//Black - 26
roulette_result_w[27] = SOUND_W_NUM27	//Red - 27
roulette_result_w[28] = SOUND_W_NUM28	//Black - 28
roulette_result_w[29] = SOUND_W_NUM29	//Black - 29
roulette_result_w[30] = SOUND_W_NUM30	//Red - 30
roulette_result_w[31] = SOUND_W_NUM31	//Black - 31
roulette_result_w[32] = SOUND_W_NUM32	//Red - 32
roulette_result_w[33] = SOUND_W_NUM33	//Black - 33
roulette_result_w[34] = SOUND_W_NUM34	//Red - 34
roulette_result_w[35] = SOUND_W_NUM35	//Black - 35
roulette_result_w[36] = SOUND_W_NUM36	//Red - 36

var_int player_gets_credit_w[3]
player_gets_credit_w[0] = SOUND_W_LEND1	//An offer of credit has been made, sir.
player_gets_credit_w[1] = SOUND_W_LEND2	//The house is prepared to offer you credit, sir.
player_gets_credit_w[2] = SOUND_W_LEND3	//The house recognises sir's credit rating.


// ÉÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ»
// º CASINO GAME GLOBALS  CR º
// ÈÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ¼




																						 
// COORDS - for Bcesar2.sc - the coke courier route
VAR_FLOAT bce2_CHECKPOINTS_x[85] bce2_CHECKPOINTS_y[85] bce2_CHECKPOINTS_z[85]
BCE2_CHECKPOINTS_X[0] = -2307.7231 
BCE2_CHECKPOINTS_Y[0] = -2235.9656 
BCE2_CHECKPOINTS_Z[0] = 23.0204 
BCE2_CHECKPOINTS_X[1] = -2276.6350 
BCE2_CHECKPOINTS_Y[1] = -2220.0469 
BCE2_CHECKPOINTS_Z[1] = 28.7160 
BCE2_CHECKPOINTS_X[2] = -2199.3909 
BCE2_CHECKPOINTS_Y[2] = -2283.9519 
BCE2_CHECKPOINTS_Z[2] = 30.1494 
BCE2_CHECKPOINTS_X[3] = -2105.4365 
BCE2_CHECKPOINTS_Y[3] = -2354.4412 
BCE2_CHECKPOINTS_Z[3] = 30.1419 
BCE2_CHECKPOINTS_X[4] = -2142.2405 
BCE2_CHECKPOINTS_Y[4] = -2410.3201 
BCE2_CHECKPOINTS_Z[4] = 30.4970 
BCE2_CHECKPOINTS_X[5] = -2026.4043 
BCE2_CHECKPOINTS_Y[5] = -2507.7610 
BCE2_CHECKPOINTS_Z[5] = 32.0519 
BCE2_CHECKPOINTS_X[6] = -1938.5264 
BCE2_CHECKPOINTS_Y[6] = -2456.1458 
BCE2_CHECKPOINTS_Z[6] = 30.3298 
BCE2_CHECKPOINTS_X[7] = -1900.3940 
BCE2_CHECKPOINTS_Y[7] = -2424.6177 
BCE2_CHECKPOINTS_Z[7] = 31.9732 
BCE2_CHECKPOINTS_X[8] = -1862.8062 
BCE2_CHECKPOINTS_Y[8] = -2381.5376 
BCE2_CHECKPOINTS_Z[8] = 31.2712 
BCE2_CHECKPOINTS_X[9] = -1800.6287 
BCE2_CHECKPOINTS_Y[9] = -2319.6387 
BCE2_CHECKPOINTS_Z[9] = 40.2496 
BCE2_CHECKPOINTS_X[10] = -1707.1582 
BCE2_CHECKPOINTS_Y[10] = -2302.5793 
BCE2_CHECKPOINTS_Z[10] = 44.1806 
BCE2_CHECKPOINTS_X[11] = -1662.6387 
BCE2_CHECKPOINTS_Y[11] = -2186.9578 
BCE2_CHECKPOINTS_Z[11] = 33.5305 
BCE2_CHECKPOINTS_X[12] = -1688.2559 
BCE2_CHECKPOINTS_Y[12] = -2115.5901 
BCE2_CHECKPOINTS_Z[12] = 37.4741 
BCE2_CHECKPOINTS_X[13] = -1778.9581 
BCE2_CHECKPOINTS_Y[13] = -2090.0979 
BCE2_CHECKPOINTS_Z[13] = 49.6459 
BCE2_CHECKPOINTS_X[14] = -1868.4254 
BCE2_CHECKPOINTS_Y[14] = -2088.3604 
BCE2_CHECKPOINTS_Z[14] = 58.5847 
BCE2_CHECKPOINTS_X[15] = -1959.4097 
BCE2_CHECKPOINTS_Y[15] = -2047.8496 
BCE2_CHECKPOINTS_Z[15] = 71.8449 
BCE2_CHECKPOINTS_X[16] = -1947.7887 
BCE2_CHECKPOINTS_Y[16] = -1948.4871 
BCE2_CHECKPOINTS_Z[16] = 77.4592 
BCE2_CHECKPOINTS_X[17] = -1852.1096 
BCE2_CHECKPOINTS_Y[17] = -1920.4701 
BCE2_CHECKPOINTS_Z[17] = 87.5994 
BCE2_CHECKPOINTS_X[18] = -1754.6162 
BCE2_CHECKPOINTS_Y[18] = -1898.5020 
BCE2_CHECKPOINTS_Z[18] = 96.5566 
BCE2_CHECKPOINTS_X[19] = -1654.1207 
BCE2_CHECKPOINTS_Y[19] = -1900.0454 
BCE2_CHECKPOINTS_Z[19] = 90.8553 
BCE2_CHECKPOINTS_X[20] = -1554.6508 
BCE2_CHECKPOINTS_Y[20] = -1901.9591 
BCE2_CHECKPOINTS_Z[20] = 81.5693 
BCE2_CHECKPOINTS_X[21] = -1512.3779 
BCE2_CHECKPOINTS_Y[21] = -1807.0281 
BCE2_CHECKPOINTS_Z[21] = 57.9597 
BCE2_CHECKPOINTS_X[22] = -1433.2327 
BCE2_CHECKPOINTS_Y[22] = -1860.0319 
BCE2_CHECKPOINTS_Z[22] = 37.3383 
BCE2_CHECKPOINTS_X[23] = -1425.8123 
BCE2_CHECKPOINTS_Y[23] = -1962.0231 
BCE2_CHECKPOINTS_Z[23] = 15.8029 
BCE2_CHECKPOINTS_X[24] = -1298.4508 
BCE2_CHECKPOINTS_Y[24] = -2114.3845 
BCE2_CHECKPOINTS_Z[24] = 24.1506 
BCE2_CHECKPOINTS_X[25] = -1269.6609 
BCE2_CHECKPOINTS_Y[25] = -2251.1453 
BCE2_CHECKPOINTS_Z[25] = 21.2137 
BCE2_CHECKPOINTS_X[26] = -1260.5251 
BCE2_CHECKPOINTS_Y[26] = -2283.1221 
BCE2_CHECKPOINTS_Z[26] = 21.5858 
BCE2_CHECKPOINTS_X[27] = -1255.2582 
BCE2_CHECKPOINTS_Y[27] = -2307.5361 
BCE2_CHECKPOINTS_Z[27] = 22.0660 
BCE2_CHECKPOINTS_X[28] = -1253.1583 
BCE2_CHECKPOINTS_Y[28] = -2315.3430 
BCE2_CHECKPOINTS_Z[28] = 22.5913 
BCE2_CHECKPOINTS_X[29] = -1216.7279 
BCE2_CHECKPOINTS_Y[29] = -2343.4971 
BCE2_CHECKPOINTS_Z[29] = 16.9383 
BCE2_CHECKPOINTS_X[30] = -1193.9438 
BCE2_CHECKPOINTS_Y[30] = -2355.9954 
BCE2_CHECKPOINTS_Z[30] = 18.7764 
BCE2_CHECKPOINTS_X[31] = -1104.6796 
BCE2_CHECKPOINTS_Y[31] = -2377.1797 
BCE2_CHECKPOINTS_Z[31] = 36.3239 
BCE2_CHECKPOINTS_X[32] = -1000.2390 
BCE2_CHECKPOINTS_Y[32] = -2371.5085 
BCE2_CHECKPOINTS_Z[32] = 64.5107 
BCE2_CHECKPOINTS_X[33] = -958.5369 
BCE2_CHECKPOINTS_Y[33] = -2281.0547 
BCE2_CHECKPOINTS_Z[33] = 52.9462 
BCE2_CHECKPOINTS_X[34] = -939.7041 
BCE2_CHECKPOINTS_Y[34] = -2187.0952 
BCE2_CHECKPOINTS_Z[34] = 35.4659 
BCE2_CHECKPOINTS_X[35] = -841.8345 
BCE2_CHECKPOINTS_Y[35] = -2181.3782 
BCE2_CHECKPOINTS_Z[35] = 23.7405 
BCE2_CHECKPOINTS_X[36] = -790.3633 
BCE2_CHECKPOINTS_Y[36] = -2095.3428 
BCE2_CHECKPOINTS_Z[36] = 25.4038 
BCE2_CHECKPOINTS_X[37] = -844.4321 
BCE2_CHECKPOINTS_Y[37] = -2010.3385 
BCE2_CHECKPOINTS_Z[37] = 20.3482 
BCE2_CHECKPOINTS_X[38] = -809.2380 
BCE2_CHECKPOINTS_Y[38] = -1902.5402 
BCE2_CHECKPOINTS_Z[38] = 9.4239 
BCE2_CHECKPOINTS_X[39] = -735.2100 
BCE2_CHECKPOINTS_Y[39] = -1855.3033 
BCE2_CHECKPOINTS_Z[39] = 13.5207 
BCE2_CHECKPOINTS_X[40] = -641.8992 
BCE2_CHECKPOINTS_Y[40] = -1846.8608 
BCE2_CHECKPOINTS_Z[40] = 21.7356 
BCE2_CHECKPOINTS_X[41] = -546.9716 
BCE2_CHECKPOINTS_Y[41] = -1832.0959 
BCE2_CHECKPOINTS_Z[41] = 25.5599 
BCE2_CHECKPOINTS_X[42] = -460.5018 
BCE2_CHECKPOINTS_Y[42] = -1800.0122 
BCE2_CHECKPOINTS_Z[42] = 8.5607 
BCE2_CHECKPOINTS_X[43] = -424.1711 
BCE2_CHECKPOINTS_Y[43] = -1704.1381 
BCE2_CHECKPOINTS_Z[43] = 10.8356 
BCE2_CHECKPOINTS_X[44] = -410.4569 
BCE2_CHECKPOINTS_Y[44] = -1611.7396 
BCE2_CHECKPOINTS_Z[44] = 20.2907 
BCE2_CHECKPOINTS_X[45] = -358.7348 
BCE2_CHECKPOINTS_Y[45] = -1531.8839 
BCE2_CHECKPOINTS_Z[45] = 19.0268 
BCE2_CHECKPOINTS_X[46] = -342.9092 
BCE2_CHECKPOINTS_Y[46] = -1455.6631 
BCE2_CHECKPOINTS_Z[46] = 17.8712 
BCE2_CHECKPOINTS_X[47] = -243.4206 
BCE2_CHECKPOINTS_Y[47] = -1451.2201 
BCE2_CHECKPOINTS_Z[47] = 4.3198 
BCE2_CHECKPOINTS_X[48] = -193.0986 
BCE2_CHECKPOINTS_Y[48] = -1477.0382 
BCE2_CHECKPOINTS_Z[48] = 7.9595 
BCE2_CHECKPOINTS_X[49] = -68.0509 
BCE2_CHECKPOINTS_Y[49] = -1510.5131 
BCE2_CHECKPOINTS_Z[49] = 1.9303 
BCE2_CHECKPOINTS_X[50] = 31.2142 
BCE2_CHECKPOINTS_Y[50] = -1526.8812 
BCE2_CHECKPOINTS_Z[50] = 4.3931 
BCE2_CHECKPOINTS_X[51] = 129.3360 
BCE2_CHECKPOINTS_Y[51] = -1459.6118 
BCE2_CHECKPOINTS_Z[51] = 23.8768 
BCE2_CHECKPOINTS_X[52] = 139.1300 
BCE2_CHECKPOINTS_Y[52] = -1255.6493 
BCE2_CHECKPOINTS_Z[52] = 44.9903 
BCE2_CHECKPOINTS_X[53] = 266.6300 
BCE2_CHECKPOINTS_Y[53] = -995.6493 
BCE2_CHECKPOINTS_Z[53] = 50.9903 
BCE2_CHECKPOINTS_X[54] = 366.6300 
BCE2_CHECKPOINTS_Y[54] = -757.6493 
BCE2_CHECKPOINTS_Z[54] = 16.4903 
BCE2_CHECKPOINTS_X[55] = 424.1300 
BCE2_CHECKPOINTS_Y[55] = -567.6493 
BCE2_CHECKPOINTS_Z[55] = 38.9903 
BCE2_CHECKPOINTS_X[56] = 458.1300 
BCE2_CHECKPOINTS_Y[56] = -448.1493 
BCE2_CHECKPOINTS_Z[56] = 30.9903 
BCE2_CHECKPOINTS_X[57] = 338.1300 
BCE2_CHECKPOINTS_Y[57] = -390.6493 
BCE2_CHECKPOINTS_Z[57] = 12.9903 
BCE2_CHECKPOINTS_X[58] = 338.1300 
BCE2_CHECKPOINTS_Y[58] = -342.1493 
BCE2_CHECKPOINTS_Z[58] = 8.9903 
BCE2_CHECKPOINTS_X[59] = 475.1300 
BCE2_CHECKPOINTS_Y[59] = -264.6493 
BCE2_CHECKPOINTS_Z[59] = 9.9903 
BCE2_CHECKPOINTS_X[60] = 615.6300 
BCE2_CHECKPOINTS_Y[60] = -197.1493 
BCE2_CHECKPOINTS_Z[60] = 9.9903 
BCE2_CHECKPOINTS_X[61] = 757.1300 
BCE2_CHECKPOINTS_Y[61] = -152.1493 
BCE2_CHECKPOINTS_Z[61] = 18.4903 
BCE2_CHECKPOINTS_X[62] = 907.1300 
BCE2_CHECKPOINTS_Y[62] = -92.1493 
BCE2_CHECKPOINTS_Z[62] = 19.9903 
BCE2_CHECKPOINTS_X[63] = 1135.6300 
BCE2_CHECKPOINTS_Y[63] = -63.6493 
BCE2_CHECKPOINTS_Z[63] = 22.9903 
BCE2_CHECKPOINTS_X[64] = 1280.6300 
BCE2_CHECKPOINTS_Y[64] = -170.6493 
BCE2_CHECKPOINTS_Z[64] = 32.4903 
BCE2_CHECKPOINTS_X[65] = 1440.6300 
BCE2_CHECKPOINTS_Y[65] = -214.6493 
BCE2_CHECKPOINTS_Z[65] = 7.9903 
BCE2_CHECKPOINTS_X[66] = 1554.6300 
BCE2_CHECKPOINTS_Y[66] = -124.6493 
BCE2_CHECKPOINTS_Z[66] = 17.9903 
BCE2_CHECKPOINTS_X[67] = 1551.6300 
BCE2_CHECKPOINTS_Y[67] = 46.8507 
BCE2_CHECKPOINTS_Z[67] = 23.9903 
BCE2_CHECKPOINTS_X[68] = 1567.1300 
BCE2_CHECKPOINTS_Y[68] = 122.3507 
BCE2_CHECKPOINTS_Z[68] = 28.9903 
BCE2_CHECKPOINTS_X[69] = 1807.1300 
BCE2_CHECKPOINTS_Y[69] = 75.3507 
BCE2_CHECKPOINTS_Z[69] = 34.4903 
BCE2_CHECKPOINTS_X[70] = 2057.1299 
BCE2_CHECKPOINTS_Y[70] = 40.3507 
BCE2_CHECKPOINTS_Z[70] = 26.9903 
BCE2_CHECKPOINTS_X[71] = 2275.6299 
BCE2_CHECKPOINTS_Y[71] = 41.8507 
BCE2_CHECKPOINTS_Z[71] = 25.4903 
BCE2_CHECKPOINTS_X[72] = 2365.6299 
BCE2_CHECKPOINTS_Y[72] = 91.8507 
BCE2_CHECKPOINTS_Z[72] = 25.4903 
BCE2_CHECKPOINTS_X[73] = 2435.6299 
BCE2_CHECKPOINTS_Y[73] = 41.8507 
BCE2_CHECKPOINTS_Z[73] = 25.4903 
BCE2_CHECKPOINTS_X[74] = 2692.1299 
BCE2_CHECKPOINTS_Y[74] = 33.8507 
BCE2_CHECKPOINTS_Z[74] = 25.4903 
BCE2_CHECKPOINTS_X[75] = 2737.6299 
BCE2_CHECKPOINTS_Y[75] = -156.1493 
BCE2_CHECKPOINTS_Z[75] = 31.4903 
BCE2_CHECKPOINTS_X[76] = 2700.1299 
BCE2_CHECKPOINTS_Y[76] = -384.1493 
BCE2_CHECKPOINTS_Z[76] = 24.4903 
BCE2_CHECKPOINTS_X[77] = 2820.1299 
BCE2_CHECKPOINTS_Y[77] = -554.1493 
BCE2_CHECKPOINTS_Z[77] = 9.9903 
BCE2_CHECKPOINTS_X[78] = 2846.1299 
BCE2_CHECKPOINTS_Y[78] = -711.6493 
BCE2_CHECKPOINTS_Z[78] = 9.9903 
BCE2_CHECKPOINTS_X[79] = 2841.6299 
BCE2_CHECKPOINTS_Y[79] = -929.1493 
BCE2_CHECKPOINTS_Z[79] = 13.9903 
BCE2_CHECKPOINTS_X[80] = 2841.1299 
BCE2_CHECKPOINTS_Y[80] = -1182.6493 
BCE2_CHECKPOINTS_Z[80] = 23.9903 
BCE2_CHECKPOINTS_X[81] = 2873.1299 
BCE2_CHECKPOINTS_Y[81] = -1383.6493 
BCE2_CHECKPOINTS_Z[81] = 10.4903 
BCE2_CHECKPOINTS_X[82] = 2797.6299 
BCE2_CHECKPOINTS_Y[82] = -1380.6493 
BCE2_CHECKPOINTS_Z[82] = 20.9903 
BCE2_CHECKPOINTS_X[83] = 2795.1299 
BCE2_CHECKPOINTS_Y[83] = -1262.1493 
BCE2_CHECKPOINTS_Z[83] = 45.9903 
BCE2_CHECKPOINTS_X[84] = 2732.1299 
BCE2_CHECKPOINTS_Y[84] = -1257.6493 
BCE2_CHECKPOINTS_Z[84] = 58.9903 


// route for coke courier in bcesar2.sc - 3 alternative routes
VAR_FLOAT bce2_route1_x[116] bce2_route1_y[116] bce2_route1_z[116]
bce2_route1_x[0] = 2487.8459 
bce2_route1_y[0] = -1661.0308 
bce2_route1_z[0] = 13.0886 

bce2_route1_x[1] = 2387.6394 
bce2_route1_y[1] = -1657.8467 
bce2_route1_z[1] = 13.1425 

bce2_route1_x[2] = 2287.4861 
bce2_route1_y[2] = -1659.3157 
bce2_route1_z[2] = 14.6440 

bce2_route1_x[3] = 2188.2212 
bce2_route1_y[3] = -1639.7067 
bce2_route1_z[3] = 15.0652 

bce2_route1_x[4] = 2091.6714 
bce2_route1_y[4] = -1612.5411 
bce2_route1_z[4] = 13.1285 

bce2_route1_x[5] = 1990.5048 
bce2_route1_y[5] = -1612.7281 
bce2_route1_z[5] = 13.1392 

bce2_route1_x[6] = 1889.5447 
bce2_route1_y[6] = -1612.9204 
bce2_route1_z[6] = 13.1432 

bce2_route1_x[7] = 1788.5085 
bce2_route1_y[7] = -1609.7860 
bce2_route1_z[7] = 13.1208 

bce2_route1_x[8] = 1688.8561 
bce2_route1_y[8] = -1592.9879 
bce2_route1_z[8] = 13.1397 

bce2_route1_x[9] = 1587.9127 
bce2_route1_y[9] = -1589.5697 
bce2_route1_z[9] = 13.1277 

bce2_route1_x[10] = 1487.4830 
bce2_route1_y[10] = -1592.5898 
bce2_route1_z[10] = 13.1511 

bce2_route1_x[11] = 1386.5505 
bce2_route1_y[11] = -1586.9473 
bce2_route1_z[11] = 13.1234 

bce2_route1_x[12] = 1286.6620 
bce2_route1_y[12] = -1571.9493 
bce2_route1_z[12] = 13.1273 

bce2_route1_x[13] = 1185.7509 
bce2_route1_y[13] = -1570.8098 
bce2_route1_z[13] = 13.1410 

bce2_route1_x[14] = 1084.5911 
bce2_route1_y[14] = -1571.3870 
bce2_route1_z[14] = 13.1352 

bce2_route1_x[15] = 1057.1094 
bce2_route1_y[15] = -1474.3876 
bce2_route1_z[15] = 13.1259 

bce2_route1_x[16] = 1061.0756 
bce2_route1_y[16] = -1373.3380 
bce2_route1_z[16] = 13.1525 

bce2_route1_x[17] = 1057.5416 
bce2_route1_y[17] = -1273.0496 
bce2_route1_z[17] = 13.1425 

bce2_route1_x[18] = 1058.5311 
bce2_route1_y[18] = -1173.3695 
bce2_route1_z[18] = 23.0197 

bce2_route1_x[19] = 963.0153 
bce2_route1_y[19] = -1141.8625 
bce2_route1_z[19] = 23.4253 

bce2_route1_x[20] = 862.2285 
bce2_route1_y[20] = -1144.6819 
bce2_route1_z[20] = 23.4156 

bce2_route1_x[21] = 796.4694 
bce2_route1_y[21] = -1069.2076 
bce2_route1_z[21] = 24.2389 

bce2_route1_x[22] = 707.7682 
bce2_route1_y[22] = -1116.4126 
bce2_route1_z[22] = 17.7387 

bce2_route1_x[23] = 648.3205 
bce2_route1_y[23] = -1197.1847 
bce2_route1_z[23] = 17.2365 

bce2_route1_x[24] = 641.5527 
bce2_route1_y[24] = -1199.7443 
bce2_route1_z[24] = 17.7693 

bce2_route1_x[25] = 568.4156 
bce2_route1_y[25] = -1131.5087 
bce2_route1_z[25] = 26.2220 

bce2_route1_x[26] = 469.6543 
bce2_route1_y[26] = -1113.5897 
bce2_route1_z[26] = 27.6279 

bce2_route1_x[27] = 370.4434 
bce2_route1_y[27] = -1129.3823 
bce2_route1_z[27] = 25.3758 

bce2_route1_x[28] = 274.1698 
bce2_route1_y[28] = -1156.7511 
bce2_route1_z[28] = 21.1352 

bce2_route1_x[29] = 181.3566 
bce2_route1_y[29] = -1195.7822 
bce2_route1_z[29] = 17.3878 

bce2_route1_x[30] = 94.4232 
bce2_route1_y[30] = -1245.7322 
bce2_route1_z[30] = 14.5145 

bce2_route1_x[31] = 15.8414 
bce2_route1_y[31] = -1308.8579 
bce2_route1_z[31] = 12.0496 

bce2_route1_x[32] = -62.2601 
bce2_route1_y[32] = -1372.5801 
bce2_route1_z[32] = 11.3571 

bce2_route1_x[33] = -133.5467 
bce2_route1_y[33] = -1443.4331 
bce2_route1_z[33] = 12.5484 

bce2_route1_x[34] = -201.4483 
bce2_route1_y[34] = -1518.1517 
bce2_route1_z[34] = 14.3358 

bce2_route1_x[35] = -261.8274 
bce2_route1_y[35] = -1597.9913 
bce2_route1_z[35] = 15.6199 

bce2_route1_x[36] = -295.7058 
bce2_route1_y[36] = -1693.1434 
bce2_route1_z[36] = 14.4603 

bce2_route1_x[37] = -331.9772 
bce2_route1_y[37] = -1786.4961 
bce2_route1_z[37] = 17.6881 

bce2_route1_x[38] = -367.9241 
bce2_route1_y[38] = -1879.7872 
bce2_route1_z[38] = 25.9672 

bce2_route1_x[39] = -367.5844 
bce2_route1_y[39] = -1980.1935 
bce2_route1_z[39] = 27.9446 

bce2_route1_x[40] = -350.2859 
bce2_route1_y[40] = -2079.7227 
bce2_route1_z[40] = 28.0525 

bce2_route1_x[41] = -333.1001 
bce2_route1_y[41] = -2179.3442 
bce2_route1_z[41] = 28.1703 

bce2_route1_x[42] = -297.5232 
bce2_route1_y[42] = -2273.4280 
bce2_route1_z[42] = 29.7426 

bce2_route1_x[43] = -233.4477 
bce2_route1_y[43] = -2351.6929 
bce2_route1_z[43] = 31.5324 

bce2_route1_x[44] = -159.7139 
bce2_route1_y[44] = -2420.7952 
bce2_route1_z[44] = 35.9570 

bce2_route1_x[45] = -92.2604 
bce2_route1_y[45] = -2496.0085 
bce2_route1_z[45] = 38.2066 

bce2_route1_x[46] = -49.5180 
bce2_route1_y[46] = -2586.4094 
bce2_route1_z[46] = 43.3945 

bce2_route1_x[47] = -39.0012 
bce2_route1_y[47] = -2686.4084 
bce2_route1_z[47] = 42.5762 

bce2_route1_x[48] = -71.9678 
bce2_route1_y[48] = -2781.2827 
bce2_route1_z[48] = 39.1346 

bce2_route1_x[49] = -162.2194 
bce2_route1_y[49] = -2824.7280 
bce2_route1_z[49] = 40.6313 

bce2_route1_x[50] = -261.9651 
bce2_route1_y[50] = -2815.2700 
bce2_route1_z[50] = 50.8160 

bce2_route1_x[51] = -357.7323 
bce2_route1_y[51] = -2787.4031 
bce2_route1_z[51] = 61.6162 

bce2_route1_x[52] = -456.2191 
bce2_route1_y[52] = -2766.1841 
bce2_route1_z[52] = 66.2852 

bce2_route1_x[53] = -555.2378 
bce2_route1_y[53] = -2751.5491 
bce2_route1_z[53] = 65.7085 

bce2_route1_x[54] = -655.1880 
bce2_route1_y[54] = -2748.9766 
bce2_route1_z[54] = 69.8092 

bce2_route1_x[55] = -753.9544 
bce2_route1_y[55] = -2764.8953 
bce2_route1_z[55] = 74.4690 

bce2_route1_x[56] = -847.7780 
bce2_route1_y[56] = -2801.9629 
bce2_route1_z[56] = 70.6215 

bce2_route1_x[57] = -938.0959 
bce2_route1_y[57] = -2845.4795 
bce2_route1_z[57] = 68.4226 

bce2_route1_x[58] = -1038.6550 
bce2_route1_y[58] = -2853.1582 
bce2_route1_z[58] = 67.4485 

bce2_route1_x[59] = -1139.4188 
bce2_route1_y[59] = -2855.4377 
bce2_route1_z[59] = 67.4660 

bce2_route1_x[60] = -1239.4492 
bce2_route1_y[60] = -2860.8049 
bce2_route1_z[60] = 65.7173 

bce2_route1_x[61] = -1338.0901 
bce2_route1_y[61] = -2878.6682 
bce2_route1_z[61] = 55.5616 

bce2_route1_x[62] = -1435.3354 
bce2_route1_y[62] = -2853.4097 
bce2_route1_z[62] = 48.3088 

bce2_route1_x[63] = -1520.4025 
bce2_route1_y[63] = -2799.3149 
bce2_route1_z[63] = 46.6003 

bce2_route1_x[64] = -1600.9691 
bce2_route1_y[64] = -2739.9763 
bce2_route1_z[64] = 48.7977 

bce2_route1_x[65] = -1681.4296 
bce2_route1_y[65] = -2679.7649 
bce2_route1_z[65] = 48.2789 

bce2_route1_x[66] = -1745.1682 
bce2_route1_y[66] = -2601.5535 
bce2_route1_z[66] = 49.4470 

bce2_route1_x[67] = -1823.6808 
bce2_route1_y[67] = -2538.1213 
bce2_route1_z[67] = 52.7491 

bce2_route1_x[68] = -1923.9066 
bce2_route1_y[68] = -2539.9272 
bce2_route1_z[68] = 38.6757 

bce2_route1_x[69] = -2019.3063 
bce2_route1_y[69] = -2507.6572 
bce2_route1_z[69] = 32.3084 

bce2_route1_x[70] = -2100.1370 
bce2_route1_y[70] = -2446.7559 
bce2_route1_z[70] = 30.2321 

bce2_route1_x[71] = -2179.1575 
bce2_route1_y[71] = -2383.9011 
bce2_route1_z[71] = 30.2293 

bce2_route1_x[72] = -2190.1733 
bce2_route1_y[72] = -2284.4062 
bce2_route1_z[72] = 30.2286 

bce2_route1_x[73] = -2264.3403 
bce2_route1_y[73] = -2216.7771 
bce2_route1_z[73] = 30.8563 

bce2_route1_x[74] = -2182.7815 
bce2_route1_y[74] = -2160.8923 
bce2_route1_z[74] = 47.4936 

bce2_route1_x[75] = -2110.8284 
bce2_route1_y[75] = -2092.4265 
bce2_route1_z[75] = 61.5663 

bce2_route1_x[76] = -2078.4963 
bce2_route1_y[76] = -1997.3062 
bce2_route1_z[76] = 60.0120 

bce2_route1_x[77] = -2028.4880 
bce2_route1_y[77] = -1910.7743 
bce2_route1_z[77] = 48.5942 

bce2_route1_x[78] = -1971.9017 
bce2_route1_y[78] = -1828.0873 
bce2_route1_z[78] = 36.8357 

bce2_route1_x[79] = -1898.3462 
bce2_route1_y[79] = -1760.4561 
bce2_route1_z[79] = 29.4533 

bce2_route1_x[80] = -1808.7107 
bce2_route1_y[80] = -1715.5684 
bce2_route1_z[80] = 28.8706 

bce2_route1_x[81] = -1724.8386 
bce2_route1_y[81] = -1660.9567 
bce2_route1_z[81] = 36.2003 

bce2_route1_x[82] = -1631.2233 
bce2_route1_y[82] = -1624.2671 
bce2_route1_z[82] = 36.0242 

bce2_route1_x[83] = -1546.3738 
bce2_route1_y[83] = -1570.5679 
bce2_route1_z[83] = 37.4867 

bce2_route1_x[84] = -1626.0752 
bce2_route1_y[84] = -1510.0033 
bce2_route1_z[84] = 37.0858 

bce2_route1_x[85] = -1722.5310 
bce2_route1_y[85] = -1483.5496 
bce2_route1_z[85] = 34.2269 

bce2_route1_x[86] = -1807.4095 
bce2_route1_y[86] = -1430.1053 
bce2_route1_z[86] = 35.7400 

bce2_route1_x[87] = -1894.5845 
bce2_route1_y[87] = -1380.6718 
bce2_route1_z[87] = 39.6104 

bce2_route1_x[88] = -1899.0354 
bce2_route1_y[88] = -1280.7308 
bce2_route1_z[88] = 39.2288 

bce2_route1_x[89] = -1896.8789 
bce2_route1_y[89] = -1179.8798 
bce2_route1_z[89] = 38.8035 

bce2_route1_x[90] = -1900.2380 
bce2_route1_y[90] = -1078.9877 
bce2_route1_z[90] = 37.9866 

bce2_route1_x[91] = -1896.7563 
bce2_route1_y[91] = -978.9510 
bce2_route1_z[91] = 41.2545 

bce2_route1_x[92] = -1896.0155 
bce2_route1_y[92] = -878.1399 
bce2_route1_z[92] = 44.7055 

bce2_route1_x[93] = -1897.2408 
bce2_route1_y[93] = -777.6757 
bce2_route1_z[93] = 44.6920 

bce2_route1_x[94] = -1895.9591 
bce2_route1_y[94] = -677.4996 
bce2_route1_z[94] = 41.0312 

bce2_route1_x[95] = -1894.7368 
bce2_route1_y[95] = -576.5858 
bce2_route1_z[95] = 38.0016 

bce2_route1_x[96] = -1897.7098 
bce2_route1_y[96] = -476.4786 
bce2_route1_z[96] = 37.9914 

bce2_route1_x[97] = -1896.1544 
bce2_route1_y[97] = -376.3374 
bce2_route1_z[97] = 37.9879 

bce2_route1_x[98] = -1896.8944 
bce2_route1_y[98] = -275.5032 
bce2_route1_z[98] = 37.9875 

bce2_route1_x[99] = -1895.7302 
bce2_route1_y[99] = -174.2612 
bce2_route1_z[99] = 37.9884 

bce2_route1_x[100] = -1890.8373 
bce2_route1_y[100] = -73.2693 
bce2_route1_z[100] = 38.0012 

bce2_route1_x[101] = -1890.6152 
bce2_route1_y[101] = 27.5906 
bce2_route1_z[101] = 38.0035 

bce2_route1_x[102] = -1874.6201 
bce2_route1_y[102] = 127.2809 
bce2_route1_z[102] = 37.9871 

bce2_route1_x[103] = -1842.7549 
bce2_route1_y[103] = 222.3595 
bce2_route1_z[103] = 37.9736 

bce2_route1_x[104] = -1776.7367 
bce2_route1_y[104] = 296.3020 
bce2_route1_z[104] = 23.6187 

bce2_route1_x[105] = -1687.5623 
bce2_route1_y[105] = 341.3000 
bce2_route1_z[105] = 13.5061 

bce2_route1_x[106] = -1620.4059 
bce2_route1_y[106] = 416.1430 
bce2_route1_z[106] = 7.1363 

bce2_route1_x[107] = -1574.6442 
bce2_route1_y[107] = 506.4197 
bce2_route1_z[107] = 6.9799 

bce2_route1_x[108] = -1557.7152 
bce2_route1_y[108] = 605.5936 
bce2_route1_z[108] = 6.9312 

bce2_route1_x[109] = -1550.6027 
bce2_route1_y[109] = 705.7773 
bce2_route1_z[109] = 6.8277 

bce2_route1_x[110] = -1532.9750 
bce2_route1_y[110] = 806.2415 
bce2_route1_z[110] = 6.8058 

bce2_route1_x[111] = -1525.1520 
bce2_route1_y[111] = 906.8005 
bce2_route1_z[111] = 6.8066 

bce2_route1_x[112] = -1560.4280 
bce2_route1_y[112] = 1000.3951 
bce2_route1_z[112] = 6.8073 

bce2_route1_x[113] = -1580.5276 
bce2_route1_y[113] = 1099.0255 
bce2_route1_z[113] = 6.8050 

bce2_route1_x[114] = -1593.1057 
bce2_route1_y[114] = 1198.8236 
bce2_route1_z[114] = 6.7993 

bce2_route1_x[115] = -1612.7999 
bce2_route1_y[115] = 1284.9078 
bce2_route1_z[115] = 6.9396 

VAR_FLOAT bce2_route2_x[98] bce2_route2_y[98] bce2_route2_z[98]
bce2_route2_x[0] = 2480.5967 
bce2_route2_y[0] = -1659.1562 
bce2_route2_z[0] = 13.0965 

bce2_route2_x[1] = 2379.8811 
bce2_route2_y[1] = -1658.1934 
bce2_route2_z[1] = 13.1467 

bce2_route2_x[2] = 2344.3992 
bce2_route2_y[2] = -1564.4354 
bce2_route2_z[2] = 23.9333 

bce2_route2_x[3] = 2343.6719 
bce2_route2_y[3] = -1463.3198 
bce2_route2_z[3] = 23.6044 

bce2_route2_x[4] = 2305.2749 
bce2_route2_y[4] = -1370.7926 
bce2_route2_z[4] = 23.6319 

bce2_route2_x[5] = 2303.4407 
bce2_route2_y[5] = -1269.6060 
bce2_route2_z[5] = 23.5946 

bce2_route2_x[6] = 2306.2170 
bce2_route2_y[6] = -1169.4031 
bce2_route2_z[6] = 26.0798 

bce2_route2_x[7] = 2213.0703 
bce2_route2_y[7] = -1132.1012 
bce2_route2_z[7] = 25.3774 

bce2_route2_x[8] = 2116.1975 
bce2_route2_y[8] = -1104.9049 
bce2_route2_z[8] = 24.8605 

bce2_route2_x[9] = 2022.4779 
bce2_route2_y[9] = -1067.8966 
bce2_route2_z[9] = 24.2557 

bce2_route2_x[10] = 1925.3049 
bce2_route2_y[10] = -1039.7681 
bce2_route2_z[10] = 23.6120 

bce2_route2_x[11] = 1867.6505 
bce2_route2_y[11] = -1121.5543 
bce2_route2_z[11] = 23.4686 

bce2_route2_x[12] = 1777.4077 
bce2_route2_y[12] = -1164.6450 
bce2_route2_z[12] = 23.5025 

bce2_route2_x[13] = 1677.1761 
bce2_route2_y[13] = -1162.4050 
bce2_route2_z[13] = 23.4443 

bce2_route2_x[14] = 1576.5533 
bce2_route2_y[14] = -1163.3073 
bce2_route2_z[14] = 23.7111 

bce2_route2_x[15] = 1476.0017 
bce2_route2_y[15] = -1159.8448 
bce2_route2_z[15] = 23.6522 

bce2_route2_x[16] = 1376.9645 
bce2_route2_y[16] = -1140.1335 
bce2_route2_z[16] = 23.4968 

bce2_route2_x[17] = 1366.8094 
bce2_route2_y[17] = -1040.2178 
bce2_route2_z[17] = 26.2347 

bce2_route2_x[18] = 1371.4180 
bce2_route2_y[18] = -940.2704 
bce2_route2_z[18] = 33.9599 

bce2_route2_x[19] = 1273.0294 
bce2_route2_y[19] = -922.0916 
bce2_route2_z[19] = 41.8145 

bce2_route2_x[20] = 1174.4037 
bce2_route2_y[20] = -940.6694 
bce2_route2_z[20] = 42.5196 

bce2_route2_x[21] = 1156.8193 
bce2_route2_y[21] = -842.2726 
bce2_route2_z[21] = 49.7390 

bce2_route2_x[22] = 1164.0050 
bce2_route2_y[22] = -742.3879 
bce2_route2_z[22] = 60.3190 

bce2_route2_x[23] = 1202.7288 
bce2_route2_y[23] = -649.9714 
bce2_route2_z[23] = 59.6176 

bce2_route2_x[24] = 1231.2836 
bce2_route2_y[24] = -554.9558 
bce2_route2_z[24] = 40.3091 

bce2_route2_x[25] = 1257.2252 
bce2_route2_y[25] = -462.3789 
bce2_route2_z[25] = 12.7939 

bce2_route2_x[26] = 1255.6779 
bce2_route2_y[26] = -362.5274 
bce2_route2_z[26] = 2.9890 

bce2_route2_x[27] = 1230.6844 
bce2_route2_y[27] = -267.0266 
bce2_route2_z[27] = 18.9881 

bce2_route2_x[28] = 1188.9194 
bce2_route2_y[28] = -178.3956 
bce2_route2_z[28] = 40.7223 

bce2_route2_x[29] = 1088.5599 
bce2_route2_y[29] = -182.8582 
bce2_route2_z[29] = 39.8590 

bce2_route2_x[30] = 989.9222 
bce2_route2_y[30] = -180.4384 
bce2_route2_z[30] = 17.6728 

bce2_route2_x[31] = 889.5374 
bce2_route2_y[31] = -173.0015 
bce2_route2_z[31] = 10.7641 

bce2_route2_x[32] = 788.8903 
bce2_route2_y[32] = -164.4205 
bce2_route2_z[32] = 16.4595 

bce2_route2_x[33] = 689.4522 
bce2_route2_y[33] = -155.3612 
bce2_route2_z[33] = 22.8900 

bce2_route2_x[34] = 589.6461 
bce2_route2_y[34] = -146.5947 
bce2_route2_z[34] = 29.7470 

bce2_route2_x[35] = 510.9780 
bce2_route2_y[35] = -208.5235 
bce2_route2_z[35] = 37.1529 

bce2_route2_x[36] = 491.5316 
bce2_route2_y[36] = -306.6292 
bce2_route2_z[36] = 42.7684 

bce2_route2_x[37] = 464.9347 
bce2_route2_y[37] = -402.1279 
bce2_route2_z[37] = 32.6014 

bce2_route2_x[38] = 370.5219 
bce2_route2_y[38] = -393.0328 
bce2_route2_z[38] = 23.9504 

bce2_route2_x[39] = 272.6069 
bce2_route2_y[39] = -378.1253 
bce2_route2_z[39] = 2.4636 

bce2_route2_x[40] = 206.3447 
bce2_route2_y[40] = -301.5085 
bce2_route2_z[40] = 1.1901 

bce2_route2_x[41] = 157.5706 
bce2_route2_y[41] = -213.2552 
bce2_route2_z[41] = 1.1960 

bce2_route2_x[42] = 57.3466 
bce2_route2_y[42] = -208.4595 
bce2_route2_z[42] = 1.2183 

bce2_route2_x[43] = -43.1581 
bce2_route2_y[43] = -203.3529 
bce2_route2_z[43] = 1.3731 

bce2_route2_x[44] = -142.4222 
bce2_route2_y[44] = -187.6390 
bce2_route2_z[44] = 1.6178 

bce2_route2_x[45] = -241.4735 
bce2_route2_y[45] = -167.4589 
bce2_route2_z[45] = 2.0199 

bce2_route2_x[46] = -298.0107 
bce2_route2_y[46] = -84.8255 
bce2_route2_z[46] = 1.2111 

bce2_route2_x[47] = -284.5947 
bce2_route2_y[47] = 14.7808 
bce2_route2_z[47] = 0.8422 

bce2_route2_x[48] = -255.5891 
bce2_route2_y[48] = 111.3571 
bce2_route2_z[48] = 0.8426 

bce2_route2_x[49] = -213.1467 
bce2_route2_y[49] = 202.6102 
bce2_route2_z[49] = 1.8256 

bce2_route2_x[50] = -292.4320 
bce2_route2_y[50] = 264.4556 
bce2_route2_z[50] = 1.8382 

bce2_route2_x[51] = -391.0918 
bce2_route2_y[51] = 282.8860 
bce2_route2_z[51] = 1.8926 

bce2_route2_x[52] = -491.8620 
bce2_route2_y[52] = 287.5233 
bce2_route2_z[52] = 1.8369 

bce2_route2_x[53] = -592.0175 
bce2_route2_y[53] = 284.7173 
bce2_route2_z[53] = 1.8298 

bce2_route2_x[54] = -686.3195 
bce2_route2_y[54] = 250.5801 
bce2_route2_z[54] = 1.8305 

bce2_route2_x[55] = -770.4146 
bce2_route2_y[55] = 194.5963 
bce2_route2_z[55] = 1.6198 

bce2_route2_x[56] = -713.9178 
bce2_route2_y[56] = 113.2328 
bce2_route2_z[56] = 15.6558 

bce2_route2_x[57] = -759.8053 
bce2_route2_y[57] = 26.0654 
bce2_route2_z[57] = 33.0027 

bce2_route2_x[58] = -856.7950 
bce2_route2_y[58] = -0.6516 
bce2_route2_z[58] = 32.9974 

bce2_route2_x[59] = -759.7305 
bce2_route2_y[59] = -20.4226 
bce2_route2_z[59] = 47.2165 

bce2_route2_x[60] = -801.2752 
bce2_route2_y[60] = -110.4809 
bce2_route2_z[60] = 63.4186 

bce2_route2_x[61] = -898.1945 
bce2_route2_y[61] = -137.0961 
bce2_route2_z[61] = 56.3295 

bce2_route2_x[62] = -946.6151 
bce2_route2_y[62] = -223.7240 
bce2_route2_z[62] = 38.9582 

bce2_route2_x[63] = -968.4164 
bce2_route2_y[63] = -321.7283 
bce2_route2_z[63] = 36.1420 

bce2_route2_x[64] = -991.3627 
bce2_route2_y[64] = -419.3090 
bce2_route2_z[64] = 36.0027 

bce2_route2_x[65] = -1077.7527 
bce2_route2_y[65] = -470.2693 
bce2_route2_z[65] = 33.7616 

bce2_route2_x[66] = -1136.2520 
bce2_route2_y[66] = -551.6271 
bce2_route2_z[66] = 29.8955 

bce2_route2_x[67] = -1170.4977 
bce2_route2_y[67] = -645.1342 
bce2_route2_z[67] = 42.4828 

bce2_route2_x[68] = -1205.1332 
bce2_route2_y[68] = -737.6744 
bce2_route2_z[68] = 59.3098 

bce2_route2_x[69] = -1284.4039 
bce2_route2_y[69] = -798.1140 
bce2_route2_z[69] = 69.5010 

bce2_route2_x[70] = -1381.9583 
bce2_route2_y[70] = -817.3228 
bce2_route2_z[70] = 81.1519 

bce2_route2_x[71] = -1481.5657 
bce2_route2_y[71] = -815.1219 
bce2_route2_z[71] = 66.9058 

bce2_route2_x[72] = -1579.5100 
bce2_route2_y[72] = -800.5511 
bce2_route2_z[72] = 49.9587 

bce2_route2_x[73] = -1673.1958 
bce2_route2_y[73] = -765.3336 
bce2_route2_z[73] = 41.5955 

bce2_route2_x[74] = -1754.9943 
bce2_route2_y[74] = -708.9478 
bce2_route2_z[74] = 28.2593 

bce2_route2_x[75] = -1761.4960 
bce2_route2_y[75] = -609.3593 
bce2_route2_z[75] = 15.9606 

bce2_route2_x[76] = -1820.3696 
bce2_route2_y[76] = -528.3566 
bce2_route2_z[76] = 14.6995 

bce2_route2_x[77] = -1814.5952 
bce2_route2_y[77] = -427.9472 
bce2_route2_z[77] = 14.7211 

bce2_route2_x[78] = -1803.9630 
bce2_route2_y[78] = -328.1352 
bce2_route2_z[78] = 14.7287 

bce2_route2_x[79] = -1801.3763 
bce2_route2_y[79] = -227.7857 
bce2_route2_z[79] = 14.7103 

bce2_route2_x[80] = -1801.0194 
bce2_route2_y[80] = -126.9901 
bce2_route2_z[80] = 12.5775 

bce2_route2_x[81] = -1797.8406 
bce2_route2_y[81] = -25.8091 
bce2_route2_z[81] = 14.8934 

bce2_route2_x[82] = -1805.5312 
bce2_route2_y[82] = 73.9609 
bce2_route2_z[82] = 14.7143 

bce2_route2_x[83] = -1807.1492 
bce2_route2_y[83] = 174.5928 
bce2_route2_z[83] = 14.7138 

bce2_route2_x[84] = -1800.1945 
bce2_route2_y[84] = 275.5728 
bce2_route2_z[84] = 10.6028 

bce2_route2_x[85] = -1810.2872 
bce2_route2_y[85] = 375.1400 
bce2_route2_z[85] = 9.7906 

bce2_route2_x[86] = -1814.4094 
bce2_route2_y[86] = 474.6516 
bce2_route2_z[86] = 23.4117 

bce2_route2_x[87] = -1877.9443 
bce2_route2_y[87] = 551.0918 
bce2_route2_z[87] = 34.4247 

bce2_route2_x[88] = -1896.8612 
bce2_route2_y[88] = 649.7911 
bce2_route2_z[88] = 37.0183 

bce2_route2_x[89] = -1895.0509 
bce2_route2_y[89] = 750.2951 
bce2_route2_z[89] = 45.0510 

bce2_route2_x[90] = -1893.9368 
bce2_route2_y[90] = 850.3843 
bce2_route2_z[90] = 34.7833 

bce2_route2_x[91] = -1820.1434 
bce2_route2_y[91] = 917.2139 
bce2_route2_z[91] = 24.9892 

bce2_route2_x[92] = -1719.0219 
bce2_route2_y[92] = 917.0076 
bce2_route2_z[92] = 24.4954 

bce2_route2_x[93] = -1619.3035 
bce2_route2_y[93] = 916.6608 
bce2_route2_z[93] = 7.6340 

bce2_route2_x[94] = -1555.8248 
bce2_route2_y[94] = 994.0962 
bce2_route2_z[94] = 6.8065 

bce2_route2_x[95] = -1579.9631 
bce2_route2_y[95] = 1091.5480 
bce2_route2_z[95] = 6.8070 

bce2_route2_x[96] = -1588.5881 
bce2_route2_y[96] = 1191.3062 
bce2_route2_z[96] = 6.7980 

bce2_route2_x[97] = -1612.3529 
bce2_route2_y[97] = 1281.6711 
bce2_route2_z[97] = 6.9398 
 
VAR_FLOAT bce2_route3_x[117] bce2_route3_y[117] bce2_route3_z[117]
bce2_route3_x[0] = 2488.2734 
bce2_route3_y[0] = -1659.9685 
bce2_route3_z[0] = 13.0884 

bce2_route3_x[1] = 2387.7185 
bce2_route3_y[1] = -1658.0190 
bce2_route3_z[1] = 13.1467 

bce2_route3_x[2] = 2286.9495 
bce2_route3_y[2] = -1658.5829 
bce2_route3_z[2] = 14.6557 

bce2_route3_x[3] = 2187.4509 
bce2_route3_y[3] = -1638.5334 
bce2_route3_z[3] = 15.0393 

bce2_route3_x[4] = 2090.3352 
bce2_route3_y[4] = -1611.9055 
bce2_route3_z[4] = 13.1306 

bce2_route3_x[5] = 1990.3311 
bce2_route3_y[5] = -1612.0214 
bce2_route3_z[5] = 13.1388 

bce2_route3_x[6] = 1888.9597 
bce2_route3_y[6] = -1614.3855 
bce2_route3_z[6] = 13.1433 

bce2_route3_x[7] = 1787.2432 
bce2_route3_y[7] = -1607.7681 
bce2_route3_z[7] = 13.1244 

bce2_route3_x[8] = 1687.1349 
bce2_route3_y[8] = -1592.2725 
bce2_route3_z[8] = 13.1375 

bce2_route3_x[9] = 1586.9517 
bce2_route3_y[9] = -1591.0009 
bce2_route3_z[9] = 13.1297 

bce2_route3_x[10] = 1486.7264 
bce2_route3_y[10] = -1590.7788 
bce2_route3_z[10] = 13.1520 

bce2_route3_x[11] = 1386.1813 
bce2_route3_y[11] = -1585.6071 
bce2_route3_z[11] = 13.1003 

bce2_route3_x[12] = 1286.6620 
bce2_route3_y[12] = -1572.9780 
bce2_route3_z[12] = 13.1664 

bce2_route3_x[13] = 1186.3978 
bce2_route3_y[13] = -1571.8763 
bce2_route3_z[13] = 13.1464 

bce2_route3_x[14] = 1085.4904 
bce2_route3_y[14] = -1570.8574 
bce2_route3_z[14] = 13.1351 

bce2_route3_x[15] = 985.0605 
bce2_route3_y[15] = -1571.4310 
bce2_route3_z[15] = 13.1410 

bce2_route3_x[16] = 884.2000 
bce2_route3_y[16] = -1577.3645 
bce2_route3_z[16] = 13.1300 

bce2_route3_x[17] = 815.0362 
bce2_route3_y[17] = -1650.2667 
bce2_route3_z[17] = 13.1344 

bce2_route3_x[18] = 810.6691 
bce2_route3_y[18] = -1750.1876 
bce2_route3_z[18] = 13.1276 

bce2_route3_x[19] = 710.2051 
bce2_route3_y[19] = -1749.9042 
bce2_route3_z[19] = 14.0481 

bce2_route3_x[20] = 612.0961 
bce2_route3_y[20] = -1727.2224 
bce2_route3_z[20] = 13.6595 

bce2_route3_x[21] = 512.0588 
bce2_route3_y[21] = -1711.1066 
bce2_route3_z[21] = 11.9235 

bce2_route3_x[22] = 411.9842 
bce2_route3_y[22] = -1701.3540 
bce2_route3_z[22] = 8.9795 

bce2_route3_x[23] = 310.8292 
bce2_route3_y[23] = -1701.1274 
bce2_route3_z[23] = 6.3614 

bce2_route3_x[24] = 210.8359 
bce2_route3_y[24] = -1692.6205 
bce2_route3_z[24] = 7.4032 

bce2_route3_x[25] = 162.6634 
bce2_route3_y[25] = -1604.7183 
bce2_route3_z[25] = 15.5120 

bce2_route3_x[26] = 96.6228 
bce2_route3_y[26] = -1530.2887 
bce2_route3_z[26] = 5.2206 

bce2_route3_x[27] = -4.3045 
bce2_route3_y[27] = -1520.7649 
bce2_route3_z[27] = 1.3181 

bce2_route3_x[28] = -100.2442 
bce2_route3_y[28] = -1492.0179 
bce2_route3_z[28] = 2.4440 

bce2_route3_x[29] = -150.6629 
bce2_route3_y[29] = -1404.9119 
bce2_route3_z[29] = 2.4387 

bce2_route3_x[30] = -125.7253 
bce2_route3_y[30] = -1307.6393 
bce2_route3_z[30] = 2.6123 

bce2_route3_x[31] = -85.3976 
bce2_route3_y[31] = -1398.9006 
bce2_route3_z[31] = 11.9881 

bce2_route3_x[32] = -156.1722 
bce2_route3_y[32] = -1470.0463 
bce2_route3_z[32] = 12.5570 

bce2_route3_x[33] = -224.7423 
bce2_route3_y[33] = -1543.4398 
bce2_route3_z[33] = 15.2432 

bce2_route3_x[34] = -278.3897 
bce2_route3_y[34] = -1627.8358 
bce2_route3_z[34] = 15.4404 

bce2_route3_x[35] = -309.6147 
bce2_route3_y[35] = -1722.8911 
bce2_route3_z[35] = 14.4331 

bce2_route3_x[36] = -347.4445 
bce2_route3_y[36] = -1815.8053 
bce2_route3_z[36] = 20.3824 

bce2_route3_x[37] = -375.8723 
bce2_route3_y[37] = -1911.9751 
bce2_route3_z[37] = 27.4563 

bce2_route3_x[38] = -367.3835 
bce2_route3_y[38] = -2012.2582 
bce2_route3_z[38] = 27.9796 

bce2_route3_x[39] = -352.2570 
bce2_route3_y[39] = -2111.5547 
bce2_route3_z[39] = 28.1054 

bce2_route3_x[40] = -325.2379 
bce2_route3_y[40] = -2208.2542 
bce2_route3_z[40] = 28.2795 

bce2_route3_x[41] = -411.8825 
bce2_route3_y[41] = -2256.1262 
bce2_route3_z[41] = 43.9768 

bce2_route3_x[42] = -507.0649 
bce2_route3_y[42] = -2286.2520 
bce2_route3_z[42] = 32.8761 

bce2_route3_x[43] = -579.3828 
bce2_route3_y[43] = -2355.2971 
bce2_route3_z[43] = 26.9589 

bce2_route3_x[44] = -678.7022 
bce2_route3_y[44] = -2358.3069 
bce2_route3_z[44] = 38.2611 

bce2_route3_x[45] = -752.1234 
bce2_route3_y[45] = -2421.5166 
bce2_route3_z[45] = 63.3924 

bce2_route3_x[46] = -819.2419 
bce2_route3_y[46] = -2493.3655 
bce2_route3_z[46] = 82.6824 

bce2_route3_x[47] = -890.7695 
bce2_route3_y[47] = -2563.2791 
bce2_route3_z[47] = 90.2311 

bce2_route3_x[48] = -981.5709 
bce2_route3_y[48] = -2605.9622 
bce2_route3_z[48] = 86.4663 

bce2_route3_x[49] = -1080.2250 
bce2_route3_y[49] = -2592.2146 
bce2_route3_z[49] = 76.2713 

bce2_route3_x[50] = -1161.9288 
bce2_route3_y[50] = -2534.0103 
bce2_route3_z[50] = 67.2918 

bce2_route3_x[51] = -1179.8179 
bce2_route3_y[51] = -2436.0122 
bce2_route3_z[51] = 53.4017 

bce2_route3_x[52] = -1164.9827 
bce2_route3_y[52] = -2337.2434 
bce2_route3_z[52] = 41.3150 

bce2_route3_x[53] = -1134.1306 
bce2_route3_y[53] = -2242.2495 
bce2_route3_z[53] = 33.4991 

bce2_route3_x[54] = -1097.9718 
bce2_route3_y[54] = -2148.7363 
bce2_route3_z[54] = 35.7186 

bce2_route3_x[55] = -1047.9979 
bce2_route3_y[55] = -2064.0186 
bce2_route3_z[55] = 54.2015 

bce2_route3_x[56] = -998.8296 
bce2_route3_y[56] = -1979.0826 
bce2_route3_z[56] = 73.7414 

bce2_route3_x[57] = -1062.2003 
bce2_route3_y[57] = -1901.6058 
bce2_route3_z[57] = 76.9899 

bce2_route3_x[58] = -1161.3781 
bce2_route3_y[58] = -1886.0137 
bce2_route3_z[58] = 77.9100 

bce2_route3_x[59] = -1206.3379 
bce2_route3_y[59] = -1800.1638 
bce2_route3_z[59] = 51.1433 

bce2_route3_x[60] = -1271.5658 
bce2_route3_y[60] = -1723.9021 
bce2_route3_z[60] = 45.7799 

bce2_route3_x[61] = -1353.9108 
bce2_route3_y[61] = -1666.7391 
bce2_route3_z[61] = 45.1373 

bce2_route3_x[62] = -1446.1687 
bce2_route3_y[62] = -1627.6653 
bce2_route3_z[62] = 44.3930 

bce2_route3_x[63] = -1543.7992 
bce2_route3_y[63] = -1605.5111 
bce2_route3_z[63] = 37.4879 

bce2_route3_x[64] = -1583.0184 
bce2_route3_y[64] = -1513.1573 
bce2_route3_z[64] = 37.4862 

bce2_route3_x[65] = -1681.6188 
bce2_route3_y[65] = -1493.6740 
bce2_route3_z[65] = 34.7810 

bce2_route3_x[66] = -1771.4012 
bce2_route3_y[66] = -1448.0941 
bce2_route3_z[66] = 34.5515 

bce2_route3_x[67] = -1859.2131 
bce2_route3_y[67] = -1399.6277 
bce2_route3_z[67] = 38.1758 

bce2_route3_x[68] = -1944.4756 
bce2_route3_y[68] = -1347.2108 
bce2_route3_z[68] = 40.5590 

bce2_route3_x[69] = -2006.2007 
bce2_route3_y[69] = -1268.1807 
bce2_route3_z[69] = 36.2375 

bce2_route3_x[70] = -2057.9255 
bce2_route3_y[70] = -1182.2268 
bce2_route3_z[70] = 31.9717 

bce2_route3_x[71] = -2105.2441 
bce2_route3_y[71] = -1093.7583 
bce2_route3_z[71] = 29.8948 

bce2_route3_x[72] = -2169.4324 
bce2_route3_y[72] = -1016.5877 
bce2_route3_z[72] = 33.6278 

bce2_route3_x[73] = -2214.3291 
bce2_route3_y[73] = -927.0612 
bce2_route3_z[73] = 42.1936 

bce2_route3_x[74] = -2196.7119 
bce2_route3_y[74] = -829.6929 
bce2_route3_z[74] = 58.5525 

bce2_route3_x[75] = -2261.1775 
bce2_route3_y[75] = -755.0598 
bce2_route3_z[75] = 75.9781 

bce2_route3_x[76] = -2341.9609 
bce2_route3_y[76] = -707.3872 
bce2_route3_z[76] = 110.6889 

bce2_route3_x[77] = -2400.1821 
bce2_route3_y[77] = -628.3421 
bce2_route3_z[77] = 131.5750 

bce2_route3_x[78] = -2453.6477 
bce2_route3_y[78] = -544.3860 
bce2_route3_z[78] = 121.1835 

bce2_route3_x[79] = -2526.8940 
bce2_route3_y[79] = -485.9847 
bce2_route3_z[79] = 85.0712 

bce2_route3_x[80] = -2446.8440 
bce2_route3_y[80] = -425.2606 
bce2_route3_z[80] = 84.4373 

bce2_route3_x[81] = -2354.2512 
bce2_route3_y[81] = -463.4032 
bce2_route3_z[81] = 79.9480 

bce2_route3_x[82] = -2389.5664 
bce2_route3_y[82] = -369.9571 
bce2_route3_z[82] = 75.0462 

bce2_route3_x[83] = -2489.3540 
bce2_route3_y[83] = -363.9357 
bce2_route3_z[83] = 63.4133 

bce2_route3_x[84] = -2588.5708 
bce2_route3_y[84] = -366.5697 
bce2_route3_z[84] = 46.1223 

bce2_route3_x[85] = -2672.8975 
bce2_route3_y[85] = -418.9947 
bce2_route3_z[85] = 31.9261 

bce2_route3_x[86] = -2681.2349 
bce2_route3_y[86] = -517.9654 
bce2_route3_z[86] = 17.1435 

bce2_route3_x[87] = -2777.1021 
bce2_route3_y[87] = -490.7432 
bce2_route3_z[87] = 6.9382 

bce2_route3_x[88] = -2818.1912 
bce2_route3_y[88] = -399.3717 
bce2_route3_z[88] = 6.8041 

bce2_route3_x[89] = -2811.5259 
bce2_route3_y[89] = -299.0307 
bce2_route3_z[89] = 6.8035 

bce2_route3_x[90] = -2805.6685 
bce2_route3_y[90] = -198.8723 
bce2_route3_z[90] = 6.7879 

bce2_route3_x[91] = -2808.8538 
bce2_route3_y[91] = -97.9767 
bce2_route3_z[91] = 6.7909 

bce2_route3_x[92] = -2809.0925 
bce2_route3_y[92] = 2.2906 
bce2_route3_z[92] = 6.8002 

bce2_route3_x[93] = -2804.6851 
bce2_route3_y[93] = 102.3091 
bce2_route3_z[93] = 6.8277 

bce2_route3_x[94] = -2806.4744 
bce2_route3_y[94] = 202.4786 
bce2_route3_z[94] = 6.8070 

bce2_route3_x[95] = -2807.9341 
bce2_route3_y[95] = 302.7264 
bce2_route3_z[95] = 6.3467 

bce2_route3_x[96] = -2849.5256 
bce2_route3_y[96] = 394.1544 
bce2_route3_z[96] = 4.1824 

bce2_route3_x[97] = -2843.3735 
bce2_route3_y[97] = 494.5805 
bce2_route3_z[97] = 4.1463 

bce2_route3_x[98] = -2825.5916 
bce2_route3_y[98] = 593.7442 
bce2_route3_z[98] = 5.2247 

bce2_route3_x[99] = -2830.8030 
bce2_route3_y[99] = 692.4010 
bce2_route3_z[99] = 22.7063 

bce2_route3_x[100] = -2856.0066 
bce2_route3_y[100] = 788.6620 
bce2_route3_z[100] = 35.7990 

bce2_route3_x[101] = -2817.7917 
bce2_route3_y[101] = 880.8148 
bce2_route3_z[101] = 43.7304 

bce2_route3_x[102] = -2840.3862 
bce2_route3_y[102] = 978.9179 
bce2_route3_z[102] = 43.2024 

bce2_route3_x[103] = -2876.4573 
bce2_route3_y[103] = 1071.9049 
bce2_route3_z[103] = 30.8111 

bce2_route3_x[104] = -2875.8850 
bce2_route3_y[104] = 1169.9523 
bce2_route3_z[104] = 11.0039 

bce2_route3_x[105] = -2826.6016 
bce2_route3_y[105] = 1257.5009 
bce2_route3_z[105] = 5.3188 

bce2_route3_x[106] = -2728.8149 
bce2_route3_y[106] = 1280.3545 
bce2_route3_z[106] = 6.8280 

bce2_route3_x[107] = -2629.3137 
bce2_route3_y[107] = 1297.9771 
bce2_route3_z[107] = 6.7957 

bce2_route3_x[108] = -2548.2625 
bce2_route3_y[108] = 1358.4670 
bce2_route3_z[108] = 7.3351 

bce2_route3_x[109] = -2448.6208 
bce2_route3_y[109] = 1370.2677 
bce2_route3_z[109] = 6.7969 

bce2_route3_x[110] = -2347.6985 
bce2_route3_y[110] = 1366.4080 
bce2_route3_z[110] = 6.9813 

bce2_route3_x[111] = -2253.1951 
bce2_route3_y[111] = 1333.5933 
bce2_route3_z[111] = 6.8010 

bce2_route3_x[112] = -2152.6421 
bce2_route3_y[112] = 1334.9623 
bce2_route3_z[112] = 6.8483 

bce2_route3_x[113] = -2055.3845 
bce2_route3_y[113] = 1309.6310 
bce2_route3_z[113] = 6.9547 

bce2_route3_x[114] = -1957.9801 
bce2_route3_y[114] = 1286.6377 
bce2_route3_z[114] = 6.9474 

bce2_route3_x[115] = -1868.5018 
bce2_route3_y[115] = 1332.5726 
bce2_route3_z[115] = 6.9476 

bce2_route3_x[116] = -1837.6033 
bce2_route3_y[116] = 1425.7190 
bce2_route3_z[116] = 6.9399 

// ********************************************* GATES ***********************************************

//grate to stop you getting area51
VAR_INT a51ventcover
CREATE_OBJECT a51_ventcoverb 245.968 1862.843 19.49 a51ventcover
SET_OBJECT_ROTATION a51ventcover 0.0 0.0 -140.998
DONT_REMOVE_OBJECT a51ventcover

// ***************************************************************************************************



// ************************************* CAR GENERATORS FOR DRIVING SCHOOL ***************************
VAR_INT f1_bronze_car_gen area51_gen[4]
VAR_INT f1_silver_car_gen
VAR_INT f1_gold_car_gen
VAR_INT f1_chunky
VAR_INT f1_dbp
VAR_INT f1_scripted_cut
VAR_INT f1_blood_car_gen


CREATE_CAR_GENERATOR -2093.9 -83.7 33.9 359.1 SUPERGT 31 0 TRUE 0 0 0 10000 f1_bronze_car_gen 
SWITCH_CAR_GENERATOR f1_bronze_car_gen 0
CREATE_CAR_GENERATOR -2076.8 -84.0 33.7 1.1 BULLET 15 15 TRUE 0 0 0 10000 f1_silver_car_gen 
SWITCH_CAR_GENERATOR f1_silver_car_gen 0
CREATE_CAR_GENERATOR_WITH_PLATE -2064.4 -83.7 34.1 0.0 HOTKNIFE 15 15 TRUE 0 0 0 10000 &__GOLD__ f1_gold_car_gen 
SWITCH_CAR_GENERATOR f1_gold_car_gen 0
CREATE_CAR_GENERATOR_WITH_PLATE -2273.2 -130.8 33.9 270.4 BANSHEE 79 0 TRUE 0 0 0 10000 &__DBP___ f1_dbp //banshee car
SWITCH_CAR_GENERATOR f1_dbp 0
CREATE_CAR_GENERATOR_WITH_PLATE -2354.6 983.0 49.3 189.4 BULLET 24 40 TRUE 0 0 0 10000 &_CHUNKY_ f1_chunky //bullet car
SWITCH_CAR_GENERATOR f1_chunky 0
CREATE_CAR_GENERATOR -2151.0 -409.1 34.1 307.2 BLOODRA -1 -1 TRUE 0 0 0 10000 f1_blood_car_gen 
SWITCH_CAR_GENERATOR f1_blood_car_gen 0


// *************************************CAR GENERATORS FOR FLYING VEHICLES***************************
VAR_INT gen_car_flying[28]


// Los Santos Flying vehicles
CREATE_CAR_GENERATOR 1544.0	-1352.0	329.0 95.0 MAVERICK -1 -1 FALSE 0 0 0 10000 gen_car_flying[0]		// On top of star-topped skyscraper, probably not until after player receives pilot's license. Roof is accessible via entry/exit point.


// San Fierro flying vehicles


CREATE_CAR_GENERATOR -1963.0	628.0	151.0 182.0 SPARROW -1 -1 FALSE 0 0 0 10000 gen_car_flying[1]	//On top of zombotech building. Should be safe at any time, building only accessible by flight
CREATE_CAR_GENERATOR -1681.0	705.0	31.0 90.0 MAVERICK -1 -1 FALSE 0 0 0 10000 gen_car_flying[2] 	//Helipad near garage, used in Toreno's Last Flight. Accessible at any time, so probably shouldn't get a vehicle until after Pilot's License is obtained.
CREATE_CAR_GENERATOR -2630.0	684.0	72.0 180.0 MAVERICK -1 -1 FALSE 0 0 0 10000 gen_car_flying[3]	//First of two helipads on hospital roof. Got a + symbol on it. Probably safe to always have a heli, as I don't think you could access this part of the hospital roof without flying anyway.
CREATE_CAR_GENERATOR -2726.0	682.0	72.0 90.0 RAINDANC -1 -1 FALSE 0 0 0 10000 gen_car_flying[4]	//Second helipad on hospital roof. Same as above, but with an H symbol.
CREATE_CAR_GENERATOR -2227.0	2329.0  8.0	180.0 SEASPAR -1 -1 FALSE 0 0 0 10000 gen_car_flying[5]		//Helipad near boat school. Accessible once the SF bridges are open, so probably shouldn't get a vehicle until the Pilot's License has been obtained.


//LAs Venuras Flying vehicles

CREATE_CAR_GENERATOR 2619.0	2721.0	37.0 90.0 CARGOBOB -1 -1 FALSE 0 0 0 10000 gen_car_flying[6]	//Roof of the military fuel dump. Accessible at any time (though not easily), so probably not until after Pilot's License obtained.
CREATE_CAR_GENERATOR 2092.0	2415.0	75.0 260.0 MAVERICK -1 -1 FALSE 0 0 0 10000 gen_car_flying[7]	//Used in the mission 'Misappropriation', but accessible from ground level at any time, so probably shouldn't have anything until after the pilots license is obtained. 
//CREATE_CAR_GENERATOR 282.0	1992.0	18.0	 //Hangars in Area 51. Probably accessible at any time, so once the user has the pilot's license.


// Harriers (after Vertical Bird complete)
CREATE_CAR_GENERATOR 292.0 2542.0 16.0	180.0 HYDRA -1 -1 FALSE 0 0 0 10000 gen_car_flying[8]		// Desert Airfield Hanger
CREATE_CAR_GENERATOR -1286.0 501.5 18.0	270.0 HYDRA -1 -1 FALSE 0 0 0 10000 gen_car_flying[9]		// Aircraft Carrier Top Deck


CREATE_CAR_GENERATOR -959.0813 2629.2058 43.229 110.0 SKIMMER -1 -1 FALSE 0 0 0 10000 gen_car_flying[10] 	// by boardwalk in desert
CREATE_CAR_GENERATOR 273.0045 -1876.0377 5.2935 271.510  RAINDANC	-1 -1 FALSE 0 0 0 10000 gen_car_flying[11] 	// LOS SANTOS BEACH
CREATE_CAR_GENERATOR 1561.8645 -1701.9529 27.948 0.0015  POLMAV	-1 -1 FALSE 0 0 0 10000 gen_car_flying[12] 	// LOS SANTOS BEACH
CREATE_CAR_GENERATOR -637.1 1811.9529 1.7 180.0015  SKIMMER	-1 -1 FALSE 0 0 0 10000 gen_car_flying[13] 	// by the Hoover dam



														 
// Car gens for SF airport
// The ones With unlocked doors
CREATE_CAR_GENERATOR -1443.0813 -523.2058 13.9929 267.0 DODO -1 -1 FALSE 0 0 0 10000 	gen_car_flying[14] 	
CREATE_CAR_GENERATOR -1363.0045 -484.0377 13.9 200.510  DODO	-1 -1 FALSE 0 0 0 10000 	gen_car_flying[15] 
CREATE_CAR_GENERATOR -1180.8645 -351.9529 13.948 0.0015  NEVADA	-1 -1 FALSE 0 0 0 10000 	gen_car_flying[16] 	
CREATE_CAR_GENERATOR -1289.1 -353.9529 13.9 219.0015  SHAMAL	-1 -1 FALSE 0 0 0 10000 	gen_car_flying[17] 
CREATE_CAR_GENERATOR -1253.1 -347.9529 13.9 219.0015  SHAMAL	-1 -1 FALSE 0 0 0 10000 	gen_car_flying[18] 	
CREATE_CAR_GENERATOR -1387.1 -227.9529 13.9 312.0015  NEVADA	-1 -1 FALSE 0 0 0 10000 	gen_car_flying[19] 	
CREATE_CAR_GENERATOR -1220.1 -150.9529 13.9 519.0015  SHAMAL	-1 -1 FALSE 0 0 0 10000 	gen_car_flying[20]
CREATE_CAR_GENERATOR -1244.6740 -599.1741 13.9 48.6377  MAVERICK	-1 -1 FALSE 0 0 0 10000 		gen_car_flying[21]	
CREATE_CAR_GENERATOR -1215.1 -6.14 13.9 95.0015  LEVIATHN	-1 -1 FALSE 0 0 0 10000 		gen_car_flying[22]
CREATE_CAR_GENERATOR -1190.1 22.14 13.9 45.0015  RAINDANC	-1 -1 FALSE 0 0 0 10000 		gen_car_flying[23]
CREATE_CAR_GENERATOR -1374.1 -503.14 13.9 249.0015  RUSTLER -1 -1 FALSE 0 0 0 10000 		gen_car_flying[24]


		

CREATE_CAR_GENERATOR -1608.724 286.14 6.22 55.0015  HUNTER -1 -1 FALSE 0 0 0 10000 		gen_car_flying[25]

CREATE_CAR_GENERATOR 1586.941 1190.642 10.863 180.0 AT400 -1 -1 FALSE 0 0 0 10000 		gen_car_flying[26]

CREATE_CAR_GENERATOR -1629.28 -236.14 13.9 129.0015  BEAGLE -1 -1 FALSE 0 0 0 10000 		gen_car_flying[27]	 // beagle in SF airport




CREATE_CAR_GENERATOR 277.55, 1989.35, 17.21 270.00 rhino -1 -1 FALSE 0 0 0 10000 area51_gen[0]	 // AREA51
CREATE_CAR_GENERATOR 281.23, 2022.33, 17.21 270.00 rhino -1 -1 FALSE 0 0 0 10000 area51_gen[1]	 // AREA51

CREATE_CAR_GENERATOR 307.35, 1990.25, 17.21 320.00 hydra -1 -1 FALSE 0 0 0 10000 area51_gen[2]	 // AREA51
CREATE_CAR_GENERATOR 307.35, 2031.36, 17.21 320.00 hydra -1 -1 FALSE 0 0 0 10000 area51_gen[3]	 // AREA51
 
SWITCH_CAR_GENERATOR area51_gen[0] 0
SWITCH_CAR_GENERATOR area51_gen[1] 0
SWITCH_CAR_GENERATOR area51_gen[2] 0
SWITCH_CAR_GENERATOR area51_gen[3] 0




SWITCH_CAR_GENERATOR gen_car_flying[0] 0
SWITCH_CAR_GENERATOR gen_car_flying[1] 0
SWITCH_CAR_GENERATOR gen_car_flying[2] 0
SWITCH_CAR_GENERATOR gen_car_flying[3] 0
SWITCH_CAR_GENERATOR gen_car_flying[4] 0
SWITCH_CAR_GENERATOR gen_car_flying[5] 0
SWITCH_CAR_GENERATOR gen_car_flying[6] 0
SWITCH_CAR_GENERATOR gen_car_flying[7] 0
SWITCH_CAR_GENERATOR gen_car_flying[8] 0
SWITCH_CAR_GENERATOR gen_car_flying[9] 0
SWITCH_CAR_GENERATOR gen_car_flying[10] 0
SWITCH_CAR_GENERATOR gen_car_flying[11] 0
SWITCH_CAR_GENERATOR gen_car_flying[12] 0
SWITCH_CAR_GENERATOR gen_car_flying[13] 0 // need jude to add this one



// Car gens for SF AIRPORT
SWITCH_CAR_GENERATOR gen_car_flying[14]	0
SWITCH_CAR_GENERATOR gen_car_flying[15]	0
SWITCH_CAR_GENERATOR gen_car_flying[16] 0
SWITCH_CAR_GENERATOR gen_car_flying[17]	0
SWITCH_CAR_GENERATOR gen_car_flying[18]	0
SWITCH_CAR_GENERATOR gen_car_flying[19]	0
SWITCH_CAR_GENERATOR gen_car_flying[20]	0
SWITCH_CAR_GENERATOR gen_car_flying[21]	0
SWITCH_CAR_GENERATOR gen_car_flying[22]	0
SWITCH_CAR_GENERATOR gen_car_flying[23]	0
SWITCH_CAR_GENERATOR gen_car_flying[24]	0
// end car gens for san fran


// HUNTER on plane carrier on SF
SWITCH_CAR_GENERATOR gen_car_flying[25]	0
SWITCH_CAR_GENERATOR gen_car_flying[26]	101

SWITCH_CAR_GENERATOR gen_car_flying[27]	0



// Car gens for SF airport
// The ones With unlocked doors

VAR_INT car_gen_locked_sf_planes[12]
CREATE_CAR_GENERATOR -1443.0813 -523.2058 13.9929 267.0 DODO -1 -1 FALSE 0 100 0 10000 	car_gen_locked_sf_planes[0] 	
CREATE_CAR_GENERATOR -1363.0045 -484.0377 13.9 200.510  DODO	-1 -1 FALSE 0 100 0 10000 	car_gen_locked_sf_planes[1] 
CREATE_CAR_GENERATOR -1180.8645 -351.9529 13.948 0.0015  NEVADA	-1 -1 FALSE 0 100 0 10000 	car_gen_locked_sf_planes[2] 	
CREATE_CAR_GENERATOR -1289.1 -353.9529 13.9 219.0015  SHAMAL	-1 -1 FALSE 0 100 0 10000 	car_gen_locked_sf_planes[3] 
CREATE_CAR_GENERATOR -1253.1 -347.9529 13.9 219.0015  SHAMAL	-1 -1 FALSE 0 100 0 10000 	car_gen_locked_sf_planes[4] 	
CREATE_CAR_GENERATOR -1387.1 -227.9529 13.9 312.0015  NEVADA	-1 -1 FALSE 0 100 0 10000 	car_gen_locked_sf_planes[5] 	
CREATE_CAR_GENERATOR -1220.1 -150.9529 13.9 519.0015  SHAMAL	-1 -1 FALSE 0 100 0 10000 	car_gen_locked_sf_planes[6]
CREATE_CAR_GENERATOR -1244.6740 -599.1741 13.9 48.6377  MAVERICK	-1 -1 TRUE 0 0 100 10000 		car_gen_locked_sf_planes[7]	
CREATE_CAR_GENERATOR -1215.1 -6.14 13.9 95.0015  LEVIATHN	-1 -1 FALSE 0 100 0 10000 		car_gen_locked_sf_planes[8]
CREATE_CAR_GENERATOR -1190.1 22.14 13.9 45.0015  RAINDANC	-1 -1 FALSE 0 100 0 10000 		car_gen_locked_sf_planes[9]
CREATE_CAR_GENERATOR -1374.1 -503.14 13.9 249.0015  RUSTLER -1 -1 FALSE 0 100 0 10000 		car_gen_locked_sf_planes[10]
CREATE_CAR_GENERATOR -1629.28 -236.14 13.9 129.0015  BEAGLE -1 -1 FALSE 0 100 0 10000 		car_gen_locked_sf_planes[11]	 // beagle in SF car_gen_locked_sf_planes







SWITCH_CAR_GENERATOR car_gen_locked_sf_planes[0] 101
SWITCH_CAR_GENERATOR car_gen_locked_sf_planes[1] 101
SWITCH_CAR_GENERATOR car_gen_locked_sf_planes[2] 101
SWITCH_CAR_GENERATOR car_gen_locked_sf_planes[3] 101
SWITCH_CAR_GENERATOR car_gen_locked_sf_planes[4] 101
SWITCH_CAR_GENERATOR car_gen_locked_sf_planes[5] 101
SWITCH_CAR_GENERATOR car_gen_locked_sf_planes[6] 101
SWITCH_CAR_GENERATOR car_gen_locked_sf_planes[7] 101
SWITCH_CAR_GENERATOR car_gen_locked_sf_planes[8] 101
SWITCH_CAR_GENERATOR car_gen_locked_sf_planes[9] 101
SWITCH_CAR_GENERATOR car_gen_locked_sf_planes[10] 101
SWITCH_CAR_GENERATOR car_gen_locked_sf_planes[11] 101










//SWITCH_CAR_GENERATOR gen_car_flying[0] 101


// *************************************CAR GENERATORS AND PICKUPS FOR INTERGLOBAL FILM STUDIOS***************************
VAR_INT gen_car_film_studio_LA[8]


// golf carts vehicles
CREATE_CAR_GENERATOR 927.7213 -1185.0419 16.5 123.3055 caddy -1 -1 FALSE 0 0 0 10000 gen_car_film_studio_LA[0] // near entrance, a line on parked caddys
CREATE_CAR_GENERATOR 927.5233 -1182.3651 16.5  123.3055 caddy -1 -1 FALSE 0 0 0 10000 gen_car_film_studio_LA[1] // near entrance, a line on parked caddys
CREATE_CAR_GENERATOR 926.9179 -1178.9500 16.5  123.3055 caddy -1 -1 FALSE 0 0 0 10000 gen_car_film_studio_LA[2] // near entrance, a line on parked caddys
CREATE_CAR_GENERATOR 861.3535 -1240.7589 14.5 180.1308 caddy -1 -1 FALSE 0 0 0 10000 gen_car_film_studio_LA[3] // mt bike parked outside sound studio
CREATE_CAR_GENERATOR 837.7494 -1206.5736 16.5 153.2632 JOURNEY -1 -1 FALSE 0 0 0 10000 gen_car_film_studio_LA[4] // forklift parked outside sound studio
CREATE_CAR_GENERATOR 897.5168 -1207.9912 16.5 86.5989 JOURNEY -1 -1 FALSE 0 0 0 10000 gen_car_film_studio_LA[5] // JOURNEY outside sound studio

CREATE_CAR_GENERATOR 736.2398 -1334.1952 13.5411 267.8109 JOURNEY -1 -1 FALSE 0 0 0 10000 gen_car_film_studio_LA[6] //JOURNEY parked outside TV studio
CREATE_CAR_GENERATOR 736.9623 -1343.9069 13.5197 273.7721 JOURNEY -1 -1 FALSE 0 0 0 10000 gen_car_film_studio_LA[7] // JOURNEY parked outside TV studio



SWITCH_CAR_GENERATOR gen_car_film_studio_LA[0] 101
SWITCH_CAR_GENERATOR gen_car_film_studio_LA[1] 101
SWITCH_CAR_GENERATOR gen_car_film_studio_LA[2] 101
SWITCH_CAR_GENERATOR gen_car_film_studio_LA[3] 101
SWITCH_CAR_GENERATOR gen_car_film_studio_LA[4] 101
SWITCH_CAR_GENERATOR gen_car_film_studio_LA[5] 101
SWITCH_CAR_GENERATOR gen_car_film_studio_LA[6] 101
SWITCH_CAR_GENERATOR gen_car_film_studio_LA[7] 101



VAR_INT pickups_film_studio[7]
CREATE_PICKUP_WITH_AMMO AK47 PICKUP_ON_STREET_SLOW 60 825.9210 -1165.8129 17.8936  pickups_film_studio[0] 
CREATE_PICKUP_WITH_AMMO sawnoff PICKUP_ON_STREET_SLOW 40 832.6030 -1273.8612 14.4833  pickups_film_studio[1]
CREATE_PICKUP nitestick PICKUP_ON_STREET_SLOW 911.6486 -1235.3898 17.6802 pickups_film_studio[2]		

CREATE_PICKUP sniper PICKUP_ON_STREET_SLOW 733.4333 -1356.4700 23.5229 pickups_film_studio[3]

// Car gens at mount chiliad
VAR_INT car_gen_mtchiliad[4]

CREATE_CAR_GENERATOR -2408.5110 -2186.0239 32.890 321.6920  SANCHEZ -1 -1 FALSE 0 0 0 10000 car_gen_mtchiliad[0] 
CREATE_CAR_GENERATOR -2407.6057 -2177.0920 32.890 321.6920  MTBIKE -1 -1 FALSE 0 0 0 10000 car_gen_mtchiliad[1] 
CREATE_CAR_GENERATOR -2338.5654 -1593.8329 482.9451 20.7510   JOURNEY -1 -1 FALSE 0 0 0 10000 car_gen_mtchiliad[2] 
CREATE_CAR_GENERATOR -2343.3704 -1613.9430 482.9757 105.5300   CAMPER -1 -1 FALSE 0 0 0 10000 car_gen_mtchiliad[3] 


SWITCH_CAR_GENERATOR car_gen_mtchiliad[0] 101
SWITCH_CAR_GENERATOR car_gen_mtchiliad[1] 101
SWITCH_CAR_GENERATOR car_gen_mtchiliad[2] 101
SWITCH_CAR_GENERATOR car_gen_mtchiliad[3] 101


// GO KART CAR GENS (THEY OPEN AFTER SIMONS GO KART MISSION)
VAR_INT car_gen_go_kart[15]




CREATE_CAR_GENERATOR -2213.5559 112.7671 34.9203 88.4720	KART -1 -1 FALSE 0 0 0 10000 car_gen_go_kart[0] // behind zeros toy shop	in SF
CREATE_CAR_GENERATOR -2693.3860 -139.4564 3.93359 90.0856   KART -1 -1 FALSE 0 0 0 10000 car_gen_go_kart[1] 	// some dudes front garden in SF
CREATE_CAR_GENERATOR -2796.4675 -94.1788 6.9875 42.6945 	KART -1 -1 FALSE 0 0 0 10000 car_gen_go_kart[2]  		// some dudes front garden in SF
CREATE_CAR_GENERATOR -2206.0508 701.2161 48.9453 183.4174 	KART -1 -1 FALSE 0 0 0 10000 car_gen_go_kart[3] 		// ally way in china town
CREATE_CAR_GENERATOR -810.5599 2430.3628 156.9649 336.533 	KART -1 -1 FALSE 0 0 0 10000 car_gen_go_kart[4] 	// barn inside shed near big jump
CREATE_CAR_GENERATOR -1693.4413 432.2852 6.9914 300.903 	KART -1 -1 FALSE 0 0 0 10000 car_gen_go_kart[5]  	 //	   jethros garage
CREATE_CAR_GENERATOR -2116.1384 924.8068 85.9791 94.9293 	KART -1 -1 FALSE 0 0 0 10000 car_gen_go_kart[6]   	// near windy road
CREATE_CAR_GENERATOR -1483.6899 2614.8354 58.2812 337.9383  KART -1 -1 FALSE 0 0 0 10000 car_gen_go_kart[7] 		// inside wee warehouse
CREATE_CAR_GENERATOR 1419.6984 1947.9960 10.9531 6.9689 	KART -1 -1 FALSE 0 0 0 10000 car_gen_go_kart[8] 		// driveway in vegas
CREATE_CAR_GENERATOR 1567.3890 2691.1184 10.265 279.9875 	KART -1 -1 FALSE 0 0 0 10000 car_gen_go_kart[9] 	// in  a back garden
CREATE_CAR_GENERATOR -2087.3667 -2519.0159 29.9250 90.9178 	KART -1 -1 FALSE 0 0 0 10000 car_gen_go_kart[10] 	// trailer park in flint
CREATE_CAR_GENERATOR 2615.3171 1939.7007 10.129 148.1757 	KART -1 -1 FALSE 0 0 0 10000 car_gen_go_kart[11] 	// behind the chinese mall in VEGAS
CREATE_CAR_GENERATOR 1074.9640 1395.4177 5.303 36.7673 		KART -1 -1 FALSE 0 0 0 10000 car_gen_go_kart[12] 		//behind dirt ring stadium in vegas
CREATE_CAR_GENERATOR 2615.2388 -1731.2253 5.9486 140.8213	KART -1 -1 FALSE 0 0 0 10000 car_gen_go_kart[13] 	 // storm drain in LA
CREATE_CAR_GENERATOR 1305.1758 -796.6955 83.9477 185.99114 	KART -1 -1 FALSE 0 0 0 10000 car_gen_go_kart[14] 	 /// behind mad dogs mansion

SWITCH_CAR_GENERATOR car_gen_go_kart[0] 	0
SWITCH_CAR_GENERATOR car_gen_go_kart[1] 	0
SWITCH_CAR_GENERATOR car_gen_go_kart[2] 	0
SWITCH_CAR_GENERATOR car_gen_go_kart[3] 	0
SWITCH_CAR_GENERATOR car_gen_go_kart[4] 	0
SWITCH_CAR_GENERATOR car_gen_go_kart[5] 	0
SWITCH_CAR_GENERATOR car_gen_go_kart[6] 	0
SWITCH_CAR_GENERATOR car_gen_go_kart[7] 	0
SWITCH_CAR_GENERATOR car_gen_go_kart[8] 	0
SWITCH_CAR_GENERATOR car_gen_go_kart[9] 	0
SWITCH_CAR_GENERATOR car_gen_go_kart[10]	0
SWITCH_CAR_GENERATOR car_gen_go_kart[11]	0
SWITCH_CAR_GENERATOR car_gen_go_kart[12]	0
SWITCH_CAR_GENERATOR car_gen_go_kart[13]	0
SWITCH_CAR_GENERATOR car_gen_go_kart[14]	0


// CAR GEN FOR HOVER CRAFTS (OPEN AFTER SIMONS MISSIONS)
VAR_INT car_gen_hover[6]

CREATE_CAR_GENERATOR -2294.9260 2546.9783 5.9175 290.9339	VORTEX  -1 -1 FALSE 0 0 0 10000 car_gen_hover[0] 		// wee beach across the bridge from SF
CREATE_CAR_GENERATOR 714.3436 -1488.2727 0.9343 270.0		VORTEX	-1 -1 FALSE 0 0 0 10000 car_gen_hover[1]  // (LA canal)
CREATE_CAR_GENERATOR -1426.4120 506.8391 2.9463 144.6100 	VORTEX	-1 -1 FALSE 0 0 0 10000 car_gen_hover[2] 	//(SF inside aircraft carrier)
CREATE_CAR_GENERATOR 1971.9197 1560.6653 10.9635 262.6150 	VORTEX	-1 -1 FALSE 0 0 0 10000 car_gen_hover[3] // vegas Pirates in mens pants
CREATE_CAR_GENERATOR -535.4126 -60.8884 63.5922 276.9756   	VORTEX	-1 -1 FALSE 0 0 0 10000 car_gen_hover[4]  // tucked away in a secet barn near river 
CREATE_CAR_GENERATOR -910.27 2699.06 42.8 110.8738 	VORTEX	-1 -1 FALSE 0 0 0 10000 car_gen_hover[5] 	// by boardwalk in desert
 
SWITCH_CAR_GENERATOR car_gen_hover[0] 	0
SWITCH_CAR_GENERATOR car_gen_hover[1] 	0
SWITCH_CAR_GENERATOR car_gen_hover[2] 	0
SWITCH_CAR_GENERATOR car_gen_hover[3] 	0
SWITCH_CAR_GENERATOR car_gen_hover[4] 	0
SWITCH_CAR_GENERATOR car_gen_hover[5] 	0
 
// CAR GEN FOR SPARROW ON ROOF OF MADD DOGGS MANSION AFTER HOME IN THE HILLS
VAR_INT car_gen_sparrow
CREATE_CAR_GENERATOR 1291.5 -787.5 95.4555 180.0	SPARROW  -1 -1 FALSE 0 0 0 10000 car_gen_sparrow 	  
SWITCH_CAR_GENERATOR car_gen_sparrow	0


// CAR GEN FOR BOLTS MISSION DESERT 1

VAR_INT car_gen_desert1_pcj
CREATE_CAR_GENERATOR 435.2751 2527.5227 16.3710 90.0 pcj600  -1 -1 FALSE 0 0 0 10000 car_gen_desert1_pcj 	  
SWITCH_CAR_GENERATOR car_gen_desert1_pcj	101

//Bolt's valet asset pickup
VAR_INT valet_cash_pickup

//ammunation challenged passed flag
VAR_INT player_has_fast_reload
player_has_fast_reload = 0

VAR_INT range_weapons_open trigger_new_range_level






//REWARD AFTER FINISHING KICKSTART
VAR_INT car_gen_duneride_kickstart

CREATE_CAR_GENERATOR 1091.89 1612.63 13.0 206.7583  duneride -1 -1 TRUE 0 0 0 10000 car_gen_duneride_kickstart 	// reward after kickstart
SWITCH_CAR_GENERATOR car_gen_duneride_kickstart	0


// CAR GEN NEAR CESARS HOUSE FOR THE LOWRIDER MISSION HIGH STAKES

VAR_INT car_gen_low_rider_near_cesars

CREATE_CAR_GENERATOR 1772.66 -2096.59 13.99 182.7583  VOODOO -1 -1 TRUE 0 0 0 10000 car_gen_low_rider_near_cesars 	// reward after kickstart
SWITCH_CAR_GENERATOR car_gen_low_rider_near_cesars	101

// --------------------------Angel Pine car gens
VAR_INT car_gen_pine[6]

CREATE_CAR_GENERATOR -2151.303 -2440.136 29.822 324.7583 -1 -1 -1 FALSE 0 0 0 10000 car_gen_pine[0]
CREATE_CAR_GENERATOR -2147.038 -2443.752 29.822 324.7583 -1 -1 -1 FALSE 0 0 0 10000 car_gen_pine[1]
CREATE_CAR_GENERATOR -2140.979 -2448.733 29.822 144.7583 -1 -1 -1 FALSE 0 0 0 10000 car_gen_pine[2]
SWITCH_CAR_GENERATOR car_gen_pine[0] 101
SWITCH_CAR_GENERATOR car_gen_pine[1] 101
SWITCH_CAR_GENERATOR car_gen_pine[2] 101

// need to switch these off for farlie5
CREATE_CAR_GENERATOR -2000.242 -2415.509 29.767 -132.0 RDTRAIN -1 -1 FALSE 0 0 0 10000 car_gen_pine[3]
CREATE_CAR_GENERATOR -1969.806 -2437.939 29.767 -82.5  DFT30 -1 -1 FALSE 0 0 0 10000 car_gen_pine[4]
CREATE_CAR_GENERATOR -1969.806 -2443.908 29.767 -19.0  FORKLIFT -1 -1 FALSE 0 0 0 10000 car_gen_pine[5]
SWITCH_CAR_GENERATOR car_gen_pine[3] 101
SWITCH_CAR_GENERATOR car_gen_pine[4] 101
SWITCH_CAR_GENERATOR car_gen_pine[5] 101





// ***************************************** SHOPS STUFF ******************************************
// *********** used to reset the amount of food player has eaten in one 6hr period ****************
// ************************************************************************************************
VAR_INT timer_wil game_timer_wil stored_shop_time
timer_wil = 0 
game_timer_wil = 0 
stored_shop_time = 0

// Locate size stuff for shops
CONST_FLOAT shop_locate_sizeX 1.0
CONST_FLOAT shop_locate_sizeY 1.0
CONST_FLOAT shop_locate_sizeZ 4.0

// KEVIN BOLT'S NEW VALET GLOBALS
VAR_INT tell_player_to_kill_valet

// BOLT - shooting range cut skip flag
VAR_INT range_cuts_watched lf2_skip_allowed
range_cuts_watched = 0

// SNIPER HELP TEXT GLOBAL
VAR_INT displayed_sniper_help_text
displayed_sniper_help_text = FALSE


// Rear Door for Aircraft Carrier (Vertical Bird)
VAR_INT g_VB_REAR_DOOR
CREATE_OBJECT_NO_OFFSET CARRIER_DOOR_SFSE -1465.797 501.289 1.145 g_VB_REAR_DOOR
SET_OBJECT_DYNAMIC g_VB_REAR_DOOR FALSE
SET_OBJECT_COLLISION_DAMAGE_EFFECT g_VB_REAR_DOOR FALSE
SET_OBJECT_PROOFS g_VB_REAR_DOOR TRUE TRUE TRUE TRUE TRUE
DONT_REMOVE_OBJECT g_VB_REAR_DOOR


// Small Lift for Aircraft Carrier (Vertical Bird)
VAR_INT g_VB_SMALL_LIFT
CREATE_OBJECT_NO_OFFSET CARRIER_LIFT2_SFSE -1414.453 516.453 16.688 g_VB_SMALL_LIFT
SET_OBJECT_DYNAMIC g_VB_SMALL_LIFT FALSE
SET_OBJECT_COLLISION_DAMAGE_EFFECT g_VB_SMALL_LIFT FALSE
SET_OBJECT_PROOFS g_VB_SMALL_LIFT TRUE TRUE TRUE TRUE TRUE
DONT_REMOVE_OBJECT g_VB_SMALL_LIFT


// Big Lift for Aircraft Carrier (Vertical Bird)
VAR_INT g_VB_BIG_LIFT
CREATE_OBJECT_NO_OFFSET CARRIER_LIFT1_SFSE -1456.719 501.297 16.953 g_VB_BIG_LIFT
SET_OBJECT_DYNAMIC g_VB_BIG_LIFT FALSE
SET_OBJECT_COLLISION_DAMAGE_EFFECT g_VB_BIG_LIFT FALSE
SET_OBJECT_PROOFS g_VB_BIG_LIFT TRUE TRUE TRUE TRUE TRUE
DONT_REMOVE_OBJECT g_VB_BIG_LIFT



// Burglary variables
VAR_INT burglary_player_stealth
burglary_player_stealth = 0


// farlie5 trip skip
VAR_INT d6_trip_skips_available
d6_trip_skips_available = 0


//--- Dance Mini-Game Globals and Constants

//--- DANCE GLOBALS
VAR_INT iDanceReport // contains the score in the first 10 BITS and various flags from bit 11 onwards
VAR_INT iDanceScore //  the on-screen counter for the score
VAR_INT iSwitchOffDanceMiniGame // a flag to prevent the locate from appearing in missions and special cases
VAR_INT iTerminateDanceMiniGame // a flag to terminate the dancing and do all the necessary clean-up 
VAR_TEXT_LABEL16 txtCURR_DANCE_MOVE txtCURR_DANCE_MOVE_P txtCURR_DANCE_BLOCK txtString

VAR_FLOAT fDanceCameraX[5] // all the camera vectors and their targets for external settings
VAR_FLOAT fDanceCameraY[5]
VAR_FLOAT fDanceCameraZ[5]
VAR_FLOAT fDanceTargetX[5]
VAR_FLOAT fDanceTargetY[5]
VAR_FLOAT fDanceTargetZ[5]

//--- DANCE PARAMETERS - CONSTANTS
CONST_INT DANCE_MINIGAME_RUNNING 	31

CONST_INT DANCE_TRACK_HIPHOP 		1 
CONST_INT DANCE_TRACK_ROCK	 		1

CONST_INT DANCE_TRACK_GFUNK	 		1 // beach - RESERVED
CONST_INT DANCE_TRACK_GFUNK_ALT		2
CONST_INT DANCE_TRACK_RUNNINGMAN 	3
CONST_INT DANCE_TRACK_WOP	 		4 
CONST_INT DANCE_TRACK_LIST_START 	1
CONST_INT DANCE_TRACK_LIST_END		5

CONST_INT DANCE_MISSION_NO_PARTNER	-1
CONST_INT DANCE_RANDOM_PARTNER		0

//--- DANCE CONSTANTS

//--- PLAYER's TIMING
CONST_INT DANCE_BEAT_PAST					0
CONST_INT DANCE_BEAT_PERFECT				1
CONST_INT DANCE_BEAT_GOOD					2
CONST_INT DANCE_BEAT_ALRIGHT				3
CONST_INT DANCE_BEAT_FUTURE					4
CONST_INT DANCE_BEAT_NONE					5
CONST_INT DANCE_BEAT_GOOD_SCORED			8
CONST_INT DANCE_BEAT_BAD_SCORED				9
CONST_INT DANCE_BEAT_WRONG_BUTTON			10
CONST_INT DANCE_BEAT_MISTIMED_BUTTON		11

//--- SCORE INCREMENTS
CONST_INT DANCE_SCORE_PERFECT				50
CONST_INT DANCE_SCORE_GOOD					20
CONST_INT DANCE_SCORE_ALRIGHT				10
CONST_INT DANCE_SCORE_BAD					0  //this is subtracted to score
CONST_INT DANCE_SCORE_VERY_BAD				0 //this is subtracted to score
CONST_INT DANCE_SCORE_PERFECT_MULTIPLYER	2
CONST_INT DANCE_SCORE_TOTAL_BEATS_TO_REPORT	4
CONST_INT DANCE_SCORE_TOP_LIMIT				99999999

//--- PAD BUTTONS
CONST_INT DANCE_BUTTON_CROSS				1
CONST_INT DANCE_BUTTON_SQUARE				2
CONST_INT DANCE_BUTTON_TRIANGLE				3
CONST_INT DANCE_BUTTON_CIRCLE				4
CONST_INT DANCE_BUTTON_NONE					5
//--- PAD BUTTON HOLD TIMERS
CONST_INT DANCE_TIME_TO_HOLD_QUIT 			800

//--- BEAT TIMING BOUNDS
CONST_INT DANCE_BOUNDS_PERFECT_MAX			35 
CONST_INT DANCE_BOUNDS_PERFECT_MIN			-35
CONST_INT DANCE_BOUNDS_GOOD_MAX				60 
CONST_INT DANCE_BOUNDS_GOOD_MIN				-60
CONST_INT DANCE_BOUNDS_ALRIGHT_MAX			110 
CONST_INT DANCE_BOUNDS_ALRIGHT_MIN			-110
CONST_INT DANCE_BOUNDS_NO_BEAT				3000

//--- DANCE STATE MACHINE
CONST_INT STATE_DANCE_INIT					0
CONST_INT STATE_DANCE_TRACK_AND_GET_BEATS	1
//--- DANCE OVERALL REPORT
CONST_INT DANCE_OVERALL_BAD					0
CONST_INT DANCE_OVERALL_GOOD				1
CONST_INT DANCE_OVERALL_PERFECT				2
//--- ANIMATION SEQUENCING
CONST_INT ANIM_NOT_PLAYING					0
CONST_INT ANIM_CONCANTENATE_NOW				1
CONST_INT ANIM_IS_PLAYING	 				-1
//--- DANCE SPEECH FEEDBACK
CONST_INT DANCE_SPEECH_IDLE					0
CONST_INT DANCE_SPEECH_PLAYBACK				1
CONST_INT DANCE_SPEECH_HIGH					2
CONST_INT DANCE_SPEECH_LOW					3
CONST_INT DANCE_SPEECH_MED					4
CONST_INT DANCE_SPEECH_NOT					5
CONST_INT DANCE_SPEECH_REQUEST_HIGH			6
CONST_INT DANCE_SPEECH_REQUEST_LOW			8		
CONST_INT DANCE_SPEECH_REQUEST_MED			9		
CONST_INT DANCE_SPEECH_REQUEST_NOT			10		
//---MISC.
CONST_INT DANCE_END_BEAT					33
CONST_INT DANCE_HARDCODED_END_BEAT			100000
//---DANCE MISSION: Time before BeatDisplay is started
CONST_INT DANCE_WAIT_TO_START_BEATDISPLAY	10000

// - Pauld gym stuff ---------------------------------------------------------------------------
 
VAR_INT gym_day gym_month gym_final_day gym_final_month

VAR_FLOAT gym_day_fitness

gym_day = -1

gym_month = -1

gym_final_day = -1

gym_final_month = -1

gym_day_fitness = 0.0

// - end gym stuff ---------------------------------------------------------------------------




// - burglary decision makers

VAR_INT burglary_male_decision	 burglary_female_decision  burglary_gang_decision


MISSION_END												