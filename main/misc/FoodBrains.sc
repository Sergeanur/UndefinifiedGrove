MISSION_START

CONST_INT SHOP_PIZZA	0
CONST_INT SHOP_CHICKEN	1
CONST_INT SHOP_BURGER	2
/*********************************************************************************************************************************************
************************************************						**********************************************************************
***********************************************			PIZZA SHOP  	**********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
Pizza_Shop:
	SCRIPT_NAME PSHOP

//--- VARS DECLARATION
LVAR_INT iShopType 		// Parameter: 0 for Pizza, 1 for Burger and 2 for Chicken
LVAR_INT iTemp iRand //Misc counters and stuff
LVAR_FLOAT fX fY fZ fUse fForwad
LVAR_INT iIdle_Keeper_Task //TASKS				    
LVAR_INT iTill_Attractor // Local Attractors specific to this shop
LVAR_INT iShopKeeper iSkeeper_model //PEDS

//--- THE GLOBAL PANIC FLAG MUST BE SET TO ZERO WHEN ENTERING A SHOP
iSetPizzaPanic = 0

/*******************************SHOP SETUP*******************************/


SWITCH iShopType  
	CASE SHOP_PIZZA
		fX = 377.3 
		fY = -117.182 
		fZ = 1000.637 
		fUse = 180.0 
		fForwad = 360.0
		iSkeeper_model = WMYPIZZ

		REQUEST_MODEL iSkeeper_model
		REQUEST_MODEL PIZZAHIGH
		REQUEST_MODEL CJ_PIZZA_2
		
		WHILE NOT HAS_MODEL_LOADED iSkeeper_model
			WAIT 0
		ENDWHILE
		WHILE NOT HAS_MODEL_LOADED PIZZAHIGH
			WAIT 0
		ENDWHILE
		WHILE NOT HAS_MODEL_LOADED CJ_PIZZA_2
			WAIT 0
		ENDWHILE
		
	BREAK

	CASE SHOP_CHICKEN
		fX = 371.5365 
		fY = -4.4935 
		fZ = 1000.8589 
		fUse = 180.0 
		fForwad = 360.0	     
		iSkeeper_model = WMYBELL

		REQUEST_MODEL iSkeeper_model
		REQUEST_MODEL BURGERHIGH
		REQUEST_MODEL CJ_BURG_2		
		WHILE NOT HAS_MODEL_LOADED iSkeeper_model
			WAIT 0
		ENDWHILE
		WHILE NOT HAS_MODEL_LOADED BURGERHIGH
			WAIT 0
		ENDWHILE
		WHILE NOT HAS_MODEL_LOADED CJ_BURG_2
			WAIT 0
		ENDWHILE
		
	BREAK

	CASE SHOP_BURGER
		fX = 378.4146 
		fY = -65.8567 
		fZ = 1000.5078
		fUse = 180.0 
		fForwad = 360.0	     
		iSkeeper_model = WFYBURG

		REQUEST_MODEL iSkeeper_model
		REQUEST_MODEL BURGERHIGH
		REQUEST_MODEL CJ_BURG_2
		WHILE NOT HAS_MODEL_LOADED iSkeeper_model
			WAIT 0
		ENDWHILE
		WHILE NOT HAS_MODEL_LOADED BURGERHIGH
			WAIT 0
		ENDWHILE
		WHILE NOT HAS_MODEL_LOADED CJ_BURG_2
			WAIT 0
		ENDWHILE
	BREAK

ENDSWITCH

//--- The Shopkeeper's IDLE
	OPEN_SEQUENCE_TASK iIdle_Keeper_Task 
		TASK_STAY_IN_SAME_PLACE  -1 TRUE
		TASK_STAND_STILL -1 -2
	CLOSE_SEQUENCE_TASK iIdle_Keeper_Task

//--- Create shopkeeper's attractor -117.2905
ADD_ATTRACTOR fX fY fZ fUse fForwad iIdle_Keeper_Task iTill_Attractor

//--- Create the shopkeeper
CREATE_CHAR_AT_ATTRACTOR PEDTYPE_CIVMALE iSkeeper_model iTill_Attractor TASK_STAND_STILL iShopKeeper	   //Shopkeeper PED
START_NEW_STREAMED_SCRIPT ShopKeeper.sc iShopKeeper iTill_Attractor
STREAM_SCRIPT ShopKeeper.sc // this fudge is to mark the script as still needed

/*******************************MAIN LOOP*******************************/
Pizza_Shop_Loop:	  		

	IF IS_PLAYER_PLAYING PLAYER1

		GET_CHAR_AREA_VISIBLE scplayer iAreaCode
		IF iAreaCode = 0
		OR iTerminateAllAmbience = 1
			GOSUB Pizza_Cleanup
		ENDIF

		IF IS_CHAR_SHOOTING scplayer
			iSetPizzaPanic = 1					
		ENDIF

		IF NOT IS_CHAR_DEAD iShopKeeper
			IF IS_PLAYER_TARGETTING_CHAR player1 iShopKeeper
			//AND NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_ANYMELEE removed for consistency with Willie
				iSetPizzaPanic = 1 //Set the global panic flag
			ELSE
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR iShopKeeper scplayer
					iSetPizzaPanic = 1 //Set the global panic flag
				ENDIF
			ENDIF
		ENDIF

		IF iSetPizzaPanic = 1 // Public: Can be set by a mission
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
 			
GOTO Pizza_Shop_Loop 
/**************************END OF MAIN LOOP****************************************/



/*******************************PIZZA CLEANUP*******************************/
Pizza_Cleanup:
	
	iSetPizzaPanic = 0

	//--- Clean up the peds	 
	MARK_CHAR_AS_NO_LONGER_NEEDED iShopKeeper
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYPIZZ
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYBELL
	MARK_MODEL_AS_NO_LONGER_NEEDED WFYBURG
	MARK_MODEL_AS_NO_LONGER_NEEDED PIZZAHIGH
	MARK_MODEL_AS_NO_LONGER_NEEDED BURGERHIGH
	MARK_MODEL_AS_NO_LONGER_NEEDED CJ_PIZZA_2
	MARK_MODEL_AS_NO_LONGER_NEEDED CJ_BURG_2
	DELETE_CHAR iShopKeeper

	//--- Clean up the attractors
	CLEAR_ATTRACTOR iTill_Attractor //The till

   //--- Clear the sequences
	CLEAR_SEQUENCE_TASK iIdle_Keeper_Task

	TERMINATE_THIS_SCRIPT
}
MISSION_END