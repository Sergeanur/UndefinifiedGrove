MISSION_START
CONST_INT MAX_GANG_IN_PHOTO 		3
CONST_INT CAMERA_TIME_STEP_SLOW		500	

CONST_FLOAT MIN_DISTANCE_IN_SHOT	3.0
CONST_FLOAT INCREMENT_TO_FIT_SHOT	0.2
CONST_FLOAT MAX_DISTANCE_IN_SHOT	6.0

/*********************************************************************************************************************************************
************************************************						**********************************************************************
************************************************ 		  CAMERA		**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
Camera:
    SCRIPT_NAME CAMERA

	LVAR_INT iState iPictureTakingState iBePhotographedState iCameraVariableStep iHavePrintedHelp iCode	
	LVAR_INT iTemp iNumOfFollowers iGangGuy iTaskStatus iTemp2 player_is_completely_safe_for_camera
	LVAR_FLOAT fAngle[MAX_GANG_IN_PHOTO] fDistance fMinX fMinY fMinZ fMaxX fMaxY fMaxZ
    IF iState = -100
        CREATE_CHAR PEDTYPE_CIVMALE 0 0.0 0.0 0.0 iGangGuy
    ENDIF
	VAR_TEXT_LABEL16 txtArse

	iCameraVariableStep = 0
	iBePhotographedState = 0
	iPictureTakingState = 0

	//--- INITLIALISE THE ANGLES TABLE:
    fAngle[0] = 0.0
	fAngle[1] = 25.0
	fAngle[2] = -25.0

	SET_PHOTO_CAMERA_EFFECT	FALSE

	//---MAIN LOOP---
Camera_Main_Loop:

	WAIT iCameraVariableStep
		
	IF IS_PLAYER_PLAYING PLAYER1		
		IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_CAMERA			
			GOSUB Camera_Check_Player_Is_Safe
			IF player_is_completely_safe_for_camera = 1
				IF IS_CHAR_STOPPED scplayer 
					IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_CAMERA			
						
						IF IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER1
							
							IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED CAM_001  
								CLEAR_HELP 
							ENDIF
							IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED CAM_002
								CLEAR_HELP 
							ENDIF
							iCameraVariableStep = 0 // speed up the time step
							iBePhotographedState = 0
							iHavePrintedHelp = 0
							//--- Player is about to take a picture 
							GOSUB Camera_PlayerTakingPicture			
						ELSE					
							//--- Player is not using camera...
							iPictureTakingState = 0
							//--- Check if player is approaching someone to have a picture taken, but not a girl					 											
							IF NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS 
								GOSUB Camera_BePhotographed			
							ENDIF
							//--- Check if we need to run on a faster time step
							IF iBePhotographedState > 0
								iCameraVariableStep = 0 
							ELSE
								iCameraVariableStep = CAMERA_TIME_STEP_SLOW 
							ENDIF
						ENDIF
					ELSE
						//--- Camera is not the current weapon...
						IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED CAM_001
							CLEAR_HELP 
						ENDIF
						IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED CAM_002
							CLEAR_HELP 
						ENDIF
						SET_PHOTO_CAMERA_EFFECT	FALSE
						iCameraVariableStep = CAMERA_TIME_STEP_SLOW
						iPictureTakingState = 0
						iBePhotographedState = 0
						iHavePrintedHelp = 0
					ENDIF
				ELSE
					//--- Player is not standing still...
					IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED CAM_001
						CLEAR_HELP 
					ENDIF
					IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED CAM_002
						CLEAR_HELP 
					ENDIF
					iCameraVariableStep = CAMERA_TIME_STEP_SLOW
					iPictureTakingState = 0
					iBePhotographedState = 0
					iHavePrintedHelp = 0
				ENDIF
			ELSE
				//--- Player has camera but is not safe...
				IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED CAM_001
					CLEAR_HELP
				ENDIF
				IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED CAM_002
					CLEAR_HELP 
				ENDIF
				SET_PHOTO_CAMERA_EFFECT	FALSE
				iCameraVariableStep = CAMERA_TIME_STEP_SLOW
				iPictureTakingState = 0
				iBePhotographedState = 0
				iHavePrintedHelp = 0
				IF iBePhotographedState >= 3
					IF NOT IS_CHAR_DEAD iGangGuy					 				
						FREEZE_CHAR_POSITION iGangGuy FALSE
						SET_CHAR_VISIBLE iGangGuy TRUE						
						IF flag_player_on_mission = 0 
						AND iCode = 0 
							SET_CAMERA_BEHIND_PLAYER
							RESTORE_CAMERA_JUMPCUT
						ENDIF						
					ENDIF
				ENDIF
			ENDIF
		ELSE
			//--- No camera in inventory...
			IF iBePhotographedState >= 3  
				//--- Call this to keep the camera in memory
				REQUEST_MODEL CAMERA
				iCameraVariableStep = 0 
				GOSUB Camera_Check_Player_Is_Safe
				IF player_is_completely_safe_for_camera = 1
					//--- Player has handed his camera to one of his guys
					GOSUB Camera_BePhotographed
				ELSE
					GOSUB Camera_CleanUp
				ENDIF
			ELSE
				//--- Player DEFINITELY has lost or swapped the camera
				GOSUB Camera_CleanUp
			ENDIF
		ENDIF
	ELSE
		//--- Player not playing...
		GOSUB Camera_CleanUp
	ENDIF

GOTO Camera_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
		   PLAYER TAKING PICTURE
********************************************/
Camera_PlayerTakingPicture:  
	SWITCH iPictureTakingState

	CASE 0		
		//--- Find if the player has people in his group
		GET_GROUP_SIZE players_group iTemp iNumOfFollowers
		IF iNumOfFollowers > 0

			//--- Check bounds of array
			IF iNumOfFollowers > MAX_GANG_IN_PHOTO
				iNumOfFollowers = MAX_GANG_IN_PHOTO				
			ENDIF
						 
			//--- Initialise the variables used in the next state
			iTemp = 0			
			--iNumOfFollowers // we scan the array starting from 0, so 1 unit less			
			fDistance = MIN_DISTANCE_IN_SHOT + INCREMENT_TO_FIT_SHOT

			++iPictureTakingState
		ENDIF 
	BREAK

	CASE 1
		
		//--- Veryfy that there is ground in front of the player - STEP 1: close by
 		GET_CHAR_COORDINATES scplayer fMaxX fMaxY fMaxZ
 		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 MIN_DISTANCE_IN_SHOT 0.0 fMinX fMinY fMinZ
		GET_GROUND_Z_FOR_3D_COORD fMinX fMinY fMinZ fMinZ		
		//--- Check if player and point to photograph are on the same level 
		fMaxZ -= fMinZ		 
		IF fMaxZ > 2.5 
		OR fMaxZ < -2.5 
			//--- Delta is too big
			iPictureTakingState = -1
			BREAK
		ENDIF
		//--- Veryfy that there is ground in front of the player - STEP 2: far away		
 		GET_CHAR_COORDINATES scplayer fMaxX fMaxY fMaxZ
 		GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 MAX_DISTANCE_IN_SHOT 0.0 fMinX fMinY fMinZ
		GET_GROUND_Z_FOR_3D_COORD fMinX fMinY fMinZ fMinZ		
		//--- Check if player and point to photograph are on the same level 
		fMaxZ -= fMinZ		 
		IF fMaxZ > 2.5 
		OR fMaxZ < -2.5 
			//--- Delta is too big
			iPictureTakingState = -1
			BREAK
		ENDIF

		//--- Verify there are no flames in front of the player
	    GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.5 0.0 0.0 fMinX fMinY fMinZ
	    GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -0.5 0.0 0.0 fMaxX fMaxY fMaxZ
		IF NOT IS_FLAME_IN_ANGLED_AREA_2D fMinX fMinY fMaxX fMaxY 7.0 FALSE
			//--- Start taking the picture
			SET_CHAR_SAY_CONTEXT scplayer CONTEXT_GLOBAL_PHOTO_CHEESE iTemp
			iTemp = 0
			++iPictureTakingState
		ELSE
			iPictureTakingState = -1
			BREAK
		ENDIF
	BREAK

	CASE 2
		IF iTemp > iNumOfFollowers				 			   
			iTemp = 0
			iTemp2 = 0
			++iPictureTakingState
		ELSE
			GET_GROUP_MEMBER players_group iTemp iGangGuy
			IF NOT IS_CHAR_DEAD iGangGuy
				IF NOT IS_CHAR_RESPONDING_TO_EVENT iGangGuy EVENT_ACQUAINTANCE_PED_HATE
				AND NOT IS_CHAR_RESPONDING_TO_EVENT iGangGuy EVENT_ACQUAINTANCE_PED_DISLIKE
				AND NOT IS_CHAR_SHOOTING iGangGuy 
					IF iTemp < MAX_GANG_IN_PHOTO 
						TASK_GOTO_CHAR_OFFSET iGangGuy scplayer 20000 fDistance fAngle[iTemp]
					ELSE
						TASK_GOTO_CHAR_OFFSET iGangGuy scplayer 20000 fDistance fAngle[0]
					ENDIF
				ENDIF
			ENDIF			
			++iTemp // move on
		ENDIF
	BREAK

	CASE 3
		IF iTemp > iNumOfFollowers				 			   			
			iTemp = 0
			IF iTemp2 > iNumOfFollowers
				iTemp = 0
				iTemp2 = 0
				++iPictureTakingState
			ENDIF
		ELSE
			GET_GROUP_MEMBER players_group iTemp iGangGuy
			IF NOT IS_CHAR_DEAD iGangGuy
				IF NOT IS_CHAR_RESPONDING_TO_EVENT iGangGuy EVENT_ACQUAINTANCE_PED_HATE
				AND NOT IS_CHAR_RESPONDING_TO_EVENT iGangGuy EVENT_ACQUAINTANCE_PED_DISLIKE
				AND NOT IS_CHAR_SHOOTING iGangGuy  
					GET_SCRIPT_TASK_STATUS iGangGuy TASK_GOTO_CHAR_OFFSET iTaskStatus  				
					IF iTaskStatus = FINISHED_TASK
						TASK_STAND_STILL iGangGuy 5000 																						
						++iTemp2 // mark this guy as done
					ENDIF
			   	ENDIF			
			ENDIF	
			++iTemp // move on				
		ENDIF
	BREAK

	CASE 4
		IF iTemp > iNumOfFollowers				 			   
			iTemp = 0
			iPictureTakingState = -1
		ELSE
			GET_GROUP_MEMBER players_group iTemp iGangGuy
			IF NOT IS_CHAR_DEAD iGangGuy				  				
				IF IS_CHAR_ON_SCREEN iGangGuy
					IF IS_CHAR_MODEL iGangGuy GANGRL3 
					OR IS_CHAR_MODEL iGangGuy MECGRL3
					OR IS_CHAR_MODEL iGangGuy GUNGRL3
					OR IS_CHAR_MODEL iGangGuy COPGRL3
					OR IS_CHAR_MODEL iGangGuy NURGRL3
					OR IS_CHAR_MODEL iGangGuy CROGRL3  
						IF HAS_ANIMATION_LOADED KISSING
							TASK_PLAY_ANIM iGangGuy GFWAVE2 KISSING 4.0 FALSE FALSE FALSE FALSE 0
						ENDIF
					ELSE					
						TASK_HAND_GESTURE iGangGuy scplayer
					ENDIF
				ENDIF								
			ENDIF									
			++iTemp // move on
		ENDIF
	BREAK

	ENDSWITCH

RETURN
/*******************************************
			   BE PHOTOGRAPHED
********************************************/
Camera_BePhotographed:
SWITCH iBePhotographedState

	CASE 0
		//--- Find if the player has people in his group		
		GET_GROUP_SIZE players_group iTemp iNumOfFollowers
		IF iNumOfFollowers > 0
			//--- Initialise the variables used in the next state
			iTemp = 0			
			--iNumOfFollowers			
			++iBePhotographedState
			iCameraVariableStep = 0
		ENDIF 
	BREAK

	CASE 1
		IF iTemp > iNumOfFollowers				 			   
			iTemp = 0
		ELSE
			GET_GROUP_MEMBER players_group iTemp iGangGuy
			IF NOT IS_CHAR_DEAD iGangGuy
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer iGangGuy 1.5 1.5 1.5 FALSE
					IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
					AND iHavePrintedHelp = 0
						PRINT_HELP CAM_001  
						iHavePrintedHelp = 1
					ENDIF
					++iBePhotographedState
				ENDIF
			ENDIF			
			++iTemp // move on to another guy
		ENDIF
	BREAK

	CASE 2
		IF NOT IS_CHAR_DEAD iGangGuy
		AND IS_GROUP_MEMBER iGangGuy players_group
			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer iGangGuy 2.5 2.5 2.5 FALSE
			AND IS_CHAR_ON_FOOT scplayer 
				
				IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
                 	SET_CHAR_SAY_CONTEXT scplayer CONTEXT_GLOBAL_PHOTO_CARL iTemp
					++iBePhotographedState
				ENDIF
			ELSE
				iBePhotographedState = 0
			ENDIF
		ELSE
		    iBePhotographedState = 0
		ENDIF
	BREAK

	CASE 3
		IF NOT IS_CHAR_DEAD iGangGuy
		AND IS_GROUP_MEMBER iGangGuy players_group
			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer iGangGuy 2.5 2.5 2.5 FALSE
			AND IS_CHAR_ON_FOOT scplayer 
				
				IF NOT IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
            	    ++iBePhotographedState
				ENDIF
			ELSE
				iBePhotographedState = 0
			ENDIF
		ELSE
			iBePhotographedState = 0
		ENDIF	
	BREAK
		
	CASE 4
		IF NOT IS_CHAR_DEAD iGangGuy		 
			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer iGangGuy 3.0 3.0 2.0 FALSE
			AND IS_CHAR_ON_FOOT scplayer 
			AND IS_GROUP_MEMBER iGangGuy players_group
				IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED CAM_001
					CLEAR_HELP 
				ENDIF
				IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED CAM_002
					CLEAR_HELP 
				ENDIF
				CLEAR_PRINTS
				FREEZE_CHAR_POSITION iGangGuy TRUE
				SET_CHAR_VISIBLE iGangGuy FALSE
				GET_CHAR_COORDINATES iGangGuy fMinX fMinY fMinZ								
				fMinZ += 0.8
				SET_FIXED_CAMERA_POSITION fMinX fMinY fMinZ 0.0 0.2 0.0								
				POINT_CAMERA_AT_CHAR scplayer FIXED JUMP_CUT
				SET_PHOTO_CAMERA_EFFECT TRUE
				GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_CAMERA iTemp2
				REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_CAMERA 
				++iBePhotographedState
			ELSE
				iBePhotographedState = 0
			ENDIF
		ELSE
			iBePhotographedState = 0
		ENDIF
	BREAK

	CASE 5		
		IF NOT IS_CHAR_DEAD iGangGuy	
			IF IS_GROUP_MEMBER iGangGuy players_group 	
			AND LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer iGangGuy 6.0 6.0 2.0 FALSE
			AND IS_CHAR_ON_FOOT scplayer			
				
				// Picture taking shit here
				PRINT_HELP_FOREVER CAM_002  
				
				IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
					++iBePhotographedState					
				ENDIF 				

				IF IS_BUTTON_PRESSED PAD1 TRIANGLE
					iBePhotographedState = 7
				ENDIF
				
			ELSE
				IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED CAM_001
					CLEAR_HELP 
				ENDIF
				IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED CAM_002
					CLEAR_HELP 
				ENDIF
				FREEZE_CHAR_POSITION iGangGuy FALSE
				SET_CHAR_VISIBLE iGangGuy TRUE
				SET_PHOTO_CAMERA_EFFECT	FALSE
				GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_CAMERA iTemp2
				SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_CAMERA
				GET_ACTIVE_CAMERA_COORDINATES fMaxX fMaxY fMaxZ
				IF fMaxX = fMinX 
				AND fMaxY = fMinY 
				AND fMaxZ = fMinZ
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT
				ENDIF
				iBePhotographedState = 0
			ENDIF
		ELSE			
			IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED CAM_001
				CLEAR_HELP 
			ENDIF
			IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED CAM_002
				CLEAR_HELP 
			ENDIF
			GET_ACTIVE_CAMERA_COORDINATES fMaxX fMaxY fMaxZ
			IF fMaxX = fMinX 
			AND fMaxY = fMinY 
			AND fMaxZ = fMinZ
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
			ENDIF
			SET_PHOTO_CAMERA_EFFECT	FALSE
			GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_CAMERA iTemp2
			SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_CAMERA
			iBePhotographedState = 0
		ENDIF
	BREAK

	CASE 6
		CLEAR_HELP
		CLEAR_PRINTS
		SET_PHOTO_CAMERA_EFFECT	FALSE
		TAKE_PHOTO TRUE
		WAIT 100
		++iBePhotographedState
	BREAK

	CASE 7
		IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED CAM_001
			CLEAR_HELP 
		ENDIF
		IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED CAM_002
			CLEAR_HELP 
		ENDIF
		CLEAR_PRINTS
		IF NOT IS_CHAR_DEAD iGangGuy
			FREEZE_CHAR_POSITION iGangGuy FALSE
			SET_CHAR_VISIBLE iGangGuy TRUE
		ENDIF
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		SET_PHOTO_CAMERA_EFFECT	FALSE
		GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_CAMERA iTemp2
		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_CAMERA
		iBePhotographedState = 0	
	BREAK

ENDSWITCH
RETURN
/*******************************************
	CHECK PLAYER IS SAFE FOR CAMERA
********************************************/
Camera_Check_Player_Is_Safe:

	player_is_completely_safe_for_camera = 0

	IF flag_player_on_mission = 0
		IF flag_cell_nation = 0 //phone call not in progress
			IF player_fall_state = 0 //Parachute
				IF NOT IS_BIT_SET iDateReport MEETING_IN_PROGRESS
					IF NOT IS_MINIGAME_IN_PROGRESS
						IF IS_CHAR_ON_FOOT scplayer
							IF NOT IS_GANG_WAR_FIGHTING_GOING_ON
								IF NOT GET_FADING_STATUS 																
									IF iAreaCode = 0
										IF NOT HAS_CUTSCENE_LOADED
											player_is_completely_safe_for_camera = 1
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

RETURN
/*******************************************
				CLEAN UP 
********************************************/
Camera_CleanUp:
 
	IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED CAM_001
		CLEAR_HELP 
	ENDIF

	IF IS_THIS_HELP_MESSAGE_BEING_DISPLAYED CAM_002
		CLEAR_HELP 
	ENDIF

	IF NOT IS_CHAR_DEAD iGangGuy
		FREEZE_CHAR_POSITION iGangGuy FALSE
		SET_CHAR_VISIBLE iGangGuy TRUE
	ENDIF

	IF flag_player_on_mission = 0
		IF iBePhotographedState >= 3
			GET_ACTIVE_CAMERA_COORDINATES fMaxX fMaxY fMaxZ
			IF fMaxX = fMinX 
			AND fMaxY = fMinY 
			AND fMaxZ = fMinZ
				SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
			ENDIF
		ELSE
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
		ENDIF
	ENDIF

//	The gang members are never marked as mission peds by the camera script anyway so the following lines aren't needed.
	REPEAT MAX_GANG_IN_PHOTO iTemp		
		GET_GROUP_MEMBER players_group iTemp iGangGuy
		MARK_CHAR_AS_NO_LONGER_NEEDED iGangGuy
	ENDREPEAT

	SET_PHOTO_CAMERA_EFFECT	FALSE

	MARK_MODEL_AS_NO_LONGER_NEEDED CAMERA

	TERMINATE_THIS_SCRIPT
RETURN
/*******************************************
				RUN DEBUG
********************************************/
Camera_Debug:

RETURN
}
MISSION_END