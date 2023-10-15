MISSION_START  
	SCRIPT_NAME APCHECK
	
/*********************************************
			GLOBAL VARIABLES
**********************************************/
VAR_INT	iAPgateSF1 iAPgateSF2 iAPgateSF1b iAPgateSF2b iAPgateLA1 iAPgateLA2 iAPgateLV1 iAPgateLV2 
VAR_INT iFlagOpenAirportGates
// LOCATE CONSTANTS 
CONST_FLOAT LAx 	1968.697  
CONST_FLOAT LAy 	-2189.776 
CONST_FLOAT LAz 	13.553
CONST_FLOAT SFx		-1540.66 
CONST_FLOAT SFy		-435.786 
CONST_FLOAT SFz		6.039
CONST_FLOAT SFbx	-1226.1616 
CONST_FLOAT SFby	65.3807 
CONST_FLOAT SFbz	13.0375
CONST_FLOAT LVx 	1703.4 
CONST_FLOAT LVy 	1600.518 
CONST_FLOAT LVz 	10.058
/*********************************************
			AIRPORT GATES INIT
**********************************************/
//--- Set the deatharrest OFF since we are doing a loop in this script
SET_DEATHARREST_STATE OFF

//--- This is initialised as false, but missions can change it
iFlagOpenAirportGates = 0

//--- San Fierro Airport
CREATE_OBJECT_NO_OFFSET ws_apgate -1543.742 -432.703 6.039 iAPgateSF1
SET_OBJECT_HEADING iAPgateSF1 -45.0
DONT_REMOVE_OBJECT iAPgateSF1

CREATE_OBJECT_NO_OFFSET ws_apgate -1547.625 -428.82 6.039 iAPgateSF2
SET_OBJECT_HEADING iAPgateSF2 -45.0
DONT_REMOVE_OBJECT iAPgateSF2

CREATE_OBJECT_NO_OFFSET ws_apgate -1222.953 53.826 14.134 iAPgateSF1b
SET_OBJECT_HEADING iAPgateSF1b -135.0
DONT_REMOVE_OBJECT iAPgateSF1b

CREATE_OBJECT_NO_OFFSET ws_apgate -1218.206 68.883 14.134 iAPgateSF2b
SET_OBJECT_HEADING iAPgateSF2b -135.0
DONT_REMOVE_OBJECT iAPgateSF2b

//--- Los Santos Airport
CREATE_OBJECT_NO_OFFSET ws_apgate 1964.342 -2189.776 13.533  iAPgateLA1
SET_OBJECT_HEADING iAPgateLA1 180.0
DONT_REMOVE_OBJECT iAPgateLA1

CREATE_OBJECT_NO_OFFSET ws_apgate 1958.851 -2189.777 13.553 iAPgateLA2
SET_OBJECT_HEADING iAPgateLA2 180.0
DONT_REMOVE_OBJECT iAPgateLA2

//--- Vegas Airport
CREATE_OBJECT_NO_OFFSET ws_apgate 1704.777 1605.165 10.058 iAPgateLV1
SET_OBJECT_HEADING iAPgateLV1 73.0
DONT_REMOVE_OBJECT iAPgateLV1

CREATE_OBJECT_NO_OFFSET ws_apgate 1706.364 1610.422 10.058 iAPgateLV2
SET_OBJECT_HEADING iAPgateLV2 73.0  
DONT_REMOVE_OBJECT iAPgateLV2

/*********************************************
	AIRPORT SECURITY MEMORY RESIDENT MAIN
**********************************************/
AP_PlayerCheck_Loop:

WAIT 0

	IF IS_PLAYER_PLAYING PLAYER1

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer SFx SFy SFz  65.0 65.0 10.0 FALSE					
			IF flag_is_farlie3_running = 1
				//--- If Kevin W. mission is running the airport has been raided...								
				iFlagOpenAirportGates = 1 // Mark the gates as open	
				START_NEW_SCRIPT Airport_Guard -1 // The ped is dead and outside the door
				SET_OBJECT_COORDINATES iAPgateSF1 -1540.66 -435.786 6.039
				SET_OBJECT_COORDINATES iAPgateSF2 -1550.709 -425.736 6.039
			ELSE
				//--- The airport operates as normal
				START_NEW_SCRIPT Airport_Guard 1			
				START_NEW_SCRIPT Airport_Gate iAPgateSF1 -1540.66 -435.786 6.039
				START_NEW_SCRIPT Airport_Gate iAPgateSF2 -1550.709 -425.736 6.039
			ENDIF
			GOTO AP_PlayerCheck_WaitSF			
		ENDIF

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer LAx LAy LAz 65.0 65.0 10.0 FALSE
			START_NEW_SCRIPT Airport_Guard 2
			START_NEW_SCRIPT Airport_Gate iAPgateLA1 1968.697 -2189.776 13.553
			START_NEW_SCRIPT Airport_Gate iAPgateLA2 1954.571 -2189.777 13.553
			GOTO AP_PlayerCheck_WaitLA			
		ENDIF

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer LVx LVy LVz 65.0 65.0 10.0 FALSE
			START_NEW_SCRIPT Airport_Guard 3
			START_NEW_SCRIPT Airport_Gate iAPgateLV1 1703.4 1600.518 10.058			
			START_NEW_SCRIPT Airport_Gate iAPgateLV2 1707.722 1614.937 10.058 
			GOTO AP_PlayerCheck_WaitLV			
		ENDIF

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer SFbx SFby SFbz  65.0 65.0 10.0 FALSE					
			START_NEW_SCRIPT Airport_Guard 4			
			START_NEW_SCRIPT Airport_Gate iAPgateSF1b -1226.59 50.189 14.134 
			START_NEW_SCRIPT Airport_Gate iAPgateSF2b -1214.392 72.697 14.134
			GOTO AP_PlayerCheck_WaitSFb			
		ENDIF
	ELSE		
		//--- Player not playing. Reset everything.  
		TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME APGATE
		SET_OBJECT_COORDINATES iAPgateSF1 -1543.742 -432.703 6.039
		SET_OBJECT_COORDINATES iAPgateSF2 -1547.625 -428.82 6.039
		SET_OBJECT_COORDINATES iAPgateSF1b -1222.953 53.826 14.134
		SET_OBJECT_COORDINATES iAPgateSF2b -1218.206 68.883 14.134
		SET_OBJECT_COORDINATES iAPgateLA1 1964.342 -2189.776 13.533
		SET_OBJECT_COORDINATES iAPgateLA2 1958.851 -2189.777 13.553						
		SET_OBJECT_COORDINATES iAPgateLV1 1704.777 1605.165 10.058
		SET_OBJECT_COORDINATES iAPgateLV2 1706.364 1610.422 10.058
		iFlagOpenAirportGates = 0
		GOTO AP_PlayerCheck_Loop
	ENDIF

GOTO AP_PlayerCheck_Loop

/*********************************************
				 WAIT SF
**********************************************/

AP_PlayerCheck_WaitSF:

WAIT 0

	IF IS_PLAYER_PLAYING PLAYER1
		IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer SFx SFy SFz 66.0 66.0 10.0 FALSE
			//--- RESET	THE GATES
		 	IF flag_player_on_mission = 0 
				TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME APGATE
				SET_OBJECT_COORDINATES iAPgateSF1 -1543.742 -432.703 6.039
				SET_OBJECT_COORDINATES iAPgateSF2 -1547.625 -428.82 6.039			
				iFlagOpenAirportGates = 0
				GOTO AP_PlayerCheck_Loop
			ENDIF
		ENDIF
	ELSE
		TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME APGATE
		SET_OBJECT_COORDINATES iAPgateSF1 -1543.742 -432.703 6.039
		SET_OBJECT_COORDINATES iAPgateSF2 -1547.625 -428.82 6.039			
		iFlagOpenAirportGates = 0
		GOTO AP_PlayerCheck_Loop
	ENDIF

GOTO AP_PlayerCheck_WaitSF		
/*********************************************
				 WAIT SFb
**********************************************/

AP_PlayerCheck_WaitSFb:

WAIT 0

	IF IS_PLAYER_PLAYING PLAYER1
		IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer SFbx SFby SFbz 66.0 66.0 10.0 FALSE
			//--- RESET	THE GATES
		 	IF flag_player_on_mission = 0 
				TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME APGATE
				SET_OBJECT_COORDINATES iAPgateSF1b -1222.953 53.826 14.134
				SET_OBJECT_COORDINATES iAPgateSF2b -1218.206 68.883 14.134
				iFlagOpenAirportGates = 0
				GOTO AP_PlayerCheck_Loop
			ENDIF
		ENDIF
	ELSE
		TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME APGATE
		SET_OBJECT_COORDINATES iAPgateSF1b -1222.953 53.826 14.134
		SET_OBJECT_COORDINATES iAPgateSF2b -1218.206 68.883 14.134
		iFlagOpenAirportGates = 0
		GOTO AP_PlayerCheck_Loop	
	ENDIF

GOTO AP_PlayerCheck_WaitSFb					 
/*********************************************
				 WAIT LA
**********************************************/	 
AP_PlayerCheck_WaitLA:

WAIT 0

	IF IS_PLAYER_PLAYING PLAYER1
		IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer LAx LAy LAz 66.0 66.0 10.0 FALSE
			//--- RESET	THE GATES
		 	IF flag_player_on_mission = 0 
				TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME APGATE
				SET_OBJECT_COORDINATES iAPgateLA1 1964.342 -2189.776 13.533
				SET_OBJECT_COORDINATES iAPgateLA2 1958.851 -2189.777 13.553						
				iFlagOpenAirportGates = 0
				GOTO AP_PlayerCheck_Loop			
			ENDIF
		ENDIF
	ELSE
		TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME APGATE
		SET_OBJECT_COORDINATES iAPgateLA1 1964.342 -2189.776 13.533
		SET_OBJECT_COORDINATES iAPgateLA2 1958.851 -2189.777 13.553						
		iFlagOpenAirportGates = 0
		GOTO AP_PlayerCheck_Loop			
	ENDIF

GOTO AP_PlayerCheck_WaitLA
/*********************************************
				 WAIT VEGAS
**********************************************/	 
AP_PlayerCheck_WaitLV:

WAIT 0

	IF IS_PLAYER_PLAYING PLAYER1
		IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer LVx LVy LVz 66.0 66.0 10.0 FALSE
			//--- RESET	THE GATES
		 	IF flag_player_on_mission = 0 
				TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME APGATE
				SET_OBJECT_COORDINATES iAPgateLV1 1704.777 1605.165 10.058
				SET_OBJECT_COORDINATES iAPgateLV2 1706.364 1610.422 10.058
				iFlagOpenAirportGates = 0
				GOTO AP_PlayerCheck_Loop			
			ENDIF
		ENDIF
	ELSE
		TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME APGATE
		SET_OBJECT_COORDINATES iAPgateLV1 1704.777 1605.165 10.058
		SET_OBJECT_COORDINATES iAPgateLV2 1706.364 1610.422 10.058
		iFlagOpenAirportGates = 0
		GOTO AP_PlayerCheck_Loop			
	ENDIF

GOTO AP_PlayerCheck_WaitLV

MISSION_END


/*****************************************************************************************************************************************
***********************************************		AIRPORT SECURITY	*******************************************************************
*****************************************************************************************************************************************/
{
Airport_Guard:	  
    SCRIPT_NAME APGUARD

	//--- PARAMETERS
	LVAR_INT iZoneIn // If this is -1 this ped is dead in SF	 	

	//--- VARS
	LVAR_INT iAPguard iTemp iHasSpoken

	//--- VAR init
	iHasSpoken = 0

	//--- Steraming Request Loop	
	WHILE NOT HAS_MODEL_LOADED WMYSGRD
		REQUEST_MODEL WMYSGRD
		WAIT 0
	ENDWHILE

	//--- Find Out the current zone we are in
	SWITCH iZoneIn
		CASE -1 
			//--- Init the guy as dead	
			CREATE_CHAR	PEDTYPE_CIVMALE WMYSGRD -1544.8795 -441.1089 5.0068 iAPguard
			SET_CHAR_HEADING iAPguard  7.3631
			SET_CHAR_MONEY iAPguard 0
			DONT_REMOVE_CHAR iAPguard
			TASK_DEAD iAPguard 			
		BREAK 

		CASE 1 
			//--- Init the guy according to the current zone	
			CREATE_CHAR	PEDTYPE_CIVMALE WMYSGRD -1544.3962 -443.2464 5.045 iAPguard
			SET_CHAR_HEADING iAPguard  54.6458
		BREAK 	

		CASE 2 
			//--- Init the guy according to the current zone	
			CREATE_CHAR	PEDTYPE_CIVMALE WMYSGRD 1955.8087 -2181.5405 12.5865 iAPguard
			SET_CHAR_HEADING iAPguard 272.1978
		BREAK
		 
		CASE 3
			//--- Init the guy according to the current zone	
			CREATE_CHAR	PEDTYPE_CIVMALE WMYSGRD 1717.2017 1617.3760 9.1924 iAPguard
			SET_CHAR_HEADING iAPguard  186.0379
		BREAK

		CASE 4
			//--- Init the guy according to the current zone	
			CREATE_CHAR	PEDTYPE_CIVMALE WMYSGRD -1229.4380 55.3906 13.2328 iAPguard
			SET_CHAR_HEADING iAPguard  301.4596
		BREAK 	
											    
	ENDSWITCH

	//--- Set Up the Decision Maker for this guy (considering he is trapped inside a hut!)
	SET_CHAR_DECISION_MAKER iAPguard DM_PED_INDOORS
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER iAPguard TRUE
	DONT_REMOVE_CHAR iAPguard	
																 
//---  Main Loop 
Airport_Guard_LoopCheck:

WAIT 0
		
	IF IS_PLAYER_PLAYING PLAYER1
		IF NOT IS_CHAR_DEAD iAPguard
			//--- Check if it's time to clear up
			IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer iAPguard 67.0 67.0 10.0 FALSE   
				GOSUB Airport_Guard_CleanUp
			ENDIF			
			//--- If player is near the guy
			IF NOT IS_CHAR_DEAD	iAPguard
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer iAPguard 15.0 15.0 15.0 FALSE
					GOSUB Airport_Guard_SpeakIfNeeded
				ENDIF
			ENDIF
			//--- If player has hit the guy
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR iAPguard scplayer
				SET_CHAR_RELATIONSHIP iAPguard ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			ENDIF
		ELSE
			GOSUB Airport_Guard_CleanUp
		ENDIF
	ELSE
		GOSUB Airport_Guard_CleanUp
	ENDIF

GOTO Airport_Guard_LoopCheck	
//--- End Of Main

/*******************************************
			SPEAK IF NEEDED
********************************************/
Airport_Guard_SpeakIfNeeded:

	IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer iAPguard 5.0 5.0 3.0 FALSE
		GET_INT_STAT FLYING_SKILL iTemp
		IF NOT IS_CHAR_RESPONDING_TO_EVENT iAPguard EVENT_ACQUAINTANCE_PED_HATE
		AND iTemp >= FLYING_SKILL_REQUIRED_FOR_PILOT_LICENCE
			IF iHasSpoken = 0 
				//--- You have the skill
				GENERATE_RANDOM_INT_IN_RANGE 1 100 iHasSpoken
				IF iHasSpoken > 50 
					$audio_string = &G_OPEN1
					audio_sound_file = SOUND_G_OPEN1
				ELSE
					$audio_string = &G_OPEN2
					audio_sound_file = SOUND_G_OPEN2
				ENDIF
				START_NEW_SCRIPT audio_line iAPguard 1 1 2 0 //They’ll give a pilot license to anybody these days!
				iHasSpoken = 1
				RETURN
			ENDIF
		ELSE
			IF iHasSpoken = 0
				//--- You DO NOT have the skill
				GENERATE_RANDOM_INT_IN_RANGE 1 100 iHasSpoken
				IF iHasSpoken > 50 
					$audio_string = &G_CLOS1
					audio_sound_file = SOUND_G_CLOS1
				ELSE
					$audio_string = &G_CLOS2
					audio_sound_file = SOUND_G_CLOS2
				ENDIF
				START_NEW_SCRIPT audio_line iAPguard 1 1 2 0 //This area is restricted to pilots only!
				iHasSpoken = 1
				RETURN
			ENDIF
		ENDIF
	ENDIF

RETURN
/*******************************************
			CLEAN UP GUARD
********************************************/
Airport_Guard_CleanUp:
	IF IS_PLAYER_PLAYING PLAYER1
		IF flag_player_on_mission = 0 // Don't do anything if the player is playing a mission - it might use this happy chap
			DELETE_CHAR iAPguard 
			MARK_MODEL_AS_NO_LONGER_NEEDED WMYSGRD			
			TERMINATE_THIS_SCRIPT		
		ENDIF
	ELSE
		DELETE_CHAR iAPguard 
		MARK_MODEL_AS_NO_LONGER_NEEDED WMYSGRD
		TERMINATE_THIS_SCRIPT		
	ENDIF
RETURN
}


/*****************************************************************************************************************************************
************************************************	AIRPORT GATE	**********************************************************************
*****************************************************************************************************************************************/
{
Airport_Gate:	  
    SCRIPT_NAME APGATE
   	
	//--- PARAMETERS 
	LVAR_INT this_gate 
	LVAR_FLOAT fAP_targetX fAP_targetY fAP_targetZ

	//--- VARS	
    LVAR_INT iTemp iGateState iSubStateStatus 	// State Machine variables	
	LVAR_INT iAPguard iDM iSoundIdx iFlyingSkill
	LVAR_FLOAT fX[3] fY[3] fZ[3]

	iGateState = 0
	iSubStateStatus = 0
	iTemp = 0

	//--- Parameter Passing Fudge
	IF iTemp > 0 
		CREATE_OBJECT_NO_OFFSET Ws_apgate 0.0 0.0 0.0 this_gate
	ENDIF
	

	//---MAIN LOOP---
Airport_Gate_Main_Loop:

	WAIT 0

	//--- Do all the checks and then run the state machine
 	IF DOES_OBJECT_EXIST this_gate
 	AND IS_PLAYER_PLAYING PLAYER1  
	   GOSUB Airport_Gate_State_Machine						   
	ENDIF

GOTO Airport_Gate_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
Airport_Gate_State_Machine:
	SWITCH iGateState	   	
   		CASE 0 //---STATE 1: 
			GOSUB GateState0
		BREAK
	ENDSWITCH

RETURN
/********************************************
				STATE 0
********************************************/
GateState0:  
	SWITCH iSubStateStatus	 				

	CASE 0
	    GET_INT_STAT FLYING_SKILL iFlyingSkill
		IF iFlyingSkill >= FLYING_SKILL_REQUIRED_FOR_PILOT_LICENCE
			//--- Player has the pilot licence, open the gate
			IF LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer this_gate 10.0 10.0 FALSE
				GET_OBJECT_COORDINATES this_gate fX[0] fY[0] fZ[0]				
				IF HAS_MISSION_AUDIO_LOADED 3
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT this_gate SOUND_MESH_GATE_OPEN_START
				ENDIF
				++iSubStateStatus
			ENDIF
		ELSE  
			//-- No pilot licence
			IF iFlagOpenAirportGates > 0 
				//--- If the gates are ordered to open by an external entity
				IF LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer this_gate 10.0 10.0 FALSE
					GET_OBJECT_COORDINATES this_gate fX[0] fY[0] fZ[0]				
					IF HAS_MISSION_AUDIO_LOADED 3
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT this_gate SOUND_MESH_GATE_OPEN_START
					ENDIF
					++iSubStateStatus
				ENDIF
			ELSE // GATES SHOULD STAY CLOSED
				//--- Check if the player is stuck on the other side
				GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS this_gate 0.0 5.2 0.0 fX[0] fY[0] fZ[0]  
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer fX[0] fY[0] fZ[0] 4.5 4.5 4.5 FALSE
					//--- Player SOMEHOW has ended on the other side, so the gate must open anyway
					GET_OBJECT_COORDINATES this_gate fX[0] fY[0] fZ[0]
					IF HAS_MISSION_AUDIO_LOADED 3
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT this_gate SOUND_MESH_GATE_OPEN_START
					ENDIF
					++iSubStateStatus				
				ELSE
					//--- Check if we need to print help
					GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS this_gate 0.0 -3.0 0.0 fX[0] fY[0] fZ[0]  				    
					IF LOCATE_CHAR_ANY_MEANS_3D scplayer fX[0] fY[0] fZ[0] 3.0 3.0 3.0 FALSE 
					AND iFlyingSkill < FLYING_SKILL_REQUIRED_FOR_PILOT_LICENCE
					AND NOT IS_THIS_HELP_MESSAGE_BEING_DISPLAYED AP_0003
						PRINT_HELP AP_0003 // help text directing player to pilot school 
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	BREAK

	CASE 1
		GOSUB Airport_Gate_GetSlideStepsForHeading // Returns fX[2],fY[2],fZ[2]
		IF SLIDE_OBJECT this_gate fAP_targetX fAP_targetY fAP_targetZ fX[2] fY[2] fZ[2] TRUE
			//--- Is this a collision or has the object reached the target?
			IF LOCATE_OBJECT_3D this_gate fAP_targetX fAP_targetY fAP_targetZ 0.1 0.1 0.1 FALSE
				//--- Target reached
				SET_OBJECT_COORDINATES this_gate fAP_targetX fAP_targetY fAP_targetZ 
				IF HAS_MISSION_AUDIO_LOADED 3
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT this_gate SOUND_MESH_GATE_OPEN_STOP
				ENDIF
				++iSubStateStatus
			ENDIF
		ENDIF
	BREAK

   	CASE 2
		//--- Now see if the player has moved past the gate
		IF NOT LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer this_gate 20.0 20.0 FALSE
			//-- YES, start closing it
		 	GET_OBJECT_COORDINATES this_gate fX[1] fY[1] fZ[1]
			IF HAS_MISSION_AUDIO_LOADED 3
				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT this_gate SOUND_MESH_GATE_OPEN_START
			ENDIF
			++iSubStateStatus
		ENDIF
	BREAK
	
	CASE 3
		GOSUB Airport_Gate_GetSlideStepsForHeading // Returns fX[2],fY[2],fZ[2]
		IF SLIDE_OBJECT this_gate fX[0] fY[0] fZ[0] fX[2] fY[2] fZ[2] TRUE
			//--- Is this a collision or has the object reached the target?
			IF LOCATE_OBJECT_3D this_gate fX[0] fY[0] fZ[0] 0.1 0.1 0.1 FALSE
				//--- Target reached
				SET_OBJECT_COORDINATES this_gate fX[0] fY[0] fZ[0] 
				IF HAS_MISSION_AUDIO_LOADED 3
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT this_gate SOUND_MESH_GATE_OPEN_STOP
				ENDIF
				//--- Loop from SubState 0
				iSubStateStatus = 0
			ENDIF
		ENDIF
	BREAK

	ENDSWITCH

RETURN	
/********************************************
	GET SLIDE STEPS FOR GATE'S HEADING
********************************************/
Airport_Gate_GetSlideStepsForHeading:

GET_OBJECT_HEADING this_gate fX[2] 			
IF fX[2] > 70.0
AND fX[2] < 75.0
	fX[2] = 0.03 
	fY[2] = 0.1 
	fZ[2] = 0.1
ELSE 
	fX[2] = 0.1
	fY[2] = 0.1 
	fZ[2] = 0.1
ENDIF 	
RETURN
}



	   