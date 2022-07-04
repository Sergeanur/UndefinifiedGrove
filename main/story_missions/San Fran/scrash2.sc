MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
//
//			   MISSION NAME : SCrash2
//				     AUTHOR : Keith
//		        DESCRIPTION : An AWOL officer in LA is going to dish the dirt to a
//								reporter. Follow the reporter to the AWOL officer
//								and take them both out.
//
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

{
// *************************************************************************************************************
//								GENERAL INITIALISATION - Every level requires this
// *************************************************************************************************************


SCRIPT_NAME SCrash2


// Global Variables
// REMOVE TRAIN PROGRESS BAR
//VAR_INT		g_SCrash2_distanceKM
// END REMOVE TRAIN PROGRESS BAR
VAR_INT		g_SCrash2_spookKM

// Initialise Global Variables
g_SCrash2_spookKM = 0



// Mission Start stuff
GOSUB mission_start_SCrash2

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_failed_SCrash2
ENDIF

GOSUB mission_cleanup_SCrash2

MISSION_END


// Global Setup
mission_start_SCrash2:

REGISTER_MISSION_GIVEN
flag_player_on_mission = 1


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
LVAR_INT	m_fail_reason
// commonly used timers
LVAR_INT 	m_mission_timer
// Debug variables
LVAR_INT 	display_debug
VAR_INT 	SCrash2_view_debug[8]	// GLOBAL (for output)
// useful int variables
LVAR_INT	nLoop
LVAR_INT	nLoop2
LVAR_INT	nTempInt
LVAR_INT	nTempInt2
LVAR_INT	nTempPed
LVAR_INT	nTempObject
LVAR_INT	nTempAmmo
LVAR_INT	nTempTimer
LVAR_INT	nSeqTask
// useful float variables
LVAR_FLOAT	xposTemp	yposTemp	zposTemp
LVAR_FLOAT	xposTemp2	yposTemp2	zposTemp2
LVAR_FLOAT	headTemp
LVAR_FLOAT	xloTemp		yloTemp		zloTemp
LVAR_FLOAT	xhiTemp		yhiTemp		zhiTemp
LVAR_FLOAT	fTempFloat
LVAR_FLOAT	fTempFloat2
LVAR_FLOAT	fTempDistance
// useful flag variables
LVAR_INT	flagTempFlag
LVAR_INT	flagTempFlag2
LVAR_INT	flagCutscenePlaying
LVAR_INT	flagCleaningUpSkippedCutscene
LVAR_INT	flagSkipCutscene



// Clear cutscene flags
flagCutscenePlaying					= 0
flagCleaningUpSkippedCutscene		= 0
flagSkipCutscene					= 0




m_stage			= 0
m_goals			= 0
m_passed		= 0
m_failed		= 0	  
m_quit			= 0
m_pause			= 0
m_status		= 0
m_fail_reason	= 0


m_mission_timer	= 0

display_debug	= 0

TIMERA = 0
TIMERB = 0



// Consts
// ...reasons for failure
CONST_INT	SCRASH2_FAILED_UNKNOWN							0
CONST_INT	SCRASH2_FAILED_TRAIN_GONE						1
CONST_INT	SCRASH2_FAILED_REPORTER_DEAD					2
CONST_INT	SCRASH2_FAILED_LOST_THE_REPORTER				3
CONST_INT	SCRASH2_FAILED_MEETING_OVER						4
CONST_INT	SCRASH2_FAILED_REPORTER_ESCAPED					5
CONST_INT	SCRASH2_FAILED_TARGET_ESCAPED					6
CONST_INT	SCRASH2_FAILED_REPORTER_SPOOKED					7
// ...number of other trains
CONST_INT	SCRASH2_NUMBER_OF_OTHER_TRAINS					3
// ...player arsing around distance (used to finish the mission quickly)
CONST_INT	SCRASH2_FAILURE_DISTANCE						2500
// ...reporter on pier AI
CONST_INT	SCRASH2_AI_REPORTER_ON_PIER_NOT_INITIALISED		0
//CONST_INT	SCRASH2_AI_REPORTER_ON_PIER_COWER				1
//CONST_INT	SCRASH2_AI_REPORTER_ON_PIER_COWERING			2
CONST_INT	SCRASH2_AI_REPORTER_ON_PIER_FLEE				3
CONST_INT	SCRASH2_AI_REPORTER_ON_PIER_FLEEING				4
// ...target AI
CONST_INT	SCRASH2_AI_TARGET_NOT_INITIALISED				0
CONST_INT	SCRASH2_AI_TARGET_KILL_PLAYER					3
CONST_INT	SCRASH2_AI_TARGET_KILLING_PLAYER				4
//CONST_INT	SCRASH2_AI_TARGET_FLEE							5
//CONST_INT	SCRASH2_AI_TARGET_FLEEING						6
// ...suspicion status
CONST_INT	SCRASH2_SUSPICION_STATUS_NONE					0
CONST_INT	SCRASH2_SUSPICION_STATUS_TOO_CLOSE				1
CONST_INT	SCRASH2_SUSPICION_STATUS_TOO_FAR_AWAY			2
CONST_INT	SCRASH2_SUSPICION_STATUS_PLAYER_AHEAD			3
// ...suspicion areas
CONST_INT	SCRASH2_SUSPICION_AREA_NONE						0
CONST_INT	SCRASH2_SUSPICION_AREA_PLATFORM					1
CONST_INT	SCRASH2_SUSPICION_AREA_LOW_STEPS				2
CONST_INT	SCRASH2_SUSPICION_AREA_HIGH_STEPS				3
CONST_INT	SCRASH2_SUSPICION_AREA_ENTRANCE					4
CONST_INT	SCRASH2_SUSPICION_AREA_KERB						5
// ...suspicion distances when reporter walking
// NOTE: The lo/hi distances prevent constant switching between two states when the reporter is on the distance boundary
CONST_FLOAT	SCRASH2_SUSPICION_WALK_MAX_DISTANCE_LO			8.0
CONST_FLOAT	SCRASH2_SUSPICION_WALK_MAX_DISTANCE_HI			10.0
// ...safe distances when reporter in car
// REMOVE OLD-STYLE SPOOK-O-METER
//CONST_FLOAT	SCRASH2_TOO_CLOSE_INSTANT						25.0
// END REMOVE OLD-STYLE SPOOK-O-METER
CONST_FLOAT	SCRASH2_TOO_CLOSE								40.0
CONST_FLOAT SCRASH2_TOO_FAR_AWAY							100.0
CONST_FLOAT SCRASH2_TOO_FAR_AWAY_INSTANT					140.0
// ...Taxi enroute locations
CONST_INT	SCRASH2_TAXI_WAYPOINT_NONE		 				0
CONST_INT	SCRASH2_TAXI_WAYPOINT_A							1
CONST_INT	SCRASH2_TAXI_WAYPOINT_B							2
CONST_INT	SCRASH2_TAXI_WAYPOINT_C							3
CONST_INT	SCRASH2_TAXI_WAYPOINT_D							4
CONST_INT	SCRASH2_TAXI_WAYPOINT_E							5
CONST_INT	SCRASH2_TAXI_WAYPOINT_F							6
CONST_INT	SCRASH2_TAXI_WAYPOINT_G							7
CONST_INT	SCRASH2_TAXI_WAYPOINT_H							8
CONST_INT	SCRASH2_TAXI_WAYPOINT_I							9
CONST_INT	SCRASH2_TAXI_WAYPOINT_J							10
CONST_INT	SCRASH2_TAXI_WAYPOINT_K							11
CONST_INT	SCRASH2_TAXI_WAYPOINT_L							12
CONST_INT	SCRASH2_TAXI_WAYPOINT_PIER						13
// REMOVE TAXI ENFORCED STOPS
// ...Taxi stopped status
//CONST_INT	SCRASH2_TAXI_STOPPED_STATUS_NO_STOP				0
//CONST_INT	SCRASH2_TAXI_STOPPED_STATUS_TO_STOP				1
//CONST_INT	SCRASH2_TAXI_STOPPED_STATUS_STOPPED				2
// END REMOVE TAXI ENFORCED STOPS
// ...Station Walk status
CONST_INT	SCRASH2_STATION_WALK_NOT_STARTED				0
CONST_INT	SCRASH2_STATION_WALK_TOP_OF_STAIRS_INIT			1
CONST_INT	SCRASH2_STATION_WALK_TOP_OF_STAIRS_LOCATE		2
CONST_INT	SCRASH2_STATION_WALK_MID_LEVEL_INIT				3
CONST_INT	SCRASH2_STATION_WALK_MID_LEVEL_LOCATE			4
CONST_INT	SCRASH2_STATION_WALK_FOOT_OF_STAIRS_INIT		5
CONST_INT	SCRASH2_STATION_WALK_FOOT_OF_STAIRS_LOCATE		6
CONST_INT	SCRASH2_STATION_WALK_READY_TO_LOOK_AROUND		7
CONST_INT	SCRASH2_STATION_WALK_KERB_INIT					8
CONST_INT	SCRASH2_STATION_WALK_KERB_LOCATE				9
CONST_INT	SCRASH2_STATION_WALK_WAIT_FOR_TAXI				10
// ...train speed
CONST_FLOAT	SCRASH2_MAX_TRAIN_SPEED_mps						25.0
// ADD NEW-STYLE SPOOK-O-METER
CONST_INT	SCRASH2_SPOOKOMETER_INCREASE_msec				60
CONST_INT	SCRASH2_SPOOKOMETER_DECREASE_msec				40
// END ADD NEW-STYLE SPOOK-O-METER
// ...General
CONST_INT	SCRASH2_PERSIST									-2


// Mission Specific Variables
// Integer Variables
// ...chars 				(char)
LVAR_INT	charReporter
LVAR_INT	charTaxiDriverLS
LVAR_INT	charTarget
// ...cars 					(car)
LVAR_INT	carTrain
LVAR_INT	carCarriage
LVAR_INT	carCarriage2
LVAR_INT	carOtherTrain[SCRASH2_NUMBER_OF_OTHER_TRAINS]
LVAR_INT	carTaxiLS
LVAR_INT	carReporter
LVAR_INT	carMotorbike
// ...objects 				(object)
// ...blips 				(blip)
LVAR_INT	blipDestination
LVAR_INT	blipTrain
LVAR_INT	blipReporter
LVAR_INT	blipTarget
LVAR_INT	blipSniperRifle
// ...pickups 				(pickup)
LVAR_INT	pickupSniperRifle
// ...fx systems 			(fx)
// ...decision makers		(dm)
LVAR_INT	dmEmpty
LVAR_INT	dmTough
// ...AI Status				(ai)
LVAR_INT	aiReporterOnPier
LVAR_INT	aiTarget
// ...general status		(status)
LVAR_INT	statusSuspicion
// REMOVE TAXI ENFORCED STOPS
//LVAR_INT	statusTaxiStopped
// END REMOVE TAXI ENFORCED STOPS
LVAR_INT	statusStationWalk
LVAR_INT	statusStationWalkInit
// ...Timers				(timer)
LVAR_INT	timerTrain
LVAR_INT	timerReporter
LVAR_INT	timerTaxiLS
LVAR_INT	timerMeeting
LVAR_INT	timerShootingOnPierReactionDelay
LVAR_INT	timerReporterAI
LVAR_INT	timerReporterAnim
LVAR_INT	timerTargetAI
LVAR_INT	timerText
LVAR_INT	timerHelpText
// REMOVE OLD-STYLE SPOOK-O-METER
//LVAR_INT	timerSuspicionNextUpdate
//LVAR_INT	timerFailureConditions
// END REMOVE OLD-STYLE SPOOK-O-METER
// ADD NEW-STYLE SPOOK-O-METER
LVAR_INT	timerTooFarAway
LVAR_INT	timerTooClose
// END ADD NEW-STYLE SPOOK-O-METER
LVAR_INT	timerTaxiStopped
LVAR_INT	timerJustStartedDrivingLeeway
LVAR_INT	timerLookAtAnim
LVAR_INT	timerReporterSpookedAndFleeing
// ...Counters				(count)
// ...mission specific		(n)
LVAR_INT	nTrainDistanceFromPlayer
LVAR_INT	nTrainDistanceFromLS
LVAR_INT	nPlayerDistanceFromLS
LVAR_INT	nDistanceSFtoLS
LVAR_INT	nOtherTrainPosition[SCRASH2_NUMBER_OF_OTHER_TRAINS]
LVAR_INT	nSuspicionWalkArea
LVAR_INT	nTaxiWaypointID
LVAR_INT	nTaxiChanceOfBurstOfSpeed
// REMOVE TAXI ENFORCED STOPS
//LVAR_INT	nTaxiChanceOfStopping
// END REMOVE TAXI ENFORCED STOPS
LVAR_INT	nTaxiNumberOfWaypoints
LVAR_INT	nFailure
LVAR_INT	nSniperAmmoAtStartOfMission


// Text Label Variables


// Float Variables
// ...area variables 		(xlo, ylo, zlo, xhi, yhi, zhi)
// ...position variables	(xpos, ypos, zpos)
LVAR_FLOAT	xposDestination		yposDestination		zposDestination
LVAR_FLOAT	xposWaypoint		yposWaypoint		zposWaypoint


// Boolean Variables
// ...flags					(flag)
LVAR_INT	flagTrainArrivedAtLS
LVAR_INT	flagTriggerReporter
LVAR_INT	flagCheckForPlayerNearLS
LVAR_INT	flagTaxiApproachingReporter
LVAR_INT	flagTriggerTaxiLS
LVAR_INT	flagAllowTaxiLS
LVAR_INT	flagReporterAssassinated
LVAR_INT	flagTargetAssassinated
LVAR_INT	flagPlayerDetectedOnPier
LVAR_INT	flagMeetingOver
LVAR_INT	flagPerformingShootingOnPierReactionDelay
LVAR_INT	flagReporterOnPier
LVAR_INT	flagPlayerOnPier
LVAR_INT	flagDisplayedAssassinationText
LVAR_INT	flagJustStartedDriving
LVAR_INT	flagReporterSpooked
LVAR_INT	flagReporterGettingIntoCab
LVAR_INT	flagRemoveSniperRifle
LVAR_INT	flagPlayerShootingNearWalkingReporter
LVAR_INT	flagReporterSpookedAndFleeing
LVAR_INT	flagTaxiLSCreationPositionAlpha
LVAR_INT	flagPlayerAheadOfReporterOnPier
LVAR_INT	flagDisplayedTooClose
LVAR_INT	flagCheckForRemoveMotorbike
LVAR_INT	flagPreviouslySuspicious
LVAR_INT	flagAllowTurnTowardsPlayer
LVAR_INT	flagDisplayedSniperRifleHelp
LVAR_INT	flagDisplayedSniperRifleHelp2
// ...exists				(exists)
LVAR_INT	existsTrain
LVAR_INT	existsReporter
LVAR_INT	existsTaxiLS
LVAR_INT	existsTarget
// Clear Exists
existsTrain		= 0
existsReporter	= 0
existsTaxiLS	= FALSE
existsTarget	= FALSE
// ...loaded				(loaded)
LVAR_INT	loadedTaxiLS
// Clear Loaded
loadedTaxiLS	= FALSE


// Clear timers


// Clear important flags
flagReporterAssassinated		= FALSE
flagTargetAssassinated			= FALSE
flagPlayerDetectedOnPier		= FALSE
flagReporterOnPier				= FALSE
flagReporterSpooked				= FALSE
flagRemoveSniperRifle			= TRUE
flagCheckForRemoveMotorbike		= FALSE
flagDisplayedSniperRifleHelp	= FALSE
flagDisplayedSniperRifleHelp2	= FALSE



// ***** FAKE ENTITY CREATION TO FOOL THE COMPILER *****
// The compiler just needs to verify there is a CREATE_ before usage
IF m_stage = -99
	
	WRITE_DEBUG SHOULD_NEVER_BE_IN_FAKE_ENTITY_CREATION

	// Cars
	CREATE_MISSION_TRAIN 11 0.0 0.0 0.0 FALSE carTrain
	CREATE_CAR TAXI 0.0 0.0 0.0 carTaxiLS
	CREATE_CAR SANCHEZ 0.0 0.0 0.0 carMotorbike

	REPEAT SCRASH2_NUMBER_OF_OTHER_TRAINS nLoop
		CREATE_MISSION_TRAIN 10 0.0 0.0 0.0 FALSE carOtherTrain[nLoop]
	ENDREPEAT

	// Peds
	CREATE_CHAR PEDTYPE_CIVMALE WMYBU 0.0 0.0 0.0 charReporter
	CREATE_CHAR PEDTYPE_CIVMALE BMYBE 0.0 0.0 0.0 charTaxiDriverLS
	CREATE_CHAR PEDTYPE_CIVMALE HMYRI 0.0 0.0 0.0 charTarget

	// Pickups
	CREATE_PICKUP_WITH_AMMO SNIPER PICKUP_ONCE 0 0.0 0.0 0.0 pickupSniperRifle

	// Blips
 	ADD_BLIP_FOR_COORD	0.0 0.0 0.0			blipDestination
	ADD_BLIP_FOR_CAR	carTrain			blipTrain
	ADD_BLIP_FOR_CHAR	charReporter		blipReporter
	ADD_BLIP_FOR_CHAR	charTarget			blipTarget
	ADD_BLIP_FOR_PICKUP pickupSniperRifle	blipSniperRifle

ENDIF



// Mission Text
LOAD_MISSION_TEXT SCrash2


// Intro Cutscene
GOSUB SCrash2_Intro_Cutscene


// Load Char Mission Decision Makers
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY dmEmpty
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH dmTough


// Mission Initialisation
GOSUB SCrash2_Initialisation


// Special: check how much sniper ammo (if any) the player has at the start of the mission. This is to
//	ensure that the player doesn't lose his sniper rifle at the end if he already had one at the start.
GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_SNIPERRIFLE nSniperAmmoAtStartOfMission
flagRemoveSniperRifle = TRUE






// *************************************************************************************************************
//								                 MISSION LOOP
// *************************************************************************************************************
mission_loop_SCrash2:

	WAIT 0


	// Special shortcut for Craig F to test all missions in order
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		m_passed = 1
		GOTO end_of_main_loop_SCrash2
	ENDIF



	// Debug Stuff
	GOSUB SCrash2_Debug_Tools
	GOSUB SCrash2_Debug_Shortcuts

	IF m_quit = 1
	OR m_pause = 1
		GOTO end_of_main_loop_SCrash2
	ENDIF


	// Housekeeping
	GOSUB SCrash2_Frame_Counter
	GOSUB SCrash2_Additional_Timers


	// Special conditions
	IF IS_CHAR_DEAD scplayer
		m_failed = 1
		GOTO end_of_main_loop_SCrash2
	ENDIF		


	// Mission Stage processing
	// *** INITIALISATION NOW TAKES PLACE BEFORE THE MAIN LOOP ***
	IF m_stage = 0
		WRITE_DEBUG STAGE_SHOULD_NEVER_BE_0_IN_MAIN_LOOP
	ENDIF


	// ...Stage 1: Get the sniper rifle
	IF m_stage = 1
		GOSUB SCrash2_Stage_GetTheSniperRifle
	ENDIF 


	// ...Stage 2: Get to the Train Station
	IF m_stage = 2
		GOSUB SCrash2_Stage_GetToTheTrainStation
	ENDIF 


	// ...Stage 3: Follow the Train
	IF m_stage = 3
		GOSUB SCrash2_Stage_FollowTheTrain
	ENDIF


	// ...Stage 4: Reporter Hails a Taxi
	IF m_stage = 4
		GOSUB SCrash2_Stage_ReporterHailsATaxi
	ENDIF


	// ...Stage 5: Reporter Goes To Meeting Place
	IF m_stage = 5
		GOSUB SCrash2_Stage_ReporterGoesToMeetingPlace
	ENDIF


	// ...Stage 6: Reporter and Target meet
	IF m_stage = 6
		GOSUB SCrash2_Stage_ReporterMeetsTheTarget
	ENDIF


	// ...final stage
	IF m_stage = 7
		m_passed = 1
	ENDIF


	// Continuous update methods and event checking







// End of Main Loop
// ***** DON'T CHANGE *****
end_of_main_loop_SCrash2:

	IF m_quit = 0
		IF m_failed = 0
			IF m_passed = 0																 
				// Restart main loop
				GOTO mission_loop_SCrash2 
			ELSE
				// Mission passed
				GOSUB mission_passed_SCrash2
				RETURN
			ENDIF
		ELSE
			// Mission failed
			GOSUB mission_failed_SCrash2
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
// STAGE 1: Get the Sniper Rifle

SCrash2_Stage_GetTheSniperRifle:

   	// Initialisation for this stage
   	IF m_goals = 0
		GOSUB SCrash2_Create_SniperRifle

		// Instructions: Pick up the sniper rifle
		PRINT_NOW SCR2_21 7000 1

		m_goals++

	ENDIF


	// FOR NOW: Give the player the sniper rifle and quit
	IF m_goals = 1
		IF HAS_PICKUP_BEEN_COLLECTED pickupSniperRifle
			REMOVE_BLIP blipSniperRifle

			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates for stage


	// exit
	IF m_goals = 99
		GOSUB SCrash2_next_stage
	ENDIF

RETURN


// ****************************************
// STAGE 2: Get to the Train Station

SCrash2_Stage_GetToTheTrainStation:

	// This should never happen, but I still need to do these checks.
	// If it should happen, then just fail the mission
	IF m_goals > 0
	AND IS_CHAR_DEAD charReporter
		m_failed = 1
		RETURN
	ENDIF

	IF m_goals > 1
	AND IS_CAR_DEAD carTrain
		m_failed = 1
		RETURN
	ENDIF


   	// Initialisation for this stage
   	IF m_goals = 0
		GOSUB SCrash2_Create_Reporter_SF

		xposDestination = -1952.7909
		yposDestination = 136.5608
		zposDestination = 25.2734
		ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination
		CHANGE_BLIP_DISPLAY blipDestination BLIP_ONLY

		// Instruction: reach station
		PRINT_NOW SCR2_00 8000 1
		timerTrain = m_mission_timer + 6000

		m_goals++
	ENDIF


	// Create Train
	IF m_goals = 1
		IF timerTrain < m_mission_timer
			GOSUB SCrash2_Create_Train
			SET_TRAIN_SPEED			carTrain 20.0
			SET_TRAIN_CRUISE_SPEED	carTrain 20.0

			// Message: Train arriving
			PRINT SCR2_01 10000 1

			m_goals++
		ENDIF
	ENDIF


	// Make the train stop (instantly make it go slow, then let it roll to a stop from there)
	IF m_goals = 2
		IF LOCATE_CAR_2D carTrain -1944.5453 130.8166 5.0 5.0 FALSE
			SET_TRAIN_SPEED			carTrain 4.0
			SET_TRAIN_CRUISE_SPEED	carTrain 0.0
			m_goals++
		ENDIF
	ENDIF


	// Reporter enters train
	IF m_goals = 3
		IF IS_CAR_STOPPED carTrain
			GET_TRAIN_CARRIAGE carTrain 1 carCarriage
			TASK_ENTER_CAR_AS_PASSENGER charReporter carCarriage 500 -1

			// Give the player a period of time (quite generous) for the player to reach the station
			timerTrain = m_mission_timer + 60000

			m_goals++
		ENDIF
	ENDIF


	// Wait for the player to reach the station
	IF m_goals = 4
		xloTemp = -1978.1010
		yloTemp = 78.2240
		xhiTemp = -1922.0023
		yhiTemp = 203.4028

		IF IS_CHAR_IN_AREA_2D scplayer xloTemp yloTemp xhiTemp yhiTemp FALSE
			// ...player in station, so set the train moving
			LOCK_CAR_DOORS carTrain CARLOCK_LOCKOUT_PLAYER_ONLY
			SET_TRAIN_CRUISE_SPEED	carTrain 40.0

			REMOVE_BLIP blipDestination
			ADD_BLIP_FOR_CAR carTrain blipTrain

			// Remove the reporter after it has boarded the train
			REMOVE_CHAR_ELEGANTLY charReporter
			existsReporter = 0

			// Instruction: Train leaving for LS
			PRINT_NOW SCR2_02 10000 1

			m_goals = 99
		ELSE
			IF timerTrain < m_mission_timer
				// ...player hasn't made it to the station in decent time, so make the train pull away slowly
				LOCK_CAR_DOORS carTrain CARLOCK_LOCKOUT_PLAYER_ONLY
				SET_TRAIN_CRUISE_SPEED	carTrain 5.0

				// Give the player a bit more time to arrive before failure
				timerTrain = m_mission_timer + 60000

				m_goals++
			ENDIF
		ENDIF
	ENDIF

	// Wait for the player to reach the station platform
	IF m_goals = 5
		xloTemp = -1978.1010
		yloTemp = 78.2240
		xhiTemp = -1922.0023
		yhiTemp = 203.4028

		IF IS_CHAR_IN_AREA_2D scplayer xloTemp yloTemp xhiTemp yhiTemp FALSE
			REMOVE_BLIP blipDestination
			ADD_BLIP_FOR_CAR carTrain blipTrain

			// Instruction: Train leaving for LS
			PRINT_NOW SCR2_02 10000 1

			SET_TRAIN_CRUISE_SPEED	carTrain 10.0

			m_goals = 99
		ELSE
			IF timerTrain < m_mission_timer
				// ...player never made it in time
				m_failed = 1
				m_fail_reason = SCRASH2_FAILED_TRAIN_GONE
			ENDIF
		ENDIF
	ENDIF


	// Continuous updates for stage
	// ----------------------------


	// exit
	IF m_goals = 99
		GOSUB SCrash2_next_stage
	ENDIF

RETURN


// ****************************************
// STAGE 3: Follow the train to LA

SCrash2_Stage_FollowTheTrain:

	// This should never happen, but I still need to do these checks.
	// If it should happen, then just fail the mission
	IF IS_CAR_DEAD carTrain
		m_failed = 1
		RETURN
	ENDIF


   	// Initialisation for this stage
   	IF m_goals = 0
		nTrainDistanceFromPlayer	= 0
		nTrainDistanceFromLS		= 0
		nPlayerDistanceFromLS		= 0
		nDistanceSFtoLS				= 0
		flagTrainArrivedAtLS		= 0
		flagTriggerReporter			= 0
		flagCheckForPlayerNearLS	= 0

		// Coordinates of destination
		xposDestination = 833.7101
		yposDestination = -1383.6434
		zposDestination = -2.6352

		// Indicate that other train positions haven't been calculated yet
		REPEAT SCRASH2_NUMBER_OF_OTHER_TRAINS nLoop
			nOtherTrainPosition[nLoop] = -1
		ENDREPEAT

		// Set a blip at the LS train station entrance
//		ADD_BLIP_FOR_COORD 812.4495 -1343.4883 12.5320 blipDestination

// REMOVE TRAIN PROGRESS BAR
//		// Display the train's distance from LS
//		DISPLAY_ONSCREEN_COUNTER_WITH_STRING g_SCrash2_distanceKM COUNTER_DISPLAY_BAR SCR2_50
// END REMOVE TRAIN PROGRESS BAR

		// Don't let the player's wanted level increase so quickly
		SET_WANTED_MULTIPLIER 0.3

		m_goals++
	ENDIF


	// Has the train reached it's destination?
	IF m_goals = 1
		IF LOCATE_CAR_2D carTrain xposDestination yposDestination 5.0 5.0 FALSE
			// Train at station, so instantly slow it down then let it roll to a stop
			SET_TRAIN_SPEED			carTrain 16.0
			SET_TRAIN_CRUISE_SPEED	carTrain 0.0

			// Info: The train is arriving
			PRINT_NOW SCR2_03 11000 1
			timerText = m_mission_timer + 10000

			flagTrainArrivedAtLS = 1

			m_goals++
		ENDIF
	ENDIF


	// Has the arrival text timed out?
	IF m_goals = 2
		IF timerText < m_mission_timer
			// ...display the spook-o-meter and the explanation text
			GOSUB SCrash2_Initialise_Spook_Meter

			PRINT_NOW SCR2_09 9000 1
			timerText = m_mission_timer + 8000

			m_goals++
		ENDIF
	ENDIF


	// Has the spook-o-meter text timed out?
	IF m_goals = 3
		IF timerText < m_mission_timer
			m_goals++
		ENDIF
	ENDIF


	// Has the train stopped?
	IF m_goals = 4
		IF IS_CAR_STOPPED carTrain
			// Give the player a period of time to trigger the next stage before it is automatically triggered
			flagCheckForPlayerNearLS = 1
			timerTrain = m_mission_timer + 120000
			REMOVE_BLIP blipTrain
// REMOVE TRAIN PROGRESS BAR
//			CLEAR_ONSCREEN_COUNTER g_SCrash2_distanceKM
// END REMOVE TRAIN PROGRESS BAR
			m_goals++
		ENDIF
	ENDIF


	// Is it time to trigger the reporter?
	IF m_goals = 5
		IF flagTriggerReporter = 1
			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates for stage
	GOSUB SCrash2_Calculate_Train_Distance_From_Player
	GOSUB SCrash2_Calculate_Train_Distance_From_LS
	GOSUB SCrash2_Calculate_Player_Distance_From_LS
	GOSUB SCrash2_Update_Train_Cruise_Speed
	GOSUB SCrash2_Update_Train_Distance_To_LS
	GOSUB SCrash2_Update_Other_Trains

	// Check for trigger reporter conditions
	// ...timer elapsed
	IF flagTriggerReporter = 0
	AND flagCheckForPlayerNearLS = 1
		IF timerTrain < m_mission_timer
			flagTriggerReporter = 1
		ENDIF
	ENDIF

	// ...player nearby
	IF flagTriggerReporter = 0
	AND flagCheckForPlayerNearLS = 1
		IF nPlayerDistanceFromLS < 300
			flagTriggerReporter = 1
		ENDIF
	ENDIF

// USE A CONSTANT SPEED (so just let the level play out as normal until it naturally fails)
/*
	// ...if the player is too far away, then put him out of his misery
	IF flagTriggerReporter = 0
	AND flagCheckForPlayerNearLS = 1
		IF nPlayerDistanceFromLS > SCRASH2_FAILURE_DISTANCE
			m_failed = 1
			m_fail_reason = SCRASH2_FAILED_LOST_THE_REPORTER
			RETURN
		ENDIF
	ENDIF
*/
// END USE A CONSTANT SPEED

	// ...get rid of the optional motorbike?
	IF flagCheckForRemoveMotorbike = TRUE
		IF NOT IS_CHAR_IN_ZONE scplayer SF
			IF IS_CAR_DEAD carMotorbike
				flagCheckForRemoveMotorbike = FALSE
			ENDIF

			IF nPlayerDistanceFromLS < 2800
				GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
				GET_CAR_COORDINATES carMotorbike xposTemp2 yposTemp2 zposTemp2
				GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
				IF fTempDistance < 100.0
					// ...player may be using the motorbike, so don't check for removal any longer
					flagCheckForRemoveMotorbike = FALSE
				ELSE
					// ...player far away from motorbike, so clear it up
					flagCheckForRemoveMotorbike = FALSE
					MARK_CAR_AS_NO_LONGER_NEEDED carMotorbike
					MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// exit
	IF m_goals = 99
		GOSUB SCrash2_next_stage
	ENDIF

RETURN


// ****************************************
// STAGE 4: Reporter Hails a Taxi

SCrash2_Stage_ReporterHailsATaxi:

   	// Initialisation for this stage
   	IF m_goals = 0
		// Instructions: The reporter is leaving. Follow without being seen.
		PRINT SCR2_04 5000 1

		// Set up suspicion level variables
		GOSUB SCrash2_Initialise_Suspicion_Level
		
		// Create the reporter on the platform
		GOSUB SCrash2_Create_Reporter_LS
		ADD_BLIP_FOR_CHAR charReporter blipReporter
//		REMOVE_BLIP blipDestination

		// Get rid of the train
		timerTrain = m_mission_timer + 10000
		timerReporterAnim = 0
		timerReporterSpookedAndFleeing = 0

		// Clear any flags used in this stage
		flagAllowTaxiLS					= FALSE
		flagTriggerTaxiLS				= FALSE
		flagTaxiApproachingReporter 	= FALSE
		flagReporterGettingIntoCab		= FALSE
		flagReporterSpookedAndFleeing	= FALSE
		flagDisplayedTooClose			= FALSE
		flagAllowTurnTowardsPlayer		= FALSE

		// Switch off the roads the taxi will travel on
		SWITCH_ROADS_OFF 789.5950 -1387.9701 11.5469 807.2843 -1334.5457 13.7250
		SWITCH_ROADS_OFF 804.9978 -1414.0320 11.3801 908.0844 -1386.6179 13.5524
		SWITCH_ROADS_OFF 802.1913 -1335.0664 11.1828 911.4662 -1313.0450 13.7469

		// Reset the station walk status
		statusStationWalk = SCRASH2_STATION_WALK_NOT_STARTED
		statusStationWalkInit = SCRASH2_STATION_WALK_NOT_STARTED

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------

	IF existsReporter = TRUE
		// ...reporter dead
		IF IS_CHAR_DEAD charReporter
			m_failed = TRUE
			m_fail_reason = SCRASH2_FAILED_REPORTER_DEAD
			RETURN
		ENDIF

		// ...reporter damaged
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR charReporter scplayer
			m_failed = TRUE
			m_fail_reason = SCRASH2_FAILED_REPORTER_SPOOKED
			TASK_SMART_FLEE_CHAR charReporter scplayer 9999.0 10000
			RETURN
		ENDIF
	ENDIF

	// If the player has been shooting near the reporter, check if the timeout has occurred so that the 'spooked' message can be displayed
	IF flagPlayerShootingNearWalkingReporter = TRUE
		IF timerReporterSpookedAndFleeing < m_mission_timer
			m_failed = TRUE
			m_fail_reason = SCRASH2_FAILED_REPORTER_SPOOKED
		ENDIF

		RETURN
	ENDIF

	// If the player has attacked the taxi close to the reporter, check for the timeout before mission failed
	IF flagReporterSpookedAndFleeing = TRUE
		IF timerReporterSpookedAndFleeing < m_mission_timer
			m_failed = TRUE
			m_fail_reason = SCRASH2_FAILED_REPORTER_SPOOKED
		ENDIF

		RETURN
	ENDIF

	// Taxi checks when taxi approaching reporter
	IF existsTaxiLS = TRUE
	AND flagTaxiApproachingReporter = TRUE
		// Taxi is close to reporter, so any damage now will spook the reporter
		IF NOT IS_CAR_DEAD carTaxiLS	 
			// Car not dead, so check various types of damage
			// ...check for driver dead
			IF IS_CHAR_DEAD charTaxiDriverLS
				flagReporterSpookedAndFleeing = TRUE
			ENDIF

			// ...check for driver not in car
			IF NOT IS_CHAR_IN_CAR charTaxiDriverLS carTaxiLS
				flagReporterSpookedAndFleeing = TRUE
			ENDIF

			// ...check for taxi damaged by player
			IF HAS_CAR_BEEN_DAMAGED_BY_CHAR carTaxiLS scplayer
				// ...taxi has been damaged, but if the player is in a car at low speed, treat the
				//		damage as accidental and ignore it
				flagTempFlag = TRUE
				IF IS_CHAR_IN_ANY_CAR scplayer
					GET_CHAR_SPEED scplayer fTempFloat
					IF fTempFloat < 13.0000
						// ...low speed, so treat the damage as accidental
						flagTempFlag = FALSE
						CLEAR_CAR_LAST_DAMAGE_ENTITY carTaxiLS
					ENDIF
				ENDIF

				flagReporterSpookedAndFleeing = flagTempFlag
			ENDIF

			// ...check for player in taxi
			IF IS_CHAR_IN_CAR scplayer carTaxiLS
				flagReporterSpookedAndFleeing = TRUE
			ENDIF

			// If now spooked, set up the timer
			IF flagReporterSpookedAndFleeing = TRUE
				IF NOT IS_CHAR_IN_ANY_CAR charReporter
					// ...if reporter not in the taxi, let him flee a bit before failing
					TASK_SMART_FLEE_CHAR charReporter scplayer 9999.0 10000
				ENDIF

				// ...allow the reporter to flee a while before failing
				// NOTE: This also allows the 'is reporter dead' check to take place and display a better failure message
				timerReporterSpookedAndFleeing = m_mission_timer + 1000

				RETURN
			ENDIF
		ELSE		 
			// Car dead, so make reporter flee
			flagReporterSpookedAndFleeing = TRUE

			IF NOT IS_CHAR_IN_ANY_CAR charReporter
				// ...if reporter not in the taxi, let him flee a bit before failing
				TASK_SMART_FLEE_CHAR charReporter scplayer 9999.0 10000
			ENDIF

			// ...allow the reporter to flee a while before failing
			// NOTE: This also allows the 'is reporter dead' check to take place and display a better failure message
			timerReporterSpookedAndFleeing = m_mission_timer + 1000

			RETURN
		ENDIF
	ENDIF

	// Taxi checks before taxi approaching reporter
	// NOTE: Only applies to taxi created in the alpha position, because the beta position is always approaching reporter
	IF existsTaxiLS = TRUE
	AND flagTaxiApproachingReporter = FALSE
		// Taxi is close to reporter, so any damage now will require a replacement cab
		IF NOT IS_CAR_DEAD carTaxiLS
			// Car not dead, so check various types of damage
			flagTempFlag = FALSE

			// ...check for driver dead
			IF IS_CHAR_DEAD charTaxiDriverLS
				flagTempFlag = TRUE
			ENDIF

			// ...check for driver not in car
			IF NOT IS_CHAR_IN_CAR charTaxiDriverLS carTaxiLS
				flagTempFlag = TRUE
			ENDIF

			// ...check for taxi damaged by player
			IF HAS_CAR_BEEN_DAMAGED_BY_CHAR carTaxiLS scplayer
				flagTempFlag = TRUE
			ENDIF

			// ...check for player in taxi
			IF IS_CHAR_IN_CAR scplayer carTaxiLS
				flagTempFlag = TRUE
			ENDIF

			// If player has attacked the taxi, then create the second one instead at the beta position
			IF flagTempFlag = TRUE
				MARK_CAR_AS_NO_LONGER_NEEDED carTaxiLS
				MARK_CHAR_AS_NO_LONGER_NEEDED charTaxiDriverLS
				existsTaxiLS = FALSE
				flagTaxiLSCreationPositionAlpha = FALSE
				GOSUB SCrash2_Create_Taxi_LS
			ENDIF
		ELSE
			// ...car dead, so mark it as no longer needed and create a new one at the beta position
			MARK_CAR_AS_NO_LONGER_NEEDED carTaxiLS
			MARK_CHAR_AS_NO_LONGER_NEEDED charTaxiDriverLS
			existsTaxiLS = FALSE
			flagTaxiLSCreationPositionAlpha = FALSE
			GOSUB SCrash2_Create_Taxi_LS
		ENDIF
	ENDIF

//	IF existsTaxiLS = TRUE
//		IF IS_CAR_DEAD carTaxiLS
//		OR IS_CHAR_DEAD charTaxiDriverLS
//			MARK_CAR_AS_NO_LONGER_NEEDED carTaxiLS
//			MARK_CHAR_AS_NO_LONGER_NEEDED charTaxiDriverLS
//			existsTaxiLS = FALSE
//			flagTriggerTaxiLS = FALSE
//		ENDIF
//	ENDIF


	// Subgoals
	// --------

	// Make the reporter step forward
	IF m_goals = 1
		TASK_GO_STRAIGHT_TO_COORD charReporter 833.4493 -1374.2609 -1.5078 PEDMOVE_WALK -1
		m_goals++
	ENDIF


	// If the reporter has stepped forward then play a look-around anim
	IF m_goals = 2
		GET_SCRIPT_TASK_STATUS charReporter TASK_GO_STRAIGHT_TO_COORD m_status
		IF m_status = FINISHED_TASK
			// Play look-around anim (wait briefly to ensure it starts before checking if it has finished)
			TASK_PLAY_ANIM charReporter roadcross PED 4.0 FALSE FALSE FALSE FALSE -1
			timerReporter = m_mission_timer + 250

			m_goals++
		ENDIF
	ENDIF


/*	
	// Check if the look-around anim has ended
	IF m_goals = 3
		IF timerReporter < m_mission_timer
			IF NOT IS_CHAR_PLAYING_ANIM charReporter roadcross
				// Make the ped walk up the stairs
				OPEN_SEQUENCE_TASK nSeqTask
					TASK_GO_STRAIGHT_TO_COORD -1 829.4084 -1359.6791 -1.5006 PEDMOVE_WALK -2		// Foot of stairs
					TASK_GO_STRAIGHT_TO_COORD -1 836.5355 -1345.2378 6.1866 PEDMOVE_WALK -2			// Mid-stairs part 1
					TASK_GO_STRAIGHT_TO_COORD -1 835.9343 -1344.0736 6.1875 PEDMOVE_WALK -2			// Mid-stairs part 2
					TASK_GO_STRAIGHT_TO_COORD -1 824.1583 -1343.3123 12.5000 PEDMOVE_WALK -2		// Top of stairs
				CLOSE_SEQUENCE_TASK nSeqTask

				PERFORM_SEQUENCE_TASK charReporter nSeqTask
				CLEAR_SEQUENCE_TASK nSeqTask

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Check if the ped is performing the sequence
	IF m_goals = 4
		GET_SCRIPT_TASK_STATUS charReporter -1 m_status
		IF m_status = PERFORMING_TASK
			// ...task being performed
			m_goals++
		ENDIF
	ENDIF
*/

	// Check if the look-around anim has ended
	IF m_goals = 3
		IF timerReporter < m_mission_timer
			IF NOT IS_CHAR_PLAYING_ANIM charReporter roadcross
				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Maintain the walking sequence
	IF m_goals = 4
		GOSUB SCrash2_Update_Station_Walk
		IF statusStationWalk = SCRASH2_STATION_WALK_READY_TO_LOOK_AROUND
			m_goals++
		ENDIF
	ENDIF

	
	// Look-around
	IF m_goals = 5
		// Trigger the look-around anim
		TASK_PLAY_ANIM charReporter roadcross PED 4.0 FALSE FALSE FALSE FALSE -1
		timerReporter = m_mission_timer + 250

		m_goals++
	ENDIF


/*
	// Check if the look-around anim has finished
	IF m_goals = 6
		IF timerReporter < m_mission_timer
			IF NOT IS_CHAR_PLAYING_ANIM charReporter roadcross
				// Make the ped walk to the kerb
				TASK_GO_STRAIGHT_TO_COORD charReporter 803.7784 -1342.9255 12.5469 PEDMOVE_WALK -2

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Check if the reporter is at the kerb
	IF m_goals = 7
		GET_SCRIPT_TASK_STATUS charReporter TASK_GO_STRAIGHT_TO_COORD m_status
		IF m_status = FINISHED_TASK
			// Allow the taxi to be triggered (give the player some time to arrive)
			flagAllowTaxiLS = TRUE
			timerTaxiLS = m_mission_timer + 60000

			// Info: reporter is hailing a cab
			IF IS_CHAR_IN_ANY_CAR scplayer
				PRINT_NOW SCR2_20 10000 1
			ELSE
				PRINT_NOW SCR2_06 10000 1
			ENDIF

			m_goals++
		ENDIF
	ENDIF
*/


	// Check if the look-around anim has finished
	IF m_goals = 6
		IF timerReporter < m_mission_timer
			IF NOT IS_CHAR_PLAYING_ANIM charReporter roadcross
				statusStationWalk = SCRASH2_STATION_WALK_KERB_INIT
				statusStationWalkInit = SCRASH2_STATION_WALK_KERB_INIT
				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Maintain the walking sequence
	IF m_goals = 7
		GOSUB SCrash2_Update_Station_Walk
		IF statusStationWalk = SCRASH2_STATION_WALK_WAIT_FOR_TAXI
			// Allow the taxi to be triggered (give the player some time to arrive)
			flagAllowTaxiLS = TRUE
			timerTaxiLS = m_mission_timer + 60000

			// Info: reporter is hailing a cab
			IF IS_CHAR_IN_ANY_CAR scplayer
				PRINT_NOW SCR2_20 10000 1
			ELSE
				PRINT_NOW SCR2_06 10000 1
			ENDIF

			// Allow the reporter to look at the player if suspicious
			flagAllowTurnTowardsPlayer = TRUE

			m_goals++
		ENDIF
	ENDIF

	
	// Play the look-around anim
	IF m_goals = 8
		GOSUB SCrash2_Update_Station_Walk
		IF flagReporterSpooked = FALSE
			OPEN_SEQUENCE_TASK nSeqTask
				TASK_ACHIEVE_HEADING -1 90.0000
				TASK_PLAY_ANIM -1 roadcross PED 4.0 FALSE FALSE FALSE FALSE -1
			CLOSE_SEQUENCE_TASK nSeqTask

			PERFORM_SEQUENCE_TASK charReporter nSeqTask
			CLEAR_SEQUENCE_TASK nSeqTask

			timerReporter = m_mission_timer + 250

			m_goals++
		ENDIF
	ENDIF


	// Check if the look-around anim has finished and either trigger it again, or hail a taxi
	IF m_goals = 9
		GOSUB SCrash2_Update_Station_Walk
		IF flagReporterSpooked = FALSE
			IF timerReporter < m_mission_timer
				GET_SCRIPT_TASK_STATUS charReporter -1 m_status
				IF m_status = FINISHED_TASK
					// Is there a taxi approaching?
					IF flagTaxiApproachingReporter = TRUE
						// ...yes
						m_goals++
					ELSE
						// ...no, so look around again
						m_goals--
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Orientate, then hail cab
	// NOTE: If taxi approaching from secondary position then run to new position instead
	IF m_goals = 10
		IF flagTaxiLSCreationPositionAlpha = TRUE
			// ...approaching at alpha position, so turn and hail it
			OPEN_SEQUENCE_TASK nSeqTask
				TASK_ACHIEVE_HEADING -1 180.0000
				TASK_PLAY_ANIM -1 Hiker_Pose MISC 4.0 FALSE FALSE FALSE TRUE -1
			CLOSE_SEQUENCE_TASK nSeqTask

			PERFORM_SEQUENCE_TASK charReporter nSeqTask
			CLEAR_SEQUENCE_TASK nSeqTask
		ELSE
			// ...approaching from beta position, so run to the other side of the station
			TASK_GO_STRAIGHT_TO_COORD charReporter 821.7603 -1334.6827 12.5391 PEDMOVE_RUN -2
		ENDIF

		m_goals++
	ENDIF


	// Wait for the cab to arrive
	IF m_goals = 11
		GOSUB SCrash2_Update_Station_Walk
		IF flagReporterSpooked = FALSE
			flagTempFlag = FALSE

			// ...check if taxi stopped at alpha position
			IF flagTaxiLSCreationPositionAlpha = TRUE
			AND LOCATE_CAR_3D carTaxiLS 803.7784 -1342.9255 12.5469 7.0 7.0 2.0 FALSE
				flagTempFlag = TRUE
			ENDIF

			// ...check if taxi stopped at beta position
			IF flagTaxiLSCreationPositionAlpha = FALSE
			AND LOCATE_CAR_3D carTaxiLS 824.4391 -1330.7069 12.4174 7.0 7.0 2.0 FALSE
				flagTempFlag = TRUE
			ENDIF

			IF flagTempFlag = TRUE
			AND IS_CAR_STOPPED carTaxiLS
				// Reporter gets into car
				TASK_ENTER_CAR_AS_PASSENGER charReporter carTaxiLS -2 2
				flagReporterGettingIntoCab = TRUE

				m_goals++
			ENDIF
		ELSE
			// Cancel the hiker pose by playing the Idle stance
			IF IS_CHAR_PLAYING_ANIM charReporter Hiker_Pose
				IF timerReporterAnim < m_mission_timer
					TASK_PLAY_ANIM charReporter roadcross PED 4.0 FALSE FALSE FALSE FALSE 100
					timerReporterAnim = m_mission_timer + 250
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Wait for the reporter to get in the taxi
	IF m_goals = 12
		GET_SCRIPT_TASK_STATUS charReporter TASK_ENTER_CAR_AS_PASSENGER m_status
		IF m_status = FINISHED_TASK
			SET_TAXI_LIGHTS carTaxiLS OFF

			// Stop updating the walking suspicion areas
			nSuspicionWalkArea = SCRASH2_SUSPICION_AREA_NONE

			// Mark this as the Reporter's car
			carReporter = carTaxiLS

			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates for stage
	// ----------------------------

	// Load the taxi
	IF loadedTaxiLS = FALSE
		REQUEST_MODEL	TAXI
		LOAD_ALL_MODELS_NOW

		IF HAS_MODEL_LOADED TAXI
			loadedTaxiLS = TRUE
		ENDIF
	ENDIF

	// Check if it is time to get rid of the train
	IF existsTrain = 1
		IF timerTrain < m_mission_timer
			IF NOT IS_CAR_DEAD carTrain
				SET_TRAIN_CRUISE_SPEED carTrain 30.0
			ENDIF

			MARK_MISSION_TRAINS_AS_NO_LONGER_NEEDED
			existsTrain = 0
		ENDIF
	ENDIF


	// Check if it is time to trigger the taxi
	IF flagAllowTaxiLS = TRUE
	AND flagTriggerTaxiLS = FALSE
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 812.4495 -1343.4883 12.5320 80.0 80.0 20.0 FALSE
			// ...trigger the taxi
			flagTriggerTaxiLS = TRUE
			GOSUB SCrash2_Choose_Taxi_LS_Creation_Position
			GOSUB SCrash2_Create_Taxi_LS
			SET_TAXI_LIGHTS carTaxiLS ON
		ELSE
			IF timerTaxiLS < m_mission_timer
				m_failed = 1
				m_fail_reason = SCRASH2_FAILED_LOST_THE_REPORTER
				RETURN
			ENDIF
		ENDIF
	ENDIF


	// Check if the taxi is approaching the reporter
	IF flagTaxiApproachingReporter = FALSE
	AND existsTaxiLS = TRUE
		IF LOCATE_CAR_3D carTaxiLS 796.9285 -1374.8674 12.3949 10.0 10.0 2.0 FALSE
			flagTaxiApproachingReporter = TRUE
		ENDIF
	ENDIF

	// Maintain Suspicion Level
	GOSUB SCrash2_Update_Walking_Suspicion_Area
	GOSUB SCrash2_Update_Walking_Spook_Meter
	IF m_failed = TRUE
		RETURN
	ENDIF


	// exit
	IF m_goals = 99
		GOSUB SCrash2_next_stage
	ENDIF

RETURN


// ****************************************
// STAGE 5: Reporter Goes To Meeting Place

SCrash2_Stage_ReporterGoesToMeetingPlace:

   	// Initialisation for this stage
   	IF m_goals = 0
		// Make the taxi drive to the destination

		// Set up the destination on the pier
		xposDestination = 366.4698
		yposDestination = -2030.6428
		zposDestination = 6.6582

		// Choose the first waypoint destination
		nTaxiChanceOfBurstOfSpeed	= 0
// REMOVE TAXI ENFORCED STOPS
//		nTaxiChanceOfStopping		= 1
// END REMOVE TAXI ENFORCED STOPS
		nTaxiNumberOfWaypoints		= 0
		nTaxiWaypointID				= SCRASH2_TAXI_WAYPOINT_NONE
		GOSUB SCrash2_Choose_Taxi_Waypoint

		// Don't allow the car to drive on the wrong side of the road
		SET_CAR_CAN_GO_AGAINST_TRAFFIC carTaxiLS FALSE

		// Spook-o-meter leeway
		timerJustStartedDrivingLeeway = m_mission_timer + 3000
		flagJustStartedDriving = TRUE

		flagReporterOnPier				= FALSE
		flagPlayerOnPier				= FALSE
		flagPlayerAheadOfReporterOnPier	= FALSE
		flagDisplayedTooClose			= FALSE

		// Allow the taxi to warp if it becomes stuck
		ADD_STUCK_CAR_CHECK_WITH_WARP carTaxiLS 2.0 30000 TRUE TRUE TRUE 20

		// Instructions: Follow the taxi
		PRINT_NOW SCR2_07 5000 1
//		PRINT SCR2_17 5000 1

		m_goals++
	ENDIF


	// Failure conditions
	// ------------------

	// ...reporter dead
	IF IS_CHAR_DEAD charReporter
		m_failed = 1
		m_fail_reason = SCRASH2_FAILED_REPORTER_DEAD
		RETURN
	ENDIF

	// ...reporter damaged
	IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR charReporter scplayer
		m_failed = 1
		m_fail_reason = SCRASH2_FAILED_REPORTER_SPOOKED
		RETURN
	ENDIF

	// If the player has attacked the taxi close to the reporter, check for the timeout before mission failed
	IF flagReporterSpookedAndFleeing = TRUE
		IF timerReporterSpookedAndFleeing < m_mission_timer
			m_failed = TRUE
			m_fail_reason = SCRASH2_FAILED_REPORTER_SPOOKED
		ENDIF

		RETURN
	ENDIF

	// Taxi checks
	IF NOT IS_CAR_DEAD carTaxiLS	 
		// Car not dead, so check various types of damage
		// ...check for driver dead
		IF IS_CHAR_DEAD charTaxiDriverLS
			flagReporterSpookedAndFleeing = TRUE
		ENDIF

		// ...check for driver not in car
		IF NOT IS_CHAR_IN_CAR charTaxiDriverLS carTaxiLS
			flagReporterSpookedAndFleeing = TRUE
		ENDIF

		// ...check for taxi damaged by player
		IF HAS_CAR_BEEN_DAMAGED_BY_CHAR carTaxiLS scplayer
			// ...taxi has been damaged, but if the player is in a car at low speed, treat the
			//		damage as accidental and ignore it
			flagTempFlag = TRUE
			IF IS_CHAR_IN_ANY_CAR scplayer
				GET_CHAR_SPEED scplayer fTempFloat
				IF fTempFloat < 13.0000
					// ...low speed, so treat the damage as accidental
					flagTempFlag = FALSE
					CLEAR_CAR_LAST_DAMAGE_ENTITY carTaxiLS
				ENDIF
			ENDIF

			flagReporterSpookedAndFleeing = flagTempFlag
		ENDIF

		// ...check for player in taxi
		IF IS_CHAR_IN_CAR scplayer carTaxiLS
			flagReporterSpookedAndFleeing = TRUE
		ENDIF

		// If now spooked, set up the timer
		IF flagReporterSpookedAndFleeing = TRUE
			IF IS_CHAR_IN_ANY_CAR charReporter
				// ...if reporter is in the taxi, fail immediately
				m_failed = TRUE
				m_fail_reason = SCRASH2_FAILED_REPORTER_SPOOKED
			ELSE
				// ...if reporter not in the taxi, let him flee a bit before failing
				timerReporterSpookedAndFleeing = m_mission_timer + 1000
				TASK_SMART_FLEE_CHAR charReporter scplayer 9999.0 10000
			ENDIF

			RETURN
		ENDIF
	ELSE		 
		// Car dead, so make reporter flee
		flagReporterSpookedAndFleeing = TRUE
		timerReporterSpookedAndFleeing = m_mission_timer + 1000
		TASK_SMART_FLEE_CHAR charReporter scplayer 9999.0 10000
		RETURN
	ENDIF

//	IF existsTaxiLS = TRUE
//	AND m_goals > 0
//		IF IS_CAR_DEAD carTaxiLS
			// ...the taxi is dead, so make the reporter go to his destination using any means
//			existsTaxiLS = FALSE

//			TASK_CAR_DRIVE_TO_COORD charReporter -1 xposDestination yposDestination zposDestination 20.0 MODE_ACCURATE 0 DRIVINGMODE_AVOIDCARS
//		ENDIF
//	ENDIF


	// Subgoals
	// --------

	// Check if the reporter has arrived at the destination, but only if the player isn't ahead of the reporter
	IF m_goals = 1
		IF NOT statusSuspicion = SCRASH2_SUSPICION_STATUS_TOO_CLOSE
		AND NOT statusSuspicion = SCRASH2_SUSPICION_STATUS_PLAYER_AHEAD
			IF LOCATE_CHAR_ANY_MEANS_3D charReporter xposDestination yposDestination zposDestination 4.0 4.0 4.0 FALSE
				// Don't need the suspicion level anymore
				CLEAR_ONSCREEN_COUNTER g_SCrash2_spookKM

				m_goals = 99
			ENDIF
		ENDIF
	ENDIF


	// Continuous updates for stage
	// ----------------------------

	// Check if the reporter is on the pier
	IF flagReporterOnPier = FALSE
		IF LOCATE_CHAR_ANY_MEANS_3D charReporter 369.3609 -1671.8887 31.8110 14.0 6.0 4.0 FALSE
			flagReporterOnPier = TRUE
			SET_CAR_DENSITY_MULTIPLIER 0.0
			SET_PED_DENSITY_MULTIPLIER 0.0
		ENDIF
	ENDIF

	// Maintain the player's 'on pier' status
	IF flagPlayerOnPier = FALSE
		// ...player not on pier, so check if he is now
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 369.3609 -1671.8887 31.8110 14.0 4.0 4.0 FALSE
			flagPlayerOnPier = TRUE
		ENDIF
	ELSE
		// ...player on pier, so check if he has left it
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 370.2572 -1656.7902 31.7201 14.0 4.0 4.0 FALSE
			flagPlayerOnPier = FALSE
		ENDIF
	ENDIF

	// Maintain Suspicion Level
	GOSUB SCrash2_Update_Driving_Spook_Meter
	IF m_failed = TRUE
		RETURN
	ENDIF

	// Display assassination text
	IF flagDisplayedAssassinationText = FALSE
	AND flagReporterOnPier = TRUE
	AND flagPlayerOnPier = TRUE
		flagDisplayedAssassinationText = TRUE
		PRINT_NOW SCR2_18 10000 1
	ENDIF

	// Keep tabs on the taxi's progress through the waypoints
	GOSUB SCrash2_Update_Taxi_Waypoint


	// exit
	IF m_goals = 99
		GOSUB SCrash2_next_stage
	ENDIF

RETURN


// ****************************************
// STAGE 6: Reporter Meets the Target

SCrash2_Stage_ReporterMeetsTheTarget:

	IF IS_CHAR_DEAD charReporter
		flagReporterAssassinated = TRUE
		REMOVE_BLIP blipReporter
	ENDIF

	IF existsTarget = TRUE
		IF IS_CHAR_DEAD charTarget
			flagTargetAssassinated = TRUE
			REMOVE_BLIP blipTarget
		ENDIF
	ENDIF


	// Check to see if both Reporter and Target are dead
	IF flagReporterAssassinated = TRUE
	AND flagTargetAssassinated = TRUE
		m_goals = 99
	ENDIF


   	// Initialisation for this stage
   	IF m_goals = 0
		GOSUB SCrash2_Create_Target
		ADD_BLIP_FOR_CHAR charTarget blipTarget

		// Make the Target move to meet the reporter
		OPEN_SEQUENCE_TASK nSeqTask
			TASK_GO_STRAIGHT_TO_COORD	-1 359.4136 -2036.9431 6.8360 PEDMOVE_WALK -2		// Midpoint
			TASK_GO_STRAIGHT_TO_COORD	-1 362.2674 -2035.8441 6.8360 PEDMOVE_WALK -2		// Front of Kiosk
			TASK_ACHIEVE_HEADING		-1 270.0000
		CLOSE_SEQUENCE_TASK nSeqTask

		PERFORM_SEQUENCE_TASK charTarget nSeqTask
		CLEAR_SEQUENCE_TASK nSeqTask

		// Make the reporter move to meet the target
		OPEN_SEQUENCE_TASK nSeqTask
			TASK_GO_STRAIGHT_TO_COORD	-1 363.1874 -2035.8441 6.8360 PEDMOVE_WALK -2		// Front of Kiosk
			TASK_ACHIEVE_HEADING		-1 90.0000
		CLOSE_SEQUENCE_TASK nSeqTask

		PERFORM_SEQUENCE_TASK charReporter nSeqTask
		CLEAR_SEQUENCE_TASK nSeqTask

		// Let the meeting take place for some time before quitting
		timerMeeting = m_mission_timer + 180000

		// Clear any flags and states for this stage
		flagPlayerDetectedOnPier	= FALSE
		flagMeetingOver				= FALSE

		aiReporterOnPier	= SCRASH2_AI_REPORTER_ON_PIER_NOT_INITIALISED
		aiTarget			= SCRASH2_AI_TARGET_NOT_INITIALISED

		// Need a shooting delay because the code registers the player shooting before it registers target or reporter death
		timerShootingOnPierReactionDelay = 0
		flagPerformingShootingOnPierReactionDelay = FALSE

		// Instructions: Assassinate them both
		PRINT_NOW SCR2_19 10000 1

		IF displayed_sniper_help_text = FALSE
			// ...sniper rifle text not previously displayed
			flagDisplayedSniperRifleHelp	= FALSE
			flagDisplayedSniperRifleHelp2	= FALSE
			timerHelpText					= m_mission_timer + 10000
			displayed_sniper_help_text		= TRUE
		ELSE
			// ...sniper rifle text already displayed in another mission
			flagDisplayedSniperRifleHelp	= TRUE
			flagDisplayedSniperRifleHelp2	= TRUE
			timerHelpText					= 0
		ENDIF
	
		m_goals++
	ENDIF


	// Make sure both sequences have started
	IF m_goals = 1
	   	IF NOT IS_CHAR_DEAD charReporter
	   	AND NOT IS_CHAR_DEAD charTarget
			GET_SCRIPT_TASK_STATUS charReporter -1 m_status
			IF m_status = PERFORMING_TASK
				// ...reporter performing task
				GET_SCRIPT_TASK_STATUS charTarget -1 m_status
				IF m_status = PERFORMING_TASK
					// ...target performing task
					m_goals++
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Wait for both sequences to stop
	IF m_goals = 2
	   	IF NOT IS_CHAR_DEAD charReporter
	   	AND NOT IS_CHAR_DEAD charTarget
			GET_SCRIPT_TASK_STATUS charReporter -1 m_status
			IF m_status = FINISHED_TASK
				// ...reporter finished task
				GET_SCRIPT_TASK_STATUS charTarget -1 m_status
				IF m_status = FINISHED_TASK
					// ...target finished task
					// Switch off their collisions and get them in place for the anim
//					SET_CHAR_COLLISION charReporter	FALSE
//					SET_CHAR_COLLISION charTarget	FALSE

					TASK_CHAR_SLIDE_TO_COORD charTarget		362.2674 -2035.8441 6.8360 270.0000 4.0
					TASK_CHAR_SLIDE_TO_COORD charReporter	363.1874 -2035.8441 6.8360 90.0000 4.0

					// Wait a short period of time before checking if the tasks have finished
					timerReporter = m_mission_timer + 250

					m_goals++
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Make them shake hands
	IF m_goals = 3
	   	IF NOT IS_CHAR_DEAD charReporter
	   	AND NOT IS_CHAR_DEAD charTarget
			IF timerReporter < m_mission_timer
				// Make them shake hands
				TASK_PLAY_ANIM charReporter	prtial_hndshk_biz_01 GANGS 4.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM charTarget	prtial_hndshk_biz_01 GANGS 4.0 FALSE FALSE FALSE FALSE -1

				// Wait a short period to make sure the anims start
				timerReporter = m_mission_timer + 250

				// Get rid of the taxi
				MARK_CHAR_AS_NO_LONGER_NEEDED	charTaxiDriverLS
				MARK_CAR_AS_NO_LONGER_NEEDED	carTaxiLS

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Wait for them to stop shaking hands
	IF m_goals = 4
	   	IF NOT IS_CHAR_DEAD charReporter
	   	AND NOT IS_CHAR_DEAD charTarget
			IF timerReporter < m_mission_timer
				IF NOT IS_CHAR_PLAYING_ANIM charReporter prtial_hndshk_biz_01
				AND NOT IS_CHAR_PLAYING_ANIM charTarget prtial_hndshk_biz_01
					// Switch on their collisions again
//					SET_CHAR_COLLISION charReporter	TRUE
//					SET_CHAR_COLLISION charTarget	TRUE

					m_goals++
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Make the reporter talk for a short random period of time
	IF m_goals = 5
	   	IF NOT IS_CHAR_DEAD charReporter
	   	AND NOT IS_CHAR_DEAD charTarget
			TASK_PLAY_ANIM charReporter	IDLE_chat PED 4.0 TRUE FALSE FALSE FALSE -1
			GENERATE_RANDOM_INT_IN_RANGE 2000 5000 nTempInt
			timerReporter = m_mission_timer + nTempInt

			m_goals++
		ENDIF
	ENDIF


	// Check if the reporter should stop talking
	IF m_goals = 6
	   	IF NOT IS_CHAR_DEAD charReporter
	   	AND NOT IS_CHAR_DEAD charTarget
			IF timerReporter < m_mission_timer
				CLEAR_CHAR_TASKS charReporter

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Make the Target talk for a longer period of time
	IF m_goals = 7
	   	IF NOT IS_CHAR_DEAD charReporter
	   	AND NOT IS_CHAR_DEAD charTarget
			TASK_PLAY_ANIM charTarget IDLE_chat PED 4.0 TRUE FALSE FALSE FALSE -1
			GENERATE_RANDOM_INT_IN_RANGE 5000 10000 nTempInt
			timerReporter = m_mission_timer + nTempInt

			m_goals++
		ENDIF
	ENDIF


	// Check if the Target should stop talking
	IF m_goals = 8
	   	IF NOT IS_CHAR_DEAD charReporter
	   	AND NOT IS_CHAR_DEAD charTarget
			IF timerReporter < m_mission_timer
				CLEAR_CHAR_TASKS charTarget

				// Repeat from goal 5 (Reporter talking)
				m_goals = 5
			ENDIF
		ENDIF
	ENDIF


	// Quit the meeting
	IF m_goals = 20
		// Make the Target walk out of sight
		IF NOT IS_CHAR_DEAD charTarget
			OPEN_SEQUENCE_TASK nSeqTask
				TASK_GO_STRAIGHT_TO_COORD	-1 361.2376 -2047.8412 6.8360 PEDMOVE_WALK -2		// Near Grill
				TASK_GO_STRAIGHT_TO_COORD	-1 354.2802 -2049.6958 6.8360 PEDMOVE_WALK -2		// Corner of Grill
				TASK_GO_STRAIGHT_TO_COORD	-1 354.8605 -2057.5879 6.8360 PEDMOVE_WALK -2		// Out of sight
			CLOSE_SEQUENCE_TASK nSeqTask

			PERFORM_SEQUENCE_TASK charTarget nSeqTask
			CLEAR_SEQUENCE_TASK nSeqTask
		ENDIF

		// Make the reporter move to meet the target
		IF NOT IS_CHAR_DEAD charReporter
			OPEN_SEQUENCE_TASK nSeqTask
				TASK_GO_STRAIGHT_TO_COORD	-1 361.2376 -2047.8412 6.8360 PEDMOVE_WALK -2		// Near Grill
				TASK_GO_STRAIGHT_TO_COORD	-1 354.2802 -2049.6958 6.8360 PEDMOVE_WALK -2		// Corner of Grill
				TASK_GO_STRAIGHT_TO_COORD	-1 354.8630 -2060.0498 6.8360 PEDMOVE_WALK -2		// Out of sight
			CLOSE_SEQUENCE_TASK nSeqTask

			PERFORM_SEQUENCE_TASK charReporter nSeqTask
			CLEAR_SEQUENCE_TASK nSeqTask
		ENDIF

		// Info: The Meeting is over
		PRINT_NOW SCR2_05 10000 1

		m_goals++
	ENDIF


	// Make sure both sequences have started
	IF m_goals = 21
	   	IF NOT IS_CHAR_DEAD charReporter
	   	AND NOT IS_CHAR_DEAD charTarget
			GET_SCRIPT_TASK_STATUS charReporter -1 m_status
			IF m_status = PERFORMING_TASK
				// ...reporter performing task
				GET_SCRIPT_TASK_STATUS charTarget -1 m_status
				IF m_status = PERFORMING_TASK
					// ...target performing task
					m_goals++
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Wait for both sequences to stop then fail mission
	IF m_goals = 22
	   	IF NOT IS_CHAR_DEAD charReporter
	   	AND NOT IS_CHAR_DEAD charTarget
			GET_SCRIPT_TASK_STATUS charReporter -1 m_status
			IF m_status = FINISHED_TASK
				// ...reporter finished task
				GET_SCRIPT_TASK_STATUS charTarget -1 m_status
				IF m_status = FINISHED_TASK
					// ...target finished task
					m_failed = TRUE
					m_fail_reason = SCRASH2_FAILED_MEETING_OVER

					REMOVE_CHAR_ELEGANTLY charReporter
					REMOVE_CHAR_ELEGANTLY charTarget
					RETURN
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Activate Reporter and Target AI
	IF m_goals = 30
//		aiReporterOnPier	= SCRASH2_AI_REPORTER_ON_PIER_COWER
		aiReporterOnPier	= SCRASH2_AI_REPORTER_ON_PIER_FLEE
		aiTarget			= SCRASH2_AI_TARGET_KILL_PLAYER

		// Clear Reporter and Target current tasks
		IF NOT IS_CHAR_DEAD charReporter
			CLEAR_CHAR_TASKS charReporter
		ENDIF

		IF NOT IS_CHAR_DEAD charTarget
			CLEAR_CHAR_TASKS charTarget
		ENDIF

	   	// Get rid of the taxi
	   	MARK_CHAR_AS_NO_LONGER_NEEDED	charTaxiDriverLS
	   	MARK_CAR_AS_NO_LONGER_NEEDED	carTaxiLS

		m_goals++
	ENDIF


	// Update Reporter and Target AI
	// NOTE: Never leave this action. Continuous checks will test for success or failure.
	IF m_goals = 31
		GOSUB SCrash2_Update_AI_ReporterOnPier
		GOSUB SCrash2_Update_AI_Target
	ENDIF



	// Continuous updates for stage
	// ----------------------------

	// Check to see if the player has been detected
	IF flagPlayerDetectedOnPier = FALSE
		// ...check if the reporter has been assassinated
		IF flagReporterAssassinated = TRUE
			flagPlayerDetectedOnPier = TRUE
			PRINT_NOW SCR2_10 10000 1
		ENDIF

		// ...check if the target has been assassinated
		IF flagPlayerDetectedOnPier = FALSE
			IF flagTargetAssassinated = TRUE
				flagPlayerDetectedOnPier = TRUE
				PRINT_NOW SCR2_11 10000 1
			ENDIF
		ENDIF

		// ...check if the reporter was damaged by the player
		IF flagPlayerDetectedOnPier = FALSE
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR charReporter scplayer
				flagPlayerDetectedOnPier = TRUE
				PRINT_NOW SCR2_14 10000 1
			ENDIF
		ENDIF

		// ...check if the target was damaged by the player
		IF flagPlayerDetectedOnPier = FALSE
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR charTarget scplayer
				flagPlayerDetectedOnPier = TRUE
				PRINT_NOW SCR2_15 10000 1
			ENDIF
		ENDIF

		// ...check if the player has fired a gun on the pier
		IF flagPlayerDetectedOnPier = FALSE
		AND flagPerformingShootingOnPierReactionDelay = FALSE
			IF IS_CHAR_SHOOTING_IN_AREA scplayer 349.8409 -2088.7983 409.7505 -1869.8873 FALSE
				// ...the shot registers before the result of the shot, so delay this reaction
				//		to allow the result (ie: ped death) to register firsrt
				flagPerformingShootingOnPierReactionDelay = TRUE
				timerShootingOnPierReactionDelay = m_mission_timer + 1000
			ENDIF
		ENDIF

		// ...check if the shooting delay has timed out
		IF flagPlayerDetectedOnPier = FALSE
		AND flagPerformingShootingOnPierReactionDelay = TRUE
			IF timerShootingOnPierReactionDelay < m_mission_timer
				// ...register the shot
				flagPlayerDetectedOnPier = TRUE
				PRINT_NOW SCR2_12 10000 1
			ENDIF
		ENDIF

		// ...check if the player is very close to the target
		IF flagPlayerDetectedOnPier = FALSE
			GET_CHAR_COORDINATES charTarget	xposTemp	yposTemp	zposTemp
			GET_CHAR_COORDINATES scplayer	xposTemp2	yposTemp2	zposTemp2
			GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempFloat
			IF fTempFloat < 30.0
				flagPlayerDetectedOnPier = TRUE
				PRINT_NOW SCR2_13 10000 1
			ENDIF
		ENDIF

		// If the player was detected on this frame, display a message
		IF flagPlayerDetectedOnPier = TRUE
			m_goals = 30
		ENDIF
	ENDIF


	// Check to see if the meeting time is over
	IF flagPlayerDetectedOnPier = FALSE
	AND flagMeetingOver = FALSE
		IF timerMeeting < m_mission_timer
			flagMeetingOver = TRUE
			m_goals = 20
		ENDIF
	ENDIF


	// Check to see if the conditions for failure have been met
	IF flagPlayerDetectedOnPier = TRUE
		// ...is the reporter too far away?
		IF flagReporterAssassinated = FALSE
//		AND aiReporterOnPier = SCRASH2_AI_REPORTER_ON_PIER_FLEEING
			// ...has the Reporter been lost
			// NOTE: Don't let the reporter be lost until he is much closer to the pier entrance
			IF NOT IS_CHAR_IN_AREA_2D charReporter 349.8409 -2088.7983 409.7505 -1869.8873 FALSE
				GET_CHAR_COORDINATES charReporter	xposTemp	yposTemp	zposTemp
				GET_CHAR_COORDINATES scplayer		xposTemp2	yposTemp2	zposTemp2
				GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempFloat
				IF fTempFloat > 200.0
					m_failed = TRUE
					m_fail_reason = SCRASH2_FAILED_REPORTER_ESCAPED
					RETURN
				ENDIF
			ENDIF
		ENDIF

		// ...is the target too far away?
		IF flagTargetAssassinated = FALSE
//		AND aiTarget = SCRASH2_AI_TARGET_FLEEING
			// ...has the Target been lost
			// NOTE: Don't let the Target be lost until he is much closer to the pier entrance
			IF NOT IS_CHAR_IN_AREA_2D charTarget 349.8409 -2088.7983 409.7505 -1869.8873 FALSE
				GET_CHAR_COORDINATES charTarget	xposTemp	yposTemp	zposTemp
				GET_CHAR_COORDINATES scplayer  	xposTemp2	yposTemp2	zposTemp2
				GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempFloat
				IF fTempFloat > 200.0
					m_failed = TRUE
					m_fail_reason = SCRASH2_FAILED_TARGET_ESCAPED
					RETURN
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Sniper Help Text
	IF flagDisplayedSniperRifleHelp = FALSE
		IF timerHelpText < m_mission_timer

			PRINT_HELP_FOREVER SNIPER1  
			
			flagDisplayedSniperRifleHelp = TRUE
			timerHelpText = m_mission_timer + 15000
		ENDIF
	ENDIF

	IF flagDisplayedSniperRifleHelp = TRUE
		IF flagDisplayedSniperRifleHelp2 = FALSE
			IF timerHelpText < m_mission_timer
				CLEAR_HELP
				PRINT_HELP_FOREVER SNIPER2 
				flagDisplayedSniperRifleHelp2 = TRUE
				timerHelpText = m_mission_timer + 10000
			ENDIF
		ENDIF
	ENDIF

	IF flagDisplayedSniperRifleHelp2 = TRUE
		IF timerHelpText > 0
			IF timerHelpText < m_mission_timer
				CLEAR_HELP
				timerHelpText = 0
			ENDIF
		ENDIF
	ENDIF


	// exit
	IF m_goals = 99
		GOSUB SCrash2_next_stage
	ENDIF

RETURN





// *************************************************************************************************************
//										MISSION SPECIFIC GOSUBS  
// *************************************************************************************************************


// *************************************************************************************************************
//								          SNIPER RIFLE PICKUP
// *************************************************************************************************************

// ****************************************
// Create Sniper Rifle
SCrash2_Create_SniperRifle:

   	// Generate Sniper Rifle pickup
   	xposTemp = -2063.2250
   	yposTemp = 258.3273
   	zposTemp = 35.3958

   	CREATE_PICKUP_WITH_AMMO SNIPER PICKUP_ONCE 5 xposTemp yposTemp zposTemp pickupSniperRifle
   	ADD_BLIP_FOR_PICKUP pickupSniperRifle blipSniperRifle

RETURN



// *************************************************************************************************************
//								       Get to the Train Station
// *************************************************************************************************************


// ****************************************
// Create Train
SCrash2_Create_Train:

//	xposTemp = -1942.3918
//	yposTemp = 175.7647
//	zposTemp = 24.7109
	xposTemp = -1937.3115
	yposTemp = 221.1162
	zposTemp = 23.8414

	// FALSE is for the direction of travel - clockwise or anti-clockwise
	CLEAR_AREA xposTemp yposTemp zposTemp 60.0 TRUE
	CREATE_MISSION_TRAIN 11 xposTemp yposTemp zposTemp FALSE carTrain

	existsTrain = 1

RETURN


// ****************************************
// Create Reporter in SF
SCrash2_Create_Reporter_SF:

	xposTemp = -1939.5200
	yposTemp = 132.8362
	zposTemp = 25.2734
	headTemp = 90.4279

	CLEAR_AREA xposTemp yposTemp zposTemp 1.0 TRUE
	CREATE_CHAR	PEDTYPE_CIVMALE WMYBU xposTemp yposTemp zposTemp charReporter
	SET_CHAR_HEADING charReporter headTemp
	SET_CHAR_IS_TARGET_PRIORITY charReporter TRUE

	existsReporter = 1

RETURN


// *************************************************************************************************************
//								             Follow the Train
// *************************************************************************************************************

// ****************************************
// Calculate Train Distance From Player
SCrash2_Calculate_Train_Distance_From_Player:

	IF flagTrainArrivedAtLS = 1
		RETURN
	ENDIF

	// Only do this every 10 frames
	IF NOT m_frame_num = 5
		RETURN
	ENDIF

	// Calculate the straight -line distance between the train and the player
	GET_CAR_COORDINATES carTrain xposTemp yposTemp zposTemp
	GET_CHAR_COORDINATES scplayer xposTemp2 yposTemp2 zposTemp2

	xloTemp = xposTemp - xposTemp2
	yloTemp = yposTemp - yposTemp2

	xhiTemp = xloTemp * xloTemp
	yhiTemp = yloTemp * yloTemp

	fTempFloat = xhiTemp + yhiTemp
	SQRT fTempFloat fTempFloat2

	// Convert the distance to an int
	nTrainDistanceFromPlayer =# fTempFloat2

RETURN


// ****************************************
// Calculate Train Distance From LS Station
SCrash2_Calculate_Train_Distance_From_LS:

	IF flagTrainArrivedAtLS = 1
		nTrainDistanceFromLS = 0
		RETURN
	ENDIF

	// Only do this every 10 frames
	IF NOT m_frame_num = 3
		RETURN
	ENDIF

	// Calculate the straight -line distance between the train and the station
	GET_CAR_COORDINATES carTrain xposTemp yposTemp zposTemp

	xloTemp = xposTemp - xposDestination
	yloTemp = yposTemp - yposDestination

	xhiTemp = xloTemp * xloTemp
	yhiTemp = yloTemp * yloTemp

	fTempFloat = xhiTemp + yhiTemp
	SQRT fTempFloat fTempFloat2

	// Convert the distance to an int
	nTrainDistanceFromLS =# fTempFloat2

RETURN


// ****************************************
// Calculate Player Distance From LS
SCrash2_Calculate_Player_Distance_From_LS:

	// Only do this every 10 frames
	IF NOT m_frame_num = 1
		RETURN
	ENDIF

	// Calculate the straight -line distance between the train and the station
	GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp

	xloTemp = xposTemp - xposDestination
	yloTemp = yposTemp - yposDestination

	xhiTemp = xloTemp * xloTemp
	yhiTemp = yloTemp * yloTemp

	fTempFloat = xhiTemp + yhiTemp
	SQRT fTempFloat fTempFloat2

	// Convert the distance to an int
	nPlayerDistanceFromLS =# fTempFloat2

RETURN


// ****************************************
// Update Train Cruise Speed
SCrash2_Update_Train_Cruise_Speed:

	IF flagTrainArrivedAtLS = 1
		RETURN
	ENDIF

	// Only do this every 10 frames
	IF NOT m_frame_num = 7
		RETURN
	ENDIF

// REMOVE VARIABLE TRAIN SPEED
/*
	// Set the cruise speed based on the current train/player information
	// Only slow the train down if it is closer to LS than the Player
	IF nTrainDistanceFromLS <= nPlayerDistanceFromLS
		// If the train is really far in front and the player is still in SF,
		// then let it move at a decent speed to get the mission finished
		IF nTrainDistanceFromPlayer > SCRASH2_FAILURE_DISTANCE
		AND IS_CHAR_IN_ZONE scplayer SF
			// ...give it a decent speed and a speed boost
			fTempFloat = 100.0
			GOTO SCRASH2_SPEED_BOOST
		ENDIF

		// Try to keep the train about 700m away
		IF nTrainDistanceFromPlayer > 700
			// ...really far away, so slow the train right down
			fTempFloat = 5.0
			GOTO SCRASH2_SET_SPEED
		ENDIF

		IF nTrainDistanceFromPlayer > 500
			fTempFloat = 15.0
			GOTO SCRASH2_SET_SPEED
		ENDIF

		IF nTrainDistanceFromPlayer > 300
			fTempFloat = 30.0
			GOTO SCRASH2_SET_SPEED
		ENDIF

		IF nTrainDistanceFromPlayer > 200
			fTempFloat = 40.0
			GOTO SCRASH2_SPEED_BOOST
		ENDIF

		IF nTrainDistanceFromPlayer > 140
			fTempFloat = 50.0
			GOTO SCRASH2_SPEED_BOOST
		ENDIF

		IF nTrainDistanceFromPlayer > 80
			fTempFloat = 60.0
			GOTO SCRASH2_SPEED_BOOST
		ENDIF

		// Player is really close, so let the train go really fast
		fTempFloat = 100.0

		// Jump to here if the train may get a speed boost
		SCRASH2_SPEED_BOOST:

		// If the player is this close behind, then give the train a boost of speed
		GET_CAR_SPEED carTrain fTempFloat2
		IF fTempFloat2 < fTempFloat
			fTempFloat2 += 2.0
			SET_TRAIN_SPEED carTrain fTempFloat2
		ENDIF
	ELSE
		// The player is ahead of the train, so give the train a boost of speed
		GET_CAR_SPEED carTrain fTempFloat2
		fTempFloat2 += 2.0
		SET_TRAIN_SPEED carTrain fTempFloat2

		// Let the train move really fast
		fTempFloat = 100.0
	ENDIF
*/
// END REMOVE VARIABLE TRAIN SPEED

// USE A CONSTANT SPEED
	fTempFloat = SCRASH2_MAX_TRAIN_SPEED_mps
	IF nTrainDistanceFromLS <= nPlayerDistanceFromLS
		// ...train closer to LS than Player
		IF nTrainDistanceFromPlayer < 80
			// ...the player is close behind the train, so give the train a speed boost until it is at full speed
			GET_CAR_SPEED carTrain fTempFloat2
			fTempFloat2 += 2.0
			IF fTempFloat2 > SCRASH2_MAX_TRAIN_SPEED_mps
				fTempFloat2 = SCRASH2_MAX_TRAIN_SPEED_mps
			ENDIF
			SET_TRAIN_SPEED carTrain fTempFloat2
		ENDIF
	ELSE
		// ...the train is behind the player, so give it a speed boost if it is not moving at full speed
		GET_CAR_SPEED carTrain fTempFloat2
		fTempFloat2 += 2.0
		IF fTempFloat2 > SCRASH2_MAX_TRAIN_SPEED_mps
			fTempFloat2 = SCRASH2_MAX_TRAIN_SPEED_mps
		ENDIF
		SET_TRAIN_SPEED carTrain fTempFloat2
	ENDIF
// END USE A CONSTANT SPEED


	// Jump to here after calculating speed based on distance from LS
	SCRASH2_SET_SPEED:

	// Set the speed
	SET_TRAIN_CRUISE_SPEED carTrain fTempFloat

RETURN


// ****************************************
// Update Train Distance to LS
SCrash2_Update_Train_Distance_To_LS:

	IF nDistanceSFtoLS = 0
		IF nTrainDistanceFromLS = 0
			// ...no distance calculated yet
// REMOVE TRAIN PROGRESS BAR
//			g_SCrash2_distanceKM = 0
// END REMOVE TRAIN PROGRESS BAR
		ELSE
			// ...initial distance has now been calculated, so store it
			nDistanceSFtoLS = nTrainDistanceFromLS
// REMOVE TRAIN PROGRESS BAR
//			g_SCrash2_distanceKM = 0
// END REMOVE TRAIN PROGRESS BAR
		ENDIF
		
		RETURN
	ENDIF

	IF nTrainDistanceFromLS < 5
// REMOVE TRAIN PROGRESS BAR
//		g_SCrash2_distanceKM = 100
// END REMOVE TRAIN PROGRESS BAR
		RETURN
	ENDIF

// REMOVE TRAIN PROGRESS BAR
//	g_SCrash2_distanceKM = nDistanceSFtoLS - nTrainDistanceFromLS
//	g_SCrash2_distanceKM = g_SCrash2_distanceKM * 100
//	g_SCrash2_distanceKM = g_SCrash2_distanceKM / nDistanceSFtoLS
// END REMOVE TRAIN PROGRESS BAR

RETURN


// ****************************************
// Update Other Trains
SCrash2_Update_Other_Trains:

	// POSITION MEANINGS
	//	-2:		Train has been used
	//	-1:		Train has not been initialised
	//	 1 - 5: The position where the train will be created

	// These other trains are a way of keeping the player on his toes if he is chasing the train along the train tracks

	// If all trains have been used, then do nothing else
	flagTempFlag = 1
	REPEAT SCRASH2_NUMBER_OF_OTHER_TRAINS nLoop
		IF NOT nOtherTrainPosition[nLoop] = -2
			flagTempFlag = 0
		ENDIF
	ENDREPEAT

	IF flagTempFlag = 1
		RETURN
	ENDIF

	// If the trains are not initialised, then randomly choose a position where they will appear
	REPEAT SCRASH2_NUMBER_OF_OTHER_TRAINS nLoop
		IF nOtherTrainPosition[nLoop] = -1

			// Jump to here if the position is a duplicate
			SCRASH2_RECHOOSE_OTHER_TRAIN_POSITION:

			// Randomly choose a position for the train to appear at
			GENERATE_RANDOM_INT_IN_RANGE 0 5 nOtherTrainPosition[nLoop]

			// Check for duplicates
			flagTempFlag = 0
			nLoop2 = 0
			WHILE nLoop2 < nLoop
				IF flagTempFlag = 0
					IF nOtherTrainPosition[nLoop] = nOtherTrainPosition[nLoop2]
						flagTempFlag = 1
					ENDIF
				ENDIF

				nLoop2++
			ENDWHILE

			IF flagTempFlag = 1
				GOTO SCRASH2_RECHOOSE_OTHER_TRAIN_POSITION
			ENDIF
		ENDIF
	ENDREPEAT

	// Check if it is time to trigger one of the trains
	REPEAT SCRASH2_NUMBER_OF_OTHER_TRAINS nLoop
		flagTempFlag = 0
		SWITCH nOtherTrainPosition[nLoop]
			CASE 0
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1982.4420 -598.0178 24.7266 8.0 8.0 2.0 FALSE
					flagTempFlag = 1
				ENDIF
				BREAK
			CASE 1
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1827.1726 -1332.3062 12.9453 8.0 8.0 2.0 FALSE
					flagTempFlag = 1
				ENDIF
				BREAK
			CASE 2
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer -946.4463 -1497.6177 89.28716 8.0 8.0 2.0 FALSE
					flagTempFlag = 1
				ENDIF
				BREAK
			CASE 3
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer -620.5595 -1144.5725 42.0616 8.0 8.0 2.0 FALSE
					flagTempFlag = 1
				ENDIF
				BREAK
			CASE 4
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 98.3887 -1021.7949 21.2188 8.0 8.0 2.0 FALSE
					flagTempFlag = 1
				ENDIF
				BREAK
		ENDSWITCH

		IF flagTempFlag = 1
			// Create a train
			// ...choose a random train config
			GENERATE_RANDOM_INT_IN_RANGE 10 14 nTempInt

			// ...choose a random speed
			GENERATE_RANDOM_INT_IN_RANGE 10 20 nTempInt2
			fTempFloat =# nTempInt2

			// ...generate the train
			SWITCH nOtherTrainPosition[nLoop]
				CASE 0
					CLEAR_AREA -1875.7197 -1267.0587 12.9511 100.0 TRUE
					// TRUE is for the direction of travel - clockwise or anti-clockwise
					CREATE_MISSION_TRAIN nTempInt -1875.7197 -1267.0587 12.9511 TRUE carOtherTrain[nLoop]
					SET_TRAIN_SPEED carOtherTrain[nLoop] fTempFloat
					SET_TRAIN_CRUISE_SPEED carOtherTrain[nLoop] fTempFloat
					BREAK
				CASE 1
					CLEAR_AREA -1332.7209 -1514.8876 22.7265 100.0 TRUE
					// TRUE is for the direction of travel - clockwise or anti-clockwise
					CREATE_MISSION_TRAIN nTempInt -1332.7209 -1514.8876 22.7265 TRUE carOtherTrain[nLoop]
					SET_TRAIN_SPEED carOtherTrain[nLoop] fTempFloat
					SET_TRAIN_CRUISE_SPEED carOtherTrain[nLoop] fTempFloat
					BREAK
				CASE 2
					CLEAR_AREA -673.6719 -1127.8745 48.8875 100.0 TRUE
					// TRUE is for the direction of travel - clockwise or anti-clockwise
					CREATE_MISSION_TRAIN nTempInt -673.6719 -1127.8745 48.8875 TRUE carOtherTrain[nLoop]
					SET_TRAIN_SPEED carOtherTrain[nLoop] fTempFloat
					SET_TRAIN_CRUISE_SPEED carOtherTrain[nLoop] fTempFloat
					BREAK
				CASE 3
//					CLEAR_AREA -321.4720 -1184.0577 34.8293 100.0 TRUE
					// TRUE is for the direction of travel - clockwise or anti-clockwise
//					CREATE_MISSION_TRAIN nTempInt -321.4720 -1184.0577 34.8293 TRUE carOtherTrain[nLoop]
					CLEAR_AREA -321.8627 -1191.6473 35.5265 100.0 TRUE
					// TRUE is for the direction of travel - clockwise or anti-clockwise
					CREATE_MISSION_TRAIN nTempInt -321.8627 -1191.6473 35.5265 TRUE carOtherTrain[nLoop]
					SET_TRAIN_SPEED carOtherTrain[nLoop] fTempFloat
					SET_TRAIN_CRUISE_SPEED carOtherTrain[nLoop] fTempFloat
					BREAK
				CASE 4
					CLEAR_AREA 802.3020 -1362.6608 -2.6302 100.0 TRUE
					// TRUE is for the direction of travel - clockwise or anti-clockwise
					CREATE_MISSION_TRAIN nTempInt 802.3020 -1362.6608 -2.6302 TRUE carOtherTrain[nLoop]
					SET_TRAIN_SPEED carOtherTrain[nLoop] fTempFloat
					SET_TRAIN_CRUISE_SPEED carOtherTrain[nLoop] fTempFloat
					BREAK
			ENDSWITCH

			// Make sure this train isn't generated again
			nOtherTrainPosition[nLoop] = -2
		ENDIF
	ENDREPEAT

RETURN


// *************************************************************************************************************
//								       Reporter Hails a Taxi
// *************************************************************************************************************

// ****************************************
// Create Reporter LS
SCrash2_Create_Reporter_LS:

//	xposTemp = 832.1662
//	yposTemp = -1376.1748
//	zposTemp = -1.5078
//	headTemp = 324.4522

//	CLEAR_AREA xposTemp yposTemp zposTemp 1.0 TRUE
//	CREATE_CHAR	PEDTYPE_CIVMALE WMYBU xposTemp yposTemp zposTemp charReporter
//	SET_CHAR_HEADING charReporter headTemp
//	SET_CHAR_DECISION_MAKER charReporter dmEmpty


	GET_TRAIN_CARRIAGE carTrain 2 carCarriage2
	CREATE_CHAR_INSIDE_CAR carCarriage2 PEDTYPE_CIVMALE WMYBU charReporter
	SET_CHAR_DECISION_MAKER charReporter dmEmpty
	TASK_LEAVE_CAR charReporter carCarriage2

	existsReporter = 1

RETURN


// ****************************************
// Choose Taxi Creation Position
SCrash2_Choose_Taxi_LS_Creation_Position:

	flagTaxiLSCreationPositionAlpha = TRUE

	// If the player is in a position to see the taxi being created at the alpha position, then change to the alternative
	IF IS_CHAR_IN_AREA_3D scplayer 685.9716 -1433.5227 11.0857 965.9512 -1379.3861 14.9731 FALSE
		flagTaxiLSCreationPositionAlpha = FALSE
	ENDIF

RETURN


// ****************************************
// Create Taxi LS
SCrash2_Create_Taxi_LS:

	IF flagTaxiLSCreationPositionAlpha = TRUE
		// ...primary position
		xposTemp = 850.1132
		yposTemp = -1391.7085
		zposTemp = 12.4161
		headTemp = 101.5280
	ELSE
		// ...secondary position
		xposTemp = 847.1364
		yposTemp = -1331.1277
		zposTemp = 12.5097
		headTemp = 91.6504
	ENDIF

	CLEAR_AREA xposTemp yposTemp zposTemp 10.0 TRUE
	CREATE_CAR TAXI xposTemp yposTemp zposTemp carTaxiLS
	SET_CAR_HEADING carTaxiLS headTemp

	CREATE_CHAR_INSIDE_CAR carTaxiLS PEDTYPE_CIVMALE BMYBE charTaxiDriverLS

	// ...make the taxi drive to the required coordinates
	IF flagTaxiLSCreationPositionAlpha = TRUE
		// ...drive to alpha position
//		TASK_CAR_DRIVE_TO_COORD charTaxiDriverLS carTaxiLS 800.5493 -1342.9694 12.3906 12.0 MODE_NORMAL 0 DRIVINGMODE_STOPFORCARS_IGNORELIGHTS
		TASK_CAR_DRIVE_TO_COORD charTaxiDriverLS carTaxiLS 801.0299 -1337.9287 12.3906 12.0 MODE_NORMAL 0 DRIVINGMODE_STOPFORCARS_IGNORELIGHTS
	ELSE
		// ...drive to beta position
		TASK_CAR_DRIVE_TO_COORD charTaxiDriverLS carTaxiLS 824.4391 -1330.7069 12.4174 12.0 MODE_NORMAL 0 DRIVINGMODE_STOPFORCARS_IGNORELIGHTS
		flagTaxiApproachingReporter = TRUE
	ENDIF

	existsTaxiLS = TRUE

RETURN


// ****************************************
// Update Station Walk
SCrash2_Update_Station_Walk:

	// If the reporter is spooked then stop and look at the player
	IF flagReporterSpooked = TRUE
		IF flagAllowTurnTowardsPlayer = TRUE
			GET_SCRIPT_TASK_STATUS charReporter TASK_ACHIEVE_HEADING m_status
		ELSE
			GET_SCRIPT_TASK_STATUS charReporter TASK_LOOK_AT_CHAR m_status
		ENDIF

		IF timerLookAtAnim < m_mission_timer
			IF NOT m_status = PERFORMING_TASK
			AND NOT m_status = WAITING_TO_START_TASK
				IF flagAllowTurnTowardsPlayer = TRUE
					// Get the Required Heading
					// ...get player coords
					GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
					xloTemp = xposTemp
					yloTemp = yposTemp

					// ...get Gang Member coords
					GET_CHAR_COORDINATES charReporter xposTemp yposTemp zposTemp
					xhiTemp = xposTemp
					yhiTemp = yposTemp

					// Calculate Vector
					xposTemp = xloTemp - xhiTemp
					yposTemp = yloTemp - yhiTemp
					GET_HEADING_FROM_VECTOR_2D xposTemp yposTemp headTemp
				ENDIF

				// Look at
				TASK_LOOK_AT_CHAR charReporter scplayer SCRASH2_PERSIST

				// Orientate
				IF flagAllowTurnTowardsPlayer = TRUE
					TASK_ACHIEVE_HEADING charReporter headTemp
					timerLookAtAnim = m_mission_timer + 250
				ENDIF
			ENDIF
		ENDIF

		RETURN
	ENDIF

	SWITCH statusStationWalk
		CASE SCRASH2_STATION_WALK_NOT_STARTED
			statusStationWalk = SCRASH2_STATION_WALK_FOOT_OF_STAIRS_INIT
			statusStationWalkInit = SCRASH2_STATION_WALK_FOOT_OF_STAIRS_INIT
			BREAK

		CASE SCRASH2_STATION_WALK_FOOT_OF_STAIRS_INIT
			TASK_GO_STRAIGHT_TO_COORD charReporter 828.2594 -1358.9948 -1.5078 PEDMOVE_WALK -2		// Foot of stairs
			statusStationWalk = SCRASH2_STATION_WALK_FOOT_OF_STAIRS_LOCATE
			BREAK

		CASE SCRASH2_STATION_WALK_FOOT_OF_STAIRS_LOCATE
			IF LOCATE_CHAR_ANY_MEANS_3D charReporter 828.2594 -1358.9948 -1.5078 1.2 1.2 1.2 FALSE
				statusStationWalk = SCRASH2_STATION_WALK_MID_LEVEL_INIT
				statusStationWalkInit = SCRASH2_STATION_WALK_MID_LEVEL_INIT

				// Update the player detection area now that the reporter is at the foot of the stairs
				nSuspicionWalkArea = SCRASH2_SUSPICION_AREA_LOW_STEPS
			ENDIF
			BREAK

		CASE SCRASH2_STATION_WALK_MID_LEVEL_INIT
			OPEN_SEQUENCE_TASK nSeqTask
				TASK_GO_STRAIGHT_TO_COORD -1 837.3880 -1346.2332 6.1719 PEDMOVE_WALK -2			// Mid-stairs part 1
				TASK_GO_STRAIGHT_TO_COORD -1 836.6295 -1342.9258 6.1719 PEDMOVE_WALK -2			// Mid-stairs part 2
			CLOSE_SEQUENCE_TASK nSeqTask

			PERFORM_SEQUENCE_TASK charReporter nSeqTask
			CLEAR_SEQUENCE_TASK nSeqTask

			statusStationWalk = SCRASH2_STATION_WALK_MID_LEVEL_LOCATE
			BREAK

		CASE SCRASH2_STATION_WALK_MID_LEVEL_LOCATE
			IF LOCATE_CHAR_ANY_MEANS_3D charReporter 836.6295 -1342.9258 6.1719 1.2 1.2 1.2 FALSE
				statusStationWalk = SCRASH2_STATION_WALK_TOP_OF_STAIRS_INIT
				statusStationWalkInit = SCRASH2_STATION_WALK_TOP_OF_STAIRS_INIT

				// Update the player detection area now that the reporter is at the stairs mid_level
				nSuspicionWalkArea = SCRASH2_SUSPICION_AREA_HIGH_STEPS
			ENDIF
			BREAK

		CASE SCRASH2_STATION_WALK_TOP_OF_STAIRS_INIT
			TASK_GO_STRAIGHT_TO_COORD charReporter 822.0569 -1342.3020 12.5190 PEDMOVE_WALK -2		// Top of stairs
			statusStationWalk = SCRASH2_STATION_WALK_TOP_OF_STAIRS_LOCATE
			BREAK

		CASE SCRASH2_STATION_WALK_TOP_OF_STAIRS_LOCATE
			IF LOCATE_CHAR_ANY_MEANS_3D charReporter 822.0569 -1342.3020 12.5190 1.2 1.2 1.2 FALSE
				statusStationWalk = SCRASH2_STATION_WALK_READY_TO_LOOK_AROUND
				statusStationWalkInit = SCRASH2_STATION_WALK_READY_TO_LOOK_AROUND

				// Update the player detection area now that the reporter is at the station entrance
				nSuspicionWalkArea = SCRASH2_SUSPICION_AREA_ENTRANCE
			ENDIF
			BREAK

		CASE SCRASH2_STATION_WALK_READY_TO_LOOK_AROUND
			// Nothing to do here (the main stage sequence takes over)
			BREAK

		CASE SCRASH2_STATION_WALK_KERB_INIT
			TASK_GO_STRAIGHT_TO_COORD charReporter 803.7784 -1340.5349 12.5469 PEDMOVE_WALK -2		// Kerb
			statusStationWalk = SCRASH2_STATION_WALK_KERB_LOCATE
			BREAK

		CASE SCRASH2_STATION_WALK_KERB_LOCATE
			IF LOCATE_CHAR_ANY_MEANS_3D charReporter 803.7784 -1340.5349 12.5469 1.2 1.2 1.2 FALSE
				statusStationWalk = SCRASH2_STATION_WALK_WAIT_FOR_TAXI
				statusStationWalkInit = SCRASH2_STATION_WALK_WAIT_FOR_TAXI

				// Update the player detection area now that the reporter is at the kerb
				nSuspicionWalkArea = SCRASH2_SUSPICION_AREA_KERB
			ENDIF
			BREAK

		CASE SCRASH2_STATION_WALK_WAIT_FOR_TAXI
			// Nothing to do here (the main stage sequence takes over)
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_STATION_WALK_STATUS
	ENDSWITCH
RETURN


// *************************************************************************************************************
//								       Reporter Goes To Meeting Place
// *************************************************************************************************************

// ****************************************
// Choose Taxi Waypoint
SCrash2_Choose_Taxi_Waypoint:

	// flagTempFlag is used to decide if a burst of speed is allowed to this waypoint
	// flagTempFlag2 is used to decide if the taxi is allowed to stop at the waypoint for a moment
	flagTempFlag	= TRUE
	flagTempFlag2	= TRUE

// REMOVE TAXI ENFORCED STOPS
//	statusTaxiStopped = SCRASH2_TAXI_STOPPED_STATUS_NO_STOP
// END REMOVE TAXI ENFORCED STOPS

	IF nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_NONE
		// ...don't allow a burst of speed to the first waypoint
		flagTempFlag = FALSE
	ENDIF

	// Choose the next waypoint
	SWITCH nTaxiWaypointID
		CASE SCRASH2_TAXI_WAYPOINT_NONE
			GENERATE_RANDOM_INT_IN_RANGE 0 3 nTempInt
			IF nTempInt = 0
				nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_A
			ENDIF
			IF nTempInt = 1
				nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_B
			ENDIF
			IF nTempInt = 2
				nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_C
			ENDIF
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_A
			nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_D
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_B
			nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_F
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_C
			nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_G
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_D
			nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_I
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_E
			nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_J
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_F
			nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_E
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_G
			nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_L
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_H
			nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_PIER
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_I
			nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_H
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_J
			nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_PIER
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_K
			nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_PIER
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_L
			nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_K
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_PIER
			// Should only enter here if the player is trying to cheat by going to the pier first
			// It will cause the taxi to drive past the pier
			GENERATE_RANDOM_INT_IN_RANGE 0 3 nTempInt
			IF nTempInt = 0
				nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_I
			ENDIF
			IF nTempInt = 1
				nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_E
			ENDIF
			IF nTempInt = 2
				nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_L
			ENDIF
			BREAK

		DEFAULT
			WRITE_DEBUG Unknown_Current_Taxi_waypoint
	ENDSWITCH


	// Don't allow a burst of speed or stopping if going to the pier
	IF nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_PIER
		flagTempFlag	= FALSE
		flagTempFlag2	= FALSE
	ENDIF


	// A new waypoint will have been chosen, so get the coordinates
	SWITCH nTaxiWaypointID
		CASE SCRASH2_TAXI_WAYPOINT_NONE
			WRITE_DEBUG TAXI_WAYPOINT_STILL_NONE
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_A
			xposWaypoint = 728.2346
			yposWaypoint = -1068.5289
			zposWaypoint = 21.3149
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_B
			xposWaypoint = 885.8015
			yposWaypoint = -1152.0475
			zposWaypoint = 22.7156
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_C
			xposWaypoint = 623.9262
			yposWaypoint = -1356.3359
			zposWaypoint = 12.4009
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_D
			xposWaypoint = 554.1271
			yposWaypoint = -1327.2174
			zposWaypoint = 12.3984
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_E
			xposWaypoint = 552.7402
			yposWaypoint = -1401.8484
			zposWaypoint = 14.0907
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_F
			xposWaypoint = 913.4064
			yposWaypoint = -1381.0392
			zposWaypoint = 12.3208
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_G
//			xposWaypoint = 806.1024
//			yposWaypoint = -1704.4703
//			zposWaypoint = 12.3828
			xposWaypoint = 807.6411
			yposWaypoint = -1737.3925
			zposWaypoint = 12.3906
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_H
			xposWaypoint = 318.3799
			yposWaypoint = -1464.9255
			zposWaypoint = 33.0030
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_I
			xposWaypoint = 461.6126
			yposWaypoint = -1433.6710
			zposWaypoint = 23.2117
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_J
			xposWaypoint = 484.8509
			yposWaypoint = -1579.2202
			zposWaypoint = 20.2086
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_K
			xposWaypoint = 570.8074
			yposWaypoint = -1580.0520
			zposWaypoint = 15.0078
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_L
			xposWaypoint = 673.4271
			yposWaypoint = -1737.3396
			zposWaypoint = 12.4441
			BREAK

		CASE SCRASH2_TAXI_WAYPOINT_PIER
			xposWaypoint = xposDestination
			yposWaypoint = yposDestination
			zposWaypoint = zposDestination
			BREAK
	ENDSWITCH


	// Decide if a burst of speed will happen
	IF flagTempFlag = TRUE
		// Don't allow an immediate burst of speed after leaving the station, or a previous burst of speed
		IF nTaxiChanceOfBurstOfSpeed = 0
			// ...no chance this time
			flagTempFlag = 0
		ELSE
			GENERATE_RANDOM_INT_IN_RANGE 0 3 nTempInt
			IF nTempInt > nTaxiChanceOfBurstOfSpeed
				// ...no chance this time
				flagTempFlag = FALSE
			ENDIF
		ENDIF
	ENDIF

	IF flagTempFlag = TRUE
		// ...burst of speed will happen, so reset variables
		nTaxiChanceOfBurstOfSpeed = 0
	ELSE
		// ...burst of speed won't happen, so increase chance for next time
		nTaxiChanceOfBurstOfSpeed++
	ENDIF


// REMOVE TAXI ENFORCED STOPS
/*
	// Decide if the taxi will stop at the next waypoint
	IF flagTempFlag2 = TRUE
		// Don't allow a stop immediately after a previous stop or if going to the Pier
		IF nTaxiChanceOfStopping = 0
			// ...no chance this time
			flagTempFlag2 = 0
		ELSE
			GENERATE_RANDOM_INT_IN_RANGE 0 4 nTempInt
			IF nTempInt > nTaxiChanceOfStopping
				// ...no chance this time
				flagTempFlag2 = FALSE
			ENDIF
		ENDIF
	ENDIF

	IF flagTempFlag2 = TRUE
		// ...stopping will happen, so reset variables
		nTaxiChanceOfStopping = 0
		statusTaxiStopped = SCRASH2_TAXI_STOPPED_STATUS_TO_STOP
	ELSE
		// ...stopping won't happen, so increase chance for next time
		nTaxiChanceOfStopping++
	ENDIF
*/
// END REMOVE TAXI ENFORCED STOPS

	
	// Make the taxi go to the waypoint
	IF NOT IS_CHAR_DEAD charTaxiDriverLS
		IF flagTempFlag = TRUE
			// ...burst of speed
			TASK_CAR_DRIVE_TO_COORD charTaxiDriverLS -1 xposWaypoint yposWaypoint zposWaypoint 25.0 MODE_ACCURATE 0 DRIVINGMODE_AVOIDCARS
		ELSE
			// ...normal driving speed (make the taxi speed up with each waypoint)
			IF nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_PIER
				// ...heading for the pier, so use a sedate pace
				nTempInt = 15
			ELSE
				// ...gradually increase the pace at each waypoint
				nTempInt = nTaxiNumberOfWaypoints * 3
				nTempInt += 18
				IF nTempInt > 25
					nTempInt = 25
				ENDIF
			ENDIF

			fTempFloat =# nTempInt

			IF nTaxiNumberOfWaypoints < 2
				// ...stop for lights in first couple of sections
				TASK_CAR_DRIVE_TO_COORD charTaxiDriverLS -1 xposWaypoint yposWaypoint zposWaypoint fTempFloat MODE_ACCURATE 0 DRIVINGMODE_AVOIDCARS_OBEYLIGHTS
			ELSE
				// ...don't stop for lights in later sections
				TASK_CAR_DRIVE_TO_COORD charTaxiDriverLS -1 xposWaypoint yposWaypoint zposWaypoint fTempFloat MODE_ACCURATE 0 DRIVINGMODE_AVOIDCARS
			ENDIF
		ENDIF
	ENDIF

	
	// Increase number of waypoints
	nTaxiNumberOfWaypoints++

RETURN

// ****************************************
// Update Taxi Waypoint
SCrash2_Update_Taxi_Waypoint:

	IF IS_CHAR_DEAD charReporter
		RETURN
	ENDIF

	IF NOT IS_CHAR_IN_CAR charReporter carTaxiLS
		RETURN
	ENDIF

	IF IS_CAR_DEAD carTaxiLS
		RETURN
	ENDIF


	// Check if the taxi has reached the waypoint (and decide if it is stopping or not)
	IF NOT nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_PIER
// REMOVE TAXI ENFORCED STOPS
/*
		SWITCH statusTaxiStopped
			CASE SCRASH2_TAXI_STOPPED_STATUS_NO_STOP
				// ...taxi is not going to stop
				IF LOCATE_CAR_3D carTaxiLS xposWaypoint yposWaypoint zposWaypoint 18.0 18.0 4.0 FALSE
					GOSUB SCrash2_Choose_Taxi_Waypoint
				ENDIF
				BREAK

			CASE SCRASH2_TAXI_STOPPED_STATUS_TO_STOP
				// ...taxi is going to stop
				IF LOCATE_CAR_3D carTaxiLS xposWaypoint yposWaypoint zposWaypoint 4.0 4.0 4.0 FALSE
					// ...reached stop location
					statusTaxiStopped = SCRASH2_TAXI_STOPPED_STATUS_STOPPED

					GENERATE_RANDOM_INT_IN_RANGE 5 10 nTempInt
					nTempInt *= 1000
					timerTaxiStopped = m_mission_timer + nTempInt
				ENDIF
				BREAK

			CASE SCRASH2_TAXI_STOPPED_STATUS_STOPPED
				// ...taxi has stopped, so check if it is time to go again
				IF timerTaxiStopped < m_mission_timer
					// ...yes
					GOSUB SCrash2_Choose_Taxi_Waypoint
				ENDIF
				BREAK
		ENDSWITCH
*/
// END REMOVE TAXI ENFORCED STOPS

// NEW CODE TO REPLACE REMOVED TAXI ENFORCED STOPS
		IF LOCATE_CAR_3D carTaxiLS xposWaypoint yposWaypoint zposWaypoint 18.0 18.0 4.0 FALSE
			GOSUB SCrash2_Choose_Taxi_Waypoint
		ENDIF
// END NEW CODE TO REPLACE REMOVED TAXI ENFORCED STOPS
	ENDIF


	// Check if the taxi is going to the pier but the player has sneaked onto the pier first
	IF nTaxiWaypointID = SCRASH2_TAXI_WAYPOINT_PIER
		IF LOCATE_CAR_3D carTaxiLS 370.0204 -1647.6471 31.7173 14.0 14.0 4.0 FALSE
			IF flagPlayerOnPier = TRUE
				// ...don't go there yet because the player is trying to cheat
				GOSUB SCrash2_Choose_Taxi_Waypoint
			ENDIF
		ENDIF
	ENDIF

RETURN




// *************************************************************************************************************
//								       Reporter Meets the Target
// *************************************************************************************************************

// ****************************************
// Create Target
SCrash2_Create_Target:

	xposTemp = 355.6180
	yposTemp = -2037.0068
	zposTemp = 6.8360
	headTemp = 271.1917

	CLEAR_AREA xposTemp yposTemp zposTemp 10.0 TRUE
	CREATE_CHAR	PEDTYPE_CIVMALE HMYRI xposTemp yposTemp zposTemp charTarget
	SET_CHAR_HEADING charTarget headTemp
	SET_CHAR_IS_TARGET_PRIORITY charTarget TRUE

	existsTarget = TRUE

RETURN


// ****************************************
// Update AI Reporter on Pier
SCrash2_Update_AI_ReporterOnPier:

	IF flagReporterAssassinated = TRUE
		RETURN
	ENDIF


	SWITCH aiReporterOnPier
/*		// Make the Reporter Cower for quite a long period of time
		CASE SCRASH2_AI_REPORTER_ON_PIER_COWER
			SET_CHAR_DECISION_MAKER charReporter dmEmpty
			TASK_PLAY_ANIM charReporter DUCK_cower PED 4.0 true false false false -1
			timerReporterAI = m_mission_timer + 15000
			timerReporterAnim = m_mission_timer + 500
			aiReporterOnPier = SCRASH2_AI_REPORTER_ON_PIER_COWERING
			BREAK

		// Keep the reporter cowering until timeout
		CASE SCRASH2_AI_REPORTER_ON_PIER_COWERING
			IF timerReporterAI < m_mission_timer
				// ...timeout, so make the reporter flee
				aiReporterOnPier = SCRASH2_AI_REPORTER_ON_PIER_FLEE
			ELSE
				// ...should still be cowering, so make sure it is
				IF timerReporterAnim < m_mission_timer
					IF NOT IS_CHAR_PLAYING_ANIM charReporter DUCK_cower
						TASK_PLAY_ANIM charReporter DUCK_cower PED 4.0 true false false false -1
						timerReporterAnim = m_mission_timer + 500
					ENDIF
				ENDIF
			ENDIF
			BREAK
*/
		// Make the reporter flee
		CASE SCRASH2_AI_REPORTER_ON_PIER_FLEE
			TASK_SMART_FLEE_CHAR charReporter scplayer 4000.0 3600000
			aiReporterOnPier = SCRASH2_AI_REPORTER_ON_PIER_FLEEING
			timerReporterAI = m_mission_timer + 250
			BREAK

		// Keep the reporter fleeing
		CASE SCRASH2_AI_REPORTER_ON_PIER_FLEEING
			IF timerReporterAI < m_mission_timer
				GET_SCRIPT_TASK_STATUS charReporter TASK_SMART_FLEE_CHAR m_status
				IF m_status = FINISHED_TASK
					TASK_SMART_FLEE_CHAR charReporter scplayer 4000.0 3600000
					timerReporterAI = m_mission_timer + 250
				ENDIF
			ENDIF
			BREAK
	ENDSWITCH

RETURN


// ****************************************
// Update AI Target
SCrash2_Update_AI_Target:

	IF flagTargetAssassinated = TRUE
		RETURN
	ENDIF

	SWITCH aiTarget
		// Make the Target Kill Player
		CASE SCRASH2_AI_TARGET_KILL_PLAYER
			GIVE_WEAPON_TO_CHAR charTarget WEAPONTYPE_PISTOL 99
			SET_CHAR_DECISION_MAKER charTarget dmTough
			TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING charTarget scplayer DUCK_RANDOMLY 4000 34
			aiTarget = SCRASH2_AI_TARGET_KILLING_PLAYER
			timerTargetAI = m_mission_timer + 250
			BREAK

		// Keep the Target killing the player
		CASE SCRASH2_AI_TARGET_KILLING_PLAYER
			IF timerTargetAI < m_mission_timer
/*				// Check if the Target should flee instead
				GET_CHAR_COORDINATES charTarget	xposTemp	yposTemp	zposTemp
				GET_CHAR_COORDINATES scplayer	xposTemp2	yposTemp2	zposTemp2
				GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempFloat
				IF fTempFloat > 100.0
					aiTarget = SCRASH2_AI_TARGET_FLEE
				ELSE
*/					// ...target shouldn't flee, so make sure it is still killing player
					GET_SCRIPT_TASK_STATUS charTarget TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING m_status
					IF m_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING charTarget scplayer DUCK_RANDOMLY 4000 34
						timerTargetAI = m_mission_timer + 250
					ENDIF
//				ENDIF
			ENDIF
			BREAK

/*		// Make the Target Flee
		CASE SCRASH2_AI_TARGET_FLEE
			SET_CHAR_DECISION_MAKER charTarget dmEmpty
			TASK_SMART_FLEE_CHAR charTarget scplayer 4000.0 3600000
			aiTarget = SCRASH2_AI_TARGET_FLEEING
			timerTargetAI = m_mission_timer + 250
			BREAK

		// Keep the Target fleeing
		CASE SCRASH2_AI_TARGET_FLEEING
			IF timerTargetAI < m_mission_timer
				// Check if the Target should kill player instead
				GET_CHAR_COORDINATES charTarget	xposTemp	yposTemp	zposTemp
				GET_CHAR_COORDINATES scplayer	xposTemp2	yposTemp2	zposTemp2
				GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempFloat
				IF fTempFloat < 60.0
					aiTarget = SCRASH2_AI_TARGET_KILL_PLAYER
				ELSE
					// ...target shouldn't kill player, so make sure it is still fleeing
					GET_SCRIPT_TASK_STATUS charTarget TASK_SMART_FLEE_CHAR m_status
					IF m_status = FINISHED_TASK
						TASK_SMART_FLEE_CHAR charTarget scplayer 4000.0 3600000
						timerTargetAI = m_mission_timer + 250
					ENDIF
				ENDIF
			ENDIF
			BREAK
*/
	ENDSWITCH

RETURN


// *************************************************************************************************************
//								             Suspicion Level
// *************************************************************************************************************

// ****************************************
// Initialise Suspicion Level

SCrash2_Initialise_Suspicion_Level:

	// Reset suspicion variables
	nSuspicionWalkArea					= SCRASH2_SUSPICION_AREA_NONE
// REMOVE OLD-STYLE SPOOK-O-METER
//	timerSuspicionNextUpdate			= m_mission_timer + 500
// END REMOVE OLD-STYLE SPOOK-O-METER
	statusSuspicion						= SCRASH2_SUSPICION_STATUS_NONE
	flagReporterSpooked					= FALSE

RETURN


// ****************************************
// Update Walking Suspicion Area

SCrash2_Update_Walking_Suspicion_Area:

	// Check if the suspicion area needs to change based on the reporter's current position
	SWITCH nSuspicionWalkArea
		// ...no suspicion area, so check if on platform
		CASE SCRASH2_SUSPICION_AREA_NONE
			IF LOCATE_CHAR_ANY_MEANS_3D charReporter 833.4493 -1374.2609 -1.5078 1.2 1.2 1.2 FALSE
				nSuspicionWalkArea = SCRASH2_SUSPICION_AREA_PLATFORM
			ENDIF
			BREAK

		// ...on platform, so check if reached foot of steps
		CASE SCRASH2_SUSPICION_AREA_PLATFORM
			// Updated during the 'Station_Walk' routine
//			IF LOCATE_CHAR_ANY_MEANS_3D charReporter 828.2594 -1358.9948 -1.5078 1.2 1.2 1.2 FALSE
//				nSuspicionWalkArea = SCRASH2_SUSPICION_AREA_LOW_STEPS
//			ENDIF
			BREAK

		// ...on lower station steps, so check if reached mid-level
		CASE SCRASH2_SUSPICION_AREA_LOW_STEPS
			// Updated during the 'Station_Walk' routine
//			IF LOCATE_CHAR_ANY_MEANS_3D charReporter 836.6295 -1342.9258 6.1719 1.2 1.2 1.2 FALSE
//  				nSuspicionWalkArea = SCRASH2_SUSPICION_AREA_HIGH_STEPS
//			ENDIF
			BREAK

		// ...on higher station steps, so check if reached top of steps
		CASE SCRASH2_SUSPICION_AREA_HIGH_STEPS
			// Updated during the 'Station_Walk' routine
//			IF LOCATE_CHAR_ANY_MEANS_3D charReporter 822.0569 -1342.3020 12.5190 1.2 1.2 1.2 FALSE
//				nSuspicionWalkArea = SCRASH2_SUSPICION_AREA_ENTRANCE
//			ENDIF
			BREAK

		// ...at top of steps (station entrance), so check if at kerb
		CASE SCRASH2_SUSPICION_AREA_ENTRANCE
			// Updated during the 'Station_Walk' routine
//			IF LOCATE_CHAR_ANY_MEANS_3D charReporter 803.7784 -1342.9255 12.5469 1.2 1.2 1.2 FALSE
//				nSuspicionWalkArea = SCRASH2_SUSPICION_AREA_KERB
//			ENDIF
			BREAK

		// ...at kerb
		CASE SCRASH2_SUSPICION_AREA_KERB
			BREAK

		DEFAULT
			WRITE_DEBUG Unknown_Suspicion_area
	ENDSWITCH

RETURN


// ****************************************
// Update Walking Spook Meter

SCrash2_Update_Walking_Spook_Meter:

	// Check if it is time to do a suspicion check
	// NOTE: If the player is shooting, do the check immediately
	IF flagPlayerShootingNearWalkingReporter = TRUE
		RETURN
	ENDIF

	IF IS_CHAR_SHOOTING scplayer
		flagPlayerShootingNearWalkingReporter = TRUE
	ENDIF

// REMOVE OLD-STYLE SPOOK-O-METER
//	IF flagPlayerShootingNearWalkingReporter = FALSE
//	AND timerSuspicionNextUpdate > m_mission_timer
//		RETURN
//	ENDIF
// END REMOVE OLD-STYLE SPOOK-O-METER

	// Check if the player is in the area
	// flagTempFlag used to indicate if the reporter is suspicious on this update
	flagTempFlag = FALSE
	SWITCH nSuspicionWalkArea
		// ...no suspicion area
		CASE SCRASH2_SUSPICION_AREA_NONE
			BREAK

		// ...reporter on platform
		CASE SCRASH2_SUSPICION_AREA_PLATFORM
			IF IS_CHAR_IN_AREA_3D scplayer 783.9064 -1393.1558 -2.7078 858.4905 -1322.6401 -0.3006 FALSE
				// ...player is in the area
				flagTempFlag = TRUE
			ENDIF
			BREAK

		// ...reporter on lower station steps
		CASE SCRASH2_SUSPICION_AREA_LOW_STEPS
			IF IS_CHAR_IN_AREA_3D scplayer 825.3654 -1359.6663 -2.7078 842.8153 -1338.8934 7.3875 FALSE
			OR IS_CHAR_IN_AREA_3D scplayer 820.1507 -1365.7661 -2.7078 831.2700 -1354.9083 -0.3078 FALSE
				// ...player is in the area
				flagTempFlag = TRUE
			ENDIF
			BREAK

		// ...reporter on higher station steps
		CASE SCRASH2_SUSPICION_AREA_HIGH_STEPS
			IF IS_CHAR_IN_AREA_3D scplayer 819.6318 -1344.2576 4.9875 842.2764 -1338.7423 13.7162 FALSE
			OR IS_CHAR_IN_AREA_3D scplayer 816.7490 -1345.1163 8.8156 829.4735 -1338.8949 13.7281 FALSE
				// ...player is in the area
				flagTempFlag = TRUE
			ENDIF
			BREAK

		// ...reporter at top of steps (station entrance)
		CASE SCRASH2_SUSPICION_AREA_ENTRANCE
			IF IS_CHAR_IN_AREA_3D scplayer 787.7994 -1357.3230 11.3391 827.1069 -1309.4060 13.7625 FALSE
			OR IS_CHAR_IN_AREA_3D scplayer 816.7490 -1345.1163 8.8156 829.4735 -1338.8949 13.7281 FALSE
				// ...player is in the area
				flagTempFlag = TRUE
			ENDIF
			BREAK

		// ...reporter at kerb
		CASE SCRASH2_SUSPICION_AREA_KERB
			IF flagReporterGettingIntoCab = FALSE
				IF IS_CHAR_IN_AREA_3D scplayer 787.0083 -1376.2400 11.3391 809.9620 -1310.5059 13.8144 FALSE
					// ...player is in the area
					flagTempFlag = TRUE
				ENDIF
			ENDIF
			BREAK

		DEFAULT
			WRITE_DEBUG Unknown_Suspicion_area
	ENDSWITCH


	// If the player is shooting, check if he is in the area
	IF flagPlayerShootingNearWalkingReporter = TRUE
		flagPlayerShootingNearWalkingReporter = flagTempFlag
	ENDIF

	IF flagPlayerShootingNearWalkingReporter = TRUE
		// ...set a timer in case the reporter dies in the meantime so that the appropriate death message is displayed
		timerReporterSpookedAndFleeing = m_mission_timer + 1000
		TASK_SMART_FLEE_CHAR charReporter scplayer 9999.0 10000
	ENDIF


	// If the player is in the area, check distance
	IF flagTempFlag = TRUE
		GET_CHAR_COORDINATES charReporter	xposTemp	yposTemp	zposTemp
		GET_CHAR_COORDINATES scplayer		xposTemp2	yposTemp2	zposTemp2
		GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance

		// If previously suspicious, then the player needs to be the HI distance away to release suspicion,
		//		but if not previously suspicious then the player needs to be the LO distance away to cause suspicion
		IF statusSuspicion = SCRASH2_SUSPICION_STATUS_TOO_CLOSE
			// ...was previously suspicious
			IF fTempDistance > SCRASH2_SUSPICION_WALK_MAX_DISTANCE_HI
				// ...too far away, so not suspicious
				flagTempFlag = FALSE
			ENDIF
		ELSE
			// ...was not previously suspicious
			IF fTempDistance > SCRASH2_SUSPICION_WALK_MAX_DISTANCE_LO
				// ...too far away, so not suspicious
				flagTempFlag = FALSE
			ENDIF
		ENDIF
	ENDIF


	// Update the suspicion timer
	// Is the reporter suspicious now?
	IF flagTempFlag = TRUE
		// ...the reporter is suspicious
		IF statusSuspicion = SCRASH2_SUSPICION_STATUS_NONE
			// ...the reporter was not previously suspicious, so set up the timer
// REMOVE OLD-STYLE SPOOK-O-METER
//			// NOTE: The timer will only display when there are 10 seconds left.
//			//			This is to prevent it flashing on and off at the boundaries.
//			nFailure = 12
//			timerFailureConditions = m_mission_timer + 1000
// END REMOVE OLD-STYLE SPOOK-O-METER
// ADD NEW-STYLE SPOOK-O-METER
			timerTooClose = m_mission_timer + SCRASH2_SPOOKOMETER_INCREASE_msec

			// Display message (first time only)
			IF flagDisplayedTooClose = FALSE
				PRINT_NOW SCR2_08 30000 1
				flagDisplayedTooClose = TRUE
			ENDIF

			flagReporterSpooked = TRUE
// END ADD NEW-STYLE SPOOK-O-METER
			statusSuspicion = SCRASH2_SUSPICION_STATUS_TOO_CLOSE
		ELSE
			// ...the reporter was previously suspicious, so maintain the timer
// REMOVE OLD-STYLE SPOOK-O-METER
/*
			IF timerFailureConditions < m_mission_timer
				// ...timer elapsed
				nFailure--
				IF nFailure < 0
					// ...time up
					nFailure = 0
					m_failed = TRUE
					m_fail_reason = SCRASH2_FAILED_REPORTER_SPOOKED
					CLEAR_SMALL_PRINTS
					RETURN
				ELSE
					// set up the timer again
					timerFailureConditions = m_mission_timer + 1000
				ENDIF
			ENDIF

			// Check for an instant message
			IF nFailure > 10
			AND fTempDistance <= SCRASH2_TOO_CLOSE_INSTANT
				nFailure = 10
				timerFailureConditions = m_mission_timer + 1000
			ENDIF

			IF nFailure = 10
				// Display message? (First time only)
				IF flagDisplayedTooClose = FALSE
					PRINT_NOW SCR2_08 30000 1
					flagDisplayedTooClose = TRUE
				ENDIF

				flagReporterSpooked = TRUE
			ENDIF
*/
// END REMOVE OLD-STYLE SPOOK-O-METER
// ADD NEW-STYLE SPOOK-O-METER
			IF timerTooClose < m_mission_timer
				// ...timer elapsed
				g_SCrash2_spookKM++
				IF g_SCrash2_spookKM > 100
					g_SCrash2_spookKM = 100
					m_failed = TRUE
					m_fail_reason = SCRASH2_FAILED_REPORTER_SPOOKED
					CLEAR_SMALL_PRINTS
					RETURN
				ELSE
					// Set up the timer again
					timerTooClose = m_mission_timer + SCRASH2_SPOOKOMETER_INCREASE_msec
				ENDIF
			ENDIF
// END ADD NEW-STYLE SPOOK-O-METER

// REMOVE OLD-STYLE SPOOK-O-METER
//			// Display (but only if there are 10 seconds left)
//			IF nFailure <= 10
//				g_SCrash2_spookKM = 10 - nFailure
//				g_SCrash2_spookKM *= 10
//			ENDIF
// END REMOVE OLD-STYLE SPOOK-O-METER
		ENDIF
	ELSE
		// ...the reporter is not suspicious
		IF statusSuspicion = SCRASH2_SUSPICION_STATUS_TOO_CLOSE
			// ...the reporter was previously suspicious, so clear the timers
			CLEAR_SMALL_PRINTS
			statusSuspicion = SCRASH2_SUSPICION_STATUS_NONE

			// Go back to previous 'goto' command and issue it again
			flagReporterSpooked = FALSE
			statusStationWalk = statusStationWalkInit
			CLEAR_LOOK_AT charReporter
// ADD NEW-STYLE SPOOK-O-METER

			timerTooClose = m_mission_timer + SCRASH2_SPOOKOMETER_DECREASE_msec
		ELSE
			// ...the reporter was not previously suspicious, so check if there is still some spookometer value to clearup
			IF g_SCrash2_spookKM > 0
				// ...yes, so maintain it until it reaches 0
				IF timerTooClose > 0
				AND timerTooClose < m_mission_timer
					// ...decrease it
					g_SCrash2_spookKM--
					IF g_SCrash2_spookKM < 0
						g_SCrash2_spookKM = 0
						timerTooClose = 0
					ENDIF
				ENDIF
			ENDIF
// END ADD NEW-STYLE SPOOK-O-METER
		ENDIF

// REMOVE OLD-STYLE SPOOK-O-METER
//		g_SCrash2_spookKM = 0
// END REMOVE OLD-STYLE SPOOK-O-METER
	ENDIF




/* MODIFIED DISPLAY
	// Update the suspicion timer
	// Is the reporter suspicious now?
	IF flagTempFlag = TRUE
		// ...the reporter is suspicious
		IF statusSuspicion = SCRASH2_SUSPICION_STATUS_NONE
			// ...the reporter was not previously suspicious, so set up the timer
			// NOTE: The timer will only display when there are 10 seconds left.
			//			This is to prevent it flashing on and off at the boundaries.
			g_SCrash2_failureKM = 12
			timerFailureConditions = m_mission_timer + 1000
			statusSuspicion = SCRASH2_SUSPICION_STATUS_TOO_CLOSE
		ELSE
			// ...the reporter was previously suspicious, so maintain the timer
			IF timerFailureConditions < m_mission_timer
				// ...timer elapsed
				g_SCrash2_failureKM--
				IF g_SCrash2_failureKM < 0
					// ...time up
					g_SCrash2_failureKM = 0
					m_failed = TRUE
					m_fail_reason = SCRASH2_FAILED_REPORTER_SPOOKED
					CLEAR_SMALL_PRINTS
					RETURN
				ELSE
					// set up the timer again
					timerFailureConditions = m_mission_timer + 1000
				ENDIF
			ENDIF

			// Display (but only if there are 10 seconds left)
			IF g_SCrash2_failureKM = 1
				// ...singular
				CLEAR_SMALL_PRINTS
				PRINT_WITH_NUMBER SCR2_09 g_SCrash2_failureKM 1000 1
			ELSE
				IF g_SCrash2_failureKM <= 10
					// ...plural
					CLEAR_SMALL_PRINTS
					PRINT_WITH_NUMBER SCR2_08 g_SCrash2_failureKM 1000 1
				ENDIF
			ENDIF
		ENDIF
	ELSE
		// ...the reporter is not suspicious
		IF NOT statusSuspicion = SCRASH2_SUSPICION_STATUS_NONE
			// ...the reporter was previously suspicious, so clear the timers
			CLEAR_SMALL_PRINTS
			statusSuspicion = SCRASH2_SUSPICION_STATUS_NONE
		ENDIF
	ENDIF

	// Set up the timer for the next update
	timerSuspicionNextUpdate = m_mission_timer + 500
*/

RETURN


// ****************************************
// Update Driving Spook Meter

SCrash2_Update_Driving_Spook_Meter:

// REMOVE OLD-STYLE SPOOK-O-METER
//	// Check if it is time to do a suspicion check
//	IF timerSuspicionNextUpdate > m_mission_timer
//		RETURN
//	ENDIF
// END REMOVE OLD-STYLE SPOOK-O-METER


	// There is a bit of leeway when the reporter first gets in the car so that he doesn't immediately become
	//	suspicious of the player. so maintain this leeway time
	IF flagJustStartedDriving = TRUE
		IF timerJustStartedDrivingLeeway < m_mission_timer
			flagJustStartedDriving = FALSE
			timerJustStartedDrivingLeeway = 0
		ENDIF
	ENDIF


	// Storage to help decide if the 'decrease spookometer' timer should be started
	flagPreviouslySuspicious = FALSE
	IF statusSuspicion = SCRASH2_SUSPICION_STATUS_PLAYER_AHEAD
	OR statusSuspicion = SCRASH2_SUSPICION_STATUS_TOO_CLOSE
		flagPreviouslySuspicious = TRUE
	ENDIF


	// Always update the spook meter
	// NOTE: flag1 indicates too close; flag2 indicates too far away
	flagTempFlag = FALSE
	flagTempFlag2 = FALSE

	// NOTE: Special check to see if the player has overtaken the reporter on the pier
	flagPlayerAheadOfReporterOnPier = FALSE

	IF flagPlayerOnPier = TRUE
	AND flagReporterOnPier = TRUE
		// Check if the player is closer to the end of the pier than the reporter
		GET_CHAR_COORDINATES charReporter	xposTemp	yposTemp	zposTemp
		GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp 372.6610 -2088.7900 6.8360 fTempDistance

		GET_CHAR_COORDINATES scplayer		xposTemp2	yposTemp2	zposTemp2
		GET_DISTANCE_BETWEEN_COORDS_3D xposTemp2 yposTemp2 zposTemp2 372.6610 -2088.7900 6.8360 fTempFloat

		IF fTempFloat < fTempDistance
			// ...player closer to end of pier than reporter
			flagPlayerAheadOfReporterOnPier = TRUE
		ENDIF
	ENDIF

	
	// Is the player ahead of the reporter on the pier?
	IF flagPlayerAheadOfReporterOnPier = TRUE
		// ...the reporter is suspicious
		IF NOT statusSuspicion = SCRASH2_SUSPICION_STATUS_PLAYER_AHEAD
			// ...the reporter was not previously suspicious, so set up the timer
// REMOVE OLD-STYLE SPOOK-O-METER
//			// NOTE: The timer will only display when there are 10 seconds left.
//			//			This is to prevent it flashing on and off at the boundaries.
// END REMOVE OLD-STYLE SPOOK-O-METER

			IF NOT statusSuspicion = SCRASH2_SUSPICION_STATUS_TOO_CLOSE
// REMOVE OLD-STYLE SPOOK-O-METER
//			AND NOT statusSuspicion = SCRASH2_SUSPICION_STATUS_TOO_FAR_AWAY
//				nFailure = 10
//				timerFailureConditions = m_mission_timer + 1000
// END REMOVE OLD-STYLE SPOOK-O-METER
// ADD NEW-STYLE SPOOK-O-METER
				timerTooClose = m_mission_timer + SCRASH2_SPOOKOMETER_INCREASE_msec
// END ADD NEW-STYLE SPOOK-O-METER
			ENDIF

			statusSuspicion = SCRASH2_SUSPICION_STATUS_PLAYER_AHEAD
			PRINT_NOW SCR2_23 30000 1
		ELSE
			// ...the reporter was previously suspicious, so maintain the timer
// REMOVE OLD-STYLE SPOOK-O-METER
/*
			IF timerFailureConditions < m_mission_timer
				// ...timer elapsed
				nFailure--
				IF nFailure < 0
					// ...time up
					nFailure = 0
					m_failed = TRUE
					m_fail_reason = SCRASH2_FAILED_REPORTER_SPOOKED
					CLEAR_SMALL_PRINTS
					RETURN
				ELSE
					// set up the timer again
					timerFailureConditions = m_mission_timer + 1000
				ENDIF
			ENDIF

			// Display (but only if there are 10 seconds left)
			IF nFailure <= 10
				g_SCrash2_spookKM = 10 - nFailure
				g_SCrash2_spookKM *= 10
			ENDIF
*/
// END REMOVE OLD-STYLE SPOOK-O-METER
// ADD NEW-STYLE SPOOK-O-METER
			IF timerTooClose < m_mission_timer
				// ...timer elapsed
				g_SCrash2_spookKM++
				IF g_SCrash2_spookKM > 100
					g_SCrash2_spookKM = 100
					m_failed = TRUE
					m_fail_reason = SCRASH2_FAILED_REPORTER_SPOOKED
					CLEAR_SMALL_PRINTS
					RETURN
				ELSE
					// Set up the timer again
					timerTooClose = m_mission_timer + SCRASH2_SPOOKOMETER_INCREASE_msec
				ENDIF
			ENDIF
// END ADD NEW-STYLE SPOOK-O-METER
		ENDIF

		RETURN
	ENDIF


	IF flagPlayerAheadOfReporterOnPier = FALSE
		GET_CHAR_COORDINATES charReporter	xposTemp	yposTemp	zposTemp
		GET_CHAR_COORDINATES scplayer		xposTemp2	yposTemp2	zposTemp2
		GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
		IF fTempDistance <= SCRASH2_TOO_CLOSE
			// ...when the reporter has just started driving, don't update the spook bar so that the
			//		changeover from walking to driving is less messy
			IF flagJustStartedDriving = FALSE
				flagTempFlag = TRUE
			ENDIF
		ELSE
			IF fTempDistance >= SCRASH2_TOO_FAR_AWAY
				// ...too far away
				flagTempFlag2 = TRUE
			ENDIF
		ENDIF
	ENDIF


	IF flagPlayerAheadOfReporterOnPier = FALSE
		// ...the player is not ahead of the reporter, so check if he was
		IF statusSuspicion = SCRASH2_SUSPICION_STATUS_PLAYER_AHEAD
			// ...the reporter was previously suspicious, so clear some variables
			IF flagTempFlag = FALSE
			AND flagTempFlag2 = FALSE
				CLEAR_SMALL_PRINTS
				statusSuspicion = SCRASH2_SUSPICION_STATUS_NONE
			ENDIF
		ENDIF
	ENDIF

	
	// Is the player too close?
	IF flagTempFlag = TRUE
		// ...too close
		IF NOT statusSuspicion = SCRASH2_SUSPICION_STATUS_TOO_CLOSE
// REMOVE OLD-STYLE SPOOK-O-METER
/*
			// ...the reporter was not previously suspicious, so set up the timer
			// NOTE: The timer will only display when there are 10 seconds left.
			//			This is to prevent it flashing on and off at the boundaries.
			IF NOT statusSuspicion = SCRASH2_SUSPICION_STATUS_PLAYER_AHEAD
				nFailure = 12
				timerFailureConditions = m_mission_timer + 1000
			ELSE
				IF nFailure <= 10
					// ...display 'Too Close' message
					// NOTE: Always in this case, because the player was previously ahead of the reporter on the pier
					//			so the player needs to know he is still in danger of failing the mission
					PRINT_NOW SCR2_08 30000 1
				ENDIF
			ENDIF
*/
// END REMOVE OLD-STYLE SPOOK-O-METER
// ADD NEW-STYLE SPOOK-O-METER
			timerTooClose = m_mission_timer + SCRASH2_SPOOKOMETER_INCREASE_msec

			IF NOT statusSuspicion = SCRASH2_SUSPICION_STATUS_PLAYER_AHEAD
				// ...not previously ahead of player, so display 'too close' message (first time only)
				IF flagDisplayedTooClose = FALSE
					PRINT_NOW SCR2_08 30000 1
					flagDisplayedTooClose = TRUE
				ENDIF
			ELSE
				// ...previously player ahead of reporter, so display 'too close' message (always, in this case)
				PRINT_NOW SCR2_08 30000 1
				flagDisplayedTooClose = TRUE
			ENDIF

			flagReporterSpooked = TRUE
// END ADD NEW-STYLE SPOOK-O-METER


			statusSuspicion = SCRASH2_SUSPICION_STATUS_TOO_CLOSE
		ELSE
// REMOVE OLD-STYLE SPOOK-O-METER
/*
			// ...the reporter was previously suspicious, so maintain the timer
			IF timerFailureConditions < m_mission_timer
				// ...timer elapsed
				nFailure--
				IF nFailure < 0
					// ...time up
					nFailure = 0
					m_failed = TRUE
					m_fail_reason = SCRASH2_FAILED_REPORTER_SPOOKED
					CLEAR_SMALL_PRINTS
					RETURN
				ELSE
					// set up the timer again
					timerFailureConditions = m_mission_timer + 1000
				ENDIF
			ENDIF

			// Check for an instant message
			IF nFailure > 10
			AND fTempDistance <= SCRASH2_TOO_CLOSE_INSTANT
				nFailure = 10
				timerFailureConditions = m_mission_timer + 1000
			ENDIF

			IF nFailure = 10
			AND flagDisplayedTooClose = FALSE
				// ...display 'too close' (once only)
				PRINT_NOW SCR2_08 30000 1
				flagDisplayedTooClose = TRUE
			ENDIF

			// Display (but only if there are 10 seconds left)
			IF nFailure <= 10
				g_SCrash2_spookKM = 10 - nFailure
				g_SCrash2_spookKM *= 10
			ENDIF
*/
// END REMOVE OLD-STYLE SPOOK-O-METER
// ADD NEW-STYLE SPOOK-O-METER
			// ...the reporter was previously suspicious, so maintain the timer
			IF timerTooClose < m_mission_timer
				// ...timer elapsed
				g_SCrash2_spookKM++
				IF g_SCrash2_spookKM > 100
					g_SCrash2_spookKM = 100
					m_failed = TRUE
					m_fail_reason = SCRASH2_FAILED_REPORTER_SPOOKED
					CLEAR_SMALL_PRINTS
					RETURN
				ELSE
					// Set up the timer again
					timerTooClose = m_mission_timer + SCRASH2_SPOOKOMETER_INCREASE_msec
				ENDIF
			ENDIF
// END ADD NEW-STYLE SPOOK-O-METER
		ENDIF
	ELSE
		// ...the reporter is not suspicious
		IF statusSuspicion = SCRASH2_SUSPICION_STATUS_TOO_CLOSE
			// ...the reporter was previously suspicious, so clear some variables
			CLEAR_SMALL_PRINTS
			statusSuspicion = SCRASH2_SUSPICION_STATUS_NONE
		ENDIF

// REMOVE OLD-STYLE SPOOK-O-METER
//		g_SCrash2_spookKM = 0
// END REMOVE OLD-STYLE SPOOK-O-METER
	ENDIF



	// Is the player too far away?
	// Only display this until the player is on the pier
	IF flagTempFlag2 = TRUE
		IF flagReporterOnPier = TRUE
		AND flagPlayerOnPier = TRUE
			flagTempFlag2 = FALSE
		ENDIF
	ENDIF

	IF flagTempFlag2 = TRUE
		// ...the reporter is suspicious
		IF statusSuspicion = SCRASH2_SUSPICION_STATUS_NONE
			// ...the player was not previously too far away, so set up the timer unless the reporter is on the pier
			// NOTE: The timer will only display when there are 10 seconds left.
			//			This is to prevent it flashing on and off at the boundaries.
			nFailure = 21
// REMOVE OLD-STYLE SPOOK-O-METER
//			timerFailureConditions = m_mission_timer + 1000
// END REMOVE OLD-STYLE SPOOK-O-METER
// ADD NEW-STYLE SPOOK-O-METER
			timerTooFarAway = m_mission_timer + 1000
// END ADD NEW-STYLE SPOOK-O-METER
			statusSuspicion = SCRASH2_SUSPICION_STATUS_TOO_FAR_AWAY
		ELSE
			// ...the player was previously too far away, so maintain the timer
// REMOVE OLD-STYLE SPOOK-O-METER
//			IF timerFailureConditions < m_mission_timer
// END REMOVE OLD-STYLE SPOOK-O-METER
// ADD NEW-STYLE SPOOK-O-METER
			IF timerTooFarAway < m_mission_timer
// END ADD NEW-STYLE SPOOK-O-METER
				// ...timer elapsed
				nFailure--
				IF nFailure < 0
					// ...time up
					nFailure = 0
					m_failed = TRUE
					m_fail_reason = SCRASH2_FAILED_LOST_THE_REPORTER
					CLEAR_SMALL_PRINTS
					RETURN
				ELSE
					// set up the timer again
// REMOVE OLD-STYLE SPOOK-O-METER
//					timerFailureConditions = m_mission_timer + 1000
// END REMOVE OLD-STYLE SPOOK-O-METER
// ADD NEW-STYLE SPOOK-O-METER
					timerTooFarAway = m_mission_timer + 1000
// END ADD NEW-STYLE SPOOK-O-METER
				ENDIF
			ENDIF

			// Check for an instant message display
			IF nFailure > 10
			AND fTempDistance >= SCRASH2_TOO_FAR_AWAY_INSTANT
				PRINT_NOW SCR2_16 30000 1
			ENDIF

			// Display (but only if there are 10 seconds left)
			IF nFailure = 10
				PRINT_NOW SCR2_16 30000 1
			ENDIF
		ENDIF
	ELSE
		// ...the player is not too far away
		IF statusSuspicion = SCRASH2_SUSPICION_STATUS_TOO_FAR_AWAY
			// ...the player was previously too far away, so clear the timers
			CLEAR_THIS_PRINT SCR2_16
			statusSuspicion = SCRASH2_SUSPICION_STATUS_NONE
		ENDIF
	ENDIF



// REMOVE OLD-STYLE SPOOK-O-METER
//	// Set up the timer for the next update
//	timerSuspicionNextUpdate = m_mission_timer + 500
// END REMOVE OLD-STYLE SPOOK-O-METER


// ADD NEW-STYLE SPOOK-O-METER
	IF NOT statusSuspicion = SCRASH2_SUSPICION_STATUS_TOO_CLOSE
	AND NOT statusSuspicion = SCRASH2_SUSPICION_STATUS_PLAYER_AHEAD
		IF flagPreviouslySuspicious = TRUE
			// ...start the 'decrease' timer
			timerTooClose = m_mission_timer + SCRASH2_SPOOKOMETER_DECREASE_msec
		ELSE
			// ...decrease the spook-o-meter if it still holds a value
			IF g_SCrash2_spookKM > 0
				IF timerTooClose < m_mission_timer
					g_SCrash2_spookKM--
					IF g_SCrash2_spookKM < 0
						g_SCrash2_spookKM = 0
						timerTooClose = 0
					ELSE
						timerTooClose = m_mission_timer + SCRASH2_SPOOKOMETER_DECREASE_msec
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
// END ADD NEW-STYLE SPOOK-O-METER



/* MODIFIED THE DISPLAY
	// Always update the safe distance bar
	// NOTE: flag1 indicates too close; flag2 indicates too far away
	flagTempFlag = FALSE
	flagTempFlag2 = FALSE
	GET_CHAR_COORDINATES charReporter	xposTemp	yposTemp	zposTemp
	GET_CHAR_COORDINATES scplayer		xposTemp2	yposTemp2	zposTemp2
	GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
	IF fTempDistance <= SCRASH2_SAFE_DISTANCE_LOW
		// ...too close
		g_SCrash2_safedistanceKM = 100

		// ...when the reporter has just started driving, don't display the 'too close' timer to allow the
		//		changeover from walking to driving to be less messy
		IF flagJustStartedDriving = FALSE
			flagTempFlag = TRUE
		ENDIF
	ELSE
		IF fTempDistance >= SCRASH2_SAFE_DISTANCE_HIGH
			// ...too far away
			g_SCrash2_safedistanceKM = 0
			flagTempFlag2 = TRUE
		ELSE
			// ...safe distance, so work out the bar display
			fTempFloat = fTempDistance - SCRASH2_SAFE_DISTANCE_LOW
			fTempFloat = fTempFloat * 100.0
			fTempFloat2 = SCRASH2_SAFE_DISTANCE_HIGH - SCRASH2_SAFE_DISTANCE_LOW
			fTempFloat = fTempFloat / fTempFloat2
			fTempFloat2 = 100.0 - fTempFloat
			g_SCrash2_safedistanceKM =# fTempFloat2
		ENDIF
	ENDIF

	// Check if it is time to do a suspicion check
	IF timerSuspicionNextUpdate > m_mission_timer
		RETURN
	ENDIF


	// Update the suspicion timers
	// Is the player too close?
	IF flagTempFlag = TRUE
		// ...the reporter is suspicious
		IF statusSuspicion = SCRASH2_SUSPICION_STATUS_NONE
			// ...the reporter was not previously suspicious, so set up the timer
			// NOTE: The timer will only display when there are 10 seconds left.
			//			This is to prevent it flashing on and off at the boundaries.
			g_SCrash2_failureKM = 12
			timerFailureConditions = m_mission_timer + 1000
			statusSuspicion = SCRASH2_SUSPICION_STATUS_TOO_CLOSE
		ELSE
			// ...the reporter was previously suspicious, so maintain the timer
			IF timerFailureConditions < m_mission_timer
				// ...timer elapsed
				g_SCrash2_failureKM--
				IF g_SCrash2_failureKM < 0
					// ...time up
					g_SCrash2_failureKM = 0
					m_failed = TRUE
					m_fail_reason = SCRASH2_FAILED_REPORTER_SPOOKED
					CLEAR_SMALL_PRINTS
					RETURN
				ELSE
					// set up the timer again
					timerFailureConditions = m_mission_timer + 1000
				ENDIF
			ENDIF

			// Display (but only if there are 10 seconds left)
			IF g_SCrash2_failureKM = 1
				// ...singular
				CLEAR_SMALL_PRINTS
				PRINT_WITH_NUMBER SCR2_09 g_SCrash2_failureKM 1000 1
			ELSE
				IF g_SCrash2_failureKM <= 10
					// ...plural
					CLEAR_SMALL_PRINTS
					PRINT_WITH_NUMBER SCR2_08 g_SCrash2_failureKM 1000 1
				ENDIF
			ENDIF
		ENDIF
	ELSE
		// ...the reporter is not suspicious
		IF statusSuspicion = SCRASH2_SUSPICION_STATUS_TOO_CLOSE
			// ...the reporter was previously suspicious, so clear the timers
			CLEAR_SMALL_PRINTS
			statusSuspicion = SCRASH2_SUSPICION_STATUS_NONE
		ENDIF
	ENDIF


	// Is the player too far away?
	// Only display this until the player is on the pier
	IF flagTempFlag2 = TRUE
		IF flagReporterOnPier = TRUE
		AND flagPlayerOnPier = TRUE
			flagTempFlag2 = FALSE
		ENDIF
	ENDIF

	IF flagTempFlag2 = TRUE
		// ...the reporter is suspicious
		IF statusSuspicion = SCRASH2_SUSPICION_STATUS_NONE
			// ...the player was not previously too far away, so set up the timer unless the reporter is on the pier
			// NOTE: The timer will only display when there are 10 seconds left.
			//			This is to prevent it flashing on and off at the boundaries.
			g_SCrash2_failureKM = 21
			timerFailureConditions = m_mission_timer + 1000
			statusSuspicion = SCRASH2_SUSPICION_STATUS_TOO_FAR_AWAY
		ELSE
			// ...the player was previously too far away, so maintain the timer
			IF timerFailureConditions < m_mission_timer
				// ...timer elapsed
				g_SCrash2_failureKM--
				IF g_SCrash2_failureKM < 0
					// ...time up
					g_SCrash2_failureKM = 0
					m_failed = TRUE
					m_fail_reason = SCRASH2_FAILED_LOST_THE_REPORTER
					CLEAR_SMALL_PRINTS
					RETURN
				ELSE
					// set up the timer again
					timerFailureConditions = m_mission_timer + 1000
				ENDIF
			ENDIF

			// Display (but only if there are 20 seconds left)
			IF g_SCrash2_failureKM = 1
				// ...singular
				CLEAR_SMALL_PRINTS
				PRINT_WITH_NUMBER SCR2_17 g_SCrash2_failureKM 1000 1
			ELSE
				IF g_SCrash2_failureKM <= 20
					// ...plural
					CLEAR_SMALL_PRINTS
					PRINT_WITH_NUMBER SCR2_16 g_SCrash2_failureKM 1000 1
				ENDIF
			ENDIF
		ENDIF
	ELSE
		// ...the player is not too far away
		IF statusSuspicion = SCRASH2_SUSPICION_STATUS_TOO_FAR_AWAY
			// ...the player was previously too far away, so clear the timers
			CLEAR_THIS_PRINT SCR2_17
			CLEAR_THIS_PRINT SCR2_16
			statusSuspicion = SCRASH2_SUSPICION_STATUS_NONE
		ENDIF
	ENDIF


	// Set up the timer for the next update
	timerSuspicionNextUpdate = m_mission_timer + 500
*/
RETURN


// ****************************************
// Initialise Spook Meter

SCrash2_Initialise_Spook_Meter:

	// Display the Safe Distance bar
	DISPLAY_ONSCREEN_COUNTER_WITH_STRING g_SCrash2_spookKM COUNTER_DISPLAY_BAR SCR2_52

RETURN





// *************************************************************************************************************
// 												HOUSEKEEPING GOSUBS   
// *************************************************************************************************************

// ****************************************
// DEBUG TOOLS
SCrash2_Debug_Tools:

	// Display mission stage variables for debug
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED  PS2_KEY_SPACE
		display_debug++

		IF display_debug > 2
			display_debug = 0
		ENDIF

		CLEAR_ALL_VIEW_VARIABLES
	ENDIF


	IF display_debug = 1
		IF NOT IS_CAR_DEAD carTrain
			GET_CAR_SPEED CarTrain fTempFloat
			SCrash2_view_debug[6] =# fTempFloat
		ENDIF

		// system variables
		SCrash2_view_debug[0] = m_stage
		SCrash2_view_debug[1] = m_goals
		SCrash2_view_debug[2] = TIMERA
		SCrash2_view_debug[3] = TIMERB
		SCrash2_view_debug[4] = m_mission_timer
		SCrash2_view_debug[5] = nTrainDistanceFromPlayer
//		SCrash2_view_debug[6] = 0
		SCrash2_view_debug[7] = nPlayerDistanceFromLS
		// First two lines are so that the important data displayed is not hidden by other text
		VIEW_INTEGER_VARIABLE SCrash2_view_debug[0] m_stage
		VIEW_INTEGER_VARIABLE SCrash2_view_debug[1] m_goals
		VIEW_INTEGER_VARIABLE SCrash2_view_debug[4] m_mission_timer
		VIEW_INTEGER_VARIABLE SCrash2_view_debug[5] Train_distance
		IF NOT IS_CAR_DEAD carTrain
			VIEW_INTEGER_VARIABLE SCrash2_view_debug[6] Train_speed
		ENDIF
		VIEW_INTEGER_VARIABLE SCrash2_view_debug[7] Player_from_LS
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
SCrash2_Debug_Shortcuts:

	IF NOT IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_J
		RETURN
	ENDIF

	// Stage 1 jump
	IF m_stage = 1
		// Warp the player to the sniper rifle
		CLEAR_AREA -2063.5474 253.2754 34.3043 0.5 TRUE
		SET_CHAR_COORDINATES scplayer -2063.5474 253.2754 34.3043
		SET_CHAR_HEADING scplayer 0.0000
	ENDIF

	// Stage 2 jump
	IF m_stage = 2
	AND m_goals < 4
		IF existsTrain = 0
			// ...create the train
			CLEAR_AREA -1944.0250 135.5236 24.7109 100.0 TRUE
			CREATE_MISSION_TRAIN 11 -1944.0250 135.5236 24.7109 FALSE carTrain
			SET_TRAIN_SPEED carTrain 0.0
			SET_TRAIN_CRUISE_SPEED carTrain 0.0
			existsTrain = 1
		ENDIF

		// Make the passenger enter the train (warping almost immediately)
		IF NOT IS_CAR_DEAD carTrain
			GET_TRAIN_CARRIAGE carTrain 1 carCarriage
		ENDIF
		
		IF NOT IS_CHAR_DEAD charReporter
			TASK_ENTER_CAR_AS_PASSENGER charReporter carCarriage 100 -1
		ENDIF

		// Warp the player to the station
		CLEAR_AREA -1989.1611 208.5921 26.6797 0.5 TRUE
		SET_CHAR_COORDINATES scplayer -1989.1611 208.5921 26.6797
		SET_CHAR_HEADING scplayer 221.8946

		// Set the 'arrival' timer
		timerTrain = m_mission_timer + 60000

		m_goals = 4
	ENDIF

	// Stage 3 jump
	IF m_stage = 3
	AND m_goals < 4
		IF m_goals = 1
			// Warp the train
			IF NOT IS_CAR_DEAD carTrain
				SET_MISSION_TRAIN_COORDINATES carTrain 779.9327 -1334.1549 -2.5340
			ENDIF
		ELSE
			m_goals = 4
		ENDIF

		// Warp the player
		CLEAR_AREA 815.0453 -1323.0974 12.5036 5.0 TRUE
		SET_CHAR_COORDINATES scplayer 815.0453 -1323.0974 12.5036
		SET_CHAR_HEADING scplayer 187.1420
	ENDIF

	// Stage 4 jump
	IF m_stage = 4
	AND m_goals < 11
		// Warp the reporter
		IF NOT IS_CHAR_DEAD charReporter
			SET_CHAR_COORDINATES	charReporter 803.7784 -1342.9255 12.5469
			SET_CHAR_HEADING		charReporter 92.3748
		ENDIF

		// Warp the taxi
		IF existsTaxiLS = TRUE
			IF NOT IS_CAR_DEAD carTaxiLS
				SET_CAR_COORDINATES	carTaxiLS 799.7784 -1342.9255 12.5469
				SET_CAR_HEADING		carTaxiLS 7.9128
			ENDIF
		ELSE
			CLEAR_AREA 799.7784 -1342.9255 12.5469 10.0 TRUE
			CREATE_CAR TAXI 799.7784 -1342.9255 12.5469 carTaxiLS
			SET_CAR_HEADING carTaxiLS 7.9128
			CREATE_CHAR_INSIDE_CAR carTaxiLS PEDTYPE_CIVMALE BMYBE charTaxiDriverLS
			existsTaxiLS = TRUE
		ENDIF

		// No longer need the trains
		IF NOT IS_CAR_DEAD carTrain
		AND existsTrain = TRUE
			SET_TRAIN_CRUISE_SPEED carTrain 30.0
			MARK_MISSION_TRAIN_AS_NO_LONGER_NEEDED carTrain
			existsTrain = FALSE
		ENDIF

		// Various flags
		flagPlayerShootingNearWalkingReporter	= FALSE
		flagReporterSpookedAndFleeing			= FALSE
		flagTaxiLSCreationPositionAlpha			= TRUE

		// Remove Market Station blip
		REMOVE_BLIP blipDestination

		m_goals = 11
	ENDIF

	// Stage 5 jump
	IF m_stage = 5
		xposDestination = 366.4698
		yposDestination = -2030.6428
		zposDestination = 6.6582

		// Warp reporter
		IF NOT IS_CHAR_DEAD charReporter
			CLEAR_AREA 366.4698 -2030.6428 6.6582 5.0 TRUE
			SET_CHAR_COORDINATES charReporter 366.4698 -2030.6428 6.6582
			SET_CHAR_HEADING charReporter 179.2269
		ENDIF

		// Warp player
		CLEAR_AREA 383.3648 -1930.4478 6.8301 5.0 TRUE
		SET_CHAR_COORDINATES scplayer 383.3648 -1930.4478 6.8301
		SET_CHAR_HEADING scplayer 88.4042

		m_goals = 1

	ENDIF

RETURN



// ****************************************
// FRAME COUNTER (Useful if processor scheduling is needed)
SCrash2_Frame_Counter:

	m_frame_num++
	IF m_frame_num > 9
		m_frame_num = 0
	ENDIF

RETURN



// ****************************************
// ADDITIONAL TIMERS
SCrash2_Additional_Timers:

	GET_GAME_TIMER m_this_frame_time
	m_time_diff = m_this_frame_time - m_last_frame_time 
	m_last_frame_time = m_this_frame_time

	m_mission_timer += m_time_diff

RETURN



// =================================================================
// GOSUB: Next_Stage (sets flags and timers for next mission stage)
// =================================================================
SCrash2_next_stage:

   	m_stage++
   	m_goals        = 0
   	TIMERA 		   = 0
   	TIMERB		   = 0

RETURN					





// *************************************************************************************************************
//										INTRO CUTSCENE GOSUB
// *************************************************************************************************************

SCrash2_Intro_Cutscene:

	SET_PLAYER_CONTROL player1 OFF

	LOAD_CUTSCENE SCRASH2
	WHILE NOT HAS_CUTSCENE_LOADED
		WAIT 0
	ENDWHILE

	SET_AREA_VISIBLE 1
	SET_CHAR_AREA_VISIBLE scplayer 1
	LOAD_SCENE -2031.0 149.0 29.0	 

	START_CUTSCENE

	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_IN


	WHILE NOT HAS_CUTSCENE_FINISHED
		WAIT 0
	ENDWHILE

	SET_FADING_COLOUR 0 0 0
	DO_FADE 0 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	CLEAR_CUTSCENE

	SET_AREA_VISIBLE 0
	SET_CHAR_AREA_VISIBLE scplayer 0

	RESTORE_CAMERA_JUMPCUT
	SET_PLAYER_CONTROL player1 ON
	SWITCH_WIDESCREEN OFF

RETURN




// *************************************************************************************************************
// 												INITIALISATION GOSUBS   
// *************************************************************************************************************

SCrash2_Initialisation:

	WHILE NOT IS_PLAYER_PLAYING player1
		WAIT 0
	ENDWHILE


	GOSUB SCrash2_Load_All_Models
	GOSUB SCrash2_Load_All_Anims

	
	// Create the motorbike outside the station
	CLEAR_AREA -1978.8496 169.6607 26.6953 3.0 FALSE
	CREATE_CAR SANCHEZ -1978.8496 169.6607 26.6953 carMotorbike
	SET_CAR_HEADING carMotorbike 90.1127
	flagCheckForRemoveMotorbike = TRUE


	// Position the player
//	CLEAR_AREA -2612.7029 828.8597 48.9688 0.5 0
//	LOAD_SCENE -2612.7029 828.8597 48.9688
//	SET_CHAR_COORDINATES scplayer -2612.7029 828.8597 48.9688 
// 	SET_CHAR_HEADING scplayer 270.0
//	CLEAR_AREA -2036.2223 169.9600 27.8359 0.5 0
//	LOAD_SCENE -2036.2223 169.9600 27.8359
//	SET_CHAR_COORDINATES scplayer -2036.2223 169.9600 27.8359 
//	SET_CHAR_HEADING scplayer 270.0
	CLEAR_AREA -2033.3392 148.6139 27.8359 0.5 0
	LOAD_SCENE -2033.3392 148.6139 27.8359
	SET_CHAR_COORDINATES scplayer -2033.3392 148.6139 27.8359 
	SET_CHAR_HEADING scplayer 270.0
	SET_CAMERA_BEHIND_PLAYER
	SET_PLAYER_CONTROL player1 ON


	// Other stuff
	SWITCH_RANDOM_TRAINS OFF


	// Respawn point
	SET_RESPAWN_POINT_FOR_DURATION_OF_MISSION -2026.0613 148.8280 27.8359


	// Reduce the resistance of the railroad tracks so that the bike moves a bit faster
	SET_RAILTRACK_RESISTANCE_MULT 0.4


	// Fade in
	WAIT 1000
	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_IN

	GOSUB SCrash2_next_stage

RETURN


// ***********************************
// LOAD ALL MODELS

SCrash2_Load_All_Models:

	// CARS
	REQUEST_MODEL STREAK		// Train engine
	REQUEST_MODEL STREAKC		// Train carriage
	REQUEST_MODEL FREIGHT		// Other train engines
	REQUEST_MODEL FREIFLAT		// Other train carriages
	REQUEST_MODEL SANCHEZ		// Motorbike available for player to use
//	REQUEST_MODEL TAXI			// Taxi	(NOW REQUESTED JUST PRIOR TO IT BEING REQUIRED)

	// PEDS
	REQUEST_MODEL WMYBU			// Reporter
	REQUEST_MODEL BMYBE			// Taxi driver
	REQUEST_MODEL HMYRI			// Target

	// GUNS
	REQUEST_MODEL SNIPER		// Sniper rifle
	REQUEST_MODEL COLT45		// Police Handgun

	// OBJECTS

	LOAD_ALL_MODELS_NOW


	// Are Cars Loaded?
	WHILE NOT HAS_MODEL_LOADED	STREAK
	OR NOT HAS_MODEL_LOADED		STREAKC
	OR NOT HAS_MODEL_LOADED		FREIGHT
	OR NOT HAS_MODEL_LOADED		FREIFLAT
	OR NOT HAS_MODEL_LOADED		SANCHEZ
//	OR NOT HAS_MODEL_LOADED		TAXI		// (NOW REQUESTED JUST PRIOR TO IT BEING REQUIRED)
		WAIT 0
	ENDWHILE

	// Are Peds Loaded?
	WHILE NOT HAS_MODEL_LOADED	WMYBU
	OR NOT HAS_MODEL_LOADED		BMYBE
	OR NOT HAS_MODEL_LOADED		HMYRI
		WAIT 0
	ENDWHILE

	// Are weapons loaded?
	WHILE NOT HAS_MODEL_LOADED	SNIPER
	OR NOT HAS_MODEL_LOADED		COLT45
		WAIT 0
	ENDWHILE

	// Are the Objects loaded?

RETURN


// ***********************************
// LOAD ALL ANIMS

SCrash2_Load_All_Anims:

	REQUEST_ANIMATION MISC				// for hailing a taxi
	REQUEST_ANIMATION GANGS				// for shaking hands


	// Are anims loaded?
	WHILE NOT HAS_ANIMATION_LOADED	MISC
	OR NOT HAS_ANIMATION_LOADED		GANGS
		WAIT 0
	ENDWHILE

RETURN






// *************************************************************************************************************
//										ENTITY CREATION GOSUBS  
// *************************************************************************************************************





// *************************************************************************************************************
//										END OF LEVEL GOSUBS - fail, pass, cleanup  
// *************************************************************************************************************

// FAIL
mission_failed_SCrash2:

	PRINT_BIG M_FAIL 5000 1

	// Display reason for failure (if there is a reason)
	CLEAR_SMALL_PRINTS
	SWITCH m_fail_reason
		CASE SCRASH2_FAILED_TRAIN_GONE
			PRINT_NOW SCR2_80 5000 1
			BREAK
		CASE SCRASH2_FAILED_REPORTER_DEAD
			PRINT_NOW SCR2_81 5000 1
			BREAK
		CASE SCRASH2_FAILED_LOST_THE_REPORTER
			PRINT_NOW SCR2_82 5000 1
			BREAK
		CASE SCRASH2_FAILED_MEETING_OVER
			PRINT_NOW SCR2_83 5000 1
			BREAK
		CASE SCRASH2_FAILED_REPORTER_ESCAPED
			PRINT_NOW SCR2_84 5000 1
			BREAK
		CASE SCRASH2_FAILED_TARGET_ESCAPED
			PRINT_NOW SCR2_85 5000 1
			BREAK
		CASE SCRASH2_FAILED_REPORTER_SPOOKED
			PRINT_NOW SCR2_86 5000 1
			BREAK
	ENDSWITCH

	flagRemoveSniperRifle = TRUE

RETURN


// PASS
mission_passed_SCrash2:

	CLEAR_SMALL_PRINTS

//	PRINT_WITH_NUMBER_BIG M_PASS 30000 5000 1
//	ADD_SCORE player1 30000

	// This mission gives no cash or respect reward, so use M_PASSD label
	PRINT_WITH_NUMBER_BIG M_PASSD 0 5000 1
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1
	REGISTER_MISSION_PASSED SCRA_2
	PLAYER_MADE_PROGRESS 1

	flag_scrash_mission_counter++
	REMOVE_BLIP scrash_contact_blip

	REMOVE_BLIP garage_contact_blip                                                       
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ garage_blip_icon garage_contact_blip


	flagRemoveSniperRifle = FALSE

RETURN


// CLEANUP
mission_cleanup_SCrash2:

	// Get rid of the Player's mission specific weapons
	// ...if the player already had a sniper rifle then only remove additional ammo
	IF IS_PLAYER_PLAYING player1
		IF flagRemoveSniperRifle = TRUE
			IF nSniperAmmoAtStartOfMission = 0
				REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_SNIPERRIFLE
			ELSE
				GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_SNIPERRIFLE nTempInt
				IF nSniperAmmoAtStartOfMission < nTempInt
					// ...sniper ammo now is more than at the start of the mission, so remove the excess
					SET_CHAR_AMMO scplayer WEAPONTYPE_SNIPERRIFLE nSniperAmmoAtStartOfMission
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	IF existsTaxiLS = TRUE
	AND NOT IS_CAR_DEAD carTaxiLS
		SET_TAXI_LIGHTS carTaxiLS OFF
	ENDIF

	IF NOT IS_CHAR_DEAD charTaxiDriverLS
		CLEAR_CHAR_TASKS charTaxiDriverLS
	ENDIF


	IF NOT IS_CHAR_DEAD charReporter
		CLEAR_LOOK_AT charReporter
	ENDIF


	// Pickups
	REMOVE_PICKUP pickupSniperRifle


	// Entity Clearup
	MARK_CHAR_AS_NO_LONGER_NEEDED			charReporter
	MARK_CAR_AS_NO_LONGER_NEEDED			cartaxiLS
	MARK_CHAR_AS_NO_LONGER_NEEDED			charTaxiDriverLS
	MARK_CHAR_AS_NO_LONGER_NEEDED			charTarget
	MARK_MODEL_AS_NO_LONGER_NEEDED			COLT45
	MARK_MODEL_AS_NO_LONGER_NEEDED			SNIPER


	REPEAT SCRASH2_NUMBER_OF_OTHER_TRAINS nLoop
		MARK_MISSION_TRAIN_AS_NO_LONGER_NEEDED carOtherTrain[nLoop]
	ENDREPEAT

	IF existsTrain = TRUE
		IF NOT IS_CAR_DEAD carTrain
			SET_TRAIN_CRUISE_SPEED carTrain 30.0
			MARK_MISSION_TRAIN_AS_NO_LONGER_NEEDED	carTrain
		ENDIF
	ENDIF

	
	// Blips
	REMOVE_BLIP blipDestination
	REMOVE_BLIP blipTrain
	REMOVE_BLIP blipReporter
	REMOVE_BLIP blipTarget
	REMOVE_BLIP blipSniperRifle


	// Counters
// REMOVE TRAIN PROGRESS BAR
//	CLEAR_ONSCREEN_COUNTER g_SCrash2_distanceKM
// END REMOVE TRAIN PROGRESS BAR
	CLEAR_ONSCREEN_COUNTER g_SCrash2_spookKM


	// Animation Clearup
	REMOVE_ANIMATION MISC
	REMOVE_ANIMATION GANGS


	// Help Text
	CLEAR_HELP


	// Models
	// ...cars
	MARK_MODEL_AS_NO_LONGER_NEEDED STREAK
	MARK_MODEL_AS_NO_LONGER_NEEDED STREAKC
	MARK_MODEL_AS_NO_LONGER_NEEDED FREIGHT
	MARK_MODEL_AS_NO_LONGER_NEEDED FREIFLAT
	MARK_MODEL_AS_NO_LONGER_NEEDED TAXI
	MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ

	// ...peds
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYBU
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYBE
	MARK_MODEL_AS_NO_LONGER_NEEDED HMYRI

	// ...guns
	MARK_MODEL_AS_NO_LONGER_NEEDED SNIPER
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45



	// === RESTORE ENVIRONMENT SETTINGS ===
	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_WANTED_MULTIPLIER 1.0


	// Switch on emergency services
	SWITCH_EMERGENCY_SERVICES ON
	SWITCH_RANDOM_TRAINS ON


	// Make sure the mobile phone doesn't ring immediately after a mission
	GET_GAME_TIMER timer_mobile_start


	// Reactivate roads
	SWITCH_ROADS_BACK_TO_ORIGINAL 789.5950 -1387.9701 11.5469 807.2843 -1334.5457 13.7250
	SWITCH_ROADS_BACK_TO_ORIGINAL 804.9978 -1414.0320 11.3801 908.0844 -1386.6179 13.5524
	SWITCH_ROADS_BACK_TO_ORIGINAL 802.1913 -1335.0664 11.1828 911.4662 -1313.0450 13.7469


	// Reset the railroad tracks resistance multiplier (negative means use default)
	SET_RAILTRACK_RESISTANCE_MULT -1.0


	flag_player_on_mission = 0
	MISSION_HAS_FINISHED

RETURN
	 

}