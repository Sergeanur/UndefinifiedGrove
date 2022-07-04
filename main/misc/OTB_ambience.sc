MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
***********************************************			OTB SHOP	  	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
OTB_Shop:
	SCRIPT_NAME OTBSHP

//--- VARS DECLARATION
LVAR_INT iTemp iRand  //Misc stuff
LVAR_INT iIdle_Keeper_Task //TASKS				    
LVAR_INT iTill_Attractor[4] // Local Attractors specific to this shop
LVAR_INT iShopKeeper[4] //PEDS
LVAR_FLOAT fX fY fZ

//--- THE GLOBAL PANIC FLAG MUST BE SET TO ZERO WHEN ENTERING A SHOP & NOT ON A MISSION
IF flag_player_on_mission = 0
	iSetOTBPanic = 0
ENDIF
iRand = 1 // this is better set to 1

/*******************************SHOP SETUP*******************************/
//--- Requests and loads
REQUEST_MODEL VBFYCRP
REQUEST_MODEL VWFYCRP
WHILE NOT HAS_MODEL_LOADED VWFYCRP
	WAIT 0
ENDWHILE
WHILE NOT HAS_MODEL_LOADED VBFYCRP
	WAIT 0
ENDWHILE

//--- The Shopkeeper's IDLE
	OPEN_SEQUENCE_TASK iIdle_Keeper_Task 
		TASK_STAY_IN_SAME_PLACE  -1 TRUE
		TASK_STAND_STILL -1 -2
	CLOSE_SEQUENCE_TASK iIdle_Keeper_Task

//--- Create shopkeeper's attractors

ADD_ATTRACTOR 820.3698 -0.3935 1003.1797 270.0 90.0 iIdle_Keeper_Task iTill_Attractor[0]
ADD_ATTRACTOR 820.1330 4.2015 1003.1797 270.0 90.0 iIdle_Keeper_Task iTill_Attractor[1]
ADD_ATTRACTOR 820.3185 6.4021 1003.1797 270.0 90.0 iIdle_Keeper_Task iTill_Attractor[2]
ADD_ATTRACTOR 820.0 2.0 1003.0 270.0 90.0 iIdle_Keeper_Task iTill_Attractor[3]

//--- Create the shopkeeper
CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVFEMALE VWFYCRP iTill_Attractor[0] TASK_STAND_STILL iShopKeeper[0]	   //Shopkeeper PED
CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVFEMALE VBFYCRP iTill_Attractor[1] TASK_STAND_STILL iShopKeeper[1]	   
CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVFEMALE VWFYCRP iTill_Attractor[2] TASK_STAND_STILL iShopKeeper[2]	   
CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVFEMALE VBFYCRP iTill_Attractor[3] TASK_STAND_STILL iShopKeeper[3]	   

START_NEW_STREAMED_SCRIPT OTB_Staff.sc iShopKeeper[0] iTill_Attractor[0]
START_NEW_STREAMED_SCRIPT OTB_Staff.sc iShopKeeper[1] iTill_Attractor[1]
START_NEW_STREAMED_SCRIPT OTB_Staff.sc iShopKeeper[2] iTill_Attractor[2]
START_NEW_STREAMED_SCRIPT OTB_Staff.sc iShopKeeper[3] iTill_Attractor[3]
STREAM_SCRIPT OTB_Staff.sc // this fudge is to mark the script as still needed	  

/*******************************MAIN LOOP*******************************/
OTB_Shop_Loop:	  		

	IF IS_PLAYER_PLAYING PLAYER1
		GET_CHAR_AREA_VISIBLE scplayer iAreaCode
 		IF iAreaCode = 0
		OR iTerminateAllAmbience = 1
			GOTO OTB_Shop_Cleanup
		ENDIF

		IF IS_CHAR_SHOOTING scplayer
		OR IS_WANTED_LEVEL_GREATER PLAYER1 iWantedOnEntry
			iSetOTBPanic = 1								
		ENDIF

		REPEAT 4 iTemp
			IF NOT IS_CHAR_DEAD iShopKeeper[iTemp]
				IF IS_PLAYER_TARGETTING_CHAR player1 iShopKeeper[iTemp]
				AND NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_ANYMELEE 
					iSetOTBPanic = 1 //Set the global panic flag
				ELSE
					IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR iShopKeeper[iTemp] scplayer
						iSetOTBPanic = 1 //Set the global panic flag
					ENDIF
				ENDIF
			ENDIF
		ENDREPEAT

		IF iSetOTBPanic = 1 // Public: Can be set by a mission
			//--- Get all peds without a brain and give them a tiny script to cower
			
			GET_RANDOM_CHAR_IN_SPHERE_NO_BRAIN fX fY fZ 20.0 iTemp			
			IF iTemp > -1				 
				iRand += 1
				START_NEW_STREAMED_SCRIPT Customer_Panic.sc iTemp iRand //Pass ped and random action
				STREAM_SCRIPT Customer_Panic.sc // this fudge is to mark the script as still needed	  
			ENDIF
			
		ENDIF		 
	ENDIF
 	
 	WAIT 0
 			
GOTO OTB_Shop_Loop 
/**************************END OF MAIN LOOP****************************************/



/*******************************OTB CLEANUP*******************************/
OTB_Shop_Cleanup:
	//--- Clean up the peds	 
	REPEAT 4 iTemp		
		MARK_CHAR_AS_NO_LONGER_NEEDED iShopKeeper[iTemp]
		DELETE_CHAR iShopKeeper[iTemp]
		//--- Clean up the attractors
		CLEAR_ATTRACTOR iTill_Attractor[iTemp] //The till
	ENDREPEAT

	MARK_MODEL_AS_NO_LONGER_NEEDED VWFYCRP
	MARK_MODEL_AS_NO_LONGER_NEEDED VBFYCRP

   //--- Clear the sequences
   CLEAR_SEQUENCE_TASK iIdle_Keeper_Task

	TERMINATE_THIS_SCRIPT
}
MISSION_END