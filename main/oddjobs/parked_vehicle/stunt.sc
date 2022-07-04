MISSION_START
// *****************************************************************************************
// ***																					 ***
// *** Date: 02/07/2004 	Time: 15:53:56	   Author:  Chris Rothwell 					 ***
// ***																					 ***
// *** Mission: BMX/4x4/PCJ Stunt Courses 												 ***
// ***																					 ***
// *****************************************************************************************

GOSUB mission_stunt_start

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_stunt_failed
ENDIF

GOSUB mission_stunt_cleanup

MISSION_END
 
// ************************************ MISSION START **************************************
{
mission_stunt_start:

flag_player_on_mission = 1

if stunt_course = 0
	if done_bmx_stunt_progress = 0
		REGISTER_MISSION_GIVEN
	endif
endif
if stunt_course = 1
	if done_nrg500_stunt_progress = 0
		REGISTER_MISSION_GIVEN
	endif
endif
SCRIPT_NAME stunt

WAIT 0

LOAD_MISSION_TEXT stunt

//while not is_player_playing player1
//	wait 0
//endwhile

lvar_int stunt_vehicle
store_car_char_is_in scplayer stunt_vehicle
set_car_health stunt_vehicle 3000

//var_int	stunt_course
//stunt_course = 0

gosub stunt_coordinates

lvar_int out_of_car_flag
out_of_car_flag = 1

lvar_int corona_collected[20] a
a = 0
while a < total_coronas
	corona_collected[a]	= 0
	++ a
endwhile

lvar_int stunt_vehicle_blip
if flag_player_on_mission = 0
	add_blip_for_coord x y z stunt_vehicle_blip
endif

lvar_int stunt_timer_displayed
stunt_timer_displayed = 0

lvar_int total_coronas_collected
total_coronas_collected = 0

lvar_float checkpoint_heading[20]

SET_PLAYER_CONTROL player1 OFF
HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
SET_EVERYONE_IGNORE_PLAYER player1 TRUE
SWITCH_WIDESCREEN ON
SET_ALL_CARS_CAN_BE_DAMAGED FALSE
SET_FIXED_CAMERA_POSITION cam_s_x cam_s_y cam_s_z 0.0 0.0 0.0
POINT_CAMERA_AT_POINT cam_s_look_x cam_s_look_y cam_s_look_z JUMP_CUT

get_game_timer game_timer
var_int stunt_timer
stunt_timer = game_timer + 5000

PRINT_NOW STUNT_6 5000 1//~s~Collect all of the ~r~checkpoints~s~ before the time runs out.

lvar_int pad1_cross_pressed
pad1_cross_pressed = 0
														 
// ************************************* MISSION LOOP **************************************
mission_stunt_loop:

WAIT 0

get_game_timer game_timer

if stunt_timer_displayed = 0
	if is_button_pressed pad1 cross
		if pad1_cross_pressed = 1
			stunt_timer_displayed += 1
		endif
	else
		pad1_cross_pressed = 1
	endif
endif

if stunt_timer_displayed = 0
	if stunt_timer < game_timer
		stunt_timer_displayed += 1
	endif
endif
if stunt_timer_displayed = 1
	SET_PLAYER_CONTROL player1 ON
	HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
	SET_EVERYONE_IGNORE_PLAYER player1 FALSE
	SWITCH_WIDESCREEN OFF
	SET_ALL_CARS_CAN_BE_DAMAGED TRUE
	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT
	stunt_timer = 11000
	DISPLAY_ONSCREEN_TIMER_WITH_STRING stunt_timer TIMER_DOWN STUNT_2 //Time Left
	lvar_int stunt_track_start
	stunt_track_start = game_timer
	stunt_timer_displayed += 1
endif
		
IF NOT IS_CAR_DEAD stunt_vehicle
	IF IS_CHAR_IN_CAR scplayer stunt_vehicle
		IF out_of_car_flag = 1
			CLEAR_THIS_PRINT STUNT_1
			REMOVE_BLIP	stunt_vehicle_blip
			a = 0
			while a < total_coronas
				if corona_collected[a] = 0
					lvar_int stunt_blip[20]
					add_blip_for_coord stunt_x[a] stunt_y[a] stunt_z[a]	stunt_blip[a]
					CHANGE_BLIP_COLOUR stunt_blip[a] RED
					lvar_int stunt_marker[20]
					CREATE_CHECKPOINT CHECKPOINT_TORUS stunt_x[a] stunt_y[a] stunt_z[a] stunt_x[a] stunt_y[a] stunt_z[a] 0.8 stunt_marker[a]
				endif
				++ a
			endwhile
			out_of_car_flag = 0
		ENDIF
		a = 0
		while a < total_coronas
			IF corona_collected[a] = 0
				//DRAW_CORONA stunt_x[a] stunt_y[a] stunt_z[a] 1.0 CORONATYPE_SHINYSTAR FLARETYPE_NONE 255 0 0
				//DRAW_CORONA stunt_x[a] stunt_y[a] stunt_z[a] 1.0 CORONATYPE_SHINYSTAR FLARETYPE_NONE 255 0 0
				SET_CHECKPOINT_HEADING stunt_marker[a] checkpoint_heading[a]
				DRAW_SHADOW SHADOW_PED stunt_x[a] stunt_y[a] stunt_z[a] 0.0 1.0 150 0 0 0
				checkpoint_heading[a] += 10.0
				if checkpoint_heading[a] > 360.0
					checkpoint_heading[a] -= 360.0
				endif
				IF LOCATE_CHAR_IN_CAR_3D scplayer stunt_x[a] stunt_y[a] stunt_z[a] 2.5 2.5 2.5 false
					REMOVE_BLIP stunt_blip[a]
					delete_checkpoint stunt_marker[a]
					//ADD_ONE_OFF_SOUND stunt_x[a] stunt_y[a] stunt_z[a] SOUND_PART_MISSION_COMPLETE
					report_mission_audio_event_at_position stunt_x[a] stunt_y[a] stunt_z[a] SOUND_PART_MISSION_COMPLETE
					stunt_timer	+= 10000
					print_with_number_now STUNT_3 10 1500 1//+~1~ seconds
					
					++ total_coronas_collected
					++ corona_collected[a]
				ENDIF
			ENDIF
			++ a
		endwhile
	ELSE
		IF out_of_car_flag = 0
			a = 0
			while a < total_coronas
				remove_blip	stunt_blip[a]
				delete_checkpoint stunt_marker[a]
				++ a
			endwhile
			ADD_BLIP_FOR_CAR stunt_vehicle stunt_vehicle_blip
			SET_BLIP_AS_FRIENDLY stunt_vehicle_blip TRUE

			LVAR_INT out_of_car_timer
			if stunt_timer > 25400
				out_of_car_timer = game_timer + 25400
			else
				out_of_car_timer = game_timer + stunt_timer
			endif
			out_of_car_flag = 1
		ENDIF
		lvar_int seconds
		seconds = out_of_car_timer - game_timer
		seconds /= 1000
		IF seconds < 1
			seconds = 0
		ENDIF
		IF out_of_car_timer < game_timer
			PRINT_NOW STUNT_4 5000 1//~r~You left your vehicle.
			goto mission_stunt_failed
		ENDIF
		if seconds = 1
			ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS	FALSE
			PRINT_WITH_NUMBER_NOW STUNT_8 seconds 200 1 //~s~You have ~1~ seconds to return to your ~b~vehicle~s~.
		else
			ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS	FALSE
			PRINT_WITH_NUMBER_NOW STUNT_1 seconds 200 1 //~s~You have ~1~ seconds to return to your ~b~vehicle~s~.
		endif
	ENDIF
else
	print_now STUNT_5 5000 1 //~r~You destroyed your vehicle!
	goto mission_stunt_failed
endif

if total_coronas_collected = total_coronas
	goto mission_stunt_passed
endif

if stunt_timer = 0
	print_now STUNT_7 5000 1 //~r~You ran out of time!
	goto mission_stunt_failed
endif

GOTO mission_stunt_loop


	
// ************************************ MISSION FAILED *************************************
mission_stunt_failed:
PRINT_BIG M_FAIL 5000 1
RETURN

   

// ************************************ MISSION PASSED *************************************
mission_stunt_passed:

ADD_SCORE player1 1000
CLEAR_WANTED_LEVEL player1
PLAY_MISSION_PASSED_TUNE 1
lvar_int stunt_time
stunt_time = game_timer - stunt_track_start
stunt_time /= 1000
if stunt_course = 0
	if done_bmx_stunt_progress = 0
		PLAYER_MADE_PROGRESS 1
		REGISTER_MISSION_PASSED BMX
		PLAY_MISSION_PASSED_TUNE 2
		done_bmx_stunt_progress = 1
	endif
	REGISTER_FASTEST_TIME BEST_TIME_BMX stunt_time
endif
if stunt_course = 1
	if done_nrg500_stunt_progress = 0
		PLAYER_MADE_PROGRESS 1
		REGISTER_MISSION_PASSED NRG500
		PLAY_MISSION_PASSED_TUNE 2
		done_nrg500_stunt_progress = 1
	endif
	REGISTER_FASTEST_TIME BEST_TIME_NRG stunt_time
endif
lvar_int mins
mins = stunt_time / 60
lvar_int temp_int
temp_int = mins * 60
seconds = stunt_time - temp_int
if stunt_time < stunt_best_time[stunt_course]
	if seconds < 10
		PRINT_WITH_2_NUMBERS_BIG STUNTN0 mins seconds 5000 1 //New Time record: ~1~:~1~
	else
		PRINT_WITH_2_NUMBERS_BIG STUNTPN mins seconds 5000 1 //New Time record: ~1~:~1~
	endif
	stunt_best_time[stunt_course] = stunt_time
else
	if seconds < 10
		PRINT_WITH_2_NUMBERS_BIG STUNTT0 mins seconds 5000 1 //Time: ~1~:~1~
	else
		PRINT_WITH_2_NUMBERS_BIG STUNTPT mins seconds 5000 1 //Time: ~1~:~1~
	endif
endif

RETURN
		


// *********************************** MISSION CLEANUP *************************************
mission_stunt_cleanup:

clear_this_print STUNT_1
clear_this_print STUNT_3
a = 0
while a < total_coronas
	remove_blip	stunt_blip[a]
	delete_checkpoint stunt_marker[a]
	++ a
endwhile
REMOVE_BLIP	stunt_vehicle_blip
CLEAR_ONSCREEN_TIMER stunt_timer
//MARK_MODEL_AS_NO_LONGER_NEEDED 

flag_player_on_mission = 0

MISSION_HAS_FINISHED
RETURN





stunt_coordinates:////////////////////////////////////////////////////////
switch stunt_course

case 0
//var_int stunt_bmx1_cargen
//CREATE_CAR_GENERATOR 1946.0896 -1380.7202 17.5781 88.1761 BMX -1 -1 TRUE FALSE FALSE 0 10000 stunt_bmx1_cargen
//SWITCH_CAR_GENERATOR stunt_bmx1_cargen 101
lvar_float stunt_x[20] stunt_y[20] stunt_z[20]
stunt_x[0] = 1955.0118 
stunt_y[0] = -1367.7200 
stunt_z[0] = 24.7354 

stunt_x[1] = 1954.6088 
stunt_y[1] = -1376.9637 
stunt_z[1] = 24.2187 

stunt_x[2] = 1887.3917 
stunt_y[2] = -1362.9537 
stunt_z[2] = 19.4329 

stunt_x[3] = 1914.1375 
stunt_y[3] = -1427.0991 
stunt_z[3] = 15.5805 

stunt_x[4] = 1908.5515 
stunt_y[4] = -1388.3890 
stunt_z[4] = 10.3294 

stunt_x[5] = 1882.3623 
stunt_y[5] = -1427.6859 
stunt_z[5] = 10.3294 

stunt_x[6] = 1953.3492 
stunt_y[6] = -1426.5858 
stunt_z[6] = 10.3294 

stunt_x[7] = 1878.1891 
stunt_y[7] = -1388.8102 
stunt_z[7] = 15.2464 

stunt_x[8] = 1878.1838 
stunt_y[8] = -1388.8392 
stunt_z[8] = 18.1461 

stunt_x[9] = 1872.3269 
stunt_y[9] = -1451.0511 
stunt_z[9] = 15.7645 

stunt_x[10] = 1905.1785 
stunt_y[10] = -1413.6747 
stunt_z[10] = 13.5322 

stunt_x[11] = 1949.5026 
stunt_y[11] = -1411.5458 
stunt_z[11] = 15.1837 

stunt_x[12] = 1867.1531 
stunt_y[12] = -1410.0066 
stunt_z[12] = 13.5322 

stunt_x[13] = 1909.3409 
stunt_y[13] = -1369.8458 
stunt_z[13] = 16.7966 

stunt_x[14] = 1939.2430 
stunt_y[14] = -1388.1323 
stunt_z[14] = 19.2615 

stunt_x[15] = 1904.8877 
stunt_y[15] = -1360.6219 
stunt_z[15] = 13.5322 

stunt_x[16] = 1930.2335 
stunt_y[16] = -1398.6198 
stunt_z[16] = 16.8554 

stunt_x[17] = 1888.7649 
stunt_y[17] = -1399.3260 
stunt_z[17] = 17.2408 

stunt_x[18] = 1900.9476 
stunt_y[18] = -1397.8297 
stunt_z[18] = 15.6868 

lvar_int total_coronas
total_coronas = 19

lvar_float cam_s_x cam_s_y cam_s_z cam_s_look_x cam_s_look_y cam_s_look_z
cam_s_x =		1964.7528
cam_s_y =		-1368.3622
cam_s_z =		29.6217
cam_s_look_x =	1963.9342
cam_s_look_y =	-1368.8182
cam_s_look_z =	29.2725 
break


case 1
//var_int stunt_nrg500_cargen
//CREATE_CAR_GENERATOR -1696.5312 77.7192 3.5547 312.9365 nrg500 -1 -1 TRUE FALSE FALSE 0 10000 stunt_nrg500_cargen
//SWITCH_CAR_GENERATOR stunt_nrg500_cargen 101

stunt_x[0] = -1632.0581 
stunt_y[0] = 143.1520 
stunt_z[0] = 3.4111 

stunt_x[1] = -1632.0208 
stunt_y[1] = 83.0413 
stunt_z[1] = 7.5331 

stunt_x[2] = -1689.1484 
stunt_y[2] = 53.3936 
stunt_z[2] = 11.7703 

stunt_x[3] = -1606.4226 
stunt_y[3] = 133.4396 
stunt_z[3] = -10.9911 

stunt_x[4] = -1680.3857 
stunt_y[4] = 87.0915
stunt_z[4] = 8.2325

stunt_x[5] = -1666.2952 
stunt_y[5] = 102.0489 
stunt_z[5] = -1.5025 

stunt_x[6] = -1654.6917
stunt_y[6] = 60.7782
stunt_z[6] = 7.6501

stunt_x[7] = -1684.8666 
stunt_y[7] = 74.6374 
stunt_z[7] = -7.0328 

stunt_x[8] = -1583.7968 
stunt_y[8] = 126.1577 
stunt_z[8] = 4.1570 

stunt_x[9] = -1667.7051
stunt_y[9] = 49.5191
stunt_z[9] = 6.5634

stunt_x[10] = -1611.5852 
stunt_y[10] = 106.3131 
stunt_z[10] = -3.6465 

stunt_x[11] = -1590.5564 
stunt_y[11] = 148.9676 
stunt_z[11] = 4.1130 

stunt_x[12] = -1671.9587 
stunt_y[12] = 98.6000 
stunt_z[12] = 8.9263 

stunt_x[13] = -1693.3202
stunt_y[13] = 65.1187
stunt_z[13] = 8.7997

stunt_x[14] = -1660.9508 
stunt_y[14] = 107.6739 
stunt_z[14] = -2.1545 

stunt_x[15] = -1654.0709 
stunt_y[15] = 77.2629 
stunt_z[15] = -10.2893

stunt_x[16] = -1645.4943
stunt_y[16] = 107.2477
stunt_z[16] = -10.6617

stunt_x[17] = -1673.7347
stunt_y[17] = 56.9287 
stunt_z[17] = -10.6740 

total_coronas = 18
total_coronas = 18
total_coronas = 18

cam_s_x =		-1681.4637
cam_s_y =		27.2091
cam_s_z =		9.6606
cam_s_look_x =	-1681.2524
cam_s_look_y =	28.1830
cam_s_look_z =	9.5773 
break



endswitch
return
return
return

}		


