MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
//
//			   MISSION NAME : Desert6
//				     AUTHOR : Keith
//		        DESCRIPTION : Fly an aircraft under the radar to a dropoff point
//
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

{
// *************************************************************************************************************
//								GENERAL INITIALISATION - Every level requires this
// *************************************************************************************************************


SCRIPT_NAME Desert6


// Global Variables
VAR_INT		g_Desert6_visibilityKM
VAR_INT		g_Desert6_failureKM
VAR_INT		g_Desert6_timerKM



// Mission Start stuff
GOSUB Desert6_Mission_Start

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB Desert6_Mission_Failed
ENDIF

GOSUB Desert6_mission_cleanup

MISSION_END


// Global Setup
Desert6_Mission_Start:

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
VAR_INT 	Desert6_view_debug[8]	// GLOBAL (for output)
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
// cutscene variables
LVAR_INT	flagCutscenePlaying
LVAR_INT	flagCleaningUpSkippedCutscene
LVAR_INT	flagSkipCutscene
// Clear cutscene variables
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
CONST_INT	DESERT6_FAILED_UNKNOWN							0
CONST_INT	DESERT6_FAILED_AIRCRAFT_DEAD					1
CONST_INT	DESERT6_FAILED_NOT_IN_AIRCRAFT					2
CONST_INT	DESERT6_FAILED_OUT_OF_TIME						3
// Heights
CONST_FLOAT	DESERT6_RADAR_VISIBLE_HEIGHT_OVER_m				41.0
CONST_FLOAT DESERT6_RADAR_VISIBLE_HEIGHT_UNDER_m			40.0
// Radar Visibility Times
CONST_INT	DESERT6_RADAR_VISIBLE_TIME_INCREASE_msec		80
CONST_INT	DESERT6_RADAR_VISIBLE_TIME_DECREASE_msec		40
// First 'above radar' message
CONST_INT	DESERT6_RADAR_VISIBLE_FIRST_DISPLAY_pc			40
// Failure Time
CONST_INT	DESERT6_FAILURE_TIME_OUT_OF_AIRCRAFT_sec		30
// Mission Time
CONST_INT	DESERT6_MISSION_TIME_min						10
// Enemies
CONST_INT	DESERT6_MAX_ENEMIES								2
CONST_INT	DESERT6_TIME_TILL_FIRST_ENEMY_sec  				3
CONST_INT	DESERT6_TIME_TILL_NEW_ENEMY_sec					30
CONST_INT	DESERT6_MIN_ATTACK_TIME_sec						10
// Destination
CONST_FLOAT	DESERT6_CHECKPOINT_SIZE							25.0
// Message Display
CONST_FLOAT DESERT6_MESSAGE_DISPLAY_DISTANCE_m				500.0
// Drop Objects
CONST_INT	DESERT6_MAX_DROP_OBJECTS						4
// Enemy Aircraft Stuck
CONST_INT	DESERT6_ENEMY_AIRCRAFT_STUCK_TIME_msec			5000
// ...General
CONST_INT	DESERT6_PERSIST									-2

// Speech
CONST_INT	DESERT6_MAX_CONVERSATION_LINES					11
CONST_INT	DESERT6_DELAY_BEFORE_NEXT_CONVERSATION_msec		120
// ...audio slot allocations
CONST_INT	DESERT6_TOTAL_AUDIO_SLOTS						2	// 3 available: generally 1 and 2 for speech; 3 for SFX
CONST_INT	DESERT6_AUDIO_SLOT_ARRAY_SIZE	 				3	// DESERT6_TOTAL_AUDIO_SLOTS+1 (slots numbered 1,2,3: so array pos0 wasted)
CONST_INT	DESERT6_FIRST_SPEECH_SLOT						1
CONST_INT	DESERT6_LAST_SPEECH_SLOT  						2
CONST_INT	DESERT6_SFX_SLOT								3	// NOTE: Not using an engine similar to speech for this because only one SFX
// ...audio slot status
CONST_INT	DESERT6_AUDIO_SLOT_FREE							0
CONST_INT	DESERT6_AUDIO_SLOT_LOADING						1
CONST_INT	DESERT6_AUDIO_SLOT_LOADED						2
CONST_INT	DESERT6_AUDIO_SLOT_PLAYING						3
// ...conversation status
CONST_INT	DESERT6_CONVERSATION_STATUS_NONE   				0
CONST_INT	DESERT6_CONVERSATION_STATUS_PREPARED			1
CONST_INT	DESERT6_CONVERSATION_STATUS_PLAYING				2
CONST_INT	DESERT6_CONVERSATION_STATUS_INTERRUPTED			3
CONST_INT	DESERT6_CONVERSATION_STATUS_FINISHED			4
// ...speech conversations
CONST_INT	DESERT6_CONVERSATION_NONE						0
CONST_INT	DESERT6_CONVERSATION_INSTRUCTIONS				1
CONST_INT	DESERT6_CONVERSATION_TOO_HIGH					2
CONST_INT	DESERT6_CONVERSATION_SPOTTED					3
CONST_INT	DESERT6_CONVERSATION_SUCCESS					4



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
LVAR_INT		nAudioSlotStatus[DESERT6_AUDIO_SLOT_ARRAY_SIZE]
LVAR_INT		nConversationSequenceSpeechID[DESERT6_MAX_CONVERSATION_LINES]
LVAR_TEXT_LABEL	tlConversationSequenceSubtitle[DESERT6_MAX_CONVERSATION_LINES]
// ...clear audio variables
nSpeechCurrentSlot					= DESERT6_FIRST_SPEECH_SLOT
nConversationStatus					= DESERT6_CONVERSATION_STATUS_NONE
nCurrentConversationID				= DESERT6_CONVERSATION_NONE
nRequiredConversationID				= DESERT6_CONVERSATION_NONE
nCurrentConversationLine			= -1
nCurrentMaxConversationLines		= 0
nNextPreloadConversationLine		= 0
timerDelayBeforeNextConversation	= 0
bitsConversationsPlayed				= DESERT6_CONVERSATION_NONE

REPEAT DESERT6_AUDIO_SLOT_ARRAY_SIZE nLoop
	nAudioSlotStatus[nLoop] = DESERT6_AUDIO_SLOT_FREE
ENDREPEAT



// Mission Specific Variables
// Integer Variables
// ...chars 				(char)
LVAR_INT	charEnemy[DESERT6_MAX_ENEMIES]
// ...cars 					(car)
LVAR_INT	carPlayerAircraft
LVAR_INT	carEnemyAircraft[DESERT6_MAX_ENEMIES]
// ...objects 				(object)
LVAR_INT	objectDrop[DESERT6_MAX_DROP_OBJECTS]
// ...blips 				(blip)
LVAR_INT	blipDestination
LVAR_INT	blipPlayerAircraft
LVAR_INT	blipEnemy[DESERT6_MAX_ENEMIES]
// ...pickups 				(pickup)
// ...fx systems 			(fx)
// ...decision makers		(dm)
LVAR_INT	dmEmpty
// ...AI Status				(ai)
// ...general status		(status)
// ...Timers				(timer)
LVAR_INT	timerText
LVAR_INT	timerVisibility
LVAR_INT	timerFailure
LVAR_INT	timerNewEnemy
LVAR_INT	timerMinAttack
LVAR_INT	timerAboveRadarLimit
LVAR_INT	timerBelowRadarLimit
LVAR_INT	timerCoronaMessage
LVAR_INT	timerLandMessage
LVAR_INT	timerDropObject[DESERT6_MAX_DROP_OBJECTS]
LVAR_INT	timerEnemyAircraftStuck[DESERT6_MAX_ENEMIES]
// ...Counters				(count)
// ...Checkpoints			(checkpoint)
LVAR_INT	checkpointDestination
// ...mission specific		(n)


// Text Label Variables


// Float Variables
// ...area variables 		(xlo, ylo, zlo, xhi, yhi, zhi)
LVAR_FLOAT	xloLand		yloLand		zloLand		xhiLand		yhiLand		zhiLand
// ...position variables	(xpos, ypos, zpos)
LVAR_FLOAT	xposDestination		yposDestination		zposDestination
// ...mission specific		(f)
LVAR_FLOAT	fInitialHeight


// Boolean Variables
// ...flags					(flag)
LVAR_INT	flagRadarVisible
LVAR_INT	flagFailureConditions
LVAR_INT	flagAttackPlayer
LVAR_INT	flagSendAnEnemy
LVAR_INT	flagAboveRadarMessageDisplayed
LVAR_INT	flagCoronaMessageDisplayed
LVAR_INT	flagCoronaMessageBeingDisplayed
LVAR_INT	flagLandMessageDisplayed
LVAR_INT	flagLandMessageBeingDisplayed
LVAR_INT	flagAllDropObjectsDropped
LVAR_INT	flagDropObjectDropped[DESERT6_MAX_DROP_OBJECTS]
LVAR_INT	flagEnemyAircraftStuck[DESERT6_MAX_ENEMIES]
LVAR_INT	flagEnemyAircraftExists
LVAR_INT	flagArea51MessageDisplayed
LVAR_INT	flagPlayedInstructionsSpeech
LVAR_INT	flagFirstEnemyCreated
LVAR_INT	flagDisplayedVisibilityBarHelp
// ...exists flags			(exists)
LVAR_INT	existsEnemy[DESERT6_MAX_ENEMIES]
// Clear Exists Flags
REPEAT DESERT6_MAX_ENEMIES nLoop
	existsEnemy[nLoop] = FALSE
ENDREPEAT
// ...loaded flags			<loaded>
// Clear Loaded Flags


// Clear timers


// Clear important flags
flagAboveRadarMessageDisplayed	= FALSE
flagCoronaMessageDisplayed		= FALSE
flagCoronaMessageBeingDisplayed	= FALSE
flagLandMessageDisplayed		= FALSE
flagLandMessageBeingDisplayed	= FALSE
flagEnemyAircraftExists			= FALSE
flagArea51MessageDisplayed		= FALSE
flagPlayedInstructionsSpeech	= FALSE
flagFirstEnemyCreated			= FALSE
flagDisplayedVisibilityBarHelp	= FALSE



// ***** FAKE ENTITY CREATION TO FOOL THE COMPILER *****
// The compiler just needs to verify there is a CREATE_ before usage
IF m_stage = -99
	
	WRITE_DEBUG SHOULD_NEVER_BE_IN_FAKE_ENTITY_CREATION

	// Cars
	CREATE_CAR RUSTLER 0.0 0.0 0.0 carPlayerAircraft
	REPEAT DESERT6_MAX_ENEMIES nLoop
		CREATE_CAR HYDRA 0.0 0.0 0.0 carEnemyAircraft[nLoop]
	ENDREPEAT

	// Peds
	REPEAT DESERT6_MAX_ENEMIES nLoop
		CREATE_CHAR PEDTYPE_CIVMALE HMORI 0.0 0.0 0.0 charEnemy[nLoop]
	ENDREPEAT

	// Objects
	REPEAT DESERT6_MAX_DROP_OBJECTS nLoop
		CREATE_OBJECT AMMO_CAPSULE 0.0 0.0 0.0 objectDrop[nLoop]
	ENDREPEAT

	// Blips
	ADD_BLIP_FOR_CAR carPlayerAircraft blipPlayerAircraft
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 blipDestination
	REPEAT DESERT6_MAX_ENEMIES nLoop
		ADD_BLIP_FOR_CAR carEnemyAircraft[nLoop] blipEnemy[nLoop]
	ENDREPEAT

ENDIF



// Mission Text
LOAD_MISSION_TEXT DSERT6


// Intro Cutscene
GOSUB Desert6_Intro_Cutscene


// Load Char Mission Decision Makers
// NOTE: Do it before the Initialisation because they make the game pause. Done here, the pause is hidden by a fade.
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY dmEmpty


// Mission Initialisation
GOSUB Desert6_Initialisation



// *************************************************************************************************************
//								                 MISSION LOOP
// *************************************************************************************************************
mission_loop_Desert6:

	WAIT 0


	// Special shortcut for Craig F to test all missions in order
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		m_passed = 1
		GOTO end_of_main_loop_Desert6
	ENDIF



	// Debug Stuff
	GOSUB Desert6_Debug_Tools
	GOSUB Desert6_Debug_Shortcuts

	IF m_quit = 1
	OR m_pause = 1
		GOTO end_of_main_loop_Desert6
	ENDIF


	// Housekeeping
	GOSUB Desert6_Frame_Counter
	GOSUB Desert6_Additional_Timers


	// Special conditions
	IF IS_CHAR_DEAD scplayer
		m_failed = 1
		GOTO end_of_main_loop_Desert6
	ENDIF		


	// Mission Stage processing
	// *** INITIALISATION NOW TAKES PLACE BEFORE THE MAIN LOOP ***
	IF m_stage = 0
		WRITE_DEBUG STAGE_SHOULD_NEVER_BE_0_IN_MAIN_LOOP
	ENDIF


	// ...Stage 1: Get into the helicopter
	IF m_stage = 1
		GOSUB Desert6_Stage_Enter_Aircraft
	ENDIF 


	// ...Stage 2: Fly to destination
	IF m_stage = 2
		GOSUB Desert6_Stage_Fly_To_Destination
	ENDIF


	// ...Stage 3: Return to landing strip
	IF m_stage = 3
		GOSUB Desert6_Stage_Return_To_Landing_Strip
	ENDIF


	// ...final stage
	IF m_stage = 4
		m_passed = 1
	ENDIF


	// Continuous update methods and event checking
	// --------------------------------------------

	GOSUB Desert6_Update_Speech







// End of Main Loop
// ***** DON'T CHANGE *****
end_of_main_loop_Desert6:

	IF m_quit = 0
		IF m_failed = 0
			IF m_passed = 0																 
				// Restart main loop
				GOTO mission_loop_Desert6 
			ELSE
				// Mission passed
				GOSUB mission_passed_Desert6
				RETURN
			ENDIF
		ELSE
			// Mission failed
			GOSUB Desert6_Mission_Failed
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
// STAGE 1: Enter Aircraft

Desert6_Stage_Enter_Aircraft:

   	// Initialisation
	// --------------

   	IF m_goals = 0
		// Instructions: Get in the aircraft
		PRINT_NOW DES6_00 7000 1

		// Create the drop objects under the plane
		GOSUB Desert6_Create_Drop_Objects

		// Prepare the instructional speech
		nRequiredConversationID = DESERT6_CONVERSATION_INSTRUCTIONS
		GOSUB Desert6_Conversation_Command_Prepare

		// Switch off the radio for the next vehicle (which should be the plane)
		SET_RADIO_CHANNEL RS_OFF

		m_goals++

	ENDIF


	// Failure Conditions
	// ------------------

	// ...is plane dead?
	IF IS_CAR_DEAD carPlayerAircraft
		m_failed = TRUE
		m_fail_reason = DESERT6_FAILED_AIRCRAFT_DEAD
		RETURN
	ENDIF


	// Subgoals
	// --------

	// Has player entered aircraft?
	IF m_goals = 1
		IF IS_CHAR_IN_CAR scplayer carPlayerAircraft
			REMOVE_BLIP blipPlayerAircraft

			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Desert6_next_stage
	ENDIF

RETURN


// ****************************************
// STAGE 2: Fly to Destination

Desert6_Stage_Fly_To_Destination:

   	// Initialisation
	// --------------

   	IF m_goals = 0
		// Instructions: Fly the aircraft to the destination
//		PRINT_NOW DES6_01 5000 1
//		GOSUB Desert6_Conversation_Command_Play
//		timerText = m_mission_timer + 5000
		flagPlayedInstructionsSpeech = FALSE
		timerText = m_mission_timer + 2000

		// Setup Destination and allowable flight height
		xposDestination = -2135.5896
		yposDestination = -2488.4541
		zposDestination = 55.8950

		// Switch off the roads the taxi will travel on
//		SWITCH_ROADS_OFF -2319.8967 -2719.9604 28.4250 -2197.9482 -2482.0303 44.5080

		// Add blip for Destination
		ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination

		// Create checkpoint at Destination
		fTempFloat = yposDestination + 1.0
		CREATE_CHECKPOINT CHECKPOINT_TORUS xposDestination yposDestination zposDestination xposDestination fTempFloat zposDestination DESERT6_CHECKPOINT_SIZE checkpointDestination

		// Visibility
		g_Desert6_visibilityKM		= 0
		timerVisibility				= 0
		flagRadarVisible			= FALSE
		flagAttackPlayer			= FALSE
		flagSendAnEnemy				= FALSE
		flagAllDropObjectsDropped	= FALSE

		// Mission Time (convert to milliseconds)
		g_Desert6_timerKM = DESERT6_MISSION_TIME_min * 60000
		DISPLAY_ONSCREEN_TIMER_WITH_STRING g_Desert6_timerKM TIMER_DOWN DES6_51

		// Store the player's height in the plane on ground
		GET_CHAR_HEIGHT_ABOVE_GROUND scplayer fInitialHeight

		// Enemy aircraft controls
		REPEAT DESERT6_MAX_ENEMIES nLoop
			flagEnemyAircraftStuck[nLoop] = FALSE
			timerEnemyAircraftStuck[nLoop] = 0
		ENDREPEAT

		// Load the 'above radar level' warning beep
		LOAD_MISSION_AUDIO DESERT6_SFX_SLOT SOUND_RADAR_LEVEL_WARNING

		// Make sure the player doesn't get a 6* rating whenever he sets foot on the aircraft carrier
		SET_DISABLE_MILITARY_ZONES TRUE

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------

	IF IS_CAR_DEAD carPlayerAircraft
		// Delete the capsules attached to the plane
		REPEAT DESERT6_MAX_DROP_OBJECTS nLoop
			IF DOES_OBJECT_EXIST objectDrop[nLoop]
				DELETE_OBJECT objectDrop[nLoop]
			ENDIF
		ENDREPEAT

		m_failed = TRUE
		m_fail_reason = DESERT6_FAILED_AIRCRAFT_DEAD
		RETURN
	ENDIF


	// Player out of aircraft?
	GOSUB Desert6_Update_Failure_Conditions
	IF m_failed = TRUE
		RETURN
	ENDIF


	// Out of time
	IF g_Desert6_timerKM = 0
		m_failed = TRUE
		m_fail_reason = DESERT6_FAILED_OUT_OF_TIME
		RETURN
	ENDIF



	// Subgoals
	// --------

	// Display the 'radar visibility' text
	IF m_goals = 1
		IF timerText < m_mission_timer
			// ...display 'stay low' message when the plane has just taken off
			GET_CHAR_HEIGHT_ABOVE_GROUND scplayer fTempFloat
			fTempFloat = fTempFloat - fInitialHeight
			IF fTempFloat > 2.0
				// Instructions: Stay close to the ground
//				PRINT_NOW DES6_02 8000 1

				DISPLAY_ONSCREEN_COUNTER_WITH_STRING g_Desert6_visibilityKM COUNTER_DISPLAY_BAR DES6_50

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Has the player arrived at the destination
	IF m_goals = 2
		xposTemp = xposDestination - DESERT6_CHECKPOINT_SIZE
		yposTemp = yposDestination - 2.0
		zposTemp = zposDestination - DESERT6_CHECKPOINT_SIZE
		xposTemp2 = xposDestination + DESERT6_CHECKPOINT_SIZE
		yposTemp2 = yposDestination + 2.0
		zposTemp2 = zposDestination + DESERT6_CHECKPOINT_SIZE
		IF IS_CHAR_IN_AREA_3D scplayer xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 FALSE
		AND IS_CHAR_IN_CAR scplayer carPlayerAircraft
			REMOVE_BLIP blipDestination
			DELETE_CHECKPOINT checkpointDestination

			GOSUB Desert6_Drop_Objects

			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	// Radar Visibility
	GOSUB Desert6_Update_Radar_Visibility


	// Is the enemy dead?
	GOSUB Desert6_Enemy_Status


	// Time to send another attacker?
	IF flagAttackPlayer = TRUE
		IF timerNewEnemy < m_mission_timer
			flagSendAnEnemy = TRUE
		ENDIF
	ENDIF


	// Send another enemy to attack player??
	IF flagSendAnEnemy = TRUE
		GOSUB Desert6_Enemy_Attack_Player

		// ...time till another enemy is generated
		nTempInt = DESERT6_TIME_TILL_NEW_ENEMY_sec * 1000
		timerNewEnemy = m_mission_timer + nTempInt
	ENDIF


	// Should enemies be removed?
	GOSUB Desert6_Remove_Enemy


	// Should the Corona message be displayed?
	IF flagCoronaMessageDisplayed = FALSE
		GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
		GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposDestination yposDestination zposDestination fTempDistance
		IF fTempDistance < DESERT6_MESSAGE_DISPLAY_DISTANCE_m
			IF IS_POINT_ON_SCREEN xposDestination yposDestination zposDestination 5.0
				// Info: Fly through corona
				PRINT_NOW DES6_09 7000 1
				flagCoronaMessageDisplayed = TRUE
				flagCoronaMessageBeingDisplayed = TRUE
				timerCoronaMessage = m_mission_timer + 7000
			ENDIF
		ENDIF
	ENDIF

	IF flagCoronaMessageBeingDisplayed = TRUE
		IF timerCoronaMessage < m_mission_timer
			flagCoronaMessageBeingDisplayed = FALSE
		ENDIF
	ENDIF


	// Prepare 'above radar' conversation?
	IF NOT IS_BIT_SET bitsConversationsPlayed DESERT6_CONVERSATION_TOO_HIGH
		IF IS_BIT_SET bitsConversationsPlayed DESERT6_CONVERSATION_INSTRUCTIONS
		AND nCurrentConversationID = DESERT6_CONVERSATION_NONE
			// ...prepare the conversation
			nRequiredConversationID = DESERT6_CONVERSATION_TOO_HIGH
			GOSUB Desert6_Conversation_Command_Prepare
		ELSE
			// ...cancel the previous conversation?
			IF nCurrentConversationID = DESERT6_CONVERSATION_INSTRUCTIONS
			AND g_Desert6_visibilityKM >= DESERT6_RADAR_VISIBLE_FIRST_DISPLAY_pc
				GOSUB Desert6_Conversation_Command_Cancel
			ENDIF
		ENDIF
	ENDIF

	// Start 'above radar' conversation?
	IF flagRadarVisible = TRUE
	AND flagAttackPlayer = FALSE
	AND flagCoronaMessageBeingDisplayed = FALSE
		IF NOT IS_BIT_SET bitsConversationsPlayed DESERT6_CONVERSATION_TOO_HIGH
		AND nCurrentConversationID = DESERT6_CONVERSATION_TOO_HIGH
		AND nConversationStatus = DESERT6_CONVERSATION_STATUS_PREPARED
		AND g_Desert6_visibilityKM >= DESERT6_RADAR_VISIBLE_FIRST_DISPLAY_pc
			GOSUB Desert6_Conversation_Command_Play
		ENDIF
	ENDIF


	// Prepare 'spotted' conversation?
	IF NOT IS_BIT_SET bitsConversationsPlayed DESERT6_CONVERSATION_SPOTTED
	AND IS_BIT_SET bitsConversationsPlayed DESERT6_CONVERSATION_TOO_HIGH
	AND nCurrentConversationID = DESERT6_CONVERSATION_NONE
		nRequiredConversationID = DESERT6_CONVERSATION_SPOTTED
		GOSUB Desert6_Conversation_Command_Prepare
	ENDIF

	// Start 'spotted' conversation?
	IF flagAttackPlayer = TRUE
	AND NOT IS_BIT_SET bitsConversationsPlayed DESERT6_CONVERSATION_SPOTTED
	AND nCurrentConversationID = DESERT6_CONVERSATION_SPOTTED
	AND nConversationStatus = DESERT6_CONVERSATION_STATUS_PREPARED
		GOSUB Desert6_Conversation_Command_Play
	ENDIF

	// Display 'lose the authorities' text?
	IF flagAboveRadarMessageDisplayed = FALSE
		IF IS_BIT_SET bitsConversationsPlayed DESERT6_CONVERSATION_SPOTTED
		AND NOT nConversationStatus = DESERT6_CONVERSATION_STATUS_PLAYING
			// Only display the message once
			PRINT_NOW DES6_06 7000 1
			flagAboveRadarMessageDisplayed = TRUE
		ENDIF
	ENDIF


	// Check if the area51 message should be displayed
	IF flagArea51MessageDisplayed = FALSE
		IF IS_CHAR_IN_AREA_2D scplayer -67.0004 1646.8861 423.2980 2132.1875 FALSE
			IF IS_CHAR_IN_ANY_PLANE scplayer
			OR IS_CHAR_IN_ANY_HELI scplayer
				IF NOT IS_MESSAGE_BEING_DISPLAYED
					PRINT_NOW AREA51 7000 1
					flagArea51MessageDisplayed = TRUE
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Instructions speech
	IF flagPlayedInstructionsSpeech = FALSE
		IF timerText < m_mission_timer
			GOSUB Desert6_Conversation_Command_Play
			timerText = m_mission_timer + 5000
			flagPlayedInstructionsSpeech = TRUE
		ENDIF
	ENDIF


	// Display visibility bar help
	IF flagDisplayedVisibilityBarHelp = FALSE
		IF IS_BIT_SET bitsConversationsPlayed DESERT6_CONVERSATION_INSTRUCTIONS
			PRINT_HELP DES6_12
			flagDisplayedVisibilityBarHelp = TRUE
		ENDIF
	ENDIF


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Desert6_next_stage
	ENDIF

RETURN


// ****************************************
// STAGE 3: Return to Landing Strip

Desert6_Stage_Return_To_Landing_Strip:

   	// Initialisation
	// --------------

   	IF m_goals = 0
		// Instructions: Return the aircraft to the landing strip
		PRINT_NOW DES6_10 5000 1
		timerText = m_mission_timer + 5000

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

		// Display Destination
		ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------

	IF IS_CAR_DEAD carPlayerAircraft
		m_failed = TRUE
		m_fail_reason = DESERT6_FAILED_AIRCRAFT_DEAD
		RETURN
	ENDIF


	// Player out of aircraft?
	GOSUB Desert6_Update_Failure_Conditions
	IF m_failed = TRUE
		RETURN
	ENDIF


	// Out of time
	IF g_Desert6_timerKM = 0
		m_failed = TRUE
		m_fail_reason = DESERT6_FAILED_OUT_OF_TIME
		RETURN
	ENDIF



	// Subgoals
	// --------

	// Has the player landed on the landing strip?
	IF m_goals = 1
		// A Locate to show where the runway is
		LOCATE_CHAR_ANY_MEANS_3D scplayer xposDestination yposDestination zposDestination 10.0 10.0 10.0 TRUE

		// The real test to see if the player is in the area
		IF IS_CHAR_IN_AREA_3D scplayer xloLand yloLand zloLand xhiLand yhiLand zhiLand FALSE
		AND IS_CHAR_IN_CAR scplayer carPlayerAircraft
		AND IS_CAR_STOPPED carPlayerAircraft
		AND NOT IS_CAR_IN_AIR_PROPER carPlayerAircraft
			REMOVE_BLIP blipDestination

			// Trigger the 'success' speech
			GOSUB Desert6_Conversation_Command_Play
			SET_PLAYER_CONTROL player1 OFF

			// Get rid of the Harriers
			flagAttackPlayer = FALSE
			timerMinAttack = 0

			m_goals++
		ENDIF
	ENDIF


	// Allow the mission to finish after the speech has been played?
	IF m_goals = 2
		IF IS_BIT_SET bitsConversationsPlayed DESERT6_CONVERSATION_SUCCESS
			SET_PLAYER_CONTROL player1 ON

			m_goals = 99
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	// Radar Visibility
	GOSUB Desert6_Update_Radar_Visibility


	// Is the enemy dead?
	GOSUB Desert6_Enemy_Status


	// Time to send another attacker?
	IF flagAttackPlayer = TRUE
		IF timerNewEnemy < m_mission_timer
			flagSendAnEnemy = TRUE
		ENDIF
	ENDIF


	// Send another enemy to attack player??
	IF flagSendAnEnemy = TRUE
		GOSUB Desert6_Enemy_Attack_Player

		// ...time till another enemy is generated
		nTempInt = DESERT6_TIME_TILL_NEW_ENEMY_sec * 1000
		timerNewEnemy = m_mission_timer + nTempInt
	ENDIF


	// Should enemies be removed?
	GOSUB Desert6_Remove_Enemy


	// Should the Land message be displayed?
	IF flagLandMessageDisplayed = FALSE
		GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
		GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposDestination yposDestination zposDestination fTempDistance
		IF fTempDistance < DESERT6_MESSAGE_DISPLAY_DISTANCE_m
			// Info: Land anywhere on landing strip
			PRINT_NOW DES6_11 7000 1
			flagLandMessageDisplayed = TRUE
			flagLandMessageBeingDisplayed = TRUE
			timerLandMessage = m_mission_timer + 7000
		ENDIF
	ENDIF

	IF flagLandMessageBeingDisplayed = TRUE
		IF timerLandMessage < m_mission_timer
			flagLandMessageBeingDisplayed = FALSE
			CLEAR_THIS_PRINT DES6_11
		ELSE
			PRINT_NOW DES6_11 7000 1
		ENDIF
	ENDIF

	IF flagAllDropObjectsDropped = FALSE
		GOSUB Desert6_Update_Drop_Objects
	ENDIF


	// Prepare the 'successful mission' speech?
	IF flagLandMessageDisplayed = TRUE
	AND NOT nCurrentConversationID = DESERT6_CONVERSATION_SUCCESS
	AND NOT IS_BIT_SET bitsConversationsPlayed DESERT6_CONVERSATION_SUCCESS
		SWITCH nConversationStatus
			CASE DESERT6_CONVERSATION_STATUS_NONE
				// request it
				nRequiredConversationID = DESERT6_CONVERSATION_SUCCESS
				GOSUB Desert6_Conversation_Command_Prepare
				BREAK

			CASE DESERT6_CONVERSATION_STATUS_PREPARED
				// ...cancel any other speech that has been prepared
				GOSUB Desert6_Conversation_Command_Cancel
				BREAK

		ENDSWITCH
	ENDIF


	// Check if the area51 message should be displayed
	IF flagArea51MessageDisplayed = FALSE
		IF IS_CHAR_IN_AREA_2D scplayer -67.0004 1646.8861 423.2980 2132.1875 FALSE
			IF IS_CHAR_IN_ANY_PLANE scplayer
			OR IS_CHAR_IN_ANY_HELI scplayer
				IF NOT IS_MESSAGE_BEING_DISPLAYED
					PRINT_NOW AREA51 7000 1
					flagArea51MessageDisplayed = TRUE
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Exit
	// ----

	IF m_goals = 99
		GOSUB Desert6_next_stage
	ENDIF

RETURN





// *************************************************************************************************************
//										MISSION SPECIFIC GOSUBS  
// *************************************************************************************************************


// *************************************************************************************************************
//								           Initialisation
// *************************************************************************************************************

Desert6_Create_Mission_Aircraft:

	// Plane
//	xposTemp = 373.9635
//	yposTemp = 2493.6355
//	zposTemp = 15.4958
//	headTemp = 90.0000
	xposTemp = 323.5454
	yposTemp = 2540.2539
	zposTemp = 15.8085
	headTemp = 180.0000

	CLEAR_AREA xposTemp yposTemp zposTemp 15.0 TRUE
	CREATE_CAR RUSTLER xposTemp yposTemp zposTemp carPlayerAircraft
	SET_CAR_HEADING carPlayerAircraft headTemp

RETURN


// *************************************************************************************************************
//								           Fly to Destination
// *************************************************************************************************************

// ****************************************
// Update Radar Visibility
Desert6_Update_Radar_Visibility:

	// Check the player's height above ground
	// fTempFloat: Land Z
	// fTempFloat2: Water Z
	GET_CAR_COORDINATES	carPlayerAircraft xposTemp yposTemp zposTemp
	GET_GROUND_Z_FOR_3D_COORD xposTemp yposTemp zposTemp fTempFloat
	GET_WATER_HEIGHT_AT_COORDS xposTemp yposTemp FALSE fTempFloat2

	// If the water level is higher than ground level, use it
	// fTempFloat: Ground Z (highest Z between Water and Land)
	IF fTempFloat2 > fTempFloat
		fTempFloat = fTempFloat2
	ENDIF

	// Is the player above radar visibility level?
	// fTempFloat2: Difference in height between player and Ground
	fTempFloat2 = zposTemp - fTempFloat
	IF fTempFloat2 > DESERT6_RADAR_VISIBLE_HEIGHT_UNDER_m
		// ...yes, player is radar visible
		// Was the player previously radar visible?
		IF flagRadarVisible = TRUE
			// ...yes, so check if the timer has elapsed
			IF timerVisibility < m_mission_timer
				// ...timer has elapsed, so increase visibility bar and restart timer
				g_Desert6_visibilityKM++
				IF g_Desert6_visibilityKM > 100
					g_Desert6_visibilityKM = 100
				ENDIF

				IF g_Desert6_visibilityKM = 100
				AND flagAttackPlayer = FALSE
					// Flags: player under attack; send a new enemy into the attack
					flagAttackPlayer = TRUE
//					flagSendAnEnemy = TRUE
					flagSendAnEnemy = FALSE
					flagFirstEnemyCreated = FALSE

					// Time until another enemy is created
//					nTempInt = DESERT6_TIME_TILL_NEW_ENEMY_sec * 1000
					nTempInt = DESERT6_TIME_TILL_FIRST_ENEMY_sec * 1000
					timerNewEnemy = m_mission_timer + nTempInt

					// Info: The authorities are after you
//					IF flagAboveRadarMessageDisplayed = FALSE
//						IF IS_BIT_SET bitsConversationsPlayed DESERT6_CONVERSATION_SPOTTED
//						AND NOT nConversationStatus = DESERT6_CONVERSATION_STATUS_PLAYING
//							// Only display the message once
//							PRINT_NOW DES6_05 7000 1
//							PRINT DES6_06 7000 1
//							flagAboveRadarMessageDisplayed = TRUE
//						ENDIF
//					ENDIF
				ENDIF

				timerVisibility = m_mission_timer + DESERT6_RADAR_VISIBLE_TIME_INCREASE_msec
			ENDIF

			IF flagAttackPlayer = FALSE
			AND flagCoronaMessageBeingDisplayed = FALSE
			AND flagEnemyAircraftExists = FALSE
///				IF IS_BIT_SET bitsConversationsPlayed DESERT6_CONVERSATION_TOO_HIGH
///				AND NOT nConversationStatus = DESERT6_CONVERSATION_STATUS_PLAYING
				IF NOT nConversationStatus = DESERT6_CONVERSATION_STATUS_PLAYING
//				AND nCurrentConversationID = DESERT6_CONVERSATION_NONE
//				AND timerAboveRadarLimit < m_mission_timer
				  	// Info: 'above radar limit'
				  	PRINT_NOW DES6_07 1000 1
				ENDIF
			ENDIF
		ELSE
			// ...no, not previously radar visible, so give the player a wee bit more leeway
			IF fTempFloat2 > DESERT6_RADAR_VISIBLE_HEIGHT_OVER_m
				flagRadarVisible = TRUE
				timerVisibility = m_mission_timer + DESERT6_RADAR_VISIBLE_TIME_INCREASE_msec
//				timerAboveRadarLimit = m_mission_timer + 2000

				// Trigger 'above radar level' looping sound
				PLAY_MISSION_AUDIO DESERT6_SFX_SLOT
			ENDIF
		ENDIF
	ELSE
		// ...no, player is not radar visible

// NEW 'STOP ATTACK' CONTROLS (where the player just has to get back down below radar height to stop the attack)
// END NEW 'STOP ATTACK' CONTROLS
		IF flagRadarVisible = TRUE
			// ...yes, previously radar visible, so indicate that it is not now radar visible and decrease timer
			flagRadarVisible = FALSE
			IF g_Desert6_visibilityKM > 0
				timerVisibility = m_mission_timer + DESERT6_RADAR_VISIBLE_TIME_DECREASE_msec
			ELSE
				timerVisibility = 0
			ENDIF

			// Minimum time before the planes are allowed to disappear
			IF flagAttackPlayer = TRUE
				nTempInt = DESERT6_MIN_ATTACK_TIME_sec * 1000
				timerMinAttack = m_mission_timer + nTempInt
			ENDIF

			// ...been under the radar for a while, so allow the attack to wane
			flagAttackPlayer = FALSE
			timerNewEnemy = 0

			CLEAR_THIS_PRINT DES6_07

			// Stop playing the 'above radar limit' sound
			CLEAR_MISSION_AUDIO DESERT6_SFX_SLOT
		ELSE
			// ...no, not previously radar visible, so check if the bar should decrease
			IF g_Desert6_visibilityKM > 0
				IF timerVisibility < m_mission_timer
					g_Desert6_visibilityKM--
					IF g_Desert6_visibilityKM = 0
						// ...bar empty again
						timerVisibility = 0
					ELSE
						// ...restart timer
						timerVisibility = m_mission_timer + DESERT6_RADAR_VISIBLE_TIME_DECREASE_msec
					ENDIF 
				ENDIF
			ENDIF
		ENDIF
// REMOVE OLD 'STOP ATTACK' CONTROLS (where the player had to clear the radar to stop the attack)
/*
		// Was the player previously radar visible?
		IF flagRadarVisible = TRUE
			// ...yes, previously radar visible, so indicate that it is not now radar visible and decrease timer
			flagRadarVisible = FALSE
			IF g_Desert6_visibilityKM > 0
				timerVisibility = m_mission_timer + DESERT6_RADAR_VISIBLE_TIME_DECREASE_msec
			ELSE
				timerVisibility = 0

				// Minimum time before the planes are allowed to disappear
				IF flagAttackPlayer = TRUE
					nTempInt = DESERT6_MIN_ATTACK_TIME_sec * 1000
					timerMinAttack = m_mission_timer + nTempInt
				ENDIF

				// ...been under the radar for a while, so allow the attack to wane
				flagAttackPlayer = FALSE
				timerNewEnemy = 0
			ENDIF

			CLEAR_THIS_PRINT DES6_07
		ELSE
			// ...no, not previously radar visible, so check if the bar should decrease
			IF g_Desert6_visibilityKM > 0
				IF timerVisibility < m_mission_timer
					g_Desert6_visibilityKM--
					IF g_Desert6_visibilityKM = 0
						// ...bar empty again
						timerVisibility = 0
						// Minimum time before the planes are allowed to disappear
						IF flagAttackPlayer = TRUE
							nTempInt = DESERT6_MIN_ATTACK_TIME_sec * 1000
							timerMinAttack = m_mission_timer + nTempInt
						ENDIF

						// ...been under the radar for a while, so allow the radar to wane
						flagAttackPlayer = FALSE
						timerNewEnemy = 0
					ELSE
						// ...restart timer
						timerVisibility = m_mission_timer + DESERT6_RADAR_VISIBLE_TIME_DECREASE_msec
					ENDIF 
				ENDIF
			ENDIF
		ENDIF
*/
// END REMOVE OLD 'STOP ATTACK' CONTROLS
	ENDIF

RETURN


// ****************************************
// Update Failure Conditions
Desert6_Update_Failure_Conditions:

	// If the player is out of the aircraft then allow a short period of time for him to get back in
	IF NOT IS_CHAR_IN_CAR scplayer carPlayerAircraft
		// ...player not in aircraft
		// Check if the player was previously in the aircraft or not
		IF flagFailureConditions = TRUE
			// ...previously out of aircraft, so update the timer
			IF timerFailure < m_mission_timer
				// ...timer ran out, so restart
				IF g_Desert6_failureKM = 0
					// ...time up
					m_failed = TRUE
					m_fail_reason = DESERT6_FAILED_NOT_IN_AIRCRAFT
					RETURN
				ELSE
					// ...decrease counter and restart
					g_Desert6_failureKM--
					timerFailure = m_mission_timer + 1000

					CLEAR_SMALL_PRINTS
					ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS FALSE
					IF g_Desert6_FailureKM = 1
						// ...display failure warning (singular)
						PRINT_WITH_NUMBER_NOW DES6_04 g_Desert6_FailureKM 1500 1
					ELSE
						// ...display failure warning (plural)
						PRINT_WITH_NUMBER_NOW DES6_03 g_Desert6_FailureKM 1500 1
					ENDIF
				ENDIF
			ENDIF
		ELSE
			// ...not previously out of the aircraft, so setup failure conditions
			g_Desert6_failureKM = DESERT6_FAILURE_TIME_OUT_OF_AIRCRAFT_sec
			timerFailure = m_mission_timer + 1000

			// Failure warning (plural)
			ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS FALSE
			PRINT_WITH_NUMBER_NOW DES6_03 g_Desert6_FailureKM 1500 1

			REMOVE_BLIP blipDestination
			ADD_BLIP_FOR_CAR carPlayerAircraft blipPlayerAircraft
			SET_BLIP_AS_FRIENDLY blipPlayerAircraft TRUE

			// Cancel plane dashboard warning audio
			CLEAR_MISSION_AUDIO DESERT6_SFX_SLOT

			flagFailureConditions = TRUE
		ENDIF
	ELSE
		// ...player in aircraft
		// If not previously in aircraft then clear up the failure conditions
		IF flagFailureConditions = TRUE
			flagFailureConditions = FALSE

			REMOVE_BLIP blipPlayerAircraft
			ADD_BLIP_FOR_COORD xposDestination yposDestination zposDestination blipDestination

			timerFailure = 0
			g_Desert6_failureKM = 0
			CLEAR_SMALL_PRINTS
		ENDIF
	ENDIF

RETURN


// ****************************************
// Enemy Status
Desert6_Enemy_Status:

	// NOTE: flagTempFlag decides of it's time to get rid of this plane
	flagEnemyAircraftExists = FALSE
	REPEAT DESERT6_MAX_ENEMIES nLoop
		flagTempFlag = FALSE
		IF existsEnemy[nLoop] = TRUE
			flagEnemyAircraftExists = TRUE

			// Check for plane or pilot dead
			IF IS_CAR_DEAD carEnemyAircraft[nLoop]
			OR IS_CHAR_DEAD charEnemy[nLoop]
				flagTempFlag = TRUE
			ENDIF

			// Check if the enemy is in water
			IF flagTempFlag = FALSE
				IF IS_CAR_IN_WATER carEnemyAircraft[nLoop]
					flagTempFlag = TRUE
				ENDIF
			ENDIF

			// Check for plane stuck on ground
			IF flagTempFlag = FALSE
				IF flagEnemyAircraftStuck[nLoop] = TRUE
					// ...enemy aircraft previously stuck
					IF IS_CAR_IN_AIR_PROPER carEnemyAircraft[nLoop]
						// ...not stuck any longer
						flagEnemyAircraftStuck[nLoop] = FALSE
						timerEnemyAircraftStuck[nLoop] = 0
					ELSE
						// ...still stuck
						IF timerEnemyAircraftStuck[nLoop] < m_mission_timer
							// ...timeout
							flagTempFlag = TRUE
						ENDIF
					ENDIF
				ELSE
					// ...enemy aircraft previously not stuck
					IF NOT IS_CAR_IN_AIR_PROPER carEnemyAircraft[nLoop]
						// ...may now be stuck
						flagEnemyAircraftStuck[nLoop] = TRUE
						timerEnemyAircraftStuck[nLoop] = m_mission_timer + DESERT6_ENEMY_AIRCRAFT_STUCK_TIME_msec
					ENDIF
				ENDIF
			ENDIF

			// Get rid of this plane
			IF flagTempFlag = TRUE
				MARK_CAR_AS_NO_LONGER_NEEDED carEnemyAircraft[nLoop]
				MARK_CHAR_AS_NO_LONGER_NEEDED charEnemy[nLoop]
				REMOVE_BLIP blipEnemy[nLoop]
				existsEnemy[nLoop] = FALSE

				IF flagAttackPlayer = TRUE
					timerNewEnemy = m_mission_timer + 2000
				ENDIF

				RETURN
			ENDIF
		ENDIF
	ENDREPEAT

RETURN


// ****************************************
// Enemy Attack Player
Desert6_Enemy_Attack_Player:

	// Enemy using planes
	GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
	REPEAT DESERT6_MAX_ENEMIES nLoop
		IF existsEnemy[nLoop] = FALSE
			// Randomly choose a start position for the Harrier around the player
//			GENERATE_RANDOM_INT_IN_RANGE 400 600 nTempInt
			GENERATE_RANDOM_INT_IN_RANGE 150 200 nTempInt
			fTempFloat =# nTempInt
			GENERATE_RANDOM_INT_IN_RANGE 0 2 nTempInt2
			IF nTempInt2 = 0
				xposTemp += fTempFloat
			ELSE
				xposTemp -= fTempFloat
			ENDIF

//			GENERATE_RANDOM_INT_IN_RANGE 400 600 nTempInt
			GENERATE_RANDOM_INT_IN_RANGE 150 200 nTempInt
			fTempFloat =# nTempInt
			GENERATE_RANDOM_INT_IN_RANGE 0 2 nTempInt2
			IF nTempInt2 = 0
				yposTemp += fTempFloat
			ELSE
				yposTemp -= fTempFloat
			ENDIF

			// Get the player's height above ground
			// fTempFloat: Land Z
			// fTempFloat2: Water Z
			GET_GROUND_Z_FOR_3D_COORD xposTemp yposTemp zposTemp fTempFloat
			GET_WATER_HEIGHT_AT_COORDS xposTemp yposTemp FALSE fTempFloat2

			// If the water level is higher than ground level, use it
			// fTempFloat: Ground Z (highest Z between Water and Land)
			IF fTempFloat2 > fTempFloat
				fTempFloat = fTempFloat2
			ENDIF

			// Start the plane 200m above the ground
			zposTemp = fTempFloat + 200.0

			CREATE_CAR HYDRA xposTemp yposTemp zposTemp carEnemyAircraft[nLoop]

			PLANE_STARTS_IN_AIR carEnemyAircraft[nLoop]
			PLANE_ATTACK_PLAYER carEnemyAircraft[nLoop] player1 10.0

			ADD_BLIP_FOR_CAR carEnemyAircraft[nLoop] blipEnemy[nLoop]
			CHANGE_BLIP_DISPLAY blipEnemy[nLoop] BLIP_ONLY

			// ...pilot
			CREATE_CHAR_INSIDE_CAR carEnemyAircraft[nLoop] PEDTYPE_CIVMALE HMORI charEnemy[nLoop]
			SET_CHAR_DECISION_MAKER charEnemy[nLoop] dmEmpty

			// ...housekeeping
			flagEnemyAircraftStuck[nLoop]	= FALSE
			timerEnemyAircraftStuck[nLoop]	= 0
			existsEnemy[nLoop]				= TRUE
			flagSendAnEnemy					= FALSE
			flagFirstEnemyCreated			= TRUE

			// ...only need one enemy just now
			RETURN
		ENDIF
	ENDREPEAT

RETURN


// ****************************************
// Remove Enemy
Desert6_Remove_Enemy:

	IF flagAttackPlayer = TRUE
		RETURN
	ENDIF


	IF timerMinAttack > m_mission_timer
		RETURN
	ENDIF


	IF flagFirstEnemyCreated = FALSE
		RETURN
	ENDIF


	GET_CHAR_COORDINATES scplayer xposTemp2 yposTemp2 zposTemp2
	REPEAT DESERT6_MAX_ENEMIES nLoop
		IF existsEnemy[nLoop] = TRUE
			GET_CAR_COORDINATES carEnemyAircraft[nLoop] xposTemp yposTemp zposTemp
			GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempFloat
			IF fTempFloat > 250.0
				// ...remove this enemy
				REMOVE_CHAR_ELEGANTLY charEnemy[nLoop]
				DELETE_CAR carEnemyAircraft[nLoop]
				REMOVE_BLIP blipEnemy[nLoop]
				existsEnemy[nLoop] = FALSE
			ENDIF
		ENDIF
	ENDREPEAT

RETURN


// ****************************************
// Create Drop Objects
Desert6_Create_Drop_Objects:

	IF IS_CAR_DEAD carPlayerAircraft
		RETURN
	ENDIF

	// Create the objects (anywhere)
	CREATE_OBJECT AMMO_CAPSULE 370.5000 2498.7500 25.5000 objectDrop[0]
	CREATE_OBJECT AMMO_CAPSULE 371.2500 2498.3500 25.3000 objectDrop[1]
	CREATE_OBJECT AMMO_CAPSULE 371.2500 2499.1500 25.7000 objectDrop[2]
	CREATE_OBJECT AMMO_CAPSULE 372.0000 2498.3500 25.4000 objectDrop[3]

	// Attach the objects to the plane (to get their initial drop positions)
	ATTACH_OBJECT_TO_CAR objectDrop[0] carPlayerAircraft -0.75  0.30 -1.05 88.0 0.0 0.0
	ATTACH_OBJECT_TO_CAR objectDrop[1] carPlayerAircraft -0.25  0.30 -1.10 88.0 0.0 0.0
	ATTACH_OBJECT_TO_CAR objectDrop[2] carPlayerAircraft  0.25  0.30 -1.10 88.0 0.0 0.0
	ATTACH_OBJECT_TO_CAR objectDrop[3] carPlayerAircraft  0.75  0.30 -1.05 88.0 0.0 0.0

	// Switch off the object collisions
	REPEAT DESERT6_MAX_DROP_OBJECTS nLoop
		SET_OBJECT_COLLISION objectDrop[nLoop] FALSE
	ENDREPEAT

RETURN


// ****************************************
// Drop Objects
Desert6_Drop_Objects:

	flagAllDropObjectsDropped = FALSE

	// Set up the timer for the objects to drop
	// NOTE: 30/7/04: They now all drop at the same time
	REPEAT DESERT6_MAX_DROP_OBJECTS nLoop
//		GENERATE_RANDOM_INT_IN_RANGE 100 1500 nTempInt
//		timerDropObject[nLoop] = m_mission_timer + nTempInt
		timerDropObject[nLoop] = m_mission_timer
		flagDropObjectDropped[nLoop] = FALSE
	ENDREPEAT

RETURN


// ****************************************
// Update Drop Objects
Desert6_Update_Drop_Objects:

	// Check if any objects need to drop
	REPEAT DESERT6_MAX_DROP_OBJECTS nLoop
		IF flagDropObjectDropped[nLoop] = FALSE
			IF timerDropObject[nLoop] < m_mission_timer
				// Detach the Object
				DETACH_OBJECT objectDrop[nLoop] 0.0 0.0 0.0 TRUE

				// Allow it to fall
				SET_OBJECT_DYNAMIC objectDrop[nLoop] TRUE

				// Play audio
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT objectDrop[nLoop] SOUND_BONNET_DENT

				// Housekeeping
				flagDropObjectDropped[nLoop] = TRUE
			ENDIF
		ENDIF
	ENDREPEAT

	// Check if this update needs to run again
	REPEAT DESERT6_MAX_DROP_OBJECTS nLoop
		IF flagDropObjectDropped[nLoop] = FALSE
			// ...continue to do the update on teh next frame
			RETURN
		ENDIF
	ENDREPEAT

	// No longer need to do the update
	flagAllDropObjectsDropped = TRUE

RETURN


// *************************************************************************************************************
// 												  AUDIO ROUTINES   
// *************************************************************************************************************

// ------------------------------
//	SPEECH ENGINE
// ------------------------------

// ****************************************
// UPDATE SPEECH
Desert6_Update_Speech:

	SWITCH nConversationStatus
		CASE DESERT6_CONVERSATION_STATUS_NONE
		CASE DESERT6_CONVERSATION_STATUS_PREPARED
		CASE DESERT6_CONVERSATION_STATUS_INTERRUPTED
		CASE DESERT6_CONVERSATION_STATUS_FINISHED
			// ...nothing to do
			BREAK

		CASE DESERT6_CONVERSATION_STATUS_PLAYING
			// ...playing the conversation
			GOSUB Desert6_Playing_Conversation
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_CONVERSATION_STATUS_ID
			RETURN
	ENDSWITCH

RETURN


// ****************************************
// Initial Speech Slot Preloads
Desert6_Initial_Speech_Slot_Preloads:

	// Preload the first two slots if possible
	IF nNextPreloadConversationLine = nCurrentMaxConversationLines
		RETURN
	ENDIF

	// ...slot 1
	nTempInt = DESERT6_FIRST_SPEECH_SLOT
   	nAudioSlotStatus[nTempInt] = DESERT6_AUDIO_SLOT_LOADING
   	LOAD_MISSION_AUDIO nTempInt nConversationSequenceSpeechID[nNextPreloadConversationLine]
   	nNextPreloadConversationLine++

	// Are there more lines to be preloaded
	IF nNextPreloadConversationLine = nCurrentMaxConversationLines
		RETURN
	ENDIF

	// ...slot 2
	nTempInt++
	IF nTempInt > DESERT6_LAST_SPEECH_SLOT
		RETURN
	ENDIF

   	nAudioSlotStatus[nTempInt] = DESERT6_AUDIO_SLOT_LOADING
   	LOAD_MISSION_AUDIO nTempInt nConversationSequenceSpeechID[nNextPreloadConversationLine]
   	nNextPreloadConversationLine++

RETURN


// ****************************************
// Playing Conversation
Desert6_Playing_Conversation:

	// Check if the conversation should be interrupted
	GOSUB Desert6_Check_If_Interrupt_Conversation
	IF nConversationStatus = DESERT6_CONVERSATION_STATUS_INTERRUPTED
		GOSUB Desert6_Interrupt_Conversation
		RETURN
	ENDIF

	// Check the status of the current slot
	SWITCH nAudioSlotStatus[nSpeechCurrentSlot]
		CASE DESERT6_AUDIO_SLOT_FREE
			RETURN
			BREAK

		CASE DESERT6_AUDIO_SLOT_LOADING
			// ...check if the speech has loaded
			IF NOT HAS_MISSION_AUDIO_LOADED nSpeechCurrentSlot
				// ...no
				RETURN
			ENDIF

			// Speech has loaded
			nAudioSlotStatus[nSpeechCurrentSlot] = DESERT6_AUDIO_SLOT_LOADED
			BREAK

		CASE DESERT6_AUDIO_SLOT_LOADED
			// ...play the speech and display the subtitles
			PLAY_MISSION_AUDIO nSpeechCurrentSlot
			PRINT_NOW $tlConversationSequenceSubtitle[nCurrentConversationLine] 10000 1
			nAudioSlotStatus[nSpeechCurrentSlot] = DESERT6_AUDIO_SLOT_PLAYING
			RETURN
			BREAK

		CASE DESERT6_AUDIO_SLOT_PLAYING
			// ...check if the speech has finished playing
			IF NOT HAS_MISSION_AUDIO_FINISHED nSpeechCurrentSlot
				// ...still playing
				RETURN
			ENDIF

			// Speech has finished playing, so move on to the next piece
			GOSUB Desert6_Current_Speech_Finished			
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_AUDIO_SLOT_STATUS
			RETURN
	ENDSWITCH

RETURN


// ****************************************
// Current Conversation has been interrupted
Desert6_Interrupt_Conversation:

	// Clear mission audio
	nTempInt = DESERT6_FIRST_SPEECH_SLOT
	WHILE nTempInt <= DESERT6_LAST_SPEECH_SLOT
		CLEAR_MISSION_AUDIO nTempInt
		nTempInt++
	ENDWHILE

	GOSUB Desert6_Finish_Conversation

RETURN


// ****************************************
// Current Speech has finished
Desert6_Current_Speech_Finished:

	// Housekeeping
	nAudioSlotStatus[nSpeechCurrentSlot] = DESERT6_AUDIO_SLOT_FREE
	nCurrentConversationLine++
	IF nCurrentConversationLine = nCurrentMaxConversationLines
		// ...conversation over
		GOSUB Desert6_Finish_Conversation
		RETURN
	ENDIF

	// Preload the next piece of audio that will be played in this slot
	IF nNextPreloadConversationLine < nCurrentMaxConversationLines
		// ...there is another piece of speech required in this slot
		nAudioSlotStatus[nSpeechCurrentSlot] = DESERT6_AUDIO_SLOT_LOADING
		LOAD_MISSION_AUDIO nSpeechCurrentSlot nConversationSequenceSpeechID[nNextPreloadConversationLine]
		nNextPreloadConversationLine++
	ENDIF

	// Switch to the next speech slot
	nSpeechCurrentSlot++
	IF nSpeechCurrentSlot > DESERT6_LAST_SPEECH_SLOT
		nSpeechCurrentSlot = DESERT6_FIRST_SPEECH_SLOT
	ENDIF

RETURN


// ****************************************
// Current Conversation has finished
Desert6_Finish_Conversation:

	// Mark this conversation as 'played'
	SET_BIT bitsConversationsPlayed nCurrentConversationID

	nCurrentConversationID			= DESERT6_CONVERSATION_NONE
	nConversationStatus				= DESERT6_CONVERSATION_STATUS_NONE
	nRequiredConversationID			= DESERT6_CONVERSATION_NONE
	nSpeechCurrentSlot				= DESERT6_FIRST_SPEECH_SLOT
	nCurrentConversationLine		= 0
	nCurrentMaxConversationLines	= 0
	nNextPreloadConversationLine	= 0

	nTempInt = DESERT6_FIRST_SPEECH_SLOT
	WHILE nTempInt < DESERT6_LAST_SPEECH_SLOT
		nAudioSlotStatus[nTempInt] = DESERT6_AUDIO_SLOT_FREE
		nTempInt++
	ENDWHILE

	// Clear subtitles
	CLEAR_SMALL_PRINTS

	// Introduce a delay before the next conversation can be prepared
	timerDelayBeforeNextConversation = m_mission_timer + DESERT6_DELAY_BEFORE_NEXT_CONVERSATION_msec

RETURN


// ------------------------------
//  EXTERNAL SPEECH CONTROLS
// ------------------------------

// ****************************************
// Conversation Command Prepare
Desert6_Conversation_Command_Prepare:

	// Prepare a conversation
	IF NOT nConversationStatus = DESERT6_CONVERSATION_STATUS_NONE
		GOSUB Desert6_Conversation_Command_Cancel
	ENDIF

	// Wait for the timer to elapse before preparing the next conversation
	IF timerDelayBeforeNextConversation > m_mission_timer
		RETURN
	ENDIF

	timerDelayBeforeNextConversation	= 0
	nCurrentConversationID				= nRequiredConversationID
	nRequiredConversationID				= DESERT6_CONVERSATION_NONE
	nSpeechCurrentSlot					= DESERT6_FIRST_SPEECH_SLOT
	nCurrentConversationLine			= 0
	nCurrentMaxConversationLines		= 0
	nNextPreloadConversationLine		= 0

	nTempInt = DESERT6_FIRST_SPEECH_SLOT
	WHILE nTempInt < DESERT6_LAST_SPEECH_SLOT
		nAudioSlotStatus[nTempInt] = DESERT6_AUDIO_SLOT_FREE
		nTempInt++
	ENDWHILE

	// Prepare the next conversation
	GOSUB Desert6_Prepare_Next_Conversation
	
RETURN


// ****************************************
// Conversation Command Play
Desert6_Conversation_Command_Play:

	// Ensure the conversation has been prepared
	IF NOT nConversationStatus = DESERT6_CONVERSATION_STATUS_PREPARED
		WRITE_DEBUG A_CONVERSATION_HAS_NOT_BEEN_PREPARED
		RETURN
	ENDIF

	nConversationStatus = DESERT6_CONVERSATION_STATUS_PLAYING

RETURN


// ****************************************
// Conversation Command Cancel
Desert6_Conversation_Command_Cancel:

	// Ensure the conversation has been prepared
	IF nConversationStatus = DESERT6_CONVERSATION_STATUS_NONE
		RETURN
	ENDIF

	GOSUB Desert6_Interrupt_Conversation

RETURN


// ------------------------------
//  MISSION SPECIFIC SPEECH SETUP
// ------------------------------

// ****************************************
// Prepare Next Conversation
Desert6_Prepare_Next_Conversation:

	SWITCH nCurrentConversationID
		CASE DESERT6_CONVERSATION_INSTRUCTIONS
			GOSUB Desert6_Prepare_Conversation_Instructions
			BREAK

		CASE DESERT6_CONVERSATION_TOO_HIGH
			GOSUB Desert6_Prepare_Conversation_Too_High
			BREAK

		CASE DESERT6_CONVERSATION_SPOTTED
			GOSUB Desert6_Prepare_Conversation_Spotted
			BREAK

		CASE DESERT6_CONVERSATION_SUCCESS
			GOSUB Desert6_Prepare_Conversation_Success
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_CONVERSATION
			RETURN
	ENDSWITCH

	// Preload the first two pieces of speech
	GOSUB Desert6_Initial_Speech_Slot_Preloads
	nConversationStatus = DESERT6_CONVERSATION_STATUS_PREPARED

RETURN


// ****************************************
// Check if the current conversation should be interrupted
Desert6_Check_If_Interrupt_Conversation:

	// Is Carl dead?
	IF IS_CHAR_DEAD scplayer
		nConversationStatus = DESERT6_CONVERSATION_STATUS_INTERRUPTED
		RETURN
	ENDIF

	// Is the plane dead?
	IF IS_CAR_DEAD carPlayerAircraft
		nConversationStatus = DESERT6_CONVERSATION_STATUS_INTERRUPTED
		RETURN
	ENDIF

	// Is Carl out of the plane?
	IF NOT IS_CHAR_IN_CAR scplayer carPlayerAircraft
		nConversationStatus = DESERT6_CONVERSATION_STATUS_INTERRUPTED
		RETURN
	ENDIF

RETURN


// ****************************************
// Prepare Conversation: Instructions
Desert6_Prepare_Conversation_Instructions:

	// Conversation between Carl and Toreno (through radio) 

	nTempInt = 0
	nConversationSequenceSpeechID[nTempInt] = SOUND_DES6_AA
	$tlConversationSequenceSubtitle[nTempInt] = DES6_AA

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_DES6_AB
	$tlConversationSequenceSubtitle[nTempInt] = DES6_AB

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_DES6_AD
	$tlConversationSequenceSubtitle[nTempInt] = DES6_AD

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_DES6_AE
	$tlConversationSequenceSubtitle[nTempInt] = DES6_AE

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_DES6_AF
	$tlConversationSequenceSubtitle[nTempInt] = DES6_AF

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_DES6_AG
	$tlConversationSequenceSubtitle[nTempInt] = DES6_AG

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_DES6_AH
	$tlConversationSequenceSubtitle[nTempInt] = DES6_AH

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_DES6_AJ
	$tlConversationSequenceSubtitle[nTempInt] = DES6_AJ

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_DES6_AK
	$tlConversationSequenceSubtitle[nTempInt] = DES6_AK

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_DES6_AM
	$tlConversationSequenceSubtitle[nTempInt] = DES6_AM

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_DES6_AN
	$tlConversationSequenceSubtitle[nTempInt] = DES6_AN

	nTempInt++
	IF nTempInt > DESERT6_MAX_CONVERSATION_LINES
		WRITE_DEBUG TOO_MANY_CONVERSATION_LINES
	ENDIF

	nCurrentMaxConversationLines = nTempInt

RETURN


// ****************************************
// Prepare Conversation: Too High
Desert6_Prepare_Conversation_Too_High:

	// A random one of 4 comments spoken by Toreno (through radio)

	GENERATE_RANDOM_INT_IN_RANGE 0 4 nTempInt
	SWITCH nTempInt
		CASE 0
			nConversationSequenceSpeechID[0] = SOUND_DES6_BA
			$tlConversationSequenceSubtitle[0] = DES6_BA
			BREAK

		CASE 1
			nConversationSequenceSpeechID[0] = SOUND_DES6_BB
			$tlConversationSequenceSubtitle[0] = DES6_BB
			BREAK

		CASE 2
			nConversationSequenceSpeechID[0] = SOUND_DES6_BC
			$tlConversationSequenceSubtitle[0] = DES6_BC
			BREAK

		CASE 3
			nConversationSequenceSpeechID[0] = SOUND_DES6_BD
			$tlConversationSequenceSubtitle[0] = DES6_BD
			BREAK

	ENDSWITCH

	nCurrentMaxConversationLines = 1
RETURN


// ****************************************
// Prepare Conversation: Spotted
Desert6_Prepare_Conversation_Spotted:

	// Conversation between Carl and Toreno (through radio) 

	nTempInt = 0
	nConversationSequenceSpeechID[nTempInt] = SOUND_DES6_CA
	$tlConversationSequenceSubtitle[nTempInt] = DES6_CA

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_DES6_CB
	$tlConversationSequenceSubtitle[nTempInt] = DES6_CB

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_DES6_CC
	$tlConversationSequenceSubtitle[nTempInt] = DES6_CC

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_DES6_CD
	$tlConversationSequenceSubtitle[nTempInt] = DES6_CD

	nTempInt++
	IF nTempInt > DESERT6_MAX_CONVERSATION_LINES
		WRITE_DEBUG TOO_MANY_CONVERSATION_LINES
	ENDIF

	nCurrentMaxConversationLines = nTempInt

RETURN


// ****************************************
// Prepare Conversation: Success
Desert6_Prepare_Conversation_Success:

	// Speech by Toreno (through radio) 

	nTempInt = 0
	nConversationSequenceSpeechID[nTempInt] = SOUND_DES6_DA
	$tlConversationSequenceSubtitle[nTempInt] = DES6_DA

	nCurrentMaxConversationLines = 1

RETURN



// *************************************************************************************************************
// 												HOUSEKEEPING GOSUBS   
// *************************************************************************************************************

// ****************************************
// DEBUG TOOLS
Desert6_Debug_Tools:

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
		Desert6_view_debug[0] = m_stage
		Desert6_view_debug[1] = m_goals
		Desert6_view_debug[2] = m_mission_timer
		Desert6_view_debug[3] = nCurrentConversationID
		Desert6_view_debug[4] = bitsConversationsPlayed
		Desert6_view_debug[5] = 0
		Desert6_view_debug[6] = 0
		Desert6_view_debug[7] = 0
		// First two lines are so that the important data displayed is not hidden by other text
		VIEW_INTEGER_VARIABLE Desert6_view_debug[0] m_stage
		VIEW_INTEGER_VARIABLE Desert6_view_debug[1] m_goals
		VIEW_INTEGER_VARIABLE Desert6_view_debug[2] m_mission_timer
		VIEW_INTEGER_VARIABLE Desert6_view_debug[3] Conversation
		VIEW_INTEGER_VARIABLE Desert6_view_debug[4] ConversationsPlayed
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
Desert6_Debug_Shortcuts:

	// Jumps
	// -----

	IF NOT IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_J
		RETURN
	ENDIF

	
	// Stage 2 jump
	IF m_stage = 2
		IF m_goals < 2
			// Warp Player
			CLEAR_AREA -2246.7063 -2539.0999 30.7512 25.0 TRUE
			SET_CHAR_COORDINATES scplayer -2246.7063 -2539.0999 30.7512
			SET_CHAR_HEADING scplayer 144.5365
			m_goals = 2
		ELSE
			// Pretend flown through corona
			REMOVE_BLIP blipDestination
			DELETE_CHECKPOINT checkpointDestination
			m_goals = 99
		ENDIF
	ENDIF

	// Stage 3 jump
	IF m_stage = 3
		// Warp Player
		CLEAR_AREA 384.4035 2449.3503 15.4921 25.0 TRUE
		SET_CHAR_COORDINATES scplayer 384.4035 2449.3503 15.4921
		SET_CHAR_HEADING scplayer 7.1386
	ENDIF

RETURN



// ****************************************
// FRAME COUNTER (Useful if processor scheduling is needed)
Desert6_Frame_Counter:

	m_frame_num++
	IF m_frame_num > 9
		m_frame_num = 0
	ENDIF

RETURN



// ****************************************
// ADDITIONAL TIMERS
Desert6_Additional_Timers:

	GET_GAME_TIMER m_this_frame_time
	m_time_diff = m_this_frame_time - m_last_frame_time 
	m_last_frame_time = m_this_frame_time

	m_mission_timer += m_time_diff

RETURN



// =================================================================
// GOSUB: Next_Stage (sets flags and timers for next mission stage)
// =================================================================
Desert6_next_stage:

   	m_stage++
   	m_goals        = 0
   	TIMERA 		   = 0
   	TIMERB		   = 0

RETURN					





// *************************************************************************************************************
//										INTRO CUTSCENE GOSUB
// *************************************************************************************************************

Desert6_Intro_Cutscene:

	IF NOT IS_CHAR_DEAD scplayer
		SET_PLAYER_CONTROL player1 OFF
	ENDIF

	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE


	// Clear the area used during the cutscene
	CLEAR_AREA 335.0 2529.0 16.0 20.0 TRUE


	LOAD_CUTSCENE DESERT6
	WHILE NOT HAS_CUTSCENE_LOADED
		WAIT 0
	ENDWHILE
	 

	START_CUTSCENE


	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_IN

	FORCE_WEATHER WEATHER_EXTRASUNNY_DESERT


	WHILE NOT HAS_CUTSCENE_FINISHED
		WAIT 0
	ENDWHILE


	SET_FADING_COLOUR 0 0 0
	DO_FADE 0 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
 

	CLEAR_CUTSCENE


	RESTORE_CAMERA_JUMPCUT
	SWITCH_WIDESCREEN OFF

RETURN




// *************************************************************************************************************
// 												INITIALISATION GOSUBS   
// *************************************************************************************************************

Desert6_Initialisation:

	WHILE NOT IS_PLAYER_PLAYING player1
		WAIT 0
	ENDWHILE


	// Switch off the Car Generators for any planes in the hangers, and clear the area
	SWITCH_CAR_GENERATOR d5_bronze_generator 0
	SWITCH_CAR_GENERATOR d5_silver_generator 0
	SWITCH_CAR_GENERATOR d5_gold_generator 0
	CLEAR_AREA 368.1973 2519.4211 15.5961 300.0 0


	GOSUB Desert6_Load_All_Models
	GOSUB Desert6_Load_All_Anims


	// Create the mission aircraft
	GOSUB Desert6_Create_Mission_Aircraft
	ADD_BLIP_FOR_CAR carPlayerAircraft blipPlayerAircraft
	SET_BLIP_AS_FRIENDLY blipPlayerAircraft TRUE


	// Position the player
	CLEAR_AREA 331.9908 2533.7666 15.8044 0.5 0
	LOAD_SCENE 331.9908 2533.7666 15.8044
	SET_CHAR_COORDINATES scplayer 331.9908 2533.7666 15.8044 
	SET_CHAR_HEADING scplayer 48.4743 //188.7185
	SET_CAMERA_BEHIND_PLAYER


	// Force the weather to be sunny
	FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT


	// Switch off ambient planes
	SWITCH_AMBIENT_PLANES OFF


	// Wait a short period of time to allow the plane to settle on the ground before fading in
	WAIT 500


	// Fade in
	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_IN

	
	// Player control
	IF NOT IS_CHAR_DEAD scplayer
		SET_PLAYER_CONTROL player1 ON
	ENDIF


	// Respawn point
	SET_RESPAWN_POINT_FOR_DURATION_OF_MISSION 322.8019 2530.1240 15.8089


	GOSUB Desert6_next_stage

RETURN


// ***********************************
// LOAD ALL MODELS

Desert6_Load_All_Models:

	// CARS
	REQUEST_MODEL RUSTLER		// Player's Plane
	REQUEST_MODEL HYDRA			// Enemy Planes

	// PEDS
	REQUEST_MODEL HMORI			// Enemy pilot

	// OBJECTS
	REQUEST_MODEL AMMO_CAPSULE	// For drop-off object

	LOAD_ALL_MODELS_NOW


	// Are Cars Loaded?
	WHILE NOT HAS_MODEL_LOADED	RUSTLER
	OR NOT HAS_MODEL_LOADED		HYDRA
		WAIT 0
	ENDWHILE

	// Are Peds Loaded?
	WHILE NOT HAS_MODEL_LOADED	HMORI
		WAIT 0
	ENDWHILE

	// Are weapons loaded?

	// Are the Objects loaded?
	WHILE NOT HAS_MODEL_LOADED	AMMO_CAPSULE
		WAIT 0
	ENDWHILE

RETURN


// ***********************************
// LOAD ALL ANIMS

Desert6_Load_All_Anims:

//	REQUEST_ANIMATION INT_HOUSE				// for washing up


	// Are anims loaded?
//	WHILE NOT HAS_ANIMATION_LOADED	INT_HOUSE
//		WAIT 0
//	ENDWHILE

RETURN





// *************************************************************************************************************
//										END OF LEVEL GOSUBS - fail, pass, cleanup  
// *************************************************************************************************************

// FAIL
Desert6_Mission_Failed:

	PRINT_BIG M_FAIL 5000 1

	// Display reason for failure (if there is a reason)
	CLEAR_SMALL_PRINTS

	SWITCH m_fail_reason
		CASE DESERT6_FAILED_AIRCRAFT_DEAD
			PRINT_NOW DES6_99 5000 1
			BREAK
		CASE DESERT6_FAILED_NOT_IN_AIRCRAFT
			PRINT_NOW DES6_98 5000 1
			BREAK
		CASE DESERT6_FAILED_OUT_OF_TIME
			PRINT_NOW DES6_97 5000 1
			BREAK
	ENDSWITCH

RETURN


// PASS
mission_passed_Desert6:

	PRINT_WITH_NUMBER_BIG M_PASS 15000 5000 1
	ADD_SCORE player1 15000
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1
	REGISTER_MISSION_PASSED DESERT6
	PLAYER_MADE_PROGRESS 1

	flag_desert_mission_counter++

RETURN


// CLEANUP
Desert6_mission_cleanup:

	// Get rid of the Player's mission specific weapons
	// ------------------------------------------------

//	IF IS_PLAYER_PLAYING player1
//		REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_MOLOTOV
//	ENDIF


	// Entity Task Removal, etc.
	// -------------------------


	// Entity Clearup
	// --------------
	MARK_CAR_AS_NO_LONGER_NEEDED	carPlayerAircraft

	REPEAT DESERT6_MAX_ENEMIES nLoop
		MARK_CAR_AS_NO_LONGER_NEEDED	carEnemyAircraft[nLoop]
		MARK_CHAR_AS_NO_LONGER_NEEDED	charEnemy[nLoop]
	ENDREPEAT

	REPEAT DESERT6_MAX_DROP_OBJECTS nLoop
		DELETE_OBJECT	objectDrop[nLoop]
	ENDREPEAT


	
	// Blip Clearup
	// ------------
	REMOVE_BLIP blipPlayerAircraft
	REMOVE_BLIP blipDestination
	REPEAT DESERT6_MAX_ENEMIES nLoop
		REMOVE_BLIP blipEnemy[nLoop]
	ENDREPEAT


	// Counters and Timers Clearup
	// ---------------------------
	CLEAR_ONSCREEN_COUNTER g_Desert6_visibilityKM
	CLEAR_ONSCREEN_TIMER g_Desert6_timerKM


	// Checkpoint Clearup
	// ------------------
	DELETE_CHECKPOINT checkpointDestination


	// Animation Clearup
	// -----------------


	// Looping Audio Clearup
	// ---------------------
	CLEAR_MISSION_AUDIO DESERT6_SFX_SLOT


	// Models Clearup
	// --------------
	// ...cars
	MARK_MODEL_AS_NO_LONGER_NEEDED	RUSTLER
	MARK_MODEL_AS_NO_LONGER_NEEDED	HYDRA

	// ...peds
	MARK_MODEL_AS_NO_LONGER_NEEDED	HMORI

	// ...guns

	// ...objects
	MARK_MODEL_AS_NO_LONGER_NEEDED	AMMO_CAPSULE


	// === RESTORE ENVIRONMENT SETTINGS ===
	// ------------------------------------
	SET_PED_DENSITY_MULTIPLIER	1.0
	SET_CAR_DENSITY_MULTIPLIER	1.0
	SET_WANTED_MULTIPLIER		1.0
	SWITCH_AMBIENT_PLANES		ON
	RELEASE_WEATHER


	// Make sure the player gets a 6* rating again whenever he sets foot on the aircraft carrier
	SET_DISABLE_MILITARY_ZONES FALSE


	// Restore switched off road
	// -------------------------
//	SWITCH_ROADS_BACK_TO_ORIGINAL -2319.8967 -2719.9604 28.4250 -2197.9482 -2482.0303 44.5080


	// Make sure the mobile phone doesn't ring immediately after a mission
	// -------------------------------------------------------------------
	GET_GAME_TIMER timer_mobile_start


	// Restore any car generators active at the Desert Airstrip
	// --------------------------------------------------------
	IF d5_bronze_generator_unlocked = TRUE
		SWITCH_CAR_GENERATOR d5_bronze_generator 101
	ENDIF

	IF d5_silver_generator_unlocked = TRUE
		SWITCH_CAR_GENERATOR d5_silver_generator 101
	ENDIF

	IF d5_gold_generator_unlocked = TRUE
		SWITCH_CAR_GENERATOR d5_gold_generator 101
	ENDIF


	// Housekeeping
	// ------------

	flag_player_on_mission = 0
	MISSION_HAS_FINISHED

RETURN
	 

}