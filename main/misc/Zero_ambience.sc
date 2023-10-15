MISSION_START
/*********************************************************************************************************************************************
************************************************						**********************************************************************
***********************************************			  RC SHOP  		**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
RC_Shop:
	SCRIPT_NAME RCSHOP

//--- VARS DECLARATION
VAR_INT iSetRCPanic 

LVAR_INT iTemp iRand //Misc counters and stuff
LVAR_FLOAT fX fY fZ fUse fForwad
LVAR_INT iIdle_Keeper_Task //TASKS				    
LVAR_INT iTill_Attractor // Local Attractors specific to this shop
LVAR_INT iShopKeeper iSkeeper_model //PEDS

//--- THE GLOBAL PANIC FLAG MUST BE SET TO ZERO WHEN ENTERING A SHOP
iSetRCPanic = 0

/*******************************SHOP SETUP*******************************/




fX = -2237.2913 
fY = 128.5856 
fZ = 1034.4219
fUse = 0.0 
fForwad = 360.0
iSkeeper_model = WMYCLOT

REQUEST_MODEL iSkeeper_model
WHILE NOT HAS_MODEL_LOADED iSkeeper_model
	WAIT 0
ENDWHILE
		

//--- The Shopkeeper's IDLE
	OPEN_SEQUENCE_TASK iIdle_Keeper_Task 
		TASK_STAY_IN_SAME_PLACE  -1 TRUE
		TASK_STAND_STILL -1 -2
	CLOSE_SEQUENCE_TASK iIdle_Keeper_Task

//--- Create shopkeeper's attractor -117.2905
ADD_ATTRACTOR fX fY fZ fUse fForwad iIdle_Keeper_Task iTill_Attractor

//--- Create the shopkeeper
CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVMALE iSkeeper_model iTill_Attractor TASK_STAND_STILL iShopKeeper	   //Shopkeeper PED

/*******************************MAIN LOOP*******************************/
RC_Shop_Loop:	  		
	 
	IF IS_PLAYER_PLAYING PLAYER1

		GET_CHAR_AREA_VISIBLE scplayer iAreaCode
		IF iAreaCode = 0
		OR iTerminateAllAmbience = 1
			GOSUB RC_Cleanup
		ENDIF

		//--- ZERO's MINI-GAME STUFF
		IF flag_zero_mission_counter = 3
		AND flag_player_on_mission = 0
			IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer -2219.2869 133.8895 1034.6406 1.5 1.5 1.5 TRUE 
		        load_and_launch_mission_if_poss = ZERO5				
		    ENDIF
		ENDIF

		IF IS_CHAR_SHOOTING scplayer
		OR IS_WANTED_LEVEL_GREATER PLAYER1 iWantedOnEntry
			iSetRCPanic = 1					
		ENDIF

		IF NOT IS_CHAR_DEAD iShopKeeper
			IF IS_PLAYER_TARGETTING_CHAR player1 iShopKeeper
			AND NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_ANYMELEE 
				iSetRCPanic = 1 //Set the global panic flag
			ELSE
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR iShopKeeper scplayer
					iSetRCPanic = 1 //Set the global panic flag
				ENDIF
			ENDIF
		ENDIF

		IF iSetRCPanic = 1 // Public: Can be set by a mission
			//--- Get all peds without a brain and give them a tiny script to cower
			GET_CHAR_COORDINATES scplayer fX fY fZ 
			GET_RANDOM_CHAR_IN_SPHERE_NO_BRAIN fX fY fZ 20.0 iTemp			
			IF iTemp > -1				 
				iRand += 1
				START_NEW_STREAMED_SCRIPT Customer_Panic.sc iTemp iRand //Pass ped and random action
				STREAM_SCRIPT Customer_Panic.sc // this fudge is to mark the script as still needed	  
			ENDIF
		ENDIF
		 
	ENDIF
  
 	WAIT 0
 			
GOTO RC_Shop_Loop 
/**************************END OF MAIN LOOP****************************************/



/*******************************PIZZA CLEANUP*******************************/
RC_Cleanup:
	
	iSetRCPanic = 0

	//--- Clean up the peds	 
	MARK_CHAR_AS_NO_LONGER_NEEDED iShopKeeper
	MARK_MODEL_AS_NO_LONGER_NEEDED iSkeeper_model
	DELETE_CHAR iShopKeeper

	//--- Clean up the attractors
	CLEAR_ATTRACTOR iTill_Attractor //The till

   //--- Clear the sequences
	CLEAR_SEQUENCE_TASK iIdle_Keeper_Task

	TERMINATE_THIS_SCRIPT
}
MISSION_END