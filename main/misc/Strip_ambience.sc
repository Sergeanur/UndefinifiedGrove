MISSION_START
//--- CONSTS
CONST_INT STRIP_MAX_SCRIPT_ATTRACTORS	6
CONST_INT STRIP_MAX_SCRIPT_PEDS			3
CONST_INT STRIP_NO_PLAYER_MONEY			0
CONST_INT STRIP_INIT_MONEY_THROW		1
CONST_INT STRIP_START_MONEY_ANIM		2
CONST_INT STRIP_CHECK_MONEY_THROW		3
CONST_INT STRIP_MONEY_READY				4
//-------------- GLOBALS ----------------------------------------------------------------------
VAR_INT iSetSTRIPPanic iPrintStripperHelp iThrowMoneyState
VAR_INT iLapDancesWatched // This is the number of sessions the player has been through (saved) 
VAR_INT iStripperPed iPlayerMoney
//----------------------------------------------------------------------------------------------

/*********************************************************************************************************************************************
************************************************						**********************************************************************
***********************************************				STRIP		  	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
STRIP_ambience:
	SCRIPT_NAME STRIP

//--- Parameters
 
//--- Internals
LVAR_INT iTemp  iRand iPrivateDone iPrintDoorHelp iLapDanceStatus iLapDancer iCamera_view//Misc stuff
LVAR_INT iIdle_BarKeeper_Task iServe_BarKeeper_Task iIdle_Bouncer_Task //TASKS				    
LVAR_INT iScript_Attractor[STRIP_MAX_SCRIPT_ATTRACTORS] // Local Attractors 
LVAR_INT iScriptedAttractorPed[STRIP_MAX_SCRIPT_PEDS] //PEDS
LVAR_FLOAT fX fY fZ fH fAnimEnd fPlayerAnimTime
LVAR_INT iAnimEnd iAnimEnd_StopCamera iPlayerTaskStatus	
LVAR_TEXT_LABEL16 iLapDanceType

//--- THE GLOBAL PANIC FLAG MUST BE SET TO ZERO WHEN ENTERING A SHOP & NOT ON A MISSION
IF flag_player_on_mission = 0
	iSetSTRIPPanic = 0
	iBouncerAction = 0
ENDIF

iTemp = 0
IF iTemp = 1
	//--- Thestripper returned by the attractor to throw money to
    CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 iStripperPed
	CREATE_OBJECT 0 0.0 0.0 0.0 iPlayerMoney
ENDIF

iRand = 1 // this is better set to 1
iThrowMoneyState = STRIP_NO_PLAYER_MONEY
iPrintStripperHelp = 0

 
/*******************************STRIP SETUP*******************************/

//--- Requests and loads
	GOSUB STRIP_StreamRequests

//--- The barkeeper's IDLE
	OPEN_SEQUENCE_TASK iIdle_BarKeeper_Task 
		TASK_PLAY_ANIM  -1 Barserve_in BAR 4.0 FALSE FALSE FALSE FALSE -1
		TASK_PLAY_ANIM  -1 Barserve_loop BAR 4.0 TRUE FALSE FALSE FALSE 10000
	CLOSE_SEQUENCE_TASK iIdle_BarKeeper_Task
//--- The barkeeper's Serve
	OPEN_SEQUENCE_TASK iServe_BarKeeper_Task 
		TASK_PLAY_ANIM  -1 Barserve_bottle BAR 4.0 FALSE FALSE FALSE FALSE -1 		
	CLOSE_SEQUENCE_TASK iServe_BarKeeper_Task
//--- The bouncer's IDLE
	OPEN_SEQUENCE_TASK iIdle_Bouncer_Task 
		TASK_WANDER_STANDARD -1 
	CLOSE_SEQUENCE_TASK iIdle_Bouncer_Task

	IF IS_PLAYER_PLAYING PLAYER1

		//--- Reset number of player's peds killed
		RESET_NUM_OF_MODELS_KILLED_BY_PLAYER PLAYER1

		GET_NAME_OF_ENTRY_EXIT_CHAR_USED scplayer txtString
		IF $txtString = &LASTRIP
			//--- Create the barman
			ADD_ATTRACTOR 1214.8589 -15.4637 999.9219 0.0 180.0 iIdle_BarKeeper_Task iScript_Attractor[0] // STRIP middle 1
			ADD_ATTRACTOR 1217.0845 -15.4657 999.9219 0.0 180.0 iIdle_BarKeeper_Task iScript_Attractor[1] // STRIP end 1
			ADD_ATTRACTOR 1213.2341 -15.4087 999.9219 0.0 180.0 iIdle_BarKeeper_Task iScript_Attractor[2] // STRIP end 2
			ADD_ATTRACTOR 1214.0851 -15.4395 999.9219 0.0 180.0 iServe_BarKeeper_Task iScript_Attractor[3] // STRIP drinks
			
			CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVFEMALE VWFYCRP iScript_Attractor[0] TASK_STAND_STILL iScriptedAttractorPed[0]
			//Pass: barman, bounds of bar attractors, drinks attractor, depth of bar, scan radius, bool skip_serve_glass_anim
			START_NEW_STREAMED_SCRIPT Bar_Staff.sc iScriptedAttractorPed[0] iScript_Attractor[0] iScript_Attractor[1] iScript_Attractor[2] iScript_Attractor[3] 2.5 15.0 TRUE
			STREAM_SCRIPT Bar_Staff.sc // this fudge is to mark the script as still needed	  

			//--- Create BOUNCER generators - script attractors
			ADD_ATTRACTOR 1220.0415 -15.3365 999.9219 341.7 -341.7 iIdle_Bouncer_Task iScript_Attractor[4]		
			ADD_ATTRACTOR 1222.0079 4.4324 999.9219 93.3 -93.3 iIdle_Bouncer_Task iScript_Attractor[5]
	
			CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVMALE BMYBOUN iScript_Attractor[4] TASK_WANDER_STANDARD iScriptedAttractorPed[1]
			CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVMALE WMYBOUN iScript_Attractor[5] TASK_WANDER_STANDARD iScriptedAttractorPed[2]
		
			START_NEW_STREAMED_SCRIPT Bouncer.sc iScriptedAttractorPed[1]
			START_NEW_STREAMED_SCRIPT Bouncer.sc iScriptedAttractorPed[2]
			STREAM_SCRIPT Bouncer.sc // this fudge is to mark the script as still needed	  

		ENDIF
		IF $txtString = &STRIP2
			//--- Create the barman
			iScript_Attractor[0] = -1 // middle
			ADD_ATTRACTOR 1206.2 -30.6012 999.9531 180.0 0.0 iIdle_BarKeeper_Task iScript_Attractor[1] // STRIP end 1
			//ADD_ATTRACTOR 1206.2 -28.4720 999.9531 270.0 180.0 iIdle_BarKeeper_Task iScript_Attractor[2] // STRIP end 2
			iScript_Attractor[2] = -1
			ADD_ATTRACTOR 1206.2 -29.1414 999.9531 270.0 0.0 iServe_BarKeeper_Task iScript_Attractor[3] // STRIP drinks
			
			CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVFEMALE VWFYCRP iScript_Attractor[1] TASK_STAND_STILL iScriptedAttractorPed[0]
			//Pass: barman, bounds of bar attractors, drinks attractor, depth of bar, scan radius, bool_skip_serve_glass_anim
			START_NEW_STREAMED_SCRIPT Bar_Staff.sc iScriptedAttractorPed[0] iScript_Attractor[1] iScript_Attractor[1] iScript_Attractor[1] iScript_Attractor[3] 1.5 15.0 TRUE
			STREAM_SCRIPT Bar_Staff.sc // this fudge is to mark the script as still needed	  

			//--- Create BOUNCER generators - script attractors
			ADD_ATTRACTOR 1216.8715 -24.1246 999.9531 146.8 -146.8 iIdle_Bouncer_Task iScript_Attractor[4]		
			iScript_Attractor[5] = -1

			CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVMALE BMYBOUN iScript_Attractor[4] TASK_WANDER_STANDARD iScriptedAttractorPed[1]
			iScriptedAttractorPed[2] = -1
			START_NEW_STREAMED_SCRIPT Bouncer.sc iScriptedAttractorPed[1]
			STREAM_SCRIPT Bouncer.sc // this fudge is to mark the script as still needed	  
								
		ENDIF
	ENDIF

/*******************************MAIN LOOP*******************************/
STRIP_Loop:	  		
	
	WAIT 0 

	IF IS_PLAYER_PLAYING PLAYER1

		GET_CHAR_AREA_VISIBLE scplayer iAreaCode
		IF iAreaCode = 0
		OR iTerminateAllAmbience = 1
			GOTO STRIP_Cleanup
		ENDIF

		IF iLapDanceStatus > 0
			GOSUB Strip_Lap_Dance
			//--- Do no more
			GOTO STRIP_Loop
		ENDIF
		//--- Player throwing money to stripper
		IF NOT iThrowMoneyState = STRIP_NO_PLAYER_MONEY
			IF NOT IS_CHAR_DEAD iStripperPed	
				GOSUB STRIP_PlayerThrowMoneyAnim
			ELSE								
				SET_PLAYER_CONTROL player1 ON
				iThrowMoneyState = STRIP_NO_PLAYER_MONEY 
				iStripperPed = -1
			ENDIF
		ENDIF

		//--- Peep Show
		IF $txtString = &LASTRIP
			IF iSetSTRIPPanic = 0
			AND iBouncerAction = 0
				IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer 1204.85 11.80 999.9219 1.0 1.0 1.0 TRUE
					IF IS_SCORE_GREATER player1 99 					
						IF iPrivateDone = 0
							iPrintDoorHelp = 1
							
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								ADD_SCORE player1 -100
								INCREMENT_FLOAT_STAT STRIP_CLUB_BUDGET 100.0
								iPrivateDone = 1
								iPrintDoorHelp = 0
								//--- Co-ords and heading of the chair
								fX = 1204.326 
								fY = 17.709 
								fZ = 998.925
								fH = 0.0
								DO_FADE 500 FADE_OUT
								iLapDanceStatus = 1
								GOSUB Strip_Lap_Dance								
							ENDIF
						ENDIF
					ELSE
						iPrintDoorHelp = 2 // not enough money for dance
					ENDIF
				ELSE
					iPrintDoorHelp = 0
				 	iPrivateDone = 0
				ENDIF
			ENDIF
		ENDIF

		IF $txtString = &STRIP2
			IF iSetSTRIPPanic = 0
			AND iBouncerAction = 0
				IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer 1207.5900 -40.6460 999.9531 1.0 1.0 1.0 TRUE					
					IF IS_SCORE_GREATER player1 99
						IF iPrivateDone = 0
							iPrintDoorHelp = 1
							
							IF IS_BUTTON_PRESSED PAD1 TRIANGLE
								ADD_SCORE player1 -100
								GET_FLOAT_STAT STRIP_CLUB_BUDGET fH
								fH += 100.0
								GET_FLOAT_STAT STRIP_CLUB_BUDGET fH
								iPrivateDone = 1
								iPrintDoorHelp = 0
								//--- Co-ords and heading of the chair
								fX = 1207.366    
								fY = -43.595 
								fZ = 998.925
								fH = 0.0
								DO_FADE 500 FADE_OUT
								iLapDanceStatus = 1
								GOSUB Strip_Lap_Dance								
							ENDIF
						ENDIF
					ELSE
						iPrintDoorHelp = 2
					ENDIF
				ELSE
					iPrintDoorHelp = 0
				 	iPrivateDone = 0
				ENDIF
			ENDIF
		ENDIF


		//--- Manages the prints on screen
		IF iSetSTRIPPanic = 0
		AND iBouncerAction = 0
			IF iPrintDoorHelp = 1
				PRINT_HELP_FOREVER STR_01  
			ELSE		
				IF iPrintDoorHelp = 2
					PRINT_HELP_FOREVER STR_03 // Private door - not enough money
				ELSE
					IF iPrintStripperHelp > 0		
						PRINT_HELP_FOREVER STR_00  
					ELSE
						IF iLapDanceStatus = 0 
							CLEAR_HELP
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ELSE
			CLEAR_HELP			
		ENDIF

		IF IS_CHAR_SHOOTING scplayer
			iSetSTRIPPanic = 1
			iBouncerAction = 1
		ENDIF

		IF IS_WANTED_LEVEL_GREATER PLAYER1 iWantedOnEntry
			iSetSTRIPPanic = 1
			iBouncerAction = 1
		ENDIF
		
		GET_TOTAL_NUMBER_OF_PEDS_KILLED_BY_PLAYER PLAYER1 iTemp
		IF iTemp > 0
			iBouncerAction = 1
		ENDIF

		REPEAT STRIP_MAX_SCRIPT_PEDS iTemp
			IF NOT IS_CHAR_DEAD iScriptedAttractorPed[iTemp]
				IF IS_PLAYER_TARGETTING_CHAR player1 iScriptedAttractorPed[iTemp]
				AND NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_ANYMELEE 
					iSetSTRIPPanic = 1 //Set the global panic flag
					iBouncerAction = 1
				ELSE
					IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR iScriptedAttractorPed[iTemp] scplayer
						iSetSTRIPPanic = 1 //Set the global panic flag
						iBouncerAction = 1
					ENDIF
				ENDIF
			ENDIF
		ENDREPEAT

		IF iSetSTRIPPanic = 1 // Public: Can be set by a mission
			iBouncerAction = 1			
			//--- Get all peds without a brain and give them a tiny script to cower
			GET_RANDOM_CHAR_IN_SPHERE_NO_BRAIN fX fY fZ 50.0 iTemp			
			IF iTemp > -1
				iRand += 1				 				
				START_NEW_STREAMED_SCRIPT Customer_Panic.sc iTemp iRand //Pass ped and random action
				STREAM_SCRIPT Customer_Panic.sc // this fudge is to mark the script as still needed	  
			ENDIF
		ENDIF

		IF iBouncerAction = 1	
			
			//--- Spawn more bouncers if needed
			IF IS_CHAR_DEAD iScriptedAttractorPed[1] 
			AND NOT iScript_Attractor[4] = -1

				IF $txtString = &LASTRIP
					fX = 1220.0415   
					fY = -15.3365 
					fZ = 999.9219
				ENDIF

				IF $txtString = &STRIP2
					fX = 1216.8715 
					fY = -24.1246 
					fZ = 999.9531
				ENDIF
				
				IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer fX fY fZ 8.0 8.0 8.0 FALSE
				AND NOT IS_POINT_ON_SCREEN fX fY fZ 3.0
					CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVMALE BMYBOUN iScript_Attractor[4] TASK_WANDER_STANDARD iScriptedAttractorPed[1]			
					START_NEW_STREAMED_SCRIPT Bouncer.sc iScriptedAttractorPed[1]
					STREAM_SCRIPT Bouncer.sc // this fudge is to mark the script as still needed	  
				ENDIF
			ENDIF

			IF IS_CHAR_DEAD iScriptedAttractorPed[2] 
			AND NOT iScript_Attractor[5] = -1

				IF $txtString = &LASTRIP
					fX = 1222.0079 
					fY = 4.4324 
					fZ = 999.9219
				ENDIF

				IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer fX fY fZ 8.0 8.0 8.0 FALSE
				AND NOT IS_POINT_ON_SCREEN fX fY fZ 3.0
					CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVMALE WMYBOUN iScript_Attractor[5] TASK_WANDER_STANDARD iScriptedAttractorPed[2]			
					START_NEW_STREAMED_SCRIPT Bouncer.sc iScriptedAttractorPed[2]
					STREAM_SCRIPT Bouncer.sc // this fudge is to mark the script as still needed	  
				ENDIF
			ENDIF

		ENDIF		 

	ENDIF
 			
GOTO STRIP_Loop 
/**************************END OF MAIN LOOP****************************************/


/*******************************************
			STREAM REQUESTS
********************************************/
STRIP_StreamRequests:

STRIP_StreamRequests_again:
	REQUEST_MODEL BMYBOUN
	REQUEST_MODEL WMYBOUN
	REQUEST_MODEL VWFYCRP
	REQUEST_ANIMATION BAR
	IF HAS_MODEL_LOADED BMYBOUN
	AND HAS_MODEL_LOADED WMYBOUN
	AND HAS_MODEL_LOADED VWFYCRP
	AND HAS_ANIMATION_LOADED BAR
		RETURN
	ELSE
		WAIT 0 
		GOTO STRIP_StreamRequests_again
	ENDIF

RETURN		
/*******************************STRIP CLEANUP*******************************/
STRIP_Cleanup:
    

	//--- Clean up the peds	 
	REPEAT STRIP_MAX_SCRIPT_PEDS iTemp		
		MARK_CHAR_AS_NO_LONGER_NEEDED iScriptedAttractorPed[iTemp]
		DELETE_CHAR iScriptedAttractorPed[iTemp]
	ENDREPEAT
	
	REPEAT STRIP_MAX_SCRIPT_ATTRACTORS iTemp
		IF NOT iScript_Attractor[iTemp] = -1 // unused or invalid attractor
			//--- Clean up the attractors
			CLEAR_ATTRACTOR iScript_Attractor[iTemp] 
		ENDIF
	ENDREPEAT

	MARK_MODEL_AS_NO_LONGER_NEEDED VWFYCRP
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYBOUN
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYBOUN
      
   //--- Clear the sequences
   CLEAR_SEQUENCE_TASK iIdle_BarKeeper_Task
   CLEAR_SEQUENCE_TASK iServe_BarKeeper_Task

   REMOVE_ANIMATION STRIP
   REMOVE_ANIMATION BAR

   iBouncerAction = 0 
   CLEAR_HELP

	IF IS_PLAYER_PLAYING PLAYER1		
		SET_PLAYER_CONTROL player1 ON
	ENDIF

TERMINATE_THIS_SCRIPT
/********************************************
			LAP DANCE 
********************************************/
Strip_Lap_Dance:  

	SWITCH iLapDanceStatus	 				
	
	CASE 1
		IF NOT GET_FADING_STATUS
			CLEAR_HELP
			SET_PLAYER_CONTROL player1 OFF			
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			DISPLAY_HUD FALSE
			DISPLAY_RADAR FALSE
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			SET_CHAR_COLLISION scplayer FALSE
			SET_CHAR_COORDINATES scplayer fX fY fZ 
			SET_CHAR_HEADING scplayer fH
			SET_CAMERA_IN_FRONT_OF_PLAYER
			//--- Request the Stripper (according to how many times player has seen the show)
			IF iLapDancesWatched = 0 
				iLapDancer = SBFYSTR
				$iLapDanceType = &LAPDAN1 
				++iLapDancesWatched
			ELSE
				IF iLapDancesWatched = 1
					iLapDancer = SWFYSTR
					$iLapDanceType = &LAPDAN2				
					++iLapDancesWatched
				ELSE
					iLapDancer = VHFYST3
					$iLapDanceType = &LAPDAN3
					iLapDancesWatched = 0
				ENDIF
			ENDIF 
			++iLapDanceStatus
		ENDIF
	BREAK

	CASE 2
		IF NOT HAS_ANIMATION_LOADED $iLapDanceType
			REQUEST_ANIMATION $iLapDanceType
		ELSE  
			IF NOT HAS_MODEL_LOADED iLapDancer
				REQUEST_MODEL iLapDancer
			ELSE
				//--- Position the stripper
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 -1.0 -1.0 fX fY fZ
				GET_CHAR_HEADING scplayer fH
				CREATE_CHAR PEDTYPE_CIVFEMALE iLapDancer fX fY fZ iLapDancer
				SET_CHAR_COLLISION iLapDancer FALSE 
				SET_CHAR_HEADING iLapDancer fH 												
				++iLapDanceStatus
			ENDIF
		ENDIF
	BREAK
	
	CASE 3
		TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer LAPDAN_P $iLapDanceType 1000.0 FALSE FALSE FALSE TRUE -1
		IF NOT IS_CHAR_DEAD iLapDancer
			TASK_PLAY_ANIM_NON_INTERRUPTABLE iLapDancer LAPDAN_D $iLapDanceType 1000.0 FALSE FALSE FALSE TRUE -1							
			iCamera_view = -1
			GOSUB STRIP_CameraViewChange
		ENDIF
		DO_FADE 1500 FADE_IN
		TIMERB = 0
		CLEAR_HELP
		++iLapDanceStatus
	BREAK

	CASE 4
		IF NOT GET_FADING_STATUS			
			IF NOT IS_CHAR_DEAD iLapDancer
				IF IS_CHAR_PLAYING_ANIM scplayer LAPDAN_P
					GET_CHAR_ANIM_TOTAL_TIME scplayer LAPDAN_P fAnimEnd
					iAnimEnd =# fAnimEnd					 
					iAnimEnd -= 800
					iAnimEnd_StopCamera = iAnimEnd - 2200  // no change 3 secs before end, to avoid clipping
				ELSE
					iAnimEnd = 0
				ENDIF 
				IF TIMERB > 2500
				AND TIMERB < 2600
					IF NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED STR_02
					    PRINT_HELP STR_02
					ENDIF
				ENDIF
				IF TIMERB >= iAnimEnd				 
					DO_FADE 500 FADE_OUT					
					++iLapDanceStatus
					BREAK
				ENDIF
				IF IS_BUTTON_PRESSED PAD1 CIRCLE
				AND TIMERA >= 500
				AND TIMERB > 2500
				AND TIMERB < iAnimEnd_StopCamera
					GOSUB STRIP_CameraViewChange
					TIMERA = 0 // to avoid multiple changes due to the button remaining down for a few frames
				ENDIF
				
				IF IS_BUTTON_PRESSED PAD1 TRIANGLE
					DO_FADE 500 FADE_OUT
					++iLapDanceStatus
					BREAK
				ENDIF
			ELSE			
				++iLapDanceStatus
			ENDIF
		ENDIF
	BREAK

	CASE 5
		IF NOT GET_FADING_STATUS
			IF NOT IS_CHAR_DEAD iLapDancer
				CLEAR_HELP
				GET_CHAR_MODEL iLapDancer iTemp
				MARK_MODEL_AS_NO_LONGER_NEEDED iTemp
				REMOVE_ANIMATION $iLapDanceType
				DELETE_CHAR iLapDancer
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
				DISPLAY_HUD TRUE
				DISPLAY_RADAR TRUE
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				SET_CHAR_COLLISION scplayer TRUE
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				IF $txtString = &LASTRIP 
					SET_CHAR_COORDINATES scplayer 1204.9907 12.6603 999.9219 
					SET_CHAR_HEADING scplayer 180.0				
				ENDIF
				IF $txtString = &STRIP2 
					SET_CHAR_COORDINATES scplayer 1207.5900 -40.6460 999.9531  
					SET_CHAR_HEADING scplayer 360.0 				
				ENDIF

	 			DO_FADE 500 FADE_IN
				++iLapDanceStatus
			ELSE
				++iLapDanceStatus
			ENDIF
		ENDIF		
	BREAK
		
	CASE 6
		IF NOT GET_FADING_STATUS
			SET_PLAYER_CONTROL player1 ON
			iLapDanceStatus = 0
			RETURN
		ENDIF
	BREAK
	ENDSWITCH
RETURN
/*******************************************
			CAMERA VIEW CHANGE
********************************************/
STRIP_CameraViewChange:

	SWITCH iCamera_view

		CASE -1			  
		   	IF $txtString = &LASTRIP 
				fX = 1204.5619
				fY = 15.6486
				fZ = 1001.3328
				SET_FIXED_CAMERA_POSITION fX fY fZ 0.0 0.0 0.0			
				fX = 1204.4366
				fY = 16.5805
				fZ = 1000.9924
				POINT_CAMERA_AT_POINT fX fY fZ JUMP_CUT
			ELSE
				fX = 1208.3197 	//1207.9777 
				fY = -45.8672 	//-46.1248 
				fZ = 1001.0659 	//1001.2154 
				SET_FIXED_CAMERA_POSITION fX fY fZ 0.0 0.0 0.0			
				fX = 1207.8466 	//1207.7568 
				fY = -44.9985 	//-45.1702 
				fZ = 1000.9191	//1001.0157
				POINT_CAMERA_AT_POINT fX fY fZ JUMP_CUT
			ENDIF
		BREAK

		CASE 0
		   	IF $txtString = &LASTRIP 
				fX = 1202.6536
				fY = 16.7123
				fZ = 1001.3015
				SET_FIXED_CAMERA_POSITION fX fY fZ 0.0 0.0 0.0			
				fX = 1203.5889
				fY = 16.9879
				fZ = 1001.0796
				POINT_CAMERA_AT_POINT fX fY fZ INTERPOLATION
			ELSE
				fX = 1205.6488 
				fY = -44.5486 
				fZ = 1001.3442 
				SET_FIXED_CAMERA_POSITION fX fY fZ 0.0 0.0 0.0			
				fX = 1206.5406 
				fY = -44.2188 
				fZ = 1001.0349 
				POINT_CAMERA_AT_POINT fX fY fZ INTERPOLATION
			ENDIF
		BREAK

		CASE 1
		   	IF $txtString = &LASTRIP 
				fX = 1203.5291
				fY = 18.3118
				fZ = 1001.3915
				SET_FIXED_CAMERA_POSITION fX fY fZ 0.0 0.0 0.0			
				fX = 1204.1101
				fY = 17.5609
				fZ = 1001.0779
				POINT_CAMERA_AT_POINT fX fY fZ INTERPOLATION
			ELSE
				fX = 1206.4344 
				fY = -43.1812 
				fZ = 1001.7045 
				SET_FIXED_CAMERA_POSITION fX fY fZ 0.0 0.0 0.0			
				fX = 1207.0441 
				fY = -43.7960 
				fZ = 1001.2042
				POINT_CAMERA_AT_POINT fX fY fZ INTERPOLATION
			ENDIF
		BREAK

		CASE 2
		   	IF $txtString = &LASTRIP 
				fX = 1204.7119 
				fY = 18.3221 
				fZ =1000.9409 
				SET_FIXED_CAMERA_POSITION fX fY fZ 0.0 0.0 0.0			
				fX = 1204.4094 
				fY = 17.3690 
				fZ = 1000.9495 
				POINT_CAMERA_AT_POINT fX fY fZ INTERPOLATION
			ELSE
				fX = 1207.4722 
				fY = -43.1042 
				fZ = 1001.0540 
				SET_FIXED_CAMERA_POSITION fX fY fZ 0.0 0.0 0.0			
				fX = 1207.3253 
				fY = -44.0841 
				fZ = 1000.9189 
				POINT_CAMERA_AT_POINT fX fY fZ INTERPOLATION
			ENDIF
		BREAK

		CASE 3
		   	IF $txtString = &LASTRIP 
				fX = 1205.8562 
				fY = 17.3141 
				fZ = 1002.2604 
				SET_FIXED_CAMERA_POSITION fX fY fZ 0.0 0.0 0.0			
				fX = 1205.0959 
				fY = 17.1228 
				fZ = 1001.6395 
				POINT_CAMERA_AT_POINT fX fY fZ INTERPOLATION
			ELSE
				fX = 1207.8461
				fY = -43.4954
				fZ = 1001.9645
				SET_FIXED_CAMERA_POSITION fX fY fZ 0.0 0.0 0.0			
				fX = 1207.4268
				fY = -43.9585
				fZ = 1001.1836
				POINT_CAMERA_AT_POINT fX fY fZ INTERPOLATION
			ENDIF
		BREAK

		DEFAULT
			iCamera_view = -1 // Loop around
		   	IF $txtString = &LASTRIP 
				fX = 1204.5619
				fY = 15.6486
				fZ = 1001.3328
				SET_FIXED_CAMERA_POSITION fX fY fZ 0.0 0.0 0.0			
				fX = 1204.4366
				fY = 16.5805
				fZ = 1000.9924
				POINT_CAMERA_AT_POINT fX fY fZ INTERPOLATION
			ELSE
				fX = 1207.9777 
				fY = -46.1248 
				fZ = 1001.2154 
				SET_FIXED_CAMERA_POSITION fX fY fZ 0.0 0.0 0.0			
				fX = 1207.7568 
				fY = -45.1702 
				fZ = 1001.0157
				POINT_CAMERA_AT_POINT fX fY fZ INTERPOLATION
			ENDIF
		BREAK		

	ENDSWITCH	
	++iCamera_view	
RETURN
/********************************************
		PLAYER THROW MONEY ANIM
********************************************/
STRIP_PlayerThrowMoneyAnim:

SWITCH iThrowMoneyState

	CASE STRIP_INIT_MONEY_THROW 
		SET_PLAYER_CONTROL player1 OFF
		TASK_TURN_CHAR_TO_FACE_CHAR scplayer iStripperPed 
		iThrowMoneyState = STRIP_START_MONEY_ANIM 	
	BREAK

	CASE STRIP_START_MONEY_ANIM
		GET_SCRIPT_TASK_STATUS scplayer TASK_TURN_CHAR_TO_FACE_CHAR iPlayerTaskStatus
		IF iPlayerTaskStatus = FINISHED_TASK			
			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer PLY_CASH STRIP 4.0 FALSE FALSE FALSE FALSE 0
			iThrowMoneyState = STRIP_CHECK_MONEY_THROW
		ENDIF		
	BREAK

	CASE STRIP_CHECK_MONEY_THROW
		IF IS_CHAR_PLAYING_ANIM scplayer PLY_CASH
			GET_CHAR_ANIM_CURRENT_TIME scplayer PLY_CASH fPlayerAnimTime
			IF fPlayerAnimTime >= 0.35
			AND fPlayerAnimTime < 1.0
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS iStripperPed 0.0 0.5 -0.5 fX fY fZ
				CREATE_OBJECT_NO_OFFSET Money fX fY fZ iPlayerMoney
				SET_OBJECT_COLLISION iPlayerMoney FALSE 
				ADD_SCORE player1 -20
				INCREMENT_FLOAT_STAT STRIP_CLUB_BUDGET 20.0	
				iThrowMoneyState = STRIP_MONEY_READY
			ENDIF
		ELSE
			iThrowMoneyState = STRIP_MONEY_READY 
		ENDIF 
	BREAK

	CASE STRIP_MONEY_READY
		IF IS_CHAR_PLAYING_ANIM scplayer PLY_CASH
			GET_CHAR_ANIM_CURRENT_TIME scplayer PLY_CASH fPlayerAnimTime
			IF fPlayerAnimTime >= 1.0
				SET_PLAYER_CONTROL player1 ON
				iThrowMoneyState = STRIP_NO_PLAYER_MONEY
			ENDIF
		ELSE
			SET_PLAYER_CONTROL player1 ON
			iThrowMoneyState = STRIP_NO_PLAYER_MONEY 			
		ENDIF					
	BREAK
	 
ENDSWITCH
RETURN
}
MISSION_END