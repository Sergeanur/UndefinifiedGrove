MISSION_START

// *******************************************************************************************
// ********************************** triathalon Race *************************************
// *******************************************************************************************

// Mission start stuff

GOSUB mission_start_triathalon

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_triathalon_failed
ENDIF

GOSUB mission_cleanup_triathalon

MISSION_END
{

// ***************************************Mission Start*************************************


// TASK_SWIM_TO_COORD CharID FloatX FloatY FloatZ

mission_start_triathalon:
CLEAR_THIS_PRINT_BIG_NOW 1

flag_player_on_mission = 1

IF flag_triathalon_passed_1stime = 0
	REGISTER_MISSION_GIVEN
ENDIF



SCRIPT_NAME TRIA
LOAD_MISSION_TEXT RACETOR
SET_MESSAGE_FORMATTING TRUE 355 370



//LOAD_MISSION_TEXT triathalon 

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

//LVAR_INT triathalon_selection
//triathalon_selection =2
//////////////////////////////////////////////





//initialise screen coordinates for the text
LVAR_FLOAT x_pos[12] y_pos[12] x_scale[12] y_scale[12] stuck_x[12] stuck_y[12] stuck_z[12]

LVAR_INT total_racers_mtbike stuck_timer[12] 
LVAR_INT flag_create_bike_and_make_opponent_get_on_it[12]
LVAR_FLOAT checkpoints_triathalon_x[120] checkpoints_triathalon_y[120] checkpoints_triathalon_z[120] checkpoint_headings[120]

LVAR_FLOAT coord_racers_current_x[12] coord_racers_current_y[12] coord_racers_current_z[12]
//LVAR_FLOAT coord_racers_bikes_current_x[10] coord_racers_bikes_current_y[10] 
LVAR_FLOAT coord_racers_bikes_current_x[12] coord_racers_bikes_current_y[12] coord_racers_bikes_current_z[12]


LVAR_FLOAT offset_swimming_opponents_x[12] offset_swimming_opponents_y[12] offset_swimming_opponents_z[12]

LVAR_INT task_status_triathlon[12] 

LVAR_FLOAT point_after_finish_line_x point_after_finish_line_y point_after_finish_line_z 
LVAR_INT flag_make_runner_get_past_finishingline[12]


LVAR_INT r a c e  





LVAR_INT temp_int_triathalon mins seconds temp_int2_tri

LVAR_FLOAT new_heading_triathalon 


LVAR_INT  race_timer   stored_bike_triathalon	 stored_bike_triathalon2

LVAR_FLOAT opponents_cycle_speed_fast opponents_cycle_speed_slow opponents_cycle_speed_really_slow 

LVAR_FLOAT opponents_swim_speed_fast opponents_swim_speed_slow opponents_swim_speed_really_slow

LVAR_FLOAT opponents_run_speed_fast opponents_run_speed_slow opponents_run_speed_really_slow


LVAR_FLOAT set_opponents_cycle_speed set_opponents_swim_speed set_opponents_run_speed

LVAR_INT out_of_car_flag_triathalon flag_is_player_falling last_check_point 


LVAR_FLOAT x2 y2 z2 temp_float heading_triathalon player_distance_from_cp heading_triathalon2
LVAR_FLOAT coord_from_swim_to_bike_x coord_from_swim_to_bike_y coord_from_swim_to_bike_z 


LVAR_FLOAT temp_x temp_y temp_z

LVAR_INT char_person_on_bike stored_checkpoint_when_not_on_bike

LVAR_INT out_of_car_timer falling_timer

LVAR_INT flag_boosting_opponents_speed

LVAR_INT seq_get_on_bike
LVAR_INT flag_make_guys_enter_bike[12]

LVAR_INT dm_racers_triathalon

LVAR_INT flag_help_text_triath

LVAR_INT triathalon_best_time

LVAR_INT get_in_water start_swim end_swim get_on_bike start_bike end_bike get_on_foot start_run end_run


LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY dm_racers_triathalon


//VAR_INT flag_triathalon_passed_1stime
					


flag_boosting_opponents_speed = 0 

flag_help_text_triath = 0 



// Interface Stuff

out_of_car_flag_triathalon = 0
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



racer_model_triathalon[0] = wmybe
racer_model_triathalon[1] = bmybe
racer_model_triathalon[2] = hmybe
racer_model_triathalon[3] = bmybe
racer_model_triathalon[4] = hmybe
racer_model_triathalon[5] = wmybe
racer_model_triathalon[6] = wmybe
racer_model_triathalon[7] = wmybell
racer_model_triathalon[8] = wmybell
racer_model_triathalon[9] = wmybell
racer_model_triathalon[10] = wmybell
racer_model_triathalon[11] = wmybell





racers_car_model_triathalon[0] = mtbike
racers_car_model_triathalon[1] = mtbike
racers_car_model_triathalon[2] = mtbike
racers_car_model_triathalon[3] = mtbike
racers_car_model_triathalon[4] = mtbike
racers_car_model_triathalon[5] = mtbike
racers_car_model_triathalon[6] = mtbike
racers_car_model_triathalon[7] = mtbike
racers_car_model_triathalon[8] = mtbike
racers_car_model_triathalon[9] = mtbike
racers_car_model_triathalon[10] = mtbike
racers_car_model_triathalon[11] = mtbike









LVAR_INT total_checkpoints_triathalon_triathalon

LVAR_INT racer_model_triathalon[12] racers_car_model_triathalon[12]

//////////////////////////////////////////////////


IF triathalon_selection = 1
	GOTO triathalon1
ENDIF

IF triathalon_selection = 2
	GOTO triathalon2
ENDIF

IF triathalon_selection = 100000
	racer_model_triathalon[0] = wmybe
	racer_model_triathalon[1] = bmybe
	racer_model_triathalon[2] = hmybe
	racer_model_triathalon[3] = bmybe
	racer_model_triathalon[4] = hmybe
	racer_model_triathalon[5] = wmybe
	racer_model_triathalon[6] = wmybe
	racer_model_triathalon[7] = wmybell
	racer_model_triathalon[11] = wmybell

	flag_help_text_triath = 0
	flag_help_text_triath = 0
	flag_help_text_triath = 0

	racers_car_model_triathalon[1] = mtbike
	racers_car_model_triathalon[2] = mtbike
	racers_car_model_triathalon[3] = mtbike
	racers_car_model_triathalon[2] = mtbike
	racers_car_model_triathalon[3] = mtbike
	racers_car_model_triathalon[2] = mtbike
	racers_car_model_triathalon[3] = mtbike
	racers_car_model_triathalon[4] = mtbike

	WAIT 0 
	WAIT 0 
	WAIT 0 
	WAIT 0 
	WAIT 0 
	WAIT 0 
	WAIT 0 
	WAIT 0 
	WAIT 0 
	WAIT 0 
	WAIT 0 
	WAIT 0 
	WAIT 0 
ENDIF


/////////////////////////////////////////////


triathalon1:


	get_in_water = 0 
	start_swim = 1 
	end_swim = 7
	get_on_bike = 8 
	start_bike = 8
	end_bike = 37
	get_on_foot = 37
	start_run = 38
	end_run	= 62


	coord_from_swim_to_bike_x = -243.9 
	coord_from_swim_to_bike_y =  -1719.31 
	coord_from_swim_to_bike_z = 2.35





	checkpoints_triathalon_x[0] = 181.3364 	
	checkpoints_triathalon_y[0] = -1885.8540 	
	checkpoints_triathalon_z[0] = 3.5158 		
	checkpoint_headings[0]  =	  194.8860 

	checkpoints_triathalon_x[1] = 187.69 	
	checkpoints_triathalon_y[1] = -1915.4199 	
	checkpoints_triathalon_z[1] = 0.0 	
	checkpoint_headings[1]  = 	  187.4411 

	checkpoints_triathalon_x[2] = 190.3243 	
	checkpoints_triathalon_y[2] = -1965.0697 	
	checkpoints_triathalon_z[2] = -1.09 	
	checkpoint_headings[2]  =	  122.2256 

	checkpoints_triathalon_x[3] = 129.4773 	
	checkpoints_triathalon_y[3] = -2001.4325 	
	checkpoints_triathalon_z[3] = -0.5606 	
	checkpoint_headings[3]  = 	  44.5101 

	checkpoints_triathalon_x[4] = 24.1326 	
	checkpoints_triathalon_y[4] = -1872.0916 	
	checkpoints_triathalon_z[4] = -0.1744 	
	checkpoint_headings[4]  = 	  46.3064 

	checkpoints_triathalon_x[5] = -38.8038 	
	checkpoints_triathalon_y[5] = -1799.3995 	
	checkpoints_triathalon_z[5] =  -0.4976 	
	checkpoint_headings[5]  =	  29.2224 

	checkpoints_triathalon_x[6] =  -132.8051 	 
	checkpoints_triathalon_y[6] =  -1809.7883  	 
	checkpoints_triathalon_z[6] =  -1.1548 	   	 
	checkpoint_headings[6]  =	   74.9425 		 

	checkpoints_triathalon_x[7] = -228.93 	  	 
	checkpoints_triathalon_y[7] = -1726.7 		 
	checkpoints_triathalon_z[7] = -0.01 	  	 
	checkpoint_headings[7]  =	  70.7750 		 
								  
	checkpoints_triathalon_x[8] = -248.1052		 
	checkpoints_triathalon_y[8] = -1716.7804 	 
	checkpoints_triathalon_z[8] = 1.9372   		 
	checkpoint_headings[8]  = 	  157.2827   	 

	checkpoints_triathalon_x[9] = -262.3395 	 
	checkpoints_triathalon_y[9] = -1787.2769 	 
	checkpoints_triathalon_z[9] = 8.7655  		 
	checkpoint_headings[9]  =	  167.5477 	 	 
	 
	checkpoints_triathalon_x[10] =-277.3514 
	checkpoints_triathalon_y[10] =-1855.0980 
	checkpoints_triathalon_z[10] =21.3005 
	checkpoint_headings[10]  =	  168.3277 

	checkpoints_triathalon_x[11] =-280.5171 
	checkpoints_triathalon_y[11] =-1887.1202 
	checkpoints_triathalon_z[11] =24.7860 
	checkpoint_headings[11]  =	  176.1066 

	checkpoints_triathalon_x[12] =-281.7469 	
	checkpoints_triathalon_y[12] =-2087.8274 	
	checkpoints_triathalon_z[12] =38.4430 	
	checkpoint_headings[12]  =	  141.1524 

	checkpoints_triathalon_x[13] =-464.7682 	
	checkpoints_triathalon_y[13] =-2179.8152 	
	checkpoints_triathalon_z[13] =58.8239 	
	checkpoint_headings[13] =	  68.1063 

	checkpoints_triathalon_x[14] =-641.5079 	
	checkpoints_triathalon_y[14] =-2196.4294 	
	checkpoints_triathalon_z[14] =11.5282 	
	checkpoint_headings[14]  =	  129.7469 

	checkpoints_triathalon_x[15] =-783.5144 	
	checkpoints_triathalon_y[15] =-2459.2942 	
	checkpoints_triathalon_z[15] =73.7524 	
	checkpoint_headings[15]  =	  140.2978 

	checkpoints_triathalon_x[16] =-871.3914 	
	checkpoints_triathalon_y[16] =-2407.9866 	
	checkpoints_triathalon_z[16] =54.4911 	
	checkpoint_headings[16]  =	  51.7369 

	checkpoints_triathalon_x[17] =-942.5181 	
	checkpoints_triathalon_y[17] =-2368.0625 	
	checkpoints_triathalon_z[17] =58.8718 	
	checkpoint_headings[17]  =	  85.4252 

	checkpoints_triathalon_x[18] =-1056.2490 	
	checkpoints_triathalon_y[18] =-2378.2434 	
	checkpoints_triathalon_z[18] =48.5923 	
	checkpoint_headings[18]  =	  94.4035 

	checkpoints_triathalon_x[19] =-1238.9647 	
	checkpoints_triathalon_y[19] =-2327.3149 	
	checkpoints_triathalon_z[19] =18.2458 	
	checkpoint_headings[19]  =	  58.7494 

	checkpoints_triathalon_x[20] =-1403.7797 	
	checkpoints_triathalon_y[20] =-2165.1235 	
	checkpoints_triathalon_z[20] =17.4059 	
	checkpoint_headings[20]  =	  77.2286 

	checkpoints_triathalon_x[21] =-1659.6847 	
	checkpoints_triathalon_y[21] =-2190.1299 	
	checkpoints_triathalon_z[21] =32.3572 	
	checkpoint_headings[21]  =	  144.7275 

	checkpoints_triathalon_x[22] =-1732.8307 	
	checkpoints_triathalon_y[22] =-2312.7092 	
	checkpoints_triathalon_z[22] =45.2250 	
	checkpoint_headings[22]  =	  109.6972 

	checkpoints_triathalon_x[23] =-1826.0583 	
	checkpoints_triathalon_y[23] =-2340.2705 	
	checkpoints_triathalon_z[23] =33.7243 	
	checkpoint_headings[23]  =	  125.1071 

	checkpoints_triathalon_x[24] =-1917.6799 	
	checkpoints_triathalon_y[24] =-2432.1472 	
	checkpoints_triathalon_z[24] =29.6249 	
	checkpoint_headings[24]  =	  124.1505 

	checkpoints_triathalon_x[25] =-1972.5150 
	checkpoints_triathalon_y[25] =-2478.3259 	
	checkpoints_triathalon_z[25] =29.6259 	
	checkpoint_headings[25]  =	  117.8682 

	checkpoints_triathalon_x[26] =-2025.6293 	
	checkpoints_triathalon_y[26] =-2500.3909 	
	checkpoints_triathalon_z[26] =31.0671 	
	checkpoint_headings[26]  =	  73.6687 

	checkpoints_triathalon_x[27] =-2119.1067 	
	checkpoints_triathalon_y[27] =-2431.2651 	
	checkpoints_triathalon_z[27] =29.4766 	
	checkpoint_headings[27]  =	  50.8121 

	checkpoints_triathalon_x[28] =-2188.5703 	
	checkpoints_triathalon_y[28] =-2377.5378 	
	checkpoints_triathalon_z[28] =29.6172 	
	checkpoint_headings[28]  =	  0.0000 

	checkpoints_triathalon_x[29] =-2215.89 	
	checkpoints_triathalon_y[29] =-2352.6011 	
	checkpoints_triathalon_z[29] =29.6981 	
	checkpoint_headings[29]  =	  61.4763 

	checkpoints_triathalon_x[30] =-2341.2097 	
	checkpoints_triathalon_y[30] =-2256.6274 	
	checkpoints_triathalon_z[30] =16.8759 	
	checkpoint_headings[30]  =	  39.8273 

	checkpoints_triathalon_x[31] =-2430.1804 	
	checkpoints_triathalon_y[31] =-2278.3782 	
	checkpoints_triathalon_z[31] =13.7817 	
	checkpoint_headings[31]  =	  45.7545 

	checkpoints_triathalon_x[32] =-2498.6375 	
	checkpoints_triathalon_y[32] =-2208.5735 	
	checkpoints_triathalon_z[32] =27.9284 	
	checkpoint_headings[32]  =	  50.0042 	

	checkpoints_triathalon_x[33] =-2619.8386 	
	checkpoints_triathalon_y[33] =-2067.5356 	
	checkpoints_triathalon_z[33] =38.1676 	
	checkpoint_headings[33]  =	  55.7597 	

	checkpoints_triathalon_x[33] =-2867.7844 	
	checkpoints_triathalon_y[33] =-1824.5710 	
	checkpoints_triathalon_z[33] =36.6821 	
	checkpoint_headings[33]  =	  16.2011 	

	checkpoints_triathalon_x[34] =-2923.2412 	
	checkpoints_triathalon_y[34] =-1561.8893 	
	checkpoints_triathalon_z[34] =10.0017 	
	checkpoint_headings[34]  =	  7.7626 	

	checkpoints_triathalon_x[35] =-2899.0308 	
	checkpoints_triathalon_y[35] =-1152.5844 	
	checkpoints_triathalon_z[35] =8.3200 		
	checkpoint_headings[35]  =	  351.8611 	

	checkpoints_triathalon_x[36] =-2858.1255 	
	checkpoints_triathalon_y[36] =-835.5231 	
	checkpoints_triathalon_z[36] =8.4844 	
	checkpoint_headings[36]  =	  352.7365 	

	checkpoints_triathalon_x[37] =-2814.01 
	checkpoints_triathalon_y[37] =-529.22 
	checkpoints_triathalon_z[37] =6.1876 
	checkpoint_headings[37]  =	  343.4993 	

	checkpoints_triathalon_x[38] = -2779.95
	checkpoints_triathalon_y[38] = -489.85
	checkpoints_triathalon_z[38] = 6.33
	checkpoint_headings[38]  =	  305.3686 //start of running	

	checkpoints_triathalon_x[39] =-2708.9814 
	checkpoints_triathalon_y[39] =-529.5283
	checkpoints_triathalon_z[39] =10.1960 
	checkpoint_headings[39]  =	  276.1151 

	checkpoints_triathalon_x[40] =-2670.1570 				// PROBLEM
	checkpoints_triathalon_y[40] =-481.2506 
	checkpoints_triathalon_z[40] =21.3104 
	checkpoint_headings[40]  =	  345.9380 

	checkpoints_triathalon_x[41] =-2666.6633 
	checkpoints_triathalon_y[41] =-406.0401 
	checkpoints_triathalon_z[41] =31.3067 
	checkpoint_headings[41]  =	  324.8106 

	checkpoints_triathalon_x[42] =-2583.7402 
	checkpoints_triathalon_y[42] =-369.7502 
	checkpoints_triathalon_z[42] =46.2653 
	checkpoint_headings[42]  =	  275.6224 

	checkpoints_triathalon_x[43] =-2515.9709 
	checkpoints_triathalon_y[43] =-367.1822 
	checkpoints_triathalon_z[43] =58.2571 
	checkpoint_headings[43]  =	  272.1555 

	checkpoints_triathalon_x[43] =-2411.1558 		   // PROBLEM
	checkpoints_triathalon_y[43] =-371.7223 
	checkpoints_triathalon_z[43] =72.2992
	checkpoint_headings[43]  =	  236.1908 

	checkpoints_triathalon_x[44] =-2340.5637 
	checkpoints_triathalon_y[44] =-416.5017 
	checkpoints_triathalon_z[44] =78.5669 
	checkpoint_headings[44]  =	  230.3644 

	checkpoints_triathalon_x[45] =-2338.8240 		// PROBLEM
	checkpoints_triathalon_y[45] =-463.8320 
	checkpoints_triathalon_z[45] =79.0233 
	checkpoint_headings[45]  =	  107.3230 

	checkpoints_triathalon_x[46] =-2387.69 
	checkpoints_triathalon_y[46] =-423.99 
	checkpoints_triathalon_z[46] =82.4307 
	checkpoint_headings[46]  =	  46.7979 
								  
	checkpoints_triathalon_x[47] =-2496.3792 
	checkpoints_triathalon_y[47] =-446.1119 
	checkpoints_triathalon_z[47] =75.3116 
	checkpoint_headings[47]  =	  79.2676 

	checkpoints_triathalon_x[48] =-2544.4983 	
	checkpoints_triathalon_y[48] =-465.2180 
	checkpoints_triathalon_z[48] =68.9665 
	checkpoint_headings[48]  =	  119.0487  	

	checkpoints_triathalon_x[48] =-2556.8521 
	checkpoints_triathalon_y[48] =-466.8922 
	checkpoints_triathalon_z[48] =69.2275 
	checkpoint_headings[48]  =	  116.1301 

	checkpoints_triathalon_x[49] =   -2580.5320 
	checkpoints_triathalon_y[49] =   -468.6765 
	checkpoints_triathalon_z[49] =   67.3814 
	checkpoint_headings[49]  =	     105.1953 

	checkpoints_triathalon_x[50] =   -2612.0640
	checkpoints_triathalon_y[50] =   -475.2015 
	checkpoints_triathalon_z[50] =   67.8512 
	checkpoint_headings[50]  =	     102.5922 

	checkpoints_triathalon_x[51] =   -2623.1050 
	checkpoints_triathalon_y[51] =   -500.2463 
	checkpoints_triathalon_z[51] =   69.9170 
	checkpoint_headings[51]  =	     197.9217 

	checkpoints_triathalon_x[52] =   -2613.7502 
	checkpoints_triathalon_y[52] =   -497.1339 
	checkpoints_triathalon_z[52] =   71.9272 
	checkpoint_headings[52]  =	     288.9547 

	checkpoints_triathalon_x[53] =   -2579.7332 
	checkpoints_triathalon_y[53] =   -492.6317 
	checkpoints_triathalon_z[53] =   75.1580 
	checkpoint_headings[53]  =	     278.9598 

	checkpoints_triathalon_x[54] =   -2542.8 
	checkpoints_triathalon_y[54] =   -490.87 
	checkpoints_triathalon_z[54] =   80.340 
	checkpoint_headings[54]  =	     280.2745
								   
	checkpoints_triathalon_x[55] =   -2497.6782 
	checkpoints_triathalon_y[55] =   -480.3432 
	checkpoints_triathalon_z[55] =   94.0158 
	checkpoint_headings[55]  =	     282.4144 

	checkpoints_triathalon_x[56] =   -2466.4011 
	checkpoints_triathalon_y[56] =   -497.2612 
	checkpoints_triathalon_z[56] =   104.2728
	checkpoint_headings[56]  =	     198.2153 

	checkpoints_triathalon_x[57] =   -2452.0803
	checkpoints_triathalon_y[57] =   -544.5864 
	checkpoints_triathalon_z[57] =   120.5837 
	checkpoint_headings[57]  =	     197.1636 

	checkpoints_triathalon_x[58] =   -2469.5137
	checkpoints_triathalon_y[58] =   -618.5020 
	checkpoints_triathalon_z[58] =   131.3831
	checkpoint_headings[58]  =	     208.6952 

	checkpoints_triathalon_x[59] =   -2471.06 
	checkpoints_triathalon_y[59] =   -618.73 
	checkpoints_triathalon_z[59] =   131.73 
	checkpoint_headings[59]  =	     85.5749 

	checkpoints_triathalon_x[60] =   -2498.3682 
	checkpoints_triathalon_y[60] =   -617.9995 
	checkpoints_triathalon_z[60] =   131.5625 
	checkpoint_headings[60]  =	     94.5939 

	checkpoints_triathalon_x[61] =   -2498.9812 
	checkpoints_triathalon_y[61] =   -653.2845 
	checkpoints_triathalon_z[61] =   137.3194
	checkpoint_headings[61]  =	     168.6758 

	checkpoints_triathalon_x[62] =   -2501.3560 
	checkpoints_triathalon_y[62] =   -695.9691 
	checkpoints_triathalon_z[62] =   138.3174 
	checkpoint_headings[62]  =	     180.3733


	point_after_finish_line_x = -2500.0
	point_after_finish_line_y = -710.0
	point_after_finish_line_z =	138.49


	total_checkpoints_triathalon_triathalon = 63

	total_racers_mtbike = 9

   //	total_racers_mtbike = 2



	opponents_cycle_speed_fast = 60.0 
	opponents_cycle_speed_slow = 40.0
	opponents_cycle_speed_really_slow = 25.0


	opponents_swim_speed_fast = 2.25
	opponents_swim_speed_slow = 2.0 
	opponents_swim_speed_really_slow = 1.8

	opponents_run_speed_fast = 1.30 
	opponents_run_speed_slow = 1.25
	opponents_run_speed_really_slow	= 1.16



	offset_swimming_opponents_x[0]= 2.3 
	offset_swimming_opponents_y[0]= -6.0

	offset_swimming_opponents_x[1]= 3.0 
	offset_swimming_opponents_y[1]= 8.0

	offset_swimming_opponents_x[2]= 5.0
	offset_swimming_opponents_y[2]= -4.0

	offset_swimming_opponents_x[3]= 2.6
	offset_swimming_opponents_y[3]= -1.0

	offset_swimming_opponents_x[4]= 5.0
	offset_swimming_opponents_y[4]= 9.0

	offset_swimming_opponents_x[5]= 0.0
	offset_swimming_opponents_y[5]= -4.0

	offset_swimming_opponents_x[6]= -4.0
	offset_swimming_opponents_y[6]= 0.0

	offset_swimming_opponents_x[7]= 0.0
	offset_swimming_opponents_y[7]= 0.9

	offset_swimming_opponents_x[8]= 3.1
	offset_swimming_opponents_y[8]= -4.0


	offset_swimming_opponents_x[9]= 3.0 
	offset_swimming_opponents_y[9]= -5.0

	offset_swimming_opponents_x[10]= -5.0
	offset_swimming_opponents_y[10]= -3.0

	offset_swimming_opponents_x[11]= -2.0
	offset_swimming_opponents_y[11]= 9.0




GOTO triathalon_start

/////////////////////////////////











/////////////////////////////////
triathalon2:


	get_in_water = 0 
	start_swim = 1 
	end_swim = 9
	get_on_bike = 10 
	start_bike = 10
	end_bike = 96
	get_on_foot = 96
	start_run = 97
	end_run	= 103	 


	coord_from_swim_to_bike_x = 1408.9 
	coord_from_swim_to_bike_y =  -298.31 
	coord_from_swim_to_bike_z = 1.14
 








	checkpoints_triathalon_x[0] = 	2135.4319 	
	checkpoints_triathalon_y[0] = 	-67.9555 
	checkpoints_triathalon_z[0] = 	5.4915 
	checkpoint_headings[0]  =	  	66.3885 

	checkpoints_triathalon_x[1] = 	2113.1892 
	checkpoints_triathalon_y[1] = 	-61.7735 
	checkpoints_triathalon_z[1] = 	-1.6317 
	checkpoint_headings[1]  = 	  	79.8290 

	checkpoints_triathalon_x[2] = 	2042.6201 
	checkpoints_triathalon_y[2] = 	-68.5141 
	checkpoints_triathalon_z[2] = 	-1.9576 
	checkpoint_headings[2]  =	  	105.8667 

	checkpoints_triathalon_x[3] = 	1914.2954 
	checkpoints_triathalon_y[3] = 	-82.3669 
	checkpoints_triathalon_z[3] = 	-1.5730 
	checkpoint_headings[3]  = 	  	101.7482 

	checkpoints_triathalon_x[4] = 	1844.0347 
	checkpoints_triathalon_y[4] = 	-62.2716 
	checkpoints_triathalon_z[4] = 	-1.6096 
	checkpoint_headings[4]  = 	  	84.9519 

	checkpoints_triathalon_x[5] = 	1742.2363 
	checkpoints_triathalon_y[5] = 	-20.0478 
	checkpoints_triathalon_z[5] = 	-1.2870 
	checkpoint_headings[5]  =	  	79.6055 

	checkpoints_triathalon_x[6] = 	1618.1678 
	checkpoints_triathalon_y[6] = 	-15.6618 
	checkpoints_triathalon_z[6] = 	-1.6797 
	checkpoint_headings[6]  =	  	156.9966 

	checkpoints_triathalon_x[7] = 	1599.8545 
	checkpoints_triathalon_y[7] = 	-118.7409 
	checkpoints_triathalon_z[7] = 	-1.4099 
	checkpoint_headings[7]  =	  	164.6305 
								  
	checkpoints_triathalon_x[8] = 	1533.9745 
	checkpoints_triathalon_y[8] = 	-213.8303 
	checkpoints_triathalon_z[8] = 	-1.8143 
	checkpoint_headings[8]  = 	  	126.3196 

	checkpoints_triathalon_x[9] = 	1426.7809 
	checkpoints_triathalon_y[9] = 	-284.0623 
	checkpoints_triathalon_z[9] = 	0.4372 
	checkpoint_headings[9]  =	  	144.5477  //end swim
	 
	checkpoints_triathalon_x[10] =	1398.9552 
	checkpoints_triathalon_y[10] =	-320.3510 
	checkpoints_triathalon_z[10] =	4.0182 
	checkpoint_headings[10]  =	  	153.3551 	// start of bikes

	checkpoints_triathalon_x[11] =	1358.0930 
	checkpoints_triathalon_y[11] =	-336.8423 
	checkpoints_triathalon_z[11] =	1.9089
	checkpoint_headings[11]  =	  	116.3511 

	checkpoints_triathalon_x[12] =	1289.7388 
	checkpoints_triathalon_y[12] =	-376.0291 
	checkpoints_triathalon_z[12] =	1.8575 
	checkpoint_headings[12]  =	  	115.9818 

	checkpoints_triathalon_x[13] =	1226.1530 
	checkpoints_triathalon_y[13] =	-418.5785 
	checkpoints_triathalon_z[13] =	4.1044 
	checkpoint_headings[13] =	  	98.6074 

	checkpoints_triathalon_x[14] =	1042.3131 
	checkpoints_triathalon_y[14] =	-450.2890 
	checkpoints_triathalon_z[14] =	50.3135 
	checkpoint_headings[14]  =	  	101.4914 

	checkpoints_triathalon_x[15] =	955.7357  
	checkpoints_triathalon_y[15] =	-394.1266 
	checkpoints_triathalon_z[15] =	62.9355 
	checkpoint_headings[15]  =	  	78.1451 

	checkpoints_triathalon_x[16] = 862.81	
	checkpoints_triathalon_y[16] = -348.28	 
	checkpoints_triathalon_z[16] = 35.11	
	checkpoint_headings[16]  = 56.0	  	

	checkpoints_triathalon_x[17] =	735.1562 
	checkpoints_triathalon_y[17] =	-269.1867 
	checkpoints_triathalon_z[17] =	8.7576 
	checkpoint_headings[17]  =	  	73.8366 

	checkpoints_triathalon_x[18] =	469.2164 
	checkpoints_triathalon_y[18] =	-284.2076 
	checkpoints_triathalon_z[18] =	9.4602 
	checkpoint_headings[18]  =	  	112.1922 

	checkpoints_triathalon_x[19] =	251.8701 
	checkpoints_triathalon_y[19] =	-371.7388 
	checkpoints_triathalon_z[19] =	7.7356 
	checkpoint_headings[19]  =	  	68.2550 

	checkpoints_triathalon_x[20] =	223.9637 
	checkpoints_triathalon_y[20] =	-281.3440 
	checkpoints_triathalon_z[20] =	0.4297 
	checkpoint_headings[20]  =	  	271.1071 

	checkpoints_triathalon_x[21] =	232.8023 
	checkpoints_triathalon_y[21] =	-147.7986 
	checkpoints_triathalon_z[21] =	0.4375 
	checkpoint_headings[21]  =	  	357.6566 

	checkpoints_triathalon_x[22] =	212.8295 
	checkpoints_triathalon_y[22] =	51.8381 
	checkpoints_triathalon_z[22] =	1.0781 
	checkpoint_headings[22]  =	  	109.6694 

	checkpoints_triathalon_x[23] =	-68.0222 
	checkpoints_triathalon_y[23] =	199.2302 
	checkpoints_triathalon_z[23] =	1.1111 
	checkpoint_headings[23]  =	  	65.8208 

	checkpoints_triathalon_x[24] =	-214.4978 
	checkpoints_triathalon_y[24] =	242.3661 
	checkpoints_triathalon_z[24] =	10.9924 
	checkpoint_headings[24]  =	  	77.1542 

	checkpoints_triathalon_x[25] =	-474.3636 
	checkpoints_triathalon_y[25] =	283.1361 
	checkpoints_triathalon_z[25] =	1.0859 
	checkpoint_headings[25]  =	  	87.3102 

	checkpoints_triathalon_x[26] =	-704.3909 
	checkpoints_triathalon_y[26] =	237.3473 
	checkpoints_triathalon_z[26] =	1.2298 
	checkpoint_headings[26]  =	  	125.7750 

	checkpoints_triathalon_x[27] =	-753.5148 
	checkpoints_triathalon_y[27] =	113.9041 
	checkpoints_triathalon_z[27] =	13.0728 
	checkpoint_headings[27]  =	  	238.8586 

	checkpoints_triathalon_x[28] =	-774.6131 
	checkpoints_triathalon_y[28] =	19.8180 
	checkpoints_triathalon_z[28] =	32.2693 
	checkpoint_headings[28]  =	  	99.2499 

	checkpoints_triathalon_x[29] =	-882.6287 
	checkpoints_triathalon_y[29] =	-30.0806
	checkpoints_triathalon_z[29] =	32.3448 
	checkpoint_headings[29]  =	  	159.8070 

	checkpoints_triathalon_x[30] =	-693.5931 
	checkpoints_triathalon_y[30] =	14.1184 
	checkpoints_triathalon_z[30] =	64.7495 
	checkpoint_headings[30]  =	  	274.7533 

	checkpoints_triathalon_x[31] =	-747.8843 
	checkpoints_triathalon_y[31] =	-87.2834 
	checkpoints_triathalon_z[31] =	65.6724 
	checkpoint_headings[31]  =	  	116.1896 

	checkpoints_triathalon_x[32] =	-914.7136 
	checkpoints_triathalon_y[32] =	-149.6012 
	checkpoints_triathalon_z[32] =	52.8499 
	checkpoint_headings[32]  =	  	134.4302 

	checkpoints_triathalon_x[33] = -962.9247 	
	checkpoints_triathalon_y[33] = -326.8087 	
	checkpoints_triathalon_z[33] = 35.2353 	
	checkpoint_headings[33]  =	   168.4629 	

	checkpoints_triathalon_x[34] = -1053.0137	
	checkpoints_triathalon_y[34] = -459.1835 	
	checkpoints_triathalon_z[34] = 34.3070 	
	checkpoint_headings[34]  =	   115.9967 	

	checkpoints_triathalon_x[35] = -1192.1448 	
	checkpoints_triathalon_y[35] = -713.6215 	
	checkpoints_triathalon_z[35] = 54.6026 	
	checkpoint_headings[35]  =	   156.9905 	

	checkpoints_triathalon_x[36] = -1487.7150 	
	checkpoints_triathalon_y[36] = -817.6843 	
	checkpoints_triathalon_z[36] = 64.4213 	
	checkpoint_headings[36]  =	   92.4112 	

	checkpoints_triathalon_x[37] = -1740.7775	
	checkpoints_triathalon_y[37] = -724.1766 	
	checkpoints_triathalon_z[37] = 30.4857 	
	checkpoint_headings[37]  =	   49.0549 	

	checkpoints_triathalon_x[38] = -1781.2047 	
	checkpoints_triathalon_y[38] = -583.2640 	
	checkpoints_triathalon_z[38] = 15.3359 	
	checkpoint_headings[38]  =	   100.3213 	

	checkpoints_triathalon_x[39] = -1823.7002 	
	checkpoints_triathalon_y[39] = -529.5533 	
	checkpoints_triathalon_z[39] = 13.9686 	
	checkpoint_headings[39]  =	   4.2211 	

	checkpoints_triathalon_x[40] = -1799.0173 	
	checkpoints_triathalon_y[40] = -273.0101 	
	checkpoints_triathalon_z[40] = 20.3398 	
	checkpoint_headings[40]  =	   354.7032 	

	checkpoints_triathalon_x[41] = -1800.1229 	
	checkpoints_triathalon_y[41] = -65.1682 	
	checkpoints_triathalon_z[41] = 10.9423 	
	checkpoint_headings[41]  =	   359.6422 	

	checkpoints_triathalon_x[42] = -1725.0618 	
	checkpoints_triathalon_y[42] = 327.4661 	
	checkpoints_triathalon_z[42] = 6.0390 	
	checkpoint_headings[42]  =	   315.3110 	

	checkpoints_triathalon_x[43] = -1557.0966 	 
	checkpoints_triathalon_y[43] = 578.6060 	
	checkpoints_triathalon_z[43] = 6.0280 	
	checkpoint_headings[43]  =	   0.4208 	

	checkpoints_triathalon_x[44] = -1541.1064 	
	checkpoints_triathalon_y[44] = 769.1249 	
	checkpoints_triathalon_z[44] = 6.0391 	
	checkpoint_headings[44]  =	   353.7148 	

	checkpoints_triathalon_x[45] = -1583.3777 	
	checkpoints_triathalon_y[45] = 1116.9907 	
	checkpoints_triathalon_z[45] = 6.0468 	
	checkpoint_headings[45]  =	   1.1392 	

	checkpoints_triathalon_x[46] = -1809.2827 	
	checkpoints_triathalon_y[46] = 1378.6250 	
	checkpoints_triathalon_z[46] = 6.0391 	
	checkpoint_headings[46]  =	   81.6560 	
								  
	checkpoints_triathalon_x[47] = -2100.2131 	
	checkpoints_triathalon_y[47] = 1275.8335 	
	checkpoints_triathalon_z[47] = 15.1271 	
	checkpoint_headings[47]  =	   95.1487 	

	checkpoints_triathalon_x[48] = -2350.2080 	 
	checkpoints_triathalon_y[48] = 1176.5745 	
	checkpoints_triathalon_z[48] = 41.5671 	
	checkpoint_headings[48]  =	   90.0114 	

	checkpoints_triathalon_x[49] = -2510.8774 	
	checkpoints_triathalon_y[49] = 1187.8561 	
	checkpoints_triathalon_z[49] = 39.1262 	
	checkpoint_headings[49]  =	   85.2520 	

	checkpoints_triathalon_x[50] = -2669.6802 	
	checkpoints_triathalon_y[50] = 1245.1427 	
	checkpoints_triathalon_z[50] = 54.4297 	
	checkpoint_headings[50]  =	   8.9025 	

	checkpoints_triathalon_x[51] = -2673.8926 	
	checkpoints_triathalon_y[51] = 1643.3517 	
	checkpoints_triathalon_z[51] = 65.0251 	
	checkpoint_headings[51]  =	   0.2767 	

	checkpoints_triathalon_x[52] = -2674.0469 	
	checkpoints_triathalon_y[52] = 2075.5439 	
	checkpoints_triathalon_z[52] = 54.4218 	
	checkpoint_headings[52]  =	   359.5528 	

	checkpoints_triathalon_x[53] = -2738.8127 	
	checkpoints_triathalon_y[53] = 2389.1667 	
	checkpoints_triathalon_z[53] = 71.7769 	
	checkpoint_headings[53]  =	   13.6254 	

	checkpoints_triathalon_x[54] = -2547.9309 	
	checkpoints_triathalon_y[54] = 2616.4624 	
	checkpoints_triathalon_z[54] = 59.9948 	
	checkpoint_headings[54]  =	   274.8428 	
								  
	checkpoints_triathalon_x[55] = -2291.8877 	
	checkpoints_triathalon_y[55] = 2639.5605 	
	checkpoints_triathalon_z[55] = 54.2564 	
	checkpoint_headings[55]  =	   274.3506 	

	checkpoints_triathalon_x[56] = -1943.4406 	
	checkpoints_triathalon_y[56] = 2477.7957 	
	checkpoints_triathalon_z[56] = 53.9877 	
	checkpoint_headings[56]  =	   203.7195 	

	checkpoints_triathalon_x[57] = -1773.8296 	
	checkpoints_triathalon_y[57] = 2183.4089 	
	checkpoints_triathalon_z[57] = 15.1278 	
	checkpoint_headings[57]  =	   244.4939 	

	checkpoints_triathalon_x[58] = -1671.4347 	
	checkpoints_triathalon_y[58] = 1994.4735 	
	checkpoints_triathalon_z[58] = 18.6050 	
	checkpoint_headings[58]  =	   169.1263 	

	checkpoints_triathalon_x[59] = -1665.7004 	
	checkpoints_triathalon_y[59] = 1828.9640 	
	checkpoints_triathalon_z[59] = 24.8360	
	checkpoint_headings[59]  =	    278.2649 	

	checkpoints_triathalon_x[60] = -1515.5267 	
	checkpoints_triathalon_y[60] = 1841.5544 	
	checkpoints_triathalon_z[60] = 28.9903 	
	checkpoint_headings[60]  =	   271.1894 	

	checkpoints_triathalon_x[61] = -1264.9932 	
	checkpoints_triathalon_y[61] = 1827.3014 	
	checkpoints_triathalon_z[61] = 39.4348 	
	checkpoint_headings[61]  =	   234.9500 	

	checkpoints_triathalon_x[62] = -1121.3851 	
	checkpoints_triathalon_y[62] = 1807.5393 	
	checkpoints_triathalon_z[62] = 41.2592 	
	checkpoint_headings[62]  =	   284.4989 	

	checkpoints_triathalon_x[63] = -988.9796 	
	checkpoints_triathalon_y[63] = 1847.6390 	
	checkpoints_triathalon_z[63] = 61.9118 	
	checkpoint_headings[63]  =	   237.4219 	

	checkpoints_triathalon_x[64] = -855.0579 	
	checkpoints_triathalon_y[64] = 1858.8971 	
	checkpoints_triathalon_z[64] = 59.2160 	
	checkpoint_headings[64]  =	   15.0168 	

	checkpoints_triathalon_x[65] = -827.7874 
	checkpoints_triathalon_y[65] = 2022.6101 
	checkpoints_triathalon_z[65] = 59.1866 
	checkpoint_headings[65]  =	   302.1424 

	checkpoints_triathalon_x[66] = -643.9840 
	checkpoints_triathalon_y[66] = 2054.7522 
	checkpoints_triathalon_z[66] = 59.3829 
	checkpoint_headings[66]  =	   252.3283 

	checkpoints_triathalon_x[67] = -460.4977 
	checkpoints_triathalon_y[67] = 2022.1373 
	checkpoints_triathalon_z[67] = 59.1932 
	checkpoint_headings[67]  =	   314.0172 

	checkpoints_triathalon_x[68] = -385.3306
	checkpoints_triathalon_y[68] = 2052.0591 
	checkpoints_triathalon_z[68] = 61.1139 
	checkpoint_headings[68]  =	   121.3349 

	checkpoints_triathalon_x[69] = -471.1979 
	checkpoints_triathalon_y[69] = 1868.4519 
	checkpoints_triathalon_z[69] = 81.3094 
	checkpoint_headings[69]  =	   185.3215 

	checkpoints_triathalon_x[70] = -424.8187 
	checkpoints_triathalon_y[70] = 1785.5519 
	checkpoints_triathalon_z[70] = 69.9688 
	checkpoint_headings[70]  =	   353.9297 

	checkpoints_triathalon_x[71] = -392.5027 
	checkpoints_triathalon_y[71] = 1899.6776 
	checkpoints_triathalon_z[71] = 56.3096 
	checkpoint_headings[71]  =	   190.7471 

	checkpoints_triathalon_x[72] = -437.5663 
	checkpoints_triathalon_y[72] = 1639.0536 
	checkpoints_triathalon_z[72] = 34.5544 
	checkpoint_headings[72]  =	   155.0889 

	checkpoints_triathalon_x[73] = -419.9048 
	checkpoints_triathalon_y[73] = 1375.7109 
	checkpoints_triathalon_z[73] = 29.3441 
	checkpoint_headings[73]  =	   198.0373 

	checkpoints_triathalon_x[74] = -254.2456 
	checkpoints_triathalon_y[74] = 1241.2448 
	checkpoints_triathalon_z[74] = 22.9055 
	checkpoint_headings[74]  =	   262.7647 

	checkpoints_triathalon_x[75] = -17.3116 
	checkpoints_triathalon_y[75] = 1265.9962 
	checkpoints_triathalon_z[75] = 7.1868 
	checkpoint_headings[75]  =	   269.9033 

	checkpoints_triathalon_x[76] = 207.2180 
	checkpoints_triathalon_y[76] = 1174.1932 
	checkpoints_triathalon_z[76] = 13.9973
	checkpoint_headings[76]  =	   316.9584 

	checkpoints_triathalon_x[77] = 339.0704 
	checkpoints_triathalon_y[77] = 1382.2875 
	checkpoints_triathalon_z[77] = 6.1753 
	checkpoint_headings[77]  =	   342.3971 

	checkpoints_triathalon_x[78] = 388.3414 
	checkpoints_triathalon_y[78] = 1547.9292 
	checkpoints_triathalon_z[78] = 13.8468 
	checkpoint_headings[78]  =	   343.6689 

	checkpoints_triathalon_x[79] = 633.4001 
	checkpoints_triathalon_y[79] = 1754.8674 
	checkpoints_triathalon_z[79] = 3.9566 
	checkpoint_headings[79]  =	   317.1131 
								  
	checkpoints_triathalon_x[80] = 669.8152 
	checkpoints_triathalon_y[80] = 2168.3225 
	checkpoints_triathalon_z[80] = 20.5117 
	checkpoint_headings[80]  =	   358.9947 

	checkpoints_triathalon_x[81] = 689.6470 
	checkpoints_triathalon_y[81] = 2565.1602 
	checkpoints_triathalon_z[81] = 27.2334 
	checkpoint_headings[81]  =	   311.5221 

	checkpoints_triathalon_x[82] = 891.8608 
	checkpoints_triathalon_y[82] = 2603.7539 
	checkpoints_triathalon_z[82] = 9.5950 
	checkpoint_headings[82]  =	   247.5050 

	checkpoints_triathalon_x[83] = 1044.0208 
	checkpoints_triathalon_y[83] = 2515.8572 
	checkpoints_triathalon_z[83] = 9.6323 
	checkpoint_headings[83]  =	   231.5339 

	checkpoints_triathalon_x[84] = 1244.5967 
	checkpoints_triathalon_y[84] = 2379.1238 
	checkpoints_triathalon_z[84] = 12.6162 
	checkpoint_headings[84]  =	   243.2790 

	checkpoints_triathalon_x[85] = 1443.8691 
	checkpoints_triathalon_y[85] = 2448.9451 
	checkpoints_triathalon_z[85] = 5.7498 
	checkpoint_headings[85]  =	   288.6619 

	checkpoints_triathalon_x[86] = 1666.8932 
	checkpoints_triathalon_y[86] = 2445.9934 
	checkpoints_triathalon_z[86] = 6.0409 
	checkpoint_headings[86]  =	   251.8771 

	checkpoints_triathalon_x[87] = 1788.8903 
	checkpoints_triathalon_y[87] = 2302.6406 
	checkpoints_triathalon_z[87] = 4.9096 
	checkpoint_headings[87]  =	   178.4221 

	checkpoints_triathalon_x[88] = 1787.2880 
	checkpoints_triathalon_y[88] = 2020.5527 
	checkpoints_triathalon_z[88] = 2.9839 
	checkpoint_headings[88]  =	   180.0315 
								  
	checkpoints_triathalon_x[89] = 1757.5692 
	checkpoints_triathalon_y[89] = 1803.7501 
	checkpoints_triathalon_z[89] = 6.6726 
	checkpoint_headings[89]  =	   151.3169 

	checkpoints_triathalon_x[90] = 1719.6592 
	checkpoints_triathalon_y[90] = 1733.2050 
	checkpoints_triathalon_z[90] = 9.6719 
	checkpoint_headings[90]  =	   76.1769 

	checkpoints_triathalon_x[91] = 1647.8604 
	checkpoints_triathalon_y[91] = 1740.3892 
	checkpoints_triathalon_z[91] = 9.6719 
	checkpoint_headings[91]  =	   353.7883 

	checkpoints_triathalon_x[92] = 1666.3218
	checkpoints_triathalon_y[92] = 1871.7675 
	checkpoints_triathalon_z[92] = 9.6797 
	checkpoint_headings[92]  =	   276.2030 

	checkpoints_triathalon_x[93] = 1707.1766 
	checkpoints_triathalon_y[93] = 1939.9534 
	checkpoints_triathalon_z[93] = 9.6719 
	checkpoint_headings[93]  =	   1.2039 

	checkpoints_triathalon_x[94] = 1736.2123 
	checkpoints_triathalon_y[94] = 2053.1387 
	checkpoints_triathalon_z[94] = 9.6797 
	checkpoint_headings[94]  =	   263.9626 

	checkpoints_triathalon_x[95] = 1897.3198 
	checkpoints_triathalon_y[95] = 2044.7122 
	checkpoints_triathalon_z[95] = 9.6719 
	checkpoint_headings[95]  =	   254.9706 

	checkpoints_triathalon_x[96] = 1994.8163 
	checkpoints_triathalon_y[96] = 2022.8688 
	checkpoints_triathalon_z[96] = 9.6797 
	checkpoint_headings[96]  =	   270.1811 	 // end of bike

	checkpoints_triathalon_x[97] = 2126.0054 
	checkpoints_triathalon_y[97] = 2014.8894 
	checkpoints_triathalon_z[97] = 9.6719 
	checkpoint_headings[97]  =	   199.0654	// start run 

	checkpoints_triathalon_x[98] = 2126.7981 
	checkpoints_triathalon_y[98] = 1885.5897 
	checkpoints_triathalon_z[98] = 9.6797 
	checkpoint_headings[98]  =	   178.0925 
								  
	checkpoints_triathalon_x[99] = 2098.3350 
	checkpoints_triathalon_y[99] = 1802.8602 
	checkpoints_triathalon_z[99] = 9.6797 
	checkpoint_headings[99]  =	   156.4693 

	checkpoints_triathalon_x[100] =2056.6440 
	checkpoints_triathalon_y[100] =1720.5748
	checkpoints_triathalon_z[100] =9.6719 
	checkpoint_headings[100]  =	   156.0535 

	checkpoints_triathalon_x[101] =2049.2598 
	checkpoints_triathalon_y[101] =1554.7893 
	checkpoints_triathalon_z[101] =9.6719 
	checkpoint_headings[101]  =	   179.2217 

	checkpoints_triathalon_x[102] =  2050.8267 
	checkpoints_triathalon_y[102] =  1403.3622 
	checkpoints_triathalon_z[102] =  9.6719 
	checkpoint_headings[102]  =	     179.9112 

	checkpoints_triathalon_x[103] =	  2048.7000 
	checkpoints_triathalon_y[103] =	  1089.7463 
	checkpoints_triathalon_z[103] =	  9.6641 
	checkpoint_headings[103]  =	   	  179.8144 	  // end run


	point_after_finish_line_x = 2046.0
	point_after_finish_line_y = 979.0
	point_after_finish_line_z =	9.49




	total_checkpoints_triathalon_triathalon = 104

	total_racers_mtbike = 9



	opponents_cycle_speed_fast = 50.0 
	opponents_cycle_speed_slow = 40.0
	opponents_cycle_speed_really_slow = 30.0


	opponents_swim_speed_fast = 2.23
	opponents_swim_speed_slow = 2.1 
	opponents_swim_speed_really_slow = 1.8

	opponents_run_speed_fast = 1.33 
	opponents_run_speed_slow = 1.30
	opponents_run_speed_really_slow	= 1.25


	offset_swimming_opponents_x[0]= 2.3 
	offset_swimming_opponents_y[0]= -6.0

	offset_swimming_opponents_x[1]= 3.0 
	offset_swimming_opponents_y[1]= -4.0

	offset_swimming_opponents_x[2]= 5.0
	offset_swimming_opponents_y[2]= -4.0

	offset_swimming_opponents_x[3]= 2.6
	offset_swimming_opponents_y[3]= -1.0

	offset_swimming_opponents_x[4]= 5.0
	offset_swimming_opponents_y[4]= 5.0

	offset_swimming_opponents_x[5]= 0.0
	offset_swimming_opponents_y[5]= -4.0

	offset_swimming_opponents_x[6]= -4.0
	offset_swimming_opponents_y[6]= 0.0

	offset_swimming_opponents_x[7]= 0.0
	offset_swimming_opponents_y[7]= 0.9

	offset_swimming_opponents_x[8]= 3.1
	offset_swimming_opponents_y[8]= -4.0


	offset_swimming_opponents_x[9]= 3.0 
	offset_swimming_opponents_y[9]= -5.0

	offset_swimming_opponents_x[10]= -5.0
	offset_swimming_opponents_y[10]= -3.0

	offset_swimming_opponents_x[11]= -2.0
	offset_swimming_opponents_y[11]= 4.0


	flag_make_runner_get_past_finishingline[0] = 0
	flag_make_runner_get_past_finishingline[1] = 0
	flag_make_runner_get_past_finishingline[2] = 0
	flag_make_runner_get_past_finishingline[3] = 0
	flag_make_runner_get_past_finishingline[4] = 0
	flag_make_runner_get_past_finishingline[5] = 0
	flag_make_runner_get_past_finishingline[6] = 0
	flag_make_runner_get_past_finishingline[7] = 0
	flag_make_runner_get_past_finishingline[8] = 0
	flag_make_runner_get_past_finishingline[9] = 0
	flag_make_runner_get_past_finishingline[10] = 0
	flag_make_runner_get_past_finishingline[11] = 0




GOTO triathalon_start




			 

CREATE_CHECKPOINT CHECKPOINT_TUBE racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] second_cp_triathalonx second_cp_triathalony second_cp_triathalonz 6.0 cp_marker_triathalon
DELETE_CHECKPOINT cp_marker_triathalon







triathalon_start:


set_opponents_cycle_speed = opponents_cycle_speed_fast 

set_opponents_swim_speed = opponents_swim_speed_fast 

set_opponents_run_speed =  opponents_run_speed_fast


WHILE NOT IS_PLAYER_PLAYING player1
	WAIT 0
ENDWHILE

CLEAR_AREA checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0] 1000000.5 0
LOAD_SCENE checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0]






DISABLE_ALL_ENTRY_EXITS TRUE




//SWITCH_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 
//SWITCH_PED_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 


	SET_CAR_DENSITY_MULTIPLIER 0.0
	SET_PED_DENSITY_MULTIPLIER 0.0




  

LVAR_INT total_checkpoints_triathalon_triathalon_minus_1
total_checkpoints_triathalon_triathalon_minus_1 = total_checkpoints_triathalon_triathalon - 1

r = 0
a = 0
c = 0
e = 0

LVAR_FLOAT racer_cp_x_triathalon[12] racer_cp_y_triathalon[12] racer_cp_z_triathalon[12]

//VAR_FLOAT stuck_x[6] stuck_y[6]
LVAR_INT racer_blip_triathalon[12]
LVAR_INT racers_mtbike[12]

LVAR_INT triathalonr[12]


LVAR_INT total_triathalon

total_triathalon = total_racers_mtbike


LVAR_INT cpcounter_triathalon[12]

WHILE a < total_triathalon
	
	cpcounter_triathalon[a] = 0
	++ a
ENDWHILE

//PRINT_NOW BIK1_1 5000 1 //"~g~Get a fast car and get to the starting grid."

LVAR_INT	first_blip_triathalon //second_blip_triathalon
ADD_BLIP_FOR_COORD checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0] first_blip_triathalon
//ADD_BLIP_FOR_COORD checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0] second_blip_triathalon
//REMOVE_BLIP second_blip_triathalon

LVAR_INT race_flag_triathalon

LVAR_INT playaz_triathalon
race_flag_triathalon = 0


//VIEW_INTEGER_VARIABLE race_flag_triathalon race_flag_triathalon





mission_triathalon_loop:
WAIT 0

GET_GAME_TIMER game_timer

LVAR_INT position_triathalon
position_triathalon = 0
a = 0	



IF race_flag_triathalon = 0
	IF NOT IS_CHAR_DEAD scplayer
		SET_CHAR_COORDINATES scplayer checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0] 
		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
	   GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP player1 Bbshortwht boxingshort 2
	   BUILD_PLAYER_MODEL player1

		IF LOCATE_CHAR_ANY_MEANS_2D scplayer checkpoints_triathalon_x[1] checkpoints_triathalon_y[1] 100.0 100.0 0
			r = 0
			x = checkpoints_triathalon_x[0] + 4.0
			y = checkpoints_triathalon_y[0] + 4.0
			z = checkpoints_triathalon_z[0] + 6.0
			x2 = checkpoints_triathalon_x[0] - 7.0
			y2 = checkpoints_triathalon_y[0] - 7.0
			z2 = checkpoints_triathalon_z[0] - 7.0
		   //	SWITCH_ROADS_OFF x y z x2 y2 z2


			++ race_flag_triathalon
		ENDIF
	ENDIF
ENDIF

IF race_flag_triathalon = 1

	  /*	temp_int_triathalon = total_triathalon - 1

		REQUEST_MODEL racer_model_triathalon[r]
		REQUEST_MODEL racers_car_model_triathalon[r]

		IF HAS_MODEL_LOADED	racer_model_triathalon[r]
		AND HAS_MODEL_LOADED racers_car_model_triathalon[r]
			++ r
		ELSE
			REQUEST_MODEL racer_model_triathalon[r]
			REQUEST_MODEL racers_car_model_triathalon[r]
		ENDIF
			
		IF r = temp_int_triathalon
			++ race_flag_triathalon
		ENDIF	   */


 



	REQUEST_MODEL wmybe
	REQUEST_MODEL bmybe
	REQUEST_MODEL hmybe
	REQUEST_MODEL wmybell
	REQUEST_MODEL mtbike


	WHILE NOT HAS_MODEL_LOADED wmybe 
		OR NOT HAS_MODEL_LOADED bmybe
		OR NOT HAS_MODEL_LOADED hmybe
		OR NOT HAS_MODEL_LOADED wmybell
		OR NOT HAS_MODEL_LOADED mtbike
		WAIT 0
	ENDWHILE
	race_flag_triathalon = 2

	


		
	
ENDIF

IF race_flag_triathalon = 2


	// players bike
  //	CREATE_CAR mtbike -248.7668 -1714.4104 2.3750 racers_mtbike[playaz_triathalon] // creating the players bike  
  //	SET_CAR_HEADING racers_mtbike[playaz_triathalon] 168.6926


	// bike for teh offsets at starting point
	CREATE_CAR racers_car_model_triathalon[0] checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0] stored_bike_triathalon   // start of cycling
	TURN_CAR_TO_FACE_COORD stored_bike_triathalon checkpoints_triathalon_x[1] checkpoints_triathalon_y[1]// MUST PLACE checkpoint[1] STRAIGHT AHEAD FROM START
	temp_int_triathalon = total_triathalon - 1
	

	// bike for teh offsets at getting on bikes section

	CREATE_CAR racers_car_model_triathalon[0] checkpoints_triathalon_x[get_on_bike] checkpoints_triathalon_y[get_on_bike] -100.0 stored_bike_triathalon2
	SET_CAR_HEADING stored_bike_triathalon2 checkpoint_headings[get_on_bike] 








	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon2 x2 y2 z2 x2 y2 z2
	GET_CAR_HEADING	stored_bike_triathalon2 heading_triathalon2
	z2 = checkpoints_triathalon_z[0]


	a = 0
	WHILE a < total_triathalon

		c = a / 2
		c *= 2
		IF c = a//IS a AN EVEN NUMBER?
			x = 1.5
			
			e = a * 2
			temp_float =# e
			temp_float *= -1.0
			y = temp_float

		ELSE
			x = -1.5
			
			e = a - 1
			e *= 2
			temp_float =# e
			temp_float *= -1.0
			y = temp_float

		ENDIF


		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon x y z x y z
		GET_CAR_HEADING	stored_bike_triathalon heading_triathalon
		z = checkpoints_triathalon_z[0]
		
		racer_cp_x_triathalon[a] = x
		racer_cp_y_triathalon[a] = y
		GET_GROUND_Z_FOR_3D_COORD x y z z
		racer_cp_z_triathalon[a] = z

	  //	DELETE_CAR heading_triathalon2 

		IF a < temp_int_triathalon

		   //	CREATE_CHAR_INSIDE_CAR racers_mtbike[a] PEDTYPE_CIVMALE racer_model_triathalon[a] triathalonr[a]   // making the mountain biker
		
			CREATE_CHAR PEDTYPE_CIVMALE racer_model_triathalon[a] x y -100.0 triathalonr[a]   // making the mountain biker
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER triathalonr[a] TRUE
			SET_CHAR_DECISION_MAKER triathalonr[a] dm_racers_triathalon


			flag_create_bike_and_make_opponent_get_on_it[a] = 0
			flag_make_guys_enter_bike[a] = 0
			SET_CHAR_HEADING triathalonr[a] checkpoint_headings[0]
			SET_CHAR_DROWNS_IN_WATER triathalonr[a] FALSE
			
			CLEAR_CHAR_LAST_DAMAGE_ENTITY triathalonr[a]	
			SET_CHAR_CAN_BE_SHOT_IN_VEHICLE triathalonr[a] FALSE


				IF flag_create_bike_and_make_opponent_get_on_it[a] = 0
					c = a / 2
					c *= 2
					IF c = a//IS a AN EVEN NUMBER?
						x = 3.5
						e = a * 2
						temp_float =# e
						temp_float *= -1.0
						y = temp_float

					ELSE
						x = -3.5
						e = a - 1
						e *= 2
						temp_float =# e
						temp_float *= -1.0
						y = temp_float

					ENDIF


					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon2 x y z x y z
					CREATE_CAR racers_car_model_triathalon[a] x y -100.00 racers_mtbike[a]
				 	IMPROVE_CAR_BY_CHEATING   racers_mtbike[a]  TRUE
				   	SET_CAR_TRACTION racers_mtbike[a] 1.5

					//DELETE_CAR stored_bike_triathalon2 
					flag_create_bike_and_make_opponent_get_on_it[a] = 1

					ADD_STUCK_CAR_CHECK_WITH_WARP racers_mtbike[a] 0.5 5000 TRUE FALSE TRUE 1
				   //	SET_CAR_HEADING	racers_mtbike[a] checkpoint_headings[8]
					SET_CAR_HEADING	racers_mtbike[a] 175.0
					SET_CAR_PROOFS racers_mtbike[a] TRUE TRUE TRUE TRUE TRUE
					SET_CAR_HEALTH racers_mtbike[a] 2000
					SET_CAR_WATERTIGHT racers_mtbike[a] TRUE
					SET_CAR_STRONG racers_mtbike[a] TRUE
					SET_UPSIDEDOWN_CAR_NOT_DAMAGED racers_mtbike[a] TRUE
					SET_CAR_STRAIGHT_LINE_DISTANCE racers_mtbike[a] 5
					SET_CAN_BURST_CAR_TYRES racers_mtbike[a] FALSE	

				ENDIF
		   	   //	WARP_CHAR_INTO_CAR triathalonr[a] racers_mtbike[a]
 






		ELSE
			LVAR_INT spectator_triathalon[11] flag_girl_triathalon
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon -4.0435 6.5755 -0.8 x y z
			CREATE_RANDOM_CHAR x y z flag_girl_triathalon
			TASK_TURN_CHAR_TO_FACE_COORD flag_girl_triathalon checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon -5.6392 2.9435 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[0]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[0] checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon -6.0071 -2.0778 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[1]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[1] checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon -7.6039 -5.0807 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[2]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[2] checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon -7.0724 -6.4057 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[3]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[3] checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon -5.9988 -13.553 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[4]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[4] checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon 6.0730 -15.2059 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[5]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[5] checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon 7.2639 -12.9933 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[6]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[6] checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon 5.8429 -7.7069 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[7]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[7] checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon 8.4135 -3.7971 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[8]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[8] checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon 6.2507 0.3634 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[9]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[9] checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0]
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon 8.6397 2.4190 -0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[10]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[10] checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0]
			REMOVE_BLIP first_blip_triathalon//PLAYERS STARTING COORDS
			ADD_BLIP_FOR_COORD racer_cp_x_triathalon[temp_int_triathalon] racer_cp_y_triathalon[temp_int_triathalon] racer_cp_z_triathalon[temp_int_triathalon] first_blip_triathalon
			SET_CAR_DENSITY_MULTIPLIER 0.5
			triathalonr[a] = scplayer


			
			playaz_triathalon = a
		ENDIF
		++ a
	ENDWHILE
	++ race_flag_triathalon



	DELETE_CAR stored_bike_triathalon
	DELETE_CAR stored_bike_triathalon2



	CREATE_CAR racers_car_model_triathalon[0] checkpoints_triathalon_x[get_on_bike] checkpoints_triathalon_y[get_on_bike] checkpoints_triathalon_z[get_on_bike] stored_bike_triathalon2
	SET_CAR_HEADING stored_bike_triathalon2 checkpoint_headings[get_on_bike] 


	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon2 5.0 -15.0 0.0 temp_x temp_y temp_z
	SET_CAR_HEADING	stored_bike_triathalon2 heading_triathalon2


	// players bike
	CREATE_CAR mtbike temp_x temp_y -100.0 racers_mtbike[playaz_triathalon] // creating the players bike  
	SET_CAR_HEADING racers_mtbike[playaz_triathalon] heading_triathalon2
	SET_CAR_WATERTIGHT racers_mtbike[playaz_triathalon] TRUE
	DELETE_CAR stored_bike_triathalon2 

	
	



   
 	IF IS_PLAYER_PLAYING player1

	  //	SET_PLAYER_CONTROL Player1 ON
	  	SET_CHAR_COORDINATES scplayer racer_cp_x_triathalon[playaz_triathalon] racer_cp_y_triathalon[playaz_triathalon] racer_cp_z_triathalon[playaz_triathalon] 
	 	SET_CHAR_HEADING scplayer checkpoint_headings[0]
	  //	TURN_CAR_TO_FACE_COORD racers_mtbike[playaz_triathalon] racer_cp_x_triathalon[0] racer_cp_y_triathalon[0]
	 //	SET_CHAR_HEADING scplayer 204.0																		   
		SET_CAMERA_BEHIND_PLAYER
	ENDIF  


	SWITCH_WIDESCREEN ON

	WAIT 500



   	DO_FADE 3000 FADE_IN 

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
	CLEAR_PRINTS





	//	PRINT_BIG MTROUTE 3000 6

 /*	IF triathalon_selection = 1
		PRINT_NOW MTROUT1 3000 6
	ENDIF


	IF triathalon_selection = 2
		PRINT_NOW MTROUT2 3000 6

	ENDIF


	IF triathalon_selection = 3
	   PRINT_NOW MTROUT3 3000 6
	ENDIF



	 	*/


	
ENDIF


IF race_flag_triathalon = 3
	IF NOT IS_CHAR_DEAD scplayer
	   //	IF NOT LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer racer_cp_x_triathalon[playaz_triathalon] racer_cp_y_triathalon[playaz_triathalon] racer_cp_z_triathalon[playaz_triathalon] 2.0 2.0 2.0 1
		
 



			SET_PLAYER_CONTROL player1 OFF
			SET_EVERYONE_IGNORE_PLAYER player1 TRUE
			SET_ALL_CARS_CAN_BE_DAMAGED FALSE
			PRINT_BIG RACES_4 1100 4
			ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_RACE_START_3

			temp_int_triathalon = total_triathalon - 1
			a = 0
	  	 	WHILE a < temp_int_triathalon
			
		  		IF IS_CHAR_DEAD	triathalonr[a]
					PRINT_NOW RACES25 5000 1 // you have been disqualifed
					GOTO mission_triathalon_failed

				ENDIF  	 
				
				
				++ a
			ENDWHILE	  

			

			LVAR_INT	triathalon_timer
			triathalon_timer = game_timer + 999
			++ race_flag_triathalon

	ENDIF
ENDIF

IF race_flag_triathalon = 4
	IF triathalon_timer <	game_timer
		PRINT_BIG RACES_5 1100 4
	 //	PRINT_BIG 3000 
		ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_RACE_START_2

		triathalon_timer = game_timer + 999
		++ race_flag_triathalon
	ENDIF
ENDIF

IF race_flag_triathalon = 5
	IF triathalon_timer <	game_timer
		PRINT_BIG RACES_6 1100 4
		ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_RACE_START_1
		triathalon_timer = game_timer + 999
		++ race_flag_triathalon
	ENDIF
ENDIF

IF race_flag_triathalon = 6
	IF triathalon_timer <	game_timer
		IF NOT IS_CHAR_DEAD	scplayer
			PRINT_BIG RACES_7 800 4
			ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_RACE_START_GO
   //			REPORT_MISSION_AUDIO_EVENT_AT_POSITION checkpoints_triathalon_x[0] checkpoints_triathalon_y[0] checkpoints_triathalon_z[0] SOUND_AIR_HORN
			SET_PLAYER_CONTROL player1 ON
			SET_EVERYONE_IGNORE_PLAYER player1 FALSE
			SET_ALL_CARS_CAN_BE_DAMAGED TRUE
			RESTORE_CAMERA
			//SET_CAMERA_BEHIND_PLAYER
			SWITCH_WIDESCREEN OFF

			//	PRINT_BIG MTROUTE 3000 6
			DISPLAY_CAR_NAMES FALSE

	   /*		IF triathalon_selection = 1
				CLEAR_THIS_PRINT MTROUT1 
			ENDIF


			IF triathalon_selection = 2
				CLEAR_THIS_PRINT MTROUT2 
			ENDIF


			IF triathalon_selection = 3
			   CLEAR_THIS_PRINT MTROUT3 
			ENDIF			  */



			race_timer = game_timer

			temp_int_triathalon = total_triathalon - 1
			a = 0
			WHILE a < temp_int_triathalon
				IF NOT IS_CAR_DEAD racers_mtbike[a]
					SET_CAR_ONLY_DAMAGED_BY_PLAYER racers_mtbike[a] FALSE
				ENDIF
				++ a
			ENDWHILE
			triathalon_timer = 0


			++ race_flag_triathalon	 
		ENDIF		
	ENDIF
ENDIF

IF race_flag_triathalon > 6
	WHILE a < total_triathalon
		IF NOT IS_CHAR_DEAD	scplayer
			IF NOT IS_CHAR_DEAD	triathalonr[a]
				IF race_flag_triathalon = 8
					IF NOT triathalonr[a] = scplayer

						IF cpcounter_triathalon[playaz_triathalon] = cpcounter_triathalon[a]
							LVAR_FLOAT player_distance_from_cp_triathalon racer_distance_from_cp_triathalon
							GET_CHAR_COORDINATES triathalonr[a] x y z
							GET_DISTANCE_BETWEEN_COORDS_2D x y racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_distance_from_cp_triathalon
							GET_CHAR_COORDINATES scplayer x y z
							GET_DISTANCE_BETWEEN_COORDS_2D x y racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] player_distance_from_cp

							IF player_distance_from_cp > racer_distance_from_cp_triathalon
								++ position_triathalon
							ENDIF
						ELSE
							IF cpcounter_triathalon[playaz_triathalon] < cpcounter_triathalon[a]
								++ position_triathalon
							ENDIF
						ENDIF
					///////////////////////////////////
					// STUCK CODE
					///////////////////////////////////
						IF NOT LOCATE_CHAR_ANY_MEANS_2D triathalonr[a] stuck_x[a] stuck_y[a] 2.0 2.0 0
							GET_CHAR_COORDINATES triathalonr[a] stuck_x[a] stuck_y[a] z
							
							IF cpcounter_triathalon[a] >= get_in_water
							AND cpcounter_triathalon[a] <= end_swim

								stuck_timer[a] = game_timer + 1500
							ELSE
								IF cpcounter_triathalon[a] = get_on_bike 
									stuck_timer[a] = game_timer + 1500
								ELSE

									stuck_timer[a] = game_timer + 4000
								ENDIF
							ENDIF
						ENDIF
						




						   // setting speeds for running
						IF cpcounter_triathalon[a] >= start_run
						AND cpcounter_triathalon[a] <= end_run
							IF IS_CHAR_PLAYING_ANIM triathalonr[a] sprint_panic 
								SET_CHAR_ANIM_SPEED triathalonr[a] sprint_panic set_opponents_run_speed
							ENDIF

							IF IS_CHAR_PLAYING_ANIM triathalonr[a] run_civi 
									SET_CHAR_ANIM_SPEED triathalonr[a] run_civi set_opponents_run_speed
							ENDIF

							IF IS_CHAR_PLAYING_ANIM triathalonr[a] WALK_civi 
								SET_CHAR_ANIM_SPEED triathalonr[a] WALK_civi set_opponents_run_speed
							ENDIF
						ENDIF

						IF LOCATE_CHAR_ANY_MEANS_2D triathalonr[a] stuck_x[a] stuck_y[a] 3.0 3.0 0
							IF stuck_timer[a] < game_timer
								IF cpcounter_triathalon[a] >= get_in_water
								AND cpcounter_triathalon[a] <= end_swim
									IF IS_CHAR_IN_WATER triathalonr[a]
										x2 = racer_cp_x_triathalon[a] + offset_swimming_opponents_x[a]
										y2 = racer_cp_y_triathalon[a] + offset_swimming_opponents_y[a]

										IF cpcounter_triathalon[a] > get_in_water			
 
											IF NOT cpcounter_triathalon[a] < 3 
										 		GET_SCRIPT_TASK_STATUS triathalonr[a] TASK_SWIM_TO_COORD task_status_triathlon[a]
										 		IF task_status_triathlon[a] = FINISHED_TASK
													   	TASK_SWIM_TO_COORD triathalonr[a] x2 y2 racer_cp_z_triathalon[a]
													SET_SWIM_SPEED triathalonr[a] set_opponents_swim_speed
										   		ENDIF
											ELSE
											   	TASK_SWIM_TO_COORD triathalonr[a] x2 y2 racer_cp_z_triathalon[a]
												SET_SWIM_SPEED triathalonr[a] 2.2
											ENDIF
											
										ELSE

											IF NOT IS_CHAR_ON_SCREEN triathalonr[a]
												last_check_point = cpcounter_triathalon[a] - 1

												IF NOT IS_POINT_ON_SCREEN racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] 10.0
													SET_CHAR_COORDINATES triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a]
		 	  									   	SET_CHAR_HEADING triathalonr[a] checkpoint_headings[a] 
//	TURN_CHAR_TO_FACE_COORD triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] //racer_cp_z_triathalon[a]
													CLEAR_CHAR_TASKS triathalonr[a] 
											 		GET_SCRIPT_TASK_STATUS triathalonr[a] TASK_SWIM_TO_COORD task_status_triathlon[a]
											 		IF task_status_triathlon[a] = FINISHED_TASK
														   	TASK_SWIM_TO_COORD triathalonr[a] x2 y2 racer_cp_z_triathalon[a]
														SET_SWIM_SPEED triathalonr[a] set_opponents_swim_speed
											   		ENDIF
	   
	   
												ENDIF
											ENDIF


										ENDIF
								   	ELSE
										IF cpcounter_triathalon[a] = end_swim
									 	//  	TASK_GO_STRAIGHT_TO_COORD triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a PEDMOVE_SPRINT -2
										ENDIF
									ENDIF
								ENDIF


								IF cpcounter_triathalon[a] >= start_bike
								AND cpcounter_triathalon[a] <= end_bike
 								   IF NOT IS_CAR_DEAD racers_mtbike[a]
									//	IF NOT IS_CHAR_IN_WATER triathalonr[a]
											SWITCH flag_make_guys_enter_bike[a]

												CASE 0
													OPEN_SEQUENCE_TASK seq_get_on_bike	
												   		TASK_GO_STRAIGHT_TO_COORD -1 coord_from_swim_to_bike_x coord_from_swim_to_bike_y coord_from_swim_to_bike_z PEDMOVE_SPRINT -2
														TASK_ENTER_CAR_AS_DRIVER -1 racers_mtbike[a] -2
													   //	TASK_CAR_DRIVE_TO_COORD -1 -1 racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] set_opponents_cycle_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
													CLOSE_SEQUENCE_TASK seq_get_on_bike
													PERFORM_SEQUENCE_TASK triathalonr[a] seq_get_on_bike 
													CLEAR_SEQUENCE_TASK seq_get_on_bike 	
													flag_make_guys_enter_bike[a] = 1
												BREAK		//	jump to line after ENDSWITCH

												CASE 1
													IF NOT IS_CHAR_ON_SCREEN triathalonr[a]
														IF NOT IS_POINT_ON_SCREEN racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] 10.0
															IF NOT IS_CHAR_IN_CAR triathalonr[a] racers_mtbike[a]  
																IF NOT IS_CAR_ON_SCREEN racers_mtbike[a]
																	IF NOT IS_CHAR_IN_ANY_CAR triathalonr[a]
																		GET_DRIVER_OF_CAR racers_mtbike[a] char_person_on_bike
																		IF char_person_on_bike = -1
																			last_check_point = cpcounter_triathalon[a] - 1

																		 //	CLEAR_CHAR_TASKS triathalonr[a]
																			WARP_CHAR_INTO_CAR triathalonr[a] racers_mtbike[a]
																			//TURN_CAR_TO_FACE_COORD racers_mtbike[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] //racer_cp_z_triathalon[a]
																			TASK_CAR_DRIVE_TO_COORD triathalonr[a] -1 racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] set_opponents_cycle_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
																			flag_make_guys_enter_bike[a] = 2
																		ENDIF
																	ENDIF
																ENDIF
															ENDIF
														ENDIF
													ENDIF
												BREAK		//	jump to line after ENDSWITCH




												DEFAULT
													IF NOT IS_CHAR_ON_SCREEN triathalonr[a]
														IF NOT IS_POINT_ON_SCREEN racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] 10.0
															IF NOT IS_CAR_ON_SCREEN racers_mtbike[a]

																GET_DRIVER_OF_CAR racers_mtbike[a] char_person_on_bike
																IF char_person_on_bike = -1

																   	GET_SCRIPT_TASK_STATUS triathalonr[a] TASK_CAR_DRIVE_TO_COORD task_status_triathlon[a]
																	last_check_point = cpcounter_triathalon[a] - 1

																	IF task_status_triathlon[a] = FINISHED_TASK
																//	OR NOT task_status_triathlon[a] = PERFORMING_TASK

																		IF NOT IS_CHAR_IN_CAR triathalonr[a] racers_mtbike[a] 
	  																		GET_DRIVER_OF_CAR racers_mtbike[a] char_person_on_bike
																			IF char_person_on_bike = -1
																				WARP_CHAR_INTO_CAR triathalonr[a] racers_mtbike[a]
																			  //	TURN_CAR_TO_FACE_COORD racers_mtbike[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] //racer_cp_z_triathalon[a]
																				TASK_CAR_DRIVE_TO_COORD triathalonr[a] -1 racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] set_opponents_cycle_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
																			ENDIF
																		ELSE
																			SET_CHAR_COORDINATES triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a]
																			TURN_CAR_TO_FACE_COORD racers_mtbike[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] //racer_cp_z_triathalon[a]
																			TASK_CAR_DRIVE_TO_COORD triathalonr[a] -1 racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] set_opponents_cycle_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
																		ENDIF
											   						ENDIF
																ENDIF
															ENDIF
														ENDIF
													ENDIF
												BREAK												

											ENDSWITCH
									ENDIF
							 ENDIF


							   	IF cpcounter_triathalon[a] >= start_run
								AND cpcounter_triathalon[a] <= end_run

									IF NOT IS_CHAR_ON_SCREEN triathalonr[a]
										IF NOT IS_POINT_ON_SCREEN racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] 10.0
											last_check_point = cpcounter_triathalon[a] - 1

											SET_CHAR_COORDINATES triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a]
										ENDIF
									ENDIF
			 					ENDIF		

							ENDIF
						ENDIF // END STUCKS


						IF IS_CHAR_IN_ANY_CAR triathalonr[a]
							temp_int_triathalon = cpcounter_triathalon[playaz_triathalon] + 5
							STORE_CAR_CHAR_IS_IN_NO_SAVE triathalonr[a] stored_bike_triathalon
						// make opponents go faster if player is winning

						ENDIF

						// Warping guys if they fall 2 checkpoints behind on bike
						
				   		r = 0
						e = 0
						WHILE r < total_triathalon
							LVAR_INT opponent_cpcounter_triathalon_minus4
							opponent_cpcounter_triathalon_minus4 = cpcounter_triathalon[r] - 2
							IF cpcounter_triathalon[a] < opponent_cpcounter_triathalon_minus4
								++ e
							ENDIF
							++ r
						ENDWHILE

						IF e > 0
							last_check_point = cpcounter_triathalon[a] - 1

							IF NOT IS_CHAR_DEAD triathalonr[a]
								IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer triathalonr[a] 30.0 30.0 30.0 0
									IF NOT IS_CHAR_ON_SCREEN triathalonr[a]
										IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] 4.0 4.0 3.0
											IF NOT IS_POINT_ON_SCREEN racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] 4.0
												SET_CHAR_COORDINATES triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a]
												
												CLEAR_CHAR_TASKS triathalonr[a]
												GET_SCRIPT_TASK_STATUS triathalonr[a] TASK_CAR_DRIVE_TO_COORD task_status_triathlon[a]
												IF task_status_triathlon[a] = FINISHED_TASK
											  //	OR NOT task_status_triathlon[a] = PERFORMING_TASK

													IF cpcounter_triathalon[a] >= start_bike
													AND cpcounter_triathalon[a] <= end_bike
														IF IS_CHAR_IN_ANY_CAR triathalonr[a]
															STORE_CAR_CHAR_IS_IN_NO_SAVE triathalonr[a] stored_bike_triathalon
														 //	TURN_CAR_TO_FACE_COORD stored_bike_triathalon racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] //racer_cp_z_triathalon[a]
														 	SET_CAR_HEADING stored_bike_triathalon checkpoint_headings[a]
														 	TASK_CAR_DRIVE_TO_COORD triathalonr[a] -1 racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] set_opponents_cycle_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
															 

														ELSE
															IF NOT IS_CAR_DEAD racers_mtbike[a]
																IF NOT IS_CHAR_IN_CAR triathalonr[a] racers_mtbike[a]
																	IF NOT IS_CHAR_IN_CAR scplayer racers_mtbike[a]
	  																	GET_DRIVER_OF_CAR racers_mtbike[a] char_person_on_bike
																		IF char_person_on_bike = -1
 
																		  	CLEAR_CHAR_TASKS triathalonr[a]
																			WARP_CHAR_INTO_CAR triathalonr[a] racers_mtbike[a]
																		//	TURN_CAR_TO_FACE_COORD racers_mtbike[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] //racer_cp_z_triathalon[a]
																			SET_CAR_HEADING racers_mtbike[a] checkpoint_headings[a]
																			TASK_CAR_DRIVE_TO_COORD triathalonr[a] -1 racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] set_opponents_cycle_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
																		ENDIF
																	ENDIF
																ENDIF
															ENDIF
														ENDIF
													ENDIF

												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF  // IF RACE FLAG IS 8

			ELSE

				// IF player shoots char, mission failed

					IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR triathalonr[a] scplayer
					AND IS_CHAR_DEAD triathalonr[a]
						IF NOT IS_CAR_DEAD racers_mtbike[playaz_triathalon]	
					   		IF NOT HAS_CHAR_BEEN_DAMAGED_BY_CAR triathalonr[a] racers_mtbike[playaz_triathalon]
								PRINT_NOW RACES25 5000 1 // you have been disqualifed
								GOTO mission_triathalon_failed
							ENDIF
						ENDIF
					ENDIF
			ENDIF 	   

			IF NOT IS_CHAR_DEAD triathalonr[a]

				/////////////////////////////////////////////
				// checking if player abandons the race
				/////////////////////////////////////////////
					
			   	IF triathalonr[a] = scplayer
				//	cpcounter_triathalon[11] = cpcounter_triathalon[a]	

				 	IF out_of_car_flag_triathalon = 0
					  //	IF cpcounter_triathalon[a] >= start_bike		  // CYCLING
					   //	AND cpcounter_triathalon[a] <= end_bike
						 //	IF IS_CHAR_IN_ANY_CAR triathalonr[a]
								IF NOT LOCATE_CHAR_ANY_MEANS_3D triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] 600.0 600.0 600.0 0
									PRINT_NOW RACES25 5000 1 //"~r~disqualified!"
								 	GOTO mission_triathalon_failed	
								ENDIF
					   //		ENDIF
					 //	ELSE
					 //		IF NOT cpcounter_triathalon[a] =  end_run
					  //			IF NOT LOCATE_CHAR_ANY_MEANS_3D triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] 1000.0 1000.0 1000.0 0
					   //				PRINT_NOW RACES25 5000 1 //"~r~disqualified!"
					  ////				GOTO mission_triathalon_failed	
							 //	ENDIF
						 //	ENDIF
					  //	ENDIF	
				  	ENDIF
				ENDIF							  


			  //////////////////////////////////////////////////
			  //	MAKING RACERS RUN TO NEXT CHECKPOINT 
			  ///////////////////////////////////////////////////


					IF cpcounter_triathalon[a] > end_run
						IF flag_make_runner_get_past_finishingline[a] = 0
							OPEN_SEQUENCE_TASK seq_get_on_bike	

								TASK_GO_STRAIGHT_TO_COORD -1 point_after_finish_line_x point_after_finish_line_y point_after_finish_line_z PEDMOVE_SPRINT -2
								TASK_TIRED  -1 -2

							CLOSE_SEQUENCE_TASK seq_get_on_bike
							PERFORM_SEQUENCE_TASK triathalonr[a] seq_get_on_bike 
							CLEAR_SEQUENCE_TASK seq_get_on_bike 
							flag_make_runner_get_past_finishingline[a] = 1

						ENDIF

					ENDIF


					IF cpcounter_triathalon[a] >= start_run	  // running
					AND cpcounter_triathalon[a] <= end_run
					//	CLEAR_AREA racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] 1000.0 TRUE 
						IF LOCATE_CHAR_ANY_MEANS_3D triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] 8.0 8.0 5.0 0
							IF cpcounter_triathalon[a] = start_run
								GOTO passed_a_checkpoint_triathalon
							ELSE
								GET_SCRIPT_TASK_STATUS triathalonr[a] TASK_FOLLOW_PATH_NODES_TO_COORD_WITH_RADIUS task_status_triathlon[a]
								IF task_status_triathlon[a] = FINISHED_TASK	
							   //	OR NOT task_status_triathlon[a] = PERFORMING_TASK
									GOTO passed_a_checkpoint_triathalon
								ENDIF
							ENDIF


							IF cpcounter_triathalon[a] = total_checkpoints_triathalon_triathalon
								IF triathalonr[a] = scplayer

									position_triathalon++
									IF position_triathalon <= 1
										GOTO mission_triathalon_passed
									ELSE
										PRINT_NOW RACES_8 5000 1  // Loser!
										GOTO mission_triathalon_failed	
									ENDIF

								ELSE
									TASK_CAR_TEMP_ACTION triathalonr[a] -1 TEMPACT_HANDBRAKESTRAIGHT 2000000
									last_check_point = cpcounter_triathalon[a] - 1
									IF NOT IS_POINT_ON_SCREEN racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] 30.0
										IF NOT IS_CAR_DEAD stored_bike_triathalon 
											TURN_CAR_TO_FACE_COORD stored_bike_triathalon checkpoints_triathalon_x[a] checkpoints_triathalon_y[a]// MUST PLACE checkpoint[1] STRAIGHT AHEAD FROM START
										ENDIF
									ENDIF
								ENDIF	
							ENDIF

						ENDIF  
					ENDIF

					IF cpcounter_triathalon[a] >= end_swim		  // CYCLING
					AND cpcounter_triathalon[a] <= end_bike
						IF triathalonr[a] = scplayer
							IF cpcounter_triathalon[a] >= START_BIKE
							AND cpcounter_triathalon[a] <= end_bike
								IF LOCATE_CHAR_IN_CAR_3D triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] 40.0 40.0 40.0 0
									temp_int2_tri = start_bike + 1
									IF cpcounter_triathalon[a] > temp_int2_tri
										GET_SCRIPT_TASK_STATUS triathalonr[a] TASK_CAR_DRIVE_TO_COORD task_status_triathlon[a]
										IF task_status_triathlon[a] = FINISHED_TASK
									  //	OR NOT task_status_triathlon[a] = PERFORMING_TASK
											GOTO passed_a_checkpoint_triathalon
										ENDIF
									ELSE
										GOTO passed_a_checkpoint_triathalon
									ENDIF
								ENDIF

							ELSE

								IF LOCATE_CHAR_ANY_MEANS_3D triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] 8.0 8.0 5.0 0
			   						IF cpcounter_triathalon[a] < 2
										GET_SCRIPT_TASK_STATUS triathalonr[a] TASK_SWIM_TO_COORD task_status_triathlon[a]
										IF task_status_triathlon[a] = FINISHED_TASK	
									   //	OR NOT task_status_triathlon[a] = PERFORMING_TASK
											GOTO passed_a_checkpoint_triathalon
										ENDIF
									ELSE
										GOTO passed_a_checkpoint_triathalon
									ENDIF
								ENDIF




							ENDIF
						ELSE
						  //	IF IS_CHAR_IN_ANY_CAR triathalonr[a] 
							IF LOCATE_CHAR_IN_CAR_3D triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] 40.0 40.0 40.0 0
								temp_int2_tri = start_bike + 1
								IF cpcounter_triathalon[a] > temp_int2_tri
									GET_SCRIPT_TASK_STATUS triathalonr[a] TASK_CAR_DRIVE_TO_COORD task_status_triathlon[a]
									IF task_status_triathlon[a] = FINISHED_TASK
								   //	OR NOT task_status_triathlon[a] = PERFORMING_TASK
										GOTO passed_a_checkpoint_triathalon
									ENDIF
								ELSE
									GOTO passed_a_checkpoint_triathalon
								ENDIF
							ENDIF
						//	ENDIF

					
						ENDIF
					ENDIF

					
				 //	temp_int2_tri = start_bike + 1

					IF cpcounter_triathalon[a] >= get_in_water		  // SWIMMING
					AND cpcounter_triathalon[a] < end_swim
						IF LOCATE_CHAR_ANY_MEANS_3D triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] 8.0 8.0 5.0 0
	   						IF cpcounter_triathalon[a] < 2
								GET_SCRIPT_TASK_STATUS triathalonr[a] TASK_SWIM_TO_COORD task_status_triathlon[a]
								IF task_status_triathlon[a] = FINISHED_TASK	
							   //	OR NOT task_status_triathlon[a] = PERFORMING_TASK
									GOTO passed_a_checkpoint_triathalon
								ENDIF
							ELSE
								GOTO passed_a_checkpoint_triathalon
							ENDIF
						ENDIF
					ENDIF


				ENDIF


	 
   		exit_locate_loop_triathalon:
				IF triathalonr[a] = scplayer

 
		////////////////////////////////
		//OUT OF CAR TIMER FOR PLAYER
		////////////////////////////////

				   	IF cpcounter_triathalon[a] >= start_bike
					AND cpcounter_triathalon[a] <= end_bike

						IF NOT IS_CAR_DEAD racers_mtbike[playaz_triathalon]
							IF NOT IS_CHAR_IN_CAR scplayer racers_mtbike[playaz_triathalon]
								IF out_of_car_flag_triathalon = 0
									DELETE_CHECKPOINT cp_marker_triathalon
									REMOVE_BLIP	first_blip_triathalon 
								   //	REMOVE_BLIP	second_blip_triathalon

									IF NOT IS_CAR_DEAD racers_mtbike[playaz_triathalon]
								  		ADD_BLIP_FOR_CAR racers_mtbike[playaz_triathalon] first_blip_triathalon
										SET_BLIP_AS_FRIENDLY first_blip_triathalon TRUE
									ENDIF
									stored_checkpoint_when_not_on_bike = a

									racer_cp_y_triathalon[a] += 1000.0
		  
							   		out_of_car_timer = game_timer + 25400
									out_of_car_flag_triathalon = 1
								ENDIF

								seconds = out_of_car_timer - game_timer
								seconds /= 1000

								IF seconds < 1
									seconds = 0
								ENDIF

								IF out_of_car_timer < game_timer
									PRINT_NOW RACES20 5000 1//~r~You have been disqualified for leaving your car.
									GOTO mission_triathalon_failed
									RETURN
								ENDIF

							 //	PRINT_WITH_NUMBER_NOW RACES50 seconds 200 1	//You have ~1~ seconds to return to your car.
								ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS FALSE
								IF seconds = 1
									PRINT_WITH_NUMBER_NOW RACE_51 seconds 200 1	//You have ~1~ second to return to your car.
								ELSE
									PRINT_WITH_NUMBER_NOW RACES50 seconds 200 1	//You have ~1~ seconds to return to your car.
								ENDIF

								

								IF cpcounter_triathalon[a] = total_checkpoints_triathalon_triathalon
									IF triathalonr[a] = scplayer
  

										position_triathalon++
										IF position_triathalon <= 1
											GOTO mission_triathalon_passed
										ELSE
											PRINT_NOW RACES_8 5000 1  // Loser!
											GOTO mission_triathalon_failed	
										ENDIF

									ENDIF
								ENDIF
					 
							ELSE
								IF out_of_car_flag_triathalon = 1
									CLEAR_THIS_PRINT RACES50
								   	REMOVE_BLIP	first_blip_triathalon
									racer_cp_y_triathalon[a] -= 1000.0
									c = 0
									temp_int_triathalon = total_racers_mtbike - 1
									ADD_BLIP_FOR_COORD racer_cp_x_triathalon[stored_checkpoint_when_not_on_bike] racer_cp_y_triathalon[stored_checkpoint_when_not_on_bike] racer_cp_z_triathalon[stored_checkpoint_when_not_on_bike] first_blip_triathalon
									CHANGE_BLIP_COLOUR first_blip_triathalon RED
									CHANGE_BLIP_DISPLAY first_blip_triathalon BLIP_ONLY
									CHANGE_BLIP_SCALE first_blip_triathalon 3
									IF cpcounter_triathalon[stored_checkpoint_when_not_on_bike] = total_checkpoints_triathalon_triathalon_minus_1
								   	//	AND lap_cpcounter[a] = total_laps_minus_1
										CREATE_CHECKPOINT CHECKPOINT_ENDTUBE racer_cp_x_triathalon[stored_checkpoint_when_not_on_bike] racer_cp_y_triathalon[stored_checkpoint_when_not_on_bike] racer_cp_z_triathalon[stored_checkpoint_when_not_on_bike] 0.0 0.0 0.0 6.0 cp_marker_triathalon
									ELSE
										temp_int_triathalon = cpcounter_triathalon[stored_checkpoint_when_not_on_bike]
										++ temp_int_triathalon
										
										IF cpcounter_triathalon[stored_checkpoint_when_not_on_bike] = total_checkpoints_triathalon_triathalon_minus_1
											temp_int_triathalon = 1
										ENDIF

										CREATE_CHECKPOINT CHECKPOINT_TUBE racer_cp_x_triathalon[stored_checkpoint_when_not_on_bike] racer_cp_y_triathalon[stored_checkpoint_when_not_on_bike] racer_cp_z_triathalon[stored_checkpoint_when_not_on_bike] second_cp_triathalonx second_cp_triathalony second_cp_triathalonz 6.0 cp_marker_triathalon
									ENDIF
									out_of_car_flag_triathalon = 0
								ENDIF

							ENDIF



						ELSE // if car is dead
						   	PRINT_NOW RACES24 5000 1//~r~You wrecked yer bike
						   	GOTO mission_triathalon_failed
						   	RETURN

						ENDIF  // if the player is in cycle mode
					ENDIF

				
					// running
					temp_int2_tri = start_run +1
					IF cpcounter_triathalon[a] >= start_run
					AND cpcounter_triathalon[a] < temp_int2_tri
						//IF NOT IS_CAR_DEAD racers_mtbike[playaz_triathalon]
							IF IS_CHAR_IN_ANY_CAR scplayer 
								IF out_of_car_flag_triathalon = 0
									STORE_CAR_CHAR_IS_IN scplayer racers_mtbike[playaz_triathalon]
									DELETE_CHECKPOINT cp_marker_triathalon
									REMOVE_BLIP	first_blip_triathalon 
								  //	REMOVE_BLIP	second_blip_triathalon

									IF NOT IS_CAR_DEAD racers_mtbike[playaz_triathalon]
								  		ADD_BLIP_FOR_CAR racers_mtbike[playaz_triathalon] first_blip_triathalon
										SET_BLIP_AS_FRIENDLY first_blip_triathalon TRUE
									ENDIF
									stored_checkpoint_when_not_on_bike = a

									racer_cp_y_triathalon[a] += 1000.0

							   		out_of_car_timer = game_timer + 25400
									out_of_car_flag_triathalon = 1
								ENDIF

								seconds = out_of_car_timer - game_timer
								seconds /= 1000
								IF seconds < 1
									seconds = 0
								ENDIF
								IF out_of_car_timer < game_timer
									PRINT_NOW RACES25 5000 1//~r~You have been disqualified .
									GOTO mission_triathalon_failed
									RETURN
								ENDIF

								ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS FALSE

								IF seconds = 1
									PRINT_WITH_NUMBER_NOW RACE_49 seconds 200 1	//You have ~1~ second to return to your car.
								ELSE
									PRINT_WITH_NUMBER_NOW RACES48 seconds 200 1	//You have ~1~ seconds to return to your car.
								ENDIF


						  /*		IF cpcounter_triathalon[a] = total_checkpoints_triathalon_triathalon
									IF triathalonr[a] = scplayer
										position_triathalon++
										IF position_triathalon = 1
											GOTO mission_triathalon_passed
										ELSE
											PRINT_NOW RACES25 5000 1 //"~r~You failed to win the race!"
											GOTO mission_triathalon_failed	
										ENDIF

									ENDIF
								ENDIF	*/
		 
							ELSE
								GOSUB triathalon_create_checkpoint
							ENDIF
						//ENDIF
					ENDIF	// END RUNNING


					// running	2
					temp_int2_tri = start_run +1
					IF cpcounter_triathalon[a] >= temp_int2_tri
					AND cpcounter_triathalon[a] <= end_run
						//IF NOT IS_CAR_DEAD racers_mtbike[playaz_triathalon]
							IF IS_CHAR_IN_ANY_CAR scplayer 
								IF out_of_car_flag_triathalon = 0
									STORE_CAR_CHAR_IS_IN scplayer racers_mtbike[playaz_triathalon]
									DELETE_CHECKPOINT cp_marker_triathalon
									REMOVE_BLIP	first_blip_triathalon 
								  //	REMOVE_BLIP	second_blip_triathalon

									IF NOT IS_CAR_DEAD racers_mtbike[playaz_triathalon]
								  		ADD_BLIP_FOR_CAR racers_mtbike[playaz_triathalon] first_blip_triathalon
										SET_BLIP_AS_FRIENDLY first_blip_triathalon TRUE
									ENDIF
									stored_checkpoint_when_not_on_bike = a

									racer_cp_y_triathalon[a] += 1000.0

							   		out_of_car_timer = game_timer + 5000
									out_of_car_flag_triathalon = 1
								ENDIF

								seconds = out_of_car_timer - game_timer
								seconds /= 1000
								IF seconds < 1
									seconds = 0
								ENDIF
								IF out_of_car_timer < game_timer
									PRINT_NOW RACES25 5000 1//~r~You have been disqualified for leaving your car.
									GOTO mission_triathalon_failed
									RETURN
								ENDIF

								ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS FALSE

								IF seconds = 1
									PRINT_WITH_NUMBER_NOW RACE_49 seconds 200 1	//You have ~1~ second to return to your car.
								ELSE
									PRINT_WITH_NUMBER_NOW RACES48 seconds 200 1	//You have ~1~ seconds to return to your car.
								ENDIF

							ELSE
								IF out_of_car_flag_triathalon = 1
									CLEAR_THIS_PRINT RACES48
								   	REMOVE_BLIP	first_blip_triathalon
									racer_cp_y_triathalon[a] -= 1000.0
									c = 0
									temp_int_triathalon = total_racers_mtbike - 1
									ADD_BLIP_FOR_COORD racer_cp_x_triathalon[stored_checkpoint_when_not_on_bike] racer_cp_y_triathalon[stored_checkpoint_when_not_on_bike] racer_cp_z_triathalon[stored_checkpoint_when_not_on_bike] first_blip_triathalon
									CHANGE_BLIP_COLOUR first_blip_triathalon RED
									CHANGE_BLIP_DISPLAY first_blip_triathalon BLIP_ONLY
									CHANGE_BLIP_SCALE first_blip_triathalon 3
									IF cpcounter_triathalon[stored_checkpoint_when_not_on_bike] = total_checkpoints_triathalon_triathalon_minus_1
								   //	AND lap_cpcounter[a] = total_laps_minus_1
										CREATE_CHECKPOINT CHECKPOINT_ENDTUBE racer_cp_x_triathalon[stored_checkpoint_when_not_on_bike] racer_cp_y_triathalon[stored_checkpoint_when_not_on_bike] racer_cp_z_triathalon[stored_checkpoint_when_not_on_bike] 0.0 0.0 0.0 6.0 cp_marker_triathalon
									ELSE
										temp_int_triathalon = cpcounter_triathalon[stored_checkpoint_when_not_on_bike]
										++ temp_int_triathalon
										
										IF cpcounter_triathalon[stored_checkpoint_when_not_on_bike] = total_checkpoints_triathalon_triathalon_minus_1
											temp_int_triathalon = 1
										ENDIF

										CREATE_CHECKPOINT CHECKPOINT_TUBE racer_cp_x_triathalon[stored_checkpoint_when_not_on_bike] racer_cp_y_triathalon[stored_checkpoint_when_not_on_bike] racer_cp_z_triathalon[stored_checkpoint_when_not_on_bike] second_cp_triathalonx second_cp_triathalony second_cp_triathalonz 6.0 cp_marker_triathalon
									ENDIF
									out_of_car_flag_triathalon = 0
								ENDIF

							ENDIF
						//ENDIF
					ENDIF	// END RUNNING


				ENDIF // is racer is player

			////////////////////////////////////////////////////////


			ENDIF  // IF NOT IS PLAYER DEAD
		++a
	ENDWHILE

	DRAW_RECT x_pos[6] y_pos[6] x_scale[6] y_scale[6] 0 0 0 255 
	DRAW_RECT x_pos[5] y_pos[5] x_scale[5] y_scale[5] 134 155 184 255 
	DRAW_RECT x_pos[0] y_pos[0] x_scale[0] y_scale[0] 0 0 0 255 

	++position_triathalon

	GOSUB triathalon_text

	SET_TEXT_SCALE x_scale[1] y_scale[1]

	IF position_triathalon = 1
		DISPLAY_TEXT x_pos[1] y_pos[1] ST
	ENDIF

	IF position_triathalon = 2
		DISPLAY_TEXT x_pos[1] y_pos[1] ND
	ENDIF

	IF position_triathalon = 3
		DISPLAY_TEXT x_pos[1] y_pos[1] RD
	ENDIF

	IF position_triathalon > 3
		DISPLAY_TEXT x_pos[1] y_pos[1] TH
	ENDIF

    IF position_triathalon <= 7
		//IF flag_boosting_opponents_speed = 0
			set_opponents_cycle_speed = opponents_cycle_speed_fast 	
			set_opponents_swim_speed = opponents_swim_speed_fast
			set_opponents_run_speed = opponents_run_speed_fast  

	   		IF triathalon_selection = 2
				IF cpcounter_triathalon[a] = 19
				OR cpcounter_triathalon[a] = 20
				OR cpcounter_triathalon[a] = 37
				OR cpcounter_triathalon[a] = 38
					set_opponents_cycle_speed = 20.0 
				ENDIF
			ENDIF 

	   		IF triathalon_selection = 2
				IF cpcounter_triathalon[a] = 69
				OR cpcounter_triathalon[a] = 70
				OR cpcounter_triathalon[a] = 71
					set_opponents_cycle_speed = 20.0 
				ENDIF
			ENDIF 

	   		IF triathalon_selection = 2
				IF cpcounter_triathalon[a] = 89
				OR cpcounter_triathalon[a] = 90
				OR cpcounter_triathalon[a] = 91
					set_opponents_cycle_speed = 20.0 
				ENDIF
			ENDIF 

		  //	flag_boosting_opponents_speed = 1
	   //	ENDIF
	ENDIF

	IF position_triathalon > 7
	AND position_triathalon < 9 

	  //	IF flag_boosting_opponents_speed = 1
			set_opponents_cycle_speed = opponents_cycle_speed_slow
			set_opponents_swim_speed = opponents_swim_speed_slow
			set_opponents_run_speed = opponents_run_speed_slow
		//	flag_boosting_opponents_speed = 0
	   //	ENDIF

	   		IF triathalon_selection = 2
				IF cpcounter_triathalon[a] = 19
				OR cpcounter_triathalon[a] = 20
				OR cpcounter_triathalon[a] = 37
				OR cpcounter_triathalon[a] = 38
					set_opponents_cycle_speed = 20.0 
				ENDIF
			ENDIF 

	   		IF triathalon_selection = 2
				IF cpcounter_triathalon[a] = 69
				OR cpcounter_triathalon[a] = 70
				OR cpcounter_triathalon[a] = 71
					set_opponents_cycle_speed = 20.0 
				ENDIF
			ENDIF 

	   		IF triathalon_selection = 2
				IF cpcounter_triathalon[a] = 91
				OR cpcounter_triathalon[a] = 92
				OR cpcounter_triathalon[a] = 93
					set_opponents_cycle_speed = 20.0 
				ENDIF
			ENDIF 


		
	ENDIF


	IF position_triathalon =9
 
	  //	IF flag_boosting_opponents_speed = 1
			set_opponents_cycle_speed = opponents_cycle_speed_really_slow
			set_opponents_swim_speed = opponents_swim_speed_really_slow
			set_opponents_run_speed = opponents_run_speed_really_slow
		//	flag_boosting_opponents_speed = 0
	   //	ENDIF
		
	ENDIF

	GOSUB triathalon_text

	SET_TEXT_SCALE x_scale[2] y_scale[2]
	SET_TEXT_CENTRE ON
	DISPLAY_TEXT_WITH_NUMBER x_pos[2] y_pos[2] NUMBER position_triathalon 

	GOSUB triathalon_text 
	SET_TEXT_SCALE x_scale[3] y_scale[3]
	DISPLAY_TEXT_WITH_NUMBER x_pos[3] y_pos[3] OUT_OF total_racers_mtbike 
	GET_GAME_TIMER game_timer 

	LVAR_INT nElapsedSeconds
	nElapsedSeconds = game_timer - race_timer
	nElapsedSeconds /= 1000 

	mins = nElapsedSeconds / 60 
	temp_int_triathalon = mins * 60 
	seconds = nElapsedSeconds - temp_int_triathalon
	GOSUB triathalon_text 
	SET_TEXT_CENTRE ON
	SET_TEXT_SCALE x_scale[4] y_scale[4]
	IF seconds > 9  
		DISPLAY_TEXT_WITH_2_NUMBERS x_pos[4] y_pos[4] TIME mins seconds 
	ELSE
		DISPLAY_TEXT_WITH_2_NUMBERS x_pos[4] y_pos[4] TIME_0 mins seconds 
	ENDIF
ENDIF

GOTO mission_triathalon_loop

triathalon_text:

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


passed_a_checkpoint_triathalon:

	IF race_flag_triathalon = 7
		race_flag_triathalon = 8
	ENDIF
					
	++ cpcounter_triathalon[a]
	
	temp_int_triathalon = cpcounter_triathalon[a]
	
	racer_cp_x_triathalon[a] = checkpoints_triathalon_x[temp_int_triathalon]
	racer_cp_y_triathalon[a] = checkpoints_triathalon_y[temp_int_triathalon]
	racer_cp_z_triathalon[a] = checkpoints_triathalon_z[temp_int_triathalon]
	
	IF triathalonr[a] = scplayer

			IF cpcounter_triathalon[a] >= total_checkpoints_triathalon_triathalon

				position_triathalon++
				IF position_triathalon <= 1
					GOTO mission_triathalon_passed
				ELSE
					PRINT_NOW RACES_8 5000 1  // Loser!
					GOTO mission_triathalon_failed	
				ENDIF
			ENDIF




		// help text during transitions	

		IF flag_help_text_triath = 0 
		// run into the pool and swim
			IF cpcounter_triathalon[a] = start_swim
				PRINT_HELP TRAI2
				flag_help_text_triath = 1
			ENDIF
		ENDIF

		IF flag_help_text_triath = 1 
		// get on the bike
			IF cpcounter_triathalon[a] = get_on_bike
				PRINT_HELP TRAI3
				flag_help_text_triath = 2
			ENDIF
		ENDIF

		IF flag_help_text_triath = 2 
		// get off the bike and run
			IF cpcounter_triathalon[a] = start_run
				PRINT_HELP TRAI4
				flag_help_text_triath = 3
			ENDIF
		ENDIF


		ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
		REMOVE_BLIP first_blip_triathalon



		++ temp_int_triathalon
		
		LVAR_FLOAT second_cp_triathalonx second_cp_triathalony second_cp_triathalonz
		second_cp_triathalonx = checkpoints_triathalon_x[temp_int_triathalon]
		second_cp_triathalony = checkpoints_triathalon_y[temp_int_triathalon]
		second_cp_triathalonz = checkpoints_triathalon_z[temp_int_triathalon]
		
		ADD_BLIP_FOR_COORD racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] first_blip_triathalon
		CHANGE_BLIP_COLOUR first_blip_triathalon RED
		CHANGE_BLIP_DISPLAY first_blip_triathalon BLIP_ONLY
		CHANGE_BLIP_SCALE first_blip_triathalon 3
		IF race_flag_triathalon = 1 //MAKE THE SCRIPT COMPILE
			CREATE_CHECKPOINT CHECKPOINT_TUBE racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] second_cp_triathalonx second_cp_triathalony second_cp_triathalonz 6.0 cp_marker_triathalon
		ENDIF
		DELETE_CHECKPOINT cp_marker_triathalon

		IF cpcounter_triathalon[a] < total_checkpoints_triathalon_triathalon_minus_1
			LVAR_INT cp_marker_triathalon
			CREATE_CHECKPOINT CHECKPOINT_TUBE racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] second_cp_triathalonx second_cp_triathalony second_cp_triathalonz 6.0 cp_marker_triathalon
		ELSE
			CREATE_CHECKPOINT CHECKPOINT_ENDTUBE racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] second_cp_triathalonx second_cp_triathalony second_cp_triathalonz 6.0 cp_marker_triathalon
		ENDIF

	  /*	temp_int_triathalon = get_in_water + 4
		IF cpcounter_triathalon[a] = temp_int_triathalon
			CREATE_CAR racers_car_model_triathalon[0] checkpoints_triathalon_x[get_on_bike] checkpoints_triathalon_y[get_on_bike] checkpoints_triathalon_z[get_on_bike] stored_bike_triathalon   // start of cycling
			temp_int_triathalon = get_on_bike - 1
			IF NOT IS_CHAR_DEAD flag_girl_triathalon 
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon -4.0435 6.5755 1.0 x y z
				SET_CHAR_COORDINATES flag_girl_triathalon x y z 
				TASK_TURN_CHAR_TO_FACE_COORD flag_girl_triathalon checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]
			ENDIF

			IF NOT IS_CHAR_DEAD spectator_triathalon[0] 
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon -5.6392 2.9435 1.0  x y z
				SET_CHAR_COORDINATES  spectator_triathalon[0] x y z
				TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[0] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]
			ENDIF

			IF NOT IS_CHAR_DEAD spectator_triathalon[1] 
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon -6.0071 -2.0778 1.0  x y z
				SET_CHAR_COORDINATES spectator_triathalon[1] x y z
				TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[1] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]
			ENDIF

			IF NOT IS_CHAR_DEAD spectator_triathalon[2] 
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon -7.6039 -5.0807 1.0  x y z
				SET_CHAR_COORDINATES spectator_triathalon[2] x y z
				TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[2] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]
			ENDIF

			IF NOT IS_CHAR_DEAD spectator_triathalon[3] 
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon -7.0724 -6.4057 1.0  x y z
				SET_CHAR_COORDINATES spectator_triathalon[3] x y z
				TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[3] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]
			ENDIF

			IF NOT IS_CHAR_DEAD spectator_triathalon[4] 
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon -5.9988 -13.553 1.0  x y z
				SET_CHAR_COORDINATES spectator_triathalon[4] x y z
				TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[4] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]
			ENDIF

			IF NOT IS_CHAR_DEAD spectator_triathalon[5] 
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon 6.0730 -15.2059 1.0  x y z
				SET_CHAR_COORDINATES spectator_triathalon[5] x y z
				TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[5] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]
			ENDIF

			IF NOT IS_CHAR_DEAD spectator_triathalon[6] 
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon 7.2639 -12.9933 1.0  x y z
				SET_CHAR_COORDINATES spectator_triathalon[6] x y z
				TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[6] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]
			ENDIF

			IF NOT IS_CHAR_DEAD spectator_triathalon[7] 
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon 5.8429 -7.7069 1.0  x y z
				SET_CHAR_COORDINATES spectator_triathalon[7] x y z
				TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[7] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]
			ENDIF

			IF NOT IS_CHAR_DEAD spectator_triathalon[8] 
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon 8.4135 -3.7971 1.0  x y z
				SET_CHAR_COORDINATES spectator_triathalon[8] x y z
				TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[8] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]
			ENDIF

			IF NOT IS_CHAR_DEAD spectator_triathalon[9] 
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon 6.2507 0.3634 1.0  x y z
				SET_CHAR_COORDINATES spectator_triathalon[9] x y z
				TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[9] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]
			ENDIF

			IF NOT IS_CHAR_DEAD spectator_triathalon[10] 
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon 8.6397 2.4190 1.0  x y z
				SET_CHAR_COORDINATES spectator_triathalon[10] x y z
				TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[10] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]
			ENDIF
			
			DELETE_CAR stored_bike_triathalon
		ENDIF
		 */

		IF cpcounter_triathalon[a] = 3
			DELETE_CHAR flag_girl_triathalon

			temp_int2_tri = 0
			WHILE NOT temp_int2_tri > 10
				DELETE_CHAR spectator_triathalon[temp_int2_tri]
				temp_int2_tri++
			ENDWHILE

		ENDIF


			temp_int_triathalon = end_bike - 3

	  	IF cpcounter_triathalon[a] = temp_int_triathalon
			CREATE_CAR racers_car_model_triathalon[0] checkpoints_triathalon_x[get_on_foot] checkpoints_triathalon_y[get_on_foot] checkpoints_triathalon_z[get_on_foot] stored_bike_triathalon   // start of cycling

			temp_int_triathalon = end_bike - 1
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon -4.0435 6.5755 0.8 x y z
			CREATE_RANDOM_CHAR x y z flag_girl_triathalon 
			TASK_TURN_CHAR_TO_FACE_COORD flag_girl_triathalon checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[end_bike] checkpoints_triathalon_z[end_bike]
			

			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon -5.6392 2.9435 0.8 x y z
			CREATE_RANDOM_CHAR x y z  spectator_triathalon[0] 
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[0] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[end_bike] checkpoints_triathalon_z[end_bike]
			

			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon -6.0071 -2.0778 0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[1] 
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[1] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]
			

			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon -7.6039 -5.0807 0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[2] 
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[2] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]
			

			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon -7.0724 -6.4057 0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[3] 
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[3] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]

			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon -5.9988 -13.553 0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[4] 
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[4] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]

			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon 6.0730 -15.2059 0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[5]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[5] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]

			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon 7.2639 -12.9933 0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[6] 
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[6] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]

			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon 5.8429 -7.7069 0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[7]
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[7] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]

			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon 8.4135 -3.7971 0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[8] 
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[8] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]
 
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon 6.2507 0.3634 0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[9] 
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[9] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]

			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS stored_bike_triathalon 8.6397 2.4190 0.8 x y z
			CREATE_RANDOM_CHAR x y z spectator_triathalon[10] 
			TASK_TURN_CHAR_TO_FACE_COORD spectator_triathalon[10] checkpoints_triathalon_x[temp_int_triathalon] checkpoints_triathalon_y[temp_int_triathalon] checkpoints_triathalon_z[temp_int_triathalon]
			
			
			DELETE_CAR stored_bike_triathalon
		ENDIF 


		temp_int2_tri =end_bike +2 
		IF cpcounter_triathalon[a] = temp_int2_tri
			MARK_CHAR_AS_NO_LONGER_NEEDED flag_girl_triathalon
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[0]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[1]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[2]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[3]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[4]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[5]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[6]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[7]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[8]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[9]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[10]
		ENDIF


		IF cpcounter_triathalon[a] = 21	 // so mission does not fail if player goes over puddle
			IF NOT IS_CAR_DEAD racers_mtbike[playaz_triathalon]
				SET_CAR_WATERTIGHT racers_mtbike[playaz_triathalon] TRUE
			ENDIF
		ENDIF

		IF cpcounter_triathalon[a] = 22	 // so mission does not fail if player goes over puddle
			IF NOT IS_CAR_DEAD racers_mtbike[playaz_triathalon]
				SET_CAR_WATERTIGHT racers_mtbike[playaz_triathalon] FALSE
			ENDIF
		ENDIF



		
			



		temp_int2_tri =start_run +2 
		IF cpcounter_triathalon[a] = temp_int2_tri
			MARK_CHAR_AS_NO_LONGER_NEEDED flag_girl_triathalon
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[0]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[1]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[2]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[3]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[4]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[5]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[6]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[7]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[8]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[9]
			MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[10]
		ENDIF


		IF cpcounter_triathalon[a] = start_run
			IF NOT IS_CAR_DEAD racers_mtbike[playaz_triathalon]
				IF IS_CHAR_IN_CAR scplayer racers_mtbike[playaz_triathalon]
					
					TASK_CAR_TEMP_ACTION scplayer racers_mtbike[playaz_triathalon] TEMPACT_HANDBRAKESTRAIGHT 2000000
				ENDIF
			ENDIF	

		ENDIF




	ELSE




		IF NOT cpcounter_triathalon[a] >= total_checkpoints_triathalon_triathalon
			// swimming to
		 	CLEAR_CHAR_TASKS triathalonr[a]


			IF cpcounter_triathalon[a] = start_swim
				TASK_GO_STRAIGHT_TO_COORD triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a PEDMOVE_SPRINT -2
				

				IF IS_CHAR_PLAYING_ANIM triathalonr[a] sprint_panic 
					SET_CHAR_ANIM_SPEED triathalonr[a] sprint_panic set_opponents_run_speed
				ENDIF

				IF IS_CHAR_PLAYING_ANIM triathalonr[a] run_civi 
						SET_CHAR_ANIM_SPEED triathalonr[a] run_civi set_opponents_run_speed
				ENDIF

				IF IS_CHAR_PLAYING_ANIM triathalonr[a] WALK_civi 
					SET_CHAR_ANIM_SPEED triathalonr[a] WALK_civi set_opponents_run_speed
				ENDIF

				GOTO exit_locate_loop_triathalon
			ENDIF




			IF cpcounter_triathalon[a] > start_swim
			AND cpcounter_triathalon[a] <= end_swim
				IF IS_CHAR_IN_WATER triathalonr[a]
					x2 = racer_cp_x_triathalon[a] + offset_swimming_opponents_x[a]
					y2 = racer_cp_y_triathalon[a] + offset_swimming_opponents_y[a]

				   	TASK_SWIM_TO_COORD triathalonr[a] x2 y2 racer_cp_z_triathalon[a]
					SET_SWIM_SPEED triathalonr[a]	set_opponents_swim_speed

						IF cpcounter_triathalon[a] = end_swim
							//TASK_GO_STRAIGHT_TO_COORD triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a PEDMOVE_SPRINT -2
						

							IF flag_make_guys_enter_bike[a] = 0
								IF NOT IS_CAR_DEAD racers_mtbike[a]
									OPEN_SEQUENCE_TASK seq_get_on_bike	
										TASK_GO_STRAIGHT_TO_COORD -1 coord_from_swim_to_bike_x coord_from_swim_to_bike_y coord_from_swim_to_bike_z PEDMOVE_SPRINT -2
										TASK_ENTER_CAR_AS_DRIVER -1 racers_mtbike[a] -2
										 //	TASK_CAR_DRIVE_TO_COORD -1 -1 racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] set_opponents_cycle_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
									CLOSE_SEQUENCE_TASK seq_get_on_bike
									PERFORM_SEQUENCE_TASK triathalonr[a] seq_get_on_bike 
									CLEAR_SEQUENCE_TASK seq_get_on_bike 	
									flag_make_guys_enter_bike[a] = 1
								ENDIF
							ENDIF

						ENDIF


				  //	TASK_SWIM_TO_COORD triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a]
				ELSE
						IF cpcounter_triathalon[a] = 2
							TASK_GO_STRAIGHT_TO_COORD triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a PEDMOVE_SPRINT -2
						ENDIF
					 /*	OPEN_SEQUENCE_TASK seq_get_on_bike	
							TASK_GO_STRAIGHT_TO_COORD -1 coord_from_swim_to_bike_x coord_from_swim_to_bike_y coord_from_swim_to_bike_z PEDMOVE_RUN -2
							TASK_ENTER_CAR_AS_DRIVER -1 racers_mtbike[a] -2
						   //	TASK_CAR_DRIVE_TO_COORD -1 -1 racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] set_opponents_cycle_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS
						CLOSE_SEQUENCE_TASK seq_get_on_bike
						PERFORM_SEQUENCE_TASK triathalonr[a] seq_get_on_bike 
						CLEAR_SEQUENCE_TASK seq_get_on_bike 	
						flag_make_guys_enter_bike[a] = 1	  */



					
				ENDIF

				GOTO exit_locate_loop_triathalon
			   //	TASK_GO_STRAIGHT_TO_COORD triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a PEDMOVE_SPRINT -2
			ENDIF

			//get_on_bike

	   /*		IF cpcounter_triathalon[a] = 7

			 //	x2 = racer_cp_x_triathalon[a] + offset_swimming_opponents_x[a]
			 //	y2 = racer_cp_y_triathalon[a] + offset_swimming_opponents_y[a]

			  	TASK_SWIM_TO_COORD triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a]



				GOTO exit_locate_loop_triathalon
			ENDIF	*/
			
			IF cpcounter_triathalon[a] >= start_bike
			AND cpcounter_triathalon[a] <= end_bike
				IF flag_make_guys_enter_bike[a] > 0
					TASK_CAR_DRIVE_TO_COORD triathalonr[a] -1 racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] set_opponents_cycle_speed MODE_RACING TRUE DRIVINGMODE_SLOWDOWNFORCARS

					
				ENDIF 
				GOTO exit_locate_loop_triathalon
			ENDIF



			IF cpcounter_triathalon[a] >= start_run
			AND cpcounter_triathalon[a] <= end_run
				IF cpcounter_triathalon[a] = start_run

				//	SET_FOLLOW_NODE_THRESHOLD_DISTANCE triathalonr[a] 20.0
				// 	TASK_FOLLOW_PATH_NODES_TO_COORD triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] PEDMOVE_SPRINT -2
					TASK_GO_STRAIGHT_TO_COORD triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] PEDMOVE_SPRINT -2
				
				ELSE

					SET_FOLLOW_NODE_THRESHOLD_DISTANCE triathalonr[a] 20.0
				// 	TASK_FOLLOW_PATH_NODES_TO_COORD triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] PEDMOVE_SPRINT -2
			 		TASK_FOLLOW_PATH_NODES_TO_COORD_WITH_RADIUS triathalonr[a] racer_cp_x_triathalon[a] racer_cp_y_triathalon[a] racer_cp_z_triathalon[a] PEDMOVE_SPRINT -2	8.0
				//	TASK_FOLLOW_PATH_NODES_TO_COORD_WITH_RADIUS CharID X Y Z MoveState Time Radius
				ENDIF
				GOTO exit_locate_loop_triathalon	

				
			ENDIF



		ENDIF


	ENDIF
//GOTO exit_locate_loop_triathalon
GOTO exit_locate_loop_triathalon

triathalon_create_checkpoint:

	IF out_of_car_flag_triathalon = 1
		CLEAR_THIS_PRINT RACES48
		REMOVE_BLIP	first_blip_triathalon
		racer_cp_y_triathalon[a] -= 1000.0
		c = 0
		temp_int_triathalon = total_racers_mtbike - 1
		ADD_BLIP_FOR_COORD racer_cp_x_triathalon[stored_checkpoint_when_not_on_bike] racer_cp_y_triathalon[stored_checkpoint_when_not_on_bike] racer_cp_z_triathalon[stored_checkpoint_when_not_on_bike] first_blip_triathalon
		CHANGE_BLIP_COLOUR first_blip_triathalon RED
		CHANGE_BLIP_DISPLAY first_blip_triathalon BLIP_ONLY
		CHANGE_BLIP_SCALE first_blip_triathalon 3
		IF cpcounter_triathalon[stored_checkpoint_when_not_on_bike] = total_checkpoints_triathalon_triathalon_minus_1
		//	AND lap_cpcounter[a] = total_laps_minus_1
			CREATE_CHECKPOINT CHECKPOINT_ENDTUBE racer_cp_x_triathalon[stored_checkpoint_when_not_on_bike] racer_cp_y_triathalon[stored_checkpoint_when_not_on_bike] racer_cp_z_triathalon[stored_checkpoint_when_not_on_bike] 0.0 0.0 0.0 6.0 cp_marker_triathalon
		ELSE
			temp_int_triathalon = cpcounter_triathalon[stored_checkpoint_when_not_on_bike]
			++ temp_int_triathalon
			
			IF cpcounter_triathalon[stored_checkpoint_when_not_on_bike] = total_checkpoints_triathalon_triathalon_minus_1
				temp_int_triathalon = 1
			ENDIF

			CREATE_CHECKPOINT CHECKPOINT_TUBE racer_cp_x_triathalon[stored_checkpoint_when_not_on_bike] racer_cp_y_triathalon[stored_checkpoint_when_not_on_bike] racer_cp_z_triathalon[stored_checkpoint_when_not_on_bike] second_cp_triathalonx second_cp_triathalony second_cp_triathalonz 6.0 cp_marker_triathalon
		ENDIF
		out_of_car_flag_triathalon = 0
	ENDIF

RETURN

// Mission triathalon failed

mission_triathalon_failed:
	PRINT_BIG RACEFA 5000 1
RETURN

   

// mission triathalon passed

mission_triathalon_passed:

	CLEAR_WANTED_LEVEL player1
    PLAY_MISSION_PASSED_TUNE 2
	PRINT_BIG RACES18 5000 1  // Winner!
	IF triathalon_selection =1

		 
		PRINT_WITH_NUMBER TRAI1 10000 5000 2 	//"YOU HAVE WON: $~1~"
	//	PRINT_WITH_2_NUMBERS_NOW TIME mins seconds 5000 1
		ADD_SCORE player1 10000
		
	ENDIF




	IF triathalon_selection =2
		PRINT_WITH_NUMBER TRAI1 20000 5000 2 	//"YOU HAVE WON: $~1~"
	//	PRINT_WITH_2_NUMBERS_NOW TIME mins seconds 5000 1
		ADD_SCORE player1 20000

	ENDIF






	triathalon_timer = triathalon_timer / 1000
	//REGISTER_FASTEST_TIME 12 triathalon_timer

	mins = triathalon_timer / 60
	temp_int_triathalon = mins * 60
	seconds = triathalon_timer - temp_int_triathalon


	IF flag_triathalon_passed_1stime = 0

		IF triathalon_selection =2
		   //	SET_PLAYER_NEVER_GETS_TIRED Player1 TRUE
		   SET_INT_STAT STAMINA 1000
		 
		ENDIF


	   	REGISTER_ODDJOB_MISSION_PASSED
	//    PLAYER_MADE_PROGRESS 1
	    flag_triathalon_passed_1stime = 1
	ENDIF 
			 

RETURN
		


// mission cleanup

mission_cleanup_triathalon:
	flag_player_on_mission = 0 






	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_triathalon[0]
	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_triathalon[1]
	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_triathalon[2]
	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_triathalon[3]
	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_triathalon[4]
	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_triathalon[5]
	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_triathalon[6]
	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_triathalon[7]
	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_triathalon[8]
	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_triathalon[9]
	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_triathalon[10]
	MARK_MODEL_AS_NO_LONGER_NEEDED racer_model_triathalon[11]


	MARK_MODEL_AS_NO_LONGER_NEEDED wmybe
    MARK_MODEL_AS_NO_LONGER_NEEDED bmybe
    MARK_MODEL_AS_NO_LONGER_NEEDED hmybe
    MARK_MODEL_AS_NO_LONGER_NEEDED wmybell
    MARK_MODEL_AS_NO_LONGER_NEEDED mtbike



	MARK_CHAR_AS_NO_LONGER_NEEDED flag_girl_triathalon
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[0]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[1]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[2]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[3]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[4]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[5]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[6]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[7]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[8]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[9]
	MARK_CHAR_AS_NO_LONGER_NEEDED spectator_triathalon[10]


	


	DISPLAY_CAR_NAMES TRUE

	//CLEAR_ONSCREEN_TIMER triathalon_timer

	REMOVE_BLIP	first_blip_triathalon
	GET_GAME_TIMER timer_mobile_start

	DISABLE_ALL_ENTRY_EXITS FALSE

	//SWITCH_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993	
   //	SWITCH_PED_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993


	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_PED_DENSITY_MULTIPLIER 1.0

	SET_MESSAGE_FORMATTING FALSE 380 464

	USE_TEXT_COMMANDS FALSE
	MISSION_HAS_FINISHED
RETURN


}

