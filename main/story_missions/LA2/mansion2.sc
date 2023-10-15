MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
//
//			   MISSION NAME : Mansion2
//		        DESCRIPTION : Enter aircraft carrier, steal harrier, destroy spy boats
//
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

{
// *************************************************************************************************************
//								GENERAL INITIALISATION - Every level requires this
// *************************************************************************************************************


SCRIPT_NAME Mansio2

// Mission Start stuff
GOSUB Mansion2_Mission_Start

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB Mansion2_Mission_Failed
ENDIF

GOSUB Mansion2_Mission_Cleanup

MISSION_END


// Global Setup
Mansion2_Mission_Start:

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
VAR_INT 	Mansion2_view_debug[8]	// GLOBAL (for output)
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

// ...Mansion2 Stages
CONST_INT	MANSION2_STAGE_INITIALISATION					0
CONST_INT	MANSION2_STAGE_INITIAL_CUTSCENE_PART1			1
CONST_INT	MANSION2_STAGE_INITIAL_CUTSCENE_PART2			2
CONST_INT	MANSION2_STAGE_HEAD_TOWARDS_CARRIER				3
CONST_INT	MANSION2_STAGE_ENTER_AIRCRAFT_CARRIER			4
CONST_INT	MANSION2_STAGE_ENTER_HARRIER					5
CONST_INT	MANSION2_STAGE_DESTROY_SPY_BOATS				6
CONST_INT	MANSION2_STAGE_LAND_HARRIER						7
CONST_INT	MANSION2_STAGE_COMPLETE							8
// ...Toreno stuff
CONST_INT	MANSION2_TORENO_SPECIAL_CHARACTER_SLOT			1
// ...car recordings
CONST_INT	MANSION2_CAR_RECORDING_TORENO_CAR				208
CONST_INT	MANSION2_CAR_RECORDING_HARRIER_TAKEOFF			209
CONST_INT	MANSION2_CAR_RECORDING_HARRIER_LANDING			211		//210
// ...reasons for failure
CONST_INT	MANSION2_FAILED_UNKNOWN							0
CONST_INT	MANSION2_FAILED_TORENO_CAR_DESTROYED			1
CONST_INT	MANSION2_FAILED_TORENO_DEAD						2
CONST_INT	MANSION2_FAILED_NO_PLANES_LEFT					3
CONST_INT	MANSION2_FAILED_HARRIER_DEAD					4
CONST_INT	MANSION2_FAILED_NOT_IN_CARRIER					5
CONST_INT	MANSION2_FAILED_LEFT_CARRIER					6
// ...reasons for detection
CONST_INT	MANSION2_DETECTED_UNKNOWN						0
CONST_INT	MANSION2_DETECTED_TOO_MUCH_NOISE				1
CONST_INT	MANSION2_DETECTED_BY_MECHANIC					2
CONST_INT	MANSION2_DETECTED_BY_GUARD						3
CONST_INT	MANSION2_DETECTED_DEAD_BODY_BY_MECHANIC			4
CONST_INT	MANSION2_DETECTED_DEAD_BODY_BY_GUARD			5
CONST_INT	MANSION2_DETECTED_EXPLOSION						6
// ...plane IDs
CONST_INT	MANSION2_PLANE_INSIDE							0
CONST_INT	MANSION2_PLANE_BIG_LIFT							1
CONST_INT	MANSION2_PLANE_SMALL_LIFT						2
CONST_INT	MANSION2_PLANE_TAKEOFF							3
// ...guard IDs
CONST_INT	MANSION2_GUARD_BOAT_DRIVER						0
CONST_INT	MANSION2_GUARD_BOAT_BACK						1
CONST_INT	MANSION2_GUARD_BOAT_FRONT						2
CONST_INT	MANSION2_GUARD_PILOT_BIG_LIFT					3
CONST_INT	MANSION2_GUARD_PILOT_SMALL_LIFT					4
CONST_INT	MANSION2_GUARD_HARRIER_GUIDE					5
// -----
CONST_INT	MANSION2_MAX_GUARDS								6	// above Guard number + 1
// ...mechanic IDs
CONST_INT	MANSION2_MECHANIC_UPSTAIRS						0
CONST_INT	MANSION2_MECHANIC_DOWNSTAIRS					1
// -----
CONST_INT	MANSION2_MAX_MECHANICS							2	// above Mechanic number + 1
// ...forklift IDs
CONST_INT	MANSION2_FORKLIFT_DOWNSTAIRS					0
CONST_INT	MANSION2_FORKLIFT_UPSTAIRS						1
CONST_INT	MANSION2_FORKLIFT_MISSILE						2
// -----
CONST_INT	MANSION2_MAX_FORKLIFTS							3	// above Forklift number + 1
// ...Alarm IDs
CONST_INT	MANSION2_ALARM_DOWNSTAIRS						0
CONST_INT	MANSION2_ALARM_UPSTAIRS_HARRIERS				1
CONST_INT	MANSION2_ALARM_UPSTAIRS_CONTROL_ROOM			2
// -----
CONST_INT	MANSION2_MAX_ALARMS								3	// above Alarm number + 1
// ...guard AI
CONST_INT	MANSION2_GUARDAI_DEAD							-2
CONST_INT	MANSION2_GUARDAI_NOT_INITIALISED				-1
CONST_INT	MANSION2_GUARDAI_STEER_BOAT_TO_SHIP				0
CONST_INT	MANSION2_GUARDAI_STEERING_BOAT_TO_SHIP			1
CONST_INT	MANSION2_GUARDAI_STEER_BOAT_INTO_SHIP			2
CONST_INT	MANSION2_GUARDAI_STEERING_BOAT_INTO_SHIP		3
CONST_INT	MANSION2_GUARDAI_BOAT_MOORED_IN_SHIP			4
CONST_INT	MANSION2_GUARDAI_ACTIVATION						5
CONST_INT	MANSION2_GUARDAI_WALK_HOME						6
CONST_INT	MANSION2_GUARDAI_WALKING_HOME					7
CONST_INT	MANSION2_GUARDAI_CHAT							8
CONST_INT	MANSION2_GUARDAI_CHATTING						9
CONST_INT	MANSION2_GUARDAI_SIT							10
CONST_INT	MANSION2_GUARDAI_WANDER							11
CONST_INT	MANSION2_GUARDAI_WANDERING						12
CONST_INT	MANSION2_GUARDAI_WANDER_STOPPED					13
CONST_INT	MANSION2_GUARDAI_EXIT_PLANE						14
CONST_INT	MANSION2_GUARDAI_EXITING_PLANE					15
CONST_INT	MANSION2_GUARDAI_WATCH_PLANE					16
CONST_INT	MANSION2_GUARDAI_WATCHING_PLANE					17
CONST_INT	MANSION2_GUARDAI_TELEPORT						18
CONST_INT	MANSION2_GUARDAI_GOTO_ALERT_POSITION			19
CONST_INT	MANSION2_GUARDAI_GOING_TO_ALERT_POSITION		20
CONST_INT	MANSION2_GUARDAI_ORIENTATE_AT_ALERT_POSITION	21
CONST_INT	MANSION2_GUARDAI_AT_ALERT_POSITION				22
CONST_INT	MANSION2_GUARDAI_ATTACK							23
CONST_INT	MANSION2_GUARDAI_RUN_ATTACK						24
CONST_INT	MANSION2_GUARDAI_RUNNING_ATTACK					25
CONST_INT	MANSION2_GUARDAI_ATTACKING						26
CONST_INT	MANSION2_GUARDAI_TICKING_OVER					27
// ..mechanic AI
CONST_INT	MANSION2_MECHANICAI_DEAD						-2
CONST_INT	MANSION2_MECHANICAI_NOT_INITIALISED				-1
CONST_INT	MANSION2_MECHANICAI_ACTIVATION					0
CONST_INT	MANSION2_MECHANICAI_ENTER_FORKLIFT				1
CONST_INT	MANSION2_MECHANICAI_ENTERING_FORKLIFT			2
CONST_INT	MANSION2_MECHANICAI_FORKLIFT_WANDER				3
CONST_INT	MANSION2_MECHANICAI_FORKLIFT_WANDERING			4
CONST_INT	MANSION2_MECHANICAI_FORKLIFT_WANDER_STOPPED		5
CONST_INT	MANSION2_MECHANICAI_CHAT						6
CONST_INT	MANSION2_MECHANICAI_CHATTING					7
CONST_INT	MANSION2_MECHANICAI_GOTO_COWER_POSITION			8
CONST_INT	MANSION2_MECHANICAI_GOING_TO_COWER_POSITION		9
CONST_INT	MANSION2_MECHANICAI_AT_COWER_POSITION			10
CONST_INT	MANSION2_MECHANICAI_CHANGE_COWER_POSITION		11
CONST_INT	MANSION2_MECHANICAI_GOTO_ALARM					12
CONST_INT	MANSION2_MECHANICAI_GOING_TO_ALARM				13
CONST_INT	MANSION2_MECHANICAI_TRIGGER_ALARM				14
CONST_INT	MANSION2_MECHANICAI_TRIGGERING_ALARM			15
CONST_INT	MANSION2_MECHANICAI_TICKING_OVER				16
// ...General
CONST_INT	MANSION2_PERSIST								-2
CONST_INT	MANSION2_MAX_PLANES								4
CONST_INT	MANSION2_MAX_ENEMY_PLANES						3
CONST_INT	MANSION2_MAX_SPY_SHIPS							4
CONST_INT	MANSION2_TIME_BEFORE_LOWER_BACK_DOOR_sec		60
CONST_FLOAT	MANSION2_INFORM_PEDS_RANGE_m					20.0
CONST_FLOAT	MANSION2_REACT_TO_DEATH_RANGE_m					10.0
//CONST_FLOAT	MANSION2_RESPECTED_FRIENDS_DISTANCE_m			60.0
//CONST_INT	MANSION2_NUMBER_OF_RESPECTED_FRIENDS			4



// Speech
CONST_INT	MANSION2_MAX_CONVERSATION_LINES					13
CONST_INT	MANSION2_DELAY_BEFORE_NEXT_CONVERSATION_msec	120
// ...audio slot allocations
CONST_INT	MANSION2_TOTAL_AUDIO_SLOTS						2	// 3 available: generally 1 and 2 for speech; 3 for SFX
CONST_INT	MANSION2_AUDIO_SLOT_ARRAY_SIZE	 				3	// MANSION2_TOTAL_AUDIO_SLOTS+1 (slots numbered 1,2,3: so array pos0 wasted)
CONST_INT	MANSION2_FIRST_SPEECH_SLOT						1
CONST_INT	MANSION2_LAST_SPEECH_SLOT  						2
CONST_INT	MANSION2_SFX_SLOT								3
// ...audio slot status
CONST_INT	MANSION2_AUDIO_SLOT_FREE						0
CONST_INT	MANSION2_AUDIO_SLOT_LOADING						1
CONST_INT	MANSION2_AUDIO_SLOT_LOADED						2
CONST_INT	MANSION2_AUDIO_SLOT_PLAYING						3
// ...conversation status
CONST_INT	MANSION2_CONVERSATION_STATUS_NONE   			0
CONST_INT	MANSION2_CONVERSATION_STATUS_PREPARED			1
CONST_INT	MANSION2_CONVERSATION_STATUS_PLAYING			2
CONST_INT	MANSION2_CONVERSATION_STATUS_INTERRUPTED		3
CONST_INT	MANSION2_CONVERSATION_STATUS_FINISHED			4
// ...speech conversations
CONST_INT	MANSION2_CONVERSATION_NONE						0
CONST_INT	MANSION2_CONVERSATION_CARL_MEETS_TORENO			1
CONST_INT	MANSION2_CONVERSATION_INSTRUCTIONS				2
CONST_INT	MANSION2_CONVERSATION_GOTO_CARRIER				3
CONST_INT	MANSION2_CONVERSATION_SNEAK_INTO_CARRIER		4
CONST_INT	MANSION2_CONVERSATION_STOLEN_HARRIER			5
CONST_INT	MANSION2_CONVERSATION_VAPOURISED				6
CONST_INT	MANSION2_CONVERSATION_DESTROY_FLOTILLA			7
CONST_INT	MANSION2_CONVERSATION_FLOTILLA_DESTROYED		8



// Audio variables
LVAR_INT		nSpeechCurrentSlot
LVAR_INT		nConversationStatus
LVAR_INT		nCurrentConversationID
LVAR_INT		nRequiredConversationID
LVAR_INT		nCurrentConversationLine
LVAR_INT		nCurrentMaxConversationLines
LVAR_INT		nNextPreloadConversationLine
LVAR_INT		bitsConversationsPlayed
LVAR_INT		timerDelayBeforeNextConversation
LVAR_INT		flagDisplaySpeechSubtitle
LVAR_INT		nAudioSlotStatus[MANSION2_AUDIO_SLOT_ARRAY_SIZE]
LVAR_INT		nConversationSequenceSpeechID[MANSION2_MAX_CONVERSATION_LINES]
LVAR_TEXT_LABEL	tlConversationSequenceSubtitle[MANSION2_MAX_CONVERSATION_LINES]
// ...clear audio variables
nSpeechCurrentSlot					= MANSION2_FIRST_SPEECH_SLOT
nConversationStatus					= MANSION2_CONVERSATION_STATUS_NONE
nCurrentConversationID				= MANSION2_CONVERSATION_NONE
nRequiredConversationID				= MANSION2_CONVERSATION_NONE
nCurrentConversationLine			= -1
nCurrentMaxConversationLines		= 0
nNextPreloadConversationLine		= 0
timerDelayBeforeNextConversation	= 0
bitsConversationsPlayed				= MANSION2_CONVERSATION_NONE
flagDisplaySpeechSubtitle			= TRUE

REPEAT MANSION2_AUDIO_SLOT_ARRAY_SIZE nLoop
	nAudioSlotStatus[nLoop] = MANSION2_AUDIO_SLOT_FREE
ENDREPEAT



// Mission Specific Variables
// Integer Variables
// ...chars 				(char)
LVAR_INT	charToreno
LVAR_INT	charGuards[MANSION2_MAX_GUARDS]
LVAR_INT	charMechanics[MANSION2_MAX_MECHANICS]
// ...cars 					(car)
LVAR_INT	carToreno
LVAR_INT	carSpeedboat
LVAR_INT	carPlanes[MANSION2_MAX_PLANES]
LVAR_INT	carPlayerPlane
LVAR_INT	carEnemyPlanes[MANSION2_MAX_ENEMY_PLANES]
LVAR_INT	carSpyShips[MANSION2_MAX_SPY_SHIPS]
LVAR_INT	carEnemyBoat
LVAR_INT	carForklifts[MANSION2_MAX_FORKLIFTS]
// ...objects 				(object)
LVAR_INT	objectCarrierBigLift
LVAR_INT	objectCarrierSmallLift
LVAR_INT	objectCarrierBackDoor
LVAR_INT	objectMissile
LVAR_INT	objectAlarms[MANSION2_MAX_ALARMS]
// ...blips 				(blip)
LVAR_INT	blipSpeedboat
LVAR_INT	blipDestination
LVAR_INT	blipEnemyPlanes[MANSION2_MAX_ENEMY_PLANES]
LVAR_INT	blipSpyShips[MANSION2_MAX_SPY_SHIPS]
LVAR_INT	blipGuards[MANSION2_MAX_GUARDS]
LVAR_INT	blipPlayerPlane
// ...pickups 				(pickup)
// ...fx systems 			(fx)
// ...decision makers		(dm)
LVAR_INT	dmEmpty
//LVAR_INT	dmWeak
LVAR_INT	dmStealth
LVAR_INT	dmTough
// ...AI Status				(ai)
LVAR_INT	aiGuards[MANSION2_MAX_GUARDS]
LVAR_INT	aiMechanics[MANSION2_MAX_MECHANICS]
// ...general status		(status)
LVAR_INT	statusHarrierFlyPast
LVAR_INT	statusHarrierTakeoff
LVAR_INT	statusHarrierLanding
// ...Timers				(timer)
LVAR_INT	timerCutscene
//LVAR_INT	timerSpyShipsBlips
LVAR_INT	timerLowerBackDoor
LVAR_INT	timerFlyPast
LVAR_INT	timerTakeoff
LVAR_INT	timerGuardsAI[MANSION2_MAX_GUARDS]
LVAR_INT	timerMechanicsAI[MANSION2_MAX_MECHANICS]
LVAR_INT	timerHelpText
LVAR_INT	timerCarlLookAtToreno
LVAR_INT	timerTorenoLookAtCarl
// ...Counters				(count)
// ...mission specific		(n)
LVAR_INT	nMechanicDestination[MANSION2_MAX_MECHANICS]
LVAR_INT	nMechanicCowerHealth[MANSION2_MAX_MECHANICS]
LVAR_INT	nReasonForDetection


// Text Label Variables


// Float Variables
// ...area variables 		(xlo, ylo, zlo, xhi, yhi, zhi)
LVAR_FLOAT	xloLand		yloLand		zloLand		xhiLand		yhiLand		zhiLand
// ...position variables	(xpos, ypos, zpos)
LVAR_FLOAT	xposDestination			yposDestination			zposDestination


// Boolean Variables
// ...flags					(flag)
LVAR_INT	flagTorenoCarRecordingPlaybackStarted
LVAR_INT	flagSpeedboatAlive
LVAR_INT	flagBeenInSpeedboat
LVAR_INT	flagBeenCloseToCarrier
LVAR_INT	flagBeenCloseToEnemyBoat
LVAR_INT	flagDisplayedGotoCarrierText
LVAR_INT	flagDisplayedControlRoomWarning
LVAR_INT	flagDisplayedFlyingHelp
LVAR_INT	flagDisplayedFlyingHelp2
LVAR_INT	flagDisplayedDogfightHelp
LVAR_INT	flagDisplayedDogfightHelp2
LVAR_INT	flagAllowSpyShipHelpText
LVAR_INT	flagDisplayedSpyShipHelp
LVAR_INT	flagDisplayedSpyShipHelp2
LVAR_INT	flagDisplayedSpyShipHelp3
LVAR_INT	flagReachedControlRoom
LVAR_INT	flagEnemyPlanesAlive[MANSION2_MAX_ENEMY_PLANES]
LVAR_INT	flagPlanesOnCarrier[MANSION2_MAX_PLANES]
LVAR_INT	flagLowerSmallLift
LVAR_INT	flagRaiseLifts
LVAR_INT	flagPlayerFlyingHarrier
LVAR_INT	flagShowingSmallLiftCamera
LVAR_INT	flagShowingBigLiftCamera
LVAR_INT	flagEnemyPlanesCreatedInAir
//LVAR_INT	flagFlashSpyShipsBlips
//LVAR_INT	flagSpyShipsBlipsFlashOn
LVAR_INT	flagSpyShipsAlive[MANSION2_MAX_SPY_SHIPS]
LVAR_INT	flagAllSpyShipsDead
LVAR_INT	flagAllHarriersDead
LVAR_INT	flagRaiseBackDoor
LVAR_INT	flagLowerBackDoor
LVAR_INT	flagLowerBackDoorQuickly
LVAR_INT	flagReorganisedPlanes
LVAR_INT	flagEnemyBoatMoving
LVAR_INT	flagPlayerInCarrier
LVAR_INT	flagPlayedHarrierTakeoff
LVAR_INT	flagPlayedHarrierLanding
LVAR_INT	flagDisplayedLandingHelp
LVAR_INT	flagCarrierOnAlert
LVAR_INT	flagDisplayedOnAlertText
LVAR_INT	flagGuardsAllowAlert[MANSION2_MAX_GUARDS]
LVAR_INT	flagGuardsOnAlert[MANSION2_MAX_GUARDS]
LVAR_INT	flagMechanicsOnAlert[MANSION2_MAX_MECHANICS]
LVAR_INT	flagAllowMechanicSensesUpdates
LVAR_INT	flagPlayerFiringGun
LVAR_INT	flagPlayerFiringSilencedGun
LVAR_INT	flagCarrierBackDoorAudioPlaying
LVAR_INT	flagCarrierBigLiftAudioPlaying
LVAR_INT	flagCarrierSmallLiftAudioPlaying
LVAR_INT	flagCarrierOnAlertAudioPlaying
LVAR_INT	flagLeftCarrierCutscenePlayed
LVAR_INT	flagGuardsImmediateActivation
LVAR_INT	flagTorenoCarDeleted
LVAR_INT	flagCarlLookingAtToreno
LVAR_INT	flagTorenoLookingAtCarl
LVAR_INT	flagAllowLookAt
LVAR_INT	flagDisplayedGotoHarrierText
LVAR_INT	flagInForklift
// ...loaded flags			<loaded>
// Clear Loaded Flags


// Clear timers


// Clear important flags
flagTorenoCarRecordingPlaybackStarted	= FALSE
flagSpeedboatAlive						= FALSE
flagBeenInSpeedboat						= FALSE
flagBeenCloseToCarrier					= FALSE
flagBeenCloseToEnemyBoat				= FALSE
flagDisplayedGotoCarrierText			= FALSE
flagDisplayedControlRoomWarning			= FALSE
flagDisplayedFlyingHelp					= FALSE
flagDisplayedFlyingHelp2				= FALSE
flagDisplayedDogfightHelp				= FALSE
flagDisplayedDogfightHelp2				= FALSE
flagAllowSpyShipHelpText				= FALSE
flagDisplayedSpyShipHelp				= FALSE
flagDisplayedSpyShipHelp2				= FALSE
flagDisplayedSpyShipHelp3				= FALSE
flagReachedControlRoom					= FALSE
flagLowerSmallLift						= FALSE
flagRaiseLifts							= FALSE
flagPlayerFlyingHarrier					= FALSE
flagShowingSmallLiftCamera				= FALSE
flagShowingBigLiftCamera				= FALSE
flagEnemyPlanesCreatedInAir				= FALSE
//flagFlashSpyShipsBlips					= FALSE
//flagSpyShipsBlipsFlashOn				= FALSE
flagAllSpyShipsDead						= FALSE
flagAllHarriersDead						= FALSE
flagReorganisedPlanes					= FALSE
flagRaiseBackDoor						= FALSE
flagLowerBackDoor						= FALSE
flagLowerBackDoorQuickly				= FALSE
flagEnemyBoatMoving						= FALSE
flagPlayerInCarrier						= FALSE
flagPlayedHarrierTakeoff				= FALSE
flagPlayedHarrierLanding				= FALSE
flagDisplayedLandingHelp				= FALSE
flagCarrierOnAlert						= FALSE
flagDisplayedOnAlertText				= FALSE
flagAllowMechanicSensesUpdates			= FALSE
flagPlayerFiringGun						= FALSE
flagPlayerFiringSilencedGun				= FALSE
flagCarrierBackDoorAudioPlaying			= FALSE
flagCarrierBigLiftAudioPlaying			= FALSE
flagCarrierSmallLiftAudioPlaying		= FALSE
flagCarrierOnAlertAudioPlaying			= FALSE
flagLeftCarrierCutscenePlayed			= FALSE
flagGuardsImmediateActivation			= FALSE
flagTorenoCarDeleted					= FALSE
flagCarlLookingAtToreno					= FALSE
flagTorenoLookingAtCarl					= FALSE
flagAllowLookAt							= FALSE
flagDisplayedGotoHarrierText			= FALSE
flagInForklift							= FALSE



// ***** FAKE ENTITY CREATION TO FOOL THE COMPILER *****
// The compiler just needs to verify there is a CREATE_ before usage
IF m_stage = -99
	
	WRITE_DEBUG SHOULD_NEVER_BE_IN_FAKE_ENTITY_CREATION

	// Entities
	// ...cars
	CREATE_CAR WASHING	0.0 0.0 0.0 carToreno
	CREATE_CAR SQUALO	0.0 0.0 0.0 carSpeedboat
	CREATE_CAR HYDRA	0.0 0.0 0.0 carPlayerPlane
	CREATE_CAR LAUNCH	0.0 0.0 0.0 carEnemyBoat

	REPEAT MANSION2_MAX_PLANES nLoop
		CREATE_CAR HYDRA	0.0 0.0 0.0 carPlanes[nLoop]
	ENDREPEAT

	REPEAT MANSION2_MAX_ENEMY_PLANES nLoop
		CREATE_CAR HYDRA	0.0 0.0 0.0 carEnemyPlanes[nLoop]
	ENDREPEAT

	REPEAT MANSION2_MAX_SPY_SHIPS nLoop
		CREATE_CAR TROPIC	0.0 0.0 0.0 carSpyShips[nLoop]
	ENDREPEAT

	REPEAT MANSION2_MAX_FORKLIFTS nLoop
		CREATE_CAR FORKLIFT	0.0 0.0 0.0 carForklifts[nLoop]
	ENDREPEAT

	// ...chars
	CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 0.0 0.0 0.0 charToreno

	REPEAT MANSION2_MAX_GUARDS nLoop
		CREATE_CHAR PEDTYPE_MISSION2 ARMY 0.0 0.0 0.0 charGuards[nLoop]
	ENDREPEAT

	REPEAT MANSION2_MAX_MECHANICS nLoop
		CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 0.0 0.0 0.0 charMechanics[nLoop]
	ENDREPEAT

	// ...objects
	CREATE_OBJECT_NO_OFFSET CARRIER_LIFT1_SFSE	0.0 0.0 0.0 objectCarrierBigLift
	CREATE_OBJECT_NO_OFFSET CARRIER_LIFT2_SFSE	0.0 0.0 0.0 objectCarrierSmallLift
	CREATE_OBJECT_NO_OFFSET CARRIER_DOOR_SFSE	0.0 0.0 0.0 objectCarrierBackDoor
	CREATE_OBJECT			MISSILE_07_SFXR		0.0 0.0 0.0 objectMissile

	REPEAT MANSION2_MAX_ALARMS nLoop
		CREATE_OBJECT KMB_KEYPAD 0.0 0.0 0.0 objectAlarms[nLoop]
	ENDREPEAT

	// ...blips
	ADD_BLIP_FOR_CAR carSpeedboat	blipSpeedboat
	ADD_BLIP_FOR_CAR carPlayerPlane	blipPlayerPlane
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0	blipDestination

	REPEAT MANSION2_MAX_ENEMY_PLANES nLoop
		ADD_BLIP_FOR_CAR carEnemyPlanes[nLoop] blipEnemyPlanes[nLoop]
	ENDREPEAT

	REPEAT MANSION2_MAX_SPY_SHIPS nLoop
		ADD_BLIP_FOR_CAR carSpyShips[nLoop] blipSpyShips[nLoop]
	ENDREPEAT

ENDIF

// Mission Text
LOAD_MISSION_TEXT MAN_2

// Force the weather to be rainy because it disguises the gradual displaying of the
// bridge and skyscrapers that occurs when the camera points towards the speedboat
FORCE_WEATHER_NOW WEATHER_RAINY_COUNTRYSIDE

// Intro Cutscene
GOSUB Mansion2_Intro_Cutscene

// Load Char Mission Decision Makers
// NOTE: Do it before the Initialisation because they make the game pause. Done here, the pause is hidden by a fade.
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY	dmEmpty
//LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_WEAK	dmWeak
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_STEAL	dmStealth
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH	dmTough

// Decision Maker Mods
CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE dmStealth EVENT_SOUND_QUIET

// Mission Initialisation
GOSUB Mansion2_Initialisation


// *************************************************************************************************************
//								                 MISSION LOOP
// *************************************************************************************************************
Mansion2_Mission_Loop:

	WAIT 0

	// Special shortcut for Craig F to test all missions in order
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		m_passed = 1
		GOTO Mansion2_End_Of_Main_Loop
	ENDIF

	// Debug Stuff
	GOSUB Mansion2_Debug_Tools
	GOSUB Mansion2_Debug_Shortcuts

	IF m_quit = 1
	OR m_pause = 1
		GOTO Mansion2_End_Of_Main_Loop
	ENDIF

	// Housekeeping
	GOSUB Mansion2_Frame_Counter
	GOSUB Mansion2_Additional_Timers

	// Special conditions
	IF IS_CHAR_DEAD scplayer
		m_failed = 1
		GOTO Mansion2_End_Of_Main_Loop
	ENDIF		


	// Mission Stage processing
	// *** INITIALISATION NOW TAKES PLACE BEFORE THE MAIN LOOP ***
	IF m_stage = 0
		WRITE_DEBUG STAGE_SHOULD_NEVER_BE_0_IN_MAIN_LOOP
	ENDIF


	SWITCH m_stage
		CASE MANSION2_STAGE_INITIAL_CUTSCENE_PART1
			GOSUB Mansion2_Initial_Cutscene_Part1
			BREAK

		CASE MANSION2_STAGE_INITIAL_CUTSCENE_PART2
			GOSUB Mansion2_Initial_Cutscene_Part2
			BREAK

		CASE MANSION2_STAGE_HEAD_TOWARDS_CARRIER
			GOSUB Mansion2_Head_Towards_Carrier
			BREAK

		CASE MANSION2_STAGE_ENTER_AIRCRAFT_CARRIER
			GOSUB Mansion2_Enter_Aircraft_Carrier
			BREAK

		CASE MANSION2_STAGE_ENTER_HARRIER
			GOSUB Mansion2_Enter_Harrier
			BREAK

		CASE MANSION2_STAGE_DESTROY_SPY_BOATS
			GOSUB Mansion2_Destroy_Spy_Boats
			BREAK

		CASE MANSION2_STAGE_LAND_HARRIER
			GOSUB Mansion2_Land_Harrier
			BREAK

		// Passed
		CASE MANSION2_STAGE_COMPLETE
			m_passed = TRUE
			BREAK
	ENDSWITCH


	// Continuous update methods and event checking

	// ...speech
	GOSUB Mansion2_Update_Speech







// End of Main Loop
// ***** DON'T CHANGE *****
Mansion2_End_Of_Main_Loop:

	IF m_quit = 0
		IF m_failed = 0
			IF m_passed = 0																 
				// Restart main loop
				GOTO Mansion2_Mission_Loop 
			ELSE
				// Mission passed
				GOSUB Mansion2_Mission_Passed
				RETURN
			ENDIF
		ELSE
			// Mission failed
			GOSUB Mansion2_Mission_Failed
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
// STAGE: Initial Cutscene Part1 (Carl and Toreno get in the car and start to drive)

Mansion2_Initial_Cutscene_Part1:

   	// Initialisation
	// --------------

   	IF m_goals = 0
		// Set up Toreno's Car
		CUSTOM_PLATE_FOR_NEXT_CAR WASHING &_OMEGA__
		CREATE_CAR WASHING 1248.6813 -815.9187 83.1484 carToreno
		CHANGE_CAR_COLOUR carToreno 84 84
		SET_CAR_HEADING	carToreno 180.0
		SET_RADIO_CHANNEL RS_COUNTRY

		// Set up Toreno
		CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 1250.8958 -802.7902 83.1484 charToreno
		SET_CHAR_HEADING charToreno 180.0

		// Make Carl and Toreno enter the car
		SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
		TASK_ENTER_CAR_AS_DRIVER charToreno carToreno 10000

		SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
		TASK_ENTER_CAR_AS_PASSENGER scplayer carToreno 10000 0

		// Set up the cutscene camera
		SET_FIXED_CAMERA_POSITION 1246.4969 -822.7375 84.7111 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 1246.7848 -821.7800 84.6972 JUMP_CUT

		// Prepare and Play the Carl meets Toreno conversation
		nRequiredConversationID = MANSION2_CONVERSATION_CARL_MEETS_TORENO
		GOSUB Mansion2_Conversation_Command_Prepare

		SET_CAR_DENSITY_MULTIPLIER 0.0

		// Fade in
		DO_FADE 1000 FADE_IN

		// Control flags
		flagTorenoCarRecordingPlaybackStarted	= FALSE
		flagTorenoCarDeleted					= FALSE

		// 'Look at each other' control variables
		flagCarlLookingAtToreno	= FALSE
		flagTorenoLookingAtCarl	= FALSE
		flagAllowLookAt			= TRUE
		timerCarlLookAtToreno	= 0
		timerTorenoLookAtCarl	= 0


		// Cutscene control variables (cutscene starts now)
		flagCutscenePlaying				= TRUE
		flagCleaningUpSkippedCutscene	= FALSE
		flagSkipCutscene				= FALSE

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------

	IF IS_CAR_DEAD carToreno
		m_failed = TRUE
		m_fail_reason = MANSION2_FAILED_TORENO_CAR_DESTROYED
		RETURN
	ENDIF


	IF IS_CHAR_DEAD charToreno
		m_failed = TRUE
		m_fail_reason = MANSION2_FAILED_TORENO_DEAD
		RETURN
	ENDIF


	// Subgoals
	// --------

	// Check if the speech is ready to play
	IF m_goals = 1
		IF flagSkipCutscene = TRUE
			m_goals++
		ELSE
			IF NOT nCurrentConversationID = MANSION2_CONVERSATION_CARL_MEETS_TORENO
				nRequiredConversationID = MANSION2_CONVERSATION_CARL_MEETS_TORENO
				GOSUB Mansion2_Conversation_Command_Prepare
			ENDIF

			IF nCurrentConversationID = MANSION2_CONVERSATION_CARL_MEETS_TORENO
				GOSUB Mansion2_Conversation_Command_Play

				m_goals++
			ENDIF
		ENDIF
	ENDIF

	// Check if Carl and Toreno have entered the car
	IF m_goals = 2
		IF flagSkipCutscene = TRUE
			m_goals++
		ELSE
			IF IS_CHAR_IN_CAR scplayer carToreno
			AND IS_CHAR_IN_CAR charToreno carToreno
				// Play Car recording of Toreno's car driving from the Mansion
				START_PLAYBACK_RECORDED_CAR carToreno MANSION2_CAR_RECORDING_TORENO_CAR
				flagTorenoCarRecordingPlaybackStarted = TRUE

				// Swap to a new camera
				LOAD_SCENE_IN_DIRECTION 1239.2856 -836.2535 83.1477 27.7504
				SET_FIXED_CAMERA_POSITION 1250.1882 -849.5969 93.6161 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1249.6897 -848.7883 93.3037 JUMP_CUT

				// Cancel 'look at'
				IF flagCarlLookingAtToreno = TRUE
					CLEAR_LOOK_AT scplayer
					flagCarlLookingAtToreno = FALSE
					timerCarlLookAtToreno = 0
				ENDIF

				IF flagTorenoLookingAtCarl = TRUE
					CLEAR_LOOK_AT charToreno
					flagTorenoLookingAtCarl = FALSE
					timerTorenoLookAtCarl = 0
				ENDIF

				flagAllowLookAt = FALSE

				// Start a timer before the next part of the cutscene
				timerCutscene = m_mission_timer + 1000
					
				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Wait for the cutscene timer to end
	IF m_goals = 3
		IF flagSkipCutscene = TRUE
			m_goals++
		ELSE
			IF timerCutscene < m_mission_timer
				// Start Fade out
				DO_FADE 2000 FADE_OUT
				timerCutscene = m_mission_timer + 2000

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Wait for the fade out to finish
	IF m_goals = 4
		IF flagSkipCutscene = TRUE
			m_goals = 50
		ELSE
			IF timerCutscene < m_mission_timer
			AND NOT GET_FADING_STATUS
				// Stop the car recording playback
				STOP_PLAYBACK_RECORDED_CAR carToreno
				flagTorenoCarRecordingPlaybackStarted = FALSE

				// Cutscene wasn't skipped
				flagCutscenePlaying = FALSE
				
				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// RETURN HERE IF CUTSCENE SKIPPED
	IF m_goals = 5
		// Remove the car recording
		REMOVE_CAR_RECORDING MANSION2_CAR_RECORDING_TORENO_CAR

		// Move Toreno's car to a new location
		CLEAR_AREA -610.7587 176.8072 18.9460 20.0 FALSE
		SET_CAR_COORDINATES carToreno -610.7587 176.8072 18.9460 
		SET_CAR_HEADING	carToreno 40.0

		// Set the Scene
		LOAD_SCENE_IN_DIRECTION -709.1310 232.4660 4.6980 250.0
		SET_TIME_OF_DAY 20 00

		// Set the camera
		SET_FIXED_CAMERA_POSITION -710.0532 232.8524 4.6799 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -709.1310 232.4660 4.6980 JUMP_CUT

		timerCutscene = m_mission_timer + 1500

		m_goals++
	ENDIF

	
	IF m_goals = 6
		IF timerCutscene < m_mission_timer
			m_goals = 99
		ENDIF
	ENDIF



	// Skipped Cutscene stuff
	// ----------------------

	// Check if the cutscene was skipped
	// NOTE: Should never enter here unless skipped
	
	IF flagCutscenePlaying = TRUE
	AND IS_SKIP_CUTSCENE_BUTTON_PRESSED
	AND flagSkipCutscene = FALSE
		// Cutscene was skipped
		flagSkipCutscene = TRUE
	ENDIF


	// fade out
	IF m_goals = 50
		DO_FADE 500 FADE_OUT
		timerCutscene = m_mission_timer + 500
		flagCleaningUpSkippedCutscene = TRUE

		m_goals++	
	ENDIF


	// Clean up the skipped cutscene
	IF m_goals = 51
		IF timerCutscene < m_mission_timer
			// Warp Toreno and Carl into the car if not already in the car
			IF NOT IS_CHAR_IN_CAR scplayer carToreno
				WARP_CHAR_INTO_CAR_AS_PASSENGER scplayer carToreno 0
			ENDIF

			IF NOT IS_CHAR_IN_CAR charToreno carToreno
				TASK_WARP_CHAR_INTO_CAR_AS_DRIVER charToreno carToreno
			ENDIF

			// Stop the car recording playback
			IF flagTorenoCarRecordingPlaybackStarted = TRUE
				STOP_PLAYBACK_RECORDED_CAR carToreno
			ENDIF

			// If the speech hasn't finished then cancel it
			IF NOT IS_BIT_SET bitsConversationsPlayed MANSION2_CONVERSATION_CARL_MEETS_TORENO
				GOSUB Mansion2_Conversation_Command_Cancel
			ENDIF

			// Cancel 'look at'
			IF flagCarlLookingAtToreno = TRUE
				CLEAR_LOOK_AT scplayer
				flagCarlLookingAtToreno = FALSE
				timerCarlLookAtToreno = 0
			ENDIF

			IF flagTorenoLookingAtCarl = TRUE
				CLEAR_LOOK_AT charToreno
				flagTorenoLookingAtCarl = FALSE
				timerTorenoLookAtCarl = 0
			ENDIF

			flagAllowLookAt = FALSE

			timerCutscene = m_mission_timer + 200

			m_goals++
		ENDIF
	ENDIF


	// Resume normal service
	IF m_goals = 52
		IF timerCutscene < m_mission_timer
			flagCleaningUpSkippedCutscene	= FALSE
			flagSkipCutscene				= FALSE
			flagCutscenePlaying				= FALSE

			m_goals = 5
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	// Look At
	IF flagAllowLookAt = TRUE
		// ...Carl
		IF timerCarlLookAtToreno = 0
			// ...set up a 'look at' time
			GENERATE_RANDOM_INT_IN_RANGE 2000 3000 nTempInt
			timerCarlLookAtToreno = m_mission_timer + nTempInt
		ELSE
			// ...check if 'look at' time has expired
			IF timerCarlLookAtToreno < m_mission_timer
				// ...time to change 'look at' action
				IF flagCarlLookingAtToreno = TRUE
					// ...currently looking, so cancel
					CLEAR_LOOK_AT scplayer
					flagCarlLookingAtToreno = FALSE
				ELSE
					// ...currently not looking, so look
					TASK_LOOK_AT_CHAR scplayer charToreno MANSION2_PERSIST
					flagCarlLookingAtToreno = TRUE
				ENDIF

				timerCarlLookAtToreno = 0
			ENDIF
		ENDIF

		// ...Toreno
		IF timerTorenoLookAtCarl = 0
			// ...set up a 'look at' time
			GENERATE_RANDOM_INT_IN_RANGE 1000 4000 nTempInt
			timerTorenoLookAtCarl = m_mission_timer + nTempInt
		ELSE
			// ...check if 'look at' time has expired
			IF timerTorenoLookAtCarl < m_mission_timer
				// ...time to change 'look at' action
				IF flagTorenoLookingAtCarl = TRUE
					// ...currently looking, so cancel
					CLEAR_LOOK_AT charToreno
					flagTorenoLookingAtCarl = FALSE
				ELSE
					// ...currently not looking, so look
					TASK_LOOK_AT_CHAR charToreno scplayer MANSION2_PERSIST
					flagTorenoLookingAtCarl = TRUE
				ENDIF

				timerTorenoLookAtCarl = 0
			ENDIF
		ENDIF

		// ...stop look at when speech finished
		IF IS_BIT_SET bitsConversationsPlayed MANSION2_CONVERSATION_CARL_MEETS_TORENO
			IF flagCarlLookingAtToreno = TRUE
				CLEAR_LOOK_AT scplayer
				flagCarlLookingAtToreno = FALSE
				timerCarlLookAtToreno = 0
			ENDIF

			IF flagTorenoLookingAtCarl = TRUE
				CLEAR_LOOK_AT charToreno
				flagTorenoLookingAtCarl = FALSE
				timerTorenoLookAtCarl = 0
			ENDIF

			flagAllowLookAt = FALSE
		ENDIF
	ENDIF

	// Player is in the car so make him look at Toreno
	IF IS_CHAR_IN_CAR scplayer carToreno
		IF flagCarlLookingAtToreno = FALSE
			TASK_LOOK_AT_CHAR scplayer charToreno MANSION2_PERSIST
			flagCarlLookingAtToreno = TRUE
		ENDIF
	ENDIF


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Mansion2_Next_Stage
	ENDIF

RETURN


// ****************************************
// STAGE: Initial Cutscene Part2 (Toreno tells Carl what to do and stops at the speedboat)

Mansion2_Initial_Cutscene_Part2:

	IF flagTorenoCarDeleted = FALSE
		IF IS_CAR_DEAD carToreno
			m_failed = TRUE
			m_fail_reason = MANSION2_FAILED_TORENO_CAR_DESTROYED
			RETURN
		ENDIF

		IF IS_CHAR_DEAD charToreno
			m_failed = TRUE
			m_fail_reason = MANSION2_FAILED_TORENO_DEAD
			RETURN
		ENDIF
	ENDIF


   	// Initialisation
	// --------------

   	IF m_goals = 0
		// Create the speedboat
		CLEAR_AREA -760.8000 243.1655 -6.6249 20.0 TRUE
//		CREATE_CAR SQUALO -760.8000 243.1655 -6.6249 carSpeedboat
		CREATE_CAR SQUALO -759.4562 240.3460 -2.0951 carSpeedboat

		SET_CAR_HEADING carSpeedboat 121.0
		ANCHOR_BOAT carSpeedboat TRUE

		// Set Toreno's car in motion
		TASK_CAR_DRIVE_TO_COORD charToreno carToreno -731.1018 223.2893 1.3815 12.0 MODE_NORMAL WASHING DRIVINGMODE_PLOUGHTHROUGH

		// Fade in
		DO_FADE 1500 FADE_IN

		// Prepare and Play the Carl meets Toreno conversation
		nRequiredConversationID = MANSION2_CONVERSATION_INSTRUCTIONS
		GOSUB Mansion2_Conversation_Command_Prepare

		// 'Look at each other' control variables
		flagCarlLookingAtToreno	= FALSE
		flagTorenoLookingAtCarl	= FALSE
		flagAllowLookAt			= FALSE
		timerCarlLookAtToreno	= 0
		timerTorenoLookAtCarl	= 0

		// Let the car drive with only music playing for a while
		timerCutscene = m_mission_timer + 5000

		// Set up the skipped cutscene control flags (NOTE: cutscene playing immediately)
		flagCutscenePlaying				= TRUE
		flagCleaningUpSkippedCutscene	= FALSE
		flagSkipCutscene				= FALSE

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------


	// Subgoals
	// --------

	// Has the speech loaded?
	IF m_goals = 1
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			IF NOT nCurrentConversationID = MANSION2_CONVERSATION_INSTRUCTIONS
				nRequiredConversationID = MANSION2_CONVERSATION_INSTRUCTIONS
				GOSUB Mansion2_Conversation_Command_Prepare
			ENDIF

			IF nCurrentConversationID = MANSION2_CONVERSATION_INSTRUCTIONS
			AND timerCutscene < m_mission_timer
				GOSUB Mansion2_Conversation_Command_Play

				// Let the car drive and the conversation play for a while
				timerCutscene = m_mission_timer + 6200	// 7000

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Has the timer elapsed?
	IF m_goals = 2
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			IF timerCutscene < m_mission_timer
				// Set up a new camera angle
				SET_FIXED_CAMERA_POSITION -738.9800 227.2293 3.4204 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -738.0241 226.9390 3.3750 JUMP_CUT

				// Allow Carl and Toreno to look at each other
				flagAllowLookAt = TRUE

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Wait for Toreno's car to stop
	IF m_goals = 3
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			IF LOCATE_STOPPED_CAR_3D carToreno -731.1018 223.2893 1.3815 5.0 5.0 5.0 FALSE
				// Let the conversation continue for a while longer
				timerCutscene = m_mission_timer + 4600	//4000

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Check if it is time to change the camera angle again
	IF m_goals = 4
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			IF timerCutscene < m_mission_timer
				SET_FIXED_CAMERA_POSITION -725.9466 218.6036 4.4433 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -726.7355 219.1785 4.2265 JUMP_CUT

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Check if the speech has finished
	IF m_goals = 5
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			IF IS_BIT_SET bitsConversationsPlayed MANSION2_CONVERSATION_INSTRUCTIONS
				// Cancel 'look at'
				IF flagCarlLookingAtToreno = TRUE
					CLEAR_LOOK_AT scplayer
					flagCarlLookingAtToreno = FALSE
					timerCarlLookAtToreno = 0
				ENDIF

				IF flagTorenoLookingAtCarl = TRUE
					CLEAR_LOOK_AT charToreno
					flagTorenoLookingAtCarl = FALSE
					timerTorenoLookAtCarl = 0
				ENDIF

				flagAllowLookAt = FALSE

				// Get the player out of the car
				TASK_LEAVE_CAR scplayer carToreno
				TASK_GO_STRAIGHT_TO_COORD scplayer -732.8385 225.4091 1.7180 PEDMOVE_WALK 10000

				// Wait a short while
				timerCutscene = m_mission_timer + 1500

				m_goals++
			ENDIF
		ENDIF
	ENDIF

	
	// Check if it is time for the car to drive away
	IF m_goals = 6
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			IF timerCutscene < m_mission_timer
				TASK_CAR_DRIVE_TO_COORD charToreno carToreno -751.1168 113.9648 13.2849 15.0 MODE_NORMAL WASHING DRIVINGMODE_PLOUGHTHROUGH

				// Wait a while longer
				timerCutscene = m_mission_timer + 500

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Check if it is time to end the cutscene
	IF m_goals = 7
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals = 50
		ELSE
			IF timerCutscene < m_mission_timer
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL player1 ON

				// Clear up stuff that's not needed anymore
				DELETE_CHAR charToreno
				DELETE_CAR carToreno
				UNLOAD_SPECIAL_CHARACTER MANSION2_TORENO_SPECIAL_CHARACTER_SLOT
				MARK_MODEL_AS_NO_LONGER_NEEDED WASHING
				flagTorenoCarDeleted = TRUE

				flagCutscenePlaying = FALSE

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// RETURN TO HERE IF CUTSCENE SKIPPED
	// Tidy up ready for the next phase
	IF m_goals = 8
		m_goals = 99
	ENDIF


	// Skipped Cutscene stuff
	// ----------------------

	// Check if the cutscene was skipped
	// NOTE: Should never enter here unless skipped
	
	IF flagCutscenePlaying = TRUE
	AND IS_SKIP_CUTSCENE_BUTTON_PRESSED
	AND flagSkipCutscene = FALSE
		// Cutscene was skipped
		flagSkipCutscene = TRUE
	ENDIF


	// fade out
	IF m_goals = 50
		DO_FADE 500 FADE_OUT
		timerCutscene = m_mission_timer + 500
		flagCleaningUpSkippedCutscene = TRUE

		m_goals++	
	ENDIF


	// Clean up the skipped cutscene
	IF m_goals = 51
		IF timerCutscene < m_mission_timer
			// Cancel speech
			IF NOT IS_BIT_SET bitsConversationsPlayed MANSION2_CONVERSATION_INSTRUCTIONS
				GOSUB Mansion2_Conversation_Command_Cancel
			ENDIF

			// Cancel 'look at'
			IF flagCarlLookingAtToreno = TRUE
				CLEAR_LOOK_AT scplayer
				flagCarlLookingAtToreno = FALSE
				timerCarlLookAtToreno = 0
			ENDIF

			IF flagTorenoLookingAtCarl = TRUE
				CLEAR_LOOK_AT charToreno
				flagTorenoLookingAtCarl = FALSE
				timerTorenoLookAtCarl = 0
			ENDIF

			flagAllowLookAt = FALSE

			// Warp player
			IF IS_CHAR_IN_CAR scplayer carToreno
				WARP_CHAR_FROM_CAR_TO_COORD scplayer -732.8385 225.4091 1.7180 
			ELSE
				SET_CHAR_COORDINATES scplayer -732.8385 225.4091 1.7180 
			ENDIF
			SET_CHAR_HEADING scplayer 83.9578

			// Restore player controls
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL player1 ON

			// Clear up stuff that's not needed anymore
			DELETE_CHAR charToreno
			DELETE_CAR carToreno
			UNLOAD_SPECIAL_CHARACTER MANSION2_TORENO_SPECIAL_CHARACTER_SLOT
			MARK_MODEL_AS_NO_LONGER_NEEDED WASHING
			flagTorenoCarDeleted = TRUE

			// Introduce a short pause before fading in
			timerCutscene = m_mission_timer + 2000

			m_goals++
		ENDIF
	ENDIF


	// Short pause then fade in
	IF m_goals = 52
		IF timerCutscene < m_mission_timer
			DO_FADE 1500 FADE_IN
			timerCutscene = m_mission_timer + 1500

			m_goals++
		ENDIF
	ENDIF


	// Resume normal service
	IF m_goals = 53
		IF timerCutscene < m_mission_timer
			flagCleaningUpSkippedCutscene	= FALSE
			flagSkipCutscene				= FALSE
			flagCutscenePlaying				= FALSE

			m_goals = 8
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	// Look At
	IF flagAllowLookAt = TRUE
		// ...Carl
		IF timerCarlLookAtToreno = 0
			// ...set up a 'look at' time
			GENERATE_RANDOM_INT_IN_RANGE 2000 3000 nTempInt
			timerCarlLookAtToreno = m_mission_timer + nTempInt
		ELSE
			// ...check if 'look at' time has expired
			IF timerCarlLookAtToreno < m_mission_timer
				// ...time to change 'look at' action
				IF flagCarlLookingAtToreno = TRUE
					// ...currently looking, so cancel
					CLEAR_LOOK_AT scplayer
					flagCarlLookingAtToreno = FALSE
				ELSE
					// ...currently not looking, so look
					TASK_LOOK_AT_CHAR scplayer charToreno MANSION2_PERSIST
					flagCarlLookingAtToreno = TRUE
				ENDIF

				timerCarlLookAtToreno = 0
			ENDIF
		ENDIF

		// ...Toreno
		IF timerTorenoLookAtCarl = 0
			// ...set up a 'look at' time
			GENERATE_RANDOM_INT_IN_RANGE 1000 4000 nTempInt
			timerTorenoLookAtCarl = m_mission_timer + nTempInt
		ELSE
			// ...check if 'look at' time has expired
			IF timerTorenoLookAtCarl < m_mission_timer
				// ...time to change 'look at' action
				IF flagTorenoLookingAtCarl = TRUE
					// ...currently looking, so cancel
					CLEAR_LOOK_AT charToreno
					flagTorenoLookingAtCarl = FALSE
				ELSE
					// ...currently not looking, so look
					TASK_LOOK_AT_CHAR charToreno scplayer MANSION2_PERSIST
					flagTorenoLookingAtCarl = TRUE
				ENDIF

				timerTorenoLookAtCarl = 0
			ENDIF
		ENDIF
	ENDIF


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Mansion2_Next_Stage
	ENDIF

RETURN


// ****************************************
// STAGE: Carl enters speedboat and heads for big boat

Mansion2_Head_Towards_Carrier:

	flagSpeedboatAlive = TRUE
	IF IS_CAR_DEAD carSpeedboat
		flagSpeedboatAlive = FALSE
	ENDIF


   	// Initialisation
	// --------------

   	IF m_goals = 0
		// Add Carrier's moveable parts
		CREATE_OBJECT_NO_OFFSET CARRIER_LIFT1_SFSE -1456.719 501.297 9.914 objectCarrierBigLift
		CREATE_OBJECT_NO_OFFSET CARRIER_LIFT2_SFSE -1414.453 516.453 16.688 objectCarrierSmallLift
		CREATE_OBJECT_NO_OFFSET CARRIER_DOOR_SFSE -1465.797 501.289 1.145 objectCarrierBackDoor

		// Add a blip on the speedboat
		ADD_BLIP_FOR_CAR carSpeedboat blipSpeedboat
		SET_BLIP_AS_FRIENDLY blipSpeedboat TRUE

		// Create Enemy Boat
		CLEAR_AREA -1519.8431 590.9230 0.0000 20.0 TRUE
		CREATE_CAR LAUNCH -1519.8431 590.9230 0.0000 carEnemyBoat
		SET_CAR_HEADING carEnemyBoat 202.1246
		ANCHOR_BOAT carEnemyBoat TRUE

		// Create Guards on Enemy Boat
		// ...driver
		nTempInt = MANSION2_GUARD_BOAT_DRIVER
		CREATE_CHAR_INSIDE_CAR carEnemyBoat PEDTYPE_MISSION2 ARMY charGuards[nTempInt]
		SET_CHAR_DECISION_MAKER charGuards[nTempInt] dmEmpty
		GIVE_WEAPON_TO_CHAR charGuards[nTempInt] WEAPONTYPE_M4 300
		SET_CURRENT_CHAR_WEAPON charGuards[nTempInt] WEAPONTYPE_M4
		SET_CHAR_IS_TARGET_PRIORITY charGuards[nTempInt] TRUE

		// ...guy on back
		nTempInt = MANSION2_GUARD_BOAT_BACK
		CREATE_CHAR PEDTYPE_MISSION2 ARMY -1429.3545 497.0963 2.039 charGuards[nTempInt]
		SET_CHAR_DECISION_MAKER charGuards[nTempInt] dmEmpty
		ATTACH_CHAR_TO_CAR charGuards[nTempInt] carEnemyBoat 0.0 -3.5 1.3 FACING_FORWARD 0.0 WEAPONTYPE_M4
		GIVE_WEAPON_TO_CHAR charGuards[nTempInt] WEAPONTYPE_M4 300
		SET_CURRENT_CHAR_WEAPON charGuards[nTempInt] WEAPONTYPE_M4
		SET_CHAR_IS_TARGET_PRIORITY charGuards[nTempInt] TRUE

		// ...guy in front
		nTempInt = MANSION2_GUARD_BOAT_FRONT
		CREATE_CHAR PEDTYPE_MISSION2 ARMY -1428.7229 500.4720 2.0391 charGuards[nTempInt]
		SET_CHAR_DECISION_MAKER charGuards[nTempInt] dmEmpty
		ATTACH_CHAR_TO_CAR charGuards[nTempInt] carEnemyBoat 0.0 4.0 1.3 FACING_FORWARD 0.0 WEAPONTYPE_M4
		GIVE_WEAPON_TO_CHAR charGuards[nTempInt] WEAPONTYPE_M4 300
		SET_CURRENT_CHAR_WEAPON charGuards[nTempInt] WEAPONTYPE_M4
		SET_CHAR_IS_TARGET_PRIORITY charGuards[nTempInt] TRUE

		// Set up Harrier doing Fly Past
		statusHarrierFlyPast = 0

		// Set up the destination
		xposDestination = -1459.1429
		yposDestination = 501.0938
		zposDestination = 2.7322

		PRINT_NOW MAN2_20 7000 1  // ~s~There's a silenced pistol and a knife in the ~b~speedboat~s~.

		// Allow ambient cars again for now
		SET_CAR_DENSITY_MULTIPLIER 1.0

		// Indicate that the 'Big Lift' plane isn't on board the carrier
		flagPlanesOnCarrier[MANSION2_PLANE_BIG_LIFT] = FALSE

		// Load the Mission Audio soundban
		LOAD_MISSION_AUDIO MANSION2_SFX_SLOT SOUND_BANK_VERTICAL_BIRD

		// Make sure the player doesn't get a 6* rating whenever he sets foot on the aircraft carrier
		SET_DISABLE_MILITARY_ZONES TRUE

		flagBeenInSpeedboat				= FALSE
		flagBeenCloseToCarrier			= FALSE
		flagBeenCloseToEnemyBoat		= FALSE
		flagDisplayedGotoCarrierText	= FALSE
		flagRaiseBackDoor				= FALSE
		flagLowerBackDoor				= FALSE
		flagLowerBackDoorQuickly		= FALSE
		timerLowerBackDoor				= 0

		flagCutscenePlaying				= FALSE
		flagCleaningUpSkippedCutscene	= FALSE
		flagSkipCutscene				= FALSE

		m_goals++

	ENDIF


	// Failure Conditions
	// ------------------


	// Subgoals
	// --------

	// Check if it is time to trigger the cutscene
	IF m_goals = 1
		IF flagBeenCloseToCarrier = TRUE
		OR flagBeenCloseToEnemyBoat = TRUE
			nRequiredConversationID = MANSION2_CONVERSATION_SNEAK_INTO_CARRIER
			GOSUB Mansion2_Conversation_Command_Prepare

			// Ensure checking for being close to enemy boat (for triggering cutscene) no longer occurs
			flagBeenCloseToEnemyBoat = TRUE

			// Clear the area near the Carrier
			CLEAR_AREA -1357.0 502.0 10.0 300.0 FALSE

			// Set Widescreen and remove player control
			SWITCH_WIDESCREEN ON
			SET_PLAYER_CONTROL player1 OFF

			// Switch camera view
//			SET_FIXED_CAMERA_POSITION -1527.7502 551.4941 15.8335 0.0 0.0 0.0
//			POINT_CAMERA_AT_POINT -1526.9746 550.8938 15.6386 JUMP_CUT
			SET_FIXED_CAMERA_POSITION -1513.6138 559.4036 22.2746 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1513.0756 558.6777 21.8463 JUMP_CUT

			// Start raising the back door
			flagRaiseBackDoor = TRUE
			IF flagCarrierBackDoorAudioPlaying = FALSE
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierBackDoor SOUND_VERTICAL_BIRD_LIFT_START
				flagCarrierBackDoorAudioPlaying = TRUE
			ENDIF

			// Make the boat steer towards the ship
			aiGuards[MANSION2_GUARD_BOAT_DRIVER] = MANSION2_GUARDAI_STEER_BOAT_TO_SHIP

			flagCutscenePlaying = TRUE

			m_goals++
		ENDIF
	ENDIF


	// Trigger the cutscene
	IF m_goals = 2
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			// When the speech has loaded, trigger it
			IF NOT nCurrentConversationID = MANSION2_CONVERSATION_SNEAK_INTO_CARRIER
				nRequiredConversationID = MANSION2_CONVERSATION_SNEAK_INTO_CARRIER
				GOSUB Mansion2_Conversation_Command_Prepare
			ENDIF

			IF nCurrentConversationID = MANSION2_CONVERSATION_SNEAK_INTO_CARRIER
				GOSUB Mansion2_Conversation_Command_Play

				// Keep this camera view for a while
				timerCutscene = m_mission_timer + 6800

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// When timer has elapsed, switch the camera view again
	IF m_goals = 3
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			IF timerCutscene < m_mission_timer
				// Switch to a different camera view
				SET_FIXED_CAMERA_POSITION -1474.7386 526.7202 16.2159 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -1474.9915 525.8577 15.7775 JUMP_CUT

				timerCutscene = m_mission_timer + 3700

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Check for speech completion
	IF m_goals = 4
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals = 50
		ELSE
			IF timerCutscene < m_mission_timer
				IF IS_BIT_SET bitsConversationsPlayed MANSION2_CONVERSATION_SNEAK_INTO_CARRIER
					SWITCH_WIDESCREEN OFF
					SET_PLAYER_CONTROL player1 ON
					RESTORE_CAMERA_JUMPCUT
					SET_CAMERA_BEHIND_PLAYER

					flagCutscenePlaying = FALSE
					
					m_goals++
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// RETURN HERE WHEN CUTSCENE COMPLETE
	IF m_goals = 5
		PRINT_NOW MAN2_32 8000 1

		m_goals = 99
	ENDIF


	// Skipped Cutscene stuff
	// ----------------------

	// Check if the cutscene was skipped
	// NOTE: Should never enter here unless skipped
	
	IF flagCutscenePlaying = TRUE
	AND IS_SKIP_CUTSCENE_BUTTON_PRESSED
	AND flagSkipCutscene = FALSE
		flagSkipCutscene = TRUE
	ENDIF
	

	// fade out
	IF m_goals = 50
		DO_FADE 500 FADE_OUT
		timerCutscene = m_mission_timer + 500
		flagCleaningUpSkippedCutscene = TRUE

		m_goals++	
	ENDIF


	// Clean up the skipped cutscene
	IF m_goals = 51
		IF timerCutscene < m_mission_timer
			// Set all the stuff that needs set when the cutscene gets interrupted
			// Cancel speech
			IF NOT IS_BIT_SET bitsConversationsPlayed MANSION2_CONVERSATION_SNEAK_INTO_CARRIER
				GOSUB Mansion2_Conversation_Command_Cancel
			ENDIF

			// ...set the back door fully open
			SET_OBJECT_COORDINATES objectCarrierBackDoor -1468.492 501.289 11.109

			// ...move the enemy boat closer to the carrier
			IF NOT IS_CAR_DEAD carEnemyBoat
				SET_CAR_COORDINATES carEnemyBoat -1446.2179 491.9281 0.0
				SET_CAR_HEADING carEnemyBoat 270.0
			ENDIF

			// ...update the boat driver's AI
			IF NOT IS_CHAR_DEAD charGuards[MANSION2_GUARD_BOAT_DRIVER]
				IF NOT aiGuards[MANSION2_GUARD_BOAT_DRIVER] = MANSION2_GUARDAI_STEERING_BOAT_INTO_SHIP
					aiGuards[MANSION2_GUARD_BOAT_DRIVER] = MANSION2_GUARDAI_STEER_BOAT_INTO_SHIP
				ENDIF
			ENDIF

			// ...switch off the cutscene camera stuff
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL player1 ON
			RESTORE_CAMERA_JUMPCUT
			SET_CAMERA_BEHIND_PLAYER

			DO_FADE 1500 FADE_IN
			timerCutscene = m_mission_timer + 1500

			m_goals++
		ENDIF
	ENDIF


	// Resume normal service
	IF m_goals = 52
		IF timerCutscene < m_mission_timer
			flagCleaningUpSkippedCutscene	= FALSE
			flagSkipCutscene				= FALSE
			flagCutscenePlaying				= FALSE

			m_goals = 5
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	// Blip Maintenance
	IF flagBeenCloseToCarrier = FALSE
		IF flagSpeedboatAlive = TRUE
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1397.0 552.0 17.0 150.0 110.0 25.0 FALSE
				// Close to the ship, so display destination blip
				IF NOT DOES_BLIP_EXIST blipDestination
					ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination
				ENDIF

				// Remove speedboat blip
				REMOVE_BLIP blipSpeedboat

				// Cancel the 'where to go' speech if it hasn't been issued
				IF NOT IS_BIT_SET bitsConversationsPlayed MANSION2_CONVERSATION_GOTO_CARRIER
					SET_BIT bitsConversationsPlayed MANSION2_CONVERSATION_GOTO_CARRIER
				ENDIF

				flagBeenCloseToCarrier = TRUE
			ELSE
				// Not close to the ship, so display speedboat blip if not in it
				IF IS_CHAR_IN_CAR scplayer carSpeedboat
					// ...in speedboat, so display destination blip
					IF NOT DOES_BLIP_EXIST blipDestination
						ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination
					ENDIF

					// Remove speedboat blip
					REMOVE_BLIP blipSpeedboat
				ELSE
					// ...not in speedboat, so display speedboat blip
					IF NOT DOES_BLIP_EXIST blipSpeedboat
						ADD_BLIP_FOR_CAR carSpeedboat blipSpeedboat
						SET_BLIP_AS_FRIENDLY blipSpeedboat TRUE
					ENDIF

					// Remove destination blip
					REMOVE_BLIP blipDestination
				ENDIF
			ENDIF
		ELSE
			// ...speedboat dead
			REMOVE_BLIP blipSpeedboat

			// Display destination blip
			IF NOT DOES_BLIP_EXIST blipDestination
				ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination
			ENDIF
		ENDIF
	ENDIF


	// Speedboat weaponry
	IF flagBeenInSpeedboat = FALSE
		IF flagSpeedboatAlive = TRUE
			IF IS_CHAR_IN_CAR scplayer carSpeedboat
				// Give the player the silenced pistol and the knife
				GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_PISTOL_SILENCED 30
				GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_KNIFE 0
				SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PISTOL_SILENCED

				// Prepare and Play the 'Goto Carrier' Toreno speech
				nRequiredConversationID = MANSION2_CONVERSATION_GOTO_CARRIER
				GOSUB Mansion2_Conversation_Command_Prepare
				GOSUB Mansion2_Conversation_Command_Play

				flagBeenInSpeedboat = TRUE
			ENDIF
		ENDIF
	ENDIF


	// Display the 'goto carrier' text?
	IF flagDisplayedGotoCarrierText = FALSE
		IF flagBeenCloseToCarrier = FALSE
			IF DOES_BLIP_EXIST blipDestination
				IF IS_BIT_SET bitsConversationsPlayed MANSION2_CONVERSATION_GOTO_CARRIER
					PRINT_NOW MAN2_21 7000 1

					flagDisplayedGotoCarrierText = TRUE
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Check if the back door should be raised
	IF flagRaiseBackDoor = TRUE
		IF SLIDE_OBJECT objectCarrierBackDoor -1468.492 501.289 11.109 0.04 0.04 0.05 FALSE
			flagRaiseBackDoor = FALSE

			IF flagCarrierBackDoorAudioPlaying = TRUE
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierBackDoor SOUND_VERTICAL_BIRD_LIFT_STOP
				flagCarrierBackDoorAudioPlaying = FALSE
			ENDIF
		ENDIF
	ENDIF


	// Trigger cutscene?
	IF flagBeenCloseToEnemyBoat = FALSE
		IF IS_CAR_DEAD carEnemyBoat
			// ...no longer do this check
			flagBeenCloseToEnemyBoat = TRUE
		ENDIF

		IF flagBeenCloseToEnemyBoat = FALSE
			GET_CAR_COORDINATES carEnemyBoat xposTemp yposTemp zposTemp
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer xposTemp yposTemp zposTemp 100.0 100.0 30.0 FALSE
				flagBeenCloseToEnemyBoat = TRUE
			ENDIF
		ENDIF
	ENDIF


	// Update AI
	GOSUB Mansion2_Maintain_GuardAI
	GOSUB Mansion2_Maintain_MechanicAI


	// Update Harrier Fly Past
	GOSUB Mansion2_Maintain_Harrier_FlyPast


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Mansion2_Next_Stage
	ENDIF

RETURN


// ****************************************
// STAGE: Carl enters the aircraft carrier before the big ship's arse shuts

Mansion2_Enter_Aircraft_Carrier:

   	// Initialisation
	// --------------

   	IF m_goals = 0
		flagLowerBackDoor				= FALSE
		flagLowerBackDoorQuickly		= FALSE
		flagPlayerInCarrier				= FALSE
		flagGuardsImmediateActivation	= FALSE

		// Delete flypast harrier if it still exists
		IF NOT IS_CAR_DEAD carPlanes[MANSION2_PLANE_BIG_LIFT]
			DELETE_CAR carPlanes[MANSION2_PLANE_BIG_LIFT]
		ENDIF

		flagCutscenePlaying				= FALSE
		flagCleaningUpSkippedCutscene	= FALSE
		flagSkipCutscene				= FALSE

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------


	// Subgoals
	// --------

	// Check for the player getting inside the aircraft carrier's arse
	IF m_goals = 1
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer xposDestination yposDestination zposDestination 4.0 13.5 5.0 FALSE
			// Tidy up
			REMOVE_BLIP blipDestination
			REMOVE_BLIP blipSpeedboat

			
			// Player made it
			flagPlayerInCarrier = TRUE
			SET_PLAYER_CONTROL player1 OFF


			// Make the AI think it is in an interior for stealth-type lighting calculations
			FORCE_INTERIOR_LIGHTING_FOR_PLAYER player1 TRUE


			// Make the player shut up
			DISABLE_CHAR_SPEECH scplayer FALSE


			// Immediately start lowering the back door
			timerLowerBackDoor = 0
			flagLowerBackDoor = TRUE
			flagLowerBackDoorQuickly = TRUE

			IF flagCarrierBackDoorAudioPlaying = FALSE
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierBackDoor SOUND_VERTICAL_BIRD_LIFT_START
				flagCarrierBackDoorAudioPlaying = TRUE
			ENDIF
			
			flagLowerSmallLift = FALSE
			
			// Fade out
			SET_FADING_COLOUR 0 0 0
			DO_FADE 200 FADE_OUT
			timerCutscene = m_mission_timer + 400

			m_goals++
			
		ENDIF
	ENDIF


	// Start cutscene: Move camera to SAM sites
	IF m_goals = 2
		IF timerCutscene < m_mission_timer
			// Set up the cutscene
			ALTER_WANTED_LEVEL Player1 0

			// Set up some stuff
//			LOAD_SCENE -1439.5457 489.3506 3.8546
			LOAD_SCENE_IN_DIRECTION -1439.5457 489.3506 3.8546 300.0
//			FORCE_WEATHER_NOW WEATHER_SUNNY_SF
			SET_RADAR_ZOOM 90
			SHOW_BLIPS_ON_ALL_LEVELS TRUE

			// Add new blip for SAM site control room
			xposDestination = -1288.0844
			yposDestination = 490.5533
			zposDestination = 10.2026

			ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination

			// Temporarily move player to keep him out of trouble and to ensure the SAM site control room highlight is displayed
			SET_CHAR_COORDINATES scplayer -1327.9574 507.4273 0.0000
			SET_CHAR_HEADING scplayer 84.8851

			// Create the 2 Harriers that will be shown in the cutscene
			nTempInt = MANSION2_PLANE_INSIDE
			CREATE_CAR HYDRA -1423.62 497.13 10.19 carPlanes[nTempInt]
			SET_CAR_HEADING carPlanes[nTempInt] 62.12
			flagPlanesOnCarrier[nTempInt] = TRUE

			nTempInt = MANSION2_PLANE_SMALL_LIFT
			CREATE_CAR HYDRA -1416.47 518.36 16.91 carPlanes[nTempInt]
			SET_CAR_HEADING carPlanes[nTempInt] 270.0
			flagPlanesOnCarrier[nTempInt] = TRUE
			// ...and pilot
			CREATE_CHAR_INSIDE_CAR carPlanes[nTempInt] PEDTYPE_MISSION2 ARMY charGuards[MANSION2_GUARD_PILOT_SMALL_LIFT]
			SET_CHAR_DECISION_MAKER charGuards[MANSION2_GUARD_PILOT_SMALL_LIFT] dmEmpty
			SET_CHAR_IS_TARGET_PRIORITY charGuards[MANSION2_GUARD_PILOT_SMALL_LIFT] TRUE

			// Wait briefly before fade in
			timerCutscene = m_mission_timer + 2000

			flagCutscenePlaying = TRUE

			m_goals++
		ENDIF
	ENDIF


	// Fade in, aiming at control room
	IF m_goals = 3
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			IF timerCutscene < m_mission_timer
				SWITCH_WIDESCREEN ON

				// Move camera to SAM site control room
				SET_FIXED_CAMERA_POSITION -1304.9261 489.9207 11.6599 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -1304.0475 490.3978 11.6435 JUMP_CUT

				// INFO: switch off SAM sites
				PRINT_NOW MAN2_22 6000 1
				timerCutscene = m_mission_timer + 4000

				// Fade in
				DO_FADE 500 FADE_IN

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Display the SAM site control room highlight while waiting to start the lift
	IF m_goals = 4
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			// ...keep displaying control room highlight
			LOCATE_CHAR_ON_FOOT_3D scplayer xposDestination yposDestination zposDestination 1.0 1.0 2.0 TRUE

			IF timerCutscene < m_mission_timer
				// Start the Harrier lift moving, but keep displaying the SAM site for a while longer
				flagLowerSmallLift = TRUE

				IF flagCarrierSmallLiftAudioPlaying = FALSE
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierSmallLift SOUND_VERTICAL_BIRD_LIFT_START
					flagCarrierSmallLiftAudioPlaying = TRUE
				ENDIF

				timerCutscene = m_mission_timer + 1800

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Switch to the Harrier camera
	IF m_goals = 5
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			// ...keep displaying control room highlight
			LOCATE_CHAR_ON_FOOT_3D scplayer xposDestination yposDestination zposDestination 1.0 1.0 2.0 TRUE

			IF timerCutscene < m_mission_timer
				// Move camera to Harrier
				SET_FIXED_CAMERA_POSITION -1400.1672 517.7336 11.9235 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -1401.0795 518.1353 12.0041 JUMP_CUT

				// INFO: steal a jet
				PRINT_NOW MAN2_25 5000 1  // ~s~Steal one of the military jets.
				timerCutscene = m_mission_timer + 3800

				// Set up activation time for the Small Lift Harrier pilot's AI
				timerGuardsAI[MANSION2_GUARD_PILOT_SMALL_LIFT] = m_mission_timer + 2500

				// Move the player back to a safe position
				IF IS_CHAR_IN_ANY_CAR scplayer
					// ...player is in a vehicle
					SET_CHAR_COORDINATES scplayer -1446.3328 506.7063 -2.0890
					SET_CHAR_HEADING scplayer 256.1819

					IF IS_CHAR_IN_ANY_BOAT scplayer
						STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
						ANCHOR_BOAT car TRUE
					ENDIF

					// Make sure the guards react
					flagGuardsImmediateActivation = TRUE
				ELSE
					// ...player is swimming
					SET_CHAR_COORDINATES scplayer -1455.5585 497.0678 -2.0890
					SET_CHAR_HEADING scplayer 256.1819

					// ...check if the speedboat is inside the carrier
					IF NOT IS_CAR_DEAD carSpeedboat
						IF IS_CAR_IN_AREA_3D carSpeedboat -1466.0829 490.7395 -2.0 -1434.1390 511.8560 3.0 FALSE
							// ...reposition the speedboat
							SET_CAR_COORDINATES carSpeedboat -1446.3328 506.7063 -2.0890
							SET_CAR_HEADING carSpeedboat 256.1819

							// ...set the guards on alert
							flagGuardsImmediateActivation = TRUE
						ELSE
							// ...delete the speedboat
							DELETE_CAR carSpeedboat
						ENDIF
					ENDIF
				ENDIF

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// While still displaying the Harrier camera, activate the walking guards
	IF m_goals = 6
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			IF timerGuardsAI[MANSION2_GUARD_PILOT_SMALL_LIFT] < m_mission_timer
			AND NOT timerGuardsAI[MANSION2_GUARD_PILOT_SMALL_LIFT] = 0
				aiGuards[MANSION2_GUARD_PILOT_SMALL_LIFT] = MANSION2_GUARDAI_ACTIVATION
				timerGuardsAI[MANSION2_GUARD_PILOT_SMALL_LIFT] = 0
			ENDIF


			IF timerCutscene < m_mission_timer
				// Activate Walking Guards
				nTempInt = MANSION2_GUARD_BOAT_BACK
				IF NOT aiGuards[nTempInt] = MANSION2_GUARDAI_DEAD
					aiGuards[nTempInt] = MANSION2_GUARDAI_WALK_HOME
				ENDIF

				nTempInt = MANSION2_GUARD_BOAT_FRONT
				IF NOT aiGuards[nTempInt] = MANSION2_GUARDAI_DEAD
					aiGuards[nTempInt] = MANSION2_GUARDAI_WALK_HOME
				ENDIF

				timerCutscene = m_mission_timer + 1000

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Display the Harrier camera for a while, then switch to the Guards camera
	IF m_goals = 7
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			IF timerCutscene < m_mission_timer
				// Switch to Guards camera
//				SET_FIXED_CAMERA_POSITION -1392.5663 496.0909 3.6179 0.0 0.0 0.0
//
//				IF NOT IS_CHAR_DEAD charGuards[MANSION2_GUARD_BOAT_BACK]
//					POINT_CAMERA_AT_CHAR charGuards[MANSION2_GUARD_BOAT_BACK] FIXED JUMP_CUT
//				ENDIF
				SET_FIXED_CAMERA_POSITION -1395.6106 493.0729 5.3645 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -1394.9767 493.8029 5.1090 JUMP_CUT
	
				// Change the weather
				FORCE_WEATHER_NOW WEATHER_SUNNY_SF

				// INFO: be wary of guards
				PRINT_NOW MAN2_23 6000 1
				timerCutscene = m_mission_timer + 5800

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Check if the cutscene should end
	IF m_goals = 8
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals = 50
		ELSE
			IF timerCutscene < m_mission_timer
				// Give the player a decent heading
				SET_CHAR_HEADING scplayer 256.1819

				// Restore player controls
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL player1 ON

				flagCutscenePlaying = FALSE

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// RETURN HERE WHEN CUTSCENE FINISHED
	IF m_goals = 9
		// Activate the AI of the Guard on top watching the Harriers and give him a blip
		aiGuards[MANSION2_GUARD_HARRIER_GUIDE] = MANSION2_GUARDAI_ACTIVATION
		flagGuardsAllowAlert[MANSION2_GUARD_HARRIER_GUIDE] = TRUE

		// Show blips for all Guards (except small lift harrier pilot, whose blip will appear after teleport to avoid glitch)
		REPEAT MANSION2_MAX_GUARDS nLoop
			IF NOT aiGuards[nLoop] = MANSION2_GUARDAI_DEAD
			AND NOT aiGuards[nLoop] = MANSION2_GUARDAI_NOT_INITIALISED
			AND NOT IS_CHAR_DEAD charGuards[nLoop]
				IF NOT nLoop = MANSION2_GUARD_PILOT_SMALL_LIFT
					ADD_BLIP_FOR_CHAR charGuards[nLoop] blipGuards[nLoop]
					CHANGE_BLIP_DISPLAY blipGuards[nLoop] BLIP_ONLY
					SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR blipGuards[nLoop] TRUE
				ENDIF
			ENDIF
		ENDREPEAT

//		// Show blip for Harrier_Guide (he currently has no AI but should get his blip)
//		IF NOT DOES_BLIP_EXIST blipGuards[MANSION2_GUARD_HARRIER_GUIDE]
//			ADD_BLIP_FOR_CHAR charGuards[MANSION2_GUARD_HARRIER_GUIDE] blipGuards[MANSION2_GUARD_HARRIER_GUIDE]
//			CHANGE_BLIP_DISPLAY blipGuards[MANSION2_GUARD_HARRIER_GUIDE] BLIP_ONLY
//			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR blipGuards[MANSION2_GUARD_HARRIER_GUIDE] TRUE
//		ENDIF

		// Teleport the Small Lift Harrier pilot (so he doesn't end up in the water)
		aiGuards[MANSION2_GUARD_PILOT_SMALL_LIFT] = MANSION2_GUARDAI_TELEPORT

		// If Carl has been detected, activate the Guard and the Mechanic
//		IF IS_CHAR_IN_ANY_CAR scplayer
		IF flagGuardsImmediateActivation = TRUE
			// ...player detected, so activate
			nReasonForDetection = MANSION2_DETECTED_TOO_MUCH_NOISE

			nTempInt = MANSION2_GUARD_BOAT_DRIVER
			IF NOT aiGuards[nTempInt] = MANSION2_GUARDAI_DEAD
				aiGuards[nTempInt] = MANSION2_GUARDAI_RUN_ATTACK
			ENDIF

			nTempInt = MANSION2_MECHANIC_DOWNSTAIRS
			IF NOT aiMechanics[nTempInt] = MANSION2_MECHANICAI_DEAD
				aiMechanics[nTempInt] = MANSION2_MECHANICAI_GOTO_ALARM
			ENDIF

			// The player made too much noise entering the Aircraft Carrier
			PRINT_NOW MAN2_46 5000 1
		ELSE
			// ...player not detected, so make the Boat Driver chat to the Mechanic
			nTempInt = MANSION2_GUARD_BOAT_DRIVER
			IF NOT aiGuards[nTempInt] = MANSION2_GUARDAI_DEAD
				aiGuards[nTempInt] = MANSION2_GUARDAI_CHAT
			ENDIF

			nTempInt = MANSION2_MECHANIC_DOWNSTAIRS
			IF NOT aiMechanics[nTempInt] = MANSION2_MECHANICAI_DEAD
				aiMechanics[nTempInt] = MANSION2_MECHANICAI_CHAT
			ENDIF
		ENDIF

		// Allow Mechanic Senses updates
		flagAllowMechanicSensesUpdates = TRUE
		
		m_goals = 99
	ENDIF


	// Skipped Cutscene stuff
	// ----------------------

	// Check if the cutscene was skipped
	// NOTE: Should never enter here unless skipped
	
	IF flagCutscenePlaying = TRUE
	AND IS_SKIP_CUTSCENE_BUTTON_PRESSED
	AND flagSkipCutscene = FALSE
		flagSkipCutscene = TRUE
	ENDIF


	// fade out
	IF m_goals = 50
		DO_FADE 500 FADE_OUT
		timerCutscene = m_mission_timer + 500
		flagCleaningUpSkippedCutscene = TRUE

		m_goals++	
	ENDIF


	// Clean up the skipped cutscene
	IF m_goals = 51
		IF timerCutscene < m_mission_timer
			// Set all the stuff that needs set when the cutscene gets interrupted
			// ...lower the small lift
			flagLowerSmallLift = TRUE

			IF flagCarrierSmallLiftAudioPlaying = FALSE
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierSmallLift SOUND_VERTICAL_BIRD_LIFT_START
				flagCarrierSmallLiftAudioPlaying = TRUE
			ENDIF

			// ...move the player back to a safe position
			IF IS_CHAR_IN_ANY_CAR scplayer
				// ...player is in a vehicle
				SET_CHAR_COORDINATES scplayer -1446.3328 506.7063 -2.0890
				SET_CHAR_HEADING scplayer 256.1819

				IF IS_CHAR_IN_ANY_BOAT scplayer
					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
					ANCHOR_BOAT car TRUE
				ENDIF

				// Make sure the guards react
				flagGuardsImmediateActivation = TRUE
			ELSE
				// ...player is swimming
				SET_CHAR_COORDINATES scplayer -1455.5585 497.0678 -2.0890
				SET_CHAR_HEADING scplayer 256.1819

				// ...check if the speedboat is inside the carrier
				IF NOT IS_CAR_DEAD carSpeedboat
					IF IS_CAR_IN_AREA_3D carSpeedboat -1466.0829 490.7395 -2.0 -1434.1390 511.8560 3.0 FALSE
						// ...reposition the speedboat
						SET_CAR_COORDINATES carSpeedboat -1446.3328 506.7063 -2.0890
						SET_CAR_HEADING carSpeedboat 256.1819

						// ...set the guards on alert
						flagGuardsImmediateActivation = TRUE
					ELSE
						// ...delete the speedboat
						DELETE_CAR carSpeedboat
					ENDIF
				ENDIF
			ENDIF

			// ...activate Walking Guards
			nTempInt = MANSION2_GUARD_BOAT_BACK
			IF NOT aiGuards[nTempInt] = MANSION2_GUARDAI_DEAD
				aiGuards[nTempInt] = MANSION2_GUARDAI_WALK_HOME
			ENDIF

			nTempInt = MANSION2_GUARD_BOAT_FRONT
			IF NOT aiGuards[nTempInt] = MANSION2_GUARDAI_DEAD
				aiGuards[nTempInt] = MANSION2_GUARDAI_WALK_HOME
			ENDIF

			// Activate the Small Lift Harrier pilot's AI if skipped in cutscene
			IF aiGuards[MANSION2_GUARD_PILOT_SMALL_LIFT] = MANSION2_GUARDAI_NOT_INITIALISED
				aiGuards[MANSION2_GUARD_PILOT_SMALL_LIFT] = MANSION2_GUARDAI_ACTIVATION
			ENDIF
	
			// Change the weather
			FORCE_WEATHER_NOW WEATHER_SUNNY_SF

			// ...restore player controls
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL player1 ON

			DO_FADE 1500 FADE_IN
			timerCutscene = m_mission_timer + 1500

			m_goals++
		ENDIF
	ENDIF


	// Resume normal service
	IF m_goals = 52
		IF timerCutscene < m_mission_timer
			flagCleaningUpSkippedCutscene	= FALSE
			flagSkipCutscene				= FALSE
			flagCutscenePlaying				= FALSE

			m_goals = 9
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	IF flagLowerSmallLift = TRUE
		IF SLIDE_OBJECT objectCarrierSmallLift -1414.453 516.453 9.648 0.05 0.05 0.05 FALSE
			flagLowerSmallLift = FALSE

			IF flagCarrierSmallLiftAudioPlaying = TRUE
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierSmallLift SOUND_VERTICAL_BIRD_LIFT_STOP
				flagCarrierSmallLiftAudioPlaying = FALSE
			ENDIF
		ENDIF
	ENDIF


	// Check if the back door should be lowered
	IF flagLowerBackDoor = TRUE
		IF flagLowerBackDoorQuickly = TRUE
			IF SLIDE_OBJECT objectCarrierBackDoor -1465.797 501.289 1.145 0.04 0.04 0.30 FALSE
				flagLowerBackDoor = FALSE

				IF flagCarrierBackDoorAudioPlaying = TRUE
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierBackDoor SOUND_VERTICAL_BIRD_LIFT_STOP
					flagCarrierBackDoorAudioPlaying = FALSE
				ENDIF
			ENDIF
		ELSE
			IF SLIDE_OBJECT objectCarrierBackDoor -1465.797 501.289 1.145 0.04 0.04 0.05 FALSE
				flagLowerBackDoor = FALSE

				IF flagCarrierBackDoorAudioPlaying = TRUE
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierBackDoor SOUND_VERTICAL_BIRD_LIFT_STOP
					flagCarrierBackDoorAudioPlaying = FALSE
				ENDIF

				// Has the player made it into the Carrier?
				IF flagPlayerInCarrier = FALSE
					// ...no
					m_failed = TRUE
					m_fail_reason = MANSION2_FAILED_NOT_IN_CARRIER
					RETURN
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Update AI
	GOSUB Mansion2_Maintain_GuardAI
	GOSUB Mansion2_Maintain_MechanicAI


	// Back Door maintenance
	IF NOT timerLowerBackDoor = 0
		IF timerLowerBackDoor < m_mission_timer
			timerLowerBackDoor = 0
			flagLowerBackDoor = TRUE

			IF flagCarrierBackDoorAudioPlaying = FALSE
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierBackDoor SOUND_VERTICAL_BIRD_LIFT_START
				flagCarrierBackDoorAudioPlaying = TRUE
			ENDIF

			// Back Door closing
			PRINT_NOW MAN2_31 7000 1
		ENDIF
	ENDIF


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Mansion2_Next_Stage
	ENDIF

RETURN


// ****************************************
// STAGE: Enter Harrier

Mansion2_Enter_Harrier:

   	// Initialisation
	// --------------

   	IF m_goals = 0
		flagReachedControlRoom = FALSE

		// Switch off ambient cars and peds
		SET_CAR_DENSITY_MULTIPLIER	0.0
		SET_PED_DENSITY_MULTIPLIER	0.0
		SWITCH_AMBIENT_PLANES		OFF

		// Create the other Harrier that starts on the Carrier
		nTempInt = MANSION2_PLANE_TAKEOFF
		CREATE_CAR HYDRA -1333.2310 507.6329 17.2266 carPlanes[nTempInt]
		SET_CAR_HEADING carPlanes[nTempInt] 270.0
		flagPlanesOnCarrier[nTempInt] = TRUE

		// Create the three alarms
		nTempInt = MANSION2_ALARM_DOWNSTAIRS
		CREATE_OBJECT KMB_KEYPAD -1414.5495 514.4193 2.9391 objectAlarms[nTempInt]
		SET_OBJECT_HEADING objectAlarms[nTempInt] 180.0

		nTempInt = MANSION2_ALARM_UPSTAIRS_HARRIERS
		CREATE_OBJECT KMB_KEYPAD -1313.1726 488.3687 11.1026 objectAlarms[nTempInt]

		nTempInt = MANSION2_ALARM_UPSTAIRS_CONTROL_ROOM
		CREATE_OBJECT KMB_KEYPAD -1412.1975 488.3644 11.1026 objectAlarms[nTempInt]

		// Switch off the radio for the next vehicle (which should be the plane, but could be a forklift)
		SET_RADIO_CHANNEL RS_OFF

		flagDisplayedFlyingHelp			= FALSE
		flagDisplayedFlyingHelp2		= FALSE
		flagDisplayedControlRoomWarning	= FALSE
		flagPlayerFlyingHarrier			= FALSE
		flagShowingSmallLiftCamera		= FALSE
		flagShowingBigLiftCamera		= FALSE
		flagReorganisedPlanes			= FALSE
		flagPlayedHarrierTakeoff		= FALSE
		flagPlayedHarrierLanding		= FALSE
		flagPlayerFiringGun				= FALSE
		flagPlayerFiringSilencedGun		= FALSE
		flagDisplayedGotoHarrierText	= FALSE

		statusHarrierTakeoff = -1
		statusHarrierLanding = -1

		flagCutscenePlaying				= FALSE
		flagCleaningUpSkippedCutscene	= FALSE
		flagSkipCutscene				= FALSE
		
		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------

	// Have all enemy planes been destroyed?
	// NOTE: If flagTempFlag is TRUE then there is a plane on the Carrier that is not dead
	flagTempFlag = FALSE
	REPEAT MANSION2_MAX_PLANES nLoop
		IF flagPlanesOnCarrier[nLoop] = TRUE
			IF NOT IS_CAR_DEAD carPlanes[nLoop]
				flagTempFlag = TRUE
			ENDIF
		ENDIF
	ENDREPEAT

	IF flagTempFlag = FALSE
		// ...all planes dead
		m_failed = TRUE
		m_fail_reason = MANSION2_FAILED_NO_PLANES_LEFT
		RETURN
	ENDIF

	
	// Is the player outside the carrier?
	IF NOT IS_CHAR_IN_MODEL scplayer HYDRA
		GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
		IF yposTemp < 484.0000
		OR yposTemp > 523.0000
			// ...player outside harrier
			m_failed = TRUE
			m_fail_reason = MANSION2_FAILED_LEFT_CARRIER
			RETURN
		ENDIF

		IF xposTemp < -1471.0000
		OR xposTemp > -1246.0000
			// ...player outside harrier
			m_failed = TRUE
			m_fail_reason = MANSION2_FAILED_LEFT_CARRIER
			RETURN
		ENDIF
	ENDIF


	// Subgoals
	// --------

	// Check if player has reached SAM sites control room
	IF m_goals = 1
		IF flagReachedControlRoom = TRUE
			SET_EVERYONE_IGNORE_PLAYER player1 TRUE
			SWITCH_WIDESCREEN ON
			SET_PLAYER_CONTROL player1 OFF
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

			SET_FIXED_CAMERA_POSITION -1291.5785 491.3468 13.2852 0.0 0.0 0.0 
			POINT_CAMERA_AT_POINT -1290.6862 491.1050 12.9042 JUMP_CUT

			SET_CHAR_COORDINATES scplayer xposDestination yposDestination zposDestination
			TASK_ACHIEVE_HEADING scplayer 270.0000
			timerCutscene = m_mission_timer + 200

			// ...remove SAM site control room blip
			REMOVE_BLIP blipDestination

			// ...add blip beside harriers
			ADD_BLIP_FOR_COORD -1432.4816 506.5725 10.1953 blipDestination

			// This warning won't be needed, so mark it as 'seen'
			flagDisplayedControlRoomWarning = TRUE

			flagCutscenePlaying = TRUE

			m_goals++
		ENDIF

		// The player may have skipped past the control room section
		IF flagPlayerFlyingHarrier = TRUE
			m_goals = 6
		ENDIF
	ENDIF


	// Check if the player has achieved the heading
	IF m_goals = 2
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			GET_SCRIPT_TASK_STATUS scplayer TASK_ACHIEVE_HEADING m_status
			IF NOT m_status = PERFORMING_TASK
			AND NOT m_status = WAITING_TO_START_TASK
				// Achieved heading, so play anim
				TASK_PLAY_ANIM scplayer Slot_bet_02 CASINO 4.0 FALSE FALSE FALSE FALSE 0
				timerCutscene = m_mission_timer + 250

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Check if the animation has finished
	IF m_goals = 3
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			IF timerCutscene < m_mission_timer
				IF NOT IS_CHAR_PLAYING_ANIM scplayer Slot_bet_02
					// Animation finished, show SAM site
//					SET_FIXED_CAMERA_POSITION -1391.7766 490.7817 19.2745 0.0 0.0 0.0 
//					POINT_CAMERA_AT_POINT -1392.5842 491.3141 19.5280 JUMP_CUT
					SET_FIXED_CAMERA_POSITION -1391.3046 490.2552 18.2899 0.0 0.0 0.0 
					POINT_CAMERA_AT_POINT -1392.1383 490.5674 18.7452 JUMP_CUT
					timerCutscene = m_mission_timer + 2000

					m_goals++
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Wait for timeout
	IF m_goals = 4
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			IF timerCutscene < m_mission_timer
				// Switch off Aircraft Carrier SAM site
				SET_AIRCRAFT_CARRIER_SAM_SITE OFF
				timerCutscene = m_mission_timer + 1500

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Wait for timeout
	IF m_goals = 5
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals = 50
		ELSE
			IF timerCutscene < m_mission_timer
				// Restore player control
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
				SET_EVERYONE_IGNORE_PLAYER player1 FALSE
				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL player1 ON

				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT

				// ...steal a harrier
				PRINT_NOW MAN2_25 15000 1  // ~s~Steal one of the military jets.

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// RETURN HERE AFTER CUTSCENE FINISHED
	// NOTE: Goal 1 jumps to here, so if this goal number changes, update goal 1
	// Check if player is flying a Harrier
	IF m_goals = 6
		IF flagPlayerFlyingHarrier = TRUE
			IF flagShowingSmallLiftCamera = TRUE
			OR flagShowingBigLiftCamera = TRUE
				// ...restore the camera
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA
			ENDIF

			// ...remove SAM site control room blip
			REMOVE_BLIP blipDestination

			// ...remove all GUARD blips
			REPEAT MANSION2_MAX_GUARDS nLoop
				REMOVE_BLIP blipGuards[nLoop]
			ENDREPEAT
			
			m_goals = 99
		ENDIF
	ENDIF


	// Skipped Cutscene stuff
	// ----------------------

	// Check if the cutscene was skipped
	// NOTE: Should never enter here unless skipped
	
	IF flagCutscenePlaying = TRUE
	AND IS_SKIP_CUTSCENE_BUTTON_PRESSED
	AND flagSkipCutscene = FALSE
		// Cutscene was skipped
		flagSkipCutscene = TRUE
	ENDIF
	
	// fade out
	IF m_goals = 50
		DO_FADE 500 FADE_OUT
		timerCutscene = m_mission_timer + 500
		flagCleaningUpSkippedCutscene = TRUE

		m_goals++	
	ENDIF


	// Clean up the skipped cutscene
	IF m_goals = 51
		IF timerCutscene < m_mission_timer
			// Set all the stuff that needs set when the cutscene gets interrupted
			// ...switch off Aircraft Carrier SAM sites
			SET_AIRCRAFT_CARRIER_SAM_SITE OFF

			// Restore player control
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
			SET_EVERYONE_IGNORE_PLAYER player1 FALSE
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL player1 ON
			
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT

			DO_FADE 1500 FADE_IN
			timerCutscene = m_mission_timer + 1500

			m_goals++
		ENDIF
	ENDIF


	// Resume normal service
	IF m_goals = 52
		IF timerCutscene < m_mission_timer
			flagCleaningUpSkippedCutscene	= FALSE
			flagSkipCutscene				= FALSE
			flagCutscenePlaying				= FALSE

			// ...steal a harrier
			PRINT_NOW MAN2_25 15000 1  // ~s~Steal one of the military jets.

			m_goals = 6
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	// Has player reached control room?
	IF flagReachedControlRoom = FALSE
		IF LOCATE_CHAR_ON_FOOT_3D scplayer xposDestination yposDestination zposDestination 1.0 1.0 2.0 TRUE
			flagReachedControlRoom = TRUE
		ENDIF
	ENDIF


	// Has player climbed in a Harrier without switching off the SAM sites?
	IF flagDisplayedControlRoomWarning = FALSE
		IF IS_CHAR_IN_MODEL scplayer HYDRA
			PRINT_NOW MAN2_24 5000 1
			flagDisplayedControlRoomWarning = TRUE
		ENDIF
	ENDIF


	// Has the flying help text been printed?
	IF flagDisplayedFlyingHelp = FALSE
		IF IS_CHAR_IN_MODEL scplayer HYDRA
			PRINT_HELP_FOREVER MAN2_70  // Use the steering controls to tilt the plane forward and back.
			flagDisplayedFlyingHelp = TRUE
			flagDisplayedFlyingHelp2 = FALSE
			timerHelpText = m_mission_timer + 15000
		ENDIF
	ENDIF


	// Has help text 2 been displayed?
	IF flagDisplayedFlyingHelp = TRUE
		IF flagDisplayedFlyingHelp2 = FALSE
			IF timerHelpText < m_mission_timer
				CLEAR_HELP
				
				PRINT_HELP_FOREVER MAN2_99 
				
				flagDisplayedFlyingHelp2 = TRUE
				timerHelpText = m_mission_timer + 10000
			ENDIF
		ENDIF
	ENDIF


	// Has flying help 2 timed out?
	IF flagDisplayedFlyingHelp2 = TRUE
		IF NOT timerHelpText = 0
			IF timerHelpText < m_mission_timer
				timerHelpText = 0
				CLEAR_HELP
			ENDIF
		ENDIF
	ENDIF


	// Check if the Harrier Takeoff car recording should be played
	IF flagPlayedHarrierTakeoff = FALSE
		IF IS_CHAR_IN_MODEL scplayer HYDRA
			// ...player is in a Harrier
			IF IS_CHAR_IN_CAR scplayer carPlanes[MANSION2_PLANE_TAKEOFF]
				// ...player is in the takeoff harrier (somehow - shouldn't happen)
				flagPlayedHarrierTakeoff = TRUE
			ELSE
				// ...player is in any of the other Harriers
				START_PLAYBACK_RECORDED_CAR carPlanes[MANSION2_PLANE_TAKEOFF] MANSION2_CAR_RECORDING_HARRIER_TAKEOFF
				statusHarrierTakeoff = 0
				flagPlayedHarrierTakeoff = TRUE
			ENDIF
		ELSE
			// ...player is not in a Harrier
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1347.6920 499.5881 17.2266 1.2 1.2 1.2 FALSE
				// ...player is at the top of the top staircase, so playback the recording
				START_PLAYBACK_RECORDED_CAR carPlanes[MANSION2_PLANE_TAKEOFF] MANSION2_CAR_RECORDING_HARRIER_TAKEOFF
				statusHarrierTakeoff = 0
				flagPlayedHarrierTakeoff = TRUE
			ELSE
				IF IS_CHAR_TOUCHING_OBJECT scplayer objectCarrierSmallLift
					// ...player is touching the small lift, so playback the recording
					START_PLAYBACK_RECORDED_CAR carPlanes[MANSION2_PLANE_TAKEOFF] MANSION2_CAR_RECORDING_HARRIER_TAKEOFF
					statusHarrierTakeoff = 0
					flagPlayedHarrierTakeoff = TRUE
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Check if the Harrier Landing car recording should be played
	IF flagPlayedHarrierLanding = FALSE
		// Is the player at the top of the first flight of steps?
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1368.3029 496.5314 10.1854 5.0 5.0 4.0 FALSE
			statusHarrierLanding = 0
			flagPlayedHarrierLanding = TRUE

			// ...also activate the Small Lift Harrier's wander
			IF flagGuardsOnAlert[MANSION2_GUARD_PILOT_SMALL_LIFT] = FALSE
				aiGuards[MANSION2_GUARD_PILOT_SMALL_LIFT] = MANSION2_GUARDAI_WANDER
			ENDIF
		ENDIF
	ENDIF


	// Slide the Small Lift if the player is in the Small Lift Harrier
	IF NOT IS_CAR_DEAD carPlanes[MANSION2_PLANE_SMALL_LIFT]
		flagTempFlag = FALSE
		IF IS_CHAR_IN_CAR scplayer carPlanes[MANSION2_PLANE_SMALL_LIFT]
			// ...slide the lift up
			IF SLIDE_OBJECT objectCarrierSmallLift -1414.453 516.453 16.688 0.05 0.05 0.05 FALSE
				flagTempFlag = TRUE
			ENDIF
		ELSE
			// ...slide down
			IF SLIDE_OBJECT objectCarrierSmallLift -1414.453 516.453 9.648 0.05 0.05 0.05 FALSE
				flagTempFlag = TRUE
			ENDIF
		ENDIF

		IF flagCarrierSmallLiftAudioPlaying = FALSE
			// ...audio is not playing. Should it be?
			IF flagTempFlag = FALSE
				// ...yes, so play it
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierSmallLift SOUND_VERTICAL_BIRD_LIFT_START
				flagCarrierSmallLiftAudioPlaying = TRUE
			ENDIF
		ELSE
			// ...audio is playing. Stop it?
			IF flagTempFlag = TRUE
				// ...yes, stop it
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierSmallLift SOUND_VERTICAL_BIRD_LIFT_STOP
				flagCarrierSmallLiftAudioPlaying = FALSE
			ENDIF
		ENDIF
	ENDIF


	// Slide the Big Lift if the player is in the Big Lift Harrier
	IF NOT IS_CAR_DEAD carPlanes[MANSION2_PLANE_BIG_LIFT]
		flagTempFlag = FALSE
		IF IS_CHAR_IN_CAR scplayer carPlanes[MANSION2_PLANE_BIG_LIFT]
			// ...slide the lift up
			IF SLIDE_OBJECT objectCarrierBigLift -1456.719 501.297 16.953 0.05 0.05 0.05 FALSE
				flagTempFlag = TRUE
			ENDIF
		ELSE
			// ...slide down
			IF SLIDE_OBJECT objectCarrierBigLift -1456.719 501.297 9.914 0.05 0.05 0.05 FALSE
				flagTempFlag = TRUE
			ENDIF
		ENDIF

		IF flagCarrierBigLiftAudioPlaying = FALSE
			// ...audio is not playing. Should it be?
			IF flagTempFlag = FALSE
				// ...yes, so play it
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierBigLift SOUND_VERTICAL_BIRD_LIFT_START
				flagCarrierBigLiftAudioPlaying = TRUE
			ENDIF
		ELSE
			// ...audio is playing. Stop it?
			IF flagTempFlag = TRUE
				// ...yes, stop it
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierBigLift SOUND_VERTICAL_BIRD_LIFT_STOP
				flagCarrierBigLiftAudioPlaying = FALSE
			ENDIF
		ENDIF
	ENDIF


	// Change the camera angle if the small lift harrier is being raised
	IF flagShowingSmallLiftCamera = FALSE
		// ...plane is in plane and plane is on lift
		IF NOT IS_CAR_DEAD carPlanes[MANSION2_PLANE_SMALL_LIFT]
			IF IS_CHAR_IN_CAR scplayer carPlanes[MANSION2_PLANE_SMALL_LIFT]
				GET_CAR_COORDINATES carPlanes[MANSION2_PLANE_SMALL_LIFT] xposTemp yposTemp zposTemp
				GET_OBJECT_COORDINATES objectCarrierSmallLift xposTemp2 yposTemp2 zposTemp2
				fTempFloat = zposTemp - zposTemp2
				IF fTempFloat >= 0.0
				OR fTempFloat <= 3.0
					// ...move camera to a better viewing position
					SET_FIXED_CAMERA_POSITION -1427.4083 516.3539 20.8899 0.0 0.0 0.0 
					POINT_CAMERA_AT_POINT -1426.4348 516.2787 20.6742 INTERPOLATION
					flagShowingSmallLiftCamera = TRUE
				ENDIF
			ENDIF
		ENDIF
	ELSE
		IF IS_CAR_DEAD carPlanes[MANSION2_PLANE_SMALL_LIFT]
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA
			flagShowingSmallLiftCamera = FALSE
		ELSE
			IF NOT IS_CHAR_IN_CAR scplayer carPlanes[MANSION2_PLANE_SMALL_LIFT]
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA
				flagShowingSmallLiftCamera = FALSE
			ENDIF
		ENDIF
	ENDIF


	// Change the camera angle if the big lift harrier is being raised
	IF flagShowingBigLiftCamera = FALSE
		// ...plane is in plane and plane is on lift
		IF NOT IS_CAR_DEAD carPlanes[MANSION2_PLANE_BIG_LIFT]
			IF IS_CHAR_IN_CAR scplayer carPlanes[MANSION2_PLANE_BIG_LIFT]
				GET_CAR_COORDINATES carPlanes[MANSION2_PLANE_BIG_LIFT] xposTemp yposTemp zposTemp
				GET_OBJECT_COORDINATES objectCarrierBigLift xposTemp2 yposTemp2 zposTemp2
				fTempFloat = zposTemp - zposTemp2
				IF fTempFloat >= 0.0
				OR fTempFloat <= 3.0
					// ...move camera to a better viewing position
					SET_FIXED_CAMERA_POSITION -1439.2832 507.3634 28.9545 0.0 0.0 0.0 
					POINT_CAMERA_AT_POINT -1440.0693 507.1481 28.3752 INTERPOLATION
					flagShowingBigLiftCamera = TRUE
				ENDIF
			ENDIF
		ENDIF
	ELSE
		IF IS_CAR_DEAD carPlanes[MANSION2_PLANE_BIG_LIFT]
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA
			flagShowingBigLiftCamera = FALSE
		ELSE
			IF NOT IS_CHAR_IN_CAR scplayer carPlanes[MANSION2_PLANE_BIG_LIFT]
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA
				flagShowingBigLiftCamera = FALSE
			ENDIF
		ENDIF
	ENDIF

					   
	// Check if the player is flying a Harrier
	REPEAT MANSION2_MAX_PLANES nLoop
		IF NOT IS_CAR_DEAD carPlanes[nLoop]
			IF IS_CHAR_IN_CAR scplayer carPlanes[nLoop]
				// ...check if plane is in the air
				SWITCH nLoop
					CASE MANSION2_PLANE_SMALL_LIFT
						GET_CAR_COORDINATES carPlanes[nLoop] xposTemp yposTemp zposTemp
						GET_OBJECT_COORDINATES objectCarrierSmallLift xposTemp2 yposTemp2 zposTemp2
						fTempFloat = zposTemp - zposTemp2
						IF fTempFloat < 0.0
						OR fTempFloat > 3.0
							flagPlayerFlyingHarrier = TRUE
						ENDIF
						BREAK
					CASE MANSION2_PLANE_BIG_LIFT
						GET_CAR_COORDINATES carPlanes[nLoop] xposTemp yposTemp zposTemp
						GET_OBJECT_COORDINATES objectCarrierBigLift xposTemp2 yposTemp2 zposTemp2
						fTempFloat = zposTemp - zposTemp2
						IF fTempFloat < 0.0
						OR fTempFloat > 3.0
							flagPlayerFlyingHarrier = TRUE
						ENDIF
						BREAK
					CASE MANSION2_PLANE_INSIDE
						GET_CAR_COORDINATES carPlanes[nLoop] xposTemp yposTemp zposTemp
						IF zposTemp > 20.0
						OR zposTemp < 5.0
							flagPlayerFlyingHarrier = TRUE
						ENDIF
						BREAK
					DEFAULT
						IF IS_CAR_IN_AIR_PROPER carPlanes[nLoop]
							// ...player is flying a Harrier
							flagPlayerFlyingHarrier = TRUE
						ENDIF
				ENDSWITCH
			ENDIF
		ENDIF
	ENDREPEAT

	IF flagPlayerFlyingHarrier = TRUE
	AND flagReorganisedPlanes = FALSE
		// Start by setting all enemy plane's alive to false
		REPEAT MANSION2_MAX_ENEMY_PLANES nLoop
			flagEnemyPlanesAlive[nLoop] = FALSE
		ENDREPEAT

		// Record the player's plane and the enemy planes
		nTempInt = 0
		REPEAT MANSION2_MAX_PLANES nLoop
			// ...the takeoff plane is special in that it'll be deleted (if not already deleted) and re-created later
			IF nLoop = MANSION2_PLANE_TAKEOFF
				IF NOT IS_CAR_DEAD carPlanes[nLoop]
					// ...plane is not dead, so kill it
					IF IS_PLAYBACK_GOING_ON_FOR_CAR carPlanes[nLoop]
						STOP_PLAYBACK_RECORDED_CAR carPlanes[nLoop]
					ENDIF

					IF NOT IS_CHAR_IN_CAR scplayer carPlanes[nLoop]
						DELETE_CAR carPlanes[nLoop]
					ENDIF
				ENDIF
			ENDIF

			// ...the landing plane is special in that it should be made to land immediately
			IF nLoop = MANSION2_PLANE_BIG_LIFT
				IF NOT IS_CAR_DEAD carPlanes[nLoop]
					IF NOT IS_CHAR_IN_CAR scplayer carPlanes[nLoop]
						// ...plane is not dead, so stop the recording if it is still going on
						IF NOT statusHarrierLanding = -1
							STOP_PLAYBACK_RECORDED_CAR carPlanes[nLoop]
							SET_CAR_COORDINATES carPlanes[nLoop] -1457.6272 502.2539 9.0078
							SET_CAR_HEADING carPlanes[nLoop] 185.0690
							statusHarrierLanding = -1
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF NOT IS_CAR_DEAD carPlanes[nLoop]
				// ...this plane will either be an enemy plane or the player's plane, so store it in the appropriate place
				IF IS_CHAR_IN_CAR scplayer carPlanes[nLoop]
					// ...player's plane
					STORE_CAR_CHAR_IS_IN scplayer carPlayerPlane
				ELSE
					// ...an enemy plane
					carEnemyPlanes[nTempInt] = carPlanes[nLoop]
					flagEnemyPlanesAlive[nTempInt] = TRUE
					nTempInt++
				ENDIF
			ENDIF
		ENDREPEAT

		flagReorganisedPlanes = TRUE
	ENDIF


	// Update AI
	GOSUB Mansion2_Maintain_Player
	GOSUB Mansion2_Maintain_GuardAI
	GOSUB Mansion2_Maintain_MechanicAI


	// Clear all the weapon damage taken so that I can detect if an AI got shot by the player
	// ...guards
	REPEAT MANSION2_MAX_GUARDS nLoop
		IF NOT IS_CHAR_DEAD charGuards[nLoop]
			CLEAR_CHAR_LAST_WEAPON_DAMAGE charGuards[nLoop]
		ENDIF
	ENDREPEAT

	// ...mechanics
	REPEAT MANSION2_MAX_MECHANICS nLoop
		IF NOT IS_CHAR_DEAD charMechanics[nLoop]
			CLEAR_CHAR_LAST_WEAPON_DAMAGE charMechanics[nLoop]
		ENDIF
	ENDREPEAT

	// ...forklifts
	REPEAT MANSION2_MAX_FORKLIFTS nLoop
		IF NOT IS_CAR_DEAD carForklifts[nLoop]
			CLEAR_CAR_LAST_WEAPON_DAMAGE carForklifts[nLoop]
		ENDIF
	ENDREPEAT


	// Update Takeoff Harrier status
	GOSUB Mansion2_Takeoff_Harrier


	// Update Harrier Landing status
	GOSUB Mansion2_Landing_Harrier


	// Trigger the Alarm
	IF flagDisplayedOnAlertText = FALSE
		IF flagCarrierOnAlert = TRUE
			SWITCH nReasonForDetection
				CASE MANSION2_DETECTED_TOO_MUCH_NOISE
					PRINT_NOW MAN2_47 7000 1
					BREAK

				CASE MANSION2_DETECTED_BY_MECHANIC
					PRINT_NOW MAN2_49 7000 1
					BREAK

				CASE MANSION2_DETECTED_BY_GUARD
					PRINT_NOW MAN2_40 7000 1
					BREAK

				CASE MANSION2_DETECTED_DEAD_BODY_BY_MECHANIC
					PRINT_NOW MAN2_41 7000 1
					BREAK

				CASE MANSION2_DETECTED_DEAD_BODY_BY_GUARD
					PRINT_NOW MAN2_42 7000 1
					BREAK

				CASE MANSION2_DETECTED_EXPLOSION
					PRINT_NOW MAN2_43 7000 1
					BREAK
			ENDSWITCH

			// Start the alarm looping audio
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_VERTICAL_BIRD_ALARM_START
			flagCarrierOnAlertAudioPlaying = TRUE

			flagDisplayedOnAlertText = TRUE
		ENDIF
	ENDIF


	// Check for a fire on the Aircraft Carrier (this will usually be caused by an explosion)
	IF flagCarrierOnAlert = FALSE
		GET_NUMBER_OF_FIRES_IN_AREA -1471.0000 484.0000 0.0 -1246.0000 523.0000 40.0 nTempInt
		IF nTempInt > 0
			nReasonForDetection = MANSION2_DETECTED_EXPLOSION
			flagCarrierOnAlert = TRUE
		ENDIF
	ENDIF


	// Check if the player has reached the Harrier area
	IF flagReachedControlRoom = TRUE
		IF flagDisplayedGotoHarrierText = FALSE
			IF IS_CHAR_IN_AREA_3D scplayer -1466.9733 488.6672 7.1953 -1393.2196 521.5200 11.3953 FALSE
				REMOVE_BLIP blipDestination

				// ...steal a harrier
				PRINT_NOW MAN2_25 5000 1  // ~s~Steal one of the military jets.

				flagDisplayedGotoHarrierText = TRUE
			ENDIF
		ENDIF
	ENDIF


	// Radio controls
	IF flagInForklift = TRUE
		// ...was in forklift. Check if still in forklift
		IF NOT IS_CHAR_IN_MODEL scplayer FORKLIFT
			// ...no longer in forklift so switch radio off for the next vehicle (which should be the harrier)
			SET_RADIO_CHANNEL RS_OFF
			flagInForklift = FALSE
		ENDIF
	ELSE
		// ...was not in forklift, so check if now in forklift
		IF IS_CHAR_IN_MODEL scplayer FORKLIFT
			// ...now in forklift
			flagInForklift = TRUE
		ENDIF
	ENDIF


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Mansion2_Next_Stage
	ENDIF

RETURN


// ****************************************
// STAGE: Destroy Spy Boats

Mansion2_Destroy_Spy_Boats:

	IF IS_CAR_DEAD carPlayerPlane
		m_failed = TRUE
		m_fail_reason = MANSION2_FAILED_HARRIER_DEAD
		RETURN
	ENDIF


   	// Initialisation
	// --------------

   	IF m_goals = 0
		// Create Spy Ships
		// ...first ship
		nTempInt = 0
		CLEAR_AREA -1178.2198 2775.0396 40.0 20.0 TRUE
		CREATE_CAR TROPIC -1178.2198 2775.0396 40.0 carSpyShips[nTempInt]
		SET_CAR_HEALTH carSpyShips[nTempInt] 500
		SET_CAR_HEADING carSpyShips[nTempInt] 327.1833
		ANCHOR_BOAT carSpyShips[nTempInt] TRUE
		FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION carSpyShips[nTempInt] TRUE
		ADD_BLIP_FOR_CAR carSpyShips[nTempInt] blipSpyShips[nTempInt]
		CHANGE_BLIP_COLOUR blipSpyShips[nTempInt] YELLOW
		flagSpyShipsAlive[nTempInt] = TRUE

		// ...second ship
		nTempInt++
		CLEAR_AREA -1168.9192 2795.9902 40.0 20.0 TRUE
		CREATE_CAR TROPIC -1168.9192 2795.9902 40.0 carSpyShips[nTempInt]
		SET_CAR_HEALTH carSpyShips[nTempInt] 500
		SET_CAR_HEADING carSpyShips[nTempInt] 171.1833
		ANCHOR_BOAT carSpyShips[nTempInt] TRUE
		FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION carSpyShips[nTempInt] TRUE
		ADD_BLIP_FOR_CAR carSpyShips[nTempInt] blipSpyShips[nTempInt]
		CHANGE_BLIP_COLOUR blipSpyShips[nTempInt] YELLOW
		flagSpyShipsAlive[nTempInt] = TRUE

		// ...third ship
		nTempInt++
		CLEAR_AREA -930.1593 2641.6047 40.0 20.0 TRUE
		CREATE_CAR TROPIC -930.1593 2641.6047 40.0 carSpyShips[nTempInt]
		SET_CAR_HEALTH carSpyShips[nTempInt] 500
		SET_CAR_HEADING carSpyShips[nTempInt] 124.0961
		ANCHOR_BOAT carSpyShips[nTempInt] TRUE
		FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION carSpyShips[nTempInt] TRUE
		ADD_BLIP_FOR_CAR carSpyShips[nTempInt] blipSpyShips[nTempInt]
		CHANGE_BLIP_COLOUR blipSpyShips[nTempInt] YELLOW
		flagSpyShipsAlive[nTempInt] = TRUE

		// ...fourth ship
		nTempInt++
		CLEAR_AREA -973.0267 2484.0210 40.0 20.0 TRUE
		CREATE_CAR TROPIC -973.0267 2484.0210 40.0 carSpyShips[nTempInt]
		SET_CAR_HEALTH carSpyShips[nTempInt] 500
		SET_CAR_HEADING carSpyShips[nTempInt] 171.8058
		ANCHOR_BOAT carSpyShips[nTempInt] TRUE
		FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION carSpyShips[nTempInt] TRUE
		ADD_BLIP_FOR_CAR carSpyShips[nTempInt] blipSpyShips[nTempInt]
		CHANGE_BLIP_COLOUR blipSpyShips[nTempInt] YELLOW
		flagSpyShipsAlive[nTempInt] = TRUE


		flagRaiseLifts 					= TRUE
		flagEnemyPlanesCreatedInAir		= FALSE
//		flagFlashSpyShipsBlips			= TRUE
//		flagSpyShipsBlipsFlashOn		= FALSE
		flagAllSpyShipsDead				= FALSE
		flagAllHarriersDead				= FALSE
		flagLeftCarrierCutscenePlayed	= FALSE
		flagDisplayedDogfightHelp 		= FALSE
		flagDisplayedDogfightHelp2		= FALSE
		flagAllowSpyShipHelpText		= FALSE
		flagDisplayedSpyShipHelp		= FALSE
		flagDisplayedSpyShipHelp2		= FALSE
		flagDisplayedSpyShipHelp2		= FALSE
//		timerSpyShipsBlips				= 0
		

		
		// Play lift audio
		IF flagCarrierSmallLiftAudioPlaying = FALSE
	   		REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierSmallLift SOUND_VERTICAL_BIRD_LIFT_START
	   		flagCarrierSmallLiftAudioPlaying = TRUE
		ENDIF

		IF flagCarrierBigLiftAudioPlaying = FALSE
	   		REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierBigLift SOUND_VERTICAL_BIRD_LIFT_START
	   		flagCarrierBigLiftAudioPlaying = TRUE
		ENDIF


		// Cancel Harrier Alarm audio
		IF flagCarrierOnAlertAudioPlaying = TRUE
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_VERTICAL_BIRD_ALARM_STOP
			flagCarrierOnAlertAudioPlaying = FALSE
		ENDIF


		// Give the player's harrier some more health
		GET_CAR_HEALTH carPlayerPlane nTempInt
		nTempInt += 1000
		SET_CAR_HEALTH carPlayerPlane nTempInt


		nRequiredConversationID = MANSION2_CONVERSATION_STOLEN_HARRIER
		GOSUB Mansion2_Conversation_Command_Prepare

		SET_RADAR_ZOOM 0

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------


	// Subgoals
	// --------

	// Check for triggering Toreno 'well done' speech
	IF m_goals = 1
		IF NOT nCurrentConversationID = MANSION2_CONVERSATION_STOLEN_HARRIER
			nRequiredConversationID = MANSION2_CONVERSATION_STOLEN_HARRIER
			GOSUB Mansion2_Conversation_Command_Prepare
		ENDIF

		IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer -1343.5 504.1 30.0 250.0 250.0 200.0 FALSE
		AND nCurrentConversationID = MANSION2_CONVERSATION_STOLEN_HARRIER
			// ...player a distance away from Carrier, so trigger cutscene
			GOSUB Mansion2_Conversation_Command_Play

			flagLeftCarrierCutscenePlayed = TRUE

			// Make sure the player gets a 6* rating again whenever he sets foot on the aircraft carrier
			SET_DISABLE_MILITARY_ZONES FALSE

			m_goals++
		ENDIF
	ENDIF


	// Check if Toreno speech finished
	IF m_goals = 2
		IF IS_BIT_SET bitsConversationsPlayed MANSION2_CONVERSATION_STOLEN_HARRIER
			// Prepare the next speech
			nRequiredConversationID = MANSION2_CONVERSATION_VAPOURISED
			GOSUB Mansion2_Conversation_Command_Prepare

			m_goals++
		ENDIF
	ENDIF


	// Check if speech ready to play
	IF m_goals = 3
		IF NOT nCurrentConversationID = MANSION2_CONVERSATION_VAPOURISED
			nRequiredConversationID = MANSION2_CONVERSATION_VAPOURISED
			GOSUB Mansion2_Conversation_Command_Prepare
		ENDIF

		IF nCurrentConversationID = MANSION2_CONVERSATION_VAPOURISED
			// Create all the jets
			// First of all, need to re-create the TAKEOFF jet
			flagTempFlag = FALSE
			REPEAT MANSION2_MAX_ENEMY_PLANES nLoop
				IF flagTempFlag = FALSE
					IF flagEnemyPlanesAlive[nLoop] = FALSE
						// ...recreate the plane here
						CREATE_CAR HYDRA -1333.2310 507.6329 17.2266 carEnemyPlanes[nLoop]
						flagEnemyPlanesAlive[nLoop] = TRUE

						flagTempFlag = TRUE
					ENDIF
				ENDIF
			ENDREPEAT

			// ...first jet
			nTempInt = 0
			IF flagEnemyPlanesAlive[nTempInt] = TRUE
				IF NOT IS_CAR_DEAD carEnemyPlanes[nTempInt]
					SET_CAR_COORDINATES carEnemyPlanes[nTempInt] -1293.86 481.91 81.18
					SET_CAR_HEADING carEnemyPlanes[nTempInt] 0.0
					SET_CAR_STATUS carEnemyPlanes[nTempInt] STATUS_PHYSICS
					SET_CAR_FORWARD_SPEED carEnemyPlanes[nTempInt] 30.0
					SET_PLANE_THROTTLE carEnemyPlanes[nTempInt] 1.5
					PLANE_STARTS_IN_AIR carEnemyPlanes[nTempInt]
					PLANE_FOLLOW_ENTITY carEnemyPlanes[nTempInt] scplayer -1 70.0

					ADD_BLIP_FOR_CAR carEnemyPlanes[nTempInt] blipEnemyPlanes[nTempInt]
					CHANGE_BLIP_DISPLAY blipEnemyPlanes[nTempInt] BLIP_ONLY
				ENDIF
			ENDIF

			// ...second jet
			nTempInt++
			IF flagEnemyPlanesAlive[nTempInt] = TRUE
				IF NOT IS_CAR_DEAD carEnemyPlanes[nTempInt]
					SET_CAR_COORDINATES carEnemyPlanes[nTempInt] -1273.86 481.91 81.18
					SET_CAR_HEADING carEnemyPlanes[nTempInt] 0.0
					SET_CAR_STATUS carEnemyPlanes[nTempInt] STATUS_PHYSICS
					SET_CAR_FORWARD_SPEED carEnemyPlanes[nTempInt] 30.0
					SET_PLANE_THROTTLE carEnemyPlanes[nTempInt] 1.5
					PLANE_STARTS_IN_AIR carEnemyPlanes[nTempInt]
					PLANE_FOLLOW_ENTITY carEnemyPlanes[nTempInt] scplayer -1 70.0

					ADD_BLIP_FOR_CAR carEnemyPlanes[nTempInt] blipEnemyPlanes[nTempInt]
					CHANGE_BLIP_DISPLAY blipEnemyPlanes[nTempInt] BLIP_ONLY
				ENDIF
			ENDIF

			// ...third jet
			nTempInt++
			IF flagEnemyPlanesAlive[nTempInt] = TRUE
				IF NOT IS_CAR_DEAD carEnemyPlanes[nTempInt]
					SET_CAR_COORDINATES carEnemyPlanes[nTempInt] -1253.86 481.91 81.18
					SET_CAR_HEADING carEnemyPlanes[nTempInt] 0.0
					SET_CAR_STATUS carEnemyPlanes[nTempInt] STATUS_PHYSICS
					SET_CAR_FORWARD_SPEED carEnemyPlanes[nTempInt] 30.0
					SET_PLANE_THROTTLE carEnemyPlanes[nTempInt] 1.5
					PLANE_STARTS_IN_AIR carEnemyPlanes[nTempInt]
					PLANE_FOLLOW_ENTITY carEnemyPlanes[nTempInt] scplayer -1 70.0

					ADD_BLIP_FOR_CAR carEnemyPlanes[nTempInt] blipEnemyPlanes[nTempInt]
					CHANGE_BLIP_DISPLAY blipEnemyPlanes[nTempInt] BLIP_ONLY
				ENDIF
			ENDIF

			flagEnemyPlanesCreatedInAir = TRUE

			timerCutscene = m_mission_timer + 1000

			m_goals++
		ENDIF
	ENDIF


	// Check if it is time to trigger the vapourised speech
	IF m_goals = 4
		IF timerCutscene < m_mission_timer
			// Switch to Flyby Camera
			nTempInt = -1
			IF flagEnemyPlanesAlive[0] = TRUE
				IF NOT IS_CAR_DEAD carEnemyPlanes[0]
					nTempInt = 0
				ENDIF
			ENDIF

			IF nTempInt = -1
				IF flagEnemyPlanesAlive[1] = TRUE
					IF NOT IS_CAR_DEAD carEnemyPlanes[1]
						nTempInt = 1
					ENDIF
				ENDIF
			ENDIF

			IF NOT nTempInt = -1
				SET_FIXED_CAMERA_POSITION -1297.6329 489.9132 74.9315 0.0 0.0 0.0
				POINT_CAMERA_AT_CAR carEnemyPlanes[nTempInt] FIXED JUMP_CUT
				IF IS_CHAR_IN_CAR scplayer carPlayerPlane
					FREEZE_CAR_POSITION carPlayerPlane TRUE
				ENDIF

				SWITCH_WIDESCREEN ON
				SET_PLAYER_CONTROL player1 OFF
			ENDIF

			GOSUB Mansion2_Conversation_Command_Play

			m_goals++
		ENDIF
	ENDIF


	// Check if the vapourised speech has finished
	IF m_goals = 5
		IF IS_BIT_SET bitsConversationsPlayed MANSION2_CONVERSATION_VAPOURISED
			// Prepare the next speech
			nRequiredConversationID = MANSION2_CONVERSATION_DESTROY_FLOTILLA
			GOSUB Mansion2_Conversation_Command_Prepare

			m_goals++
		ENDIF
	ENDIF


	// Check for the next speech being ready to play
	IF m_goals = 6
		IF NOT nCurrentConversationID = MANSION2_CONVERSATION_DESTROY_FLOTILLA
			nRequiredConversationID = MANSION2_CONVERSATION_DESTROY_FLOTILLA
			GOSUB Mansion2_Conversation_Command_Prepare
		ENDIF

		IF nCurrentConversationID = MANSION2_CONVERSATION_DESTROY_FLOTILLA
			// ...ready to play, so switch back from flyby camera
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL player1 ON
			
			RESTORE_CAMERA_JUMPCUT
			SET_CAMERA_BEHIND_PLAYER
			IF IS_CHAR_IN_CAR scplayer carPlayerPlane
				FREEZE_CAR_POSITION carPlayerPlane FALSE
				SET_CAR_FORWARD_SPEED carPlayerPlane 30.0
				SET_PLANE_THROTTLE carPlayerPlane 1.0
			ENDIF

			// Wait briefly before triggering the rest of the conversation
			timerCutscene = m_mission_timer + 500

			m_goals++
		ENDIF
	ENDIF


	// Trigger the rest of the conversation
	IF m_goals = 7
		IF timerCutscene < m_mission_timer
			GOSUB Mansion2_Conversation_Command_Play
			m_goals++
		ENDIF
	ENDIF


	// If the speech has finished then display help text for combat
	IF m_goals = 8
		IF IS_BIT_SET bitsConversationsPlayed MANSION2_CONVERSATION_DESTROY_FLOTILLA
			// Dogfight help
			PRINT_HELP_FOREVER MAN2_71
			flagDisplayedDogfightHelp = TRUE
			timerHelpText = m_mission_timer + 15000

			// Delay before Harriers attack
			timerCutscene = m_mission_timer + 5000

			m_goals++
		ENDIF
	ENDIF


	// Activate the enemy harriers
	IF m_goals = 9
		IF timerCutscene < m_mission_timer
			nTempInt = 0
			REPEAT MANSION2_MAX_ENEMY_PLANES nLoop
				IF flagEnemyPlanesAlive[nLoop] = TRUE
					IF NOT IS_CAR_DEAD carEnemyPlanes[nLoop]
						SET_PLANE_THROTTLE carEnemyPlanes[nLoop] 0.5
						PLANE_ATTACK_PLAYER_USING_DOG_FIGHT carEnemyPlanes[nLoop] player1 30.0
						nTempInt++
					ENDIF
				ENDIF
			ENDREPEAT

			IF nTempInt > 0
				IF nTempInt = 1
					PRINT_NOW MAN2_36 7000 1  // ~s~There is a ~r~military jet~s~ after you. Take it out!
				ELSE
					PRINT_NOW MAN2_35 7000 1  // ~s~There are ~r~military jets~s~ on your tail. Take them out!
				ENDIF
			ENDIF

			m_goals++
		ENDIF
	ENDIF


	// Check if the spy ships have all been destroyed
	IF m_goals = 10
		IF flagAllSpyShipsDead = TRUE
		AND flagAllHarriersDead = TRUE
			m_goals = 99
		ENDIF
	ENDIF



	// Continuous updates
	// ------------------

	// Spy Ships alive
	flagTempFlag = FALSE
	nTempInt = 0
	REPEAT MANSION2_MAX_SPY_SHIPS nLoop
		IF flagSpyShipsAlive[nLoop] = TRUE
			IF IS_CAR_DEAD carSpyShips[nLoop]
				flagSpyShipsAlive[nLoop] = FALSE
				REMOVE_BLIP blipSpyShips[nLoop]
				flagTempFlag = TRUE
			ELSE
				nTempInt++
			ENDIF
		ENDIF
	ENDREPEAT

	IF flagTempFlag = TRUE
		IF nTempInt > 0
			IF nTempInt = 1
				// ...singular
				PRINT_WITH_NUMBER_NOW MAN2_30 nTempInt 5000 1
			ELSE
				// ...plural
				PRINT_WITH_NUMBER_NOW MAN2_29 nTempInt 5000 1
			ENDIF
		ELSE
			IF flagAllHarriersDead = FALSE
				PRINT_NOW MAN2_44 5000 1
			ENDIF
		ENDIF

		// If the Spy Ship help text hasn't been displayed, then don't display it
		IF flagDisplayedSpyShipHelp = FALSE
			flagDisplayedSpyShipHelp = TRUE
			flagDisplayedSpyShipHelp2 = TRUE
			flagDisplayedSpyShipHelp2 = TRUE
		ENDIF
	ENDIF


	// Are all spy ships dead?
	flagAllSpyShipsDead = TRUE
	REPEAT MANSION2_MAX_SPY_SHIPS nLoop
		IF flagSpyShipsAlive[nLoop] = TRUE
			flagAllSpyShipsDead = FALSE
		ENDIF
	ENDREPEAT


	// Update the Harriers
	flagTempFlag = FALSE
	nTempInt = 0
	REPEAT MANSION2_MAX_ENEMY_PLANES nLoop
		IF flagEnemyPlanesAlive[nLoop] = TRUE
			IF IS_CAR_DEAD carEnemyPlanes[nLoop]
				flagEnemyPlanesAlive[nLoop] = FALSE
				MARK_CAR_AS_NO_LONGER_NEEDED carEnemyPlanes[nLoop]
				REMOVE_BLIP blipEnemyPlanes[nLoop]
				IF flagEnemyPlanesCreatedInAir = TRUE
					flagTempFlag = TRUE
				ENDIF
			ELSE
				nTempInt++
			ENDIF
		ENDIF
	ENDREPEAT

	IF flagTempFlag = TRUE
		IF nTempInt > 0
			PRINT_WITH_NUMBER_NOW MAN2_27 nTempInt 5000 1
		ELSE
			flagAllHarriersDead = TRUE
			IF flagAllSpyShipsDead = FALSE
				PRINT_NOW MAN2_28 5000 1
				flagAllowSpyShipHelpText = TRUE
			ENDIF
		ENDIF
	ENDIF


	// Raise Lifts
	IF flagRaiseLifts = TRUE
		IF SLIDE_OBJECT objectCarrierSmallLift -1414.453 516.453 16.688 0.05 0.05 0.05 FALSE
		AND SLIDE_OBJECT objectCarrierBigLift -1456.719 501.297 16.953 0.05 0.05 0.05 FALSE
			flagRaiseLifts = FALSE

			IF flagCarrierSmallLiftAudioPlaying = TRUE
		   		REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierSmallLift SOUND_VERTICAL_BIRD_LIFT_STOP
		   		flagCarrierSmallLiftAudioPlaying = FALSE
			ENDIF

			IF flagCarrierBigLiftAudioPlaying = TRUE
		   		REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierBigLift SOUND_VERTICAL_BIRD_LIFT_STOP
		   		flagCarrierBigLiftAudioPlaying = FALSE
			ENDIF
		ENDIF
	ENDIF


	// Player Blip Maintenance
	IF NOT IS_CHAR_IN_CAR scplayer carPlayerPlane
		IF NOT DOES_BLIP_EXIST blipPlayerPlane
			PRINT_NOW MAN2_37 5000 1
			ADD_BLIP_FOR_CAR carPlayerPlane blipPlayerPlane
			SET_BLIP_AS_FRIENDLY blipPlayerPlane TRUE
		ELSE
			IF flagLeftCarrierCutscenePlayed = FALSE
				// ...check if the player has instead climbed into one of the other hydras
				flagTempFlag = FALSE
				REPEAT MANSION2_MAX_ENEMY_PLANES nLoop
					IF flagEnemyPlanesAlive[nLoop] = TRUE
						IF flagTempFlag = FALSE
							IF NOT IS_CAR_DEAD carEnemyPlanes[nLoop]
								IF IS_CHAR_IN_CAR scplayer carEnemyPlanes[nLoop]
									// Swap the enemy plane and the player plane
									car = carEnemyPlanes[nLoop]
									carEnemyPlanes[nLoop] = carPlayerPlane
									carPlayerPlane = car
									flagTempFlag = TRUE
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDREPEAT
			ENDIF
		ENDIF
	ELSE
		IF DOES_BLIP_EXIST blipPlayerPlane
			REMOVE_BLIP blipPlayerPlane
			CLEAR_THIS_PRINT MAN2_37
		ENDIF
	ENDIF


	// Has help text 2 been displayed?
	IF flagDisplayedFlyingHelp2 = FALSE
		IF timerHelpText < m_mission_timer
			CLEAR_HELP
			
			PRINT_HELP_FOREVER MAN2_99
			
			flagDisplayedFlyingHelp2 = TRUE
			timerHelpText = m_mission_timer + 10000
		ENDIF
	ENDIF


	// Has flying help 2 timed out?
	IF flagDisplayedFlyingHelp2 = TRUE
	AND flagDisplayedDogfightHelp = FALSE
		IF NOT timerHelpText = 0
			IF timerHelpText < m_mission_timer
				timerHelpText = 0
				CLEAR_HELP
			ENDIF
		ENDIF
	ENDIF


	// Has Dogfight Help text timed out?
	IF flagDisplayedDogfightHelp = TRUE
		IF flagDisplayedDogfightHelp2 = FALSE
			IF timerHelpText < m_mission_timer
				CLEAR_HELP
				PRINT_HELP_FOREVER MAN2_98  
				flagDisplayedDogfightHelp2 = TRUE
				timerHelpText = m_mission_timer + 10000
			ENDIF
		ENDIF
	ENDIF


	// Has dogfight help 2 timed out?
	IF flagDisplayedDogfightHelp2 = TRUE
		IF NOT timerHelpText = 0
			IF timerHelpText < m_mission_timer
				timerHelpText = 0
				CLEAR_HELP
			ENDIF
		ENDIF
	ENDIF


	// Is it time to display the spyship help?
	IF flagAllowSpyShipHelpText = TRUE
		IF flagDisplayedSpyShipHelp = FALSE
			IF flagDisplayedDogfightHelp2 = TRUE
			AND timerHelpText = 0
				// ...the dogfight2 text has been removed
				// Check if the player is near any of the spyships
				IF IS_CHAR_IN_CAR scplayer carPlayerPlane
					flagTempFlag = FALSE
					REPEAT MANSION2_MAX_SPY_SHIPS nLoop
						IF flagTempFlag = FALSE
							IF flagSpyShipsAlive[nLoop] = TRUE
								IF LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer carSpyShips[nLoop] 300.0 300.0 300.0 FALSE
									flagTempFlag = TRUE
								ENDIF
							ENDIF
						ENDIF
					ENDREPEAT

					IF flagTempFlag = TRUE
						// ...player near one of the spy ships, so display help text
						CLEAR_HELP
						PRINT_HELP_FOREVER HYDHLP1  // Use the steering controls to tilt the plane forward and back.
						flagDisplayedSpyShipHelp = TRUE
						timerHelpText = m_mission_timer + 10000
					ENDIF
				ENDIF
			ENDIF
		ENDIF


		// Has SpyShip Help text timed out?
		IF flagDisplayedSpyShipHelp = TRUE
			IF flagDisplayedSpyShipHelp2 = FALSE
				IF timerHelpText < m_mission_timer
					CLEAR_HELP
					PRINT_HELP_FOREVER HYDHLP2  
					flagDisplayedSpyShipHelp2 = TRUE
					timerHelpText = m_mission_timer + 10000
				ENDIF
			ENDIF
		ENDIF


		// Has SpyShip help 2 timed out?
		IF flagDisplayedSpyShipHelp2 = TRUE
			IF flagDisplayedSpyShipHelp3 = FALSE
				IF timerHelpText < m_mission_timer
					CLEAR_HELP
					PRINT_HELP_FOREVER HYDHLP3 
					flagDisplayedSpyShipHelp3 = TRUE
					timerHelpText = m_mission_timer + 10000
				ENDIF
			ENDIF
		ENDIF


		// Has SpyShip help 3 timed out?
		IF flagDisplayedSpyShipHelp3 = TRUE
			IF NOT timerHelpText = 0
				IF timerHelpText < m_mission_timer
					timerHelpText = 0
					CLEAR_HELP
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Mansion2_Next_Stage
	ENDIF

RETURN


// ****************************************
// STAGE: Land Harrier

Mansion2_Land_Harrier:

	IF IS_CAR_DEAD carPlayerPlane
		m_failed = TRUE
		m_fail_reason = MANSION2_FAILED_HARRIER_DEAD
		RETURN
	ENDIF

   	// Initialisation
	// --------------

   	IF m_goals = 0
		// Setup Landing Strip
		xloLand = -76.4228
		yloLand = 2478.6396
		zloLand = 14.2844
		xhiLand = 433.6483
		yhiLand = 2525.6577
		zhiLand = 18.6279

		// Setup Blip Position
		xposDestination = xhiLand - xloLand
		xposDestination = xposDestination / 2.0
		xposDestination = xposDestination + xloLand
		yposDestination = yhiLand - yloLand
		yposDestination = yposDestination / 2.0
		yposDestination = yposDestination + yloLand
		zposDestination = zhiLand - zloLand
		zposDestination = zposDestination / 2.0
		zposDestination = zposDestination + zloLand

		ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination

		// Prepare the final speech
		nRequiredConversationID = MANSION2_CONVERSATION_FLOTILLA_DESTROYED
		GOSUB Mansion2_Conversation_Command_Prepare
		timerCutscene = m_mission_timer + 1000

		flagDisplayedLandingHelp = FALSE

		// Request the models for Toreno's special weapons
		REQUEST_MODEL ROCKETLA
		REQUEST_MODEL HEATSEEK
		REQUEST_MODEL MINIGUN
		REQUEST_MODEL FLAME

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------


	// Subgoals
	// --------

	// After a pause, start the conversation
	IF m_goals = 1
		IF NOT nCurrentConversationID = MANSION2_CONVERSATION_FLOTILLA_DESTROYED
			nRequiredConversationID = MANSION2_CONVERSATION_FLOTILLA_DESTROYED
			GOSUB Mansion2_Conversation_Command_Prepare
		ENDIF

		IF nCurrentConversationID = MANSION2_CONVERSATION_FLOTILLA_DESTROYED
		AND timerCutscene < m_mission_timer
			GOSUB Mansion2_Conversation_Command_Play

			m_goals++
		ENDIF
	ENDIF


	// Check if the conversation has ended
	IF m_goals = 2
		IF IS_BIT_SET bitsConversationsPlayed MANSION2_CONVERSATION_FLOTILLA_DESTROYED
			PRINT_NOW MAN2_26 10000 1

			m_goals++
		ENDIF
	ENDIF


	// Has the player landed on the landing strip?
	IF m_goals = 3
		// A Locate to show where the runway is
		LOCATE_CHAR_ANY_MEANS_3D scplayer xposDestination yposDestination zposDestination 10.0 10.0 10.0 TRUE

		// The real test to see if the player is in the area
		IF IS_CHAR_IN_AREA_3D scplayer xloLand yloLand zloLand xhiLand yhiLand zhiLand FALSE
		AND IS_CHAR_IN_CAR scplayer carPlayerPlane
			IF IS_CAR_STOPPED carPlayerPlane
			AND NOT IS_CAR_IN_AIR_PROPER carPlayerPlane
				REMOVE_BLIP blipDestination

				m_goals++
			ENDIF
		ELSE
			fTempFloat = zhiLand + 20.0
			IF IS_CHAR_IN_AREA_3D scplayer xloLand yloLand zloLand xhiLand yhiLand fTempFloat FALSE
			AND IS_CHAR_IN_CAR scplayer carPlayerPlane
			AND flagDisplayedLandingHelp = FALSE
				GET_PLANE_UNDERCARRIAGE_POSITION carPlayerPlane fTempFloat2
				IF fTempFloat2 > 0.1
					PRINT_HELP MAN2_72
					flagDisplayedLandingHelp = TRUE
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Show the hanger blip
	IF m_goals = 4
		xposDestination = 291.3149
		yposDestination = 2535.2493
		zposDestination = 15.8205

		ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination

		PRINT_NOW MAN2_33 5000 1

		m_goals++
	ENDIF


	// Has the player guided the plane into the hanger?
	IF m_goals = 5
		IF IS_CHAR_IN_CAR scplayer carPlayerPlane
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer xposDestination yposDestination zposDestination 10.0 10.0 10.0 TRUE
				IF IS_CAR_STOPPED carPlayerPlane
					REMOVE_BLIP blipDestination

					m_goals = 99
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Mansion2_Next_Stage
	ENDIF

RETURN




// *************************************************************************************************************
//										MISSION SPECIFIC GOSUBS  
// *************************************************************************************************************


// *************************************************************************************************************
//								               Player
// *************************************************************************************************************

// ****************************************
// Maintain Player
Mansion2_Maintain_Player:

	flagPlayerFiringSilencedGun = FALSE

	IF flagPlayerFiringGun = TRUE
		RETURN
	ENDIF

	IF IS_CHAR_SHOOTING scplayer
		IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PISTOL_SILENCED
			flagPlayerFiringSilencedGun = TRUE
		ELSE
			flagPlayerFiringGun = TRUE
		ENDIF
	ENDIF

RETURN


// *************************************************************************************************************
//								               Guard AI
// *************************************************************************************************************

// ****************************************
// Maintain Guard AI
Mansion2_Maintain_GuardAI:

	// Check for any newly dead guards
	REPEAT MANSION2_MAX_GUARDS nTempPed
		IF NOT aiGuards[nTempPed] = MANSION2_GUARDAI_NOT_INITIALISED
		AND NOT aiGuards[nTempPed] = MANSION2_GUARDAI_DEAD
			IF IS_CHAR_DEAD charGuards[nTempPed]
				aiGuards[nTempPed] = MANSION2_GUARDAI_DEAD
				REMOVE_BLIP blipGuards[nTempPed]
				GOSUB Mansion2_Dead_Guard_Informs_Nearby_Peds
			ENDIF
		ENDIF
	ENDREPEAT


	REPEAT MANSION2_MAX_GUARDS nTempPed
		// Check for a change in the Guard's alert state
		IF flagGuardsAllowAlert[nTempPed] = TRUE
			GOSUB Mansion2_Update_Alert_State
		ENDIF

		SWITCH aiGuards[nTempPed]
			CASE MANSION2_GUARDAI_NOT_INITIALISED
			CASE MANSION2_GUARDAI_DEAD
				// ...nothing to do
				BREAK

			CASE MANSION2_GUARDAI_STEER_BOAT_TO_SHIP
				GOSUB Mansion2_AI_Steer_Boat
				BREAK

			CASE MANSION2_GUARDAI_STEERING_BOAT_TO_SHIP
				GOSUB Mansion2_AI_Steering_Boat
				BREAK

			CASE MANSION2_GUARDAI_STEER_BOAT_INTO_SHIP
				GOSUB Mansion2_AI_Steer_In
				BREAK

			CASE MANSION2_GUARDAI_STEERING_BOAT_INTO_SHIP
				GOSUB Mansion2_AI_Steering_In
				BREAK

			CASE MANSION2_GUARDAI_BOAT_MOORED_IN_SHIP
				GOSUB Mansion2_AI_Moored
				BREAK

			CASE MANSION2_GUARDAI_ACTIVATION
				GOSUB Mansion2_AI_Activation
				BREAK

			CASE MANSION2_GUARDAI_WALK_HOME
				GOSUB Mansion2_AI_Walk_Home
				BREAK

			CASE MANSION2_GUARDAI_WALKING_HOME
				GOSUB Mansion2_AI_Walking_Home
				BREAK

			CASE MANSION2_GUARDAI_CHAT
				GOSUB Mansion2_AI_Chat
				BREAK

			CASE MANSION2_GUARDAI_CHATTING
				GOSUB Mansion2_AI_Chatting
				BREAK

			CASE MANSION2_GUARDAI_SIT
				GOSUB Mansion2_AI_Sit
				BREAK

			CASE MANSION2_GUARDAI_WANDER
				GOSUB Mansion2_AI_Wander
				BREAK

			CASE MANSION2_GUARDAI_WANDERING
				GOSUB Mansion2_AI_Wandering
				BREAK

			CASE MANSION2_GUARDAI_WANDER_STOPPED
				GOSUB Mansion2_AI_WanderStop
				BREAK

			CASE MANSION2_GUARDAI_EXIT_PLANE
				GOSUB Mansion2_AI_ExitPlane
				BREAK

			CASE MANSION2_GUARDAI_EXITING_PLANE
				GOSUB Mansion2_AI_ExitingPlane
				BREAK

			CASE MANSION2_GUARDAI_WATCH_PLANE
				GOSUB Mansion2_AI_WatchPlane
				BREAK

			CASE MANSION2_GUARDAI_WATCHING_PLANE
				GOSUB Mansion2_AI_WatchingPlane
				BREAK

			CASE MANSION2_GUARDAI_TELEPORT
				GOSUB Mansion2_AI_Teleport
				BREAK

			CASE MANSION2_GUARDAI_GOTO_ALERT_POSITION
				GOSUB Mansion2_AI_GotoAlert
				BREAK

			CASE MANSION2_GUARDAI_GOING_TO_ALERT_POSITION
				GOSUB Mansion2_AI_GoingToAlert
				BREAK

			CASE MANSION2_GUARDAI_ORIENTATE_AT_ALERT_POSITION
				GOSUB Mansion2_AI_AlertOrientate
				BREAK

			CASE MANSION2_GUARDAI_AT_ALERT_POSITION
				GOSUB Mansion2_AI_Alert
				BREAK

			CASE MANSION2_GUARDAI_ATTACK
				GOSUB Mansion2_AI_Attack
				BREAK

			CASE MANSION2_GUARDAI_RUN_ATTACK
				GOSUB Mansion2_AI_RunAttack
				BREAK

			CASE MANSION2_GUARDAI_RUNNING_ATTACK
				GOSUB Mansion2_AI_RunningAttack
				BREAK

			CASE MANSION2_GUARDAI_ATTACKING
				GOSUB Mansion2_AI_Attacking
				BREAK

			CASE MANSION2_GUARDAI_TICKING_OVER
				// ...nothing to do for now
				BREAK


			DEFAULT
				WRITE_DEBUG Unknown_Guard_AI_Action
		ENDSWITCH
	ENDREPEAT

RETURN


// ****************************************
// GuardAI: Update Alert State
Mansion2_Update_Alert_State:

	// This updates the alert state for Guards when the ship is not on alert or the guard is not on alert

	// Is the ship on alert?
	IF flagCarrierOnAlert = TRUE
		// ...yes, so check if this Guard is already on alert
		IF flagGuardsOnAlert[nTempPed] = FALSE
			// ...no, so make this guard go on alert
			aiGuards[nTempPed] = MANSION2_GUARDAI_GOTO_ALERT_POSITION
			flagGuardsOnAlert[nTempPed] = TRUE

			IF NOT IS_CHAR_DEAD charGuards[nTempPed]
				SET_CHAR_DECISION_MAKER charGuards[nTempPed] dmTough
			ENDIF
		ENDIF
		RETURN
	ENDIF

	// Can the Guard see the player?
	IF flagGuardsOnAlert[nTempPed] = FALSE
		IF NOT IS_CHAR_DEAD charGuards[nTempPed]
			IF HAS_CHAR_SPOTTED_CHAR_IN_FRONT charGuards[nTempPed] scplayer
				// Restrict the distance
				GET_CHAR_COORDINATES charGuards[nTempPed] xposTemp yposTemp zposTemp
				GET_CHAR_COORDINATES scplayer xposTemp2 yposTemp2 zposTemp2
				GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
				IF fTempDistance < 15.0
					aiGuards[nTempPed] = MANSION2_GUARDAI_ATTACK
					GOSUB Mansion2_Guard_Informs_Nearby_Peds
					RETURN
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// Can the guard see a dead guy?
	IF flagGuardsOnAlert[nTempPed] = FALSE
		IF NOT IS_CHAR_DEAD charGuards[nTempPed]
			IF CAN_CHAR_SEE_DEAD_CHAR charGuards[nTempPed] PEDTYPE_MISSION2
			OR CAN_CHAR_SEE_DEAD_CHAR charGuards[nTempPed] PEDTYPE_MISSION1
				// ...dead body was found
				flagCarrierOnAlert = TRUE

				IF nReasonForDetection = MANSION2_DETECTED_UNKNOWN
					nReasonForDetection = MANSION2_DETECTED_DEAD_BODY_BY_GUARD
				ENDIF
				RETURN
			ENDIF
		ENDIF
	ENDIF

	// Has player fired a gun?
	IF flagGuardsOnAlert[nTempPed] = FALSE
		IF NOT IS_CHAR_DEAD charGuards[nTempPed]
			IF flagPlayerFiringGun = TRUE
				// ...player was heard firing a gun so put the carrier on alert
				flagCarrierOnAlert = TRUE

				IF nReasonForDetection = MANSION2_DETECTED_UNKNOWN
					nReasonForDetection = MANSION2_DETECTED_BY_GUARD
				ENDIF
				RETURN
			ENDIF

			IF flagPlayerFiringSilencedGun = TRUE
				// ...player fired a silenced gun, so check if the guard is close to the player
				GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
				GET_CHAR_COORDINATES charGuards[nTempPed] xposTemp2 yposTemp2 zposTemp2
				GET_DISTANCE_BETWEEN_COORDS_2D xposTemp yposTemp xposTemp2 yposTemp2 fTempFloat
				IF fTempFloat < 12.0
					fTempFloat2 = zposTemp - zposTemp2
					IF fTempFloat2 < 0.0
						fTempFloat2 *= -1.0
					ENDIF

					IF fTempFloat2 < 5.0
						// ...player was heard firing a silenced pistol on the same floor as this ped
						aiGuards[nTempPed] = MANSION2_GUARDAI_ATTACK
						GOSUB Mansion2_Guard_Informs_Nearby_Peds
						RETURN
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// Has this ped been damaged by player?
	IF flagGuardsOnAlert[nTempPed] = FALSE
		IF NOT IS_CHAR_DEAD charGuards[nTempPed]
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR charGuards[nTempPed] scplayer
			OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON charGuards[nTempPed] WEAPONTYPE_PISTOL_SILENCED
				// ...player has damaged this guard
				aiGuards[nTempPed] = MANSION2_GUARDAI_ATTACK
				GOSUB Mansion2_Guard_Informs_Nearby_Peds
				RETURN
			ENDIF
		ENDIF
	ENDIF

RETURN


// ****************************************
// GuardAI: Update Active Alert State
Mansion2_Update_Active_Alert_State:

	// This updates the alert state for Guards when the guard is already on alert

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	// flagTempFlag, if TRUE means make the Guard active and inform nearby peds
	flagTempFlag = FALSE

	// Can the Guard see the player?
	IF HAS_CHAR_SPOTTED_CHAR_IN_FRONT charGuards[nTempPed] scplayer
		flagTempFlag = TRUE
	ENDIF

	// Has player fired a gun close by?
	IF flagTempFlag = FALSE
		IF flagPlayerFiringGun = TRUE
			GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
			GET_CHAR_COORDINATES charGuards[nTempPed] xposTemp2 yposTemp2 zposTemp2
			GET_DISTANCE_BETWEEN_COORDS_2D xposTemp yposTemp xposTemp2 yposTemp2 fTempFloat
			IF fTempFloat < 75.0
				fTempFloat2 = zposTemp - zposTemp2
				IF fTempFloat2 < 0.0
					fTempFloat2 *= -1.0
				ENDIF

				IF fTempFloat2 < 5.0
					flagTempFlag = TRUE
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// Has player fired a silenced gun close by?
	IF flagTempFlag = FALSE
		IF flagPlayerFiringSilencedGun = TRUE
			// ...player fired a silenced gun, so check if the guard is close to the player
			GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
			GET_CHAR_COORDINATES charGuards[nTempPed] xposTemp2 yposTemp2 zposTemp2
			GET_DISTANCE_BETWEEN_COORDS_2D xposTemp yposTemp xposTemp2 yposTemp2 fTempFloat
			IF fTempFloat < 12.0
				fTempFloat2 = zposTemp - zposTemp2
				IF fTempFloat2 < 0.0
					fTempFloat2 *= -1.0
				ENDIF

				IF fTempFloat2 < 5.0
					flagTempFlag = TRUE
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// Has this ped been damaged by player?
	IF flagTempFlag = FALSE
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR charGuards[nTempPed] scplayer
		OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON charGuards[nTempPed] WEAPONTYPE_PISTOL_SILENCED
			flagTempFlag = TRUE
		ENDIF
	ENDIF

	// Should this Ped upgrade his alert state and inform nearby peds?
	IF flagTempFlag = TRUE
		// Upgrade this ped's reactions
		IF NOT aiGuards[nTempPed] = MANSION2_GUARDAI_ATTACK
		AND NOT aiGuards[nTempPed] = MANSION2_GUARDAI_ATTACKING
		AND NOT aiGuards[nTempPed] = MANSION2_GUARDAI_RUN_ATTACK
		AND NOT aiGuards[nTempPed] = MANSION2_GUARDAI_RUNNING_ATTACK
			aiGuards[nTempPed] = MANSION2_GUARDAI_ATTACK
		ENDIF

		// Upgrade nearby ped's reactions
		GOSUB Mansion2_Guard_Informs_Nearby_Peds
	ENDIF

RETURN


// ****************************************
// GuardAI: Guard Informs Nearby Peds
Mansion2_Guard_Informs_Nearby_Peds:

	// Inform nearby Guards and Mechanics that they also should react
	IF NOT IS_CHAR_DEAD charGuards[nTempPed]
		GET_CHAR_COORDINATES charGuards[nTempPed] xposTemp yposTemp zposTemp
	ELSE
		GET_DEAD_CHAR_COORDINATES charGuards[nTempPed] xposTemp yposTemp zposTemp
	ENDIF

	// ...check if any Guards should be informed
	REPEAT MANSION2_MAX_GUARDS nLoop
		IF NOT IS_CHAR_DEAD charGuards[nLoop]
		AND NOT nTempPed = nLoop
			GET_CHAR_COORDINATES charGuards[nLoop] xposTemp2 yposTemp2 zposTemp2
			GET_DISTANCE_BETWEEN_COORDS_2D xposTemp yposTemp xposTemp2 yposTemp2 fTempDistance
			IF fTempDistance < MANSION2_INFORM_PEDS_RANGE_m
				fTempFloat = zposTemp - zposTemp2
				IF fTempFloat < 0.0
					fTempFloat *= -1.0
				ENDIF

				IF fTempFloat < 6.0
					IF flagGuardsAllowAlert[nLoop] = TRUE
						// ...the Guard is allowed to go on alert
						// Make the Guard attack if not already doing so
						IF NOT aiGuards[nLoop] = MANSION2_GUARDAI_ATTACK
						AND NOT aiGuards[nLoop] = MANSION2_GUARDAI_ATTACKING
						AND NOT aiGuards[nLoop] = MANSION2_GUARDAI_RUN_ATTACK
						AND NOT aiGuards[nLoop] = MANSION2_GUARDAI_RUNNING_ATTACK
							aiGuards[nLoop] = MANSION2_GUARDAI_ATTACK
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDREPEAT

	// ...check if any Mechanics should be informed
	REPEAT MANSION2_MAX_MECHANICS nLoop
		IF NOT IS_CHAR_DEAD charMechanics[nLoop]
			GET_CHAR_COORDINATES charMechanics[nLoop] xposTemp2 yposTemp2 zposTemp2
			GET_DISTANCE_BETWEEN_COORDS_2D xposTemp yposTemp xposTemp2 yposTemp2 fTempDistance
			IF fTempDistance < MANSION2_INFORM_PEDS_RANGE_m
				fTempFloat = zposTemp - zposTemp2
				IF fTempFloat < 0.0
					fTempFloat *= -1.0
				ENDIF

				IF fTempFloat < 6.0
					// Make the Mechanic react if not already doing so
					IF flagCarrierOnAlert = FALSE
						// ...if the Carrier is not on alert, make the Mechanic head for the alarm if not already doing so
						IF flagMechanicsOnAlert[nLoop] = FALSE
							// ...mechanic is not already on alert, so put him on alert
							aiMechanics[nLoop] = MANSION2_MECHANICAI_GOTO_ALARM

							// Mechanic has heard Guard's call of alarm
							IF NOT nReasonForDetection = MANSION2_DETECTED_TOO_MUCH_NOISE
								PRINT_NOW MAN2_45 7000 1
							ENDIF

							flagMechanicsOnAlert[nLoop] = TRUE
						ENDIF
					ELSE
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDREPEAT

RETURN


// ****************************************
// GuardAI: Dead Guard Informs Nearby Peds
Mansion2_Dead_Guard_Informs_Nearby_Peds:

	// Nearby Guards and Mechanics should react to this seeing this Guard die
	GET_DEAD_CHAR_COORDINATES charGuards[nTempPed] xposTemp yposTemp zposTemp

	// ...check if any Guards should be informed
	REPEAT MANSION2_MAX_GUARDS nLoop
		IF NOT IS_CHAR_DEAD charGuards[nLoop]
		AND NOT nTempPed = nLoop
			GET_CHAR_COORDINATES charGuards[nLoop] xposTemp2 yposTemp2 zposTemp2
			GET_DISTANCE_BETWEEN_COORDS_2D xposTemp yposTemp xposTemp2 yposTemp2 fTempDistance
			IF fTempDistance < MANSION2_REACT_TO_DEATH_RANGE_m
				fTempFloat = zposTemp - zposTemp2
				IF fTempFloat < 0.0
					fTempFloat *= -1.0
				ENDIF

				IF fTempFloat < 6.0
					IF flagGuardsAllowAlert[nLoop] = TRUE
						// ...the Guard is allowed to go on alert
						// Make the Guard attack if not already doing so
						IF NOT aiGuards[nLoop] = MANSION2_GUARDAI_ATTACK
						AND NOT aiGuards[nLoop] = MANSION2_GUARDAI_ATTACKING
						AND NOT aiGuards[nLoop] = MANSION2_GUARDAI_RUN_ATTACK
						AND NOT aiGuards[nLoop] = MANSION2_GUARDAI_RUNNING_ATTACK
							aiGuards[nLoop] = MANSION2_GUARDAI_ATTACK
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDREPEAT

	// ...check if any Mechanics should be informed
	REPEAT MANSION2_MAX_MECHANICS nLoop
		IF NOT IS_CHAR_DEAD charMechanics[nLoop]
			GET_CHAR_COORDINATES charMechanics[nLoop] xposTemp2 yposTemp2 zposTemp2
			GET_DISTANCE_BETWEEN_COORDS_2D xposTemp yposTemp xposTemp2 yposTemp2 fTempDistance
			IF fTempDistance < MANSION2_REACT_TO_DEATH_RANGE_m
				fTempFloat = zposTemp - zposTemp2
				IF fTempFloat < 0.0
					fTempFloat *= -1.0
				ENDIF

				IF fTempFloat < 6.0
					// Make the Mechanic react if not already doing so
					IF flagCarrierOnAlert = FALSE
						// ...if the Carrier is not on alert, make the Mechanic head for the alarm if not already doing so
						IF flagMechanicsOnAlert[nLoop] = FALSE
							// ...mechanic is not already on alert, so put him on alert
							aiMechanics[nLoop] = MANSION2_MECHANICAI_GOTO_ALARM

							// Mechanic is running for alarm
							IF NOT nReasonForDetection = MANSION2_DETECTED_TOO_MUCH_NOISE
								PRINT_NOW MAN2_45 7000 1
							ENDIF
	
							flagMechanicsOnAlert[nLoop] = TRUE
						ENDIF
					ELSE
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDREPEAT

RETURN


// ****************************************
// GuardAI: Steer Boat To Ship
Mansion2_AI_Steer_Boat:

	IF IS_CAR_DEAD carEnemyBoat
		RETURN
	ENDIF

	// Steer towards the turning position
	ANCHOR_BOAT carEnemyBoat FALSE
	TASK_CAR_DRIVE_TO_COORD charGuards[nTempPed] carEnemyBoat -1493.4399 506.8398 0.0000 20.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
	aiGuards[nTempPed] = MANSION2_GUARDAI_STEERING_BOAT_TO_SHIP

RETURN


// ****************************************
// GuardAI: Steering Boat To Ship
Mansion2_AI_Steering_Boat:

	IF IS_CAR_DEAD carEnemyBoat
		RETURN
	ENDIF

	IF NOT LOCATE_CAR_3D carEnemyBoat -1493.4399 506.8398 0.0000 10.0 15.0 5.0 FALSE
		GET_SCRIPT_TASK_STATUS charGuards[nTempPed] TASK_CAR_DRIVE_TO_COORD m_status

		IF m_status = PERFORMING_TASK
		OR m_status = WAITING_TO_START_TASK
			RETURN
		ENDIF

		// Give the task again
		TASK_CAR_DRIVE_TO_COORD charGuards[nTempPed] carEnemyBoat -1493.4399 506.8398 0.0000 25.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
		RETURN
	ENDIF

	// Reached turning position, so now steer into ship
	aiGuards[nTempPed] = MANSION2_GUARDAI_STEER_BOAT_INTO_SHIP

RETURN


// ****************************************
// GuardAI: Steer Boat Into Ship
Mansion2_AI_Steer_In:

	IF IS_CAR_DEAD carEnemyBoat
		RETURN
	ENDIF

	// Steer into the ship
	TASK_CAR_DRIVE_TO_COORD charGuards[nTempPed] carEnemyBoat -1446.2179 493.9281 0.0000 10.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
	aiGuards[nTempPed] = MANSION2_GUARDAI_STEERING_BOAT_INTO_SHIP

RETURN


// ****************************************
// GuardAI: Steering Boat Into Ship
Mansion2_AI_Steering_In:

	IF IS_CAR_DEAD carEnemyBoat
		RETURN
	ENDIF

	IF NOT LOCATE_CAR_3D carEnemyBoat -1446.2179 493.9281 0.0000 3.0 6.0 5.0 FALSE
		GET_SCRIPT_TASK_STATUS charGuards[nTempPed] TASK_CAR_DRIVE_TO_COORD m_status

		IF m_status = PERFORMING_TASK
		OR m_status = WAITING_TO_START_TASK
			RETURN
		ENDIF

		// Give the task again
		TASK_CAR_DRIVE_TO_COORD charGuards[nTempPed] carEnemyBoat -1446.2179 493.9281 0.0000 10.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
		RETURN
	ENDIF

	// Reached destination, so moor the ship
	SET_CAR_COORDINATES carEnemyBoat -1446.2179 491.9281 0.0000
	SET_CAR_HEADING carEnemyBoat 270.0
	ANCHOR_BOAT carEnemyBoat TRUE

	// Set up a timer for lowering the Back Door
	timerLowerBackDoor = MANSION2_TIME_BEFORE_LOWER_BACK_DOOR_sec * 1000
	timerLowerBackDoor += m_mission_timer

	// Update the Guard's AI
	aiGuards[nTempPed] = MANSION2_GUARDAI_BOAT_MOORED_IN_SHIP

RETURN


// ****************************************
// GuardAI: Boat Moored In Ship
Mansion2_AI_Moored:

	// Activate Guards
	// NOTE: Don't use nTempPed: it's being used
	nTempInt = MANSION2_GUARD_BOAT_DRIVER
	IF NOT aiGuards[nTempInt] = MANSION2_GUARDAI_DEAD
		aiGuards[nTempInt] = MANSION2_GUARDAI_ACTIVATION
	ENDIF

	nTempInt = MANSION2_GUARD_BOAT_BACK
	IF NOT aiGuards[nTempInt] = MANSION2_GUARDAI_DEAD
		aiGuards[nTempInt] = MANSION2_GUARDAI_ACTIVATION
	ENDIF

	nTempInt = MANSION2_GUARD_BOAT_FRONT
	IF NOT aiGuards[nTempInt] = MANSION2_GUARDAI_DEAD
		aiGuards[nTempInt] = MANSION2_GUARDAI_ACTIVATION
	ENDIF

	nTempInt = MANSION2_GUARD_HARRIER_GUIDE
	// Start on top deck facing small lift harrier, but don't give any AI yet
	CREATE_CHAR PEDTYPE_MISSION2 ARMY -1401.7865 504.5374 17.2266 charGuards[nTempInt]
	SET_CHAR_HEADING charGuards[nTempInt] 43.9825
	GIVE_WEAPON_TO_CHAR charGuards[nTempInt] WEAPONTYPE_M4 300
	SET_CURRENT_CHAR_WEAPON charGuards[nTempInt] WEAPONTYPE_M4
	SET_CHAR_ACCURACY charGuards[nTempInt] 70
	SET_CHAR_SHOOT_RATE charGuards[nTempInt] 70
	SET_CHAR_DECISION_MAKER charGuards[nTempInt] dmStealth
	SET_CHAR_IS_TARGET_PRIORITY charGuards[nTempInt] TRUE
//	SET_INFORM_RESPECTED_FRIENDS charGuards[nTempPed] MANSION2_RESPECTED_FRIENDS_DISTANCE_m MANSION2_NUMBER_OF_RESPECTED_FRIENDS

	// Activate Mechanics
	nTempInt = MANSION2_MECHANIC_DOWNSTAIRS
	CREATE_CHAR PEDTYPE_MISSION1 WMYMECH -1421.7574 496.6105 2.0463 charMechanics[nTempInt]
	aiMechanics[nTempInt] = MANSION2_MECHANICAI_ACTIVATION

	nTempInt = MANSION2_MECHANIC_UPSTAIRS
	CREATE_CHAR PEDTYPE_MISSION1 WMYMECH -1346.2185 497.9554 10.1953 charMechanics[nTempInt]
	aiMechanics[nTempInt] = MANSION2_MECHANICAI_ACTIVATION

	// Create Forklifts
	nTempInt = MANSION2_FORKLIFT_DOWNSTAIRS
	CREATE_CAR FORKLIFT -1415.9326 502.7617 2.0391 carForklifts[nTempInt]
	SET_CAR_HEADING carForklifts[nTempInt] 250.5131

	nTempInt = MANSION2_FORKLIFT_UPSTAIRS
	CREATE_CAR FORKLIFT -1312.8903 503.5017 10.1953 carForklifts[nTempInt]
	SET_CAR_HEADING carForklifts[nTempInt] 265.6291

	nTempInt = MANSION2_FORKLIFT_MISSILE
	CREATE_CAR FORKLIFT -1405.7511 495.4972 10.2026 carForklifts[nTempInt]
	SET_CAR_HEADING carForklifts[nTempInt] 81.1713
	// ...attach a missile to this forklift
	CREATE_OBJECT MISSILE_07_SFXR -1312.8903 503.5017 10.1953 objectMissile
	ATTACH_OBJECT_TO_CAR objectMissile carForklifts[nTempInt] 0.0 0.4 1.5 0.0 90.0 90.0

RETURN


// ****************************************
// GuardAI: Activation
Mansion2_AI_Activation:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_GUARD_BOAT_DRIVER
			// Start at door of mooring area talking to a mechanic
			CLEAR_CHAR_TASKS_IMMEDIATELY charGuards[nTempPed]
			SET_CHAR_COORDINATES charGuards[nTempPed] -1423.1376 493.9279 2.0391
			SET_CHAR_HEADING charGuards[nTempPed] 355.6815
			aiGuards[nTempPed] = MANSION2_GUARDAI_TICKING_OVER
			flagGuardsAllowAlert[nTempPed] = TRUE
			BREAK

		CASE MANSION2_GUARD_BOAT_BACK
			// Start in downstairs boxes room
			DETACH_CHAR_FROM_CAR charGuards[nTempPed]
			CLEAR_CHAR_TASKS_IMMEDIATELY charGuards[nTempPed]
			SET_CHAR_COORDINATES charGuards[nTempPed] -1394.4768 500.6530 2.0463
			SET_CHAR_HEADING charGuards[nTempPed] 214.6545
			aiGuards[nTempPed] = MANSION2_GUARDAI_TICKING_OVER
			flagGuardsAllowAlert[nTempPed] = TRUE
			BREAK

		CASE MANSION2_GUARD_BOAT_FRONT
			// Start in downstairs boxes room
			DETACH_CHAR_FROM_CAR charGuards[nTempPed]
			CLEAR_CHAR_TASKS_IMMEDIATELY charGuards[nTempPed]
			SET_CHAR_COORDINATES charGuards[nTempPed] -1396.3969 500.5882 2.0391
			SET_CHAR_HEADING charGuards[nTempPed] 233.6078
			aiGuards[nTempPed] = MANSION2_GUARDAI_TICKING_OVER
			flagGuardsAllowAlert[nTempPed] = TRUE
			BREAK

		CASE MANSION2_GUARD_PILOT_BIG_LIFT
			// Start by exiting Harrier
			aiGuards[nTempPed] = MANSION2_GUARDAI_EXIT_PLANE
			BREAK

		CASE MANSION2_GUARD_HARRIER_GUIDE
			// Go Home
			aiGuards[nTempPed] = MANSION2_GUARDAI_WALK_HOME
			BREAK

		CASE MANSION2_GUARD_PILOT_SMALL_LIFT
			// Exit the Harrier
			aiGuards[nTempPed] = MANSION2_GUARDAI_EXIT_PLANE
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_GUARD_FOR_ACTIVATION
	ENDSWITCH

	// Make all Guards have a Stealth decision maker
	// NOTE: Harier Guide gets this stuff on creation
	IF NOT nTempPed = MANSION2_GUARD_HARRIER_GUIDE
		GIVE_WEAPON_TO_CHAR charGuards[nTempPed] WEAPONTYPE_M4 300
		SET_CURRENT_CHAR_WEAPON charGuards[nTempPed] WEAPONTYPE_M4
		SET_CHAR_ACCURACY charGuards[nTempPed] 70
		SET_CHAR_SHOOT_RATE charGuards[nTempPed] 70
		SET_CHAR_DECISION_MAKER charGuards[nTempPed] dmStealth
//		SET_INFORM_RESPECTED_FRIENDS charGuards[nTempPed] MANSION2_RESPECTED_FRIENDS_DISTANCE_m MANSION2_NUMBER_OF_RESPECTED_FRIENDS
	ENDIF

RETURN


// ****************************************
// GuardAI: Walk Home
Mansion2_AI_Walk_Home:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_GUARD_BOAT_DRIVER
			// Walk to recreation room
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1348.1710 498.9987 10.1953 PEDMOVE_WALK MANSION2_PERSIST
			aiGuards[nTempPed] = MANSION2_GUARDAI_WALKING_HOME
			BREAK

		CASE MANSION2_GUARD_BOAT_BACK
			// Walk to upper deck Harrier
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1340.6578 500.8229 17.2266 PEDMOVE_WALK MANSION2_PERSIST
			aiGuards[nTempPed] = MANSION2_GUARDAI_WALKING_HOME
			BREAK

		CASE MANSION2_GUARD_BOAT_FRONT
			// Walk to control room
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1289.7054 489.1904 10.2026 PEDMOVE_WALK MANSION2_PERSIST
			aiGuards[nTempPed] = MANSION2_GUARDAI_WANDERING
			timerGuardsAI[nTempPed] = m_mission_timer + 5000
			BREAK

		CASE MANSION2_GUARD_HARRIER_GUIDE
			// Walk to Big Lift to watch other plane
			TASK_GO_STRAIGHT_TO_COORD charGuards[nTempPed] -1445.2346 501.5114 17.2266 PEDMOVE_WALK MANSION2_PERSIST
			aiGuards[nTempPed] = MANSION2_GUARDAI_WALKING_HOME
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_GUARD_FOR_WALK_HOME
	ENDSWITCH

RETURN


// ****************************************
// GuardAI: Walking Home
Mansion2_AI_Walking_Home:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_GUARD_BOAT_DRIVER
			// Walking to recreation room
			IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1348.1710 498.9987 10.1953 1.2 1.2 2.0 FALSE
				aiGuards[nTempPed] = MANSION2_GUARDAI_SIT
			ENDIF
			BREAK

		CASE MANSION2_GUARD_BOAT_BACK
			// Walk to upper deck Harrier
			IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1340.6578 500.8229 17.2266 1.2 1.2 2.0 FALSE
				TASK_ACHIEVE_HEADING charGuards[nTempPed] 303.0599
				aiGuards[nTempPed] = MANSION2_GUARDAI_TICKING_OVER
			ENDIF
			BREAK

		CASE MANSION2_GUARD_HARRIER_GUIDE
			// Walk to upper deck Harrier
			IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1435.7202 501.0618 17.2266 1.2 1.2 2.0 FALSE
				aiGuards[nTempPed] = MANSION2_GUARDAI_WATCH_PLANE
			ENDIF
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_GUARD_FOR_WALKING_HOME
	ENDSWITCH

RETURN


// ****************************************
// GuardAI: Chat
Mansion2_AI_Chat:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_GUARD_BOAT_DRIVER
			// Chat to Mechanic
			IF NOT IS_CHAR_DEAD charMechanics[MANSION2_MECHANIC_DOWNSTAIRS]
				TASK_CHAT_WITH_CHAR charGuards[nTempPed] charMechanics[MANSION2_MECHANIC_DOWNSTAIRS] TRUE TRUE
				TASK_CHAT_WITH_CHAR charMechanics[MANSION2_MECHANIC_DOWNSTAIRS] charGuards[nTempPed] FALSE TRUE
			ENDIF
			timerGuardsAI[nTempPed] = m_mission_timer + 7000
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_GUARD_FOR_CHAT
	ENDSWITCH


	aiGuards[nTempPed] = MANSION2_GUARDAI_CHATTING

RETURN


// ****************************************
// GuardAI: Chatting
Mansion2_AI_Chatting:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_GUARD_BOAT_DRIVER
			// Still chatting to Mechanic?
			IF timerGuardsAI[nTempPed] < m_mission_timer
				// ...cancel the chat, and walk home
				aiGuards[nTempPed] = MANSION2_GUARDAI_WALK_HOME
			ENDIF
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_GUARD_FOR_CHAT
	ENDSWITCH

RETURN


// ****************************************
// GuardAI: Sit
Mansion2_AI_Sit:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_GUARD_BOAT_DRIVER
			// Sit on rec-room sofa
			OPEN_SEQUENCE_TASK nSeqTask
				TASK_GO_STRAIGHT_TO_COORD -1 -1348.0746 493.1958 10.1953 PEDMOVE_WALK MANSION2_PERSIST	// Alongside Sofa
				TASK_GO_STRAIGHT_TO_COORD -1 -1345.7485 493.6958 10.2027 PEDMOVE_WALK MANSION2_PERSIST  // Front of Sofa
				TASK_ACHIEVE_HEADING -1 180.0															// Achieve Heading
				TASK_SIT_DOWN -1 99999999																// Sit down (forever)
			CLOSE_SEQUENCE_TASK nSeqTask

			PERFORM_SEQUENCE_TASK charGuards[nTempPed] nSeqTask
			CLEAR_SEQUENCE_TASK nSeqTask

			aiGuards[nTempPed] = MANSION2_GUARDAI_TICKING_OVER
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_GUARD_FOR_SIT
	ENDSWITCH

RETURN


// ****************************************
// GuardAI: Wander
Mansion2_AI_Wander:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_GUARD_BOAT_FRONT
			// Choose a new destination
			GOSUB Mansion2_AI_Wander_GBF
			aiGuards[nTempPed] = MANSION2_GUARDAI_WANDERING
			BREAK

		CASE MANSION2_GUARD_BOAT_BACK
			// Choose a new destination
			GOSUB Mansion2_AI_Wander_GBB
			aiGuards[nTempPed] = MANSION2_GUARDAI_WANDERING
			BREAK

		CASE MANSION2_GUARD_PILOT_BIG_LIFT
			// Choose a new destination
			GOSUB Mansion2_AI_Wander_GPBL
			aiGuards[nTempPed] = MANSION2_GUARDAI_WANDERING
			BREAK

		CASE MANSION2_GUARD_HARRIER_GUIDE
			// Choose a new destination
			GOSUB Mansion2_AI_Wander_GHG
			aiGuards[nTempPed] = MANSION2_GUARDAI_WANDERING
			BREAK

		CASE MANSION2_GUARD_PILOT_SMALL_LIFT
			// Choose a new destination
			GOSUB Mansion2_AI_Wander_GPSL
			aiGuards[nTempPed] = MANSION2_GUARDAI_WANDERING
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_GUARD_FOR_WANDER
	ENDSWITCH

RETURN


// ****************************************
// GuardAI: Wandering
Mansion2_AI_Wandering:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_GUARD_BOAT_FRONT
			// Arrived at destination?
			IF timerGuardsAI[nTempPed] < m_mission_timer
				GOSUB Mansion2_AI_Wandering_GBF

				IF flagTempFlag = TRUE
					GENERATE_RANDOM_INT_IN_RANGE 5000 7000 nTempInt
					timerGuardsAI[nTempPed] = m_mission_timer + nTempInt
					aiGuards[nTempPed] = MANSION2_GUARDAI_WANDER_STOPPED
				ENDIF
			ENDIF
			BREAK

		CASE MANSION2_GUARD_BOAT_BACK
			// Arrived at destination?
			IF timerGuardsAI[nTempPed] < m_mission_timer
				GOSUB Mansion2_AI_Wandering_GBB

				IF flagTempFlag = TRUE
					GENERATE_RANDOM_INT_IN_RANGE 5000 7000 nTempInt
					timerGuardsAI[nTempPed] = m_mission_timer + nTempInt
					aiGuards[nTempPed] = MANSION2_GUARDAI_WANDER_STOPPED
				ENDIF
			ENDIF
			BREAK

		CASE MANSION2_GUARD_PILOT_BIG_LIFT
			// Arrived at destination?
			IF timerGuardsAI[nTempPed] < m_mission_timer
				GOSUB Mansion2_AI_Wandering_GPBL

				IF flagTempFlag = TRUE
					GENERATE_RANDOM_INT_IN_RANGE 5000 7000 nTempInt
					timerGuardsAI[nTempPed] = m_mission_timer + nTempInt
					aiGuards[nTempPed] = MANSION2_GUARDAI_WANDER_STOPPED
				ENDIF
			ENDIF
			BREAK

		CASE MANSION2_GUARD_HARRIER_GUIDE
			// Arrived at destination?
			IF timerGuardsAI[nTempPed] < m_mission_timer
				GOSUB Mansion2_AI_Wandering_GHG

				IF flagTempFlag = TRUE
					GENERATE_RANDOM_INT_IN_RANGE 5000 7000 nTempInt
					timerGuardsAI[nTempPed] = m_mission_timer + nTempInt
					aiGuards[nTempPed] = MANSION2_GUARDAI_WANDER_STOPPED
				ENDIF
			ENDIF
			BREAK

		CASE MANSION2_GUARD_PILOT_SMALL_LIFT
			// Arrived at destination?
			IF timerGuardsAI[nTempPed] < m_mission_timer
				GOSUB Mansion2_AI_Wandering_GPSL

				IF flagTempFlag = TRUE
					GENERATE_RANDOM_INT_IN_RANGE 5000 7000 nTempInt
					timerGuardsAI[nTempPed] = m_mission_timer + nTempInt
					aiGuards[nTempPed] = MANSION2_GUARDAI_WANDER_STOPPED
				ENDIF
			ENDIF
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_GUARD_FOR_WANDERING
	ENDSWITCH

RETURN


// ****************************************
// GuardAI: Wander Stopped
Mansion2_AI_WanderStop:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	// Ready to Wander again?
	IF timerGuardsAI[nTempPed] < m_mission_timer
		aiGuards[nTempPed] = MANSION2_GUARDAI_WANDER
	ENDIF

RETURN


// ****************************************
// GuardAI: Exit Plane
Mansion2_AI_ExitPlane:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	// Exit Plane
	IF IS_CHAR_IN_ANY_PLANE charGuards[nTempPed]
		TASK_LEAVE_ANY_CAR charGuards[nTempPed]
	ENDIF

	aiGuards[nTempPed] = MANSION2_GUARDAI_EXITING_PLANE

RETURN


// ****************************************
// GuardAI: Exiting Plane
Mansion2_AI_ExitingPlane:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	// Has Guard left plane?
	IF IS_CHAR_IN_ANY_PLANE charGuards[nTempPed]
		RETURN
	ENDIF

	// Tell Ped what to do next
	SWITCH nTempPed
		CASE MANSION2_GUARD_PILOT_BIG_LIFT
			aiGuards[nTempPed] = MANSION2_GUARDAI_WANDER
			flagGuardsAllowAlert[nTempPed] = TRUE
			BREAK

		CASE MANSION2_GUARD_PILOT_SMALL_LIFT
			aiGuards[nTempPed] = MANSION2_GUARDAI_TICKING_OVER
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_GUARD_FOR_EXITING_PLANE
	ENDSWITCH

RETURN


// ****************************************
// GuardAI: Watch Plane
Mansion2_AI_WatchPlane:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_GUARD_HARRIER_GUIDE
			// Watch the Big Lift plane coming in to land
			IF IS_CAR_DEAD carPlanes[MANSION2_PLANE_BIG_LIFT]
				// ...plane is dead, so wander
				aiGuards[nTempPed] = MANSION2_GUARDAI_WANDER
				CLEAR_LOOK_AT charGuards[nTempPed]
			ELSE
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR carPlanes[MANSION2_PLANE_BIG_LIFT]
					// ...plane has landed, so wander
					aiGuards[nTempPed] = MANSION2_GUARDAI_WANDER
					CLEAR_LOOK_AT charGuards[nTempPed]
				ELSE
					// Face plane again
					// ...guard coords
					GET_CHAR_COORDINATES charGuards[nTempPed] xposTemp yposTemp zposTemp
					xhiTemp = xposTemp
					yhiTemp = yposTemp

					// ...plane coords
					GET_CAR_COORDINATES carPlanes[MANSION2_PLANE_BIG_LIFT] xposTemp yposTemp zposTemp
					xloTemp = xposTemp
					yloTemp = yposTemp

					// ...calculate vector
					xposTemp = xloTemp - xhiTemp
					yposTemp = yloTemp - yhiTemp
					GET_HEADING_FROM_VECTOR_2D xposTemp yposTemp headTemp

					// ...turn towards vector
					TASK_ACHIEVE_HEADING charGuards[nTempPed] headTemp

					// Look at, if not already a task
					GET_SCRIPT_TASK_STATUS charGuards[nTempPed] TASK_LOOK_AT_CHAR m_status
					IF NOT m_status = PERFORMING_TASK
						IF NOT IS_CHAR_DEAD charGuards[MANSION2_GUARD_PILOT_BIG_LIFT]
							TASK_LOOK_AT_CHAR charGuards[nTempPed] charGuards[MANSION2_GUARD_PILOT_BIG_LIFT] MANSION2_PERSIST
						ENDIF
					ENDIF

					// Set up the timer to allow a re-adjustment of the heading
					timerGuardsAI[nTempPed] = m_mission_timer + 5000
					aiGuards[nTempPed] = MANSION2_GUARDAI_WATCHING_PLANE
				ENDIF
			ENDIF
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_GUARD_FOR_WATCH_PLANES
	ENDSWITCH

RETURN


// ****************************************
// GuardAI: Watching Plane
Mansion2_AI_WatchingPlane:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_GUARD_HARRIER_GUIDE
			IF timerGuardsAI[nTempPed] < m_mission_timer
				// ...timer has elapsed, so re-adjust heading
				aiGuards[nTempPed] = MANSION2_GUARDAI_WATCH_PLANE
			ENDIF
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_GUARD_FOR_WATCHING_PLANE
	ENDSWITCH

RETURN


// ****************************************
// GuardAI: Teleport
Mansion2_AI_Teleport:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	IF IS_CHAR_IN_ANY_CAR charGuards[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_GUARD_PILOT_SMALL_LIFT
			// ...teleport off the lift to safety
			SET_CHAR_COORDINATES charGuards[nTempPed] -1413.0044 508.9740 10.1953
			aiGuards[nTempPed] = MANSION2_GUARDAI_TICKING_OVER
			flagGuardsAllowAlert[nTempPed] = TRUE

			// Show blip after teleport to avoid glitch
			ADD_BLIP_FOR_CHAR charGuards[nTempPed] blipGuards[nTempPed]
			CHANGE_BLIP_DISPLAY blipGuards[nTempPed] BLIP_ONLY
			SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR blipGuards[nTempPed] TRUE
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_GUARD_FOR_TELEPORT
	ENDSWITCH

RETURN


// ****************************************
// GuardAI: Goto Alert Position
Mansion2_AI_GotoAlert:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_GUARD_BOAT_DRIVER
			// Run to Middle Deck area
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1324.1584 508.4572 10.1953 PEDMOVE_RUN MANSION2_PERSIST
			aiGuards[nTempPed] = MANSION2_GUARDAI_GOING_TO_ALERT_POSITION
			BREAK

		CASE MANSION2_GUARD_BOAT_BACK
			// Run to Rec Room area
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1348.0607 492.4249 10.1953 PEDMOVE_RUN MANSION2_PERSIST
			aiGuards[nTempPed] = MANSION2_GUARDAI_GOING_TO_ALERT_POSITION
			BREAK

		CASE MANSION2_GUARD_BOAT_FRONT
			// Run to Control Room area
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1299.6533 509.2915 10.1953 PEDMOVE_RUN MANSION2_PERSIST
			aiGuards[nTempPed] = MANSION2_GUARDAI_GOING_TO_ALERT_POSITION
			BREAK

		CASE MANSION2_GUARD_PILOT_BIG_LIFT
			// Run to Harriers area
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1434.2120 508.0886 10.1953 PEDMOVE_RUN MANSION2_PERSIST
			aiGuards[nTempPed] = MANSION2_GUARDAI_GOING_TO_ALERT_POSITION
			BREAK

		CASE MANSION2_GUARD_HARRIER_GUIDE
			// Run to Top Deck area
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1418.2070 492.2773 17.2266 PEDMOVE_RUN MANSION2_PERSIST
			aiGuards[nTempPed] = MANSION2_GUARDAI_GOING_TO_ALERT_POSITION
			BREAK

		CASE MANSION2_GUARD_PILOT_SMALL_LIFT
			// Run to Boxes and missiles area
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1409.1537 506.4637 10.1953 PEDMOVE_RUN MANSION2_PERSIST
			aiGuards[nTempPed] = MANSION2_GUARDAI_GOING_TO_ALERT_POSITION
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_GUARD_FOR_GOTO_ALERT
	ENDSWITCH

RETURN


// ****************************************
// GuardAI: Going To Alert Position
Mansion2_AI_GoingToAlert:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_GUARD_BOAT_DRIVER
			// Middle Deck area
			IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1324.1584 508.4572 10.1953 2.0 2.0 2.0 FALSE
				TASK_ACHIEVE_HEADING charGuards[nTempPed] 98.0823
				aiGuards[nTempPed] = MANSION2_GUARDAI_ORIENTATE_AT_ALERT_POSITION
				timerGuardsAI[nTempPed] = 0
			ENDIF
			BREAK

		CASE MANSION2_GUARD_BOAT_BACK
			// Rec Room area
			IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1348.0607 492.4249 10.1953 2.0 2.0 2.0 FALSE
				TASK_ACHIEVE_HEADING charGuards[nTempPed] 0.0
				aiGuards[nTempPed] = MANSION2_GUARDAI_ORIENTATE_AT_ALERT_POSITION
				timerGuardsAI[nTempPed] = 0
			ENDIF
			BREAK

		CASE MANSION2_GUARD_BOAT_FRONT
			// Control Room area
			IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1299.6533 509.2915 10.1953 2.0 2.0 2.0 FALSE
				TASK_ACHIEVE_HEADING charGuards[nTempPed] 122.1536
				aiGuards[nTempPed] = MANSION2_GUARDAI_ORIENTATE_AT_ALERT_POSITION
				timerGuardsAI[nTempPed] = 0
			ENDIF
			BREAK

		CASE MANSION2_GUARD_PILOT_BIG_LIFT
			// Harriers area
			IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1434.2120 508.0886 10.1953 2.0 2.0 2.0 FALSE
				TASK_ACHIEVE_HEADING charGuards[nTempPed] 261.6837
				aiGuards[nTempPed] = MANSION2_GUARDAI_ORIENTATE_AT_ALERT_POSITION
				timerGuardsAI[nTempPed] = 0
			ENDIF
			BREAK

		CASE MANSION2_GUARD_HARRIER_GUIDE
			// Top Deck area
			IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1418.2070 492.2773 17.2266 2.0 2.0 2.0 FALSE
				TASK_ACHIEVE_HEADING charGuards[nTempPed] 304.1052
				aiGuards[nTempPed] = MANSION2_GUARDAI_ORIENTATE_AT_ALERT_POSITION
				timerGuardsAI[nTempPed] = 0
			ENDIF
			BREAK

		CASE MANSION2_GUARD_PILOT_SMALL_LIFT
			// Boxes and Missiles area
			IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1409.1537 506.4637 10.1953 2.0 2.0 2.0 FALSE
				TASK_ACHIEVE_HEADING charGuards[nTempPed] 270.2725
				aiGuards[nTempPed] = MANSION2_GUARDAI_ORIENTATE_AT_ALERT_POSITION
				timerGuardsAI[nTempPed] = 0
			ENDIF
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_GUARD_FOR_GOING_TO_ALERT
	ENDSWITCH

	GOSUB Mansion2_Update_Active_Alert_State

RETURN


// ****************************************
// GuardAI: Alert Orientate
Mansion2_AI_AlertOrientate:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	GET_SCRIPT_TASK_STATUS charGuards[nTempPed] TASK_ACHIEVE_HEADING m_status
	IF m_status = FINISHED_TASK
		SET_CHAR_KINDA_STAY_IN_SAME_PLACE charGuards[nTempPed] TRUE
		aiGuards[nTempPed] = MANSION2_GUARDAI_AT_ALERT_POSITION
		timerGuardsAI[nTempPed] = 0
	ENDIF

	GOSUB Mansion2_Update_Active_Alert_State

RETURN


// ****************************************
// GuardAI: At Alert Position
Mansion2_AI_Alert:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	IF timerGuardsAI[nTempPed] > m_mission_timer
		RETURN
	ENDIF

	GET_SCRIPT_TASK_STATUS charGuards[nTempPed] TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING m_status
	IF m_status = FINISHED_TASK
		TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING charGuards[nTempPed] scplayer DUCK_RANDOMLY 3000 60
		timerGuardsAI[nTempPed] = m_mission_timer + 1000
	ENDIF

	GOSUB Mansion2_Update_Active_Alert_State

RETURN


// ****************************************
// GuardAI: Attack
Mansion2_AI_Attack:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	// Make the guy tough and allow him to move
	SET_CHAR_DECISION_MAKER charGuards[nTempPed] dmTough
	SET_CHAR_KINDA_STAY_IN_SAME_PLACE charGuards[nTempPed] FALSE
	aiGuards[nTempPed] = MANSION2_GUARDAI_ATTACKING
	flagGuardsOnAlert[nTempPed] = TRUE
	timerGuardsAI[nTempPed] = 0

RETURN


// ****************************************
// GuardAI: Run Attack
Mansion2_AI_RunAttack:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_GUARD_BOAT_DRIVER
			// ...the run bit
			TASK_GO_STRAIGHT_TO_COORD charGuards[nTempPed] -1431.9436 500.2454 2.0391 PEDMOVE_RUN MANSION2_PERSIST  // Corner of warehouse
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_GUARD_FOR_RUN_ATTACK
	ENDSWITCH

	// Make the guy tough and allow him to move
	SET_CHAR_DECISION_MAKER charGuards[nTempPed] dmTough
	aiGuards[nTempPed] = MANSION2_GUARDAI_RUNNING_ATTACK
	flagGuardsOnAlert[nTempPed] = TRUE
	timerGuardsAI[nTempPed] = 0

RETURN


// ****************************************
// GuardAI: Running Attack
Mansion2_AI_RunningAttack:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	// If this char is shooting then put the boat on alert
	IF flagCarrierOnAlert = FALSE
		IF IS_CHAR_SHOOTING charGuards[nTempPed]
			flagCarrierOnAlert = TRUE

			IF nReasonForDetection = MANSION2_DETECTED_UNKNOWN
				nReasonForDetection = MANSION2_DETECTED_BY_GUARD
			ENDIF
		ENDIF
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_GUARD_BOAT_DRIVER
			IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1431.9436 500.2454 2.0391 1.2 1.2 2.0 FALSE
				timerGuardsAI[nTempPed] = 0
				// ...the attack bit
				aiGuards[nTempPed] = MANSION2_GUARDAI_ATTACKING
			ENDIF
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_GUARD_FOR_RUNNING_ATTACK
	ENDSWITCH

	GOSUB Mansion2_Update_Active_Alert_State

RETURN


// ****************************************
// GuardAI: Attacking
Mansion2_AI_Attacking:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	// If this char is shooting then put the boat on alert
	IF flagCarrierOnAlert = FALSE
		IF IS_CHAR_SHOOTING charGuards[nTempPed]
			flagCarrierOnAlert = TRUE

			IF nReasonForDetection = MANSION2_DETECTED_UNKNOWN
				nReasonForDetection = MANSION2_DETECTED_BY_GUARD
			ENDIF
		ENDIF
	ENDIF

	IF timerGuardsAI[nTempPed] > m_mission_timer
		RETURN
	ENDIF

	GET_SCRIPT_TASK_STATUS charGuards[nTempPed] TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING m_status
	IF m_status = FINISHED_TASK
		TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING charGuards[nTempPed] scplayer DUCK_RANDOMLY 3000 60
		timerGuardsAI[nTempPed] = m_mission_timer + 1000
	ENDIF

	GOSUB Mansion2_Update_Active_Alert_State

RETURN


// ****************************************
// GuardAI: Wander (Guard Boat Front)
Mansion2_AI_Wander_GBF:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	GENERATE_RANDOM_INT_IN_RANGE 0 6 nTempInt
	SWITCH nTempInt
		CASE 0
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1289.7054 489.1904 10.2026 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 1
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1325.6605 498.4251 10.2026 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 2
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1302.2484 504.2907 10.1953 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 3
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1292.3530 494.8264 10.1953 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 4
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1301.5991 509.8161 10.1953 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 5
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1294.2568 504.8536 10.1953 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
	ENDSWITCH

	// Make sure the Guard gets a bit away from the current location before checking for a new location
	timerGuardsAI[nTempPed] = m_mission_timer + 5000

RETURN


// ****************************************
// GuardAI: Wandering (Guard Boat Front)
Mansion2_AI_Wandering_GBF:

	flagTempFlag = FALSE

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1289.7054 489.1904 10.2026 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 155.3066
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1325.6605 498.4251 10.2026 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 77.7535
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1302.2484 504.2907 10.1953 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 275.9547
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1292.3530 494.8264 10.1953 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 248.8300
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1301.5991 509.8161 10.1953 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 14.8410
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1294.2568 504.8536 10.1953 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 157.6618
		flagTempFlag = TRUE
	ENDIF

RETURN


// ****************************************
// GuardAI: Wander (Guard Boat Back)
Mansion2_AI_Wander_GBB:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	GENERATE_RANDOM_INT_IN_RANGE 0 5 nTempInt
	SWITCH nTempInt
		CASE 0
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1311.8341 489.4401 17.2266 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 1
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1298.3593 512.5721 17.2266 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 2
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1247.3519 502.8824 17.2266 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 3
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1359.6558 512.5123 17.2266 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 4
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1349.6064 493.5821 17.2279 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
	ENDSWITCH

	// Make sure the Guard gets a bit away from the current location before checking for a new location
	timerGuardsAI[nTempPed] = m_mission_timer + 5000

RETURN


// ****************************************
// GuardAI: Wandering (Guard Boat Back)
Mansion2_AI_Wandering_GBB:

	flagTempFlag = FALSE

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1311.8341 489.4401 17.2266 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 180.0
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1298.3593 512.5721 17.2266 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 0.0
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1247.3519 502.8824 17.2266 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 270.0
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1359.6558 512.5123 17.2266 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 0.0
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1349.6064 493.5821 17.2279 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 80.0
		flagTempFlag = TRUE
	ENDIF

RETURN


// ****************************************
// GuardAI: Wander (Guard Pilot Big Lift)
Mansion2_AI_Wander_GPBL:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	GENERATE_RANDOM_INT_IN_RANGE 0 6 nTempInt
	SWITCH nTempInt
		CASE 0
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1444.2896 496.4273 10.1953 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 1
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1449.6210 506.0983 10.1953 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 2
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1424.2526 511.1483 10.1953 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 3
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1411.0833 511.8316 10.1953 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 4
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1417.3419 505.5704 10.1953 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 5
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1431.4982 501.8023 10.1953 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
	ENDSWITCH

	// Make sure the Guard gets a bit away from the current location before checking for a new location
	timerGuardsAI[nTempPed] = m_mission_timer + 5000

RETURN


// ****************************************
// GuardAI: Wandering (Guard Pilot Big Lift)
Mansion2_AI_Wandering_GPBL:

	flagTempFlag = FALSE

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1444.2896 496.4273 10.1953 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 56.0502
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1449.6210 506.0983 10.1953 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 123.3438
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1424.2526 511.1483 10.1953 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 318.9861
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1411.0833 511.8316 10.1953 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 28.0647
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1417.3419 505.5704 10.1953 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 139.4299
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1431.4982 501.8023 10.1953 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 242.7025
		flagTempFlag = TRUE
	ENDIF

RETURN


// ****************************************
// GuardAI: Wander (Guard Harrier Guide)
Mansion2_AI_Wander_GHG:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	GENERATE_RANDOM_INT_IN_RANGE 0 5 nTempInt
	SWITCH nTempInt
		CASE 0
			TASK_GO_STRAIGHT_TO_COORD charGuards[nTempPed] -1445.2346 501.5114 17.2266 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 1
			TASK_GO_STRAIGHT_TO_COORD charGuards[nTempPed] -1419.4803 510.3460 17.2295 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 2
			TASK_GO_STRAIGHT_TO_COORD charGuards[nTempPed] -1412.2179 489.4574 17.2266 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 3
			TASK_GO_STRAIGHT_TO_COORD charGuards[nTempPed] -1395.9346 498.2455 17.2266 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 4
			TASK_GO_STRAIGHT_TO_COORD charGuards[nTempPed] -1373.5787 512.9980 17.2295 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
	ENDSWITCH

	// Make sure the Guard gets a bit away from the current location before checking for a new location
	timerGuardsAI[nTempPed] = m_mission_timer + 5000

RETURN


// ****************************************
// GuardAI: Wandering (Guard Harrier Guide)
Mansion2_AI_Wandering_GHG:

	flagTempFlag = FALSE

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1445.2346 501.5114 17.2266 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 90.0
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1419.4803 510.3460 17.2295 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 0.0
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1412.2179 489.4574 17.2266 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 180.0
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1395.9346 498.2455 17.2266 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 194.6539
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1373.5787 512.9980 17.2295 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 326.6510
		flagTempFlag = TRUE
	ENDIF

RETURN


// ****************************************
// GuardAI: Wander (Guard Pilot Small Lift)
Mansion2_AI_Wander_GPSL:

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	GENERATE_RANDOM_INT_IN_RANGE 0 5 nTempInt
	SWITCH nTempInt
		CASE 0
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1411.7139 512.2815 10.1953 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 1
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1406.4513 502.4760 10.1953 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 2
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1366.0299 505.7779 10.2026 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 3
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1366.4841 516.8234 10.1797 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
		CASE 4
			TASK_FOLLOW_PATH_NODES_TO_COORD charGuards[nTempPed] -1344.8208 499.0515 10.2027 PEDMOVE_WALK MANSION2_PERSIST
			BREAK
	ENDSWITCH

	// Make sure the Guard gets a bit away from the current location before checking for a new location
	timerGuardsAI[nTempPed] = m_mission_timer + 5000

RETURN


// ****************************************
// GuardAI: Wandering (Guard Pilot Small Lift)
Mansion2_AI_Wandering_GPSL:

	flagTempFlag = FALSE

	IF IS_CHAR_DEAD charGuards[nTempPed]
		RETURN
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1411.7139 512.2815 10.1953 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 24.5074
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1406.4513 502.4760 10.1953 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 236.3499
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1366.0299 505.7779 10.2026 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 180.0
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1366.4841 516.8234 10.1797 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 341.4966
		flagTempFlag = TRUE
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D charGuards[nTempPed] -1344.8208 499.0515 10.2027 1.2 1.2 1.2 FALSE
		TASK_ACHIEVE_HEADING charGuards[nTempPed] 192.1196
		flagTempFlag = TRUE
	ENDIF

RETURN



// *************************************************************************************************************
//								               Mechanic AI
// *************************************************************************************************************

// ****************************************
// Maintain Mechanic AI
Mansion2_Maintain_MechanicAI:

	// Check for any newly dead mechanics
	REPEAT MANSION2_MAX_MECHANICS nTempPed
		IF NOT aiMechanics[nTempPed] = MANSION2_MECHANICAI_NOT_INITIALISED
		AND NOT aiMechanics[nTempPed] = MANSION2_MECHANICAI_DEAD
			IF IS_CHAR_DEAD charMechanics[nTempPed]
				aiMechanics[nTempPed] = MANSION2_MECHANICAI_DEAD
				GOSUB Mansion2_Dead_Mechanic_Informs_Nearby_Peds
			ENDIF
		ENDIF
	ENDREPEAT


	REPEAT MANSION2_MAX_MECHANICS nTempPed
		// Check for a change in the Mechanic's alert state
		GOSUB Mansion2_Update_Mech_Alert_State

		SWITCH aiMechanics[nTempPed]
			CASE MANSION2_MECHANICAI_NOT_INITIALISED
			CASE MANSION2_MECHANICAI_DEAD
				// ...nothing to do
				BREAK

			CASE MANSION2_MECHANICAI_ACTIVATION
				GOSUB Mansion2_MechAI_Activation
				BREAK

			CASE MANSION2_MECHANICAI_CHAT
				GOSUB Mansion2_MechAI_Chat
				BREAK

			CASE MANSION2_MECHANICAI_CHATTING
				GOSUB Mansion2_MechAI_Chatting
				BREAK

			CASE MANSION2_MECHANICAI_ENTER_FORKLIFT
				GOSUB Mansion2_MechAI_Enter
				BREAK

			CASE MANSION2_MECHANICAI_ENTERING_FORKLIFT
				GOSUB Mansion2_MechAI_Entering
				BREAK

			CASE MANSION2_MECHANICAI_FORKLIFT_WANDER
				GOSUB Mansion2_MechAI_Wander
				BREAK

			CASE MANSION2_MECHANICAI_FORKLIFT_WANDERING
				GOSUB Mansion2_MechAI_Wandering
				BREAK

			CASE MANSION2_MECHANICAI_FORKLIFT_WANDER_STOPPED
				GOSUB Mansion2_MechAI_WanderStop
				BREAK

			CASE MANSION2_MECHANICAI_GOTO_COWER_POSITION
				GOSUB Mansion2_MechAI_GotoCower
				BREAK

			CASE MANSION2_MECHANICAI_GOING_TO_COWER_POSITION
				GOSUB Mansion2_MechAI_GoingToCower
				BREAK

			CASE MANSION2_MECHANICAI_AT_COWER_POSITION
				GOSUB Mansion2_MechAI_Cower
				BREAK

			CASE MANSION2_MECHANICAI_CHANGE_COWER_POSITION
				GOSUB Mansion2_MechAI_ChangeCower
				BREAK

			CASE MANSION2_MECHANICAI_GOTO_ALARM
				GOSUB Mansion2_MechAI_GotoAlarm
				BREAK

			CASE MANSION2_MECHANICAI_GOING_TO_ALARM
				GOSUB Mansion2_MechAI_GoingToAlarm
				BREAK

			CASE MANSION2_MECHANICAI_TRIGGER_ALARM
				GOSUB Mansion2_MechAI_TriggerAlarm
				BREAK

			CASE MANSION2_MECHANICAI_TRIGGERING_ALARM
				GOSUB Mansion2_MechAI_TriggeringAlarm
				BREAK

			CASE MANSION2_MECHANICAI_TICKING_OVER
				// ...nothing to do for now
				BREAK


			DEFAULT
				WRITE_DEBUG Unknown_MechanicAI
		ENDSWITCH
	ENDREPEAT

RETURN


// ****************************************
// MechanicAI: Update Mechanic Alert State
Mansion2_Update_Mech_Alert_State:

	// This routine is called when the ped is not on alert

	IF flagAllowMechanicSensesUpdates = FALSE
		RETURN
	ENDIF

	// Is the ship on alert?
	IF flagCarrierOnAlert = TRUE
		// ...yes, so check if this Mechanic is already on alert
		IF flagMechanicsOnAlert[nTempPed] = FALSE
			// ...no, so make this mechanic go on alert
			aiMechanics[nTempPed] = MANSION2_MECHANICAI_GOTO_COWER_POSITION
			flagMechanicsOnAlert[nTempPed] = TRUE
		ENDIF
		RETURN
	ENDIF

	// Can the Mechanic see the player?
	IF flagMechanicsOnAlert[nTempPed] = FALSE
		IF NOT IS_CHAR_DEAD charMechanics[nTempPed]
			IF HAS_CHAR_SPOTTED_CHAR_IN_FRONT charMechanics[nTempPed] scplayer
				// Restrict the distance
				GET_CHAR_COORDINATES charMechanics[nTempPed] xposTemp yposTemp zposTemp
				GET_CHAR_COORDINATES scplayer xposTemp2 yposTemp2 zposTemp2
				GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
				IF fTempDistance < 15.0
					aiMechanics[nTempPed] = MANSION2_MECHANICAI_GOTO_ALARM

					// Mechanic is running for alarm
					IF NOT nReasonForDetection = MANSION2_DETECTED_TOO_MUCH_NOISE
						PRINT_NOW MAN2_45 7000 1
					ENDIF

					flagMechanicsOnAlert[nTempPed] = TRUE
					GOSUB Mansion2_Mechanic_Informs_Nearby_Peds
					RETURN
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// Can the Mechanic see a dead army guy?
	IF flagMechanicsOnAlert[nTempPed] = FALSE
		IF NOT IS_CHAR_DEAD charMechanics[nTempPed]
			IF CAN_CHAR_SEE_DEAD_CHAR charMechanics[nTempPed] PEDTYPE_MISSION2
				aiMechanics[nTempPed] = MANSION2_MECHANICAI_GOTO_ALARM
				flagMechanicsOnAlert[nTempPed] = TRUE

				// Mechanic has seen dead body and is running for alarm
				PRINT_NOW MAN2_48 7000 1

				GOSUB Mansion2_Mechanic_Informs_Nearby_Peds

				IF nReasonForDetection = MANSION2_DETECTED_UNKNOWN
					nReasonForDetection = MANSION2_DETECTED_DEAD_BODY_BY_MECHANIC
				ENDIF
				RETURN
			ENDIF
		ENDIF
	ENDIF

	// Has player fired a gun?
	IF flagMechanicsOnAlert[nTempPed] = FALSE
		IF NOT IS_CHAR_DEAD charMechanics[nTempPed]
			IF flagPlayerFiringGun = TRUE
				// ...Player fired a gun, so goto alarm
				aiMechanics[nTempPed] = MANSION2_MECHANICAI_GOTO_ALARM

				// Mechanic is running for alarm
				IF NOT nReasonForDetection = MANSION2_DETECTED_TOO_MUCH_NOISE
					PRINT_NOW MAN2_45 7000 1
				ENDIF

				flagMechanicsOnAlert[nTempPed] = TRUE
				GOSUB Mansion2_Mechanic_Informs_Nearby_Peds

				IF nReasonForDetection = MANSION2_DETECTED_UNKNOWN
					nReasonForDetection = MANSION2_DETECTED_BY_MECHANIC
				ENDIF
				RETURN
			ENDIF

			IF flagPlayerFiringSilencedGun = TRUE
				// ...player fired a silenced gun, so check if the mechanic is close to the player
				GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
				GET_CHAR_COORDINATES charMechanics[nTempPed] xposTemp2 yposTemp2 zposTemp2
				GET_DISTANCE_BETWEEN_COORDS_2D xposTemp yposTemp xposTemp2 yposTemp2 fTempFloat
				IF fTempFloat < 12.0
					fTempFloat2 = zposTemp - zposTemp2
					IF fTempFloat2 < 0.0
						fTempFloat2 *= -1.0
					ENDIF

					IF fTempFloat2 < 5.0
						// ...player was heard firing a silenced pistol on the same floor as this ped
						aiMechanics[nTempPed] = MANSION2_MECHANICAI_GOTO_ALARM

						// Mechanic is running for alarm
						IF NOT nReasonForDetection = MANSION2_DETECTED_TOO_MUCH_NOISE
							PRINT_NOW MAN2_45 7000 1
						ENDIF

						flagMechanicsOnAlert[nTempPed] = TRUE
						GOSUB Mansion2_Mechanic_Informs_Nearby_Peds
						RETURN
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// Has this ped been damaged by player?
	IF flagMechanicsOnAlert[nTempPed] = FALSE
		IF NOT IS_CHAR_DEAD charMechanics[nTempPed]
			IF IS_CHAR_IN_ANY_CAR charMechanics[nTempPed]
				STORE_CAR_CHAR_IS_IN_NO_SAVE charMechanics[nTempPed] car

				IF HAS_CAR_BEEN_DAMAGED_BY_CHAR car scplayer
				OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR charMechanics[nTempPed] scplayer
					// ...mechanic or vehicle has been damaged by player
					aiMechanics[nTempPed] = MANSION2_MECHANICAI_GOTO_ALARM

					// Mechanic is running for alarm
					IF NOT nReasonForDetection = MANSION2_DETECTED_TOO_MUCH_NOISE
						PRINT_NOW MAN2_45 7000 1
					ENDIF

					flagMechanicsOnAlert[nTempPed] = TRUE
					GOSUB Mansion2_Mechanic_Informs_Nearby_Peds
					RETURN
				ENDIF
			ELSE
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR charMechanics[nTempPed] scplayer
					// ...mechanic has been damaged by player
					aiMechanics[nTempPed] = MANSION2_MECHANICAI_GOTO_ALARM

					// Mechanic is running for alarm
					IF NOT nReasonForDetection = MANSION2_DETECTED_TOO_MUCH_NOISE
						PRINT_NOW MAN2_45 7000 1
					ENDIF

					flagMechanicsOnAlert[nTempPed] = TRUE
					GOSUB Mansion2_Mechanic_Informs_Nearby_Peds
					RETURN
				ENDIF
			ENDIF
		ENDIF
	ENDIF

RETURN


// ****************************************
// MechanicAI: Update Mechanic Active Alert State
Mansion2_Update_Mech_Active_Alert_State:

	// This updates the alert state for Mechanics when the mechanic is already on alert

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	IF flagAllowMechanicSensesUpdates = FALSE
		RETURN
	ENDIF

	// flagTempFlag, if TRUE means make the Mechanic active and inform nearby peds
	flagTempFlag = FALSE

	// Can the Mechanic see the player?
	IF HAS_CHAR_SPOTTED_CHAR_IN_FRONT charMechanics[nTempPed] scplayer
		flagTempFlag = TRUE
	ENDIF

	// Has player fired a gun close by?
	IF flagTempFlag = FALSE
		IF flagPlayerFiringGun = TRUE
			GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
			GET_CHAR_COORDINATES charMechanics[nTempPed] xposTemp2 yposTemp2 zposTemp2
			GET_DISTANCE_BETWEEN_COORDS_2D xposTemp yposTemp xposTemp2 yposTemp2 fTempFloat
			IF fTempFloat < 75.0
				fTempFloat2 = zposTemp - zposTemp2
				IF fTempFloat2 < 0.0
					fTempFloat2 *= -1.0
				ENDIF

				IF fTempFloat2 < 5.0
					flagTempFlag = TRUE
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// Has player fired a silence gun close by?
	IF flagTempFlag = FALSE
		IF flagPlayerFiringSilencedGun = TRUE
			// ...player fired a silenced gun, so check if the mechanic is close to the player
			GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
			GET_CHAR_COORDINATES charMechanics[nTempPed] xposTemp2 yposTemp2 zposTemp2
			GET_DISTANCE_BETWEEN_COORDS_2D xposTemp yposTemp xposTemp2 yposTemp2 fTempFloat
			IF fTempFloat < 12.0
				fTempFloat2 = zposTemp - zposTemp2
				IF fTempFloat2 < 0.0
					fTempFloat2 *= -1.0
				ENDIF

				IF fTempFloat2 < 5.0
					flagTempFlag = TRUE
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// Has this ped been damaged by player?
	IF flagTempFlag = FALSE
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR charMechanics[nTempPed] scplayer
		OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON charMechanics[nTempPed] WEAPONTYPE_PISTOL_SILENCED
			flagTempFlag = TRUE
		ENDIF
	ENDIF

	// Should this Ped inform nearby peds?
	IF flagTempFlag = TRUE
		// Upgrade nearby ped's reactions
		GOSUB Mansion2_Mechanic_Informs_Nearby_Peds
	ENDIF

RETURN


// ****************************************
// MechanicAI: Mechanic Informs Nearby Peds
Mansion2_Mechanic_Informs_Nearby_Peds:

	// Inform nearby Guards and Mechanics that they also should react
	IF NOT IS_CHAR_DEAD charMechanics[nTempPed]
		GET_CHAR_COORDINATES charMechanics[nTempPed] xposTemp yposTemp zposTemp
	ELSE
		GET_DEAD_CHAR_COORDINATES charMechanics[nTempPed] xposTemp yposTemp zposTemp
	ENDIF

	// ...check if any Guards should be informed
	REPEAT MANSION2_MAX_GUARDS nLoop
		IF NOT IS_CHAR_DEAD charGuards[nLoop]
			GET_CHAR_COORDINATES charGuards[nLoop] xposTemp2 yposTemp2 zposTemp2
			GET_DISTANCE_BETWEEN_COORDS_2D xposTemp yposTemp xposTemp2 yposTemp2 fTempDistance
			IF fTempDistance < MANSION2_INFORM_PEDS_RANGE_m
				fTempFloat = zposTemp - zposTemp2
				IF fTempFloat < 0.0
					fTempFloat *= -1.0
				ENDIF

				IF fTempFloat < 6.0
					IF flagGuardsAllowAlert[nLoop] = TRUE
						// ...the Guard is allowed to go on alert
						// Make the Guard attack if not already doing so
						IF NOT aiGuards[nLoop] = MANSION2_GUARDAI_ATTACK
						AND NOT aiGuards[nLoop] = MANSION2_GUARDAI_ATTACKING
						AND NOT aiGuards[nLoop] = MANSION2_GUARDAI_RUN_ATTACK
						AND NOT aiGuards[nLoop] = MANSION2_GUARDAI_RUNNING_ATTACK
							aiGuards[nLoop] = MANSION2_GUARDAI_ATTACK
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDREPEAT

	// ...check if any Mechanics should be informed
	REPEAT MANSION2_MAX_MECHANICS nLoop
		IF NOT IS_CHAR_DEAD charMechanics[nLoop]
		AND NOT nTempPed = nLoop
			GET_CHAR_COORDINATES charMechanics[nLoop] xposTemp2 yposTemp2 zposTemp2
			GET_DISTANCE_BETWEEN_COORDS_2D xposTemp yposTemp xposTemp2 yposTemp2 fTempDistance
			IF fTempDistance < MANSION2_INFORM_PEDS_RANGE_m
				fTempFloat = zposTemp - zposTemp2
				IF fTempFloat < 0.0
					fTempFloat *= -1.0
				ENDIF

				IF fTempFloat < 6.0
					// Make the Mechanic react if not already doing so
					IF flagCarrierOnAlert = FALSE
						// ...if the Carrier is not on alert, make the Mechanic head for the alarm if not already doing so
						IF flagMechanicsOnAlert[nLoop] = FALSE
							// ...mechanic is not already on alert, so put him on alert
							aiMechanics[nLoop] = MANSION2_MECHANICAI_GOTO_ALARM
							flagMechanicsOnAlert[nLoop] = TRUE
						ENDIF
					ELSE
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDREPEAT

RETURN


// ****************************************
// MechanicAI: Dead Mechanic Informs Nearby Peds
Mansion2_Dead_Mechanic_Informs_Nearby_Peds:

	// Nearby Guards and Mechanics should react to this seeing this Mechanic die
	GET_DEAD_CHAR_COORDINATES charMechanics[nTempPed] xposTemp yposTemp zposTemp

	// ...check if any Guards should be informed
	REPEAT MANSION2_MAX_GUARDS nLoop
		IF NOT IS_CHAR_DEAD charGuards[nLoop]
		AND NOT nTempPed = nLoop
			GET_CHAR_COORDINATES charGuards[nLoop] xposTemp2 yposTemp2 zposTemp2
			GET_DISTANCE_BETWEEN_COORDS_2D xposTemp yposTemp xposTemp2 yposTemp2 fTempDistance
			IF fTempDistance < MANSION2_REACT_TO_DEATH_RANGE_m
				fTempFloat = zposTemp - zposTemp2
				IF fTempFloat < 0.0
					fTempFloat *= -1.0
				ENDIF

				IF fTempFloat < 6.0
					IF flagGuardsAllowAlert[nLoop] = TRUE
						// ...the Guard is allowed to go on alert
						// Make the Guard attack if not already doing so
						IF NOT aiGuards[nLoop] = MANSION2_GUARDAI_ATTACK
						AND NOT aiGuards[nLoop] = MANSION2_GUARDAI_ATTACKING
						AND NOT aiGuards[nLoop] = MANSION2_GUARDAI_RUN_ATTACK
						AND NOT aiGuards[nLoop] = MANSION2_GUARDAI_RUNNING_ATTACK
							aiGuards[nLoop] = MANSION2_GUARDAI_ATTACK
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDREPEAT

	// ...check if any Mechanics should be informed
	REPEAT MANSION2_MAX_MECHANICS nLoop
		IF NOT IS_CHAR_DEAD charMechanics[nLoop]
			GET_CHAR_COORDINATES charMechanics[nLoop] xposTemp2 yposTemp2 zposTemp2
			GET_DISTANCE_BETWEEN_COORDS_2D xposTemp yposTemp xposTemp2 yposTemp2 fTempDistance
			IF fTempDistance < MANSION2_REACT_TO_DEATH_RANGE_m
				fTempFloat = zposTemp - zposTemp2
				IF fTempFloat < 0.0
					fTempFloat *= -1.0
				ENDIF

				IF fTempFloat < 6.0
					// Make the Mechanic react if not already doing so
					IF flagCarrierOnAlert = FALSE
						// ...if the Carrier is not on alert, make the Mechanic head for the alarm if not already doing so
						IF flagMechanicsOnAlert[nLoop] = FALSE
							// ...mechanic is not already on alert, so put him on alert
							aiMechanics[nLoop] = MANSION2_MECHANICAI_GOTO_ALARM

							// Mechanic is running for alarm
							IF NOT nReasonForDetection = MANSION2_DETECTED_TOO_MUCH_NOISE
								PRINT_NOW MAN2_45 7000 1
							ENDIF

							flagMechanicsOnAlert[nLoop] = TRUE
						ENDIF
					ELSE
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDREPEAT

RETURN


// ****************************************
// MechanicAI: Activation
Mansion2_MechAI_Activation:

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_MECHANIC_DOWNSTAIRS
			// Start at door of mooring area talking to a Guard
			SET_CHAR_COORDINATES charMechanics[nTempPed] -1421.7574 496.6105 2.0463
			SET_CHAR_HEADING charMechanics[nTempPed] 175.6815
			aiMechanics[nTempPed] = MANSION2_MECHANICAI_TICKING_OVER
			BREAK

		CASE MANSION2_MECHANIC_UPSTAIRS
			// Start in rec room
			SET_CHAR_COORDINATES charMechanics[nTempPed] -1346.2185 497.9554 10.1953
			SET_CHAR_HEADING charMechanics[nTempPed] 270.000
			aiMechanics[nTempPed] = MANSION2_MECHANICAI_ENTER_FORKLIFT
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_MECHANIC
	ENDSWITCH

	// Make all Mechanics have no decision maker
	SET_CHAR_DECISION_MAKER charMechanics[nTempPed] dmEmpty
//	SET_INFORM_RESPECTED_FRIENDS charMechanics[nTempPed] MANSION2_RESPECTED_FRIENDS_DISTANCE_m MANSION2_NUMBER_OF_RESPECTED_FRIENDS

RETURN


// ****************************************
// MechanicAI: Chat
Mansion2_MechAI_Chat:

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_MECHANIC_DOWNSTAIRS
			// Chat to Boat Driver
			// NOTE: The 'chat' task is now handled within the Guard AI so they are both issued at the same time
//			IF NOT IS_CHAR_DEAD charGuards[MANSION2_GUARD_BOAT_DRIVER]
//				TASK_CHAT_WITH_CHAR charMechanics[nTempPed] charGuards[MANSION2_GUARD_BOAT_DRIVER] FALSE TRUE
//			ENDIF
			timerMechanicsAI[nTempPed] = m_mission_timer + 7000
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_MECHANIC_FOR_CHAT
	ENDSWITCH


	aiMechanics[nTempPed] = MANSION2_MECHANICAI_CHATTING

RETURN


// ****************************************
// MechanicAI: Chatting
Mansion2_MechAI_Chatting:

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_MECHANIC_DOWNSTAIRS
			// Still chatting to Boat Driver?
			IF timerMechanicsAI[nTempPed] < m_mission_timer
				// ...cancel the chat, and enter the forklift
				aiMechanics[nTempPed] = MANSION2_MECHANICAI_ENTER_FORKLIFT
			ENDIF
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_MECHANIC_FOR_CHATTING
	ENDSWITCH

RETURN


// ****************************************
// MechanicAI: Enter Forklift
Mansion2_MechAI_Enter:

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_MECHANIC_DOWNSTAIRS
			IF NOT IS_CAR_DEAD carForklifts[MANSION2_FORKLIFT_DOWNSTAIRS]
				SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
				TASK_ENTER_CAR_AS_DRIVER charMechanics[nTempPed] carForklifts[MANSION2_FORKLIFT_DOWNSTAIRS] MANSION2_PERSIST
			ENDIF
			BREAK

		CASE MANSION2_MECHANIC_UPSTAIRS
			IF NOT IS_CAR_DEAD carForklifts[MANSION2_FORKLIFT_UPSTAIRS]
				SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
				TASK_ENTER_CAR_AS_DRIVER charMechanics[nTempPed] carForklifts[MANSION2_FORKLIFT_UPSTAIRS] MANSION2_PERSIST
			ENDIF
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_MECHANIC_FOR_ENTER

	ENDSWITCH

	aiMechanics[nTempPed] = MANSION2_MECHANICAI_ENTERING_FORKLIFT

RETURN


// ****************************************
// MechanicAI: Entering Forklift
Mansion2_MechAI_Entering:

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	// Has mechanic entered forklift?
	SWITCH nTempPed
		CASE MANSION2_MECHANIC_DOWNSTAIRS
			IF IS_CAR_DEAD carForklifts[MANSION2_FORKLIFT_DOWNSTAIRS]
				// ...leave the mechanic ticking over
				aiMechanics[nTempPed] = MANSION2_MECHANICAI_TICKING_OVER
			ELSE
				IF IS_CHAR_IN_CAR charMechanics[nTempPed] carForklifts[MANSION2_FORKLIFT_DOWNSTAIRS]
					// ...tell the mechanic what to do now
					aiMechanics[nTempPed] = MANSION2_MECHANICAI_FORKLIFT_WANDER
				ENDIF
			ENDIF
			BREAK

		CASE MANSION2_MECHANIC_UPSTAIRS
			IF IS_CAR_DEAD carForklifts[MANSION2_FORKLIFT_UPSTAIRS]
				// ...leave the mechanic ticking over
				aiMechanics[nTempPed] = MANSION2_MECHANICAI_TICKING_OVER
			ELSE
				IF IS_CHAR_IN_CAR charMechanics[nTempPed] carForklifts[MANSION2_FORKLIFT_UPSTAIRS]
					// ...tell the mechanic what to do now
					aiMechanics[nTempPed] = MANSION2_MECHANICAI_FORKLIFT_WANDER
				ENDIF
			ENDIF
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_MECHANIC_FOR_ENTERING
	ENDSWITCH

RETURN


// ****************************************
// MechanicAI: Forklift Wander
Mansion2_MechAI_Wander:

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_MECHANIC_DOWNSTAIRS
			// Choose a new destination
			GOSUB Mansion2_MechAI_Wander_MD
			aiMechanics[nTempPed] = MANSION2_MECHANICAI_FORKLIFT_WANDERING
			BREAK

		CASE MANSION2_MECHANIC_UPSTAIRS
			// Choose a new destination
			GOSUB Mansion2_MechAI_Wander_MU
			aiMechanics[nTempPed] = MANSION2_MECHANICAI_FORKLIFT_WANDERING
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_MECHANIC_FOR_WANDER
	ENDSWITCH

RETURN


// ****************************************
// MechanicAI: Forklift Wandering
Mansion2_MechAI_Wandering:

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_MECHANIC_DOWNSTAIRS
			// Arrived at destination?
			IF timerMechanicsAI[nTempPed] < m_mission_timer
				GOSUB Mansion2_MechAI_Wandering_MD

				IF flagTempFlag = TRUE
					GENERATE_RANDOM_INT_IN_RANGE 2000 4000 nTempInt
					timerMechanicsAI[nTempPed] = m_mission_timer + nTempInt
					aiMechanics[nTempPed] = MANSION2_MECHANICAI_FORKLIFT_WANDER_STOPPED
				ENDIF
			ENDIF
			BREAK

		CASE MANSION2_MECHANIC_UPSTAIRS
			// Arrived at destination?
			IF timerMechanicsAI[nTempPed] < m_mission_timer
				GOSUB Mansion2_MechAI_Wandering_MU

				IF flagTempFlag = TRUE
					GENERATE_RANDOM_INT_IN_RANGE 2000 4000 nTempInt
					timerMechanicsAI[nTempPed] = m_mission_timer + nTempInt
					aiMechanics[nTempPed] = MANSION2_MECHANICAI_FORKLIFT_WANDER_STOPPED
				ENDIF
			ENDIF
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_MECHANIC_FOR_WANDERING
	ENDSWITCH

RETURN


// ****************************************
// MechanicAI: Forklift Wander Stopped
Mansion2_MechAI_WanderStop:

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	// Ready to Wander again?
	IF timerMechanicsAI[nTempPed] < m_mission_timer
		aiMechanics[nTempPed] = MANSION2_MECHANICAI_FORKLIFT_WANDER
	ENDIF

RETURN


// ****************************************
// MechanicAI: Goto Cower Position
Mansion2_MechAI_GotoCower:

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_MECHANIC_DOWNSTAIRS
			// Run to corner
			OPEN_SEQUENCE_TASK nSeqTask
				TASK_LEAVE_ANY_CAR -1																		// Leave forklift if in it
				TASK_FOLLOW_PATH_NODES_TO_COORD -1 -1420.1246 512.2188 2.0463 PEDMOVE_RUN MANSION2_PERSIST  // Corner of warehouse
			CLOSE_SEQUENCE_TASK nSeqTask

			PERFORM_SEQUENCE_TASK charMechanics[nTempPed] nSeqTask
			CLEAR_SEQUENCE_TASK nSeqTask

			aiMechanics[nTempPed] = MANSION2_MECHANICAI_GOING_TO_COWER_POSITION
			timerMechanicsAI[nTempPed] = 0
			BREAK

		CASE MANSION2_MECHANIC_UPSTAIRS
			GET_CHAR_COORDINATES charMechanics[nTempPed] xposTemp yposTemp zposTemp
			GET_CHAR_COORDINATES scplayer xposTemp2 yposTemp2 zposTemp2
			IF xposTemp > xposTemp2
				// Run away from player towards corner beside Harriers
				OPEN_SEQUENCE_TASK nSeqTask
					TASK_LEAVE_ANY_CAR -1																		// Leave forklift if in it
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 -1316.8624 490.2876 10.2026 PEDMOVE_RUN MANSION2_PERSIST	// Corner beside COntrol Room
				CLOSE_SEQUENCE_TASK nSeqTask
			ELSE
				// Run away from player towards corner beside Control Room
				OPEN_SEQUENCE_TASK nSeqTask
					TASK_LEAVE_ANY_CAR -1																		// Leave forklift if in it
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 -1394.8461 491.5163 10.1953 PEDMOVE_RUN MANSION2_PERSIST	// Corner beside Harriers
				CLOSE_SEQUENCE_TASK nSeqTask
			ENDIF

			PERFORM_SEQUENCE_TASK charMechanics[nTempPed] nSeqTask
			CLEAR_SEQUENCE_TASK nSeqTask

			aiMechanics[nTempPed] = MANSION2_MECHANICAI_GOING_TO_COWER_POSITION
			timerMechanicsAI[nTempPed] = 0
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_MECHANIC_FOR_GOTO_COWER
	ENDSWITCH

RETURN


// ****************************************
// MechanicAI: Going To Cower Position
Mansion2_MechAI_GoingToCower:

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF


	IF timerMechanicsAI[nTempPed] > m_mission_timer
		RETURN
	ENDIF


	SWITCH nTempPed
		CASE MANSION2_MECHANIC_DOWNSTAIRS
			// Warehouse Corner
			IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1420.1246 512.2188 2.0463 2.0 2.0 2.0 FALSE
				aiMechanics[nTempPed] = MANSION2_MECHANICAI_AT_COWER_POSITION
				timerMechanicsAI[nTempPed] = 0
			ELSE
				// Corner under the stairs
				IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1389.8097 491.2630 2.0463 2.0 2.0 2.0 FALSE
					aiMechanics[nTempPed] = MANSION2_MECHANICAI_AT_COWER_POSITION
					timerMechanicsAI[nTempPed] = 0
				ENDIF
			ENDIF

			// Record Health when reached Cower position
			GET_CHAR_HEALTH charMechanics[nTempPed] nMechanicCowerHealth[nTempPed]
			BREAK

		CASE MANSION2_MECHANIC_UPSTAIRS
			// Corner beside Harriers
			IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1394.8461 491.5163 10.1953 2.0 2.0 2.0 FALSE
				aiMechanics[nTempPed] = MANSION2_MECHANICAI_AT_COWER_POSITION
				timerMechanicsAI[nTempPed] = 0
			ELSE
				// Corner beside control room
				IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1316.8624 490.2876 10.2026 2.0 2.0 2.0 FALSE
					aiMechanics[nTempPed] = MANSION2_MECHANICAI_AT_COWER_POSITION
					timerMechanicsAI[nTempPed] = 0
				ENDIF
			ENDIF

			// Record Health when reached Cower position
			GET_CHAR_HEALTH charMechanics[nTempPed] nMechanicCowerHealth[nTempPed]
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_MECHANIC_FOR_GOING_TO_COWER
	ENDSWITCH

	GOSUB Mansion2_Update_Mech_Active_Alert_State

RETURN


// ****************************************
// MechanicAI: At Cower Position
Mansion2_MechAI_Cower:

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	GET_CHAR_HEALTH charMechanics[nTempPed] nTempInt
	IF nTempInt < nMechanicCowerHealth[nTempPed]
		aiMechanics[nTempPed] = MANSION2_MECHANICAI_CHANGE_COWER_POSITION
		nMechanicCowerHealth[nTempPed] = nTempInt
		timerMechanicsAI[nTempPed] = 0
		RETURN
	ENDIF

	IF timerMechanicsAI[nTempPed] > m_mission_timer
		RETURN
	ENDIF

	GET_SCRIPT_TASK_STATUS charMechanics[nTempPed] TASK_DUCK m_status
	IF m_status = FINISHED_TASK
		TASK_DUCK charMechanics[nTempPed] MANSION2_PERSIST
		timerMechanicsAI[nTempPed] = m_mission_timer + 1000
	ENDIF

	GOSUB Mansion2_Update_Mech_Active_Alert_State

RETURN


// ****************************************
// MechanicAI: Change Cower Position
Mansion2_MechAI_ChangeCower:

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_MECHANIC_DOWNSTAIRS
			IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1420.1246 512.2188 2.0463 2.0 2.0 2.0 FALSE
				// Run to corner under the stairs
				TASK_FOLLOW_PATH_NODES_TO_COORD charMechanics[nTempPed] -1389.8097 491.2630 2.0463 PEDMOVE_RUN MANSION2_PERSIST	// Corner beside COntrol Room
			ELSE
				// Run to corner beside alarm
				TASK_FOLLOW_PATH_NODES_TO_COORD charMechanics[nTempPed] -1420.1246 512.2188 2.0463 PEDMOVE_RUN MANSION2_PERSIST	// Corner beside Harriers
			ENDIF

			aiMechanics[nTempPed] = MANSION2_MECHANICAI_GOING_TO_COWER_POSITION
			timerMechanicsAI[nTempPed] = m_mission_timer + 10000
			BREAK

		CASE MANSION2_MECHANIC_UPSTAIRS
			IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1394.8461 491.5163 10.1953 2.0 2.0 2.0 FALSE
				// Run away from player towards corner beside Harriers
				TASK_FOLLOW_PATH_NODES_TO_COORD charMechanics[nTempPed] -1316.8624 490.2876 10.2026 PEDMOVE_RUN MANSION2_PERSIST	// Corner beside COntrol Room
			ELSE
				// Run away from player towards corner beside Control Room
				TASK_FOLLOW_PATH_NODES_TO_COORD charMechanics[nTempPed] -1394.8461 491.5163 10.1953 PEDMOVE_RUN MANSION2_PERSIST	// Corner beside Harriers
			ENDIF

			aiMechanics[nTempPed] = MANSION2_MECHANICAI_GOING_TO_COWER_POSITION
			timerMechanicsAI[nTempPed] = m_mission_timer + 10000
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_MECHANIC_FOR_CHANGE_COWER
	ENDSWITCH

RETURN


// ****************************************
// MechanicAI: Goto Alarm Position
Mansion2_MechAI_GotoAlarm:

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_MECHANIC_DOWNSTAIRS
			// Run to downstairs
			OPEN_SEQUENCE_TASK nSeqTask
				TASK_LEAVE_ANY_CAR -1																		// Leave forklift if in it
				TASK_FOLLOW_PATH_NODES_TO_COORD -1 -1414.3495 514.0193 2.0391 PEDMOVE_RUN MANSION2_PERSIST  // Corner of warehouse
			CLOSE_SEQUENCE_TASK nSeqTask

			PERFORM_SEQUENCE_TASK charMechanics[nTempPed] nSeqTask
			CLEAR_SEQUENCE_TASK nSeqTask

			aiMechanics[nTempPed] = MANSION2_MECHANICAI_GOING_TO_ALARM
			BREAK

		CASE MANSION2_MECHANIC_UPSTAIRS
			GET_CHAR_COORDINATES charMechanics[nTempPed] xposTemp yposTemp zposTemp
			GET_CHAR_COORDINATES scplayer xposTemp2 yposTemp2 zposTemp2
			IF xposTemp > xposTemp2
				// Run away from player towards corner beside Harriers
				OPEN_SEQUENCE_TASK nSeqTask
					TASK_LEAVE_ANY_CAR -1																		// Leave forklift if in it
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 -1313.3726 488.6687 10.2026 PEDMOVE_RUN MANSION2_PERSIST	// Corner beside Harriers
				CLOSE_SEQUENCE_TASK nSeqTask
			ELSE
				// Run away from player towards corner beside Control Room
				OPEN_SEQUENCE_TASK nSeqTask
					TASK_LEAVE_ANY_CAR -1																		// Leave forklift if in it
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 -1412.3975 488.6644 10.2026 PEDMOVE_RUN MANSION2_PERSIST	// Corner beside COntrol Room
				CLOSE_SEQUENCE_TASK nSeqTask
			ENDIF

			PERFORM_SEQUENCE_TASK charMechanics[nTempPed] nSeqTask
			CLEAR_SEQUENCE_TASK nSeqTask

			aiMechanics[nTempPed] = MANSION2_MECHANICAI_GOING_TO_ALARM
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_MECHANIC_FOR_GOTO_ALARM
	ENDSWITCH

RETURN


// ****************************************
// MechanicAI: Going To Alarm Position
Mansion2_MechAI_GoingToAlarm:

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_MECHANIC_DOWNSTAIRS
			// Warehouse Corner
			IF flagCarrierOnAlert = TRUE
				aiMechanics[nTempPed] = MANSION2_MECHANICAI_GOTO_COWER_POSITION
				RETURN
			ELSE
				IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1414.3495 514.8193 2.0391 1.2 1.2 1.2 FALSE
					aiMechanics[nTempPed] = MANSION2_MECHANICAI_TRIGGER_ALARM
					timerMechanicsAI[nTempPed] = 0
				ENDIF
			ENDIF
			BREAK

		CASE MANSION2_MECHANIC_UPSTAIRS
			// Corner beside Harriers
			IF flagCarrierOnAlert = TRUE
				aiMechanics[nTempPed] = MANSION2_MECHANICAI_GOTO_COWER_POSITION
				RETURN
			ELSE
				IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1313.3726 487.8687 10.2026 1.2 1.2 1.2 FALSE
					aiMechanics[nTempPed] = MANSION2_MECHANICAI_TRIGGER_ALARM
					timerMechanicsAI[nTempPed] = 0
				ELSE
					IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1412.3975 487.8644 10.2026 1.2 1.2 1.2 FALSE
						aiMechanics[nTempPed] = MANSION2_MECHANICAI_TRIGGER_ALARM
						timerMechanicsAI[nTempPed] = 0
					ENDIF
				ENDIF
			ENDIF
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_MECHANIC_FOR_GOING_TO_ALARM
	ENDSWITCH

	GOSUB Mansion2_Update_Mech_Active_Alert_State

RETURN


// ****************************************
// MechanicAI: Trigger Alarm
Mansion2_MechAI_TriggerAlarm:

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_MECHANIC_DOWNSTAIRS
			TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM charMechanics[nTempPed] -1414.3495 513.4193 2.0391 0.0 0.2 CARDS_RAISE CASINO 10.0 FALSE FALSE FALSE FALSE -1
//			TASK_ACHIEVE_HEADING charMechanics[nTempPed] 0.0
			timerMechanicsAI[nTempPed] = m_mission_timer + 2000

			aiMechanics[nTempPed] = MANSION2_MECHANICAI_TRIGGERING_ALARM
			BREAK

		CASE MANSION2_MECHANIC_UPSTAIRS
			IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1313.3726 487.8687 10.2026 1.2 1.2 1.2 FALSE
				TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM charMechanics[nTempPed] -1313.3726 489.3687 10.2026 180.0 0.2 CARDS_RAISE CASINO 10.0 FALSE FALSE FALSE FALSE -1
			ELSE
				TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM charMechanics[nTempPed] -1412.3975 489.3644 10.2026 180.0 0.2 CARDS_RAISE CASINO 10.0 FALSE FALSE FALSE FALSE -1
			ENDIF
//			TASK_ACHIEVE_HEADING charMechanics[nTempPed] 180.0
			timerMechanicsAI[nTempPed] = m_mission_timer + 2000

			aiMechanics[nTempPed] = MANSION2_MECHANICAI_TRIGGERING_ALARM
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_MECHANIC_FOR_TRIGGER_ALARM
	ENDSWITCH

RETURN


// ****************************************
// MechanicAI: Triggering Alarm
Mansion2_MechAI_TriggeringAlarm:

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	SWITCH nTempPed
		CASE MANSION2_MECHANIC_DOWNSTAIRS
			IF timerMechanicsAI[nTempPed] < m_mission_timer
				aiMechanics[nTempPed] = MANSION2_MECHANICAI_GOTO_COWER_POSITION
				flagCarrierOnAlert = TRUE

				IF nReasonForDetection = MANSION2_DETECTED_UNKNOWN
					nReasonForDetection = MANSION2_DETECTED_BY_MECHANIC
				ENDIF
			ENDIF
			BREAK

		CASE MANSION2_MECHANIC_UPSTAIRS
			IF timerMechanicsAI[nTempPed] < m_mission_timer
				aiMechanics[nTempPed] = MANSION2_MECHANICAI_GOTO_COWER_POSITION
				flagCarrierOnAlert = TRUE

				IF nReasonForDetection = MANSION2_DETECTED_UNKNOWN
					nReasonForDetection = MANSION2_DETECTED_BY_MECHANIC
				ENDIF
			ENDIF
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_MECHANIC_FOR_TRIGGERING_ALARM
	ENDSWITCH

	GOSUB Mansion2_Update_Mech_Active_Alert_State

RETURN


// ****************************************
// MechanicAI: Wandering (Mechanic Downstairs)
Mansion2_MechAI_Wander_MD:

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	IF IS_CAR_DEAD carForklifts[MANSION2_FORKLIFT_DOWNSTAIRS]
		aiMechanics[nTempPed] = MANSION2_MECHANICAI_TICKING_OVER
		RETURN
	ENDIF

	GENERATE_RANDOM_INT_IN_RANGE 0 4 nTempInt
	SWITCH nTempInt
		CASE 0
//			TASK_CAR_DRIVE_TO_COORD charMechanics[nTempPed] carForklifts[MANSION2_FORKLIFT_DOWNSTAIRS] -1392.3628 507.1703 2.0463 2.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_STOPFORCARS
			TASK_CAR_DRIVE_TO_COORD charMechanics[nTempPed] carForklifts[MANSION2_FORKLIFT_DOWNSTAIRS] -1400.2054 507.2590 2.0463 2.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_STOPFORCARS
			BREAK
		CASE 1
//			TASK_CAR_DRIVE_TO_COORD charMechanics[nTempPed] carForklifts[MANSION2_FORKLIFT_DOWNSTAIRS] -1404.5876 498.4576 2.0391 2.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_STOPFORCARS
			TASK_CAR_DRIVE_TO_COORD charMechanics[nTempPed] carForklifts[MANSION2_FORKLIFT_DOWNSTAIRS] -1394.3872 506.6834 2.0463 2.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_STOPFORCARS
			BREAK
		CASE 2
//			TASK_CAR_DRIVE_TO_COORD charMechanics[nTempPed] carForklifts[MANSION2_FORKLIFT_DOWNSTAIRS] -1411.9678 510.0882 2.0391 2.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_STOPFORCARS
			TASK_CAR_DRIVE_TO_COORD charMechanics[nTempPed] carForklifts[MANSION2_FORKLIFT_DOWNSTAIRS] -1399.3480 501.5815 2.0463 2.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_STOPFORCARS
			BREAK
		CASE 3
//			TASK_CAR_DRIVE_TO_COORD charMechanics[nTempPed] carForklifts[MANSION2_FORKLIFT_DOWNSTAIRS] -1400.8392 507.0317 2.0463 2.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_STOPFORCARS
			TASK_CAR_DRIVE_TO_COORD charMechanics[nTempPed] carForklifts[MANSION2_FORKLIFT_DOWNSTAIRS] -1410.6714 501.3295 2.0391 2.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_STOPFORCARS
			BREAK
	ENDSWITCH

	// Store the destination
	nMechanicDestination[nTempPed] = nTempInt

	// Make sure the Forklift gets a bit away from the current location before checking for a new location
	timerMechanicsAI[nTempPed] = m_mission_timer + 10000

RETURN


// ****************************************
// MechanicAI: Wandering (Mechanic Downstairs)
Mansion2_MechAI_Wandering_MD:

	flagTempFlag = FALSE

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	IF IS_CAR_DEAD carForklifts[MANSION2_FORKLIFT_DOWNSTAIRS]
		aiMechanics[nTempPed] = MANSION2_MECHANICAI_TICKING_OVER
		RETURN
	ENDIF

	SWITCH nMechanicDestination[nTempPed]
		CASE 0
			IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1400.2054 507.2590 2.0463 4.0 4.0 4.0 FALSE
				flagTempFlag = TRUE
			ENDIF
			BREAK

		CASE 1
			IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1394.3872 506.6834 2.0463 4.0 4.0 4.0 FALSE
				flagTempFlag = TRUE
			ENDIF
			BREAK

		CASE 2
			IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1399.3480 501.5815 2.0463 4.0 4.0 4.0 FALSE
				flagTempFlag = TRUE
			ENDIF
			BREAK

		CASE 3
			IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1410.6714 501.3295 2.0391 4.0 4.0 4.0 FALSE
				flagTempFlag = TRUE
			ENDIF
			BREAK
	ENDSWITCH

RETURN


// ****************************************
// MechanicAI: Wandering (Mechanic Upstairs)
Mansion2_MechAI_Wander_MU:

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	IF IS_CAR_DEAD carForklifts[MANSION2_FORKLIFT_UPSTAIRS]
		aiMechanics[nTempPed] = MANSION2_MECHANICAI_TICKING_OVER
		RETURN
	ENDIF

	GENERATE_RANDOM_INT_IN_RANGE 0 4 nTempInt
	SWITCH nTempInt
		CASE 0
			TASK_CAR_DRIVE_TO_COORD charMechanics[nTempPed] carForklifts[MANSION2_FORKLIFT_UPSTAIRS] -1321.4218 495.9306 10.2026 2.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS
			BREAK
		CASE 1
			TASK_CAR_DRIVE_TO_COORD charMechanics[nTempPed] carForklifts[MANSION2_FORKLIFT_UPSTAIRS] -1305.4445 497.4329 10.2026 2.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS
			BREAK
		CASE 2
			TASK_CAR_DRIVE_TO_COORD charMechanics[nTempPed] carForklifts[MANSION2_FORKLIFT_UPSTAIRS] -1307.4249 503.4752 10.1953 2.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS
			BREAK
		CASE 3
			TASK_CAR_DRIVE_TO_COORD charMechanics[nTempPed] carForklifts[MANSION2_FORKLIFT_UPSTAIRS] -1351.0980 506.4217 10.1953 2.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS
			BREAK
		CASE 4
			TASK_CAR_DRIVE_TO_COORD charMechanics[nTempPed] carForklifts[MANSION2_FORKLIFT_UPSTAIRS] -1390.8839 507.6548 10.1953 3.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS
			BREAK
	ENDSWITCH

	// Store the destination
	nMechanicDestination[nTempPed] = nTempInt

	// Make sure the Forklift gets a bit away from the current location before checking for a new location
	timerMechanicsAI[nTempPed] = m_mission_timer + 10000

RETURN


// ****************************************
// MechanicAI: Wandering (Mechanic Upstairs)
Mansion2_MechAI_Wandering_MU:

	flagTempFlag = FALSE

	IF IS_CHAR_DEAD charMechanics[nTempPed]
		RETURN
	ENDIF

	IF IS_CAR_DEAD carForklifts[MANSION2_FORKLIFT_DOWNSTAIRS]
		aiMechanics[nTempPed] = MANSION2_MECHANICAI_TICKING_OVER
		RETURN
	ENDIF

	SWITCH nMechanicDestination[nTempPed]
		CASE 0
			IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1321.4218 495.9306 10.2026 4.0 4.0 4.0 FALSE
				flagTempFlag = TRUE
			ENDIF
			BREAK

		CASE 1
			IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1305.4445 497.4329 10.2026 4.0 4.0 4.0 FALSE
				flagTempFlag = TRUE
			ENDIF
			BREAK

		CASE 2
			IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1307.4249 503.4752 10.1953 4.0 4.0 4.0 FALSE
				flagTempFlag = TRUE
			ENDIF
			BREAK

		CASE 3
			IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1351.0980 506.4217 10.1953 4.0 4.0 4.0 FALSE
				flagTempFlag = TRUE
			ENDIF
			BREAK

		CASE 4
			IF LOCATE_CHAR_ANY_MEANS_3D charMechanics[nTempPed] -1390.8839 507.6548 10.1953 4.0 4.0 4.0 FALSE
				flagTempFlag = TRUE
			ENDIF
			BREAK
	ENDSWITCH

RETURN



// *************************************************************************************************************
//								                  MISCELLANEOUS
// *************************************************************************************************************

// ****************************************
// Maintain Harrier Flypast
Mansion2_Maintain_Harrier_FlyPast:

	IF statusHarrierFlyPast = -1
		RETURN
	ENDIF

	nTempInt = MANSION2_PLANE_BIG_LIFT
	IF NOT statusHarrierFlyPast = 0
		IF IS_CAR_DEAD carPlanes[nTempInt]
			statusHarrierFlyPast = -1
			MARK_CAR_AS_NO_LONGER_NEEDED carPlanes[nTempInt]
			RETURN
		ENDIF
	ENDIF

	SWITCH statusHarrierFlyPast
		CASE 0
			// Goto first coordinates
			CREATE_CAR HYDRA -84.0771 418.3652 92.2066 carPlanes[nTempInt]
			SET_CAR_COORDINATES carPlanes[nTempInt] -84.0771 418.3652 92.2066
			SET_CAR_HEADING carPlanes[nTempInt] 91.2218
			SET_CAR_STATUS carPlanes[nTempInt] STATUS_PHYSICS
			SET_CAR_FORWARD_SPEED carPlanes[nTempInt] 70.0
			SET_PLANE_THROTTLE carPlanes[nTempInt] 1.5
			PLANE_STARTS_IN_AIR carPlanes[nTempInt]
//			CAR_GOTO_COORDINATES carPlanes[nTempInt] -2877.4304 185.3741 358.0352
			PLANE_FLY_IN_DIRECTION carPlanes[nTempInt] 190.0 20.0 30.0
			timerFlyPast = m_mission_timer + 25000
			statusHarrierFlyPast++
			BREAK

		CASE 1
			// Has plane reached first coords?
			IF timerFlyPast < m_mission_timer
				// ...delete plane
				DELETE_CAR carPlanes[nTempInt]
				statusHarrierFlyPast = -1
			ENDIF
			BREAK
	ENDSWITCH

RETURN


// ****************************************
// Maintain Harrier Takeoff
Mansion2_Takeoff_Harrier:

	IF statusHarrierTakeoff = -1
		RETURN
	ENDIF

	nTempInt = MANSION2_PLANE_TAKEOFF
	IF IS_CAR_DEAD carPlanes[nTempInt]
		// ...plane is dead, so delete it
		statusHarrierTakeoff = -1
		DELETE_CAR carPlanes[nTempInt]
		RETURN
	ENDIF

	SWITCH statusHarrierTakeoff
		CASE 0
			// ...playback has began, so set up a timer to decide when it should not be classed as being on the Carrier
			timerTakeoff = m_mission_timer + 6000
			statusHarrierTakeoff++
			BREAK

		CASE 1
			// ...still on the Carrier but taking off
			IF timerTakeoff < m_mission_timer
				flagPlanesOnCarrier[nTempInt] = FALSE

				// ...also allow Guard watching Harrier to move
				IF flagGuardsOnAlert[MANSION2_GUARD_BOAT_BACK] = FALSE
					aiGuards[MANSION2_GUARD_BOAT_BACK] = MANSION2_GUARDAI_WANDER
				ENDIF

				statusHarrierTakeoff++
			ENDIF
			BREAK

		CASE 2
			// ...playing recording
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR carPlanes[nTempInt]
				// ...playback finished, so delete place
				DELETE_CAR carPlanes[nTempInt]
				statusHarrierTakeoff = -1
			ENDIF
			BREAK
	ENDSWITCH

RETURN


// ****************************************
// Maintain Harrier Landing
Mansion2_Landing_Harrier:

	IF statusHarrierLanding = -1
		RETURN
	ENDIF

	nTempInt = MANSION2_PLANE_BIG_LIFT
	IF statusHarrierLanding > 0
		IF IS_CAR_DEAD carPlanes[nTempInt]
			statusHarrierLanding = -1
			DELETE_CAR carPlanes[nTempInt]
			RETURN
		ENDIF
	ENDIF

	SWITCH statusHarrierLanding
		CASE 0
			// ...create car
			CREATE_CAR HYDRA -1354.5869 6.2662 13.1485 carPlanes[nTempInt]
			// ...and pilot
			CREATE_CHAR_INSIDE_CAR carPlanes[nTempInt] PEDTYPE_MISSION2 ARMY charGuards[MANSION2_GUARD_PILOT_BIG_LIFT]
			SET_CHAR_DECISION_MAKER charGuards[MANSION2_GUARD_PILOT_BIG_LIFT] dmEmpty
			SET_CHAR_IS_TARGET_PRIORITY charGuards[MANSION2_GUARD_PILOT_BIG_LIFT] TRUE
			// ...start playback
			START_PLAYBACK_RECORDED_CAR carPlanes[nTempInt] MANSION2_CAR_RECORDING_HARRIER_LANDING

			statusHarrierLanding++
			BREAK

		CASE 1
			// ...check if Harrier should be classed as on Carrier
			IF LOCATE_CAR_3D carPlanes[nTempInt] -1456.3562 501.1173 9.0078 10.0 15.0 20.0 FALSE
				flagPlanesOnCarrier[nTempInt] = TRUE
				statusHarrierLanding++
			ENDIF
			BREAK

		CASE 2
			// ...check if recording has ended
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR carPlanes[nTempInt]
				// ...activate the pilot's AI
				aiGuards[MANSION2_GUARD_PILOT_BIG_LIFT] = MANSION2_GUARDAI_ACTIVATION
				ADD_BLIP_FOR_CHAR charGuards[MANSION2_GUARD_PILOT_BIG_LIFT] blipGuards[MANSION2_GUARD_PILOT_BIG_LIFT]
				CHANGE_BLIP_DISPLAY blipGuards[MANSION2_GUARD_PILOT_BIG_LIFT] BLIP_ONLY
				SET_BLIP_ALWAYS_DISPLAY_ON_ZOOMED_RADAR blipGuards[MANSION2_GUARD_PILOT_BIG_LIFT] TRUE

				statusHarrierLanding = -1
			ENDIF
			BREAK
	ENDSWITCH

RETURN


// *************************************************************************************************************
// 												  AUDIO ROUTINES   
// *************************************************************************************************************

// ------------------------------
//	SPEECH ENGINE
// ------------------------------

// ****************************************
// UPDATE SPEECH
Mansion2_Update_Speech:

	SWITCH nConversationStatus
		CASE MANSION2_CONVERSATION_STATUS_NONE
		CASE MANSION2_CONVERSATION_STATUS_PREPARED
		CASE MANSION2_CONVERSATION_STATUS_INTERRUPTED
		CASE MANSION2_CONVERSATION_STATUS_FINISHED
			// ...nothing to do
			BREAK

		CASE MANSION2_CONVERSATION_STATUS_PLAYING
			// ...playing the conversation
			GOSUB Mansion2_Playing_Conversation
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_CONVERSATION_STATUS_ID
			RETURN
	ENDSWITCH

RETURN


// ****************************************
// Initial Speech Slot Preloads
Mansion2_Initial_Speech_Slot_Preloads:

	// Preload the first two slots if possible
	IF nNextPreloadConversationLine = nCurrentMaxConversationLines
		RETURN
	ENDIF

	// ...slot 1
	nTempInt = MANSION2_FIRST_SPEECH_SLOT
   	nAudioSlotStatus[nTempInt] = MANSION2_AUDIO_SLOT_LOADING
   	LOAD_MISSION_AUDIO nTempInt nConversationSequenceSpeechID[nNextPreloadConversationLine]
   	nNextPreloadConversationLine++

	// Are there more lines to be preloaded
	IF nNextPreloadConversationLine = nCurrentMaxConversationLines
		RETURN
	ENDIF

	// ...slot 2
	nTempInt++
	IF nTempInt > MANSION2_LAST_SPEECH_SLOT
		RETURN
	ENDIF

   	nAudioSlotStatus[nTempInt] = MANSION2_AUDIO_SLOT_LOADING
   	LOAD_MISSION_AUDIO nTempInt nConversationSequenceSpeechID[nNextPreloadConversationLine]
   	nNextPreloadConversationLine++

RETURN


// ****************************************
// Playing Conversation
Mansion2_Playing_Conversation:

	// Check if the conversation should be interrupted
	GOSUB Mansion2_Check_If_Interrupt_Conversation
	IF nConversationStatus = MANSION2_CONVERSATION_STATUS_INTERRUPTED
		GOSUB Mansion2_Interrupt_Conversation
		RETURN
	ENDIF

	// Check the status of the current slot
	SWITCH nAudioSlotStatus[nSpeechCurrentSlot]
		CASE MANSION2_AUDIO_SLOT_FREE
			RETURN
			BREAK

		CASE MANSION2_AUDIO_SLOT_LOADING
			// ...check if the speech has loaded
			IF NOT HAS_MISSION_AUDIO_LOADED nSpeechCurrentSlot
				// ...no
				RETURN
			ENDIF

			// Speech has loaded
			nAudioSlotStatus[nSpeechCurrentSlot] = MANSION2_AUDIO_SLOT_LOADED
			BREAK

		CASE MANSION2_AUDIO_SLOT_LOADED
			// ...play the speech and display the subtitles
			PLAY_MISSION_AUDIO nSpeechCurrentSlot

			IF flagDisplaySpeechSubtitle = TRUE
				PRINT_NOW $tlConversationSequenceSubtitle[nCurrentConversationLine] 10000 1
			ENDIF

			nAudioSlotStatus[nSpeechCurrentSlot] = MANSION2_AUDIO_SLOT_PLAYING
			RETURN
			BREAK

		CASE MANSION2_AUDIO_SLOT_PLAYING
			// ...check if the speech has finished playing
			IF NOT HAS_MISSION_AUDIO_FINISHED nSpeechCurrentSlot
				// ...still playing
				RETURN
			ENDIF

			// Speech has finished playing, so move on to the next piece
			GOSUB Mansion2_Current_Speech_Finished			
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_AUDIO_SLOT_STATUS
			RETURN
	ENDSWITCH

RETURN


// ****************************************
// Current Conversation has been interrupted
Mansion2_Interrupt_Conversation:

	// Clear mission audio
	nTempInt = MANSION2_FIRST_SPEECH_SLOT
	WHILE nTempInt <= MANSION2_LAST_SPEECH_SLOT
		CLEAR_MISSION_AUDIO nTempInt
		nTempInt++
	ENDWHILE

	GOSUB Mansion2_Finish_Conversation

RETURN


// ****************************************
// Current Speech has finished
Mansion2_Current_Speech_Finished:

	// Housekeeping
	nAudioSlotStatus[nSpeechCurrentSlot] = MANSION2_AUDIO_SLOT_FREE
	nCurrentConversationLine++
	IF nCurrentConversationLine = nCurrentMaxConversationLines
		// ...conversation over
		GOSUB Mansion2_Finish_Conversation
		RETURN
	ENDIF

	// Preload the next piece of audio that will be played in this slot
	IF nNextPreloadConversationLine < nCurrentMaxConversationLines
		// ...there is another piece of speech required in this slot
		nAudioSlotStatus[nSpeechCurrentSlot] = MANSION2_AUDIO_SLOT_LOADING
		LOAD_MISSION_AUDIO nSpeechCurrentSlot nConversationSequenceSpeechID[nNextPreloadConversationLine]
		nNextPreloadConversationLine++
	ENDIF

	// Switch to the next speech slot
	nSpeechCurrentSlot++
	IF nSpeechCurrentSlot > MANSION2_LAST_SPEECH_SLOT
		nSpeechCurrentSlot = MANSION2_FIRST_SPEECH_SLOT
	ENDIF

RETURN


// ****************************************
// Current Conversation has finished
Mansion2_Finish_Conversation:

	// Mark this conversation as 'played'
	SET_BIT bitsConversationsPlayed nCurrentConversationID

	nCurrentConversationID			= MANSION2_CONVERSATION_NONE
	nConversationStatus				= MANSION2_CONVERSATION_STATUS_NONE
	nRequiredConversationID			= MANSION2_CONVERSATION_NONE
	nSpeechCurrentSlot				= MANSION2_FIRST_SPEECH_SLOT
	nCurrentConversationLine		= 0
	nCurrentMaxConversationLines	= 0
	nNextPreloadConversationLine	= 0

	nTempInt = MANSION2_FIRST_SPEECH_SLOT
	WHILE nTempInt < MANSION2_LAST_SPEECH_SLOT
		nAudioSlotStatus[nTempInt] = MANSION2_AUDIO_SLOT_FREE
		nTempInt++
	ENDWHILE

	// Clear subtitles
	CLEAR_SMALL_PRINTS

	// Introduce a delay before the next conversation can be prepared
	timerDelayBeforeNextConversation = m_mission_timer + MANSION2_DELAY_BEFORE_NEXT_CONVERSATION_msec

RETURN


// ------------------------------
//  EXTERNAL SPEECH CONTROLS
// ------------------------------

// ****************************************
// Conversation Command Prepare
Mansion2_Conversation_Command_Prepare:

	// Prepare a conversation
	IF NOT nConversationStatus = MANSION2_CONVERSATION_STATUS_NONE
		GOSUB Mansion2_Conversation_Command_Cancel
	ENDIF

	// Wait for the timer to elapse before preparing the next conversation
	IF timerDelayBeforeNextConversation > m_mission_timer
		RETURN
	ENDIF

	timerDelayBeforeNextConversation	= 0
	nCurrentConversationID				= nRequiredConversationID
	nRequiredConversationID				= MANSION2_CONVERSATION_NONE
	nSpeechCurrentSlot					= MANSION2_FIRST_SPEECH_SLOT
	nCurrentConversationLine			= 0
	nCurrentMaxConversationLines		= 0
	nNextPreloadConversationLine		= 0

	nTempInt = MANSION2_FIRST_SPEECH_SLOT
	WHILE nTempInt < MANSION2_LAST_SPEECH_SLOT
		nAudioSlotStatus[nTempInt] = MANSION2_AUDIO_SLOT_FREE
		nTempInt++
	ENDWHILE

	// Prepare the next conversation
	GOSUB Mansion2_Prepare_Next_Conversation
	
RETURN


// ****************************************
// Conversation Command Play
Mansion2_Conversation_Command_Play:

	// Ensure the conversation has been prepared
	IF NOT nConversationStatus = MANSION2_CONVERSATION_STATUS_PREPARED
		RETURN
	ENDIF

	nConversationStatus = MANSION2_CONVERSATION_STATUS_PLAYING

RETURN


// ****************************************
// Conversation Command Cancel
Mansion2_Conversation_Command_Cancel:

	// Ensure the conversation has been prepared
	IF nConversationStatus = MANSION2_CONVERSATION_STATUS_NONE
		RETURN
	ENDIF

	GOSUB Mansion2_Interrupt_Conversation

RETURN


// ------------------------------
//  MISSION SPECIFIC SPEECH SETUP
// ------------------------------

// ****************************************
// Prepare Next Conversation
Mansion2_Prepare_Next_Conversation:

	SWITCH nCurrentConversationID
		CASE MANSION2_CONVERSATION_CARL_MEETS_TORENO
			GOSUB Mansion2_Prepare_Conversation_Carl_Meets_Toreno
			BREAK
		
		CASE MANSION2_CONVERSATION_INSTRUCTIONS
			GOSUB Mansion2_Prepare_Conversation_Instructions
			BREAK

		CASE MANSION2_CONVERSATION_GOTO_CARRIER
			GOSUB Mansion2_Prepare_Conversation_Goto_Carrier
			BREAK

		CASE MANSION2_CONVERSATION_SNEAK_INTO_CARRIER
			GOSUB Mansion2_Prepare_Conversation_Sneak_Into_Carrier
			BREAK

		CASE MANSION2_CONVERSATION_STOLEN_HARRIER
			GOSUB Mansion2_Prepare_Conversation_Stolen_Harrier
			BREAK

		CASE MANSION2_CONVERSATION_VAPOURISED
			GOSUB Mansion2_Prepare_Conversation_Vapourised
			BREAK

		CASE MANSION2_CONVERSATION_DESTROY_FLOTILLA
			GOSUB Mansion2_Prepare_Conversation_Destroy_Flotilla
			BREAK

		CASE MANSION2_CONVERSATION_FLOTILLA_DESTROYED
			GOSUB Mansion2_Prepare_Conversation_Flotilla_Destroyed
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_CONVERSATION
			RETURN
	ENDSWITCH

	// Preload the first two pieces of speech
	GOSUB Mansion2_Initial_Speech_Slot_Preloads
	nConversationStatus = MANSION2_CONVERSATION_STATUS_PREPARED

RETURN


// ****************************************
// Check if the current conversation should be interrupted
Mansion2_Check_If_Interrupt_Conversation:

	// Is Carl Dead?
	SWITCH nCurrentConversationID
		CASE MANSION2_CONVERSATION_CARL_MEETS_TORENO
		CASE MANSION2_CONVERSATION_INSTRUCTIONS
		CASE MANSION2_CONVERSATION_GOTO_CARRIER
		CASE MANSION2_CONVERSATION_SNEAK_INTO_CARRIER
		CASE MANSION2_CONVERSATION_STOLEN_HARRIER
		CASE MANSION2_CONVERSATION_VAPOURISED
		CASE MANSION2_CONVERSATION_DESTROY_FLOTILLA
		CASE MANSION2_CONVERSATION_FLOTILLA_DESTROYED
			IF IS_CHAR_DEAD scplayer
				nConversationStatus = MANSION2_CONVERSATION_STATUS_INTERRUPTED
				RETURN
			ENDIF
			BREAK
	ENDSWITCH

	// Is Toreno dead?
	SWITCH nCurrentConversationID
		CASE MANSION2_CONVERSATION_CARL_MEETS_TORENO
		CASE MANSION2_CONVERSATION_INSTRUCTIONS
			IF IS_CHAR_DEAD charToreno
				nConversationStatus = MANSION2_CONVERSATION_STATUS_INTERRUPTED
				RETURN
			ENDIF
			BREAK
	ENDSWITCH

RETURN


// ****************************************
// Prepare Conversation: Carl Meets Toreno
Mansion2_Prepare_Conversation_Carl_Meets_Toreno:

	// Conversation between Toreno and Carl 

	nTempInt = 0
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_AA
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_AA

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_AB
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_AB

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_AC
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_AC

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_AD
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_AD

	nTempInt++
	IF nTempInt > MANSION2_MAX_CONVERSATION_LINES
		WRITE_DEBUG TOO_MANY_CONVERSATION_LINES
	ENDIF

	nCurrentMaxConversationLines = nTempInt

RETURN


// ****************************************
// Prepare Conversation: Instructions
Mansion2_Prepare_Conversation_Instructions:

	// Instructions from Toreno for Carl 

	nTempInt = 0
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_BA
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_BA

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_BB
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_BB

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_BC
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_BC

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_BD
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_BD

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_BE
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_BE

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_BF
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_BF

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_BG
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_BG

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_BH
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_BH

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_BJ
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_BJ

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_BK
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_BK

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_BL
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_BL

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_BM
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_BM

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_BN
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_BN

	nTempInt++
	IF nTempInt > MANSION2_MAX_CONVERSATION_LINES
		WRITE_DEBUG TOO_MANY_CONVERSATION_LINES
	ENDIF

	nCurrentMaxConversationLines = nTempInt

RETURN


// ****************************************
// Prepare Conversation: Goto Carrier
Mansion2_Prepare_Conversation_Goto_Carrier:

	// Information from Toreno (talking through earpiece) to Carl 

	nTempInt = 0
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_CA
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_CA

	nCurrentMaxConversationLines = 1

RETURN


// ****************************************
// Prepare Conversation: Sneak Into Carrier
Mansion2_Prepare_Conversation_Sneak_Into_Carrier:

	// Conversation between Toreno (through earpiece) and Carl

	nTempInt = 0
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_DA
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_DA

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_DB
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_DB

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_DC
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_DC

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_DD
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_DD

	nTempInt++
	IF nTempInt > MANSION2_MAX_CONVERSATION_LINES
		WRITE_DEBUG TOO_MANY_CONVERSATION_LINES
	ENDIF

	nCurrentMaxConversationLines = nTempInt

RETURN


// ****************************************
// Prepare Conversation: Flotilla Destroyed
Mansion2_Prepare_Conversation_Flotilla_Destroyed:

	// Final Conversation between Toreno and Carl 

	nTempInt = 0
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_FA
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_FA

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_FB
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_FB

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_FC
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_FC

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_FD
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_FD

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_FE
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_FE

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_FF
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_FF

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_FG
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_FG

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_FK
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_FK

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_FL
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_FL

	nTempInt++
	IF nTempInt > MANSION2_MAX_CONVERSATION_LINES
		WRITE_DEBUG TOO_MANY_CONVERSATION_LINES
	ENDIF

	nCurrentMaxConversationLines = nTempInt

RETURN


// ****************************************
// Prepare Conversation: Stolen Harrier
Mansion2_Prepare_Conversation_Stolen_Harrier:

	// Toreno Congratulates Carl

	nTempInt = 0
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_EA
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_EA

	nCurrentMaxConversationLines = 1

RETURN


// ****************************************
// Prepare Conversation: Vapourised
Mansion2_Prepare_Conversation_Vapourised:

	// Enemy Harrier pilot

	nTempInt = 0
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_EB
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_EB

	nCurrentMaxConversationLines = 1

RETURN


// ****************************************
// Prepare Conversation: Destroy Flotilla
Mansion2_Prepare_Conversation_Destroy_Flotilla:

	// Toreno tells Carl to destroy the flotilla 

	nTempInt = 0
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_EC
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_EC

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_EE
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_EE

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_EF
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_EF

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_EG
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_EG

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_EH
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_EH

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_MAN2_EJ
	$tlConversationSequenceSubtitle[nTempInt] = MAN2_EJ

	nTempInt++
	IF nTempInt > MANSION2_MAX_CONVERSATION_LINES
		WRITE_DEBUG TOO_MANY_CONVERSATION_LINES
	ENDIF

	nCurrentMaxConversationLines = nTempInt

RETURN




// *************************************************************************************************************
// 												HOUSEKEEPING GOSUBS   
// *************************************************************************************************************

// ****************************************
// DEBUG TOOLS
Mansion2_Debug_Tools:

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
		Mansion2_view_debug[0] = m_stage
		Mansion2_view_debug[1] = m_goals
		Mansion2_view_debug[2] = m_mission_timer
		Mansion2_view_debug[3] = 0
		Mansion2_view_debug[4] = 0
		Mansion2_view_debug[5] = 0
		Mansion2_view_debug[6] = 0
		Mansion2_view_debug[7] = 0
		// First two lines are so that the important data displayed is not hidden by other text
		VIEW_INTEGER_VARIABLE Mansion2_view_debug[0] m_stage
		VIEW_INTEGER_VARIABLE Mansion2_view_debug[1] m_goals
		VIEW_INTEGER_VARIABLE Mansion2_view_debug[2] m_mission_timer
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
Mansion2_Debug_Shortcuts:

	// Jumps
	// -----

	IF NOT IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_J
		RETURN
	ENDIF


	IF m_stage = MANSION2_STAGE_HEAD_TOWARDS_CARRIER
		CLEAR_AREA xposDestination yposDestination zposDestination 50.0 FALSE
		SET_CHAR_COORDINATES scplayer -1460.1915 513.4575 2.0030
		SET_CHAR_HEADING scplayer 270.0000
		GOSUB Mansion2_Next_Stage
		RETURN
	ENDIF

	
	IF m_stage = MANSION2_STAGE_ENTER_AIRCRAFT_CARRIER
	AND m_goals > 1
	AND m_goals < 8
		// ...skips past cutscene
		m_goals = 8

		RETURN
	ENDIF

	
	IF m_stage = MANSION2_STAGE_ENTER_HARRIER
	AND m_goals = 1
		CLEAR_AREA -1403.1462 509.5356 17.2266 1.0 FALSE
		SET_CHAR_COORDINATES scplayer -1403.1462 509.5356 17.2266
		SET_CHAR_HEADING scplayer 78.0000

		SET_AIRCRAFT_CARRIER_SAM_SITE OFF
		flagReachedControlRoom = TRUE
		flagDisplayedControlRoomWarning = TRUE
		REMOVE_BLIP blipDestination

		m_goals = 6

		RETURN
	ENDIF

RETURN



// ****************************************
// FRAME COUNTER (Useful if processor scheduling is needed)
Mansion2_Frame_Counter:

	m_frame_num++
	IF m_frame_num > 9
		m_frame_num = 0
	ENDIF

RETURN



// ****************************************
// ADDITIONAL TIMERS
Mansion2_Additional_Timers:

	GET_GAME_TIMER m_this_frame_time
	m_time_diff = m_this_frame_time - m_last_frame_time 
	m_last_frame_time = m_this_frame_time

	m_mission_timer += m_time_diff

RETURN



// =================================================================
// GOSUB: Next_Stage (sets flags and timers for next mission stage)
// =================================================================
Mansion2_Next_Stage:

   	m_stage++
   	m_goals        = 0
   	TIMERA 		   = 0
   	TIMERB		   = 0

RETURN					





// *************************************************************************************************************
//										INTRO CUTSCENE GOSUB
// *************************************************************************************************************

Mansion2_Intro_Cutscene:

	SET_PLAYER_CONTROL player1 OFF

	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	
	SET_AREA_VISIBLE 5


	LOAD_CUTSCENE BHILL2
	WHILE NOT HAS_CUTSCENE_LOADED
		WAIT 0
	ENDWHILE
	 

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

RETURN




// *************************************************************************************************************
// 												INITIALISATION GOSUBS   
// *************************************************************************************************************

Mansion2_Initialisation:

	m_stage = MANSION2_STAGE_INITIALISATION

	// About to play an in-game cutscene, so keep widescreen mode ON and player control OFF
	SWITCH_WIDESCREEN ON 
	SET_PLAYER_CONTROL player1 OFF


	WHILE NOT IS_PLAYER_PLAYING player1
		WAIT 0
	ENDWHILE


	GOSUB Mansion2_Load_All_Models
	GOSUB Mansion2_Load_All_Anims
	GOSUB Mansion2_Load_All_Car_Recordings


	// Clear out the Guards AI
	REPEAT MANSION2_MAX_GUARDS nLoop
		aiGuards[nLoop] = MANSION2_GUARDAI_NOT_INITIALISED
		flagGuardsAllowAlert[nLoop]		= FALSE
		flagGuardsOnAlert[nLoop]		= FALSE
	ENDREPEAT


	// Clear out the Mechanics AI
	REPEAT MANSION2_MAX_MECHANICS nLoop
		aiMechanics[nLoop] = MANSION2_MECHANICAI_NOT_INITIALISED
		flagMechanicsOnAlert[nLoop]		= FALSE
	ENDREPEAT


	// Switch off emergency services and clear the wanted multiplier
	SWITCH_EMERGENCY_SERVICES	OFF
	SET_WANTED_MULTIPLIER		0.0


	// Clear the reason for detection
	nReasonForDetection = MANSION2_DETECTED_UNKNOWN


	// Position the player
	CLEAR_AREA 1248.6813 -807.9187 83.1484 100.0 TRUE
	LOAD_SCENE 1248.6813 -807.9187 83.1484
	SET_CHAR_COORDINATES scplayer 1246.0164 -803.2319 83.1484  
	SET_CHAR_HEADING scplayer 180.0
	SET_CAMERA_BEHIND_PLAYER


	// Set time of day (so that it's not midnight and therefore not dark and rainy)
	SET_TIME_OF_DAY 8 0


	WAIT 2000


	// Fade in
	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_IN


	// Set up Ped relationships
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 PEDTYPE_PLAYER1
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1 PEDTYPE_MISSION2
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION2 PEDTYPE_MISSION1
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION2 PEDTYPE_MISSION2

	
	// Player control
	// NOTE: About to play an in-game cutscene so don't switch on player control


	// Switch on the Aircraft Carrier's SAM sites
	SET_AIRCRAFT_CARRIER_SAM_SITE ON


	// Delete the Permanent Carrier objects
	DELETE_OBJECT g_VB_REAR_DOOR
	DELETE_OBJECT g_VB_SMALL_LIFT
	DELETE_OBJECT g_VB_BIG_LIFT


	// Respawn point
	SET_RESPAWN_POINT_FOR_DURATION_OF_MISSION 1250.6500 -785.9349 89.2412


	GOSUB Mansion2_Next_Stage

RETURN


// ***********************************
// LOAD ALL MODELS

Mansion2_Load_All_Models:

	// CARS
	REQUEST_MODEL WASHING		// Toreno's car
	REQUEST_MODEL SQUALO		// Player's speedboat
	REQUEST_MODEL HYDRA			// Harriers
	REQUEST_MODEL TROPIC		// Spy ships
	REQUEST_MODEL LAUNCH		// Enemy boat
	REQUEST_MODEL FORKLIFT		// Forklift!

	// PEDS
	REQUEST_MODEL ARMY
	REQUEST_MODEL WMYMECH

	// SPECIAL CHARACTERS
	LOAD_SPECIAL_CHARACTER MANSION2_TORENO_SPECIAL_CHARACTER_SLOT TORINO

	// WEAPONS
	REQUEST_MODEL SILENCED
	REQUEST_MODEL KNIFECUR
	REQUEST_MODEL M4

	// OBJECTS
	REQUEST_MODEL CARRIER_LIFT1_SFSE
	REQUEST_MODEL CARRIER_LIFT2_SFSE
	REQUEST_MODEL CARRIER_DOOR_SFSE
	REQUEST_MODEL MISSILE_07_SFXR
	REQUEST_MODEL KMB_KEYPAD

	LOAD_ALL_MODELS_NOW


	// Are Cars Loaded?
	WHILE NOT HAS_MODEL_LOADED	WASHING
	OR NOT HAS_MODEL_LOADED		SQUALO
	OR NOT HAS_MODEL_LOADED		HYDRA
	OR NOT HAS_MODEL_LOADED		TROPIC
	OR NOT HAS_MODEL_LOADED		LAUNCH
	OR NOT HAS_MODEL_LOADED		FORKLIFT
		WAIT 0
	ENDWHILE

	// Are Peds Loaded?
	WHILE NOT HAS_MODEL_LOADED	ARMY
	OR NOT HAS_MODEL_LOADED		WMYMECH
		WAIT 0
	ENDWHILE

	// Are Special Characters Loaded?
	WHILE NOT HAS_SPECIAL_CHARACTER_LOADED MANSION2_TORENO_SPECIAL_CHARACTER_SLOT
		WAIT 0
	ENDWHILE

	// Are weapons loaded?
	WHILE NOT HAS_MODEL_LOADED	SILENCED
	OR NOT HAS_MODEL_LOADED		KNIFECUR
	OR NOT HAS_MODEL_LOADED		M4
		WAIT 0
	ENDWHILE

	// Are the Objects loaded?
	WHILE NOT HAS_MODEL_LOADED	CARRIER_LIFT1_SFSE
	OR NOT HAS_MODEL_LOADED		CARRIER_LIFT2_SFSE
	OR NOT HAS_MODEL_LOADED		CARRIER_DOOR_SFSE
	OR NOT HAS_MODEL_LOADED		MISSILE_07_SFXR
	OR NOT HAS_MODEL_LOADED		KMB_KEYPAD
		WAIT 0
	ENDWHILE

RETURN


// ***********************************
// LOAD ALL ANIMS

Mansion2_Load_All_Anims:

	REQUEST_ANIMATION CASINO				// for player deactivating SAMs


	// Are anims loaded?
	WHILE NOT HAS_ANIMATION_LOADED	CASINO
		WAIT 0
	ENDWHILE

RETURN


// ***********************************
// LOAD ALL CAR RECORDINGS

Mansion2_Load_All_Car_Recordings:

	REQUEST_CAR_RECORDING	MANSION2_CAR_RECORDING_TORENO_CAR
	REQUEST_CAR_RECORDING	MANSION2_CAR_RECORDING_HARRIER_TAKEOFF
	REQUEST_CAR_RECORDING	MANSION2_CAR_RECORDING_HARRIER_LANDING

	WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED MANSION2_CAR_RECORDING_TORENO_CAR
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED MANSION2_CAR_RECORDING_HARRIER_TAKEOFF
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED MANSION2_CAR_RECORDING_HARRIER_LANDING
		WAIT 0
	ENDWHILE

RETURN





// *************************************************************************************************************
//										END OF LEVEL GOSUBS - fail, pass, cleanup  
// *************************************************************************************************************

// FAIL
Mansion2_Mission_Failed:

	PRINT_BIG M_FAIL 5000 1

	// Display reason for failure (if there is a reason)
	CLEAR_SMALL_PRINTS

	SWITCH m_fail_reason
		CASE MANSION2_FAILED_TORENO_CAR_DESTROYED
			PRINT_NOW MAN2_80 5000 1
			BREAK
		CASE MANSION2_FAILED_TORENO_DEAD
			PRINT_NOW MAN2_81 5000 1
			BREAK
		CASE MANSION2_FAILED_NO_PLANES_LEFT
			PRINT_NOW MAN2_82 5000 1
			BREAK
		CASE MANSION2_FAILED_HARRIER_DEAD
			PRINT_NOW MAN2_83 5000 1
			BREAK
		CASE MANSION2_FAILED_NOT_IN_CARRIER
			PRINT_NOW MAN2_84 5000 1
			BREAK
		CASE MANSION2_FAILED_LEFT_CARRIER
			PRINT_NOW MAN2_ZZ 5000 1
			BREAK
	ENDSWITCH


	// Get rid of the Player's mission specific weapons
	IF IS_PLAYER_PLAYING player1
		REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_PISTOL_SILENCED
		REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_KNIFE
	ENDIF

RETURN


// PASS
Mansion2_Mission_Passed:

	// This mission gives $50,000 reward
	PRINT_WITH_NUMBER_BIG M_PASS 50000 5000 1
	ADD_SCORE player1 50000
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1
	REGISTER_MISSION_PASSED MAN_2
	PLAYER_MADE_PROGRESS 1

	SET_INT_STAT PASSED_MANSION2 1

	PRINT_NOW MAN2_34 7000 1

	flag_mansion_mission_counter ++

	// Create powerful / exotic weapons in Toreno's mansion
	CREATE_PICKUP ROCKETLA	PICKUP_ON_STREET_SLOW -686.0000 934.0000 13.5000 gun_toreno1		// Rocket Launcher
	CREATE_PICKUP HEATSEEK	PICKUP_ON_STREET_SLOW -690.0000 934.0000 13.5000 gun_toreno2		// Stinger-type Rocket Launcher
	CREATE_PICKUP MINIGUN	PICKUP_ON_STREET_SLOW -690.0000 939.0000 13.5000 gun_toreno3		// Minigun
	CREATE_PICKUP FLAME		PICKUP_ON_STREET_SLOW -686.0000 939.0000 13.5000 gun_toreno4		// Flamethrower

	// Switch on Harrier car generators
	SWITCH_CAR_GENERATOR gen_car_flying[8] 101
	SWITCH_CAR_GENERATOR gen_car_flying[9] 101
	SWITCH_CAR_GENERATOR area51_gen[2] 101
	SWITCH_CAR_GENERATOR area51_gen[3] 101

RETURN


// CLEANUP
Mansion2_Mission_Cleanup:

	// Entity Task Removal, etc.
	// -------------------------


	// Audio Clearup
	// -------------

	IF flagCarrierBackDoorAudioPlaying = TRUE
   		REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierBackDoor SOUND_VERTICAL_BIRD_LIFT_STOP
   		flagCarrierBackDoorAudioPlaying = FALSE
	ENDIF

	IF flagCarrierSmallLiftAudioPlaying = TRUE
   		REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierSmallLift SOUND_VERTICAL_BIRD_LIFT_STOP
   		flagCarrierSmallLiftAudioPlaying = FALSE
	ENDIF

	IF flagCarrierBigLiftAudioPlaying = TRUE
   		REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectCarrierBigLift SOUND_VERTICAL_BIRD_LIFT_STOP
   		flagCarrierBigLiftAudioPlaying = FALSE
	ENDIF

	IF flagCarrierOnAlertAudioPlaying = TRUE
		REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_VERTICAL_BIRD_ALARM_STOP
		flagCarrierOnAlertAudioPlaying = FALSE
	ENDIF


	// Entity Clearup
	// --------------

	// NOTE: The Player Plane and the Enemy Planes will be cleared up by the 'Planes' loop
	//			because the Player and Enemy planes are just copies of these variables
	MARK_CAR_AS_NO_LONGER_NEEDED	carToreno
	MARK_CAR_AS_NO_LONGER_NEEDED	carSpeedboat
	MARK_CAR_AS_NO_LONGER_NEEDED	carEnemyBoat
	MARK_CHAR_AS_NO_LONGER_NEEDED	charToreno
	MARK_OBJECT_AS_NO_LONGER_NEEDED	objectCarrierBigLift
	MARK_OBJECT_AS_NO_LONGER_NEEDED	objectCarrierSmallLift
	MARK_OBJECT_AS_NO_LONGER_NEEDED	objectCarrierBackDoor
	MARK_OBJECT_AS_NO_LONGER_NEEDED objectMissile

	REPEAT MANSION2_MAX_PLANES nLoop
		MARK_CAR_AS_NO_LONGER_NEEDED carPlanes[nLoop]
	ENDREPEAT

	REPEAT MANSION2_MAX_ENEMY_PLANES nLoop
		MARK_CAR_AS_NO_LONGER_NEEDED carEnemyPlanes[nLoop]
	ENDREPEAT

	REPEAT MANSION2_MAX_SPY_SHIPS nLoop
		MARK_CAR_AS_NO_LONGER_NEEDED carSpyShips[nLoop]
	ENDREPEAT

	REPEAT MANSION2_MAX_FORKLIFTS nLoop
		MARK_CAR_AS_NO_LONGER_NEEDED carForklifts[nLoop]
	ENDREPEAT

	REPEAT MANSION2_MAX_GUARDS nLoop
		MARK_CHAR_AS_NO_LONGER_NEEDED charGuards[nLoop]
	ENDREPEAT

	
	// Blip Clearup
	// ------------

	REMOVE_BLIP	blipSpeedboat
	REMOVE_BLIP blipDestination
	REMOVE_BLIP blipPlayerPlane

	REPEAT MANSION2_MAX_ENEMY_PLANES nLoop
		REMOVE_BLIP blipEnemyPlanes[nLoop]
	ENDREPEAT

	REPEAT MANSION2_MAX_SPY_SHIPS nLoop
		REMOVE_BLIP blipSpyShips[nLoop]
	ENDREPEAT

	REPEAT MANSION2_MAX_GUARDS nLoop
		REMOVE_BLIP blipGuards[nLoop]
	ENDREPEAT



	// Help Text Clearup
	// -----------------
	CLEAR_HELP



	// Counters and Timers Clearup
	// ---------------------------


	// Animation Clearup
	// -----------------

	REMOVE_ANIMATION CASINO


	// Models Clearup
	// --------------

	MARK_MODEL_AS_NO_LONGER_NEEDED	WASHING
	MARK_MODEL_AS_NO_LONGER_NEEDED	SQUALO
	MARK_MODEL_AS_NO_LONGER_NEEDED	HYDRA
	MARK_MODEL_AS_NO_LONGER_NEEDED	TROPIC
	MARK_MODEL_AS_NO_LONGER_NEEDED	LAUNCH
	MARK_MODEL_AS_NO_LONGER_NEEDED	FORKLIFT
	MARK_MODEL_AS_NO_LONGER_NEEDED	ARMY
	MARK_MODEL_AS_NO_LONGER_NEEDED	WMYMECH
	MARK_MODEL_AS_NO_LONGER_NEEDED	SILENCED
	MARK_MODEL_AS_NO_LONGER_NEEDED	KNIFECUR
	MARK_MODEL_AS_NO_LONGER_NEEDED	M4
	MARK_MODEL_AS_NO_LONGER_NEEDED	CARRIER_LIFT1_SFSE
	MARK_MODEL_AS_NO_LONGER_NEEDED	CARRIER_LIFT2_SFSE
	MARK_MODEL_AS_NO_LONGER_NEEDED	CARRIER_DOOR_SFSE
	MARK_MODEL_AS_NO_LONGER_NEEDED	MISSILE_07_SFXR
	MARK_MODEL_AS_NO_LONGER_NEEDED	KMB_KEYPAD

	// Toreno's special weapons
	MARK_MODEL_AS_NO_LONGER_NEEDED ROCKETLA
	MARK_MODEL_AS_NO_LONGER_NEEDED HEATSEEK
	MARK_MODEL_AS_NO_LONGER_NEEDED MINIGUN
	MARK_MODEL_AS_NO_LONGER_NEEDED FLAME

	UNLOAD_SPECIAL_CHARACTER		MANSION2_TORENO_SPECIAL_CHARACTER_SLOT


	// Car Recording Clearup
	// ---------------------

	REMOVE_CAR_RECORDING MANSION2_CAR_RECORDING_TORENO_CAR
	REMOVE_CAR_RECORDING MANSION2_CAR_RECORDING_HARRIER_TAKEOFF
	REMOVE_CAR_RECORDING MANSION2_CAR_RECORDING_HARRIER_LANDING


	// === RESTORE ENVIRONMENT SETTINGS ===
	// ------------------------------------

	SET_PED_DENSITY_MULTIPLIER	1.0
	SET_CAR_DENSITY_MULTIPLIER	1.0
	SET_WANTED_MULTIPLIER		1.0
	SWITCH_EMERGENCY_SERVICES	ON
	SWITCH_RANDOM_TRAINS		ON
	SWITCH_AMBIENT_PLANES		ON
	RELEASE_WEATHER


	// Make sure the player gets a 6* rating again whenever he sets foot on the aircraft carrier
	SET_DISABLE_MILITARY_ZONES FALSE


	// Switch off the Aircraft Carrier's SAM sites
	SET_AIRCRAFT_CARRIER_SAM_SITE ON


	// Don't force interior lighting again
	FORCE_INTERIOR_LIGHTING_FOR_PLAYER player1 FALSE


	// Make sure the player can talk again
	IF NOT IS_CHAR_DEAD scplayer
		ENABLE_CHAR_SPEECH scplayer
	ENDIF


	// Restore switched off road
	// -------------------------

//	SWITCH_ROADS_BACK_TO_ORIGINAL xlo ylo zlo xhi yhi zhi


	// Make sure the mobile phone doesn't ring immediately after a mission
	// -------------------------------------------------------------------

	GET_GAME_TIMER timer_mobile_start


	// Re-create the permanent objects
	// ...Rear Door for Aircraft Carrier
	CREATE_OBJECT_NO_OFFSET CARRIER_DOOR_SFSE -1465.797 501.289 1.145 g_VB_REAR_DOOR
	SET_OBJECT_DYNAMIC g_VB_REAR_DOOR FALSE
	SET_OBJECT_COLLISION_DAMAGE_EFFECT g_VB_REAR_DOOR FALSE
	SET_OBJECT_PROOFS g_VB_REAR_DOOR TRUE TRUE TRUE TRUE TRUE
	DONT_REMOVE_OBJECT g_VB_REAR_DOOR

	// ...Small Lift for Aircraft Carrier
	CREATE_OBJECT_NO_OFFSET CARRIER_LIFT2_SFSE -1414.453 516.453 16.688 g_VB_SMALL_LIFT
	SET_OBJECT_DYNAMIC g_VB_SMALL_LIFT FALSE
	SET_OBJECT_COLLISION_DAMAGE_EFFECT g_VB_SMALL_LIFT FALSE
	SET_OBJECT_PROOFS g_VB_SMALL_LIFT TRUE TRUE TRUE TRUE TRUE
	DONT_REMOVE_OBJECT g_VB_SMALL_LIFT

	// ...Big Lift for Aircraft Carrier
	CREATE_OBJECT_NO_OFFSET CARRIER_LIFT1_SFSE -1456.719 501.297 16.953 g_VB_BIG_LIFT
	SET_OBJECT_DYNAMIC g_VB_BIG_LIFT FALSE
	SET_OBJECT_COLLISION_DAMAGE_EFFECT g_VB_BIG_LIFT FALSE
	SET_OBJECT_PROOFS g_VB_BIG_LIFT TRUE TRUE TRUE TRUE TRUE
	DONT_REMOVE_OBJECT g_VB_BIG_LIFT


	// Housekeeping
	// ------------

	flag_player_on_mission = 0
	MISSION_HAS_FINISHED

RETURN
	 

}