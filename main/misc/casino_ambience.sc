MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
***********************************************			CASINO		  	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
CASAMB_ambience:
	SCRIPT_NAME CASAMB

//--- CONSTS
CONST_INT MAX_SCRIPT_ATTRACTORS		11
CONST_INT MAX_SCRIPT_PEDS			11

//-------------- GLOBALS ---------------------------
VAR_INT iSetCasinoPanic iBouncerAction 
//---------------------------------------------------
 
//--- Internals
LVAR_INT iTemp iRand  //Misc stuff
LVAR_INT iIdle_BarKeeper_Task iServe_BarKeeper_Task iIdle_Bouncer_Task //TASKS				    
LVAR_INT iScript_Attractor[MAX_SCRIPT_ATTRACTORS] // Local Attractors 
LVAR_INT iScriptedAttractorPed[MAX_SCRIPT_PEDS] //PEDS
LVAR_FLOAT fX fY fZ

//--- THE GLOBAL PANIC FLAG MUST BE SET TO ZERO WHEN ENTERING A SHOP & NOT ON A MISSION
IF flag_player_on_mission = 0
	iSetCasinoPanic = 0
	iBouncerAction = 0
ENDIF
iRand = 1 // this is better set to 1

/*******************************CASINO SETUP*******************************/
//--- Requests and loads
	IF $txtEntryExit = &MAFCAS
		REQUEST_MODEL VMAFF1
		REQUEST_MODEL VMAFF3
		REQUEST_MODEL VMAFF2
		REQUEST_MODEL VWFYWAI
	ENDIF
	IF $txtEntryExit = &TRICAS 
		REQUEST_MODEL BMYBOUN
		REQUEST_MODEL WMYBOUN
		REQUEST_MODEL VWFYWA2
	ENDIF
	IF $txtEntryExit = &CASINO2
		REQUEST_MODEL BMYBOUN
		REQUEST_MODEL WMYBOUN
		REQUEST_MODEL VWFYCRP			
	ENDIF
	REQUEST_ANIMATION BAR
	WHILE NOT HAS_ANIMATION_LOADED BAR
		WAIT 0 
	ENDWHILE


//--- The barkeeper's IDLE
	OPEN_SEQUENCE_TASK iIdle_BarKeeper_Task 
		//TASK_STAND_STILL -1 -2
		TASK_PLAY_ANIM  -1 Barserve_in BAR 4.0 FALSE FALSE FALSE FALSE -1
		TASK_PLAY_ANIM  -1 Barserve_loop BAR 4.0 TRUE FALSE FALSE FALSE 10000
	CLOSE_SEQUENCE_TASK iIdle_BarKeeper_Task
//--- The barkeeper's Serve
	OPEN_SEQUENCE_TASK iServe_BarKeeper_Task 
		TASK_PLAY_ANIM  -1 Barserve_bottle BAR 4.0 FALSE FALSE FALSE FALSE -1 		
	CLOSE_SEQUENCE_TASK iServe_BarKeeper_Task

//--- The bouncer's IDLE
	OPEN_SEQUENCE_TASK iIdle_Bouncer_Task 
		TASK_WANDER_STANDARD -1 
	CLOSE_SEQUENCE_TASK iIdle_Bouncer_Task

IF IS_PLAYER_PLAYING PLAYER1
	//--- Reset number of player's peds killed
	RESET_NUM_OF_MODELS_KILLED_BY_PLAYER PLAYER1

	IF $txtEntryExit = &TRICAS
		//--- Create BAR script attractors		
		ADD_ATTRACTOR 1953.7736 1018.1968 991.4766 260.0 90.0 iIdle_BarKeeper_Task iScript_Attractor[0] // bar middle 1
		ADD_ATTRACTOR 1952.2864 1025.5803 991.4745 260.0 90.0 iIdle_BarKeeper_Task iScript_Attractor[1] // bar end 1
		ADD_ATTRACTOR 1952.3 1010.2959 991.4745 260.0 90.0 iIdle_BarKeeper_Task iScript_Attractor[2] // bar end 2
		ADD_ATTRACTOR 1952.25 1017.92 991.4766 90.0 260.0 iServe_BarKeeper_Task iScript_Attractor[3] // bar drinks
		
		ADD_ATTRACTOR 1948.0728 1016.5054 991.4766 90.0 180.0 iIdle_BarKeeper_Task iScript_Attractor[4]
		ADD_ATTRACTOR 1948.5643 1022.1820 991.4766 90.0 180.0 iIdle_BarKeeper_Task iScript_Attractor[5]
		ADD_ATTRACTOR 1949.2462 1013.6127 991.4766 90.0 180.0 iIdle_BarKeeper_Task iScript_Attractor[6]
		ADD_ATTRACTOR 1949.0 1015.8 991.4766 275.0 85.0  iServe_BarKeeper_Task iScript_Attractor[7]
		
		CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVFEMALE VWFYWA2 iScript_Attractor[0] TASK_STAND_STILL iScriptedAttractorPed[0]
		CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVFEMALE VWFYWA2 iScript_Attractor[4] TASK_STAND_STILL iScriptedAttractorPed[1]
		START_NEW_STREAMED_SCRIPT BAR_Staff.sc iScriptedAttractorPed[0] iScript_Attractor[0] iScript_Attractor[1] iScript_Attractor[2] iScript_Attractor[3] 2.5 //Pass: barman, bounds of bar attractors, drinks attractor, depth of bar
		START_NEW_STREAMED_SCRIPT BAR_Staff.sc iScriptedAttractorPed[1] iScript_Attractor[4] iScript_Attractor[5] iScript_Attractor[6] iScript_Attractor[7] 2.5 //Pass: barman, bounds of bar attractors, drinks attractor, depth of bar
		STREAM_SCRIPT BAR_Staff.sc // this fudge is to mark the script as still needed

		//--- Create BOUNCER generators - script attractors
		ADD_ATTRACTOR 1966.5394 984.8237 993.4688 270.0 270.0 iIdle_Bouncer_Task iScript_Attractor[8]		
		ADD_ATTRACTOR 1932.3954 1033.8857 993.4688 60.0 300.0 iIdle_Bouncer_Task iScript_Attractor[9]
		ADD_ATTRACTOR 1959.0935 993.7656 991.4766 240.0 240.0 iIdle_Bouncer_Task iScript_Attractor[10]

		CREATE_CHAR_AT_ATTRACTOR PEDTYPE_COP BMYBOUN iScript_Attractor[8] TASK_WANDER_STANDARD iScriptedAttractorPed[2]
		CREATE_CHAR_AT_ATTRACTOR PEDTYPE_COP WMYBOUN iScript_Attractor[9] TASK_WANDER_STANDARD iScriptedAttractorPed[3]
		CREATE_CHAR_AT_ATTRACTOR PEDTYPE_COP BMYBOUN iScript_Attractor[10] TASK_WANDER_STANDARD iScriptedAttractorPed[4]
		START_NEW_STREAMED_SCRIPT Bouncer.sc iScriptedAttractorPed[2]
		START_NEW_STREAMED_SCRIPT Bouncer.sc iScriptedAttractorPed[3]
		START_NEW_STREAMED_SCRIPT Bouncer.sc iScriptedAttractorPed[4]
		STREAM_SCRIPT Bouncer.sc // this fudge is to mark the script as still needed

		

	ENDIF

	IF $txtEntryExit = &MAFCAS
		//--- Create BAR script attractors		
		ADD_ATTRACTOR 2195.7305 1600.7856 1004.0625 206.0 -206.0 iIdle_BarKeeper_Task iScript_Attractor[0] // bar middle 1
		ADD_ATTRACTOR 2197.0120 1602.8521 1004.0625 253.0 -253.0 iIdle_BarKeeper_Task iScript_Attractor[1] // bar end 1
		ADD_ATTRACTOR 2195.7856 1606.3628 1004.0625 349.0 -349.0 iIdle_BarKeeper_Task iScript_Attractor[2] // bar end 2
		ADD_ATTRACTOR 2196.1372 1603.5211 1004.0625 98.0 -98.0 iServe_BarKeeper_Task iScript_Attractor[3] // bar drinks
		
		ADD_ATTRACTOR 2191.5916 1601.2855 1004.0625 151.0 -151.0 iIdle_BarKeeper_Task iScript_Attractor[4]
		ADD_ATTRACTOR 2191.1758 1604.8744 1004.0625 58.0 -58.0 iIdle_BarKeeper_Task iScript_Attractor[5]
		ADD_ATTRACTOR 2192.4250 1606.9707 1004.0625 40.0 -40.0 iIdle_BarKeeper_Task iScript_Attractor[6]
		ADD_ATTRACTOR 2192.9539 1605.6869 1004.0690 207.0 -207.0 iServe_BarKeeper_Task iScript_Attractor[7]
		
		CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVFEMALE VWFYWAI iScript_Attractor[0] TASK_STAND_STILL iScriptedAttractorPed[0]
		CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVFEMALE VWFYWAI iScript_Attractor[4] TASK_STAND_STILL iScriptedAttractorPed[1]
		START_NEW_STREAMED_SCRIPT BAR_Staff.sc iScriptedAttractorPed[0] iScript_Attractor[0] iScript_Attractor[1] iScript_Attractor[2] iScript_Attractor[3] 3.5 //Pass: barman, bounds of bar attractors, drinks attractor, depth of bar
		START_NEW_STREAMED_SCRIPT BAR_Staff.sc iScriptedAttractorPed[1] iScript_Attractor[4] iScript_Attractor[5] iScript_Attractor[6] iScript_Attractor[7] 3.5 //Pass: barman, bounds of bar attractors, drinks attractor, depth of bar
		STREAM_SCRIPT BAR_Staff.sc // this fudge is to mark the script as still needed

		//--- Create BOUNCER generators - script attractors
		ADD_ATTRACTOR 2247.8687 1624.4673 1007.3672 152.0 -152.0 iIdle_Bouncer_Task iScript_Attractor[8]		
		ADD_ATTRACTOR 2247.3489 1583.2570 1007.3663 358.0 -358.0 iIdle_Bouncer_Task iScript_Attractor[9]
		ADD_ATTRACTOR 2164.6428 1582.1510 1007.3594 13.0 -13.0 iIdle_Bouncer_Task iScript_Attractor[10]

		CREATE_CHAR_AT_ATTRACTOR PEDTYPE_COP VMAFF1 iScript_Attractor[8] TASK_WANDER_STANDARD iScriptedAttractorPed[2]
		CREATE_CHAR_AT_ATTRACTOR PEDTYPE_COP VMAFF2 iScript_Attractor[9] TASK_WANDER_STANDARD iScriptedAttractorPed[3]
		CREATE_CHAR_AT_ATTRACTOR PEDTYPE_COP VMAFF3 iScript_Attractor[10] TASK_WANDER_STANDARD iScriptedAttractorPed[4]
		START_NEW_STREAMED_SCRIPT Bouncer.sc iScriptedAttractorPed[2]
		START_NEW_STREAMED_SCRIPT Bouncer.sc iScriptedAttractorPed[3]
		START_NEW_STREAMED_SCRIPT Bouncer.sc iScriptedAttractorPed[4]
		STREAM_SCRIPT Bouncer.sc // this fudge is to mark the script as still needed
	ENDIF

	IF $txtEntryExit = &CASINO2
		//--- Create BAR script attractors		
		ADD_ATTRACTOR 1141.2058 -5.9198 999.6719 81.0 -81.0 iIdle_BarKeeper_Task iScript_Attractor[0] // bar middle 1
		ADD_ATTRACTOR 1141.8308 -8.9940 999.6797 94.0 -94.0 iIdle_BarKeeper_Task iScript_Attractor[1] // bar end 1
		ADD_ATTRACTOR 1141.1610 1.2240 999.6719 110.0 -110.0 iIdle_BarKeeper_Task iScript_Attractor[2] // bar end 2
		ADD_ATTRACTOR 1141.1591 -3.9906 999.6797 93.0 -93.0 iServe_BarKeeper_Task iScript_Attractor[3] // bar drinks
		CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVFEMALE VWFYCRP iScript_Attractor[0] TASK_STAND_STILL iScriptedAttractorPed[0]
		START_NEW_STREAMED_SCRIPT BAR_Staff.sc iScriptedAttractorPed[0] iScript_Attractor[0] iScript_Attractor[1] iScript_Attractor[2] iScript_Attractor[3] 2.0 //Pass: barman, bounds of bar attractors, drinks attractor, depth of bar
		STREAM_SCRIPT BAR_Staff.sc // this fudge is to mark the script as still needed

		iScript_Attractor[4] = -1
		iScript_Attractor[5] = -1
		iScript_Attractor[6] = -1
		iScript_Attractor[7] = -1

		//--- Create BOUNCER generators - script attractors
		ADD_ATTRACTOR 1143.6765 6.4929 999.6797 71.0 -71.0 iIdle_Bouncer_Task iScript_Attractor[8]		
		CREATE_CHAR_AT_ATTRACTOR PEDTYPE_COP BMYBOUN iScript_Attractor[8] TASK_WANDER_STANDARD iScriptedAttractorPed[2]
		START_NEW_STREAMED_SCRIPT Bouncer.sc iScriptedAttractorPed[2]
		STREAM_SCRIPT Bouncer.sc // this fudge is to mark the script as still needed

		iScript_Attractor[9] = -1
		iScript_Attractor[10] = -1

	ENDIF

ENDIF						 
  

/*******************************MAIN LOOP*******************************/
CASAMB_Loop:	  		

	

	IF IS_PLAYER_PLAYING PLAYER1
		
		GET_CHAR_AREA_VISIBLE scplayer iAreaCode		
		IF iAreaCode = 0
		OR iTerminateAllAmbience = 1
			GOTO CASAMB_Cleanup
		ENDIF

		IF IS_CHAR_SHOOTING scplayer
			iSetCasinoPanic = 1	
			iBouncerAction = 1							
		ENDIF
		

		GET_TOTAL_NUMBER_OF_PEDS_KILLED_BY_PLAYER PLAYER1 iTemp
		IF iTemp > 0
			iBouncerAction = 1
		ENDIF

		IF IS_WANTED_LEVEL_GREATER PLAYER1 iWantedOnEntry
			iBouncerAction = 1
		ENDIF

		REPEAT MAX_SCRIPT_PEDS iTemp
			IF NOT IS_CHAR_DEAD iScriptedAttractorPed[iTemp]
				IF IS_PLAYER_TARGETTING_CHAR player1 iScriptedAttractorPed[iTemp]
				AND NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_ANYMELEE 
					iSetCasinoPanic = 1 //Set the global panic flag
					iBouncerAction = 1
				ELSE
					IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR iScriptedAttractorPed[iTemp] scplayer
						iSetCasinoPanic = 1 //Set the global panic flag
						iBouncerAction = 1
					ENDIF
				ENDIF
			ENDIF
		ENDREPEAT

		IF iSetCasinoPanic = 1 // Public: Can be set by a mission
			iBouncerAction = 1
			//--- Get all peds without a brain and give them a tiny script to cower
			GET_RANDOM_CHAR_IN_SPHERE_NO_BRAIN fX fY fZ 50.0 iTemp			
			IF iTemp > -1				 
				iRand += 1
				START_NEW_STREAMED_SCRIPT Customer_Panic.sc iTemp iRand //Pass ped and random action
				STREAM_SCRIPT Customer_Panic.sc // this fudge is to mark the script as still needed
			ENDIF
		ENDIF

		IF iBouncerAction = 1	
			//--- Spawn more bouncers if needed
			IF IS_CHAR_DEAD iScriptedAttractorPed[2] 
			AND NOT iScript_Attractor[8] = -1 	

				IF $txtEntryExit = &TRICAS
					fX = 1966.5394  
					fY = 984.8237
					fZ = 993.4688
				ENDIF
				IF $txtEntryExit = &MAFCAS
					fX = 2247.8687 
					fY = 1624.4673 
					fZ = 1007.3672
				ENDIF
				IF $txtEntryExit = &CASINO2
					fX = 1143.6765   
					fY = 6.4929 
					fZ = 999.6797
				ENDIF
				
				IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer fX fY fZ 8.0 8.0 8.0 FALSE
				AND NOT IS_POINT_ON_SCREEN fX fY fZ 3.0 
					IF $txtEntryExit = &MAFCAS
						CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVMALE VMAFF1 iScript_Attractor[8] TASK_WANDER_STANDARD iScriptedAttractorPed[2]			
					ELSE
						CREATE_CHAR_AT_ATTRACTOR PEDTYPE_COP BMYBOUN iScript_Attractor[8] TASK_WANDER_STANDARD iScriptedAttractorPed[2]			
					ENDIF
					START_NEW_STREAMED_SCRIPT Bouncer.sc iScriptedAttractorPed[2]
					STREAM_SCRIPT Bouncer.sc // this fudge is to mark the script as still needed
				ENDIF
			ENDIF

			IF IS_CHAR_DEAD iScriptedAttractorPed[3] 
			AND NOT iScript_Attractor[9] = -1

				IF $txtEntryExit = &TRICAS
					fX = 1932.3954    
					fY = 1033.8857 
					fZ = 993.4688
				ENDIF
				IF $txtEntryExit = &MAFCAS
					fX = 2247.3489 
					fY = 1583.2570
					fZ = 1007.3663
				ENDIF

				IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer fX fY fZ 8.0 8.0 8.0 FALSE
				AND NOT IS_POINT_ON_SCREEN fX fY fZ 3.0
					IF $txtEntryExit = &MAFCAS
						CREATE_CHAR_AT_ATTRACTOR PEDTYPE_COP VMAFF2 iScript_Attractor[9] TASK_WANDER_STANDARD iScriptedAttractorPed[3]			
					ELSE
						CREATE_CHAR_AT_ATTRACTOR PEDTYPE_COP WMYBOUN iScript_Attractor[9] TASK_WANDER_STANDARD iScriptedAttractorPed[3]			
					ENDIF
					START_NEW_STREAMED_SCRIPT Bouncer.sc iScriptedAttractorPed[3]
					STREAM_SCRIPT Bouncer.sc // this fudge is to mark the script as still needed
				ENDIF
			ENDIF

			IF IS_CHAR_DEAD iScriptedAttractorPed[4] 
			AND NOT iScript_Attractor[10] = -1

				IF $txtEntryExit = &TRICAS
					fX = 1959.0935     
					fY = 993.7656 
					fZ = 991.4766
				ENDIF
				IF $txtEntryExit = &MAFCAS
					fX = 2164.6428 
					fY = 1582.1510
					fZ = 1007.3594
				ENDIF

				IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer fX fY fZ 8.0 8.0 8.0 FALSE
				AND NOT IS_POINT_ON_SCREEN fX fY fZ 3.0
					IF $txtEntryExit = &MAFCAS
						CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVMALE VMAFF3 iScript_Attractor[10] TASK_WANDER_STANDARD iScriptedAttractorPed[4]			
					ELSE
						CREATE_CHAR_AT_ATTRACTOR PEDTYPE_COP WMYBOUN iScript_Attractor[10] TASK_WANDER_STANDARD iScriptedAttractorPed[4]			
					ENDIF
					START_NEW_STREAMED_SCRIPT Bouncer.sc iScriptedAttractorPed[4]
					STREAM_SCRIPT Bouncer.sc // this fudge is to mark the script as still needed
				ENDIF
			ENDIF
		ENDIF		 

	ENDIF

 	WAIT 0		

GOTO CASAMB_Loop 
/**************************END OF MAIN LOOP****************************************/



/*******************************CASINO CLEANUP*******************************/
CASAMB_Cleanup:
	//--- Clean up the peds	 
	REPEAT MAX_SCRIPT_PEDS iTemp		
		MARK_CHAR_AS_NO_LONGER_NEEDED iScriptedAttractorPed[iTemp]
		DELETE_CHAR iScriptedAttractorPed[iTemp]
	ENDREPEAT
	
	REPEAT MAX_SCRIPT_ATTRACTORS iTemp
		IF NOT iScript_Attractor[iTemp] = -1 // unused or invalid attractor
			//--- Clean up the attractors
			CLEAR_ATTRACTOR iScript_Attractor[iTemp] 
		ENDIF
	ENDREPEAT

	REMOVE_ANIMATION BAR

	MARK_MODEL_AS_NO_LONGER_NEEDED VWFYWAI
	MARK_MODEL_AS_NO_LONGER_NEEDED VWFYWA2
	MARK_MODEL_AS_NO_LONGER_NEEDED VWFYCRP
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYBOUN
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYBOUN
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF1
 	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF2
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF3

   //--- Clear the sequences
   CLEAR_SEQUENCE_TASK iIdle_BarKeeper_Task
   CLEAR_SEQUENCE_TASK iServe_BarKeeper_Task
   CLEAR_SEQUENCE_TASK iIdle_Bouncer_Task

TERMINATE_THIS_SCRIPT
}
MISSION_END
