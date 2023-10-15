MISSION_START
// *****************************************************************************************
// *********************************** Strap 2	******************************************** 
// ********************************* Steal Rhyme Book 2  ***********************************
// *****************************************************************************************
// * Player has to steal rhyme book from Dr G's mansion.  This mission teaches the player  *
// * about stealth 
// *****************************************************************************************


// Mission start stuff
{
SCRIPT_NAME music2

GOSUB mission_start_music2

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_music2_failed
ENDIF

GOSUB mission_cleanup_music2

MISSION_END
 
// **************************************** Variables for mission ****************************


//mansion
LVAR_INT mansioninterior_s2
LVAR_INT drgcar1_s2
LVAR_INT drgcar2_s2
LVAR_INT guard1_s2
LVAR_INT guard2_s2
LVAR_INT guard3_s2
LVAR_INT guard4_s2
LVAR_INT guard5_s2
LVAR_INT guard6_s2
LVAR_INT guard7_s2
LVAR_INT guard8_s2
LVAR_INT strap2_DM //decision makers
LVAR_INT strap2empty_DM //decision makers
LVAR_INT drink_s2
LVAR_INT enemy_s2
LVAR_INT drinkvend_s2seq
LVAR_INT console_s2seq
LVAR_INT guard4_s2seq
LVAR_INT ogloc_s2
LVAR_INT mtbike_s2
//sequences
LVAR_INT guard2_s2seq
LVAR_INT guard7_s2seq
LVAR_INT guard1_s2seq
LVAR_INT sequencetask_s2
LVAR_INT guard3_s2seq
//pickups
LVAR_INT rhymebook_s2
LVAR_INT pickupgun_s2
//blips
LVAR_INT rhymebook_s2blip
LVAR_INT guard1_s2blip
LVAR_INT guard2_s2blip
LVAR_INT guard3_s2blip
LVAR_INT guard4_s2blip
LVAR_INT guard5_s2blip
LVAR_INT guard6_s2blip
LVAR_INT guard7_s2blip
LVAR_INT guard8_s2blip

//flags
LVAR_INT rhymebook_s2flag
LVAR_INT helptext_s2flag
LVAR_INT guard2_s2flag
LVAR_INT guard3_s2flag
LVAR_INT guard4_s2flag
LVAR_INT guard5_s2flag
LVAR_INT guard6_s2flag
LVAR_INT guard7_s2flag
LVAR_INT guard8_s2flag
LVAR_INT hudflash_s2flag
LVAR_INT guard1text_s2flag
LVAR_INT guard2text_s2flag
LVAR_INT guard3text_s2flag
LVAR_INT guard4text_s2flag
LVAR_INT guard5text_s2flag
LVAR_INT pickupcollect_s2flag
LVAR_INT playerseenstate_s2
LVAR_INT guard1playerseenstate_s2
LVAR_INT guard2playerseenstate_s2
LVAR_INT guard3playerseenstate_s2
LVAR_INT guard4playerseenstate_s2
LVAR_INT guard5playerseenstate_s2
LVAR_INT guard6playerseenstate_s2
LVAR_INT guard7playerseenstate_s2
LVAR_INT bararea_s2flag
LVAR_INT missionaudio_s2flag
LVAR_INT playerducking_s2flag
VAR_INT tripskip_s2flag //do not reset
LVAR_INT mountainbike_s2flag
/////////////////////////////////////////////	CUTSCENE END


// **************************************** Mission Start ************************************

mission_start_music2:

flag_player_on_mission = 1

REGISTER_MISSION_GIVEN

LOAD_MISSION_TEXT STRAP2  

/////////////////////////////////////////////	CUTSCENE START
SET_FADING_COLOUR 0 0 0

SET_AREA_VISIBLE 10

DO_FADE 2000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE 

LOAD_CUTSCENE STRAP2A
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE

CLEAR_CUTSCENE

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

SET_AREA_VISIBLE 0



SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL PLAYER1 ON
RESTORE_CAMERA_JUMPCUT


REQUEST_MODEL HUNTLEY
REQUEST_MODEL STAFFORD
REQUEST_MODEL KMB_RHYMESBOOK
REQUEST_MODEL CJ_JUICE_CAN
REQUEST_MODEL SILENCED
REQUEST_MODEL MTBIKE

CLEAR_MISSION_AUDIO 1
CLEAR_MISSION_AUDIO 2
LOAD_MISSION_AUDIO 1 SOUND_LOC2_BA
LOAD_MISSION_AUDIO 2 SOUND_LOC2_BB

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED HUNTLEY
	OR NOT HAS_MODEL_LOADED STAFFORD
	OR NOT HAS_MISSION_AUDIO_LOADED 1
	OR NOT HAS_MISSION_AUDIO_LOADED 2
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED KMB_RHYMESBOOK
	OR NOT HAS_MODEL_LOADED CJ_JUICE_CAN
	OR NOT HAS_MODEL_LOADED SILENCED
	OR NOT HAS_MODEL_LOADED MTBIKE
	WAIT 0
ENDWHILE


ADD_BLIP_FOR_COORD 1239.032 -741.106 94.76 rhymebook_s2blip  //1298.72 -796.12 84.07
SET_CHAR_COORDINATES scplayer 790.0 -1626.0 12.5
LOAD_SCENE 790.0 -1626.0 12.5
SET_CAMERA_BEHIND_PLAYER
SET_CHAR_HEADING scplayer 356.0
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_STEAL strap2_DM
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY strap2empty_DM
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1 PEDTYPE_MISSION1
SWITCH_ENTRY_EXIT MDDOGS TRUE
SET_PLAYER_GROUP_RECRUITMENT PLAYER1 FALSE
SET_SCRIPT_LIMIT_TO_GANG_SIZE 0
CLEAR_AREA 790.54 -1627.91 2.0 2.0 FALSE
DO_FADE 1500 FADE_IN

// ****************************** Declare Variables Values ***********************************


//flags
rhymebook_s2flag = 0
helptext_s2flag = 0
guard2_s2flag = 0
guard3_s2flag = 0
guard4_s2flag = 0
guard5_s2flag = 0
guard6_s2flag = 0
guard7_s2flag = 0
guard8_s2flag = 0
hudflash_s2flag = 0
guard1text_s2flag = 0
guard2text_s2flag = 0
guard3text_s2flag = 0
guard4text_s2flag = 0
guard5text_s2flag = 0
pickupcollect_s2flag = 0
playerseenstate_s2 = 0
guard1playerseenstate_s2 = 0
guard2playerseenstate_s2 = 0
guard3playerseenstate_s2 = 0
guard4playerseenstate_s2 = 0
guard5playerseenstate_s2 = 0
guard6playerseenstate_s2 = 0
guard7playerseenstate_s2 = 0
bararea_s2flag = 0
missionaudio_s2flag = 0 
playerducking_s2flag = 0
mountainbike_s2flag = 0
// ************************************** Main mission ******************************************


//
//CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE crash3_DM EVENT_DRAGGED_OUT_CAR
//ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE crash3_DM EVENT_DRAGGED_OUT_CAR TASK_COMPLEX_KILL_PED_ON_FOOT 50.0 50.0 50.0 50.0 1 1
//																 //friendProb threatProb playerProb undefinedProb inCar onFoot

PRINT_NOW STP2_1 10000 1 //~s~Go to Mad Dogg's mansion. 

////////////////////////////////////////////////////////////////////////////////////////////////////

IF tripskip_s2flag > 0
	SET_UP_SKIP	1242.659 -748.503 94.33 191.586
ENDIF

stealrhymebook_loop:

WAIT 0

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
     GOTO mission_music2_passed
ENDIF


IF rhymebook_s2flag = 0
	IF IS_CHAR_IN_ANGLED_AREA_2D scplayer 1222.799 -735.563 1348.407 -759.322 -100.0 FALSE
		IF tripskip_s2flag = 0
			tripskip_s2flag = 1
		ENDIF
		CLEAR_SKIP
		REMOVE_BLIP rhymebook_s2blip		 		
		ADD_BLIP_FOR_COORD 1298.66 -795.58 83.84 rhymebook_s2blip
		PRINT_NOW STP2_2 5000 1//~s~The main door is around the back of the mansion, you should be able to get inside undetected as most of Mad Dogg's entourage are at rehearsals for an award ceremony.
		
		CREATE_CAR HUNTLEY 1243.154 -805.76 83.188 drgcar1_s2
		SET_CAR_HEADING drgcar1_s2 1.533

		CREATE_CAR STAFFORD 1255.024 -804.587 83.934 drgcar2_s2
		SET_CAR_HEADING drgcar2_s2 182.9

		rhymebook_s2flag = 1
	ENDIF
ENDIF

//player is insdie mansion
IF rhymebook_s2flag = 1
	GET_CHAR_AREA_VISIBLE scplayer mansioninterior_s2
	IF mansioninterior_s2 = 5
		SET_WANTED_MULTIPLIER 0.0
		SWITCH_WIDESCREEN ON
		SET_FIXED_CAMERA_POSITION 1256.6234 -801.0125 1084.0914 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 1255.9296 -801.7316 1084.0444 JUMP_CUT

		//
		SET_CHAR_COORDINATES scplayer 1299.3522 -792.6416 1083.0078
		SET_CHAR_HEADING scplayer 0.0
		CLEAR_AREA 1299.3522 -792.6416 200.0 200.0 FALSE

		REQUEST_ANIMATION CRIB
		REQUEST_ANIMATION VENDING
		REQUEST_MODEL KNIFECUR
		REQUEST_MODEL WBDYG1
		REQUEST_MODEL WBDYG2
		REQUEST_MODEL MICRO_UZI
		
		LOAD_MISSION_AUDIO 3 SOUND_VIDEO_GAME_LOOP

		LOAD_ALL_MODELS_NOW
		LOAD_SCENE 1256.6234 -801.0125 1084.0914

		WHILE NOT HAS_ANIMATION_LOADED CRIB
			OR NOT HAS_ANIMATION_LOADED VENDING
			OR NOT HAS_MODEL_LOADED KNIFECUR
			OR NOT HAS_MODEL_LOADED WBDYG1
			OR NOT REQUEST_MODEL WBDYG2
			OR NOT HAS_MISSION_AUDIO_LOADED 3
			WAIT 0
		ENDWHILE

		SET_PLAYER_CONTROL PLAYER1 OFF
		SET_RADAR_ZOOM 100


		REMOVE_BLIP rhymebook_s2blip
		

		//1st guard
		CREATE_CHAR PEDTYPE_MISSION1 WBDYG1 1301.98 -785.31 1083.05 guard1_s2//piano guy
		SET_CHAR_HAS_USED_ENTRY_EXIT guard1_s2 1298.78 -798.2 1.0
		SET_FOLLOW_NODE_THRESHOLD_DISTANCE guard1_s2 2.0
		SET_CHAR_HEADING guard1_s2 358.83
		SET_CHAR_DECISION_MAKER guard1_s2 strap2_DM
		ADD_BLIP_FOR_CHAR guard1_s2	guard1_s2blip
		CHANGE_BLIP_DISPLAY guard1_s2blip BLIP_ONLY
//		SET_BLIP_ENTRY_EXIT guard1_s2blip 1298.78 -798.2 1.0
		GIVE_WEAPON_TO_CHAR guard1_s2 WEAPONTYPE_MICRO_UZI 9999
		SET_CHAR_ACCURACY guard1_s2 90
		SET_CHAR_DROPS_WEAPONS_WHEN_DEAD guard1_s2 FALSE
		SET_CHAR_AREA_VISIBLE guard1_s2 5


		PRINT_NOW STP2_3 7000 1 //~s~The rhyme book is in Mad Dogg's recording studio.

		WAIT 2000
		
		//////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////
		SKIP_CUTSCENE_START
		//////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////

		WAIT 1000

		CREATE_CHAR PEDTYPE_MISSION1 WBDYG2 1249.0631 -769.8262 1083.0234 guard2_s2
		SET_CHAR_HAS_USED_ENTRY_EXIT guard2_s2 1298.78 -798.2 1.0
		SET_CHAR_AREA_VISIBLE guard2_s2 5
		SET_CHAR_HEADING guard2_s2 270.5520
		SET_FOLLOW_NODE_THRESHOLD_DISTANCE guard2_s2 5.0
		SET_CHAR_DECISION_MAKER guard2_s2 strap2_DM
		
		WAIT 3500

		IF NOT IS_CHAR_DEAD guard2_s2
			TASK_GO_STRAIGHT_TO_COORD guard2_s2 1262.0562 -769.9772 1083.9878 PEDMOVE_WALK -1
		ENDIF

		WAIT 500
										    
		SET_FIXED_CAMERA_POSITION 1260.7443 -769.5749 1085.2577 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 1259.7814 -769.5841 1084.9878 JUMP_CUT
		PRINT_NOW STP2_39 7000 1 ////~r~Madd Dogg's personal security ~s~are patrolling the mansion.  Do not use any guns on your way to the rhymebook - you will alert them, use stealth to steal the rhymebook.
		WAIT 7000

		SET_NEAR_CLIP 0.1
		SET_FIXED_CAMERA_POSITION 1299.5042 -793.3958 1084.6926 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 1299.5894 -792.3994 1084.6846 JUMP_CUT
		PRINT_NOW STP2_5 7000 1 //The first guard cannot see you. This means you can sneak up to him with a melee weapon and perform a stealth kill.
		WAIT 7000

		//////////////////////////////////////////////////////////////////////////////////////////		
		//////////////////////////////////////////////////////////////////////////////////////////
		SKIP_CUTSCENE_END
		//////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////

		DELETE_CHAR guard2_s2
		//rhyme book
		CREATE_PICKUP kmb_rhymesbook PICKUP_ONCE 1256.871 -801.048 1084.063 rhymebook_s2
		ADD_BLIP_FOR_PICKUP rhymebook_s2 rhymebook_s2blip
		SET_BLIP_ENTRY_EXIT rhymebook_s2blip 1298.78 -798.2 1.0

		DISABLE_ALL_ENTRY_EXITS TRUE

		RESTORE_CAMERA_JUMPCUT
		SET_CAMERA_BEHIND_PLAYER
		SWITCH_WIDESCREEN OFF
		SET_PLAYER_CONTROL PLAYER1 ON
		GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_KNIFE 1
		SET_PLAYER_CYCLE_WEAPON_BUTTON PLAYER1 FALSE

		DELETE_CAR drgcar1_s2
		MARK_MODEL_AS_NO_LONGER_NEEDED HUNTLEY
		MARK_MODEL_AS_NO_LONGER_NEEDED STAFFORD
		DELETE_CAR drgcar2_s2
		CLEAR_PRINTS

		//Walk up to him slowly so as not to make a loud noise, target him and when your arm raises press the circle button.

		TIMERA = 0

		rhymebook_s2flag = 2
		
	ENDIF
ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////	Player on their way through mansion	////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF pickupcollect_s2flag = 0

	IF rhymebook_s2flag = 2

		IF helptext_s2flag = 0
			PRINT_HELP STP2_7 //The patrolling security guards are shown on the radar by the red blips which point in the direction the security guards are facing.
			FLASH_HUD_OBJECT HUD_FLASH_RADAR
			TIMERA = 0
			TIMERB = 0
			helptext_s2flag = 1
		ENDIF

		IF hudflash_s2flag = 0
			IF TIMERB > 5000
				FLASH_HUD_OBJECT -1
				hudflash_s2flag = 1
			ENDIF
		ENDIF

		IF NOT IS_CHAR_DEAD guard1_s2

			IF helptext_s2flag = 1
				IF TIMERA > 5000
					PRINT_HELP STP2_9 //Approach the guard and remain undetected.
					FLASH_HUD_OBJECT -1
					TIMERA = 0
					helptext_s2flag = 2
				ENDIF
			ENDIF

			IF helptext_s2flag = 2
				IF NOT IS_CHAR_DEAD guard1_s2
					IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer guard1_s2 5.0 5.0 FALSE
					OR TIMERA > 9500
						TIMERA = 0
						helptext_s2flag = 3
					ENDIF
				ENDIF
			ENDIF

			IF helptext_s2flag = 3
				IF TIMERA > 10000
				OR IS_CHAR_PLAYING_ANIM scplayer KILL_PARTIAL
				OR IS_PLAYER_TARGETTING_CHAR PLAYER1 guard1_s2 
					
					PRINT_HELP STP2_10  // Perform the stealth kill by moving slowly towards the guard while targeting him.
					
					TIMERA = 0
					helptext_s2flag = 4
				ENDIF
			ENDIF

			IF helptext_s2flag = 4
				IF IS_CHAR_PLAYING_ANIM scplayer KILL_PARTIAL
					PRINT_HELP STP2_11  
					TIMERA = 0
					helptext_s2flag = 5
				ENDIF
			ENDIF

		ENDIF

		//swimming pool
		IF guard2_s2flag = 0
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1290.907 -781.423 1083.59 4.3 4.3 6.0 FALSE

				IF IS_CHAR_DUCKING scplayer
					playerducking_s2flag = 1
				ENDIF

				SET_PLAYER_CONTROL PLAYER1 OFF
				SWITCH_WIDESCREEN ON
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				SET_NEAR_CLIP 0.1
				CLEAR_AREA 1290.907 -781.423 100.0 100.0 FALSE

				CREATE_CHAR PEDTYPE_MISSION1 WBDYG2 1290.6670 -778.9339 1083.0116 guard2_s2
				SET_CHAR_HAS_USED_ENTRY_EXIT guard2_s2 1298.78 -798.2 1.0
				SET_CHAR_AREA_VISIBLE guard2_s2 5
				SET_CHAR_HEADING guard2_s2 350.1510
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE guard2_s2 5.0
				SET_CHAR_DECISION_MAKER guard2_s2 strap2_DM
				ADD_BLIP_FOR_CHAR guard2_s2	guard2_s2blip
				CHANGE_BLIP_DISPLAY guard2_s2blip BLIP_ONLY
//				SET_BLIP_ENTRY_EXIT guard2_s2blip 1298.78 -798.2 1.0
				GIVE_WEAPON_TO_CHAR guard2_s2 WEAPONTYPE_MICRO_UZI 9999
				SET_CHAR_ACCURACY guard2_s2 90
				SET_CHAR_DROPS_WEAPONS_WHEN_DEAD guard2_s2 FALSE

				FLUSH_PATROL_ROUTE
				EXTEND_PATROL_ROUTE 1289.988 -766.581 1084.063 NONE NONE
				EXTEND_PATROL_ROUTE 1270.314 -766.564 1084.063 ROADCROSS PED
				EXTEND_PATROL_ROUTE 1270.074 -782.155 1084.063 NONE NONE
				EXTEND_PATROL_ROUTE 1290.246 -782.463 1084.056 ROADCROSS PED

				TASK_FOLLOW_PATROL_ROUTE guard2_s2 PEDMOVE_WALK FOLLOW_ROUTE_LOOP
				
				SET_CHAR_COORDINATES scplayer 1297.599 -781.234 1083.0477
				SET_CHAR_HEADING scplayer 89.58

				SET_FIXED_CAMERA_POSITION 1291.0549 -784.5060 1084.5138 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1290.5502 -783.6482 1084.4172 JUMP_CUT
				
				/////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////////////////////////
				IF NOT IS_CHAR_DEAD guard1_s2
					DELETE_CHAR guard1_s2
					CREATE_CHAR PEDTYPE_MISSION1 WBDYG1 1304.1895 -785.871 1084.0477 guard1_s2//piano guy
					SET_CHAR_HAS_USED_ENTRY_EXIT guard1_s2 1298.78 -798.2 1.0
					SET_CHAR_AREA_VISIBLE guard1_s2 5
					SET_FOLLOW_NODE_THRESHOLD_DISTANCE guard1_s2 2.0
					SET_CHAR_HEADING guard1_s2 311.2134
					SET_CHAR_DECISION_MAKER guard1_s2 strap2_DM
					ADD_BLIP_FOR_CHAR guard1_s2	guard1_s2blip
					CHANGE_BLIP_DISPLAY guard1_s2blip BLIP_ONLY
//					SET_BLIP_ENTRY_EXIT guard1_s2blip 1298.78 -798.2 1.0
					GIVE_WEAPON_TO_CHAR guard1_s2 WEAPONTYPE_MICRO_UZI 9999
					SET_CHAR_ACCURACY guard1_s2 90
					SET_CHAR_DROPS_WEAPONS_WHEN_DEAD guard1_s2 FALSE
				ENDIF
				/////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////////////////////////


				PRINT_NOW STP2_13 6000 1 //A guard is patrolling the swimming pool area. Observing his route will allow you to plan how to get past him or kill him undetected.			
				WAIT 2000
				///////////////////////////////////////////////////////////////////////
				SKIP_CUTSCENE_START
				///////////////////////////////////////////////////////////////////////

				WAIT 4000
				
				///////////////////////////////////////////////////////////////////////
				SKIP_CUTSCENE_END
				///////////////////////////////////////////////////////////////////////


				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER
				SET_PLAYER_CONTROL PLAYER1 ON
				IF playerducking_s2flag = 1
					TASK_TOGGLE_DUCK scplayer TRUE
				ENDIF
				playerducking_s2flag = 0

				SWITCH_WIDESCREEN OFF
				PRINT_NOW STP2_14 5000 1 //There are lots of shadows in this area to hide in. Hiding in shadows makes it difficult for a guard to spot you. 
				TIMERA = 0
				TIMERB = 0
				guard2_s2flag = 1
				helptext_s2flag = 6
			ENDIF
		ENDIF

		IF helptext_s2flag = 6
			IF TIMERA > 5000
				helptext_s2flag = 7
			ENDIF
		ENDIF

		//changing room area
		IF guard3_s2flag = 0
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1260.104 -769.51 1083.06 5.0 5.0 6.0 FALSE

				IF IS_CHAR_DUCKING scplayer
					playerducking_s2flag = 1
				ENDIF

				SET_PLAYER_CONTROL PLAYER1 OFF
				SWITCH_WIDESCREEN ON
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				CLEAR_PRINTS 
				CLEAR_AREA 1260.104 -769.51 100.0 100.0 FALSE

				CREATE_CHAR PEDTYPE_MISSION1 WBDYG2 1249.598 -780.06 1083.063 guard3_s2
				SET_CHAR_HAS_USED_ENTRY_EXIT guard3_s2 1298.78 -798.2 1.0
				SET_CHAR_AREA_VISIBLE guard3_s2 5
				SET_CHAR_ACCURACY guard3_s2 90
				SET_CHAR_HEADING guard3_s2 355.188
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE guard3_s2 5.0
				SET_CHAR_DECISION_MAKER guard3_s2 strap2_DM
				TASK_GO_STRAIGHT_TO_COORD guard3_s2 1250.092 -769.277 1083.064 PEDMOVE_WALK -1

				SET_FIXED_CAMERA_POSITION 1249.8562 -766.8402 1084.7775 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1249.8140 -767.8293 1084.6366 JUMP_CUT

				SET_CHAR_COORDINATES scplayer 1266.494 -769.705 1083.06
				SET_CHAR_HEADING scplayer 92.259

				///////////////////////////////////////////////////////////////////////
				///////////////////////////////////////////////////////////////////////
				IF NOT IS_CHAR_DEAD guard2_s2
					DELETE_CHAR guard2_s2 //delete guard
					CREATE_CHAR PEDTYPE_MISSION1 WBDYG2 1290.6670 -778.9339 1083.0116 guard2_s2
					SET_CHAR_HAS_USED_ENTRY_EXIT guard2_s2 1298.78 -798.2 1.0
					SET_CHAR_HEADING guard2_s2 350.1510
					SET_FOLLOW_NODE_THRESHOLD_DISTANCE guard2_s2 2.0
					SET_CHAR_DECISION_MAKER guard2_s2 strap2_DM
					ADD_BLIP_FOR_CHAR guard2_s2	guard2_s2blip
					CHANGE_BLIP_DISPLAY guard2_s2blip BLIP_ONLY
//					SET_BLIP_ENTRY_EXIT guard2_s2blip 1298.78 -798.2 1.0
					GIVE_WEAPON_TO_CHAR guard2_s2 WEAPONTYPE_MICRO_UZI 9999
					SET_CHAR_ACCURACY guard2_s2 90
					SET_CHAR_DROPS_WEAPONS_WHEN_DEAD guard2_s2 FALSE
					FLUSH_PATROL_ROUTE
					EXTEND_PATROL_ROUTE 1289.988 -766.581 1084.063 NONE NONE
					EXTEND_PATROL_ROUTE 1270.314 -766.564 1084.063 ROADCROSS PED
					EXTEND_PATROL_ROUTE 1270.074 -782.155 1084.063 NONE NONE
					EXTEND_PATROL_ROUTE 1290.246 -782.463 1084.056 ROADCROSS PED
					TASK_FOLLOW_PATROL_ROUTE guard2_s2 PEDMOVE_WALK FOLLOW_ROUTE_LOOP
				ENDIF
				///////////////////////////////////////////////////////////////////////
				///////////////////////////////////////////////////////////////////////

				PRINT_NOW STP2_15 3000 1//~s~A guard is coming.

				WAIT 2000

				///////////////////////////////////////////////////////////////////////
				SKIP_CUTSCENE_START
				///////////////////////////////////////////////////////////////////////

				WAIT 1000

				PRINT_NOW STP2_16 4000 1//~s~Find a suitable place to hide before he spots you.

				SET_FIXED_CAMERA_POSITION 1267.4048 -769.6973 1084.6667 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1266.4120 -769.6685 1084.5508 JUMP_CUT

				IF NOT IS_CHAR_DEAD guard3_s2
					SET_CHAR_COORDINATES guard3_s2 1249.598 -780.06 1083.063
					SET_CHAR_HEADING guard3_s2 355.188
				ENDIF


				WAIT 2000

				POINT_CAMERA_AT_POINT 1266.9119 -770.5618 1084.5688 INTERPOLATION

				WAIT 2000

				///////////////////////////////////////////////////////////////////////
				SKIP_CUTSCENE_END
				///////////////////////////////////////////////////////////////////////
				DELETE_CHAR guard3_s2
				CREATE_CHAR PEDTYPE_MISSION1 WBDYG2 1249.634 -781.405 1083.5 guard3_s2
				SET_CHAR_HAS_USED_ENTRY_EXIT guard3_s2 1298.78 -798.2 1.0
				SET_CHAR_AREA_VISIBLE guard3_s2 5
				SET_CHAR_HEADING guard3_s2 357.273
				ADD_BLIP_FOR_CHAR guard3_s2	guard3_s2blip
				CHANGE_BLIP_DISPLAY guard3_s2blip BLIP_ONLY
//				SET_BLIP_ENTRY_EXIT guard3_s2blip 1298.78 -798.2 1.0
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE guard3_s2 5.0
				SET_CHAR_DECISION_MAKER guard3_s2 strap2_DM
			
				OPEN_SEQUENCE_TASK guard3_s2seq
				TASK_FOLLOW_PATH_NODES_TO_COORD -1 1267.96 -769.792 1084.06 PEDMOVE_WALK -2
				SET_SEQUENCE_TO_REPEAT guard3_s2seq 1
				CLOSE_SEQUENCE_TASK guard3_s2seq
				PERFORM_SEQUENCE_TASK guard3_s2 guard3_s2seq
				CLEAR_SEQUENCE_TASK guard3_s2seq
				GIVE_WEAPON_TO_CHAR guard3_s2 WEAPONTYPE_MICRO_UZI 50
				SET_CHAR_ACCURACY guard3_s2 90
				SET_CHAR_DROPS_WEAPONS_WHEN_DEAD guard3_s2 FALSE

				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER
				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL PLAYER1 ON
				IF playerducking_s2flag = 1
					TASK_TOGGLE_DUCK scplayer TRUE
				ENDIF
				playerducking_s2flag = 0

				CLEAR_PRINTS
				PRINT_HELP STP2_41 //If the white blip on the radar that represents your position turns blue it indicates that you are in the shadows and are hidden from patrolling guards.
				guard3_s2flag = 1
			ENDIF
		ENDIF


		// lounge and bar area
		IF guard4_s2flag = 0
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1249.466 -789.487 1083.0635 7.5 7.5 6.0 FALSE

				IF HAS_MISSION_AUDIO_LOADED 3
					SET_MISSION_AUDIO_POSITION 3 1276.31 -793.92 1084.12
					PLAY_MISSION_AUDIO 3
				ENDIF

				IF IS_CHAR_DUCKING scplayer
					playerducking_s2flag = 1
				ENDIF

				SET_PLAYER_CONTROL PLAYER1 OFF
				SWITCH_WIDESCREEN ON
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				CLEAR_AREA 1249.466 -789.487 100.0 100.0 FALSE
				SET_CHAR_COORDINATES scplayer 1249.825 -780.682 1083.06
				SET_CHAR_HEADING scplayer 180.659
				CLEAR_PRINTS 
				//drink guy
				CREATE_CHAR PEDTYPE_MISSION1 WBDYG2 1258.2712 -793.395 1083.289	guard4_s2
				SET_CHAR_HAS_USED_ENTRY_EXIT guard4_s2 1298.78 -798.2 1.0
				SET_CHAR_AREA_VISIBLE guard4_s2 5
				SET_CHAR_HEADING guard4_s2 354.328
				ADD_BLIP_FOR_CHAR guard4_s2	guard4_s2blip
				CHANGE_BLIP_DISPLAY guard4_s2blip BLIP_ONLY
//				SET_BLIP_ENTRY_EXIT guard4_s2blip 1298.78 -798.2 1.0
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE guard4_s2 5.0
				SET_CHAR_DECISION_MAKER guard4_s2 strap2_DM
				SET_CHAR_DROPS_WEAPONS_WHEN_DEAD guard4_s2 FALSE

				CREATE_OBJECT_NO_OFFSET CJ_JUICE_CAN 1258.25 -792.64 1084.16 drink_s2
				GET_MODEL_DIMENSIONS CJ_JUICE_CAN x y z x y z
				y /= 2.0
				y += 0.027

				//console
				CREATE_CHAR PEDTYPE_MISSION1 WBDYG1 1276.795 -792.368 1083.055 guard5_s2
				SET_CHAR_HAS_USED_ENTRY_EXIT guard5_s2 1298.78 -798.2 1.0
				SET_CHAR_ACCURACY guard5_s2 100
				SET_CHAR_AREA_VISIBLE guard5_s2 5
				SET_CHAR_HEADING guard5_s2 180.0
				SET_CHAR_DECISION_MAKER guard5_s2 strap2_DM

				IF NOT IS_CHAR_DEAD guard4_s2
					OPEN_SEQUENCE_TASK drinkvend_s2seq
					TASK_PICK_UP_OBJECT -1 drink_s2 0.062 y 0.0 PED_HANDL HOLD_ORIENTATE_BONE_FULL VEND_USE_PT2 VENDING FALSE//-0.05 0.15 0.0
					TASK_PICK_UP_OBJECT -1 drink_s2 0.062 y 0.0 PED_HANDL HOLD_ORIENTATE_BONE_FULL VEND_DRINK2_P VENDING FALSE//-0.05 0.15 0.0
					CLOSE_SEQUENCE_TASK drinkvend_s2seq
					PERFORM_SEQUENCE_TASK guard4_s2 drinkvend_s2seq
					CLEAR_SEQUENCE_TASK drinkvend_s2seq
				ENDIF

				//drinking at bar
				SET_FIXED_CAMERA_POSITION 1251.8201 -788.3540 1084.5016 0.0 0.0 0.0 
				POINT_CAMERA_AT_POINT 1252.4631 -789.1154 1084.4197 JUMP_CUT 
				PRINT_NOW STP2_19 5000 1 //~s~If you walk straight past the guard here he will see you try crouching and walking past the bar to remain out of his line of sight.

				//////////////////////////////////////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////////////////

				IF NOT IS_CHAR_DEAD guard2_s2
					DELETE_CHAR guard2_s2 //delete guard
					CREATE_CHAR PEDTYPE_MISSION1 WBDYG2 1290.6670 -778.9339 1083.0116 guard2_s2
					SET_CHAR_HAS_USED_ENTRY_EXIT guard2_s2 1298.78 -798.2 1.0
					SET_CHAR_HEADING guard2_s2 350.1510
					SET_FOLLOW_NODE_THRESHOLD_DISTANCE guard2_s2 2.0
					SET_CHAR_DECISION_MAKER guard2_s2 strap2_DM
					ADD_BLIP_FOR_CHAR guard2_s2	guard2_s2blip
					CHANGE_BLIP_DISPLAY guard2_s2blip BLIP_ONLY
//					SET_BLIP_ENTRY_EXIT guard2_s2blip 1298.78 -798.2 1.0
					GIVE_WEAPON_TO_CHAR guard2_s2 WEAPONTYPE_MICRO_UZI 9999
					SET_CHAR_ACCURACY guard2_s2 100
					SET_CHAR_DROPS_WEAPONS_WHEN_DEAD guard2_s2 FALSE
					FLUSH_PATROL_ROUTE
					EXTEND_PATROL_ROUTE 1289.988 -766.581 1084.063 NONE NONE
					EXTEND_PATROL_ROUTE 1270.314 -766.564 1084.063 ROADCROSS PED
					EXTEND_PATROL_ROUTE 1270.074 -782.155 1084.063 NONE NONE
					EXTEND_PATROL_ROUTE 1290.246 -782.463 1084.056 ROADCROSS PED
					TASK_FOLLOW_PATROL_ROUTE guard2_s2 PEDMOVE_WALK FOLLOW_ROUTE_LOOP
				ENDIF

				IF NOT IS_CHAR_DEAD guard3_s2
					DELETE_CHAR guard3_s2 //delete guard
					CREATE_CHAR PEDTYPE_MISSION1 WBDYG2 1267.96 -769.792 1084.06 guard3_s2
					SET_CHAR_HAS_USED_ENTRY_EXIT guard3_s2 1298.78 -798.2 1.0
					SET_CHAR_HEADING guard3_s2 0.141
					ADD_BLIP_FOR_CHAR guard3_s2	guard3_s2blip
					CHANGE_BLIP_DISPLAY guard3_s2blip BLIP_ONLY
//					SET_BLIP_ENTRY_EXIT guard3_s2blip 1298.78 -798.2 1.0
					SET_FOLLOW_NODE_THRESHOLD_DISTANCE guard3_s2 5.0
					SET_CHAR_DECISION_MAKER guard3_s2 strap2_DM
					GIVE_WEAPON_TO_CHAR guard3_s2 WEAPONTYPE_MICRO_UZI 50
					SET_CHAR_ACCURACY guard3_s2 100
					SET_CHAR_DROPS_WEAPONS_WHEN_DEAD guard3_s2 FALSE
				ENDIF
				//////////////////////////////////////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////////////////

				WAIT 1750

				///////////////////////////////////////////////////////////////////////
				SKIP_CUTSCENE_START
				///////////////////////////////////////////////////////////////////////

				WAIT 2000

				//console gamers
				IF NOT IS_CHAR_DEAD guard5_s2
					TASK_PLAY_ANIM guard5_s2 PED_CONSOLE_LOOP CRIB 4.0 TRUE FALSE FALSE FALSE -1
					START_CHAR_FACIAL_TALK guard5_s2 10000
				ENDIF

				WAIT 500

				SET_FIXED_CAMERA_POSITION 1274.9828 -790.5506 1084.0632 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1275.5747 -791.3560 1084.0349 JUMP_CUT

				PRINT_NOW ( LOC2_BA ) 4000 1
				PLAY_MISSION_AUDIO 1

				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0
				ENDWHILE

				//tv view
				SET_FIXED_CAMERA_POSITION 1276.3011 -794.5306 1084.2312 0.0 0.0 0.0 
				POINT_CAMERA_AT_POINT 1276.2915 -793.5345 1084.1427 JUMP_CUT 

				PRINT_NOW ( LOC2_BB ) 4000 1
				PLAY_MISSION_AUDIO 2

				IF NOT IS_CHAR_DEAD guard5_s2
					OPEN_SEQUENCE_TASK console_s2seq
					TASK_PLAY_ANIM -1 PED_CONSOLE_LOOSE CRIB 4.0 FALSE FALSE FALSE FALSE -1
					TASK_PLAY_ANIM -1 PED_CONSOLE_LOOP CRIB 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK console_s2seq
					PERFORM_SEQUENCE_TASK guard5_s2 console_s2seq
					CLEAR_SEQUENCE_TASK console_s2seq
				ENDIF

				WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
					WAIT 0
				ENDWHILE

				///////////////////////////////////////////////////////////////////////
				SKIP_CUTSCENE_END
				///////////////////////////////////////////////////////////////////////

				DELETE_CHAR guard4_s2
				DELETE_CHAR guard5_s2

				CREATE_CHAR PEDTYPE_MISSION1 WBDYG2 1258.2712 -793.395 1083.289 guard4_s2 //
				SET_CHAR_HAS_USED_ENTRY_EXIT guard4_s2 1298.78 -798.2 1.0
				SET_CHAR_HEADING guard4_s2 354.328 //
				TASK_PLAY_ANIM guard4_s2 ATM PED 4.0 TRUE FALSE FALSE FALSE -1
				ADD_BLIP_FOR_CHAR guard4_s2	guard4_s2blip
				CHANGE_BLIP_DISPLAY guard4_s2blip BLIP_ONLY
//				SET_BLIP_ENTRY_EXIT guard4_s2blip 1298.78 -798.2 1.0
				SET_CHAR_DECISION_MAKER guard4_s2 strap2_DM
				SET_CHAR_DROPS_WEAPONS_WHEN_DEAD guard4_s2 FALSE
				GIVE_WEAPON_TO_CHAR guard4_s2 WEAPONTYPE_MICRO_UZI 5000
				SET_CHAR_ACCURACY guard4_s2 100
				SET_INFORM_RESPECTED_FRIENDS guard4_s2 5.0 1
				//console
				CREATE_CHAR PEDTYPE_MISSION1 WBDYG1 1276.795 -792.368 1083.055 guard5_s2
				SET_CHAR_HAS_USED_ENTRY_EXIT guard5_s2 1298.78 -798.2 1.0
				SET_CHAR_HEADING guard5_s2 180.0
				SET_CHAR_DECISION_MAKER guard5_s2 strap2_DM
				SET_CHAR_DROPS_WEAPONS_WHEN_DEAD guard5_s2 FALSE
				GIVE_WEAPON_TO_CHAR guard5_s2 WEAPONTYPE_MICRO_UZI 5000
				ADD_BLIP_FOR_CHAR guard5_s2	guard5_s2blip
				CHANGE_BLIP_DISPLAY guard5_s2blip BLIP_ONLY
//				SET_BLIP_ENTRY_EXIT guard5_s2blip 1298.78 -798.2 1.0
				SET_CHAR_ACCURACY guard5_s2 100
				SET_INFORM_RESPECTED_FRIENDS guard5_s2 5.0 1

				CREATE_CHAR PEDTYPE_MISSION1 WBDYG2 1262.533 -805.139 1083.0555 guard7_s2
				SET_CHAR_HAS_USED_ENTRY_EXIT guard7_s2 1298.78 -798.2 1.0
				SET_CHAR_HEADING guard7_s2 359.265
				SET_CHAR_DECISION_MAKER guard7_s2 strap2_DM
				ADD_BLIP_FOR_CHAR guard7_s2	guard7_s2blip
				CHANGE_BLIP_DISPLAY guard7_s2blip BLIP_ONLY
//				SET_BLIP_ENTRY_EXIT guard7_s2blip 1298.78 -798.2 1.0
				SET_CHAR_DROPS_WEAPONS_WHEN_DEAD guard7_s2 FALSE
				GIVE_WEAPON_TO_CHAR guard7_s2 WEAPONTYPE_MICRO_UZI 5555
				SET_CHAR_ACCURACY guard7_s2 100

				IF NOT IS_CHAR_DEAD guard5_s2
					TASK_PLAY_ANIM guard5_s2 PED_CONSOLE_LOOP CRIB 4.0 TRUE FALSE FALSE FALSE -1
				ENDIF
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2

				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER
				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL PLAYER1 ON
				IF playerducking_s2flag = 1
					TASK_TOGGLE_DUCK scplayer TRUE
				ENDIF
				playerducking_s2flag = 0

				TIMERA = 0
				TIMERB = 0
				helptext_s2flag = 9
				PRINT_HELP STP2_36 //Press ~h~L3 ~h~ to crouch. You can walk while crouched allowing you to move quietly and reduce the chances of you being spotted.							
				guard4_s2flag = 1
			ENDIF
		ENDIF
	
		IF guard4_s2flag = 1
			GET_CHAR_SPEED scplayer x
			IF NOT IS_CHAR_DEAD guard4_s2
				IF IS_CHAR_IN_AREA_3D scplayer 1246.77 -785.08 1083.26 1255.87 -797.69 1090.0 FALSE
				OR x > 0.0
					SET_CHAR_HEADING guard4_s2 168.503
					OPEN_SEQUENCE_TASK guard4_s2seq
					TASK_GO_STRAIGHT_TO_COORD -1 1256.29 -795.6 1084.289 PEDMOVE_WALK -1
					TASK_PLAY_ANIM -1 ATM PED 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK guard4_s2seq
					PERFORM_SEQUENCE_TASK guard4_s2 guard4_s2seq
					CLEAR_SEQUENCE_TASK guard4_s2seq
					guard4_s2flag = 2
				ENDIF
			ENDIF
		ENDIF

		IF HAS_PICKUP_BEEN_COLLECTED rhymebook_s2
			//you now have the rhyme book get it back to burger shot 
			//Use the skills you have learned to get past the remaining guards patrolling the mansion.
			REMOVE_BLIP rhymebook_s2blip
			ADD_BLIP_FOR_COORD 1298.74 -797.46 1083.4 rhymebook_s2blip
			IF IS_PLAYER_PLAYING PLAYER1
				SET_PLAYER_CYCLE_WEAPON_BUTTON PLAYER1 TRUE
			ENDIF
			SET_BLIP_ENTRY_EXIT rhymebook_s2blip 1298.78 -798.2 1.0
 			PRINT_NOW STP2_40 7000 1 //~y~Exit ~s~the mansion and get the rhymebook back to OG loc. Its safe to use your weapons now, although it may be less risky if you use stealth to get past any ~r~guards ~s~on patrol.
			TIMERA = 0
			TIMERB = 0
			rhymebook_s2flag = 3
			pickupcollect_s2flag = 1
		ENDIF

		IF IS_CHAR_DEAD guard1_s2
			IF DOES_BLIP_EXIST guard1_s2blip
				REMOVE_BLIP guard1_s2blip
			ENDIF
		ENDIF
		IF IS_CHAR_DEAD guard2_s2
			IF DOES_BLIP_EXIST guard2_s2blip
				REMOVE_BLIP guard2_s2blip
			ENDIF
		ENDIF
		IF IS_CHAR_DEAD guard3_s2
			IF DOES_BLIP_EXIST guard3_s2blip
				REMOVE_BLIP guard3_s2blip
			ENDIF
		ENDIF
		IF IS_CHAR_DEAD guard4_s2
			IF DOES_BLIP_EXIST guard4_s2blip
				REMOVE_BLIP guard4_s2blip
			ENDIF
		ENDIF
		IF IS_CHAR_DEAD guard5_s2
			IF DOES_BLIP_EXIST guard5_s2blip
				REMOVE_BLIP guard5_s2blip
			ENDIF
		ENDIF
		IF IS_CHAR_DEAD guard7_s2
			IF DOES_BLIP_EXIST guard7_s2blip
				REMOVE_BLIP guard7_s2blip
			ENDIF
		ENDIF

//		//stealth dialogue
//		IF NOT IS_CHAR_DEAD guard2_s2
//			enemy_s2 = guard2_s2
//			playerseenstate_s2 = guard2playerseenstate_s2
//			GOSUB alertstate_s2label
//			guard2playerseenstate_s2 = playerseenstate_s2
//		ENDIF
//
//		IF NOT IS_CHAR_DEAD guard3_s2
//			enemy_s2 = guard3_s2
//			playerseenstate_s2 = guard3playerseenstate_s2
//			GOSUB alertstate_s2label
//			guard3playerseenstate_s2 = playerseenstate_s2
//		ENDIF
//
//		IF NOT IS_CHAR_DEAD guard4_s2
//			enemy_s2 = guard4_s2
//			playerseenstate_s2 = guard4playerseenstate_s2
//			GOSUB alertstate_s2label
//			guard4playerseenstate_s2 = playerseenstate_s2
//		ENDIF
//
//		IF NOT IS_CHAR_DEAD guard5_s2
//			enemy_s2 = guard5_s2
//			playerseenstate_s2 = guard5playerseenstate_s2
//			GOSUB alertstate_s2label
//			guard5playerseenstate_s2 = playerseenstate_s2
//		ENDIF
//
//		IF NOT IS_CHAR_DEAD guard7_s2
//			enemy_s2 = guard7_s2
//			playerseenstate_s2 = guard7playerseenstate_s2
//			GOSUB alertstate_s2label
//			guard7playerseenstate_s2 = playerseenstate_s2
//		ENDIF


	ENDIF
ENDIF


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////	Player on their way out mansion	////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF rhymebook_s2flag = 3
	
	IF pickupcollect_s2flag = 1
		DISABLE_ALL_ENTRY_EXITS FALSE

		CREATE_PICKUP_WITH_AMMO silenced PICKUP_ONCE 30 1237.4177 -808.5872 1084.09 pickupgun_s2 //silenced pistol, office	
		//text - some of the guards are using
		//if not console guys are dead create him
		//if not bar guy is dead create him
		DELETE_CHAR guard1_s2
		DELETE_CHAR guard2_s2
		DELETE_CHAR guard3_s2

		CREATE_CHAR PEDTYPE_MISSION1 WBDYG1 1243.53 -812.19 1083.04 guard1_s2 //investigator
		SET_CHAR_HAS_USED_ENTRY_EXIT guard1_s2 1298.78 -798.2 1.0
		SET_CHAR_HEADING guard1_s2 270.948
		SET_CHAR_DECISION_MAKER guard1_s2 strap2_DM
		ADD_BLIP_FOR_CHAR guard1_s2	guard1_s2blip
		CHANGE_BLIP_DISPLAY guard1_s2blip BLIP_ONLY
//		SET_BLIP_ENTRY_EXIT guard1_s2blip 1298.78 -798.2 1.0
		GIVE_WEAPON_TO_CHAR guard1_s2 WEAPONTYPE_PISTOL_SILENCED 99999
		SET_FOLLOW_NODE_THRESHOLD_DISTANCE guard1_s2 2.0
		TASK_FOLLOW_PATH_NODES_TO_COORD guard1_s2 1270.694 -802.518 1084.05 PEDMOVE_WALK -2
		SET_INFORM_RESPECTED_FRIENDS guard1_s2 10.0 1
		SET_CHAR_ACCURACY guard1_s2 100

		CREATE_CHAR PEDTYPE_MISSION1 WBDYG2 1280.02 -789.01 1083.05 guard2_s2 //outside recording room
		SET_CHAR_HAS_USED_ENTRY_EXIT guard2_s2 1298.78 -798.2 1.0
		SET_CHAR_HEADING guard2_s2 91.837
		SET_CHAR_DECISION_MAKER guard2_s2 strap2_DM
		ADD_BLIP_FOR_CHAR guard2_s2	guard2_s2blip
		CHANGE_BLIP_DISPLAY guard2_s2blip BLIP_ONLY
//		SET_BLIP_ENTRY_EXIT guard2_s2blip 1298.78 -798.2 1.0
		GIVE_WEAPON_TO_CHAR guard2_s2 WEAPONTYPE_PISTOL_SILENCED 99999
		SET_INFORM_RESPECTED_FRIENDS guard2_s2 5.0 1
		SET_CHAR_ACCURACY guard2_s2 100

		CREATE_CHAR PEDTYPE_MISSION1 WBDYG1 1249.768 -780.616 1084.063 guard6_s2	//corridor and changing rooms
		SET_CHAR_HAS_USED_ENTRY_EXIT guard6_s2 1298.78 -798.2 1.0
		SET_CHAR_HEADING guard6_s2 3.43
		ADD_BLIP_FOR_CHAR guard6_s2	guard6_s2blip
		CHANGE_BLIP_DISPLAY guard6_s2blip BLIP_ONLY
//		SET_BLIP_ENTRY_EXIT guard6_s2blip 1298.78 -798.2 1.0
		SET_CHAR_DECISION_MAKER guard6_s2 strap2_DM
		GIVE_WEAPON_TO_CHAR guard6_s2 WEAPONTYPE_PISTOL_SILENCED 99999
		FLUSH_PATROL_ROUTE
		EXTEND_PATROL_ROUTE 1249.799 -769.679 1084.063 ROADCROSS PED
		EXTEND_PATROL_ROUTE 1267.62 -769.651 1084.06 NONE NONE
		EXTEND_PATROL_ROUTE 1249.799 -769.679 1084.063 NONE NONE
		EXTEND_PATROL_ROUTE 1249.768 -780.616 1084.063 NONE NONE
		TASK_FOLLOW_PATROL_ROUTE guard6_s2 PEDMOVE_WALK FOLLOW_ROUTE_LOOP
		SET_INFORM_RESPECTED_FRIENDS guard6_s2 15.0 2

		IF NOT IS_CHAR_DEAD guard5_s2
			DELETE_CHAR guard5_s2
			CREATE_CHAR PEDTYPE_MISSION1 WBDYG1 1276.795 -792.368 1083.055 guard5_s2	//console gaming
			SET_CHAR_HAS_USED_ENTRY_EXIT guard5_s2 1298.78 -798.2 1.0
			SET_CHAR_HEADING guard5_s2 180.0
			SET_CHAR_DECISION_MAKER guard5_s2 strap2empty_DM
			GIVE_WEAPON_TO_CHAR guard5_s2 WEAPONTYPE_PISTOL_SILENCED 99999
			ADD_BLIP_FOR_CHAR guard5_s2	guard5_s2blip
			CHANGE_BLIP_DISPLAY guard5_s2blip BLIP_ONLY
//			SET_BLIP_ENTRY_EXIT guard5_s2blip 1298.78 -798.2 1.0
			TASK_PLAY_ANIM guard5_s2 PED_CONSOLE_LOOP CRIB 4.0 TRUE FALSE FALSE FALSE -1
			SET_INFORM_RESPECTED_FRIENDS guard5_s2 20.0 1
		ELSE
			REMOVE_CHAR_ELEGANTLY guard5_s2
			CREATE_CHAR PEDTYPE_MISSION1 WBDYG1 1270.616 -792.298 1084.047 guard5_s2	//console gaming
			SET_CHAR_HAS_USED_ENTRY_EXIT guard5_s2 1298.78 -798.2 1.0
			SET_CHAR_HEADING guard5_s2 186.126
			SET_CHAR_DECISION_MAKER guard5_s2 strap2empty_DM
			GIVE_WEAPON_TO_CHAR guard5_s2 WEAPONTYPE_PISTOL_SILENCED 99999
			ADD_BLIP_FOR_CHAR guard5_s2	guard5_s2blip
			CHANGE_BLIP_DISPLAY guard5_s2blip BLIP_ONLY
//			SET_BLIP_ENTRY_EXIT guard5_s2blip 1298.78 -798.2 1.0
			TASK_SIT_DOWN guard5_s2 400000
			SET_INFORM_RESPECTED_FRIENDS guard5_s2 20.0 1
		ENDIF

		IF NOT IS_CHAR_DEAD guard4_s2
			DELETE_CHAR guard4_s2
			CREATE_CHAR PEDTYPE_MISSION1 WBDYG2 1256.492 -795.662 1084.282 guard4_s2
			SET_CHAR_HAS_USED_ENTRY_EXIT guard4_s2 1298.78 -798.2 1.0
			SET_CHAR_HEADING guard4_s2 186.842
			TASK_PLAY_ANIM guard4_s2 ATM PED 4.0 TRUE FALSE FALSE FALSE -1
			ADD_BLIP_FOR_CHAR guard4_s2	guard4_s2blip
			CHANGE_BLIP_DISPLAY guard4_s2blip BLIP_ONLY
//			SET_BLIP_ENTRY_EXIT guard4_s2blip 1298.78 -798.2 1.0
			SET_CHAR_DECISION_MAKER guard4_s2 strap2empty_DM
			GIVE_WEAPON_TO_CHAR guard4_s2 WEAPONTYPE_PISTOL_SILENCED 99999
			SET_INFORM_RESPECTED_FRIENDS guard4_s2 10.0 1
		ELSE
			REMOVE_CHAR_ELEGANTLY guard4_s2
			CREATE_CHAR PEDTYPE_MISSION1 WBDYG2 1252.99 -795.461 1083.282 guard4_s2
			SET_CHAR_HAS_USED_ENTRY_EXIT guard4_s2 1298.78 -798.2 1.0
			SET_CHAR_HEADING guard4_s2 186.842
			TASK_PLAY_ANIM guard4_s2 ATM PED 4.0 TRUE FALSE FALSE FALSE -1
			ADD_BLIP_FOR_CHAR guard4_s2	guard4_s2blip
			CHANGE_BLIP_DISPLAY guard4_s2blip BLIP_ONLY
//			SET_BLIP_ENTRY_EXIT guard4_s2blip 1298.78 -798.2 1.0
			SET_CHAR_DECISION_MAKER guard4_s2 strap2empty_DM
			GIVE_WEAPON_TO_CHAR guard4_s2 WEAPONTYPE_PISTOL_SILENCED 99999
			SET_INFORM_RESPECTED_FRIENDS guard4_s2 10.0 1
		ENDIF

		CREATE_CHAR PEDTYPE_MISSION1 WBDYG2 1290.6670 -778.9339 1083.0116 guard8_s2 //swimming pool
		SET_CHAR_HAS_USED_ENTRY_EXIT guard8_s2 1298.78 -798.2 1.0
		SET_CHAR_HEADING guard8_s2 350.1510
		SET_FOLLOW_NODE_THRESHOLD_DISTANCE guard8_s2 5.0
		SET_CHAR_DECISION_MAKER guard8_s2 strap2empty_DM
		ADD_BLIP_FOR_CHAR guard8_s2	guard8_s2blip
		CHANGE_BLIP_DISPLAY guard8_s2blip BLIP_ONLY
//		SET_BLIP_ENTRY_EXIT guard8_s2blip 1298.78 -798.2 1.0
		GIVE_WEAPON_TO_CHAR guard8_s2 WEAPONTYPE_PISTOL_SILENCED 5000
		SET_CHAR_ACCURACY guard8_s2 100
		FLUSH_PATROL_ROUTE
		EXTEND_PATROL_ROUTE 1289.988 -766.581 1084.063 NONE NONE
		EXTEND_PATROL_ROUTE 1270.314 -766.564 1084.063 ROADCROSS PED
		EXTEND_PATROL_ROUTE 1270.074 -782.155 1084.063 NONE NONE
		EXTEND_PATROL_ROUTE 1290.246 -782.463 1084.056 ROADCROSS PED
		TASK_FOLLOW_PATROL_ROUTE guard8_s2 PEDMOVE_WALK FOLLOW_ROUTE_LOOP
		guard8_s2flag = 0

		pickupcollect_s2flag = 2
	ENDIF


	IF bararea_s2flag = 0
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1282.61 -787.389 1084.0635 6.0 6.0 6.0 FALSE

			IF NOT IS_CHAR_DEAD guard4_s2
				SET_CHAR_DECISION_MAKER guard4_s2 strap2_DM
			ENDIF
			IF NOT IS_CHAR_DEAD guard5_s2
				SET_CHAR_DECISION_MAKER guard5_s2 strap2_DM
			ENDIF

			bararea_s2flag = 1
		ENDIF
	ENDIF

	IF guard8_s2flag = 0
		IF LOCATE_CHAR_ON_FOOT_3D scplayer 1249.61 -772.86 1083.46 7.0 7.0 5.0 FALSE
			IF NOT IS_CHAR_DEAD guard8_s2
				SET_CHAR_DECISION_MAKER guard8_s2 strap2_DM
				guard8_s2flag = 1
			ENDIF
		ENDIF
	ENDIF
  
	IF pickupcollect_s2flag > 0
		IF helptext_s2flag = 9
			IF TIMERA > 8500
				PRINT_HELP STP2_33 //The guards are using silenced guns if you manage to obtain one they will allow you to fire a gun without being heard by nearby guards.
				helptext_s2flag = 10
			ENDIF
		ENDIF
	ENDIF

//	GET_AREA_VISIBLE mansioninterior_s2
	GET_CHAR_AREA_VISIBLE scplayer mansioninterior_s2
	IF mansioninterior_s2 = 0
		REMOVE_BLIP rhymebook_s2blip
		WHILE NOT GET_FADING_STATUS
			WAIT 0 
		ENDWHILE

		SET_CHAR_COORDINATES scplayer 1299.664 -800.546 83.148
		SET_CHAR_HEADING scplayer 271.284
		SET_CAMERA_BEHIND_PLAYER

		pickupcollect_s2flag = 2
		rhymebook_s2flag = 4
	ENDIF

	IF IS_CHAR_DEAD guard1_s2
		IF DOES_BLIP_EXIST guard1_s2blip
			REMOVE_BLIP guard1_s2blip
		ENDIF
	ENDIF
	IF IS_CHAR_DEAD guard2_s2
		IF DOES_BLIP_EXIST guard2_s2blip
			REMOVE_BLIP guard2_s2blip
		ENDIF
	ENDIF
	IF IS_CHAR_DEAD guard3_s2
		IF DOES_BLIP_EXIST guard3_s2blip
			REMOVE_BLIP guard3_s2blip
		ENDIF
	ENDIF
	IF IS_CHAR_DEAD guard4_s2
		IF DOES_BLIP_EXIST guard4_s2blip
			REMOVE_BLIP guard4_s2blip
		ENDIF
	ENDIF
	IF IS_CHAR_DEAD guard5_s2
		IF DOES_BLIP_EXIST guard5_s2blip
			REMOVE_BLIP guard5_s2blip
		ENDIF
	ENDIF
	IF IS_CHAR_DEAD guard6_s2
		IF DOES_BLIP_EXIST guard6_s2blip
			REMOVE_BLIP guard6_s2blip
		ENDIF
	ENDIF
	IF IS_CHAR_DEAD guard7_s2
		IF DOES_BLIP_EXIST guard7_s2blip
			REMOVE_BLIP guard7_s2blip
		ENDIF
	ENDIF
	IF IS_CHAR_DEAD guard8_s2
		IF DOES_BLIP_EXIST guard8_s2blip
			REMOVE_BLIP guard8_s2blip
		ENDIF
	ENDIF


//	//stealth dialogue
//	IF NOT IS_CHAR_DEAD guard2_s2
//		enemy_s2 = guard2_s2
//		playerseenstate_s2 = guard2playerseenstate_s2
//		GOSUB alertstate_s2label
//		guard2playerseenstate_s2 = playerseenstate_s2
//	ENDIF
//
//	IF NOT IS_CHAR_DEAD guard3_s2
//		enemy_s2 = guard3_s2
//		playerseenstate_s2 = guard3playerseenstate_s2
//		GOSUB alertstate_s2label
//		guard3playerseenstate_s2 = playerseenstate_s2
//	ENDIF
//
//	IF NOT IS_CHAR_DEAD guard4_s2
//		enemy_s2 = guard4_s2
//		playerseenstate_s2 = guard4playerseenstate_s2
//		GOSUB alertstate_s2label
//		guard4playerseenstate_s2 = playerseenstate_s2
//	ENDIF
//
//	IF NOT IS_CHAR_DEAD guard5_s2
//		enemy_s2 = guard5_s2
//		playerseenstate_s2 = guard5playerseenstate_s2
//		GOSUB alertstate_s2label
//		guard5playerseenstate_s2 = playerseenstate_s2
//	ENDIF
//
//	IF NOT IS_CHAR_DEAD guard6_s2
//		enemy_s2 = guard6_s2
//		playerseenstate_s2 = guard6playerseenstate_s2
//		GOSUB alertstate_s2label
//		guard6playerseenstate_s2 = playerseenstate_s2
//	ENDIF
//
//	IF NOT IS_CHAR_DEAD guard7_s2
//		enemy_s2 = guard7_s2
//		playerseenstate_s2 = guard7playerseenstate_s2
//		GOSUB alertstate_s2label
//		guard7playerseenstate_s2 = playerseenstate_s2
//	ENDIF


ENDIF


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////   Outside Mansion		////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF pickupcollect_s2flag = 2
	IF rhymebook_s2flag = 4

		ADD_BLIP_FOR_COORD 790.0 -1631.0 12.43 rhymebook_s2blip
		CLEAR_MISSION_AUDIO 3
		CREATE_CAR MTBIKE 1306.718 -797.24 83.66 mtbike_s2
		SET_CAR_HEADING mtbike_s2 206.253

		DELETE_CHAR guard1_s2
		DELETE_CHAR guard2_s2
		DELETE_CHAR guard3_s2
		DELETE_CHAR guard4_s2
		DELETE_CHAR guard5_s2
		DELETE_CHAR guard6_s2
		DELETE_CHAR guard7_s2
		REMOVE_BLIP guard1_s2blip
		REMOVE_BLIP guard2_s2blip
		REMOVE_BLIP guard3_s2blip
		REMOVE_BLIP guard4_s2blip
		REMOVE_BLIP guard5_s2blip
		REMOVE_BLIP guard6_s2blip
		REMOVE_BLIP guard7_s2blip

		MARK_MODEL_AS_NO_LONGER_NEEDED WBDYG1
		MARK_MODEL_AS_NO_LONGER_NEEDED WBDYG2
		MARK_MODEL_AS_NO_LONGER_NEEDED HUNTLEY
		MARK_MODEL_AS_NO_LONGER_NEEDED STAFFORD
		MARK_MODEL_AS_NO_LONGER_NEEDED KMB_RHYMESBOOK
		MARK_MODEL_AS_NO_LONGER_NEEDED CJ_JUICE_CAN
		MARK_MODEL_AS_NO_LONGER_NEEDED SILENCED
		REMOVE_ANIMATION CRIB
		REMOVE_ANIMATION VENDING
		MARK_MODEL_AS_NO_LONGER_NEEDED KNIFECUR
		SET_RADAR_ZOOM 0
		REMOVE_BLIP guard1_s2blip
		REMOVE_BLIP guard2_s2blip
		REMOVE_BLIP guard3_s2blip
		REMOVE_BLIP guard4_s2blip
		REMOVE_BLIP guard5_s2blip
		REMOVE_BLIP guard6_s2blip
		REMOVE_BLIP guard7_s2blip

		PRINT_NOW STP2_30 7000 1 //~s~You need to take the rhymebook back to OG Loc he is at ~y~Burger Shot~s~.
		
		rhymebook_s2flag = 5
		TIMERA = 0

	ENDIF
ENDIF

IF mountainbike_s2flag = 0
	IF NOT IS_CAR_DEAD mtbike_s2
		IF IS_CHAR_SITTING_IN_CAR scplayer mtbike_s2
			//SET_PLAYER_IN_CAR_CAMERA_MODE ZOOM_THREE
			mountainbike_s2flag = 1
		ENDIF
	ENDIF
ENDIF

IF rhymebook_s2flag = 5
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 788.05 -1630.31 12.42 2.0 2.0 5.0 TRUE

		SET_PLAYER_CONTROL PLAYER1 OFF
		SWITCH_WIDESCREEN ON

		DO_FADE 1000 FADE_OUT

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE 

		CLEAR_AREA -1095.4885 -1630.6090 50.0 50.0 TRUE

		MARK_CAR_AS_NO_LONGER_NEEDED mtbike_s2

		IF IS_CHAR_IN_ANY_CAR scplayer
			STORE_CAR_CHAR_IS_IN scplayer car
			SET_CAR_COORDINATES car 784.686 -1613.7 12.22
			SET_CAR_HEADING car 353.08
			WARP_CHAR_FROM_CAR_TO_COORD scplayer 785.75 -1632.84 12.43
			SET_CHAR_HEADING scplayer 316.0
		ELSE
			SET_CHAR_COORDINATES scplayer 785.75 -1632.84 12.43
			SET_CHAR_HEADING scplayer 316.0
		ENDIF

		REQUEST_ANIMATION GANGS
		LOAD_SPECIAL_CHARACTER 1 OGLOC

		CLEAR_MISSION_AUDIO 1
		LOAD_MISSION_AUDIO 1 SOUND_LOC2_CA //Yo, Loc, I got what you wanted, dog.
		CLEAR_MISSION_AUDIO 2
		LOAD_MISSION_AUDIO 2 SOUND_LOC2_CB //Holmes, you are ice cold, dude!
		LOAD_ALL_MODELS_NOW

		WHILE NOT HAS_ANIMATION_LOADED GANGS
			OR NOT HAS_MISSION_AUDIO_LOADED 1
			OR NOT HAS_MISSION_AUDIO_LOADED 2
			WAIT 0
		ENDWHILE

		CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 792.72 -1626.6 12.43 ogloc_s2 
		SET_CHAR_HEADING ogloc_s2 114.71
		SET_CHAR_DECISION_MAKER ogloc_s2 strap2empty_DM
		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

		SHUT_CHAR_UP ogloc_s2 TRUE
		SHUT_CHAR_UP scplayer TRUE

		DO_FADE 2000 FADE_IN
		missionaudio_s2flag = 1
		SET_FIXED_CAMERA_POSITION 785.7997 -1635.4298 13.4579 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 786.2913 -1634.5647 13.3583 JUMP_CUT

		TASK_GO_STRAIGHT_TO_COORD scplayer 788.98 -1629.61 12.59 PEDMOVE_WALK -1
		TASK_GO_STRAIGHT_TO_COORD ogloc_s2 788.98 -1629.61 12.59 PEDMOVE_WALK -1
		TIMERA = 0
		rhymebook_s2flag = 6
	ENDIF
ENDIF

IF rhymebook_s2flag = 6
	IF TIMERA > 2440
		IF NOT IS_CHAR_DEAD ogloc_s2

			SET_FIXED_CAMERA_POSITION 788.2733 -1628.8318 13.9503 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 788.4730 -1629.8112 13.9214 JUMP_CUT

			SET_CHAR_COORDINATES scplayer 787.973 -1630.274 12.42
			SET_CHAR_HEADING scplayer 310.378
			SET_CHAR_COORDINATES ogloc_s2 788.7348 -1629.6262 12.42
			SET_CHAR_HEADING ogloc_s2 130.378
			CLEAR_CHAR_TASKS_IMMEDIATELY ogloc_s2
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			TASK_PLAY_ANIM ogloc_s2 hndshkfa GANGS 4.0 FALSE FALSE FALSE FALSE -1
			TASK_PLAY_ANIM scplayer hndshkfa GANGS 4.0 FALSE FALSE FALSE FALSE -1
			rhymebook_s2flag = 7
		ENDIF
	ENDIF
ENDIF

IF rhymebook_s2flag > 4
	IF NOT IS_CHAR_DEAD ogloc_s2

		IF missionaudio_s2flag = 1
			PLAY_MISSION_AUDIO 1
			START_CHAR_FACIAL_TALK scplayer 10000
			PRINT_NOW LOC2_CA 5000 1//Yo, Loc, I got what you wanted, dog.
			missionaudio_s2flag = 2
		ENDIF
		IF missionaudio_s2flag = 2
			IF HAS_MISSION_AUDIO_FINISHED 1
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				STOP_CHAR_FACIAL_TALK scplayer
				LOAD_MISSION_AUDIO 1 SOUND_LOC2_CC //I'll catch you later, dog.
				missionaudio_s2flag = 3
			ENDIF
		ENDIF
		IF missionaudio_s2flag = 3
			PLAY_MISSION_AUDIO 2	
			PRINT_NOW LOC2_CB 5000 1//Holmes, you are ice cold, dude!
			START_CHAR_FACIAL_TALK ogloc_s2 10000
			missionaudio_s2flag = 4
		ENDIF
		IF missionaudio_s2flag = 4
			IF HAS_MISSION_AUDIO_FINISHED 2
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 2
				STOP_CHAR_FACIAL_TALK ogloc_s2
				LOAD_MISSION_AUDIO 2 SOUND_LOC2_DC //Ciao, holmes!
				missionaudio_s2flag = 5
			ENDIF
		ENDIF
		IF missionaudio_s2flag = 5
			IF HAS_MISSION_AUDIO_LOADED 1
				PRINT_NOW LOC2_CC 5000 1//I'll catch you later, dog
				START_CHAR_FACIAL_TALK scplayer 10000
				PLAY_MISSION_AUDIO 1
				missionaudio_s2flag = 6
			ENDIF
		ENDIF
		IF missionaudio_s2flag = 6
			IF HAS_MISSION_AUDIO_FINISHED 1
				CLEAR_PRINTS
				STOP_CHAR_FACIAL_TALK scplayer
				missionaudio_s2flag = 7
			ENDIF
		ENDIF
		IF missionaudio_s2flag = 7
			SET_FIXED_CAMERA_POSITION 773.2047 -1633.0664 17.7324 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 774.1813 -1632.9076 17.5877 JUMP_CUT
			PLAY_MISSION_AUDIO 2
			PRINT_NOW LOC2_DC 5000 1//Ciao, holmes!
			START_CHAR_FACIAL_TALK ogloc_s2 4000
			TASK_GO_STRAIGHT_TO_COORD scplayer 806.1331 -1630.2195 12.3906 PEDMOVE_WALK 60000
			TASK_GO_STRAIGHT_TO_COORD ogloc_s2 793.8177 -1625.1552 12.3828 PEDMOVE_WALK 60000
			missionaudio_s2flag = 8
		ENDIF
		IF missionaudio_s2flag = 8
			IF HAS_MISSION_AUDIO_FINISHED 2
				DO_FADE 1000 FADE_OUT
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 2

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

				SET_CHAR_COORDINATES scplayer 808.299 -1630.424 12.42
				SET_CHAR_HEADING scplayer 254.602

				DO_FADE 1000 FADE_IN
				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER
				SET_PLAYER_CONTROL PLAYER1 ON
				SWITCH_WIDESCREEN OFF
				GOTO mission_music2_passed

				missionaudio_s2flag = 9
			ENDIF
		ENDIF

	ENDIF
ENDIF




GOTO stealrhymebook_loop


//alertstate_s2label:
//IF NOT IS_CHAR_DEAD enemy_s2
//
//	IF playerseenstate_s2 = 0
//		IF IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_SOUND_QUIET
//		OR IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_ACQUAINTANCE_PED_HATE_BADLY_LIT
//			IF NOT IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_ACQUAINTANCE_PED_HATE
//				playerseenstate_s2 = 1
//				PRINT_NOW STP2_22 3000 1 //What was that?
//			ENDIF
//		ENDIF
//	ENDIF
//
//	IF playerseenstate_s2 = 1
//		IF NOT IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_SOUND_QUIET
//        AND NOT IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_ACQUAINTANCE_PED_HATE_BADLY_LIT
//        AND NOT IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_ACQUAINTANCE_PED_HATE
//		AND NOT IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_SHOT_FIRED
//		AND NOT IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_DAMAGE
//		AND NOT IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_GUN_AIMED_AT
//			PRINT_NOW STP2_23 3000 1 //Hmm..must of been nothing.
//			playerseenstate_s2 = 0
//		ENDIF
//	ENDIF
//
//	IF playerseenstate_s2 = 0 //KILL PLAYER
//	OR playerseenstate_s2 = 1
//		IF IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_ACQUAINTANCE_PED_HATE
//		OR IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_SHOT_FIRED
//		OR IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_DAMAGE
//		OR IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_GUN_AIMED_AT
//            PRINT_NOW (STP2_24) 3000 1 //Soldier: Hey you stop!
//			playerseenstate_s2 = 2
//        ENDIF
//	ENDIF
//
//	IF playerseenstate_s2 = 2  //RETURN FROM KILL PLAYER
//		IF NOT IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_SOUND_QUIET
//		AND NOT IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_ACQUAINTANCE_PED_HATE_BADLY_LIT
//		AND NOT IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_ACQUAINTANCE_PED_HATE
//		AND NOT IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_SHOT_FIRED
//		AND NOT IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_DAMAGE
//		AND NOT IS_CHAR_RESPONDING_TO_EVENT enemy_s2 EVENT_GUN_AIMED_AT
//			PRINT_NOW (MAF8_12) 3000 1 //Soldier: Shit, lost him!
//			playerseenstate_s2 = 0
//		ENDIF
//	ENDIF
//
//ENDIF
//
//RETURN
 



// Mission music2 failed
mission_music2_failed:
PRINT_BIG M_FAIL 5000 1
RETURN

   

// mission music2 passed

mission_music2_passed:
REGISTER_MISSION_PASSED STRAP_2
PRINT_WITH_NUMBER_BIG ( M_PASSR ) 4 5000 1 //"Mission Passed!"
AWARD_PLAYER_MISSION_RESPECT 4//amount of respect
CLEAR_WANTED_LEVEL player1
PLAY_MISSION_PASSED_TUNE 1
PLAYER_MADE_PROGRESS 1
flag_strap_mission_counter ++
RETURN
		
// mission cleanup

mission_cleanup_music2:
SET_SCRIPT_LIMIT_TO_GANG_SIZE 99
REMOVE_CHAR_ELEGANTLY ogloc_s2
REMOVE_PICKUP pickupgun_s2
SWITCH_ENTRY_EXIT maddogs FALSE
SWITCH_ENTRY_EXIT MDDOGS FALSE
IF IS_PLAYER_PLAYING PLAYER1
	SET_PLAYER_CYCLE_WEAPON_BUTTON PLAYER1 TRUE
	SET_PLAYER_GROUP_RECRUITMENT PLAYER1 TRUE
ENDIF

SET_WANTED_MULTIPLIER 1.0
//radar
SET_RADAR_ZOOM 0
//blips
SHUT_CHAR_UP scplayer FALSE
REMOVE_BLIP rhymebook_s2blip
REMOVE_BLIP guard1_s2blip
REMOVE_BLIP guard2_s2blip
REMOVE_BLIP guard3_s2blip
REMOVE_BLIP guard4_s2blip
REMOVE_BLIP guard5_s2blip
REMOVE_BLIP guard6_s2blip
REMOVE_BLIP guard7_s2blip
REMOVE_PICKUP rhymebook_s2
//models & animations
UNLOAD_SPECIAL_CHARACTER 1
REMOVE_ANIMATION GANGS
MARK_MODEL_AS_NO_LONGER_NEEDED MTBIKE
MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
MARK_MODEL_AS_NO_LONGER_NEEDED WBDYG1
MARK_MODEL_AS_NO_LONGER_NEEDED WBDYG2
MARK_MODEL_AS_NO_LONGER_NEEDED HUNTLEY
MARK_MODEL_AS_NO_LONGER_NEEDED STAFFORD
MARK_MODEL_AS_NO_LONGER_NEEDED KMB_RHYMESBOOK
MARK_MODEL_AS_NO_LONGER_NEEDED CJ_JUICE_CAN
MARK_MODEL_AS_NO_LONGER_NEEDED SILENCED
REMOVE_ANIMATION CRIB
REMOVE_ANIMATION VENDING
MARK_MODEL_AS_NO_LONGER_NEEDED KNIFECUR
flag_player_on_mission = 0
DISABLE_ALL_ENTRY_EXITS FALSE
GET_GAME_TIMER timer_mobile_start
MISSION_HAS_FINISHED
RETURN


}		





