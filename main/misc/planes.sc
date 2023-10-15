MISSION_START
{
SCRIPT_NAME planes

VAR_INT pl_plane

VAR_TEXT_LABEL pl_days[8]
VAR_INT pl_days_flag
VAR_INT pl_city_open
VAR_INT pl_which_city_select
VAR_INT pl_which_car_recording
VAR_INT pl_ticket_price pl_players_money
VAR_INT pl_denied_flag pl_control_flag
VAR_INT pl_game_timer_start pl_game_timer_end pl_game_timer_diff
VAR_INT pl_which_recording
VAR_INT pl_has_player_skipped
VAR_INT pl_undercarriage_flag pl_undercarriage_up_time pl_undercarriage_down_time
VAR_INT pl_print_flag_11

VAR_FLOAT pl_playerx pl_playery pl_playerz pl_player_heading
VAR_FLOAT pl_planex pl_planey pl_planez pl_plane_heading
VAR_FLOAT pl_skip_in_how_much

VAR_INT pl_stop_player_skipping_flag


// *************************************Set Flags/variables*********************************
$pl_days[1] = &PLA_9 //sunday
$pl_days[2] = &PLA_3 //monday
$pl_days[3] = &PLA_4 //tuesday 
$pl_days[4] = &PLA_5 //wednesday
$pl_days[5] = &PLA_6 //thursday
$pl_days[6] = &PLA_7 //friday
$pl_days[7] = &PLA_8 //saturday
pl_days_flag = 0
pl_which_city_select = 0
pl_which_car_recording = 0
pl_ticket_price = 500
pl_players_money = 0
pl_denied_flag = 0 
pl_control_flag = 0
pl_game_timer_start = 0
pl_game_timer_end = 0
pl_game_timer_diff = 0
pl_which_recording = 0
pl_has_player_skipped = 0 
pl_undercarriage_flag = 0 
pl_undercarriage_up_time = 0
pl_undercarriage_down_time = 0
pl_skip_in_how_much = 0.0
pl_playerx = 0.0
pl_playery = 0.0
pl_playerz = 0.0
pl_player_heading = 0.0
pl_planex = 0.0
pl_planey = 0.0 
pl_planez = 0.0 
pl_plane_heading = 0.0
pl_print_flag_11 = 0

pl_stop_player_skipping_flag = 0
// **************************************** START OF SETUP ********************************
IF NOT IS_PLAYER_PLAYING player1 
	GOTO mission_cleanup_planes
ENDIF
SET_PLAYER_CONTROL player1 OFF
SET_DEATHARREST_STATE OFF 
//sorting out what day to print.
STORE_CLOCK
GET_CURRENT_DAY_OF_WEEK pl_days_flag 
IF pl_days_flag = 7
	pl_days_flag = 1	 
ELSE
	pl_days_flag += 1
ENDIF

//checking which cities are open

GET_INT_STAT CITIES_PASSED pl_city_open

//checking which car recording to load up  
GOSUB pl_which_city_coords 

//initialisation for the ticket machine screen 
SWITCH_WIDESCREEN OFF
SET_FADING_COLOUR 0 0 0
CLEAR_PRINTS
WAIT 0
IF NOT IS_PLAYER_PLAYING player1 
	GOTO mission_cleanup_planes
ENDIF

DO_FADE 500 FADE_OUT
WHILE GET_FADING_STATUS
    WAIT 0
	IF NOT IS_PLAYER_PLAYING player1 
		GOTO mission_cleanup_planes
	ENDIF
ENDWHILE 

LOAD_TEXTURE_DICTIONARY LD_PLAN

LOAD_SPRITE 1 airlogo
LOAD_SPRITE 2 tvcorn
LOAD_SPRITE 3 tvbase
LOAD_SPRITE 4 blkdot 

LOAD_ALL_MODELS_NOW

CLEAR_HELP
CLEAR_PRINTS
USE_TEXT_COMMANDS TRUE
DISPLAY_HUD FALSE
DISPLAY_RADAR FALSE
SHOW_UPDATE_STATS FALSE

DO_FADE 500 FADE_IN
// **************************************** TICKET MACHINE ********************************

GOSUB drawing_ticket_machine
WHILE GET_FADING_STATUS
	WAIT 0
	IF NOT IS_PLAYER_PLAYING player1 
		GOTO mission_cleanup_planes
	ENDIF
	GOSUB drawing_ticket_machine
ENDWHILE 
GOSUB drawing_ticket_machine

plane_ticket_machine_loop:
WAIT 0 

	GOSUB drawing_ticket_machine

	IF NOT IS_PLAYER_PLAYING player1 
		GOTO mission_cleanup_planes
	ENDIF
	
	RESTORE_CLOCK

	IF pl_city_open > 1
		GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
		IF LStickX < -100
		OR IS_BUTTON_PRESSED PAD1 DPADLEFT

			//REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_ROULETTE_REMOVE_CASH 
			pl_which_city_select = 0
		ENDIF
		
		IF LStickX > 100 
		OR IS_BUTTON_PRESSED PAD1 DPADRIGHT

			//REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_ROULETTE_REMOVE_CASH
			pl_which_city_select = 1
		ENDIF
	ELSE
		pl_which_city_select = 0
	ENDIF	

	//buying a ticket
	
	IF pl_city_open > 0

		IF pl_control_flag = 0
			IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
				pl_denied_flag = 0
				pl_control_flag = 1
			ENDIF
		ENDIF

		IF pl_control_flag = 1 
			IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT 
				pl_control_flag = 2
			ENDIF
		ENDIF		

		IF pl_control_flag = 2 
			STORE_SCORE player1 pl_players_money
			IF pl_players_money >= pl_ticket_price
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_ROULETTE_ADD_CASH
				GOSUB drawing_ticket_machine
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
					GOSUB drawing_ticket_machine
				    WAIT 0
					IF NOT IS_PLAYER_PLAYING player1 
						GOTO mission_cleanup_planes
					ENDIF
					GOSUB drawing_ticket_machine
				ENDWHILE 
				
				GOTO pl_plane_flying_setup
			ELSE
				IF pl_denied_flag = 0 
					GET_GAME_TIMER pl_game_timer_start 			
					GET_CHAR_COORDINATES scplayer player_x player_y player_z
					//REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_ROULETTE_NO_CASH
					pl_denied_flag = 1
					pl_control_flag = 0
				ENDIF
			ENDIF
		ENDIF
		
		//printing denied text 
		GET_GAME_TIMER pl_game_timer_end 	
		pl_game_timer_diff = pl_game_timer_end - pl_game_timer_start
		IF pl_game_timer_diff < 5000
			GOSUB planes_small_text			 
			SET_TEXT_SCALE 0.41 1.84
			SET_TEXT_COLOUR 255 255 255 255
			IF current_Language = LANGUAGE_ITALIAN
			OR current_Language = LANGUAGE_GERMAN
				DISPLAY_TEXT 40.0 337.0 SHOPNO  // You don't have enough money to buy this item.
			ELSE
				DISPLAY_TEXT 93.0 337.0 SHOPNO  // You don't have enough money to buy this item.
			ENDIF
		ENDIF
	ENDIF
	
	//exiting the ticket machine without buying a ticket
			
	IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
		REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_ROULETTE_NO_CASH
		GOSUB drawing_ticket_machine
		DO_FADE 500 FADE_OUT
		WHILE GET_FADING_STATUS
			GOSUB drawing_ticket_machine
		    WAIT 0
			IF NOT IS_PLAYER_PLAYING player1 
				GOTO mission_cleanup_planes
			ENDIF
			GOSUB drawing_ticket_machine
		ENDWHILE 
		GOSUB pl_which_city_coords
		SET_CHAR_COORDINATES scplayer pl_playerx pl_playery pl_playerz 
		SET_CHAR_HEADING scplayer pl_player_heading
		DO_FADE 500 FADE_IN
		GOTO mission_cleanup_planes
		
	ENDIF

GOTO plane_ticket_machine_loop

// **************************************** PLANE FLYING ********************************
pl_plane_flying_setup:
IF NOT IS_PLAYER_PLAYING player1 
	GOTO mission_cleanup_planes
ENDIF

SET_TIME_ONE_DAY_FORWARD
ADD_SCORE player1 -500

REMOVE_TEXTURE_DICTIONARY
REQUEST_MODEL at400
REQUEST_MODEL gun_para
LOAD_ALL_MODELS_NOW

ALLOW_PAUSE_IN_WIDESCREEN TRUE
												    
//SWITCH_AMBIENT_PLANES FALSE 

GOSUB pl_which_city_coords 								  
REQUEST_COLLISION pl_planex pl_planey
CLEAR_AREA pl_planex pl_planey pl_planez 100.0 TRUE 
CREATE_CAR at400 pl_planex pl_planey pl_planez pl_plane
SET_CAR_HEADING pl_plane pl_plane_heading  
WARP_CHAR_INTO_CAR scplayer pl_plane 

LOAD_SCENE pl_planex pl_planey pl_planez 

SWITCH_WIDESCREEN ON  
RESTORE_CAMERA_JUMPCUT
SET_CAMERA_BEHIND_PLAYER

//setting time of day
IF im_players_city = LEVEL_LOSANGELES
	//LA - SF
	IF pl_which_city_select = 0
		SET_TIME_OF_DAY 10 30													  
		pl_skip_in_how_much = 9500.0											  
		pl_undercarriage_up_time = 20000										  
		pl_undercarriage_down_time = 150000										  
		pl_which_recording = 42      											  
	ENDIF																		  

	//LA - LV
	IF pl_which_city_select = 1
		SET_TIME_OF_DAY 17 50 
		pl_skip_in_how_much = 5000.0 
		pl_undercarriage_up_time = 30000
		pl_undercarriage_down_time = 110000
		pl_which_recording = 46  
	ENDIF
ENDIF
IF im_players_city = LEVEL_SANFRANCISCO
	//SF - LA
	IF pl_which_city_select = 0
		SET_TIME_OF_DAY 11 20 
		pl_skip_in_how_much = 6000.0 
		pl_undercarriage_up_time = 20000
		pl_undercarriage_down_time = 120000
		pl_which_recording = 49  
	ENDIF

	//SF - LV
	IF pl_which_city_select = 1
		SET_TIME_OF_DAY 14 40 
		pl_skip_in_how_much = 8000.0 
		pl_undercarriage_up_time = 20000
		pl_undercarriage_down_time = 160000
		pl_which_recording = 44 
	ENDIF
ENDIF
IF im_players_city = LEVEL_LASVEGAS
	//LV - LA
	IF pl_which_city_select = 0
		SET_TIME_OF_DAY 19 10 
		pl_skip_in_how_much = 6600.0 
		pl_undercarriage_up_time = 20000
		pl_undercarriage_down_time = 120000
		pl_which_recording = 48   
	ENDIF

	//LV - SF
	IF pl_which_city_select = 1
		SET_TIME_OF_DAY 14 30 
		pl_skip_in_how_much = 3800.0 
		pl_undercarriage_up_time = 20000
		pl_undercarriage_down_time = 70000
		pl_which_recording = 43   
	ENDIF
ENDIF
						   
REQUEST_CAR_RECORDING pl_which_recording
WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED pl_which_recording
	WAIT 0
	IF NOT IS_PLAYER_PLAYING player1 
		GOTO mission_cleanup_planes
	ENDIF
ENDWHILE	 

GET_GAME_TIMER pl_game_timer_start
pl_control_flag = 0

DO_FADE 500 FADE_IN

pl_plane_flying_loop:
	WAIT 0
	IF NOT IS_PLAYER_PLAYING player1 
		GOTO mission_cleanup_planes
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		CLEAR_WANTED_LEVEL player1 
	ENDIF
	 
	//sorting out timer
	GET_GAME_TIMER pl_game_timer_end 	
	pl_game_timer_diff = pl_game_timer_end - pl_game_timer_start

	//waiting for 2000 secs for plane to take off
	IF NOT IS_CAR_DEAD pl_plane
		IF pl_control_flag = 0
			IF pl_game_timer_diff > 2000
				START_PLAYBACK_RECORDED_CAR pl_plane pl_which_recording  
				SET_CINEMA_CAMERA TRUE
				pl_control_flag = 1	
			ENDIF
		ENDIF

		//letting player jump from the plane
		IF pl_control_flag > 0 
			IF pl_has_player_skipped = 0
				GET_CHAR_COORDINATES scplayer player_x player_y player_z
				IF player_z > 150.0
					//jumping from the plane text
					
					CLEAR_THIS_PRINT PLA_11 

					IF pl_print_flag_11 = 0
						PRINT_NOW PLA_12 20000 1
						pl_print_flag_11 = 1
					ENDIF
					
					IF IS_JAPANESE_VERSION
						IF IS_BUTTON_PRESSED PAD1 TRIANGLE
							pl_control_flag = 2
						ENDIF
					ELSE
						IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
							pl_control_flag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//stopping player from skipping the cutscene at the end
		IF pl_control_flag > 0
			IF pl_stop_player_skipping_flag = 0
				GET_CHAR_COORDINATES scplayer player_x player_y player_z
				IF player_z > 150.0
					pl_stop_player_skipping_flag = 1
				ENDIF
			ENDIF
			
			IF pl_stop_player_skipping_flag = 1 
				IF player_z < 150.0
					pl_stop_player_skipping_flag = 2

				ENDIF
			ENDIF
		ENDIF

		
		//stopping at end of car recording
		IF pl_control_flag = 1
			IF pl_has_player_skipped = 0
				//skipping cutscene text
				IF player_z <= 150.0
					
					CLEAR_THIS_PRINT PLA_12  
					
					IF pl_print_flag_11 = 0
						PRINT_NOW PLA_11 5000 1 
					ENDIF
				ENDIF
							
				IF pl_stop_player_skipping_flag < 2  

					IF IS_JAPANESE_VERSION
						IF IS_BUTTON_PRESSED PAD1 CROSS
							
							DO_FADE 500 FADE_OUT
							WHILE GET_FADING_STATUS
								WAIT 0
								IF NOT IS_PLAYER_PLAYING player1 
									GOTO mission_cleanup_planes
								ENDIF
							ENDWHILE 
							IF NOT IS_CAR_DEAD pl_plane 
								IF IS_PLAYBACK_GOING_ON_FOR_CAR pl_plane
									IF pl_undercarriage_flag = 1
										SET_PLANE_UNDERCARRIAGE_UP pl_plane FALSE
									ENDIF
									SKIP_IN_PLAYBACK_RECORDED_CAR pl_plane -10000000.0 
									SKIP_IN_PLAYBACK_RECORDED_CAR pl_plane pl_skip_in_how_much
									
									SET_CINEMA_CAMERA FALSE
									
									//LA
									IF pl_which_recording = 48 //LV - LA
									OR pl_which_recording = 49 //SF - LA
										SET_FIXED_CAMERA_POSITION 1774.9 -2529.3 30.9 0.0 0.0 0.0
										IF NOT IS_CAR_DEAD pl_plane 
											POINT_CAMERA_AT_CAR pl_plane FIXED JUMP_CUT 
										ENDIF
										LOAD_SCENE_IN_DIRECTION 1774.9 -2529.3 13.9 48.0
									ENDIF
							
									//SF
									IF pl_which_recording = 42 //LA - SF 
									OR pl_which_recording = 43 //LV - SF
										SET_FIXED_CAMERA_POSITION -1268.5 106.9 37.1 0.0 0.0 0.0
										IF NOT IS_CAR_DEAD pl_plane 
											POINT_CAMERA_AT_CAR pl_plane FIXED JUMP_CUT 
										ENDIF
										LOAD_SCENE_IN_DIRECTION -1268.5 106.9 13.0 350.0
									ENDIF
									
									//LV
									IF pl_which_recording = 46 //LA - LV 
									OR pl_which_recording = 44 //SF - LV
										SET_FIXED_CAMERA_POSITION 1396.1 1382.3 25.4 0.0 0.0 0.0
										IF NOT IS_CAR_DEAD pl_plane 
											POINT_CAMERA_AT_CAR pl_plane FIXED JUMP_CUT 
										ENDIF
										LOAD_SCENE_IN_DIRECTION 1396.1 1382.3 10.0 264.0
									ENDIF

									pl_has_player_skipped = 1
								ENDIF
							ENDIF 
							CLEAR_PRINTS
							DO_FADE 2500 FADE_IN
							WHILE GET_FADING_STATUS	
								WAIT 0
								IF NOT IS_PLAYER_PLAYING player1 
									GOTO mission_cleanup_planes
								ENDIF
							ENDWHILE 
						ENDIF
					ELSE
						IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
							
							DO_FADE 500 FADE_OUT
							WHILE GET_FADING_STATUS
								WAIT 0
								IF NOT IS_PLAYER_PLAYING player1 
									GOTO mission_cleanup_planes
								ENDIF
							ENDWHILE 
							IF NOT IS_CAR_DEAD pl_plane 
								IF IS_PLAYBACK_GOING_ON_FOR_CAR pl_plane
									IF pl_undercarriage_flag = 1
										SET_PLANE_UNDERCARRIAGE_UP pl_plane FALSE
									ENDIF
									SKIP_IN_PLAYBACK_RECORDED_CAR pl_plane -10000000.0 
									SKIP_IN_PLAYBACK_RECORDED_CAR pl_plane pl_skip_in_how_much
									
									SET_CINEMA_CAMERA FALSE
									
									//LA
									IF pl_which_recording = 48 //LV - LA
									OR pl_which_recording = 49 //SF - LA
										SET_FIXED_CAMERA_POSITION 1774.9 -2529.3 30.9 0.0 0.0 0.0
										IF NOT IS_CAR_DEAD pl_plane 
											POINT_CAMERA_AT_CAR pl_plane FIXED JUMP_CUT 
										ENDIF
										LOAD_SCENE_IN_DIRECTION 1774.9 -2529.3 13.9 48.0
									ENDIF
							
									//SF
									IF pl_which_recording = 42 //LA - SF 
									OR pl_which_recording = 43 //LV - SF
										SET_FIXED_CAMERA_POSITION -1268.5 106.9 37.1 0.0 0.0 0.0
										IF NOT IS_CAR_DEAD pl_plane 
											POINT_CAMERA_AT_CAR pl_plane FIXED JUMP_CUT 
										ENDIF
										LOAD_SCENE_IN_DIRECTION -1268.5 106.9 13.0 350.0
									ENDIF
									
									//LV
									IF pl_which_recording = 46 //LA - LV 
									OR pl_which_recording = 44 //SF - LV
										SET_FIXED_CAMERA_POSITION 1396.1 1382.3 25.4 0.0 0.0 0.0
										IF NOT IS_CAR_DEAD pl_plane 
											POINT_CAMERA_AT_CAR pl_plane FIXED JUMP_CUT 
										ENDIF
										LOAD_SCENE_IN_DIRECTION 1396.1 1382.3 10.0 264.0
									ENDIF

									pl_has_player_skipped = 1
								ENDIF
							ENDIF 
							CLEAR_PRINTS
							DO_FADE 2500 FADE_IN
							WHILE GET_FADING_STATUS	
								WAIT 0
								IF NOT IS_PLAYER_PLAYING player1 
									GOTO mission_cleanup_planes
								ENDIF
							ENDWHILE 
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF NOT IS_CAR_DEAD pl_plane
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR pl_plane	
					DO_FADE 2000 FADE_OUT
					WHILE GET_FADING_STATUS
					    WAIT 0
						IF NOT IS_PLAYER_PLAYING player1 
							GOTO mission_cleanup_planes
						ENDIF
					ENDWHILE 
					GOSUB pl_which_city_coords 
					SET_CINEMA_CAMERA FALSE
					REQUEST_COLLISION pl_playerx pl_playery
					CLEAR_AREA pl_playerx pl_playery pl_playerz 100.0 TRUE 
					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					SET_CHAR_COORDINATES scplayer pl_playerx pl_playery pl_playerz 
					SET_CHAR_HEADING scplayer pl_player_heading 
					LOAD_SCENE pl_playerx pl_playery pl_playerz 
					RESTORE_CAMERA_JUMPCUT
					SET_CAMERA_BEHIND_PLAYER
					SWITCH_WIDESCREEN OFF  
					DO_FADE 500 FADE_IN
					WHILE GET_FADING_STATUS
					    WAIT 0
						IF NOT IS_PLAYER_PLAYING player1 
							GOTO mission_cleanup_planes
						ENDIF
					ENDWHILE 
					GOTO mission_cleanup_planes
				ENDIF
			ENDIF
		ENDIF

		//getting player to jump out of plane
		IF pl_control_flag = 2
			DO_FADE 500 FADE_OUT
			WHILE GET_FADING_STATUS
			    WAIT 0
				IF NOT IS_PLAYER_PLAYING player1 
					GOTO mission_cleanup_planes
				ENDIF
			ENDWHILE 
			
			GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_PARACHUTE 1
			SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PARACHUTE 
			
			SET_CINEMA_CAMERA FALSE
			TASK_LEAVE_ANY_CAR scplayer 
			CLEAR_THIS_PRINT PLA_11  
			
			CLEAR_THIS_PRINT PLA_12  
			WHILE IS_CHAR_IN_ANY_CAR scplayer
				WAIT 0
				IF NOT IS_PLAYER_PLAYING player1 
					GOTO mission_cleanup_planes
				ENDIF
			ENDWHILE
			DELETE_CAR pl_plane 
			RESTORE_CAMERA_JUMPCUT
			SET_CAMERA_BEHIND_PLAYER
			SWITCH_WIDESCREEN OFF  
			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
			    WAIT 0
				IF NOT IS_PLAYER_PLAYING player1 
					GOTO mission_cleanup_planes
				ENDIF
			ENDWHILE 
			GOTO mission_cleanup_planes
		ENDIF
	
		//sorting out the undercarriage
		IF pl_has_player_skipped = 0
			IF pl_undercarriage_flag = 0 
				IF pl_undercarriage_up_time < pl_game_timer_diff  
					SET_PLANE_UNDERCARRIAGE_UP pl_plane TRUE
					pl_undercarriage_flag = 1
				ENDIF
			ENDIF  	
				
			IF pl_undercarriage_flag = 1 
				IF pl_undercarriage_down_time < pl_game_timer_diff  
					SET_PLANE_UNDERCARRIAGE_UP pl_plane FALSE
					pl_undercarriage_flag = 2
				ENDIF
			ENDIF
		ENDIF  	
	ENDIF

GOTO pl_plane_flying_loop 
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// mission cleanup
mission_cleanup_planes:

CLEAR_THIS_PRINT PLA_11  

CLEAR_THIS_PRINT PLA_12  
IF IS_STRING_EMPTY $shop_name
	RESTORE_CAMERA_JUMPCUT
ENDIF
IF IS_PLAYER_PLAYING player1
	SET_PLAYER_CONTROL player1 ON 
ENDIF
SET_CINEMA_CAMERA FALSE
//SWITCH_AMBIENT_PLANES TRUE
ALLOW_PAUSE_IN_WIDESCREEN FALSE
USE_TEXT_COMMANDS FALSE
MARK_CAR_AS_NO_LONGER_NEEDED pl_plane 
MARK_MODEL_AS_NO_LONGER_NEEDED at400
MARK_MODEL_AS_NO_LONGER_NEEDED gun_para

REMOVE_TEXTURE_DICTIONARY  
DISPLAY_HUD TRUE
DISPLAY_RADAR TRUE
SHOW_UPDATE_STATS TRUE
TERMINATE_THIS_SCRIPT

pl_which_city_coords:///////////////////////////////////////////////////////////////////
IF im_players_city = LEVEL_LOSANGELES
	pl_playerx = 1682.7
	pl_playery = -2244.9
	pl_playerz = 12.5
	pl_player_heading = 178.9
	IF pl_which_city_select = 0
		pl_planex = 2052.0 
		pl_planey = -2497.5 
		pl_planez = 12.4
		pl_plane_heading = 89.9
	ENDIF
	IF pl_which_city_select = 1
		pl_planex = 1580.0  
		pl_planey = -2493.5 
		pl_planez = 12.4
		pl_plane_heading = 270.0
	ENDIF
	pl_which_car_recording = 18
ENDIF 	  
IF im_players_city = LEVEL_SANFRANCISCO
	pl_playerx = -1431.6 
	pl_playery = -283.9
	pl_playerz = 13.1
	pl_player_heading = 149.9
	IF pl_which_city_select = 0
		pl_planex = -1626.0 
		pl_planey = -137.3 
		pl_planez = 13.0
		pl_plane_heading = 315.6
	ENDIF
	IF pl_which_city_select = 1
		pl_planex = -1590.9  
		pl_planey = -103.5 
		pl_planez = 13.0
		pl_plane_heading = 315.3
	ENDIF
	pl_which_car_recording = 19
ENDIF 	  
IF im_players_city = LEVEL_LASVEGAS
	pl_playerx = 1669.8
	pl_playery = 1422.1
	pl_playerz = 9.7
	pl_player_heading = 263.9
	IF pl_which_city_select = 0
		pl_planex = 1479.7 
		pl_planey = 1716.1 
		pl_planez = 9.7
		pl_plane_heading = 180.0
	ENDIF
	IF pl_which_city_select = 1
		pl_planex = 1475.7  
		pl_planey = 1636.0 
		pl_planez = 9.7
		pl_plane_heading = 180.0
	ENDIF
	pl_which_car_recording = 20
ENDIF 	  
RETURN/////////////////////////////////////////////////////////////////////////////////////

drawing_ticket_machine://////////////////////////////////////////////////////////////////////

	//drawing black background 
    
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
    DRAW_SPRITE 4 320.0 225.0 612.0 438.0 0 0 0 255
	
	SET_SPRITES_DRAW_BEFORE_FADE TRUE															  
	DRAW_SPRITE 4 320.0 430.0 640.0 250.0 0 0 0 255												  
																								  
	//drawing Juank Air background																		  
	SET_SPRITES_DRAW_BEFORE_FADE TRUE															  	
	DRAW_SPRITE 1 321.0 246.0 356.0 246.0 160 160 160 255

	//drawing grey box
	SET_SPRITES_DRAW_BEFORE_FADE TRUE															  
	DRAW_SPRITE 4 326.0 84.0 602.0 58.0 73 73 73 255												  
	
    //// OVERALL TITLE (always there) //////
	
	GOSUB planes_large_text
	SET_TEXT_COLOUR 255 255 255 255

	IF NOT IS_JAPANESE_VERSION
		IF current_Language = LANGUAGE_ENGLISH
			DISPLAY_TEXT 269.0 65.0 PLA_1
		ENDIF

		IF current_Language = LANGUAGE_FRENCH
			SET_TEXT_SCALE 0.9 3.4
			DISPLAY_TEXT 327.0 65.0 PLA_1
		ENDIF

		IF current_Language = LANGUAGE_GERMAN
			DISPLAY_TEXT 269.0 65.0 PLA_1
		ENDIF

		IF current_Language = LANGUAGE_ITALIAN
			SET_TEXT_SCALE 0.91 3.43
			DISPLAY_TEXT 322.0 65.0 PLA_1
		ENDIF

		IF current_Language = LANGUAGE_SPANISH
			SET_TEXT_SCALE 0.9 3.4
			DISPLAY_TEXT 322.0 65.0 PLA_1
		ENDIF
	ELSE
		DISPLAY_TEXT 322.0 65.0 PLA_1
	ENDIF
	
    
	//// DRAWING MACHINE OUT OF ORDER NOTICE ////
	//los santos only open 
		
	IF pl_city_open = 0

		GOSUB planes_large_text
		SET_TEXT_COLOUR 255 255 255 255
		IF current_Language = LANGUAGE_SPANISH
			SET_TEXT_SCALE 1.21 6.23
			DISPLAY_TEXT 320.0 204.0 PLA_25  //OUT OF ORDER
		ELSE
			SET_TEXT_SCALE 1.41 6.23
			DISPLAY_TEXT 316.0 204.0 PLA_25  //OUT OF ORDER
		ENDIF
		
		GOSUB planes_small_text	
		SET_TEXT_SCALE 0.62 1.94
		SET_TEXT_COLOUR 255 255 255 255
		IF IS_JAPANESE_VERSION
			DISPLAY_TEXT 362.0 370.0 SCHX
		ELSE
			DISPLAY_TEXT 277.0 370.0 PLA_23
		ENDIF
		
		GOSUB planes_small_text			 
		SET_TEXT_COLOUR 255 255 255 255
		DISPLAY_TEXT 298.0 368.0 PLA_21
		
	//// DRAWING TITLES (these don't change) ////

	ELSE
    
		GOSUB planes_small_text			 
		SET_TEXT_COLOUR 138 138 138 255
		DISPLAY_TEXT 92.0 144.0 PLA_2  //DEPARTURE
		
		GOSUB planes_small_text			 
		SET_TEXT_COLOUR 138 138 138 255
		DISPLAY_TEXT 92.0 207.0 PLA_13  //DESTINATION
		
		GOSUB planes_small_text			 
		SET_TEXT_COLOUR 138 138 138 255
		DISPLAY_TEXT 92.0 267.0 PLA_17  //PRICE
	
        //// DRAWING HELP TEXT ////
		//everything open 
		
		IF pl_city_open > 1
			
			GOSUB planes_small_text	
			SET_TEXT_SCALE 0.62 1.54		 
			SET_TEXT_COLOUR 255 255 255 255

			IF current_Language = LANGUAGE_ENGLISH
				DISPLAY_TEXT 92.0 372.0 PLA_22 //~<~~>~
			ENDIF

			IF current_Language = LANGUAGE_FRENCH
				DISPLAY_TEXT 52.0 372.0 PLA_22 //~<~~>~
			ENDIF

			IF current_Language = LANGUAGE_GERMAN
				DISPLAY_TEXT 52.0 372.0 PLA_22 //~<~~>~
			ENDIF

			IF current_Language = LANGUAGE_ITALIAN
				DISPLAY_TEXT 52.0 372.0 PLA_22 //~<~~>~
			ENDIF

			IF current_Language = LANGUAGE_SPANISH
				DISPLAY_TEXT 52.0 372.0 PLA_22 //~<~~>~
			ENDIF
			
			GOSUB planes_small_text			 
			SET_TEXT_COLOUR 255 255 255 255
			

			IF current_Language = LANGUAGE_ENGLISH
				DISPLAY_TEXT 124.0 368.0 PLA_19 //SELECT
			ENDIF

			IF current_Language = LANGUAGE_FRENCH
				DISPLAY_TEXT 84.0 368.0 PLA_19 //SELECT
			ENDIF

			IF current_Language = LANGUAGE_GERMAN
				DISPLAY_TEXT 84.0 368.0 PLA_19 //SELECT
			ENDIF

			IF current_Language = LANGUAGE_ITALIAN
				DISPLAY_TEXT 84.0 368.0 PLA_19 //SELECT
			ENDIF

			IF current_Language = LANGUAGE_SPANISH
				DISPLAY_TEXT 84.0 368.0 PLA_19 //SELECT
			ENDIF
			

			GOSUB planes_small_text			 
			SET_TEXT_SCALE 0.62 1.94
			SET_TEXT_COLOUR 255 255 255 255

			IF IS_JAPANESE_VERSION
				DISPLAY_TEXT 260.0 370.0 SCHO
			ELSE
				IF current_Language = LANGUAGE_ENGLISH
					DISPLAY_TEXT 260.0 370.0 PLA_24
				ENDIF

				IF current_Language = LANGUAGE_FRENCH
					DISPLAY_TEXT 295.0 370.0 PLA_24
				ENDIF

				IF current_Language = LANGUAGE_GERMAN
					DISPLAY_TEXT 295.0 370.0 PLA_24
				ENDIF

				IF current_Language = LANGUAGE_ITALIAN
					DISPLAY_TEXT 258.0 370.0 PLA_24
				ENDIF

				IF current_Language = LANGUAGE_SPANISH
					DISPLAY_TEXT 295.0 370.0 PLA_24
				ENDIF
			ENDIF
			

			GOSUB planes_small_text			 
			SET_TEXT_COLOUR 255 255 255 255

			IF current_Language = LANGUAGE_ENGLISH
				DISPLAY_TEXT 281.0 368.0 PLA_20
			ENDIF

			IF current_Language = LANGUAGE_FRENCH
				DISPLAY_TEXT 312.0 368.0 PLA_20
			ENDIF

			IF current_Language = LANGUAGE_GERMAN
				DISPLAY_TEXT 312.0 368.0 PLA_20
			ENDIF

			IF current_Language = LANGUAGE_ITALIAN
				DISPLAY_TEXT 277.0 368.0 PLA_20
			ENDIF

			IF current_Language = LANGUAGE_SPANISH
				DISPLAY_TEXT 312.0 368.0 PLA_20
			ENDIF

			GOSUB planes_small_text			 
			SET_TEXT_SCALE 0.62 1.94
			SET_TEXT_COLOUR 255 255 255 255

			IF IS_JAPANESE_VERSION
				DISPLAY_TEXT 362.0 370.0 SCHX
			ELSE
				IF current_Language = LANGUAGE_ENGLISH
					DISPLAY_TEXT 362.0 370.0 PLA_23
				ENDIF

				IF current_Language = LANGUAGE_FRENCH
					DISPLAY_TEXT 458.0 370.0 PLA_23
				ENDIF

				IF current_Language = LANGUAGE_GERMAN
					DISPLAY_TEXT 458.0 370.0 PLA_23
				ENDIF

				IF current_Language = LANGUAGE_ITALIAN
					DISPLAY_TEXT 458.0 370.0 PLA_23
				ENDIF

				IF current_Language = LANGUAGE_SPANISH
					DISPLAY_TEXT 458.0 370.0 PLA_23
				ENDIF
			ENDIF

			GOSUB planes_small_text			 
			SET_TEXT_COLOUR 255 255 255 255

			IF current_Language = LANGUAGE_ENGLISH
				DISPLAY_TEXT 383.0 368.0 PLA_21
			ENDIF

			IF current_Language = LANGUAGE_FRENCH
				DISPLAY_TEXT 477.0 368.0 PLA_21
			ENDIF

			IF current_Language = LANGUAGE_GERMAN
				DISPLAY_TEXT 477.0 368.0 PLA_21
			ENDIF

			IF current_Language = LANGUAGE_ITALIAN
				DISPLAY_TEXT 477.0 368.0 PLA_21
			ENDIF

			IF current_Language = LANGUAGE_SPANISH
				DISPLAY_TEXT 477.0 368.0 PLA_21
			ENDIF

		ELSE
			//san fierro and los santos open
			GOSUB planes_small_text			 
			SET_TEXT_SCALE 0.62 1.94
			SET_TEXT_COLOUR 255 255 255 255
			IF IS_JAPANESE_VERSION
				DISPLAY_TEXT 220.0 370.0 SCHO
			ELSE
				IF current_Language = LANGUAGE_ENGLISH
					DISPLAY_TEXT 220.0 370.0 PLA_24
				ELSE
					DISPLAY_TEXT 170.0 370.0 PLA_24
				ENDIF
			ENDIF

			GOSUB planes_small_text			
			SET_TEXT_COLOUR 255 255 255 255
			IF current_Language = LANGUAGE_ENGLISH
				DISPLAY_TEXT 241.0 368.0 PLA_20
			ELSE
				DISPLAY_TEXT 191.0 368.0 PLA_20
			ENDIF 

			GOSUB planes_small_text			
			SET_TEXT_SCALE 0.62 1.94 
			SET_TEXT_COLOUR 255 255 255 255
			IF IS_JAPANESE_VERSION
				DISPLAY_TEXT 328.0 370.0 SCHX
			ELSE
				DISPLAY_TEXT 328.0 370.0 PLA_23
			ENDIF

			GOSUB planes_small_text			
			SET_TEXT_COLOUR 255 255 255 255
			DISPLAY_TEXT 349.0 368.0 PLA_21
		ENDIF
				 
//// DRAWING DAY and PRICE ////
		
		GOSUB planes_small_text			 
		SET_TEXT_COLOUR 255 255 255 255
		DISPLAY_TEXT 92.0 167.0 $pl_days[pl_days_flag]  //DAY

		//"$~1~"
		GOSUB planes_small_text			 
		SET_TEXT_COLOUR 255 255 255 255
		DISPLAY_TEXT_WITH_NUMBER 92.0 290.0 PLA_18 pl_ticket_price //$~1~
		
		
//// LOS SANTOS TICKET MACHINE ////
		IF im_players_city = LEVEL_LOSANGELES
			//"SAN FIERRO"
			GOSUB planes_small_text			 
			IF pl_which_city_select = 0
				SET_TEXT_COLOUR 255 255 255 255
			ELSE
				SET_TEXT_COLOUR 40 40 40 255
			ENDIF
			
			DISPLAY_TEXT 92.0 230.0 PLA_15  //SAN FIERRO
			
			IF pl_city_open > 1
				//"VEGAS"
				GOSUB planes_small_text			 
				IF pl_which_city_select = 0
					SET_TEXT_COLOUR 40 40 40 255
				ELSE
					SET_TEXT_COLOUR 255 255 255 255
				ENDIF
				DISPLAY_TEXT 282.0 230.0 PLA_14 // LAS VENTURAS
			ENDIF

			//"time of day"
			GOSUB planes_small_text			 
			SET_TEXT_COLOUR 255 255 255 255
			IF pl_which_city_select = 0
				DISPLAY_TEXT_WITH_2_NUMBERS 281.0 167.0 PLA_10 10 30 //TIME OF DAY
			ELSE
				DISPLAY_TEXT_WITH_2_NUMBERS 281.0 167.0 PLA_10 17 50 //TIME OF DAY
			ENDIF
		ENDIF	
	
//// SAN FIERRO TICKET MACHINE ////
		IF im_players_city = LEVEL_SANFRANCISCO
			
			GOSUB planes_small_text			 
			IF pl_which_city_select = 0
				SET_TEXT_COLOUR 255 255 255 255
			ELSE
				SET_TEXT_COLOUR 40 40 40 255
			ENDIF
			
			DISPLAY_TEXT 92.0 230.0 PLA_16 // LOS SANTOS
	
			IF pl_city_open > 1
				
				GOSUB planes_small_text			 
				IF pl_which_city_select = 0
					SET_TEXT_COLOUR 40 40 40 255
				ELSE
					SET_TEXT_COLOUR 255 255 255 255
				ENDIF
				
				DISPLAY_TEXT 282.0 230.0 PLA_14 // LAS VENTURAS
			ENDIF

			//"time of day"
			GOSUB planes_small_text			 
			SET_TEXT_COLOUR 255 255 255 255
			IF pl_which_city_select = 0
				DISPLAY_TEXT_WITH_2_NUMBERS 281.0 167.0 PLA_10 11 20 //TIME OF DAY
			ELSE
				DISPLAY_TEXT_WITH_2_NUMBERS 281.0 167.0 PLA_10 14 40  //TIME OF DAY
			ENDIF
		ENDIF
	
//// SAN FIERRO TICKET MACHINE ////
		IF im_players_city = LEVEL_LASVEGAS
			//"LOS SANTOS"
			GOSUB planes_small_text			 
			IF pl_which_city_select = 0
				SET_TEXT_COLOUR 255 255 255 255
			ELSE
				SET_TEXT_COLOUR 40 40 40 255
			ENDIF
			
			DISPLAY_TEXT 92.0 230.0 PLA_16  // LOS SANTOS
	
			//"SAN FIERRO"
			GOSUB planes_small_text			 
			IF pl_which_city_select = 0
				SET_TEXT_COLOUR 40 40 40 255
			ELSE
				SET_TEXT_COLOUR 255 255 255 255
			ENDIF
			
			DISPLAY_TEXT 282.0 230.0 PLA_15  //SAN FIERRO
	
			//"time of day"
			GOSUB planes_small_text			 
			SET_TEXT_COLOUR 255 255 255 255
			IF pl_which_city_select = 0
				DISPLAY_TEXT_WITH_2_NUMBERS 281.0 167.0 PLA_10 19 10 //TIME OF DAY
			ELSE
				DISPLAY_TEXT_WITH_2_NUMBERS 281.0 167.0 PLA_10 14 30  //TIME OF DAY
			ENDIF
		ENDIF
	ENDIF				 


	//TV SCREEN HUD
	SET_SPRITES_DRAW_BEFORE_FADE TRUE 
	DRAW_SPRITE	2 160.0 112.0 320.0 224.0 150 150 150 255 //top left
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE	2 160.0 317.0 320.0 -224.0 150 150 150 255 //bottom right
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE	2 480.0 112.0 -320.0 224.0 150 150 150 255 //top right 
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE	2 480.0 317.0 -320.0 -224.0 150 150 150 255 //bottom left
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE	3 160.0 435.0 320.0 17.0 150 150 150 255 //bottom left extra bit
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE	3 480.0 435.0 -320.0 17.0 150 150 150 255 //bottom right extra bit
RETURN/////////////////////////////////////////////////////////////////////////////////////

planes_large_text://///////////////////////////////////////////////////////////////////////
	SET_TEXT_DRAW_BEFORE_FADE TRUE
	SET_TEXT_JUSTIFY ON
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_WRAPX 1000.0
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_CENTRE ON
	SET_TEXT_BACKGROUND OFF
	SET_TEXT_SCALE 1.0 3.4
	SET_TEXT_DROPSHADOW 3 0 0 0 255
	SET_TEXT_EDGE 2 0 0 0 255  
	SET_TEXT_FONT FONT_SPACEAGE
RETURN/////////////////////////////////////////////////////////////////////////////////////

planes_small_text://///////////////////////////////////////////////////////////////////////
	SET_TEXT_DRAW_BEFORE_FADE TRUE
	SET_TEXT_JUSTIFY ON
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_WRAPX 1000.0
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_CENTRE OFF
	SET_TEXT_BACKGROUND OFF
	SET_TEXT_SCALE 0.62 2.34 
	SET_TEXT_DROPSHADOW 2 0 0 0 255
	SET_TEXT_EDGE 2 0 0 0 255  
	SET_TEXT_FONT FONT_SPACEAGE
RETURN/////////////////////////////////////////////////////////////////////////////////////

MISSION_END
}








