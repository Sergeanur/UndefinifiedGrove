MISSION_START
// *****************************************************************************************
// ***************************** toreno1 aka Monster ************************************ 
// *****************************************************************************************
// *****************************************************************************************
// ***                                                                                   ***
// *****************************************************************************************

SCRIPT_NAME toreno1

// Mission start stuff
GOSUB mission_start_toreno1
IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_toreno1_failed
ENDIF
GOSUB mission_cleanup_toreno1
MISSION_END
{

////////////////////////////
// Variables for mission ///
////////////////////////////
 
LVAR_INT des1_counter 
LVAR_INT des1_temp_int
LVAR_INT des1_number_of_check_points
LVAR_INT des1_current_check_point des1_controller_mode
LVAR_INT des1_car_health
LVAR_INT des1_temp_counter


LVAR_INT des1_task_status
LVAR_INT des1_reward

///// timer stuff /////
LVAR_INT des1_timer_start
LVAR_INT des1_timer_current
LVAR_INT des1_timer_end
LVAR_INT des1_total_time
LVAR_INT des1_timer_temp
LVAR_INT des1_minutes
LVAR_INT des1_seconds

LVAR_INT des1_out_of_car_start
LVAR_INT des1_out_of_car_current


////// on screen display /////
LVAR_FLOAT des1x_pos[7] des1y_pos[7] des1x_scale[7] des1y_scale[7] 
// cars 
LVAR_INT des1_player_car


// peds
LVAR_INT des1_starter_ped
				    
//blips
LVAR_INT des1_route_blip

//flags

LVAR_INT des1_mission_progression 
LVAR_INT des1_safety_flag 
LVAR_INT des1_car_blip_vis
LVAR_INT des1_player_in_car

// sequences
LVAR_INT des1_starter_seq

/// route finding stuff ///
LVAR_INT des1_player_index des1_temp_index

//coords
LVAR_FLOAT des1_ROUTE_x[35]
LVAR_FLOAT des1_ROUTE_y[35]
LVAR_FLOAT des1_ROUTE_z[35]
LVAR_INT des1_route_length
LVAR_FLOAT des1_playerx des1_playery des1_playerz

// check-points
LVAR_INT des1_checkpoint


// leader board stuff

LVAR_INT des1_board_time[5]
VAR_TEXT_LABEL des1_board_name[5]
LVAR_INT des1_player_ranking
LVAR_INT des1_ranking_menu


/// ADUIO STUFF ///
VAR_TEXT_LABEL des1_text[17]
LVAR_INT des1_audio[17]
LVAR_INT des1_audio_counter
LVAR_INT des1_audio_slot1 des1_audio_slot2 des1_audio_playing
LVAR_INT desert1_text_loop_done
LVAR_INT des1_mobile
LVAR_INT des1_text_timer_diff 
LVAR_INT des1_text_timer_end 
LVAR_INT des1_text_timer_start
LVAR_INT des1_ahead_counter

// ****************************************Mission Start************************************
mission_start_toreno1:
flag_player_on_mission = 1

REGISTER_MISSION_GIVEN

SET_FADING_COLOUR 0 0 0

FORCE_WEATHER WEATHER_SUNNY_COUNTRYSIDE

LOAD_MISSION_TEXT TORENO1
LOAD_CUTSCENE DESERT1

WHILE NOT HAS_CUTSCENE_LOADED  
	WAIT 0
ENDWHILE
CLEAR_AREA -694.4335 964.9288 11.2431 100.0 TRUE
 
START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE
 
SET_PLAYER_CONTROL player1 OFF

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE
CLEAR_CUTSCENE
RELEASE_WEATHER

WAIT 0
// *************************************Set Flags/variables*********************************


des1_counter 					 = 0
des1_task_status				 = 0
des1_reward						 = 0
des1_timer_start				 = 0
des1_timer_current				 = 0
des1_timer_end					 = 0
des1_total_time					 = 0
des1_minutes					 = 0
des1_seconds					 = 0
des1_out_of_car_start			 = 0
des1_out_of_car_current			 = 0
des1_mission_progression 		 = 0
des1_safety_flag 				 = 0
des1_car_blip_vis				 = 0
des1_player_in_car				 = 0
des1_counter					 = 0
des1_temp_counter				 = 0

des1_player_index = 0
des1_route_length = 33
des1_number_of_check_points = 35
des1_current_check_point = 0

des1x_pos[0]   = 582.7242
des1y_pos[0]   = 356.2812
des1x_scale[0] = 60.2072// may need to be increased 
des1y_scale[0] = 52.2072 

des1x_pos[1]   = 581.7789
des1y_pos[1]   = 329.3647
des1x_scale[1] = 0.5427 
des1y_scale[1] = 2.0841 

des1x_pos[2]   = 569.0
des1y_pos[2]   = 323.5937
des1x_scale[2] = 0.7585 
des1y_scale[2] = 3.8106 

des1x_pos[3]   = 577.7526
des1y_pos[3]   = 335.6341
des1x_scale[3] = 0.4929 
des1y_scale[3] = 1.8511

des1x_pos[4]   = 583.3384 // time placing
des1y_pos[4]   = 355.0323
des1x_scale[4] = 0.4993 
des1y_scale[4] = 2.6370 

des1x_pos[5]   = 582.7242
des1y_pos[5]   = 356.2812
des1x_scale[5] = 64.2072// may need to be increased 
des1y_scale[5] = 56.2072 

des1x_pos[6]   = 582.7242
des1y_pos[6]   = 356.2812
des1x_scale[6] = 66.2072 // may need to be increased 
des1y_scale[6] = 58.2072


/////// CO-ORDINATES ////////////


DES1_ROUTE_X[0] = -824.7935 
DES1_ROUTE_Y[0] = 1078.2754 
DES1_ROUTE_Z[0] = 34.9479 

DES1_ROUTE_X[1] = -955.9566  //-920.2867 
DES1_ROUTE_Y[1] = 1359.6206//1408.6897 
DES1_ROUTE_Z[1] = 33.8826//30.5854 

DES1_ROUTE_X[2] = -1030.9991  //-1095.8743 
DES1_ROUTE_Y[2] = 1446.2977//1648.5925 
DES1_ROUTE_Z[2] = 38.340//33.5612 

DES1_ROUTE_X[3] = -1132.6752  //-1170.2841 
DES1_ROUTE_Y[3] = 1759.1525//1801.9480 
DES1_ROUTE_Z[3] = 35.3673//40.4073 


/////////////////////

DES1_ROUTE_X[4] =  -1088.9204 //-1114.5194 1873.5863 65.4108
DES1_ROUTE_Y[4] =  1981.8010 
DES1_ROUTE_Z[4] = 125.1471

DES1_ROUTE_X[5] = -990.0067  //-987.7365   
DES1_ROUTE_Y[5] = 2014.0245//1991.0751
DES1_ROUTE_Z[5] = 113.5758//115.6293 


DES1_ROUTE_X[6] = -1028.1370  //-1064.1555   
DES1_ROUTE_Y[6] = 2116.4675//2129.4456 
DES1_ROUTE_Z[6] = 88.2456//86.7735 


///////////////

DES1_ROUTE_X[7] = -1102.4310 
DES1_ROUTE_Y[7] = 2369.9448 
DES1_ROUTE_Z[7] = 84.0834


DES1_ROUTE_X[8] = -1130.0396 
DES1_ROUTE_Y[8] = 2323.3086 
DES1_ROUTE_Z[8] = 87.9125

DES1_ROUTE_X[9] = -1150.1838 
DES1_ROUTE_Y[9] = 2261.9431 
DES1_ROUTE_Z[9] = 96.1878

DES1_ROUTE_X[10] = -1293.3766  //-1327.9843 
DES1_ROUTE_Y[10] = 2221.6052//2250.6079 
DES1_ROUTE_Z[10] = 98.3220//96.9375

DES1_ROUTE_X[11] = -1349.9104  //-1341.2506 
DES1_ROUTE_Y[11] = 2342.3962//2393.0906 
DES1_ROUTE_Z[11] = 94.5565//94.5539



//////////////////////////////////  

DES1_ROUTE_X[12] = -1285.2140 
DES1_ROUTE_Y[12] = 2441.4224 
DES1_ROUTE_Z[12] = 88.5570 

DES1_ROUTE_X[13] = -1248.6062   
DES1_ROUTE_Y[13] = 2596.9902 
DES1_ROUTE_Z[13] = 89.817


////////////////////////////////// 

DES1_ROUTE_X[14] = -1174.8699 
DES1_ROUTE_Y[14] = 2304.5889 
DES1_ROUTE_Z[14] = 113.1954 


DES1_ROUTE_X[15] = -1094.3529 
DES1_ROUTE_Y[15] = 2304.9487 
DES1_ROUTE_Z[15] = 85.4543 

//////////////////////////////// 

DES1_ROUTE_X[16] = -1108.6028  //-1113.7892 	
DES1_ROUTE_Y[16] = 2491.6934//2494.0642 
DES1_ROUTE_Z[16] = 79.8438//81.3487 

DES1_ROUTE_X[17] = -1224.7633 
DES1_ROUTE_Y[17] = 2680.3228 
DES1_ROUTE_Z[17] = 46.8193 

DES1_ROUTE_X[18] = -1333.6726  //-1384.6338 PETROL STATION  
DES1_ROUTE_Y[18] = 2660.2051//2673.7705 
DES1_ROUTE_Z[18] = 49.0624//52.8626 

DES1_ROUTE_X[19] = -1622.4323   
DES1_ROUTE_Y[19] = 2670.2620 
DES1_ROUTE_Z[19] = 53.5027 



/////////////////////////////
DES1_ROUTE_X[20] = -1646.6423 
DES1_ROUTE_Y[20] = 2478.2905 
DES1_ROUTE_Z[20] = 85.8330 

 
DES1_ROUTE_X[21] = -1742.6581 
DES1_ROUTE_Y[21] = 2458.6633 
DES1_ROUTE_Z[21] = 71.4663

///////////////////////////// 

DES1_ROUTE_X[22] = -1687.2998 
DES1_ROUTE_Y[22] = 2394.6926 
DES1_ROUTE_Z[22] = 58.0739 

DES1_ROUTE_X[23] = -1752.3837 
DES1_ROUTE_Y[23] = 2299.2939 
DES1_ROUTE_Z[23] = 37.9221 

DES1_ROUTE_X[24] = -1687.5840 
DES1_ROUTE_Y[24] = 2336.4053 
DES1_ROUTE_Z[24] = 32.4859 

DES1_ROUTE_X[25] = -1575.4052 
DES1_ROUTE_Y[25] = 2275.4121 
DES1_ROUTE_Z[25] = 55.2948 

DES1_ROUTE_X[26] = -1508.8157   
DES1_ROUTE_Y[26] = 1929.0089 
DES1_ROUTE_Z[26] = 46.1035 

DES1_ROUTE_X[27] = -1330.1467 
DES1_ROUTE_Y[27] = 1888.0103 
DES1_ROUTE_Z[27] = 46.2572 

DES1_ROUTE_X[28] = -1147.8496   
DES1_ROUTE_Y[28] = 1721.9263 
DES1_ROUTE_Z[28] = 43.4100 

DES1_ROUTE_X[29] = -1036.4755    //-1019.4760 
DES1_ROUTE_Y[29] = 1564.2968//1518.8257 
DES1_ROUTE_Z[29] = 32.8195//41.3999 


DES1_ROUTE_X[30] = -995.6179 
DES1_ROUTE_Y[30] = 1395.2605 
DES1_ROUTE_Z[30] = 40.9086



DES1_ROUTE_X[31] = -810.2653  //-833.2089 
DES1_ROUTE_Y[31] = 1149.1143//1195.7451 
DES1_ROUTE_Z[31] = 33.0240//32.8746 

DES1_ROUTE_X[32] = -702.6020 
DES1_ROUTE_Y[32] = 1118.1898 
DES1_ROUTE_Z[32] = 35.5621

DES1_ROUTE_X[33] = -700.9643 
DES1_ROUTE_Y[33] = 955.2460 
DES1_ROUTE_Z[33] = 12.7168 


des1_counter = 0
WHILE des1_counter < 34
DES1_ROUTE_Z[des1_counter] -= 1.0
des1_counter++
ENDWHILE

des1_counter = 0


////////////////// AUDIO CRAP /////////////////////////

des1_audio_counter  	= 0 
des1_audio_slot1 		= 1
des1_audio_slot2 		= 2
des1_audio_playing		= 0
desert1_text_loop_done 	= 0
des1_mobile		    	= 0
des1_text_timer_diff 	= 0
des1_text_timer_end 	= 0 
des1_text_timer_start 	= 0
des1_ahead_counter 		= 0 

/// TEXT LABELS ////
$des1_text[1] = DES1_BA//Here's the deal, this is all about speed and commitment.
$des1_text[2] = DES1_BB//You got a GPS in the cab.Get to each set of map coords as quick as you can.
$des1_text[3] = DES1_BC//Make it to all the coordinates then get the truck back here.
$des1_text[4] = DES1_BD//Lose the truck and you fail.
$des1_text[5] = DES1_BE//What's a GPS? Fail what? Who are you?
$des1_text[6] = DES1_BF//Sorry, need-to-know basis only!
$des1_text[7] = DES1_BG//Oh, one more thing, this baby's got four-wheel steering.
$des1_text[8] = DES1_BH//Goodluck !


$des1_text[9] = DES1_CA//Ok, the boss was right about you!
$des1_text[10] = DES1_CB//Not bad at all, I'm semi-hard.
$des1_text[11] = DES1_CC//Boy, by the skin of your teeth.
$des1_text[12] = DES1_CD//The boss will be in touch.
$des1_text[13] = DES1_CE//Sorry pal, you're not what the boss is looking for.
$des1_text[14] = DES1_CF//Luck.
$des1_text[15] = DES1_CG//Pretty good for a ghetto boy...

$des1_text[16]  = DES1_BJ	//And third, who the fuck are you?

/// ADUIO LABELS ////
des1_audio[1] = SOUND_DES1_BA//Here's the deal, this is all about speed and commitment.
des1_audio[2] = SOUND_DES1_BB//You got a GPS in the cab.Get to each set of map coords as quick as you can.
des1_audio[3] = SOUND_DES1_BC//Make it to all the coordinates then get the truck back here.
des1_audio[4] = SOUND_DES1_BD//Lose the truck and you fail.
des1_audio[5] = SOUND_DES1_BE//What's a GPS? Fail what? Who are you?
des1_audio[6] = SOUND_DES1_BF//Sorry, need-to-know basis only!
des1_audio[7] = SOUND_DES1_BG//Oh, one more thing, this baby's got four-wheel steering.
des1_audio[8] = SOUND_DES1_BH//Goodluck !


des1_audio[9] = SOUND_DES1_CA//Ok, the boss was right about you!
des1_audio[10] = SOUND_DES1_CB//Not bad at all, I'm semi-hard.
des1_audio[11] = SOUND_DES1_CC//Boy, by the skin of your teeth.
des1_audio[12] = SOUND_DES1_CD//The boss will be in touch.
des1_audio[13] = SOUND_DES1_CE//Sorry pal, you're not what the boss is looking for.
des1_audio[14] = SOUND_DES1_CF//Luck.
des1_audio[15] = SOUND_DES1_CG//Pretty good for a ghetto boy...
des1_audio[16] = SOUND_DES1_BJ	//And third, who the fuck are you?


$des1_board_name[0]	= DES1_45 //beddoes
$des1_board_name[1]	= DES1_46 // mcmahon
$des1_board_name[2]	= DES1_47 // bolt
$des1_board_name[3]	= DES1_48 // Taylor
$des1_board_name[4]	= DES1_49 // Wong



des1_board_time[0] = 260 // me. though it may be possible to go faster i hit some cars in last section	(4 min 17 sec best by me so far)
des1_board_time[1] = 300 // chris macmahon
des1_board_time[2] = 330 // kevin bolt
des1_board_time[3] = 360 // steven taylor
des1_board_time[4] = 390 // keven Wong

des1_player_ranking = 0


// ****************************************START OF CUTSCENE********************************
// ******************************************END OF CUTSCENE********************************
SET_FADING_COLOUR 0 0 0
WAIT 0
//------------------REQUEST_MODELS ------------------------------

DO_FADE 1500 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

SET_PLAYER_CONTROL player1 OFF
SWITCH_WIDESCREEN ON

REQUEST_MODEL MONSTER
REQUEST_MODEL WBDYG2
REQUEST_ANIMATION GANGS
//REQUEST_MODEL briefcase

WHILE NOT HAS_MODEL_LOADED MONSTER
OR NOT HAS_MODEL_LOADED WBDYG2
OR NOT HAS_ANIMATION_LOADED GANGS
//OR NOT HAS_MODEL_LOADED briefcase
WAIT 0
ENDWHILE

CLEAR_AREA -694.4335 964.9288 11.2431 50.0 TRUE

CREATE_CAR MONSTER 	-694.4335 964.9288 11.2431 des1_player_car
SET_CAR_HEADING des1_player_car  85.8500
FREEZE_CAR_POSITION des1_player_car TRUE
CREATE_CHAR PEDTYPE_MISSION1 WBDYG2 -698.2989 963.0260 11.3035 des1_starter_ped 
SET_CHAR_HEADING des1_starter_ped 211.0638 
GET_CAR_HEALTH des1_player_car des1_car_health


///////// have cutscene here ///////

SET_CHAR_COORDINATES scplayer -697.7833 961.7714 11.2955 
SET_CHAR_HEADING scplayer 15.8778

SET_FIXED_CAMERA_POSITION -699.15 961.41 12.76 0.0 0.0 0.0
POINT_CAMERA_AT_POINT -685.70 971.97 13.10 JUMP_CUT

//CREATE_OBJECT briefcase -719.5559 980.5707 11.1371  des1_merchandise_obj

DISPLAY_CAR_NAMES FALSE
DISPLAY_ZONE_NAMES FALSE


DO_FADE 1500 FADE_IN
WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

IF NOT IS_CHAR_DEAD des1_starter_ped
	TASK_LOOK_AT_CHAR scplayer des1_starter_ped	20000
	CLEAR_CHAR_TASKS des1_starter_ped
ENDIF

des1_audio_counter  = 0 
des1_audio_slot1 	= 1
des1_audio_slot2 	= 2
des1_audio_playing	= 0
des1_ahead_counter = 0
desert1_text_loop_done = 0

SKIP_CUTSCENE_START

desert1_text_loop1:
WAIT 0 

IF desert1_text_loop_done = 0
	
	//////////////////////////////////////////////////////////////////
	/// PLAY THE SPEECH SAMPLES ALONG WITH SOME TALKING ANIMATIONS ///
	//////////////////////////////////////////////////////////////////

	IF NOT des1_audio_counter = 0
		IF des1_audio_playing = 0
			IF HAS_MISSION_AUDIO_LOADED des1_audio_slot2
				CLEAR_MISSION_AUDIO des1_audio_slot2
			ENDIF
			des1_audio_playing = 1
		ENDIF

		IF des1_audio_playing = 1
			LOAD_MISSION_AUDIO des1_audio_slot1 des1_audio[des1_audio_counter]
			des1_audio_playing = 2
		ENDIF

		IF des1_audio_playing = 2
		 	IF HAS_MISSION_AUDIO_LOADED des1_audio_slot1
				PLAY_MISSION_AUDIO des1_audio_slot1
				PRINT_NOW $des1_text[des1_audio_counter] 10000 1
				des1_audio_playing = 3
			ENDIF
		ENDIF

		IF des1_audio_playing = 3
			IF HAS_MISSION_AUDIO_FINISHED des1_audio_slot1
				CLEAR_THIS_PRINT $des1_text[des1_audio_counter]
				
				IF NOT IS_CHAR_DEAD des1_starter_ped
					STOP_CHAR_FACIAL_TALK des1_starter_ped
				ENDIF
				STOP_CHAR_FACIAL_TALK scplayer
				
				
				IF des1_audio_slot1 = 1
					des1_audio_slot1 = 2
					des1_audio_slot2 = 1
				ELSE
					des1_audio_slot1 = 1
					des1_audio_slot2 = 2
				ENDIF
				des1_audio_counter = 0
				des1_audio_playing = 0
			ELSE
				IF NOT HAS_MISSION_AUDIO_LOADED des1_audio_slot2
					IF des1_audio_counter < 8
						des1_ahead_counter = des1_audio_counter + 1
						LOAD_MISSION_AUDIO des1_audio_slot2 des1_audio[des1_ahead_counter]
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
	SWITCH des1_mobile
			CASE 0
				IF des1_audio_playing = 0
					IF NOT IS_CHAR_DEAD des1_starter_ped
					 	STOP_CHAR_FACIAL_TALK des1_starter_ped
					 	START_CHAR_FACIAL_TALK des1_starter_ped	 4000									
						TASK_PLAY_ANIM des1_starter_ped  prtial_gngtlkA	GANGS 4.0 FALSE FALSE FALSE FALSE -1 // latest addition
					ENDIF
					des1_audio_counter = 1	// //Here's the deal, this is all about speed and commitment.
					des1_mobile = 1
					GET_GAME_TIMER des1_text_timer_start
				ENDIF
				BREAK
			CASE 1
				GET_GAME_TIMER des1_text_timer_end
				des1_text_timer_diff = des1_text_timer_end - des1_text_timer_start
				IF des1_text_timer_diff > 1000
					IF des1_audio_playing = 0
						IF NOT IS_CHAR_DEAD des1_starter_ped
					 	STOP_CHAR_FACIAL_TALK des1_starter_ped
					 	START_CHAR_FACIAL_TALK des1_starter_ped	 4000									
						TASK_PLAY_ANIM des1_starter_ped  prtial_gngtlkB	GANGS 4.0 FALSE FALSE FALSE FALSE -1 // latest addition
						ENDIF
						des1_audio_counter = 2 //You got a GPS in the cab.Get to each set of map coords as quick as you can.	
						des1_mobile = 2
						GET_GAME_TIMER des1_text_timer_start
					ENDIF
				ENDIF	
				BREAK
			CASE 2
				GET_GAME_TIMER des1_text_timer_end
				des1_text_timer_diff = des1_text_timer_end - des1_text_timer_start
				IF des1_text_timer_diff > 1000
					IF des1_audio_playing = 0
						IF NOT IS_CHAR_DEAD des1_starter_ped
					 	STOP_CHAR_FACIAL_TALK des1_starter_ped
					 	START_CHAR_FACIAL_TALK des1_starter_ped	 4000									
						TASK_PLAY_ANIM des1_starter_ped  prtial_gngtlkF	GANGS 4.0 FALSE FALSE FALSE FALSE -1 // latest addition
						ENDIF
						des1_audio_counter = 3 //Make it to all the coordinates then get the truck back here.	
						des1_mobile = 3
						GET_GAME_TIMER des1_text_timer_start
					ENDIF
				ENDIF
				BREAK
			CASE 3
				GET_GAME_TIMER des1_text_timer_end
				des1_text_timer_diff = des1_text_timer_end - des1_text_timer_start
				IF des1_text_timer_diff > 1000
					IF des1_audio_playing = 0
						IF NOT IS_CHAR_DEAD des1_starter_ped
					 	STOP_CHAR_FACIAL_TALK des1_starter_ped
					 	START_CHAR_FACIAL_TALK des1_starter_ped	 4000															
						TASK_PLAY_ANIM des1_starter_ped  prtial_gngtlkH	GANGS 4.0 FALSE FALSE FALSE FALSE -1 // latest addition
						ENDIF
						des1_audio_counter = 4	//Lose the truck and you fail.
						des1_mobile = 4
						GET_GAME_TIMER des1_text_timer_start
					ENDIF
				ENDIF
				BREAK

			CASE 4
				GET_GAME_TIMER des1_text_timer_end
				des1_text_timer_diff = des1_text_timer_end - des1_text_timer_start
				IF des1_text_timer_diff > 1000
					IF des1_audio_playing = 0
						IF NOT IS_CHAR_DEAD des1_starter_ped
					 	STOP_CHAR_FACIAL_TALK des1_starter_ped
					 	START_CHAR_FACIAL_TALK scplayer	 4000									
						CLEAR_CHAR_TASKS des1_starter_ped 
						//TASK_PLAY_ANIM des1_starter_ped  IDLE_stance PED 4.0 FALSE FALSE FALSE FALSE -1 // latest addition
						CLEAR_CHAR_TASKS des1_starter_ped
						ENDIF						
						
						OPEN_SEQUENCE_TASK des1_starter_seq																													
						TASK_PLAY_ANIM -1  prtial_gngtlkF	GANGS 4.0 FALSE FALSE FALSE FALSE -1 // latest addition		
						TASK_PLAY_ANIM -1  prtial_gngtlkD	GANGS 4.0 FALSE FALSE FALSE FALSE -1 // latest addition		
						CLOSE_SEQUENCE_TASK des1_starter_seq
						PERFORM_SEQUENCE_TASK scplayer des1_starter_seq
						CLEAR_SEQUENCE_TASK des1_starter_seq
						
						
						des1_audio_counter = 5 //What's a GPS? Fail what? Who are you?	
						des1_mobile = 5
						GET_GAME_TIMER des1_text_timer_start
					ENDIF
				ENDIF
				BREAK			
			CASE 5
				GET_GAME_TIMER des1_text_timer_end
				des1_text_timer_diff = des1_text_timer_end - des1_text_timer_start
				IF des1_text_timer_diff > 1000
					IF des1_audio_playing = 0
						
					 	STOP_CHAR_FACIAL_TALK scplayer
					 	START_CHAR_FACIAL_TALK scplayer	 4000									
						TASK_PLAY_ANIM scplayer  prtial_gngtlkB	GANGS 4.0 FALSE FALSE FALSE FALSE -1 // latest addition
						des1_audio_counter = 16 //And third, who the fuck are you?	
						des1_mobile = 6
						GET_GAME_TIMER des1_text_timer_start
					ENDIF
				ENDIF
			BREAK									
			
			CASE 6
				GET_GAME_TIMER des1_text_timer_end
				des1_text_timer_diff = des1_text_timer_end - des1_text_timer_start
				IF des1_text_timer_diff > 1000
					IF des1_audio_playing = 0
						CLEAR_CHAR_TASKS scplayer
						IF NOT IS_CHAR_DEAD des1_starter_ped
					 	STOP_CHAR_FACIAL_TALK scplayer
					 	START_CHAR_FACIAL_TALK des1_starter_ped	 4000															
						TASK_PLAY_ANIM des1_starter_ped  prtial_gngtlkH	GANGS 4.0 FALSE FALSE FALSE FALSE -1 // latest addition
						ENDIF
						des1_audio_counter = 6//Sorry, need-to-know basis only!	
						des1_mobile = 7
						GET_GAME_TIMER des1_text_timer_start
					ENDIF
				ENDIF
				BREAK
			CASE 7
				GET_GAME_TIMER des1_text_timer_end
				des1_text_timer_diff = des1_text_timer_end - des1_text_timer_start
				IF des1_text_timer_diff > 1000
					IF des1_audio_playing = 0
						IF NOT IS_CHAR_DEAD des1_starter_ped
					 	STOP_CHAR_FACIAL_TALK des1_starter_ped
					 	START_CHAR_FACIAL_TALK des1_starter_ped	 4000																												
						OPEN_SEQUENCE_TASK des1_starter_seq																	
						TASK_PLAY_ANIM -1  prtial_gngtlkE	GANGS 4.0 FALSE FALSE FALSE FALSE -1 // latest addition		
						TASK_PLAY_ANIM -1  prtial_gngtlkB	GANGS 4.0 FALSE FALSE FALSE FALSE -1 // latest addition		
						CLOSE_SEQUENCE_TASK des1_starter_seq
						PERFORM_SEQUENCE_TASK des1_starter_ped des1_starter_seq
						CLEAR_SEQUENCE_TASK des1_starter_seq
						
						
						ENDIF
						
						des1_audio_counter = 7 //Oh, one more thing, this baby's got four-wheel steering instead.	
						des1_mobile = 8
						GET_GAME_TIMER des1_text_timer_start
					ENDIF
				ENDIF
				BREAK
			CASE 8
				GET_GAME_TIMER des1_text_timer_end
				des1_text_timer_diff = des1_text_timer_end - des1_text_timer_start
				IF des1_text_timer_diff > 1000
					IF des1_audio_playing = 0
						IF NOT IS_CHAR_DEAD des1_starter_ped
							CLEAR_CHAR_TASKS des1_starter_ped
							//TASK_PLAY_ANIM des1_starter_ped  prtial_gngtlkB	GANGS 4.0 FALSE FALSE FALSE FALSE -1 // latest addition							
							TASK_PLAY_ANIM	des1_starter_ped IDLE_chat PED 4.0 FALSE FALSE FALSE FALSE -1
						ENDIF
						IF NOT IS_CHAR_DEAD des1_starter_ped
					 	STOP_CHAR_FACIAL_TALK des1_starter_ped
					 	START_CHAR_FACIAL_TALK des1_starter_ped	 4000									
						ENDIF
						des1_audio_counter = 8 //Goodluck !	
						des1_mobile = 9
						GET_GAME_TIMER des1_text_timer_start
					ENDIF
				ENDIF
				BREAK
			DEFAULT
				GET_GAME_TIMER des1_text_timer_end
				des1_text_timer_diff = des1_text_timer_end - des1_text_timer_start
				IF des1_text_timer_diff > 1000
					IF des1_audio_playing = 0
					   IF NOT IS_CHAR_DEAD des1_starter_ped
					 		STOP_CHAR_FACIAL_TALK des1_starter_ped
						ENDIF
					   desert1_text_loop_done = 1	
					ENDIF
				ENDIF
				BREAK
		ENDSWITCH
	
	GOTO desert1_text_loop1
ENDIF

//////////////////////////////////////

SKIP_CUTSCENE_END

IF NOT IS_CHAR_DEAD des1_starter_ped
	STOP_CHAR_FACIAL_TALK des1_starter_ped
ENDIF
STOP_CHAR_FACIAL_TALK scplayer
					 	
CLEAR_MISSION_AUDIO des1_audio_slot1
CLEAR_MISSION_AUDIO des1_audio_slot2

CLEAR_CHAR_TASKS scplayer
IF NOT IS_CHAR_DEAD des1_starter_ped
CLEAR_CHAR_TASKS des1_starter_ped
ENDIF
 
CLEAR_PRINTS

/////////////////////////////////////////////////////////////////////
/// CALCULATE THE TIME THE PLAYER HAS TO BEAT TO PASS THE MISSION ///
/////////////////////////////////////////////////////////////////////

des1_minutes = des1_board_time[4] / 60
des1_temp_int = des1_minutes * 60
des1_seconds = des1_board_time[4] - des1_temp_int

SKIP_CUTSCENE_START
IF des1_seconds > 9
	PRINT_WITH_2_NUMBERS_NOW (DES1_69) des1_minutes des1_seconds 4000 1//~s~You need to get a time better than ~1~:~1~.
ELSE
	PRINT_WITH_2_NUMBERS_NOW (DES1_70) des1_minutes des1_seconds 4000 1//~s~You need to get a time better than ~1~:0~1~.
ENDIF

///////////////////////////////////
WHILE IS_MESSAGE_BEING_DISPLAYED
	WAIT 0	 			 
ENDWHILE

SKIP_CUTSCENE_END

CLEAR_PRINTS

DO_FADE 1000 FADE_OUT
WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE





DELETE_CHAR des1_starter_ped
SET_CHAR_COORDINATES scplayer -691.7533 960.2396 11.2090



/////////////////////////////////////
/////// LEADER BOARD STUFF //////////
/////////////////////////////////////



SKIP_CUTSCENE_START


USE_TEXT_COMMANDS TRUE







SKIP_CUTSCENE_END
CLEAR_PRINTS
DELETE_MENU des1_ranking_menu

//////////////////////////////////////////
//////// END OF LEADER BOARD STUFF ///////
//////////////////////////////////////////

REMOVE_ANIMATION GANGS
CLEAR_LOOK_AT scplayer
RESTORE_CAMERA_JUMPCUT
//DELETE_OBJECT des1_merchandise_obj
CLEAR_CHAR_TASKS scplayer
PRINT (DES1_10) 10000 1 //~s~ Get in the monster truck.
IF NOT IS_CAR_DEAD des1_player_car
	FREEZE_CAR_POSITION des1_player_car FALSE
	DELETE_CHAR des1_starter_ped
	ADD_BLIP_FOR_CAR des1_player_car des1_route_blip
	SET_BLIP_AS_FRIENDLY des1_route_blip TRUE 
ENDIF

SET_CHAR_COORDINATES scplayer -697.7833 961.7714 11.2955 
SET_CHAR_HEADING scplayer 15.8778
				  	

RESTORE_CAMERA_JUMPCUT
SWITCH_WIDESCREEN OFF

DO_FADE 1000 FADE_IN
WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

SET_PLAYER_CONTROL player1 ON

mission_toreno1_loop:

WAIT 0  


IF des1_mission_progression = 0				
	IF NOT IS_CAR_DEAD des1_player_car
		IF IS_CHAR_SITTING_IN_CAR scplayer des1_player_car
		   
		   CLEAR_PRINTS		   
		   REMOVE_BLIP des1_route_blip		   
		   ADD_BLIP_FOR_COORD -719.5559 980.5707 11.1371  des1_route_blip
		   SET_COORD_BLIP_APPEARANCE   des1_route_blip COORD_BLIP_APPEARANCE_ENEMY		   
		   CREATE_CHECKPOINT CHECKPOINT_TUBE -719.5559 980.5707 11.1371  des1_ROUTE_x[des1_player_index] des1_ROUTE_y[des1_player_index] des1_ROUTE_z[des1_player_index] 6.0 des1_checkpoint				
		   PRINT (DES1_58) 5000 1//~Get to the first check-point.~PRINT (DES1_44) 1000 1//~s~ Get to the next check-point.			 	  
		   
		   GET_CONTROLLER_MODE des1_controller_mode
		   IF des1_controller_mode < 2
				PRINT_HELP_FOREVER DES1_59
		   ELSE
				IF des1_controller_mode = 2
					PRINT_HELP_FOREVER DES1_60
				ELSE
					PRINT_HELP_FOREVER DES1_61
				ENDIF
		   ENDIF

		   des1_car_blip_vis = 1
		   des1_mission_progression = 1
		ELSE
			// PLAYER LEAVES MONSTER TRUCK \\
			CLEAR_PRINTS
			CLEAR_HELP
			PRINT (DES1_10) 1000 1 //~s~ Get in the monster truck.
		ENDIF
	ELSE
		// TRUCK HAS BEEN DESTROYED \\
		CLEAR_PRINTS
		PRINT (DES1_11) 2000 1 //~r~ How are you going to race without a car!
		GOTO mission_toreno1_failed
	ENDIF
ENDIF



											  
IF des1_mission_progression = 1
	IF NOT IS_CAR_DEAD des1_player_car
		IF IS_CHAR_SITTING_IN_CAR scplayer des1_player_car
			IF des1_car_blip_vis = 0
			   // IF THE PLAYER HAS PREVIOUSLY LEFT THE TRUCK ADD THE NEXT CHECK-POINT AND HELP MESSAGE \\
			   CLEAR_PRINTS 
			   PRINT (DES1_58) 5000 1//~Get to the first check-point.~PRINT (DES1_44) 1000 1//~s~ Get to the next check-point.			 	  
			   REMOVE_BLIP des1_route_blip			   
			   ADD_BLIP_FOR_COORD -719.5559 980.5707 11.1371  des1_route_blip
			   CREATE_CHECKPOINT CHECKPOINT_TUBE -719.5559 980.5707 11.1371   des1_ROUTE_x[des1_player_index] des1_ROUTE_y[des1_player_index] des1_ROUTE_z[des1_player_index] 6.0 des1_checkpoint							   
			   SET_COORD_BLIP_APPEARANCE   des1_route_blip COORD_BLIP_APPEARANCE_ENEMY			   			   
			   GET_CONTROLLER_MODE des1_controller_mode
			   IF des1_controller_mode < 2
					PRINT_HELP_FOREVER DES1_59
			   ELSE
					IF des1_controller_mode = 2
						PRINT_HELP_FOREVER DES1_60
					ELSE
						PRINT_HELP_FOREVER DES1_61
					ENDIF
			   ENDIF
			   des1_car_blip_vis = 1			   
			ENDIF

			IF LOCATE_CHAR_IN_CAR_3D scplayer -719.5559 980.5707 11.1371  8.0 8.0 8.5 FALSE						
				CLEAR_PRINTS
				ADD_ONE_OFF_SOUND -719.5559 980.5707 11.1371 SOUND_PART_MISSION_COMPLETE
				REMOVE_BLIP des1_route_blip								
				DELETE_CHECKPOINT des1_checkpoint												
				GET_GAME_TIMER des1_timer_start
				des1_temp_index = 1
				CREATE_CHECKPOINT CHECKPOINT_TUBE des1_ROUTE_x[des1_player_index] des1_ROUTE_y[des1_player_index] des1_ROUTE_z[des1_player_index]  des1_ROUTE_x[des1_temp_index] des1_ROUTE_y[des1_temp_index] des1_ROUTE_z[des1_temp_index] 6.0 des1_checkpoint				
				ADD_BLIP_FOR_COORD des1_ROUTE_x[des1_player_index] des1_ROUTE_y[des1_player_index] des1_ROUTE_z[des1_player_index] des1_route_blip 				
				SET_COORD_BLIP_APPEARANCE   des1_route_blip COORD_BLIP_APPEARANCE_ENEMY								
				des1_player_in_car = 1																
				des1_mission_progression = 2							 			 	 
			 ENDIF
		ELSE // player not in car
			 
			 // IF PLAYER IS NOT IN A CAR REMOVE THE CHECKPOINT AND ITS BLIP, ADD BLIP FOR TRUCK AND PRINT MESSAGE \\ 
			 
			 IF des1_car_blip_vis = 1
		   		REMOVE_BLIP des1_route_blip				
				//REMOVE_BLIP des1_next_checkpoint_blip 
				DELETE_CHECKPOINT des1_checkpoint				
				ADD_BLIP_FOR_CAR des1_player_car des1_route_blip
				SET_BLIP_AS_FRIENDLY des1_route_blip TRUE 				
		   		des1_car_blip_vis = 0
			 	CLEAR_HELP
			 	CLEAR_PRINTS
			 ENDIF
			 PRINT (DES1_10) 4000 1 //~s~ Get in the monster truck.		
		ENDIF
	ELSE // car destroyed		 	
		
		// IF TRUCK IS DESTROYED FAIL THE MISSION \\

		CLEAR_PRINTS
		PRINT (DES1_11) 2000 1 //~r~ How are you going to race without a car!
		GOTO mission_toreno1_failed
	ENDIF
ENDIF


IF des1_mission_progression = 2
	 	 	  	
		//////////////////////////////
		/// ON SCREEN DISPLAY CODE ///
		//////////////////////////////			 	 
            
			DRAW_RECT des1x_pos[6] des1y_pos[6] des1x_scale[6] des1y_scale[6] 0 0 0 255
			DRAW_RECT des1x_pos[5] des1y_pos[5] des1x_scale[5] des1y_scale[5] 180 180 180 255
			DRAW_RECT des1x_pos[0] des1y_pos[0] des1x_scale[0] des1y_scale[0] 0 0 0 255

			des1_current_check_point = des1_temp_index
			GOSUB setup_text1_des1
			SET_TEXT_SCALE des1x_scale[3] des1y_scale[3]
			SET_TEXT_CENTRE ON
			IF NOT IS_JAPANESE_VERSION
				IF des1_current_check_point = 11
					SET_TEXT_PROPORTIONAL OFF
				ENDIF
			ENDIF

			DISPLAY_TEXT_WITH_2_NUMBERS 583.7526 des1y_pos[3] DES1_67 des1_current_check_point des1_number_of_check_points
			GOSUB setup_text1_des1
			SET_TEXT_SCALE des1x_scale[3] des1y_scale[3]

			GET_GAME_TIMER des1_timer_current	 

			des1_timer_temp = des1_timer_current - des1_timer_start  // Set these variables for the rendering subroutine.
			des1_timer_temp /= 1000
			GOSUB setup_text1_des1
			
			IF des1_timer_temp > des1_board_time[4]
				SET_TEXT_COLOUR 180 0 0 255
			ENDIF

			des1_minutes = des1_timer_temp / 60
			des1_temp_int = des1_minutes * 60
			des1_seconds = des1_timer_temp - des1_temp_int

			SET_TEXT_CENTRE ON
			SET_TEXT_SCALE des1x_scale[4] des1y_scale[4]

			IF NOT IS_JAPANESE_VERSION
				IF des1_seconds = 11
				OR des1_minutes = 11
					SET_TEXT_PROPORTIONAL OFF
				ENDIF
			ENDIF
			
			
			// IF PLAYER TAKES TOO LONG TO COMPLETE RACE, FAIL THE MISSION.
			IF des1_minutes > 99 
				CLEAR_PRINTS
				PRINT (DES1_28) 4000 1 //~r~ You're out of time!
				GOTO mission_toreno1_failed
			ENDIF

			
			// DISPLAY THE TIMER \\
			IF des1_seconds > 9
				DISPLAY_TEXT_WITH_2_NUMBERS des1x_pos[4] des1y_pos[4] DES1_65 des1_minutes des1_seconds//  ~1~:~1~
			ELSE
				DISPLAY_TEXT_WITH_2_NUMBERS des1x_pos[4] des1y_pos[4] DES1_66 des1_minutes des1_seconds//  ~1~:0~1~
			ENDIF


		///////////////////////////////
		/// END OF ONSCREEN DISPLAY ///
		///////////////////////////////
				
			   																		
		/////////////////////
		/// PLAYER CHECKS ///
		/////////////////////			
			
		IF NOT IS_CAR_DEAD des1_player_car
			IF NOT IS_CHAR_IN_CAR scplayer des1_player_car
				IF des1_player_in_car = 1			
					// IF PLAYER OUT OF TRUCK STUFF NOT DONE BEFORE ADD A BLIP FOR THE TRUCK AND START THE OUT OF TRUCK TIMER \\
					DELETE_CHECKPOINT des1_checkpoint					
					REMOVE_BLIP des1_route_blip					
					ADD_BLIP_FOR_CAR des1_player_car des1_route_blip					
					SET_BLIP_AS_FRIENDLY des1_route_blip TRUE
					GET_GAME_TIMER des1_out_of_car_start
					des1_player_in_car = 0					
				ELSE					
					 // CHECK HOW LONG PLAYER HAS BEEN OUT OF THE TRUCK \\
					 GET_GAME_TIMER des1_out_of_car_current	 
					 des1_timer_temp =  des1_out_of_car_current  - des1_out_of_car_start			 
					 des1_seconds = 10000 - des1_timer_temp
					 des1_seconds /= 1000
					 IF des1_seconds > 0
						// PRINT A MESSAGE INFORMING PLAYER OF HOW LONG HE HAS LEFT TO GET BACK IN THE TRUCK \\
						CLEAR_PRINTS
						ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS FALSE
						IF des1_seconds < 2
							PRINT_WITH_NUMBER (DES1_26) des1_seconds 4000 1 //~s~You have ~1~ second back in the truck
						ELSE						
							PRINT_WITH_NUMBER (DES1_25) des1_seconds 4000 1 //~s~You have ~1~ seconds to get back in the truck	
					 	ENDIF 					 	
					 ELSE
						// PLAYER IS OUT OF TIME TO GET BACK IN THE TRUCK \\
						CLEAR_PRINTS
						PRINT (DES1_27) 4000 1 //~r~ You been out of the truck for too long!						
						GOTO mission_toreno1_failed
					 ENDIF
				ENDIF
			ELSE
				IF des1_player_in_car = 0					
					// IF NOT DONE BEFORE AND PLAYER INSIDE TRUCK REMOVE TRUCK BLIP AND ADD CHECKPOINTS \\
					REMOVE_BLIP des1_route_blip
					CLEAR_PRINTS					
					PRINT (DES1_44) 4000 1// ~s~ Get to the next check-point.										
					IF des1_temp_index <= des1_route_length
						CREATE_CHECKPOINT CHECKPOINT_TUBE des1_ROUTE_x[des1_player_index] des1_ROUTE_y[des1_player_index] des1_ROUTE_z[des1_player_index]  des1_ROUTE_x[des1_temp_index] des1_ROUTE_y[des1_temp_index] des1_ROUTE_z[des1_temp_index] 6.0 des1_checkpoint
					ELSE
						CREATE_CHECKPOINT CHECKPOINT_ENDTUBE des1_ROUTE_x[des1_player_index] des1_ROUTE_y[des1_player_index] des1_ROUTE_z[des1_player_index] des1_ROUTE_x[des1_route_length] des1_ROUTE_y[des1_route_length] des1_ROUTE_z[des1_route_length]   6.0 des1_checkpoint
					ENDIF
					ADD_BLIP_FOR_COORD des1_ROUTE_x[des1_player_index] des1_ROUTE_y[des1_player_index] des1_ROUTE_z[des1_player_index] des1_route_blip							
					SET_COORD_BLIP_APPEARANCE   des1_route_blip COORD_BLIP_APPEARANCE_ENEMY					
					des1_player_in_car = 1
				ENDIF
			ENDIF
		ELSE
			// TRUCK HAS BEEN DESTROYED
			CLEAR_PRINTS
			PRINT (DES1_43) 4000 1 //~r~ You wrecked the truck!
			GOTO mission_toreno1_failed
		ENDIF
		
		////////////////////////////
		/// END OF PLAYER CHECKS ///
		////////////////////////////
   





	/////////////////////////////////////////////////////////////////////////
	/// code for checking players location in relation to the checkpoints ///
	/////////////////////////////////////////////////////////////////////////

		IF des1_player_in_car = 1													
			IF des1_player_index < des1_route_length
				IF LOCATE_CHAR_IN_CAR_3D scplayer des1_ROUTE_x[des1_player_index] des1_ROUTE_y[des1_player_index] des1_ROUTE_z[des1_player_index] 8.0 8.0 8.0 FALSE
					CLEAR_HELP
					ADD_ONE_OFF_SOUND des1_ROUTE_x[des1_player_index] des1_ROUTE_y[des1_player_index] des1_ROUTE_z[des1_player_index] SOUND_PART_MISSION_COMPLETE
					DELETE_CHECKPOINT des1_checkpoint				   
					REMOVE_BLIP des1_route_blip
																									
					des1_player_index ++
					des1_temp_index = des1_player_index + 1

					IF des1_player_index > des1_route_length
						des1_player_index = des1_route_length
					ENDIF					  

					IF des1_temp_index <= des1_route_length
						CREATE_CHECKPOINT CHECKPOINT_TUBE des1_ROUTE_x[des1_player_index] des1_ROUTE_y[des1_player_index] des1_ROUTE_z[des1_player_index]  des1_ROUTE_x[des1_temp_index] des1_ROUTE_y[des1_temp_index] des1_ROUTE_z[des1_temp_index] 6.0 des1_checkpoint						
						ADD_BLIP_FOR_COORD des1_ROUTE_x[des1_player_index] des1_ROUTE_y[des1_player_index] des1_ROUTE_z[des1_player_index] des1_route_blip							
						SET_COORD_BLIP_APPEARANCE   des1_route_blip COORD_BLIP_APPEARANCE_ENEMY						
					ELSE
						// HEADING TO LAST CHECKPOINT SO DISPLAY THE CHECKERED FLAG ONE \\
						CREATE_CHECKPOINT CHECKPOINT_ENDTUBE des1_ROUTE_x[des1_player_index] des1_ROUTE_y[des1_player_index] des1_ROUTE_z[des1_player_index] des1_ROUTE_x[des1_route_length] des1_ROUTE_y[des1_route_length] des1_ROUTE_z[des1_route_length]   6.0 des1_checkpoint
						ADD_BLIP_FOR_COORD des1_ROUTE_x[des1_player_index] des1_ROUTE_y[des1_player_index] des1_ROUTE_z[des1_player_index] des1_route_blip
						SET_COORD_BLIP_APPEARANCE   des1_route_blip COORD_BLIP_APPEARANCE_ENEMY
					ENDIF
					
					IF NOT IS_CAR_DEAD des1_player_car
						// GIVE THE TRUCK SOME MORE HEALTH WHEN IT PASSES THROUGH A CHECKPOINT \\
						GET_CAR_HEALTH des1_player_car des1_car_health
						des1_car_health += 200
						IF des1_car_health > 1000
							des1_car_health = 1000
						ENDIF
						SET_CAR_HEALTH des1_player_car des1_car_health	
					ENDIF
				ENDIF
			ELSE
				IF LOCATE_CHAR_IN_CAR_3D scplayer des1_ROUTE_x[des1_route_length] des1_ROUTE_y[des1_route_length] des1_ROUTE_z[des1_route_length] 4.0 4.0 8.0 FALSE
				   // PLAYER HAS REACHED THE LAST CHECK-POINT \\
				   DELETE_CHECKPOINT des1_checkpoint
				   REMOVE_BLIP des1_route_blip
				   GOSUB toreno1_end_cut_scene
				   IF des1_player_ranking < 5
				   		GOTO mission_toreno1_passed
				   ELSE
				   		PRINT_NOW (DES1_68) 5000 1 //~r~You did not make it into the top five.
				   		GOTO mission_toreno1_failed
				   ENDIF
				ENDIF

			    /// player at end of route
			ENDIF
		ENDIF							   		

ENDIF // des1_mission_progression = 1	


													

////////////////////////////////////
// DEBUG CRAP					  //
////////////////////////////////////

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_NUMPAD_MINUS
    des1_timer_start -= 1000
ENDIF


IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_RETURN
	GET_CHAR_COORDINATES scplayer des1_playerx des1_playery des1_playerz
	SET_CHAR_COORDINATES scplayer des1_playerx des1_playery des1_playerz
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SPACE
    des1_timer_end = 360000
    des1_timer_start = 0
    GOSUB toreno1_end_cut_scene
    GOTO mission_toreno1_passed
ENDIF


IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
    GOTO mission_toreno1_passed
ENDIF

GOTO mission_toreno1_loop



///////////////// 
///START CODE ///
/////////////////

toreno1_end_cut_scene:

	
	GET_GAME_TIMER des1_timer_end
	DO_FADE 1000 FADE_OUT	
	REQUEST_MODEL WBDYG2
	REQUEST_ANIMATION GANGS
	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON

	WHILE GET_FADING_STATUS
	WAIT 0
	ENDWHILE	 	
	
	WHILE NOT HAS_MODEL_LOADED WBDYG2	
	OR NOT HAS_ANIMATION_LOADED GANGS
	WAIT 0 
	ENDWHILE

	CLEAR_AREA -684.8021 948.1096 11.1250 100.0 TRUE
	
	IF NOT IS_CAR_DEAD des1_player_car
		SET_CAR_COORDINATES des1_player_car -680.7953 953.2972 11.1250 
		SET_CAR_HEADING des1_player_car 238.0439
	ENDIF
	
	IF IS_CHAR_IN_ANY_CAR scplayer
		WARP_CHAR_FROM_CAR_TO_COORD scplayer -684.8021 948.1096 11.1250
	ELSE
		SET_CHAR_COORDINATES scplayer -684.8021 948.1096 11.1250
	ENDIF
	SET_CHAR_HEADING scplayer 124.6699

	CREATE_CHAR PEDTYPE_MISSION3  WBDYG2 -687.6009 946.4823 12.0389   des1_starter_ped 
	SET_CHAR_HEADING des1_starter_ped  293.1942
	
	SET_FIXED_CAMERA_POSITION -688.32 945.02 14.13   0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -680.57 951.17 10.63 JUMP_CUT

	// SPLIT PALYERS TIME INTO MINUTES / SECONDS \\	 
	des1_total_time =  des1_timer_end  - des1_timer_start
	des1_total_time /= 1000 //gets time in seconds
	des1_minutes = des1_total_time / 60
	des1_temp_int = des1_minutes * 60
	des1_seconds = des1_total_time - des1_temp_int
	
	CLEAR_PRINTS
	DO_FADE 1500 FADE_IN
	WHILE GET_FADING_STATUS
	WAIT 0
	ENDWHILE
	 
	IF des1_seconds < 10
		PRINT_WITH_2_NUMBERS_NOW (DES1_32) des1_minutes des1_seconds 4000 1 //" ~1~:0~1~, lets see how you stand up against our guys."
	ELSE
		PRINT_WITH_2_NUMBERS_NOW (DES1_33) des1_minutes des1_seconds 4000 1 //" ~1~:~1~, lets see how you stand up against our guys."
	ENDIF
 			
	// FIND WHERE THE PLAYER PLACED COMPARED TO THE OTHER DRIVERS
	
	des1_player_ranking = 5
	des1_counter = 0
	WHILE des1_counter < 5
		IF des1_total_time < des1_board_time[des1_counter]
			des1_player_ranking--		  
		ENDIF
		des1_counter++
	ENDWHILE

	 	
	// DISPLAY THE HIGH-SCORE TABLE RELATING TO PLAYERS POSITION
					
	SWITCH des1_player_ranking
	CASE 0
		IF IS_JAPANESE_VERSION
			CREATE_MENU DES1_34 354.0 200.0 300.0 3 FALSE TRUE 0 des1_ranking_menu
		ELSE
			CREATE_MENU DES1_34 354.0 200.0 80.0 3 FALSE TRUE 0 des1_ranking_menu
		ENDIF
		SET_MENU_COLUMN des1_ranking_menu 0 DES1_52 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN des1_ranking_menu 1 DES1_53 DES1_50 $des1_board_name[0] $des1_board_name[1] $des1_board_name[2] $des1_board_name[3] DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN des1_ranking_menu 2 DES1_54 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		IF IS_JAPANESE_VERSION
			SET_MENU_COLUMN_WIDTH des1_ranking_menu 0 20 
			SET_MENU_COLUMN_WIDTH des1_ranking_menu 1 150 
			SET_MENU_COLUMN_WIDTH des1_ranking_menu 2 80 
		ENDIF
		des1_counter = 0
		WHILE des1_counter < 5
			des1_temp_int = des1_counter
			des1_temp_int += 1
			SET_MENU_ITEM_WITH_NUMBER des1_ranking_menu 0 des1_counter DES1_51 des1_temp_int
			des1_counter += 1
		ENDWHILE
		des1_temp_counter = 0
		des1_counter = 0

		WHILE des1_counter < 5
			IF des1_counter = 0
				des1_minutes = des1_total_time / 60
				des1_temp_int = des1_minutes * 60
				des1_seconds = des1_total_time - des1_temp_int
			ELSE
				des1_minutes = des1_board_time[des1_temp_counter] / 60
				des1_temp_int = des1_minutes * 60
				des1_seconds = des1_board_time[des1_temp_counter] - des1_temp_int
				ACTIVATE_MENU_ITEM des1_ranking_menu des1_counter FALSE
				des1_temp_counter += 1
			ENDIF
			IF des1_seconds < 10
				SET_MENU_ITEM_WITH_2_NUMBERS des1_ranking_menu 2 des1_counter DES1_66 des1_minutes des1_seconds
			ELSE
				SET_MENU_ITEM_WITH_2_NUMBERS des1_ranking_menu 2 des1_counter DES1_65 des1_minutes des1_seconds
			ENDIF
			des1_counter += 1
		ENDWHILE
		BREAK
	CASE 1
		IF IS_JAPANESE_VERSION
			CREATE_MENU DES1_34 354.0 200.0 300.0 3 FALSE TRUE 0 des1_ranking_menu
		ELSE
			CREATE_MENU DES1_34 354.0 200.0 80.0 3 FALSE TRUE 0 des1_ranking_menu
		ENDIF
		SET_MENU_COLUMN des1_ranking_menu 0 DES1_52 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN des1_ranking_menu 1 DES1_53 $des1_board_name[0] DES1_50 $des1_board_name[1] $des1_board_name[2] $des1_board_name[3] DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN des1_ranking_menu 2 DES1_54 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		IF IS_JAPANESE_VERSION
			SET_MENU_COLUMN_WIDTH des1_ranking_menu 0 20 
			SET_MENU_COLUMN_WIDTH des1_ranking_menu 1 150 
			SET_MENU_COLUMN_WIDTH des1_ranking_menu 2 80 
		ENDIF
		des1_counter = 0
		WHILE des1_counter < 5
			des1_temp_int = des1_counter
			des1_temp_int += 1
			SET_MENU_ITEM_WITH_NUMBER des1_ranking_menu 0 des1_counter DES1_51 des1_temp_int
			des1_counter += 1
		ENDWHILE
		des1_counter = 0
		des1_temp_counter = 0

		WHILE des1_counter < 5
			IF des1_counter = 1
				des1_minutes = des1_total_time / 60
				des1_temp_int = des1_minutes * 60
				des1_seconds = des1_total_time - des1_temp_int
			ELSE
				des1_minutes = des1_board_time[des1_temp_counter] / 60
				des1_temp_int = des1_minutes * 60
				des1_seconds = des1_board_time[des1_temp_counter] - des1_temp_int
				ACTIVATE_MENU_ITEM des1_ranking_menu des1_counter FALSE
				des1_temp_counter += 1
			ENDIF
			IF des1_seconds < 10
				SET_MENU_ITEM_WITH_2_NUMBERS des1_ranking_menu 2 des1_counter DES1_66 des1_minutes des1_seconds
			ELSE
				SET_MENU_ITEM_WITH_2_NUMBERS des1_ranking_menu 2 des1_counter DES1_65 des1_minutes des1_seconds
			ENDIF
			des1_counter += 1
		ENDWHILE
		BREAK
	CASE 2
		IF IS_JAPANESE_VERSION
			CREATE_MENU DES1_34 354.0 200.0 300.0 3 FALSE TRUE 0 des1_ranking_menu
		ELSE
			CREATE_MENU DES1_34 354.0 200.0 80.0 3 FALSE TRUE 0 des1_ranking_menu
		ENDIF
		SET_MENU_COLUMN des1_ranking_menu 0 DES1_52 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN des1_ranking_menu 1 DES1_53 $des1_board_name[0] $des1_board_name[1] DES1_50 $des1_board_name[2] $des1_board_name[3] DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN des1_ranking_menu 2 DES1_54 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		IF IS_JAPANESE_VERSION
			SET_MENU_COLUMN_WIDTH des1_ranking_menu 0 20 
			SET_MENU_COLUMN_WIDTH des1_ranking_menu 1 150 
			SET_MENU_COLUMN_WIDTH des1_ranking_menu 2 80 
		ENDIF
		des1_counter = 0
		WHILE des1_counter < 5
			des1_temp_int = des1_counter
			des1_temp_int += 1
			SET_MENU_ITEM_WITH_NUMBER des1_ranking_menu 0 des1_counter DES1_51 des1_temp_int
			des1_counter += 1
		ENDWHILE
		des1_temp_counter = 0
		des1_counter = 0

		WHILE des1_counter < 5
			IF des1_counter = 2
				des1_minutes = des1_total_time / 60
				des1_temp_int = des1_minutes * 60
				des1_seconds = des1_total_time - des1_temp_int
			ELSE
				des1_minutes = des1_board_time[des1_temp_counter] / 60
				des1_temp_int = des1_minutes * 60
				des1_seconds = des1_board_time[des1_temp_counter] - des1_temp_int
				ACTIVATE_MENU_ITEM des1_ranking_menu des1_counter FALSE
				des1_temp_counter += 1
			ENDIF
			IF des1_seconds < 10
				SET_MENU_ITEM_WITH_2_NUMBERS des1_ranking_menu 2 des1_counter DES1_66 des1_minutes des1_seconds
			ELSE
				SET_MENU_ITEM_WITH_2_NUMBERS des1_ranking_menu 2 des1_counter DES1_65 des1_minutes des1_seconds
			ENDIF
			des1_counter += 1
		ENDWHILE
		BREAK
	CASE 3
		IF IS_JAPANESE_VERSION
			CREATE_MENU DES1_34 354.0 200.0 300.0 3 FALSE TRUE 0 des1_ranking_menu
		ELSE
			CREATE_MENU DES1_34 354.0 200.0 80.0 3 FALSE TRUE 0 des1_ranking_menu
		ENDIF
		SET_MENU_COLUMN des1_ranking_menu 0 DES1_52 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN des1_ranking_menu 1 DES1_53 $des1_board_name[0] $des1_board_name[1] $des1_board_name[2] DES1_50 $des1_board_name[3] DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN des1_ranking_menu 2 DES1_54 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		IF IS_JAPANESE_VERSION
			SET_MENU_COLUMN_WIDTH des1_ranking_menu 0 20 
			SET_MENU_COLUMN_WIDTH des1_ranking_menu 1 150 
			SET_MENU_COLUMN_WIDTH des1_ranking_menu 2 80 
		ENDIF
		des1_counter = 0
		WHILE des1_counter < 5
			des1_temp_int = des1_counter
			des1_temp_int += 1
			SET_MENU_ITEM_WITH_NUMBER des1_ranking_menu 0 des1_counter DES1_51 des1_temp_int
			des1_counter += 1
		ENDWHILE
		des1_counter = 0
		des1_temp_counter = 0

		WHILE des1_counter < 5
			IF des1_counter = 3
				des1_minutes = des1_total_time / 60
				des1_temp_int = des1_minutes * 60
				des1_seconds = des1_total_time - des1_temp_int
			ELSE
				des1_minutes = des1_board_time[des1_temp_counter] / 60
				des1_temp_int = des1_minutes * 60
				des1_seconds = des1_board_time[des1_temp_counter] - des1_temp_int
				ACTIVATE_MENU_ITEM des1_ranking_menu des1_counter FALSE
				des1_temp_counter += 1
			ENDIF
			IF des1_seconds < 10
				SET_MENU_ITEM_WITH_2_NUMBERS des1_ranking_menu 2 des1_counter DES1_66 des1_minutes des1_seconds
			ELSE
				SET_MENU_ITEM_WITH_2_NUMBERS des1_ranking_menu 2 des1_counter DES1_65 des1_minutes des1_seconds
			ENDIF
			des1_counter += 1
		ENDWHILE
		BREAK
	CASE 4
		IF IS_JAPANESE_VERSION
			CREATE_MENU DES1_34 354.0 200.0 300.0 3 FALSE TRUE 0 des1_ranking_menu
		ELSE
			CREATE_MENU DES1_34 354.0 200.0 80.0 3 FALSE TRUE 0 des1_ranking_menu
		ENDIF
		SET_MENU_COLUMN des1_ranking_menu 0 DES1_52 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN des1_ranking_menu 1 DES1_53 $des1_board_name[0] $des1_board_name[1] $des1_board_name[2] $des1_board_name[3] DES1_50 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN des1_ranking_menu 2 DES1_54 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		IF IS_JAPANESE_VERSION
			SET_MENU_COLUMN_WIDTH des1_ranking_menu 0 20 
			SET_MENU_COLUMN_WIDTH des1_ranking_menu 1 150 
			SET_MENU_COLUMN_WIDTH des1_ranking_menu 2 80 
		ENDIF
		des1_counter = 0
		WHILE des1_counter < 5
			des1_temp_int = des1_counter
			des1_temp_int += 1
			SET_MENU_ITEM_WITH_NUMBER des1_ranking_menu 0 des1_counter DES1_51 des1_temp_int
			des1_counter += 1
		ENDWHILE
		des1_counter = 0
		des1_temp_counter = 0

		WHILE des1_counter < 5
			IF des1_counter = 4
				des1_minutes = des1_total_time / 60
				des1_temp_int = des1_minutes * 60
				des1_seconds = des1_total_time - des1_temp_int
			ELSE
				des1_minutes = des1_board_time[des1_temp_counter] / 60
				des1_temp_int = des1_minutes * 60
				des1_seconds = des1_board_time[des1_temp_counter] - des1_temp_int
				ACTIVATE_MENU_ITEM des1_ranking_menu des1_counter FALSE
				des1_temp_counter += 1
			ENDIF
			IF des1_seconds < 10
				SET_MENU_ITEM_WITH_2_NUMBERS des1_ranking_menu 2 des1_counter DES1_66 des1_minutes des1_seconds
			ELSE
				SET_MENU_ITEM_WITH_2_NUMBERS des1_ranking_menu 2 des1_counter DES1_65 des1_minutes des1_seconds
			ENDIF
			des1_counter += 1
		ENDWHILE
		BREAK
	DEFAULT
		IF IS_JAPANESE_VERSION
			CREATE_MENU DES1_34 354.0 200.0 300.0 3 FALSE TRUE 0 des1_ranking_menu
		ELSE
			CREATE_MENU DES1_34 354.0 200.0 80.0 3 FALSE TRUE 0 des1_ranking_menu
		ENDIF
		SET_MENU_COLUMN des1_ranking_menu 0 DES1_52 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN des1_ranking_menu 1 DES1_53 $des1_board_name[0] $des1_board_name[1] $des1_board_name[2] $des1_board_name[3] $des1_board_name[4] DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN des1_ranking_menu 2 DES1_54 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		IF IS_JAPANESE_VERSION
			SET_MENU_COLUMN_WIDTH des1_ranking_menu 0 20 
			SET_MENU_COLUMN_WIDTH des1_ranking_menu 1 150 
			SET_MENU_COLUMN_WIDTH des1_ranking_menu 2 80 
		ENDIF
		des1_counter = 0
		WHILE des1_counter < 5
			des1_temp_int = des1_counter
			des1_temp_int += 1
			SET_MENU_ITEM_WITH_NUMBER des1_ranking_menu 0 des1_counter DES1_51 des1_temp_int
			des1_counter += 1
		ENDWHILE
		des1_counter = 0
		des1_temp_counter = 0

		WHILE des1_counter < 5
			des1_minutes = des1_board_time[des1_temp_counter] / 60
			des1_temp_int = des1_minutes * 60
			des1_seconds = des1_board_time[des1_temp_counter] - des1_temp_int
			des1_temp_counter += 1
			IF des1_seconds < 10
				SET_MENU_ITEM_WITH_2_NUMBERS des1_ranking_menu 2 des1_counter DES1_66 des1_minutes des1_seconds
			ELSE
				SET_MENU_ITEM_WITH_2_NUMBERS des1_ranking_menu 2 des1_counter DES1_65 des1_minutes des1_seconds
			ENDIF
			des1_counter += 1
		ENDWHILE
		BREAK
	ENDSWITCH
	
	WHILE IS_MESSAGE_BEING_DISPLAYED
		WAIT 0
	ENDWHILE
		
	des1_audio_counter  = 0 
	des1_audio_slot1 	= 1
	des1_audio_slot2 	= 2
	des1_audio_playing	= 0
	des1_ahead_counter = 0 
	desert1_text_loop_done = 0
	des1_mobile = 0

	desert1_text_loop2:
	WAIT 0 
	IF desert1_text_loop_done = 0
		IF NOT des1_audio_counter = 0
			IF des1_audio_playing = 0
				IF HAS_MISSION_AUDIO_LOADED des1_audio_slot2
					CLEAR_MISSION_AUDIO des1_audio_slot2
				ENDIF
				des1_audio_playing = 1
			ENDIF

			IF des1_audio_playing = 1
				LOAD_MISSION_AUDIO des1_audio_slot1 des1_audio[des1_audio_counter]
				des1_audio_playing = 2
			ENDIF

			IF des1_audio_playing = 2
			 	IF HAS_MISSION_AUDIO_LOADED des1_audio_slot1
					PLAY_MISSION_AUDIO des1_audio_slot1
					PRINT_NOW $des1_text[des1_audio_counter] 10000 1
					des1_audio_playing = 3
				ENDIF
			ENDIF

			IF des1_audio_playing = 3
				IF HAS_MISSION_AUDIO_FINISHED des1_audio_slot1
					CLEAR_THIS_PRINT $des1_text[des1_audio_counter]
					IF des1_audio_slot1 = 1
						des1_audio_slot1 = 2
						des1_audio_slot2 = 1
					ELSE
						des1_audio_slot1 = 1
						des1_audio_slot2 = 2
					ENDIF
					des1_audio_counter = 0
					des1_audio_playing = 0
					desert1_text_loop_done = 1				
				ENDIF
			ENDIF
		ENDIF


				
				

		IF des1_mobile = 0
			IF NOT IS_CHAR_DEAD des1_starter_ped
				STOP_CHAR_FACIAL_TALK des1_starter_ped
				START_CHAR_FACIAL_TALK des1_starter_ped	 4000									
				TASK_PLAY_ANIM des1_starter_ped  prtial_gngtlkF	GANGS 4.0 FALSE FALSE FALSE FALSE -1 // latest addition
			ENDIF
			SWITCH des1_player_ranking																					
			CASE 0
				IF des1_audio_playing = 0
					des1_audio_counter = 9	// CESAR: CJ.
					des1_mobile = 1					
				ENDIF				
				PRINT_NOW $des1_text[des1_audio_counter] 10000 1
				des1_reward = 5000
				BREAK
			CASE 1
				IF des1_audio_playing = 0
					des1_audio_counter = 15	// CESAR: CJ.
					des1_mobile = 1					
				ENDIF				
				des1_reward = 4000
				PRINT_NOW $des1_text[des1_audio_counter] 10000 1
				BREAK
			CASE 2
				IF des1_audio_playing = 0
					des1_audio_counter = 10	// CESAR: CJ.
					des1_mobile = 1					
				ENDIF				
				des1_reward = 3000
				PRINT_NOW $des1_text[des1_audio_counter] 10000 1
				BREAK
			CASE 3	
				IF des1_audio_playing = 0
					des1_audio_counter = 14	// CESAR: CJ.
					des1_mobile = 1					
				ENDIF				
				des1_reward = 2000
				PRINT_NOW $des1_text[des1_audio_counter] 10000 1
				BREAK
			CASE 4
				IF des1_audio_playing = 0
					des1_audio_counter = 11	// CESAR: CJ.
					des1_mobile = 1					
				ENDIF				
				des1_reward = 1000
				PRINT_NOW $des1_text[des1_audio_counter] 10000 1
				BREAK
											
			DEFAULT	// not good enough below this
				IF des1_audio_playing = 0
					des1_audio_counter = 13	// CESAR: CJ.
					des1_mobile = 1					
				ENDIF
				 //"Sorry pal, you're not what the boss is looking for"
				des1_reward = 0
				PRINT_NOW $des1_text[des1_audio_counter] 10000 1
				BREAK
			ENDSWITCH
		ENDIF
	GOTO desert1_text_loop2
	ENDIF

	DELETE_MENU des1_ranking_menu
	
	IF NOT IS_CHAR_DEAD des1_starter_ped		
		TASK_LOOK_AT_CHAR scplayer des1_starter_ped 4000
		CLEAR_CHAR_TASKS des1_starter_ped
		OPEN_SEQUENCE_TASK des1_starter_seq			
		TASK_GO_STRAIGHT_TO_COORD -1 -688.0 944.0 14.0 PEDMOVE_WALK 20000
		TASK_GO_STRAIGHT_TO_COORD -1 -688.0 938.0 14.0 PEDMOVE_WALK 3000
		CLOSE_SEQUENCE_TASK des1_starter_seq
		PERFORM_SEQUENCE_TASK des1_starter_ped des1_starter_seq
		CLEAR_SEQUENCE_TASK des1_starter_seq
	ENDIF

	
	SET_FIXED_CAMERA_POSITION -685.08 949.38 12.36 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -688.21 938.04 14.72 JUMP_CUT

	CLEAR_PRINTS
	IF des1_player_ranking < 5		
		des1_safety_flag = 0
		IF NOT IS_CHAR_DEAD des1_starter_ped
			STOP_CHAR_FACIAL_TALK des1_starter_ped
			START_CHAR_FACIAL_TALK des1_starter_ped	 4000									
		ENDIF
		
		des1_audio_counter = 12		
		
		WHILE des1_safety_flag = 0

			IF NOT des1_audio_counter = 0
				IF des1_audio_playing = 0
					IF HAS_MISSION_AUDIO_LOADED des1_audio_slot2
						CLEAR_MISSION_AUDIO des1_audio_slot2
					ENDIF
					des1_audio_playing = 1
				ENDIF

				IF des1_audio_playing = 1
					LOAD_MISSION_AUDIO des1_audio_slot1 des1_audio[des1_audio_counter]
					des1_audio_playing = 2
				ENDIF

				IF des1_audio_playing = 2
				 	IF HAS_MISSION_AUDIO_LOADED des1_audio_slot1
						PLAY_MISSION_AUDIO des1_audio_slot1
						PRINT_NOW $des1_text[des1_audio_counter] 10000 1
						des1_audio_playing = 3
					ENDIF
				ENDIF

				IF des1_audio_playing = 3
					IF HAS_MISSION_AUDIO_FINISHED des1_audio_slot1
						CLEAR_THIS_PRINT $des1_text[des1_audio_counter]
						IF des1_audio_slot1 = 1
							des1_audio_slot1 = 2
							des1_audio_slot2 = 1
						ELSE
							des1_audio_slot1 = 1
							des1_audio_slot2 = 2
						ENDIF
						des1_audio_counter = 0
						des1_audio_playing = 0
						des1_safety_flag = 1
					ENDIF
				ENDIF
			ENDIF
			WAIT 0 
		ENDWHILE
		
	ENDIF

	des1_safety_flag = 0
	WHILE des1_safety_flag = 0
		IF NOT IS_CHAR_DEAD des1_starter_ped
			GET_SCRIPT_TASK_STATUS des1_starter_ped PERFORM_SEQUENCE_TASK  des1_task_status
			IF  des1_task_status  = FINISHED_TASK
				des1_safety_flag = 1
			ENDIF
		ELSE
			des1_safety_flag = 1
		ENDIF
		WAIT 0 
	ENDWHILE
	
	CLEAR_CHAR_TASKS scplayer
	CLEAR_LOOK_AT scplayer
	
	DELETE_CHAR des1_starter_ped
	SWITCH_WIDESCREEN  OFF
	SET_PLAYER_CONTROL player1 ON
	RESTORE_CAMERA_JUMPCUT							
RETURN

setup_text1_des1:
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

setup_text2_des1:
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


/////////////////////////////////////////
//////// standard pss/fail/ cleanup /////
/////////////////////////////////////////




	
// Mission toreno1 failed

mission_toreno1_failed:
PRINT_BIG M_FAIL 5000 1
RETURN

   
// mission toreno1 passed
mission_toreno1_passed:

    PRINT_WITH_NUMBER_BIG M_PASS des1_reward 5000 1
    ADD_SCORE player1 des1_reward
    CLEAR_WANTED_LEVEL player1
    PLAY_MISSION_PASSED_TUNE 1
    SET_INT_STAT PASSED_DESERT1 1
    REGISTER_MISSION_PASSED ( DESERT1 )
    PLAYER_MADE_PROGRESS 1
    REMOVE_BLIP desert_contact_blip
    flag_desert_mission_counter++

RETURN

///////////////////////		
/// mission cleanup ///
///////////////////////



mission_cleanup_toreno1:

//REMOVE_BLIP
GET_GAME_TIMER timer_mobile_start
USE_TEXT_COMMANDS FALSE

// cars 
MARK_CAR_AS_NO_LONGER_NEEDED  des1_player_car

// menu
DELETE_MENU des1_ranking_menu

// peds
REMOVE_CHAR_ELEGANTLY des1_starter_ped
MARK_CHAR_AS_NO_LONGER_NEEDED  des1_starter_ped
				    
//blips
REMOVE_BLIP des1_route_blip 
DELETE_CHECKPOINT des1_checkpoint

// models
MARK_MODEL_AS_NO_LONGER_NEEDED MONSTER
MARK_MODEL_AS_NO_LONGER_NEEDED WBDYG2
MARK_MODEL_AS_NO_LONGER_NEEDED DESERT_EAGLE


// animations
REMOVE_ANIMATION ON_LOOKERS
REMOVE_ANIMATION GANGS


SET_CAR_DENSITY_MULTIPLIER 1.0
flag_player_on_mission = 0
MISSION_HAS_FINISHED
RETURN
}
		



