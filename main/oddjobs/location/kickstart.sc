MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ********************************** KickStart Bike Trials ********************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME kicksta

// Mission start stuff

GOSUB mission_start_kickstart

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_kickstart_failed
ENDIF

GOSUB mission_cleanup_kickstart

MISSION_END

{

// Variables for mission

VAR_INT player_checkpoint_kickstart // number of checkpoints that the player has completed

VAR_INT time_now_ks // time recorded for falling off of bike

VAR_INT time_off_bike_ks // time player has been off of bike 

VAR_INT time_bailed_ks  // time player fell off

VAR_INT time_left_to_find_bike_ks  // timer player has left to find bike

VAR_INT	time_left_to_find_bike_secs_ks // timer player has left to find bike in seconds

// timer stuff
VAR_INT race_timer_kickstart // time taken

// Timer stuff
VAR_INT race_timer_seconds_kickstart

VAR_INT race_timer_mins_kickstart

VAR_INT race_timer_seconds2_kickstart

VAR_INT player_taken_too_long_kickstart // player has been in the area too long

// Bike stuff
LVAR_INT bike_kickstart

LVAR_INT flag_bike_blip_on_kickstart 

LVAR_INT bike_blip_kickstart

// Blob Stuff
VAR_INT number_of_checkpoints_kickstart score_kickstart

// Dodgy map stuff coords
LVAR_FLOAT player_x_kickstart player_y_kickstart player_z_kickstart

LVAR_FLOAT bike_x_kickstart bike_y_kickstart bike_z_kickstart

// player in end area
LVAR_INT player_in_end_area_kickstart

LVAR_INT sphere_kickstart

// New scoreing system
LVAR_INT player_been_rewarded_kickstart

// New audio stuff
LVAR_INT audio_flag_cheer_kick // Used when the player has scored 8 checkpoints

LVAR_INT audio_flag_ohh_kick // used when the player has falled off of the bike

LVAR_INT flag_play_audio_kickstart

LVAR_INT flash_number_kickstart
flash_number_kickstart = 0


CONST_INT TOTAL_CHECKPOINTS_KICKSTART 33 // constant cant change
LVAR_FLOAT checkpointX_kickstart[TOTAL_CHECKPOINTS_KICKSTART]
LVAR_FLOAT checkpointY_kickstart[TOTAL_CHECKPOINTS_KICKSTART]
LVAR_FLOAT checkpointZ_kickstart[TOTAL_CHECKPOINTS_KICKSTART]
LVAR_INT checkpoint_blip_kickstart[TOTAL_CHECKPOINTS_KICKSTART]

LVAR_INT flag_done_checkpoint_kickstart[TOTAL_CHECKPOINTS_KICKSTART]

LVAR_INT corona_colour[TOTAL_CHECKPOINTS_KICKSTART]	  

LVAR_INT stat_kickstart


LVAR_FLOAT corona_size_kickstart

LVAR_INT red_value_kickstart green_value_kickstart blue_value_kickstart

LVAR_INT counter_array_kickstart

LVAR_INT flag_player_bailed_ks

LVAR_INT watched_cutscene_ks


LVAR_INT index_kickstart

LVAR_INT flag_cutscene_kickstart

LVAR_INT reward_kickstart 

//LVAR_INT car_gen_duneride2_kickstart 




 
		
// ****************************************Mission Start************************************

mission_start_kickstart:
CLEAR_THIS_PRINT_BIG_NOW 1

flag_player_on_mission = 1

flag_play_audio_kickstart = 0

corona_size_kickstart = 1.5
/*
red_value_kickstart = 255
green_value_kickstart = 0 
blue_value_kickstart = 0  */
counter_array_kickstart = 0

flag_player_bailed_ks = 0

score_kickstart = 0


flag_cutscene_kickstart = 0

IF flag_kickstart_passed_1stime = 0
	REGISTER_MISSION_GIVEN
ENDIF

player_checkpoint_kickstart = 0

race_timer_kickstart = 240000//4 minutes
					   
time_left_to_find_bike_ks = 30000 //30 seconds	 

//////////////////////////////////////
// 0 = GREEN
// 1 = AMBER
// 2 = RED
//////////////////////////////////////


// Checkpoint1 jump through fire hoop infront of player
checkpointX_kickstart[0] = -1376.4366
checkpointY_kickstart[0] = 1562.2899
checkpointZ_kickstart[0] = 1059.0
flag_done_checkpoint_kickstart[0] = 0
corona_colour[0] = 0


// Checkpoint 2 steel ledge behind 1st fire jump
checkpointX_kickstart[1] = -1354.8418
checkpointY_kickstart[1] = 1585.2007
checkpointZ_kickstart[1] = 1057.8851
flag_done_checkpoint_kickstart[1] = 0
corona_colour[1] = 0


// Checkpoint 3 steel ledge to left of start 
checkpointX_kickstart[2] = -1408.0247
checkpointY_kickstart[2] = 1568.7357
checkpointZ_kickstart[2] = 1055.8383
flag_done_checkpoint_kickstart[2] = 0
corona_colour[2] = 0

// Checkpoint 4 big rocks to the left of the start 
checkpointX_kickstart[3] = -1451.7278
checkpointY_kickstart[3] = 1571.0653
checkpointZ_kickstart[3] = 1059.000
flag_done_checkpoint_kickstart[3] = 0
corona_colour[3] = 1


// Checkpoint 5 oval wall beind the start       
checkpointX_kickstart[4] = -1487.5543
checkpointY_kickstart[4] = 1564.2778
checkpointZ_kickstart[4] = 1056.4565
flag_done_checkpoint_kickstart[4] = 0
corona_colour[4] = 1

// Checkpoint 6 small rocks with pipe to the left of the start 
checkpointX_kickstart[5] = -1466.2863
checkpointY_kickstart[5] = 1595.3552
checkpointZ_kickstart[5] = 1057.3250
flag_done_checkpoint_kickstart[5] = 0
corona_colour[5] = 2
 
// Checkpoint 7 pile of cars to the left of the first jump 
checkpointX_kickstart[6] = -1387.5973   
checkpointY_kickstart[6] = 1572.1252 
checkpointZ_kickstart[6] = 1053.9291
flag_done_checkpoint_kickstart[6] = 0
corona_colour[6] = 0


// Checkpoint 8 top of bus 1
checkpointX_kickstart[7] = -1362.5787   
checkpointY_kickstart[7] = 1614.6240 
checkpointZ_kickstart[7] = 1055.3297 
flag_done_checkpoint_kickstart[7] = 0
corona_colour[7] = 1


// Checkpoint 9 top of bus 2 
checkpointX_kickstart[8] = -1359.5103   
checkpointY_kickstart[8] = 1638.2191 
checkpointZ_kickstart[8] = 1056.0347 
flag_done_checkpoint_kickstart[8] = 0
corona_colour[8] = 2


// Checkpoint 10 2nd burnt out car with plank bridge 
checkpointX_kickstart[9] = -1371.3506   
checkpointY_kickstart[9] = 1623.2739 
checkpointZ_kickstart[9] = 1054.6587 
flag_done_checkpoint_kickstart[9] = 0
corona_colour[9] = 1

// Checkpoint 11 3rd burnt out car with plank bridge
checkpointX_kickstart[10] = -1369.5204    
checkpointY_kickstart[10] = 1634.0248 
checkpointZ_kickstart[10] = 1055.7720 
flag_done_checkpoint_kickstart[10] = 0
corona_colour[10] = 2

// Checkpoint 12 1st egg yolk from jump
checkpointX_kickstart[11] = -1384.6157   
checkpointY_kickstart[11] = 1603.5475 
checkpointZ_kickstart[11] = 1053.5953 
flag_done_checkpoint_kickstart[11] = 0
corona_colour[11] = 0	

// Checkpoint 13 2nd hoop jump middle of stadium
checkpointX_kickstart[12] = -1398.9961    
checkpointY_kickstart[12] = 1603.2748 
checkpointZ_kickstart[12] =  1055.1562
flag_done_checkpoint_kickstart[12] = 0
corona_colour[12] = 0
 
// Checkpoint 14 loop da loop right hand side 
checkpointX_kickstart[13] = -1403.9114   
checkpointY_kickstart[13] = 1645.1582 
checkpointZ_kickstart[13] = 1071.4617 
flag_done_checkpoint_kickstart[13] = 0
corona_colour[13] = 1

// Checkpoint 15 small metal square on right hand side
checkpointX_kickstart[14] = -1443.3920   
checkpointY_kickstart[14] = 1648.5477 
checkpointZ_kickstart[14] = 1054.2872 
flag_done_checkpoint_kickstart[14] = 0
corona_colour[14] = 0

// Checkpoint 16 small metal v shape right hand side
checkpointX_kickstart[15] = -1457.0033   
checkpointY_kickstart[15] = 1648.7136 
checkpointZ_kickstart[15] = 1054.9373 
flag_done_checkpoint_kickstart[15] = 0
corona_colour[15] = 0

// Checkpoint 17 small 1st square box at back of stadium
checkpointX_kickstart[16] = -1486.9312   
checkpointY_kickstart[16] = 1630.2261 
checkpointZ_kickstart[16] = 1056.6263 
flag_done_checkpoint_kickstart[16] = 0
corona_colour[16] = 0


// Checkpoint18 steall box 2nd story at back of stadium
checkpointX_kickstart[17] = -1482.8134   
checkpointY_kickstart[17] = 1642.2053 
checkpointZ_kickstart[17] = 1060.7042 
flag_done_checkpoint_kickstart[17] = 0
corona_colour[17] = 2

// Checkpoint19 loop da loop at the back of the course
checkpointX_kickstart[18] = -1484.8126  
checkpointY_kickstart[18] = 1597.1162 
checkpointZ_kickstart[18] = 1061.3817 
flag_done_checkpoint_kickstart[18] = 0
corona_colour[18] = 1

// Checkpoint20 1st log section middle of stadium
checkpointX_kickstart[19] = -1431.3992   
checkpointY_kickstart[19] = 1598.0626 
checkpointZ_kickstart[19] = 1054.7516
flag_done_checkpoint_kickstart[19] = 0
corona_colour[19] = 0


// Checkpoint21 2nd log section middle of stadium
checkpointX_kickstart[20] = -1420.6818   
checkpointY_kickstart[20] = 1598.2748 
checkpointZ_kickstart[20] = 1054.9562 
flag_done_checkpoint_kickstart[20] = 0
corona_colour[20] = 0


// Checkpoint22 1st v shape boards in middle of course
checkpointX_kickstart[21] = -1408.1071    
checkpointY_kickstart[21] = 1585.3644 
checkpointZ_kickstart[21] = 1055.6914 
flag_done_checkpoint_kickstart[21] = 0
corona_colour[21] = 0


// Checkpoint23 2st v shape boards in middle of course
checkpointX_kickstart[22] = -1408.2167   
checkpointY_kickstart[22] = 1593.3018 
checkpointZ_kickstart[22] = 1055.6173 
flag_done_checkpoint_kickstart[22] = 0
corona_colour[22] = 0


// Checkpoint24 metal barrel section smallest metal ramp
checkpointX_kickstart[23] = -1462.2439  
checkpointY_kickstart[23] = 1622.4238  
checkpointZ_kickstart[23] = 1054.3130 
flag_done_checkpoint_kickstart[23] = 0
corona_colour[23] = 1

// Checkpoint25 1st ramp up metal section
checkpointX_kickstart[24] = -1461.7395  
checkpointY_kickstart[24] = 1635.7531  
checkpointZ_kickstart[24] = 1054.0070 
flag_done_checkpoint_kickstart[24] = 0
corona_colour[24] = 1

// Checkpoint26 2nd box ramp
checkpointX_kickstart[25] = -1453.8929   
checkpointY_kickstart[25] = 1636.0277 
checkpointZ_kickstart[25] = 1056.1796 
flag_done_checkpoint_kickstart[25] = 0
corona_colour[25] = 1

// Checkpoint27 middle metal pipe section
checkpointX_kickstart[26] = -1454.0554   
checkpointY_kickstart[26] = 1628.6007 
checkpointZ_kickstart[26] = 1054.2977 
flag_done_checkpoint_kickstart[26] = 0
corona_colour[26] = 2

// Checkpoint28 2nd last ramp floating in air
checkpointX_kickstart[27] = -1454.0670  
checkpointY_kickstart[27] = 1617.6372 
checkpointZ_kickstart[27] = 1055.9988 
flag_done_checkpoint_kickstart[27] = 0
corona_colour[27] = 2

// Checkpoint29 last section of metal area
checkpointX_kickstart[28] = -1450.0260   
checkpointY_kickstart[28] = 1620.4666 
checkpointZ_kickstart[28] = 1056.4214 
flag_done_checkpoint_kickstart[28] = 0
corona_colour[28] = 2

// Checkpoint30 middle top of figure of eight 
checkpointX_kickstart[29] = -1407.7319    
checkpointY_kickstart[29] = 1617.4155 
checkpointZ_kickstart[29] = 1054.7971 
flag_done_checkpoint_kickstart[29] = 0
corona_colour[29] = 1

// Checkpoint31 1st ring on figure of eight 
checkpointX_kickstart[30] = -1414.1587   
checkpointY_kickstart[30] = 1607.1595 
checkpointZ_kickstart[30] = 1054.8434
flag_done_checkpoint_kickstart[30] = 0
corona_colour[30] = 1

// Checkpoint32 2nd ring figure of eight
checkpointX_kickstart[31] = -1388.3088   
checkpointY_kickstart[31] = 1620.9905  
checkpointZ_kickstart[31] = 1054.9513 
flag_done_checkpoint_kickstart[31] = 0
corona_colour[31] = 2

// Checkpoint33 strange square thing in the middle of the building
checkpointX_kickstart[32] = -1431.090  
checkpointY_kickstart[32] = 1580.832  
checkpointZ_kickstart[32] = 1055.759 
flag_done_checkpoint_kickstart[32] = 0
corona_colour[32] = 1

// Timer stuff
race_timer_seconds_kickstart = 0

race_timer_seconds2_kickstart = 0

race_timer_mins_kickstart = 0

flag_bike_blip_on_kickstart = 0

player_taken_too_long_kickstart = 0

// Number of checkpoint stuff

number_of_checkpoints_kickstart = TOTAL_CHECKPOINTS_KICKSTART

// dodgy map stuff
player_x_kickstart = 0.0

player_y_kickstart = 0.0

player_z_kickstart = 0.0

bike_x_kickstart = 0.0

bike_y_kickstart = 0.0

bike_z_kickstart = 0.0

// Player in end area
player_in_end_area_kickstart = 0

// New scoreing system
player_been_rewarded_kickstart = 0

// New audio stuff
audio_flag_cheer_kick = 0

audio_flag_ohh_kick = 0

watched_cutscene_ks = 0

WAIT 0

LOAD_MISSION_TEXT KICKSTT



// *************************************** MISSION START ***********************************


SET_PLAYER_CONTROL player1 OFF

SET_FADING_COLOUR 0 0 0

DO_FADE 2000 FADE_OUT

WHILE GET_FADING_STATUS
			
	WAIT 0

ENDWHILE


//SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 1100.0 1599.0 10.0
SET_RESPAWN_POINT_FOR_DURATION_OF_MISSION 1579.5698 1769.0111 9.8280	

SET_PLAYER_IS_IN_STADIUM TRUE
SET_AREA_VISIBLE 14 // 13
//SET_EXTRA_COLOURS 9 FALSE
REQUEST_COLLISION -1473.187 1565.173
SET_CHAR_COORDINATES scplayer -1473.187 1565.173 1051.589
SET_CHAR_AREA_VISIBLE scplayer 14
DISPLAY_RADAR TRUE

SET_CHAR_HEADING scplayer 266.488
LOAD_SCENE -1473.187 1565.173 1051.789 
SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT

SET_EXTRA_COLOURS 2 FALSE








// Waiting for the ped models to load
REQUEST_MODEL SANCHEZ

WHILE NOT HAS_MODEL_LOADED SANCHEZ

	WAIT 0
      
ENDWHILE





CREATE_CAR SANCHEZ -1472.380 1563.576 1051.417 bike_kickstart
SET_VEHICLE_AREA_VISIBLE bike_kickstart 14 // 13
SET_CAR_HEADING bike_kickstart 266.488
SET_CAR_PROOFS bike_kickstart TRUE TRUE TRUE TRUE TRUE
ADD_BLIP_FOR_CAR bike_kickstart bike_blip_kickstart
SET_BLIP_AS_FRIENDLY bike_blip_kickstart TRUE

SET_CAR_STRONG bike_kickstart TRUE

ADD_SPHERE -1464.32 1556.69 1051.75 3.0 sphere_kickstart

// creates the checkpoints

index_kickstart = 0

WHILE index_kickstart < TOTAL_CHECKPOINTS_KICKSTART

	IF corona_colour[index_kickstart] = 0 // GREEN
		red_value_kickstart = 0 
		green_value_kickstart = 255
		blue_value_kickstart = 0
	ENDIF

	IF corona_colour[index_kickstart] = 1 // AMBER
		red_value_kickstart = 255 
		green_value_kickstart = 150
		blue_value_kickstart = 0
	ENDIF

	IF corona_colour[index_kickstart] = 2 // RED
		red_value_kickstart = 255 
		green_value_kickstart = 0
		blue_value_kickstart = 0
	ENDIF



	
	DRAW_CORONA checkpointX_kickstart[index_kickstart] checkpointX_kickstart[index_kickstart] checkpointX_kickstart[index_kickstart] corona_size_kickstart CORONATYPE_SHINYSTAR FLARETYPE_NONE red_value_kickstart green_value_kickstart blue_value_kickstart
	ADD_BLIP_FOR_COORD_OLD checkpointX_kickstart[index_kickstart] checkpointX_kickstart[index_kickstart] checkpointX_kickstart[index_kickstart] 0 BLIP_ONLY checkpoint_blip_kickstart[index_kickstart] 
	CHANGE_BLIP_SCALE checkpoint_blip_kickstart[index_kickstart] 1
	index_kickstart++

ENDWHILE


WAIT 1000
SWITCH_WIDESCREEN ON
SET_POLICE_IGNORE_PLAYER player1 ON
SET_EVERYONE_IGNORE_PLAYER player1 ON
SET_PLAYER_CONTROL player1 OFF


	CLEAR_MISSION_AUDIO 1
	LOAD_MISSION_AUDIO 1 SOUND_CROWD_CHEERS_BIG
	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		WAIT 0
	ENDWHILE  

	


//fades the screen in
	SET_FADING_COLOUR 0 0 0

	DO_FADE 1500 FADE_IN

   	SET_FIXED_CAMERA_POSITION 	-1461.0845 1558.4484 1054.4601  0.0 0.0 0.0 
   	POINT_CAMERA_AT_POINT 		-1460.1351 1558.7611 1054.4293   JUMP_CUT 



   	CAMERA_RESET_NEW_SCRIPTABLES
    CAMERA_PERSIST_TRACK TRUE                   
    CAMERA_PERSIST_POS TRUE                       
    CAMERA_SET_VECTOR_MOVE  -1461.0845 1558.4484 1054.4601 -1474.2550 1561.5668 1052.5531    6000 TRUE    // two sets of coord for cam start point and end point + time for pan
    CAMERA_SET_VECTOR_TRACK -1460.1351 1558.7611 1054.4293 -1473.7408 1562.4242 1052.5328   6000 TRUE    // two sets of coords for cam aim at start and end point + time for pan
							
							
							
						     
    PLAY_MISSION_AUDIO 1
					    

WHILE GET_FADING_STATUS		 
							 
	WAIT 0

	IF IS_CAR_DEAD bike_kickstart
		SET_PLAYER_CONTROL player1 OFF
		PRINT_NOW ( KICK1_7 ) 5000 1 //"You have wrecked the bike!"
	 //	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed!"

		SET_FADING_COLOUR 0 0 0
		
		DO_FADE 1000 FADE_OUT

		WHILE GET_FADING_STATUS
			
			WAIT 0

		ENDWHILE
		SET_AREA_VISIBLE 0
		LOAD_SCENE 1105.210 1604.649 12.510

		IF is_player_playing player1
			SET_CHAR_AREA_VISIBLE scplayer 0
		ENDIF


		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer 1105.210 1604.649 12.0546
		ELSE
			SET_CHAR_COORDINATES scplayer 1105.210 1604.649 12.0546
		ENDIF
		

		WAIT 1500 
		
		GOTO mission_kickstart_failed

	ELSE
		SET_CAR_HEALTH bike_kickstart 1000
	ENDIF 
		
	GOSUB checkpoints_kickstart
   	
ENDWHILE

PRINT_NOW ( KICK_10 ) 10000 1  // Use the Sanchez to collect Coronas. The ~g~Green Corona~s~ is worth 1 point. The ~y~Amber Corona~s~ is worth 2 points. The ~r~Red Corona~s~ is worth 3 points.


TIMERA = 0

SKIP_CUTSCENE_START

WHILE TIMERA < 7000

	WAIT 0

	IF IS_CAR_DEAD bike_kickstart
		SET_PLAYER_CONTROL player1 OFF
		PRINT_NOW ( KICK1_7 ) 5000 1 //"You have wrecked the bike!"
	   //	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed!"

		SET_FADING_COLOUR 0 0 0
		DO_FADE 1000 FADE_OUT

		WHILE GET_FADING_STATUS
			
			WAIT 0

		ENDWHILE

		LOAD_SCENE 1105.210 1604.649 12.510
		SET_AREA_VISIBLE 0

		IF is_player_playing player1
			SET_CHAR_AREA_VISIBLE scplayer 0
		ENDIF

		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer 1105.210 1604.649 12.0546
		ELSE
			SET_CHAR_COORDINATES scplayer 1105.210 1604.649 12.0546
		ENDIF
		

		WAIT 1500 

		
		GOTO mission_kickstart_failed

	ELSE
		SET_CAR_HEALTH bike_kickstart 1000
	ENDIF

	GOSUB checkpoints_kickstart 

ENDWHILE

CLEAR_THIS_PRINT ( KICK_10 )
CAMERA_RESET_NEW_SCRIPTABLES
SET_FIXED_CAMERA_POSITION 	-1474.9097 1564.6246 1052.9518     0.0 0.0 0.0
POINT_CAMERA_AT_POINT 		-1473.9476 1564.3668 1053.0413    JUMP_CUT
PRINT_NOW ( KICK_11 ) 10000 1  // Score 26 points to complete the mission. To leave the stadium stand in the ~r~red highlighted~s~ area on foot.

TASK_LOOK_AT_COORD scplayer -1464.32 1556.69 1051.75 6000


TIMERA = 0

WHILE TIMERA < 7000

	WAIT 0

	IF IS_CAR_DEAD bike_kickstart
		SET_PLAYER_CONTROL player1 OFF
		PRINT_NOW ( KICK1_7 ) 5000 1 //"You have wrecked the bike!"
	  //	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed!"

		SET_FADING_COLOUR 0 0 0
		DO_FADE 1000 FADE_OUT

		WHILE GET_FADING_STATUS
			
			WAIT 0

		ENDWHILE

		LOAD_SCENE 1105.210 1604.649 12.510
		SET_AREA_VISIBLE 0

		IF is_player_playing player1
			SET_CHAR_AREA_VISIBLE scplayer 0
		ENDIF

		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer 1105.210 1604.649 12.0546
		ELSE
			SET_CHAR_COORDINATES scplayer 1105.210 1604.649 12.0546
		ENDIF
		
		WAIT 1500 
		

		
		GOTO mission_kickstart_failed

	ELSE
		SET_CAR_HEALTH bike_kickstart 1000
	ENDIF

	GOSUB checkpoints_kickstart 
		
ENDWHILE

CLEAR_THIS_PRINT ( KICK_11 )  // Score 26 points to complete the mission. To leave the stadium stand in the ~r~red highlighted~s~ area on foot.

SKIP_CUTSCENE_END

IF IS_CAR_DEAD bike_kickstart
	SET_PLAYER_CONTROL player1 OFF
	PRINT_NOW ( KICK1_7 ) 5000 1 //"You have wrecked the bike!"
   //	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed!"

	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_OUT

	WHILE GET_FADING_STATUS
		
		WAIT 0

	ENDWHILE

		LOAD_SCENE 1105.210 1604.649 12.510
		SET_AREA_VISIBLE 0

		IF is_player_playing player1
			SET_CHAR_AREA_VISIBLE scplayer 0
		ENDIF

		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer 1105.210 1604.649 12.0546
		ELSE
			SET_CHAR_COORDINATES scplayer 1105.210 1604.649 12.0546
		ENDIF
		
		WAIT 1500 

	
	GOTO mission_kickstart_failed

ELSE
	SET_CAR_HEALTH bike_kickstart 1000
ENDIF 

SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT

SET_RADIO_CHANNEL RS_OFF


SET_POLICE_IGNORE_PLAYER player1 OFF
SET_EVERYONE_IGNORE_PLAYER player1 OFF
SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT

SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL player1 ON
CAMERA_RESET_NEW_SCRIPTABLES
DISPLAY_RADAR FALSE






  




PRINT_NOW ( KICK1_8 ) 5000 1 //"Get ont the bike!"

WHILE NOT IS_CHAR_IN_CAR scplayer bike_kickstart

	WAIT 0

	IF IS_CAR_DEAD bike_kickstart
		SET_PLAYER_CONTROL player1 OFF
		PRINT_NOW ( KICK1_7 ) 5000 1 //"You have wrecked the bike!"
	  //	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed!"

		SET_FADING_COLOUR 0 0 0
		DO_FADE 1000 FADE_OUT

		WHILE GET_FADING_STATUS
			
			WAIT 0

		ENDWHILE
		SET_AREA_VISIBLE 0
		LOAD_SCENE 1105.210 1604.649 12.510

		IF is_player_playing player1
			SET_CHAR_AREA_VISIBLE scplayer 0
		ENDIF

		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer 1105.210 1604.649 12.0546
		ELSE
			SET_CHAR_COORDINATES scplayer 1105.210 1604.649 12.0546
		ENDIF
		
		WAIT 1500 

		
		GOTO mission_kickstart_failed

	ELSE
		SET_CAR_HEALTH bike_kickstart 1000
	ENDIF

	IF race_timer_kickstart >= 3600000
	OR player_taken_too_long_kickstart = 1
		
		SET_PLAYER_CONTROL player1 OFF
	  //	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed!"
		PRINT_NOW ( KICK_13 ) 5000 1 //"You have taken too long!
		SET_FADING_COLOUR 0 0 0

		WAIT 1500 
		DO_FADE 1000 FADE_OUT

		WHILE GET_FADING_STATUS
			
			WAIT 0

		ENDWHILE
		SET_AREA_VISIBLE 0
		LOAD_SCENE 1105.210 1604.649 12.510


		IF is_player_playing player1
			SET_CHAR_AREA_VISIBLE scplayer 0
		ENDIF

		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer 1105.210 1604.649 12.0546
		ELSE
			SET_CHAR_COORDINATES scplayer 1105.210 1604.649 12.0546
		ENDIF
		
		WAIT 1500 


		GOTO mission_kickstart_failed
	ENDIF
	
	IF LOCATE_CHAR_ON_FOOT_3D scplayer -1464.32 1556.69 1051.75 3.0 3.0 3.0 FALSE
	OR player_in_end_area_kickstart = 1
		SET_PLAYER_CONTROL player1 OFF
	  //	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed!"
		PRINT_NOW ( KICK_12 ) 5000 1 //"You bottled it!"
		REMOVE_SPHERE sphere_kickstart

		SET_FADING_COLOUR 0 0 0
		DO_FADE 1000 FADE_OUT

		WHILE GET_FADING_STATUS
			
			WAIT 0

		ENDWHILE

		LOAD_SCENE 1105.210 1604.649 12.510
		SET_AREA_VISIBLE 0

		IF is_player_playing player1
			SET_CHAR_AREA_VISIBLE scplayer 0
		ENDIF

		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer 1105.210 1604.649 12.0546
		ELSE
			SET_CHAR_COORDINATES scplayer 1105.210 1604.649 12.0546
		ENDIF
		
		WAIT 1500 

		
		GOTO mission_kickstart_failed
	ENDIF
		
	
	GOSUB checkpoints_kickstart	
   
ENDWHILE


SWITCH_WIDESCREEN ON
SET_PLAYER_CONTROL player1 OFF
SET_CAMERA_BEHIND_PLAYER


CLEAR_THIS_PRINT KICK1_8



// 4 3 2 1!
flag_cutscene_kickstart = 0
TIMERA = 0
TIMERB= 0

WHILE NOT flag_cutscene_kickstart = 10 
	WAIT 0

	 

	IF flag_cutscene_kickstart = 0
		IF TIMERB >	900
			PRINT_BIG KICK_17 1000 4
			ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_RACE_START_3

			LOAD_MISSION_AUDIO 4 SOUND_BANK_AIR_HORN
			WHILE NOT HAS_MISSION_AUDIO_LOADED 4
				WAIT 0
			ENDWHILE   


		   //	REPORT_MISSION_AUDIO_EVENT_AT_CHAR char_mech_garag1[index_garag1] SOUND_AIR_HORN
			
			++ flag_cutscene_kickstart
			TIMERB = 0
		ENDIF
	ENDIF


	IF flag_cutscene_kickstart = 1
		IF TIMERB >	900
			PRINT_BIG KICK_18 1000 4
			ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_RACE_START_2 



			++ flag_cutscene_kickstart
			TIMERB = 0
		ENDIF
	ENDIF

	IF flag_cutscene_kickstart = 2
		IF TIMERB >	900

			PRINT_BIG KICK_19 1000 4
			ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_RACE_START_1


			++ flag_cutscene_kickstart
			TIMERB = 0
		ENDIF
	ENDIF

	IF flag_cutscene_kickstart = 3
		IF TIMERB >	900
			PRINT_BIG KICK_20 800 4


		 	REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1462.0 1558.69 1055.02 SOUND_AIR_HORN

		   //ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_CHECKPOINT_RED
			flag_cutscene_kickstart = 10
			TIMERB = 0
		ENDIF
	ENDIF

	IF TIMERA > 4000
		flag_cutscene_kickstart = 10
	ENDIF	
	GOSUB checkpoints_kickstart
ENDWHILE

SWITCH_WIDESCREEN OFF


REMOVE_BLIP bike_blip_kickstart

SET_PLAYER_CONTROL player1 ON

PRINT_NOW ( KICK_26 ) 5000 1 //"Collect as many points as possible"

DISPLAY_ONSCREEN_COUNTER_WITH_STRING score_kickstart COUNTER_DISPLAY_NUMBER ( KICK1_9 )

DISPLAY_ONSCREEN_TIMER_WITH_STRING race_timer_kickstart TIMER_DOWN ( KICK1_T ) 

TIMERA = 0

TIMERB = 0

// Player started the competition  

WHILE NOT player_checkpoint_kickstart = TOTAL_CHECKPOINTS_KICKSTART

	WAIT 0

	GOSUB checkpoints_kickstart

	IF flag_play_audio_kickstart= 1
		IF HAS_MISSION_AUDIO_LOADED 1
			flag_play_audio_kickstart= 2
		ENDIF  
	ENDIF

	IF flag_play_audio_kickstart= 2
		PLAY_MISSION_AUDIO 1
		flag_play_audio_kickstart= 3
	ENDIF

	IF flag_play_audio_kickstart= 3
		IF HAS_MISSION_AUDIO_FINISHED 1
			CLEAR_MISSION_AUDIO 1
			flag_play_audio_kickstart= 0
		ENDIF
	ENDIF


	IF race_timer_kickstart <= 0
	OR player_taken_too_long_kickstart = 1
		SET_PLAYER_CONTROL player1 OFF

		CLEAR_ONSCREEN_TIMER race_timer_kickstart
		CLEAR_ONSCREEN_COUNTER score_kickstart 



	  	IF score_kickstart > current_highest_score_kickstart 
			current_highest_score_kickstart = score_kickstart
			PRINT_WITH_NUMBER_BIG ( KICK_22 ) current_highest_score_kickstart 5000 1		  // new highest score
			PRINT_NOW KICK_23 5000 1 
	   	 	REGISTER_INT_STAT KICKSTART_BEST_SCORE current_highest_score_kickstart
			PLAY_MISSION_PASSED_TUNE 2
			IF flag_kickstart_passed_1stime = 0
				SWITCH_CAR_GENERATOR car_gen_duneride_kickstart	101
			ENDIF


			WAIT 5000

			SET_FADING_COLOUR 0 0 0
			DO_FADE 1000 FADE_OUT

			WHILE GET_FADING_STATUS
				
				WAIT 0

			ENDWHILE

			LOAD_SCENE 1105.210 1604.649 12.510
			SET_AREA_VISIBLE 0

			IF is_player_playing player1
				SET_CHAR_AREA_VISIBLE scplayer 0
			ENDIF

			IF IS_CHAR_IN_ANY_CAR scplayer
				WARP_CHAR_FROM_CAR_TO_COORD scplayer 1105.210 1604.649 12.0546
			ELSE
				SET_CHAR_COORDINATES scplayer 1105.210 1604.649 12.0546
			ENDIF
			
			WAIT 1500 



	   	 	GOTO mission_kickstart_passed
 
		ELSE
			PRINT_WITH_NUMBER_BIG ( KICK1_9 ) score_kickstart 5000 1 
			PRINT_WITH_NUMBER_BIG KICK_21 current_highest_score_kickstart 5000 6
			PRINT_NOW KICK_24 5000 1  // no new records broken
			GET_INT_STAT KICKSTART_BEST_SCORE stat_kickstart
			IF stat_kickstart < score_kickstart
				REGISTER_INT_STAT KICKSTART_BEST_SCORE score_kickstart
			ENDIF

			PLAY_MISSION_PASSED_TUNE 1
			WAIT 5000

			SET_FADING_COLOUR 0 0 0
			DO_FADE 1000 FADE_OUT

			WHILE GET_FADING_STATUS
				
				WAIT 0

			ENDWHILE

			LOAD_SCENE 1105.210 1604.649 12.510
			SET_AREA_VISIBLE 0

			IF is_player_playing player1
				SET_CHAR_AREA_VISIBLE scplayer 0
			ENDIF

			IF IS_CHAR_IN_ANY_CAR scplayer
				WARP_CHAR_FROM_CAR_TO_COORD scplayer 1105.210 1604.649 12.0546
			ELSE
				SET_CHAR_COORDINATES scplayer 1105.210 1604.649 12.0546
			ENDIF
			
			WAIT 1500 



			GOTO mission_kickstart_failed

		ENDIF






	ENDIF
	
	IF LOCATE_CHAR_ON_FOOT_3D scplayer -1464.32 1556.69 1051.75 3.0 3.0 3.0 FALSE
	OR player_in_end_area_kickstart = 1
		SET_PLAYER_CONTROL player1 OFF
	 //	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed!"
		PRINT_NOW ( KICK_12 ) 5000 1 //"You bottled it!"
		REMOVE_SPHERE sphere_kickstart

		SET_FADING_COLOUR 0 0 0
		DO_FADE 1000 FADE_OUT

		WHILE GET_FADING_STATUS
			
			WAIT 0

		ENDWHILE

		LOAD_SCENE 1105.210 1604.649 12.510
		SET_AREA_VISIBLE 0

		IF is_player_playing player1
			SET_CHAR_AREA_VISIBLE scplayer 0
		ENDIF

		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer 1105.210 1604.649 12.0546
		ELSE
			SET_CHAR_COORDINATES scplayer 1105.210 1604.649 12.0546
		ENDIF
		
		WAIT 1500 

		
		GOTO mission_kickstart_failed
	ENDIF

	GET_CHAR_COORDINATES scplayer player_x_kickstart player_y_kickstart player_z_kickstart

// player has fallen through the world
   	IF player_z_kickstart < 1050.0
   		SET_CHAR_COORDINATES scplayer -1473.187 1565.173 1051.789
		SET_CHAR_HEADING scplayer 266.488
   	ENDIF 

	IF IS_CAR_DEAD bike_kickstart
		SET_PLAYER_CONTROL player1 OFF
		PRINT_NOW ( KICK1_7 ) 5000 1 //"You have wrecked the bike!"
	 //	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed!"

		SET_FADING_COLOUR 0 0 0
		DO_FADE 1000 FADE_OUT

		WHILE GET_FADING_STATUS
			
			WAIT 0

		ENDWHILE

		LOAD_SCENE 1105.210 1604.649 12.510
		SET_AREA_VISIBLE 0

		IF is_player_playing player1
			SET_CHAR_AREA_VISIBLE scplayer 0
		ENDIF

		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer 1105.210 1604.649 12.0546
		ELSE
			SET_CHAR_COORDINATES scplayer 1105.210 1604.649 12.0546
		ENDIF
		
		WAIT 1500 

		
		GOTO mission_kickstart_failed
	ELSE
				
		SET_CAR_HEALTH bike_kickstart 1000
	
		GET_CAR_COORDINATES bike_kickstart bike_x_kickstart bike_y_kickstart bike_z_kickstart

	   	IF bike_z_kickstart < 1050.0
	   		CREATE_CAR SANCHEZ -1472.380 1563.576 1051.417 bike_kickstart
			SET_CAR_HEADING bike_kickstart 266.488
	   	ENDIF

		IF NOT IS_CHAR_IN_MODEL scplayer SANCHEZ

			IF flag_player_bailed_ks = 0 
		
				IF flag_bike_blip_on_kickstart = 0
					ADD_BLIP_FOR_CAR bike_kickstart bike_blip_kickstart
					SET_BLIP_AS_FRIENDLY bike_blip_kickstart TRUE

					IF audio_flag_ohh_kick = 2
					  //	PLAY_MISSION_AUDIO 2
						audio_flag_ohh_kick = 3
					ENDIF

					flag_bike_blip_on_kickstart = 1
				ENDIF
			  
				GET_GAME_TIMER time_bailed_ks

				time_left_to_find_bike_ks = 30000


				IF race_timer_kickstart >= 30000  

					time_left_to_find_bike_ks = 30000
				ELSE
					time_left_to_find_bike_ks = race_timer_kickstart
				ENDIF


				flag_player_bailed_ks = 1
				
		   //		ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_CROWD_AWWS
				CLEAR_MISSION_AUDIO 1
 			  	LOAD_MISSION_AUDIO 1 SOUND_CROWD_AWWS
				flag_play_audio_kickstart= 1




			ELSE

				IF time_left_to_find_bike_ks > 0 
					GET_GAME_TIMER time_now_ks
			 		time_off_bike_ks = time_now_ks - time_bailed_ks
					time_bailed_ks = time_now_ks
			 		time_left_to_find_bike_ks = time_left_to_find_bike_ks - time_off_bike_ks
			 		time_left_to_find_bike_secs_ks = time_left_to_find_bike_ks / 1000

					ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS FALSE
					IF NOT time_left_to_find_bike_secs_ks = 1 
			 			PRINT_WITH_NUMBER_NOW (KICK_27) time_left_to_find_bike_secs_ks 1000 1 //"time left to get onto bike!"
					ELSE
						PRINT_WITH_NUMBER_NOW (KICK_28) time_left_to_find_bike_secs_ks 1000 1 //"time left to get onto bike!"
					ENDIF

			 		IF IS_CHAR_IN_MODEL scplayer SANCHEZ
						CLEAR_THIS_PRINT (GETBIKE)
						CLEAR_THIS_PRINT (GETBIK2)
			 			time_left_to_find_bike_ks = 1
			 		ENDIF

				ELSE

					IF time_left_to_find_bike_ks <= 0
						PRINT_NOW ( KICK1_2 ) 5000 1 //"You did not get back to the bike quickly enough!"
						SET_PLAYER_CONTROL player1 OFF
					   //	ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_CROWD_AWWS
						CLEAR_MISSION_AUDIO 1
		 			  	LOAD_MISSION_AUDIO 1 SOUND_CROWD_AWWS
						flag_play_audio_kickstart= 1


						WAIT 1500
						SET_FADING_COLOUR 0 0 0
						DO_FADE 1000 FADE_OUT

						WHILE GET_FADING_STATUS

							WAIT 0
											
						ENDWHILE

						LOAD_SCENE 1105.210 1604.649 12.510
						SET_AREA_VISIBLE 0

						IF is_player_playing player1
							SET_CHAR_AREA_VISIBLE scplayer 0
						ENDIF

						IF IS_CHAR_IN_ANY_CAR scplayer
							WARP_CHAR_FROM_CAR_TO_COORD scplayer 1105.210 1604.649 12.0546
						ELSE
							SET_CHAR_COORDINATES scplayer 1105.210 1604.649 12.0546
						ENDIF
						
						WAIT 1500 

						
						GOTO mission_kickstart_failed

					ENDIF

				ENDIF // timer left to find bike
				
			ENDIF // player bailed

		ELSE 

			CLEAR_THIS_PRINT ( KICK_27 )
		
			IF flag_bike_blip_on_kickstart = 1
				REMOVE_BLIP bike_blip_kickstart
				flag_bike_blip_on_kickstart = 0
			ENDIF

			flag_player_bailed_ks = 0

		ENDIF // is player in car
	
    ENDIF
    			
ENDWHILE

	CLEAR_ONSCREEN_TIMER race_timer_kickstart
	CLEAR_ONSCREEN_COUNTER score_kickstart 


	  	IF score_kickstart > current_highest_score_kickstart 
	   //	IF score_kickstart > current_highest_score_kickstart
			current_highest_score_kickstart = score_kickstart
			PRINT_WITH_NUMBER_BIG ( KICK_22 ) current_highest_score_kickstart 5000 1		  // new highest score
			PRINT_NOW KICK_23 5000 1 
	   	 // 	REGISTER_HIGHEST_SCORE KICKSTART_BEST_SCORE current_highest_score_kickstart 
	   	 	REGISTER_INT_STAT KICKSTART_BEST_SCORE current_highest_score_kickstart

			IF flag_kickstart_passed_1stime = 0
				SWITCH_CAR_GENERATOR car_gen_duneride_kickstart	101
			ENDIF
			 PLAY_MISSION_PASSED_TUNE 2

			WAIT 5000

			SET_FADING_COLOUR 0 0 0
			DO_FADE 1000 FADE_OUT

			WHILE GET_FADING_STATUS
				
				WAIT 0

			ENDWHILE

			LOAD_SCENE 1105.210 1604.649 12.510
			SET_AREA_VISIBLE 0

			IF is_player_playing player1
				SET_CHAR_AREA_VISIBLE scplayer 0
			ENDIF

			IF IS_CHAR_IN_ANY_CAR scplayer
				WARP_CHAR_FROM_CAR_TO_COORD scplayer 1105.210 1604.649 12.0546
			ELSE
				SET_CHAR_COORDINATES scplayer 1105.210 1604.649 12.0546
			ENDIF
			
			WAIT 1500 



	   	 	GOTO mission_kickstart_passed
 
		ELSE
			//REGISTER_INT_STAT KICKSTART_BEST_SCORE score_kickstart 
			PLAY_MISSION_PASSED_TUNE 1
			//REGISTER_INT_STAT KICKSTART_BEST_SCORE score_kickstart
			GET_INT_STAT KICKSTART_BEST_SCORE stat_kickstart
			IF stat_kickstart < score_kickstart
				REGISTER_INT_STAT KICKSTART_BEST_SCORE score_kickstart
			ENDIF


			PRINT_WITH_NUMBER_BIG ( KICK1_9 ) score_kickstart 5000 1 
		//	PRINT_WITH_NUMBER_NOW KICK_21 current_highest_score_kickstart 5000 1
			PRINT_WITH_NUMBER_BIG KICK_21 current_highest_score_kickstart 5000 6

			WAIT 5000

			SET_FADING_COLOUR 0 0 0
			DO_FADE 1000 FADE_OUT

			WHILE GET_FADING_STATUS
				
				WAIT 0

			ENDWHILE

			LOAD_SCENE 1105.210 1604.649 12.510
			SET_AREA_VISIBLE 0

			IF is_player_playing player1
				SET_CHAR_AREA_VISIBLE scplayer 0
			ENDIF

			IF IS_CHAR_IN_ANY_CAR scplayer
				WARP_CHAR_FROM_CAR_TO_COORD scplayer 1105.210 1604.649 12.0546
			ELSE
				SET_CHAR_COORDINATES scplayer 1105.210 1604.649 12.0546
			ENDIF
			
			WAIT 1500 



			GOTO mission_kickstart_failed

		ENDIF


// ****************************************** Mission Failed *******************************

mission_kickstart_failed:

//PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed!"
	PRINT_WITH_NUMBER_BIG ( KICK1_9  ) 15 score_kickstart 1 
	PRINT_WITH_NUMBER_NOW KICK_21 current_highest_score_kickstart 5000 1

	reward_kickstart = score_kickstart * 100
	ADD_SCORE player1 reward_kickstart


RETURN

  

// *************************************** Mission Passed **********************************

mission_kickstart_passed:

IF flag_kickstart_passed_1stime = 0
	REGISTER_ODDJOB_MISSION_PASSED
	PLAYER_MADE_PROGRESS 1
	flag_kickstart_passed_1stime = 1
   //	CREATE_CAR_GENERATOR 1107.0597 1616.5936 13.0 167.7583  duneride	-1 -1 TRUE 0 0 0 10000 car_gen_duneride 	// by the Hoover dam
   //	SWITCH_CAR_GENERATOR car_gen_duneride 101


ENDIF

//PLAY_MISSION_PASSED_TUNE 1
//SET_MUSIC_DOES_FADE FALSE
CLEAR_WANTED_LEVEL player1

PRINT_WITH_NUMBER_BIG ( KICK_22  ) current_highest_score_kickstart 5000 1		  // new highest score

reward_kickstart = score_kickstart * 100
ADD_SCORE player1 reward_kickstart



 

RETURN
	


// *************************************** Mission Cleanup *********************************

mission_cleanup_kickstart:
	DISPLAY_RADAR TRUE
	CLEAR_EXTRA_COLOURS FALSE
	flag_player_on_mission = 0
	CLEAR_PRINTS
	CLEAR_EXTRA_COLOURS FALSE
	// SET_MUSIC_DOES_FADE TRUE

	REMOVE_SPHERE sphere_kickstart
	CAMERA_RESET_NEW_SCRIPTABLES


	REMOVE_BLIP checkpoint_blip_kickstart[0]
	REMOVE_BLIP checkpoint_blip_kickstart[1]
	REMOVE_BLIP checkpoint_blip_kickstart[2]
	REMOVE_BLIP checkpoint_blip_kickstart[3]
	REMOVE_BLIP checkpoint_blip_kickstart[4]
	REMOVE_BLIP checkpoint_blip_kickstart[5]
	REMOVE_BLIP checkpoint_blip_kickstart[6]
	REMOVE_BLIP checkpoint_blip_kickstart[7]
	REMOVE_BLIP checkpoint_blip_kickstart[8]
	REMOVE_BLIP checkpoint_blip_kickstart[9]
	REMOVE_BLIP checkpoint_blip_kickstart[10]
	REMOVE_BLIP checkpoint_blip_kickstart[11]
	REMOVE_BLIP checkpoint_blip_kickstart[12]
	REMOVE_BLIP checkpoint_blip_kickstart[13]
	REMOVE_BLIP checkpoint_blip_kickstart[14]
	REMOVE_BLIP checkpoint_blip_kickstart[15]
	REMOVE_BLIP checkpoint_blip_kickstart[16]
	REMOVE_BLIP checkpoint_blip_kickstart[17]
	REMOVE_BLIP checkpoint_blip_kickstart[18]
	REMOVE_BLIP checkpoint_blip_kickstart[19]
	REMOVE_BLIP checkpoint_blip_kickstart[20]
	REMOVE_BLIP checkpoint_blip_kickstart[21]
	REMOVE_BLIP checkpoint_blip_kickstart[22]
	REMOVE_BLIP checkpoint_blip_kickstart[23]
	REMOVE_BLIP checkpoint_blip_kickstart[24]
	REMOVE_BLIP checkpoint_blip_kickstart[25]
	REMOVE_BLIP checkpoint_blip_kickstart[26]
	REMOVE_BLIP checkpoint_blip_kickstart[27]
	REMOVE_BLIP checkpoint_blip_kickstart[28]
	REMOVE_BLIP checkpoint_blip_kickstart[29]
	REMOVE_BLIP checkpoint_blip_kickstart[30]
	REMOVE_BLIP checkpoint_blip_kickstart[31]
	REMOVE_BLIP checkpoint_blip_kickstart[32]

	REMOVE_BLIP bike_blip_kickstart



	MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
	CLEAR_ONSCREEN_TIMER race_timer_kickstart
	CLEAR_ONSCREEN_COUNTER score_kickstart


 //	LOAD_SCENE 1105.210 1604.649 12.510

	IF NOT HAS_DEATHARREST_BEEN_EXECUTED
	OR flag_kickstart_mission1_passed = 1
	
	 /*	IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer 1105.210 1604.649 12.0546
		ELSE
			SET_CHAR_COORDINATES scplayer 1105.210 1604.649 12.0546
		ENDIF
	   */
		SET_CHAR_HEADING scplayer 1.0
		RESTORE_CAMERA_JUMPCUT
		SET_CAMERA_BEHIND_PLAYER
		SET_FADING_COLOUR 0 0 0 
		DO_FADE 3000 FADE_IN
		SET_PLAYER_CONTROL player1 ON
	//	SET_CHAR_HAS_USED_ENTRY_EXIT scplayer -1463.87 1556.0 1052.56

	ENDIF

   //	SET_AREA_VISIBLE 0

	IF is_player_playing player1
		SET_CHAR_AREA_VISIBLE scplayer 0
	ENDIF







	DISPLAY_RADAR TRUE

	MARK_CAR_AS_NO_LONGER_NEEDED bike_kickstart



   	SET_PLAYER_IS_IN_STADIUM FALSE
	GET_GAME_TIMER timer_mobile_start
	MISSION_HAS_FINISHED


RETURN

// draws the checkpoints
checkpoints_kickstart:

	counter_array_kickstart = 0

	WHILE counter_array_kickstart < TOTAL_CHECKPOINTS_KICKSTART 

		IF flag_done_checkpoint_kickstart[counter_array_kickstart] = 0

			IF corona_colour[counter_array_kickstart] = 0 // GREEN
				red_value_kickstart = 0 
				green_value_kickstart = 255
				blue_value_kickstart = 0
			ENDIF

			IF corona_colour[counter_array_kickstart] = 1 // AMBER
				red_value_kickstart = 255 
				green_value_kickstart = 150
				blue_value_kickstart = 0
			ENDIF

			IF corona_colour[counter_array_kickstart] = 2 // RED
				red_value_kickstart = 255 
				green_value_kickstart = 0
				blue_value_kickstart = 0
			ENDIF

		   	DRAW_CORONA checkpointX_kickstart[counter_array_kickstart] checkpointY_kickstart[counter_array_kickstart] checkpointZ_kickstart[counter_array_kickstart] corona_size_kickstart CORONATYPE_SHINYSTAR FLARETYPE_NONE red_value_kickstart green_value_kickstart blue_value_kickstart
			
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer checkpointX_kickstart[counter_array_kickstart] checkpointY_kickstart[counter_array_kickstart] checkpointZ_kickstart[counter_array_kickstart] 1.5 1.5 1.5 FALSE

				IF IS_CHAR_IN_MODEL scplayer SANCHEZ


					IF corona_colour[counter_array_kickstart] = 0 // GREEN
						score_kickstart += 1
						
						PRINT_NOW ( KICK_14 ) 1000 1 //"Well done now on to the next checkpoint!"
					  //	ADD_ONE_OFF_SOUND checkpointX_kickstart[counter_array_kickstart] checkpointY_kickstart[counter_array_kickstart] checkpointZ_kickstart[counter_array_kickstart] SOUND_CHECKPOINT_GREEN

						CLEAR_MISSION_AUDIO 1
	  					//LOAD_MISSION_AUDIO 1 SOUND_CHECKPOINT_GREEN
						LOAD_MISSION_AUDIO 1 SOUND_CROWD_CHEERS

						flag_play_audio_kickstart= 1



					ENDIF

					IF corona_colour[counter_array_kickstart] = 1 // AMBER
						score_kickstart += 2
						PRINT_NOW ( KICK_15 ) 1000 1 //"Well done now on to the next checkpoint!"
	  					CLEAR_MISSION_AUDIO 1
	  				   //	LOAD_MISSION_AUDIO 1 SOUND_CHECKPOINT_AMBER
					   LOAD_MISSION_AUDIO 1 SOUND_CROWD_CHEERS
						flag_play_audio_kickstart= 1
					
					
					ENDIF

					IF corona_colour[counter_array_kickstart] = 2 // RED
						score_kickstart += 3
						PRINT_NOW ( KICK_16 ) 1000 1 //"Well done now on to the next checkpoint!"
		 			  	CLEAR_MISSION_AUDIO 1
		 			   //	LOAD_MISSION_AUDIO 1 SOUND_CHECKPOINT_RED
					   LOAD_MISSION_AUDIO 1 SOUND_CROWD_CHEERS_BIG
						flag_play_audio_kickstart= 1



					ENDIF

  

					REMOVE_BLIP checkpoint_blip_kickstart[counter_array_kickstart]
					++ player_checkpoint_kickstart
					-- number_of_checkpoints_kickstart
					flag_done_checkpoint_kickstart[counter_array_kickstart] = 1
				ENDIF
				
			ENDIF
			
		ENDIF

		++ counter_array_kickstart

	ENDWHILE

RETURN

}









   