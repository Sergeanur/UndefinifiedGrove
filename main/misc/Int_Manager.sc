MISSION_START
	START_NEW_SCRIPT Int_Manager 
MISSION_END
/*********************************************************************************************************************************************
************************************************						**********************************************************************
************************************************ 	INTERIORS MANAGER	*********************************************************************
************************************************						**********************************************************************
**********************************************************************************************************************************************/
{
Int_Manager:
    SCRIPT_NAME INTMAN

	//--- GLOBALS
	VAR_INT iTerminateAllAmbience iAreaCode iStoredAreaCode
	VAR_TEXT_LABEL16 txtEntryExit
	VAR_INT iGlobalPedPanicDM iGlobalShopkeeperDM
	VAR_INT iSetClothesPanic iWantedOnEntry iCheckForWeapons iCreateDecisionMakers

	//--- LOCALS
	LVAR_INT iState iStreamedScriptInstances iAmbienceHasTerminated

	iGlobalPedPanicDM = -1
	IF iGlobalPedPanicDM = 9999	//	fudge to fool the excellent compiler
		COPY_CHAR_DECISION_MAKER  DM_PED_INDOORS iGlobalPedPanicDM
		COPY_CHAR_DECISION_MAKER  DM_PED_INDOORS iGlobalShopkeeperDM		
	ENDIF

	//---MAIN LOOP---
Int_Manager_Main_Loop:
	
	IF IS_PLAYER_PLAYING PLAYER1
		GOSUB Int_Manager_State_Machine
	ENDIF
		
	WAIT 0

GOTO Int_Manager_Main_Loop 
	//---END OF MAIN LOOP---

/********************************************
			STATE MACHINE
********************************************/
Int_Manager_State_Machine:
	SWITCH iState	   	
   		CASE 0 //---STATE 0 - Waiting to enter an entry exit
			GOSUB Int_Manager_ENTRY
		BREAK
   		CASE 1 //---STATE 1 - Waiting to leave an entry exit
			GOSUB Int_Manager_EXIT
		BREAK
	ENDSWITCH
RETURN
/********************************************
					ENTRY
********************************************/
Int_Manager_ENTRY:  

	GET_CHAR_AREA_VISIBLE scplayer iAreaCode
	IF NOT iAreaCode = 0 
		GET_NAME_OF_ENTRY_EXIT_CHAR_USED scplayer txtEntryExit
		
		//--- Find out if the area has changed due to an entry exit point
		IF NOT IS_STRING_EMPTY $txtEntryExit

			//--- OK. Store this area code
			iStoredAreaCode = iAreaCode

			//--- Grab the current wanted level
			IF IS_PLAYER_PLAYING PLAYER1
				STORE_WANTED_LEVEL PLAYER1 iWantedOnEntry											   
			ENDIF

			//--- Fill the shop string for Willie
			GET_LOADED_SHOP shop_name

			//--- Reset the global flags that control the ambience
			iCreateDecisionMakers = 0
			iTerminateAllAmbience = 0
			iAmbienceHasTerminated = 0

			//------------------------
			// ENTRY EXIT LABEL CHECKS
			//------------------------

			//--- WARDROBE
			IF $txtEntryExit = &CHANGER
			AND iAreaCode = 14

				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT wardrobe.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT wardrobe.sc
					LOAD_ALL_MODELS_NOW
					IF HAS_STREAMED_SCRIPT_LOADED wardrobe.sc
						START_NEW_STREAMED_SCRIPT wardrobe.sc
						++iState // move on to the wait state
						RETURN
					ENDIF
				ENDIF
			ENDIF

			//--- CASINO
			IF $txtEntryExit = &TRICAS
			OR $txtEntryExit = &MAFCAS
			OR $txtEntryExit = &CASINO2
				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT Bar_Staff.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT Bar_Staff.sc
				ENDIF

				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT Bouncer.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT Bouncer.sc
				ENDIF

				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT Customer_Panic.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT Customer_Panic.sc
				ENDIF

				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT casino_ambience.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT casino_ambience.sc
				ENDIF
				
				REQUEST_ANIMATION BAR
				REQUEST_ANIMATION CASINO

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

				LOAD_ALL_MODELS_NOW

				IF HAS_STREAMED_SCRIPT_LOADED casino_ambience.sc
					START_NEW_STREAMED_SCRIPT casino_ambience.sc
				ENDIF

				IF HAS_STREAMED_SCRIPT_LOADED Bar_Staff.sc 
				AND HAS_STREAMED_SCRIPT_LOADED Bouncer.sc
				AND HAS_STREAMED_SCRIPT_LOADED Customer_Panic.sc
				AND HAS_STREAMED_SCRIPT_LOADED casino_ambience.sc
					iCreateDecisionMakers = 1
					++iState // move on to the wait state						
				ENDIF
			ENDIF

			//--- BAR & CLUB
			IF $txtEntryExit = &BAR1
			OR $txtEntryExit = &BAR2
			OR $txtEntryExit = &UFOBAR
				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT Bar_Staff.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT Bar_Staff.sc
				ENDIF

				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT Customer_Panic.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT Customer_Panic.sc
				ENDIF

				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT bar_ambience.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT bar_ambience.sc
				ENDIF

				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT Dance.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT Dance.sc
				ENDIF
				
				REQUEST_ANIMATION BAR
				IF $txtEntryExit = &BAR1
					REQUEST_ANIMATION DANCING
				ENDIF
				
				REQUEST_MODEL VWFYCRP

				LOAD_ALL_MODELS_NOW

				IF HAS_STREAMED_SCRIPT_LOADED bar_ambience.sc
					START_NEW_STREAMED_SCRIPT bar_ambience.sc
				ENDIF
				
				IF HAS_STREAMED_SCRIPT_LOADED Bar_Staff.sc 
				AND HAS_STREAMED_SCRIPT_LOADED Customer_Panic.sc
				AND HAS_STREAMED_SCRIPT_LOADED bar_ambience.sc
				AND HAS_STREAMED_SCRIPT_LOADED Dance.sc
					iCreateDecisionMakers = 1
					++iState // move on to the wait state						
				ENDIF
			ENDIF

			//--- OTB
			IF $txtEntryExit = &GENOTB
				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT OTB_Staff.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT OTB_Staff.sc
				ENDIF

				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT Customer_Panic.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT Customer_Panic.sc
				ENDIF

				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT OTB_ambience.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT OTB_ambience.sc
				ENDIF
				
				REQUEST_ANIMATION OTB
				REQUEST_ANIMATION INT_SHOP
				
				REQUEST_MODEL VBFYCRP
				REQUEST_MODEL VWFYCRP

				LOAD_ALL_MODELS_NOW

				IF HAS_STREAMED_SCRIPT_LOADED OTB_ambience.sc
					START_NEW_STREAMED_SCRIPT OTB_ambience.sc
				ENDIF
				
				IF HAS_STREAMED_SCRIPT_LOADED OTB_Staff.sc 
				AND HAS_STREAMED_SCRIPT_LOADED Customer_Panic.sc
				AND HAS_STREAMED_SCRIPT_LOADED OTB_ambience.sc
					iCreateDecisionMakers = 1
					++iState // move on to the wait state 						
				ENDIF
			ENDIF

			//--- STRIP CLUB
			IF $txtEntryExit = &LASTRIP
			OR $txtEntryExit = &STRIP2
				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT Bar_Staff.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT Bar_Staff.sc
				ENDIF

				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT Bouncer.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT Bouncer.sc
				ENDIF

				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT Customer_Panic.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT Customer_Panic.sc
				ENDIF

				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT Strip_ambience.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT Strip_ambience.sc
				ENDIF
				
				REQUEST_ANIMATION BAR
				REQUEST_ANIMATION STRIP
			
				REQUEST_MODEL BMYBOUN
				REQUEST_MODEL WMYBOUN
				REQUEST_MODEL VWFYCRP

				LOAD_ALL_MODELS_NOW

				IF HAS_STREAMED_SCRIPT_LOADED Strip_ambience.sc
					START_NEW_STREAMED_SCRIPT Strip_ambience.sc
				ENDIF

				IF HAS_STREAMED_SCRIPT_LOADED Bar_Staff.sc 
				AND HAS_STREAMED_SCRIPT_LOADED Bouncer.sc
				AND HAS_STREAMED_SCRIPT_LOADED Customer_Panic.sc
				AND HAS_STREAMED_SCRIPT_LOADED Strip_ambience.sc
					iCreateDecisionMakers = 1
					++iState // move on to the wait state						
				ENDIF
			ENDIF

			//--- CLOTHES SHOPS		
			IF player_using_wardrobe = 0				
				IF $txtEntryExit = cschp
				OR $txtEntryExit = cssprt
				OR $txtEntryExit = lacs1
				OR $txtEntryExit = clothgp
				OR $txtEntryExit = csdesgn
				OR $txtEntryExit = csexl

					GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT clothes.sc iStreamedScriptInstances
					IF iStreamedScriptInstances = 0
						STREAM_SCRIPT clothes.sc
					ENDIF
					
					REQUEST_ANIMATION INT_SHOP

					LOAD_ALL_MODELS_NOW

					IF HAS_STREAMED_SCRIPT_LOADED clothes.sc						
						iSetClothesPanic = 0
						START_NEW_STREAMED_SCRIPT clothes.sc
						iCreateDecisionMakers = 1
						++iState // move on to the wait state 							
					ENDIF
				ENDIF
			ENDIF

			//--- FAST FOOD SHOPS
			IF $txtEntryExit = &FDPIZA
			OR $txtEntryExit = &FDCHICK
			OR $txtEntryExit = &FDBURG
				
				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT junkfud.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT junkfud.sc
				ENDIF

				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT FoodBrains.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT FoodBrains.sc
				ENDIF

				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT ShopKeeper.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT ShopKeeper.sc
				ENDIF

				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT Customer_Panic.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT Customer_Panic.sc
				ENDIF
				
				REQUEST_ANIMATION FOOD
				REQUEST_ANIMATION INT_SHOP
				
				IF $txtEntryExit = &FDPIZA
					REQUEST_MODEL WMYPIZZ
					REQUEST_MODEL PIZZAHIGH
					REQUEST_MODEL CJ_PIZZA_1
					REQUEST_MODEL CJ_PIZZA_2
				ENDIF
				IF $txtEntryExit = &FDCHICK
					REQUEST_MODEL WMYBELL
					REQUEST_MODEL BURGERHIGH
					REQUEST_MODEL CJ_BURG_1
					REQUEST_MODEL CJ_BURG_2
				ENDIF
				IF $txtEntryExit = &FDBURG
					REQUEST_MODEL WFYBURG
					REQUEST_MODEL BURGERHIGH
					REQUEST_MODEL CJ_BURG_1
					REQUEST_MODEL CJ_BURG_2
				ENDIF
				
				LOAD_ALL_MODELS_NOW

				IF HAS_STREAMED_SCRIPT_LOADED junkfud.sc
					START_NEW_STREAMED_SCRIPT junkfud.sc
				ENDIF					

				IF HAS_STREAMED_SCRIPT_LOADED FoodBrains.sc	
					IF $txtEntryExit = &FDPIZA
						START_NEW_STREAMED_SCRIPT FoodBrains.sc	0
					ELSE
						IF $txtEntryExit = &FDCHICK
							START_NEW_STREAMED_SCRIPT FoodBrains.sc	1
						ELSE
							IF $txtEntryExit = &FDBURG
								START_NEW_STREAMED_SCRIPT FoodBrains.sc	2
							ENDIF
						ENDIF
					ENDIF									
				ENDIF

				IF HAS_STREAMED_SCRIPT_LOADED junkfud.sc 
				AND HAS_STREAMED_SCRIPT_LOADED FoodBrains.sc
				AND HAS_STREAMED_SCRIPT_LOADED ShopKeeper.sc
				AND HAS_STREAMED_SCRIPT_LOADED Customer_Panic.sc
					iCreateDecisionMakers = 1
					++iState // move on to the wait state 						
				ENDIF
			ENDIF

			//--- BARBER SHOPS
			IF $txtEntryExit = &BARBERS
			OR $txtEntryExit = &BARBER2
			OR $txtEntryExit = &BARBER3
				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT barber.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT barber.sc
					LOAD_ALL_MODELS_NOW
					IF HAS_STREAMED_SCRIPT_LOADED barber.sc
						START_NEW_STREAMED_SCRIPT barber.sc
						iCreateDecisionMakers = 1
						++iState // move on to the wait state							
					ENDIF
				ENDIF
			ENDIF

			//--- TATTOO SHOPS
			IF $txtEntryExit = &TATTOO
			OR $txtEntryExit = &TATTO2
			OR $txtEntryExit = &TATTO3
				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT tattoo.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT tattoo.sc
					LOAD_ALL_MODELS_NOW
					IF HAS_STREAMED_SCRIPT_LOADED tattoo.sc
						START_NEW_STREAMED_SCRIPT tattoo.sc
						iCreateDecisionMakers = 1
						++iState // move on to the wait state							
					ENDIF
				ENDIF
			ENDIF
		
			//--- ZERO's RC Shop
			IF $txtEntryExit = &RCPLAY
				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT Zero_ambience.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT Zero_ambience.sc
				ENDIF

				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT Customer_Panic.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT Customer_Panic.sc
				ENDIF

				REQUEST_MODEL WMYCLOT

				LOAD_ALL_MODELS_NOW

				IF HAS_STREAMED_SCRIPT_LOADED Zero_ambience.sc
					START_NEW_STREAMED_SCRIPT Zero_ambience.sc
				ENDIF
				
				IF HAS_STREAMED_SCRIPT_LOADED Zero_ambience.sc 
				AND HAS_STREAMED_SCRIPT_LOADED Customer_Panic.sc
					iCreateDecisionMakers = 1
					++iState // move on to the wait state						
				ENDIF
			ENDIF

			//--- POLICE
			IF $txtEntryExit = &POLICE1
			OR $txtEntryExit = &POLICE2
			OR $txtEntryExit = &POLICE3
			OR $txtEntryExit = &POLICE4
				iCheckForWeapons = 1
				iCreateDecisionMakers = 1
				REQUEST_ANIMATION COP_AMBIENT
				REQUEST_ANIMATION INT_OFFICE
				REQUEST_ANIMATION GANGS
				REQUEST_MODEL CIGAR
				LOAD_ALL_MODELS_NOW
				++iState // move on to the wait state					
			ENDIF
			
			//--- AMMUNATION
			IF $txtEntryExit = &AMMUN1
			OR $txtEntryExit = &AMMUN2
			OR $txtEntryExit = &AMMUN3
			OR $txtEntryExit = &AMMUN4
			OR $txtEntryExit = &AMMUN5
				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT ammu.sc iStreamedScriptInstances
				IF iStreamedScriptInstances = 0
					STREAM_SCRIPT ammu.sc
					LOAD_ALL_MODELS_NOW
					IF HAS_STREAMED_SCRIPT_LOADED ammu.sc
						START_NEW_STREAMED_SCRIPT ammu.sc
						iCreateDecisionMakers = 1
						++iState // move on to the wait state							
					ENDIF
				ENDIF
			ENDIF
		
			//-------------------------------
			// END OF ENTRY EXIT LABEL CHECKS
			//-------------------------------

			//--- See if we must create some special decision maker
			IF iCreateDecisionMakers = 1
				IF NOT DOES_DECISION_MAKER_EXIST iGlobalPedPanicDM
					COPY_CHAR_DECISION_MAKER  DM_PED_INDOORS iGlobalPedPanicDM
					CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE iGlobalPedPanicDM EVENT_ATTRACTOR					
					CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE iGlobalPedPanicDM EVENT_SCRIPTED_ATTRACTOR
				ENDIF
				IF NOT DOES_DECISION_MAKER_EXIST iGlobalShopkeeperDM
					COPY_CHAR_DECISION_MAKER  DM_PED_INDOORS iGlobalShopkeeperDM
					CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE iGlobalShopkeeperDM EVENT_ATTRACTOR					
					CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE iGlobalShopkeeperDM EVENT_SCRIPTED_ATTRACTOR
					CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE iGlobalShopkeeperDM EVENT_DAMAGE
				ENDIF
			ENDIF
		ENDIF
	ENDIF
RETURN
/********************************************
					EXIT
********************************************/
Int_Manager_EXIT:  

	GET_CHAR_AREA_VISIBLE scplayer iAreaCode
	IF NOT iAreaCode = iStoredAreaCode
		//--- Player has changed area code, mark all loaded stuff as ready for removal 
		GOSUB Int_Manager_MarkStuffNoLongerNeeded
		//--- Reset the global flags
		iTerminateAllAmbience = 0
		iAmbienceHasTerminated = 0
		iCheckForWeapons = 0
		GET_LOADED_SHOP shop_name 
		//--- Move on to the entry state
		--iState 
		RETURN
	ELSE
		//--- Player is still in the same area code
		IF iCheckForWeapons = 1
			IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_ANYMELEE
				ALTER_WANTED_LEVEL_NO_DROP PLAYER1 2
			ENDIF
		ENDIF
		IF iTerminateAllAmbience = 1
		AND iAmbienceHasTerminated = 0
			//--- Mark all loaded stuff as ready for removal 
			GOSUB Int_Manager_MarkStuffNoLongerNeeded
			//--- Hang in here until the area code changes			 
			iAmbienceHasTerminated = 1			
		ENDIF
	ENDIF
RETURN
/*******************************************
	MARK STREAMS AS NO LONGER NEEDED
********************************************/
Int_Manager_MarkStuffNoLongerNeeded:
	//--- Animations
	REMOVE_ANIMATION BAR
	REMOVE_ANIMATION CASINO
	REMOVE_ANIMATION DANCING
	REMOVE_ANIMATION OTB
	REMOVE_ANIMATION INT_SHOP
	REMOVE_ANIMATION STRIP
	REMOVE_ANIMATION FOOD
	REMOVE_ANIMATION COP_AMBIENT
	REMOVE_ANIMATION INT_OFFICE
	REMOVE_ANIMATION GANGS
	//--- Models
	MARK_MODEL_AS_NO_LONGER_NEEDED CIGAR		   
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF1
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF3
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF2
	MARK_MODEL_AS_NO_LONGER_NEEDED VWFYWAI
	MARK_MODEL_AS_NO_LONGER_NEEDED VWFYWA2
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYBOUN
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYBOUN
	MARK_MODEL_AS_NO_LONGER_NEEDED VWFYCRP			
	MARK_MODEL_AS_NO_LONGER_NEEDED VBFYCRP
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYPIZZ
	MARK_MODEL_AS_NO_LONGER_NEEDED PIZZAHIGH
	MARK_MODEL_AS_NO_LONGER_NEEDED CJ_PIZZA_1
	MARK_MODEL_AS_NO_LONGER_NEEDED CJ_PIZZA_2
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYBELL
	MARK_MODEL_AS_NO_LONGER_NEEDED BURGERHIGH
	MARK_MODEL_AS_NO_LONGER_NEEDED CJ_BURG_1
	MARK_MODEL_AS_NO_LONGER_NEEDED CJ_BURG_2
	MARK_MODEL_AS_NO_LONGER_NEEDED WFYBURG
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYCLOT
	//--- Shop scripts	
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED wardrobe.sc
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED clothes.sc
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED junkfud.sc
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED barber.sc
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED tattoo.sc
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED ammu.sc 
	//--- Ambience manager scripts
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED casino_ambience.sc
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED bar_ambience.sc
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED OTB_ambience.sc
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED Strip_ambience.sc
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED FoodBrains.sc
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED Zero_ambience.sc
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED Dance.sc
	//--- Ambient People scripts 
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED ShopKeeper.sc
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED Customer_Panic.sc
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED Bar_Staff.sc
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED Bouncer.sc
	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED OTB_Staff.sc	
	//--- Decision Makers					
	REMOVE_DECISION_MAKER iGlobalPedPanicDM
	REMOVE_DECISION_MAKER iGlobalShopkeeperDM
	iGlobalPedPanicDM = -1
	iGlobalShopkeeperDM = -1					
RETURN
}