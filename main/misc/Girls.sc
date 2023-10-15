MISSION_START
	SCRIPT_NAME GFINIT
/*********************************************************************************************************************************************
************************************************						**********************************************************************
***********************************************		GIRLFRIENDS INIT	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/


/* N.B. ALL THESE ARE NOW IN MAIN.SC
	VAR_INT iGFSelfRespect[6] iGFLikesPlayer[6]	iGFDesiredSexAppeal[6] iGFLikesPlayerTraits[6]	// GF STATS
	VAR_INT iGFLikesOnDate[6] iGFDiaryOfBusyHours[6] // MORE GF STATS
	VAR_INT iGFidx //GF index for the arrays above, obviously no greater than 5			
	VAR_INT iDateReport iAgentFlags iPhoneState	iCaller
	VAR_INT iGFBonusPickupID[4]	iGFHomeBlips[6]
	VAR_TEXT_LABEL txtCurrZone // Zone string used a bit everywhere
	VAR_INT iActiveGF // Public Bitfield to record active GFs. 
			BITS:	0	GANG GIRL (Coochie Robinson)
					1	MECHANIC (Michelle Cannes)
					2	GUN NUT (Kylie Goodpoke)
					3	COP (Barbara Schternvart)
					4	NURSE (Suzie Nookie)
					5	CROUPIER (Millie)
					    //--- the values below are calculated. Do not push them around. 
				   10 	COOCHIE_NOT_AT_HOME
				   11	MICHELLE_NOT_AT_HOME
				   12	KYLIE_NOT_AT_HOME
				   13	BARBARA_NOT_AT_HOME
				   14	SUZIE_NOT_AT_HOME
				   15	MILLIE_NOT_AT_HOME
				   21	MICHELLE_BONUS_ACTIVE
				   22	KYLIE_BONUS_ACTIVE
				   23	BARBARA_BONUS_ACTIVE
				   24	SUZIE_BONUS_ACTIVE
						//--- CAR flags
				   25	CAR_COOCHIE_UNLOCKED_HELP	
				   26 	CAR_MICHELLE_UNLOCKED_HELP	
				   27	CAR_KYLIE_UNLOCKED_HELP	
				   28	CAR_BARBARA_UNLOCKED_HELP	
				   29	CAR_SUZIE_UNLOCKED_HELP	
				   30	CAR_MILLIE_UNLOCKED_HELP	
						//--- cheat mode
				   31	GF_CHEAT_MODE_ON
*/
//--------------------------------------------CONSTANTS-------------------------------------------
//--- AGENT'S TIME STEPS
CONST_INT GF_TIME_STEP_SLOW						500		// In milliseconds, how long to wait before looping again
CONST_INT GF_TIME_STEP_MEDIUM					250		// In milliseconds
CONST_INT GF_TIME_STEP_FAST						0		// This should always be ZERO = 1 frame
//--- AGENT'S SUPER STATES
CONST_INT GF_IDLE_TRY_TO_LOCATE_PLAYER			0
CONST_INT GF_LAUNCH_DATE_OR_SEX					1
CONST_INT GF_WRAP_UP_AND_UPDATE					2
CONST_INT GF_MEETING_WAIT						3
CONST_INT GF_START_DATE_WAIT					4
//--- MORE DATE REPORT CONSTS...
CONST_INT DATE_WAS_ABORTED						8
CONST_INT PLAYER_TWO_TIMING 					22
CONST_INT CHEAT_TWO_TIMING  					25
//--- MORE AGENT FLAG CONSTS...
CONST_INT GF_CAR_GENERATOR_ON					26
//--- MORE ACTTIVE GF FLAGS.... CAR FLAGS						
CONST_INT CAR_COOCHIE_UNLOCKED_HELP				25
CONST_INT CAR_MICHELLE_UNLOCKED_HELP			26
CONST_INT CAR_KYLIE_UNLOCKED_HELP				27
CONST_INT CAR_BARBARA_UNLOCKED_HELP				28
CONST_INT CAR_SUZIE_UNLOCKED_HELP				29
CONST_INT CAR_MILLIE_UNLOCKED_HELP				30
//--- STATS: ADDITIONS AND DEDUCTIONS
CONST_INT GF_LIKES_PLAYER_INCREMENT				5
CONST_INT GF_LIKES_PLAYER_INCREMENT_MEETING		15
CONST_INT GF_LIKES_PLAYER_DECREMENT				5
CONST_INT GF_LIKES_PLAYER_DECREMENT_MISS_DATE	3
CONST_INT GF_LIKES_PLAYER_DECREMENT_NO_ANSWER	2
CONST_INT GF_LIKES_PLAYER_DECREMENT_BEATEN_UP	10
CONST_INT GF_LIKES_PLAYER_DECREMENT_TWOTIMING	4
CONST_INT GF_LIKES_PLAYER_DECREMENT_DATE_ABORT	1
//--- FUN: ADDITIONS AND DEDUCTIONS
CONST_FLOAT GF_FUN_INCREMENT_SMALL				0.5
CONST_FLOAT GF_FUN_INCREMENT_MEDIUM				1.5
CONST_FLOAT GF_FUN_INCREMENT_BIG				4.0
CONST_FLOAT GF_FUN_DECREMENT_SMALL				0.5
CONST_FLOAT GF_FUN_DECREMENT_MEDIUM				1.0
CONST_FLOAT GF_FUN_DECREMENT_BIG				2.5
//--- STAT LIMITS
CONST_INT GF_LIKES_PLAYER_LOW_LIMIT 			-15
CONST_INT GF_HATES_PLAYER		  				-100
CONST_INT GF_IS_DEAD							-999
CONST_INT GF_DUMP_PLAYER_IMMEDIATELY 			-99
//---  LIKE PLAYER STAGES
CONST_INT GF_LIKES_PLAYER_STAGE1				30 		// Dates get more variety other than EAT_OUT
CONST_INT GF_LIKES_PLAYER_STAGE2				50 		// Girl's car is unlocked, player can drive it
CONST_INT GF_LIKES_PLAYER_STAGE3				100		// Player is awarded a unique set of clothes
//--- TIMER CHECKS & LIMITS
CONST_INT GF_TIME_STOPPED_CONSIDERED_PARKING	10000 	// Counted while the car is standing still
CONST_INT GF_TIME_FOR_RANDOM_SPEECH				25000 	// Counted while the car is driving
CONST_INT GF_TIME_BEFORE_TRIGGERING_TWOTIME		7000 	// Counted after an event if idle in car or on foot
CONST_INT GF_TIME_BEFORE_ESCAPED_TWOTIME		20000 	// Counted from when the player has distanced the GF
CONST_INT GF_TIME_MAX_BEFORE_BORED_WARNING		120000 	// The max time before the bored warning is issued
CONST_INT GF_TIME_MAX_BORED_WARNING_OUT_OF_TOWN	240000 	// Version of above for dates originating out of town
CONST_INT GF_TIME_BEFORE_BORED_END_OF_DATE		180000 	// The max time the girl can spend while doing nothing
CONST_INT GF_TIME_BORED_END_OF_DATE_OUT_OF_TOWN	300000 	// Version of above for dates originating out of town
CONST_INT GF_TIME_BEFORE_BORED_FLEE_PLAYER		360000 	// Max time after asking to be taken home before she runs
CONST_INT GF_TIME_BEFORE_START_FUN_CHECKS		30000	// First few seconds free for the player to sort himself
CONST_INT GF_TIME_BEFORE_AREA_COMMENTS			30000	// When a girl can start commenting on areas, on any date
CONST_INT GF_EARLIEST_MINUTE_FOR_MOBILE_CHECK	5		// 'Appointment missed' check is done between this and 0 
//--- COUNTERS OF THINGS
CONST_INT GF_AMMO_IN_UZI						60 		// Counted during a date, when empty she had enough 
//--- SPEECH MANAGER STATES
CONST_INT GF_SPEECH_FREE						0
CONST_INT GF_SPEECH_ENABLE_AI					1
CONST_INT GF_SPEECH_DISABLE_AI					2
CONST_INT GF_SPEECH_REQUEST						3
CONST_INT GF_SPEECH_DISABLE_AI_AND_SPEAK		4
CONST_INT GF_SPEECH_ENABLE_AI_WHEN_SILENT		5
CONST_INT GF_SPEECH_REQUEST_FOR_PLAYER			6
CONST_INT GF_SPEECH_SPECIAL_TT_CONTEXT			7
//--- MUTE GF SPEECH CONTEXTS
CONST_INT GF_CONTEXT_JUSTPLAYER					-2
//--- TWO-TIMING STATES
CONST_INT GF_TT_INIT							0
CONST_INT GF_TT_REQUESTS						1
CONST_INT GF_TT_CREATE_CAR						2
CONST_INT GF_TT_CUT1_START						3
CONST_INT GF_TT_CUT1_END						4
CONST_INT GF_TT_INTERCEPT_PLAYER				5
CONST_INT GF_TT_CHECK_PLAYER_POSITION			6
CONST_INT GF_TT_VERIFY_PLAYER_LOST				7
CONST_INT GF_TT_REACHED_PLAYER					8
CONST_INT GF_TT_CUT2_START						9
CONST_INT GF_TT_CUT2_MIDDLE						10
CONST_INT GF_TT_CUT2_END						11
CONST_INT GF_TT_END								12
CONST_INT GF_TT_END_WRAP_UP						13
CONST_INT GF_TT_EMERGENCY_END					99
CONST_INT GF_TT_DO_NOT_RUN						-1
//--- INTEGER LIMITS THAT VALUES CAN REACH
CONST_INT GF_ALLOWED_HEALTH_LOSS_BY_PLAYER 		498
CONST_INT GF_HEALTH_LIMIT_FOR_DEATH 			10
CONST_INT GF_CAR_DAMAGE_LIMIT_FOR_END_DATE		0 		// REMEMBER TO SET THIS EVENTUALLY!!!
CONST_INT GF_DANCE_SCORE_REQUIRED				3000
CONST_INT GF_SPECIAL_CLOTHES_APPEAL_INCREASE	25
CONST_INT GF_CHANCE_TWOTIMING_PERCENT			50 		// Chance of getting caught two-timing, out of 100 
//--- FLOAT LIMITS THAT VALUES CAN REACH
CONST_FLOAT GF_KISS_2_SELFRESPECT_DIVIDER	 	1.2	   	// It's a division so cannot be zero
CONST_FLOAT GF_PROXIMITY_OF_PEDS_AS_PUBLIC 		20.0
CONST_FLOAT GF_FAST_CAR_SPEED 					22.0
CONST_FLOAT GF_CRUISE_CAR_SPEED_LOW 			8.0
CONST_FLOAT GF_CRUISE_CAR_SPEED_FAST 			20.0
//--- MISC
CONST_INT GF_BARN_DOOR							6

//------------------------------------GIRLFRIEND RESPECT GLOBALS----------------------------------
VAR_INT iGFRespectGiven[6]
//-----------------------------------GIRLFRIEND TWO PLAYER GLOBALS--------------------------------
VAR_INT iGFAvailableFor2Player
//-------------------------------------DATE SEQUENCING GLOBALS------------------------------------
VAR_INT iTransitionStages iGFTimeStep 
//--------------------------------------SEX MINIGAME GLOBALS--------------------------------------
VAR_INT GF_left_stick_x GF_left_stick_y GF_right_stick_x GF_right_stick_y excitement power iCensoredVersion
VAR_FLOAT GF_anim_time
//-----------------------------------PS2 KEYBOARD CONSOLE GLOBALS----------------------------------
VAR_TEXT_LABEL16 GF_txtConsoleIn GF_txtConsoleCheck
//--------------------------------------GLOBAL DECISION MAKERS-------------------------------------
VAR_INT iGF_GlobalDM_Tough iGF_GlobalDM_Weak_Gang
//--------------------------------------DANCE MINIGAME GLOBALS--------------------------------------
VAR_INT iDanceGirlfriend 
//--------------------------------------SPEECH MANAGER GLOBALS--------------------------------------
VAR_INT iGFSpeechStatus iGFSayContext iCJSayContext iGFContextVariation  
//----------------------------------------TWO-TIMING GLOBALS----------------------------------------
VAR_INT iGF_TT_Status iGF_TT_driver	iGF_TT_Car iGF_TT_PedModel iGF_TT_CarModel iGF_TT_Blip
//--------------------------------------GF DATA STRUCTURES------------------------------------------
//--- GANG GIRL (COOCHIE)
	iGFSelfRespect[COOCHIE] = 30  
	iGFLikesPlayer[COOCHIE] = 15 	
	iGFDesiredSexAppeal[COOCHIE] = -100 // Coochie likes any piece of shit (as long as he's got personality!)
	iGFRespectGiven[COOCHIE] = 200

	iGFLikesOnDate[COOCHIE] = 0
		SET_BIT iGFLikesOnDate[COOCHIE] LIKES_JUNK_FOOD     
		SET_BIT iGFLikesOnDate[COOCHIE] LIKES_BARS
		SET_BIT iGFLikesOnDate[COOCHIE] LIKES_STUNTS
		SET_BIT iGFLikesOnDate[COOCHIE] LIKES_GANG_ZONES
		SET_BIT iGFLikesOnDate[COOCHIE] LIKES_PARK_BEACH_ZONES
		SET_BIT iGFLikesOnDate[COOCHIE] LIKES_ENTERTAINMENT_ZONES
		SET_BIT iGFLikesOnDate[COOCHIE] LIKES_GANG_FIGHTS	 		
		SET_BIT iGFLikesOnDate[COOCHIE] LIKES_SNOGGING_IN_PUBLIC				  

	iGFLikesPlayerTraits[COOCHIE] = 0
		SET_BIT iGFLikesPlayerTraits[COOCHIE] NORMAL

	iGFDiaryOfBusyHours[COOCHIE] = 0
		SET_BIT iGFDiaryOfBusyHours[COOCHIE] H_6AM
		SET_BIT iGFDiaryOfBusyHours[COOCHIE] H_8AM 
		SET_BIT iGFDiaryOfBusyHours[COOCHIE] H_10AM
		SET_BIT iGFDiaryOfBusyHours[COOCHIE] H_NOON
		SET_BIT iGFDiaryOfBusyHours[COOCHIE] H_2PM

//--- MECHANIC GIRL (MICHELLE)
	iGFSelfRespect[MICHELLE] = 45 
	iGFLikesPlayer[MICHELLE] = 0 	
	iGFDesiredSexAppeal[MICHELLE] = 60
	iGFRespectGiven[MICHELLE] = 500

	iGFLikesOnDate[MICHELLE] = 0
		SET_BIT iGFLikesOnDate[MICHELLE] LIKES_BARS     
		SET_BIT iGFLikesOnDate[MICHELLE] LIKES_STUNTS
		SET_BIT iGFLikesOnDate[MICHELLE] LIKES_TO_GO_FAST
		SET_BIT iGFLikesOnDate[MICHELLE] LIKES_GANG_ZONES
		SET_BIT iGFLikesOnDate[MICHELLE] LIKES_PARK_BEACH_ZONES
		SET_BIT iGFLikesOnDate[MICHELLE] LIKES_ENTERTAINMENT_ZONES
		SET_BIT iGFLikesOnDate[MICHELLE] LIKES_SNOGGING_IN_PUBLIC	
		SET_BIT iGFLikesOnDate[MICHELLE] LIKES_TO_DRIVE			  

	iGFLikesPlayerTraits[MICHELLE] = 0
		SET_BIT iGFLikesPlayerTraits[MICHELLE] OBESE

	iGFDiaryOfBusyHours[MICHELLE] = 0
		SET_BIT iGFDiaryOfBusyHours[MICHELLE] H_NOON
		SET_BIT iGFDiaryOfBusyHours[MICHELLE] H_2PM
		SET_BIT iGFDiaryOfBusyHours[MICHELLE] H_4PM
		SET_BIT iGFDiaryOfBusyHours[MICHELLE] H_6PM
		SET_BIT iGFDiaryOfBusyHours[MICHELLE] H_8PM 
		SET_BIT iGFDiaryOfBusyHours[MICHELLE] H_10PM

//--- GUN GIRL (KYLIE)
	iGFSelfRespect[KYLIE] = 70 
	iGFLikesPlayer[KYLIE] = 0 	
	iGFDesiredSexAppeal[KYLIE] = 70
	iGFRespectGiven[KYLIE] = 700

	iGFLikesOnDate[KYLIE] = 0
		SET_BIT iGFLikesOnDate[KYLIE] LIKES_SWANK_PLACES     
		SET_BIT iGFLikesOnDate[KYLIE] LIKES_PARKING_ROMANTIC
		SET_BIT iGFLikesOnDate[KYLIE] LIKES_TO_CRUISE
		SET_BIT iGFLikesOnDate[KYLIE] LIKES_RICH_ZONES
		SET_BIT iGFLikesOnDate[KYLIE] LIKES_DESERT_COUNTRY_ZONES				  
		SET_BIT iGFLikesOnDate[KYLIE] LIKES_SEX_IN_PUBLIC

	iGFLikesPlayerTraits[KYLIE] = 0
		SET_BIT iGFLikesPlayerTraits[KYLIE] NORMAL

	iGFDiaryOfBusyHours[KYLIE] = 0
		SET_BIT iGFDiaryOfBusyHours[KYLIE] H_2AM
		SET_BIT iGFDiaryOfBusyHours[KYLIE] H_4AM
		SET_BIT iGFDiaryOfBusyHours[KYLIE] H_6AM
		SET_BIT iGFDiaryOfBusyHours[KYLIE] H_NOON

//--- COP GIRL (BARBARA)
	iGFSelfRespect[BARBARA] = 60 
	iGFLikesPlayer[BARBARA] = 0 	
	iGFDesiredSexAppeal[BARBARA] = 45
	iGFRespectGiven[BARBARA] = 400

	iGFLikesOnDate[BARBARA] = 0
		SET_BIT iGFLikesOnDate[BARBARA] LIKES_DINERS     
		SET_BIT iGFLikesOnDate[BARBARA] LIKES_TO_CRUISE
		SET_BIT iGFLikesOnDate[BARBARA] LIKES_DESERT_COUNTRY_ZONES
		SET_BIT iGFLikesOnDate[BARBARA] LIKES_ENTERTAINMENT_ZONES

	iGFLikesPlayerTraits[BARBARA] = 0
		SET_BIT iGFLikesPlayerTraits[BARBARA] OBESE

	iGFDiaryOfBusyHours[BARBARA] = 0
		SET_BIT iGFDiaryOfBusyHours[BARBARA] H_6AM
		SET_BIT iGFDiaryOfBusyHours[BARBARA] H_8AM 
		SET_BIT iGFDiaryOfBusyHours[BARBARA] H_10AM
		SET_BIT iGFDiaryOfBusyHours[BARBARA] H_NOON
		SET_BIT iGFDiaryOfBusyHours[BARBARA] H_2PM

//--- NURSE GIRL (SUZIE)
	iGFSelfRespect[SUZIE] = 50 
	iGFLikesPlayer[SUZIE] = 0 	
	iGFDesiredSexAppeal[SUZIE] = 50
	iGFRespectGiven[SUZIE] = 300

	iGFLikesOnDate[SUZIE] = 0
		SET_BIT iGFLikesOnDate[SUZIE] 	LIKES_DINERS
		SET_BIT iGFLikesOnDate[SUZIE] LIKES_TO_CRUISE
		SET_BIT iGFLikesOnDate[SUZIE] LIKES_ENTERTAINMENT_ZONES
		SET_BIT iGFLikesOnDate[SUZIE] LIKES_SHOPPING_ZONES
		SET_BIT iGFLikesOnDate[SUZIE] LIKES_PARK_BEACH_ZONES
		SET_BIT iGFLikesOnDate[SUZIE] LIKES_TO_CAUSE_ACCIDENTS_KILL_PEDS

	iGFLikesPlayerTraits[SUZIE] = 0
		SET_BIT iGFLikesPlayerTraits[SUZIE] FIT

	iGFDiaryOfBusyHours[SUZIE] = 0		
		SET_BIT iGFDiaryOfBusyHours[SUZIE] H_2AM
		SET_BIT iGFDiaryOfBusyHours[SUZIE] H_4AM
		SET_BIT iGFDiaryOfBusyHours[SUZIE] H_6AM
		SET_BIT iGFDiaryOfBusyHours[SUZIE] H_8AM 
		SET_BIT iGFDiaryOfBusyHours[SUZIE] H_10AM
		SET_BIT iGFDiaryOfBusyHours[SUZIE] H_MIDNIGHT

//--- CROUPIER (MILLIE)
	iGFSelfRespect[MILLIE] = 45
	iGFLikesPlayer[MILLIE] = 20 	
	iGFDesiredSexAppeal[MILLIE] = 60
	iGFRespectGiven[MILLIE] = 400

	iGFLikesOnDate[MILLIE] = 0
		SET_BIT iGFLikesOnDate[MILLIE] LIKES_SWANK_PLACES     
		SET_BIT iGFLikesOnDate[MILLIE] LIKES_BARS
		SET_BIT iGFLikesOnDate[MILLIE] LIKES_TO_CRUISE
		SET_BIT iGFLikesOnDate[MILLIE] LIKES_PARKING_ROMANTIC
		SET_BIT iGFLikesOnDate[MILLIE] LIKES_RICH_ZONES
		SET_BIT iGFLikesOnDate[MILLIE] LIKES_SHOPPING_ZONES
		SET_BIT iGFLikesOnDate[MILLIE] LIKES_ENTERTAINMENT_ZONES
		SET_BIT iGFLikesOnDate[MILLIE] LIKES_SNOGGING_IN_PUBLIC
		SET_BIT iGFLikesOnDate[MILLIE] LIKES_KINKY_SEX

	iGFLikesPlayerTraits[MILLIE] = 0
		SET_BIT iGFLikesPlayerTraits[MILLIE] FIT

	iGFDiaryOfBusyHours[MILLIE] = 0
		SET_BIT iGFDiaryOfBusyHours[MILLIE] H_10PM
		SET_BIT iGFDiaryOfBusyHours[MILLIE] H_MIDNIGHT		
		SET_BIT iGFDiaryOfBusyHours[MILLIE] H_2AM		
		SET_BIT iGFDiaryOfBusyHours[MILLIE] H_4AM
		SET_BIT iGFDiaryOfBusyHours[MILLIE] H_6AM
		SET_BIT iGFDiaryOfBusyHours[MILLIE] H_8AM 
		SET_BIT iGFDiaryOfBusyHours[MILLIE] H_10AM

//-------------------------------------------GF DOORS---------------------------------------------
VAR_INT iGFdoor[7]

CREATE_OBJECT_NO_OFFSET Gen_doorEXT03 2401.75 -1714.477 13.125 iGFdoor[COOCHIE]
DONT_REMOVE_OBJECT iGFdoor[COOCHIE]
SET_OBJECT_COLLISION_DAMAGE_EFFECT iGFdoor[COOCHIE] FALSE
SET_OBJECT_PROOFS iGFdoor[COOCHIE] TRUE TRUE TRUE TRUE TRUE


CREATE_OBJECT_NO_OFFSET Gen_doorEXT07 -2574.495 1153.023 54.669 iGFdoor[SUZIE]
SET_OBJECT_HEADING iGFdoor[SUZIE] -19.444
DONT_REMOVE_OBJECT iGFdoor[SUZIE]
SET_OBJECT_COLLISION_DAMAGE_EFFECT iGFdoor[SUZIE] FALSE
SET_OBJECT_PROOFS iGFdoor[SUZIE] TRUE TRUE TRUE TRUE TRUE

CREATE_OBJECT_NO_OFFSET Gen_doorSHOP02 -1800.706 1201.041 24.12 iGFdoor[MICHELLE]	  
DONT_REMOVE_OBJECT iGFdoor[MICHELLE]
SET_OBJECT_COLLISION_DAMAGE_EFFECT iGFdoor[MICHELLE] FALSE
SET_OBJECT_PROOFS iGFdoor[MICHELLE] TRUE TRUE TRUE TRUE TRUE

CREATE_OBJECT_NO_OFFSET Gen_doorEXT04 -383.46 -1439.64 25.33 iGFdoor[KYLIE]
SET_OBJECT_HEADING iGFdoor[KYLIE] 90.0
DONT_REMOVE_OBJECT iGFdoor[KYLIE]
SET_OBJECT_COLLISION_DAMAGE_EFFECT iGFdoor[KYLIE] FALSE
SET_OBJECT_PROOFS iGFdoor[KYLIE] TRUE TRUE TRUE TRUE TRUE

CREATE_OBJECT_NO_OFFSET Gen_doorSHOP3 -1390.79 2639.33 54.973 iGFdoor[BARBARA]
DONT_REMOVE_OBJECT iGFdoor[BARBARA]
SET_OBJECT_COLLISION_DAMAGE_EFFECT iGFdoor[BARBARA] FALSE
SET_OBJECT_PROOFS iGFdoor[BARBARA] TRUE TRUE TRUE TRUE TRUE

CREATE_OBJECT_NO_OFFSET Gen_doorEXT03 2038.036 2721.37 10.53 iGFdoor[MILLIE]
SET_OBJECT_HEADING iGFdoor[MILLIE] -180.0
DONT_REMOVE_OBJECT iGFdoor[MILLIE]
SET_OBJECT_COLLISION_DAMAGE_EFFECT iGFdoor[MILLIE] FALSE
SET_OBJECT_PROOFS iGFdoor[MILLIE] TRUE TRUE TRUE TRUE TRUE

CREATE_OBJECT_NO_OFFSET CUNTGIRLDOOR -371.4 -1429.42 26.47 iGFdoor[GF_BARN_DOOR]
DONT_REMOVE_OBJECT iGFdoor[GF_BARN_DOOR]
SET_OBJECT_COLLISION_DAMAGE_EFFECT iGFdoor[GF_BARN_DOOR] FALSE
SET_OBJECT_PROOFS iGFdoor[GF_BARN_DOOR] TRUE TRUE TRUE TRUE TRUE

//------------------------------------------------------------------------------------------------
	//--- Flag here what type of sex version to run
	//	 	0 = ALL SEX IS IN
	//		1 = NO SEX 

	iCensoredVersion = 1

	//--- Launch the memory resident that handles GFs. 
	START_NEW_SCRIPT GF_Dating_Agent 

MISSION_END


/*****************************************************************************************************************************************
************************************************					**********************************************************************
************************************************    DATING AGENT	**********************************************************************
************************************************					**********************************************************************
*****************************************************************************************************************************************/
/* TO DO ON THIS SCRIPT:
------------------------
	- Optimisations
*/
{
GF_Dating_Agent:	  
    SCRIPT_NAME GFAGNT
    
    /*--- Dating Agent Structures--------
		see GirlFriend Stats Map.txt
	-------------------------------------*/
	
    LVAR_INT iAgentState iSubStateStatus iCurrentGF iTemp iTemp2 iHours iMinutes iDay iSixFrameCounter iTempStat 
    LVAR_FLOAT fOriginX fOriginY fOriginZ fOriginH


	//--- Init the vars
	iGFTimeStep = 0
	iTemp = 0
	iTemp2 = 0
	iAgentFlags = 0
	iAgentState = GF_IDLE_TRY_TO_LOCATE_PLAYER 
	iSubStateStatus = 0
	$GF_txtConsoleCheck = NIL
	iPhoneState = MOBILE_INACTIVE
	iGFidx = -1
	iSixFrameCounter = -1
	iDay = 1 // there is no day zero!

   	//--- Fudge
	IF iTemp > 0
	   CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 iCurrentGF
	   ADD_BLIP_FOR_COORD 0.0 0.0 0.0 iGFHomeBlips[0]
	   ADD_BLIP_FOR_COORD 0.0 0.0 0.0 iGFHomeBlips[1]
	   ADD_BLIP_FOR_COORD 0.0 0.0 0.0 iGFHomeBlips[2]
	   ADD_BLIP_FOR_COORD 0.0 0.0 0.0 iGFHomeBlips[3]
	   ADD_BLIP_FOR_COORD 0.0 0.0 0.0 iGFHomeBlips[4]
	   ADD_BLIP_FOR_COORD 0.0 0.0 0.0 iGFHomeBlips[5]
	   CREATE_CAR_GENERATOR 0.0 0.0 0.0 0.0 0 0 0 0 0 0 0 0 iGFLockedCarGenerator[0] 
	   CREATE_CAR_GENERATOR 0.0 0.0 0.0 0.0 0 0 0 0 0 0 0 0 iGFUnlockedCarGenerator[0] 
	ENDIF

//--- Main Loop
GF_Dating_Agent_Main:
	
	WAIT iGFTimeStep

//--- Calls to the debug subroutines if needed - otherwise comment	
// 	GOSUB GF_Dating_Agent_Debug
//	GOSUB GF_Dating_Agent_Console_Commands

	//--- Alive Checks for player and mission checks
 	IF IS_PLAYER_PLAYING PLAYER1
	AND NOT IS_CHAR_DEAD scplayer
	AND flag_player_on_mission = 0
		//-- Player NOT on mission, remove the clean up flag
		CLEAR_BIT iAgentFlags MISSION_CLEANUP_DONE
		//--- Go into the appopriate Agent State
		GOSUB GF_Dating_Agent_DoCurrentState
	ELSE
		IF NOT IS_BIT_SET iAgentFlags MISSION_CLEANUP_DONE			
			GOSUB GF_Dating_Agent_MissionCleanUp
			SET_BIT iAgentFlags MISSION_CLEANUP_DONE
		ENDIF
	ENDIF
		
GOTO GF_Dating_Agent_Main 
//--- End Main Loop

/*****************************************************************************
							GF_Dating_Agent STATES
******************************************************************************/
GF_Dating_Agent_DoCurrentState:
  
	SWITCH iAgentState	   	
   		CASE GF_IDLE_TRY_TO_LOCATE_PLAYER //---STATE 0: WAIT TO START DATE
			GOSUB GF_Dating_Agent_State0
		BREAK
   		CASE GF_LAUNCH_DATE_OR_SEX //---STATE 1: LAUNCH A DATE OR SEX 
			GOSUB GF_Dating_Agent_State1
		BREAK
   		CASE GF_WRAP_UP_AND_UPDATE //---STATE 2: WRAP UP AND STAT UPDATES 
			GOSUB GF_Dating_Agent_State2
		BREAK
   		CASE GF_MEETING_WAIT //---STATE3: MEETING WAIT STATE
			GOSUB GF_Dating_Agent_State3
		BREAK
   		CASE GF_START_DATE_WAIT //---STATE4: START DATE WAIT STATE
			GOSUB GF_Dating_Agent_State4
		BREAK
	ENDSWITCH
RETURN
/********************************************
		STATE 0: WAIT TO START DATE 
********************************************/
GF_Dating_Agent_State0:  
	//--- Get the index of the girlfriend we are going to handle this frame
	GOSUB GF_Dating_Agent_GetGFidxThisFrame
	//--- Check that the girl is active
	IF IS_BIT_SET iActiveGF iGFidx
		IF IS_BIT_SET iAgentFlags KEEP_THIS_IDX // If we have locked this girl already...
			iTemp = 1 // City ALWAYS matches with this girl
		ELSE
			//--- Check the current city
			iTemp = 0
			GET_CITY_PLAYER_IS_IN PLAYER1 iTemp2				  
			SWITCH iGFidx
				CASE COOCHIE  
					IF iTemp2 = LEVEL_LOSANGELES
						iTemp = 1
					ENDIF 
				BREAK
				CASE BARBARA
					IF iTemp2 = LEVEL_GENERIC
						iTemp = 1
					ENDIF 
				BREAK
				CASE KYLIE
					IF iTemp2 = LEVEL_GENERIC
						iTemp = 1
					ENDIF 
				BREAK
				CASE MICHELLE
					IF iTemp2 = LEVEL_SANFRANCISCO
						iTemp = 1
					ENDIF 
				BREAK
				CASE SUZIE
					IF iTemp2 = LEVEL_SANFRANCISCO
						iTemp = 1
					ENDIF 
				BREAK					
				CASE MILLIE
					IF iTemp2 = LEVEL_LASVEGAS
						iTemp = 1
					ENDIF 
				BREAK
			ENDSWITCH
		ENDIF  
		IF iTemp = 1 // City matches with girl
			//--- Check if the Girlfriend Blips and Bonuses are ON as required
			GOSUB GF_Dating_Agent_CheckStatusOfActiveBlipsAndBonuses
			//--- Check if player is in zone of the active girl
			GOSUB GF_Dating_Agent_MatchCurrentZoneWithGFZone // Returns iTemp > 0 if active GF, and the matching iGFidx		
			IF iTemp > 0 // Player is around the zone of an active GF
				//--- Speed up the agent's time step a little bit 
				iGFTimeStep = GF_TIME_STEP_MEDIUM
				//--- Check if we have not aquired this GF at this very moment
				IF NOT IS_BIT_SET iDateReport MEETING_IN_PROGRESS
					//--- Get origin coordinates for curent girlfriend
					GOSUB GF_Dating_Agent_GetOrigin_X_Y_Z_H // Reads iGFidx, Returns in fOriginX,Y,Z,Heading
					//--- Determine if this girl is at home or not, according to her diary
					GOSUB GF_Dating_Agent_CheckGirlsDiary
					IF iTemp > 0 // She is in						
						//--- Set the agent time step at its fastest speed - MUST RENDER THE LOCATE 
						iGFTimeStep = GF_TIME_STEP_FAST
						GOSUB GF_Dating_Agent_CreateGFCar
						//--- Mark the GF available for 2 Player games
						SET_BIT iGFAvailableFor2Player iGFidx						
						//--- Player detected at the flat
		 				iAgentState = GF_START_DATE_WAIT 
		 				iSubStateStatus = 0
		 				RETURN
					ELSE // she is not home
						//--- Mark the GF as not ready for 2 player games
						CLEAR_BIT iGFAvailableFor2Player iGFidx
						GOSUB GF_Dating_Agent_RemoveGFCar
						iGFTimeStep = GF_TIME_STEP_MEDIUM
						IF LOCATE_STOPPED_CHAR_ANY_MEANS_3D scplayer fOriginX fOriginY fOriginZ 5.0 5.0 3.0 FALSE
							//--- Player detected at the flat - display HELP
			 				IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
			 				AND NOT IS_MESSAGE_BEING_DISPLAYED 
			 					PRINT_HELP GF_0048 // Your girlfriend is not at home. Try again later
							ENDIF
			 				RETURN
						ENDIF	
					ENDIF
				ENDIF
			ELSE 
				//--- Player is not around the area of the girl				
				GOSUB GF_Dating_Agent_CheckPhoneState // Mobile Phone	
			ENDIF
		ELSE
			//--- City check failed, return			
			RETURN
		ENDIF 
	ELSE // This is an inactive girlfriend
		//--- Check if the Girlfriend Blips and Bonuses are OFF as required
		GOSUB GF_Dating_Agent_CheckStatusOfInactiveBlipsAndBonuses
		//--- Check if player is in the 'meeting' zone of an inactive girl
		IF NOT IS_BIT_SET iDateReport MEETING_IN_PROGRESS // If the meeting script is not running already
			GOSUB GF_Dating_Agent_MatchCurrentZoneWithMeetZone // Returns iTemp > 0 if match, and the matching iGFidx		
			IF iTemp > 0					
				//--- Speed up the agent's time step a little bit 
				iGFTimeStep = GF_TIME_STEP_MEDIUM 
				//--- Special case for KYLIE who is not available until after this mission
				IF iGFidx = KYLIE  
				AND flag_la1fin1_mission_counter < 2
					MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED GF_Meeting.sc
					RETURN
				ENDIF
				//--- Check if girl is allowed to be created in the world or not
				IF NOT iGFLikesPlayer[iGFidx] = GF_HATES_PLAYER
				AND NOT iGFLikesPlayer[iGFidx] = GF_IS_DEAD
					//--- Check if we must skip a day
					IF IS_BIT_SET iGFDiaryOfBusyHours[iGFidx] NEXT_FREE_DAY 
						//--- Verify that this is the next free day in her diary
						GET_CURRENT_DAY_OF_WEEK iTemp		
						iTemp += H_10PM // pick the right bit to find the day of the week in the diary
						IF IS_BIT_SET iGFDiaryOfBusyHours[iGFidx] iTemp // if this is the right day
							//--- Clean the "day to skip" and the "skip a day" bits off her diary 
							CLEAR_BIT iGFDiaryOfBusyHours[iGFidx] NEXT_FREE_DAY
							CLEAR_BIT iGFDiaryOfBusyHours[iGFidx] iTemp
							//--- This IS the next day
							iTemp = 1
						ELSE	
							//--- All ok, tomorrow is her free day, so return false
							iTemp = -1
							RETURN
						ENDIF
					ELSE
						//--- No need to check for free day
						iTemp = 1
					ENDIF
					//--- If she is supposed to be there, get her co-ordinates
					IF iTemp = 1				  			
						GOSUB GF_Dating_Agent_GetMeetingOrigin_X_Y_Z_H
						//--- Start a meeting with this girl - gives the opportunity to activate her
						GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT GF_Meeting.sc iTemp 
						IF iTemp = 0
							STREAM_SCRIPT GF_Meeting.sc
							IF HAS_STREAMED_SCRIPT_LOADED GF_Meeting.sc  
								SET_BIT iDateReport MEETING_IN_PROGRESS
								START_NEW_STREAMED_SCRIPT GF_Meeting.sc iGFidx fOriginX fOriginY fOriginZ fOriginH	
								//--- Speed up the agent's time step to its maximum speed
								iGFTimeStep = GF_TIME_STEP_FAST
								iAgentState = GF_MEETING_WAIT 
								iSubStateStatus = 0
							ENDIF
						ENDIF				
					ENDIF
				ENDIF
			ELSE
				//--- Slow down the agent's time step
				iGFTimeStep = GF_TIME_STEP_SLOW
				//--- Player has moved away from the meeting zone
				MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED GF_Meeting.sc 
			ENDIF			
		ENDIF
	ENDIF
RETURN
/********************************************
		STATE 1: LAUNCH A DATE OR SEX 
********************************************/
GF_Dating_Agent_State1: 

	SWITCH iSubStateStatus	 				

	CASE 0		
		//--- Check the mobile phone - if player has received a call from this GF
		IF iCaller = iGFidx
			//--- RESET 
			GOSUB GF_Dating_Agent_ResetAppointment
			iPhoneState = MOBILE_INACTIVE
		ENDIF
		
		CLEAR_BIT iAgentFlags iGFidx // Clear the appointment bit anyway.
				
		//------CREATE THE GF in the game world:		
		GOSUB GF_Dating_Agent_CreateAndInitGF  // Pass the IDX of the GF, returns iCurrentGF as ped pointer

		//--- Disband the current player's group in case he has gang members with him
		IF IS_PLAYER_PLAYING PLAYER1
			REMOVE_GROUP players_group		
			SET_PLAYER_GROUP_RECRUITMENT PLAYER1 FALSE
			GET_RID_OF_PLAYER_PROSTITUTE
		ENDIF

		//--- Clear the report for this date (this should be clean already, but just to make sure...)
		IF NOT IS_BIT_SET iActiveGF GF_CHEAT_MODE_ON // If there are no keyboard cheats active
			iDateReport = 0 
		ENDIF

		//--- Check if the player is in GIMP SUIT here, and if the GF likes kinky
		GOSUB GF_Dating_Agent_IsPlayerInGimpSuit // Returns iTemp = 1 if player in gimp suit, 0 otherwise
		IF iTemp = 1 
		AND IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_KINKY_SEX
			SET_BIT iDateReport KINKY_SEX // Push the kinky sex as date, this starts the cut of her leaving house
		ELSE
			//--- On the first few dates, the girl always wants to be taken out for dinner
			IF iGFLikesPlayer[iGFidx] <= GF_LIKES_PLAYER_STAGE1
				SET_BIT iDateReport EAT_OUT				
			ELSE 
				//--- Generate a random date								
				GOSUB GF_Dating_Agent_CreateRandomDate // Returns date number in iTemp. Valid dates: 11, 12, 13 
				SET_BIT iDateReport iTemp //Push the date type in the bitfield
			ENDIF
		ENDIF
		//--- Start the date script						
		STREAM_SCRIPT GF_Date.sc
		++iSubStateStatus // Wait for the date to end, i.e. bit 1 in the report is clear
	BREAK

	CASE 1		 
		IF HAS_STREAMED_SCRIPT_LOADED GF_Date.sc
			SET_BIT iDateReport DATE_IN_PROGRESS // Mark Date as In Progress
			CLEAR_BIT iGFAvailableFor2Player iGFidx // Mark GF as NOT available for 2 player games
			START_NEW_STREAMED_SCRIPT GF_Date.sc iCurrentGF //Start the date, passing the GF model
			++iSubStateStatus // Wait for the date to end, i.e. bit 1 in the report is clear
		ENDIF	   
	BREAK

	CASE 2
		//--- Date Has ended
		IF NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS // Date In Progress
			//--- Check if player has agreed to sex or has declined
			IF IS_BIT_SET iDateReport PLAYER_AGREES_TO_SEX 			
				//--- Player did agree to Sex 
				IF NOT IS_BIT_SET iDateReport SEX_IN_PROGRESS  
					SET_PLAYER_CONTROL player1 OFF
					//--- Remove the instance, it is no longer needed on screen.
					GOSUB GF_Dating_Agent_RemoveGF 
					STREAM_SCRIPT GF_Sex.sc
					++iSubStateStatus					
				ENDIF
			ELSE
				//--- Player refused sex
				DO_FADE 1000 FADE_IN
				iAgentState = GF_WRAP_UP_AND_UPDATE
				iSubStateStatus = 0
			ENDIF			
		ELSE
			GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT GF_Date.sc iTemp
			IF iTemp =0
				CLEAR_BIT iDateReport DATE_IN_PROGRESS 
			ENDIF
		ENDIF
	BREAK

	CASE 3
		IF HAS_STREAMED_SCRIPT_LOADED GF_Sex.sc
			//--- Retrieve the stuff we need to pass to the sex script
			GET_AREA_VISIBLE iTemp 
			GOSUB GF_Dating_Agent_GetOrigin_X_Y_Z_H
			//--- See what type of sex we shoud trigger
			IF IS_BIT_SET iDateReport KINKY_SEX						
				START_NEW_STREAMED_SCRIPT GF_Sex.sc iGFidx TRUE iTemp fOriginX fOriginY fOriginZ fOriginH //Pass the GF, spanking, current area, home outside XYZ and final heading
			ELSE 
				START_NEW_STREAMED_SCRIPT GF_Sex.sc	iGFidx FALSE iTemp fOriginX fOriginY fOriginZ fOriginH //Pass the GF, spanking, current area, home outside XYZ and final heading
			ENDIF
			SET_BIT iDateReport SEX_IN_PROGRESS	
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 4
		//--- Sex has ended
		IF NOT IS_BIT_SET iDateReport SEX_IN_PROGRESS	
			iAgentState = GF_WRAP_UP_AND_UPDATE
			iSubStateStatus = 0
		ELSE
			GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT GF_Sex.sc iTemp
			IF iTemp =0
				CLEAR_BIT iDateReport SEX_IN_PROGRESS 
			ENDIF
		ENDIF
	BREAK

	ENDSWITCH

RETURN
/********************************************
		STATE 2: WRAP UP AND STAT UPDATES
********************************************/
GF_Dating_Agent_State2:  
	SWITCH iSubStateStatus	 				

	CASE 0
	
		CLEAR_BIT iAgentFlags iGFidx // APPOINTMENT_ON for current gf

		//--- Do all the basic housekeeping stuff on the instanced girlfriend model
		GOSUB GF_Dating_Agent_RemoveGF		

		//--- Check if the girlfriend is DEAD
		IF iGFLikesPlayer[iGFidx] = GF_IS_DEAD
			CLEAR_HELP
			PRINT_HELP GF_0039 //Your girlfriend is dead.				
			CLEAR_BIT iActiveGF iGFidx // remove the girl			
		ELSE			
			//--- GF is alive and well, update her stats			
			GOSUB GF_Dating_Agent_UpdateGFStats
			//--- Print unlocked car generator help
			IF iGFLikesPlayer[iGFidx] >= GF_LIKES_PLAYER_STAGE2			
				GOSUB GF_Dating_Agent_PrintUnlockedCarInfo				
			ENDIF
		ENDIF
		++iSubStateStatus
	BREAK

	CASE 1
		//--- Check if the girl has dumped the player
		IF iGFLikesPlayer[iGFidx] = GF_HATES_PLAYER
		OR iGFLikesPlayer[iGFidx] = GF_IS_DEAD 
			GOSUB GF_Dating_Agent_DeleteStats
			iAgentState = GF_IDLE_TRY_TO_LOCATE_PLAYER 
			iSubStateStatus = 0
		ELSE
			//--- Check that the player has moved away from the GF origin (i.e. where most dates end)
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer fOriginX fOriginY fOriginZ 10.0 10.0 10.0 FALSE
				iAgentState = GF_IDLE_TRY_TO_LOCATE_PLAYER
				iSubStateStatus = 0
			ENDIF
		ENDIF
	BREAK

	ENDSWITCH

RETURN
/********************************************
		STATE 3: MEETING WAIT STATE 
********************************************/
GF_Dating_Agent_State3:  
	//--- Meeting Script Has ended
	IF NOT IS_BIT_SET iDateReport MEETING_IN_PROGRESS // MEETING In Progress				
		GOSUB GF_Dating_Agent_GetMeetingOrigin_X_Y_Z_H
		IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer fOriginX fOriginY fOriginZ 65.0 65.0 5.0 FALSE			
			IF IS_BIT_SET iDateReport MEET_TOMORROW
				GET_CURRENT_DAY_OF_WEEK iDay
				++iDay
				IF iDay > 7
					iDay = 1
				ENDIF
			ENDIF
			iAgentState = GF_IDLE_TRY_TO_LOCATE_PLAYER
			iSubStateStatus = 0				
		ENDIF
	ELSE
		GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT GF_Meeting.sc iTemp
		IF iTemp =0
			CLEAR_BIT iDateReport MEETING_IN_PROGRESS 
		ENDIF
	ENDIF
RETURN
/********************************************
		STATE 4: START DATE WAIT STATE 
********************************************/
GF_Dating_Agent_State4:  
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer fOriginX fOriginY fOriginZ 75.0 75.0 10.0 FALSE 
		GOSUB GF_Dating_Agent_CheckGirlsDiary
		IF iTemp > 0 // She is in				
			IF LOCATE_STOPPED_CHAR_ANY_MEANS_3D scplayer fOriginX fOriginY fOriginZ 1.5 1.5 1.5 TRUE
				IF NOT IS_BIT_SET iAgentFlags MOBILE_CALL_SCRIPT_RUNNING
					SET_PLAYER_CONTROL player1 OFF
					iAgentState = GF_LAUNCH_DATE_OR_SEX
					iSubStateStatus = 0
					RETURN
				ENDIF
			ENDIF
		ELSE
			//---She is no longer at home
			iAgentState = GF_IDLE_TRY_TO_LOCATE_PLAYER
			iSubStateStatus = 0				
		ENDIF
	ELSE
		//--- Player has left the area
		iAgentState = GF_IDLE_TRY_TO_LOCATE_PLAYER
		iSubStateStatus = 0				
	ENDIF
RETURN
/*****************************************************************************
							GF_Dating_Agent SUBROUTINES
******************************************************************************/

/********************************************
 CHECK STATUS OF ACTIVE BONUSES AND BLIPS
********************************************/
GF_Dating_Agent_CheckStatusOfActiveBlipsAndBonuses:
	//--- check if the ACTIVE girl we are doing this frame needs a blip
	IF NOT DOES_BLIP_EXIST iGFHomeBlips[iGFidx]
		GOSUB GF_Dating_Agent_GetOrigin_X_Y_Z_H
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD fOriginX fOriginY fOriginZ RADAR_SPRITE_GIRLFRIEND iGFHomeBlips[iGFidx]
		//--- The blip can also be considered a 'flag' to see if this girl needs to be counted		
		INCREMENT_INT_STAT CURRENT_GIRLFRIENDS 1 // current number, always updated 
		INCREMENT_INT_STAT GIRLS_DATED 1 // total number, up to 6
		//--- Set Girlfriend Respect
		GET_INT_STAT RESPECT_GIRLFRIEND iTemp
		iTemp += iGFRespectGiven[iGFidx]
		IF iTemp > 1000 
			iTemp = 1000 
		ENDIF
		SET_INT_STAT RESPECT_GIRLFRIEND iTemp		
	ENDIF
	//--- Girl specific checks
	SWITCH iGFidx		
		CASE COOCHIE
			//--- Give player clothes as gift
			IF iGFLikesPlayer[iGFidx] >= GF_LIKES_PLAYER_STAGE3
			AND flag_got_pimp_clothes = 0
				IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED 
					PRINT_HELP GF_H006 // your girlfriend has bought you a gift
					flag_got_pimp_clothes = 1
				ENDIF
			ENDIF
		BREAK
		CASE MICHELLE
			//--- Give player clothes as gift
			IF iGFLikesPlayer[iGFidx] >= GF_LIKES_PLAYER_STAGE3
			AND flag_got_mechanic_clothes = 0
				IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED 
					PRINT_HELP GF_H006 // your girlfriend has bought you a gift
					flag_got_mechanic_clothes = 1
				ENDIF
			ENDIF
			//--- check if this girl needs her bonus activated
			IF NOT IS_BIT_SET iActiveGF MICHELLE_BONUS_ACTIVE
				CHANGE_GARAGE_TYPE MICHDR GARAGE_RESPRAY
				SET_GARAGE_RESPRAY_FREE	MICHDR TRUE
				SET_BIT iActiveGF MICHELLE_BONUS_ACTIVE
			ENDIF			
		BREAK
		CASE KYLIE
			//--- Give player clothes as gift
			IF iGFLikesPlayer[iGFidx] >= GF_LIKES_PLAYER_STAGE3
			AND flag_player_got_country_clothes = 0
				IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED 
					PRINT_HELP GF_H006 // your girlfriend has bought you a gift
					flag_player_got_country_clothes = 1
				ENDIF
			ENDIF
			//--- check if this girl needs her bonus activated
			IF NOT IS_BIT_SET iActiveGF KYLIE_BONUS_ACTIVE
				SET_OBJECT_VISIBLE iGFdoor[GF_BARN_DOOR] FALSE 
				SET_OBJECT_COLLISION iGFdoor[GF_BARN_DOOR] FALSE 
				CREATE_PICKUP MOLOTOV PICKUP_ON_STREET_SLOW -366.2235 -1429.0878 25.5 iGFBonusPickupID[0]
				CREATE_PICKUP CHNSAW PICKUP_ON_STREET_SLOW -365.7906 -1425.2526 25.5 iGFBonusPickupID[1]
				CREATE_PICKUP_WITH_AMMO  COLT45 PICKUP_ON_STREET_SLOW 100 -365.4774 -1422.4015 25.5 iGFBonusPickupID[2]
				CREATE_PICKUP_WITH_AMMO FLAME PICKUP_ON_STREET_SLOW 200 -366.0660 -1418.6830 25.5 iGFBonusPickupID[3]
				SET_BIT iActiveGF KYLIE_BONUS_ACTIVE
			ENDIF									
		BREAK
		CASE BARBARA
			//--- Give player clothes as gift
			IF iGFLikesPlayer[iGFidx] >= GF_LIKES_PLAYER_STAGE3
			AND flag_player_got_police_uniform = 0
				IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED 
					PRINT_HELP GF_H006 // your girlfriend has bought you a gift
					flag_player_got_police_uniform = 1
				ENDIF
			ENDIF
			//--- check if this girl needs her bonus activated
			IF NOT IS_BIT_SET iActiveGF BARBARA_BONUS_ACTIVE
				SET_EXTRA_POLICE_STATION_RESTART_POINT -1379.8428 2635.7395 54.4315 1500.0 170.6194
				SWITCH_ARREST_PENALTIES FALSE
				SET_BIT iActiveGF BARBARA_BONUS_ACTIVE
			ENDIF
		BREAK
		CASE SUZIE
			//--- Give player clothes as gift
			IF iGFLikesPlayer[iGFidx] >= GF_LIKES_PLAYER_STAGE3
			AND flag_got_medic_clothes = 0
				IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED 
					PRINT_HELP GF_H006 // your girlfriend has bought you a gift
					flag_got_medic_clothes = 1
				ENDIF
			ENDIF
			IF NOT IS_BIT_SET iActiveGF SUZIE_BONUS_ACTIVE
				//--- check if this girl needs her bonus activated		
				SET_EXTRA_HOSPITAL_RESTART_POINT -2570.5107 1139.5815 54.8547 1500.0 160.0
				SWITCH_DEATH_PENALTIES FALSE
				SET_BIT iActiveGF SUZIE_BONUS_ACTIVE
			ENDIF
		BREAK
	ENDSWITCH
RETURN
/********************************************
CHECK STATUS OF INACTIVE BONUSES AND BLIPS
********************************************/
GF_Dating_Agent_CheckStatusOfInactiveBlipsAndBonuses:
	
	IF returned_oysters = 50
		//--- Resurrect this girl if dead
		IF iGFLikesPlayer[iGFidx] < 0
		AND NOT iGFidx = COOCHIE 
		AND NOT iGFidx = MILLIE
			iGFLikesPlayer[iGFidx] = 45
		ENDIF
	ENDIF

	IF DOES_BLIP_EXIST iGFHomeBlips[iGFidx]
	   	REMOVE_BLIP iGFHomeBlips[iGFidx]
	   	//--- Delete her stats
	   	GOSUB GF_Dating_Agent_DeleteStats
	   	//--- Remove her car 
		GOSUB GF_Dating_Agent_RemoveGFCar
		//--- Mark the GF as not ready for 2 player games
		CLEAR_BIT iGFAvailableFor2Player iGFidx
		//--- Decrease the counter of current girls
		DECREMENT_INT_STAT CURRENT_GIRLFRIENDS 1 
		//--- Increase the counter of girls dumped
		INCREMENT_INT_STAT GIRLS_DUMPED 1 	
		//--- Decrease Girlfriend Respect
		GET_INT_STAT RESPECT_GIRLFRIEND iTemp
		iTemp -= iGFRespectGiven[iGFidx]
		IF iTemp < 0 
			iTemp = 0 
		ENDIF
		SET_INT_STAT RESPECT_GIRLFRIEND iTemp
	ENDIF		
	//--- calculate the bonus_active bit for this girl
	iTemp = iGFidx + 20 
	IF IS_BIT_SET iActiveGF iTemp
		//--- need to remove this bonus as the girl has gone inactive
		SWITCH iGFidx		
			CASE MICHELLE
				IF NOT IS_CHAR_IN_AREA_3D scplayer -1790.7 1209.0 23.0 -1784.0 1221.5 27.5  FALSE
					SET_GARAGE_RESPRAY_FREE	MICHDR FALSE
					CHANGE_GARAGE_TYPE MICHDR GARAGE_FOR_SCRIPT_TO_OPEN_AND_CLOSE								
					CLOSE_GARAGE MICHDR
					CLEAR_BIT iActiveGF MICHELLE_BONUS_ACTIVE
				ENDIF
			BREAK
			CASE KYLIE
				IF NOT IS_CHAR_IN_AREA_3D scplayer -361.0 -1417.0 23.0 -372.0 -1431.0 30.0 FALSE
					REMOVE_PICKUP iGFBonusPickupID[0]
					REMOVE_PICKUP iGFBonusPickupID[1]
					REMOVE_PICKUP iGFBonusPickupID[2]
					REMOVE_PICKUP iGFBonusPickupID[3]
					SET_OBJECT_VISIBLE iGFdoor[GF_BARN_DOOR] TRUE 
					SET_OBJECT_COLLISION iGFdoor[GF_BARN_DOOR] TRUE 
					CLEAR_BIT iActiveGF KYLIE_BONUS_ACTIVE
				ENDIF
			BREAK
			CASE BARBARA
				SET_EXTRA_POLICE_STATION_RESTART_POINT -1379.8428 2635.7395 54.4315 0.0 170.6194 // clears it
				SWITCH_ARREST_PENALTIES TRUE
				CLEAR_BIT iActiveGF BARBARA_BONUS_ACTIVE
			BREAK
			CASE SUZIE
				SET_EXTRA_HOSPITAL_RESTART_POINT fOriginX fOriginY fOriginZ 0.0 fOriginH // clears it
				SWITCH_DEATH_PENALTIES TRUE
				CLEAR_BIT iActiveGF SUZIE_BONUS_ACTIVE
			BREAK
		ENDSWITCH
	ENDIF
RETURN	
/********************************************
GET CURRENT GIRLFRIEND ORIGIN XYZ & HEADING
********************************************/
GF_Dating_Agent_GetOrigin_X_Y_Z_H:
	SWITCH iGFidx

		CASE COOCHIE 
			fOriginX 	= 2401.9646
			fOriginY 	= -1723.2197
			fOriginZ 	= 12.6005
			fOriginH	= 180.0		    
		BREAK

		CASE MICHELLE
			fOriginX 	= -1799.5 
			fOriginY 	= 1195.5 
			fOriginZ 	= 24.1094 
			fOriginH	= 180.0
		BREAK

		CASE KYLIE
			fOriginX 	= -377.3978 //-398.5166    89.8377
			fOriginY 	= -1438.6919 //-1426.4675 
			fOriginZ 	= 24.7209 //24.7110 
			fOriginH	= 270.0 //90.0
		BREAK

		CASE BARBARA
			fOriginX 	= -1398.1010 
			fOriginY 	= 2636.8730 
			fOriginZ 	= 54.7031 
			fOriginH	= 75.0	
		BREAK

		CASE SUZIE
			fOriginX 	= -2576.8079 
			fOriginY 	= 1144.7438 
			fOriginZ 	= 54.7422
			fOriginH	= 164.0
		BREAK

		CASE MILLIE
			fOriginX = 2035.3619  
			fOriginY = 2732.4106  
			fOriginZ = 9.8203
			fOriginH = 0.0			
		BREAK

	ENDSWITCH
RETURN
/********************************************
	GET GF MEETING ORIGIN XYZ & HEADING
********************************************/
GF_Dating_Agent_GetMeetingOrigin_X_Y_Z_H:
	SWITCH iGFidx

		CASE MICHELLE
			fOriginX 	= -2027.5835 
			fOriginY 	= -118.7022 
			fOriginZ 	= 1034.0 
			fOriginH	= 3.4
		BREAK

		CASE KYLIE
			fOriginX 	= 257.6682 
			fOriginY 	= -154.7475 
			fOriginZ 	= 4.0786 
			fOriginH	= 268.7731
		BREAK

		CASE BARBARA
			fOriginX 	= -1410.0997 
			fOriginY 	= 2648.7307 
			fOriginZ 	= 54.6875
			fOriginH	= 130.0
		BREAK

		CASE SUZIE // commented the co-ords inside the gym, temporarily set her in a park nearby
			fOriginX 	= -2291.7075 //762.9347 
			fOriginY 	= -233.0215 //-23.3127 
			fOriginZ 	= 41.7185 //999.5938 
			fOriginH	= 360.0 //360.0
		BREAK

		DEFAULT
			fOriginX 	= 0.0 
			fOriginY 	= 0.0 
			fOriginZ 	= 0.0 
			fOriginH	= 0.0
		BREAK

	ENDSWITCH
RETURN
/********************************************
	GET GIRLFRIEND INDEX FOR THIS FRAME
********************************************/
GF_Dating_Agent_GetGFidxThisFrame:

	++iSixFrameCounter
	
	IF iSixFrameCounter >= 6
		//--- Loop around
		iSixFrameCounter = 0
	ENDIF  

	IF IS_BIT_SET iAgentFlags KEEP_THIS_IDX 
		IF iSixFrameCounter = iGFidx // we have looped back to the same girl
			CLEAR_BIT iAgentFlags KEEP_THIS_IDX  
		ENDIF
	ELSE
		++iGFidx
		IF iGFidx >= 6
			//--- Loop around
			iGFidx = 0
		ENDIF  
	ENDIF

RETURN
/********************************************
 	MATCH CURRENT ZONE WITH GF ORIGIN ZONE
********************************************/
GF_Dating_Agent_MatchCurrentZoneWithGFZone:

	GOSUB GF_Dating_Agent_GetOrigin_X_Y_Z_H // Reads iGFidx, Returns in fOriginX,Y,Z
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer fOriginX fOriginY fOriginZ 65.0 65.0 5.0 FALSE
		//--- Hold this iGFidx until we leave the area, to prevent locate flickers etc.			
		SET_BIT iAgentFlags KEEP_THIS_IDX
		//--- Return Match for Active GF
		iTemp = 1				
		RETURN
	ENDIF
	iTemp = -1
RETURN
/********************************************
   MATCH CURRENT ZONE WITH GF MEETING ZONE
********************************************/
GF_Dating_Agent_MatchCurrentZoneWithMeetZone:

	GOSUB GF_Dating_Agent_GetMeetingOrigin_X_Y_Z_H // Reads iGFidx, Returns in fOriginX,Y,Z,H (0.0 if no meeting allowed)
	IF NOT fOriginX = 0.0
		//--- Check if player is withing the maximum distance to start the meeting script
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer fOriginX fOriginY fOriginZ 80.0 80.0 10.0 FALSE
			//--- Now check if the player is not too close to actually stream in the GF (to avoid pop)
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer fOriginX fOriginY fOriginZ 8.0 8.0 10.0 FALSE	//7.0
				//--- Hold this iGFidx until we leave the area, to prevent locate flickers etc.			
				SET_BIT iAgentFlags KEEP_THIS_IDX
				//--- Return Match 
				iTemp = 1	
				RETURN
			ENDIF
		ENDIF
	ENDIF
	iTemp = -1
RETURN
/********************************************
		CHECK GIRLFRIEND'S DIARY
********************************************/
GF_Dating_Agent_CheckGirlsDiary:
   	
   	
   	iTemp = -1
   	
   	//--- Check if there is an appointment on AND she has phoned you 		
	IF IS_BIT_SET iAgentFlags MOBILE_CALL_SCRIPT_RUNNING
	AND iCaller = iGFidx
		//--- She is always home if she is on the phone
		iTemp = 1
		RETURN
	ENDIF

	//--- Check if the player has received a call from the girl
	IF IS_BIT_SET iAgentFlags MOBILE_CALL_ANSWERED
	AND iCaller = iGFidx
		//--- She is always home if she is is wai ting for you
		iTemp = 1
		RETURN
	ENDIF

	//--- Check if we must skip a day after a date 
	IF IS_BIT_SET iGFDiaryOfBusyHours[iGFidx] NEXT_FREE_DAY 
		//--- Verify that this is the next free day in her diary
		GET_CURRENT_DAY_OF_WEEK iTemp
		iTemp += H_10PM // pick the right bit to find the day of the week in the diary
		IF IS_BIT_SET iGFDiaryOfBusyHours[iGFidx] iTemp // if this is the right day
			//--- Clean her diary so that only one day can be set at any given time
			CLEAR_BIT iGFDiaryOfBusyHours[iGFidx] NEXT_FREE_DAY
			CLEAR_BIT iGFDiaryOfBusyHours[iGFidx] iTemp
			//--- Carry on with the rest of the diary below...
		ELSE
			//--- All ok, tomorrow is her free day, so return false
			iTemp = -1
			RETURN
		ENDIF
	ENDIF

	//--- Now proceed to check the diary
	GET_TIME_OF_DAY iTemp iTemp2 
		
	// We only consider odd hours, minutes are discarded an overwritten
	iTemp2 = iTemp / 2
	iTemp2 *= 2 // If it's an odd number it will be less than the original due to the integer cast 
 
	IF IS_BIT_SET iGFDiaryOfBusyHours[iGFidx] iTemp2		
		//--- According to her diary, she is not at home
		iTemp = -1 // Store an invalid time in the return var
		RETURN
	ELSE 
		//--- Her diary says she is free...
		iTemp = 1
		RETURN
	ENDIF

RETURN
/********************************************
		CREATE GIRLFRIEND'S CAR
********************************************/
GF_Dating_Agent_CreateGFCar:
	IF iGFLikesPlayer[iGFidx] >= GF_LIKES_PLAYER_STAGE2                        		 
		IF NOT IS_BIT_SET iAgentFlags GF_CAR_GENERATOR_ON  
			SWITCH_CAR_GENERATOR iGFUnlockedCarGenerator[iGFidx] 101
			SET_BIT iAgentFlags GF_CAR_GENERATOR_ON 
		ENDIF
	ELSE
		IF NOT IS_BIT_SET iAgentFlags GF_CAR_GENERATOR_ON 
			SWITCH_CAR_GENERATOR iGFLockedCarGenerator[iGFidx] 101
			SET_BIT iAgentFlags GF_CAR_GENERATOR_ON 
		ENDIF
	ENDIF
RETURN
/********************************************
		REMOVE GIRLFRIEND'S CAR
********************************************/
GF_Dating_Agent_RemoveGFCar:
	IF IS_BIT_SET iAgentFlags GF_CAR_GENERATOR_ON
		SWITCH_CAR_GENERATOR iGFUnlockedCarGenerator[iGFidx] 0
		SWITCH_CAR_GENERATOR iGFLockedCarGenerator[iGFidx] 0
		CLEAR_BIT iAgentFlags GF_CAR_GENERATOR_ON 
	ENDIF
RETURN
/********************************************
		PRINT UNLOCKED CAR INFO
********************************************/
GF_Dating_Agent_PrintUnlockedCarInfo:

	SWITCH iGFidx
		CASE COOCHIE
			IF NOT IS_BIT_SET iActiveGF CAR_COOCHIE_UNLOCKED_HELP
				PRINT_HELP GF_H007 // message to inform player he now has the keys to her car 
				SET_BIT iActiveGF CAR_COOCHIE_UNLOCKED_HELP
			ENDIF 
		BREAK
		CASE MICHELLE
			IF NOT IS_BIT_SET iActiveGF CAR_MICHELLE_UNLOCKED_HELP
				PRINT_HELP GF_H008 // message to inform player he now has the keys to her car 
				SET_BIT iActiveGF CAR_MICHELLE_UNLOCKED_HELP
			ENDIF 
		BREAK
		CASE KYLIE
			IF NOT IS_BIT_SET iActiveGF CAR_KYLIE_UNLOCKED_HELP
				PRINT_HELP GF_H009 // message to inform player he now has the keys to her car 
				SET_BIT iActiveGF CAR_KYLIE_UNLOCKED_HELP
			ENDIF 
		BREAK
		CASE BARBARA
			IF NOT IS_BIT_SET iActiveGF CAR_BARBARA_UNLOCKED_HELP
				PRINT_HELP GF_H010 // message to inform player he now has the keys to her car 
				SET_BIT iActiveGF CAR_BARBARA_UNLOCKED_HELP
			ENDIF 
		BREAK
		CASE SUZIE
			IF NOT IS_BIT_SET iActiveGF CAR_SUZIE_UNLOCKED_HELP
				PRINT_HELP GF_H011 // message to inform player he now has the keys to her car 
				SET_BIT iActiveGF CAR_SUZIE_UNLOCKED_HELP
			ENDIF 
		BREAK
		CASE MILLIE
			IF NOT IS_BIT_SET iActiveGF CAR_MILLIE_UNLOCKED_HELP	
				PRINT_HELP GF_H012 // message to inform player he now has the keys to her car 
				SET_BIT iActiveGF CAR_MILLIE_UNLOCKED_HELP
			ENDIF 
		BREAK
	ENDSWITCH		
RETURN
/*******************************************
			CHECK PHONE STATE
********************************************/
GF_Dating_Agent_CheckPhoneState:

	SWITCH iPhoneState
		
		CASE MOBILE_INACTIVE
			GOSUB GF_Dating_Agent_ResetAppointment
			GOSUB GF_Dating_Agent_CheckIfDumpPlayerPhone
			GOSUB GF_Dating_Agent_CreateRandomAppointment // Sets the time, returns appoinment bit in iAgentFlags 
		BREAK

		CASE TIME_FOR_CALL
			GET_MINUTES_TO_TIME_OF_DAY iHours iMinutes iTemp
			IF iTemp <= GF_EARLIEST_MINUTE_FOR_MOBILE_CHECK
				IF NOT IS_BIT_SET iAgentFlags MOBILE_CALL_SCRIPT_RUNNING
					IF iCaller = iGFidx // if the appointment is set with the girl for this frame
						START_NEW_SCRIPT cell_phone_GF iGFidx CALL_DATE						
						iPhoneState = TIME_FOR_ANSWER
					ELSE
						IF NOT IS_BIT_SET iActiveGF iCaller 
							GOSUB GF_Dating_Agent_ResetAppointment
							iPhoneState = MOBILE_INACTIVE
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		BREAK
		
		CASE TIME_FOR_ANSWER
			IF NOT IS_BIT_SET iAgentFlags MOBILE_CALL_SCRIPT_RUNNING
				IF IS_BIT_SET iAgentFlags MOBILE_CALL_ANSWERED
					//--- Player has answered
					GOSUB GF_Dating_Agent_CreateDateAppointment
					iPhoneState = TIME_FOR_DATE
				ELSE
					//--- Player has NOT answered
					iGFLikesPlayer[iCaller] -= GF_LIKES_PLAYER_DECREMENT_NO_ANSWER
					GOSUB GF_Dating_Agent_SynchStats
					//--- RESET 
					GOSUB GF_Dating_Agent_ResetAppointment
					iPhoneState = MOBILE_INACTIVE
				ENDIF		 
			ENDIF
		BREAK

		CASE TIME_FOR_DATE
			GET_MINUTES_TO_TIME_OF_DAY iHours iMinutes iTemp
			IF iTemp <= GF_EARLIEST_MINUTE_FOR_MOBILE_CHECK
				iGFidx = iCaller
				GOSUB GF_Dating_Agent_PrintHelpAndClearMissedAppointment
				iPhoneState = MOBILE_INACTIVE
			ENDIF
		BREAK

		CASE MOBILE_DUMPED
			IF NOT IS_BIT_SET iAgentFlags MOBILE_CALL_SCRIPT_RUNNING								
				IF IS_BIT_SET iAgentFlags MOBILE_CALL_ANSWERED
					IF iCaller > -1 	
					AND iCaller < 6						
						iGFLikesPlayer[iCaller] = GF_HATES_PLAYER
						CLEAR_BIT iActiveGF iCaller  // remove the girlfriend					
					ENDIF
					iPhoneState = MOBILE_INACTIVE
				ELSE					
					IF iCaller > -1 	
					AND iCaller < 6
						//--- Player has not answered... wait until he meets girl
						iGFLikesPlayer[iCaller] = GF_DUMP_PLAYER_IMMEDIATELY
					ENDIF
					iPhoneState = MOBILE_INACTIVE
				ENDIF		 
			ENDIF
		BREAK
	ENDSWITCH
RETURN
/********************************************
		CREATE A RANDOM APPOINTMENT
********************************************/
GF_Dating_Agent_CreateRandomAppointment:
	GENERATE_RANDOM_INT_IN_RANGE 0 6 iTemp // pick a random girl		
	IF IS_BIT_SET iActiveGF iTemp // see if this random girl is active
 		IF iGFLikesPlayer[iTemp] > GF_LIKES_PLAYER_LOW_LIMIT // see if she likes the player and is alive
			IF NOT iGFLikesPlayer[iTemp] = GF_HATES_PLAYER
			AND NOT iGFLikesPlayer[iGFidx] = GF_IS_DEAD
				//--- Generate appointmen
				GENERATE_RANDOM_INT_IN_RANGE 1 23 iHours // Hours: 0-23
				GENERATE_RANDOM_INT_IN_RANGE 1 59 iMinutes // Minutes: 0-59
				iCaller = iTemp
				iPhoneState = TIME_FOR_CALL
			ENDIF	 
		ENDIF
	ENDIF
RETURN
/********************************************
		CREATE A DATE APPOINTMENT
********************************************/
GF_Dating_Agent_CreateDateAppointment:
	GET_TIME_OF_DAY iHours iMinutes 
	iMinutes = 31
	iHours += 5
	IF iHours > 23
		iTemp = iHours - 23
		iHours = 0 + iTemp
	ENDIF
RETURN
/*******************************************
  PRINT HELP AND CLEAR MISSED APPOINTMENT
********************************************/
GF_Dating_Agent_PrintHelpAndClearMissedAppointment:

	IF iCaller > -1 	
	AND iCaller < 6
		
		iGFLikesPlayer[iCaller] -= GF_LIKES_PLAYER_DECREMENT_MISS_DATE
		GOSUB GF_Dating_Agent_SynchStats

		IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
		AND iGFLikesPlayer[iCaller] >  GF_LIKES_PLAYER_LOW_LIMIT
			
			SWITCH iCaller
				CASE COOCHIE
				 	PRINT_HELP GF_APP0 //You missed your appointment with <name>. She won't be happy.
				 BREAK

				CASE MICHELLE
				 	PRINT_HELP GF_APP1 
				 BREAK

				CASE KYLIE
				 	PRINT_HELP GF_APP2 
				 BREAK

				CASE BARBARA
				 	PRINT_HELP GF_APP3 
				 BREAK

				CASE SUZIE
				 	PRINT_HELP GF_APP4 
				 BREAK

				CASE MILLIE
				 	PRINT_HELP GF_APP5 
				 BREAK
			ENDSWITCH

			CLEAR_BIT iAgentFlags MOBILE_CALL_ANSWERED			
			CLEAR_BIT iAgentFlags iCaller // Remove the appointment flag		
			iCaller = -1 // mark the caller as null
		ENDIF
	ENDIF
RETURN
/********************************************
		RESET APPOINTMENT
********************************************/
GF_Dating_Agent_ResetAppointment:
	iCaller = -1
	CLEAR_BIT iAgentFlags MOBILE_CALL_ANSWERED
	CLEAR_BIT iAgentFlags iGFidx // Remove the appointment flag
RETURN
/********************************************
			CREATE A RANDOM DATE
********************************************/
GF_Dating_Agent_CreateRandomDate:

	//--- Create a random Date
	GENERATE_RANDOM_INT_IN_RANGE 0 45 iTemp				
	IF iTemp > 10
		IF iTemp > 20
			IF iTemp > 30 
				IF IS_BIT_SET iGFLikesOnDate[iGFidx] LIKES_TO_DRIVE // 31 to 45 				
					iTemp =	GIRL_DRIVE
				ELSE
					IF iTemp > 35 // 36 to 45
						iTemp = DRIVE					
					ELSE // 31 to 35
				    	iTemp = DANCE
					ENDIF
				ENDIF			
			ELSE // 21 to 30
				iTemp = DANCE 
			ENDIF
		ELSE //11 to 20
			iTemp = DRIVE
		ENDIF
	ELSE // 0 to 10
		iTemp = EAT_OUT
	ENDIF

	//--- CHEATS TRIGGERED BY CONSOLE COMMANDS
   	IF IS_BIT_SET iDateReport CHEAT_EAT_OUT
		iTemp = EAT_OUT
		iDateReport = 0
	ENDIF

	IF IS_BIT_SET iDateReport CHEAT_DRIVE
		iTemp = DRIVE
		iDateReport = 0
	ENDIF

	IF IS_BIT_SET iDateReport CHEAT_DANCE
		iTemp = DANCE
		iDateReport = 0
	ENDIF

	IF IS_BIT_SET iDateReport CHEAT_KINKY_SEX
		iTemp = KINKY_SEX
		iDateReport = 0
	ENDIF

	IF IS_BIT_SET iDateReport CHEAT_GIRL_DRIVE
		iTemp = GIRL_DRIVE
		iDateReport = 0
	ENDIF	
 	//---------------------------------------------
	
RETURN
/********************************************
		CREATE & INIT GF AS A PED
********************************************/
GF_Dating_Agent_CreateAndInitGF:

	SWITCH iGFidx

		CASE COOCHIE 
				WHILE NOT HAS_MODEL_LOADED GANGRL3
				OR NOT HAS_MODEL_LOADED MICRO_UZI
					REQUEST_MODEL MICRO_UZI
					REQUEST_MODEL GANGRL3						  
					WAIT 0
				ENDWHILE
				
				CREATE_CHAR	PEDTYPE_CIVFEMALE GANGRL3 2402.3899 -1712.8988 13.1402 iCurrentGF
				SET_CHAR_HEADING iCurrentGF 180.0
													 
				GIVE_WEAPON_TO_CHAR iCurrentGF WEAPONTYPE_MICRO_UZI GF_AMMO_IN_UZI
				SET_CURRENT_CHAR_WEAPON iCurrentGF WEAPONTYPE_UNARMED
				SET_CHAR_ACCURACY iCurrentGF 75
				 
				COPY_SHARED_CHAR_DECISION_MAKER DM_PED_RANDOM_WEAK iGF_GlobalDM_Weak_Gang
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE iGF_GlobalDM_Weak_Gang EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_GANG_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE iGF_GlobalDM_Weak_Gang EVENT_ACQUAINTANCE_PED_DISLIKE TASK_SIMPLE_GANG_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE iGF_GlobalDM_Weak_Gang EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT_STAND_STILL 0.0 50.0 0.0 0.0 FALSE TRUE
				SET_CHAR_DECISION_MAKER iCurrentGF iGF_GlobalDM_Weak_Gang	
			
				SET_CHAR_RELATIONSHIP iCurrentGF ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_FLAT 	
				SET_CHAR_RELATIONSHIP iCurrentGF ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_NMEX
				SET_CHAR_RELATIONSHIP iCurrentGF ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_SFMEX
				SET_CHAR_RELATIONSHIP iCurrentGF ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_VIET 
				SET_CHAR_RELATIONSHIP iCurrentGF ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_MAFIA
				SET_CHAR_RELATIONSHIP iCurrentGF ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_TRIAD
				SET_CHAR_RELATIONSHIP iCurrentGF ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_SMEX
				
				SET_CHAR_STAY_IN_SAME_PLACE iCurrentGF TRUE
				SET_CHAR_SUFFERS_CRITICAL_HITS iCurrentGF FALSE
				SET_CHAR_NEVER_TARGETTED iCurrentGF TRUE
				SET_CHAR_PROOFS iCurrentGF FALSE FALSE TRUE TRUE FALSE
				SET_CHAR_HEALTH iCurrentGF 500
				DONT_REMOVE_CHAR iCurrentGF	
				TASK_STAND_STILL iCurrentGF -1
		BREAK

		CASE MICHELLE
				WHILE NOT HAS_MODEL_LOADED MECGRL3
					REQUEST_MODEL MECGRL3						  
					WAIT 0
				ENDWHILE
				
				CREATE_CHAR	PEDTYPE_CIVFEMALE MECGRL3 -1799.8746 1202.0568 24.1328 iCurrentGF				 
				SET_CHAR_HEADING iCurrentGF 180.0
				 								    
				SET_CHAR_DECISION_MAKER iCurrentGF DM_PED_INDOORS 

				SET_CHAR_STAY_IN_SAME_PLACE iCurrentGF TRUE
				SET_CHAR_SUFFERS_CRITICAL_HITS iCurrentGF FALSE
				SET_CHAR_NEVER_TARGETTED iCurrentGF TRUE
				SET_CHAR_PROOFS iCurrentGF FALSE FALSE TRUE TRUE FALSE
				SET_CHAR_HEALTH iCurrentGF 500
				DONT_REMOVE_CHAR iCurrentGF	
				TASK_STAND_STILL iCurrentGF -1
		BREAK

		CASE KYLIE
				WHILE NOT HAS_MODEL_LOADED GUNGRL3
					REQUEST_MODEL GUNGRL3						  
					WAIT 0
				ENDWHILE
				
				CREATE_CHAR	PEDTYPE_CIVFEMALE GUNGRL3 -384.6795 -1438.8401 25.3281 iCurrentGF				 
				SET_CHAR_HEADING iCurrentGF 268.2687 
				
				COPY_SHARED_CHAR_DECISION_MAKER DM_PED_RANDOM_TOUGH iGF_GlobalDM_Tough
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE iGF_GlobalDM_Tough EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT_STAND_STILL 0.0 50.0 0.0 0.0 FALSE TRUE
				SET_CHAR_DECISION_MAKER iCurrentGF iGF_GlobalDM_Tough

				SET_CHAR_STAY_IN_SAME_PLACE iCurrentGF TRUE
				SET_CHAR_SUFFERS_CRITICAL_HITS iCurrentGF FALSE
				SET_CHAR_NEVER_TARGETTED iCurrentGF TRUE
				SET_CHAR_PROOFS iCurrentGF FALSE FALSE TRUE TRUE FALSE
				SET_CHAR_HEALTH iCurrentGF 500
				DONT_REMOVE_CHAR iCurrentGF	
				TASK_STAND_STILL iCurrentGF -1
		BREAK

		CASE BARBARA
				WHILE NOT HAS_MODEL_LOADED COPGRL3
					REQUEST_MODEL COPGRL3						  
					WAIT 0
				ENDWHILE
				
				CREATE_CHAR	PEDTYPE_CIVFEMALE COPGRL3 -1390.3027 2641.0662 54.9844 iCurrentGF				 
				SET_CHAR_HEADING iCurrentGF 187.2
				 								    
				COPY_SHARED_CHAR_DECISION_MAKER DM_PED_RANDOM_TOUGH iGF_GlobalDM_Tough
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE iGF_GlobalDM_Tough EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT_STAND_STILL 0.0 50.0 0.0 0.0 FALSE TRUE
				SET_CHAR_DECISION_MAKER iCurrentGF iGF_GlobalDM_Tough

				SET_CHAR_STAY_IN_SAME_PLACE iCurrentGF TRUE
				SET_CHAR_SUFFERS_CRITICAL_HITS iCurrentGF FALSE
				SET_CHAR_NEVER_TARGETTED iCurrentGF TRUE
				SET_CHAR_PROOFS iCurrentGF FALSE FALSE TRUE TRUE FALSE
				SET_CHAR_HEALTH iCurrentGF 500
				DONT_REMOVE_CHAR iCurrentGF	
				TASK_STAND_STILL iCurrentGF -1
		BREAK

		CASE SUZIE
				WHILE NOT HAS_MODEL_LOADED NURGRL3
					REQUEST_MODEL NURGRL3						  
					WAIT 0
				ENDWHILE
				
				CREATE_CHAR	PEDTYPE_CIVFEMALE NURGRL3 -2573.3567 1155.0908 54.7347 iCurrentGF				 
				SET_CHAR_HEADING iCurrentGF 167.8854
				 								    
				COPY_SHARED_CHAR_DECISION_MAKER DM_PED_RANDOM_TOUGH iGF_GlobalDM_Tough
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE iGF_GlobalDM_Tough EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT_STAND_STILL 0.0 50.0 0.0 0.0 FALSE TRUE
				SET_CHAR_DECISION_MAKER iCurrentGF iGF_GlobalDM_Tough

				SET_CHAR_STAY_IN_SAME_PLACE iCurrentGF TRUE
				SET_CHAR_SUFFERS_CRITICAL_HITS iCurrentGF FALSE
				SET_CHAR_NEVER_TARGETTED iCurrentGF TRUE
				SET_CHAR_PROOFS iCurrentGF FALSE FALSE TRUE TRUE FALSE
				SET_CHAR_HEALTH iCurrentGF 500
				DONT_REMOVE_CHAR iCurrentGF	
				TASK_STAND_STILL iCurrentGF -1
		BREAK

		CASE MILLIE
				WHILE NOT HAS_MODEL_LOADED CROGRL3
					REQUEST_MODEL CROGRL3						  
					WAIT 0
				ENDWHILE
				
				CREATE_CHAR	PEDTYPE_CIVFEMALE CROGRL3 2037.4762 2719.7939 10.5436 iCurrentGF				 
				SET_CHAR_HEADING iCurrentGF 9.2
				 								    
				SET_CHAR_DECISION_MAKER iCurrentGF DM_PED_INDOORS	

				SET_CHAR_STAY_IN_SAME_PLACE iCurrentGF TRUE
				SET_CHAR_SUFFERS_CRITICAL_HITS iCurrentGF FALSE
				SET_CHAR_NEVER_TARGETTED iCurrentGF TRUE
				SET_CHAR_PROOFS iCurrentGF FALSE FALSE TRUE TRUE FALSE
				SET_CHAR_HEALTH iCurrentGF 500
				DONT_REMOVE_CHAR iCurrentGF	
				TASK_STAND_STILL iCurrentGF -1
		BREAK

	ENDSWITCH
RETURN
/********************************************
		UPDATE GIRLFRIEND STATISTICS
********************************************/
GF_Dating_Agent_UpdateGFStats:
//--- This subroutine reads the DATE REPORT bitfield and updates the statistics accordingly 

	IF IS_BIT_SET iDateReport DATE_WAS_SUCCESS // Date Successful		
		// Increment Likes Player 
		IF iGFLikesPlayer[iGFidx] <= 0
		AND iGFLikesPlayer[iGFidx] > GF_LIKES_PLAYER_LOW_LIMIT
			iGFLikesPlayer[iGFidx] = 0 // reset the lower boundary or the progress will still be negative
			iGFLikesPlayer[iGFidx] += GF_LIKES_PLAYER_INCREMENT
		ELSE   
			iGFLikesPlayer[iGFidx] += GF_LIKES_PLAYER_INCREMENT	
		ENDIF
		GOSUB GF_Dating_Agent_SynchStats
		
		//--- Update the good dates counter
		INCREMENT_INT_STAT DATES 1
	ELSE 
		//--- Check if the date took place at all
		IF IS_BIT_SET iDateReport DATE_WAS_ABORTED
			//--- Check if the date was not aborted because the girl simply wanted to dump the player
			IF NOT iGFLikesPlayer[iGFidx] = GF_HATES_PLAYER
			AND NOT iGFLikesPlayer[iGFidx] = GF_IS_DEAD
				//--- The date never actually started, but she cannot be totally happy
				iGFLikesPlayer[iGFidx]-= GF_LIKES_PLAYER_DECREMENT_DATE_ABORT 
				GOSUB GF_Dating_Agent_SynchStats		
			ENDIF
		ELSE
			//--- This is a disastrous date			
			iGFLikesPlayer[iGFidx]-= GF_LIKES_PLAYER_DECREMENT // Decrement Likes Player 
			GOSUB GF_Dating_Agent_SynchStats		
			//--- Update the bad dates counter
			INCREMENT_INT_STAT BAD_DATES 1  // the overall number of bad dates
		ENDIF
   ENDIF

	IF IS_BIT_SET iDateReport SEX_WAS_GOOD // Sex was Good
		// Increment Likes Player 
		iGFLikesPlayer[iGFidx] += GF_LIKES_PLAYER_INCREMENT
		GOSUB GF_Dating_Agent_SynchStats
		INCREMENT_INT_STAT TIMES_SCORED_WITH_GIRL 1
	ELSE
		//--- Make sure that the player acutally had sex, and was shit
		IF IS_BIT_SET iDateReport PLAYER_AGREES_TO_SEX 
			//--- Decrement Likes Player 
			iGFLikesPlayer[iGFidx] -= GF_LIKES_PLAYER_DECREMENT
			GOSUB GF_Dating_Agent_SynchStats
		ENDIF
	ENDIF

	//--- Check if she dosn't like the player anymore, if so instruct her to dump him
	IF NOT iGFLikesPlayer[iGFidx] = GF_HATES_PLAYER
	AND NOT iGFLikesPlayer[iGFidx] = GF_IS_DEAD
		IF iGFLikesPlayer[iGFidx] <= GF_LIKES_PLAYER_LOW_LIMIT
		   iGFLikesPlayer[iGFidx] = GF_DUMP_PLAYER_IMMEDIATELY		   
		ENDIF
	ENDIF

RETURN
/********************************************
  CHECK IF GIRL SHOULD DUMP PLAYER (PHONE)
********************************************/
GF_Dating_Agent_CheckIfDumpPlayerPhone:
	IF NOT iGFLikesPlayer[iGFidx] = GF_HATES_PLAYER
	AND NOT iGFLikesPlayer[iGFidx] = GF_IS_DEAD
		IF NOT iGFLikesPlayer[iGFidx] = GF_DUMP_PLAYER_IMMEDIATELY
			IF iGFLikesPlayer[iGFidx] <= GF_LIKES_PLAYER_LOW_LIMIT
				iPhoneState = MOBILE_DUMPED	
				iCaller = iGFidx													
				START_NEW_SCRIPT cell_phone_GF iCaller CALL_DUMP
				RETURN		
			ENDIF
		ENDIF
	ENDIF
RETURN
/********************************************
			REMOVE GIRLFRIEND MODEL
********************************************/
GF_Dating_Agent_RemoveGF:

	SWITCH iGFidx
		CASE COOCHIE 
			MARK_MODEL_AS_NO_LONGER_NEEDED GANGRL3
			MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
			REMOVE_DECISION_MAKER iGF_GlobalDM_Weak_Gang 
		BREAK

		CASE MICHELLE
			MARK_MODEL_AS_NO_LONGER_NEEDED MECGRL3
		BREAK

		CASE KYLIE
			MARK_MODEL_AS_NO_LONGER_NEEDED GUNGRL3
			REMOVE_DECISION_MAKER iGF_GlobalDM_Tough
		BREAK

		CASE BARBARA
			MARK_MODEL_AS_NO_LONGER_NEEDED COPGRL3
			REMOVE_DECISION_MAKER iGF_GlobalDM_Tough
		BREAK
		
		CASE SUZIE
			MARK_MODEL_AS_NO_LONGER_NEEDED NURGRL3
			REMOVE_DECISION_MAKER iGF_GlobalDM_Tough
		BREAK

		CASE MILLIE
			MARK_MODEL_AS_NO_LONGER_NEEDED CROGRL3	 
		BREAK
	ENDSWITCH  	

	IF IS_BIT_SET iDateReport GIRL_IS_BACK_AT_HOME		
		//--- GIRL is in front of her house
		DELETE_CHAR iCurrentGF
	ELSE		
		IF IS_BIT_SET iDateReport PLAYER_TWO_TIMING
			//--- Wherever she has left the car, she must be deleted
			DELETE_CHAR iCurrentGF
		ELSE			
			IF iGFLikesPlayer[iGFidx] = GF_IS_DEAD
				//--- If she is dead the body should stay there for a while
				MARK_CHAR_AS_NO_LONGER_NEEDED iCurrentGF
			ELSE
				//--- She is probably running away in the distance, so fade her
				REMOVE_CHAR_ELEGANTLY iCurrentGF			
			ENDIF
		ENDIF
	ENDIF
	
	//--- Clear the global var for the dance minigame
	iDanceGirlfriend = 0

	IF IS_BIT_SET iDateReport MEET_TOMORROW
		GET_CURRENT_DAY_OF_WEEK iTemp
		++iTemp
		IF iTemp > 7
			iTemp = 1
		ENDIF
		//--- Find the right bit to store this day in the diary		
		iTemp += H_10PM // the last hours 
		//--- Store the day and mark the "skip a day" bit
		SET_BIT iGFDiaryOfBusyHours[iGFidx] iTemp
		SET_BIT iGFDiaryOfBusyHours[iGFidx] NEXT_FREE_DAY
	ENDIF

RETURN
/*******************************************
		IS PLAYER IN GIMP SUIT
********************************************/
GF_Dating_Agent_IsPlayerInGimpSuit:
	IF IS_PLAYER_PLAYING PLAYER1
		IF IS_PLAYER_WEARING Player1 CLOTHES_TEX_EXTRA1 gimpleg
			iTemp = 1
		ELSE
			iTemp = 0
		ENDIF
	ENDIF
RETURN
/********************************************
		CLEAN GIRLFRIEND STATS
********************************************/
GF_Dating_Agent_DeleteStats:
 
	SHOW_UPDATE_STATS  FALSE

	SWITCH iGFidx
		CASE COOCHIE 
			SET_INT_STAT GIRLFRIEND_DENISE 0
		BREAK

		CASE MICHELLE
			SET_INT_STAT GIRLFRIEND_MICHELLE 0
		BREAK

		CASE KYLIE
			SET_INT_STAT GIRLFRIEND_HELENA 0
		BREAK

		CASE BARBARA
			SET_INT_STAT GIRLFRIEND_BARBARA 0
		BREAK

		CASE SUZIE
			SET_INT_STAT GIRLFRIEND_KATIE 0
		BREAK

		CASE MILLIE
			SET_INT_STAT GIRLFRIEND_MILLIE 0
		BREAK
	ENDSWITCH

	SHOW_UPDATE_STATS  TRUE
	
RETURN
/********************************************
		SYNCH GIRLFRIEND STATS
********************************************/
GF_Dating_Agent_SynchStats:
	SWITCH iGFidx

		CASE COOCHIE 			
			GET_INT_STAT GIRLFRIEND_DENISE iTempStat 
			IF iTempStat < iGFLikesPlayer[COOCHIE]
				IF iGFLikesPlayer[COOCHIE] > 100
					iGFLikesPlayer[COOCHIE] = 100
				ENDIF 
				IF iGFLikesPlayer[COOCHIE] < 0
					SET_INT_STAT GIRLFRIEND_DENISE 0
				ELSE
					iTempStat -= iGFLikesPlayer[COOCHIE]
					ABS iTempStat 
					INCREMENT_INT_STAT GIRLFRIEND_DENISE iTempStat
				ENDIF 
			ELSE
				IF iTempStat > iGFLikesPlayer[COOCHIE]
					IF iGFLikesPlayer[COOCHIE] > 100
						iGFLikesPlayer[COOCHIE] = 100
					ENDIF 
					IF iGFLikesPlayer[COOCHIE] < 0
						SET_INT_STAT GIRLFRIEND_DENISE 0
					ELSE
						iTempStat -= iGFLikesPlayer[COOCHIE]
						DECREMENT_INT_STAT GIRLFRIEND_DENISE iTempStat
					ENDIF 
				ENDIF
			ENDIF
		BREAK

		CASE MICHELLE
			GET_INT_STAT GIRLFRIEND_MICHELLE iTempStat 
			IF iTempStat < iGFLikesPlayer[MICHELLE]
				IF iGFLikesPlayer[MICHELLE] > 100
					iGFLikesPlayer[MICHELLE] = 100
				ENDIF 
				IF iGFLikesPlayer[MICHELLE] < 0
					SET_INT_STAT GIRLFRIEND_MICHELLE 0
				ELSE
					iTempStat -= iGFLikesPlayer[MICHELLE]
					ABS iTempStat 
					INCREMENT_INT_STAT GIRLFRIEND_MICHELLE iTempStat
				ENDIF 
			ELSE
				IF iTempStat > iGFLikesPlayer[MICHELLE]
					IF iGFLikesPlayer[MICHELLE] > 100
						iGFLikesPlayer[MICHELLE] = 100
					ENDIF 
					IF iGFLikesPlayer[MICHELLE] < 0
						SET_INT_STAT GIRLFRIEND_MICHELLE 0
					ELSE
						iTempStat -= iGFLikesPlayer[MICHELLE]
						DECREMENT_INT_STAT GIRLFRIEND_MICHELLE iTempStat
					ENDIF 
				ENDIF
			ENDIF
		BREAK

		CASE KYLIE
			GET_INT_STAT GIRLFRIEND_HELENA iTempStat 
			IF iTempStat < iGFLikesPlayer[KYLIE]
				IF iGFLikesPlayer[KYLIE] > 100
					iGFLikesPlayer[KYLIE] = 100
				ENDIF 
				IF iGFLikesPlayer[KYLIE] < 0
					SET_INT_STAT GIRLFRIEND_HELENA 0
				ELSE
					iTempStat -= iGFLikesPlayer[KYLIE]
					ABS iTempStat 
					INCREMENT_INT_STAT GIRLFRIEND_HELENA iTempStat
				ENDIF 
			ELSE
				IF iTempStat > iGFLikesPlayer[KYLIE]
					IF iGFLikesPlayer[KYLIE] > 100
						iGFLikesPlayer[KYLIE] = 100
					ENDIF 
					IF iGFLikesPlayer[KYLIE] < 0
						SET_INT_STAT GIRLFRIEND_HELENA 0
					ELSE
						iTempStat -= iGFLikesPlayer[KYLIE]
						DECREMENT_INT_STAT GIRLFRIEND_HELENA iTempStat
					ENDIF 
				ENDIF
			ENDIF
		BREAK

		CASE BARBARA
			GET_INT_STAT GIRLFRIEND_BARBARA iTempStat 
			IF iTempStat < iGFLikesPlayer[BARBARA]
				IF iGFLikesPlayer[BARBARA] > 100
					iGFLikesPlayer[BARBARA] = 100
				ENDIF 
				IF iGFLikesPlayer[BARBARA] < 0
					SET_INT_STAT GIRLFRIEND_BARBARA 0
				ELSE
					iTempStat -= iGFLikesPlayer[BARBARA]
					ABS iTempStat 
					INCREMENT_INT_STAT GIRLFRIEND_BARBARA iTempStat
				ENDIF 
			ELSE
				IF iTempStat > iGFLikesPlayer[BARBARA]
					IF iGFLikesPlayer[BARBARA] > 100
						iGFLikesPlayer[BARBARA] = 100
					ENDIF 
					IF iGFLikesPlayer[BARBARA] < 0
						SET_INT_STAT GIRLFRIEND_BARBARA 0
					ELSE
						iTempStat -= iGFLikesPlayer[BARBARA]
						DECREMENT_INT_STAT GIRLFRIEND_BARBARA iTempStat
					ENDIF 
				ENDIF
			ENDIF
		BREAK

		CASE SUZIE
			GET_INT_STAT GIRLFRIEND_KATIE iTempStat 
			IF iTempStat < iGFLikesPlayer[SUZIE]
				IF iGFLikesPlayer[SUZIE] > 100
					iGFLikesPlayer[SUZIE] = 100
				ENDIF 
				IF iGFLikesPlayer[SUZIE] < 0
					SET_INT_STAT GIRLFRIEND_KATIE 0
				ELSE
					iTempStat -= iGFLikesPlayer[SUZIE]
					ABS iTempStat 
					INCREMENT_INT_STAT GIRLFRIEND_KATIE iTempStat
				ENDIF 
			ELSE
				IF iTempStat > iGFLikesPlayer[SUZIE]
					IF iGFLikesPlayer[SUZIE] > 100
						iGFLikesPlayer[SUZIE] = 100
					ENDIF 
					IF iGFLikesPlayer[SUZIE] < 0
						SET_INT_STAT GIRLFRIEND_KATIE 0
					ELSE
						iTempStat -= iGFLikesPlayer[SUZIE]
						DECREMENT_INT_STAT GIRLFRIEND_KATIE iTempStat
					ENDIF 
				ENDIF
			ENDIF
		BREAK

		CASE MILLIE
			GET_INT_STAT GIRLFRIEND_MILLIE iTempStat 
			IF iTempStat < iGFLikesPlayer[MILLIE]
				IF iGFLikesPlayer[MILLIE] > 100
					iGFLikesPlayer[MILLIE] = 100
				ENDIF 
				IF iGFLikesPlayer[MILLIE] < 0
					SET_INT_STAT GIRLFRIEND_MILLIE 0
				ELSE
					iTempStat -= iGFLikesPlayer[MILLIE]
					ABS iTempStat 
					INCREMENT_INT_STAT GIRLFRIEND_MILLIE iTempStat
				ENDIF 
			ELSE
				IF iTempStat > iGFLikesPlayer[MILLIE]
					IF iGFLikesPlayer[MILLIE] > 100
						iGFLikesPlayer[MILLIE] = 100
					ENDIF 
					IF iGFLikesPlayer[MILLIE] < 0
						SET_INT_STAT GIRLFRIEND_MILLIE 0
					ELSE
						iTempStat -= iGFLikesPlayer[MILLIE]
						DECREMENT_INT_STAT GIRLFRIEND_MILLIE iTempStat
					ENDIF 
				ENDIF
			ENDIF
		BREAK
	ENDSWITCH
RETURN
/*******************************************
	DATING AGENT MISSION CLEANUP
********************************************/
GF_Dating_Agent_MissionCleanUp:  

	IF NOT iAgentState = GF_IDLE_TRY_TO_LOCATE_PLAYER // Dating Agent is not idle
		//--- do the baisc date housekeeping and stats update			
		GOSUB GF_Dating_Agent_RemoveGF
		GOSUB GF_Dating_Agent_UpdateGFStats
		//--- Put the agent back to idle
		iAgentState = GF_IDLE_TRY_TO_LOCATE_PLAYER	
	ENDIF
	

	//--- Clear all active appointments
	iTemp2 = -1
	REPEAT 6 iTemp
		IF IS_BIT_SET iAgentFlags iTemp
			CLEAR_BIT iAgentFlags iTemp // APPOINTMENT_ON_FOR_CURRENT_GF	
		ENDIF
	ENDREPEAT

	//--- Re-enable the stat updates (bar on screen) just in case we aborted while these were set off
	SHOW_UPDATE_STATS  TRUE

	iGFTimeStep = GF_TIME_STEP_SLOW

RETURN
/********************************************
		KEYBOARD CONSOLE COMMANDS
********************************************/
GF_Dating_Agent_Console_Commands:
  	GET_LATEST_CONSOLE_COMMAND GF_txtConsoleIn
	IF NOT IS_STRING_EMPTY $GF_txtConsoleIn 

		$GF_txtConsoleCheck = DOCOOCHIE
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			SET_BIT iActiveGF COOCHIE			
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = DOMICHELLE
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			SET_BIT iActiveGF MICHELLE
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = DOKYLIE
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			SET_BIT iActiveGF KYLIE
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = DOBARBARA
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			SET_BIT iActiveGF BARBARA
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = DOSUZIE
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			SET_BIT iActiveGF SUZIE			
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = DOMILLIE
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			SET_BIT iActiveGF MILLIE
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = FASTDATE
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			SET_BIT iActiveGF GF_CHEAT_MODE_ON
			//--- All girls like the player as much as needed for sex
			iGFLikesPlayer[COOCHIE] = iGFSelfRespect[COOCHIE]
			iGFLikesPlayer[MICHELLE] = iGFSelfRespect[MICHELLE]
			iGFLikesPlayer[BARBARA] = iGFSelfRespect[BARBARA]
			iGFLikesPlayer[KYLIE] = iGFSelfRespect[KYLIE]
			iGFLikesPlayer[SUZIE] = iGFSelfRespect[SUZIE]
			iGFLikesPlayer[MILLIE] = iGFSelfRespect[MILLIE]
			SET_BIT iDateReport FASTDATE_ON
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	

		$GF_txtConsoleCheck = GOCOOCHIE
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			IF IS_PLAYER_PLAYING PLAYER1
				SET_CHAR_COORDINATES scplayer 2405.2124 -1720.6879 12.6365
			ENDIF
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = GOMICHELLE
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			IF IS_PLAYER_PLAYING PLAYER1
				SET_CHAR_COORDINATES scplayer -1788.0 1202.0 30.0
			ENDIF
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = GOKYLIE
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			IF IS_PLAYER_PLAYING PLAYER1
				SET_CHAR_COORDINATES scplayer -371.4376 -1440.0717 24.7209
			ENDIF
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = GOBARBARA
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			IF IS_PLAYER_PLAYING PLAYER1
				SET_CHAR_COORDINATES scplayer -1400.0 2640.0 -100.0
			ENDIF
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = GOSUZIE
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			IF IS_PLAYER_PLAYING PLAYER1
				SET_CHAR_COORDINATES scplayer -2570.5107 1139.5815 54.8547
			ENDIF
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = GOMILLIE
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			IF IS_PLAYER_PLAYING PLAYER1
				SET_CHAR_COORDINATES scplayer 2028.3471 2736.96890 10.8603
			ENDIF
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = DATEFOOD
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn		
			SET_BIT iActiveGF GF_CHEAT_MODE_ON
			SET_BIT iDateReport CHEAT_EAT_OUT // This is dependent on fast date being on to be activated
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = DATEDRIVE
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			SET_BIT iActiveGF GF_CHEAT_MODE_ON
			iGFLikesPlayer[COOCHIE] = GF_LIKES_PLAYER_STAGE2
			iGFLikesPlayer[MICHELLE] = GF_LIKES_PLAYER_STAGE2
			iGFLikesPlayer[BARBARA] = GF_LIKES_PLAYER_STAGE2
			iGFLikesPlayer[KYLIE] = GF_LIKES_PLAYER_STAGE2
			iGFLikesPlayer[SUZIE] = GF_LIKES_PLAYER_STAGE2
			iGFLikesPlayer[MILLIE] = GF_LIKES_PLAYER_STAGE2
			SET_BIT iDateReport CHEAT_DRIVE // This is dependent on fast date being on to be activated
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = DATEDANCE
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			SET_BIT iActiveGF GF_CHEAT_MODE_ON	
			iGFLikesPlayer[COOCHIE] = GF_LIKES_PLAYER_STAGE2
			iGFLikesPlayer[MICHELLE] = GF_LIKES_PLAYER_STAGE2
			iGFLikesPlayer[BARBARA] = GF_LIKES_PLAYER_STAGE2
			iGFLikesPlayer[KYLIE] = GF_LIKES_PLAYER_STAGE2
			iGFLikesPlayer[SUZIE] = GF_LIKES_PLAYER_STAGE2
			iGFLikesPlayer[MILLIE] = GF_LIKES_PLAYER_STAGE2
			SET_BIT iDateReport CHEAT_DANCE // This is dependent on fast date being on to be activated
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = DATESPANK
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			SET_BIT iActiveGF GF_CHEAT_MODE_ON
			SET_BIT iDateReport CHEAT_KINKY_SEX // This is dependent on fast date being on to be activated
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = DATESHEDRIVES
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			SET_BIT iActiveGF GF_CHEAT_MODE_ON
			iGFLikesPlayer[MICHELLE] = GF_LIKES_PLAYER_STAGE2
			SET_BIT iDateReport CHEAT_GIRL_DRIVE 
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	

		$GF_txtConsoleCheck = MEETMICHELLE
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			IF IS_PLAYER_PLAYING PLAYER1
				SET_CHAR_COORDINATES scplayer -2026.9199 -99.9395 34.1641
				SET_CHAR_HEADING scplayer 180.0
			ENDIF
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = MEETKYLIE
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			IF IS_PLAYER_PLAYING PLAYER1
				flag_la1fin1_mission_counter = 2
				SET_CHAR_COORDINATES scplayer 243.9633 -161.2493 0.5781 
				SET_CHAR_HEADING scplayer 291.5735
			ENDIF
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = MEETBARBARA
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			IF IS_PLAYER_PLAYING PLAYER1
				SET_CHAR_COORDINATES scplayer -1411.2939 2637.6094 54.6875
				 SET_CHAR_HEADING scplayer 348.9253
			ENDIF
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = MEETSUZIE
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			IF IS_PLAYER_PLAYING PLAYER1
				SET_CHAR_COORDINATES scplayer -2266.2529 -155.7216 34.3125 
				SET_CHAR_HEADING scplayer 85.0
			ENDIF
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	  

		$GF_txtConsoleCheck = GIMPSUIT
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			IF IS_PLAYER_PLAYING PLAYER1
				GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP Player1 gimpleg gimpleg CLOTHES_TEX_EXTRA1
				BUILD_PLAYER_MODEL player1
			ENDIF
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	
		  
		$GF_txtConsoleCheck = TWOTIMING
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			SET_BIT iActiveGF GF_CHEAT_MODE_ON
			SET_BIT iDateReport CHEAT_TWO_TIMING
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	

		$GF_txtConsoleCheck = ELEGANTMAN
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn
			iGFDesiredSexAppeal[COOCHIE] = 0
			iGFDesiredSexAppeal[MICHELLE] = 0
			iGFDesiredSexAppeal[BARBARA] = 0
			iGFDesiredSexAppeal[KYLIE] = 0
			iGFDesiredSexAppeal[SUZIE] = 0
			iGFDesiredSexAppeal[MILLIE] = 0
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	

		$GF_txtConsoleCheck = SHOWENTRY
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn			
			SWITCH_ENTRY_EXIT FDrest1 TRUE
			SWITCH_ENTRY_EXIT rest2 TRUE
			SWITCH_ENTRY_EXIT diner1 TRUE
			SWITCH_ENTRY_EXIT diner2 TRUE
			SWITCH_ENTRY_EXIT TSdiner TRUE
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	

		$GF_txtConsoleCheck = HIDEENTRY
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn			
			SWITCH_ENTRY_EXIT FDrest1 FALSE
			SWITCH_ENTRY_EXIT rest2 FALSE
			SWITCH_ENTRY_EXIT diner1 FALSE
			SWITCH_ENTRY_EXIT diner2 FALSE
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	

		$GF_txtConsoleCheck = UNCENSORED
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn			
			iCensoredVersion = 0
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	

		$GF_txtConsoleCheck = STATSUP
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn			
			iGFLikesPlayer[iGFidx] += 10
			GOSUB GF_Dating_Agent_SynchStats
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	

		$GF_txtConsoleCheck = STATSDOWN
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn			
			iGFLikesPlayer[iGFidx] -= 10
			GOSUB GF_Dating_Agent_SynchStats
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	

		$GF_txtConsoleCheck = DUMPME
		IF $GF_txtConsoleCheck = $GF_txtConsoleIn			
			iGFLikesPlayer[iGFidx] = GF_LIKES_PLAYER_LOW_LIMIT
			GOSUB GF_Dating_Agent_SynchStats
			RESET_LATEST_CONSOLE_COMMAND  
			RETURN
		ENDIF	
	
	ENDIF
RETURN
/*******************************************
				RUN DEBUG
********************************************/
GF_Dating_Agent_Debug:

	WRITE_DEBUG I__________________________I
	WRITE_DEBUG H 		   
	WRITE_DEBUG G
	WRITE_DEBUG F
	WRITE_DEBUG E
	WRITE_DEBUG D
	WRITE_DEBUG $txtCurrZone
	WRITE_DEBUG_WITH_INT IGFIDX iGFidx
	
	SWITCH iAgentState
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