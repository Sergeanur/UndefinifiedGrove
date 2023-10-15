MISSION_START

// *******************************************************************************************
// ********************************** Mountain Bike Race *************************************
// *******************************************************************************************

// Mission start stuff

GOSUB mission_start_mtbikerace

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_mtbikerace_failed
ENDIF

GOSUB mission_cleanup_mtbikerace

MISSION_END
{

// ***************************************Mission Start*************************************

mission_start_mtbikerace:

flag_player_on_mission = 1
CLEAR_THIS_PRINT_BIG_NOW 1

IF flag_mtbike_passed_1stime = 0
	REGISTER_MISSION_GIVEN
ENDIF



SCRIPT_NAME MTBIKER
LOAD_MISSION_TEXT RACETOR
SET_MESSAGE_FORMATTING TRUE 355 370



//LOAD_MISSION_TEXT MTBIKE 

WAIT 0

USE_TEXT_COMMANDS TRUE

IF NOT IS_CHAR_DEAD scplayer
	SET_PLAYER_CONTROL Player1 OFF
ENDIF
DO_FADE 1000 FADE_OUT 

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE												




// TEMP! Need to go intot he main script /////

//LVAR_INT mtbikerace_selection
//mtbikerace_selection =3
//////////////////////////////////////////////





//initialise screen coordinates for the text
LVAR_FLOAT x_pos[7] y_pos[7] x_scale[7] y_scale[7] stuck_x[10] stuck_y[10] stuck_z[10]

LVAR_INT total_racers_mtbikerace stuck_timer[10] 
LVAR_FLOAT checkpoints_mtbike_x[120] checkpoints_mtbike_y[120] checkpoints_mtbike_z[120] checkpoint_headings[120]

LVAR_FLOAT coord_racers_current_x[10] coord_racers_current_y[10] coord_racers_current_z[10]
//LVAR_FLOAT coord_racers_bikes_current_x[10] coord_racers_bikes_current_y[10] 
LVAR_FLOAT coord_racers_bikes_current_x[10] coord_racers_bikes_current_y[10] coord_racers_bikes_current_z[10]


LVAR_INT r a c e  






LVAR_INT temp_int_mtbikerace mins seconds

LVAR_FLOAT new_heading_mtbike 


LVAR_INT  race_timer 	 //stored_bike_mtbikerace

LVAR_FLOAT opponents_speed_fast opponents_speed_slow opponents_speed_really_slow 

LVAR_FLOAT set_opponents_speed

LVAR_INT out_of_car_flag_mtbike flag_is_player_falling last_check_point 


LVAR_FLOAT x2 y2 z2 temp_float heading_mtbikerace player_distance_from_cp

LVAR_INT char_person_on_bike stored_checkpoint_when_not_on_bike

LVAR_INT out_of_car_timer falling_timer

//LVAR_INT flag_boosting_opponents_speed

VAR_INT random_number_mtbike

LVAR_INT dm_racers_mtbiker

LVAR_INT has_mtbiker_fallen[10]
LVAR_INT status_mtbikers_task[10]

LVAR_INT flag_make_char_go_past_final_checkpoint[10]


LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY dm_racers_mtbiker

	
flag_make_char_go_past_final_checkpoint[0] = 0
flag_make_char_go_past_final_checkpoint[1] = 0
flag_make_char_go_past_final_checkpoint[2] = 0
flag_make_char_go_past_final_checkpoint[3] = 0
flag_make_char_go_past_final_checkpoint[4] = 0
flag_make_char_go_past_final_checkpoint[5] = 0
flag_make_char_go_past_final_checkpoint[6] = 0
flag_make_char_go_past_final_checkpoint[7] = 0
flag_make_char_go_past_final_checkpoint[8] = 0
flag_make_char_go_past_final_checkpoint[9] = 0
					


//flag_boosting_opponents_speed = 0



// Interface Stuff

out_of_car_flag_mtbike = 0
flag_is_player_falling = 0



/*
x_pos[1]   = 581.7789	 
y_pos[1]   = 329.3647	 
x_scale[1] = 0.5427 	 
y_scale[1] = 2.0841  */

x_pos[0]   = 582.7242
y_pos[0]   = 356.2812
x_scale[0] = 52.2072 
y_scale[0] = 52.2072 

x_pos[1]   = 584.0833
y_pos[1]   = 329.3647
x_scale[1] = 0.5427 
y_scale[1] = 2.0841 
	 
x_pos[2]   = 572.9216
y_pos[2]   = 323.5937
x_scale[2] = 1.0585 
y_scale[2] = 4.8106 

x_pos[3]   = 585.7240
y_pos[3]   = 345.6341
x_scale[3] = 0.4929 
y_scale[3] = 1.8511 

x_pos[4]   = 582.7242
y_pos[4]   = 359.0323
x_scale[4] = 0.4993 
y_scale[4] = 2.6370 

x_pos[5]   = 582.7242
y_pos[5]   = 356.2812
x_scale[5] = 56.2072 
y_scale[5] = 56.2072 

x_pos[6]   = 582.7242
y_pos[6]   = 356.2812
x_scale[6] = 58.2072 
y_scale[6] = 58.2072 


has_mtbiker_fallen[0] = 0
has_mtbiker_fallen[1] =	0
has_mtbiker_fallen[2] =	0
has_mtbiker_fallen[3] =	0
has_mtbiker_fallen[4] =	0
has_mtbiker_fallen[5] =	0
has_mtbiker_fallen[6] =	0
has_mtbiker_fallen[7] =	0
has_mtbiker_fallen[8] =	0
has_mtbiker_fallen[9] =	0	


LVAR_INT total_checkpoints_mtbike_mtbikerace

LVAR_INT racer_model_mtbikerace[6] racers_car_model_mtbikerace[6]

GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP player1 Bbshortwht  boxingshort 2
BUILD_PLAYER_MODEL player1
//////////////////////////////////////////////////


IF mtbikerace_selection = 1
	GOTO mtbikerace1
ENDIF

IF mtbikerace_selection = 2
	GOTO mtbikerace2
ENDIF


IF mtbikerace_selection = 3
	GOTO mtbikerace3
ENDIF


/////////////////////////////////////////////


mtbikerace1:

IF mtbikerace_selection = 1
	PRINT_BIG MTROUT1 3000 5
ENDIF


	 /*
checkpoints_mtbike_x[0] = -2304.3438  
checkpoints_mtbike_y[0] = -1667.1248 
checkpoints_mtbike_z[0] = 483.5860

					   */

checkpoints_mtbike_x[0] = -2304.3438  
checkpoints_mtbike_y[0] = -1667.1248 
checkpoints_mtbike_z[0] = 483.3860
checkpoint_headings[0]	= 208.0

checkpoints_mtbike_x[1] = -2280.4800 
checkpoints_mtbike_y[1] = -1730.3153 
checkpoints_mtbike_z[1] = 467.8242 
checkpoint_headings[1]	= 178.6093 

checkpoints_mtbike_x[2] = -2357.1819 
checkpoints_mtbike_y[2] = -1815.4287 
checkpoints_mtbike_z[2] = 431.4012 
checkpoint_headings[2]	= 64.0670 

checkpoints_mtbike_x[3] = -2447.4026 
checkpoints_mtbike_y[3] = -1718.4354 
checkpoints_mtbike_z[3] = 430.9626 
checkpoint_headings[3]	= 148.4592 

checkpoints_mtbike_x[4] = -2446.6516 
checkpoints_mtbike_y[4] = -1810.6578 
checkpoints_mtbike_z[4] = 409.0582 
checkpoint_headings[4]	= 93.1950 

checkpoints_mtbike_x[5] = -2527.5864 
checkpoints_mtbike_y[5] = -1711.3759 
checkpoints_mtbike_z[5] = 401.1750 
checkpoint_headings[5]	= 16.0889 

checkpoints_mtbike_x[6] = -2552.8447 
checkpoints_mtbike_y[6] = -1668.8422 
checkpoints_mtbike_z[6] = 398.4550 	//dodgy check point wherre dudes get stuck
checkpoint_headings[6]	= 155.2717 

checkpoints_mtbike_x[7] = -2534.5166 
checkpoints_mtbike_y[7] = -1802.5302 
checkpoints_mtbike_z[7] = 374.7244 
checkpoint_headings[7]	= 127.1514 

checkpoints_mtbike_x[8] = -2595.2495 
checkpoints_mtbike_y[8] = -1631.9951 
checkpoints_mtbike_z[8] = 342.7061 
checkpoint_headings[8]	= 176.4540 

checkpoints_mtbike_x[9] = -2526.4302 
checkpoints_mtbike_y[9] = -1888.4586 
checkpoints_mtbike_z[9] = 292.5149 
checkpoint_headings[9]	= 77.3269 

checkpoints_mtbike_x[10] =-2652.8594 
checkpoints_mtbike_y[10] =-1739.1473 
checkpoints_mtbike_z[10] =255.1567 
checkpoint_headings[10]	 =189.6474 

checkpoints_mtbike_x[11] =-2491.2285 
checkpoints_mtbike_y[11] =-2034.5972 
checkpoints_mtbike_z[11] =150.1413 
checkpoint_headings[11]	 = 225.1505 

checkpoints_mtbike_x[12] =-2428.6436 
checkpoints_mtbike_y[12] =-2088.8870 
checkpoints_mtbike_z[12] =121.9700 
checkpoint_headings[12]	 =243.2365 

checkpoints_mtbike_x[13] =-2322.0586 
checkpoints_mtbike_y[13] =-2097.6396 
checkpoints_mtbike_z[13] =114.0331 
checkpoint_headings[13]	 =292.4177 

checkpoints_mtbike_x[14] = -2218.1306 
checkpoints_mtbike_y[14] = -2047.0885 
checkpoints_mtbike_z[14] = 118.8139 //end of the line/ no nodes here.....
checkpoint_headings[14]	 = 315.4970 

checkpoints_mtbike_x[15] = -2168.4204 
checkpoints_mtbike_y[15] = -1991.8716 
checkpoints_mtbike_z[15] = 117.8448 
checkpoint_headings[15]  = 314.2462 

checkpoints_mtbike_x[16] = -2102.5454 
checkpoints_mtbike_y[16] = -1895.2271 
checkpoints_mtbike_z[16] = 108.4655 
checkpoint_headings[16]	 = 174.3973 

checkpoints_mtbike_x[17] = -2162.3645 
checkpoints_mtbike_y[17] = -2039.5222 
checkpoints_mtbike_z[17] = 91.2074 
checkpoint_headings[17]	 = 135.1339 

checkpoints_mtbike_x[18] = -2373.6670 
checkpoints_mtbike_y[18] = -2195.0891 
checkpoints_mtbike_z[18] = 32.3837 
checkpoint_headings[18]	 = 112.8270 

total_checkpoints_mtbike_mtbikerace = 19  

total_racers_mtbikerace = 6

racer_model_mtbikerace[0] = wmybmx
racer_model_mtbikerace[1] = wmymoun
racer_model_mtbikerace[2] = bmymoun
racer_model_mtbikerace[3] = wmymoun
racer_model_mtbikerace[4] = wmybmx
racer_model_mtbikerace[5] = bmymoun

racers_car_model_mtbikerace[0] = mtbike
racers_car_model_mtbikerace[1] = mtbike
racers_car_model_mtbikerace[2] = mtbike
racers_car_model_mtbikerace[3] = mtbike
racers_car_model_mtbikerace[4] = mtbike
racers_car_model_mtbikerace[5] = mtbike


opponents_speed_fast = 38.0 
opponents_speed_slow = 27.0
opponents_speed_really_slow = 23.0




GOTO mtbikerace_start


mtbikerace2:


IF mtbikerace_selection = 2
	PRINT_BIG MTROUT2 3000 5

ENDIF


checkpoints_mtbike_x[0] = -2304.3438  
checkpoints_mtbike_y[0] = -1667.1248 
checkpoints_mtbike_z[0] = 483.5860
checkpoint_headings[0]  = 208.0

checkpoints_mtbike_x[1] = -2279.8574
checkpoints_mtbike_y[1] = -1732.7520
checkpoints_mtbike_z[1] = 466.7990 
checkpoint_headings[1]  = 163.4517 

checkpoints_mtbike_x[2] = -2355.6089
checkpoints_mtbike_y[2] = -1815.3241
checkpoints_mtbike_z[2] = 431.5401 
checkpoint_headings[2]  = 90.1409 

checkpoints_mtbike_x[3] = -2458.8430
checkpoints_mtbike_y[3] = -1733.4342
checkpoints_mtbike_z[3] = 426.4320 
checkpoint_headings[3]  = 187.8666 

checkpoints_mtbike_x[4] = -2460.9041
checkpoints_mtbike_y[4] = -1807.3839
checkpoints_mtbike_z[4] = 406.6599 
checkpoint_headings[4]  = 60.8579 

checkpoints_mtbike_x[5] = -2521.2917
checkpoints_mtbike_y[5] = -1707.7533
checkpoints_mtbike_z[5] = 401.2728 
checkpoint_headings[5]  = 2.4494 

checkpoints_mtbike_x[6] = -2467.1262
checkpoints_mtbike_y[6] = -1467.8677
checkpoints_mtbike_z[6] = 388.9202 
checkpoint_headings[6]  = 270.5872 

checkpoints_mtbike_x[7] = -2324.3667
checkpoints_mtbike_y[7] = -1455.1486
checkpoints_mtbike_z[7] = 380.9767 
checkpoint_headings[7]  = 233.5233 

checkpoints_mtbike_x[8] = -2251.9221
checkpoints_mtbike_y[8] = -1492.6534
checkpoints_mtbike_z[8] = 377.8563 
checkpoint_headings[8]  = 196.0940 

checkpoints_mtbike_x[9] = -2191.9495
checkpoints_mtbike_y[9] = -1713.9932
checkpoints_mtbike_z[9] = 375.5497 
checkpoint_headings[9]  = 322.9377 

checkpoints_mtbike_x[10] =-2206.7781
checkpoints_mtbike_y[10] =-1528.3400
checkpoints_mtbike_z[10] =359.6156 
checkpoint_headings[10]  =20.0752 

checkpoints_mtbike_x[11] =-2280.6833
checkpoints_mtbike_y[11] =-1426.1876
checkpoints_mtbike_z[11] =355.6379 
checkpoint_headings[11]  =58.5606 

checkpoints_mtbike_x[12] =-2419.2219
checkpoints_mtbike_y[12] =-1392.9248
checkpoints_mtbike_z[12] =358.1968 
checkpoint_headings[12]  =98.4498 

checkpoints_mtbike_x[13] =-2569.7671
checkpoints_mtbike_y[13] =-1460.6979
checkpoints_mtbike_z[13] =357.2175 
checkpoint_headings[13]  =325.2711 

checkpoints_mtbike_x[14] =-2440.8894
checkpoints_mtbike_y[14] =-1368.2570
checkpoints_mtbike_z[14] =337.4130 
checkpoint_headings[14]  =304.5745 

checkpoints_mtbike_x[15] =-2357.0701
checkpoints_mtbike_y[15] =-1286.3617
checkpoints_mtbike_z[15] =306.1062 
checkpoint_headings[15]  =103.3780 

checkpoints_mtbike_x[16] =-2508.1528
checkpoints_mtbike_y[16] =-1273.6208
checkpoints_mtbike_z[16] =271.7476 
checkpoint_headings[16]  =103.6739 

checkpoints_mtbike_x[17] =-2595.4917
checkpoints_mtbike_y[17] =-1360.2438
checkpoints_mtbike_z[17] =264.5467 
checkpoint_headings[17]  =100.4540 

checkpoints_mtbike_x[18] =-2671.1550
checkpoints_mtbike_y[18] =-1378.1902
checkpoints_mtbike_z[18] =252.3816 
checkpoint_headings[18]  =352.7671 

checkpoints_mtbike_x[19] =-2608.5
checkpoints_mtbike_y[19] =-1285.22
checkpoints_mtbike_z[19] =219.88 
checkpoint_headings[19]  =335.6836 

checkpoints_mtbike_x[20] =-2537.2939
checkpoints_mtbike_y[20] =-1121.2975
checkpoints_mtbike_z[20] =175.3035 
checkpoint_headings[20]  =140.5565 

checkpoints_mtbike_x[21] =-2634.5535
checkpoints_mtbike_y[21] =-1169.3840
checkpoints_mtbike_z[21] =166.8249 
checkpoint_headings[21]  =128.4300 	

checkpoints_mtbike_x[22] =-2747.9368
checkpoints_mtbike_y[22] =-1377.9432
checkpoints_mtbike_z[22] =143.0419 
checkpoint_headings[22]  =168.2364 

checkpoints_mtbike_x[23] =-2773.7800
checkpoints_mtbike_y[23] =-1670.3008
checkpoints_mtbike_z[23] =140.4944 
checkpoint_headings[23]  =169.7697 

checkpoints_mtbike_x[24] =-2733.3638
checkpoints_mtbike_y[24] =-1876.0216
checkpoints_mtbike_z[24] =138.8912 
checkpoint_headings[24]  =269.3722 

checkpoints_mtbike_x[25] =-2620.5791
checkpoints_mtbike_y[25] =-2066.7456
checkpoints_mtbike_z[25] =129.5185 
checkpoint_headings[25]  =201.5526 

checkpoints_mtbike_x[26] =-2438.3792
checkpoints_mtbike_y[26] =-2086.9282
checkpoints_mtbike_z[26] =123.3899 
checkpoint_headings[26]  =257.1008 

checkpoints_mtbike_x[27] =-2335.7844
checkpoints_mtbike_y[27] =-2102.4417
checkpoints_mtbike_z[27] =112.9194 
checkpoint_headings[27]  =283.7088 

checkpoints_mtbike_x[28] =-2101.0046
checkpoints_mtbike_y[28] =-1893.2450
checkpoints_mtbike_z[28] =108.8372 
checkpoint_headings[28]  =174.9563 

checkpoints_mtbike_x[29] =-2156.2161
checkpoints_mtbike_y[29] =-2031.7744
checkpoints_mtbike_z[29] =91.9901 
checkpoint_headings[29]  =145.7200 

checkpoints_mtbike_x[30] =-2236.1113
checkpoints_mtbike_y[30] =-2118.7666
checkpoints_mtbike_z[30] =66.3876 
checkpoint_headings[30]  =131.2891 

checkpoints_mtbike_x[31] =-2374.9575
checkpoints_mtbike_y[31] =-2195.4536
checkpoints_mtbike_z[31] =32.3626 
checkpoint_headings[31]  =113.8008 


total_checkpoints_mtbike_mtbikerace = 32

total_racers_mtbikerace = 6


racer_model_mtbikerace[0] = wmybmx
racer_model_mtbikerace[1] = wmymoun
racer_model_mtbikerace[2] = bmymoun
racer_model_mtbikerace[3] = wmymoun
racer_model_mtbikerace[4] = wmybmx
racer_model_mtbikerace[5] = bmymoun

racers_car_model_mtbikerace[0] = mtbike
racers_car_model_mtbikerace[1] = mtbike
racers_car_model_mtbikerace[2] = mtbike
racers_car_model_mtbikerace[3] = mtbike
racers_car_model_mtbikerace[4] = mtbike
racers_car_model_mtbikerace[5] = mtbike


opponents_speed_fast = 35.0 
opponents_speed_slow = 30.0

opponents_speed_really_slow = 28.0



GOTO mtbikerace_start

mtbikerace3:



IF mtbikerace_selection = 3
   PRINT_BIG MTROUT3 3000 5
ENDIF




checkpoints_mtbike_x[0] = -2432.8760  
checkpoints_mtbike_y[0] = -1939.6539 
checkpoints_mtbike_z[0] = 303.7731
checkpoint_headings[0]  = 273.0

checkpoints_mtbike_x[1] = -2339.9976  
checkpoints_mtbike_y[1] = -1941.2238 
checkpoints_mtbike_z[1] = 295.8086 
checkpoint_headings[1]  = 269.6869 

checkpoints_mtbike_x[2] = -2286.7820 
checkpoints_mtbike_y[2] = -1940.2395 
checkpoints_mtbike_z[2] = 268.8093 
checkpoint_headings[2]  = 245.1214 

checkpoints_mtbike_x[3] = -2234.8911 
checkpoints_mtbike_y[3] = -1925.0674 
checkpoints_mtbike_z[3] = 242.4752 
checkpoint_headings[3]  = 300.1313 

checkpoints_mtbike_x[4] = -2205.4290 
checkpoints_mtbike_y[4] = -1914.0288 
checkpoints_mtbike_z[4] = 239.2782 
checkpoint_headings[4]  = 269.1174 

checkpoints_mtbike_x[5] = -2204.8611 
checkpoints_mtbike_y[5] = -1914.0312 
checkpoints_mtbike_z[5] = 239.1954 
checkpoint_headings[5]  = 269.4709 

checkpoints_mtbike_x[6] = -2173.8950 
checkpoints_mtbike_y[6] = -1903.8802 
checkpoints_mtbike_z[6] = 233.6897 
checkpoint_headings[6]  = 314.0030 

checkpoints_mtbike_x[7] = -2169.7278 
checkpoints_mtbike_y[7] = -1885.1361 
checkpoints_mtbike_z[7] = 231.1551 
checkpoint_headings[7]  = 8.2283 

checkpoints_mtbike_x[8] = -2173.5110 
checkpoints_mtbike_y[8] = -1861.9478 
checkpoints_mtbike_z[8] = 222.7416 
checkpoint_headings[8]  = 13.9626 

checkpoints_mtbike_x[9] = -2173.6409 
checkpoints_mtbike_y[9] = -1843.2456 
checkpoints_mtbike_z[9] = 217.9074 
checkpoint_headings[9]  = 13.6434    

checkpoints_mtbike_x[10] =-2148.4309 
checkpoints_mtbike_y[10] =-1792.7305 
checkpoints_mtbike_z[10] =210.1444 
checkpoint_headings[10]  =294.9009 

checkpoints_mtbike_x[11] =-2129.2415 
checkpoints_mtbike_y[11] =-1775.9731 
checkpoints_mtbike_z[11] =207.5088 
checkpoint_headings[11]  =313.3039 

checkpoints_mtbike_x[12] =-2109.7236 
checkpoints_mtbike_y[12] =-1755.5731 
checkpoints_mtbike_z[12] =195.2899 
checkpoint_headings[12]  =332.9617 

checkpoints_mtbike_x[13] =-2101.7561 
checkpoints_mtbike_y[13] =-1744.0090 
checkpoints_mtbike_z[13] =195.9091 
checkpoint_headings[13]  =324.6714 

checkpoints_mtbike_x[14] =-2072.0796 
checkpoints_mtbike_y[14] =-1644.9003 
checkpoints_mtbike_z[14] =174.3308 
checkpoint_headings[14]  =357.3320 

checkpoints_mtbike_x[15] =-2050.7004 
checkpoints_mtbike_y[15] =-1571.4911 
checkpoints_mtbike_z[15] =137.4740 
checkpoint_headings[15]  =272.8463 

checkpoints_mtbike_x[16] =-2041.6647 
checkpoints_mtbike_y[16] =-1509.0067 
checkpoints_mtbike_z[16] =124.2398 
checkpoint_headings[16]  =16.1558 

checkpoints_mtbike_x[17] =-2033.5597 
checkpoints_mtbike_y[17] =-1438.6035 
checkpoints_mtbike_z[17] =93.2658 
checkpoint_headings[17]  =210.8019 

checkpoints_mtbike_x[18] =-1991.3282 
checkpoints_mtbike_y[18] =-1596.5256 
checkpoints_mtbike_z[18] =86.5936 
checkpoint_headings[18]  =182.1263 

checkpoints_mtbike_x[19] =-2027.7069 
checkpoints_mtbike_y[19] =-1789.3309 
checkpoints_mtbike_z[19] =96.5540 
checkpoint_headings[19]  =160.6694 

checkpoints_mtbike_x[20] =-2099.8538 
checkpoints_mtbike_y[20] =-1890.8799 
checkpoints_mtbike_z[20] =109.2095 
checkpoint_headings[20]  =175.6702 

checkpoints_mtbike_x[21] =-2118.3008 
checkpoints_mtbike_y[21] =-1948.2500 
checkpoints_mtbike_z[21] =99.0724 
checkpoint_headings[21]  =162.0987 

checkpoints_mtbike_x[22] =-2233.6492 
checkpoints_mtbike_y[22] =-2117.0457 
checkpoints_mtbike_z[22] =67.2467 
checkpoint_headings[22]  =134.1193 

checkpoints_mtbike_x[23] =-2373.8918 
checkpoints_mtbike_y[23] =-2194.9983 
checkpoints_mtbike_z[23] =32.3799 
checkpoint_headings[23]  =115.9183 





total_checkpoints_mtbike_mtbikerace = 24

total_racers_mtbikerace = 3


racer_model_mtbikerace[0] = bmymoun
racer_model_mtbikerace[1] = wmymoun
racer_model_mtbikerace[2] = wmybmx


racers_car_model_mtbikerace[0] = mtbike
racers_car_model_mtbikerace[1] = mtbike
racers_car_model_mtbikerace[2] = mtbike


opponents_speed_fast = 40.0 
opponents_speed_slow = 30.0
opponents_speed_really_slow = 25.0

//opponents_speed_fast = 40.0 
//opponents_speed_slow = 30.0



REQUEST_CAR_RECORDING 829
REQUEST_CAR_RECORDING 830
REQUEST_CAR_RECORDING 831
REQUEST_CAR_RECORDING 832
REQUEST_CAR_RECORDING 833
REQUEST_CAR_RECORDING 834



WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 829
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 830
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 831
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 832
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 833
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 834


	WAIT 0
ENDWHILE

CREATE_CHECKPOINT CHECKPOINT_TUBE racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] second_cp_mtbikeracex second_cp_mtbikeracey second_cp_mtbikeracez 6.0 cp_marker_mtbikerace
DELETE_CHECKPOINT cp_marker_mtbikerace



GOTO mtbikerace_start




mtbikerace_start:


set_opponents_speed = opponents_speed_really_slow 


WHILE NOT IS_PLAYER_PLAYING player1
	WAIT 0
ENDWHILE

CLEAR_AREA checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0] 0.5 0

LOAD_SCENE checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0]



//SET_CHAR_COORDINATES scplayer checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0] 
///SET_CHAR_HEADING scplayer 0.0
//SET_CAMERA_BEHIND_PLAYER

		// switching OFF the roads at the mountain
	  //	SWITCH_ROADS_OFF -2999.5276 -2629.4624 0.8299 -1612.5211 -979.4300  480.2183 
	   //	SET_CAR_DENSITY_MULTIPLIER 0.0
	   //	SET_PED_DENSITY_MULTIPLIER 0.0 



DISABLE_ALL_ENTRY_EXITS TRUE



  

//VAR_INT temproad
//CREATE_OBJECT_NO_OFFSET temp_road 0.0 0.0 40.0 temproad
//SET_OBJECT_HEADING temproad 45.0

LVAR_INT total_checkpoints_mtbike_mtbikerace_minus_1
total_checkpoints_mtbike_mtbikerace_minus_1 = total_checkpoints_mtbike_mtbikerace - 1

r = 0
a = 0
c = 0
e = 0

LVAR_FLOAT racer_cp_x_mtbikerace[6] racer_cp_y_mtbikerace[6] racer_cp_z_mtbikerace[6]

//VAR_FLOAT stuck_x[6] stuck_y[6]
//LVAR_INT racer_blip_mtbikerace[6]
LVAR_INT racers_mtbike[6]
LVAR_INT mtbikeracer[6]


LVAR_INT total_mtbikerace

total_mtbikerace = total_racers_mtbikerace


LVAR_INT cpcounter_mtbikerace[10]

WHILE a < total_mtbikerace
	
	cpcounter_mtbikerace[a] = 0
	++ a
ENDWHILE

//PRINT_NOW BIK1_1 5000 1 //"~g~Get a fast car and get to the starting grid."

LVAR_INT	first_blip_mtbikerace 
ADD_BLIP_FOR_COORD checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0] first_blip_mtbikerace

LVAR_INT race_flag_mtbikerace
LVAR_INT playaz_mtbikerace
race_flag_mtbikerace = 0


//VIEW_INTEGER_VARIABLE out_of_car_flag_mtbike out_of_car_flag_mtbike





mission_mtbikerace_loop:
WAIT 0
GET_GAME_TIMER game_timer

LVAR_INT position_mtbikerace
position_mtbikerace = 0
a = 0	



IF race_flag_mtbikerace = 0
	IF NOT IS_CHAR_DEAD scplayer
		SET_CHAR_COORDINATES scplayer checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0] 

		IF LOCATE_CHAR_ANY_MEANS_2D scplayer checkpoints_mtbike_x[1] checkpoints_mtbike_y[1] 100.0 100.0 0
			r = 0
			x = checkpoints_mtbike_x[0] + 7.0
			y = checkpoints_mtbike_y[0] + 7.0
			z = checkpoints_mtbike_z[0] + 4.0
			x2 = checkpoints_mtbike_x[0] - 7.0
			y2 = checkpoints_mtbike_y[0] - 7.0
			z2 = checkpoints_mtbike_z[0] - 4.0
		   //	SWITCH_ROADS_OFF x y z x2 y2 z2


			++ race_flag_mtbikerace
		ENDIF
	ENDIF
ENDIF

IF race_flag_mtbikerace = 1

		temp_int_mtbikerace = total_mtbikerace - 1
		IF HAS_MODEL_LOADED	racer_model_mtbikerace[r]
		AND HAS_MODEL_LOADED racers_car_model_mtbikerace[r]
			++ r
		ELSE
			REQUEST_MODEL racer_model_mtbikerace[r]
			REQUEST_MODEL racers_car_model_mtbikerace[r]
		ENDIF
			
		IF r = temp_int_mtbikerace
			++ race_flag_mtbikerace
		ENDIF
	
ENDIF

IF race_flag_mtbikerace = 2
	CREATE_CAR racers_car_model_mtbikerace[0] checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0] stored_bike_mtbikerace
	TURN_CAR_TO_FACE_COORD stored_bike_mtbikerace checkpoints_mtbike_x[1] checkpoints_mtbike_y[1]// MUST PLACE checkpoint[1] STRAIGHT AHEAD FROM START
	temp_int_mtbikerace = total_mtbikerace - 1
	

	a = 0
	WHILE a < total_mtbikerace
		c = a / 2
		c *= 2
		IF c = a//IS a AN EVEN NUMBER?
			x = 2.0
			
			e = a * 4
			temp_float =# e
			temp_float *= -3.0
			y = temp_float

		ELSE
			x = -2.0
			
			e = a - 1
			e *= 4
			temp_float =# e
			temp_float *= -1.2
			y = temp_float

		ENDIF
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_mtbikerace x y z x y z
		GET_CAR_HEADING	stored_bike_mtbikerace heading_mtbikerace
		z = checkpoints_mtbike_z[0]
		
		racer_cp_x_mtbikerace[a] = x
		racer_cp_y_mtbikerace[a] = y
		GET_GROUND_Z_FOR_3D_COORD x y z z
		racer_cp_z_mtbikerace[a] = z
		
		IF a < temp_int_mtbikerace
			CREATE_CAR racers_car_model_mtbikerace[a] x y z racers_mtbike[a]
			ADD_STUCK_CAR_CHECK_WITH_WARP racers_mtbike[a] 0.5 5000 TRUE FALSE TRUE 1
			SET_CAR_TRACTION racers_mtbike[a] 1.5

			MARK_MODEL_AS_NO_LONGER_NEEDED racers_car_model_mtbikerace[a]
			SET_CAR_HEADING	racers_mtbike[a] heading_mtbikerace
		  //	LOCK_CAR_DOORS racers_mtbike[a] CARLOCK_LOCKED
			SET_CAR_PROOFS racers_mtbike[a] FALSE TRUE FALSE FALSE FALSE
			SET_CAR_WATERTIGHT racers_mtbike[a] TRUE
			SET_CAR_STRONG racers_mtbike[a] TRUE
			SET_UPSIDEDOWN_CAR_NOT_DAMAGED racers_mtbike[a] TRUE
			SET_CAR_STRAIGHT_LINE_DISTANCE racers_mtbike[a] 5
			SET_CAN_BURST_CAR_TYRES racers_mtbike[a] FALSE

			CREATE_CHAR_INSIDE_CAR racers_mtbike[a] PEDTYPE_CIVMALE racer_model_mtbikerace[a] mtbikeracer[a]
			CLEAR_CHAR_LAST_DAMAGE_ENTITY mtbikeracer[a]	
			MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_mtbikerace[a]
			SET_CHAR_CAN_BE_SHOT_IN_VEHICLE mtbikeracer[a] FALSE
		   //	ADD_BLIP_FOR_CHAR mtbikeracer[a] racer_blip_mtbikerace[a]
		   //	CHANGE_BLIP_DISPLAY	racer_blip_mtbikerace[a] MARKER_ONLY
		   //	CHANGE_BLIP_COLOUR racer_blip_mtbikerace[a] RED
		   SET_CHAR_DECISION_MAKER mtbikeracer[a] dm_racers_mtbiker
		   IMPROVE_CAR_BY_CHEATING racers_mtbike[a] TRUE

			CAR_SET_IDLE racers_mtbike[a]
			SET_CAR_DRIVING_STYLE racers_mtbike[a] 2
			SET_CAR_CRUISE_SPEED racers_mtbike[a] set_opponents_speed
			SET_CAR_ONLY_DAMAGED_BY_PLAYER racers_mtbike[a] TRUE
		ELSE
			LVAR_INT spectator_mtbikerace[11] flag_girl_mtbikerace
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_mtbikerace -4.0435 6.5755 -0.8 x y z
			CREATE_RANDOM_CHAR x y z flag_girl_mtbikerace
			TASK_TURN_CHAR_TO_FACE_COORD flag_girl_mtbikerace checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_mtbikerace -5.6392 2.9435 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_mtbikerace[0]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_mtbikerace[0] checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_mtbikerace -6.0071 -2.0778 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_mtbikerace[1]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_mtbikerace[1] checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_mtbikerace -7.6039 -5.0807 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_mtbikerace[2]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_mtbikerace[2] checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_mtbikerace -7.0724 -6.4057 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_mtbikerace[3]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_mtbikerace[3] checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_mtbikerace -5.9988 -13.553 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_mtbikerace[4]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_mtbikerace[4] checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_mtbikerace 6.0730 -15.2059 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_mtbikerace[5]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_mtbikerace[5] checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_mtbikerace 7.2639 -12.9933 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_mtbikerace[6]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_mtbikerace[6] checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_mtbikerace 5.8429 -7.7069 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_mtbikerace[7]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_mtbikerace[7] checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_mtbikerace 8.4135 -3.7971 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_mtbikerace[8]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_mtbikerace[8] checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_mtbikerace 6.2507 0.3634 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_mtbikerace[9]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_mtbikerace[9] checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_mtbikerace 8.6397 2.4190 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_mtbikerace[10]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_mtbikerace[10] checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0]
			DELETE_CAR stored_bike_mtbikerace
			REMOVE_BLIP first_blip_mtbikerace//PLAYERS STARTING COORDS
			ADD_BLIP_FOR_COORD racer_cp_x_mtbikerace[temp_int_mtbikerace] racer_cp_y_mtbikerace[temp_int_mtbikerace] racer_cp_z_mtbikerace[temp_int_mtbikerace] first_blip_mtbikerace
			SET_CAR_DENSITY_MULTIPLIER 0.5
			mtbikeracer[a] = scplayer


			
			playaz_mtbikerace = a
		//	CREATE_CAR racers_car_model_mtbikerace[a] racer_cp_x_mtbikerace[temp_int_mtbikerace] racer_cp_y_mtbikerace[temp_int_mtbikerace] racer_cp_z_mtbikerace[temp_int_mtbikerace] stored_bike_mtbikerace//DEBUG!!!
		//	SET_CAR_HEADING	stored_bike_mtbikerace heading_mtbikerace//DEBUG!!!
		ENDIF
		++ a
	ENDWHILE
	++ race_flag_mtbikerace
	
	

	IF NOT IS_CHAR_DEAD scplayer
		STORE_CAR_CHAR_IS_IN scplayer racers_mtbike[playaz_mtbikerace]
		SET_CAR_HEALTH racers_mtbike[playaz_mtbikerace] 500
		CLEAR_CAR_LAST_DAMAGE_ENTITY racers_mtbike[playaz_mtbikerace]

	  //	SET_PLAYER_CONTROL Player1 ON
	  	SET_CHAR_COORDINATES scplayer racer_cp_x_mtbikerace[playaz_mtbikerace] racer_cp_y_mtbikerace[playaz_mtbikerace] racer_cp_z_mtbikerace[playaz_mtbikerace] 
	 	SET_CAR_HEADING racers_mtbike[playaz_mtbikerace] checkpoint_headings[0]
	  //	TURN_CAR_TO_FACE_COORD racers_mtbike[playaz_mtbikerace] racer_cp_x_mtbikerace[0] racer_cp_y_mtbikerace[0]
	 //	SET_CHAR_HEADING scplayer 204.0																		   
		SET_CAMERA_BEHIND_PLAYER
	ENDIF

	SWITCH_WIDESCREEN ON
	
	WAIT 500



   	DO_FADE 1000 FADE_IN 
	CLEAR_PRINTS
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE






	//	PRINT_BIG MTROUTE 3000 6

	IF mtbikerace_selection = 1
	  	PRINT_NOW MTROUT1 3000 6
	  //	PRINT_BIG MTROUT1 3000 5 // scotch bonnet 
	ENDIF


	IF mtbikerace_selection = 2
	  //	PRINT_BIG MTROUT2 3000 5 // birdseye 
	  PRINT_NOW MTROUT2 3000 6

	ENDIF


	IF mtbikerace_selection = 3
	 //  PRINT_BIG MTROUT3 3000 5 // cobra 
		PRINT_NOW MTROUT3 3000 6

	ENDIF



	 


	
ENDIF


IF race_flag_mtbikerace = 3
	IF NOT IS_CHAR_DEAD scplayer
	   //	IF NOT LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer racer_cp_x_mtbikerace[playaz_mtbikerace] racer_cp_y_mtbikerace[playaz_mtbikerace] racer_cp_z_mtbikerace[playaz_mtbikerace] 2.0 2.0 2.0 1
		
			temp_int_mtbikerace = total_mtbikerace - 1
			a = 0
	  	 	WHILE a < temp_int_mtbikerace
			
		  		IF IS_CAR_DEAD racers_mtbike[a]
				OR IS_CHAR_DEAD	mtbikeracer[a]
					PRINT_NOW RACES25 5000 1 // you have been disqualifed
					GOTO mission_mtbikerace_failed

				ENDIF  	 
				
				IF NOT IS_CAR_DEAD  racers_mtbike[a]
					IF NOT IS_CHAR_DEAD mtbikeracer[a]

						IF NOT IS_CAR_HEALTH_GREATER racers_mtbike[a] 999
						OR NOT IS_CHAR_IN_CAR mtbikeracer[a] racers_mtbike[a]

							PRINT_BIG RACES_3 1200 4 // "3..2..1.. GO GO GO!"
							DISPLAY_CAR_NAMES FALSE
							race_flag_mtbikerace = 7
						ENDIF
					ENDIF
				ENDIF
				++ a
			ENDWHILE	   
	 //	ELSE

		  //	
		/*   	IF NOT IS_CAR_DEAD racers_mtbike[0]
				

				POINT_CAMERA_AT_CAR racers_mtbike[0] FIXED INTERPOLATION
			ENDIF 	  */	 
	   /*		IF NOT IS_POINT_ON_SCREEN checkpoints_mtbike_x[1] checkpoints_mtbike_y[1] checkpoints_mtbike_z[1] 5.0 
				SET_CAMERA_BEHIND_PLAYER 
			ENDIF				   */



		   //	CLEAR_THIS_PRINT BIK1_1
			SET_PLAYER_CONTROL player1 OFF
			SET_EVERYONE_IGNORE_PLAYER player1 TRUE
			SET_ALL_CARS_CAN_BE_DAMAGED FALSE
			PRINT_BIG RACES_4 1100 4
			ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_RACE_START_3




			

			LVAR_INT	mtbike_timer
			mtbike_timer = game_timer + 999
			++ race_flag_mtbikerace

		/*	IF mtbikeracer[playaz_mtbikerace] = scplayer
				STORE_CAR_CHAR_IS_IN scplayer racers_mtbike[playaz_mtbikerace]

			ENDIF	*/

	ENDIF
ENDIF

IF race_flag_mtbikerace = 4
	IF mtbike_timer <	game_timer
		PRINT_BIG RACES_5 1100 4


	 //	PRINT_BIG 3000 
		ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_RACE_START_2

		mtbike_timer = game_timer + 999
		++ race_flag_mtbikerace
	ENDIF
ENDIF

IF race_flag_mtbikerace = 5
	IF mtbike_timer <	game_timer
		PRINT_BIG RACES_6 1100 4
		ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_RACE_START_1
		mtbike_timer = game_timer + 999
		++ race_flag_mtbikerace
	ENDIF
ENDIF

IF race_flag_mtbikerace = 6
	IF mtbike_timer <	game_timer
		IF NOT IS_CHAR_DEAD	scplayer
			PRINT_BIG RACES_7 800 4
			ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_RACE_START_GO
	 //		REPORT_MISSION_AUDIO_EVENT_AT_POSITION checkpoints_mtbike_x[0] checkpoints_mtbike_y[0] checkpoints_mtbike_z[0] SOUND_AIR_HORN

			SET_PLAYER_CONTROL player1 ON
			SET_EVERYONE_IGNORE_PLAYER player1 FALSE
			SET_ALL_CARS_CAN_BE_DAMAGED TRUE
			RESTORE_CAMERA
			//SET_CAMERA_BEHIND_PLAYER
			SWITCH_WIDESCREEN OFF

			//	PRINT_BIG MTROUTE 3000 6

			DISPLAY_CAR_NAMES FALSE



			race_timer = game_timer

			temp_int_mtbikerace = total_mtbikerace - 1
			a = 0
			WHILE a < temp_int_mtbikerace
				IF NOT IS_CAR_DEAD racers_mtbike[a]
					SET_CAR_ONLY_DAMAGED_BY_PLAYER racers_mtbike[a] FALSE
				ENDIF



				++ a
			ENDWHILE
			mtbike_timer = 0
			//DISPLAY_ONSCREEN_TIMER_WITH_STRING mtbike_timer TIMER_UP RACES


			++ race_flag_mtbikerace	 
		ENDIF		
	ENDIF
ENDIF

IF race_flag_mtbikerace > 6
	WHILE a < total_mtbikerace

	  //	VIEW_INTEGER_VARIABLE a a
		IF NOT IS_CHAR_DEAD	scplayer
			IF NOT IS_CHAR_DEAD	mtbikeracer[a]
				
				// IF player shoots char, mission failed
				IF NOT mtbikeracer[a] = scplayer

					IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR mtbikeracer[a] scplayer
						IF NOT IS_CAR_DEAD racers_mtbike[playaz_mtbikerace]	
					   		IF NOT HAS_CHAR_BEEN_DAMAGED_BY_CAR mtbikeracer[a] racers_mtbike[playaz_mtbikerace]
								PRINT_NOW RACES25 5000 1 // you have been disqualifed
								GOTO mission_mtbikerace_failed
							ENDIF
						ENDIF
					ENDIF
				ENDIF







				IF race_flag_mtbikerace = 8


					IF NOT mtbikeracer[a] = scplayer
						IF cpcounter_mtbikerace[playaz_mtbikerace] = cpcounter_mtbikerace[a]
							LVAR_FLOAT player_distance_from_cp_mtbikerace racer_distance_from_cp_mtbikerace
							GET_CHAR_COORDINATES mtbikeracer[a] x y z
							GET_DISTANCE_BETWEEN_COORDS_2D x y racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_distance_from_cp_mtbikerace

							GET_CHAR_COORDINATES scplayer x y z
							GET_DISTANCE_BETWEEN_COORDS_2D x y racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] player_distance_from_cp

							IF player_distance_from_cp > racer_distance_from_cp_mtbikerace
								++ position_mtbikerace
							ENDIF
						ELSE
							IF cpcounter_mtbikerace[playaz_mtbikerace] < cpcounter_mtbikerace[a]
								++ position_mtbikerace
							ENDIF
						ENDIF

						IF NOT IS_CHAR_DEAD mtbikeracer[a] 
							IF NOT LOCATE_CHAR_ANY_MEANS_2D mtbikeracer[a] stuck_x[a] stuck_y[a] 3.0 3.0 0
								GET_CHAR_COORDINATES mtbikeracer[a] stuck_x[a] stuck_y[a] z
								stuck_timer[a] = game_timer + 2000
							ENDIF
						ENDIF



						IF NOT IS_CHAR_DEAD mtbikeracer[a]
							IF NOT IS_CAR_DEAD racers_mtbike[a]

								IF has_mtbiker_fallen[a] = 0
									IF NOT IS_CHAR_IN_CAR mtbikeracer[a] racers_mtbike[a]
										has_mtbiker_fallen[a] = 1
									ENDIF
								ENDIF

								IF has_mtbiker_fallen[a] = 1
									IF IS_CHAR_IN_CAR mtbikeracer[a] racers_mtbike[a]
										CLEAR_CHAR_TASKS mtbikeracer[a] 
										TASK_CAR_DRIVE_TO_COORD mtbikeracer[a] -1 racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] set_opponents_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
										has_mtbiker_fallen[a] = 0
									ENDIF
								ENDIF
							ENDIF
						




										







							IF LOCATE_CHAR_ANY_MEANS_2D mtbikeracer[a] stuck_x[a] stuck_y[a] 3.0 3.0 0
								IF stuck_timer[a] < game_timer
									IF NOT IS_CHAR_ON_SCREEN mtbikeracer[a]
										
										IF NOT IS_CAR_DEAD racers_mtbike[a]

											IF NOT IS_CHAR_IN_CAR mtbikeracer[a] racers_mtbike[a]
												GET_DRIVER_OF_CAR racers_mtbike[a] char_person_on_bike
													IF char_person_on_bike = -1
														WARP_CHAR_INTO_CAR mtbikeracer[a] racers_mtbike[a]
													ELSE
														MARK_CAR_AS_NO_LONGER_NEEDED racers_mtbike[a]

														CREATE_CAR racers_car_model_mtbikerace[a] x y z racers_mtbike[a]
														WARP_CHAR_INTO_CAR mtbikeracer[a] racers_mtbike[a]
														TURN_CAR_TO_FACE_COORD racers_mtbike[a] racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] //racer_cp_z_mtbikerace[a]
														TASK_CAR_DRIVE_TO_COORD mtbikeracer[a] -1 racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] set_opponents_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
													ENDIF

															
											ENDIF
										ELSE

											DELETE_CAR racers_mtbike[a] 
											CREATE_CAR racers_car_model_mtbikerace[a] x y z racers_mtbike[a]
											WARP_CHAR_INTO_CAR mtbikeracer[a] racers_mtbike[a]
											TURN_CAR_TO_FACE_COORD racers_mtbike[a] racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] //racer_cp_z_mtbikerace[a]
											TASK_CAR_DRIVE_TO_COORD mtbikeracer[a] -1 racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] set_opponents_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS


										ENDIF

										
										
										GET_CHAR_COORDINATES mtbikeracer[a] x y z
										GET_CLOSEST_CAR_NODE x y z x y z
										IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY x y z 10.0 10.0 10.0
											IF NOT IS_POINT_ON_SCREEN x y z 4.0
												SET_CHAR_COORDINATES mtbikeracer[a] x y z
												LVAR_INT stored_bike_mtbikerace
												IF IS_CHAR_IN_ANY_CAR mtbikeracer[a]
													STORE_CAR_CHAR_IS_IN_NO_SAVE mtbikeracer[a] stored_bike_mtbikerace
													//TURN_CAR_TO_FACE_COORD stored_bike_mtbikerace racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] //racer_cp_z_mtbikerace[a]
													SET_CHAR_HEADING mtbikeracer[a] checkpoint_headings[a]

													TASK_CAR_DRIVE_TO_COORD mtbikeracer[a] -1 racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] set_opponents_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS

												ENDIF
											ENDIF
										ENDIF

									ENDIF
								ENDIF
							ENDIF
						ENDIF

						IF IS_CHAR_IN_ANY_CAR mtbikeracer[a]
							temp_int_mtbikerace = cpcounter_mtbikerace[playaz_mtbikerace] + 5
							STORE_CAR_CHAR_IS_IN_NO_SAVE mtbikeracer[a] stored_bike_mtbikerace
						// make opponents go faster if player is winning

						ENDIF
						
						r = 0
						e = 0
						WHILE r < total_mtbikerace
							LVAR_INT opponent_cpcounter_mtbikerace_minus4
							opponent_cpcounter_mtbikerace_minus4 = cpcounter_mtbikerace[r] - 3
							IF cpcounter_mtbikerace[a] < opponent_cpcounter_mtbikerace_minus4
								++ e
							ENDIF
							++ r
						ENDWHILE
						IF e > 0
							IF NOT  IS_CHAR_DEAD mtbikeracer[a]
								IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer mtbikeracer[a] 30.0 30.0 5.0 0
									IF NOT IS_CHAR_ON_SCREEN mtbikeracer[a]
										IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] 10.0 10.0 10.0
											IF NOT IS_POINT_ON_SCREEN racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] 4.0
												
												SET_CHAR_COORDINATES mtbikeracer[a] racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a]
												IF IS_CHAR_IN_ANY_CAR mtbikeracer[a]
													STORE_CAR_CHAR_IS_IN_NO_SAVE mtbikeracer[a] stored_bike_mtbikerace
												 //	TURN_CAR_TO_FACE_COORD stored_bike_mtbikerace racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] //racer_cp_z_mtbikerace[a]
													SET_CHAR_HEADING mtbikeracer[a] checkpoint_headings[a]
													TASK_CAR_DRIVE_TO_COORD mtbikeracer[a] -1 racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] set_opponents_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS

												ELSE

													CREATE_CAR racers_car_model_mtbikerace[a] racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] racers_mtbike[a]
													WARP_CHAR_INTO_CAR mtbikeracer[a] racers_mtbike[a]
												  //	TURN_CAR_TO_FACE_COORD racers_mtbike[a] racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] //racer_cp_z_mtbikerace[a]
													SET_CHAR_HEADING mtbikeracer[a] checkpoint_headings[a]

													TASK_CAR_DRIVE_TO_COORD mtbikeracer[a] -1 racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] set_opponents_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			   // check if player abandons race
				IF out_of_car_flag_mtbike = 0
					IF mtbikeracer[a] = scplayer
						IF NOT LOCATE_CHAR_ANY_MEANS_3D mtbikeracer[a] racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] 500.0 500.0 500.0 0

							PRINT_NOW RACES25 5000 1 //"~r~You failed to win the race!"
							GOTO mission_mtbikerace_failed	

						ENDIF
					ENDIF
				ENDIF

				
				IF mtbikerace_selection = 3
					IF cpcounter_mtbikerace[a] < 13
					AND cpcounter_mtbikerace[a] > 4
						IF LOCATE_CHAR_IN_CAR_3D mtbikeracer[a] racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] 4.0 4.0 1.5 0
							GOTO passed_a_checkpoint_mtbikerace
							

						ENDIF
					ELSE
						IF LOCATE_CHAR_IN_CAR_3D mtbikeracer[a] racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] 15.0 15.0 5.0 0
							GOTO passed_a_checkpoint_mtbikerace

						ENDIF
					ENDIF


				ELSE
					IF LOCATE_CHAR_IN_CAR_3D mtbikeracer[a] racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] 15.0 15.0 5.0 0
						GOTO passed_a_checkpoint_mtbikerace


					ENDIF
				ENDIF



			ELSE
				IF NOT mtbikeracer[a] = scplayer
					IF NOT IS_CHAR_IN_WATER mtbikeracer[a]
						cpcounter_mtbikerace[a] = 0
					ENDIF
				ENDIF
			ENDIF


	 
		exit_locate_loop_mtbikerace:



			IF mtbikeracer[a] = scplayer

 
		////////////////////////////////
		//OUT OF CAR TIMER FOR PLAYER
		////////////////////////////////

				IF NOT IS_CAR_DEAD racers_mtbike[playaz_mtbikerace]

					// CHECK IF PLAYER FALLS OFF MOUNTAIN
						GET_CHAR_COORDINATES mtbikeracer[playaz_mtbikerace] coord_racers_current_x[a] coord_racers_current_y[a] coord_racers_current_z[a]
						
						GET_CAR_COORDINATES racers_mtbike[playaz_mtbikerace] coord_racers_bikes_current_x[0] coord_racers_bikes_current_y[0] coord_racers_bikes_current_z[0]
					
						coord_racers_current_z[a] -= racer_cp_z_mtbikerace[a]
						coord_racers_bikes_current_z[0] -=racer_cp_z_mtbikerace[a]
						
						
						IF coord_racers_current_z[a] < -24.5
						OR coord_racers_bikes_current_z[0] < -24.5



					//	IF IS_CHAR_IN_AIR scplayer
							IF flag_is_player_falling = 0
								flag_is_player_falling = 1
						   //		LVAR_INT out_of_car_timer
								falling_timer = game_timer + 1500	// one and a half seconds if player is past the z buffer until hes warped
							ENDIF
							IF flag_is_player_falling = 1
								seconds = falling_timer - game_timer
								seconds /= 1000
								IF seconds < 1
									seconds = 0
								ENDIF
								IF falling_timer < game_timer
									last_check_point = cpcounter_mtbikerace[a] - 1

									SET_PLAYER_CONTROL Player1 OFF
									DO_FADE 500 FADE_OUT

									WHILE GET_FADING_STATUS
										WAIT 0
									ENDWHILE

									IF NOT IS_CAR_DEAD racers_mtbike[playaz_mtbikerace]
										IF NOT IS_CHAR_IN_CAR mtbikeracer[a] racers_mtbike[playaz_mtbikerace]
											WARP_CHAR_INTO_CAR mtbikeracer[a] racers_mtbike[playaz_mtbikerace]

										ENDIF
										SET_CAR_COORDINATES racers_mtbike[playaz_mtbikerace] checkpoints_mtbike_x[last_check_point] checkpoints_mtbike_y[last_check_point] checkpoints_mtbike_z[last_check_point]
										SET_CAR_HEADING racers_mtbike[playaz_mtbikerace] checkpoint_headings[last_check_point]
										//	SET_CAR_HEADING racers_mtbike[playaz_mtbikerace] new_heading_mtbike
										//	TURN_CAR_TO_FACE_COORD racers_mtbike[playaz_mtbikerace] racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a]
									ENDIF

								

									DO_FADE 500 FADE_IN
									SET_CAMERA_BEHIND_PLAYER
									RESTORE_CAMERA_JUMPCUT

									WHILE GET_FADING_STATUS
										WAIT 0
									ENDWHILE
									SET_CAMERA_BEHIND_PLAYER
									SET_PLAYER_CONTROL Player1 ON
								ENDIF
							ENDIF
						ELSE

							IF flag_is_player_falling = 1
								flag_is_player_falling = 0
							ENDIF

						ENDIF



					IF NOT IS_CAR_DEAD racers_mtbike[playaz_mtbikerace]
						IF NOT IS_CHAR_IN_CAR scplayer racers_mtbike[playaz_mtbikerace]
							IF out_of_car_flag_mtbike = 0
								DELETE_CHECKPOINT cp_marker_mtbikerace
								REMOVE_BLIP	first_blip_mtbikerace 

								IF NOT IS_CAR_DEAD racers_mtbike[playaz_mtbikerace]
							  		ADD_BLIP_FOR_CAR racers_mtbike[playaz_mtbikerace] first_blip_mtbikerace
									SET_BLIP_AS_FRIENDLY first_blip_mtbikerace TRUE
								ENDIF
								stored_checkpoint_when_not_on_bike = a

								racer_cp_y_mtbikerace[a] += 1000.0
		  
						   		out_of_car_timer = game_timer + 25400
								out_of_car_flag_mtbike = 1
							ENDIF

							seconds = out_of_car_timer - game_timer
							seconds /= 1000
							IF seconds < 1
								seconds = 0
							ENDIF
							IF out_of_car_timer < game_timer
								PRINT_NOW RACES20 5000 1//~r~You have been disqualified for leaving your car.
								GOTO mission_mtbikerace_failed
								RETURN
							ENDIF
							
							IF seconds = 1
								ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS FALSE

								PRINT_WITH_NUMBER_NOW RACE_51 seconds 200 1	//You have ~1~ second to return to your car.
							ELSE
								ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS FALSE

								PRINT_WITH_NUMBER_NOW RACES50 seconds 200 1	//You have ~1~ seconds to return to your car.
							ENDIF



							IF cpcounter_mtbikerace[a] = total_checkpoints_mtbike_mtbikerace
								IF mtbikeracer[a] = scplayer										 				
									IF mtbikerace_best_times[mtbikerace_selection] < game_timer
										mtbikerace_best_times[mtbikerace_selection] = game_timer 
										race_timer_temp = game_timer - race_timer
										mtbikerace_best_times[mtbikerace_selection] = game_timer
										REGISTER_FASTEST_TIME 12 mtbikerace_best_times[mtbikerace_selection]

										race_timer_temp /= 1000
										mins = race_timer_temp / 60
										temp_int_mtbikerace = mins * 60
										seconds = race_timer_temp - temp_int_mtbikerace
										// NEW best time
										IF seconds > 9
 											PRINT_WITH_2_NUMBERS_BIG LAPMTB2 mins seconds 5000 6 //"Time: ~1~:~1~"
										ELSE
											PRINT_WITH_2_NUMBERS_BIG LAPMTB mins seconds 5000 6 //"Time: ~1~:0~1~"
										ENDIF
							
										
										
									ELSE
										// CURRENT BEST race time
										race_timer_temp = mtbikerace_best_times[mtbikerace_selection] - race_timer

										race_timer_temp /= 1000
										mins = race_timer_temp / 60
										temp_int_mtbikerace = mins * 60
										seconds = race_timer_temp - temp_int_mtbikerace
										// best time
										IF seconds > 9
											PRINT_WITH_2_NUMBERS_BIG RACES08 mins seconds 5000 6 //"Time: ~1~:~1~"
										ELSE
											PRINT_WITH_2_NUMBERS_BIG RACES22 mins seconds 5000 6 //"Time: ~1~:0~1~"
										ENDIF


										// Your race time
										race_timer_temp = game_timer - race_timer
										race_timer_temp /= 1000
										mins = race_timer_temp / 60
										temp_int_mtbikerace = mins * 60
										seconds = race_timer_temp - temp_int_mtbikerace

										IF seconds > 9
											PRINT_WITH_2_NUMBERS_BIG RACE_52 mins seconds 5000 4 //"Time: ~1~:~1~"
										ELSE
											PRINT_WITH_2_NUMBERS_BIG RACE_53 mins seconds 5000 4 //"Time: ~1~:0~1~"
										ENDIF



									ENDIF	 


									position_mtbikerace++
									IF position_mtbikerace = 1
										GOTO mission_mtbikerace_passed
									ELSE
										GOTO mission_mtbikerace_failed	
									ENDIF

								ENDIF
							ENDIF
				 
						ELSE
							IF out_of_car_flag_mtbike = 1
								CLEAR_THIS_PRINT RACES50
							   	REMOVE_BLIP	first_blip_mtbikerace
								racer_cp_y_mtbikerace[a] -= 1000.0
								c = 0
								temp_int_mtbikerace = total_racers_mtbikerace - 1
								ADD_BLIP_FOR_COORD racer_cp_x_mtbikerace[stored_checkpoint_when_not_on_bike] racer_cp_y_mtbikerace[stored_checkpoint_when_not_on_bike] racer_cp_z_mtbikerace[stored_checkpoint_when_not_on_bike] first_blip_mtbikerace
								CHANGE_BLIP_COLOUR first_blip_mtbikerace RED
								CHANGE_BLIP_DISPLAY first_blip_mtbikerace BLIP_ONLY
								CHANGE_BLIP_SCALE first_blip_mtbikerace 3
								IF cpcounter_mtbikerace[stored_checkpoint_when_not_on_bike] = total_checkpoints_mtbike_mtbikerace_minus_1
							   //	AND lap_cpcounter[a] = total_laps_minus_1
									CREATE_CHECKPOINT CHECKPOINT_ENDTUBE racer_cp_x_mtbikerace[stored_checkpoint_when_not_on_bike] racer_cp_y_mtbikerace[stored_checkpoint_when_not_on_bike] racer_cp_z_mtbikerace[stored_checkpoint_when_not_on_bike] 0.0 0.0 0.0 6.0 cp_marker_mtbikerace
								ELSE
									temp_int_mtbikerace = cpcounter_mtbikerace[stored_checkpoint_when_not_on_bike]
									++ temp_int_mtbikerace
									
									IF cpcounter_mtbikerace[stored_checkpoint_when_not_on_bike] = total_checkpoints_mtbike_mtbikerace_minus_1
										temp_int_mtbikerace = 1
									ENDIF

									CREATE_CHECKPOINT CHECKPOINT_TUBE racer_cp_x_mtbikerace[stored_checkpoint_when_not_on_bike] racer_cp_y_mtbikerace[stored_checkpoint_when_not_on_bike] racer_cp_z_mtbikerace[stored_checkpoint_when_not_on_bike] second_cp_mtbikeracex second_cp_mtbikeracey second_cp_mtbikeracez 6.0 cp_marker_mtbikerace
								ENDIF
								out_of_car_flag_mtbike = 0
							ENDIF

						ENDIF
					ENDIF

				ELSE // if car is dead

					PRINT_NOW RACES24 5000 1//~r~You wrecked yer bike
						GOTO mission_mtbikerace_failed
					RETURN


				ENDIF



			ELSE // warpinbg opponents back tot he previous check point if they fall off cliff
				IF NOT IS_CHAR_DEAD	mtbikeracer[a]
					IF NOT IS_CAR_DEAD racers_mtbike[a]

						GET_CHAR_COORDINATES mtbikeracer[a] coord_racers_current_x[a] coord_racers_current_y[a] coord_racers_current_z[a]
						coord_racers_current_z[a] -= racer_cp_z_mtbikerace[a]
						IF coord_racers_current_z[a] < -15.5
							IF NOT IS_POINT_ON_SCREEN checkpoints_mtbike_x[last_check_point] checkpoints_mtbike_y[last_check_point] checkpoints_mtbike_z[last_check_point] 10.0

								last_check_point = cpcounter_mtbikerace[a] - 1
								IF IS_CHAR_IN_CAR mtbikeracer[a] racers_mtbike[a]
									SET_CAR_COORDINATES racers_mtbike[a] checkpoints_mtbike_x[last_check_point] checkpoints_mtbike_y[last_check_point] checkpoints_mtbike_z[last_check_point]
									SET_CAR_HEADING racers_mtbike[a] checkpoint_headings[a]

								ELSE

									GET_DRIVER_OF_CAR racers_mtbike[a] char_person_on_bike
									IF char_person_on_bike = -1
										WARP_CHAR_INTO_CAR mtbikeracer[a] racers_mtbike[a]
										SET_CAR_COORDINATES racers_mtbike[a] checkpoints_mtbike_x[last_check_point] checkpoints_mtbike_y[last_check_point] checkpoints_mtbike_z[last_check_point]
									ELSE
										MARK_CAR_AS_NO_LONGER_NEEDED racers_mtbike[a]
										CREATE_CAR racers_car_model_mtbikerace[a]  checkpoints_mtbike_x[last_check_point] checkpoints_mtbike_y[last_check_point] checkpoints_mtbike_z[last_check_point] racers_mtbike[a]
										WARP_CHAR_INTO_CAR mtbikeracer[a] racers_mtbike[a]
									   //	SET_CAR_HEADING racers_mtbike[a] checkpoint_headings[last_check_point]
									  //	TURN_CAR_TO_FACE_COORD racers_mtbike[a] racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] //racer_cp_z_mtbikerace[a]
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF

			ENDIF // is racer is player

			////////////////////////////////////////////////////////


		ENDIF
		++ a
	ENDWHILE

	DRAW_RECT x_pos[6] y_pos[6] x_scale[6] y_scale[6] 0 0 0 255 
	DRAW_RECT x_pos[5] y_pos[5] x_scale[5] y_scale[5] 134 155 184 255 
	DRAW_RECT x_pos[0] y_pos[0] x_scale[0] y_scale[0] 0 0 0 255 
	++ position_mtbikerace
	GOSUB mtbikerace_text
	SET_TEXT_SCALE x_scale[1] y_scale[1]

	IF position_mtbikerace = 1 
		DISPLAY_TEXT x_pos[1] y_pos[1] ST
	ENDIF

	IF position_mtbikerace = 2 
		DISPLAY_TEXT x_pos[1] y_pos[1] ND
	ENDIF

	IF position_mtbikerace = 3 
		DISPLAY_TEXT x_pos[1] y_pos[1] RD
	ENDIF

	IF position_mtbikerace > 3 
		DISPLAY_TEXT x_pos[1] y_pos[1] TH
	ENDIF


	IF position_mtbikerace <= 2
		set_opponents_speed = opponents_speed_fast 	

		IF mtbikerace_selection = 1
			IF cpcounter_mtbikerace[a] = 17
			OR cpcounter_mtbikerace[a] = 16
				set_opponents_speed = opponents_speed_really_slow 
			ENDIF
		ENDIF 
	ENDIF

	IF position_mtbikerace > 2
	AND position_mtbikerace < 4 

	    set_opponents_speed = opponents_speed_slow
		
		IF mtbikerace_selection = 1
			IF cpcounter_mtbikerace[a] = 17	  
			OR cpcounter_mtbikerace[a] = 16
				set_opponents_speed = opponents_speed_really_slow 
			ENDIF
		ENDIF 
	ENDIF


	IF position_mtbikerace = 6
	    set_opponents_speed = opponents_speed_really_slow
	ENDIF
	
	
	// Rendering the interface.

	GOSUB mtbikerace_text
	SET_TEXT_SCALE x_scale[2] y_scale[2]
	SET_TEXT_CENTRE ON 
	DISPLAY_TEXT_WITH_NUMBER x_pos[2] y_pos[2] NUMBER position_mtbikerace
	GOSUB mtbikerace_text 
	SET_TEXT_SCALE x_scale[3] y_scale[3]
	DISPLAY_TEXT_WITH_NUMBER x_pos[3] y_pos[3] OUT_OF total_racers_mtbikerace
	GET_GAME_TIMER game_timer

	LVAR_INT race_timer_temp
	race_timer_temp = game_timer - race_timer
	race_timer_temp /= 1000
	
	mins = race_timer_temp / 60
	temp_int_mtbikerace = mins * 60
	seconds = race_timer_temp - temp_int_mtbikerace

	GOSUB mtbikerace_text
	SET_TEXT_CENTRE ON 
	SET_TEXT_SCALE x_scale[4] y_scale[4]
	IF seconds > 9
		DISPLAY_TEXT_WITH_2_NUMBERS x_pos[4] y_pos[4] TIME mins seconds
	ELSE
		DISPLAY_TEXT_WITH_2_NUMBERS x_pos[4] y_pos[4] TIME_0 mins seconds
	ENDIF

ENDIF

GOTO mission_mtbikerace_loop


/*

// CUTSCENE

LVAR_FLOAT coord_1st_place_position_x coord_1st_place_position_y coord_1st_place_position_z 
LVAR_FLOAT coord_2nd_place_position_x coord_2nd_place_position_y coord_2nd_place_position_z 
LVAR_FLOAT coord_3rd_place_position_x coord_3rd_place_position_y coord_3rd_place_position_z   

LVAR_FLOAT coord_players_position_x coord_players_position_y coord_players_position_z 

LVAR_INT flag_cutscene_mtbikerace

award_ceremony_mtbike_race:	

	coord_1st_place_position_x = -2404.1289 
	coord_1st_place_position_y = -2206.4763  
	coord_1st_place_position_z = 33.4909

	coord_2nd_place_position_x = -2404.1821  
	coord_2nd_place_position_y = -2205.1987 
	coord_2nd_place_position_z = 33.4909

	coord_3rd_place_position_x = -2403.8489 
	coord_3rd_place_position_y = -2208.4829 
	coord_3rd_place_position_z = 33.4909


	flag_cutscene_mtbikerace = 0

	
	IF position_mtbikerace = 1
		coord_players_position_x = coord_1st_place_position_x  
		coord_players_position_y = coord_1st_place_position_y 
		coord_players_position_z = coord_1st_place_position_z 
	ENDIF

	IF position_mtbikerace = 2
		coord_players_position_x = coord_2nd_place_position_x  
		coord_players_position_y = coord_2nd_place_position_y 
		coord_players_position_z = coord_2nd_place_position_z 
	ENDIF


	IF position_mtbikerace = 3
		coord_players_position_x = coord_3rd_place_position_x  
		coord_players_position_y = coord_3rd_place_position_y 
		coord_players_position_z = coord_3rd_place_position_z 
	ENDIF

	IF position_mtbikerace > 3 // Player in audiance

		coord_players_position_x = -2404.3813 
		coord_players_position_y = -2212.2981 
		coord_players_position_z =  32.3042 
	ENDIF


	IF IS_CHAR_IN_ANY_CAR scplayer
		WARP_CHAR_FROM_CAR_TO_COORD scplayer coord_players_position_x coord_players_position_y coord_players_position_z
	ELSE
		SET_CHAR_COORDINATES scplayer coord_players_position_x coord_players_position_y coord_players_position_z
	ENDIF

	SET_CHAR_HEADING scplayer 270.0 

	TIMERA = 0
	TIMERB = 0
	
	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON  

	WHILE NOT flag_cutscene_mtbikerace = 10
		WAIT 0

		IF flag_cutscene_mtbikerace = 0
			SET_FIXED_CAMERA_POSITION -2399.4277 -2207.4241 32.9310 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2401.7283 -2207.2827 33.8958 JUMP_CUT 

			flag_cutscene_mtbikerace = 1

		ENDIF

	
		// SAFETY
		IF TIMERA > 5000
			flag_cutscene_mtbikerace = 10
		ENDIF 

	ENDWHILE






	IF NOT IS_CHAR_DEAD scplayer
		SET_PLAYER_CONTROL player1 ON
		SWITCH_WIDESCREEN OFF
		RESTORE_CAMERA_JUMPCUT
		SET_CAMERA_BEHIND_PLAYER
	ENDIF

RETURN	*/

passed_a_checkpoint_mtbikerace:

	IF race_flag_mtbikerace = 7
		race_flag_mtbikerace = 8
	ENDIF
					
	IF mtbikeracer[a] = scplayer
		ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
		REMOVE_BLIP first_blip_mtbikerace
	ENDIF
		
	++ cpcounter_mtbikerace[a]
	
	IF cpcounter_mtbikerace[a] = total_checkpoints_mtbike_mtbikerace

	 //	GOSUB award_ceremony_mtbike_race
		
   		IF mtbikeracer[a] = scplayer

			IF mtbikerace_best_times[mtbikerace_selection] < game_timer
			   // new fastest time!

				mtbikerace_best_times[mtbikerace_selection] = game_timer 
				race_timer_temp = game_timer - race_timer
				mtbikerace_best_times[mtbikerace_selection] = game_timer
				REGISTER_FASTEST_TIME 12 mtbikerace_best_times[mtbikerace_selection]

				race_timer_temp /= 1000
				mins = race_timer_temp / 60
				temp_int_mtbikerace = mins * 60
				seconds = race_timer_temp - temp_int_mtbikerace

				IF seconds > 9
			  //		PRINT_WITH_2_NUMBERS_NOW RACES08 mins seconds 5000 6 //"Time: ~1~:~1~"
					PRINT_WITH_2_NUMBERS_BIG LAPMTB2 mins seconds 5000 6 //"Time: ~1~:~1~"
				ELSE
				   //	PRINT_WITH_2_NUMBERS_NOW RACES22 mins seconds 5000 6 //"Time: ~1~:0~1~"
					PRINT_WITH_2_NUMBERS_BIG LAPMTB mins seconds 5000 6 //"Time: ~1~:0~1~"
				ENDIF
	
				
				
			ELSE

				// no records broken

				race_timer_temp = mtbikerace_best_times[mtbikerace_selection] - race_timer

			 //	REGISTER_FASTEST_TIME 12 mtbike_timer

				race_timer_temp /= 1000
				mins = race_timer_temp / 60
				temp_int_mtbikerace = mins * 60
				seconds = race_timer_temp - temp_int_mtbikerace

				IF seconds > 9
			  //		PRINT_WITH_2_NUMBERS_NOW RACES08 mins seconds 5000 6 //"Time: ~1~:~1~"
					PRINT_WITH_2_NUMBERS_BIG RACES08 mins seconds 5000 6 //"Time: ~1~:~1~"
				ELSE
				   //	PRINT_WITH_2_NUMBERS_NOW RACES22 mins seconds 5000 6 //"Time: ~1~:0~1~"
					PRINT_WITH_2_NUMBERS_BIG RACES22 mins seconds 5000 6 //"Time: ~1~:~1~"
				ENDIF


				race_timer_temp = game_timer - race_timer
				race_timer_temp /= 1000
				mins = race_timer_temp / 60
				temp_int_mtbikerace = mins * 60
				seconds = race_timer_temp - temp_int_mtbikerace

				IF seconds > 9
			  //		PRINT_WITH_2_NUMBERS_NOW RACES08 mins seconds 5000 6 //"Time: ~1~:~1~"
					PRINT_WITH_2_NUMBERS_BIG TIME mins seconds 5000 4 //"Time: ~1~:~1~"
				ELSE
				   //	PRINT_WITH_2_NUMBERS_NOW RACES22 mins seconds 5000 6 //"Time: ~1~:0~1~"
					PRINT_WITH_2_NUMBERS_BIG TIME_0 mins seconds 5000 4 //"Time: ~1~:~1~"
				ENDIF


			ENDIF	

			position_mtbikerace++
			IF position_mtbikerace = 1
				GOTO mission_mtbikerace_passed
			ELSE
				GOTO mission_mtbikerace_failed	
			ENDIF

		ELSE
			TASK_CAR_TEMP_ACTION mtbikeracer[a] -1 TEMPACT_HANDBRAKESTRAIGHT 2000000
			last_check_point = cpcounter_mtbikerace[a] - 1
			IF NOT IS_POINT_ON_SCREEN racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] 30.0
				IF NOT IS_CAR_DEAD stored_bike_mtbikerace
					TURN_CAR_TO_FACE_COORD stored_bike_mtbikerace checkpoints_mtbike_x[last_check_point] checkpoints_mtbike_y[last_check_point]// MUST PLACE checkpoint[1] STRAIGHT AHEAD FROM START
				ENDIF
			ENDIF


		ENDIF	 
	ENDIF
	
	temp_int_mtbikerace = cpcounter_mtbikerace[a]
	
	racer_cp_x_mtbikerace[a] = checkpoints_mtbike_x[temp_int_mtbikerace]
	racer_cp_y_mtbikerace[a] = checkpoints_mtbike_y[temp_int_mtbikerace]
	racer_cp_z_mtbikerace[a] = checkpoints_mtbike_z[temp_int_mtbikerace]
	
	IF mtbikeracer[a] = scplayer
		
		++ temp_int_mtbikerace
		
		LVAR_FLOAT second_cp_mtbikeracex second_cp_mtbikeracey second_cp_mtbikeracez
		second_cp_mtbikeracex = checkpoints_mtbike_x[temp_int_mtbikerace]
		second_cp_mtbikeracey = checkpoints_mtbike_y[temp_int_mtbikerace]
		second_cp_mtbikeracez = checkpoints_mtbike_z[temp_int_mtbikerace]
		
		ADD_BLIP_FOR_COORD racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] first_blip_mtbikerace
		CHANGE_BLIP_COLOUR first_blip_mtbikerace RED
		CHANGE_BLIP_DISPLAY first_blip_mtbikerace BLIP_ONLY
		CHANGE_BLIP_SCALE first_blip_mtbikerace 3
		IF race_flag_mtbikerace = 1 //MAKE THE SCRIPT COMPILE
			CREATE_CHECKPOINT CHECKPOINT_TUBE racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] second_cp_mtbikeracex second_cp_mtbikeracey second_cp_mtbikeracez 6.0 cp_marker_mtbikerace
		ENDIF
		DELETE_CHECKPOINT cp_marker_mtbikerace

		IF cpcounter_mtbikerace[a] < total_checkpoints_mtbike_mtbikerace_minus_1
			LVAR_INT cp_marker_mtbikerace
			CREATE_CHECKPOINT CHECKPOINT_TUBE racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] second_cp_mtbikeracex second_cp_mtbikeracey second_cp_mtbikeracez 6.0 cp_marker_mtbikerace
		ELSE
			CREATE_CHECKPOINT CHECKPOINT_ENDTUBE racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] second_cp_mtbikeracex second_cp_mtbikeracey second_cp_mtbikeracez 6.0 cp_marker_mtbikerace
		ENDIF

	
		IF cpcounter_mtbikerace[a] = 3
			MARK_CHAR_AS_NO_LONGER_NEEDED flag_girl_mtbikerace
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[0]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[1]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[2]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[3]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[4]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[5]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[6]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[7]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[8]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[9]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[10]
		ENDIF
	ELSE
		
		IF mtbikerace_selection = 3
			IF NOT IS_CAR_DEAD racers_mtbike[a]

				IF cpcounter_mtbikerace[a] = 1
					IF a = 0	
						
						GENERATE_RANDOM_INT_IN_RANGE 832 835 random_number_mtbike
					  	START_PLAYBACK_RECORDED_CAR racers_mtbike[a] random_number_mtbike
						SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE mtbikeracer[a] KNOCKOFFBIKE_NEVER 
					ENDIF
					IF a = 1
						GENERATE_RANDOM_INT_IN_RANGE 829 832 random_number_mtbike	
					  	START_PLAYBACK_RECORDED_CAR racers_mtbike[a] random_number_mtbike
						SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE mtbikeracer[a] KNOCKOFFBIKE_NEVER 
					ENDIF
				ENDIF





				IF cpcounter_mtbikerace[a] = 13 
					IS_PLAYBACK_GOING_ON_FOR_CAR racers_mtbike[a]
						STOP_PLAYBACK_RECORDED_CAR racers_mtbike[a]
					  //	REMOVE_CAR_RECORDING 832
						IF NOT cpcounter_mtbikerace[a] = total_checkpoints_mtbike_mtbikerace
							SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE mtbikeracer[a] KNOCKOFFBIKE_DEFAULT
					  		TASK_CAR_DRIVE_TO_COORD mtbikeracer[a] -1 racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] set_opponents_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
							GOTO exit_locate_loop_mtbikerace

						ENDIF
				ENDIF

				IF cpcounter_mtbikerace[a] > 13 
					IF NOT cpcounter_mtbikerace[a] = total_checkpoints_mtbike_mtbikerace
					  	TASK_CAR_DRIVE_TO_COORD mtbikeracer[a] -1 racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] set_opponents_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
						GOTO exit_locate_loop_mtbikerace

					ENDIF
				ENDIF

			ENDIF
		ELSE
			IF NOT cpcounter_mtbikerace[a] = total_checkpoints_mtbike_mtbikerace
			  	TASK_CAR_DRIVE_TO_COORD mtbikeracer[a] -1 racer_cp_x_mtbikerace[a] racer_cp_y_mtbikerace[a] racer_cp_z_mtbikerace[a] set_opponents_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
				GOTO exit_locate_loop_mtbikerace

			ENDIF



			IF cpcounter_mtbikerace[a] >= total_checkpoints_mtbike_mtbikerace
				IF NOT IS_CAR_DEAD racers_mtbike[a]
					IF NOT IS_CHAR_DEAD mtbikeracer[a]
						IF IS_CHAR_IN_CAR mtbikeracer[a] racers_mtbike[a]
							IF flag_make_char_go_past_final_checkpoint[a] = 0
								CLEAR_CHAR_TASKS mtbikeracer[a]
								flag_make_char_go_past_final_checkpoint[a] = 1
								TASK_CAR_DRIVE_TO_COORD mtbikeracer[a] -1 -2399.8628 -2206.5488 32.5652 set_opponents_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			


		ENDIF
	  
	ENDIF

GOTO exit_locate_loop_mtbikerace

mtbikerace_text:
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

// Mission mtbikerace failed

mission_mtbikerace_failed:
    PRINT_BIG RACES_8 5000 1  // Loser!
RETURN

   

// mission mtbikerace passed

mission_mtbikerace_passed:

	e =	mtbikerace_selection + 1
	e *= 10000
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1



	IF mtbikerace_selection = 1
        PRINT_BIG RACES18 500 1  // Winner!
		ADD_SCORE player1 500
		PRINT_NOW MTWIN1 5000 1	 // You won! The 'Birdseye Winder' is now available 
		PLAY_MISSION_PASSED_TUNE 1
	ENDIF

	IF mtbikerace_selection = 2
		PRINT_NOW MTWIN2 5000 1	  // You won! The 'Cobra Run' is now available 
		PRINT_BIG RACES18 1000 1  // Winner!
		ADD_SCORE player1 1000
	  	PLAY_MISSION_PASSED_TUNE 1
	ENDIF


	IF mtbikerace_selection = 3

		PRINT_BIG RACES18 2000 1  // Winner!
		ADD_SCORE player1 2000
	  	PRINT_NOW MTWIN3 5000 1	  // You have completed all routes for 'The Chiliad Challange'
	    PLAY_MISSION_PASSED_TUNE 2	
	ENDIF

	//PLAY_MISSION_PASSED_TUNE 2

	++ mtbikerace_selection
	IF mtbikerace_selection > 3
		IF flag_mtbike_passed_1stime = 0
			REGISTER_ODDJOB_MISSION_PASSED
		    PLAYER_MADE_PROGRESS 1
		    flag_mtbike_passed_1stime = 1
		ENDIF
		mtbikerace_selection = 1 //reset the race
	ENDIF








RETURN
		


// mission cleanup

mission_cleanup_mtbikerace:
	flag_player_on_mission = 0 




	IF NOT IS_CAR_DEAD racers_mtbike[0]
		IF IS_RECORDING_GOING_ON_FOR_CAR racers_mtbike[0]
			STOP_PLAYBACK_RECORDED_CAR racers_mtbike[0]
		ENDIF
	ENDIF

	IF NOT IS_CAR_DEAD racers_mtbike[1]
		IF IS_RECORDING_GOING_ON_FOR_CAR racers_mtbike[1]
			STOP_PLAYBACK_RECORDED_CAR racers_mtbike[1]
		ENDIF
	ENDIF


 /* 	IF NOT IS_CAR_DEAD racers_mtbike[0]
		IF NOT IS_CHAR_DEAD mtbikeracer[0]
			IF IS_CHAR_IN_CAR mtbikeracer[0] racers_mtbike[0]

 
				CLEAR_CHAR_TASKS mtbikeracer[0]

				TASK_CAR_DRIVE_TO_COORD mtbikeracer[0] -1 -2399.8628 -2206.5488 32.5652 set_opponents_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
			ENDIF
		ENDIF
	ENDIF

	IF NOT IS_CAR_DEAD racers_mtbike[1]
		IF NOT IS_CHAR_DEAD mtbikeracer[1]
			IF IS_CHAR_IN_CAR mtbikeracer[1] racers_mtbike[1]

				CLEAR_CHAR_TASKS mtbikeracer[1]

			
				TASK_CAR_DRIVE_TO_COORD mtbikeracer[1] -1 -2399.8628 -2206.5488 32.5652 set_opponents_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
			ENDIF
		ENDIF
	ENDIF

	IF NOT IS_CAR_DEAD racers_mtbike[2]
		IF NOT IS_CHAR_DEAD mtbikeracer[2]
			IF IS_CHAR_IN_CAR mtbikeracer[2] racers_mtbike[2]

				CLEAR_CHAR_TASKS mtbikeracer[2]
				TASK_CAR_DRIVE_TO_COORD mtbikeracer[2] -1 -2399.8628 -2206.5488 32.5652 set_opponents_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
			ENDIF
		ENDIF
	ENDIF

	IF NOT IS_CAR_DEAD racers_mtbike[3]
		IF NOT IS_CHAR_DEAD mtbikeracer[3]
			IF IS_CHAR_IN_CAR mtbikeracer[3] racers_mtbike[3]

				CLEAR_CHAR_TASKS mtbikeracer[3]

				TASK_CAR_DRIVE_TO_COORD mtbikeracer[3] -1 -2399.8628 -2206.5488 32.5652 set_opponents_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
			ENDIF
		ENDIF
	ENDIF

	IF NOT IS_CAR_DEAD racers_mtbike[4]
		IF NOT IS_CHAR_DEAD mtbikeracer[4]
			IF IS_CHAR_IN_CAR mtbikeracer[4] racers_mtbike[4]

				CLEAR_CHAR_TASKS mtbikeracer[4]

				TASK_CAR_DRIVE_TO_COORD mtbikeracer[4] -1 -2399.8628 -2206.5488 32.5652 set_opponents_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
			ENDIF
		ENDIF
	ENDIF

	IF NOT IS_CAR_DEAD racers_mtbike[5]
		IF NOT IS_CHAR_DEAD mtbikeracer[5]
			IF IS_CHAR_IN_CAR mtbikeracer[5] racers_mtbike[5]

				CLEAR_CHAR_TASKS mtbikeracer[5]
				TASK_CAR_DRIVE_TO_COORD mtbikeracer[5] -1 -2399.8628 -2206.5488 32.5652 set_opponents_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
			ENDIF
		ENDIF
	ENDIF			   




	*/




	REMOVE_CAR_RECORDING 829
	REMOVE_CAR_RECORDING 830
	REMOVE_CAR_RECORDING 831
	REMOVE_CAR_RECORDING 832
	REMOVE_CAR_RECORDING 833
	REMOVE_CAR_RECORDING 834




	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_mtbikerace[0]
	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_mtbikerace[1]
	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_mtbikerace[2]
	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_mtbikerace[3]
	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_mtbikerace[4]
	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_mtbikerace[5]
	MARK_MODEL_AS_NO_LONGER_NEEDED racers_car_model_mtbikerace[0]
	MARK_MODEL_AS_NO_LONGER_NEEDED racers_car_model_mtbikerace[1]
	MARK_MODEL_AS_NO_LONGER_NEEDED racers_car_model_mtbikerace[2]
	MARK_MODEL_AS_NO_LONGER_NEEDED racers_car_model_mtbikerace[3]
	MARK_MODEL_AS_NO_LONGER_NEEDED racers_car_model_mtbikerace[4]
	MARK_MODEL_AS_NO_LONGER_NEEDED racers_car_model_mtbikerace[5]


	MARK_CHAR_AS_NO_LONGER_NEEDED flag_girl_mtbikerace
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[0]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[1]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[2]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[3]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[4]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[5]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[6]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[7]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[8]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[9]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_mtbikerace[10]
	/*
    a = 0
	WHILE total_racers_mtbikerace > a
		WAIT 0
		MARK_CAR_AS_NO_LONGER_NEEDED racers_mtbike[a]
		MARK_CHAR_AS_NO_LONGER_NEEDED mtbikeracer[a]
		a++
	ENDWHILE

		*/




	DISPLAY_CAR_NAMES TRUE
	//CLEAR_ONSCREEN_TIMER mtbike_timer

	REMOVE_BLIP	first_blip_mtbikerace
	/*REMOVE_BLIP	racer_blip_mtbikerace[0]
	REMOVE_BLIP	racer_blip_mtbikerace[1]
	REMOVE_BLIP	racer_blip_mtbikerace[2]
	REMOVE_BLIP	racer_blip_mtbikerace[3]
	REMOVE_BLIP	racer_blip_mtbikerace[4]
	REMOVE_BLIP	racer_blip_mtbikerace[5]	   */

	// switching ON the roads at the mountain

   //	SWITCH_ROADS_ON -2999.5276 -2629.4624 0.8299 -1612.5211 -979.4300  480.2183 
	GET_GAME_TIMER timer_mobile_start

	DISABLE_ALL_ENTRY_EXITS FALSE


	//SET_CAR_DENSITY_MULTIPLIER 1.0
	//SET_PED_DENSITY_MULTIPLIER 1.0 

	SET_MESSAGE_FORMATTING FALSE 380 464

	USE_TEXT_COMMANDS FALSE
	MISSION_HAS_FINISHED
RETURN


}

//INTRO TEXT FOR POSITION + TIMERS
//SPECIAL EVENTS - VEHICLE OR ACCIDENT . ROADWORKS JUMP . BREAKFAST . DRIVEBY . NOS . STINGER
//CUTSCENES ?
//FLAG WAVER
//CLEANUP
//PED MODELS
//SCPECTATOR PED SEQUENCES


