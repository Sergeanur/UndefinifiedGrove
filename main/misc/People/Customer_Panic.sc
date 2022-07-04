MISSION_START
/*****************************************************************************************************************************************
************************************************ 	CUSTOMERS PANIC		******************************************************************
*****************************************************************************************************************************************/
// THIS IS NOT A BRAIN SCRIPT - REMEMBER TO MARK THIS_PED AS NO LONGER NEEDED.
{
//Customer_Panic:

    SCRIPT_NAME FFPNC

    LVAR_INT this_ped iRandPar    // parameters arriving from caller
    LVAR_INT iTemp iIsCowering 
    LVAR_FLOAT fX fY fZ 

    iTemp = 0
	
    IF iTemp = 1
        CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 this_ped
    ENDIF

	//--- Misc Setups that require an 'alive' check
	IF NOT IS_CHAR_DEAD this_ped 
		//--- Set up the Decision Maker		
		SET_CHAR_DECISION_MAKER this_ped iGlobalPedPanicDM
	ENDIF

Customer_Panic_Loop:

	WAIT 0 
		
		IF IS_PLAYER_PLAYING PLAYER1
		AND iTerminateAllAmbience = 0
			GET_CHAR_AREA_VISIBLE scplayer iAreaCode
			IF iAreaCode = 0
			OR iTerminateAllAmbience = 1
				GOSUB Customer_Panic_CleanUp
			ENDIF
			IF NOT IS_CHAR_DEAD this_ped			
				iTemp = iRandPar / 2
				iTemp *= 2 // If it's an odd number it will be less than the original due to the integer cast 
				IF iTemp < iRandPar
					GET_SCRIPT_TASK_STATUS this_ped	TASK_DUCK iIsCowering
					IF iIsCowering = FINISHED_TASK
						TASK_DUCK this_ped -2
					ENDIF
				ELSE
					GET_SCRIPT_TASK_STATUS this_ped	TASK_HANDS_UP iIsCowering
					IF iIsCowering = FINISHED_TASK
						TASK_HANDS_UP this_ped -2
					ENDIF
				ENDIF					   
			ELSE
				GOSUB Customer_Panic_CleanUp		
			ENDIF		
		ELSE
			GOSUB Customer_Panic_CleanUp
		ENDIF

GOTO  Customer_Panic_Loop
/*******************************************
				PANIC CLEAN UP 
*******************************************/
Customer_Panic_CleanUp:
	MARK_CHAR_AS_NO_LONGER_NEEDED this_ped	
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED Customer_Panic.sc
	TERMINATE_THIS_SCRIPT
RETURN
}
MISSION_END