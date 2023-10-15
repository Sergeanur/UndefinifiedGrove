MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
//
//			   MISSION NAME : Taxiodd
//				     AUTHOR : Keith
//		        DESCRIPTION : Crazy Taxi - GTA style
//
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

{
// *************************************************************************************************************
//								GENERAL INITIALISATION - Every level requires this
// *************************************************************************************************************


SCRIPT_NAME Taxiodd


// Global Variables
VAR_INT		g_Taxiodd_faresKM
VAR_INT		g_Taxiodd_timerKM
VAR_INT		g_Taxiodd_bonusKM



// TEMP: Destination Testing (set to TRUE to enter destination testing)
LVAR_INT	TEMP_flagDestinationTesting
TEMP_flagDestinationTesting = FALSE
// END




// Mission Start stuff
GOSUB Taxiodd_Mission_Start

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB Taxiodd_Mission_Ended
ENDIF

GOSUB Taxiodd_Mission_Cleanup

MISSION_END


// Global Setup
Taxiodd_Mission_Start:

IF done_taxiodd_progress = FALSE
	REGISTER_MISSION_GIVEN
ENDIF

flag_player_on_mission	= TRUE
flag_player_on_oddjob	= TRUE



// Check for conditions that stop the taxi missions from starting
LVAR_INT nPassengers
LVAR_INT nHealth
LVAR_INT carTest
IF NOT IS_CHAR_DEAD scplayer
	IF IS_CHAR_IN_TAXI scplayer
		// ...player is in a taxi
		STORE_CAR_CHAR_IS_IN scplayer carTest

		// Existing Passengers Check
		GET_NUMBER_OF_PASSENGERS carTest nPassengers
		IF NOT nPassengers = 0
			// ...don't start the mission if there are already passengers in the car
			PRINT_NOW TX_FULL 5000 1

			WAIT 4500
			CLEAR_PRINTS
			RETURN
		ENDIF

		// Car Already Trashed Check
		GET_CAR_HEALTH carTest nHealth
		IF NOT IS_CAR_HEALTH_GREATER carTest 400
			// ...don't start the mission if the car is already trashed
			PRINT_NOW TX_ILL 5000 1

			WAIT 4500
			CLEAR_PRINTS
			RETURN
		ENDIF
	ENDIF
ENDIF




// KEITH: Make sure the mod garage is not active during taxi missions
// NOTE: Store the initial state because it only becomes available after a certain mission
// NOTE: 1 means STOP GARAGE (or turn it off)
VAR_INT nHoldModGarageState
nHoldModGarageState = stop_gargae_for_neil
stop_gargae_for_neil = 1



// general variables that are in every mission - DON'T DECLARE MISSION SPECIFIC VARIABLES HERE
LVAR_INT 	m_stage
LVAR_INT 	m_goals
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
VAR_INT 	Taxiodd_view_debug[8]	// GLOBAL (for output)
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
LVAR_FLOAT	xposTemp3	yposTemp3	zposTemp3
LVAR_FLOAT	xdiffTemp	ydiffTemp	zdiffTemp
LVAR_FLOAT	xdiffsqTemp	ydiffsqTemp	zdiffsqTemp
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
m_quit			= 0
m_pause			= 0
m_status		= 0

m_mission_timer	= 0

display_debug	= 0

TIMERA = 0
TIMERB = 0



// Consts

// ...reasons for End Mission
CONST_INT	TAXIODD_END_UNKNOWN								0
CONST_INT	TAXIODD_END_NOT_IN_TAXI							1
CONST_INT	TAXIODD_END_OUT_OF_TIME							2
CONST_INT	TAXIODD_END_TAXI_FUCKED							3
// Stage 1 Goals
CONST_INT	TAXIODD_STAGE1GOAL_FIND_POTENTIAL_CUSTOMER		1
CONST_INT	TAXIODD_STAGE1GOAL_PICKUP_POTENTIAL_CUSTOMER	2
CONST_INT	TAXIODD_STAGE1GOAL_GET_TAXI_REPAIRED			10
CONST_INT	TAXIODD_STAGE1GOAL_DISPLAYING_NITRO_MESSAGE		20
// Taxi Look At Thresholds
CONST_FLOAT	TAXIODD_TAXI_LOOK_AT_THRESHOLD					4.0
// In a Row bonus
CONST_INT	TAXIODD_IN_A_ROW_JOBS_INCREASE					5
CONST_INT	TAXIODD_IN_A_ROW_BONUS_INCREASE					500
// Pay and Spray
CONST_INT	TAXIODD_MAX_PAY_AND_SPRAY_ON_DISPLAY			3
// Pickup distances
CONST_FLOAT	TAXIODD_ENTER_CAR_DISTANCE						10.0
CONST_FLOAT TAXIODD_HAIL_TAXI_SPEECH_DISTANCE				10.0
CONST_INT	TAXIODD_ALLOW_HAIL_TO_CANCEL_TIME				300
// Direction Helper objects
CONST_INT	TAXIODD_HELPER_FRONT							0
CONST_INT	TAXIODD_HELPER_BACK								1
CONST_INT	TAXIODD_HELPER_LEFT								2
CONST_INT	TAXIODD_HELPER_RIGHT							3
// -----------
CONST_INT	TAXIODD_MAX_HELPERS								4	// Above + 1
// Passenger Doors
CONST_INT	TAXIODD_REAR_DRIVER_DOOR						1
CONST_INT	TAXIODD_REAR_PASSENGER_DOOR						2
// Speed Bonus (Tip) consts
CONST_INT	TAXIODD_TIP_PERCENT								95
CONST_INT	TAXIODD_DAMAGE_POINT_COST_msec					180
// General
CONST_INT	TAXIODD_NO_NEARBY_PEDS							-1
CONST_INT	TAXIODD_PERSIST									-2
CONST_INT	TAXIODD_NO_CHAR_MODEL							-1



// Mission Specific Variables
// Integer Variables
// ...chars 				(char)
LVAR_INT	charCustomer
//LVAR_INT	charPreviousCustomer
// ...cars 					(car)
LVAR_INT	carTaxi
// ...objects 				(object)
LVAR_INT	objectHelpers[TAXIODD_MAX_HELPERS]
// ...models				(model)
LVAR_INT	modelCustomer
LVAR_INT	modelPreviousCustomer
// ...blips 				(blip)
LVAR_INT	blipCustomer
LVAR_INT	blipDestination
LVAR_INT	blipPayAndSpray[TAXIODD_MAX_PAY_AND_SPRAY_ON_DISPLAY]
// ...pickups 				(pickup)
// ...fx systems 			(fx)
// ...decision makers		(dm)
LVAR_INT	dmEmpty
// ...AI Status				(ai)
// ...general status		(status)
// ...Timers				(timer)
LVAR_INT	timerPickupText
LVAR_INT	timerJobStartTime
LVAR_INT	timerJobBonusTime
LVAR_INT	timerTipBarHelp
LVAR_INT	timerNitro
LVAR_INT	timerAllowHailToCancel
// ...Counters				(count)
// ...mission specific		(n)
LVAR_INT	nEndMissionReason
LVAR_INT	nEarnings
LVAR_INT	nDestinationID
LVAR_INT	nDestinationDistance
LVAR_INT	nBonusTimeFromBadPlace
LVAR_INT	nBonusTimeToBadPlace
LVAR_INT	nNextInARowJobs
LVAR_INT	nNextInARowBonus
LVAR_INT	nIgnore
LVAR_INT	nMissionStartHealth


// Text Label Variables


// Float Variables
// ...area variables 		(xlo, ylo, zlo, xhi, yhi, zhi)
// ...position variables	(xpos, ypos, zpos)
LVAR_FLOAT	xposTaxiLookAT	yposTaxiLookAt	zposTaxiLookAt
LVAR_FLOAT	xposDestination	yposDestination	zposDestination


// Boolean Variables
// ...flags					(flag)
LVAR_INT	flagEndMission
LVAR_INT	flagPassThisJob
LVAR_INT	flagFailThisJob
LVAR_INT	flagPlayerInTaxi	
LVAR_INT	flagPotentialCustomerSelected
LVAR_INT	flagPlayerStoppedCloseToPotentialCustomer
LVAR_INT	flagTimerOnDisplay
LVAR_INT	flagArrivedAtDestination
LVAR_INT	flagBonusOnDisplay
LVAR_INT	flagTaxiNeedsRepaired
LVAR_INT	flagPayAndSprayOnDisplay
LVAR_INT	flagWaitForQuitButtonRelease
LVAR_INT	flagPotentialCustomerFound
LVAR_INT	flagDisplayingNoNearbyPedsText
LVAR_INT	flagSaidTaxiBail
LVAR_INT	flagImmediateRepair
LVAR_INT	flagOnRightSide
LVAR_INT	flagAtFront
LVAR_INT	flagReceivingInARowBonus
LVAR_INT	flagDisplayedTipBarHelp
LVAR_INT	flagNitroOnDisplay
LVAR_INT	flagSaidTaxiHail
// ...exists flags			(exists)
// Clear Exists Flags
// ...loaded flags			<loaded>
// Clear Loaded Flags


// Clear timers
timerPickupText = 0


// Clear important flags
flagPlayerInTaxi				= FALSE
flagTimerOnDisplay				= FALSE
flagBonusOnDisplay				= FALSE
flagTaxiNeedsRepaired			= FALSE
flagPayAndSprayOnDisplay		= FALSE
flagWaitForQuitButtonRelease	= FALSE
flagPotentialCustomerFound		= FALSE
flagDisplayingNoNearbyPedsText	= FALSE
flagImmediateRepair				= FALSE
flagOnRightSide					= FALSE
flagAtFront						= FALSE
flagReceivingInARowBonus		= FALSE
flagDisplayedTipBarHelp			= FALSE
flagNitroOnDisplay				= FALSE
flagSaidTaxiHail				= FALSE


// End Mission Controls
flagEndMission			= FALSE
flagPassThisJob			= FALSE
flagFailThisJob			= FALSE
nEndMissionReason		= TAXIODD_END_UNKNOWN



// Cleanup testing
LVAR_INT	nCleanupTest_MissionPeds
nCleanupTest_MissionPeds = 0


// ***** FAKE ENTITY CREATION TO FOOL THE COMPILER *****
// The compiler just needs to verify there is a CREATE_ before usage
IF m_stage = -99
	
	WRITE_DEBUG SHOULD_NEVER_BE_IN_FAKE_ENTITY_CREATION

	// Cars
	CREATE_CAR TAXI 0.0 0.0 0.0 carTaxi

	// Peds
	CREATE_CHAR PEDTYPE_CIVMALE WMYBU 0.0 0.0 0.0 charCustomer
//	CREATE_CHAR PEDTYPE_CIVMALE WMYBU 0.0 0.0 0.0 charPreviousCustomer

	// Objects
	REPEAT TAXIODD_MAX_HELPERS nLoop
		CREATE_OBJECT CR_AMMOBOX 0.0 0.0 0.0 objectHelpers[nLoop]
	ENDREPEAT

	// Blips
 	ADD_BLIP_FOR_CHAR charCustomer blipCustomer
 	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 blipDestination

	REPEAT TAXIODD_MAX_PAY_AND_SPRAY_ON_DISPLAY nLoop
		ADD_BLIP_FOR_COORD 0.0 0.0 0.0 blipPayAndSpray[nLoop]
	ENDREPEAT

ENDIF



// Mission Text
LOAD_MISSION_TEXT TAXI1


// Intro Cutscene
GOSUB Taxiodd_Intro_Cutscene


// Load Char Mission Decision Makers
// NOTE: Do it before the Initialisation because they make the game pause. Done here, the pause is hidden by a fade.
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY dmEmpty


// Mission Initialisation
GOSUB Taxiodd_Initialisation





// TEMP: Destination Testing
IF TEMP_flagDestinationTesting = TRUE
	GOTO Taxiodd_Destination_Testing
ENDIF
// END TEMP





// *************************************************************************************************************
//								                 MISSION LOOP
// *************************************************************************************************************
Taxiodd_Mission_Loop:

	WAIT 0


	// Debug Stuff
	GOSUB Taxiodd_Debug_Tools
	GOSUB Taxiodd_Debug_Shortcuts

	IF m_quit = TRUE
	OR m_pause = TRUE
		GOTO Taxiodd_End_Of_Main_Loop
	ENDIF


	// Housekeeping
	GOSUB Taxiodd_Frame_Counter
	GOSUB Taxiodd_Additional_Timers


	// Special conditions
	IF IS_CHAR_DEAD scplayer
		flagEndMission = TRUE
		GOTO Taxiodd_End_Of_Main_Loop
	ENDIF		


	// Mission Stage processing
	// *** INITIALISATION NOW TAKES PLACE BEFORE THE MAIN LOOP ***
	IF m_stage = 0
		WRITE_DEBUG STAGE_SHOULD_NEVER_BE_0_IN_MAIN_LOOP
	ENDIF


	// CONTINUOUS UPDATE ROUTINES
	// --------------------------

	// ...player quit?
	flagTempFlag = FALSE

	GET_CONTROLLER_MODE controlmode
	IF NOT controlmode = 3
		IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
			// ...quit button currently being pressed
			flagTempFlag = TRUE
		ENDIF
	ELSE
		IF IS_BUTTON_PRESSED PAD1 SQUARE
			// ...quit button currently being pressed
			flagTempFlag = TRUE
		ENDIF
	ENDIF
	
	IF flagWaitForQuitButtonRelease = FALSE
		// ...checking for the quit button being pressed
		IF flagTempFlag = TRUE
			// ...button pressed, so wait for release before quitting (to prevent immediate mission restart again)
			IF IS_PLAYER_PLAYING player1
				flagWaitForQuitButtonRelease = TRUE
			ENDIF
		ENDIF
	ELSE
		// ...checking for the quit button being released
		IF flagTempFlag = FALSE
			IF IS_PLAYER_PLAYING player1
				// ...button released, so quit the mission
				flagEndMission = TRUE
				GOTO Taxiodd_End_Of_Main_Loop
			ENDIF
		ENDIF
	ENDIF

	// ...taxi dead?
	IF IS_CAR_DEAD carTaxi
		// FOR NOW: End the mission
		flagEndMission		= TRUE

		GOTO Taxiodd_End_Of_Main_Loop
	ENDIF

	// ...taxi on fire?
	GET_CAR_HEALTH carTaxi nTempInt
	IF nTempInt <= 250
		IF NOT IS_CHAR_DEAD charCustomer
			IF IS_CHAR_IN_CAR charCustomer carTaxi
				// FOR NOW: Fail the mission
				PRINT_NOW TX_FLED 5000 1

				IF flagSaidTaxiBail = FALSE
				SET_CHAR_SAY_CONTEXT charCustomer CONTEXT_GLOBAL_TAXI_BAIL nIgnore
					flagSaidTaxiBail = TRUE
				ENDIF
			ENDIF
		ENDIF

		flagFailThisJob		= TRUE

		GOTO Taxiodd_End_Of_Main_Loop
	ENDIF

	// ...out of time?
	IF flagTimerOnDisplay = TRUE
		IF g_Taxiodd_timerKM = 0
			flagEndMission		= TRUE
			nEndMissionReason	= TAXIODD_END_OUT_OF_TIME

			GOTO Taxiodd_End_Of_Main_Loop
		ENDIF
	ENDIF

	// ...player in taxi?
	GOSUB Store_Player_Taxi
	IF flagPlayerInTaxi = FALSE
		// FOR NOW: Fail the mission
		flagEndMission		= TRUE
		nEndMissionReason	= TAXIODD_END_NOT_IN_TAXI

		GOTO Taxiodd_End_Of_Main_Loop
	ENDIF


	// ...Stage 1: Pickup a Customer
	IF m_stage = 1
		GOSUB Taxiodd_Stage_Pickup_Customer
	ENDIF 


	// ...Stage 2: Go To Destination
	IF m_stage = 2
		GOSUB Taxiodd_Stage_Go_To_Destination
	ENDIF


	// ...final stage
	IF m_stage = 3
		flagPassThisJob = TRUE
	ENDIF



// End of Main Loop
// ***** DON'T CHANGE *****
Taxiodd_End_Of_Main_Loop:

	IF m_quit = FALSE
		IF flagEndMission = FALSE
			IF flagFailThisJob = FALSE
				IF flagPassThisJob = FALSE
					// Restart main loop
					GOTO Taxiodd_Mission_Loop 
				ELSE
					// Job passed
					GOSUB Taxiodd_Job_Passed
					GOTO Taxiodd_Mission_Loop 
				ENDIF
			ELSE
				// Job failed
				GOSUB Taxiodd_Job_Failed
				GOTO Taxiodd_Mission_Loop 
			ENDIF
		ELSE
			// End Mission
			GOSUB Taxiodd_Mission_Ended
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
// STAGE 1: Pickup a customer

Taxiodd_Stage_Pickup_Customer:

   	// Initialisation
	// --------------

   	IF m_goals = 0
		flagPotentialCustomerSelected				= FALSE
		flagPlayerStoppedCloseToPotentialCustomer	= FALSE
		flagSaidTaxiBail							= FALSE
		flagImmediateRepair							= FALSE
		flagSaidTaxiHail							= FALSE

		timerAllowHailToCancel = 0

		SET_TAXI_LIGHTS carTaxi ON

		IF flagNitroOnDisplay = TRUE
			m_goals = TAXIODD_STAGE1GOAL_DISPLAYING_NITRO_MESSAGE
		ELSE
			GOSUB Taxiodd_Check_If_Taxi_Needs_Repaired
			IF flagTaxiNeedsRepaired = FALSE
				// ...taxi isn't trashed, so choose a new fare
				m_goals = TAXIODD_STAGE1GOAL_FIND_POTENTIAL_CUSTOMER
			ELSE
				// ...taxi is trashed, so allow the timer to countdown while the player gets to a pay n spray
				// NOTE: The timer will stop, as always, once the taxi is repaired until a valid potential fare is found
				PRINT_NOW TX_JUNK g_Taxiodd_timerKM 1
				flagImmediateRepair = TRUE
				FREEZE_ONSCREEN_TIMER FALSE
				m_goals = TAXIODD_STAGE1GOAL_GET_TAXI_REPAIRED
			ENDIF
		ENDIF
	ENDIF


	// Failure Conditions
	// ------------------


	// Special Updates
	// ---------------

	// ...taxi needs repaired?
	IF flagTaxiNeedsRepaired = TRUE
		IF flagPayAndSprayOnDisplay = FALSE
			// ...display Pay and Spray
			GOSUB Taxiodd_Maintain_Pay_And_Spray
		ELSE
			GOSUB Taxiodd_Check_If_Taxi_Needs_Repaired

			IF flagTaxiNeedsRepaired = FALSE
				// ...remove Pay and Spray
				GOSUB Taxiodd_Maintain_Pay_And_Spray

				// Remove the 'car is trashed' text
				CLEAR_THIS_PRINT TX_JUNK

				// Give some extra time to pick up a new fare
				g_Taxiodd_timerKM += 10000

				// If the taxi needed immediate repair, then freeze the timer countdown again
				IF flagImmediateRepair = TRUE
					FREEZE_ONSCREEN_TIMER TRUE
					flagImmediateRepair = FALSE
				ENDIF

				m_goals = 0
			ENDIF
		ENDIF
	ENDIF


	// Subgoals
	// --------

	// Find a potential customer
	IF m_goals = TAXIODD_STAGE1GOAL_FIND_POTENTIAL_CUSTOMER
		GOSUB Select_Potential_Customer

		IF flagPotentialCustomerSelected = TRUE
			IF flagTimerOnDisplay = FALSE
				g_Taxiodd_timerKM = 36000
				DISPLAY_ONSCREEN_TIMER_WITH_STRING g_Taxiodd_timerKM TIMER_DOWN TX_TIME
				flagTimerOnDisplay = TRUE
			ENDIF

			FREEZE_ONSCREEN_TIMER FALSE
			m_goals = TAXIODD_STAGE1GOAL_PICKUP_POTENTIAL_CUSTOMER
		ENDIF
	ENDIF


	// Pickup the Customer
	IF m_goals = TAXIODD_STAGE1GOAL_PICKUP_POTENTIAL_CUSTOMER
		IF NOT IS_CHAR_DEAD charCustomer
			IF IS_CHAR_IN_CAR charCustomer carTaxi
				REMOVE_BLIP blipCustomer
				CLEAR_LOOK_AT charCustomer
				SET_TAXI_LIGHTS carTaxi OFF
				SET_CHAR_SAY_CONTEXT charCustomer CONTEXT_GLOBAL_TAXI_START nIgnore

				GET_CAR_HEALTH carTaxi nMissionStartHealth

				m_goals = 99
			ENDIF
		ENDIF
	ENDIF


	// TAXI REPAIR GOAL
	// ...this goal is reached by trying to pickup a potential customer
	// ...it's job is to make sure that the genuine goals for this stage aren't triggered
	IF m_goals = TAXIODD_STAGE1GOAL_GET_TAXI_REPAIRED
		// ...nothing to do
	ENDIF


	// TAXI NITRO ON DISPLAY
	IF m_goals = TAXIODD_STAGE1GOAL_DISPLAYING_NITRO_MESSAGE
		// ...check for time out
		IF timerNitro < m_mission_timer
			flagNitroOnDisplay = FALSE
			timerNitro = 0
			m_goals = 0
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	GOSUB Taxiodd_Maintain_Potential_Customer


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Taxiodd_Next_Stage
	ENDIF

RETURN


// ****************************************
// STAGE 2: Go To Destination

Taxiodd_Stage_Go_To_Destination:

   	// Initialisation
	// --------------

   	IF m_goals = 0
		GOSUB Taxiodd_Setup_Destination

		// Unfreeze the timer when the ped is in the taxi
		FREEZE_ONSCREEN_TIMER FALSE

		flagArrivedAtDestination = FALSE

		// Tidy-ups ready for the next job after this one complete
		timerPickupText = 0
		flagPotentialCustomerFound = FALSE

		// Help text for taxi
		IF flagDisplayedTipBarHelp = FALSE
			timerTipBarHelp = m_mission_timer + 5000
		ENDIF
		
		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------

	IF IS_CHAR_DEAD charCustomer
		flagFailThisJob = TRUE
	ENDIF

	IF NOT IS_CHAR_IN_CAR charCustomer carTaxi
	AND flagArrivedAtDestination = FALSE
		flagFailThisJob = TRUE
	ENDIF

	// Check if the taxi needs repaired
	GOSUB Taxiodd_Check_If_Taxi_Needs_Repaired
	IF flagTaxiNeedsRepaired = TRUE
		// ...taxi needs repaired
		IF flagPayAndSprayOnDisplay = FALSE
			// ...get your car repaired
			PRINT_NOW TX_REP 6000 1
			// ...Pay and Spray not on display, so display them
			GOSUB Taxiodd_Maintain_Pay_And_Spray
		ENDIF
	ELSE
		// ...taxi doesn't need repaired
		IF flagPayAndSprayOnDisplay = TRUE
			// ...Pay and Spray on display, so remove them
			GOSUB Taxiodd_Maintain_Pay_And_Spray
		ENDIF
	ENDIF


	// Subgoals
	// --------

	// Check for arrival
	IF m_goals = 1
		IF LOCATE_CHAR_IN_CAR_3D charCustomer xposDestination yposDestination zposDestination 4.0 4.0 4.0 TRUE
			IF IS_CHAR_IN_CAR charCustomer carTaxi
			AND IS_CHAR_IN_CAR scplayer carTaxi
				SET_PLAYER_CONTROL player1 OFF

				REMOVE_BLIP blipDestination
				flagArrivedAtDestination = TRUE

				FREEZE_ONSCREEN_TIMER TRUE

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Check for taxi stopped
	IF m_goals = 2
		IF IS_CAR_STOPPED carTaxi
			// Make the customer leave
			IF IS_CHAR_IN_CAR charCustomer carTaxi
				TASK_LEAVE_CAR_IMMEDIATELY charCustomer carTaxi
			ENDIF

			// Update and display the 'job passed' scores and stats, etc
			GOSUB Taxiodd_Update_Job_Passed_Scores

			m_goals++
		ENDIF
	ENDIF


	// Check for ped out of car
	IF m_goals = 3
		IF NOT IS_CHAR_IN_CAR charCustomer carTaxi
			MARK_CHAR_AS_NO_LONGER_NEEDED charCustomer
			nCleanupTest_MissionPeds--

			SET_PLAYER_CONTROL player1 ON

			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	IF flagArrivedAtDestination = FALSE
		GOSUB Taxiodd_Maintain_Bonus
	ENDIF

	// Tip bar help
	IF flagDisplayedTipBarHelp = FALSE
		IF timerTipBarHelp < m_mission_timer
			PRINT_HELP TX_TIP
			flagDisplayedTipBarHelp = TRUE
		ENDIF
	ENDIF


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Taxiodd_Next_Stage
	ENDIF

RETURN




// *************************************************************************************************************
//										MISSION SPECIFIC GOSUBS  
// *************************************************************************************************************


// *************************************************************************************************************
//								           TAXI MAINTENANCE
// *************************************************************************************************************

// ****************************************
// STORE PLAYER TAXI
Store_Player_Taxi:

	IF IS_CHAR_IN_TAXI scplayer
		// ...player is in a taxi
		IF NOT flagPlayerInTaxi = TRUE
			// ...player was not previously in a taxi, so record the details
			STORE_CAR_CHAR_IS_IN scplayer carTaxi
			flagPlayerInTaxi = TRUE

			GOSUB Taxiodd_Create_Helpers
		ENDIF
	ELSE
		// ...player is not in a taxi
		flagPlayerInTaxi = FALSE
	ENDIF

RETURN


// ****************************************
// CREATE THE TAXI'S LEFT/RIGHT/FRONT/BACK HELPERS
Taxiodd_Create_Helpers:

	nTempInt = TAXIODD_HELPER_FRONT
	CREATE_OBJECT CR_AMMOBOX 0.0 0.0 0.0 objectHelpers[nTempInt]
	SET_OBJECT_COLLISION objectHelpers[nTempInt] FALSE
	SET_OBJECT_VISIBLE objectHelpers[nTempInt] FALSE
	ATTACH_OBJECT_TO_CAR objectHelpers[nTempInt] carTaxi 0.0 2.3 0.0 0.0 0.0 0.0
	
	nTempInt = TAXIODD_HELPER_BACK
	CREATE_OBJECT CR_AMMOBOX 0.0 0.0 0.0 objectHelpers[nTempInt]
	SET_OBJECT_COLLISION objectHelpers[nTempInt] FALSE
	SET_OBJECT_VISIBLE objectHelpers[nTempInt] FALSE
	ATTACH_OBJECT_TO_CAR objectHelpers[nTempInt] carTaxi 0.0 -2.6 0.0 0.0 0.0 0.0
	
	nTempInt = TAXIODD_HELPER_LEFT
	CREATE_OBJECT CR_AMMOBOX 0.0 0.0 0.0 objectHelpers[nTempInt]
	SET_OBJECT_COLLISION objectHelpers[nTempInt] FALSE
	SET_OBJECT_VISIBLE objectHelpers[nTempInt] FALSE
	ATTACH_OBJECT_TO_CAR objectHelpers[nTempInt] carTaxi -1.0 -1.0 0.0 0.0 0.0 0.0
	
	nTempInt = TAXIODD_HELPER_RIGHT
	CREATE_OBJECT CR_AMMOBOX 0.0 0.0 0.0 objectHelpers[nTempInt]
	SET_OBJECT_COLLISION objectHelpers[nTempInt] FALSE
	SET_OBJECT_VISIBLE objectHelpers[nTempInt] FALSE
	ATTACH_OBJECT_TO_CAR objectHelpers[nTempInt] carTaxi 1.0 -1.0 0.0 0.0 0.0 0.0

RETURN


// ****************************************
// CHECK IF TAXI NEEDS REPAIRED
Taxiodd_Check_If_Taxi_Needs_Repaired:

	IF IS_CAR_HEALTH_GREATER carTaxi 400
		flagTaxiNeedsRepaired = FALSE
	ELSE
		flagTaxiNeedsRepaired = TRUE
	ENDIF


RETURN


// ****************************************
// Maintain Pay and Spray blips
Taxiodd_Maintain_Pay_And_Spray:

	IF flagPayAndSprayOnDisplay = TRUE
		// ...blips are on display, so they need to be removed
		REPEAT TAXIODD_MAX_PAY_AND_SPRAY_ON_DISPLAY nLoop
			REMOVE_BLIP blipPayAndSpray[nLoop]
		ENDREPEAT

		flagPayAndSprayOnDisplay = FALSE

		RETURN
	ENDIF

	// Blips are not on display, so add them
	// (display the best ones for the player's current location)
	// ... Los Santos
	IF IS_CHAR_IN_ZONE scplayer LA
		nTempInt = 0
		GOSUB Taxiodd_Display_Pay_and_Spray_LS_East
		nTempInt++
		GOSUB Taxiodd_Display_Pay_and_Spray_LS_West
		nTempInt++
		GOSUB Taxiodd_Display_Pay_and_Spray_LS_North
	ENDIF

	// ...Las Venturas
	IF IS_CHAR_IN_ZONE scplayer VE
		nTempInt = 0
		GOSUB Taxiodd_Display_Pay_and_Spray_LV
	ENDIF

	// ...San Fierro
	IF IS_CHAR_IN_ZONE scplayer SF
		nTempInt = 0
		GOSUB Taxiodd_Display_Pay_and_Spray_SF_North
		nTempInt++
		GOSUB Taxiodd_Display_Pay_and_Spray_SF_Middle
	ENDIF

	// ...Red County (North of Los Santos / South of Las Venturas)
	IF IS_CHAR_IN_ZONE scplayer RED
		nTempInt = 0
		GOSUB Taxiodd_Display_Pay_and_Spray_Red_County
		nTempInt++
		GOSUB Taxiodd_Display_Pay_and_Spray_LS_North
	ENDIF

	// ...Flint County (South East of San Fierro / West of Los Santos)
	IF IS_CHAR_IN_ZONE scplayer FLINTC
		nTempInt = 0
		GOSUB Taxiodd_Display_Pay_and_Spray_LS_West
		nTempInt++
		GOSUB Taxiodd_Display_Pay_and_Spray_Red_County
		nTempInt++
		GOSUB Taxiodd_Display_Pay_and_Spray_SF_Middle
	ENDIF

	// ...Whetstone (South of San Fierro)
	IF IS_CHAR_IN_ZONE scplayer WHET
		nTempInt = 0
		GOSUB Taxiodd_Display_Pay_and_Spray_LS_West
		nTempInt++
		GOSUB Taxiodd_Display_Pay_and_Spray_Red_County
		nTempInt++
		GOSUB Taxiodd_Display_Pay_and_Spray_SF_Middle
	ENDIF

	// ...Tierra Robada (North of San Fierro)
	IF IS_CHAR_IN_ZONE scplayer ROBAD
		nTempInt = 0
		GOSUB Taxiodd_Display_Pay_and_Spray_Tierra_Robada
	ENDIF

	// ...Bone County (West of Las Venturas)
	IF IS_CHAR_IN_ZONE scplayer BONE
		nTempInt = 0
		GOSUB Taxiodd_Display_Pay_and_Spray_Tierra_Robada
		nTempInt++
		GOSUB Taxiodd_Display_Pay_and_Spray_LV
	ENDIF

	flagPayAndSprayOnDisplay = TRUE

RETURN


// ****************************************
// Display Pay and Spray blips (coords from Initial.sc)
Taxiodd_Display_Pay_and_Spray_LS_East:
	IF DOES_BLIP_EXIST blipPayAndSpray[nTempInt]
		// ...safety feature added since JohnG had a crash re-using some blip somewhere in the taxi missions
		REMOVE_BLIP blipPayAndSpray[nTempInt]
	ENDIF
	ADD_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY blipPayAndSpray[nTempInt]
RETURN

Taxiodd_Display_Pay_and_Spray_LS_West:
	IF DOES_BLIP_EXIST blipPayAndSpray[nTempInt]
		// ...safety feature added since JohnG had a crash re-using some blip somewhere in the taxi missions
		REMOVE_BLIP blipPayAndSpray[nTempInt]
	ENDIF
	ADD_SPRITE_BLIP_FOR_COORD 488.0 -1734.0 34.4 RADAR_SPRITE_SPRAY blipPayAndSpray[nTempInt]
RETURN

Taxiodd_Display_Pay_and_Spray_Red_County:
	IF DOES_BLIP_EXIST blipPayAndSpray[nTempInt]
		// ...safety feature added since JohnG had a crash re-using some blip somewhere in the taxi missions
		REMOVE_BLIP blipPayAndSpray[nTempInt]
	ENDIF
	ADD_SPRITE_BLIP_FOR_COORD 720.016 -454.625 15.328 RADAR_SPRITE_SPRAY blipPayAndSpray[nTempInt]
RETURN

Taxiodd_Display_Pay_and_Spray_Tierra_Robada:
	IF DOES_BLIP_EXIST blipPayAndSpray[nTempInt]
		// ...safety feature added since JohnG had a crash re-using some blip somewhere in the taxi missions
		REMOVE_BLIP blipPayAndSpray[nTempInt]
	ENDIF
	ADD_SPRITE_BLIP_FOR_COORD -1420.547 2583.945 58.031 RADAR_SPRITE_SPRAY blipPayAndSpray[nTempInt]
RETURN

Taxiodd_Display_Pay_and_Spray_LV:
	IF DOES_BLIP_EXIST blipPayAndSpray[nTempInt]
		// ...safety feature added since JohnG had a crash re-using some blip somewhere in the taxi missions
		REMOVE_BLIP blipPayAndSpray[nTempInt]
	ENDIF
	ADD_SPRITE_BLIP_FOR_COORD 1966.532 2162.65 10.995 RADAR_SPRITE_SPRAY blipPayAndSpray[nTempInt]
RETURN

Taxiodd_Display_Pay_and_Spray_SF_North:
	IF DOES_BLIP_EXIST blipPayAndSpray[nTempInt]
		// ...safety feature added since JohnG had a crash re-using some blip somewhere in the taxi missions
		REMOVE_BLIP blipPayAndSpray[nTempInt]
	ENDIF
	ADD_SPRITE_BLIP_FOR_COORD -2425.46 1020.83 49.39 RADAR_SPRITE_SPRAY blipPayAndSpray[nTempInt]
RETURN

Taxiodd_Display_Pay_and_Spray_LS_North:
	IF DOES_BLIP_EXIST blipPayAndSpray[nTempInt]
		// ...safety feature added since JohnG had a crash re-using some blip somewhere in the taxi missions
		REMOVE_BLIP blipPayAndSpray[nTempInt]
	ENDIF
	ADD_SPRITE_BLIP_FOR_COORD 1021.8, -1018.7, 30.9 RADAR_SPRITE_SPRAY blipPayAndSpray[nTempInt]
RETURN

Taxiodd_Display_Pay_and_Spray_SF_Middle:
	IF DOES_BLIP_EXIST blipPayAndSpray[nTempInt]
		// ...safety feature added since JohnG had a crash re-using some blip somewhere in the taxi missions
		REMOVE_BLIP blipPayAndSpray[nTempInt]
	ENDIF
	ADD_SPRITE_BLIP_FOR_COORD -1908.9, 292.3, 40.0 RADAR_SPRITE_SPRAY blipPayAndSpray[nTempInt]
RETURN

Taxiodd_Display_Pay_and_Spray_Bone_County:
	IF DOES_BLIP_EXIST blipPayAndSpray[nTempInt]
		// ...safety feature added since JohnG had a crash re-using some blip somewhere in the taxi missions
		REMOVE_BLIP blipPayAndSpray[nTempInt]
	ENDIF
	ADD_SPRITE_BLIP_FOR_COORD -103.6, 1112.4, 18.7 RADAR_SPRITE_SPRAY blipPayAndSpray[nTempInt]
RETURN



// *************************************************************************************************************
//								          CUSTOMER MAINTENANCE
// *************************************************************************************************************

// ****************************************
// SELECT POTENTIAL CUSTOMER
Select_Potential_Customer:

	IF timerPickupText < m_mission_timer
		IF flagPotentialCustomerFound = FALSE
			// ...special message if there are no potential customers in the area and there have been none
			//			since this taxi job started
			PRINT_NOW TX_NONE 6000 1
			timerPickupText = m_mission_timer + 5000
			flagDisplayingNoNearbyPedsText = TRUE
		ELSE
			// ...standard message when looking for a fare
			PRINT_NOW TX_PKUP 6000 1
			timerPickupText = m_mission_timer + 5000
		ENDIF
	ENDIF

	// NOTE: FALSE means don't use GangPeds
	GET_RANDOM_CHAR_IN_ZONE SAN_AND TRUE FALSE TRUE charCustomer
	IF charCustomer = TAXIODD_NO_NEARBY_PEDS
		RETURN
	ENDIF

	// Ped selected
	nCleanupTest_MissionPeds++
	
	IF IS_CHAR_DEAD charCustomer
	OR IS_CHAR_IN_ANY_CAR charCustomer
	OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR charCustomer scplayer
		// ...no use
		MARK_CHAR_AS_NO_LONGER_NEEDED charCustomer
		nCleanupTest_MissionPeds--
		RETURN
	ENDIF

/* the new 'model' check should make this check redundant
	// Ensure this isn't the previous customer
	IF NOT IS_CHAR_DEAD charPreviousCustomer
		IF charCustomer = charPreviousCustomer
			// ...try again
			MARK_CHAR_AS_NO_LONGER_NEEDED charCustomer
			nCleanupTest_MissionPeds--
			RETURN
		ENDIF
	ENDIF
*/

	// Ensure this isn't the previous customer model
	GET_CHAR_MODEL charCustomer modelCustomer
	IF modelCustomer = modelPreviousCustomer
		// ...try again
		MARK_CHAR_AS_NO_LONGER_NEEDED charCustomer
		nCleanupTest_MissionPeds--
		RETURN
	ENDIF

	// Ensure the customer is in range
	GET_CHAR_COORDINATES charCustomer xposTemp yposTemp zposTemp
	IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer xposTemp yposTemp zposTemp 88.0 88.0 19.0 FALSE
		// ...either not in range, or close to being out of range, so try again
		MARK_CHAR_AS_NO_LONGER_NEEDED charCustomer
		nCleanupTest_MissionPeds--
		RETURN
	ENDIF

	// Indicate that at least one potential customer has been found since the last taxi mission ended
	// NOTE: This is used to display a different message if there are no potential fares in the area
	flagPotentialCustomerFound = TRUE

	// Clear the 'no peds nearby' text if on display, and replace with 'find a fare'
	IF flagDisplayingNoNearbyPedsText = TRUE
		PRINT_NOW TX_PKUP 6000 1
		timerPickupText = m_mission_timer + 5000
		flagDisplayingNoNearbyPedsText = FALSE
	ENDIF

	// Found a customer, so set it up
	CLEAR_CHAR_TASKS_IMMEDIATELY charCustomer
	SET_CHAR_DECISION_MAKER charCustomer dmEmpty
	TASK_STAND_STILL charCustomer TAXIODD_PERSIST
	flagSaidTaxiHail = FALSE
	GOSUB Taxiodd_Hail

	IF DOES_BLIP_EXIST blipCustomer
		// ...safety feature because of a crash JohnG got. Don't know how it happened though.
		REMOVE_BLIP blipCustomer
	ENDIF
	ADD_BLIP_FOR_CHAR charCustomer blipCustomer
	SET_BLIP_AS_FRIENDLY blipCustomer TRUE

	// Store the initial position for the taxi look at stuff (but fudge it to force an initial 'look at')
	GET_CHAR_COORDINATES scplayer xposTaxiLookAt yposTaxiLookAt zposTaxiLookAt
	xposTaxiLookAt += TAXIODD_TAXI_LOOK_AT_THRESHOLD
	yposTaxiLookAt += TAXIODD_TAXI_LOOK_AT_THRESHOLD

//	charPreviousCustomer = charCustomer
	modelPreviousCustomer = modelCustomer
	flagPotentialCustomerSelected = TRUE

RETURN


// ****************************************
// Maintain potential customer
Taxiodd_Maintain_Potential_Customer:

	IF flagPotentialCustomerSelected = FALSE
		RETURN
	ENDIF

	// Maintain 'pickup' text
	IF timerPickupText < m_mission_timer
		// ...standard message when looking for a fare
		PRINT_NOW TX_PKUP 6000 1
		timerPickupText = m_mission_timer + 5000
	ENDIF

	// NOTE: flagTempFlag used to indicate if another customer is needed
	//		 flagTempFlag2 used if the 'pickup text' timer should be cancelled
	flagTempFlag = FALSE
	flagTempFlag2 = TRUE

	IF IS_CHAR_DEAD charCustomer
	OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR charCustomer scplayer
		// ...need another customer
		flagTempFlag = TRUE
		flagTempFlag2 = FALSE

		// Info: 'pickup another passenger'
		PRINT_NOW TX_OOPS 6000 1
		timerPickupText = m_mission_timer + 5000
	ENDIF

	// Check the taxi condition
	IF IS_CAR_DEAD carTaxi
		// ...need another customer
		flagTempFlag = TRUE
	ENDIF

	// Check for the player still being in the taxi
	IF flagTempFlag = FALSE
		IF NOT IS_CHAR_IN_CAR scplayer carTaxi
			// ...need another customer
			flagTempFlag = TRUE
		ENDIF
	ENDIF

	// Get player and customer coords
	IF flagTempFlag = FALSE
		GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
		GET_CHAR_COORDINATES charCustomer xposTemp2 yposTemp2 zposTemp2
	ENDIF

	IF flagTempFlag = FALSE

		// If the player is too far away from the customer then choose another
		IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer xposTemp2 yposTemp2 zposTemp2 90.0 90.0 20.0 FALSE
			// ...need another customer
			flagTempFlag = TRUE
		ENDIF
	ENDIF

	// Check if another customer is required
	IF flagTempFlag = TRUE

		// Refresh the pickup text if special text is not being displayed
		IF flagTempFlag2 = TRUE
			timerPickupText = 0
		ENDIF

		// GOTO LABEL
		TAXIODD_GET_ANOTHER_CUSTOMER:

		// ...get another customer
		flagPotentialCustomerSelected	= FALSE
		flagSaidTaxiBail				= FALSE
		REMOVE_BLIP blipCustomer

		IF flagTaxiNeedsRepaired = TRUE
			// ...don't display any further customers until the car is repaired
			m_goals = TAXIODD_STAGE1GOAL_GET_TAXI_REPAIRED
		ELSE
			// ...find another potential customer
			m_goals = TAXIODD_STAGE1GOAL_FIND_POTENTIAL_CUSTOMER
		ENDIF

		IF NOT IS_CHAR_DEAD charCustomer
			CLEAR_LOOK_AT charCustomer
			GOSUB Taxiodd_Cancel_Hail
		ENDIF

		// Clear up
		MARK_CHAR_AS_NO_LONGER_NEEDED charCustomer
		nCleanupTest_MissionPeds--

		// Make sure the timer isn't frozen
		FREEZE_ONSCREEN_TIMER FALSE

		RETURN
	ENDIF

	// Maintain the 'goto taxi' task
	IF IS_CHAR_IN_CAR scplayer carTaxi
	AND LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer xposTemp2 yposTemp2 zposTemp2 TAXIODD_ENTER_CAR_DISTANCE TAXIODD_ENTER_CAR_DISTANCE 3.0 FALSE
		// ...player stopped near customer
		IF flagPlayerStoppedCloseToPotentialCustomer = FALSE
			// Check if the taxi needs repaired
			GOSUB Taxiodd_Check_If_Taxi_Needs_Repaired
			IF flagTaxiNeedsRepaired = TRUE
				// ...car trashed
				PRINT_NOW TX_JUNK 5000 1

				GOTO TAXIODD_GET_ANOTHER_CUSTOMER
			ENDIF

			// ...cancel the hail anim prior to getting into the car
			GOSUB Taxiodd_Cancel_Hail
			timerAllowHailToCancel = m_mission_timer + TAXIODD_ALLOW_HAIL_TO_CANCEL_TIME
			flagPlayerStoppedCloseToPotentialCustomer = TRUE

			// Freeze the timer while the ped is getting into the car
			FREEZE_ONSCREEN_TIMER TRUE
		ELSE
			// Ensure the 'allow hail to cancel' time has elapsed
			IF timerAllowHailToCancel > 0
				IF timerAllowHailToCancel < m_mission_timer
					GET_SCRIPT_TASK_STATUS charCustomer TASK_PLAY_ANIM_SECONDARY m_status
					IF m_status = FINISHED_TASK
						// ...get into the cab
						GOSUB Taxiodd_Enter_Taxi_As_Passenger
						timerAllowHailToCancel = 0
					ENDIF
				ENDIF
			ENDIF

			// Ensure the passenger is still trying to enter the cab
			IF timerAllowHailToCancel = 0
				IF NOT IS_CHAR_IN_CAR charCustomer carTaxi
					GET_SCRIPT_TASK_STATUS charCustomer TASK_ENTER_CAR_AS_PASSENGER m_status
					IF m_status = FINISHED_TASK
						GOSUB Taxiodd_Enter_Taxi_As_Passenger
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		RETURN
	ELSE
		// ...the 'get in taxi' conditions have not been met
		IF flagPlayerStoppedCloseToPotentialCustomer = TRUE
			// ...the conditions were previously met
			IF IS_CHAR_IN_CAR scplayer carTaxi
				// ...the player is still in the taxi
				IF NOT LOCATE_CHAR_IN_CAR_3D scplayer xposTemp2 yposTemp2 zposTemp2 TAXIODD_ENTER_CAR_DISTANCE TAXIODD_ENTER_CAR_DISTANCE 3.0 FALSE
					// ...the player is no longer close by, so make the customer stand still
					GOSUB Taxiodd_Hail
					TASK_STAND_STILL charCustomer TAXIODD_PERSIST
					flagPlayerStoppedCloseToPotentialCustomer = FALSE

					// Unfreeze the timer if the taxi is moving
					FREEZE_ONSCREEN_TIMER FALSE
				ENDIF
			ENDIF

			RETURN
		ENDIF

		timerAllowHailToCancel = 0
	ENDIF

	// Maintain Look At
	IF NOT IS_CHAR_IN_ANY_CAR charCustomer
		GET_SCRIPT_TASK_STATUS charCustomer TASK_LOOK_AT_CHAR m_status
		IF NOT m_status = PERFORMING_TASK
		AND NOT m_status = WAITING_TO_START_TASK
			TASK_LOOK_AT_CHAR charCustomer scplayer TAXIODD_PERSIST
		ENDIF
	ENDIF

	// Maintain Achieve Heading
	IF NOT IS_CHAR_IN_ANY_CAR charCustomer
		GET_SCRIPT_TASK_STATUS charCustomer TASK_ACHIEVE_HEADING m_status
		IF NOT m_status = PERFORMING_TASK
		AND NOT m_status = WAITING_TO_START_TASK
			// Get the Required Heading
			// ...only issue the task again if the player has moved a worthwhile distance and is not hailing a taxi
			GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTaxiLookAt yposTaxiLookAt zposTaxiLookAt fTempDistance
			IF fTempDistance > TAXIODD_TAXI_LOOK_AT_THRESHOLD
				// ...player coords
				xloTemp = xposTemp
				yloTemp = yposTemp

				// ...customer coords
				xhiTemp = xposTemp2
				yhiTemp = yposTemp2

				// Calculate Vector
				xposTemp = xloTemp - xhiTemp
				yposTemp = yloTemp - yhiTemp
				GET_HEADING_FROM_VECTOR_2D xposTemp yposTemp headTemp
				TASK_ACHIEVE_HEADING charCustomer headTemp
			ENDIF
		ENDIF
	ENDIF

	// Issue 'hail' speech
	IF flagSaidTaxiHail = FALSE
		IF flagPlayerStoppedCloseToPotentialCustomer = FALSE
			GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
			IF fTempDistance < TAXIODD_HAIL_TAXI_SPEECH_DISTANCE
				SET_CHAR_SAY_CONTEXT charCustomer CONTEXT_GLOBAL_TAXI_HAIL nIgnore			
				flagSaidTaxiHail = TRUE
			ENDIF
		ENDIF
	ENDIF

RETURN



// *************************************************************************************************************
//								           		 HAIL ANIM
// *************************************************************************************************************

// ****************************************
// Hail Taxi
Taxiodd_Hail:

	flagOnRightSide = FALSE
	GOSUB Taxiodd_Customer_Position_LeftRight

	flagAtFront = FALSE
	GOSUB Taxiodd_Customer_Position_FrontBack

	// If flagTempFlag = TRUE then play the left hand anim
	flagTempFlag = FALSE

	IF flagOnRightSide = TRUE
		// Customer is on right side of taxi
		// Is the taxi facing the customer?
		IF flagAtFront = TRUE
			// ...taxi is facing customer, so use right hand anim
			flagTempFlag = FALSE
		ELSE
			// ...taxi is moving away from customer, so use left hand anim
			flagTempFlag = TRUE
		ENDIF
	ELSE
		// Customer is on left hand side of taxi
		// Is the taxi facing the customer?
		IF flagAtFront = TRUE
			// ...taxi is facing customer, so use left hand anim
			flagTempFlag = TRUE
		ELSE
			// ...taxi is moving away from customer, so use right hand anim
			flagTempFlag = FALSE
		ENDIF
	ENDIF

	IF flagTempFlag = TRUE
		// ...play left hand anim
		TASK_PLAY_ANIM_SECONDARY charCustomer Hiker_Pose_L MISC 4.0 FALSE FALSE FALSE TRUE -1
	ELSE
		// ...play right hand anim
		TASK_PLAY_ANIM_SECONDARY charCustomer Hiker_Pose MISC 4.0 FALSE FALSE FALSE TRUE -1
	ENDIF

RETURN


// ****************************************
// Cancel Hail Taxi
Taxiodd_Cancel_Hail:

	TASK_PLAY_ANIM_SECONDARY charCustomer roadcross PED 4.0 FALSE FALSE FALSE FALSE 100

RETURN



// *************************************************************************************************************
//								           		 ENTER TAXI
// *************************************************************************************************************

// ****************************************
// Enter Taxi as Passenger
Taxiodd_Enter_Taxi_As_Passenger:

	flagOnRightSide = FALSE
	GOSUB Taxiodd_Customer_Position_LeftRight

	IF flagOnRightSide = TRUE
		// ...passenger on right hand side, so enter car by rear passenger side door
		TASK_ENTER_CAR_AS_PASSENGER charCustomer carTaxi TAXIODD_PERSIST TAXIODD_REAR_PASSENGER_DOOR
	ELSE
		// ...passenger on left hand side, so enter car by rear driver side door
		TASK_ENTER_CAR_AS_PASSENGER charCustomer carTaxi TAXIODD_PERSIST TAXIODD_REAR_DRIVER_DOOR
	ENDIF

RETURN



// *************************************************************************************************************
//								           		 TAXI POSITIONING
// *************************************************************************************************************

// ****************************************
// Customer position in relation to taxi left/right
Taxiodd_Customer_Position_LeftRight:

	GET_CHAR_COORDINATES charCustomer xposTemp yposTemp zposTemp
	GET_OBJECT_COORDINATES objectHelpers[TAXIODD_HELPER_LEFT] xposTemp2 yposTemp2 zposTemp2
	GET_OBJECT_COORDINATES objectHelpers[TAXIODD_HELPER_RIGHT] xposTemp3 yposTemp3 zposTemp3

	// Find the distance between the customer and both the left and right helper objects
	GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempFloat
	GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp3 yposTemp3 zposTemp3 fTempFloat2

	IF fTempFloat < fTempFloat2
		// ...customer is closer to the left side than the right
		flagOnRightSide = FALSE
	ELSE
		// ...customer is closer to the right side than the left
		flagOnRightSide = TRUE
	ENDIF

RETURN


// ****************************************
// Customer position in relation to taxi front/back
Taxiodd_Customer_Position_FrontBack:

	GET_CHAR_COORDINATES charCustomer xposTemp yposTemp zposTemp
	GET_OBJECT_COORDINATES objectHelpers[TAXIODD_HELPER_FRONT] xposTemp2 yposTemp2 zposTemp2
	GET_OBJECT_COORDINATES objectHelpers[TAXIODD_HELPER_BACK] xposTemp3 yposTemp3 zposTemp3

	// Find the distance between the customer and both the front and back helper objects
	GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempFloat
	GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp3 yposTemp3 zposTemp3 fTempFloat2

	IF fTempFloat < fTempFloat2
		// ...customer is closer to the front than the back
		flagAtFront = TRUE
	ELSE
		// ...customer is closer to the back than the front
		flagAtFront = FALSE
	ENDIF

RETURN




// *************************************************************************************************************
//								           		 MONEY, ETC
// *************************************************************************************************************

// ****************************************
// MAINTAIN BONUS
Taxiodd_Maintain_Bonus:

	IF flagBonusOnDisplay = FALSE
		RETURN
	ENDIF

	// Has the bonus timer ran out?
	IF timerJobBonusTime < m_mission_timer
		// ...yes, so clear the bonus
		CLEAR_ONSCREEN_COUNTER g_Taxiodd_bonusKM
		flagBonusOnDisplay = FALSE
		g_Taxiodd_bonusKM = 0
		RETURN
	ENDIF

	// Update the bonus bar
	// ...damage
	GET_CAR_HEALTH carTaxi nTempInt
	IF nTempInt > nMissionStartHealth
		// ...player must have gone into a pay n spray
		nMissionStartHealth = nTempInt
	ELSE
		IF nTempInt < nMissionStartHealth
			nTempInt2 = nMissionStartHealth - nTempInt
			nMissionStartHealth = nTempInt
			// ...each point of damage costs msec of bonus time
			nTempInt2 *= TAXIODD_DAMAGE_POINT_COST_msec
			timerJobBonusTime -= nTempInt2
		ENDIF
	ENDIF

	// ...time
	nTempInt = timerJobBonusTime - timerJobStartTime
	nTempInt2 = m_mission_timer - timerJobStartTime
	g_Taxiodd_bonusKM = nTempInt - nTempInt2
	g_Taxiodd_bonusKM *= 100
	g_Taxiodd_bonusKM /= nTempInt

	IF g_Taxiodd_bonusKM < 0
		g_Taxiodd_bonusKM = 0
	ENDIF

RETURN


// *************************************************************************************************************
//								           		DESTINATIONS
// *************************************************************************************************************

// ****************************************
// SETUP DESTINATION
Taxiodd_Setup_Destination:

	IF IS_CHAR_DEAD charCustomer
		RETURN
	ENDIF

	// Some destinations are awkward to reach, so they get a bonus TO and FROM time
	nBonusTimeFromBadPlace = nBonusTimeToBadPlace
	nBonusTimeToBadPlace = 0

	nDestinationID = -1
	GOSUB Taxiodd_Choose_Destination

	// Calculate the distance
	GET_CHAR_COORDINATES charCustomer xposTemp yposTemp zposTemp
	xdiffTemp = xposTemp - xposDestination
	ydiffTemp = yposTemp - yposDestination

	xdiffsqTemp = xdiffTemp * xdiffTemp
	ydiffsqTemp = ydiffTemp * ydiffTemp

	fTempFloat = xdiffsqTemp + ydiffsqTemp
	SQRT fTempFloat fTempFloat2
	nDestinationDistance =# fTempFloat2

	// Choose another destination if this one is too close
	IF nDestinationDistance < 200
		GOTO Taxiodd_Setup_Destination
	ENDIF


	// Calculate the time allowed
	nTempInt = nDestinationDistance	

	IF g_Taxiodd_faresKM = 0
		nTempInt = nTempInt * 100
	ENDIF

	IF g_Taxiodd_faresKM = 1
		nTempInt = nTempInt * 95
	ENDIF

	IF g_Taxiodd_faresKM = 2
		nTempInt = nTempInt * 80
	ENDIF

	IF g_Taxiodd_faresKM = 3
		nTempInt = nTempInt * 79
	ENDIF

	IF g_Taxiodd_faresKM = 4
		nTempInt = nTempInt * 78
	ENDIF

	IF g_Taxiodd_faresKM = 5
		nTempInt = nTempInt * 76
	ENDIF

	IF g_Taxiodd_faresKM > 5
	AND	g_Taxiodd_faresKM <= 10
	   nTempInt = nTempInt * 75
	ENDIF

	IF g_Taxiodd_faresKM > 10
	AND	g_Taxiodd_faresKM <= 20
	   nTempInt = nTempInt * 70
	ENDIF

	IF g_Taxiodd_faresKM > 20
	AND	g_Taxiodd_faresKM <= 50
	   nTempInt = nTempInt * 65
	ENDIF

	IF g_Taxiodd_faresKM > 50
	   nTempInt = nTempInt * 60
	ENDIF

	g_Taxiodd_timerKM += nTempInt

	// NOTE: re-use nTempInt (with it's current value) to work out the bonus distance

	// Add on any extra time for particulary awkward places to get To or FROM
	// ...bonus FROM awkward place (half of the 'to' bonus time)
	nTempInt2 = nBonusTimeFromBadPlace * 500
	g_Taxiodd_timerKM += nTempInt2
	nTempInt += nTempInt2

	// ...bonus TO awkward place
	nTempInt2 = nBonusTimeToBadPlace * 1000
	g_Taxiodd_timerKM += nTempInt2
	nTempInt += nTempInt2

	// Bonus Time
	timerJobStartTime = m_mission_timer
	timerJobBonusTime = nTempInt * TAXIODD_TIP_PERCENT
	timerJobBonusTime = timerJobBonusTime / 100

	timerJobBonusTime += m_mission_timer

	// Display bonus timer
	g_Taxiodd_bonusKM = 0
	DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING g_Taxiodd_bonusKM COUNTER_DISPLAY_BAR 3 TX_ADDS
	flagBonusOnDisplay = TRUE

	IF DOES_BLIP_EXIST blipDestination
		// ...safety feature because of a crash JohnG got. Don't know how it happened though.
		REMOVE_BLIP blipDestination
	ENDIF
	ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination

RETURN


// ****************************************
// CHOOSE DESTINATION
Taxiodd_Choose_Destination:

	// Find out which city or county the player is in
	// ... Los Santos
	IF IS_CHAR_IN_ZONE scplayer LA
		GOSUB Taxiodd_Choose_Destination_LosSantos
	ENDIF

	// ...Las Venturas
	IF IS_CHAR_IN_ZONE scplayer VE
		GOSUB Taxiodd_Choose_Destination_LasVenturas
	ENDIF

	// ...San Fierro
	IF IS_CHAR_IN_ZONE scplayer SF
		GOSUB Taxiodd_Choose_Destination_SanFierro
	ENDIF

	// ...Red County (North of Los Santos / South of Las Venturas)
	IF IS_CHAR_IN_ZONE scplayer RED
		GENERATE_RANDOM_INT_IN_RANGE 0 2 nTempInt
		SWITCH nTempInt
			CASE 0
				GOSUB Taxiodd_Choose_Destination_RedCounty
				BREAK
			CASE 1
				GOSUB Taxiodd_Choose_Destination_LosSantos
				BREAK
		ENDSWITCH
	ENDIF

	// ...Flint County (South East of San Fierro / West of Los Santos)
	IF IS_CHAR_IN_ZONE scplayer FLINTC
		GENERATE_RANDOM_INT_IN_RANGE 0 3 nTempInt
		SWITCH nTempInt
			CASE 0
				GOSUB Taxiodd_Choose_Destination_FlintCounty
				BREAK
			CASE 1
				GOSUB Taxiodd_Choose_Destination_LosSantos
				BREAK
			CASE 2
				GOSUB Taxiodd_Choose_Destination_SanFierro
				BREAK
		ENDSWITCH
	ENDIF

	// ...Whetstone (South of San Fierro)
	IF IS_CHAR_IN_ZONE scplayer WHET
		GENERATE_RANDOM_INT_IN_RANGE 0 2 nTempInt
		SWITCH nTempInt
			CASE 0
				GOSUB Taxiodd_Choose_Destination_Whetstone
				BREAK
			CASE 1
				GOSUB Taxiodd_Choose_Destination_SanFierro
				BREAK
		ENDSWITCH
	ENDIF

	// ...Tierra Robada (North of San Fierro)
	IF IS_CHAR_IN_ZONE scplayer ROBAD
		GENERATE_RANDOM_INT_IN_RANGE 0 2 nTempInt
		SWITCH nTempInt
			CASE 0
				GOSUB Taxiodd_Choose_Destination_TierraRobada
				BREAK
			CASE 1
				GOSUB Taxiodd_Choose_Destination_SanFierro
				BREAK
		ENDSWITCH
	ENDIF

	// ...Bone County (West of Las Venturas)
	IF IS_CHAR_IN_ZONE scplayer BONE
		GENERATE_RANDOM_INT_IN_RANGE 0 2 nTempInt
		SWITCH nTempInt
			CASE 0
				GOSUB Taxiodd_Choose_Destination_BoneCounty
				BREAK
			CASE 1
				GOSUB Taxiodd_Choose_Destination_LasVenturas
				BREAK
		ENDSWITCH
	ENDIF

RETURN


// ****************************************
// CHOOSE DESTINATION
// Los Santos
Taxiodd_Choose_Destination_LosSantos:

	IF TEMP_flagDestinationTesting = 1
		// TESTING
		nDestinationID = TEMP_nDestination + 1
	ELSE
		GENERATE_RANDOM_INT_IN_RANGE 1 35 nDestinationID
	ENDIF

	SWITCH nDestinationID
		// Food Place
		CASE 1
			PRINT_NOW ( TX_LS1 ) 5000 1 
			xposDestination = 2468.1455 
			yposDestination = -1736.1840
			zposDestination = 12.3828
			BREAK

		// Stadium
		CASE 2
			PRINT_NOW ( TX_LS2 ) 5000 1 
			xposDestination = 2794.0000
			yposDestination = -1828.0000
			zposDestination = 10.0000
			BREAK

		// Glen Park
		CASE 3
			PRINT_NOW ( TX_LS3 ) 5000 1 
			xposDestination = 1884.1929
			yposDestination = -1257.5214
			zposDestination = 12.3984
			BREAK

		// Sculpture Park
		CASE 4
			PRINT_NOW ( TX_LS4 ) 5000 1 
			xposDestination = 2317.4805
			yposDestination = -1386.6042
			zposDestination = 22.8784
			BREAK

		// Church
		CASE 5
			PRINT_NOW ( TX_LS5 ) 5000 1 
			xposDestination = 2237.7595
			yposDestination = -1304.1653
			zposDestination = 22.8488
			BREAK

		// Motel
		CASE 6
			PRINT_NOW ( TX_LS6 ) 5000 1 
			xposDestination = 2221.0874
			yposDestination = -1137.3323
			zposDestination = 24.6250
			BREAK

		// Pic 'n'Go market
		CASE 7
			PRINT_NOW ( TX_LS7 ) 5000 1 
			xposDestination = 2146.7366
			yposDestination = -1179.7950
			zposDestination = 22.8278
			BREAK

		// Leon Diamonds
		CASE 8
			PRINT_NOW ( TX_LS8 ) 5000 1 
			xposDestination = 2075.5579
			yposDestination = -1202.7931
			zposDestination = 22.7571
			BREAK

		// Ten Green Bottles
		CASE 9
			PRINT_NOW ( TX_LS9 ) 5000 1 
			xposDestination = 2320.0000
			yposDestination = -1655.0000
			zposDestination = 14.0000
			BREAK

		// Mama's Cinema
		CASE 10
			PRINT_NOW ( TX_LS10 ) 5000 1 
			xposDestination = 2455.0000
			yposDestination = -1502.0000
			zposDestination = 24.0000
			BREAK

		// 24 Hour Motel
		CASE 11
			PRINT_NOW ( TX_LS11 ) 5000 1 
			xposDestination = 2181.0000
			yposDestination = -1771.0000
			zposDestination = 13.0000
			BREAK

		// Well Stacked Pizza Co.
		CASE 12
			PRINT_NOW ( TX_LS12 ) 5000 1 
			xposDestination = 2084.7307
			yposDestination = -1800.8602
			zposDestination = 12.3828
			BREAK

		// Reece's Barber Shop
		CASE 13
			PRINT_NOW ( TX_LS13 ) 5000 1 
			xposDestination = 2078.0149
			yposDestination = -1791.5895
			zposDestination = 12.3828
			BREAK

		// Tattoo Shop
		CASE 14
			PRINT_NOW ( TX_LS14 ) 5000 1 
			xposDestination = 2081.2869
			yposDestination = -1779.4996
			zposDestination = 12.3828
			BREAK

		// Truck Park
		CASE 15
			PRINT_NOW ( TX_LS15 ) 5000 1 
			xposDestination = 2418.5422
			yposDestination = -2085.1187
			zposDestination = 12.2928
			BREAK

		// Unity Station
		CASE 16
			PRINT_NOW ( TX_LS16 ) 5000 1 
			xposDestination = 1742.6893
			yposDestination = -1858.6967
			zposDestination = 12.4185
			BREAK

		// Airport
		CASE 17
			PRINT_NOW ( TX_LS17 ) 5000 1 
			xposDestination = 1668.0000
			yposDestination = -2253.0000
			zposDestination = 13.0000
			nBonusTimeToBadPlace = 40			// Long-winded journey to get there, so give more time
			BREAK

		// Cool Circular Building at airport
		CASE 18
			PRINT_NOW ( TX_LS18 ) 5000 1 
			xposDestination = 1432.1981
			yposDestination = -2274.6477
			zposDestination = 12.3906
			nBonusTimeToBadPlace = 30			// Long-winded journey to get there, so give more time
			BREAK

		// Observatory
		CASE 19
			PRINT_NOW ( TX_LS19 ) 5000 1 
			xposDestination = 1256.0000
			yposDestination = -2028.0000
			zposDestination = 60.0000
			nBonusTimeToBadPlace = 30			// Long-winded journey to get there, so give more time
			BREAK

		// Police Station
		CASE 20
			PRINT_NOW ( TX_LS20 ) 5000 1 
			xposDestination = 1532.7894
			yposDestination = -1675.4420 
			zposDestination = 12.3828
			BREAK

		// Pershing Square
		CASE 21
			PRINT_NOW ( TX_LS21 ) 5000 1 
			xposDestination = 1472.3503
			yposDestination = -1729.7062
			zposDestination = 12.3828
			BREAK

		// Skate Park
		CASE 22
			PRINT_NOW ( TX_LS22 ) 5000 1 
			xposDestination = 1855.4191
			yposDestination = -1383.2733
			zposDestination = 12.3984
			BREAK

		// County General Hospital
		CASE 23
			PRINT_NOW ( TX_LS23 ) 5000 1 
			xposDestination = 2025.0000
			yposDestination = -1413.0000
			zposDestination = 17.0000
			BREAK

		// Ammunation
		CASE 24
			PRINT_NOW ( TX_LS24 ) 5000 1 
			xposDestination = 1361.2184
			yposDestination = -1277.9036
			zposDestination = 12.3828
			BREAK

		// All Saints General Hospital
		CASE 25
			PRINT_NOW ( TX_LS25 ) 5000 1 
			xposDestination = 1192.0000
			yposDestination = -1324.0000
			zposDestination = 13.0000
			BREAK

		// Donut Shop
		CASE 26
			PRINT_NOW ( TX_LS26 ) 5000 1 
			xposDestination = 1031.0656
			yposDestination = -1329.7043
			zposDestination = 12.3861
			BREAK

		// Market Station
		CASE 27
			PRINT_NOW ( TX_LS27 ) 5000 1 
			xposDestination = 814.0000
			yposDestination = -1330.0000
			zposDestination = 13.0000
			BREAK

		// Country Club
		CASE 28
			PRINT_NOW ( TX_LS28 ) 5000 1 
			xposDestination = 667.7637
			yposDestination = -1265.6902
			zposDestination = 12.4687
			BREAK

		// Brown Starfish bar and grill
		CASE 29
			PRINT_NOW ( TX_LS29 ) 5000 1 
			xposDestination = 368.1992
			yposDestination = -2042.5366
			zposDestination = 6.6582
			nBonusTimeToBadPlace = 30			// Awkward getting onto the pier if route not known, so give more time
			BREAK

		// Playa del Seville
		CASE 30
			PRINT_NOW ( TX_LS30 ) 5000 1 
			xposDestination = 152.5806
			yposDestination = -1754.8307
			zposDestination = 3.9518
			BREAK

		// Pro Lapse Store
		CASE 31
			PRINT_NOW ( TX_LS31 ) 5000 1 
			xposDestination = 508.2797
			yposDestination = -1358.5979
			zposDestination = 14.9532
			BREAK

		// Burger Shot
		CASE 32
			PRINT_NOW ( TX_LS32 ) 5000 1 
			xposDestination = 1199.8457
			yposDestination = -933.0658
			zposDestination = 41.7332
			BREAK

		// Burger Shot
		CASE 33
			PRINT_NOW ( TX_LS33 ) 5000 1 
			xposDestination = 816.2869
			yposDestination = -1630.7621
			zposDestination = 12.3906
			BREAK

		// Regal Cinema
		CASE 34
			PRINT_NOW ( TX_LS34 ) 5000 1 
			xposDestination = 1311.9703
			yposDestination = -1712.2892
			zposDestination = 12.3906
			BREAK

		DEFAULT
			IF TEMP_flagDestinationTesting = 1
				// TESTING
				nDestinationID = 0
			ENDIF

			WRITE_DEBUG Unknown_Los_Santos_Taxi_Destination
			PRINT_NOW ( TX_LS1 ) 5000 1 
			xposDestination = 2468.1455 
			yposDestination = -1736.1840
			zposDestination = 12.3828
			BREAK
	ENDSWITCH

RETURN


// ****************************************
// CHOOSE DESTINATION
// Las Venturas
Taxiodd_Choose_Destination_LasVenturas:

	IF TEMP_flagDestinationTesting = 1
		// TESTING
		nDestinationID = TEMP_nDestination + 1
	ELSE
		GENERATE_RANDOM_INT_IN_RANGE 1 46 nDestinationID
	ENDIF

	SWITCH nDestinationID
		// Oil Refinery
		CASE 1
			PRINT_NOW ( TX_VE1 ) 5000 1 
			xposDestination = 2491.378 
			yposDestination = 2773.608
			zposDestination = 9.764
			BREAK

		// Shopping Mall
		CASE 2
			PRINT_NOW ( TX_VE2 ) 5000 1 
			xposDestination = 2899.211
			yposDestination = 2435.624
			zposDestination = 9.764
			BREAK

		// Ring Master Casino (circus Circus)
		CASE 3
			PRINT_NOW ( TX_VE3 ) 5000 1 
			xposDestination = 2220.495
			yposDestination = 1838.497
			zposDestination = 9.764
			BREAK

		// The Starfish Casino
		CASE 4
			PRINT_NOW ( TX_VE4 ) 5000 1 
			xposDestination = 2246.202
			yposDestination = 1896.591
			zposDestination = 9.764
			BREAK

		// The Emerald Isle
		CASE 5
			PRINT_NOW ( TX_VE5 ) 5000 1 
			xposDestination = 2127.307
			yposDestination = 2355.785
			zposDestination = 9.764
			BREAK

		// Police Station
		CASE 6
			PRINT_NOW ( TX_VE6 ) 5000 1 
			xposDestination = 2289.5889
			yposDestination = 2415.9392
			zposDestination = 9.7773
			BREAK

		// VRock Casino
		CASE 7
			PRINT_NOW ( TX_VE7 ) 5000 1 
			xposDestination = 2636.223
			yposDestination = 2344.803
			zposDestination = 9.764
			BREAK

		// Blackfield Chapel
		CASE 8
			PRINT_NOW ( TX_VE8 ) 5000 1 
			xposDestination = 1439.000
			yposDestination = 754.000
			zposDestination = 9.764
			BREAK

		// Vegas Stadium
		CASE 9
			PRINT_NOW ( TX_VE9 ) 5000 1 
			xposDestination = 1095.6185
			yposDestination = 1375.2921
			zposDestination = 9.7977
			BREAK

		// Greenglass College
		CASE 10
			PRINT_NOW ( TX_VE10 ) 5000 1 
			xposDestination = 1162.0768
			yposDestination = 1124.4409
			zposDestination = 9.8125
			BREAK

		// Vegas Airport
		CASE 11
			PRINT_NOW ( TX_VE11 ) 5000 1 
			xposDestination = 1710.6244
			yposDestination = 1448.1534
			zposDestination = 9.6643
			BREAK

		// Military Fuel Depot
		CASE 12
			PRINT_NOW ( TX_VE12 ) 5000 1 
			xposDestination = 2490.3840
			yposDestination = 2771.7024
			zposDestination = 9.7964
			BREAK

		// Golf Clubhouse
		CASE 13
			PRINT_NOW ( TX_VE13 ) 5000 1 
			xposDestination = 1465.1866
			yposDestination = 2773.9648
			zposDestination = 9.6875
			BREAK

		// Train Station
		CASE 14
			PRINT_NOW ( TX_VE14 ) 5000 1 
			xposDestination = 1436.1449
			yposDestination = 2670.3848
			zposDestination = 9.6797
			BREAK

		// Baseball Stadium
		CASE 15
			PRINT_NOW ( TX_VE15 ) 5000 1 
			xposDestination = 1486.4716
			yposDestination = 2257.9453
			zposDestination = 9.8128
			BREAK

		// Steakhouse
		CASE 16
			PRINT_NOW ( TX_VE16 ) 5000 1 
			xposDestination = 1694.1876
			yposDestination = 2200.3777
			zposDestination = 9.8203
			BREAK

		// Fire Station
		CASE 17
			PRINT_NOW ( TX_VE17 ) 5000 1 
			xposDestination = 1744.6561
			yposDestination = 2055.8103
			zposDestination = 9.7309
			BREAK

		// Hotel
		CASE 18
			PRINT_NOW ( TX_VE18 ) 5000 1 
			xposDestination = 1840.5543
			yposDestination = 2169.6541
			zposDestination = 9.8010
			BREAK

		// Souvenier Shop
		CASE 19
			PRINT_NOW ( TX_VE19 ) 5000 1 
			xposDestination = 1928.3573
			yposDestination = 2434.3101
			zposDestination = 9.8130
			BREAK

		// Art Gallery
		CASE 20
			PRINT_NOW ( TX_VE20 ) 5000 1 
			xposDestination = 2424.1226
			yposDestination = 2315.7451
			zposDestination = 9.6797
			BREAK

		// Bank
		CASE 21
			PRINT_NOW ( TX_VE21 ) 5000 1 
			xposDestination = 2431.1538
			yposDestination = 2375.0613
			zposDestination = 9.6797
			BREAK

		// Courthouse
		CASE 22
			PRINT_NOW ( TX_VE22 ) 5000 1 
			xposDestination = 2370.2981
			yposDestination = 2467.9248
			zposDestination = 9.6797
			BREAK

		// Estate Agents
		CASE 23
			PRINT_NOW ( TX_VE23 ) 5000 1 
			xposDestination = 2272.0427
			yposDestination = 2286.7554
			zposDestination = 9.6797
			BREAK

		// Freement St. Casino
		CASE 24
			PRINT_NOW ( TX_VE24 ) 5000 1 
			xposDestination = 2324.5129
			yposDestination = 2155.0994
			zposDestination = 9.6797
			BREAK

		// Strip Club
		CASE 25
			PRINT_NOW ( TX_VE25 ) 5000 1 
			xposDestination = 2508.4724
			yposDestination = 2131.2046
			zposDestination = 9.8125
			BREAK

		// Ammunation
		CASE 26
			PRINT_NOW ( TX_VE26 ) 5000 1 
			xposDestination = 2530.9136
			yposDestination = 2083.3552
			zposDestination = 9.6797
			BREAK

		// Drive Thru Pharmacy
		CASE 27
			PRINT_NOW ( TX_VE27 ) 5000 1 
			xposDestination = 2546.5105
			yposDestination = 1968.6073
			zposDestination = 9.8125
			BREAK

		// Chinese Mall
		CASE 28
			PRINT_NOW ( TX_VE28 ) 5000 1 
			xposDestination = 2530.7805
			yposDestination = 1821.2277
			zposDestination = 9.8129
			BREAK

		// Burger Shot
		CASE 29
			PRINT_NOW ( TX_VE29 ) 5000 1 
			xposDestination = 2360.3125
			yposDestination = 2071.9961
			zposDestination = 9.6797
			BREAK

		// Visage Casino
		CASE 30
			PRINT_NOW ( TX_VE30 ) 5000 1 
			xposDestination = 2035.4565
			yposDestination = 1912.2793
			zposDestination = 11.1768
			BREAK

		// Tourist Information
		CASE 31
			PRINT_NOW ( TX_VE31 ) 5000 1 
			xposDestination = 2078.4136
			yposDestination = 2041.1165
			zposDestination = 9.8203
			BREAK

		// Caligulas Casino
		CASE 32
			PRINT_NOW ( TX_VE32 ) 5000 1 
			xposDestination = 2159.0903
			yposDestination = 1678.1124
			zposDestination = 9.6953
			BREAK

		// Treasure Island Casino
		CASE 33
			PRINT_NOW ( TX_VE33 ) 5000 1 
			xposDestination = 2028.4962
			yposDestination = 1711.7435
			zposDestination = 9.6797
			BREAK

		// Royal Casino
		CASE 34
			PRINT_NOW ( TX_VE34 ) 5000 1 
			xposDestination = 2076.4060
			yposDestination = 1519.0378
			zposDestination = 9.6875
			BREAK

		// High Roller Casino
		CASE 35
			PRINT_NOW ( TX_VE35 ) 5000 1 
			xposDestination = 2040.2511
			yposDestination = 1342.8905
			zposDestination = 9.6797
			BREAK

		// Camel's Toe Casino
		CASE 36
			PRINT_NOW ( TX_VE36 ) 5000 1 
			xposDestination = 2230.4697
			yposDestination = 1284.8906
			zposDestination = 9.6797
			BREAK

		// Come-a-lot Casino
		CASE 37
			PRINT_NOW ( TX_VE37 ) 5000 1 
			xposDestination = 2074.5100
			yposDestination = 1162.8325
			zposDestination = 9.6875
			BREAK

		// Pink Swan Casino
		CASE 38
			PRINT_NOW ( TX_VE38 ) 5000 1 
			xposDestination = 2039.2568
			yposDestination = 1174.1720
			zposDestination = 9.6797
			BREAK

		// The Four Dragons Casino
		CASE 39
			PRINT_NOW ( TX_VE39 ) 5000 1 
			xposDestination = 2040.2843
			yposDestination = 1005.6189
			zposDestination = 9.6645
			BREAK

		// Hospital
		CASE 40
			PRINT_NOW ( TX_VE40 ) 5000 1 
			xposDestination = 1608.5217
			yposDestination = 1827.7522
			zposDestination = 9.8281
			BREAK

		// Church
		CASE 41
			PRINT_NOW ( TX_VE41 ) 5000 1 
			xposDestination = 2483.5139
			yposDestination = 922.5383
			zposDestination = 9.8203
			BREAK

		// Strip Club
		CASE 42
			PRINT_NOW ( TX_VE42 ) 5000 1 
			xposDestination = 2544.6680
			yposDestination = 1016.1683
			zposDestination = 9.7593
			BREAK

		// Tiki Hotel
		CASE 43
			PRINT_NOW ( TX_VE43 ) 5000 1 
			xposDestination = 2491.7646
			yposDestination = 1533.6870
			zposDestination = 9.6875
			BREAK

		// Train Station
		CASE 44
			PRINT_NOW ( TX_VE44 ) 5000 1 
			xposDestination = 2828.6487
			yposDestination = 1292.2684
			zposDestination = 9.8281
			BREAK

		// Sex Shop
		CASE 45
			PRINT_NOW ( TX_VE45 ) 5000 1 
			xposDestination = 2524.4929
			yposDestination = 2297.5842
			zposDestination = 9.6797
			BREAK

		DEFAULT
			IF TEMP_flagDestinationTesting = 1
				// TESTING
				nDestinationID = 0
			ENDIF

			WRITE_DEBUG Unknown_Las_Venturas_Taxi_Destination
			PRINT_NOW ( TX_VE1 ) 5000 1 
			xposDestination = 2491.378 
			yposDestination = 2773.608
			zposDestination = 9.764
			BREAK
	ENDSWITCH

RETURN


// ****************************************
// CHOOSE DESTINATION
// San Fierro
Taxiodd_Choose_Destination_SanFierro:

	IF TEMP_flagDestinationTesting = 1
		// TESTING
		nDestinationID = TEMP_nDestination + 1
	ELSE
		GENERATE_RANDOM_INT_IN_RANGE 1 28 nDestinationID
	ENDIF

	SWITCH nDestinationID
		// Skyscraper
		CASE 1
			PRINT_NOW ( TX_SF1 ) 5000 1 
			xposDestination = -1974.876
			yposDestination = 486.737
			zposDestination = 30.371
			BREAK

		// Skyscraper
		CASE 2
			PRINT_NOW ( TX_SF2 ) 5000 1 
			xposDestination = -2044.419
			yposDestination = 500.771
			zposDestination = 35.176
			BREAK

		// Mall
		CASE 3
			PRINT_NOW ( TX_SF3 ) 5000 1 
			xposDestination = -2150.581
			yposDestination = 251.624
			zposDestination = 35.176
			BREAK

		// Train Station
		CASE 4
			PRINT_NOW ( TX_SF4 ) 5000 1 
			xposDestination = -1988.513
			yposDestination = 138.31
			zposDestination = 27.857
			BREAK

		// Stadium
		CASE 5
			PRINT_NOW ( TX_SF5 ) 5000 1 
			xposDestination = -2216.399
			yposDestination = -297.305
			zposDestination = 35.202
			BREAK

		// Country Club
		CASE 6
			PRINT_NOW ( TX_SF6 ) 5000 1 
			xposDestination = -2726.4282
			yposDestination = -310.5865
			zposDestination = 6.0313
			BREAK

		// Modern Cathedral
		CASE 7
			PRINT_NOW ( TX_SF7 ) 5000 1 
			xposDestination = -2704.6492
			yposDestination = -3.5644
			zposDestination = 3.1953
			BREAK

		// Library
		CASE 8
			PRINT_NOW ( TX_SF8 ) 5000 1 
			xposDestination = -2708.997
			yposDestination = 127.501
			zposDestination = 4.584
			BREAK

		// City Hall
		CASE 9
			PRINT_NOW ( TX_SF9 ) 5000 1 
			xposDestination = -2751.905
			yposDestination = 376.781
			zposDestination = 4.584
			BREAK

		// Vank Hoff in the Park Hotel
		CASE 10
			PRINT_NOW ( TX_SF10 ) 5000 1 
			xposDestination = -2415.7495
			yposDestination = 330.9614
			zposDestination = 33.9765
			BREAK

		// Biffin Bridge Hotel
		CASE 11
			PRINT_NOW ( TX_SF11 ) 5000 1 
			xposDestination = -2454.9255
			yposDestination = 138.9312
			zposDestination = 33.9765
			BREAK

		// Central Hashbury
		CASE 12
			PRINT_NOW ( TX_SF12 ) 5000 1 
			xposDestination = -2499.2144
			yposDestination = -16.6149
			zposDestination = 24.6094
			BREAK

		// Missionary Hill Viewpoint
		CASE 13
			PRINT_NOW ( TX_SF13 ) 5000 1 
			xposDestination = -2431.7349
			yposDestination = -198.9205
			zposDestination = 34.1563
			BREAK

		// Skyscraper
		CASE 14
			PRINT_NOW ( TX_SF14 ) 5000 1 
			xposDestination = -1815.151
			yposDestination = 597.754
			zposDestination = 38.528
			BREAK

		// Easter Basin Naval Station
		CASE 15
			PRINT_NOW ( TX_SF15 ) 5000 1 
			xposDestination = -1530.776
			yposDestination = 487.201
			zposDestination = 9.44
			BREAK

		// Easter Basin Docks
		CASE 16
			PRINT_NOW ( TX_SF16 ) 5000 1 
			xposDestination = -1745.08
			yposDestination = 27.759
			zposDestination = 5.452
			BREAK

		// Airport
		CASE 17
			PRINT_NOW ( TX_SF17 ) 5000 1 
			xposDestination = -1414.557
			yposDestination = -301.347
			zposDestination = 14.637
			BREAK

		// Under Gant Bridge
		CASE 18
			PRINT_NOW ( TX_SF18 ) 5000 1 
			xposDestination = -2618.4668
			yposDestination = 1432.7518
			zposDestination = 6.1016
			BREAK

		// Downtown Square
		CASE 19
			PRINT_NOW ( TX_SF19 ) 5000 1 
			xposDestination = -1904.7648
			yposDestination = 882.7293
			zposDestination = 34.0156
			BREAK

		// Twisty Hill
		CASE 20
			PRINT_NOW ( TX_SF20 ) 5000 1 
			xposDestination = -2134.7288
			yposDestination = 919.0828
			zposDestination = 78.8438
			BREAK

		// Burger Shot
		CASE 21
			PRINT_NOW ( TX_SF21 ) 5000 1 
			xposDestination = -2361.8362
			yposDestination = 993.0967
			zposDestination = 49.6875
			BREAK

		// Tuff Nut Donuts
		CASE 22
			PRINT_NOW ( TX_SF22 ) 5000 1 
			xposDestination = -2753.6897
			yposDestination = 779.9389
			zposDestination = 53.2422
			BREAK

		// Pier 69
		CASE 23
			PRINT_NOW ( TX_SF23 ) 5000 1 
			xposDestination = -1714.7173
			yposDestination = 1332.5645
			zposDestination = 6.0391
			BREAK

		// Chinatown Gates
		CASE 24
			PRINT_NOW ( TX_SF24 ) 5000 1 
			xposDestination = -2251.0332
			yposDestination = 717.7971
			zposDestination = 48.2969
			BREAK

		// Well Stacked Pizza Co
		CASE 25
			PRINT_NOW ( TX_SF25 ) 5000 1 
			xposDestination = -1807.8131
			yposDestination = 936.0701
			zposDestination = 23.7500
			BREAK

		// Church
		CASE 26
			PRINT_NOW ( TX_SF26 ) 5000 1 
			xposDestination = -1969.2053
			yposDestination = 1115.3121
			zposDestination = 52.6942
			BREAK

		// Bridge Walkway
		CASE 27
			PRINT_NOW ( TX_SF27 ) 5000 1 
			xposDestination = -2540.5933
			yposDestination = 1222.1876
			zposDestination = 36.4283
			BREAK

		DEFAULT
			IF TEMP_flagDestinationTesting = 1
				// TESTING
				nDestinationID = 0
			ENDIF

			WRITE_DEBUG Unknown_San_Fierro_Taxi_Destination
			PRINT_NOW ( TX_SF1 ) 5000 1 
			xposDestination = -1974.876
			yposDestination = 486.737
			zposDestination = 30.371
			BREAK
	ENDSWITCH

RETURN


// ****************************************
// CHOOSE DESTINATION
// Red County
Taxiodd_Choose_Destination_RedCounty:

	WRITE_DEBUG NO_RED_COUNTY_DESTINATIONS_YET

	IF TEMP_flagDestinationTesting = 1
		// TESTING
		nDestinationID = 0
	ELSE
		WRITE_DEBUG CHOOSING_LOS_SANTOS
		GOSUB Taxiodd_Choose_Destination_LosSantos
	ENDIF

RETURN


// ****************************************
// CHOOSE DESTINATION
// FlintCounty
Taxiodd_Choose_Destination_FlintCounty:

	WRITE_DEBUG NO_FLINT_COUNTY_DESTINATIONS_YET

	IF TEMP_flagDestinationTesting = 1
		// TESTING
		nDestinationID = 0
	ELSE
		WRITE_DEBUG CHOOSING_SAN_FIERRO
		GOSUB Taxiodd_Choose_Destination_SanFierro
	ENDIF

RETURN


// ****************************************
// CHOOSE DESTINATION
// Whetstone
Taxiodd_Choose_Destination_Whetstone:

	WRITE_DEBUG NO_WHETSTONE_DESTINATIONS_YET

	IF TEMP_flagDestinationTesting = 1
		// TESTING
		nDestinationID = 0
	ELSE
		WRITE_DEBUG CHOOSING_SAN_FIERRO
		GOSUB Taxiodd_Choose_Destination_SanFierro
	ENDIF

RETURN


// ****************************************
// CHOOSE DESTINATION
// Tierra Robada
Taxiodd_Choose_Destination_TierraRobada:

	IF TEMP_flagDestinationTesting = 1
		// TESTING
		nDestinationID = TEMP_nDestination + 1
	ELSE
		GENERATE_RANDOM_INT_IN_RANGE 1 3 nDestinationID
	ENDIF

	SWITCH nDestinationID
		// Art-Deco Cafe
		CASE 1
			PRINT_NOW ( TX_TR1 ) 5000 1 
			xposDestination = -1934.0780
			yposDestination = 2382.7617
			zposDestination = 48.5000
			BREAK

		// Grill Restaurant
		CASE 2
			PRINT_NOW ( TX_TR2 ) 5000 1 
			xposDestination = -846.9434
			yposDestination = 1528.1541
			zposDestination = 20.5510
			BREAK

		DEFAULT
			IF TEMP_flagDestinationTesting = 1
				// TESTING
				nDestinationID = 0
			ENDIF

			WRITE_DEBUG Unknown_Tierra_Robada_Taxi_Destination
			PRINT_NOW ( TX_TR1 ) 5000 1 
			xposDestination = -1934.0780
			yposDestination = 2382.7617
			zposDestination = 48.5000
			BREAK
	ENDSWITCH

RETURN


// ****************************************
// CHOOSE DESTINATION
// Bone County
Taxiodd_Choose_Destination_BoneCounty:

	IF TEMP_flagDestinationTesting = 1
		// TESTING
		nDestinationID = TEMP_nDestination + 1
	ELSE
		GENERATE_RANDOM_INT_IN_RANGE 1 6 nDestinationID
	ENDIF

	SWITCH nDestinationID
		// Bunny Ranch
		CASE 1
			PRINT_NOW ( TX_BC1 ) 5000 1 
			xposDestination = 689.6460
			yposDestination = 1943.2524
			zposDestination = 4.5390
			BREAK

		// Dam
		CASE 2
			PRINT_NOW ( TX_BC2 ) 5000 1 
			xposDestination = -904.3732
			yposDestination = 2007.6432
			zposDestination = 59.9141
			BREAK

		// Desert Airfield
		CASE 3
			PRINT_NOW ( TX_BC3 ) 5000 1 
			xposDestination = 392.0225
			yposDestination = 2547.8064
			zposDestination = 15.5568
			BREAK

		// Shooting Range
		CASE 4
			PRINT_NOW ( TX_BC4 ) 5000 1 
			xposDestination = 832.8276
			yposDestination = 1705.4286
			zposDestination = 4.8587
			BREAK

		// Probe Inn
		CASE 5
			PRINT_NOW ( TX_BC5 ) 5000 1 
			xposDestination = -84.9834
			yposDestination = 1358.6156
			zposDestination = 9.3644
			BREAK

		DEFAULT
			IF TEMP_flagDestinationTesting = 1
				nDestinationID = 0
			ENDIF

			WRITE_DEBUG Unknown_Bone_County_Taxi_Destination
			PRINT_NOW ( TX_BC1 ) 5000 1 
			xposDestination = 689.6460
			yposDestination = 1943.2524
			zposDestination = 4.5390
			BREAK
	ENDSWITCH

RETURN




// *************************************************************************************************************
// 												HOUSEKEEPING GOSUBS   
// *************************************************************************************************************

// ****************************************
// DEBUG TOOLS
Taxiodd_Debug_Tools:

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
		Taxiodd_view_debug[0] = m_stage
		Taxiodd_view_debug[1] = m_goals
		Taxiodd_view_debug[2] = m_mission_timer
		Taxiodd_view_debug[3] = taxi_passed
		Taxiodd_view_debug[4] = nCleanupTest_MissionPeds
		Taxiodd_view_debug[5] = 0
		Taxiodd_view_debug[6] = 0
		Taxiodd_view_debug[7] = 0
		// First two lines are so that the important data displayed is not hidden by other text
		VIEW_INTEGER_VARIABLE Taxiodd_view_debug[0] m_stage
		VIEW_INTEGER_VARIABLE Taxiodd_view_debug[1] m_goals
		VIEW_INTEGER_VARIABLE Taxiodd_view_debug[4] Cleanup_Testing
		VIEW_INTEGER_VARIABLE Taxiodd_view_debug[2] m_mission_timer
		VIEW_INTEGER_VARIABLE Taxiodd_view_debug[3] Total_taxi_passed
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
Taxiodd_Debug_Shortcuts:

	// Jumps
	// -----

	IF NOT IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_J
		RETURN
	ENDIF

	IF m_stage = 2
		CLEAR_AREA xposDestination yposDestination zposDestination 10.0 FALSE
		SET_CHAR_COORDINATES scplayer xposDestination yposDestination zposDestination
		m_goals = 1
	ENDIF

RETURN



// ****************************************
// DESTINATION TESTING (NOTE: Endless loop)
Taxiodd_Destination_Testing:

LVAR_INT	TEMP_nCityOrCounty
LVAR_INT	TEMP_nDestination

TEMP_nCityOrCounty			= 0
TEMP_nDestination			= 0


	// GOTO LABEL
	TAXIODD_TEMP_RECHOOSE_LOCATION:

	nDestinationID = 0

	SWITCH TEMP_nCityOrCounty
		CASE 0
			GOSUB Taxiodd_Choose_Destination_LosSantos
			IF NOT nDestinationID = 0
				WRITE_DEBUG Los_Santos
			ENDIF
			BREAK
		CASE 1
			GOSUB Taxiodd_Choose_Destination_LasVenturas
			IF NOT nDestinationID = 0
				WRITE_DEBUG Las_Venturas
			ENDIF
			BREAK
		CASE 2
			GOSUB Taxiodd_Choose_Destination_SanFierro
			IF NOT nDestinationID = 0
				WRITE_DEBUG San_Fierro
			ENDIF
			BREAK
		CASE 3
			GOSUB Taxiodd_Choose_Destination_RedCounty
			IF NOT nDestinationID = 0
				WRITE_DEBUG Red_County
			ENDIF
			BREAK
		CASE 4
			GOSUB Taxiodd_Choose_Destination_FlintCounty
			IF NOT nDestinationID = 0
				WRITE_DEBUG Flint_County
			ENDIF
			BREAK
		CASE 5
			GOSUB Taxiodd_Choose_Destination_Whetstone
			IF NOT nDestinationID = 0
				WRITE_DEBUG Whetstone
			ENDIF
			BREAK
		CASE 6
			GOSUB Taxiodd_Choose_Destination_TierraRobada
			IF NOT nDestinationID = 0
				WRITE_DEBUG Tierra_Robada
			ENDIF
			BREAK
		CASE 7
			GOSUB Taxiodd_Choose_Destination_BoneCounty
			IF NOT nDestinationID = 0
				WRITE_DEBUG Bone_County
			ENDIF
			BREAK
		DEFAULT
			TEMP_nCityOrCounty = -1
	ENDSWITCH

	IF nDestinationID = 0
		WAIT 0
		TEMP_nCityOrCounty++
		TEMP_nDestination = 0
		GOTO TAXIODD_TEMP_RECHOOSE_LOCATION
	ENDIF

	// Jump to location
	WRITE_DEBUG_WITH_INT J_A_D___ID nDestinationID
	WHILE NOT IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_J
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_A
			TEMP_nCityOrCounty++
			GOTO TAXIODD_TEMP_RECHOOSE_LOCATION
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D
			TEMP_nDestination++
			GOTO TAXIODD_TEMP_RECHOOSE_LOCATION
		ENDIF

		WAIT 0
	ENDWHILE

	IF NOT IS_CHAR_DEAD scplayer
		ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination
		SET_CHAR_COORDINATES scplayer xposDestination yposDestination zposDestination
	ENDIF

	TEMP_nDestination++

	WRITE_DEBUG PRESS_N_FOR_NEXT_FARE
	WHILE NOT IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_N
		WAIT 0
	ENDWHILE

	REMOVE_BLIP blipDestination
	WAIT 0
	GOTO TAXIODD_TEMP_RECHOOSE_LOCATION
RETURN



// ****************************************
// FRAME COUNTER (Useful if processor scheduling is needed)
Taxiodd_Frame_Counter:

	m_frame_num++
	IF m_frame_num > 9
		m_frame_num = 0
	ENDIF

RETURN



// ****************************************
// ADDITIONAL TIMERS
Taxiodd_Additional_Timers:

	GET_GAME_TIMER m_this_frame_time
	m_time_diff = m_this_frame_time - m_last_frame_time 
	m_last_frame_time = m_this_frame_time

	m_mission_timer += m_time_diff

RETURN



// =================================================================
// GOSUB: Next_Stage (sets flags and timers for next mission stage)
// =================================================================
Taxiodd_Next_Stage:

   	m_stage++
   	m_goals	= 0
   	TIMERA	= 0
   	TIMERB	= 0

RETURN					



// =================================================================
// GOSUB: Next_Job (sets flags and timers for a new job)
// =================================================================
Taxiodd_Next_Job:

   	m_stage	= 1
   	m_goals	= 0
   	TIMERA	= 0
   	TIMERB	= 0

RETURN					





// *************************************************************************************************************
//										INTRO CUTSCENE GOSUB
// *************************************************************************************************************

Taxiodd_Intro_Cutscene:

/*
	SET_PLAYER_CONTROL player1 OFF

	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE


	LOAD_CUTSCENE SCRASH2
	WHILE NOT HAS_CUTSCENE_LOADED
		WAIT 0
	ENDWHILE
	 

	START_CUTSCENE


	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_IN


	WHILE NOT HAS_CUTSCENE_FINISHED
		WAIT 0
	ENDWHILE
 

	CLEAR_CUTSCENE


	SET_FADING_COLOUR 0 0 0
	DO_FADE 0 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE


	RESTORE_CAMERA_JUMPCUT
	SWITCH_WIDESCREEN OFF
*/

RETURN




// *************************************************************************************************************
// 												INITIALISATION GOSUBS   
// *************************************************************************************************************

Taxiodd_Initialisation:

	WHILE NOT IS_PLAYER_PLAYING player1
		WAIT 0
	ENDWHILE


	GOSUB Taxiodd_Load_All_Models
	GOSUB Taxiodd_Load_All_Anims


	// Display help? (first time only)
	IF done_taxi_help = FALSE
		PRINT_HELP TX_H1
		done_taxi_help = TRUE
	ENDIF


	// Get the taxi the player is in
	GOSUB Store_Player_Taxi

	// Variables
	nEarnings				= 0
	nBonusTimeFromBadPlace	= 0
	nBonusTimeToBadPlace	= 0


	// Fares Counter
	g_Taxiodd_faresKM = 0
	DISPLAY_ONSCREEN_COUNTER_WITH_STRING g_Taxiodd_faresKM COUNTER_DISPLAY_NUMBER TX_TOTL


	// Timer
	g_Taxiodd_timerKM = 0


	// In a Row Bonus
	nNextInARowJobs = TAXIODD_IN_A_ROW_JOBS_INCREASE
	nNextInARowBonus = TAXIODD_IN_A_ROW_BONUS_INCREASE


	// Model values
	modelCustomer = TAXIODD_NO_CHAR_MODEL
	modelPreviousCustomer = TAXIODD_NO_CHAR_MODEL

	GOSUB Taxiodd_Next_Stage

RETURN


// ***********************************
// LOAD ALL MODELS

Taxiodd_Load_All_Models:

	// CARS
//	REQUEST_MODEL VOODOO	// Car with hood popped, and gangBackup car
//	REQUEST_MODEL FIRETRUK	// Fire Engine

	// PEDS

	// GUNS

	// OBJECTS
	REQUEST_MODEL CR_AMMOBOX

	LOAD_ALL_MODELS_NOW


	// Are Cars Loaded?

	// Are Peds Loaded?

	// Are weapons loaded?

	// Are the Objects loaded?
	WHILE NOT HAS_MODEL_LOADED	CR_AMMOBOX
		WAIT 0
	ENDWHILE

RETURN


// ***********************************
// LOAD ALL ANIMS

Taxiodd_Load_All_Anims:

	REQUEST_ANIMATION MISC				// for hailing a taxi


	// Are anims loaded?
	WHILE NOT HAS_ANIMATION_LOADED	MISC
		WAIT 0
	ENDWHILE

RETURN





// *************************************************************************************************************
//										END OF LEVEL GOSUBS - fail, pass, cleanup  
// *************************************************************************************************************

// SCORES, ETC when the Job Passed
Taxiodd_Update_Job_Passed_Scores:

	// This has been taken out of the 'job passed' routine so that it can display the 'job passed' text as
	//	the passenger is getting out of the car. Before, it waited until the passenger was out of the car.

	// ...display normal 'pass' messages
	// Calculate the money
	// NOTE: To lower the money, increase the value
//	IF g_Taxiodd_bonusKM > 0
	IF flagBonusOnDisplay = TRUE
		// Job complete within bonus time
		nTempInt = nDestinationDistance / 16//20
	ELSE
		// Job complete - but no bonus
		nTempInt = nDestinationDistance / 40//60
	ENDIF

	// NOTE: nTempInt2 used to recored the total money made in this job so that the stat can be updated
	nTempInt2 = nTempInt

	// Score
	ADD_SCORE player1 nTempInt
	PRINT_WITH_NUMBER_BIG ( TX_PAY ) nTempInt 6000 6
	ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
	nEarnings += nTempInt

	// Update Global and Local counters
	g_Taxiodd_faresKM++
	taxi_passed++

	// In a Row Bonus
	flagReceivingInARowBonus = FALSE
	IF g_Taxiodd_faresKM = nNextInARowJobs
		// Add and Display bonus
		PRINT_WITH_2_NUMBERS_BIG ( TX_SEQ ) g_Taxiodd_faresKM nNextInARowBonus 5000 5
		ADD_SCORE player1 nNextInARowBonus
		nEarnings = nEarnings + nNextInARowBonus
		flagReceivingInARowBonus = TRUE

		// Increase total for this job
		nTempInt2 += nNextInARowBonus

		// Update for next bonus
		nNextInARowJobs += TAXIODD_IN_A_ROW_JOBS_INCREASE
		nNextInARowBonus += TAXIODD_IN_A_ROW_BONUS_INCREASE
	ENDIF

	// Pass the mission when 50 jobs complete
	IF done_taxiodd_progress = FALSE
	AND taxi_passed > 49
		
		REGISTER_ODDJOB_MISSION_PASSED
		PLAYER_MADE_PROGRESS 1
		PLAY_MISSION_PASSED_TUNE 2

		PRINT_BIG TX_DONE 5000 5

		// All taxi's have nitro
		PRINT_NOW TX_NTRO 7000 1
		flagNitroOnDisplay = TRUE
		timerNitro = m_mission_timer + 7000

		// Activate nitro
		SET_ALL_TAXIS_HAVE_NITRO TRUE

		done_taxiodd_progress = TRUE
	ELSE
		// ...display normal 'pass' messages
		IF g_Taxiodd_bonusKM > 0
			// Job complete within bonus time
			IF flagReceivingInARowBonus = TRUE
				PRINT_BIG TX_FAST 5000 4
			ELSE
				PRINT_BIG TX_FAST 5000 5
			ENDIF
			SET_CHAR_SAY_CONTEXT charCustomer CONTEXT_GLOBAL_TAXI_TIP nIgnore
		ELSE
			// Job complete - but no bonus
			IF flagReceivingInARowBonus = TRUE
				PRINT_BIG TX_WIN 5000 4
			ELSE
				PRINT_BIG TX_WIN 5000 5
			ENDIF
			SET_CHAR_SAY_CONTEXT charCustomer CONTEXT_GLOBAL_TAXI_SUCCESS nIgnore
		ENDIF
	ENDIF

	// Taxi stats
	INCREMENT_INT_STAT_NO_MESSAGE PASSENGERS_DELIVERED_IN_TAXI 1
	INCREMENT_INT_STAT_NO_MESSAGE TAXI_MONEY_MADE nTempInt2

RETURN


// PASS (individual job)
Taxiodd_Job_Passed:

	// Give some extra time to pick up a new passenger
	g_Taxiodd_timerKM += 10000

	// Clear bonus counter
	CLEAR_ONSCREEN_COUNTER	g_Taxiodd_bonusKM
	flagBonusOnDisplay = FALSE

	// Prepare for new job
	flagPassThisJob = FALSE
	GOSUB Taxiodd_Next_Job

RETURN


// FAIL (individual job, but allows another job to start)
Taxiodd_Job_Failed:

	IF NOT IS_CHAR_DEAD charCustomer
		MARK_CHAR_AS_NO_LONGER_NEEDED charCustomer
		nCleanupTest_MissionPeds--
	ENDIF

	REMOVE_BLIP blipCustomer
	REMOVE_BLIP blipDestination

	// Clear bonus counter
	CLEAR_ONSCREEN_COUNTER	g_Taxiodd_bonusKM
	flagBonusOnDisplay = FALSE

	flagFailThisJob = FALSE
	GOSUB Taxiodd_Next_Job

RETURN


// MISSION ENDED (Quits out of the taxi missions and cleans up the script)
Taxiodd_Mission_Ended:

	PRINT_BIG TX_END 5000 5
	PRINT_WITH_NUMBER_BIG TX_PAY nEarnings 6000 6


	// Display reason for mission end
	CLEAR_SMALL_PRINTS

	SWITCH nEndMissionReason
		CASE TAXIODD_END_NOT_IN_TAXI
			PRINT_NOW TX_F1 5000 1
			BREAK
		CASE TAXIODD_END_OUT_OF_TIME
			PRINT_NOW TX_F2 5000 1
			BREAK
		CASE TAXIODD_END_TAXI_FUCKED
			PRINT_NOW TX_F3 5000 1
			BREAK
	ENDSWITCH

	CLEAR_HELP

RETURN


// CLEANUP
Taxiodd_Mission_Cleanup:

	// Get rid of the Player's mission specific weapons
	// ------------------------------------------------

//	IF IS_PLAYER_PLAYING player1
//		REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_MOLOTOV
//	ENDIF


	// Entity Task Removal, etc.
	// -------------------------
	IF NOT IS_CHAR_DEAD charCustomer
		CLEAR_LOOK_AT charCustomer

		IF IS_CAR_DEAD carTaxi
			GOSUB Taxiodd_Cancel_Hail
		ELSE
			IF NOT IS_CHAR_IN_CAR charCustomer carTaxi
				GOSUB Taxiodd_Cancel_Hail
			ENDIF
		ENDIF
	ENDIF


	// Entity Clearup
	// --------------
	MARK_CAR_AS_NO_LONGER_NEEDED	carTaxi
	MARK_CHAR_AS_NO_LONGER_NEEDED	charCustomer

	REPEAT TAXIODD_MAX_HELPERS nLoop
		MARK_OBJECT_AS_NO_LONGER_NEEDED	objectHelpers[nLoop]
	ENDREPEAT

	
	// Blip Clearup
	// ------------
	REMOVE_BLIP blipCustomer
	REMOVE_BLIP blipDestination

	REPEAT TAXIODD_MAX_PAY_AND_SPRAY_ON_DISPLAY nLoop
		REMOVE_BLIP blipPayAndSpray[nLoop]
	ENDREPEAT


	// Counters and Timers Clearup
	// ---------------------------
	CLEAR_ONSCREEN_COUNTER	g_Taxiodd_faresKM
	CLEAR_ONSCREEN_TIMER	g_Taxiodd_timerKM
	CLEAR_ONSCREEN_COUNTER	g_Taxiodd_bonusKM


	// Animation Clearup
	// -----------------
	REMOVE_ANIMATION MISC


	// Models Clearup
	// --------------
	MARK_MODEL_AS_NO_LONGER_NEEDED	CR_AMMOBOX


	// === RESTORE ENVIRONMENT SETTINGS ===
	// ------------------------------------

	SET_PED_DENSITY_MULTIPLIER	1.0
	SET_CAR_DENSITY_MULTIPLIER	1.0
	SET_WANTED_MULTIPLIER		1.0
	SWITCH_EMERGENCY_SERVICES	ON
	SWITCH_RANDOM_TRAINS		ON


	// Restore switched off road
	// -------------------------

//	SWITCH_ROADS_BACK_TO_ORIGINAL xlo ylo zlo xhi yhi zhi


	// Restore the mod garage state back to its original setting
	// ---------------------------------------------------------

	stop_gargae_for_neil = nHoldModGarageState


	// Make sure the mobile phone doesn't ring immediately after a mission
	// -------------------------------------------------------------------

	GET_GAME_TIMER timer_mobile_start


	// Housekeeping
	// ------------

	flag_player_on_oddjob			= FALSE
	flag_player_on_mission			= FALSE
	flag_taxiodd_mission_launched	= FALSE
	MISSION_HAS_FINISHED

RETURN
	 

}