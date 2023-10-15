MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
//
//			   MISSION NAME : Crash1
//				     AUTHOR : Keith
//		       DESICRIPTION : Torch a building, then rescue lady trapped inside
//
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

{
// *************************************************************************************************************
//								GENERAL INITIALISATION - Every level requires this
// *************************************************************************************************************


SCRIPT_NAME Crash1


// Global Variables
VAR_INT	g_Crash1_counterKM1


// Mission Start stuff
GOSUB mission_start_Crash1

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_failed_Crash1
ENDIF

GOSUB mission_cleanup_Crash1

MISSION_END


// Global Setup
mission_start_Crash1:

REGISTER_MISSION_GIVEN
flag_player_on_mission = 1


// general variables that are in every mission - DON'T DECLARE MISSION SPECIFIC VARIABLES HERE
LVAR_INT m_stage
LVAR_INT m_goals
LVAR_INT m_passed
LVAR_INT m_failed  
LVAR_INT m_quit
LVAR_INT m_pause
LVAR_INT m_status
LVAR_INT m_frame_num
LVAR_INT m_this_frame_time
LVAR_INT m_last_frame_time
LVAR_INT m_time_diff	
// commonly used timers
LVAR_INT m_mission_timer
// Debug variables
LVAR_INT display_debug
VAR_INT Crash1_view_debug[8]	// GLOBAL (for output)




m_stage			= 0
m_goals			= 0
m_passed		= 0
m_failed		= 0	  
m_quit			= 0
m_pause			= 0
m_status		= 0

m_mission_timer	= 0

display_debug	= 0

TIMERA = 0
TIMERB = 0


// Consts
CONST_INT	CRASH1_GIRL_BURNS_TIME_ms						3000
CONST_INT	CRASH1_INFINITE_AMMO							30000
CONST_INT	CRASH1_PERSIST									-2
// ...cash pickup IDs
CONST_INT	CRASH1_CASH_PICKUP_DOWNSTAIRS_BEDROOM			0
CONST_INT	CRASH1_CASH_PICKUP_COOCHIE_BEDROOM				1
// ----------
CONST_INT	CRASH1_MAX_CASH_PICKUPS							2	// The last value above + 1
// ...external fires
CONST_INT	CRASH1_EXTERIOR_FIRE_LIVING_ROOM 				0
CONST_INT	CRASH1_EXTERIOR_FIRE_BIG_BEDROOM				1
CONST_INT	CRASH1_EXTERIOR_FIRE_EXTRA_ROOM					2
CONST_INT	CRASH1_EXTERIOR_FIRE_SMALL_BEDROOM				3
CONST_INT	CRASH1_EXTERIOR_FIRE_KITCHEN					4
CONST_INT	CRASH1_EXTERIOR_FIRE_ALL						5

// Speech
CONST_INT	CRASH1_MAX_CONVERSATION_LINES					9
CONST_INT	CRASH1_DELAY_BEFORE_NEXT_CONVERSATION_msec		120
// ...audio slot allocations
CONST_INT	CRASH1_TOTAL_AUDIO_SLOTS						2	// 3 available: generally 1 and 2 for speech; 3 for SFX
CONST_INT	CRASH1_AUDIO_SLOT_ARRAY_SIZE	 				3	// CRASH1_TOTAL_AUDIO_SLOTS+1 (slots numbered 1,2,3: so array pos0 wasted)
CONST_INT	CRASH1_FIRST_SPEECH_SLOT						1
CONST_INT	CRASH1_LAST_SPEECH_SLOT  						2
CONST_INT	CRASH1_SFX_SLOT									3	// NOTE: Not using an engine similar to speech for this because only a few SFX
// ...audio slot status
CONST_INT	CRASH1_AUDIO_SLOT_FREE							0
CONST_INT	CRASH1_AUDIO_SLOT_LOADING						1
CONST_INT	CRASH1_AUDIO_SLOT_LOADED						2
CONST_INT	CRASH1_AUDIO_SLOT_PLAYING						3
// ...conversation status
CONST_INT	CRASH1_CONVERSATION_STATUS_NONE   				0
CONST_INT	CRASH1_CONVERSATION_STATUS_PREPARED				1
CONST_INT	CRASH1_CONVERSATION_STATUS_PLAYING				2
CONST_INT	CRASH1_CONVERSATION_STATUS_INTERRUPTED			3
CONST_INT	CRASH1_CONVERSATION_STATUS_FINISHED				4
// ...speech conversations
CONST_INT	CRASH1_CONVERSATION_NONE						0
CONST_INT	CRASH1_CONVERSATION_COOCHIE_SHOUTS				1
CONST_INT	CRASH1_CONVERSATION_COOCHIE_SCREAMS				2
CONST_INT	CRASH1_CONVERSATION_CARL_HELP					3
//CONST_INT	CRASH1_CONVERSATION_INSIDE_GUY					4
CONST_INT	CRASH1_CONVERSATION_NO_WAY_OUT					5
CONST_INT	CRASH1_CONVERSATION_FIRE_EX						6
CONST_INT	CRASH1_CONVERSATION_I_BE_BACK					7
CONST_INT	CRASH1_CONVERSATION_OUTSIDE_ROOM				8
CONST_INT	CRASH1_CONVERSATION_REACH_COOCHIE				9
CONST_INT	CRASH1_CONVERSATION_BUILDING_COLLAPSE			10
CONST_INT	CRASH1_CONVERSATION_PUT_OUT_FIRE				11
CONST_INT	CRASH1_CONVERSATION_COOCHIE_HERO				12
CONST_INT	CRASH1_CONVERSATION_DRIVE_COOCHIE_HOME			13
CONST_INT	CRASH1_CONVERSATION_ON_RIDE_HOME				14
CONST_INT	CRASH1_CONVERSATION_COOCHIE_HOUSE				15
CONST_INT	CRASH1_CONVERSATION_COOCHIE_NAME				16
CONST_INT	CRASH1_CONVERSATION_GO_OUT						17
CONST_INT	CRASH1_CONVERSATION_CYA							18



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
LVAR_INT		nAudioSlotStatus[CRASH1_AUDIO_SLOT_ARRAY_SIZE]
LVAR_INT		nConversationSequenceSpeechID[CRASH1_MAX_CONVERSATION_LINES]
LVAR_TEXT_LABEL	tlConversationSequenceSubtitle[CRASH1_MAX_CONVERSATION_LINES]
// ...clear audio variables
nSpeechCurrentSlot					= CRASH1_FIRST_SPEECH_SLOT
nConversationStatus					= CRASH1_CONVERSATION_STATUS_NONE
nCurrentConversationID				= CRASH1_CONVERSATION_NONE
nRequiredConversationID				= CRASH1_CONVERSATION_NONE
nCurrentConversationLine			= -1
nCurrentMaxConversationLines		= 0
nNextPreloadConversationLine		= 0
timerDelayBeforeNextConversation	= 0
bitsConversationsPlayed				= CRASH1_CONVERSATION_NONE
flagDisplaySpeechSubtitle			= TRUE

REPEAT CRASH1_AUDIO_SLOT_ARRAY_SIZE nLoop
	nAudioSlotStatus[nLoop] = CRASH1_AUDIO_SLOT_FREE
ENDREPEAT



// Mission Specific Variables
// Integer Variables
// chars
LVAR_INT	charGangOutside[5]
LVAR_INT	charGangBackup[2]
LVAR_INT	charGuyOnFire
LVAR_INT	charCoochie
LVAR_INT	charHouseCower
LVAR_INT	charHouseStairs
// ...cars
LVAR_INT	carBeingWorkedOn
LVAR_INT	carGangBackup
LVAR_INT	carCopCar
// ...objects
LVAR_INT	objectWindow[8]
LVAR_INT	objectTargetRoom[5]
LVAR_INT	objectFrontDoor
LVAR_INT	objectInteriorDoor[4]
LVAR_INT	objectWall[3]
// ...blips
LVAR_INT	blipDestination
LVAR_INT	blipTargetRoom[5]
LVAR_INT	blipMolotovsOnRoute
LVAR_INT	blipMolotovPickups[2]
LVAR_INT	blipCoochie
// ...pickups
LVAR_INT	pickupMolotovsOnRoute
LVAR_INT	pickupMolotovs[2]
LVAR_INT	pickupFireExtinguisher
LVAR_INT	pickupCash[CRASH1_MAX_CASH_PICKUPS]
// ...fires
LVAR_INT	fireGuyOnFire
LVAR_INT	fireCoochie
LVAR_INT	fireHouseCower
LVAR_INT	fireHouseStairs
LVAR_INT	fireInHouse[21]
LVAR_INT	fireInHouseBig[4]
// ...fx systems
LVAR_INT	fxFrontDoor
LVAR_INT	fxPlayerSmoke[9]
LVAR_INT	fxEntranceSmoke
LVAR_INT	fxCoochieWindowSmoke
LVAR_INT	fxDisguiseSmoke
// ...decision makers
LVAR_INT	dmEmpty
LVAR_INT	dmTough
// ...AI Status
LVAR_INT	aiGangOutside[5]
LVAR_INT	aiGangBackup
LVAR_INT	aiHouseGuyInCower
LVAR_INT	aiHouseGuyInStairs
LVAR_INT	aiCoochie
// ...general status
LVAR_INT	statusFires
LVAR_INT	statusBigFires
LVAR_INT	statusWalls[3]
LVAR_INT	statusCoochieExitPath
LVAR_INT	statusSmoke
// ...Timers
LVAR_INT	timerOrientate
LVAR_INT	timerGangBackup
LVAR_INT	timerCutscene
LVAR_INT	timerCoochieAnim
LVAR_INT	timerCoochieBurn
LVAR_INT	timerOutsideDelay
LVAR_INT	timerHelpText
LVAR_INT	timerTargetRoomHitText
LVAR_INT	timerAllowCoochieScreams
LVAR_INT	timerPreloadAudio
LVAR_INT	timerMoreMolotovsRequired
// ...Counter
LVAR_INT	countTargetRoomsHit
LVAR_INT	countNextTempCutscenePosition
// ...mission specific
LVAR_INT	nCurrentCoochieAnim
LVAR_INT	nCashPickupID
// ...useful int variables
LVAR_INT	nLoop
LVAR_INT	nTempInt
LVAR_INT	nTempInt2
LVAR_INT	nTempPed
LVAR_INT	nTempObject
LVAR_INT	nTempAmmo
LVAR_INT	nTempTimer
LVAR_INT	nSeqTask
LVAR_INT	nIgnore


// Float Variables
// ...area variables
LVAR_FLOAT	xloAreaHitTargetRoom[5]
LVAR_FLOAT	xhiAreaHitTargetRoom[5]
LVAR_FLOAT	yloAreaHitTargetRoom[5]
LVAR_FLOAT	yhiAreaHitTargetRoom[5]
LVAR_FLOAT	zloAreaHitTargetRoom[5]
LVAR_FLOAT	zhiAreaHitTargetRoom[5]
// ...position variables
LVAR_FLOAT	xposGangBackup			yposGangBackup			zposGangBackup
LVAR_FLOAT	xposFireEx				yposFireEx				zposFireEx
LVAR_FLOAT	xposCoochieHome			yposCoochieHome			zposCoochieHome
LVAR_FLOAT	xposGangOutsideHold[5]	yposGangOutsideHold[5]	zposGangOutsideHold[5]
LVAR_FLOAT	xposGangBackupHold[2]	yposGangBackupHold[2]	zposGangBackupHold[2]
// ...useful float variables
LVAR_FLOAT	xposTemp		yposTemp		zposTemp		headTemp
LVAR_FLOAT	xposTemp2		yposTemp2		zposTemp2
LVAR_FLOAT	xloTemp			xhiTemp			yloTemp			yhiTemp			zloTemp			zhiTemp
LVAR_FLOAT	fTempFloat
LVAR_FLOAT	fTempDistance


// Boolean Variables
// ...flags
LVAR_INT	flagGangOutsidePlayerDetected
LVAR_INT	flagGangOutsideCallForHelp
LVAR_INT	flagGangOutsideInjuredOrDead
LVAR_INT	flagOrientate
LVAR_INT	flagHouseBurning
LVAR_INT	flagTargetRoomHit[5]
LVAR_INT	flagTargetRoomBurning[5]
LVAR_INT	flagMolotovsOnRouteActive
LVAR_INT	flagMolotovPickupsActive
LVAR_INT	flagInHouse
LVAR_INT	flagBeenInHouse
LVAR_INT	flagLeavingHouse
LVAR_INT	flagFireExtinguisherPickupActive
LVAR_INT	flagFireInHouseBigStillAlight[4]
LVAR_INT	flagWarpCoochieOutside
LVAR_INT	flagCheckingForCoochie
LVAR_INT	flagBlipAtTopOfStairs
LVAR_INT	flagPlayerInCar
LVAR_INT	flagCoochieHome
LVAR_INT	flagFireExHelpDisplayed
LVAR_INT	flagPotentialWalkThroughFire
LVAR_INT	flagEnterHouseCutscenePlaying
LVAR_INT	flagFireExtinguisherCutscenePlaying
LVAR_INT	flagKissingCutscenePlaying
LVAR_INT	flagProjectileAroundHouse
LVAR_INT	flagFireAroundHouse
LVAR_INT	flagCutsceneFrontDoorStuffDone
LVAR_INT	flagCutsceneGirlSmokeCreated
LVAR_INT	flagCoochieLeftBehind
LVAR_INT	flagCoochieBurnsCounterActive
LVAR_INT	flagDisplayGoBackMessage_WaitForStartFade
LVAR_INT	flagDisplayGoBackMessage_WaitForEndFade
LVAR_INT	flagGirlIsToast
LVAR_INT	flagAllowCoochieScreams
LVAR_INT	flagPrepareAndPlayPutOutFireSpeech
LVAR_INT	flagCashCreated[CRASH1_MAX_CASH_PICKUPS]
LVAR_INT	flagMoreMolotovsRequired
LVAR_INT	flagBedroomFireStillBlazing
LVAR_INT	flagClearFlamesAroundGirl
LVAR_INT	flagNearHouse
LVAR_INT	flagDisplayedMolotovHelpText
LVAR_INT	flagPlayOnlyCoochieLoopingAnim
// ...useful flag variables
LVAR_INT	flagTempFlag
LVAR_INT	flagCutscenePlaying
LVAR_INT	flagCleaningUpSkippedCutscene
LVAR_INT	flagSkipCutscene

// ...exists flags
LVAR_INT	existsGangOutside
LVAR_INT	existsCarBeingWorkedOn
LVAR_INT	existsGangBackup
LVAR_INT	existsGuyOnFire
LVAR_INT	existsCoochie
LVAR_INT	existsHouseGuysIn
LVAR_INT	existsWalls
//...clear exists flags
existsGangOutside		= 0
existsCarBeingWorkedOn	= 0
existsGangBackup		= 0
existsGuyOnFire			= 0
existsCoochie			= 0
existsHouseGuysIn		= 0
existsWalls				= 0


// Clear timers
timerOrientate			= 0


// Clear important flags
flagInHouse							= FALSE
flagBeenInHouse						= FALSE
flagLeavingHouse					= FALSE
flagCheckingForCoochie				= FALSE
flagWarpCoochieOutside				= FALSE
flagMolotovPickupsActive			= FALSE
flagFireExtinguisherPickupActive	= FALSE
flagPlayerInCar						= FALSE
flagCleaningUpSkippedCutscene		= FALSE
flagSkipCutscene					= FALSE
flagCoochieLeftBehind				= FALSE
flagGirlIsToast						= FALSE
flagPrepareAndPlayPutOutFireSpeech	= FALSE
flagMoreMolotovsRequired			= FALSE
flagBedroomFireStillBlazing			= TRUE
flagClearFlamesAroundGirl			= TRUE
flagNearHouse						= FALSE
flagDisplayedMolotovHelpText		= FALSE
flagPlayOnlyCoochieLoopingAnim		= FALSE


// Other stuff
countNextTempCutscenePosition		= 0

// ***** FAKE ENTITY CREATION TO FOOL THE COMPILER *****
// The compiler just needs to verify there is a CREATE_ before usage
IF m_stage = -99
	
	WRITE_DEBUG SHOULD_NEVER_BE_IN_FAKE_ENTITY_CREATION

	// Cars
	// ...car being worked on
	CREATE_CAR VOODOO	0.0 0.0 0.0 carBeingWorkedOn
	CREATE_CAR VOODOO	0.0 0.0 0.0 carGangBackup
	CREATE_CAR COPCARLA	0.0 0.0 0.0 carCopCar

	// Peds
	// ...gang outside
	REPEAT 5 nLoop
		CREATE_CHAR PEDTYPE_GANG_SMEX LSV3 0.0 0.0 0.0 charGangOutside[nLoop]
	ENDREPEAT

	// ...gang backup
	REPEAT 2 nLoop
		CREATE_CHAR PEDTYPE_GANG_SMEX LSV3 0.0 0.0 0.0 charGangBackup[nLoop]
	ENDREPEAT

	// ...guy on fire
	CREATE_CHAR PEDTYPE_GANG_SMEX LSV2 0.0 0.0 0.0 charGuyOnFire

	// ...coochie
	CREATE_CHAR PEDTYPE_CIVFEMALE GANGRL3 0.0 0.0 0.0 charCoochie

	// ...house guys on way in
	CREATE_CHAR PEDTYPE_GANG_SMEX LSV2 0.0 0.0 0.0 charHouseCower
	CREATE_CHAR PEDTYPE_GANG_SMEX LSV2 0.0 0.0 0.0 charHouseStairs

	// Objects
	// ...windows
	REPEAT 8 nLoop
		CREATE_OBJECT_NO_OFFSET bd_window 0.0 0.0 0.0 objectWindow[nLoop]
	ENDREPEAT

	// ...front door
	CREATE_OBJECT cr1_door 0.0 0.0 0.0 objectFrontDoor

	// ...walls
	CREATE_OBJECT BREAK_WALL_1B 0.0 0.0 0.0 objectWall[0]
	CREATE_OBJECT BREAK_WALL_2B 0.0 0.0 0.0 objectWall[1]
	CREATE_OBJECT BREAK_WALL_3B 0.0 0.0 0.0 objectWall[2]
	CREATE_OBJECT BD_FIRE1_O 0.0 0.0 0.0 objectWall[0]

	// Fires
	START_CHAR_FIRE charGuyOnFire fireGuyOnFire	   
	START_CHAR_FIRE charCoochie fireCoochie
	START_CHAR_FIRE charHouseCower fireHouseCower
	START_CHAR_FIRE charHouseStairs fireHouseStairs

	REPEAT 21 nLoop
		START_SCRIPT_FIRE 0.0 0.0 0.0 0 1 fireInHouse[nLoop]
	ENDREPEAT

	REPEAT 4 nLoop
		START_SCRIPT_FIRE 0.0 0.0 0.0 0 1 fireInHouseBig[nLoop]
	ENDREPEAT

	// Pickups
	CREATE_PICKUP_WITH_AMMO FIRE_EX PICKUP_ONCE 0 0.0 0.0 0.0 pickupFireExtinguisher
	CREATE_PICKUP_WITH_AMMO MOLOTOV PICKUP_ONCE 0 0.0 0.0 0.0 pickupMolotovsOnRoute

	// Blips									  
 	ADD_BLIP_FOR_CHAR charCoochie blipCoochie
	ADD_BLIP_FOR_PICKUP pickupMolotovsOnRoute blipMolotovsOnRoute

ENDIF



// Mission Text
LOAD_MISSION_TEXT CRASH1


// Intro Cutscene
GOSUB Crash1_Intro_Cutscene


// Load Char Mission Decision Makers
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY dmEmpty
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH dmTough


// Mission Initialisation
GOSUB Crash1_Initialisation






// *************************************************************************************************************
//								                 MISSION LOOP
// *************************************************************************************************************
mission_loop_Crash1:

	WAIT 0


	// Special shortcut for Craig F to test all missions in order
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		m_passed = 1
		GOTO end_of_main_loop_Crash1
	ENDIF



	// Debug Stuff
	GOSUB Crash1_Debug_Tools
	GOSUB Crash1_Debug_Shortcuts

	IF m_quit = 1
	OR m_pause = 1
		GOTO end_of_main_loop_Crash1
	ENDIF


	// Housekeeping
	GOSUB Crash1_Frame_Counter
	GOSUB Crash1_Additional_Timers


	// Special conditions
	IF IS_CHAR_DEAD scplayer
		m_failed = 1
		GOTO end_of_main_loop_Crash1
	ENDIF		


	// Mission Stage processing
	// *** INITIALISATION NOW TAKES PLACE BEFORE THE MAIN LOOP ***
	IF m_stage = 0
		WRITE_DEBUG STAGE_SHOULD_NEVER_BE_0_IN_MAIN_LOOP
	ENDIF


	// ...Stage 1: Go to the House
	IF m_stage = 1
		GOSUB Crash1_Stage_GoToHouse
	ENDIF 


	// ...Stage 2: Torch the House
	IF m_stage = 2
		GOSUB Crash1_Stage_TorchHouse
	ENDIF


	// ...Stage 3: Go into the House
	IF m_stage = 3
		GOSUB Crash1_Stage_GoIntoHouse
	ENDIF


	// ...Stage 4: Reach the lady
	IF m_stage = 4
		GOSUB Crash1_Stage_ReachTheLady
	ENDIF


	// ...Stage 5: Collapsing wall cutscene
	IF m_stage = 5
		GOSUB Crash1_Stage_CollapsingWall
	ENDIF


	// ...Stage 6: Get the lady out
	IF m_stage = 6
		GOSUB Crash1_Stage_GetTheLadyOut
	ENDIF


	// ...Stage 7: Take the Lady home
	IF m_stage = 7
		GOSUB Crash1_Stage_TakeTheLadyHome
	ENDIF


	// ...final stage
	IF m_stage = 8
		m_passed = 1
	ENDIF


	// ...special 'walk out of house after mission failure' cutscene
	IF m_stage = 50
		GOSUB Crash1_Stage_FailedInHouse
	ENDIF

	IF m_stage = 51
		// ...quit to cleanup without displaying 'failed' text again
		m_quit = TRUE
	ENDIF


	// Continuous update methods and event checking
	// --------------------------------------------

	// ...special timers
	GOSUB Crash1_UpdateTimer_OrientateTimer

	// ...special flags
	GOSUB Crash1_CheckIf_In_House

	// ...speech
	GOSUB Crash1_Update_Speech




// End of Main Loop
// ***** DON'T CHANGE *****
end_of_main_loop_Crash1:

	IF m_quit = 0
		IF m_failed = 0
			IF m_passed = 0																 
				// Restart main loop
				GOTO mission_loop_Crash1 
			ELSE
				// Mission passed
				GOSUB mission_passed_Crash1
				RETURN
			ENDIF
		ELSE
			// Mission failed
			GOSUB mission_failed_Crash1

			// If the player is in the house then play the 'exit house' cutscene, otherwise just quit
			// NOTE: Also play the cutscene if the player is in the corridor behind the entry/exit
			IF flagInHouse = TRUE
			OR IS_CHAR_IN_AREA_3D scplayer 2350.4048 -1181.9069 26.8553 2353.4836 -1170.5240 30.0687 FALSE
				m_stage = 50
			   	m_goals = 0
				m_failed = FALSE
				GOTO mission_loop_Crash1 
			ENDIF

			RETURN
		ENDIF
	ELSE
		RETURN // quits out - goes to cleanup
	ENDIF

RETURN	// Should never reach here




// *************************************************************************************************************
// 												MISSION STAGE GOSUBS   
// *************************************************************************************************************

// ****************************************
// STAGE 1: Get to the House

Crash1_Stage_GoToHouse:

   	// Initialisation for this stage
   	IF m_goals = 0
		// INSTRUCTIONS: Go pick up some molotovs
		PRINT_NOW CRA1_00 15000 1

		// Generate molotov pickups and blip
		GOSUB Crash1_Create_MolotovsOnRoute

		flagDisplayedMolotovHelpText = FALSE
		timerHelpText = 0

		m_goals++
	ENDIF


	// Arrival at the Molotov pickup point
	IF m_goals = 1
		// Check if player has arrived at molotov pickup point
		IF HAS_PICKUP_BEEN_COLLECTED pickupMolotovsOnRoute
			REMOVE_BLIP blipMolotovsOnRoute

			flagDisplayedMolotovHelpText = FALSE
			timerHelpText = m_mission_timer +5000

			m_goals++
		ENDIF
	ENDIF


	// Cutscene? showing player being given molotovs
	// ***** THERE'S NOT GOING TO BE A CUTSCENE *****
	IF m_goals = 2
		m_goals++
	ENDIF


	// Instructions for next stage
	IF m_goals = 3
		// Instructions: Go to the gang house and torch it.
		PRINT_NOW CRA1_01 5000 1

		// Display blip on Gang house
		ADD_BLIP_FOR_COORD 2351.9038 -1170.8260 28.5 blipDestination

		m_goals++
	ENDIF


	// Place the outside guys and animate them
	IF m_goals = 4
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2350.1685 -1164.9385 300.0 300.0 FALSE
			GOSUB Crash1_Create_Car_Being_Worked_On
			GOSUB Crash1_Create_Outside_Gang_Members
			GOSUB Crash1_Create_Windows
			GOSUB Crash1_Create_Front_Door

			m_goals++
		ENDIF
	ENDIF

		
	// Arrival at the House
	IF m_goals = 5
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2337.6660 -1180.9938 31.7513 38.1099 31.5599 8.7500 FALSE

			// Arrived at destination
			REMOVE_BLIP blipDestination

			// Make sure there are no Peds or cars to interfere
			SET_PED_DENSITY_MULTIPLIER 0.0
			SET_CAR_DENSITY_MULTIPLIER 0.0
			flagNearHouse = TRUE

			// Stage complete
			m_goals = 99
		ENDIF

	ENDIF	


	// Continuous updates
	// ------------------

	// Display molotov help
	IF flagDisplayedMolotovHelpText = FALSE
		IF NOT timerHelpText = 0
			IF timerHelpText < m_mission_timer
				PRINT_HELP MOLOTOV  
				flagDisplayedMolotovHelpText = TRUE
			ENDIF
		ENDIF
	ENDIF


	// exit
	IF m_goals = 99
		GOSUB Crash1_next_stage
	ENDIF

RETURN


// ****************************************
// STAGE 2: Torch the house

Crash1_Stage_TorchHouse:

   	// Initialisation for this stage
	IF m_goals = 0
		flagGangOutsidePlayerDetected	= 0
		flagGangOutsideCallForHelp		= 0
		flagGangOutsideInjuredOrDead	= 0
		flagOrientate					= 0
		flagHouseBurning				= 0
		flagMolotovPickupsActive		= 0
		flagProjectileAroundHouse		= 0
		flagFireAroundHouse				= 0
		countTargetRoomsHit				= 0
		aiGangBackup					= -1
		timerTargetRoomHitText			= 0

		// Set up the Target Rooms stuff
		GOSUB Crash1_Setup_Target_Rooms
		GOSUB Crash1_Setup_Target_Room_Hit_Areas

		// Switch off emergency services
		SWITCH_EMERGENCY_SERVICES OFF

		// Slow down the wanted multipler
		SET_WANTED_MULTIPLIER 0.3

		// Make sure the fires don't spread too much
		SET_MAX_FIRE_GENERATIONS 1

		m_goals++
	ENDIF

		
	// Help text explaining breaking the windows and molotov use
	IF m_goals = 1
		// Instructions: Torch the House
		PRINT_NOW CRA1_02 6000 1

		timerHelpText = m_mission_timer + 7000

		m_goals++
	
	ENDIF


	// Check if it is time to display help text for how to break windows
	IF m_goals = 2
		REPEAT 5 nLoop
			nTempObject = nLoop
			GOSUB Crash1_Update_Target_Rooms_Hit
		ENDREPEAT

		IF timerHelpText < m_mission_timer
			PRINT_HELP WINDOWS

			m_goals++
		ENDIF
	ENDIF


	// Check if all rooms of house are torched
	IF m_goals = 3
		REPEAT 5 nLoop
			nTempObject = nLoop
			GOSUB Crash1_Update_Target_Rooms_Hit
		ENDREPEAT

		// If all rooms hit, goto next stage
		IF countTargetRoomsHit = 5
			// Display a message saying house on fire
			PRINT_NOW CRA1_04 3000 1

			m_goals = 99
		ENDIF
	ENDIF


	// Continuous Updates
	// ...GangOutside's AI
	REPEAT 5 nLoop
  	nTempPed = nLoop
		GOSUB Crash1_Update_GangOutside_AI
	ENDREPEAT


	// ...molotov pickups
	IF flagMolotovPickupsActive = 1
		// ...molotov pickups are active, so check if they have been picked up
		GOSUB Crash1_CheckIf_Molotov_Pickups_Collected
	ELSE
		// ...molotov pickups are not active, so check if they should be created
		GOSUB Crash1_CheckIf_Molotov_Pickups_Required
	ENDIF


	// ...GangBackup
	GOSUB Crash1_Update_GangBackup


	// Target Rooms Hit Timer
	IF NOT timerTargetRoomHitText = 0
		IF timerTargetRoomHitText < m_mission_timer
			// Reset the timer to ensure this doesn't get entered again until next window
			timerTargetRoomHitText = 0

			// Recalculate number of rooms hit
			nTempInt = 0
			REPEAT 5 nLoop
				IF flagTargetRoomHit[nLoop] = 1
					nTempInt++
				ENDIF
			ENDREPEAT

			// PROBABLY OVERKILL: Unlikely to happen in practice, but just in case...
			// Make sure two rooms haven't been torched in very quick succession
			// IF they have, then display the message twice but ensure that the
			//		countTargetRoomsHit flag increases only by 1 each time because
			//		some events are triggered when countTargetRoomsHit reach a certain
			//		amount
			countTargetRoomsHit++
			IF NOT nTempInt = countTargetRoomsHit
				// ...problem somewhere
				IF nTempInt < countTargetRoomsHit
					// if nTempInt is less than the counter, then make the counter = nTempInt
					//		because nTempInt has just been re-calculated using the latest data
					// NOTE: It should NEVER get in here, but just in case
					countTargetRoomsHit = nTempInt
				ELSE
					// if nTempInt is greater than the counter then more than one window has been
					//		smashed in ultra quick succession, so restart the timer for the next
					//		window
					// NOTE: Highly unlikely to ever get in here, but just in case
					timerTargetRoomHitText = m_mission_timer + 500
				ENDIF
			ENDIF

			// Print target room hit text
			IF countTargetRoomsHit < 5
				GOSUB Crash1_Print_Random_RoomHit_Text
			ENDIF

			// Start the scripted fires in this room
			flagTempFlag = FALSE
			REPEAT 5 nLoop
				IF flagTempFlag = FALSE
					IF flagTargetRoomHit[nLoop] = TRUE
					AND flagTargetRoomBurning[nLoop] = FALSE
						statusFires = nLoop
						GOSUB Crash1_Update_Fires_Exterior
						flagTargetRoomBurning[nLoop] = TRUE
						flagTempFlag = TRUE
					ENDIF
				ENDIF
			ENDREPEAT
		ENDIF
	ENDIF


	// If player not detected. check for a projectile or a fire in the area
	IF flagGangOutsidePlayerDetected = 0
		xloTemp = 2337.6660		- 31.000
		yloTemp = -1180.9938	- 38.000
		zloTemp = 31.7513		- 8.000
		xhiTemp = 2337.6660		+ 31.000
		yhiTemp = -1180.9938	+ 38.000
		zhiTemp = 31.7513		+ 8.000

		// ...check for projectile in area
		IF flagProjectileAroundHouse = 0
			IF IS_PROJECTILE_IN_AREA xloTemp yloTemp zloTemp xhiTemp yhiTemp zhiTemp
				flagProjectileAroundHouse = 1
			ENDIF
		ENDIF

		IF flagFireAroundHouse = 0
			GET_NUMBER_OF_FIRES_IN_AREA xloTemp yloTemp zloTemp xhiTemp yhiTemp zhiTemp nTempInt
			IF nTempInt > 0
				flagFireAroundHouse = 1
			ENDIF
		ENDIF
	ENDIF


	// If the player moves a distance away from the house, restart the traffix and the peds
	IF flagNearHouse = TRUE
		// ...currently near the house
		IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2337.6660 -1180.9938 31.7513 48.1099 41.5599 18.7500 FALSE
			// ...away from house again
			SET_PED_DENSITY_MULTIPLIER 1.0
			SET_CAR_DENSITY_MULTIPLIER 1.0
			flagNearHouse = FALSE
		ENDIF
	ELSE
		// ...not currently near the house
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2337.6660 -1180.9938 31.7513 38.1099 31.5599 8.7500 FALSE
			// ...near house again
			SET_PED_DENSITY_MULTIPLIER 0.0
			SET_CAR_DENSITY_MULTIPLIER 0.0
			flagNearHouse = TRUE
		ENDIF
	ENDIF


	// exit
	IF m_goals = 99
		GOSUB Crash1_next_stage
	ENDIF

RETURN


// ****************************************
// STAGE 3: Go into the house

Crash1_Stage_GoIntoHouse:

   	// Initialisation for this stage
	IF m_goals = 0
		// Display the fires
		statusFires = CRASH1_EXTERIOR_FIRE_ALL
		GOSUB Crash1_Update_Fires_Exterior

		// Activate the big fire in the girl's room
		statusBigFires = 0
		GOSUB Crash1_Update_Big_Fires

		timerCutscene = m_mission_timer + 3000
		flagEnterHouseCutscenePlaying	= 0
		flagCutsceneFrontDoorStuffDone	= 0
		flagCutsceneGirlSmokeCreated	= 0

		flagCutscenePlaying				= 0
		flagCleaningUpSkippedCutscene	= 0
		flagSkipCutscene				= 0

		flagCoochieBurnsCounterActive	= FALSE
		flagClearFlamesAroundGirl		= TRUE

		timerAllowCoochieScreams		= 0

		// Switch off the wanted multipler
		SET_WANTED_MULTIPLIER 0.0
		CLEAR_WANTED_LEVEL player1

		// Load the door break audio
		LOAD_MISSION_AUDIO CRASH1_SFX_SLOT SOUND_WOODEN_DOOR_BREACH

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------

	// Check if coochie dead
	IF existsCoochie = TRUE
		IF IS_CHAR_DEAD charCoochie
			m_failed = 1
			RETURN
		ENDIF
	ENDIF


	// Pause before fade out
	IF m_goals = 1
		IF timerCutscene < m_mission_timer
			DO_FADE 400 FADE_OUT
			SET_PLAYER_CONTROL player1 OFF

			timerCutscene = m_mission_timer + 2500

			GOSUB Crash1_Create_Guy_On_Fire
			GOSUB Crash1_Create_Coochie

			flagAllowCoochieScreams = FALSE

			// Prepare the Coochie speech for the cutscene
			nRequiredConversationID = CRASH1_CONVERSATION_COOCHIE_SHOUTS
			GOSUB Crash1_Conversation_Command_Prepare

			m_goals++
		ENDIF
	ENDIF

		
	// Pause before fade in
	IF m_goals = 2
		IF flagSkipCutscene = 1
			m_goals++
		ELSE
			IF timerCutscene < m_mission_timer
				GOSUB Crash1_Enter_House_Cutscene_Begin
				flagEnterHouseCutscenePlaying = 1
				flagCutscenePlaying = 1
				m_goals++
			ENDIF
		ENDIF
	ENDIF

		
	// Pause, then trigger guy on fire running out of house
	IF m_goals = 3
		IF flagSkipCutscene = 1
			m_goals++
		ELSE
			IF timerCutscene < m_mission_timer
			AND HAS_MISSION_AUDIO_LOADED CRASH1_SFX_SLOT
				GOSUB Crash1_Enter_House_Cutscene_Guy_On_Fire
				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Wait for guy to die (but ensure the cut lasts a certain time), then continue with cutscene
	IF m_goals = 4
		IF flagSkipCutscene = 1
			m_goals++
		ELSE
			IF IS_CHAR_DEAD charGuyOnFire
			AND timerCutscene < m_mission_timer
				GOSUB Crash1_Enter_House_Cutscene_Coochie
				m_goals++
			ENDIF

			// The guy is refusing to die, so wait a bit longer before continuing
			nTempTimer = timerCutscene + 8000
			IF NOT IS_CHAR_DEAD charGuyOnFire
			AND nTempTimer < m_mission_timer
				GOSUB Crash1_Enter_House_Cutscene_Coochie
				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Wait briefly before shattering the window
	IF m_goals = 5
		IF flagSkipCutscene = 1
			m_goals++
		ELSE
			IF timerCutscene < m_mission_timer
				IF DOES_OBJECT_EXIST objectWindow[5]	//girls window
					IF NOT HAS_OBJECT_BEEN_DAMAGED objectWindow[5]
					   	BREAK_OBJECT objectWindow[5] TRUE
					ENDIF
				ENDIF

				// Create Coochie Window smoke
				statusSmoke = 4
				GOSUB Crash1_Update_Smoke
				flagCutsceneGirlSmokeCreated = 1

				// Trigger the girl's speech
				// In this instance, overwrite the speech subtitle with the info text below
				flagDisplaySpeechSubtitle = FALSE
				GOSUB Crash1_Conversation_Command_Play

				// Info:You've trapped a girl
				PRINT_NOW CRA1_05 4600 1

				timerCutscene = m_mission_timer + 4300
				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Need to let the cutscene fade out to hide the guys being teleported back into position
	IF m_goals = 6
		IF flagSkipCutscene = 1
			m_goals++
		ELSE
			IF timerCutscene < m_mission_timer
				DO_FADE 300 FADE_OUT
				timerCutscene = m_mission_timer + 1000

				// Move the Gang Outside and Gang Backup guys back to their original positions
				GOSUB Crash1_GangOutside_Move_Back_After_Cutscene
				GOSUB Crash1_GangBackup_Move_Back_After_Cutscene

				m_goals++
			ENDIF
		ENDIF
	ENDIF



	// Show Coochie for a short period of time then quit the cutscene
	IF m_goals = 7
		IF flagSkipCutscene = 1
			m_goals++
		ELSE
			IF timerCutscene < m_mission_timer
				GOSUB Crash1_Enter_House_Cutscene_Quit

				// Add a blip at the front door
				xposTemp = 2352.0371
				yposTemp = -1170.3175
				zposTemp = 26.9702

				ADD_BLIP_FOR_COORD xposTemp yposTemp zposTemp blipDestination

				// Remove big fire at Front Door
				statusBigFires = 2
				GOSUB Crash1_Update_Big_Fires

				DO_FADE 200 FADE_IN
				timerCutscene = m_mission_timer + 200

				m_goals++
			ENDIF
		ENDIF
	ENDIF

	
	// Check if the fade in has completed
	IF m_goals = 8
		IF flagSkipCutscene = 1
			m_goals = 50
		ELSE
			IF timerCutscene < m_mission_timer
				flagEnterHouseCutscenePlaying = 0
				flagCutscenePlaying = 0
				SET_PLAYER_CONTROL player1 ON

				GOSUB Crash1_Activate_Coochie_Burns_Counter

				// NOTE: The above gosub triggers a 5000msec message followed by the 4000msec message below = 9000msec
				timerAllowCoochieScreams = m_mission_timer + 9000

				// Enter the house
				PRINT CRA1_06 4000 1

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Wait for the player to enter the house
	IF m_goals = 9
		IF flagInHouse = 1
			// Remove blip at front door and add it on girl
			REMOVE_BLIP blipDestination

			// Don't allow Coochie to scream and shout any more
			flagAllowCoochieScreams		= FALSE
			timerAllowCoochieScreams	= 0

			IF NOT IS_CHAR_DEAD	charCoochie
				// Interior now 1000m up in the air, so move Coochie
				// Indicates that the character was created in an interior
				SET_CHAR_HAS_USED_ENTRY_EXIT charCoochie 2352.1143 -1170.6102 8.0

				// NOTE: She ends up a meter too high for some reason, so take this into account
				GET_CHAR_COORDINATES charCoochie xposTemp yposTemp zposTemp
				zposTemp += 999.0
				SET_CHAR_COORDINATES charCoochie xposTemp yposTemp zposTemp
				ADD_BLIP_FOR_CHAR charCoochie blipCoochie
				SET_BLIP_AS_FRIENDLY blipCoochie TRUE
			ENDIF

			REMOVE_SCRIPT_FIRE fireGuyOnFire

			// Move the Girl's Bedroom fire to the doorway
			statusBigFires = 4
			GOSUB Crash1_Update_Big_Fires

			// Move the entrance smoke so it doesn't obsure the view a sthe player leaves
			statusSmoke = 6
			GOSUB Crash1_Update_Smoke

			// Create stuff only when the player is in the house (to avoid them being seen when you torch a room)
			GOSUB Crash1_Setup_Fire_Extinguisher
			GOSUB Crash1_Create_Interior_Doors
			GOSUB Crash1_Create_Walls		

			m_goals = 99
		ELSE
			// ...not in house so keep the action going
			// Update GangOutside's AI
			REPEAT 5 nLoop
  			nTempPed = nLoop
				GOSUB Crash1_Update_GangOutside_AI
			ENDREPEAT

			// Update GangBackup
			GOSUB Crash1_Update_GangBackup

		ENDIF
	ENDIF


	// Skipped Cutscene stuff
	// ----------------------

	// Check if the cutscene was skipped
	// NOTE: Should never enter here unless skipped
	
	IF flagCutscenePlaying = 1
	AND IS_SKIP_CUTSCENE_BUTTON_PRESSED
	AND flagSkipCutscene = 0
		flagSkipCutscene = 1
	ENDIF

	// fade out
	IF m_goals = 50
		DO_FADE 500 FADE_OUT
		timerCutscene = m_mission_timer + 500
		flagCleaningUpSkippedCutscene = 1

		m_goals++	
	ENDIF


	// Clean up the skipped cutscene
	IF m_goals = 51
		IF timerCutscene < m_mission_timer
			GOSUB Crash1_Cutscene_Skipped_EnterHouse
			DO_FADE 1500 FADE_IN
			timerCutscene = m_mission_timer + 1500

			m_goals++
		ENDIF
	ENDIF


	// Resume normal service
	IF m_goals = 52
		IF timerCutscene < m_mission_timer
			flagCleaningUpSkippedCutscene = 0
			flagSkipCutscene = 0
			flagEnterHouseCutscenePlaying = 0
			flagCutscenePlaying = 0
			SET_PLAYER_CONTROL player1 ON

			GOSUB Crash1_Activate_Coochie_Burns_Counter

			// NOTE: The above gosub triggers a 5000msec message followed by the 4000msec message below = 9000msec
			timerAllowCoochieScreams = m_mission_timer + 9000

			// Enter the house
			PRINT CRA1_06 4000 1

			m_goals = 9
		ENDIF
	ENDIF


	// Continuous Updates
	// -------------------

	GOSUB Crash1_Update_Coochie_Panic_Anims


	// ...GangOutside's AI
	IF flagCleaningUpSkippedCutscene = 0
		REPEAT 5 nLoop
  		nTempPed = nLoop
			GOSUB Crash1_Update_GangOutside_AI
		ENDREPEAT
	ENDIF


	// ...GangBackup
	IF flagCleaningUpSkippedCutscene = 0
		GOSUB Crash1_Update_GangBackup
	ENDIF


	// Coochie Burns Timer
	IF flagCoochieBurnsCounterActive = TRUE
		GOSUB Crash1_Update_Coochie_Burn_Time
	ENDIF


	// Allow Coochie Screams timer
	IF timerAllowCoochieScreams > 0
		IF timerAllowCoochieScreams < m_mission_timer
			timerAllowCoochieScreams = 0
			flagAllowCoochieScreams = TRUE
		ENDIF
	ENDIF


	// Continuously clear the area around the girl
	IF flagClearFlamesAroundGirl = TRUE
		CLEAR_AREA 2345.9072 -1171.6591 30.9688 1.5 FALSE
		CLEAR_AREA 2345.9072 -1171.6591 1030.9688 1.5 FALSE
	ENDIF


	// Check if the player should issue 'encouragement' speech
	IF NOT IS_BIT_SET bitsConversationsPlayed CRASH1_CONVERSATION_CARL_HELP
	AND flagAllowCoochieScreams = TRUE
		IF IS_CHAR_IN_AREA_3D scplayer 2340.2625 -1170.5386 24.9859 2351.0952 -1164.2870 28.2183 FALSE
			// Make sure the girl isn't issuing speech
			IF nCurrentConversationID = CRASH1_CONVERSATION_COOCHIE_SCREAMS
			OR nCurrentConversationID = CRASH1_CONVERSATION_COOCHIE_SHOUTS
				// ...the girl is issuing screams, so check if they are being played
				IF nConversationStatus = CRASH1_CONVERSATION_STATUS_PLAYING
					// ...they are being played, so don't interrupt
					RETURN
				ENDIF

				// The screams must only be prepared, so cancel them
				GOSUB Crash1_Conversation_Command_Cancel
			ENDIF

			// Prepare or play Carl's speech
			IF nCurrentConversationID = CRASH1_CONVERSATION_NONE
				// ...prepare the speech
				nRequiredConversationID =  CRASH1_CONVERSATION_CARL_HELP
				GOSUB Crash1_Conversation_Command_Prepare
				RETURN
			ENDIF

			IF nCurrentConversationID = CRASH1_CONVERSATION_CARL_HELP
			AND NOT nConversationStatus = CRASH1_CONVERSATION_STATUS_PLAYING
				// ...play the speech
				GOSUB Crash1_Conversation_Command_Play
			ENDIF
		ENDIF
	ENDIF


	// If the player moves a distance away from the house, restart the traffix and the peds
	IF flagNearHouse = TRUE
		// ...currently near the house
		IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2337.6660 -1180.9938 31.7513 48.1099 41.5599 18.7500 FALSE
			// ...away from house again
			SET_PED_DENSITY_MULTIPLIER 1.0
			SET_CAR_DENSITY_MULTIPLIER 1.0
			flagNearHouse = FALSE
		ENDIF
	ELSE
		// ...not currently near the house
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2337.6660 -1180.9938 31.7513 38.1099 31.5599 8.7500 FALSE
			// ...near house again
			SET_PED_DENSITY_MULTIPLIER 0.0
			SET_CAR_DENSITY_MULTIPLIER 0.0
			flagNearHouse = TRUE
		ENDIF
	ENDIF


	// exit
	IF m_goals = 99
		GOSUB Crash1_next_stage
	ENDIF

RETURN


// ****************************************
// STAGE 4: Reach the Lady

Crash1_Stage_ReachTheLady:

   	// Initialisation for this stage
	IF m_goals = 0
		// Create House Guys for way in
		GOSUB Crash1_Create_HouseGuys_In

		flagFireExHelpDisplayed = 0
		flagFireExtinguisherCutscenePlaying = 0

		flagCutscenePlaying				= 0
		flagCleaningUpSkippedCutscene	= 0
		flagSkipCutscene				= 0

		flagDisplayGoBackMessage_WaitForStartFade	= FALSE
		flagDisplayGoBackMessage_WaitForEndFade		= FALSE
		flagPlayOnlyCoochieLoopingAnim				= FALSE
		
		aiCoochie = -1

		// Don't need the ammo boxes any more
		MARK_MODEL_AS_NO_LONGER_NEEDED CR_AMMOBOX

		m_goals++
	ENDIF


	// Check if coochie dead
	IF IS_CHAR_DEAD charCoochie
		m_failed = 1
		RETURN
	ENDIF


	// Code within this goal removed, so just move on to the next goal
	IF m_goals = 1
		m_goals++
	ENDIF


	// Check if player outside Coochie Room
	IF m_goals = 2
		xloTemp = 2344.7510		- 1.5000
		yloTemp = -1181.1611	- 1.5000
		zloTemp = 1030.9610		- 0.5000
		xhiTemp = 2344.7510		+ 1.5000
		yhiTemp = -1181.1611	+ 1.5000
		zhiTemp = 1030.9610		+ 1.5000

		IF IS_CHAR_IN_AREA_3D scplayer xloTemp yloTemp zloTemp xhiTemp yhiTemp zhiTemp FALSE
			// Remove blip from girl and place on extinguisher
			// NOTE: If the fire extinguisher has been collected this will immediately be cleared up
			REMOVE_BLIP blipCoochie

			// Stop clearing the flames around Coochie and remove her flame proofs, just in case the player molotovs her
			flagClearFlamesAroundGirl = FALSE
			SET_CHAR_PROOFS charCoochie FALSE FALSE FALSE FALSE FALSE

			// From now on, only play the Coochie looping anim
			flagPlayOnlyCoochieLoopingAnim = TRUE

			// Initiate cutscene if fire extinguisher not collected
			IF NOT HAS_PICKUP_BEEN_COLLECTED pickupFireExtinguisher
				DO_FADE 500 FADE_OUT
				timerCutscene = m_mission_timer + 550

				SET_PLAYER_CONTROL player1 OFF

				// Make Coochie face the player and stand on the floor
				headTemp = 180.0000
				SET_CHAR_HEADING charCoochie headTemp
				GET_CHAR_COORDINATES charCoochie xposTemp yposTemp zposTemp
				zposTemp = 1030.9688
				SET_CHAR_COORDINATES charCoochie xposTemp yposTemp zposTemp

				// Stop Coochie playing anims
				nCurrentCoochieAnim = 99
				timerCoochieAnim = 0

				flagFireExtinguisherCutscenePlaying = 1
				flagCutscenePlaying = 1

				// Prepare the 'No Way Out' speech
				GOSUB Crash1_Conversation_Command_Cancel
				nRequiredConversationID = CRASH1_CONVERSATION_NO_WAY_OUT
				GOSUB Crash1_Conversation_Command_Prepare

				m_goals++
			ELSE
				m_goals = 10
			ENDIF		
		ENDIF
	ENDIF


	// Check if Fade_Out has finished
	IF m_goals = 3
		IF flagSkipCutscene = 1
			m_goals++
		ELSE
			IF NOT nCurrentConversationID = CRASH1_CONVERSATION_NO_WAY_OUT
				nRequiredConversationID = CRASH1_CONVERSATION_NO_WAY_OUT
				GOSUB Crash1_Conversation_Command_Prepare
			ENDIF

			IF timerCutscene < m_mission_timer
				ADD_BLIP_FOR_PICKUP pickupFireExtinguisher blipDestination
				SET_BLIP_ENTRY_EXIT blipDestination 2352.1143 -1170.6102 3.0

				GOSUB Crash1_Cutscene_Get_Fire_Extinguisher
				DO_FADE 500 FADE_IN
				timerCutscene = m_mission_timer + 550

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Check if Fade_In has finished
	IF m_goals = 4
		IF flagSkipCutscene = 1
			m_goals++
		ELSE
			IF NOT nCurrentConversationID = CRASH1_CONVERSATION_NO_WAY_OUT
				nRequiredConversationID = CRASH1_CONVERSATION_NO_WAY_OUT
				GOSUB Crash1_Conversation_Command_Prepare
			ENDIF

			IF timerCutscene < m_mission_timer
			AND nCurrentConversationID = CRASH1_CONVERSATION_NO_WAY_OUT
				// Play the 'No Way Out' speech
				GOSUB Crash1_Conversation_Command_Play

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Check for the 'No Way Out' speech being finished
	IF m_goals = 5
		IF flagSkipCutscene = 1
			m_goals++
		ELSE
			IF IS_BIT_SET bitsConversationsPlayed CRASH1_CONVERSATION_NO_WAY_OUT
				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Prepare the 'Fire Ex' speech and wait for it to be prepared
	IF m_goals = 6
		IF flagSkipCutscene = 1
			m_goals++
		ELSE
			IF NOT nCurrentConversationID = CRASH1_CONVERSATION_FIRE_EX
				// Prepare the 'Fire Extinguisher' cutscene speech
				nRequiredConversationID = CRASH1_CONVERSATION_FIRE_EX
				GOSUB Crash1_Conversation_Command_Prepare
			ELSE
				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Jump to 'fire extinguisher' cut
	IF m_goals = 7
		IF flagSkipCutscene = 1
			m_goals++
		ELSE
			// Jump to fire extinguisher cut
			SET_FIXED_CAMERA_POSITION 2329.6804 -1172.0250 1030.1374 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2328.8677 -1172.5338 1029.8537 JUMP_CUT

			// Play 'Fire-Ex' speech
			GOSUB Crash1_Conversation_Command_Play

			m_goals++
		ENDIF
	ENDIF


	// Check if Lady has finished talking
	IF m_goals = 8
		IF flagSkipCutscene = 1
			m_goals = 50
		ELSE
			IF IS_BIT_SET bitsConversationsPlayed CRASH1_CONVERSATION_FIRE_EX
				// Give player back control and allow Coochie Anims again
				SET_PLAYER_CONTROL player1 ON
				SET_EVERYONE_IGNORE_PLAYER player1 FALSE
				SWITCH_WIDESCREEN OFF
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT

				flagFireExtinguisherCutscenePlaying = 0
				flagCutscenePlaying = 0

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Prepare and play the 'I'll Be Back' speech
	IF m_goals = 9
		nRequiredConversationID = CRASH1_CONVERSATION_I_BE_BACK
		GOSUB Crash1_Conversation_Command_Prepare

		IF nCurrentConversationID = CRASH1_CONVERSATION_I_BE_BACK
			GOSUB Crash1_Conversation_Command_Play
			m_goals++
		ENDIF
	ENDIF


	// Check for player with extinguisher
	IF m_goals = 10
		IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_EXTINGUISHER
			// Remove blip on fire extinguisher and add it on girl
			REMOVE_BLIP blipDestination

			IF NOT IS_CHAR_DEAD	charCoochie
				ADD_BLIP_FOR_CHAR charCoochie blipCoochie
				SET_BLIP_AS_FRIENDLY blipCoochie TRUE
			ENDIF

			// Prepare the 'outside room' speech
			GOSUB Crash1_Conversation_Command_Cancel
			nRequiredConversationID = CRASH1_CONVERSATION_OUTSIDE_ROOM
			GOSUB Crash1_Conversation_Command_Prepare

			m_goals++
		ENDIF
	ENDIF


	// Check if player outside Coochie Room with fire extinguisher
	IF m_goals = 11
		xloTemp = 2344.7510		- 1.5000
		yloTemp = -1181.1611	- 1.5000
		zloTemp = 1030.9610		- 0.5000
		xhiTemp = 2344.7510		+ 1.5000
		yhiTemp = -1181.1611	+ 1.5000
		zhiTemp = 1030.9610		+ 1.5000

		IF IS_CHAR_IN_AREA_3D scplayer xloTemp yloTemp zloTemp xhiTemp yhiTemp zhiTemp FALSE
			IF NOT nCurrentConversationID = CRASH1_CONVERSATION_OUTSIDE_ROOM
				nRequiredConversationID = CRASH1_CONVERSATION_OUTSIDE_ROOM
				GOSUB Crash1_Conversation_Command_Prepare
			ENDIF						

			IF nCurrentConversationID = CRASH1_CONVERSATION_OUTSIDE_ROOM
				// Instructions: Put out the fire
				GOSUB Crash1_Conversation_Command_Play

				// Make Coochie face the player and stand on the floor
				headTemp = 180.0000
				SET_CHAR_HEADING charCoochie headTemp
				GET_CHAR_COORDINATES charCoochie xposTemp yposTemp zposTemp
				zposTemp = 1030.9688
				SET_CHAR_COORDINATES charCoochie xposTemp yposTemp zposTemp

				// Timer for when help text should be displayed
				timerCutscene = m_mission_timer + 3000

				// Stop Coochie playing anims
				nCurrentCoochieAnim = 99
				
				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Get to Coochie
	IF m_goals = 12
		IF timerCutscene > 0
			IF timerCutscene < m_mission_timer
				timerCutscene = 0
				PRINT_HELP CRA1_35
			ENDIF
		ENDIF

		IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer charCoochie 6.0 6.0 1.0 0
			// Give Coochie full health again
			SET_CHAR_HEALTH charCoochie 100
			
			m_goals = 99
		ENDIF
	ENDIF


	// Skipped Cutscene stuff
	// ----------------------

	// Check if the cutscene was skipped
	// NOTE: Should never enter here unless skipped
	IF flagCutscenePlaying = 1
	AND IS_SKIP_CUTSCENE_BUTTON_PRESSED
	AND flagSkipCutscene = 0
		flagSkipCutscene = 1
	ENDIF


	// Restore camera if cutscene skipped
	IF m_goals = 50
		// Give player back control and allow Coochie Anims again
		SET_PLAYER_CONTROL player1 ON
		SET_EVERYONE_IGNORE_PLAYER player1 FALSE
		SWITCH_WIDESCREEN OFF
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		nCurrentCoochieAnim = 0

		flagCleaningUpSkippedCutscene = 0
		flagSkipCutscene = 0
		flagFireExtinguisherCutscenePlaying = 0
		flagCutscenePlaying = 0

		m_goals = 10
	ENDIF


	// Continuous Updates
	// ------------------

	GOSUB Crash1_Update_Coochie_Panic_Anims
	GOSUB Crash1_Update_Coochie_Burn_Time
//	GOSUB Crash1_Update_HouseGuyIn_Cowering
//	GOSUB Crash1_Update_HouseGuyIn_Stairs

	// Check if Player Left House
	IF flagInHouse = 0
	AND flagBeenInHouse = 1
		// Player is outside, tell him to go back in
		flagDisplayGoBackMessage_WaitForStartFade = TRUE

		flagBeenInHouse = 0
	ENDIF


	// Display the 'go back for girl' message? (Wait for start fade)
	IF flagDisplayGoBackMessage_WaitForStartFade = TRUE
		IF GET_FADING_STATUS
			flagDisplayGoBackMessage_WaitForStartFade	= FALSE
			flagDisplayGoBackMessage_WaitForEndFade		= TRUE
		ENDIF
	ENDIF


 	// Display the 'go back for girl' message? (Wait for Fade out)
	IF flagDisplayGoBackMessage_WaitForEndFade = TRUE
		IF NOT GET_FADING_STATUS
			flagDisplayGoBackMessage_WaitForEndFade	= FALSE

			// Instructions: Go back for girl
			PRINT_NOW CRA1_10 5000 1
		ENDIF
	ENDIF

	
	// Continuously clear the area around the fire extinguisher pickup
	CLEAR_AREA xposFireEx yposFireEx zposFireEx 3.0 FALSE

	// When the fire extinguisher is picked up, display the help
	IF flagFireExHelpDisplayed = 0
		IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_EXTINGUISHER
			flagFireExHelpDisplayed = 1
			// Go upstairs and rescue the girl
			PRINT_NOW CRA1_09 4000 1
			PRINT_HELP FIRE_EX  
		ENDIF
	ENDIF


	// Continuously clear the area around the girl
	IF flagClearFlamesAroundGirl = TRUE
		CLEAR_AREA 2345.9072 -1171.6591 30.9688 1.5 FALSE
		CLEAR_AREA 2345.9072 -1171.6591 1030.9688 1.5 FALSE
	ENDIF


	// If the player moves a distance away from the house, restart the traffic and the peds
	IF flagInHouse = FALSE
		IF flagNearHouse = TRUE
			// ...currently near the house
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2337.6660 -1180.9938 31.7513 48.1099 41.5599 18.7500 FALSE
				// ...away from house again
				SET_PED_DENSITY_MULTIPLIER 1.0
				SET_CAR_DENSITY_MULTIPLIER 1.0
				flagNearHouse = FALSE
			ENDIF
		ELSE
			// ...not currently near the house
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2337.6660 -1180.9938 31.7513 38.1099 31.5599 8.7500 FALSE
				// ...near house again
				SET_PED_DENSITY_MULTIPLIER 0.0
				SET_CAR_DENSITY_MULTIPLIER 0.0
				flagNearHouse = TRUE
			ENDIF
		ENDIF
	ENDIF


	// exit
	IF m_goals = 99
		GOSUB Crash1_next_stage
	ENDIF

RETURN


// ****************************************
// STAGE 5: Collapsing Wall cutscene

Crash1_Stage_CollapsingWall:

   	// Initialisation
	// --------------

   	IF m_goals = 0
		// Check if the big fire at the girl's door is still blazing
		flagBedroomFireStillBlazing = TRUE
		IF IS_SCRIPT_FIRE_EXTINGUISHED fireInHouseBig[0]
			flagBedroomFireStillBlazing = FALSE
		ENDIF

		// Switch off player control
		SET_PLAYER_CONTROL player1 OFF

		// Cleanup earlier parts of the mission
//		GOSUB Crash1_Burn_HouseGuysIn
		GOSUB Crash1_Remove_All_Outside_Guys

		// No longer need the LSV2 model
		MARK_MODEL_AS_NO_LONGER_NEEDED LSV2

		IF NOT IS_CAR_DEAD carBeingWorkedOn
			OPEN_CAR_DOOR_A_BIT carBeingWorkedOn BONNET 0.0f
		ENDIF

		flagWarpCoochieOutside = FALSE
		statusCoochieExitPath = 0

		// Activate all big fires
		statusBigFires = 3
		GOSUB Crash1_Update_Big_Fires

		// Remove the timer
		CLEAR_ONSCREEN_COUNTER g_Crash1_counterKM1

		// Convert walls to rubble
		statusWalls[1] = 3
		statusWalls[2] = 3

		// Show blip at top of stairs
		xposTemp = 2319.7173
		yposTemp = -1178.5564
		zposTemp = 1030.9688

		ADD_BLIP_FOR_COORD xposTemp yposTemp zposTemp blipDestination
		SET_BLIP_ENTRY_EXIT blipDestination 2352.1143 -1170.6102 3.0
		flagBlipAtTopOfStairs = 1

		flagCutscenePlaying				= 0
		flagCleaningUpSkippedCutscene	= 0
		flagSkipCutscene				= 0

		// Load the 'wall collapse' audio
		LOAD_MISSION_AUDIO CRASH1_SFX_SLOT SOUND_WALL_COLLAPSE_NEARBY

		// Make the girl move forward then face the player
		FREEZE_CHAR_POSITION charCoochie FALSE
		TASK_GO_STRAIGHT_TO_COORD charCoochie 2345.6267 -1175.8444 1030.9688 PEDMOVE_RUN -2

		// Make the player face the girl
		TASK_TURN_CHAR_TO_FACE_CHAR scplayer charCoochie

		// Change the camera angle
		SET_FIXED_CAMERA_POSITION 2349.7678 -1177.1592 1031.7914 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2348.8494 -1176.7654 1031.8282 JUMP_CUT

		// Re-activate the fire in the girl's bedroom doorway (it gets put out when control is removed from the player)
		IF flagBedroomFireStillBlazing = TRUE
		   	statusBigFires = 4
			GOSUB Crash1_Update_Big_Fires
		ENDIF

		// Re-activate the fires in the rooms below the position the player is moved to (they've also gone out)
		statusFires = 1
		GOSUB Crash1_Update_Fires_Interior

		// Widescreen
		SWITCH_WIDESCREEN ON

		flagCutscenePlaying				= TRUE
		flagCleaningUpSkippedCutscene	= FALSE
		flagSkipCutscene				= FALSE

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------


	// Subgoals
	// --------

	// Start Coochie running towards the player
	IF m_goals = 1
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			// Prepare and Play the 'Reach Coochie' speech
			nRequiredConversationID = CRASH1_CONVERSATION_REACH_COOCHIE
			GOSUB Crash1_Conversation_Command_Prepare

			IF nCurrentConversationID = CRASH1_CONVERSATION_REACH_COOCHIE
				GOSUB Crash1_Conversation_Command_Play

				timerCutscene = m_mission_timer + 500

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Check if the girl has reached her destination
	IF m_goals = 2
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			IF timerCutscene < m_mission_timer
				GET_SCRIPT_TASK_STATUS charCoochie TASK_GO_STRAIGHT_TO_COORD m_status
				IF m_status = FINISHED_TASK
					// Make the player and the girl face each other
					TASK_TURN_CHAR_TO_FACE_CHAR scplayer charCoochie
					TASK_TURN_CHAR_TO_FACE_CHAR charCoochie scplayer

					m_goals++
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Wait for the girl's speech to end and show the animation
	IF m_goals = 3
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			IF IS_BIT_SET bitsConversationsPlayed CRASH1_CONVERSATION_REACH_COOCHIE
			AND HAS_MISSION_AUDIO_LOADED CRASH1_SFX_SLOT
				// Start the wall animation and sound effect
				PLAY_MISSION_AUDIO CRASH1_SFX_SLOT

				// Play the breaking walls animation
				statusWalls[0] = 1

				// Wait for a very brief moment before cutting to the animation camera
				timerCutscene = m_mission_timer + 100

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Cut to the animation camera
	IF m_goals = 4
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			IF timerCutscene < m_mission_timer
				SET_FIXED_CAMERA_POSITION 2347.9424 -1179.9541 1031.8584 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2346.9497 -1179.8898 1031.9612 JUMP_CUT

				// Prepare the 'Building Collapse' conversation
				nRequiredConversationID = CRASH1_CONVERSATION_BUILDING_COLLAPSE
				GOSUB Crash1_Conversation_Command_Prepare

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Wait for the animation to end
	IF m_goals = 5
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			// Keep requesting the speech until it is loaded
			IF NOT nCurrentConversationID = CRASH1_CONVERSATION_BUILDING_COLLAPSE
				nRequiredConversationID = CRASH1_CONVERSATION_BUILDING_COLLAPSE
				GOSUB Crash1_Conversation_Command_Prepare
			ENDIF

			// Keep the camera shaking
			SHAKE_CAM 40

			IF statusWalls[0] = 5
			AND HAS_MISSION_AUDIO_FINISHED CRASH1_SFX_SLOT
				// Have a brief pause before going back to the girl again
				timerCutscene = m_mission_timer + 500

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Move the cutscene camera and trigger the next speech
	IF m_goals = 6
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			// Keep requesting the speech until it is loaded
			IF NOT nCurrentConversationID = CRASH1_CONVERSATION_BUILDING_COLLAPSE
				nRequiredConversationID = CRASH1_CONVERSATION_BUILDING_COLLAPSE
				GOSUB Crash1_Conversation_Command_Prepare
			ENDIF

			IF timerCutscene < m_mission_timer
			AND nCurrentConversationID = CRASH1_CONVERSATION_BUILDING_COLLAPSE
				// Move the cutscene camera back to Player and Coochie
				SET_FIXED_CAMERA_POSITION 2348.4949 -1176.4503 1031.4856 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2347.5381 -1176.7322 1031.5580 JUMP_CUT

				// Trigger the girl speech
				GOSUB Crash1_Conversation_Command_Play

				timerCutscene = m_mission_timer + 3000

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Make the player walk away (but only if he put out the bedroom fire)
	IF m_goals = 7
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals++
		ELSE
			IF timerCutscene < m_mission_timer
				IF flagBedroomFireStillBlazing = FALSE
					TASK_GO_STRAIGHT_TO_COORD scplayer 2344.7380 -1180.8529 1030.9766 PEDMOVE_WALK -2
				ENDIF

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// End the cutscene when the speech is finished
	IF m_goals = 8
		IF flagSkipCutscene = TRUE
			// ...cutscene skipped
			m_goals = 50
		ELSE
			IF IS_BIT_SET bitsConversationsPlayed CRASH1_CONVERSATION_BUILDING_COLLAPSE
				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL player1 ON
				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER

				flagCutscenePlaying = FALSE

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// RETURN HERE WHEN CUTSCENE ENDED
	IF m_goals = 9
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
			// Tidy up after the cutscene
			// Cancel speech
			// ...reach coochie
			IF NOT IS_BIT_SET bitsConversationsPlayed CRASH1_CONVERSATION_REACH_COOCHIE
				GOSUB Crash1_Conversation_Command_Cancel
				SET_BIT bitsConversationsPlayed CRASH1_CONVERSATION_BUILDING_COLLAPSE
			ENDIF

			// ...building collapse
			IF NOT IS_BIT_SET bitsConversationsPlayed CRASH1_CONVERSATION_BUILDING_COLLAPSE
				GOSUB Crash1_Conversation_Command_Cancel
			ENDIF

			// Wall collapse anims
			IF statusWalls[0] < 5
				IF IS_OBJECT_PLAYING_ANIM objectWall[0] BD_FIRE1
					SET_OBJECT_ANIM_CURRENT_TIME objectWall[0] BD_FIRE1 1.0
					statusWalls[0] = 3
				ELSE
					statusWalls[0] = 4
				ENDIF
			ENDIF

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
			m_goals = 9
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------

	// ...breaking walls
	GOSUB Crash1_Update_AnimatedWall0



	// Exit
	// ----

	IF m_goals = 99
		GOSUB Crash1_Next_Stage
	ENDIF

RETURN


// ****************************************
// STAGE 6: Get the Lady Out

Crash1_Stage_GetTheLadyOut:

	// Check if coochie dead
	IF IS_CHAR_DEAD charCoochie
		m_failed = 1
		RETURN
	ENDIF


   	// Initialisation for this stage
	IF m_goals = 0
		flagCutscenePlaying				= 0
		flagCleaningUpSkippedCutscene	= 0
		flagSkipCutscene				= 0

		flagKissingCutscenePlaying = 0

		m_goals++
	ENDIF


	// Initialise Cutscene showing building collapsing
	// (NOW DONE IN PREVIOUS STAGE)
	IF m_goals = 1
		m_goals++
	ENDIF


	// Wait until the Speech has finished and the SFX has loaded before shaking the camera
	// (NOW DONE IN PREVIOUS STAGE)
	IF m_goals = 2
		m_goals++
	ENDIF


	// Shaking the camera for the first time
	// (NOW DONE IN PREVIOUS STAGE)
	IF m_goals = 3
		// Prepare the next camera shake
		timerCutscene = m_mission_timer + 5000
		timerPreloadAudio = timerCutscene - 3000

		m_goals++
	ENDIF


	// Wait until the delay before camera shake times out
	IF m_goals = 4
		IF NOT timerPreloadAudio = 0
			IF timerPreloadAudio < m_mission_timer
				// Load the 'roof collapse' audio
				LOAD_MISSION_AUDIO CRASH1_SFX_SLOT SOUND_ROOF_COLLAPSE
				timerPreloadAudio = 0
			ENDIF
		ENDIF
		
		IF timerCutscene < m_mission_timer
		AND HAS_MISSION_AUDIO_LOADED CRASH1_SFX_SLOT
			// Trigger the sound effect
			PLAY_MISSION_AUDIO CRASH1_SFX_SLOT

			// Period of time the camera will shake for
			timerCutscene = m_mission_timer + 2000

			m_goals++
		ENDIF
	ENDIF


	// Shaking the camera for the second time
	IF m_goals = 5
		IF timerCutscene < m_mission_timer
			// Stop shaking the camera and introduce a delay until the next shake
			timerCutscene = m_mission_timer + 5000
			timerPreloadAudio = timerCutscene - 3000

			m_goals++
		ELSE
			SHAKE_CAM 80	//200
		ENDIF
	ENDIF


	// Wait until the delay before camera shake times out
	IF m_goals = 6
		IF NOT timerPreloadAudio = 0
			IF timerPreloadAudio < m_mission_timer
				// Load the 'roof collapse' audio
				LOAD_MISSION_AUDIO CRASH1_SFX_SLOT SOUND_WALL_COLLAPSE_BELOW
				timerPreloadAudio = 0
			ENDIF
		ENDIF
		
		IF timerCutscene < m_mission_timer
		AND HAS_MISSION_AUDIO_LOADED CRASH1_SFX_SLOT
			// Trigger the sound effect
			PLAY_MISSION_AUDIO CRASH1_SFX_SLOT

			// Period of time the camera will shake for
			timerCutscene = m_mission_timer + 1500

			m_goals++
		ENDIF
	ENDIF


	// Shaking the camera for the third time
	IF m_goals = 7
		IF timerCutscene < m_mission_timer
			// All shakes finished
			m_goals++
		ELSE
			SHAKE_CAM 40	//80
		ENDIF
	ENDIF


	// Check if Coochie should be warped outside
	IF m_goals = 8
		IF flagWarpCoochieOutside = TRUE
			// ...yes, so warp her into the house corridor downstairs
			CLEAR_AREA 2351.7622 -1178.7505 26.9753 15.0000 TRUE
			SET_CHAR_COORDINATES charCoochie 2352.2622 -1176.7505 26.9753
			SET_CHAR_HEADING charCoochie 0.0000
			SET_CHAR_HAS_USED_ENTRY_EXIT charCoochie 2352.1143 -1170.6102 8.0
			SET_CHAR_AREA_VISIBLE charCoochie 0
			CLEAR_CHAR_TASKS_IMMEDIATELY charCoochie

			// Switch off the entry/exit to make sure the player is never allowed back in
			SWITCH_ENTRY_EXIT GANG FALSE

			// Remove blip on front door
			REMOVE_BLIP blipDestination

			// Prepare the 'My Hero' conversation
			GOSUB Crash1_Conversation_Command_Cancel
			nRequiredConversationID = CRASH1_CONVERSATION_COOCHIE_HERO
			GOSUB Crash1_Conversation_Command_Prepare

			m_goals++
		ENDIF
	ENDIF


	// Set up the cutscene
	IF m_goals = 9
		SET_EVERYONE_IGNORE_PLAYER player1 TRUE
		SWITCH_WIDESCREEN ON
		SET_ALL_CARS_CAN_BE_DAMAGED FALSE

		SET_FIXED_CAMERA_POSITION 2354.5891 -1166.2617 27.8700 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2353.8257 -1166.8904 28.0181 JUMP_CUT

		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

		// Make the player turn around to face the house
		OPEN_SEQUENCE_TASK nSeqTask
			TASK_GO_STRAIGHT_TO_COORD -1 2351.9700 -1169.2600 27.0130 PEDMOVE_WALK CRASH1_PERSIST	// Back towards door
			TASK_ACHIEVE_HEADING -1 180.0															// Achieve Heading
		CLOSE_SEQUENCE_TASK nSeqTask

		PERFORM_SEQUENCE_TASK scplayer nSeqTask
		CLEAR_SEQUENCE_TASK nSeqTask

		// Make Coochie run out towards the player
		OPEN_SEQUENCE_TASK nSeqTask
			TASK_GO_STRAIGHT_TO_COORD -1 2351.9700 -1170.2600 27.0130 PEDMOVE_RUN CRASH1_PERSIST	// Outside
			TASK_ACHIEVE_HEADING -1 0.0																// Achieve Heading
		CLOSE_SEQUENCE_TASK nSeqTask

		PERFORM_SEQUENCE_TASK charCoochie nSeqTask
		CLEAR_SEQUENCE_TASK nSeqTask

		// Allow the fire engine and driver to be loaded
		REQUEST_MODEL FIRETRUK
		REQUEST_MODEL LAFD1
		LOAD_ALL_MODELS_NOW

		flagKissingCutscenePlaying = 1
		flagCutscenePlaying = 1

		timerCutscene = m_mission_timer + 500

		m_goals++
	ENDIF


	// Wait for all tasks to be achieved and the conversation to be ready
	IF m_goals = 10
		IF flagSkipCutscene = 1
			m_goals = 50
		ELSE
			IF NOT nCurrentConversationID = CRASH1_CONVERSATION_COOCHIE_HERO
				nRequiredConversationID = CRASH1_CONVERSATION_COOCHIE_HERO
				GOSUB Crash1_Conversation_Command_Prepare
			ENDIF

			// Check if the audio is ready
			IF timerCutscene < m_mission_timer
				IF nCurrentConversationID = CRASH1_CONVERSATION_COOCHIE_HERO
					// Check if the girl has reached the player
					GET_SCRIPT_TASK_STATUS charCoochie -1 m_status
					IF m_status = FINISHED_TASK
						// Check if the player has turned around
						GET_SCRIPT_TASK_STATUS scplayer -1 m_status
						IF m_status = FINISHED_TASK
							// ...everything ready to continue
							m_goals++
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Start the anims and the speech
	IF m_goals = 11
		IF flagSkipCutscene = 1
			m_goals = 50
		ELSE
			// ...anims
			TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM charCoochie 2351.9700 -1170.2600 27.0130 0.0 0.2 GRLFRD_KISS_03 BD_FIRE 4.0 FALSE FALSE FALSE FALSE -1
			TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM scplayer 2351.9700 -1169.2600 27.0130 180.0 0.2 PLAYA_KISS_03 BD_FIRE 4.0 FALSE FALSE FALSE FALSE -1

			// ...speech
			GOSUB Crash1_Conversation_Command_Play

			timerCutscene = m_mission_timer + 1000

			m_goals++
		ENDIF
	ENDIF


	// Wait for the kiss to end
	IF m_goals = 12
		IF flagSkipCutscene = 1
			m_goals = 50
		ELSE
			IF NOT IS_CHAR_PLAYING_ANIM charCoochie GRLFRD_KISS_03
			AND NOT IS_CHAR_PLAYING_ANIM scplayer PLAYA_KISS_03
			AND timerCutscene < m_mission_timer
				SET_PLAYER_CONTROL player1 ON
				SET_EVERYONE_IGNORE_PLAYER player1 FALSE
				SWITCH_WIDESCREEN OFF
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT

				// Set up the group again
				MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
				SET_GROUP_MEMBER players_group charCoochie
				SET_CHAR_NEVER_LEAVES_GROUP charCoochie TRUE
	  	  		LISTEN_TO_PLAYER_GROUP_COMMANDS charCoochie FALSE
				TASK_FOLLOW_FOOTSTEPS charCoochie scplayer
				SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 TRUE

				flagKissingCutscenePlaying = 0
				flagCutscenePlaying = 0

				// Convert all script fires to non-script fires so that the fire engine puts them out
				CLEAR_ALL_SCRIPT_FIRE_FLAGS

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Wait for the fire engine and driver to load
	IF m_goals = 13
		IF HAS_MODEL_LOADED		FIRETRUK
		AND HAS_MODEL_LOADED	LAFD1
			m_goals = 99
		ENDIF
	ENDIF


	// Skipped Cutscene stuff
	// ----------------------

	// Check if the cutscene was skipped
	// NOTE: Should never enter here unless skipped
	IF flagCutscenePlaying = 1
	AND IS_SKIP_CUTSCENE_BUTTON_PRESSED
	AND flagSkipCutscene = 0
		// Cutscene was skipped
	    flagSkipCutscene = 1
	ENDIF


	// Restore camera if cutscene skipped
	IF m_goals = 50
		IF IS_CHAR_PLAYING_ANIM charCoochie GRLFRD_KISS_03
			SET_CHAR_ANIM_CURRENT_TIME charCoochie GRLFRD_KISS_03 1.00
		ENDIF

		IF IS_CHAR_PLAYING_ANIM scplayer PLAYA_KISS_03
			SET_CHAR_ANIM_CURRENT_TIME scplayer PLAYA_KISS_03 1.00
		ENDIF

		// Cancel speech
		IF NOT IS_BIT_SET bitsConversationsPlayed CRASH1_CONVERSATION_COOCHIE_HERO
			GOSUB Crash1_Conversation_Command_Cancel
		ENDIF

		CLEAR_CHAR_TASKS_IMMEDIATELY charCoochie
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

		timerCutscene = m_mission_timer

		flagCleaningUpSkippedCutscene = 0
		flagSkipCutscene = 0
		flagKissingCutscenePlaying = 0
		flagCutscenePlaying = 0

		m_goals = 12
	ENDIF


	// Continuous Updates
	// ------------------

	// ...coochie following player
	IF flagKissingCutscenePlaying = 0
		GOSUB Crash1_Update_Coochie
	ENDIF


	// ...breaking walls
	GOSUB Crash1_Update_AnimatedWall0
	GOSUB Crash1_Update_AnimatedWall1
	GOSUB Crash1_Update_AnimatedWall2


	// If the player has reached the top of the stairs, remove the blip and place it at the front door
	xloTemp = 2319.9937		- 1.5000
	yloTemp = -1179.2180	- 1.5000
	zloTemp = 1030.9766		- 1.2000
	xhiTemp = 2319.9937		+ 1.5000
	yhiTemp = -1179.2180	+ 1.5000
	zhiTemp = 1030.9766		+ 1.2000

	IF IS_CHAR_IN_AREA_3D scplayer xloTemp yloTemp zloTemp xhiTemp yhiTemp zhiTemp FALSE
	AND flagBlipAtTopOfStairs = 1
		REMOVE_BLIP blipDestination

		// Add blip at front door
		xposTemp = 2353.0847
		yposTemp = -1181.1002
		zposTemp = 1026.9702

		ADD_BLIP_FOR_COORD xposTemp yposTemp zposTemp blipDestination
		SET_BLIP_ENTRY_EXIT blipDestination 2352.1143 -1170.6102 3.0

		flagBlipAtTopOfStairs = 0
	ENDIF


	// Continuously clear the area around the fire extinguisher pickup
	CLEAR_AREA xposFireEx yposFireEx zposFireEx 3.0 FALSE


	// Is the player outside?
	IF flagInHouse = FALSE
	AND flagBeenInHouse = TRUE
		// ...yes
		flagBeenInHouse = FALSE

		// Temporarily disable the player
		SET_PLAYER_CONTROL player1 OFF

		// Temporarily disable Coochie
		REMOVE_CHAR_FROM_GROUP charCoochie
		TASK_STAND_STILL charCoochie -2
		FREEZE_CHAR_POSITION charCoochie TRUE

		// Wait for the screen to fade in to see if Coochie should join the player
		flagCheckingForCoochie = TRUE
		flagCoochieLeftBehind = FALSE
	ENDIF

	// Wait for the player to be in position following the warp out of the house and for the fade to finish
	IF flagInHouse = FALSE
	AND flagCheckingForCoochie = TRUE
		IF LOCATE_CHAR_ON_FOOT_3D scplayer 2351.5747 -1166.1903 26.4524 4.0 4.0 4.0 FALSE
			IF NOT GET_FADING_STATUS
				// ...player is outside and the fade has finished
				// Is Coochie in the area near the front door?
				IF IS_CHAR_IN_AREA_3D charCoochie 2340.0972 -1188.5099 1025.5766 2352.9773 -1179.8820 1028.1839 FALSE
				OR IS_CHAR_IN_AREA_3D charCoochie 2344.2205 -1179.8920 1025.5766 2349.8313 -1171.6642 1028.1839 FALSE
					// ...yes
					// Allow Coochie to be warped outside and set up the kissing cutscene
					flagWarpCoochieOutside = TRUE
					flagCheckingForCoochie = FALSE
					FREEZE_CHAR_POSITION charCoochie FALSE
				ELSE
					// ...no
					// Instructions: You've left the girl behind
					PRINT_NOW CRA1_10 8000 1
					SET_PLAYER_CONTROL player1 ON
					flagCoochieLeftBehind = TRUE
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// If the player is in the house and the Coochie Left Behind flag is set, then re-activate Coochie
	IF flagInHouse = TRUE
	AND flagCoochieLeftBehind = TRUE
		flagCoochieLeftBehind = FALSE

		// Re-enable Coochie again
		FREEZE_CHAR_POSITION charCoochie FALSE
		MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
		SET_GROUP_MEMBER players_group charCoochie
		SET_CHAR_NEVER_LEAVES_GROUP charCoochie TRUE
	  	LISTEN_TO_PLAYER_GROUP_COMMANDS charCoochie FALSE
		SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 TRUE

		// Make her wait for the player to return
		aiCoochie = 2
	ENDIF


	// Make sure the player's fire extinguisher has infinite ammo
	IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_EXTINGUISHER
		GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_EXTINGUISHER nTempInt
		IF nTempInt < 500
			SET_CHAR_AMMO scplayer WEAPONTYPE_EXTINGUISHER CRASH1_INFINITE_AMMO
		ENDIF
	ENDIF


	// Check if the 'Put Out Fire' speech should be prepared and played
	IF flagPrepareAndPlayPutOutFireSpeech = TRUE
		CLEAR_BIT bitsConversationsPlayed CRASH1_CONVERSATION_PUT_OUT_FIRE
		nRequiredConversationID = CRASH1_CONVERSATION_PUT_OUT_FIRE
		GOSUB Crash1_Conversation_Command_Prepare

		IF nCurrentConversationID = CRASH1_CONVERSATION_PUT_OUT_FIRE
			GOSUB Crash1_Conversation_Command_Play
			flagPrepareAndPlayPutOutFireSpeech = FALSE
		ENDIF
	ENDIF


	// If the player moves a distance away from the house, restart the traffix and the peds
	IF flagInHouse = FALSE
		IF flagNearHouse = TRUE
			// ...currently near the house
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2337.6660 -1180.9938 31.7513 48.1099 41.5599 18.7500 FALSE
				// ...away from house again
				SET_PED_DENSITY_MULTIPLIER 1.0
				SET_CAR_DENSITY_MULTIPLIER 1.0
				flagNearHouse = FALSE
			ENDIF
		ELSE
			// ...not currently near the house
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2337.6660 -1180.9938 31.7513 38.1099 31.5599 8.7500 FALSE
				// ...near house again
				SET_PED_DENSITY_MULTIPLIER 0.0
				SET_CAR_DENSITY_MULTIPLIER 0.0
				flagNearHouse = TRUE
			ENDIF
		ENDIF
	ENDIF


	// exit
	IF m_goals = 99
		GOSUB Crash1_next_stage
	ENDIF

RETURN


// ****************************************
// STAGE 7: Take the Lady Home

Crash1_Stage_TakeTheLadyHome:

   	// Initialisation for this stage
	IF m_goals = 0
		// Allow Peds and Vehicles again
		SET_PED_DENSITY_MULTIPLIER 1.0
		SET_CAR_DENSITY_MULTIPLIER 1.0

		// Create the Fire Engine
		GOSUB Crash1_Create_FireEngine

		// Display destination blip
		xposCoochieHome = 2402.3071
		yposCoochieHome = -1727.5302
		zposCoochieHome = 12.3906
		ADD_BLIP_FOR_COORD xposCoochieHome yposCoochieHome zposCoochieHome blipDestination

		// Remove Coochie Blip
		REMOVE_BLIP blipCoochie

		// Clear control flags for this stage
		flagPlayerInCar = 0
		flagCoochieHome = 0
		flagCoochieLeftBehind = FALSE

		// Prepare the speech
		nRequiredConversationID = CRASH1_CONVERSATION_DRIVE_COOCHIE_HOME
		GOSUB Crash1_Conversation_Command_Prepare

		// Make sure neither CJ nor the girl talks during the ride home
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH charCoochie TRUE

		m_goals++
	ENDIF


	// FAILURE CONDITIONS
	// ------------------

	// Check if coochie dead but only fail if coochie not home
	IF IS_CHAR_DEAD charCoochie
		IF flagCoochieHome = FALSE
			m_failed = 1
			RETURN
		ENDIF
	ENDIF


	// SUBGOALS
	// --------

	// Play the 'Drive Home' conversation
	IF m_goals = 1
		IF NOT nCurrentConversationID = CRASH1_CONVERSATION_DRIVE_COOCHIE_HOME
			nRequiredConversationID = CRASH1_CONVERSATION_DRIVE_COOCHIE_HOME
			GOSUB Crash1_Conversation_Command_Prepare
		ENDIF

		IF nCurrentConversationID = CRASH1_CONVERSATION_DRIVE_COOCHIE_HOME
			GOSUB Crash1_Conversation_Command_Play

			m_goals++
		ENDIF
	ENDIF

	// Check if Coochie has reached home
	IF m_goals = 2
		flagTempFlag = FALSE	// Used to decide if coochie is in the locate

		// ...check if Coochie arrived home
		IF LOCATE_CHAR_ANY_MEANS_3D charCoochie xposCoochieHome yposCoochieHome zposCoochieHome 3.0 3.0 3.0 TRUE
			flagTempFlag = TRUE
			SET_PLAYER_CONTROL player1 OFF

			// ...check if coochie in a car
			IF IS_CHAR_IN_ANY_CAR charCoochie 
				// ...yes, in a car, so make sure the player is also in the car and make him stop
				STORE_CAR_CHAR_IS_IN_NO_SAVE charCoochie car
				IF NOT IS_CHAR_IN_CAR scplayer car
					flagTempFlag = FALSE
				ENDIF
			ENDIF
		ENDIF

		IF flagTempFlag = TRUE
			REMOVE_CHAR_FROM_GROUP charCoochie
			REMOVE_BLIP blipDestination
			REMOVE_BLIP blipCoochie
			flagCoochieHome = 1

			// Set up the Cutscene camera
			SET_EVERYONE_IGNORE_PLAYER player1 TRUE
			SWITCH_WIDESCREEN ON
			SET_ALL_CARS_CAN_BE_DAMAGED FALSE

			// Clear the area around her front door to make sure she doesn't get blocked
			CLEAR_AREA 2401.5200 -1722.0745 12.6072 8.0 TRUE

			// Clear the area around the player's car
			IF IS_CHAR_IN_ANY_CAR charCoochie
				STORE_CAR_CHAR_IS_IN_NO_SAVE charCoochie car
				GET_CAR_COORDINATES car xposTemp yposTemp zposTemp
				CLEAR_AREA xposTemp yposTemp zposTemp 5.0 TRUE
			ENDIF

			// Cut to camera position
			SET_FIXED_CAMERA_POSITION 2392.5417 -1727.9492 14.9910 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2393.4580 -1727.5577 14.9067 JUMP_CUT

			m_goals++
		ENDIF
	ENDIF


	// Prepare and Play the 'Coochie Name' speech
	IF m_goals = 3
		nRequiredConversationID = CRASH1_CONVERSATION_COOCHIE_NAME
		GOSUB Crash1_Conversation_Command_Prepare

		IF nCurrentConversationID = CRASH1_CONVERSATION_COOCHIE_NAME
			GOSUB Crash1_Conversation_Command_Play

			// If the player is not in a car then make him face Coochie
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				TASK_TURN_CHAR_TO_FACE_CHAR scplayer charCoochie
			ENDIF

			m_goals++
		ENDIF
	ENDIF


	// Wait for the Coochie Name speech to finish
	IF m_goals = 4
		IF IS_BIT_SET bitsConversationsPlayed CRASH1_CONVERSATION_COOCHIE_NAME
			m_goals++
		ENDIF
	ENDIF


	// Prepare and Play the 'Go Out' speech
	IF m_goals = 5
		nRequiredConversationID = CRASH1_CONVERSATION_GO_OUT
		GOSUB Crash1_Conversation_Command_Prepare

		IF nCurrentConversationID = CRASH1_CONVERSATION_GO_OUT
			GOSUB Crash1_Conversation_Command_Play

			m_goals++
		ENDIF
	ENDIF


	// Make Coochie leave the car if she is in one
	IF m_goals = 6
		// Make Coochie get out of the car if she is in one
		IF IS_CHAR_IN_ANY_CAR charCoochie
			// ...she is in a car
			TASK_LEAVE_ANY_CAR charCoochie
			m_goals++
		ELSE
			// ...not in a car, so skip the next goal
			m_goals = 8
		ENDIF
	ENDIF

	
	// Check if Coochie has left the car
	IF m_goals = 7
		GET_SCRIPT_TASK_STATUS charCoochie TASK_LEAVE_ANY_CAR m_status

		IF m_status = FINISHED_TASK
			m_goals++
		ENDIF
	ENDIF


	// Make Coochie go to coords and make the player face her if not in a car
	IF m_goals = 8
		TASK_GO_STRAIGHT_TO_COORD charCoochie 2402.0522 -1719.7156 12.6181 PEDMOVE_RUN -2

		IF NOT IS_CHAR_IN_ANY_CAR scplayer
			TASK_TURN_CHAR_TO_FACE_CHAR scplayer charCoochie
		ENDIF
		
		m_goals++
	ENDIF


	// Check if Coochie reached wave position, and make player turn again to face her
	IF m_goals = 9
		GET_SCRIPT_TASK_STATUS charCoochie TASK_GO_STRAIGHT_TO_COORD m_status

		IF m_status = FINISHED_TASK
			// The wave anim starts 180 degrees away from the actual wave heading, so calculate this heading
			// ...player coords
			GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
			xhiTemp = xposTemp
			yhiTemp = yposTemp

			// ...coochie coords
			GET_CHAR_COORDINATES charCoochie xposTemp yposTemp zposTemp
			xloTemp = xposTemp
			yloTemp = yposTemp

			// ...calculate vector
			xposTemp = xloTemp - xhiTemp
			yposTemp = yloTemp - yhiTemp
			GET_HEADING_FROM_VECTOR_2D xposTemp yposTemp headTemp

			// Play Wave Anim
			TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM charCoochie 2402.0522 -1719.7156 12.6181 headTemp 0.2 BD_GF_Wave BD_FIRE 4.0 FALSE FALSE FALSE FALSE -1
//			TASK_PLAY_ANIM_NON_INTERRUPTABLE charCoochie BD_GF_Wave BD_FIRE 4.0 false false false false -1

			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				TASK_TURN_CHAR_TO_FACE_CHAR scplayer charCoochie
			ENDIF

			m_goals++
		ENDIF
	ENDIF


	// Prepare and Play the 'CYA' speech
	IF m_goals = 10
		nRequiredConversationID = CRASH1_CONVERSATION_CYA
		GOSUB Crash1_Conversation_Command_Prepare

		IF nCurrentConversationID = CRASH1_CONVERSATION_CYA
			GOSUB Crash1_Conversation_Command_Play

			m_goals++
		ENDIF
	ENDIF


	// Check if animation finished
	IF m_goals = 11
		IF IS_CHAR_PLAYING_ANIM charCoochie BD_GF_Wave
 			GET_CHAR_ANIM_CURRENT_TIME charCoochie BD_GF_Wave fTempFloat
			IF fTempFloat = 1.0000
				// Walk away
				TASK_GO_STRAIGHT_TO_COORD charCoochie 2401.8574 -1717.0104 12.6334 PEDMOVE_RUN -2

				m_goals++
			ENDIF
		ENDIF
	ENDIF


	// Check if girl disappeared from view
	IF m_goals = 12
		GET_SCRIPT_TASK_STATUS charCoochie TASK_GO_STRAIGHT_TO_COORD m_status

		IF m_status = FINISHED_TASK
			DELETE_CHAR charCoochie

			// Restore camera
			SWITCH_WIDESCREEN OFF
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT

			// Allow CJ to talk again
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

			m_goals++
		ENDIF
	ENDIF


	IF m_goals = 13
		// Restore player control
		SET_PLAYER_CONTROL player1 ON
		SET_EVERYONE_IGNORE_PLAYER player1 FALSE
		SET_ALL_CARS_CAN_BE_DAMAGED TRUE

		m_goals = 99
	ENDIF


	// Continuous Updates
	// If the player gets in a car then remove Coochie's Follow_Footsteps so that she gets in too
	IF flagCoochieHome = 0
		IF IS_CHAR_IN_ANY_CAR scplayer
			// ...player is in a car
			// If the player was not previously in a car, then remove Coochie's tasks so she follows him
			IF flagPlayerInCar = 0
				flagPlayerInCar = 1
				CLEAR_CHAR_TASKS_IMMEDIATELY charCoochie
			ENDIF
		ELSE
			// ...player is not in a car
			// If the player was previously in a car, then add Follow_Footsteps to Coochie again
			IF flagPlayerInCar = 1
				flagPlayerInCar = 0
				TASK_FOLLOW_FOOTSTEPS charCoochie scplayer
			ENDIF
		ENDIF
	ENDIF


	// Check if the player and Coochie are nearby or not
	// NOTE 26/8/04: but only if they are in the same visible area. If player enters a shop, Coochie will be left outside.
	IF NOT IS_CHAR_DEAD charCoochie
		GET_CHAR_AREA_VISIBLE scplayer nTempInt
		GET_CHAR_AREA_VISIBLE charCoochie nTempInt2
		IF nTempInt = nTempInt2
			GET_CHAR_COORDINATES charCoochie	xposTemp	yposTemp	zposTemp
			GET_CHAR_COORDINATES scplayer		xposTemp2	yposTemp2	zposTemp2
			GET_DISTANCE_BETWEEN_COORDS_3D xposTemp yposTemp zposTemp xposTemp2 yposTemp2 zposTemp2 fTempDistance
			IF flagCoochieLeftBehind = TRUE
				// ...Coochie was far away, see if she are closer now
				IF fTempDistance < 20.0
					// ...she is now close
					flagCoochieLeftBehind = FALSE

					// Set her up again
					CLEAR_CHAR_TASKS charCoochie
					MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
					SET_GROUP_MEMBER players_group charCoochie
					SET_CHAR_NEVER_LEAVES_GROUP charCoochie TRUE
		  	  		LISTEN_TO_PLAYER_GROUP_COMMANDS charCoochie FALSE
					SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 TRUE

					IF flagPlayerInCar = FALSE
						TASK_FOLLOW_FOOTSTEPS charCoochie scplayer
					ENDIF

					// Update Blips
					ADD_BLIP_FOR_COORD xposCoochieHome yposCoochieHome zposCoochieHome blipDestination
					REMOVE_BLIP blipCoochie
				ENDIF
			ELSE
				// ...Coochie was close, see if she is now far away
				IF fTempDistance > 50.0
					// ...she is now far away
					flagCoochieLeftBehind = TRUE
					PRINT_NOW CRA1_12 7000 1

					// Temporarily disable Coochie
					REMOVE_CHAR_FROM_GROUP charCoochie
					CLEAR_CHAR_TASKS_IMMEDIATELY charCoochie
					TASK_STAND_STILL charCoochie -2

					// Update Blips
					REMOVE_BLIP blipDestination
					ADD_BLIP_FOR_CHAR charCoochie blipCoochie
					SET_BLIP_AS_FRIENDLY blipCoochie TRUE
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Player and Coochie banter on way home
	IF NOT IS_CHAR_DEAD charCoochie
	AND flagCoochieHome = FALSE
		IF IS_CHAR_IN_ANY_CAR scplayer
		AND IS_CHAR_IN_ANY_CAR charCoochie
			STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
			IF IS_CHAR_IN_CAR charCoochie car
			AND NOT IS_CAR_STOPPED car
				IF NOT IS_BIT_SET bitsConversationsPlayed CRASH1_CONVERSATION_ON_RIDE_HOME
				AND nCurrentConversationID = CRASH1_CONVERSATION_NONE
					nRequiredConversationID = CRASH1_CONVERSATION_ON_RIDE_HOME
					GOSUB Crash1_Conversation_Command_Prepare

					IF nCurrentConversationID = CRASH1_CONVERSATION_ON_RIDE_HOME
						GOSUB Crash1_Conversation_Command_Play
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// Player approaching Coochie house
	IF NOT IS_CHAR_DEAD charCoochie
	AND flagCoochieHome = FALSE
		IF NOT IS_BIT_SET bitsConversationsPlayed CRASH1_CONVERSATION_COOCHIE_HOUSE
		AND NOT nCurrentConversationID = CRASH1_CONVERSATION_COOCHIE_HOUSE
			IF LOCATE_CHAR_ANY_MEANS_3D charCoochie xposCoochieHome yposCoochieHome zposCoochieHome 30.0 30.0 20.0 FALSE
				GOSUB Crash1_Conversation_Command_Cancel
				nRequiredConversationID = CRASH1_CONVERSATION_COOCHIE_HOUSE
				GOSUB Crash1_Conversation_Command_Prepare

				IF nCurrentConversationID = CRASH1_CONVERSATION_COOCHIE_HOUSE
					GOSUB Crash1_Conversation_Command_Play
				ENDIF

				// Clear the area around her front door to make sure she doesn't get blocked
				CLEAR_AREA 2401.5200 -1722.0745 12.6072 8.0 TRUE
			ENDIF
		ENDIF
	ENDIF


	// exit
	IF m_goals = 99
		GOSUB Crash1_next_stage
	ENDIF

RETURN


// ****************************************
// The 'Failed In House' cutscene
Crash1_Stage_FailedInHouse:

   	// Initialisation
	// --------------

   	IF m_goals = 0
		// Make the player everything proof and remove his control
		SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
		SET_PLAYER_CONTROL player1 OFF

		// Let the 'mission failed' text be on screen for a few seconds
		timerCutscene = m_mission_timer + 1000

		m_goals++
	ENDIF


	// Failure Conditions
	// ------------------


	// Subgoals
	// --------

	// Kep displaying mission failed for a few seconds, then fade out
	IF m_goals = 1
		IF timerCutscene < m_mission_timer
			SET_FADING_COLOUR 0 0 0
			DO_FADE 500 FADE_OUT

			timerCutscene = m_mission_timer + 500

			m_goals++
		ENDIF
	ENDIF


	// Warp the player out and set up the 'exiting house' cutscene
	IF m_goals = 2
		IF NOT GET_FADING_STATUS
		AND timerCutscene < m_mission_timer
			// If the player is in the wee corridor behind the entry/exit sign and is in a vehicle, then get him
			//	out of the vehicle and delete it
			IF flagInHouse = FALSE
				IF IS_CHAR_IN_ANY_CAR scplayer
					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
					WARP_CHAR_FROM_CAR_TO_COORD scplayer 2351.7332 -1179.0419 26.9753
					DELETE_CAR car
				ENDIF
			ENDIF

			// Position the player
			SET_AREA_VISIBLE 0
			CLEAR_AREA 2351.9607 -1172.0735 26.9765 8.0 TRUE
			LOAD_SCENE 2351.9607 -1172.0735 26.9765
			SET_CHAR_COORDINATES scplayer 2351.9607 -1172.0735 26.9765 
			SET_CHAR_HEADING scplayer 0.0
			SET_CHAR_AREA_VISIBLE scplayer 0

			// Position the camera
			SET_FIXED_CAMERA_POSITION 2345.0439 -1170.0148 32.0063 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2345.8000 -1169.8221 31.3810 JUMP_CUT

			// Wait before fading in
			timerCutscene = m_mission_timer + 1000

			m_goals++
		ENDIF
	ENDIF


	// Get ready to fade in
	IF m_goals = 3
		IF timerCutscene < m_mission_timer
			DO_FADE 500 FADE_IN

			TASK_GO_STRAIGHT_TO_COORD scplayer 2351.9277 -1166.0802 26.4593 PEDMOVE_RUN CRASH1_PERSIST
			timerCutscene = m_mission_timer + 500

			m_goals++
		ENDIF
	ENDIF


	// Wait for the player to reach the destination
	IF m_goals = 4
		IF timerCutscene < m_mission_timer
			GET_SCRIPT_TASK_STATUS scplayer TASK_GO_STRAIGHT_TO_COORD m_status
			IF m_status = FINISHED_TASK
				SET_CHAR_COORDINATES scplayer 2351.9277 -1166.0802 26.4593 
				SET_CHAR_HEADING scplayer 0.0
				SET_CAMERA_BEHIND_PLAYER

				m_goals = 99
			ENDIF
		ENDIF
	ENDIF


	// Continuous updates
	// ------------------


	// Exit
	// ----

	IF m_goals = 99
		// Make the player nothing proof again, and give him back control
		SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
		SET_PLAYER_CONTROL player1 ON

		GOSUB Crash1_Next_Stage
	ENDIF

RETURN





// *************************************************************************************************************
//										CODE CUTSCENES SKIPPED  
// *************************************************************************************************************

// ****************************************
// The 'Enter House' cutscene
Crash1_Cutscene_Skipped_EnterHouse:
		
	// Do front door stuff if not done
	IF flagCutsceneFrontDoorStuffDone = 0
		// Remove the front door
		SET_OBJECT_PROOFS objectFrontDoor FALSE FALSE FALSE FALSE FALSE
		SET_OBJECT_COLLISION_DAMAGE_EFFECT objectFrontDoor TRUE
		SET_OBJECT_DYNAMIC objectFrontDoor TRUE
		BREAK_OBJECT objectFrontDoor TRUE

		// Create entrance smoke
		statusSmoke = 0
		GOSUB Crash1_Update_Smoke
	ELSE
		// Remove the front door fire
		statusBigFires = 2
		GOSUB Crash1_Update_Big_Fires
	ENDIF

	// Deal with guy on fire
	IF NOT IS_CHAR_DEAD charGuyOnFire
		SET_CHAR_COORDINATES charGuyOnFire 2352.1809 -1164.8928 26.4392
		START_CHAR_FIRE charGuyOnFire fireGuyOnFire
		SET_CHAR_HEALTH	charGuyOnFire 1
		CLEAR_CHAR_TASKS_IMMEDIATELY charGuyOnFire
		TASK_DIE charGuyOnFire
	ENDIF

	// Deal with girl's bedroom stuff
	IF DOES_OBJECT_EXIST objectWindow[5]	//girls window
//	AND NOT HAS_OBJECT_BEEN_DAMAGED objectWindow[5]
//		BREAK_OBJECT objectWindow[5] TRUE
		DELETE_OBJECT objectWindow[5]
	ENDIF

	IF flagCutsceneGirlSmokeCreated = 0
		// Create Coochie Window smoke
		statusSmoke = 4
		GOSUB Crash1_Update_Smoke
	ENDIF

	// Front Door blip
	REMOVE_BLIP blipDestination
	xposTemp = 2352.0371
	yposTemp = -1170.3175
	zposTemp = 26.9702
	ADD_BLIP_FOR_COORD xposTemp yposTemp zposTemp blipDestination

	// Camera angles and allowing entrance into the house, etc
	GOSUB Crash1_Enter_House_Cutscene_Quit

	// GangOutside if they were teleported
	REPEAT 5 nTempPed
		IF NOT IS_CHAR_DEAD charGangOutside[nTempPed]
			IF aiGangOutside[nTempPed] = 6
				// ...this guy was teleported
				GOSUB Crash1_GangOutside_Move_Back_After_Cutscene
			ENDIF
		ENDIF
	ENDREPEAT

	// GangBackup if they were teleported
	IF aiGangBackup = 5
		GOSUB Crash1_GangBackup_Move_Back_After_Cutscene
	ENDIF

RETURN





// *************************************************************************************************************
//										MISSION SPECIFIC GOSUBS  
// *************************************************************************************************************


// *************************************************************************************************************
//								        MOLOTOVS ON ROUTE
// *************************************************************************************************************

// ****************************************
// Create Molotovs On Route
Crash1_Create_MolotovsOnRoute:

   	// Generate Molotovs on Route pickup
   	xposTemp = 1785.6981
   	yposTemp = -1222.1055
   	zposTemp = 16.9119

   	nTempInt = 0
   	CREATE_PICKUP_WITH_AMMO MOLOTOV PICKUP_ONCE 20 xposTemp yposTemp zposTemp pickupMolotovsOnRoute
   	ADD_BLIP_FOR_PICKUP pickupMolotovsOnRoute blipMolotovsOnRoute

RETURN



// *************************************************************************************************************
//								        "GANG OUTSIDE" GUYS
// *************************************************************************************************************

// ****************************************
// GANG OUTSIDE'S AI
Crash1_Update_GangOutside_AI:

	// AI STATES
	// -1:	Not Initialised
	//  0:	Playing Idle Anims
	//  1:  Insult and Going Closer
	//  2:	Orientate
	//  3:	Chasing (OBSOLETE)
	//  4:	Shooting
	//	5:	At start of cutscene, teleport if in area around door
	//	6:	Stand Still during cutscene


	// Check if this gang member is alive
	IF IS_CHAR_DEAD charGangOutside[nTempPed]
		flagGangOutsideInjuredOrDead = 1
		RETURN
	ENDIF


	// Update AI states
	SWITCH aiGangOutside[nTempPed]

		// Not Initialised
		CASE -1
			GOSUB Crash1_GangOutside_Play_Idle_Anim
			aiGangOutside[nTempPed] = 0
			BREAK

		// Playing Idle Anims
		CASE 0
			GOSUB Crash1_GangOutside_Update_Playing_Idle_Anims
			BREAK

		// Insult and Going Closer
		CASE 1
			GOSUB Crash1_GangOutside_Update_Insult_And_Going_Closer
			BREAK

		// Orientate
		CASE 2
			GOSUB Crash1_GangOutside_Update_Orientating
			BREAK

		// Chasing (obsolete)
		CASE 3
			BREAK

		// Shooting
		CASE 4
			GOSUB Crash1_GangOutside_Update_Shooting
			BREAK

		// Possible Teleport
		CASE 5
			GOSUB Crash1_GangOutside_Possible_Teleport
			BREAK

		// Stand Still during cutscene
		CASE 6
			GOSUB Crash1_GangOutside_Stand_Still
			BREAK
	
	ENDSWITCH

RETURN


// ****************************************
// GANG OUTSIDE PLAY IDLE ANIMS

Crash1_GangOutside_Play_Idle_Anim:

	SWITCH nTempPed

		// Mechanic
		CASE 0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE charGangOutside[nTempPed] wash_up BD_FIRE 1.0 TRUE FALSE FALSE FALSE -1
			BREAK

		// Phone Guy
		CASE 1
			TASK_PLAY_ANIM charGangOutside[nTempPed] phone_talk PED 1.0 TRUE FALSE FALSE FALSE -1
			BREAK

		// Smokers
		DEFAULT
			TASK_PLAY_ANIM charGangOutside[nTempPed] M_smklean_loop BD_FIRE 1.0 TRUE FALSE FALSE FALSE -1

	ENDSWITCH

RETURN


// ****************************************
// GANG OUTSIDE UPDATE PLAYING IDLE ANIMS

Crash1_GangOutside_Update_Playing_Idle_Anims:

	// IF the house is burning, then Shoot
	// IF there has been a projectile and a fire in the area, then Shoot
	// IF a call for help has been issued, then Orientate
	// IF a friend injured or dead then Shoot
	// IF myself injured then Shoot
	// OTHERWISE IF the player has not been detected, then check for player detection

	// Is the house burning?
	IF flagHouseBurning = 1
		GOSUB Crash1_GangOutside_Shoot
		RETURN
	ENDIF

	// Has there been a projectile and a fire in the area
	// (ie: has the player thrown a molotov)
	IF flagProjectileAroundHouse = 1
	AND flagFireAroundHouse = 1
		GOSUB Crash1_GangOutside_Shoot
		RETURN
	ENDIF

	// Has there been a call for help?
	IF flagGangOutsideCallForHelp = 1
		GOSUB Crash1_GangOutside_Orientate
		RETURN
	ENDIF

	// Has a friend been injured or killed?
	IF flagGangOutsideInjuredOrDead = 1
		GOSUB Crash1_GangOutside_Shoot
		RETURN
	ENDIF

	// Have I been injured?
	IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR charGangOutside[nTempPed] scplayer
		GOSUB Crash1_GangOutside_Shoot
		flagGangOutsideInjuredOrDead = 1
		RETURN
	ENDIF

	// Is this gang member the first to detect the player?
	// Do nothing else if the player has already been detected by someone
	IF flagGangOutsidePlayerDetected = 1
		RETURN
	ENDIF


	// Check if any gang member (except the mechanic) has spotted the Player
	SWITCH nTempPed

		// Mechanic
		CASE 0
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D charGangOutside[nTempPed] scplayer 3.0 3.0 FALSE
				GOSUB Crash1_GangOutside_Insult_And_Go_Closer
			ENDIF
			BREAK

		// Phone Guy
		CASE 1
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D charGangOutside[nTempPed] scplayer 3.0 3.0 FALSE
				GOSUB Crash1_GangOutside_Insult_And_Go_Closer
			ENDIF
			BREAK

		// Front Door guy
		CASE 2
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D charGangOutside[nTempPed] scplayer 3.0 3.0 FALSE
				GOSUB Crash1_GangOutside_Insult_And_Go_Closer
			ENDIF
			BREAK


		// Tree guy
		CASE 3
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D charGangOutside[nTempPed] scplayer 3.0 3.0 FALSE
				GOSUB Crash1_GangOutside_Insult_And_Go_Closer
			ENDIF
			BREAK


		// Other House guy
		CASE 4
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D charGangOutside[nTempPed] scplayer 3.0 3.0 FALSE
				GOSUB Crash1_GangOutside_Insult_And_Go_Closer
			ENDIF
			BREAK

	ENDSWITCH

RETURN


// ****************************************
// GANG OUTSIDE INSULT AND GO CLOSER
Crash1_GangOutside_Insult_And_Go_Closer:

	// SHOUT: Take this punk down
	SET_CHAR_SAY_CONTEXT charGangOutside[nTempPed] CONTEXT_GLOBAL_ABUSE_GANG_LSV nIgnore

	// ...the tree guy should not run to the player because he looks really stupid, all others should
	//		unless the player is in a car
	IF NOT IS_CHAR_IN_ANY_CAR scplayer
		// ...run towards the player
		TASK_GOTO_CHAR charGangOutside[nTempPed] scplayer 5000 3.0
	ENDIF

	
	// Housekeeping
	aiGangOutside[nTempPed] = 1
	flagGangOutsidePlayerDetected = 1

RETURN


// ****************************************
// GANG OUTSIDE UPDATE INSULT AND GOING CLOSER

Crash1_GangOutside_Update_Insult_And_Going_Closer:

	// Check if reached player

	// Check if the TASK_GOTO_CHAR is still active
	GET_SCRIPT_TASK_STATUS charGangOutside[nTempPed] TASK_GOTO_CHAR m_status

	IF m_status = FINISHED_TASK
		// ...task has ended

		// Set the 'call for help' flag so that other gang members respond
		flagGangOutsideCallForHelp = 1

		// Make this gang member move into 'close to player' mode
		GOSUB Crash1_GangOutside_Shoot

	ENDIF

RETURN


// ****************************************
// GANG OUTSIDE SHOOT

Crash1_GangOutside_Shoot:

	// Make the gang member enter a shooting phase

	// Clear current tasks
	CLEAR_CHAR_TASKS_IMMEDIATELY charGangOutside[nTempPed]

	// Issue attacking task
	SET_CHAR_DECISION_MAKER charGangOutside[nTempPed] dmTough
	TASK_KILL_CHAR_ON_FOOT charGangOutside[nTempPed] scplayer

	aiGangOutside[nTempPed] = 4

RETURN


// ****************************************
// GANG OUTSIDE UPDATE SHOOTING

Crash1_GangOutside_Update_Shooting:

	// If the gang member is a distance away from the player, then swap to a GOTO task

	// If the Enter House cutscene is playing then change to the 'possible teleport' ai status
	IF flagEnterHouseCutscenePlaying = 1
		aiGangOutside[nTempPed] = 5
		RETURN
	ENDIF

	// Get Player Coords
	GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
	xloTemp = xposTemp
	yloTemp = yposTemp

	// Get Gang Member coordinates
	GET_CHAR_COORDINATES charGangOutside[nTempPed] xposTemp yposTemp zposTemp
	xhiTemp = xposTemp
	yhiTemp = yposTemp

	// Get the distance between the coordinates
	GET_DISTANCE_BETWEEN_COORDS_2D xloTemp yloTemp xhiTemp yhiTemp fTempFloat

	// If the distance gets too great, then swap to an ORIENTATE task
	IF fTempFloat > 38.000
		GOSUB Crash1_GangOutside_Orientate
		RETURN
	ENDIF

	// Otherwise, ensure the Ped doesn't switch out of the combat mode
	GET_SCRIPT_TASK_STATUS charGangOutside[nTempPed] TASK_KILL_CHAR_ON_FOOT m_status

	IF m_status = FINISHED_TASK
		// ...task shouldn't be finished, so re-issue
		GOSUB Crash1_GangOutside_Shoot
	ENDIF

RETURN


// ****************************************
// GANG OUTSIDE ORIENTATE

Crash1_GangOutside_Orientate:

	// Make the gang member Orientate

	// If the Enter House cutscene is playing then change to the 'possible teleport' ai status
	IF flagEnterHouseCutscenePlaying = 1
		aiGangOutside[nTempPed] = 5
		RETURN
	ENDIF

	// Clear current tasks
	CLEAR_CHAR_TASKS_IMMEDIATELY charGangOutside[nTempPed]

	// Get the Required Heading
	// ...get player coords
	GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
	xloTemp = xposTemp
	yloTemp = yposTemp

	// ...get Gang Member coords
	GET_CHAR_COORDINATES charGangOutside[nTempPed] xposTemp yposTemp zposTemp
	xhiTemp = xposTemp
	yhiTemp = yposTemp

	// Calculate Vector
	xposTemp = xloTemp - xhiTemp
	yposTemp = yloTemp - yhiTemp
	GET_HEADING_FROM_VECTOR_2D xposTemp yposTemp headTemp

	// Orientate
	TASK_ACHIEVE_HEADING charGangOutside[nTempPed] headTemp

	aiGangOutside[nTempPed] = 2

RETURN


// ****************************************
// GANG OUTSIDE UPDATE ORIENTATING

Crash1_GangOutside_Update_Orientating:

	// If close to player, then swap to a SHOOT task
	// Periodically, re-issue the Orientate task

	// Get Player Coords
	GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
	xloTemp = xposTemp
	yloTemp = yposTemp

	// Get Gang Member coordinates
	GET_CHAR_COORDINATES charGangOutside[nTempPed] xposTemp yposTemp zposTemp
	xhiTemp = xposTemp
	yhiTemp = yposTemp

	// Get the distance between the coordinates
	GET_DISTANCE_BETWEEN_COORDS_2D xloTemp yloTemp xhiTemp yhiTemp fTempFloat

	// If the distance is short, then swap to a SHOOT task
	IF fTempFloat < 35.0000
		GOSUB Crash1_GangOutside_Shoot
		RETURN
	ENDIF


	// Distance is not short, so check if it is time to re-issue the orientate task
	IF flagOrientate = 1
		GOSUB Crash1_GangOutside_Orientate
	ENDIF

RETURN


// ****************************************
// GANG OUTSIDE POSSIBLE TELEPORT

Crash1_GangOutside_Possible_Teleport:

	// Move the Gang Outside member to a temporary hidden position for the cutscene
	GOSUB Crash1_GangOutside_Hide_During_Cutscene

	// Get the ped to stand still
	CLEAR_CHAR_TASKS_IMMEDIATELY charGangOutside[nTempPed]
	SET_CHAR_DECISION_MAKER charGangOutside[nTempPed] dmEmpty
	TASK_STAND_STILL charGangOutside[nTempPed] -2

	aiGangOutside[nTempPed] = 6

	// NOTE: Ned to freeze the ped in place, but do this on the next frame

RETURN


// ****************************************
// GANG OUTSIDE STAND STILL

Crash1_GangOutside_Stand_Still:

	// While the cutscene is still playing, stay standing still
	IF flagEnterHouseCutscenePlaying = 1
		// Ensure still playing stand still
		GET_SCRIPT_TASK_STATUS charGangOutside[nTempPed] TASK_STAND_STILL m_status

		IF m_status = FINISHED_TASK
			CLEAR_CHAR_TASKS_IMMEDIATELY charGangOutside[nTempPed]
			TASK_STAND_STILL charGangOutside[nTempPed] -2
		ENDIF
		RETURN
	ENDIF

	// Swap to a Shooting task
	CLEAR_CHAR_TASKS_IMMEDIATELY charGangOutside[nTempPed]
	SET_CHAR_DECISION_MAKER charGangOutside[nTempPed] dmTough
	TASK_KILL_CHAR_ON_FOOT charGangOutside[nTempPed] scplayer

	aiGangOutside[nTempPed] = 4

RETURN


// ****************************************
// GANG OUTSIDE HIDE DURING CUTSCENE

Crash1_GangOutside_Hide_During_Cutscene:

	// Get and store current coords
	GET_CHAR_COORDINATES charGangOutside[nTempPed] xposGangOutsideHold[nTempPed] yposGangOutsideHold[nTempPed] zposGangOutsideHold[nTempPed]

	// Move the Gang Outside member to a temporary hidden position
	// NOTE: There are 7 sets of coords because the Gang Backup guys will get moved also
	SWITCH countNextTempCutscenePosition
		CASE 0
			SET_CHAR_COORDINATES charGangOutside[nTempPed] 2319.3142 -1202.8967 26.9609
			BREAK
		CASE 1
			SET_CHAR_COORDINATES charGangOutside[nTempPed] 2315.9771 -1204.6580 26.9609
			BREAK
		CASE 2
			SET_CHAR_COORDINATES charGangOutside[nTempPed] 2318.9915 -1207.2223 26.9615
			BREAK
		CASE 3
			SET_CHAR_COORDINATES charGangOutside[nTempPed] 2315.7451 -1210.2960 26.9609
			BREAK
		CASE 4
			SET_CHAR_COORDINATES charGangOutside[nTempPed] 2318.0896 -1212.9161 26.9609
			BREAK
		CASE 5
			SET_CHAR_COORDINATES charGangOutside[nTempPed] 2314.1089 -1215.6415 26.9609
			BREAK
		CASE 6
			SET_CHAR_COORDINATES charGangOutside[nTempPed] 2316.0420 -1219.5007 26.9609
			BREAK
		DEFAULT
			RETURN
	ENDSWITCH

	countNextTempCutscenePosition++


RETURN


// ****************************************
// GANG OUTSIDE HIDE DURING CUTSCENE

Crash1_GangOutside_Move_Back_After_Cutscene:

	// Store the original coords
	REPEAT 5 nLoop
		IF NOT IS_CHAR_DEAD charGangOutside[nLoop]
			SET_CHAR_COORDINATES charGangOutside[nLoop] xposGangOutsideHold[nLoop] yposGangOutsideHold[nLoop] zposGangOutsideHold[nLoop]
		ENDIF
	ENDREPEAT

RETURN




// *************************************************************************************************************
//								        TARGET ROOMS
// *************************************************************************************************************

// ****************************************
// SETUP TARGET ROOMS
Crash1_Setup_Target_Rooms:

	// Set up invisible ammo boxes so that blips can be attached to show which windows need smashed
	// ...window 0
	xposTemp = 2346.0469
	yposTemp = -1189.6610
	zposTemp = 27.9709

	nTempInt = 0
	CREATE_OBJECT CR_AMMOBOX xposTemp yposTemp zposTemp objectTargetRoom[nTempInt]
	SET_OBJECT_VISIBLE objectTargetRoom[nTempInt] FALSE
	SET_OBJECT_COLLISION objectTargetRoom[nTempInt] FALSE
	ADD_BLIP_FOR_OBJECT objectTargetRoom[nTempInt] blipTargetRoom[nTempInt]
	CHANGE_BLIP_COLOUR blipTargetRoom[nTempInt] RED
	SET_OBJECT_HEALTH objectTargetRoom[nTempInt] 1


	// ...window 1
	xposTemp = 2336.0449
	yposTemp = -1189.6610
	zposTemp = 27.9709

	nTempInt++
	CREATE_OBJECT CR_AMMOBOX xposTemp yposTemp zposTemp objectTargetRoom[nTempInt]
	SET_OBJECT_VISIBLE objectTargetRoom[nTempInt] FALSE
	SET_OBJECT_COLLISION objectTargetRoom[nTempInt] FALSE
	ADD_BLIP_FOR_OBJECT objectTargetRoom[nTempInt] blipTargetRoom[nTempInt]
	CHANGE_BLIP_COLOUR blipTargetRoom[nTempInt] RED
	SET_OBJECT_HEALTH objectTargetRoom[nTempInt] 1


	// ...window 2
	xposTemp = 2326.4087
	yposTemp = -1189.6610
	zposTemp = 27.9709

	nTempInt++
	CREATE_OBJECT CR_AMMOBOX xposTemp yposTemp zposTemp objectTargetRoom[nTempInt]
	SET_OBJECT_VISIBLE objectTargetRoom[nTempInt] FALSE
	SET_OBJECT_COLLISION objectTargetRoom[nTempInt] FALSE
	ADD_BLIP_FOR_OBJECT objectTargetRoom[nTempInt] blipTargetRoom[nTempInt]
	CHANGE_BLIP_COLOUR blipTargetRoom[nTempInt] RED
	SET_OBJECT_HEALTH objectTargetRoom[nTempInt] 1


	// ...window 3
	xposTemp = 2345.8977
	yposTemp = -1170.0438
	zposTemp = 27.9709

	nTempInt++
	CREATE_OBJECT CR_AMMOBOX xposTemp yposTemp zposTemp objectTargetRoom[nTempInt]
	SET_OBJECT_VISIBLE objectTargetRoom[nTempInt] FALSE
	SET_OBJECT_COLLISION objectTargetRoom[nTempInt] FALSE
	ADD_BLIP_FOR_OBJECT objectTargetRoom[nTempInt] blipTargetRoom[nTempInt]
	CHANGE_BLIP_COLOUR blipTargetRoom[nTempInt] RED
	SET_OBJECT_HEALTH objectTargetRoom[nTempInt] 1


	// ...window 4
	xposTemp = 2326.4067
	yposTemp = -1170.0438
	zposTemp = 27.9709

	nTempInt++
	CREATE_OBJECT CR_AMMOBOX xposTemp yposTemp zposTemp objectTargetRoom[nTempInt]
	SET_OBJECT_VISIBLE objectTargetRoom[nTempInt] FALSE
	SET_OBJECT_COLLISION objectTargetRoom[nTempInt] FALSE
	ADD_BLIP_FOR_OBJECT objectTargetRoom[nTempInt] blipTargetRoom[nTempInt]
	CHANGE_BLIP_COLOUR blipTargetRoom[nTempInt] RED
	SET_OBJECT_HEALTH objectTargetRoom[nTempInt] 1

RETURN


// ****************************************
// SETUP HIT TARGET ROOM AREAS

Crash1_Setup_Target_Room_Hit_Areas:

	// Setup the Areas that the molotovs need to hit to be classed as setting the room on fire
	// ...window 0
	nTempInt = 0
	xloAreaHitTargetRoom[nTempInt]	= 	2345.9788	- 2.1000
	xhiAreaHitTargetRoom[nTempInt]	= 	2345.9788	+ 2.1000
	yloAreaHitTargetRoom[nTempInt]	= 	-1188.8		- 0.8000
	yhiAreaHitTargetRoom[nTempInt]	= 	-1188.8		+ 0.8000
	zloAreaHitTargetRoom[nTempInt]	= 	28.8649		- 1.2000
	zhiAreaHitTargetRoom[nTempInt]	= 	28.8649		+ 1.2000
	flagTargetRoomHit[nTempInt]		=	FALSE
	flagTargetRoomBurning[nTempInt]	= 	FALSE


	// ...window 1
	nTempInt++
	xloAreaHitTargetRoom[nTempInt]	= 	2336.1875	- 2.1000
	xhiAreaHitTargetRoom[nTempInt]	= 	2336.1875	+ 2.1000
	yloAreaHitTargetRoom[nTempInt]	= 	-1188.8		- 0.8000
	yhiAreaHitTargetRoom[nTempInt]	= 	-1188.8		+ 0.8000
	zloAreaHitTargetRoom[nTempInt]	= 	28.8649		- 1.2000
	zhiAreaHitTargetRoom[nTempInt]	= 	28.8649		+ 1.2000
	flagTargetRoomHit[nTempInt]		=	FALSE
	flagTargetRoomBurning[nTempInt]	= 	FALSE


	// ...window 2
	nTempInt++
	xloAreaHitTargetRoom[nTempInt]	= 	2326.2551	- 2.1000
	xhiAreaHitTargetRoom[nTempInt]	= 	2326.2551	+ 2.1000
	yloAreaHitTargetRoom[nTempInt]	= 	-1188.8		- 0.8000
	yhiAreaHitTargetRoom[nTempInt]	= 	-1188.8		+ 0.8000
	zloAreaHitTargetRoom[nTempInt]	= 	28.8649		- 1.2000
	zhiAreaHitTargetRoom[nTempInt]	= 	28.8649		+ 1.2000
	flagTargetRoomHit[nTempInt]		=	FALSE
	flagTargetRoomBurning[nTempInt]	= 	FALSE


	// ...window 3
	nTempInt++
	xloAreaHitTargetRoom[nTempInt]	= 	2346.1160	- 2.1000
	xhiAreaHitTargetRoom[nTempInt]	= 	2346.1160	+ 2.1000
	yloAreaHitTargetRoom[nTempInt]	= 	-1171.0		- 0.8000
	yhiAreaHitTargetRoom[nTempInt]	= 	-1171.0		+ 0.8000
	zloAreaHitTargetRoom[nTempInt]	= 	28.8649		- 1.2000
	zhiAreaHitTargetRoom[nTempInt]	= 	28.8649		+ 1.2000
	flagTargetRoomHit[nTempInt]		=	FALSE
	flagTargetRoomBurning[nTempInt]	= 	FALSE


	// ...window 4
	nTempInt++
	xloAreaHitTargetRoom[nTempInt]	= 	2326.2551	- 2.1000
	xhiAreaHitTargetRoom[nTempInt]	= 	2326.2551	+ 2.1000
	yloAreaHitTargetRoom[nTempInt]	= 	-1171.0		- 0.8000
	yhiAreaHitTargetRoom[nTempInt]	= 	-1171.0		+ 0.8000
	zloAreaHitTargetRoom[nTempInt]	= 	28.8649		- 1.2000
	zhiAreaHitTargetRoom[nTempInt]	= 	28.8649		+ 1.2000
	flagTargetRoomHit[nTempInt]		=	FALSE
	flagTargetRoomBurning[nTempInt]	= 	FALSE

RETURN


// ****************************************
// UPDATE TARGET ROOMS HIT

Crash1_Update_Target_Rooms_Hit:

	// Check if this room already been hit.
	IF flagTargetRoomHit[nTempObject] = 1
		RETURN
	ENDIF

	
	// The room hasn't yet been hit, so check if it has now been hit
	IF IS_PROJECTILE_IN_AREA xloAreaHitTargetRoom[nTempObject] yloAreaHitTargetRoom[nTempObject] zloAreaHitTargetRoom[nTempObject] xhiAreaHitTargetRoom[nTempObject] yhiAreaHitTargetRoom[nTempObject] zhiAreaHitTargetRoom[nTempObject]

		// Smash the window if it isn't already smashed
		// NOTE: Although there are 8 windows, the first five are the ones for the rooms that need to be set on fire
		IF NOT HAS_OBJECT_BEEN_DAMAGED objectWindow[nTempObject]
			BREAK_OBJECT objectWindow[nTempObject] TRUE
		ENDIF

		// Mark this room as being on fire, and increase the total hit
		flagTargetRoomHit[nTempObject] = 1							  

		// Set the House Burning flag to make the Peds react
		flagHouseBurning = 1

		// Remove the blip
		DELETE_OBJECT objectTargetRoom[nTempObject]
		REMOVE_BLIP blipTargetRoom[nTempObject]

		// Start a timer saying target room hit
		timerTargetRoomHitText = m_mission_timer + 500
	ENDIF

RETURN


// ****************************************
// Choose Random 'Room Hit' text

Crash1_Print_Random_RoomHit_Text:

	// Print target room hit text
	GENERATE_RANDOM_INT_IN_RANGE 0 5 nTempInt
	SWITCH nTempInt
		CASE 0
			PRINT_NOW CRA1_30 4000 1
			BREAK
		CASE 1
			PRINT_NOW CRA1_31 4000 1
			BREAK
		CASE 2
			PRINT_NOW CRA1_32 4000 1
			BREAK
		CASE 3
			PRINT_NOW CRA1_33 4000 1
			BREAK
		CASE 4
			PRINT_NOW CRA1_34 4000 1
			BREAK
	ENDSWITCH

RETURN




// *************************************************************************************************************
//								        MOLOTOV PICKUPS
// *************************************************************************************************************

// ****************************************
// CHECK IF MOLOTOV PICKUPS REQUIRED

Crash1_CheckIf_Molotov_Pickups_Required:

	// If the Player has no ammo and there are still some rooms that need to be set on fire, then create pickups
	GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_MOLOTOV nTempAmmo
	IF nTempAmmo = 0
	AND countTargetRoomsHit < 5
		IF flagMoreMolotovsRequired = FALSE
			// Start a timer to allow this section to end without creating the molotovs if the last window
			//		is about to be broken
			timerMoreMolotovsRequired = m_mission_timer + 3000
			flagMoreMolotovsRequired = TRUE
			RETURN
		ENDIF

		// Has the timer elapsed?
		IF NOT timerMoreMolotovsRequired < m_mission_timer
			RETURN
		ENDIF

		// Reset the variables
		timerMoreMolotovsRequired = 0
		flagMoreMolotovsRequired = FALSE

		// Generate pickups
		// ...molotov pickup 0
		xposTemp = 2337.6023
		yposTemp = -1169.3689
		zposTemp = 28.2207

		nTempInt = 0
		CREATE_PICKUP_WITH_AMMO MOLOTOV PICKUP_ONCE 20 xposTemp yposTemp zposTemp pickupMolotovs[nTempInt]
		ADD_BLIP_FOR_PICKUP pickupMolotovs[nTempInt] blipMolotovPickups[nTempInt]


		// ...molotov pickup 1
		xposTemp = 2334.1289
		yposTemp = -1200.4379
		zposTemp = 28.1609

		nTempInt++
		CREATE_PICKUP_WITH_AMMO MOLOTOV PICKUP_ONCE 20 xposTemp yposTemp zposTemp pickupMolotovs[nTempInt]
		ADD_BLIP_FOR_PICKUP pickupMolotovs[nTempInt] blipMolotovPickups[nTempInt]


		// Temporarily remove the Target Rooms blips
		REPEAT 5 nLoop
			IF flagTargetRoomHit[nLoop] = 0
				REMOVE_BLIP blipTargetRoom[nLoop]
			ENDIF
		ENDREPEAT


		// There are more molotovs nearby
		PRINT_NOW CRA1_03 5000 1


		flagMolotovPickupsActive = 1
	ENDIF

RETURN


// ****************************************
// CHECK IF MOLOTOV PICKUPS COLLECTED

Crash1_CheckIf_Molotov_Pickups_Collected:

	// If either pickup has been collected, then remove both pickups
	IF HAS_PICKUP_BEEN_COLLECTED pickupMolotovs[0]
	OR HAS_PICKUP_BEEN_COLLECTED pickupMolotovs[1]
		// Remove Pickups
		REPEAT 2 nLoop
			REMOVE_PICKUP pickupMolotovs[nLoop]
			REMOVE_BLIP blipMolotovPickups[nLoop]
		ENDREPEAT


		// Add the Target Rooms blips again
		REPEAT 5 nLoop
			IF flagTargetRoomHit[nLoop] = 0
				ADD_BLIP_FOR_OBJECT objectTargetRoom[nLoop] blipTargetRoom[nLoop]
				CHANGE_BLIP_COLOUR blipTargetRoom[nLoop] RED
			ENDIF
		ENDREPEAT

		flagMolotovPickupsActive = 0
	ENDIF

RETURN




// *************************************************************************************************************
//								        "GANG BACKUP" GUYS
// *************************************************************************************************************

// ****************************************
// UPDATE GANG BACKUP CAR

Crash1_Update_GangBackup:

	// AI STATUS
	// -1:	Not Initialised
	//  0:	Car Doing Goto
	//  1:	Car Doing Left Turn
	//  2:	Car Doing Handbrake Turn
	//  3:	Gang exiting car
	//  4:	Gang Backup On Foot
	//	5:	Gang Backup During Cutscene


	SWITCH aiGangBackup

		// Not Initialised
		CASE -1
			GOSUB Crash1_Create_GangBackup
			BREAK

		// Car Doing Goto
		CASE 0
			GOSUB Crash1_GangBackup_Car_Doing_Goto
			BREAK

		// Car Doing Turn
		CASE 1
			GOSUB Crash1_GangBackup_Car_Turning
			BREAK

		// Car Doing Handbreak Turn
		CASE 2
			GOSUB Crash1_GangBackup_Car_Handbraking
			BREAK

		// Gang exiting car
		CASE 3
			GOSUB Crash1_GangBackup_Exiting_Car
			BREAK

		// Gang on Foot
		CASE 4
			GOSUB Crash1_GangBackup_On_Foot
			BREAK

		// Gang during cutscene
		CASE 5
			GOSUB Crash1_GangBackup_During_Cutscene
			BREAK

	ENDSWITCH

RETURN


// *******************************
// Gang Backup Car and Peds
Crash1_Create_GangBackup:

	IF NOT countTargetRoomsHit = 2
		RETURN
	ENDIF


	// The Car
	xposTemp = 2370.7917	//2416.7803
	yposTemp = -1250.8990	//-1158.0902
	zposTemp = 22.8451		//30.0039
	headTemp = 358.0048		//90.0406

	CLEAR_AREA xposTemp yposTemp zposTemp 10.0 FALSE
	CREATE_CAR VOODOO xposTemp yposTemp zposTemp carGangBackup
	SET_CAR_HEADING carGangBackup headTemp
	SET_CAR_CRUISE_SPEED carGangBackup 100.0
	SET_CAR_DRIVING_STYLE carGangBackup 2


	// The Peds
	// ...gang backup 0
	nTempInt = 0
	CREATE_CHAR_INSIDE_CAR carGangBackup PEDTYPE_GANG_SMEX LSV2 charGangBackup[nTempInt]
  	GIVE_WEAPON_TO_CHAR charGangBackup[nTempInt] WEAPONTYPE_PISTOL 999
	SET_CHAR_IS_TARGET_PRIORITY charGangBackup[nTempInt] TRUE

	// ...gang backup 1
	nTempInt++
	CREATE_CHAR_AS_PASSENGER carGangBackup PEDTYPE_GANG_SMEX LSV1 0 charGangBackup[nTempInt]
	GIVE_WEAPON_TO_CHAR charGangBackup[nTempInt] WEAPONTYPE_PISTOL 999
	SET_CHAR_IS_TARGET_PRIORITY charGangBackup[nTempInt] TRUE


	// Get the Car to the House
	xposGangBackup = 2370.7917	//2376.2666
	yposGangBackup = -1185.6624	//-1155.0165
	zposGangBackup = 26.4297	//26.4531
	CAR_GOTO_COORDINATES carGangBackup xposGangBackup yposGangBackup zposGangBackup


	// Housekeeping
	aiGangBackup = 0
	existsGangBackup = 1

RETURN


// ****************************************
// GANG BACKUP CAR DOING GOTO

Crash1_GangBackup_Car_Doing_Goto:

	// If car is dead, go to ped actions
	IF IS_CAR_DEAD carGangBackup
		aiGangBackup = 2
		RETURN
	ENDIF

	// Car not dead, so see if it has arrived at it's destination
	IF LOCATE_CAR_2D carGangBackup xposGangBackup yposGangBackup 6.0 6.0 FALSE
		// ...arrived, so do a turn
		SET_CAR_TEMP_ACTION	carGangBackup TEMPACT_TURNLEFT 500
		timerGangBackup = m_mission_timer + 350
		
		aiGangBackup = 1
	ENDIF

RETURN


// ****************************************
// GANG BACKUP CAR TURNING

Crash1_GangBackup_Car_Turning:

	// If car is dead, go to ped actions
	IF IS_CAR_DEAD carGangBackup
		aiGangBackup = 2
		RETURN
	ENDIF

	// Check if finished performing turn
	IF timerGangBackup < m_mission_timer
		// ...timer elapsed, so do a handbrake turn
		SET_CAR_TEMP_ACTION	carGangBackup TEMPACT_HANDBRAKETURNLEFT 5000
		
		aiGangBackup = 2
	ENDIF

RETURN


// ****************************************
// GANG BACKUP CAR HANDBRAKING

Crash1_GangBackup_Car_Handbraking:

	// If car is stopped, then move on to the next stage
	IF IS_CAR_DEAD carGangBackup
	OR IS_CAR_STOPPED carGangBackup
		// First gang member
		nTempPed = 0
		IF NOT IS_CHAR_DEAD charGangBackup[nTempPed]
			// Issue leave car
			TASK_LEAVE_CAR charGangBackup[nTempPed] carGangBackup
		ENDIF

		
		// Second gang member
		nTempPed++
		IF NOT IS_CHAR_DEAD charGangBackup[nTempPed]
			// Issue leave car
			TASK_LEAVE_CAR charGangBackup[nTempPed] carGangBackup
		ENDIF

		aiGangBackup = 3
	ENDIF

RETURN


// ****************************************
// GANG BACKUP EXIT CAR

Crash1_GangBackup_Exiting_Car:

	// Check if both gang members have exited the car
	// ...member 0
	nTempPed = 0
	IF NOT IS_CHAR_DEAD charGangBackup[nTempPed]
		// Check if performing "leave car"
		GET_SCRIPT_TASK_STATUS charGangBackup[nTempPed] TASK_LEAVE_CAR m_status

		IF NOT m_status = FINISHED_TASK
			RETURN
		ENDIF
	ENDIF


	// ...member 1
	nTempPed++
	IF NOT IS_CHAR_DEAD charGangBackup[nTempPed]
		// Check if performing "leave car"
		GET_SCRIPT_TASK_STATUS charGangBackup[nTempPed] TASK_LEAVE_CAR m_status

		IF NOT m_status = FINISHED_TASK
			RETURN
		ENDIF
	ENDIF

	// Noth are either dead or have exited the car
	aiGangBackup = 4

RETURN


// ****************************************
// GANG BACKUP ON FOOT

Crash1_GangBackup_On_Foot:

	// Check if Cutscene playing
	IF flagEnterHouseCutscenePlaying = 1
		nTempPed = 0
		IF NOT IS_CHAR_DEAD charGangBackup[nTempPed]
			CLEAR_CHAR_TASKS_IMMEDIATELY charGangBackup[nTempPed]
			SET_CHAR_DECISION_MAKER charGangBackup[nTempPed] dmEmpty
			TASK_STAND_STILL charGangBackup[nTempPed] -2
		ENDIF

		nTempPed++
		IF NOT IS_CHAR_DEAD charGangBackup[nTempPed]
			CLEAR_CHAR_TASKS_IMMEDIATELY charGangBackup[nTempPed]
			SET_CHAR_DECISION_MAKER charGangBackup[nTempPed] dmEmpty
			TASK_STAND_STILL charGangBackup[nTempPed] -2
		ENDIF


		// Move them out of the way during the cutscene
		GOSUB Crash1_GangBackup_Hide_During_Cutscene

		aiGangBackup = 5
		RETURN
	ENDIF

	// Update first gang member
	nTempPed = 0
	IF NOT IS_CHAR_DEAD charGangBackup[nTempPed]
		// Check if performing "kill player"
		GET_SCRIPT_TASK_STATUS charGangBackup[nTempPed] TASK_KILL_CHAR_ON_FOOT m_status

		IF m_status = FINISHED_TASK
			// ...task shouldn't be finished, so re-issue
			CLEAR_CHAR_TASKS_IMMEDIATELY charGangBackup[nTempPed]

			// Issue attacking task
			SET_CHAR_DECISION_MAKER charGangBackup[nTempPed] dmTough
			TASK_KILL_CHAR_ON_FOOT charGangBackup[nTempPed] scplayer
		ENDIF
	ENDIF

	
	// Update second gang member
	nTempPed++
	IF NOT IS_CHAR_DEAD charGangBackup[nTempPed]
		// Check if performing "kill player"
		GET_SCRIPT_TASK_STATUS charGangBackup[nTempPed] TASK_KILL_CHAR_ON_FOOT m_status

		IF m_status = FINISHED_TASK
			// ...task shouldn't be finished, so re-issue
			CLEAR_CHAR_TASKS_IMMEDIATELY charGangBackup[nTempPed]

			// Issue attacking task
			SET_CHAR_DECISION_MAKER charGangBackup[nTempPed] dmTough
			TASK_KILL_CHAR_ON_FOOT charGangBackup[nTempPed] scplayer
		ENDIF
	ENDIF

RETURN


// ****************************************
// GANG BACKUP DURING CUTSCENE

Crash1_GangBackup_During_Cutscene:

	// While the cutscene is still playing, stay standing still
	IF flagEnterHouseCutscenePlaying = 1
		// Ensure still playing stand still
		nTempPed = 0
		IF NOT IS_CHAR_DEAD charGangBackup[nTempPed]
			GET_SCRIPT_TASK_STATUS charGangBackup[nTempPed] TASK_STAND_STILL m_status

			IF m_status = FINISHED_TASK
				CLEAR_CHAR_TASKS_IMMEDIATELY charGangBackup[nTempPed]
				TASK_STAND_STILL charGangBackup[nTempPed] -2
			ENDIF
		ENDIF

		nTempPed++
		IF NOT IS_CHAR_DEAD charGangBackup[nTempPed]
			GET_SCRIPT_TASK_STATUS charGangBackup[nTempPed] TASK_STAND_STILL m_status

			IF m_status = FINISHED_TASK
				CLEAR_CHAR_TASKS_IMMEDIATELY charGangBackup[nTempPed]
				TASK_STAND_STILL charGangBackup[nTempPed] -2
			ENDIF
		ENDIF
		RETURN
	ENDIF

	// Swap to a Shooting task
	nTempPed = 0
	IF NOT IS_CHAR_DEAD charGangBackup[nTempPed]
		CLEAR_CHAR_TASKS_IMMEDIATELY charGangBackup[nTempPed]
		SET_CHAR_DECISION_MAKER charGangBackup[nTempPed] dmTough
		TASK_KILL_CHAR_ON_FOOT charGangBackup[nTempPed] scplayer
	ENDIF

	nTempPed++
	IF NOT IS_CHAR_DEAD charGangBackup[nTempPed]
		CLEAR_CHAR_TASKS_IMMEDIATELY charGangBackup[nTempPed]
		SET_CHAR_DECISION_MAKER charGangBackup[nTempPed] dmTough
		TASK_KILL_CHAR_ON_FOOT charGangBackup[nTempPed] scplayer
	ENDIF

	aiGangBackup = 4

RETURN


// ****************************************
// GANG BACKUP HIDE DURING CUTSCENE

Crash1_GangBackup_Hide_During_Cutscene:

	REPEAT 2 nLoop
		IF NOT IS_CHAR_DEAD charGangBackup[nLoop]
			// Get and store current coords
			GET_CHAR_COORDINATES charGangBackup[nLoop] xposGangBackupHold[nLoop] yposGangBackupHold[nLoop] zposGangBackupHold[nLoop]

			// Move the Gang Outside member to a temporary hidden position
			// NOTE: There are 7 sets of coords because the Gang Outside guys will get moved also
			SWITCH countNextTempCutscenePosition
				CASE 0
					SET_CHAR_COORDINATES charGangBackup[nLoop] 2319.3142 -1202.8967 26.9609
					BREAK
				CASE 1
					SET_CHAR_COORDINATES charGangBackup[nLoop] 2315.9771 -1204.6580 26.9609
					BREAK
				CASE 2
					SET_CHAR_COORDINATES charGangBackup[nLoop] 2318.9915 -1207.2223 26.9615
					BREAK
				CASE 3
					SET_CHAR_COORDINATES charGangBackup[nLoop] 2315.7451 -1210.2960 26.9609
					BREAK
				CASE 4
					SET_CHAR_COORDINATES charGangBackup[nLoop] 2318.0896 -1212.9161 26.9609
					BREAK
				CASE 5
					SET_CHAR_COORDINATES charGangBackup[nLoop] 2314.1089 -1215.6415 26.9609
					BREAK
				CASE 6
					SET_CHAR_COORDINATES charGangBackup[nLoop] 2316.0420 -1219.5007 26.9609
					BREAK
				DEFAULT
					RETURN
			ENDSWITCH

			countNextTempCutscenePosition++
		ENDIF
	ENDREPEAT

RETURN


// ****************************************
// GANG BACKUP HIDE DURING CUTSCENE

Crash1_GangBackup_Move_Back_After_Cutscene:

	// Store the original coords
	REPEAT 2 nLoop
		IF NOT IS_CHAR_DEAD charGangBackup[nLoop]
			SET_CHAR_COORDINATES charGangBackup[nLoop] xposGangBackupHold[nLoop] yposGangBackupHold[nLoop] zposGangBackupHold[nLoop]
		ENDIF
	ENDREPEAT

RETURN




// *************************************************************************************************************
//								        ENTER HOUSE CUTSCENE
// *************************************************************************************************************


// ****************************************
// CREATE GUY ON FIRE
Crash1_Enter_House_Cutscene_Begin:

	SET_PLAYER_CONTROL player1 OFF
	SET_EVERYONE_IGNORE_PLAYER player1 TRUE
	SWITCH_WIDESCREEN ON
	SET_ALL_CARS_CAN_BE_DAMAGED FALSE

	// Cut to guy bursting out of the front door on fire
	SET_FIXED_CAMERA_POSITION 2335.3389 -1159.1302 30.4689 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 2336.1541 -1159.7073 30.4200 JUMP_CUT

	DO_FADE 1000 FADE_IN
	timerCutscene = m_mission_timer + 1500

RETURN


// ****************************************
// UPDATE FIRES
Crash1_Update_Fires_Exterior:

	IF statusFires = CRASH1_EXTERIOR_FIRE_ALL
		REMOVE_ALL_SCRIPT_FIRES
	ENDIF


	// NOTE: Randomly choose the size of the fire (between 1 and 3)

	IF statusFires = CRASH1_EXTERIOR_FIRE_ALL
	OR statusFires = CRASH1_EXTERIOR_FIRE_SMALL_BEDROOM
		GENERATE_RANDOM_INT_IN_RANGE 2 4 nTempInt
		START_SCRIPT_FIRE 2346.3552 -1172.7339 27.2957 1 nTempInt fireInHouse[0]

		GENERATE_RANDOM_INT_IN_RANGE 1 4 nTempInt
		START_SCRIPT_FIRE 2345.4836 -1171.8418 27.2957 0 nTempInt fireInHouse[1]

		GENERATE_RANDOM_INT_IN_RANGE 2 4 nTempInt
		START_SCRIPT_FIRE 2348.1709 -1173.4933 27.2957 0 nTempInt fireInHouse[2]

		GENERATE_RANDOM_INT_IN_RANGE 2 4 nTempInt
		START_SCRIPT_FIRE 2348.3870 -1172.3396 27.2957 1 nTempInt fireInHouse[3]
	ENDIF

	IF statusFires = CRASH1_EXTERIOR_FIRE_ALL
	OR statusFires = CRASH1_EXTERIOR_FIRE_LIVING_ROOM
		GENERATE_RANDOM_INT_IN_RANGE 1 4 nTempInt
		START_SCRIPT_FIRE 2347.9832 -1187.0693 27.2891 0 nTempInt fireInHouse[4]

		GENERATE_RANDOM_INT_IN_RANGE 1 4 nTempInt
		START_SCRIPT_FIRE 2347.7915 -1186.2532 27.2891 1 nTempInt fireInHouse[5]

		GENERATE_RANDOM_INT_IN_RANGE 2 4 nTempInt
		START_SCRIPT_FIRE 2346.1501 -1186.6389 27.2891 0 nTempInt fireInHouse[6]

		GENERATE_RANDOM_INT_IN_RANGE 1 4 nTempInt
		START_SCRIPT_FIRE 2345.9875 -1188.3011 27.2891 1 nTempInt fireInHouse[7]
	ENDIF

	IF statusFires = CRASH1_EXTERIOR_FIRE_ALL
	OR statusFires = CRASH1_EXTERIOR_FIRE_BIG_BEDROOM
		GENERATE_RANDOM_INT_IN_RANGE 1 4 nTempInt
		START_SCRIPT_FIRE 2334.9724 -1187.8058 27.0820 1 nTempInt fireInHouse[8] 

		GENERATE_RANDOM_INT_IN_RANGE 1 4 nTempInt
		START_SCRIPT_FIRE 2334.1821 -1188.2694 27.0080 0 nTempInt fireInHouse[9] 

		GENERATE_RANDOM_INT_IN_RANGE 2 4 nTempInt
		START_SCRIPT_FIRE 2335.7856 -1188.2498 27.7429 1 nTempInt fireInHouse[10] 

		GENERATE_RANDOM_INT_IN_RANGE 1 4 nTempInt
		START_SCRIPT_FIRE 2335.4668 -1187.1431 27.5372 0 nTempInt fireInHouse[11] 
	ENDIF

	IF statusFires = CRASH1_EXTERIOR_FIRE_ALL
	OR statusFires = CRASH1_EXTERIOR_FIRE_EXTRA_ROOM
		GENERATE_RANDOM_INT_IN_RANGE 2 4 nTempInt
		START_SCRIPT_FIRE 2323.4312 -1185.6132 27.2891 1 nTempInt fireInHouse[12] 

		GENERATE_RANDOM_INT_IN_RANGE 1 4 nTempInt
		START_SCRIPT_FIRE 2324.5488 -1186.6141 27.2891 0 nTempInt fireInHouse[13] 

		GENERATE_RANDOM_INT_IN_RANGE 1 4 nTempInt
		START_SCRIPT_FIRE 2325.3708 -1184.3833 27.2891 1 nTempInt fireInHouse[14] 

		GENERATE_RANDOM_INT_IN_RANGE 2 4 nTempInt
		START_SCRIPT_FIRE 2324.7622 -1187.4618 27.2891 0 nTempInt fireInHouse[15] 

		GENERATE_RANDOM_INT_IN_RANGE 1 3 nTempInt
		START_SCRIPT_FIRE 2324.7163 -1188.4775 30.5800 1 nTempInt fireInHouse[16] 
	ENDIF

	IF statusFires = CRASH1_EXTERIOR_FIRE_ALL
	OR statusFires = CRASH1_EXTERIOR_FIRE_KITCHEN
		GENERATE_RANDOM_INT_IN_RANGE 2 4 nTempInt
		START_SCRIPT_FIRE 2322.2388 -1172.9714 27.1409 0 nTempInt fireInHouse[17]

		GENERATE_RANDOM_INT_IN_RANGE 1 4 nTempInt
		START_SCRIPT_FIRE 2322.2205 -1174.7516 27.2031 0 nTempInt fireInHouse[18]

		GENERATE_RANDOM_INT_IN_RANGE 1 4 nTempInt
		START_SCRIPT_FIRE 2323.6958 -1174.4291 27.2131 1 nTempInt fireInHouse[19]

		GENERATE_RANDOM_INT_IN_RANGE 2 4 nTempInt
		START_SCRIPT_FIRE 2325.1592 -1172.7932 27.1539 1 nTempInt fireInHouse[20]
	ENDIF

	// NOTE: Fires 21; 22; and 23 are for blocking pathways

RETURN


// ****************************************
// UPDATE FIRES
Crash1_Update_Fires_Interior:

	// STATUS
	//	0: Remove ALL fires as seen from outside, and reactivate them 1000, higher
	//	1: Clear out and re-activate the fires in the first two rooms
	//			NOTE: These fires get put out automatically by code when I switch player control off
	//					to play the 'get the fire extinguisher' cutscene

	IF statusFires = 0
		REMOVE_ALL_SCRIPT_FIRES
	ENDIF

	IF statusFires = 1
		REPEAT 8 nLoop
			REMOVE_SCRIPT_FIRE fireInHouse[nLoop]
		ENDREPEAT
	ENDIF


	// NOTE: Randomly choose the size of the fire (between 1 and 3)



	// *****************************
	//
	// 26/6/04
	// TEMP: Only use size 1 and 2 because size 3 are HUGE
	// NOTE: So this will be a random int between 1 and 3 to produce results of 1 or 2
	//
	// 28/6/04
	// Fires are no longer HUGE, so selevctively use some size 3's
	//
	// *****************************



	//pickup room 1
	GENERATE_RANDOM_INT_IN_RANGE 1 3 nTempInt
	START_SCRIPT_FIRE 2345.6462 -1178.3800 1027.1534 1 nTempInt fireInHouse[0]

	GENERATE_RANDOM_INT_IN_RANGE 1 3 nTempInt
	START_SCRIPT_FIRE 2344.5576 -1178.3342 1027.1247 0 nTempInt fireInHouse[1]

	GENERATE_RANDOM_INT_IN_RANGE 1 4 nTempInt
	START_SCRIPT_FIRE 2345.0271 -1177.2084 1027.1654 0 nTempInt fireInHouse[2]

	GENERATE_RANDOM_INT_IN_RANGE 1 3 nTempInt
	START_SCRIPT_FIRE 2347.8647 -1173.9291 1027.2237 1 nTempInt fireInHouse[3]

	//lounge - room 2
	GENERATE_RANDOM_INT_IN_RANGE 1 3 nTempInt
	START_SCRIPT_FIRE 2349.1365 -1187.9143 1027.2592 0 nTempInt fireInHouse[4]

	GENERATE_RANDOM_INT_IN_RANGE 1 4 nTempInt
	START_SCRIPT_FIRE 2348.7976 -1186.3055 1027.2812 1 nTempInt fireInHouse[5]

	GENERATE_RANDOM_INT_IN_RANGE 1 3 nTempInt
	START_SCRIPT_FIRE 2347.6250 -1187.3240 1027.2684 0 nTempInt fireInHouse[6]

	GENERATE_RANDOM_INT_IN_RANGE 1 4 nTempInt
	START_SCRIPT_FIRE 2347.5872 -1184.5962 1027.1338 1 nTempInt fireInHouse[7]

	// Only activate these if the status is 0
	IF statusFires = 0
		//bedroom - room 3
		GENERATE_RANDOM_INT_IN_RANGE 1 3 nTempInt
		START_SCRIPT_FIRE 2334.9724 -1187.8058 1027.1820 1 nTempInt fireInHouse[8] 

		GENERATE_RANDOM_INT_IN_RANGE 1 3 nTempInt
		START_SCRIPT_FIRE 2334.1821 -1188.2694 1027.1080 0 nTempInt fireInHouse[9] 

		GENERATE_RANDOM_INT_IN_RANGE 1 3 nTempInt
		START_SCRIPT_FIRE 2335.7856 -1188.2498 1027.7429 1 nTempInt fireInHouse[10] 

		GENERATE_RANDOM_INT_IN_RANGE 1 4 nTempInt
		START_SCRIPT_FIRE 2335.4668 -1187.1431 1027.5372 0 nTempInt fireInHouse[11] 

		//lounge - room 5
		GENERATE_RANDOM_INT_IN_RANGE 1 3 nTempInt
		START_SCRIPT_FIRE 2321.8201 -1179.5139 1027.7670 1 nTempInt fireInHouse[12] 

		GENERATE_RANDOM_INT_IN_RANGE 1 3 nTempInt
		START_SCRIPT_FIRE 2321.4541 -1181.0443 1027.2244 0 nTempInt fireInHouse[13] 

		GENERATE_RANDOM_INT_IN_RANGE 1 4 nTempInt
		START_SCRIPT_FIRE 2322.7886 -1181.0933 1027.1716 1 nTempInt fireInHouse[14] 

		GENERATE_RANDOM_INT_IN_RANGE 1 3 nTempInt
		START_SCRIPT_FIRE 2321.8521 -1183.0619 1027.1119 0 nTempInt fireInHouse[15] 

		GENERATE_RANDOM_INT_IN_RANGE 1 4 nTempInt
		START_SCRIPT_FIRE 2322.6111 -1184.0139 1027.1331 0 nTempInt fireInHouse[16] 

		//kitchen - room 6
		GENERATE_RANDOM_INT_IN_RANGE 1 3 nTempInt
		START_SCRIPT_FIRE 2322.2388 -1172.9714 1027.1409 0 nTempInt fireInHouse[17]

		GENERATE_RANDOM_INT_IN_RANGE 1 3 nTempInt
		START_SCRIPT_FIRE 2322.2205 -1174.7516 1027.2031 0 nTempInt fireInHouse[18]

		GENERATE_RANDOM_INT_IN_RANGE 1 4 nTempInt
		START_SCRIPT_FIRE 2323.6958 -1174.4291 1027.2131 1 nTempInt fireInHouse[19]

		GENERATE_RANDOM_INT_IN_RANGE 1 3 nTempInt
		START_SCRIPT_FIRE 2325.1592 -1172.7932 1027.1539 1 nTempInt fireInHouse[20]
	ENDIF

	// NOTE: Fires 21; 22; and 23 are for blocking pathways

RETURN


// ****************************************
// UPDATE BIG FIRES
Crash1_Update_Big_Fires:

	// States:
	//	0:	Create Cutscene fire in Girl's bedroom
	//	1:	Create Cutscene fire in Front Door
	//	2:	Remove Front Door fire again
	//	3:	Create all fires in their doorways
	//  4:	Move Girl's Room fire to her doorway
	//	5:	Record status because player has gone outside (only used while leading the lady out)
	//	6:	Reactivate the fires that should still be lit when player re-enters (only used while leading the lady out)




	// *****************************
	//
	// 26/6/04
	// TEMP: The kitchen door fire (fireInHouseBig[3]) has been temporarily reduced to a size 2
	//			because it was coming through the floor upstairs
	//
	// *****************************




	SWITCH statusBigFires
		// Create Cutscene fire in Girl's bedroom
		CASE 0
			START_SCRIPT_FIRE 2345.4644 -1174.0333 31.1000 0 3 fireInHouseBig[0]
			BREAK

		// Create Cutscene fire in Front Door (use a smaller fire)
		CASE 1
			START_SCRIPT_FIRE 2352.1000 -1171.2500 26.9700 0 2 fireInHouseBig[1]
			BREAK

		// Remove Cutscene fire in Front Door
		CASE 2
			REMOVE_SCRIPT_FIRE fireInHouseBig[1]
			BREAK

		// Create all fires to their doorways
		CASE 3
			START_SCRIPT_FIRE 2340.3645 -1184.9277 1031.1000 0 3 fireInHouseBig[1]
			START_SCRIPT_FIRE 2332.4829 -1180.8887 1031.1000 0 3 fireInHouseBig[2]
			START_SCRIPT_FIRE 2326.0811 -1178.8252 1027.2000 0 3 fireInHouseBig[3]
			BREAK

		// Move Girl's Room fire to her doorway
		CASE 4
			REMOVE_SCRIPT_FIRE fireInHouseBig[0]
			START_SCRIPT_FIRE 2344.8838 -1178.6970 1031.1000 0 3 fireInHouseBig[0]
			BREAK

		// Record status of fires
		CASE 5
			REPEAT 4 nLoop
				flagFireInHouseBigStillAlight[nLoop] = TRUE
				IF DOES_SCRIPT_FIRE_EXIST fireInHouseBig[nLoop]
					IF IS_SCRIPT_FIRE_EXTINGUISHED fireInHouseBig[nLoop]
						flagFireInHouseBigStillAlight[nLoop] = FALSE
					ENDIF
				ENDIF
			ENDREPEAT
			BREAK

		// Restore fires
		// NOTE: Need to always create a fire, but make previously extinguished ones small
		CASE 6
			nTempInt = 3
			IF flagFireInHouseBigStillAlight[0] = FALSE
				nTempInt = 1
			ENDIF
			START_SCRIPT_FIRE 2344.8838 -1178.6970 1031.1000 0 nTempInt fireInHouseBig[0]

			nTempInt = 3
			IF flagFireInHouseBigStillAlight[1] = FALSE
				nTempInt = 1
			ENDIF
			START_SCRIPT_FIRE 2340.3645 -1184.9277 1031.1000 0 nTempInt fireInHouseBig[1]

			nTempInt = 3
			IF flagFireInHouseBigStillAlight[2] = FALSE
				nTempInt = 1
			ENDIF
			START_SCRIPT_FIRE 2332.4829 -1180.8887 1031.1000 0 nTempInt fireInHouseBig[2]

			nTempInt = 3
			IF flagFireInHouseBigStillAlight[3] = FALSE
				nTempInt = 1
			ENDIF
			START_SCRIPT_FIRE 2326.0811 -1178.8252 1027.2000 0 nTempInt fireInHouseBig[3]
			BREAK

	ENDSWITCH

RETURN


// ****************************************
// UPDATE SMOKE
Crash1_Update_Smoke:

	// STATUS
	//	0: Create entrance smoke
	//	1: Remove entrance smoke
	//	2: Create smoke around player
	//	3: Remove smoke around player
	//	4: Create Coochie window smoke
	//	5: Remove Coochie window smoke
	//	6: Move entrance smoke

	SWITCH statusSmoke
		// Create entrance Smoke
		CASE 0
			// Outside Entrance Cutscene Smoke: create the fx system
			CREATE_FX_SYSTEM TEARGASAD 2351.7180 -1170.9823 26.7609 TRUE fxEntranceSmoke
			PLAY_FX_SYSTEM fxEntranceSmoke

			// Inside House Disguise Smoke: create the fx system
			CREATE_FX_SYSTEM TEARGAS 2351.8130 -1181.2899 1029.7834 TRUE fxDisguiseSmoke
			PLAY_FX_SYSTEM fxDisguiseSmoke
			BREAK

		// Remove entrance Smoke
		CASE 1
			KILL_FX_SYSTEM fxEntranceSmoke
			KILL_FX_SYSTEM fxDisguiseSmoke
			BREAK

		// Smoke around the player
		CASE 2
			// ...create the fx systems
		    CREATE_FX_SYSTEM_ON_CHAR TEARGAS scplayer  0.0  0.0 2.0	TRUE fxPlayerSmoke[0]	//directly above
			CREATE_FX_SYSTEM_ON_CHAR TEARGAS scplayer  0.0  2.0 1.8	TRUE fxPlayerSmoke[1]	// in front
			CREATE_FX_SYSTEM_ON_CHAR TEARGAS scplayer  0.0 -2.0 2.0	TRUE fxPlayerSmoke[2]	// behind
			CREATE_FX_SYSTEM_ON_CHAR TEARGAS scplayer  1.0  5.0 1.5	TRUE fxPlayerSmoke[3]	// 5m in front
			CREATE_FX_SYSTEM_ON_CHAR TEARGAS scplayer -1.0  8.0 1.8	TRUE fxPlayerSmoke[4]	// 8m in front
			CREATE_FX_SYSTEM_ON_CHAR TEARGAS scplayer  2.0  0.0 1.5	TRUE fxPlayerSmoke[5]	// side
			CREATE_FX_SYSTEM_ON_CHAR TEARGAS scplayer -2.0  0.0 1.5	TRUE fxPlayerSmoke[6]	//side
			CREATE_FX_SYSTEM_ON_CHAR TEARGAS scplayer -2.0  2.0 1.5	TRUE fxPlayerSmoke[7]	//front diag
			CREATE_FX_SYSTEM_ON_CHAR TEARGAS scplayer  2.0  2.0 1.5	TRUE fxPlayerSmoke[8]	//front diag
			// ...play the effects
			REPEAT 9 nLoop
				PLAY_FX_SYSTEM fxPlayerSmoke[nLoop]
			ENDREPEAT
			BREAK

		// Remove smoke around player
		CASE 3
			REPEAT 9 nLoop
				KILL_FX_SYSTEM fxPlayerSmoke[nLoop]
			ENDREPEAT
			BREAK

		// Create Coochie window Smoke
		CASE 4
			// create the fx system
			CREATE_FX_SYSTEM TEARGASAD 2345.0068 -1171.8123 33.9960 TRUE fxCoochieWindowSmoke
			PLAY_FX_SYSTEM fxCoochieWindowSmoke
			BREAK

		// Remove Coochie window Smoke
		CASE 5
			KILL_FX_SYSTEM fxCoochieWindowSmoke
			BREAK

		// Move the entrance smoke a bit farther back so that the player's view isn't obscured as he leaves
		CASE 6
			KILL_FX_SYSTEM fxEntranceSmoke
			CREATE_FX_SYSTEM TEARGASAD 2351.7180 -1175.1287 26.7609 TRUE fxEntranceSmoke
			PLAY_FX_SYSTEM fxEntranceSmoke
			BREAK
	ENDSWITCH

RETURN


// ****************************************
// CREATE FIRE EXTINGUISHER
Crash1_Setup_Fire_Extinguisher:

	xposFireEx = 2322.6394
	yposFireEx = -1176.5375
	zposFireEx = 1027.7566

	CREATE_PICKUP_WITH_AMMO FIRE_EX PICKUP_ONCE CRASH1_INFINITE_AMMO xposFireEx yposFireEx zposFireEx pickupFireExtinguisher
	flagFireExtinguisherPickupActive = 1

RETURN


// ****************************************
// CREATE INTERIOR DOORS
Crash1_Create_Interior_Doors:

	CREATE_OBJECT_NO_OFFSET cr1_door 2342.7078 -1177.8259 1031.0018 objectInteriorDoor[0]//girls room - bathroom
	CREATE_OBJECT_NO_OFFSET cr1_door 2338.4023 -1177.8323 1030.9852 objectInteriorDoor[1]//bathroom - girlsroom
	CREATE_OBJECT_NO_OFFSET cr1_door 2333.6650 -1173.9318 1030.9767 objectInteriorDoor[2]//bathroom - hallway west
	CREATE_OBJECT_NO_OFFSET cr1_door 2335.9680 -1179.4352 1030.9687 objectInteriorDoor[3]//bathroom - hallway south
	SET_OBJECT_HEADING objectInteriorDoor[3] 89.3975

	// Make sure the interior gets collision for these doors
	REPEAT 4 nLoop
		SET_OBJECT_AREA_VISIBLE objectInteriorDoor[nLoop] 5
	ENDREPEAT

	// Make sure that the doors can't be smashed
	REPEAT 4 nLoop
		SET_OBJECT_COLLISION_DAMAGE_EFFECT objectInteriorDoor[nLoop] FALSE
	ENDREPEAT

RETURN


// ****************************************
// CREATE GUY ON FIRE
Crash1_Create_Guy_On_Fire:

	xposTemp = 2352.3254
	yposTemp = -1179.7899
	zposTemp = 26.9766
	headTemp = 2.8064

	CREATE_CHAR PEDTYPE_GANG_SMEX LSV3 xposTemp yposTemp zposTemp charGuyOnFire

	// He starts off inside, so need the interior collisions
	SET_CHAR_AREA_VISIBLE charGuyOnFire 5

	SET_CHAR_HEADING charGuyOnFire headTemp
	SET_CHAR_DECISION_MAKER charGuyOnFire dmEmpty

	existsGuyOnFire = 1

RETURN


// ****************************************
// CREATE COOCHIE
Crash1_Create_Coochie:

	xposTemp = 2345.9072
	yposTemp = -1171.6591
	zposTemp = 30.9688
	headTemp = 359.0737

	CREATE_CHAR PEDTYPE_CIVFEMALE GANGRL3 xposTemp yposTemp zposTemp charCoochie

	// She starts off inside, so need the interior collision for her
	SET_CHAR_AREA_VISIBLE charCoochie 5

	SET_CHAR_HEADING charCoochie headTemp

	SET_CHAR_HEALTH	charCoochie 100
	SET_CHAR_NEVER_TARGETTED charCoochie TRUE
	SET_CHAR_PROOFS charCoochie FALSE TRUE TRUE FALSE FALSE

	SET_CHAR_RELATIONSHIP charCoochie ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_PLAYER1 
	FREEZE_CHAR_POSITION charCoochie TRUE

	// Play the looping anim
	TASK_PLAY_ANIM_NON_INTERRUPTABLE charCoochie BD_Panic_Loop BD_FIRE 4.0 true false false false -1
	nCurrentCoochieAnim = 0

	GENERATE_RANDOM_INT_IN_RANGE 500 2000 nTempInt
	timerCoochieAnim = m_mission_timer + nTempInt

	// Shut her up
	DISABLE_CHAR_SPEECH charCoochie FALSE

	existsCoochie = 1

RETURN


// ****************************************
// MAKE GUY ON FIRE EXIT HOUSE, AND EXPLODE DOORWAY
Crash1_Enter_House_Cutscene_Guy_On_Fire:

	// Explode the Door
	SET_OBJECT_PROOFS objectFrontDoor FALSE FALSE FALSE FALSE FALSE
	SET_OBJECT_COLLISION_DAMAGE_EFFECT objectFrontDoor TRUE
	SET_OBJECT_DYNAMIC objectFrontDoor TRUE
	SET_OBJECT_VELOCITY	objectFrontDoor 0.0 15.0 0.0
	BREAK_OBJECT objectFrontDoor TRUE

	// Create the Particle effect for exploding the front door
	CREATE_FX_SYSTEM explosion_door 2351.9185 -1171.0139 28.1817 TRUE fxFrontDoor
	PLAY_AND_KILL_FX_SYSTEM fxFrontDoor

	// Play the audio
	PLAY_MISSION_AUDIO CRASH1_SFX_SLOT


	// Create entrance smoke
	statusSmoke = 0
	GOSUB Crash1_Update_Smoke


	// Create Front Door fire
	statusBigFires = 1
	GOSUB Crash1_Update_Big_Fires


	// Set flag in case cutscene skipped
	flagCutsceneFrontDoorStuffDone = 1

	
	// Guy On Fire runs out of house
	IF IS_CHAR_DEAD charGuyOnFire
		RETURN
	ENDIF

	OPEN_SEQUENCE_TASK nSeqTask
		TASK_GO_STRAIGHT_TO_COORD -1 2352.1809 -1164.8928 26.4392 PEDMOVE_RUN -2
		TASK_DIE -1
	CLOSE_SEQUENCE_TASK nSeqTask

	PERFORM_SEQUENCE_TASK charGuyOnFire nSeqTask
	CLEAR_SEQUENCE_TASK nSeqTask

	// NOTE: The natural reactions of a guy on fire is to run away from the player. I need to make this guy
	//		fireproof so that he burns but doesn't do the normal 'Help I'm in fire' reactions
	SET_CHAR_PROOFS charGuyOnFire FALSE TRUE FALSE FALSE FALSE
	REMOVE_SCRIPT_FIRE fireGuyOnFire
	START_CHAR_FIRE charGuyOnFire fireGuyOnFire
	SET_CHAR_HEALTH	charGuyOnFire 55

	timerCutscene = m_mission_timer + 4000

RETURN


// ****************************************
// SHOW COOCHIE PANIC
Crash1_Enter_House_Cutscene_Coochie:

	// Cut to Coochie screaming in building
//	SET_FIXED_CAMERA_POSITION 2342.6807 -1167.5538 32.1681 0.0 0.0 0.0
//	POINT_CAMERA_AT_POINT 2343.5144 -1168.1018 32.2343 JUMP_CUT
	SET_FIXED_CAMERA_POSITION 2343.5598 -1168.5067 32.4408 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 2344.3914 -1169.0100 32.6753 JUMP_CUT
	
	timerCutscene = m_mission_timer + 200

RETURN


// ****************************************
// QUIT CUTSCENE
Crash1_Enter_House_Cutscene_Quit:

	SWITCH_ENTRY_EXIT GANG TRUE

	GET_CHAR_COORDINATES scplayer xposTemp yposTemp zposTemp
	LOAD_SCENE xposTemp yposTemp zposTemp
	SET_PLAYER_CONTROL player1 ON
	SET_EVERYONE_IGNORE_PLAYER player1 FALSE
	SWITCH_WIDESCREEN OFF
	SET_ALL_CARS_CAN_BE_DAMAGED TRUE
	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT
	SET_AREA_VISIBLE 0

RETURN


// ****************************************
// UPDATE COOCHIE PANIC ANIMS
Crash1_Update_Coochie_Panic_Anims:

	// Do nothing if coochie dead
	IF IS_CHAR_DEAD charCoochie
	OR nCurrentCoochieAnim = 99
		RETURN
	ENDIF

	// Do nothing if timer has not elapsed
	IF m_mission_timer < timerCoochieAnim
		RETURN
	ENDIF

	// Timer has elapsed
	// Is the looping anim playing?
	IF nCurrentCoochieAnim = 0
		IF flagPlayOnlyCoochieLoopingAnim = TRUE
			// ...just leave the looping anim playing
			timerCoochieAnim = m_mission_timer + 120000
		ELSE
			// ...yes, so choose a new one-shot to play
			GENERATE_RANDOM_INT_IN_RANGE 1 5 nCurrentCoochieAnim
		ENDIF
	ELSE
		// ...no, so check if the current one-shot has finished
		fTempFloat = 1.0
		SWITCH nCurrentCoochieAnim
			CASE 1
				IF IS_CHAR_PLAYING_ANIM charCoochie BD_Panic_01
 					GET_CHAR_ANIM_CURRENT_TIME charCoochie BD_Panic_01 fTempFloat
				ENDIF
 				BREAK

			CASE 2
				IF IS_CHAR_PLAYING_ANIM charCoochie BD_Panic_02
 					GET_CHAR_ANIM_CURRENT_TIME charCoochie BD_Panic_02 fTempFloat
				ENDIF
				BREAK

			CASE 3
				IF IS_CHAR_PLAYING_ANIM charCoochie BD_Panic_03
 					GET_CHAR_ANIM_CURRENT_TIME charCoochie BD_Panic_03 fTempFloat
				ENDIF
				BREAK

			CASE 4
				IF IS_CHAR_PLAYING_ANIM charCoochie BD_Panic_04
 					GET_CHAR_ANIM_CURRENT_TIME charCoochie BD_Panic_04 fTempFloat
				ENDIF
				BREAK
		ENDSWITCH

		IF NOT fTempFloat = 1.0
			RETURN
		ENDIF

		// Current one-shot has finished, so play the looping anim for a while
		nCurrentCoochieAnim = 0
		GENERATE_RANDOM_INT_IN_RANGE 500 2000 nTempInt
		timerCoochieAnim = m_mission_timer + nTempInt
	ENDIF

	// Play the new anim
	SWITCH nCurrentCoochieAnim
		CASE 0
			IF NOT IS_CHAR_PLAYING_ANIM charCoochie BD_Panic_Loop
				TASK_PLAY_ANIM_NON_INTERRUPTABLE charCoochie BD_Panic_Loop BD_FIRE 4.0 true false false false -1
			ENDIF
			BREAK

		CASE 1
			TASK_PLAY_ANIM_NON_INTERRUPTABLE charCoochie BD_Panic_01 BD_FIRE 1.0 false false false false -1
			BREAK

		CASE 2
			TASK_PLAY_ANIM_NON_INTERRUPTABLE charCoochie BD_Panic_02 BD_FIRE 1.0 false false false false -1
			BREAK

		CASE 3
			TASK_PLAY_ANIM_NON_INTERRUPTABLE charCoochie BD_Panic_03 BD_FIRE 1.0 false false false false -1
			BREAK

		CASE 4
			TASK_PLAY_ANIM_NON_INTERRUPTABLE charCoochie BD_Panic_04 BD_FIRE 1.0 false false false false -1
			BREAK
	ENDSWITCH

	// If Coochie is allowed to scream and shout, then prepare or play the next shout or scream
	IF flagAllowCoochieScreams = FALSE
		RETURN
	ENDIF

	// If something other than a Coochie Scream/Shout has been prepared, then do nothing else here
	IF NOT nCurrentConversationID = CRASH1_CONVERSATION_COOCHIE_SHOUTS
	AND NOT nCurrentConversationID = CRASH1_CONVERSATION_COOCHIE_SCREAMS
	AND NOT nCurrentConversationID = CRASH1_CONVERSATION_NONE
		RETURN
	ENDIF

	// If this is a looping anim, then prepare the next scream shout
	IF nCurrentCoochieAnim = 0
		// If the scream and shout speech has been played, then clear it
		IF IS_BIT_SET bitsConversationsPlayed CRASH1_CONVERSATION_COOCHIE_SHOUTS
		OR IS_BIT_SET bitsConversationsPlayed CRASH1_CONVERSATION_COOCHIE_SCREAMS
			// ...clear both bits
			CLEAR_BIT bitsConversationsPlayed CRASH1_CONVERSATION_COOCHIE_SHOUTS
			CLEAR_BIT bitsConversationsPlayed CRASH1_CONVERSATION_COOCHIE_SCREAMS
		ENDIF

		// Prepare the next scream/shout, if something else hasn't been prepared
		IF nCurrentConversationID = CRASH1_CONVERSATION_NONE
			GENERATE_RANDOM_INT_IN_RANGE 0 3 nTempInt
			SWITCH nTempInt
				CASE 0
					// ...shout
					nRequiredConversationID = CRASH1_CONVERSATION_COOCHIE_SHOUTS
					GOSUB Crash1_Conversation_Command_Prepare
					BREAK

				CASE 1
					// ...scream
					nRequiredConversationID = CRASH1_CONVERSATION_COOCHIE_SCREAMS
					GOSUB Crash1_Conversation_Command_Prepare
					BREAK

				DEFAULT
					// ...do nothing
					RETURN
			ENDSWITCH
		ENDIF

		RETURN
	ENDIF

	// If the anim is one of the one-off panic anims, then play any prepared scream/shout if the player is nearby
	IF nCurrentCoochieAnim >= 1
	AND nCurrentCoochieAnim <= 4
		IF nConversationStatus = CRASH1_CONVERSATION_STATUS_PREPARED
		AND IS_CHAR_IN_AREA_3D scplayer 2327.2424 -1175.0516 24.6005 2361.9519 -1149.0908 28.2697 FALSE
			// ...because of an earlier check, this can only be a Coochie scream/shout
			// NOTE: If it is a scream, don't display subtitles
			IF nCurrentConversationID = CRASH1_CONVERSATION_COOCHIE_SCREAMS
				flagDisplaySpeechSubtitle = FALSE
			ENDIF

			GOSUB Crash1_Conversation_Command_Play
		ENDIF
	ENDIF

RETURN


// ****************************************
// ACTIVATE COOCHIE BURNS COUNTER
Crash1_Activate_Coochie_Burns_Counter:

	// Instructions: Save the girl
	PRINT_NOW CRA1_07 5000 1

	// Set up health bar for Coochie
	g_Crash1_counterKM1 = 100
	timerCoochieBurn = m_mission_timer + CRASH1_GIRL_BURNS_TIME_ms
	DISPLAY_ONSCREEN_COUNTER_WITH_STRING g_Crash1_counterKM1 COUNTER_DISPLAY_BAR CRA1_70

	flagCoochieBurnsCounterActive = TRUE

	SET_CHAR_HEALTH charCoochie 100

RETURN



// ****************************************
// UPDATE COOCHIE BURN TIME
Crash1_Update_Coochie_Burn_Time:

	IF IS_CHAR_DEAD charCoochie
		RETURN
	ENDIF

	// Get the Girl's Health
	GET_CHAR_HEALTH charCoochie nTempInt

	IF timerCoochieBurn < m_mission_timer
		// Decrease health by 1
		nTempInt--

		// Restart the counter for the next health point
		// NOTE: If there are any fires around Coochie then increase the speed (because Coochie refuses to burn)
		GET_CHAR_COORDINATES charCoochie xposTemp yposTemp zposTemp
		GET_NUMBER_OF_FIRES_IN_RANGE xposTemp yposTemp zposTemp 1.2 nTempInt2
		IF nTempInt2 > 0
		AND flagInHouse = TRUE
			// ...fires in area
			timerCoochieBurn = m_mission_timer + 100
		ELSE
			// ...no fires in area
			timerCoochieBurn = m_mission_timer + CRASH1_GIRL_BURNS_TIME_ms
		ENDIF
	ENDIF

	// Ensure not negative
	IF nTempInt < 0
		nTempInt = 0
	ENDIF

	// IF counter has reached 0 then coochie dies
	IF nTempInt = 0
		// Turn coochie around to head into the room, set her on fire, and kill her
		CLEAR_CHAR_TASKS_IMMEDIATELY charCoochie
		REMOVE_SCRIPT_FIRE fireCoochie
		START_CHAR_FIRE charCoochie fireCoochie
		SET_CHAR_HEALTH	charCoochie 20
		TASK_DIE charCoochie
		FREEZE_CHAR_POSITION charCoochie FALSE
		nCurrentCoochieAnim = 99
		CLEAR_ONSCREEN_COUNTER g_Crash1_counterKM1

		// The girl is dead
		PRINT_NOW CRA1_50 5000 1
		flagGirlIsToast = TRUE

		// Turn Coochie to face inside the room
		headTemp = 180.0000
		SET_CHAR_HEADING charCoochie headTemp
	ELSE
		// Set Coochie's health
		SET_CHAR_HEALTH charCoochie nTempInt
	ENDIF

	// Update Counter
	g_Crash1_counterKM1 = nTempInt

RETURN




// *************************************************************************************************************
//								        HOUSE GUYS ON WAY IN
// *************************************************************************************************************

// ****************************************
// SETUP HOUSE GUYS ON WAY IN
Crash1_Create_HouseGuys_In:

	// Cowering House Guy on way in
	xposTemp = 2342.7424
	yposTemp = -1184.7313
	zposTemp = 1026.9766
	headTemp = 274.3023

	CREATE_CHAR PEDTYPE_GANG_SMEX LSV3 xposTemp yposTemp zposTemp charHouseCower

	// Indicates that the character was created in an interior
	SET_CHAR_HAS_USED_ENTRY_EXIT charHouseCower 2352.1143 -1170.6102 2.0

	// Starts off inside, so needs interior collisions
	SET_CHAR_AREA_VISIBLE charHouseCower 5
	
	SET_CHAR_HEADING charHouseCower headTemp 
	SET_CHAR_DECISION_MAKER charHouseCower dmEmpty
	SET_CHAR_HEALTH	charHouseCower 20
	SET_CHAR_MAX_HEALTH charHouseCower 100
	SET_CHAR_RELATIONSHIP charHouseCower ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1 
	SET_CHAR_ACCURACY charHouseCower 5
	START_CHAR_FIRE charHouseCower fireHouseCower
	TASK_DEAD charHouseCower
	aiHouseGuyInCower = 0


	// Stairs House Guy on way in
	xposTemp = 2322.5632
	yposTemp = -1185.2175
	zposTemp = 1026.9766
	headTemp = 292.9689

	CREATE_CHAR PEDTYPE_GANG_SMEX LSV1 xposTemp yposTemp zposTemp charHouseStairs

	// Indicates that the character was created in an interior
	SET_CHAR_HAS_USED_ENTRY_EXIT charHouseStairs 2352.1143 -1170.6102 2.0

	// Starts off inside, so needs interior collisions
	SET_CHAR_AREA_VISIBLE charHouseStairs 5

	SET_CHAR_HEADING charHouseStairs headTemp 
	SET_CHAR_DECISION_MAKER charHouseStairs dmEmpty
	SET_CHAR_HEALTH	charHouseStairs 20
	SET_CHAR_MAX_HEALTH charHouseStairs 100
	SET_CHAR_RELATIONSHIP charHouseStairs ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1 
	SET_CHAR_ACCURACY charHouseStairs 5
	START_CHAR_FIRE charHouseStairs fireHouseStairs
	TASK_DEAD charHouseStairs
	aiHouseGuyInStairs = 0

	existsHouseGuysIn = 1

RETURN


/*
// ****************************************
// UPDATE COWERING HOUSE GUY ON WAY IN
Crash1_Update_HouseGuyIn_Cowering:

	IF IS_CHAR_DEAD charHouseCower
		RETURN
	ENDIF


	// Update Cowering guy's AI
	SWITCH aiHouseGuyInCower
		// Cowering
		CASE 0
			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer charHouseCower 5.5 5.5 1.0 FALSE
			OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR charHouseCower scplayer
				// Prepare and Play the inside guy's speech (but don't worry if it doesn't happen)
				nRequiredConversationID = CRASH1_CONVERSATION_INSIDE_GUY
				GOSUB Crash1_Conversation_Command_Prepare
				
				// Issue this speech if prepared, otherwise issue generic speech
				IF nCurrentConversationID = CRASH1_CONVERSATION_INSIDE_GUY
					// ...issue this speech
					GOSUB Crash1_Conversation_Command_Play
				ELSE
					// ...it didn't get prepared, so issue generic speech and set the 'issued bit' manually
					SET_CHAR_SAY_CONTEXT charHouseCower CONTEXT_GLOBAL_ABUSE_GANG_LSV nIgnore
					SET_BIT bitsConversationsPlayed CRASH1_CONVERSATION_INSIDE_GUY
				ENDIF

				// Activate Kill Player
				GET_CHAR_HEALTH charHouseCower nTempInt
				IF nTempInt > 0
					TASK_KILL_CHAR_ON_FOOT charHouseCower scplayer
				ENDIF

				aiHouseGuyInCower = 1
			ENDIF
			BREAK

		// Killing Player
		CASE 1
			// Check if the fire extinguisher cutscene is playing
			IF flagFireExtinguisherCutscenePlaying = 1
				aiHouseGuyInCower = 2
				RETURN
			ENDIF

			// If not killing the player, then re-activate it
			GET_SCRIPT_TASK_STATUS charHouseCower TASK_KILL_CHAR_ON_FOOT m_status

			IF m_status = FINISHED_TASK
				// Re-activate Kill Player
				TASK_KILL_CHAR_ON_FOOT charHouseCower scplayer
			ENDIF
			BREAK

		// Stand Still during fire extinguisher cutscene
		CASE 2
			// Check if the fire extinguisher cutscene is no longer playing
			IF flagFireExtinguisherCutscenePlaying = 0
				aiHouseGuyInCower = 1
				RETURN
			ENDIF

			// If not standing still, then re-activate it
			GET_SCRIPT_TASK_STATUS charHouseCower TASK_STAND_STILL m_status

			IF m_status = FINISHED_TASK
				// Re-activate Kill Player
				CLEAR_CHAR_TASKS_IMMEDIATELY charHouseCower
				TASK_STAND_STILL charHouseCower -2
			ENDIF
			BREAK

	ENDSWITCH

RETURN
*/


/*
// ****************************************
// UPDATE STAIRS HOUSE GUY ON WAY IN
Crash1_Update_HouseGuyIn_Stairs:

	IF IS_CHAR_DEAD charHouseStairs
		RETURN
	ENDIF


	// Update Stairs guy's AI
	SWITCH aiHouseGuyInStairs
		// Waiting
		CASE 0
			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer charHouseStairs 10.0 10.0 1.0 FALSE
			OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR charHouseStairs scplayer
				// Activate Kill Player
				GET_CHAR_HEALTH charHouseStairs nTempInt
				IF nTempInt > 0
					TASK_GO_STRAIGHT_TO_COORD charHouseStairs 2322.8635 -1186.7020 1026.9839 PEDMOVE_RUN -2
				ENDIF

				aiHouseGuyInStairs = 1
			ENDIF
			BREAK

		// Moving
		CASE 1
			// If reached destination, then kill player
			GET_SCRIPT_TASK_STATUS charHouseStairs TASK_GO_STRAIGHT_TO_COORD m_status

			IF m_status = FINISHED_TASK
				// Activate Kill Player
				TASK_KILL_CHAR_ON_FOOT charHouseStairs scplayer

				aiHouseGuyInStairs = 2
			ENDIF
			BREAK

		// Killing
		CASE 2
			// Check if the fire extinguisher cutscene is playing
			IF flagFireExtinguisherCutscenePlaying = 1
				aiHouseGuyInStairs = 3
				RETURN
			ENDIF

			// If not killing the player, then re-activate it
			GET_SCRIPT_TASK_STATUS charHouseStairs TASK_KILL_CHAR_ON_FOOT m_status

			IF m_status = FINISHED_TASK
				// Activate Kill Player
				CLEAR_CHAR_TASKS_IMMEDIATELY charHouseStairs
				TASK_KILL_CHAR_ON_FOOT charHouseStairs scplayer
			ENDIF
			BREAK

		// Stand Still during fire extinguisher cutscene
		CASE 3
			// Check if the fire extinguisher cutscene is no longer playing
			IF flagFireExtinguisherCutscenePlaying = 0
				aiHouseGuyInStairs = 2
				RETURN
			ENDIF

			// If not standing still, then re-activate it
			GET_SCRIPT_TASK_STATUS charHouseStairs TASK_STAND_STILL m_status

			IF m_status = FINISHED_TASK
				// Re-activate Kill Player
				CLEAR_CHAR_TASKS_IMMEDIATELY charHouseStairs
				TASK_STAND_STILL charHouseStairs -2
			ENDIF
			BREAK

	ENDSWITCH

RETURN
*/




// *************************************************************************************************************
//								        INSIDE BURNING BUILDING
// *************************************************************************************************************

// ****************************************
// CUTSCENE: Coochie says get fire extinguisher
Crash1_Cutscene_Get_Fire_Extinguisher:

	SET_EVERYONE_IGNORE_PLAYER player1 TRUE
	SWITCH_WIDESCREEN ON

	// Move the player
	SET_CHAR_COORDINATES scplayer 2344.5354 -1181.7455 1030.9610
	SET_CHAR_HEADING scplayer 359.8736

	// Position camera behind player and raised
	SET_FIXED_CAMERA_POSITION 2344.6243 -1185.3538 1033.7832 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 2344.6946 -1184.3729 1033.6016 JUMP_CUT

	// Re-activate the fire in the girl's bedroom doorway (it must have been put out when the player was moved)
   	statusBigFires = 4
	GOSUB Crash1_Update_Big_Fires

	// Re-activate the fires in the rooms below the position the player is moved to (they've also gone out)
	statusFires = 1
	GOSUB Crash1_Update_Fires_Interior

RETURN


/*
// ****************************************
// Burn House Guys
Crash1_Burn_HouseGuysIn:

	IF NOT IS_CHAR_DEAD charHouseCower
		CLEAR_CHAR_TASKS_IMMEDIATELY charHouseCower
		TASK_DIE charHouseCower
		START_CHAR_FIRE charHouseCower fireHouseCower
	ENDIF	


	IF NOT IS_CHAR_DEAD charHouseStairs
		CLEAR_CHAR_TASKS_IMMEDIATELY charHouseStairs
		TASK_DIE charHouseStairs
		START_CHAR_FIRE charHouseStairs fireHouseStairs
	ENDIF	

RETURN
*/


// ****************************************
// Burn House Guys
Crash1_Remove_All_Outside_Guys:

	// GangOutside
	REPEAT 5 nLoop
		REMOVE_CHAR_ELEGANTLY charGangOutside[nLoop]
	ENDREPEAT

	// GangBackup
	REPEAT 2 nLoop
		REMOVE_CHAR_ELEGANTLY charGangBackup[nLoop]
	ENDREPEAT

RETURN


// ****************************************
// Update Coochie actions
Crash1_Update_Coochie:

	// AI States:
	//	-1: Not Initialised
	//	 0: Following Player
	//	 1: Stopped because of potential walk through fire
	//	 2: Stopped because the player has left her behind
	//	 3: Checking if the player has returned

	SWITCH aiCoochie
		// Not Initialised
		CASE -1
			// Set up the Group
			MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
			SET_GROUP_MEMBER players_group charCoochie
			SET_CHAR_NEVER_LEAVES_GROUP charCoochie TRUE
  	  		LISTEN_TO_PLAYER_GROUP_COMMANDS charCoochie FALSE
			TASK_FOLLOW_FOOTSTEPS charCoochie scplayer
			SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 TRUE

			aiCoochie = 0
			BREAK

		// Following Player
		CASE 0
			// Check for potential to walk through fire
			flagPotentialWalkThroughFire = FALSE
			IF flagInHouse = TRUE
				GOSUB CheckCoochiePotentialWalkThroughFire
				IF flagPotentialWalkThroughFire = TRUE
					// ...potential for Coochie to walk through fire
					// Prepare and Play Put Out Fire text
					flagPrepareAndPlayPutOutFireSpeech = TRUE

					// Change to a 'stand still' task
					CLEAR_CHAR_TASKS_IMMEDIATELY charCoochie
					TASK_STAND_STILL charCoochie -2

					aiCoochie = 1
				ELSE
					// ...make sure Coochie still following
					GET_SCRIPT_TASK_STATUS charCoochie TASK_FOLLOW_FOOTSTEPS m_status

					IF m_status = FINISHED_TASK
						// Re-activate Follow Footsteps
						CLEAR_CHAR_TASKS_IMMEDIATELY charCoochie
						TASK_FOLLOW_FOOTSTEPS charCoochie scplayer
					ENDIF
				ENDIF
			ENDIF
			BREAK

		// Stopped because of fire
		CASE 1
			// Check for potential to walk through fire
			flagPotentialWalkThroughFire = 0
			IF flagInHouse = TRUE
				GOSUB CheckCoochiePotentialWalkThroughFire
				IF flagPotentialWalkThroughFire = 0
					// Allow Coochie to move again
					CLEAR_CHAR_TASKS_IMMEDIATELY charCoochie
					TASK_FOLLOW_FOOTSTEPS charCoochie scplayer
					flagPrepareAndPlayPutOutFireSpeech = FALSE

					aiCoochie = 0
				ENDIF
			ENDIF
			BREAK

		// Stopped because the player has left her behind
		CASE 2
			CLEAR_CHAR_TASKS_IMMEDIATELY charCoochie
			TASK_STAND_STILL charCoochie -2
			aiCoochie = 3
			BREAK

		// Checking if the player has returned
		CASE 3
			// Check if the player is nearby
			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer charCoochie 6.0 6.0 1.0 0
				// Re-activate Coochie
				flagPotentialWalkThroughFire = 0
				IF flagInHouse = TRUE
					GOSUB CheckCoochiePotentialWalkThroughFire
					IF flagPotentialWalkThroughFire = 0
						// ...not potentially about to walk through fire, so make her follow the player again
						TASK_FOLLOW_FOOTSTEPS charCoochie scplayer
						aiCoochie = 0
					ELSE
						// ...about to walk through fire, so make her wait till the fires out
						OPEN_SEQUENCE_TASK nSeqTask
							TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
							TASK_STAND_STILL -1 -2
						CLOSE_SEQUENCE_TASK nSeqTask

						PERFORM_SEQUENCE_TASK charCoochie nSeqTask
						CLEAR_SEQUENCE_TASK nSeqTask

						// Prepare and play 'put out fire' text
						flagPrepareAndPlayPutOutFireSpeech = TRUE
						aiCoochie = 1
					ENDIF
				ENDIF
			ENDIF
			BREAK

	ENDSWITCH

RETURN


// ****************************************
// Check if Coochie has the potential to walk through fire
CheckCoochiePotentialWalkThroughFire:

	// When Coochie is in specific areas, check against script fires to
	//		see if they are still burning

	// AREAS
	//	0: Coochie Bedroom Door
	//	1: Door out of room opposite Coochie bedroom
	//	2: Top of stairs
	//	3: Kitchen Door
	//	4: Big Fires downstairs

	SWITCH statusCoochieExitPath
		// Coochie Bedroom door fire
		CASE 0
			xloTemp = 2345.5000		- 2.0000
			yloTemp = -1177.1218	- 2.0000
			zloTemp = 1030.9688		- 0.5000
			xhiTemp = 2345.5000		+ 2.0000
			yhiTemp = -1177.1218	+ 2.0000
			zhiTemp = 1030.9688		+ 1.5000

			IF IS_CHAR_IN_AREA_3D charCoochie xloTemp yloTemp zloTemp xhiTemp yhiTemp zhiTemp FALSE
				// Is the bedroom door fire still burning?
				IF DOES_SCRIPT_FIRE_EXIST fireInHouseBig[0]
					IF IS_SCRIPT_FIRE_EXTINGUISHED fireInHouseBig[0]
						// ...fire extinguished, so start checking next fire area
						statusCoochieExitPath++
					ELSE
						// ...fire still burning, so set the flag
						flagPotentialWalkThroughFire = 1
					ENDIF
				ELSE
					// ...fire doesn't exist, so start checking next fire area
					statusCoochieExitPath++
				ENDIF
			ENDIF
			BREAK

		// Exit of room opposite Coochie Bedroom
		CASE 1
			xloTemp = 2342.7952		- 2.0000
			yloTemp = -1184.9711	- 2.0000
			zloTemp = 1030.9688		- 0.5000
			xhiTemp = 2342.7952		+ 2.0000
			yhiTemp = -1184.9711	+ 2.0000
			zhiTemp = 1030.9688		+ 1.5000

			IF IS_CHAR_IN_AREA_3D charCoochie xloTemp yloTemp zloTemp xhiTemp yhiTemp zhiTemp FALSE
				// Is the fire still burning?
				IF DOES_SCRIPT_FIRE_EXIST fireInHouseBig[1]
					IF IS_SCRIPT_FIRE_EXTINGUISHED fireInHouseBig[1]
						// ...fire extinguished, so start checking next fire area
						statusCoochieExitPath++
					ELSE
						// ...fire still burning, so set the flag
						flagPotentialWalkThroughFire = 1
					ENDIF
				ELSE
					// ...fire doesn't exist, so start checking next fire area
					statusCoochieExitPath++
				ENDIF
			ENDIF
			BREAK

		// Fire at top of stairs
		CASE 2
			xloTemp = 2334.4453		- 2.0000
			yloTemp = -1181.4629	- 2.0000
			zloTemp = 1030.9688		- 0.5000
			xhiTemp = 2334.4453		+ 2.0000
			yhiTemp = -1181.4629	+ 2.0000
			zhiTemp = 1030.9688		+ 1.5000

			IF IS_CHAR_IN_AREA_3D charCoochie xloTemp yloTemp zloTemp xhiTemp yhiTemp zhiTemp FALSE
				// Is the fire still burning?
				IF DOES_SCRIPT_FIRE_EXIST fireInHouseBig[2]
					IF IS_SCRIPT_FIRE_EXTINGUISHED fireInHouseBig[2]
						// ...fire extinguished, so start checking next fire area
						statusCoochieExitPath++
					ELSE
						// ...fire still burning, so set the flag
						flagPotentialWalkThroughFire = 1
					ENDIF
				ELSE
					// ...fire doesn't exist, so start checking next fire area
					statusCoochieExitPath++
				ENDIF
			ENDIF
			BREAK

		// Fire in kitchen door
		CASE 3
			xloTemp = 2325.8542		- 2.5000
			yloTemp = -1182.6364	- 2.5000
			zloTemp = 1026.9839		- 0.5000
			xhiTemp = 2325.8542		+ 2.5000
			yhiTemp = -1182.6364	+ 2.5000
			zhiTemp = 1026.9839		+ 1.5000

			IF IS_CHAR_IN_AREA_3D charCoochie xloTemp yloTemp zloTemp xhiTemp yhiTemp zhiTemp FALSE
				// Is the fire still burning?
				IF DOES_SCRIPT_FIRE_EXIST fireInHouseBig[3]
					IF IS_SCRIPT_FIRE_EXTINGUISHED fireInHouseBig[3]
						// ...fire extinguished, so start checking next fire area
						statusCoochieExitPath++
					ELSE
						// ...fire still burning, so set the flag
						flagPotentialWalkThroughFire = 1
					ENDIF
				ELSE
					// ...fire doesn't exist, so start checking next fire area
					statusCoochieExitPath++
				ENDIF
			ENDIF
			BREAK

		// Small fires in doorway of downstairs bedroom
		CASE 4
			xloTemp = 2342.9607		- 2.0000
			yloTemp = -1176.9055	- 2.0000
			zloTemp = 1026.9766		- 0.5000
			xhiTemp = 2342.9607		+ 2.0000
			yhiTemp = -1176.9055	+ 2.0000
			zhiTemp = 1026.9766		+ 1.5000

			IF IS_CHAR_IN_AREA_3D charCoochie xloTemp yloTemp zloTemp xhiTemp yhiTemp zhiTemp FALSE
				// Are the small fires in this area still burning?
				xloTemp = 2344.9900
				yloTemp = -1178.3976
				zloTemp = 1026.9766
				REPEAT 21 nLoop
					// Get this script fire's coordinates
					IF DOES_SCRIPT_FIRE_EXIST fireInHouse[nLoop]
						IF NOT IS_SCRIPT_FIRE_EXTINGUISHED fireInHouse[nLoop]
							GET_SCRIPT_FIRE_COORDS fireInHouse[nLoop] xhiTemp yhiTemp zhiTemp					

							// Get distance between script fire's coords and the test coords
							GET_DISTANCE_BETWEEN_COORDS_3D xloTemp yloTemp zloTemp xhiTemp yhiTemp zhiTemp fTempFloat
							IF fTempFloat <= 3.0
								// This fire is still burning, so set the flag and return
								flagPotentialWalkThroughFire = 1
								RETURN
							ENDIF
						ENDIF
					ENDIF
				ENDREPEAT

				// All scripted fires extinguished
				// Do a check for non-scripted fires also
				GET_NUMBER_OF_FIRES_IN_RANGE xloTemp yloTemp zloTemp 3.0f nTempInt
				IF NOT nTempInt = 0
					flagPotentialWalkThroughFire = 1
				ENDIF

				// All fires extinguished
				statusCoochieExitPath++
			ENDIF
			BREAK
	ENDSWITCH

RETURN



// ****************************************
// Update Animated Wall 0
Crash1_Update_AnimatedWall0:

	// STATUS
	//	0: Normal wall is in place
	//	1: Start the animated wall
	//	2: Wall animation in progress
	//	3: Place broken wall
	//	4: Wall collape finished

	nTempInt = 0
	SWITCH statusWalls[nTempInt]
		// Normal wall in place
		CASE 0
			// Nothing to do
			BREAK

		// Create the object
		CASE 1
			// Delete the normal wall
			DELETE_OBJECT objectWall[nTempInt]

			MARK_MODEL_AS_NO_LONGER_NEEDED BREAK_WALL_1A
			
			// Create the animated wall in its place
			xposTemp = 2338.0
			yposTemp = -1180.0
			zposTemp = 1030.5
			headTemp = 90.0

			CREATE_OBJECT_NO_OFFSET	BD_FIRE1_O xposTemp yposTemp zposTemp objectWall[nTempInt]
			SET_OBJECT_HEADING objectWall[nTempInt] headTemp

			statusWalls[nTempInt] = 2
			BREAK

		// Start the animation
		CASE 2
			IF DOES_OBJECT_EXIST objectWall[nTempInt]
				IF PLAY_OBJECT_ANIM objectWall[nTempInt] BD_FIRE1 BD_FIRE 1000.0 FALSE TRUE
					statusWalls[nTempInt] = 3
				ELSE
					statusWalls[nTempInt] = 4
				ENDIF
			ENDIF
			BREAK

		// Animation in progress
		CASE 3
			GET_OBJECT_ANIM_CURRENT_TIME objectWall[nTempInt] BD_FIRE1 fTempFloat
			IF fTempFloat = 1.0
				statusWalls[nTempInt] = 4
			ENDIF
			BREAK

		// Place broken wall
		CASE 4
			// Delete the animated wall
			DELETE_OBJECT objectWall[nTempInt]

			MARK_MODEL_AS_NO_LONGER_NEEDED BD_FIRE1_O
			
			// Create the broken wall in its place
			xposTemp = 2338.126
			yposTemp = -1181.917
			zposTemp = 1033.188

			CREATE_OBJECT_NO_OFFSET	BREAK_WALL_1B xposTemp yposTemp zposTemp objectWall[nTempInt]
			statusWalls[nTempInt] = 5
			BREAK

		// Wall collapse finished
		CASE 5
			// Nothing to do
			BREAK
	ENDSWITCH

RETURN


// ****************************************
// Update Animated Wall 1
Crash1_Update_AnimatedWall1:

	// STATUS
	//	0: Normal wall is in place
	//	1: Start the animated wall
	//	2: Wall animation in progress
	//	3: Place broken wall
	//	4: Wall collape finished

	nTempInt = 1
	SWITCH statusWalls[nTempInt]
		// Normal wall in place
		CASE 0
			// Nothing to do
			BREAK

		// Start the animation
		CASE 1
			BREAK

		// Animation in progress
		CASE 2
			BREAK

		// Place broken wall
		CASE 3
			// Delete the animated wall
			DELETE_OBJECT objectWall[nTempInt]

			MARK_MODEL_AS_NO_LONGER_NEEDED BREAK_WALL_2A
			
			// Create the broken wall in its place
			xposTemp = 2330.402
			yposTemp = -1179.15
			zposTemp = 1030.55

			CREATE_OBJECT_NO_OFFSET	BREAK_WALL_2B xposTemp yposTemp zposTemp objectWall[nTempInt]
			statusWalls[nTempInt] = 4
			BREAK

		// Wall collapse finished
		CASE 4
			// Nothing to do
			BREAK
	ENDSWITCH

RETURN


// ****************************************
// Update Animated Wall 2
Crash1_Update_AnimatedWall2:

	// STATUS
	//	0: Normal wall is in place
	//	1: Start the animated wall
	//	2: Wall animation in progress
	//	3: Place broken wall
	//	4: Wall collape finished

	nTempInt = 2
	SWITCH statusWalls[nTempInt]
		// Normal wall in place
		CASE 0
			// Nothing to do
			BREAK

		// Start the animation
		CASE 1
			BREAK

		// Animation in progress
		CASE 2
			BREAK

		// Place broken wall
		CASE 3
			// Delete the animated wall
			DELETE_OBJECT objectWall[nTempInt]

			MARK_MODEL_AS_NO_LONGER_NEEDED BREAK_WALL_3A
						
			// Create the broken wall in its place
			xposTemp = 2340.293
			yposTemp = -1182.294
			zposTemp = 1026.957

			CREATE_OBJECT_NO_OFFSET	BREAK_WALL_3B xposTemp yposTemp zposTemp objectWall[nTempInt]
			statusWalls[nTempInt] = 4
			BREAK

		// Wall collapse finished
		CASE 4
			// Nothing to do
			BREAK
	ENDSWITCH

RETURN




// *************************************************************************************************************
//								        OUTSIDE BURNING BUILDING
// *************************************************************************************************************

// ****************************************
// CREATE FIRE TRUCK
Crash1_Create_FireEngine:

	// The Fire Engine
	// NOTE: The Coordinates are where the vehicle has to drive to
	CREATE_EMERGENCY_SERVICES_CAR FIRETRUK 2340.6372 -1152.7217 25.9686

RETURN




// *************************************************************************************************************
//								              CASH PICKUPS
// *************************************************************************************************************

// ****************************************
// Create Cash Pickups In Rooms
Crash1_Create_Room_Cash_Pickups:

	REPEAT CRASH1_MAX_CASH_PICKUPS nLoop
		IF flagCashCreated[nLoop] = FALSE
			nCashPickupID = nLoop
			GOSUB Crash1_Create_Cash_Pickups
		ENDIF
	ENDREPEAT

RETURN


// ****************************************
// Create Cash Pickups
Crash1_Create_Cash_Pickups:

	IF flagCashCreated[nCashPickupID] = TRUE
		RETURN
	ENDIF

	SWITCH nCashPickupID
		CASE CRASH1_CASH_PICKUP_DOWNSTAIRS_BEDROOM
			CREATE_MONEY_PICKUP 2338.5505 -1186.9388 1027.9766 500 TRUE pickupCash[nCashPickupID]
			flagCashCreated[nCashPickupID] = TRUE
			BREAK
		CASE CRASH1_CASH_PICKUP_COOCHIE_BEDROOM
			CREATE_MONEY_PICKUP 2348.1841 -1173.1639 1031.9766 500 TRUE pickupCash[nCashPickupID]
			flagCashCreated[nCashPickupID] = TRUE
			BREAK
		DEFAULT
			WRITE_DEBUG_WITH_INT Illegal_Cash_Pickup_ID nCashPickupID
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
Crash1_Update_Speech:

	SWITCH nConversationStatus
		CASE CRASH1_CONVERSATION_STATUS_NONE
		CASE CRASH1_CONVERSATION_STATUS_PREPARED
		CASE CRASH1_CONVERSATION_STATUS_INTERRUPTED
		CASE CRASH1_CONVERSATION_STATUS_FINISHED
			// ...nothing to do
			BREAK

		CASE CRASH1_CONVERSATION_STATUS_PLAYING
			// ...playing the conversation
			GOSUB Crash1_Playing_Conversation
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_CONVERSATION_STATUS_ID
			RETURN
	ENDSWITCH

RETURN


// ****************************************
// Initial Speech Slot Preloads
Crash1_Initial_Speech_Slot_Preloads:

	// Preload the first two slots if possible
	IF nNextPreloadConversationLine = nCurrentMaxConversationLines
		RETURN
	ENDIF

	// ...slot 1
	nTempInt = CRASH1_FIRST_SPEECH_SLOT
   	nAudioSlotStatus[nTempInt] = CRASH1_AUDIO_SLOT_LOADING
   	LOAD_MISSION_AUDIO nTempInt nConversationSequenceSpeechID[nNextPreloadConversationLine]
   	nNextPreloadConversationLine++

	// Are there more lines to be preloaded
	IF nNextPreloadConversationLine = nCurrentMaxConversationLines
		RETURN
	ENDIF

	// ...slot 2
	nTempInt++
	IF nTempInt > CRASH1_LAST_SPEECH_SLOT
		RETURN
	ENDIF

   	nAudioSlotStatus[nTempInt] = CRASH1_AUDIO_SLOT_LOADING
   	LOAD_MISSION_AUDIO nTempInt nConversationSequenceSpeechID[nNextPreloadConversationLine]
   	nNextPreloadConversationLine++

RETURN


// ****************************************
// Playing Conversation
Crash1_Playing_Conversation:

	// Check if the conversation should be interrupted
	GOSUB Crash1_Check_If_Interrupt_Conversation
	IF nConversationStatus = CRASH1_CONVERSATION_STATUS_INTERRUPTED
		GOSUB Crash1_Interrupt_Conversation
		RETURN
	ENDIF

	// Check the status of the current slot
	SWITCH nAudioSlotStatus[nSpeechCurrentSlot]
		CASE CRASH1_AUDIO_SLOT_FREE
			RETURN
			BREAK

		CASE CRASH1_AUDIO_SLOT_LOADING
			// ...check if the speech has loaded
			IF NOT HAS_MISSION_AUDIO_LOADED nSpeechCurrentSlot
				// ...no
				RETURN
			ENDIF

			// Speech has loaded
			nAudioSlotStatus[nSpeechCurrentSlot] = CRASH1_AUDIO_SLOT_LOADED
			BREAK

		CASE CRASH1_AUDIO_SLOT_LOADED
			// ...play the speech and display the subtitles
			PLAY_MISSION_AUDIO nSpeechCurrentSlot

			IF flagDisplaySpeechSubtitle = TRUE
				PRINT_NOW $tlConversationSequenceSubtitle[nCurrentConversationLine] 10000 1
			ENDIF

			nAudioSlotStatus[nSpeechCurrentSlot] = CRASH1_AUDIO_SLOT_PLAYING
			RETURN
			BREAK

		CASE CRASH1_AUDIO_SLOT_PLAYING
			// ...check if the speech has finished playing
			IF NOT HAS_MISSION_AUDIO_FINISHED nSpeechCurrentSlot
				// ...still playing
				RETURN
			ENDIF

			// Speech has finished playing, so move on to the next piece
			GOSUB Crash1_Current_Speech_Finished			
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_AUDIO_SLOT_STATUS
			RETURN
	ENDSWITCH

RETURN


// ****************************************
// Current Conversation has been interrupted
Crash1_Interrupt_Conversation:

	// Clear mission audio
	nTempInt = CRASH1_FIRST_SPEECH_SLOT
	WHILE nTempInt <= CRASH1_LAST_SPEECH_SLOT
		CLEAR_MISSION_AUDIO nTempInt
		nTempInt++
	ENDWHILE

	GOSUB Crash1_Finish_Conversation

RETURN


// ****************************************
// Current Speech has finished
Crash1_Current_Speech_Finished:

	// Housekeeping
	nAudioSlotStatus[nSpeechCurrentSlot] = CRASH1_AUDIO_SLOT_FREE
	nCurrentConversationLine++
	IF nCurrentConversationLine = nCurrentMaxConversationLines
		// ...conversation over
		GOSUB Crash1_Finish_Conversation
		RETURN
	ENDIF

	// Preload the next piece of audio that will be played in this slot
	IF nNextPreloadConversationLine < nCurrentMaxConversationLines
		// ...there is another piece of speech required in this slot
		nAudioSlotStatus[nSpeechCurrentSlot] = CRASH1_AUDIO_SLOT_LOADING
		LOAD_MISSION_AUDIO nSpeechCurrentSlot nConversationSequenceSpeechID[nNextPreloadConversationLine]
		nNextPreloadConversationLine++
	ENDIF

	// Switch to the next speech slot
	nSpeechCurrentSlot++
	IF nSpeechCurrentSlot > CRASH1_LAST_SPEECH_SLOT
		nSpeechCurrentSlot = CRASH1_FIRST_SPEECH_SLOT
	ENDIF

RETURN


// ****************************************
// Current Conversation has finished
Crash1_Finish_Conversation:

	// Mark this conversation as 'played'
	SET_BIT bitsConversationsPlayed nCurrentConversationID

	nCurrentConversationID			= CRASH1_CONVERSATION_NONE
	nConversationStatus				= CRASH1_CONVERSATION_STATUS_NONE
	nRequiredConversationID			= CRASH1_CONVERSATION_NONE
	nSpeechCurrentSlot				= CRASH1_FIRST_SPEECH_SLOT
	nCurrentConversationLine		= 0
	nCurrentMaxConversationLines	= 0
	nNextPreloadConversationLine	= 0

	nTempInt = CRASH1_FIRST_SPEECH_SLOT
	WHILE nTempInt < CRASH1_LAST_SPEECH_SLOT
		nAudioSlotStatus[nTempInt] = CRASH1_AUDIO_SLOT_FREE
		nTempInt++
	ENDWHILE

	// Clear subtitles
	CLEAR_SMALL_PRINTS

	// Introduce a delay before the next conversation can be prepared
	timerDelayBeforeNextConversation = m_mission_timer + CRASH1_DELAY_BEFORE_NEXT_CONVERSATION_msec

RETURN


// ------------------------------
//  EXTERNAL SPEECH CONTROLS
// ------------------------------

// ****************************************
// Conversation Command Prepare
Crash1_Conversation_Command_Prepare:

	// Prepare a conversation
	IF NOT nConversationStatus = CRASH1_CONVERSATION_STATUS_NONE
		GOSUB Crash1_Conversation_Command_Cancel
	ENDIF

	// Wait for the timer to elapse before preparing the next conversation
	IF timerDelayBeforeNextConversation > m_mission_timer
		RETURN
	ENDIF

	timerDelayBeforeNextConversation	= 0
	nCurrentConversationID				= nRequiredConversationID
	nRequiredConversationID				= CRASH1_CONVERSATION_NONE
	nSpeechCurrentSlot					= CRASH1_FIRST_SPEECH_SLOT
	nCurrentConversationLine			= 0
	nCurrentMaxConversationLines		= 0
	nNextPreloadConversationLine		= 0
	flagDisplaySpeechSubtitle			= TRUE

	nTempInt = CRASH1_FIRST_SPEECH_SLOT
	WHILE nTempInt < CRASH1_LAST_SPEECH_SLOT
		nAudioSlotStatus[nTempInt] = CRASH1_AUDIO_SLOT_FREE
		nTempInt++
	ENDWHILE

	// Prepare the next conversation
	GOSUB Crash1_Prepare_Next_Conversation
	
RETURN


// ****************************************
// Conversation Command Play
Crash1_Conversation_Command_Play:

	// Ensure the conversation has been prepared
	IF NOT nConversationStatus = CRASH1_CONVERSATION_STATUS_PREPARED
		RETURN
	ENDIF

	nConversationStatus = CRASH1_CONVERSATION_STATUS_PLAYING

RETURN


// ****************************************
// Conversation Command Cancel
Crash1_Conversation_Command_Cancel:

	// Ensure the conversation has been prepared
	IF nConversationStatus = CRASH1_CONVERSATION_STATUS_NONE
		RETURN
	ENDIF

	GOSUB Crash1_Interrupt_Conversation

RETURN


// ------------------------------
//  MISSION SPECIFIC SPEECH SETUP
// ------------------------------

// ****************************************
// Prepare Next Conversation
Crash1_Prepare_Next_Conversation:

	SWITCH nCurrentConversationID
		CASE CRASH1_CONVERSATION_COOCHIE_SHOUTS
			GOSUB Crash1_Prepare_Conversation_Coochie_Shouts
			BREAK

		CASE CRASH1_CONVERSATION_COOCHIE_SCREAMS
			GOSUB Crash1_Prepare_Conversation_Coochie_Screams
			BREAK

		CASE CRASH1_CONVERSATION_CARL_HELP
			GOSUB Crash1_Prepare_Conversation_Carl_Help
			BREAK

//		CASE CRASH1_CONVERSATION_INSIDE_GUY
//			GOSUB Crash1_Prepare_Conversation_Inside_Guy
//			BREAK

		CASE CRASH1_CONVERSATION_NO_WAY_OUT
			GOSUB Crash1_Prepare_Conversation_No_Way_Out
			BREAK

		CASE CRASH1_CONVERSATION_FIRE_EX
			GOSUB Crash1_Prepare_Conversation_Fire_Ex
			BREAK

		CASE CRASH1_CONVERSATION_I_BE_BACK
			GOSUB Crash1_Prepare_Conversation_I_Be_Back
			BREAK

		CASE CRASH1_CONVERSATION_OUTSIDE_ROOM
			GOSUB Crash1_Prepare_Conversation_Outside_Room
			BREAK

		CASE CRASH1_CONVERSATION_REACH_COOCHIE
 			GOSUB Crash1_Prepare_Conversation_Reach_Coochie
			BREAK

		CASE CRASH1_CONVERSATION_BUILDING_COLLAPSE
 			GOSUB Crash1_Prepare_Conversation_Building_Collapse
			BREAK

		CASE CRASH1_CONVERSATION_PUT_OUT_FIRE
			GOSUB Crash1_Prepare_Conversation_Put_Out_Fire
			BREAK

		CASE CRASH1_CONVERSATION_COOCHIE_HERO
			GOSUB Crash1_Prepare_Conversation_Coochie_Hero
			BREAK

		CASE CRASH1_CONVERSATION_DRIVE_COOCHIE_HOME
			GOSUB Crash1_Prepare_Conversation_Drive_Coochie_Home
			BREAK

		CASE CRASH1_CONVERSATION_ON_RIDE_HOME
			GOSUB Crash1_Prepare_Conversation_On_Ride_Home
			BREAK

		CASE CRASH1_CONVERSATION_COOCHIE_HOUSE
			GOSUB Crash1_Prepare_Conversation_Coochie_House
			BREAK

		CASE CRASH1_CONVERSATION_COOCHIE_NAME
			GOSUB Crash1_Prepare_Conversation_Coochie_Name
			BREAK

		CASE CRASH1_CONVERSATION_GO_OUT
			GOSUB Crash1_Prepare_Conversation_Go_Out
			BREAK

		CASE CRASH1_CONVERSATION_CYA
			GOSUB Crash1_Prepare_Conversation_Cya
			BREAK

		DEFAULT
			WRITE_DEBUG UNKNOWN_CONVERSATION
			RETURN
	ENDSWITCH

	// Preload the first two pieces of speech
	GOSUB Crash1_Initial_Speech_Slot_Preloads
	nConversationStatus = CRASH1_CONVERSATION_STATUS_PREPARED

RETURN


// ****************************************
// Check if the current conversation should be interrupted
Crash1_Check_If_Interrupt_Conversation:

	// Is Carl Dead?
	SWITCH nCurrentConversationID
		CASE CRASH1_CONVERSATION_CARL_HELP
		CASE CRASH1_CONVERSATION_NO_WAY_OUT
		CASE CRASH1_CONVERSATION_FIRE_EX
		CASE CRASH1_CONVERSATION_I_BE_BACK
		CASE CRASH1_CONVERSATION_OUTSIDE_ROOM
		CASE CRASH1_CONVERSATION_REACH_COOCHIE
		CASE CRASH1_CONVERSATION_BUILDING_COLLAPSE
		CASE CRASH1_CONVERSATION_PUT_OUT_FIRE
		CASE CRASH1_CONVERSATION_COOCHIE_HERO
		CASE CRASH1_CONVERSATION_DRIVE_COOCHIE_HOME
		CASE CRASH1_CONVERSATION_ON_RIDE_HOME
		CASE CRASH1_CONVERSATION_COOCHIE_HOUSE
		CASE CRASH1_CONVERSATION_COOCHIE_NAME
		CASE CRASH1_CONVERSATION_GO_OUT
		CASE CRASH1_CONVERSATION_CYA
			IF IS_CHAR_DEAD scplayer
				nConversationStatus = CRASH1_CONVERSATION_STATUS_INTERRUPTED
				RETURN
			ENDIF
			BREAK
	ENDSWITCH

	// Is Coochie dead?
	SWITCH nCurrentConversationID
		CASE CRASH1_CONVERSATION_COOCHIE_SHOUTS
		CASE CRASH1_CONVERSATION_COOCHIE_SCREAMS
		CASE CRASH1_CONVERSATION_NO_WAY_OUT
		CASE CRASH1_CONVERSATION_FIRE_EX
		CASE CRASH1_CONVERSATION_I_BE_BACK
		CASE CRASH1_CONVERSATION_OUTSIDE_ROOM
		CASE CRASH1_CONVERSATION_REACH_COOCHIE
		CASE CRASH1_CONVERSATION_BUILDING_COLLAPSE
		CASE CRASH1_CONVERSATION_PUT_OUT_FIRE
		CASE CRASH1_CONVERSATION_COOCHIE_HERO
		CASE CRASH1_CONVERSATION_DRIVE_COOCHIE_HOME
		CASE CRASH1_CONVERSATION_ON_RIDE_HOME
		CASE CRASH1_CONVERSATION_COOCHIE_HOUSE
		CASE CRASH1_CONVERSATION_COOCHIE_NAME
		CASE CRASH1_CONVERSATION_GO_OUT
		CASE CRASH1_CONVERSATION_CYA
			IF IS_CHAR_DEAD charCoochie
				nConversationStatus = CRASH1_CONVERSATION_STATUS_INTERRUPTED
				RETURN
			ENDIF
			BREAK
	ENDSWITCH

	// Is Cowering Guy dead?
//	SWITCH nCurrentConversationID
//		CASE CRASH1_CONVERSATION_INSIDE_GUY
//			IF IS_CHAR_DEAD charHouseCower
//				nConversationStatus = CRASH1_CONVERSATION_STATUS_INTERRUPTED
//				RETURN
//			ENDIF
//			BREAK
//	ENDSWITCH

	// Is the Player and Coochie still in the same car?
	SWITCH nCurrentConversationID
		CASE CRASH1_CONVERSATION_ON_RIDE_HOME
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				nConversationStatus = CRASH1_CONVERSATION_STATUS_INTERRUPTED
				RETURN
			ENDIF

			IF NOT IS_CHAR_IN_ANY_CAR charCoochie
				nConversationStatus = CRASH1_CONVERSATION_STATUS_INTERRUPTED
				RETURN
			ENDIF
			BREAK
	ENDSWITCH

RETURN


// ****************************************
// Prepare Conversation: Coochie Shouts
Crash1_Prepare_Conversation_Coochie_Shouts:

	// A random one of 9 Coochie shouts for help

	GENERATE_RANDOM_INT_IN_RANGE 0 9 nTempInt
	SWITCH nTempInt
		CASE 0
			nConversationSequenceSpeechID[0] = SOUND_CRA1_AA
			$tlConversationSequenceSubtitle[0] = CRA1_AA
			BREAK

		CASE 1
			nConversationSequenceSpeechID[0] = SOUND_CRA1_AB
			$tlConversationSequenceSubtitle[0] = CRA1_AB
			BREAK

		CASE 2
			nConversationSequenceSpeechID[0] = SOUND_CRA1_AC
			$tlConversationSequenceSubtitle[0] = CRA1_AC
			BREAK

		CASE 3
			nConversationSequenceSpeechID[0] = SOUND_CRA1_AD
			$tlConversationSequenceSubtitle[0] = CRA1_AD
			BREAK

		CASE 4
			nConversationSequenceSpeechID[0] = SOUND_CRA1_AE
			$tlConversationSequenceSubtitle[0] = CRA1_AE
			BREAK

		CASE 5
			nConversationSequenceSpeechID[0] = SOUND_CRA1_AF
			$tlConversationSequenceSubtitle[0] = CRA1_AF
			BREAK

		CASE 6
			nConversationSequenceSpeechID[0] = SOUND_CRA1_AG
			$tlConversationSequenceSubtitle[0] = CRA1_AG
			BREAK

		CASE 7
			nConversationSequenceSpeechID[0] = SOUND_CRA1_AH
			$tlConversationSequenceSubtitle[0] = CRA1_AH
			BREAK

		CASE 8
			nConversationSequenceSpeechID[0] = SOUND_CRA1_AJ
			$tlConversationSequenceSubtitle[0] = CRA1_AJ
			BREAK

	ENDSWITCH

	nCurrentMaxConversationLines = 1
RETURN


// ****************************************
// Prepare Conversation: Coochie Screams
Crash1_Prepare_Conversation_Coochie_Screams:

	// A random one of 5 Coochie screams

	GENERATE_RANDOM_INT_IN_RANGE 0 5 nTempInt
	SWITCH nTempInt
		CASE 0
			nConversationSequenceSpeechID[0] = SOUND_CRA1_BA
			$tlConversationSequenceSubtitle[0] = CRA1_BA
			BREAK

		CASE 1
			nConversationSequenceSpeechID[0] = SOUND_CRA1_BB
			$tlConversationSequenceSubtitle[0] = CRA1_BB
			BREAK

		CASE 2
			nConversationSequenceSpeechID[0] = SOUND_CRA1_BC
			$tlConversationSequenceSubtitle[0] = CRA1_BC
			BREAK

		CASE 3
			nConversationSequenceSpeechID[0] = SOUND_CRA1_BD
			$tlConversationSequenceSubtitle[0] = CRA1_BD
			BREAK

		CASE 4
			nConversationSequenceSpeechID[0] = SOUND_CRA1_BE
			$tlConversationSequenceSubtitle[0] = CRA1_BE
			BREAK
	ENDSWITCH

	nCurrentMaxConversationLines = 1
RETURN


// ****************************************
// Prepare Conversation: Carl Help
Crash1_Prepare_Conversation_Carl_Help:

	// A random one of 2 Coochie shouts of encouragement

	GENERATE_RANDOM_INT_IN_RANGE 0 2 nTempInt
	SWITCH nTempInt
		CASE 0
			nConversationSequenceSpeechID[0] = SOUND_CRA1_CA
			$tlConversationSequenceSubtitle[0] = CRA1_CA
			BREAK

		CASE 1
			nConversationSequenceSpeechID[0] = SOUND_CRA1_CB
			$tlConversationSequenceSubtitle[0] = CRA1_CB
			BREAK

	ENDSWITCH

	nCurrentMaxConversationLines = 1
RETURN


/*
// ****************************************
// Prepare Conversation: Inside Guy
Crash1_Prepare_Conversation_Inside_Guy:

	// A random one of 4 LSV gang comments
	// NOTE: Each of these is actually by a differnet voice - but there are no variations, so I'll randomly
	//			choose a piece of speech and see how dodgy it sounds

	GENERATE_RANDOM_INT_IN_RANGE 0 4 nTempInt
	SWITCH nTempInt
		CASE 0
			nConversationSequenceSpeechID[0] = SOUND_CRA1_DA
			$tlConversationSequenceSubtitle[0] = CRA1_DA
			BREAK

		CASE 1
			nConversationSequenceSpeechID[0] = SOUND_CRA1_DB
			$tlConversationSequenceSubtitle[0] = CRA1_DB
			BREAK

		CASE 2
			nConversationSequenceSpeechID[0] = SOUND_CRA1_DC
			$tlConversationSequenceSubtitle[0] = CRA1_DC
			BREAK

		CASE 3
			nConversationSequenceSpeechID[0] = SOUND_CRA1_DD
			$tlConversationSequenceSubtitle[0] = CRA1_DD
			BREAK

	ENDSWITCH

	nCurrentMaxConversationLines = 1
RETURN
*/


// ****************************************
// Prepare Conversation: No Way Out
Crash1_Prepare_Conversation_No_Way_Out:

	// Conversation between Coochie and Carl 

	nTempInt = 0
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_EA
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_EA

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_EB
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_EB

	nTempInt++
	IF nTempInt > CRASH1_MAX_CONVERSATION_LINES
		WRITE_DEBUG TOO_MANY_CONVERSATION_LINES
	ENDIF

	nCurrentMaxConversationLines = nTempInt

RETURN


// ****************************************
// Prepare Conversation: Fire Ex
Crash1_Prepare_Conversation_Fire_Ex:

	// Conversation between Coochie and Carl 

	nConversationSequenceSpeechID[0] = SOUND_CRA1_EC
	$tlConversationSequenceSubtitle[0] = CRA1_EC

	nCurrentMaxConversationLines = 1

RETURN


// ****************************************
// Prepare Conversation: I'll Be Back
Crash1_Prepare_Conversation_I_Be_Back:

	// Conversation between Coochie and Carl 

	nConversationSequenceSpeechID[0] = SOUND_CRA1_ED
	$tlConversationSequenceSubtitle[0] = CRA1_ED

	nCurrentMaxConversationLines = 1

RETURN


// ****************************************
// Prepare Conversation: Outside Room
Crash1_Prepare_Conversation_Outside_Room:

	// Conversation between Coochie and Carl 

	nTempInt = 0
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_FA
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_FA

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_FB
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_FB

	nTempInt++
	IF nTempInt > CRASH1_MAX_CONVERSATION_LINES
		WRITE_DEBUG TOO_MANY_CONVERSATION_LINES
	ENDIF

	nCurrentMaxConversationLines = nTempInt

RETURN


// ****************************************
// Prepare Conversation: Reach Coochie
Crash1_Prepare_Conversation_Reach_Coochie:

	// Conversation between Coochie and Carl 

	nConversationSequenceSpeechID[0] = SOUND_CRA1_GA
	$tlConversationSequenceSubtitle[0] = CRA1_GA

	nCurrentMaxConversationLines = 1

RETURN


// ****************************************
// Prepare Conversation: Building Collapse
Crash1_Prepare_Conversation_Building_Collapse:

	// Conversation between Coochie and Carl 

	nTempInt = 0
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_GB
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_GB

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_GC
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_GC

	nTempInt++
	IF nTempInt > CRASH1_MAX_CONVERSATION_LINES
		WRITE_DEBUG TOO_MANY_CONVERSATION_LINES
	ENDIF

	nCurrentMaxConversationLines = nTempInt

RETURN


// ****************************************
// Prepare Conversation: Put Out Fire
Crash1_Prepare_Conversation_Put_Out_Fire:

	// A random one of 11 Coochie or Carl fire fighting comments

	GENERATE_RANDOM_INT_IN_RANGE 0 11 nTempInt
	SWITCH nTempInt
		CASE 0
			nConversationSequenceSpeechID[0] = SOUND_CRA1_HA
			$tlConversationSequenceSubtitle[0] = CRA1_HA
			BREAK

		CASE 1
			nConversationSequenceSpeechID[0] = SOUND_CRA1_HB
			$tlConversationSequenceSubtitle[0] = CRA1_HB
			BREAK

		CASE 2
			nConversationSequenceSpeechID[0] = SOUND_CRA1_HC
			$tlConversationSequenceSubtitle[0] = CRA1_HC
			BREAK

		CASE 3
			nConversationSequenceSpeechID[0] = SOUND_CRA1_HD
			$tlConversationSequenceSubtitle[0] = CRA1_HD
			BREAK

		CASE 4
			nConversationSequenceSpeechID[0] = SOUND_CRA1_HE
			$tlConversationSequenceSubtitle[0] = CRA1_HE
			BREAK

		CASE 5
			nConversationSequenceSpeechID[0] = SOUND_CRA1_HF
			$tlConversationSequenceSubtitle[0] = CRA1_HF
			BREAK

		CASE 6
			nConversationSequenceSpeechID[0] = SOUND_CRA1_HG
			$tlConversationSequenceSubtitle[0] = CRA1_HG
			BREAK

		CASE 7
			nConversationSequenceSpeechID[0] = SOUND_CRA1_HH
			$tlConversationSequenceSubtitle[0] = CRA1_HH
			BREAK

		CASE 8
			nConversationSequenceSpeechID[0] = SOUND_CRA1_HJ
			$tlConversationSequenceSubtitle[0] = CRA1_HJ
			BREAK

		CASE 9
			nConversationSequenceSpeechID[0] = SOUND_CRA1_HK
			$tlConversationSequenceSubtitle[0] = CRA1_HK
			BREAK

		CASE 10
			nConversationSequenceSpeechID[0] = SOUND_CRA1_HL
			$tlConversationSequenceSubtitle[0] = CRA1_HL
			BREAK

	ENDSWITCH

	nCurrentMaxConversationLines = 1
RETURN


// ****************************************
// Prepare Conversation: Coochie Hero
Crash1_Prepare_Conversation_Coochie_Hero:

	// Conversation between Coochie and Carl 

	nConversationSequenceSpeechID[0] = SOUND_CRA1_JA
	$tlConversationSequenceSubtitle[0] = CRA1_JA

	nCurrentMaxConversationLines = 1

RETURN


// ****************************************
// Prepare Conversation: Drive Coochie Home
Crash1_Prepare_Conversation_Drive_Coochie_Home:

	// Conversation between Coochie and Carl 

	nTempInt = 0
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_JB
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_JB

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_JC
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_JC

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_JD
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_JD

	nTempInt++
	IF nTempInt > CRASH1_MAX_CONVERSATION_LINES
		WRITE_DEBUG TOO_MANY_CONVERSATION_LINES
	ENDIF

	nCurrentMaxConversationLines = nTempInt

RETURN


// ****************************************
// Prepare Conversation: On Ride Home
Crash1_Prepare_Conversation_On_Ride_Home:

	// Conversation between Coochie and Carl 

	nTempInt = 0
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_KA
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_KA

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_KB
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_KB

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_KC
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_KC

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_KD
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_KD

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_KE
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_KE

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_KF
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_KF

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_KG
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_KG

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_KH
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_KH

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_KJ
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_KJ

	nTempInt++
	IF nTempInt > CRASH1_MAX_CONVERSATION_LINES
		WRITE_DEBUG TOO_MANY_CONVERSATION_LINES
	ENDIF

	nCurrentMaxConversationLines = nTempInt

RETURN


// ****************************************
// Prepare Conversation: Coochie House
Crash1_Prepare_Conversation_Coochie_House:

	// Conversation between Coochie and Carl 

	nConversationSequenceSpeechID[0] = SOUND_CRA1_LA
	$tlConversationSequenceSubtitle[0] = CRA1_LA

	nCurrentMaxConversationLines = 1

RETURN


// ****************************************
// Prepare Conversation: Coochie Name
Crash1_Prepare_Conversation_Coochie_Name:

	// Conversation between Coochie and Carl 

	nTempInt = 0
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_MA
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_MA

	nTempInt++
	nConversationSequenceSpeechID[nTempInt] = SOUND_CRA1_MB
	$tlConversationSequenceSubtitle[nTempInt] = CRA1_MB

	nTempInt++
	IF nTempInt > CRASH1_MAX_CONVERSATION_LINES
		WRITE_DEBUG TOO_MANY_CONVERSATION_LINES
	ENDIF

	nCurrentMaxConversationLines = nTempInt

RETURN


// ****************************************
// Prepare Conversation: Go Out
Crash1_Prepare_Conversation_Go_Out:

	// Conversation between Coochie and Carl 

	nConversationSequenceSpeechID[0] = SOUND_CRA1_MC
	$tlConversationSequenceSubtitle[0] = CRA1_MC

	nCurrentMaxConversationLines = 1

RETURN


// ****************************************
// Prepare Conversation: Cya
Crash1_Prepare_Conversation_Cya:

	// Conversation between Coochie and Carl 

	nConversationSequenceSpeechID[0] = SOUND_CRA1_MD
	$tlConversationSequenceSubtitle[0] = CRA1_MD

	nCurrentMaxConversationLines = 1

RETURN




// *************************************************************************************************************
// 												HOUSEKEEPING GOSUBS   
// *************************************************************************************************************

// ****************************************
// DEBUG TOOLS
Crash1_Debug_Tools:

	// Display mission stage variables for debug
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED  PS2_KEY_SPACE
		display_debug++

		IF display_debug > 2
			display_debug = 0
		ENDIF

		CLEAR_ALL_VIEW_VARIABLES
	ENDIF


	GET_CHAR_HEALTH scplayer nTempInt
	IF NOT IS_CHAR_DEAD charCoochie
		GET_CHAR_HEALTH charCoochie nTempInt2
	ENDIF


	IF display_debug = 1
		// system variables
		Crash1_view_debug[0] = m_stage
		Crash1_view_debug[1] = m_goals
		Crash1_view_debug[2] = m_mission_timer
		Crash1_view_debug[3] = nTempInt
		Crash1_view_debug[4] = nTempInt2
		Crash1_view_debug[5] = aiCoochie
		Crash1_view_debug[6] = 0
		Crash1_view_debug[7] = 0
		VIEW_INTEGER_VARIABLE Crash1_view_debug[6] ignore
		VIEW_INTEGER_VARIABLE Crash1_view_debug[7] ignore
		VIEW_INTEGER_VARIABLE Crash1_view_debug[0] m_stage
		VIEW_INTEGER_VARIABLE Crash1_view_debug[1] m_goals
		VIEW_INTEGER_VARIABLE Crash1_view_debug[2] m_mission_timer
		VIEW_INTEGER_VARIABLE Crash1_view_debug[3] player_health
		VIEW_INTEGER_VARIABLE Crash1_view_debug[4] coochie_health
		VIEW_INTEGER_VARIABLE Crash1_view_debug[5] aiCoochie
	ENDIF


	IF display_debug = 2
	// put mission variable for display in here

	//	Crash1_view_debug[0] = m_stage
	//	Crash1_view_debug[1] = m_goals
	//	Crash1_view_debug[2] = dialogue_flag
	//	Crash1_view_debug[3] = dialogue_timer
	//	Crash1_view_debug[4] = help_flag
	//	Crash1_view_debug[5] = help_timer
	//	Crash1_view_debug[6] = TIMERA
	//	Crash1_view_debug[7]	= TIMERB
	//	VIEW_INTEGER_VARIABLE Crash1_view_debug[0] m_stage
	//	VIEW_INTEGER_VARIABLE Crash1_view_debug[1] m_goals
	//	VIEW_INTEGER_VARIABLE Crash1_view_debug[2] dialogue_flag
	//	VIEW_INTEGER_VARIABLE Crash1_view_debug[3] dialogue_timer
	//	VIEW_INTEGER_VARIABLE Crash1_view_debug[4] help_flag
	//	VIEW_INTEGER_VARIABLE Crash1_view_debug[5] help_timer
	//	VIEW_INTEGER_VARIABLE Crash1_view_debug[6] TIMERA
	//	VIEW_INTEGER_VARIABLE Crash1_view_debug[7] TIMERB
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
Crash1_Debug_Shortcuts:

	IF NOT IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_J
		RETURN
	ENDIF

	// Jump to the house
	IF m_stage = 1
	AND m_goals < 3
		// Position the player at the house
		CLEAR_AREA 2311.2341 -1150.6134 25.8027 0.5 0
		LOAD_SCENE 2311.2341 -1150.6134 25.8027
		SET_CHAR_HEADING scplayer 266.3994
		SET_CHAR_COORDINATES scplayer 2311.2341 -1150.6134 25.8027 
		SET_CAMERA_BEHIND_PLAYER

		// Remove Ammunation Blip variable
		REMOVE_BLIP blipDestination
		REMOVE_BLIP blipMolotovsOnRoute

		// Give molotovs if none
		IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_MOLOTOV
			REMOVE_PICKUP pickupMolotovsOnRoute
			GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_MOLOTOV 20
		ENDIF

		m_goals = 3
	ENDIF

	// Jump to the 'Screaming Girl' cutscene
	IF m_stage = 2
		// Clean up 'Rooms on Fire'
		REPEAT 5 nLoop
			IF flagTargetRoomHit[nLoop] = 0
				IF NOT HAS_OBJECT_BEEN_DAMAGED objectWindow[nLoop]
					BREAK_OBJECT objectWindow[nLoop] TRUE
				ENDIF

				flagTargetRoomHit[nLoop] = 1							  
				flagTargetRoomBurning[nLoop] = TRUE
				DELETE_OBJECT objectTargetRoom[nLoop]
				REMOVE_BLIP blipTargetRoom[nLoop]
			ENDIF
		ENDREPEAT

		countTargetRoomsHit = 5

		// Clean up Molotov Pickups
		IF flagMolotovPickupsActive = 1
			REPEAT 2 nLoop
				REMOVE_PICKUP pickupMolotovs[nLoop]
				REMOVE_BLIP blipMolotovPickups[nLoop]
			ENDREPEAT
		ENDIF

		IF flagMolotovsOnRouteActive = 1
			REMOVE_PICKUP pickupMolotovsOnRoute
			REMOVE_BLIP blipMolotovsOnRoute
		ENDIF

		// Kill off GangOutside
		IF existsGangOutside = 1
			REPEAT 5 nLoop
				IF NOT IS_CHAR_DEAD charGangOutside[nLoop]
					CLEAR_CHAR_TASKS_IMMEDIATELY charGangOutside[nLoop]
					TASK_DIE charGangOutside[nLoop]
				ENDIF
			ENDREPEAT
		ENDIF

		// Kill off GangBackup
		IF existsGangBackup = 1
			REPEAT 5 nLoop
				IF NOT IS_CHAR_DEAD charGangBackup[nLoop]
					CLEAR_CHAR_TASKS_IMMEDIATELY charGangBackup[nLoop]
					TASK_DIE charGangBackup[nLoop]
				ENDIF
			ENDREPEAT
		ENDIF

		GOSUB Crash1_next_stage
	ENDIF

	// Jump to 'outside girl's room with extinguisher'
	IF m_stage = 4
	AND m_goals < 10
		// Give player the fire extinguisher
		flagFireExHelpDisplayed = 1
		IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_EXTINGUISHER
			GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_EXTINGUISHER CRASH1_INFINITE_AMMO
			REMOVE_BLIP blipDestination
		ENDIF

		// Position the player outside the room
		CLEAR_AREA 2336.5095 -1181.3417 1030.9688 0.5 0
		SET_CHAR_HEADING scplayer 279.3672
		SET_CHAR_COORDINATES scplayer 2336.5095 -1181.3417 1030.9688 
		SET_CAMERA_BEHIND_PLAYER

		// Stop protecting hte girl from flames
		flagClearFlamesAroundGirl = FALSE
		SET_CHAR_PROOFS charCoochie FALSE FALSE FALSE FALSE FALSE

		// Prepare the 'outside room' speech
		IF NOT nCurrentConversationID = CRASH1_CONVERSATION_NONE
			GOSUB Crash1_Conversation_Command_Cancel
		ENDIF

		nRequiredConversationID = CRASH1_CONVERSATION_OUTSIDE_ROOM
		GOSUB Crash1_Conversation_Command_Prepare

		m_goals = 11
	ENDIF

	// Jump to 'ready to leave house with girl'
	IF m_stage = 6
	AND m_goals < 8
		REMOVE_BLIP blipDestination
		// Move player 
		CLEAR_AREA 2350.3596 -1180.5604 1026.9766 0.5 0
		SET_CHAR_HEADING scplayer 266.3335
		SET_CHAR_COORDINATES scplayer 2350.3596 -1180.5604 1026.9766 
		SET_CAMERA_BEHIND_PLAYER

		// Move Coochie
		IF NOT IS_CHAR_DEAD charCoochie
			CLEAR_AREA 2349.0698 -1181.5055 1026.9834 0.5 0
			SET_CHAR_HEADING charCoochie 266.6129
			SET_CHAR_COORDINATES charCoochie 2349.0698 -1181.5055 1026.9834 
		ENDIF

		m_goals = 8
	ENDIF

	// Jump to girl's house
	IF m_stage = 7
	AND m_goals = 2
		// Move player 
		CLEAR_AREA 2423.5388 -1721.2123 12.9061 0.5 0
		SET_CHAR_HEADING scplayer 154.9302
		SET_CHAR_COORDINATES scplayer 2423.5388 -1721.2123 12.9061 
		SET_CAMERA_BEHIND_PLAYER

		// Move Coochie
		IF NOT IS_CHAR_DEAD charCoochie
			IF NOT IS_CHAR_IN_ANY_CAR charCoochie
				CLEAR_AREA 2419.1685 -1718.1705 12.7866 0.5 0
				SET_CHAR_HEADING charCoochie 75.9556
				SET_CHAR_COORDINATES charCoochie 2419.1685 -1718.1705 12.7866
			ENDIF
		ENDIF
	ENDIF

RETURN



// ****************************************
// FRAME COUNTER (Useful if processor scheduling is needed)
Crash1_Frame_Counter:

	m_frame_num++
	IF m_frame_num > 9
		m_frame_num = 0
	ENDIF

RETURN



// ****************************************
// ADDITIONAL TIMERS
Crash1_Additional_Timers:

	GET_GAME_TIMER m_this_frame_time
	m_time_diff = m_this_frame_time - m_last_frame_time 
	m_last_frame_time = m_this_frame_time

	m_mission_timer += m_time_diff

RETURN



// ****************************************
// UPDATE ORIENTATE TIMER
Crash1_UpdateTimer_OrientateTimer:

	GET_GAME_TIMER nTempTimer

	flagOrientate = 0
	IF nTempTimer > timerOrientate
		flagOrientate = 1
		timerOrientate = nTempTimer + 3000
	ENDIF

RETURN



// ****************************************
// CHECK IF IN HOUSE
Crash1_CheckIf_In_House:

	// NOTE: When entering a house, it needs to use the GET_CHAR_AREA_VISIBLE for the player so that the Interior ID
	//		of the player and the Entry/Exit that the player used both get updated at the same time.
	// When exiting a house, it needs to use the GET_AREA_VISIBLE which is the GAME's visible area because this
	//		changes immediately to be the outside area whereas the GET_CHAR_AREA_VISIBLE for the player doesn't until
	//		the player has played the 'exiting house' sequence and appears outside. The problem with this is that
	//		Coochie doesn't get frozen until the player is outside and this leads to all sorts of problems if she
	//		follows him out of the house.

	// If the player is in the house, check if the GAME has registered him as being out of the house
	IF flagInHouse = TRUE
		// Use the GAME's visible area
		GET_AREA_VISIBLE nTempInt
		IF NOT nTempInt = 5
			// ...no longer in the house
			flagInHouse = FALSE
			flagLeavingHouse = TRUE

			// ...allow the player to sprint again
			DISABLE_PLAYER_SPRINT player1 FALSE

			// ...remove player smoke effect
			statusSmoke = 3
			GOSUB Crash1_Update_Smoke

			// ...remove heathaze effect in the house
			SET_HEATHAZE_EFFECT FALSE

			// Record the status of the big fires so they can be reactivated if the player comes back into the house
			IF m_stage = 5
				statusBigFires = 5
				GOSUB Crash1_Update_Big_Fires
			ENDIF

			// Remove the existing small fires and re-create them at ground level
			statusFires = CRASH1_EXTERIOR_FIRE_ALL
			GOSUB Crash1_Update_Fires_Exterior

			// Unzoom the radar
			SET_RADAR_ZOOM 0
		ENDIF

		RETURN
	ENDIF

	// The player is not in the house, so check if he has entered it
	IF flagInHouse = FALSE
		// Use the PLAYER's visible area and the Entry/Exit used
		GET_CHAR_AREA_VISIBLE scplayer nTempInt
		IF flagLeavingHouse = TRUE
			IF nTempInt = 5
				// ...still leaving house
				RETURN
			ELSE
				// ...definitely left house
				flagLeavingHouse = FALSE
			ENDIF
		ENDIF

		// Check if the player has entered the house
		IF nTempInt = 5
			GET_NAME_OF_ENTRY_EXIT_CHAR_USED scplayer txtString
			IF $txtString = &GANG
				// ...definitely just entered the Burning Desire house
				flagInHouse = TRUE
				flagBeenInHouse = TRUE
				flagCheckingForCoochie = FALSE
				timerOutsideDelay = 0
				DISABLE_PLAYER_SPRINT player1 TRUE

				// ...create player smoke effect
				statusSmoke = 2
				GOSUB Crash1_Update_Smoke

				// ...create heathaze effect in the house
				SET_HEATHAZE_EFFECT TRUE

				// ...make sure the player takes less fire damage while in the house
				SET_CHAR_FIRE_DAMAGE_MULTIPLIER scplayer 0.3

				// ...make sure the navigation nodes are switched on
				SWITCH_PED_ROADS_ON 2300.0 -1200.0 1000.0 2370.0 -1160.0 1050.0

				// Remove the existing small fires and re-create them in the interior (1000m in air)
				statusFires = 0
				GOSUB Crash1_Update_Fires_Interior

				// If the player goes outside at any time during stage 4 (reach the lady) and then comes back in,
				//		reactivate the fire at her bedroom door
				IF m_stage = 4
					statusBigFires = 4
					GOSUB Crash1_Update_Big_Fires
				ENDIF

				// If the player goes outside at any time during stage 5 (lead the lady out) and then comes back in,
				//		reactivate the fires that were burning when he left
				IF m_stage = 5
					statusBigFires = 6
					GOSUB Crash1_Update_Big_Fires
				ENDIF

				// Spread some cash pickups around the house
				GOSUB Crash1_Create_Room_Cash_Pickups

				// Zoom the radar and makle sure all blips get shown on the zoomed radar
				SET_RADAR_ZOOM 50
				SHOW_BLIPS_ON_ALL_LEVELS TRUE

				// Get rid of the 'enter the house' text
				CLEAR_THIS_PRINT CRA1_06

				// Get rid of the 'go back for girl' text
				CLEAR_THIS_PRINT CRA1_10
			ENDIF
		ENDIF
	ENDIF

RETURN



// =================================================================
// GOSUB: Next_Stage (sets flags and timers for next mission stage)
// =================================================================
Crash1_next_stage:

   	m_stage++
   	m_goals        = 0
   	TIMERA 		   = 0
   	TIMERB		   = 0

RETURN					





// *************************************************************************************************************
//										INTRO CUTSCENE GOSUB
// *************************************************************************************************************

Crash1_Intro_Cutscene:

	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE


	SET_AREA_VISIBLE 17


	SET_CHAR_COORDINATES scplayer 1038.1704 -1335.2207 12.5493
	SET_CHAR_HEADING scplayer 0.0


	LOAD_CUTSCENE CRASH1A
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

	RESTORE_CAMERA_JUMPCUT
	SET_PLAYER_CONTROL player1 ON
	SWITCH_WIDESCREEN OFF

RETURN




// *************************************************************************************************************
// 												INITIALISATION GOSUBS   
// *************************************************************************************************************

Crash1_Initialisation:

	WHILE NOT IS_PLAYER_PLAYING player1
		WAIT 0
	ENDWHILE


	GOSUB Crash1_Load_All_Models
	GOSUB Crash1_Load_All_Anims


	// Position the player
	CLEAR_AREA 1038.1704 -1335.2207 12.5493 0.5 0
	LOAD_SCENE 1038.1704 -1335.2207 12.5493
	SET_CHAR_COORDINATES scplayer 1038.1704 -1335.2207 12.5493 
	SET_CHAR_HEADING scplayer 0.0
	SET_CAMERA_BEHIND_PLAYER


	// Create a Cop Car outside the Donut shop
	CREATE_CAR COPCARLA 1032.4376 -1330.9314 12.3864 carCopCar
	SET_CAR_HEADING carCopCar 270.0000
	LOCK_CAR_DOORS carCopCar CARLOCK_UNLOCKED
	MARK_CAR_AS_NO_LONGER_NEEDED carCopCar


	// Don't allow entry to the gang house
	SWITCH_ENTRY_EXIT GANG FALSE


	// Delete the global Object Burning Desire door
	DELETE_OBJECT g_BD_DOOR


	// Disband the player's group and prevent recruitment
	REMOVE_GROUP Players_Group
	SET_PLAYER_GROUP_RECRUITMENT player1 FALSE


	// Disable the mod garage (to prevent a potential problem failing while in it during the mission)
	disable_mod_garage = TRUE


	// Fade in
	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_IN

	GOSUB Crash1_next_stage

RETURN


// ***********************************
// LOAD ALL MODELS

Crash1_Load_All_Models:

	// CARS
	REQUEST_MODEL VOODOO	// Car with hood popped, and gangBackup car
	REQUEST_MODEL COPCARLA	// Outside donut shop

	// PEDS
	REQUEST_MODEL LSV1		// Gang guy type 1
	REQUEST_MODEL LSV2		// Gang guy type 2
	REQUEST_MODEL LSV3		// Gang guy type 3
	REQUEST_MODEL GANGRL3	// Coochie (the girl to be rescued)

	// GUNS
	REQUEST_MODEL COLT45	// pistol
	REQUEST_MODEL MOLOTOV	// molotov
	REQUEST_MODEL FIRE_EX	// fire extinguisher

	// OBJECTS
	REQUEST_MODEL BREAK_WALL_1A		// Normal Wall 0
	REQUEST_MODEL BREAK_WALL_2A		// Normal Wall 1
	REQUEST_MODEL BREAK_WALL_3A		// Normal Wall 2
	REQUEST_MODEL BREAK_WALL_1B		// Broken Wall 0
	REQUEST_MODEL BREAK_WALL_2B		// Broken Wall 1
	REQUEST_MODEL BREAK_WALL_3B		// Broken Wall 2
	REQUEST_MODEL BD_FIRE1_O		// Animated Wall 0
	REQUEST_MODEL CR_AMMOBOX		// Invisible obbject at breakable windows

	LOAD_ALL_MODELS_NOW


	// Are Cars Loaded?
	WHILE NOT HAS_MODEL_LOADED	VOODOO
	OR NOT HAS_MODEL_LOADED		COPCARLA
		WAIT 0
	ENDWHILE

	// Are Peds Loaded?
	WHILE NOT HAS_MODEL_LOADED	LSV1
	OR NOT HAS_MODEL_LOADED		LSV2
	OR NOT HAS_MODEL_LOADED		LSV3
	OR NOT HAS_MODEL_LOADED		GANGRL3
		WAIT 0
	ENDWHILE

	// Are weapons loaded?
	WHILE NOT HAS_MODEL_LOADED	COLT45
	OR NOT HAS_MODEL_LOADED		MOLOTOV
	OR NOT HAS_MODEL_LOADED		FIRE_EX
		WAIT 0
	ENDWHILE

	// Are the Objects loaded?
	WHILE NOT HAS_MODEL_LOADED	BREAK_WALL_1B
	OR NOT HAS_MODEL_LOADED		BREAK_WALL_2B
	OR NOT HAS_MODEL_LOADED		BREAK_WALL_3B
	OR NOT HAS_MODEL_LOADED		BD_FIRE1_O
		WAIT 0
	ENDWHILE

	WHILE NOT HAS_MODEL_LOADED	CR_AMMOBOX
	OR NOT HAS_MODEL_LOADED		BREAK_WALL_1A
	OR NOT HAS_MODEL_LOADED		BREAK_WALL_2A
	OR NOT HAS_MODEL_LOADED		BREAK_WALL_3A
		WAIT 0
	ENDWHILE

RETURN


// ***********************************
// LOAD ALL ANIMS

Crash1_Load_All_Anims:

	// Gang Outside anims
	REQUEST_ANIMATION BD_FIRE				// for BD specific anims


	// Are Gang Outside anims loaded?
	WHILE NOT HAS_ANIMATION_LOADED	BD_FIRE
		WAIT 0
	ENDWHILE

RETURN






// *************************************************************************************************************
//										ENTITY CREATION GOSUBS  
// *************************************************************************************************************

// *******************************
// Car outside Gang Hunt with Hood open
Crash1_Create_Car_Being_Worked_On:

	xposTemp = 2351.2366
	yposTemp = -1161.0748
	zposTemp = 27.4175
	headTemp = 268.3430

	CLEAR_AREA xposTemp yposTemp zposTemp 10.0 FALSE
	CREATE_CAR VOODOO xposTemp yposTemp zposTemp carBeingWorkedOn
	SET_CAR_HEADING carBeingWorkedOn headTemp
	OPEN_CAR_DOOR carBeingWorkedOn BONNET

	existsCarBeingWorkedOn = 1

RETURN


// *******************************
// Five outside gang guys
Crash1_Create_Outside_Gang_Members:

	// Gang Guy 0: Mechanic
	xposTemp = 2354.2604
	yposTemp = -1161.1060
	zposTemp = 26.4678
	headTemp = 80.3034

	nTempInt = 0
	CREATE_CHAR PEDTYPE_GANG_SMEX LSV3 xposTemp yposTemp zposTemp charGangOutside[nTempInt]
	SET_CHAR_HEADING charGangOutside[nTempInt] headTemp 
	GIVE_WEAPON_TO_CHAR charGangOutside[nTempInt] WEAPONTYPE_PISTOL 999
	SET_CHAR_IS_TARGET_PRIORITY charGangOutside[nTempInt] TRUE
	SET_CHAR_DECISION_MAKER charGangOutside[nTempInt] dmEmpty
	aiGangOutside[nTempInt] = -1


	// Gang Guy 1: Speaking on mobile phone
	xposTemp = 2349.7393
	yposTemp = -1165.1923
	zposTemp = 26.3933
	headTemp = 65.0946	//325.2994

	nTempInt++
	CREATE_CHAR PEDTYPE_GANG_SMEX LSV2 xposTemp yposTemp zposTemp charGangOutside[nTempInt]
	SET_CHAR_HEADING charGangOutside[nTempInt] headTemp 
	GIVE_WEAPON_TO_CHAR charGangOutside[nTempInt] WEAPONTYPE_PISTOL 999
	SET_CHAR_IS_TARGET_PRIORITY charGangOutside[nTempInt] TRUE
	SET_CHAR_DECISION_MAKER charGangOutside[nTempInt] dmEmpty
	aiGangOutside[nTempInt] = -1


	// Gang Guy 2: Smoking at Front Door
	xposTemp = 2354.2446
	yposTemp = -1170.4009
	zposTemp = 26.9609
	headTemp = 1.1416

	nTempInt++
	CREATE_CHAR PEDTYPE_GANG_SMEX LSV1 xposTemp yposTemp zposTemp charGangOutside[nTempInt]
	SET_CHAR_HEADING charGangOutside[nTempInt] headTemp 
	GIVE_WEAPON_TO_CHAR charGangOutside[nTempInt] WEAPONTYPE_PISTOL 999
	SET_CHAR_IS_TARGET_PRIORITY charGangOutside[nTempInt] TRUE
	SET_CHAR_DECISION_MAKER charGangOutside[nTempInt] dmEmpty
	aiGangOutside[nTempInt] = -1


	// Gang Guy 3: Smoking against tree (back of house at right)
	xposTemp = 2315.0057
	yposTemp = -1189.8250
	zposTemp = 27.0045
	headTemp = 52.4876

	nTempInt++
	CREATE_CHAR PEDTYPE_GANG_SMEX LSV2 xposTemp yposTemp zposTemp charGangOutside[nTempInt]
	SET_CHAR_HEADING charGangOutside[nTempInt] headTemp 
	GIVE_WEAPON_TO_CHAR charGangOutside[nTempInt] WEAPONTYPE_PISTOL 999
	SET_CHAR_IS_TARGET_PRIORITY charGangOutside[nTempInt] TRUE
	SET_CHAR_DECISION_MAKER charGangOutside[nTempInt] dmEmpty
	SET_CHAR_ACCURACY charGangOutside[nTempInt] 15
	aiGangOutside[nTempInt] = -1


	// Gang Guy 4: Smoking against other house (back of house at left)
	xposTemp = 2349.9534
	yposTemp = -1204.1256
	zposTemp = 26.9609
	headTemp = 348.4000

	nTempInt++
	CREATE_CHAR PEDTYPE_GANG_SMEX LSV1 xposTemp yposTemp zposTemp charGangOutside[nTempInt]
	SET_CHAR_HEADING charGangOutside[nTempInt] headTemp 
	GIVE_WEAPON_TO_CHAR charGangOutside[nTempInt] WEAPONTYPE_PISTOL 999
	SET_CHAR_IS_TARGET_PRIORITY charGangOutside[nTempInt] TRUE
	SET_CHAR_DECISION_MAKER charGangOutside[nTempInt] dmEmpty
	aiGangOutside[nTempInt] = -1

	existsGangOutside = 1

RETURN



// *******************************
// House Windows
Crash1_Create_Windows:

	// Front Downstairs Right Window
	xposTemp = 2345.914
	yposTemp = -1188.633
	zposTemp = 28.969

	nTempInt = 0
	CREATE_OBJECT_NO_OFFSET bd_window xposTemp yposTemp zposTemp objectWindow[nTempInt]


	// Front Downstairs Left Window
	xposTemp = 2336.023
	yposTemp = -1188.633
	zposTemp = 28.969

	nTempInt++
	CREATE_OBJECT_NO_OFFSET bd_window xposTemp yposTemp zposTemp objectWindow[nTempInt]


	// Front Downstairs Far Left Window
	xposTemp = 2326.164
	yposTemp = -1188.633
	zposTemp = 28.969

	nTempInt++
	CREATE_OBJECT_NO_OFFSET bd_window xposTemp yposTemp zposTemp objectWindow[nTempInt]


	// Window by Front Door
	xposTemp = 2345.977
	yposTemp = -1170.977
	zposTemp = 28.969

	nTempInt++
	CREATE_OBJECT_NO_OFFSET bd_window xposTemp yposTemp zposTemp objectWindow[nTempInt]
	SET_OBJECT_HEADING objectWindow[nTempInt] 180.0


	// Kitchen Window
	xposTemp = 2326.57
	yposTemp = -1170.984
	zposTemp = 28.953

	nTempInt++
	CREATE_OBJECT_NO_OFFSET bd_window xposTemp yposTemp zposTemp objectWindow[nTempInt]
	SET_OBJECT_HEADING objectWindow[nTempInt] 180.0


	// Girls Window
	xposTemp = 2345.977
	yposTemp = -1170.977
	zposTemp = 32.953

	nTempInt++
	CREATE_OBJECT_NO_OFFSET bd_window_shatter xposTemp yposTemp zposTemp objectWindow[nTempInt]
	SET_OBJECT_HEADING objectWindow[nTempInt] 180.0


	// Front Upstairs Left Window
	xposTemp = 2336.023
	yposTemp = -1188.633
	zposTemp = 32.961

	nTempInt++
	CREATE_OBJECT_NO_OFFSET bd_window xposTemp yposTemp zposTemp objectWindow[nTempInt]


	// Front Upstairs Right Window
	xposTemp = 2345.914
	yposTemp = -1188.633
	zposTemp = 32.961

	nTempInt++
	CREATE_OBJECT_NO_OFFSET bd_window xposTemp yposTemp zposTemp objectWindow[nTempInt]

RETURN



// *******************************
// Front Door
Crash1_Create_Front_Door:

	// Create the mission version of the BD door
	CREATE_OBJECT CR1_DOOR 2352.8512 -1171.0266 26.9669 objectFrontDoor
	SET_OBJECT_HEADING objectFrontDoor 90.0000 
	SET_OBJECT_DYNAMIC objectFrontDoor FALSE
	SET_OBJECT_COLLISION_DAMAGE_EFFECT objectFrontDoor FALSE
	SET_OBJECT_PROOFS objectFrontDoor TRUE TRUE TRUE TRUE TRUE

RETURN



// *******************************
// Front Door
Crash1_Create_Walls:

	// Wall 0
	xposTemp = 2338.126
	yposTemp = -1181.917
	zposTemp = 1033.188

	nTempInt = 0
	CREATE_OBJECT_NO_OFFSET	BREAK_WALL_1A xposTemp yposTemp zposTemp objectWall[nTempInt]
	SET_OBJECT_HEADING objectWall[nTempInt] 90.0
	statusWalls[nTempInt] = 0


	// Wall 1
	xposTemp = 2330.402
	yposTemp = -1179.15
	zposTemp = 1030.55

	nTempInt++
	CREATE_OBJECT_NO_OFFSET	BREAK_WALL_2A xposTemp yposTemp zposTemp objectWall[nTempInt]
	statusWalls[nTempInt] = 0


	// Wall 2
	xposTemp = 2340.293
	yposTemp = -1182.294
	zposTemp = 1026.963

	nTempInt++
	CREATE_OBJECT_NO_OFFSET	BREAK_WALL_3A xposTemp yposTemp zposTemp objectWall[nTempInt]
	SET_OBJECT_HEADING objectWall[nTempInt] 90.0
	statusWalls[nTempInt] = 0

	// These objects will need interior collision
	REPEAT 3 nLoop
		SET_OBJECT_AREA_VISIBLE objectWall[nLoop] 5
	ENDREPEAT

	existsWalls = 1

RETURN





// *************************************************************************************************************
//										END OF LEVEL GOSUBS - fail, pass, cleanup  
// *************************************************************************************************************

// FAIL
mission_failed_Crash1:

	PRINT_BIG M_FAIL 5000 1

	IF flagGirlIsToast = FALSE
	AND existsCoochie = TRUE
	AND IS_CHAR_DEAD charCoochie
		// Girl didn't burn to death so display a more generic 'girl dead' message
		PRINT_NOW CRA1_51 5000 1
	ENDIF

RETURN


// PASS
mission_passed_Crash1:

	// This mission gives no cash or respect reward, so use M_PASSD label
	PRINT_WITH_NUMBER_BIG M_PASSD 0 5000 1
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1
	REGISTER_MISSION_PASSED CRASH_1
	PLAYER_MADE_PROGRESS 1

	flag_crash_mission_counter ++

	// Make Coochie a girlfriend
	SET_BIT iActiveGF COOCHIE

RETURN


// CLEANUP
mission_cleanup_Crash1:

	// Entity Clearup
	// ...gang outside
	IF existsGangOutside = 1
		REPEAT 5 nLoop
			MARK_CHAR_AS_NO_LONGER_NEEDED	charGangOutside[nLoop]
		ENDREPEAT

		existsGangOutside = 0
	ENDIF


	// ...gang car being worked on
	IF existsCarBeingWorkedOn = 1
		MARK_CAR_AS_NO_LONGER_NEEDED		carBeingWorkedOn

		existsCarBeingWorkedOn = 0
	ENDIF


	// ...molotov pickups
	IF flagMolotovPickupsActive = 1
		REPEAT 2 nLoop
			REMOVE_PICKUP pickupMolotovs[nLoop]
			REMOVE_BLIP blipMolotovPickups[nLoop]
		ENDREPEAT

		flagMolotovPickupsActive = 0
	ENDIF

	// ...cash pickups
	REPEAT CRASH1_MAX_CASH_PICKUPS nLoop
		REMOVE_PICKUP pickupCash[nLoop]
	ENDREPEAT


	// ...gang backup
	IF existsGangBackup = 1
		MARK_CAR_AS_NO_LONGER_NEEDED		carGangBackup

		REPEAT 2 nLoop
			MARK_CHAR_AS_NO_LONGER_NEEDED	charGangBackup[nLoop]
		ENDREPEAT

		existsGangBackup = 0
	ENDIF


	// ...guy on fire
	IF existsGuyOnFire = 1
		MARK_CHAR_AS_NO_LONGER_NEEDED	charGuyOnFire
		REMOVE_SCRIPT_FIRE fireGuyOnFire

		existsGuyOnFire = 0
	ENDIF


	// ...coochie
	IF existsCoochie = 1
		MARK_CHAR_AS_NO_LONGER_NEEDED	charCoochie
		REMOVE_BLIP blipCoochie
		CLEAR_ONSCREEN_COUNTER g_Crash1_counterKM1
		REMOVE_SCRIPT_FIRE fireCoochie
		existsCoochie = 1
	ENDIF


	// ...house guys on way in
	IF existsHouseGuysIn = 1
		REMOVE_CHAR_ELEGANTLY	charHouseCower
		REMOVE_CHAR_ELEGANTLY	charHouseStairs
		existsHouseGuysIn = 0
	ENDIF


	// ...fire extinguisher
	IF flagFireExtinguisherPickupActive = 1
		REMOVE_PICKUP pickupFireExtinguisher

		flagFireExtinguisherPickupActive = 0
	ENDIF

	
	// Blips
	REMOVE_BLIP blipDestination
	REMOVE_BLIP blipMolotovsOnRoute


	// Animation Clearup
	REMOVE_ANIMATION BD_FIRE


	// Get rid of the Player's mission specific weapons
	IF IS_PLAYER_PLAYING player1
		REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_MOLOTOV
		REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_EXTINGUISHER

		// ...restore the player's fire damage
		SET_CHAR_FIRE_DAMAGE_MULTIPLIER scplayer 1.0

		// ...make sure the player can sprint again
		DISABLE_PLAYER_SPRINT player1 FALSE

		// ...make sure the player's group follows his commands again
		SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 FALSE

		// ...make sure player is allowed to talk again
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
	ENDIF


	// Fires
// Don't do this because all the fires will then be converted to non-script fires when mission is finished and keep burning
//	REMOVE_ALL_SCRIPT_FIRES


	// Smoke
	IF flagInHouse = 1
		// ...remove smoke effect
		statusSmoke = 3
		GOSUB Crash1_Update_Smoke
	ENDIF

	// ...remove entrance smoke
	statusSmoke = 1
	GOSUB Crash1_Update_Smoke

	// ...remove Coochie Window smoke
	statusSmoke = 5
	GOSUB Crash1_Update_Smoke


	// Models
	MARK_MODEL_AS_NO_LONGER_NEEDED VOODOO
	MARK_MODEL_AS_NO_LONGER_NEEDED COPCARLA
	MARK_MODEL_AS_NO_LONGER_NEEDED FIRETRUK

	// PEDS
	MARK_MODEL_AS_NO_LONGER_NEEDED LSV1
	MARK_MODEL_AS_NO_LONGER_NEEDED LSV2
	MARK_MODEL_AS_NO_LONGER_NEEDED LSV3
	MARK_MODEL_AS_NO_LONGER_NEEDED GANGRL3
	MARK_MODEL_AS_NO_LONGER_NEEDED LAFD1

	// GUNS
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
	MARK_MODEL_AS_NO_LONGER_NEEDED MOLOTOV
	MARK_MODEL_AS_NO_LONGER_NEEDED FIRE_EX

	// OBJECTS
	MARK_MODEL_AS_NO_LONGER_NEEDED BREAK_WALL_1A
	MARK_MODEL_AS_NO_LONGER_NEEDED BREAK_WALL_2A
	MARK_MODEL_AS_NO_LONGER_NEEDED BREAK_WALL_3A
	MARK_MODEL_AS_NO_LONGER_NEEDED BREAK_WALL_1B
	MARK_MODEL_AS_NO_LONGER_NEEDED BREAK_WALL_2B
	MARK_MODEL_AS_NO_LONGER_NEEDED BREAK_WALL_3B
	MARK_MODEL_AS_NO_LONGER_NEEDED BD_FIRE1_O
	MARK_MODEL_AS_NO_LONGER_NEEDED CR_AMMOBOX

			
	// Heat haze
	SET_HEATHAZE_EFFECT FALSE

	// === RESTORE ENVIRONMENT SETTINGS ===
	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_WANTED_MULTIPLIER 1.0
	SET_RADAR_ZOOM 0


	// Restore Player settings
	DISABLE_PLAYER_SPRINT player1 FALSE


	// Switch on emergency services
	SWITCH_EMERGENCY_SERVICES ON


	// Switch off the entry/exit to make sure the player is never allowed back in outside the mission
	SWITCH_ENTRY_EXIT GANG FALSE


	// Allow Group Recruitment again
	SET_PLAYER_GROUP_RECRUITMENT player1 TRUE


	// Re-enable the mod garage (to prevent a potential problem failing while in it during the mission)
	disable_mod_garage = FALSE


	// Re-create the Burning Desire door (global object)
	CREATE_OBJECT CR1_DOOR 2352.8512 -1171.0266 26.9669 g_BD_DOOR
	SET_OBJECT_HEADING g_BD_DOOR 90.0000 
	SET_OBJECT_DYNAMIC g_BD_DOOR FALSE
	SET_OBJECT_COLLISION_DAMAGE_EFFECT g_BD_DOOR FALSE
	SET_OBJECT_PROOFS g_BD_DOOR TRUE TRUE TRUE TRUE TRUE
	DONT_REMOVE_OBJECT g_BD_DOOR


	// Make sure the mobile phone doesn't ring immediately after a mission
	GET_GAME_TIMER timer_mobile_start


	flag_player_on_mission = 0
	MISSION_HAS_FINISHED

RETURN
	 

}