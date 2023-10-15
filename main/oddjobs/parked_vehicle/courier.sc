MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* courier.sc ****************************************
// ****************************** bike courier parked vehicle ******************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME BCOUR

// Mission start stuff

GOSUB mission_start_bcou

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_bcou_failed
ENDIF

GOSUB mission_cleanup_bcou

MISSION_END

{ 

// Variables for mission

LVAR_INT bcou_helpflag

LVAR_INT bcou_visible


// Peds, vehicles and sequences

LVAR_INT bcou_bike


LVAR_INT bcou_package


LVAR_INT bcou_heavy


LVAR_INT bcou_emptydm

//blips

LVAR_INT bcou_bikeB
LVAR_INT bcou_homeB
LVAR_INT dropoffB[30]
LVAR_INT pickupB




//arrays

LVAR_INT dropoffdone[30]

LVAR_FLOAT SFdropoffX[30]					
LVAR_FLOAT SFdropoffY[30]
LVAR_FLOAT SFdropoffZ[30]

LVAR_FLOAT LAdropoffX[30]
LVAR_FLOAT LAdropoffY[30]
LVAR_FLOAT LAdropoffZ[30]

LVAR_FLOAT LVdropoffX[30]
LVAR_FLOAT LVdropoffY[30]
LVAR_FLOAT LVdropoffZ[30]




LVAR_INT pickupdone[10]

LVAR_FLOAT SFpickupX[30]
LVAR_FLOAT SFpickupY[30]
LVAR_FLOAT SFpickupZ[30]

LVAR_FLOAT LApickupX[30]
LVAR_FLOAT LApickupY[30]
LVAR_FLOAT LApickupZ[30]

LVAR_FLOAT LVpickupX[30]
LVAR_FLOAT LVpickupY[30]
LVAR_FLOAT LVpickupZ[30]


LVAR_INT bcou_delivered[30] // array of successful / unsuccessful delivery slots
LVAR_INT dropoffcorona[30]
LVAR_FLOAT coronaz[30]



//Various Ints

LVAR_INT bcou_packagesdelivered

LVAR_INT dropoffIndex
LVAR_INT pickupIndex

LVAR_INT bcou_progress

LVAR_INT bcou_blipswap

LVAR_INT bcou_cutscene
LVAR_INT bcou_ctskipneeded

LVAR_INT bcou_paymentseq


VAR_INT bcou_bikeostimer // off_biketimer
LVAR_INT timeradjustment
LVAR_INT lowtimeradjustment
VAR_INT bcou_timelimit   // limit for particular run

LVAR_INT bcou_backonbikecheck
LVAR_INT bcou_biketimer


LVAR_INT bcou_totalpackagesplus
LVAR_INT bcou_packagestodeliver 
LVAR_INT bcou_packagesthistrip
LVAR_INT bcou_totalpackages
LVAR_INT dropoffIndexOrigin

LVAR_INT bcou_SFrunscompleted
LVAR_INT bcou_LArunscompleted
LVAR_INT bcou_LVrunscompleted


LVAR_INT drugs_thrown[30]

VAR_INT bags_on_bike
LVAR_INT old_bag

LVAR_FLOAT courplayer1_facing_this_direction

LVAR_FLOAT drugs_lobx
LVAR_FLOAT drugs_loby

LVAR_INT this_bag_left[30]

LVAR_INT drugs_fx[30]
LVAR_INT drug_been_thrown_previously

LVAR_FLOAT playerx playery playerz


LVAR_INT initial_index

LVAR_INT outofbags

LVAR_INT bcou_menu

LVAR_INT bcou_menuonscreen

LVAR_INT row_counter

LVAR_INT menudelay

LVAR_INT contact_delete

LVAR_INT bcou_paymentskip
  	
LVAR_INT time_bonus bag_bonus total_bonus

LVAR_INT bcou_city
 
LVAR_INT maxpackages 
LVAR_INT packageslefttodeliver


LVAR_INT bcou_countbegun 

LVAR_FLOAT createX createY createZ

LVAR_INT proximity_flag	checked_proximity

LVAR_INT pass_check


LVAR_INT sfx_throw

// **************************************** Mission Start **********************************

mission_start_bcou:

IF NOT IS_CHAR_DEAD	scplayer
	GET_CITY_PLAYER_IS_IN player1 bcou_city
ENDIF



IF bcou_city = LEVEL_LOSANGELES
	IF courierLA_passed_once = 0
		REGISTER_MISSION_GIVEN
	ENDIF
ENDIF

IF bcou_city = LEVEL_LASVEGAS
	IF courierLV_passed_once = 0
		REGISTER_MISSION_GIVEN
	ENDIF
ENDIF

IF bcou_city = LEVEL_SANFRANCISCO
	IF courierSF_passed_once = 0
		REGISTER_MISSION_GIVEN
	ENDIF
ENDIF

flag_player_on_mission = 1
//flag_player_on_mission = 0

WAIT 0

// ****************************************START OF CUTSCENE********************************

// fades the screen in 

/*
LOAD_CUTSCENE casino_1
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE
 
CLEAR_CUTSCENE

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE
*/
// ****************************************END OF CUTSCENE**********************************

WAIT 0
	
LOAD_MISSION_TEXT BCOU
	
SET_FADING_COLOUR 0 0 0

//WAIT 500
CREATE_OBJECT kmb_packet -2492.5083 0.5813 24.7593 bcou_package

IF bcou_progress = -13


  	CREATE_CHAR PEDTYPE_MISSION1 WMYdrug -2455.6621 -105.3974 26.5568 bcou_heavy

ENDIF

   
//DO_FADE 500 FADE_IN

// request models

REQUEST_MODEL WAYFARER
REQUEST_MODEL WMYdrug
REQUEST_ANIMATION SMOKING

LOAD_ALL_MODELS_NOW


//REQUEST_MODEL SATCHEL
//WHILE NOT HAS_MODEL_LOADED SATCHEL
//	WAIT 0
//ENDWHILE

WHILE NOT HAS_MODEL_LOADED WAYFARER
	OR NOT HAS_MODEL_LOADED WMYdrug
	OR NOT HAS_ANIMATION_LOADED SMOKING
	WAIT 0
ENDWHILE



									   

IF NOT IS_CHAR_DEAD scplayer
	SET_PLAYER_CONTROL player1 ON
ENDIF

// ************************************ Declare Variables *********************************

//flags
bcou_countbegun = 0

sfx_throw = 0

proximity_flag = 0 // 0 means outwith 5 metres, 1 means close
checked_proximity = 0

menudelay = 0
contact_delete = 0
bcou_helpflag = 0

time_bonus = 0
bag_bonus = 0
total_bonus = 0

																					 
dropoffIndex = 0									
pickupIndex = 0
bcou_packagesdelivered = 0

bcou_progress = 0
//bcou_progress = -1 // skip old cutscene stuff

bcou_blipswap = 0

bcou_cutscene = 0
bcou_ctskipneeded = 0
bcou_paymentskip = 0


bcou_SFrunscompleted = 0
bcou_LArunscompleted = 0
bcou_LVrunscompleted = 0


SFpickupX[0] = -2594.8164 
SFpickupY[0] = 52.5992 
SFpickupZ[0] = 3.3359
//New SF home area


//SFpickupX[0] = -2590.4607 
//SFpickupY[0] = 73.2006
//SFpickupZ[0] = 3.4097
//Old Home area

SFdropoffX[0] = -2797.9521 
SFdropoffY[0] = 209.7299
SFdropoffZ[0] = 6.1963

SFdropoffX[1] = -2492.7710 	 	 
SFdropoffY[1] = -196.6625 
SFdropoffZ[1] = 24.6236

SFdropoffX[2] = -2611.9885 
SFdropoffY[2] = -110.7328 
SFdropoffZ[2] = 3.331
// End of sf run 1
						   
SFdropoffX[3] = -2176.3923 
SFdropoffY[3] = -41.7131 
SFdropoffZ[3] = 34.3125

SFdropoffX[4] = -2243.8965 //
SFdropoffY[4] = 603.6998 
SFdropoffZ[4] = 40.1216

//SFdropoffX[5] = -1576.8414 
//SFdropoffY[5] = 681.6105 
//SFdropoffZ[5] = 6.1874

SFdropoffX[5] = -1569.9940 
SFdropoffY[5] = 662.3286 
SFdropoffZ[5] = 6.1874  

SFdropoffX[6] = -2756.3708 
SFdropoffY[6] = 379.1574 
SFdropoffZ[6] = 3.3281

// End of sf run 2


SFdropoffX[7] = -2609.9016 
SFdropoffY[7] = 131.9432 
SFdropoffZ[7] = 3.3260

SFdropoffX[8] = -2743.8582 
SFdropoffY[8] = 761.4077 
SFdropoffZ[8] = 53.3828


SFdropoffX[9] = -2600.5430 
SFdropoffY[9] = 980.7458 
SFdropoffZ[9] = 77.2676

SFdropoffX[10] = -2153.9590 
SFdropoffY[10] = 716.1611 
SFdropoffZ[10] = 68.5511

SFdropoffX[11] = -2153.6042 
SFdropoffY[11] = 251.9152 
SFdropoffZ[11] = 34.3125

// End of sf run 3

SFdropoffX[12] = -2808.2854 
SFdropoffY[12] = -340.2412
SFdropoffZ[12] = 6.1797

SFdropoffX[13] = -2428.8459 
SFdropoffY[13] = -164.1249 
SFdropoffZ[13] = 34.3125

SFdropoffX[14] = -1984.2133 
SFdropoffY[14] = 128.7572 
SFdropoffZ[14] = 26.6797

SFdropoffX[15] = -1888.4598 
SFdropoffY[15] = 819.6346 
SFdropoffZ[15] = 34.1797

SFdropoffX[16] = -1503.3295 
SFdropoffY[16] = 919.9264 
SFdropoffZ[16] = 6.1776

SFdropoffX[17] = -2533.8845 
SFdropoffY[17] = 987.9146
SFdropoffZ[17] = 77.3017

// End of sf run 4

LApickupX[0] = 1346.0194 
LApickupY[0] = -1741.7805 
LApickupZ[0] = 12.397
//Home area

LAdropoffX[0] = 1286.7687
LAdropoffY[0] = -1676.1659
LAdropoffZ[0] = 12.5625

LAdropoffX[1] = 1544.0627 	 
LAdropoffY[1] = -1675.7537 
LAdropoffZ[1] = 12.5600

LAdropoffX[2] = 1320.0966  
LAdropoffY[2] = -1795.0992 
LAdropoffZ[2] = 12.5391
// End of LA run 1

LAdropoffX[3] = 1614.2609 
LAdropoffY[3] = -1885.7557 
LAdropoffZ[3] = 12.5536

LAdropoffX[4] = 2009.1731 
LAdropoffY[4] = -1716.9135 
LAdropoffZ[4] = 12.5469

LAdropoffX[5] = 1256.5126 
LAdropoffY[5] = -1010.9740 
LAdropoffZ[5] = 32.9002

LAdropoffX[6] = 1016.6732 
LAdropoffY[6] = -1508.7825
LAdropoffZ[6] = 12.5410
// End of LA run 2
				 


LAdropoffX[7] = 1286.7687
LAdropoffY[7] = -1676.1659
LAdropoffZ[7] = 12.5625

LAdropoffX[8] = 1544.0627 	 
LAdropoffY[8] = -1675.7537 
LAdropoffZ[8] = 12.5600

LAdropoffX[9] = 1320.0966  
LAdropoffY[9] = -1795.0992 
LAdropoffZ[9] = 12.5391


LAdropoffX[10] = 1614.2609 
LAdropoffY[10] = -1885.7557 
LAdropoffZ[10] = 12.5536

LAdropoffX[11] = 2009.1731 
LAdropoffY[11] = -1716.9135 
LAdropoffZ[11] = 12.5469
// End of LA run 3



//beginning of LA run 4

LAdropoffX[12] = 2865.3887 
LAdropoffY[12] = -1438.9569
LAdropoffZ[12] = 9.9659 

LAdropoffX[13] = 2740.5605 
LAdropoffY[13] = -2103.4417
LAdropoffZ[13] = 11.1221

LAdropoffX[14] = 1346.4553 
LAdropoffY[14] = -989.3849 
LAdropoffZ[14] = 28.0294

LAdropoffX[15] = 150.4926 
LAdropoffY[15] = -1945.3845
LAdropoffZ[15] = 2.7657    

LAdropoffX[16] = 378.0941 
LAdropoffY[16] = -2053.5847  
LAdropoffZ[16] = 7.0156 

 
LAdropoffX[17] = 804.5734 
LAdropoffY[17] = -1020.7144 
LAdropoffZ[17] = 25.6819

//End of LA run 4


LVpickupX[0] = 1904.6807 
LVpickupY[0] = 2096.1704 
LVpickupZ[0] = 9.8203
//Home area


LVdropoffX[0] = 1698.4038 
LVdropoffY[0] = 2054.8977
LVdropoffZ[0] = 9.8203


LVdropoffX[1] = 2159.8325
LVdropoffY[1] = 2066.7275
LVdropoffZ[1] = 9.8125 

LVdropoffX[2] = 1935.7007 
LVdropoffY[2] = 2158.4639 
LVdropoffZ[2] = 9.8203
// End of LV run 1


LVdropoffX[3]  = 1654.3308 
LVdropoffY[3] = 1803.8123 
LVdropoffZ[3] = 9.8125

LVdropoffX[4] = 2083.3247 
LVdropoffY[4] = 1503.7544 
LVdropoffZ[4] = 9.8125

LVdropoffX[5] = 1720.1105 
LVdropoffY[5] = 1298.5477 
LVdropoffZ[5] = 9.8203 

LVdropoffX[6] = 2233.8367 
LVdropoffY[6] = 1282.1949
LVdropoffZ[6] = 9.8203

// End of LV run 2

LVdropoffX[7] = 1698.4038 
LVdropoffY[7] = 2063.9690
LVdropoffZ[7] = 9.8203


LVdropoffX[8] = 2159.8325
LVdropoffY[8] = 2066.7275  
LVdropoffZ[8] = 9.8125 

LVdropoffX[9] = 1935.7007 
LVdropoffY[9] = 2158.4639 
LVdropoffZ[9] = 9.8203


LVdropoffX[10]  = 1654.3308 
LVdropoffY[10] = 1803.8123 
LVdropoffZ[10] = 9.8125

LVdropoffX[11] = 2083.3247 
LVdropoffY[11] = 1503.7544 
LVdropoffZ[11] = 9.8125

// End of LV run 3

LVdropoffX[12] = 1917.7623 
LVdropoffY[12] = 2768.8735 
LVdropoffZ[12] = 9.8203

LVdropoffX[13] = 2344.5691 
LVdropoffY[13] = 2779.9653 
LVdropoffZ[13] = 9.8203

LVdropoffX[14] = 973.1230 
LVdropoffY[14] = 1782.2382 
LVdropoffZ[14] = 7.6562

LVdropoffX[15] = 2346.5200 
LVdropoffY[15] = 2296.9734
LVdropoffZ[15] = 7.1478

LVdropoffX[16] = 1488.1754	 //home of the bandits
LVdropoffY[16] = 2148.4744 
LVdropoffZ[16] = 9.8203


LVdropoffX[17] = 1104.6967 	// stadium
LVdropoffY[17] = 1648.0645 
LVdropoffZ[17] = 4.8273

// End of LV run 4

//Not in array on purpose for testing
bcou_delivered[0] = 0
bcou_delivered[1] = 0
bcou_delivered[2] = 0
bcou_delivered[3] = 0
bcou_delivered[4] = 0
bcou_delivered[5] = 0
bcou_delivered[6] = 0
bcou_delivered[7] = 0
bcou_delivered[8] = 0
bcou_delivered[9] = 0
bcou_delivered[10] = 0
bcou_delivered[11] = 0
bcou_delivered[12] = 0
bcou_delivered[13] = 0
bcou_delivered[14] = 0
bcou_delivered[15] = 0
bcou_delivered[16] = 0
bcou_delivered[17] = 0
bcou_delivered[18] = 0
bcou_delivered[19] = 0
bcou_delivered[20] = 0
bcou_delivered[21] = 0
bcou_delivered[22] = 0
bcou_delivered[23] = 0
bcou_delivered[24] = 0
bcou_delivered[25] = 0
bcou_delivered[26] = 0
bcou_delivered[27] = 0
bcou_delivered[28] = 0

bcou_backonbikecheck = 99

// ****************************************************************************************
			 
//WRITE_DEBUG script_running
//CREATE_OBJECT kmb_packet -2492.5083 0.5813 24.7593 bcou_package


//CREATE_CAR WAYFARER -2485.6458 7.4586 24.6316 bcou_bike  //bcourier office coords
//SET_CAR_HEADING bcou_bike 159.0957
//CHANGE_CAR_COLOUR bcou_bike 55 1
//WARP_CHAR_INTO_CAR scplayer bcou_bike
//FORCE_CAR_LIGHTS bcou_bike FORCE_CAR_LIGHTS_OFF


//SET_CHAR_COORDINATES scplayer -2484.2043 49.2125 26.4078		 


SET_PLAYER_CAN_DO_DRIVE_BY player1 FALSE




														
IF NOT IS_PLAYER_PLAYING player1
	GOTO mission_bcou_failed
ENDIF


//PRINT_BIG BCOU_1 1000 1
//WAIT 4000



//SET_CAMERA_BEHIND_PLAYER
//RESTORE_CAMERA_JUMPCUT 

IF IS_CHAR_IN_ANY_CAR scplayer
   STORE_CAR_CHAR_IS_IN scplayer bcou_bike
ENDIF

//bcou_progress = 1
//DO_FADE 500 FADE_IN
//

/*
IF NOT IS_CHAR_DEAD scplayer
	SET_PLAYER_CONTROL player1 OFF
ENDIF
*/



//WAIT 2000



bcou_backonbikecheck = 99
bcou_biketimer = 0

bcou_totalpackagesplus = 0
bcou_totalpackages = 0
bcou_packagestodeliver = 0
bcou_packagesthistrip = 0
drug_been_thrown_previously = 0
outofbags = 0
pass_check = 0

	 
bcou_main_mission_loop:





//SWITCH_WIDESCREEN ON
WAIT 0




//Audio

IF sfx_throw = 0
	LOAD_MISSION_AUDIO 3 SOUND_BIKE_PACKAGE_THROW
	sfx_throw = 1
ENDIF


IF sfx_throw = 1
	IF HAS_MISSION_AUDIO_LOADED 3
	 	sfx_throw = 2
	ENDIF
ENDIF











IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_w
	   bcou_timelimit = bcou_timelimit + 60000
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_q
	   bcou_timelimit = bcou_timelimit - 45000
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_p
	   ALTER_WANTED_LEVEL Player1 2
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_o
	    ALTER_WANTED_LEVEL Player1 0
ENDIF


IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_x

		IF pass_check = 0
	 		pass_check = 1
		ENDIF

ENDIF


IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_z

		pass_check = 0

   
ENDIF



IF outofbags = 1
	CLEAR_ONSCREEN_TIMER bcou_timelimit
	CLEAR_ONSCREEN_COUNTER bags_on_bike

	PRINT_NOW BCOU_11 6000 1
	GOTO mission_bcou_failed
ENDIF


IF NOT IS_PLAYER_PLAYING player1
	GOTO mission_bcou_failed
ENDIF

IF IS_CAR_DEAD bcou_bike
	GOTO mission_bcou_failed
ENDIF


IF NOT IS_CAR_DEAD bcou_bike
	IF IS_CHAR_IN_CAR scplayer bcou_bike

		IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
			PRINT_NOW BCOU_35 6000 1
 			GOTO mission_bcou_failed
		ENDIF
	ENDIF
ENDIF


IF bcou_packagesdelivered = 10
	GOTO mission_bcou_passed
ENDIF


IF bcou_packagesdelivered = -99

	CREATE_OBJECT_NO_OFFSET kmb_packet 0.0 0.0 100.0 drugs_thrown[old_bag]  
	CREATE_FX_SYSTEM_ON_OBJECT coke_trail drugs_thrown[old_bag] 0.0 0.0 0.0 TRUE drugs_fx[old_bag]

ENDIF


IF bcou_ctskipneeded = 1 
	IF IS_BUTTON_PRESSED PAD1 CROSS
	   bcou_cutscene = 50
	ENDIF
ENDIF


IF bcou_paymentskip = 1 
	IF IS_BUTTON_PRESSED PAD1 CROSS
	   menudelay = 2
	   SET_PLAYER_CONTROL player1 ON
	   //bcou_progress = 50
	   bcou_paymentskip = 0
	ENDIF
ENDIF


IF bcou_backonbikecheck = 0
	
	IF NOT IS_CAR_DEAD bcou_bike

		IF NOT IS_CHAR_IN_CAR scplayer bcou_bike
			TIMERB = 0 
			bcou_biketimer = 1 // begin timer
			lowtimeradjustment = bcou_timelimit / 1000	 // get time for under twenty seconds check

			bcou_backonbikecheck = 1
			CLEAR_PRINTS
			//PRINT_NOW BCOU_37 4000 1
			//DISPLAY_ONSCREEN_COUNTER_WITH_STRING bcou_bikeostimer COUNTER_DISPLAY_NUMBER BCOU_31	 //fixing
			ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS FALSE

			PRINT_WITH_NUMBER_NOW BCOU_37 bcou_bikeostimer 4000 1
			CLEAR_HELP
			//PRINT_HELP_FOREVER BCOU_32
		ENDIF

	ENDIF
ENDIF


IF bcou_backonbikecheck = 1

	IF NOT IS_CAR_DEAD bcou_bike

		IF IS_CHAR_IN_CAR scplayer bcou_bike
			TIMERB = 0
			bcou_backonbikecheck = 0
			bcou_biketimer = 0
			bcou_countbegun = 0
			CLEAR_PRINTS
			CLEAR_HELP
			IF bcou_progress > 9
				PRINT_NOW BCOU_6 4000 1
			ENDIF
			IF bcou_progress < 10
				PRINT_NOW BCOU_3 4000 1
			ENDIF


			//CLEAR_ONSCREEN_COUNTER bcou_bikeostimer 
		ENDIF 

	ENDIF

ENDIF

IF bcou_biketimer = 1
	timeradjustment = TIMERB / 1000


//	IF bcou_timelimit < 21000
//	AND bcou_timelimit > 1
//	//lowtimeradjustment = bcou_timelimit / 1000
//   //bcou_bikeostimer = lowtimeradjustment - timeradjustment
//	
//		IF bcou_countbegun = 0
//			bcou_bikeostimer = bcou_timelimit / 1000
//		  	//bcou_countbegun = 1
//	   	ENDIF
//
//	ELSE
//
//	   	
//			bcou_bikeostimer = 20 - timeradjustment
//		 	bcou_countbegun = 1
//	   	
//
//	ENDIF





	IF bcou_countbegun = 0
		IF bcou_timelimit < 21000
		AND bcou_timelimit > 1
			bcou_countbegun = 1
		ENDIF
	ENDIF


	IF bcou_countbegun = 0
		IF bcou_timelimit > 20099
		   bcou_countbegun = 2
		ENDIF
	ENDIF


	IF bcou_countbegun = 1
		bcou_bikeostimer = bcou_timelimit / 1000
	ENDIF

	
	IF bcou_countbegun = 2
		bcou_bikeostimer = 20 - timeradjustment
	ENDIF





	CLEAR_PRINTS
	IF bcou_bikeostimer > 0
	ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS FALSE

	PRINT_WITH_NUMBER_NOW BCOU_37 bcou_bikeostimer 4000 1
	ENDIF
	IF TIMERB > 21000
		//CLEAR_ONSCREEN_COUNTER bcou_bikeostimer
		CLEAR_PRINTS
		PRINT_NOW BCOU_35 6000 1
		GOTO mission_bcou_failed
	ENDIF
ENDIF



IF bcou_progress = -1

		IF bcou_cutscene = 0
				//SET_PLAYER_CONTROL player1 OFF

				//DO_FADE 500 FADE_OUT
				//LOAD_SCENE -2491.2029 6.9828 24.6328
				LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY bcou_emptydm
				DELETE_CHAR bcou_heavy


				CLEAR_AREA -2455.6621 -105.3974 26.5568	10.0 TRUE

				CREATE_CHAR PEDTYPE_MISSION1 WMYdrug -2455.6621 -105.3974 26.5568 bcou_heavy
				//GIVE_WEAPON_TO_CHAR bcou_heavy WEAPONTYPE_SHOTGUN 2000 // biker boss
				SET_CHAR_HEADING bcou_heavy 176.0958
				SET_CHAR_DECISION_MAKER bcou_heavy bcou_emptydm

			
				//LOAD_SCENE -2491.2029 6.9828 24.6328

				//WAIT 1000
				SET_PLAYER_CONTROL player1 OFF

					
		//		IF NOT IS_CHAR_DEAD bcou_heavy
		//			TASK_PICK_UP_OBJECT bcou_heavy bcou_package 0.0 -0.2 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE
		//		ENDIF
				
			  		
			  	SWITCH_WIDESCREEN ON

		   		//SET_FIXED_CAMERA_POSITION -2481.5649 8.2399 26.3633 0.0 0.0 0.0
				//POINT_CAMERA_AT_POINT -2482.5618 8.2028 26.2935 JUMP_CUT
				 SET_FIXED_CAMERA_POSITION -2468.3928 -96.8149 26.1614 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2467.6929 -97.5246 26.0805 JUMP_CUT

				
				
				//DO_FADE 500 FADE_IN

			
				IF NOT IS_CHAR_DEAD bcou_heavy
					FLUSH_ROUTE
					EXTEND_ROUTE -2457.0398 -110.2905 25.0058

					EXTEND_ROUTE -2460.8452 -108.7783 24.9517 	
					EXTEND_ROUTE -2462.3708 -106.9304 24.9069
					//PRINT_NOW BCOU_2 4000 1
					TASK_FOLLOW_POINT_ROUTE bcou_heavy PEDMOVE_RUN FOLLOW_ROUTE_ONCE
					SET_FOLLOW_NODE_THRESHOLD_DISTANCE bcou_heavy 150.0
			  	ENDIF

				bcou_cutscene = 10
				
				bcou_ctskipneeded = 1
				timerb = 0
			 
		ENDIF


		 /*

		IF DOES_CHAR_EXIST bcou_heavy
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR bcou_heavy scplayer
				CLEAR_PRINTS
				PRINT_NOW BCOU_52 6000 1
				GOTO mission_bcou_failed
			ENDIF
		ENDIF
		*/


		IF bcou_cutscene = 10
			IF timerb > 4000
			IF NOT IS_CHAR_DEAD bcou_heavy
				
			  
				
				PRINT_NOW BCOU_2 4000 1
				

				//TASK_PLAY_ANIM bcou_heavy BOM_Plant_loop BOMBER 4.0 1 FALSE FALSE FALSE -1

				
				bcou_cutscene = 15
				TIMERB = 0
			ENDIF
			ENDIF
		ENDIF 


		IF bcou_cutscene = 15
			IF TIMERB > 1000
				IF NOT IS_CHAR_DEAD bcou_heavy
				//TASK_PICK_UP_OBJECT bcou_heavy bcou_package 0.0 0.15 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE 
				//DELETE_OBJECT bcou_package
				bcou_cutscene = 20
				TIMERB = 0
				ENDIF
			ENDIF
		ENDIF

		IF bcou_cutscene = 20
			IF TIMERB > 1000
				
			  	TIMERB = 0
				bcou_cutscene = 25
			ENDIF
		ENDIF


		IF bcou_cutscene = 25
			IF TIMERB > 200
			 IF NOT IS_CHAR_DEAD bcou_heavy
				CLEAR_CHAR_TASKS bcou_heavy
				//TASK_LOOK_AT_CHAR bcou_heavy scplayer 3400
				//TASK_LOOK_AT_CHAR scplayer bcou_heavy 3200

				TIMERB = 0
				//PRINT_NOW BCOU_3 4000 1
				bcou_cutscene = 40
			 ENDIF
			 ENDIF
		ENDIF

		IF bcou_cutscene = 40

			IF TIMERB > 1000 
				PRINT_NOW BCOU_3 4000 1
			ENDIF


			IF TIMERB > 4000
				IF NOT IS_CHAR_DEAD bcou_heavy
					CLEAR_CHAR_TASKS bcou_heavy
				 	TASK_GO_STRAIGHT_TO_COORD bcou_heavy -2457.0859 -126.3415 25.0130 PEDMOVE_RUN 5000
				
				ENDIF
			  	
				TIMERB = 0 
				CLEAR_PRINTS
				PRINT_NOW BCOU_1 4000 1
				bcou_cutscene = 45
			ENDIF
		ENDIF


		IF bcou_cutscene = 45
			IF TIMERB > 4000
			 bcou_cutscene = 50
			ENDIF
		ENDIF

		IF bcou_cutscene = 50
				CLEAR_PRINTS
			    bcou_ctskipneeded = 0
				//DO_FADE 500 FADE_OUT 
				//WAIT 1000
			  	IF NOT IS_CHAR_DEAD bcou_heavy
					CLEAR_CHAR_TASKS bcou_heavy
					SET_CHAR_COORDINATES bcou_heavy	 -2457.0859 -126.3415 25.0130
					SET_CHAR_HEADING bcou_heavy 180.0
					DELETE_OBJECT bcou_package
				ENDIF

				SWITCH_WIDESCREEN OFF 
				DELETE_OBJECT bcou_package
				//DO_FADE 500 FADE_IN
				//SET_CAMERA_BEHIND_PLAYER
				RESTORE_CAMERA_JUMPCUT
				SET_PLAYER_CONTROL player1 ON
				
				bcou_cutscene = 60
				timerb = 0
				//
				bcou_progress = 1
				//
				// bcou_progress = 25
				IF NOT IS_CAR_DEAD bcou_bike
					FORCE_CAR_LIGHTS bcou_bike NO_CAR_LIGHT_OVERRIDE
				ENDIF

				IF NOT IS_CHAR_DEAD bcou_heavy
					CLEAR_CHAR_TASKS bcou_heavy
					TASK_ACHIEVE_HEADING bcou_heavy 100.0
					PRINT_NOW BCOU_4 6000 1
				   //	PRINT_HELP BCOU_9
				ENDIF

				bcou_backonbikecheck = 0

				CREATE_OBJECT kmb_packet -2458.2485 -98.5704 28.1875 bcou_package
				SET_OBJECT_COLLISION bcou_package TRUE 	  
				SET_OBJECT_DYNAMIC bcou_package TRUE


		ENDIF

		IF bcou_cutscene = 60
			 IF timerb > 2000
				IF NOT IS_CHAR_DEAD bcou_heavy
					CLEAR_CHAR_TASKS bcou_heavy
					TASK_PLAY_ANIM bcou_heavy M_smkstnd_loop SMOKING 4.0 1 FALSE FALSE FALSE -1
					bcou_cutscene = 99
				ENDIF
			ENDIF
		ENDIF

ENDIF //bcou_progress = -1


IF bcou_progress = 0

  	GET_CITY_PLAYER_IS_IN player1 bcou_city

	IF bcou_city = LEVEL_LOSANGELES
		CREATE_OBJECT kmb_packet 1361.5579 -1764.2756 18.0781 bcou_package
		SET_OBJECT_COLLISION bcou_package TRUE 	  
		SET_OBJECT_DYNAMIC bcou_package TRUE

	   //	write_debug inla
    ENDIF

	IF bcou_city = LEVEL_SANFRANCISCO
		//write_debug insf
		CREATE_OBJECT kmb_packet -2584.6130 60.3094 3.9701 bcou_package
		SET_OBJECT_COLLISION bcou_package TRUE 	  
		SET_OBJECT_DYNAMIC bcou_package TRUE
		SWITCH_CAR_GENERATOR gen_car21 0
    ENDIF

	IF bcou_city = LEVEL_LASVEGAS
		CREATE_OBJECT kmb_packet 1884.3292 2078.7214 15.0869 bcou_package
		SET_OBJECT_COLLISION bcou_package TRUE 	  
		SET_OBJECT_DYNAMIC bcou_package TRUE
	   //	write_debug inlv
    ENDIF

	PRINT_NOW BCOU_4 6000 1
	//PRINT_HELP BCOU_9
	timera = 0
	bcou_helpflag = 1
	bcou_backonbikecheck = 0
	bcou_progress = 1

ENDIF

IF bcou_helpflag = 1
	IF timera > 7000
		bcou_helpflag = 0
		PRINT_HELP BCOU_84  // Motorcycles. The way to throw an item is however you do drive-by shooing (condidering mappings, vehicle type, which changes on bmx vs motorcycle).
	ENDIF
ENDIF

//
//Level 1
//

IF bcou_progress = 1
  
	  bcou_totalpackagesplus  = 4
	  bcou_totalpackages = 3

	  bcou_packagestodeliver = 3

	  dropoffIndex = 0
	  dropoffIndexOrigin = 0
	  bags_on_bike = 6
	  initial_index = 0
	  contact_delete = 0

	  WHILE initial_index < 7	// bags on bike + 1
	  	this_bag_left[initial_index] = 0
	  	initial_index ++
	  ENDWHILE



	  IF NOT IS_CAR_DEAD bcou_bike
	  IF IS_CHAR_IN_CAR scplayer bcou_bike


	  IF bcou_city = LEVEL_SANFRANCISCO
		  WHILE dropoffIndex < bcou_totalpackages
		 	   	ADD_BLIP_FOR_COORD SFdropoffX[dropoffIndex] SFdropoffY[dropoffIndex] SFdropoffZ[dropoffIndex] dropoffB[dropoffIndex]
				 coronaz[dropoffIndex] = SFdropoffZ[dropoffIndex] + 2.2 
				 CREATE_CHECKPOINT CHECKPOINT_TORUS_NOFADE SFdropoffX[dropoffIndex] SFdropoffY[dropoffIndex] coronaz[dropoffIndex] 0.0 0.0 0.0 1.8 dropoffcorona[dropoffIndex]
				++ dropOffIndex
		  ENDWHILE
	  ENDIF
	  
	  IF bcou_city = LEVEL_LOSANGELES
		  WHILE dropoffIndex < bcou_totalpackages
		 	   	ADD_BLIP_FOR_COORD LAdropoffX[dropoffIndex] LAdropoffY[dropoffIndex] LAdropoffZ[dropoffIndex] dropoffB[dropoffIndex]
				 coronaz[dropoffIndex] = LAdropoffZ[dropoffIndex] + 2.2 
				 CREATE_CHECKPOINT CHECKPOINT_TORUS_NOFADE LAdropoffX[dropoffIndex] LAdropoffY[dropoffIndex] coronaz[dropoffIndex] -2000.0 -2000.0 0.0 1.8 dropoffcorona[dropoffIndex]
				++ dropOffIndex
		  ENDWHILE
	  ENDIF
	  
	  IF bcou_city = LEVEL_LASVEGAS
		  WHILE dropoffIndex < bcou_totalpackages
		 	   	ADD_BLIP_FOR_COORD LVdropoffX[dropoffIndex] LVdropoffY[dropoffIndex] LVdropoffZ[dropoffIndex] dropoffB[dropoffIndex]
				 coronaz[dropoffIndex] = LVdropoffZ[dropoffIndex] + 2.2 
				 CREATE_CHECKPOINT CHECKPOINT_TORUS_NOFADE LVdropoffX[dropoffIndex] LVdropoffY[dropoffIndex] coronaz[dropoffIndex] -2000.0 2000.0 0.0 1.8 dropoffcorona[dropoffIndex]
				++ dropOffIndex
		  ENDWHILE
	  ENDIF
	


	  bcou_blipswap = 0
	  bcou_progress = 9
	  bcou_packagesthistrip = 0

	  bcou_timelimit = 180000//90000
	  DISPLAY_ONSCREEN_COUNTER_WITH_STRING bags_on_bike COUNTER_DISPLAY_NUMBER BCOU_83
	  DISPLAY_ONSCREEN_TIMER_WITH_STRING bcou_timelimit TIMER_DOWN BCOU_34

	  ENDIF
	  ENDIF

ENDIF

//
//Level 2
//
IF bcou_progress = 2
  
	  bcou_totalpackagesplus  = 8
	  bcou_totalpackages = 7 // 3 packages delivered in level 1 so total packages delivered will equal seven at end of this level

	  bcou_packagestodeliver = 4 // four packages to be delivered during this run.

	  dropoffIndex = 3
	  dropoffIndexOrigin = 3
	  bags_on_bike = 6
	  initial_index = 0
	  contact_delete = 0

	  WHILE initial_index < 7	// bags on bike + 1
	  	this_bag_left[initial_index] = 0
	  	initial_index ++
	  ENDWHILE


	  IF NOT IS_CAR_DEAD bcou_bike
	  IF IS_CHAR_IN_CAR scplayer bcou_bike


	  IF bcou_city = LEVEL_SANFRANCISCO
		  WHILE dropoffIndex < bcou_totalpackages
		 	   	ADD_BLIP_FOR_COORD SFdropoffX[dropoffIndex] SFdropoffY[dropoffIndex] SFdropoffZ[dropoffIndex] dropoffB[dropoffIndex]
				 coronaz[dropoffIndex] = SFdropoffZ[dropoffIndex] + 2.2 
				 CREATE_CHECKPOINT CHECKPOINT_TORUS_NOFADE SFdropoffX[dropoffIndex] SFdropoffY[dropoffIndex] coronaz[dropoffIndex] 0.0 0.0 0.0 1.8 dropoffcorona[dropoffIndex]
				++ dropOffIndex
		  ENDWHILE
	  ENDIF
	  
	  IF bcou_city = LEVEL_LOSANGELES
		  WHILE dropoffIndex < bcou_totalpackages
		 	   	ADD_BLIP_FOR_COORD LAdropoffX[dropoffIndex] LAdropoffY[dropoffIndex] LAdropoffZ[dropoffIndex] dropoffB[dropoffIndex]
				 coronaz[dropoffIndex] = LAdropoffZ[dropoffIndex] + 2.2 
				 CREATE_CHECKPOINT CHECKPOINT_TORUS_NOFADE LAdropoffX[dropoffIndex] LAdropoffY[dropoffIndex] coronaz[dropoffIndex] -2000.0 -2000.0 0.0 1.8 dropoffcorona[dropoffIndex]
				++ dropOffIndex
		  ENDWHILE
	  ENDIF

	  IF bcou_city = LEVEL_LASVEGAS
		  WHILE dropoffIndex < bcou_totalpackages
		 	   	ADD_BLIP_FOR_COORD LVdropoffX[dropoffIndex] LVdropoffY[dropoffIndex] LVdropoffZ[dropoffIndex] dropoffB[dropoffIndex]
				 coronaz[dropoffIndex] = LVdropoffZ[dropoffIndex] + 2.2 
				 CREATE_CHECKPOINT CHECKPOINT_TORUS_NOFADE LVdropoffX[dropoffIndex] LVdropoffY[dropoffIndex] coronaz[dropoffIndex] -2000.0 2000.0 0.0 1.8 dropoffcorona[dropoffIndex]
				++ dropOffIndex
		  ENDWHILE
	  ENDIF

  	  
	  bcou_blipswap = 0
	  bcou_progress = 9
	  bcou_packagesthistrip = 0

	  bcou_timelimit = 300000//90000
	  DISPLAY_ONSCREEN_COUNTER_WITH_STRING bags_on_bike COUNTER_DISPLAY_NUMBER BCOU_83

	  DISPLAY_ONSCREEN_TIMER_WITH_STRING bcou_timelimit TIMER_DOWN BCOU_34

	  ENDIF
	  ENDIF

ENDIF

//
//Level 3
//
IF bcou_progress = 3
  
	  bcou_totalpackagesplus  = 13
	  bcou_totalpackages = 12 // 3,4 packages delivered in level 1,2 so total packages delivered will equal 12 at end of this level

	  bcou_packagestodeliver = 5 // 5 packages to be delivered during this run.

	  dropoffIndex = 7	  // delivering to indices 7, 8, 9, 10, 11
	  dropoffIndexOrigin = 7
	  bags_on_bike = 7
	  initial_index = 0
	  contact_delete = 0

	  WHILE initial_index < 8	// bags on bike + 1
	  	this_bag_left[initial_index] = 0
	  	initial_index ++
	  ENDWHILE

	  IF NOT IS_CAR_DEAD bcou_bike
	  IF IS_CHAR_IN_CAR scplayer bcou_bike

	  IF bcou_city = LEVEL_SANFRANCISCO
		  WHILE dropoffIndex < bcou_totalpackages
		 	   	ADD_BLIP_FOR_COORD SFdropoffX[dropoffIndex] SFdropoffY[dropoffIndex] SFdropoffZ[dropoffIndex] dropoffB[dropoffIndex]
				 coronaz[dropoffIndex] = SFdropoffZ[dropoffIndex] + 2.2 
				 CREATE_CHECKPOINT CHECKPOINT_TORUS_NOFADE SFdropoffX[dropoffIndex] SFdropoffY[dropoffIndex] coronaz[dropoffIndex] 0.0 0.0 0.0 1.8 dropoffcorona[dropoffIndex]
				++ dropOffIndex
		  ENDWHILE
	  ENDIF
	  
	  IF bcou_city = LEVEL_LOSANGELES
		  WHILE dropoffIndex < bcou_totalpackages
		 	   	ADD_BLIP_FOR_COORD LAdropoffX[dropoffIndex] LAdropoffY[dropoffIndex] LAdropoffZ[dropoffIndex] dropoffB[dropoffIndex]
				 coronaz[dropoffIndex] = LAdropoffZ[dropoffIndex] + 2.2 
				 CREATE_CHECKPOINT CHECKPOINT_TORUS_NOFADE LAdropoffX[dropoffIndex] LAdropoffY[dropoffIndex] coronaz[dropoffIndex] -2000.0 -2000.0 0.0 1.8 dropoffcorona[dropoffIndex]
				++ dropOffIndex
		  ENDWHILE
	  ENDIF

	  IF bcou_city = LEVEL_LASVEGAS
		  WHILE dropoffIndex < bcou_totalpackages
		 	   	ADD_BLIP_FOR_COORD LVdropoffX[dropoffIndex] LVdropoffY[dropoffIndex] LVdropoffZ[dropoffIndex] dropoffB[dropoffIndex]
				 coronaz[dropoffIndex] = LVdropoffZ[dropoffIndex] + 2.2 
				 CREATE_CHECKPOINT CHECKPOINT_TORUS_NOFADE LVdropoffX[dropoffIndex] LVdropoffY[dropoffIndex] coronaz[dropoffIndex] -2000.0 2000.0 0.0 1.8 dropoffcorona[dropoffIndex]
				++ dropOffIndex
		  ENDWHILE
	  ENDIF

	  
	  bcou_blipswap = 0
	  bcou_progress = 9
	  bcou_packagesthistrip = 0

	  bcou_timelimit = 300000//90000
	  DISPLAY_ONSCREEN_COUNTER_WITH_STRING bags_on_bike COUNTER_DISPLAY_NUMBER BCOU_83

	  DISPLAY_ONSCREEN_TIMER_WITH_STRING bcou_timelimit TIMER_DOWN BCOU_34

      ENDIF
	  ENDIF
	

ENDIF

//
//Level 4
//
IF bcou_progress = 4
  
	  bcou_totalpackagesplus  = 19
	  bcou_totalpackages = 18 // 3,4,5 packages delivered in level 1,2,3 so total packages delivered will equal 12 at end of this level

	  bcou_packagestodeliver = 6 // 6 packages to be delivered during this run.

	  dropoffIndex = 12	  // delivering to indices 12, 13, 14, 15, 16, 17
	  dropoffIndexOrigin = 12
	  bags_on_bike = 8
	  initial_index = 0
	  contact_delete = 0

	  WHILE initial_index < 9	// bags on bike + 1
	  	this_bag_left[initial_index] = 0
	  	initial_index ++
	  ENDWHILE


	  IF NOT IS_CAR_DEAD bcou_bike
	  IF IS_CHAR_IN_CAR scplayer bcou_bike

	  IF bcou_city = LEVEL_SANFRANCISCO
		  WHILE dropoffIndex < bcou_totalpackages
		 	   	ADD_BLIP_FOR_COORD SFdropoffX[dropoffIndex] SFdropoffY[dropoffIndex] SFdropoffZ[dropoffIndex] dropoffB[dropoffIndex]
				 coronaz[dropoffIndex] = SFdropoffZ[dropoffIndex] + 2.2 
				 CREATE_CHECKPOINT CHECKPOINT_TORUS_NOFADE SFdropoffX[dropoffIndex] SFdropoffY[dropoffIndex] coronaz[dropoffIndex] 0.0 0.0 0.0 1.8 dropoffcorona[dropoffIndex]
				++ dropOffIndex
		  ENDWHILE
	  ENDIF
	  
	  IF bcou_city = LEVEL_LOSANGELES
		  WHILE dropoffIndex < bcou_totalpackages
		 	   	ADD_BLIP_FOR_COORD LAdropoffX[dropoffIndex] LAdropoffY[dropoffIndex] LAdropoffZ[dropoffIndex] dropoffB[dropoffIndex]
				 coronaz[dropoffIndex] = LAdropoffZ[dropoffIndex] + 2.2 
				 CREATE_CHECKPOINT CHECKPOINT_TORUS_NOFADE LAdropoffX[dropoffIndex] LAdropoffY[dropoffIndex] coronaz[dropoffIndex] -2000.0 -2000.0 0.0 1.8 dropoffcorona[dropoffIndex]
				++ dropOffIndex
		  ENDWHILE
	  ENDIF

	  IF bcou_city = LEVEL_LASVEGAS
		  WHILE dropoffIndex < bcou_totalpackages
		 	   	ADD_BLIP_FOR_COORD LVdropoffX[dropoffIndex] LVdropoffY[dropoffIndex] LVdropoffZ[dropoffIndex] dropoffB[dropoffIndex]
				 coronaz[dropoffIndex] = LVdropoffZ[dropoffIndex] + 2.2 
				 CREATE_CHECKPOINT CHECKPOINT_TORUS_NOFADE LVdropoffX[dropoffIndex] LVdropoffY[dropoffIndex] coronaz[dropoffIndex] -2000.0 2000.0 0.0 1.8 dropoffcorona[dropoffIndex]
				++ dropOffIndex
		  ENDWHILE
	  ENDIF

	  
	  bcou_blipswap = 0
	  bcou_progress = 9
	  bcou_packagesthistrip = 0

	  IF bcou_city = LEVEL_LOSANGELES
	 	 bcou_timelimit = 600000// 10 minutes
	  ENDIF

	  IF bcou_city = LEVEL_SANFRANCISCO
		 bcou_timelimit = 420000	// 7 minutes
	  ENDIF

	  IF bcou_city = LEVEL_LASVEGAS
		 bcou_timelimit = 480000  // 8 minutes
	  ENDIF


	  DISPLAY_ONSCREEN_COUNTER_WITH_STRING bags_on_bike COUNTER_DISPLAY_NUMBER BCOU_83

	  DISPLAY_ONSCREEN_TIMER_WITH_STRING bcou_timelimit TIMER_DOWN BCOU_34

	  ENDIF
	  ENDIF

ENDIF


IF bcou_progress = 9

			IF contact_delete = 0
				IF NOT IS_CHAR_DEAD bcou_heavy
					IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bcou_heavy 100.0 100.0 100.0 FALSE 
						DELETE_CHAR bcou_heavy
						contact_delete = 1
					ENDIF
				ENDIF
			ENDIF


					

			IF NOT IS_CAR_DEAD bcou_bike
				IF bcou_blipswap = 0

					IF IS_BUTTON_PRESSED PAD1 RIGHTSHOULDER2
						IF IS_BUTTON_PRESSED PAD1 CIRCLE
							proximity_flag = 0
							GOSUB drugs_throw_right
							GOSUB drugs_delivered_ok
						ENDIF
					ENDIF

					IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER2
						IF IS_BUTTON_PRESSED PAD1 CIRCLE
							proximity_flag = 0
							GOSUB drugs_throw_left
							GOSUB drugs_delivered_ok
						ENDIF
					ENDIF


					IF NOT IS_CAR_DEAD bcou_bike
					IF NOT IS_CHAR_IN_CAR scplayer bcou_bike
						ADD_BLIP_FOR_CAR bcou_bike bcou_bikeB
						SET_BLIP_AS_FRIENDLY bcou_bikeB TRUE

						WHILE dropoffIndex < bcou_totalpackagesplus
						    REMOVE_BLIP dropoffB[dropoffIndex]
						    ++ dropoffIndex
	                    ENDWHILE
						dropoffIndex = dropoffIndexOrigin																																												                                                                       
	 					
						bcou_blipswap = 1												  
					ENDIF
					ENDIF
						

				ENDIF // bcou_blipswap = 0 condition check

				IF bcou_blipswap = 1
																								   
					IF IS_CHAR_IN_CAR scplayer bcou_bike
						REMOVE_BLIP bcou_bikeB
						
						WHILE dropoffIndex < bcou_totalpackages

							IF bcou_delivered[dropoffIndex] = 0
								IF bcou_city = LEVEL_SANFRANCISCO
								ADD_BLIP_FOR_COORD SFdropoffX[dropoffIndex] SFdropoffY[dropoffIndex] SFdropoffZ[dropoffIndex] dropoffB[dropoffIndex]
						   		ENDIF
								IF bcou_city = LEVEL_LOSANGELES
								ADD_BLIP_FOR_COORD LAdropoffX[dropoffIndex] LAdropoffY[dropoffIndex] LAdropoffZ[dropoffIndex] dropoffB[dropoffIndex]
						   		ENDIF
								IF bcou_city = LEVEL_LASVEGAS
								ADD_BLIP_FOR_COORD LVdropoffX[dropoffIndex] LVdropoffY[dropoffIndex] LVdropoffZ[dropoffIndex] dropoffB[dropoffIndex]
						   		ENDIF
						   	
						   	ENDIF
					   	
					   	++ dropoffIndex																	   

						ENDWHILE
					  	
					  	bcou_blipswap = 0
													
						dropoffIndex = dropoffIndexOrigin	
					ENDIF
																															  //0.5 0.5 2.5
				ENDIF

				IF IS_CHAR_IN_CAR scplayer bcou_bike
				
						IF bcou_city = LEVEL_SANFRANCISCO
						WHILE dropoffIndex < bcou_totalpackages

					   		IF bcou_delivered[dropoffIndex] = 0
								IF DOES_OBJECT_EXIST drugs_thrown[old_bag]
							   	IF LOCATE_OBJECT_3D drugs_thrown[old_bag] SFdropoffX[dropoffIndex] SFdropoffY[dropoffIndex] coronaz[dropoffIndex] 0.7 1.7 3.0 FALSE	 //0.7 1.7 3.0
								  	//ADD_ONE_OFF_SOUND SFdropoffX[dropoffIndex] SFdropoffY[dropoffIndex] coronaz[dropoffIndex] SOUND_RACE_START_GO

								  		REMOVE_BLIP dropoffB[dropoffIndex]
										KILL_FX_SYSTEM drugs_fx[old_bag]  // bag successful, so remove it
					  	 				DELETE_OBJECT  drugs_thrown[old_bag]


										INCREMENT_INT_STAT PIZZAS_DELIVERED 1
										REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE



										DELETE_CHECKPOINT dropoffcorona[dropoffIndex]
									   //	WRITE_DEBUG_WITH_INT doi dropoffindex
										bcou_delivered[dropoffIndex] = 1
									   	++ bcou_packagesthistrip
										//WRITEDEBUG_WITH_INT packages_delivered_this_trip bcou_packagesthistrip
										IF bcou_packagesthistrip = bcou_packagestodeliver
										  	CLEAR_PRINTS 
										  	PRINT_NOW BCOU_6 6000 1
										  	bcou_progress = 10
										ELSE
											PRINT_NOW BCOU_5 6000 1
										ENDIF
									
								ENDIF
								ENDIF
							ENDIF
					 

						++ dropoffIndex
						ENDWHILE
						ENDIF

						IF bcou_city = LEVEL_LOSANGELES
						WHILE dropoffIndex < bcou_totalpackages

					   		IF bcou_delivered[dropoffIndex] = 0
								IF DOES_OBJECT_EXIST drugs_thrown[old_bag]
							   	IF LOCATE_OBJECT_3D drugs_thrown[old_bag] LAdropoffX[dropoffIndex] LAdropoffY[dropoffIndex] coronaz[dropoffIndex] 1.2 1.7 3.0 FALSE	//0.7 1.7 3.0
								  	//ADD_ONE_OFF_SOUND LAdropoffX[dropoffIndex] LAdropoffY[dropoffIndex] coronaz[dropoffIndex] SOUND_RACE_START_GO

								  		REMOVE_BLIP dropoffB[dropoffIndex]
										KILL_FX_SYSTEM drugs_fx[old_bag]  // bag successful, so remove it
					  	 				DELETE_OBJECT  drugs_thrown[old_bag]


										INCREMENT_INT_STAT PIZZAS_DELIVERED 1
										REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE



										DELETE_CHECKPOINT dropoffcorona[dropoffIndex]
									   //	WRITE_DEBUG_WITH_INT doi dropoffindex
										bcou_delivered[dropoffIndex] = 1
									   	++ bcou_packagesthistrip
										//WRITEDEBUG_WITH_INT packages_delivered_this_trip bcou_packagesthistrip
										IF bcou_packagesthistrip = bcou_packagestodeliver
										  	CLEAR_PRINTS 
										  	PRINT_NOW BCOU_6 6000 1
										  	bcou_progress = 10
										ELSE
											PRINT_NOW BCOU_5 6000 1
										ENDIF
									
								ENDIF
								ENDIF
							ENDIF
					 

						++ dropoffIndex
						ENDWHILE
						ENDIF

						IF bcou_city = LEVEL_LASVEGAS
						WHILE dropoffIndex < bcou_totalpackages

					   		IF bcou_delivered[dropoffIndex] = 0
								IF DOES_OBJECT_EXIST drugs_thrown[old_bag]
							   	IF LOCATE_OBJECT_3D drugs_thrown[old_bag] LVdropoffX[dropoffIndex] LVdropoffY[dropoffIndex] coronaz[dropoffIndex] 1.2 1.7 3.0 FALSE
								   //	ADD_ONE_OFF_SOUND LVdropoffX[dropoffIndex] LVdropoffY[dropoffIndex] coronaz[dropoffIndex] SOUND_RACE_START_GO

								  		REMOVE_BLIP dropoffB[dropoffIndex]
										KILL_FX_SYSTEM drugs_fx[old_bag]  // bag successful, so remove it
					  	 				DELETE_OBJECT  drugs_thrown[old_bag]



										INCREMENT_INT_STAT PIZZAS_DELIVERED 1
										REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE


										DELETE_CHECKPOINT dropoffcorona[dropoffIndex]
										

									   //	WRITE_DEBUG_WITH_INT doi dropoffindex
										bcou_delivered[dropoffIndex] = 1
									   	++ bcou_packagesthistrip
										//WRITEDEBUG_WITH_INT packages_delivered_this_trip bcou_packagesthistrip
										IF bcou_packagesthistrip = bcou_packagestodeliver
										  	CLEAR_PRINTS 
										  	PRINT_NOW BCOU_6 6000 1
										  	bcou_progress = 10
										ELSE
											PRINT_NOW BCOU_5 6000 1
										ENDIF
									
								ENDIF
								ENDIF
							ENDIF
					 

						++ dropoffIndex
						ENDWHILE
						ENDIF

						

					dropoffIndex = dropoffIndexOrigin
				ENDIF


				packageslefttodeliver = bcou_packagestodeliver - bcou_packagesthistrip
				 //write_debug_with_int poss maxpackages
				// write_debug_with_int left packageslefttodeliver

				maxpackages = bags_on_bike // player might have thrown one and be able to pick it up.
				
				IF DOES_OBJECT_EXIST drugs_thrown[old_bag]
				   maxpackages = maxpackages + 1
				ENDIF

				IF packageslefttodeliver > maxpackages
				 outofbags = 1
				 //write_debug failed
				ENDIF
				
			ENDIF

ENDIF // bcou_progress = 1 condition check

IF bcou_progress > 8
	IF bcou_progress < 25
		IF bcou_timelimit < 1
			CLEAR_PRINTS 
			PRINT_NOW BCOU_33 6000 1
			CLEAR_ONSCREEN_TIMER bcou_timelimit
			GOTO mission_bcou_failed
		ENDIF
	ENDIF
ENDIF

IF bcou_progress = 10 
	
	bcou_progress = 20

 	IF bcou_city = LEVEL_SANFRANCISCO
		ADD_BLIP_FOR_COORD SFpickupX[pickupIndex] SFpickupY[pickupIndex] SFpickupZ[pickupIndex] pickupB
	ENDIF	 

	IF bcou_city = LEVEL_LOSANGELES
		ADD_BLIP_FOR_COORD LApickupX[pickupIndex] LApickupY[pickupIndex] LApickupZ[pickupIndex] pickupB
	ENDIF	

	IF bcou_city = LEVEL_LASVEGAS
		ADD_BLIP_FOR_COORD LVpickupX[pickupIndex] LVpickupY[pickupIndex] LVpickupZ[pickupIndex] pickupB
	ENDIF	
	

ENDIF // bcou_progress = 1 condition check


IF bcou_progress = 20
	
	GET_AREA_VISIBLE bcou_visible
   	IF bcou_visible = 0
 		IF NOT IS_MINIGAME_IN_PROGRESS	
			SET_PLAYER_CONTROL player1 ON
	 	ENDIF
	ENDIF


	IF NOT IS_CAR_DEAD bcou_bike
		IF bcou_blipswap = 0
			IF NOT IS_CHAR_IN_CAR scplayer bcou_bike
				ADD_BLIP_FOR_CAR bcou_bike bcou_bikeB
				SET_BLIP_AS_FRIENDLY bcou_bikeB TRUE
				REMOVE_BLIP pickupB
				bcou_blipswap = 1
			ENDIF
		ENDIF

		IF bcou_blipswap = 1
			IF IS_CHAR_IN_CAR scplayer bcou_bike
				REMOVE_BLIP bcou_bikeB
				
				IF bcou_city = LEVEL_SANFRANCISCO
				    ADD_BLIP_FOR_COORD SFpickupX[pickupIndex] SFpickupY[pickupIndex] SFpickupZ[pickupIndex] pickupB
				ENDIF

				IF bcou_city = LEVEL_LASVEGAS
				    ADD_BLIP_FOR_COORD LVpickupX[pickupIndex] LVpickupY[pickupIndex] LVpickupZ[pickupIndex] pickupB
				ENDIF

				IF bcou_city = LEVEL_LOSANGELES
				    ADD_BLIP_FOR_COORD LApickupX[pickupIndex] LApickupY[pickupIndex] LApickupZ[pickupIndex] pickupB
				ENDIF

				bcou_blipswap = 0
			ENDIF
		ENDIF

		IF bcou_city = LEVEL_SANFRANCISCO
			//CLEAR_AREA -2590.4607 73.2006 3.4097 5.0 TRUE

			IF IS_CHAR_IN_CAR scplayer bcou_bike
			IF LOCATE_CHAR_IN_CAR_3D scplayer SFpickupX[pickupIndex] SFpickupY[pickupIndex] SFpickupZ[pickupIndex] 4.0 4.0 4.0 TRUE
				SET_PLAYER_CONTROL player1 OFF

				IF IS_CAR_STOPPED bcou_bike
					//SET_PLAYER_CONTROL player1 OFF 
					REMOVE_BLIP pickupB
					CLEAR_ONSCREEN_TIMER bcou_timelimit
					CLEAR_ONSCREEN_COUNTER bags_on_bike
								
					++ bcou_SFrunscompleted
					
					bcou_progress = 50
				ENDIF
			ENDIF
			ENDIF
		ENDIF

		IF bcou_city = LEVEL_LOSANGELES
			//CLEAR_AREA -2590.4607 73.2006 3.4097 5.0 TRUE

			IF IS_CHAR_IN_CAR scplayer bcou_bike
			IF LOCATE_CHAR_IN_CAR_3D scplayer LApickupX[pickupIndex] LApickupY[pickupIndex] LApickupZ[pickupIndex] 4.0 4.0 4.0 TRUE
				SET_PLAYER_CONTROL player1 OFF

				IF IS_CAR_STOPPED bcou_bike
					//SET_PLAYER_CONTROL player1 OFF 
					REMOVE_BLIP pickupB
					CLEAR_ONSCREEN_TIMER bcou_timelimit
					CLEAR_ONSCREEN_COUNTER bags_on_bike
								
					++ bcou_LArunscompleted

					bcou_progress = 50
				ENDIF
			ENDIF
			ENDIF
		ENDIF

		IF bcou_city = LEVEL_LASVEGAS
			//CLEAR_AREA -2590.4607 73.2006 3.4097 5.0 TRUE

			IF IS_CHAR_IN_CAR scplayer bcou_bike
			IF LOCATE_CHAR_IN_CAR_3D scplayer LVpickupX[pickupIndex] LVpickupY[pickupIndex] LVpickupZ[pickupIndex] 4.0 4.0 4.0 TRUE
				SET_PLAYER_CONTROL player1 OFF

				IF IS_CAR_STOPPED bcou_bike
					//SET_PLAYER_CONTROL player1 OFF 
					REMOVE_BLIP pickupB
					CLEAR_ONSCREEN_TIMER bcou_timelimit
					CLEAR_ONSCREEN_COUNTER bags_on_bike
								
					++ bcou_LVrunscompleted

					bcou_progress = 50
				ENDIF
			ENDIF
			ENDIF
		ENDIF

	ENDIF
   
ENDIF // bcou_progress = 20 condition check


IF bcou_progress = 50	 // Display menu and calculate player scores.

	SET_PLAYER_CONTROL player1 ON

	bcou_paymentskip = 0
	CLEAR_PRINTS
	PRINT_NOW BCOU_12 4000 1

	IF bcou_city = LEVEL_LOSANGELES
		CREATE_MENU BCOU_81 31.0 80.0 99.0 2 FALSE TRUE FO_LEFT bcou_menu
	  	time_bonus = bcou_timelimit	/ 100
		time_bonus = time_bonus * bcou_LArunscompleted
    ENDIF

    IF bcou_city = LEVEL_LASVEGAS
		CREATE_MENU BCOU_82 31.0 80.0 99.0 2 FALSE TRUE FO_LEFT bcou_menu
	 	time_bonus = bcou_timelimit	/ 100
		time_bonus = time_bonus * bcou_LVrunscompleted
	ENDIF

	IF bcou_city = LEVEL_SANFRANCISCO
		CREATE_MENU BCOU_80 31.0 80.0 99.0 2 FALSE TRUE FO_LEFT bcou_menu
	  	time_bonus = bcou_timelimit	/ 100
		time_bonus = time_bonus * bcou_SFrunscompleted
	ENDIF

	SET_MENU_COLUMN bcou_menu 0 BCOU_21 BCOU_22 BCOU_23 BCOU_24 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
	SET_MENU_COLUMN_ORIENTATION bcou_menu 1 FO_RIGHT

	row_counter = 0

   	bag_bonus = bags_on_bike * 100
    total_bonus = time_bonus + bag_bonus

	SET_MENU_ITEM_WITH_NUMBER bcou_menu 1 0 DOLLAR time_bonus
	SET_MENU_ITEM_WITH_NUMBER bcou_menu 1 1 DOLLAR bag_bonus
	SET_MENU_ITEM_WITH_NUMBER bcou_menu 1 2 DOLLAR total_bonus
	SET_ACTIVE_MENU_ITEM bcou_menu 2

	timera = 0
	menudelay = 1

	ADD_SCORE player1 total_bonus	
    DELETE_OBJECT bcou_package
    bcou_progress = 55

ENDIF


IF menudelay = 1
 
	IF timera > 2000
		bcou_paymentskip = 1
	ENDIF
	
	IF bcou_LArunscompleted = 4
		IF timera > 4000
			DELETE_MENU bcou_menu
			menudelay = 2
		ENDIF
	ENDIF
	
	IF bcou_SFrunscompleted = 4
		IF timera > 4000
			DELETE_MENU bcou_menu
			menudelay = 2
		ENDIF
	ENDIF
	
	IF bcou_LVrunscompleted = 4
		IF timera > 4000
			DELETE_MENU bcou_menu
			menudelay = 2
		ENDIF
	ENDIF

	IF timera > 6000
		DELETE_MENU bcou_menu
		menudelay = 2
	ENDIF
ENDIF


IF menudelay = 2

	IF NOT IS_CAR_DEAD bcou_bike
	IF IS_CHAR_IN_CAR scplayer bcou_bike

		DELETE_MENU bcou_menu

	    bcou_paymentskip = 0

	    IF bcou_city = LEVEL_SANFRANCISCO
	 	 	
			CLEAR_PRINTS
			
			SET_PLAYER_CONTROL player1 ON
	 	 	
			IF bcou_SFrunscompleted = 1 
				PRINT_NOW BCOU_4 6000 1
				bcou_progress = 2
			ENDIF
			
			IF bcou_SFrunscompleted = 2
				PRINT_NOW BCOU_4 6000 1
				bcou_progress = 3
			ENDIF
			
			IF bcou_SFrunscompleted = 3
				PRINT_NOW BCOU_4 6000 1
				bcou_progress = 4
			ENDIF
			
			IF bcou_SFrunscompleted = 4
				GOTO mission_bcou_passed
				bcou_progress = 5
			ENDIF
	    ENDIF

	    IF bcou_city = LEVEL_LOSANGELES
	 	 	CLEAR_PRINTS
			SET_PLAYER_CONTROL player1 ON

	 	 	IF bcou_LArunscompleted = 1 
			
				PRINT_NOW BCOU_4 6000 1
				bcou_progress = 2
			ENDIF
			IF bcou_LArunscompleted = 2
				PRINT_NOW BCOU_4 6000 1
				bcou_progress = 3
			ENDIF
			IF bcou_LArunscompleted = 3
				PRINT_NOW BCOU_4 6000 1
				bcou_progress = 4
			ENDIF
			
			IF bcou_LArunscompleted = 4
			    GOTO mission_bcou_passed
				bcou_progress = 5
			ENDIF
	    ENDIF

		IF bcou_city = LEVEL_LASVEGAS
		
		 	CLEAR_PRINTS
			SET_PLAYER_CONTROL player1 ON

		 	IF bcou_LVrunscompleted = 1 
				PRINT_NOW BCOU_4 6000 1
				bcou_progress = 2
			ENDIF

			IF bcou_LVrunscompleted = 2
				PRINT_NOW BCOU_4 6000 1
				bcou_progress = 3
			ENDIF

		   	IF bcou_LVrunscompleted = 3
				PRINT_NOW BCOU_4 6000 1
				bcou_progress = 4
			ENDIF
			
			IF bcou_LVrunscompleted = 4
			    GOTO mission_bcou_passed
				bcou_progress = 5
			ENDIF
		ENDIF

		menudelay = 0
		
		ENDIF
	ENDIF
ENDIF


IF DOES_OBJECT_EXIST drugs_thrown[old_bag]
	
	IF LOCATE_CHAR_ANY_MEANS_OBJECT_3D scplayer drugs_thrown[old_bag] 1.5 1.5 1.5 FALSE
		  IF timera > 3000
		  	  KILL_FX_SYSTEM drugs_fx[old_bag]
		  	  DELETE_OBJECT  drugs_thrown[old_bag]
		   	  bags_on_bike ++
			  this_bag_left[bags_on_bike] = 0
				//   write_debug_with_int bags bags_on_bike
		  ENDIF

     ENDIF
ENDIF



GOTO bcou_main_mission_loop

//Procedures/////////////////////////////////////////////////////////////////////////////

   	drugs_throw_right:
		
			IF NOT IS_CAR_DEAD bcou_bike
			IF bags_on_bike > 0
				IF this_bag_left[bags_on_bike] = 0
					IF drug_been_thrown_previously > 0
					KILL_FX_SYSTEM drugs_fx[old_bag]
					//GET_OBJECT_COORDINATES drugs_fx[old_bag] bcou_dummyX bcou_dummyY bcou_dummyZ
					//CREATE_OBJECT_NO_OFFSET kmb_packet 0.0 0.0 100.0 dummy_bag[dummy_index]
					DELETE_OBJECT  drugs_thrown[old_bag]
					ENDIF

				   
					//CREATE_OBJECT_NO_OFFSET kmb_packet 0.0 0.0 100.0 drugs_thrown[bags_on_bike]  
					//WRITE_DEBUG_WITH_INT drug_launchedright bags_on_bike
					

					IF bcou_city = LEVEL_LOSANGELES
						WHILE dropoffIndex < bcou_totalpackages


							IF checked_proximity = 0
					   		IF bcou_delivered[dropoffIndex] = 0
								
							   	IF LOCATE_CHAR_ANY_MEANS_3D scplayer LAdropoffX[dropoffIndex] LAdropoffY[dropoffIndex] coronaz[dropoffIndex] 5.0 5.0 5.0 FALSE	//0.7 1.7 3.0
							   		//WRITE_DEBUG close
									checked_proximity = 1// don't check any other coronas
									proximity_flag = 1 // player is close by a corona.
								  //	ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 0.1
							   	ELSE
									//WRITE_DEBUG ok
									
									//ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 6.0
						   		ENDIF
							ENDIF
					 		ENDIF 
							


						++ dropoffIndex
						ENDWHILE
						dropoffIndex = dropoffIndexOrigin  // reset for next throw
						checked_proximity = 0 // reset for next throw

					ENDIF



					IF bcou_city = LEVEL_SANFRANCISCO
						WHILE dropoffIndex < bcou_totalpackages


							IF checked_proximity = 0
					   		IF bcou_delivered[dropoffIndex] = 0
								
							   	IF LOCATE_CHAR_ANY_MEANS_3D scplayer SFdropoffX[dropoffIndex] SFdropoffY[dropoffIndex] coronaz[dropoffIndex] 5.0 5.0 5.0 FALSE	//0.7 1.7 3.0
							   		//WRITE_DEBUG close
									checked_proximity = 1// don't check any other coronas
									proximity_flag = 1 // player is close by a corona.
								  //	ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 0.1
							   	ELSE
									//WRITE_DEBUG ok
									
									//ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 6.0
						   		ENDIF
							ENDIF
					 		ENDIF 
							


						++ dropoffIndex
						ENDWHILE
						dropoffIndex = dropoffIndexOrigin  // reset for next throw
						checked_proximity = 0 // reset for next throw

					ENDIF


					IF bcou_city = LEVEL_LASVEGAS
						WHILE dropoffIndex < bcou_totalpackages


							IF checked_proximity = 0
					   		IF bcou_delivered[dropoffIndex] = 0
								
							   	IF LOCATE_CHAR_ANY_MEANS_3D scplayer LVdropoffX[dropoffIndex] LVdropoffY[dropoffIndex] coronaz[dropoffIndex] 5.0 5.0 5.0 FALSE	//0.7 1.7 3.0
							   	   //	WRITE_DEBUG close
									checked_proximity = 1// don't check any other coronas
									proximity_flag = 1 // player is close by a corona.
								  //	ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 0.1
							   	ELSE
								   //	WRITE_DEBUG ok
									
									//ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 6.0
						   		ENDIF
							ENDIF
					 		ENDIF 
							


						++ dropoffIndex
						ENDWHILE
						dropoffIndex = dropoffIndexOrigin  // reset for next throw
						checked_proximity = 0 // reset for next throw

					ENDIF



					GET_CAR_COORDINATES bcou_bike createX createY createZ
					CREATE_OBJECT_NO_OFFSET kmb_packet createX createY createZ drugs_thrown[bags_on_bike]
					SET_OBJECT_COLLISION drugs_thrown[bags_on_bike] TRUE 	  
				  	SET_OBJECT_DYNAMIC drugs_thrown[bags_on_bike] TRUE


					//WAIT 1000

					
					
					
					CREATE_FX_SYSTEM_ON_OBJECT coke_puff drugs_thrown[bags_on_bike] 0.0 0.0 0.5 TRUE drugs_fx[bags_on_bike] //directly above
					 PLAY_FX_SYSTEM	drugs_fx[bags_on_bike]
					GET_CHAR_COORDINATES scplayer playerx playery playerz
				   //	ADD_ONE_OFF_SOUND playerx playery playerz SOUND_PART_MISSION_COMPLETE

					IF sfx_throw = 2
						//write_debug test
						PLAY_MISSION_AUDIO 3
					ENDIF



					IF proximity_flag = 1

					PLACE_OBJECT_RELATIVE_TO_CAR drugs_thrown[bags_on_bike] bcou_bike -1.0 0.0 1.3//0.1 0.0 0.7//0.7 0.0 0.7

					ELSE

					PLACE_OBJECT_RELATIVE_TO_CAR drugs_thrown[bags_on_bike] bcou_bike 0.0 0.0 0.7//was -2.0

					ENDIF


				   	GET_CHAR_HEADING scplayer courplayer1_facing_this_direction 
				  	drugs_lobx -= 30.0
					drugs_loby -= 30.0
				  	COS courplayer1_facing_this_direction drugs_lobx  
				  	SIN courplayer1_facing_this_direction drugs_loby 
				   //	drugs_lobx *= 28.0//8.0 	 //change this to alter height of trajectory
				   //	drugs_loby *= 28.0//8.0	 







				   IF proximity_flag = 1

					drugs_lobx *= 15.0//8.0 	 //change this to alter height of trajectory
					drugs_loby *= 15.0//8.0	 
				   	ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 1.0//0.2//0.05

				   ELSE
				   
					drugs_lobx *= 30.0//8.0 	 //change this to alter height of trajectory
					drugs_loby *= 30.0//8.0	 


				   	ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 8.5//6.5
	
				   ENDIF

				   proximity_flag = 0 // reset for another throw

				   
				   //	Original for backup purposes
				   //	ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 7.0//5.0
				   //	ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 0.1 // short range

					
					
					
					
					
					drug_been_thrown_previously ++

					this_bag_left[bags_on_bike] = 1
				  	old_bag  = bags_on_bike
				  			

					bags_on_bike --
					/*
				    IF bags_on_bike > 1
					bags_on_bike --
					ELSE
					bags_on_bike --
					//WRITE_DEBUG outtadrugs
					CLEAR_ONSCREEN_COUNTER bags_on_bike
	  				CLEAR_ONSCREEN_TIMER bcou_timelimit 
					outofbags = 1
					

				   //	GOTO mission_bcou_failed
					
					ENDIF
					*/
					timera = 0
				  //	GOSUB drugs_old_bag						
				ENDIF
			ENDIF
			ENDIF
		

	RETURN


	///////////////////////////////////////////////////////////////////////////////////


	drugs_throw_left:

			IF bags_on_bike > 0
			IF NOT IS_CAR_DEAD bcou_bike
				IF this_bag_left[bags_on_bike] = 0
					IF drug_been_thrown_previously > 0
					KILL_FX_SYSTEM drugs_fx[old_bag]
					DELETE_OBJECT  drugs_thrown[old_bag]
					ENDIF


	
					IF bcou_city = LEVEL_LOSANGELES
						WHILE dropoffIndex < bcou_totalpackages


							IF checked_proximity = 0
					   		IF bcou_delivered[dropoffIndex] = 0
								
							   	IF LOCATE_CHAR_ANY_MEANS_3D scplayer LAdropoffX[dropoffIndex] LAdropoffY[dropoffIndex] coronaz[dropoffIndex] 5.0 5.0 5.0 FALSE	//0.7 1.7 3.0
							   		//WRITE_DEBUG close
									checked_proximity = 1// don't check any other coronas
									proximity_flag = 1 // player is close by a corona.
								  //	ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 0.1
							   	ELSE
									//WRITE_DEBUG ok
									
									//ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 6.0
						   		ENDIF
							ENDIF
					 		ENDIF 
							


						++ dropoffIndex
						ENDWHILE
						dropoffIndex = dropoffIndexOrigin  // reset for next throw
						checked_proximity = 0 // reset for next throw

					ENDIF



					IF bcou_city = LEVEL_SANFRANCISCO
						WHILE dropoffIndex < bcou_totalpackages


							IF checked_proximity = 0
					   		IF bcou_delivered[dropoffIndex] = 0
								
							   	IF LOCATE_CHAR_ANY_MEANS_3D scplayer SFdropoffX[dropoffIndex] SFdropoffY[dropoffIndex] coronaz[dropoffIndex] 5.0 5.0 5.0 FALSE	//0.7 1.7 3.0
							   		//WRITE_DEBUG close
									checked_proximity = 1// don't check any other coronas
									proximity_flag = 1 // player is close by a corona.
								  //	ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 0.1
							   	ELSE
									//WRITE_DEBUG ok
									
									//ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 6.0
						   		ENDIF
							ENDIF
					 		ENDIF 
							


						++ dropoffIndex
						ENDWHILE
						dropoffIndex = dropoffIndexOrigin  // reset for next throw
						checked_proximity = 0 // reset for next throw

					ENDIF


					IF bcou_city = LEVEL_LASVEGAS
						WHILE dropoffIndex < bcou_totalpackages


							IF checked_proximity = 0
					   		IF bcou_delivered[dropoffIndex] = 0
								
							   	IF LOCATE_CHAR_ANY_MEANS_3D scplayer LVdropoffX[dropoffIndex] LVdropoffY[dropoffIndex] coronaz[dropoffIndex] 5.0 5.0 5.0 FALSE	//0.7 1.7 3.0
							   		//WRITE_DEBUG close
									checked_proximity = 1// don't check any other coronas
									proximity_flag = 1 // player is close by a corona.
								  //	ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 0.1
							   	ELSE
									//WRITE_DEBUG ok
									
									//ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 6.0
						   		ENDIF
							ENDIF
					 		ENDIF 
							


						++ dropoffIndex
						ENDWHILE
						dropoffIndex = dropoffIndexOrigin  // reset for next throw
						checked_proximity = 0 // reset for next throw

					ENDIF


					GET_CAR_COORDINATES bcou_bike createX createY createZ
					CREATE_OBJECT_NO_OFFSET kmb_packet createX createY createZ drugs_thrown[bags_on_bike]

					SET_OBJECT_COLLISION drugs_thrown[bags_on_bike] TRUE 	  
				  	SET_OBJECT_DYNAMIC drugs_thrown[bags_on_bike] TRUE

				   //	WAIT 1000

				 

			  		//CREATE_OBJECT_NO_OFFSET kmb_packet 0.0 0.0 100.0 drugs_thrown[bags_on_bike]  
			  		//WRITE_DEBUG_WITH_INT drug_launchedleft bags_on_bike
					CREATE_FX_SYSTEM_ON_OBJECT coke_puff drugs_thrown[bags_on_bike]  0.0 0.0 0.0 TRUE drugs_fx[bags_on_bike] //directly above
					PLAY_FX_SYSTEM	drugs_fx[bags_on_bike]

					GET_CHAR_COORDINATES scplayer playerx playery playerz
				   //	ADD_ONE_OFF_SOUND playerx playery playerz SOUND_PART_MISSION_COMPLETE
					IF sfx_throw = 2
					   //	write_debug testaudio
						PLAY_MISSION_AUDIO 3
					ENDIF


					IF proximity_flag = 1
 
					PLACE_OBJECT_RELATIVE_TO_CAR drugs_thrown[bags_on_bike] bcou_bike 1.0 0.0 1.3//was 1.0 0.0 0.7

					ELSE

					PLACE_OBJECT_RELATIVE_TO_CAR drugs_thrown[bags_on_bike] bcou_bike 0.0 0.0 0.7//was 2.0

					ENDIF



				   	GET_CHAR_HEADING scplayer courplayer1_facing_this_direction 
				  	drugs_lobx -= 330.0
					drugs_loby -= 330.0																		 
				  	COS courplayer1_facing_this_direction drugs_lobx  
				  	SIN courplayer1_facing_this_direction drugs_loby 



					IF proximity_flag = 1

						drugs_lobx *= -15.0//8.0 	 //change this to alter height of trajectory
						drugs_loby *= -15.0//8.0	 
				   		ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 1.0//0.2//0.05 //0.1

				  	 ELSE
				   
						drugs_lobx *= -30.0//-28.0 	 //change this to alter height of trajectory
						drugs_loby *= -30.0//-28.0	 


				   		ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 8.5//6.5
	
				   	ENDIF

				   	proximity_flag = 0 // reset for another throw






				   //	drugs_lobx *= -28.0//-8.0	//was -6.0
				   //	drugs_loby *= -28.0//-8.0	//was -6.0
				   //	ADD_TO_OBJECT_VELOCITY drugs_thrown[bags_on_bike] drugs_lobx drugs_loby 7.0//5.0
					
					
					
					
					
					
					
					drug_been_thrown_previously ++
					this_bag_left[bags_on_bike] = 1
					old_bag  = bags_on_bike

					
					
					bags_on_bike --
					/*
					IF bags_on_bike > 1
				   	bags_on_bike --
					ELSE
					bags_on_bike --
					CLEAR_ONSCREEN_COUNTER bags_on_bike
	  				CLEAR_ONSCREEN_TIMER bcou_timelimit 

				   //	WRITE_DEBUG outtadrugs

					outofbags = 1
					
					//GOTO mission_bcou_failed

					ENDIF
					*/
					timera = 0
				   //	GOSUB drugs_old_bag	
				  
				ENDIF
			ENDIF
			ENDIF
	   
	RETURN

	////////////////////////////////////////////////////////////////////////////////////
	 
	drugs_delivered_ok:
	
		WHILE IS_BUTTON_PRESSED PAD1 CIRCLE 
			WAIT 0
		ENDWHILE
	RETURN 


	////////////////////////////////////////////////////////////////////////////////////
	drugs_old_bag:
		WHILE timerb < 3000
			WAIT 0
		ENDWHILE
		KILL_FX_SYSTEM drugs_fx[old_bag]
		DELETE_OBJECT  drugs_thrown[old_bag]
	RETURN



 // **************************************** Mission bcou failed ***********************

mission_bcou_failed:
SET_PLAYER_CAN_DO_DRIVE_BY player1 TRUE

//PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"

IF bcou_city = LEVEL_SANFRANCISCO
	IF bcou_SFrunscompleted = 0
		PRINT_BIG bcou_40 5000 1
	ENDIF

	IF bcou_SFrunscompleted = 1
		PRINT_BIG bcou_41 5000 1
	ENDIF

	IF bcou_SFrunscompleted = 2
		PRINT_BIG bcou_42 5000 1
	ENDIF

	IF bcou_SFrunscompleted = 3
		PRINT_BIG bcou_43 5000 1
	ENDIF

	IF bcou_SFrunscompleted = 4
		PRINT_BIG bcou_44 5000 1
	ENDIF

	IF bcou_SFrunscompleted = 5
		PRINT_BIG bcou_45 5000 1
	ENDIF

	IF bcou_SFrunscompleted = 6
		PRINT_BIG bcou_46 5000 1
	ENDIF

	IF bcou_SFrunscompleted = 7
	  	PRINT_BIG bcou_47 5000 1
	ENDIF
ENDIF

IF bcou_city = LEVEL_LASVEGAS
	IF bcou_LVrunscompleted = 0
		PRINT_BIG bcou_40 5000 1
	ENDIF

	IF bcou_LVrunscompleted = 1
		PRINT_BIG bcou_41 5000 1
	ENDIF

	IF bcou_LVrunscompleted = 2
		PRINT_BIG bcou_42 5000 1
	ENDIF

	IF bcou_LVrunscompleted = 3
		PRINT_BIG bcou_43 5000 1
	ENDIF

	IF bcou_LVrunscompleted = 4
		PRINT_BIG bcou_44 5000 1
	ENDIF

	IF bcou_LVrunscompleted = 5
		PRINT_BIG bcou_45 5000 1
	ENDIF

	IF bcou_LVrunscompleted = 6
		PRINT_BIG bcou_46 5000 1
	ENDIF

	IF bcou_LVrunscompleted = 7
	  	PRINT_BIG bcou_47 5000 1
	ENDIF
ENDIF

IF bcou_city = LEVEL_LOSANGELES
	IF bcou_LArunscompleted = 0
		PRINT_BIG bcou_40 5000 1
	ENDIF

	IF bcou_LArunscompleted = 1
		PRINT_BIG bcou_41 5000 1
	ENDIF

	IF bcou_LArunscompleted = 2
		PRINT_BIG bcou_42 5000 1
	ENDIF

	IF bcou_LArunscompleted = 3
		PRINT_BIG bcou_43 5000 1
	ENDIF

	IF bcou_LArunscompleted = 4
		PRINT_BIG bcou_44 5000 1
	ENDIF

	IF bcou_LArunscompleted = 5
		PRINT_BIG bcou_45 5000 1
	ENDIF

	IF bcou_LArunscompleted = 6
		PRINT_BIG bcou_46 5000 1
	ENDIF

	IF bcou_LArunscompleted = 7
	  	PRINT_BIG bcou_47 5000 1
	ENDIF
ENDIF

RETURN
			
   

// **************************************** mission bcou passed ************************

mission_bcou_passed:

//flag_heist_mission_counter ++ //Used to terminate this mission loop in the main script. These varibles will be set up in the main.sc
//REGISTER_MISSION_PASSED ( HOOD_3 ) //Used in the stats 

CLEAR_MISSION_AUDIO 3

PLAY_MISSION_PASSED_TUNE 2


IF bcou_city = LEVEL_LASVEGAS
	//PRINT_BIG BCOU_50 5000 1
	IF courierLV_passed_once = 0 
		REGISTER_ODDJOB_MISSION_PASSED
		PLAYER_MADE_PROGRESS 1
		courierLV_passed_once = 1

		CREATE_PROTECTION_PICKUP 1887.9968 2073.0081 10.5547 courierLV_revenue courierLV_revenue courierLV_cash_pickup //remember to create the pick 0.5 higher than the dropped coords

		CLEAR_PRINTS
		SET_PLAYER_CONTROL player1 OFF
		//SWITCH_WIDESCREEN ON

		DO_FADE 500 FADE_OUT

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		SWITCH_WIDESCREEN ON


		LOAD_SCENE_IN_DIRECTION 1895.5298 2063.0935 15.8639	47.0
		SET_FIXED_CAMERA_POSITION 1895.5298 2063.0935 15.8639 0.0 0.0 0.0 //cut scene of building
		POINT_CAMERA_AT_POINT 1894.8131 2063.7754 15.7175 JUMP_CUT
		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		PLAY_MISSION_PASSED_TUNE 1
	   	PRINT_BIG ASS_ACQ 5000 6 //CAR ASSET ACQUIRED

		WAIT 5000
		SET_FIXED_CAMERA_POSITION 1890.1636 2070.4780 11.8641 0.0 0.0 0.0 //cut scene showing pickup
		POINT_CAMERA_AT_POINT 1889.5161 2071.2131 11.6633  JUMP_CUT
		PRINT_WITH_NUMBER_NOW ASS_LUV courierLV_revenue 6000 1//This will now generate revenue up to a maximum of $~1~. Make sure you collect it regulary.

		WAIT 6000
		DO_FADE 500 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		

	ENDIF

ENDIF

IF bcou_city = LEVEL_LOSANGELES
   //	PRINT_BIG BCOU_49 5000 1
	IF courierLA_passed_once = 0 
		REGISTER_ODDJOB_MISSION_PASSED
		PLAYER_MADE_PROGRESS 1
		courierLA_passed_once = 1

		CREATE_PROTECTION_PICKUP 1345.8591 -1757.4316 13.0078 courierLA_revenue courierLA_revenue courierLA_cash_pickup //remember to create the pick 0.5 higher than the dropped coords

		CLEAR_PRINTS
		SET_PLAYER_CONTROL player1 OFF
		//SWITCH_WIDESCREEN ON

		DO_FADE 500 FADE_OUT

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		SWITCH_WIDESCREEN ON


		LOAD_SCENE_IN_DIRECTION 1358.5959 -1741.1840 18.4310 223.0
		SET_FIXED_CAMERA_POSITION 1358.5959 -1741.1840 18.4310 0.0 0.0 0.0 //cut scene of building
		POINT_CAMERA_AT_POINT 1358.3717 -1742.1354 18.2203 JUMP_CUT
		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		PLAY_MISSION_PASSED_TUNE 1
		PRINT_BIG ASS_ACQ 5000 6 //CAR ASSET ACQUIRED

		WAIT 5000
		SET_FIXED_CAMERA_POSITION 1345.5647 -1753.3677 15.2186 0.0 0.0 0.0 //cut scene showing pickup
		POINT_CAMERA_AT_POINT 1345.5670 -1754.2177 14.6920 JUMP_CUT
		PRINT_WITH_NUMBER_NOW ASS_LUV courierLA_revenue 6000 1//This will now generate revenue up to a maximum of $~1~. Make sure you collect it regulary.

		WAIT 6000
		DO_FADE 500 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

	ENDIF
ENDIF

IF bcou_city = LEVEL_SANFRANCISCO
   //	PRINT_BIG BCOU_48 5000 1
	IF courierSF_passed_once = 0 
		PLAYER_MADE_PROGRESS 1
		REGISTER_ODDJOB_MISSION_PASSED
	
		courierSF_passed_once = 1
	   //	write_debug passed
	   	CREATE_PROTECTION_PICKUP -2593.8818 59.2467 3.8359 courierSF_revenue courierSF_revenue courierSF_cash_pickup //remember to create the pick 0.5 higher than the dropped coords

		SET_PLAYER_CONTROL player1 OFF
		CLEAR_PRINTS
		
		
		//SWITCH_WIDESCREEN ON


		DO_FADE 500 FADE_OUT

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		SWITCH_WIDESCREEN ON


		LOAD_SCENE_IN_DIRECTION -2603.1135 51.3338 10.1663 275.0
		SET_FIXED_CAMERA_POSITION -2603.9802 54.4522 7.5275 0.0 0.0 0.0 //cut scene of building
		POINT_CAMERA_AT_POINT -2603.0139 54.7055 7.4818 JUMP_CUT
		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		PLAY_MISSION_PASSED_TUNE 1
	   	PRINT_BIG ASS_ACQ 5000 6 //CAR ASSET ACQUIRED

		WAIT 5000
		SET_FIXED_CAMERA_POSITION -2598.9001 59.0635 4.8343 0.0 0.0 0.0 //cut scene showing pickup
		POINT_CAMERA_AT_POINT -2597.9045 59.0525 4.7405  JUMP_CUT
		PRINT_WITH_NUMBER_NOW ASS_LUV courierSF_revenue 6000 1//This will now generate revenue up to a maximum of $~1~. Make sure you collect it regulary.

		WAIT 6000
		DO_FADE 500 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

	ENDIF
ENDIF


//PRINT_WITH_NUMBER_BIG ( bcou_47 ) 100 5000 1 //"Mission Passed!"
//ADD_SCORE player1 300
CLEAR_WANTED_LEVEL player1

//SWITCH_WIDESCREEN OFF
//DO_FADE 500 FADE_IN
//WHILE GET_FADING_STATUS
//		WAIT 0
//ENDWHILE

SET_CAMERA_BEHIND_PLAYER 
RESTORE_CAMERA_JUMPCUT




RETURN
		


// ********************************** mission cleanup ***********************************

mission_cleanup_bcou:


CLEAR_HELP

CLEAR_MISSION_AUDIO 3

IF IS_PLAYER_PLAYING player1
	SET_PLAYER_CAN_DO_DRIVE_BY player1 TRUE

	SET_PLAYER_CONTROL player1 ON

ENDIF

CLEAR_ONSCREEN_TIMER bcou_timelimit
CLEAR_ONSCREEN_COUNTER bags_on_bike
DELETE_MENU bcou_menu
DELETE_OBJECT bcou_package
KILL_FX_SYSTEM drugs_fx[old_bag]
DELETE_OBJECT  drugs_thrown[old_bag]


MARK_CHAR_AS_NO_LONGER_NEEDED bcou_heavy
MARK_MODEL_AS_NO_LONGER_NEEDED WAYFARER
MARK_MODEL_AS_NO_LONGER_NEEDED WMYdrug
//MARK_MODEL_AS_NO_LONGER_NEEDED satchel

REMOVE_ANIMATION SMOKING

REMOVE_BLIP bcou_bikeB
//REMOVE_BLIP dropoffB[dropoffIndex]
REMOVE_BLIP pickupB

dropoffIndex = 0
WHILE dropoffIndex < 30
	REMOVE_BLIP dropoffB[dropoffIndex]
	++ dropOffIndex
ENDWHILE

SWITCH_CAR_GENERATOR gen_car21 101
GET_GAME_TIMER timer_mobile_start //Used to reset the mobile phone timer so it doesn't ring immediately after the mission
flag_player_on_mission = 0

MISSION_HAS_FINISHED


//UNLOAD_SPECIAL_CHARACTER 1
RETURN
 
}






/*

[BCOU_1:BCOU]
~w~Don't hang about at the drop off! Throw a package in the right area for my customer then get back to me!

[BCOU_2:BCOU]
~w~Hey, you look like you're accustomed to two wheels! You after some easy money?

[BCOU_3:BCOU]
~s~Get those items thrown through the ~y~dropoff markers~s~ quickly!

[BCOU_4:BCOU]
~s~Drive the bike around town and launch items through the ~y~dropoff markers~s~ before time runs out.

[BCOU_5:BCOU]
~s~Item Delivered! Get to another ~y~dropoff marker~s~ and deliver another item.

[BCOU_6:BCOU]
~s~All items delivered! Head back to your ~y~pick up point~s~ to collect your payment.

[BCOU_7:BCOU]
~s~Payment $~1~ 

{should read items}
[BCOU_8:BCOU]
items onboard

[BCOU_9:BCOU]
Hold the L2 or R2 button and press ~o~ to launch an item to the left or right. Drive over the last unsuccessfully thrown
item to reclaim it.

[BCOU_10:BCOU]
~s~Launch a package and get it through the ~y~dropoff marker~s~ to register a successful delivery.

[BCOU_11:BCOU]
~r~You don't have enough items available to fulfill the delivery quota of this run! 

[BCOU_12:BCOU]
Good job! Let's work out your cut of the profits!

[BCOU_13:BCOU]
Okay, a couple of dropoffs this time. Don't run out of items so aim carefully!

[BCOU_14:BCOU]
Even more dropoffs now. No sightseeing! Get 'em delivered!

[BCOU_18:BCOU]
LA Biker

[BCOU_19:BCOU]
LV Biker

[BCOU_20:BCOU]
SF Biker

[BCOU_21:BCOU]
Payment :-

[BCOU_22:BCOU]
Time Left

[BCOU_23:BCOU]
items Spare

[BCOU_24:BCOU]
Total Cash

[BCOU_31:BCOU]
Get on bike: 

[BCOU_32:BCOU]
Press the R3 button to end delivery run

[BCOU_33:BCOU]
~r~Out of time! 

[BCOU_34:BCOU]
Time left:

[BCOU_35:BCOU]
~r~Delivery run over.

[BCOU_37:BCOU]
~s~You've got ~1~ seconds to get back on the ~b~bike~s~ or this run's over!

[BCOU_40:BCOU]
0 out of 7 runs completed

[BCOU_41:BCOU]
1 out of 7 runs completed

[BCOU_42:BCOU]
2 out of 7 runs completed

[BCOU_43:BCOU]
3 out of 7 runs completed

[BCOU_44:BCOU]
4 out of 7 runs completed

[BCOU_45:BCOU]
5 out of 7 runs completed

[BCOU_46:BCOU]
6 out of 7 runs completed

[BCOU_47:BCOU]
7 out of 7 runs completed

[BCOU_48:BCOU]
~y~San Fierro Courier complete!

[BCOU_49:BCOU]
~y~Los Santos Courier complete!

[BCOU_50:BCOU]
~y~Las Venturas Courier complete!

*/