MISSION_START
//--- CONSTS
CONST_INT BAR_MAX_SCRIPT_ATTRACTORS			4
CONST_INT BAR_MAX_SCRIPT_PEDS				2

CONST_INT BAR_DANCE_SCORE_TO_ADVANCE_TRACK1	3500
CONST_INT BAR_DANCE_SCORE_TO_ADVANCE_TRACK2	6000
CONST_INT BAR_DANCE_SCORE_TO_ADVANCE_TRACK3	7500

//-------------- GLOBALS ---------------------------
VAR_INT iSetBarPanic 
//---------------------------------------------------
/*********************************************************************************************************************************************
************************************************						**********************************************************************
***********************************************				BAR		  	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
BAR_ambience:
	SCRIPT_NAME BAR

//--- Parameters
 
//--- Internals
LVAR_INT iTemp iRand iDoNotLocateDance iRandomDance //Misc stuff
LVAR_INT iIdle_BarKeeper_Task iServe_BarKeeper_Task //TASKS				    
LVAR_INT iScript_Attractor[BAR_MAX_SCRIPT_ATTRACTORS] // Local Attractors 
LVAR_INT iScriptedAttractorPed[BAR_MAX_SCRIPT_PEDS] //PEDS
LVAR_FLOAT fX fY fZ
LVAR_INT iDanceOrder iPrintedScoreMessage

IF flag_player_on_mission = 0
AND NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS 
	//--- If player NOT mission and NOT on a date, reset the variables
	iSetBarPanic = 0
	iSwitchOffDanceMiniGame = 0
ENDIF

iTemp = 0
IF iTemp > 0
	CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 iDanceGirlfriend	
ENDIF

//--- Init the interals
iRand = 1 // this is better set to 1
iDoNotLocateDance = 0

//--- Init the dance order
GET_INT_STAT LATEST_DANCE_SCORE iTemp
IF iTemp >= BAR_DANCE_SCORE_TO_ADVANCE_TRACK3  
	iDanceOrder = 3 // Random track
ELSE
	iDanceOrder = 0
ENDIF

/*******************************BAR SETUP*******************************/

//--- Requests and loads
	GOSUB BAR_StreamRequests

//--- The barkeeper's IDLE
	OPEN_SEQUENCE_TASK iIdle_BarKeeper_Task 
		TASK_PLAY_ANIM  -1 Barserve_in BAR 4.0 FALSE FALSE FALSE FALSE -1
		TASK_PLAY_ANIM  -1 Barserve_loop BAR 4.0 TRUE FALSE FALSE FALSE 10000
	CLOSE_SEQUENCE_TASK iIdle_BarKeeper_Task
//--- The barkeeper's Serve
	OPEN_SEQUENCE_TASK iServe_BarKeeper_Task 
		TASK_PLAY_ANIM  -1 Barserve_bottle BAR 4.0 FALSE FALSE FALSE FALSE -1 		
	CLOSE_SEQUENCE_TASK iServe_BarKeeper_Task

	IF IS_PLAYER_PLAYING PLAYER1
		GET_NAME_OF_ENTRY_EXIT_CHAR_USED scplayer txtEntryExit
		IF $txtEntryExit = &BAR1
			ADD_ATTRACTOR 502.2242 -19.8799 999.6797 107.0 -107.0 iIdle_BarKeeper_Task iScript_Attractor[0] // bar middle 1
			ADD_ATTRACTOR 502.0665 -24.1488 999.6797 94.0 -94.0 iIdle_BarKeeper_Task iScript_Attractor[1] // bar end 1
			ADD_ATTRACTOR 502.0757 -17.2798 999.6719 85.0 -85.0 iIdle_BarKeeper_Task iScript_Attractor[2] // bar end 2
			ADD_ATTRACTOR 502.3116 -19.8236 999.6797 278.6 -278.6 iServe_BarKeeper_Task iScript_Attractor[3] // bar drinks
			
			CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVFEMALE VWFYCRP iScript_Attractor[0] TASK_STAND_STILL iScriptedAttractorPed[0]
			//Pass: barman, bounds of bar attractors, drinks attractor, depth of bar, scan radius, bool skip_serve_glass_anim
			START_NEW_STREAMED_SCRIPT BAR_Staff.sc iScriptedAttractorPed[0] iScript_Attractor[0] iScript_Attractor[1] iScript_Attractor[2] iScript_Attractor[3] 2.5 20.0
			STREAM_SCRIPT BAR_Staff.sc // this fudge is to mark the script as still needed
		ENDIF
		IF $txtEntryExit = &BAR2
			ADD_ATTRACTOR 497.9362 -78.0533 997.7651 1.0 -1.0 iIdle_BarKeeper_Task iScript_Attractor[0] // bar middle 1
			ADD_ATTRACTOR 493.9349 -77.9233 997.7651 100.5 -100.5 iIdle_BarKeeper_Task iScript_Attractor[1] // bar end 1
			ADD_ATTRACTOR 501.2002 -77.7390 997.7651 19.0 -19.0 iIdle_BarKeeper_Task iScript_Attractor[2] // bar end 2
			ADD_ATTRACTOR 496.5553 -78.7725 997.7651 194.5 -194.5 iServe_BarKeeper_Task iScript_Attractor[3] // bar drinks
			
			CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVFEMALE VWFYCRP iScript_Attractor[0] TASK_STAND_STILL iScriptedAttractorPed[0]
			START_NEW_STREAMED_SCRIPT BAR_Staff.sc iScriptedAttractorPed[0] iScript_Attractor[0] iScript_Attractor[1] iScript_Attractor[2] iScript_Attractor[3] 2.5 20.0
			STREAM_SCRIPT BAR_Staff.sc // this fudge is to mark the script as still needed
		ENDIF
		IF $txtEntryExit = &UFOBAR
			ADD_ATTRACTOR -223.2594 1405.6335 26.7656 86.9 262.0 iIdle_BarKeeper_Task iScript_Attractor[0] // bar middle 1
			ADD_ATTRACTOR -223.2600 1402.9843 26.7656 145.4 327.0 iIdle_BarKeeper_Task iScript_Attractor[1] // bar end 1
			ADD_ATTRACTOR -223.2956 1407.4860 26.7656 26.0 184.0 iIdle_BarKeeper_Task iScript_Attractor[2] // bar end 2
			ADD_ATTRACTOR -222.6826 1405.7870 26.7656 275.7 97.0 iServe_BarKeeper_Task iScript_Attractor[3] // bar drinks
			
			CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVFEMALE VWFYCRP iScript_Attractor[0] TASK_STAND_STILL iScriptedAttractorPed[0]
			START_NEW_STREAMED_SCRIPT BAR_Staff.sc iScriptedAttractorPed[0] iScript_Attractor[0] iScript_Attractor[1] iScript_Attractor[2] iScript_Attractor[3] 2.5 20.0
			STREAM_SCRIPT BAR_Staff.sc // this fudge is to mark the script as still needed
		ENDIF

	ENDIF

/*******************************MAIN LOOP*******************************/
BAR_Loop:	  		
	
	WAIT 0 

	IF IS_PLAYER_PLAYING PLAYER1

		GET_CHAR_AREA_VISIBLE scplayer iAreaCode 
		IF iAreaCode = 0
		OR iTerminateAllAmbience = 1
			GOTO BAR_Cleanup
		ENDIF
		//---------------------------------
		//--- DANCE MINI-GAME TRIGGER HERE
		//---------------------------------
		IF iSwitchOffDanceMiniGame = 0
		AND flag_player_on_mission = 0 
		AND iDoNotLocateDance = 0
			IF $txtEntryExit = &BAR1				
				IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer 488.0048 -14.0754 999.6797 1.5 1.5 1.5 TRUE	
					//--- The player has stepped into the marker and we are allowed to fire the mini-game
					CLEAR_HELP
					CLEAR_PRINTS
					//--- Check if we are on a date or not
					IF IS_BIT_SET iDateReport DATE_IN_PROGRESS
 						//--- Dance date, use the score saved in the statistics to determine the date
						GET_INT_STAT LATEST_DANCE_SCORE iTemp
						IF iTemp >= BAR_DANCE_SCORE_TO_ADVANCE_TRACK1
							iRandomDance = DANCE_TRACK_RUNNINGMAN
						ELSE
							IF iTemp >= BAR_DANCE_SCORE_TO_ADVANCE_TRACK2
								iRandomDance = DANCE_TRACK_WOP
							ELSE
								IF iTemp >= BAR_DANCE_SCORE_TO_ADVANCE_TRACK3
									GENERATE_RANDOM_INT_IN_RANGE DANCE_TRACK_LIST_START DANCE_TRACK_LIST_END iRandomDance
								ELSE
									iRandomDance = DANCE_TRACK_GFUNK_ALT	 
								ENDIF
							ENDIF
						ENDIF 						
 						START_NEW_STREAMED_SCRIPT Dance.sc 488.0048 -14.0754 999.6797 180.0 iRandomDance iDanceGirlfriend 			
						STREAM_SCRIPT Dance.sc
					ELSE
						//--- We are not on a date, so see what dance to trigger according to difficulty
						SWITCH iDanceOrder
							CASE 0
								iRandomDance = DANCE_TRACK_GFUNK_ALT 
							BREAK
							CASE 1
								iRandomDance = DANCE_TRACK_RUNNINGMAN
							BREAK
							CASE 2
								iRandomDance = DANCE_TRACK_WOP	
							BREAK
							CASE 3
								GENERATE_RANDOM_INT_IN_RANGE DANCE_TRACK_LIST_START DANCE_TRACK_LIST_END iRandomDance
							BREAK
						ENDSWITCH						
						START_NEW_STREAMED_SCRIPT Dance.sc 488.0048 -14.0754 999.6797 180.0 iRandomDance 0 
						STREAM_SCRIPT Dance.sc
					ENDIF
					iDoNotLocateDance = 1
					iPrintedScoreMessage = 0
				ENDIF
			ENDIF
		ELSE
			//--- Check if player has left the immediate vicinity, so we can re-check & render the marker
			IF iSwitchOffDanceMiniGame = 0
			AND flag_player_on_mission = 0
			AND iDoNotLocateDance = 1
				IF $txtEntryExit = &BAR1
					IF NOT LOCATE_CHAR_ON_FOOT_3D scplayer 488.0048 -14.0754 999.6797 2.5 2.5 2.5 FALSE
						//--- Player has left the marker area
						iDoNotLocateDance = 0
					ELSE 
						//--- If the player has reached or passed the PAR score, allow the next track to play
						IF NOT IS_MINIGAME_IN_PROGRESS 						
						AND NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS
						AND iPrintedScoreMessage = 0
							SWITCH iDanceOrder
								CASE 0
									IF iDanceScore >= BAR_DANCE_SCORE_TO_ADVANCE_TRACK1
										PRINT_BIG DNC_P1 5000 4 
										PRINT_NOW DNC_P2 10000 1 // Next track unlocked
										iPrintedScoreMessage = 1  
										++iDanceOrder
									ENDIF
								BREAK
								CASE 1
									IF iDanceScore >= BAR_DANCE_SCORE_TO_ADVANCE_TRACK2
										PRINT_BIG DNC_P1 5000 4 
										PRINT_NOW DNC_P2 10000 1 // Next track unlocked
										iPrintedScoreMessage = 1  
										++iDanceOrder
									ENDIF 						
								BREAK
								CASE 2
									IF iDanceScore >= BAR_DANCE_SCORE_TO_ADVANCE_TRACK3
										PRINT_BIG DNC_P1 5000 4
										PRINT_NOW DNC_P3 10000 1 // All tracks unlocked
										iPrintedScoreMessage = 1
										++iDanceOrder
									ENDIF 						
								BREAK
							ENDSWITCH
						ENDIF
					ENDIF
				ENDIF
			ENDIF 
		ENDIF
		//--------------------------
		//--- END OF DANCE MINI-GAME 
		//--------------------------

		IF IS_CHAR_SHOOTING scplayer
			iSetBarPanic = 1	
		ENDIF

		IF IS_WANTED_LEVEL_GREATER PLAYER1 iWantedOnEntry
			iSetBarPanic = 1
		ENDIF

		REPEAT BAR_MAX_SCRIPT_PEDS iTemp
			IF NOT IS_CHAR_DEAD iScriptedAttractorPed[iTemp]
				IF IS_PLAYER_TARGETTING_CHAR player1 iScriptedAttractorPed[iTemp]
				AND NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_ANYMELEE 
					iSetBarPanic = 1 //Set the global panic flag
				ELSE
					IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR iScriptedAttractorPed[iTemp] scplayer
						iSetBarPanic = 1 //Set the global panic flag
					ENDIF
				ENDIF
			ENDIF
		ENDREPEAT

		IF iSetBarPanic = 1 // Public: Can be set by a mission			
			//--- Disable minigame
			iSwitchOffDanceMiniGame = 1 
			//--- Get all peds without a brain and give them a tiny script to cower
			GET_RANDOM_CHAR_IN_SPHERE_NO_BRAIN fX fY fZ 50.0 iTemp			
			IF iTemp > -1
				iRand += 1				 				
				START_NEW_STREAMED_SCRIPT Customer_Panic.sc iTemp iRand //Pass ped and random action
				STREAM_SCRIPT Customer_Panic.sc // this fudge is to mark the script as still needed
			ENDIF
		ENDIF
	ENDIF
 			
GOTO BAR_Loop 
/**************************END OF MAIN LOOP****************************************/


/*******************************************
			STREAM REQUESTS
********************************************/
BAR_StreamRequests:

BAR_StreamRequests_again:
		
	REQUEST_MODEL VWFYCRP
	REQUEST_ANIMATION BAR
	REQUEST_ANIMATION DANCING

	IF HAS_MODEL_LOADED VWFYCRP
	AND HAS_ANIMATION_LOADED BAR
	AND HAS_ANIMATION_LOADED DANCING
		RETURN
	ELSE
		WAIT 0 
		GOTO BAR_StreamRequests_again
	ENDIF

RETURN
/*******************************BAR CLEANUP*******************************/
BAR_Cleanup:
	//--- Clean up the peds	 
	REPEAT BAR_MAX_SCRIPT_PEDS iTemp		
		MARK_CHAR_AS_NO_LONGER_NEEDED iScriptedAttractorPed[iTemp]
		DELETE_CHAR iScriptedAttractorPed[iTemp]
	ENDREPEAT
	
	REPEAT BAR_MAX_SCRIPT_ATTRACTORS iTemp
		IF NOT iScript_Attractor[iTemp] = -1 // unused or invalid attractor
			//--- Clean up the attractors
			CLEAR_ATTRACTOR iScript_Attractor[iTemp] 
		ENDIF
	ENDREPEAT

	MARK_MODEL_AS_NO_LONGER_NEEDED VWFYCRP

   //--- Clear the sequences
   CLEAR_SEQUENCE_TASK iIdle_BarKeeper_Task
   CLEAR_SEQUENCE_TASK iServe_BarKeeper_Task

   REMOVE_ANIMATION BAR
   REMOVE_ANIMATION	DANCING

TERMINATE_THIS_SCRIPT
}
MISSION_END