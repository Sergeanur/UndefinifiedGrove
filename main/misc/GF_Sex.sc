MISSION_START

//--- Spanking Powerbar Margins
CONST_INT GF_POWERBAR_CHARGE_HIT_MARGIN 	97 //%
//--- Spanking Powerbar Increments
CONST_INT GF_POWERBAR_DECREMENT_SMALL		3
CONST_INT GF_POWERBAR_DECREMENT 			7
CONST_INT GF_EXCITEMENT_SPANK_INCREMENT		10
CONST_INT GF_EXCITEMENT_SPANK_DECREMENT		10
CONST_FLOAT POWER_SIN_SCALE_FACTOR 			13.5
/*****************************************************************************************************************************************
************************************************					**********************************************************************
************************************************	SEX MINIGAME	**********************************************************************
************************************************					**********************************************************************
*****************************************************************************************************************************************/
/* TO DO ON THIS SCRIPT:
------------------------
*/
{
GF_Sex:		  
    SCRIPT_NAME GFSEX

	SET_MINIGAME_IN_PROGRESS TRUE

    LVAR_INT  iGFIdx_par iSpanking iGFHomeArea 			 		   	// Parameters arriving from caller
	LVAR_FLOAT fGFHomeX fGFHomeY fGFHomeZ fGHHomeHeading
    LVAR_INT iSexState iSubStateStatus iSexMachineFlags	 			// State Machine variables
	LVAR_FLOAT fX[2] fY[2] fZ[2] fTemp[2] fAngle fSin   
	LVAR_INT iTemp iTemp2 shaggin_position	iCamera_View iGF_ped iCar

	//--- Init the vars			
	iSexState = 0 
	iSubStateStatus = 0

	IF iSpanking > 0
		excitement = 40	// Allowed values: 0, 30, 40, 50, 70, 90
	ELSE
		excitement = 50	// Allowed values: 0, 30, 40, 50, 70, 90
	ENDIF
	power = 1

	iSexMachineFlags = 0  
	/* BITS:	1 	// Skip Transitions
				2 	// Is CIRCLE Pad Button Being Depressed
				3 	// Is SQUARE Pad Button Being Depressed
				4 	// Is TRIANGLE Pad Button Being Depressed
				5 	// End of Sex Animation Loop Reached
				6 	// Increase Excitement X 2
				7 	// Increase Excitement X 3
				8 	// Is CROSS Pad Button being Depressed
				9	// Power counter on screen
				10 	// SPANKING CHARGE PERFECT!
				11 	// SPANKING RELEASE PERFECT!
				12 	// SPANKING RELEASE GOOD!
				13	// Excitement counter on screen
				14  // POWER_BAR_TOP_REACHED	
	*/

	//--- Parameter passing Fudge
	IF iSexMachineFlags > 0
	   CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 iGF_ped
	ENDIF
	
	
	//--- Streaming requests   	 
	GOSUB GF_Sex_SteamGFModel // Uses iGFidx to retrieve the proper sexy version
	IF iCensoredVersion = 0
		GOSUB GF_Sex_SteamAnims
	ENDIF
	 
	//---MAIN LOOP---
GF_Sex_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB GF_Sex_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF 	NOT IS_CHAR_DEAD scplayer
   	AND	NOT IS_CHAR_DEAD iGF_ped
   	AND	NOT iGFLikesPlayer[iGFIdx_par] = GF_IS_DEAD
	AND	NOT iGFLikesPlayer[iGFIdx_par] = GF_HATES_PLAYER
	AND	NOT iGFLikesPlayer[iGFIdx_par] = GF_DUMP_PLAYER_IMMEDIATELY

			//--- Prossess all the Transitions	
			GOSUB GF_Sex_GetCurrentState
	 
			//--- Go into the appopriate Animation State
			GOSUB GF_Sex_DoCurrentState

			//--- Call the speech for the girlfriend, according to what has been requested 
			GOSUB GF_Sex_SpeechManager

	ELSE
		GOSUB GF_Sex_CleanUp
	ENDIF

GOTO GF_Sex_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
				TRANSITIONS
********************************************/
GF_Sex_GetCurrentState:

//--- See if we are currently in a state that allows transitions
IF NOT IS_BIT_SET iSexMachineFlags 1 	

	//--- Set up the censored verison of the script	(just a cut-scene over the house)
	IF iCensoredVersion = 1
		SET_BIT iSexMachineFlags 1
		iSexState = 9
		iSubStateStatus = 0
		RETURN
	ENDIF

	//--- PAD CONTROLLED TRANSITIONS
	
	IF IS_BUTTON_PRESSED PAD1 CIRCLE		
		//--- Camera View Change
		IF NOT IS_BIT_SET iSexMachineFlags 2 // Is CIRCLE Button Down
			GOSUB GF_Sex_CameraViewChange
			SET_BIT iSexMachineFlags 2 
		ENDIF
	ELSE
		CLEAR_BIT iSexMachineFlags 2
	ENDIF

	IF IS_BUTTON_PRESSED PAD1 SQUARE		
		//--- Sex Position Change
		IF NOT IS_BIT_SET iSexMachineFlags 3 // Is SQUARE Button Down
			GOSUB GF_Sex_PositionChange
			SET_BIT iSexMachineFlags 3 
		ENDIF
	ELSE
		CLEAR_BIT iSexMachineFlags 3
	ENDIF

	IF IS_BUTTON_PRESSED PAD1 TRIANGLE		
		//--- QUIT
		IF NOT IS_BIT_SET iSexMachineFlags 4 // Is TRIANGLE Button Down
			iSexState = 2
			iSubStateStatus = 0
			SET_BIT iSexMachineFlags 4 
		ENDIF
	ELSE
		CLEAR_BIT iSexMachineFlags 4
	ENDIF
		
ENDIF
 
RETURN
/********************************************
				SEX STATES
********************************************/
GF_Sex_DoCurrentState:
	SWITCH iSexState	   	
   		CASE 0 //--- FADE AND INIT
			GOSUB GF_Sex_State0
		BREAK
		CASE 1 //--- INTRO SEATED BLOWJOB
			GOSUB GF_Sex_State1
		BREAK
		CASE 2
			//--- TOTAL FADE AND END
			GOSUB GF_Sex_State2
		BREAK
		CASE 3	//--- SPANKING
		   GOSUB GF_Sex_State3
		BREAK
		CASE 4	//--- END SPANK BAD
		   GOSUB GF_Sex_State4
		BREAK
		CASE 5	//--- END SPANK GOOD
		   GOSUB GF_Sex_State5
		BREAK
		CASE 6	//--- PROPER SEX
		   GOSUB GF_Sex_State6
		BREAK
		CASE 7	//--- ALTERNATIVE INTRO: STANDING BLOWJOB
		   GOSUB GF_Sex_State7
		BREAK
		CASE 8	//--- END SEX BAD
		   GOSUB GF_Sex_State8
		BREAK
		CASE 9 //--- CENSORED VERSION: CUT-SCENE
			GOSUB GF_Sex_State9	
		BREAK
	ENDSWITCH


RETURN
/********************************************
			STATE 0: 
********************************************/
GF_Sex_State0: 
	SWITCH iSubStateStatus	 				

	CASE 0
		SET_BIT iSexMachineFlags 1 // Mark this state as non transitable
		//--- Fade to black
		DO_FADE 0 FADE_OUT
		++iSubStateStatus
	BREAK
	CASE 1
		IF NOT GET_FADING_STATUS
			SET_PLAYER_CONTROL player1 OFF
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE iGF_ped TRUE
			DISPLAY_RADAR FALSE
			SET_EVERYONE_IGNORE_PLAYER player1 TRUE
			SWITCH_WIDESCREEN ON
			SET_ALL_CARS_CAN_BE_DAMAGED FALSE 
			 			
			GOSUB GF_Sex_LoadGirlHouse // Returns in iTemp2 the STATE with the Intro to launch for this GF	
			//--- Init Finished			
			iSexState = iTemp2
			iSubStateStatus = 0
		ENDIF
	BREAK
	ENDSWITCH
RETURN
/********************************************
			STATE 1: INTRO 
********************************************/
GF_Sex_State1:  
	SWITCH iSubStateStatus	 				

	CASE 0
		SET_BIT iSexMachineFlags 1
		
		//--- Set the special 'Blowjob' Camera
		iCamera_view = -1
		GOSUB GF_Sex_CameraViewChange
		
		DO_FADE 1500 FADE_IN				

  		//--- Move On To State 1
		++iSubStateStatus
	BREAK
	
	CASE 1
		IF NOT GET_FADING_STATUS	   
			
			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer BJ_COUCH_START_P BLOWJOBZ 4.0 FALSE FALSE FALSE TRUE 0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped BJ_COUCH_START_W BLOWJOBZ 4.0 FALSE FALSE FALSE TRUE 0

			++iSubStateStatus
		ENDIF		
	BREAK

	CASE 2
		GET_CHAR_ANIM_CURRENT_TIME scplayer BJ_COUCH_START_P fTemp[0]

		IF fTemp[0] > 0.5
		AND fTemp[0] < 1.0
			
			IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
				DO_FADE	500 FADE_OUT	
				iSubStateStatus = 5
				BREAK
			ENDIF
		ENDIF

		IF fTemp[0] = 1.0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer BJ_COUCH_LOOP_P BLOWJOBZ 4.0 TRUE FALSE FALSE TRUE 6000
			TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped  BJ_COUCH_LOOP_W BLOWJOBZ 4.0 TRUE FALSE FALSE TRUE 6000 
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_HEAD
			TIMERB = 0
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 3
		
		IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			DO_FADE	500 FADE_OUT	
			iSubStateStatus = 5
		ELSE		
			IF TIMERB > 6000		
				TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer BJ_COUCH_END_P BLOWJOBZ 4.0 FALSE FALSE FALSE FALSE 0
				TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped BJ_COUCH_END_W BLOWJOBZ 4.0 FALSE FALSE FALSE FALSE 0			
			  	++iSubStateStatus
			ENDIF
		ENDIF
	BREAK

	CASE 4
		GET_SCRIPT_TASK_STATUS iGF_ped TASK_PLAY_ANIM_NON_INTERRUPTABLE iTemp				
		IF iTemp = FINISHED_TASK
			DO_FADE	500 FADE_OUT
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 5
		IF NOT GET_FADING_STATUS											
			//--- State Completed - move on to the appropriate sex game
			IF iSpanking > 0
				iSexState = 3
				iSubStateStatus = 0				
			ELSE
				iSexState = 6
				iSubStateStatus = 0									
			ENDIF 
		ENDIF
	BREAK
	ENDSWITCH		
RETURN
/********************************************
			STATE 2: 
********************************************/
GF_Sex_State2: 
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Fade to black
		DO_FADE 1000 FADE_OUT
		++iSubStateStatus
	BREAK
	CASE 1
		IF NOT GET_FADING_STATUS
			
			GOSUB GF_Sex_LeaveGirlHouse

			IF IS_BIT_SET iSexMachineFlags 13	// Excitement counter on screen
				CLEAR_ONSCREEN_COUNTER excitement
				CLEAR_BIT iSexMachineFlags 13 
			ENDIF
			
			IF IS_BIT_SET iSexMachineFlags 9	// Power counter on screen
				CLEAR_ONSCREEN_COUNTER power
				CLEAR_BIT iSexMachineFlags 9
			ENDIF

			CLEAR_HELP			
			SWITCH_WIDESCREEN OFF
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			SET_PLAYER_CONTROL player1 ON 		
			SET_EVERYONE_IGNORE_PLAYER player1 FALSE			 						

			//--- Remove GIMP SUIT if it's on
			IF IS_PLAYER_WEARING Player1 CLOTHES_TEX_EXTRA1 gimpleg 
				GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
				BUILD_PLAYER_MODEL player1
			ENDIF

			//--- Fade back to game
			DO_FADE 1000 FADE_IN
			++iSubStateStatus
		ENDIF
	BREAK
	CASE 2
		IF NOT GET_FADING_STATUS			
			//--- Out Of House Finished
			GOSUB GF_Sex_CleanUp
		ENDIF
	BREAK
	ENDSWITCH
RETURN
/********************************************
			STATE 3: SPANKING
********************************************/
GF_Sex_State3: 
	SWITCH iSubStateStatus	 				

	CASE 0
		IF NOT GET_FADING_STATUS
			SWITCH_WIDESCREEN OFF

			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			CLEAR_CHAR_TASKS_IMMEDIATELY iGF_ped
			
			GOSUB GF_Sex_GetCoordsShag // returns in arrays XYZ, heading in fTemp[0] 

			SET_CHAR_COORDINATES scplayer fX[0] fY[0] fZ[0]
			SET_CHAR_HEADING scplayer fTemp[0]

			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 0.5 -1.0 fX[0] fY[0] fZ[0]
			GET_CHAR_HEADING scplayer fTemp[0]

			SET_CHAR_COORDINATES iGF_ped fX[0] fY[0] fZ[0]
			SET_CHAR_HEADING iGF_ped fTemp[0]
			
			PRINT_HELP_FOREVER GF_0043

			DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING excitement COUNTER_DISPLAY_BAR 1 GF_0017 //Excitement:
			DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING power COUNTER_DISPLAY_BAR	3 GF_0040 //Power:
			SET_BIT iSexMachineFlags 9	// Power counter on screen
			SET_BIT iSexMachineFlags 13	// Excitement counter on screen

			//--- Start with a different Camera than the shag
			iCamera_view = 1
			GOSUB GF_Sex_CameraViewChange
						  			 
			DO_FADE	1000 FADE_IN
			
			CLEAR_BIT iSexMachineFlags 1
						 
			++iSubStateStatus	
		ENDIF
	BREAK
	
	CASE 1		
		TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped Spanking_IdleW SNM 4.0 TRUE FALSE FALSE FALSE 0
		TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer Spanking_IdleP SNM 4.0 TRUE FALSE FALSE FALSE 0				
	
		CLEAR_BIT iSexMachineFlags 8

		GOSUB GF_Sex_UpdatePowerBar

		//--- Check excitement bounds
		IF excitement <= 0
			//--- Move on to the BAD ending
			iSexState =	4
			iSubStateStatus = 0
			BREAK
		ELSE 
			IF excitement >= 100
				//--- Move on to the GOOD ending
				iSexState =	5
				iSubStateStatus = 0
				BREAK
			ENDIF
		ENDIF

		++iSubStateStatus
	BREAK

	CASE 2		
		GOSUB GF_Sex_UpdatePowerBar

		IF power >= 100
			++iSubStateStatus
		ENDIF

		IF IS_BUTTON_PRESSED PAD1 CROSS				
			IF NOT IS_BIT_SET iSexMachineFlags 8 // Is CROSS Button Down
			AND NOT IS_CHAR_PLAYING_ANIM scplayer SpankingP
				SET_BIT iSexMachineFlags 8
				GOSUB GF_Sex_RegisterPowerBarScore
				iSubStateStatus = 4
				BREAK
			ENDIF
		ELSE
			CLEAR_BIT iSexMachineFlags 8			
		ENDIF

		//--- Check excitement bounds
		IF excitement <= 0
			//--- Move on to the BAD ending
			iSexState =	4
			iSubStateStatus = 0
			BREAK
		ELSE 
			IF excitement = 100
				//--- Move on to the GOOD ending
				iSexState =	5
				iSubStateStatus = 0
				BREAK
			ENDIF
		ENDIF
		
	BREAK

	CASE 3		
		GOSUB GF_Sex_UpdatePowerBar		

		IF power >= GF_POWERBAR_CHARGE_HIT_MARGIN
			IF IS_BUTTON_PRESSED PAD1 CROSS				
				IF NOT IS_BIT_SET iSexMachineFlags 8 // Is CROSS Button Down
				AND NOT IS_CHAR_PLAYING_ANIM scplayer SpankingP
					SET_BIT iSexMachineFlags 8
					GOSUB GF_Sex_RegisterPowerBarScore
					iSubStateStatus = 4
					BREAK
				ENDIF
			ELSE
				CLEAR_BIT iSexMachineFlags 8			
			ENDIF
		ENDIF

		IF power <= 0			
			GOSUB GF_Sex_DecrementExcitement
			--iSubStateStatus
		ENDIF

		//--- Check excitement bounds
		IF excitement <= 0
			//--- Move on to the BAD ending
			iSexState =	4
			iSubStateStatus = 0
			BREAK
		ELSE 
			IF excitement = 100
				//--- Move on to the GOOD ending
				iSexState =	5
				iSubStateStatus = 0
				BREAK
			ENDIF
		ENDIF
			
	BREAK

	CASE 4
		//--- Start to play a 'slap'
		GOSUB GF_Sex_UpdatePowerBar

		GOSUB GF_Sex_ComputeAnimSpeedForPower // Returns: fTemp[0]

		TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped SpankingW SNM 99.0 FALSE FALSE FALSE TRUE 0
		TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer SpankingP SNM 99.0 FALSE FALSE FALSE TRUE 0

		CLEAR_BIT iSexMachineFlags 5 // Sex anim start 
		
		++iSubStateStatus
	BREAK
	
	CASE 5
		//--- Continue to play a 'slap' until end of anim is reached
		GOSUB GF_Sex_UpdatePowerBar
		GOSUB GF_Sex_SpankAtGivenSpeed // Reads: fTemp[0], returns end of anim by setting iSexMachineFlags 5  		
 		IF IS_BIT_SET iSexMachineFlags 5
			iCJSayContext = CONTEXT_GLOBAL_SPANKING
			GOSUB GF_Sex_ComputeExcitementFromPower			
			iSubStateStatus = 1	 // Loop back
		ENDIF
	BREAK
	ENDSWITCH	
RETURN
/********************************************
		STATE 4: SPANKING END BAD
********************************************/
GF_Sex_State4: 
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Remove counters if present	
		IF IS_BIT_SET iSexMachineFlags 13	// Excitement counter on screen
			CLEAR_ONSCREEN_COUNTER excitement
			CLEAR_BIT iSexMachineFlags 13 
		ENDIF
		
		IF IS_BIT_SET iSexMachineFlags 9	// Power counter on screen
			CLEAR_ONSCREEN_COUNTER power
			CLEAR_BIT iSexMachineFlags 9
		ENDIF

		CLEAR_HELP 

		DO_FADE 1000 FADE_OUT
		++iSubStateStatus
	BREAK

	CASE 1
		IF NOT GET_FADING_STATUS

			GOSUB GF_Sex_GetCoordsShag // returns in arrays XYZ 
			
			SET_CHAR_COORDINATES scplayer fX[0] fY[0] fZ[0]
			SET_CHAR_HEADING scplayer fTemp[0]

			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 0.5 -1.0 fX[0] fY[0] fZ[0]
			GET_CHAR_HEADING scplayer fTemp[0]

			SET_CHAR_COORDINATES iGF_ped fX[0] fY[0] fZ[0]
			SET_CHAR_HEADING iGF_ped fTemp[0]

			TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped SpankedW SNM 4.0 TRUE FALSE FALSE TRUE 600000
			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer SpankedP SNM 4.0 TRUE FALSE FALSE TRUE 600000

			DO_FADE 1000 FADE_IN
			TIMERB = 0
			++iSubStateStatus		
		ENDIF
	BREAK

	CASE 2
		IF NOT GET_FADING_STATUS

			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_SEX_BAD
			iCJSayContext = CONTEXT_GLOBAL_SPANKED

			IF TIMERB > 7000
				//--- The END
		
				//--- Mark the sex as bad		  
				CLEAR_BIT iDateReport SEX_WAS_GOOD
				//--- Move on to the end and cleanup
				iSexState = 2
				iSubStateStatus = 0				
			ENDIF
		ENDIF
	BREAK

	ENDSWITCH		
RETURN
/********************************************
		STATE 5: SPANKING END GOOD
********************************************/
GF_Sex_State5: 
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Remove counters if present	
		IF IS_BIT_SET iSexMachineFlags 13	// Excitement counter on screen
			CLEAR_ONSCREEN_COUNTER excitement
			CLEAR_BIT iSexMachineFlags 13 
		ENDIF
		
		IF IS_BIT_SET iSexMachineFlags 9	// Power counter on screen
			CLEAR_ONSCREEN_COUNTER power
			CLEAR_BIT iSexMachineFlags 9
		ENDIF

		CLEAR_HELP 

		DO_FADE 1000 FADE_OUT
		++iSubStateStatus
	BREAK

	CASE 1
		IF NOT GET_FADING_STATUS

			GOSUB GF_Sex_GetCoordsShag // returns in arrays XYZ 
			
			SET_CHAR_COORDINATES scplayer fX[0] fY[0] fZ[0]
			SET_CHAR_HEADING scplayer fTemp[0]

			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 0.5 -1.0 fX[0] fY[0] fZ[0]
			GET_CHAR_HEADING scplayer fTemp[0]

			SET_CHAR_COORDINATES iGF_ped fX[0] fY[0] fZ[0]
			SET_CHAR_HEADING iGF_ped fTemp[0]
			
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.707 3.136 -0.163 fX[0] fY[0] fZ[0]
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.427 0.634 -0.5 fX[1] fY[1] fZ[1]
			SET_FIXED_CAMERA_POSITION fX[0] fY[0] fZ[0] 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT fX[1] fY[1] fZ[1] JUMP_CUT

			TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped Spanking_endW SNM 4.0 FALSE FALSE FALSE TRUE 0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer Spanking_endP SNM 4.0 FALSE FALSE FALSE TRUE 0

			DO_FADE 1000 FADE_IN
			
			++iSubStateStatus		
		ENDIF
	BREAK

	CASE 2
		IF NOT GET_FADING_STATUS
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_SEX_GOOD
			TIMERB = 0
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 3
		GET_CHAR_ANIM_CURRENT_TIME scplayer Spanking_endP fTemp[0]
		IF fTemp[0] = 1.0		
			//--- Mark the sex as good
			SET_BIT iDateReport SEX_WAS_GOOD 
		
			//--- Move on to the end and cleanup
			iSexState = 2
			iSubStateStatus = 0				
		ENDIF		
	BREAK

	ENDSWITCH
RETURN  
/********************************************
			STATE 6: PROPER SEX 
********************************************/
GF_Sex_State6: 
	SWITCH iSubStateStatus

	CASE 0
		IF NOT GET_FADING_STATUS
			
			SWITCH_WIDESCREEN OFF

			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			CLEAR_CHAR_TASKS_IMMEDIATELY iGF_ped
			
			GOSUB GF_Sex_GetCoordsShag // returns in arrays XYZ 

			SET_CHAR_COORDINATES scplayer fX[0] fY[0] fZ[0]
			SET_CHAR_HEADING scplayer fTemp[0]

			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 1.0 -1.0 fX[0] fY[0] fZ[0]
			GET_CHAR_HEADING scplayer fTemp[0]
			fTemp[0] += 180.0

			SET_CHAR_COORDINATES iGF_ped fX[0] fY[0] fZ[0]
			SET_CHAR_HEADING iGF_ped fTemp[0]

			DISPLAY_ONSCREEN_COUNTER_WITH_STRING excitement	COUNTER_DISPLAY_BAR	GF_0017 //iGF_excitement:
			SET_BIT iSexMachineFlags 13	// Excitement counter on screen
			
			GOSUB GF_Sex_CameraViewChange
						
			PRINT_HELP_FOREVER GF_0020  
			  			 
			DO_FADE	500 FADE_IN
			
			CLEAR_BIT iSexMachineFlags 1
			++iSubStateStatus	
		ENDIF
	BREAK
	
	CASE 1
		TIMERB = 0 // Reset the timer 

		IF shaggin_position = 0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped SEX_1_W SEX 4.0 TRUE FALSE FALSE FALSE 0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer SEX_1_P SEX 4.0 TRUE FALSE FALSE FALSE 0
    
			++iSubStateStatus
		ENDIF

		IF shaggin_position = 1
			IF IS_CHAR_PLAYING_ANIM scplayer SEX_1to2_P
				GET_CHAR_ANIM_CURRENT_TIME scplayer SEX_1to2_P fTemp[0]
				IF fTemp[0] >= 1.0
					TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped SEX_2_W SEX 4.0 TRUE FALSE FALSE FALSE 0
					TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer SEX_2_P SEX 4.0 TRUE FALSE FALSE FALSE 0
				ENDIF																				  
			ELSE
				TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped SEX_2_W SEX 4.0 TRUE FALSE FALSE FALSE 0
				TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer SEX_2_P SEX 4.0 TRUE FALSE FALSE FALSE 0
			ENDIF
			++iSubStateStatus
		ENDIF

		IF shaggin_position = 2
			IF IS_CHAR_PLAYING_ANIM scplayer SEX_2to3_P
				GET_CHAR_ANIM_CURRENT_TIME scplayer SEX_2to3_P fTemp[0]
				IF fTemp[0] >= 1.0
					TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped SEX_3_W SEX 4.0 TRUE FALSE FALSE FALSE 0
					TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer SEX_3_P SEX 4.0 TRUE FALSE FALSE FALSE 0
				ENDIF																				  
			ELSE			
				TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped SEX_3_W SEX 4.0 TRUE FALSE FALSE FALSE 0
				TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer SEX_3_P SEX 4.0 TRUE FALSE FALSE FALSE 0
			ENDIF
			++iSubStateStatus
		ENDIF			

	BREAK

	CASE 2
		//--- Play Shag loop 

		GOSUB GF_Sex_ComputeAnimSpeedForExcitement // Returns a valid animation speed in fTemp[0]
				
		GOSUB GF_Sex_LoopSexAtGivenSpeed //Uses TIMERB and fTemp[0] - Fills in the excitement meter
	 			
		//--- See if the girl had enough plasure...
		IF excitement = 100
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_CLIMAX_HIGH
			SET_PLAYER_CONTROL player1 OFF
			SET_BIT iSexMachineFlags 1 // Set the next state as NON TRANSITABLE
			SET_BIT iDateReport SEX_WAS_GOOD // Mark SEX as Good for the Dating Agent			
			++iSubStateStatus
		ENDIF

		IF excitement = 0 // Your excitement meter has dropped
			//--- Mark that sex was bad
			CLEAR_BIT iDateReport SEX_WAS_GOOD 
			//--- Botched sex 
			CLEAR_HELP			
			iSexState = 8
			iSubStateStatus = 0
		ENDIF
		
	BREAK

	CASE 3   	
		IF shaggin_position = 0
			++iSubStateStatus		
		ENDIF

	   	IF shaggin_position = 1
			GOSUB GF_Sex_CameraViewChange
			shaggin_position = 0
			++iSubStateStatus
		ENDIF

		IF shaggin_position = 2
			GOSUB GF_Sex_CameraViewChange
			shaggin_position = 0
			++iSubStateStatus
		ENDIF

	BREAK

	CASE 4
	
		TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped SEX_1_Cum_W SEX 4.0 FALSE FALSE FALSE TRUE 0
		TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer SEX_1_Cum_P SEX 4.0 FALSE FALSE FALSE TRUE 0
		iGFSayContext = CONTEXT_GLOBAL_GFRIEND_SEX_GOOD
		++iSubStateStatus
	BREAK

	CASE 5
		GET_CHAR_ANIM_CURRENT_TIME scplayer SEX_1_Cum_P fTemp[0]
		IF fTemp[0] = 1.0		
			//--- Sex Finished			
			iSexState = 2
			iSubStateStatus = 0
		ENDIF		
	BREAK
	ENDSWITCH	
RETURN
/********************************************
		STATE 7: ALTERNATIVE INTRO
********************************************/
GF_Sex_State7:  
	SWITCH iSubStateStatus	 				

	CASE 0
		SET_BIT iSexMachineFlags 1
		
		//--- Set the special 'Blowjob' Camera
		iCamera_view = -1
		GOSUB GF_Sex_CameraViewChange
		
		DO_FADE 1500 FADE_IN				

  		//--- Move On To State 1
		++iSubStateStatus
	BREAK
	
	CASE 1
		IF NOT GET_FADING_STATUS	   
			
			TASK_PLAY_ANIM_NON_INTERRUPTABLE SCPLAYER BJ_STAND_START_P BLOWJOBZ 4.0 FALSE FALSE FALSE TRUE 0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped BJ_STAND_START_W BLOWJOBZ 4.0 FALSE FALSE FALSE TRUE 0

			++iSubStateStatus
		ENDIF		
	BREAK

	CASE 2
		GET_CHAR_ANIM_CURRENT_TIME scplayer BJ_STAND_START_P fTemp[0]

		IF fTemp[0] > 0.5
		AND fTemp[0] < 1.0
			
			IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
				DO_FADE	500 FADE_OUT	
				iSubStateStatus = 5
				BREAK
			ENDIF
		ENDIF

		IF fTemp[0] = 1.0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer BJ_STAND_LOOP_P BLOWJOBZ 4.0 TRUE FALSE FALSE TRUE 6000
			TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped BJ_STAND_LOOP_W BLOWJOBZ 4.0 TRUE FALSE FALSE TRUE 6000 
			TIMERB = 0
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 3
		
		IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			DO_FADE	500 FADE_OUT	
			iSubStateStatus = 5
		ELSE		
			IF TIMERB > 6000		
				TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer BJ_STAND_END_P BLOWJOBZ 4.0 FALSE FALSE FALSE FALSE 0
				TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped BJ_STAND_END_W BLOWJOBZ 4.0 FALSE FALSE FALSE FALSE 0			
			  	++iSubStateStatus
			ENDIF
		ENDIF
	BREAK

	CASE 4
		GET_SCRIPT_TASK_STATUS iGF_ped TASK_PLAY_ANIM_NON_INTERRUPTABLE iTemp				
		IF iTemp = FINISHED_TASK
			DO_FADE	500 FADE_OUT
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 5
		IF NOT GET_FADING_STATUS											
			//--- State Completed - move on to the appropriate sex game
			IF iSpanking > 0
				iSexState = 3
				iSubStateStatus = 0				
			ELSE
				iSexState = 6
				iSubStateStatus = 0									
			ENDIF 
		ENDIF
	BREAK
	ENDSWITCH		
RETURN
/********************************************
		STATE 8: SEX END BAD
********************************************/
GF_Sex_State8: 
	SWITCH iSubStateStatus	 				

	CASE 0
		IF shaggin_position = 0		
			TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped SEX_1_Fail_W SEX 4.0 FALSE FALSE FALSE TRUE 0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer SEX_1_Fail_P SEX 4.0 FALSE FALSE FALSE TRUE 0
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_CLIMAX_LOW
			++iSubStateStatus
			BREAK
		ENDIF
		IF shaggin_position = 1		
			TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped SEX_2_Fail_W SEX 4.0 FALSE FALSE FALSE TRUE 0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer SEX_2_Fail_P SEX 4.0 FALSE FALSE FALSE TRUE 0
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_CLIMAX_LOW
			++iSubStateStatus
			BREAK
		ENDIF
		IF shaggin_position = 2		
			TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped SEX_3_Fail_W SEX 4.0 FALSE FALSE FALSE TRUE 0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer SEX_3_Fail_P SEX 4.0 FALSE FALSE FALSE TRUE 0
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_CLIMAX_LOW
			++iSubStateStatus
			BREAK
		ENDIF
	BREAK

	CASE 1
		IF shaggin_position = 0
			GET_CHAR_ANIM_CURRENT_TIME scplayer SEX_1_Fail_P fTemp[0]
		ELSE
			IF shaggin_position = 1
				GET_CHAR_ANIM_CURRENT_TIME scplayer SEX_2_Fail_P fTemp[0]
			ELSE
				IF shaggin_position = 2
					GET_CHAR_ANIM_CURRENT_TIME scplayer SEX_3_Fail_P fTemp[0]
				ENDIF
			ENDIF
		ENDIF
		IF fTemp[0] = 1.0		
			//--- Sex Finished			
			iSexState = 2
			iSubStateStatus = 0
		ENDIF		
	BREAK
	ENDSWITCH
RETURN  
/********************************************
			STATE 9: 
********************************************/
GF_Sex_State9: 
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Fade to black		
		IF IS_CHAR_IN_ANY_CAR scplayer 
			STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer iCar
			SET_CAR_PROOFS iCar TRUE TRUE TRUE TRUE TRUE 
		ELSE
			SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE 
		ENDIF

		DO_FADE 0 FADE_OUT
		++iSubStateStatus
	BREAK

	CASE 1
		IF NOT GET_FADING_STATUS

			SET_AREA_VISIBLE 0
			SET_CHAR_AREA_VISIBLE iGF_ped 0
			SET_CHAR_AREA_VISIBLE scplayer 0

			IF IS_CHAR_ON_FOOT scplayer
				SET_CHAR_COORDINATES scplayer fGFHomeX fGFHomeY fGFHomeZ
				SET_CHAR_HEADING scplayer fGHHomeHeading					 					
			ENDIF

			LOAD_SCENE fGFHomeX fGFHomeY fGFHomeZ
			REQUEST_COLLISION fGFHomeX fGFHomeY

			SET_CHAR_VISIBLE scplayer FALSE			
			SET_PLAYER_CONTROL player1 OFF
			SWITCH_WIDESCREEN ON

			DISPLAY_RADAR FALSE	 

			SET_EVERYONE_IGNORE_PLAYER player1 TRUE				
			SET_ALL_CARS_CAN_BE_DAMAGED FALSE 
			 			
			GOSUB GF_Sex_Censored_GetCoordsHomeCut // Returns in fX,Y,Z[0] the camera and fX,Y,Z[1] the target
			SET_CHAR_COORDINATES iGF_ped fX[0] fY[0] fZ[0]
		
			SET_CHAR_COLLISION iGF_ped FALSE
			SET_CHAR_VISIBLE iGF_ped FALSE
		
			SET_FIXED_CAMERA_POSITION fX[0] fY[0] fZ[0] 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT fX[1] fY[1] fZ[1] JUMP_CUT

			CAMERA_SET_SHAKE_SIMULATION_SIMPLE 5 5000.0 10.0

			GET_TIME_OF_DAY iTemp iTemp2
			iTemp2 += 30
			IF iTemp2 > 60
				iTemp2 -= 60
				++iTemp
			ENDIF
			SET_TIME_OF_DAY iTemp iTemp2

			DO_FADE 1000 FADE_IN			
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 2
		IF NOT GET_FADING_STATUS
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_MOAN
			TIMERB = 0
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 3
		IF TIMERB > 3000
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_MOAN
			TIMERB = 0
			CAMERA_SET_SHAKE_SIMULATION_SIMPLE 5 3000.0 15.0
			++iSubStateStatus
		ENDIF
	BREAK
																		
	CASE 4
		IF TIMERB > 3000			
			iGFSayContext = GF_CONTEXT_JUSTPLAYER
			IF iSpanking > 0
				iCJSayContext = CONTEXT_GLOBAL_SPANKING 
			ELSE
				iCJSayContext = CONTEXT_GLOBAL_HAVING_SEX			 
			ENDIF
			CAMERA_SET_SHAKE_SIMULATION_SIMPLE 5 3000.0 20.0
			TIMERB = 0
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 5
		IF TIMERB > 3000			
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_CLIMAX_HIGH
			CAMERA_SET_SHAKE_SIMULATION_SIMPLE 5 4000.0 30.0
			TIMERB = 0
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 6
		IF TIMERB > 4000			
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_SEX_GOOD
			CAMERA_SET_SHAKE_SIMULATION_SIMPLE 5 3000.0 5.0
			TIMERB = 0
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 7
		IF TIMERB > 3000
			SET_BIT iDateReport DATE_WAS_SUCCESS
			SET_BIT iDateReport SEX_WAS_GOOD // Mark SEX as Good for the Dating Agent
			//--- Sex Finished			
			iSexState = 2
			iSubStateStatus = 0
		ENDIF
	BREAK

	ENDSWITCH
RETURN
/*****************************************************************************
								SEX SUBROUTINES
******************************************************************************/
/*******************************************
		LOAD GF MODEL FROM STREAM
********************************************/
GF_Sex_SteamGFModel:

	SWITCH iGFIdx_par
		
		CASE COOCHIE
			WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
				LOAD_SPECIAL_CHARACTER 1 GANGRL2
				WAIT 0
			ENDWHILE			
		BREAK

		CASE MICHELLE
			WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
				LOAD_SPECIAL_CHARACTER 1 MECGRL2
				WAIT 0
			ENDWHILE			
		BREAK
		
		CASE KYLIE
			WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
				LOAD_SPECIAL_CHARACTER 1 GUNGRL2
				WAIT 0
			ENDWHILE				
		BREAK

		CASE BARBARA
			WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
				LOAD_SPECIAL_CHARACTER 1 COPGRL2
				WAIT 0
			ENDWHILE						
		BREAK

		CASE SUZIE
			WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
				LOAD_SPECIAL_CHARACTER 1 NURGRL2 
				WAIT 0
			ENDWHILE		
		BREAK

		CASE MILLIE
			WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
				LOAD_SPECIAL_CHARACTER 1 CROGRL2
				WAIT 0
			ENDWHILE
		BREAK

	ENDSWITCH
	CREATE_CHAR PEDTYPE_CIVFEMALE SPECIAL01 0.0 0.0 0.0 iGF_ped		

	SET_CHAR_PROOFS iGF_ped TRUE TRUE TRUE TRUE TRUE
	SET_CHAR_COLLISION iGF_ped FALSE  				  
	DONT_REMOVE_CHAR iGF_ped

RETURN
/*******************************************
		LOAD ANIMATIONS FROM STREAM
********************************************/
GF_Sex_SteamAnims:
		
	REQUEST_ANIMATION BLOWJOBZ
	REQUEST_ANIMATION KISSING
	REQUEST_ANIMATION SEX	  
	REQUEST_ANIMATION SNM
	
GF_Sex_StreamLoop:
	WAIT 0

	IF HAS_ANIMATION_LOADED BLOWJOBZ
	AND HAS_ANIMATION_LOADED SEX
	AND HAS_ANIMATION_LOADED KISSING
	AND HAS_ANIMATION_LOADED SNM
		RETURN
	ELSE
		REQUEST_ANIMATION BLOWJOBZ
		REQUEST_ANIMATION KISSING
 		REQUEST_ANIMATION SEX	  
 		REQUEST_ANIMATION SNM
		GOTO GF_Sex_StreamLoop
	ENDIF
/*******************************************
		LOAD GIRLFRIEND'S HOUSE
********************************************/
GF_Sex_LoadGirlHouse:
	//--- Just fill this bit with the co-ords required for each girl 
	SWITCH iGFidx_par
	 
		CASE COOCHIE // Gang Girl 
			//--- Seated \ Standing position
			fX[0] = 245.6252 // 245.0 
			fY[0] = 304.1411 // 304.0
			fZ[0] = 998.32 
			//--- Heading 
			fTemp[0] = 0.0	 
			//--- Visible Area Code of house
			iTemp = 1
			//--- INTRO STATE for this GF (1 or 7)
			iTemp2 = 1
		BREAK

		CASE MICHELLE 
			//--- Seated \ Standing position
			fX[0] = 306.1999 
			fY[0] = 305.1225 
			fZ[0] = 1002.2969
			//--- Heading 
			fTemp[0] = 90.0
			//--- Visible Area Code of house
			iTemp = 4
			//--- INTRO STATE for this GF (1 or 7)
			iTemp2 = 1
		BREAK

		CASE KYLIE 
			//--- Seated \ Standing position
			fX[0] = 288.7753 
			fY[0] = 308.4772 
			fZ[0] = 998.1641 
			//--- Heading 
			fTemp[0] = 213.8029
			//--- Visible Area Code of house
			iTemp = 3
			//--- INTRO STATE for this GF (1 or 7)
			iTemp2 = 7
		BREAK

		CASE BARBARA // COP Girl 
			//--- Seated \ Standing position
			fX[0] = 324.6405 
			fY[0] = 307.4622 
			fZ[0] = 998.1558 
			//--- Heading 
			fTemp[0] = 175.0	 
			//--- Visible Area Code of house
			iTemp = 5
			//--- INTRO STATE for this GF (1 or 7)
			iTemp2 = 7
		BREAK

		CASE SUZIE // Nurse Girl 
			//--- Bed \ Standing position
			fX[0] = 271.4699 
			fY[0] = 307.2668 
			fZ[0] = 998.32
			//--- Heading 
			fTemp[0] = 265.0
			//--- Visible Area Code of house
			iTemp = 2
			//--- INTRO STATE for this GF (1 or 7)
			iTemp2 = 1
		BREAK

		CASE MILLIE // Croupier Girl 
			//--- Bed \ Standing position
			fX[0] = 345.6252 
			fY[0] = 304.1411 
			fZ[0] = 998.25
			//--- Heading 
			fTemp[0] = 0.0	 
			//--- Visible Area Code of house
			iTemp = 6
			//--- INTRO STATE for this GF (1 or 7)
			iTemp2 = 1
		BREAK

	ENDSWITCH


	//--- The following is generic for all girls
	
	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
	CLEAR_CHAR_TASKS_IMMEDIATELY iGF_ped
	
	SET_CHAR_COLLISION iGF_ped FALSE 							
	SET_CHAR_COLLISION scplayer FALSE

	SET_CHAR_COORDINATES scplayer fX[0]	fY[0] fZ[0]
	SET_CHAR_HEADING scplayer fTemp[0]
	
	//--- Set Up the position of the GF
	GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 1.0 -1.0 fX[0] fY[0] fZ[0]
	GET_CHAR_HEADING scplayer fTemp[0]
	fTemp[0] += 180.0
	SET_CHAR_COORDINATES iGF_ped fX[0] fY[0] fZ[0]
	SET_CHAR_HEADING iGF_ped fTemp[0]

	SET_AREA_VISIBLE iTemp
	SET_CHAR_AREA_VISIBLE iGF_ped iTemp
	SET_CHAR_AREA_VISIBLE scplayer iTemp

	LOAD_SCENE fX[0] fY[0] fZ[0]
	REQUEST_COLLISION fX[0] fY[0]

RETURN
/*******************************************
		LEAVE GIRLFRIEND'S HOUSE
********************************************/
GF_Sex_LeaveGirlHouse:
	
	SET_AREA_VISIBLE 0
	SET_CHAR_AREA_VISIBLE scplayer 0

	CLEAR_AREA fGFHomeX fGFHomeY fGFHomeZ 3.0 TRUE

	IF IS_CHAR_ON_FOOT scplayer
		SET_CHAR_COORDINATES scplayer fGFHomeX fGFHomeY fGFHomeZ
		SET_CHAR_HEADING scplayer fGHHomeHeading
	ENDIF

	REQUEST_COLLISION fGFHomeX fGFHomeY	 	
	LOAD_SCENE fGFHomeX fGFHomeY fGFHomeZ

RETURN
/*******************************************
	GET COORDINATES FOR SHAG INSIDE FLAT
********************************************/
GF_Sex_GetCoordsShag:

	SWITCH iGFidx_par  
		CASE COOCHIE // Gang Girl 
			//--- The Bed
			fX[0] = 245.617 
			fY[0] = 301.507
			fZ[0] = 999.046  
			//--- Heading
			fTemp[0] = 0.0
		BREAK

		CASE BARBARA 
			//--- The Bed
			fX[0] = 318.4  
			fY[0] = 311.85 
			fZ[0] = 998.8
			//--- Heading
			fTemp[0] = 110.0
		BREAK

		CASE MICHELLE 
			//--- The Bed
			fX[0] = 308.8862 
			fY[0] = 301.9657
			fZ[0] = 1003.0
			//--- Heading
			fTemp[0] = 0.0
		BREAK

		CASE KYLIE 
			//--- The Bed
			fX[0] = 282.5775 
			fY[0] = 307.7573 
			fZ[0] = 1002.3906 
			//--- Heading
			fTemp[0] = 0.0
		BREAK

		CASE SUZIE 
			//--- The Bed
			fX[0] = 270.1200
			fY[0] = 307.7370 
			fZ[0] = 999.0204 
			//--- Heading
			fTemp[0] = 180.0
		BREAK

		CASE MILLIE 
			//--- The Bed
			fX[0] = 345.617 
			fY[0] = 301.507
			fZ[0] = 998.8  
			//--- Heading
			fTemp[0] = 0.0
		BREAK

	ENDSWITCH

RETURN
/*******************************************
			CAMERA VIEW CHANGE
********************************************/
GF_Sex_CameraViewChange:
	
	SWITCH iCamera_view

		CASE -1	// Initial 'Blowjob' wider shot - never get back in here
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 1.8 2.9 0.353 fX[0] fY[0] fZ[0]
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 1.0 0.8701 0.1828 fX[1] fY[1] fZ[1]			
//			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 2.2014 1.3628	0.353 fX[0] fY[0] fZ[0]
//			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 1.3481 0.8701 0.1828 fX[1] fY[1] fZ[1]			
		BREAK

		CASE 0
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -0.6375 2.3043 0.1065 fX[0] fY[0] fZ[0]
			IF iSexState = 3 // Spanking
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -0.3435 1.4278 -0.1 fX[1] fY[1] fZ[1]
			ELSE
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -0.3435 1.4278 -0.2746 fX[1] fY[1] fZ[1]
			ENDIF
		BREAK

		CASE 1
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 1.5989 0.7828 -0.2044  fX[0] fY[0] fZ[0]
			IF iSexState = 3 // Spanking
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.6223 0.6748 -0.2 fX[1] fY[1] fZ[1]
			ELSE
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.6223 0.6748 -0.3904 fX[1] fY[1] fZ[1]				
			ENDIF
		BREAK

		CASE 2
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.9748 -0.1026 0.7511  fX[0] fY[0] fZ[0]
			IF iSexState = 3 // Spanking
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.4155 0.3026	0.01 fX[1] fY[1] fZ[1]
			ELSE
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.4155 0.3026	0.0279 fX[1] fY[1] fZ[1]
			ENDIF
		BREAK

		CASE 3
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -1.3103 1.6396 -0.6208 fX[0] fY[0] fZ[0]
			IF iSexState = 3 // Spanking
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -0.49	1.0874 -0.2 fX[1] fY[1] fZ[1]
			ELSE
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -0.49	1.0874 -0.4726 fX[1] fY[1] fZ[1]
			ENDIF
		BREAK

		DEFAULT
			iCamera_view = 0 // Loop around
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -0.6375 2.3043 0.1065 fX[0] fY[0] fZ[0]
			IF iSexState = 3 // Spanking
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -0.3435 1.4278 0.0 fX[1] fY[1] fZ[1]
			ELSE
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -0.3435 1.4278 -0.2746 fX[1] fY[1] fZ[1]
			ENDIF
		BREAK		

	ENDSWITCH	
	
	SET_FIXED_CAMERA_POSITION fX[0] fY[0] fZ[0] 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT fX[1] fY[1] fZ[1] JUMP_CUT

	++iCamera_view

RETURN
/*******************************************
		SEX POSITION CHANGE
********************************************/
GF_Sex_PositionChange:
	IF iSexState = 6 // Only During Proper Sex

		++shaggin_position 	

		SWITCH shaggin_position
			CASE 1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped SEX_1to2_W SEX 4.0 FALSE FALSE FALSE FALSE 0
				TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer SEX_1to2_P SEX 4.0 FALSE FALSE FALSE FALSE 0
			BREAK

			CASE 2
				TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped SEX_2to3_W SEX 4.0 FALSE FALSE FALSE FALSE 0
				TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer SEX_2to3_P SEX 4.0 FALSE FALSE FALSE FALSE 0
			BREAK

			CASE 3
				shaggin_position = 0 // Loop around
				TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped SEX_3to1_W SEX 4.0 FALSE FALSE FALSE FALSE 0
				TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer SEX_3to1_P SEX 4.0 FALSE FALSE FALSE FALSE 0
			BREAK
				
		ENDSWITCH

		
		iSubStateStatus = 1			

	ENDIF
RETURN	
/*******************************************
COMPUTE ANIM SPEED FOR THIS EXCITEMENT LEVEL
********************************************/
GF_Sex_ComputeAnimSpeedForExcitement:
iTemp = excitement / 10

IF iTemp < 3
	iTemp = 0
ENDIF

	SWITCH iTemp				

		CASE 0 // The crap
			fTemp[0] = 0.035				 
			CLEAR_BIT iSexMachineFlags 6 // Increase Excitement X 2	
			CLEAR_BIT iSexMachineFlags 7 // Increase Excitement X 3
		BREAK
			
		CASE 3 // The slow
			fTemp[0] = 0.045				 
			CLEAR_BIT iSexMachineFlags 6 // Increase Excitement X 2	
			CLEAR_BIT iSexMachineFlags 7 // Increase Excitement X 3
		BREAK

		CASE 4 // The slow
			fTemp[0] = 0.050				 
			CLEAR_BIT iSexMachineFlags 6 // Increase Excitement X 2	
			CLEAR_BIT iSexMachineFlags 7 // Increase Excitement X 3
		BREAK

		CASE 5 // Get going fella...
			fTemp[0] = 0.055
			CLEAR_BIT iSexMachineFlags 6 // Increase Excitement X 2	
			CLEAR_BIT iSexMachineFlags 7 // Increase Excitement X 3
		BREAK

		CASE 6 // Get going fella...
			fTemp[0] = 0.055
			CLEAR_BIT iSexMachineFlags 6 // Increase Excitement X 2	
			CLEAR_BIT iSexMachineFlags 7 // Increase Excitement X 3
		BREAK

		CASE 7 // Getting there man...
			fTemp[0] = 0.08	
			SET_BIT iSexMachineFlags 6 // Increase Excitement X 2
			CLEAR_BIT iSexMachineFlags 7 // Increase Excitement X 3
		BREAK

		CASE 8 // Getting there man...
			fTemp[0] = 0.08	
			SET_BIT iSexMachineFlags 6 // Increase Excitement X 2
			CLEAR_BIT iSexMachineFlags 7 // Increase Excitement X 3
		BREAK

		CASE 9 // Woooow keep it hard...
			fTemp[0] = 0.09	
			SET_BIT iSexMachineFlags 7 // Increase Excitement X 3
		BREAK

	ENDSWITCH
RETURN
/*******************************************
		LOOP SEX ANIM AT GIVEN SPEED
********************************************/
GF_Sex_LoopSexAtGivenSpeed:
	SWITCH shaggin_position
	
		CASE 0
			IF IS_CHAR_PLAYING_ANIM scplayer SEX_1_P
				SET_CHAR_ANIM_PLAYING_FLAG scplayer SEX_1_P FALSE
				SET_CHAR_ANIM_CURRENT_TIME scplayer SEX_1_P GF_anim_time					
			ENDIF
				
			IF IS_CHAR_PLAYING_ANIM iGF_ped SEX_1_W
				SET_CHAR_ANIM_PLAYING_FLAG iGF_ped SEX_1_W FALSE
				SET_CHAR_ANIM_CURRENT_TIME iGF_ped SEX_1_W GF_anim_time
			ENDIF
		BREAK

		CASE 1
			IF IS_CHAR_PLAYING_ANIM scplayer SEX_2_P
				SET_CHAR_ANIM_PLAYING_FLAG scplayer SEX_2_P FALSE
				SET_CHAR_ANIM_CURRENT_TIME scplayer SEX_2_P GF_anim_time
			ENDIF
		
			IF IS_CHAR_PLAYING_ANIM iGF_ped SEX_2_W
				SET_CHAR_ANIM_PLAYING_FLAG iGF_ped SEX_2_W FALSE
				SET_CHAR_ANIM_CURRENT_TIME iGF_ped SEX_2_W GF_anim_time
			ENDIF
		BREAK

		CASE 2
			IF IS_CHAR_PLAYING_ANIM scplayer SEX_3_P
				SET_CHAR_ANIM_PLAYING_FLAG scplayer SEX_3_P FALSE
				SET_CHAR_ANIM_CURRENT_TIME scplayer SEX_3_P GF_anim_time
			ENDIF
			
			IF IS_CHAR_PLAYING_ANIM iGF_ped SEX_3_W
				SET_CHAR_ANIM_PLAYING_FLAG iGF_ped SEX_3_W FALSE
				SET_CHAR_ANIM_CURRENT_TIME iGF_ped SEX_3_W GF_anim_time
			ENDIF
		 	
		BREAK
						
	ENDSWITCH

	//--- Calculate the Next frame
	IF IS_BIT_SET iSexMachineFlags 5  // End Reached
		GF_anim_time -= fTemp[0] // Decrease according to step
	ELSE 
   		GF_anim_time += fTemp[0] // Increase according to step
	ENDIF

	IF GF_anim_time <= 0.0
		GF_anim_time = 0.01
		CLEAR_BIT iSexMachineFlags 5 // Mark START of Sex Anim
		GOSUB GF_Sex_GetStickSynchToSexAnim
	ENDIF

	IF GF_anim_time >= 1.0
		GF_anim_time = 0.99
		SET_BIT iSexMachineFlags 5 // Mark END of Sex Anim
		GOSUB GF_Sex_GetStickSynchToSexAnim 
	ENDIF

	//-- Midway call to prevent cheating
	IF GF_anim_time = 0.5		
		GOSUB GF_Sex_GetStickSynchToSexAnim
	ENDIF
RETURN						 
/*******************************************
	GET STICK SYNCH COMPARED TO SEX ANIM
********************************************/
GF_Sex_GetStickSynchToSexAnim:
	GET_POSITION_OF_ANALOGUE_STICKS PAD1 GF_left_stick_x GF_left_stick_y GF_right_stick_x GF_right_stick_y

	fTemp[1] =# GF_left_stick_y // Cast into a float for divisions
	fTemp[1] /= 128.0
	
	//--- Call to prevent cheating with the stick
	IF GF_anim_time = 0.5
		IF fTemp[1] < 0.0
			GOSUB GF_Sex_IncrementExcitement
		ELSE
			GOSUB GF_Sex_DecrementExcitement
			RETURN
		ENDIF
	ENDIF

	IF IS_BIT_SET iSexMachineFlags 5  // End Sex Anim Reached		
		SHAKE_PAD PAD1 200 100
		iGFSayContext = CONTEXT_GLOBAL_GFRIEND_MOAN
		iCJSayContext = CONTEXT_GLOBAL_HAVING_SEX 
		//--- STICK DOWN: The closer we get to -1 the better
		IF fTemp[1] < -0.8 
			GOSUB GF_Sex_IncrementExcitement
		ELSE
			GOSUB GF_Sex_DecrementExcitement
		ENDIF		 
	ELSE		
		//--- STICK UP: The closer we get to 1 the better 
		IF fTemp[1] > 0.8 
			GOSUB GF_Sex_IncrementExcitement
		ELSE
			GOSUB GF_Sex_DecrementExcitement
		ENDIF		 
	ENDIF					  
RETURN
/*******************************************
			INCREMENT EXCITEMENT
********************************************/
GF_Sex_IncrementExcitement:
	//--- Return an Increment
	IF excitement < 100
		IF IS_BIT_SET iSexMachineFlags 6 // Increase Excitement X 2
			excitement = excitement + 5
		ELSE
			IF IS_BIT_SET iSexMachineFlags 7 // Increase Excitement X 3
				excitement = excitement + 10	
			ELSE
				++excitement // Standard Increment
			ENDIF
		ENDIF
	ELSE
		excitement = 100
	ENDIF
RETURN
/*******************************************
			DECREMENT  EXCITEMENT
********************************************/
GF_Sex_DecrementExcitement:
//--- NOTE: TIMERB must have been correctly initialised in the current SexState

	IF TIMERB > 5000 // If player has been playing for more than 5 seconds
		//-- Return a decrease
		IF excitement > 0		   
		   	IF excitement > 10
		   	AND NOT IS_BIT_SET iSexMachineFlags 6 // Increase Excitement X 2		   		  			
			   	excitement = excitement - 5				
		   	ELSE
		   		-- excitement 
		   	ENDIF		   
		ELSE
			excitement = 0
		ENDIF

	ENDIF
RETURN
/*******************************************
			INCREMENT POWER BAR
********************************************/
GF_Sex_IncrementPowerBar:
	IF fAngle > 90.0
		fAngle = 0.0
	ENDIF

	SIN fAngle fSin
	fSin *= POWER_SIN_SCALE_FACTOR
	iTemp =# fSin
	iTemp += power
	IF iTemp >= 100
		power = 100
	ELSE		
		power = iTemp
	ENDIF 
 	fAngle += 1.0
RETURN
/*******************************************
			DECREMENT POWER BAR
********************************************/
GF_Sex_DecrementPowerBar:
	fAngle = 0.0
	IF power >= GF_POWERBAR_CHARGE_HIT_MARGIN
		power -= GF_POWERBAR_DECREMENT_SMALL
	ELSE
		IF power <= GF_POWERBAR_DECREMENT
			power = 0
		ELSE
			power -= GF_POWERBAR_DECREMENT
		ENDIF
	ENDIF
RETURN
/*******************************************
		REGISTER POWER BAR SCORE UP
********************************************/
GF_Sex_RegisterPowerBarScore:
	IF power >= GF_POWERBAR_CHARGE_HIT_MARGIN
		SET_BIT iSexMachineFlags 10 // SPANKING CHARGE PERFECT!		
	ELSE
		CLEAR_BIT iSexMachineFlags 10 
	ENDIF 
RETURN
/*******************************************
  COMPUTE ANIM SPEED FOR THIS POWER LEVEL
********************************************/
GF_Sex_ComputeAnimSpeedForPower:
	fTemp[0] = 0.025
	
	IF IS_BIT_SET iSexMachineFlags 10 // SPANKING CHARGE PERFECT!
		fTemp[0] += 0.015//0.050 
	ENDIF 

	GF_anim_time = 0.01
RETURN
/*******************************************
			SPANK AT GIVEN SPEED
********************************************/
GF_Sex_SpankAtGivenSpeed:
																   
	IF IS_CHAR_PLAYING_ANIM scplayer SpankingP
		SET_CHAR_ANIM_PLAYING_FLAG scplayer SpankingP FALSE
		SET_CHAR_ANIM_CURRENT_TIME scplayer SpankingP GF_anim_time					
	ENDIF
		
	IF IS_CHAR_PLAYING_ANIM iGF_ped SpankingW
		SET_CHAR_ANIM_PLAYING_FLAG iGF_ped SpankingW FALSE
		SET_CHAR_ANIM_CURRENT_TIME iGF_ped SpankingW GF_anim_time
	ENDIF

	//--- Calculate 

	IF NOT IS_BIT_SET iSexMachineFlags 5  // End Reached
   		GF_anim_time += fTemp[0] // Increase according to step
	ENDIF

	IF GF_anim_time >= 1.0
		GF_anim_time = 0.99
		SET_BIT iSexMachineFlags 5 // Mark END of Sex Anim
		RETURN
	ENDIF
RETURN	
/*******************************************
	  COMPUTE POWER SCORE
********************************************/
GF_Sex_ComputeExcitementFromPower:

	IF IS_BIT_SET iSexMachineFlags 10 // SPANKING CHARGE PERFECT!
		iGFSayContext = CONTEXT_GLOBAL_GFRIEND_CLIMAX_HIGH
		IF excitement <= 100
			excitement += GF_EXCITEMENT_SPANK_INCREMENT			
		ELSE
			excitement = 100		
		ENDIF
	ELSE
		IF excitement >= 10
			excitement -= GF_EXCITEMENT_SPANK_DECREMENT			
		ELSE
			excitement = 0		
		ENDIF			
	ENDIF 

	CLEAR_BIT iSexMachineFlags 10
	CLEAR_BIT iSexMachineFlags 11 
	CLEAR_BIT iSexMachineFlags 12
RETURN	
/*******************************************
			UPDATE POWER BAR
********************************************/
GF_Sex_UpdatePowerBar:
	IF power < 100
	AND power > 0
	AND IS_BIT_SET iSexMachineFlags 14  // POWER_BAR_TOP_REACHED
		GOSUB GF_Sex_DecrementPowerBar
		RETURN
	ENDIF

	IF power < 100
	AND power > 0
	AND NOT IS_BIT_SET iSexMachineFlags 14  // POWER_BAR_TOP_REACHED
		GOSUB GF_Sex_IncrementPowerBar
		RETURN
	ENDIF

	IF power >= 100	// Reached the top
		power = 100
		SET_BIT iSexMachineFlags 14  // POWER_BAR_TOP_REACHED
		GOSUB GF_Sex_DecrementPowerBar
		RETURN
	ENDIF

	IF power <= 0 // Reached the bottom
		power = 0
		CLEAR_BIT iSexMachineFlags 14  // POWER_BAR_TOP_REACHED
		GOSUB GF_Sex_IncrementPowerBar
		RETURN
	ENDIF
RETURN				 
/********************************************
			SPEECH MANAGER
********************************************/
GF_Sex_SpeechManager:

SWITCH iGFSpeechStatus
	
	CASE GF_SPEECH_FREE
		IF iGFSayContext > 0
			iGFSpeechStatus = GF_SPEECH_REQUEST
		ENDIF
	BREAK

	CASE GF_SPEECH_ENABLE_AI
		ENABLE_CHAR_SPEECH iGF_ped
	BREAK

	CASE GF_SPEECH_DISABLE_AI
		DISABLE_CHAR_SPEECH iGF_ped FALSE
	BREAK

	CASE GF_SPEECH_REQUEST
		IF NOT IS_CHAR_TALKING iGF_ped 
			 IF iGFSayContext > 0 
				 SET_CHAR_SAY_CONTEXT_IMPORTANT iGF_ped iGFSayContext TRUE TRUE TRUE iGFContextVariation
				 GOSUB GF_Sex_RetrieveSpeechSubtitleForThisContext
				 iGFSayContext = -1
				 IF iCJSayContext > 0
					iGFSpeechStatus = GF_SPEECH_REQUEST_FOR_PLAYER
				 ELSE					 
					 iGFSpeechStatus = GF_SPEECH_FREE				 
				ENDIF
			ELSE
				IF iGFSayContext = GF_CONTEXT_JUSTPLAYER
					iGFSayContext = -1
					iGFSpeechStatus = GF_SPEECH_REQUEST_FOR_PLAYER
				ELSE 
					//--- Invalid context passed
					iGFSayContext = -1
					iCJSayContext = -1
					iGFSpeechStatus = GF_SPEECH_FREE
				ENDIF
			ENDIF
		ENDIF		
	BREAK
		 
	CASE GF_SPEECH_REQUEST_FOR_PLAYER
		IF NOT IS_CHAR_TALKING scplayer
		AND NOT IS_CHAR_TALKING iGF_ped  
			 IF iCJSayContext > 0 
				 SET_CHAR_SAY_CONTEXT_IMPORTANT scplayer iCJSayContext TRUE TRUE TRUE iGFContextVariation
				 iCJSayContext = -1
				 iGFSpeechStatus = GF_SPEECH_FREE				 
			ELSE
				//--- Invalid context passed
				 iGFSpeechStatus = GF_SPEECH_FREE
			ENDIF
		ENDIF		
	BREAK

	CASE GF_SPEECH_DISABLE_AI_AND_SPEAK
		DISABLE_CHAR_SPEECH iGF_ped FALSE
		IF NOT IS_CHAR_TALKING iGF_ped 
			 IF iGFSayContext > 0 
				 SET_CHAR_SAY_CONTEXT_IMPORTANT iGF_ped iGFSayContext TRUE TRUE TRUE iGFContextVariation
				 GOSUB GF_Sex_RetrieveSpeechSubtitleForThisContext
				 iGFSayContext = -1
				 iGFSpeechStatus = GF_SPEECH_ENABLE_AI_WHEN_SILENT				 
			ELSE
				//--- Invalid context passed
				 iGFSpeechStatus = GF_SPEECH_FREE
			ENDIF
		ENDIF		
	BREAK

	CASE GF_SPEECH_ENABLE_AI_WHEN_SILENT
		IF NOT IS_CHAR_TALKING iGF_ped 
			ENABLE_CHAR_SPEECH iGF_ped
			iGFSayContext = -1
			iGFSpeechStatus = GF_SPEECH_FREE
		ENDIF
	BREAK

ENDSWITCH

RETURN
/********************************************
	RETRIEVE SPEECH SUBTITLE FOR CONTEXT
********************************************/
GF_Sex_RetrieveSpeechSubtitleForThisContext:

	SWITCH iGFSayContext

		CASE CONTEXT_GLOBAL_GFRIEND_CLIMAX_HIGH
			PRINT_NOW GF_0019 5000 1 
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_CLIMAX_LOW
			PRINT_NOW GF_0067 5000 1
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_SEX_GOOD
			PRINT_NOW GF_0042 5000 1 
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_SEX_BAD
			PRINT_NOW GF_0067 5000 1
		BREAK

	ENDSWITCH

RETURN
/*******************************************
CENSORED SEX: GET COORDINATES FOR HOME CUT 
********************************************/
GF_Sex_Censored_GetCoordsHomeCut:

	SWITCH iGFidx_par 
		CASE COOCHIE // Gang Girl
			//--- Camera pos
			fX[0] = 2394.4080  
			fY[0] = -1728.2148
			fZ[0] = 14.2715  

			//--- Camera target
			fX[1] = 2395.0662   
			fY[1] = -1727.4651
			fZ[1] = 14.2063 
		BREAK

		CASE BARBARA // COP Girl
			//--- Camera pos
			fX[0] = -1401.4941   
			fY[0] = 2640.0989 
			fZ[0] = 59.3883

			//--- Camera target
			fX[1] = -1400.7040 
			fY[1] = 2639.6411 
			fZ[1] = 58.9806 
		BREAK

		CASE MICHELLE
			//--- Camera pos
			fX[0] = -1799.7374 
			fY[0] = 1192.8625 
			fZ[0] = 29.6868 

			//--- Camera target
			fX[1] = -1799.4719 
			fY[1] = 1193.6835 
			fZ[1] = 29.1813 
		BREAK

		CASE KYLIE
			//--- Camera pos
			fX[0] = -383.3153 //-396.7247 
			fY[0] = -1435.2662 //-1431.1246 
			fZ[0] =  26.6950 //26.1885 

			//--- Camera target
			fX[1] = -382.5756 //-396.0125 
			fY[1] = -1435.8875 //-1430.4487 
			fZ[1] = 26.4366 //26.3779
		BREAK

		CASE SUZIE
			//--- Camera pos
			fX[0] =	-2582.2927 //-2579.3992 
			fY[0] =	1147.7621 //1149.8939 
			fZ[0] =	59.6235 //56.8211 

			//--- Camera target
			fX[1] = -2581.4065 //-2578.4297 
			fY[1] = 1147.7681 //1150.1238 
			fZ[1] = 59.1605 //56.7361
		BREAK

		CASE MILLIE
			//--- Camera pos
			fX[0] =	2032.2354 
			fY[0] =	2734.5413 
			fZ[0] =	13.3226 

			//--- Camera target
			fX[1] = 2032.7435 
			fY[1] = 2733.7144 
			fZ[1] = 13.0817 
		BREAK
	ENDSWITCH				   
RETURN
/*******************************************
				CLEAN UP 
********************************************/
GF_Sex_Cleanup:
	
	REMOVE_ANIMATION SNM
	REMOVE_ANIMATION SEX
	REMOVE_ANIMATION BLOWJOBZ
	REMOVE_ANIMATION KISSING
	
	//--- Remove the model
	UNLOAD_SPECIAL_CHARACTER 1
	DELETE_CHAR iGF_ped
		
	IF NOT IS_CAR_DEAD iCar
		SET_CAR_PROOFS iCar FALSE FALSE FALSE FALSE FALSE 
		MARK_CAR_AS_NO_LONGER_NEEDED iCar
	ENDIF

	IF IS_PLAYER_PLAYING PLAYER1
		SET_CHAR_VISIBLE scplayer TRUE
		SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE		
		CLEAR_CHAR_TASKS scplayer
		SET_PLAYER_CONTROL player1 ON
		SWITCH_WIDESCREEN OFF
	ENDIF

	DISPLAY_RADAR TRUE

	SET_BIT iDateReport MEET_TOMORROW // Sex took place, so don't date again until tomorrow 
	CLEAR_BIT iDateReport SEX_IN_PROGRESS // Clear SEX_IN_PROGRESS for the Dating Agent
	SET_MINIGAME_IN_PROGRESS FALSE
	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
				RUN DEBUG
********************************************/
GF_Sex_Run_Debug:

	WRITE_DEBUG I__________________________I
	WRITE_DEBUG H 		   
	WRITE_DEBUG G
	WRITE_DEBUG_WITH_FLOAT STICK_POSITION fTemp[1]	
	WRITE_DEBUG_WITH_INT SHAGGIN_POSITION shaggin_position
	WRITE_DEBUG_WITH_INT I_EXCITEMENT excitement
	WRITE_DEBUG_WITH_FLOAT ANIM_TIME GF_anim_time
	WRITE_DEBUG_WITH_INT SubStateStatus iSubStateStatus
	
	SWITCH iSexState
		CASE 0
			WRITE_DEBUG STATE0__
		BREAK
		CASE 1
			WRITE_DEBUG STATE1__ 
		BREAK
		CASE 2
			WRITE_DEBUG STATE2__
		BREAK
		CASE 3
			WRITE_DEBUG STATE3__
		BREAK
		CASE 4
			WRITE_DEBUG STATE4__
		BREAK
		DEFAULT
			WRITE_DEBUG UNTRAPPED_STATE___
		BREAK
	ENDSWITCH

	WRITE_DEBUG I__________________________I

RETURN
}
MISSION_END