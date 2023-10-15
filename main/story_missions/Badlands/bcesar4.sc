MISSION_START
// *******************************************************************************************
// ********************************** Race Tournament ****************************************
// *******************************************************************************************

// Mission start stuff

SCRIPT_NAME bcesar4

GOSUB mission_start_bc4

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_bc4_failed
ENDIF

GOSUB mission_cleanup_bc4

MISSION_END							  


// ***************************************Mission Start*************************************
{
mission_start_bc4:

flag_player_on_mission = 1

LVAR_INT bc4_players_car
	  
mission_bc4_loop:

WAIT 0
    
	// race 1*****************************************************************
	IF flag_bcesar_mission_counter = 0	//START

		LOAD_MISSION_TEXT BCESAR4

		IF IS_CHAR_IN_ANY_CAR scplayer
			GET_CAR_CHAR_IS_USING scplayer bc4_players_car
			SET_CAR_AS_MISSION_CAR bc4_players_car
			GET_CAR_COORDINATES bc4_players_car x y z
			z += 1000.0
			SET_CAR_COORDINATES bc4_players_car x y z
			FREEZE_CAR_POSITION bc4_players_car TRUE
		ELSE
			bc4_players_car = -1
		ENDIF

		CLEAR_AREA 1557.1616 24.3050 22.7313 100.0 TRUE
		MAKE_PLAYER_SAFE_FOR_CUTSCENE Player1
		SWITCH_STREAMING OFF
		LOAD_CUTSCENE BCESAR4
		WHILE NOT HAS_CUTSCENE_LOADED
			WAIT 0
		ENDWHILE
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
		flag_stage_of_bcesar_race = 1
		start_the_bcesar_race = 1

		IF NOT bc4_players_car = -1
			IF NOT IS_CAR_DEAD bc4_players_car

				FREEZE_CAR_POSITION bc4_players_car FALSE
				z -= 1000.0
				SET_CAR_COORDINATES bc4_players_car x y z
			ENDIF
		ENDIF

		GOTO mission_bc4_passed
	ENDIF

	IF flag_bcesar_mission_counter = 2 // player failed Wu Zi Mu race
		IF IS_CHAR_IN_AREA_3D scplayer -782.3699 -147.9480 19.5518 -405.0757 -237.5195 91.1306 FALSE
			flag_player_on_mission = 1
			DO_FADE 1000 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			REQUEST_MODEL STALLION 
			REQUEST_MODEL SABRE 
			REQUEST_MODEL FORTUNE 
			REQUEST_MODEL WMYST 
			REQUEST_MODEL WMYBMX
			REQUEST_MODEL SPECIAL01
			REQUEST_ANIMATION KISSING
 
			CLEAR_AREA -518.0490 -189.3775 79.3468 100.0 FALSE			
			SET_PLAYER_CONTROL player1 OFF
			SWITCH_WIDESCREEN ON
			SET_FIXED_CAMERA_POSITION -535.8186 -198.0116 80.8647 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -535.5711 -197.1029 80.5285 JUMP_CUT

			IF NOT IS_CHAR_DEAD scplayer
				IF IS_CHAR_IN_ANY_CAR scplayer
					GET_CAR_CHAR_IS_USING scplayer car
					IF car > 0
						IF NOT IS_CAR_DEAD car
							SET_CAR_VISIBLE car FALSE
							FREEZE_CAR_POSITION car TRUE
							SET_CAR_COLLISION car FALSE
							SET_CHAR_VISIBLE scplayer FALSE
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			WAIT 1000
			WHILE NOT HAS_MODEL_LOADED STALLION
			AND NOT HAS_MODEL_LOADED SABRE
			AND NOT HAS_MODEL_LOADED FORTUNE
			AND NOT HAS_MODEL_LOADED WMYST 
			AND NOT HAS_MODEL_LOADED WMYBMX
			AND NOT HAS_MODEL_LOADED SPECIAL01
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_ANIMATION_LOADED KISSING
				WAIT 0
			ENDWHILE

			LVAR_INT a_car1 a_car2 a_car3 a_char1 a_char2 a_char3 a_seq
			CREATE_CAR STALLION -536.7188 -192.5453 77.404 a_car1
			CREATE_CHAR PEDTYPE_MISSION1 WMYST -530.4067 -188.8082 77.3986 a_char1
			
			SET_CAR_HEADING a_car1 287.0
			OPEN_SEQUENCE_TASK a_seq
				TASK_PLAY_ANIM -1 GIFT_GIVE KISSING 1000.0 FALSE FALSE FALSE FALSE -2
				TASK_ENTER_CAR_AS_DRIVER -1 a_car1 -2
				TASK_CAR_DRIVE_TO_COORD -1 a_car1 -507.5502 -99.2875 61.825 10.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
			CLOSE_SEQUENCE_TASK a_seq
			PERFORM_SEQUENCE_TASK a_char1 a_Seq
			CLEAR_SEQUENCE_TASK a_seq

			CREATE_CAR SABRE -536.1580 -185.2865 77.398 a_car2
			CREATE_CHAR PEDTYPE_MISSION1 WMYBMX -530.4067 -187.8082 77.3986 a_char2
			SET_CHAR_HEADING a_char2 180.0
			SET_CAR_HEADING a_car2 243.0
			OPEN_SEQUENCE_TASK a_seq
				TASK_PLAY_ANIM -1 GIFT_GET KISSING 1000.0 FALSE FALSE FALSE FALSE -2
				TASK_ENTER_CAR_AS_DRIVER -1 a_car2 -2
				TASK_CAR_DRIVE_TO_COORD -1 a_car2 -507.5502 -99.2875 61.825 10.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
			CLOSE_SEQUENCE_TASK a_seq
			PERFORM_SEQUENCE_TASK a_char2 a_Seq
			CLEAR_SEQUENCE_TASK a_seq

			CREATE_CAR FORTUNE -524.9425 -184.2080 77.2766 a_car3
			CREATE_CHAR_INSIDE_CAR a_car3 PEDTYPE_MISSION1 SPECIAL01 a_char3
			SET_CAR_HEADING a_car3 239.0

			OPEN_SEQUENCE_TASK a_seq
				TASK_PAUSE -1 7000
				TASK_CAR_DRIVE_TO_COORD -1 a_car3 -507.5502 -99.2875 61.825 10.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
			CLOSE_SEQUENCE_TASK a_seq
			PERFORM_SEQUENCE_TASK a_char3 a_Seq
			CLEAR_SEQUENCE_TASK a_seq
	
			DO_FADE 800 FADE_IN
			SKIP_CUTSCENE_START
			WAIT 10000
			DO_FADE 1000 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			SKIP_CUTSCENE_END
			IF NOT IS_CAR_DEAD a_car1
				DELETE_CAR a_car1
			ENDIF
			IF NOT IS_CAR_DEAD a_car2
				DELETE_CAR a_car2
			ENDIF
			IF NOT IS_CAR_DEAD a_car3
				DELETE_CAR a_car3
			ENDIF

			IF NOT IS_CHAR_DEAD a_char1
				DELETE_CHAR a_char1
			ENDIF
			IF NOT IS_CHAR_DEAD a_char2
				DELETE_CHAR a_char2
			ENDIF
			IF NOT IS_CHAR_DEAD a_char3
				DELETE_CHAR a_char3
			ENDIF

			MARK_MODEL_AS_NO_LONGER_NEEDED STALLION
			MARK_MODEL_AS_NO_LONGER_NEEDED SABRE
			MARK_MODEL_AS_NO_LONGER_NEEDED FORTUNE
			MARK_MODEL_AS_NO_LONGER_NEEDED WMYST 
			MARK_MODEL_AS_NO_LONGER_NEEDED WMYBMX
			MARK_MODEL_AS_NO_LONGER_NEEDED SPECIAL01
			REMOVE_ANIMATION KISSING
			IF NOT IS_CHAR_DEAD scplayer
				IF IS_CHAR_IN_ANY_CAR scplayer
					GET_CAR_CHAR_IS_USING scplayer car
					IF car > 0
						IF NOT IS_CAR_DEAD car
							SET_CAR_VISIBLE car TRUE
							FREEZE_CAR_POSITION car FALSE
							SET_CAR_COLLISION car TRUE
							SET_CHAR_VISIBLE scplayer TRUE
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL player1 ON
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			DO_FADE 800 FADE_IN			
		ENDIF
		flag_bcesar_mission_counter = -1
		flag_player_on_mission = 0
		bc4_players_car = 0
		GOTO mission_bc4_passed
	ENDIF

	IF flag_bcesar_mission_counter = 4 //END
		LOAD_MISSION_TEXT BCESAR4
		// Race 13 cut scene end

		IF IS_CHAR_IN_ANY_CAR scplayer
			GET_CAR_CHAR_IS_USING scplayer bc4_players_car
			FREEZE_CAR_POSITION bc4_players_car TRUE
			SET_CAR_VISIBLE bc4_players_Car FALSE
			SET_CAR_COLLISION bc4_players_car FALSE
		ELSE
			bc4_players_car = -1
		ENDIF

	    CLEAR_AREA -526.7778 -189.0571 76.9917 100.0 TRUE
	    MAKE_PLAYER_SAFE_FOR_CUTSCENE Player1
		SWITCH_STREAMING OFF
	    LOAD_CUTSCENE BCESA4W
	    WHILE NOT HAS_CUTSCENE_LOADED
	    	WAIT 0
	    ENDWHILE
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
		IF NOT bc4_players_car = -1
			IF NOT IS_CAR_DEAD bc4_players_car			
				FREEZE_CAR_POSITION bc4_players_car FALSE
				SET_CAR_VISIBLE bc4_players_car TRUE
				SET_CAR_COLLISION bc4_players_car TRUE
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
			ENDIF
		ELSE
			SET_CHAR_COORDINATES scplayer -503.3945 -186.0599 76.6103
			SET_CHAR_HEADING scplayer 53.0
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
		ENDIF
		DO_FADE 1000 FADE_IN
		REMOVE_BLIP bcesar_contact_blip
		REGISTER_MISSION_PASSED ( BCESAR4 )
		PRINT_WITH_NUMBER_BIG ( M_PASSD ) 3 5000 1 //"Mission Passed!"

		flag_stage_of_bcesar_race = 0
		start_the_bcesar_race = 0
		GOTO mission_bc4_passed
	ENDIF

	// race 2*****************************************************************
	IF flag_bcesar_mission_counter = 5 //START

		LOAD_MISSION_TEXT BCESAR4
		CLEAR_AREA -526.7778 -189.0571 76.9917 100.0 TRUE
		SET_CHAR_COORDINATES scplayer -495.9034 -196.2218 77.3986 
		MAKE_PLAYER_SAFE_FOR_CUTSCENE Player1
		SWITCH_STREAMING OFF								   
		LOAD_CUTSCENE BCESAR5
		WHILE NOT HAS_CUTSCENE_LOADED
			WAIT 0
		ENDWHILE
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
		//REMOVE_BLIP catalina_contact_blip[0]
		start_the_bcesar_race = 1
		GOTO mission_bc4_passed
	ENDIF

	IF flag_bcesar_mission_counter = 9 //END
		LOAD_MISSION_TEXT BCESAR4

		IF IS_CHAR_IN_ANY_CAR scplayer
			GET_CAR_CHAR_IS_USING scplayer bc4_players_car
			SET_CAR_AS_MISSION_CAR bc4_players_car
			GET_CAR_COORDINATES bc4_players_car x y z
			z += 1000.0
			SET_CAR_COORDINATES bc4_players_car x y z
			FREEZE_CAR_POSITION bc4_players_car TRUE
		ELSE
			bc4_players_car = -1
		ENDIF

		// Race 14 cut scene end
		CLEAR_AREA 1557.1616 24.3050 22.7313 100.0 TRUE
		MAKE_PLAYER_SAFE_FOR_CUTSCENE Player1
		SWITCH_STREAMING OFF
		LOAD_CUTSCENE BCESA5W
		WHILE NOT HAS_CUTSCENE_LOADED
			WAIT 0
		ENDWHILE
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

		IF NOT bc4_players_car = -1
			IF NOT IS_CAR_DEAD bc4_players_car			
				FREEZE_CAR_POSITION bc4_players_car FALSE
				SET_CAR_VISIBLE bc4_players_car TRUE
				SET_CAR_COLLISION bc4_players_car TRUE
				SET_CAR_COORDINATES bc4_players_car 1557.6938 17.4472 23.1641
				SET_CAR_HEADING bc4_players_car 9.0
				WARP_CHAR_INTO_CAR scplayer bc4_players_car
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
			ENDIF
		ELSE
			SET_CHAR_COORDINATES scplayer 1559.7679 14.4176 23.1641
			SET_CHAR_HEADING scplayer 10.0
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
		ENDIF
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		DO_FADE 1000 FADE_IN
		REMOVE_BLIP bcesar_contact_blip
		REGISTER_MISSION_PASSED ( BCES4_2 )
		PRINT_WITH_NUMBER_BIG ( M_PASSD ) 3 5000 1 //"Mission Passed!"
		start_the_bcesar_race = 0
		GOTO mission_bc4_passed
	ENDIF


GOTO mission_bc4_loop

		   
// Mission cprace failed

mission_bc4_failed:

RETURN
   

// mission cprace passed

mission_bc4_passed:

IF flag_bcesar_mission_counter < 5
	IF NOT IS_CAR_DEAD bc4_players_car
		WARP_CHAR_INTO_CAR scplayer bc4_players_car
		FREEZE_CAR_POSITION bc4_players_car FALSE
	ENDIF
ENDIF

RETURN

// mission cleanup

mission_cleanup_bc4:

flag_bcesar_mission_counter ++
GET_GAME_TIMER timer_mobile_start
flag_player_on_mission = 0	
MISSION_HAS_FINISHED

IF flag_bcesar_mission_counter = 1
	DO_FADE 0 FADE_OUT
ENDIF

IF flag_bcesar_mission_counter = 6
	DO_FADE 0 FADE_OUT
ENDIF

RETURN
}
