MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* Cesar 2 *******************************************
// ********************************* ?????????????? ****************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************


SCRIPT_NAME cesar2

// Mission start stuff

GOSUB mission_start_cesar2

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_cesar2_failed
ENDIF

GOSUB mission_cleanup_cesar2

MISSION_END

{
 
// Variables for mission
LVAR_INT end_scene_now scene_Wait scene_flag cs2_task_status

//blips
LVAR_INT cs2_mission_blip cs2_blip_flag											  

//cars

//coords
LVAR_FLOAT cs2_x cs2_y cs2_z
//VAR_FLOAT cs2_MinX cs2_MinY cs2_MinZ cs2_MaxX cs2_MaxY cs2_MaxZ


	 
mission_start_cesar2:

	flag_player_on_mission = 1

	IF flag_player_on_mission = 39
		CREATE_CAR CHEETAH 0.0 0.0 0.0 cs2_player_car
	ENDIF

	IF NOT IS_CAR_DEAD cs2_player_car
		SET_CAR_COORDINATES cs2_player_car 1534.7788 -1644.9319 4.8984
		SET_CAR_HEADING cs2_player_car 180.2225
		SET_CAR_ENGINE_ON cs2_player_car FALSE
		SET_CAR_LIGHTS_ON cs2_player_car FALSE

		SET_CAR_AS_MISSION_CAR cs2_player_car
		SET_LOAD_COLLISION_FOR_CAR_FLAG cs2_player_car TRUE
		LOCK_CAR_DOORS cs2_player_car CARLOCK_UNLOCKED 

		LVAR_INT cs2_MaxPassengers cs2_i cs2_charid
		GET_MAXIMUM_NUMBER_OF_PASSENGERS cs2_player_car cs2_MaxPassengers
		cs2_i = 0
		WHILE cs2_i < cs2_MaxPassengers 
			IF NOT IS_CAR_PASSENGER_SEAT_FREE cs2_player_car cs2_i
				GET_CHAR_IN_CAR_PASSENGER_SEAT cs2_player_car cs2_i cs2_CharID
				DELETE_CHAR cs2_charID
			ENDIF
			cs2_i ++
		ENDWHILE

//		FREEZE_CAR_POSITION cs2_player_car TRUE
	ENDIF
	WARP_CHAR_FROM_CAR_TO_COORD scplayer 1796.8322 -2118.3374 12.5547
 

   	end_Scene_now = 0
	scene_flag = 3

	LOAD_MISSION_TEXT CESAR2


	// Scene 2 - Cutscene for CJ talking to Cesar and CJ's car gets nabbed
	WHILE end_scene_now = 0
		WAIT 0
		GOSUB cesar2_audio
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
        	GOTO mission_cesar2_passed  
		ENDIF
   		IF scene_flag = 3

			SET_CAR_DENSITY_MULTIPLIER 0.0
			SET_PED_DENSITY_MULTIPLIER 0.0

			LOAD_CUTSCENE CESAR2A
			 
			WHILE NOT HAS_CUTSCENE_LOADED
			            WAIT 0
			ENDWHILE

			CLEAR_AREA 1796.4819 -2129.3479 14.2444 150.0 TRUE
			 
			START_CUTSCENE

			DO_FADE 1000 FADE_IN
			  
			WHILE NOT HAS_CUTSCENE_FINISHED
			    WAIT 0
			ENDWHILE
			 
			

			SET_FADING_COLOUR 0 0 0
			DO_FADE 0 FADE_OUT

			REQUEST_MODEL COPCARLA
			REQUEST_MODEL LAPD1

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			CLEAR_CUTSCENE

			WHILE NOT HAS_MODEL_LOADED COPCARLA
			OR NOT HAS_MODEL_LOADED LAPD1
				WAIT 0
			ENDWHILE

			CLEAR_AREA 1573.2593 -1658.1173 17.8007 100.0 TRUE

			SET_CHAR_COORDINATES scplayer 1583.9818 -1636.1099 12.3905
			SET_FIXED_CAMERA_POSITION 1512.5804 -1617.6443 13.8058 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 1513.3191 -1618.2893 14.0010 JUMP_CUT
			SWITCH_WIDESCREEN ON
			SET_PLAYER_CONTROL player1 OFF

			LOAD_SCENE 1598.0006 -1618.6028 12.8122
			

			LVAR_INT cs_seq cs_cop_car cs_cop

			CREATE_CAR COPCARLA 1527.0925 -1606.7228 12.3828 cs_cop_car 
			SET_CAR_HEADING cs_cop_car 180.0
			SET_CAR_CAN_GO_AGAINST_TRAFFIC cs_cop_car TRUE
			CREATE_CHAR_INSIDE_CAR cs_cop_car PEDTYPE_MISSION1 LAPD1 cs_cop

			WAIT 800

			IF NOT IS_CAR_DEAD cs_cop_car
			AND NOT IS_CHAR_DEAD cs_cop

				OPEN_SEQUENCE_TASK cs_seq
					TASK_CAR_DRIVE_TO_COORD -1 cs_cop_car 1526.4885 -1620.8052 12.3828 15.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS 
					TASK_CAR_DRIVE_TO_COORD -1 cs_cop_car 1537.0930 -1625.6025 12.3828 7.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS 
					TASK_PAUSE -1 2500
					TASK_CAR_DRIVE_TO_COORD -1 cs_cop_car 1548.1996 -1625.6206 12.3828 10.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS
					TASK_CAR_DRIVE_TO_COORD -1 cs_cop_car 1581.9962 -1627.9320 12.3828 10.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS 
					TASK_PAUSE -1 2500
					TASK_CAR_DRIVE_TO_COORD	-1 cs_cop_car 1587.0450 -1635.4348 12.3905 10.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS
					TASK_CAR_DRIVE_TO_COORD	-1 cs_cop_car 1594.7870 -1700.9177 4.8970 10.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS 		
				CLOSE_SEQUENCE_TASK cs_seq
				PERFORM_SEQUENCE_TASK cs_cop cs_seq
				CLEAR_SEQUENCE_TASK cs_seq

			ENDIF

			DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			SKIP_CUTSCENE_START
			scene_wait = TIMERA + 8000
			scene_flag = 4

			force_audio = 1 

			play_timed_audio = 1
			play_timed_audio_for = 8

			audio_time[0] = 395 
			audio_time[1] = 4202 
			audio_time[2] = 9380 
			audio_time[3] = 15000 
			audio_time[4] = 16516
			audio_time[5] = 19352
			audio_time[6] = 23617
			audio_time[7] = 26861

			audio_to_play[0] = 1
			audio_to_play[1] = 2
			audio_to_play[2] = 3
			audio_to_play[3] = 4
			audio_to_play[4] = 5
			audio_to_play[5] = 6
			audio_to_play[6] = 7
			audio_to_play[7] = 8


		ENDIF

		LVAR_INT im_barrier_opens im_shutter_opens

		IF im_barrier_opens = 0
			IF IS_AREA_OCCUPIED 1535.8057 -1617.2372 20.2196 1552.9937 -1632.9320 6.3988 FALSE TRUE FALSE FALSE FALSE
				door_control[0] = 1
				im_barrier_opens = 1
			ENDIF
		ENDIF

		IF im_shutter_opens = 0
			IF IS_AREA_OCCUPIED 1583.0176 -1631.2181 16.4270 1597.1375 -1649.1669 9.4768 FALSE TRUE FALSE FALSE FALSE
				door_control[0] = 1
				im_shutter_opens = 1
			ENDIF
		ENDIF


		IF scene_flag = 4
			IF TIMERA > scene_wait
				SET_FIXED_CAMERA_POSITION 1579.1005 -1624.9429 14.1665 0.0 0.0 0.0
//				POINT_CAMERA_AT_POINT 1598.0006 -1618.6028 12.8122 JUMP_CUT
				scene_flag = 5
				SET_CHAR_COORDINATES scplayer 1593.2775 -1717.9689 5.2265
				scene_wait = TIMERA + 8000				
			ENDIF
		ENDIF

		IF scene_flag = 5
			IF NOT IS_CAR_DEAD cs_cop_car
				GET_CAR_COORDINATES cs_cop_car cs2_x cs2_y cs2_z
				cs2_z += 1.0
				POINT_CAMERA_AT_POINT cs2_x cs2_y cs2_z JUMP_CUT
			ENDIF

			IF TIMERA > scene_wait
				SET_FIXED_CAMERA_POSITION 1606.5302 -1720.0452 6.1467 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1605.9066 -1719.2644 6.1048 JUMP_CUT
				scene_flag = 6
				scene_wait = TIMERA + 8000				
			ENDIF
		ENDIF

		IF scene_flag = 6
			IF TIMERA > scene_wait
				SET_FIXED_CAMERA_POSITION 1525.8379 -1634.0477 10.5122 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1526.2441 -1634.9055 10.1975 JUMP_CUT
				scene_flag = 7
				scene_wait = TIMERA + 8000				
			ENDIF
		ENDIF
				
		IF scene_flag = 7
			IF TIMERA > scene_wait
				SKIP_CUTSCENE_END
				DO_FADE 800 FADE_OUT
				
				
				scene_flag = 8
			ENDIF
		ENDIF

		IF scene_flag = 8
			IF NOT GET_FADING_STATUS

				SET_CAR_DENSITY_MULTIPLIER 1.0
				SET_PED_DENSITY_MULTIPLIER 1.0
				CLEAR_PRINTS
				SET_CHAR_COORDINATES scplayer 1793.1068 -2122.2827 12.5547
				SET_CHAR_HEADING scplayer 0.0

				LOAD_SCENE 1794.4355 -2117.4565 12.3902

				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL player1 ON
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT


				IF NOT IS_CAR_DEAD cs2_player_car
					ADD_BLIP_FOR_CAR cs2_player_car cs2_mission_blip
					SET_BLIP_AS_FRIENDLY cs2_mission_blip TRUE
				ENDIF
				DELETE_CAR cs_cop_car
				DELETE_CHAR cs_cop
				scene_wait = TIMERA + 1000
				PRINT CS2_23 8000 1				
				scene_flag = 9
			ENDIF			
		ENDIF
		
		IF scene_flag = 9
			IF TIMERA > scene_wait
				DO_FADE 800 FADE_IN
				end_scene_now = 1		
			ENDIF			
		ENDIF		
	ENDWHILE

	end_Scene_now = 0
	scene_flag = 0

	//scene 3 - player must get over to the police precinct and steal the car
	WHILE end_scene_now = 0
		WAIT 0
IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
            GOTO mission_cesar2_passed  
ENDIF

			IF IS_CAR_DEAD cs2_player_car
				CLEAR_PRINTS
				PRINT CS2_16 8000 1				
				GOTO mission_cesar2_failed
			ENDIF


		IF scene_flag = 0
			IF NOT IS_CAR_DEAD cs2_player_car
				IF IS_CHAR_IN_CAR scplayer cs2_player_car
					REMOVE_BLIP cs2_mission_blip
					ADD_SPRITE_BLIP_FOR_COORD 2065.6299 -1831.4094 12.5527 RADAR_SPRITE_SPRAY cs2_mission_blip
					ALTER_WANTED_LEVEL_NO_DROP Player1 3
					cs2_blip_flag = 1
					CLEAR_PRINTS
					PRINT CS2_A1 4000 1	
					PRINT CS2_A4 4000 1
					scene_flag = 1
					LVAR_INT fake_cop[2] fake_cops_dec
					CREATE_CHAR PEDTYPE_MISSION1 LAPD1 1568.6577 -1707.9141 4.8984 fake_cop[0]
					SET_CHAR_HEADING fake_cop[0] 60.0
					SET_CHAR_STAY_IN_SAME_PLACE fake_cop[0] TRUE
//					TASK_KILL_CHAR_ON_FOOT fake_cop[0] scplayer
					CREATE_CHAR PEDTYPE_MISSION1 LAPD1 1571.0417 -1705.6963 4.8984 fake_cop[1]
					SET_CHAR_HEADING fake_cop[1] 60.0
					SET_CHAR_STAY_IN_SAME_PLACE fake_cop[1] TRUE
					TASK_TOGGLE_DUCK fake_cop[1] TRUE
					COPY_CHAR_DECISION_MAKER DM_PED_RANDOM_TOUGH fake_cops_dec
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE fake_cops_dec EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 TRUE TRUE
					GIVE_WEAPON_TO_CHAR fake_cop[0] WEAPONTYPE_PISTOL 9999
					GIVE_WEAPON_TO_CHAR fake_cop[1] WEAPONTYPE_PISTOL 9999
					SET_CHAR_RELATIONSHIP fake_cop[0] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
					SET_CHAR_RELATIONSHIP fake_cop[1] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
					SET_CHAR_DECISION_MAKER fake_cop[0] fake_cops_dec
					SET_CHAR_DECISION_MAKER fake_cop[1] fake_cops_dec
//					TASK_KILL_CHAR_ON_FOOT fake_cop[1] scplayer
				ENDIF  
			ENDIF
		ENDIF
		IF scene_flag = 1
			IF NOT IS_WANTED_LEVEL_GREATER Player1 0
				wanted_check_wait_time = TIMERA + 4000
				scene_flag = 2
			ENDIF	
				
			IF cs2_blip_flag = 1
				IF LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer 2065.6299 -1831.4094 12.5527 14.0 14.0 14.0 FALSE
					PRINT_NOW CS2_A3 1000 1
				ENDIF
				IF NOT IS_CHAR_IN_CAR scplayer cs2_player_car
					REMOVE_BLIP cs2_mission_blip
					IF NOT IS_CAR_DEAD cs2_player_car
						ADD_BLIP_FOR_CAR cs2_player_car cs2_mission_blip
						SET_BLIP_AS_FRIENDLY cs2_mission_blip TRUE
						cs2_blip_flag = 0
						CLEAR_PRINTS
						PRINT CS2_25 6000 1
					ENDIF
				ENDIF
			ENDIF
			IF cs2_blip_flag = 0
				IF IS_CHAR_IN_CAR scplayer cs2_player_car
					REMOVE_BLIP cs2_mission_blip
					ADD_SPRITE_BLIP_FOR_COORD 2065.6299 -1831.4094 12.5527 RADAR_SPRITE_SPRAY cs2_mission_blip
					cs2_blip_flag = 1
					CLEAR_PRINTS
					PRINT CS2_A2 6000 1
					
				ENDIF
			ENDIF
		ENDIF

		IF scene_flag = 2
		LVAR_INT wanted_check_wait_time
			IF TIMERA > wanted_check_wait_time
				IF NOT IS_WANTED_LEVEL_GREATER Player1 0
					GOTO mission_cesar2_passed
				ELSE
					scene_flag = 1	
				ENDIF
			ENDIF
		ENDIF
	ENDWHILE
RETURN



cesar2_audio:


	LVAR_INT play_timed_audio play_timed_audio_flag audio_time_start audio_timer_flag audio_time[20] audio_to_play[20] play_timed_audio_for
	LVAR_INT audio_plays_global // flag for peds do speech anims but dialogue not attached to ped.
 


	// play timed audio
	IF NOT play_timed_audio = 0
		IF play_timed_audio_flag = 0
			play_timed_audio_flag = 1
			audio_time_start = TIMERA
			audio_timer_flag = 0
			play_delay = audio_time_start + audio_time[audio_timer_flag]
		ENDIF	

		IF play_timed_audio_flag = 1
			IF TIMERA > play_delay
			AND play_audio = 0
				play_audio = audio_to_play[audio_timer_flag]
				audio_timer_flag ++
				play_timed_audio_for --
				IF play_timed_audio_for = 0
					play_timed_audio = 0
					play_timed_audio_flag = 0
				ELSE
					play_delay = audio_time_start + audio_time[audio_timer_flag]
				ENDIF
			ENDIF
		ENDIF
	ENDIF
		
		


	SWITCH audio_flag

		CASE 0 //first time game starts

			LVAR_TEXT_LABEL audio_text[100]
			LVAR_INT audio_sound[100] audio_slot[3] play_slot  
			LVAR_INT next_audio  
			LVAR_INT audio_flag play_audio play_audio_for

			LVAR_INT audio_for_char[100] audio_actor[7] play_audio_now
			LVAR_INT actor_int this_actor loaded_audio play_delay audio_i audio_char audio_count force_audio play_audio_delay

			IF audio_flag = 1
				CREATE_CHAR PEDTYPE_GANG_GROVE SPECIAL01 2752.3408 -1948.8669 16.3125 this_actor
				CREATE_CHAR PEDTYPE_GANG_GROVE SPECIAL01 2752.3408 -1948.8669 16.3125 audio_char
			ENDIF


			audio_sound[1] = SOUND_CES2_ZA
			audio_sound[2] = SOUND_CES2_ZB
			audio_sound[3] = SOUND_CES2_ZC
			audio_sound[4] = SOUND_CES2_ZD
			audio_sound[5] = SOUND_CES2_ZE
			audio_sound[6] = SOUND_CES2_ZF
			audio_sound[7] = SOUND_CES2_ZG
			audio_sound[8] = SOUND_CES2_ZH
	 
			$audio_text[1] = &CES2_ZA
			$audio_text[2] = &CES2_ZB
			$audio_text[3] = &CES2_ZC
			$audio_text[4] = &CES2_ZD
			$audio_text[5] = &CES2_ZE
			$audio_text[6] = &CES2_ZF
			$audio_text[7] = &CES2_ZG
			$audio_text[8] = &CES2_ZH
		 
			audio_for_char[1] = 0
			audio_for_char[2] = 0
			audio_for_char[3] = 0
			audio_for_char[4] = 0
			audio_for_char[5] = 0
			audio_for_char[6] = 0
			audio_for_char[7] = 0
			audio_for_char[8] = 0
	   
			audio_actor[1] = scplayer











			
			//1 = catalina
			//2 = player

			audio_flag = 1
//			play_audio = 0

			LOAD_MISSION_AUDIO 1 audio_sound[1]
			LOAD_MISSION_AUDIO 2 audio_sound[2]

			audio_slot[1] = 1
			audio_slot[2] = 2


		BREAK

		CASE 1 //waiting to play audio
			
			IF NOT play_audio = 0
				IF TIMERA > play_audio_delay
					IF HAS_MISSION_AUDIO_FINISHED 1
					AND HAS_MISSION_AUDIO_FINISHED 2
						IF audio_slot[1] = play_audio
							play_slot = 1
						ELSE
							IF audio_slot[2] = play_audio
								play_slot = 2
							ELSE
								play_slot = 1
								audio_slot[1] = play_audio
								CLEAR_MISSION_AUDIO 1
								LOAD_MISSION_AUDIO 1 audio_sound[play_audio]
								//audio hasn't been requested yet
							ENDIF
						ENDIF			

						IF HAS_MISSION_AUDIO_LOADED play_slot
							actor_int = audio_for_char[play_audio]
							this_actor = audio_actor[actor_int]
							IF NOT force_audio = 1 //otherwise audio will not play if no mission peds are nearby
								audio_i = 1
								audio_count = 0
								WHILE audio_i < 7
									audio_char = audio_actor[audio_i]
									IF NOT audio_char = 0
										IF NOT audio_char = this_actor
											IF NOT IS_CHAR_DEAD this_actor
												IF NOT IS_CHAR_DEAD audio_char
													IF LOCATE_CHAR_ANY_MEANS_CHAR_3D this_actor audio_char 10.0 10.0 10.0 FALSE
														audio_count ++
													ENDIF
												ENDIF
											ENDIF
										ENDIF
									ENDIF
									audio_i++
								ENDWHILE
							ENDIF

							IF force_audio = 1
							OR audio_count > 0
							OR audio_for_char[play_audio] = 0
								IF NOT audio_for_char[play_audio] = 0
									IF NOT IS_CHAR_DEAD this_actor   
										ATTACH_MISSION_AUDIO_TO_CHAR play_slot this_actor								 
										IF NOT IS_CHAR_TALKING this_actor
											play_audio_now = 1
											START_CHAR_FACIAL_TALK this_actor 15000
										ELSE
											DISABLE_CHAR_SPEECH this_actor FALSE
										ENDIF
									ENDIF
								ENDIF

								IF audio_for_char[play_audio] = 0
									play_audio_now = 1
								ENDIF

								IF play_audio_now = 1							
									PLAY_MISSION_AUDIO play_slot
									CLEAR_PRINTS
									PRINT $audio_text[play_audio] 10000 1
									audio_flag ++
									play_audio_now = 0

									play_audio ++
									next_audio = play_audio

									// if the other slot doesn't already have the next audio loaded, then load it.
									IF NOT audio_sound[play_audio] = 0
										IF play_slot = 1									
											IF NOT audio_slot[2] = play_audio
												LOAD_MISSION_AUDIO 2 audio_sound[play_audio]	
												audio_slot[2] = play_audio
											ENDIF
										ELSE
											IF NOT audio_slot[1] = play_audio
												LOAD_MISSION_AUDIO 1 audio_sound[play_audio]	
												audio_slot[1] = play_audio
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ELSE
								audio_flag = 6
							ENDIF
						ELSE
							LOAD_MISSION_AUDIO play_slot audio_sound[play_audio]
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		BREAK


		CASE 2 // check if audio has/should finish
			IF HAS_MISSION_AUDIO_FINISHED play_slot
				audio_flag++
			ELSE
				IF DOES_CHAR_EXIST this_actor
					IF IS_CHAR_DEAD this_actor
						audio_flag++
					ENDIF
				ENDIF
				IF NOT IS_CHAR_DEAD this_actor
				ENDIF
			ENDIF
		BREAK

		CASE 3 //clear audio
			CLEAR_MISSION_AUDIO play_slot
			audio_slot[play_slot] = 0
			CLEAR_PRINTS 
			IF NOT IS_CHAR_DEAD this_actor
				STOP_CHAR_FACIAL_TALK this_actor
				ENABLE_CHAR_SPEECH this_actor
			ENDIF
			audio_flag++
		BREAK

		CASE 4 //request next audio

			play_audio ++
			IF NOT audio_sound[play_audio] = 0 
				LOAD_MISSION_AUDIO play_slot audio_sound[play_audio]
				audio_slot[play_slot] = play_audio
			ENDIF
			
			play_audio_for -= 1
			IF NOT play_audio_for > 0
				play_audio = 0
				play_audio_for = 0
			ELSE
				play_audio = next_audio
			ENDIF
			audio_flag = 1
		BREAK

		CASE 5 // clear all for cut scene skip

			audio_flag = 1
			play_audio = 0
			play_audio_for = 0
			play_timed_audio = 0
			play_timed_audio_for = 0
			CLEAR_MISSION_AUDIO play_slot
			audio_slot[play_slot] = 0
			CLEAR_PRINTS
			IF NOT IS_CHAR_DEAD this_actor
				ENABLE_CHAR_SPEECH this_actor
			ENDIF

		BREAK

		CASE 6 // clear all for cut scene skip (fade)

			audio_flag = 2
			play_audio = 0
			play_audio_for = 0
			play_timed_audio = 0
			play_timed_audio_for = 0
			audio_slot[play_slot] = 0
			CLEAR_PRINTS
			IF NOT IS_CHAR_DEAD this_actor
				ENABLE_CHAR_SPEECH this_actor
			ENDIF

		BREAK


	ENDSWITCH
RETURN












// ****************************************START OF CUTSCENE********************************




// ****************************************END OF CUTSCENE**********************************

// fades the screen in 


 
 // **************************************** Mission cesar2 failed ************************

mission_cesar2_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN

   

// **************************************** mission cesar2 passed *************************

mission_cesar2_passed:

REMOVE_BLIP cs2_mission_Blip

REGISTER_MISSION_PASSED ( CESAR_2 ) //Used in the stats 
PLAYER_MADE_PROGRESS 1

PLAY_MISSION_PASSED_TUNE 1
CLEAR_WANTED_LEVEL PLAYER1

PRINT_WITH_NUMBER_BIG ( M_PASSR ) 5 5000 1 //"Mission Passed!"
AWARD_PLAYER_MISSION_RESPECT 5 //amount of respect

flag_cesar_mission_counter ++
REMOVE_BLIP cesar_contact_blip

//TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME CESAR

RETURN
		


// ********************************** mission cleanup ************************************

mission_cleanup_cesar2:


			MARK_MODEL_AS_NO_LONGER_NEEDED COPCARLA
			MARK_MODEL_AS_NO_LONGER_NEEDED LAPD1
			MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
   			MARK_MODEL_AS_NO_LONGER_NEEDED COLT45



REMOVE_BLIP cs2_mission_blip
flag_player_on_mission = 0
GET_GAME_TIMER timer_mobile_start

MISSION_HAS_FINISHED
RETURN

 
}