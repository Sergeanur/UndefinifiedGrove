MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
//
//			   MISSION NAME : Quarry
//				     AUTHOR : Keith
//		        DESCRIPTION : Quarry oddjobs (an amalgamation of Dozer and Dumper)
//
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

{
// *************************************************************************************************************
//								GENERAL INITIALISATION - Every level requires this
// *************************************************************************************************************


SCRIPT_NAME Quarry

// Global Variables for Main
VAR_INT	g_nQuarryMissionsPassed

// Global Variables
VAR_INT	g_Quarry_timerKM
VAR_INT	g_Quarry_damageKM


// Global Timing Array
CONST_INT QUARRY_LEVELS	7	// Should be same as 'Highest Mission'
VAR_INT	g_Quarry_recordsKM[QUARRY_LEVELS]


// Mission Start stuff
GOSUB Quarry_Mission_Start

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB Quarry_Mission_Failed
ENDIF

GOSUB Quarry_Mission_Cleanup

MISSION_END


// Global Setup
Quarry_Mission_Start:


IF done_quarry_progress = FALSE
	REGISTER_MISSION_GIVEN
ENDIF

flag_player_on_mission = TRUE
flag_player_on_oddjob = TRUE


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
VAR_INT 	Quarry_view_debug[8]	// GLOBAL (for output)
// useful int variables
LVAR_INT	nLoop
LVAR_INT	nLoop2
LVAR_INT	nTempInt
LVAR_INT	nTempInt2
LVAR_INT	nTempInt3
LVAR_INT	nTempCar
LVAR_INT	nTempPed
LVAR_INT	nTempObject
LVAR_INT	nTempAmmo
LVAR_INT	nTempTimer
LVAR_INT	nSeqTask
// useful float variables
LVAR_FLOAT	xposTemp	yposTemp	zposTemp
LVAR_FLOAT	xposTemp2	yposTemp2	zposTemp2
LVAR_FLOAT	xposTemp3	yposTemp3	zposTemp3
LVAR_FLOAT	headTemp
LVAR_FLOAT	xloTemp		yloTemp		zloTemp
LVAR_FLOAT	xhiTemp		yhiTemp		zhiTemp
LVAR_FLOAT	fTempFloat
LVAR_FLOAT	fTempFloat2
LVAR_FLOAT	fTempFloat3
LVAR_FLOAT	fTempDistance
LVAR_FLOAT	fTempDistance2
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
//m_stage = QUARRY_STAGE_BURIAL_PUSH_BODY

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
CONST_INT	QUARRY_FAILED_UNKNOWN							0
CONST_INT	QUARRY_FAILED_DOZER_DESTROYED					1
CONST_INT	QUARRY_FAILED_DUMPER_DESTROYED					2
CONST_INT	QUARRY_FAILED_DROPPED_LOAD						3
CONST_INT	QUARRY_FAILED_COPS_ARRIVED						4
CONST_INT	QUARRY_FAILED_DELIVERY_ARRIVED					5
CONST_INT	QUARRY_FAILED_BOMB_EXPLODED						6
CONST_INT	QUARRY_FAILED_TOO_CLOSE_TO_EXPLOSION			7
CONST_INT	QUARRY_FAILED_BODY_DUMPED_IN_WRONG_PLACE		8
CONST_INT	QUARRY_FAILED_TOO_MUCH_DAMAGE					9
CONST_INT	QUARRY_FAILED_MISSED_DEADLINE					10
CONST_INT	QUARRY_FAILED_EXPLOSIVES_ON_TRACK				11
CONST_INT	QUARRY_FAILED_TRAIN_DESTROYED					12
CONST_INT	QUARRY_FAILED_TOO_FAR_AWAY						13
CONST_INT	QUARRY_FAILED_REACHED_SAFETY					14
// ...mission IDs
CONST_INT	QUARRY_MISSION_NONE								0
CONST_INT	QUARRY_MISSION_ROCKFALL							1
CONST_INT	QUARRY_MISSION_SABOTAGE							2
CONST_INT	QUARRY_MISSION_DESTROY							3
CONST_INT	QUARRY_MISSION_REMOVAL							4
CONST_INT	QUARRY_MISSION_DELIVERY							5
CONST_INT	QUARRY_MISSION_SPILLAGE							6
CONST_INT	QUARRY_MISSION_BURIAL							7
// -----
CONST_INT	QUARRY_HIGHEST_MISSION							7	// same as last mission number above (ALSO: same as QUARRY_LEVELS at top)

// ...general mission controls
CONST_INT	QUARRY_MAX_ROCKS								7
CONST_INT	QUARRY_MAX_BOMBS								10
CONST_INT	QUARRY_MAX_BODIES								3
CONST_INT	QUARRY_MAX_FIRES								18	// NOTE: 3 more than the Removal Max Fires
CONST_INT	QUARRY_MAX_FIRE_FUEL							7
CONST_INT	QUARRY_MAX_HELPERS								3

// ...mission control variables
CONST_INT	QUARRY_BURIAL_MISSION_TIME_sec					481	// 8mins (+1 sec for cosmetic reasons)
CONST_INT	QUARRY_ROCKFALL_MAX_ROCKS						7
//CONST_INT	QUARRY_ROCKFALL_HOURS							3
//CONST_INT	QUARRY_ROCKFALL_MINUTES							30
CONST_INT	QUARRY_ROCKFALL_MISSION_TIME_sec				211	// 3mins 30secs (+1 sec for cosmetic reasons)
CONST_INT	QUARRY_SABOTAGE_MAX_BOMBS						4
CONST_FLOAT	QUARRY_SABOTAGE_PLAYER_IN_EXPLOSION_RANGE_m		20.0
CONST_FLOAT QUARRY_SABOTAGE_THEATRICAL_EXPLOSION_RANGE_m	21.0	// 1m farther than above
CONST_INT	QUARRY_SABOTAGE_MISSION_TIME_sec				300	// 5 mins
CONST_INT	QUARRY_REMOVAL_MAX_BODIES						3
CONST_INT	QUARRY_REMOVAL_MAX_FIRES						15
CONST_INT	QUARRY_REMOVAL_MAX_FIRE_FUEL					7
//CONST_INT	QUARRY_REMOVAL_HOURS							4
//CONST_INT	QUARRY_REMOVAL_MINUTES							0
CONST_INT	QUARRY_REMOVAL_MISSION_TIME_sec					241	// 4mins (+1 sec for cosmetic reasons)
CONST_INT	QUARRY_DELIVERY_MAX_BOMBS						6
CONST_INT	QUARRY_DELIVERY_HEALTH_THRESHOLD				10
//CONST_INT	QUARRY_DELIVERY_HOURS							4
//CONST_INT	QUARRY_DELIVERY_MINUTES							0
//CONST_INT	QUARRY_DELIVERY_MISSION_TIME_sec				301	// 5mins (+1 sec for cosmetic reasons)
CONST_INT	QUARRY_DELIVERY_MISSION_TIME_sec				181	// 3mins (+1 sec for cosmetic reasons)
CONST_INT	QUARRY_SPILLAGE_MAX_BOMBS						10
CONST_INT	QUARRY_SPILLAGE_TRAIN_TIME_sec					181	// 3mins (+1 sec for cosmetic reasons)
CONST_FLOAT	QUARRY_SPILLAGE_TRAIN_SPEED						56.0
CONST_INT	QUARRY_DESTROY_MAX_CRATES						6
CONST_INT	QUARRY_DESTROY_MISSION_TIME_sec					481	// 8mins (+1 sec for cosmetic reasons)
CONST_FLOAT	QUARRY_DESTROY_MAX_DISTANCE_APART_m				600.0
CONST_FLOAT	QUARRY_DESTROY_TOO_FAR_AWAY_WARNING_m			500.0
CONST_FLOAT	QUARRY_DESTROY_CLEAR_TOO_FAR_AWAY_WARNING_m		400.0

// ...mission stage IDs
CONST_INT	QUARRY_STAGE_NONE								0
CONST_INT	QUARRY_STAGE_BURIAL_PUSH_BODY					1
CONST_INT	QUARRY_STAGE_BURIAL_LOAD_DUMPER					2
CONST_INT	QUARRY_STAGE_BURIAL_DRIVE_DUMPER				3
CONST_INT	QUARRY_STAGE_BURIAL_COMPLETE					4
CONST_INT	QUARRY_STAGE_ROCKFALL_CLEAR_PATHS				5
CONST_INT	QUARRY_STAGE_ROCKFALL_COMPLETE					6
CONST_INT	QUARRY_STAGE_SABOTAGE_MOVE_BOMBS				7
CONST_INT	QUARRY_STAGE_SABOTAGE_COMPLETE					8
CONST_INT	QUARRY_STAGE_REMOVAL_REACH_DUMPER				9
CONST_INT	QUARRY_STAGE_REMOVAL_DUMP_BODIES				10
CONST_INT	QUARRY_STAGE_REMOVAL_COMPLETE					11
CONST_INT	QUARRY_STAGE_DELIVERY_DELIVER					12
CONST_INT	QUARRY_STAGE_DELIVERY_COMPLETE					13
CONST_INT	QUARRY_STAGE_SPILLAGE_CLEAR_TRACKS				14
CONST_INT	QUARRY_STAGE_SPILLAGE_COMPLETE					15
CONST_INT	QUARRY_STAGE_DESTROY_CHASE_TRUCK				16
CONST_INT	QUARRY_STAGE_DESTROY_COMPLETE					17
CONST_INT	QUARRY_STAGE_ASSET_ACQUIRED						18
CONST_INT	QUARRY_STAGE_ASSET_ACQUIRED_COMPLETE			19

// ...crane lift radius
CONST_FLOAT	QUARRY_CRANE_DISPLAY_RADIUS						7.0
CONST_FLOAT	QUARRY_CRANE_LIFT_RADIUS						4.0

// ...dumper load
CONST_FLOAT	QUARRY_DUMPER_RADIUS							3.2
CONST_FLOAT QUARRY_DUMPER_HEIGHT							1.5

// ...General
CONST_FLOAT	QUARRY_AUTO_ATTACH								-1000.0
CONST_INT	QUARRY_PERSIST									-2



// Mission Specific Variables
// Integer Variables
// ...chars 				(char)
LVAR_INT	charDriver
// ...cars 					(car)
LVAR_INT	carDirtbike
LVAR_INT	carDozer
LVAR_INT	carDumper
LVAR_INT	carCopBike
LVAR_INT	carTrain
LVAR_INT	carStolen
// ...objects 				(object)
LVAR_INT	objectDeadBody
LVAR_INT	objectDumperLoadHelper
LVAR_INT	objectRocks[QUARRY_MAX_ROCKS]
LVAR_INT	objectBombs[QUARRY_MAX_BOMBS]
LVAR_INT	objectBodies[QUARRY_MAX_BODIES]
LVAR_INT	objectFireFuel[QUARRY_MAX_FIRE_FUEL]
LVAR_INT	objectTrainHelper
LVAR_INT	objectHelpers[QUARRY_MAX_HELPERS]
// ...blips 				(blip)
LVAR_INT	blipDozer
LVAR_INT	blipDumper
LVAR_INT	blipCrane
LVAR_INT	blipDestination
LVAR_INT	blipDeadBody
LVAR_INT	blipCopBike
LVAR_INT	blipEntrance
LVAR_INT	blipOtherEntrance
LVAR_INT	blipRocks[QUARRY_MAX_ROCKS]
LVAR_INT	blipBomb
LVAR_INT	blipTrain
LVAR_INT	blipBombs[QUARRY_MAX_BOMBS]
LVAR_INT	blipStolen
// ...fires					(fire)
LVAR_INT	fireFires[QUARRY_MAX_FIRES]
// ...pickups 				(pickup)
// ...fx systems 			(fx)
// ...decision makers		(dm)
// ...AI Status				(ai)
// ...general status		(status)
// ...Timers				(timer)
LVAR_INT	timerMissionText
LVAR_INT	timerMissionPass
LVAR_INT	timerMissionFail
LVAR_INT	timerBombsExplode[QUARRY_SABOTAGE_MAX_BOMBS]
LVAR_INT	timerBombsSecondExplosion[QUARRY_SABOTAGE_MAX_BOMBS]
LVAR_INT	timerTrainStart
LVAR_INT	timerCutscene
LVAR_INT	timerBombStuck[QUARRY_DELIVERY_MAX_BOMBS]
// ...Counters				(count)
// ...Checkpoints			(check)
LVAR_INT	checkRockfalls[QUARRY_ROCKFALL_MAX_ROCKS]
LVAR_INT	checkDestination
// ...mission specific		(n)
LVAR_INT	nCurrentMission
LVAR_INT	nRockfallRocks
LVAR_INT	nHours
LVAR_INT	nMinutes
LVAR_INT	nAddHours
LVAR_INT	nAddMinutes
LVAR_INT	nCurrentBomb
LVAR_INT	nPreviousHealth
LVAR_INT	nMissionStartTime
LVAR_INT	nMissionStopTime
LVAR_INT	nBestMinutes
LVAR_INT	nBestSeconds
LVAR_INT	nTotalMinutes
LVAR_INT	nTotalSeconds


// Text Label Variables


// Float Variables
// ...area variables 		(xlo, ylo, zlo, xhi, yhi, zhi)
// ...position variables	(xpos, ypos, zpos)
LVAR_FLOAT	xposDestination		yposDestination		zposDestination
LVAR_FLOAT	xposEntrance		yposEntrance		zposEntrance
LVAR_FLOAT	xposBombs[QUARRY_SABOTAGE_MAX_BOMBS]
LVAR_FLOAT	yposBombs[QUARRY_SABOTAGE_MAX_BOMBS]
LVAR_FLOAT	zposBombs[QUARRY_SABOTAGE_MAX_BOMBS]
// ...heading variables
// ...mission specific


// Boolean Variables
// ...flags					(flag)
LVAR_INT	flagReadyToLift
LVAR_INT	flagReadyToDrive
LVAR_INT	flagShowDestinationHighlight
LVAR_INT	flagCopBikeInPlace
LVAR_INT	flagDeadBodyInPlace
LVAR_INT	flagReadyToDump
LVAR_INT	flagDumped
LVAR_INT	flagReturned
LVAR_INT	flagDisplayEntranceBlip
LVAR_INT	flagDisplayOtherEntranceBlip
LVAR_INT	flagDisplayCraneBlip
LVAR_INT	flagDisplayingMissionText
LVAR_INT	flagDumperTextDisplayed
LVAR_INT	flagDozerTextDisplayed
LVAR_INT	flagRockfallRocksPushed[QUARRY_ROCKFALL_MAX_ROCKS]
LVAR_INT	flagBombDisplayed
LVAR_INT	flagAllBombsMoved
LVAR_INT	flagBombExploded[QUARRY_SABOTAGE_MAX_BOMBS]
LVAR_INT	flagBombsSecondExplosion[QUARRY_SABOTAGE_MAX_BOMBS]
LVAR_INT	flagAlternativeExplosionLocation
LVAR_INT	flagDumperHelpDisplayed
LVAR_INT	flagDisplayedTracksText
LVAR_INT	flagDisplayedDumpText
LVAR_INT	flagBombBlipsOnDisplay
LVAR_INT	flagTrainStarted
LVAR_INT	flagTrainPassed
LVAR_INT	flagBombsOnTracks[QUARRY_SPILLAGE_MAX_BOMBS]
LVAR_INT	flagDestroyed
LVAR_INT	flagDisplayedTooFarText
LVAR_INT	flagCopBikeInWater
LVAR_INT	flagCopBodyInWater
LVAR_INT	flagBikeAndBodyFrozen
LVAR_INT	flagRoadsSwitchedOff
LVAR_INT	flagBeenInDumper
LVAR_INT	flagRockfallCheckpointsOnDisplay[QUARRY_ROCKFALL_MAX_ROCKS]
LVAR_INT	flagSkippingMission
LVAR_INT	flagEqualledBestTime
LVAR_INT	flagBeatBestTime
LVAR_INT	flagShowAssetAcquired
LVAR_INT	flagBombStuck[QUARRY_DELIVERY_MAX_BOMBS]
// ...exists flags			(exists)
// Clear Exists Flags
// ...loaded flags			<loaded>
// Clear Loaded Flags


// Clear timers


// Clear important flags
flagReadyToLift					= FALSE
flagReadyToDrive				= FALSE
flagCopBikeInPlace				= FALSE
flagDeadBodyInPlace				= FALSE
flagReadyToDump					= FALSE
flagDumped						= FALSE
flagDisplayEntranceBlip			= FALSE
flagDisplayOtherEntranceBlip	= FALSE
flagDisplayCraneBlip			= FALSE
flagDisplayingMissionText		= FALSE
flagDumperTextDisplayed			= FALSE
flagDozerTextDisplayed			= FALSE
flagBombDisplayed				= FALSE
flagAllBombsMoved				= FALSE
flagDumperHelpDisplayed			= FALSE
flagDisplayedTracksText			= FALSE
flagDisplayedDumpText			= FALSE
flagBombBlipsOnDisplay			= FALSE
flagTrainStarted				= FALSE
flagTrainPassed					= FALSE
flagDestroyed					= FALSE
flagDisplayedTooFarText			= FALSE
flagCopBikeInWater				= FALSE
flagCopBodyInWater				= FALSE
flagBikeAndBodyFrozen			= FALSE
flagRoadsSwitchedOff			= FALSE
flagBeenInDumper				= FALSE
flagSkippingMission				= FALSE
flagEqualledBestTime			= FALSE
flagBeatBestTime				= FALSE
flagShowAssetAcquired			= FALSE



// ***** FAKE ENTITY CREATION TO FOOL THE COMPILER *****
// The compiler just needs to verify there is a CREATE_ before usage
IF m_stage = -99
	
	WRITE_DEBUG SHOULD_NEVER_BE_IN_FAKE_ENTITY_CREATION

	CREATE_CAR SANCHEZ	0.0 0.0 0.0 carDirtbike
	CREATE_CAR DOZER	0.0 0.0 0.0 carDozer
	CREATE_CAR DUMPER	0.0 0.0 0.0 carDumper
	CREATE_CAR COPBIKE	0.0 0.0 0.0 carCopBike
	CREATE_CAR DUMPER	0.0 0.0 0.0 carStolen

	CREATE_CHAR PEDTYPE_CIVMALE BMYBE 0.0 0.0 0.0 charDriver

//	CREATE_OBJECT DYN_QUARRYROCK02	0.0 0.0 0.0 objectDeadBody
	CREATE_OBJECT DEAD_TIED_COP		0.0 0.0 0.0 objectDeadBody
	CREATE_OBJECT CR_AMMOBOX		0.0 0.0 0.0 objectDumperLoadHelper
	CREATE_OBJECT CR_AMMOBOX		0.0 0.0 0.0 objectTrainHelper

	REPEAT QUARRY_MAX_ROCKS nLoop
		CREATE_OBJECT DYN_QUARRYROCK03	0.0 0.0 0.0 objectRocks[nLoop]
	ENDREPEAT

	REPEAT QUARRY_MAX_BOMBS nLoop
		CREATE_OBJECT KB_BARREL	0.0 0.0 0.0 objectBombs[nLoop]
	ENDREPEAT

	REPEAT QUARRY_MAX_BODIES nLoop
//		CREATE_OBJECT DYN_QUARRYROCK02	0.0 0.0 0.0 objectBodies[nLoop]
		CREATE_OBJECT DEAD_TIED_COP 0.0 0.0 0.0 objectBodies[nLoop]
	ENDREPEAT

	REPEAT QUARRY_MAX_FIRE_FUEL nLoop
		CREATE_OBJECT CM_BOX 0.0 0.0 0.0 objectFireFuel[nLoop]
	ENDREPEAT

	CREATE_MISSION_TRAIN 11 0.0 0.0 0.0 FALSE carTrain

	REPEAT QUARRY_ROCKFALL_MAX_ROCKS nLoop
		CREATE_CHECKPOINT CHECKPOINT_EMPTYTUBE 0.0 0.0 0.0 0.0 0.0 0.0 0.0 checkRockfalls[nLoop]
	ENDREPEAT

	REPEAT QUARRY_MAX_HELPERS nLoop
		CREATE_OBJECT CR_AMMOBOX 0.0 0.0 0.0 objectHelpers[nLoop]
	ENDREPEAT

	CREATE_CHECKPOINT CHECKPOINT_EMPTYTUBE 0.0 0.0 0.0 0.0 0.0 0.0 0.0 checkDestination

	ADD_BLIP_FOR_CAR carDozer blipDozer
	ADD_BLIP_FOR_CAR carDumper blipDumper
	ADD_BLIP_FOR_CAR carCopBike blipCopBike
	ADD_BLIP_FOR_CAR carTrain blipTrain
	ADD_BLIP_FOR_CAR carStolen blipStolen
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 blipCrane
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 blipDestination
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 blipEntrance
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 blipOtherEntrance
	ADD_BLIP_FOR_OBJECT objectDeadBody blipDeadBody
	ADD_BLIP_FOR_OBJECT objectBombs[0] blipBomb

	REPEAT QUARRY_MAX_ROCKS nLoop
		ADD_BLIP_FOR_OBJECT objectRocks[nLoop] blipRocks[nLoop]
	ENDREPEAT

	REPEAT QUARRY_MAX_BOMBS nLoop
		ADD_BLIP_FOR_OBJECT objectBombs[nLoop] blipBombs[nLoop]
	ENDREPEAT

ENDIF



// Mission Text
LOAD_MISSION_TEXT Quarry



// Don't start the Quarry mission if the player has a wanted level.
IF NOT IS_CHAR_DEAD scplayer
	IF IS_WANTED_LEVEL_GREATER player1 0
	   	PRINT_NOW VAL_C1 5000 1
   		SET_CHAR_COORDINATES scplayer 827.9296 857.7913 11.3110 
   		SET_CHAR_HEADING scplayer 120.0000
   		SET_CAMERA_BEHIND_PLAYER
		SET_PLAYER_CONTROL player1 ON
	   	DO_FADE 500 FADE_IN

		// Return the oddjob 'title over fade' status back to default
		DRAW_ODDJOB_TITLE_BEFORE_FADE TRUE

	   	RETURN
   	ENDIF
ENDIF



//// Any more quarry missions to do?
//IF done_quarry_progress = TRUE
//	// ...no
//	PRINT_NOW QUAR_P3 5000 1
//	WAIT 5000
//	RETURN
//ENDIF



// Load Char Mission Decision Makers
// NOTE: Do it before the Initialisation because they make the game pause. Done here, the pause is hidden by a fade.


// Mission Initialisation
GOSUB Quarry_Initialisation






// *************************************************************************************************************
//								                 MISSION LOOP
// *************************************************************************************************************
Quarry_Mission_Loop:

	WAIT 0


	// Special shortcut for Craig F to test all missions in order
	IF NOT m_stage = QUARRY_STAGE_ASSET_ACQUIRED
	AND NOT m_stage = QUARRY_STAGE_ASSET_ACQUIRED_COMPLETE
		IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
			// Make sure the record for this mission doesn't become unfeasibly low because of the skip
			nTempInt = g_Quarry_recordsKM[g_nQuarryMissionsPassed] * 1000
			nMissionStartTime = m_mission_timer - nTempInt

			m_passed = TRUE
			GOTO Quarry_End_Of_Main_Loop
		ENDIF


		// Check if the mission is being skipped
		IF done_quarry_progress = TRUE
			IF flagSkippingMission = TRUE
				// ...button previously pressed
				IF NOT IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
					// ...button release, so skip mission
					m_quit = TRUE
					PRINT_NOW QUAR_37 5000 1
					PRINT_BIG SKIPPED 5000 5

					g_nQuarryMissionsPassed++
					IF g_nQuarryMissionsPassed = QUARRY_HIGHEST_MISSION
						g_nQuarryMissionsPassed = 0
					ENDIF

					// Switch on Dumper and Dozer car generators again
					SWITCH_CAR_GENERATOR gen_car1 101
					SWITCH_CAR_GENERATOR gen_car2 101

					GOTO Quarry_End_Of_Main_Loop
				ENDIF
			ELSE
				// ...button not pressed
				IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
					flagSkippingMission = TRUE
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Debug Stuff
	GOSUB Quarry_Debug_Tools
	GOSUB Quarry_Debug_Shortcuts

	IF m_quit = 1
	OR m_pause = 1
		GOTO Quarry_End_Of_Main_Loop
	ENDIF


	// Housekeeping
	GOSUB Quarry_Frame_Counter
	GOSUB Quarry_Additional_Timers


	// Special conditions
	IF IS_CHAR_DEAD scplayer
		m_failed = 1
		GOTO Quarry_End_Of_Main_Loop
	ENDIF		


	// Mission Stage processing
	// *** INITIALISATION NOW TAKES PLACE BEFORE THE MAIN LOOP ***
	IF m_stage = 0
		WRITE_DEBUG STAGE_SHOULD_NEVER_BE_0_IN_MAIN_LOOP
	ENDIF


	SWITCH m_stage
		// ROCKFALL sub-mission
		CASE QUARRY_STAGE_ROCKFALL_CLEAR_PATHS
			GOSUB Quarry_Rockfall_Clear_Paths
			BREAK

		// SABOTAGE sub-mission
		CASE QUARRY_STAGE_SABOTAGE_MOVE_BOMBS
			GOSUB Quarry_Sabotage_Move_Bombs
			BREAK

		// REMOVAL sub-mission
		CASE QUARRY_STAGE_REMOVAL_REACH_DUMPER
			GOSUB Quarry_Removal_Reach_Dumper
			BREAK
		CASE QUARRY_STAGE_REMOVAL_DUMP_BODIES
			GOSUB Quarry_Removal_Dump_Bodies
			BREAK

		// DELIVERY sub-mission
		CASE QUARRY_STAGE_DELIVERY_DELIVER
			GOSUB Quarry_Delivery_Deliver
			BREAK

		// SPILLAGE sub-mission
		CASE QUARRY_STAGE_SPILLAGE_CLEAR_TRACKS
			GOSUB Quarry_Spillage_Clear_Tracks
			BREAK

		// BURIAL Sub-mission
		CASE QUARRY_STAGE_BURIAL_PUSH_BODY
			GOSUB Quarry_Burial_Push_Body
			BREAK
		CASE QUARRY_STAGE_BURIAL_LOAD_DUMPER
			GOSUB Quarry_Burial_Load_Dumper
			BREAK
		CASE QUARRY_STAGE_BURIAL_DRIVE_DUMPER
			GOSUB Quarry_Burial_Drive_Dumper
			BREAK

		// DESTROY sub-mission
		CASE QUARRY_STAGE_DESTROY_CHASE_TRUCK
			GOSUB Quarry_Destroy_Chase_Truck
			BREAK

		// Passed
		CASE QUARRY_STAGE_ROCKFALL_COMPLETE
		CASE QUARRY_STAGE_SABOTAGE_COMPLETE
		CASE QUARRY_STAGE_REMOVAL_COMPLETE
		CASE QUARRY_STAGE_DELIVERY_COMPLETE
		CASE QUARRY_STAGE_SPILLAGE_COMPLETE
		CASE QUARRY_STAGE_BURIAL_COMPLETE
		CASE QUARRY_STAGE_DESTROY_COMPLETE
			m_passed = TRUE
			BREAK

		// Asset Acquired
		CASE QUARRY_STAGE_ASSET_ACQUIRED
			GOSUB Quarry_Asset_Acquired
			BREAK

		CASE QUARRY_STAGE_ASSET_ACQUIRED_COMPLETE
			m_quit = TRUE
			BREAK
	ENDSWITCH



// Continuous update methods and event checking


// End of Main Loop
// ***** DON'T CHANGE *****
Quarry_End_Of_Main_Loop:

	IF m_quit = 0
		IF m_failed = 0
			IF m_passed = 0																 
				// Restart main loop
				GOTO Quarry_Mission_Loop 
			ELSE
				// Mission passed
				GOSUB Quarry_Mission_Passed

				// Check if the 'asset acquired' cutscene should be shown
				IF flagShowAssetAcquired = TRUE
					// ...yes
					m_passed = FALSE
					m_stage = QUARRY_STAGE_ASSET_ACQUIRED
					m_goals = 0
					flagShowAssetAcquired = FALSE

					GOTO Quarry_Mission_Loop
				ENDIF

				RETURN
			ENDIF
		ELSE
			// Mission failed
			GOSUB Quarry_Mission_Failed
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
// BURIAL: Push Body

Quarry_Burial_Push_Body:

	// Check if the dozer is still alive
	IF IS_CAR_DEAD carDozer
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_DOZER_DESTROYED
		RETURN
	ENDIF


	// Check if the dumper is still alive
	IF IS_CAR_DEAD carDumper
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_DUMPER_DESTROYED
		RETURN
	ENDIF


	// A dummy check for the copbike (because I'm forcing it to stay alive to allow it to be lifted by the crane)
	IF NOT IS_CAR_DEAD carCopBike
		SET_CAR_HEALTH carCopBike 10000
	ENDIF
	

   	// Initialisation
	// --------------

   	IF m_goals = 0
		// Add Blip for Dozer
		ADD_BLIP_FOR_CAR carDozer blipDozer
		SET_BLIP_AS_FRIENDLY blipDozer TRUE

		flagReadyToLift			= FALSE
		flagCopBikeInPlace		= FALSE
		flagDeadBodyInPlace		= FALSE
		flagBikeAndBodyFrozen	= FALSE

		xposDestination = 672.7664
		yposDestination = 888.3682
		zposDestination = -41.3985

		// INFO: Mission Description
		PRINT_NOW QUAR_00 10000 1
//		timerMissionText = m_mission_timer + 9500
//		flagDisplayingMissionText = TRUE

//		g_Quarry_timerKM = QUARRY_BURIAL_MISSION_TIME_sec * 1000
		nTempInt = QUARRY_MISSION_BURIAL - 1
		g_Quarry_timerKM = g_Quarry_recordsKM[nTempInt] * 1000
		DISPLAY_ONSCREEN_TIMER_WITH_STRING g_Quarry_timerKM TIMER_DOWN QUAR_D1
		nMissionStartTime = m_mission_timer

		IF NOT IS_CAR_DEAD carCopBike
			SET_VEHICLE_IS_CONSIDERED_BY_PLAYER carCopBike FALSE
		ENDIF

		// Make sure the crane can't pick the Dumper and Dozer up
		WINCH_CAN_PICK_VEHICLE_UP carDumper FALSE
		WINCH_CAN_PICK_VEHICLE_UP carDozer FALSE
	
		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------

	// Check for Time Up
	IF g_Quarry_timerKM <= 0
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_COPS_ARRIVED
		RETURN
	ENDIF


	// Subgoals
	// --------

	// Check if body and bike are in 'lift' location
	IF m_goals = 1
		IF flagReadyToLift = TRUE
			REMOVE_BLIP blipDestination
			REMOVE_BLIP blipDozer
			REMOVE_BLIP blipDeadBody
			REMOVE_BLIP blipCopBike

			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	// Blip maintenance
	IF flagReadyToLift = FALSE
		IF IS_CHAR_IN_CAR scplayer carDozer
			// ...player is in the dozer
			IF DOES_BLIP_EXIST blipDozer
				// ...remove the dozer blip and add the objects blips and the destination blip
				REMOVE_BLIP blipDozer
				ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination

				CLEAR_THIS_PRINT QUAR_35

				// Add blips for Dead Body and Cop Bike
				IF flagDeadBodyInPlace = FALSE
					ADD_BLIP_FOR_OBJECT objectDeadBody blipDeadBody
				ENDIF

				IF flagCopBikeInPlace = FALSE
					ADD_BLIP_FOR_CAR carCopBike blipCopBike
					CHANGE_BLIP_COLOUR blipCopBike GREEN
				ENDIF
			ENDIF
		ELSE
			// ...player is not in the dozer
			IF NOT DOES_BLIP_EXIST blipDozer
				// ...add the dozer blip and remove the objects blips and the destination blip
				ADD_BLIP_FOR_CAR carDozer blipDozer
				SET_BLIP_AS_FRIENDLY blipDozer TRUE

				REMOVE_BLIP blipDestination
				REMOVE_BLIP blipDeadBody
				REMOVE_BLIP blipCopBike

				// Info: Get back in the Dozer
				PRINT_NOW QUAR_35 5000 1
			ENDIF
		ENDIF
	ENDIF


	// Decide if the locate highlight should be shown or not
	IF flagShowDestinationHighlight = FALSE
		IF IS_CHAR_IN_CAR scplayer carCopBike
		OR IS_CHAR_IN_CAR scplayer carDozer
			flagShowDestinationHighlight = TRUE

			// INFO: push body and bike
			PRINT_NOW QUAR_02 7000 1
		ENDIF
	ENDIF


	// Check if both the dead body and the cop bike are in the crane lift radius
	IF flagReadyToLift = FALSE
		// Need to do this, but I don't really care, so the dead check is a dummy
		IF flagCopBikeInPlace = FALSE
			// ...display
			IF LOCATE_CAR_3D carCopBike xposDestination yposDestination zposDestination QUARRY_CRANE_DISPLAY_RADIUS QUARRY_CRANE_DISPLAY_RADIUS 4.0 flagShowDestinationHighlight
				// ...locate
				IF LOCATE_CAR_3D carCopBike xposDestination yposDestination zposDestination QUARRY_CRANE_LIFT_RADIUS QUARRY_CRANE_LIFT_RADIUS 4.0 FALSE
					flagCopBikeInPlace = TRUE
					REMOVE_BLIP blipCopBike

					// Freeze the cop bike position to stop it sliding over the pickup area
					FREEZE_CAR_POSITION carCopBike TRUE
					SET_CAR_COLLISION carCopBike FALSE

					IF flagDeadBodyInPlace = FALSE
						// INFO: now get body
						PRINT_NOW QUAR_03 5000 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF flagDeadBodyInPlace = FALSE
			IF flagCopBikeInPlace = TRUE
				// ...cop bike is in place so need to show highlight
				// ...display
				IF LOCATE_OBJECT_3D objectDeadBody xposDestination yposDestination zposDestination QUARRY_CRANE_DISPLAY_RADIUS QUARRY_CRANE_DISPLAY_RADIUS 4.0 flagShowDestinationHighlight
					// ...locate
					IF LOCATE_OBJECT_3D objectDeadBody xposDestination yposDestination zposDestination QUARRY_CRANE_LIFT_RADIUS QUARRY_CRANE_LIFT_RADIUS 4.0 FALSE
						flagDeadBodyInPlace = TRUE
						REMOVE_BLIP blipDeadBody

						// Freeze the cop body position to stop it sliding over the pickup area
						FREEZE_OBJECT_POSITION objectDeadBody TRUE
						SET_OBJECT_COLLISION objectDeadBody FALSE
					ENDIF
				ENDIF
			ELSE
				// ...cop bike not in place, so the cop bike will show the highlight
				// ...display
				IF LOCATE_OBJECT_3D objectDeadBody xposDestination yposDestination zposDestination QUARRY_CRANE_DISPLAY_RADIUS QUARRY_CRANE_DISPLAY_RADIUS 4.0 FALSE
					// ...locate
					IF LOCATE_OBJECT_3D objectDeadBody xposDestination yposDestination zposDestination QUARRY_CRANE_LIFT_RADIUS QUARRY_CRANE_LIFT_RADIUS 4.0 FALSE
						flagDeadBodyInPlace = TRUE
						REMOVE_BLIP blipDeadBody

						// Freeze the cop body position to stop it sliding over the pickup area
						FREEZE_OBJECT_POSITION objectDeadBody TRUE
						SET_OBJECT_COLLISION objectDeadBody FALSE

						// INFO: now get bike
						PRINT_NOW QUAR_04 5000 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF flagDeadBodyInPlace = TRUE
		AND flagCopBikeInPlace = TRUE
			flagReadyToLift = TRUE
			
			// INFO: get into crane
			PRINT_NOW QUAR_05 5000 1

			flagBikeAndBodyFrozen = TRUE
		ENDIF
	ENDIF

/*
	// Mission Text
	IF flagDisplayingMissionText = TRUE
		IF timerMissionText < m_mission_timer
			IF NOT IS_CHAR_IN_CAR scplayer carDozer

				// INFO: get into dozer
				PRINT_NOW QUAR_01 5000 1
				flagDisplayingMissionText = FALSE
			ENDIF
		ENDIF
	ENDIF
*/	


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Quarry_Next_Stage
	ENDIF

RETURN


// ****************************************
// BURIAL: Load Dumper

Quarry_Burial_Load_Dumper:

	// Check if the dozer is still alive
	IF IS_CAR_DEAD carDozer
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_DOZER_DESTROYED
		RETURN
	ENDIF


	// Check if the dumper is still alive
	IF IS_CAR_DEAD carDumper
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_DUMPER_DESTROYED
		RETURN
	ENDIF


	// A dummy check for the copbike (because I'm forcing it to stay alive to allow it to be lifted by the crane)
	IF NOT IS_CAR_DEAD carCopBike
		SET_CAR_HEALTH carCopBike 10000
	ENDIF
	

   	// Initialisation
	// --------------

   	IF m_goals = 0
		flagReadyToDrive 	= FALSE
		flagCopBikeInPlace	= FALSE
		flagDeadBodyInPlace	= FALSE

		// Add a blip for the crane
		ADD_BLIP_FOR_COORD 705.1055 916.3154 -19.6407 blipCrane
		SET_BLIP_AS_FRIENDLY blipCrane TRUE
		flagDisplayCraneBlip = TRUE

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------

	// Check for Time Up
	IF g_Quarry_timerKM <= 0
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_COPS_ARRIVED
		RETURN
	ENDIF


	// Subgoals
	// --------

	// Wait for the dumper to be loaded up
	IF m_goals = 1
		IF flagReadyToDrive = TRUE
			REMOVE_BLIP blipDumper
			SET_CAR_HEAVY carDumper TRUE

			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	// Make sure the Load Helper object stays in place
	IF NOT IS_OBJECT_ATTACHED objectDumperLoadHelper
		ATTACH_OBJECT_TO_CAR objectDumperLoadHelper carDumper 0.0 -1.7 1.5 0.0 0.0 0.0
	ENDIF


/*
	// Has the dumper been loaded?
	IF flagReadyToDrive = FALSE
		GET_OBJECT_COORDINATES objectDumperLoadHelper xposTemp yposTemp zposTemp
		IF LOCATE_STOPPED_CAR_3D carCopBike xposTemp yposTemp zposTemp QUARRY_DUMPER_RADIUS QUARRY_DUMPER_RADIUS QUARRY_DUMPER_HEIGHT FALSE
		AND LOCATE_OBJECT_3D objectDeadBody xposTemp yposTemp zposTemp QUARRY_DUMPER_RADIUS QUARRY_DUMPER_RADIUS QUARRY_DUMPER_HEIGHT FALSE
			GRAB_ENTITY_ON_ROPE_FOR_OBJECT quarry_arm nTempCar nTempPed nTempObject
			IF NOT nTempCar = carCopBike
			AND NOT nTempObject = objectDeadBody
				flagReadyToDrive = TRUE

				// Make sure the crane can't pick these Objects up
				WINCH_CAN_PICK_VEHICLE_UP carCopBike FALSE
				WINCH_CAN_PICK_OBJECT_UP objectDeadBody FALSE
			ENDIF
		ENDIF
	ENDIF
*/

	
	// Has the dumper been loaded?
	IF flagReadyToDrive = FALSE
		GET_OBJECT_COORDINATES objectDumperLoadHelper xposTemp yposTemp zposTemp
		GRAB_ENTITY_ON_ROPE_FOR_OBJECT quarry_arm nTempCar nTempPed nTempObject

		// Update condition of dead body
		IF flagDeadBodyInPlace = FALSE
			// ...dead body isn't in place, so check if it is now
			IF LOCATE_OBJECT_3D objectDeadBody xposTemp yposTemp zposTemp QUARRY_DUMPER_RADIUS QUARRY_DUMPER_RADIUS QUARRY_DUMPER_HEIGHT FALSE
				IF IS_OBJECT_ATTACHED objectDeadBody
					// ...the dead body is attached to the dumper
					IF NOT nTempObject = objectDeadBody
						// ...the dead body is no longer attached to the crane
						flagDeadBodyInPlace = TRUE
						WINCH_CAN_PICK_OBJECT_UP objectDeadBody FALSE
					ELSE
						// ...the dead body is still attached to the crane, so detach it from the dumper
						DETACH_OBJECT objectDeadBody 0.0 0.0 0.0 FALSE
					ENDIF
				ENDIF
				
//				IF NOT nTempObject = objectDeadBody
//				AND IS_OBJECT_ATTACHED objectDeadBody
//					// ...dead body is now attached to the dumper and not attached to the crane
//					flagDeadBodyInPlace = TRUE
//					WINCH_CAN_PICK_OBJECT_UP objectDeadBody FALSE
//				ENDIF
			ENDIF
		ELSE
			// ...dead body is in place, so ensure it stays there
			IF NOT IS_OBJECT_ATTACHED objectDeadBody
				GET_OBJECT_COORDINATES objectDeadBody xposTemp yposTemp zposTemp
				GET_GROUND_Z_FOR_3D_COORD xposTemp yposTemp zposTemp fTempFloat
				fTempFloat2 = zposTemp - fTempFloat
				IF fTempFloat2 < 1.5
					// ...object is on the ground, so allow the crane to pick it up again
					flagDeadBodyInPlace = FALSE
					WINCH_CAN_PICK_OBJECT_UP objectDeadBody TRUE
				ENDIF
			ENDIF
		ENDIF

		// Update condition of cop bike
		IF flagCopBikeInPlace = FALSE
			// ...cop bike isn't in place, so check if it is now
			IF LOCATE_STOPPED_CAR_3D carCopBike xposTemp yposTemp zposTemp QUARRY_DUMPER_RADIUS QUARRY_DUMPER_RADIUS QUARRY_DUMPER_HEIGHT FALSE
				IF NOT nTempCar = carCopBike
					// ...cop bike is in the dumper and not attached to the crane
					ATTACH_CAR_TO_CAR carCopBike carDumper QUARRY_AUTO_ATTACH 0.0 0.0 0.0 0.0 0.0
					flagCopBikeInPlace = TRUE
					WINCH_CAN_PICK_VEHICLE_UP carCopBike FALSE
				ENDIF
			ENDIF
		ELSE
			// ...cop bike is in place, so ensure it stays there
			IF NOT IS_VEHICLE_ATTACHED carCopBike
				GET_CAR_COORDINATES carCopBike xposTemp yposTemp zposTemp
				GET_GROUND_Z_FOR_3D_COORD xposTemp yposTemp zposTemp fTempFloat
				fTempFloat2 = zposTemp - fTempFloat
				IF fTempFloat2 < 1.5
					// ...cop bike is on the ground, so allow the crane to pick it up again
					flagCopBikeInPlace = FALSE
					WINCH_CAN_PICK_VEHICLE_UP carCopBike TRUE
				ENDIF
			ENDIF
		ENDIF

		// Are both in place?
		IF flagDeadBodyInPlace = TRUE
		AND flagCopBikeInPlace = TRUE
			flagReadyToDrive = TRUE
		ENDIF
	ENDIF

	
	// Maintain Crane blip
	IF flagDisplayCraneBlip = TRUE
		// Display highlight
//		LOCATE_CHAR_ANY_MEANS_3D scplayer 705.1055 916.3154 -19.6407 4.0 4.0 4.0 TRUE

		// Check if the player has reached the crane
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 705.1055 916.3154 -19.6407 4.0 4.0 1.2 FALSE
			// ...remove crane blip
			REMOVE_BLIP blipCrane
			flagDisplayCraneBlip = FALSE

			// ...show dumper blip
			ADD_BLIP_FOR_CAR carDumper blipDumper
			SET_BLIP_AS_FRIENDLY blipDumper TRUE

			// INFO: load up dumper
			PRINT_NOW QUAR_06 7000 1
		ENDIF
	ENDIF

	
	// Unfreeze bike and body
	IF flagBikeAndBodyFrozen = TRUE
		IF NOT IS_CHAR_IN_CAR scplayer carDozer
			// Unfreeze the cop body
			FREEZE_OBJECT_POSITION objectDeadBody FALSE
			SET_OBJECT_COLLISION objectDeadBody TRUE

			// Unfreeze the cop bike position again
			FREEZE_CAR_POSITION carCopBike FALSE
			SET_CAR_COLLISION carCopBike TRUE

			flagBikeAndBodyFrozen = FALSE
		ENDIF
	ENDIF


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Quarry_Next_Stage
	ENDIF

RETURN


// ****************************************
// BURIAL: Drive Dumper

Quarry_Burial_Drive_Dumper:

	// Check if the dumper is still alive
	IF IS_CAR_DEAD carDumper
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_DUMPER_DESTROYED
		RETURN
	ENDIF


	// A dummy check for the copbike (because I'm forcing it to stay alive to allow it to be lifted by the crane)
	IF NOT IS_CAR_DEAD carCopBike
		SET_CAR_HEALTH carCopBike 10000
	ENDIF


   	// Initialisation
	// --------------

   	IF m_goals = 0
		xposDestination = 985.1581
		yposDestination = 688.4734
		zposDestination = 8.7255

		GOSUB Quarry_Display_Entrance_Blip

		flagDumped				= FALSE
		flagReadyToDump			= FALSE
		flagDumperTextDisplayed	= FALSE
		flagCopBikeInWater		= FALSE
		flagCopBodyInWater		= FALSE
		flagBeenInDumper		= FALSE

		timerMissionPass = 0

		// INFO: get into dumper
		PRINT_NOW QUAR_07 5000 1

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------

	// Check for Time Up
	IF g_Quarry_timerKM <= 0
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_COPS_ARRIVED
		RETURN
	ENDIF

	// Check for either of the objects being on the ground a distance away from the water
	IF flagReadyToDump = FALSE 
		IF NOT IS_VEHICLE_ATTACHED carCopBike
			GET_CAR_COORDINATES carCopBike xposTemp yposTemp zposTemp
			GET_GROUND_Z_FOR_3D_COORD xposTemp yposTemp zposTemp fTempFloat
			fTempFloat2 = zposTemp - fTempFloat
			IF fTempFloat2 < 1.5
				m_failed = TRUE
				m_fail_reason = QUARRY_FAILED_DROPPED_LOAD
			ENDIF
		ENDIF

		IF NOT IS_OBJECT_ATTACHED objectDeadBody
			GET_OBJECT_COORDINATES objectDeadBody xposTemp yposTemp zposTemp
			GET_GROUND_Z_FOR_3D_COORD xposTemp yposTemp zposTemp fTempFloat
			fTempFloat2 = zposTemp - fTempFloat
			IF fTempFloat2 < 1.5
				m_failed = TRUE
				m_fail_reason = QUARRY_FAILED_DROPPED_LOAD
			ENDIF
		ENDIF
	ENDIF



	// Subgoals
	// --------

	// 
	IF m_goals = 1
		IF flagDumped = TRUE
			// ...wait a few seconds to make sure the dumper doesn't end up in the water also
			IF timerMissionPass < m_mission_timer
				REMOVE_BLIP blipDestination
				DELETE_CHECKPOINT checkDestination

				m_goals = 99
			ENDIF
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	// Blip Maintenance
	IF IS_CHAR_IN_CAR scplayer carDumper
		// ...player is in the dumper
		IF DOES_BLIP_EXIST blipDumper
			// ...remove the dumper blip and add the destination blip
			REMOVE_BLIP blipDumper

			CLEAR_THIS_PRINT QUAR_36

			IF flagDisplayEntranceBlip = TRUE
				GOSUB Quarry_Display_Entrance_Blip
			ELSE
				// ...display destination blip
				ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination
				fTempFloat = yposDestination - 5.0
				CREATE_CHECKPOINT CHECKPOINT_EMPTYTUBE xposDestination fTempFloat zposDestination 0.0 0.0 0.0 5.0 checkDestination
			ENDIF
		ENDIF

		flagBeenInDumper = TRUE
	ELSE
		// ...player is not in the dumper
		IF NOT DOES_BLIP_EXIST blipDumper
			// ...add the dumper blip and remove the destination blip
			ADD_BLIP_FOR_CAR carDumper blipDumper
			SET_BLIP_AS_FRIENDLY blipDumper TRUE

			REMOVE_BLIP blipDestination
			DELETE_CHECKPOINT checkDestination
			REMOVE_BLIP blipEntrance

			IF flagBeenInDumper = TRUE
				// Info: Get back in the Dozer
				PRINT_NOW QUAR_36 5000 1
			ENDIF
		ENDIF
	ENDIF


	// Display dumper text?
	IF flagDumperTextDisplayed = FALSE
		IF IS_CHAR_IN_CAR scplayer carDumper
			// INFO: drive to quarry entrance
			PRINT_NOW QUAR_08 7000 1
			flagDumperTextDisplayed = TRUE
		ENDIF
	ENDIF


	// Entrance blip
	IF DOES_BLIP_EXIST blipEntrance
		// Display a highlight at the entrance
		LOCATE_CHAR_ANY_MEANS_3D scplayer xposEntrance yposEntrance zposEntrance 4.0 4.0 4.0 TRUE

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer xposEntrance yposEntrance zposEntrance 12.0 12.0 12.0 FALSE
			// Remove entrance blip
			REMOVE_BLIP blipEntrance
			flagDisplayEntranceBlip = FALSE

			// Add destination blip
			ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination
			fTempFloat = yposDestination - 5.0
			CREATE_CHECKPOINT CHECKPOINT_EMPTYTUBE xposDestination fTempFloat zposDestination 0.0 0.0 0.0 5.0 checkDestination

			// INFO: dump in water
			PRINT_NOW QUAR_09 5000 1
		ENDIF
	ENDIF


//	// Display the dumping destination highlight
//	IF flagDisplayEntranceBlip = FALSE
//		// ...not displaying entrance blip, so display destination highlight
//		LOCATE_CAR_3D carDumper xposDestination yposDestination zposDestination 8.0 2.5 4.0 TRUE
//	ENDIF


	// Arrival at destination
	IF flagReadyToDump = FALSE
		// Check if the dumper is near the destination
		IF LOCATE_CAR_3D carDumper xposDestination yposDestination zposDestination 50.0 30.0 10.0 FALSE
			flagReadyToDump = TRUE
			PRINT_HELP QUAR_H4
		ELSE
			flagReadyToDump = FALSE
		ENDIF
	ENDIF


	// If the truck is at the destination and the back is raised and the bike or body is attached, unattach them
	IF flagReadyToDump = TRUE
		GET_CAR_MOVING_COMPONENT_OFFSET carDumper fTempFloat
		IF fTempFloat > 0.05
			// Unattach bike
			IF IS_VEHICLE_ATTACHED carCopBike
				DETACH_CAR carCopBike 0.0 0.0 0.0 FALSE
			ENDIF

			// Unattach body
			IF IS_OBJECT_ATTACHED objectDeadBody
				DETACH_OBJECT objectDeadBody 0.0 0.0 0.0 FALSE
			ENDIF
		ENDIF
	ENDIF


	// Cop bike in the water?
	IF flagCopBikeInWater = FALSE
		IF IS_CAR_IN_WATER carCopBike
			flagCopBikeInWater = TRUE
		ENDIF
	ENDIF


	// Cop body in the water?
	IF flagCopBodyInWater = FALSE
		IF IS_OBJECT_IN_WATER objectDeadBody
			flagCopBodyInWater = TRUE
		ENDIF
	ENDIF


	// Bike and Rock in the sea?
	IF flagDumped = FALSE
		IF flagCopBikeInWater = TRUE
		AND flagCopBodyInWater = TRUE
			flagDumped = TRUE
			timerMissionPass = m_mission_timer + 2000
		ENDIF
	ENDIF


	// Make sure the Load Helper object stays in place
	IF NOT IS_OBJECT_ATTACHED objectDumperLoadHelper
		ATTACH_OBJECT_TO_CAR objectDumperLoadHelper carDumper 0.0 -1.7 1.5 0.0 0.0 0.0
	ENDIF


	// Manually attach the cop bike
	IF NOT IS_VEHICLE_ATTACHED carCopBike
		GET_OBJECT_COORDINATES objectDumperLoadHelper xposTemp yposTemp zposTemp
		IF LOCATE_STOPPED_CAR_3D carCopBike xposTemp yposTemp zposTemp QUARRY_DUMPER_RADIUS QUARRY_DUMPER_RADIUS QUARRY_DUMPER_HEIGHT FALSE
			// NOTE: If the xpos is -1000.0 (QUARRY_AUTO_ATTACH) then the bike is attached at its current position and orientation
			ATTACH_CAR_TO_CAR carCopBike carDumper QUARRY_AUTO_ATTACH 0.0 0.0 0.0 0.0 0.0
		ENDIF
	ENDIF

//	IF IS_OBJECT_ATTACHED objectDeadBody
//		IF NOT IS_VEHICLE_ATTACHED carCopBike
//			ATTACH_CAR_TO_CAR carCopBike carDumper 0.0 -1.7 1.0 0.0 0.0 0.0
//		ENDIF
//	ELSE
//		IF IS_VEHICLE_ATTACHED carCopBike
//			DETACH_CAR carCopBike 0.0 0.0 0.0 FALSE
//			FREEZE_CAR_POSITION carCopBike FALSE
//		ENDIF
//	ENDIF
//	// END TEMP


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Quarry_Next_Stage
	ENDIF

RETURN


// ****************************************
// ROCKFALL: Clear Paths

Quarry_Rockfall_Clear_Paths:

	// Check if the dozer is still alive
	IF IS_CAR_DEAD carDozer
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_DOZER_DESTROYED
		RETURN
	ENDIF


   	// Initialisation
	// --------------

   	IF m_goals = 0
		nRockfallRocks = 0

		// Clear the flags for all rocks
		REPEAT QUARRY_ROCKFALL_MAX_ROCKS nLoop
			flagRockfallRocksPushed[nLoop] = FALSE
			flagRockfallCheckpointsOnDisplay[nLoop] = FALSE
		ENDREPEAT

		// Add blip for dozer
		ADD_BLIP_FOR_CAR carDozer blipDozer
		SET_BLIP_AS_FRIENDLY blipDozer TRUE

		// INFO: push rocks off edge
		PRINT_NOW QUAR_11 10000 1

		// Display the 'delivery' time
//		g_Quarry_timerKM = QUARRY_ROCKFALL_MISSION_TIME_sec * 1000
		nTempInt = QUARRY_MISSION_ROCKFALL - 1
		g_Quarry_timerKM = g_Quarry_recordsKM[nTempInt] * 1000
		DISPLAY_ONSCREEN_TIMER_WITH_STRING g_Quarry_timerKM TIMER_DOWN QUAR_D2
		nMissionStartTime = m_mission_timer

//		nAddHours = QUARRY_ROCKFALL_HOURS
//		nAddMinutes = QUARRY_ROCKFALL_MINUTES
//		timerMissionFail = 0
//		GOSUB Quarry_Calculate_Future_Time
//		DISPLAY_ONSCREEN_TIMER_WITH_STRING g_Quarry_timerKM TIMER_UP QUAR_D2
//		FREEZE_ONSCREEN_TIMER TRUE

		flagDozerTextDisplayed = FALSE

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------

	// Time ran out
//	IF timerMissionFail < m_mission_timer
//		m_failed = TRUE
//		m_fail_reason = QUARRY_FAILED_DELIVERY_ARRIVED
//		RETURN
//	ENDIF

	// Check for Time Up
	IF g_Quarry_timerKM <= 0
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_DELIVERY_ARRIVED
		RETURN
	ENDIF


	// Subgoals
	// --------

	// Check if all rocks have been pushed into their respective areas
	IF m_goals = 1
		IF nRockfallRocks = QUARRY_ROCKFALL_MAX_ROCKS
			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	// Blip and Locate maintenance
	IF DOES_BLIP_EXIST blipDozer
		// ...the dozer blip exists
		IF IS_CHAR_IN_CAR scplayer carDozer
			// ...player is in the car, so remove the dozer blip
			REMOVE_BLIP blipDozer

			CLEAR_THIS_PRINT QUAR_35

			// ...add rocks blips
			REPEAT QUARRY_ROCKFALL_MAX_ROCKS nLoop
				IF flagRockfallRocksPushed[nLoop] = FALSE
					ADD_BLIP_FOR_OBJECT objectRocks[nLoop] blipRocks[nLoop]
				ENDIF
			ENDREPEAT
		ENDIF
	ELSE
		// ...the dozer blip doesn't exist
		IF NOT IS_CHAR_IN_CAR scplayer carDozer
			// ...the player is not in the car, so clear all rocks blips
			REPEAT QUARRY_ROCKFALL_MAX_ROCKS nLoop
				REMOVE_BLIP blipRocks[nLoop]
			ENDREPEAT

			// ...add dozer blip
			ADD_BLIP_FOR_CAR carDozer blipDozer
			SET_BLIP_AS_FRIENDLY blipDozer TRUE

			// Info: Get back in the Dozer
			PRINT_NOW QUAR_35 5000 1
		ELSE
			// ...the player is in the dozer, so check for rocks being pushed to their destination
			GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
			REPEAT QUARRY_ROCKFALL_MAX_ROCKS nLoop
				// NOTE: flagTempFlag is used to decide if this rock has been pushed
				flagTempFlag = FALSE
				IF flagRockfallRocksPushed[nLoop] = FALSE
					SWITCH nLoop
						CASE 0
							GET_OBJECT_COORDINATES objectRocks[nLoop] xposTemp2 yposTemp2 zposTemp2
							GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
							IF fTempDistance < 30.0
								// Display small highlight, then check for rock entering bigger area
//								LOCATE_OBJECT_3D objectRocks[nLoop] 762.1190 836.6474 -3.4382 14.0 14.0 5.0 TRUE
								IF flagRockfallCheckpointsOnDisplay[nLoop] = FALSE
									CREATE_CHECKPOINT CHECKPOINT_EMPTYTUBE 762.1190 836.6474 -3.4382 0.0 0.0 0.0 5.0 checkRockfalls[nLoop]
									flagRockfallCheckpointsOnDisplay[nLoop] = TRUE
								ENDIF

								IF LOCATE_OBJECT_3D objectRocks[nLoop] 762.1190 836.6474 -5.4382 15.0 35.0 5.0 FALSE
									flagTempFlag = TRUE
								ENDIF
							ELSE
								IF flagRockfallCheckpointsOnDisplay[nLoop] = TRUE
									DELETE_CHECKPOINT checkRockfalls[nLoop]
									flagRockfallCheckpointsOnDisplay[nLoop] = FALSE
								ENDIF
							ENDIF
							BREAK
						CASE 1
							GET_OBJECT_COORDINATES objectRocks[nLoop] xposTemp2 yposTemp2 zposTemp2
							GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
							IF fTempDistance < 50.0
								// Display small highlight, then check for rock entering bigger area
//								LOCATE_OBJECT_3D objectRocks[nLoop] 735.8682 933.0437 -4.3942 13.0 13.0 5.0 TRUE
								IF flagRockfallCheckpointsOnDisplay[nLoop] = FALSE
									CREATE_CHECKPOINT CHECKPOINT_EMPTYTUBE 735.8682 933.0437 -4.3942 0.0 0.0 0.0 5.0 checkRockfalls[nLoop]
									flagRockfallCheckpointsOnDisplay[nLoop] = TRUE
								ENDIF

								IF LOCATE_OBJECT_3D objectRocks[nLoop] 735.8682 933.0437 -4.3942 20.0 30.0 5.0 FALSE
									flagTempFlag = TRUE
								ENDIF
							ELSE
								IF flagRockfallCheckpointsOnDisplay[nLoop] = TRUE
									DELETE_CHECKPOINT checkRockfalls[nLoop]
									flagRockfallCheckpointsOnDisplay[nLoop] = FALSE
								ENDIF
							ENDIF
							BREAK
						CASE 2
							GET_OBJECT_COORDINATES objectRocks[nLoop] xposTemp2 yposTemp2 zposTemp2
							GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
							IF fTempDistance < 30.0
								// Display small highlight, then check for rock entering bigger area
//								LOCATE_OBJECT_3D objectRocks[nLoop] 518.9683 967.7642 -25.8910 13.0 13.0 5.0 TRUE
								IF flagRockfallCheckpointsOnDisplay[nLoop] = FALSE
									CREATE_CHECKPOINT CHECKPOINT_EMPTYTUBE 518.9683 967.7642 -25.8910 0.0 0.0 0.0 5.0 checkRockfalls[nLoop]
									flagRockfallCheckpointsOnDisplay[nLoop] = TRUE
								ENDIF

								IF LOCATE_OBJECT_3D objectRocks[nLoop] 518.9683 967.7642 -17.8910 45.0 20.0 9.0 FALSE
									flagTempFlag = TRUE
								ENDIF
							ELSE
								IF flagRockfallCheckpointsOnDisplay[nLoop] = TRUE
									DELETE_CHECKPOINT checkRockfalls[nLoop]
									flagRockfallCheckpointsOnDisplay[nLoop] = FALSE
								ENDIF
							ENDIF
							BREAK
						CASE 3
							GET_OBJECT_COORDINATES objectRocks[nLoop] xposTemp2 yposTemp2 zposTemp2
							GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
							IF fTempDistance < 30.0
								// Display small highlight, then check for rock entering bigger area
//								LOCATE_OBJECT_3D objectRocks[nLoop] 671.3267 941.1938 -29.3380 18.0 18.0 5.0 TRUE
								IF flagRockfallCheckpointsOnDisplay[nLoop] = FALSE
									CREATE_CHECKPOINT CHECKPOINT_EMPTYTUBE 671.3267 941.1938 -29.3380 0.0 0.0 0.0 5.0 checkRockfalls[nLoop]
									flagRockfallCheckpointsOnDisplay[nLoop] = TRUE
								ENDIF

								IF LOCATE_OBJECT_3D objectRocks[nLoop] 671.3267 941.1938 -29.3380 25.0 25.0 5.0 FALSE
									flagTempFlag = TRUE
								ENDIF
							ELSE
								IF flagRockfallCheckpointsOnDisplay[nLoop] = TRUE
									DELETE_CHECKPOINT checkRockfalls[nLoop]
									flagRockfallCheckpointsOnDisplay[nLoop] = FALSE
								ENDIF
							ENDIF
							BREAK
						CASE 4
							GET_OBJECT_COORDINATES objectRocks[nLoop] xposTemp2 yposTemp2 zposTemp2
							GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
							IF fTempDistance < 30.0
								// Display small highlight, then check for rock entering bigger area
//								LOCATE_OBJECT_3D objectRocks[nLoop] 523.2235 907.1995 -43.4101 14.0 14.0 5.0 TRUE
								IF flagRockfallCheckpointsOnDisplay[nLoop] = FALSE
									CREATE_CHECKPOINT CHECKPOINT_EMPTYTUBE 523.2235 907.1995 -43.4101 0.0 0.0 0.0 5.0 checkRockfalls[nLoop]
									flagRockfallCheckpointsOnDisplay[nLoop] = TRUE
								ENDIF

								IF LOCATE_OBJECT_3D objectRocks[nLoop] 523.2235 907.1995 -40.4101 35.0 25.0 5.0 FALSE
									flagTempFlag = TRUE
								ENDIF
							ELSE
								IF flagRockfallCheckpointsOnDisplay[nLoop] = TRUE
									DELETE_CHECKPOINT checkRockfalls[nLoop]
									flagRockfallCheckpointsOnDisplay[nLoop] = FALSE
								ENDIF
							ENDIF
							BREAK
						CASE 5
							GET_OBJECT_COORDINATES objectRocks[nLoop] xposTemp2 yposTemp2 zposTemp2
							GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
							IF fTempDistance < 30.0
								// Display small highlight, then check for rock entering bigger area
//								LOCATE_OBJECT_3D objectRocks[nLoop] 536.8022 838.0778 -38.2296 10.0 10.0 5.0 TRUE
								IF flagRockfallCheckpointsOnDisplay[nLoop] = FALSE
									CREATE_CHECKPOINT CHECKPOINT_EMPTYTUBE 536.8022 838.0778 -38.2296 0.0 0.0 0.0 5.0 checkRockfalls[nLoop]
									flagRockfallCheckpointsOnDisplay[nLoop] = TRUE
								ENDIF

								IF LOCATE_OBJECT_3D objectRocks[nLoop] 536.8022 838.0778 -38.2296 25.0 15.0 5.0 FALSE
									flagTempFlag = TRUE
								ENDIF
							ELSE
								IF flagRockfallCheckpointsOnDisplay[nLoop] = TRUE
									DELETE_CHECKPOINT checkRockfalls[nLoop]
									flagRockfallCheckpointsOnDisplay[nLoop] = FALSE
								ENDIF
							ENDIF
							BREAK
						CASE 6
							GET_OBJECT_COORDINATES objectRocks[nLoop] xposTemp2 yposTemp2 zposTemp2
							GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
							IF fTempDistance < 50.0
								// Display small highlight, then check for rock entering bigger area
//								LOCATE_OBJECT_3D objectRocks[nLoop] 645.2571 773.7028 -28.2457 17.0 17.0 5.0 TRUE
								IF flagRockfallCheckpointsOnDisplay[nLoop] = FALSE
									CREATE_CHECKPOINT CHECKPOINT_EMPTYTUBE 645.2571 773.7028 -28.2457 0.0 0.0 0.0 5.0 checkRockfalls[nLoop]
									flagRockfallCheckpointsOnDisplay[nLoop] = TRUE
								ENDIF

								IF LOCATE_OBJECT_3D objectRocks[nLoop] 645.2571 773.7028 -26.2457 35.0 20.0 7.0 FALSE
									flagTempFlag = TRUE
								ENDIF
							ELSE
								IF flagRockfallCheckpointsOnDisplay[nLoop] = TRUE
									DELETE_CHECKPOINT checkRockfalls[nLoop]
									flagRockfallCheckpointsOnDisplay[nLoop] = FALSE
								ENDIF
							ENDIF
							BREAK
						DEFAULT
							WRITE_DEBUG Too_Many_Rocks
					ENDSWITCH

					// Has this rock just been pushed?
					IF flagTempFlag = TRUE
						REMOVE_BLIP blipRocks[nLoop]
						DELETE_OBJECT objectRocks[nLoop]
						IF flagRockfallCheckpointsOnDisplay[nLoop] = TRUE
							DELETE_CHECKPOINT checkRockfalls[nLoop]
							flagRockfallCheckpointsOnDisplay[nLoop] = FALSE
						ENDIF
						flagRockfallRocksPushed[nLoop] = TRUE
						nRockfallRocks++
						// INFO: rocks left
						nTempInt = QUARRY_ROCKFALL_MAX_ROCKS - nRockfallRocks
						IF NOT nTempInt = 0
							IF nRockfallRocks = 1
								PRINT_WITH_2_NUMBERS_NOW QUAR_14 nRockfallRocks nTempInt 5000 1
							ELSE
								PRINT_WITH_2_NUMBERS_NOW QUAR_13 nRockfallRocks nTempInt 5000 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDREPEAT
		ENDIF
	ENDIF

	// Dozer Text
	IF flagDozerTextDisplayed = FALSE
		IF IS_CHAR_IN_CAR scplayer carDozer
			// INFO: push rocks
			PRINT_NOW QUAR_12 7000 1
			flagDozerTextDisplayed = TRUE
		ENDIF
	ENDIF


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Quarry_Next_Stage
	ENDIF

RETURN


// ****************************************
// SABOTAGE: Move Bombs

Quarry_Sabotage_Move_Bombs:

	// Check if the dozer is still alive
	IF IS_CAR_DEAD carDozer
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_DOZER_DESTROYED
		RETURN
	ENDIF


   	// Initialisation
	// --------------

   	IF m_goals = 0
		nCurrentBomb = 0

		// Clear the flags for all bombs
		REPEAT QUARRY_SABOTAGE_MAX_BOMBS nLoop
			flagBombExploded[nLoop] = FALSE
			flagBombsSecondExplosion[nLoop] = FALSE
			timerBombsSecondExplosion[nLoop] = 0
		ENDREPEAT

		// Add blip for dozer
		ADD_BLIP_FOR_CAR carDozer blipDozer
		SET_BLIP_AS_FRIENDLY blipDozer TRUE

		// INFO: push bombs
		PRINT_NOW QUAR_15 10000 1

		// Display the first timer
		g_Quarry_timerKM = timerBombsExplode[0]
		DISPLAY_ONSCREEN_TIMER_WITH_STRING g_Quarry_timerKM TIMER_DOWN QUAR_D3
		nMissionStartTime = m_mission_timer

		// Start all bomb timers
		REPEAT QUARRY_SABOTAGE_MAX_BOMBS nLoop
			timerBombsExplode[nLoop] += m_mission_timer
		ENDREPEAT

		flagBombDisplayed = FALSE
		flagAllBombsMoved = FALSE
		flagAlternativeExplosionLocation = FALSE
		flagDozerTextDisplayed = FALSE

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------


	// Subgoals
	// --------

	// Have all bombs been safely moved?
	IF m_goals = 1
		IF flagAllBombsMoved = TRUE
			GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
			nTempInt = QUARRY_SABOTAGE_MAX_BOMBS - 1
			GET_OBJECT_COORDINATES objectBombs[nTempInt] xposTemp2 yposTemp2 zposTemp2
			GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
			IF fTempDistance > QUARRY_SABOTAGE_PLAYER_IN_EXPLOSION_RANGE_m

				ADD_EXPLOSION xposTemp2 yposTemp2 zposTemp2 EXPLOSION_HELI
				DELETE_OBJECT objectBombs[nTempInt]
				flagBombExploded[nTempInt] = TRUE

				m_goals = 99
			ENDIF
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	// Blip maintenance
	IF DOES_BLIP_EXIST blipDozer
		// ...dozer blip exists
		IF IS_CHAR_IN_CAR scplayer carDozer
			// ...player is in the dozer so remove the dozer blip and allow the bomb blip to be displayed again
			REMOVE_BLIP blipDozer

			CLEAR_THIS_PRINT QUAR_35
		ENDIF
	ELSE
		// ...dozer blip does not exist
		IF NOT IS_CHAR_IN_CAR scplayer carDozer
			// ...player is not in the dozer, so remove the bomb blip and add the dozer blip
			REMOVE_BLIP blipBomb
			REMOVE_BLIP blipDestination
			flagBombDisplayed = FALSE
			ADD_BLIP_FOR_CAR carDozer blipDozer
			SET_BLIP_AS_FRIENDLY blipDozer TRUE

			// Info: Get back in the Dozer
			PRINT_NOW QUAR_35 5000 1
		ENDIF
	ENDIF


	// Bomb Sequence Controls
	IF flagAllBombsMoved = FALSE
		// ...bomb safety locates
		// NOTE: flagTempFlag used to determine if it is time to move on to the next bomb or not
		flagTempFlag = FALSE
		SWITCH nCurrentBomb
			CASE 0
				// Check for bomb entering area
				IF LOCATE_OBJECT_3D objectBombs[nCurrentBomb] 830.4274 889.8520 12.3516 4.0 4.0 1.0 TRUE
					flagTempFlag = TRUE
				ENDIF
				BREAK
			CASE 1
				// Check for bomb entering area
				IF flagAlternativeExplosionLocation = FALSE
					// ...display normal destination
					IF LOCATE_OBJECT_3D objectBombs[nCurrentBomb] 678.5786 981.4282 -13.7490 4.0 4.0 1.0 TRUE
						flagTempFlag = TRUE
					ENDIF

					// Check if the bomb has fallen off the edge
					GET_OBJECT_COORDINATES objectBombs[nCurrentBomb] xposTemp yposTemp zposTemp
					IF zposTemp < -23.6787
						flagAlternativeExplosionLocation = TRUE
						REMOVE_BLIP blipBomb
						REMOVE_BLIP blipDestination
						flagBombDisplayed = FALSE
						// INFO: alternative site
						PRINT_NOW QUAR_21 7000 1
					ENDIF
				ELSE
					// ...display alternative destination
					IF LOCATE_OBJECT_3D objectBombs[nCurrentBomb] 660.6660 913.7190 -41.4059 4.0 4.0 1.0 TRUE
						flagTempFlag = TRUE
					ENDIF
				ENDIF
				BREAK
			CASE 2
				// Check for bomb entering area
				IF LOCATE_OBJECT_3D objectBombs[nCurrentBomb] 648.9208 916.2570 -42.2287 4.0 4.0 1.0 TRUE
					flagTempFlag = TRUE
				ENDIF
				BREAK
			CASE 3
				// Check for bomb entering area
				IF LOCATE_OBJECT_3D objectBombs[nCurrentBomb] 632.4524 810.3196 -43.9609 4.0 4.0 1.0 TRUE
					flagTempFlag = TRUE
				ENDIF
				BREAK
			DEFAULT
				WRITE_DEBUG Unknown_bomb_ID
		ENDSWITCH

		// ...update this bomb because it has been safely moved
		IF flagTempFlag = TRUE
			REMOVE_BLIP blipBomb
			REMOVE_BLIP blipDestination
			flagBombDisplayed = FALSE
			flagAlternativeExplosionLocation = FALSE

			// Freeze the object and remove it's collision
			FREEZE_OBJECT_POSITION objectBombs[nCurrentBomb] TRUE
			SET_OBJECT_COLLISION objectBombs[nCurrentBomb] FALSE

			// If this was the last bomb, then make it explode soonish
			nTempInt = nCurrentBomb + 1
			IF nTempInt = QUARRY_SABOTAGE_MAX_BOMBS
				// Give the player 15 seconds to move away
				nTempInt2 = m_mission_timer + 15000
				IF timerBombsExplode[nCurrentBomb] > nTempInt2
					timerBombsExplode[nCurrentBomb] = nTempInt2
				ENDIF
			ENDIF

			nCurrentBomb++
		ENDIF

		// ...update checking for completion
		IF nCurrentBomb = QUARRY_SABOTAGE_MAX_BOMBS
			flagAllBombsMoved = TRUE
		ENDIF

		// ...update timer
		IF flagTempFlag = TRUE
			// Remove timer
			CLEAR_ONSCREEN_TIMER g_Quarry_timerKM

			IF flagAllBombsMoved = FALSE
				// update timer for next bomb
				g_Quarry_timerKM = timerBombsExplode[nCurrentBomb] - m_mission_timer
				DISPLAY_ONSCREEN_TIMER_WITH_STRING g_Quarry_timerKM TIMER_DOWN QUAR_D3
			ENDIF
		ENDIF

		// ...next text
		IF flagTempFlag = TRUE
			IF flagAllBombsMoved = TRUE
				PRINT_NOW QUAR_20 7000 1
			ELSE
				SWITCH nCurrentBomb
					CASE 0
						// NOTE: text displayed earlier
						BREAK
					CASE 1
						// INFO: crane bomb
						PRINT_NOW QUAR_17 7000 1
						BREAK
					CASE 2
						// INFO: grinder bomb
						PRINT_NOW QUAR_18 7000 1
						BREAK
					CASE 3
						// INFO: crushers bomb
						PRINT_NOW QUAR_34 7000 1
						BREAK
				ENDSWITCH
			ENDIF
		ENDIF

		// ...bomb blips
		IF flagBombDisplayed = FALSE
		AND flagAllBombsMoved = FALSE
		AND NOT DOES_BLIP_EXIST blipDozer
			// ...display blip for barrel
			ADD_BLIP_FOR_OBJECT objectBombs[nCurrentBomb] blipBomb
			flagBombDisplayed = TRUE

			// ...display blip for Destination
			SWITCH nCurrentBomb
				CASE 0
					ADD_BLIP_FOR_COORD 830.4274 889.8520 12.3516 blipDestination
					BREAK
				CASE 1
					IF flagAlternativeExplosionLocation = FALSE
						// ...normal destination
						ADD_BLIP_FOR_COORD 678.5786 981.4282 -13.7490 blipDestination
					ELSE
						// ...alternative destination
						ADD_BLIP_FOR_COORD 660.6660 913.7190 -41.4059 blipDestination
					ENDIF
					BREAK
				CASE 2
					ADD_BLIP_FOR_COORD 648.9208 916.2570 -42.2287 blipDestination
					BREAK
				CASE 3
					ADD_BLIP_FOR_COORD 632.4524 810.3196 -43.9609 blipDestination
					BREAK
			ENDSWITCH
		ENDIF
	ENDIF


	// Explode bombs when their time is up (or when player is a safe distance away)
	nLoop = 0
	nTempInt = nCurrentBomb
	IF flagAllBombsMoved = TRUE
		nTempInt--
	ENDIF

	WHILE nLoop <= nTempInt
		// Is it time for this bomb to explode?
		IF flagBombExploded[nLoop] = FALSE
			// If the player and dozer are safe distances away from the bombs then explode them immediately for effect
			GET_OBJECT_COORDINATES objectBombs[nLoop] xposTemp yposTemp zposTemp
			GET_CAR_COORDINATES carDozer xposTemp2 yposTemp2 zposTemp2
			GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
			GET_CHAR_COORDINATES scplayer xposTemp3 yposTemp3 zposTemp3
			GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp3 yposTemp3 zposTemp3 fTempDistance2

			// Only previous bombs should explode when out of blast radius, definitely not the current bomb
			IF NOT nLoop = nCurrentBomb
				IF fTempDistance > QUARRY_SABOTAGE_THEATRICAL_EXPLOSION_RANGE_m
				AND fTempDistance2 > QUARRY_SABOTAGE_THEATRICAL_EXPLOSION_RANGE_m
					// Set the mission timer so that the bombs now explode
					timerBombsExplode[nLoop] = m_mission_timer - 1
				ENDIF
			ENDIF

			// Check if it is time to explode this bomb
			IF timerBombsExplode[nLoop] < m_mission_timer
				ADD_EXPLOSION xposTemp yposTemp zposTemp EXPLOSION_TANK_GRENADE
				DELETE_OBJECT objectBombs[nLoop]
				flagBombExploded[nLoop] = TRUE
				flagBombsSecondExplosion[nLoop] = FALSE
				timerBombsSecondExplosion[nLoop] = m_mission_timer + 2000
				xposBombs[nLoop] = xposTemp
				yposBombs[nLoop] = yposTemp
				zposBombs[nLoop] = zposTemp

				// Check if the dozer is within range
				IF fTempDistance < QUARRY_SABOTAGE_PLAYER_IN_EXPLOSION_RANGE_m
					// Dozer within range, so make it explode
					EXPLODE_CAR carDozer
				ENDIF

				// Check if the player is within range
				IF fTempDistance2 < QUARRY_SABOTAGE_PLAYER_IN_EXPLOSION_RANGE_m
					// Player within range
					m_failed = TRUE
					m_fail_reason = QUARRY_FAILED_TOO_CLOSE_TO_EXPLOSION
					RETURN
				ENDIF

				// If this was the current bomb then fail the mission
				IF nLoop = nCurrentBomb
					m_failed = TRUE
					m_fail_reason = QUARRY_FAILED_BOMB_EXPLODED
					RETURN
				ENDIF
			ENDIF
		ELSE
			IF flagBombsSecondExplosion[nLoop] = FALSE
				IF timerBombsSecondExplosion[nLoop] < m_mission_timer
					ADD_EXPLOSION xposBombs[nLoop] yposBombs[nLoop] zposBombs[nLoop] EXPLOSION_OBJECT
					flagBombsSecondExplosion[nLoop] = TRUE
				ENDIF
			ENDIF
		ENDIF

		nLoop++
	ENDWHILE


	// Dozer Text
	IF flagDozerTextDisplayed = FALSE
		IF IS_CHAR_IN_CAR scplayer carDozer
			// INFO: site office bomb
			PRINT_NOW QUAR_16 7000 1
			flagDozerTextDisplayed = TRUE
		ENDIF
	ENDIF


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Quarry_Next_Stage
	ENDIF

RETURN


// ****************************************
// REMOVAL: Reach Dumper

Quarry_Removal_Reach_Dumper:

	// Check if the dumper is still alive
	IF IS_CAR_DEAD carDumper
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_DUMPER_DESTROYED
		RETURN
	ENDIF


   	// Initialisation
	// --------------

   	IF m_goals = 0
		// Add blip for dumper
		ADD_BLIP_FOR_CAR carDumper blipDumper
		SET_BLIP_AS_FRIENDLY blipDumper TRUE

		// Display blip at other entrance
		GOSUB Quarry_Display_Other_Entrance_Blip

		// INFO: get to the dumper
		PRINT_NOW QUAR_22 10000 1

		flagReadyToDump = FALSE

		// Display the 'deadline' time
//		g_Quarry_timerKM = QUARRY_REMOVAL_MISSION_TIME_sec * 1000
		nTempInt = QUARRY_MISSION_REMOVAL - 1
		g_Quarry_timerKM = g_Quarry_recordsKM[nTempInt] * 1000
		DISPLAY_ONSCREEN_TIMER_WITH_STRING g_Quarry_timerKM TIMER_DOWN QUAR_D1
		nMissionStartTime = m_mission_timer

//		nAddHours = QUARRY_REMOVAL_HOURS
//		nAddMinutes = QUARRY_REMOVAL_MINUTES
//		timerMissionFail = 0
//		GOSUB Quarry_Calculate_Future_Time
//		DISPLAY_ONSCREEN_TIMER_WITH_STRING g_Quarry_timerKM TIMER_UP QUAR_D1
//		FREEZE_ONSCREEN_TIMER TRUE

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------

	// Check time limit
//	IF timerMissionFail < m_mission_timer
//		m_failed = TRUE
//		m_fail_reason = QUARRY_FAILED_COPS_ARRIVED
//		RETURN
//	ENDIF

	// Check for Time Up
	IF g_Quarry_timerKM <= 0
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_COPS_ARRIVED
		RETURN
	ENDIF


	// Subgoals
	// --------

	// Has player reached the Dumper?
	IF m_goals = 1
		IF flagReadyToDump = TRUE
			// Remove blip at other entrance
			REMOVE_BLIP blipOtherEntrance
			flagDisplayOtherEntranceBlip = FALSE

			REMOVE_BLIP blipDumper

			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	// Check if player in dumper
	IF flagReadyToDump = FALSE
		IF IS_CHAR_IN_CAR scplayer carDumper
			flagReadyToDump = TRUE
		ENDIF
	ENDIF


	// Check if player reached other entrance
	IF flagDisplayOtherEntranceBlip = TRUE
		// Display a small highlight at the entrance
		LOCATE_CHAR_ANY_MEANS_3D scplayer xposEntrance yposEntrance zposEntrance 4.0 4.0 4.0 TRUE
		// Check for arrival at the entrance
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer xposEntrance yposEntrance zposEntrance 15.0 10.0 4.0 FALSE
			REMOVE_BLIP blipOtherEntrance
			flagDisplayOtherEntranceBlip = FALSE
		ENDIF
	ENDIF


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Quarry_Next_Stage
	ENDIF

RETURN


// ****************************************
// REMOVAL: Dump Bodies

Quarry_Removal_Dump_Bodies:

	// Check if the dumper is still alive
	IF IS_CAR_DEAD carDumper
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_DUMPER_DESTROYED
		RETURN
	ENDIF


   	// Initialisation
	// --------------

   	IF m_goals = 0
		// Set up destination (fires)
		xposDestination = 469.3770
		yposDestination = 877.2751
		zposDestination = -29.0362
//		ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination
		
		// Add blip at Quarry entrance
		GOSUB Quarry_Display_Entrance_Blip

		// Set up fires at destination
		REPEAT QUARRY_REMOVAL_MAX_FIRES nLoop
			// NOTE: nTempInt used to randomly choose size of fire
			GENERATE_RANDOM_INT_IN_RANGE 2 4 nTempInt
			GENERATE_RANDOM_FLOAT_IN_RANGE -2.0 2.0 xposTemp
			GENERATE_RANDOM_FLOAT_IN_RANGE -3.0 3.0 yposTemp
			xposTemp += xposDestination
			yposTemp += yposDestination
			GET_GROUND_Z_FOR_3D_COORD xposTemp yposTemp -20.0 zposTemp
			START_SCRIPT_FIRE xposTemp yposTemp zposTemp 0 nTempInt fireFires[nLoop]
		ENDREPEAT

		// Set up Fire Fuel at Destination
		REPEAT QUARRY_REMOVAL_MAX_FIRE_FUEL nLoop
			// NOTE: nTempInt used to randomly choose the object
			GENERATE_RANDOM_INT_IN_RANGE 0 4 nTempInt
			// NOTE: xposTemp, yposTemp, and zposTemp used to generate random position
			GENERATE_RANDOM_FLOAT_IN_RANGE -1.3 1.3 xposTemp
			GENERATE_RANDOM_FLOAT_IN_RANGE -1.8 1.8 yposTemp
			xposTemp += xposDestination
			yposTemp += yposDestination
			GET_GROUND_Z_FOR_3D_COORD xposTemp yposTemp -20.0 zposTemp

			SWITCH nTempInt
				CASE 0
					CREATE_OBJECT CM_BOX xposTemp yposTemp zposTemp objectFireFuel[nTempInt]
					BREAK
				CASE 1
					CREATE_OBJECT BLACKBAG2 xposTemp yposTemp zposTemp objectFireFuel[nTempInt]
					BREAK
				CASE 2
					CREATE_OBJECT DYN_WOODPILE xposTemp yposTemp zposTemp objectFireFuel[nTempInt]
					BREAK
				CASE 3
					CREATE_OBJECT DYN_WOODPILE2 xposTemp yposTemp zposTemp objectFireFuel[nTempInt]
					BREAK
			ENDSWITCH

			// Rotation
			// NOTE: fTempFloat, fTempFloat2, fTempFloat3 used to get random orientations
			GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 359.0 fTempFloat
			GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 359.0 fTempFloat2
			GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 359.0 fTempFloat3
			SET_OBJECT_ROTATION objectFireFuel[nTempInt] fTempFloat fTempFloat2 fTempFloat3

			// Other stuff
			SET_OBJECT_RENDER_SCORCHED objectFireFuel[nTempInt] TRUE
			SET_OBJECT_COLLISION objectFireFuel[nTempInt] FALSE
		ENDREPEAT

		// INFO: dump the bodies
		PRINT_NOW QUAR_23 7000 1

		flagDisplayedTracksText = FALSE

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------
	// Check for the bodies being on the ground
	// NOTE: nTempInt is used to check the number of bodies on the ground in the correct place
	IF flagDumped = FALSE
		nTempInt = 0
		REPEAT QUARRY_REMOVAL_MAX_BODIES nLoop
			IF NOT IS_OBJECT_ATTACHED objectBodies[nLoop]
				GET_OBJECT_COORDINATES objectBodies[nLoop] xposTemp yposTemp zposTemp
				GET_GROUND_Z_FOR_3D_COORD xposTemp yposTemp zposTemp fTempFloat
				fTempFloat2 = zposTemp - fTempFloat
				IF fTempFloat2 < 0.5
					// ...body on ground
					// Check if it is in the correct place
					IF LOCATE_OBJECT_3D objectBodies[nLoop] xposDestination yposDestination zposDestination 12.0 15.0 3.0 FALSE
						nTempInt2 = QUARRY_REMOVAL_MAX_FIRES + nLoop
						IF NOT DOES_SCRIPT_FIRE_EXIST fireFires[nTempInt2]
							START_SCRIPT_FIRE xposTemp yposTemp zposTemp 0 3 fireFires[nTempInt2]
							SET_OBJECT_RENDER_SCORCHED objectBodies[nLoop] TRUE
							FREEZE_OBJECT_POSITION objectBodies[nLoop] TRUE
							SET_OBJECT_COLLISION objectBodies[nLoop] FALSE
						ENDIF

						nTempInt++
					ELSE
						m_failed = TRUE
						m_fail_reason = QUARRY_FAILED_BODY_DUMPED_IN_WRONG_PLACE
						RETURN
					ENDIF
				ENDIF
			ENDIF
		ENDREPEAT

		// Check for success
		IF nTempInt = QUARRY_REMOVAL_MAX_BODIES
			flagDumped = TRUE
		ENDIF
	ENDIF


	// Check for Time Up
	IF g_Quarry_timerKM <= 0
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_COPS_ARRIVED
		RETURN
	ENDIF


	// Subgoals
	// --------

	// Check if the bodies have been dumped
	IF m_goals = 1
		IF flagDumped = TRUE
			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	// Blip Maintenance
	IF IS_CHAR_IN_CAR scplayer carDumper
		// ...player is in the dumper, so make sure that the destination blip is on display
		IF DOES_BLIP_EXIST blipDumper
			REMOVE_BLIP blipDumper

			CLEAR_THIS_PRINT QUAR_36

			IF flagDisplayEntranceBlip = TRUE
				ADD_BLIP_FOR_COORD xposEntrance yposEntrance zposEntrance blipDestination
			ELSE
				ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination
			ENDIF
		ENDIF
	ELSE
		// ...player is not in the dumper, so display the dumper blip
		IF NOT DOES_BLIP_EXIST blipDumper
			REMOVE_BLIP blipDestination

			ADD_BLIP_FOR_CAR carDumper blipDumper
			SET_BLIP_AS_FRIENDLY blipDumper TRUE

			// Info: Get back in the Dumper
			PRINT_NOW QUAR_36 5000 1
		ENDIF
	ENDIF

	// Display a locate until closer to the fire
	IF flagDumped = FALSE
		IF IS_CHAR_IN_CAR scplayer carDumper
			IF NOT LOCATE_CHAR_IN_CAR_3D scplayer xposDestination yposDestination zposDestination 25.0 25.0 20.0 FALSE
				// Display highlight
				LOCATE_CHAR_IN_CAR_3D scplayer xposDestination yposDestination zposDestination 4.0 4.0 4.0 TRUE
			ELSE
				// Display Help text
				IF flagDumperHelpDisplayed = FALSE
					PRINT_HELP_FOREVER QUAR_H1  
					flagDumperHelpDisplayed = TRUE
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	
	// Check for player at entrance
	IF flagDisplayedTracksText = FALSE
		IF IS_CHAR_IN_CAR scplayer carDumper
			IF LOCATE_CHAR_IN_CAR_3D scplayer xposEntrance yposEntrance zposEntrance 12.0 12.0 12.0 TRUE
				PRINT_NOW QUAR_24 7000 1
				flagDisplayedTracksText = TRUE

				// Remove entrance blip
				REMOVE_BLIP blipEntrance
				flagDisplayEntranceBlip = FALSE

				// Add blip at fire
				ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination
			ENDIF
		ENDIF
	ENDIF


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Quarry_Next_Stage
	ENDIF

RETURN


// ****************************************
// DELIVERY: Deliver

Quarry_Delivery_Deliver:

	// Check if the dumper is still alive
	IF IS_CAR_DEAD carDumper
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_DUMPER_DESTROYED
		RETURN
	ENDIF


   	// Initialisation
	// --------------

   	IF m_goals = 0
		// Set up the Destination
		xposDestination = -59.3126
		yposDestination = 2502.2544
		zposDestination = 15.4766

		// Store the Dumper's initial health
		GET_CAR_HEALTH carDumper nPreviousHealth

		// Dumper blip
		ADD_BLIP_FOR_CAR carDumper blipDumper
		SET_BLIP_AS_FRIENDLY blipDumper TRUE

		// Display the 'deadline' time
//		g_Quarry_timerKM = QUARRY_DELIVERY_MISSION_TIME_sec * 1000
		nTempInt = QUARRY_MISSION_DELIVERY - 1
		g_Quarry_timerKM = g_Quarry_recordsKM[nTempInt] * 1000
		DISPLAY_ONSCREEN_TIMER_WITH_STRING g_Quarry_timerKM TIMER_DOWN QUAR_D4
		nMissionStartTime = m_mission_timer

//		nAddHours = QUARRY_DELIVERY_HOURS
//		nAddMinutes = QUARRY_DELIVERY_MINUTES
//		timerMissionFail = 0
//		GOSUB Quarry_Calculate_Future_Time
//		DISPLAY_ONSCREEN_TIMER_WITH_STRING g_Quarry_timerKM TIMER_UP QUAR_D4
//		FREEZE_ONSCREEN_TIMER TRUE

		// INFO: Deliver the explosives
//		PRINT_NOW QUAR_25 10000 1
		PRINT_NOW QUAR_38 10000 1

		flagDumped = FALSE
		flagDisplayedDumpText = FALSE

		REPEAT QUARRY_DELIVERY_MAX_BOMBS nLoop
			flagBombStuck[nLoop] = FALSE
			timerBombStuck[nLoop] = 0
		ENDREPEAT

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------
	// Check for the barrels being on the ground
	// NOTE: nTempInt is used to check the number of barrels on the ground in the correct place
	IF flagDumped = FALSE
		flagTempFlag = FALSE
		nTempInt = 0
		REPEAT QUARRY_DELIVERY_MAX_BOMBS nLoop
			IF NOT IS_OBJECT_ATTACHED objectBombs[nLoop]
				GET_OBJECT_COORDINATES objectBombs[nLoop] xposTemp yposTemp zposTemp
				GET_GROUND_Z_FOR_3D_COORD xposTemp yposTemp zposTemp fTempFloat
				fTempFloat2 = zposTemp - fTempFloat
				IF fTempFloat2 < 1.0
					// ...bomb on ground
					// Check if it is in the correct place
					IF LOCATE_OBJECT_3D objectBombs[nLoop] xposDestination yposDestination zposDestination 12.0 15.0 3.0 FALSE
						// ...bomb dropped in correct place
						nTempInt++
					ELSE
						// ..wrong place, so explode it
						ADD_EXPLOSION xposTemp yposTemp zposTemp EXPLOSION_ROCKET
						DELETE_OBJECT objectBombs[nLoop]
						flagTempFlag = TRUE
					ENDIF
				ENDIF
			ENDIF
		ENDREPEAT

		// Check for failure
		IF flagTempFlag = TRUE
			// Explode all bombs
			REPEAT QUARRY_DELIVERY_MAX_BOMBS nLoop
				IF DOES_OBJECT_EXIST objectBombs[nLoop]
					ADD_EXPLOSION xposTemp yposTemp zposTemp EXPLOSION_ROCKET
					DELETE_OBJECT objectBombs[nLoop]
				ENDIF
			ENDREPEAT
			
			// Explode the dumper
			EXPLODE_CAR carDumper
			m_failed = TRUE
			m_fail_reason = QUARRY_FAILED_TOO_MUCH_DAMAGE
			RETURN
		ENDIF

		// Check for success
		IF nTempInt = QUARRY_DELIVERY_MAX_BOMBS
			flagDumped = TRUE
		ENDIF
	ENDIF


	// Check for the dumper taking too much damage
	IF flagDumped = FALSE
		GET_CAR_HEALTH carDumper nTempInt
		nTempInt2 = nPreviousHealth - nTempInt
		IF nTempInt2 > QUARRY_DELIVERY_HEALTH_THRESHOLD
			// ...taken too much damage, so explode all barrels
			REPEAT QUARRY_DELIVERY_MAX_BOMBS nLoop
				IF DOES_OBJECT_EXIST objectBombs[nLoop]
					GET_OBJECT_COORDINATES objectBombs[nLoop] xposTemp yposTemp zposTemp
					ADD_EXPLOSION xposTemp yposTemp zposTemp EXPLOSION_ROCKET
					DELETE_OBJECT objectBombs[nLoop]
				ENDIF
			ENDREPEAT

			// Explode Dumper
			EXPLODE_CAR carDumper
			m_failed = TRUE
			m_fail_reason = QUARRY_FAILED_TOO_MUCH_DAMAGE
			RETURN
		ENDIF

		nPreviousHealth = nTempInt
	ENDIF


	// Check time limit
//	IF timerMissionFail < m_mission_timer
//		m_failed = TRUE
//		m_fail_reason = QUARRY_FAILED_MISSED_DEADLINE
//		RETURN
//	ENDIF

	// Check for Time Up
	IF g_Quarry_timerKM <= 0
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_MISSED_DEADLINE
		RETURN
	ENDIF



	// Subgoals
	// --------

	// Check for the goods being dumped
	IF m_goals = 1
		IF flagDumped = TRUE
			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	// Make sure the Helper objects stay in place
	REPEAT QUARRY_MAX_HELPERS nLoop
		IF DOES_OBJECT_EXIST objectHelpers[nLoop]
			IF NOT IS_OBJECT_ATTACHED objectHelpers[nLoop]
				SWITCH nLoop
					CASE 0
						ATTACH_OBJECT_TO_CAR objectHelpers[nLoop] carDumper -1.0 -5.0 0.3 0.0 0.0 0.0
						BREAK
					CASE 1
						ATTACH_OBJECT_TO_CAR objectHelpers[nLoop] carDumper 0.0 -5.0 0.3 0.0 0.0 0.0
						BREAK
					CASE 2
						ATTACH_OBJECT_TO_CAR objectHelpers[nLoop] carDumper 1.0 -5.0 0.3 0.0 0.0 0.0
						BREAK
				ENDSWITCH
			ENDIF
		ENDIF
	ENDREPEAT


	// Blip maintenance
	IF IS_CHAR_IN_CAR scplayer carDumper
		// ...player is in the dumper, so display destination blip
		IF NOT DOES_BLIP_EXIST blipDestination
			// ...remove the dumper blip
			REMOVE_BLIP blipDumper
			// ...add the destination blip
			ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination

			CLEAR_THIS_PRINT QUAR_36
		ENDIF
	ELSE
		// ...player is not in the dumper, so display the dumper blip
		IF NOT DOES_BLIP_EXIST blipDumper
			// ...remove the destination blip
			REMOVE_BLIP blipDestination
			// ...add the dumper blip
			ADD_BLIP_FOR_CAR carDumper blipDumper
			SET_BLIP_AS_FRIENDLY blipDumper TRUE

			// Info: Get back in the Dumper
			PRINT_NOW QUAR_36 5000 1
		ENDIF
	ENDIF

	
	// Highlights
	IF DOES_BLIP_EXIST blipDestination
		// ...display destination highlight
		LOCATE_CHAR_ANY_MEANS_3D scplayer xposDestination yposDestination zposDestination 8.0 8.0 4.0 TRUE
	ENDIF


	// Text
	IF flagDisplayedDumpText = FALSE
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer xposDestination yposDestination zposDestination 24.0 30.0 8.0 FALSE
			// INFO: Drop yur load
			PRINT_NOW QUAR_26 7000 1
			flagDisplayedDumpText = TRUE
		ENDIF
	ENDIF


	// If the truck is at the destination and the back is raised and there are barrels attached, unattach them
	IF LOCATE_CAR_3D carDumper xposDestination yposDestination zposDestination 10.0 10.0 5.0 FALSE
		GET_CAR_MOVING_COMPONENT_OFFSET carDumper fTempFloat
		IF fTempFloat > 0.05
			REPEAT QUARRY_DELIVERY_MAX_BOMBS nLoop
				IF DOES_OBJECT_EXIST objectBombs[nLoop]
					IF IS_OBJECT_ATTACHED objectBombs[nLoop]
						DETACH_OBJECT objectBombs[nLoop] 0.0 0.0 0.0 FALSE
					ENDIF
				ENDIF
			ENDREPEAT
		ENDIF
	ENDIF


	// Check if the barrels have taken significant damage and explode them
	flagTempFlag = FALSE
	REPEAT QUARRY_DELIVERY_MAX_BOMBS nLoop
		IF DOES_OBJECT_EXIST objectBombs[nLoop]
			GET_OBJECT_HEALTH objectBombs[nLoop] nTempInt
			IF nTempInt <= 950
				flagTempFlag = TRUE
			ENDIF
		ENDIF
	ENDREPEAT

	IF flagTempFlag = TRUE
		// ...they have taken significant damage, so explode barrels
		REPEAT QUARRY_DELIVERY_MAX_BOMBS nLoop
			IF DOES_OBJECT_EXIST objectBombs[nLoop]
				GET_OBJECT_COORDINATES objectBombs[nLoop] xposTemp yposTemp zposTemp
				ADD_EXPLOSION xposTemp yposTemp zposTemp EXPLOSION_ROCKET
				DELETE_OBJECT objectBombs[nLoop]
			ENDIF
		ENDREPEAT

		// ...explode dumper
		EXPLODE_CAR carDumper
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_TOO_MUCH_DAMAGE
		RETURN
	ENDIF


	// Check for barrels stuck on the dumper
	IF LOCATE_CAR_3D carDumper xposDestination yposDestination zposDestination 10.0 10.0 5.0 FALSE
		GET_CAR_MOVING_COMPONENT_OFFSET carDumper fTempFloat
		IF fTempFloat > 0.25
			REPEAT QUARRY_DELIVERY_MAX_BOMBS nLoop
				IF DOES_OBJECT_EXIST objectBombs[nLoop]
					// Check if this bomb is stuck on the tailgate
					GET_OBJECT_COORDINATES objectBombs[nLoop] xposTemp yposTemp zposTemp

					flagTempFlag = FALSE
					nTempInt = 0
					WHILE nTempInt < QUARRY_MAX_HELPERS
						IF flagTempFlag = FALSE
						AND DOES_OBJECT_EXIST objectHelpers[nTempInt]
							GET_OBJECT_COORDINATES objectHelpers[nTempInt] xposTemp2 yposTemp2 zposTemp2
							GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
							IF fTempDistance < 0.75
								flagTempFlag = TRUE
							ENDIF
						ENDIF

						nTempInt++
					ENDWHILE

					IF flagTempFlag = TRUE
						// ...barrel close to a helper object
						IF flagBombStuck[nLoop] = FALSE
							// ...barrel wasn't previously close, so set up the 'is it stuck' check
							flagBombStuck[nLoop] = TRUE
							timerBombStuck[nLoop] = m_mission_timer + 5000
						ELSE
							// ...barrel was previously close, so see if the timer has ran out
							IF timerBombStuck[nLoop] < m_mission_timer
								// ...timer has ran out, so restart it, make sure the barrel is free, drop the barrel
								timerBombStuck[nLoop] = m_mission_timer + 5000
								IF IS_OBJECT_ATTACHED objectBombs[nLoop]
									DETACH_OBJECT objectBombs[nLoop] 0.0 0.0 0.0 FALSE
								ENDIF
								zposTemp += 1.2
								SET_OBJECT_COORDINATES objectBombs[nLoop] xposTemp yposTemp zposTemp
							ENDIF
						ENDIF
					ELSE
						// ...barrel not close to a helper object
						flagBombStuck[nLoop] = FALSE
						timerBombStuck[nLoop] = 0
					ENDIF
				ENDIF
			ENDREPEAT
		ENDIF
	ENDIF



	// Exit
	// ----

	IF m_goals = 99
		REMOVE_BLIP blipDestination
		REMOVE_BLIP blipDumper

		GOSUB Quarry_Next_Stage
	ENDIF

RETURN


/* The delivery will no longer feature a return journey
// ****************************************
// DELIVERY: Return

Quarry_Delivery_Return:

	// Check if the dumper is still alive
	IF IS_CAR_DEAD carDumper
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_DUMPER_DESTROYED
		RETURN
	ENDIF


   	// Initialisation
	// --------------

   	IF m_goals = 0
		// Set up the Destination
		xposDestination = 816.4832
		yposDestination = 845.8509
		zposDestination = 9.4934

		// INFO: Return to Quarry
		PRINT_NOW QUAR_27 7000 1

		flagReturned = FALSE

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------

	// Check time limit
//	IF timerMissionFail < m_mission_timer
//		m_failed = TRUE
//		m_fail_reason = QUARRY_FAILED_MISSED_DEADLINE
//		RETURN
//	ENDIF

	// Check for Time Up
	IF g_Quarry_timerKM <= 0
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_MISSED_DEADLINE
		RETURN
	ENDIF



	// Subgoals
	// --------

	// Return to Quarry
	IF m_goals = 1
		IF flagReturned = TRUE
			SET_PLAYER_CONTROL player1 OFF

			m_goals++
		ENDIF
	ENDIF


	// Wait for car to stop
	IF m_goals = 2
		IF IS_CAR_STOPPED carDumper
			SET_PLAYER_CONTROL player1 ON

			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	// Blip maintenance
	IF IS_CHAR_IN_CAR scplayer carDumper
		// ...player is in the dumper, so display destination blip
		IF NOT DOES_BLIP_EXIST blipDestination
			// ...remove the dumper blip
			REMOVE_BLIP blipDumper
			// ...add the destination blip
			ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination

			CLEAR_THIS_PRINT QUAR_36
		ENDIF
	ELSE
		// ...player is not in the dumper, so display the dumper blip
		IF NOT DOES_BLIP_EXIST blipDumper
			// ...remove the destination blip
			REMOVE_BLIP blipDestination
			// ...add the dumper blip
			ADD_BLIP_FOR_CAR carDumper blipDumper
			SET_BLIP_AS_FRIENDLY blipDumper TRUE

			// Info: Get back in the Dumper
			PRINT_NOW QUAR_36 5000 1
		ENDIF
	ENDIF


	// Player returned to Quarry
	IF flagReturned = FALSE
		IF IS_CHAR_IN_CAR scplayer carDumper
			IF LOCATE_CHAR_IN_CAR_3D scplayer xposDestination yposDestination zposDestination 4.0 4.0 4.0 TRUE
				flagReturned = TRUE
			ENDIF
		ENDIF
	ENDIF


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Quarry_Next_Stage
	ENDIF

RETURN
*/


// ****************************************
// SPILLAGE: Clear Tracks

Quarry_Spillage_Clear_Tracks:

	// Check if the dozer is still alive
	IF IS_CAR_DEAD carDozer
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_DOZER_DESTROYED
		RETURN
	ENDIF


	// Check if the train is still alive
	IF IS_CAR_DEAD carTrain
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_TRAIN_DESTROYED
		RETURN
	ENDIF

   	// Initialisation
	// --------------

   	IF m_goals = 0
		flagBombBlipsOnDisplay = FALSE

		// Add blip for Dozer
		ADD_BLIP_FOR_CAR carDozer blipDozer
		SET_BLIP_AS_FRIENDLY blipDozer TRUE

		// Set up timers
		// ...arrival time
//		g_Quarry_timerKM = QUARRY_SPILLAGE_TRAIN_TIME_sec * 1000
		nTempInt = QUARRY_MISSION_SPILLAGE - 1
		g_Quarry_timerKM = g_Quarry_recordsKM[nTempInt] * 1000
		DISPLAY_ONSCREEN_TIMER_WITH_STRING g_Quarry_timerKM TIMER_DOWN QUAR_D5
		nMissionStartTime = m_mission_timer

		// ...train activate time (arrival time - 15 secs)
//		timerTrainStart = QUARRY_SPILLAGE_TRAIN_TIME_sec - 15
		timerTrainStart = g_Quarry_recordsKM[nTempInt] - 15
		timerTrainStart *= 1000
		timerTrainStart += m_mission_timer

		flagTrainStarted = FALSE
		flagTrainPassed = FALSE

		// Set all the 'bombs on tracks' flags
		REPEAT QUARRY_SPILLAGE_MAX_BOMBS nLoop
			flagBombsOnTracks[nLoop] = TRUE
		ENDREPEAT

		// INFO: push explosive off track
		PRINT_NOW QUAR_28 10000 1
		
		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------

	// Check if the train is touching any of the barrels
	IF flagTrainStarted = TRUE
		GET_OBJECT_COORDINATES objectTrainHelper xposTemp2 yposTemp2 zposTemp2
		REPEAT QUARRY_SPILLAGE_MAX_BOMBS nLoop
			IF DOES_OBJECT_EXIST objectBombs[nLoop]
				IF flagBombsOnTracks[nLoop] = TRUE
					GET_OBJECT_COORDINATES objectBombs[nLoop] xposTemp yposTemp zposTemp
					GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
					IF fTempDistance < 2.0
						ADD_EXPLOSION xposTemp yposTemp zposTemp EXPLOSION_HELI
						DELETE_OBJECT objectBombs[nLoop]

						IF NOT IS_CAR_DEAD carTrain
							EXPLODE_CAR carTrain
							SET_TRAIN_CRUISE_SPEED carTrain 0.0

							m_failed = TRUE
							m_fail_reason = QUARRY_FAILED_EXPLOSIVES_ON_TRACK
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDREPEAT

		IF m_failed = TRUE
			RETURN
		ENDIF
	ENDIF


	// Subgoals
	// --------

	// If the train has passed or all bombs have been moved fully off the track, then pass the mission
	IF m_goals = 1
		IF flagTrainPassed = TRUE
			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	// Blip Maintenance
	IF IS_CHAR_IN_CAR scplayer carDozer
		// ...player is in dozer
		IF flagBombBlipsOnDisplay = FALSE
			// ...allow bomb blips to be displayed
			flagBombBlipsOnDisplay = TRUE

			// ...remove dozer blip
			REMOVE_BLIP blipDozer

			CLEAR_THIS_PRINT QUAR_35
		ENDIF
	ELSE
		// ...player is not in dozer
		IF flagBombBlipsOnDisplay = TRUE
			// ...bomb blips are not on display, so display them
			REPEAT QUARRY_SPILLAGE_MAX_BOMBS nLoop
				REMOVE_BLIP blipBombs[nLoop]
			ENDREPEAT

			flagBombBlipsOnDisplay = FALSE
		ENDIF

		IF NOT DOES_BLIP_EXIST blipDozer
			// ...add dozer blip
			ADD_BLIP_FOR_CAR carDozer blipDozer
			SET_BLIP_AS_FRIENDLY blipDozer TRUE

			// Info: Get back in the Dozer
			PRINT_NOW QUAR_35 5000 1
		ENDIF
	ENDIF


	// Start the train?
	IF flagTrainStarted = FALSE
// PC has a timing descrepancy, so use the on-screen timer to trigger the train
//		IF timerTrainStart < m_mission_timer
		IF g_Quarry_timerKM <= 15000
			SET_TRAIN_SPEED carTrain QUARRY_SPILLAGE_TRAIN_SPEED
			SET_TRAIN_CRUISE_SPEED carTrain QUARRY_SPILLAGE_TRAIN_SPEED
			ADD_BLIP_FOR_CAR carTrain blipTrain
			SET_BLIP_AS_FRIENDLY blipTrain TRUE

			// INFO: The train is approaching
			PRINT_NOW QUAR_29 7000 1

			flagTrainStarted = TRUE
		ENDIF
	ENDIF


	// Detect if the player is trying to head for the train's start point
	IF flagTrainStarted = FALSE
		IF IS_CHAR_IN_AREA_3D scplayer 727.6937 2412.2041 17.6685 872.5978 2714.9175 21.0511 FALSE
			SET_TRAIN_SPEED carTrain QUARRY_SPILLAGE_TRAIN_SPEED
			SET_TRAIN_CRUISE_SPEED carTrain QUARRY_SPILLAGE_TRAIN_SPEED
			ADD_BLIP_FOR_CAR carTrain blipTrain
			SET_BLIP_AS_FRIENDLY blipTrain TRUE

			CLEAR_ONSCREEN_TIMER g_Quarry_timerKM

			// INFO: The train is approaching
			PRINT_NOW QUAR_29 7000 1

			flagTrainStarted = TRUE
		ENDIF
	ENDIF


	// Has the train passed?
	IF flagTrainStarted = TRUE
	AND flagTrainPassed = FALSE
		IF LOCATE_CAR_3D carTrain 673.9813 1331.0792 10.7891 20.0 20.0 8.0 FALSE
			flagTrainPassed = TRUE
		ENDIF
	ENDIF


	// Test the barrels to see if they are still near the tracks
	IF flagBombBlipsOnDisplay = TRUE
		REPEAT QUARRY_SPILLAGE_MAX_BOMBS nLoop
			IF DOES_OBJECT_EXIST objectBombs[nLoop]
				GET_OBJECT_COORDINATES objectBombs[nLoop] xposTemp yposTemp zposTemp
				IF xposTemp > 739.0
				AND xposTemp < 747.0
					flagBombsOnTracks[nLoop] = TRUE

					// Show Blip
					IF NOT DOES_BLIP_EXIST blipBombs[nLoop]
						ADD_BLIP_FOR_OBJECT objectBombs[nLoop] blipBombs[nLoop]
					ENDIF
				ELSE
					flagBombsOnTracks[nLoop] = FALSE

					// Remove blip
					IF DOES_BLIP_EXIST blipBombs[nLoop]
						REMOVE_BLIP blipBombs[nLoop]
					ENDIF
				ENDIF
			ENDIF
		ENDREPEAT
	ENDIF


	// If all barrels are off the tracks then trigger the train if it hasn't already been triggered
	IF flagTrainStarted = FALSE
		// NOTE: nTempInt use to count barrels OFF the track
		nTempInt = 0
		REPEAT QUARRY_SPILLAGE_MAX_BOMBS nLoop
			IF DOES_OBJECT_EXIST objectBombs[nLoop]
				IF flagBombsOnTracks[nLoop] = FALSE
					nTempInt++
				ENDIF
			ELSE
				// ...object doesn't exist
				nTempInt++
			ENDIF
		ENDREPEAT


		// If all bombs are off the tracks, start the train at double speed
		IF nTempInt = QUARRY_SPILLAGE_MAX_BOMBS
			fTempFloat = QUARRY_SPILLAGE_TRAIN_SPEED * 2.0
			SET_TRAIN_SPEED carTrain fTempFloat
			SET_TRAIN_CRUISE_SPEED carTrain fTempFloat
			ADD_BLIP_FOR_CAR carTrain blipTrain
			SET_BLIP_AS_FRIENDLY blipTrain TRUE

			CLEAR_ONSCREEN_TIMER g_Quarry_timerKM

			// INFO: The train is approaching
			PRINT_NOW QUAR_29 7000 1

			// Set the mission stop time
			nMissionStopTime = m_mission_timer

			flagTrainStarted = TRUE
		ENDIF
	ENDIF


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Quarry_Next_Stage
	ENDIF

RETURN


// ****************************************
// DESTROY: Chase Truck

Quarry_Destroy_Chase_Truck:

	IF IS_CAR_DEAD carStolen
		flagDestroyed = TRUE
	ENDIF

	IF IS_CAR_DEAD carDumper
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_DUMPER_DESTROYED
		RETURN
	ENDIF


   	// Initialisation
	// --------------

   	IF m_goals = 0
		// Set Stolen truck to drive randomly at a decent speed
		CAR_WANDER_RANDOMLY carStolen
		SET_CAR_CRUISE_SPEED carStolen 26.0
		SET_CAR_DRIVING_STYLE carStolen DRIVINGMODE_AVOIDCARS
		SET_CAR_ONLY_DAMAGED_BY_PLAYER carStolen TRUE
		ADD_BLIP_FOR_CAR carStolen blipStolen
		LOCK_CAR_DOORS carStolen CARLOCK_LOCKOUT_PLAYER_ONLY

		// Dumper blip
		ADD_BLIP_FOR_CAR carDumper blipDumper
		SET_BLIP_AS_FRIENDLY blipDumper TRUE

		// INFO: Destroy dumper
		PRINT_NOW QUAR_32 10000 1

		// Damage bar
		DISPLAY_ONSCREEN_COUNTER_WITH_STRING g_Quarry_damageKM COUNTER_DISPLAY_BAR QUAR_D6

		// Timer
//		g_Quarry_timerKM = QUARRY_DESTROY_MISSION_TIME_sec * 1000
		nTempInt = QUARRY_MISSION_DESTROY - 1
		g_Quarry_timerKM = g_Quarry_recordsKM[nTempInt] * 1000
		DISPLAY_ONSCREEN_TIMER_WITH_STRING g_Quarry_timerKM TIMER_DOWN QUAR_D4
		nMissionStartTime = m_mission_timer

		// Switch off any roads that are particularly dodgy for the stolen dumper to travel on
		SWITCH_ROADS_OFF 256.6312 734.7498 4.0053 343.9576 765.6713 6.4852
		SWITCH_ROADS_OFF 291.2253 762.6765 4.0625 354.4869 784.4223 6.6101
		SWITCH_ROADS_OFF -149.9865 460.0670 4.9719 -119.7325 530.4155 12.4813
		SWITCH_ROADS_OFF -100.9858 1190.3802 16.1736 97.0928 1205.2023 19.9344
		SWITCH_ROADS_OFF -124.9028 1209.5811 14.6519 -108.1954 1243.8207 19.8554
		SWITCH_ROADS_OFF -113.2273 1246.4767 11.3781 -84.1825 1268.7506 17.5621
		flagRoadsSwitchedOff = TRUE

		flagDisplayedTooFarText = FALSE

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------

	// Has truck escaped?
	IF NOT IS_CAR_DEAD carStolen
		GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
		GET_CAR_COORDINATES carStolen xposTemp2 yposTemp2 zposTemp2
		GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
		IF fTempDistance > QUARRY_DESTROY_MAX_DISTANCE_APART_m
			m_failed = TRUE
			m_fail_reason = QUARRY_FAILED_TOO_FAR_AWAY
			RETURN
		ENDIF

		IF flagDisplayedTooFarText = FALSE
			IF fTempDistance > QUARRY_DESTROY_TOO_FAR_AWAY_WARNING_m
				PRINT_NOW QUAR_33 7000 1
				flagDisplayedTooFarText = TRUE
			ENDIF
		ENDIF

		IF flagDisplayedTooFarText = TRUE
			IF fTempDistance < QUARRY_DESTROY_CLEAR_TOO_FAR_AWAY_WARNING_m
				CLEAR_THIS_PRINT QUAR_33
				flagDisplayedTooFarText = FALSE
			ENDIF
		ENDIF
	ENDIF


	// Check for Time Up
	IF g_Quarry_timerKM <= 0
		m_failed = TRUE
		m_fail_reason = QUARRY_FAILED_REACHED_SAFETY
		RETURN
	ENDIF


	// Subgoals
	// --------

	// Mission passed when stolen truck destroyed, but make sure the player's truck doesn't also get destroyed
	IF m_goals = 1
		IF flagDestroyed = TRUE
			timerMissionPass = m_mission_timer + 2000

			m_goals++
		ENDIF
	ENDIF


	// Pass mission when timer runs out
	IF m_goals = 2
		IF timerMissionPass < m_mission_timer
			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	// Blip maintenance
	IF IS_CHAR_IN_CAR scplayer carDumper
		IF DOES_BLIP_EXIST blipDumper
			REMOVE_BLIP blipDumper
			
			CLEAR_THIS_PRINT QUAR_36
		ENDIF
	ELSE
		IF NOT DOES_BLIP_EXIST blipDumper
			ADD_BLIP_FOR_CAR carDumper blipDumper
			SET_BLIP_AS_FRIENDLY blipDumper TRUE

			// Info: Get back in the Dumper
			PRINT_NOW QUAR_36 5000 1
		ENDIF
	ENDIF


	// When the car takes a decent bit of damage, change it's health to be almost dead
	IF NOT IS_CAR_DEAD carStolen
		GET_CAR_HEALTH carStolen nTempInt
		IF nTempInt > 300
		AND nTempInt <= 800
			SET_CAR_HEALTH carStolen 300
		ENDIF
	ENDIF


	IF NOT IS_CAR_DEAD carStolen
		// Calculate and update onscreen counter
		// NOTE: health <= 250 is 0
		//		 health 251 - 300 is 20% of total
		//		 health 801 - 1000 is 80% of total
		GET_CAR_HEALTH carStolen nTempInt
		IF nTempInt <= 250
			g_Quarry_damageKM = 0
		ELSE
			IF nTempInt = 1000
				g_Quarry_damageKM = 100
			ELSE
				// ...add up total of valid health range
				IF nTempInt = 300
					g_Quarry_damageKM = 20
				ELSE
					IF nTempInt < 300
						// must be between 251 and 299 which is 20% of total bar
						nTempInt2 = 300 - nTempInt
						g_Quarry_damageKM = 50 - nTempInt2
						g_Quarry_damageKM *= 20		// 20% of total bar
						g_Quarry_damageKM /= 50		// 50 is the valid range (250 - 300)
					ELSE
						// must be between 801 and 999
						IF nTempInt < 800
							nTempInt = 800
						ENDIF
						nTempInt2 = 1000 - nTempInt
						g_Quarry_damageKM = 200 - nTempInt2
						g_Quarry_damageKM *= 80		// 80% of total bar
						g_Quarry_damageKM /= 200	// 200 is the valid range (800 - 1000)
						g_Quarry_damageKM += 20		// add on other 20%
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Make sure the boxes stay attached
	IF NOT IS_CAR_DEAD carStolen
		REPEAT QUARRY_DESTROY_MAX_CRATES nLoop
			IF DOES_OBJECT_EXIST objectRocks[nLoop]
				IF NOT IS_OBJECT_ATTACHED objectRocks[nLoop]
					// Re-attach object
					SWITCH nLoop
						CASE 0
							ATTACH_OBJECT_TO_CAR objectRocks[nLoop] carStolen 0.5 -0.7 0.7 90.0 0.0 0.0
							BREAK
						CASE 1
							ATTACH_OBJECT_TO_CAR objectRocks[nLoop] carStolen -0.5 -0.7 0.7 90.0 0.0 0.0
							BREAK
						CASE 2
							ATTACH_OBJECT_TO_CAR objectRocks[nLoop] carStolen 0.5 -1.7 0.73 90.0 0.0 0.0
							BREAK
						CASE 3
							ATTACH_OBJECT_TO_CAR objectRocks[nLoop] carStolen -0.5 -1.7 0.73 90.0 0.0 0.0
							BREAK
						CASE 4
							ATTACH_OBJECT_TO_CAR objectRocks[nLoop] carStolen 0.5 -2.7 0.76 90.0 0.0 0.0
							BREAK
						CASE 5
							ATTACH_OBJECT_TO_CAR objectRocks[nLoop] carStolen -0.5 -2.7 0.76 90.0 0.0 0.0
							BREAK
					ENDSWITCH
				ENDIF
			ENDIF
		ENDREPEAT
	ENDIF


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Quarry_Next_Stage
	ENDIF

RETURN


// ****************************************
// ASSET ACQUIRED

Quarry_Asset_Acquired:

   	// Initialisation
	// --------------

   	IF m_goals = 0
		// Allow the 'mission passed' to be displayed for a few seconds
		SET_PLAYER_CONTROL player1 OFF
		timerCutscene = m_mission_timer + 5000

		// Clear the onscreen timer
		CLEAR_ONSCREEN_TIMER g_Quarry_timerKM

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
			CREATE_PROTECTION_PICKUP 818.2578 853.8873 10.8059 g_Quarry_CashPickupValueKM g_Quarry_CashPickupValueKM g_Quarry_CashPickupKM
			SWITCH_WIDESCREEN ON

			SET_EVERYONE_IGNORE_PLAYER player1 TRUE
			SET_ALL_CARS_CAN_BE_DAMAGED FALSE

			LOAD_SCENE 818.2578 853.8873 10.8059
//			SET_FIXED_CAMERA_POSITION 830.2333 852.7350 16.4594 0.0 0.0 0.0
//			POINT_CAMERA_AT_POINT 829.2551 852.8201 16.2696 JUMP_CUT
			SET_FIXED_CAMERA_POSITION 849.6491 839.9428 42.7363 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 848.7661 840.1115 42.2983 JUMP_CUT
			
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
//			SET_FIXED_CAMERA_POSITION 820.7517 850.7118 11.7398 0.0 0.0 0.0
//			POINT_CAMERA_AT_POINT 820.1194 851.4731 11.5961 JUMP_CUT
			SET_FIXED_CAMERA_POSITION 825.0984 855.4503 13.5770 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 824.1995 855.1732 13.2376 JUMP_CUT

			// Print potential revenue
			PRINT_WITH_NUMBER_NOW ASS_LUV g_Quarry_CashPickupValueKM 6000 1

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
			GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
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
			// Quarry completion time
			PRINT_WITH_2_NUMBERS_NOW QUAR_P7 nTotalMinutes nTotalSeconds 10000 1

			// Dumper and Dozer now always available
			PRINT QUAR_H3 7000 1

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
		GOSUB Quarry_Next_Stage
	ENDIF

RETURN




// *************************************************************************************************************
//										MISSION SPECIFIC GOSUBS  
// *************************************************************************************************************

// ****************************************
// Display Entrance Blip
Quarry_Display_Entrance_Blip:

	xposEntrance = 808.4078
	yposEntrance = 841.9887
	zposEntrance = 8.6694

	ADD_BLIP_FOR_COORD xposEntrance yposEntrance zposEntrance blipEntrance
	flagDisplayEntranceBlip = TRUE

RETURN


// ****************************************
// Display Other Entrance Blip
Quarry_Display_Other_Entrance_Blip:

	xposEntrance = 371.5454
	yposEntrance = 978.1724
	zposEntrance = 28.5210

	ADD_BLIP_FOR_COORD xposEntrance yposEntrance zposEntrance blipOtherEntrance
	flagDisplayOtherEntranceBlip = TRUE

RETURN


// ****************************************
// Calculate Future Time
Quarry_Calculate_Future_Time:

	GET_TIME_OF_DAY nHours nMinutes

	// Work out the mission fail timer
	// ...convert eh add hours and add minutes into msec
	nTempInt = nAddHours * 60
	nTempInt += nAddMinutes
	nTempInt *= 1000
	timerMissionFail = nTempInt + m_mission_timer

	// Update the minutes
	nMinutes += nAddMinutes
	IF nMinutes >= 60
		nHours++
		nMinutes -= 60
	ENDIF

	// Update the hours
	nHours += nAddHours
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

		nTempInt *= 1000
		timerMissionFail += nTempInt
		flagTempFlag = TRUE
	ENDIF

	IF flagTempFlag = FALSE
		IF nMinutes > 30
		AND nMinutes < 45
			// ...round up to quarter to the hour
			nTempInt = 45 - nMinutes
			nMinutes = 45

			nTempInt *= 1000
			timerMissionFail += nTempInt
			flagTempFlag = TRUE
		ENDIF
	ENDIF

	IF flagTempFlag = FALSE
		IF nMinutes > 15
		AND nMinutes < 30
			// ...round up to the half hour
			nTempInt = 30 - nMinutes
			nMinutes = 30

			nTempInt *= 1000
			timerMissionFail += nTempInt
			flagTempFlag = TRUE
		ENDIF
	ENDIF

	IF flagTempFlag = FALSE
		IF nMinutes > 0
		AND nMinutes < 15
			// ...round up to quarter past the hour
			nTempInt = 15 - nMinutes
			nMinutes = 15

			nTempInt *= 1000
			timerMissionFail += nTempInt
			flagTempFlag = TRUE
		ENDIF
	ENDIF

	// Convert the Hours and Minutes into a msec equivalent for display
	nTempInt = nHours * 60
	nTempInt += nMinutes
	nTempInt *= 1000
	g_Quarry_timerKM = nTempInt

RETURN



// *************************************************************************************************************
// 												HOUSEKEEPING GOSUBS   
// *************************************************************************************************************

// ****************************************
// DEBUG TOOLS
Quarry_Debug_Tools:

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
		Quarry_view_debug[0] = m_stage
		Quarry_view_debug[1] = m_goals
		Quarry_view_debug[2] = m_mission_timer
		Quarry_view_debug[3] = 0
		Quarry_view_debug[4] = 0
		Quarry_view_debug[5] = 0
		Quarry_view_debug[6] = 0
		Quarry_view_debug[7] = 0
		// First two lines are so that the important data displayed is not hidden by other text
		VIEW_INTEGER_VARIABLE Quarry_view_debug[0] m_stage
		VIEW_INTEGER_VARIABLE Quarry_view_debug[1] m_goals
		VIEW_INTEGER_VARIABLE Quarry_view_debug[2] m_mission_timer
	ENDIF


	IF display_debug = 2
		Quarry_view_debug[7] = 0
		REPEAT QUARRY_LEVELS nLoop
			Quarry_view_debug[nLoop] = g_Quarry_recordsKM[nLoop]
			Quarry_view_debug[7] += g_Quarry_recordsKM[nLoop]
		ENDREPEAT

		// Quarry records
		VIEW_INTEGER_VARIABLE Quarry_view_debug[0] rockfall
		VIEW_INTEGER_VARIABLE Quarry_view_debug[1] SABOTAGE
		VIEW_INTEGER_VARIABLE Quarry_view_debug[2] DESTROY.
		VIEW_INTEGER_VARIABLE Quarry_view_debug[3] REMOVAL.
		VIEW_INTEGER_VARIABLE Quarry_view_debug[4] DELIVERY
		VIEW_INTEGER_VARIABLE Quarry_view_debug[5] SPILLAGE
		VIEW_INTEGER_VARIABLE Quarry_view_debug[6] BURIAL..
		VIEW_INTEGER_VARIABLE Quarry_view_debug[7] TOTAL...
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
Quarry_Debug_Shortcuts:

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
		m_passed = TRUE
		RETURN
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_M
		g_nQuarryMissionsPassed++
		WRITE_DEBUG_WITH_INT Missions_Passed g_nQuarryMissionsPassed
		RETURN
	ENDIF


	// Jumps
	// -----

	IF NOT IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_J
		RETURN
	ENDIF

RETURN



// ****************************************
// FRAME COUNTER (Useful if processor scheduling is needed)
Quarry_Frame_Counter:

	m_frame_num++
	IF m_frame_num > 9
		m_frame_num = 0
	ENDIF

RETURN



// ****************************************
// ADDITIONAL TIMERS
Quarry_Additional_Timers:

	GET_GAME_TIMER m_this_frame_time
	m_time_diff = m_this_frame_time - m_last_frame_time 
	m_last_frame_time = m_this_frame_time

	m_mission_timer += m_time_diff

RETURN



// =================================================================
// GOSUB: Next_Stage (sets flags and timers for next mission stage)
// =================================================================
Quarry_Next_Stage:

   	m_stage++
   	m_goals        = 0
   	TIMERA 		   = 0
   	TIMERB		   = 0

RETURN					




// *************************************************************************************************************
// 												INITIALISATION GOSUBS   
// *************************************************************************************************************

Quarry_Initialisation:

	WHILE NOT IS_PLAYER_PLAYING player1
		WAIT 0
	ENDWHILE


	SET_PLAYER_CONTROL player1 OFF


//	PRINT_BIG ( QUARRY ) 3000 4 //Quarry
	WAIT 1000
	

	DO_FADE 1000 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE


	// Initialise global 'records' variables
	IF done_quarry_progress = FALSE
		IF g_nQuarryMissionsPassed = 0
			// Store the best times for all missions
			nTempInt = QUARRY_MISSION_ROCKFALL - 1
			g_Quarry_recordsKM[nTempInt] = QUARRY_ROCKFALL_MISSION_TIME_sec

			nTempInt = QUARRY_MISSION_SABOTAGE - 1
			g_Quarry_recordsKM[nTempInt] = QUARRY_SABOTAGE_MISSION_TIME_sec

			nTempInt = QUARRY_MISSION_DESTROY - 1
			g_Quarry_recordsKM[nTempInt] = QUARRY_DESTROY_MISSION_TIME_sec

			nTempInt = QUARRY_MISSION_REMOVAL - 1
			g_Quarry_recordsKM[nTempInt] = QUARRY_REMOVAL_MISSION_TIME_sec

			nTempInt = QUARRY_MISSION_DELIVERY - 1
			g_Quarry_recordsKM[nTempInt] = QUARRY_DELIVERY_MISSION_TIME_sec

			nTempInt = QUARRY_MISSION_SPILLAGE - 1
			g_Quarry_recordsKM[nTempInt] = QUARRY_SPILLAGE_TRAIN_TIME_sec

			nTempInt = QUARRY_MISSION_BURIAL - 1
			g_Quarry_recordsKM[nTempInt] = QUARRY_BURIAL_MISSION_TIME_sec
		ENDIF
	ENDIF


	// Position the player
	CLEAR_AREA quarryX quarryY quarryZ 1000.0 FALSE
	LOAD_SCENE quarryX quarryY quarryZ
	SET_CHAR_COORDINATES scplayer quarryX quarryY quarryZ 
	SET_CHAR_HEADING scplayer 90.0
	SET_CAMERA_BEHIND_PLAYER


	WAIT 500


	// Initialise missions
	nTempInt = g_nQuarryMissionsPassed
	WHILE nTempInt >= QUARRY_HIGHEST_MISSION
		nTempInt -= QUARRY_HIGHEST_MISSION
	ENDWHILE

	nCurrentMission = nTempInt + 1
	GOSUB Quarry_Initialise_Mission


	// Set the 'stop' time to 0
	// NOTE: This is only used by the train mission
	nMissionStopTime = 0


	// Switch off the Dumper and Dozer car generators
	SWITCH_CAR_GENERATOR gen_car1 0
	SWITCH_CAR_GENERATOR gen_car2 0


	WAIT 1500


	// Return the oddjob 'title over fade' status back to default
	DRAW_ODDJOB_TITLE_BEFORE_FADE TRUE


	// Fade in
	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_IN
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE


	// If the Quarry missions have been completed then allow each mission to be skipped
	IF done_quarry_progress = TRUE
		PRINT_HELP QUAR_H2  
	ENDIF


	SET_PLAYER_CONTROL player1 ON

	m_fail_reason = QUARRY_FAILED_UNKNOWN

	TimerA	= 0
	TimerB	= 0
	m_goals	= 0

RETURN


// ***********************************
// Initialise Mission
Quarry_Initialise_Mission:

	SWITCH nCurrentMission
		CASE QUARRY_MISSION_ROCKFALL
			GOSUB Quarry_Initialise_Mission_Rockfall
			BREAK

		CASE QUARRY_MISSION_SABOTAGE
			GOSUB Quarry_Initialise_Mission_Sabotage
			BREAK

		CASE QUARRY_MISSION_REMOVAL
			GOSUB Quarry_Initialise_Mission_Removal
			BREAK

		CASE QUARRY_MISSION_DELIVERY
			GOSUB Quarry_Initialise_Mission_Delivery
			BREAK

		CASE QUARRY_MISSION_SPILLAGE
			GOSUB Quarry_Initialise_Mission_Spillage
			BREAK

		CASE QUARRY_MISSION_BURIAL
			GOSUB Quarry_Initialise_Mission_Burial
			BREAK

		CASE QUARRY_MISSION_DESTROY
			GOSUB Quarry_Initialise_Mission_Destroy
			BREAK

		DEFAULT
			WRITE_DEBUG Unknown_Quarry_Mission_ID
	ENDSWITCH

RETURN


// ***********************************
// Initialise Mission Burial
Quarry_Initialise_Mission_Burial:

	// Set the first mission stage
	m_stage = QUARRY_STAGE_BURIAL_PUSH_BODY

	// Request and load necessary models
	REQUEST_MODEL SANCHEZ
	REQUEST_MODEL DOZER
	REQUEST_MODEL DUMPER
//	REQUEST_MODEL DYN_QUARRYROCK02		// TEMP: Dead body object
	REQUEST_MODEL DEAD_TIED_COP
	REQUEST_MODEL COPBIKE
	REQUEST_MODEL CR_AMMOBOX

	LOAD_ALL_MODELS_NOW

	WHILE NOT HAS_MODEL_LOADED	SANCHEZ
	OR NOT HAS_MODEL_LOADED		DOZER
	OR NOT HAS_MODEL_LOADED		DUMPER
	OR NOT HAS_MODEL_LOADED		DEAD_TIED_COP
	OR NOT HAS_MODEL_LOADED		COPBIKE
	OR NOT HAS_MODEL_LOADED		CR_AMMOBOX
		WAIT 0
	ENDWHILE
//	OR NOT HAS_MODEL_LOADED		DYN_QUARRYROCK02

	// Position models
	// ...dirtbike
	CREATE_CAR SANCHEZ 819.7281 858.3189 11.0469 carDirtbike
	SET_CAR_HEADING carDirtbike 291.4761

	// ...dozer
	CREATE_CAR DOZER 653.1636 879.9844 -42.5799 carDozer
	SET_CAR_HEADING carDozer 119.7783

	// ...dumper
	CREATE_CAR DUMPER 717.0151 938.3721 -19.7314 carDumper
	SET_CAR_HEADING carDumper 38.1129

	// ...dumper load helper
	CREATE_OBJECT CR_AMMOBOX 720.5391 922.5157 -19.8436 objectDumperLoadHelper
	SET_OBJECT_COLLISION objectDumperLoadHelper FALSE
	SET_OBJECT_VISIBLE objectDumperLoadHelper FALSE
	ATTACH_OBJECT_TO_CAR objectDumperLoadHelper carDumper 0.0 -1.7 1.5 0.0 0.0 0.0

	// ...cop body
	CREATE_OBJECT DEAD_TIED_COP 606.9487 933.0876 -38.5322 objectDeadBody
	SET_OBJECT_DYNAMIC objectDeadBody TRUE

	// ...cop bike
	// NOTE: Drop it from a height so it doesn't appear standing up
	CREATE_CAR COPBIKE 560.3003 844.9364 -13.0258 carCopBike
	SET_CAR_CAN_BE_DAMAGED carCopBike FALSE

	// Special conditions
	SWITCH_EMERGENCY_SERVICES	OFF

RETURN


// ***********************************
// Initialise Mission Rockfall
Quarry_Initialise_Mission_Rockfall:

	// Set the first mission stage
	m_stage = QUARRY_STAGE_ROCKFALL_CLEAR_PATHS

	// Request and load necessary models
	REQUEST_MODEL DOZER
	REQUEST_MODEL DYN_QUARRYROCK03

	LOAD_ALL_MODELS_NOW

	WHILE NOT HAS_MODEL_LOADED	DOZER
	OR NOT HAS_MODEL_LOADED		DYN_QUARRYROCK03
		WAIT 0
	ENDWHILE

	// Position models
	// ...dozer
	CREATE_CAR DOZER 816.4832 845.8509 9.4934 carDozer
	SET_CAR_HEADING carDozer 115.4882

	// ...rocks
	nTempInt = 0
	CREATE_OBJECT DYN_QUARRYROCK03 771.6403 836.2275 4.8965 objectRocks[nTempInt]

	nTempInt++
	CREATE_OBJECT DYN_QUARRYROCK03 751.6732 944.9977 4.8593 objectRocks[nTempInt]

	nTempInt++
	CREATE_OBJECT DYN_QUARRYROCK03 538.9102 985.6403 -8.5887 objectRocks[nTempInt]

	nTempInt++
	CREATE_OBJECT DYN_QUARRYROCK03 680.5322 963.4501 -13.6607 objectRocks[nTempInt]

	nTempInt++
	CREATE_OBJECT DYN_QUARRYROCK03 516.8391 928.7185 -27.6183 objectRocks[nTempInt]

	nTempInt++
	CREATE_OBJECT DYN_QUARRYROCK03 522.6153 828.3048 -27.1838 objectRocks[nTempInt]

	nTempInt++
	CREATE_OBJECT DYN_QUARRYROCK03 646.7208 752.2311 -12.1482 objectRocks[nTempInt]

RETURN


// ***********************************
// Initialise Mission Sabotage
Quarry_Initialise_Mission_Sabotage:

	// Set the first mission stage
	m_stage = QUARRY_STAGE_SABOTAGE_MOVE_BOMBS

	// Request and load necessary models
	REQUEST_MODEL DOZER
	REQUEST_MODEL KB_BARREL

	LOAD_ALL_MODELS_NOW

	WHILE NOT HAS_MODEL_LOADED	DOZER
	OR NOT HAS_MODEL_LOADED		KB_BARREL
		WAIT 0
	ENDWHILE

	// Position models
	// ...dozer
	CREATE_CAR DOZER 816.4832 845.8509 9.4934 carDozer
	SET_CAR_HEADING carDozer 115.4882

	// ...bombs
	// NOTE: Make sure their explode times increase so that they explode in sequence
	nTempInt = 0
	CREATE_OBJECT KB_BARREL 817.0658 865.5903 11.0469 objectBombs[nTempInt]
//	timerBombsExplode[nTempInt] = 1000 * 90

	nTempInt++
	CREATE_OBJECT KB_BARREL 714.5327 920.0583 -19.6787 objectBombs[nTempInt]
//	timerBombsExplode[nTempInt] = 1000 * 180

	nTempInt++
	CREATE_OBJECT KB_BARREL 632.2825 895.8612 -43.9534 objectBombs[nTempInt]
//	timerBombsExplode[nTempInt] = 1000 * 240

	nTempInt++
	CREATE_OBJECT KB_BARREL 675.7952 841.8704 -43.9609 objectBombs[nTempInt]
//	timerBombsExplode[nTempInt] = 1000 * 300

	// Calculate the mission time for each bomb
//	nTempInt = QUARRY_SABOTAGE_MISSION_TIME_sec * 100
	nTempInt2 = QUARRY_MISSION_SABOTAGE - 1
	nTempInt = g_Quarry_recordsKM[nTempInt2] * 100
	REPEAT QUARRY_SABOTAGE_MAX_BOMBS nLoop
		SWITCH nLoop
			CASE 0
				// 3/10ths of the total time
				timerBombsExplode[nLoop] = nTempInt * 3
				BREAK
			CASE 1
				// 6/10ths of the total time
				timerBombsExplode[nLoop] = nTempInt * 6
				BREAK
			CASE 2
				// 8/10ths of the total time
				timerBombsExplode[nLoop] = nTempInt * 8
				BREAK
			CASE 3
				// All of the total time
				timerBombsExplode[nLoop] = g_Quarry_recordsKM[nTempInt2] * 1000
				BREAK
		ENDSWITCH
	ENDREPEAT

	// Special conditions
	SWITCH_EMERGENCY_SERVICES	OFF

RETURN


// ***********************************
// Initialise Mission Removal
Quarry_Initialise_Mission_Removal:

	// Set the first mission stage
	m_stage = QUARRY_STAGE_REMOVAL_REACH_DUMPER

	// Request and load necessary models
	REQUEST_MODEL DUMPER
	REQUEST_MODEL DEAD_TIED_COP
	REQUEST_MODEL SANCHEZ

	// Fire Fuel models
	REQUEST_MODEL CM_BOX
	REQUEST_MODEL BLACKBAG2
	REQUEST_MODEL DYN_WOODPILE
	REQUEST_MODEL DYN_WOODPILE2

	LOAD_ALL_MODELS_NOW

	WHILE NOT HAS_MODEL_LOADED	DUMPER
	OR NOT HAS_MODEL_LOADED		DEAD_TIED_COP
	OR NOT HAS_MODEL_LOADED		SANCHEZ
		WAIT 0
	ENDWHILE

	WHILE NOT HAS_MODEL_LOADED	CM_BOX
	OR NOT HAS_MODEL_LOADED		BLACKBAG2
	OR NOT HAS_MODEL_LOADED		DYN_WOODPILE
	OR NOT HAS_MODEL_LOADED		DYN_WOODPILE2
		WAIT 0
	ENDWHILE

	// Position models
	// ...dumper
	CREATE_CAR DUMPER 355.8632 895.6545 19.3990 carDumper
	SET_CAR_HEADING carDumper 342.1892

	// ...dirtbike
	CREATE_CAR SANCHEZ 819.7281 858.3189 11.0469 carDirtbike
	SET_CAR_HEADING carDirtbike 291.4761

	// ...dead bodies (rocks)
	nTempInt = 0
	CREATE_OBJECT DEAD_TIED_COP 355.8632 894.6545 24.2254 objectBodies[nTempInt]
	GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 180.0 fTempFloat
	fTempFloat -= 90.0
	IF fTempFloat < 0.0
		fTempFloat += 360.0
	ENDIF
	ATTACH_OBJECT_TO_CAR objectBodies[nTempInt] carDumper 0.5 -0.7 0.7 90.0 fTempFloat fTempFloat

	nTempInt++
	CREATE_OBJECT DEAD_TIED_COP 355.8632 895.6545 24.5629 objectBodies[nTempInt]
	GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 180.0 fTempFloat
	fTempFloat -= 90.0
	IF fTempFloat < 0.0
		fTempFloat += 360.0
	ENDIF
	ATTACH_OBJECT_TO_CAR objectBodies[nTempInt] carDumper -0.5 -1.7 0.73 90.0 fTempFloat fTempFloat

	nTempInt++
	CREATE_OBJECT DEAD_TIED_COP 355.8632 896.6545 24.8877 objectBodies[nTempInt]
	GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 180.0 fTempFloat
	fTempFloat -= 90.0
	IF fTempFloat < 0.0
		fTempFloat += 360.0
	ENDIF
	ATTACH_OBJECT_TO_CAR objectBodies[nTempInt] carDumper 0.5 -2.7 0.76 90.0 fTempFloat fTempFloat

	// Special Conditions
	SET_CAR_DENSITY_MULTIPLIER 3.0

	// Special conditions
	SWITCH_EMERGENCY_SERVICES	OFF

RETURN


// ***********************************
// Initialise Mission Delivery
Quarry_Initialise_Mission_Delivery:

	// Set the first mission stage
	m_stage = QUARRY_STAGE_DELIVERY_DELIVER

	// Request and load necessary models
	REQUEST_MODEL DUMPER
	REQUEST_MODEL KB_BARREL

	LOAD_ALL_MODELS_NOW

	WHILE NOT HAS_MODEL_LOADED	DUMPER
	OR NOT HAS_MODEL_LOADED		KB_BARREL
		WAIT 0
	ENDWHILE

	// Position models
	// ...dumper
	CREATE_CAR DUMPER 816.4832 845.8509 9.4934 carDumper
	SET_CAR_HEADING carDumper 295.4882

	// ...bombs
	nTempInt = 0
	CREATE_OBJECT KB_BARREL 815.4841 846.2587 15.0409 objectBombs[nTempInt]
	ATTACH_OBJECT_TO_CAR objectBombs[nTempInt] carDumper 0.5 -0.7 0.9 90.0 0.0 0.0
	SET_OBJECT_PROOFS objectBombs[nTempInt] FALSE FALSE FALSE TRUE FALSE

	nTempInt++
	CREATE_OBJECT KB_BARREL 816.6973 844.8737 15.0460 objectBombs[nTempInt]
	ATTACH_OBJECT_TO_CAR objectBombs[nTempInt] carDumper -0.5 -0.7 0.9 90.0 0.0 0.0
	SET_OBJECT_PROOFS objectBombs[nTempInt] FALSE FALSE FALSE TRUE FALSE

	nTempInt++
	CREATE_OBJECT KB_BARREL 814.2406 845.8572 14.9014 objectBombs[nTempInt]
	ATTACH_OBJECT_TO_CAR objectBombs[nTempInt] carDumper 0.5 -1.7 0.93 90.0 0.0 0.0
	SET_OBJECT_PROOFS objectBombs[nTempInt] FALSE FALSE FALSE TRUE FALSE

	nTempInt++
	CREATE_OBJECT KB_BARREL 815.0480 844.0368 14.8892 objectBombs[nTempInt]
	ATTACH_OBJECT_TO_CAR objectBombs[nTempInt] carDumper -0.5 -1.7 0.93 90.0 0.0 0.0
	SET_OBJECT_PROOFS objectBombs[nTempInt] FALSE FALSE FALSE TRUE FALSE

	nTempInt++
	CREATE_OBJECT KB_BARREL 813.2042 843.0317 14.7114 objectBombs[nTempInt]
	ATTACH_OBJECT_TO_CAR objectBombs[nTempInt] carDumper 0.5 -2.7 0.96 90.0 0.0 0.0
	SET_OBJECT_PROOFS objectBombs[nTempInt] FALSE FALSE FALSE TRUE FALSE

	nTempInt++
	CREATE_OBJECT KB_BARREL 813.2919 845.4581 14.8038 objectBombs[nTempInt]
	ATTACH_OBJECT_TO_CAR objectBombs[nTempInt] carDumper -0.5 -2.7 0.96 90.0 0.0 0.0
	SET_OBJECT_PROOFS objectBombs[nTempInt] FALSE FALSE FALSE TRUE FALSE

	// ...helpers
	nTempInt = 0
	CREATE_OBJECT CR_AMMOBOX 815.4841 846.2587 15.0409 objectHelpers[nTempInt]
	SET_OBJECT_COLLISION objectHelpers[nTempInt] FALSE
	SET_OBJECT_VISIBLE objectHelpers[nTempInt] FALSE
	ATTACH_OBJECT_TO_CAR objectHelpers[nTempInt] carDumper -1.0 -5.0 0.3 0.0 0.0 0.0

	nTempInt++
	CREATE_OBJECT CR_AMMOBOX 816.6973 844.8737 15.0460 objectHelpers[nTempInt]
	SET_OBJECT_COLLISION objectHelpers[nTempInt] FALSE
	SET_OBJECT_VISIBLE objectHelpers[nTempInt] FALSE
	ATTACH_OBJECT_TO_CAR objectHelpers[nTempInt] carDumper 0.0 -5.0 0.3 0.0 0.0 0.0

	nTempInt++
	CREATE_OBJECT CR_AMMOBOX 815.0480 844.0368 14.8892 objectHelpers[nTempInt]
	SET_OBJECT_COLLISION objectHelpers[nTempInt] FALSE
	SET_OBJECT_VISIBLE objectHelpers[nTempInt] FALSE
	ATTACH_OBJECT_TO_CAR objectHelpers[nTempInt] carDumper 1.0 -5.0 0.3 0.0 0.0 0.0

	// Special conditions
	SWITCH_EMERGENCY_SERVICES	OFF

RETURN


// ***********************************
// Initialise Mission Spillage
Quarry_Initialise_Mission_Spillage:

	// Set the first mission stage
	m_stage = QUARRY_STAGE_SPILLAGE_CLEAR_TRACKS

	// Request and load necessary models
	REQUEST_MODEL DOZER
	REQUEST_MODEL KB_BARREL
	REQUEST_MODEL STREAK
	REQUEST_MODEL STREAKC

	LOAD_ALL_MODELS_NOW

	WHILE NOT HAS_MODEL_LOADED	DOZER
	OR NOT HAS_MODEL_LOADED		KB_BARREL
	OR NOT HAS_MODEL_LOADED		STREAK
	OR NOT HAS_MODEL_LOADED		STREAKC
		WAIT 0
	ENDWHILE

	// Position models
	// ...dozer
	CREATE_CAR DOZER 816.4832 845.8509 9.4934 carDozer
	SET_CAR_HEADING carDozer 295.4882

	// ...train
	CREATE_MISSION_TRAIN 11 832.5291 2669.7156 19.8594 FALSE carTrain
	SET_TRAIN_SPEED carTrain 0.0
	SET_TRAIN_CRUISE_SPEED carTrain 0.0

	// ...train collision helper
	CREATE_OBJECT CR_AMMOBOX 816.4832 848.8509 9.4934 objectTrainHelper
	SET_OBJECT_COLLISION objectTrainHelper FALSE
	SET_OBJECT_VISIBLE objectTrainHelper FALSE
	ATTACH_OBJECT_TO_CAR objectTrainHelper carTrain 0.0 9.0 -1.8 0.0 0.0 0.0

	// ...bombs
	// NOTE: Make sure their explode times increase so that they explode in sequence
	nTempInt = 0
	CREATE_OBJECT KB_BARREL 744.1446 1583.6001 7.5408 objectBombs[nTempInt]
	SET_OBJECT_ROTATION objectBombs[nLoop] 0.0 0.0 0.0

	nTempInt++
	CREATE_OBJECT KB_BARREL 743.0427 1602.7684 7.1368 objectBombs[nTempInt]
	SET_OBJECT_ROTATION objectBombs[nLoop] 0.0 78.0 0.0

	nTempInt++
	CREATE_OBJECT KB_BARREL 743.5758 1616.7494 6.8536 objectBombs[nTempInt]
	SET_OBJECT_ROTATION objectBombs[nLoop] 0.0 64.0 0.0

	nTempInt++
	CREATE_OBJECT KB_BARREL 742.6569 1653.4200 6.1580 objectBombs[nTempInt]
	SET_OBJECT_ROTATION objectBombs[nLoop] 0.0 36.0 0.0

	nTempInt++
	CREATE_OBJECT KB_BARREL 743.8057 1671.5228 5.8467 objectBombs[nTempInt]
	SET_OBJECT_ROTATION objectBombs[nLoop] 0.0 14.0 0.0

	nTempInt++
	CREATE_OBJECT KB_BARREL 742.5768 1700.3049 5.4186 objectBombs[nTempInt]
	SET_OBJECT_ROTATION objectBombs[nLoop] 0.0 128.0 0.0

	nTempInt++
	CREATE_OBJECT KB_BARREL 743.4429 1730.1412 5.0731 objectBombs[nTempInt]
	SET_OBJECT_ROTATION objectBombs[nLoop] 0.0 96.0 0.0

	nTempInt++
	CREATE_OBJECT KB_BARREL 742.0187 1765.3209 4.6566 objectBombs[nTempInt]
	SET_OBJECT_ROTATION objectBombs[nLoop] 0.0 65.0 0.0

	nTempInt++
	CREATE_OBJECT KB_BARREL 742.1760 1812.3752 4.3281 objectBombs[nTempInt]
	SET_OBJECT_ROTATION objectBombs[nLoop] 0.0 170.0 0.0

	nTempInt++
	CREATE_OBJECT KB_BARREL 742.7178 1869.9910 4.3281 objectBombs[nTempInt]
	SET_OBJECT_ROTATION objectBombs[nLoop] 0.0 143.0 0.0

	// Special conditions
	SWITCH_RANDOM_TRAINS	OFF

RETURN


// ***********************************
// Initialise Mission Destroy
Quarry_Initialise_Mission_Destroy:

	// Set the first mission stage
	m_stage = QUARRY_STAGE_DESTROY_CHASE_TRUCK

	// Request and load necessary models
	REQUEST_MODEL DUMPER
	REQUEST_MODEL BMYBE
	REQUEST_MODEL CR_GUNCRATE

	LOAD_ALL_MODELS_NOW

	WHILE NOT HAS_MODEL_LOADED	DUMPER
	OR NOT HAS_MODEL_LOADED		BMYBE
	OR NOT HAS_MODEL_LOADED		CR_GUNCRATE
		WAIT 0
	ENDWHILE

	// Position models
	// ...dumper
	CREATE_CAR DUMPER 816.4832 845.8509 9.4934 carDumper
	SET_CAR_HEADING carDumper 295.4882

	// ...stolen truck
	CREATE_CAR DUMPER 812.4955 1144.5670 27.3445 carStolen
	SET_CAR_HEADING carStolen  116.9096
	LOCK_CAR_DOORS carStolen CARLOCK_LOCKED

	// ...stolen truck driver
	CREATE_CHAR_INSIDE_CAR carStolen PEDTYPE_CIVMALE BMYBE charDriver

	// ...Gun Crates
	nTempInt = 0
	CREATE_OBJECT CR_GUNCRATE 815.4841 846.2587 15.0409 objectRocks[nTempInt]
	ATTACH_OBJECT_TO_CAR objectRocks[nTempInt] carStolen 0.5 -0.7 0.7 90.0 0.0 0.0

	nTempInt++
	CREATE_OBJECT CR_GUNCRATE 816.6973 844.8737 15.0460 objectRocks[nTempInt]
	ATTACH_OBJECT_TO_CAR objectRocks[nTempInt] carStolen -0.5 -0.7 0.7 90.0 0.0 0.0

	nTempInt++
	CREATE_OBJECT CR_GUNCRATE 814.2406 845.8572 14.9014 objectRocks[nTempInt]
	ATTACH_OBJECT_TO_CAR objectRocks[nTempInt] carStolen 0.5 -1.7 0.73 90.0 0.0 0.0

	nTempInt++
	CREATE_OBJECT CR_GUNCRATE 815.0480 844.0368 14.8892 objectRocks[nTempInt]
	ATTACH_OBJECT_TO_CAR objectRocks[nTempInt] carStolen -0.5 -1.7 0.73 90.0 0.0 0.0

	nTempInt++
	CREATE_OBJECT CR_GUNCRATE 813.2042 843.0317 14.7114 objectRocks[nTempInt]
	ATTACH_OBJECT_TO_CAR objectRocks[nTempInt] carStolen 0.5 -2.7 0.76 90.0 0.0 0.0

	nTempInt++
	CREATE_OBJECT CR_GUNCRATE 813.2919 845.4581 14.8038 objectRocks[nTempInt]
	ATTACH_OBJECT_TO_CAR objectRocks[nTempInt] carStolen -0.5 -2.7 0.76 90.0 0.0 0.0

	// Special conditions
	SWITCH_EMERGENCY_SERVICES	OFF

RETURN




// *************************************************************************************************************
//										END OF LEVEL GOSUBS - fail, pass, cleanup  
// *************************************************************************************************************

// FAIL
Quarry_Mission_Failed:

	PRINT_BIG M_FAIL 5000 1

	// Display reason for failure (if there is a reason)
	CLEAR_SMALL_PRINTS

	SWITCH m_fail_reason
		CASE QUARRY_FAILED_MISSED_DEADLINE
			PRINT_NOW QUAR_F0 5000 1
			BREAK
		CASE QUARRY_FAILED_DOZER_DESTROYED
			PRINT_NOW QUAR_F1 5000 1
			BREAK
		CASE QUARRY_FAILED_DUMPER_DESTROYED
			PRINT_NOW QUAR_F2 5000 1
			BREAK
		CASE QUARRY_FAILED_DROPPED_LOAD
			PRINT_NOW QUAR_F3 5000 1
			BREAK
		CASE QUARRY_FAILED_COPS_ARRIVED
			PRINT_NOW QUAR_F4 5000 1
			BREAK
		CASE QUARRY_FAILED_DELIVERY_ARRIVED
			PRINT_NOW QUAR_F5 5000 1
			BREAK
		CASE QUARRY_FAILED_BOMB_EXPLODED
			PRINT_NOW QUAR_F6 5000 1
			BREAK
		CASE QUARRY_FAILED_TOO_CLOSE_TO_EXPLOSION
			PRINT_NOW QUAR_F7 5000 1
			BREAK
		CASE QUARRY_FAILED_BODY_DUMPED_IN_WRONG_PLACE
			PRINT_NOW QUAR_F8 5000 1
			BREAK
		CASE QUARRY_FAILED_TOO_MUCH_DAMAGE
			PRINT_NOW QUAR_F9 5000 1
			BREAK
		CASE QUARRY_FAILED_EXPLOSIVES_ON_TRACK
			PRINT_NOW QUAR_Fa 5000 1
			BREAK
		CASE QUARRY_FAILED_TRAIN_DESTROYED
			PRINT_NOW QUAR_Fb 5000 1
			BREAK
		CASE QUARRY_FAILED_TOO_FAR_AWAY
			PRINT_NOW QUAR_Fc 5000 1
			BREAK
		CASE QUARRY_FAILED_REACHED_SAFETY
			PRINT_NOW QUAR_Fd 5000 1
			BREAK
	ENDSWITCH

RETURN


// PASS
Quarry_Mission_Passed:

	// Work out how long the mission took
	// NOTE: the time is in milliseconds. Add a second unless the time was very close to the previous second.
	// NOTE: All levels (except the train) level use the current timer, but the train level uses a specific
	//			stop time which marks when all barrels were moved off the tracks and this time will trigger the
	//			train on the next playthrough.
	IF nMissionStopTime = 0
		nTempInt = m_mission_timer - nMissionStartTime
	ELSE
		nTempInt = nMissionStopTime - nMissionStartTime
	ENDIF

	nTempInt2 = nTempInt / 1000
	nTempInt3 = nTempInt2 * 1000
	nTempInt -= nTempInt3
	IF nTempInt > 50
		// ...increase the number of seconds
		nTempInt2++
	ENDIF

	flagEqualledBestTime	= FALSE
	flagBeatBestTime		= FALSE
	IF g_Quarry_recordsKM[g_nQuarryMissionsPassed] = nTempInt2
		// ...equalled the old record
		flagEqualledBestTime = TRUE
	ELSE
		IF nTempInt2 < g_Quarry_recordsKM[g_nQuarryMissionsPassed]
			flagBeatBestTime = TRUE
		ENDIF
	ENDIF

	// The train mission is strange in that you can pass it with a higher time than previously because the mission time
	// starts the train, but you still have a few seconds to clear the path, so ensure this does not update the stats.
	IF flagBeatBestTime = TRUE
		g_Quarry_recordsKM[g_nQuarryMissionsPassed] = nTempInt2
	ENDIF


	// If this is second or subsequent playthroughs, convert the seconds to minutes and seconds
	IF done_quarry_progress = TRUE
	AND flagBeatBestTime = TRUE
		nBestMinutes = nTempInt2 / 60
		nTempInt = nBestMinutes * 60
		nBestSeconds = nTempInt2 - nTempInt
	ENDIF


	// If all quarry missions are complete, work out the new total mission time
	nTempInt = 0
	IF done_quarry_progress = TRUE
		REPEAT QUARRY_HIGHEST_MISSION nLoop
			nTempInt += g_Quarry_recordsKM[nLoop]
		ENDREPEAT
	ENDIF
	SET_INT_STAT QUARRY_COMPLETION_TIME nTempInt


	// If this is second or subsequent playthroughs, convert the seconds to minutes and seconds
	IF done_quarry_progress = TRUE
	AND flagBeatBestTime = TRUE
		nTotalMinutes = nTempInt / 60
		nTempInt2 = nTotalMinutes * 60
		nTotalSeconds = nTempInt - nTempInt2
	ENDIF


	// Increase number of missions passed
	g_nQuarryMissionsPassed++


	IF done_quarry_progress = FALSE
		// ...this is the player's first playthrough
		// Work out the score
		// NOTE: nTempInt used to hold the score
		SWITCH g_nQuarryMissionsPassed
			CASE 1
				nTempInt = 500
				BREAK
			CASE 2
				nTempInt = 1000
				BREAK
			CASE 3
				nTempInt = 2000
				BREAK
			CASE 4
				nTempInt = 3000
				BREAK
			CASE 5
				nTempInt = 5000
				BREAK
			CASE 6
				nTempInt = 7500
				BREAK
			CASE 7
				nTempInt = 10000
				BREAK
		ENDSWITCH

		IF g_nQuarryMissionsPassed = 7
			PRINT_WITH_NUMBER_BIG M_PASS nTempInt 3000 1
		ELSE
			PRINT_WITH_NUMBER_BIG M_PASS nTempInt 5000 1
		ENDIF

		ADD_SCORE player1 nTempInt
//		INCREMENT_INT_STAT QUARRY_MONEY_MADE nTempInt (Don't bother with this)
	ELSE
		// ...give no more cash on future playthroughs
		PRINT_WITH_NUMBER_BIG M_PASSD 0 5000 1

		IF flagEqualledBestTime = TRUE
			PRINT_NOW QUAR_P5 5000 1
		ELSE
			IF flagBeatBestTime = TRUE
				PRINT_WITH_4_NUMBERS_NOW QUAR_P6 nBestMinutes nBestSeconds nTotalMinutes nTotalSeconds 10000 1
			ENDIF
		ENDIF
	ENDIF

	// Clear wanted level only on pass
	CLEAR_WANTED_LEVEL player1

	// Display number of missions passed
	IF done_quarry_progress = FALSE
		// ...this is the first playthrough of the Quarry missions
		IF g_nQuarryMissionsPassed = 1
			// ... 1 mission passed so don't display plurals
			PRINT_NOW QUAR_P1 5000 1
		ELSE
			IF g_nQuarryMissionsPassed = QUARRY_HIGHEST_MISSION
				REGISTER_ODDJOB_MISSION_PASSED
				PLAYER_MADE_PROGRESS 1
				PLAY_MISSION_PASSED_TUNE 2
				done_quarry_progress = TRUE
				g_nQuarryMissionsPassed = 0

				// ...all progressive missions passed
				PRINT_BIG QUAR_P2 4000 4

				// All quarry missions are complete, so work out the overall mission time
				nTempInt = 0
				IF done_quarry_progress = TRUE
					REPEAT QUARRY_HIGHEST_MISSION nLoop
						nTempInt += g_Quarry_recordsKM[nLoop]
					ENDREPEAT
				ENDIF
				SET_INT_STAT QUARRY_COMPLETION_TIME nTempInt


				// Convert the seconds to minutes and seconds
				nTotalMinutes = nTempInt / 60
				nTempInt2 = nTotalMinutes * 60
				nTotalSeconds = nTempInt - nTempInt2

				// Quarry completion time
///				PRINT_WITH_2_NUMBERS_NOW QUAR_P7 nTotalMinutes nTotalSeconds 10000 1

				// Dumper and Dozer now always available
///				PRINT QUAR_H3 7000 1

				flagShowAssetAcquired = TRUE
			ELSE
				// ...other numbers of missions
				PRINT_WITH_NUMBER_NOW QUAR_P4 g_nQuarryMissionsPassed 5000 1
			ENDIF
		ENDIF
	ELSE
		// ...the player is replaying the quarry missions
		IF g_nQuarryMissionsPassed = QUARRY_HIGHEST_MISSION
			// ...restart the missions again
			g_nQuarryMissionsPassed = 0
		ENDIF
	ENDIF

	// If the Quarry is complete, switch on Dumper and Dozer car generators
	IF done_quarry_progress = TRUE
		SWITCH_CAR_GENERATOR gen_car1 101
		SWITCH_CAR_GENERATOR gen_car2 101
	ENDIF

RETURN


// CLEANUP
Quarry_Mission_Cleanup:

	// Get rid of the Player's mission specific weapons
	// ------------------------------------------------


	// Entity Task Removal, etc.
	// -------------------------


	// Entity Clearup
	// --------------
	MARK_CAR_AS_NO_LONGER_NEEDED	carDirtbike
	MARK_CAR_AS_NO_LONGER_NEEDED	carDozer
	MARK_CAR_AS_NO_LONGER_NEEDED	carDumper
	MARK_CAR_AS_NO_LONGER_NEEDED	carCopBike
	MARK_CAR_AS_NO_LONGER_NEEDED	carStolen

	MARK_CHAR_AS_NO_LONGER_NEEDED	charDriver

	MARK_OBJECT_AS_NO_LONGER_NEEDED	objectDeadBody
	MARK_OBJECT_AS_NO_LONGER_NEEDED	objectDumperLoadHelper
	MARK_OBJECT_AS_NO_LONGER_NEEDED objectTrainHelper

	MARK_MISSION_TRAIN_AS_NO_LONGER_NEEDED carTrain

	REPEAT QUARRY_MAX_ROCKS nLoop
		MARK_OBJECT_AS_NO_LONGER_NEEDED	objectRocks[nLoop]
	ENDREPEAT

	REPEAT QUARRY_MAX_BOMBS nLoop
		MARK_OBJECT_AS_NO_LONGER_NEEDED	objectBombs[nLoop]
	ENDREPEAT

	REPEAT QUARRY_MAX_BODIES nLoop
		MARK_OBJECT_AS_NO_LONGER_NEEDED	objectBodies[nLoop]
	ENDREPEAT

	REPEAT QUARRY_MAX_FIRE_FUEL nLoop
		MARK_OBJECT_AS_NO_LONGER_NEEDED	objectFireFuel[nLoop]
	ENDREPEAT

	REPEAT QUARRY_MAX_HELPERS nLoop
		MARK_OBJECT_AS_NO_LONGER_NEEDED	objectHelpers[nLoop]
	ENDREPEAT


	// Checkpoint clearup
	// ------------------
	REPEAT QUARRY_ROCKFALL_MAX_ROCKS nLoop
		DELETE_CHECKPOINT checkRockfalls[nLoop]
	ENDREPEAT

	DELETE_CHECKPOINT checkDestination

	// Blip Clearup
	// ------------
	REMOVE_BLIP blipDozer
	REMOVE_BLIP blipDumper
	REMOVE_BLIP blipDestination
	REMOVE_BLIP blipCopBike
	REMOVE_BLIP blipDeadBody
	REMOVE_BLIP blipEntrance
	REMOVE_BLIP blipOtherEntrance
	REMOVE_BLIP blipBomb
	REMOVE_BLIP blipTrain
	REMOVE_BLIP blipStolen
	REMOVE_BLIP blipCrane

	REPEAT QUARRY_MAX_ROCKS nLoop
		REMOVE_BLIP	blipRocks[nLoop]
	ENDREPEAT

	REPEAT QUARRY_MAX_BOMBS nLoop
		REMOVE_BLIP	blipBombs[nLoop]
	ENDREPEAT


	// Counters and Timers Clearup
	// ---------------------------
	CLEAR_ONSCREEN_TIMER g_Quarry_timerKM
	CLEAR_ONSCREEN_COUNTER g_Quarry_damageKM


	// Fires Clearup
	// -------------
	CLEAR_ALL_SCRIPT_FIRE_FLAGS


	// Help Clearup
	// ------------

	IF flagDumperHelpDisplayed = TRUE
		CLEAR_HELP
	ENDIF


	// Animation Clearup
	// -----------------


	// Models Clearup
	// --------------
	MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
	MARK_MODEL_AS_NO_LONGER_NEEDED DOZER
	MARK_MODEL_AS_NO_LONGER_NEEDED DUMPER
	MARK_MODEL_AS_NO_LONGER_NEEDED DYN_QUARRYROCK02
	MARK_MODEL_AS_NO_LONGER_NEEDED DEAD_TIED_COP
	MARK_MODEL_AS_NO_LONGER_NEEDED DYN_QUARRYROCK03
	MARK_MODEL_AS_NO_LONGER_NEEDED COPBIKE
	MARK_MODEL_AS_NO_LONGER_NEEDED CR_AMMOBOX
	MARK_MODEL_AS_NO_LONGER_NEEDED KB_BARREL
	MARK_MODEL_AS_NO_LONGER_NEEDED STREAK
	MARK_MODEL_AS_NO_LONGER_NEEDED STREAKC
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYBE
	MARK_MODEL_AS_NO_LONGER_NEEDED CR_GUNCRATE
	MARK_MODEL_AS_NO_LONGER_NEEDED CM_BOX
	MARK_MODEL_AS_NO_LONGER_NEEDED BLACKBAG2
	MARK_MODEL_AS_NO_LONGER_NEEDED DYN_WOODPILE
	MARK_MODEL_AS_NO_LONGER_NEEDED DYN_WOODPILE2


	// === RESTORE ENVIRONMENT SETTINGS ===
	// ------------------------------------
	SET_PED_DENSITY_MULTIPLIER	1.0
	SET_CAR_DENSITY_MULTIPLIER	1.0
	SET_WANTED_MULTIPLIER		1.0
	SWITCH_EMERGENCY_SERVICES	ON
	SWITCH_RANDOM_TRAINS		ON


	// Restore switched off road
	// -------------------------
	IF flagRoadsSwitchedOff = TRUE
		// Reactivate roads
		SWITCH_ROADS_BACK_TO_ORIGINAL 256.6312 734.7498 4.0053 343.9576 765.6713 6.4852
		SWITCH_ROADS_BACK_TO_ORIGINAL 291.2253 762.6765 4.0625 354.4869 784.4223 6.6101
		SWITCH_ROADS_BACK_TO_ORIGINAL -149.9865 460.0670 4.9719 -119.7325 530.4155 12.4813
		SWITCH_ROADS_BACK_TO_ORIGINAL -100.9858 1190.3802 16.1736 97.0928 1205.2023 19.9344
		SWITCH_ROADS_BACK_TO_ORIGINAL -124.9028 1209.5811 14.6519 -108.1954 1243.8207 19.8554
		SWITCH_ROADS_BACK_TO_ORIGINAL -113.2273 1246.4767 11.3781 -84.1825 1268.7506 17.5621
	ENDIF


	// Make sure the mobile phone doesn't ring immediately after a mission
	// -------------------------------------------------------------------
	GET_GAME_TIMER timer_mobile_start


	// Return the oddjob 'title over fade' status back to default
	DRAW_ODDJOB_TITLE_BEFORE_FADE TRUE


	// Housekeeping
	// ------------
	flag_player_on_mission	= FALSE
	flag_player_on_oddjob	= FALSE
	MISSION_HAS_FINISHED

RETURN
	 

}