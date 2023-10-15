MISSION_START

//--- CONSTANTS
CONST_INT PLAYER_IS_BAD 2

CONST_INT MEETING_PLAYER_NOT_FAT 	10
CONST_INT MEETING_PLAYER_NOT_FIT	11
CONST_INT MEETING_PLAYER_TOO_FIT	12

/*****************************************************************************************************************************************
************************************************					**********************************************************************
************************************************	GIRL MEETING	**********************************************************************
************************************************					**********************************************************************
*****************************************************************************************************************************************/
/* TO DO ON THIS SCRIPT:
------------------------

*/
{
GF_Meeting:		  
    SCRIPT_NAME GFMEET

    LVAR_INT  iGFIdx_par 								 		   	// Parameters arriving from caller
	LVAR_FLOAT fParX fParY fParZ fParH
    LVAR_INT iState iSubStateStatus iMeetingFlags iDefaultState		// State Machine variables
	LVAR_FLOAT fX fY fZ fTemp   
	LVAR_INT iTemp iTemp2 iGF_ped iGF_DM iOther_Ped girl_arrow
	LVAR_TEXT_LABEL GF_intro GF_positive GF_negative
	
	/* iMeetingFlags Bits
			1 	HAS_DISMISSED_PLAYER
			2 	PLAYER_HAS_HIT_GIRL 
			3 	ALLOWED_TO_MAKE_PHYSIQUE_COMMENT
			10 	PLAYER_NOT_FAT
			11 	PLAYER_NOT_FIT
			12 	PLAYER_TOO_FIT

	*/
			
	//--- Init the vars			
	iState = 0 
	iSubStateStatus = 0
	iMeetingFlags = 0

	//--- Parameter passing Fudge
	IF iMeetingFlags > 0
	   CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 iGF_ped
	   CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 iOther_ped
	   ADD_BLIP_FOR_CHAR iGF_ped girl_arrow
	ENDIF
	
	
	//--- Streaming requests
	GOSUB GF_Meeting_SteamAnims
	GOSUB GF_Meeting_SteamGFModel_SetAmbience
	
	//---MAIN LOOP---
GF_Meeting_Main_Loop:

	WAIT 0

//--- A call to the debug subroutine if needed - otherwise comment	
//	GOSUB GF_Meeting_Run_Debug	

	//--- Do all the checks and then run the state machine
 	IF IS_PLAYER_PLAYING PLAYER1
	   	IF	NOT IS_CHAR_DEAD iGF_ped   	 
		AND LOCATE_CHAR_ANY_MEANS_3D scplayer fParX fParY fParZ 100.0 100.0 20.0 FALSE	 	

			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR iGF_ped scplayer
				SET_BIT iMeetingFlags PLAYER_IS_BAD
				iState = 6
				iSubStateStatus = 0
			ENDIF

			IF iDefaultState = 1
				IF IS_CHAR_DEAD iOther_ped
				OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR iOther_ped scplayer				  
					SET_BIT iMeetingFlags PLAYER_IS_BAD
					iState = 6
					iSubStateStatus = 0
				ENDIF
			ENDIF

			//--- Go into the appopriate state
			GOSUB GF_Meeting_DoCurrentState

			//--- Call the speech for the girlfriend, according to what has been requested 
			GOSUB GF_Meeting_SpeechManager	   

		ELSE
			GOSUB GF_Meeting_CleanUp
		ENDIF
	ELSE
		GOSUB GF_Meeting_CleanUp
	ENDIF

GOTO GF_Meeting_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
				STATES
********************************************/
GF_Meeting_DoCurrentState:
	SWITCH iState	   	
   		CASE 1 //--- IDLE
			GOSUB GF_Meeting_State1
		BREAK
   		CASE 2 //--- GIRL IMPRESSED - CONVERSATION
			GOSUB GF_Meeting_State2
		BREAK
   		CASE 3 //--- GIRL NOT IMPRESSED
			GOSUB GF_Meeting_State3
		BREAK
   		CASE 4 //--- SHOOTING IDLE
			GOSUB GF_Meeting_State4
		BREAK
   		CASE 5 //--- TAI CHI IDLE
			GOSUB GF_Meeting_State5
		BREAK
		CASE 6 //--- UPSET STATE
			GOSUB GF_Meeting_State6
		BREAK
	ENDSWITCH

RETURN
/********************************************
			DEFAULT CHAT IDLE STATE
********************************************/
GF_Meeting_State1:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Entry of IDLE				
		IF IS_PLAYER_IN_POSITION_FOR_CONVERSATION iGF_ped
			IF NOT IS_BIT_SET iActiveGF iGFidx_par // check that she has not JUST become an active GF
				IF LOCATE_CHAR_ON_FOOT_CHAR_2D scplayer iGF_ped 2.0 2.0 FALSE
					GOSUB GF_Meeting_CheckLikesPlayer // Returns iTemp = 1 if she likes him	
					IF iTemp = 1
						ENABLE_CONVERSATION iGF_ped TRUE
						IF IS_CONVERSATION_AT_NODE iGF_ped  $GF_intro 
							iState = 2  // Impressed State
							iSubStateStatus = 0
						ELSE		
							ENABLE_CONVERSATION iGF_ped FALSE					
						ENDIF
					ELSE
						IF NOT IS_BIT_SET iMeetingFlags 1 // HAS_DISMISSED_PLAYER 1
							iState = 3 // Not Impressed State
							iSubStateStatus = 0				
							ENABLE_CONVERSATION iGF_ped FALSE
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		
			//--- Play default idle anims					
			GET_SCRIPT_TASK_STATUS iGF_ped TASK_CHAT_WITH_CHAR iTemp
			GET_SCRIPT_TASK_STATUS iOther_ped TASK_CHAT_WITH_CHAR iTemp2
			IF iTemp = FINISHED_TASK
			AND iTemp2 = FINISHED_TASK
				TASK_CHAT_WITH_CHAR iGF_ped iOther_ped TRUE TRUE
				TASK_CHAT_WITH_CHAR iOther_ped iGF_ped FALSE TRUE
			ENDIF

		ENDIF					
	BREAK

	ENDSWITCH

RETURN
/********************************************
				CONVERSATION
********************************************/
GF_Meeting_State2:  
SWITCH iSubStateStatus	

	CASE 0
		//--- orients to face player
		TIMERB = 0 
		TASK_TURN_CHAR_TO_FACE_CHAR iGF_ped scplayer
		IF iDefaultState = 1
		AND NOT IS_CHAR_DEAD iOther_ped
			//--- Stop the other char from speaking		
			GET_SCRIPT_TASK_STATUS iOther_ped TASK_STAND_STILL iTemp
			IF iTemp = FINISHED_TASK		 
				TASK_STAND_STILL iOther_ped -2		
			ENDIF 
		ENDIF
		++iSubStateStatus
	BREAK

	CASE 1
		//--- starts looking at player 
		GET_SCRIPT_TASK_STATUS iGF_ped TASK_TURN_CHAR_TO_FACE_CHAR iTemp		
		IF iTemp = FINISHED_TASK
			TASK_LOOK_AT_CHAR iGF_ped scplayer	-1

			GET_SCRIPT_TASK_STATUS iGF_ped TASK_STAND_STILL iTemp
			IF iTemp = FINISHED_TASK
				TASK_STAND_STILL iGF_ped -2		  				
			ENDIF
			PRINT_HELP_FOREVER TALK_1  
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 2																   
		IF IS_CONVERSATION_AT_NODE iGF_ped  $GF_negative //Negative response
			
			CLEAR_HELP
			IF TIMERB > 9000
				//--- Reset the 'likes player' value stored previously
				iGFLikesPlayer[iGFidx_par] = 0
				
				CLEAR_BIT iActiveGF iGFidx_par
			
			 	IF DOES_BLIP_EXIST girl_arrow 
			   		REMOVE_BLIP girl_arrow
				ENDIF
				
				//--- Conclude CONVERSATION - Back to Idle
				
				CLEAR_HELP
				CLEAR_LOOK_AT iGF_ped
				iSubStateStatus = 0
				iState = iDefaultState
			ENDIF
		ENDIF
			
		IF IS_CONVERSATION_AT_NODE iGF_ped  $GF_positive //Positive response
			
			CLEAR_HELP
			//--- Activate this girlfriend
			IF NOT IS_BIT_SET iActiveGF iGFidx_par 												
				SET_BIT iActiveGF iGFidx_par
			ENDIF
			//--- Remove arrow over her head
		 	IF DOES_BLIP_EXIST girl_arrow 
		   		REMOVE_BLIP girl_arrow
			ENDIF
			//--- Print help when conversation subtitle has cleared
			++iSubStateStatus
		ENDIF

		IF NOT IS_PLAYER_IN_POSITION_FOR_CONVERSATION iGF_ped
		AND TIMERB > 9000
			//--- Conclude CONVERSATION - Back to Idle			
			
			CLEAR_HELP
			CLEAR_LOOK_AT iGF_ped
			iSubStateStatus = 0
			iState = iDefaultState
		ENDIF		 

	BREAK
	
	CASE 3
		IF NOT IS_MESSAGE_BEING_DISPLAYED
			GOSUB GF_Meeting_PrintGirlSpecificHelp
			//--- Back to Idle																
			CLEAR_LOOK_AT iGF_ped
			iSubStateStatus = 0
			iState = iDefaultState				 
		ENDIF
	BREAK
		
	ENDSWITCH
RETURN
/********************************************
				UNIMPRESSED
********************************************/
GF_Meeting_State3:  
SWITCH iSubStateStatus	

	CASE 0
		//--- orients to face player
		TIMERB = 0 
		TASK_TURN_CHAR_TO_FACE_CHAR iGF_ped scplayer
		IF iDefaultState = 1
		AND NOT IS_CHAR_DEAD iOther_ped
			//--- Stop the other char from speaking
			GET_SCRIPT_TASK_STATUS iOther_ped TASK_STAND_STILL iTemp
			IF iTemp = FINISHED_TASK		 
				TASK_STAND_STILL iOther_ped -2		
			ENDIF 
		ENDIF
		++iSubStateStatus
	BREAK

	CASE 1
		//--- starts looking at player 
		GET_SCRIPT_TASK_STATUS iGF_ped TASK_TURN_CHAR_TO_FACE_CHAR iTemp		
		IF iTemp = FINISHED_TASK
			TASK_LOOK_AT_CHAR iGF_ped scplayer	-1

			GET_SCRIPT_TASK_STATUS iGF_ped TASK_STAND_STILL iTemp
			IF iTemp = FINISHED_TASK
				TASK_STAND_STILL iGF_ped -2		  				
			ENDIF
			++iSubStateStatus
		ENDIF
	BREAK

	CASE 2																   
		IF IS_BIT_SET iMeetingFlags 3 // ALLOWED_TO_MAKE_PHYSIQUE_COMMENT			
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_PHYSIQUE_CRITIQUE
			CLEAR_BIT iMeetingFlags 3 // ALLOWED_TO_MAKE_PHYSIQUE_COMMENT
		ELSE
			iGFSayContext = CONTEXT_GLOBAL_GFRIEND_SEX_APPEAL_TOO_LOW
		ENDIF	
		++iSubStateStatus				
	BREAK

	CASE 3										    
		IF TIMERB > 6000
			//--- Back to Idle
			CLEAR_LOOK_AT iGF_ped
			SET_BIT iMeetingFlags 1 // HAS_DISMISSED_PLAYER
			iSubStateStatus = 0
			iState = iDefaultState
		ENDIF
	BREAK	
	ENDSWITCH
RETURN
/********************************************
			SHOOTING IDLE STATE
********************************************/
GF_Meeting_State4:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Entry of IDLE				
		IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer iGF_ped 3.0 3.0 FALSE
			GOSUB GF_Meeting_CheckLikesPlayer // Returns iTemp > 0 if she likes him
			IF iTemp = 1
				ENABLE_CONVERSATION iGF_ped TRUE
				IF IS_CONVERSATION_AT_NODE iGF_ped  $GF_intro 
					iState = 2  // Impressed State
					iSubStateStatus = 0
				ELSE		
					ENABLE_CONVERSATION iGF_ped FALSE					
				ENDIF
			ELSE
				IF NOT IS_BIT_SET iMeetingFlags 1 // HAS_DISMISSED_PLAYER
					iState = 3 // Not Impressed State
					iSubStateStatus = 0				
					ENABLE_CONVERSATION iGF_ped FALSE
				ENDIF
			ENDIF
		ENDIF

		//--- Play default idle anims					
		GET_SCRIPT_TASK_STATUS iGF_ped TASK_SHOOT_AT_COORD iTemp
		IF iTemp = FINISHED_TASK
			TASK_SHOOT_AT_COORD iGF_ped 264.0 -155.0 5.0  -1
		ENDIF

	BREAK

	ENDSWITCH

RETURN
/********************************************
			TAI CHI IDLE STATE
********************************************/
GF_Meeting_State5:  
	SWITCH iSubStateStatus	 				

	CASE 0
		//--- Entry of IDLE				
  
  		//--- Play idle anims					
		GET_SCRIPT_TASK_STATUS iGF_ped TASK_PLAY_ANIM iTemp
		IF iTemp = FINISHED_TASK
			TASK_PLAY_ANIM iGF_ped Tai_Chi_in PARK 4.0 FALSE FALSE FALSE FALSE -1
			++iSubStateStatus
		ENDIF

	BREAK

	CASE 1
		//--- IDLE loop
		GET_SCRIPT_TASK_STATUS iGF_ped TASK_PLAY_ANIM iTemp
		IF iTemp = FINISHED_TASK
			TASK_PLAY_ANIM iGF_ped Tai_Chi_loop PARK 4.0 FALSE FALSE FALSE FALSE 99999999999
		ENDIF

		IF IS_PLAYER_IN_POSITION_FOR_CONVERSATION iGF_ped
		AND LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer iGF_ped 2.0 2.0 FALSE
		AND IS_CONVERSATION_AT_NODE iGF_ped  $GF_intro 
		AND NOT IS_BIT_SET iMeetingFlags 1 // HAS_DISMISSED_PLAYER
			TASK_PLAY_ANIM iGF_ped Tai_Chi_out PARK 4.0 FALSE FALSE FALSE FALSE -1
			++iSubStateStatus
		ELSE
			ENABLE_CONVERSATION iGF_ped FALSE		
		ENDIF

	BREAK

	CASE 2
		GOSUB GF_Meeting_CheckLikesPlayer // Returns iTemp > 0 if she likes him
		IF iTemp = 1
			ENABLE_CONVERSATION iGF_ped TRUE
			iState = 2  // Impressed State
			iSubStateStatus = 0	
		ELSE
			iState = 3 // Not Impressed State
			iSubStateStatus = 0							
		ENDIF 
	BREAK	  
	ENDSWITCH

RETURN
/********************************************
				UPSET STATE
********************************************/
GF_Meeting_State6:  

SWITCH iSubStateStatus	 				

	CASE 0
		IF IS_BIT_SET iMeetingFlags PLAYER_IS_BAD

			iGFLikesPlayer[iGFidx_par] = 0
		
			IF iGFIdx_par = MICHELLE
			   SET_CHAR_DECISION_MAKER iGF_ped DM_PED_INDOORS			   
			   
			ELSE
	   			SET_CHAR_DECISION_MAKER iGF_ped DM_PED_RANDOM_TOUGH	
			ENDIF

			IF NOT IS_CHAR_DEAD iOther_ped
				SET_CHAR_DECISION_MAKER iOther_Ped DM_PED_INDOORS				
			ENDIF
			
			IF HAS_CHAR_GOT_WEAPON iGF_ped WEAPONTYPE_PISTOL
				SET_CURRENT_CHAR_WEAPON iGF_ped WEAPONTYPE_PISTOL 
				GET_SCRIPT_TASK_STATUS iGF_ped TASK_KILL_CHAR_ON_FOOT iTemp 
				IF iTemp = FINISHED_TASK
					TASK_KILL_CHAR_ON_FOOT iGF_ped scplayer					
				ENDIF
			ELSE
				GET_SCRIPT_TASK_STATUS iGF_ped TASK_DUCK iTemp 
				IF iTemp = FINISHED_TASK		
					TASK_DUCK iGF_ped -2				   					
				ENDIF
			ENDIF

		   	IF DOES_BLIP_EXIST girl_arrow 
		   		REMOVE_BLIP girl_arrow
			ENDIF  

			//--- Flag her only available to meet the next day
			GET_CURRENT_DAY_OF_WEEK iTemp
			++iTemp
			IF iTemp > 7
				iTemp = 1
			ENDIF
			//--- Find the right bit to store this info in the diary
			iTemp += H_10PM // the last hours 
			SET_BIT iGFDiaryOfBusyHours[iGFIdx_par] iTemp
			SET_BIT iGFDiaryOfBusyHours[iGFIdx_par] NEXT_FREE_DAY

			++iSubStateStatus 
		ENDIF
	BREAK

	CASE 1
		IF HAS_CHAR_GOT_WEAPON iGF_ped WEAPONTYPE_PISTOL
			SET_CURRENT_CHAR_WEAPON iGF_ped WEAPONTYPE_PISTOL 
			GET_SCRIPT_TASK_STATUS iGF_ped TASK_KILL_CHAR_ON_FOOT iTemp 
			IF iTemp = FINISHED_TASK
				TASK_KILL_CHAR_ON_FOOT iGF_ped scplayer					
			ENDIF
		ELSE
			GET_SCRIPT_TASK_STATUS iGF_ped TASK_DUCK iTemp 
			IF iTemp = FINISHED_TASK		
				TASK_DUCK iGF_ped -2				   					
			ENDIF
		ENDIF
	BREAK

ENDSWITCH

RETURN	 
/*****************************************************************************
								SUBROUTINES
******************************************************************************/
/*******************************************
		LOAD GF MODEL FROM STREAM
********************************************/
GF_Meeting_SteamGFModel_SetAmbience:

	SWITCH iGFIdx_par
		
		CASE MICHELLE
			WHILE NOT HAS_MODEL_LOADED MECGRL3
				REQUEST_MODEL MECGRL3						  
				WAIT 0
			ENDWHILE
			iTemp 			= MECGRL3
			$GF_intro 		= GF_1A // intro
			$GF_positive 	= GF_1C	// positive
			$GF_negative	= GF_1B // negative
			iDefaultState 	= 1 // Default Chat Idle State
		BREAK
		
		CASE KYLIE
			WHILE NOT HAS_MODEL_LOADED GUNGRL3			 
				REQUEST_MODEL GUNGRL3										  
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_MODEL_LOADED COLT45
				REQUEST_MODEL COLT45
				WAIT 0 
			ENDWHILE

			iTemp 			= GUNGRL3
			$GF_intro 		= GF_2A	
			$GF_positive 	= GF_2C
			$GF_negative	= GF_2B
			iDefaultState 	= 4  // Shooting Idle State
			
		BREAK

		CASE BARBARA
			WHILE NOT HAS_MODEL_LOADED COPGRL3
				REQUEST_MODEL COPGRL3						  
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_MODEL_LOADED COLT45
				REQUEST_MODEL COLT45
				WAIT 0 
			ENDWHILE

			iTemp 			= COPGRL3
			$GF_intro 		= GF_3A
			$GF_positive 	= GF_3C
			$GF_negative	= GF_3B
			iDefaultState 	= 1  // Default Chat Idle State

		BREAK						   

		CASE SUZIE
			WHILE NOT HAS_MODEL_LOADED NURGRL3
				REQUEST_MODEL NURGRL3						  
				WAIT 0
			ENDWHILE
			iTemp 			= NURGRL3
			$GF_intro 		= GF_4A
			$GF_positive 	= GF_4C
			$GF_negative	= GF_4B
			iDefaultState 	= 5  // Tai Chi Idle State
			iSubStateStatus = 0			

		BREAK

	ENDSWITCH

	CREATE_CHAR	PEDTYPE_CIVFEMALE iTemp fParX fParY fParZ iGF_ped	
	SET_CHAR_HEADING iGF_ped fParH

	IF iGFIdx_par = KYLIE
		GIVE_WEAPON_TO_CHAR iGF_ped WEAPONTYPE_PISTOL 999999999		
		SET_CHAR_ACCURACY iGF_ped 100
	ENDIF

	IF iGFIdx_par = BARBARA
  		GIVE_WEAPON_TO_CHAR iGF_ped WEAPONTYPE_PISTOL 999999999		
		SET_CURRENT_CHAR_WEAPON iGF_ped WEAPONTYPE_UNARMED
		SET_CHAR_ACCURACY iGF_ped 100 
	ENDIF

	SET_CHAR_STAY_IN_SAME_PLACE iGF_ped TRUE
	SET_CHAR_SUFFERS_CRITICAL_HITS iGF_ped FALSE
	SET_CHAR_NEVER_TARGETTED iGF_ped TRUE
	SET_CHAR_PROOFS iGF_ped FALSE FALSE TRUE TRUE FALSE
	SET_CHAR_HEALTH iGF_ped 200
	TASK_STAND_STILL iGF_ped -1
	SET_CHAR_KEEP_TASK iGF_ped TRUE 

	//--- Init the PLAYER CONVERSATION
	START_SETTING_UP_CONVERSATION iGF_ped
	SET_UP_CONVERSATION_NODE_WITH_SPEECH $GF_intro $GF_positive $GF_negative CONTEXT_GLOBAL_GFRIEND_INTRO CONTEXT_GLOBAL_GIVE_NUMBER_YES CONTEXT_GLOBAL_GIVE_NUMBER_NO
	SET_UP_CONVERSATION_END_NODE_WITH_SPEECH $GF_positive CONTEXT_GLOBAL_GFRIEND_POS_RESPONSE
	SET_UP_CONVERSATION_END_NODE_WITH_SPEECH $GF_negative CONTEXT_GLOBAL_GFRIEND_NEG_RESPONSE											
	FINISH_SETTING_UP_CONVERSATION 														 
	ENABLE_CONVERSATION iGF_ped FALSE
	
	ADD_BLIP_FOR_CHAR iGF_ped girl_arrow
	SET_BLIP_AS_FRIENDLY girl_arrow TRUE
	CHANGE_BLIP_DISPLAY girl_arrow MARKER_ONLY

	IF iDefaultState = 1 
		//--- Create the random person she is talking to at the moment
		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS iGF_ped 0.5 1.0 0.0 fX fY fZ
		GET_GROUND_Z_FOR_3D_COORD fX fY fZ fZ		
		CREATE_RANDOM_CHAR fX fY fZ iOther_Ped
		fParH += 180.0
		SET_CHAR_HEADING iOther_Ped fParH
		TASK_CHAT_WITH_CHAR iGF_ped iOther_ped TRUE TRUE
		TASK_CHAT_WITH_CHAR iOther_ped iGF_ped FALSE TRUE
	ENDIF

	iState = iDefaultState
	iSubStateStatus = 0

RETURN
/*******************************************
		PRINT GIRL SPECIFIC HELP
********************************************/
GF_Meeting_PrintGirlSpecificHelp:
	
	CLEAR_HELP

	SWITCH iGFidx_par
		CASE MICHELLE			
			PRINT_HELP GF_H001
		BREAK
		CASE KYLIE
			PRINT_HELP GF_H002
		BREAK
		CASE BARBARA
			PRINT_HELP GF_H003
		BREAK
		CASE SUZIE
			PRINT_HELP GF_H004
		BREAK
	ENDSWITCH

RETURN
/*******************************************
		LOAD ANIMATIONS FROM STREAM
********************************************/
GF_Meeting_SteamAnims:

	REQUEST_ANIMATION PARK

GF_Meeting_StreamLoop:
	WAIT 0

	IF HAS_ANIMATION_LOADED PARK
		RETURN
	ELSE
		REQUEST_ANIMATION PARK
		GOTO GF_Meeting_StreamLoop
	ENDIF
/*******************************************
		CHECK SHE LIKES PLAYER
********************************************/
GF_Meeting_CheckLikesPlayer:
	
	iTemp = 0

	GET_INT_STAT SEX_APPEAL iTemp
	iTemp /= 10	// Scale it between 0 and 100

	//--- Assess player's FAT status
	GET_INT_STAT FAT iTemp2
	iTemp2 /= 10 // Scale it 
	iTemp2 /= 2 // Halve it - its impact on sex appeal must be moderate anyway

		IF iTemp2 >= 25 // Player is chubby...
			IF IS_BIT_SET iGFLikesPlayerTraits[iGFidx_par] 2	// FAT
				//--- ...and she likes fat, sum this to sex appeal, otherwise detract it
				iTemp += iTemp2
			ELSE 
				//--- ...but she dislikes fat, subtract from sex appeal
				iTemp -= iTemp2
				SET_BIT iMeetingFlags 3 // ALLOWED_TO_MAKE_PHYSIQUE_COMMENT 3
				SET_BIT iMeetingFlags MEETING_PLAYER_NOT_FIT
			ENDIF
		ELSE // Player NOT chubby...
			IF IS_BIT_SET iGFLikesPlayerTraits[iGFidx_par] 2	// FAT
				//--- ... but she likes fat, detract from sex appeal
				iTemp -= iTemp2
				SET_BIT iMeetingFlags 3 // ALLOWED_TO_MAKE_PHYSIQUE_COMMENT
				SET_BIT iMeetingFlags MEETING_PLAYER_NOT_FAT   
			ELSE 
				IF IS_BIT_SET iGFLikesPlayerTraits[iGFidx_par] 3	// NORMAL
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
			IF IS_BIT_SET iGFLikesPlayerTraits[iGFidx_par] 1	// FIT
				//--- ... and she likes fit, sum this to sex appeal
				iTemp += iTemp2
			ELSE
				IF IS_BIT_SET iGFLikesPlayerTraits[iGFidx_par] 3	// NORMAL
					//--- ... but she likes skinny, too much muscle is bad
					iTemp -= iTemp2
					SET_BIT iMeetingFlags 3 // ALLOWED_TO_MAKE_PHYSIQUE_COMMENT
					SET_BIT iMeetingFlags MEETING_PLAYER_TOO_FIT
				ENDIF
			ENDIF
 		ELSE // Player is not very fit...
			IF IS_BIT_SET iGFLikesPlayerTraits[iGFidx_par] 1	// FIT
				//--- ... but she likes fit, detract this from sex appeal
				iTemp -= iTemp2
				SET_BIT iMeetingFlags 3 // ALLOWED_TO_MAKE_PHYSIQUE_COMMENT
				SET_BIT iMeetingFlags MEETING_PLAYER_NOT_FIT
			ELSE
				IF IS_BIT_SET iGFLikesPlayerTraits[iGFidx_par] 3	// NORMAL
					//--- she likes skinny, sum this to sex appeal
					IF iTemp2 = 0 // No muscle at all
						iTemp2 = 5 // Must increase to have a value to add to sex appeal
					ENDIF
					iTemp += iTemp2
				ENDIF
			ENDIF
 		ENDIF
	
	IF iTemp >= iGFDesiredSexAppeal[iGFidx_par] 
		//--- store the first value of 'like player' in the GF array 
		iGFLikesPlayer[iGFidx_par] = GF_LIKES_PLAYER_INCREMENT_MEETING 
		GOSUB GF_Meeting_SynchStats
		//--- Store the return value
		iTemp = 1
	ELSE
		//--- Store the return value
		iTemp = -1	
	ENDIF
RETURN
/********************************************
		SYNCH GIRLFRIEND STATS
********************************************/
GF_Meeting_SynchStats:
	SWITCH iGFidx_par

		CASE COOCHIE 
			IF iGFLikesPlayer[iGFidx_par] > 100
				iGFLikesPlayer[iGFidx_par] = 100
			ENDIF 
			SET_INT_STAT GIRLFRIEND_DENISE iGFLikesPlayer[iGFidx_par]
		BREAK

		CASE MICHELLE
			IF iGFLikesPlayer[iGFidx_par] > 100
				iGFLikesPlayer[iGFidx_par] = 100
			ENDIF 
			SET_INT_STAT GIRLFRIEND_MICHELLE iGFLikesPlayer[iGFidx_par]
		BREAK

		CASE KYLIE
			IF iGFLikesPlayer[iGFidx_par] > 100
				iGFLikesPlayer[iGFidx_par] = 100
			ENDIF 
			SET_INT_STAT GIRLFRIEND_HELENA iGFLikesPlayer[iGFidx_par]
		BREAK

		CASE BARBARA
			IF iGFLikesPlayer[iGFidx_par] > 100
				iGFLikesPlayer[iGFidx_par] = 100
			ENDIF 
			SET_INT_STAT GIRLFRIEND_BARBARA iGFLikesPlayer[iGFidx_par]
		BREAK

		CASE SUZIE
			IF iGFLikesPlayer[iGFidx_par] > 100
				iGFLikesPlayer[iGFidx_par] = 100
			ENDIF 
			SET_INT_STAT GIRLFRIEND_KATIE iGFLikesPlayer[iGFidx_par]
		BREAK

		CASE MILLIE
			IF iGFLikesPlayer[iGFidx_par] > 100
				iGFLikesPlayer[iGFidx_par] = 100
			ENDIF 
			SET_INT_STAT GIRLFRIEND_MILLIE iGFLikesPlayer[iGFidx_par]
		BREAK

	ENDSWITCH
RETURN
/********************************************
			SPEECH MANAGER
********************************************/
GF_Meeting_SpeechManager:

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
				 SET_CHAR_SAY_CONTEXT_IMPORTANT iGF_ped iGFSayContext FALSE TRUE FALSE iGFContextVariation
				 GOSUB GF_Meeting_RetrieveSpeechSubtitleForThisContext
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
				 GOSUB GF_Meeting_RetrieveSpeechSubtitleForThisContext
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
GF_Meeting_RetrieveSpeechSubtitleForThisContext:

	SWITCH iGFSayContext

		CASE CONTEXT_GLOBAL_GFRIEND_PHYSIQUE_CRITIQUE
			IF IS_BIT_SET iMeetingFlags MEETING_PLAYER_NOT_FAT
				PRINT_NOW GF_0074 5000 1 
				BREAK
			ENDIF	
			IF IS_BIT_SET iMeetingFlags MEETING_PLAYER_NOT_FIT
				PRINT_NOW GF_0053 5000 1 
				BREAK
			ENDIF	
			IF IS_BIT_SET iMeetingFlags MEETING_PLAYER_TOO_FIT
				PRINT_NOW GF_0075 5000 1 
				BREAK
			ENDIF	
		BREAK
		CASE CONTEXT_GLOBAL_GFRIEND_SEX_APPEAL_TOO_LOW
			PRINT_NOW GF_0052 5000 1 
		BREAK

	ENDSWITCH

RETURN
/*******************************************
				CLEAN UP 
********************************************/
GF_Meeting_Cleanup:
	
	MARK_CHAR_AS_NO_LONGER_NEEDED iGF_ped
	MARK_CHAR_AS_NO_LONGER_NEEDED iOther_ped
	REMOVE_ANIMATION PARK
		
	CLEAR_HELP

	SWITCH iGFidx_par
		CASE COOCHIE 
			MARK_MODEL_AS_NO_LONGER_NEEDED GANGRL3
		BREAK

		CASE MICHELLE
			MARK_MODEL_AS_NO_LONGER_NEEDED MECGRL3
		BREAK

		CASE KYLIE
			MARK_MODEL_AS_NO_LONGER_NEEDED GUNGRL3
		BREAK

		CASE BARBARA
			MARK_MODEL_AS_NO_LONGER_NEEDED COPGRL3
		BREAK
		
		CASE SUZIE
			MARK_MODEL_AS_NO_LONGER_NEEDED NURGRL3
		BREAK

		CASE MILLIE
			MARK_MODEL_AS_NO_LONGER_NEEDED CROGRL3	 
		BREAK
	ENDSWITCH  	

	CLEAR_CONVERSATION_FOR_CHAR iGF_ped

	IF DOES_BLIP_EXIST girl_arrow 
   		REMOVE_BLIP girl_arrow
	ENDIF

	CLEAR_BIT iDateReport MEETING_IN_PROGRESS

	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
				RUN DEBUG
********************************************/
GF_Meeting_Run_Debug:

	WRITE_DEBUG I__________________________I
	WRITE_DEBUG H 		   
	WRITE_DEBUG G
	WRITE_DEBUG F
	WRITE_DEBUG E
	WRITE_DEBUG_WITH_INT GF_LIKES_PLAYER iGFLikesPlayer[iGFidx_par] 
	WRITE_DEBUG_WITH_INT DESIRED_SEX_APPEAL iGFDesiredSexAppeal[iGFidx_par]
	WRITE_DEBUG_WITH_INT iSubStateStatus iSubStateStatus 
	
	SWITCH iState
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
