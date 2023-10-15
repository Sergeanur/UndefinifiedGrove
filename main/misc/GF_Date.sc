MISSION_START

//--- VERY SPECIFIC CONSTANTS
CONST_INT SIZE_OF_BLIP_ARRAY 					9
//--- DATE FLAGS								
CONST_INT DESTINATION_BACK_HOME 				2
CONST_INT BORED_TAKE_ME_HOME 					7
CONST_INT HOLD_TIMER_THIS_FRAME 				8
CONST_INT DATE_ABORTED							9
CONST_INT RESET_TIMER_THIS_FRAME 				13
CONST_INT GIRL_DRIVE_HELP_ON 					14
CONST_INT GIRL_ATTACKED_BY_PLAYER	 			15
CONST_INT GIRL_DOES_NOT_LIKE_PLAYER				16
CONST_INT REGISTER_STUNT						17
CONST_INT REGISTER_CAR_FAST						18
CONST_INT REGISTER_CAR_SLOW						19
CONST_INT PLAYER_NOT_FAT 						20
CONST_INT PLAYER_NOT_FIT 						21
CONST_INT PLAYER_TOO_FIT						22
CONST_INT COMMENT_DISLIKE_PHYSIQUE				23
CONST_INT KISS_GIVEN							24
CONST_INT FOOT_BLOW_GIVEN						25
CONST_INT BORED_WARNING							26
CONST_INT DRIVE_BY_DONE							27
CONST_INT GIFT_HELP_ON							28
CONST_INT READY_FOR_RANDOM_SPEECH				29
CONST_INT SKIP_BLOWJOB							30
//--- KISS & GIFT FLAGS
CONST_INT GF_PLAYER_GIVE_KISS 					1
CONST_INT GF_PLAYER_GIVE_GIFT 					2
//--- ENTRY EXIT NAMES
CONST_INT GF_FDCHICK							1
CONST_INT GF_FDBURG								2
CONST_INT GF_FDPIZA								3
CONST_INT GF_FDREST1							4
CONST_INT GF_REST2								5
CONST_INT GF_DINER1								6
CONST_INT GF_DINER2								7
CONST_INT GF_BAR1								8
CONST_INT GF_BAR2								9
CONST_INT GF_UFOBAR								10
CONST_INT GF_TSDINER							11
/*****************************************************************************************************************************************
************************************************					**********************************************************************
************************************************   GIRLFRIEND DATE  **********************************************************************
************************************************					**********************************************************************
*****************************************************************************************************************************************/
/* TO DO ON THIS SCRIPT:
------------------------
	- Make the GF driving car speed up or slow down according to the radio station (needs new commands)
*/
{
GF_Date:		  
    SCRIPT_NAME GFDATE

    //--- VARS
    LVAR_INT iGF_ped 												// Parameters arriving from caller
    LVAR_INT iDateState iSubStateStatus 							// State Machine variables
	VAR_TEXT_LABEL16 txtEntryExitName
	LVAR_INT iDateFlags 
	/* BITS:	1 SkipTransitions
				2 Take GF home
				3 L1 pressed
				4 Kiss HELP ON
			    5 Parking Detected
				6 Car BlowJob Given
				7 Girlfriend Got Bored\Annoyed 
				8 Request to Decrease TIMERA this frame
				9 Date Aborted
				10 Intro HELP ON
				11 BLIP_FOR_HOME_ON
				12 TRIANGLE pressed
				13 Request to reset TIMERA this frame
				14 GIRL_DRIVE_HELP_ON
				15 Player Hit GF
				16 GIRL DOES NOT LIKE PLAYER
				17 REGISTER_STUNT
				18 REGISTER_CAR_FAST
				19 REGISTER_CAR_SLOW
				20 PLAYER_NOT_FAT
				21 PLAYER_NOT_FIT
				22 PLAYER_TOO_FIT
				23 COMMENT_DISLIKE_PHYSIQUE 
				24 KISS_GIVEN	
				25 FOOT_BLOW_GIVEN  
				26 BORED_WARNING
				27 DRIVE_BY_DONE
				28 GIFT_HELP_ON
				29 READY_FOR_RANDOM_SPEECH
				30 SKIP_BLOWJOB
	*/

	LVAR_FLOAT fX[3] fY[3] fZ[3] fTemp[2] 
	LVAR_INT  iLocationBlip[SIZE_OF_BLIP_ARRAY] iCurrentArea iTemp iTemp2 iTempPed iCurrentCar iCurrentCarHealth iFrozenTime   
	LVAR_INT  iStoredZone 
	VAR_INT iFun  // Counters must be global...
	VAR_FLOAT fFun fFunTemp

	//--- Init the vars			
	iDateState = 0 
	iSubStateStatus = 0
	iDateFlags = 0 
	iCurrentArea = 0
	iFrozenTime = -1 // Init like so to request a validation as soon as possible
	iCurrentCarHealth = -1 // Init like so to request a validation as soon as possible

	//--- Fill the fun meter at start of date
	iFun = 30 			
	fFun = 30.0
	
	//--- Timer Usage: 	TIMERA counts the lenght of the date, DO NOT use it directly, use GF_Date_UpdateDateTime.
	//					TIMERB is used in each of the states. Clear before use. Be advised.

	//--- Parameter passing Fudge
	IF iDateFlags > 0
	   CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 iGF_ped
	   STORE_CAR_CHAR_IS_IN_NO_SAVE iGF_ped iCurrentCar
	ENDIF
	
	//--- Stream the animations
	GOSUB GF_Date_SteamAnims

	//--- Now mark the no-transitions-allowed bit 	
	SET_BIT iDateFlags 1 // The INIT state (0) must run before allowing transitions
	
//--- Main Loop
GF_Date_Main:
	
	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
// 	GOSUB GF_Date_Debug

	//--- Alive Checks for player and girlfriend
	IF IS_PLAYER_PLAYING PLAYER1
	AND flag_player_on_mission = 0 
		IF NOT IS_CHAR_DEAD iGF_ped		
			//--- Retrieve various useful car data 
			GOSUB GF_Date_GetCurrentCarDataIfAvailable
  
			//--- Update the ongoing Date Timer
			GOSUB GF_Date_UpdateDateTime // Uses and alters TIMERA
		
			//--- Prossess all the Date Transitions	
			GOSUB GF_Date_GetCurrentState

			//--- Go into the appopriate Date State
			GOSUB GF_Date_DoCurrentState

			//--- If required by the date, manage the second girlfriend while player is two-timing
			IF IS_BIT_SET iDateReport PLAYER_TWO_TIMING
				GOSUB GF_Date_TwoTiming 
			ENDIF

			//--- Call the speech for the girlfriend, according to what has been requested 
			GOSUB GF_Date_SpeechManager // This call should be the last to ensure all requests are dealt with
		ELSE
			CLEAR_HELP
			GOSUB GF_Date_Cleanup
		ENDIF
	ELSE
		CLEAR_HELP
		SET_BIT iDateFlags DATE_ABORTED
		GOSUB GF_Date_Cleanup
	ENDIF
		
GOTO GF_Date_Main 
//--- End Main Loop



/*****************************************************************************
								GF_DATE TRANSITIONS
******************************************************************************/
GF_Date_GetCurrentState:


//--- See if we are currently in a state that allows transitions
IF NOT IS_BIT_SET iDateFlags 1 	
 
//---------------------------------------------------
//--- CHECKS THAT MUST BE DONE EVERY FRAME START HERE
//---------------------------------------------------

	//--- CHEATS - remember to disable these
   	IF IS_BIT_SET iDateReport FASTDATE_ON		
		SET_BIT iDateReport DATE_WAS_SUCCESS
		iDateState = 3
		iSubStateStatus = 0				
		RETURN
	ENDIF
	//--- End of CHEATS

	//--- See if player has reached the destination			
   	GOSUB GF_Date_GetDestination // Returns in iCurrentArea: -1 if invalid, otherwise the visible area num. 
	IF iCurrentArea > -1			  
		IF IS_BIT_SET iDateReport EAT_OUT 
 			//--- Restaurant reached - Set the transition
			GOSUB GF_Date_RemoveAllBlipsAndCounters
			DO_FADE 0 FADE_OUT
			iDateState = 2
			iSubStateStatus = 0				
			RETURN
		ENDIF
		IF IS_BIT_SET iDateReport DANCE
	 			//--- Bar reached - Set the transition
				GOSUB GF_Date_RemoveAllBlipsAndCounters
				iDateState = 10
				iSubStateStatus = 0				
			RETURN
		ENDIF
	ELSE
		IF iCurrentArea = -2
			//--- Home Reached - Set the transition
			GOSUB GF_Date_RemoveAllBlipsAndCounters
			iDateState = 3
			iSubStateStatus = 0
			RETURN
		ENDIF
	ENDIF

//-----------------------------------------------
//--- SEQUENCED CHECKS AND TRANSITIONS START HERE
//-----------------------------------------------
	

	SWITCH iTransitionStages
		CASE 0
			//--- GIFT OR KISS: player must be at right position & press L1 to trigger.
			GOSUB GF_Date_IsPlayerAtKissDistance // Returns iTemp 0 or GF_PLAYER_GIVE_GIFT, GF_PLAYER_GIVE_KISS
			IF iTemp > 0
				IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
					IF iDateState = 1 // Idle On Foot
					AND NOT IS_BIT_SET iDateFlags BORED_TAKE_ME_HOME // Girlfriend Got Bored
					AND NOT IS_BIT_SET iDateFlags BORED_WARNING
					AND NOT IS_BIT_SET iDateReport PLAYER_TWO_TIMING // Not doing the two-timing stuff
						IF NOT IS_BIT_SET iDateFlags 3 // Is L1 Button Down
							SET_BIT iDateFlags 3 //L1 Button Down 
							IF iTemp = GF_PLAYER_GIVE_GIFT
								//--- Enter the Gift-Giving State
								iDateState = 11
								iSubStateStatus = 0
								RETURN		
							ELSE
								//--- Enter the Kissing State
								iDateState = 6
								iSubStateStatus = 0
								RETURN		
							ENDIF
						ENDIF
					ENDIF
				ELSE
					CLEAR_BIT iDateFlags 3
				ENDIF
			ENDIF
			++iTransitionStages
		BREAK

		CASE 1
			//--- GENERIC DRIVE-BY
			IF NOT IS_BIT_SET iDateFlags DESTINATION_BACK_HOME //Take GF home			
				IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_GANG_FIGHTS
				OR IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_GANG_ZONES
					//--- Store the current zone this frame (IMPORTANT! txtCurrZone will be read by a few states)
					GET_CHAR_COORDINATES scplayer fX[0] fY[0] fZ[0] 
					GET_NAME_OF_INFO_ZONE fX[0] fY[0] fZ[0] txtCurrZone
					//--- Date specific: check if the girl did enough shooting, but not if this is a DRIVE date
					IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_GANG_FIGHTS
					AND NOT IS_BIT_SET iDateReport DRIVE 
						GET_AMMO_IN_CHAR_WEAPON iGF_ped WEAPONTYPE_MICRO_UZI iTemp				
						IF iTemp = 0 // No more ammo			
							ADD_AMMO_TO_CHAR iGF_ped WEAPONTYPE_MICRO_UZI GF_AMMO_IN_UZI // Add some spare ammo
							iGFSayContext = CONTEXT_GLOBAL_GFRIEND_TAKE_HOME_HAPPY
							SET_BIT iDateReport DATE_WAS_SUCCESS // Mark this DATE as a SUCCESS
							GOSUB GF_Date_RemoveAllBlipsAndCounters
							GOSUB GF_Date_SwitchOffAllEntryExits							
							SET_BIT iDateFlags DRIVE_BY_DONE // flag drive by as done
							SET_BIT iDateFlags DESTINATION_BACK_HOME //Take GF home
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			++iTransitionStages
		BREAK

		CASE 2		   
			//--- Is player in car or on foot?
			IF IS_CHAR_IN_ANY_CAR iGF_ped
				//--- She is in a car
				IF IS_BIT_SET iDateReport GIRL_DRIVE // Girlfriend wants to drive				
				AND NOT iDateState = 8
					iDateState = 8
					iSubStateStatus = 0
					RETURN
				ENDIF
				IF NOT iDateState = 4
					//--- Set the IDLE ON CAR state
					iDateState = 4
					iSubStateStatus = 0
					RETURN
				ELSE // Was already in car
					//--- Check if player is trashing the car
					IF NOT IS_BIT_SET iDateReport DRIVE	// Skip this if in drive date as there are other checks
						IF iCurrentCarHealth < GF_CAR_DAMAGE_LIMIT_FOR_END_DATE
						AND NOT IS_BIT_SET iDateFlags BORED_TAKE_ME_HOME // Girlfriend Got Bored 
							//--- The GF is pissed off, ends the date
							SET_BIT iDateFlags BORED_TAKE_ME_HOME // Girlfriend Got Bored
								iDateState = 5 // Dump player - ask to be taken home
								iSubStateStatus = 0
							RETURN		
						ENDIF
					ENDIF
					//--- Check if the player is parking
					IF IS_BIT_SET iDateFlags 5 // Parking Detected
					AND NOT IS_BIT_SET iDateFlags BORED_TAKE_ME_HOME // Girlfriend Got Bored
					AND NOT IS_BIT_SET iDateFlags 6 // Car BlowJob Already Given
					AND NOT IS_CHAR_ON_ANY_BIKE scplayer // Player is not on a bike
					AND NOT IS_BIT_SET iDateFlags SKIP_BLOWJOB
					AND NOT IS_BIT_SET iDateReport PLAYER_TWO_TIMING // not running the two-timing stuff
						//--- Player is 'parking' 
						//--- Enter the Car BlowJob State
						iDateState = 7
						iSubStateStatus = 0			
						RETURN
					ENDIF
				ENDIF
			ELSE 
				//--- He is on foot
				IF NOT iDateState = 1
					//--- Set the IDLE ON FOOT state
					iDateState = 1
					iSubStateStatus = 0							
					RETURN
				ENDIF
			ENDIF
			++iTransitionStages
		BREAK

		CASE 3
			//--- If she is not with player
			IF TIMERA > 10000
			AND NOT IS_GROUP_MEMBER iGF_ped players_group
			AND NOT IS_BIT_SET iDateReport PLAYER_TWO_TIMING
				IF iDateState = 1 // Idle On Foot 
				OR iDateState = 4 // Idle In Car
					SET_BIT iDateFlags BORED_TAKE_ME_HOME // Girl Got Bored\Annoyed
					CLEAR_PRINTS
					CLEAR_HELP
					PRINT_HELP GF_H017 // You've abandoned your girlfriend. Your date is over.
					GOSUB GF_Date_Cleanup				
					RETURN
				ENDIF
			ENDIF
			++iTransitionStages
		BREAK
				
		CASE 4
			//--- Check if player's beating up the girl 
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR iGF_ped scplayer
				IF NOT IS_CHAR_HEALTH_GREATER iGF_ped GF_ALLOWED_HEALTH_LOSS_BY_PLAYER
					SET_BIT iDateFlags GIRL_ATTACKED_BY_PLAYER
					PRINT_HELP GF_H018 // You've annoyed your girlfriend. Your date is over.
					iDateState = 9 // Flee Player
					iSubStateStatus = 0
					RETURN
				ELSE
					iGFSayContext = CONTEXT_GLOBAL_GFRIEND_HIT_BY_PLAYER_WARNING
					CLEAR_CHAR_LAST_DAMAGE_ENTITY iGF_ped
				ENDIF			
			ENDIF
			++iTransitionStages
		BREAK

		CASE 5
	 		//--- If not getting anywhere & getting bored
			IF TIMERA > GF_TIME_MAX_BEFORE_BORED_WARNING //some minutes
			AND NOT IS_BIT_SET iDateFlags BORED_WARNING
				IF iGFidx = BARBARA
				OR iGFidx = KYLIE
					//--- Add more time to the country girls
					IF TIMERA > GF_TIME_MAX_BORED_WARNING_OUT_OF_TOWN
						GOSUB GF_Date_Bored_Warning
						RETURN
					ENDIF
				ELSE
					//--- All the other girls
					GOSUB GF_Date_Bored_Warning
					RETURN
				ENDIF
			ENDIF
			++iTransitionStages
		BREAK

		CASE 6
			IF TIMERA > GF_TIME_BEFORE_BORED_END_OF_DATE //some more minutes
			AND NOT IS_BIT_SET iDateFlags BORED_TAKE_ME_HOME // Girlfriend Got Bored (take me home message given)
				IF iGFidx = BARBARA
				OR iGFidx = KYLIE
					//--- Add more time to the country girls
					IF TIMERA > GF_TIME_BORED_END_OF_DATE_OUT_OF_TOWN 
						GOSUB GF_Date_Bored_Take_Me_Home
						RETURN
					ENDIF
				ELSE
					//--- All the other girls
					GOSUB GF_Date_Bored_Take_Me_Home
					RETURN
				ENDIF
			ENDIF
			++iTransitionStages
		BREAK

		CASE 7
			IF TIMERA > GF_TIME_BEFORE_BORED_FLEE_PLAYER //some more minutes
			AND IS_BIT_SET iDateFlags BORED_TAKE_ME_HOME // Girlfriend Got Bored (take me home message given)
				//--- Flee the player
				iDateState = 9 
				iSubStateStatus = 0
				RETURN
			ENDIF
			++iTransitionStages
		BREAK

		CASE 8
			//--- Check girl's health
			IF NOT IS_CHAR_HEALTH_GREATER iGF_ped GF_HEALTH_LIMIT_FOR_DEATH 
				iGFLikesPlayer[iGFidx] = GF_IS_DEAD
				TASK_DEAD iGF_ped 
				GOSUB GF_Date_Cleanup
				RETURN
			ENDIF

			//--- If player has wanted status > 2 and she is not a gang girl
			IF IS_WANTED_LEVEL_GREATER PLAYER1 2
			AND NOT IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_GANG_FIGHTS
			AND NOT IS_BIT_SET iDateFlags BORED_TAKE_ME_HOME // Girlfriend Got Bored
				//--- The GF is pissed off, ends the date
				SET_BIT iDateFlags BORED_TAKE_ME_HOME // Girlfriend Got Bored
				iDateState = 5 // Dump player - ask to be taken home
				iSubStateStatus = 0
				RETURN		
			ENDIF
			++iTransitionStages
		BREAK

		CASE 9
			//--- DRIVE TRHU CITY: Comments if the girl likes the area (and FUN increments if requried)	
			IF IS_CHAR_IN_ANY_CAR iGF_ped
			AND NOT IS_CAR_DEAD iCurrentCar
				IF NOT IS_CAR_STOPPED iCurrentCar					
					//--- Check if she likes this area
					IF NOT IS_BIT_SET iDateFlags BORED_TAKE_ME_HOME // Girl Got Bored\Annoyed
					AND NOT IS_BIT_SET iDateFlags DESTINATION_BACK_HOME // Take GF Home				
						GOSUB GF_Date_CheckEnjoymentInCurrentZone // returns in fFunTemp
					ENDIF
				ENDIF
			ENDIF
			++iTransitionStages
		BREAK

		CASE 10
			//--- Player is two-timing
			IF IS_BIT_SET iDateFlags DESTINATION_BACK_HOME //Take GF home
			AND NOT IS_BIT_SET iDateFlags DRIVE  
			AND NOT IS_BIT_SET iDateFlags GIRL_DRIVE
			AND NOT iGF_TT_Status = GF_TT_DO_NOT_RUN // Two-timing aborted
				//--- Check if the player is dating more than 1 girl
				GET_INT_STAT CURRENT_GIRLFRIENDS iTemp
				IF iTemp > 1 // player is dating more than 1 girl
					//--- Check if the player is outside
					GET_AREA_VISIBLE iTemp
					IF iTemp = 0 // player is outside   
						//--- Check in what state the girl is actually in
						IF iDateState = 1 // Idle on Foot
						OR iDateState = 4 // Idle In Car						
							IF NOT IS_BIT_SET iDateReport PLAYER_TWO_TIMING // Not already doing the two-timing stuff
							AND TIMERA > GF_TIME_BEFORE_TRIGGERING_TWOTIME // It's time							
								GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp
								IF iTemp <= GF_CHANCE_TWOTIMING_PERCENT
									iGF_TT_Status = GF_TT_INIT 
				 					SET_BIT iDateReport PLAYER_TWO_TIMING
								ELSE
									//--- no two-timing this time, unless cheating
									IF NOT IS_BIT_SET iDateReport CHEAT_TWO_TIMING
										//--- Mark the status as aborted
										iGF_TT_Status = GF_TT_DO_NOT_RUN
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ELSE
					//--- player is dating only 1 girl, abort
					iGF_TT_Status = GF_TT_DO_NOT_RUN
				ENDIF
			ENDIF											   
		   	//--- If required by the date, compute and print on screen the fun for the current state
		   	IF IS_BIT_SET iDateReport DRIVE
			AND TIMERA >= GF_TIME_BEFORE_START_FUN_CHECKS // the first 30 seconds are free
			AND NOT IS_BIT_SET iDateFlags DESTINATION_BACK_HOME // Take GF Home
				++iTransitionStages	// Carry on with the checks
				BREAK
			ELSE
				fFunTemp = 0.0
			ENDIF
			
			iTransitionStages = 0 // Reset sequence here
		BREAK
		//-----------------------------------------------------------
		//--- DRIVING DATE: FUN INCREMENTS AND TRANSITIONS START HERE
		//-----------------------------------------------------------
		CASE 11			

			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_SNOGGING_IN_PUBLIC
			AND iDateState = 6 // Kissing On Foot
				IF iSubStateStatus = 0 // Increment fun quite a bit at state entry 
					fFunTemp += GF_FUN_INCREMENT_MEDIUM
				ELSE
					fFunTemp += 0.00001 // This is a minimal amount stored purely to avoid a decrement
				ENDIF
			ENDIF

			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_SEX_IN_PUBLIC	
			AND iDateState = 7 // BJ In Car
				IF iSubStateStatus = 0 // Increment fun quite a bit at state entry 
					fFunTemp += GF_FUN_INCREMENT_MEDIUM
				ELSE
					fFunTemp += 0.00001 // This is a minimal amount stored purely to avoid a decrement
				ENDIF
			ENDIF

			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_GANG_FIGHTS
				 IF IS_CHAR_SHOOTING iGF_ped
					fFunTemp += GF_FUN_INCREMENT_BIG
				ENDIF
			ENDIF				
			++iTransitionStages
		BREAK

		CASE 12 
			//--- DRIVING FUN 
			IF IS_CHAR_IN_ANY_CAR iGF_ped
		   		//--- CAR CRASHING FUN
				GET_CAR_HEALTH iCurrentCar iTemp
				//--- Now compare it against the initial health of this car, as retrieved when we boarded it
				IF iTemp < iCurrentCarHealth
					//--- We have hit something. See if the GF likes it or not
					IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_TO_CAUSE_ACCIDENTS_KILL_PEDS
						//--- Se never dislikes damaging the car, and loves hitting other cars
						IF HAS_CAR_BEEN_DAMAGED_BY_CAR iCurrentCar -1
							//--- Increment the fun 
							fFunTemp += GF_FUN_INCREMENT_MEDIUM
							CLEAR_CAR_LAST_DAMAGE_ENTITY iCurrentCar
						ENDIF
					ELSE // dislikes accidents
						//--- Decrement the fun	
						fFunTemp -= GF_FUN_DECREMENT_MEDIUM
					ENDIF
					//--- Store the current health for future checks
					iCurrentCarHealth = iTemp
				ENDIF
			ENDIF
			++iTransitionStages
		BREAK

		CASE 13			 
			//--- SPEED DRIVING & STUNTS FUN
			IF IS_CHAR_IN_ANY_CAR iGF_ped
			AND NOT IS_CAR_DEAD iCurrentCar
				IF NOT IS_CAR_STOPPED iCurrentCar	
					//--- Stunts check
					IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_STUNTS
						IF passenger_said_jump = 1 // global set by Chris R.
						AND NOT IS_BIT_SET iDateFlags REGISTER_STUNT 
							fFunTemp += GF_FUN_INCREMENT_MEDIUM
							SET_BIT iDateFlags REGISTER_STUNT	 
						ENDIF
						IF passenger_said_jump = 0
							CLEAR_BIT iDateFlags REGISTER_STUNT 
						ENDIF
					ENDIF
					//--- Fast driving check			
					GET_CAR_SPEED iCurrentCar fTemp[1] // <= 10.0 slow, 11.0-20.0 cruise, > 25.0 fast
					IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_TO_GO_FAST
						IF fTemp[1] > GF_FAST_CAR_SPEED
							//--- Car is going fast
							fFunTemp += GF_FUN_INCREMENT_MEDIUM
							CLEAR_BIT iDateFlags REGISTER_CAR_SLOW
						ELSE
							//--- Car is going slow
							IF NOT IS_BIT_SET iDateFlags REGISTER_CAR_SLOW 
								SET_BIT iDateFlags REGISTER_CAR_SLOW
								iGFSayContext = CONTEXT_GLOBAL_CAR_SLOW
							ENDIF
							fFunTemp -= GF_FUN_DECREMENT_BIG
						ENDIF
					ENDIF
					//--- Cruise driving check
					IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_TO_CRUISE
						IF fTemp[1] > GF_CRUISE_CAR_SPEED_LOW
						AND fTemp[1] < GF_CRUISE_CAR_SPEED_FAST
							//--- Car speed in the interval defined as 'cruising speed'
							fFunTemp += GF_FUN_INCREMENT_SMALL
							CLEAR_BIT iDateFlags REGISTER_CAR_SLOW
							CLEAR_BIT iDateFlags REGISTER_CAR_FAST
						ELSE
							IF fTemp[1] <= GF_CRUISE_CAR_SPEED_LOW							
								//--- Car is going slow
								IF NOT IS_BIT_SET iDateFlags REGISTER_CAR_SLOW 
									SET_BIT iDateFlags REGISTER_CAR_SLOW
									iGFSayContext = CONTEXT_GLOBAL_CAR_SLOW
								ENDIF
							ELSE								
								//--- Car is going fast
								IF NOT IS_BIT_SET iDateFlags REGISTER_CAR_FAST 
									SET_BIT iDateFlags REGISTER_CAR_FAST
									iGFSayContext = CONTEXT_GLOBAL_CAR_FAST
								ENDIF
							ENDIF					
							fFunTemp -= GF_FUN_DECREMENT_BIG
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			++iTransitionStages
		BREAK

		CASE 14
			//--- DRIVE TRHU CITY FUN: Special check for gang zones	
			IF IS_CHAR_IN_ANY_CAR iGF_ped
			AND NOT IS_CAR_DEAD iCurrentCar
				IF NOT IS_CAR_STOPPED iCurrentCar					
					IF NOT IS_BIT_SET iDateFlags BORED_TAKE_ME_HOME // Girl Got Bored\Annoyed				
			   			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_GANG_ZONES
						   	GOSUB GF_Date_AreEnemiesHere // Returns iTemp  > 0 if this is gang area
							IF iTemp > 0 
								fFunTemp += GF_FUN_INCREMENT_SMALL
							ENDIF		
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			//--- Check (again) if she is shooting
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_GANG_FIGHTS
				IF IS_CHAR_SHOOTING iGF_ped
					fFunTemp += GF_FUN_INCREMENT_BIG
				ENDIF
			ENDIF				
			++iTransitionStages
		BREAK

		CASE 15
			//--- PARKING FUN
			IF IS_CHAR_IN_ANY_CAR iGF_ped
			AND NOT IS_CAR_DEAD iCurrentCar
				IF IS_CAR_STOPPED iCurrentCar
					GET_CURRENT_POPULATION_ZONE_TYPE iTemp2
					//--- Car Stopped 
					IF IS_BIT_SET iGFLikesOnDate[iGFidx] 14	// LIKES_PARKING_ROMANTIC
						IF iTemp2 = POPCYCLE_ZONE_PARK
						OR iTemp2 = POPCYCLE_ZONE_COUNTRYSIDE
							GET_MINUTES_TO_TIME_OF_DAY 6 0 iTemp2 // Sunrise
							IF iTemp <= 30
							OR iTemp >= 1380								 		
								fFunTemp += GF_FUN_INCREMENT_BIG
							ELSE
								GET_MINUTES_TO_TIME_OF_DAY 20 0 iTemp2 // Sunset
								IF iTemp <= 30
								OR iTemp >= 1380								 		
									fFunTemp += GF_FUN_INCREMENT_BIG
								ENDIF
							ENDIF
						ENDIF						
					ENDIF
				ENDIF
			ENDIF
			GOSUB GF_Date_CheckEnjoymentThisSequence
			++iTransitionStages
		BREAK
				
		CASE 16
		 	IF iFun >= 100
			AND NOT IS_BIT_SET iDateFlags DESTINATION_BACK_HOME // Take GF Home
				//--- The GF is happy
				SET_BIT iDateReport DATE_WAS_SUCCESS
				iGFSayContext = CONTEXT_GLOBAL_GFRIEND_TAKE_HOME_HAPPY				
				SET_BIT iDateFlags DESTINATION_BACK_HOME // Take GF Home
				GOSUB GF_Date_RemoveAllBlipsAndCounters
				RETURN	
			ELSE 
				IF iFun <= 0
				AND NOT IS_BIT_SET iDateFlags BORED_TAKE_ME_HOME // Girlfriend Got Bored
					CLEAR_BIT iDateReport DATE_WAS_SUCCESS
					//--- The GF is pissed off, ends the date
					SET_BIT iDateFlags BORED_TAKE_ME_HOME // Girlfriend Got Bored	
					iDateState = 5
					iSubStateStatus = 0
					RETURN
				ENDIF
			ENDIF
			//--- Reset Sequence
			fFunTemp = 0.0
			iTransitionStages = 0
		BREAK

	ENDSWITCH
ENDIF // skip transitions
RETURN
/*****************************************************************************
								GF_DATE STATES
******************************************************************************/
GF_Date_DoCurrentState:
	SWITCH iDateState	   	
   		CASE 0 //---STATE 0: Date Intro Cut
			GOSUB GF_Date_State0
		BREAK
		CASE 1	//---STATE 1: Idle On Foot TRANSITIONAL
			GOSUB GF_Date_State1
		BREAK
		CASE 2	//---STATE 2: Eat Out Location Reached - NON-TRANSITIONAL CUT-SCENE 
			GOSUB GF_Date_State2
		BREAK
		CASE 3	//---STATE 3: Home Reached - NON-TRANSITIONAL CUT-SCENE 
			GOSUB GF_Date_State3
		BREAK
		CASE 4	//---STATE 4: Idle In Car TRANSITIONAL 
			GOSUB GF_Date_State4
		BREAK
		CASE 5	//---STATE 5: Dump the player TRANSITIONAL
			GOSUB GF_Date_State5
		BREAK
		CASE 6	//---STATE 6: Kissing On Foot NON-TRANSITIONAL CUT-SCENE  
			GOSUB GF_Date_State6
		BREAK
		CASE 7	//---STATE 7: BlowJob In Car NON-TRANSITIONAL CUT-SCENE  
			GOSUB GF_Date_State7
		BREAK
		CASE 8	//---STATE 8: Girl Driving Car PARTLY-TRANSITIONAL (entry of state only)
			GOSUB GF_Date_State8
		BREAK
		CASE 9	//---STATE 9: Flee Player NON-TRANSITIONAL
			GOSUB GF_Date_State9
		BREAK
		CASE 10	//---STATE 10: Dance NON-TRANSITIONAL
			GOSUB GF_Date_State10
		BREAK
		CASE 11	//---STATE 11: Gift-Giving NON-TRANSITIONAL
			GOSUB GF_Date_State11
		BREAK
	ENDSWITCH

RETURN
/********************************************
			STATE 0: DATE INIT 
********************************************/
GF_Date_State0:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Date init step 1 - CUT INIT
		SET_BIT iDateFlags 1 // Mark the state as non transitable

		SET_PLAYER_CONTROL player1 OFF
		SET_EVERYONE_IGNORE_PLAYER player1 TRUE			
		IF IS_BIT_SET iDateReport KINKY_SEX		
			//--- Abort the date straight away
			SET_BIT iDateFlags DATE_ABORTED // Date Aborted
			SET_BIT iDateReport DATE_WAS_SUCCESS // Date is always a success in a GIMP SUIT
			SET_BIT iDateReport PLAYER_AGREES_TO_SEX // And always leads to SPANKING SEX		
			iDateState = 3
			iSubStateStatus = 0	
			BREAK
		ENDIF

		SWITCH_WIDESCREEN ON 
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE iGF_ped TRUE
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER iGF_ped TRUE

		GOSUB GF_Date_GetCoordsHomeCut //Returns XYZ arrays: [0]=cam pos, [1]=cam target, [2] back home point, fTemp[0]=GF Heading
		CLEAR_AREA fX[2] fY[2] fZ[2] 2.0 TRUE
			 		
		SET_FIXED_CAMERA_POSITION fX[0] fY[0] fZ[0] 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT fX[1] fY[1] fZ[1] JUMP_CUT

		LOCK_DOOR iGFdoor[iGFidx] FALSE

		GOSUB GF_Date_CheckLikesPlayer // Returns iTemp = -1 if she does NOT like him
		IF iTemp = -1 // GF Does NOT  like player
			SET_BIT iDateFlags GIRL_DOES_NOT_LIKE_PLAYER
			TASK_GOTO_CHAR iGF_ped scplayer 5000 2.5
		ELSE
			SET_GROUP_MEMBER players_group iGF_ped
			LISTEN_TO_PLAYER_GROUP_COMMANDS iGF_ped FALSE
	   	   	SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS PLAYER1 TRUE				
		ENDIF

		TIMERB = 0
		++iSubStateStatus
	BREAK

	CASE 1
		GET_CHAR_COORDINATES scplayer fX[0] fY[0] fZ[0]
		GET_CHAR_COORDINATES iGF_ped fX[1] fY[1] fZ[1]
		GET_DISTANCE_BETWEEN_COORDS_3D fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1] fTemp[0]

		IF TIMERB >= 6000
			//--- Warp the girl by the player
			GET_CLOSEST_CHAR_NODE fX[1] fY[1] fZ[1] fX[1] fY[1] fZ[1]			
			SET_CHAR_COORDINATES iGF_ped fX[1] fY[1] fZ[1]
			//-- Let the bit below carry on
			fTemp[0] = 1.0
		ENDIF
   
		IF fTemp[0] <= 3.0		   			 		
			POINT_CAMERA_AT_POINT fX[0] fY[0] fZ[0] INTERPOLATION
			//--- Lock the door
			LOCK_DOOR iGFdoor[iGFidx] TRUE
			//--- See if previous check found that GF dilikes player in its current state (look, car, etc.)
			IF IS_BIT_SET iDateFlags GIRL_DOES_NOT_LIKE_PLAYER
				//--- Abort the date straight away
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER iGF_ped FALSE
				SET_BIT iDateFlags DATE_ABORTED // Date Aborted				
				iDateState = 3
				iSubStateStatus = 3	
				BREAK   
			ELSE
				//--- Greeting
				iGFSayContext = CONTEXT_GLOBAL_GFRIEND_HELLO
				iCJSayContext = CONTEXT_GLOBAL_GREETING_GFRIEND				
				TIMERB = 0
				++iSubStateStatus
			ENDIF
		ENDIF 
	BREAK

	CASE 2	
		IF TIMERB >= 3000	   
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE iGF_ped FALSE
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER iGF_ped FALSE
			SET_PLAYER_CONTROL player1 ON
			SET_EVERYONE_IGNORE_PLAYER player1 FALSE
			SWITCH_WIDESCREEN OFF
			SET_ALL_CARS_CAN_BE_DAMAGED TRUE
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT			
		   ++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 3
		//--- GF declares her desires for the current date
		GOSUB GF_Date_DeclareDesiresForThisDate

		//--- Print help appropriate to date
		IF IS_BIT_SET iDateReport EAT_OUT
			PRINT_HELP GF_0036 //HELP FOR FOOD
		ENDIF

		IF IS_BIT_SET iDateReport DRIVE
			PRINT_HELP GF_0037 //HELP FOR DRIVE
		ENDIF

		IF IS_BIT_SET iDateReport DANCE
			PRINT_HELP GF_0038 //HELP FOR DANCE
		ENDIF

		//--- Enter the IDLE state by default		
		CLEAR_BIT iDateFlags 1 // Mark the next state as transitable
		iDateState = 1
		iSubStateStatus = 0
			   
	BREAK
		
	ENDSWITCH

RETURN
/********************************************
		STATE 1: IDLE ON FOOT
********************************************/
GF_Date_State1:  
	SWITCH iSubStateStatus	 				

	CASE 0		
		CLEAR_BIT iDateFlags 5 // Parking Detected
		CLEAR_BIT iDateFlags 6 // Car BlowJob Given
		++iSubStateStatus
	BREAK

	CASE 1			
		IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_GANG_FIGHTS
			IF NOT IS_BIT_SET iDateFlags BORED_TAKE_ME_HOME // Girlfriend Got Bored
			AND NOT IS_CHAR_RESPONDING_TO_EVENT iGF_ped EVENT_ACQUAINTANCE_PED_HATE
				IF IS_CURRENT_CHAR_WEAPON iGF_ped WEAPONTYPE_MICRO_UZI // given when she started a drive-by	  
					SET_CURRENT_CHAR_WEAPON iGF_ped WEAPONTYPE_UNARMED
				ENDIF 
			ENDIF
		ENDIF
		++iSubStateStatus
	BREAK

	CASE 2
		//--- Check for random bit of conversation
		IF NOT IS_BIT_SET iDateReport PLAYER_TWO_TIMING // in the middle of the two timing stuff
			IF IS_BIT_SET iDateFlags READY_FOR_RANDOM_SPEECH 
			AND NOT IS_BIT_SET iDateFlags BORED_TAKE_ME_HOME // Girlfriend Got Bored
				IF TIMERB > GF_TIME_FOR_RANDOM_SPEECH 
					iGFSayContext = CONTEXT_GLOBAL_GFRIEND_STORY
					iCJSayContext = CONTEXT_GLOBAL_UH_HUH
					CLEAR_BIT iDateFlags READY_FOR_RANDOM_SPEECH
				ENDIF
			ELSE			
				TIMERB = 0
				SET_BIT iDateFlags READY_FOR_RANDOM_SPEECH
			ENDIF	
		ENDIF
		iSubStateStatus = 0
	BREAK
	ENDSWITCH
RETURN
/********************************************
	STATE 2: EAT OUT DESTINATION REACHED
********************************************/
GF_Date_State2:  
	SWITCH iSubStateStatus	 				

	CASE 0
		
		SET_BIT	iDateFlags	1 // This state does not allow transitions
		//--- The player has just entered the Entry-Exit of a valid eating place		
		DO_FADE 0 FADE_OUT
		GOSUB GF_Date_RemoveAllBlipsAndCounters			
		CLEAR_PRINTS
		CLEAR_HELP
		++iSubStateStatus
	BREAK

	CASE 1
		IF NOT GET_FADING_STATUS
			//--- Cut scene set-up		 
			MAKE_PLAYER_SAFE_FOR_CUTSCENE PLAYER1
			SET_CURRENT_CHAR_WEAPON iGF_ped WEAPONTYPE_UNARMED
			SET_CHAR_COLLISION iGF_ped FALSE 							
			SET_CHAR_COLLISION scplayer FALSE							
			GOSUB GF_Date_AppendGirlToCutscene
			GOSUB GF_Date_SetUpCutAtLocation
			++iSubStateStatus	   			
		ENDIF
	BREAK

	CASE 2		
		IF HAS_CUTSCENE_LOADED 
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 3	  
		SET_CUTSCENE_OFFSET fX[0] fY[0] fZ[0]			   
		DO_FADE 1000 FADE_IN
		SWITCH_RUBBISH OFF
		START_CUTSCENE 
		++iSubStateStatus	
	BREAK

   	CASE 4
		//--- CutScene Subtitles Here
		// GET_CUTSCENE_TIME iTemp
		++iSubStateStatus				
	BREAK
	
	CASE 5
		IF HAS_CUTSCENE_FINISHED
			DO_FADE 1000 FADE_OUT
			CLEAR_PRINTS
			++iSubStateStatus
		ENDIF
	BREAK

	
	CASE 6
		IF NOT GET_FADING_STATUS
			CLEAR_CUTSCENE

			//--- Restore normal colour 
			CLEAR_EXTRA_COLOURS FALSE

			GOSUB GF_Date_SwitchOffAllEntryExits // we don't want the player to accidentally enter one of these again

			SET_AREA_VISIBLE 0
			
			SET_CHAR_AREA_VISIBLE iGF_ped 0
			SET_CHAR_AREA_VISIBLE scplayer 0

			GET_CHAR_COORDINATES scplayer fX[0] fY[0] fZ[0 			
			CLEAR_AREA fX[0] fY[0] fZ[0] 2.0 TRUE

			LOAD_SCENE fX[0] fY[0] fZ[0]
			REQUEST_COLLISION fX[0] fY[0]

			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			CLEAR_CHAR_TASKS_IMMEDIATELY iGF_ped

			IF NOT IS_GROUP_MEMBER iGF_ped players_group
				SET_GROUP_MEMBER players_group iGF_ped				
				LISTEN_TO_PLAYER_GROUP_COMMANDS iGF_ped FALSE
				SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS PLAYER1 TRUE
			ENDIF
	
			++iSubStateStatus

		ENDIF

	BREAK

	CASE 7
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE iGF_ped FALSE

		SET_EVERYONE_IGNORE_PLAYER player1 FALSE
		SWITCH_WIDESCREEN OFF
		DISPLAY_RADAR TRUE
		SET_CHAR_COLLISION iGF_ped TRUE 							
		SET_CHAR_COLLISION scplayer TRUE
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		SET_PLAYER_CONTROL player1 ON 		
		SWITCH_RUBBISH ON

		CLEAR_CHAR_TASKS scplayer
		TASK_PAUSE iGF_ped 0
		
 		DO_FADE 500 FADE_IN
		++iSubStateStatus
	BREAK

	CASE 8
		IF NOT GET_FADING_STATUS
			//--- First speech request:
			IF IS_BIT_SET iDateReport DATE_WAS_SUCCESS // DATE WAS A SUCCESS
				iGFSayContext = CONTEXT_GLOBAL_GFRIEND_ENJOYED_MEAL_HIGH				
			ELSE
				iGFSayContext = CONTEXT_GLOBAL_GFRIEND_ENJOYED_EVENT_LOW				
			ENDIF			
			++iSubStateStatus
		ENDIF  
	BREAK

	CASE 9
		//--- Next speech request
		IF iGFSayContext = -1
		AND NOT IS_MESSAGE_BEING_DISPLAYED  
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_TAKE_HOME_HAPPY
			CLEAR_BIT iDateFlags 1 // Allow transitions again
			SET_BIT iDateFlags DESTINATION_BACK_HOME // Mark GF wants to go back home
			//--- State Complete - Back to IDLE on Foot
			iDateState = 1
			iSubStateStatus = 0	 
		ENDIF
	BREAK

	ENDSWITCH

RETURN
/********************************************
			STATE 3: DATE END
********************************************/
GF_Date_State3:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Player has reached the GF's house 
		SET_BIT	iDateFlags	1 // This state does not allow transitions		
		DO_FADE 500 FADE_OUT				
		++iSubStateStatus															
	BREAK

	CASE 1
		SET_BIT	iDateFlags	1 // repeated from above (to no harm) for all transitions ending straigh in here		
		IF NOT GET_FADING_STATUS
			
			GOSUB GF_Date_RemoveAllBlipsAndCounters	

			SET_PLAYER_CONTROL player1 OFF
			SET_EVERYONE_IGNORE_PLAYER player1 TRUE			

			IF IS_GROUP_MEMBER iGF_ped players_group
				REMOVE_CHAR_FROM_GROUP iGF_ped
			ENDIF

			SWITCH_WIDESCREEN ON
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE iGF_ped TRUE
			
			GOSUB GF_Date_GetCoordsHomeCut //Returns XYZ arrays: [0]=cam pos, [1]=cam target, [2] GF walk to, fTemp[0]=GF Heading
			CLEAR_AREA fX[2] fY[2] fZ[2] 1.0 TRUE 		
			SET_FIXED_CAMERA_POSITION fX[0] fY[0] fZ[0] 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT fX[1] fY[1] fZ[1] JUMP_CUT

		 	CLEAR_CHAR_TASKS_IMMEDIATELY iGF_ped
		 	SET_CHAR_COORDINATES iGF_ped fX[2] fY[2] fZ[2] 			
			TASK_ACHIEVE_HEADING iGF_ped fTemp[0]
		 	++iSubStateStatus
		ENDIF
	BREAK

	CASE 2			
		DO_FADE 1000 FADE_IN
		++iSubStateStatus
	BREAK

	CASE 3

		IF NOT GET_FADING_STATUS						
			IF IS_BIT_SET iDateFlags DATE_ABORTED // Date Aborted
				//--- Find the reason why the date has been aborted at the start...
				IF IS_BIT_SET iDateReport KINKY_SEX
					iGFSayContext = CONTEXT_GLOBAL_GFRIEND_REQ_SEX_DESPERATE		
					//--- Skip to the end
					TIMERB = 0
					iTemp = 5000
					iSubStateStatus = 6
					BREAK
				ENDIF
				//--- Check if she hates him
				IF iGFLikesPlayer[iGFidx] = GF_DUMP_PLAYER_IMMEDIATELY
					//--- Dump him!						 			
					iGFLikesPlayer[iGFidx] = GF_HATES_PLAYER
					iGFSayContext = CONTEXT_GLOBAL_GFRIEND_DUMP_PLAYER_LIVE					 
					CLEAR_BIT iActiveGF iGFidx  // remove the girlfriend
				ELSE
					//--- Check if he looks like shit
					IF IS_BIT_SET iDateFlags GIRL_DOES_NOT_LIKE_PLAYER
						IF IS_BIT_SET iDateFlags COMMENT_DISLIKE_PHYSIQUE
							iGFSayContext = CONTEXT_GLOBAL_GFRIEND_PHYSIQUE_CRITIQUE
						ELSE
							iGFSayContext = CONTEXT_GLOBAL_GFRIEND_SEX_APPEAL_TOO_LOW								
						ENDIF	
					ENDIF
				ENDIF
				//--- Skip to the end
				TIMERB = 0
				iTemp = 5000
				iSubStateStatus = 6 
			ELSE
				//--- Date was not aborted, so see how it went
				IF IS_BIT_SET iDateFlags BORED_TAKE_ME_HOME 
					//--- Girl Got BORED during the date
					iGFSayContext = CONTEXT_GLOBAL_GFRIEND_GOODBYE_ANGRY					
					//--- Skip to the end
					TIMERB = 0
					iTemp = 5000
					iSubStateStatus = 6
				ELSE
					IF IS_BIT_SET iDateReport DATE_WAS_SUCCESS 
						//--- The date was GOOD
						IF iGFLikesPlayer[iGFidx] >= iGFSelfRespect[iGFidx] 
							//--- She wants to have sex
							iGFSayContext = CONTEXT_GLOBAL_GFRIEND_COFFEE
							TIMERB = 0
							iTemp = 0
							++iSubStateStatus 
						ELSE
							//--- Let's meet again
							iGFSayContext = CONTEXT_GLOBAL_GFRIEND_MEET_AGAIN
							iCJSayContext = CONTEXT_GLOBAL_PARTING_GFRIEND														
							//--- Skip to the end
							TIMERB = 0
							iTemp = 5000
							iSubStateStatus = 6
						ENDIF
					ELSE // Date was a FAILURE
						//--- Check if she still wants to date him
						IF iGFLikesPlayer[iGFidx] <= GF_LIKES_PLAYER_LOW_LIMIT
							//--- Dump him!						 			
							iGFSayContext = CONTEXT_GLOBAL_GFRIEND_DUMP_PLAYER_LIVE
						ELSE
							//--- Girl is upset
							iGFSayContext = CONTEXT_GLOBAL_GFRIEND_GOODBYE_ANGRY
						ENDIF
						//--- Skip to the end
						TIMERB = 0
						iTemp = 5000
						iSubStateStatus = 6
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	BREAK	

	CASE 4
		IF TIMERB >= 5000
			//--- Display Choice text
			PRINT_HELP_FOREVER TALK_1  
			//--- Wait a little bit before reading the DPAD 
			IF TIMERB > 5100 
				++iSubStateStatus
			ENDIF		
		ENDIF
	BREAK

	CASE 5

		//--- Read pad for choice:  LEFTARROW = NO , RIGHTARROW = YES
		IF IS_BUTTON_PRESSED PAD1 DPADRIGHT 
			CLEAR_PRINTS
			CLEAR_HELP
			SET_BIT iDateReport PLAYER_AGREES_TO_SEX // Player agrees to SEX			 
			iGFSayContext = GF_CONTEXT_JUSTPLAYER
			iCJSayContext = CONTEXT_GLOBAL_COFFEE_ACCEPT
			TIMERB = 0
			iTemp = 3500
			++iSubStateStatus
		ELSE
			IF IS_BUTTON_PRESSED PAD1 DPADLEFT
				CLEAR_PRINTS
				CLEAR_HELP
				CLEAR_BIT iDateReport PLAYER_AGREES_TO_SEX // Player declines SEX
				iGFSayContext = GF_CONTEXT_JUSTPLAYER
				iCJSayContext = CONTEXT_GLOBAL_COFFEE_DECLINE
				TIMERB = 0
				iTemp = 3500
				++iSubStateStatus
			ENDIF
		ENDIF 
	BREAK

	CASE 6
		IF TIMERB >= iTemp					    
			//--- Say goodbye here????
			//iGFSayContext = CONTEXT_GLOBAL_GFRIEND_GOODBYE
			//--- Date Ended
			SET_BIT iDateReport GIRL_IS_BACK_AT_HOME
			DO_FADE 1000 FADE_OUT
			++iSubStateStatus			
		ENDIF
	BREAK
	
	CASE 7
		IF NOT GET_FADING_STATUS
			//--- On a black screen, terminate
			SWITCH_WIDESCREEN OFF
			DISPLAY_RADAR TRUE
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			SET_PLAYER_CONTROL player1 ON 		
			SET_EVERYONE_IGNORE_PLAYER player1 FALSE
			GOSUB GF_Date_Cleanup
		ENDIF
	BREAK
		
	ENDSWITCH

RETURN
/********************************************
		STATE 4: IDLE IN CAR
********************************************/
GF_Date_State4:  
	SWITCH iSubStateStatus	 				

	CASE 0		
		IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_GANG_FIGHTS
		AND NOT IS_BIT_SET iDateFlags DESTINATION_BACK_HOME //Take GF home
			//--- Do the drive-by checks here first
			IF NOT IS_BIT_SET iDateFlags BORED_TAKE_ME_HOME // Girlfriend Got Bored
				IF NOT IS_BIT_SET iDateFlags DRIVE_BY_DONE
					//--- See if we are in gangland then pop in a comment for picking a fight.
					GOSUB GF_Date_AreEnemiesHere // Returns iTemp  > 0 if this is gang area
					IF iTemp > 0						 
						//--- Trigger speech to comment about doing a drive-by
						IF NOT IS_MESSAGE_BEING_DISPLAYED
						AND NOT IS_CHAR_TALKING scplayer 
						AND NOT IS_CHAR_TALKING iGF_ped
							iGFSayContext = CONTEXT_GLOBAL_GFRIEND_DO_A_DRIVEBY					
							iCJSayContext = CONTEXT_GLOBAL_AGREE_TO_DO_DRIVEBY
							//--- Enter drive-by sub-state
							iSubStateStatus = 4
							BREAK
						ENDIF
					ENDIF
				ENDIF
			ENDIF		
		ENDIF
		++iSubStateStatus
	BREAK

	CASE 1
		//--- Check for Parking
		IF NOT IS_CAR_DEAD iCurrentCar
			IF IS_CAR_STOPPED iCurrentCar					 
			 	IF NOT IS_BIT_SET iDateFlags 5	// Parking not yet detected
				AND NOT IS_BIT_SET iDateReport PLAYER_TWO_TIMING // in the middle of the two timing stuff
					TIMERB = 0
					//--- Enter parking sub-state
					iSubStateStatus = 3
					BREAK
				ENDIF			
			ELSE
				CLEAR_BIT iDateFlags SKIP_BLOWJOB
				CLEAR_BIT iDateFlags 5	// Parking detected			
			ENDIF
		ELSE
			CLEAR_BIT iDateFlags SKIP_BLOWJOB
			CLEAR_BIT iDateFlags 5	// Parking detected			
		ENDIF
		++iSubStateStatus
	BREAK

	CASE 2
		//--- Check for random bit of conversation
		IF IS_BIT_SET iDateFlags READY_FOR_RANDOM_SPEECH
			IF NOT IS_BIT_SET iDateReport PLAYER_TWO_TIMING // in the middle of the two timing stuff
				IF NOT IS_BIT_SET iDateFlags BORED_TAKE_ME_HOME // Girlfriend Got Bored					
					IF TIMERB > GF_TIME_FOR_RANDOM_SPEECH 
						iGFSayContext = CONTEXT_GLOBAL_GFRIEND_STORY
						iCJSayContext = CONTEXT_GLOBAL_UH_HUH
						CLEAR_BIT iDateFlags READY_FOR_RANDOM_SPEECH
					ENDIF
				ENDIF
			ENDIF
		ELSE
			IF NOT IS_BIT_SET iDateReport PLAYER_TWO_TIMING // in the middle of the two timing stuff
				TIMERB = 0
				SET_BIT iDateFlags READY_FOR_RANDOM_SPEECH
			ENDIF
		ENDIF
		//--- Loop back
		iSubStateStatus = 0
	BREAK

	CASE 3
		//--- Calculating the parking time, will break out as soon as the car moves.
		IF NOT IS_CAR_DEAD iCurrentCar
			IF NOT IS_CAR_STOPPED iCurrentCar
				iSubStateStatus = 0
			ELSE
				IF TIMERB > GF_TIME_STOPPED_CONSIDERED_PARKING
					SET_BIT iDateFlags 5 // Parking Detected				
					iSubStateStatus = 0
				ENDIF
			ENDIF	
		ELSE
			iSubStateStatus = 0
		ENDIF
	BREAK
	
	CASE 4
		//--- Girl is in drive-by mode here, so no parking checks etc. until there are no enemies around
		IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_GANG_FIGHTS
		AND NOT IS_BIT_SET iDateFlags DESTINATION_BACK_HOME //Take GF home
			IF NOT IS_CURRENT_CHAR_WEAPON iGF_ped WEAPONTYPE_MICRO_UZI
				//--- Equip the shooter
				SET_CURRENT_CHAR_WEAPON iGF_ped WEAPONTYPE_MICRO_UZI
			ENDIF		
			//--- See if we have left gang areas, and reset if appropriate
			GOSUB GF_Date_AreEnemiesHere // Returns iTemp  > 0 if this is gang area
			IF iTemp <= 0
				//--- We have left the gang area, loop back 
				SET_CURRENT_CHAR_WEAPON iGF_ped WEAPONTYPE_UNARMED
				iSubStateStatus = 0
			ENDIF
		ELSE
			//--- We should not be here in the first place!
			iSubStateStatus = 0
		ENDIF
	BREAK
	ENDSWITCH

RETURN
/********************************************
		STATE 5: DUMP PLAYER
********************************************/
GF_Date_State5:  
	SWITCH iSubStateStatus	 				

	CASE 0
		CLEAR_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A FAILURE
		SET_BIT iDateFlags DESTINATION_BACK_HOME // Take GF Home
		SET_BIT iDateFlags BORED_TAKE_ME_HOME // Girl Got Bored		
		iGFSayContext = CONTEXT_GLOBAL_GFRIEND_TAKE_HOME_ANGRY		
		GOSUB GF_Date_RemoveAllBlipsAndCounters
		GOSUB GF_Date_SwitchOffSpecialEntryExits
		iSwitchOffDanceMiniGame = 1 // Switch off the dance mini-game
	BREAK
		
	ENDSWITCH

RETURN
/********************************************
		STATE 6: ON FOOT KISSING
********************************************/
GF_Date_State6:  
	SWITCH iSubStateStatus	 				

	CASE 0
		SET_BIT	iDateFlags	1 // This state does not allow transitions 
		CLEAR_HELP
		SET_PLAYER_CONTROL player1 OFF
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE iGF_ped TRUE
		SET_EVERYONE_IGNORE_PLAYER player1 TRUE
		SWITCH_WIDESCREEN ON
		SET_ALL_CARS_CAN_BE_DAMAGED FALSE
		//--- Switch Roads OFF
		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 30.0 30.0 2.0 fX[0] fY[0] fZ[0]
		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -30.0 -30.0 -1.0 fX[1] fY[1] fZ[1]
		SWITCH_ROADS_OFF fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1]
		SWITCH_PED_ROADS_OFF fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1]
		//--- Rotate player and GF to face each other		
		TASK_TURN_CHAR_TO_FACE_CHAR scplayer iGF_ped
		TASK_TURN_CHAR_TO_FACE_CHAR iGF_ped scplayer
		TIMERB = 0
		++iSubStateStatus
	BREAK
	
	CASE 1

		GET_SCRIPT_TASK_STATUS scplayer TASK_TURN_CHAR_TO_FACE_CHAR iTemp
		GET_SCRIPT_TASK_STATUS iGF_ped TASK_TURN_CHAR_TO_FACE_CHAR iTemp2
					
		IF iTemp = FINISHED_TASK
		AND iTemp2 = FINISHED_TASK
		   	//--- Switch collisions OFF
		   	SET_CHAR_COLLISION scplayer FALSE 
		   	SET_CHAR_COLLISION iGF_ped FALSE
		   	//--- Update Player and GF co-ords to match anim 			
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 1.0 0.0 fX[1] fY[1] fZ[1]
			GET_GROUND_Z_FOR_3D_COORD fX[1] fY[1] fZ[1] fZ[1]	
			//---Store the current girl co-ords
			GET_CHAR_COORDINATES iGF_ped fX[2] fY[2] fZ[2] 
			GET_GROUND_Z_FOR_3D_COORD fX[2] fY[2] fZ[2] fZ[2]
			//--- Now move the girl in the right spot
			SET_CHAR_COORDINATES iGF_ped fX[1] fY[1] fZ[1] 
			GET_CHAR_HEADING scplayer fTemp[0]
			fTemp[0] += 180.0
			SET_CHAR_HEADING iGF_ped fTemp[0]  
			 
			//--- Camera Cut
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 1.2 -1.0 0.5 fX[0] fY[0] fZ[0]
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 0.7 0.5 fX[1] fY[1] fZ[1]

			IF IS_LINE_OF_SIGHT_CLEAR fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1] TRUE TRUE FALSE TRUE FALSE
				SET_FIXED_CAMERA_POSITION fX[0] fY[0] fZ[0] 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT fX[1] fY[1] fZ[1] JUMP_CUT
			ENDIF

			TASK_STAND_STILL scplayer -1
			TASK_STAND_STILL iGF_ped -1

		   	++iSubStateStatus			
					 
		ELSE
			//--- Check if the player is facing the girl
			GET_SCRIPT_TASK_STATUS scplayer TASK_TURN_CHAR_TO_FACE_CHAR iTemp
			IF iTemp = FINISHED_TASK			
				//--- wait until she is ready
				GET_SCRIPT_TASK_STATUS scplayer TASK_STAND_STILL iTemp
				IF iTemp = FINISHED_TASK
					TASK_STAND_STILL scplayer -1
				ENDIF
			ENDIF
			//--- Check if the girl is facing the player
			GET_SCRIPT_TASK_STATUS iGF_ped TASK_TURN_CHAR_TO_FACE_CHAR iTemp
			IF iTemp = FINISHED_TASK			
				//--- wait until he is ready
				GET_SCRIPT_TASK_STATUS iGF_ped TASK_STAND_STILL iTemp
				IF iTemp = FINISHED_TASK
					TASK_STAND_STILL iGF_ped -1
				ENDIF
			ENDIF
			//--- emergency timout
			IF TIMERB > 6000
				iSubStateStatus = 6
				BREAK
			ENDIF
		ENDIF
	BREAK

	CASE 2
		//--- Switch collisions back ON
		SET_CHAR_COLLISION scplayer TRUE 
		SET_CHAR_COLLISION iGF_ped TRUE

		fTemp[0] =# iGFSelfRespect[iGFidx] // Reduce the self respect a bit since its just a kiss
		fTemp[0] /= GF_KISS_2_SELFRESPECT_DIVIDER
		fTemp[1] =# iGFLikesPlayer[iGFidx]
		IF fTemp[1] <= fTemp[0] // She likes the player less or same as her reduced self-respect
			//--- Not really going for it...
			TASK_PLAY_ANIM scplayer PLAYA_KISS_01 KISSING 4.0 FALSE FALSE FALSE FALSE 0
			TASK_PLAY_ANIM iGF_ped GRLFRD_KISS_01 KISSING 4.0 FALSE FALSE FALSE FALSE 0

			iSubStateStatus = 6 // Jump to the end
		ELSE
			IF iGFLikesPlayer[iGFidx] <= iGFSelfRespect[iGFidx] // She likes the player less than full s-respect
				//--- Fine Kiss, but she will not get horny for it
				TASK_PLAY_ANIM scplayer PLAYA_KISS_02 KISSING 4.0 FALSE FALSE FALSE FALSE 0
				TASK_PLAY_ANIM iGF_ped GRLFRD_KISS_02 KISSING 4.0 FALSE FALSE FALSE FALSE 0
				iSubStateStatus = 6 // Jump to the end
			ELSE // She likes the player more than full s-respect
				//--- Now we are talking!
				TASK_PLAY_ANIM scplayer PLAYA_KISS_03 KISSING 4.0 FALSE FALSE FALSE FALSE 0
				TASK_PLAY_ANIM iGF_ped GRLFRD_KISS_03 KISSING 4.0 FALSE FALSE FALSE FALSE 0

				//--- Check if there are other peds around there
				GET_CHAR_COORDINATES iGF_ped fX[0] fY[0] fZ[0]
				GET_RANDOM_CHAR_IN_SPHERE fX[0] fY[0] fZ[0] GF_PROXIMITY_OF_PEDS_AS_PUBLIC TRUE TRUE TRUE iTempPed
				IF iTempPed = -1 
					//--- No one here
					IF iCensoredVersion = 0
						//--- Uncensored game
						++iSubStateStatus // move on to BJ
					ELSE
						//--- Censored game
						SET_BIT iDateFlags FOOT_BLOW_GIVEN
						iSubStateStatus = 6 // Jump to the end
					ENDIF
				ELSE
					//--- At least one ped here
					MARK_CHAR_AS_NO_LONGER_NEEDED iTempPed // release the ped right away!!!
					IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_SNOGGING_IN_PUBLIC						
						IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_SEX_IN_PUBLIC
							//--- Check if we are running a censored version of the game
							IF iCensoredVersion = 0 
								//--- Uncensored game
								++iSubStateStatus // move on to BJ
							ELSE
								//--- Censored game
								SET_BIT iDateFlags FOOT_BLOW_GIVEN
								iSubStateStatus = 6 // Jump to the end
							ENDIF
						ELSE // Doesn't like sex in public							
							iSubStateStatus = 6 // Jump to the end
						ENDIF
					ELSE // Doesn't like snogging
						iSubStateStatus = 6 // Jump to the end				
					ENDIF								
				ENDIF
			ENDIF
		ENDIF
	BREAK

	CASE 3
		iSubStateStatus = 6
		BREAK
		    
			IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			IF NOT IS_BIT_SET iDateFlags FOOT_BLOW_GIVEN			
				GET_SCRIPT_TASK_STATUS iGF_ped TASK_PLAY_ANIM iTemp
				IF iTemp = FINISHED_TASK
					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS SCPLAYER 2.5 -1.2 0.5 fX[0] fY[0] fZ[0]
					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS SCPLAYER 0.0 0.7 0.1 fX[1] fY[1] fZ[1]

					IF IS_LINE_OF_SIGHT_CLEAR fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1] TRUE TRUE FALSE TRUE FALSE
						SET_FIXED_CAMERA_POSITION fX[0] fY[0] fZ[0] 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT fX[1] fY[1] fZ[1] JUMP_CUT
					ENDIF

				   	//--- Re-Update Player and GF co-ords to match anim 			
					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 1.0 0.0 fX[1] fY[1] fZ[1]
					GET_GROUND_Z_FOR_3D_COORD fX[1] fY[1] fZ[1] fZ[1]	
					//--- Now move the girl in the right spot
					SET_CHAR_COORDINATES iGF_ped fX[1] fY[1] fZ[1] 
					GET_CHAR_HEADING scplayer fTemp[0]
					fTemp[0] += 180.0
					SET_CHAR_HEADING iGF_ped fTemp[0]  

					TASK_PLAY_ANIM SCPLAYER ASDFRTYUIPOIUYTR ABCDFETS 4.0 FALSE FALSE FALSE TRUE 0
					TASK_PLAY_ANIM iGF_ped ASDFRTYUIPOIUYTR ABCDFETS 4.0 FALSE FALSE FALSE TRUE 0
					SET_BIT iDateFlags FOOT_BLOW_GIVEN
					++iSubStateStatus
				ENDIF
			ELSE
				//--- Skip the blowjob after the first one
				iSubStateStatus = 6
			ENDIF
		ENDIF
		
	BREAK

	CASE 4			
		iSubStateStatus = 6
		BREAK
		
		IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			IF IS_CHAR_PLAYING_ANIM scplayer ASDFRTYUIPOIUYTR
				GET_CHAR_ANIM_CURRENT_TIME scplayer ASDFRTYUIPOIUYTR fTemp[0]
				IF fTemp[0] = 1.0 
					TASK_PLAY_ANIM scplayer ASDFRTYUIPOIUYT ABCDFETS 4.0 TRUE FALSE FALSE TRUE 6000
					TASK_PLAY_ANIM iGF_ped ASDFRTYUIPOIUYT ABCDFETS 4.0 TRUE FALSE FALSE TRUE 6000
					iGFSayContext = CONTEXT_GLOBAL_GFRIEND_HEAD
					TIMERB = 0
					++iSubStateStatus
				ENDIF
			ELSE
				iSubStateStatus = 6
			ENDIF
		ENDIF
	BREAK

	CASE 5
		iSubStateStatus = 6
		BREAK
		
		IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			IF IS_CHAR_PLAYING_ANIM scplayer ASDFRTYUIPOIUYT
				IF TIMERB > 6000
					TASK_PLAY_ANIM scplayer ASDFRTYUIPOIUY ABCDFETS 4.0 FALSE FALSE FALSE FALSE 0
					TASK_PLAY_ANIM iGF_ped ASDFRTYUIPOIUY ABCDFETS 4.0 FALSE FALSE FALSE FALSE 0
					++iSubStateStatus
				ENDIF
			ELSE
				iSubStateStatus = 6
			ENDIF
		ENDIF
	BREAK

	CASE 6			
		//--- Check if we can recompute the stats
		IF NOT IS_BIT_SET iDateFlags KISS_GIVEN 
			SET_BIT iDateFlags KISS_GIVEN 
			//--- Compute the stats now
			IF iGFLikesPlayer[iGFidx] > iTemp
			AND iGFLikesPlayer[iGFidx] < iGFSelfRespect[iGFidx]
				IF ARE_ANY_CHARS_NEAR_CHAR iGF_ped 20.0
				AND IS_BIT_SET iGFLikesOnDate[iGFidx] 27	// LIKES_SNOGGING_IN_PUBLIC
				   	++iGFLikesPlayer[iGFidx]
					//GOSUB GF_Date_SynchStats
				ELSE
					//--- Decrement Likes Player
					--iGFLikesPlayer[iGFidx]
					//GOSUB GF_Date_SynchStats
				ENDIF								
			ELSE
				IF iGFLikesPlayer[iGFidx] > iTemp
				AND iGFLikesPlayer[iGFidx] >= iGFSelfRespect[iGFidx]
					IF ARE_ANY_CHARS_NEAR_CHAR iGF_ped 20.0
					AND IS_BIT_SET iGFLikesOnDate[iGFidx] 27	// LIKES_SNOGGING_IN_PUBLIC						
						//--- Increment Likes Player
						++iGFLikesPlayer[iGFidx] 				   						
						//GOSUB GF_Date_SynchStats
						IF IS_BIT_SET iGFLikesOnDate[iGFidx] 28	// LIKES_SEX_IN_PUBLIC
							//--- Increment Likes Player
							++iGFLikesPlayer[iGFidx]
							//GOSUB GF_Date_SynchStats
						ENDIF
					ELSE // Doesn't like snogging
						//--- Decrement Likes Player
						--iGFLikesPlayer[iGFidx]	
						//GOSUB GF_Date_SynchStats
					ENDIF								
				ENDIF
			ENDIF
		ENDIF
		//--- Now conclude the cut-scene
		fTemp[0] = -1.0

		IF IS_CHAR_PLAYING_ANIM scplayer BJ_STAND_END_P
			GET_CHAR_ANIM_CURRENT_TIME scplayer BJ_STAND_END_P fTemp[0]
		ENDIF
		
		IF IS_CHAR_PLAYING_ANIM scplayer PLAYA_KISS_01
			GET_CHAR_ANIM_CURRENT_TIME scplayer PLAYA_KISS_01 fTemp[0]
		ENDIF

		IF IS_CHAR_PLAYING_ANIM scplayer PLAYA_KISS_02
			GET_CHAR_ANIM_CURRENT_TIME scplayer PLAYA_KISS_02 fTemp[0]
		ENDIF

		IF IS_CHAR_PLAYING_ANIM scplayer PLAYA_KISS_03
			GET_CHAR_ANIM_CURRENT_TIME scplayer PLAYA_KISS_03 fTemp[0]
		ENDIF

		IF fTemp[0] > -1.0 // An anim is playing
			IF fTemp[0] = 1.0 // The animation is finished
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE iGF_ped FALSE
				//--- Restore the girl's co-ords
				SET_CHAR_COORDINATES iGF_ped fX[2] fY[2] fZ[2]
				//--- Switch Roads ON
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 30.0 30.0 1.0 fX[0] fY[0] fZ[0]
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -30.0 -30.0 -1.0 fX[1] fY[1] fZ[1]
				SWITCH_ROADS_BACK_TO_ORIGINAL fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1]
				SWITCH_PED_ROADS_BACK_TO_ORIGINAL fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1]
				//--- Restore collisions etc.
				SET_CHAR_COLLISION scplayer TRUE 
				SET_CHAR_COLLISION iGF_ped TRUE
				SET_PLAYER_CONTROL player1 ON
				SET_EVERYONE_IGNORE_PLAYER player1 FALSE
				SWITCH_WIDESCREEN OFF
				SET_ALL_CARS_CAN_BE_DAMAGED TRUE
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				//--- State Complete - Back to IDLE on Foot
				CLEAR_BIT iDateFlags 1 // This state allows transitions again
				iDateState = 1
				iSubStateStatus = 0
			ENDIF
		ELSE // This is a cut-scene skip
			CLEAR_CHAR_TASKS scplayer
			TASK_PAUSE iGF_ped 0
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE iGF_ped FALSE
			//--- Restore the girl's co-ords			
			SET_CHAR_COORDINATES iGF_ped fX[2] fY[2] fZ[2]
			//--- Switch Roads ON
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 30.0 30.0 1.0 fX[0] fY[0] fZ[0]
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -30.0 -30.0 -1.0 fX[1] fY[1] fZ[1]
			SWITCH_ROADS_BACK_TO_ORIGINAL fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1]
			SWITCH_PED_ROADS_BACK_TO_ORIGINAL fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1]
			//--- Restore collisions etc.
			SET_CHAR_COLLISION scplayer TRUE 
			SET_CHAR_COLLISION iGF_ped TRUE
			SET_PLAYER_CONTROL player1 ON
			SET_EVERYONE_IGNORE_PLAYER player1 FALSE
			SWITCH_WIDESCREEN OFF
			SET_ALL_CARS_CAN_BE_DAMAGED TRUE
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			IF NOT IS_GROUP_MEMBER iGF_ped players_group
				SET_GROUP_MEMBER players_group iGF_ped				
				LISTEN_TO_PLAYER_GROUP_COMMANDS iGF_ped FALSE
				SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS PLAYER1 TRUE
			ENDIF
			//--- State Complete - Back to IDLE on Foot
			CLEAR_BIT iDateFlags 1 // This state allows transitions again
			iDateState = 1
			iSubStateStatus = 0
		ENDIF
	BREAK
		
	ENDSWITCH

RETURN
/********************************************
			STATE 7: BLOWJOB IN CAR
********************************************/
GF_Date_State7:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Check if we are running a censored version of the game
		IF iCensoredVersion = 1
			//--- Censored game
			SET_BIT iDateFlags SKIP_BLOWJOB
			CLEAR_BIT iDateFlags 1 // This state allows transitions again
			iDateState = 4
			iSubStateStatus = 0
			BREAK
		ELSE
			//--- Uncensored game
			SET_BIT	iDateFlags	1 // This state does not allow transitions
			//--- Check Conditions
			IF iGFLikesPlayer[iGFidx] >= iGFSelfRespect[iGFidx]
			AND NOT IS_CAR_DEAD iCurrentCar
			AND NOT IS_CAR_PASSENGER_SEAT_FREE iCurrentCar 0
				GET_CHAR_IN_CAR_PASSENGER_SEAT iCurrentCar 0 iTempPed 
				IF iTempPed = iGF_ped // If the GF is sitting on the passenger seat
					GET_CHAR_COORDINATES iGF_ped fX[0] fY[0] fZ[0]
					GET_RANDOM_CHAR_IN_SPHERE fX[0] fY[0] fZ[0] GF_PROXIMITY_OF_PEDS_AS_PUBLIC TRUE TRUE TRUE iTempPed
					IF iTempPed = -1 
						//--- No One here, go on...
						++iSubStateStatus
					ELSE
						//--- There is at least a ped around here...										
						MARK_CHAR_AS_NO_LONGER_NEEDED iTempPed // release the ped right away!!!
						IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_SEX_IN_PUBLIC
							//--- She likes doing it with people around, so go on...
							++iSubStateStatus
						ELSE
							//--- No Can Do Amigo
							iGFSayContext = CONTEXT_GLOBAL_GFRIEND_PARK_LOCATION_HATE						
							CLEAR_BIT iDateFlags 1 // This state allows transitions again
							SET_BIT iDateFlags SKIP_BLOWJOB
							iDateState = 4
							iSubStateStatus = 0
						ENDIF

					ENDIF
				ELSE // Someone else is sitting on the passenger seat
					//--- State Complete - Back to IDLE In Car
					CLEAR_BIT iDateFlags 1 // This state allows transitions again
					SET_BIT iDateFlags SKIP_BLOWJOB
					iDateState = 4
					iSubStateStatus = 0
				ENDIF
			ELSE // Car invalid, or GF not in passenger seat, or she doesn't like the player enough 
				//--- State Complete - Back to IDLE In Car
				CLEAR_BIT iDateFlags 1 // This state allows transitions again
				SET_BIT iDateFlags SKIP_BLOWJOB
				iDateState = 4
				iSubStateStatus = 0
			ENDIF
		ENDIF
	BREAK

	CASE 1		
		SET_BIT iDateFlags 6 // Car BlowJob Given
		SET_PLAYER_CONTROL player1 OFF
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE iGF_ped TRUE
		SET_EVERYONE_IGNORE_PLAYER player1 TRUE
		SWITCH_WIDESCREEN ON
		SET_ALL_CARS_CAN_BE_DAMAGED FALSE

		//--- Camera Cut
		IF NOT IS_CAR_DEAD iCurrentCar
			ATTACH_CAMERA_TO_VEHICLE iCurrentCar 4.0 4.0 1.0 0.0 0.0 0.0 0.0 JUMP_CUT
		ENDIF

		++iSubStateStatus
	BREAK
	
	CASE 2
		iSubStateStatus = 5
		BREAK
		
		IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			TASK_PLAY_ANIM_NON_INTERRUPTABLE SCPLAYER ASDFGHJKLPOIUY QWERTYUI 4.0 FALSE FALSE FALSE TRUE 0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped ASDFGHJKLPOIUY QWERTYUI 4.0 FALSE FALSE FALSE TRUE 0
			++iSubStateStatus	
		ENDIF

	BREAK

	CASE 3
		iSubStateStatus = 5
		BREAK

		IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			IF IS_CHAR_PLAYING_ANIM scplayer ASDFGHJKLPOIUY 
				GET_CHAR_ANIM_CURRENT_TIME scplayer ASDFGHJKLPOIUY fTemp[0]
				IF fTemp[0] = 1.0 
					TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer ASDFREWQZXCVF QWERTYUI 4.0 TRUE FALSE FALSE TRUE 6000
					TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped ASDFREWQZXCVF QWERTYUI 4.0 TRUE FALSE FALSE TRUE 6000
					iGFSayContext = CONTEXT_GLOBAL_GFRIEND_HEAD
					TIMERB = 0
					++iSubStateStatus
				ENDIF
			ELSE
				iSubStateStatus = 5
			ENDIF
		ENDIF
	BREAK

	CASE 4
		iSubStateStatus = 5
		BREAK
		
		IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			IF TIMERB > 6000
				TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer LKJHGFDSAQWE QWERTYUI 4.0 FALSE FALSE FALSE FALSE 0
				TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped LKJHGFDSAQWE QWERTYUI 4.0 FALSE FALSE FALSE FALSE 0
				++iSubStateStatus
			ENDIF
		ENDIF
	BREAK

	CASE 5
		//--- Compute the statistics
		IF iGFLikesPlayer[iGFidx] >= iGFSelfRespect[iGFidx]
			//--- Increment Likes Player
			++iGFLikesPlayer[iGFidx]
			//GOSUB GF_Date_SynchStats
			IF ARE_ANY_CHARS_NEAR_CHAR iGF_ped 20.0
			AND IS_BIT_SET iGFLikesOnDate[iGFidx] 28	// LIKES_SEX_IN_PUBLIC
				//--- Increment Likes Player
				++iGFLikesPlayer[iGFidx]
				//GOSUB GF_Date_SynchStats
			ENDIF
		ENDIF
		//--- Now conclude the cut-scene
		IF IS_CHAR_PLAYING_ANIM scplayer BJ_CAR_END_P
			GET_CHAR_ANIM_CURRENT_TIME scplayer BJ_CAR_END_P fTemp[0]
			IF fTemp[0] = 1.0
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE iGF_ped FALSE
				SET_PLAYER_CONTROL player1 ON
				SET_EVERYONE_IGNORE_PLAYER player1 FALSE
				SWITCH_WIDESCREEN OFF
				SET_ALL_CARS_CAN_BE_DAMAGED TRUE
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				//--- State Complete - Back to IDLE In Car
				CLEAR_BIT iDateFlags 1 // This state allows transitions again
				iDateState = 4
				iSubStateStatus = 0
			ENDIF
		ELSE // This is a cut-scene skip
			CLEAR_CHAR_TASKS scplayer 
			TASK_PAUSE iGF_ped 0			
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE iGF_ped FALSE
			SET_PLAYER_CONTROL player1 ON
			SET_EVERYONE_IGNORE_PLAYER player1 FALSE
			SWITCH_WIDESCREEN OFF
			SET_ALL_CARS_CAN_BE_DAMAGED TRUE
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			IF NOT IS_GROUP_MEMBER iGF_ped players_group
				SET_GROUP_MEMBER players_group iGF_ped				
				LISTEN_TO_PLAYER_GROUP_COMMANDS iGF_ped FALSE
				SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS PLAYER1 TRUE
			ENDIF
			//--- State Complete - Back to IDLE In Car
			CLEAR_BIT iDateFlags 1 // This state allows transitions again
			iDateState = 4
			iSubStateStatus = 0
		ENDIF
	BREAK
	ENDSWITCH

RETURN
/********************************************
		STATE 8: GIRL DRIVES CAR
********************************************/
GF_Date_State8:  
	SWITCH iSubStateStatus	 				

	CASE 0	
		//--- Check Conditions
		IF NOT IS_CAR_DEAD iCurrentCar
			IF NOT IS_CHAR_ON_ANY_BIKE iGF_ped
			AND NOT IS_CHAR_IN_FLYING_VEHICLE iGF_ped
				GET_MAXIMUM_NUMBER_OF_PASSENGERS iCurrentCar iTemp
				IF iTemp > 0				
					IF IS_CHAR_SITTING_IN_ANY_CAR scplayer
					AND IS_CHAR_SITTING_IN_ANY_CAR iGF_ped
						SET_EVERYONE_IGNORE_PLAYER player1 TRUE
						SET_BIT iDateFlags 1 // At this point we cannot allow more transitions
						++iSubStateStatus
					ENDIF
				ELSE
					IF NOT IS_MESSAGE_BEING_DISPLAYED 
					AND NOT IS_BIT_SET iDateFlags GIRL_DRIVE_HELP_ON 
						PRINT_HELP GF_0049 // Go get a nice car for your girlfriend!
						SET_BIT iDateFlags GIRL_DRIVE_HELP_ON
					ENDIF
				ENDIF
			ELSE
				IF NOT IS_MESSAGE_BEING_DISPLAYED 
				AND NOT IS_BIT_SET iDateFlags GIRL_DRIVE_HELP_ON 
					PRINT_HELP GF_0049 // Go get a nice car for your girlfriend!
					SET_BIT iDateFlags GIRL_DRIVE_HELP_ON
				ENDIF
			ENDIF
		ELSE
			IF NOT IS_MESSAGE_BEING_DISPLAYED 
			AND NOT IS_BIT_SET iDateFlags GIRL_DRIVE_HELP_ON 
				PRINT_HELP GF_0049 // Go get a nice car for your girlfriend!
				SET_BIT iDateFlags GIRL_DRIVE_HELP_ON
			ENDIF
		ENDIF
	BREAK

	CASE 1				
		SET_PLAYER_CONTROL player1 OFF
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE iGF_ped TRUE
		
		//--- Camera Cut
		IF NOT IS_CAR_DEAD iCurrentCar
			SET_CAR_CRUISE_SPEED iCurrentCar 0.0
			ATTACH_CAMERA_TO_VEHICLE iCurrentCar 4.0 4.0 1.0 0.0 0.0 0.0 0.0 JUMP_CUT
		ENDIF
	
		IF IS_GROUP_MEMBER iGF_ped players_group
			REMOVE_CHAR_FROM_GROUP iGF_ped
		ENDIF							  

		++iSubStateStatus
	BREAK
	
	CASE 2
		IF NOT IS_CAR_DEAD iCurrentCar
		    IF NOT IS_CAR_PASSENGER_SEAT_FREE iCurrentCar 0 // Means she is sitting next to player
				TASK_LEAVE_CAR scplayer iCurrentCar 			
			ELSE
				TASK_LEAVE_CAR iGF_ped iCurrentCar
			ENDIF
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 3
	    GET_SCRIPT_TASK_STATUS scplayer TASK_LEAVE_CAR iTemp
		IF iTemp = FINISHED_TASK		   
			GET_SCRIPT_TASK_STATUS iGF_ped TASK_LEAVE_CAR iTemp
			IF iTemp = FINISHED_TASK		   
				IF IS_CHAR_IN_ANY_CAR scplayer 
					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer iCurrentCar
					TASK_ENTER_CAR_AS_PASSENGER iGF_ped iCurrentCar -1 0 
				ELSE					
					STORE_CAR_CHAR_IS_IN_NO_SAVE iGF_ped iCurrentCar
					TASK_SHUFFLE_TO_NEXT_CAR_SEAT iGF_ped iCurrentCar
					TASK_ENTER_CAR_AS_PASSENGER scplayer iCurrentCar -1 0					
				ENDIF
			   ++iSubStateStatus			
		   ENDIF
		ENDIF
	BREAK					   				

	CASE 4
	    GET_SCRIPT_TASK_STATUS scplayer TASK_ENTER_CAR_AS_PASSENGER iTemp
		IF iTemp = FINISHED_TASK		   
			GET_SCRIPT_TASK_STATUS iGF_ped TASK_ENTER_CAR_AS_PASSENGER iTemp
			IF iTemp = FINISHED_TASK
				GET_DRIVER_OF_CAR iCurrentCar iTempPed
				IF iTempPed = scplayer  
					iSubStateStatus = 2
					BREAK
				ELSE
					TASK_CAR_DRIVE_WANDER iGF_ped iCurrentCar 30.0 DRIVINGMODE_AVOIDCARS
					ADD_STUCK_CAR_CHECK iCurrentCar 1.0 1000
					++iSubStateStatus
				ENDIF				 		
			ENDIF
		ENDIF
	BREAK

	CASE 5		
		IF NOT IS_CAR_DEAD iCurrentCar
			POINT_CAMERA_AT_CAR iCurrentCar WHEELCAM JUMP_CUT
			SET_CINEMA_CAMERA TRUE
			PRINT_HELP GF_RAD
			SET_BIT iDateFlags RESET_TIMER_THIS_FRAME // Put in a request to reset TIMERA			 
			++iSubStateStatus  
		ENDIF
	BREAK

	CASE 6	
		//--- CRUISING AROUND
		IF NOT GET_FADING_STATUS
			//--- Put the car back on the road if stuck
			IF IS_CAR_STUCK iCurrentCar
				DO_FADE 500 FADE_OUT
				iSubStateStatus = 9
				BREAK
			ENDIF			

			//--- If player changes the radio station
			IF IS_BUTTON_PRESSED PAD1 DPADUP
				GET_RADIO_CHANNEL iTemp
				++iTemp	
				IF iTemp > 10
					iTemp = 0	
				ENDIF
				SET_RADIO_CHANNEL iTemp
			ENDIF
			IF IS_BUTTON_PRESSED PAD1 DPADDOWN
				GET_RADIO_CHANNEL iTemp
				--iTemp	
				IF iTemp < 0
					iTemp = 10	
				ENDIF
				SET_RADIO_CHANNEL iTemp
			ENDIF
			//--- CODE MISSING	- to make the GF speed up or slow down according to the radio station!

			//--- If player presses TRIANGLE, quit out
			IF IS_BUTTON_PRESSED PAD1 TRIANGLE
				//--- QUIT
				IF NOT IS_BIT_SET iDateFlags 12 // Is TRIANGLE Button Down					
					iSubStateStatus = 10
					SET_BIT iDateFlags 12 
					BREAK
				ENDIF
			ELSE
				CLEAR_BIT iDateFlags 12
			ENDIF
			
			//--- If the girl has driven the car for enough time
			IF TIMERA > 60000 
				GOSUB GF_Date_GetHomeCoords	// Returns fX[0], fY[0], fZ[0] for the current GF
				TASK_CAR_DRIVE_TO_COORD iGF_ped iCurrentCar fX[0] fY[0] fZ[0] 30.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS
				++iSubStateStatus
			ENDIF
		ENDIF
	BREAK

	CASE 7
		//--- DRIVING BACK HOME
		//--- Put the car back on the road if stuck
		IF IS_CAR_STUCK iCurrentCar
			DO_FADE 500 FADE_OUT
			iSubStateStatus = 9
			BREAK
		ENDIF			
		//--- If player changes the radio station
		IF IS_BUTTON_PRESSED PAD1 DPADUP
			GET_RADIO_CHANNEL iTemp
			++iTemp	
			IF iTemp > 10
				iTemp = 0	
			ENDIF
			SET_RADIO_CHANNEL iTemp
		ENDIF
		IF IS_BUTTON_PRESSED PAD1 DPADDOWN
			GET_RADIO_CHANNEL iTemp
			--iTemp	
			IF iTemp < 0
				iTemp = 10	
			ENDIF
			SET_RADIO_CHANNEL iTemp
		ENDIF
		//--- CODE MISSING	- to make the GF speed up or slow down according to the radio station!

			//--- If player presses TRIANGLE, quit out
			IF IS_BUTTON_PRESSED PAD1 TRIANGLE		
				//--- QUIT
				IF NOT IS_BIT_SET iDateFlags 12 // Is TRIANGLE Button Down					
					iSubStateStatus = 10
					SET_BIT iDateFlags 12 
					BREAK
				ENDIF
			ELSE
				CLEAR_BIT iDateFlags 12
			ENDIF

		//--- If player presses TRIANGLE, quit out
		IF IS_BUTTON_PRESSED PAD1 TRIANGLE
			//--- QUIT
			IF NOT IS_BIT_SET iDateFlags 12 // Is TRIANGLE Button Down					
				iSubStateStatus = 10
				SET_BIT iDateFlags 12 
				BREAK
			ENDIF
		ELSE
			CLEAR_BIT iDateFlags 12
		ENDIF
		//--- If the girl has reached home
	    GET_SCRIPT_TASK_STATUS iGF_ped TASK_CAR_DRIVE_TO_COORD iTemp
		IF iTemp = FINISHED_TASK	
			//--- Driving Done			
			CLEAR_HELP
			SET_CINEMA_CAMERA FALSE
			IF DOES_CAR_HAVE_STUCK_CAR_CHECK iCurrentCar 
				REMOVE_STUCK_CAR_CHECK iCurrentCar 
			ENDIF
			SET_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A SUCCESS

			//--- Restore the group situation
			IF NOT IS_GROUP_MEMBER iGF_ped players_group
				SET_GROUP_MEMBER players_group iGF_ped				
				LISTEN_TO_PLAYER_GROUP_COMMANDS iGF_ped FALSE
				SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS PLAYER1 TRUE
			ENDIF
			
			//--- Increment Likes Player
			iGFLikesPlayer[iGFidx] += GF_LIKES_PLAYER_INCREMENT
			//GOSUB GF_Date_SynchStats
			
			//--- Home Reached - Set the transition
			iDateState = 3
			iSubStateStatus = 0
		ENDIF	
	BREAK

	CASE 9	
		//-- Fade in from stuck, never gets in here if not to warp and fade
		IF NOT GET_FADING_STATUS
			GET_CAR_COORDINATES iCurrentCar fX[0] fY[0] fZ[0]
			GET_NTH_CLOSEST_CAR_NODE fX[0] fY[0] fZ[0] 2 fX[1] fY[1] fZ[1] 
			SET_CAR_COORDINATES iCurrentCar fX[1] fY[1] fZ[1]
			DO_FADE 2000 FADE_IN
		    GET_SCRIPT_TASK_STATUS iGF_ped TASK_CAR_DRIVE_TO_COORD iTemp
			IF iTemp = PERFORMING_TASK	
				iSubStateStatus = 7	 // Back to DRIVING BACK HOME State				
			ELSE
				iSubStateStatus = 6	 // Back to CRUISING AROUND State
			ENDIF
		ENDIF
	BREAK

	CASE 10
		//-- Quit out, only gets in here if player presses triangle
		CLEAR_HELP

		//--- Camera Cut
		IF NOT IS_CAR_DEAD iCurrentCar
			CAR_SET_IDLE iCurrentCar
			SET_CINEMA_CAMERA FALSE
			IF DOES_CAR_HAVE_STUCK_CAR_CHECK iCurrentCar 
				REMOVE_STUCK_CAR_CHECK iCurrentCar 
			ENDIF
			ATTACH_CAMERA_TO_VEHICLE iCurrentCar 4.0 4.0 1.0 0.0 0.0 0.0 0.0 JUMP_CUT			
			SWITCH_WIDESCREEN ON
	    	++iSubStateStatus 
		ELSE
			++iSubStateStatus
		ENDIF	    
	BREAK

	CASE 11 // ...continued from above (player quits)
		IF NOT IS_CAR_DEAD iCurrentCar
			IF IS_CAR_STOPPED iCurrentCar
				GET_SCRIPT_TASK_STATUS scplayer TASK_LEAVE_CAR iTemp
				IF iTemp = FINISHED_TASK
				    TASK_LEAVE_CAR scplayer iCurrentCar
					++iSubStateStatus
				ENDIF
			ENDIF
		ELSE
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 12	// ...continued from above (player quits)
	    GET_SCRIPT_TASK_STATUS scplayer TASK_LEAVE_CAR iTemp
		IF iTemp = FINISHED_TASK
			STORE_CAR_CHAR_IS_IN_NO_SAVE iGF_ped iCurrentCar
			IF NOT IS_CAR_DEAD iCurrentCar
				SET_CAR_PROOFS iCurrentCar FALSE FALSE FALSE FALSE FALSE
				TASK_CAR_DRIVE_WANDER iGF_ped iCurrentCar 30.0 DRIVINGMODE_AVOIDCARS
				iGFSayContext = CONTEXT_GLOBAL_GFRIEND_DROP_PLAYER_DRIVE_AWAY				
				iCJSayContext = CONTEXT_GLOBAL_MICHELLE_TAKE_CAR
			ENDIF
			CLEAR_CHAR_TASKS scplayer 
			CLEAR_HELP
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE iGF_ped FALSE
			SET_PLAYER_CONTROL player1 ON
			SET_EVERYONE_IGNORE_PLAYER player1 FALSE
			SWITCH_WIDESCREEN OFF
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
					
			SET_BIT iDateFlags BORED_TAKE_ME_HOME // Girl Got Bored\Annoyed
			SET_CHAR_PROOFS iGF_ped FALSE FALSE FALSE FALSE FALSE			
			//--- Set her to 'continue' to flee the player (substate 1)
			iDateState = 9
			iSubStateStatus = 1 
		ENDIF
	BREAK

	ENDSWITCH

RETURN
/********************************************
		STATE 9 : FLEE PLAYER
********************************************/
GF_Date_State9:  
	SWITCH iSubStateStatus	 				

	CASE 0
		GOSUB GF_Date_RemoveAllBlipsAndCounters
		SET_BIT	iDateFlags	1 // This state does not allow transitions 
		CLEAR_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A FAILURE		
		REMOVE_CHAR_FROM_GROUP iGF_ped 
		CLEAR_CHAR_TASKS_IMMEDIATELY iGF_ped
		TASK_FLEE_CHAR_ANY_MEANS iGF_ped scplayer 100.0 36000000 FALSE 0 0 30.0
		SET_CHAR_PROOFS iGF_ped FALSE FALSE FALSE FALSE FALSE		
		++iSubStateStatus
	BREAK

	CASE 1
		//--- Check girl's health
		IF NOT IS_CHAR_HEALTH_GREATER iGF_ped 5			
			iGFLikesPlayer[iGFidx] = GF_IS_DEAD 
			TASK_DIE iGF_ped 
			GOSUB GF_Date_Cleanup
			RETURN
		ENDIF
		IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer iGF_ped 70.0 70.0 8.0 FALSE
			GOSUB GF_Date_Cleanup
			RETURN
		ENDIF
	BREAK
		
	ENDSWITCH

RETURN
/********************************************
		STATE 10 : DANCE
********************************************/
GF_Date_State10:

	SWITCH iSubStateStatus	 				

	CASE 0
		SET_BIT iDateFlags 1 // Do NOT allow transitions 
		iDanceGirlfriend = iGF_ped
		iSetBarPanic = 0
		iSwitchOffDanceMiniGame = 0
		SET_BIT iDanceReport DANCE_MINIGAME_RUNNING
		++iSubStateStatus
	BREAK

	CASE 1
		IF NOT IS_BIT_SET iDanceReport DANCE_MINIGAME_RUNNING 			
			iSwitchOffDanceMiniGame = 1 // Switch off the dance mini-game
			IF NOT IS_GROUP_MEMBER iGF_ped players_group
				SET_GROUP_MEMBER players_group iGF_ped
				LISTEN_TO_PLAYER_GROUP_COMMANDS iGF_ped FALSE
				SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS PLAYER1 TRUE
			ENDIF
			//--- See how well the player performed at the mini-game
			IF iDanceScore >= GF_DANCE_SCORE_REQUIRED
				SET_BIT iDateReport DATE_WAS_SUCCESS				
				iGFSayContext = CONTEXT_GLOBAL_GFRIEND_ENJOYED_CLUB_HIGH				
			ELSE
				CLEAR_BIT iDateReport DATE_WAS_SUCCESS
				iGFSayContext = CONTEXT_GLOBAL_GFRIEND_ENJOYED_EVENT_LOW
			ENDIF
						
			CLEAR_BIT iDateFlags 1 // Allow transitions again
			SET_BIT iDateFlags DESTINATION_BACK_HOME // Mark GF wants to go back home

			//--- State Complete - Back to IDLE on Foot
			iDateState = 1
			iSubStateStatus = 0	 
		ELSE // Is the mini-game still running or have we left?
			GET_CHAR_AREA_VISIBLE scplayer iCurrentArea
			IF iCurrentArea = 0 // player has left the club
				CLEAR_BIT iDateFlags 1 // Allow transitions again
				CLEAR_BIT iDanceReport DANCE_MINIGAME_RUNNING
				//--- State Complete - Back to IDLE on Foot
				iDateState = 1
				iSubStateStatus = 0	 
			ELSE 
				SET_BIT iDateFlags RESET_TIMER_THIS_FRAME //Request to reset TIMERA this frame 
			ENDIF
		ENDIF
	BREAK

	ENDSWITCH
RETURN
/********************************************
		STATE 11: GIFT GIVING ON FOOT
********************************************/
GF_Date_State11:  
	SWITCH iSubStateStatus	 				

	CASE 0
		SET_BIT	iDateFlags	1 // This state does not allow transitions 
		CLEAR_HELP
		SET_PLAYER_CONTROL player1 OFF
		SET_PLAYER_FIRE_BUTTON player1 FALSE
		SET_EVERYONE_IGNORE_PLAYER player1 TRUE
		SWITCH_WIDESCREEN ON
		SET_ALL_CARS_CAN_BE_DAMAGED FALSE
		//--- Switch Roads OFF
		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 30.0 30.0 2.0 fX[0] fY[0] fZ[0]
		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -30.0 -30.0 -1.0 fX[1] fY[1] fZ[1]
		SWITCH_ROADS_OFF fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1]
		SWITCH_PED_ROADS_OFF fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1]
		//--- Rotate player and GF to face each other		
		TASK_TURN_CHAR_TO_FACE_CHAR scplayer iGF_ped
		TASK_TURN_CHAR_TO_FACE_CHAR iGF_ped scplayer 
		
		++iSubStateStatus
	BREAK
	
	CASE 1

		GET_SCRIPT_TASK_STATUS scplayer TASK_TURN_CHAR_TO_FACE_CHAR iTemp
		GET_SCRIPT_TASK_STATUS iGF_ped TASK_TURN_CHAR_TO_FACE_CHAR iTemp2
					
		IF iTemp = FINISHED_TASK
		AND iTemp2 = FINISHED_TASK
		   	//--- Switch collisions OFF
		   	SET_CHAR_COLLISION scplayer FALSE 
		   	SET_CHAR_COLLISION iGF_ped FALSE
		   	//--- Update Player and GF co-ords to match anim 			
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 1.0 0.0 fX[1] fY[1] fZ[1]
			GET_GROUND_Z_FOR_3D_COORD fX[1] fY[1] fZ[1] fZ[1]	
			//---Store the current girl co-ords
			GET_CHAR_COORDINATES iGF_ped fX[2] fY[2] fZ[2] 
			GET_GROUND_Z_FOR_3D_COORD fX[2] fY[2] fZ[2] fZ[2]
			//--- Now move the girl in the right spot
			SET_CHAR_COORDINATES iGF_ped fX[1] fY[1] fZ[1] 
			GET_CHAR_HEADING scplayer fTemp[0]
			fTemp[0] += 180.0
			SET_CHAR_HEADING iGF_ped fTemp[0]  
			 
			//--- Camera Cut
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 1.2 -1.0 0.5 fX[0] fY[0] fZ[0]
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 0.7 0.5 fX[1] fY[1] fZ[1]

			IF IS_LINE_OF_SIGHT_CLEAR fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1] TRUE TRUE FALSE TRUE FALSE
				SET_FIXED_CAMERA_POSITION fX[0] fY[0] fZ[0] 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT fX[1] fY[1] fZ[1] JUMP_CUT
			ENDIF

		   	++iSubStateStatus			
					 
		ELSE
			GET_SCRIPT_TASK_STATUS scplayer TASK_TURN_CHAR_TO_FACE_CHAR iTemp
			IF iTemp = FINISHED_TASK			
				GET_SCRIPT_TASK_STATUS scplayer TASK_STAND_STILL iTemp
				IF iTemp = FINISHED_TASK
					TASK_STAND_STILL scplayer -1
				ENDIF
			ENDIF
			GET_SCRIPT_TASK_STATUS iGF_ped TASK_TURN_CHAR_TO_FACE_CHAR iTemp
			IF iTemp = FINISHED_TASK			
				GET_SCRIPT_TASK_STATUS iGF_ped TASK_STAND_STILL iTemp
				IF iTemp = FINISHED_TASK
					TASK_STAND_STILL iGF_ped -1
				ENDIF
			ENDIF
		ENDIF
	BREAK

	CASE 2
		//--- Switch collisions back ON
		SET_CHAR_COLLISION scplayer TRUE 
		SET_CHAR_COLLISION iGF_ped TRUE

		TASK_PLAY_ANIM scplayer gift_give KISSING 4.0 FALSE FALSE FALSE FALSE 0
		TASK_PLAY_ANIM iGF_ped gift_get KISSING 4.0 FALSE FALSE FALSE FALSE 0
		
		TIMERB = 0
		++iSubStateStatus
	BREAK

	CASE 3
		IF TIMERB >= 3966
			GET_CURRENT_CHAR_WEAPON scplayer iTemp
			REMOVE_WEAPON_FROM_CHAR scplayer iTemp
			GIVE_WEAPON_TO_CHAR iGF_ped iTemp 0
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_GIFT_LIKE
			iSubStateStatus = 4   	
		ENDIF
	BREAK

	CASE 4 			
		//--- Now conclude the cut-scene
		fTemp[0] = -1.0
	
		IF IS_CHAR_PLAYING_ANIM scplayer gift_give
			GET_CHAR_ANIM_CURRENT_TIME scplayer gift_give fTemp[0]
		ENDIF

		IF fTemp[0] > -1.0 // An anim is playing
			IF fTemp[0] = 1.0 // The animation is finished
				//--- Restore the girl's co-ords
				SET_CHAR_COORDINATES iGF_ped fX[2] fY[2] fZ[2]
				//--- Switch Roads ON
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 30.0 30.0 1.0 fX[0] fY[0] fZ[0]
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -30.0 -30.0 -1.0 fX[1] fY[1] fZ[1]
				SWITCH_ROADS_BACK_TO_ORIGINAL fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1]
				SWITCH_PED_ROADS_BACK_TO_ORIGINAL fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1]
				//--- Restore collisions etc.
				SET_CHAR_COLLISION scplayer TRUE 
				SET_CHAR_COLLISION iGF_ped TRUE
				SET_PLAYER_CONTROL player1 ON
				SET_PLAYER_FIRE_BUTTON player1 TRUE
				SET_EVERYONE_IGNORE_PLAYER player1 FALSE
				SWITCH_WIDESCREEN OFF
				SET_ALL_CARS_CAN_BE_DAMAGED TRUE
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				//--- Remove the gift from GF
				GET_CURRENT_CHAR_WEAPON iGF_ped iTemp
				REMOVE_WEAPON_FROM_CHAR iGF_ped iTemp
				//---Compute the Stats
				++iGFLikesPlayer[iGFidx]
				//GOSUB GF_Date_SynchStats
				//--- State Complete - Back to IDLE on Foot
				CLEAR_BIT iDateFlags 1 // This state allows transitions again
				iDateState = 1
				iSubStateStatus = 0
			ENDIF
		ELSE // This is a cut-scene skip
			CLEAR_CHAR_TASKS scplayer
			TASK_PAUSE iGF_ped 0
			//---Compute the Stats
			++iGFLikesPlayer[iGFidx]
			//GOSUB GF_Date_SynchStats
			//--- Restore the girl's co-ords			
			SET_CHAR_COORDINATES iGF_ped fX[2] fY[2] fZ[2]
			//--- Switch Roads ON
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 30.0 30.0 1.0 fX[0] fY[0] fZ[0]
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -30.0 -30.0 -1.0 fX[1] fY[1] fZ[1]
			SWITCH_ROADS_BACK_TO_ORIGINAL fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1]
			SWITCH_PED_ROADS_BACK_TO_ORIGINAL fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1]
			//--- Restore collisions etc.
			SET_CHAR_COLLISION scplayer TRUE 
			SET_CHAR_COLLISION iGF_ped TRUE
			SET_PLAYER_CONTROL player1 ON
			SET_PLAYER_FIRE_BUTTON player1 TRUE
			SET_EVERYONE_IGNORE_PLAYER player1 FALSE
			SWITCH_WIDESCREEN OFF
			SET_ALL_CARS_CAN_BE_DAMAGED TRUE
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			IF NOT IS_GROUP_MEMBER iGF_ped players_group
				SET_GROUP_MEMBER players_group iGF_ped				
				LISTEN_TO_PLAYER_GROUP_COMMANDS iGF_ped FALSE
				SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS PLAYER1 TRUE
			ENDIF
			//--- State Complete - Back to IDLE on Foot
			CLEAR_BIT iDateFlags 1 // This state allows transitions again
			iDateState = 1
			iSubStateStatus = 0
		ENDIF
	BREAK
		
	ENDSWITCH

RETURN
/*****************************************************************************
								GF_DATE SUBROUTINES
******************************************************************************/
/*******************************************
		CHECK SHE LIKES PLAYER
********************************************/
GF_Date_CheckLikesPlayer:

	
	//--- Check if she still wants to date him
	IF iGFLikesPlayer[iGFidx] <= GF_LIKES_PLAYER_LOW_LIMIT
	   iGFLikesPlayer[iGFidx] = GF_DUMP_PLAYER_IMMEDIATELY		   
	ENDIF

	//--- Check if she wants to dump the player
	IF iGFLikesPlayer[iGFidx] = GF_DUMP_PLAYER_IMMEDIATELY
		iTemp = -1
		RETURN
	ENDIF

	//--- Check if this is a KINKY SEX date and skip all checks if it's the case
	IF IS_BIT_SET iDateReport KINKY_SEX
		iTemp = 1
		RETURN
	ENDIF
		
	//--- Get the player's sex appeal and start checking against it
	GET_INT_STAT SEX_APPEAL iTemp
	iTemp /= 10	// Scale it between 0 and 100

	//--- Check if player is wearing special clothes and add a sex appeal bonus for this
	SWITCH iGFidx 
		CASE KYLIE 
			IF IS_PLAYER_WEARING Player1 CLOTHES_TEX_EXTRA1 countrytr
				iTemp += GF_SPECIAL_CLOTHES_APPEAL_INCREASE
			ENDIF
		BREAK
		CASE BARBARA 
			IF IS_PLAYER_WEARING Player1 CLOTHES_TEX_EXTRA1 policetr
				iTemp += GF_SPECIAL_CLOTHES_APPEAL_INCREASE
			ENDIF
		BREAK
	ENDSWITCH

	//--- Assess player's FAT status
	GET_INT_STAT FAT iTemp2
	iTemp2 /= 10 // Scale it 
	iTemp2 /= 2 // Halve it - its impact on sex appeal must be moderate anyway

		IF iTemp2 >= 25 // Player is chubby...
			IF IS_BIT_SET iGFLikesPlayerTraits[iGFidx] 2	// FAT
				//--- ...and she likes fat, sum this to sex appeal, otherwise detract it
				iTemp += iTemp2
			ELSE 
				//--- ...but she dislikes fat, subtract from sex appeal
				iTemp -= iTemp2
				SET_BIT iDateFlags COMMENT_DISLIKE_PHYSIQUE
				SET_BIT iDateFlags PLAYER_NOT_FIT
			ENDIF
		ELSE // Player NOT chubby...
			IF IS_BIT_SET iGFLikesPlayerTraits[iGFidx] 2	// FAT
				//--- ... but she likes fat, detract from sex appeal
				iTemp -= iTemp2
				SET_BIT iDateFlags COMMENT_DISLIKE_PHYSIQUE
				SET_BIT iDateFlags PLAYER_NOT_FAT
			ELSE 
				IF IS_BIT_SET iGFLikesPlayerTraits[iGFidx] 3	// NORMAL
					//--- she likes skinny, sum this to sex appeal
					IF iTemp2 = 0 // No fat at all
						iTemp2 = 5 // Must increase to have a value to add to sex appeal
					ENDIF
					iTemp += iTemp2
				ENDIF
			ENDIF
		ENDIF
	
	//--- Assess player's MUSCLE status
	GET_INT_STAT BODY_MUSCLE iTemp2
	iTemp2 /= 10 // Scale it 
	iTemp2 /= 2 // Halve it - its impact on sex appeal must be moderate anyway
	 	
	 	IF iTemp2 >= 20 // Player is well fit...
			IF IS_BIT_SET iGFLikesPlayerTraits[iGFidx] 1	// FIT
				//--- ... and she likes fit, sum this to sex appeal
				iTemp += iTemp2
			ELSE
				IF IS_BIT_SET iGFLikesPlayerTraits[iGFidx] 3	// NORMAL
					//--- ... but she likes skinny, too much muscle is bad
					iTemp -= iTemp2
					SET_BIT iDateFlags COMMENT_DISLIKE_PHYSIQUE
					SET_BIT iDateFlags PLAYER_TOO_FIT
				ENDIF
			ENDIF
 		ELSE // Player is not very fit...
			IF IS_BIT_SET iGFLikesPlayerTraits[iGFidx] 1	// FIT
				//--- ... but she likes fit, detract this from sex appeal
				iTemp -= iTemp2
				SET_BIT iDateFlags COMMENT_DISLIKE_PHYSIQUE
				SET_BIT iDateFlags PLAYER_NOT_FIT
			ELSE
				IF IS_BIT_SET iGFLikesPlayerTraits[iGFidx] 3	// NORMAL
					//--- she likes skinny, sum this to sex appeal
					IF iTemp2 = 0 // No muscle at all
						iTemp2 = 5 // Must increase to have a value to add to sex appeal
					ENDIF
					iTemp += iTemp2
				ENDIF
			ENDIF
 		ENDIF

	//--- How much she already likes the player must play a role in sex appeal, too
	iTemp2 =  iGFLikesPlayer[iGFidx] / 2
	iTemp += iTemp2

 	IF iTemp >= iGFDesiredSexAppeal[iGFidx]		
		iTemp = 1
	ELSE
		iTemp = -1
	ENDIF

RETURN
/********************************************
	DECLARE DESIRES FOR THIS DATE
********************************************/
GF_Date_DeclareDesiresForThisDate:
	
	GENERATE_RANDOM_INT_IN_RANGE 0 100 iTemp

	IF IS_BIT_SET iDateReport EAT_OUT					   
		//--- Speech manager request
		IF iGFLikesPlayer[iGFidx] > iGFSelfRespect[iGFidx]
		AND iTemp > 50
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_REQ_MEAL_DESPERATE	
		ELSE
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_REQ_MEAL_NORMAL
		ENDIF
		iCJSayContext = CONTEXT_GLOBAL_ACCEPT_DATE_REQUEST			
		GOSUB GF_Date_EnableDateEntryExits
		GOSUB GF_Date_AddBlipsForCity
		RETURN
	ENDIF

   	IF IS_BIT_SET iDateReport DRIVE
		IF iGFLikesPlayer[iGFidx] > iGFSelfRespect[iGFidx]
		AND iTemp > 50
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_REQ_DRIVE_DESPERATE	
		ELSE
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_REQ_DRIVE_NORMAL
		ENDIF
   		iCJSayContext = CONTEXT_GLOBAL_ACCEPT_DATE_REQUEST
   		DISPLAY_ONSCREEN_COUNTER_WITH_STRING iFun COUNTER_DISPLAY_BAR GF_0029 //Fun:
		RETURN
   ENDIF

	IF IS_BIT_SET iDateReport DANCE
		IF iGFLikesPlayer[iGFidx] > iGFSelfRespect[iGFidx]
		AND iTemp > 50
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_REQ_DANCE_DESPERATE	
		ELSE
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_REQ_DANCE_NORMAL
		ENDIF
		iCJSayContext = CONTEXT_GLOBAL_ACCEPT_DATE_REQUEST
		GOSUB GF_Date_EnableDateEntryExits
		GOSUB GF_Date_AddBlipsForCity
		RETURN
	ENDIF
	
	IF IS_BIT_SET iDateReport GIRL_DRIVE
		iGFSayContext = CONTEXT_GLOBAL_GFRIEND_REQUEST_TO_DRIVE_CAR		
		iCJSayContext = CONTEXT_GLOBAL_AGREE_TO_LET_DRIVE
		IF IS_PLAYER_PLAYING PLAYER1
			SET_CHAR_CANT_BE_DRAGGED_OUT scplayer TRUE
		ENDIF		
		RETURN
	ENDIF
RETURN
/*******************************************
	ADD DATE BLIPS FOR CURRENT CITY
********************************************/
GF_Date_AddBlipsForCity:

	IF IS_PLAYER_PLAYING PLAYER1
		
		GOSUB GF_Date_RemoveAllBlipsAndCounters
		 
		GET_CITY_PLAYER_IS_IN PLAYER1 iTemp

		IF iTemp = LEVEL_LOSANGELES
			IF IS_BIT_SET iDateReport EAT_OUT				
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2862.0 -1439.0 10.0 RADAR_SPRITE_DINER iLocationBlip[0] // Diner
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2317.0 -1645.0 13.0 RADAR_SPRITE_DATE_DRINK iLocationBlip[1] // bar2
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 1837.0 -1682.0 14.0 RADAR_SPRITE_DATE_DRINK iLocationBlip[2] // Bar1
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 415.3947 -1431.0171 31.5057 RADAR_SPRITE_DATE_FOOD iLocationBlip[3] // restaurant in rodeo
			    ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 384.6615 -1819.5294 6.8414 RADAR_SPRITE_DINER iLocationBlip[4] // Diner	
			ENDIF

			IF IS_BIT_SET IDateReport DANCE				
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 1837.0 -1682.0 14.0 RADAR_SPRITE_DATE_DISCO iLocationBlip[0] // BAR1 
			ENDIF

		ENDIF
		
		IF iTemp = LEVEL_SANFRANCISCO
			IF IS_BIT_SET iDateReport EAT_OUT				
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2463.0 132.0 34.0 RADAR_SPRITE_DATE_FOOD iLocationBlip[0] // FDREST1
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -1885.0 747.0 44.0 RADAR_SPRITE_DATE_FOOD iLocationBlip[1] //REST2
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -1699.0 1380.0 6.0 RADAR_SPRITE_DINER iLocationBlip[2]//DINER
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2524.0 1216.0 37.0 RADAR_SPRITE_DINER iLocationBlip[3]//DINER
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2241.0 -88.0 35.0 RADAR_SPRITE_DATE_DRINK iLocationBlip[4] // BAR2
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2551.115 193.657 5.2 RADAR_SPRITE_DATE_DRINK iLocationBlip[5] //BAR1 
			ENDIF

			IF IS_BIT_SET IDateReport DANCE
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2551.115 193.657 5.2 RADAR_SPRITE_DATE_DISCO iLocationBlip[0] //BAR1 
			ENDIF
		ENDIF

		IF iTemp = LEVEL_LASVEGAS
			IF IS_BIT_SET iDateReport EAT_OUT
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2441.145 2065.145 9.847 RADAR_SPRITE_DATE_DRINK iLocationBlip[0] //Craw In Bar2 
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 1693.0 2208.0 9.8 RADAR_SPRITE_DATE_FOOD iLocationBlip[1] //Restaurant
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2490.666 2065.145 9.847 RADAR_SPRITE_DATE_FOOD iLocationBlip[2] //Restaurant
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 1376.892 2327.792 9.822 RADAR_SPRITE_DINER iLocationBlip[3] //Diner1
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2368.058 1983.185 10.003 RADAR_SPRITE_DINER iLocationBlip[4] //Diner2
			ENDIF

			IF IS_BIT_SET IDateReport DANCE				
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2507.44 1242.31 9.833 RADAR_SPRITE_DATE_DISCO iLocationBlip[0] // BAR1
			ENDIF
		ENDIF						 
																		  
		IF iTemp = LEVEL_GENERIC  //Desert or country
			IF IS_BIT_SET iDateReport EAT_OUT
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 292.865 -195.47 0.852 RADAR_SPRITE_DATE_DRINK iLocationBlip[0] //bar     
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 681.544 -473.494 15.592 RADAR_SPRITE_DINER iLocationBlip[1] //The Welcome Pump (TSdiner)     
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -1941.5165 2379.6719 48.4922 RADAR_SPRITE_DINER iLocationBlip[2] // art deco cafe	(diner2)
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -857.1400 1537.2772 21.5870 RADAR_SPRITE_DATE_FOOD iLocationBlip[3] //The Smokin' Beef Grill (rest2)
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -85.2583 1378.9913 9.2812 RADAR_SPRITE_DATE_DRINK iLocationBlip[4] //Lil' Probe Inn (UFOBAR) 
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -53.867 1189.297 18.414 RADAR_SPRITE_DINER iLocationBlip[5] //Fort carson (diner2)
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2317.0 -1645.0 13.0 RADAR_SPRITE_DATE_DRINK iLocationBlip[6] // bar2 in LA
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 415.3947 -1431.0171 31.5057 RADAR_SPRITE_DATE_FOOD iLocationBlip[7] // restaurant in rodeo LA
			ENDIF

			IF IS_BIT_SET IDateReport DANCE
				//--- Just add them all...
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 1837.0 -1682.0 14.0 RADAR_SPRITE_DATE_DISCO iLocationBlip[0] // BAR1 
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2551.115 193.657 5.2 RADAR_SPRITE_DATE_DISCO iLocationBlip[1] //BAR1 
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2507.44 1242.31 9.833 RADAR_SPRITE_DATE_DISCO iLocationBlip[2] // BAR1
			ENDIF
		ENDIF	
	ENDIF

RETURN
/*******************************************
		ENABLE DATE ENTRY-EXITS
********************************************/
GF_Date_EnableDateEntryExits:
	//--- Sets the special flags to the entryexits used during a date

	IF IS_BIT_SET iDateReport EAT_OUT

		SWITCH_ENTRY_EXIT FDrest1 TRUE
		SWITCH_ENTRY_EXIT rest2 TRUE
		SWITCH_ENTRY_EXIT diner1 TRUE
		SWITCH_ENTRY_EXIT diner2 TRUE
		SWITCH_ENTRY_EXIT TSdiner TRUE

		SET_NAMED_ENTRY_EXIT_FLAG FDpiza ENTRYEXITS_FLAG_NO_WARP TRUE
		SET_NAMED_ENTRY_EXIT_FLAG FDburg ENTRYEXITS_FLAG_NO_WARP TRUE
		SET_NAMED_ENTRY_EXIT_FLAG FDchick ENTRYEXITS_FLAG_NO_WARP TRUE
		SET_NAMED_ENTRY_EXIT_FLAG UFOBAR ENTRYEXITS_FLAG_NO_WARP TRUE

		SET_NAMED_ENTRY_EXIT_FLAG rest2 ENTRYEXITS_FLAG_NO_WARP TRUE
		SET_NAMED_ENTRY_EXIT_FLAG diner1 ENTRYEXITS_FLAG_NO_WARP TRUE
		SET_NAMED_ENTRY_EXIT_FLAG FDrest1 ENTRYEXITS_FLAG_NO_WARP TRUE
		SET_NAMED_ENTRY_EXIT_FLAG rest2 ENTRYEXITS_FLAG_NO_WARP TRUE
		SET_NAMED_ENTRY_EXIT_FLAG diner1 ENTRYEXITS_FLAG_NO_WARP TRUE
		SET_NAMED_ENTRY_EXIT_FLAG diner2 ENTRYEXITS_FLAG_NO_WARP TRUE
		SET_NAMED_ENTRY_EXIT_FLAG TSdiner ENTRYEXITS_FLAG_NO_WARP TRUE
		SET_NAMED_ENTRY_EXIT_FLAG bar1 ENTRYEXITS_FLAG_NO_WARP TRUE
		SET_NAMED_ENTRY_EXIT_FLAG bar2 ENTRYEXITS_FLAG_NO_WARP TRUE
		
	ELSE
		IF IS_BIT_SET iDateReport DANCE
			SET_NAMED_ENTRY_EXIT_FLAG bar1 ENTRYEXITS_FLAG_WARP_GROUP TRUE
			SET_NAMED_ENTRY_EXIT_FLAG bar2 ENTRYEXITS_FLAG_WARP_GROUP TRUE
		ENDIF
	ENDIF

RETURN
/*******************************************
		RESET DATE ENTRY-EXITS
********************************************/
GF_Date_ResetDateEntryExits:

	SWITCH_ENTRY_EXIT FDrest1 FALSE
	SWITCH_ENTRY_EXIT rest2 FALSE
	SWITCH_ENTRY_EXIT diner1 FALSE
	SWITCH_ENTRY_EXIT diner2 FALSE
	SWITCH_ENTRY_EXIT TSdiner FALSE

	//--- Turn back on the entry-exits that should be always active
	SWITCH_ENTRY_EXIT FDPIZA TRUE
	SWITCH_ENTRY_EXIT FDBURG TRUE
	SWITCH_ENTRY_EXIT FDCHICK TRUE
	SWITCH_ENTRY_EXIT BAR1 TRUE
	SWITCH_ENTRY_EXIT BAR2 TRUE
	SWITCH_ENTRY_EXIT UFOBAR TRUE

	//--- Reset the flags
	SET_NAMED_ENTRY_EXIT_FLAG FDpiza ENTRYEXITS_FLAG_NO_WARP FALSE
	SET_NAMED_ENTRY_EXIT_FLAG FDburg ENTRYEXITS_FLAG_NO_WARP FALSE
	SET_NAMED_ENTRY_EXIT_FLAG FDchick ENTRYEXITS_FLAG_NO_WARP FALSE

	SET_NAMED_ENTRY_EXIT_FLAG rest2 ENTRYEXITS_FLAG_NO_WARP FALSE
	SET_NAMED_ENTRY_EXIT_FLAG diner1 ENTRYEXITS_FLAG_NO_WARP FALSE
	SET_NAMED_ENTRY_EXIT_FLAG FDrest1 ENTRYEXITS_FLAG_NO_WARP FALSE
	SET_NAMED_ENTRY_EXIT_FLAG rest2 ENTRYEXITS_FLAG_NO_WARP FALSE
	SET_NAMED_ENTRY_EXIT_FLAG diner1 ENTRYEXITS_FLAG_NO_WARP FALSE
	SET_NAMED_ENTRY_EXIT_FLAG diner2 ENTRYEXITS_FLAG_NO_WARP FALSE
	SET_NAMED_ENTRY_EXIT_FLAG TSdiner ENTRYEXITS_FLAG_NO_WARP FALSE
	SET_NAMED_ENTRY_EXIT_FLAG bar1 ENTRYEXITS_FLAG_NO_WARP FALSE
	SET_NAMED_ENTRY_EXIT_FLAG bar2 ENTRYEXITS_FLAG_NO_WARP FALSE
	SET_NAMED_ENTRY_EXIT_FLAG UFOBAR ENTRYEXITS_FLAG_NO_WARP FALSE

	SET_NAMED_ENTRY_EXIT_FLAG bar1 ENTRYEXITS_FLAG_WARP_GROUP FALSE
	SET_NAMED_ENTRY_EXIT_FLAG bar2 ENTRYEXITS_FLAG_WARP_GROUP FALSE

RETURN 
/*******************************************
		SWITCH OFF ALL ENTRY-EXITS
********************************************/
GF_Date_SwitchOffAllEntryExits:

	SWITCH_ENTRY_EXIT FDrest1 FALSE
	SWITCH_ENTRY_EXIT rest2 FALSE
	SWITCH_ENTRY_EXIT diner1 FALSE
	SWITCH_ENTRY_EXIT diner2 FALSE
	SWITCH_ENTRY_EXIT TSdiner FALSE
	SWITCH_ENTRY_EXIT FDPIZA FALSE
	SWITCH_ENTRY_EXIT FDBURG FALSE
	SWITCH_ENTRY_EXIT FDCHICK FALSE
	SWITCH_ENTRY_EXIT BAR1 FALSE
	SWITCH_ENTRY_EXIT BAR2 FALSE
	SWITCH_ENTRY_EXIT UFOBAR FALSE	
	
RETURN 
/*******************************************
		SWITCH OFF SPECIAL ENTRY-EXITS
********************************************/
GF_Date_SwitchOffSpecialEntryExits:

	SWITCH_ENTRY_EXIT FDrest1 FALSE
	SWITCH_ENTRY_EXIT rest2 FALSE
	SWITCH_ENTRY_EXIT diner1 FALSE
	SWITCH_ENTRY_EXIT diner2 FALSE
	SWITCH_ENTRY_EXIT TSdiner FALSE

	SET_NAMED_ENTRY_EXIT_FLAG FDpiza ENTRYEXITS_FLAG_NO_WARP FALSE
	SET_NAMED_ENTRY_EXIT_FLAG FDburg ENTRYEXITS_FLAG_NO_WARP FALSE
	SET_NAMED_ENTRY_EXIT_FLAG FDchick ENTRYEXITS_FLAG_NO_WARP FALSE
	SET_NAMED_ENTRY_EXIT_FLAG bar1 ENTRYEXITS_FLAG_NO_WARP FALSE
	SET_NAMED_ENTRY_EXIT_FLAG bar2 ENTRYEXITS_FLAG_NO_WARP FALSE
	SET_NAMED_ENTRY_EXIT_FLAG UFOBAR ENTRYEXITS_FLAG_NO_WARP FALSE
	
RETURN 
/*******************************************
		GET GIRL HOME COORDINATES
********************************************/
GF_Date_GetHomeCoords:
	 
	SWITCH iGFidx 
		CASE COOCHIE  
			//--- Home in Los Santos
			fX[0] 	= 2402.0117
			fY[0] 	= -1719.6484
			fZ[0] 	= 12.6219
		BREAK

		CASE BARBARA
			fX[0] 	= -1398.1010 
			fY[0] 	= 2636.8730 
			fZ[0] 	= 54.7031 
		BREAK

		CASE KYLIE
			fX[0] 	= -377.3978 //-398.5166    
			fY[0] 	= -1438.6919 //-1426.4675 
			fZ[0] 	= 24.7209 //24.7110 
		BREAK

		CASE MICHELLE
			fX[0] 	= -1796.9523 
			fY[0] 	= 1197.3763 
			fZ[0] 	= 24.1094 
		BREAK

		CASE SUZIE
			fX[0] 	= -2576.8079 
			fY[0] 	= 1144.7438 
			fZ[0] 	= 54.8547 
		BREAK
		
		CASE MILLIE
			//--- Home in Vegas
			fX[0] = 2035.3619  
			fY[0] = 2732.4106  
			fZ[0] = 9.8203	  
		BREAK

	ENDSWITCH
	
RETURN 		
/*******************************************
			GET CURRENT DESTINATION 
********************************************/
GF_Date_GetDestination:

	IF IS_BIT_SET iDateFlags DESTINATION_BACK_HOME // Take GF Home					
		GOSUB GF_Date_GetHomeCoords
	   	IF LOCATE_STOPPED_CHAR_ANY_MEANS_3D iGF_ped fX[0] fY[0] fZ[0] 3.0 3.0 3.0 TRUE
			iCurrentArea = -2 // Home Reached
	   	ELSE
	   		iCurrentArea = -1			
	   	ENDIF
	ELSE // Girl en route somewhere
		GET_AREA_VISIBLE iCurrentArea
		IF NOT iCurrentArea = 0
			//--- The area has changed to something that could be a valid Entry-Exit 
			IF IS_BIT_SET iDateReport EAT_OUT // If en route to a food place...
				//--- Now check if it is actually an entry exit
				GET_NAME_OF_ENTRY_EXIT_CHAR_USED scplayer txtEntryExitName
				GOSUB GF_Date_VerifyEntryExit // Reads txtEntryExitName, Returns iTemp = entry exit code 
				IF iTemp > -1					 					 
					RETURN
				ELSE
					iCurrentArea = -1
				ENDIF
			ELSE
				IF IS_BIT_SET iDateReport DANCE // If en route to a bar...
					//--- new area. Now check if it is actually an entry exit
					GET_NAME_OF_ENTRY_EXIT_CHAR_USED scplayer txtEntryExitName
					GOSUB GF_Date_VerifyEntryExit // Reads txtEntryExitName, Returns iTemp = entry exit code 
					IF iTemp = GF_BAR1						
						//--- Reset the timer NOW or the warping will count as 'running away from the girl'
						SET_BIT iDateFlags RESET_TIMER_THIS_FRAME //Request to reset TIMERA this frame
						RETURN
					ELSE											
						iCurrentArea = -1
					ENDIF
				ELSE
					iCurrentArea = -1
				ENDIF
			ENDIF
		ELSE
			//--- Return and invalid current area
			iCurrentArea = -1
		ENDIF
	ENDIF
RETURN
/********************************************
		APPEND GIRL TO CUTSCENE
********************************************/
GF_Date_AppendGirlToCutscene:

	SWITCH iGFidx 
		CASE COOCHIE 
			APPEND_TO_NEXT_CUTSCENE GANGRL3	GFRIEND				 
		BREAK

		CASE BARBARA
			APPEND_TO_NEXT_CUTSCENE COPGRL3	GFRIEND
		BREAK

		CASE KYLIE
			APPEND_TO_NEXT_CUTSCENE GUNGRL3	GFRIEND	
		BREAK

		CASE MICHELLE
			APPEND_TO_NEXT_CUTSCENE MECGRL3	GFRIEND
		BREAK

		CASE SUZIE
			APPEND_TO_NEXT_CUTSCENE NURGRL3	GFRIEND
		BREAK
		
		CASE MILLIE
			APPEND_TO_NEXT_CUTSCENE CROGRL3	GFRIEND
		BREAK

	ENDSWITCH

RETURN
/*******************************************
		SET UP CUT AT LOCATION
********************************************/
GF_Date_SetUpCutAtLocation:
	
	GOSUB GF_Date_VerifyEntryExit // Reads txtEntryExitName, Returns iTemp = entry exit code 

	SWITCH iTemp

		CASE GF_FDPIZA 
			//--- Set the extra colour 
			SET_EXTRA_COLOURS 4 FALSE
			//--- Increase Calories STAT
			SHOW_UPDATE_STATS  FALSE
			INCREMENT_INT_STAT CALORIES 40 			
			INCREMENT_INT_STAT FAT 40
			//--- Cut-Scene Offset			
			fX[0] = 370.0
			fY[0] = -125.0
			fZ[0] = 1001.52
			LOAD_SCENE fX[0] fY[0] fZ[0]
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_JUNK_FOOD       
				//--- GOOD Date	
				APPEND_TO_NEXT_CUTSCENE GFPIZ1 GFPIZ1
				APPEND_TO_NEXT_CUTSCENE GFPIZ2 GFPIZ2			
				LOAD_CUTSCENE DATE1A
				SET_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A SUCCESS
			ELSE
				//--- BAD date
				APPEND_TO_NEXT_CUTSCENE GFPIZ1 GFPIZ1
				APPEND_TO_NEXT_CUTSCENE GFPIZ2 GFPIZ2

				LOAD_CUTSCENE DATE1B
				CLEAR_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A FAILURE
			ENDIF  
		BREAK

		CASE GF_FDREST1
			//--- Set the extra colour 
			SET_EXTRA_COLOURS 4 FALSE
			//--- Increase Calories STAT
			SHOW_UPDATE_STATS  FALSE
			INCREMENT_INT_STAT CALORIES 40 
			INCREMENT_INT_STAT FAT 40
			//--- Cut-Scene Offset			
			fX[0] = 445.381 
			fY[0] = -14.147 
			fZ[0] = 1001.731
			LOAD_SCENE fX[0] fY[0] fZ[0]
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_SWANK_PLACES       
				//--- GOOD Date
				LOAD_CUTSCENE DATE5A
				SET_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A SUCCESS
			ELSE
				//--- BAD date
				LOAD_CUTSCENE DATE5B
				CLEAR_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A FAILURE
			ENDIF  
		BREAK

		CASE GF_REST2	
			//--- Set the extra colour 
			SET_EXTRA_COLOURS 4 FALSE
			//--- Increase Calories STAT
			SHOW_UPDATE_STATS  FALSE
			INCREMENT_INT_STAT CALORIES 40 
			INCREMENT_INT_STAT FAT 40
			//--- Cut-Scene Offset			
			fX[0] = 441.871 
			fY[0] = -60.839 
			fZ[0] = 1000.675
			LOAD_SCENE fX[0] fY[0] fZ[0]
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_SWANK_PLACES       
				//--- GOOD Date
				LOAD_CUTSCENE DATE5A
				SET_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A SUCCESS
			ELSE
				//--- BAD date
				LOAD_CUTSCENE DATE5B
				CLEAR_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A FAILURE
			ENDIF  
		BREAK

		CASE GF_FDCHICK
			//--- Set the extra colour 
			SET_EXTRA_COLOURS 4 FALSE
			//--- Increase Calories STAT
			SHOW_UPDATE_STATS  FALSE
			INCREMENT_INT_STAT CALORIES 40 
			INCREMENT_INT_STAT FAT 40
			//--- Cut-Scene Offset			
			fX[0] = 374.478
			fY[0] = -8.415
			fZ[0] = 1002.86
			LOAD_SCENE fX[0] fY[0] fZ[0]
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_JUNK_FOOD       
				//--- GOOD Date
				LOAD_CUTSCENE DATE2A
				SET_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A SUCCESS
			ELSE
				//--- BAD date
				LOAD_CUTSCENE DATE2B 
				CLEAR_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A FAILURE
			ENDIF  
		BREAK

		CASE GF_FDBURG
			//--- Set the extra colour 
			SET_EXTRA_COLOURS 4 FALSE
			//--- Increase Calories STAT
			SHOW_UPDATE_STATS  FALSE
			INCREMENT_INT_STAT CALORIES 40 
			INCREMENT_INT_STAT FAT 40
			//--- Cut-Scene Offset			
			fX[0] = 367.891
			fY[0] = -67.591
			fZ[0] = 1001.516
			LOAD_SCENE fX[0] fY[0] fZ[0]
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_JUNK_FOOD       
				//--- GOOD Date
				APPEND_TO_NEXT_CUTSCENE BURG_PA GFPIZ1
				APPEND_TO_NEXT_CUTSCENE BURG_GA GFPIZ2

				LOAD_CUTSCENE DATE1A
				SET_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A SUCCESS
			ELSE
				//--- BAD date
				APPEND_TO_NEXT_CUTSCENE BURG_PA GFPIZ1
				APPEND_TO_NEXT_CUTSCENE BURG_GA GFPIZ2

				LOAD_CUTSCENE DATE1B
				CLEAR_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A FAILURE
			ENDIF  
		BREAK

		CASE GF_BAR1 
			//--- Set the extra colour 
			SET_EXTRA_COLOURS 5 FALSE
			//--- Increase Calories STAT
			SHOW_UPDATE_STATS  FALSE
			INCREMENT_INT_STAT CALORIES 20 
			INCREMENT_INT_STAT FAT 20
			//--- Cut-Scene Offset			
			fX[0] = 498.536 
			fY[0] = -18.2  
			fZ[0] = 1000.651
			LOAD_SCENE fX[0] fY[0] fZ[0]
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_BARS       
				//--- GOOD Date
				LOAD_CUTSCENE DATE6A
				SET_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A SUCCESS
			ELSE
				//--- BAD date
				LOAD_CUTSCENE DATE6B
				CLEAR_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A FAILURE
			ENDIF  
		BREAK

		CASE GF_BAR2
			//--- Set the extra colour 
			SET_EXTRA_COLOURS 6 FALSE
			//--- Increase Calories STAT
			SHOW_UPDATE_STATS  FALSE
			INCREMENT_INT_STAT CALORIES 20 
			INCREMENT_INT_STAT FAT 20
			//--- Cut-Scene Offset			
			fX[0] = 490.718 
			fY[0] = -79.168 
			fZ[0] = 998.76
			LOAD_SCENE fX[0] fY[0] fZ[0]
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_BARS       
				//--- GOOD Date
				LOAD_CUTSCENE DATE6A
				SET_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A SUCCESS
			ELSE
				//--- BAD date
				LOAD_CUTSCENE DATE6B
				CLEAR_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A FAILURE
			ENDIF  
		BREAK

		CASE GF_DINER1 
			//--- Set the extra colour 
			SET_EXTRA_COLOURS 4 FALSE
			//--- Increase Calories STAT
			SHOW_UPDATE_STATS  FALSE
			INCREMENT_INT_STAT CALORIES 40 
			INCREMENT_INT_STAT FAT 40
			//--- Cut-Scene Offset			
			fX[0] = 449.41
			fY[0] = -86.72//-86.83
			fZ[0] = 1000.53
			LOAD_SCENE fX[0] fY[0] fZ[0]
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_DINERS       
				//--- GOOD Date
				LOAD_CUTSCENE DATE3A 
				SET_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A SUCCESS
			ELSE
				//--- BAD date
				LOAD_CUTSCENE DATE3B 
				CLEAR_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A FAILURE
			ENDIF  
		BREAK

		CASE GF_TSDINER 
			//--- Set the extra colour 
			SET_EXTRA_COLOURS 4 FALSE
			//--- Increase Calories STAT
			SHOW_UPDATE_STATS  FALSE
			INCREMENT_INT_STAT CALORIES 40 
			INCREMENT_INT_STAT FAT 40
			//--- Cut-Scene Offset			
			fX[0] = 678.028//678.028
			fY[0] = -452.9//-452.578
			fZ[0] = -24.444//-24.394
			LOAD_SCENE fX[0] fY[0] fZ[0]
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_DINERS       
				//--- GOOD Date
				LOAD_CUTSCENE DATE3A 
				SET_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A SUCCESS
			ELSE
				//--- BAD date
				LOAD_CUTSCENE DATE3B 
				CLEAR_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A FAILURE
			ENDIF  
		BREAK

		CASE GF_DINER2 
			//--- Set the extra colour 
			SET_EXTRA_COLOURS 4 FALSE
			//--- Increase Calories STAT
			SHOW_UPDATE_STATS  FALSE
			INCREMENT_INT_STAT CALORIES 40 
			INCREMENT_INT_STAT FAT 40
			//--- Cut-Scene Offset			
			fX[0] = 449.41 
			fY[0] = -108.24 
			fZ[0] = 1000.528
			LOAD_SCENE fX[0] fY[0] fZ[0]
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_DINERS       
				//--- GOOD Date
				LOAD_CUTSCENE DATE4A 
				SET_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A SUCCESS
			ELSE
				//--- BAD date
				LOAD_CUTSCENE DATE4B 
				CLEAR_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A FAILURE
			ENDIF  
		BREAK

		CASE GF_UFOBAR
			//--- Set the extra colour 
			SET_EXTRA_COLOURS 4 FALSE
			//--- Increase Calories STAT
			SHOW_UPDATE_STATS  FALSE
			INCREMENT_INT_STAT CALORIES 20 
			INCREMENT_INT_STAT FAT 20
			//--- Cut-Scene Offset			
			fX[0] = -226.249//-226.293      
			fY[0] = 1405.448//1405.358
			fZ[0] = 27.7//28.772

			LOAD_SCENE fX[0] fY[0] fZ[0]
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_BARS       
				//--- GOOD Date
				LOAD_CUTSCENE DATE6A
				SET_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A SUCCESS
			ELSE
				//--- BAD date
				LOAD_CUTSCENE DATE6B
				CLEAR_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A FAILURE
			ENDIF  
		BREAK

	ENDSWITCH	
		
RETURN
/*******************************************
			VERIFY ENTRY EXIT
********************************************/
GF_Date_VerifyEntryExit:

	iTemp = -1	
	 
	IF $txtEntryExitName = &FDCHICK
		iTemp = GF_FDCHICK
		RETURN
	ENDIF

	IF $txtEntryExitName = &FDBURG
		iTemp = GF_FDBURG
		RETURN
	ENDIF

	IF $txtEntryExitName = &FDPIZA
		iTemp = GF_FDPIZA
		RETURN
	ENDIF

	IF $txtEntryExitName = &FDREST1
		iTemp = GF_FDREST1
		RETURN
	ENDIF

	IF $txtEntryExitName = &REST2
		iTemp = GF_REST2
		RETURN
	ENDIF

	IF $txtEntryExitName = &DINER1
		iTemp = GF_DINER1
		RETURN
	ENDIF

	IF $txtEntryExitName = &DINER2
		iTemp = GF_DINER2
		RETURN
	ENDIF

	IF $txtEntryExitName = &BAR1
		iTemp = GF_BAR1
		RETURN
	ENDIF

	IF $txtEntryExitName = &BAR2
		iTemp = GF_BAR2
		RETURN
	ENDIF

	IF $txtEntryExitName = &UFOBAR
		iTemp = GF_UFOBAR
		RETURN
	ENDIF

	IF $txtEntryExitName = &TSDINER
		iTemp = GF_TSDINER
		RETURN
	ENDIF

 	$txtEntryExitName = NIL

RETURN
/*******************************************
	GET COORDINATES FOR HOME CUT-SCENE
********************************************/
GF_Date_GetCoordsHomeCut:

	SWITCH iGFidx 
		CASE COOCHIE // Gang Girl
			//--- Camera pos
			fX[0] = 2394.4080  
			fY[0] = -1728.2148
			fZ[0] = 14.2715  

			//--- Camera target
			fX[1] = 2395.0662   
			fY[1] = -1727.4651
			fZ[1] = 14.2063 

			//--- Point to reach to walk back in house
			fX[2] = 2401.9099
			fY[2] = -1715.6326
			fZ[2] = 13.0848 
							      
			//--- GF heading once at door
			fTemp[0] = 180.0
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

			//--- Point to reach to walk back in house
			fX[2] = -1390.6003 
			fY[2] = 2638.3494 
			fZ[2] = 54.9965 
							      
			//--- GF heading once at door
			fTemp[0] = 90.0
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

			//--- Point to reach to walk back in house
			fX[2] = -1799.8102 //-1799.9113    
			fY[2] = 1200.6017 //1202.2201
			fZ[2] = 24.1194 //24.1328
							      
			//--- GF heading once at door
			fTemp[0] = 197.0//180.0
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

			//--- Point to reach to walk back in house
			fX[2] = -382.0961 //-393.5520 
			fY[2] = -1438.2074 //-1426.6538 
			fZ[2] = 24.7209 //25.3638 
							      
			//--- GF heading once at door
			fTemp[0] = 269.3417 //94.7863
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

			//--- Point to reach to walk back in house
			fX[2] = -2574.0449 
			fY[2] = 1152.3970 
			fZ[2] = 54.7422 
							      
			//--- GF heading once at door
			fTemp[0] = 151.7896
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

			//--- Point to reach to walk back in house
			fX[2] = 2037.4008 
			fY[2] = 2723.2832
			fZ[2] = 9.8352
							      
			//--- GF heading once at door
			fTemp[0] = 8.0
		BREAK
				     
	ENDSWITCH				   

RETURN
/*******************************************
	ARE THERE ENEMIES IN THIS ZONE?
********************************************/
GF_Date_AreEnemiesHere: // Returns iTemp  > 0 if this is gang area

	GET_ZONE_GANG_STRENGTH $txtCurrZone GANG_FLAT iTemp 	
	IF iTemp > 0 
		RETURN
	ENDIF
	GET_ZONE_GANG_STRENGTH $txtCurrZone GANG_SMEX iTemp 	
	IF iTemp > 0 
		RETURN
	ENDIF
	GET_ZONE_GANG_STRENGTH $txtCurrZone GANG_NMEX iTemp 	
	IF iTemp > 0 
		RETURN
	ENDIF
	GET_ZONE_GANG_STRENGTH $txtCurrZone GANG_SFMEX iTemp 	
	IF iTemp > 0 
		RETURN
	ENDIF
RETURN
/********************************************
		 GIRLFRIEND BORED WARNING
********************************************/
 GF_Date_Bored_Warning:  
	
	IF IS_BIT_SET iDateReport DRIVE	// DRIVE
		//--- During a drive date, 
		IF NOT IS_BIT_SET iDateFlags DESTINATION_BACK_HOME //Take GF home 
		AND NOT IS_BIT_SET iDateFlags BORED_WARNING			
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_BORED_2																								
		ENDIF
	ELSE
		//--- During any other date
		IF NOT IS_BIT_SET iDateFlags DESTINATION_BACK_HOME //Take GF home
		AND NOT IS_BIT_SET iDateFlags BORED_WARNING 						
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_BORED_1	
		ENDIF
	ENDIF

	SET_BIT iDateFlags BORED_WARNING		
RETURN
/********************************************
	 GIRLFRIEND BORED TAKE ME HOME
********************************************/
GF_Date_Bored_Take_Me_Home:	
	//--- The GF is pissed off, ends the date					
	iDateState = 5 // Dump player - ask to be taken home
	iSubStateStatus = 0	
RETURN   
/*******************************************
		IS PLAYER AT KISS DISTANCE
********************************************/
GF_Date_IsPlayerAtKissDistance: // Returns iTemp 0 or GF_PLAYER_GIVE_GIFT,GF_PLAYER_GIVE_KISS

iTemp = 0
	IF NOT IS_BIT_SET iDateFlags BORED_TAKE_ME_HOME // Girl Got Bored\Annoyed
		IF NOT IS_CHAR_IN_ANY_CAR iGF_ped
		AND NOT IS_PLAYER_USING_JETPACK	 PLAYER1
		AND NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_CAMERA
			IF IS_CHAR_STOPPED iGF_ped 
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS iGF_ped 0.0 0.5 0.0 fX[0] fY[0] fZ[0]
				IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer fX[0] fY[0] fZ[0] 1.3 1.3 1.0 FALSE
					GET_CHAR_COORDINATES scplayer fX[0] fY[0] fZ[0]
					GET_CHAR_COORDINATES iGF_ped fX[1] fY[1] fZ[1]
					fTemp[0] = fZ[1] - 0.02
					fTemp[1] = fZ[1] + 0.02
					//--- Check if player and girl are on the same level 
					IF fZ[0] > fTemp[0] 
					AND fZ[0] < fTemp[1]				
						IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_FLOWERS
						OR IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_DILDO1
						OR IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_DILDO2
						OR IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_VIBE1
						OR IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_VIBE2						
							IF NOT IS_BIT_SET iDateFlags GIFT_HELP_ON
							AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
								PRINT_HELP GF_H013 
								SET_BIT iDateFlags GIFT_HELP_ON 
							ENDIF
							iTemp = GF_PLAYER_GIVE_GIFT
							RETURN
						ELSE
							IF NOT IS_BIT_SET iDateFlags 4 // Kiss Help OFF
							AND NOT IS_HELP_MESSAGE_BEING_DISPLAYED
								PRINT_HELP GF_0021  
								SET_BIT iDateFlags 4 // Kiss Help ON
							ENDIF
							iTemp = GF_PLAYER_GIVE_KISS
							RETURN
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
RETURN
/*******************************************
		LOAD ANIMATIONS FROM STREAM
********************************************/
GF_Date_SteamAnims:
	IF iCensoredVersion = 0
		REQUEST_ANIMATION ABCDFETS
	ENDIF
	
	REQUEST_ANIMATION KISSING

GF_Date_StreamLoop:
	
	WAIT 0	
	
	IF HAS_ANIMATION_LOADED KISSING
		IF iCensoredVersion = 0
			IF HAS_ANIMATION_LOADED ABCDFETS
				RETURN
			ELSE
				REQUEST_ANIMATION ABCDFETS
				GOTO GF_Date_StreamLoop
			ENDIF
		ELSE
			RETURN
		ENDIF
	ELSE		
		REQUEST_ANIMATION KISSING
		GOTO GF_Date_StreamLoop
	ENDIF
/*******************************************
				GET DATE TIMER
********************************************/
GF_Date_UpdateDateTime:

	IF IS_BIT_SET iDateFlags RESET_TIMER_THIS_FRAME   
		//--- Request to reset TIMERA this frame
		IF IS_BIT_SET iDateReport DRIVE
			TIMERA = GF_TIME_BEFORE_START_FUN_CHECKS
		ELSE
			TIMERA = 0
		ENDIF
		CLEAR_BIT iDateFlags RESET_TIMER_THIS_FRAME 
	ELSE
		IF IS_BIT_SET iDateFlags HOLD_TIMER_THIS_FRAME 
			//--- Request to HOLD the timer this frame
			IF IS_BIT_SET iDateReport DRIVE
				TIMERA = GF_TIME_BEFORE_START_FUN_CHECKS
			ELSE
				IF iFrozenTime = -1
					iFrozenTime = TIMERA
				ENDIF	
				TIMERA = 0					
			ENDIF
			CLEAR_BIT iDateFlags HOLD_TIMER_THIS_FRAME
		ELSE
			SWITCH iDateState
				CASE 1 // Idle On Foot			
					IF IS_BIT_SET iGFLikesOnDate[iGFidx] 21	// LIKES_GANG_FIGHTS
					AND IS_CHAR_SHOOTING iGF_ped
					AND NOT IS_BIT_SET iDateReport DRIVE 
						//--- Reset the date timer - she is having fun
						TIMERA = 0
					ENDIF
					IF iFrozenTime > 0
						TIMERA = iFrozenTime
						iFrozenTime = -1
					ENDIF				
				BREAK
				CASE 2 // Reached Destination
					TIMERA = 0 		
				BREAK
				CASE 3 // Reached Home
					TIMERA = 0
				BREAK
				CASE 4 // Idle In Car
					IF IS_BIT_SET iGFLikesOnDate[iGFidx] 21	// LIKES_GANG_FIGHTS
					AND IS_CHAR_SHOOTING iGF_ped
					AND NOT IS_BIT_SET iDateReport DRIVE
						//--- Reset the date timer - she is having fun
						TIMERA = 0
					ENDIF	 
					//--- Restore the timer if we need to
					IF iFrozenTime > 0
						TIMERA = iFrozenTime
						iFrozenTime = -1
					ENDIF				
				BREAK
				CASE 5 // Dump Player
					TIMERA = 0
				BREAK
				CASE 6 // Kiss On Foot
					IF IS_BIT_SET iGFLikesOnDate[iGFidx] 27	// LIKES_SNOGGING_IN_PUBLIC
						//--- HOLD the date timer - she is having fun
						IF iFrozenTime = -1
							iFrozenTime = TIMERA
						ENDIF	
						IF IS_BIT_SET iDateReport DRIVE
							TIMERA = GF_TIME_BEFORE_START_FUN_CHECKS
						ELSE
							TIMERA = 0
						ENDIF
					ENDIF
				BREAK
				CASE 7	// BlowJob In Car
					IF IS_BIT_SET iGFLikesOnDate[iGFidx] 28	// LIKES_SEX_IN_PUBLIC	
						//--- HOLD the date timer - she is having fun
						IF iFrozenTime = -1
							iFrozenTime = TIMERA
						ENDIF	
						IF IS_BIT_SET iDateReport DRIVE
							TIMERA = GF_TIME_BEFORE_START_FUN_CHECKS
						ELSE
							TIMERA = 0
						ENDIF
					ENDIF
				BREAK 
				CASE 10 // Dance
					TIMERA = 0
				BREAK
				CASE 11 // Gift give
					//--- HOLD the date timer - she is having fun
					IF iFrozenTime = -1
						iFrozenTime = TIMERA
					ENDIF	
					IF IS_BIT_SET iDateReport DRIVE
						TIMERA = GF_TIME_BEFORE_START_FUN_CHECKS
					ELSE
						TIMERA = 0
					ENDIF
				BREAK
			ENDSWITCH
		ENDIF
	ENDIF
RETURN
/********************************************
	CHECK ENJOYMENT FOR THIS FRAME
********************************************/
GF_Date_CheckEnjoymentThisSequence:
	
	IF fFunTemp <= 0.0 	
		//--- We have done nothing fun or particularly boring this sequence
		fFunTemp -= GF_FUN_DECREMENT_SMALL											
	ENDIF

	//--- Update fun bar			  		
	fFun += fFunTemp
	IF fFun < 0.0
		fFun = 0.0
	ENDIF
	IF fFun > 100.0
		fFun = 100.0
	ENDIF
	
	iFun =# fFun

	SET_BIT iDateFlags HOLD_TIMER_THIS_FRAME

RETURN
/********************************************
	CHECK ENJOYMENT IN CURRENT ZONE
********************************************/
GF_Date_CheckEnjoymentInCurrentZone:
 
	GET_CURRENT_POPULATION_ZONE_TYPE iTemp2

	SWITCH iTemp2

		CASE POPCYCLE_ZONE_INDUSTRY
			//--- Indifference to this zone
		BREAK
		CASE POPCYCLE_ZONE_OUT_OF_TOWN_FACTORY
			//--- Indifference to this zone
		BREAK
		CASE POPCYCLE_ZONE_AIRPORT
			//--- Indifference to this zone
		BREAK
		CASE POPCYCLE_ZONE_AIRPORT_RUNWAY
			//--- Indifference to this zone
		BREAK
		CASE POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
			//--- Indifference to this zone
		BREAK
		CASE POPCYCLE_ZONE_RESIDENTIAL_POOR
			//--- Indifference to this zone
		BREAK

		CASE POPCYCLE_ZONE_BUSINESS
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_RICH_ZONES
				IF TIMERA >= GF_TIME_BEFORE_START_FUN_CHECKS
					fFunTemp += GF_FUN_INCREMENT_SMALL
				ENDIF
				GOSUB GF_Date_CommentLikeCurrentZone
			ELSE
				GOSUB GF_Date_CommentDislikeCurrentZone
			ENDIF		
		BREAK

		CASE POPCYCLE_ZONE_RESIDENTIAL_RICH
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_RICH_ZONES
				IF TIMERA >= GF_TIME_BEFORE_START_FUN_CHECKS
					fFunTemp += GF_FUN_INCREMENT_SMALL
				ENDIF
				GOSUB GF_Date_CommentLikeCurrentZone
			ELSE
				GOSUB GF_Date_CommentDislikeCurrentZone	
			ENDIF		
		BREAK

		CASE POPCYCLE_ZONE_RESIDENTIAL_RICH_SECLUDED
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_RICH_ZONES
				IF TIMERA >= GF_TIME_BEFORE_START_FUN_CHECKS
					fFunTemp += GF_FUN_INCREMENT_SMALL
				ENDIF
				GOSUB GF_Date_CommentLikeCurrentZone
			ELSE
				GOSUB GF_Date_CommentDislikeCurrentZone
			ENDIF		
		BREAK

		CASE POPCYCLE_ZONE_PARK
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_PARK_BEACH_ZONES
				IF TIMERA >= GF_TIME_BEFORE_START_FUN_CHECKS
					IF TIMERA >= GF_TIME_BEFORE_START_FUN_CHECKS
						fFunTemp += GF_FUN_INCREMENT_SMALL
					ENDIF
				ENDIF
				GOSUB GF_Date_CommentLikeCurrentZone
			ELSE
				GOSUB GF_Date_CommentDislikeCurrentZone
			ENDIF		
		BREAK

		CASE POPCYCLE_ZONE_BEACH
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_PARK_BEACH_ZONES
				IF TIMERA >= GF_TIME_BEFORE_START_FUN_CHECKS
					fFunTemp += GF_FUN_INCREMENT_SMALL
				ENDIF
				GOSUB GF_Date_CommentLikeCurrentZone
			ELSE
				GOSUB GF_Date_CommentDislikeCurrentZone
			ENDIF		
		BREAK

		CASE POPCYCLE_ZONE_GOLF_CLUB
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_PARK_BEACH_ZONES
				IF TIMERA >= GF_TIME_BEFORE_START_FUN_CHECKS
					fFunTemp += GF_FUN_INCREMENT_SMALL
				ENDIF
				GOSUB GF_Date_CommentLikeCurrentZone
			ELSE
				GOSUB GF_Date_CommentDislikeCurrentZone	
			ENDIF		
		BREAK

		CASE POPCYCLE_ZONE_DESERT
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_DESERT_COUNTRY_ZONES 
				IF TIMERA >= GF_TIME_BEFORE_START_FUN_CHECKS
					fFunTemp += GF_FUN_INCREMENT_SMALL
				ENDIF
				GOSUB GF_Date_CommentLikeCurrentZone
			ELSE
				GOSUB GF_Date_CommentDislikeCurrentZone
			ENDIF		
		BREAK

		CASE POPCYCLE_ZONE_COUNTRYSIDE
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_DESERT_COUNTRY_ZONES 
				IF TIMERA >= GF_TIME_BEFORE_START_FUN_CHECKS
					fFunTemp += GF_FUN_INCREMENT_SMALL
				ENDIF
				GOSUB GF_Date_CommentLikeCurrentZone
			ELSE
				GOSUB GF_Date_CommentDislikeCurrentZone	
			ENDIF		
		BREAK

		CASE POPCYCLE_ZONE_SHOPPING_POSH
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_SHOPPING_ZONES 
				IF TIMERA >= GF_TIME_BEFORE_START_FUN_CHECKS
					fFunTemp += GF_FUN_INCREMENT_SMALL
				ENDIF
				GOSUB GF_Date_CommentLikeCurrentZone
			ELSE
				GOSUB GF_Date_CommentDislikeCurrentZone	
			ENDIF		
		BREAK

		CASE POPCYCLE_ZONE_SHOPPING
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_SHOPPING_ZONES 
				IF TIMERA >= GF_TIME_BEFORE_START_FUN_CHECKS
					fFunTemp += GF_FUN_INCREMENT_SMALL
				ENDIF
				GOSUB GF_Date_CommentLikeCurrentZone
			ELSE
				GOSUB GF_Date_CommentDislikeCurrentZone	
			ENDIF		
		BREAK

		CASE POPCYCLE_ZONE_SHOPPING_BUSY
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_SHOPPING_ZONES 
				IF TIMERA >= GF_TIME_BEFORE_START_FUN_CHECKS
					fFunTemp += GF_FUN_INCREMENT_SMALL
				ENDIF
				GOSUB GF_Date_CommentLikeCurrentZone
			ELSE
				GOSUB GF_Date_CommentDislikeCurrentZone	
			ENDIF		
		BREAK

		CASE POPCYCLE_ZONE_ENTERTAINMENT_BUSY
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_ENTERTAINMENT_ZONES 
				IF TIMERA >= GF_TIME_BEFORE_START_FUN_CHECKS
					fFunTemp += GF_FUN_INCREMENT_SMALL
				ENDIF
				GOSUB GF_Date_CommentLikeCurrentZone
			ELSE
				GOSUB GF_Date_CommentDislikeCurrentZone	
			ENDIF		
		BREAK

		CASE POPCYCLE_ZONE_ENTERTAINMENT
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_ENTERTAINMENT_ZONES 
				IF TIMERA >= GF_TIME_BEFORE_START_FUN_CHECKS
					fFunTemp += GF_FUN_INCREMENT_SMALL
				ENDIF
				GOSUB GF_Date_CommentLikeCurrentZone
			ELSE
				GOSUB GF_Date_CommentDislikeCurrentZone	
			ENDIF		
		BREAK
		
		CASE POPCYCLE_ZONE_GANGLAND
			IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_GANG_ZONES 
				IF TIMERA >= GF_TIME_BEFORE_START_FUN_CHECKS
					fFunTemp += GF_FUN_INCREMENT_SMALL
				ENDIF
				GOSUB GF_Date_CommentLikeCurrentZone
			ELSE
				GOSUB GF_Date_CommentDislikeCurrentZone	
			ENDIF		
		BREAK

	ENDSWITCH

RETURN
/*******************************************
		COMMENT LIKE CURRENT ZONE
********************************************/
GF_Date_CommentLikeCurrentZone:
	
	GET_CURRENT_POPULATION_ZONE_TYPE iTemp2
	IF NOT iTemp2 = iStoredZone
		IF IS_BIT_SET iDateReport DRIVE
			//--- If on a date drive, check the fun meter before saying anything 
			IF iFun > 0
				iGFSayContext = CONTEXT_GLOBAL_GFRIEND_LIKE_CURRENT_ZONE
				iStoredZone = iTemp2
			ENDIF
		ELSE
			IF TIMERA >= GF_TIME_BEFORE_AREA_COMMENTS
				//--- Not on a drive date, the comment here is purely informative for the player
				iGFSayContext = CONTEXT_GLOBAL_GFRIEND_LIKE_CURRENT_ZONE
				iStoredZone = iTemp2
			ENDIF
		ENDIF
	ENDIF
RETURN
/*******************************************
		COMMENT DISLIKE CURRENT ZONE
********************************************/
GF_Date_CommentDislikeCurrentZone:

	GET_CURRENT_POPULATION_ZONE_TYPE iTemp2
	IF NOT iTemp2 = iStoredZone
		IF IS_BIT_SET iDateReport DRIVE
			//--- If on a date drive, check the fun meter before saying anything 
			IF iFun > 0
				iGFSayContext = CONTEXT_GLOBAL_GFRIEND_DISLIKE_CURRENT_ZONE
				iStoredZone = iTemp2
			ENDIF
		ELSE
			IF TIMERA >= GF_TIME_BEFORE_AREA_COMMENTS
				//--- Not on a drive date, the comment here is purely informative for the player
				iGFSayContext = CONTEXT_GLOBAL_GFRIEND_DISLIKE_CURRENT_ZONE
				iStoredZone = iTemp2
			ENDIF
		ENDIF
	ENDIF
RETURN
/*******************************************
	STORE CURRENT CAR DATA IF AVAILABLE
********************************************/
GF_Date_GetCurrentCarDataIfAvailable:

	IF IS_CHAR_IN_ANY_CAR iGF_ped
		STORE_CAR_CHAR_IS_IN_NO_SAVE iGF_ped iCurrentCar
		GET_CAR_HEALTH iCurrentCar iTemp
			//--- Check if we have stored this car's health already
			IF iCurrentCarHealth = -1
			   iCurrentCarHealth = iTemp	
			ENDIF
	ELSE
		//--- GF has left the car, reset the health readings
		iCurrentCar = -1
		iCurrentCarHealth = -1
	ENDIF

RETURN
/********************************************
		SYNCH GIRLFRIEND STATS
********************************************/
GF_Date_SynchStats:
	SWITCH iGFidx

		CASE COOCHIE 
			IF iGFLikesPlayer[iGFidx] > 100
				iGFLikesPlayer[iGFidx] = 100
			ENDIF 
			SET_INT_STAT GIRLFRIEND_DENISE iGFLikesPlayer[iGFidx]
		BREAK

		CASE MICHELLE
			IF iGFLikesPlayer[iGFidx] > 100
				iGFLikesPlayer[iGFidx] = 100
			ENDIF 
			SET_INT_STAT GIRLFRIEND_MICHELLE iGFLikesPlayer[iGFidx]
		BREAK

		CASE KYLIE
			IF iGFLikesPlayer[iGFidx] > 100
				iGFLikesPlayer[iGFidx] = 100
			ENDIF 
			SET_INT_STAT GIRLFRIEND_HELENA iGFLikesPlayer[iGFidx]
		BREAK

		CASE BARBARA
			IF iGFLikesPlayer[iGFidx] > 100
				iGFLikesPlayer[iGFidx] = 100
			ENDIF 
			SET_INT_STAT GIRLFRIEND_BARBARA iGFLikesPlayer[iGFidx]
		BREAK

		CASE SUZIE
			IF iGFLikesPlayer[iGFidx] > 100
				iGFLikesPlayer[iGFidx] = 100
			ENDIF 
			SET_INT_STAT GIRLFRIEND_KATIE iGFLikesPlayer[iGFidx]
		BREAK

		CASE MILLIE
			IF iGFLikesPlayer[iGFidx] > 100
				iGFLikesPlayer[iGFidx] = 100
			ENDIF 
			SET_INT_STAT GIRLFRIEND_MILLIE iGFLikesPlayer[iGFidx]
		BREAK

	ENDSWITCH
RETURN
/********************************************
				TWO TIMING
********************************************/
GF_Date_TwoTiming:
	SWITCH iGF_TT_Status

		CASE GF_TT_DO_NOT_RUN
			//--- Swtich the bit off if this request comes in
			CLEAR_BIT iDateReport PLAYER_TWO_TIMING 
		BREAK

		CASE GF_TT_INIT 
			//--- Find the GF, it might take a few attempts
			GENERATE_RANDOM_INT_IN_RANGE 0 6 iGF_TT_PedModel
			IF iGF_TT_PedModel < 6 // If this GF exists
			AND IS_BIT_SET iActiveGF iGF_TT_PedModel // If she is active
			AND NOT iGF_TT_PedModel = iGFidx // she must not the same girl we are on a date with!
				//--- Read what random girl has been extracted
				IF iGF_TT_PedModel = COOCHIE
					iGF_TT_PedModel = GANGRL3
					iGF_TT_CarModel = HUSTLER //83 1 
				ENDIF
				IF iGF_TT_PedModel = MICHELLE
					iGF_TT_PedModel = MECGRL3
					iGF_TT_CarModel = MONSTERB
				ENDIF
				IF iGF_TT_PedModel = BARBARA
					iGF_TT_PedModel = COPGRL3
					iGF_TT_CarModel = COPCARRU
				ENDIF
				IF iGF_TT_PedModel = KYLIE
					iGF_TT_PedModel = GUNGRL3
					iGF_TT_CarModel = BANDITO
				ENDIF
				IF iGF_TT_PedModel = SUZIE
					iGF_TT_PedModel = NURGRL3
					iGF_TT_CarModel = ROMERO //1 1
				ENDIF
				IF iGF_TT_PedModel = MILLIE
					iGF_TT_PedModel = CROGRL3
					iGF_TT_CarModel = FELTZER //126 1
				ENDIF
				//--- Move on to do requests for the above
				iGF_TT_Status = GF_TT_REQUESTS 
			ENDIF
		BREAK

		CASE GF_TT_REQUESTS
			//--- Request the models for two-timing
			IF HAS_MODEL_LOADED iGF_TT_CarModel
			AND HAS_MODEL_LOADED iGF_TT_PedModel
				iGF_TT_Status = GF_TT_CREATE_CAR 
			ELSE
				REQUEST_MODEL iGF_TT_CarModel
				REQUEST_MODEL iGF_TT_PedModel 
			ENDIF
		BREAK
		
		CASE GF_TT_CREATE_CAR									
			//--- Create and set up the car and the jelous girl
			GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 25.0 0.0 fX[0] fY[0] fZ[0]
			IF GET_CLOSEST_CAR_NODE_WITH_HEADING fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1] fTemp[0]
				CLEAR_AREA fX[1] fY[1] fZ[1] 5.0 TRUE 
				CREATE_CAR iGF_TT_CarModel fX[1] fY[1] fZ[1] iGF_TT_Car
				ADD_STUCK_CAR_CHECK_WITH_WARP iGF_TT_Car 1.0 1000 TRUE TRUE TRUE -1
				IF iGF_TT_CarModel = HUSTLER 
					CHANGE_CAR_COLOUR iGF_TT_Car 83 1 
				ENDIF
				IF iGF_TT_CarModel = ROMERO 
					CHANGE_CAR_COLOUR iGF_TT_Car 1 1 
				ENDIF
				IF iGF_TT_CarModel = FELTZER 
					CHANGE_CAR_COLOUR iGF_TT_Car 126 1 
				ENDIF
				CREATE_CHAR_INSIDE_CAR iGF_TT_Car PEDTYPE_CIVFEMALE iGF_TT_PedModel iGF_TT_driver
				SET_CHAR_CANT_BE_DRAGGED_OUT iGF_TT_driver TRUE
				SET_CAR_HEADING iGF_TT_Car fTemp[0] 					
				iGF_TT_Status = GF_TT_CUT1_START	 
			ELSE
				//--- Go to end state
				iGF_TT_Status = GF_TT_EMERGENCY_END
			ENDIF
		BREAK

		CASE GF_TT_CUT1_START			
			//--- The first cut scene. The girl has spotted the player, or better 'she think she has...'
			CLEAR_PRINTS
			CLEAR_HELP
			SET_PLAYER_CONTROL player1 OFF
			SET_EVERYONE_IGNORE_PLAYER player1 TRUE
			SWITCH_WIDESCREEN ON 
			//--- Camera Cut
			IF NOT IS_CAR_DEAD iGF_TT_Car				
			AND NOT IS_CHAR_DEAD iGF_TT_driver 
				ATTACH_CAMERA_TO_VEHICLE_LOOK_AT_CHAR iGF_TT_Car -1.5 1.5 1.5 iGF_TT_driver 6.0 JUMP_CUT
				TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_TT_driver GF_CarSpot KISSING 4.0 TRUE FALSE FALSE FALSE 5000
		  		PRINT_HELP GF_H014 //You've been spotted by another girlfriend. Quick, shake her off your tail!
		  		iGF_TT_Status = GF_TT_CUT1_END			
	  		ELSE
	  			//--- Go to end state
				iGF_TT_Status = GF_TT_EMERGENCY_END
			ENDIF
		BREAK

		CASE GF_TT_CUT1_END
		    //--- End of the cut, do some resetting of stuff and go on to intercept the player
		    IF NOT IS_CHAR_DEAD iGF_TT_driver
			AND NOT IS_CAR_DEAD iGF_TT_Car
			    GET_SCRIPT_TASK_STATUS iGF_TT_driver TASK_PLAY_ANIM_NON_INTERRUPTABLE iTemp2
				IF iTemp2 = FINISHED_TASK		   			
					//--- Check if the player has entered a car and warp in there (or she will be standing)
					IF NOT IS_CAR_DEAD iCurrentCar
						IF IS_CAR_PASSENGER_SEAT_FREE iCurrentCar 0 
						AND IS_CHAR_IN_CAR scplayer	iCurrentCar
							TASK_WARP_CHAR_INTO_CAR_AS_PASSENGER iGF_ped iCurrentCar 0	
						ENDIF 
					ENDIF
					SET_PLAYER_CONTROL player1 ON
					SET_EVERYONE_IGNORE_PLAYER player1 FALSE
					SWITCH_WIDESCREEN OFF
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT
					ADD_BLIP_FOR_CAR iGF_TT_Car iGF_TT_Blip
					iGF_TT_Status = GF_TT_INTERCEPT_PLAYER 	
				ENDIF
			ELSE
				//--- Go to end state
				iGF_TT_Status = GF_TT_EMERGENCY_END
			ENDIF
		BREAK
		
		CASE GF_TT_INTERCEPT_PLAYER
		    //--- The girls mission is to block the player and confront him!
		    IF NOT IS_CHAR_DEAD iGF_TT_driver
			AND NOT IS_CAR_DEAD iGF_TT_Car
				SET_CAR_MISSION  iGF_TT_Car MISSION_RAMPLAYER_CLOSE 				
				iGF_TT_Status = GF_TT_CHECK_PLAYER_POSITION
			ELSE
				//--- Go to end state
				iGF_TT_Status = GF_TT_EMERGENCY_END
			ENDIF
		BREAK

		CASE GF_TT_CHECK_PLAYER_POSITION 
		    //--- Try to assess where we are related to the player...
		    IF NOT IS_CHAR_DEAD iGF_TT_driver
			AND NOT IS_CAR_DEAD iGF_TT_Car
				GET_CHAR_COORDINATES scplayer fX[0] fY[0] fZ[0] 
				GET_CAR_COORDINATES iGF_TT_Car fX[1] fY[1] fZ[1]
				GET_DISTANCE_BETWEEN_COORDS_3D fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1] fTemp[0] 
				IF  fTemp[0] <= 6.0
					//--- Reached Player, freeze the cars
					SET_CAR_MISSION  iGF_TT_Car MISSION_NONE
					FREEZE_CAR_POSITION iGF_TT_Car TRUE
					SET_PLAYER_CONTROL player1 OFF
					DO_FADE 500 FADE_OUT
					iGF_TT_Status = GF_TT_REACHED_PLAYER
				ELSE 
					IF fTemp[0] > 200.0					
						//--- The player has put quite a distance between himself and the girl
						iTemp2 = TIMERA
						iTemp2 += GF_TIME_BEFORE_ESCAPED_TWOTIME
						iGF_TT_Status = GF_TT_VERIFY_PLAYER_LOST
					ENDIF				
				ENDIF
			ELSE
				//--- Go to end state
				iGF_TT_Status = GF_TT_EMERGENCY_END
			ENDIF
		BREAK

		CASE GF_TT_VERIFY_PLAYER_LOST
		    //--- Check that enough time has passed before the girl admits to having lost the player
		    IF NOT IS_CHAR_DEAD iGF_TT_driver
			AND NOT IS_CAR_DEAD iGF_TT_Car
				GET_CHAR_COORDINATES scplayer fX[0] fY[0] fZ[0] 
				GET_CAR_COORDINATES iGF_TT_Car fX[1] fY[1] fZ[1]
				GET_DISTANCE_BETWEEN_COORDS_3D fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1] fTemp[0] 
				IF  fTemp[0] > 180.0					 
				 	IF TIMERA > iTemp2 					 
						//--- The player has consistently kept the distance during this time
						IF DOES_BLIP_EXIST iGF_TT_Blip
							REMOVE_BLIP iGF_TT_Blip   
						ENDIF
						DELETE_CHAR iGF_TT_driver
						IF NOT IS_CAR_DEAD iGF_TT_Car 
							IF DOES_CAR_HAVE_STUCK_CAR_CHECK iGF_TT_Car
								REMOVE_STUCK_CAR_CHECK iGF_TT_Car
							ENDIF
							DELETE_CAR iGF_TT_Car
						ENDIF
						PRINT_HELP GF_H016 //You've managed to lose the jealous girlfriend. Well done.
						iGF_TT_Status = GF_TT_END
					ENDIF
				ELSE
					iGF_TT_Status = GF_TT_CHECK_PLAYER_POSITION
				ENDIF
			ELSE
				//--- Go to end state
				iGF_TT_Status = GF_TT_EMERGENCY_END
			ENDIF
		BREAK

		CASE GF_TT_REACHED_PLAYER
		    //--- The girl has cornered the player, set up the last cut
		    IF NOT IS_CHAR_DEAD iGF_TT_driver
			AND NOT IS_CAR_DEAD iGF_TT_Car 
				IF NOT GET_FADING_STATUS	
					CLEAR_PRINTS
					CLEAR_HELP
					SET_PLAYER_CONTROL player1 OFF
					SET_EVERYONE_IGNORE_PLAYER player1 TRUE
					SWITCH_WIDESCREEN ON
					SET_ALL_CARS_CAN_BE_DAMAGED FALSE					 
					SET_CAMERA_IN_FRONT_OF_PLAYER
					DO_FADE 500 FADE_IN
					TIMERB = 0	// This will count if she is on foot and taking too long to reach the player 
					iGF_TT_Status = GF_TT_CUT2_START
				ENDIF
			ELSE
				//--- Go to end state
				iGF_TT_Status = GF_TT_EMERGENCY_END
			ENDIF
		BREAK

		CASE GF_TT_CUT2_START
		    //--- The jelous girl shouts at him
		    IF NOT IS_CHAR_DEAD iGF_TT_driver
			AND NOT IS_CAR_DEAD iGF_TT_Car 
				IF NOT GET_FADING_STATUS
					//--- Set camera
					GET_ACTIVE_CAMERA_COORDINATES fX[0] fY[0] fZ[0]
					GET_CHAR_COORDINATES iGF_TT_driver fX[1] fY[1] fZ[1]
					SET_FIXED_CAMERA_POSITION fX[0] fY[0] fZ[0] 0.0 0.0 1.0
					POINT_CAMERA_AT_POINT fX[1] fY[1] fZ[1] INTERPOLATION
 					//--- Make the other girl invincible
 					SET_CHAR_PROOFS iGF_TT_driver TRUE TRUE TRUE TRUE TRUE
					SET_CAR_PROOFS iGF_TT_Car TRUE TRUE TRUE TRUE TRUE   
				   	//--- Play anim
				   	TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_TT_driver GF_CarArgue_01 KISSING 4.0 TRUE FALSE FALSE FALSE 5000
					//--- Make this 'extra' girlfriend speak
					iGFSpeechStatus = GF_SPEECH_SPECIAL_TT_CONTEXT // Put the Speech Manager in the special state
					iGFSayContext = CONTEXT_GLOBAL_GFRIEND_JEALOUS
					//IDEA: pass into CJ but change speech manager free state to 'NEXT IS NOT CJ BUT ANOTHER GF'
					iGF_TT_Status = GF_TT_CUT2_MIDDLE
				ENDIF	
			ELSE
				//--- Go to end state
				iGF_TT_Status = GF_TT_EMERGENCY_END
			ENDIF
		BREAK
		
		CASE GF_TT_CUT2_MIDDLE
		    //--- The girl on a date shouts back
		    IF NOT IS_CHAR_DEAD iGF_TT_driver
			AND NOT IS_CAR_DEAD iGF_TT_Car 
			    GET_SCRIPT_TASK_STATUS iGF_TT_driver TASK_PLAY_ANIM_NON_INTERRUPTABLE iTemp2
				IF iTemp2 = FINISHED_TASK		   							       					
				   	IF IS_CHAR_IN_ANY_CAR iGF_ped
						//--- Set camera
						GET_CHAR_COORDINATES iGF_ped fX[1] fY[1] fZ[1]
						POINT_CAMERA_AT_POINT fX[1] fY[1] fZ[1] INTERPOLATION
						//--- Make the girl invincible
						SET_CHAR_PROOFS iGF_ped TRUE TRUE TRUE TRUE TRUE
						//--- Play anims
					   	TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped GF_CarArgue_02 KISSING 4.0 TRUE FALSE FALSE FALSE 5000
						//--- The current date girl speaks
						iGFSayContext = CONTEXT_GLOBAL_GFRIEND_JEALOUS_REPLY
						iGF_TT_Status = GF_TT_CUT2_END
					ELSE
						//--- Girl is on foot
						GET_CHAR_COORDINATES iGF_ped fX[0] fY[0] fZ[0]
						GET_CHAR_COORDINATES scplayer fX[1] fY[1] fZ[1]
						GET_DISTANCE_BETWEEN_COORDS_3D fX[0] fY[0] fZ[0] fX[1] fY[1] fZ[1] fTemp[0]
						IF fTemp[0] < 5.0
						OR TIMERB > 6000 // has taken too long to get to the player
							//--- Set camera
							GET_CHAR_COORDINATES iGF_ped fX[1] fY[1] fZ[1]
							POINT_CAMERA_AT_POINT fX[1] fY[1] fZ[1] INTERPOLATION
							SET_CHAR_PROOFS iGF_ped TRUE TRUE TRUE TRUE TRUE
							TASK_PLAY_ANIM_NON_INTERRUPTABLE iGF_ped GF_StreetArgue_02 KISSING 4.0 TRUE FALSE FALSE FALSE 5000
							//--- The current date girl speaks
							iGFSayContext = CONTEXT_GLOBAL_GFRIEND_JEALOUS_REPLY
							iGF_TT_Status = GF_TT_CUT2_END
						ELSE
							//--- Wait until she is closer to the player
							BREAK
						ENDIF
					ENDIF
				ENDIF
			ELSE
				iGF_TT_Status = GF_TT_EMERGENCY_END
			ENDIF
		BREAK
		
		CASE GF_TT_CUT2_END
		    //--- Both angry girls leave the player
		    IF NOT IS_CHAR_DEAD iGF_TT_driver
			AND NOT IS_CAR_DEAD iGF_TT_Car 
			    GET_SCRIPT_TASK_STATUS iGF_ped TASK_PLAY_ANIM_NON_INTERRUPTABLE iTemp2
				IF iTemp2 = FINISHED_TASK		   									
				    GET_SCRIPT_TASK_STATUS iGF_TT_driver TASK_CAR_DRIVE_WANDER iTemp2
				    IF iTemp2 = FINISHED_TASK
				    	FREEZE_CAR_POSITION iGF_TT_Car FALSE
				    	TASK_CAR_DRIVE_WANDER iGF_TT_driver iGF_TT_Car 30.0 DRIVINGMODE_PLOUGHTHROUGH
					ENDIF
					IF NOT IS_CAR_DEAD iCurrentCar
						IF IS_CHAR_IN_CAR iGF_ped iCurrentCar
							GET_SCRIPT_TASK_STATUS iGF_ped TASK_LEAVE_CAR iTemp2
							IF iTemp2 = FINISHED_TASK
								TASK_LEAVE_CAR iGF_ped iCurrentCar								 
							ENDIF
						ELSE
							GET_SCRIPT_TASK_STATUS iGF_ped TASK_FLEE_CHAR_ANY_MEANS iTemp2
							IF iTemp2 = FINISHED_TASK
								TASK_FLEE_CHAR_ANY_MEANS iGF_ped scplayer 100.0 5000 FALSE 0 0 30.0
							ENDIF
						ENDIF
					ENDIF					
					TIMERB = 0
					iGF_TT_Status = GF_TT_END
				ENDIF						
			ELSE
				//--- Go to end state
				iGF_TT_Status = GF_TT_EMERGENCY_END
			ENDIF
		BREAK

		CASE GF_TT_END
		    IF NOT IS_CHAR_DEAD iGF_TT_driver
			AND NOT IS_CAR_DEAD iGF_TT_Car 			    
				IF TIMERB > 5000		   									
					DO_FADE 1000 FADE_OUT
					//--- Update the stats for the girls
					iGFLikesPlayer[iGFidx] -= GF_LIKES_PLAYER_DECREMENT_TWOTIMING					
					//--- Retrieve the jealous girl IDX from the model
					IF iGF_TT_PedModel = GANGRL3					
						iTemp2 = COOCHIE
					ENDIF
					IF iGF_TT_PedModel = MECGRL3
						iTemp2 = MICHELLE	
					ENDIF
					IF iGF_TT_PedModel = COPGRL3
						iTemp2 = BARBARA
					ENDIF							  
					IF iGF_TT_PedModel = GUNGRL3
						iTemp2 = KYLIE
					ENDIF
					IF iGF_TT_PedModel = NURGRL3
						iTemp2 = SUZIE
					ENDIF
					IF iGF_TT_PedModel = CROGRL3
						iTemp2 = MILLIE
					ENDIF					
					iGFLikesPlayer[iTemp2] -= GF_LIKES_PLAYER_DECREMENT_TWOTIMING
					//--- Re-synch the statistics
					//GOSUB GF_Date_SynchStats
					//--- Move on 
					iGF_TT_Status = GF_TT_END_WRAP_UP
				ENDIF
			ELSE
				iGF_TT_Status = GF_TT_EMERGENCY_END
			ENDIF
		BREAK

		CASE GF_TT_END_WRAP_UP
			IF NOT GET_FADING_STATUS				
				SET_PLAYER_CONTROL player1 ON
				SET_EVERYONE_IGNORE_PLAYER player1 FALSE
				SWITCH_WIDESCREEN OFF
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				SET_BIT iDateFlags BORED_TAKE_ME_HOME // Girl Got Bored\Annoyed
				//--- Delete all the TT peds - a bit dirty, but the date manager will cope with this
				DELETE_CHAR iGF_TT_driver
				IF NOT IS_CAR_DEAD iGF_TT_Car 
					IF DOES_CAR_HAVE_STUCK_CAR_CHECK iGF_TT_Car
						REMOVE_STUCK_CAR_CHECK iGF_TT_Car
					ENDIF
					DELETE_CAR iGF_TT_Car
				ENDIF
				SET_CHAR_PROOFS iGF_ped FALSE FALSE FALSE FALSE FALSE
				DO_FADE 1500 FADE_IN
				PRINT_HELP GF_H015 //You have been discovered two-timing. Your date is over.
				//--- Date Ended
				GOSUB GF_Date_Cleanup  
			ENDIF
		BREAK

		CASE GF_TT_EMERGENCY_END
			//--- EMERGENCY!! We might have ended here because something went tits up...
			SET_PLAYER_CONTROL player1 ON
			SWITCH_WIDESCREEN OFF
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			IF NOT IS_CHAR_DEAD iGF_ped
				SET_CHAR_PROOFS iGF_ped FALSE FALSE FALSE FALSE FALSE
			ENDIF
			IF NOT IS_CHAR_DEAD iGF_TT_driver								
				SET_CHAR_PROOFS iGF_TT_driver FALSE FALSE FALSE FALSE FALSE
			ENDIF
			IF NOT IS_CAR_DEAD iGF_TT_Car 
				IF DOES_CAR_HAVE_STUCK_CAR_CHECK iGF_TT_Car
					REMOVE_STUCK_CAR_CHECK iGF_TT_Car
				ENDIF				
			ENDIF
			IF DOES_BLIP_EXIST iGF_TT_Blip
				REMOVE_BLIP iGF_TT_Blip
			ENDIF
			//--- If the payer has killed his other girl, she should be removed
			IF IS_CHAR_DEAD iGF_TT_driver
			AND HAS_CHAR_BEEN_DAMAGED_BY_CHAR iGF_TT_driver scplayer
				//--- Retrieve the jealous girl IDX from the model
				IF iGF_TT_PedModel = GANGRL3					
					iTemp2 = COOCHIE
				ENDIF
				IF iGF_TT_PedModel = MECGRL3
					iTemp2 = MICHELLE	
				ENDIF
				IF iGF_TT_PedModel = COPGRL3
					iTemp2 = BARBARA
				ENDIF								   
				IF iGF_TT_PedModel = GUNGRL3
					iTemp2 = KYLIE
				ENDIF
				IF iGF_TT_PedModel = NURGRL3
					iTemp2 = SUZIE
				ENDIF
				IF iGF_TT_PedModel = CROGRL3
					iTemp2 = MILLIE
				ENDIF
				//--- Mark this girl as dead
				iGFLikesPlayer[iTemp2] = GF_IS_DEAD
			ENDIF 
			//--- Clear this bit, as if nothing ever happened...
			CLEAR_BIT iDateReport PLAYER_TWO_TIMING	
			//--- Stop running the two timing stuff
			iGF_TT_Status = GF_TT_DO_NOT_RUN			
		BREAK

	ENDSWITCH
RETURN
/********************************************
			SPEECH MANAGER
********************************************/
GF_Date_SpeechManager:

SWITCH iGFSpeechStatus
	
	CASE GF_SPEECH_FREE
		IF iGFSayContext > 0
			iGFSpeechStatus = GF_SPEECH_REQUEST
		ELSE
			IF iGFSayContext = GF_CONTEXT_JUSTPLAYER
			AND iCJSayContext > 0
				iGFSpeechStatus = GF_SPEECH_REQUEST
			ENDIF
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
				SET_CHAR_SAY_CONTEXT_IMPORTANT iGF_ped iGFSayContext FALSE TRUE FALSE iGFContextVariation
				GOSUB GF_Date_RetrieveSpeechSubtitleForThisContext
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
				 SET_CHAR_SAY_CONTEXT_IMPORTANT scplayer iCJSayContext FALSE TRUE FALSE iGFContextVariation
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
				 SET_CHAR_SAY_CONTEXT_IMPORTANT iGF_ped iGFSayContext FALSE TRUE FALSE iGFContextVariation
				 GOSUB GF_Date_RetrieveSpeechSubtitleForThisContext
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

	CASE GF_SPEECH_SPECIAL_TT_CONTEXT
		IF NOT IS_CHAR_DEAD iGF_TT_driver
			IF NOT IS_CHAR_TALKING iGF_TT_driver 
				 IF iGFSayContext > 0 
					 SET_CHAR_SAY_CONTEXT_IMPORTANT iGF_TT_driver iGFSayContext FALSE TRUE FALSE iGFContextVariation
					 GOSUB GF_Date_RetrieveSpeechSubtitleForThisContext
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
		ENDIF
	BREAK

ENDSWITCH

RETURN
/********************************************
	RETRIEVE SPEECH SUBTITLE FOR CONTEXT
********************************************/
GF_Date_RetrieveSpeechSubtitleForThisContext:

	SWITCH iGFSayContext

		CASE CONTEXT_GLOBAL_GFRIEND_REQ_MEAL_NORMAL
			// Just leave the help text box
			// PRINT_NOW GF_0026 5000 1 
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_REQ_DRIVE_NORMAL
			// Just leave the help text box
			//PRINT_NOW GF_0027 5000 1 
		BREAK 
		CASE CONTEXT_GLOBAL_GFRIEND_REQ_DANCE_NORMAL
			// Just leave the help text box
			// PRINT_NOW GF_0028 5000 1 
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_REQUEST_TO_DRIVE_CAR
			PRINT_NOW GF_0045 5000 1 
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_REQ_SEX_NORMAL
			PRINT_NOW GF_0061 5000 1
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_REQ_SEX_DESPERATE 
			PRINT_NOW GF_0044 5000 1 
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_TAKE_HOME_HAPPY
			PRINT_NOW GF_0022 5000 1
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_BORED_1 
			IF IS_BIT_SET iDateReport EAT_OUT
				PRINT_NOW GF_0077 5000 1 
			ELSE
				IF IS_BIT_SET iDateReport DRIVE
					PRINT_NOW GF_0078 5000 1 
				ELSE
					IF IS_BIT_SET iDateReport DANCE
						PRINT_NOW GF_0079 5000 1 
					ELSE
						//--- default
						PRINT_NOW GF_0031 5000 1
					ENDIF
				ENDIF
			ENDIF
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_BORED_2
			PRINT_NOW GF_0031 5000 1
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_HELLO
			PRINT_NOW GF_0004 3000 1
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_ENJOYED_MEAL_HIGH
			PRINT_NOW GF_0007 5000 1
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_ENJOYED_EVENT_LOW
			PRINT_NOW GF_0066 5000 1
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_DUMP_PLAYER_LIVE
			PRINT_NOW GF_0011 5000 1
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_PHYSIQUE_CRITIQUE
			IF IS_BIT_SET iDateFlags PLAYER_NOT_FAT
				PRINT_NOW GF_0074 5000 1 
				BREAK
			ENDIF	
			IF IS_BIT_SET iDateFlags PLAYER_NOT_FIT
				PRINT_NOW GF_0053 5000 1 
				BREAK
			ENDIF	
			IF IS_BIT_SET iDateFlags PLAYER_TOO_FIT
				PRINT_NOW GF_0075 5000 1 
				BREAK
			ENDIF	
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_SEX_APPEAL_TOO_LOW
			PRINT_NOW GF_0052 5000 1 
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_GOODBYE_ANGRY
			PRINT_NOW GF_0034 5000 1
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_COFFEE
			PRINT_NOW GF_0014 5000 1
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_MEET_AGAIN
			PRINT_NOW GF_0033 5000 1
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_GOODBYE			
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_DO_A_DRIVEBY
			PRINT_NOW GF_0024 5000 1
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_TAKE_HOME_ANGRY
			PRINT_NOW GF_0032 5000 1
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_DROP_PLAYER_DRIVE_AWAY
			PRINT_NOW GF_0047 5000 1
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_ENJOYED_CLUB_HIGH
			PRINT_NOW GF_0054 5000 1
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_LIKE_CURRENT_ZONE
			PRINT_NOW GF_0050 5000 1
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_DISLIKE_CURRENT_ZONE
			PRINT_NOW GF_0051 5000 1
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_PARK_LOCATION_HATE
			PRINT_NOW GF_0070 5000 1			
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_HIT_BY_PLAYER_WARNING
			PRINT_NOW GF_0072 5000 1
		BREAK
		CASE CONTEXT_GLOBAL_CAR_FAST
			PRINT_NOW GF_0073 2500 1			
		BREAK
		CASE CONTEXT_GLOBAL_CAR_SLOW
			PRINT_NOW GF_0076 2500 1			
		BREAK
	ENDSWITCH
RETURN
/*******************************************
	REMOVE DATE BLIPS FOR CURRENNT CITY
********************************************/
GF_Date_RemoveAllBlipsAndCounters:

	REPEAT SIZE_OF_BLIP_ARRAY iTemp2
   		IF DOES_BLIP_EXIST iLocationBlip[iTemp2] 
	   		REMOVE_BLIP iLocationBlip[iTemp2]
		ENDIF
	ENDREPEAT
	
	IF DOES_BLIP_EXIST iGF_TT_Blip 
	   	REMOVE_BLIP iGF_TT_Blip
	ENDIF

	CLEAR_ONSCREEN_COUNTER iFun
	IF IS_BIT_SET iDateReport DRIVE
	ENDIF	 
	
	CLEAR_BIT iDateFlags 11	//BLIP_FOR_HOME_ON

RETURN
/*******************************************
				CLEAN UP 
********************************************/
GF_Date_Cleanup:
	 
	GOSUB GF_Date_RemoveAllBlipsAndCounters
	GOSUB GF_Date_ResetDateEntryExits
	
	CLEAR_PRINTS
	
	//--- Group clean up	 
	IF NOT IS_CHAR_DEAD iGF_ped
		IF IS_GROUP_MEMBER iGF_ped players_group
			REMOVE_CHAR_FROM_GROUP iGF_ped
		ENDIF
	ENDIF

	//--- Current Car clean up
	IF NOT IS_CAR_DEAD iCurrentCar
		IF DOES_CAR_HAVE_STUCK_CAR_CHECK iCurrentCar 
			REMOVE_STUCK_CAR_CHECK iCurrentCar 
		ENDIF
	ENDIF

    //--- TWO-TIMING clean up
	IF iGF_TT_Status > 0
		IF IS_PLAYER_PLAYING player1
			SET_PLAYER_CONTROL player1 ON
			SET_EVERYONE_IGNORE_PLAYER player1 FALSE
		ENDIF
	ENDIF
	IF NOT IS_CAR_DEAD iGF_TT_Car 
		IF DOES_CAR_HAVE_STUCK_CAR_CHECK iGF_TT_Car
			REMOVE_STUCK_CAR_CHECK iGF_TT_Car
		ENDIF
	ENDIF

	MARK_MODEL_AS_NO_LONGER_NEEDED iGF_TT_CarModel
	MARK_MODEL_AS_NO_LONGER_NEEDED iGF_TT_PedModel
	MARK_CHAR_AS_NO_LONGER_NEEDED iGF_TT_driver 
	MARK_CAR_AS_NO_LONGER_NEEDED iGF_TT_Car
	
	//--- Date nimations clean up
	REMOVE_ANIMATION KISSING
	IF iCensoredVersion = 0
		REMOVE_ANIMATION ABCDFETS
	ENDIF

	//--- Damage and Death checks
	IF IS_CHAR_DEAD iGF_ped				
		//--- Mark date as a disaster 
		CLEAR_BIT iDateReport DATE_WAS_SUCCESS
		//--- A girlfriend is really dead only if the player has killed her
		IF IS_BIT_SET iDateFlags GIRL_ATTACKED_BY_PLAYER 
			iGFLikesPlayer[iGFidx] = GF_IS_DEAD
		ENDIF
	ELSE
		//--- Girl is alive
		IF IS_BIT_SET iDateFlags GIRL_ATTACKED_BY_PLAYER 
			//--- Mark the date as a failure
			CLEAR_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A FAILURE
			//--- Check health
			IF NOT IS_CHAR_HEALTH_GREATER iGF_ped GF_HEALTH_LIMIT_FOR_DEATH
				iGFLikesPlayer[iGFidx] = GF_IS_DEAD
			ELSE
				//--- Have an extra decrement to the likes player
				iGFLikesPlayer[iGFidx] -= GF_LIKES_PLAYER_DECREMENT_BEATEN_UP
				//--- Re-Synch the statistics
				//GOSUB GF_Date_SynchStats
			ENDIF
		ENDIF
	ENDIF

	IF IS_BIT_SET iDateFlags BORED_TAKE_ME_HOME // Girl Got Bored\Annoyed				
		//--- Mark date as a failure
		CLEAR_BIT iDateReport DATE_WAS_SUCCESS // DATE WAS A FAILURE
	ENDIF

	//--- Dance housekeeping
	iSwitchOffDanceMiniGame = 0 // Clear the flag that switches off the dance mini-game
	iDanceGirlfriend = -1 // Remove the GF ped from the global for the dance mini-game

	//--- Date housekeeping	
	IF IS_BIT_SET iDateFlags DATE_ABORTED
		IF NOT IS_BIT_SET iDateReport KINKY_SEX	 // Kinky sex will abort a date right away but without penalty
			SET_BIT iDateReport DATE_WAS_ABORTED // Mark the date as aborted so the stats can update accordingly
		ENDIF
	ELSE
		SET_BIT iDateReport MEET_TOMORROW // A date took place, so don't date again until tomorrow 
	ENDIF

	//--- Always set this in case something bad happened during one of the cut-scenes
	IF IS_PLAYER_PLAYING player1
		SET_CHAR_CANT_BE_DRAGGED_OUT scplayer FALSE // in case we set this earlier - STATE 8
		SET_PLAYER_CONTROL player1 ON
		SWITCH_WIDESCREEN OFF
		//--- Restore the player's group back to its default settings
		SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS PLAYER1 FALSE
		SET_PLAYER_GROUP_RECRUITMENT PLAYER1 TRUE	
	ENDIF

	SHOW_UPDATE_STATS  TRUE

	//--- End of script
	CLEAR_BIT iDateReport DATE_IN_PROGRESS //Mark the END of the Date for the Dating Agent
	SET_BIT iGFAvailableFor2Player iGFidx // Mark GF as available for 2 player games	

	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
				RUN DEBUG
********************************************/
GF_Date_Debug:		  

	WRITE_DEBUG_WITH_INT IGF_TT_STATUS iGF_TT_Status 
	WRITE_DEBUG_WITH_INT $txtEntryExitName iCurrentArea
	WRITE_DEBUG_WITH_INT ICURRENTCARHEALTH iCurrentCarHealth
	WRITE_DEBUG_WITH_FLOAT FTEMP1_SPEED fTemp[1]
    WRITE_DEBUG_WITH_FLOAT FUN_TEMP fFunTemp
	WRITE_DEBUG_WITH_INT TIMERB TIMERB
	WRITE_DEBUG_WITH_INT DATE_TIMER TIMERA
	WRITE_DEBUG_WITH_INT SubStateStatus iSubStateStatus
	
	SWITCH iDateState
		CASE 0
			WRITE_DEBUG STATE0__DATE_INIT
		BREAK
		CASE 1
			WRITE_DEBUG STATE1__IDLE_ON_FOOT 
		BREAK
		CASE 2
			WRITE_DEBUG STATE2__REACHED_DEST 
		BREAK
		CASE 3
			WRITE_DEBUG STATE3__REACHED_HOME
		BREAK
		CASE 4
			WRITE_DEBUG STATE4__IDLE_IN_CAR
		BREAK
		CASE 5
			WRITE_DEBUG STATE5__DUMP_PLAYER
		BREAK
		CASE 6
			WRITE_DEBUG STATE6__KISS_ON_FOOT
		BREAK
		CASE 7
			WRITE_DEBUG STATE7__BLOWJOB_IN_CAR
		BREAK
		CASE 8
			WRITE_DEBUG STATE8__GIRL_DRIVES
		BREAK
		CASE 9
			WRITE_DEBUG STATE9__FLEE_PLAYER
		BREAK
		CASE 10
			WRITE_DEBUG STATE10__DANCE
		BREAK
		DEFAULT
			WRITE_DEBUG_WITH_INT UNTRAPPED_STATE___ iDateState
		BREAK
	ENDSWITCH

	WRITE_DEBUG I__________________________I

RETURN
}
MISSION_END
