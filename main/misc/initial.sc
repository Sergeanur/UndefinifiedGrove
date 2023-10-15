MISSION_START

SCRIPT_NAME initial
																						 
SET_LA_RIOTS OFF
				  
VAR_INT flag_had_taxi_jump_help
VAR_INT done_ambulance_progress
VAR_INT done_copcar_progress
VAR_INT done_firetruck_progress
VAR_INT done_burglary_progress
VAR_INT done_taxiodd_progress
VAR_INT done_truck_progress
VAR_INT done_quarry_progress
VAR_INT	nascar_best_lap_time nascar_best_time best_nascar_result

VAR_INT player_has_parachute player_landed japcar_mod_garage_open
VAR_FLOAT para_float_Vy para_float_Vz para_flare_Vy para_flare_Vz para_freefall_Vz para_freefall_Vy para_default_Vy
VAR_FLOAT para_Vx para_Vy para_Vz
VAR_INT para_pickup_flag para_control_off para_force_chute_open player_fall_state can_skip_smoking_cutscene

VAR_INT bcesar3_passed_mission
bcesar3_passed_mission = 0

japcar_mod_garage_open = 0

para_float_Vy = 5.0
para_float_Vz = -5.0
para_flare_Vy = 8.5
para_flare_Vz = -1.5
para_freefall_Vz = -30.0
para_freefall_Vy = 32.0
para_default_Vy = 0.0
para_force_chute_open = 0
player_fall_state = 0
player_has_parachute = 0
para_pickup_flag = 1

trigger_final_synd_mission = 0 
trigger_scrash2_mission = 0
trigger_ice_cold_mission = 0
trigger_final_LA2_missions = 0
trigger_final_LA1_missions = 0
funeral_mission_finished = 0
trucking_help_flag = 0
oddjob_help_flag = 0
girlfried_help_flag = 0

trigger_phonecall_failed = 0
flag_bcesar_mission_counter = 0
played_scipted_airscript_cut = 0

VAR_INT	returned_oysters_flag flag_returned_shoehorses flag_returned_snapshots flag_returned_tags

returned_oysters_flag = 0
flag_returned_shoehorses = 0
flag_returned_snapshots	= 0
flag_returned_tags = 0

game_starts_from_scratch = 0
print_first_help = 0
voice_over_at_hub = 0
bike_help = 0
car_help_played = 0
drive_by_help = 0

can_skip_smoking_cutscene = 0

launch_shit_for_debug_build = 0

VAR_INT cat_getaway_dialogue

cat_getaway_dialogue = 0

var_int gate_stay_open
gate_stay_open = 0

VAR_INT tlf_underway // Kev B uses to switch off SF police compound script during Toreno's Last Flight ( syn6 )
tlf_underway = 0

var_int total_roulette_tables
total_roulette_tables = 0


// *****************************************CARMODS******************************************************************
//VAR_INT stored_spray_car initial_spray_setup 
//VAR_INT first_spoiler_mod original_mod1 carmod_index char_not_in_carmod_car	added_one_to_counter
//VAR_INT dpad_state2Y car_mod_counter old_mod_index got_mod1 paint_mod num_of_car_wheels
//VAR_INT new_mod_index[12] what_upgrade[12] check_prints_once car_wheel_class wheel_index  car_mod_location misc_mod
//VAR_INT spoiler_mod wings_mod vent_mod exhaust_mod nitro_mod fbump_mod rbump_mod lights_mod	roof_mod chass_mod fbull_mod rbull_mod
//VAR_INT spoiler[5] 
//VAR_INT car_wings[5] 
//VAR_INT vent[5] 
//VAR_INT exhaust[5] 
//VAR_INT nitro[5]	
//VAR_INT fbump[5] 
//VAR_INT rbump[5] 
//VAR_INT new_lights[5] 
//VAR_INT roof[5] 
//VAR_INT chass[5] 
//VAR_INT fbull[5] 
//VAR_INT rbull[5] 
//VAR_INT wheels[10]
//VAR_INT misc[10]

// *****************************************PROPERTY******************************************************************

CONST_INT PROP_BUY0 0
CONST_INT PROP_BUY1 1
CONST_INT PROP_BUY2 2
CONST_INT PROP_BUY3 3
CONST_INT PROP_BUY4 4
CONST_INT PROP_BUY5 5
CONST_INT PROP_BUY6 6
CONST_INT PROP_BUY7 7
CONST_INT PROP_BUY8 8
CONST_INT PROP_BUY9 9
CONST_INT PROP_BUY10 10
CONST_INT PROP_BUY11 11
CONST_INT PROP_BUY12 12
CONST_INT PROP_BUY13 13
CONST_INT PROP_BUY14 14
CONST_INT PROP_BUY15 15
CONST_INT PROP_BUY16 16
CONST_INT PROP_BUY17 17
CONST_INT PROP_BUY18 18
CONST_INT PROP_BUY19 19
CONST_INT PROP_BUY20 20
CONST_INT PROP_BUY21 21
CONST_INT PROP_BUY22 22
CONST_INT PROP_BUY23 23
CONST_INT PROP_BUY24 24
CONST_INT PROP_BUY25 25
CONST_INT PROP_BUY26 26
CONST_INT PROP_BUY27 27
CONST_INT PROP_BUY28 28
CONST_INT PROP_BUY29 29
CONST_INT PROP_BUY30 30
CONST_INT PROP_BUY31 31

VAR_FLOAT propertyX[32] propertyY[32] propertyZ[32]	
VAR_INT zeros_property_bought prop_save_house_blip[32]
VAR_INT save_houseprice[32] save_housepickup[32] save_house_blip[18]
VAR_INT buying_property_switch

already_bought_house[0] = 0
already_bought_house[1] = 0
already_bought_house[2] = 0
already_bought_house[3] = 0
already_bought_house[4] = 0
already_bought_house[5] = 0
already_bought_house[6] = 0
already_bought_house[7] = 0
already_bought_house[8] = 0
already_bought_house[9] = 0
already_bought_house[10] = 0
already_bought_house[11] = 0
already_bought_house[12] = 0
already_bought_house[13] = 0
already_bought_house[14] = 0
already_bought_house[15] = 0
already_bought_house[16] = 0
already_bought_house[17] = 0
already_bought_house[18] = 0
already_bought_house[19] = 0
already_bought_house[20] = 0
already_bought_house[21] = 0
already_bought_house[22] = 0
already_bought_house[23] = 0
already_bought_house[24] = 0
already_bought_house[25] = 0
already_bought_house[26] = 0
already_bought_house[27] = 0
already_bought_house[28] = 0
already_bought_house[29] = 0
already_bought_house[30] = 0
already_bought_house[31] = 0

prorerty_switch[0] = PROP_BUY0
prorerty_switch[1] = PROP_BUY1
prorerty_switch[2] = PROP_BUY2
prorerty_switch[3] = PROP_BUY3
prorerty_switch[4] = PROP_BUY4
prorerty_switch[5] = PROP_BUY5
prorerty_switch[6] = PROP_BUY6
prorerty_switch[7] = PROP_BUY7
prorerty_switch[8] = PROP_BUY8
prorerty_switch[9] = PROP_BUY9
prorerty_switch[10] = PROP_BUY10
prorerty_switch[11] = PROP_BUY11
prorerty_switch[12] = PROP_BUY12
prorerty_switch[13] = PROP_BUY13
prorerty_switch[14] = PROP_BUY14
prorerty_switch[15] = PROP_BUY15
prorerty_switch[16] = PROP_BUY16
prorerty_switch[17] = PROP_BUY17
prorerty_switch[18] = PROP_BUY18
prorerty_switch[19] = PROP_BUY19
prorerty_switch[20] = PROP_BUY20
prorerty_switch[21] = PROP_BUY21
prorerty_switch[22] = PROP_BUY22
prorerty_switch[23] = PROP_BUY23
prorerty_switch[24] = PROP_BUY24
prorerty_switch[25] = PROP_BUY25
prorerty_switch[26] = PROP_BUY26
prorerty_switch[27] = PROP_BUY27
prorerty_switch[28] = PROP_BUY28
prorerty_switch[29] = PROP_BUY29
prorerty_switch[30] = PROP_BUY30
prorerty_switch[31] = PROP_BUY31

zeros_property_bought = 0

propertyX[0] = -1969.27 //SHOWROM
propertyY[0] = 282.47 
propertyZ[0] = 34.6

propertyX[1] = -2243.62	//ZEROS
propertyY[1] = 133.20 
propertyZ[1] = 34.8

propertyX[2] = 426.4972   //AIRSTRIP
propertyY[2] = 2530.6890   
propertyZ[2] = 16.1


CREATE_LOCKED_PROPERTY_PICKUP propertyX[0] propertyY[0] propertyZ[0] PROP_4 save_housepickup[0]	//SHOWROM
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[0] propertyY[0] propertyZ[0] RADAR_SPRITE_PROPERTY_RED showroom_contact_blip
CHANGE_BLIP_DISPLAY showroom_contact_blip BLIP_ONLY

CREATE_LOCKED_PROPERTY_PICKUP propertyX[1] propertyY[1] propertyZ[1] PROP_4 save_housepickup[1]	//ZEROS
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[1] propertyY[1] propertyZ[1] RADAR_SPRITE_PROPERTY_RED zero_contact_blip
CHANGE_BLIP_DISPLAY zero_contact_blip BLIP_ONLY

CREATE_LOCKED_PROPERTY_PICKUP propertyX[2] propertyY[2] propertyZ[2] PROP_4 save_housepickup[2]	//AIRSTRIP
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[2] propertyY[2] propertyZ[2] RADAR_SPRITE_PROPERTY_RED airstrip_contact_blip
CHANGE_BLIP_DISPLAY airstrip_contact_blip BLIP_ONLY
SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[2] propertyY[2] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE



//BUYABLE PROPERIES************************************************************

//BEACH FRONT MALIBU HOUSE	LA ******************************************************
propertyX[3] = 316.0696 //beach front malibu house	LA
propertyY[3] = -1772.5688 
propertyZ[3] = 4.1893

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[3] propertyY[3] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 
 
save_houseprice[3] = 30000

CREATE_FORSALE_PROPERTY_PICKUP propertyX[3] propertyY[3] propertyZ[3] save_houseprice[3] PROP_3 save_housepickup[3] //beach front malibu house	LA
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[3] propertyY[3] propertyZ[3] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[3]
CHANGE_BLIP_DISPLAY prop_save_house_blip[3] BLIP_ONLY
//SWITCH_ENTRY_EXIT beacsv FALSE
DEACTIVATE_GARAGE beacsv //HIDEOUT_TWO //GARAGE FUCKED


//SHABBYHOUSE IN VEGAS EAST	**********************************************************
propertyX[4] = 2441.0022 //Shabbyhouse in Vegas East
propertyY[4] = 695.1089 
propertyZ[4] = 10.6646

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[4] propertyY[4] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

save_houseprice[4] = 20000

CREATE_LOCKED_PROPERTY_PICKUP propertyX[4] propertyY[4] propertyZ[4] PROP_4 save_housepickup[4] //Shabbyhouse in Vegas East ***********************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[4] propertyY[4] propertyZ[4] save_houseprice[4] PROP_3 save_housepickup[4] //Shabbyhouse in Vegas East
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[4] propertyY[4] propertyZ[4] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[4]
CHANGE_BLIP_DISPLAY prop_save_house_blip[4] BLIP_ONLY
//SWITCH_ENTRY_EXIT svvgmd FALSE
DEACTIVATE_GARAGE vEsvgrg //HIDEOUT_THREE	//GARAGE FUCKED


//FORT CARSON OLD HOUSE SAVEHOUSE NEAR RIVER BADLANDS *********************************
propertyX[5] = -366.1849 //Fort Carson old house savehouse near river BADLANDS
propertyY[5] = 1166.0305
propertyZ[5] = 19.2422

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[5] propertyY[5] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

save_houseprice[5] = 30000

CREATE_LOCKED_PROPERTY_PICKUP propertyX[5] propertyY[5] propertyZ[5] PROP_4 save_housepickup[5] //Fort Carson old house savehous *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[5] propertyY[5] propertyZ[5] save_houseprice[5] PROP_3 save_housepickup[5] //Fort Carson old house savehouse near river BADLANDS
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[5] propertyY[5] propertyZ[5] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[5]
CHANGE_BLIP_DISPLAY prop_save_house_blip[5] BLIP_ONLY
//SWITCH_ENTRY_EXIT svcunt FALSE
DEACTIVATE_GARAGE cn2gar1 //HIDEOUT_FOUR //GARAGE WORKING


//MEDIUM HOUSE IN NICE SUBURB OF VENTURAS **********************************************
propertyX[6] = 1283.8439  //Medium house in nice suburb of Venturas
propertyY[6] = 2528.7029   
propertyZ[6] = 10.3203

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[6] propertyY[6] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

save_houseprice[6] = 50000

CREATE_LOCKED_PROPERTY_PICKUP propertyX[6] propertyY[6] propertyZ[6] PROP_4 save_housepickup[6] //Medium house in nice suburb of Venturas *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[6] propertyY[6] propertyZ[6] save_houseprice[6] PROP_3 save_housepickup[6] //Medium house in nice suburb of Venturas
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[6] propertyY[6] propertyZ[6] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[6]
CHANGE_BLIP_DISPLAY prop_save_house_blip[6] BLIP_ONLY
//SWITCH_ENTRY_EXIT svvgmd FALSE
DEACTIVATE_GARAGE blob69 //HIDEOUT_SEVEN //GARAGE FUCKED


//SHABBY HOUSE IN RUNDOWN RESIDENTIAL AREA ********************************
propertyX[7] = 922.3647 //shabby house in rundown residential area
propertyY[7] = 2011.8984 
propertyZ[7] = 10.7660

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[7] propertyY[7] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 
 
save_houseprice[7] = 30000

CREATE_LOCKED_PROPERTY_PICKUP propertyX[7] propertyY[7] propertyZ[7] PROP_4 save_housepickup[7] //shabby house in rundown residential area *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[7] propertyY[7] propertyZ[7] save_houseprice[7] PROP_3 save_housepickup[7] //shabby house in rundown residential area
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[7] propertyY[7] propertyZ[7] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[7]
CHANGE_BLIP_DISPLAY prop_save_house_blip[7] BLIP_ONLY
//SWITCH_ENTRY_EXIT svvgmd FALSE
DEACTIVATE_GARAGE blob7	//HIDEOUT_EIGHT	//GARAGE FUCKED

 
//SMALL TOWN SUBURBAN HOUSE WITH LAWN AND PORCH	********************************
propertyX[8] = 2236.9280 //Small town suburban house with lawn and porch
propertyY[8] = 162.8057 
propertyZ[8] = 26.8462
 
SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[8] propertyY[8] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

save_houseprice[8] = 35000

CREATE_LOCKED_PROPERTY_PICKUP propertyX[8] propertyY[8] propertyZ[8] PROP_4 save_housepickup[8] //Small town suburban house with lawn and porch *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[8] propertyY[8] propertyZ[8] save_houseprice[8] PROP_3 save_housepickup[8] //Small town suburban house with lawn and porch
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[8] propertyY[8] propertyZ[8] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[8]
CHANGE_BLIP_DISPLAY prop_save_house_blip[8] BLIP_ONLY
//SWITCH_ENTRY_EXIT svcunt FALSE
DEACTIVATE_GARAGE burbdoo //HIDEOUT_NINE //GARAGE WORKING


//SHABBY HOUSE IN RUNDOWN RESIDENTIAL AREA ********************************
propertyX[9] = 1402.3174 //shabby house in rundown residential area
propertyY[9] = 1901.9783 
propertyZ[9] = 10.8449
 
SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[9] propertyY[9] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

save_houseprice[9] = 30000

CREATE_LOCKED_PROPERTY_PICKUP propertyX[9] propertyY[9] propertyZ[9] PROP_4 save_housepickup[9] //shabby house in rundown residential area *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[9] propertyY[9] propertyZ[9] save_houseprice[9] PROP_3 save_housepickup[9] //shabby house in rundown residential area
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[9] propertyY[9] propertyZ[9] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[9]
CHANGE_BLIP_DISPLAY prop_save_house_blip[9] BLIP_ONLY
//SWITCH_ENTRY_EXIT svvgmd FALSE
DEACTIVATE_GARAGE blob6 //HIDEOUT_TEN	//GARAGE FUCKED


//HOUSE IN RUFF HOOD NEXT TO AIRPORT ********************************
propertyX[10] = 1687.9805 //house in ruff hood next to airport
propertyY[10] = -2100.6431 
propertyZ[10] = 13.3343
 
save_houseprice[10] = 10000

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[10] propertyY[10] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

CREATE_FORSALE_PROPERTY_PICKUP propertyX[10] propertyY[10] propertyZ[10] save_houseprice[10] PROP_3 save_housepickup[10]  //house in ruff hood next to airport
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[10] propertyY[10] propertyZ[10] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[10]
CHANGE_BLIP_DISPLAY prop_save_house_blip[10] BLIP_ONLY
DEACTIVATE_GARAGE carlas1 //HIDEOUT_ELEVEN //GARAGE WORKING


//BIG SWANKY SF SAVEHOUSE AT THE TOP OF LOMBARD STREET ********************************
propertyX[11] = -2106.6392 //big swanky SF savehouse at the top of lombard street
propertyY[11] = 900.5537
propertyZ[11] = 76.2032

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[11] propertyY[11] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

save_houseprice[11] = 100000

CREATE_LOCKED_PROPERTY_PICKUP propertyX[11] propertyY[11] propertyZ[11] PROP_4 save_housepickup[11] //big swanky SF savehouse at the top of lombard street *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[11] propertyY[11] propertyZ[11] save_houseprice[11] PROP_3 save_housepickup[11] //big swanky SF savehouse at the top of lombard street
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[11] propertyY[11] propertyZ[11] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[11]
CHANGE_BLIP_DISPLAY prop_save_house_blip[11] BLIP_ONLY
DEACTIVATE_GARAGE CEsafe1 //HIDEOUT_TWELVE //GARAGE WORKING 


//SWANKY PAD IN LA HILLS WITH POOL AND LOTS OF BIG WINDOWS ********************************
propertyX[12] = 1331.1855 //ENTRY EXIT DOESN'T WORK
propertyY[12] = -630.4962
propertyZ[12] =  108.6349  

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[12] propertyY[12] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 
 
save_houseprice[12] = 120000

CREATE_FORSALE_PROPERTY_PICKUP propertyX[12] propertyY[12] propertyZ[12] save_houseprice[12] PROP_3 save_housepickup[12] //Swanky pad in LA hills with pool and lots of big windows
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[12] propertyY[12] propertyZ[12] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[12]
CHANGE_BLIP_DISPLAY prop_save_house_blip[12] BLIP_ONLY
DEACTIVATE_GARAGE sav1sfe //HIDEOUT_THIRTEEN //GARAGE WORKING


//SMALL FLAT NEAR HOSPITAL ********************************
propertyX[13] = -2695.7451 //Needs WARDROBE
propertyY[13] = 818.4718 
propertyZ[13] = 49.4844

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[13] propertyY[13] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

save_houseprice[13] = 20000

CREATE_LOCKED_PROPERTY_PICKUP propertyX[13] propertyY[13] propertyZ[13] PROP_4 save_housepickup[13] //small flat near hospital *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[13] propertyY[13] propertyZ[13] save_houseprice[13] PROP_3 save_housepickup[13] //small flat near hospital
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[13] propertyY[13] propertyZ[13] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[13]
CHANGE_BLIP_DISPLAY prop_save_house_blip[13] BLIP_ONLY
DEACTIVATE_GARAGE sav1sfw //HIDEOUT_FOURTEEN //GARAGE WORKING


//LARGE GARAGE IN ALLEY BEHIND APARTMENTS IN HASHBURY ********************************
propertyX[14] = -2456.9255 //large garage in alley behind apartments in Hashbury
propertyY[14] = -131.3292  
propertyZ[14] = 25.5376

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[14] propertyY[14] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

save_houseprice[14] = 40000

CREATE_LOCKED_PROPERTY_PICKUP propertyX[14] propertyY[14] propertyZ[14] PROP_4 save_housepickup[14] //large garage in alley behind apartments in Hashbury *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[14] propertyY[14] propertyZ[14] save_houseprice[14] PROP_3 save_housepickup[14] //large garage in alley behind apartments in Hashbury
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[14] propertyY[14] propertyZ[14] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[14]
CHANGE_BLIP_DISPLAY prop_save_house_blip[14] BLIP_ONLY
DEACTIVATE_GARAGE svgsfs1 //HIDEOUT_SIXTEEN //GARAGE WORKING

// SAVEHOUSES WITHOUT GARAGE**********************************************************************************************************
//MEDIUM APARTMENT NEAR VENICE CANALS ********************************
propertyX[15] = 892.6662   //Medium apartment near Venice canals
propertyY[15] = -1639.7139    
propertyZ[15] = 14.4567

save_houseprice[15] = 10000
 
SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[15] propertyY[15] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

CREATE_FORSALE_PROPERTY_PICKUP propertyX[15] propertyY[15] propertyZ[15] save_houseprice[15] PROP_3 save_housepickup[15] //Medium apartment near Venice canals  
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[15] propertyY[15] propertyZ[15] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[15]
CHANGE_BLIP_DISPLAY prop_save_house_blip[15] BLIP_ONLY


//PIRATES IN MEN’S PANTS CASINO	********************************
propertyX[16] = 1969.9325 //Pirates In Men’s Pants Casino
propertyY[16] = 1623.2429    
propertyZ[16] = 12.3619

save_houseprice[16] = 6000

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[16] propertyY[16] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

CREATE_LOCKED_PROPERTY_PICKUP propertyX[16] propertyY[16] propertyZ[16] PROP_4 save_housepickup[16] //large garage in alley behind apartments in Hashbury *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[16] propertyY[16] propertyZ[16] save_houseprice[16] PROP_3 save_housepickup[16] //Pirates In Men’s Pants Casino
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[16] propertyY[16] propertyZ[16] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[16]
CHANGE_BLIP_DISPLAY prop_save_house_blip[16] BLIP_ONLY


//THE CAMELS TOE CASINO ********************************
propertyX[17] = 2234.9087  //The Camels Toe Casino
propertyY[17] = 1285.6981     
propertyZ[17] = 10.3203

save_houseprice[17] = 6000

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[17] propertyY[17] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

CREATE_LOCKED_PROPERTY_PICKUP propertyX[17] propertyY[17] propertyZ[17] PROP_4 save_housepickup[17] //large garage in alley behind apartments in Hashbury *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[17] propertyY[17] propertyZ[17] save_houseprice[17] PROP_3 save_housepickup[17] //The Camels Toe Casino
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[17] propertyY[17] propertyZ[17] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[17]
CHANGE_BLIP_DISPLAY prop_save_house_blip[17] BLIP_ONLY


//SMALL CHINATOWN PAD ********************************
propertyX[18] = -2213.8643  //small chinatown pad
propertyY[18] = 723.5587      
propertyZ[18] = 48.9140
 
save_houseprice[18] = 20000

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[18] propertyY[18] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

CREATE_LOCKED_PROPERTY_PICKUP propertyX[18] propertyY[18] propertyZ[18] PROP_4 save_housepickup[18] //small chinatown pad *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[18] propertyY[18] propertyZ[18] save_houseprice[18] PROP_3 save_housepickup[18] //small chinatown pad
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[18] propertyY[18] propertyZ[18] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[18]
CHANGE_BLIP_DISPLAY prop_save_house_blip[18] BLIP_ONLY


//FARM NEAR THE TRUTHS ********************************
propertyX[19] = -1439.0140     
propertyY[19] = -1540.5901         
propertyZ[19] = 101.2579
 
save_houseprice[19] = 100000

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[19] propertyY[19] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

CREATE_LOCKED_PROPERTY_PICKUP propertyX[19] propertyY[19] propertyZ[19] PROP_4 save_housepickup[19] //Farm *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[19] propertyY[19] propertyZ[19] save_houseprice[19] PROP_3 save_housepickup[19] //Farm
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[19] propertyY[19] propertyZ[19] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[19]
CHANGE_BLIP_DISPLAY prop_save_house_blip[19] BLIP_ONLY


//LARGE PAD NEXT TO DRIVING SCHOOL ********************************
propertyX[20] = -2027.8300 //large pad next to driving school
propertyY[20] = -44.0454        
propertyZ[20] = 38.7692

save_houseprice[20] = 20000

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[20] propertyY[20] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

CREATE_LOCKED_PROPERTY_PICKUP propertyX[20] propertyY[20] propertyZ[20] PROP_4 save_housepickup[20] //large pad next to driving school *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[20] propertyY[20] propertyZ[20] save_houseprice[20] PROP_3 save_housepickup[20] //large pad next to driving school   
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[20] propertyY[20] propertyZ[20] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[20]
CHANGE_BLIP_DISPLAY prop_save_house_blip[20] BLIP_ONLY


//VANK HOFF IN THE PARK HOTEL ROOM ********************************
propertyX[21] = -2419.6768 //Vank Hoff In The Park Hotel Room
propertyY[21] = 334.1621         
propertyZ[21] = 34.6796

save_houseprice[21] = 50000

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[21] propertyY[21] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

CREATE_LOCKED_PROPERTY_PICKUP propertyX[21] propertyY[21] propertyZ[21] PROP_4 save_housepickup[21] //Vank Hoff In The Park Hotel Room *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[21] propertyY[21] propertyZ[21] save_houseprice[21] PROP_3 save_housepickup[21] //Vank Hoff In The Park Hotel Room    
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[21] propertyY[21] propertyZ[21] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[21]
CHANGE_BLIP_DISPLAY prop_save_house_blip[21] BLIP_ONLY


//JUST OFF MAIN ANGEL PINE TOWN, BEHIND SAWMILL	********************************
propertyX[22] = -2079.0969 //just off main Angel Pine town, behind Sawmill
propertyY[22] = -2309.8987          
propertyZ[22] = 30.1172

save_houseprice[22] = 20000

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[22] propertyY[22] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

CREATE_LOCKED_PROPERTY_PICKUP propertyX[22] propertyY[22] propertyZ[22] PROP_4 save_housepickup[22] //just off main Angel Pine town, behind Sawmil *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[22] propertyY[22] propertyZ[22] save_houseprice[22] PROP_3 save_housepickup[22] //just off main Angel Pine town, behind Sawmill   
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[22] propertyY[22] propertyZ[22] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[22]
CHANGE_BLIP_DISPLAY prop_save_house_blip[22] BLIP_ONLY


//IN EL QUEBRADOS (MOST NORTHERLY TOWN)	********************************
propertyX[23] = -1534.1703 //In El Quebrados (most northerly town)
propertyY[23] = 2650.3000           
propertyZ[23] = 55.3437

save_houseprice[23] = 20000

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[23] propertyY[23] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

CREATE_LOCKED_PROPERTY_PICKUP propertyX[23] propertyY[23] propertyZ[23] PROP_4 save_housepickup[23] //In El Quebrados (most northerly town) *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[23] propertyY[23] propertyZ[23] save_houseprice[23] PROP_3 save_housepickup[23] //In El Quebrados (most northerly town)
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[23] propertyY[23] propertyZ[23] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[23]
CHANGE_BLIP_DISPLAY prop_save_house_blip[23] BLIP_ONLY


//LAS BARRANCAS (SOUTHERN TOWN)	********************************
propertyX[24] = -1045.7751 //Las Barrancas (southern town)
propertyY[24] = 1552.9763            
propertyZ[24] = 32.7980

save_houseprice[24] = 20000

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[24] propertyY[24] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

CREATE_LOCKED_PROPERTY_PICKUP propertyX[24] propertyY[24] propertyZ[24] PROP_4 save_housepickup[24] //Las Barrancas (southern town) *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[24] propertyY[24] propertyZ[24] save_houseprice[24] PROP_3 save_housepickup[24] //Las Barrancas (southern town)
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[24] propertyY[24] propertyZ[24] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[24]
CHANGE_BLIP_DISPLAY prop_save_house_blip[24] BLIP_ONLY
      
      
//SMALL TOWN SUBURBAN HOUSE WITH LAWN AND PORCH	********************************
propertyX[25] = 793.5623  //Small town suburban house with lawn and porch
propertyY[25] = -514.4116             
propertyZ[25] = 16.3973

save_houseprice[25] = 40000

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[25] propertyY[25] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

CREATE_LOCKED_PROPERTY_PICKUP propertyX[25] propertyY[25] propertyZ[25] PROP_4 save_housepickup[25] //Las Barrancas (southern town) *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[25] propertyY[25] propertyZ[25] save_houseprice[25] PROP_3 save_housepickup[25] //Las Barrancas (southern town)
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[25] propertyY[25] propertyZ[25] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[25]
CHANGE_BLIP_DISPLAY prop_save_house_blip[25] BLIP_ONLY
DEACTIVATE_GARAGE burbdo2 //HIDEOUT_SIX //GARAGE WORKING

   
   
//COMPTON HOUSE	********************************
propertyX[26] = 2103.3459   
propertyY[26] = -1288.3389              
propertyZ[26] = 23.8168

save_houseprice[26] = 10000

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[26] propertyY[26] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

CREATE_FORSALE_PROPERTY_PICKUP propertyX[26] propertyY[26] propertyZ[26] save_houseprice[26] PROP_3 save_housepickup[26] //Compton house
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[26] propertyY[26] propertyZ[26] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[26]
CHANGE_BLIP_DISPLAY prop_save_house_blip[26] BLIP_ONLY

//CASINO
propertyX[27] = 2370.4773    
propertyY[27] = 2165.4744               
propertyZ[27] = 10.3269

save_houseprice[27] = 6000

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[27] propertyY[27] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

CREATE_LOCKED_PROPERTY_PICKUP propertyX[27] propertyY[27] propertyZ[27] PROP_4 save_housepickup[27] //CASINO *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[27] propertyY[27] propertyZ[27] save_houseprice[27] PROP_3 save_housepickup[27] //CASINO
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[27] propertyY[27] propertyZ[27] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[27]
CHANGE_BLIP_DISPLAY prop_save_house_blip[27] BLIP_ONLY        

//CASINO CLOWNS POCKET
propertyX[28] = 2220.6257     
propertyY[28] = 1837.3475                
propertyZ[28] = 10.3203

save_houseprice[28] = 6000

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[28] propertyY[28] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

CREATE_LOCKED_PROPERTY_PICKUP propertyX[28] propertyY[28] propertyZ[28] PROP_4 save_housepickup[28] //CASINO *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[28] propertyY[28] propertyZ[28] save_houseprice[28] PROP_3 save_housepickup[28] //Casino
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[28] propertyY[28] propertyZ[28] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[28]
CHANGE_BLIP_DISPLAY prop_save_house_blip[28] BLIP_ONLY

//VEGAS FLAT
propertyX[29] = 2819.1255      
propertyY[29] = 2149.3718                 
propertyZ[29] = 10.3203

save_houseprice[29] = 10000

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[29] propertyY[29] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

CREATE_LOCKED_PROPERTY_PICKUP propertyX[29] propertyY[29] propertyZ[29] PROP_4 save_housepickup[29] //CASINO *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[29] propertyY[29] propertyZ[29] save_houseprice[29] PROP_3 save_housepickup[29] //Vegas house
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[29] propertyY[29] propertyZ[29] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[29]
CHANGE_BLIP_DISPLAY prop_save_house_blip[29] BLIP_ONLY

// Scummy compton house
propertyX[30] = 2483.0237       
propertyY[30] = -2001.0741                  
propertyZ[30] = 13.0540

save_houseprice[30] = 10000

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[30] propertyY[30] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

CREATE_FORSALE_PROPERTY_PICKUP propertyX[30] propertyY[30] propertyZ[30] save_houseprice[30] PROP_3 save_housepickup[30] //Compton house
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[30] propertyY[30] propertyZ[30] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[30]
CHANGE_BLIP_DISPLAY prop_save_house_blip[30] BLIP_ONLY

				   
// North Country
propertyX[31] = 206.8        
propertyY[31] = -112.1                   
propertyZ[31] = 4.3965

save_houseprice[31] = 10000

SET_CLOSEST_ENTRY_EXIT_FLAG propertyX[31] propertyY[31] 10.0 ENTRYEXITS_FLAG_ENABLED FALSE 

CREATE_LOCKED_PROPERTY_PICKUP propertyX[31] propertyY[31] propertyZ[31] PROP_4 save_housepickup[31] //CASINO *****************
//CREATE_FORSALE_PROPERTY_PICKUP propertyX[31] propertyY[31] propertyZ[31] save_houseprice[31] PROP_3 save_housepickup[31] //North Badlands
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[31] propertyY[31] propertyZ[31] RADAR_SPRITE_PROPERTY_RED prop_save_house_blip[31]
CHANGE_BLIP_DISPLAY prop_save_house_blip[31] BLIP_ONLY



// ***************************************BLIPS******************************************************
// FIRST HALF BLIPS

VAR_INT barber_shop1
VAR_INT	food_shop1
VAR_INT clothes_shop1

//ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD taxibuyX taxibuyY taxibuyZ RADAR_SPRITE_HOUSEG taxibuy_blip
//REMOVE_BLIP	taxibuy_blip


//CJ'S MOTHERS HOUSE********************************
save_pickupX[0] = 2495.53 
save_pickupY[0]	= -1711.88 
save_pickupZ[0]	= 1014.24 

save_playerX[0] = 2495.90 
save_playerY[0] = -1707.45 
save_playerZ[0] = 1013.26
save_playerH[0]	= 0.0

remove_grove_pickup[0] = 0

//MANSION ON THE HILLS********************************
save_pickupX[1] = 1263.05  
save_pickupY[1]	= -773.67  
save_pickupZ[1]	= 1091.39

save_playerX[1] = 1263.76  
save_playerY[1] = -776.59  
save_playerZ[1] = 1090.89 
save_playerH[1]	= 183.23
 
remove_grove_pickup[1] = 0	


//AIRPORT GRAVEYARD ********************************
save_pickupX[2] = 416.9548          
save_pickupY[2]	= 2538.8127         
save_pickupZ[2]	= 9.5077

save_playerX[2] = 418.0759         
save_playerY[2] = 2536.7712          
save_playerZ[2] = 9.0077        
save_playerH[2]	= 269.2893
 
remove_grove_pickup[2] = 0


//SAVE PICKUPS
save_pickupX[3] = 2367.1924 //CHANGED          
save_pickupY[3]	= -1121.5184           
save_pickupZ[3]	= 1050.3824

save_playerX[3] = 2367.0718           
save_playerY[3] = -1123.7151            
save_playerZ[3] = 1049.8750           
save_playerH[3]	= 183.2590
remove_grove_pickup[3] = 0


save_pickupX[4] = 2320.1033 //CHANGED          
save_pickupY[4]	= -1009.1160           
save_pickupZ[4]	= 1049.7178

save_playerX[4] = 2320.1033          
save_playerY[4] = -1013.3750           
save_playerZ[4] = 1049.2109          
save_playerH[4]	= 181.7957
remove_grove_pickup[4] = 0


save_pickupX[5] = 2342.8728  //CHANGED        
save_pickupY[5] = -1063.9392          
save_pickupZ[5] = 1048.5234  

save_playerX[5] = 2339.2539          
save_playerY[5] = -1063.8602           
save_playerZ[5] = 1048.0234          
save_playerH[5] = 90.7733
remove_grove_pickup[5] = 0


save_pickupX[6] = 2186.0137  //CHANGED         
save_pickupY[6] = -1203.8339           
save_pickupZ[6] = 1048.5234

save_playerX[6] = 2189.8184          
save_playerY[6] = -1203.8297           
save_playerZ[6] = 1048.0308          
save_playerH[6] = 268.6874
remove_grove_pickup[6] = 0


save_pickupX[7] = 2313.3687 //CHANGED         
save_pickupY[7] = -1208.7438          
save_pickupZ[7] = 1048.5234

save_playerX[7] = 2310.8530         
save_playerY[7] = -1210.9749          
save_playerZ[7] = 1048.0234         
save_playerH[7] = 130.1776
remove_grove_pickup[7] = 0


save_pickupX[8] = 2249.3970 //CHANGED          
save_pickupY[8] = -1209.4495           
save_pickupZ[8] = 1048.5234

save_playerX[8] = 2252.6997          
save_playerY[8] = -1209.3879           
save_playerZ[8] = 1048.0234          
save_playerH[8] = 270.3929 
remove_grove_pickup[8] = 0


save_pickupX[9] = 2244.5334 //CHANGED         
save_pickupY[9] = -1077.6633           
save_pickupZ[9] = 1048.5234

save_playerX[9] = 2241.0688          
save_playerY[9] = -1077.7365           
save_playerZ[9] = 1048.0309          
save_playerH[9] = 88.0268
remove_grove_pickup[9] = 0


save_pickupX[10] = 2205.3823 //CHANGED        
save_pickupY[10] = -1074.5905          
save_pickupZ[10] = 1049.9766

save_playerX[10] = 2207.8516         
save_playerY[10] = -1075.3242          
save_playerZ[10] = 1049.4766      
save_playerH[10] = 261.7253
remove_grove_pickup[10] = 0


save_pickupX[11] = 2233.0237 //CHANGED         
save_pickupY[11] = -1105.7168          
save_pickupZ[11] = 1050.3901

save_playerX[11] = 2233.2307         
save_playerY[11] = -1107.8516          
save_playerZ[11] = 1049.8901         
save_playerH[11] = 182.2496 
remove_grove_pickup[11] = 0


save_pickupX[12] = 2278.1389 //CHANGED          
save_pickupY[12] = -1139.5831           
save_pickupZ[12] = 1050.3984

save_playerX[12] = 2281.5242          
save_playerY[12] = -1139.3701           
save_playerZ[12] = 1049.8984          
save_playerH[12] = 273.5033 
remove_grove_pickup[12] = 0


//BADLANDS TRAILOR********************************
save_pickupX[13] = -2037.89    
save_pickupY[13] = -2526.96    
save_pickupZ[13] = 30.13

save_playerX[13] = -2039.78   
save_playerY[13] = -2529.10    
save_playerZ[13] = 29.63   
save_playerH[13] = 21.23
 
remove_grove_pickup[13] = 0

//CATS LODGE********************************
save_pickupX[14] = 877.81   
save_pickupY[14] = -26.91   
save_pickupZ[14] = 62.69

save_playerX[14] = 877.04  
save_playerY[14] = -30.16   
save_playerZ[14] = 62.19  
save_playerH[14] = 66.85
 
remove_grove_pickup[14] = 0

//SAN FRAN GARAGE********************************
save_pickupX[15] = -2026.4814        
save_pickupY[15] = 156.8028        
save_pickupZ[15] = 28.5391

save_playerX[15] = -2021.0358       
save_playerY[15] = 156.0674        
save_playerZ[15] = 27.6521      
save_playerH[15] = 272.5815
 
remove_grove_pickup[15] = 0

//TORENOS RANCH ********************************
save_pickupX[16] = -693.3776         
save_pickupY[16] = 957.8240        
save_pickupZ[16] = 11.7829

save_playerX[16] = -700.3663       
save_playerY[16] = 966.0852        
save_playerZ[16] = 11.3575      
save_playerH[16] = 90.0
 
remove_grove_pickup[16] = 0

//TRIAD CASINO ********************************
save_pickupX[17] = 2024.5645            
save_pickupY[17] = 996.4728            
save_pickupZ[17] = 10.3203

save_playerX[17] = 2027.0435           
save_playerY[17] = 996.0585            
save_playerZ[17] = 9.9203           
save_playerH[17] = 275.5160
 
remove_grove_pickup[17] = 0


CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[0] save_pickupY[0] save_pickupZ[0] grove_save_pickup[0]
CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[1] save_pickupY[1] save_pickupZ[1] grove_save_pickup[1]																 
CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[2] save_pickupY[2] save_pickupZ[2] grove_save_pickup[2]
//BUYABLE PROPERIES************************************************************
CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[3] save_pickupY[3] save_pickupZ[3] grove_save_pickup[3]
CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[4] save_pickupY[4] save_pickupZ[4] grove_save_pickup[4]
CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[5] save_pickupY[5] save_pickupZ[5] grove_save_pickup[5]
CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[6] save_pickupY[6] save_pickupZ[6] grove_save_pickup[6]
CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[7] save_pickupY[7] save_pickupZ[7] grove_save_pickup[7]
CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[8] save_pickupY[8] save_pickupZ[8] grove_save_pickup[8]																	 
CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[9] save_pickupY[9] save_pickupZ[9] grove_save_pickup[9]
CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[10] save_pickupY[10] save_pickupZ[10] grove_save_pickup[10]
CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[11] save_pickupY[11] save_pickupZ[11] grove_save_pickup[11]
CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[12] save_pickupY[12] save_pickupZ[12] grove_save_pickup[12]																	 

//ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT save_pickupX[8] save_pickupY[8] save_pickupZ[8] RADAR_SPRITE_SAVEHOUSE save_house_blip[8]
//CHANGE_BLIP_DISPLAY save_house_blip[8] BLIP_ONLY

//ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT save_pickupX[9] save_pickupY[9] save_pickupZ[9] RADAR_SPRITE_SAVEHOUSE save_house_blip[9]
//CHANGE_BLIP_DISPLAY save_house_blip[9] BLIP_ONLY																		 
save_index = 0 

									 


VAR_INT stadium_blip1 stadium_blip2 stadium_blip3 // FOR FILSHIE 

// kickstart
VAR_FLOAT kickX kickY kickZ

kickX = 1099.5513  
kickY = 1601.4974  
kickZ = 11.5546  

// blood bowl
VAR_FLOAT bloodX bloodY bloodZ

bloodx = -2120.0417   
bloody = -444.2838   
bloodz = 34.5312

//HOTRING
VAR_FLOAT hotringX hotringY	hotringZ

hotringX = 2695.6243 
hotringY = -1704.6885 
hotringZ = 10.8437


ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT kickX kickY kickZ RADAR_SPRITE_RACE stadium_blip1 
//CHANGE_BLIP_DISPLAY stadium_blip1 BLIP_ONLY

ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT bloodX bloodY bloodZ RADAR_SPRITE_RACE stadium_blip2  
//CHANGE_BLIP_DISPLAY stadium_blip2 BLIP_ONLY

ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT hotringX hotringY hotringZ RADAR_SPRITE_RACE stadium_blip3 
//CHANGE_BLIP_DISPLAY stadium_blip3 BLIP_ONLY






VAR_FLOAT lowrideX lowrideY lowrideZ

lowrideX = 	2644.4414 
lowrideY = 	-2012.5581 
lowrideZ = 	12.5469


VAR_INT tag_target[10]


//////////// Dave B Globals
VAR_INT fire_la_levels_completed[6]
VAR_INT fire_sf_levels_completed[6] 
VAR_INT fire_vg_levels_completed[6]
VAR_INT fire_total_la_completed		
VAR_INT fire_total_sf_completed		
VAR_INT fire_total_vg_completed		
VAR_INT fire_total_levels_completed
VAR_INT fire_temp_counter
VAR_INT	fire_people_created
VAR_INT pros_active
VAR_INT player_in_otb_flag
VAR_INT burglary_total_made	burglary_total_items
VAR_FLOAT burglary_noise


VAR_INT burglary_player_spotted

IF fire_temp_counter < 6
	fire_la_levels_completed[fire_temp_counter] = 0
	fire_sf_levels_completed[fire_temp_counter] = 0
	fire_vg_levels_completed[fire_temp_counter] = 0
ENDIF

fire_total_la_completed = 0		
fire_total_sf_completed	= 0	
fire_total_vg_completed	= 0
fire_total_levels_completed = 0

fire_people_created = 0

burglary_total_made	= 0
burglary_total_items= 0
 	
player_in_otb_flag = 0
pros_active = 0



VAR_INT flag_end_guy_audio_crash4
flag_end_guy_audio_crash4 = 0

// KEVIN WONGS GLOBALS //////
//VAR_INT mtbikeracer[6] // for  mtbike race
VAR_INT counter_robbery_cat4   // cat4
VAR_INT timer_countdown_farlie3	 //cat4
VAR_INT int_distance_player_girl  // farlie3
VAR_INT flag_otb_robbing_peds_panic
VAR_INT mtbikerace_selection

VAR_INT flag_is_farlie3_running

VAR_INT flag_mtbike_passed_1stime
VAR_INT flag_dozer_passed_1stime
VAR_INT flag_dumper_passed_1stime

VAR_INT current_highest_score_kickstart //kickstart highest score

VAR_INT flag_cycling_skill_help 
VAR_INT flag_triathalon_passed_1stime

VAR_INT mtbikerace_best_times[5]
VAR_INT triathalon_selection



triathalon_selection =1



flag_triathalon_passed_1stime = 0

flag_cycling_skill_help = 0


flag_mtbike_passed_1stime = 0
flag_dozer_passed_1stime = 0
flag_dumper_passed_1stime =	0

current_highest_score_kickstart = 25


flag_is_farlie3_running = 0


mtbikerace_selection = 1

//shared flags
VAR_INT animstate_flag
VAR_INT playernextset_flag
//shared flags

//do not reset flags
VAR_INT tread_helpflag


//shared between bench and dumbell
VAR_FLOAT animframe
VAR_FLOAT upperbodymusclestat_gym
VAR_INT powerbar
VAR_FLOAT animvalue
VAR_FLOAT bar_animvalue
VAR_FLOAT diffgym
VAR_FLOAT tempfloat_gym 
VAR_FLOAT animationtobeplayed
//shared between bench and dumbell

//objects

//blips

//new pickup object stuff
VAR_FLOAT animframe_bppickup animframe_bpgetoff
VAR_FLOAT animframe_freepickup animframe_freegetoff
VAR_FLOAT lowerbodymusclestat_gym

VAR_INT are_anims_loaded

////flags
VAR_INT playerexercising_flag
//////treadmill specific flags
VAR_INT starttreadmill_flag
VAR_INT incline_tread_flag
VAR_INT inclinebutton_flag
VAR_INT reptread_flag
VAR_INT buttonpressgymtread_flag
VAR_INT playerstarttread_flag
VAR_INT playwalkanim_flag
VAR_INT playjoganim_flag
VAR_INT playsprintanim_flag
VAR_INT finishedtread_flag

// Two Player Variables


VAR_INT ram_2p_level 

VAR_INT bike_2p_level
VAR_INT heli_2p_level
VAR_INT cars_2p_level

VAR_INT peds_2p_level


VAR_INT failed_cesar_race
failed_cesar_race = 0

var_int flag_stage_of_bcesar_race
flag_stage_of_bcesar_race = 0

//// BOLT's GLOBAL DOMINATION ///////
VAR_INT total_cars_parked


// SHOOTING RANGE LOCATIONS

// coords to check player location in each ammunation
VAR_FLOAT Srange_X[4] Srange_Y[4] Srange_Z[4]

//skill given to make sure player doesn't get awraded skills more than once.
VAR_INT SR_skill_award_given

VAR_INT sr_i flag_shooting_range_mission SR_range_id

flag_shooting_range_mission = 0

// coords for each of the shooting ranges
Srange_X[0] = 292.33 
Srange_Y[0]	= -35.39 
Srange_Z[0]	= 1000.50

Srange_X[1] = 289.4804 
Srange_Y[1]	= -79.6619 
Srange_Z[1]	= 1001.7275

Srange_X[2] = 309.9744 
Srange_Y[2]	= -135.3927 
Srange_Z[2]	= 1002.8265

Srange_X[3] = 306.8972 
Srange_Y[3]	= -164.9947 
Srange_Z[3]	= 1000.2593

val_Area = 0
force_valet_cleanup = 0


//impund
VAR_INT impound_area im_players_city

VAR_INT sc3_missioncar

//for valet script
VAR_INT valet_mission_terminate

//flags
VAR_INT valet_oddjob_opened player_on_valet_mission	playing_scrash1  valet_unlocked force_valet_cleanup

//chars (global)
VAR_INT valet[3] 

//cars
VAR_INT valet_pickup_car valet_car[3]	drop_off_car a_pickup_car
 
// global flags
VAR_INT can_script_be_cleaned val_Area create_drop_off_car_now
VAR_INT force_valet_on_mission valet_can_be_created[3] freeze_creating_drop_off_cars
VAR_INT debug_this valet_task[3] valet_event valet_scene_running valet_mission_dead[3]

VAR_INT valet_mission_completed

valet_mission_completed = 0

//scrash1
VAR_INT sc3_missioncar_alive

//parachute
VAR_INT player_fall_safe


VAR_INT bball_unlocked

bball_unlocked = 0




//////////////////////////////////



// ************ NEIL'S GLOBALS ***************************************************


VAR_INT disable_debug // used to disable entire debug script
VAR_INT syn2_mission_attempts
VAR_INT lowrider_meeting_is_loaded


VAR_INT zero_revenue zero_cash_pickup // put this in initial
zero_revenue = 5000 // put this in initial


// ------- IMPORT / EXPORT GLOBALS -------
VAR_INT import_export_is_active
import_export_is_active = 0	 // gets unlocked after the steal4 mission.

// ----- CRANE GLOBALS -------------------
VAR_INT player_is_in_crane
player_is_in_crane = 0
VAR_INT reset_crane_camera
reset_crane_camera = 0
VAR_INT disable_crane
disable_crane = 1  // gets enabled after the steal4 mission
VAR_INT disable_crane_exit
disable_crane_exit = 0
VAR_INT finish_crane_loading_script
finish_crane_loading_script = 0
VAR_INT remove_player_from_crane
remove_player_from_crane = 0
VAR_INT disable_crane_slide
disable_crane_slide = 0
VAR_INT disable_crane_rotate
disable_crane_rotate = 0
VAR_INT disable_manual_control
disable_manual_control = 0
VAR_INT magno_camera_type
magno_camera_type = 0


// ----- LOWRIDER GLOBALS ---------------
VAR_INT sw6_mission_attempts // global for lowrider mission
sw6_mission_attempts = 0

// ----- CONVERSATION CONSTANTS ---------
CONST_INT CONVERSATION_INITIALISE			0
CONST_INT CONVERSATION_PLAYING				1
CONST_INT CONVERSATION_PAUSED				2
CONST_INT CONVERSATION_FINISHED				3
CONST_INT CONVERSATION_FINISH_GRACEFULLY	4

// ----- BASKETBALL GLOBALS -------------
VAR_INT bball_challenge_active
VAR_INT bball_challenge_high_score
VAR_INT bball_challenge_timer
VAR_INT bball_challenge_score
VAR_INT bball_help
VAR_INT bball_shot_dist	bball_shot_dist_decimal
VAR_INT bball_active
VAR_INT points_scored
VAR_INT bball_throw_active


// KEITH ODDJOB CASH PICKUP GLOBALS
// ...quarry completion
VAR_INT g_Quarry_CashPickupKM
VAR_INT g_Quarry_CashPickupValueKM
g_Quarry_CashPickupValueKM = 2000

// ...trucking completion
VAR_INT g_Truck_CashPickupKM
VAR_INT g_Truck_CashPickupValueKM
g_Truck_CashPickupValueKM = 2000


// *****************************************************************************

// onscreen counter and timer vars
VAR_INT d2_num_packages_collected d6_door_health

//STEAL 5 - opening car showroom stuff
VAR_INT showroom_revenue showroom_cash_pickup 
showroom_revenue = 8000 


//VAR_INT TO PASS STRAP4 - see andyd
VAR_INT strap4_mission_passed_once_flag

strap4_mission_passed_once_flag = 0

flag_had_taxi_jump_help = 0
flag_catcutscene_counter = 0
flag_otb_robbing_peds_panic = 0

nascar_best_lap_time = 999999
nascar_best_time = 999999
best_nascar_result = 999999

mission_trigger_wait_time = 250
one_sixteenth = 0.0625 
one_thirtysecond = one_sixteenth / 2.0
one_sixtyfourth =  one_thirtysecond / 2.0
recording = 0
stop_gargae_for_neil = 1

bmx_1_x = -425.0
bmx_1_y = 1410.0
bmx_1_z = 10.0
//heli1_checkpoint_best_time = 99999999

// airrace stuff
race1_best_position_airrace = 99999999
race1_best_time_airrace = 99999999
race2_best_position_airrace = 99999999
race2_best_time_airrace = 99999999
race3_best_position_airrace = 99999999
race3_best_time_airrace = 99999999
race4_best_position_airrace = 99999999
race4_best_time_airrace = 99999999
race5_best_position_airrace = 99999999
race5_best_time_airrace = 99999999
race6_best_position_airrace = 99999999
race6_best_time_airrace = 99999999
done_race1_progress_airrace = 0
done_race2_progress_airrace = 0
done_race3_progress_airrace = 0
done_race4_progress_airrace = 0
done_race5_progress_airrace = 0
done_race6_progress_airrace = 0
//first_cat_fade[0] = 0
//first_cat_fade[1] = 0
//first_cat_fade[2] = 0
//first_cat_fade[3] = 0
//cat_mission_ended[0] = 0
//cat_mission_ended[1] = 0
//cat_mission_ended[2] = 0
//cat_mission_ended[3] = 0

//added_final_twar_blip = 0

pilot_test_passed = 0
driving_test_passed = 0

//andy d's driving school stuff
f1_the360_best_score = 0
f1_the180_best_score = 0 
f1_the90_best_score = 0 
f1_spinrightgo_best_score = 0 
f1_spinleftgo_best_score = 0 
f1_burnlapright_best_score = 0 
f1_burnlapleft_best_score = 0 
f1_popcontrol_best_score = 0 
f1_cityslicking_best_score = 0 
f1_whiprightterminate_best_score = 0
f1_whipleftterminate_best_score = 0 
f1_alleyoop_best_score = 0  
f1_wheelieweave_best_score = 0 
f1_pittechnique_best_score = 0 
f1_conecoilright_best_score = 0 
f1_conecoilleft_best_score = 0
f1_which_missions_are_open_flag = 0

//andy's bloodbowl stuff
VAR_INT blood_attempt blood_passed_once
blood_attempt = 1
blood_passed_once = 0

// jude's pilot school stuff
VAR_INT d5_best_scores[10]
VAR_INT d5_total_missions_open d5_last_played
d5_best_scores[0] = 0
d5_best_scores[1] = 0
d5_best_scores[2] = 0
d5_best_scores[3] = 0
d5_best_scores[4] = 0
d5_best_scores[5] = 0
d5_best_scores[6] = 0
d5_best_scores[7] = 0
d5_best_scores[8] = 0
d5_best_scores[9] = 0
d5_total_missions_open = 1
d5_last_played = 1

VAR_INT d5_watched_first_cutscene
d5_watched_first_cutscene = 0

CONST_INT FLYING_SKILL_REQUIRED_FOR_PILOT_LICENCE 180
VAR_INT d5_pilot_licence_obtained
d5_pilot_licence_obtained = 0

VAR_INT d5_bronze_generator d5_silver_generator d5_gold_generator
VAR_INT d5_bronze_generator_unlocked d5_silver_generator_unlocked d5_gold_generator_unlocked
d5_bronze_generator_unlocked = 0
d5_silver_generator_unlocked = 0
d5_gold_generator_unlocked = 0
CREATE_CAR_GENERATOR 325.12 2537.1 17.52 180.0 RUSTLER -1 -1 TRUE 0 0 0 10000 d5_bronze_generator
CREATE_CAR_GENERATOR 348.0 2537.1 17.42 180.0 STUNT -1 -1 TRUE 0 0 0 10000 d5_silver_generator
CREATE_CAR_GENERATOR 365.51 2537.1 17.42 180.0 HUNTER -1 -1 TRUE 0 0 0 10000 d5_gold_generator
SWITCH_CAR_GENERATOR d5_bronze_generator 0
SWITCH_CAR_GENERATOR d5_silver_generator 0
SWITCH_CAR_GENERATOR d5_gold_generator 0

//Steve T's Boat School stuff.

VAR_INT boat_which_missions_are_open_flag
boat_which_missions_are_open_flag = 1

VAR_INT boat_accelerate_best_score
VAR_INT boat_simplecircuit_best_score
VAR_INT boat_slalom_best_score
VAR_INT boat_skijump_best_score
VAR_INT boat_hover_best_score

boat_accelerate_best_score = 60000
boat_simplecircuit_best_score = 80000
boat_slalom_best_score = 180000
boat_skijump_best_score = 10
boat_hover_best_score = 200000

VAR_INT boat_cut_watched
boat_cut_watched = 0

VAR_INT boat_passed_once
boat_passed_once = 0


//Boat School-  all gold, all silver, all bronze reward vars
VAR_INT boat_accelerate_bronzeachieved boat_accelerate_silverachieved boat_accelerate_goldachieved
boat_accelerate_bronzeachieved = 0
boat_accelerate_silverachieved = 0 
boat_accelerate_goldachieved = 0

VAR_INT boat_simplecircuit_bronzeachieved boat_simplecircuit_silverachieved boat_simplecircuit_goldachieved
boat_simplecircuit_bronzeachieved = 0
boat_simplecircuit_silverachieved = 0
boat_simplecircuit_goldachieved = 0

VAR_INT boat_slalom_bronzeachieved boat_slalom_silverachieved boat_slalom_goldachieved
boat_slalom_bronzeachieved = 0
boat_slalom_silverachieved = 0
boat_slalom_goldachieved = 0

VAR_INT boat_skijump_bronzeachieved boat_skijump_silverachieved boat_skijump_goldachieved
boat_skijump_bronzeachieved = 0
boat_skijump_silverachieved = 0
boat_skijump_goldachieved = 0

VAR_INT boat_hover_bronzeachieved boat_hover_silverachieved boat_hover_goldachieved
boat_hover_bronzeachieved = 0
boat_hover_silverachieved = 0
boat_hover_goldachieved = 0

//only give the reward vehicle once.
VAR_INT boat_bronze_rewardgiven	boat_silver_rewardgiven boat_gold_rewardgiven
boat_bronze_rewardgiven = 0
boat_silver_rewardgiven = 0
boat_gold_rewardgiven = 0


VAR_INT boat_bronze_generator boat_silver_generator boat_gold_generator

CREATE_CAR_GENERATOR -2227.1045 2445.7756 0.0 229.3771  MARQUIS -1 -1 TRUE 0 0 0 10000 boat_bronze_generator
CREATE_CAR_GENERATOR -2223.8726 2409.8657 0.0 49.0555   SQUALO -1 -1 TRUE 0 0 0 10000 boat_silver_generator
CREATE_CAR_GENERATOR -2247.8357 2425.6753 0.0 220.2123  JETMAX -1 -1 TRUE 0 0 0 10000 boat_gold_generator
SWITCH_CAR_GENERATOR boat_bronze_generator 0
SWITCH_CAR_GENERATOR boat_silver_generator 0
SWITCH_CAR_GENERATOR boat_gold_generator 0








//Steve T's Pimp, LA, SF and LV courier oddjobs

VAR_INT pimp_passed_once
pimp_passed_once = 0
VAR_INT courierLA_passed_once
courierLA_passed_once = 0
VAR_INT courierLV_passed_once
courierLV_passed_once = 0
VAR_INT courierSF_passed_once
courierSF_passed_once = 0

VAR_INT courierLA_revenue courierLA_cash_pickup
courierLA_revenue = 2000

VAR_INT courierSF_revenue courierSF_cash_pickup
courierSF_revenue = 2000

VAR_INT courierLV_revenue courierLV_cash_pickup
courierLV_revenue = 2000

// Steve T's casino3 skip
VAR_INT ca3_driven_to_factory

ca3_driven_to_factory = 0

// Chris M's Shooter vars
VAR_TEXT_LABEL $shtr_hi_1[10] $shtr_hi_2[10] $shtr_hi_3[10] 
VAR_INT shtr_hi_s[10] shtr_hi_l[10]

// Chris M's Gravity vars
VAR_TEXT_LABEL $grav_hi_1[10] $grav_hi_2[10] $grav_hi_3[10] 
VAR_INT grav_hi_s[10] grav_hi_l[10]


// Chris M's Bike School Vars
VAR_INT bs_open_tests flag_trailor_cutscene
VAR_INT bs_wheelie_best_score 
VAR_INT bs_stoppie_best_score
VAR_INT bs_the360_best_score 
VAR_INT bs_the180_best_score
VAR_INT bs_jumpstop_best_score 
VAR_INT bs_jumpstoppie_best_score
VAR_INT bs_passed

bs_passed = 0
flag_trailor_cutscene = 0
bs_open_tests = 0
bs_wheelie_best_score = 0 
bs_stoppie_best_score = 0
bs_the360_best_score = 0 
bs_the180_best_score = 0
bs_jumpstop_best_score = 0 
bs_jumpstoppie_best_score = 0

VAR_INT bs_the360_bronzeachieved bs_the360_silverachieved bs_the360_goldachieved
bs_the360_bronzeachieved = 0
bs_the360_silverachieved = 0 
bs_the360_goldachieved = 0

VAR_INT bs_the180_bronzeachieved bs_the180_silverachieved bs_the180_goldachieved
bs_the180_bronzeachieved = 0
bs_the180_silverachieved = 0
bs_the180_goldachieved = 0

VAR_INT bs_wheelie_bronzeachieved bs_wheelie_silverachieved bs_wheelie_goldachieved
bs_wheelie_bronzeachieved = 0
bs_wheelie_silverachieved = 0
bs_wheelie_goldachieved = 0

VAR_INT bs_jumpstop_bronzeachieved bs_jumpstop_silverachieved bs_jumpstop_goldachieved
bs_jumpstop_bronzeachieved = 0
bs_jumpstop_silverachieved = 0
bs_jumpstop_goldachieved = 0

VAR_INT bs_stoppie_bronzeachieved bs_stoppie_silverachieved bs_stoppie_goldachieved
bs_stoppie_bronzeachieved = 0
bs_stoppie_silverachieved = 0
bs_stoppie_goldachieved = 0

VAR_INT bs_jumpstoppie_bronzeachieved bs_jumpstoppie_silverachieved bs_jumpstoppie_goldachieved
bs_jumpstoppie_bronzeachieved = 0
bs_jumpstoppie_silverachieved = 0
bs_jumpstoppie_goldachieved = 0

VAR_INT bs_bronze_rewardgiven bs_silver_rewardgiven bs_gold_rewardgiven
bs_bronze_rewardgiven = 0
bs_silver_rewardgiven = 0
bs_gold_rewardgiven = 0

VAR_INT bs_bronze_generator bs_silver_generator bs_gold_generator

CREATE_CAR_GENERATOR 1174.7596 1364.8319 10.1203 280.0355 FREEWAY -1 -1 TRUE 0 0 0 10000 bs_bronze_generator
CREATE_CAR_GENERATOR 1174.9988 1366.4785 10.1203 282.2258 FCR900 -1 -1 TRUE 0 0 0 10000 bs_silver_generator
CREATE_CAR_GENERATOR 1174.4670 1368.3585 10.1203 283.0546 NRG500 -1 -1 TRUE 0 0 0 10000 bs_gold_generator
SWITCH_CAR_GENERATOR bs_bronze_generator 0
SWITCH_CAR_GENERATOR bs_silver_generator 0
SWITCH_CAR_GENERATOR bs_gold_generator 0


// Chris M's Cash Courier Var
VAR_INT bce2_played

bce2_played = 0

recreate_tplay_pickup = 0

VAR_INT start_the_bcesar_race
start_the_bcesar_race = 0

// Chris M's car for Cesar's race
VAR_INT cs1_player_car

// Chris M's First Time Flags
VAR_INT flag_bce2_passed_1stime
VAR_INT flag_syn1_passed_1stime
VAR_INT flag_cas1_passed_1stime
VAR_INT flag_bikeschool_passed_1stime
VAR_INT flag_shtr_passed_1stime
VAR_INT flag_grav_passed_1stime

VAR_INT flag_on_courier_mission	respect_help_played

respect_help_played = 0
flag_bce2_passed_1stime = 0
flag_syn1_passed_1stime = 0
flag_cas1_passed_1stime = 0
flag_bikeschool_passed_1stime = 0
flag_shtr_passed_1stime = 0
flag_grav_passed_1stime = 0
flag_on_courier_mission = 0

// Chris M's Trip Skip flags
VAR_INT flag_syn1_skip1 flag_syn1_skip2
VAR_INT flag_bcr1_skip

flag_syn1_skip1 = 0
flag_syn1_skip2	= 0
flag_bcr1_skip = 0

//stat calculation 
statbikestamina_ctr = 0.0
statbikelowmuscle_ctr = 0.0
statbike_temp = 0.0
//stat calculation 
stattreadstamina_ctr = 0.0
stattreadlowmuscle_ctr = 0.0
stattread_temp = 0.0
trigger_final_LA1_missions = 0

on_footX = 1.5
on_footY = 1.5	
on_footZ = 3.0

in_carX = 4.0
in_carY = 4.0
in_carZ	= 4.0




traceX[0] =	1766.1335
traceY[0] =	-1704.2281
traceZ[0] =	12.4731 //LA

traceX[1] =	-1922.9225
traceY[1] =	282.7151
traceZ[1] =	40.0391 //San Fran

traceX[2] =	1636.9160
traceY[2] =	912.9478
traceZ[2] =	9.6890 //Vegas

traceX[3] =	1701.6902
traceY[3] =	1649.1930
traceZ[3] =	9.6296 // Vegas aircraft race

const_int CESAR_RACE 0
const_int BADLAND_RACE1 7
const_int BADLAND_RACE2 8
const_int NASCAR_RACE 25
const_int DIRTBIKE_STADIUM 26

const_int LA_RACES 0
const_int SF_RACES 1
const_int LV_RACES 2
const_int AIR_RACES 3
var_int menu_mode
menu_mode = 0

VAR_INT cprace_best_position[30] 
cprace_best_position[0] = 999999999 
cprace_best_position[1] = 999999999
cprace_best_position[2] = 999999999
cprace_best_position[3] = 999999999
cprace_best_position[4] = 999999999
cprace_best_position[5] = 999999999
cprace_best_position[6] = 999999999
cprace_best_position[7] = 999999999
cprace_best_position[8] = 999999999
cprace_best_position[9] = 999999999
cprace_best_position[10] = 999999999
cprace_best_position[11] = 999999999
cprace_best_position[12] = 999999999
cprace_best_position[13] = 999999999
cprace_best_position[14] = 999999999
cprace_best_position[15] = 999999999
cprace_best_position[16] = 999999999
cprace_best_position[17] = 999999999
cprace_best_position[18] = 999999999
cprace_best_position[19] = 999999999
cprace_best_position[20] = 999999999
cprace_best_position[21] = 999999999
cprace_best_position[22] = 999999999
cprace_best_position[23] = 999999999
cprace_best_position[24] = 999999999
cprace_best_position[25] = 999999999
cprace_best_position[26] = 999999999
cprace_best_position[27] = 999999999
cprace_best_position[28] = 999999999
cprace_best_position[29] = 999999999

VAR_INT cprace_best_times[30]
cprace_best_times[0] = 999999999
cprace_best_times[1] = 999999999
cprace_best_times[2] = 999999999
cprace_best_times[3] = 999999999
cprace_best_times[4] = 999999999
cprace_best_times[5] = 999999999
cprace_best_times[6] = 999999999
cprace_best_times[7] = 999999999
cprace_best_times[8] = 999999999
cprace_best_times[9] = 999999999
cprace_best_times[10] = 999999999
cprace_best_times[11] = 999999999
cprace_best_times[12] = 999999999
cprace_best_times[13] = 999999999
cprace_best_times[14] = 999999999
cprace_best_times[15] = 999999999
cprace_best_times[16] = 999999999
cprace_best_times[17] = 999999999
cprace_best_times[18] = 999999999
cprace_best_times[19] = 999999999
cprace_best_times[20] = 999999999
cprace_best_times[21] = 999999999
cprace_best_times[22] = 999999999
cprace_best_times[23] = 999999999
cprace_best_times[24] = 999999999
cprace_best_times[25] = 999999999
cprace_best_times[26] = 999999999
cprace_best_times[27] = 999999999
cprace_best_times[28] = 999999999
cprace_best_times[29] = 999999999

VAR_INT cprace_best_lap_times[30]
cprace_best_lap_times[0] = 999999999
cprace_best_lap_times[1] = 999999999
cprace_best_lap_times[2] = 999999999
cprace_best_lap_times[3] = 999999999
cprace_best_lap_times[4] = 999999999
cprace_best_lap_times[5] = 999999999
cprace_best_lap_times[6] = 999999999
cprace_best_lap_times[7] = 999999999
cprace_best_lap_times[8] = 999999999
cprace_best_lap_times[9] = 999999999
cprace_best_lap_times[10] = 999999999
cprace_best_lap_times[11] = 999999999
cprace_best_lap_times[12] = 999999999
cprace_best_lap_times[13] = 999999999
cprace_best_lap_times[14] = 999999999
cprace_best_lap_times[15] = 999999999
cprace_best_lap_times[16] = 999999999
cprace_best_lap_times[17] = 999999999
cprace_best_lap_times[18] = 999999999
cprace_best_lap_times[19] = 999999999
cprace_best_lap_times[20] = 999999999
cprace_best_lap_times[21] = 999999999
cprace_best_lap_times[22] = 999999999
cprace_best_lap_times[23] = 999999999
cprace_best_lap_times[24] = 999999999
cprace_best_lap_times[25] = 999999999
cprace_best_lap_times[26] = 999999999
cprace_best_lap_times[27] = 999999999
cprace_best_lap_times[28] = 999999999
cprace_best_lap_times[29] = 999999999

VAR_INT got_race_made_progress[30]
got_race_made_progress[0] = 0	
got_race_made_progress[1] = 0	
got_race_made_progress[2] = 0	
got_race_made_progress[3] = 0	
got_race_made_progress[4] = 0	
got_race_made_progress[5] = 0	
got_race_made_progress[6] = 0	
got_race_made_progress[7] = 0	
got_race_made_progress[8] = 0	
got_race_made_progress[9] = 0	
got_race_made_progress[10] = 0
got_race_made_progress[11] = 0
got_race_made_progress[12] = 0
got_race_made_progress[13] = 0
got_race_made_progress[14] = 0
got_race_made_progress[15] = 0
got_race_made_progress[16] = 0
got_race_made_progress[17] = 0
got_race_made_progress[18] = 0
got_race_made_progress[19] = 0
got_race_made_progress[20] = 0
got_race_made_progress[21] = 0
got_race_made_progress[22] = 0
got_race_made_progress[23] = 0
got_race_made_progress[24] = 0
got_race_made_progress[25] = 0
got_race_made_progress[26] = 0
got_race_made_progress[27] = 0
got_race_made_progress[28] = 0
got_race_made_progress[29] = 0

var_int total_races_completed
total_races_completed = 0

var_int got_race_mission_complete
got_race_mission_complete = 0

var_int disable_mod_garage
disable_mod_garage = 0



flag_airrace_mission_passed = 0
been_in_a_coastguard_before = 0
flag_player_on_coastguard_mission = 0

//CATALINA PED GENERATION COORDINATES
catX[0] = 868.3358   //CATALINA CONTACT POINT AT LODGE
catY[0] = -29.5529   
catZ[0] = 62.3276

//ACTUAL MISSION TRIGGER COORDINATES
catX[1] = 257.5389 //CAT1.SC - LIQUOR - Neil    
catY[1] = -77.2300 
catZ[1] = 1.3678

catX[2] = 2297.78//CAT2.SC - BANK - ChrisR
catY[2] = -16.83
catZ[2] = 26.29

catX[3] = 652.17 //CAT3.SC - GAS STATION - ChrisM
catY[3] = -559.75
catZ[3] = 15.15

catX[4] = 1294.09 //CAT4.SC - OTB - Kev
catY[4] = 267.78 
catZ[4] = 18.54

catX[5] = 681.5950    //CONTACT POINT AT BAR
catY[5] = -478.7909  
catZ[5] = 15.3281

var_int cat_liquor_banter cat_otb_banter
cat_liquor_banter = 0
cat_otb_banter	= 0
cat_counter = 0
flag_cat_mission1_passed = 0
flag_cat_mission2_passed = 0
flag_cat_mission3_passed = 0
flag_cat_mission4_passed = 0


pilotX = 415.55     //Pilot School
pilotY = 2533.57    
pilotZ = 19.18

// ****************** HEIST5 CAR GEN FOR MISSION COMPLETE **********************
VAR_INT hm5_car_gen
CREATE_CAR_GENERATOR 383.178 2538.891 16.53 180.0 LEVIATHN -1 -1 TRUE 0 0 0 10000 hm5_car_gen
SWITCH_CAR_GENERATOR hm5_car_gen 0

// LA1*************************************************  
introX = 2495.2144     
introY = -1687.0298     
introZ = 12.5144

sweetX = 2515.07       
sweetY = -1673.98    
sweetZ = 12.71

ryderX = 2459.55       
ryderY = -1687.75      
ryderZ = 12.56

smokeX = 2070.87    
smokeY = -1703.01   
smokeZ = 12.55

strapX = 790.54  //burger joint   
strapY = -1627.91    
strapZ = 12.39

strap2X = 2486.61 //house party   
strap2Y = -1649.42   
strap2Z = 12.48

cesarX = 1801.08    
cesarY = -2117.92   
cesarZ = 12.56

crashX = 1042.85 
crashY = -1338.62 
crashZ = 12.55


//la1fin1X = 2522.23
//la1fin1Y = -1679.20
//la1fin1Z = 14.0

// Cesar Race Flag

VAR_INT cs1_race_is_go


//BADLANDS*********************************************
bcrashX = -2043.34  
bcrashY = -2525.99  
bcrashZ = 29.62

truthX = -922.5121  
truthY = -1719.3951  
truthZ = 76.5703

truth2X = -2198.8696  
truth2Y = -2261.2024 
truth2Z	= 29.6419

bcesarX = 1552.7803   
bcesarY = 39.3031   
bcesarZ	= 23.1445

	 		
//SAN FRAN*********************************************		 	 
wuziX = -2154.2080 //ChinaTown
wuziY = 645.3251  
wuziZ = 51.3516

syndX = -2623.4971  //Pimp house
syndY = 1405.6602  
syndZ = 6.1016

stealX = -2031.2612   //GARAGE
stealY = 179.2488    			
stealZ = 27.8359				

zeroX = -2245.6631 //Toy shop 
zeroY = 128.8889    
zeroZ = 34.3203

scrashX = -2030.4019 //Hub
scrashY = 148.8279 
scrashZ	= 27.8359

garageX = -2030.4019  //Hub														 
garageY = 148.8279 
garageZ	= 27.8359

testsX = -2026.8105   //Driving tests
testsY = -114.9093   
testsZ = 1034.1792


//VEGAS************************************************
desertX = -685.2156 //Ranch  
desertY = 923.2191    
desertZ	= 11.1531

desert2X = 327.4480  //AirStrip  
desert2Y = 2530.0950        
desert2Z = 15.8066

casinoX = 1962.4316 //Players casino
casinoY = 974.6750     
casinoZ	= 993.4688

TheheistX = 1962.3984 //Players casino Heist
TheheistY = 1060.9623   
TheheistZ = 993.4688 

heistX = 2270.6335 //Mafia casino  
heistY = 1635.5992  
heistZ = 1007.3672

vcrashX	= 1598.5573 //Suburbs
vcrashY	= 2667.8296  
vcrashZ	= 9.8203

docX = 2090.0 
docY = 1451.0 
docZ = 9.8


//LA2**************************************************
mansionX = 1253.7880        
mansionY = -785.2594       
mansionZ = 91.0313

groveX = 2496.06 
groveY = -1687.96
groveZ = 12.53



//Miss
chickX = -299.6  
chickY = 1530.4 
chickZ = 74.7

shooter1X = 1498.7   
shooter1Y =	-1631.9
shooter1Z =	14.0

gravX = 1505.7 
gravY = -1631.9
gravZ =	14.0

spaceX = 1512.7 
spaceY = -1631.9
spaceZ = 14.0


//ODDJOBS
RouletteX =	1960.8979   
RouletteY =	1008.4060 
RouletteZ =	991.4745 

// PIMP
VAR_FLOAT pimpX pimpY pimpZ

pimpX = 1918.9883 
pimpY =	-1789.3239
pimpZ =	12.5

// hitchhikers			
VAR_FLOAT hitchX hitchY hitchZ 

hitchX = -1563.7684     
hitchY = 2690.8730   
hitchZ = 54.6985


//otb 
otbX = 828.77 
otbY = 4.71 
otbZ = 1003.17

wheeloX = 1494.3 
wheeloY = -1644.6 
wheeloZ	= 14.0

blackjackX = 1489.5 
blackjackY = -1674.2
blackjackZ = 12.5

driving_schoolx = -2132.45
driving_schooly = -47.0
driving_schoolz	= 35.3

basketballx = 2323.97 
basketbally = -1517.04 
basketballz = 24.32  

limox = 1979.1 
limoy = -1996.1 
limoz = 12.4

directorx = 862.9
directory = -1154.8
directorz = 23.0

valetx = -1758.8767 
valety = 952.7440 
valetz = 23.7487

banditx = 2258.5234  
bandity = 1613.2504  
banditz = 1005.1875


//heli1X = -569.1451  
//heli1Y = 851.0923 
//heli1Z = 22.8402


//ass_2_x = 482.5293
//ass_2_Y = 244.276
//ass_2_Z	= 10.09
//
//ass_3_x = 38.244
//ass_3_Y = -1024.251
//ass_3_Z	= 9.451
//
//ass_4_x = -1481.8719
//ass_4_Y = -825.3049
//ass_4_Z	= 13.8777
//
//ass_5_x = -978.0145
//ass_5_Y = -530.7043
//ass_5_Z	= 9.9513

flag_courier_mission_passed = 0
flag_courier_trigger = 0

VAR_FLOAT cour1X cour1Y cour1Z
VAR_FLOAT cour2X cour2Y cour2Z
VAR_FLOAT cour3X cour3Y cour3Z

cour1X = 1359.45  
cour1Y = -1755.00 
cour1Z = 12.85

cour2X = -2590.44  
cour2Y = 73.21 
cour2Z = 3.91

cour3X = 1887.79  
cour3Y = 2087.39 
cour3Z = 10.05

/*
//Bulldozer
VAR_FLOAT bulldozerX bulldozerY bulldozerZ
VAR_INT flag_dozer_trigger

bulldozerx = 623.8815  
bulldozery = 870.9470  
bulldozerz = -43.9531 

//Dumper Truck Mission
VAR_FLOAT dumperX dumperY dumperZ
VAR_INT flag_dumper_trigger

dumperx = 684.1580    
dumpery = 899.4011   
dumperz = -41.382 
*/

// Quarry
VAR_FLOAT quarryX quarryY quarryZ

quarryX = 823.6816    
quarryY = 854.1741   
quarryZ = 10.7949 


//mountainbike
VAR_FLOAT mountbX mountbY mountbZ

mountbX = -2309.23 
mountbY = -1651.22 
mountbZ = 483.09


VAR_FLOAT carcraneX carcraneY carcraneZ

carcraneX = -1517.9033 
carcraneY = 153.7578  
carcraneZ = 2.7813

//demolition derby
VAR_FLOAT demoX demoY demoZ

demox = 2705.1716
demoy = -1698.1272
demoz = 10.8515

// pool
VAR_FLOAT poolX poolY poolZ

poolx = 1134.0
pooly = -7.0  
poolz = 1001.0

// boat school
VAR_FLOAT boatsX boatsY boatsZ

boatsX = -2187.3782  
boatsY = 2416.5518  
boatsZ = 4.1660

VAR_FLOAT bikesX bikesY bikesZ

bikesX = 1173.8767   
bikesY = 1351.0371   
bikesZ = 9.9219


//Trucking
VAR_FLOAT truckX truckY truckZ

truckX = -77.6456  
truckY = -1136.4010  
truckZ = 0.0781

// dancing
VAR_FLOAT danceX danceY danceZ

dancex = -2645.23
dancey = 1413.30
dancez = -92.69

// 2player kill
VAR_FLOAT kill2pX kill2pY kill2pZ

kill2pX = -2325.37
kill2pY = -131.655
kill2pZ = 34.3


VAR_FLOAT bike_2pX bike_2pY bike_2pZ

bike_2pX = 563.78  
bike_2pY = 2725.14 
bike_2pZ = 60.49

VAR_FLOAT cars_2pX cars_2pY cars_2pZ

cars_2pX = 424.6570    
cars_2pY = -1439.6741  
cars_2pZ = 30.3410

VAR_FLOAT heli_2pX heli_2pY heli_2pZ

heli_2pX = 1681.19  
heli_2pY = -1433.31 
heli_2pZ = 12.53

VAR_FLOAT peds_2pX peds_2pY peds_2pZ

peds_2pX = 1576.7339   
peds_2pY = -1609.7407  
peds_2pZ = 12.3828

VAR_FLOAT run_2pX run_2pY run_2pZ

run_2pX = 2831.2429   
run_2pY = -1674.6372   
run_2pZ = 8.9732

VAR_FLOAT demo_2pX demo_2pY demo_2pZ

demo_2pX = 2668.7688 
demo_2pY = -1753.9734 
demo_2pZ = 10.8515


// 2 player tag
VAR_FLOAT tagX tagY tagZ 

tagx = -2338.50
tagy = -109.01
tagz = 34.36

//2 player boxing 
VAR_FLOAT boxingX boxingY boxingZ

boxingX = 764.4572 
boxingY = 2.7450 
boxingZ = 999.7153 

//p2_survival
VAR_FLOAT surviveX surviveY surviveZ

surviveX = 2747.0552
surviveY = -1691.2076 
surviveZ = 10.8515

//p2_sumo
VAR_FLOAT sumoX sumoY sumoZ

sumox = 2694.2456
sumoy = -1706.0067
sumoz = 10.8556


// **************************************** CRASH4 GANG WAR TRAINING *******************************

VAR_INT gym_is_running

// **************************************** CRASH4 GANG WAR TRAINING *******************************
VAR_INT played_cut1_before_crash4 played_cut2_before_crash4 // used to let player skip the cutscenes if they have watched them before.

played_cut1_before_crash4 = 0
played_cut2_before_crash4 = 0

created_save_blips = 0

// **************************************** SHOP MENU VARAIBLES ***********************************
VAR_INT shopkeeper_model_shops

VAR_INT switch_the_gym_interiors_off
switch_the_gym_interiors_off = 0

VAR_INT cost_menu_shops cost_menu_drawn_shops main_menu_shops main_menu_drawn_shops 
VAR_INT bought_menu_shops bought_menu_drawn_shops sub_menu_shops sub_menu_drawn_shops

VAR_INT price_counter

CONST_INT MAX_NUMBER_ALLOWED_IN_MENU_SHOPS 12

VAR_INT item_price[MAX_NUMBER_ALLOWED_IN_MENU_SHOPS]
VAR_INT item_model_index[MAX_NUMBER_ALLOWED_IN_MENU_SHOPS]
VAR_TEXT_LABEL $item_text_label[MAX_NUMBER_ALLOWED_IN_MENU_SHOPS]
VAR_INT item_modelid[MAX_NUMBER_ALLOWED_IN_MENU_SHOPS]
VAR_INT item_component[MAX_NUMBER_ALLOWED_IN_MENU_SHOPS]

VAR_INT item_hilight_shops[MAX_NUMBER_ALLOWED_IN_MENU_SHOPS]

VAR_INT modelid_shops component_shops special_camera // used to display clothes on the player

VAR_INT area_looked_for_shops item_no_shops temp_var_shops

VAR_INT shop_main_item_picked_shops shop_sub_item_picked_shops

VAR_TEXT_LABEL $ITEM1 $ITEM2 $ITEM3 $ITEM4 $ITEM5 $ITEM6 $ITEM7 $ITEM8 $ITEM9 $ITEM10 $ITEM11 $ITEM12 // used for 1st menu

VAR_INT player_item_texture_shops player_item_model_shops // used to check what stuff player has

VAR_INT flag_no_money_shops // used to print message to tell player they have no cash

VAR_INT flag_bought_item_already_shops

VAR_INT flag_player_owned_item_shops

VAR_INT area_to_look_at_shops
area_to_look_at_shops = 0

flag_bought_item_already_shops = 0
flag_no_money_shops = 0
flag_player_owned_item_shops = 0

price_counter = 0
cost_menu_drawn_shops = 0
main_menu_drawn_shops = 0
bought_menu_drawn_shops = 0
sub_menu_drawn_shops = 0
shop_main_item_picked_shops = 0
shop_sub_item_picked_shops = 0

area_looked_for_shops = 0 
item_no_shops = 0 
temp_var_shops = 0

// for lowrider mission to give free stuff in Cesars
VAR_INT flag_on_neil_lowrider_mission
flag_on_neil_lowrider_mission = 0

// used to tell me player has bought from shops for the Wardrobe
VAR_INT flag_bought_from_binco
VAR_INT flag_bought_from_prolapse	
VAR_INT flag_bought_from_suburban
VAR_INT flag_bought_from_zip	
VAR_INT flag_bought_from_victim
VAR_INT flag_bought_from_didiessachs
flag_bought_from_binco = 0
flag_bought_from_prolapse = 0	
flag_bought_from_suburban = 0
flag_bought_from_zip = 0	
flag_bought_from_victim = 0
flag_bought_from_didiessachs = 0

VAR_INT flag_player_got_gimp_suit
VAR_INT flag_player_got_valet_uniform
VAR_INT flag_player_got_casino_uniform
VAR_INT flag_player_got_police_uniform
VAR_INT flag_player_got_country_clothes
VAR_INT flag_got_mechanic_clothes
VAR_INT flag_got_medic_clothes
VAR_INT flag_got_pimp_clothes

flag_player_got_gimp_suit = 0
flag_player_got_valet_uniform = 0
flag_player_got_casino_uniform = 0
flag_player_got_police_uniform = 0
flag_player_got_country_clothes = 0
flag_got_mechanic_clothes = 0
flag_got_medic_clothes = 0
flag_got_pimp_clothes = 0
 
// used for shopkeeper speech
VAR_INT sample_name_shop

// ammunation weapon opens stuff
VAR_INT flag_6weapons_open flag_7weapons_open flag_8weapons_open flag_9weapons_open flag_10weapons_open
VAR_INT flag_11weapons_open flag_12weapons_open flag_13weapons_open flag_14weapons_open flag_15weapons_open
VAR_INT flag_16weapons_open
flag_6weapons_open = 0
flag_7weapons_open = 0 
flag_8weapons_open = 0 
flag_9weapons_open = 0 
flag_10weapons_open = 0
flag_11weapons_open = 0 
flag_12weapons_open = 0 
flag_13weapons_open = 0 
flag_14weapons_open = 0 
flag_15weapons_open = 0
flag_16weapons_open = 0

// ********************************************* FOR CRASH4 IN AMMUNATION *************************
VAR_INT flag_bought_gun_for_doberman
flag_bought_gun_for_doberman = 0

VAR_INT flag_on_doberman_mission
flag_on_doberman_mission = 0

VAR_INT flag_gang_war_active
flag_gang_war_active = 0

VAR_INT ammu_shop_blip[10] mod_garage_blips[3] barbers_blips[5] pizza_blips[8] burger_blips[10] chicken_blips[12] tattoo_blips[4] gym_blips[2] clothes_blips[17] spray_shop[6]


ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY spray_shop1
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 488.0 -1734.0 34.4 RADAR_SPRITE_SPRAY spray_shop2
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 720.016 -454.625 15.328 RADAR_SPRITE_SPRAY spray_shop4 //Badlands Countryeast
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -1420.547 2583.945 58.031 RADAR_SPRITE_SPRAY spray_shop[0]	//Desert CountryN
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 1966.532 2162.65 10.995 RADAR_SPRITE_SPRAY spray_shop[1] //North West Vegas
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2425.46 1020.83 49.39 RADAR_SPRITE_SPRAY spray_shop[2] //San Fran North
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 1021.8, -1018.7, 30.9 RADAR_SPRITE_SPRAY spray_shop[3] 
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -1908.9, 292.3, 40.0 RADAR_SPRITE_SPRAY spray_shop[4] 
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -103.6, 1112.4, 18.7 RADAR_SPRITE_SPRAY spray_shop[5] 
//ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2393.779 1481.181 12.551 RADAR_SPRITE_SPRAY spray_shop[5] //East Vegas

//ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2644.2524 -2028.2457 12.5547 RADAR_SPRITE_MOD_GARAGE mod_garage_blip
//ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 1043.4 -1025.3 34.4 RADAR_SPRITE_MOD_GARAGE mod_garage1

VAR_INT added_all_food_blips_before
added_all_food_blips_before = 0

VAR_INT added_all_clothes_blips_before
added_all_clothes_blips_before = 0


//2502.309814, -1699.359985, 12.432300 : HIDEOUT_ONE cjsafe	//CJ'S GARAGE
//DEACTIVATE_GARAGE LCKSfse //HIDEOUT_FIFTEEN //SAN FRAN HUB GARAGES
DEACTIVATE_GARAGE cn2gar2 //HIDEOUT_FIVE //PILOT SCHOOL



//ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 1043.4 -1025.3 34.4 RADAR_SPRITE_MOD_GARAGE mod_garage1

//ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 488.0 -1734.0 34.4 RADAR_SPRITE_TSHIRT spray_shop2 //Clothes shop
 
									  
/*
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 364.8 1051.5 21.0 RADAR_SPRITE_GUN weapon_shop2_blip
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD ammu3X ammu3Y ammu3Z RADAR_SPRITE_GUN weapon_shop3_blip
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD hard1X hard1Y hard1Z RADAR_SPRITE_HARDWARE hardware_shop1
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 364.8 1086.5 21.0 RADAR_SPRITE_HARDWARE hardware_shop2
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD hard3X hard3Y hard3Z RADAR_SPRITE_HARDWARE hardware_shop3

ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -3.8 -1265.8 12.0 RADAR_SPRITE_SPRAY spray_shop1 //South beach
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 319.0 441.3 12.0 RADAR_SPRITE_SPRAY spray_shop2 //Vice Point
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -903.0 -1261.1 12.0 RADAR_SPRITE_SPRAY spray_shop3 //Docks
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -876.2 -105.5 12.0 RADAR_SPRITE_SPRAY spray_shop5 //Main road haiti
*/
//CHANGE_BLIP_DISPLAY hotel_contact_blip BLIP_ONLY

// ***************************************POLICE AND HOSPITAL RESTARTS****************************************
//RESTARTS


//0: retart always works (LA)
//1: Only works when San Fran has been opened up
//2: Only works when the entire map is opened up.


// LA
ADD_HOSPITAL_RESTART 2027.77 -1420.52 15.99 137.0 0 // (LA EAST)
ADD_HOSPITAL_RESTART 1180.85 -1325.57 12.58 271.40 0 // (LA WEST)
ADD_POLICE_RESTART 1550.68 -1675.49 14.51 90.0 0 // (LA CENTRAL)


// BADLANDS
ADD_HOSPITAL_RESTART 1244.4370 331.2261 18.5547 7.5465 1 // (Badlands north east)
ADD_POLICE_RESTART 632.2344 -571.7104 15.3515 267.2000 1 // (Badlands north east)

ADD_HOSPITAL_RESTART -2199.7195 -2308.0750 29.6181 322.8928 1 // (Badlands south west)
ADD_POLICE_RESTART -2163.8286 -2387.8169 29.6250 134.2066 1 // (Badlands south west)


//SAN FRAN
ADD_HOSPITAL_RESTART -2670.2854 616.4364 13.4531 183.1042 1 // (San Fran north)
ADD_POLICE_RESTART -1605.7917 716.8598 11.0241 355.2978 1 // (San Fran)


// DESERT
ADD_HOSPITAL_RESTART -316.3832 1056.0450 18.7344 1.6017	2 // (Desert South east)
ADD_POLICE_RESTART -212.1889 979.4168 18.3219 278.0478 2 // (Desert South east)

ADD_HOSPITAL_RESTART -1514.8230 2527.1189 54.7443 2.3546 2 // (Desert North west)
ADD_POLICE_RESTART -1393.0723 2633.1162 54.9491 86.0424 2 // (Desert North west)

// VEGAS
ADD_HOSPITAL_RESTART 1578.4460 1770.6816 9.8358 99.7567 2 // (VEGAS)
ADD_POLICE_RESTART 2337.0833 2453.8018 13.9765 90.7643 2 // (VEGAS)


/*
SET_SHORTCUT_PICKUP_POINT 407.6 725.3 10.5 177.0 //Vice point hospital
SET_SHORTCUT_PICKUP_POINT -102.6 -947.7 9.6 192.7 //South Beach hospital
SET_SHORTCUT_PICKUP_POINT -784.4 1189.3 10.2 90.0 //Downtown hospital
SET_SHORTCUT_PICKUP_POINT -850.8 -483.9 10.2 189.8 //Havana hospital

SET_SHORTCUT_PICKUP_POINT 492.5 475.1 10.6 274.2 //Vice point Cop Shop
SET_SHORTCUT_PICKUP_POINT 418.5 -422.6 9.3 58.7 //South Beach Cop Shop
SET_SHORTCUT_PICKUP_POINT -665.3 793.7 10.5 180.0 //Downtown Cop Shop
SET_SHORTCUT_PICKUP_POINT -873.0 -641.8 10.5 97.1 //Havana Cop Shop
*/									



// ***************************************UNIQUE STUNT JUMPS****************************************

//Los Santos - Jump going east onto railway platform roof (possible in PCJ upwards.)
ADD_STUNT_JUMP (1939.1357 -1968.0520 17.6649) (2.5 2.7280 3.1700) (1989.9585 -1971.9513 28.7249) (11.1340 5.3980 7.2300) (1960.5447 -1963.5712 25.3229) 500

//Los Santos - Bridge structure going south, user will have to lift front wheel slightly on motorbike.
ADD_STUNT_JUMP (2209.2747 -1843.6862 19.0964) (2.5 4.2800 3.8300) (2215.1238 -1889.3594 17.7864) (15.3600 7.0100 11.0500) (2203.3396 -1861.5244 21.8367) 500

//Los Santos - Heading north using steps as jump, user can land on adjacsent building.
ADD_STUNT_JUMP (1177.2153 -1696.7614 21.3680) (2.2200 2.5 5.1000) (1177.9723 -1626.7192 32.8079) (18.2000 7.2300 12.3600) (1182.9198 -1659.4308 29.1115) 500

//Los Santos - North up multi-coloured ped walkway steps, possible on bike or car
ADD_STUNT_JUMP (2278.2896 -1357.8571 31.3255) (2.5 2.5 3.9600) (2275.2991 -1310.7556 30.9555) (10.6900 2.5000 9.3600) (2271.6812 -1331.2822 32.6621) 500

//Los Santos - Driving approximately SSE over the mound of dirt at these co-ords, the user should just clear the top of the palm tree in his/her path.
ADD_STUNT_JUMP (2335.3513 -2163.9470 18.5147) (3.5200 2.5 3.4400) (2344.4189 -2178.7029 24.2546) (15.6400 3.0500 11.9600) (2336.8899 -2190.3213 22.4292) 500

//Los Santos - Driving west up the steps here, just possible on a PCJ, easy on an NRG. User should clear wall behind steps.
ADD_STUNT_JUMP (2825.9329 -1582.6599 18.1260) (2.5 4.2200 4.9200) (2787.5039 -1569.3179 27.7760) (2.7040 19.1600 14.1500) (2805.1213 -1579.8921 20.7985) 500

//Los Santos - Driving South onto corrugated roof, possible in a variety of vehicles.
ADD_STUNT_JUMP (2460.1765 -2567.9106 18.8162) (2.5 2.5 3.6500) (2464.2058 -2597.4551 25.8862) (8.8900 0.8200 6.2600) (2454.8777 -2593.7869 23.9707) 500
 
//Los Santos - Use the boarding ramp at these co-ords to get over airport fence.
ADD_STUNT_JUMP (1832.4530 -2386.8049 17.9247) (2.5 2.5 3.0100) (1833.6118 -2372.3845 17.9247) (6.5700 0.5000 6.6500) (1840.2876 -2367.0955 19.4731)	500

//Los Santos - using the sign at these co-ords, going east, the user has to clear the concrete and the red sign.
ADD_STUNT_JUMP (1839.7450 -2514.4766 16.1769) (2.5 2.3400 2.7700) (1921.6793 -2517.8958 20.6069) (2.5120 6.4200 8.6999) (1879.7524 -2529.8940 22.7841) 500

//Los Santos - Using this ramp the user would have to make it onto the adacent building (heading west.)
ADD_STUNT_JUMP (2634.3975 -2107.9841 17.0887) (4.1720 2.5 5.0600) (2595.6458 -2109.0552 25.7387) (9.5920 4.1780 6.1000) (2608.7000 -2117.1738 21.6272) 500

//Los Santos - heading west up and over these steps, user should have to clear the railway tracks.
ADD_STUNT_JUMP (2352.3000 -1259.0685 28.9962) (2.5 2.5 2.7600) (2298.2036 -1257.6772 28.7062) (2.6200 20.0400 9.9600) (2312.8376 -1265.6143 37.7793) 500

//Los Santos - east over the steps and onto the road (over the roof.)
ADD_STUNT_JUMP (1013.1629 -1270.3624 24.4456) (2.0000 2.0000 4.0500) (1045.3594 -1273.4749 32.9636) (2.0000 14.3040 13.1400) (1037.3560 -1264.5017 25.3721) 500 

//San Fierro - If the user hits this banked bit of road/dirt, just slightly WNW, it's possible to land on the San Fierro airport runway area, Reminiscant of one of the GTA3 Shoreside jumps.
ADD_STUNT_JUMP (-973.3570 -223.2307 53.3717)  (5.3500 29.1299 15.9800) (-1116.0278 -211.0103 30.6016) (49.9398 32.2599 27.0399) (-1038.6666 -187.1254 54.0) 500

//San Fierro - User would have to clear the buildings to the south and land correctly.
ADD_STUNT_JUMP (-2637.0688 780.3949 50.0166)  (6.7600 2.5200 9.2900) (-2639.5774 733.2753 44.1565) (9.9100 2.5200 9.2900) (-2633.3594 757.9172 52.0809) 500

//San Fierro - User goes north over this grass verge landing safely.
ADD_STUNT_JUMP (-2520.1995 1169.2032 59.5525) (6.5400 3.0900 5.9700) (-2520.1995 1202.1201 53.4025) (33.5399 6.2100 17.6600) (-2513.7776 1193.9250 57.4115) 500

//San Fierro - Hit this set of steps going North, land on the building opposite the multi storey car park.
ADD_STUNT_JUMP (-1794.9822 1211.6116 35.6794) (3.0200 2.5800 4.7500) (-1795.8021 1258.1971 32.5394) (19.5100 5.1300 19.6900) (-1797.4895 1238.3950 37.9679) 500

//San Fierro - Run off this scaffolding north into the water (usually this would be a bad idea, but there's a beach nearby so the user won't have to swim too far to get out of the water.)
ADD_STUNT_JUMP (-2659.5508 1533.6698 53.2004) (2.5 2.6200 4.7300) (-2659.5508 1551.6447 45.5003) (7.2600 11.7900 18.5400) (-2658.4263 1531.6315 60.6476) 500

//San Fierro - East over these steps and at least onto the roof beyond.
ADD_STUNT_JUMP (-1690.6622 1110.3917 57.2776) (2.3000 2.9800 5.0800) (-1662.5118 1110.3917 57.2776) (2.3000 18.1200 12.2600) (-1649.6760 1114.1404 50.8559) 500 

//San Fierro - Out of the Los Cabras compound.
ADD_STUNT_JUMP (-2145.4607 -112.0259 44.3452) (7.2400 3.3000 5.4900) (-2146.8203 -89.4659 54.3752) (4.3600 3.3000 14.2800) (-2141.0510 -89.1471 45.6462) 500 

//Las Payasadas - Jump east over the massive chicken. Ace
ADD_STUNT_JUMP (-267.7739 2662.0359 68.7405)  (3.5300 4.0200 6.0000) (-237.1135 2661.9358 79.7505) (7.2800 12.0600 8.1400) (-253.1990 2654.0164 72.7989) 500

//Las Payasadas - hit these steps going southwards, land OK for success.
ADD_STUNT_JUMP (-249.6904 2590.8052 66.1194)  (3.6800 2.9600 3.9600) (-249.6904 2575.3252 66.1194) (12.6600 5.2100 9.6800) (-256.2141 2555.2065 64.5246) 500

//Arca Del Peste - Head west along the wooden path and over jump at end.
ADD_STUNT_JUMP (-871.5031 2308.7898 164.5739) (3.3900 2.8700 4.8600) (-981.0790 2300.9133 111.7538) (18.7700 40.0200 69.5898) (-905.3314 2315.0637 163.7206) 500

//Las Venturas - Jumping south out of the emerald isle multi storey and at least onto the roof of the souvenire shop (or beyond.)
ADD_STUNT_JUMP (2073.7126 2366.6531 50.8534) (5.5400 3.4500 2.1800) (2050.7300 2313.7092 31.8133) (29.6900 11.1900 26.0900) (2077.4861 2318.8723 26.9287) 500 

//Las Venturas - Jumping from the roof of the building eastward from this ramp and laning safely.
ADD_STUNT_JUMP (2170.9465 2402.6086 62.2905) (2.7800 3.5900 3.6200) (2183.6704 2402.6086 62.2905) (4.9200 9.2000 9.8000) (2232.1096 2387.5867 50.9115) 500 

//Las Venturas - Jumping east from here, user should land on the roof of the pawn shop to be succesful.
ADD_STUNT_JUMP (2418.0557 1353.2336 12.3641) (2.2000 5.0140 3.9800) (2481.9229 1346.3536 25.6640) (5.0100 19.9840 11.6300) (2462.3130 1362.7017 19.7672) 500 

//Las Venturas - Jumping North from here, user should land on top of building directly north to be succesful.
ADD_STUNT_JUMP (2407.3083 1364.3785 14.5734) (5.1600 2.7200 6.0000) (2399.1201 1386.5244 25.4934) (18.5700 4.6100 11.6200) (2412.7717 1392.4314 22.3043) 500 

//Las Venturas - Jump west here, user should land on at least northbound section of freeway.
ADD_STUNT_JUMP (2784.7629 2208.7197 15.1934) (3.0500 1.7600 4.3300) (2738.9844 2208.7197 21.8434) (3.2400 24.2300 16.1500) (2758.6555 2201.6653 18.3639) 500  

//Las Venturas - Jumping north here the user should land on a ledge on the building to the north.
ADD_STUNT_JUMP (1482.3834 2037.0040 17.0576) (2.0900 2.6900 5.5800) (1482.8750 2077.9778 23.8776) (3.2840 4.0900 7.0700) (1489.1245 2065.0237 20.6514) 500 

//Las Venturas - Heading east, user should make it over the freeway bridge from this ramp (only just possible in PCJ, faster bikes no problem.)
ADD_STUNT_JUMP (1976.3966 2593.6860 17.2126) (3.6700 2.0000 5.4400) (2041.1909 2593.6860 30.4425) (5.0500 32.8000 20.5000) (2007.6760 2596.3137 28.1338) 500  

//Las Venturas - North over this ramp, user should make it onto the west bound section of freeway.
ADD_STUNT_JUMP (1636.2639 2399.7122 15.8903) (2.5000 2.5000 5.0800) (1636.2639 2449.0708 15.8903) (21.3300 2.0 13.9500) (1642.1699 2440.2903 18.5118) 500 

//Las Venturas - east out of multistorey(10) carpark used in missapropiation (uber chase)
ADD_STUNT_JUMP (2119.2305 2385.2126 33.7094) (2.1000 3.3600 2.6000) (2151.8909 2385.6013 30.8958) (3.5100 13.4200 9.0400) (2128.1023 2392.0476 32.2357) 500

//Angel Pine - Jump NW on this ramp and land on the roof over the road for success.
ADD_STUNT_JUMP (-2082.5291 -2498.9316 33.9682) (2.3400 2.4000 3.5500) (-2110.0312 -2479.7092 40.8882) (4.4200 4.0800 5.9400) (-2101.4182 -2496.4680 37.1704) 500

//Red County - Jumping over this bridge east into town, suitable for any vehicle.
ADD_STUNT_JUMP (2112.4077 97.9000 39.9930) (5.6400 10.2800 8.0500) (2152.1167 91.3300 39.9930) (14.9500 26.9500 16.2200) (2149.9333 76.6566 44.9509) 500 

//Chiliad - Jumping off this wooden ramp at a decent speed.
ADD_STUNT_JUMP (-2100.4128 -1743.5951 199.7312) (2.9400 4.2700 4.9600) (-2082.4910 -1718.9235 199.7312) (10.8000 9.3900 17.6200) (-2096.1672 -1714.5718 201.0246) 500 

//Whetstone - jump east off this sand dune.
ADD_STUNT_JUMP (-2287.6707 -2800.7786 22.0541) (5.8100 13.6600 5.5700) (-2255.1331 -2800.7786 22.0541) (12.5600 28.6800 14.6900) (-2253.3186 -2814.6453 22.8247) 500 

//Verdant Meadows - Hit the plane's wing here going about Northeast, user should clear the plane to the northeast.
ADD_STUNT_JUMP (110.8902 2428.0488 23.7936) (3.5800 3.1500 4.2000) (132.6400 2445.7815 32.7336) (4.3800 16.6200 15.3500) (119.7758 2449.6401 27.7167) 500 

//Valle Ocultado - Hit the ramp against the wall of this shack, landing in the water should suffice, there is a beach nearby so CJ won't be swimming for long.
ADD_STUNT_JUMP (-894.4963 2693.1987 44.7303) (3.5800 2.6300 2.4400) (-941.4026 2662.9392 55.7302) (29.1400 16.0200 19.7400) (-931.5991 2647.9949 51.4501) 500 

//Blueberry - Hit the smashed pallet ramp landing safely on the other side.
ADD_STUNT_JUMP (89.9936 -243.2046 7.5581) (3.9600 4.4600 4.4800) (101.5435 -255.4345 7.5581) (7.3900 11.3100 14.9900) (87.0604 -266.0403 9.8153) 500 

//Blueberry - Hitting this set of stairs going east, the player sould clear the building he/she is propelled over.
ADD_STUNT_JUMP (160.7991 -160.2479 6.1453) (2.1200 1.4520 2.2300) (171.8147 -162.4050 12.3210) (2.0000 7.2000 7.1800) (183.2545 -151.3140 13.5961) 500 

//Blueberry - The user could jump NNE over the barn here, there may be a problem once the (somewhat illogically placed IMHO,) boulders in the farmyard get collision.
ADD_STUNT_JUMP (-72.3000 17.7 8.4132) (2.8100 2.4 4.4800) (-65.1154 35.7188 16.6432) (7.7100 2.0000 7.2700) (-70.4781 55.8289 15.0868) 500 

//Blueberry - User could jump NNE here also, probably best in a car because the landing usually involves a crash through a wooden fence.
ADD_STUNT_JUMP (-39.7989 53.3007 6.9223) (3.1500 2.7600 2.3500) (-36.4289 76.6106 6.9223) (12.5100 4.4800 9.6700) (-42.5507 77.3671 7.8899) 500 

//Dillimore - South over this ramp, landing at least in front of the gas station.
ADD_STUNT_JUMP (638.7504 -520.0714 18.8519) (2.0000 2.1500 2.3400) (639.8602 -545.0903 22.7619) (10.9700 3.0100 8.2600) (629.1661 -542.9603 24.9594) 500 

//Las Venturas - very south vegas out of warehouse compound
ADD_STUNT_JUMP (1749.7230 779.6028 13.4757) (1.6800 2.6160 2.1640) (1749.7230 825.0914 14.2657) (10.7180 2.6160 5.8240) (1744.0463 799.4149 12.6554) 500

//over trailers from pallett ramp
ADD_STUNT_JUMP (110.2446 -257.9929 6.2739) (3.3600 3.3200 2.9300) (92.4846 -245.6928 11.0439) (8.4300 6.9200 3.9900) (92.9552 -254.6195 10.1539) 500

//Red County - over broken bridge for catalina chase in bank job
ADD_STUNT_JUMP (2125.6423 95.2542 37.3959) (3.8300 4.0900 2.5700) (2098.5098 99.5841 35.8559) (3.8300 8.4600 5.7200) (2116.3931 88.0306 40.2113) 500

//The Panicoption - short jump over ravine
ADD_STUNT_JUMP (-344.6466 -9.8812 44.9609) (14.2600 3.4500 3.8000) (-336.1166 28.7688 37.7609) (16.0900 4.5500 6.2900) (-358.2565 15.6353 41.7241) 500

//San Fierro - big jump near golden gate bridge
ADD_STUNT_JUMP (-2589.1804 1164.3700 59.8256) (2.8400 2.7700 2.2300) (-2567.1250 1231.1827 47.0362) (18.9000 9.0400 6.0500) (-2576.0596 1209.2253 53.5490) 500

//San Fierro - above tunnel near bridge
ADD_STUNT_JUMP (-2292.0442 1097.7101 84.7347) (2.0820 2.0740 2.0200) (-2350.5911 1101.9282 64.1846) (4.5220 14.2040 9.0300) (-2323.7307 1106.6652 77.1770) 500

//San Fierro - supermarket carpark
ADD_STUNT_JUMP (-2408.2852 723.2977 38.6835) (2.6200 2.5000 1.6900) (-2408.2852 723.2977 38.6835) (22.4100 2.5800 4.0600) (-2407.9814 716.5809 38.4334) 500

//San Fierro - stairs at top of windy road
ADD_STUNT_JUMP (-2116.3557 925.6832 87.8190) (2.6800 1.9900 2.4800) (-2069.5649 925.4330 76.6890) (2.6800 15.2600 14.4600) (-2085.6289 935.5674 85.4677) 500

//San Fierro - onto highway near building site
ADD_STUNT_JUMP (-1903.1918 223.3468 37.7965) (1.7500 1.8400 1.7000) (-1874.3824 223.3467 42.4064) (1.7500 8.1300 4.6700) (-1897.0392 219.1329 39.9110) 500

//San Fierro - out of airport onto docks
ADD_STUNT_JUMP (-1687.3230 -162.2010 17.8603) (2.6800 3.1900 2.2000) (-1722.5374 -126.3910 14.9903) (9.0900 7.1300 13.0700) (-1716.8781 -142.6182 14.6114) 500

//San Fierro - out of airport west
ADD_STUNT_JUMP (-1729.9243 -364.5276 18.2246) (2.2680 2.5100 2.1480) (-1777.4872 -364.2776 17.9446) (2.2680 15.3300 7.0680) (-1745.8403 -355.4089 14.2197) 500

//San Fierro - from stadium carpark onto freeway
ADD_STUNT_JUMP (-1972.0991 -543.0899 38.9862) (1.9600 2.0100 1.7700) (-1912.3181 -543.0899 45.3462) (7.5800 14.7800 8.6900) (-1942.9154 -539.1157 40.2453) 500

//Los santos - near from cliff above burglary house
ADD_STUNT_JUMP (2770.2141 -1177.4768 70.7527) (2.3440 1.9900 2.1720) (2814.4675 -1182.9965 55.7938) (5.9400 17.3800 31.5200) (2766.0310 -1184.6915 74.9193) 500

//Los santos - docks up stairs
ADD_STUNT_JUMP (2248.8379 -2463.2581 15.1049) (5.1800 2.2600 2.5900) (2247.4675 -2432.4275 21.8068) (12.4400 2.2600 9.3200) (2240.7571 -2446.2014 16.8109) 500

//Los santos - docks up stairs
ADD_STUNT_JUMP (2248.8320 -2627.6233 15.1806) (4.8620 2.1440 2.5560) (2248.8320 -2655.4978 21.2585) (8.6800 2.1440 8.4980) (2237.6948 -2650.7676 16.3791) 500

//Los santos - Steps at basketball courts
ADD_STUNT_JUMP (2326.1216 -1508.7510 27.0066) (2.2420 1.7280 2.0800) (2286.8135 -1508.7317 30.3748) (3.8600 9.3800 8.9900) (2299.4038 -1514.5957 29.8683) 500

//Los santos - centre north los santos along high freeway heading north
ADD_STUNT_JUMP (1623.5740 -1110.9561 61.8178) (1.6560 2.0140 1.8360) (1623.5740 -1079.2654 61.8178) (17.2460 2.0140 22.1459) (1615.6329 -1093.7998 64.8724) 500

//Los santos - centre north los santos along high freeway heading south
ADD_STUNT_JUMP (1679.6456 -962.2209 65.6737) (1.6940 2.4900 1.9400) (1679.6456 -988.7401 65.6737) (13.1240 2.4900 16.7400) (1683.8813 -977.1464 65.7485) 500

//Los santos - centre north los santos underneath freeway into carpark
ADD_STUNT_JUMP (1667.1671 -992.8579 32.7591) (6.3200 3.7680 2.4800) (1675.8049 -1008.2971 32.7591) (18.7479 2.4380 9.7900) (1689.1560 -1013.6305 35.6715) 500

//Los santos - south west los santos off grassy ramp onto pier bridge.
ADD_STUNT_JUMP (347.0000 -1662.2990 35.8218) (3.1200 2.6500 2.6100) (351.6596 -1700.2644 35.8218) (19.9100 4.1300 29.7600) (337.9746 -1676.9927 36.7335) 500

//Los santos - off building onto sunset road
ADD_STUNT_JUMP (743.1525 -1132.8186 25.7991) (1.8340 3.6060 2.3500) (710.0014 -1132.9885 25.7991) (1.8340 11.0960 10.0000) (717.1129 -1136.5488 25.5161) 500

//Los santos - out of storm drains
ADD_STUNT_JUMP (1395.5621 -1460.5657 12.3906) (1.6720 1.9700 1.7500) (1402.9596 -1447.4658 17.7266) (10.0620 1.9700 5.1500) (1395.0233 -1450.8358 17.1621) 500

//Los santos - end of freeway heading west at the montgomery intersection
ADD_STUNT_JUMP (1586.4889 312.8876 23.9965) (2.1500 6.3800 1.9900) (1540.2079 325.3573 28.9665) (2.1500 32.8899 11.3700) (1562.9119 331.3251 23.6496) 500

//Los santos - south between airport & sea on freeway near underpass
ADD_STUNT_JUMP (2058.1001 -2677.5168 15.4946) (3.1000 2.4800 2.6000) (2009.2607 -2677.5168 20.8946) (1.9300 16.5499 11.2600) (2036.5663 -2685.2805 13.2053) 500

//Los santos - South LA at the beach up steps under promenade
ADD_STUNT_JUMP (586.0102 -1773.7693 15.2312) (2.0000 2.2700 1.7800) (627.7189 -1781.1187 22.6912) (2.0000 7.0600 9.9400) (598.5319 -1778.4811 17.0529) 500

//Las Venturas - off missapropriation roof
ADD_STUNT_JUMP (2172.0779 2430.5071 61.7470) (2.1200 3.3000 2.3500) (2191.5918 2436.3044 53.0570) (2.5000 12.5700 25.2200) (2227.2341 2436.8850 37.7880) 500

//Las Venturas - Under power lines over the strip
ADD_STUNT_JUMP (2176.1326 2268.1958 13.4683) (2.2860 1.9340 1.9380) (2137.2717 2268.2456 16.7183) (2.2860 10.5540 6.4580) (2148.6008 2264.8921 13.6634) 500

//San Fierro - Jump the freeway. One ramp of the overpass to the other on a bike.
ADD_STUNT_JUMP (-2213.7732 -311.0786 38.5339) (1.1500 3.0400 2.5900) (-2213.7732 -327.8282 44.6738) (10.3740 3.0400 5.9300) (-2215.9768 -325.7729 42.5119) 500


//Las Venturas - onto airport hangar roof
//A/DD_STUNT_JUMP (1584.1844 1118.5709 13.6009) (2.1000 2.2900 2.2400) (1585.0873 1149.7214 19.6745) (17.5800 2.2000 10.2380) (1581.0806 1136.2915 19.4338) 500
//Los santos - East footbridge over train tracks, steps on the right
//A/DD_STUNT_JUMP (2295.6306 -1356.5637 30.9933) (1.9100 4.1300 2.6500) (2295.6306 -1312.9896 34.1733) (17.0000 2.6300 11.0800) (2288.0129 -1329.0284 27.4067) 500
//East up the down escalator, possible to clear the roof
//A/DD_STUNT_JUMP (1112.0548 -1618.9945 23.2458) (2.4000 2.5 5.2400) (1132.5320 -1620.1743 25.5957) (4.6900 16.9200 9.0700) (1125.0527 -1625.7938 24.4827) 500
//Directly south over the mound leading to the basket ball court, user would have to land safeley on freeway. Should be possible in either car or bike.
//A/DD_STUNT_JUMP (2281.8477 -1509.7455 28.7425) (6.0700 3.2500 3.9900) (2281.7976 -1577.8739 28.7425) (36.0299 27.2400 11.9100) (2270.1919 -1550.0366 30.9957) 500
//If the user hits this ramp correctly they will clear the bridge and the airport fence.
//A/DD_STUNT_JUMP (-1516.8522 -566.0588 13.9385) (4.4440 5.4900 5.4800) (-1600.6017 -550.1583 13.9385) (21.3940 16.0200 20.0300)  (-1560.1888 -562.7290 25.3578) 500
//Get a run up towards this set of stairs and clear the building, landing near the multi storey car park.
//A/DD_STUNT_JUMP (-1794.4597 1208.8374 34.4865) (3.2900 2.7600 5.7400) (-1791.6495 1233.4645 43.4064) (10.2200 9.1000 16.9600) (-1783.6460 1230.5397 34.4055) 500
//Get a good run up along the road, head for the broken piece of road and jump off the right hand side to land on the freeway (to the right, not on the broken road.)
//shit one (on an angle - triggered from both directions)
//A/DD_STUNT_JUMP (-1771.8066 503.6432 46.1222)  (7.6100 12.1300 7.5800) (-1722.9945 518.6924 53.5521) (13.2400 22.0500 21.0500) (-1729.4946 502.8332 40.6866) 500
//X:-1794 Y:1199 Z:25 - North over the steps.
//A/DD_STUNT_JUMP (-1794.6830 1210.1572 34.4895) (2.6600 2.0000 3.3700) (-1789.4122 1231.6329 38.7795) (9.9700 2.0000 7.0000) (-1797.4153 1238.2430 34.3920) 500
//jump North here, user should make it to the second warehouse roof to be succesful.
//A/DD_STUNT_JUMP (1423.1846 1678.1962 20.2303) (7.4300 3.6300 8.2300) (1418.4126 1747.8409 41.0103) (14.0900 4.5300 25.3900) (1411.9707 1727.8572 20.8908) 500 
//(JW) Jumping north from here the user can clear the canyon (run up through the archway to the south.)
//A/DD_STUNT_JUMP (-1992.0266 -1447.9917 96.6975) (22.9599 15.4500 13.0200) (-1973.9852 -1338.4615 51.0276) (34.7998 14.0399 17.7900) (-1940.2611 -1379.0653 86.3168) 500 
//vegas airport over fence
//A/DD_STUNT_JUMP (1568.8297 1648.5426 15.1416) (2.2700 2.3840 2.1200) (1577.1488 1705.5762 22.0215) (19.4600 2.3840 7.2400) (1586.8275 1688.4302 19.4262) 500
//sanfran jump into airport from docks
//A/DD_STUNT_JUMP (-1739.8580 -168.6013 7.2647) (2.1800 2.2000 1.8100) (-1715.9192 -225.4707 19.3046) (10.4700 8.5900 6.3600) (-1721.0516 -189.0959 15.5977) 500
//short jump over ravine (the panicoption)
//A/DD_STUNT_JUMP (-619.5474 203.3859 28.5974) (4.0200 6.6600 2.3500) (-639.3463 179.9758 29.1074) (8.9400 9.3300 7.5500) (-628.8107 206.7740 28.3064) 500
//san fran over bridge on road into airport
//A/DD_STUNT_JUMP (-1577.0126 -478.5486 10.7547) (2.6400 2.5200 2.4400) (-1560.0413 -459.0581 17.2947) (9.9500 11.2300 4.2000) (-1572.9957 -468.5905 14.9977) 500
//sanfran jump over pier 69
//A/DD_STUNT_JUMP (-1671.3883 1317.8365 11.1095) (2.4100 2.5560 1.9140) (-1688.0160 1335.4849 21.2695) (4.2900 4.0160 4.9440) (-1679.6945 1348.7806 21.5220) 500
////Los Santos Heading West away from grove street - house driveway
//A/DD_STUNT_JUMP (1982.8108 -1622.8293 17.1587) (2.4400 2.8600 2.1500) (1946.5498 -1622.8293 19.6087) (2.4400 9.8400 7.2500)	(1957.8710 -1625.4438 18.5694) 500
////Los Santos Heading East towards grove street - house driveway
//A/DD_STUNT_JUMP (1960.6704 -1622.8293 17.1587) (2.4400 2.8600 2.1500) (1996.8063 -1622.8293 19.6087) (2.4400 9.8400 7.2500) (1984.0558 -1625.8560 18.6816) 500


// **************************************GLOBAL ZONES********************************************************

// these are using old Washington values
	VAR_INT init_ped_density

	init_ped_density = 50

  		
// ****************************VICECITY SWITCHED OFF ROAD AND PED NODES*********************

// ped nodes
/*
SWITCH_PED_ROADS_OFF -956.0 -355.0 5.0 -898.0 -328.0 25.0  // El Banco Corrupto Grande
SWITCH_PED_ROADS_OFF 411.0 -600.0 -10.0 451.0 -553.0 30.0 //Ocean drive back alleyway (Middle)
SWITCH_PED_ROADS_OFF 450.0 -509.0 -10.0 472.0 -489.0 30.0 //Ocean drive back alleyway (North)
// car nodes

SWITCH_ROADS_OFF -583.92 1371.84 8.0 -383.40 1531.17 25.0 // Dirtbike track
SWITCH_ROADS_OFF 86.0 250.0 15.0 -100.0 281.0 30.0 // entrance to golf club
SWITCH_ROADS_OFF 411.0 -600.0 -10.0 451.0 -553.0 30.0 //Ocean drive back alleyway (Middle)
*/

// ***************************************** GANGS*******************************************
/*
SET_GANG_CAR_MODEL GANG_CUBAN cuban 
SET_GANG_CAR_MODEL GANG_HAITIAN voodoo 
SET_GANG_CAR_MODEL GANG_STREET gangbur  
SET_GANG_CAR_MODEL GANG_BIKER angel

SET_GANG_ATTACK_PLAYER_WITH_COPS GANG_SECURITY TRUE
SET_GANG_WEAPONS GANG_CUBAN WEAPONTYPE_PISTOL WEAPONTYPE_UNARMED 
SET_GANG_WEAPONS GANG_HAITIAN WEAPONTYPE_PISTOL WEAPONTYPE_UNARMED  
SET_GANG_WEAPONS GANG_STREET WEAPONTYPE_PISTOL WEAPONTYPE_UNARMED
SET_GANG_WEAPONS GANG_DIAZ WEAPONTYPE_PISTOL WEAPONTYPE_PISTOL
SET_GANG_WEAPONS GANG_SECURITY WEAPONTYPE_PISTOL WEAPONTYPE_PISTOL
SET_GANG_WEAPONS GANG_BIKER WEAPONTYPE_PISTOL WEAPONTYPE_UNARMED			
SET_GANG_WEAPONS GANG_PLAYER WEAPONTYPE_PISTOL WEAPONTYPE_PISTOL 

SET_THREAT_FOR_PED_TYPE PEDTYPE_GANG_CUBAN THREAT_GANG_HAITIAN
SET_THREAT_FOR_PED_TYPE PEDTYPE_GANG_HAITIAN THREAT_GANG_CUBAN
*/

// ****************************************OBJECTS***********************************************

// ****** San Fran Hub Hole **********
//VAR_INT san_fran_hub_hole                         
//CREATE_OBJECT_NO_OFFSET Hubhole1_SFSe -2049.1714 250.3193 34.4770 san_fran_hub_hole
//DONT_REMOVE_OBJECT san_fran_hub_hole
/*
VAR_INT	mexican_gate_left mexican_gate_right
CREATE_OBJECT_NO_OFFSET comp_wood_gate 907.464 -1686.79 12.523 mexican_gate_left 
SET_OBJECT_HEADING mexican_gate_left 0.0
SET_OBJECT_COLLISION_DAMAGE_EFFECT mexican_gate_left FALSE // gate cannot be broken
DONT_REMOVE_OBJECT mexican_gate_left

CREATE_OBJECT_NO_OFFSET comp_wood_gate 907.464 -1695.647 12.523 mexican_gate_right 
SET_OBJECT_HEADING mexican_gate_right 180.0
SET_OBJECT_COLLISION_DAMAGE_EFFECT mexican_gate_right FALSE // gate cannot be broken
DONT_REMOVE_OBJECT mexican_gate_right

created_gates_1stime_riot3 = 1
*/

//Door used for fake garage at Four Dragons Casino in vegas.
VAR_INT fourdragons_door
CREATE_OBJECT_NO_OFFSET trdcsgrgdoor_lvs 1903.383 967.62 11.438 fourdragons_door // 11.438Safe room door
SET_OBJECT_HEADING fourdragons_door 0.0
DONT_REMOVE_OBJECT fourdragons_door

VAR_INT advert_f1
CREATE_OBJECT_NO_OFFSET md_poster 2167.82 -1518.193 20.237 advert_f1
SET_OBJECT_HEADING advert_f1 0.0
DONT_REMOVE_OBJECT advert_f1													


// small town bank doors - palomino creek
VAR_INT pc_door[4]
CREATE_OBJECT_NO_OFFSET CR_DOOR_01 2322.845 8.304 25.483 pc_door[0]
set_object_collision_damage_effect pc_door[0] FALSE
FREEZE_OBJECT_POSITION pc_door[0] TRUE 
CREATE_OBJECT_NO_OFFSET CR_DOOR_01 2316.233 0.712 25.742 pc_door[1]
SET_OBJECT_HEADING pc_door[1] 270.0
FREEZE_OBJECT_POSITION pc_door[1] TRUE 
set_object_collision_damage_effect pc_door[1] FALSE
CREATE_OBJECT_NO_OFFSET CR_DOOR_03 2304.257 -17.744 25.742 pc_door[2]
FREEZE_OBJECT_POSITION pc_door[2] TRUE 
set_object_collision_damage_effect pc_door[2] FALSE
CREATE_OBJECT_NO_OFFSET CR_DOOR_03 2304.257 -14.583 25.742 pc_door[3]
FREEZE_OBJECT_POSITION pc_door[3] TRUE 
SET_OBJECT_HEADING pc_door[3] 180.0
set_object_collision_damage_effect pc_door[3] FALSE


//Door for casino3.sc	
VAR_INT ca3_loadingbay
CREATE_OBJECT_NO_OFFSET shutter_vegas 1055.629 2087.67 12.469 ca3_loadingbay
DONT_REMOVE_OBJECT ca3_loadingbay


// Door for Burning Desire house
VAR_INT g_BD_DOOR
CREATE_OBJECT CR1_DOOR 2352.8512 -1171.0266 26.9669 g_BD_DOOR
SET_OBJECT_HEADING g_BD_DOOR 90.0000 
SET_OBJECT_DYNAMIC g_BD_DOOR FALSE
SET_OBJECT_COLLISION_DAMAGE_EFFECT g_BD_DOOR FALSE
SET_OBJECT_PROOFS g_BD_DOOR TRUE TRUE TRUE TRUE TRUE
DONT_REMOVE_OBJECT g_BD_DOOR




// china town gate
VAR_INT	china_town_gate
CREATE_OBJECT_NO_OFFSET ct_gatexr -2179.353 661.232 50.214 china_town_gate
SET_OBJECT_COLLISION_DAMAGE_EFFECT china_town_gate FALSE
DONT_REMOVE_OBJECT china_town_gate

//area51 blast doors
VAR_INT frontdoorr_a51
VAR_INT frontdoorl_a51
VAR_INT trapdoor_a51
VAR_INT launchdoor_a51
CREATE_OBJECT_NO_OFFSET a51_blastdoorR 215.941 1874.571 13.903 frontdoorr_a51
SET_OBJECT_HEADING frontdoorr_a51 0.0
CREATE_OBJECT_NO_OFFSET a51_blastdoorL 211.842 1874.571 13.903 frontdoorl_a51
SET_OBJECT_HEADING frontdoorl_a51 0.0
DONT_REMOVE_OBJECT frontdoorr_a51
DONT_REMOVE_OBJECT frontdoorl_a51
CREATE_OBJECT_NO_OFFSET a51_labdoor	297.766 1842.618 6.764 trapdoor_a51
DONT_REMOVE_OBJECT trapdoor_a51
CREATE_OBJECT a51_jetdoor 268.664 1884.06 15.925 launchdoor_a51
ROTATE_OBJECT launchdoor_a51 90.0 90.0 FALSE
DONT_REMOVE_OBJECT launchdoor_a51

//san fran hub garage 
CHANGE_GARAGE_TYPE hbgdSFS GARAGE_FOR_SCRIPT_TO_OPEN_AND_CLOSE

CHANGE_GARAGE_TYPE Ghostdr GARAGE_FOR_SCRIPT_TO_OPEN_AND_CLOSE

// portacabins
//ALLOCATE_SCRIPT_TO_OBJECT porta_cabin PORTAKABIN 100
// portakabins for construction site - removed after GARAGE2.sc (Demolition Mission)  
VAR_INT init_portakabin[6]
VAR_FLOAT init_portakabin_x[6] init_portakabin_y[6] init_portakabin_z[6] init_portakabin_h[6]
VAR_INT portakabin_num

init_portakabin_x[0] = -2108.0000 
init_portakabin_y[0] = 155.0000 
init_portakabin_z[0] = 34.0488 
init_portakabin_h[0] = 178.0000

init_portakabin_x[1] = -2089.0000  
init_portakabin_y[1] = 172.0000 
init_portakabin_z[1] = 34.0488 
init_portakabin_h[1] = 267.0000

init_portakabin_x[2] = -2069.0000  
init_portakabin_y[2] = 229.0000 
init_portakabin_z[2] = 35.0215 
init_portakabin_h[2] = 270.0000

init_portakabin_x[3] = -2077.0000  
init_portakabin_y[3] = 271.0000 
init_portakabin_z[3] = 33.7475 
init_portakabin_h[3] = 0.0000

init_portakabin_x[4] = -2096.0000 
init_portakabin_y[4] = 261.0000 
init_portakabin_z[4] = 34.5808 
init_portakabin_h[4] = 264.0000

init_portakabin_x[5] = -2129.0000 
init_portakabin_y[5] = 306.0000 
init_portakabin_z[5] = 33.7245 
init_portakabin_h[5] = 0.0000

portakabin_num = 0
WHILE portakabin_num < 6
	CREATE_OBJECT portakabin init_portakabin_x[portakabin_num] init_portakabin_y[portakabin_num] init_portakabin_z[portakabin_num] init_portakabin[portakabin_num]
	SET_OBJECT_HEADING init_portakabin[portakabin_num] init_portakabin_h[portakabin_num]
	SET_OBJECT_DYNAMIC init_portakabin[portakabin_num] FALSE
	DONT_REMOVE_OBJECT init_portakabin[portakabin_num]
portakabin_num++
ENDWHILE

//////DOOR FOR STORM FREIGHTER MISSION
VAR_INT w5_storm_freighter_door
CREATE_OBJECT freight_SFW_door -2468.141 1547.938 22.7 w5_storm_freighter_door
DONT_REMOVE_OBJECT w5_storm_freighter_door

//////Keycode door inside Mafia Casino
VAR_INT h9_door_c
CREATE_OBJECT_NO_OFFSET ab_casdorLok 2168.6438 1619.4300 1000.3000 h9_door_c
SET_OBJECT_AREA_VISIBLE h9_door_c 1
SET_OBJECT_HEADING h9_door_c 270.0000
DONT_REMOVE_OBJECT h9_door_c

//TRIP SKIP VARIABLE FOR SMOKE1
VAR_INT tw7_trip_skip_flag[2]
tw7_trip_skip_flag[0] = 0
tw7_trip_skip_flag[1] = 0


///////doors for riot2 mission
VAR_INT riot2_door[15] 

//lookouts house 0 - no rotation
CREATE_OBJECT ad_flatdoor 1833.36 -1995.45 12.5 riot2_door[0]
SET_OBJECT_HEADING riot2_door[0] 90.0
DONT_REMOVE_OBJECT riot2_door[0]  

//lookouts house 1 - no rotation 
CREATE_OBJECT ad_flatdoor 1819.81 -1994.66 12.5 riot2_door[1]
DONT_REMOVE_OBJECT riot2_door[1]  

//lookouts house 2
CREATE_OBJECT ad_flatdoor 1827.68 -1980.0 12.5 riot2_door[2]
SET_OBJECT_HEADING riot2_door[2] 270.0 
DONT_REMOVE_OBJECT riot2_door[2]  

//NW house 0 - no rotation 
CREATE_OBJECT ad_flatdoor 1851.84 -1990.67 12.5 riot2_door[3]
DONT_REMOVE_OBJECT riot2_door[3]  

//NW house 1     
CREATE_OBJECT ad_flatdoor 1867.29 -1984.96 12.5 riot2_door[4]
SET_OBJECT_HEADING riot2_door[4] 270.0 
DONT_REMOVE_OBJECT riot2_door[4]  

//NW house 2 - no rotation 
CREATE_OBJECT ad_flatdoor 1866.52 -1998.53 12.5 riot2_door[5]
SET_OBJECT_HEADING riot2_door[5] 90.0 
DONT_REMOVE_OBJECT riot2_door[5]  

//NE house 0
CREATE_OBJECT ad_flatdoor 1899.75 -1984.95 12.5 riot2_door[6]
SET_OBJECT_HEADING riot2_door[6] 270.0 
DONT_REMOVE_OBJECT riot2_door[6]  

//NE house 1  - no rotation  
CREATE_OBJECT ad_flatdoor 1914.39 -1992.82 12.5 riot2_door[7]
SET_OBJECT_HEADING riot2_door[7] 180.0 
DONT_REMOVE_OBJECT riot2_door[7]  

//NE house 2 - no rotation  
CREATE_OBJECT ad_flatdoor 1899.01 -1998.5 12.5 riot2_door[8]
SET_OBJECT_HEADING riot2_door[8] 90.0 
DONT_REMOVE_OBJECT riot2_door[8]  

//SE house 0 - no rotation 
CREATE_OBJECT ad_flatdoor 1900.89 -2020.11 12.5 riot2_door[9]
DONT_REMOVE_OBJECT riot2_door[9]  

//SE house 1 - no rotation 
CREATE_OBJECT ad_flatdoor 1914.4 -2020.91 12.5 riot2_door[10]
SET_OBJECT_HEADING riot2_door[10] 180.0 
DONT_REMOVE_OBJECT riot2_door[10]  

//SE house 2 - no rotation 
CREATE_OBJECT ad_flatdoor 1906.54 -2035.52 12.5 riot2_door[11]
SET_OBJECT_HEADING riot2_door[11] 90.0 
DONT_REMOVE_OBJECT riot2_door[11]  

//SW house 0 - no rotation 
CREATE_OBJECT ad_flatdoor 1851.86 -2020.14 12.5 riot2_door[12]
DONT_REMOVE_OBJECT riot2_door[12]  

//SW house 1 - no rotation
CREATE_OBJECT ad_flatdoor 1865.42 -2020.89 12.5 riot2_door[13]
SET_OBJECT_HEADING riot2_door[13] 180.0 
DONT_REMOVE_OBJECT riot2_door[13]  

//SW house 2 - no rotation 
CREATE_OBJECT ad_flatdoor 1857.55 -2035.52 12.5 riot2_door[14]
SET_OBJECT_HEADING riot2_door[14] 90.0 
DONT_REMOVE_OBJECT riot2_door[14]  





// BRICK WALL FOR FINALE
VAR_INT carterwall
CREATE_OBJECT_NO_OFFSET imy_shash_wall 2522.008 -1272.93 35.609 carterwall
SET_OBJECT_COLLISION_DAMAGE_EFFECT carterwall FALSE
FREEZE_OBJECT_POSITION carterwall TRUE
DONT_REMOVE_OBJECT carterwall



//VAR_INT cement_hole // used in the building site in san fran

// ***********************************************************************************
//
//					   			CRANE STUFF - Neil
//										 _____
//										/     \
//					o===================       =======[][]=
//						 |				\_____/	      [][]
//						 |				  IxI
//						 |				  IxI
//						 |				  IxI
//						[ ]				  IxI
//										  IxI
//										  IxI
//										  IxI
//										  IxI
//										 [	 ]
//
// ***********************************************************************************
	
	// SF BUILDING SITE CRANE --------------------------- 
	VAR_INT sf_crane1_base sf_crane1_arm sf_crane1_trolley
	VAR_INT sf_crane1_base_LOD sf_crane1_arm_LOD 
	// main bits
	CREATE_OBJECT_NO_OFFSET TWRCRANE_M_04 -2080.441 256.015 66.869 sf_crane1_base
	DONT_REMOVE_OBJECT sf_crane1_base
	CREATE_OBJECT_NO_OFFSET TWRCRANE_M_01 -2080.441 256.007 99.408 sf_crane1_arm
	DONT_REMOVE_OBJECT sf_crane1_arm
	CREATE_OBJECT_NO_OFFSET TWRCRANE_M_02 -2080.441 296.46 102.861 sf_crane1_trolley
	DONT_REMOVE_OBJECT sf_crane1_trolley
	// lods
	GET_OBJECT_COORDINATES sf_crane1_base x y z
	GET_OBJECT_HEADING sf_crane1_base heading 
	CREATE_OBJECT_NO_OFFSET LODCrane_M_04 x y z sf_crane1_base_LOD
	SET_OBJECT_HEADING sf_crane1_base_LOD heading
	DONT_REMOVE_OBJECT sf_crane1_base_LOD
	GET_OBJECT_COORDINATES sf_crane1_arm x y z
	GET_OBJECT_HEADING sf_crane1_arm heading 
	CREATE_OBJECT_NO_OFFSET LODCrane_M_01 x y z sf_crane1_arm_LOD
	SET_OBJECT_HEADING sf_crane1_arm_LOD heading
	DONT_REMOVE_OBJECT sf_crane1_arm_LOD
	CONNECT_LODS sf_crane1_base sf_crane1_base_LOD
	CONNECT_LODS sf_crane1_arm 	sf_crane1_arm_LOD 
																								   
	// MAGNO CRANE -----------------------------------------
	VAR_INT magno_base magno_cabin magno_arm 	
	VAR_INT magno_base_LOD magno_cabin_LOD magno_arm_LOD	
	VAR_FLOAT magno_base_x magno_base_y magno_base_z
	VAR_FLOAT magno_cabin_h magno_arm_h magno_arm_rotate_y
	// main bits
	CREATE_OBJECT_NO_OFFSET MAGNOCRANE_01 -1547.9777 123.9883 26.9332 magno_base
	SET_OBJECT_HEADING magno_base 45.0
	DONT_REMOVE_OBJECT magno_base
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS magno_base 0.0 0.0 0.0 x y z
	SET_OBJECT_VELOCITY magno_base 0.0 0.0 0.0
	CREATE_OBJECT_NO_OFFSET MAGNOCRANE_02 x y z magno_cabin
	DONT_REMOVE_OBJECT magno_cabin 
	SET_OBJECT_HEADING magno_cabin 140.0
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS magno_cabin 0.0 -2.185 8.51 x y z
	GET_OBJECT_HEADING magno_cabin heading
	CREATE_OBJECT_NO_OFFSET MAGNOCRANE_03 x y z magno_arm
	SET_OBJECT_ROTATION magno_arm 15.0 0.0  heading
	DONT_REMOVE_OBJECT magno_arm
	// lods
	GET_OBJECT_COORDINATES magno_base x y z
	GET_OBJECT_HEADING magno_base heading
	CREATE_OBJECT_NO_OFFSET LODnoCrane_01 x y z magno_base_LOD
	SET_OBJECT_HEADING magno_base_LOD heading
	DONT_REMOVE_OBJECT magno_base_LOD

	GET_OBJECT_COORDINATES magno_cabin x y z
	GET_OBJECT_HEADING magno_cabin heading
	CREATE_OBJECT_NO_OFFSET LODnoCrane_02 x y z magno_cabin_LOD
	SET_OBJECT_HEADING magno_cabin_LOD heading
	DONT_REMOVE_OBJECT magno_cabin_LOD 

	GET_OBJECT_COORDINATES magno_arm x y z
	GET_OBJECT_HEADING magno_arm heading
	CREATE_OBJECT_NO_OFFSET LODnoCrane_03 x y z magno_arm_LOD
	SET_OBJECT_HEADING magno_arm_LOD heading
	SET_OBJECT_ROTATION magno_arm_LOD 15.0 0.0  heading
	DONT_REMOVE_OBJECT magno_arm_LOD

	CONNECT_LODS magno_base   magno_base_LOD 
	CONNECT_LODS magno_cabin  magno_cabin_LOD
	CONNECT_LODS magno_arm 	  magno_arm_LOD 	


	// quarry crane
	VAR_FLOAT arm_rotate_y
	VAR_INT quarry_base quarry_stand quarry_arm
	CREATE_OBJECT_NO_OFFSET QUARRY_CRANEBASE 709.45 915.93 -19.66 quarry_base
	SET_OBJECT_HEADING quarry_base 135.0
	DONT_REMOVE_OBJECT quarry_base
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS quarry_base 0.0 0.0 4.0689 x y z
	GET_OBJECT_HEADING quarry_base heading
	CREATE_OBJECT_NO_OFFSET QUARRY_CRANE x y z quarry_stand
	SET_OBJECT_HEADING quarry_stand heading
	DONT_REMOVE_OBJECT quarry_stand
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS quarry_stand 0.0 0.0 1.0206 x y z
	GET_OBJECT_HEADING quarry_stand heading
	CREATE_OBJECT_NO_OFFSET QUARRY_CRANEARM x y z quarry_arm								   
	SET_OBJECT_HEADING quarry_arm heading
	DONT_REMOVE_OBJECT quarry_arm
	arm_rotate_y = 45.0
	SET_OBJECT_ROTATION quarry_arm 0.0 arm_rotate_y heading
// 	START_NEW_SCRIPT crane3_script quarry_base quarry_stand quarry_arm
			

	// BUILDING SITE CRANE IN LAS VEGAS - same as construction site crane in san fran
	VAR_INT lv_base lv_arm lv_trolley
	VAR_INT lv_base_LOD lv_arm_LOD
	// main bits
	CREATE_OBJECT_NO_OFFSET TWRCRANE_M_04 2399.202 1879.139 37.55 lv_base
	SET_OBJECT_HEADING lv_base 0.0
	DONT_REMOVE_OBJECT lv_base
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS lv_base 0.0 0.0 32.5210 x y z
	GET_OBJECT_HEADING lv_base heading
	CREATE_OBJECT_NO_OFFSET TWRCRANE_M_01 x y z lv_arm
	SET_OBJECT_HEADING lv_arm heading
	DONT_REMOVE_OBJECT lv_arm
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS lv_arm 0.0 20.0 3.789 x y z
	GET_OBJECT_HEADING lv_arm heading
	CREATE_OBJECT_NO_OFFSET TWRCRANE_M_02 x y z lv_trolley
	SET_OBJECT_HEADING lv_trolley heading
	DONT_REMOVE_OBJECT lv_trolley
	// lods
	GET_OBJECT_COORDINATES lv_base x y z
	GET_OBJECT_HEADING lv_base heading 
	CREATE_OBJECT_NO_OFFSET LODCrane_M_04 x y z lv_base_LOD
	SET_OBJECT_HEADING lv_base_LOD heading
	DONT_REMOVE_OBJECT lv_base_LOD
	GET_OBJECT_COORDINATES lv_arm x y z
	GET_OBJECT_HEADING lv_arm heading 
	CREATE_OBJECT_NO_OFFSET LODCrane_M_01 x y z lv_arm_LOD
	SET_OBJECT_HEADING lv_arm_LOD heading
	DONT_REMOVE_OBJECT lv_arm_LOD
	CONNECT_LODS lv_base lv_base_LOD
	CONNECT_LODS lv_arm lv_arm_LOD 





// ***********************************************************************************************
//
//						 -------------		  
//						|  [****] ::  |	  KEYPADS - Neil
//						|			  |
//						|  A   B   C  |	  Used for opening doors.	
//						|  D   E   F  |	  They operate exactly the same way as 'Mastermind'
//						 ------------- 
//
// ***********************************************************************************************

//
//VAR_INT kpad_heist							  
//CREATE_OBJECT_NO_OFFSET KMB_KEYPAD 2148.9954 1604.7000 1006.5000 kpad_heist
//DONT_REMOVE_OBJECT kpad_heist
//SET_OBJECT_HEADING kpad_heist 0.0
//START_NEW_SCRIPT kpad_script kpad_heist 0 1 2 3  // last four numbers are the combination



//	VAR_INT kpad_ngw
//	CREATE_OBJECT_NO_OFFSET KMB_KEYPAD 2774.1479 -2423.2297 14.2684 kpad_ngw
//	SET_OBJECT_HEADING kpad_ngw 90.0
//	START_NEW_SCRIPT kpad_script kpad_ngw 0 1 2 3
		

//	// national guard - warehouse 1	keypad
//	IF NOT DOES_OBJECT_EXIST keypad[1]
//		// create numbers for keypad first - so they get drawn last 
//		CREATE_OBJECT_NO_OFFSET  KEYPAD_LETTERS 2174.896 -2258.914 15.0 keypad_numbers[1]
//		SET_OBJECT_HEADING keypad_numbers[1] 215.23
//		DONT_REMOVE_OBJECT keypad_numbers[1]
//		SET_OBJECT_COLLISION keypad_numbers[1] FALSE
//		SET_OBJECT_DYNAMIC keypad_numbers[1] FALSE
//		
//		CREATE_OBJECT_NO_OFFSET KMB_KEYPAD 2174.896 -2258.914 15.0 keypad[1]
//		SET_OBJECT_HEADING keypad[1] 215.23
//		DONT_REMOVE_OBJECT keypad[1]
//		SET_OBJECT_COLLISION keypad[1] FALSE
//		SET_OBJECT_DYNAMIC keypad[1] FALSE
//
//		key_comb_A[1] =	0
//		key_comb_B[1] = 0
//		key_comb_C[1] =	0
//		key_comb_D[1] =	0
//	ENDIF
//
//	// LA police impound
//	IF NOT DOES_OBJECT_EXIST keypad[2]
//		// create numbers for keypad first - so they get drawn last 
//		CREATE_OBJECT_NO_OFFSET  KEYPAD_LETTERS 1543.218 -1660.918 6.98 keypad_numbers[2]
//		SET_OBJECT_HEADING keypad_numbers[2] 180.0
//		DONT_REMOVE_OBJECT keypad_numbers[2]
//		SET_OBJECT_COLLISION keypad_numbers[2] FALSE
//		SET_OBJECT_DYNAMIC keypad_numbers[2] FALSE
//		
//		CREATE_OBJECT_NO_OFFSET KMB_KEYPAD 1543.218 -1660.918 6.98 keypad[2]
//		SET_OBJECT_HEADING keypad[2] 180.0
//		DONT_REMOVE_OBJECT keypad[2]
//		SET_OBJECT_COLLISION keypad[2] FALSE
//		SET_OBJECT_DYNAMIC keypad[2] FALSE
//
//		key_comb_A[2] =	5
//		key_comb_B[2] = 7
//		key_comb_C[2] =	8
//		key_comb_D[2] =	9
//	ENDIF
//
//	// casino heist - heist8 combination (1234)
//	IF NOT DOES_OBJECT_EXIST keypad[3]
//		// create numbers for keypad first - so they get drawn last 
//		CREATE_OBJECT_NO_OFFSET  KEYPAD_LETTERS 2274.333 1718.620 8.407 keypad_numbers[3]
//		SET_OBJECT_HEADING keypad_numbers[3] 180.0
//		DONT_REMOVE_OBJECT keypad_numbers[3]
//		SET_OBJECT_COLLISION keypad_numbers[3] FALSE
//		SET_OBJECT_DYNAMIC keypad_numbers[3] FALSE
//		
//		CREATE_OBJECT_NO_OFFSET KMB_KEYPAD 2274.333 1718.620 8.407 keypad[3]
//		SET_OBJECT_HEADING keypad[3] 180.0
//		DONT_REMOVE_OBJECT keypad[3]
//		SET_OBJECT_COLLISION keypad[3] FALSE
//		SET_OBJECT_DYNAMIC keypad[3] FALSE
//
//		key_comb_A[3] =	0  // * combination *
//		key_comb_B[3] = 1
//		key_comb_C[3] =	2
//		key_comb_D[3] =	3
//	ENDIF
//
//	// plastic mouldings factory ( casino3 ) combination CFAC
//	IF NOT DOES_OBJECT_EXIST keypad[4]
//		// create numbers for keypad first - so they get drawn last 
//		CREATE_OBJECT_NO_OFFSET  KEYPAD_LETTERS 1093.8585 2076.7827 15.58 keypad_numbers[4]
//		SET_OBJECT_HEADING keypad_numbers[4] 0.0
//		DONT_REMOVE_OBJECT keypad_numbers[4]
//		SET_OBJECT_COLLISION keypad_numbers[4] FALSE
//		SET_OBJECT_DYNAMIC keypad_numbers[4] FALSE
//																							   
//		CREATE_OBJECT_NO_OFFSET KMB_KEYPAD 1093.8585 2076.7827 15.58 keypad[4]
//		SET_OBJECT_HEADING keypad[4] 0.0
//		DONT_REMOVE_OBJECT keypad[4]
//		SET_OBJECT_COLLISION keypad[4] FALSE
//		SET_OBJECT_DYNAMIC keypad[4] FALSE
//
//		key_comb_A[4] =	2  // * combination *
//		key_comb_B[4] = 5
//		key_comb_C[4] =	0
//		key_comb_D[4] =	2
//	ENDIF

//VAR_INT wheeloffortune					
//CREATE_OBJECT_NO_OFFSET WHEEL_O_FORTUNE 2473.7759 -1676.4655 13.3498 wheeloffortune
//WAIT 1000
//SET_OBJECT_HEADING wheeloffortune 335.4150
//DONT_REMOVE_OBJECT wheeloffortune
//START_NEW_SCRIPT wof_script wheeloffortune

//VAR_INT dock_gate1
//CREATE_OBJECT_NO_OFFSET electricgate 2234.123 -2215.985 12.67 dock_gate1 
//SET_OBJECT_HEADING dock_gate1 134.0
//DONT_REMOVE_OBJECT dock_gate1
//
//VAR_INT dock_gate2
//CREATE_OBJECT_NO_OFFSET electricgate 2233.937 -2215.798 12.637 dock_gate2
//SET_OBJECT_HEADING dock_gate2 312.0
//DONT_REMOVE_OBJECT dock_gate2

// ***********************************************************************************************
//
//					  	  KEVINS SAFE TUMBLER
//					  
//					  
//					  
//					  
//			 
//
// ***********************************************************************************************



  /*
   	REQUEST_MODEL safe_mock
	REQUEST_MODEL safe_tumbler

	WHILE NOT HAS_MODEL_LOADED safe_mock 
	OR NOT HAS_MODEL_LOADED safe_tumbler
		WAIT 0
	ENDWHILE   */	 


	//g1_flag_finished_with_safe_safetum_safetum = 0
 /*
	big_safe_x_safetum = 1286.0831 
	big_safe_y_safetum = 274.8189 
	big_safe_z_safetum	= -23.7070
	big_safe_heading_safetum = 350.0

	big_tumbler_x_safetum = 1286.1917 
	big_tumbler_y_safetum = 274.1938  
	big_tumbler_z_safetum = -21.7  
	big_tumbler_heading_safetum = 0.0

	IF NOT DOES_OBJECT_EXIST otb_big_safe_safetum
		CREATE_OBJECT safe_mock big_safe_x_safetum big_safe_y_safetum big_safe_z_safetum otb_big_safe_safetum
		SET_OBJECT_HEADING otb_big_safe_safetum big_safe_heading_safetum 
		DONT_REMOVE_OBJECT otb_big_safe_safetum
	ENDIF

	IF NOT DOES_OBJECT_EXIST big_tumbler_safetum 
		CREATE_OBJECT safe_tumbler big_tumbler_x_safetum big_tumbler_y_safetum big_tumbler_z_safetum big_tumbler_safetum
		DONT_REMOVE_OBJECT big_tumbler_safetum
		SET_OBJECT_COORDINATES big_tumbler_safetum big_tumbler_x_safetum big_tumbler_y_safetum big_tumbler_z_safetum	
		SET_OBJECT_ROTATION big_tumbler_safetum 0.0 0.0 0.0 
		SET_OBJECT_VELOCITY big_tumbler_safetum 0.0 0.0 0.0
		GET_OBJECT_HEADING  big_tumbler_safetum big_tumbler_heading_safetum
		   	
		SET_OBJECT_COLLISION big_tumbler_safetum FALSE 	  
		SET_OBJECT_DYNAMIC big_tumbler_safetum FALSE
	ENDIF

	WAIT 0	   */




// ************************************* SHOP DATA *****************************************
// *****************************************************************************************

VAR_INT flag_attacked_keeper_food // flag to say guy has been attacked

VAR_INT flag_attacked_barber
VAR_INT flag_food_message

VAR_INT shop_item_price
VAR_TEXT_LABEL shop_item_label

VAR_INT players_money

VAR_FLOAT temp_shopX temp_shopY temp_shopZ

temp_shopX = 0.0
temp_shopY = 0.0
temp_shopZ = 0.0

players_money = 0

flag_food_message = 0
shop_item_price = 0


// **************************************** CAR MODING GARAGES ************************************
// ************************************************************************************************

// LA Normal Cars
CHANGE_GARAGE_TYPE bodLAwN GARAGE_OPEN_FOR_NORMAL_CARS

// LA LowRiders (Neils Lowrider garage)
CHANGE_GARAGE_TYPE modlast GARAGE_OPEN_FOR_LOW_RIDERS

// San Fran Normal Cars
CHANGE_GARAGE_TYPE mdsSFSe GARAGE_OPEN_FOR_NORMAL_CARS

// San Fran Street Racer Garage
CHANGE_GARAGE_TYPE mds1SFS GARAGE_OPEN_FOR_STREET_RACERS

// Vegas Normal Cars
CHANGE_GARAGE_TYPE vEcmod GARAGE_OPEN_FOR_NORMAL_CARS

// GARAGE LOCATES FOR PLAYER TO STOP IN
// LA normal garage
CONST_FLOAT LS_NR_GARAGEX 1042.013 // 1042.263
CONST_FLOAT LS_NR_GARAGEY -1019.927 // -1013.566
CONST_FLOAT LS_NR_GARAGEZ 31.127

// LA lowrider garage Old one under bridge (Neils Mission one)  
CONST_FLOAT LS_LR_GARAGEX 2645.112
CONST_FLOAT LS_LR_GARAGEY -2045.745
CONST_FLOAT LS_LR_GARAGEZ 12.607

// San Fran normal garage
CONST_FLOAT SF_NR_GARAGEX -1935.528
CONST_FLOAT SF_NR_GARAGEY 247.029
CONST_FLOAT SF_NR_GARAGEZ 33.561

// San Fran Street Racer Garage
CONST_FLOAT SF_SR_GARAGEX -2723.845  
CONST_FLOAT	SF_SR_GARAGEY 217.804
CONST_FLOAT SF_SR_GARAGEZ 3.585

// Vegas normal garage
CONST_FLOAT LV_NR_GARAGEX 2387.075
CONST_FLOAT LV_NR_GARAGEY 1050.511
CONST_FLOAT LV_NR_GARAGEZ 9.812

// used for help messages for nitros
VAR_INT flag_1st_time_nitro_shop 
flag_1st_time_nitro_shop = 0   

// ***************************BRIDGE BLOCKS***********************************

//STARFISH GATES
/*
VAR_INT	strike_gate	star_gate_1 star_gate_2

CREATE_OBJECT_NO_OFFSET comgate1closed -715.082 -489.689 12.549 star_gate_1
DONT_REMOVE_OBJECT star_gate_1
CREATE_OBJECT_NO_OFFSET comgate2closed -181.451 -472.61 11.353 star_gate_2
SET_OBJECT_HEADING star_gate_2 102.0
DONT_REMOVE_OBJECT star_gate_2
*/										

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

// *************************************SA CAR GENERATORS***************************
VAR_INT gen_car1  gen_car2  gen_car3  gen_car4  gen_car5  gen_car6  gen_car7 gen_car8  gen_car9  gen_car10
VAR_INT gen_car12 gen_car13 gen_car14 gen_car15 gen_car17 gen_car18 gen_car19 gen_car20	tank_gen1
VAR_INT gen_car21 gen_car22 
VAR_INT gen_car51
VAR_INT badlands_car_gen highstakes_car_gen	farm_gen1 farm_gen2	farm_gen3

//CREATE_CAR_GENERATOR Remap1 Remap2 ParkedFlag ChanceOfCarAlarm ChanceOfLocked MinDelay MaxDelay CarGenID



//LA

sweet_carX = 2508.16  
sweet_carY = -1666.47  
sweet_carZ = 13.0 
sweet_carH = 16.0


ryder_carX = 2473.53  
ryder_carY = -1690.21 
ryder_carZ = 13.0 
ryder_carH = 0.0
 
 
var_int nascar_reward_cargen1
CREATE_CAR_GENERATOR 2692.0286 -1674.0243 9.4656 178.8279 MONSTERA -1 -1 TRUE 0 0 0 10000 nascar_reward_cargen1 // Outside LA stadium
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR nascar_reward_cargen1 TRUE
SWITCH_CAR_GENERATOR nascar_reward_cargen1 0
var_int nascar_reward_cargen2
CREATE_CAR_GENERATOR 2676.6953 -1673.7561 9.4038 178.8279 HOTRING -1 -1 TRUE 0 0 0 10000 nascar_reward_cargen2 // Outside LA stadium
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR nascar_reward_cargen2 TRUE
SWITCH_CAR_GENERATOR nascar_reward_cargen2 0

var_int dirtrack_reward_cargen
CREATE_CAR_GENERATOR 1104.9323 1614.8076 12.5546 85.6435 BFINJECT -1 -1 TRUE 0 0 0 10000 dirtrack_reward_cargen // Outside LA stadium
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR dirtrack_reward_cargen TRUE
SWITCH_CAR_GENERATOR dirtrack_reward_cargen 0



CREATE_CAR_GENERATOR -1460.8699 -1566.7368 101.0579 2.0 SANCHEZ -1 -1 FALSE 0 0 0 10000 farm_gen1 // Farm car //HUB
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR farm_gen1 TRUE
SWITCH_CAR_GENERATOR farm_gen1 101

CREATE_CAR_GENERATOR -1446.2390 -1494.7314 101.0514 6.0 WALTON -1 -1 FALSE 0 0 0 10000 farm_gen2 // Farm car //HUB
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR farm_gen2 TRUE
SWITCH_CAR_GENERATOR farm_gen2 101

CREATE_CAR_GENERATOR -1439.6428 -1576.8230 101.0578 264.1183 TRACTOR -1 -1 FALSE 0 0 0 10000 farm_gen3 // Farm car //HUB
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR farm_gen3 TRUE
SWITCH_CAR_GENERATOR farm_gen3 101

CREATE_CAR_GENERATOR_WITH_PLATE ryder_carX ryder_carY ryder_carZ ryder_carH PICADOR 84 84 FALSE 0 0 0 10000 &_SHERM__ gen_car6 // Ryders car //HUB
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR gen_car6 TRUE
SWITCH_CAR_GENERATOR gen_car6 101
					 
CREATE_CAR_GENERATOR_WITH_PLATE sweet_carX sweet_carY sweet_carZ sweet_carH GREENWOO 59 34 FALSE 0 0 0 10000 &GROVE4L_ gen_car7 // Sweets car //HUB
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR gen_car7 TRUE
SWITCH_CAR_GENERATOR gen_car7 101

CREATE_CAR_GENERATOR 2412.52 -1326.49 23.74 177.92 BMX -1 -1 FALSE 0 0 0 10000 gen_car9 // BMX for intro
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR gen_car9 TRUE
SWITCH_CAR_GENERATOR gen_car9 101
					 

CREATE_CAR_GENERATOR 2445.5 -1340.8 23.5 180.0 CLOVER -1 -1 FALSE 0 0 0 10000 gen_car8 //Mexican area
SWITCH_CAR_GENERATOR gen_car8 101

CREATE_CAR_GENERATOR 2499.18 -1648.26 13.0 158.61 BMX -1 -1 FALSE 0 0 0 10000 gen_car10 // BMX //HUB
SWITCH_CAR_GENERATOR gen_car10 101


// lowrider car gen - beside the lowrider garage
VAR_INT sw6_car_gen1
CREATE_CAR_GENERATOR 2685.9807 -2016.2097 12.5501 0.3370 SAVANNA -1 -1 TRUE 0 0 0 10000 sw6_car_gen1 // lowrider
SWITCH_CAR_GENERATOR sw6_car_gen1 0

// High Stakes car gen - next to Cesar's place
CREATE_CAR_GENERATOR 1772.0959 -2125.1028 13.0469 0.3441 BLADE -1 -1 FALSE 0 0 0 10000 highstakes_car_gen
SWITCH_CAR_GENERATOR highstakes_car_gen 101

//COUNTRYSIDE
// Badlands car gen - Sanchez next to Cesar's trailer in Angel Pine
CREATE_CAR_GENERATOR -2048.8560 -2521.2756 31.1250 171.0232 SANCHEZ -1 -1 FALSE 0 0 0 10000 badlands_car_gen
SWITCH_CAR_GENERATOR badlands_car_gen 101

//SAN FRAN
//steal mission car generators
VAR_INT steal_car_cargen1 steal_car_cargen2 steal_car_cargen3 steal_car_cargen4 steal_car_cargen5    									  
CREATE_CAR_GENERATOR -1956.3 297.7 34.3 64.8 URANUS -1 -1 TRUE 0 0 0 10000 steal_car_cargen1
CREATE_CAR_GENERATOR -1952.6 265.7 39.9 292.8 ELEGY -1 -1 TRUE 0 0 0 10000 steal_car_cargen2
CREATE_CAR_GENERATOR -1957.7 277.0 34.3 133.4 SULTAN -1 -1 TRUE 0 0 0 10000 steal_car_cargen3
CREATE_CAR_GENERATOR -1952.8 258.8 39.9 259.1 SAVANNA -1 -1 TRUE 0 0 0 10000 steal_car_cargen4
CREATE_CAR_GENERATOR -1950.5 259.7 34.3 53.8 STRATUM -1 -1 TRUE 0 0 0 10000 steal_car_cargen5

SWITCH_CAR_GENERATOR steal_car_cargen1 0
SWITCH_CAR_GENERATOR steal_car_cargen2 0
SWITCH_CAR_GENERATOR steal_car_cargen3 0
SWITCH_CAR_GENERATOR steal_car_cargen4 0
SWITCH_CAR_GENERATOR steal_car_cargen5 0

SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR steal_car_cargen1 TRUE
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR steal_car_cargen2 TRUE
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR steal_car_cargen3 TRUE
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR steal_car_cargen4 TRUE
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR steal_car_cargen5 TRUE

//VEGAS
//CREATE_CAR_GENERATOR 2499.18 -1648.26 13.0 158.61 BMX -1 -1 FALSE 0 0 0 10000 gen_car10 // BMX //HUB
//SWITCH_CAR_GENERATOR gen_car10 101



//  stunt.sc stuff 

VAR_INT done_bmx_stunt_progress
done_bmx_stunt_progress = 0
VAR_INT done_nrg500_stunt_progress
done_nrg500_stunt_progress = 0
VAR_INT stunt_best_time[2]
stunt_best_time[0] = 999999999
stunt_best_time[1] = 999999999

//skate park LA
VAR_INT stunt_bmx1_cargen
VAR_FLOAT stunt_bmx1_x stunt_bmx1_y stunt_bmx1_z
stunt_bmx1_x = 1946.0896 
stunt_bmx1_y = -1380.7202 
stunt_bmx1_z = 18.0
CREATE_CAR_GENERATOR stunt_bmx1_x stunt_bmx1_y stunt_bmx1_z 88.1761 BMX -1 -1 TRUE FALSE FALSE 0 10000 stunt_bmx1_cargen
SWITCH_CAR_GENERATOR stunt_bmx1_cargen 101

//ship building yard bowl SF
VAR_INT stunt_nrg500_cargen
VAR_FLOAT stunt_nrg500_x stunt_nrg500_y stunt_nrg500_z
stunt_nrg500_x = -1696.5312 
stunt_nrg500_y = 77.7192 
stunt_nrg500_z = 3.5547
CREATE_CAR_GENERATOR stunt_nrg500_x stunt_nrg500_y stunt_nrg500_z 312.9365 nrg500 -1 -1 TRUE FALSE FALSE 0 10000 stunt_nrg500_cargen
SWITCH_CAR_GENERATOR stunt_nrg500_cargen 101

//  stunt.sc stuff 


// ******************************* CAR GENERATORS ********************************************

// FIRE TRUCKS
VAR_INT fire_truck1 fire_truck1b fire_truck2 fire_truck3
CREATE_CAR_GENERATOR -2057.0 58.0 28.0 90.0 firela -1 -1 FALSE 0 0 0 10000 fire_truck1  // san fran
SWITCH_CAR_GENERATOR fire_truck1 101

CREATE_CAR_GENERATOR -2057.0 64.0 28.0 90.0 FIRETRUK -1 -1 FALSE 0 0 0 10000 fire_truck1b  // san fran
SWITCH_CAR_GENERATOR fire_truck1b 101


CREATE_CAR_GENERATOR 1763.8245 2075.7566 9.9093 179.4753 FIRETRUK -1 -1 FALSE 0 0 0 10000 fire_truck2  // vegas
SWITCH_CAR_GENERATOR fire_truck2 101

CREATE_CAR_GENERATOR 1751.5117 -1455.1016 12.5547 263.5589 FIRETRUK -1 -1 FALSE 0 0 0 10000 fire_truck3  // LA
SWITCH_CAR_GENERATOR fire_truck3 101


// AMBULANCE
VAR_INT ambulance_1 ambulance_2 ambulance_3 ambulance_4 ambulance_5 ambulance_6
CREATE_CAR_GENERATOR 2033.8879 -1432.6698 16.6453 177.8290 	AMBULAN -1 -1 FALSE FALSE 0 0 10000	ambulance_1
SWITCH_CAR_GENERATOR ambulance_1 101
CREATE_CAR_GENERATOR 1178.0477 -1338.1754 13.4050 269.4923 	AMBULAN -1 -1 FALSE FALSE 0 0 10000	ambulance_2
SWITCH_CAR_GENERATOR ambulance_2 101
CREATE_CAR_GENERATOR -303.7814 1032.3256 19.0860 268.5016 	AMBULAN -1 -1 FALSE FALSE 0 0 10000	ambulance_3
SWITCH_CAR_GENERATOR ambulance_3 101
CREATE_CAR_GENERATOR -1507.1642 2525.4553 55.1875 358.5728 	AMBULAN -1 -1 FALSE FALSE 0 0 10000	ambulance_4
SWITCH_CAR_GENERATOR ambulance_4 101
CREATE_CAR_GENERATOR 1229.6648 297.6883 19.0547 154.9553 	AMBULAN -1 -1 FALSE FALSE 0 0 10000	ambulance_5
SWITCH_CAR_GENERATOR ambulance_5 101
CREATE_CAR_GENERATOR -2202.5195 -2315.9856 30.1172 319.6815	AMBULAN -1 -1 FALSE FALSE 0 0 10000	ambulance_6
SWITCH_CAR_GENERATOR ambulance_6 101



var_int catalinas_cargen
CREATE_CAR_GENERATOR 886.3782 -25.6671 63.2440 157.6212	BUFFALO -1 -1 TRUE FALSE FALSE 0 10000 catalinas_cargen	//catalinas hideout shack 
SWITCH_CAR_GENERATOR catalinas_cargen 101

CREATE_CAR_GENERATOR mountbX mountbY mountbZ 206.39 MTBIKE -1 -1 TRUE 0 0 0 10000 gen_car12 // Mountain Bike 
SWITCH_CAR_GENERATOR gen_car12 101

CREATE_CAR_GENERATOR 687.3733 890.6700 -40.4285 35.1400 DUMPER -1 -1 TRUE 0 0 0 10000 gen_car1 // Dumper truck 
SWITCH_CAR_GENERATOR gen_car1 0

CREATE_CAR_GENERATOR 620.8820 861.2452 -43.9534 298.7428 DOZER -1 -1 TRUE 0 0 0 10000 gen_car2 // Bulldozer 
SWITCH_CAR_GENERATOR gen_car2 0

CREATE_CAR_GENERATOR 623.3402 887.0944 -43.5625 347.2967 SANCHEZ -1 -1 TRUE 0 0 0 10000 gen_car51	// Quarry dirtbike
SWITCH_CAR_GENERATOR gen_car51 101

//CREATE_CAR_GENERATOR 2490.84 1723.72 10.38 158.61 PIZZABOY -1 -1 TRUE 0 0 0 10000 gen_car40 // pizzaboy vegas
//SWITCH_CAR_GENERATOR gen_car40 101

CREATE_CAR_GENERATOR -2486.0457 59.1840 24.8284 180.0 SANCHEZ -1 -1 TRUE 0 0 0 10000 gen_car3 // Courier //back alleyway San fran
SWITCH_CAR_GENERATOR gen_car3 101

CREATE_CAR_GENERATOR pimpX pimpY pimpZ 270.0 BROADWAY 1 5 TRUE 0 0 0 10000 gen_car5 // PIMP
SWITCH_CAR_GENERATOR gen_car5 101

CREATE_CAR_GENERATOR hitchX hitchY hitchZ 180.0 SADLER 51 4 FALSE 0 0 0 10000 gen_car4 // hitchhiker
SWITCH_CAR_GENERATOR gen_car4 101
 
//Robsons Food Mart ( Commerce LA ), bmx can be replaced if it’s unpopular.
CREATE_CAR_GENERATOR cour1X cour1Y cour1Z 53.1 BMX -1 -1 TRUE 0 0 0 10000 gen_car20 // Courier
SWITCH_CAR_GENERATOR gen_car20 101

//By Hippy Shopper in SF, model needs to be a Freeway as it’s powerful enough to get up hills easily.
CREATE_CAR_GENERATOR cour2X cour2Y cour2Z 42.0 FREEWAY -1 -1 TRUE 0 0 0 10000 gen_car21 // Courier
SWITCH_CAR_GENERATOR gen_car21 101

//By Burger Shop in LV, Vegas is flat so the Faggio handles it quite easily
CREATE_CAR_GENERATOR cour3X cour3Y cour3Z 183.4 FAGGIO -1 -1 TRUE 0 0 0 10000 gen_car22 // Courier
SWITCH_CAR_GENERATOR gen_car22 101



CREATE_CAR_GENERATOR 2216.9023 -1160.4034 24.7265 270.8013 GREENWOO -1 -1 FALSE 0 0 0 10000 gen_car13 //at motel for motel deal
CREATE_CAR_GENERATOR 2216.9023 -1160.4034 24.7265 270.8013 GREENWOO -1 -1 FALSE 0 0 0 10000 gen_car14 //at motel for motel deal
CREATE_CAR_GENERATOR 2229.0015 -1173.7983 24.7331 90.5569 BMX -1 -1 FALSE 0 0 0 10000 gen_car15 //at the end of the alley for motel deal


CREATE_CAR_GENERATOR 2251.0281 -1788.6610 12.7625 358.9591 BOXBURG -1 -1 TRUE 0 0 0 10000 gen_car19 //Van for Burglay LA
SWITCH_CAR_GENERATOR gen_car19 101

CREATE_CAR_GENERATOR -2118.1707 -4.0948 35.0203 270.1420 BOXBURG -1 -1 TRUE 0 0 0 10000 gen_car18 //Van for Burglay	 SAN FRAN
SWITCH_CAR_GENERATOR gen_car18 101

CREATE_CAR_GENERATOR 2596.7471 1444.2452 10.3203 178.2712 BOXBURG -1 -1 TRUE 0 0 0 10000 gen_car17 //Van for Burglay	 VEGAS
SWITCH_CAR_GENERATOR gen_car17 101

// GIRLFRIEND CAR GENERATORS
VAR_INT iGFLockedCarGenerator[6] iGFUnlockedCarGenerator[6]	    
//--- Locked version of the cars
CREATE_CAR_GENERATOR 2408.1562 -1719.4630 13.6665 0.5881 HUSTLER 83 1 TRUE 0 100 0 10000 iGFLockedCarGenerator[COOCHIE]  
CREATE_CAR_GENERATOR -1778.1777 1207.0745 25.1194 91.9357 MONSTERB -1 -1 TRUE 0 100 0 10000 iGFLockedCarGenerator[MICHELLE]  
CREATE_CAR_GENERATOR -1399.7174 2628.5898 55.7823 271.7941 COPCARRU -1 -1 TRUE 0 100 0 10000 iGFLockedCarGenerator[BARBARA]  
CREATE_CAR_GENERATOR -379.5 -1443.1154 25.7266 88.9244 BANDITO -1 -1 TRUE 0 100 0 10000 iGFLockedCarGenerator[KYLIE]  
CREATE_CAR_GENERATOR -2572.0398 1148.5645 55.7333 337.8434 ROMERO 1 1 TRUE 0 100 0 10000 iGFLockedCarGenerator[SUZIE]  
CREATE_CAR_GENERATOR 2028.4459 2731.1021 10.53 268.9940 CLUB 126 1 TRUE 0 100 0 10000 iGFLockedCarGenerator[MILLIE]  
//---Unlocked version of the cars
CREATE_CAR_GENERATOR_WITH_PLATE 2408.1562 -1719.4630 13.6665 0.5881 HUSTLER 83 1 TRUE 0 0 0 10000 HOMEGIRL iGFUnlockedCarGenerator[COOCHIE]  
CREATE_CAR_GENERATOR_WITH_PLATE -1778.1777 1207.0745 25.1194 91.9357 MONSTERB -1 -1 TRUE 0 0 0 10000 &__NOS___ iGFUnlockedCarGenerator[MICHELLE]  
CREATE_CAR_GENERATOR_WITH_PLATE -1399.7174 2628.5898 55.7823 271.7941 COPCARRU -1 -1 TRUE 0 0 0 10000 &_CUFFS__  iGFUnlockedCarGenerator[BARBARA] 
CREATE_CAR_GENERATOR_WITH_PLATE -379.5 -1443.1154 25.7266 88.9244 BANDITO -1 -1 TRUE 0 0 0 10000 FULLAUTO iGFUnlockedCarGenerator[KYLIE]  
CREATE_CAR_GENERATOR_WITH_PLATE -2572.0398 1148.5645 55.7333 337.8434 ROMERO 1 1 TRUE 0 0 0 10000 &_TRAUMA_ iGFUnlockedCarGenerator[SUZIE]  
CREATE_CAR_GENERATOR_WITH_PLATE 2028.4459 2731.1021 10.53 268.9940 CLUB 126 1 TRUE 0 0 0 10000 &_SPANK__ iGFUnlockedCarGenerator[MILLIE]  
//--- Set this to avoid a wanted level if player 'borrows' the unlocked version of the car
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR iGFUnlockedCarGenerator[COOCHIE] TRUE
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR iGFUnlockedCarGenerator[MICHELLE] TRUE
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR iGFUnlockedCarGenerator[BARBARA] TRUE
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR iGFUnlockedCarGenerator[KYLIE] TRUE
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR iGFUnlockedCarGenerator[SUZIE] TRUE
SET_HAS_BEEN_OWNED_FOR_CAR_GENERATOR iGFUnlockedCarGenerator[MILLIE] TRUE   
//--- At start-up all generators are off
SWITCH_CAR_GENERATOR iGFLockedCarGenerator[COOCHIE] 0
SWITCH_CAR_GENERATOR iGFLockedCarGenerator[MICHELLE] 0
SWITCH_CAR_GENERATOR iGFLockedCarGenerator[BARBARA] 0
SWITCH_CAR_GENERATOR iGFLockedCarGenerator[KYLIE] 0
SWITCH_CAR_GENERATOR iGFLockedCarGenerator[SUZIE] 0
SWITCH_CAR_GENERATOR iGFLockedCarGenerator[MILLIE] 0	   
SWITCH_CAR_GENERATOR iGFUnlockedCarGenerator[COOCHIE] 0
SWITCH_CAR_GENERATOR iGFUnlockedCarGenerator[MICHELLE] 0
SWITCH_CAR_GENERATOR iGFUnlockedCarGenerator[BARBARA] 0
SWITCH_CAR_GENERATOR iGFUnlockedCarGenerator[KYLIE] 0
SWITCH_CAR_GENERATOR iGFUnlockedCarGenerator[SUZIE] 0
SWITCH_CAR_GENERATOR iGFUnlockedCarGenerator[MILLIE] 0	


// --------------------- CAR IMPORT / EXPORT CAR GENERATORS -----------------------------
VAR_INT impexp_car_gen[30]
CREATE_CAR_GENERATOR -1673.94 	439.02 		7.01 	136.0 BUFFALO 	-1 -1 FALSE 70 40 0 10000 impexp_car_gen[0]
CREATE_CAR_GENERATOR 926.45		-1292.29 	13.60 	270.0 SENTINEL 	-1 -1 FALSE 60 30 0 10000 impexp_car_gen[1]
CREATE_CAR_GENERATOR -2665.44	990.77		64.45	51.0  INFERNUS 	-1 -1 FALSE 80 50 0 10000 impexp_car_gen[2]
CREATE_CAR_GENERATOR -2516.5979 1228.9192 36.4283 	211.5 CAMPER 	-1 -1 TRUE 	30 10 0 10000 impexp_car_gen[3]  
CREATE_CAR_GENERATOR 1122.29	-1699.76	13.43	270.0 ADMIRAL 	-1 -1 FALSE 50 10 0 10000 impexp_car_gen[4]
CREATE_CAR_GENERATOR -1006.41	-628.27		32.00	270.0 PATRIOT 	-1 -1 TRUE  50 10 0 10000 impexp_car_gen[5]
CREATE_CAR_GENERATOR -2085.23	-2437.52	30.31	142.0 SANCHEZ 	-1 -1 FALSE 50 10 0 10000 impexp_car_gen[6]
CREATE_CAR_GENERATOR -1922.19	288.34		40.84	180.0 STRETCH 	-1 -1 FALSE 50 10 0 10000 impexp_car_gen[7]
CREATE_CAR_GENERATOR -16.66		-2521.17	36.37	210.0 FELTZER 	-1 -1 FALSE 50 10 0 10000 impexp_car_gen[8]
CREATE_CAR_GENERATOR 1803.38	-1931.05	13.66	0.0   REMINGTN 	-1 -1 FALSE 50 10 0 10000 impexp_car_gen[9]
CREATE_CAR_GENERATOR 1272.24	2603.03		10.49	117.0 CHEETAH 	-1 -1 TRUE  90 30 0 10000 impexp_car_gen[10]
CREATE_CAR_GENERATOR -112.40	-41.82		3.26	160.0 RANCHER 	-1 -1 FALSE 50 10 0 10000 impexp_car_gen[11]
CREATE_CAR_GENERATOR -2456.10	741.65		34.92	180.0 STALLION 	-1 -1 FALSE 50 10 0 10000 impexp_car_gen[12]
CREATE_CAR_GENERATOR -1951.81	2393.83		50.08	292.0 PETRO 	-1 -1 TRUE  50 10 0 10000 impexp_car_gen[13]
CREATE_CAR_GENERATOR -2751.79	-281.50		6.81	0.0   COMET 	-1 -1 FALSE 90 40 0 10000 impexp_car_gen[14]
CREATE_CAR_GENERATOR 1923.93	-2118.89	13.35	0.0   SLAMVAN 	-1 -1 FALSE 50 10 0 10000 impexp_car_gen[15]
CREATE_CAR_GENERATOR -1675.94	-618.74		13.86	256.0 BLISTAC 	-1 -1 FALSE 50 10 0 10000 impexp_car_gen[16]
CREATE_CAR_GENERATOR -2430.22	320.84		34.97	245.0 STAFFORD 	-1 -1 FALSE 50 10 0 10000 impexp_car_gen[17]
CREATE_CAR_GENERATOR -2265.33	200.65		34.97	270.0 SABRE 	-1 -1 FALSE 50 10 0 10000 impexp_car_gen[18]
CREATE_CAR_GENERATOR 2282.70	2535.88		10.39	180.0 FCR900 	-1 -1 FALSE 50 10 0 10000 impexp_car_gen[19]
CREATE_CAR_GENERATOR 2133.04	1009.75		10.49	270.0 BANSHEE 	-1 -1 FALSE 90 50 0 10000 impexp_car_gen[20]
CREATE_CAR_GENERATOR 2229.30	1402.99		10.82	180.0 SUPERGT 	-1 -1 TRUE  90 50 0 10000 impexp_car_gen[21]
CREATE_CAR_GENERATOR -1550.40	2687.54		56.22	90.0  JOURNEY 	-1 -1 FALSE 50 10 0 10000 impexp_car_gen[22]
CREATE_CAR_GENERATOR -2068.69	-83.75		35.10	0.0   HUNTLEY 	-1 -1 FALSE 50 10 0 10000 impexp_car_gen[23]
CREATE_CAR_GENERATOR 682.17		-1867.46	4.82	180.0 BFINJECT 	-1 -1 FALSE 70 10 0 10000 impexp_car_gen[24]
CREATE_CAR_GENERATOR 1747.87	-2098.03	13.28	180.0 BLADE 	-1 -1 FALSE 80 10 0 10000 impexp_car_gen[25]
CREATE_CAR_GENERATOR 1144.46	-1101.26	25.35	300.0 FREEWAY 	-1 -1 FALSE 50 10 0 10000 impexp_car_gen[26]
CREATE_CAR_GENERATOR -2406.25	-2180.84	33.39	180.0 MESA 		-1 -1 FALSE 70 10 0 10000 impexp_car_gen[27]
CREATE_CAR_GENERATOR 2163.79	1810.23		10.58	180.0 ZR350 	-1 -1 FALSE 80 10 0 10000 impexp_car_gen[28]
CREATE_CAR_GENERATOR 2207.43	1286.13		10.57	180.0 EUROS 	-1 -1 FALSE 50 10 0 10000 impexp_car_gen[29]
// all import export car gens are switched off at first, then switched on when the relevant board is unlocked
SWITCH_CAR_GENERATOR impexp_car_gen[0] 0
SWITCH_CAR_GENERATOR impexp_car_gen[1] 0
SWITCH_CAR_GENERATOR impexp_car_gen[2] 0
SWITCH_CAR_GENERATOR impexp_car_gen[3] 0
SWITCH_CAR_GENERATOR impexp_car_gen[4] 0
SWITCH_CAR_GENERATOR impexp_car_gen[5] 0
SWITCH_CAR_GENERATOR impexp_car_gen[6] 0
SWITCH_CAR_GENERATOR impexp_car_gen[7] 0
SWITCH_CAR_GENERATOR impexp_car_gen[8] 0
SWITCH_CAR_GENERATOR impexp_car_gen[9] 0
SWITCH_CAR_GENERATOR impexp_car_gen[10] 0
SWITCH_CAR_GENERATOR impexp_car_gen[11] 0
SWITCH_CAR_GENERATOR impexp_car_gen[12] 0
SWITCH_CAR_GENERATOR impexp_car_gen[13] 0
SWITCH_CAR_GENERATOR impexp_car_gen[14] 0
SWITCH_CAR_GENERATOR impexp_car_gen[15] 0
SWITCH_CAR_GENERATOR impexp_car_gen[16] 0
SWITCH_CAR_GENERATOR impexp_car_gen[17] 0
SWITCH_CAR_GENERATOR impexp_car_gen[18] 0
SWITCH_CAR_GENERATOR impexp_car_gen[19] 0
SWITCH_CAR_GENERATOR impexp_car_gen[20] 0
SWITCH_CAR_GENERATOR impexp_car_gen[21] 0
SWITCH_CAR_GENERATOR impexp_car_gen[22] 0
SWITCH_CAR_GENERATOR impexp_car_gen[23] 0
SWITCH_CAR_GENERATOR impexp_car_gen[24] 0
SWITCH_CAR_GENERATOR impexp_car_gen[25] 0
SWITCH_CAR_GENERATOR impexp_car_gen[26] 0
SWITCH_CAR_GENERATOR impexp_car_gen[27] 0
SWITCH_CAR_GENERATOR impexp_car_gen[28] 0
SWITCH_CAR_GENERATOR impexp_car_gen[29] 0

//--- COUNTRYSIDE CAR GENRATORS FOR TRACTORS AND COMBINE HARVESTERS
VAR_INT iFieldTractor[5] iFieldHarvester[8] 
// Flint
CREATE_CAR_GENERATOR -540.0441 -1396.1467 15.0 0.0 COMBINE 	-1 -1 FALSE 0 100 0 10000 iFieldHarvester[0]
CREATE_CAR_GENERATOR -289.5517 -1389.6273 10.0 0.0 COMBINE 	-1 -1 FALSE 0 100 0 10000 iFieldHarvester[1]
CREATE_CAR_GENERATOR -192.9000 -1331.3068 21.5 0.0 COMBINE 	-1 -1 FALSE 0 100 0 10000 iFieldHarvester[2]
CREATE_CAR_GENERATOR -273.9629 -1507.5961 5.0 0.0 TRACTOR 	-1 -1 FALSE 0 100 0 10000 iFieldTractor[0]
CREATE_CAR_GENERATOR -395.1952 -1293.1897 30.8 0.0 TRACTOR 	-1 -1 FALSE 0 100 0 10000 iFieldTractor[1] 
CREATE_CAR_GENERATOR -186.6494 -1339.2146 6.0 0.0 TRACTOR 	-1 -1 FALSE 0 100 0 10000 iFieldTractor[2]
// The Farm
CREATE_CAR_GENERATOR -1030.2489 -1050.1881 129.0 0.0 COMBINE	-1 -1 FALSE 0 100 0 10000 iFieldHarvester[3] 
CREATE_CAR_GENERATOR -1169.4252 -989.6309 129.0  0.0 COMBINE	-1 -1 FALSE 0 100 0 10000 iFieldHarvester[4] 
CREATE_CAR_GENERATOR -1110.7898 -947.7925 129.0 0.0 TRACTOR 	-1 -1 FALSE 0 100 0 10000 iFieldTractor[3] 
// Blueberry
CREATE_CAR_GENERATOR 16.8768 49.9910 3.0 0.0 COMBINE -1 -1 FALSE 0 100 0 10000 iFieldHarvester[5] 
CREATE_CAR_GENERATOR 81.0510 3.3203 1.5 0.0 COMBINE -1 -1 FALSE 0 100 0 10000 iFieldHarvester[6] 
CREATE_CAR_GENERATOR -15.2986 -84.6533 3.0 0.0 COMBINE -1 -1 FALSE 0 100 0 10000 iFieldHarvester[7]
CREATE_CAR_GENERATOR 81.0533 3.3234 1.5 0.0 TRACTOR -1 -1 FALSE 0 100 0 10000 iFieldTractor[4] 

// The COMBINE HARVESTERS are initialised as OFF - will be switched on after the Truth mission
SWITCH_CAR_GENERATOR iFieldHarvester[0] 0
SWITCH_CAR_GENERATOR iFieldHarvester[1] 0
SWITCH_CAR_GENERATOR iFieldHarvester[2] 0
SWITCH_CAR_GENERATOR iFieldHarvester[3] 0
SWITCH_CAR_GENERATOR iFieldHarvester[4] 0
SWITCH_CAR_GENERATOR iFieldHarvester[5] 0
SWITCH_CAR_GENERATOR iFieldHarvester[6] 0
SWITCH_CAR_GENERATOR iFieldHarvester[7] 0
// The TRACTORS are initialised as ON
SWITCH_CAR_GENERATOR iFieldTractor[0] 101
SWITCH_CAR_GENERATOR iFieldTractor[1] 101
SWITCH_CAR_GENERATOR iFieldTractor[2] 101
SWITCH_CAR_GENERATOR iFieldTractor[3] 101
SWITCH_CAR_GENERATOR iFieldTractor[4] 101

// ***************************************************PICKUPS********************************************************
/*
X runs from -2400 to 1600
Y from -2000 to 2000
Z from -100 to 100
*/
//WEAPONS********************************************************************************

VAR_INT  gun_toreno1 gun_toreno2 gun_toreno3 gun_toreno4


// LA Packages
/*
CREATE_COLLECTABLE1 1803.46 -1579.89 13.58 
CREATE_COLLECTABLE1 1838.63 -1485.81 13.69 
CREATE_COLLECTABLE1	2410.2507 -1558.6847 30.4922
CREATE_COLLECTABLE1	2629.9314 -1485.3994 15.3902
CREATE_COLLECTABLE1	2633.1877 -1479.8478 15.3543
CREATE_COLLECTABLE1	2516.1604 -1945.1355 15.7336
CREATE_COLLECTABLE1	2517.0613 -2049.5420 5.5971 
CREATE_COLLECTABLE1	2649.8315 -2116.7195 15.9609
CREATE_COLLECTABLE1	2689.0583 -2516.2615 16.3672
CREATE_COLLECTABLE1	1168.6608 -891.5475 42.0859
*/ 

VAR_INT  hunter_created  
//CREATE_CLOTHES_PICKUP -1200.3 -322.9 10.9 11 starting_tracksuit 
hunter_created = 0


// ****************************************ENTRY-EXITS RESERVED FOR DATES***********************************************
//--- The restaurants reserved for cut scenes are switched on and off by the date script
SWITCH_ENTRY_EXIT FDrest1 FALSE	   
SWITCH_ENTRY_EXIT rest2 FALSE
SWITCH_ENTRY_EXIT diner1 FALSE
SWITCH_ENTRY_EXIT diner2 FALSE
SWITCH_ENTRY_EXIT TSdiner FALSE
//--- The GF flats must always be off
SWITCH_ENTRY_EXIT GF1 FALSE	   
SWITCH_ENTRY_EXIT GF2 FALSE
SWITCH_ENTRY_EXIT GF3 FALSE
SWITCH_ENTRY_EXIT GF4 FALSE
SWITCH_ENTRY_EXIT GF5 FALSE
SWITCH_ENTRY_EXIT GF6 FALSE
// **********************************************************************************************************************




// --- flags for car skips --- ///
VAR_INT played_casino4_before
played_casino4_before = 0 

VAR_INT played_vcrash1_before
played_vcrash1_before = 0 

VAR_INT played_ryder1_before
played_ryder1_before = 0

//--- CHRISTIAN C. PED GLOBAL FLAGS ---
VAR_INT iSetOTBPanic
iSetOTBPanic = 0


//----------------------------------------------	|ActMINx| ActMINy| ActMAXx| ActMAXy| Gen1x | Gen1y	|Help1x| Help1y	|Gen2x | Gen2y | Help2x | Help2y // Area

//--- Set_pieces ------LA-----

ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE   	 2435.2  -1741.0    2454.9  -1723.4  2481.0  -1707.0  2480.0 -1732.0  2481.0 -1707.0  2480.0 -1732.0 // GANTON SOUTH OF HUB
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE  	 2680.6  -1665.8    2702.2  -1647.8  2724.7  -1610.1  2727.2 -1656.9  2724.0 -1610.0  2727.0 -1656.0 // East BEACH next to Stadium
ADD_SET_PIECE SETPIECE_CREATECOPPERONFOOT		  	 2878.4  -1972.5    2869.7	-1954.9	 2890.0  -1912.0  2877.0 -1962.0  2893.0 -2007.0  2881.0 -1970.0 // East BEACH on the steps to the beach	
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE 2807.0  -1152.0	2822.0  -1135.0	 2872.0  -1105.0  2874.0 -1133.0  2866.0 -1111.0  2864.0 -1138.0 // EAST BEACH North on the hill down towards the sea
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE	 2261.0  -1263.0    2249.0 	-1258.0	 2173.0  -1232.0  2170.0 -1257.0  2184.0 -1258.0  2202.0 -1260.0 // Alleyway in JEFFERSON
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE	 2157.0  -1500.0    2173.0  -1495.0	 2131.0  -1461.0  2131.0 -1495.0  2130.0 -1460.0  2131.0 -1494.0 // 
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT 		 2295.0  -1772.0    2302.0  -1761.0  2263.0	 -1780.0  2263.0 -1809.0  2327.0 -1821.0  2303.0 -1817.0 // Apartments in GANTON 
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     1518.0  -1700.0    1538.0  -1683.0  1564.0  -1730.0  1527.0 -1730.0  1565.0 -1731.0  1527.1 -1732.0 // OUTSIDE POLICE STAION AT PERSHING
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      2531.0  -1861.0    2547.0	-1839.0  2581.0  -1911.0  2578.0 -1865.0  2560.0 -1896.0  2561.0 -1848.0 // WILLOWFIELD STORM DRAINS
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE 1833.0  -1617.0    1854.0  -1608.0  1820.0  -1590.0  1818.0 -1605.0  1824.0 -1641.0  1824.0 -1619.0 // IDLEWOOD
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT		 1694.0  -1518.0    1706.0  -1505.0  1720.0  -1476.0  1709.0 -1487.0  1718.0 -1473.0  1709.0 -1474.0 // COMMERCE
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      1707.0  -1467.0    1711.0  -1457.0  1689.0  -1443.0  1709.0 -1444.0  1688.0 -1443.0 -1709.0 -1444.0 // COMMERCE 
ADD_SET_PIECE SETPIECE_CREATECOPPERONFOOT			 1815.0  -1406.0    1829.0  -1388.0	 1840.0  -1410.0  1835.0 -1402.0  1841.0 -1411.0  1836.0 -1403.0 // GLEN PARK
ADD_SET_PIECE SETPIECE_CREATECOPPERONFOOT			 1804.0  -1353.0    1807.0  -1342.0	 1781.0  -1366.0  1784.0 -1352.0  1781.0 -1366.0  1784.0 -1352.0 // GLEN PARK
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE	 1710.0  -1339.0    1724.0  -1319.0  1684.0  -1305.0  1712.0 -1305.0  1684.0 -1305.0  1712.0 -1305.0 //	DOWNTOWN LOS SANTOS
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE	 1711.0  -1204.0    1720.0  -1194.0	 1747.0  -1160.0  1712.0 -1159.0  1747.0 -1160.0  1712.0 -1159.0 // DOWNTOWN LOS SANTOS
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      2389.0  -1479.0    2404.0  -1469.0	 2429.0  -1457.0  2429.0 -1479.0  2428.0 -1456.0  2430.0 -1480.0 // DOWNTOWN LOS SANTOS CAR PARK
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      2443.0  -1221.0    2457.0  -1209.0	 2474.0  -1253.0  2447.0 -1255.0  2475.0 -1254.0  2448.0 -1256.0 //	EAST LOS SANTOS
ADD_SET_PIECE SETPIECE_CREATECOPPERONFOOT			 2627.0  -1134.0    2632.0  -1132.0  2649.0  -1150.0  2642.0 -1139.0  2650.0 -1151.0  2643.0 -1140.0 // STEPS IN LOS FLORES
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     2677.0  -1095.0    2697.0  -1060.0  2645.0  -1106.0  2646.0 -1077.0  2646.0 -1107.0  2647.0 -1078.0 // LOS COLINAS
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     1947.0  -1055.0    1955.0  -1038.0	 1989.0  -1025.0  1978.0 -1057.0  1990.0 -1026.0  1979.0 -1058.0 // GLEN PARK
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     1858.0  -1123.0    1876.0  -1113.0	 1895.0  -1133.0  1865.0 -1133.0  1896.0 -1134.0  1866.0 -1134.0 // GLEN PARK
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     1977.0  -1416.0    1996.0  -1411.0  1966.0  -1467.0  1988.0 -1465.0  1967.0 -1468.0  1989.0 -1466.0 // IDLEWOOD
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     2191.0  -1345.0    2202.0  -1338.0  2216.0  -1355.0  2216.0 -1343.0  2217.0 -1356.0  2217.0 -1344.0 // JEFFERSON
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      2339.0  -1161.0    2345.0  -1148.0  2372.0  -1182.0  2373.0 -1157.0  2373.0 -1183.0  2374.0 -1158.0 // EAST LS SANTOS 
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      2034.0  -1271.0    2049.0  -1251.0  2072.0  -1281.0  2070.0 -1261.0  2073.0 -1282.0  2070.0 -2074.0 // GLEN PARK AREA
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE 1410.0  -1250.0    1427.0  -1233.0	 1457.0  -1263.0  1456.0 -1243.0  1452.0 -1211.0  1453.0 -1238.0 // DOWNTOWN LOS SANTOS
ADD_SET_PIECE SETPIECE_TWOCOPCARSINALLEY             1427.0  -1334.0    1433.0  -1323.0  1419.0  -1270.0  1419.0 -1286.0  1420.0 -1253.0  1419.0 -1257.0 // DOWNTOWN LOS SANTOS
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE	 1520.0  -1655.0    1538.0  -1642.0	 1561.0  -1626.0  1530.0 -1627.0  1562.0 -1620.0  1531.0 -1622.0 // PERSHING SQUARE PD
ADD_SET_PIECE SETPIECE_TWOCOPCARSINALLEY             1129.0  -1669.0    1140.0  -1663.0  1095.0  -1665.0  1130.0 -1665.0  1117.0 -1714.0  1138.0 -1715.0 // ALLEYWAY AND RPOAD ADJACENT VERONA BEACH
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      1167.0  -1782.0    1190.0  -1769.0  1148.0  -1744.0  1176.0 -1743.0  1141.0 -1739.0  1175.0 -1737.0 // CONFERENCE CENTRE
ADD_SET_PIECE SETPIECE_TWOCOPCARSINALLEY			 1207.0  -1214.0    1216.0  -1193.0  1175.0  -1210.0  1194.0 -1209.0  1179.0 -1204.0  1197.0 -1204.0 // ALLEYWAY MARKET
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE 1261.0  -1721.0    1271.0  -1707.0  1295.0  -1680.0  1295.0 -1717.0  1300.0 -1670.0  1300.0 -1704.0 // VERONA BEACH
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE	     1237.0  -1290.0    1257.0  -1271.0  1214.0  -1253.0  1215.0 -1281.0  1215.0 -1215.0 -1215.0 -1281.0 // MARKET
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      1013.0  -1158.0    1030.0  -1132.0  1060.0  -1174.0  1060.0 -1148.0  1060.0 -1174.0  1060.0 -1148.0 // MARKET
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     1089.0  -1212.0    1076.0	-1204.0  1061.0  -1223.0  1064.0 -1205.0  1061.0 -1223.0  1064.0 -1205.0 // MARKET
ADD_SET_PIECE SETPIECE_CREATECOPPERONFOOT	          953.0  -1108.0     945.0  -1097.0   928.0  -1084.0   937.0 -1099.0   928.0 -1084.0   937.0 -1099.0 // CEMETERY ENTRANCE
ADD_SET_PIECE SETPIECE_CREATECOPPERONFOOT			  829.0  -1066.0     830.0  -1057.0   837.0  -1049.0   828.0 -1055.0   837.0 -1049.0   828.0 -1055.0 // VINEWOOD NR CEMETERY
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE	  816.0  -1048.0     840.0  -1030.0   793.0  -1015.0   791.0 -1055.0   793.0 -1015.0   791.0 -1055.0 // VINEWOOD
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE	      642.0  -1197.0     676.0  -1187.0   597.0  -1183.0   616.0 -1211.0   597.0 -1193.0   616.0 -1211.0 // RICHMAN TUNNEL     
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE       618.0  -1287.0     647.0  -1272.0   665.0  -1317.0   632.0 -1317.0   662.0 -1317.0   632.0 -1317.0 // RODEO
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE  457.0  -1364.0     487.0  -1357.0	  493.0  -1323.0   501.0 -1340.0   498.0 -1317.0   513.0 -1350.0 // RODEO
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE  523.0  -1456.0     555.0  -1442.0	  569.0  -1404.0   532.0 -1413.0   567.0 -1399.0   525.0 -1407.0 // RODEO
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT         478.0  -1488.0     496.0  -1472.0   491.0  -1518.0   490.0 -1501.0   490.0 -1521.0   487.0 -1497.0 // RODEO
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE	  470.0  -1604.0     490.0  -1573.0   540.0  -1626.0   540.0 -1583.0   540.0 -1626.0   540.0 -1583.0 // RODEO
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE	  519.0  -1687.0     544.0  -1676.0   486.0  -1727.0   534.0 -1734.0   486.0 -1727.0   534.0 -1734.0 // SANTA MARIA BEACH WHEN YOU CRASH THROUGH THE FENCE
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT         380.0  -1803.0     394.0  -1798.0   363.0  -1833.0   373.0 -1819.0   385.0 -1831.0   373.0 -1819.0 // PIER IN SANTA MARIA
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE 2134.0  -1904.0    2156.0  -1885.0  2105.0  -1919.0  2105.0 -1899.0  2079.0 -1871.0  2080.0 -1898.0 // WILLOWFIELD
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE	 1953.0  -1988.0    1971.0  -1966.0  1985.0  -1930.0  1967.0 -1929.0  1927.0 -1935.0  1953.0 -1935.0 // EL CORONA 
ADD_SET_PIECE SETPIECE_TWOCOPCARSINALLEY			 2015.0  -2064.0    2026.0  -2052.0  2009.0  -2010.0  2007.0 -2033.0  1970.0 -2059.0  1988.0 -2058.0 // ALLEYWAY WILLOWFIELD
ADD_SET_PIECE SETPIECE_TWOCOPCARSINALLEY	         1997.0  -2077.0    2009.0  -2065.0  2041.0  -2056.0  2016.0 -2058.0  2027.0 -2084.0  2005.0 -2084.0 // ALLEYWAY WILLOWFIELD
ADD_SET_PIECE SETPIECE_TWOCOPCARSINALLEY			 1924.0  -2108.0    1935.0  -2096.0  1917.0  -2074.0  1917.0 -2094.0  1917.0 -2138.0  1917.0 -2116.0 // ALLEYWAY ELCORONA
ADD_SET_PIECE SETPIECE_TWOCOPCARSINALLEY             1847.0  -2108.0    1860.0  -2097.0  1821.0  -2089.0  1820.0 -2101.0  1903.0 -2101.0  1883.0 -2101.0 // ELCORONA	
ADD_SET_PIECE SETPIECE_TWOCOPCARSINALLEY             1861.0  -2010.0    1870.0  -2005.0  1885.0  -2041.0  1886.0 -2021.0  1880.0 -2044.0  1878.0 -2016.0 // ELCORONA
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     1751.0  -2388.0    1781.0  -2369.0  1799.0  -2345.0  1798.0 -2377.0  1799.0 -2345.0  1798.0 -2377.0 // AIRPORT LA
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     1314.0  -2423.0    1325.0  -2410.0  1339.0  -2446.0  1317.0 -2446.0  1339.0 -2446.0  1317.0 -2446.0 // AIRPORT LOS SANTOS 	
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE		 1551.0  -1636.0    1562.0	-1618.0	 1531.0  -1660.0  1532.0 -1632.0  1531.0 -1660.0  1532.0 -1632.0 // OUTSIDE COPS
ADD_SET_PIECE SETPIECE_TWOFASTCOPCARSINALLEY	     1225.0  -1880.0    1237.0  -1866.0  1180.0  -1884.0  1213.0 -1885.0  1169.0 -1887.0  1194.0 -1888.0 // Verdant bluffs alley
ADD_SET_PIECE SETPIECE_TWOFASTCOPCARSINALLEY	     1076.0  -1890.0    1097.0  -1881.0  1161.0  -1880.0  1108.0 -1883.0  1050.0 -1894.0  1084.0 -1887.0 // verdant bluffs
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE	 1059.0  -1721.0    1090.0	-1703.0  1034.0  -1674.0  1039.0 -1713.0  1034.0 -1674.0  1039.0 -1713.0 // verona beach
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     1028.0  -1690.0    1048.0  -1657.0  1079.0  -1710.0  1037.0 -1709.0  1079.0 -1710.0  1037.0 -1709.0 // verona beach
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT         982.0  -1913.0     953.0  -1858.0   980.0  -1852.0   966.0 -1860.0   946.0 -1846.0   956.0 -1857.0 // verona beach
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT         849.0  -1899.0     823.0  -1937.0   836.0  -2017.0   839.0 -1993.0   848.0 -2020.0   842.0 -1990.0 // verona beach pier
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE  820.0  -1844.0     848.0  -1819.0   789.0  -1786.0   827.0 -1786.0   881.0 -1772.0   827.0 -1772.0 // Marina
ADD_SET_PIECE SETPIECE_CREATECOPPERONFOOT             788.0  -1816.0     780.0  -1800.0   786.0  -1835.0   781.0 -1820.0   786.0 -1835.0   781.0 -1820.0 // verona beach
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT         740.0  -1815.0     759.0 	-1808.0   738.0  -1790.0   737.0 -1806.0   744.0 -1836.0   746.0 -1819.0 // verona beacj
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT         717.0  -1819.0     727.0  -1805.0   745.0  -1827.0   746.0 -1815.0   735.0 -1792.0   728.0 -1813.0 // verona b
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT         624.0  -1786.0     641.0  -1778.0   644.0  -1799.0   647.0 -1789.0   651.0 -1767.0   649.0 -1783.0 // verona beach
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE      606.0  -1786.0     622.0  -1770.0	  656.0  -1759.0   649.0 -1787.0   656.0 -1759.0   649.0 -1787.0 // verona beach
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE      586.0  -1868.0     612.0  -1835.0   648.0  -1818.0   643.0 -1856.0   648.0 -1818.0   643.0 -1856.0 // verona beach
ADD_SET_PIECE SETPIECE_CREATECOPPERONFOOT             541.0	 -1778.0     562.0  -1762.0   593.0  -1775.0   573.0 -1772.0   593.0 -1772.0   573.0 -1772.0 // veroan stairs beach
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE      511.0  -1777.0     478.0  -1762.0   455.0  -1737.0   458.0 -1769.0   455.0 -1737.0   458.0 -1769.0 // verona beach
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT         381.0  -1806.0     398.0  -1790.0   385.0  -1825.0   383.0 -1806.0   364.0 -1831.0   377.0 -1810.0 // verona beach pier
ADD_SET_PIECE SETPIECE_TWOCARSRAMMINGPLAYERFROMSIDE   361.0  -1689.0     379.0  -1667.0   401.0  -1650.0   372.0 -1646.0   334.0 -1646.0   365.0 -1649.0 // pier top verona b   
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE	      289.0  -1647.0     314.0  -1631.0   321.0  -1597.0   321.0 -1642.0   321.0 -1597.0   321.0 -1642.0 // rodeo
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE      225.0  -1610.0     252.0  -1590.0   274.0  -1576.0   242.0 -1566.0   274.0 -1576.0   242.0 -1566.0 // rodeo
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE      301.0  -1472.0     336.0  -1467.0   269.0  -1476.0   291.0 -1497.0   269.0 -1476.0   291.0 -1497.0 // rodeo
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE  196.0  -1528.0     223.0  -1508.0   193.0  -1489.0   179.0 -1512.0   174.0 -1579.0   147.0 -1554.0 // rodeo
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE      221.0	 -1367.0     226.0  -1337.0   228.0  -1310.0   246.0 -1336.0   228.0 -1310.0   246.0 -1336.0 // richman
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE      427.0  -1238.0     450.0  -1229.0   391.0  -1225.0   402.0 -1252.0   391.0 -1225.0   402.0 -1252.0 // richman
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE      478.0  -1257.0     478.0  -1227.0   489.0  -1281.0   508.0 -1265.0   489.0 -1281.0   508.0 -1265.0 // rodeo
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE  337.0  -1176.0     371.0  -1170.0	  395.0	 -1169.0   362.0 -1141.0   344.0 -1131.0   381.0 -1167.0 // richman



//---------------------------------------------  	 |ActMINx| ActMINy| ActMAXx| ActMAXy |Gen1x | Gen1y	|Help1x| Help1y	| Gen2x | Gen2y | Help2x | Help2y // Area
//SETPIECE_TWOFASTCOPSCARSINALLEY
//--------------------LV------------------------	 |ActMINx| ActMINy| ActMAXx| ActMAXy| Gen1x | Gen1y	|Help1x| Help1y	| Gen2x | Gen2y | Help2x | Help2y // Area

ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      1798.0    796.0    1816.0    823.0  1760.0    832.0  1809.0   834.0  1760.0   832.0  1809.0   834.0  //CROSSROADS SOUTH LA
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE		 2032.0    892.0    2056.0    919.0  2086.0    856.0  2047.0   856.0  2086.0   856.0  2047.0   856.0  //JTSOUTH OF STRIP
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     2034.0    985.0    2053.0	 1024.0  2074.0    975.0  2044.0   974.0  2074.0   975.0  2044.0   974.0  //STRIP
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     2035.0   1228.0    2056.0   1254.0  2021.0   1275.0  2051.0  1277.0  2021.0  1275.0  2051.0  1277.0  //STRIP
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE	 2114.0   2071.0    2136.0   2096.0  2098.0   2020.0  2131.0  2020.0  2098.0  2020.0  2131.0  2020.0  //STRIP
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     1937.0   2104.0    1954.0   2122.0	 1925.0   2139.0  1926.0  2110.0  1925.0  2139.0  1926.0  2110.0  // REDSANDS EAST
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      1836.0   2044.0    1858.0 	 2059.0	 1815.0   2080.0  1821.0  2051.0  1815.0  2080.0  1821.0  2051.0  // REDSANDS EAST
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      1721.0   2044.0    1739.0   2063.0  1710.0   2023.0  1709.0  2051.0  1710.0  2023.0  1709.0  2051.0  // REDSANDS WEST
ADD_SET_PIECE SETPIECE_TWOCARSRAMMINGPLAYERFROMSIDE  1699.0   2134.0    1717.0   2152.0  1734.0   2172.0  1709.0  2171.0  1729.0  2175.0  1694.0  2175.0  // REDSANDS WEST next to steakhouse
ADD_SET_PIECE SETPIECE_TWOCARSRAMMINGPLAYERFROMSIDE	 1581.0   2164.0    1600.0   2182.0  1616.0   2143.0  1615.0  2170.0  1621.0  2146.0  1622.0  2182.0  // redsands west
ADD_SET_PIECE SETPIECE_TWOCARSRAMMINGPLAYERFROMSIDE  1522.0   2044.0    1538.0   2060.0  1529.0   2083.0  1532.0  2055.0  1525.0  2087.0  1525.0  2056.0  // redsnads west
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT		 1466.0   1989.0    1490.0   2003.0  1457.0   2024.0  1476.0  2009.0  1499.0  2027.0  1483.0  2006.0  // redsands west apartments
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT		 1429.0   2617.0    1439.0   2624.0  1422.0   2615.0  1433.0  2619.0  1446.0  2615.0  1434.0  2622.0  // yellow bell station
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     1469.0   2545.0    1487.0   2568.0  1500.0   2592.0  1474.0  2591.0  1500.0  2592.0  1474.0  2591.0  // prickle pine
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     1300.0   2580.0    1325.0   2599.0  1291.0   2568.0  1291.0  2597.0  1291.0  2568.0  1291.0  2597.0  // prickle pine
ADD_SET_PIECE SETPIECE_TWOCARSRAMMINGPLAYERFROMSIDE	 2240.0   2460.0    2246.0   2449.0  2230.0   2433.0  2231.0  2451.0  2229.0  2476.0  2231.0  2456.0  // cop station 
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE	 1238.0   2671.0    1257.0   2690.0	 1276.0   2716.0  1244.0  2713.0  1276.0  2716.0  1244.0  2713.0  //PRICKLE PINE	 
ADD_SET_PIECE SETPIECE_TWOCARSRAMMINGPLAYERFROMSIDE	 1493.0	  2580.0    1512.0   2599.0  1479.0   2562.0  1480.0  2588.0  1476.0  2555.0  1475.0  2585.0  // PRICKLEPINE
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE	 1469.0   2433.0    1486.0   2447.0  1449.0   2390.0  1478.0  2391.0  1449.0  2390.0  1478.0  2391.0  // REDSNADS WEST
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE	     1558.0   2283.0    1577.0   2305.0  1529.0   2311.0  1565.0  2310.0  1529.0  2311.0  1565.0  2310.0  //REDSANDS WEST
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE	     1397.0   2303.0    1419.0 	 2323.0  1385.0   2331.0  1389.0  2313.0  1385.0  2331.0  1389.0  2313.0  // REDSANDS WEST
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT        1568.0   2114.0    1578.0	 2140.0  1573.0   2078.0  1573.0  2118.0  1571.0  2088.0  1571.0  2106.0  // alleyway REDSANDS WEST
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE	 1660.0   2002.0    1685.0   2021.0  1710.0   1985.0  1709.0  2015.0  1710.0  1985.0  1709.0  2015.0  //  redsands
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE	     1765.0   2044.0    1788.0   2063.0  1810.0   2083.0  1827.0  2056.0  1810.0  2083.0  1827.0  2056.0  //  Harry Goldway area
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     1948.0   2274.0    1971.0   2293.0  1930.0   2259.0  1930.0  2283.0  1930.0  2259.0  1930.0  2283.0  //  Redsands East
ADD_SET_PIECE SETPIECE_TWOCARSRAMMINGPLAYERFROMSIDE  1918.0   2309.0    1938.0   2328.0  1951.0   2338.0  1929.0  2338.0  1895.0  2333.0  1926.0  2337.0  //  REDSANDS EAST
ADD_SET_PIECE SETPIECE_TWOCARSRAMMINGPLAYERFROMSIDE  1986.0   2384.0    2006.0   2403.0  2031.0   2367.0  2024.0  2392.0  2026.0  2433.0  2025.0  2395.0  //  tHE eMERALD iSLE
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     2011.0   2334.0    2017.0   2352.0  1995.0   2286.0  2014.0  2288.0  1995.0  2286.0  2014.0  2288.0  // ALLEYWAY THE EMERALD ISLE
ADD_SET_PIECE SETPIECE_TWOCARSRAMMINGPLAYERFROMSIDE	 2063.0   2274.0    2090.0   2293.0  2068.0   2313.0  2096.0  2292.0  2119.0  2260.0  2104.0  2282.0  // THE EMERALD iSLE
ADD_SET_PIECE SETPIECE_TWOCARSRAMMINGPLAYERFROMSIDE  2171.0   2356.0    2185.0   2356.0	 2214.0   2328.0  2196.0  2336.0  2217.0  2370.0  2204.0  2348.0  // the emerald isle
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      2177.0   2302.0    2185.0   2325.0  2198.0   2286.0  2161.0  2288.0  2198.0  2286.0  2161.0  2288.0  // the emerald isle
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      2256.0   2274.0    2294.0   2293.0  2288.0   2260.0  2287.0  2283.0  2288.0  2260.0  2287.0  2283.0  // roca Escalnte
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      2379.0   2404.0    2395.0   2423.0  2342.0   2436.0  2353.0  2415.0  2342.0  2436.0  2353.0  2415.0  // Roca Escalnate cop shop
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      2218.0   2422.0    2239.0   2438.0  2257.0   2455.0  2227.0  2452.0  2257.0  2455.0  2227.0  2452.0  // LVPD car park
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     2337.0   2504.0    2354.0   2522.0  2370.0   2482.0  2370.0  2513.0  2370.0  2482.0  2370.0  2513.0  // roca escalnate
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     2490.0   2403.0    2511.0   2423.0  2524.0   2438.0  2525.0  2411.0  2524.0  2438.0  2525.0  2411.0  // roca escalante
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     2518.0   2255.0    2537.0   2274.0  2555.0   2236.0  2528.0  2237.0  2555.0  2236.0  2528.0  2237.0  // roca escalnate
ADD_SET_PIECE SETPIECE_TWOCARSRAMMINGPLAYERFROMSIDE  2518.0   2189.0   	2539.0   2210.0  2558.0   2235.0  2518.0  2236.0  2498.0  2231.0  2537.0  2231.0  // roca
ADD_SET_PIECE SETPIECE_TWOCARSRAMMINGPLAYERFROMSIDE  2544.0   2222.0    2566.0	 2243.0  2524.0   2265.0  2525.0  2234.0  2530.0  2271.0  2531.0  2226.0  // rocaq
ADD_SET_PIECE SETPIECE_TWOCARSRAMMINGPLAYERFROMSIDE	 2363.0	  2224.0    2395.0   2248.0  2352.0   2205.0  2353.0  2236.0  2346.0  2197.0  2344.0  2231.0  // old venturas strip
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     2333.0   2173.0    2369.0 	 2190.0  2313.0   2138.0  2349.0  2140.0  2313.0  2138.0  2349.0  2140.0  // old v strip
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     2380.0   2124.0    2404.0   2163.0  2346.0   2178.0  2350.0  2149.0  2346.0  2178.0  2350.0  2149.0  // old v strip
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE 2484.0   2124.0    2513.0   2163.0  2525.0   2179.0  2525.0  2151.0  2530.0  2184.0  2530.0  2136.0  // o v strip
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE 2713.0   2104.0    2737.0   2122.0  2793.0   2086.0  2796.0  2110.0  2796.0  2079.0  2801.0  2110.0  // creek
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE 2838.0   2127.0    2857.0   2159.0  2824.0   2111.0  2846.0  2111.0  2803.0  2111.0  2845.0  2112.0  // creek
ADD_SET_PIECE SETPIECE_TWOCARSRAMMINGPLAYERFROMSIDE  2789.0   1945.0    2803.0   1985.0  2745.0   1956.0  2779.0  1956.0  2739.0  1952.0  2786.0  1951.0  // creek bridge mad max
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE	 2640.0   1944.0    2660.0   1965.0  2605.0   1982.0  2605.0  1953.0  2605.0  1982.0  2605.0  1953.0  // Starfish Casion
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     2498.0	  1921.0	2517.0   1938.0	 2537.0   1955.0  2507.0  1956.0  2537.0  1955.0  2507.0  1956.0  // Strafish Casion
ADD_SET_PIECE SETPIECE_CREATECOPPERONFOOT	         2408.0   2021.0    2429.0   2042.0  2398.0   2045.0  2412.0  2044.0  2398.0  2045.0  2412.0  2044.0  // o v strip
ADD_SET_PIECE SETPIECE_CREATECOPPERONFOOT            2188.0   1826.0    2205.0   1851.0	 2234.0   1867.0  2212.0  1851.0  2234.0  1867.0  2212.0  1851.0  // the ring master
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      2067.0   1863.0    2082.0   1894.0  2083.0   1843.0  2095.0  1865.0  2083.0  1843.0  2095.0  1865.0  // the visage
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT		 2033.0   1894.0    2048.0   1936.0  2060.0   1951.0  2041.0  1943.0  2069.0  1865.0  2063.0  1878.0  // the visage
ADD_SET_PIECE SETPIECE_TWOCARSRAMMINGPLAYERFROMSIDE  2069.0   1785.0    2139.0   1806.0	 2147.0   1776.0  2095.0  1776.0  1975.0  1711.0  2060.0  1710.0  // the strip
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT        2094.0   1649.0    2165.0   1737.0	 2177.0   1666.0  2129.0  1655.0  2151.0  1723.0  2134.0  1717.0  // caligula's palace
ADD_SET_PIECE SETPIECE_CREATECOPPERONFOOT	         2006.0   1541.0    2021.0   1550.0  2001.0   1564.0  2004.0  1553.0  2001.0  1564.0  2004.0  1553.0  // pirate ship
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     1931.0   1444.0    1964.0   1466.0  1989.0   1415.0  1991.0  1454.0  1989.0  1415.0  1991.0  1454.0  // the high roller
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     1935.0   1299.0    1958.0   1322.0	 1974.0   1276.0  1949.0  1277.0  1974.0  1276.0  1949.0  1277.0  // the pink swan
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     1756.0   1264.0    1786.0	 1282.0  1730.0   1302.0  1722.0  1273.0  1730.0  1302.0  1722.0  1273.0  // las V Airport
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT	     1723.0   1182.0    1736.0	 1205.0	 1714.0   1234.0  1714.0  1217.0  1703.0  1229.0  1709.0  1219.0  // las v airport carpark top
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE		 1583.0   1124.0    1606.0   1142.0	 1570.0   1098.0  1569.0  1138.0  1570.0  1098.0  1569.0  1138.0  //  freight depot
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      1716.0   1585.0    1744.0   1605.0  1726.0   1663.0  1745.0  1637.0  1726.0  1663.0  1745.0  1637.0  //  lv airport     
    

//--------------------------------------------  	 |ActMINx| ActMINy| ActMAXx| ActMAXy |Gen1x | Gen1y	 |Help1x| Help1y | Gen2x | Gen2y | Help2x | Help2y // Area

//----------------------SF---------------       	 |ActMINx| ActMINy| ActMAXx| ActMAXy |Gen1x | Gen1y	 |Help1x| Help1y | Gen2x | Gen2y | Help2x | Help2y // Area
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -2349.0  2381.0   -2327.0   2377.0   -2349.0  2402.0  -2328.0 2388.0  -2349.0 2402.0  -2328.0  2388.0 // BAYSIDE MARINA
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -2475.0  2299.0   -2456.0   2315.0   -2435.0  2337.0  -2466.0 2334.0  -2435.0 2337.0  -2466.0  2334.0 // BAYSIDE MARINA
ADD_SET_PIECE SETPIECE_TWOCARSRAMMINGPLAYERFROMSIDE  -1591.0   656.0   -1574.0    667.0   -1561.0   690.0  -1562.0  663.0  -1557.0  692.0  -1556.0   665.0 // cop station 
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -1752.0  1317.0   -1726.0   1323.0   -1749.0  1269.0  -1714.0 1299.0  -1749.0 1269.0  -1714.0  1299.0 // Esplanade north
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE	 -1715.0  1309.0   -1711.0	 1343.0   -1710.0  1377.0  -1736.0 1350.0  -1710.0 1377.0  -1736.0  1350.0 // esplanade north
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT	     -1673.0  1386.0   -1637.0   1391.0   -1624.0  1380.0  -1626.0 1393.0  -1662.0 1430.0  -1642.0  1427.0 // esplanade north pier 69
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE	 -2002.0  1273.0   -1967.0   1299.0   -1943.0  1258.0  -1944.0 1293.0  -1943.0 1258.0  -1944.0  1293.0 // esplanade north alley way 	
ADD_SET_PIECE SETPIECE_TWOCOPCARSINALLEY             -1946.0  1233.0   -1939.0	 1245.0	  -1943.0  1195.0  -1943.0 1221.0  -2021.0 1227.0  -1983.0  1227.0 // calton height alley
ADD_SET_PIECE SETPIECE_CREATECOPPERONFOOT            -1917.0  1253.0   -1904.0   1259.0   -1900.0  1242.0  -1900.0 1256.0  -1900.0 1242.0  -1900.0  1256.0 // downtown alley
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      -2041.0  1223.0   -2022.0   1232.0   -2010.0  1208.0  -2010.0 1228.0  -2010.0 1208.0  -2010.0  1228.0 // Calton heights
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE -1862.0  1166.0   -1836.0   1191.0   -1789.0  1147.0  -1790.0 1187.0  -1794.0 1148.0  -1794.0	1186.0 // Downtown
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -1728.0  1134.0   -1702.0	 1154.0	  -1747.0  1182.0  -1710.0 1184.0  -1747.0 1182.0  -1710.0  1184.0 // Downtown
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -1685.0  1173.0   -1663.0   1201.0   -1708.0  1151.0  -1712.0 1186.0  -1708.0 1151.0  -1712.0  1186.0 // Downton
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE -1678.0  1174.0   -1647.0   1206.0   -1644.0  1222.0  -1618.0 1192.0  -1606.0 1151.0  -1618.0  1192.0 // Downtown
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE		 -1760.0  1083.0   -1736.0   1115.0   -1718.0  1068.0  -1718.0 1105.0  -1718.0 1068.0  -1718.0  1105.0 // Financial
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      -1726.0  1005.0   -1701.0    986.0   -1673.0   932.0  -1716.0  933.0  -1673.0  932.0  -1716.0   933.0 // Fianacial
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -1769.0   963.0   -1749.0    901.0   -1710.0   886.0  -1711.0  923.0  -1710.0  886.0  -1711.0   923.0 // Financial
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE -1684.0   903.0   -1658.0	  944.0   -1711.0   833.0  -1710.0	912.0  -1718.0  975.0  -1719.0   926.0 // financila#
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE -1806.0   954.0   -1782.0    981.0	  -1753.0   935.0  -1795.0  934.0  -1842.0  916.0  -1793.0   916.0 // finacila
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE	     -1806.0   891.0   -1781.0    867.0   -1838.0   914.0  -1794.0  915.0  -1838.0  914.0  -1794.0   915.0 // finacial
ADD_SET_PIECE SETPIECE_TWOCARSRAMMINGPLAYERFROMSIDE	 -1614.0   943.0   -1589.0    904.0   -1563.0   880.0  -1561.0  922.0  -1563.0  966.0  -1556.0   929.0 // downtown
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -1871.0   827.0   -1843.0	  864.0   -1902.0   885.0  -1903.0  837.0  -1902.0  885.0  -1903.0   837.0 // downtown
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE	     -1912.0   954.0   -1880.0    977.0	  -1936.0   934.0  -1896.0	935.0  -1936.0  934.0  -1896.0   935.0 // downmtown
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE	     -1882.0   906.0   -1855.0    943.0	  -1896.0   889.0  -1896.0  924.0  -1896.0  889.0  -1896.0   924.0 // downtown
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      -1983.0   906.0   -1954.0    943.0   -1998.0   883.0  -1999.0  924.0  -1998.0  883.0  -1999.0   924.0 // calton heights
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT        -2044.0   901.0   -2030.0	  907.0   -2045.0   922.0  -2049.0  907.0  -2058.0  935.0  -2050.0   912.0 // Calton heights
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT        -2113.0   952.0   -2098.0	  961.0   -2078.0   953.0  -2094.0  954.0  -2086.0  931.0  -2097.0   948.0 // calton heights
ADD_SET_PIECE SETPIECE_TWOFASTCOPCARSINALLEY         -2034.0   957.0   -2019.0    951.0   -2010.0   981.0  -2010.0  952.0  -1965.0  933.0  -2000.0   935.0 // calton heights
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -2018.0  1017.0   -1992.0   1038.0   -1959.0  1053.0  -2002.0 1052.0  -1959.0 1053.0  -2002.0  1052.0 // calton heights    
ADD_SET_PIECE SETPIECE_CREATECOPPERONFOOT            -2077.0  1038.0   -2072.0   1045.0   -2080.0  1078.0  -2079.0 1053.0  -2080.0 1078.0  -2079.0  1053.0 // calton heights
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE 	 -2301.0  1043.0   -2253.0   1089.0   -2350.0  1117.0  -2360.0 1088.0  -2350.0 1117.0  -2360.0  1088.0 // juniper hollow
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -2381.0  1030.0   -2375.0   1061.0   -2320.0  1074.0  -2356.0 1085.0  -2320.0 1074.0  -2356.0  1085.0 // jumiper hollow
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -2399.0   965.0   -2374.0    992.0   -2344.0   961.0  -2385.0  961.0  -2344.0  961.0  -2385.0   961.0 // juniper hill
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE	     -2294.0   949.0   -2281.0    969.0   -2254.0   956.0  -2265.0  960.0  -2254.0  956.0  -2265.0   960.0 // calton heights
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE	     -2282.0   992.0   -2239.0   1017.0   -2298.0   957.0  -2258.0  955.0  -2298.0  957.0  -2258.0   955.0 // calton heights
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -2206.0   903.0   -2171.0    934.0   -2254.0   943.0  -2253.0  917.0  -2254.0  943.0  -2253.0   917.0 // clacton heighhts
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -2160.0   927.0   -2129.0    954.0   -2177.0   916.0  -2128.0  916.0  -2177.0  916.0  -2128.0   916.0 // calton hiks
ADD_SET_PIECE SETPIECE_TWOCOPCARSINALLEY             -2104.0   853.0   -2088.0    859.0   -2082.0   816.0  -2082.0  838.0  -2101.0  806.0  -2079.0   806.0 // calton heights
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -2099.0   696.0   -2079.0    710.0   -2114.0   729.0  -2089.0  729.0  -2114.0  729.0  -2089.0   729.0 // Chinatown
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE -2045.0   720.0   -2028.0    746.0   -2008.0   758.0  -2009.0  730.0  -2001.0  773.0  -2001.0   732.0 // downtown
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE -1989.0   719.0   -1965.0    744.0   -2000.0   691.0  -2001.0  731.0  -2007.0  765.0  -2008.0   732.0 // downtown
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -2017.0   632.0   -1992.0	  657.0   -1982.0   611.0  -2014.0  611.0  -1982.0  611.0  -2014.0   611.0 // downtown
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -1979.0   596.0   -1955.0	  620.0	  -2015.0   643.0  -2013.0  615.0  -2015.0  643.0  -2013.0   615.0 // donwtown
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -2056.0   455.0   -2027.0    477.0   -2008.0   438.0  -2008.0  457.0  -2008.0  438.0  -2008.0   457.0 // King's
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -2136.0   347.0   -2130.0    368.0   -2117.0   386.0  -2135.0  386.0  -2117.0  386.0  -2135.0   386.0 // Kings
ADD_SET_PIECE SETPIECE_TWOCOPCARSINALLEY             -2196.0   363.0   -2174.0    369.0	  -2148.0 	356.0  -2149.0  374.0  -2145.0  388.0  -2145.0   367.0 // King's
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      -2176.0   451.0   -2164.0    456.0   -2148.0   469.0  -2149.0  452.0  -2148.0  469.0  -2149.0   452.0 // King's
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -2203.0   451.0   -2190.0    456.0   -2233.0   463.0  -2224.0  462.0  -2233.0  463.0  -2224.0	 462.0 // King's
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -2280.0   590.0   -2243.0    621.0   -2221.0   568.0  -2260.0  568.0  -2221.0  568.0  -2260.0   568.0 // Kings
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -2235.0   537.0   -2217.0    552.0   -2196.0   569.0  -2233.0  569.0  -2196.0  569.0  -2233.0   569.0 // King's       
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE	     -2327.0   391.0   -2326.0    417.0   -2332.0   439.0  -2310.0  416.0  -2332.0  439.0  -2310.0   416.0 // Queens
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -2442.0   421.0   -2419.0    424.0   -2468.0   420.0  -2450.0  403.0  -2468.0  420.0  -2450.0   403.0 // Queens
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -2488.0   427.0   -2458.0    429.0   -2517.0   434.0  -2496.0  450.0  -2517.0  434.0  -2496.0   450.0 // Queens
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE 	 -2549.0   307.0   -2528.0    329.0   -2558.0   339.0  -2540.0  339.0  -2558.0  339.0  -2540.0   339.0 // Queens
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      -2592.0   379.0   -2577.0 	  390.0	  -2610.0   407.0  -2608.0  381.0  -2610.0  407.0  -2608.0   381.0 // Queens
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE -2636.0   279.0   -2618.0    307.0   -2639.0   331.0  -2608.0  324.0  -2566.0  345.0  -2605.0   351.0 // City Hall
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE -2651.0   326.0   -2630.0    341.0   -2661.0   359.0  -2660.0  336.0  -2665.0  362.0  -2665.0   330.0 // City Hall
ADD_SET_PIECE SETPIECE_CREATETWOCOPPERSONFOOT        -2741.0   361.0   -2697.0    390.0	  -2714.0   342.0  -2708.0  344.0  -2711.0  347.0  -2706.0   360.0 // City Hall
ADD_SET_PIECE SETPIECE_TWOCARSBLOCKINGPLAYERFROMSIDE -2616.0   392.0   -2597.0	  416.0	  -2578.0   388.0  -2606.0  386.0  -2570.0  346.0  -2606.0   350.0 // City Hall
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      -2653.0   326.0   -2634.0    346.0   -2609.0   359.0  -2609.0  327.0  -2609.0  359.0  -2609.0   327.0 // City Hall
ADD_SET_PIECE SETPIECE_CARRAMMINGPLAYERFROMSIDE      -2779.0   279.0   -2777.0	  303.0   -2806.0   256.0  -2807.0 	289.0  -2806.0  256.0  -2807.0   289.0 // Ocenan Flats
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -2829.0  -375.0   -2806.0   -347.0   -2789.0  -328.0  -2816.0 -327.0  -2789.0 -328.0  -2816.0  -327.0 // Country Club
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE     -2823.0  -305.0   -2802.0   -283.0   -2782.0  -328.0  -2814.0 -327.0  -2782.0 -328.0  -2814.0  -327.0 //   Cuntclub 


//--------------------COUNTRYSIDE----------          |ActMINx| ActMINy| ActMAXx| ActMAXy |Gen1x | Gen1y	|Help1x| Help1y	| Gen2x | Gen2y | Help2x | Help2y // Area

ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE 	-1555.0   2574.0   -1536.0	2588.0   -1524.0  2604.0  -1549.0  2604.0 -1524.0 2604.0 -1549.0  2604.0  // el RUEBRADOS
ADD_SET_PIECE SETPIECE_CARBLOCKINGPLAYERFROMSIDE    -1506.0   2616.0   -1486.0  2625.0	 -1470.0  2604.0  -1496.0  2604.0 -1470.0 2604.0 -1496.0  2604.0  // EL RUEBRADOS 
ADD_SET_PIECE SETPIECE_TWOCARSRAMMINGPLAYERFROMSIDE  2573.0     35.0    2603.0    49.0	  2534.0    90.0   2534.0    46.0  2533.0    1.0  2534.0	40.0  // PALOMINO CREEK
   
//----------------------------------------------	|ActMINx| ActMINy| ActMAXx| ActMAXy| Gen1x | Gen1y	|Help1x| Help1y	| Gen2x | Gen2y | Help2x | Help2y // Area


//--- Run the initialisation code that will reset all values for NEW GAME
INIT_ZONE_POPULATION_SETTINGS

// ****************************************LOS SANTOS PEDS****************************************************
	
	// Trainstations
		SET_ZONE_POPULATION_TYPE MARKST POPCYCLE_ZONE_SHOPPING_POSH // IN MARKET
		SET_ZONE_POPULATION_TYPE UNITY POPCYCLE_ZONE_RESIDENTIAL_AVERAGE // IN LITTLE MEXICO

	// Airport*
		// NEED ANOTHER ZONE FOR THE SMALL STRIP WIYH BAGGAGE HANDLERS 
		SET_ZONE_POPULATION_TYPE LAIR1 POPCYCLE_ZONE_AIRPORT
		SET_ZONE_POPULATION_TYPE LAIR2a POPCYCLE_ZONE_AIRPORT 
		SET_ZONE_POPULATION_TYPE LAIR2b POPCYCLE_ZONE_AIRPORT 
			// zone for baggage handlers
		SET_ZONE_POPULATION_TYPE LBAG1 POPCYCLE_ZONE_AIRPORT_RUNWAY	
		SET_ZONE_POPULATION_TYPE LBAG2 POPCYCLE_ZONE_AIRPORT_RUNWAY	
		SET_ZONE_POPULATION_TYPE LBAG3 POPCYCLE_ZONE_AIRPORT_RUNWAY	
  
	// Bluffs*
	// Verdant Bluffs   Tourists, few and far between, and walkers/backpackers.
		SET_ZONE_POPULATION_TYPE BLUF1a POPCYCLE_ZONE_PARK
		SET_ZONE_POPULATION_TYPE BLUF2 POPCYCLE_ZONE_PARK

	// Temple - Sunrise*
	//Tourists, actors, wannabees.
		SET_ZONE_POPULATION_TYPE SUN1 POPCYCLE_ZONE_ENTERTAINMENT
		SET_ZONE_POPULATION_TYPE SUN1 POPCYCLE_ZONE_ENTERTAINMENT
		SET_ZONE_POPULATION_TYPE SUN3a POPCYCLE_ZONE_ENTERTAINMENT
		SET_ZONE_POPULATION_TYPE SUN3b POPCYCLE_ZONE_ENTERTAINMENT 
		SET_ZONE_POPULATION_TYPE SUN3c POPCYCLE_ZONE_ENTERTAINMENT
		SET_ZONE_POPULATION_TYPE SUN4 POPCYCLE_ZONE_ENTERTAINMENT
		
		SET_ZONE_GANG_STRENGTH SUN1 GANG_GROVE 30
		SET_ZONE_GANG_STRENGTH SUN1 GANG_GROVE 30
		SET_ZONE_GANG_STRENGTH SUN3a GANG_GROVE 30
		SET_ZONE_GANG_STRENGTH SUN3b GANG_GROVE 30 
		SET_ZONE_GANG_STRENGTH SUN3c GANG_GROVE 30
		SET_ZONE_GANG_STRENGTH SUN4 GANG_GROVE 30
 	
	// Commerce* 
	// The old heart of the city, with city hall, police station, a hotel and some department stores 
	// Businessmen, tourists, middle class.
	   	SET_ZONE_POPULATION_TYPE COM1a POPCYCLE_ZONE_SHOPPING
		SET_ZONE_POPULATION_TYPE COM1b POPCYCLE_ZONE_SHOPPING
		SET_ZONE_POPULATION_TYPE COM2 POPCYCLE_ZONE_SHOPPING
		SET_ZONE_POPULATION_TYPE COM3 POPCYCLE_ZONE_SHOPPING
		SET_ZONE_POPULATION_TYPE COM4 POPCYCLE_ZONE_SHOPPING

	//Pershing Square*		
		SET_ZONE_POPULATION_TYPE PER1 POPCYCLE_ZONE_SHOPPING 
		
	// Las Collinas - Chicano Heights*
	// goes from a relatively ok, latino, working class area in the west to assortment of shacks
	// lower class latino to totally scummy latino
		SET_ZONE_POPULATION_TYPE CHC1a  POPCYCLE_ZONE_RESIDENTIAL_POOR
		SET_ZONE_POPULATION_TYPE CHC1b  POPCYCLE_ZONE_RESIDENTIAL_POOR

		SET_ZONE_POPULATION_TYPE CHC2a  POPCYCLE_ZONE_RESIDENTIAL_POOR
		SET_ZONE_POPULATION_TYPE CHC2b  POPCYCLE_ZONE_RESIDENTIAL_POOR

		SET_ZONE_POPULATION_TYPE CHC3  POPCYCLE_ZONE_GANGLAND
		SET_ZONE_POPULATION_TYPE CHC4a  POPCYCLE_ZONE_GANGLAND
		SET_ZONE_POPULATION_TYPE CHC4b  POPCYCLE_ZONE_GANGLAND		

		SET_ZONE_POPULATION_RACE CHC1a POPRACE_HB
		SET_ZONE_POPULATION_RACE CHC1b POPRACE_HB
		SET_ZONE_POPULATION_RACE CHC2a POPRACE_HB
		SET_ZONE_POPULATION_RACE CHC2b POPRACE_HB
		SET_ZONE_POPULATION_RACE CHC3  POPRACE_HB
		SET_ZONE_POPULATION_RACE CHC4a  POPRACE_HB
		SET_ZONE_POPULATION_RACE CHC4b  POPRACE_HB	

		SET_ZONE_GANG_STRENGTH CHC1a GANG_NMEX 40
		SET_ZONE_GANG_STRENGTH CHC1b GANG_NMEX 40
		SET_ZONE_GANG_STRENGTH CHC2a GANG_NMEX 40
		SET_ZONE_GANG_STRENGTH CHC2b GANG_NMEX 40
		SET_ZONE_GANG_STRENGTH CHC3  GANG_NMEX 40
		SET_ZONE_GANG_STRENGTH CHC4a GANG_NMEX 40
		SET_ZONE_GANG_STRENGTH CHC4b GANG_NMEX 40
			
		SET_ZONE_DEALER_STRENGTH CHC1a 4
		SET_ZONE_DEALER_STRENGTH CHC1b 4
		SET_ZONE_DEALER_STRENGTH CHC2a 4
		SET_ZONE_DEALER_STRENGTH CHC2b 4
		SET_ZONE_DEALER_STRENGTH CHC3  4
		SET_ZONE_DEALER_STRENGTH CHC4a 4
		SET_ZONE_DEALER_STRENGTH CHC4b 4	

  
	// Conference Centre*
	// Businessmen and tourists
		SET_ZONE_POPULATION_TYPE CONF1a POPCYCLE_ZONE_BUSINESS
		SET_ZONE_POPULATION_TYPE CONF1b POPCYCLE_ZONE_BUSINESS

	// Town Hall*
		SET_ZONE_POPULATION_TYPE THALL1 POPCYCLE_ZONE_BUSINESS

	// Docks*
	// Dock workers
		SET_ZONE_POPULATION_TYPE LDOC1a POPCYCLE_ZONE_INDUSTRY
		SET_ZONE_POPULATION_TYPE LDOC1b POPCYCLE_ZONE_INDUSTRY

		SET_ZONE_POPULATION_TYPE LDOC2 POPCYCLE_ZONE_INDUSTRY

		SET_ZONE_POPULATION_TYPE LDOC3a POPCYCLE_ZONE_INDUSTRY
		SET_ZONE_POPULATION_TYPE LDOC3b POPCYCLE_ZONE_INDUSTRY
		SET_ZONE_POPULATION_TYPE LDOC3c POPCYCLE_ZONE_INDUSTRY

		SET_ZONE_POPULATION_TYPE LDOC4 POPCYCLE_ZONE_INDUSTRY

	// Downtown*
	// Businessmen
		SET_ZONE_POPULATION_TYPE LDT1a POPCYCLE_ZONE_BUSINESS
		SET_ZONE_POPULATION_TYPE LDT1b POPCYCLE_ZONE_BUSINESS
		SET_ZONE_POPULATION_TYPE LDT1c POPCYCLE_ZONE_BUSINESS

		SET_ZONE_POPULATION_TYPE LDT3 POPCYCLE_ZONE_BUSINESS
		SET_ZONE_POPULATION_TYPE LDT4 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
		SET_ZONE_POPULATION_TYPE LDT5 POPCYCLE_ZONE_BUSINESS
		SET_ZONE_POPULATION_TYPE LDT6 POPCYCLE_ZONE_BUSINESS
		SET_ZONE_POPULATION_TYPE LDT7 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

	//  East Beach*
	//	Mixture of black and latino gangs populate this area, 
	// along the main avenue it can seem quite nice during the day: Lower and middle class, all races.
	// Gang here: GANG_NMEX
		SET_ZONE_POPULATION_TYPE EBE1 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
		SET_ZONE_POPULATION_TYPE EBE2a POPCYCLE_ZONE_RESIDENTIAL_AVERAGE	
		SET_ZONE_POPULATION_TYPE EBE2b POPCYCLE_ZONE_RESIDENTIAL_AVERAGE 

		SET_ZONE_POPULATION_TYPE EBE3c POPCYCLE_ZONE_BEACH

		SET_ZONE_GANG_STRENGTH EBE1 GANG_NMEX  30  
		SET_ZONE_GANG_STRENGTH EBE2a GANG_NMEX 30  
		SET_ZONE_GANG_STRENGTH EBE2b GANG_NMEX 30		
		SET_ZONE_GANG_STRENGTH EBE3c GANG_NMEX 30

		SET_ZONE_DEALER_STRENGTH EBE1  2
		SET_ZONE_DEALER_STRENGTH EBE2a 2  
		SET_ZONE_DEALER_STRENGTH EBE2b 2
		SET_ZONE_DEALER_STRENGTH EBE3c 2

		
	// El Corona*
	// The traditional home of the fishermen, dockworkers and navy staff 
	// A bit of a rough area, with a gang presence.  Lower class black and Mexican. 
	// Gang here: GANG_SMEX
		SET_ZONE_POPULATION_TYPE ELCO1 POPCYCLE_ZONE_RESIDENTIAL_POOR	
		SET_ZONE_POPULATION_TYPE ELCO2 POPCYCLE_ZONE_GANGLAND 
		
		SET_ZONE_POPULATION_RACE ELCO1	POPRACE_HB	   
		SET_ZONE_POPULATION_RACE ELCO2	POPRACE_HB

		SET_ZONE_GANG_STRENGTH ELCO1 GANG_SMEX 40
		SET_ZONE_GANG_STRENGTH ELCO2 GANG_SMEX 40

	// Ganton*
	// This is where the player hub is. Gang area, scary looking houses - Black gang area
	// Gang here: GANG_GROVE
		SET_ZONE_POPULATION_TYPE GAN1 POPCYCLE_ZONE_GANGLAND
		SET_ZONE_POPULATION_TYPE GAN2 POPCYCLE_ZONE_GANGLAND

		SET_ZONE_POPULATION_RACE GAN1 POPRACE_B
		SET_ZONE_POPULATION_RACE GAN2 POPRACE_B

		SET_ZONE_GANG_STRENGTH GAN1 GANG_GROVE 25 // reduced from 40 - should be increased after first 4\5 missions
		SET_ZONE_GANG_STRENGTH GAN2 GANG_GROVE 25 // was 40

		SET_ZONE_NO_COPS GAN1 TRUE

	// Glen Park*
	// Aspiring blue collar workers mingle with young urban professionals starting out.
	// Gang here: GANG_FLAT	(Glan 1 and 4 only)
		SET_ZONE_POPULATION_TYPE GLN1 POPCYCLE_ZONE_GANGLAND
		SET_ZONE_POPULATION_TYPE GLN2a POPCYCLE_ZONE_GANGLAND

		SET_ZONE_GANG_STRENGTH GLN1 GANG_FLAT 40 
		SET_ZONE_GANG_STRENGTH GLN2a GANG_FLAT 40

		SET_ZONE_DEALER_STRENGTH GLN1 6 
		SET_ZONE_DEALER_STRENGTH GLN2a 2
  
	// Willowfield - Industrial*
	// Rundown collection of industrial units, run down shops and houses - Lower class, all races
		SET_ZONE_POPULATION_TYPE LIND1a POPCYCLE_ZONE_GANGLAND
		SET_ZONE_POPULATION_TYPE LIND1b POPCYCLE_ZONE_INDUSTRY

		SET_ZONE_POPULATION_TYPE LIND2a POPCYCLE_ZONE_INDUSTRY
		SET_ZONE_POPULATION_TYPE LIND2b POPCYCLE_ZONE_INDUSTRY

		SET_ZONE_POPULATION_TYPE LIND3 POPCYCLE_ZONE_INDUSTRY

		SET_ZONE_GANG_STRENGTH LIND1a GANG_FLAT 20
		SET_ZONE_GANG_STRENGTH LIND1b GANG_FLAT 20
		SET_ZONE_GANG_STRENGTH LIND2a GANG_FLAT 20
		SET_ZONE_GANG_STRENGTH LIND2b GANG_FLAT 20
		SET_ZONE_GANG_STRENGTH LIND3 GANG_FLAT 20

	// Idlewood*	
	// Gangster houses and a few nicer well kept bungalows side by side. 
	// Lower class black and black gangs.
	// Gang here: GANG_FLAT
		SET_ZONE_POPULATION_TYPE IWD1 POPCYCLE_ZONE_GANGLAND
		SET_ZONE_POPULATION_TYPE IWD2 POPCYCLE_ZONE_GANGLAND
		SET_ZONE_POPULATION_TYPE IWD3a POPCYCLE_ZONE_GANGLAND
		SET_ZONE_POPULATION_TYPE IWD3b POPCYCLE_ZONE_GANGLAND
		SET_ZONE_POPULATION_TYPE IWD4 POPCYCLE_ZONE_GANGLAND
		SET_ZONE_POPULATION_TYPE IWD5 POPCYCLE_ZONE_GANGLAND

 		SET_ZONE_POPULATION_RACE IWD1 POPRACE_B
 		SET_ZONE_POPULATION_RACE IWD2 POPRACE_B
 		SET_ZONE_POPULATION_RACE IWD3a POPRACE_B
 		SET_ZONE_POPULATION_RACE IWD3b POPRACE_B
 		SET_ZONE_POPULATION_RACE IWD4 POPRACE_B
 		SET_ZONE_POPULATION_RACE IWD5 POPRACE_B

		SET_ZONE_GANG_STRENGTH IWD1 GANG_FLAT 20 
		SET_ZONE_GANG_STRENGTH IWD2 GANG_FLAT 20
		SET_ZONE_GANG_STRENGTH IWD3a GANG_FLAT 20
		SET_ZONE_GANG_STRENGTH IWD3b GANG_FLAT 20
		SET_ZONE_GANG_STRENGTH IWD4 GANG_FLAT 10
		//SET_ZONE_GANG_STRENGTH IWD4 GANG_FLAT 20
		SET_ZONE_GANG_STRENGTH IWD5 GANG_FLAT 20

		SET_ZONE_DEALER_STRENGTH IWD1 2
		SET_ZONE_DEALER_STRENGTH IWD2 2
		SET_ZONE_DEALER_STRENGTH IWD3a 2
		SET_ZONE_DEALER_STRENGTH IWD3b 2
		SET_ZONE_DEALER_STRENGTH IWD4 2
		SET_ZONE_DEALER_STRENGTH IWD5 2

	// Jefferson*
	// More affluent black area, still has gangs though. 
	// Lower middle class black and gangs
	// Gang here: GANG_FLAT 
		SET_ZONE_POPULATION_TYPE JEF1a POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
		SET_ZONE_POPULATION_TYPE JEF1b POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

		SET_ZONE_POPULATION_TYPE JEF2 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

		SET_ZONE_POPULATION_TYPE JEF3a POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
		SET_ZONE_POPULATION_TYPE JEF3b POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
		SET_ZONE_POPULATION_TYPE JEF3c POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

		SET_ZONE_GANG_STRENGTH JEF1a GANG_FLAT 40
		SET_ZONE_GANG_STRENGTH JEF1b GANG_FLAT 40
		SET_ZONE_GANG_STRENGTH JEF2  GANG_FLAT 40
		//SET_ZONE_GANG_STRENGTH JEF3a GANG_FLAT 40 commented because the hospital is here
		SET_ZONE_GANG_STRENGTH JEF3b GANG_FLAT 40
		SET_ZONE_GANG_STRENGTH JEF3c GANG_FLAT 40

		SET_ZONE_DEALER_STRENGTH JEF1a 6
		SET_ZONE_DEALER_STRENGTH JEF1b 6
		SET_ZONE_DEALER_STRENGTH JEF2  6
//		SET_ZONE_DEALER_STRENGTH JEF3a  commented because the hospital is here
		SET_ZONE_DEALER_STRENGTH JEF3b 6
		SET_ZONE_DEALER_STRENGTH JEF3c 6
	   	  
	// Los Flores*
	// Lower class latino and black.
	// Gang here: GANG_NMEX
		SET_ZONE_POPULATION_TYPE LFL1a POPCYCLE_ZONE_GANGLAND
		SET_ZONE_POPULATION_TYPE LFL1b POPCYCLE_ZONE_GANGLAND

 		SET_ZONE_POPULATION_RACE LFL1a	POPRACE_HB
 		SET_ZONE_POPULATION_RACE LFL1b	POPRACE_HB

		SET_ZONE_GANG_STRENGTH LFL1a GANG_NMEX	 40             
		SET_ZONE_GANG_STRENGTH LFL1b GANG_NMEX	 40

		SET_ZONE_DEALER_STRENGTH LFL1a 4
		SET_ZONE_DEALER_STRENGTH LFL1b 4

	// Little Mexico*	  
	// Tourists and Mexicans
		SET_ZONE_POPULATION_TYPE LMEX1a POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
		SET_ZONE_POPULATION_TYPE LMEX1b POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

		SET_ZONE_GANG_STRENGTH LMEX1a GANG_SMEX 30
		SET_ZONE_GANG_STRENGTH LMEX1b GANG_SMEX 30

	// East Los Santos*
	// The main Latino area of the city, pretty run down and gang populated. 
	// lower class Mexican and Mexican  gangs.
	// Gang here: GANG_FLAT(lower area)
		SET_ZONE_POPULATION_TYPE ELS1a POPCYCLE_ZONE_GANGLAND
		SET_ZONE_POPULATION_TYPE ELS1b POPCYCLE_ZONE_GANGLAND
		SET_ZONE_POPULATION_TYPE ELS2 POPCYCLE_ZONE_GANGLAND
		SET_ZONE_POPULATION_TYPE ELS3a POPCYCLE_ZONE_GANGLAND
		SET_ZONE_POPULATION_TYPE ELS3b POPCYCLE_ZONE_GANGLAND
		SET_ZONE_POPULATION_TYPE ELS4 POPCYCLE_ZONE_GANGLAND

 		SET_ZONE_POPULATION_RACE ELS1a	POPRACE_HW
 		SET_ZONE_POPULATION_RACE ELS1b	POPRACE_HW
 		SET_ZONE_POPULATION_RACE ELS2	POPRACE_HW
 		SET_ZONE_POPULATION_RACE ELS3a	POPRACE_HW
 		SET_ZONE_POPULATION_RACE ELS3b	POPRACE_HW
 		SET_ZONE_POPULATION_RACE ELS4	POPRACE_HW

		SET_ZONE_GANG_STRENGTH ELS1a GANG_FLAT 30
		SET_ZONE_GANG_STRENGTH ELS1b GANG_FLAT 30
		SET_ZONE_GANG_STRENGTH ELS2	 GANG_FLAT 30
		SET_ZONE_GANG_STRENGTH ELS3a GANG_FLAT 30
		SET_ZONE_GANG_STRENGTH ELS3b GANG_FLAT 30
		SET_ZONE_GANG_STRENGTH ELS4	 GANG_FLAT 30
	
		SET_ZONE_DEALER_STRENGTH ELS1a	2
		SET_ZONE_DEALER_STRENGTH ELS1b	2
		SET_ZONE_DEALER_STRENGTH ELS2	2
		SET_ZONE_DEALER_STRENGTH ELS3a	2
		SET_ZONE_DEALER_STRENGTH ELS3b	2
		SET_ZONE_DEALER_STRENGTH ELS4	2

	// Marina*
	// Nice residential area and resort, Middle class white
		SET_ZONE_POPULATION_TYPE MAR1 POPCYCLE_ZONE_RESIDENTIAL_RICH
		
		SET_ZONE_POPULATION_TYPE MAR2 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
		SET_ZONE_POPULATION_TYPE MAR3 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

	// Market*
	// General nice residential and shopping area
	// Businessmen, tourists, middle class,rich ladies out shopping.
		SET_ZONE_POPULATION_TYPE MKT1 POPCYCLE_ZONE_SHOPPING_POSH
		SET_ZONE_POPULATION_TYPE MKT2 POPCYCLE_ZONE_SHOPPING_POSH
		SET_ZONE_POPULATION_TYPE MKT3 POPCYCLE_ZONE_SHOPPING_POSH
		SET_ZONE_POPULATION_TYPE MKT4 POPCYCLE_ZONE_SHOPPING_POSH

	// Mulholland*
	// Really nice houses of all descriptions. Rich, affluent white.	 
		 
		SET_ZONE_POPULATION_TYPE MUL1a POPCYCLE_ZONE_RESIDENTIAL_RICH_SECLUDED
		SET_ZONE_POPULATION_TYPE MUL1b POPCYCLE_ZONE_RESIDENTIAL_RICH_SECLUDED
		SET_ZONE_POPULATION_TYPE MUL1c POPCYCLE_ZONE_RESIDENTIAL_RICH_SECLUDED

		SET_ZONE_POPULATION_TYPE MUL2a POPCYCLE_ZONE_PARK 
		SET_ZONE_POPULATION_TYPE MUL2b POPCYCLE_ZONE_PARK		
		SET_ZONE_POPULATION_TYPE MUL3 POPCYCLE_ZONE_PARK
	
		SET_ZONE_POPULATION_TYPE MUL4 POPCYCLE_ZONE_RESIDENTIAL_RICH_SECLUDED

		SET_ZONE_POPULATION_TYPE MUL5a POPCYCLE_ZONE_RESIDENTIAL_RICH_SECLUDED
		SET_ZONE_POPULATION_TYPE MUL5b POPCYCLE_ZONE_RESIDENTIAL_RICH_SECLUDED
		SET_ZONE_POPULATION_TYPE MUL5c POPCYCLE_ZONE_RESIDENTIAL_RICH_SECLUDED

		SET_ZONE_POPULATION_TYPE MUL6  POPCYCLE_ZONE_RESIDENTIAL_RICH_SECLUDED

		SET_ZONE_POPULATION_TYPE MUL7a POPCYCLE_ZONE_RESIDENTIAL_RICH_SECLUDED
		SET_ZONE_POPULATION_TYPE MUL7b POPCYCLE_ZONE_RESIDENTIAL_RICH_SECLUDED

	//  Playa del Seville*
		SET_ZONE_POPULATION_TYPE PLS POPCYCLE_ZONE_BEACH

		SET_ZONE_GANG_STRENGTH PLS GANG_GROVE 10

	// Richman*
	// Super rich and affluent white, movie stars and moguls
		SET_ZONE_POPULATION_TYPE RIH1a POPCYCLE_ZONE_RESIDENTIAL_RICH_SECLUDED
		SET_ZONE_POPULATION_TYPE RIH1b POPCYCLE_ZONE_RESIDENTIAL_RICH_SECLUDED

		SET_ZONE_POPULATION_TYPE RIH2 POPCYCLE_ZONE_RESIDENTIAL_RICH_SECLUDED

		SET_ZONE_POPULATION_TYPE RIH3a POPCYCLE_ZONE_RESIDENTIAL_RICH_SECLUDED
		SET_ZONE_POPULATION_TYPE RIH3b POPCYCLE_ZONE_RESIDENTIAL_RICH_SECLUDED

		SET_ZONE_POPULATION_TYPE RIH4 POPCYCLE_ZONE_RESIDENTIAL_RICH

		SET_ZONE_POPULATION_TYPE RIH5a POPCYCLE_ZONE_RESIDENTIAL_RICH
		SET_ZONE_POPULATION_TYPE RIH6b POPCYCLE_ZONE_RESIDENTIAL_RICH
		SET_ZONE_POPULATION_TYPE RIH5a POPCYCLE_ZONE_RESIDENTIAL_RICH
		SET_ZONE_POPULATION_TYPE RIH6b POPCYCLE_ZONE_RESIDENTIAL_RICH


	// Rodeo*
	// ultra expensive shopping paradise. Super rich and affluent white
		SET_ZONE_POPULATION_TYPE ROD1a POPCYCLE_ZONE_SHOPPING_POSH
		SET_ZONE_POPULATION_TYPE ROD1b POPCYCLE_ZONE_SHOPPING_POSH
		SET_ZONE_POPULATION_TYPE ROD1c POPCYCLE_ZONE_SHOPPING_POSH
		SET_ZONE_POPULATION_TYPE ROD2a POPCYCLE_ZONE_SHOPPING_POSH
		SET_ZONE_POPULATION_TYPE ROD2b POPCYCLE_ZONE_SHOPPING_POSH
		SET_ZONE_POPULATION_TYPE ROD3a POPCYCLE_ZONE_SHOPPING_POSH
		SET_ZONE_POPULATION_TYPE ROD3b POPCYCLE_ZONE_SHOPPING_POSH
		SET_ZONE_POPULATION_TYPE ROD4a POPCYCLE_ZONE_SHOPPING_POSH
		SET_ZONE_POPULATION_TYPE ROD4b POPCYCLE_ZONE_SHOPPING_POSH 
		SET_ZONE_POPULATION_TYPE ROD4c POPCYCLE_ZONE_SHOPPING_POSH

		SET_ZONE_POPULATION_TYPE ROD5a POPCYCLE_ZONE_RESIDENTIAL_RICH
		SET_ZONE_POPULATION_TYPE ROD5b POPCYCLE_ZONE_RESIDENTIAL_RICH

	// Santa Maria Beach*
	// Touristy beach area, Middle class white.
		SET_ZONE_POPULATION_TYPE SMB1 POPCYCLE_ZONE_BEACH
		SET_ZONE_POPULATION_TYPE SMB2 POPCYCLE_ZONE_BEACH
	
		SET_ZONE_GANG_STRENGTH SMB1 GANG_GROVE 10 
		SET_ZONE_GANG_STRENGTH SMB2 GANG_GROVE 10 		

	// Studio - Vinewood*
	// Tourists, middle class white, actors
		SET_ZONE_POPULATION_TYPE VIN2 POPCYCLE_ZONE_ENTERTAINMENT		
		SET_ZONE_POPULATION_TYPE VIN3 POPCYCLE_ZONE_ENTERTAINMENT
		SET_ZONE_POPULATION_TYPE VIN1a POPCYCLE_ZONE_ENTERTAINMENT
		SET_ZONE_POPULATION_TYPE VIN1b POPCYCLE_ZONE_ENTERTAINMENT

		SET_ZONE_GANG_STRENGTH VIN2 GANG_GROVE 10		

	// Verona Beach* 
	// The arty beach, with California wackos mingling with foreign bohemians
	//  black and latino gang members A bit dodgy after dark. Artists, musclemen, tourists, lower class white.
		SET_ZONE_POPULATION_TYPE VERO1 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

		SET_ZONE_POPULATION_TYPE VERO2 POPCYCLE_ZONE_SHOPPING
		SET_ZONE_POPULATION_TYPE VERO3 POPCYCLE_ZONE_SHOPPING

		SET_ZONE_POPULATION_TYPE VERO4a POPCYCLE_ZONE_BEACH
		SET_ZONE_POPULATION_TYPE VERO4b POPCYCLE_ZONE_BEACH

		SET_ZONE_GANG_STRENGTH VERO1 GANG_FLAT 10
		SET_ZONE_GANG_STRENGTH VERO2 GANG_FLAT 10
		SET_ZONE_GANG_STRENGTH VERO3 GANG_FLAT 10
		SET_ZONE_GANG_STRENGTH VERO4a GANG_FLAT 10
		SET_ZONE_GANG_STRENGTH VERO4b GANG_FLAT 10

		SET_ZONE_DEALER_STRENGTH VERO1 2
		SET_ZONE_DEALER_STRENGTH VERO2 2
		SET_ZONE_DEALER_STRENGTH VERO3 2
		SET_ZONE_DEALER_STRENGTH VERO4a 2
		SET_ZONE_DEALER_STRENGTH VERO4b 2


// ******************************************SAN FRAN PEDS ****************************************************
		// Trainstations
		SET_ZONE_POPULATION_TYPE CRANB POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

		// Calton Heights
		SET_ZONE_POPULATION_TYPE CALT POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
		SET_ZONE_GANG_STRENGTH CALT GANG_TRIAD 10	

		// Bayside Marina
		SET_ZONE_POPULATION_TYPE SUNMA POPCYCLE_ZONE_RESIDENTIAL_RICH

		// Bayside
		SET_ZONE_POPULATION_TYPE SUNNN POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

		// Battery Point
		SET_ZONE_POPULATION_TYPE BATTP POPCYCLE_ZONE_ENTERTAINMENT
		SET_ZONE_GANG_STRENGTH BATTP GANG_SFMEX 25

		// Esplanade North
		SET_ZONE_POPULATION_TYPE ESPN1 POPCYCLE_ZONE_SHOPPING
		SET_ZONE_POPULATION_TYPE ESPN2 POPCYCLE_ZONE_SHOPPING
		SET_ZONE_POPULATION_TYPE ESPN3 POPCYCLE_ZONE_SHOPPING
		SET_ZONE_GANG_STRENGTH ESPN1 GANG_VIET 10
		SET_ZONE_GANG_STRENGTH ESPN2 GANG_VIET 10
		SET_ZONE_GANG_STRENGTH ESPN3 GANG_VIET 10

		// Pallisades 
		SET_ZONE_POPULATION_TYPE BAYV POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
			//Needs also a park zone

		//Paradiso
		SET_ZONE_POPULATION_TYPE PARA POPCYCLE_ZONE_RESIDENTIAL_RICH

		// Juniper Hollow
		SET_ZONE_POPULATION_TYPE JUNIHO POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

		// Juniper Hill		
		SET_ZONE_POPULATION_TYPE JUNIHI POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

		// Santa Flora
		SET_ZONE_POPULATION_TYPE CIVI POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

		// Chinatown
		SET_ZONE_POPULATION_TYPE CHINA POPCYCLE_ZONE_SHOPPING
		SET_ZONE_POPULATION_RACE CHINA POPRACE_OW
		SET_ZONE_GANG_STRENGTH CHINA GANG_TRIAD 40	

		// Downtown				
		SET_ZONE_POPULATION_TYPE SFDWT1 POPCYCLE_ZONE_BUSINESS
		SET_ZONE_POPULATION_TYPE SFDWT2 POPCYCLE_ZONE_BUSINESS
		SET_ZONE_POPULATION_TYPE SFDWT3 POPCYCLE_ZONE_BUSINESS
		SET_ZONE_POPULATION_TYPE SFDWT4 POPCYCLE_ZONE_SHOPPING
		SET_ZONE_POPULATION_TYPE SFDWT5 POPCYCLE_ZONE_SHOPPING
		SET_ZONE_POPULATION_TYPE SFDWT6 POPCYCLE_ZONE_SHOPPING

		// Financial
		SET_ZONE_POPULATION_TYPE FINA POPCYCLE_ZONE_BUSINESS

		// City Hall
		SET_ZONE_POPULATION_TYPE CITYS POPCYCLE_ZONE_BUSINESS
		
		// Westpark
		SET_ZONE_POPULATION_TYPE WESTP1 POPCYCLE_ZONE_RESIDENTIAL_RICH
		SET_ZONE_POPULATION_TYPE WESTP2 POPCYCLE_ZONE_RESIDENTIAL_RICH
		SET_ZONE_POPULATION_TYPE WESTP3 POPCYCLE_ZONE_RESIDENTIAL_RICH

		// King's Theatre
		SET_ZONE_POPULATION_TYPE THEA1 POPCYCLE_ZONE_ENTERTAINMENT
		SET_ZONE_POPULATION_TYPE THEA2 POPCYCLE_ZONE_ENTERTAINMENT
		SET_ZONE_POPULATION_TYPE THEA3 POPCYCLE_ZONE_ENTERTAINMENT
		SET_ZONE_GANG_STRENGTH THEA1 GANG_TRIAD 10
		SET_ZONE_GANG_STRENGTH THEA3 GANG_SFMEX 10

		// Garcia
		SET_ZONE_POPULATION_TYPE GARC POPCYCLE_ZONE_GANGLAND
		SET_ZONE_GANG_STRENGTH GARC GANG_SFMEX 40

		// Hashbury
		SET_ZONE_POPULATION_TYPE HASH POPCYCLE_ZONE_RESIDENTIAL_POOR

		// Ocean Flats
		SET_ZONE_POPULATION_TYPE OCEAF1 POPCYCLE_ZONE_BEACH
		SET_ZONE_POPULATION_TYPE OCEAF2 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
		SET_ZONE_POPULATION_TYPE OCEAF3 POPCYCLE_ZONE_BEACH

		// Doherty SF HUB
		SET_ZONE_POPULATION_TYPE DOH1 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
		SET_ZONE_POPULATION_TYPE DOH2 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
		SET_ZONE_GANG_STRENGTH DOH2 GANG_SFMEX 10

		// Avispa Country Club
		SET_ZONE_POPULATION_TYPE CUNTC1 POPCYCLE_ZONE_RESIDENTIAL_RICH
		SET_ZONE_POPULATION_TYPE CUNTC2 POPCYCLE_ZONE_RESIDENTIAL_RICH
		SET_ZONE_POPULATION_TYPE CUNTC3 POPCYCLE_ZONE_RESIDENTIAL_RICH
			//need small zone with golfers in the club
		SET_ZONE_POPULATION_TYPE SFGLF1 POPCYCLE_ZONE_GOLF_CLUB 
		SET_ZONE_POPULATION_TYPE SFGLF2 POPCYCLE_ZONE_GOLF_CLUB 
		SET_ZONE_POPULATION_TYPE SFGLF3 POPCYCLE_ZONE_GOLF_CLUB 
		SET_ZONE_POPULATION_TYPE SFGLF4 POPCYCLE_ZONE_GOLF_CLUB

		// Missionary Hill
		SET_ZONE_POPULATION_TYPE HILLP POPCYCLE_ZONE_PARK

		// Foster Valley
		SET_ZONE_POPULATION_TYPE SILLY1 POPCYCLE_ZONE_BUSINESS
		SET_ZONE_POPULATION_TYPE SILLY2 POPCYCLE_ZONE_BUSINESS
		SET_ZONE_POPULATION_TYPE SILLY3 POPCYCLE_ZONE_BUSINESS
		SET_ZONE_POPULATION_TYPE SILLY4 POPCYCLE_ZONE_BUSINESS

		// Easter Bay Airport
		SET_ZONE_POPULATION_TYPE SFAIR1 POPCYCLE_ZONE_AIRPORT
		SET_ZONE_POPULATION_TYPE SFAIR2 POPCYCLE_ZONE_AIRPORT
		SET_ZONE_POPULATION_TYPE SFAIR3 POPCYCLE_ZONE_AIRPORT
		SET_ZONE_POPULATION_TYPE SFAIR4 POPCYCLE_ZONE_AIRPORT
		SET_ZONE_POPULATION_TYPE SFAIR5 POPCYCLE_ZONE_AIRPORT
			// Small zones for baggage cars
		SET_ZONE_POPULATION_TYPE SFBAG1 POPCYCLE_ZONE_AIRPORT_RUNWAY
		SET_ZONE_POPULATION_TYPE SFBAG2 POPCYCLE_ZONE_AIRPORT_RUNWAY
		SET_ZONE_POPULATION_TYPE SFBAG3 POPCYCLE_ZONE_AIRPORT_RUNWAY

		// Easter Basin
		SET_ZONE_POPULATION_TYPE EASB1 POPCYCLE_ZONE_INDUSTRY
		SET_ZONE_POPULATION_TYPE EASB2 POPCYCLE_ZONE_INDUSTRY
		
		SET_ZONE_GANG_STRENGTH EASB1 GANG_VIET 40
		SET_ZONE_GANG_STRENGTH EASB2 GANG_VIET 40

		// Esplanade East
		SET_ZONE_POPULATION_TYPE ESPE1 POPCYCLE_ZONE_SHOPPING
		SET_ZONE_POPULATION_TYPE ESPE2 POPCYCLE_ZONE_SHOPPING
		SET_ZONE_POPULATION_TYPE ESPE3 POPCYCLE_ZONE_SHOPPING

		SET_ZONE_GANG_STRENGTH EASB1 GANG_VIET 10
		SET_ZONE_GANG_STRENGTH EASB2 GANG_VIET 10
		
// ******************************************VEGAS PEDS  ****************************************************

	// Trainstations	
	SET_ZONE_POPULATION_TYPE LINDEN POPCYCLE_ZONE_RESIDENTIAL_AVERAGE // Linden Station
	SET_ZONE_POPULATION_TYPE YELLOW POPCYCLE_ZONE_RESIDENTIAL_RICH	// Yellow Bell Station

	// Blackfield Chapel
	SET_ZONE_POPULATION_TYPE BFC1 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE 
	SET_ZONE_POPULATION_TYPE BFC2 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

	// Randolph Industrial Estate
	SET_ZONE_POPULATION_TYPE RIE POPCYCLE_ZONE_BUSINESS

	// Last Dime Motel
	SET_ZONE_POPULATION_TYPE LDM POPCYCLE_ZONE_RESIDENTIAL_POOR

	// Rockshore West 
	SET_ZONE_POPULATION_TYPE RSW1 POPCYCLE_ZONE_RESIDENTIAL_POOR
	SET_ZONE_POPULATION_TYPE RSW2 POPCYCLE_ZONE_RESIDENTIAL_POOR

	// Rockshore East
	SET_ZONE_POPULATION_TYPE RSE POPCYCLE_ZONE_RESIDENTIAL_POOR

	// Linden Side
	SET_ZONE_POPULATION_TYPE LDS POPCYCLE_ZONE_INDUSTRY

	// Sobell Rail Yards
	SET_ZONE_POPULATION_TYPE SRY POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

	// Julius Thruway South
	SET_ZONE_POPULATION_TYPE JTS1 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	SET_ZONE_POPULATION_TYPE JTS2 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	
	// LVA Freight Depot
	SET_ZONE_POPULATION_TYPE LVA1 POPCYCLE_ZONE_INDUSTRY
	SET_ZONE_POPULATION_TYPE LVA2 POPCYCLE_ZONE_OUT_OF_TOWN_FACTORY 
	SET_ZONE_POPULATION_TYPE LVA3 POPCYCLE_ZONE_INDUSTRY
	SET_ZONE_POPULATION_TYPE LVA4 POPCYCLE_ZONE_INDUSTRY

	// Las Ventura Airport
	SET_ZONE_POPULATION_TYPE VAIR1 POPCYCLE_ZONE_AIRPORT
	SET_ZONE_POPULATION_TYPE VAIR2 POPCYCLE_ZONE_AIRPORT
	SET_ZONE_POPULATION_TYPE VAIR3 POPCYCLE_ZONE_AIRPORT
		// zone for baggage cars
	SET_ZONE_POPULATION_TYPE LVBAG POPCYCLE_ZONE_AIRPORT_RUNWAY

	// Greengrass College
	SET_ZONE_POPULATION_TYPE GGC1 POPCYCLE_ZONE_PARK
	SET_ZONE_POPULATION_TYPE GGC2 POPCYCLE_ZONE_ENTERTAINMENT

	// Blackfield
	SET_ZONE_POPULATION_TYPE BFLD1 POPCYCLE_ZONE_PARK
	SET_ZONE_POPULATION_TYPE BFLD2 POPCYCLE_ZONE_ENTERTAINMENT

	// Whitewood Estates
	SET_ZONE_POPULATION_TYPE WWE POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	SET_ZONE_POPULATION_TYPE WWE1 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

	// Pilson Intersection
	SET_ZONE_POPULATION_TYPE PINT POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

	// Yellow Bell Golf Course
	SET_ZONE_POPULATION_TYPE YBELL2 POPCYCLE_ZONE_RESIDENTIAL_RICH	
		// Golf course for buggies and golfers
	SET_ZONE_POPULATION_TYPE YBELL1 POPCYCLE_ZONE_GOLF_CLUB

	// Julius Thruway West
	SET_ZONE_POPULATION_TYPE JTW1 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	SET_ZONE_POPULATION_TYPE JTW2 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

	// Julius Thruway North
	SET_ZONE_POPULATION_TYPE JTN1 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	SET_ZONE_POPULATION_TYPE JTN2 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	SET_ZONE_POPULATION_TYPE JTN3 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	SET_ZONE_POPULATION_TYPE JTN4 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	SET_ZONE_POPULATION_TYPE JTN5 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	SET_ZONE_POPULATION_TYPE JTN6 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	SET_ZONE_POPULATION_TYPE JTN7 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	SET_ZONE_POPULATION_TYPE JTN8 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

	// Julius Thruway East
	SET_ZONE_POPULATION_TYPE JTE1 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	SET_ZONE_POPULATION_TYPE JTE2 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	SET_ZONE_POPULATION_TYPE JTE3 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	SET_ZONE_POPULATION_TYPE JTE4 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

	// Harry Gold Parkway
	SET_ZONE_POPULATION_TYPE HGP POPCYCLE_ZONE_RESIDENTIAL_RICH

	// Spinybed
	SET_ZONE_POPULATION_TYPE SPIN POPCYCLE_ZONE_INDUSTRY
	
	// Blackfield Inter.
	SET_ZONE_POPULATION_TYPE BINT1 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	SET_ZONE_POPULATION_TYPE BINT2 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	SET_ZONE_POPULATION_TYPE BINT3 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	SET_ZONE_POPULATION_TYPE BINT4 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

	// Prickle Pine
	SET_ZONE_POPULATION_TYPE PRP1 POPCYCLE_ZONE_RESIDENTIAL_RICH
	SET_ZONE_POPULATION_TYPE PRP2 POPCYCLE_ZONE_RESIDENTIAL_RICH
	SET_ZONE_POPULATION_TYPE PRP3 POPCYCLE_ZONE_RESIDENTIAL_RICH
	SET_ZONE_POPULATION_TYPE PRP4 POPCYCLE_ZONE_RESIDENTIAL_RICH

	// Redsands East
	SET_ZONE_POPULATION_TYPE REDE1 POPCYCLE_ZONE_BUSINESS
	SET_ZONE_POPULATION_TYPE REDE2 POPCYCLE_ZONE_BUSINESS
	SET_ZONE_POPULATION_TYPE REDE3 POPCYCLE_ZONE_ENTERTAINMENT

	// Redsands West
	SET_ZONE_POPULATION_TYPE REDW1 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	SET_ZONE_POPULATION_TYPE REDW2 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE
	SET_ZONE_POPULATION_TYPE REDW3 POPCYCLE_ZONE_INDUSTRY
	SET_ZONE_POPULATION_TYPE REDW4 POPCYCLE_ZONE_RESIDENTIAL_AVERAGE

	// Roca Escalante
	SET_ZONE_POPULATION_TYPE ROCE1 POPCYCLE_ZONE_BUSINESS
	SET_ZONE_POPULATION_TYPE ROCE2 POPCYCLE_ZONE_BUSINESS

	// Old Venturas Strip
	SET_ZONE_POPULATION_TYPE OVS POPCYCLE_ZONE_ENTERTAINMENT_BUSY 

	// Creek
	SET_ZONE_POPULATION_TYPE CREE POPCYCLE_ZONE_SHOPPING

	// The Strip
	SET_ZONE_POPULATION_TYPE STRIP1 POPCYCLE_ZONE_ENTERTAINMENT_BUSY
	SET_ZONE_POPULATION_TYPE STRIP2 POPCYCLE_ZONE_ENTERTAINMENT_BUSY
	SET_ZONE_POPULATION_TYPE STRIP3 POPCYCLE_ZONE_ENTERTAINMENT_BUSY
	SET_ZONE_POPULATION_TYPE STRIP4 POPCYCLE_ZONE_ENTERTAINMENT_BUSY

	// Pilgrim
	SET_ZONE_POPULATION_TYPE PILL1 POPCYCLE_ZONE_SHOPPING
	SET_ZONE_POPULATION_TYPE PILL2 POPCYCLE_ZONE_SHOPPING

	// The Ring Master
	SET_ZONE_POPULATION_TYPE RING POPCYCLE_ZONE_ENTERTAINMENT_BUSY

	// The Emeral Isle
	SET_ZONE_POPULATION_TYPE ISLE POPCYCLE_ZONE_ENTERTAINMENT_BUSY

	// Starfish Casino
	SET_ZONE_POPULATION_TYPE STAR1 POPCYCLE_ZONE_ENTERTAINMENT_BUSY
	SET_ZONE_POPULATION_TYPE STAR2 POPCYCLE_ZONE_ENTERTAINMENT_BUSY

	// Royale Casino
	SET_ZONE_POPULATION_TYPE ROY POPCYCLE_ZONE_ENTERTAINMENT_BUSY

	// The Camel's Toe
	SET_ZONE_POPULATION_TYPE CAM POPCYCLE_ZONE_ENTERTAINMENT_BUSY

	// Come-A-Lot
	SET_ZONE_POPULATION_TYPE LOT POPCYCLE_ZONE_ENTERTAINMENT_BUSY

	// Pirates In Men's Pants
	SET_ZONE_POPULATION_TYPE PIRA POPCYCLE_ZONE_ENTERTAINMENT_BUSY

	// Visage
	SET_ZONE_POPULATION_TYPE VISA1 POPCYCLE_ZONE_ENTERTAINMENT_BUSY
	SET_ZONE_POPULATION_TYPE VISA2 POPCYCLE_ZONE_ENTERTAINMENT_BUSY

	// Caligula's Palace
	SET_ZONE_POPULATION_TYPE CALI1 POPCYCLE_ZONE_ENTERTAINMENT_BUSY
	SET_ZONE_POPULATION_TYPE CALI2 POPCYCLE_ZONE_ENTERTAINMENT_BUSY

	// Four Dragons Casino
	SET_ZONE_POPULATION_TYPE DRAG POPCYCLE_ZONE_ENTERTAINMENT_BUSY

	// The Pink Swan
	SET_ZONE_POPULATION_TYPE PINK POPCYCLE_ZONE_ENTERTAINMENT_BUSY

	// The High Roller
	SET_ZONE_POPULATION_TYPE HIGH POPCYCLE_ZONE_ENTERTAINMENT_BUSY
	
	// Construction zone near starfish casino
	SET_ZONE_POPULATION_TYPE CONST1 POPCYCLE_ZONE_OUT_OF_TOWN_FACTORY

// ******************************************COUNTRYSIDE & DESERT PEDS  ****************************************************

	SET_ZONE_POPULATION_TYPE ANGPI POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE SHACA POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE BACKO POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE BEACO POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE FARM POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE PALO POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE MONT  POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE MONT1 POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE MTCHI1 POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE MTCHI2 POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE MTCHI3 POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE MTCHI4 POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE HBARNS POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE DILLI POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE TOPFA POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE BLUEB POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE BLUEB1 POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE PANOP POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE BLUAC POPCYCLE_ZONE_COUNTRYSIDE 
	SET_ZONE_POPULATION_TYPE CREEK POPCYCLE_ZONE_COUNTRYSIDE 
	SET_ZONE_POPULATION_TYPE CREEK1 POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE MAKO POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE TOPFA POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE FERN POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE NROCK POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE HANKY POPCYCLE_ZONE_COUNTRYSIDE
    SET_ZONE_POPULATION_TYPE LEAFY POPCYCLE_ZONE_COUNTRYSIDE
	SET_ZONE_POPULATION_TYPE RED POPCYCLE_ZONE_COUNTRYSIDE //Red Country
    SET_ZONE_POPULATION_TYPE FLINTC POPCYCLE_ZONE_COUNTRYSIDE // Flint County  
	SET_ZONE_POPULATION_TYPE WHET POPCYCLE_ZONE_COUNTRYSIDE // Whetstone                    

	SET_ZONE_POPULATION_TYPE BIGE POPCYCLE_ZONE_DESERT
	SET_ZONE_POPULATION_TYPE ELQUE POPCYCLE_ZONE_DESERT 
	SET_ZONE_POPULATION_TYPE BARRA POPCYCLE_ZONE_DESERT
	SET_ZONE_POPULATION_TYPE CARSO POPCYCLE_ZONE_DESERT
	SET_ZONE_POPULATION_TYPE PAYAS POPCYCLE_ZONE_DESERT
	SET_ZONE_POPULATION_TYPE PROBE POPCYCLE_ZONE_DESERT
	SET_ZONE_POPULATION_TYPE ELCA POPCYCLE_ZONE_DESERT
	SET_ZONE_POPULATION_TYPE FLINTR POPCYCLE_ZONE_DESERT
	SET_ZONE_POPULATION_TYPE ARCO POPCYCLE_ZONE_DESERT
	SET_ZONE_POPULATION_TYPE VALLE POPCYCLE_ZONE_DESERT
	SET_ZONE_POPULATION_TYPE FLINTI POPCYCLE_ZONE_DESERT
	SET_ZONE_POPULATION_TYPE MONINT POPCYCLE_ZONE_DESERT
	SET_ZONE_POPULATION_TYPE ROBINT POPCYCLE_ZONE_DESERT
	SET_ZONE_POPULATION_TYPE TOM POPCYCLE_ZONE_DESERT
	SET_ZONE_POPULATION_TYPE ALDEA POPCYCLE_ZONE_DESERT
	SET_ZONE_POPULATION_TYPE BRUJA POPCYCLE_ZONE_DESERT
	SET_ZONE_POPULATION_TYPE ROBAD POPCYCLE_ZONE_DESERT // Tierra Robada  
	SET_ZONE_POPULATION_TYPE BONE POPCYCLE_ZONE_DESERT // Bone County

	SET_ZONE_POPULATION_TYPE HAUL POPCYCLE_ZONE_OUT_OF_TOWN_FACTORY
	SET_ZONE_POPULATION_TYPE QUARY POPCYCLE_ZONE_OUT_OF_TOWN_FACTORY
	SET_ZONE_POPULATION_TYPE OCTAN POPCYCLE_ZONE_OUT_OF_TOWN_FACTORY
	SET_ZONE_POPULATION_TYPE PALMS POPCYCLE_ZONE_OUT_OF_TOWN_FACTORY
	SET_ZONE_POPULATION_TYPE EBAY POPCYCLE_ZONE_OUT_OF_TOWN_FACTORY
	SET_ZONE_POPULATION_TYPE EBAY2 POPCYCLE_ZONE_OUT_OF_TOWN_FACTORY
	SET_ZONE_POPULATION_TYPE DAM POPCYCLE_ZONE_OUT_OF_TOWN_FACTORY


//////////////////////////////////////////////////////////////////////////////////////
//////////////////                      PICKUPS	             /////////////////////////
//////////////////////////////////////////////////////////////////////////////////////



// Horseshoes in vegas
VAR_INT pickup_horseshoe[50] 

CREATE_HORSESHOE_PICKUP 1224.0 2617.0 11.0 pickup_horseshoe[0]				// Prickle Pine, In the porch of this house in LV suburbs. 
CREATE_HORSESHOE_PICKUP 2323.0 1284.0 97.0 pickup_horseshoe[1]				// Camel's Toe, Right at the top of the big pyramid 
CREATE_HORSESHOE_PICKUP 2035.0 2305.0 18.0 pickup_horseshoe[2]				// Emerald Isle - Frog's Pawn Shop, Go up the stairs at the gym, jump on to the next roof, get on to the thin piece of wall, take a run, jump and cling on to the next roof above the Frog's Pawn store. 
CREATE_HORSESHOE_PICKUP 2491.0 2263.0 15.0 pickup_horseshoe[3]				// Roca Escalante : Erotic Wedding Chapel - Climb the box at the front of the chapel and jump on to the roof. 
CREATE_HORSESHOE_PICKUP 1433.0 2796.0 20.0 pickup_horseshoe[4]				// Yellow Bell Golf House, On the roof of the golf house. The roof is fairly low to the ground and easy to get on to from the roof of a vehicle or wall. 
CREATE_HORSESHOE_PICKUP 2071.0 0712.0 11.0 pickup_horseshoe[5]				// Rockshore West, In the back garden of this house in the suburbs. 
CREATE_HORSESHOE_PICKUP 2239.0 1839.0 18.0 pickup_horseshoe[6]				// The Ring Master, On top of the ring master building. Player must climb a wall to gain access to the roof. Would be better on the coloured roof, but that can't be walked up. 
CREATE_HORSESHOE_PICKUP 2583.0 2387.0 16.0 pickup_horseshoe[7]				// Roca Escalante: Rock Hotel, In the middle of the guitar shaped swimming pool in the Rock Hotel. 
CREATE_HORSESHOE_PICKUP 2864.0 0857.0 13.0 pickup_horseshoe[8]				// Rockshore East, Inside a skip behind a warehouse in this compound. 
CREATE_HORSESHOE_PICKUP 2612.0 2200.0 -1.0 pickup_horseshoe[9]				// Old Venturas Railway Tunnel, Follow the tunnel along from the entrance in Roca Escalante, until the entrance can't be seen any more, so the package is hidden from view. 
CREATE_HORSESHOE_PICKUP 2274.0 1507.0 24.0 pickup_horseshoe[10]				// Royale Casino Car Park, On trhe third floor of this multi storey car park, hidden out of sight. 
CREATE_HORSESHOE_PICKUP 2184.0 2529.0 11.0 pickup_horseshoe[11]				// Julius Thruway North, Hidden in this small garage enclosure.
CREATE_HORSESHOE_PICKUP 1863.0 2314.0 15.0 pickup_horseshoe[12]				// Redsands East, On top of this roof. Vehicle / heli required. 
CREATE_HORSESHOE_PICKUP 2054.0 2434.0 166.0 pickup_horseshoe[13]				// Las Venturas, Land a heli on the small area at the very top of this building. 
CREATE_HORSESHOE_PICKUP 1603.0 1435.0 11.0 pickup_horseshoe[14]				// Las Venturas Airport, Beneath a staircase round the back of the airport.
CREATE_HORSESHOE_PICKUP 1362.92 1015.24 11.0 pickup_horseshoe[15]				// LVA Freight Depot, Hidden in the small gap between the warehouse and the wall. 
CREATE_HORSESHOE_PICKUP 2058.7 2159.10 16.0 pickup_horseshoe[16]				// Redsands East, Get up on the roof of the motel and run round to the back to find the horseshoe on the sheltered balcony 
CREATE_HORSESHOE_PICKUP 2003.0 1672.0 12.0 pickup_horseshoe[17]				// The Pirates in Men's Pants, Package in a cool place, slightly obscured by bushes. 
CREATE_HORSESHOE_PICKUP 2238.0 1135.0 49.0 pickup_horseshoe[18]				// Come-A-Lot, Land a heli on this parapet to get the goods. 
CREATE_HORSESHOE_PICKUP 1934.06 0988.79 22.0 pickup_horseshoe[19]				// The Four Dragons Casino, In the dark garage beneath the Four Dragons Casino. 
CREATE_HORSESHOE_PICKUP 1768.0 2847.0 09.0 pickup_horseshoe[20]				// Prickle Pine, In this shallow pool within some housing. 
CREATE_HORSESHOE_PICKUP 1084.0 1076.0 11.0 pickup_horseshoe[21]				// Greenglass College, In the front garden of the college. Easy to get. 
CREATE_HORSESHOE_PICKUP 2879.0 2522.0 11.0 pickup_horseshoe[22]				// Creek, Hidden behind this mall on the outskirts of town. 
CREATE_HORSESHOE_PICKUP 2371.0 2009.0 15.0 pickup_horseshoe[23]				// Starfish Casino, Use a vehicle to access this thin piece of roofing. 
CREATE_HORSESHOE_PICKUP 1521.0 1690.0 10.6 pickup_horseshoe[24]				// Las Venturas Airport, Heli required to access this roof. 
CREATE_HORSESHOE_PICKUP 2417.0 1281.0 21.0 pickup_horseshoe[25]				// The Camel's Toe, Get up on the sphinx, turn on to the walkway and follow it round to the other side of the pyramid. 
CREATE_HORSESHOE_PICKUP 1376.0 2304.0 15.0 pickup_horseshoe[26]				// Pilson Intersection, Use a vehicle to get on to the wall. 
CREATE_HORSESHOE_PICKUP 1393.0 1832.0 12.34 pickup_horseshoe[27]				// Las Venturas Airport, Hidden from view by some bushes and such. 
CREATE_HORSESHOE_PICKUP 0984.0 2563.0 12.0 pickup_horseshoe[28]				// Las Venturas, Under the Welcome sign. 
CREATE_HORSESHOE_PICKUP 1767.0 0601.0 13.0 pickup_horseshoe[29]				// Randolph Industrial Estate, Hidden below this bridge on the way out of town. 
CREATE_HORSESHOE_PICKUP 2108.0 1003.0 46.0 pickup_horseshoe[30]				// Come-A-Lot, Jetpack required. At the top of the Come-a-Lot sign. 
CREATE_HORSESHOE_PICKUP 2705.98 1862.52 24.41 pickup_horseshoe[31]				// Juluis Thruway East, Jetpack only. The collision for the middle of this sign needs to be altered, so maybe the Z value of this needs to be increased by a metre or so. 
CREATE_HORSESHOE_PICKUP 2493.0 0922.0 16.0 pickup_horseshoe[32]				// Las Venturas, On the roof of this wedding chapel, behind the parapet, This can be accessed from ground level. 
CREATE_HORSESHOE_PICKUP 1881.0 2846.0 11.0 pickup_horseshoe[33]				// Prickle Pine, Stashed behind the gate to one of the tennis courts in this motel complex. 
CREATE_HORSESHOE_PICKUP 2020.0 2352.0 11.0 pickup_horseshoe[34]				// Emerald Isle, In this small enclosure near the Emerald Isle. 
CREATE_HORSESHOE_PICKUP 1680.30 2226.86 16.11 pickup_horseshoe[35]				// Redsands West, On the roof of the Steakhouse restaurant, away from the road to conceal it more. 
CREATE_HORSESHOE_PICKUP 1462.0 0936.0 10.0 pickup_horseshoe[36]				// LvA Freight Depot, In this slightly sloped factory entrance. 
CREATE_HORSESHOE_PICKUP 2125.50 0789.23 11.45 pickup_horseshoe[37]			 // Rockshore West, Beside the bin in this garden [74] 				// Rockshore West, Beside the bin in this garden 
CREATE_HORSESHOE_PICKUP 2588.0 1902.0 15.0 pickup_horseshoe[38]				// Starfish Casino, Ledge currently has no colision, but would need a vehicle / jetpack to access. 
CREATE_HORSESHOE_PICKUP 0919.0 2070.0 11.0 pickup_horseshoe[39]				// Whitewood Estates, At the back entrance of this warehouse. 
CREATE_HORSESHOE_PICKUP 2173.0 2465.0 11.0 pickup_horseshoe[40]				// Emerald Isle, Hidden in a small garage complex. 
CREATE_HORSESHOE_PICKUP 2031.25 2207.33 11.0  pickup_horseshoe[41]				// The Emerald Isle,
CREATE_HORSESHOE_PICKUP 2509.0 1144.0 19.0 pickup_horseshoe[42]				// Come-A-Lot, Slightly hidden in the little alcove (player has to drop down onto balcony from roof above)
CREATE_HORSESHOE_PICKUP 2215.0 1968.0 11.0 pickup_horseshoe[43]				// Starfish Casino, in the corner of this dark alleyway
CREATE_HORSESHOE_PICKUP 2626.0 2841.0 11.0 pickup_horseshoe[44]				// K.A.C.C Military Fuels, in the little slightly secluded alleyway. Easily spottable when playing Heist5
CREATE_HORSESHOE_PICKUP 2440.08 2161.07 20.0 pickup_horseshoe[45]				// Old Venturas Strip, 'hidden' in the O of HOTEL. Probably only accessible via flight
CREATE_HORSESHOE_PICKUP 1582.0 2401.0 19.0 pickup_horseshoe[46]				// Redsands West, on top of packing crates, accessible by jumping onto wall and across to crates
CREATE_HORSESHOE_PICKUP 2077.0 1912.0 14.0 pickup_horseshoe[47]				// The Visage, underneath the water fall
CREATE_HORSESHOE_PICKUP 0970.0 1787.0 11.0 pickup_horseshoe[48]				// Whitewood Estates, in the back garden of this house on the edge of town
CREATE_HORSESHOE_PICKUP 1526.22 0751.0 29.04 pickup_horseshoe[49]				// Blackfiel Chapel, On the roof of the sloped chapel 


// Photos in San Fran
															
															
VAR_INT location_photo[50] 
CREATE_SNAPSHOT_PICKUP -2511.28 -672.99 195.75     location_photo[0]        // the structure at the top of missionary hill
CREATE_SNAPSHOT_PICKUP -2723.63 -314.72 55.79     location_photo[1]        // The arched tower on the top of the club         
CREATE_SNAPSHOT_PICKUP -1737.71 -579.55 26.19     location_photo[2]        // Aeroplane sign at the entrance to the airport          
CREATE_SNAPSHOT_PICKUP -1486.08 920.04 41.37      location_photo[3]         // Clock Face on the clock tower    
CREATE_SNAPSHOT_PICKUP -1269.82 963.63 130.37  location_photo[4]              // Carver Bridge middle support 
CREATE_SNAPSHOT_PICKUP -1650.01 422.00 21.17      location_photo[5]          // Xoomer gas station sign        
CREATE_SNAPSHOT_PICKUP -1851.72 -96.73 24.37           location_photo[6]     // Gas tanks/towers Solarin industries 
CREATE_SNAPSHOT_PICKUP -2732.0 -244.0 19.0      location_photo[7]         // Tennis court nets
CREATE_SNAPSHOT_PICKUP -2802.75 375.47 36.59   location_photo[8]           // Dome on top of building
CREATE_SNAPSHOT_PICKUP -2773.04 783.45 67.66      location_photo[9]       // tough nuts donuts sign
CREATE_SNAPSHOT_PICKUP -2680.07 1590.80 143.53     location_photo[10]          // Gant Bridge SF side support (top)  
CREATE_SNAPSHOT_PICKUP -2476.75 1543.44 49.26     location_photo[11]         // Da nang thang boat comms mast        
CREATE_SNAPSHOT_PICKUP -1879.04 1456.52 9.34   location_photo[12]              // Submarine's conning tower      
CREATE_SNAPSHOT_PICKUP -1561.55 655.19 56.52    location_photo[13]              // One of the Kincaid bridge girders SF side
CREATE_SNAPSHOT_PICKUP -1325.15 494.19 26.83   location_photo[14]              // Aircraft carrier bridge
CREATE_SNAPSHOT_PICKUP -1941.41 137.72 37.83      location_photo[15]             // Cranberry station middle roof support    
CREATE_SNAPSHOT_PICKUP -2153.23 462.25 103.27     location_photo[16]              // Top of this chinatown building, east side of tower
CREATE_SNAPSHOT_PICKUP -2243.96 577.76 49.0      location_photo[17]               // WANGS KUNGFU SCHOOL 
CREATE_SNAPSHOT_PICKUP -2051.0 456.0 167.0     location_photo[18]               // Top of King's building, middle two "flag poles."  
CREATE_SNAPSHOT_PICKUP -1951.0 659.0 81.0      location_photo[19]              // Sculpture in front of building    
CREATE_SNAPSHOT_PICKUP -2064.0 926.0 63.0      location_photo[20]             // the middle of the curviest road   
CREATE_SNAPSHOT_PICKUP -2357.33 1017.01 59.76   location_photo[21]               // burger sign
CREATE_SNAPSHOT_PICKUP -2072.0 1066.0 74.0  location_photo[22]               // lampost in the middle of the road at tunnel entrance
CREATE_SNAPSHOT_PICKUP -1744.0 972.46 156.89 location_photo[23] 				// Aerials on top of this financial building
CREATE_SNAPSHOT_PICKUP -1941.0 883.0 68.0   location_photo[24]              // Baseball player statue
CREATE_SNAPSHOT_PICKUP -1839.51 1086.88 101.29     location_photo[25]               // Middle of building, nice lights  
CREATE_SNAPSHOT_PICKUP -1704.8 1338.0 14.0     location_photo[26]                 // Pier 69 sign      
CREATE_SNAPSHOT_PICKUP -2346.62 536.85 86.02      location_photo[27]                // Chimney on top of building  
CREATE_SNAPSHOT_PICKUP -2443.0 755.0 49.0      location_photo[28]                // Supasave entrance 
CREATE_SNAPSHOT_PICKUP -2765.0 375.0 15.0   location_photo[29]              // Middle two columns, front of city hall
CREATE_SNAPSHOT_PICKUP -2880.31 -935.83 40.82     location_photo[30]           // The first beam passed under on this bridge on the way out of SF     
CREATE_SNAPSHOT_PICKUP -2083.0 -808.0 69.00    location_photo[31]           // The middle round tower in Foster Valley       
CREATE_SNAPSHOT_PICKUP -1954.0 -760.0 53.00    location_photo[32]           // Plants in the middle of the roof garden       
CREATE_SNAPSHOT_PICKUP -964.53 -331.59 47.16   location_photo[33]          // The bridge visible from the SF airport runway area (the middle support)
CREATE_SNAPSHOT_PICKUP -1689.0 51.0 38.0   location_photo[34]         // The lip of the dry dock in easter basin
CREATE_SNAPSHOT_PICKUP -2080.0 256.05 107.0  location_photo[35]         //The red tip of the large yellow crane in Kings           
CREATE_SNAPSHOT_PICKUP -2413.0 331.0 37.0      location_photo[36]              // Entrance to the Vank Hoff Park hotel     
CREATE_SNAPSHOT_PICKUP -2244.42 731.32 61.88      location_photo[37]             // Middle roof Chinatown Gateway   
CREATE_SNAPSHOT_PICKUP -2462.0 369.0 59.0      location_photo[38]              // The back of the Vank Hoff Hotel  
CREATE_SNAPSHOT_PICKUP -1124.44 -153.15 18.50     location_photo[39]                // Airport storage tanks
CREATE_SNAPSHOT_PICKUP -1275.78 53.68 89.07       location_photo[40]               // ATC tower bridge 
CREATE_SNAPSHOT_PICKUP -2430.0 38.0 51.0             location_photo[41]          // Save The Whale land sign
CREATE_SNAPSHOT_PICKUP -2591.0 162.0 15.0 location_photo[42]                 // gay movie theatre
CREATE_SNAPSHOT_PICKUP -2591.0 -16.0 17.0       location_photo[43]                // One of the middle gravestones      
CREATE_SNAPSHOT_PICKUP -2648.0 -5.0 31.0             location_photo[44]          // Ocean Flats Church spire
CREATE_SNAPSHOT_PICKUP -2593.0 56.0 16.0             location_photo[45]          // A photo of hippy shopper
CREATE_SNAPSHOT_PICKUP -1619.31 1341.39 11.30     location_photo[46]            //A photo of the rock formation in the bay
CREATE_SNAPSHOT_PICKUP -2307.0 207.0 42.0      location_photo[47]                // Baseball diamond, home base/hitters plate 
CREATE_SNAPSHOT_PICKUP -2343.0 -79.0 38.0      location_photo[48]              // A picture of the construction sign  
CREATE_SNAPSHOT_PICKUP -1906.66 518.58 61.71     location_photo[49]      //between 2 skyscrapers


VAR_INT pickup_oyster[50]

   ////Los Santos	 				  
CREATE_OYSTER_PICKUP 979.0 -2210.0 -3.0		pickup_oyster[0]								//Beside the entrance to the Verdant Bluffs tunnel
CREATE_OYSTER_PICKUP 2750.0 -2584.0 -5.0	pickup_oyster[1]						 //Beside an Ocean Docks pier
CREATE_OYSTER_PICKUP 1279.0 -806.0 85.0 	pickup_oyster[2]						//Inside Doc G's pool
CREATE_OYSTER_PICKUP 2945.13 -2051.93 -3.0	pickup_oyster[3]						 // End of this playa del seville beach pier
CREATE_OYSTER_PICKUP 67.0 -1018.0 -5.0 		pickup_oyster[4]								//Under this rail bridge just outside of the city
CREATE_OYSTER_PICKUP 2327.0 -2662.0 -5.0 	pickup_oyster[5]					//under flat docks bridge
CREATE_OYSTER_PICKUP 2621.0 -2506.0 -5.0 	pickup_oyster[6]					//under another docks bridge
CREATE_OYSTER_PICKUP 1249.0 -2687.0 -1.0 	pickup_oyster[7]					//Beach beside the airport
CREATE_OYSTER_PICKUP 725.0 -1849.0 -5.0 	pickup_oyster[8]					//under Verona Beach pedestrian bridge
CREATE_OYSTER_PICKUP 723.0 -1586.0 -3.0 	pickup_oyster[9]					// Under this flood control bridge in the Marina area
CREATE_OYSTER_PICKUP 155.0 -1975.0 -8.0 	pickup_oyster[10]				//south of the lighthouse at Santa Maria beach
CREATE_OYSTER_PICKUP 1968.0 -1203.0 17.0	pickup_oyster[11]				 //under park bridge in pond
CREATE_OYSTER_PICKUP -2657.0 1564.0 -6.0  	pickup_oyster[12]			//Landing point for a USJ under the Gant bridge   <=====================// *************** 	San Fier
CREATE_OYSTER_PICKUP -1252.0 501.0 -8.0  	pickup_oyster[13]			// At the bow of the aircraft carrier in Easter Bay
CREATE_OYSTER_PICKUP -1625.0 4.0 -10.0 		pickup_oyster[14]				//under the stern of the large freighter in dock
CREATE_OYSTER_PICKUP -1484.0 1489.0 -10.0 	pickup_oyster[15]			//At the bow of the freighter in the middle of the bay
CREATE_OYSTER_PICKUP -2505.4058 1543.7236 -22.5553	pickup_oyster[16]			// under the BOW of the Da Nang Thang ship in SF bay
CREATE_OYSTER_PICKUP -2727.0 -469.0 -5.0  	pickup_oyster[17]			//in a pool on missionary hill behind avispa country club
CREATE_OYSTER_PICKUP -1266.0 966.0 -10.0 	pickup_oyster[18]			//under the water bound support of the Garver Bridge
CREATE_OYSTER_PICKUP -1013.0 478.0 -7.0  	pickup_oyster[19]			//End of the Easter Bay Airport runway
CREATE_OYSTER_PICKUP -1364.0 390.0 -5.0  	pickup_oyster[20]			//In an Easter bay dock
CREATE_OYSTER_PICKUP 2578.0 2382.0 16.0 	pickup_oyster[21]				//Under the diving board at the VRock hotel pool	 	// ******************Las Venturas 
CREATE_OYSTER_PICKUP 2090.0 1898.0 8.0 		pickup_oyster[22]			// Under a Visage waterfall
CREATE_OYSTER_PICKUP 2130.0 1152.0 7.0 		pickup_oyster[23]			// in the Come a Lot moat
CREATE_OYSTER_PICKUP 2013.0 1670.0 7.0 		pickup_oyster[24]			// Pirates in mens pants, in front of the skull sculpture 
CREATE_OYSTER_PICKUP 2531.0 1569.0 9.0 		pickup_oyster[25]			//in the pool in front of the pilgrim
CREATE_OYSTER_PICKUP 2998.0 2998.0 -10.0	pickup_oyster[26]			 		//Northeast Corner of the map  // *&******************8//Countryside/Desert	 
CREATE_OYSTER_PICKUP -832.0 925.0 -2.0	 	pickup_oyster[27]				//Tierra Robada tributary overlooking SF bay
CREATE_OYSTER_PICKUP 486.0 -253.0 -4.0		pickup_oyster[28]					 //Under these two red county bridges near Blueberry 
CREATE_OYSTER_PICKUP -90.0 -910.0 -5.0		pickup_oyster[29]					 // Under this bridge which connects Flint County to Red County
CREATE_OYSTER_PICKUP 26.43 -1320.94	-10.04	pickup_oyster[30]				 //Between the motorway bridges which lead out of LS towards SF
CREATE_OYSTER_PICKUP -207.0 -1682.0	-8.0	pickup_oyster[31]				 //Flint County where the beach meets the cliffs
CREATE_OYSTER_PICKUP -1672.0 -1641.0 -2.0	pickup_oyster[32]				 //Under this large Whetstone bridge
CREATE_OYSTER_PICKUP -1175.0 -2639.0 -2.50	pickup_oyster[33]				 // Under this Flint County rickety wooden bridge
CREATE_OYSTER_PICKUP -1097.0 -2858.0 -8.0	pickup_oyster[34]				 // Under a lofty flint county bridge beside the open sea
CREATE_OYSTER_PICKUP -2889.0 -1042.0 -9.0	pickup_oyster[35]				 //Under the bridge leading from Chiliad to SF
CREATE_OYSTER_PICKUP -659.0 874.0 -2.0		pickup_oyster[36]			 //under the water in this boat shed at Toreno's Tierra Robada mission point
CREATE_OYSTER_PICKUP -955.0 2628.0 35.0		pickup_oyster[37]			 //Tierra Robada where the user lands from the Valle Occultado USJ
CREATE_OYSTER_PICKUP -1066.0 2197.0 32.0	pickup_oyster[38]				 //Tierra Robada under the wooden bridge near the dam
CREATE_OYSTER_PICKUP 40.0 -531.0 -8.0		pickup_oyster[39]			 //under a red county bridge near Blueberry
CREATE_OYSTER_PICKUP -765.0 247.0 -8.0		pickup_oyster[40]			 //The Panoptican Just off a beach, should be visible from the road
CREATE_OYSTER_PICKUP 2098.0 -108.0 -2.0		pickup_oyster[41]			 //Fishermans Creek at the end of the pier
CREATE_OYSTER_PICKUP 2767.0 470.0 -8.0		pickup_oyster[42]			 //Underneath the middle of the Frederick Bridge
CREATE_OYSTER_PICKUP -783.0 2116.0 35.0		pickup_oyster[43]			 //Sherman Dam underneath the western control towers
CREATE_OYSTER_PICKUP -821.0 1374.0 -8.0		pickup_oyster[44]			 //Tierra Robada under the metal bridge beside the los Barrancas wigwam motel
CREATE_OYSTER_PICKUP -2110.50 2329.72 -7.50 pickup_oyster[45]				 //Tierra Robada in the water beside the lighthouse
CREATE_OYSTER_PICKUP -1538.0 1708.0 -3.27	pickup_oyster[46]				 // Tierra Robada hidden from the motorist's view by a small roadside wall
CREATE_OYSTER_PICKUP -2685.0 2153.0 -5.0	pickup_oyster[47]				 //between the Gant Bridges Northernmost support and the cliff
CREATE_OYSTER_PICKUP 796.0 2939.0 -5.0		pickup_oyster[48]			 //Bone county beach near Las Venturas
CREATE_OYSTER_PICKUP 2179.0 235.0 -5.0		pickup_oyster[49]			 //Under this Red County Bridge near Palomino Creek


//////////////////////////////////////////////////////////////
// WEAPON PICKUPS
//////////////////////////////////////////////////////////////


///////////////////////////////													   					 
// CREATING LV 	WEAPON PICKUPS																		 
//////////////////////////////																		 

VAR_INT pickup_weapon[217]
VAR_INT goggle_pickups[8]

CREATE_PICKUP_WITH_AMMO MOLOTOV PICKUP_ON_STREET_SLOW 10 2832.0 2405.0 18.0	 			pickup_weapon[0]		 // Creek, on mall roof
CREATE_PICKUP_WITH_AMMO SPRAYCAN PICKUP_ON_STREET_SLOW 500 2819.0 1663.0 11.0	 		pickup_weapon[1]			 // Sobell Rail Yards, inbetween two of the train yard huts
CREATE_PICKUP_WITH_AMMO TEARGAS PICKUP_ON_STREET_SLOW 10 2725.0 2727.0 11.0	 			pickup_weapon[2]		 // K.A.C.C Military Fuels, resting on grating in top of smoke stack
CREATE_PICKUP_WITH_AMMO FLAME PICKUP_ON_STREET_SLOW 2000 2649.0 2733.0	11.0	 		pickup_weapon[3]			  // K.A.C.C Military Fuels, dark little corner behind main KACC building		
CREATE_PICKUP_WITH_AMMO fire_ex PICKUP_ON_STREET_SLOW 3000 2148.0 2721.0 11.0	 		pickup_weapon[4]			  
CREATE_PICKUP_WITH_AMMO chromegun PICKUP_ON_STREET_SLOW 30 1345.0 2367.0	11.0		pickup_weapon[5]				  //Pilson Intersection, underneath the overpass
CREATE_PICKUP_WITH_AMMO AK47 PICKUP_ON_STREET_SLOW 60 1625.0	1944.0 11.0	  			pickup_weapon[6]					// Redsands West, hidden in the bushes in the middle of this apartment complex
CREATE_PICKUP_WITH_AMMO SAWNOFF PICKUP_ON_STREET_SLOW 25 1569.0 2150.0 11.0	 			pickup_weapon[7]		 		// Redsands West, slightly hidden behind skip
CREATE_PICKUP_WITH_AMMO rocketla PICKUP_ON_STREET_SLOW 10 2072.0 2370.0 61.0	 		pickup_weapon[8]			  // The Emerald Isle, in corner on top of building, near helipad
CREATE_PICKUP_WITH_AMMO sniper PICKUP_ON_STREET_SLOW 20 2225.0 2530.0 17.0	 			pickup_weapon[9]		  // Julius Thruway North, Hidden from road behind steeple
CREATE_PICKUP_WITH_AMMO sniper PICKUP_ON_STREET_SLOW 20 2337.0 1806.0 72.0	   			pickup_weapon[10]			// The Ring Master, On corner of building opposite Adrenaline
CREATE_PICKUP_WITH_AMMO M4 PICKUP_ON_STREET_SLOW 70 2575.0 1562.0 16.0	   				pickup_weapon[11]					// Pilgrim, Behind flat bit of sloped roof on top of smaller motel building
CREATE_PICKUP_WITH_AMMO mp5lng PICKUP_ON_STREET_SLOW 70 2243.0 1132.0 11.0				pickup_weapon[12]				   // Come-A-Lot, in the little hidey hole round the side of the big castle
CREATE_PICKUP_WITH_AMMO minigun PICKUP_ON_STREET_SLOW 200 2676.0 0836.0 22.0			pickup_weapon[13]					   // Rockshore East, in the scaffolding (highest bit accesible by climbing)
CREATE_PICKUP_WITH_AMMO tec9 PICKUP_ON_STREET_SLOW 50 1761.0	0591.0 10.0	   			pickup_weapon[14]				// Red County, underneath bridge
CREATE_PICKUP_WITH_AMMO GRENADE PICKUP_ON_STREET_SLOW 20 2809.0 0864.0 21.0				pickup_weapon[15]				   // Rockshore East, behind the air duct poking out the roof
CREATE_PICKUP_WITH_AMMO AK47 PICKUP_ON_STREET_SLOW 60 1923.0	1011.0 22.0	   			pickup_weapon[16]				// The Four Dragons Casino, in a little secluded corner of the roof of the casino (probably only accesible by flight)
CREATE_PICKUP_WITH_AMMO shotgspa PICKUP_ON_STREET_SLOW 50 1407.0	1098.0 11.0	 		pickup_weapon[17]						  // LVA Freight Depot, in the bushes in the small flowerbed
CREATE_PICKUP_WITH_AMMO TEARGAS PICKUP_ON_STREET_SLOW 10 1319.0 1636.0 10.6	 			pickup_weapon[18]					  // Las Venturas Airport, Inbetween big oil tank things
CREATE_PICKUP_WITH_AMMO MICRO_UZI PICKUP_ON_STREET_SLOW 60 1446.35 1900.03 11.0			pickup_weapon[19]			   // Inbetween wall and skip
CREATE_PICKUP_WITH_AMMO silenced PICKUP_ON_STREET_SLOW 30 1098.0 1681.0 07.0	 		pickup_weapon[20]			  // Blackfield, in the flowerbed underneath the walkway
CREATE_PICKUP_WITH_AMMO SAWNOFF PICKUP_ON_STREET_SLOW 25 0924.0 2138.0 11.0	 			pickup_weapon[21]		  // Whitewood Estates, around back of a warehouse
CREATE_PICKUP_WITH_AMMO heatseek PICKUP_ON_STREET_SLOW 10 1155.0 2341.0 17.0	 		pickup_weapon[22]			 // Pilson Intersection, corner of the top level of the car park
CREATE_PICKUP_WITH_AMMO rocketla PICKUP_ON_STREET_SLOW 10 1646.0	1349.0 11.0	 		pickup_weapon[23]			 // Las Venturas Airport, hidden behind the packing crates
CREATE_PICKUP_WITH_AMMO MOLOTOV PICKUP_ON_STREET_SLOW 10 1781.0 2072.0 11.0	 			pickup_weapon[24]		 // Harry Gold Parkway, hidden in bushes beside police station
CREATE_PICKUP_WITH_AMMO MOLOTOV PICKUP_ON_STREET_SLOW 10 2478.0 1182.0 22.0	 			pickup_weapon[25]		  // Come-A-Lot, In the corner of the building (accessible by jumping from one roof to another)
CREATE_PICKUP POOLCUE PICKUP_ON_STREET_SLOW 2854.0 0944.0 11.0							pickup_weapon[26]				  // Linden Side, behind the skip
CREATE_PICKUP nitestick PICKUP_ON_STREET_SLOW 2241.0 2425.0	11.0						pickup_weapon[27]			  // Roca Escalante, Hidden behind police station sign 
CREATE_PICKUP GOLFCLUB PICKUP_ON_STREET_SLOW 1418.0	2774.0 15.0	 						pickup_weapon[28]		 // Yellow Bell Golf Course, on the balcony overlooking the golf course
CREATE_PICKUP SHOVEL PICKUP_ON_STREET_SLOW 1393.0 2174.0 10.0	 						pickup_weapon[29]		// Redsands West, in the dugout of the baseball stadium
CREATE_PICKUP chnsaw PICKUP_ON_STREET_SLOW 1061.0 2074.0 11.0	 						pickup_weapon[30]		 // Whitewood Estates, in the industrial warehousey area (will be nice to use on the chip machines in casino3)
CREATE_PICKUP gun_para PICKUP_ON_STREET_SLOW 2057.0 2434.0	166.0						pickup_weapon[31]				   // The Emerald Isle, on top of the tallest scraper in venturas
CREATE_PICKUP KATANA PICKUP_ON_STREET_SLOW 2000.0 1526.0 15.0	   						pickup_weapon[32]			// The Pirates in Men's Pants, just near the back of the boat
CREATE_PICKUP SHOVEL PICKUP_ON_STREET_SLOW 1997.0 1658.0 12.0	   						pickup_weapon[33]			// The Pirates in Men's Pants, hidden in the rocks and bushes, for digging for treasure
																				   					 
																									 
//////////////////////////////////////////////////////////////										 
// END OF LV WEAPON PICKUPS																			 
//////////////////////////////////////////////////////////////										 
																									 
																									 
//////////////////////////////////////////////////////////////										 
// LA WEAPON PICKUPS																				 
//////////////////////////////////////////////////////////////										 

CREATE_PICKUP_WITH_AMMO SPRAYCAN PICKUP_ON_STREET_SLOW 500 2510.0 -1723.0 19.0 	  	pickup_weapon[34]		//	- Spray Can //roof beside Ryder's place
CREATE_PICKUP_WITH_AMMO COLT45 PICKUP_ON_STREET_SLOW 35 2538.0 -1630.0 14.0 	  	pickup_weapon[35]					//	- Semi Automatic Pistol //Behind Sweet's place
CREATE_PICKUP_WITH_AMMO MICRO_UZI PICKUP_ON_STREET_SLOW 60 2551.33 -1740.0 6.49		pickup_weapon[36]				  //	- Mac10 //under bridge in flood control
CREATE_PICKUP_WITH_AMMO SAWNOFF PICKUP_ON_STREET_SLOW 25 2428.0 -1214.0 36.0 	  	pickup_weapon[37]		//	- Sawn Off Shotgun //on roof of the Pig Pen
CREATE_PICKUP_WITH_AMMO DESERT_EAGLE PICKUP_ON_STREET_SLOW 30 2766.0 -2182.0 11.0 	pickup_weapon[38]			  //	- Desert Eagle //Playa del Seville beside life's A beach location
CREATE_PICKUP_WITH_AMMO  GRENADE PICKUP_ON_STREET_SLOW 20 2142.0 -1804.0 16.0 	  	pickup_weapon[39]		//	-  GRENADE 20s //2nd floor raised walkway 24 hour motel
CREATE_PICKUP_WITH_AMMO mp5lng PICKUP_ON_STREET_SLOW 70 1764.0 -1930.0 14.0 	 	pickup_weapon[40]	   	 //	- MP5 //Unity Station beside a hedge   	
CREATE_PICKUP_WITH_AMMO silenced PICKUP_ON_STREET_SLOW 30 1214.0 -1816.0 17.0 	  	pickup_weapon[41]		//	- silenced 30 Pistol //Top of conference centre steps
CREATE_PICKUP_WITH_AMMO rocketla PICKUP_ON_STREET_SLOW 10 1740.0 -1231.0 92.0 	  	pickup_weapon[42]		//	- Rocket Luncher //top of Downtown building accessible with copters
CREATE_PICKUP_WITH_AMMO mp5lng PICKUP_ON_STREET_SLOW 70 2266.0 -1028.0 59.0 	  	pickup_weapon[43]					//	- MP5 //top of a set of steps in las colina
CREATE_PICKUP_WITH_AMMO SPRAYCAN PICKUP_ON_STREET_SLOW 500 2463.0 -1061.0 60.0 	  	pickup_weapon[44]		//	- Spray can //In yard of Las Colinas shack
CREATE_PICKUP_WITH_AMMO sniper PICKUP_ON_STREET_SLOW 20 2047.0 -1406.0 68.0 	  	pickup_weapon[45]					//	- sniper 20 rifle //roof of Jefferson county general
CREATE_PICKUP_WITH_AMMO TEARGAS PICKUP_ON_STREET_SLOW 10 2213.0 -2283.0 15.0 		pickup_weapon[46]					  //	- Screwdriver //Ocean Docks freight train terminal
CREATE_PICKUP_WITH_AMMO TEARGAS PICKUP_ON_STREET_SLOW 10 1463.0 -1013.0 27.0 		pickup_weapon[47]					  //	- smoke  GRENADE 20s //in front of downtown LS oper house
CREATE_PICKUP_WITH_AMMO AK47 PICKUP_ON_STREET_SLOW 60 1308.97 -874.40 40.0 	  		pickup_weapon[48]				//	- AK47 60 //behind robois food market in Mulholland
CREATE_PICKUP_WITH_AMMO cuntgun PICKUP_ON_STREET_SLOW 30 1102.0 -661.0 114.0 	  	pickup_weapon[49]		//	- countryside rifle //behind a redcounty or mulholland posh house
CREATE_PICKUP_WITH_AMMO tec9 PICKUP_ON_STREET_SLOW 50 899.8012 -792.0780 102.0		pickup_weapon[50]		 //	- tec9 50 //behind another posh Mulholland house
CREATE_PICKUP_WITH_AMMO COLT45 PICKUP_ON_STREET_SLOW 35 338.0 -1875.0 4.0 	 		pickup_weapon[51]		 //	- semi automatic pistol //on a santa maria beach lifeguard post
CREATE_PICKUP_WITH_AMMO  GRENADE PICKUP_ON_STREET_SLOW 20 397.0 -1924.0 8.0 	 	pickup_weapon[52]			 //	- Hand  GRENADE 20s //behind a wooden building on the Santa Maria pier
CREATE_PICKUP_WITH_AMMO MOLOTOV PICKUP_ON_STREET_SLOW 10 886.0 -966.0 37.0 	 		pickup_weapon[53]		 //	- molotovs //vinewood beside the giant bottle 
CREATE_PICKUP_WITH_AMMO tec9 PICKUP_ON_STREET_SLOW 50 1408.0 -2380.0 14.0 	 		pickup_weapon[54]		 //	- tec9 50 //under LS international bridge
CREATE_PICKUP_WITH_AMMO M4 PICKUP_ON_STREET_SLOW 70 1379.0 -2547.0 14.0 			pickup_weapon[55] 						 //	- M4 70 //between airport ramps
CREATE_PICKUP_WITH_AMMO MOLOTOV PICKUP_ON_STREET_SLOW 10 2426.0 -1416.0 24.0 	pickup_weapon[68]	//	- Molotovs //behind recylcling centre in East Los Santos
CREATE_PICKUP nitestick PICKUP_ON_STREET_SLOW 259.0 80.0 1004.0 	pickup_weapon[56]	//	- nightstick //LS police locker room
CREATE_PICKUP Gun_dildo1 PICKUP_ON_STREET_SLOW 261.0 71.0 1003.0	pickup_weapon[57]	//	- Big purple dildo//LS police shower room
CREATE_PICKUP GOLFCLUB PICKUP_ON_STREET_SLOW 1457.0 -792.0 90.0 	pickup_weapon[58]				  //	- golfclub //behind the arial behind the Vinewood sign
CREATE_PICKUP chnsaw PICKUP_ON_STREET_SLOW 2371.0 -2543.0 3.0 	  	pickup_weapon[59]			//	- chainsaw //Ocean docks ot the bottom of a stone pier
CREATE_PICKUP knifecur PICKUP_ON_STREET_SLOW 1124.0 -1335.0 13.0 	pickup_weapon[60]				  //	- knife //Market behind some stores
CREATE_PICKUP_WITH_AMMO MOLOTOV PICKUP_ON_STREET_SLOW 10 2197.0 -2475.0 14.0 pickup_weapon[61]	//	- Hammer //Ocean docks, under industrial looking building
CREATE_PICKUP gun_para PICKUP_ON_STREET_SLOW 1528.2217 -1357.9854 330.0371 	pickup_weapon[62]	//	- gun_para //top of tallest building in Los Santos
CREATE_PICKUP katana PICKUP_ON_STREET_SLOW 1862.0 -1862.0 14.0 	 	pickup_weapon[63]			 //	- Katana //Waste ground in El Corona
CREATE_PICKUP brassknuckle PICKUP_ON_STREET_SLOW 1339.0 -1765.0 14.0 pickup_weapon[64]		  //	- Brass Knuckles //Commerce, end of alleyway
CREATE_PICKUP chnsaw PICKUP_ON_STREET_SLOW 2192.2429 -1988.7507 13.4185 pickup_weapon[65]	// junkyard la //	- Dildo //on pole dancing stage inside the Pig Pen
CREATE_PICKUP SHOVEL PICKUP_ON_STREET_SLOW 2459.0 -1708.0 13.6 	  	pickup_weapon[66]			//	- Shovel //Ryder's backyard
CREATE_PICKUP_WITH_AMMO  GRENADE PICKUP_ON_STREET_SLOW 20 2441.0 -1013.0 54.0 pickup_weapon[67]	//	- Hammer//in yard of Las Colinas shack


//////////////////////////
//SF
////////////////////////


CREATE_PICKUP_WITH_AMMO mp5lng PICKUP_ON_STREET_SLOW 70 -2678.0 -128.0 4.0 	 		pickup_weapon[69]			  //	- MP5 //back yard of an Ocean flats house
CREATE_PICKUP_WITH_AMMO DESERT_EAGLE PICKUP_ON_STREET_SLOW 30 -2212.0 109.0 35.0  	pickup_weapon[70]		  		 //	- Desert Eagle //Garcia back lot
CREATE_PICKUP_WITH_AMMO M4 PICKUP_ON_STREET_SLOW 70 -2903.0 784.0 35.0  	 		pickup_weapon[71]						  //	- M4 70 //Back yard of a Pallisades home
CREATE_PICKUP_WITH_AMMO tec9  PICKUP_ON_STREET_SLOW 50 -2665.0 1452.0 7.0 			pickup_weapon[72]											  ///- tec9 50 //Battery point beside Jizzy's
CREATE_PICKUP_WITH_AMMO Satchel PICKUP_ON_STREET_SLOW 15 -2754.0 -400.0 7.0 	 	pickup_weapon[73]								  //	- Satchel 15 bombs //behind Avispa country club
CREATE_PICKUP_WITH_AMMO COLT45 PICKUP_ON_STREET_SLOW 35 -2206.0 -23.0 35.0  	 	pickup_weapon[74]						 //	- semi auto handgun //Garcia alleyway
CREATE_PICKUP_WITH_AMMO chromegun PICKUP_ON_STREET_SLOW 30 -1841.1058 -74.2171 14.7606 pickup_weapon[75]					 	 //	- pump shotgun //Doherty behind Solarin industries building
CREATE_PICKUP_WITH_AMMO FLAME PICKUP_ON_STREET_SLOW 2000 -1579.0 29.45 17.0  	 		pickup_weapon[76] 					  //	- FLAME 50thrower //Easter basin on the freight liner
CREATE_PICKUP_WITH_AMMO cuntgun PICKUP_ON_STREET_SLOW 30 -2094.0 -488.0 36.0  		pickup_weapon[77]					 //	- countryside rifle //outside Foster Valler arena
CREATE_PICKUP_WITH_AMMO AK47 PICKUP_ON_STREET_SLOW 60 -1968.0 -923.0 32.0 	 		pickup_weapon[78]					//	- AK47 60 //behind rocks at Foster Valley complex
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -1945.0 -1088.0 31.0  		pickup_weapon[79]					 //	- camera 50 //outside the loading bays in the foster valley complex
CREATE_PICKUP_WITH_AMMO fire_ex PICKUP_ON_STREET_SLOW 3000 -1700.0 415.0 7.0 	 	pickup_weapon[80]						  //	- Fire Extinguisher //outside the Easter Basin Xoomer gas station
CREATE_PICKUP_WITH_AMMO TEARGAS PICKUP_ON_STREET_SLOW 10 -1386.0 509.0 4.0 	 		pickup_weapon[81]					  //	- smoke  GRENADE 20s //cargo hold of aircraft carrier
CREATE_PICKUP_WITH_AMMO MICRO_UZI PICKUP_ON_STREET_SLOW  60 -1679.0 1410.0 7.0 		pickup_weapon[82]						   //	- Mac 10 //Esplanade beside where follow the ped used to be
CREATE_PICKUP_WITH_AMMO FLAME PICKUP_ON_STREET_SLOW 2000 -2132.5195 189.2507 35.5379 	pickup_weapon[83]	  				//	- FLAME //Doherty construction yard in a concrete pipe
CREATE_PICKUP_WITH_AMMO heatseek PICKUP_ON_STREET_SLOW 10 -1126.69 -150.82 14.61		pickup_weapon[84]				    //	- RPG //SF airport behind gas cannisters on runway area
CREATE_PICKUP_WITH_AMMO minigun PICKUP_ON_STREET_SLOW 200 -1496.0 591.0 42.0  		pickup_weapon[85]					  //	- minigun 200 //on top of a SF side Kincaid Bridge support
CREATE_PICKUP_WITH_AMMO Satchel PICKUP_ON_STREET_SLOW 15 -2542.2620 922.2401 67.1221  		pickup_weapon[86]						   //	- Package bombs //Someones doorstep in Paradiso
CREATE_PICKUP_WITH_AMMO tec9 PICKUP_ON_STREET_SLOW 50 -2092.0 1121.0 54.0 			pickup_weapon[87]					  //	 - tec9 50 //Calton Heights grassy area
CREATE_PICKUP_WITH_AMMO sniper PICKUP_ON_STREET_SLOW 20 -1629.0 1167.0 24.0  		pickup_weapon[88]						//- sniper 20 rifle //downtown on roof behind drink beer sign		
CREATE_PICKUP chnsaw PICKUP_ON_STREET_SLOW -2083.0 298.0 42.0  	 	 pickup_weapon[89]			 //	- Hammer //Unfinished roadway in King's
CREATE_PICKUP bat PICKUP_ON_STREET_SLOW -2306.0 93.0 35.0  	 		 pickup_weapon[90]			 //	- Baseball Bat //Garcia baseball diamond
CREATE_PICKUP shovel PICKUP_ON_STREET_SLOW -2796.4155 123.686 6.844  pickup_weapon[91]			//	- Shovel //pallisades back yard
CREATE_PICKUP POOLCUE PICKUP_ON_STREET_SLOW -2135.0 197.0 35.0  	 pickup_weapon[92]			  //	- Poolcue // hidden behind a fallen piece of concrete in the Doherty construction yard
CREATE_PICKUP KATANA PICKUP_ON_STREET_SLOW -2208.0 696.0 50.0  	 	 pickup_weapon[93]			  //	- Katana //chinatown backalley
CREATE_PICKUP brassknuckle PICKUP_ON_STREET_SLOW -2206.0 961.0 80.0  pickup_weapon[94]			//	- Brass Knuckles //beside a Calton Heights car park
CREATE_PICKUP nitestick PICKUP_ON_STREET_SLOW -2222.0 -302.0 43.0	 pickup_weapon[95]			  	//- cane //Doherty overpass
CREATE_PICKUP knifecur PICKUP_ON_STREET_SLOW -1871.0 351.0 26.0  	 pickup_weapon[96]				  //	- knife //Downtown underpass
CREATE_PICKUP golfclub PICKUP_ON_STREET_SLOW -2715.0 -314.0 7.0 	 pickup_weapon[97]					//- golf club //front entrance Avispa country club
CREATE_PICKUP chnsaw PICKUP_ON_STREET_SLOW -2359.0 -82.0 35.0  	 	 pickup_weapon[98]			 //	- Chainsaw //corner of Garcia consrucion plot
																				 				 	
																																			 
																				 
///////////////////////////////////////////////////								 
// 	END OF SF WEAPON PICKUPS													 
///////////////////////////////////////////////////								 
																				 
																				 

///////////////////////////////////////////////////
// 	COUNTRY WEAPON PICKUPS
///////////////////////////////////////////////////
CREATE_PICKUP_WITH_AMMO DESERT_EAGLE PICKUP_ON_STREET_SLOW 30 -1870.0 -1625.0 22.0 pickup_weapon[99]				// - desert eagle //Whetstone scrap yard warehouse
CREATE_PICKUP_WITH_AMMO fire_ex PICKUP_ON_STREET_SLOW 3000 -1627.0	-2692.0	49.0   pickup_weapon[100]							// - fire extinguisher //outside whetstone xoomer station
CREATE_PICKUP_WITH_AMMO MICRO_UZI PICKUP_ON_STREET_SLOW 60 -2038.0 -2562.0 31.0	   pickup_weapon[101]					 //- mac10 //trailer park behind trailer
CREATE_PICKUP_WITH_AMMO cuntgun PICKUP_ON_STREET_SLOW 30 -1035.0	-2258.0 70.0   pickup_weapon[102]							 //- countryside rifle //on top of mound in the middle of nowhere
CREATE_PICKUP_WITH_AMMO SAWNOFF PICKUP_ON_STREET_SLOW 25 2366.0 23.0 28.0		   pickup_weapon[103]						 //- Sawn Off shotgun //behind a house, on the back porch
CREATE_PICKUP_WITH_AMMO MOLOTOV PICKUP_ON_STREET_SLOW 10 2255.0 -74.0 32.0 		   pickup_weapon[104]					//-	 MOLOTOV 10s //on the roof of the palomino creek library
CREATE_PICKUP_WITH_AMMO mp5lng PICKUP_ON_STREET_SLOW 70 1296.0 392.0	20.0	   pickup_weapon[105]						// - MP5 //inside the yard of the partially burned building
CREATE_PICKUP_WITH_AMMO tec9 PICKUP_ON_STREET_SLOW 50 262.0 38.0 2.0			   pickup_weapon[106]					// - tec9 50 //beside warehouse opposite the spandex depot
CREATE_PICKUP_WITH_AMMO AK47 PICKUP_ON_STREET_SLOW 60 -121.0 -232.0 1.0			   pickup_weapon[107]					// - AK47 60 //outside the fleisch beer factory
CREATE_PICKUP SHOVEL PICKUP_ON_STREET_SLOW			 -532.0 -106.0 63.0			   pickup_weapon[108]					// - sawn off shotgun //in the midst of the logging site
CREATE_PICKUP_WITH_AMMO M4 PICKUP_ON_STREET_SLOW 70 113.0 1811.0 18.0			   pickup_weapon[109]					// - M4 70 //at the bottom of a guard tower
CREATE_PICKUP_WITH_AMMO DESERT_EAGLE PICKUP_ON_STREET_SLOW 30 36.0 1372.0 9.0	   pickup_weapon[110]		// - Desert Eagle //behind the middle solar panel in the trailer park
CREATE_PICKUP_WITH_AMMO chromegun PICKUP_ON_STREET_SLOW 30 24.0 969.0 20.0		   pickup_weapon[111]		// - Pump Shotgun //beside a low budget house in the outskirts
CREATE_PICKUP_WITH_AMMO MOLOTOV PICKUP_ON_STREET_SLOW 10 -170.0 1025.0 20.0		   pickup_weapon[112]		// - MOLOTOV 10s //behind the liquor store
CREATE_PICKUP_WITH_AMMO COLT45 PICKUP_ON_STREET_SLOW 35 -639.0 1181.0 13.0		   pickup_weapon[113]		// - semi automatic pistol //under bridge close to save point
CREATE_PICKUP_WITH_AMMO AK47 PICKUP_ON_STREET_SLOW 60 -585.0 2714.0 72.0		   pickup_weapon[114]				// - AK47 60 //beside Caravan in small traile
CREATE_PICKUP_WITH_AMMO fire_ex PICKUP_ON_STREET_SLOW 3000 -742.0 2752.0 47.0	   pickup_weapon[115]					// - Fire Extinguisher //beside the pumpless gas station
CREATE_PICKUP_WITH_AMMO chromegun PICKUP_ON_STREET_SLOW 30 -932.02 2649.92	42.0   pickup_weapon[116]					// - Shotgun //beside the water at the USJ
CREATE_PICKUP_WITH_AMMO heatseek PICKUP_ON_STREET_SLOW 10 -1317.0 2509.0 87.0	   pickup_weapon[117]				// - RPG //behind one of the ruins
CREATE_PICKUP_WITH_AMMO mp5lng PICKUP_ON_STREET_SLOW 70 -1474.0 2577.0 56.0		   pickup_weapon[118]				// - MP5 //behind a building NE of the medical centre
CREATE_PICKUP_WITH_AMMO DESERT_EAGLE PICKUP_ON_STREET_SLOW 30 -2352.0 2456.0 6.0   pickup_weapon[119]					// - Desert Eagle //in walled garden at the beginning of the marina, northernmost point of town
CREATE_PICKUP_WITH_AMMO  GRENADE PICKUP_ON_STREET_SLOW 20 -2520.0 2293.0 5.0	   pickup_weapon[120]					// -  GRENADE 20s //alleyway behind shops
CREATE_PICKUP_WITH_AMMO FLAME PICKUP_ON_STREET_SLOW 2000 -1358.0 -2115.0 30.0 	   pickup_weapon[121]					//- FLAME 50thrower //in a copse of trees
CREATE_PICKUP_WITH_AMMO MICRO_UZI PICKUP_ON_STREET_SLOW 60 119.0 2409.0 17.0	   pickup_weapon[122]					// - mac10 //inside aeroplane fuselage distant from main airfield buildings 						 
CREATE_PICKUP SHOVEL PICKUP_ON_STREET_SLOW -1809.0 -1662.0 24.0		pickup_weapon[123]				// - Shovel //Whetstone scrap yard beside a mound of scrap
CREATE_PICKUP gun_para PICKUP_ON_STREET_SLOW -2350.0 -1586.0 485.0	pickup_weapon[124]						// - gun_para //top of chiliad
CREATE_PICKUP GOLFCLUB PICKUP_ON_STREET_SLOW -2227.0 -2401.0 31.40	pickup_weapon[125]				// - golf club //angel pine residential garage
CREATE_PICKUP SHOVEL PICKUP_ON_STREET_SLOW 2240.0 -83.0	27.0 		pickup_weapon[126]			//- shovel //in the palomino creek cemetary
CREATE_PICKUP POOLCUE PICKUP_ON_STREET_SLOW 294.0 -188.0 2.0		pickup_weapon[127]				// - pool cue //outside the bar
CREATE_PICKUP chnsaw PICKUP_ON_STREET_SLOW -761.0 -126.0 66.0		pickup_weapon[128]				// - Chainsaw //beside a logging hut
CREATE_PICKUP katana PICKUP_ON_STREET_SLOW -1568.0 2718.0 56.0		pickup_weapon[129]					// - Katana //Behind a building beside the northern road
CREATE_PICKUP Gun_dildo2 PICKUP_ON_STREET_SLOW -2401.0 2360.0 5.0	pickup_weapon[130]					// - screwdriver //behind the skip
CREATE_PICKUP gun_para PICKUP_ON_STREET_SLOW -2679.0 1933.0 217.0	pickup_weapon[131]					// - gun_para //on top of the highest support
CREATE_PICKUP SHOVEL PICKUP_ON_STREET_SLOW 637.0 832.0 -43.0		pickup_weapon[132]			// - Spade // in front of the packing crate/mud
CREATE_PICKUP chnsaw PICKUP_ON_STREET_SLOW 680.0 826.0 -39.0		pickup_weapon[133]			// - Chainsaw // behind the rock crusher machine (walk up stairway to get behind big clylinder)
CREATE_PICKUP chnsaw PICKUP_ON_STREET_SLOW 752.0 260.0 27.0 		pickup_weapon[134]				//- Chainsaw //in front of a hut
CREATE_PICKUP brassknuckle PICKUP_ON_STREET_SLOW -246.0 2725.0 63.0	pickup_weapon[135]			// - Brass Knuckles //behind small building close to two USJs
CREATE_PICKUP knifecur PICKUP_ON_STREET_SLOW -23.0 2322.0 24.0 		pickup_weapon[136]			//- knife //between snake pens at the snake farm
CREATE_PICKUP_WITH_AMMO Satchel PICKUP_ON_STREET_SLOW 15 1284.8936 278.5705 19.5547 pickup_weapon[137]	// behind otb																			 	   
CREATE_PICKUP_WITH_AMMO AK47 PICKUP_ON_STREET_SLOW 60 2129.4 -2280.71 14.42 pickup_weapon[139] //Warehouse for crash3 grey imports 
CREATE_PICKUP gun_para PICKUP_ON_STREET_SLOW -1542.8567 698.4825 139.2658	pickup_weapon[140]				   // on top of bridge in san fierro
CREATE_PICKUP_WITH_AMMO mp5lng PICKUP_ON_STREET_SLOW 130 2198.11 -1170.22 33.5 pickup_weapon[141]	 // on top of motel deal motel
CREATE_PICKUP gun_para PICKUP_ON_STREET_SLOW  -225.6758 1394.2562 172.0143 	pickup_weapon[142]	  //On the aerial 
CREATE_PICKUP gun_para PICKUP_ON_STREET_SLOW  -773.0379 2423.4993 157.0856	pickup_weapon[143]	  // In the desert… 			   
CREATE_PICKUP_WITH_AMMO sniper PICKUP_ON_STREET_SLOW 20 935.7440 -926.0453 57.7642 pickup_weapon[144]	// chateux marmount in LA

//////////////////////////////
// Weapons in police stations
/////////////////////////////
CREATE_PICKUP SHOVEL PICKUP_ON_STREET_SLOW 842.9783 -17.3791 64.2 pickup_weapon[145]		// shovel at Catalina`s graveyard.   
// police 1
CREATE_PICKUP_WITH_AMMO COLT45 PICKUP_ON_STREET_SLOW 35 255.0493 84.0615 1002.4531	pickup_weapon[146] //	- semi automatic pistol //police station 1
CREATE_PICKUP_WITH_AMMO chromegun PICKUP_ON_STREET_SLOW 30 217.8 76.4 1005.0466 		pickup_weapon[147] // shotgun Police 1	
//police 2

CREATE_PICKUP nitestick PICKUP_ON_STREET_SLOW 				223.8347 120.4458 1010.2118	 	pickup_weapon[148]	
CREATE_PICKUP_WITH_AMMO COLT45 PICKUP_ON_STREET_SLOW 35 	263.2531 109.7859 1004.6245		pickup_weapon[149] //	- semi automatic pistol //police station 1
CREATE_PICKUP_WITH_AMMO chromegun PICKUP_ON_STREET_SLOW 30 	228.3176 114.4330 999.0215 		pickup_weapon[150] // shotgun Police 1	

// police 3
CREATE_PICKUP nitestick PICKUP_ON_STREET_SLOW 				247.4536 192.3085 1008.1719	 	pickup_weapon[151]	
CREATE_PICKUP_WITH_AMMO COLT45 PICKUP_ON_STREET_SLOW 35 	242.6130 196.3202 1008.1719		pickup_weapon[152] //	- semi automatic pistol //police station 1
CREATE_PICKUP_WITH_AMMO chromegun PICKUP_ON_STREET_SLOW 30 	240.7765 196.1124 1008.1719		pickup_weapon[153] // shotgun Police 1	
CREATE_PICKUP nitestick PICKUP_ON_STREET_SLOW 				188.9769 158.2180 1003.0312 	pickup_weapon[154]

CREATE_PICKUP gun_para PICKUP_ON_STREET_SLOW  -1753.4181 885.3446 295.5166	pickup_weapon[155]	  // The Pointy Building in SF 


// CAMERAS AROUND SF
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -2539.9180 -598.6152 132.7640  	 pickup_weapon[156]		  // on top of triathlon hill
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -2329.9844 -165.3635 35.2389 	 pickup_weapon[157]	     // outside burger shot	
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -2721.2410 -318.8085 7.5246 	 pickup_weapon[158]	   // entrance of swank SHINING-like hotel	
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -2677.1023 234.9912 4.1048 		 pickup_weapon[159]	   // behind clucken bell					
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -2706.6921 375.8728 5.0525		 pickup_weapon[160]	   // in the middle of square (where city hall and the art gallery are) 	
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -2550.1064 657.2860 14.7319 	 pickup_weapon[161]	   // hospital car park	
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -2791.2478 771.5468 51.0904 	 pickup_weapon[162]	   // behind TUFF NUTS	
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -1770.8149 903.2556 25.3894    	 pickup_weapon[163]	    // next to the POINTY BUILDING (behind BIG PRICKS SIGN)	
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -1713.0062 1368.2390 7.2664 	 pickup_weapon[164]	   // behind pizza stack at pier 69 	
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -1851.3165 1302.2909 60.7553 	 pickup_weapon[165]	   // on top of multi story car park	
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -1635.0255 604.4713 40.6377 	 pickup_weapon[166]	   // on the road bridge	
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -1976.4830 670.5043 46.6039 	 pickup_weapon[167]	   // inside zombie tech building	
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -2038.4086 1111.4065 53.7928 	 pickup_weapon[168]	   //the side of church
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -2048.8032 899.5274 53.8866    	 pickup_weapon[169]	   // up windy street	
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -2292.4702 722.5441 49.4265	   	 pickup_weapon[170]	    //across teh road from gates of china town 	
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -1977.9155 113.8457 27.1096      pickup_weapon[171]	   // train station	
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -1528.1443 160.0232 3.5142 	     pickup_weapon[172]	   // down by the docks	
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 -1771.2611 -597.5884 16.6287     pickup_weapon[173]	   // entrance of the airports	
CREATE_PICKUP_WITH_AMMO camera PICKUP_ON_STREET_SLOW 50 2495.8069 -1700.6371 1017.8368   special_camera //CJ's house	
																  


// Extra weapons added on sept 6th
CREATE_PICKUP gun_cane PICKUP_ON_STREET_SLOW  	-2677.7261 -192.3469 6.8518		pickup_weapon[174]    // outside some guys doorstep SF	
CREATE_PICKUP chnsaw PICKUP_ON_STREET_SLOW  	-2752.2429 -272.2891 6.5956 	pickup_weapon[175]      // TEnnis courts in SF
CREATE_PICKUP gun_cane PICKUP_ON_STREET_SLOW  	-2617.4731 -97.0801 4.0030		pickup_weapon[176]      // outside some guys doorstep SF
CREATE_PICKUP gun_cane PICKUP_ON_STREET_SLOW 	-2777.1921 -25.2984 6.8721 		pickup_weapon[177] 	// a guys doorstep SF
CREATE_PICKUP gun_cane PICKUP_ON_STREET_SLOW 	-2774.1130 87.8845 6.7987 		pickup_weapon[178] // a guys doorstep SF
CREATE_PICKUP gun_cane PICKUP_ON_STREET_SLOW  	-2770.6235 389.0772 4.2818 		pickup_weapon[179]	// OUTSIDE CITY HALLsf
CREATE_PICKUP katana PICKUP_ON_STREET_SLOW 		-2535.6311 51.7034 8.6512 		pickup_weapon[180]	// UNDERGROUND WALK NEAR STALLS sf 
CREATE_PICKUP gun_cane PICKUP_ON_STREET_SLOW  	-2530.9580 -34.1009 25.2855 	pickup_weapon[181]	// STALLS NEAR HIPPY AREA SF
CREATE_PICKUP gun_cane PICKUP_ON_STREET_SLOW  	-1691.6486 946.7679 24.8084 	pickup_weapon[182]	  // OUTSIDE VICTIM	  SF
CREATE_PICKUP gun_cane PICKUP_ON_STREET_SLOW  	-2664.5183 636.5673 14.2474 	pickup_weapon[183]	 //OUTSIDE HOSPITAL	SF

CREATE_PICKUP_WITH_AMMO FLAME PICKUP_ON_STREET_SLOW 2000 -601.4012 -1068.6002 23.6667  pickup_weapon[184]	// OUTSIDE CABIN MOTEL COUNTRY WEST
CREATE_PICKUP gun_cane PICKUP_ON_STREET_SLOW  	-377.2184 -1048.0535 58.9125 	pickup_weapon[185]	  // OUTSIDE FARM IN COUNTRY		WESSR
CREATE_PICKUP gun_cane PICKUP_ON_STREET_SLOW  	-45.5928 -1148.5286 1.3953 	pickup_weapon[186]	   // OUTSIDE BARN/WAREHOUSE COUNTRYWEST


CREATE_PICKUP brassknuckle PICKUP_ON_STREET_SLOW 2428.4990 -1679.2703 13.1633 pickup_weapon[187]		 // under freeway	la
CREATE_PICKUP_WITH_AMMO  GRENADE PICKUP_ON_STREET_SLOW 20 	2820.0129 -1426.5194 23.805	 pickup_weapon[188]	 // multi story car park la
CREATE_PICKUP_WITH_AMMO MICRO_UZI PICKUP_ON_STREET_SLOW 60 2790.3428 -1427.4893 39.6258	 pickup_weapon[189] // top of multi story carpark la
CREATE_PICKUP_WITH_AMMO tec9 PICKUP_ON_STREET_SLOW 50 2574.0649 -1134.2010 64.6535		 pickup_weapon[190]  // hispanic area
CREATE_PICKUP_WITH_AMMO COLT45 PICKUP_ON_STREET_SLOW 35 2423.8916 -1117.4524 41.2464  	 	pickup_weapon[191]    // hispanic area
CREATE_PICKUP gun_cane PICKUP_ON_STREET_SLOW  	1296.1552 -1081.8922 26.1502 	pickup_weapon[192]	  //  hospital in la
CREATE_PICKUP gun_cane PICKUP_ON_STREET_SLOW  	1390.6113 -800.4332 81.7795 	pickup_weapon[193]	 // vinewood sign LA
CREATE_PICKUP bat PICKUP_ON_STREET_SLOW 		1308.4662 2111.2886 10.7221		pickup_weapon[194]	 // baseball stadium VEGAS
CREATE_PICKUP gun_cane PICKUP_ON_STREET_SLOW  	2183.1160 2396.8269 10.7722 	pickup_weapon[195]	  //somewhere in vagas
CREATE_PICKUP bat PICKUP_ON_STREET_SLOW 		1081.1333 1603.6969 5.6000  	pickup_weapon[196]	  // under stadium in VEGAS
CREATE_PICKUP knifecur PICKUP_ON_STREET_SLOW 	777.8668 1948.1228 5.3634 		pickup_weapon[197]  // trailor park outside vegas
CREATE_PICKUP_WITH_AMMO SAWNOFF PICKUP_ON_STREET_SLOW 25 1706.3517 1242.0194 34.2952	pickup_weapon[199]	 // multi story car park in vegas

CREATE_PICKUP_WITH_AMMO minigun PICKUP_ON_STREET_SLOW 200 2492.0513 2398.3774 4.5293		pickup_weapon[200]  // underground car park in vegas (where one of rothwells missions are set)
CREATE_PICKUP_WITH_AMMO rocketla PICKUP_ON_STREET_SLOW 10 2055.3555 2435.3564 40.3684  		pickup_weapon[201]	 // another multi story car park in vegas
CREATE_PICKUP SHOVEL PICKUP_ON_STREET_SLOW 1888.2698 2877.2617 10.1621 			pickup_weapon[202]  // tennis courts in vegas
CREATE_PICKUP gun_cane PICKUP_ON_STREET_SLOW  	1420.9449 2519.8816 10.6199  	pickup_weapon[203]	   // behind somebodys house at the edge of VEGAS
CREATE_PICKUP gun_cane PICKUP_ON_STREET_SLOW  	1372.9963 2605.7576 10.8776  	pickup_weapon[204]	    // railway platform in vegas
CREATE_PICKUP_WITH_AMMO mp5lng PICKUP_ON_STREET_SLOW 70 2293.6855 1982.2860 31.4335 pickup_weapon[205]  // on top of another multi storie carpark
CREATE_PICKUP KATANA PICKUP_ON_STREET_SLOW  	2631.2629 1722.3947 11.0312 	pickup_weapon[206]	   // chinese mall in vegas
CREATE_PICKUP gun_cane PICKUP_ON_STREET_SLOW  	2490.4966 1522.4697 10.5760 	pickup_weapon[207]	   // next to the entrance of the TIKI casino
CREATE_PICKUP gun_cane PICKUP_ON_STREET_SLOW  	455.4583 -1485.8964 30.9717 	pickup_weapon[208]	   // outside victim and dider sachs in LA


CREATE_PICKUP_WITH_AMMO minigun PICKUP_ON_STREET_SLOW 200 244.98 1859.185 14.08			pickup_weapon[209]	// minigun in area 51

// send these to immy
//CREATE_PICKUP_WITH_AMMO shotgspa PICKUP_ON_STREET_SLOW 30 297.8289 1846.6226 6.7266 	pickup_weapon[210] // spaz in area 51
//CREATE_PICKUP_WITH_AMMO JETPACK PICKUP_ONCE 0 268.7 1884.1 -30.085 pickup_weapon[211]

CREATE_PICKUP_WITH_AMMO tec9 PICKUP_ON_STREET_SLOW 50 2529.7241 -1678.5634 19.4225		pickup_weapon[212]
CREATE_PICKUP_WITH_AMMO SAWNOFF PICKUP_ON_STREET_SLOW 15 2254.3777 -2261.6895 14.3751 		pickup_weapon[213]
CREATE_PICKUP_WITH_AMMO sniper PICKUP_ON_STREET_SLOW 20 2015.7440 1004.0453 39.1 pickup_weapon[214]	// on top of 4 dragons casino in VEGAS
CREATE_PICKUP KATANA PICKUP_ON_STREET_SLOW 	2002.2629 981.3947 10.5 	pickup_weapon[215]	   // behind 4 dragons casino in VEGAS


///////////////////////////////////////////////////
// 	END OF COUNTRY WEAPON PICKUPS
///////////////////////////////////////////////////



//FLOWER PICK UPS:

//LOS SANTOS: 
VAR_INT pickup_flowers[40]
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 1928.68 -1774.21 13.54 	pickup_flowers[0]		//1. Gas Station - next to Sprunk machine.  flower1
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 1875.91 -1917.18 15.03 	pickup_flowers[1]		//2. El Corona - in between plants in house garden.  flower2
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 2019.60 -1214.15 21.47 	pickup_flowers[2]	//3. Glen Park - in between flowerbed. flower3
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 2209.77 -1001.69 63.71 	pickup_flowers[3]	//4. Las Colinas - Front Garden behind flowers. flower4
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 1000.34 -1858.58 12.3 	pickup_flowers[4]	//5. Verona Beach - next to palm tree.  flower5
//CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 289.53 -1391.96 13.96  	pickup_flowers[5]	//6. Rodeo - outside Florist's  flower6
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 911.11 -1120.31 24.03  	pickup_flowers[6]	//7. Vinewood - in front of headstone in Graveyard  flower7
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 929.00 -750.00 105.82	pickup_flowers[7]	//8. Mulholland - on tight bend (car crash hotspot)   flower8
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 1129.09 -2052.82 69.00 	pickup_flowers[8]	//9. Verdant Bluffs - behind flowerbed. flower9
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -92.74 -1425.46 12.75 	pickup_flowers[9]	//10. Flint Intersection - beside concrete wall  flower10
																				  
//COUNTRY: 
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW  -77.65 -1167.18 2.16 	pickup_flowers[10]//11. Flint County - In front of Gas Station door.flower 11
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 34.00 -2649.00 40.73 	pickup_flowers[11]//12. Flint County - Back of "69" flower12
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -739.00 -1262.00 68.12 	pickup_flowers[12]//13. Flint County - on hairpin bend  flower13
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -2177.00 -2423.00 30.63 pickup_flowers[13]//14. Angel Pine - behind trash dumpster flower14
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -615.00 -861.00 105.72 	pickup_flowers[14]//15. Flint County - in between large trees flower15
																				  
//SAN FIERO:   
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -2051.00 948.00 55.40  	pickup_flowers[15]//16. Calton Heights - in low hedges on slope  flower16
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -2658.00 -187.00 4.18	pickup_flowers[16]//17. Ocean Flats - next to dumpster flower17
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -2649.00 734.97 27.96 	pickup_flowers[17]//18. Santa Flora - in back garden flower18
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -1791.00 481.00 25.68 	pickup_flowers[18]//19. Easter Basin - next to collapsed road (quake memorial?) flower19
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -2797.00 1182.00 20.28 	pickup_flowers[19]//20. Pallisades - next to pond in grasses  flower20
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -2589.6233 -16.1650 3.9662 pickup_flowers[20] 	// GRAVEYARD SF 
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -2865.00 690.00 23.43	pickup_flowers[21]//22. Pallisades - in front garden flower22
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -2339.00 -453.00 80.24	pickup_flowers[22]//23. Missionary Hill - on hairpin bend   flower23
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -1955.00 -748.00 36.22	pickup_flowers[23]//24. Foster Valley - in plant bed  flower24
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -2420.03 987.59 45.30 	pickup_flowers[24]//25. Juniper Hollow - next to coke machine in Xoomer flower25

//DESERT: 
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -326.56 2215.37 43.57	pickup_flowers[25]//26. Las Brujas - in front of headstone flower26
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -1319.00 2705.00 50.27 	pickup_flowers[26]//27. Tierra Robada - next to Gas Station toilets flower27
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -2474.94 2443.52 16.03 	pickup_flowers[27]//28. Bayside - in front garden flower28
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -1670.64 2590.49 81.37	pickup_flowers[28]//29. Tierra Robada - back of bungalow  flower29
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW -892.98 1971.66 60.61 	pickup_flowers[29]//30. The Sherman Dam - in front of monument flower30

//LAS VENTURAS:	  
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 1576.86 2837.14 10.83 	pickup_flowers[30]//31. Prickle Pine - next to flowerbed flower31
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 1492.72 2773.76 10.81 	pickup_flowers[31]//32. Yellow Bell Golf Course - in front of main sign in flowerbed flower 32
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 2642.03 1125.74 11.03	pickup_flowers[32]//33. Las Venturas - next to Gas Station doors flower33
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 2025.24 661.60 10.93 	pickup_flowers[33]//34. Rockshore West - in front garden flower34
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 2181.82 1484.97 11.36 	pickup_flowers[34]//35. Royale Casino - in flowerbed flower35
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 2197.02 2476.33 11.00 	pickup_flowers[35]//36. The Emerald Isle - in between petrol pumps at Xoomer flower36
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 2212.00 2526.00 10.81 	pickup_flowers[36]////37. Julius Thruway North - Wedding Tackle front doorflower37
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 2715.79 1109.47 6.70 	pickup_flowers[37]//38. Julie Thruway North - Crash barrier flower38
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 2489.25 918.28 11.02 	pickup_flowers[38]//39. Las Venturas - outside wedding chapel lower39
CREATE_PICKUP flowera PICKUP_ON_STREET_SLOW 1472.08 1890.09 10.81	pickup_flowers[39]//40. Redsands West - in front garden between trees and shrubs  flower40
		
		


   				
///////////////////////////////////////////////////
// 	END OF CREATING WEAPON PICKUPS
///////////////////////////////////////////////////




/*
// HEALTH

VAR_INT pickup_health[51]

								   



CREATE_PICKUP health PICKUP_ON_STREET_SLOW 1433.0 2634.0 11.0    pickup_health[0]					// Yellow Bell Station, inbetween train tracks
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 2257.0 2530.0 11.0    pickup_health[1]					// Roca Escalante, tucked between two dumpsters
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 2418.0 2119.0 24.0    pickup_health[2]				// Old Venturas Strip, behind 7th heaven rainbow sign (player has to jump on top opf car roof, jump across to roof in front of Zip Pizza, then run along behind sign)
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 2458.0 1293.0 11.0 	 pickup_health[3]			// The Camel's Toe, Behind the church, little alleyway
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 2098.0 1161.0 12.0    pickup_health[4]				// Come-A-Lot, in the middle of the little castle
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 1913.0 0702.0 11.0 	 pickup_health[5]			// Last Dime Motel, underneath car park covering
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 2624.0 -1600.0 10.0   pickup_health[6]				 //under bridge beside flood control
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 2891.0 2102.0 11.65     pickup_health[7]			//Playa del Seville near Life's a Beach
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 2791.0 -1096.0 31.0   pickup_health[8]				 //behind a las colinas appartment
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 2062.0 -1966.0 13.0 	 pickup_health[9]			 //Willowfield beside train tracks between platform and crates.
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 1574.0 -1888.0 14.0   pickup_health[10]				 //Verdant Bluffs outside restaurant seating
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 1339.0 -1841.0 14.0 	 pickup_health[11]			 //Commerce end of alleyway
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 2250.0 -1060.0 56.0   pickup_health[12]				 //Las Colinas top of entranceway steps
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 1099.0 -1335.0 13.0 	 pickup_health[13]			 //Market behind a super $5 store
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 1014.0 -1006.0 32.0   pickup_health[14]				//beside LS mod garage
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 153.0 -1965.0 4.0 	 pickup_health[15]			//beside the santa maria beach lighthouse
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 1750.0 -2273.0 26.7   pickup_health[16]				 //on top of LS airport heli pad
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -2661.0 -198.0 4.0 	 pickup_health[17]			//back yard of an Ocean flats house
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -2871.0 835.0 40.0    pickup_health[18]				//between back yards of some pallisades homes 
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -2314.0 -128.0 35.0	 pickup_health[19]			 //Garcia construction plot
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -2155.0 -83.0 35.0    pickup_health[20]				//Doherty Los cabras crack lab lot
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -1699.0 76.0 4.0 	 pickup_health[21]			//beside the most excellent dry dock
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -1398.3 502.5 10.8    pickup_health[22]				//inside an open crate in the aircraft carrier
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -2098.0 901.0 77.0 	 pickup_health[23]			//Calton heights at the top of the curvy road
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -1451.0 -116.0 6.0    pickup_health[24]				//inside SF airport underground car park
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -1968.0 410.0 24.0 	 pickup_health[25]			//beside guard booth in downtown hotel's underground carpark
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -2307.0 -1602.0 484.0 pickup_health[26]					//top of Chiliad
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -1831.0 -1623.0 23.0  pickup_health[27]					//Whetstone scrap yard warehouse
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -1550.0 -2739.0 49.0  pickup_health[28]				//Whetstone xoomer station rest area
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -2153.0 -2549.0 31.0  pickup_health[29]					//residential garage
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 112.0 -224.0 2.0		 pickup_health[30]					 //Yard of warehouse beside the fleisch beer building and pallet ramp USJ
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -590.0 -202.0 81.0	 pickup_health[31]				  //on top of a tree stump just outside the logging site
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 144.0 1875.0 18.0 	 pickup_health[32]				//doorway to a bunker
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -87.0 1366.0 10.0 	 pickup_health[33]				//South side of the Inn
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -2019.0 2357.0 7.0	 pickup_health[34]				 //Beach beside the lighthouse
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -837.0 2761.53 46.0 	 pickup_health[35]				//entrance to a tee pee at the Wigwam Motel
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -1466.0 2639.0 56.0	 pickup_health[36]				 //under the water tower
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -2386.0 2216.0 5.0 	 pickup_health[37]				//doorway of Bayside lighthouse
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 492.0 781.0 -22.0 	 pickup_health[38]					// In the semi-cave
CREATE_PICKUP health PICKUP_ON_STREET_SLOW -322.0 2673.0 63.0 	 pickup_health[39]					//behind building on the way to the chicken stunt jump
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 1245.9048 -775.9602 1084.0078 	 pickup_health[40]				 //health pick up, dark sauna style room model name 
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 934.2552 -935.1726 57.7345 	 pickup_health[41]				 //chatuex marmount balcony in la
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 1722.38 -1640.57 20.22 pickup_health[42]				 //atrium where just business is

CREATE_PICKUP health PICKUP_ON_STREET_SLOW 1173.22 -1324.43 15.43 pickup_health[43]	 // all saints general hospital
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 1877.54 -1982.63 13.48 pickup_health[44]	 // for andy d mission"Los Desperados"

CREATE_PICKUP health PICKUP_ON_STREET_SLOW 2033.5 -1404.72 17.81 pickup_health[45]	 // county general hospital

CREATE_PICKUP health PICKUP_ON_STREET_SLOW 1254.62 327.76 20.25 pickup_health[46]	 // crippin memorial (country side)


CREATE_PICKUP health PICKUP_ON_STREET_SLOW -2653.41 610.22 14.75 pickup_health[47]	 // san fierro hospital

CREATE_PICKUP health PICKUP_ON_STREET_SLOW 1581.24 1776.44 10.92 pickup_health[48]	 // las vegas hospital

CREATE_PICKUP health PICKUP_ON_STREET_SLOW 263.457 1816.14 1.01 pickup_health[49]	 //health at inside area51 
CREATE_PICKUP health PICKUP_ON_STREET_SLOW 1278.67 -773.87 1083.116 pickup_health[50]	 //health at inside mansion 
 */


				 
////END OF HEALTH


	///Body Armour
VAR_INT pickup_armour[48]

CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 2097.0 2154.0 14.0		pickup_armour[0]	 				// Redsands East, in corner of balcony-like area (have to run round entire inner section of building to get to it)
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 2435.0 1663.0 16.0  		pickup_armour[1]		// Caligula's Palace, on top of oddly shaped central motel building 
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 2500.0 0925.0 11.0  		pickup_armour[2]		// Las Venturas, in the corner of the church
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 2106.0 1004.0 11.0  		pickup_armour[3]		// Come-A-Lot, in archway underneath sign
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 1531.0 0925.0 11.0  		pickup_armour[4]		// LVA Freight Depot, In corner next to wooden fence inside courtyard of building
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 1433.0 1852.0 10.8  		pickup_armour[5]		// Las Venturas Airport, in the corner, slightly hidden from the road
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 1000.0 1068.0 11.0  		pickup_armour[6]		// Greenglass College, behind (east of) the dumpsters
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 1269.0 1352.0 11.0  		pickup_armour[7]		// Las Venturas Airport, in the corner of this hanger (opposite hanger used in 'Freefall')
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 2294.0 0547.0 01.0  		pickup_armour[8]		// Red County, between the stairways
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 2543.0 -1625.0 12.0 		pickup_armour[9]		 //under flood control overpass
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 2339.0 -1944.0 13.0 		pickup_armour[10]	 //behind willowfield building
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 2767.0 -1192.0 69.0 		pickup_armour[11]	 //behind house in East Beach
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 2112.0 -1990.0 14.0 		pickup_armour[12]	 //Willowfield beside train tracks between mound of scrap and crates.
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 2544.0 -1120.0 62.0 		pickup_armour[13]	 //Las Colinas yard
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 1562.0 -1888.0 14.0 		pickup_armour[14]	 //Verdant Bluffs outside restaurant seating
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 1086.0 -1806.0 17.0 		pickup_armour[15]	//Conference centre orbital walkway
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 253.0 80.0 1004.0 		pickup_armour[16]		//LS police locker room	  <----------------INSIDE PICKUP
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 1759.0 -2242.0 1.0 		pickup_armour[17]	 //front of LS airport
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW -2650.0 -198.0 4.0 		pickup_armour[18]	 //back yard of an Ocean flats house
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW -2285.0 -24.0 35.0 		pickup_armour[19]	 //Garcia alleyway
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW -1863.0 112.0 15.0 		pickup_armour[20]	//Hidden behind Solarin Industries building
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW -1574.0 1268.0 1.27 		pickup_armour[21]	//on a concrete pier in Esplanade East 
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW -2916.0 992.0 8.0 		pickup_armour[22]	//Palisades bottom of a cliff
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW -2513.0 770.0 35.0 		pickup_armour[23]	// Juniper Hill supa save carpark
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW -1394.0 -373.0 6.0 		pickup_armour[24]	//SF airport outside terminal
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW -2303.0 -1606.0 484.0	pickup_armour[25]	 //top of Chiliad
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW -2092.0 -2330.0 31.0 	pickup_armour[26]	//behind restaurant wall
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW -2260.0 2568.0 6.0 		pickup_armour[27]	//Bayside beach
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW -902.0 2689.0 42.0 		pickup_armour[28]	//between huts at the USJ
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW -317.0 2651.0 67.0 		pickup_armour[29]	//on roof behind the Pecker's feed and seed sign
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 1325.0 190.0 19.0 		pickup_armour[30]//inside trailer park
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 2487.0 139.0 27.0 		pickup_armour[31]//between a house and it's garage
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 761.0 380.0 23.0 		pickup_armour[32]// In trailer park behind shack
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW -51.0 -232.0 7.0 		pickup_armour[33]//round the back of the fleisch beer factory, beside the back door
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 252.0 2616.0 17.0 		pickup_armour[34]//In an aeroplane fuselage
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 212.0 1807.0 22.0 		pickup_armour[35]//top of a guard tower
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 1291.7604 -803.4566 1089.9297 pickup_armour[36]//armour, bedroom
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 1715.12 -1673.51 20.22 pickup_armour[37]//armour, bedroom
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 943.0120 -939.8284 57.7345 	 pickup_armour[38]				 //chatuex marmount balcony in la
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 275.169 1859.699 9.81 	 pickup_armour[39] //body armour at inside area51 	
CREATE_PICKUP bodyarmour PICKUP_ONCE 1268.34 -804.33 1084.01 	 pickup_armour[40] //body armour at inside mansion mad dog

CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 263.52 83.14 1001.0391 	 pickup_armour[41] //police station 1
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 264.2632 117.0737 1008.8125 	 pickup_armour[42] //police station 1
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 245.0618 195.9429 1008.1719 	 pickup_armour[43] //police station 1
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 215.8489 126.0831 1003.2257 	 pickup_armour[44] //police station 1
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 2125.4934 -2275.0366 20.5202	 pickup_armour[45] //for imrans warehouse mission
//CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 266.2813 1816.3976 1.594 	 pickup_armour[46] // body armor for area 51 mission
CREATE_PICKUP bodyarmour PICKUP_ON_STREET_SLOW 2230.4504 -2286.0044 14.3751	 pickup_armour[47] //for imrans warehouse mission

					 	 
//END OF BODY ARMOR		 
					 	 
						 
// POLICE BRIBES		 
VAR_INT pickup_bribe[50]			   

//LAS VENTURAS:

CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 2743.0 1316.0 08.0   pickup_bribe[0]  // Julius Thruway Est, underneath the overpass (near to one of the police bike pickups
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 2168.66 2267.96  15.34 pickup_bribe[1] // The Emerald Isleween this set of trees, at about the right height to grab from doing the jump  
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 2408.0 1389.0 22.0  pickup_bribe[2]  // Royale Casino, near powerlines, accessible from USJ to south
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 2034.0 0842.0 10.0  pickup_bribe[3] 	// Julius Thruway S should be able to pick it up by jumping off the central divider
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 2096.0 1287.0 10.8  pickup_bribe[4] 	// The Camel's Toe, beneath the big needle style monument
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 1987.0 1543.0 16.0  pickup_bribe[5] 	// The Strip, in a good position to grab by jumping off the pirate ships ramp in a bike
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 1854.0 0912.0 10.80  pickup_bribe[6] 	// The Four Dragons Casino, in the little fenced off bit (should be nice for coming off the freeway,   smashing through the fences and grabbing it)
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 2540.38 2527.85 10.39 pickup_bribe[7] // Julius Thruway East, in between tracks and building, along gravel path to Highway.
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 1548.02 1024.47 10.39 pickup_bribe[8] // LVA Freight Depot, inside cordoned off fence - great to smash through.
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 1592.91 2053.83 10.26 pickup_bribe[9] // Redsands West, in small alley with shootable wooden fences - poifect!
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 1971.29 2330.26 10.41 pickup_bribe[10] // Redsands East, in box filled alleyway, Starsky and Hutch style
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 1700.74 1792.70 10.41 pickup_bribe[11] // Las Venturas Airport, by building, right before jumping highway via ramp.



//LOS SANTOS:

CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 2581.0 -1491.0 24.0 pickup_bribe[12]// East Los Santos going through gap in fence to flood control
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 2296.0 -1696.0 14.0 pickup_bribe[13]// Ganton, through aleyway close to hub
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 2273.0 -1099.0  38.0 pickup_bribe[14] //Las Colinas sloping alleyway
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 2716.0 -1048.0 66.0 pickup_bribe[15]	// collected by jumping east down this las colinas road
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 2614.0 -2496.0 33.0 pickup_bribe[16] //top of bridge arch Ocean docks
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 1183.85 -1250.68 14.7 pickup_bribe[17]	// alleyway slash courtyard which forms a kind of crossroads
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 1970.0 -1158.0 21.0 pickup_bribe[18]  //archway under bridge in Glen Park
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 734.0 -1137.0 18.0  pickup_bribe[19]	// Vinewood underpass
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 2553.76 -2464.31 13.62 pickup_bribe[20] // Ocean Docks, in between building and cargo
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 1204.06 -1613.89 13.28 pickup_bribe[21] // Verona Beach, in alleyway
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 611.21 -1459.63 14.01 pickup_bribe[22] // Rodeo, in building foyer thing.
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 1116.67 -719.91 100.17 pickup_bribe[23] // Mullholland, next to house, just before massive leap down to road below



//SAN FIERRO:

CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -1903.10 -466.44 25.18 pickup_bribe[24] // Foster Valley, under Highway
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -2657.0 -144.0  4.0 pickup_bribe[25]	// alleyway behind houses Ocean Flats
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -2454.0 -166.0  35.0 pickup_bribe[26] // Hashbury alleyway
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -2009.0 1227.0  32.0 pickup_bribe[27] // Calton heights steep alleyway
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -2120.0 96.39 39.0    pickup_bribe[28] //Doherty Alley
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -2411.0 -334.0 37.0  pickup_bribe[29]  //Avispa country club underpass
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -1690.0 450.0 13.0   pickup_bribe[30] // traintracks beside the Xoomer station]
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -1991.26 -1144.13 29.69 pickup_bribe[31] // Foster Valley, on road turnoff, quite secluded.
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -2636.13 -492.83 70.09 pickup_bribe[32] // Missionary Hill, on edge of hill leading down to road below
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -2022.68 345.98 35.17 pickup_bribe[33] // King's, inside scaffolding barrier
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -2683.20 784.13 49.98 pickup_bribe[34] //Santa Flora, back of houses, in front of residential garages
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -1820.67 -154.12 9.40 pickup_bribe[35]   // Doherty, in industrial warehouse thingy



//DESERT/COUNTRY:

CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -736.0 66.0 24.0 pickup_bribe[36] 	// small dirt track linking curvy road
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 262.33 -149.12 1.58 pickup_bribe[37]    // beside where the gun nut g/f is encountered
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 1643.0 264.0 20.0  pickup_bribe[38] // under freeway overpass at the intersection
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 601.98 2150.38 39.41 pickup_bribe[39] // Bone County, on road edge in bushes
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -1407.0 -2039.0 1.0  pickup_bribe[40] // over the forded river
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -2156.0 -2371.0 31.0 pickup_bribe[41] // angel pine alleyway
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -419.25 1362.36 12.21 pickup_bribe[42] // in secret tunnel underground
//CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -111.0 1132.0 20.0   pickup_bribe[43] // drive through barn
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 629.04 2842.83 25.21 pickup_bribe[44] // at bottom of slope at side of desert
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 690.49 -209.59 25.60 pickup_bribe[45] // Red County, on rocky slope
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 88.82 -125.10 0.85 pickup_bribe[46] // Blueberry Acres, on field road
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 215.69 1089.10 16.40 pickup_bribe[47] // Bone County, in between trees
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -2305.24 2310.11 4.98 pickup_bribe[48] // Bayside Marina, on pier
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -213.61 2717.44 62.68 pickup_bribe[49] // Las Payasadas, in between houses in alley

// END BRIBES

MISSION_END												