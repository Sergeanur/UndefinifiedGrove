MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* Pimp.sc *******************************************
// ****************************** Earning an honest living! ********************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME PIMP

// Mission start stuff

GOSUB mission_start_pimp

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_pimp_failed
ENDIF

GOSUB mission_cleanup_pimp

MISSION_END

{ 

// Variables for mission

// Peds	and sequences

LVAR_INT pimp_window_showing

LVAR_INT pimp_whore[2]
LVAR_INT wIndex
LVAR_INT whore_acknowledgeseq whore_punterseq


// Vehicles

LVAR_INT pimp_car


// Conditions and flags

LVAR_INT pimp_patch_decided
LVAR_INT whore_spawned
LVAR_INT whore_created

LVAR_INT whore_entervehicle	whore_lookat  whore_leavevehicle
LVAR_INT whore_trickfind
LVAR_INT whore_stopprompt

LVAR_INT whore_tricknumber // incremented every time the whore services a punter successfully
LVAR_INT pimp_trick	// an index which decides in which zone the punter will be created. 27 zones in LA.
LVAR_INT pimp_removalindex // used to keep a copy of above for removal purposes.


LVAR_INT both_whores_spawned whore_copy	punter_copy
LVAR_INT whorepunterchat

LVAR_INT increments_this_run


LVAR_INT backintimer



//Pimping areas
//1 South Los Santos
//2 North Los Santos
//3 West Los Santos
LVAR_INT pimp_patch 

LVAR_INT whore_zone

LVAR_INT pimp_donealready[28] // signifies if a trick has been done in an area already.



// blips

LVAR_INT pimp_whoreB
LVAR_INT punterB
LVAR_INT punkB

// arrays
LVAR_INT punter[28]
LVAR_INT punterhurt[28]
LVAR_INT punter_wander[28]

LVAR_FLOAT LApunterX[28] LApunterY[28] LApunterZ[28] LApunterH[28]
LVAR_FLOAT SFpunterX[28] SFpunterY[28] SFpunterZ[28] SFpunterH[28]
LVAR_FLOAT LVpunterX[28] LVpunterY[28] LVpunterZ[28] LVpunterH[28]


// Various ints

LVAR_INT punter_holder0
LVAR_INT punter_holder1
LVAR_INT punter_scenario


VAR_INT pimp_bikeostimer // off_biketimer

VAR_INT pimp_runtimer

LVAR_INT pimp_timeradjustment
LVAR_INT pimp_lowtimeradjustment
//VAR_INT bcou_timelimit   // limit for particular run

LVAR_INT pimp_backonbikecheck
LVAR_INT pimp_biketimer

LVAR_INT pimp_emptydm


LVAR_INT pimp_isawhore

LVAR_INT pimp_chat
							 
LVAR_INT pimp_blipswap
LVAR_INT pimp_carB
LVAR_INT call_fix beat_fix cheap_fix


LVAR_INT total_pimpbonus
LVAR_FLOAT ftotal_pimpbonus

LVAR_INT pimp_cut
LVAR_INT pimp_window
LVAR_INT tempcount

// New city detection shit

LVAR_INT pimp_city

LVAR_FLOAT current_playerX current_playerY current_playerZ

LVAR_FLOAT pimp_nearestcity pimp_tempnearest 

LVAR_INT pimp_timerpresent

LVAR_INT pimp_countbegun

LVAR_INT pimptask_status

LVAR_INT old_pimplevel		 

LVAR_INT pimp_sfx 												  
// **************************************** Mission Start **********************************

mission_start_pimp:

IF pimp_passed_once = 0
	REGISTER_MISSION_GIVEN
ENDIF

flag_player_on_pimp_mission = 1
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
	
LOAD_MISSION_TEXT PIMP
	
//LOAD_MISSION_TEXT DOC1

SET_FADING_COLOUR 0 0 0


DO_FADE 500 FADE_IN

// request models


IF NOT IS_CHAR_DEAD scplayer
	SET_PLAYER_CONTROL player1 ON
ENDIF

// ************************************ Declare Variables *********************************

//flags

pimp_sfx = 0
pimp_window_showing = 0

pimp_timerpresent = 0 // timer not present
pimp_countbegun = 0

pimp_bikeostimer = 0// off_biketimer
pimp_timeradjustment = 0

pimp_backonbikecheck = 0
pimp_biketimer = 0
pimp_chat = 0

pimp_city = 0
																										 
pimp_patch_decided = 0
whore_spawned = 0 
whore_trickfind = 0
whore_entervehicle = 0
whore_leavevehicle = 0
whore_lookat = 0
whore_created = 0	
whore_stopprompt = 0 
 
whore_tricknumber = 0
punter_wander[0] = 0

both_whores_spawned = 0	 
increments_this_run = 0

whorepunterchat = 0

punter_holder0 = 0
punter_holder1 = 0
wIndex = 0

pimp_blipswap = 0
cheap_fix = 0
beat_fix = 0
call_fix = 0



backintimer = 1


LApunterX[0] = 2286.7896 	   //Docks
LApunterY[0] = -2312.8364
LApunterZ[0] = 12.5570
LApunterH[0] = 301.0


LApunterX[1] = 1537.2684 
LApunterY[1] = -2314.2698
LApunterZ[1] =  12.5469 
LApunterH[1] = 280.2771 // Los Santos International Airport


LApunterX[2] = 2307.9026 
LApunterY[2] = -1998.6470 
LApunterZ[2] = 12.5529 
LApunterH[2] = 321.3693 // Willowfield Industrial


LApunterX[3] = 1239.2817
LApunterY[3] = -1918.8195 
LApunterZ[3] = 30.3478 
LApunterH[3] = 284.1407 // Verdant Bluffs
// pimp_patch 1 end










LApunterX[4] = 1685.3196
LApunterY[4] = -1585.2435 
LApunterZ[4] = 12.5469 	   // Commerce gangsta shop
LApunterH[4] = 233.3592 

LApunterX[5] = 1749.7454		//Downtown
LApunterY[5] =  -1455.7781 
LApunterZ[5] = 12.5547 
LApunterH[5] = 8.5912

LApunterX[6] = 1959.5386 	   //Glen Park
LApunterY[6] = -1452.5599 
LApunterZ[6] = 12.5495 
LApunterH[6] = 186.4147 

LApunterX[7] = 1793.7080 
LApunterY[7] = -1600.1200 
LApunterZ[7] = 12.5387 
LApunterH[7] = 74.0619 	  // Little Mexico

// pimp_patch 2 ended



LApunterX[8] = 2259.7034 
LApunterY[8] = -1339.9070 
LApunterZ[8] = 22.9832 
LApunterH[8] = 267.1747 	 // Jefferson church


LApunterX[9] = 2350.0093 
LApunterY[9] = -1504.5988 
LApunterZ[9] = 23.0000 
LApunterH[9] = 113.9031  // Mexican place in East Los Santos

LApunterX[10] = 2745.3743 
LApunterY[10] = -1188.6801
LApunterZ[10] = 68.3986
LApunterH[10] = 109.0182 	// Los Flores ( near Saints Boulevard sign )



LApunterX[11] = 2833.8865 // East Beach ( by walkway )
LApunterY[11] = -1585.9792 
LApunterZ[11] = 10.0860
LApunterH[11] = 223.6582 

//pimp_patch 3 ended



LApunterX[12] = 1282.4292 
LApunterY[12] = -1666.1295 
LApunterZ[12] = 12.5547 
LApunterH[12] = 202.3730 // Conference Centre 

LApunterX[13] = 899.8489 
LApunterY[13] = -1823.8766 
LApunterZ[13] = 11.2302 
LApunterH[13] = 215.4203 	 // Verona Beach

LApunterX[14] = 447.2084
LApunterY[14] = -1761.3137 
LApunterZ[14] = 4.7837
LApunterH[14] = 230.4552  // Santa maria Beach

LApunterX[15] = 765.7432 
LApunterY[15] = -1541.3196 
LApunterZ[15] = 12.5536 
LApunterH[15] = 264.2591 // Marina

//pimp_patch 4 ended 



LApunterX[16] = 1269.5922 
LApunterY[16] = -1560.4619 
LApunterZ[16] = 12.5643 
LApunterH[16] = 186.3764 // Market


LApunterX[17] = 557.9569 
LApunterY[17] = -1509.4019 
LApunterZ[17] = 13.5557 
LApunterH[17] = 94.5521 // Central Rodeo

LApunterX[18] = 434.0113 			// Dodgy height bug
LApunterY[18] = -1555.6675 
LApunterZ[18] = 26.5851 
LApunterH[18] = 273.0415 // Rodeo Steps


LApunterX[19] = 403.0965 //Richman	// Dodgy height bug.
LApunterY[19] = -1256.6650
LApunterZ[19] = 51.2
LApunterH[19] = 29.0138  

//pimp_patch 5 ended


LApunterX[20] = 1147.1298 	//Mulholland golfer
LApunterY[20] = -898.4011
LApunterZ[20] = 41.7814
LApunterH[20] = 234.2193	



											  

LApunterX[21] = 1020.8024 
LApunterY[21] = -911.8980
LApunterZ[21] = 41.2395
LApunterH[21] = 222.6945 // Mulholland grease monkey at car wash

LApunterX[22] = 828.6284 
LApunterY[22] = -1056.4452 
LApunterZ[22] = 24.2484 
LApunterH[22] = 22.8380 


LApunterX[23] =  971.1776 
LApunterY[23] = -1107.7435 
LApunterZ[23] = 22.8672 
LApunterH[23] = 83.3249  // Temple cemetery

//pimp_patch 6 ended
 

LApunterX[24] = 1893.3621 
LApunterY[24] = -1741.2435
LApunterZ[24] = 12.5468 
LApunterH[24] = 177.2180  	//Idlewood by big building

LApunterX[25] = 2333.6255 
LApunterY[25] = -1764.6594 
LApunterZ[25] = 12.5534 
LApunterH[25] = 333.4691 	// Ganton blue apartments


LApunterX[26] = 1775.9417 
LApunterY[26] = -1895.6230 
LApunterZ[26] = 12.3865 
LApunterH[26] = 248.6633	// El corona


LApunterX[27] = 1707.1208 
LApunterY[27] = -1804.7551 
LApunterZ[27] = 12.5391
LApunterH[27] = 172.0678	// Back alley in little mexico

//pimp_patch 7 ended




SFpunterX[0] = -2020.2729 
SFpunterY[0] = 932.0927 
SFpunterZ[0] = 44.5639 
SFpunterH[0] = 221.8539 // By the bottom of brick path in Calton Heights 

SFpunterX[1] = -2399.2983 
SFpunterY[1] = 1071.8297 
SFpunterZ[1] = 54.7266 
SFpunterH[1] = 327.6848 // Juniper Hollow, top of park

SFpunterX[2] = -2400.6516
SFpunterY[2] = 922.1189 
SFpunterZ[2] = 44.4453
SFpunterH[2] = 283.5361  // By house in Juniper Hill near Burger joint

SFpunterX[3] = -2301.2925 
SFpunterY[3] = 1373.1053
SFpunterZ[3] = 6.1961 
SFpunterH[3] = 124.8329  //Esplanade North on towpath running perpendicular to ship

// SF pimp patch 1 ended


/*
SFpunterX[4] = -2248.7585 
SFpunterY[4] = 2285.5298 
SFpunterZ[4] = 3.8125 
SFpunterH[4] = 162.2104	 // Bayside Marina by Boat School

SFpunterX[5] = -2436.1055 
SFpunterY[5] = 2228.9583 
SFpunterZ[5] = 3.9921
SFpunterH[5] = 75.3359	// Bayside Housing
*/





SFpunterX[6] = -2576.9475 
SFpunterY[6] = 1144.2339 
SFpunterZ[6] = 54.7266 
SFpunterH[6] = 124.6449 // Near Battery Point house	  ( Meant to be Battery point zone )

SFpunterX[7] = -2694.1509
SFpunterY[7] = 1204.6277 
SFpunterZ[7] = 54.1953 
SFpunterH[7] = 357.2611 // Corner of road in Pallisades ( Meant to be Gant Bridge zone )

// SF pimp patch 2 ended

SFpunterX[8] = -2244.2346 
SFpunterY[8] = 643.8406 
SFpunterZ[8] = 48.4375 
SFpunterH[8] = 99.8674 // Chinatown

SFpunterX[9] = -1923.3507 
SFpunterY[9] = 563.4099 
SFpunterZ[9] = 34.1972 
SFpunterH[9] = 44.6685 // Downtown, near plaza section

SFpunterX[10] = -1736.4307 
SFpunterY[10] = 809.5150 
SFpunterZ[10] = 23.8904 
SFpunterH[10] = 333.3014 // Financial district by big blue glass fronted building

SFpunterX[11] = -1504.5710 
SFpunterY[11] = 1082.9998 
SFpunterZ[11] = 6.1846 
SFpunterH[11] = 156.5032  // Esplanade East on promenade area

// SF pimp patch 3 ended



SFpunterX[12] = -2400.0508
SFpunterY[12] = 556.1022
SFpunterZ[12] = 23.8906
SFpunterH[12] = 18.5350   // West Park	 now known as Queens.

SFpunterX[13] = -2490.2551
SFpunterY[13] = 6.0292 
SFpunterZ[13] = 24.6238 
SFpunterH[13] = 56.3408  // Hashbury

SFpunterX[14] = -2757.4685 
SFpunterY[14] = 168.1762 
SFpunterZ[14] = 6.0408 
SFpunterH[14] =196.6891 // Ocean Flats ( 1or 2 - double zone )

SFpunterX[15] = -2779.7908
SFpunterY[15] = -319.9682 
SFpunterZ[15] = 6.1875 
SFpunterH[15] = 123.0427 // Avispa Country Club entrance ( 1 or 2 - double zone )
// SF pimp_patch 4 ended



SFpunterX[16] = -2192.0227 
SFpunterY[16] = 309.7259 
SFpunterZ[16] = 34.1172 
SFpunterH[16] = 7.9069 	 // King's car park

SFpunterX[17] = -2195.7893
SFpunterY[17] = 197.3115
SFpunterZ[17] = 34.3203 
SFpunterH[17] = 9.4354 	// side alley in garcia


SFpunterX[18] = -2108.6475 
SFpunterY[18] = 217.1542 
SFpunterZ[18] = 34.3045 
SFpunterH[18] = 133.6119 // Doherty building site


SFpunterX[19] = -1545.4487
SFpunterY[19] = 560.5441
SFpunterZ[19] = 6.1797 
SFpunterH[19] = 165.7533 // Easter Basin bridge pillar 
//SF pimp_patch 5 ended




SFpunterX[20] = -2779.7996 
SFpunterY[20] = -500.7523 
SFpunterZ[20] = 6.2925 
SFpunterH[20] = 37.7744 // Foot of Missionary Hill


SFpunterX[21] = -1974.4753
SFpunterY[21] = -975.0550 
SFpunterZ[21] = 31.2266 
SFpunterH[21] = 138.4206  //By rock display in Silicon Valley

SFpunterX[22] = -1587.1193 
SFpunterY[22] = -482.9532 
SFpunterZ[22] = 5.1484 
SFpunterH[22] = 117.5313 // near guard hut at Easter Bay Airport 

//pimp_patch 6 ended
 


SFpunterX[23] = -2838.1116 
SFpunterY[23] = 846.8309 
SFpunterZ[23] = 42.5804 
SFpunterH[23] = 288.0701 // Pallisades terrace

SFpunterX[24] = -2566.2415 
SFpunterY[24] = 918.7418 
SFpunterZ[24] = 63.9765 
SFpunterH[24] = 178.9968 // Back alley in paradiso

SFpunterX[25] = -2601.5911
SFpunterY[25] = 688.1887 
SFpunterZ[25] = 26.8125
SFpunterH[25] = 34.6082 // car park bit in Santa Flora 


SFpunterX[26] = -2758.5510 
SFpunterY[26] = 394.1800 
SFpunterZ[26] = 3.3359
SFpunterH[26] = 315.4430 // City Hall Steps



// Vegas coords.

LVpunterX[0] = 2009.3130 // The Pink Swan
LVpunterY[0] = 1167.7930 
LVpunterZ[0] = 9.8130
LVpunterH[0] = 266.7310 

LVpunterX[1] = 1959.9984 // Side entrance of the Four Dragons Casino
LVpunterY[1] = 945.6432
LVpunterZ[1] = 9.8203 
LVpunterH[1] = 225.7712

LVpunterX[2] = 2202.7373 // The Camel's Toe
LVpunterY[2] = 1254.4501
LVpunterZ[2] = 9.8203 
LVpunterH[2] = 110.1904 

LVpunterX[3] = 2186.8398 // Come-A-Lot 
LVpunterY[3] = 998.9764 
LVpunterZ[3] = 9.8203 
LVpunterH[3] = 202.3453 

//pp1 ended

LVpunterX[4] = 2031.9764  // PIRA The Pirates in Mens Pants
LVpunterY[4] = 1539.8997 
LVpunterZ[4] = 9.8203
LVpunterH[4] = 224.9353 


LVpunterX[5] = 2139.4617  // ROY Royal Casino
LVpunterY[5] = 1516.0449 
LVpunterZ[5] = 9.8203 
LVpunterH[5] = 351.7

LVpunterX[6] = 2150.3640 // CALI Caligula's Palace
LVpunterY[6] = 1675.9688
LVpunterZ[6] = 9.8203
LVpunterH[6] = 217.8727 

LVpunterX[7] = 1939.5504 // HIGH The High Roller
LVpunterY[7] = 1389.7562 
LVpunterZ[7] = 8.2578 
LVpunterH[7] = 271.8136 

// pp2


LVpunterX[8] = 2595.7898 // PILG Pilgrim  ( seems quite odd though, it's in the Starfish casino car park )
LVpunterY[8] = 1793.6372 
LVpunterZ[8] = 9.9692 
LVpunterH[8] = 105.7608 

LVpunterX[9] = 2185.6855 // STAR Starfish Casino 
LVpunterY[9] = 1885.9117 
LVpunterZ[9] = 9.8203
LVpunterH[9] = 4.9185 

LVpunterX[10] = 2159.8909 // The Strip
LVpunterY[10] = 1931.0157
LVpunterZ[10] = 9.8203 
LVpunterH[10] = 94.0854 

 
LVpunterX[11] = 2057.3735 // the Visage
LVpunterY[11] = 1957.5223 
LVpunterZ[11] = 10.7324
LVpunterH[11] = 218.8441

// end of pp3


LVpunterX[12] = 2181.5325  // ISLE The Emerald Isle
LVpunterY[12] = 2353.4844
LVpunterZ[12] = 9.8203 
LVpunterH[12] = 186.4606 

LVpunterX[13] = 2437.6074 // ROCE Roca Escalante
LVpunterY[13] = 2376.5813
LVpunterZ[13] = 9.8203 
LVpunterH[13] = 87.5329 

LVpunterX[14] = 2335.3909 // OVS Old Venturas Strip 
LVpunterY[14] = 2076.1465
LVpunterZ[14] = 9.8125
LVpunterH[14] = 261.0065

LVpunterX[15] = 2217.5637 // RING The Ring Master, the casino here is known as the Clown's Pocket though
LVpunterY[15] = 1787.8440
LVpunterZ[15] = 9.8203
LVpunterH[15] = 36.1247 
// end of pp4

LVpunterX[16] = 1540.7322 // BFC Blackfield Chapel 
LVpunterY[16] = 748.3782 
LVpunterZ[16] = 9.8203 
LVpunterH[16] = 259.5982 

LVpunterX[17] = 1011.9598 // GGC Greenglass College
LVpunterY[17] = 1104.6254 
LVpunterZ[17] = 9.8203 
LVpunterH[17] = 4.4360 

LVpunterX[18] = 1723.9036 // VAIR Las Venturas Airport 	// Needs moved in a little, she starts on the road!
LVpunterY[18] = 1541.1241 
LVpunterZ[18] = 9.8203 
LVpunterH[18] = 317.5323 

LVpunterX[19] = 1958.8224 // LDM Last Dime Motel
LVpunterY[19] = 706.9632
LVpunterZ[19] = 9.8199
LVpunterH[19] = 216.0597 
 









 pimp_runtimer = 180000 // needs to be big to start with












// ****************************************************************************************


																	 

			 
//WRITE_DEBUG script_running

//CREATE_CAR OCEANIC 1918.9883 -1789.3239 12.3828 pimp_car  //garage coords
//SET_CAR_HEADING pimp_car 267.0957
//CHANGE_CAR_COLOUR pimp_car 1 5


//WARP_CHAR_INTO_CAR scplayer pimp_car
			 
CLEAR_AREA 1918.9883 -1791.8542 12.3828 30.0 FALSE
SET_CHAR_CANT_BE_DRAGGED_OUT scplayer TRUE




														
IF NOT IS_PLAYER_PLAYING player1
	GOTO mission_pimp_failed
ENDIF


IF IS_CHAR_IN_ANY_CAR scplayer
   STORE_CAR_CHAR_IS_IN scplayer pimp_car
ELSE
	GOTO mission_pimp_failed
ENDIF

IF NOT IS_CAR_DEAD pimp_car
//SET_UPSIDEDOWN_CAR_NOT_DAMAGED pimp_car TRUE
SET_CAR_HEALTH pimp_car 2000

ENDIF



LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY pimp_emptydm
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_GANG_FLAT PEDTYPE_PLAYER1


GET_RANDOM_CHAR_IN_ZONE BLUFF TRUE FALSE TRUE pimp_whore[wIndex]
DELETE_CHAR pimp_whore[wIndex]

GET_RANDOM_CHAR_IN_ZONE BLUFF TRUE FALSE TRUE punter[pimp_removalindex]
DELETE_CHAR punter[pimp_removalindex]

IF whore_trickfind = -13
	DISPLAY_ONSCREEN_TIMER_WITH_STRING pimp_runtimer TIMER_DOWN PIMP_90
	CLEAR_ONSCREEN_TIMER pimp_runtimer
ENDIF

//New decision area to decide which city pimping will take place in

IF NOT IS_CHAR_DEAD scplayer
	GET_CITY_PLAYER_IS_IN player1 pimp_city
ENDIF

IF NOT pimp_city = LEVEL_LOSANGELES
	IF NOT pimp_city = LEVEL_SANFRANCISCO
		IF NOT pimp_city = LEVEL_LASVEGAS
		  //	write_debug country
	
		  // Put closest city shit in here
		  //pimp_city = LEVEL_LOSANGELES

		  GET_CHAR_COORDINATES scplayer current_playerX current_playerY current_playerZ

		  // try LA first. Getting distance between player's position and Ganton, centralish LA ( vec 25 ) 
		  GET_DISTANCE_BETWEEN_COORDS_3D current_playerX current_playerY current_playerZ LApunterX[25] LApunterY[25] LApunterZ[25] pimp_tempnearest
		  pimp_nearestcity = pimp_tempnearest
		  pimp_city = LEVEL_LOSANGELES

		  // now try SF	
		  GET_DISTANCE_BETWEEN_COORDS_3D current_playerX current_playerY current_playerZ SFpunterX[0] SFpunterY[0] SFpunterZ[0] pimp_tempnearest
		 
		  IF pimp_tempnearest < pimp_nearestcity
		  pimp_nearestcity = pimp_tempnearest
		  pimp_city = LEVEL_SANFRANCISCO
		  ENDIF


		    // now try Vegas
		  GET_DISTANCE_BETWEEN_COORDS_3D current_playerX current_playerY current_playerZ LVpunterX[0] LVpunterY[0] LVpunterZ[0] pimp_tempnearest
		 
		  IF pimp_tempnearest < pimp_nearestcity
		  pimp_nearestcity = pimp_tempnearest
		  pimp_city = LEVEL_LASVEGAS
		  ENDIF

	
		ENDIF
	ENDIF
ENDIF






	 
pimp_main_mission_loop:
WAIT 0


IF pimp_sfx = 1
	CLEAR_MISSION_AUDIO 4
	LOAD_MISSION_AUDIO 4 SOUND_PIMP_CUSTOMER_SEX
	pimp_sfx = 2
ENDIF

IF pimp_sfx = 2

	IF HAS_MISSION_AUDIO_LOADED 4
		//WRITE_DEBUG fire2
	   //	PLAY_MISSION_AUDIO 4
	  	pimp_sfx = 3
	ENDIF

ENDIF


IF pimp_timerpresent = 1  // get girl to customer

	IF pimp_runtimer < 1
		CLEAR_PRINTS
		PRINT_NOW  PIMP_92 6000 1
		GOTO mission_pimp_failed
	ENDIF

ENDIF

IF pimp_timerpresent = 2  // get to girl in danger

	IF pimp_runtimer < 1
		CLEAR_PRINTS
		PRINT_NOW  PIMP_91 6000 1
		GOTO mission_pimp_failed
	ENDIF

ENDIF


IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_w
	   pimp_runtimer = pimp_runtimer + 60000
ENDIF


IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_q
	   pimp_runtimer = pimp_runtimer - 30000
ENDIF


//SWITCH_WIDESCREEN ON

IF NOT IS_PLAYER_PLAYING player1
	GOTO mission_pimp_failed
ENDIF

IF IS_CAR_DEAD pimp_car
	GOTO mission_pimp_failed
ENDIF


IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_p
	   ALTER_WANTED_LEVEL Player1 2
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_o
	    ALTER_WANTED_LEVEL Player1 0
ENDIF

GET_CONTROLLER_MODE controlmode

IF NOT controlmode = 3
	IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
	AND flag_player_on_pimp_mission = 1
		CLEAR_SMALL_PRINTS
		PRINT_NOW pimp_59 6000 1
		
		IF NOT IS_CHAR_DEAD pimp_whore[0]
			IF NOT IS_CAR_DEAD pimp_car
				IF IS_CHAR_SITTING_IN_CAR pimp_whore[0] pimp_car
					CLEAR_CHAR_TASKS pimp_whore[0]
					TASK_LEAVE_ANY_CAR pimp_whore[0]
				ENDIF
			ENDIF
			MARK_CHAR_AS_NO_LONGER_NEEDED pimp_whore[0]
		ENDIF
			
		IF NOT IS_CHAR_DEAD pimp_whore[1]
			IF NOT IS_CAR_DEAD pimp_car
				IF IS_CHAR_SITTING_IN_CAR pimp_whore[1] pimp_car
					CLEAR_CHAR_TASKS pimp_whore[1]
					TASK_LEAVE_ANY_CAR pimp_whore[1]
				ENDIF
			ENDIF
			MARK_CHAR_AS_NO_LONGER_NEEDED pimp_whore[1]
		ENDIF

		GOTO pimp_fail_button_pressed
	ENDIF
ELSE
	IF IS_BUTTON_PRESSED PAD1 SQUARE
	AND flag_player_on_pimp_mission = 1
		CLEAR_PRINTS
		PRINT_NOW pimp_59 6000 1
		
		IF NOT IS_CHAR_DEAD pimp_whore[0]
			IF NOT IS_CAR_DEAD pimp_car
				IF IS_CHAR_SITTING_IN_CAR pimp_whore[0] pimp_car
					CLEAR_CHAR_TASKS pimp_whore[0]
					TASK_LEAVE_ANY_CAR pimp_whore[0]
				ENDIF
			ENDIF
			MARK_CHAR_AS_NO_LONGER_NEEDED pimp_whore[0]
		ENDIF
			
		IF NOT IS_CHAR_DEAD pimp_whore[1]
			IF NOT IS_CAR_DEAD pimp_car
				IF IS_CHAR_SITTING_IN_CAR pimp_whore[1] pimp_car
					CLEAR_CHAR_TASKS pimp_whore[1]
					TASK_LEAVE_ANY_CAR pimp_whore[1]
				ENDIF
			ENDIF
			MARK_CHAR_AS_NO_LONGER_NEEDED pimp_whore[1]
		ENDIF

		GOTO pimp_fail_button_pressed
	ENDIF
ENDIF


// Need to check if player has hit a current punter. Flagged by corresponding array so that non-paying / rough customers are exempt
tempcount = 0
WHILE tempcount < 28
	
	IF punterhurt[tempcount] = 1
	IF DOES_CHAR_EXIST punter[tempcount]
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR punter[tempcount] scplayer
			PRINT_NOW PIMP_52 8000 1
			GOTO mission_pimp_failed
		ENDIF
		IF IS_CHAR_DEAD punter[tempcount]
			PRINT_NOW PIMP_52 8000 1
			GOTO mission_pimp_failed
		ENDIF

	ENDIF
	ENDIF

	tempcount ++
ENDWHILE








IF pimp_backonbikecheck = 0
	
	IF NOT IS_CAR_DEAD pimp_car

		IF NOT IS_CHAR_IN_CAR scplayer pimp_car
			TIMERB = 0 
			pimp_biketimer = 1 // begin timer
			pimp_lowtimeradjustment = pimp_runtimer / 1000	 // get time for under twenty seconds check

			pimp_backonbikecheck = 1
			CLEAR_PRINTS
			CLEAR_HELP
		ENDIF

	ENDIF
ENDIF


IF pimp_backonbikecheck = 1

	IF NOT IS_CAR_DEAD pimp_car

		IF IS_CHAR_IN_CAR scplayer pimp_car
			TIMERB = 0
			pimp_backonbikecheck = 0
			pimp_biketimer = 0
			pimp_countbegun = 0
		
			CLEAR_PRINTS

		ENDIF 

	ENDIF

ENDIF



IF pimp_biketimer = 1
	pimp_timeradjustment = TIMERB / 1000


//	IF pimp_runtimer < 21000
//	AND pimp_runtimer > 1
//	//lowtimeradjustment = bcou_timelimit / 1000
//	pimp_bikeostimer = pimp_lowtimeradjustment - pimp_timeradjustment
//
//	ELSE
//	pimp_bikeostimer = 20 - pimp_timeradjustment
//	ENDIF
//

	IF pimp_countbegun = 0
		IF pimp_runtimer < 21000
		AND pimp_runtimer > 1
			pimp_countbegun = 1
		ENDIF
	ENDIF


	IF pimp_countbegun = 0
		IF pimp_runtimer > 20099
		   pimp_countbegun = 2
		ENDIF
	ENDIF


	IF pimp_countbegun = 1
		pimp_bikeostimer = pimp_runtimer / 1000
	ENDIF

	
	IF pimp_countbegun = 2
		pimp_bikeostimer = 20 - pimp_timeradjustment
	ENDIF


	CLEAR_PRINTS

	IF pimp_bikeostimer > 0
	ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS FALSE
	PRINT_WITH_NUMBER_NOW pimp_1 pimp_bikeostimer 4000 1
	ENDIF
	IF TIMERB > 21000
		CLEAR_PRINTS
		PRINT_NOW pimp_59 6000 1
		GOTO mission_pimp_failed
	ENDIF
ENDIF

IF pimp_patch_decided = -13
	CREATE_CHAR PEDTYPE_PROSTITUTE WFYPRO 1954.3475 -1867.1636 12.5391 pimp_whore[wIndex]	
	ADD_BLIP_FOR_CHAR pimp_whore[wIndex] pimp_whoreB
	ADD_BLIP_FOR_CHAR scplayer punterB
	ADD_BLIP_FOR_CHAR scplayer punkB
ENDIF

IF pimp_patch_decided = 0
	GOSUB pimp_patch_decider
	pimp_patch_decided = 1
ENDIF

IF pimp_patch_decided = 1

	
	//FORCE_RANDOM_PED_TYPE PEDTYPE_PROSTITUTE

	IF pimp_city = LEVEL_LOSANGELES

			REQUEST_MODEL BFYPRO
			REQUEST_MODEL HFYPRO

			WHILE NOT HAS_MODEL_LOADED BFYPRO
				OR NOT HAS_MODEL_LOADED HFYPRO
				WAIT 0
			ENDWHILE

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer IWD 
				// in Idlewood, spawn Hispanic whore in LMEX, Little Mexico,  using punter coords.
			   	CREATE_CHAR PEDTYPE_PROSTITUTE HFYPRO LApunterX[27] LApunterY[27] LApunterZ[27]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[27]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer GAN	 
				// in Ganton, spawn Hispanic whore in ELCO, El Corona
			 	CREATE_CHAR PEDTYPE_PROSTITUTE HFYPRO LApunterX[26] LApunterY[26] LApunterZ[26]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[26]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer ELCO
				// in El Corona, spawn black whore in GAN, ganton
			  	CREATE_CHAR PEDTYPE_PROSTITUTE BFYPRO LApunterX[25] LApunterY[25] LApunterZ[25]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[25]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer LMEX
				// in Little Mexico, spawn black whore in IWD, Idlewood
			  	CREATE_CHAR PEDTYPE_PROSTITUTE BFYPRO LApunterX[24] LApunterY[24] LApunterZ[24]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[24]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF //pp0?

			




			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer LDOC
			  // in LA Docks, spawn black whore at LAIR, Los Santos International Airport
			  	CREATE_CHAR PEDTYPE_PROSTITUTE BFYPRO LApunterX[1] LApunterY[1] LApunterZ[1]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[1]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer LAIR
			 // in Los Santos International Airport, spawn black whore at LIND, Willowfield Industrial
			  	CREATE_CHAR PEDTYPE_PROSTITUTE BFYPRO LApunterX[2] LApunterY[2] LApunterZ[2]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[2]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer LIND
				 // in Willowfield Industrial , spawn Hispanic whore at LDOC, Docks
			  	CREATE_CHAR PEDTYPE_PROSTITUTE HFYPRO LApunterX[0] LApunterY[0] LApunterZ[0]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[0]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer BLUF
		   		// in Verdant Bluffs , spawn Hispanic whore at LAIR
				CREATE_CHAR PEDTYPE_PROSTITUTE HFYPRO LApunterX[1] LApunterY[1] LApunterZ[1]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[1]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	 //pp1






			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer COM
			   	// in Commerce, spawn Hispanic whore at LDT, Downtown Los Santos
				CREATE_CHAR PEDTYPE_PROSTITUTE HFYPRO LApunterX[5] LApunterY[5] LApunterZ[5]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[5]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer LDT
			    // in Los Santos Downtown, spawn Hispanic whore in COM, commerce gangsta shop
				CREATE_CHAR PEDTYPE_PROSTITUTE HFYPRO LApunterX[4] LApunterY[4] LApunterZ[4]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[4]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer GLN
			 // in Glen Park, spawn Hispanic whore in LMEX, Little Mexico
				CREATE_CHAR PEDTYPE_PROSTITUTE HFYPRO LApunterX[7] LApunterY[7] LApunterZ[7]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[7]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer CHC
				// in Las Colinas, spawn black whore in GLN, Glen Park
				CREATE_CHAR PEDTYPE_PROSTITUTE BFYPRO LApunterX[6] LApunterY[6] LApunterZ[6]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[6]
			   	GOSUB whore_suitable			
			ENDIF
			ENDIF //pp2



		





			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer JEF
			   	// in Jefferson, spawn hispanic whore in ELS, Mexican place in East Los Santos
				CREATE_CHAR PEDTYPE_PROSTITUTE HFYPRO LApunterX[9] LApunterY[9] LApunterZ[9]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[9]
			   	GOSUB whore_suitable				
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer ELS
			    // in East Los Santos, spawn black whore by church in Jefferson
				CREATE_CHAR PEDTYPE_PROSTITUTE BFYPRO LApunterX[8] LApunterY[8] LApunterZ[8]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[8]
			   	GOSUB whore_suitable	
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer LFL
			   // in Los Flores, spawn black whore by EBE, East Beach
				CREATE_CHAR PEDTYPE_PROSTITUTE BFYPRO LApunterX[11] LApunterY[11] LApunterZ[11]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[11]
			   	GOSUB whore_suitable	
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer EBE
			      // in East Beach, spawn Hispanic whore in LFL, Los Flores
				CREATE_CHAR PEDTYPE_PROSTITUTE HFYPRO LApunterX[10] LApunterY[10] LApunterZ[10]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[10]
			   	GOSUB whore_suitable	
			ENDIF
			ENDIF // pp3






			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer CONF
			    // in Conference Centre, spawn black whore in VERO, Verona Beach
				CREATE_CHAR PEDTYPE_PROSTITUTE BFYPRO LApunterX[13] LApunterY[13] LApunterZ[13]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[13]
			   	GOSUB whore_suitable	
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer VERO
			  // in Verona Beach, spawn black whore in CONF, conference centre
				CREATE_CHAR PEDTYPE_PROSTITUTE BFYPRO LApunterX[12] LApunterY[12] LApunterZ[12]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[12]
			   	GOSUB whore_suitable	
			ENDIF
			ENDIF

			
			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer MKT //?????????????????????????????
			     // in Market, spawn black whore in MAR, Marina
				CREATE_CHAR PEDTYPE_PROSTITUTE BFYPRO LApunterX[15] LApunterY[15] LApunterZ[15]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[15]
			   	GOSUB whore_suitable				
			ENDIF
			ENDIF
			

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer MAR
			     // in Marina, spawn Hispanic whore in SMB, Santa Maria Beach
				CREATE_CHAR PEDTYPE_PROSTITUTE BFYPRO LApunterX[14] LApunterY[14] LApunterZ[14]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[14]
			   	GOSUB whore_suitable			
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer SMB
				  // in Marina, spawn Hispanic whore in MAR, Marina
				CREATE_CHAR PEDTYPE_PROSTITUTE BFYPRO LApunterX[15] LApunterY[15] LApunterZ[15]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[15]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF











			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer PLS
				// in Playa Del Sevilla, spawn Hispanic whore by ROD Rodeo steps
				CREATE_CHAR PEDTYPE_PROSTITUTE HFYPRO LApunterX[18] LApunterY[18] LApunterZ[18]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[18]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer ROD
			// in Rodeo, spawn black whore in RIH Richman
				CREATE_CHAR PEDTYPE_PROSTITUTE BFYPRO LApunterX[19] LApunterY[19] LApunterZ[19]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[19]
			   	GOSUB whore_suitable

			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer RIH
			   // in Richamn, spawn black whore in ROD, central Rodeo
				CREATE_CHAR PEDTYPE_PROSTITUTE BFYPRO LApunterX[17] LApunterY[17] LApunterZ[17]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[17]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF



				
			
			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer VIN
			 // in Vinewood, spawn black whore in MUL, Mulholland sex shop area
				CREATE_CHAR PEDTYPE_PROSTITUTE BFYPRO LApunterX[20] LApunterY[20] LApunterZ[20]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[20]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF
			

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer MUL 
			// in Mulhalloand, spawn black whore by Vinewood / Sunrise / Temple cemetery area
		 		 CREATE_CHAR PEDTYPE_PROSTITUTE BFYPRO LApunterX[23] LApunterY[23] LApunterZ[23]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[23]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			
			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer SUN
				// in Sunrise, spawm Hispanic whore by Mulholland garage
			   	CREATE_CHAR PEDTYPE_PROSTITUTE HFYPRO LApunterX[21] LApunterY[21] LApunterZ[21]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[21]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF
			


			//If the player started out in the sticks they automatically wouldn't
			//have a starting zone so we need to create a whore in the centre of the closest city somewhere. 
			//If whore_spawned still equals zero ( it gets incremented in whore_suitable proc, after all of the 
			//above checks, it also acts as a failsafe in case the player is in a zone that has been missed out!


			IF whore_spawned = 0 //	Failsafe! Only gets called if all above zone checks pass a negative
		  	    // Failsafe! Player started out of town, nearest city was LA, spawn Hispanic whore at LDT, Downtown Los Santos
				CREATE_CHAR PEDTYPE_PROSTITUTE HFYPRO LApunterX[5] LApunterY[5] LApunterZ[5]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LApunterH[5]
				GOSUB whore_suitable
			ENDIF
		 
			MARK_MODEL_AS_NO_LONGER_NEEDED BFYPRO
			MARK_MODEL_AS_NO_LONGER_NEEDED HFYPRO

	ENDIF // pimp_city = LEVEL_LOSANGELES





	IF pimp_city = LEVEL_SANFRANCISCO

			REQUEST_MODEL SWFOPRO
			REQUEST_MODEL WFYPRO

			WHILE NOT HAS_MODEL_LOADED SWFOPRO
			OR NOT HAS_MODEL_LOADED WFYPRO
			WAIT 0
			ENDWHILE

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer CALT
				// in Calton, spawn whore at JUNIHO, Juniper Hollow
			   	CREATE_CHAR PEDTYPE_PROSTITUTE WFYPRO SFpunterX[1] SFpunterY[1] SFpunterZ[1]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[1]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer JUNIHO
				// in Juniper Hollow, spawn whore at CALT, Calton Heights
			   	CREATE_CHAR PEDTYPE_PROSTITUTE SWFOPRO SFpunterX[0] SFpunterY[0] SFpunterZ[0]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[0]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer ESPN
				// in Esplanade North, spawn whore at JUNIHI, Juniper Hill
			   	CREATE_CHAR PEDTYPE_PROSTITUTE SWFOPRO SFpunterX[2] SFpunterY[2] SFpunterZ[2]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[2]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer JUNIHI
				// in Juniper Hill, spawn whore at ESPN, Esplanade North
			   	CREATE_CHAR PEDTYPE_PROSTITUTE WFYPRO SFpunterX[3] SFpunterY[3] SFpunterZ[3]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[3]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF // pimp_patch 1 ( green ended )



			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer SUNMA
				// in Bayside Marina, spawn whore at SUNNN, Bayside
			   	CREATE_CHAR PEDTYPE_PROSTITUTE WFYPRO SFpunterX[5] SFpunterY[5] SFpunterZ[5]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[5]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer SUNNN
				// in Bayside, spawn whore at SUNMA Bayside Marina
			   	CREATE_CHAR PEDTYPE_PROSTITUTE SWFOPRO SFpunterX[4] SFpunterY[4] SFpunterZ[4]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[4]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer BATTP
				// near Battery Point, spawn whore near Gant Bridge
			   	CREATE_CHAR PEDTYPE_PROSTITUTE SWFOPRO SFpunterX[7] SFpunterY[7] SFpunterZ[7]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[7]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer GANTB
				// in GantBridge, spawn whore near Battery Point
			   	CREATE_CHAR PEDTYPE_PROSTITUTE WFYPRO SFpunterX[6] SFpunterY[6] SFpunterZ[6]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[6]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF // pimp_patch 2 ( cyan ended )




			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer CHINA
				// in Chinatown, spawn whore in SFDWT, Downtown
			   	CREATE_CHAR PEDTYPE_PROSTITUTE WFYPRO SFpunterX[9] SFpunterY[9] SFpunterZ[9]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[9]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer SFDWT
				// in Downtown, spawn whore in Chinatown
			   	CREATE_CHAR PEDTYPE_PROSTITUTE SWFOPRO SFpunterX[8] SFpunterY[8] SFpunterZ[8]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[8]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer FINA
				// in Fina, spawn whore in Esplanade East
			   	CREATE_CHAR PEDTYPE_PROSTITUTE SWFOPRO SFpunterX[11] SFpunterY[11] SFpunterZ[11]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[11]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer ESPE
				// in Esplanade East, spawn whore in financial
			   	CREATE_CHAR PEDTYPE_PROSTITUTE WFYPRO SFpunterX[10] SFpunterY[10] SFpunterZ[10]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[10]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF // pimp_patch 3 ( magenta ended )



			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer WESTP
				// in West Park, spawn whore in HASH, Hashbury
			   	CREATE_CHAR PEDTYPE_PROSTITUTE WFYPRO SFpunterX[13] SFpunterY[13] SFpunterZ[13]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[13]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer HASH
				// in Hashbury, spawn whore in West Park
			   	CREATE_CHAR PEDTYPE_PROSTITUTE SWFOPRO SFpunterX[12] SFpunterY[12] SFpunterZ[12]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[12]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer OCEAF
	  				// in Ocean Flats, spawn whore in CUNTC1 or CUNTC2, Country club
			   	CREATE_CHAR PEDTYPE_PROSTITUTE WFYPRO SFpunterX[15] SFpunterY[15] SFpunterZ[15]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[15]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer CUNTC
				// in Country Club, spawn whore in Ocean Flats
			   	CREATE_CHAR PEDTYPE_PROSTITUTE SWFOPRO SFpunterX[14] SFpunterY[14] SFpunterZ[14]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[14]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	//pimp_patch 4 grey ended






			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer THEA
				// in King's, spawn whore in Garcia
			   	CREATE_CHAR PEDTYPE_PROSTITUTE WFYPRO SFpunterX[17] SFpunterY[17] SFpunterZ[17]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[17]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer GARC
				// in Garcia, spawn whore in KIng's
			   	CREATE_CHAR PEDTYPE_PROSTITUTE SWFOPRO SFpunterX[16] SFpunterY[16] SFpunterZ[16]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[16]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer DOH
				// in Doherty, spawn whore in Easter Basin
			   	CREATE_CHAR PEDTYPE_PROSTITUTE WFYPRO SFpunterX[19] SFpunterY[19] SFpunterZ[19]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[19]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer EASB
				// in Easter Basin, spawn whore in Doherty
			   	CREATE_CHAR PEDTYPE_PROSTITUTE SWFOPRO SFpunterX[18] SFpunterY[18] SFpunterZ[18]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[18]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	//pimp_patch 5  ended



			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer HILLP
				// in Missionary Hill, spawn whore in Silicon Valley
			   	CREATE_CHAR PEDTYPE_PROSTITUTE SWFOPRO SFpunterX[21] SFpunterY[21] SFpunterZ[21]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[21]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer SILLY
				// in Silicon Valley, spawn whore at Easter bay airport
			   	CREATE_CHAR PEDTYPE_PROSTITUTE WFYPRO SFpunterX[22] SFpunterY[22] SFpunterZ[22]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[22]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer SFAIR
				// in easter Bay Airport, spawn whore in Missionary Hill
			   	CREATE_CHAR PEDTYPE_PROSTITUTE SWFOPRO SFpunterX[20] SFpunterY[20] SFpunterZ[20]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[20]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	//pimp_patch 6  ended	  



			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer BAYV
				// in Pallisades, spawn whore in Paradiso
			   	CREATE_CHAR PEDTYPE_PROSTITUTE SWFOPRO SFpunterX[24] SFpunterY[24] SFpunterZ[24]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[24]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer PARA
				// in Paradiso, spawn whore in Pallisades
			   	CREATE_CHAR PEDTYPE_PROSTITUTE WFYPRO SFpunterX[23] SFpunterY[23] SFpunterZ[23]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[23]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer CIVI
				// in Santa Flora, spawn whore in City Hall
			   	CREATE_CHAR PEDTYPE_PROSTITUTE WFYPRO SFpunterX[26] SFpunterY[26] SFpunterZ[26]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[26]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer CITYS
				// in City Hall, spawn whore in Santa Flora
			   	CREATE_CHAR PEDTYPE_PROSTITUTE SWFOPRO SFpunterX[25] SFpunterY[25] SFpunterZ[25]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[25]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	//pimp_patch 7  ended






			//If the player started out in the sticks they automatically wouldn't
			//have a starting zone so we need to create a whore in the centre of the closest city somewhere. 
			//If whore_spawned still equals zero ( it gets incremented in whore_suitable proc, after all of the 
			//above checks, it also acts as a failsafe in case the player is in a zone that has been missed out!


			IF whore_spawned = 0 //	Failsafe! Only gets called if all above zone checks pass a negative
		  	    // Failsafe! Player started out of town, nearest city was SF, spawn Hispanic whore at CALT, Calton Heights
				CREATE_CHAR PEDTYPE_PROSTITUTE SWFOPRO SFpunterX[0] SFpunterY[0] SFpunterZ[0]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] SFpunterH[0]
				GOSUB whore_suitable
			ENDIF

			
			MARK_MODEL_AS_NO_LONGER_NEEDED SWFOPRO
			MARK_MODEL_AS_NO_LONGER_NEEDED WFYPRO


	ENDIF	// pimp_city = LEVEL_SANFRANCISCO




	IF pimp_city = LEVEL_LASVEGAS
			REQUEST_MODEL VWFYPRO
			REQUEST_MODEL VBFYPRO 

			WHILE NOT HAS_MODEL_LOADED VWFYPRO
			OR NOT HAS_MODEL_LOADED VBFYPRO 
			WAIT 0
			ENDWHILE





			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer PINK
				// at the Pink Swan, spawn whore at the Four Dragons
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VWFYPRO LVpunterX[1] LVpunterY[1] LVpunterZ[1]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[1]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer DRAG
				// at the Four Dragon's, spawn whore at the Pink Swan
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VWFYPRO LVpunterX[0] LVpunterY[0] LVpunterZ[0]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[0]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer CAM
				// at the Camel's Toe, spawn whore at Come-A-Lot.
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VBFYPRO LVpunterX[3] LVpunterY[3] LVpunterZ[3]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[3]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer LOT
				// at Come-A-Lot, spawn whore at the Camel's Toe
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VBFYPRO LVpunterX[2] LVpunterY[2] LVpunterZ[2]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[2]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	

			// end of pp1


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer PIRA
				// at the Pirates in Mens Pants, spawn whore at the Royal Casino
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VWFYPRO LVpunterX[5] LVpunterY[5] LVpunterZ[5]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[5]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer ROY
				// at the Rotal Casino, spawn whore at the Pirates in Mens Pants
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VWFYPRO LVpunterX[4] LVpunterY[4] LVpunterZ[4]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[4]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer CALI
				// at Caligula's Palace, spawn whore at the High Roller
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VBFYPRO LVpunterX[7] LVpunterY[7] LVpunterZ[7]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[7]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer HIGH
				// at the High Roller, spawn whore at Caligula's Palace.
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VBFYPRO LVpunterX[6] LVpunterY[6] LVpunterZ[6]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[6]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	
			// end of pp2


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer PILG
				// at Pilgrim, spawn whore at the starfish casino
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VWFYPRO LVpunterX[9] LVpunterY[9] LVpunterZ[9]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[9]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer STAR
				// at the Starfish Casino, spawn whore at Pilgrim
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VWFYPRO LVpunterX[8] LVpunterY[8] LVpunterZ[8]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[8]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer STRIP
				// at the Strip, spawn whore at The Visage
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VBFYPRO LVpunterX[11] LVpunterY[11] LVpunterZ[11]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[11]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer VISA
				// at the Visage, spawn whore at the strip.
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VBFYPRO  LVpunterX[10] LVpunterY[10] LVpunterZ[10]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[10]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	
			// end of pp3


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer ISLE
				// at the Emerald Isle casino, spawn whore at the Roca Escalante
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VWFYPRO LVpunterX[13] LVpunterY[13] LVpunterZ[13]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[13]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer ROCE
				// at the Roca Escalante, spawn whore at the Emerald Isle Casino
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VWFYPRO LVpunterX[12] LVpunterY[12] LVpunterZ[12]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[12]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer OVS
				// On the Old Venturas Strip, spawn whore at the Ring Master AKA the clown's pocket
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VBFYPRO LVpunterX[15] LVpunterY[15] LVpunterZ[15]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[15]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer RING
				// at the Ring Master AKA the Clown's Pocket, spawn whore on the Old Venturas Strip
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VBFYPRO  LVpunterX[14] LVpunterY[14] LVpunterZ[14]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[14]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	
			// end of pp4


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer BFC
				// at the Blackfield Chapel, spawn whore at the greenglass college
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VWFYPRO LVpunterX[17] LVpunterY[17] LVpunterZ[17]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[17]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer GGC
				// at the Greenglass College, spawn whore at the Blackfield Chapel
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VWFYPRO LVpunterX[16] LVpunterY[16] LVpunterZ[16]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[16]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	

			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer VAIR
				// At Las Venturas Airport, spawn whore at the Last Dime Motel
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VBFYPRO  LVpunterX[19] LVpunterY[19] LVpunterZ[19]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[19]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	


			IF whore_spawned = 0
			IF IS_CHAR_IN_ZONE scplayer LDM
				// at the LDM, spawn whore at the Las Venturas Airport.
			   	CREATE_CHAR PEDTYPE_PROSTITUTE VBFYPRO  LVpunterX[18] LVpunterY[18] LVpunterZ[18]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[18]
			   	GOSUB whore_suitable
			ENDIF
			ENDIF	
			// end of pp5





			//If the player started out in the sticks they automatically wouldn't
			//have a starting zone so we need to create a whore in the centre of the closest city somewhere. 
			//If whore_spawned still equals zero ( it gets incremented in whore_suitable proc, after all of the 
			//above checks, it also acts as a failsafe in case the player is in a zone that has been missed out!


			IF whore_spawned = 0 //	Failsafe! Only gets called if all above zone checks pass a negative
		  	    // Failsafe! Player started out of town, nearest city was SF, spawn white Vegas whore at PINK, the Pink Swan
				CREATE_CHAR PEDTYPE_PROSTITUTE VWFYPRO LVpunterX[0] LVpunterY[0] LVpunterZ[0]  pimp_whore[wIndex]	
			   	SET_CHAR_HEADING pimp_whore[wIndex] LVpunterH[0]
				GOSUB whore_suitable
			ENDIF


			MARK_MODEL_AS_NO_LONGER_NEEDED VWFYPRO
			MARK_MODEL_AS_NO_LONGER_NEEDED VBFYPRO 


	ENDIF // pimp_city = LEVEL_LASVEGAS



ENDIF


IF whore_spawned = 1
	
	IF NOT IS_CHAR_DEAD pimp_whore[wIndex]
		TASK_STAND_STILL pimp_whore[wIndex] -1
		REMOVE_BLIP pimp_whoreB
		ADD_BLIP_FOR_CHAR pimp_whore[wIndex] pimp_whoreB
		SET_CHAR_DECISION_MAKER pimp_whore[wIndex] pimp_emptydm
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER pimp_whore[wIndex] TRUE
		SET_CHAR_CANT_BE_DRAGGED_OUT pimp_whore[wIndex] TRUE


		SET_BLIP_AS_FRIENDLY pimp_whoreB TRUE	
		IF wIndex = 0
			CLEAR_PRINTS
			PRINT_NOW PIMP_2 6000 1
			SET_CHAR_CANT_BE_DRAGGED_OUT pimp_whore[wIndex] TRUE

		ENDIF
		IF wIndex = 1
			CLEAR_PRINTS
			PRINT_NOW PIMP_30 6000 1
			SET_CHAR_CANT_BE_DRAGGED_OUT pimp_whore[wIndex] TRUE

		ENDIF
		//WRITE_DEBUG grabbedwhore
		whore_spawned = 2
		whore_created = 1
	ENDIF

ENDIF
									    

IF whore_created = 1
	IF DOES_CHAR_EXIST pimp_whore[0] 
		IF IS_CHAR_DEAD pimp_whore[0]
			PRINT_NOW PIMP_51 8000 1
			GOTO mission_pimp_failed
		ENDIF
	ENDIF
   
	IF both_whores_spawned = 1

		IF DOES_CHAR_EXIST pimp_whore[1] 
			IF IS_CHAR_DEAD pimp_whore[1]
			PRINT_NOW PIMP_51 8000 1
			GOTO mission_pimp_failed
			ENDIF
		ENDIF

	ENDIF
ENDIF


IF whore_spawned = 2

	IF call_fix =  1
		IF timera > 4000
			PRINT_NOW PIMP_57 6000 1
			call_fix = 99
		ENDIF
	ENDIF

//	IF cheap_fix =  1
//		IF timera > 4000
//			PRINT_NOW PIMP_37 6000 1
//			cheap_fix = 99
//		ENDIF
//	ENDIF
//
//	IF beat_fix =  1
//		IF timera > 4000
//			PRINT_NOW PIMP_36 6000 1
//			beat_fix = 99
//		ENDIF
//	ENDIF


	IF NOT IS_CHAR_DEAD pimp_whore[wIndex]
	IF NOT IS_CAR_DEAD pimp_car

		
  				IF pimp_blipswap = 0

					IF NOT IS_CHAR_IN_CAR scplayer pimp_car
						ADD_BLIP_FOR_CAR pimp_car pimp_carB
						SET_BLIP_AS_FRIENDLY pimp_carB TRUE				
						REMOVE_BLIP pimp_whoreB

					 						
						pimp_blipswap = 1												  
					ENDIF
				

				ENDIF // bcou_blipswap = 0 condition check

				IF pimp_blipswap = 1
																								   
					IF IS_CHAR_IN_CAR scplayer pimp_car
						REMOVE_BLIP pimp_carB
								  	
					  	pimp_blipswap = 0
						REMOVE_BLIP pimp_whoreB	
						ADD_BLIP_FOR_CHAR pimp_whore[wIndex] pimp_whoreB
						SET_BLIP_AS_FRIENDLY pimp_whoreB TRUE
					    PRINT_NOW PIMP_54 6000 1
						pimp_blipswap = 0

					ENDIF
																															  
				ENDIF






		IF whore_entervehicle = 0
		IF pimp_blipswap = 0
		IF LOCATE_CHAR_IN_CAR_CHAR_2D scplayer pimp_whore[wIndex] 7.0 7.0 FALSE
			IF whore_lookat = 0 
				OPEN_SEQUENCE_TASK whore_acknowledgeseq
					TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer  
					TASK_STAND_STILL -1 -1     		
				CLOSE_SEQUENCE_TASK whore_acknowledgeseq

    			PERFORM_SEQUENCE_TASK pimp_whore[wIndex] whore_acknowledgeseq
    			CLEAR_SEQUENCE_TASK whore_acknowledgeseq
				whore_lookat = 1 
			ENDIF				


			IF IS_CAR_STOPPED pimp_car
			   	CLEAR_CHAR_TASKS pimp_whore[wIndex]
			  	 TASK_ENTER_CAR_AS_PASSENGER pimp_whore[wIndex] pimp_car 60000 -1
				 // should be -1, to enter any seat
		 		 whore_entervehicle = 1
				// write_debug_with_int wi wIndex
			ENDIF
		ENDIF
		ENDIF
		ENDIF



		IF NOT IS_CAR_DEAD pimp_car
		 //write_debug_with_int wi wIndex

		
		IF NOT IS_CHAR_DEAD pimp_whore[wIndex]
	  	IF IS_CHAR_SITTING_IN_CAR pimp_whore[wIndex] pimp_car
			// write_debug_with_int wi wIndex


			IF increments_this_run < 2
			
				increments_this_run++
				INCREMENT_INT_STAT NUM_GIRLS_PIMPED 1
				
			 
			ENDIF
			

			whore_entervehicle = 2
			REMOVE_BLIP pimp_whoreB
			whore_spawned = 99
		   
			whore_trickfind = 1	
			
			GOSUB pimp_patch_decider
			
			pimp_chat = 1
			TIMERA = 0

			IF whore_tricknumber = 10//10
				CLEAR_PRINTS 
				PRINT_NOW SEXGOD 6000 1
				GOTO mission_pimp_passed
			ENDIF
		ENDIF
		ENDIF
		ENDIF
	ENDIF
	ENDIF
ENDIF


IF whore_entervehicle = 1


		IF NOT IS_CHAR_DEAD pimp_whore[wIndex]
		IF NOT IS_CAR_DEAD pimp_car
		
			GET_SCRIPT_TASK_STATUS pimp_whore[wIndex] TASK_ENTER_CAR_AS_PASSENGER pimptask_status

			IF pimptask_status = FINISHED_TASK

				IF NOT IS_CHAR_IN_CAR pimp_whore[wIndex] pimp_car
		
					CLEAR_CHAR_TASKS pimp_whore[wIndex]
					TASK_ENTER_CAR_AS_PASSENGER pimp_whore[wIndex] pimp_car 60000 -1
			
				ENDIF
				
				
			ENDIF

		ENDIF
		ENDIF



ENDIF


/*
IF punter_wander[pimp_removalindex] = 1
   TASK_SMART_FLEE_CHAR punter[pimp_removalindex] scplayer 100.0 -1
   punter_wander[pimp_removalindex] = 2

ENDIF
 */


IF whore_trickfind = 1	

	//GOSUB pimp_patch_decider


	IF pimp_city = LEVEL_LOSANGELES

	
	//IF TIMERA > 2000	 working
	IF pimp_patch = 1  //South Los Santos A

		IF pimp_donealready[0] = 1
			IF pimp_donealready[1] = 1
			   IF pimp_donealready[2] = 1
					 IF pimp_donealready[3] = 1
						pimp_patch = 2 // All tricks done in this area , need to try another
					 ENDIF
				ENDIF
			ENDIF
		ENDIF


		GENERATE_RANDOM_INT_IN_RANGE 0 4 pimp_trick
		//TIMERA = 0

		IF pimp_trick = 0	//SLS B Docks
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL swmyhp1
			WHILE NOT HAS_MODEL_LOADED swmyhp1
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE swmyhp1 LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED swmyhp1
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_10 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF
		
		IF pimp_trick = 1	//SLS B Airport	 LAIR Los Santos International
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL swmyhp1
			WHILE NOT HAS_MODEL_LOADED swmyhp1
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE swmyhp1 LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED swmyhp1
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_11 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 2	//SLS B Industrial	LIND Willowfield
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL swmyhp1
			WHILE NOT HAS_MODEL_LOADED swmyhp1
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE swmyhp1 LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED swmyhp1 
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE

			PRINT_NOW PIMP_12 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF


		IF pimp_trick = 3	//SLS B Was (Verdant ) BLUF Bluffs
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL BMYST
			WHILE NOT HAS_MODEL_LOADED BMYST
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE BMYST LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED BMYST
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_13 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF


		ENDIF

	   


	ENDIF







	IF pimp_patch = 2  //South Los Santos B

		IF pimp_donealready[4] = 1
			IF pimp_donealready[5] = 1
			   IF pimp_donealready[6] = 1
					 IF pimp_donealready[7] = 1
						pimp_patch = 3 // All tricks done in this area , need to try another
					 ENDIF
				ENDIF
			ENDIF
		ENDIF




		GENERATE_RANDOM_INT_IN_RANGE 4 8 pimp_trick
		
		IF pimp_trick = 4	//NLS A Civic Centre / City Hall etc...	  COM Commerce
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL bmyst
			WHILE NOT HAS_MODEL_LOADED bmyst
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE bmyst LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED bmyst
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_14 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
		ENDIF

		IF pimp_trick = 5	//NLS A Downtown   LDT Downtown Los Santos
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmybu
			WHILE NOT HAS_MODEL_LOADED wmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmybu LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_15 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 6	//NLS A Glen Park	  GLN Glen Park
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL BMYST
			WHILE NOT HAS_MODEL_LOADED BMYST
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE BMYST LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED BMYST
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_16 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF
		ENDIF

		IF pimp_trick = 7	//NLS A Chicano Heights		  CHC Las Colinas	  Now Little Mexico
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL BMYST
			WHILE NOT HAS_MODEL_LOADED BMYST
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE BMYST LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED BMYST
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_17 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF


   
							

	ENDIF

	IF pimp_patch = 3  //North Los Santos A

		IF pimp_donealready[8] = 1
			IF pimp_donealready[9] = 1
			   IF pimp_donealready[10] = 1
					 IF pimp_donealready[11] = 1
						pimp_patch = 4 // All tricks done in this area , need to try another
					 ENDIF
				ENDIF
			ENDIF
		ENDIF





		GENERATE_RANDOM_INT_IN_RANGE 8 12 pimp_trick

		IF pimp_trick = 8	//NLS B Jefferson	JEFSN Jefferson
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmoprea
			WHILE NOT HAS_MODEL_LOADED wmoprea
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmoprea LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmoprea
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_18 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		
		
		
		IF pimp_trick = 9	//NLS B East Los Santos	  ELS East Los Santos
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL hmyst
			WHILE NOT HAS_MODEL_LOADED hmyst
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE hmyst LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED hmyst
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_19 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF
		
		
		
		
		IF pimp_trick = 10	//NLS B Los Flores	LFL Los Flores
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL hmyst
			WHILE NOT HAS_MODEL_LOADED hmyst
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE hmyst LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED hmyst
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_20 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1			//pimp_removalindex = 0
			ENDIF

		ENDIF
		
		
		
		
		IF pimp_trick = 11 //NLS B East Beach	EBE East Beach
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmybu
			WHILE NOT HAS_MODEL_LOADED wmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmybu LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_21 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF
		
  	   

	ENDIF

	IF pimp_patch = 4  //North Los Santos B

		IF pimp_donealready[12] = 1
			IF pimp_donealready[13] = 1
			   IF pimp_donealready[14] = 1
					 IF pimp_donealready[15] = 1
						pimp_patch = 5 // All tricks done in this area , need to try another
					 ENDIF
				ENDIF
			ENDIF
		ENDIF



		GENERATE_RANDOM_INT_IN_RANGE 12 16 pimp_trick
		IF pimp_trick = 12	//WLS A CONF Conference Centre 
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmybu
			WHILE NOT HAS_MODEL_LOADED wmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmybu LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_22 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF
		IF pimp_trick = 13	//WLS A VERON Verona Beach
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmybmx
			WHILE NOT HAS_MODEL_LOADED wmybmx
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmybmx LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmybmx
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_23 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF



		ENDIF
		IF pimp_trick = 14	//WLS A SMB Santa Maria Beach 
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL bmybu
			WHILE NOT HAS_MODEL_LOADED bmybu
				WAIT 0
			ENDWHILE

			//
			//
			//Changed as it was too far away at times! Points to Verona ( 13 )
			//
			//

		   	CREATE_CHAR PEDTYPE_CIVMALE bmybu LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED bmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_24 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
		ENDIF
		IF pimp_trick = 15	//WLS A Marina
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmymech
			WHILE NOT HAS_MODEL_LOADED wmymech
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmymech LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmymech
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_25 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF
	ENDIF

	IF pimp_patch = 5  //West Los Santos A

		IF pimp_donealready[16] = 1
			IF pimp_donealready[17] = 1
			   IF pimp_donealready[18] = 1
					 IF pimp_donealready[19] = 1
						pimp_patch = 6 // All tricks done in this area , need to try another
					 ENDIF
				ENDIF
			ENDIF
		ENDIF




		GENERATE_RANDOM_INT_IN_RANGE 16 20 pimp_trick
		IF pimp_trick = 16	//WLS B SMB Santa Maria Beach  COULD BE MARKET!!!!!!  Went for Market
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmybu
			WHILE NOT HAS_MODEL_LOADED wmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmybu LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_26 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF


			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
		ENDIF
		IF pimp_trick = 17	//WLS B PLS Playa Del Sevilla	? Doesn't exist? Will use another Rodeo coord
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL hmyst
			WHILE NOT HAS_MODEL_LOADED hmyst
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE hmyst LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED hmyst
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_27 4000 1 //Central Rodeo
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
		ENDIF
		IF pimp_trick = 18	//WLS B ROD Rodeo
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmybmx
			WHILE NOT HAS_MODEL_LOADED wmybmx
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmybmx LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmybmx
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_28 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF
		IF pimp_trick = 19	//WLS B RIH Richman Heights
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL bmybu
			WHILE NOT HAS_MODEL_LOADED bmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE bmybu LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED bmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_29 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF
	ENDIF

	IF pimp_patch = 6  //West Los Santos B

		IF pimp_donealready[20] = 1
			IF pimp_donealready[21] = 1
			   IF pimp_donealready[22] = 1
					 IF pimp_donealready[23] = 1
						pimp_patch = 7 // All tricks done in this area , need to try another
					 ENDIF
				ENDIF
			ENDIF
		ENDIF


		GENERATE_RANDOM_INT_IN_RANGE 20 24 pimp_trick
		IF pimp_trick = 20	//WLS C MUL Mulholland
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL  wmygol1
			WHILE NOT HAS_MODEL_LOADED  wmygol1
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE  wmygol1 LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED  wmygol1
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_80 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF
		IF pimp_trick = 21	//WLS C MUL Mulholland	  Carwash
	 		//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL  wmymech
			WHILE NOT HAS_MODEL_LOADED  wmymech
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE  wmymech LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED  wmymech
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_81 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 22	//WLS C VIN Vinewood
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL  hmyst
			WHILE NOT HAS_MODEL_LOADED hmyst
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE hmyst LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED hmyst
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_82 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF
		IF pimp_trick = 23	//WLS C SUN Sunrise / Temple boulevard
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL  wmoprea
			WHILE NOT HAS_MODEL_LOADED  wmoprea
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE  wmoprea LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED  wmoprea
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_83 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
		ENDIF
	ENDIF

	IF pimp_patch = 7  //West Los Santos C

		IF pimp_donealready[24] = 1
			IF pimp_donealready[25] = 1
			   IF pimp_donealready[26] = 1
					 IF pimp_donealready[27] = 1
						pimp_patch = 1 // All tricks done in this area , need to try another
					 ENDIF
				ENDIF
			ENDIF
		ENDIF


		GENERATE_RANDOM_INT_IN_RANGE 24 28 pimp_trick
		IF pimp_trick = 24	//SLS A IWD Idlewood
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL bmyboun
			WHILE NOT HAS_MODEL_LOADED bmyboun
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE bmyboun LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED bmyboun
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_84 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF
		IF pimp_trick = 25	//SLS A GAN Ganton
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL bmydj
			WHILE NOT HAS_MODEL_LOADED bmydj
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE bmydj LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED bmydj
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_85 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF
		IF pimp_trick = 26	//SLS A El Corona	ELCO El Corona
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmydrug
			WHILE NOT HAS_MODEL_LOADED wmydrug
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmydrug LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmydrug
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_86 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF
		IF pimp_trick = 27	//SLS A Little Mexico
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL hmyst
			WHILE NOT HAS_MODEL_LOADED hmyst
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE hmyst LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LApunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED hmyst
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP_87 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
		ENDIF
	ENDIF

	ENDIF // pimp_city = LEVEL_LOSANGELES condition check

	///////////////////////////////////////////////////////////////////////////////////////////////////////End of LA coords



															
	IF pimp_city = LEVEL_SANFRANCISCO	
	
	
	IF pimp_patch = 1  //SF

		IF pimp_donealready[0] = 1
			IF pimp_donealready[1] = 1
			   IF pimp_donealready[2] = 1
					 IF pimp_donealready[3] = 1
						pimp_patch = 2 // All tricks done in this area , need to try another
					 ENDIF
				ENDIF
			ENDIF
		ENDIF


		GENERATE_RANDOM_INT_IN_RANGE 0 4 pimp_trick
		//TIMERA = 0

		IF pimp_trick = 0	//CALT, brick path at Calton Heights
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL swmyhp1
			WHILE NOT HAS_MODEL_LOADED swmyhp1
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE swmyhp1 SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED swmyhp1
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP200 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF
		
		IF pimp_trick = 1	//Top of Park JUNIHI, Juniper Hill
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL swmyhp1
			WHILE NOT HAS_MODEL_LOADED swmyhp1
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE swmyhp1 SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED swmyhp1
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP201 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 2	//JUNIHI Juniper Hill		
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL WMYMECH
			WHILE NOT HAS_MODEL_LOADED WMYMECH
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE WMYMECH SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP202 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF


		IF pimp_trick = 3	//ESPN Esplanade North
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL WMYMECH
			WHILE NOT HAS_MODEL_LOADED WMYMECH
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE WMYMECH SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP203 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF


		ENDIF

	   


	ENDIF  // pp1 

					   
	IF pimp_patch = 2  //SF

		IF pimp_donealready[4] = 1
			IF pimp_donealready[5] = 1
			   IF pimp_donealready[6] = 1
					 IF pimp_donealready[7] = 1
						pimp_patch = 3 // All tricks done in this area , need to try another
					 ENDIF
				ENDIF
			ENDIF
		ENDIF


		GENERATE_RANDOM_INT_IN_RANGE 4 8 pimp_trick
		//TIMERA = 0

		IF pimp_trick = 4	//SUNMA , Bayside Marina

			pimp_donealready[pimp_trick] = 1
			//This will disable this location from ever being picked as it might not have been opened up yet.
			//!
			//!

			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL bmybu
			WHILE NOT HAS_MODEL_LOADED bmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE bmybu SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED bmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP204 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF
		
		IF pimp_trick = 5	//Bayside Housing, car park near front pier
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick


			pimp_donealready[pimp_trick] = 1
			//This will disable this location from ever being picked as it might not have been opened up yet.
			//!
			//!


			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmybu
			WHILE NOT HAS_MODEL_LOADED wmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmybu SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP205 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 6	//Driveway of house nearby Battery Point		DODGY! Coords aren't in actual zones now!
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL WMYMECH
			WHILE NOT HAS_MODEL_LOADED WMYMECH
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE WMYMECH SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP206 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF


		IF pimp_trick = 7	//Corner of road in Pallisades , near Gant Bridge	 DODGY!	 Coords aren't in actual zones now!
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL BMYST
			WHILE NOT HAS_MODEL_LOADED BMYST
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE BMYST SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED BMYST
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP207 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF


		ENDIF  

	   


	ENDIF //pp2



	IF pimp_patch = 3  //SF

		IF pimp_donealready[8] = 1
			IF pimp_donealready[9] = 1
			   IF pimp_donealready[10] = 1
					 IF pimp_donealready[11] = 1
						pimp_patch = 4 // All tricks done in this area , need to try another
					 ENDIF
				ENDIF
			ENDIF
		ENDIF


		GENERATE_RANDOM_INT_IN_RANGE 8 12 pimp_trick
		//TIMERA = 0

		IF pimp_trick = 8	//CHINA Chinatown
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL OMOST
			WHILE NOT HAS_MODEL_LOADED OMOST
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE OMOST SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED OMOST
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP208 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF
		
		IF pimp_trick = 9	//Downtown, near plaza section
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmybu
			WHILE NOT HAS_MODEL_LOADED wmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmybu SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP209 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 10	//Financial district by big blue glass fronted building	
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL bmybu
			WHILE NOT HAS_MODEL_LOADED bmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE bmybu SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED bmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP210 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF


		IF pimp_trick = 11	//Esplanade East on promenade area
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL BMYST
			WHILE NOT HAS_MODEL_LOADED BMYST
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE BMYST SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED BMYST
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP211 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF


		ENDIF  

	   


	ENDIF //pp3


	IF pimp_patch = 4  //SF

		IF pimp_donealready[12] = 1
			IF pimp_donealready[13] = 1
			   IF pimp_donealready[14] = 1
					 IF pimp_donealready[15] = 1
						pimp_patch = 5 // All tricks done in this area , need to try another
					 ENDIF
				ENDIF
			ENDIF
		ENDIF


		GENERATE_RANDOM_INT_IN_RANGE 12 16 pimp_trick
		//TIMERA = 0

		IF pimp_trick = 12	//WESTP West Park
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmybu
			WHILE NOT HAS_MODEL_LOADED wmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmybu SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP212 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF
		
		IF pimp_trick = 13	//HASH Hashbury
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL WMYDRUG
			WHILE NOT HAS_MODEL_LOADED WMYDRUG
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE WMYDRUG SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED WMYDRUG
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP213 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 14	//Ocean Flats
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL bmybu
			WHILE NOT HAS_MODEL_LOADED bmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE bmybu SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED bmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP214 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF


		IF pimp_trick = 15	//Avispa Country Club
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL WMYGOL1
			WHILE NOT HAS_MODEL_LOADED WMYGOL1
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE WMYGOL1 SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED WMYGOL1

			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE

			PRINT_NOW PIMP215 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF


		ENDIF  

	   


	ENDIF //pp4


	IF pimp_patch = 5  //SF

		IF pimp_donealready[16] = 1
			IF pimp_donealready[17] = 1
			   IF pimp_donealready[18] = 1
					 IF pimp_donealready[19] = 1
						pimp_patch = 6 // All tricks done in this area , need to try another
					 ENDIF
				ENDIF
			ENDIF
		ENDIF


		GENERATE_RANDOM_INT_IN_RANGE 16 20 pimp_trick
		//TIMERA = 0

		IF pimp_trick = 16	//King's Car Park
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmybu
			WHILE NOT HAS_MODEL_LOADED wmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmybu SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP216 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF
		
		IF pimp_trick = 17	//Garcia side alley
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL WMYDRUG
			WHILE NOT HAS_MODEL_LOADED WMYDRUG
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE WMYDRUG SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED WMYDRUG
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP217 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 18	//Doherty building site	
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL WMYCON
			WHILE NOT HAS_MODEL_LOADED WMYCON
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE WMYCON SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED WMYCON
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP218 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF


		IF pimp_trick = 19	//Easter Basin by bridge pillar
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL WMYMECH
			WHILE NOT HAS_MODEL_LOADED WMYMECH
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE WMYMECH SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP219 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF


		ENDIF  

	   


	ENDIF //pp5


	IF pimp_patch = 6  //SF

		IF pimp_donealready[20] = 1
			IF pimp_donealready[21] = 1
			   IF pimp_donealready[22] = 1
					 pimp_patch = 7 // All tricks done in this area , need to try another
					 
				ENDIF
			ENDIF
		ENDIF


		GENERATE_RANDOM_INT_IN_RANGE 20 23 pimp_trick
		//TIMERA = 0

		IF pimp_trick = 20	//Foot of Missionary Hill
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmybu
			WHILE NOT HAS_MODEL_LOADED wmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmybu SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP220 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF
		
		IF pimp_trick = 21	//By rock display in Foster Valley
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL bmybu
			WHILE NOT HAS_MODEL_LOADED bmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE bmybu SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED bmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP221 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 22	//near guard hut at Easter Bay Airport
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmysgrd
			WHILE NOT HAS_MODEL_LOADED wmysgrd
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmysgrd SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmysgrd
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP222 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF


  
	   


	ENDIF //pp6


	IF pimp_patch = 7  //SF

		IF pimp_donealready[23] = 1
			IF pimp_donealready[24] = 1
			   IF pimp_donealready[25] = 1
					 IF pimp_donealready[26] = 1
						pimp_patch = 1 // All tricks done in this area , need to try another
					 ENDIF
				ENDIF
			ENDIF
		ENDIF


		GENERATE_RANDOM_INT_IN_RANGE 23 27 pimp_trick
		//TIMERA = 0

		IF pimp_trick = 23	//Pallisades Terrace
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmybu
			WHILE NOT HAS_MODEL_LOADED wmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmybu SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP223 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF
		
		IF pimp_trick = 24 // Back Alley in Paradiso
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL WMYDRUG
			WHILE NOT HAS_MODEL_LOADED WMYDRUG
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE WMYDRUG SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED WMYDRUG
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP224 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 25	//car park in Santa Flora
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmybmx
			WHILE NOT HAS_MODEL_LOADED wmybmx
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmybmx SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmybmx
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP225 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF

		ENDIF


		IF pimp_trick = 26	//City Hall
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL bmybu
			WHILE NOT HAS_MODEL_LOADED bmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE bmybu SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] SFpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED bmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP226 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1
			//pimp_removalindex = 0
			ENDIF


		ENDIF  

	   


	ENDIF //pp7



	// Don't forget check stuff to ensure pimp-patch is a number which will generate a punter 
	// put in here >>>>>>>>>>>>>>>>
	



	ENDIF // pimp_city = LEVEL_SANFRANCISCO condition check




	IF pimp_city = LEVEL_LASVEGAS

	IF pimp_patch = 1  //LAS VEGAS

		IF pimp_donealready[0] = 1
			IF pimp_donealready[1] = 1
			   IF pimp_donealready[2] = 1
					 IF pimp_donealready[3] = 1
						pimp_patch = 2 // All tricks done in this area , need to try another
					 ENDIF
				ENDIF
			ENDIF
		ENDIF


		GENERATE_RANDOM_INT_IN_RANGE 0 4 pimp_trick
		//TIMERA = 0

		IF pimp_trick = 0	//Pink Swan Casino
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL swmyhp1
			WHILE NOT HAS_MODEL_LOADED swmyhp1
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE swmyhp1 LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED swmyhp1
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP300 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 1	//Four Dragon's Casino
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL swmyhp1
			WHILE NOT HAS_MODEL_LOADED swmyhp1
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE swmyhp1 LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED swmyhp1
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP301 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 2	//Camel's Toe
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL bmybu
			WHILE NOT HAS_MODEL_LOADED bmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE bmybu LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED bmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP302 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 3	//Come-A-Lot
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmybu
			WHILE NOT HAS_MODEL_LOADED wmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmybu LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP303 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF




	 ENDIF // pp1

	IF pimp_patch = 2  //LAS VEGAS

		IF pimp_donealready[4] = 1
			IF pimp_donealready[5] = 1
			   IF pimp_donealready[6] = 1
					 IF pimp_donealready[7] = 1
						pimp_patch = 3 // All tricks done in this area , need to try another
					 ENDIF
				ENDIF
			ENDIF
		ENDIF


		GENERATE_RANDOM_INT_IN_RANGE 4 8 pimp_trick
		//TIMERA = 0

		IF pimp_trick = 4	//The Pirates in Men's Pants
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL DWMOLC1
			WHILE NOT HAS_MODEL_LOADED DWMOLC1
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE DWMOLC1 LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED DWMOLC1
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP304 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 5	//Royal Casino
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL DWMOLC2
			WHILE NOT HAS_MODEL_LOADED DWMOLC2
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE DWMOLC2 LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED DWMOLC2
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP305 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 6	//Caligula's Palace
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL bmybu
			WHILE NOT HAS_MODEL_LOADED bmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE bmybu LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED bmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP306 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 7	//The High Roller
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmybu
			WHILE NOT HAS_MODEL_LOADED wmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmybu LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP307 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF




	 ENDIF // pp2

	 IF pimp_patch = 3  //LAS VEGAS

		IF pimp_donealready[8] = 1
			IF pimp_donealready[9] = 1
			   IF pimp_donealready[10] = 1
					 IF pimp_donealready[11] = 1
						pimp_patch = 4 // All tricks done in this area , need to try another
					 ENDIF
				ENDIF
			ENDIF
		ENDIF


		GENERATE_RANDOM_INT_IN_RANGE 8 12 pimp_trick
		//TIMERA = 0

		IF pimp_trick = 8	//Pilgrim
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL DWMOLC1
			WHILE NOT HAS_MODEL_LOADED DWMOLC1
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE DWMOLC1 LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED DWMOLC1
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP308 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 9	//Starfish Casino
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL DWMOLC2
			WHILE NOT HAS_MODEL_LOADED DWMOLC2
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE DWMOLC2 LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED DWMOLC2
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP309 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 10	//The Strip
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL bmybu
			WHILE NOT HAS_MODEL_LOADED bmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE bmybu LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED bmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP310 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 11	//The Visage
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmybu
			WHILE NOT HAS_MODEL_LOADED wmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmybu LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP311 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF




	 ENDIF // pp3


	 IF pimp_patch = 4  //LAS VEGAS

		IF pimp_donealready[12] = 1
			IF pimp_donealready[13] = 1
			   IF pimp_donealready[14] = 1
					 IF pimp_donealready[15] = 1
						pimp_patch = 5 // All tricks done in this area , need to try another
					 ENDIF
				ENDIF
			ENDIF
		ENDIF


		GENERATE_RANDOM_INT_IN_RANGE 12 16 pimp_trick
		//TIMERA = 0

		IF pimp_trick = 12	// The Emerald Isle
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL WMYMECH
			WHILE NOT HAS_MODEL_LOADED WMYMECH
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE WMYMECH LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP312 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 13	//Roca Escalante
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL DWMYLC1
			WHILE NOT HAS_MODEL_LOADED DWMYLC1
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE DWMYLC1 LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED DWMYLC1
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP313 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 14	//Old Venturas Strip
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL bmybu
			WHILE NOT HAS_MODEL_LOADED bmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE bmybu LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED bmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP314 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 15	//The Ring Master AKA the Clown's Pocket
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmydrug
			WHILE NOT HAS_MODEL_LOADED wmydrug
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmydrug LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmydrug
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP315 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF




	 ENDIF // pp4

	 IF pimp_patch = 5  //LAS VEGAS

		IF pimp_donealready[16] = 1
			IF pimp_donealready[17] = 1
			   IF pimp_donealready[18] = 1
					 IF pimp_donealready[19] = 1
						pimp_patch = 1 // All tricks done in this area , need to try another
					 ENDIF
				ENDIF
			ENDIF
		ENDIF


		GENERATE_RANDOM_INT_IN_RANGE 16 20 pimp_trick
		//TIMERA = 0

		IF pimp_trick = 16	// Blackfield Chapel
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmoprea
			WHILE NOT HAS_MODEL_LOADED wmoprea
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmoprea LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmoprea
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP316 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 17	//Greenglass College
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL wmyst
			WHILE NOT HAS_MODEL_LOADED wmyst
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE wmyst LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED wmyst
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP317 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 18	//Las Venturas Airport
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL bmybu
			WHILE NOT HAS_MODEL_LOADED bmybu
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE bmybu LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED bmybu
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP318 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF

		IF pimp_trick = 19	//Last Dime Motel
			IF pimp_donealready[pimp_trick] = 0
			REQUEST_MODEL DWMYLC1
			WHILE NOT HAS_MODEL_LOADED DWMYLC1
				WAIT 0
			ENDWHILE
		   	CREATE_CHAR PEDTYPE_CIVMALE DWMYLC1 LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] punter[pimp_trick]
			//WRITE_DEBUG_WITH_INT Trick_located_in_area pimp_trick
			SET_CHAR_HEADING punter[pimp_trick] LVpunterH[pimp_trick]
			MARK_MODEL_AS_NO_LONGER_NEEDED DWMYLC1
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER punter[pimp_trick] TRUE


			PRINT_NOW PIMP319 4000 1
			whore_trickfind = 2
			TIMERA = 0

			pimp_donealready[pimp_trick] = 1
			punterhurt[pimp_trick] = 1 // punter created - flagged as "hurt him you fail!"
			//pimp_removalindex = 0
			ENDIF

		ENDIF




	 ENDIF // pp5



	ENDIF // pimp_city = LEVEL_LASVEGAS condition check

	  
ENDIF  // punter creation section


IF pimp_chat = 1
	IF TIMERA > 5000
		IF whore_tricknumber < 1
		
				IF wIndex = 0
					PRINT_NOW PIMP_3 6000 1
				ENDIF
				IF wIndex = 1
					PRINT_NOW PIMP_31 6000 1
				ENDIF

		ENDIF
		
		IF whore_tricknumber > 0

			pimp_sfx = 1 // play wheezing

			IF NOT punter_scenario = 1
				//CLEAR_ONSCREEN_TIMER pimp_runtimer

				CREATE_MENU PIMP_70 31.0 80.0 89.0 2 FALSE TRUE FO_LEFT pimp_window
				pimp_window_showing = 1

				SET_MENU_COLUMN pimp_window 0 PIMP_71 PIMP_72 PIMP_73 PIMP_74 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
				SET_MENU_COLUMN_ORIENTATION pimp_window 1 FO_RIGHT 

			    pimp_cut = 300
				total_pimpbonus = whore_tricknumber * pimp_cut

				SET_MENU_ITEM_WITH_NUMBER pimp_window 1 0 DOLLAR pimp_cut
				SET_MENU_ITEM_WITH_NUMBER pimp_window 1 1 PIMP_75 whore_tricknumber
				SET_MENU_ITEM_WITH_NUMBER pimp_window 1 2 DOLLAR total_pimpbonus
				SET_ACTIVE_MENU_ITEM pimp_window 2

				ftotal_pimpbonus =# total_pimpbonus  // need a float copy of total pimp bonus in order to increment stat properly.
				
				IF wIndex = 0
				PRINT_NOW PIMP_7 6000 1
				ADD_SCORE player1 total_pimpbonus
				INCREMENT_FLOAT_STAT MONEY_MADE_PIMPING ftotal_pimpbonus 
				ENDIF

				IF wIndex = 1
				PRINT_NOW PIMP_32 6000 1
				ADD_SCORE player1 total_pimpbonus
				INCREMENT_FLOAT_STAT MONEY_MADE_PIMPING ftotal_pimpbonus
				ENDIF

			ENDIF
		ENDIF

		pimp_chat = 2
		TIMERA = 0

	ENDIF
ENDIF

IF pimp_chat = 2
	IF timera > 6000
		IF pimp_window_showing = 1
			DELETE_MENU pimp_window
			pimp_window_showing = 0
		ENDIF
		pimp_chat = 3
	ENDIF
ENDIF

IF whore_trickfind = 2 

	IF NOT IS_CHAR_DEAD punter[pimp_trick]

		IF wIndex = 0
		punter_holder0 = pimp_trick
		ENDIF

		IF wIndex = 1
		punter_holder1 = pimp_trick
		ENDIF

	ENDIF


	REMOVE_BLIP punterB
	ADD_BLIP_FOR_CHAR punter[pimp_trick] punterB
    CHANGE_BLIP_COLOUR punterB yellow
	
	pimp_runtimer = 180000

	CLEAR_ONSCREEN_TIMER pimp_runtimer
	pimp_timerpresent = 0
	DISPLAY_ONSCREEN_TIMER_WITH_STRING pimp_runtimer TIMER_DOWN PIMP_90
	pimp_timerpresent = 1 // deliver girl check
	
	whore_trickfind = 3
	whore_stopprompt = 0


ENDIF


IF whore_trickfind = 3
										  
   IF NOT IS_CHAR_DEAD punter[pimp_trick]
   IF NOT IS_CAR_DEAD pimp_car
		IF pimp_blipswap = 0

					IF NOT IS_CHAR_IN_CAR scplayer pimp_car
						ADD_BLIP_FOR_CAR pimp_car pimp_carB
						SET_BLIP_AS_FRIENDLY pimp_carB TRUE				
						REMOVE_BLIP punterB

					 						
						pimp_blipswap = 1												  
					ENDIF
				

				ENDIF // bcou_blipswap = 0 condition check

				IF pimp_blipswap = 1
																								   
					IF IS_CHAR_IN_CAR scplayer pimp_car
						REMOVE_BLIP pimp_carB
								  	
					  	pimp_blipswap = 0
						
						REMOVE_BLIP punterB		
						ADD_BLIP_FOR_CHAR punter[pimp_trick] punterB
						CHANGE_BLIP_COLOUR punterB yellow
					    PRINT_NOW PIMP_53 6000 1
						pimp_blipswap = 0

					ENDIF
																															  
				ENDIF

	IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR punter[pimp_trick] scplayer
	PRINT_NOW PIMP_52 8000 1
	GOTO mission_pimp_failed
	ENDIF

	IF NOT IS_CAR_DEAD pimp_car
		IF NOT IS_CHAR_DEAD pimp_whore[wIndex]

		IF whore_leavevehicle = 0
			IF pimp_blipswap = 0
			IF LOCATE_CHAR_IN_CAR_CHAR_2D scplayer punter[pimp_trick] 10.0 10.0 FALSE
				//WRITE_DEBUG inpunterrange
				
				IF whore_stopprompt = 0
					IF wIndex = 0
						PRINT_NOW PIMP_6 4000 1
						whore_stopprompt =1
					ENDIF
					IF wIndex = 1
						PRINT_NOW PIMP_33 4000 1
						whore_stopprompt =1
					ENDIF

				ENDIF

				whore_copy = wIndex // need to do this for timing issues


				IF IS_CAR_STOPPED pimp_car
					TASK_TURN_CHAR_TO_FACE_CHAR punter[pimp_trick] pimp_whore[whore_copy]

					CLEAR_CHAR_TASKS pimp_whore[whore_copy]

					punter_copy = pimp_trick
					
					pimp_timerpresent = 0
					CLEAR_ONSCREEN_TIMER pimp_runtimer
					pimp_runtimer = 180000

					
					OPEN_SEQUENCE_TASK whore_punterseq
						//TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer

						TASK_LEAVE_CAR -1 pimp_car
						CLEAR_MISSION_AUDIO 4
						IF pimp_city = LEVEL_LOSANGELES
							TASK_GO_STRAIGHT_TO_COORD -1 LApunterX[pimp_trick] LApunterY[pimp_trick] LApunterZ[pimp_trick] PEDMOVE_RUN 10000
						ENDIF
						IF pimp_city = LEVEL_SANFRANCISCO
							TASK_GO_STRAIGHT_TO_COORD -1 SFpunterX[pimp_trick] SFpunterY[pimp_trick] SFpunterZ[pimp_trick] PEDMOVE_RUN 10000
						ENDIF
						IF pimp_city = LEVEL_LASVEGAS
							TASK_GO_STRAIGHT_TO_COORD -1 LVpunterX[pimp_trick] LVpunterY[pimp_trick] LVpunterZ[pimp_trick] PEDMOVE_RUN 10000
						ENDIF

					CLOSE_SEQUENCE_TASK whore_punterseq

    				PERFORM_SEQUENCE_TASK pimp_whore[whore_copy] whore_punterseq
    				CLEAR_SEQUENCE_TASK whore_punterseq
					
					whore_leavevehicle = 1

					whorepunterchat = 1
					
					whore_trickfind = 4
					TIMERA = 0	

				ENDIF
				ENDIF
						
		  	ENDIF
		ENDIF
	 
		ENDIF
		ENDIF
	ENDIF
ENDIF
ENDIF


IF whorepunterchat = 1
	IF NOT IS_CHAR_DEAD pimp_whore[whore_copy]
	IF NOT IS_CHAR_DEAD punter[punter_copy]

	  IF LOCATE_CHAR_ANY_MEANS_CHAR_2D pimp_whore[whore_copy] punter[punter_copy] 3.0 3.0 FALSE
		CLEAR_CHAR_TASKS pimp_whore[whore_copy]
		CLEAR_CHAR_TASKS punter[punter_copy]
	   
		TASK_CHAT_WITH_CHAR pimp_whore[whore_copy] punter[punter_copy]  true true //ped0 will lead the chatting
		TASK_CHAT_WITH_CHAR punter[punter_copy] pimp_whore[whore_copy] false true //ped1 will follow ped0 at chatting
		whorepunterchat = 0
  	  ENDIF
	ENDIF
	ENDIF
ENDIF



IF whore_trickfind= 4
  
	IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR punter[pimp_trick] scplayer
	 	PRINT_NOW PIMP_52 8000 1
	   	GOTO mission_pimp_failed
   	ENDIF

	IF NOT IS_CHAR_DEAD pimp_whore[whore_copy]
	IF NOT IS_CHAR_DEAD punter[pimp_trick]

		IF both_whores_spawned = 1
			whore_trickfind = 15
		ENDIF

		IF both_whores_spawned = 0
			
			IF wIndex = 0
				whore_spawned = 0
				pimp_patch_decided = 0
				wIndex = 1 // Use the other hoor now!
			
				whore_trickfind = 99
				REMOVE_BLIP punterB
                
				whore_lookat = 0
				whore_entervehicle = 0
				whore_leavevehicle = 0
				both_whores_spawned = 1
		 
			ENDIF
		ENDIF

	 ENDIF
	ENDIF
ENDIF


IF whore_trickfind = 15	
   	IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR punter[pimp_trick] scplayer
		PRINT_NOW PIMP_52 8000 1
	   GOTO mission_pimp_failed
	ENDIF
	
	REMOVE_BLIP punterB
		
		whore_lookat = 0
		whore_entervehicle = 0
		whore_leavevehicle = 0

	  	// Once both hoors are delivered, we put blip back to previous hoor who is in
		// some sort of peril. When she's saved and dropped off we point back to the other one again. 
		// Need a pointer to the punter though. Can just copy his index and refer back to him.

		GOSUB next_whore_decider
		whore_trickfind = 20

ENDIF


IF whore_trickfind = 20
   	IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR punter[pimp_trick] scplayer
	 	PRINT_NOW PIMP_52 8000 1
	   	GOTO mission_pimp_failed
	ENDIF

	whore_trickfind = 25
ENDIF // whore_trickfind = 20 condition check


IF whore_trickfind = 25		 //final scenario

		GENERATE_RANDOM_INT_IN_RANGE 0 4 punter_scenario
	   //	write_debug_with_int ps punter_scenario
		IF whore_tricknumber < 2
			punter_scenario = 3// proper
		   //	punter_scenario = 0	//test
		ENDIF
		
		IF punter_scenario = 0
		   whore_trickfind = 30
		   PRINT_NOW PIMP_35 6000 1
		   //Help I'm being beaten black and blue by this punk!
		   
		   beat_fix = 1
		   timera = 0	

		   IF wIndex = 0
		   IF NOT IS_CHAR_DEAD punter[punter_holder0]
				REMOVE_BLIP punkB
				ADD_BLIP_FOR_CHAR punter[punter_holder0] punkB
				SET_CHAR_IS_TARGET_PRIORITY punter[punter_holder0] TRUE

				punterhurt[punter_holder0] = 0 // Don't need to check if the player has damaged this guy anymore

				CHANGE_BLIP_COLOUR punkB RED
				CLEAR_ONSCREEN_TIMER pimp_runtimer
				pimp_runtimer = 180000
				DISPLAY_ONSCREEN_TIMER_WITH_STRING pimp_runtimer TIMER_DOWN PIMP_90
				pimp_timerpresent = 2 // girl in danger check

		   ENDIF
		   ENDIF

		   IF wIndex = 1
		   IF NOT IS_CHAR_DEAD punter[punter_holder1]
				REMOVE_BLIP punkB
				ADD_BLIP_FOR_CHAR punter[punter_holder1] punkB
				SET_CHAR_IS_TARGET_PRIORITY punter[punter_holder1] TRUE

				punterhurt[punter_holder1] = 0 // Don't need to check if the player has damaged this guy anymore

				CHANGE_BLIP_COLOUR punkB RED
				CLEAR_ONSCREEN_TIMER pimp_runtimer
				pimp_runtimer = 180000
				DISPLAY_ONSCREEN_TIMER_WITH_STRING pimp_runtimer TIMER_DOWN PIMP_90
				pimp_timerpresent = 2 // girl in danger check

		   ENDIF
		   ENDIF


		ENDIF

		IF punter_scenario = 1
		   whore_trickfind = 40
		   PRINT_NOW PIMP_39 6000 1
		   //This guy's a cheapskate!

		   cheap_fix = 1
		   timera = 0		   
		   
		   IF wIndex = 0
		   IF NOT IS_CHAR_DEAD punter[punter_holder0]
				REMOVE_BLIP punkB

				ADD_BLIP_FOR_CHAR punter[punter_holder0] punkB
				SET_CHAR_IS_TARGET_PRIORITY punter[punter_holder0] TRUE
				punterhurt[punter_holder0] = 0 // Don't need to check if the player has damaged this guy anymore

				SET_CHAR_MONEY punter[punter_holder0] 250
				CHANGE_BLIP_COLOUR punkB RED
				CLEAR_ONSCREEN_TIMER pimp_runtimer
				pimp_runtimer = 180000
				DISPLAY_ONSCREEN_TIMER_WITH_STRING pimp_runtimer TIMER_DOWN PIMP_90
				pimp_timerpresent = 2 // girl in danger check

		   ENDIF
		   ENDIF

		   IF wIndex = 1
		   IF NOT IS_CHAR_DEAD punter[punter_holder1]
				REMOVE_BLIP punkB

				ADD_BLIP_FOR_CHAR punter[punter_holder1] punkB
				SET_CHAR_IS_TARGET_PRIORITY punter[punter_holder1] TRUE

				punterhurt[punter_holder1] = 0 // Don't need to check if the player has damaged this guy anymore

				CHANGE_BLIP_COLOUR punkB RED
				SET_CHAR_MONEY punter[punter_holder1] 250
				CLEAR_ONSCREEN_TIMER pimp_runtimer
				pimp_runtimer = 180000
				DISPLAY_ONSCREEN_TIMER_WITH_STRING pimp_runtimer TIMER_DOWN PIMP_90
				pimp_timerpresent = 2 // girl in danger check


		   ENDIF
		   ENDIF


		ENDIF

	
		IF punter_scenario = 2
		   call_fix = 0
		   whore_trickfind = 50
		   PRINT_NOW PIMP_56 4000 1
		   timera = 0
		   call_fix = 1
		  		   //Good job continue etc
		ENDIF

		IF punter_scenario = 3
		   call_fix = 0
		   whore_trickfind = 50
		   PRINT_NOW PIMP_56 4000 1
		   timera = 0
		   call_fix = 1																											 
		  		   //Good job continue etc
		ENDIF

//		IF punter_scenario = 4
//		   call_fix = 0
//		   whore_trickfind = 50
//		   PRINT_NOW PIMP_56 4000 1
//		   timera = 0
//		   call_fix = 1																											 
//		  		   //Good job continue etc
//		ENDIF

//   		IF punter_scenario = 5
//		   call_fix = 0
//		   whore_trickfind = 50
//		   PRINT_NOW PIMP_56 4000 1
//		   timera = 0
//		   call_fix = 1																											 
//		  		   //Good job continue etc
//		ENDIF

ENDIF // whore_trickfind = 25 condition check


IF whore_trickfind = 30	//punter_scenario = 0

	//pimp_backonbikecheck = 99 // don't have time limit on player.


	IF cheap_fix =  1
		IF timera > 4000
			PRINT_NOW PIMP_37 6000 1
			cheap_fix = 99
		ENDIF
	ENDIF

	IF beat_fix =  1
		IF timera > 4000
			PRINT_NOW PIMP_36 6000 1
			beat_fix = 99
		ENDIF
	ENDIF


	
	IF NOT IS_CAR_DEAD pimp_car
	IF pimp_blipswap = 0

		IF NOT IS_CHAR_IN_CAR scplayer pimp_car
			ADD_BLIP_FOR_CAR pimp_car pimp_carB
			SET_BLIP_AS_FRIENDLY pimp_carB TRUE				
			REMOVE_BLIP punkB

		 						
			pimp_blipswap = 1												  
		ENDIF
	

	ENDIF // bcou_blipswap = 0 condition check

	IF pimp_blipswap = 1
																					   
		IF IS_CHAR_IN_CAR scplayer pimp_car
			REMOVE_BLIP pimp_carB
					  	
		  	
										
		   IF wIndex = 0
		   IF NOT IS_CHAR_DEAD punter[punter_holder0]
				REMOVE_BLIP punkB

				ADD_BLIP_FOR_CHAR punter[punter_holder0] punkB
				CHANGE_BLIP_COLOUR punkB RED
		   ENDIF
		   ENDIF

		   IF wIndex = 1
		   IF NOT IS_CHAR_DEAD punter[punter_holder1]
				REMOVE_BLIP punkB

				ADD_BLIP_FOR_CHAR punter[punter_holder1] punkB
				CHANGE_BLIP_COLOUR punkB RED
		   ENDIF
		   ENDIF

		    PRINT_NOW PIMP_55 6000 1
			pimp_blipswap = 0

		ENDIF
																												  
	ENDIF
	ENDIF




	IF pimp_blipswap = 0
	IF wIndex = 0
	IF NOT IS_CHAR_DEAD pimp_whore[wIndex]
		IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer pimp_whore[wIndex] 50.0 50.0 FALSE
			IF NOT IS_CHAR_DEAD punter[punter_holder0]
				
				CLEAR_CHAR_TASKS pimp_whore[wIndex] 
				//TASK_COWER pimp_whore[wIndex]
				TASK_KILL_CHAR_ON_FOOT pimp_whore[wIndex] punter[punter_holder0]


				TASK_KILL_CHAR_ON_FOOT punter[punter_holder0] pimp_whore[wIndex]
				PRINT_NOW PIMP_55 6000 1
				whore_trickfind = 35
			ENDIF   
		ENDIF
	ENDIF
	ENDIF
	IF wIndex = 1
	IF NOT IS_CHAR_DEAD pimp_whore[wIndex]
		IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer pimp_whore[wIndex] 50.0 50.0 FALSE
			IF NOT IS_CHAR_DEAD punter[punter_holder1]
				TASK_KILL_CHAR_ON_FOOT punter[punter_holder1] pimp_whore[wIndex]
					CLEAR_CHAR_TASKS pimp_whore[wIndex] 
				TASK_KILL_CHAR_ON_FOOT pimp_whore[wIndex] punter[punter_holder1]
				PRINT_NOW PIMP_55 6000 1
				whore_trickfind = 35
			ENDIF   
		ENDIF
	ENDIF
	ENDIF
	ENDIF

	IF wIndex = 0
	IF IS_CHAR_DEAD punter[punter_holder0]
		REMOVE_BLIP punkB
		PRINT_NOW PIMP_38 4000 1

		whore_trickfind = 50
	ENDIF
	ENDIF

	IF wIndex = 1
	IF IS_CHAR_DEAD punter[punter_holder1]
	   	REMOVE_BLIP punkB
		PRINT_NOW PIMP_38 4000 1

	  	whore_trickfind = 50
	ENDIF
	ENDIF


ENDIF

IF whore_trickfind = 35

	pimp_backonbikecheck = 99 // don't have time limit on player.

	IF cheap_fix =  1
		IF timera > 4000
			PRINT_NOW PIMP_37 6000 1
			cheap_fix = 99
		ENDIF
	ENDIF

	IF beat_fix =  1
		IF timera > 4000
			PRINT_NOW PIMP_36 6000 1
			beat_fix = 99
		ENDIF
	ENDIF


	IF NOT IS_CAR_DEAD pimp_car
	IF pimp_blipswap = 0

		IF NOT IS_CHAR_IN_CAR scplayer pimp_car
			ADD_BLIP_FOR_CAR pimp_car pimp_carB
			SET_BLIP_AS_FRIENDLY pimp_carB TRUE				
		   //	REMOVE_BLIP punkB

		 						
			pimp_blipswap = 1
			
															  
		ENDIF
	

	ENDIF // bcou_blipswap = 0 condition check

	IF pimp_blipswap = 1
																					   
		IF IS_CHAR_IN_CAR scplayer pimp_car
			REMOVE_BLIP pimp_carB
					  	
		  	
										
		   IF wIndex = 0
		   IF NOT IS_CHAR_DEAD punter[punter_holder0]
				REMOVE_BLIP punkB

			   	ADD_BLIP_FOR_CHAR punter[punter_holder0] punkB
				CHANGE_BLIP_COLOUR punkB RED
		   ENDIF
		   ENDIF

		   IF wIndex = 1
		   IF NOT IS_CHAR_DEAD punter[punter_holder1]
				REMOVE_BLIP punkB

			   	ADD_BLIP_FOR_CHAR punter[punter_holder1] punkB
				CHANGE_BLIP_COLOUR punkB RED
		   ENDIF
		   ENDIF
		   

		    PRINT_NOW PIMP_55 6000 1
			pimp_blipswap = 0

		ENDIF
																												  
	ENDIF
	ENDIF


IF wIndex = 0
	IF IS_CHAR_DEAD punter[punter_holder0]
		PRINT_NOW PIMP_38 4000 1
		REMOVE_BLIP punkB
		whore_trickfind = 50
	ENDIF
	ENDIF
	IF wIndex = 1
	IF IS_CHAR_DEAD punter[punter_holder1]
		PRINT_NOW PIMP_38 4000 1
		REMOVE_BLIP punkB
		whore_trickfind = 50
	ENDIF
	ENDIF
  
ENDIF


IF whore_trickfind = 40 // punter_scenario = 1
	
	//pimp_backonbikecheck = 99 // don't have time limit on player.

	IF cheap_fix =  1
		IF timera > 4000
			PRINT_NOW PIMP_37 6000 1
			cheap_fix = 99
		ENDIF
	ENDIF

	IF beat_fix =  1
		IF timera > 4000
			PRINT_NOW PIMP_36 6000 1
			beat_fix = 99
		ENDIF
	ENDIF

	IF NOT IS_CAR_DEAD pimp_car
	IF pimp_blipswap = 0

		IF NOT IS_CHAR_IN_CAR scplayer pimp_car
			ADD_BLIP_FOR_CAR pimp_car pimp_carB
			SET_BLIP_AS_FRIENDLY pimp_carB TRUE				
			REMOVE_BLIP punkB

		 						
			pimp_blipswap = 1												  
		ENDIF
	

	ENDIF // bcou_blipswap = 0 condition check

	IF pimp_blipswap = 1
																					   
		IF IS_CHAR_IN_CAR scplayer pimp_car
			REMOVE_BLIP pimp_carB
					  	
		  	
										
		   IF wIndex = 0
		   IF NOT IS_CHAR_DEAD punter[punter_holder0]
				REMOVE_BLIP punkB

				ADD_BLIP_FOR_CHAR punter[punter_holder0] punkB
				CHANGE_BLIP_COLOUR punkB RED
		   ENDIF
		   ENDIF

		   IF wIndex = 1
		   IF NOT IS_CHAR_DEAD punter[punter_holder1]
				REMOVE_BLIP punkB

				ADD_BLIP_FOR_CHAR punter[punter_holder1] punkB
				CHANGE_BLIP_COLOUR punkB RED
		   ENDIF
		   ENDIF

		    PRINT_NOW PIMP_55 6000 1
			pimp_blipswap = 0

		ENDIF
																												  
	ENDIF
	ENDIF

	IF pimp_blipswap = 0
	IF wIndex = 0
	IF NOT IS_CHAR_DEAD pimp_whore[wIndex]
		IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer pimp_whore[wIndex] 20.0 20.0 FALSE
			IF NOT IS_CHAR_DEAD punter[punter_holder0]
				TASK_SMART_FLEE_CHAR punter[punter_holder0] scplayer 100.0 -1
				PRINT_NOW PIMP_37 6000 1
				whore_trickfind = 45
			ENDIF   
		ENDIF
	ENDIF
	ENDIF
	IF wIndex = 1
	IF NOT IS_CHAR_DEAD pimp_whore[wIndex]
		IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer pimp_whore[wIndex] 20.0 20.0 FALSE
			IF NOT IS_CHAR_DEAD punter[punter_holder1]
				TASK_SMART_FLEE_CHAR punter[punter_holder1] scplayer 100.0 -1
				PRINT_NOW PIMP_37 6000 1
				whore_trickfind = 45
			ENDIF   
		ENDIF
	ENDIF
	ENDIF
	ENDIF

   	IF wIndex = 0
	IF IS_CHAR_DEAD punter[punter_holder0]
		REMOVE_BLIP punkB
		PRINT_NOW PIMP_38 4000 1

		whore_trickfind = 50
	ENDIF
	ENDIF

	IF wIndex = 1
	IF IS_CHAR_DEAD punter[punter_holder1]
	   	REMOVE_BLIP punkB
		PRINT_NOW PIMP_38 4000 1

	  	whore_trickfind = 50
	ENDIF
	ENDIF
  







ENDIF

IF whore_trickfind = 45

	pimp_backonbikecheck = 99 // don't have time limit on player.

	IF cheap_fix =  1
		IF timera > 4000
			PRINT_NOW PIMP_37 6000 1
			cheap_fix = 99
		ENDIF
	ENDIF

	IF beat_fix =  1
		IF timera > 4000
			PRINT_NOW PIMP_36 6000 1
			beat_fix = 99
		ENDIF
	ENDIF

	IF NOT IS_CAR_DEAD pimp_car
	IF pimp_blipswap = 0

		IF NOT IS_CHAR_IN_CAR scplayer pimp_car
			ADD_BLIP_FOR_CAR pimp_car pimp_carB
			SET_BLIP_AS_FRIENDLY pimp_carB TRUE				
		   //	REMOVE_BLIP punkB

		 						
			pimp_blipswap = 1												  
		ENDIF
	

	ENDIF // bcou_blipswap = 0 condition check

	IF pimp_blipswap = 1
																					   
		IF IS_CHAR_IN_CAR scplayer pimp_car
			REMOVE_BLIP pimp_carB
					  	
		  	
		   								
		   IF wIndex = 0
		   IF NOT IS_CHAR_DEAD punter[punter_holder0]
				REMOVE_BLIP punkB

				ADD_BLIP_FOR_CHAR punter[punter_holder0] punkB
				CHANGE_BLIP_COLOUR punkB RED
		   ENDIF
		   ENDIF

		   IF wIndex = 1
		   IF NOT IS_CHAR_DEAD punter[punter_holder1]
				REMOVE_BLIP punkB

				ADD_BLIP_FOR_CHAR punter[punter_holder1] punkB
				CHANGE_BLIP_COLOUR punkB RED
		   ENDIF
		   ENDIF
			

		    PRINT_NOW PIMP_55 6000 1
			pimp_blipswap = 0

		ENDIF
																												  
	ENDIF
	ENDIF

	IF wIndex = 0
		IF IS_CHAR_DEAD punter[punter_holder0]
			PRINT_NOW PIMP_38 4000 1
			REMOVE_BLIP punkB
			WAIT 2000
			whore_trickfind = 50
		ENDIF
	ENDIF
	
	IF wIndex = 1
		IF IS_CHAR_DEAD punter[punter_holder1]
			PRINT_NOW PIMP_38 4000 1
			REMOVE_BLIP punkB
			WAIT 2000
			whore_trickfind = 50
		ENDIF
	ENDIF

ENDIF
 	
IF whore_trickfind = 50

	pimp_timerpresent = 0
	CLEAR_ONSCREEN_TIMER pimp_runtimer
	pimp_runtimer = 180000

	pimp_backonbikecheck = 0 // want to check again
   
   	IF NOT IS_CHAR_DEAD pimp_whore[wIndex] 
		CLEAR_PRINTS
		CLEAR_CHAR_TASKS pimp_whore[wIndex]
		REMOVE_BLIP pimp_whoreB
		ADD_BLIP_FOR_CHAR pimp_whore[wIndex] pimp_whoreB
		SET_BLIP_AS_FRIENDLY pimp_whoreB TRUE
					
		whore_trickfind = 25

		IF wIndex = 0
			IF NOT IS_CHAR_DEAD punter[punter_holder0]
				MARK_CHAR_AS_NO_LONGER_NEEDED punter[punter_holder0]
				punterhurt[punter_holder0] = 0 // Don't need to check if the player has damaged this guy anymore
			ENDIF
		ENDIF
			
		IF wIndex = 1
			IF NOT IS_CHAR_DEAD punter[punter_holder1]
				MARK_CHAR_AS_NO_LONGER_NEEDED punter[punter_holder1]
				punterhurt[punter_holder1] = 0 // Don't need to check if the player has damaged this guy anymore
			ENDIF
		ENDIF
	ENDIF

   whore_trickfind = 99
   whore_spawned = 2
   whore_tricknumber++
   GET_INT_STAT PIMPING_LEVEL old_pimplevel

   IF NOT whore_tricknumber < old_pimplevel
	  SET_INT_STAT PIMPING_LEVEL whore_tricknumber
   ENDIF

ENDIF // whore_trickfind = 50 condition check


//collision management

IF NOT IS_CHAR_DEAD punter[pimp_trick]
	
	IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer punter[pimp_trick] 50.0 50.0 60.0 FALSE	// z was 50.0
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION punter[pimp_trick] FALSE
	ELSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION punter[pimp_trick] TRUE
	ENDIF

ENDIF


IF NOT IS_CHAR_DEAD punter[punter_holder0]
	
	IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer punter[punter_holder0] 50.0 50.0 60.0 FALSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION punter[punter_holder0] FALSE
	ELSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION punter[punter_holder0] TRUE
	ENDIF

ENDIF

IF NOT IS_CHAR_DEAD punter[punter_holder1]
	
	IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer punter[punter_holder1] 50.0 50.0 60.0 FALSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION punter[punter_holder1] FALSE
	ELSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION punter[punter_holder1] TRUE
	ENDIF

ENDIF

IF NOT IS_CHAR_DEAD pimp_whore[0]
	
	IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer pimp_whore[0] 50.0 50.0 60.0 FALSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION pimp_whore[0] FALSE
		//write_debug ufw
	ELSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION pimp_whore[0] TRUE
		//write_debug fw
	ENDIF

ENDIF

IF NOT IS_CHAR_DEAD pimp_whore[1]
	
	IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer pimp_whore[1] 50.0 50.0 60.0 FALSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION pimp_whore[1] FALSE
		//write_debug ufw1
	ELSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION pimp_whore[1] TRUE
		//write_debug fw1
	ENDIF

ENDIF

GOTO pimp_main_mission_loop


whore_suitable:
	IF NOT pimp_whore[wIndex] = -1
		GET_PED_TYPE pimp_whore[wIndex] pimp_isawhore
		IF NOT IS_CHAR_MALE pimp_whore[wIndex]
			pimp_patch_decided = 99
			whore_spawned = 1
		ENDIF
	ENDIF
RETURN


next_whore_decider:
	IF wIndex = 0
		wIndex = 1 
		//!!WRITE_DEBUG_WITH_INT whore_to_pick_up wIndex
		RETURN
	ENDIF

	IF wIndex = 1
		wIndex = 0 
		//!!WRITE_DEBUG_WITH_INT whore_to_pick_up wIndex
		RETURN
	ENDIF

RETURN



whore_scenario:
RETURN

pimp_patch_decider:


IF pimp_city = LEVEL_LOSANGELES

	IF IS_CHAR_IN_ZONE scplayer IWD	// Idlewood
	OR IS_CHAR_IN_ZONE scplayer GAN	// Ganton
	OR IS_CHAR_IN_ZONE scplayer ELCO	// El Corona
	OR IS_CHAR_IN_ZONE scplayer LMEX	// Little Mexico
		pimp_patch = 1 // South Los Santos A
		//WRITE_DEBUG_WITH_INT patch pimp_patch

	ENDIF	

	IF IS_CHAR_IN_ZONE scplayer	LDOC // Docks
	OR IS_CHAR_IN_ZONE scplayer LAIR // Airport
	OR IS_CHAR_IN_ZONE scplayer LIND // Industrial
	OR IS_CHAR_IN_ZONE scplayer BLUF // Verdant Bluffs
		pimp_patch = 2 // South Los Santos B
	   //	WRITE_DEBUG_WITH_INT patch pimp_patch
	ENDIF




	IF IS_CHAR_IN_ZONE scplayer COM	// Civic Centre
	OR IS_CHAR_IN_ZONE scplayer LDT	// Downtown
	OR IS_CHAR_IN_ZONE scplayer GLN	// Glen Park
	OR IS_CHAR_IN_ZONE scplayer LMEX	//Little Mexico
		pimp_patch = 3 // North Los Santos A
		//WRITE_DEBUG_WITH_INT patch pimp_patch

	ENDIF	

	IF IS_CHAR_IN_ZONE scplayer	JEF   // Jefferson
	OR IS_CHAR_IN_ZONE scplayer ELS   // East Los Santos
	OR IS_CHAR_IN_ZONE scplayer LFL  // Los Flores
	OR IS_CHAR_IN_ZONE scplayer EBE   // East Beach
		pimp_patch = 4 // North Los Santos B
	   //	WRITE_DEBUG_WITH_INT patch pimp_patch

	ENDIF




	IF IS_CHAR_IN_ZONE scplayer CONF	// Conference Centre
	OR IS_CHAR_IN_ZONE scplayer VERO 	// Verona
	OR IS_CHAR_IN_ZONE scplayer SMB	// Santa Maria Beach
	OR IS_CHAR_IN_ZONE scplayer MAR	// Marina
	OR IS_CHAR_IN_ZONE scplayer MKT // Market
		pimp_patch = 5 // West Los Santos A
	   //	WRITE_DEBUG_WITH_INT patch pimp_patch

	ENDIF	

	IF IS_CHAR_IN_ZONE scplayer	SMB   // Santa Maria Beach
	OR IS_CHAR_IN_ZONE scplayer PLS   // Playa del Sevilla
	OR IS_CHAR_IN_ZONE scplayer ROD   // Rodeo
	OR IS_CHAR_IN_ZONE scplayer RIH   // Richman Heights
		pimp_patch = 6 // West Los Santos B
	  //	WRITE_DEBUG_WITH_INT patch pimp_patch

	ENDIF

	IF IS_CHAR_IN_ZONE scplayer VIN	// Vinewood	 ??????????????????????????????
	OR IS_CHAR_IN_ZONE scplayer	MUL	// Mulholland
	OR IS_CHAR_IN_ZONE scplayer	SUN	// Sunrise	??????????????????????????????
		pimp_patch = 7 // West Los Santos  C
	   //	WRITE_DEBUG_WITH_INT patch pimp_patch

	ENDIF
	
	IF pimp_patch < 1 
		pimp_patch = 1
	ENDIF

	IF pimp_patch > 7
		pimp_patch = 1
	ENDIF

	//write_debug_with_int patch pimp_patch
   //pimp_patch_decided = 1

ENDIF // pimp_city = LEVEL_LOSANGELES


IF pimp_city = LEVEL_SANFRANCISCO

	IF IS_CHAR_IN_ZONE scplayer BAYV // Pallisades
	OR IS_CHAR_IN_ZONE scplayer PARA // Paradiso
	OR IS_CHAR_IN_ZONE scplayer CIVI // Santa Flora
	OR IS_CHAR_IN_ZONE scplayer CITYS //City Hall
		pimp_patch = 1 // North West SF
		//WRITE_DEBUG_WITH_INT patch pimp_patch

	ENDIF	

	IF IS_CHAR_IN_ZONE scplayer	CALT // Calton Heights
	OR IS_CHAR_IN_ZONE scplayer JUNIHO // Juniper Hollow
	OR IS_CHAR_IN_ZONE scplayer JUNIHI // Juniper Hill
	OR IS_CHAR_IN_ZONE scplayer ESPN // Esplanade North
		pimp_patch = 2 // North Central SF
	   //	WRITE_DEBUG_WITH_INT patch pimp_patch
	ENDIF
	
	IF IS_CHAR_IN_ZONE scplayer	SUNMA // Bayside Marina
	OR IS_CHAR_IN_ZONE scplayer SUNNN // Bayside
	OR IS_CHAR_IN_ZONE scplayer GANTB // Gant Bridge
	OR IS_CHAR_IN_ZONE scplayer BATTP // Battery Point
		pimp_patch = 3 // Extreme North SF
	   //	WRITE_DEBUG_WITH_INT patch pimp_patch
	ENDIF


	IF IS_CHAR_IN_ZONE scplayer	CHINA // Chinatown
	OR IS_CHAR_IN_ZONE scplayer SFDWT // SF Downtown
	OR IS_CHAR_IN_ZONE scplayer FINA // Financial
	OR IS_CHAR_IN_ZONE scplayer ESPE // Esplanade East
		pimp_patch = 4
	   //	WRITE_DEBUG sfdwtOK
	ENDIF


	IF IS_CHAR_IN_ZONE scplayer WESTP // Westpark
	OR IS_CHAR_IN_ZONE scplayer HASH // Hashbury
	OR IS_CHAR_IN_ZONE scplayer OCEAF // Ocean Flats
	OR IS_CHAR_IN_ZONE scplayer CUNTC // Avispa Country Club	 
	
		pimp_patch = 5
	ENDIF


	IF IS_CHAR_IN_ZONE scplayer THEA // Kings
	OR IS_CHAR_IN_ZONE scplayer GARC // Garcia
	OR IS_CHAR_IN_ZONE scplayer DOH // Doherty
	OR IS_CHAR_IN_ZONE scplayer EASB // Easter Basin

		pimp_patch = 6
	ENDIF


	IF IS_CHAR_IN_ZONE scplayer HILLP // Missionary Hill
	OR IS_CHAR_IN_ZONE scplayer SILLY // Foster Valley
	OR IS_CHAR_IN_ZONE scplayer SFAIR // Easter Bay Airport

		pimp_patch = 7
	ENDIF

	IF pimp_patch < 1 
		pimp_patch = 1
	ENDIF

	IF pimp_patch > 7
		pimp_patch = 1
	ENDIF


	// remember failsafe shit. See LA above >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


ENDIF // pimp_city = LEVEL_SANFRANCISCO


IF pimp_city = LEVEL_LASVEGAS

	IF IS_CHAR_IN_ZONE scplayer BFC // Blackfield Chapel
	OR IS_CHAR_IN_ZONE scplayer GGC // Greenglass College
	OR IS_CHAR_IN_ZONE scplayer VAIR // Las Venturas Airport
	OR IS_CHAR_IN_ZONE scplayer LDM //Last Dime Motel
		pimp_patch = 1 // Pink Swan , 4 Dragons etc.
		//WRITE_DEBUG_WITH_INT patch pimp_patch

	ENDIF

	IF IS_CHAR_IN_ZONE scplayer	PINK // The Pink Swan
	OR IS_CHAR_IN_ZONE scplayer	DRAG // The Four Dragons Casino
	OR IS_CHAR_IN_ZONE scplayer CAM // The Camel's Toe
	OR IS_CHAR_IN_ZONE scplayer	LOT // Come-A-Lot
		pimp_patch = 2
	ENDIF

	IF IS_CHAR_IN_ZONE scplayer	PIRA // The Pirate In Mens Pants
	OR IS_CHAR_IN_ZONE scplayer	ROY // The Royal Casino
	OR IS_CHAR_IN_ZONE scplayer CALI // Caligula's Palace
	OR IS_CHAR_IN_ZONE scplayer	HIGH // The High Roller
		pimp_patch = 3
	ENDIF

	IF IS_CHAR_IN_ZONE scplayer	PILG // Pilgrim's Creek
	OR IS_CHAR_IN_ZONE scplayer	STAR // Starfish Casino
	OR IS_CHAR_IN_ZONE scplayer STRIP // The Strip
	OR IS_CHAR_IN_ZONE scplayer	VISA // The Visage
		pimp_patch = 4
	ENDIF

	IF IS_CHAR_IN_ZONE scplayer	ISLE // Emerald Isle
	OR IS_CHAR_IN_ZONE scplayer	ROCE // Roca Escalante
	OR IS_CHAR_IN_ZONE scplayer OVS //  Old Venturas Strip
	OR IS_CHAR_IN_ZONE scplayer RING // The Ring Master
		pimp_patch = 5
	ENDIF




	
	IF pimp_patch < 1 
		pimp_patch = 1
	ENDIF

	IF pimp_patch > 5
		pimp_patch = 1
	ENDIF
ENDIF

RETURN


pimp_fail_button_pressed:
	
	GET_CONTROLLER_MODE controlmode

	IF NOT controlmode = 3
		WHILE IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
			WAIT 0
			IF NOT IS_PLAYER_PLAYING player1
				GOTO mission_pimp_failed
			ENDIF
		ENDWHILE
	ELSE
		WHILE IS_BUTTON_PRESSED PAD1 SQUARE
			WAIT 0
			IF NOT IS_PLAYER_PLAYING player1
				GOTO mission_pimp_failed
			ENDIF
		ENDWHILE
	ENDIF
	GOTO mission_pimp_failed


 // **************************************** Mission pimp failed ***********************

mission_pimp_failed:
//PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"

IF whore_tricknumber = 0
	PRINT_BIG PIMP_40 5000 1
ENDIF

IF whore_tricknumber = 1
	PRINT_BIG PIMP_41 5000 1
ENDIF

IF whore_tricknumber = 2
	PRINT_BIG PIMP_42 5000 1
ENDIF

IF whore_tricknumber = 3
	PRINT_BIG PIMP_43 5000 1
ENDIF

IF whore_tricknumber = 4
	PRINT_BIG PIMP_44 5000 1
ENDIF

IF whore_tricknumber = 5
	PRINT_BIG PIMP_45 5000 1
ENDIF

IF whore_tricknumber = 6
	PRINT_BIG PIMP_46 5000 1
ENDIF

IF whore_tricknumber = 7
	PRINT_BIG PIMP_47 5000 1
ENDIF

IF whore_tricknumber = 8
	PRINT_BIG PIMP_48 5000 1
ENDIF

IF whore_tricknumber = 9
	PRINT_BIG PIMP_49 5000 1
ENDIF

RETURN

   

// **************************************** mission pimp passed ************************

mission_pimp_passed:

//flag_heist_mission_counter ++ //Used to terminate this mission loop in the main script. These varibles will be set up in the main.sc
//REGISTER_MISSION_PASSED ( HOOD_3 ) //Used in the stats 
PRINT_WITH_NUMBER_BIG ( PIMP_50 ) 1000 5000 1 //"Mission Passed!"
PLAY_MISSION_PASSED_TUNE 2
ADD_SCORE player1 1000
CLEAR_WANTED_LEVEL player1
IF pimp_passed_once = 0 
	REGISTER_ODDJOB_MISSION_PASSED
	PLAYER_MADE_PROGRESS 1
	ACTIVATE_PIMP_CHEAT TRUE

	pimp_passed_once = 1
	   //	PRINT_HELP SEXGOD
ENDIF

RETURN
		


// ********************************** mission cleanup ***********************************

mission_cleanup_pimp:

CLEAR_MISSION_AUDIO 4

CLEAR_ONSCREEN_TIMER pimp_runtimer

//MARK_CHAR_AS_NO_LONGER_NEEDED pimp_whore[wIndex]
//MARK_MODEL_AS_NO_LONGER_NEEDED OCEANIC
//MARK_MODEL_AS_NO_LONGER_NEEDED MOONBEAM
MARK_MODEL_AS_NO_LONGER_NEEDED wmymech
//MARK_MODEL_AS_NO_LONGER_NEEDED WFYCRP
MARK_MODEL_AS_NO_LONGER_NEEDED BMYST
MARK_MODEL_AS_NO_LONGER_NEEDED wmybu
MARK_MODEL_AS_NO_LONGER_NEEDED bmybu
MARK_MODEL_AS_NO_LONGER_NEEDED wmygol1
MARK_MODEL_AS_NO_LONGER_NEEDED wmydrug

MARK_MODEL_AS_NO_LONGER_NEEDED DWMOLC2
MARK_MODEL_AS_NO_LONGER_NEEDED DWMYLC1

MARK_MODEL_AS_NO_LONGER_NEEDED DWMOLC1


//Ho's

MARK_MODEL_AS_NO_LONGER_NEEDED WFYPRO
MARK_MODEL_AS_NO_LONGER_NEEDED SWFOPRO

MARK_MODEL_AS_NO_LONGER_NEEDED BFYPRO
MARK_MODEL_AS_NO_LONGER_NEEDED HFYPRO

MARK_MODEL_AS_NO_LONGER_NEEDED VWFYPRO
MARK_MODEL_AS_NO_LONGER_NEEDED VBFYPRO 

//

MARK_MODEL_AS_NO_LONGER_NEEDED wmysgrd


MARK_MODEL_AS_NO_LONGER_NEEDED OMOST
MARK_MODEL_AS_NO_LONGER_NEEDED wmymech
MARK_MODEL_AS_NO_LONGER_NEEDED wmoprea
MARK_MODEL_AS_NO_LONGER_NEEDED wmybmx

MARK_MODEL_AS_NO_LONGER_NEEDED swmyhp1
MARK_MODEL_AS_NO_LONGER_NEEDED hmyst
MARK_MODEL_AS_NO_LONGER_NEEDED bmyboun
MARK_MODEL_AS_NO_LONGER_NEEDED bmydj

IF NOT IS_CHAR_DEAD scplayer
SET_CHAR_CANT_BE_DRAGGED_OUT scplayer FALSE
ENDIF

REMOVE_BLIP pimp_whoreB
REMOVE_BLIP punterB
REMOVE_BLIP punkB
REMOVE_BLIP pimp_carB

IF pimp_window_showing = 1
	DELETE_MENU pimp_window
ENDIF

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_GANG_FLAT PEDTYPE_PLAYER1
GET_GAME_TIMER timer_mobile_start //Used to reset the mobile phone timer so it doesn't ring immediately after the mission
flag_player_on_pimp_mission = 0
flag_player_on_mission = 0

MISSION_HAS_FINISHED
//UNLOAD_SPECIAL_CHARACTER 1
RETURN
}


/*

[PIMP_1:PIMP]
~s~You've got ~1~ seconds to get back in the ~b~car~s~ or this pimping session is over!

[PIMP_2:PIMP]
~s~There's a ~b~girl~s~ nearby. Drive to her location and wait for her to get in.

[PIMP_3:PIMP]
~w~Girl: Nice wheels! Keep me safe and you'll get a percentage!

[PIMP_6:PIMP]
~w~Girl: Ok! Here's my customer! Let me out and I'll call you when I'm done!

[PIMP_7:PIMP]
~w~Girl: Here's your cut! 

[PIMP_10:PIMP]
~s~Head to the ~y~Docks~s~ to find a potential trick for your girl.

[PIMP_11:PIMP]
~s~Get to ~y~Los Santos Airport~s~ to find a customer.

[PIMP_12:PIMP]
~s~Go to ~y~Willowfield~s~ now.

[PIMP_13:PIMP]
~s~Head up to the ~y~Bluffs~s~ region.

[PIMP_14:PIMP]
~s~Drive into the ~y~Commerce~s~ district.

[PIMP_15:PIMP] 
~s~Take your girl ~y~Downtown~s~ now.

[PIMP_16:PIMP]
~y~Glen Park~s~ is your next destination.

[PIMP_17:PIMP]
~s~Get to ~y~Little Mexico~s~ for another trick.

[PIMP_18:PIMP]
~s~Make for the church in ~y~Jefferson~s~ now.

[PIMP_19:PIMP]
~y~East Los Santos~s~ next.

[PIMP_20:PIMP]
~s~Drive to ~y~Los Flores~s~ to find another customer.

[PIMP_21:PIMP]
~s~Drop your girl off at ~y~East Beach~s~ this time.

[PIMP_22:PIMP]
~s~Head for the ~y~Conference Centre~s~ at the centre of town.

[PIMP_23:PIMP]
~s~Make for ~y~Verona Beach~s~ this time.

[PIMP_24:PIMP]
~y~Santa Maria Beach~s~ is your next destination.

[PIMP_25:PIMP]
~s~Drive to the ~y~Marina~s~ to find another customer for your girl.

[PIMP_26:PIMP]
~s~Head in to the ~y~Market~s~ region now.

[PIMP_27:PIMP]
~s~Central ~y~Rodeo~s~ is your next destination.

[PIMP_28:PIMP]
~s~Make for the ~y~Rodeo~s~ steps this time.

[PIMP_29:PIMP]
~s~Head on up to ~y~Richman~s~ to find another customer for your girl


[PIMP_30:PIMP]
~s~Good! Go and pick up another ~b~girl~s~ to double your potential income.

[PIMP_31:PIMP]
~w~Girl: Treat me right! Do I get a pension plan? 

[PIMP_32:PIMP]
~w~Girl: There's your share big boy!

[PIMP_33:PIMP]
~w~Girl: Here's my man. Stop nearby. I'll call you when I'm ready for my next appointment.

[PIMP_35:PIMP]
~w~phone: Help me! This punk is getting rough! He thinks I've given him crabs!

[PIMP_36:PIMP]
~s~A ~r~customer~s~ is being too rough with your girl! Get him!

[PIMP_37:PIMP]
~s~Chase down that ~r~punk~s~ quickly. Non-payment must be punished!

[PIMP_38:PIMP]
~s~Good work! Drive back and pick up your ~b~girl~s~ to continue.

[PIMP_39:PIMP]
~w~phone: I got a cheapskate who won't pay! I need your persuasive technique!

[PIMP_40:PIMP]
0 out of 10 pimps successful!	   

[PIMP_41:PIMP]
1 out of 10 pimps successful!

[PIMP_42:PIMP]
2 out of 10 pimps successful!

[PIMP_43:PIMP]
3 out of 10 pimps successful!

[PIMP_44:PIMP]
4 out of 10 pimps successful!

[PIMP_45:PIMP]
5 out of 10 pimps successful!

[PIMP_46:PIMP]
6 out of 10 pimps successful!

[PIMP_47:PIMP]
7 out of 10 pimps successful!

[PIMP_48:PIMP]
8 out of 10 pimps successful!

[PIMP_49:PIMP]
9 out of 10 pimps successful!

[PIMP_50:PIMP]
Pimping Complete!

[PIMP_51:PIMP]
~r~Your girl can't service customers in hospital! You're no pimp!

[PIMP_52:PIMP]
~r~Respect your customers! They won't use your girls again if you get tough with them!

[PIMP_53:PIMP]
~s~Get to the customer's ~y~area~s~ now!

[PIMP_54:PIMP]
~s~Pick up your ~b~girl~s~ now!

[PIMP_55:PIMP] 
~s~That ~r~customer~s~ is taking advantage of your services. Destroy him!

[PIMP_56:PIMP]
~w~phone: Hey, I'm finished. Come by and pick me up!

[PIMP_57:PIMP]
~s~Your ~b~girl~s~ has finished with a customer. Go and pick her up.

[PIMP_59:PIMP]
~r~Pimping session over.

[PIMP_60:PIMP]
~s~Excellent pimping! You've kept your girls safe.


[PIMP_70:PIMP]
Pimping

[PIMP_71:PIMP]
Payment:

[PIMP_72:PIMP]
Trick Cut

[PIMP_73:PIMP]
Multiplier

[PIMP_74:PIMP]
Total Cash

[PIMP_75:PIMP]
X ~1~


[PIMP_80:PIMP]
~s~Drive to ~y~Mulholland~s~ to find another customer.

[PIMP_81:PIMP]
~s~Head to the car wash in ~y~Mulholland~s~ next.

[PIMP_82:PIMP]
~s~Get your girl to ~y~Vinewood~s~ this time.

[PIMP_83:PIMP]
~s~Take your girl near the ~y~Temple~s~ cemetery.

[PIMP_84:PIMP]
~s~Head back to ~y~Idlewood~s~ for another trick for your girl.

[PIMP_85:PIMP] 
~s~Drive to ~y~Ganton~s~ this time.

[PIMP_86:PIMP]
~s~Make for ~y~El Corona~s~ to find another customer.

[PIMP_87:PIMP]
~s~Head towards ~y~Little Mexico~s~ to find another customer.

*/