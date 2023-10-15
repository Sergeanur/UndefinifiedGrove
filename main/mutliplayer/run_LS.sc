MISSION_START

// *****************************************************************************************
// ********************************* 2-Player Run-Around ***********************************
// *****************************************************************************************
// ************************************* Paul Davis ****************************************
// *****************************************************************************************


SCRIPT_NAME run_ls

// Begin...
GOSUB mission_start_ls_run

	IF HAS_DEATHARREST_BEEN_EXECUTED
		GOSUB mission_ls_run_failed
	ENDIF

GOSUB mission_ls_run_cleanup

MISSION_END

{

// ****************************************Mission Start************************************
mission_start_ls_run:

HIDE_ALL_FRONTEND_BLIPS TRUE

//--- KISSING VARIABLES
LVAR_FLOAT fX[3] fY[3] fZ[3] fTemp[3] // 4 arrays of floats for co-ordinates etc.

LVAR_INT iTemp iTemp2 // 2 temp vars to store various int values

LVAR_INT iKissingStatus	iRadius

LVAR_INT ls_model_select

SET_FADING_COLOUR 0 0 0

flag_player_on_mission = 1

disable_all_cranes = 1 

REGISTER_MISSION_GIVEN

LOAD_MISSION_TEXT RUN_2P

GOSUB runls_fade_out

WAIT 0

// *****************************************************************************************
// *																					   *
// *                                	  Requests   				   					   *
// *																					   *
// *****************************************************************************************

REQUEST_MODEL BFYPRO
REQUEST_ANIMATION KISSING

WHILE NOT HAS_MODEL_LOADED BFYPRO
OR NOT HAS_ANIMATION_LOADED KISSING
	WAIT 0
ENDWHILE

TIMERA = 0

CLEAR_AREA 2069.9075 -1556.3783 12.4198 10.0 TRUE

IF NOT IS_CHAR_DEAD scplayer 

SET_CHAR_COORDINATES scplayer 2070.3369 -1557.6238 12.4203

SET_CHAR_HEADING scplayer 269.0526

CLEAR_AREA 2491.8950 -1669.1218 12.3594 10.0 TRUE

CREATE_PLAYER 1 2069.7400 -1555.6284 12.4214 player2

DO_WEAPON_STUFF_AT_START_OF_2P_GAME

GET_PLAYER_CHAR player2 p2

SET_PLAYER_MODEL player2 BFYPRO

SET_CHAR_HEADING p2 269.0526 

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_FLAT PEDTYPE_PLAYER2

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_GANG_GROVE PEDTYPE_PLAYER2

SET_PLAYER_PLAYER_TARGETTING FALSE

SET_CHAR_NEVER_TARGETTED scplayer TRUE

SET_CHAR_NEVER_TARGETTED p2 TRUE 

SET_TWO_PLAYER_CAMERA_MODE JUMP_CUT

LIMIT_TWO_PLAYER_DISTANCE 20.0

DISABLE_CHAR_SPEECH p2 TRUE

ENDIF
 
GOSUB runls_set_camera

	SET_FIXED_CAMERA_POSITION 2078.4504 -1556.6678 13.6446 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 2077.4514 -1556.6514 13.6027 JUMP_CUT

	GOSUB runls_fade_in

	PRINT_NOW ( RUN_02 ) 5000 1 // ~s~Free roam around the city, but don't let the cops catch you!

	WAIT 5000

	CLEAR_PRINTS

	SWITCH_WIDESCREEN OFF

	PRINT_HELP_FOREVER ( RUN_XX ) // Player 2 : Push the ~H~left analog stick~w~ left or right to select a character.~N~~X~ Select~N~

	WHILE NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD p2
		
		WAIT 0

		IF TIMERB > 250

			GET_POSITION_OF_ANALOGUE_STICKS PAD2 LStickX LStickY RStickX RStickY

			IF IS_BUTTON_PRESSED PAD2 LEFTSTICKX
			AND LStickX > 100

				ls_model_select ++
				  
				IF ls_model_select > 5

					ls_model_select = 0

				ENDIF

				GOSUB ls_select_model

				TIMERB = 0

			ENDIF	
					
			IF IS_BUTTON_PRESSED PAD2 LEFTSTICKX
			AND LStickX < -100

				ls_model_select --

				IF ls_model_select < 0

					ls_model_select = 5

				ENDIF

				GOSUB ls_select_model

				TIMERB = 0

			ENDIF

			IF IS_BUTTON_PRESSED PAD2 DPADRIGHT

				ls_model_select ++

				IF ls_model_select > 5

					ls_model_select = 0

				ENDIF

				GOSUB ls_select_model

				TIMERB = 0 

			ENDIF

			IF IS_BUTTON_PRESSED PAD2 DPADLEFT 

				ls_model_select --

				IF ls_model_select < 0

					ls_model_select = 5

				ENDIF

				GOSUB ls_select_model

				TIMERB = 0

			ENDIF

		ENDIF

		IF IS_JAPANESE_VERSION

			IF IS_BUTTON_PRESSED PAD2 CIRCLE 

				GOSUB runls_restore_camera

				CLEAR_HELP

				GOTO runls_main_loop

			ENDIF

		ELSE

			IF IS_BUTTON_PRESSED PAD2 CROSS 

				GOSUB runls_restore_camera

				CLEAR_HELP

				GOTO runls_main_loop

			ENDIF

		ENDIF

	ENDWHILE

	runls_main_loop:

	PRINT_HELP ( RUN_06 ) // ~s~You can kiss the other player by standing near them and pressing the ~h~L1 ~w~button.

	WHILE NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD p2

		WAIT 0

		GOSUB runls_keys

		IF NOT IS_CHAR_DEAD scplayer
		AND NOT IS_CHAR_DEAD p2

		   	IF NOT IS_CHAR_IN_ANY_CAR scplayer

				IF NOT HAS_CHAR_BEEN_DAMAGED_BY_WEAPON scplayer WEAPONTYPE_SPRAYCAN
				AND NOT HAS_CHAR_BEEN_DAMAGED_BY_WEAPON p2 WEAPONTYPE_SPRAYCAN

					GOSUB runls_KissCheck

				ELSE
					
					CLEAR_CHAR_LAST_WEAPON_DAMAGE scplayer

					CLEAR_CHAR_LAST_WEAPON_DAMAGE p2

				ENDIF

			ENDIF

		ENDIF

	ENDWHILE

	GOTO mission_ls_run_failed

mission_ls_run_failed:

	IF IS_CHAR_DEAD scplayer

		PRINT_NOW ( RUN_03 ) 4000 1 //	~r~Player one has been killed

	ENDIF

	IF IS_CHAR_DEAD p2

		PRINT_NOW ( RUN_04 ) 4000 1 //	~r~Player two has been killed

	ENDIF

	IF IS_CHAR_DEAD p2
	AND IS_CHAR_DEAD scplayer

		PRINT_NOW ( RUN_05 ) 4000 1 //	~r~Both players were killed

	ENDIF

	PRINT_BIG ( M_FAIL ) 5000 1

RETURN
   
mission_ls_run_passed:

//flag_twoplay_mission1_passed = 1

	CLEAR_WANTED_LEVEL player1

RETURN		

// mission cleanup
mission_ls_run_cleanup:

	disable_all_cranes = 0

	HIDE_ALL_FRONTEND_BLIPS FALSE

	MARK_MODEL_AS_NO_LONGER_NEEDED BFYPRO
	MARK_MODEL_AS_NO_LONGER_NEEDED FAM2
	MARK_MODEL_AS_NO_LONGER_NEEDED WMOPREA
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYBELL
	MARK_MODEL_AS_NO_LONGER_NEEDED LAPD1
	MARK_MODEL_AS_NO_LONGER_NEEDED FAM1
	MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1

	REMOVE_ANIMATION KISSING

	flag_player_on_mission = 0

	CLEAR_WANTED_LEVEL player1

	MISSION_HAS_FINISHED

RETURN

runls_set_camera:

	SWITCH_WIDESCREEN ON

	IF IS_PLAYER_PLAYING player1
	AND IS_PLAYER_PLAYING player2

		SET_PLAYER_CONTROL player1 OFF
		SET_PLAYER_CONTROL player2 OFF

	ENDIF

RETURN

runls_restore_camera:

	SET_CAMERA_IN_FRONT_OF_PLAYER
	SWITCH_WIDESCREEN OFF
	
	IF IS_PLAYER_PLAYING player1
	AND IS_PLAYER_PLAYING player2

		SET_PLAYER_CONTROL player1 ON
		SET_PLAYER_CONTROL player2 ON
	
	ENDIF

	RESTORE_CAMERA_JUMPCUT
 
RETURN

runls_fade_out:
	SET_FADING_COLOUR 0 0 0
	DO_FADE 2000 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

runls_fade_in:
	SET_FADING_COLOUR 0 0 0
	DO_FADE 2000 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

ls_select_model:

	SWITCH ls_model_select

		CASE 0

			REQUEST_MODEL BFYPRO

			WHILE NOT HAS_MODEL_LOADED BFYPRO
				WAIT 0
			ENDWHILE

			SET_PLAYER_MODEL player2 BFYPRO
	
		BREAK

		CASE 1

			REQUEST_MODEL FAM2

			WHILE NOT HAS_MODEL_LOADED FAM2
				WAIT 0
			ENDWHILE

			SET_PLAYER_MODEL player2 FAM2
	
		BREAK

		CASE 2

			REQUEST_MODEL WMOPREA

			WHILE NOT HAS_MODEL_LOADED WMOPREA
				WAIT 0
			ENDWHILE

			SET_PLAYER_MODEL player2 WMOPREA
	
		BREAK

		CASE 3

			REQUEST_MODEL BALLAS1

			WHILE NOT HAS_MODEL_LOADED BALLAS1
				WAIT 0
			ENDWHILE

			SET_PLAYER_MODEL player2 BALLAS1
	
		BREAK

		CASE 4

			REQUEST_MODEL LAPD1

			WHILE NOT HAS_MODEL_LOADED LAPD1
				WAIT 0
			ENDWHILE

			SET_PLAYER_MODEL player2 LAPD1
	
		BREAK

		CASE 5

			REQUEST_MODEL FAM1

			WHILE NOT HAS_MODEL_LOADED FAM1
				WAIT 0
			ENDWHILE

			SET_PLAYER_MODEL player2 FAM1
	
		BREAK

	ENDSWITCH

RETURN

runls_keys:

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_A

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
		
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D
		

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_F

		IF NOT IS_CHAR_DEAD p2

			TASK_DIE p2

		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_B

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_K

		IF NOT IS_CHAR_DEAD scplayer

			TASK_DIE scplayer

		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_H

		IF NOT IS_CHAR_DEAD scplayer

			ADD_ARMOUR_TO_CHAR scplayer 100
			INCREASE_PLAYER_MAX_HEALTH player1 100

		ENDIF

	ENDIF

RETURN

/********************************************
		STATE 6: ON FOOT KISSING
********************************************/

/*******************************************
              KISS CHECK
********************************************/
//--- Usage: Call this sub-routine every loop cycle. 
//    It performs a series of spatial checks and also check for L1 to be depressed.
//    If help text is needed, it can be prented here (I have provided a lablel, commented)

runls_KissCheck:
  
    IF NOT IS_CHAR_DEAD scplayer  
	AND NOT IS_CHAR_DEAD p2

	    IF iKissingStatus = 0

		    IF NOT IS_CHAR_IN_ANY_CAR scplayer
	        AND NOT IS_CHAR_IN_ANY_CAR p2

	            IF IS_CHAR_STOPPED p2
	             
	                GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS p2 0.0 0.5 0.0 fX[0] fY[0] fZ[0]

	                IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer fX[0] fY[0] fZ[0] 1.2 1.2 1.0 FALSE

						IF iRadius = 0

                            PRINT_HELP_FOREVER ( GF_0021 ) 

							iRadius = 1

						ENDIF

	                    GET_CHAR_COORDINATES scplayer fX[0] fY[0] fZ[0]
	                    GET_CHAR_COORDINATES p2 	  fX[1] fY[1] fZ[1]

	                    fTemp[0] = fZ[1] - 0.02
	                    fTemp[1] = fZ[1] + 0.02

	                    //--- Check if player and girl are on the same level 
	                    IF fZ[0] > fTemp[0] 
	                    AND fZ[0] < fTemp[1]  
            
	                        // Kiss now
	                        IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
							OR IS_BUTTON_PRESSED PAD2 LEFTSHOULDER1

	                            iKissingStatus = 1

	                            GOSUB runls_Kissing 
	                                                    
	                        ENDIF

	                    ENDIF

					ELSE

						IF iRadius = 1

                            CLEAR_HELP

							iRadius = 0

						ENDIF

	                ENDIF

	            ENDIF

	        ENDIF

	    ELSE

	    	GOSUB runls_Kissing

	    ENDIF

	ENDIF

RETURN

/********************************************
                  KISSING
********************************************/
//--- Usage: This minute state-machine is called by KissCheck every loop cycle if a kiss has been triggered. 
//    Its main purpose is play the kiss animation. It will reset itself by setting iKissingStatus = 0.
                                            
runls_Kissing:  
	
    SWITCH iKissingStatus                                                 
                                         
	CASE 1

		IF NOT IS_CHAR_DEAD scplayer
		AND NOT IS_CHAR_DEAD p2

			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			CLEAR_CHAR_TASKS_IMMEDIATELY p2

	        CLEAR_HELP
	        SET_PLAYER_CONTROL player1 OFF
	        HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
	        HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE p2 TRUE
	        SET_EVERYONE_IGNORE_PLAYER player1 TRUE
	        SWITCH_WIDESCREEN ON
	        SET_ALL_CARS_CAN_BE_DAMAGED FALSE

	        //--- Switch Roads OFF
	        GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 30.0 30.0 2.0 fX[0] fY[0] fZ[0]
	        GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -30.0 -30.0 -1.0 fX[1] fY[1] fZ[1]
	        SWITCH_ROADS_OFF fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1]
	        SWITCH_PED_ROADS_OFF fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1]

	        //--- Rotate player and GF to face each other                    
	        TASK_TURN_CHAR_TO_FACE_CHAR scplayer p2
	        TASK_TURN_CHAR_TO_FACE_CHAR p2 scplayer

	        ++iKissingStatus

		ENDIF

	BREAK
    
    CASE 2

		IF NOT IS_CHAR_DEAD scplayer
		AND NOT IS_CHAR_DEAD p2

	        GET_SCRIPT_TASK_STATUS scplayer TASK_TURN_CHAR_TO_FACE_CHAR iTemp
	        GET_SCRIPT_TASK_STATUS p2 TASK_TURN_CHAR_TO_FACE_CHAR iTemp2
	                                            
	        IF iTemp = FINISHED_TASK
	        AND iTemp2 = FINISHED_TASK
	            //--- Switch collisions OFF
	            SET_CHAR_COLLISION scplayer FALSE 
	            SET_CHAR_COLLISION p2 FALSE
	            //--- Update Player and GF co-ords to match anim                                    
	            GET_CHAR_HEADING scplayer fTemp[0]
	            fTemp[0] += 180.0
	            SET_CHAR_HEADING p2 fTemp[0]  
	            GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 1.0 0.0 fX[1] fY[1] fZ[1]
	            GET_GROUND_Z_FOR_3D_COORD fX[1] fY[1] fZ[1] fZ[1] 
	            //---Store the current girl co-ords
	            GET_CHAR_COORDINATES p2 fX[2] fY[2] fZ[2] 
	            GET_GROUND_Z_FOR_3D_COORD fX[2] fY[2] fZ[2] fZ[2]
	            //--- Now move the girl in the right spot
	            SET_CHAR_COORDINATES p2 fX[1] fY[1] fZ[1] 
	             
	            //--- Camera Cut
	            GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 1.2 -1.0 0.5 fX[0] fY[0] fZ[0]
	            GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 0.7 0.5 fX[1] fY[1] fZ[1]

	            IF IS_LINE_OF_SIGHT_CLEAR fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1] TRUE TRUE FALSE TRUE FALSE
	                SET_FIXED_CAMERA_POSITION fX[0] fY[0] fZ[0] 0.0 0.0 0.0
	                POINT_CAMERA_AT_POINT fX[1] fY[1] fZ[1] JUMP_CUT
	            ENDIF

				TASK_STAND_STILL scplayer -1
				TASK_STAND_STILL p2 -1

	            ++iKissingStatus                                   
	                                             
	        ELSE

	            IF iTemp = FINISHED_TASK                               
	                GET_SCRIPT_TASK_STATUS scplayer TASK_STAND_STILL iTemp                                 
	                IF NOT iTemp = PERFORMING_TASK
	                AND NOT iTemp = WAITING_TO_START_TASK
	                    TASK_STAND_STILL scplayer -1
	                ENDIF
	            ENDIF
	                                    
	            IF iTemp2 = FINISHED_TASK 
	                GET_SCRIPT_TASK_STATUS p2 TASK_STAND_STILL iTemp2                   
	                IF NOT iTemp2 = PERFORMING_TASK
	                AND NOT iTemp2 = WAITING_TO_START_TASK
	                    TASK_STAND_STILL p2 -1
	                    GET_CHAR_HEADING scplayer fTemp[0]
	                    fTemp[0] += 180.0
	                    SET_CHAR_HEADING p2 fTemp[0]  
	                ENDIF 
	            ENDIF

	        ENDIF

		ENDIF

    BREAK

    CASE 3

		IF NOT IS_CHAR_DEAD scplayer
		AND NOT IS_CHAR_DEAD p2

	        //--- Switch collisions back ON

	        SET_CHAR_COLLISION scplayer TRUE 
	        SET_CHAR_COLLISION p2 TRUE

	        //--- Play anim
	        TASK_PLAY_ANIM scplayer PLAYA_KISS_02 KISSING 4.0 FALSE FALSE FALSE FALSE 0
	        TASK_PLAY_ANIM p2 GRLFRD_KISS_02 KISSING 4.0 FALSE FALSE FALSE FALSE 0

	        ++iKissingStatus 

		ENDIF

    BREAK

    CASE 4

		IF NOT IS_CHAR_DEAD scplayer
		AND NOT IS_CHAR_DEAD p2

	        //--- Now conclude the cut-scene
	        fTemp[0] = -1.0

	        IF IS_CHAR_PLAYING_ANIM scplayer PLAYA_KISS_02

	            GET_CHAR_ANIM_CURRENT_TIME scplayer PLAYA_KISS_02 fTemp[0]

	        ENDIF

	        IF fTemp[0] > -1.0 // An anim is playing

	            IF fTemp[0] = 1.0 // The animation is finished

	                HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
	                HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE p2 FALSE
	                //--- Restore the girl's co-ords
	                SET_CHAR_COORDINATES p2 fX[2] fY[2] fZ[2]
	                //--- Switch Roads ON
	                GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 30.0 30.0 1.0 fX[0] fY[0] fZ[0]
	                GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -30.0 -30.0 -1.0 fX[1] fY[1] fZ[1]
	                SWITCH_ROADS_BACK_TO_ORIGINAL fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1]
	                SWITCH_PED_ROADS_BACK_TO_ORIGINAL fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1]
	                //--- Restore collisions etc.
	                SET_CHAR_COLLISION scplayer TRUE 
	                SET_CHAR_COLLISION p2 TRUE
	                SET_PLAYER_CONTROL player1 ON
	                SET_EVERYONE_IGNORE_PLAYER player1 FALSE
	                SWITCH_WIDESCREEN OFF
	                SET_ALL_CARS_CAN_BE_DAMAGED TRUE
	                SET_CAMERA_BEHIND_PLAYER
	                RESTORE_CAMERA_JUMPCUT
	                //--- All done
	                iKissingStatus = 0

	            ENDIF

	        ELSE 

	            CLEAR_CHAR_TASKS scplayer
	            TASK_PAUSE p2 0
	            HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
	            HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE p2 FALSE
	            //--- Restore the girl's co-ords                              
	            SET_CHAR_COORDINATES p2 fX[2] fY[2] fZ[2]
	            //--- Switch Roads ON
	            GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 30.0 30.0 1.0 fX[0] fY[0] fZ[0]
	            GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -30.0 -30.0 -1.0 fX[1] fY[1] fZ[1]
	            SWITCH_ROADS_BACK_TO_ORIGINAL fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1]
	            SWITCH_PED_ROADS_BACK_TO_ORIGINAL fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1]
	            //--- Restore collisions etc.
	            SET_CHAR_COLLISION scplayer TRUE 
	            SET_CHAR_COLLISION p2 TRUE
	            SET_PLAYER_CONTROL player1 ON
	            SET_EVERYONE_IGNORE_PLAYER player1 FALSE
	            SWITCH_WIDESCREEN OFF
	            SET_ALL_CARS_CAN_BE_DAMAGED TRUE
	            SET_CAMERA_BEHIND_PLAYER
	            RESTORE_CAMERA_JUMPCUT
	            //--- All done
	            iKissingStatus = 0

	        ENDIF

		ENDIF

    BREAK

    ENDSWITCH

RETURN

}
		
/*

{--------------------------------------RUN_2P---------------------------------------------}

[RUN_00:RUN_2P]
~s~

[RUN_01:RUN_2P]
2p Run Around 

[RUN_02:RUN_2P]
~s~Free roam around the city, but don't let the cops catch you!

[RUN_03:RUN_2P]
~s~Ped Limit

[RUN_04:RUN_2P]
~s~Time Limit

[RUN_05:RUN_2P] 
~s~Los Angelas

[RUN_06:RUN_2P]
~s~Los Vegas

[RUN_07:RUN_2P]
~s~Mountains

[RUN_08:RUN_2P]
~s~Desert

[RUN_09:RUN_2P]
~s~~1~ Mins

[RUN_10:RUN_2P]
~s~~1~ Peds

[RUN_11:RUN_2P]
~s~Press ~x~ to progress

[RUN_12:RUN_2P]
Select a Weapon   

[RUN_13:RUN_2P]
Shoot ~1~ Peds in ~1~ minutes

*/

