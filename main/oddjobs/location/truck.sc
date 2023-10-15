MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
//
//			   MISSION NAME : Truck
//				     AUTHOR : Keith
//		        DESCRIPTION : Trucking delivery missions from Truck Depot to other cities.
//
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

{
// *************************************************************************************************************
//								GENERAL INITIALISATION - Every level requires this
// *************************************************************************************************************


SCRIPT_NAME Truck


// Global Variables for Main
VAR_INT		g_nTruckMissionsPassed



// Global Variables
VAR_INT		g_Truck_cashKM
VAR_INT		g_Truck_damageKM
VAR_INT		g_Truck_timedKM



// Mission Start stuff
GOSUB mission_start_Truck

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_failed_Truck
ENDIF

GOSUB mission_cleanup_Truck

MISSION_END


// Global Setup
mission_start_Truck:

IF done_truck_progress = FALSE
	REGISTER_MISSION_GIVEN
ENDIF

flag_player_on_mission	= TRUE
flag_player_on_oddjob	= TRUE



// TEMP vars
LVAR_INT	TEMP_nTestAreas
TEMP_nTestAreas = 0
LVAR_INT	TEMP_nTestDestinations
TEMP_nTestDestinations = 0




// general variables that are in every mission - DON'T DECLARE MISSION SPECIFIC VARIABLES HERE
LVAR_INT 	m_stage
LVAR_INT 	m_goals
LVAR_INT 	m_passed
LVAR_INT 	m_failed  
LVAR_INT 	m_quit
LVAR_INT 	m_pause
LVAR_INT 	m_status
LVAR_INT 	m_frame_num
LVAR_INT 	m_this_frame_time
LVAR_INT 	m_last_frame_time
LVAR_INT 	m_time_diff	
// commonly used timers
LVAR_INT 	m_mission_timer
// Debug variables
LVAR_INT 	display_debug
VAR_INT 	Truck_view_debug[8]	// GLOBAL (for output)
// useful int variables
LVAR_INT	nLoop
LVAR_INT	nTempInt
LVAR_INT	nTempInt2
LVAR_INT	nTempInt3
LVAR_INT	nTempPed
LVAR_INT	nTempCar
LVAR_INT	nTempObject
LVAR_INT	nTempAmmo
LVAR_INT	nTempTimer
LVAR_INT	nSeqTask
// useful float variables
LVAR_FLOAT	xposTemp	yposTemp	zposTemp
LVAR_FLOAT	headTemp
LVAR_FLOAT	xloTemp		yloTemp		zloTemp
LVAR_FLOAT	xhiTemp		yhiTemp		zhiTemp
LVAR_FLOAT	fTempFloat
LVAR_FLOAT	fTempFloat2
LVAR_FLOAT	fTempDistance
// useful flag variables
LVAR_INT	flagTempFlag
LVAR_INT	flagTempFlag2


m_stage			= 0
m_goals			= 0
m_passed		= FALSE
m_failed		= FALSE
m_quit			= FALSE
m_pause			= FALSE
m_status		= 0

m_mission_timer	= 0

display_debug	= 0

TIMERA = 0
TIMERB = 0



// Consts
// ...highest trucking mission
CONST_INT	TRUCK_HIGHEST_TRUCKING_MISSION			8

// ...mission types
CONST_INT	TRUCK_NORMAL_DAMAGE_MISSION				1
CONST_INT	TRUCK_NORMAL_TIMED_MISSION				2
CONST_INT	TRUCK_FRAGILE_DAMAGE_MISSION			3
CONST_INT	TRUCK_ILLEGAL_MISSION					4
CONST_INT	TRUCK_TIMED_DAMAGE_MISSION				5
CONST_INT	TRUCK_HIGHLY_ILLEGAL_MISSION			6
// -----
CONST_INT	TRUCK_HIGHEST_MISSION					6	// same as the last mission above

// ...mission destinations
CONST_INT	TRUCK_COUNTRY_EAST_NEAR_DESTINATION		1
CONST_INT	TRUCK_COUNTRY_EAST_FAR_DESTINATION		2
CONST_INT	TRUCK_LOS_SANTOS_DESTINATION			3
CONST_INT	TRUCK_COUNTRY_SOUTH_DESTINATION			4
CONST_INT	TRUCK_SAN_FIERRO_DESTINATION			5
CONST_INT	TRUCK_COUNTRY_NORTH_DESTINATION			6
CONST_INT	TRUCK_COUNTRY_NORTH_2_DESTINATION		7
CONST_INT	TRUCK_LAS_VENTURAS_DESTINATION			8
// -----
CONST_INT	TRUCK_HIGHEST_DESTINATION				8	// same as the last destination above

// ...mission time IDs
CONST_INT	TRUCK_MISSION_TIME_NOT_CALCULATED		0
CONST_INT	TRUCK_MISSION_TIME_NOT_LATE				1
CONST_INT	TRUCK_MISSION_TIME_LATE					2
CONST_INT	TRUCK_MISSION_TIME_VERY_LATE			3
CONST_INT	TRUCK_MISSION_TIME_LAST_CHANCE			4
CONST_INT	TRUCK_MISSION_TIME_ELAPSED				5

// ...distance multipliers to calculate mission times
CONST_INT	TRUCK_DISTANCE_TO_TIME_CALC_NOT_LATE	140
CONST_INT	TRUCK_DISTANCE_TO_TIME_CALC_LATE		84	// 60% of NOT_LATE value
CONST_INT	TRUCK_LAST_CHANCE_hours					2
CONST_INT	TRUCK_LAST_CHANCE_mins					0

// ...reasons for failure
CONST_INT	TRUCK_FAILED_UNKNOWN					0
CONST_INT	TRUCK_FAILED_OUT_OF_CASH				1
CONST_INT	TRUCK_FAILED_OUT_OF_TIME				2
CONST_INT	TRUCK_FAILED_TOO_MUCH_DAMAGE			3
CONST_INT	TRUCK_FAILED_NOT_IN_CAB					4
CONST_INT	TRUCK_FAILED_TRAILER_NOT_ATTACHED		5

// ...car health values
CONST_INT	TRUCK_CAR_NOT_ON_FIRE_HEALTH_BOUNDARY	250

// ...available trailers
CONST_INT	TRUCK_TRAILER_PETROTR					1
CONST_INT	TRUCK_TRAILER_ARTICT1					2
CONST_INT	TRUCK_TRAILER_ARTICT2					3
CONST_INT	TRUCK_TRAILER_ARTICT3					4
// -----
CONST_INT	TRUCK_HIGHEST_TRAILER					4	// same as the last trailer above

// ...available cabs
CONST_INT	TRUCK_CAB_PETRO							1
CONST_INT	TRUCK_CAB_LINERUN						2
CONST_INT	TRUCK_CAB_RDTRAIN						3
// -----
CONST_INT	TRUCK_HIGHEST_CAB						3	// same as the last cab above



// Mission Specific Variables
// Integer Variables
// ...chars 				(char)
// ...cars 					(car)
LVAR_INT	carCab
LVAR_INT	carTrailer
// ...blips 				(blip)
LVAR_INT	blipDestination
LVAR_INT	blipCab
LVAR_INT	blipFailureOutOfCab
LVAR_INT	blipFailureUnattachedTrailer
// ...pickups 				(pickup)
// ...fx systems 			(fx)
// ...decision makers		(dm)
// ...AI Status				(ai)
// ...general status		(status)
// ...Timers				(timer)
LVAR_INT	timerFailureConditions
LVAR_INT	timerMissionControl
LVAR_INT	timerCutscene
// ...Counters				(count)
LVAR_INT	countCash
LVAR_INT	countFailure
// ...mission specific		(n)
LVAR_INT	nMissionSelected
LVAR_INT	nDestinationArea
LVAR_INT	nDestinationLocation
LVAR_INT	nMissionType
LVAR_INT	nReasonForFailure
LVAR_INT	nInitialHealth
LVAR_INT	nInitialCash
LVAR_INT	nMissionTimeStage
LVAR_INT	nDestinationDistance
LVAR_INT	nHours
LVAR_INT	nMinutes
LVAR_INT	nThisTrailer
LVAR_INT	nThisCab


// Text Label Variables
LVAR_TEXT_LABEL		tlMissionType
LVAR_TEXT_LABEL		tlMissionDestination


// Float Variables
// ...area variables 		(xlo, ylo, zlo, xhi, yhi, zhi)
// ...position variables	(xpos, ypos, zpos)
LVAR_FLOAT	xposDestination		yposDestination		zposDestination
// ...heading variables		(head)


// Boolean Variables
// ...flags					(flag)
LVAR_INT	flagFailureConditionsApply
LVAR_INT	flagOutOfDepot
LVAR_INT	flagTimedMission
LVAR_INT	flagDamageMission
LVAR_INT	flagPoliceMission
LVAR_INT	flagDisplayCashCutLate
LVAR_INT	flagDisplayCashCutVeryLate
LVAR_INT	flagDisplayLastChance
LVAR_INT	flagArrived
LVAR_INT	flagShowAssetAcquired
// ...bit flags				(bits)
// ...exists flags			(exists)
//...clear exists flags


// Clear timers


// Clear important flags
flagFailureConditionsApply	= FALSE
flagOutOfDepot				= FALSE
flagShowAssetAcquired		= FALSE



// ***** FAKE ENTITY CREATION TO FOOL THE COMPILER *****
// The compiler just needs to verify there is a CREATE_ before usage
IF m_stage = -99
	
	WRITE_DEBUG SHOULD_NEVER_BE_IN_FAKE_ENTITY_CREATION

	// Cars
	CREATE_CAR PETRO 0.0 0.0 0.0 carCab
	CREATE_CAR PETROTR 0.0 0.0 0.0 carTrailer

	// Objects

	// Blips
	ADD_BLIP_FOR_CAR carCab		blipFailureOutOfCab
	ADD_BLIP_FOR_CAR carCab		blipCab
	ADD_BLIP_FOR_CAR carTrailer	blipFailureUnattachedTrailer

ENDIF



// Mission Text
LOAD_MISSION_TEXT TRUCK



// Ensure the Trucking mission area is open.
// ...mission 6 and 7 needs LV to be open
IF g_nTruckMissionsPassed >= 6
	GET_INT_STAT CITIES_PASSED Return_cities_passed
	IF Return_cities_passed < 2
		// ...Vegas not open
		PRINT_NOW TRK_H17 5000 1
		IF NOT IS_CHAR_DEAD scplayer
			SET_CHAR_COORDINATES scplayer -76.9664 -1134.6389 0.0781 
			SET_CHAR_HEADING scplayer 14.7291
			SET_CAMERA_BEHIND_PLAYER
			SET_PLAYER_CONTROL player1 ON
		ENDIF
		DO_FADE 500 FADE_IN
		WAIT 5000

		// Return the oddjob 'title over fade' status back to default
		DRAW_ODDJOB_TITLE_BEFORE_FADE TRUE

		RETURN
	ENDIF
ENDIF

// ...mission 4 and 5 needs SF to be open
IF g_nTruckMissionsPassed >= 4
	GET_INT_STAT CITIES_PASSED Return_cities_passed
	IF Return_cities_passed < 1
		// ...SanFran not open
		PRINT_NOW TRK_H18 5000 1
		IF NOT IS_CHAR_DEAD scplayer
			SET_CHAR_COORDINATES scplayer -76.9664 -1134.6389 0.0781 
			SET_CHAR_HEADING scplayer 14.7291
			SET_CAMERA_BEHIND_PLAYER
			SET_PLAYER_CONTROL player1 ON
		ENDIF
		DO_FADE 500 FADE_IN
		WAIT 5000

		// Return the oddjob 'title over fade' status back to default
		DRAW_ODDJOB_TITLE_BEFORE_FADE TRUE

		RETURN
	ENDIF
ENDIF



// Intro Cutscene
GOSUB Truck_Intro_Cutscene


// Mission Initialisation
GOSUB Truck_Initialisation


// Load Char Mission Decision Makers


//USE_TEXT_COMMANDS TRUE
WAIT 100


// *************************************************************************************************************
//								                 MISSION LOOP
// *************************************************************************************************************
mission_loop_Truck:

	WAIT 0


	// Debug Stuff
	GOSUB Truck_Debug_Tools
	GOSUB Truck_Debug_Shortcuts

	IF m_quit = 1
	OR m_pause = 1
		GOTO end_of_main_loop_Truck
	ENDIF


	// Housekeeping
	GOSUB Truck_Frame_Counter
	GOSUB Truck_Additional_Timers


	// Special conditions
	IF IS_CHAR_DEAD scplayer
		m_failed = TRUE
		GOTO end_of_main_loop_Truck
	ENDIF		


	// Mission Stage processing
	// *** INITIALISATION NOW TAKES PLACE BEFORE THE MAIN LOOP ***
	IF m_stage = 0
		WRITE_DEBUG STAGE_SHOULD_NEVER_BE_0_IN_MAIN_LOOP
	ENDIF


	// ...Stage 1: Wait for player to enter cab
	IF m_stage = 1
		GOSUB Truck_Stage_EnterCab
	ENDIF 


	// ...Stage 2: Perform mission
	IF m_stage = 2
		GOSUB Truck_Stage_PerformMission
	ENDIF


	// ...final stage
	IF m_stage = 3
		m_passed = TRUE
	ENDIF


	// ...special 'asset achieved' cutscene
	IF m_stage = 50
		GOSUB Truck_Asset_Acquired
	ENDIF

	IF m_stage = 51
		// ...quit to cleanup without displaying 'failed' text again
		m_quit = TRUE
	ENDIF


	// Continuous update methods and event checking







// End of Main Loop
// ***** DON'T CHANGE *****
end_of_main_loop_Truck:

	IF m_quit = FALSE
		IF m_failed = FALSE
			IF m_passed = FALSE																 
				// Restart main loop
				GOTO mission_loop_Truck 
			ELSE
				// Mission passed
				GOSUB mission_passed_Truck

				// Check if the 'asset acquired' cutscene should be shown
				IF flagShowAssetAcquired = TRUE
					// ...yes
					m_passed = FALSE
					m_stage = 50
					m_goals = 0
					flagShowAssetAcquired = FALSE

					GOTO mission_loop_Truck
				ENDIF

				RETURN
			ENDIF
		ELSE
			// Mission failed
			GOSUB mission_failed_Truck
			RETURN
		ENDIF
	ELSE
		RETURN // quits out - goes to cleanup
	ENDIF

	WRITE_DEBUG SHOULD_NEVER_BE_HERE_IN_END_OF_MAIN_LOOP
RETURN	// Should never reach here




// *************************************************************************************************************
// 												MISSION STAGE GOSUBS   
// *************************************************************************************************************

// ****************************************
// STAGE 1: Wait for player to enter cab

Truck_Stage_EnterCab:

	IF IS_CAR_DEAD carTrailer
	OR IS_CAR_DEAD carCab
		m_failed = TRUE
		nReasonForFailure = TRUCK_FAILED_TOO_MUCH_DAMAGE
		RETURN
	ENDIF


   	// Initialisation for this stage
   	IF m_goals = 0
		// Display the cash
		DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING g_Truck_cashKM COUNTER_DISPLAY_NUMBER 1 TRK_CSH
		g_Truck_cashKM = nInitialCash
		countCash = nInitialCash

		// Display the mission information
		GOSUB Truck_Store_Mission_Type
		GOSUB Truck_Store_Mission_Destination
		GOSUB Truck_Display_Mission_Info

		ADD_BLIP_FOR_CAR carCab blipCab
		SET_BLIP_AS_FRIENDLY blipCab TRUE

		flagArrived = FALSE
		
		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------


	// Subgoals
	// --------

	// Wait for the player to enter the cab
	IF m_goals = 1
		IF IS_CHAR_IN_CAR scplayer carCab
			REMOVE_BLIP blipCab

			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------


	// exit
	IF m_goals = 99
		GOSUB Truck_next_stage
	ENDIF

RETURN


// ****************************************
// STAGE 2: Perform Mission

Truck_Stage_PerformMission:

	// Error Checks for this stage
	IF IS_CAR_DEAD carCab
	OR IS_CAR_DEAD carTrailer
		m_failed = TRUE
		nReasonForFailure = TRUCK_FAILED_TOO_MUCH_DAMAGE
		RETURN
	ENDIF

	IF flagArrived = FALSE
		GOSUB Truck_Check_For_Failure_Conditions
		IF m_failed = TRUE
			RETURN
		ENDIF
	ENDIF

   	// Initialisation for this stage
   	IF m_goals = 0
		// Blip at Destination
		ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination
		CHANGE_BLIP_SCALE blipDestination 4

		// Flag that determines when traffic is allowed again
		flagOutOfDepot = FALSE

		GOSUB Truck_Setup_Mission_Control

		// Display appropriate text
		SWITCH nMissionType

			CASE TRUCK_NORMAL_DAMAGE_MISSION
				PRINT_NOW TRK_H6 15000 1
				BREAK
			CASE TRUCK_NORMAL_TIMED_MISSION
				PRINT_NOW TRK_H7 15000 1  // Deliver the goods as quickly as possible. If you are late you'll lose cash.
				BREAK
			CASE TRUCK_FRAGILE_DAMAGE_MISSION
				PRINT_NOW TRK_H12 15000 1
				BREAK
			CASE TRUCK_ILLEGAL_MISSION
				PRINT_NOW TRK_H9 15000 1
				BREAK
			CASE TRUCK_TIMED_DAMAGE_MISSION
				PRINT_NOW TRK_H11 15000 1
				BREAK
			CASE TRUCK_HIGHLY_ILLEGAL_MISSION
				PRINT_NOW TRK_H10 15000 1
				BREAK
			DEFAULT
				WRITE_DEBUG UNKNOWN_MISSION_TYPE

		ENDSWITCH

		m_goals++
	ENDIF


	// Has the destination been reached?
	IF m_goals = 1
		xloTemp = xposDestination	- 4.0000
		yloTemp = yposDestination	- 4.0000
		zloTemp = zposDestination	- 4.0000
		xhiTemp = xposDestination	+ 4.0000
		yhiTemp = yposDestination	+ 4.0000
		zhiTemp = zposDestination	+ 4.0000

		IF IS_CAR_IN_AREA_3D carTrailer xloTemp yloTemp zloTemp xhiTemp yhiTemp zhiTemp TRUE
		OR IS_CAR_IN_AREA_3D carCab xloTemp yloTemp zloTemp xhiTemp yhiTemp zhiTemp FALSE
			IF IS_CHAR_IN_CAR scplayer carCab
			AND IS_TRAILER_ATTACHED_TO_CAB carTrailer carCab
				SET_PLAYER_CONTROL player1 OFF
				flagArrived = TRUE

				// Boost the mission time
				// NOTE: This gives the player a reasonable chance to get out of the cab without hitting a boundary and losing cash
				timerMissionControl += 15000

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Check for trailer stopped
	IF m_goals = 2
		IF IS_CAR_STOPPED carTrailer
		AND IS_CAR_STOPPED carCab
			// Lock Cab in place to prevent player driving away with it
			FREEZE_CAR_POSITION carCab TRUE

			TASK_LEAVE_CAR scplayer carCab
			GOSUB Truck_Display_Mission_Passed_Text

			m_goals++
		ENDIF
	ENDIF


	// Check for player out of cab
	IF m_goals = 3
		IF NOT IS_CHAR_IN_CAR scplayer carCab
			CLOSE_ALL_CAR_DOORS carCab
			LOCK_CAR_DOORS carCab CARLOCK_LOCKED
			SET_PLAYER_CONTROL player1 ON

			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates for stage
	GOSUB Truck_Regulate_Cab_And_Trailer_Health
	GOSUB Truck_Maintain_Mission_Timer
	GOSUB Truck_Update_Cash

	IF flagOutOfDepot = TRUE
		GOSUB Truck_Maintain_Wanted_Level
	ENDIF

	// Keep the road closed until the the truck is out
	IF flagOutOfDepot = FALSE
		xposTemp = -76.1692
		yposTemp = -1128.9630
		zposTemp = 0.0781
		IF LOCATE_CAR_3D carTrailer xposTemp yposTemp zposTemp 40.0 40.0 40.0 FALSE
		AND NOT LOCATE_CAR_3D carTrailer xposTemp yposTemp zposTemp 20.0 20.0 20.0 FALSE
			// Allow traffic again
			SET_CAR_DENSITY_MULTIPLIER 1.0

			// Set wanted level for illegal goods missions
			IF nMissionType = TRUCK_ILLEGAL_MISSION
				ALTER_WANTED_LEVEL_NO_DROP player1 3
			ENDIF
				
			IF nMissionType = TRUCK_HIGHLY_ILLEGAL_MISSION
				ALTER_WANTED_LEVEL_NO_DROP player1 4
			ENDIF

			flagOutOfDepot = TRUE
		ENDIF
	ENDIF


	// Cash cut messages for timed missions
	IF flagFailureConditionsApply = FALSE
		IF flagDisplayCashCutLate = TRUE
			PRINT_NOW TRK_H14 6000 1
			flagDisplayCashCutLate = FALSE
		ENDIF

		IF flagDisplayCashCutVeryLate = TRUE
			PRINT_NOW TRK_H15 6000 1
			flagDisplayCashCutVeryLate = FALSE
		ENDIF
	ENDIF


	// Deadline message for all missions
	IF flagFailureConditionsApply = FALSE
		IF flagDisplayLastChance = TRUE
			PRINT_NOW TRK_H16 7000 1
			flagDisplayLastChance = FALSE
		ENDIF
	ENDIF


	// exit
	IF m_goals = 99
		GOSUB Truck_next_stage
	ENDIF

RETURN


// ****************************************
// ASSET ACQUIRED

Truck_Asset_Acquired:

   	// Initialisation
	// --------------

   	IF m_goals = 0
		// Allow the 'mission passed' to be displayed for a few seconds
		SET_PLAYER_CONTROL player1 OFF
		timerCutscene = m_mission_timer + 5000

		GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
		GET_CHAR_HEADING scplayer headTemp

		CLEAR_ONSCREEN_COUNTER g_Truck_cashKM

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------


	// Subgoals
	// --------

	// Fade out
	IF m_goals = 1
		IF timerCutscene < m_mission_timer
			DO_FADE 500 FADE_OUT
			timerCutscene = m_mission_timer + 500

			m_goals++
		ENDIF
	ENDIF

	
	// Create the protection pickup and prepare for the cutscene
	IF m_goals = 2
		IF timerCutscene < m_mission_timer
		AND NOT GET_FADING_STATUS
			CREATE_PROTECTION_PICKUP -82.2693 -1135.5262 0.5847 g_Truck_CashPickupValueKM g_Truck_CashPickupValueKM g_Truck_CashPickupKM
			SWITCH_WIDESCREEN ON

			IF IS_CHAR_IN_ANY_CAR scplayer
				WARP_CHAR_FROM_CAR_TO_COORD scplayer -47.0873 -1122.1464 0.4453
			ELSE
				SET_CHAR_COORDINATES scplayer -47.0873 -1122.1464 0.4453
			ENDIF

			MARK_CAR_AS_NO_LONGER_NEEDED carCab
			MARK_CAR_AS_NO_LONGER_NEEDED carTrailer

			SET_EVERYONE_IGNORE_PLAYER player1 TRUE
			SET_ALL_CARS_CAN_BE_DAMAGED FALSE

//			LOAD_SCENE -82.2693 -1135.5262 0.5847
			LOAD_SCENE_IN_DIRECTION -96.3779 -1125.2618 0.0930 261.7115
			SET_FIXED_CAMERA_POSITION -109.2539 -1122.3531 15.1451 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -108.3806 -1122.4388 14.6655 JUMP_CUT
			
			// Leave it faded out for a couple of seconds
			timerCutscene = m_mission_timer + 2000

			m_goals++
		ENDIF
	ENDIF


	// Leave it faded out for a couple of seconds, then fade in
	IF m_goals = 3
		IF timerCutscene < m_mission_timer
			// Fade in
			DO_FADE 500 FADE_IN

			// Display 'asset acquired'
			PRINT_BIG ASS_ACQ 5000 6

			timerCutscene = m_mission_timer + 5000

			m_goals++
		ENDIF
	ENDIF


	// Display the scene for a short while then change camera view
	IF m_goals = 4
		IF timerCutscene < m_mission_timer
			SET_FIXED_CAMERA_POSITION -83.7387 -1133.7926 0.9871 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -82.9525 -1134.4098 0.9592 JUMP_CUT

			// Print potential revenue
			PRINT_WITH_NUMBER_NOW ASS_LUV g_Truck_CashPickupValueKM 6000 1

			timerCutscene = m_mission_timer + 6000

			m_goals++
		ENDIF
	ENDIF

	
	// Display the new scene for a short while
	IF m_goals = 5
		IF timerCutscene < m_mission_timer
			DO_FADE 500 FADE_OUT

			timerCutscene = m_mission_timer + 500

			m_goals++
		ENDIF
	ENDIF


	// Switch back to normal view and display the rest of the text, then fade in again
	IF m_goals = 6
		IF timerCutscene < m_mission_timer
		AND NOT GET_FADING_STATUS
			SET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
			SET_CHAR_HEADING scplayer headTemp
			LOAD_SCENE xposTemp yposTemp zposTemp

			SWITCH_WIDESCREEN OFF
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT

			// Leave it faded out for a couple of seconds
			timerCutscene = m_mission_timer + 2000

			m_goals++
		ENDIF
	ENDIF
			

	// Leave it faded out for a couple of seconds
	IF m_goals = 7
		IF timerCutscene < m_mission_timer
			// Future Missions description
			PRINT_NOW TRK_P2 10000 1

			DO_FADE 500 FADE_IN

			timerCutscene = m_mission_timer + 500

			m_goals++
		ENDIF
	ENDIF


	// End cutscene
	IF m_goals = 8
		IF timerCutscene < m_mission_timer
		AND NOT GET_FADING_STATUS
			SET_EVERYONE_IGNORE_PLAYER player1 FALSE
			SET_ALL_CARS_CAN_BE_DAMAGED TRUE
			SET_PLAYER_CONTROL player1 ON

			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Truck_next_stage
	ENDIF

RETURN





// *************************************************************************************************************
//										MISSION SPECIFIC GOSUBS  
// *************************************************************************************************************


// *************************************************************************************************************
//								         MISSION INFORMATION
// *************************************************************************************************************

// ****************************************
// Store Mission Type
Truck_Store_Mission_Type:

	SWITCH nMissionType
		CASE TRUCK_NORMAL_DAMAGE_MISSION
			$tlMissionType = TRK_MND
			BREAK

		CASE TRUCK_NORMAL_TIMED_MISSION
			$tlMissionType = TRK_MNT
			BREAK

		CASE TRUCK_FRAGILE_DAMAGE_MISSION
			$tlMissionType = TRK_MFD
			BREAK

		CASE TRUCK_ILLEGAL_MISSION
			$tlMissionType = TRK_MI
			BREAK

		CASE TRUCK_TIMED_DAMAGE_MISSION
			$tlMissionType = TRK_MTD
			BREAK

		CASE TRUCK_HIGHLY_ILLEGAL_MISSION
			$tlMissionType = TRK_MHI
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_MISSION_TYPE_ID
	ENDSWITCH

RETURN


// ****************************************
// Store Mission Destination
Truck_Store_Mission_Destination:

	// Country East (near)
	IF nDestinationArea = TRUCK_COUNTRY_EAST_NEAR_DESTINATION
		SWITCH nDestinationLocation
			CASE 0
				$tlMissionDestination = TRK_CE0
				BREAK
			CASE 1
				$tlMissionDestination = TRK_CE1
				BREAK
			CASE 2
				$tlMissionDestination = TRK_CE2
				BREAK
			CASE 3
				$tlMissionDestination = TRK_CE3
				BREAK
		ENDSWITCH
	ENDIF

	// Country East (far)
	IF nDestinationArea = TRUCK_COUNTRY_EAST_FAR_DESTINATION
		SWITCH nDestinationLocation
			CASE 0
				$tlMissionDestination = TRK_CE4
				BREAK
			CASE 1
				$tlMissionDestination = TRK_CE5
				BREAK
		ENDSWITCH
	ENDIF

	// Los Santos
	IF nDestinationArea = TRUCK_LOS_SANTOS_DESTINATION
		SWITCH nDestinationLocation
			CASE 0
				$tlMissionDestination = TRK_LS0
				BREAK
			CASE 1
				$tlMissionDestination = TRK_LS1
				BREAK
			CASE 2
				$tlMissionDestination = TRK_LS2
				BREAK
			CASE 3
				$tlMissionDestination = TRK_LS3
				BREAK
		ENDSWITCH
	ENDIF

	// Las Venturas
	IF nDestinationArea = TRUCK_LAS_VENTURAS_DESTINATION
		SWITCH nDestinationLocation
			CASE 0
				$tlMissionDestination = TRK_V0
				BREAK
			CASE 1
				$tlMissionDestination = TRK_V1
				BREAK
			CASE 2
				$tlMissionDestination = TRK_V2
				BREAK
			CASE 3
				$tlMissionDestination = TRK_V3
				BREAK
			CASE 4
				$tlMissionDestination = TRK_V4
				BREAK
			CASE 5
				$tlMissionDestination = TRK_V5
				BREAK
			CASE 6
				$tlMissionDestination = TRK_V6
				BREAK
			CASE 7
				$tlMissionDestination = TRK_V7
				BREAK
			CASE 8
				$tlMissionDestination = TRK_V8
				BREAK
			CASE 9
				$tlMissionDestination = TRK_V9
				BREAK
			CASE 10
				$tlMissionDestination = TRK_V10
				BREAK
			CASE 11
				$tlMissionDestination = TRK_V11
				BREAK
			CASE 12
				$tlMissionDestination = TRK_V12
				BREAK
			CASE 13
				$tlMissionDestination = TRK_V13
				BREAK
		ENDSWITCH
	ENDIF

	// Country North 2
	IF nDestinationArea = TRUCK_COUNTRY_NORTH_2_DESTINATION
		SWITCH nDestinationLocation
			CASE 0
				$tlMissionDestination = TRK_NC0
				BREAK
			CASE 1
				$tlMissionDestination = TRK_NC1
				BREAK
			CASE 2
				$tlMissionDestination = TRK_NC2
				BREAK
			CASE 3
				$tlMissionDestination = TRK_NC3
				BREAK
			CASE 4
				$tlMissionDestination = TRK_NC4
				BREAK
		ENDSWITCH
	ENDIF

	// Country South
	IF nDestinationArea = TRUCK_COUNTRY_SOUTH_DESTINATION
		SWITCH nDestinationLocation
			CASE 0
				$tlMissionDestination = TRK_CS0
				BREAK
			CASE 1
				$tlMissionDestination = TRK_CS1
				BREAK
			CASE 2
				$tlMissionDestination = TRK_CS2
				BREAK
		ENDSWITCH
	ENDIF

	// San Fierro
	IF nDestinationArea = TRUCK_SAN_FIERRO_DESTINATION
		SWITCH nDestinationLocation
			CASE 0
				$tlMissionDestination = TRK_SF0
				BREAK
			CASE 1
				$tlMissionDestination = TRK_SF1
				BREAK
			CASE 2
				$tlMissionDestination = TRK_SF2
				BREAK
			CASE 3
				$tlMissionDestination = TRK_SF3
				BREAK
		ENDSWITCH
	ENDIF

	// Country North
	IF nDestinationArea = TRUCK_COUNTRY_NORTH_DESTINATION
		SWITCH nDestinationLocation
			CASE 0
				$tlMissionDestination = TRK_CN0
				BREAK
			CASE 1
				$tlMissionDestination = TRK_CN1
				BREAK
			CASE 2
				$tlMissionDestination = TRK_CN2
				BREAK
		ENDSWITCH
	ENDIF

RETURN


// ****************************************
// Display Mission Info
Truck_Display_Mission_Info:

	// Now displays the mission as a PRINT statement
	CLEAR_SMALL_PRINTS
	PRINT_STRING_IN_STRING_NOW $tlMissionType $tlMissionDestination 15000 1

RETURN



// *************************************************************************************************************
//								           STUFF TO UPDATE WHILE PERFORMING MISSION
// *************************************************************************************************************


// ****************************************
// Check For Failure Conditions
Truck_Check_For_Failure_Conditions:

	// Check if failure conditions already apply
	IF flagFailureConditionsApply = TRUE
		// ...failure conditions already apply, so check if they no longer apply, or check for timeout
		IF IS_TRAILER_ATTACHED_TO_CAB carTrailer carCab
		AND IS_CHAR_IN_CAR scplayer carCab
			// ...failure conditions no longer apply
			countFailure = 0
			flagFailureConditionsApply = FALSE
			REMOVE_BLIP blipFailureOutOfCab
			REMOVE_BLIP blipFailureUnattachedTrailer
			ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination
			CHANGE_BLIP_SCALE blipDestination 4

			CLEAR_SMALL_PRINTS
			RETURN
		ENDIF

		// ...if the cab is about to explode then don't display a failure conditions message
		GET_CAR_HEALTH carCab nTempInt
		IF nTempInt < TRUCK_CAR_NOT_ON_FIRE_HEALTH_BOUNDARY
			CLEAR_SMALL_PRINTS
			REMOVE_BLIP blipFailureOutOfCab
			REMOVE_BLIP blipFailureUnattachedTrailer
		ENDIF

		// ...failure conditions still apply, so update the counter
		IF timerFailureConditions < m_mission_timer
			countFailure--
			timerFailureConditions = m_mission_timer + 1000
		ENDIF

		// ...check for timeout
		IF countFailure < 0
			// ...failure
			m_failed = TRUE
			CLEAR_SMALL_PRINTS
			REMOVE_BLIP blipFailureOutOfCab
			REMOVE_BLIP blipFailureUnattachedTrailer

			flagTempFlag = FALSE
			IF NOT IS_CHAR_IN_CAR scplayer carCab
				flagTempFlag = TRUE
				nReasonForFailure = TRUCK_FAILED_NOT_IN_CAB
			ENDIF

			IF NOT IS_TRAILER_ATTACHED_TO_CAB carTrailer carCab
			AND flagTempFlag = FALSE
				nReasonForFailure = TRUCK_FAILED_TRAILER_NOT_ATTACHED
			ENDIF

			RETURN
		ENDIF

		// Update the message and the blips
		CLEAR_PRINTS
		ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS FALSE
		IF NOT IS_CHAR_IN_CAR scplayer carCab
			// ...get back in the cab
			IF countFailure = 1
				PRINT_WITH_NUMBER TRK_H5b countFailure 1000 1
			ELSE
				PRINT_WITH_NUMBER TRK_H5 countFailure 1000 1
			ENDIF

			IF DOES_BLIP_EXIST blipFailureUnattachedTrailer
				REMOVE_BLIP blipFailureUnattachedTrailer
			ENDIF

			IF NOT DOES_BLIP_EXIST blipFailureOutOfCab
				ADD_BLIP_FOR_CAR carCab blipFailureOutOfCab
				SET_BLIP_AS_FRIENDLY blipFailureOutOfCab TRUE
			ENDIF
		ELSE
			// ...reattach trailer
			IF countFailure = 1
				PRINT_WITH_NUMBER TRK_H4b countFailure 1000 1
			ELSE
				PRINT_WITH_NUMBER TRK_H4 countFailure 1000 1
			ENDIF

			IF NOT DOES_BLIP_EXIST blipFailureUnattachedTrailer
				ADD_BLIP_FOR_CAR carTrailer blipFailureUnattachedTrailer
				SET_BLIP_AS_FRIENDLY blipFailureUnattachedTrailer TRUE
			ENDIF

			IF DOES_BLIP_EXIST blipFailureOutOfCab
				REMOVE_BLIP blipFailureOutOfCab
			ENDIF
		ENDIF
	ELSE
		// ...failure conditions don't apply, so check if they do now
		IF NOT IS_TRAILER_ATTACHED_TO_CAB carTrailer carCab
		OR NOT IS_CHAR_IN_CAR scplayer carCab
			// ...failure conditions now apply
			// Give the player a minute to rectify the situation
			countFailure = 60
			timerFailureConditions = m_mission_timer + 1000
			flagFailureConditionsApply = TRUE

			// Remove destination blip
			REMOVE_BLIP blipDestination

			// Display the message
			ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS FALSE
			IF NOT IS_CHAR_IN_CAR scplayer carCab
				// ...get back in the cab
				PRINT_WITH_NUMBER_NOW TRK_H5 countFailure 1000 1
			ELSE
				// ...reattach trailer
				PRINT_WITH_NUMBER_NOW TRK_H4 countFailure 1000 1
			ENDIF
		ENDIF
	ENDIF

RETURN


// ****************************************
// Setup Mission Control
Truck_Setup_Mission_Control:

	// Health issues
	nInitialHealth = 750	// out of 750 because the cab catches fire at 250

	IF nMissionType = TRUCK_NORMAL_DAMAGE_MISSION
	OR nMissionType = TRUCK_TIMED_DAMAGE_MISSION
		nInitialHealth = 500
	ENDIF

	IF nMissionType = TRUCK_FRAGILE_DAMAGE_MISSION
		nInitialHealth = 250
	ENDIF

	nTempInt = nInitialHealth + TRUCK_CAR_NOT_ON_FIRE_HEALTH_BOUNDARY
	SET_CAR_HEALTH carTrailer	nTempInt
	SET_CAR_HEALTH carCab		nTempInt


	// Calculate the initial mission time
	nMissionTimeStage = TRUCK_MISSION_TIME_NOT_CALCULATED
	GOSUB Truck_Calculate_Mission_Time


	// Displays
	IF nMissionType = TRUCK_NORMAL_DAMAGE_MISSION
	OR nMissionType = TRUCK_FRAGILE_DAMAGE_MISSION
		DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING g_Truck_damageKM COUNTER_DISPLAY_BAR 2 TRK_DMG
	ENDIF

	IF nMissionType = TRUCK_NORMAL_TIMED_MISSION
		GOSUB Truck_Calculate_Delivery_Time
		DISPLAY_ONSCREEN_TIMER_WITH_STRING g_Truck_timedKM TIMER_UP TRK_TIM
		FREEZE_ONSCREEN_TIMER TRUE
	ENDIF

	IF nMissionType = TRUCK_TIMED_DAMAGE_MISSION
		GOSUB Truck_Calculate_Delivery_Time
		DISPLAY_ONSCREEN_TIMER_WITH_STRING g_Truck_timedKM TIMER_UP TRK_TIM
		DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING g_Truck_damageKM COUNTER_DISPLAY_BAR 2 TRK_DMG
		FREEZE_ONSCREEN_TIMER TRUE
	ENDIF

	// Timed mission flags
	flagDisplayCashCutLate = FALSE
	flagDisplayCashCutVeryLate = FALSE

RETURN


// ****************************************
// Calculate Mission Time
Truck_Calculate_Mission_Time:

	// Calculate the next mission time boundary
	SWITCH nMissionTimeStage
		CASE TRUCK_MISSION_TIME_NOT_CALCULATED
			// ...initialisation
			timerMissionControl = nDestinationDistance * TRUCK_DISTANCE_TO_TIME_CALC_NOT_LATE
			timerMissionControl += m_mission_timer
			nMissionTimeStage = TRUCK_MISSION_TIME_NOT_LATE
			BREAK

		CASE TRUCK_MISSION_TIME_NOT_LATE
			// ...stage1 (for timed missions, equivalent of 'LATE DELIVERY')
			timerMissionControl = nDestinationDistance * TRUCK_DISTANCE_TO_TIME_CALC_LATE
			timerMissionControl += m_mission_timer
			nMissionTimeStage = TRUCK_MISSION_TIME_LATE
			BREAK

		CASE TRUCK_MISSION_TIME_LATE
			// ...stage2 (for timed missions, equivalent of 'VERY LATE DELIVERY')
			timerMissionControl = nDestinationDistance * TRUCK_DISTANCE_TO_TIME_CALC_LATE
			timerMissionControl += m_mission_timer
			nMissionTimeStage = TRUCK_MISSION_TIME_VERY_LATE
			BREAK

		CASE TRUCK_MISSION_TIME_VERY_LATE
			// ...Last Chance: Deliver within a certain time or mission failed
			GOSUB Truck_Calculate_Last_Chance
			nMissionTimeStage = TRUCK_MISSION_TIME_LAST_CHANCE
			BREAK

		CASE TRUCK_MISSION_TIME_LAST_CHANCE
			// ...time up
			nMissionTimeStage = TRUCK_MISSION_TIME_ELAPSED
			BREAK
	ENDSWITCH

RETURN


// ****************************************
// Calculate Last Chance
Truck_Calculate_Last_Chance:

	// Calculate the Last Chance 'deliver by' time and display any messaging
	nTempInt = TRUCK_LAST_CHANCE_hours * 60
	nTempInt += TRUCK_LAST_CHANCE_mins
	nTempInt *= 1000
	timerMissionControl = m_mission_timer + nTempInt

	GET_TIME_OF_DAY nTempInt nTempInt2

	// Update minutes
	nTempInt2 += TRUCK_LAST_CHANCE_mins
	IF nTempInt2 >= 60
		nTempInt2 -= 60
		nTempInt++
	ENDIF

	// Update Hours
	nTempInt += TRUCK_LAST_CHANCE_hours
	IF nTempInt >= 24
		nTempInt -= 24
	ENDIF

	// Convert the Hours and Minutes into a msec equivalent for display
	nTempInt3 = nTempInt * 60
	nTempInt3 += nTempInt2
	nTempInt3 *= 1000
	g_Truck_timedKM = nTempInt3

	// Display the 'deadline' time
	DISPLAY_ONSCREEN_TIMER_WITH_STRING g_Truck_timedKM TIMER_UP TRK_DLN
	FREEZE_ONSCREEN_TIMER TRUE

	// Set a flag to display an appropriate message when possible
	flagDisplayLastChance = TRUE

RETURN


// ****************************************
// Calculate Delivery Time
Truck_Calculate_Delivery_Time:

	// Used by the timed missions to calculate a delivery-by time in hours/minutes
	// ...calculate the 'not late' delivery time in msec
	nTempInt = nDestinationDistance * TRUCK_DISTANCE_TO_TIME_CALC_NOT_LATE

	// ...convert it to seconds
	nTempInt /= 1000

	// ...get the number of minutes (note: this will ignore the remainder)
	nTempInt2 = nTempInt / 60

	// ...work out how many seconds these minutes are the equivalent of
	nTempInt3 = nTempInt2 * 60

	// ...get the seconds
	nTempInt -= nTempInt3

	// NOTE: nTempInt2 contains the minutes and nTempInt contains the seconds
	//		 These are the equivalent of hours and minutes for the time of day

	GET_TIME_OF_DAY nHours nMinutes

	// Update the minutes
	nMinutes += nTempInt
	IF nMinutes >= 60
		nHours++
		nMinutes -= 60
	ENDIF

	// Update the hours
	nHours += nTempInt2
	WHILE nHours >= 24
		nHours -= 24
	ENDWHILE

	// Round up the minutes to be a decent time (ie: on the hour, half hour, or quarter hour)
	flagTempFlag = FALSE
	IF nMinutes > 45
		// ...round up to the next hour
		nTempInt = 60 - nMinutes
		nMinutes = 0
		nHours++
		IF nHours = 24
			nHours = 0
		ENDIF

		// ...increase the mission time accordingly
		nTempInt *= 1000
		timerMissionControl += nTempInt
		flagTempFlag = TRUE
	ENDIF

	IF flagTempFlag = FALSE
		IF nMinutes > 30
		AND nMinutes < 45
			// ...round up to quarter to the hour
			nTempInt = 45 - nMinutes
			nMinutes = 45

			// ...increase the mission time accordingly
			nTempInt *= 1000
			timerMissionControl += nTempInt
			flagTempFlag = TRUE
		ENDIF
	ENDIF

	IF flagTempFlag = FALSE
		IF nMinutes > 15
		AND nMinutes < 30
			// ...round up to the half hour
			nTempInt = 30 - nMinutes
			nMinutes = 30

			// ...increase the mission time accordingly
			nTempInt *= 1000
			timerMissionControl += nTempInt
			flagTempFlag = TRUE
		ENDIF
	ENDIF

	IF flagTempFlag = FALSE
		IF nMinutes > 0
		AND nMinutes < 15
			// ...round up to quarter past the hour
			nTempInt = 15 - nMinutes
			nMinutes = 15

			// ...increase the mission time accordingly
			nTempInt *= 1000
			timerMissionControl += nTempInt
			flagTempFlag = TRUE
		ENDIF
	ENDIF

	// Convert the Hours and Minutes into a msec equivalent for display
	nTempInt = nHours * 60
	nTempInt += nMinutes
	nTempInt *= 1000
	g_Truck_timedKM = nTempInt

RETURN



// ****************************************
// Regulate the cab and trailer health
Truck_Regulate_Cab_And_Trailer_Health:

	// Set both cab and trailer health to the lowest health
	GET_CAR_HEALTH carTrailer nTempInt
	GET_CAR_HEALTH carCab nTempInt2

	IF nTempInt > nTempInt2
		nTempInt = nTempInt2
	ENDIF

	IF nTempInt > 1
		SET_CAR_HEALTH carCab nTempInt
		SET_CAR_HEALTH carTrailer nTempInt
	ENDIF

	IF flagDamageMission = FALSE
		RETURN
	ENDIF

	// Update the display damage bar 
	nTempInt = nTempInt - TRUCK_CAR_NOT_ON_FIRE_HEALTH_BOUNDARY
	nTempInt = nTempInt * 100
	nTempInt = nTempInt / nInitialHealth
	IF nTempInt < 0
		nTempInt = 0
	ENDIF

	g_Truck_damageKM = nTempInt

RETURN


// ****************************************
// Maintain Mission Timer
Truck_Maintain_Mission_Timer:

// The time no longer has no penalty and penalty elements
	IF timerMissionControl < m_mission_timer
		// ...timer has elapsed, so recalculate timer to give a bit more time
		GOSUB Truck_Calculate_Mission_Time
		IF nMissionTimeStage = TRUCK_MISSION_TIME_ELAPSED
			// ...out of time
			m_failed = TRUE
			timerMissionControl = 0
			countCash = 0
			nReasonForFailure = TRUCK_FAILED_OUT_OF_TIME
			RETURN
		ENDIF

		// For timed missions, a new timer stage will result in a drop in cash
		IF flagTimedMission = TRUE
			IF nMissionTimeStage = TRUCK_MISSION_TIME_LATE
			OR nMissionTimeStage = TRUCK_MISSION_TIME_VERY_LATE
			OR nMissionTimeStage = TRUCK_MISSION_TIME_LAST_CHANCE
				nInitialCash = nInitialCash / 2

				// Flash the cash by removing the counter and adding it again
				CLEAR_ONSCREEN_COUNTER g_Truck_cashKM
				DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING g_Truck_cashKM COUNTER_DISPLAY_NUMBER 1 TRK_CSH

				// Remove the 'deliver by' time
				IF nMissionTimeStage = TRUCK_MISSION_TIME_LATE
					CLEAR_ONSCREEN_TIMER g_Truck_timedKM
				ENDIF

				// Display a 'cash cut' message
				IF nMissionTimeStage = TRUCK_MISSION_TIME_LATE
					flagDisplayCashCutLate = TRUE
				ENDIF

				IF nMissionTimeStage = TRUCK_MISSION_TIME_VERY_LATE
					flagDisplayCashCutVeryLate = TRUE
				ENDIF
			ENDIF
		ENDIF
	ENDIF

RETURN


// ****************************************
// Update Cash
Truck_Update_Cash:

	// Re-calculate the cash for the mission
	// NOTE: nInitialCash gets modified when a timed mission boundary is reached, so start with that as the base value
	countCash = nInitialCash

	// Damage missions reduce this amount based on the damage taken
	IF flagDamageMission = TRUE
		IF IS_CAR_DEAD carCab
			countCash = 0
		ELSE
			// ...get current health
			GET_CAR_HEALTH carCab nTempInt

			// Check for being on fire
			IF nTempInt < TRUCK_CAR_NOT_ON_FIRE_HEALTH_BOUNDARY
				countCash = 0
			ELSE
				// ...not on fire, so reduce it by the 'on fire' health value
				nTempInt -= TRUCK_CAR_NOT_ON_FIRE_HEALTH_BOUNDARY

				// ...calculate the percentage health remaining from the initial value
				nTempInt2 = nTempInt * 100
				nTempInt2 /= nInitialHealth

				// ...the current cash becomes this percentage of the base value
				countCash *= nTempInt2
				countCash /= 100
			ENDIF
		ENDIF
	ENDIF

	// Police missions reduce this amount based on the damage taken, but damage plays a lesser part than in damage missions
	// NOTE: ***7/9/04 *** Police missions no longer reduce cash for damages
	IF flagPoliceMission = TRUE
		IF IS_CAR_DEAD carCab
			countCash = 0
/*
		ELSE
			// ...get current health
			GET_CAR_HEALTH carCab nTempInt

			// ...reduce it by the 'on fire' health value
			IF nTempInt < TRUCK_CAR_NOT_ON_FIRE_HEALTH_BOUNDARY
				countCash = 0
			ELSE
				nTempInt -= TRUCK_CAR_NOT_ON_FIRE_HEALTH_BOUNDARY

				// ...calculate the percentage health remaining from the initial value
				nTempInt2 = nTempInt * 100
				nTempInt2 /= nInitialHealth

				// Increase this percentage
				nTempInt3 = 100 - nTempInt2
				nTempInt3 /= 2
				nTempInt2 += nTempInt3

				// ...the current cash becomes this percentage of the base value
				countCash *= nTempInt2
				countCash /= 100
			ENDIF
*/
		ENDIF
	ENDIF

	// Update the cash display value
	g_Truck_cashKM = countCash

RETURN


// ****************************************
// Maintain Wanted Level
Truck_Maintain_Wanted_Level:

	IF flagPoliceMission = FALSE
		RETURN
	ENDIF

	IF nMissionType = TRUCK_ILLEGAL_MISSION
		IF NOT IS_WANTED_LEVEL_GREATER player1 2
			ALTER_WANTED_LEVEL_NO_DROP player1 3
		ENDIF

		RETURN
	ENDIF

	IF nMissionType = TRUCK_HIGHLY_ILLEGAL_MISSION
		IF NOT IS_WANTED_LEVEL_GREATER player1 3
			ALTER_WANTED_LEVEL_NO_DROP player1 4
		ENDIF

		RETURN
	ENDIF

RETURN




// *************************************************************************************************************
//								        MISSION INITIALISATION
// *************************************************************************************************************

// ***********************************
// Initialise the available missions
Truck_Initialise_Available_Missions:

	nDestinationArea = -1
	nDestinationLocation = -1
	nMissionType = 0

	GOSUB Truck_Initialise_Cabs_And_Trailers
	GOSUB Truck_Initialise_Mission_Destination
	GOSUB Truck_Initialise_Destination_Coords
	GOSUB Truck_Initialise_Mission_Type
	GOSUB Truck_Initialise_Distance
	GOSUB Truck_Initialise_Cash

RETURN


// ***********************************
// Create the available cabs and trucks
Truck_Initialise_Cabs_And_Trailers:

	// Cab
	xposTemp = -76.1692
	yposTemp = -1128.9630
	zposTemp = 0.0781
	headTemp = 69.9957

	CLEAR_AREA xposTemp yposTemp zposTemp 30.0 FALSE

	SWITCH nThisCab
		CASE TRUCK_CAB_PETRO
			CREATE_CAR PETRO xposTemp yposTemp zposTemp carCab
			BREAK

		CASE TRUCK_CAB_LINERUN
			CREATE_CAR LINERUN xposTemp yposTemp zposTemp carCab
			BREAK

		CASE TRUCK_CAB_RDTRAIN
			CREATE_CAR RDTRAIN xposTemp yposTemp zposTemp carCab
			BREAK

		DEFAULT
			WRITE_DEBUG Unknown_Cab
	ENDSWITCH
	
	SET_CAR_HEADING carCab headTemp


	// Trailer
	xposTemp = -60.6033
	yposTemp = -1136.8992
	zposTemp = 0.0781

	CLEAR_AREA xposTemp yposTemp zposTemp 50.0 FALSE

	SWITCH nThisTrailer
		CASE TRUCK_TRAILER_PETROTR
			CREATE_CAR PETROTR xposTemp yposTemp zposTemp carTrailer
			BREAK

		CASE TRUCK_TRAILER_ARTICT1
			CREATE_CAR ARTICT1 xposTemp yposTemp zposTemp carTrailer
			BREAK

		CASE TRUCK_TRAILER_ARTICT2
			CREATE_CAR ARTICT2 xposTemp yposTemp zposTemp carTrailer
			BREAK

		CASE TRUCK_TRAILER_ARTICT3
			CREATE_CAR ARTICT3 xposTemp yposTemp zposTemp carTrailer
			BREAK

		DEFAULT
			WRITE_DEBUG Unknown_Trailer
	ENDSWITCH

	ATTACH_TRAILER_TO_CAB carTrailer carCab

RETURN


// ***********************************
// Initialise the mission Destination
Truck_Initialise_Mission_Destination:

	// DESTINATION AREAS
	//	Country East (near)
	//	Country East (far)
	//	Los Santos
	//	Country South
	//	San Fierro
	//	Country North
	//	Country North 2
	//	Las Venturas


	// *** IMPORTANT ***
	// If the destination area order changes then this will affect the printing of the 'need to open SF to proceed' message at the start


	// MISSION DESTINATION IS PROGRESSIVE AND BASED ON THE NUMBER OF MISSIONS PASSED
	SWITCH g_nTruckMissionsPassed
		CASE 0
			// Only Country East (near)
			nDestinationArea = TRUCK_COUNTRY_EAST_NEAR_DESTINATION
			BREAK
		CASE 1
			// Only Country East (far)
			nDestinationArea = TRUCK_COUNTRY_EAST_FAR_DESTINATION
			BREAK
		CASE 2
			// Only Los Santos
			nDestinationArea = TRUCK_LOS_SANTOS_DESTINATION
			BREAK
		CASE 3
			// Only Country South
			nDestinationArea = TRUCK_COUNTRY_SOUTH_DESTINATION
			BREAK
		CASE 4
			// Only San Fierro
			nDestinationArea = TRUCK_SAN_FIERRO_DESTINATION
			BREAK
		CASE 5
			// Country South or San Fierro
			nTempInt2 = TRUCK_SAN_FIERRO_DESTINATION + 1
			GENERATE_RANDOM_INT_IN_RANGE TRUCK_COUNTRY_SOUTH_DESTINATION nTempInt2 nDestinationArea
			BREAK
		CASE 6
			// Country North or Country North2
			nTempInt2 = TRUCK_COUNTRY_NORTH_2_DESTINATION + 1
			GENERATE_RANDOM_INT_IN_RANGE TRUCK_COUNTRY_NORTH_DESTINATION nTempInt2 nDestinationArea
			BREAK
		CASE 7
			// Only Las Venturas
			nDestinationArea = TRUCK_LAS_VENTURAS_DESTINATION
			BREAK
		DEFAULT
			// Anywhere except Country East (near)
			nTempInt2 = TRUCK_HIGHEST_DESTINATION + 1
			GENERATE_RANDOM_INT_IN_RANGE TRUCK_COUNTRY_EAST_FAR_DESTINATION nTempInt2 nDestinationArea
			BREAK
	ENDSWITCH


	// Choose Destination Location
	SWITCH nDestinationArea
		// Country East (near)
		CASE TRUCK_COUNTRY_EAST_NEAR_DESTINATION
			GOSUB Truck_Choose_Mission_Location_CountryEastNear
			BREAK

		// Country East (far)
		CASE TRUCK_COUNTRY_EAST_FAR_DESTINATION
			GOSUB Truck_Choose_Mission_Location_CountryEastFar
			BREAK

		// Los Santos
		CASE TRUCK_LOS_SANTOS_DESTINATION
			GOSUB Truck_Choose_Mission_Location_LosSantos
			BREAK

		// Las Venturas
		CASE TRUCK_LAS_VENTURAS_DESTINATION
			GOSUB Truck_Choose_Mission_Location_LasVenturas
			BREAK

		// Country North 2
		CASE TRUCK_COUNTRY_NORTH_2_DESTINATION
			GOSUB Truck_Choose_Mission_Location_CountryNorth2
			BREAK

		// Country South
		CASE TRUCK_COUNTRY_SOUTH_DESTINATION
			GOSUB Truck_Choose_Mission_Location_CountrySouth
			BREAK

		// San Fierro
		CASE TRUCK_SAN_FIERRO_DESTINATION
			GOSUB Truck_Choose_Mission_Location_SanFierro
			BREAK

		// Country North
		CASE TRUCK_COUNTRY_NORTH_DESTINATION
			GOSUB Truck_Choose_Mission_Location_CountryNorth
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_DESTINATION_AREA

	ENDSWITCH

RETURN


// ***********************************
// Initialise the mission Destination
Truck_Choose_Mission_Location_CountryEastNear:

	// COUNTRY EAST DESTINATIONS
	//	0: FleischBerg Brewery in Red County
	//	1: Warehouse in Blueberry
	//	2: Finalbuild Construction in Bluebery Acres
	//	3: Warehouse in Dillimore

	GENERATE_RANDOM_INT_IN_RANGE 0 4 nDestinationLocation

RETURN


// ***********************************
// Initialise the mission Destination
Truck_Choose_Mission_Location_CountryEastFar:

	// COUNTRY EAST DESTINATIONS
	//	0: Factory in Montgomery
	//	1: BioEngineering in Montgomery

	GENERATE_RANDOM_INT_IN_RANGE 0 2 nDestinationLocation

RETURN


// ***********************************
// Initialise the mission Destination
Truck_Choose_Mission_Location_LasVenturas:

	// LAS VENTURAS DESTINATIONS
	//	0: Warehouse in Redsands West
	//	1: Plastic Chips Factory
	//	2: Abattoir in Whitewood Estates
	//	3: Las Venturas Airport Depot
	//	4: Starfish Casino Building Site
	//	5: Shopping Mall in Creek
	//	6: Warehouses in Spinybed
	//	7: Chinese Shopping Mall in Pilgrim
	//	8: Warehouses in Rockshore East
	//	9: Building Site in Rockshore East
	// 10: Warehouses at Randolph Industrial Estate
	// 11: Warehouses at LVA Freight depot
	// 12: Las Venturas Airport
	// 13: Warehouses in Redsands West

	GENERATE_RANDOM_INT_IN_RANGE 0 14 nDestinationLocation

RETURN


// ***********************************
// Initialise the mission Destination
Truck_Choose_Mission_Location_LosSantos:

	// LOS SANTOS DESTINATIONS
	//	0: Chemical Plant in Ocean Docks
	//	1: Ship in Ocean Docks
	//	2: Industrial area in Willowfield
	//	3: Airport in El Corona

	GENERATE_RANDOM_INT_IN_RANGE 0 4 nDestinationLocation

RETURN


// ***********************************
// Initialise the mission Destination
Truck_Choose_Mission_Location_SanFierro:

	// SAN FIERRO DESTINATIONS
	//	0: SF Airport Terminal at Easter Bay Airport
	//	1: Bridge under construction at Battery Point
	//	2: Petrol Station at Easter Basin
	//	3: Easter Basin Docks

	GENERATE_RANDOM_INT_IN_RANGE 0 4 nDestinationLocation

RETURN


// ***********************************
// Initialise the mission Destination
Truck_Choose_Mission_Location_CountrySouth:

	// COUNTRY SOUTH DESTINATIONS
	//	0: Junkyard in Whetstone
	//	1: Woodmill in Angel Pine
	//	2: Truckstop in Whetstone

	GENERATE_RANDOM_INT_IN_RANGE 0 3 nDestinationLocation

RETURN


// ***********************************
// Initialise the mission Destination
Truck_Choose_Mission_Location_CountryNorth:

	// COUNTRY NORTH DESTINATIONS
	//	0: Truckstop in El Quebrados
	//	1: Art-deco cafe in Bayside Marina
	//	2: Fuelstop in Tierra Robada

	GENERATE_RANDOM_INT_IN_RANGE 0 3 nDestinationLocation

RETURN


// ***********************************
// Initialise the mission Destination
Truck_Choose_Mission_Location_CountryNorth2:

	// COUNTRY NORTH2 DESTINATIONS
	//	0: Refinery in Green Palms
	//	1: Truckstop in Bone County
	//	2: Factory in Bone County
	//	3: The Sherman Dam
	//	4: Desert Airfield in Verdant Meadows

	GENERATE_RANDOM_INT_IN_RANGE 0 5 nDestinationLocation

RETURN


// ***********************************
// Initialise the mission type
Truck_Initialise_Mission_Type:

	SWITCH g_nTruckMissionsPassed
		CASE 0
		CASE 3
			// Normal Timed mission
			nMissionType = TRUCK_NORMAL_TIMED_MISSION
			BREAK
		CASE 1
			// Normal Damage mission
			nMissionType = TRUCK_NORMAL_DAMAGE_MISSION
			BREAK
		CASE 4
			// Fragile Damage mission
			nMissionType = TRUCK_FRAGILE_DAMAGE_MISSION
			BREAK
		CASE 2
		CASE 5
			// 3* police mission
			nMissionType = TRUCK_ILLEGAL_MISSION
			BREAK
		CASE 6
			// Timed Damage mission
			nMissionType = TRUCK_TIMED_DAMAGE_MISSION
			BREAK
		CASE 7
			// 4* police mission
			nMissionType = TRUCK_HIGHLY_ILLEGAL_MISSION
			BREAK
		DEFAULT
			// Random mission
			GENERATE_RANDOM_INT_IN_RANGE 0 TRUCK_HIGHEST_MISSION nMissionType
			// ...add 1 because mission numbers run from 1 upwards
			nMissionType = nMissionType + 1
			BREAK
	ENDSWITCH

	// Set the mission flags
	IF nMissionType = TRUCK_NORMAL_DAMAGE_MISSION
	OR nMissionType = TRUCK_FRAGILE_DAMAGE_MISSION
	OR nMissionType = TRUCK_TIMED_DAMAGE_MISSION
		flagDamageMission = TRUE
	ENDIF

	IF nMissionType = TRUCK_NORMAL_TIMED_MISSION
	OR nMissionType = TRUCK_TIMED_DAMAGE_MISSION
		flagTimedMission = TRUE
	ENDIF

	IF nMissionType = TRUCK_ILLEGAL_MISSION
	OR nMissionType = TRUCK_HIGHLY_ILLEGAL_MISSION
		flagPoliceMission = TRUE
	ENDIF

RETURN


// ***********************************
// Initialise the destination distance
Truck_Initialise_Distance:

	// Find the distance between the destination and the trucking depot
	xloTemp = xposDestination - truckX
	yloTemp = yposDestination - truckY

	xhiTemp = xloTemp * xloTemp
	yhiTemp = yloTemp * yloTemp

	fTempFloat = xhiTemp + yhiTemp
	SQRT fTempFloat fTempFloat2

	// Convert the distance to an int
	nDestinationDistance =# fTempFloat2

	nLoop++

RETURN


// ***********************************
// Initialise the cash for the mission
Truck_Initialise_Cash:

	// The basic cash value based on distance
	SWITCH nDestinationArea
		CASE TRUCK_COUNTRY_EAST_NEAR_DESTINATION
		CASE TRUCK_COUNTRY_EAST_FAR_DESTINATION
		CASE TRUCK_LOS_SANTOS_DESTINATION
			nInitialCash = 1000
			BREAK

		CASE TRUCK_COUNTRY_SOUTH_DESTINATION
		CASE TRUCK_SAN_FIERRO_DESTINATION
			nInitialCash = 3000
			BREAK

		CASE TRUCK_COUNTRY_NORTH_DESTINATION
		CASE TRUCK_COUNTRY_NORTH_2_DESTINATION
		CASE TRUCK_LAS_VENTURAS_DESTINATION
			nInitialCash = 4000
			BREAK

		DEFAULT
			WRITE_DEBUG INITIALISE_CASH_UNKNOWN_DESTINATION
	ENDSWITCH

	// Now add some more cash based on the mission type
	SWITCH nMissionType
		CASE TRUCK_NORMAL_TIMED_MISSION
			// No additional cash
			BREAK

		CASE TRUCK_NORMAL_DAMAGE_MISSION
			nInitialCash = nInitialCash + 500
			BREAK

		CASE TRUCK_FRAGILE_DAMAGE_MISSION
		CASE TRUCK_ILLEGAL_MISSION
			nInitialCash = nInitialCash + 1000
			BREAK

		CASE TRUCK_TIMED_DAMAGE_MISSION
			nInitialCash = nInitialCash + 3000
			BREAK

		CASE TRUCK_HIGHLY_ILLEGAL_MISSION
			nInitialCash = nInitialCash + 6000
			BREAK

		DEFAULT
			WRITE_DEBUG INITIALISE_CASH_UNKNOWN_MISSION_TYPE
	ENDSWITCH

	// The only flaw is that mission 4 and 5 have the same cash, so cheat by adding on an extra 1000
	IF g_nTruckMissionsPassed = 5
		nInitialCash += 1000
	ENDIF

RETURN


// ***********************************
// Get the mission destination coordinates
Truck_Initialise_Destination_Coords:

	// Find Destination Location
	SWITCH nDestinationArea
		// Country East (near)
		CASE TRUCK_COUNTRY_EAST_NEAR_DESTINATION
			GOSUB Truck_Get_Mission_Coordinates_CountryEastNear
			BREAK

		// Country East (far)
		CASE TRUCK_COUNTRY_EAST_FAR_DESTINATION
			GOSUB Truck_Get_Mission_Coordinates_CountryEastFar
			BREAK

		// Los Santos
		CASE TRUCK_LOS_SANTOS_DESTINATION
			GOSUB Truck_Get_Mission_Coordinates_LosSantos
			BREAK

		// Las Venturas
		CASE TRUCK_LAS_VENTURAS_DESTINATION
			GOSUB Truck_Get_Mission_Coordinates_LasVenturas
			BREAK

		// Country North 2
		CASE TRUCK_COUNTRY_NORTH_2_DESTINATION
			GOSUB Truck_Get_Mission_Coordinates_CountryNorth2
			BREAK

		// Country South
		CASE TRUCK_COUNTRY_SOUTH_DESTINATION
			GOSUB Truck_Get_Mission_Coordinates_CountrySouth
			BREAK

		// San Fierro
		CASE TRUCK_SAN_FIERRO_DESTINATION
			GOSUB Truck_Get_Mission_Coordinates_SanFierro
			BREAK

		// Country North
		CASE TRUCK_COUNTRY_NORTH_DESTINATION
			GOSUB Truck_Get_Mission_Coordinates_CountryNorth
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_DESTINATION_AREA

	ENDSWITCH

RETURN


// ***********************************
// Get the mission destination coordinates in Country East (near)
Truck_Get_Mission_Coordinates_CountryEastNear:

	// COUNTRY EAST DESTINATIONS
	//	0: FleischBerg Brewery in Red County
	//	1: Warehouse in Blueberry
	//	2: Finalbuild Construction in Bluebery Acres
	//	3: Warehouse in Dillimore

	SWITCH nDestinationLocation
		// FleishBerg Brewery in Red County
		CASE 0
			xposDestination = -187.4041
			yposDestination = -277.0196
			zposDestination = 0.4219
			BREAK

		// Warehouse in Blueberry
		CASE 1
			xposDestination = 58.0364
			yposDestination = -256.7285
			zposDestination = 0.5781
			BREAK

		// Finalbuild Construction in Bluebery Acres
		CASE 2
			xposDestination = 95.8675
			yposDestination = -154.3627
			zposDestination = 1.5751
			BREAK

		// Warehouse in Dillimore
		CASE 3
			xposDestination = 809.7556
			yposDestination = -598.0007
			zposDestination = 15.1875
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_COUNTRYEASTNEAR_LOCATION

	ENDSWITCH

RETURN


// ***********************************
// Get the mission destination coordinates in Country East (far)
Truck_Get_Mission_Coordinates_CountryEastFar:

	// COUNTRY EAST DESTINATIONS
	//	0: Factory in Montgomery
	//	1: BioEngineering in Montgomery

	SWITCH nDestinationLocation
		// Factory in Montgomery
		CASE 0
			xposDestination = 1403.8329
			yposDestination = 399.4294
			zposDestination = 18.7500
			BREAK

		// BioEngineering in Montgomery
		CASE 1
			xposDestination = 1338.2888
			yposDestination = 348.9004
			zposDestination = 18.4062
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_COUNTRYEASTFAR_LOCATION

	ENDSWITCH

RETURN


// ***********************************
// Get the mission destination coordinates in Las Venturas
Truck_Get_Mission_Coordinates_LasVenturas:

	// LAS VENTURAS DESTINATIONS
	//	0: Warehouse in Redsands West
	//	1: Plastic Chips Factory
	//	2: Abattoir in Whitewood Estates
	//	3: Las Venturas Airport Depot
	//	4: Starfish Casino Building Site
	//	5: Shopping Mall in Creek
	//	6: Warehouses in Spinybed
	//	7: Chinese Shopping Mall in Pilgrim
	//	8: Warehouses in Rockshore East
	//	9: Building Site in Rockshore East
	// 10: Warehouses at Randolph Industrial Estate
	// 11: Warehouses at LVA Freight depot
	// 12: Las Venturas Airport
	// 13: Warehouses in Redsands West

	SWITCH nDestinationLocation
		// Warehouse in Redsands West
		CASE 0
			xposDestination = 1449.7152
			yposDestination = 2358.8516
			zposDestination = 9.8203
			BREAK

		// Plastic Chips Company
		CASE 1
			xposDestination = 1037.4753
			yposDestination = 2131.3445
			zposDestination = 9.8203
			BREAK

		// Abattoir
		CASE 2
			xposDestination = 987.9741
			yposDestination = 2080.3894
			zposDestination = 9.8203
			BREAK

		// Airport Fuel Depot
		CASE 3
			xposDestination = 1288.6713
			yposDestination = 1195.2324
			zposDestination = 9.8656
			BREAK

		// Building Site1
		CASE 4
			xposDestination = 2467.9021
			yposDestination = 1950.0613
			zposDestination = 9.2381
			BREAK

		// Shopping Mall Delivery Entrance
		CASE 5
			xposDestination = 2792.7441
			yposDestination = 2578.3364
			zposDestination = 9.8203
			BREAK

		// Warehouse2
		CASE 6
			xposDestination = 2271.4773
			yposDestination = 2791.7390
			zposDestination = 9.8203
			BREAK

		// Chinese Shopping Mall
		CASE 7
			xposDestination = 2596.5193
			yposDestination = 1738.5822
			zposDestination = 9.8281
			BREAK

		// Warehouse3
		CASE 8
			xposDestination = 2818.8401
			yposDestination = 912.5091
			zposDestination = 9.7500
			BREAK

		// Building Site2
		CASE 9
			xposDestination = 2706.5049
			yposDestination = 827.3236
			zposDestination = 9.2145
			BREAK

		// Warehouse4
		CASE 10
			xposDestination = 1627.7229
			yposDestination = 688.4043
			zposDestination = 9.8281
			BREAK

		// Warehouse5
		CASE 11
			xposDestination = 1504.4922
			yposDestination = 981.1410
			zposDestination = 9.7187
			BREAK

		// Airport
		CASE 12
			xposDestination = 1724.0117
			yposDestination = 1590.1277
			zposDestination = 9.2578
			BREAK

		// Warehouse6
		CASE 13
			xposDestination = 1727.8325
			yposDestination = 2338.0168
			zposDestination = 9.8130
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_LASVENTURAS_LOCATION
	ENDSWITCH

RETURN


// ***********************************
// Get the mission destination coordinates in Los Santos
Truck_Get_Mission_Coordinates_LosSantos:

	// LOS SANTOS DESTINATIONS
	//	0: Chemical Plant in Ocean Docks
	//	1: Ship in Ocean Docks
	//	2: Industrial area in Willowfield
	//	3: Airport in El Corona

	SWITCH nDestinationLocation
		CASE 0
			xposDestination = 2413.6826
			yposDestination = -2113.6736
			zposDestination = 12.3881
			BREAK

		CASE 1
			xposDestination = 2784.9731
			yposDestination = -2455.4414
			zposDestination = 12.6250
			BREAK

		CASE 2
			xposDestination = 2112.6621
			yposDestination = -2070.3762
			zposDestination = 12.5547
			BREAK

		CASE 3
			xposDestination = 1763.6410
			yposDestination = -2070.3713
			zposDestination = 12.6195
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_LOSSANTOS_LOCATION

	ENDSWITCH

RETURN


// ***********************************
// Get the mission destination coordinates in Country South
Truck_Get_Mission_Coordinates_CountrySouth:

	// COUNTRY SOUTH DESTINATIONS
	//	0: Junkyard in Whetstone
	//	1: Woodmill in Angel Pine
	//	2: Truckstop in Whetstone

	SWITCH nDestinationLocation
		CASE 0
			xposDestination = -1888.6215
			yposDestination = -1711.8364
			zposDestination = 20.7656
			BREAK

		CASE 1
			xposDestination = -2117.2273
			yposDestination = -2380.5068
			zposDestination = 29.4688
			BREAK

		CASE 2
			xposDestination = -1545.4387
			yposDestination = -2747.0322
			zposDestination = 47.5314
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_COUNTRYSOUTH_LOCATION

	ENDSWITCH

RETURN


// ***********************************
// Get the mission destination coordinates in Country North
Truck_Get_Mission_Coordinates_CountryNorth:

	// COUNTRY NORTH DESTINATIONS
	//	0: Truckstop in El Quebrados
	//	1: Art-deco cafe in Bayside Marina
	//	2: Fuelstop in Tierra Robada

	SWITCH nDestinationLocation
		CASE 0
			xposDestination = -1407.3749
			yposDestination = 2645.9573
			zposDestination = 54.7031
			BREAK

		CASE 1
			xposDestination = -2245.5808
			yposDestination = 2371.6934
			zposDestination = 3.9919
			BREAK

		CASE 2
			xposDestination = -1360.6327
			yposDestination = 2068.0942
			zposDestination = 51.4589
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_COUNTRYNORTH_LOCATION

	ENDSWITCH

RETURN


// ***********************************
// Get the mission destination coordinates in Country North 2
Truck_Get_Mission_Coordinates_CountryNorth2:

	// COUNTRY NORTH2 DESTINATIONS
	//	0: Refinery in Green Palms
	//	1: Truckstop in Bone County
	//	2: Factory in Bone County
	//	3: The Sherman Dam
	//	4: Desert Airfield in Verdant Meadows

	SWITCH nDestinationLocation
		CASE 0
			xposDestination = 274.2705
			yposDestination = 1382.7808
			zposDestination = 9.6016
			BREAK

		CASE 1
			xposDestination = 628.8638
			yposDestination = 1714.8909
			zposDestination = 5.9922
			BREAK

		CASE 2
			xposDestination = 635.0028
			yposDestination = 1213.7767
			zposDestination = 10.7188
			BREAK

		CASE 3
			xposDestination = -914.9530
			yposDestination = 2012.1382
			zposDestination = 59.9283
			BREAK

		CASE 4
			xposDestination = 385.8214
			yposDestination = 2595.5503
			zposDestination = 15.4843
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_COUNTRYNORTH2_LOCATION

	ENDSWITCH

RETURN


// ***********************************
// Get the mission destination coordinates in San Fierro
Truck_Get_Mission_Coordinates_SanFierro:

	// SAN FIERRO DESTINATIONS
	//	0: SF Airport Terminal at Easter Bay Airport
	//	1: Bridge under construction at Battery Point
	//	2: Petrol Station at Easter Basin
	//	3: Easter Basin Docks

	SWITCH nDestinationLocation
		CASE 0
			xposDestination =-1556.9773
			yposDestination = -441.3493
			zposDestination = 5.0000
			BREAK

		CASE 1
			xposDestination = -2659.6309
			yposDestination = 1380.6423
			zposDestination = 6.1643
			BREAK

		CASE 2
			xposDestination = -1650.9283
			yposDestination = 437.5679
			zposDestination = 6.1797
			BREAK

		CASE 3
			xposDestination = -1745.1165
			yposDestination = 37.8752
			zposDestination = 2.5408
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_SAN_FIERRO_LOCATION

	ENDSWITCH

RETURN




// *************************************************************************************************************
// 												HOUSEKEEPING GOSUBS   
// *************************************************************************************************************

// ****************************************
// DEBUG TOOLS
Truck_Debug_Tools:

	// Display mission stage variables for debug
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED  PS2_KEY_SPACE
		display_debug++

		IF display_debug > 2
			display_debug = 0
		ENDIF

		CLEAR_ALL_VIEW_VARIABLES
	ENDIF


	IF display_debug = 1
		// system variables
		Truck_view_debug[0] = m_stage
		Truck_view_debug[1] = m_goals
		Truck_view_debug[2] = m_mission_timer
		Truck_view_debug[3] = timerMissionControl - m_mission_timer
		Truck_view_debug[4] = nMissionTimeStage
		Truck_view_debug[5] = nDestinationDistance
		Truck_view_debug[6] = 0
		Truck_view_debug[7] = 0
		VIEW_INTEGER_VARIABLE Truck_view_debug[0] m_stage
		VIEW_INTEGER_VARIABLE Truck_view_debug[1] m_goals
		VIEW_INTEGER_VARIABLE Truck_view_debug[2] m_mission_timer
		VIEW_INTEGER_VARIABLE Truck_view_debug[3] Time_Boundary_hit
		VIEW_INTEGER_VARIABLE Truck_view_debug[4] Time_Stage
		VIEW_INTEGER_VARIABLE Truck_view_debug[5] Distance
	ENDIF


	IF display_debug = 2
	// A second page of variable output (if required - just copy format above)
	ENDIF
	 

	// Quit level - no mission pass/fail - cleanup only 
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_ESC 
		m_quit = 1
	ENDIF


	// Pause level
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED  PS2_KEY_P
		IF m_pause = 0
			m_pause = 1
			WRITE_DEBUG LEVEL_PAUSED
		ELSE
			m_pause = 0
			WRITE_DEBUG LEVEL_UNPAUSED
		ENDIF		
	ENDIF

RETURN



// ****************************************
// DEBUG SHORTCUTS
Truck_Debug_Shortcuts:

	IF m_stage = 50
	OR m_stage = 51
		RETURN
	ENDIF


	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
		GOSUB Truck_Display_Mission_Passed_Text
		m_passed = TRUE
		RETURN
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_M
		g_nTruckMissionsPassed++
		WRITE_DEBUG_WITH_INT Missions_Passed g_nTruckMissionsPassed
		RETURN
	ENDIF

	flagTempFlag = 0
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_A
		flagTempFlag = 1
	ENDIF

	flagTempFlag2 = 0
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D
		flagTempFlag2 = 1
	ENDIF

	nTempInt = 0	// used as a flag
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_J
		nTempInt = 1
	ENDIF

	IF NOT flagTempFlag = 1
	AND NOT flagTempFlag2 = 1
	AND NOT nTempInt = 1
		RETURN
	ENDIF

	IF flagTempFlag = 1
		TEMP_nTestAreas++
		IF TEMP_nTestAreas = 6
			TEMP_nTestAreas = 0
		ENDIF

		TEMP_nTestDestinations = 0
		WRITE_DEBUG_WITH_INT Area TEMP_nTestAreas
		RETURN
	ENDIF

	IF flagTempFlag2 = 1
		TEMP_nTestDestinations++
		WRITE_DEBUG_WITH_INT Destination TEMP_nTestDestinations
		RETURN
	ENDIF


	IF TEMP_nTestAreas = 0
		// ...country east
		SWITCH TEMP_nTestDestinations
			// Warehouse1
			CASE 1
				xposDestination = 56.744
				yposDestination = -268.404
				zposDestination = 0.579
				BREAK

			// Builders Yard
			CASE 2
				xposDestination = 100.397
				yposDestination = -155.05
				zposDestination = 1.583
				BREAK

			// Warehouse 2
			CASE 3
				xposDestination = 815.046
				yposDestination = -605.128
				zposDestination = 15.336
				BREAK

			// Factory 1
			CASE 4
				xposDestination = 1401.109
				yposDestination = 398.763
				zposDestination = 18.756
				BREAK

			// Factory 2
			CASE 5
				xposDestination = 1343.125
				yposDestination = 345.667
				zposDestination = 18.556
				BREAK

			// Brewery
			CASE 0
			DEFAULT
				xposDestination = -189.23
				yposDestination = -273.013
				zposDestination = 0.429
				TEMP_nTestDestinations = 0
				BREAK
		ENDSWITCH
	ENDIF


	IF TEMP_nTestAreas = 2
		// ...Vegas
		SWITCH TEMP_nTestDestinations
			// Plastic Chips Company
			CASE 1
				xposDestination = 1042.15
				yposDestination = 2130.46
				zposDestination = 101.0
				BREAK

			// Abattoir
			CASE 2
				xposDestination = 983.11
				yposDestination = 2076.38
				zposDestination = 101.0
				BREAK

			// Airport Fuel Depot
			CASE 3
				xposDestination = 1288.6713
				yposDestination = 1195.2324
				zposDestination = 9.8656
				BREAK

			// Building Site1
			CASE 4
				xposDestination = 2469.57
				yposDestination = 1949.52
				zposDestination = 101.0
				BREAK

			// Shopping Mall Delivery Entrance
			CASE 5
				xposDestination = 2807.83
				yposDestination = 2609.94
				zposDestination = 101.0
				BREAK

			// Warehouse2
			CASE 6
				xposDestination = 2248.71
				yposDestination = 2775.12
				zposDestination = 101.0
				BREAK

			// Chinese Shopping Mall
			CASE 7
				xposDestination = 2603.22
				yposDestination = 1730.48
				zposDestination = 101.0
				BREAK

			// Warehouse3
			CASE 8
				xposDestination = 2818.84
				yposDestination = 912.19
				zposDestination = 101.0
				BREAK

			// Building Site2
			CASE 9
				xposDestination = 2710.36
				yposDestination = 850.12
				zposDestination = 101.0
				BREAK

			// Warehouse4
			CASE 10
				xposDestination = 1588.90
				yposDestination = 715.31
				zposDestination = 101.0
				BREAK

			// Warehouse5
			CASE 11
				xposDestination = 1446.26
				yposDestination = 1000.51
				zposDestination = 101.0
				BREAK

			// Airport
			CASE 12
				xposDestination = 1724.0117
				yposDestination = 1590.1277
				zposDestination = 9.2578
				BREAK

			// Warehouse6
			CASE 13
				xposDestination = 1713.79
				yposDestination = 2329.05
				zposDestination = 101.0
				BREAK

			// Warehouse1
			CASE 0
			DEFAULT
				xposDestination = 1450.8
				yposDestination = 2360.69
				zposDestination = 101.0
				TEMP_nTestDestinations = 0
				BREAK
		ENDSWITCH
	ENDIF


	IF TEMP_nTestAreas = 1
		// ...los santos
		SWITCH TEMP_nTestDestinations
			// Ship
			CASE 1
				xposDestination = 2759.0000
				yposDestination = -2446.0000
				zposDestination = 101.0
				BREAK

			// Industry Type bit
			CASE 2
				xposDestination = 2104.0000
				yposDestination = -2089.0000
				zposDestination = 101.0
				BREAK

			// Airport
			CASE 3
				xposDestination = 1751.0000
				yposDestination = -2067.0000
				zposDestination = 101.0
				BREAK

			// Chemical Plant
			CASE 0
			DEFAULT
				xposDestination = 2412.0000
				yposDestination = -2106.0000
				zposDestination = 101.0
				TEMP_nTestDestinations = 0
				BREAK
		ENDSWITCH
	ENDIF


	IF TEMP_nTestAreas = 4
		// ...country south
		SWITCH TEMP_nTestDestinations
			// Woodmill
			CASE 1
				xposDestination = -2001.03
				yposDestination = -2388.08
				zposDestination = 101.0
				BREAK

			// Truckstop
			CASE 2
				xposDestination = -1560.67
				yposDestination = -2728.39
				zposDestination = 101.0
				BREAK

			// Junkyard
			CASE 0
			DEFAULT
				xposDestination = -1835.0
				yposDestination = -1647.68
				zposDestination = 101.0
				TEMP_nTestDestinations = 0
				BREAK
		ENDSWITCH
	ENDIF


	IF TEMP_nTestAreas = 6
		// ...country north
		SWITCH TEMP_nTestDestinations
			// Art-Deco Cafe
			CASE 1
				xposDestination = -1945.89
				yposDestination = 2377.90
				zposDestination = 101.0
				BREAK

			// Fuelstop
			CASE 2
				xposDestination = -1471.49
				yposDestination = 1864.85
				zposDestination = 101.0
				BREAK

			// TruckStop
			CASE 0
			DEFAULT
				xposDestination = -1286.73
				yposDestination = 2695.92
				zposDestination = 101.0
				TEMP_nTestDestinations = 0
				BREAK
		ENDSWITCH
	ENDIF


	IF TEMP_nTestAreas = 3
		// ...country north2
		SWITCH TEMP_nTestDestinations
			// TruckStop
			CASE 1
				xposDestination = 613.75
				yposDestination = 1692.39
				zposDestination = 101.0
				BREAK

			// Factory
			CASE 2
				xposDestination = 620.35
				yposDestination = 1249.328
				zposDestination = 101.0
				BREAK

			// Dam
			CASE 3
				xposDestination = -533.24
				yposDestination = 1995.917
				zposDestination = 101.0
				BREAK

			// Desert Airfield
			CASE 4
				xposDestination = 325.05
				yposDestination = 2543.29
				zposDestination = 101.0
				BREAK

			// Refinery
			CASE 0
			DEFAULT
				xposDestination = 246.56
				yposDestination = 1435.19
				zposDestination = 101.0
				TEMP_nTestDestinations = 0
				BREAK
		ENDSWITCH
	ENDIF


	IF TEMP_nTestAreas = 5
		// ...san fierro
		SWITCH TEMP_nTestDestinations
			// Bridge under construction
			CASE 1
				xposDestination = -2629.0908
				yposDestination = 1387.4202
				zposDestination = 101.0
				BREAK

			// Easter Basin Naval Station
			CASE 2
				xposDestination = -1530.776
				yposDestination = 487.201
				zposDestination = 101.0
				BREAK

			// Easter Basin Docks
			CASE 3
				xposDestination = -1745.08
				yposDestination = 27.759
				zposDestination = 101.0
				BREAK

			// SF Airport
			CASE 0
			DEFAULT
				xposDestination =-1549.8589
				yposDestination = -434.8600
				zposDestination = 101.0068
				TEMP_nTestDestinations = 0
				BREAK
		ENDSWITCH
	ENDIF


	CLEAR_AREA xposDestination yposDestination zposDestination 0.5 0
	SET_CHAR_HEADING scplayer 0.0
	SET_CHAR_COORDINATES scplayer xposDestination yposDestination -101.00 
	SET_CAMERA_BEHIND_PLAYER


RETURN



// ****************************************
// FRAME COUNTER (Useful if processor scheduling is needed)
Truck_Frame_Counter:

	m_frame_num++
	IF m_frame_num > 9
		m_frame_num = 0
	ENDIF

RETURN



// ****************************************
// ADDITIONAL TIMERS
Truck_Additional_Timers:

	GET_GAME_TIMER m_this_frame_time
	m_time_diff = m_this_frame_time - m_last_frame_time 
	m_last_frame_time = m_this_frame_time

	m_mission_timer += m_time_diff

RETURN



// =================================================================
// GOSUB: Next_Stage (sets flags and timers for next mission stage)
// =================================================================
Truck_next_stage:

   	m_stage++
   	m_goals        = 0
   	TIMERA 		   = 0
   	TIMERB		   = 0

RETURN					





// *************************************************************************************************************
//										INTRO CUTSCENE GOSUB
// *************************************************************************************************************

Truck_Intro_Cutscene:

RETURN




// *************************************************************************************************************
// 												INITIALISATION GOSUBS   
// *************************************************************************************************************

Truck_Initialisation:

	WHILE NOT IS_PLAYER_PLAYING player1
		WAIT 0
	ENDWHILE

	SET_PLAYER_CONTROL player1 OFF

//	PRINT_BIG ( TRUCK ) 1000 4 //Trucking
	WAIT 1000
	

	DO_FADE 1000 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE


	GOSUB Truck_Choose_Random_Cab_And_Trailer


	GOSUB Truck_Load_All_Models
	GOSUB Truck_Load_All_Anims


	// Position the player
	CLEAR_AREA truckX truckY truckZ 100.0 TRUE 
	LOAD_SCENE truckX truckY truckZ
	SET_CHAR_COORDINATES scplayer -76.9664 -1134.6389 0.0781 
	SET_CHAR_HEADING scplayer 14.7291
	SET_CAMERA_BEHIND_PLAYER


	// Don't want traffic while the player is hooking up the trailer
	SET_CAR_DENSITY_MULTIPLIER 0.0


	// Initialise available missions
	GOSUB Truck_Initialise_Available_Missions


	WAIT 2000


	// Return the oddjob 'title over fade' status back to default
	DRAW_ODDJOB_TITLE_BEFORE_FADE TRUE


	// Fade in
	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_IN
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	SET_PLAYER_CONTROL player1 ON

	nReasonForFailure = TRUCK_FAILED_UNKNOWN

	GOSUB Truck_next_stage

RETURN


// ***********************************
// CHOOSE RNADOM CAB AND TRAILER
Truck_Choose_Random_Cab_And_Trailer:

	// Choose a random cab
	GENERATE_RANDOM_INT_IN_RANGE 0 TRUCK_HIGHEST_CAB nThisCab
	nThisCab++

	IF nThisCab = TRUCK_CAB_PETRO
		// Choose the petrol tanker trailer
		nThisTrailer = TRUCK_TRAILER_PETROTR
	ELSE
		// Choose a random trailer (ignoring the petrol trailer)
		GENERATE_RANDOM_INT_IN_RANGE TRUCK_TRAILER_PETROTR TRUCK_HIGHEST_TRAILER nThisTrailer
		nThisTrailer++
	ENDIF

RETURN


// ***********************************
// LOAD ALL MODELS

Truck_Load_All_Models:

	// CARS
	// ...cab
	SWITCH nThisCab
		CASE TRUCK_CAB_PETRO
			REQUEST_MODEL PETRO
			BREAK

		CASE TRUCK_CAB_LINERUN
			REQUEST_MODEL LINERUN
			BREAK

		CASE TRUCK_CAB_RDTRAIN
			REQUEST_MODEL RDTRAIN
			BREAK

		DEFAULT
			WRITE_DEBUG Unknown_Cab
	ENDSWITCH

	// ...trailer
	SWITCH nThisTrailer
		CASE TRUCK_TRAILER_PETROTR
			REQUEST_MODEL PETROTR
			BREAK

		CASE TRUCK_TRAILER_ARTICT1
			REQUEST_MODEL ARTICT1
			BREAK

		CASE TRUCK_TRAILER_ARTICT2
			REQUEST_MODEL ARTICT2
			BREAK

		CASE TRUCK_TRAILER_ARTICT3
			REQUEST_MODEL ARTICT3
			BREAK

		DEFAULT
			WRITE_DEBUG Unknown_Trailer
	ENDSWITCH

	// PEDS

	// GUNS

	// OBJECTS


	LOAD_ALL_MODELS_NOW


	// Are Cars Loaded?
	// ...cab
	SWITCH nThisCab
		CASE TRUCK_CAB_PETRO
			WHILE NOT HAS_MODEL_LOADED	PETRO
				WAIT 0
			ENDWHILE
			BREAK

		CASE TRUCK_CAB_LINERUN
			WHILE NOT HAS_MODEL_LOADED	LINERUN
				WAIT 0
			ENDWHILE
			BREAK

		CASE TRUCK_CAB_RDTRAIN
			WHILE NOT HAS_MODEL_LOADED	RDTRAIN
				WAIT 0
			ENDWHILE
			BREAK

		DEFAULT
			WRITE_DEBUG Unknown_Cab
	ENDSWITCH

	// ...trailer
	SWITCH nThisTrailer
		CASE TRUCK_TRAILER_PETROTR
			WHILE NOT HAS_MODEL_LOADED	PETROTR
				WAIT 0
			ENDWHILE
			BREAK

		CASE TRUCK_TRAILER_ARTICT1
			WHILE NOT HAS_MODEL_LOADED	ARTICT1
				WAIT 0
			ENDWHILE
			BREAK

		CASE TRUCK_TRAILER_ARTICT2
			WHILE NOT HAS_MODEL_LOADED	ARTICT2
				WAIT 0
			ENDWHILE
			BREAK

		CASE TRUCK_TRAILER_ARTICT3
			WHILE NOT HAS_MODEL_LOADED	ARTICT3
				WAIT 0
			ENDWHILE
			BREAK

		DEFAULT
			WRITE_DEBUG Unknown_Trailer
	ENDSWITCH

	// Are Peds Loaded?

	// Are weapons loaded?

	// Are the Objects loaded?

RETURN


// ***********************************
// LOAD ALL ANIMS

Truck_Load_All_Anims:

//	REQUEST_ANIMATION INT_HOUSE				// for washing up


	// Are anims loaded?
//	WHILE NOT HAS_ANIMATION_LOADED	INT_HOUSE
//		WAIT 0
//	ENDWHILE

RETURN





// *************************************************************************************************************
//										ENTITY CREATION GOSUBS
// *************************************************************************************************************




// *************************************************************************************************************
//										END OF LEVEL GOSUBS - fail, pass, cleanup  
// *************************************************************************************************************

// FAIL
mission_failed_Truck:

	PRINT_BIG M_FAIL 5000 1

	// Display reason for failure
	SWITCH nReasonForFailure
		CASE TRUCK_FAILED_OUT_OF_CASH
			PRINT_NOW TRK_F1 5000 1
			BREAK
		CASE TRUCK_FAILED_OUT_OF_TIME
			PRINT_NOW TRK_F2 5000 1
			BREAK
		CASE TRUCK_FAILED_TOO_MUCH_DAMAGE
			PRINT_NOW TRK_F3 5000 1
			BREAK
		CASE TRUCK_FAILED_NOT_IN_CAB
			PRINT_NOW TRK_F4 5000 1
			BREAK
		CASE TRUCK_FAILED_TRAILER_NOT_ATTACHED
			PRINT_NOW TRK_F5 5000 1
			BREAK
	ENDSWITCH

RETURN


// PASS
// ...text for passing mission (displayed while player being forced out of cab)
Truck_Display_Mission_Passed_Text:

	GOSUB Truck_Update_Cash

	PRINT_WITH_NUMBER_BIG M_PASS countCash 5000 1
	ADD_SCORE player1 countCash

	CLEAR_ONSCREEN_COUNTER g_Truck_cashKM

	// Increase number of missions passed
	g_nTruckMissionsPassed++

	// Display number of missions passed
	IF g_nTruckMissionsPassed = 1
		// ... 1 mission passed so don't display plurals
		PRINT_NOW TRK_P1 5000 1
	ELSE
		IF g_nTruckMissionsPassed = TRUCK_HIGHEST_TRUCKING_MISSION
			IF done_truck_progress = FALSE
				REGISTER_ODDJOB_MISSION_PASSED
				PLAYER_MADE_PROGRESS 1
				PLAY_MISSION_PASSED_TUNE 2
				done_truck_progress = TRUE
				flagShowAssetAcquired = TRUE
			ENDIF

			// ...all progressive missions passed
			PRINT_BIG TRK_P4 5000 4
//			PRINT_NOW TRK_P2 10000 1
		ELSE
			// ...other numbers of missions
			PRINT_WITH_NUMBER_NOW TRK_P3 g_nTruckMissionsPassed 5000 1
		ENDIF
	ENDIF

	// STATS
	INCREMENT_INT_STAT_NO_MESSAGE TRUCK_MISSIONS_PASSED 1
	INCREMENT_INT_STAT_NO_MESSAGE TRUCK_MONEY_MADE countCash

RETURN


// ...regular 'pass' stuff
mission_passed_Truck:

	// Clear wanted level only on pass
	CLEAR_WANTED_LEVEL player1

RETURN


// CLEANUP
mission_cleanup_Truck:

	// Text Commands
//	USE_TEXT_COMMANDS FALSE


	// Entity Clearup
	MARK_CAR_AS_NO_LONGER_NEEDED	carCab
	MARK_CAR_AS_NO_LONGER_NEEDED	carTrailer

	
	// Blips
	REMOVE_BLIP blipCab
	REMOVE_BLIP blipDestination
	REMOVE_BLIP blipFailureOutOfCab
	REMOVE_BLIP blipFailureUnattachedTrailer


	// Counters
	CLEAR_ONSCREEN_COUNTER g_Truck_cashKM
	CLEAR_ONSCREEN_COUNTER g_Truck_damageKM
	CLEAR_ONSCREEN_TIMER g_Truck_timedKM


	// Animation Clearup


	// Get rid of the Player's mission specific weapons


	// Models
	MARK_MODEL_AS_NO_LONGER_NEEDED PETRO
	MARK_MODEL_AS_NO_LONGER_NEEDED LINERUN
	MARK_MODEL_AS_NO_LONGER_NEEDED RDTRAIN
	MARK_MODEL_AS_NO_LONGER_NEEDED PETROTR
	MARK_MODEL_AS_NO_LONGER_NEEDED ARTICT1
	MARK_MODEL_AS_NO_LONGER_NEEDED ARTICT2
	MARK_MODEL_AS_NO_LONGER_NEEDED ARTICT3


	// === RESTORE ENVIRONMENT SETTINGS ===
	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_CAR_DENSITY_MULTIPLIER 1.0


	// Camera


	// Switch on emergency services
	SWITCH_EMERGENCY_SERVICES ON


	// Make sure the mobile phone doesn't ring immediately after a mission
	// -------------------------------------------------------------------
	GET_GAME_TIMER timer_mobile_start


	// Return the oddjob 'title over fade' status back to default
	DRAW_ODDJOB_TITLE_BEFORE_FADE TRUE


	flag_player_on_oddjob	= FALSE
	flag_player_on_mission	= FALSE
	MISSION_HAS_FINISHED

RETURN
	 

}